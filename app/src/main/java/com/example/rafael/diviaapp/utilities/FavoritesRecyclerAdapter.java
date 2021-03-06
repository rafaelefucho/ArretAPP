package com.example.rafael.diviaapp.utilities;


import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rafael.diviaapp.R;
import com.example.rafael.diviaapp.utilities.data.ArretFavoriteContract;

/**
 * Created by Rafael on 25/01/2018.
 */

public class FavoritesRecyclerAdapter extends RecyclerView.Adapter<FavoritesRecyclerAdapter.FavoritesViewHolder> {

    final private FavoritesItemClickListener mFavoritesItemClickListener;
    private Cursor mCursor;
    private Context mContext;

    public FavoritesRecyclerAdapter(Cursor mCursor, Context mContext, FavoritesItemClickListener favoritesItemClickListener) {
        this.mCursor = mCursor;
        this.mContext = mContext;
        this.mFavoritesItemClickListener = favoritesItemClickListener;
    }

    public boolean isArretOnRecyclerView(String newRefs){

        if(mCursor != null)
        {
            String refs;
            mCursor.moveToFirst();
            mCursor.moveToPrevious();
            while(mCursor.moveToNext())
            {
                refs = mCursor.getString(mCursor.getColumnIndex(ArretFavoriteContract.ArretFavorite.COLUMN_REFS));
                if (refs.contentEquals(newRefs)){
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public FavoritesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.simple_item_favorite_adapter, parent, false);
        return new FavoritesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoritesViewHolder holder, int position) {

        if (!mCursor.moveToPosition(position))
            return; // bail if returned null

        String arret = mCursor.getString(mCursor.getColumnIndex(ArretFavoriteContract.ArretFavorite.COLUMN_ARRET));
        String ligne = mCursor.getString(mCursor.getColumnIndex(ArretFavoriteContract.ArretFavorite.COLUMN_LIGNE));
        String sens =  mCursor.getString(mCursor.getColumnIndex(ArretFavoriteContract.ArretFavorite.COLUMN_SENS));
        String hexColor = mCursor.getString(mCursor.getColumnIndex(ArretFavoriteContract.ArretFavorite.COLUMN_COLOR));
        long id = mCursor.getLong(mCursor.getColumnIndex(ArretFavoriteContract.ArretFavorite._ID));



        holder.Ligne.setBackgroundColor(Color.parseColor(hexColor));
        holder.Arret.setText(arret);
        holder.Ligne.setText(ligne);
        holder.Sens.setText(sens);

        holder.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {
        if (mCursor == null) return 0;
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        // Always close the previous mCursor first
        if (mCursor != null) mCursor.close();
        mCursor = newCursor;
        if (newCursor != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }

    public interface FavoritesItemClickListener {
        void onFavoriteItemClick (String clickedFavoriteRef);
    }

    class FavoritesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView Ligne;
        TextView Arret;
        TextView Sens;


        public FavoritesViewHolder(View view) {
            super(view);
            Ligne = (TextView) view.findViewById(R.id.textView_Ligne_adapter);
            Arret = (TextView) view.findViewById(R.id.textView_Arret_adapter);
            Sens  = (TextView) view.findViewById(R.id.textView_Sens_adapter);
            view.setOnClickListener(this);
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    CharSequence text = mContext.getString(R.string.hint_to_delete);
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(mContext, text, duration);
                    toast.show();

                    return false;
                }
            });


        }


        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            if (!mCursor.moveToPosition(clickedPosition))
                return; // bail if returned null
            String refs = mCursor.getString(mCursor.getColumnIndex(ArretFavoriteContract.ArretFavorite.COLUMN_REFS));
            mFavoritesItemClickListener.onFavoriteItemClick(refs);
        }
    }
}