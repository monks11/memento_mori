package com.monks.memento_mori.tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.monks.memento_mori.providers.AppSettingsContentProvider;

import java.util.StringTokenizer;

/**
 * Created by monks on 11.04.2017.
 */

public class AppSettings {

    Context context;

    public AppSettings(Context context){
        this.context = context;
    }

    public boolean ifFirstRun(){
        boolean bResult = false;
        Cursor cursor = context.getContentResolver().query(AppSettingsContentProvider.SHOW_TERMS_URI, null,null,null,null);
        if (cursor!=null){
            if(cursor.moveToFirst()){
                bResult = cursor.getInt(0)==1;
            }
            cursor.close();
        }
        return bResult;
    }
    public String getName(){
        String nResult = "who are you?";
        Cursor cursor = context.getContentResolver().query(AppSettingsContentProvider.USER_NAME_URI, null,null,null,null);
        if (cursor!=null){
            if(cursor.moveToFirst()){
                nResult = cursor.getString(0);
            }
            cursor.close();
        }
        return nResult;
    }

    public int getYear(){
        int nResult = 1970;
        Cursor cursor = context.getContentResolver().query(AppSettingsContentProvider.BIRTHDAY_YEAR_URI, null,null,null,null);
        if (cursor!=null){
            if(cursor.moveToFirst()){
                nResult = cursor.getInt(0);
            }
            cursor.close();
        }
        return nResult;
    }

    public int getMonth(){
        int nResult = 0;
        Cursor cursor = context.getContentResolver().query(AppSettingsContentProvider.BIRTHDAY_MONTH_URI, null,null,null,null);
        if (cursor!=null){
            if(cursor.moveToFirst()){
                nResult = cursor.getInt(0);
            }
            cursor.close();
        }
        return nResult;
    }

    public int getDay(){
        int nResult = 1;
        Cursor cursor = context.getContentResolver().query(AppSettingsContentProvider.BIRTHDAY_DAY_URI, null,null,null,null);
        if (cursor!=null){
            if(cursor.moveToFirst()){
                nResult = cursor.getInt(0);
            }
            cursor.close();
        }
        return nResult;
    }

    public long getTime(){
        long nResult = 10800;
        Cursor cursor = context.getContentResolver().query(AppSettingsContentProvider.TIME_TO_WIN_URI, null,null,null,null);
        if (cursor!=null){
            if(cursor.moveToFirst()){
                nResult = cursor.getLong(0);
            }
            cursor.close();
        }
        return nResult;
    }

    public long getHeartbeats(){
        long nResult =  /*2177280*/2137280;
        Cursor cursor = context.getContentResolver().query(AppSettingsContentProvider.COUNT_HEARTBEATS_URI, null,null,null,null);
        if (cursor!=null){
            if(cursor.moveToFirst()){
                nResult = cursor.getLong(0);
            }
            cursor.close();
        }
        return nResult;
    }

    public long getStatisticTimeToday(){
        long nResult = 0;
        Cursor cursor = context.getContentResolver().query(AppSettingsContentProvider.STATISTIC_TIME_TODAY_URI, null,null,null,null);
        if (cursor!=null){
            if(cursor.moveToFirst()){
                nResult = cursor.getLong(0);
            }
            cursor.close();
        }
        return nResult;
    }

    public long getStatisticTimeWeek(){
        long nResult = 0;
        String nResultString = "";
        Cursor cursor = context.getContentResolver().query(AppSettingsContentProvider.STATISTIC_TIME_WEEK_URI, null,null,null,null);
        if (cursor!=null){
            if(cursor.moveToFirst()){
                nResultString = cursor.getString(0);
            }
            cursor.close();
        }
        StringTokenizer st = new StringTokenizer(nResultString, ",");
        for (int i = 0; i < 7; i++) {
            nResult += Long.parseLong(st.nextToken());
        }
        return nResult;
    }

    private String getStatisticTimeWeekStr(){
        String nResultString = "0,0,0,0,0,0,0";
        Cursor cursor = context.getContentResolver().query(AppSettingsContentProvider.STATISTIC_TIME_WEEK_URI, null,null,null,null);
        if (cursor!=null){
            if(cursor.moveToFirst()){
                nResultString = cursor.getString(0);
            }
            cursor.close();
        }
        return nResultString;
    }

    public long getStatisticTimeAllTime(){
        long nResult = 0;
        Cursor cursor = context.getContentResolver().query(AppSettingsContentProvider.STATISTIC_TIME_ALL_TIME_URI, null,null,null,null);
        if (cursor!=null){
            if(cursor.moveToFirst()){
                nResult = cursor.getLong(0);
            }
            cursor.close();
        }
        return nResult;
    }

    public int getCountUnlockToday(){
        int nResult = 0;
        Cursor cursor = context.getContentResolver().query(AppSettingsContentProvider.COUNT_UNLOCK_TODAY_URI, null,null,null,null);
        if (cursor!=null){
            if(cursor.moveToFirst()){
                nResult = cursor.getInt(0);
            }
            cursor.close();
        }
        return nResult;
    }

    public int getCountUnlockWeek(){
        int nResult = 0;
        String nResultString = "";
        Cursor cursor = context.getContentResolver().query(AppSettingsContentProvider.COUNT_UNLOCK_WEEK_URI, null,null,null,null);
        if (cursor!=null){
            if(cursor.moveToFirst()){
                nResultString = cursor.getString(0);
            }
            cursor.close();
        }
        StringTokenizer st = new StringTokenizer(nResultString, ",");
        for (int i = 0; i < 7; i++) {
            nResult += Integer.parseInt(st.nextToken());
        }
        return nResult;
    }

    private String getCountUnlockWeekStr(){
        String nResultString = "0,0,0,0,0,0,0";
        Cursor cursor = context.getContentResolver().query(AppSettingsContentProvider.COUNT_UNLOCK_WEEK_URI, null,null,null,null);
        if (cursor!=null){
            if(cursor.moveToFirst()){
                nResultString = cursor.getString(0);
            }
            cursor.close();
        }
        return nResultString;
    }

    public int getCountUnlockAllTime(){
        int nResult = 0;
        Cursor cursor = context.getContentResolver().query(AppSettingsContentProvider.COUNT_UNLOCK_ALL_TIME_URI, null,null,null,null);
        if (cursor!=null){
            if(cursor.moveToFirst()){
                nResult = cursor.getInt(0);
            }
            cursor.close();
        }
        return nResult;
    }

    public int getDayOfWeek (){
        int nResult = 0;
        Cursor cursor = context.getContentResolver().query(AppSettingsContentProvider.DAY_OF_WEEK_URI, null,null,null,null);
        if (cursor!=null){
            if(cursor.moveToFirst()){
                nResult = cursor.getInt(0);
            }
            cursor.close();
        }
        return nResult;
    }

    public  void setShowTerms(boolean blsShow){
        ContentValues values = new ContentValues();
        values.put(AppSettingsContentProvider.KEY_DATA, blsShow);
        context.getContentResolver().insert(AppSettingsContentProvider.SHOW_TERMS_URI, values);
    }

    public  void setName(String strName){
        ContentValues values = new ContentValues();
        values.put(AppSettingsContentProvider.KEY_DATA, strName);
        context.getContentResolver().insert(AppSettingsContentProvider.USER_NAME_URI, values);
    }

    public  void setBirthday(int year, int month, int day){
        ContentValues values = new ContentValues();
        values.put(AppSettingsContentProvider.KEY_DATA, year);
        context.getContentResolver().insert(AppSettingsContentProvider.BIRTHDAY_YEAR_URI, values);
        values.put(AppSettingsContentProvider.KEY_DATA, month);
        context.getContentResolver().insert(AppSettingsContentProvider.BIRTHDAY_MONTH_URI, values);
        values.put(AppSettingsContentProvider.KEY_DATA, day);
        context.getContentResolver().insert(AppSettingsContentProvider.BIRTHDAY_DAY_URI, values);
    }

    public  void setTime(long time){;
        ContentValues values = new ContentValues();
        values.put(AppSettingsContentProvider.KEY_DATA, time);
        context.getContentResolver().insert(AppSettingsContentProvider.TIME_TO_WIN_URI, values);
    }

    public void setHeartbeats(long heartbeats) {
        ContentValues values = new ContentValues();
        values.put(AppSettingsContentProvider.KEY_DATA, heartbeats);
        context.getContentResolver().insert(AppSettingsContentProvider.COUNT_HEARTBEATS_URI, values);
    }

    public void setStatisticTimeToday(long time){
        ContentValues values = new ContentValues();
        values.put(AppSettingsContentProvider.KEY_DATA, time);
        context.getContentResolver().insert(AppSettingsContentProvider.STATISTIC_TIME_TODAY_URI, values);
    }

    public void setStatisticTimeWeek(long time){
        int dayOfWeek = getDayOfWeek();
        long[] nArrWeek = new long[7];
        String strWeek = getStatisticTimeWeekStr();
        StringTokenizer st = new StringTokenizer(strWeek, ",");
        for (int i = 0; i < 7; i++) {
            nArrWeek[i] = Long.parseLong(st.nextToken());
        }
        nArrWeek[dayOfWeek] += time;
        StringBuilder strStat = new StringBuilder();
        for (int i = 0; i < nArrWeek.length; i++) {
            strStat.append(nArrWeek[i]).append(",");
        }
        ContentValues values = new ContentValues();
        values.put(AppSettingsContentProvider.KEY_DATA, strStat.toString());
        context.getContentResolver().insert(AppSettingsContentProvider.STATISTIC_TIME_WEEK_URI , values);
    }

    public void setStatisticTimeAllTime(long time){
        ContentValues values = new ContentValues();
        values.put(AppSettingsContentProvider.KEY_DATA, time);
        context.getContentResolver().insert(AppSettingsContentProvider.STATISTIC_TIME_ALL_TIME_URI, values);
    }

    public void setCountUnlockToday(int count){
        ContentValues values = new ContentValues();
        values.put(AppSettingsContentProvider.KEY_DATA, count);
        context.getContentResolver().insert(AppSettingsContentProvider.COUNT_UNLOCK_TODAY_URI, values);
    }

    public void setCountUnlockWeek(int count){
        int dayOfWeek = getDayOfWeek();
        int[] nArrWeek = new int[7];
        String strWeek = getCountUnlockWeekStr();
        StringTokenizer st = new StringTokenizer(strWeek, ",");
        for (int i = 0; i < 7; i++) {
            nArrWeek[i] = Integer.parseInt(st.nextToken());
        }
        nArrWeek[dayOfWeek] += count;
        StringBuilder strStat = new StringBuilder();
        for (int i = 0; i < nArrWeek.length; i++) {
            strStat.append(nArrWeek[i]).append(",");
        }
        ContentValues values = new ContentValues();
        values.put(AppSettingsContentProvider.KEY_DATA, strStat.toString());
        context.getContentResolver().insert(AppSettingsContentProvider.COUNT_UNLOCK_WEEK_URI , values);
    }

    public void setCountUnlockAllTime(int count){
        ContentValues values = new ContentValues();
        values.put(AppSettingsContentProvider.KEY_DATA, count);
        context.getContentResolver().insert(AppSettingsContentProvider.COUNT_UNLOCK_ALL_TIME_URI, values);
    }

    public void setDayOfWeek (int day){
        ContentValues values = new ContentValues();
        values.put(AppSettingsContentProvider.KEY_DATA, day);
        context.getContentResolver().insert(AppSettingsContentProvider.DAY_OF_WEEK_URI, values);
    }
}
