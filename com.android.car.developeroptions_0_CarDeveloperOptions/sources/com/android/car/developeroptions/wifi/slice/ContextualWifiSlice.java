package com.android.car.developeroptions.wifi.slice;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class ContextualWifiSlice extends WifiSlice {
    static long sActiveUiSession = -1000;
    static boolean sPreviouslyDisplayed;

    public ContextualWifiSlice(Context context) {
        super(context);
    }
}
