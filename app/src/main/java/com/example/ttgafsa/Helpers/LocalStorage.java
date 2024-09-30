package com.example.ttgafsa.Helpers;

import android.content.Context;
import android.content.SharedPreferences;

public class LocalStorage {

    private static final String PREF_NAME = "UserPreferences";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";

    // Method to set the user's login state
    public static void setUserLoggedIn(Context context, boolean isLoggedIn) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
        editor.apply();
    }

    // Method to check if the user is logged in
    public static boolean isUserLoggedIn(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false); // Default to false if not found
    }

    // Method to log out the user
    public static void logOutUser(Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();
        editor.remove(KEY_IS_LOGGED_IN);
        editor.apply();
    }
}
