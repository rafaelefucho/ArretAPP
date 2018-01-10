package com.example.rafael.diviaapp;

/**
 * Created by Rafael on 10/01/2018.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.rafael.diviaapp.utilities.ArretsTransport;
import com.example.rafael.diviaapp.utilities.LignesTransport;
import com.example.rafael.diviaapp.utilities.loadData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<LignesTransport> mlignesTransportList = new ArrayList<LignesTransport>();
    private List<ArretsTransport> mArretTransportList = new ArrayList<ArretsTransport>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadLignesAndArrets(this);
        TextView textView2 = (TextView) findViewById(R.id.text_view2);
        textView2.setText(mlignesTransportList.get(0).toString());

        TextView textView = (TextView) findViewById(R.id.text_view);
        textView.setText(mArretTransportList.get(0).toString());

    }

    private void loadLignesAndArrets(MainActivity mainActivity) {
        mlignesTransportList = loadData.loadLignes(mainActivity);
        mArretTransportList = loadData.loadArrets(mainActivity);
    }


}
