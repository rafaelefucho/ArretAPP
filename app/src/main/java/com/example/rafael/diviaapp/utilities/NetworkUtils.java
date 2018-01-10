package com.example.rafael.diviaapp.utilities;

import java.io.IOException;
import java.net.URL;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by Rafael on 10/01/2018.
 */

public class NetworkUtils {

    final static String URL_KEOLIS_LIGNES ="http://timeo3.keolis.com/relais/217.php?xml=1";
    final static String URL_KEOLIS_BASE_ARRETS ="http://timeo3.keolis.com/relais/217.php?";

    public static String buildUrlArret(String ligne, String sens){
        //Builds a URL like this one: http://timeo3.keolis.com/relais/217.php?xml=1&ligne=T1&sens=A
        String stringURL = URL_KEOLIS_BASE_ARRETS + "xml=1&ligne=" + ligne + "&sens=" + sens;
        return stringURL;
    }


    public static String getXMLfromKeolis(String URL) throws IOException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(URL)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();

    }
}
