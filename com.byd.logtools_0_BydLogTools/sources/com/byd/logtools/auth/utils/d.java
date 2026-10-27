package com.byd.logtools.auth.utils;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.byd.logtools.application.BydLogApplication;

/* JADX INFO: compiled from: NetworkUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class d {
    private static NetworkInfo a() {
        return ((ConnectivityManager) BydLogApplication.d().getSystemService("connectivity")).getActiveNetworkInfo();
    }

    public static boolean b() {
        NetworkInfo networkInfoA = a();
        return networkInfoA != null && networkInfoA.isConnected();
    }
}
