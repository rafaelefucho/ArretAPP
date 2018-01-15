package com.example.rafael.diviaapp.utilities;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.example.rafael.diviaapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafael on 15/01/2018.
 */

public class AdapterArret extends ArrayAdapter<ArretsTransport> {
    private final Context mContext;
    private final List<ArretsTransport> mArrets;
    private final List<ArretsTransport> mArretsAll;
    private final int mLayoutResourceId;

    public AdapterArret(Context context, int resource, List<ArretsTransport> arrets) {
        super(context, resource, arrets);
        this.mContext = context;
        this.mLayoutResourceId = resource;
        this.mArrets = new ArrayList<>(arrets);
        this.mArretsAll = new ArrayList<>(arrets);
    }

    public int getCount() {
        return mArrets.size();
    }

    public ArretsTransport getItem(int position) {
        return mArrets.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            if (convertView == null) {
                LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
                convertView = inflater.inflate(mLayoutResourceId, parent, false);
            }
            ArretsTransport arret = getItem(position);
            TextView name = (TextView) convertView.findViewById(android.R.id.text1);
            name.setText(arret.getArretLineCode() + ": "+ arret.getArretNom() + " --> "+ arret.getArretVersSens());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            public String convertResultToString(Object resultValue) {
                return ((ArretsTransport) resultValue).getArretNom();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                List<ArretsTransport> arretsSuggestion = new ArrayList<>();
                if (constraint != null) {
                    for (ArretsTransport arret : mArretsAll) {
                        if (arret.getArretNom().toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                            arretsSuggestion.add(arret);
                        }
                    }
                    filterResults.values = arretsSuggestion;
                    filterResults.count = arretsSuggestion.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mArrets.clear();
                if (results != null && results.count > 0) {
                    // avoids unchecked cast warning when using mDepartments.addAll((ArrayList<Department>) results.values);
                    for (Object object : (List<?>) results.values) {
                        if (object instanceof ArretsTransport) {
                            mArrets.add((ArretsTransport) object);
                        }
                    }
                    notifyDataSetChanged();
                } else if (constraint == null) {
                    // no filter, add entire original list back in
                    mArrets.addAll(mArretsAll);
                    notifyDataSetInvalidated();
                }
            }
        };
    }
}
