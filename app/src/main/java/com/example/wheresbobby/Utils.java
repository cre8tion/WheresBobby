package com.example.wheresbobby;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

public class Utils {

    static final String UTILS_TAG = "UtilsTag";


    /**
     * Builds the URL to call API from
     * @return URL object with the website string
     */
    public static URL buildURL(){

        String scheme = "https";
        final String authority = "asia-east2-practice-48fa2.cloudfunctions.net";
        final String back = "profanity-check";
        Uri.Builder builder;
        URL url = null;

        builder = new Uri.Builder();
        builder.scheme(scheme)
                .authority(authority)
                .appendPath(back);

        Uri uri = builder.build();

        try{
            url = new URL(uri.toString());
            Log.i(UTILS_TAG,"URL OK: " + url.toString());
        }catch(MalformedURLException e) {
            Log.i(UTILS_TAG, "malformed URL: " + url.toString());
        }

        return url;

    }

    /**
     * Checks if Network Connection is present
     * @param context Current Activity
     * @return True/False
     */
    static boolean isNetworkAvailable(Context context) {

        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        boolean haveNetwork = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        Log.i(UTILS_TAG, "Active Network: " + haveNetwork);
        return haveNetwork;
    }

    /**
     * Generates a Toast to remind the user to turn on their network connection for the application
     * to function
     * @param context Current Activity
     */
    static void remindOnline(Context context){
        if(!isNetworkAvailable(context)){
            Toast.makeText(context, "Internet Connection is required for the application to work!", Toast.LENGTH_LONG).show();
        }
    }

}
