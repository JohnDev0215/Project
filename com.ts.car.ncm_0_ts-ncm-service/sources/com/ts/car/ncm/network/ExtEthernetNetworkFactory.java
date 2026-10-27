package com.ts.car.ncm.network;

import android.content.Context;
import android.net.NetworkCapabilities;
import android.os.Handler;

/* JADX INFO: loaded from: classes.dex */
public final class ExtEthernetNetworkFactory extends ExtNetworkFactory {
    public static final boolean DBG = true;
    private static final String TAG = ExtEthernetNetworkFactory.class.getSimpleName();

    public ExtEthernetNetworkFactory(Handler handler, Context context, NetworkCapabilities netcap) {
        super(handler, context, netcap, "Ethernet", 9);
        updateScore(70);
    }
}
