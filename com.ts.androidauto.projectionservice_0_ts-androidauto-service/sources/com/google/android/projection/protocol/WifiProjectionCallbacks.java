package com.google.android.projection.protocol;

import com.google.android.projection.protocol.CarWifi;

/* JADX INFO: loaded from: classes.dex */
interface WifiProjectionCallbacks extends NativeCallback {
    CarWifi.CarWifiCredentials onWifiCredentialsRequest();
}
