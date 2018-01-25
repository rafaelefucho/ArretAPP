package com.example.rafael.diviaapp;

/**
 * Created by Rafael on 10/01/2018.
 */

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.example.rafael.diviaapp.utilities.AdapterArret;
import com.example.rafael.diviaapp.utilities.ArretsTransport;
import com.example.rafael.diviaapp.utilities.FavoritesRecyclerAdapter;
import com.example.rafael.diviaapp.utilities.JsonUtils.Xml_Data_Arret_Temp;
import com.example.rafael.diviaapp.utilities.LignesTransport;
import com.example.rafael.diviaapp.utilities.NetworkUtils;
import com.example.rafael.diviaapp.utilities.data.ArretFavoriteContract;
import com.example.rafael.diviaapp.utilities.data.ArretFavoriteDBHelper;
import com.example.rafael.diviaapp.utilities.loadData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FavoritesRecyclerAdapter.FavoritesItemClickListener{

    private AutoCompleteTextView mAutoCompleteTextView;

    private RecyclerView mFavoritesRV;
    private FavoritesRecyclerAdapter mFavoritesRecyclerAdapter;

    private TextView mTextViewLigne;
    private TextView mTextViewArret;
    private TextView mTextViewSens;
    private TextView mTextViewTime1;
    private TextView mTextViewTime2;

    private ConstraintLayout mCLArretInfo;
    private ConstraintLayout mCLNoData;
    private ConstraintLayout mCLLoading;

    private Context mContextActivityMain;

    private List<LignesTransport> mlignesTransportList = new ArrayList<LignesTransport>(); //Soon to be erased
    private List<ArretsTransport> mArretTransportList = new ArrayList<ArretsTransport>();
    private String[] mLignesString; //Soon to be erased
    private String[] mArretsString; //Soon to be erased

    private SQLiteDatabase mDBArretFavorite;

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
        setupFavoritesDB();
        setupRecyclerView();


    }

    private void setupFavoritesDB() {
        ArretFavoriteDBHelper arretFavoriteDBHelper = new ArretFavoriteDBHelper(this);
        mDBArretFavorite = arretFavoriteDBHelper.getWritableDatabase();
    }

    private void setupRecyclerView() {


        mFavoritesRV = (RecyclerView) findViewById(R.id.recyclerview_favorites);
        mFavoritesRV.setLayoutManager(new LinearLayoutManager(this));
        Cursor cursor = getAllFavorites();
        mFavoritesRecyclerAdapter = new FavoritesRecyclerAdapter(cursor,this, (FavoritesRecyclerAdapter.FavoritesItemClickListener) this);
        mFavoritesRV.setAdapter(mFavoritesRecyclerAdapter);


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT){
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                long id = (long) viewHolder.itemView.getTag();
                removeFavorite(id);
                mFavoritesRecyclerAdapter.swapCursor(getAllFavorites());

            }
        }).attachToRecyclerView(mFavoritesRV);


        Button btn = (Button) findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_arret_to_favorite(v);
            }
        });
    }

    private Cursor getAllFavorites() {
        return mDBArretFavorite.query(
                ArretFavoriteContract.ArretFavorite.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
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
                    mTextViewArret.setTag(arretURL);
                    new getArretInfo().execute(arretURL);
                }
            }
        });
    }

    private void add_arret_to_favorite(View view){

        String arret = mTextViewArret.getText().toString();
        String refs  = mTextViewArret.getTag().toString();
        String ligne = mTextViewLigne.getText().toString();
        String sens  = mTextViewSens.getText().toString();
        String color = (String) mTextViewLigne.getTag();

        ContentValues cv = new ContentValues();
        cv.put(ArretFavoriteContract.ArretFavorite.COLUMN_ARRET,arret);
        cv.put(ArretFavoriteContract.ArretFavorite.COLUMN_LIGNE,ligne);
        cv.put(ArretFavoriteContract.ArretFavorite.COLUMN_SENS,sens);
        cv.put(ArretFavoriteContract.ArretFavorite.COLUMN_REFS,refs);
        cv.put(ArretFavoriteContract.ArretFavorite.COLUMN_COLOR,color);

        mDBArretFavorite.insert(ArretFavoriteContract.ArretFavorite.TABLE_NAME,null,cv);
        mFavoritesRecyclerAdapter.swapCursor(getAllFavorites());

    }

    private boolean removeFavorite(long id){

        return mDBArretFavorite.delete(ArretFavoriteContract.ArretFavorite.TABLE_NAME,
                ArretFavoriteContract.ArretFavorite._ID + "=" + id,null)>0;
    }

    @Override
    public void onFavoriteItemClick(String clickedFavoriteRef) {

        String arretURL = NetworkUtils.buildUrlArretTemp(clickedFavoriteRef);
        mTextViewArret.setTag(clickedFavoriteRef);
        new getArretInfo().execute(arretURL);

    }


    private class getArretInfo extends AsyncTask<String, Void, JSONObject>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mCLArretInfo.setVisibility(View.GONE);
            mCLNoData.setVisibility(View.GONE);
            mCLLoading.setVisibility(View.VISIBLE);
        }

        @Override
        protected JSONObject doInBackground(String... strings) {

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


            return jsonObj;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject){
            super.onPostExecute(jsonObject);

            //If not internet connection
            if (jsonObject == null){
                mCLLoading.setVisibility(View.GONE);
                mCLNoData.setVisibility(View.VISIBLE);
                TextView textView = (TextView) findViewById(R.id.cl_TextView_no_data);
                textView.setText(getResources().getString(R.string.no_internet_connection));
                return;
            }

            Gson gson = new GsonBuilder().create();
            Xml_Data_Arret_Temp dataArret = gson.fromJson(jsonObject.toString() , Xml_Data_Arret_Temp.class);
            String NPassages = dataArret.getXmldata().getHoraires().getHoraire().getPassages().getNb();
            String NPassagess = null;
            try {
                NPassagess = jsonObject.getJSONObject("horaire").getString("passages");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //If there is not next last buses
            if(Integer.parseInt(NPassages) < 2) {
                mCLLoading.setVisibility(View.GONE);
                mCLNoData.setVisibility(View.VISIBLE);
                TextView textView = (TextView) findViewById(R.id.cl_TextView_no_data);
                textView.setText(getResources().getString(R.string.no_data_arrrets));
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

                    mTextViewLigne.setTag(arret.getArretLineColor()); //Putting color for adding it to the DB

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
