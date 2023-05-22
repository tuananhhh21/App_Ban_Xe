package com.banxemay.online.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class ConstSaveData {
    public static String DATA_NAME_USER = "DATA_NAME_USER";
    public static String DATA_EMAIL_USER = "DATA_EMAIL_USER";
    public static String DATA_DIACHI_USER = "DATA_DIACHI_USER";
    public static String DATA_SDT_USER = "DATA_SDT_USER";

    public static void SaveDataTypeNumberByName(Context context, String NameData, int value){
        SharedPreferences.Editor editor =
                PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putInt(NameData, value);
        editor.apply();
    }

    public static int GetDataTypeNumberByName(Context context, String NameData, int ValueDefault){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        int value = sp.getInt(NameData, ValueDefault);
        return value;
    }

    public static void SaveDataTypeStringByName(Context context, String NameData, String value){
        SharedPreferences.Editor editor =
                PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(NameData, value);
        editor.apply();
    }

    public static String GetDataTypeStringByName(Context context, String NameData, String ValueDefault){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String value = sp.getString(NameData, ValueDefault);
        return value;
    }
}
