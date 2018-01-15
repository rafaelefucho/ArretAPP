package com.example.rafael.diviaapp;

/**
 * Created by Rafael on 10/01/2018.
 */

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
import com.example.rafael.diviaapp.utilities.loadData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<LignesTransport> mlignesTransportList = new ArrayList<LignesTransport>();
    private List<ArretsTransport> mArretTransportList = new ArrayList<ArretsTransport>();
    private String[] mLignesString;
    private String[] mArretsString; //For autocomplete

    AutoCompleteTextView mAutoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadLignesAndArrets(this);

        mAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        //ArrayAdapter adapterArrets = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line,mArretsString);
        AdapterArret adapterArret = new AdapterArret(this, android.R.layout.simple_dropdown_item_1line, mArretTransportList);

        mAutoCompleteTextView.setAdapter(adapterArret);
        mAutoCompleteTextView.setThreshold(3);

        final TextView textView = (TextView) findViewById(R.id.textView);

        mAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                textView.setText(String.valueOf(position));

            }
        });



    }

    private void loadLignesAndArrets(MainActivity mainActivity) {
        mlignesTransportList = loadData.loadLignes(mainActivity);
        mArretTransportList = loadData.loadArrets(mainActivity);
        mArretsString = loadData.fillArrets(mArretTransportList);
    }


}
