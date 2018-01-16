package com.example.rafael.diviaapp;

/**
 * Created by Rafael on 10/01/2018.
 */

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.example.rafael.diviaapp.utilities.AdapterArret;
import com.example.rafael.diviaapp.utilities.ArretsTransport;
import com.example.rafael.diviaapp.utilities.LignesTransport;
import com.example.rafael.diviaapp.utilities.NetworkUtils;
import com.example.rafael.diviaapp.utilities.loadData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<LignesTransport> mlignesTransportList = new ArrayList<LignesTransport>();
    private List<ArretsTransport> mArretTransportList = new ArrayList<ArretsTransport>();
    private String[] mLignesString; //Soon to be erased
    private String[] mArretsString; //Soon to be erased

    AutoCompleteTextView mAutoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadLignesAndArrets(this);
        setupAutoCompleteTextView();



    }

    private class getArretInfo extends AsyncTask<String, Void, Void>{

        @Override
        protected Void doInBackground(String... strings) {

            String arretUrl = strings[0];
            String resultXML = null;
            try {
                resultXML = NetworkUtils.getXMLfromKeolis(arretUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }



            return null;
        }
    }

    private void loadLignesAndArrets(MainActivity mainActivity) {
        mlignesTransportList = loadData.loadLignes(mainActivity);
        mArretTransportList = loadData.loadArrets(mainActivity);
        mArretsString = loadData.fillArrets(mArretTransportList);
    }

    private void setupAutoCompleteTextView(){

        mAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);

        AdapterArret adapterArret = new AdapterArret(this, android.R.layout.simple_dropdown_item_1line, mArretTransportList);
        mAutoCompleteTextView.setAdapter(adapterArret);
        mAutoCompleteTextView.setThreshold(3);

        mAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Object item = parent.getItemAtPosition(position);
                if (item instanceof ArretsTransport){
                    ArretsTransport arret =(ArretsTransport) item;
                    String arretURL = NetworkUtils.buildUrlArretTemp(arret.getArretRefs());
                }
            }
        });
    }

}
