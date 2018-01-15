package com.example.rafael.diviaapp.utilities;

import android.content.Context;
import android.util.Log;

import com.example.rafael.diviaapp.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafael on 10/01/2018.
 */

public class loadData {

    public static String[] fillArrets(List<ArretsTransport> list){
        String[] array = new String[list.size()];
        int index = 0;
        for (ArretsTransport value : list) {
            array[index] = (String) (value.getArretNom() + " --> "+ value.getArretLineSens()); //For the future fix this suggestion
            index++;
        }
        return array;
    }

    public static List<LignesTransport> loadLignes(Context context) {
        // Based on https://stackoverflow.com/questions/19974708/reading-csv-file-in-resources-folder-android/19976110
        List<LignesTransport> mlignesTransportList = new ArrayList<LignesTransport>();
        InputStream inputStream = context.getResources().openRawResource(R.raw.lignes);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, Charset.forName("UTF-8"))
        );

        String line = "";
        try {
            while((line = reader.readLine()) != null ){
                String[] lineWithoutcommas = line.split(",");
                LignesTransport ligne = new LignesTransport(
                        lineWithoutcommas[0], //Code
                        lineWithoutcommas[1], //Nom
                        lineWithoutcommas[2], //Sens
                        lineWithoutcommas[3], //Vers
                        lineWithoutcommas[4]  //Color
                );
                mlignesTransportList.add(ligne);
            }
        } catch (IOException e) {
            Log.wtf("diviapp","Error reading data file" + line, e);
            e.printStackTrace();
        }
        return mlignesTransportList;

    }
    public static List<ArretsTransport> loadArrets(Context context) {
        // Based on https://stackoverflow.com/questions/19974708/reading-csv-file-in-resources-folder-android/19976110
        List<ArretsTransport> arretsTransportList = new ArrayList<ArretsTransport>();
        InputStream inputStream = context.getResources().openRawResource(R.raw.arrets);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, Charset.forName("UTF-8"))
        );

        String line = "";
        try {
            while((line = reader.readLine()) != null ){
                String[] lineWithoutcommas = line.split(",");

                ArretsTransport ligne = new ArretsTransport(
                        lineWithoutcommas[0], //Code
                        lineWithoutcommas[1], //Nom
                        lineWithoutcommas[2], //Refs
                        lineWithoutcommas[3], //Ligne Code
                        lineWithoutcommas[4],  //Ligne Sens
                        lineWithoutcommas[5]  //Ligne Vers
                );
                arretsTransportList.add(ligne);
            }
        } catch (IOException e) {
            Log.wtf("diviapp","Error reading data file" + line, e);
            e.printStackTrace();
        }
        return arretsTransportList;

    }

}
