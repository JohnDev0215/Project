package com.android.wifitrackerlib;

import android.net.LinkProperties;
import android.net.NetworkCapabilities;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public abstract class NetworkDetailsTracker extends BaseWifiTracker {
    @NonNull
    @AnyThread
    public abstract WifiEntry n();

    @WorkerThread
    protected void o(@Nullable LinkProperties linkProperties) {
        WifiEntry wifiEntryN = n();
        if (wifiEntryN.h() == 2) {
            wifiEntryN.N(linkProperties);
        }
    }

    @WorkerThread
    protected void p(@Nullable NetworkCapabilities networkCapabilities) {
        WifiEntry wifiEntryN = n();
        if (wifiEntryN.h() == 2) {
            wifiEntryN.O(networkCapabilities);
            wifiEntryN.J(this.p && this.r);
        }
    }
}
