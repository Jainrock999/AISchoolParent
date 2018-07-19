package com.example.sbs.aischoolparentapp.savedata;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.sbs.aischoolparentapp.Config.ConstValue;


/**
 */
public class SaveData {

    public static String getParentName(Context ctx) {
        String parentname = null;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        parentname = preferences.getString("Parent_Name", "");
        return parentname;
    }

    public static void setParentName(Context cx, String parentname) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(cx);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Parent_Name", parentname);
        editor.commit();
    }

    public static void logout(Context cx) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(cx);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Parent_Name", "");
        editor.commit();
        editor.clear();
    }

    public static String getchildid(Context ctx) {
        String child_id = null;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        child_id = preferences.getString("Child_id", "");
        return child_id;
    }

    public static void setchildid(Context cx, String child_id) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(cx);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Child_id", child_id);
        editor.commit();
    }

    public static String getStandardId(Context ctx) {
        String standardid = null;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        standardid = preferences.getString("Standard_id", "");
        return standardid;
    }

    public static void setStandardId(Context cx, String standardid) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(cx);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Standard_id", standardid);
        editor.commit();
    }

    public static String getReqId(Context ctx) {
        String reg = null;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        reg = preferences.getString("Reg", "");
        return reg;
    }

    public static void setreqId(Context cx, String reg) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(cx);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Reg", reg);
        editor.commit();
    }

    public static String getSchoolid(Context ctx) {
        String schoolid = null;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        schoolid = preferences.getString("School_Id", "");
        return schoolid;
    }

    public static void setSchoolId(Context cx, String schoolid) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(cx);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("School_Id", schoolid);
        editor.commit();
    }

    public static String getLoginStatus(Context cx) {
        String status = null;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(cx);
        status = preferences.getString("STATUS", "");
        return status;
    }

    public static void setLoginStatus(Context cx, String status) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(cx);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("STATUS", status);
        editor.commit();

    }

}
