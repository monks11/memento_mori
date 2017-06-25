package com.monks.memento_mori.providers;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class AppSettingsContentProvider extends ContentProvider {

    public static final String KEY_DATA = "KEY_DATA";

    private static final String AUTHORITY = "com.monks.memento_mori.providers.AppSettingsContentProvider.jC9MaHGkTy";

    private static final String KEY_BOOLEAN_IS_SHOW_TERMS   = "KEY_BOOLEAN_IS_SHOW_TERMS";
    private static final String KEY_STRING_NAME             = "KEY_STRING_NAME";
    private static final String KEY_INT_YEAR                = "KEY_INT_YEAR";
    private static final String KEY_INT_MONTH               = "KEY_INT_MONTH";
    private static final String KEY_INT_DAY                 = "KEY_INT_DAY";
    private static final String KEY_LONG_TIME               = "KEY_LONG_TIME";
    private static final String KEY_LONG_HEARTBEATS         = "KEY_LONG_HEARTBEATS";

    private static final String KEY_DAY_OF_WEEK             = "KEY_DAY_OF_WEEK";
    private static final String KEY_UNLOCK_TODAY            = "KEY_UNLOCK_TODAY";
    private static final String KEY_UNLOCK_WEEK             = "KEY_UNLOCK_WEEK";
    private static final String KEY_UNLOCK_ALL_TIME         = "KEY_UNLOCK_ALL_TIME";
    private static final String KEY_STATISTIC_TIME_TODAY    = "KEY_STATISTIC_TIME_TODAY";
    private static final String KEY_STATISTIC_TIME_WEEK     = "KEY_STATISTIC_TIME_WEEK";
    private static final String KEY_STATISTIC_TIME_ALL_TIME = "KEY_STATISTIC_TIME_ALL_TIME";


    private static final String SHOW_TERMS              = KEY_BOOLEAN_IS_SHOW_TERMS.toLowerCase();
    private static final String USER_NAME               = KEY_STRING_NAME.toLowerCase();
    private static final String BIRTHDAY_YEAR           = KEY_INT_YEAR.toLowerCase();
    private static final String BIRTHDAY_MONTH          = KEY_INT_MONTH.toLowerCase();
    private static final String BIRTHDAY_DAY            = KEY_INT_DAY.toLowerCase();
    private static final String TIME_TO_WIN             = KEY_LONG_TIME.toLowerCase();
    private static final String COUNT_HEARTBEATS        = KEY_LONG_HEARTBEATS.toLowerCase();

    private static final String DAY_OF_WEEK             = KEY_DAY_OF_WEEK.toLowerCase();
    private static final String UNLOCK_TODAY            = KEY_UNLOCK_TODAY.toLowerCase();
    private static final String UNLOCK_WEEK             = KEY_UNLOCK_WEEK.toLowerCase();
    private static final String UNLOCK_ALL_TIME         = KEY_UNLOCK_ALL_TIME.toLowerCase();
    private static final String STATISTIC_TIME_TODAY    = KEY_STATISTIC_TIME_TODAY.toLowerCase();
    private static final String STATISTIC_TIME_WEEK     = KEY_STATISTIC_TIME_WEEK.toLowerCase();
    private static final String STATISTIC_TIME_ALL_TIME = KEY_STATISTIC_TIME_ALL_TIME.toLowerCase();


    public static final Uri SHOW_TERMS_URI              = Uri.parse("content://" + AUTHORITY + "/" + SHOW_TERMS);
    public static final Uri USER_NAME_URI               = Uri.parse("content://" + AUTHORITY + "/" + USER_NAME);
    public static final Uri BIRTHDAY_YEAR_URI           = Uri.parse("content://" + AUTHORITY + "/" + BIRTHDAY_YEAR);
    public static final Uri BIRTHDAY_MONTH_URI          = Uri.parse("content://" + AUTHORITY + "/" + BIRTHDAY_MONTH);
    public static final Uri BIRTHDAY_DAY_URI            = Uri.parse("content://" + AUTHORITY + "/" + BIRTHDAY_DAY);
    public static final Uri TIME_TO_WIN_URI             = Uri.parse("content://" + AUTHORITY + "/" + TIME_TO_WIN);
    public static final Uri COUNT_HEARTBEATS_URI        = Uri.parse("content://" + AUTHORITY + "/" + COUNT_HEARTBEATS);

    public static final Uri DAY_OF_WEEK_URI             = Uri.parse("content://" + AUTHORITY + "/" + DAY_OF_WEEK);
    public static final Uri COUNT_UNLOCK_TODAY_URI      = Uri.parse("content://" + AUTHORITY + "/" + UNLOCK_TODAY);
    public static final Uri COUNT_UNLOCK_WEEK_URI       = Uri.parse("content://" + AUTHORITY + "/" + UNLOCK_WEEK);
    public static final Uri COUNT_UNLOCK_ALL_TIME_URI   = Uri.parse("content://" + AUTHORITY + "/" + UNLOCK_ALL_TIME);
    public static final Uri STATISTIC_TIME_TODAY_URI    = Uri.parse("content://" + AUTHORITY + "/" + STATISTIC_TIME_TODAY);
    public static final Uri STATISTIC_TIME_WEEK_URI     = Uri.parse("content://" + AUTHORITY + "/" + STATISTIC_TIME_WEEK);
    public static final Uri STATISTIC_TIME_ALL_TIME_URI = Uri.parse("content://" + AUTHORITY + "/" + STATISTIC_TIME_ALL_TIME);


    private static final int URI_SHOW_TERMS              = 1;
    private static final int URI_USER_NAME               = 2;
    private static final int URI_BIRTHDAY_YEAR           = 3;
    private static final int URI_BIRTHDAY_MONTH          = 4;
    private static final int URI_BIRTHDAY_DAY            = 5;
    private static final int URI_TIME_TO_WIN             = 6;
    private static final int URI_COUNT_HEARTBEATS        = 7;
    private static final int URI_COUNT_UNLOCK_TODAY      = 8;
    private static final int URI_COUNT_UNLOCK_WEEK       = 9;
    private static final int URI_COUNT_UNLOCK_ALL_TIME   = 10;
    private static final int URI_STATISTIC_TIME_TODAY    = 11;
    private static final int URI_STATISTIC_TIME_WEEK     = 12;
    private static final int URI_STATISTIC_TIME_ALL_TIME = 13;
    private static final int URI_DAY_OF_WEEK             = 14;

    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, SHOW_TERMS, URI_SHOW_TERMS);
        uriMatcher.addURI(AUTHORITY, USER_NAME, URI_USER_NAME);
        uriMatcher.addURI(AUTHORITY, BIRTHDAY_YEAR, URI_BIRTHDAY_YEAR);
        uriMatcher.addURI(AUTHORITY, BIRTHDAY_MONTH, URI_BIRTHDAY_MONTH);
        uriMatcher.addURI(AUTHORITY, BIRTHDAY_DAY, URI_BIRTHDAY_DAY);
        uriMatcher.addURI(AUTHORITY, TIME_TO_WIN, URI_TIME_TO_WIN);
        uriMatcher.addURI(AUTHORITY, COUNT_HEARTBEATS, URI_COUNT_HEARTBEATS);
        uriMatcher.addURI(AUTHORITY, UNLOCK_TODAY, URI_COUNT_UNLOCK_TODAY);
        uriMatcher.addURI(AUTHORITY, UNLOCK_WEEK, URI_COUNT_UNLOCK_WEEK);
        uriMatcher.addURI(AUTHORITY, UNLOCK_ALL_TIME, URI_COUNT_UNLOCK_ALL_TIME);
        uriMatcher.addURI(AUTHORITY, STATISTIC_TIME_TODAY, URI_STATISTIC_TIME_TODAY);
        uriMatcher.addURI(AUTHORITY, STATISTIC_TIME_WEEK, URI_STATISTIC_TIME_WEEK);
        uriMatcher.addURI(AUTHORITY, STATISTIC_TIME_ALL_TIME, URI_STATISTIC_TIME_ALL_TIME);
        uriMatcher.addURI(AUTHORITY, DAY_OF_WEEK, URI_DAY_OF_WEEK);
    }

    private SharedPreferences m_SharedPreferences;

    @Override
    public boolean onCreate() {
        m_SharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        return m_SharedPreferences!=null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Object o = null;
        switch (uriMatcher.match(uri)){
            case URI_SHOW_TERMS:
                o = m_SharedPreferences.getBoolean(KEY_BOOLEAN_IS_SHOW_TERMS, true)?1:0;
                break;
            case URI_USER_NAME:
                o = m_SharedPreferences.getString(KEY_STRING_NAME, "who are you?");
                break;
            case URI_BIRTHDAY_YEAR:
                o = m_SharedPreferences.getInt(KEY_INT_YEAR, 1970);
                break;
            case URI_BIRTHDAY_MONTH:
                o = m_SharedPreferences.getInt(KEY_INT_MONTH, 0);
                break;
            case URI_BIRTHDAY_DAY:
                o = m_SharedPreferences.getInt(KEY_INT_DAY, 1);
                break;
            case URI_TIME_TO_WIN:
                o = m_SharedPreferences.getLong(KEY_LONG_TIME, 10800);
                break;
            case URI_COUNT_HEARTBEATS:
                o = m_SharedPreferences.getLong(KEY_LONG_HEARTBEATS,  /*2177280*/2137280);
                break;
            case URI_COUNT_UNLOCK_TODAY:
                o = m_SharedPreferences.getInt(KEY_UNLOCK_TODAY, 0);
                break;
            case URI_COUNT_UNLOCK_WEEK:
                o = m_SharedPreferences.getString(KEY_UNLOCK_WEEK, "0,0,0,0,0,0,0");
                break;
            case URI_COUNT_UNLOCK_ALL_TIME:
                o = m_SharedPreferences.getInt(KEY_UNLOCK_ALL_TIME, 0);
                break;
            case URI_STATISTIC_TIME_TODAY:
                o = m_SharedPreferences.getLong(KEY_STATISTIC_TIME_TODAY, 0);
                break;
            case URI_STATISTIC_TIME_WEEK:
                o = m_SharedPreferences.getString(KEY_STATISTIC_TIME_WEEK, "0,0,0,0,0,0,0");
                break;
            case URI_STATISTIC_TIME_ALL_TIME:
                o = m_SharedPreferences.getLong(KEY_STATISTIC_TIME_ALL_TIME, 0);
                break;
            case URI_DAY_OF_WEEK:
                o = m_SharedPreferences.getInt(KEY_DAY_OF_WEEK, 0);
                break;
        }

        MatrixCursor cursor = new MatrixCursor(new String[]{KEY_DATA}, 1);
        Object[] arrValue = {o};
        cursor.addRow(arrValue);
        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SharedPreferences.Editor editor = m_SharedPreferences.edit();
        switch (uriMatcher.match(uri)){
            case URI_SHOW_TERMS:
                boolean bShowTerms = values.getAsBoolean(KEY_DATA);
                editor.putBoolean(KEY_BOOLEAN_IS_SHOW_TERMS, bShowTerms);
                break;
            case URI_USER_NAME:
                String sName = values.getAsString(KEY_DATA);
                editor.putString(KEY_STRING_NAME, sName);
                break;
            case URI_BIRTHDAY_YEAR:
                int nYear = values.getAsInteger(KEY_DATA);
                editor.putInt(KEY_INT_YEAR, nYear);
                break;
            case URI_BIRTHDAY_MONTH:
                int nMonth = values.getAsInteger(KEY_DATA);
                editor.putInt(KEY_INT_MONTH, nMonth);
                break;
            case URI_BIRTHDAY_DAY:
                int nDay = values.getAsInteger(KEY_DATA);
                editor.putInt(KEY_INT_DAY, nDay);
                break;
            case URI_TIME_TO_WIN:
                long nTime = values.getAsLong(KEY_DATA);
                editor.putLong(KEY_LONG_TIME, nTime);
                break;
            case URI_COUNT_HEARTBEATS:
                long nHeartbeats = values.getAsLong(KEY_DATA);
                editor.putLong(KEY_LONG_HEARTBEATS, nHeartbeats);
                break;
            case URI_COUNT_UNLOCK_TODAY:
                int nCountUnDay = values.getAsInteger(KEY_DATA);
                editor.putInt(KEY_UNLOCK_TODAY, nCountUnDay);
                break;
            case URI_COUNT_UNLOCK_WEEK:
                String sCountUnWeek = values.getAsString(KEY_DATA);
                editor.putString(KEY_UNLOCK_WEEK, sCountUnWeek);
                break;
            case URI_COUNT_UNLOCK_ALL_TIME:
                int nCountUnAllTime = values.getAsInteger(KEY_DATA);
                editor.putInt(KEY_UNLOCK_ALL_TIME, nCountUnAllTime);
                break;
            case URI_STATISTIC_TIME_TODAY:
                long nStatTimeToday = values.getAsLong(KEY_DATA);
                editor.putLong(KEY_STATISTIC_TIME_TODAY, nStatTimeToday);
                break;
            case URI_STATISTIC_TIME_WEEK:
                String sStatTimeWeek = values.getAsString(KEY_DATA);
                editor.putString(KEY_STATISTIC_TIME_WEEK, sStatTimeWeek);
                break;
            case URI_STATISTIC_TIME_ALL_TIME:
                long nStatTimeAllTime = values.getAsLong(KEY_DATA);
                editor.putLong(KEY_STATISTIC_TIME_ALL_TIME, nStatTimeAllTime);
                break;
            case URI_DAY_OF_WEEK:
                int nDayOfWeek = values.getAsInteger(KEY_DATA);
                editor.putInt(KEY_DAY_OF_WEEK, nDayOfWeek);
                break;
        }
        editor.commit();
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }
}
