package com.example.rafael.diviaapp;

/**
 * Created by Rafael on 10/01/2018.
 */

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
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

    AutoCompleteTextView mAutoCompleteTextView;
    TextView mTextViewLigne;
    TextView mTextViewArret;
    TextView mTextViewSens;
    TextView mTextViewTime1;
    TextView mTextViewTime2;
    ConstraintLayout mCLArretInfo;
    ConstraintLayout mCLNoData;
    ConstraintLayout mCLLoading;
    Context mContextActivityMain;
    private List<LignesTransport> mlignesTransportList = new ArrayList<LignesTransport>(); //Soon to be erased
    private List<ArretsTransport> mArretTransportList = new ArrayList<ArretsTransport>();
    private String[] mLignesString; //Soon to be erased
    private String[] mArretsString; //Soon to be erased

    public static boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) return false;
        final NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        // if no network is available networkInfo will be null
        // otherwise check if we are connected
        return (networkInfo != null && networkInfo.isConnected());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         mContextActivityMain = this;

        loadLignesAndArrets(this);
        setupAutoCompleteTextView();
        setupArretInfoOfTheTextView();



    }

    private void setupArretInfoOfTheTextView() {

        mTextViewLigne = (TextView) findViewById(R.id.textView_Ligne_main);
        mTextViewArret = (TextView) findViewById(R.id.textView_Arret_main);
        mTextViewSens  = (TextView) findViewById(R.id.textView_Sens_main);
        mTextViewTime1 = (TextView) findViewById(R.id.textView_time1_main);
        mTextViewTime2 = (TextView) findViewById(R.id.textView_time2_main);
        mCLArretInfo = (ConstraintLayout) findViewById(R.id.cl_arret_temp_information);
        mCLNoData = (ConstraintLayout) findViewById(R.id.cl_arret_temp_no_data);
        mCLLoading = (ConstraintLayout) findViewById(R.id.cl_loading_same_pos_temp);

        mCLArretInfo.setVisibility(View.GONE);
        mCLNoData.setVisibility(View.VISIBLE);
        mCLLoading.setVisibility(View.GONE);

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

    private class getArretInfo extends AsyncTask<String, Void, Xml_Data_Arret_Temp>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mCLArretInfo.setVisibility(View.GONE);
            mCLNoData.setVisibility(View.GONE);
            mCLLoading.setVisibility(View.VISIBLE);
        }

        @Override
        protected Xml_Data_Arret_Temp doInBackground(String... strings) {

            String arretUrl = strings[0];
            String resultXML = null;
            //Get XML from site

            if (!isNetworkAvailable(mContextActivityMain)){
                return null;
            }

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

            return arretTemp;
        }

        @Override
        protected void onPostExecute(Xml_Data_Arret_Temp dataArret) {
            super.onPostExecute(dataArret);

            if (dataArret == null){
                mCLLoading.setVisibility(View.GONE);
                mCLNoData.setVisibility(View.VISIBLE);
                return;
            }

            String NPassages = dataArret.getXmldata().getHoraires().getHoraire().getPassages().getNb();
            if(Integer.parseInt(NPassages) < 2) {
                mCLLoading.setVisibility(View.GONE);
                mCLNoData.setVisibility(View.VISIBLE);
                return;
            }

            String codeArret = dataArret.getXmldata().getHoraires().getHoraire().getDescription().getCode();

            for (ArretsTransport arret : mArretTransportList) {
                if (arret.getArretCode().toLowerCase().contains(codeArret.toString().toLowerCase())) {
                    String nextT1 = dataArret.getXmldata().getHoraires().getHoraire().getPassages().getPassage()[0].getDuree();
                    String nextT2 = dataArret.getXmldata().getHoraires().getHoraire().getPassages().getPassage()[1].getDuree();

                    mTextViewLigne.setText(arret.getArretLineNom());
                    mTextViewArret.setText(arret.getArretNom());
                    mTextViewSens.setText(arret.getArretVersSens());
                    mTextViewTime1.setText(nextT1);
                    mTextViewTime2.setText(nextT2);

                    String hexColor = Integer.toHexString(Integer.parseInt(arret.getArretLineColor()));
                    mTextViewLigne.setBackgroundColor(0xff000000 + Integer.parseInt(hexColor,16));

                    mCLArretInfo.setVisibility(View.VISIBLE);
                    mCLLoading.setVisibility(View.GONE);

                    break;
                }
            }

        }
    }
}
