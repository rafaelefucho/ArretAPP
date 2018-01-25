package com.example.rafael.diviaapp.utilities.data;

import android.provider.BaseColumns;

/**
 * Created by Rafael on 25/01/2018.
 */

public class ArretFavoriteContract {

    public static final class ArretFavorite implements BaseColumns {

        public static final String TABLE_NAME = "arret_favorite";

        public static final String COLUMN_LIGNE = "ligne";
        public static final String COLUMN_ARRET = "arret";
        public static final String COLUMN_SENS  = "sens";
        public static final String COLUMN_REFS  = "refs";
        public static final String COLUMN_COLOR = "color";

    }
}
