package com.byd.data.common.i.j;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* JADX INFO: compiled from: NetWorkUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class b {
    private static NetworkInfo a() {
        return ((ConnectivityManager) com.byd.data.common.g.a.i().d().getSystemService("connectivity")).getActiveNetworkInfo();
    }

    public static boolean b() {
        NetworkInfo networkInfoA = a();
        return networkInfoA != null && networkInfoA.isConnected();
    }
}
