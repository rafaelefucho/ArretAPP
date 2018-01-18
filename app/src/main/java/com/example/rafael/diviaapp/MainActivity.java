package com.example.rafael.diviaapp;

/**
 * Created by Rafael on 10/01/2018.
 */

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.example.rafael.diviaapp.utilities.AdapterArret;
import com.example.rafael.diviaapp.utilities.ArretsTransport;
import com.example.rafael.diviaapp.utilities.JsonUtils.Xml_Data_Arret_Temp;
import com.example.rafael.diviaapp.utilities.LignesTransport;
import com.example.rafael.diviaapp.utilities.NetworkUtils;
import com.example.rafael.diviaapp.utilities.loadData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<LignesTransport> mlignesTransportList = new ArrayList<LignesTransport>(); //Soon to be erased
    private List<ArretsTransport> mArretTransportList = new ArrayList<ArretsTransport>();
    private String[] mLignesString; //Soon to be erased
    private String[] mArretsString; //Soon to be erased

    AutoCompleteTextView mAutoCompleteTextView;
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadLignesAndArrets(this);
        setupAutoCompleteTextView();

        mTextView = (TextView) findViewById(R.id.textView);


    }

    private class getArretInfo extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {

            String arretUrl = strings[0];
            String resultXML = null;
            //Get XML from site
            try {
                resultXML = NetworkUtils.getXMLfromKeolis(arretUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //Transform into json Object
            JSONObject jsonObj = null;
            try {
                jsonObj = XML.toJSONObject(resultXML);
            } catch (JSONException e) {
                Log.e("JSON exception", e.getMessage());
                e.printStackTrace();
            }

            Gson gson = new GsonBuilder().create();
            Xml_Data_Arret_Temp arretTemp = gson.fromJson(jsonObj.toString() , Xml_Data_Arret_Temp.class);

            String next = arretTemp.getXmldata().getHoraires().getHoraire().getPassages().getPassage()[0].getDuree();
            String nextA = arretTemp.getXmldata().getHoraires().getHoraire().getPassages().getPassage()[1].getDuree();

            return next + "\n" + nextA;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mTextView.setText(s);
        }
    }

    private void loadLignesAndArrets(MainActivity mainActivity) {
        mlignesTransportList = loadData.loadLignes(mainActivity);
        mArretTransportList = loadData.loadArrets(mainActivity);
        mArretsString = loadData.fillArrets(mArretTransportList);
    }

    private void setupAutoCompleteTextView(){

        mAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);

        AdapterArret adapterArret = new AdapterArret(this, R.layout.simple_dropdown_item, mArretTransportList);
        mAutoCompleteTextView.setAdapter(adapterArret);
        mAutoCompleteTextView.setThreshold(3);

        mAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Object item = parent.getItemAtPosition(position);
                if (item instanceof ArretsTransport){
                    ArretsTransport arret =(ArretsTransport) item;
                    String arretURL = NetworkUtils.buildUrlArretTemp(arret.getArretRefs());
                    new getArretInfo().execute(arretURL);
                }
            }
        });
    }

}
