package com.google.android.projection.protocol;

/* JADX INFO: loaded from: classes.dex */
interface BluetoothCallbacks extends NativeCallback {
    void onAuthenticationResult(int i);

    void onPairingRequest(String str, int i);
}
