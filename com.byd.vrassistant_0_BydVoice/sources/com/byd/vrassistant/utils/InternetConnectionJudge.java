package com.byd.vrassistant.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import com.byd.vrassistant.BydVoiceApplication;

/* JADX INFO: loaded from: classes2.dex */
public class InternetConnectionJudge {
    private static final String TAG = "NEU_ChectNet";
    private static InternetConnectionJudge internetConnectionJudge;
    private Context mContext = BydVoiceApplication.getContext();

    private InternetConnectionJudge() {
    }

    public static synchronized InternetConnectionJudge getInstance() {
        if (internetConnectionJudge == null) {
            internetConnectionJudge = new InternetConnectionJudge();
        }
        return internetConnectionJudge;
    }

    public boolean isThereInternetConnection() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            if (activeNetworkInfo.getType() == 0) {
                RLog.i(TAG, "isThereInternetConnection TYPE_MOBILE isConnected=true");
                return true;
            }
            if (activeNetworkInfo.getType() == 1) {
                RLog.i(TAG, "isThereInternetConnection TYPE_WIFI isConnected ");
                return true;
            }
        }
        RLog.i(TAG, "isThereInternetConnection isConnected=false");
        return false;
    }

    public static boolean checkNetState(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        for (Network network : connectivityManager.getAllNetworks()) {
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(network);
            RLog.i(TAG, networkInfo.getTypeName() + " connect is " + networkInfo.isConnected());
            if (networkInfo.isConnected()) {
                return true;
            }
        }
        return false;
    }
}
