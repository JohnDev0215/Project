package com.google.android.projection.protocol;

/* JADX INFO: loaded from: classes.dex */
interface NavigationStatusCallbacks extends NativeCallback {
    void navigationCurrentPositionCallback(byte[] bArr);

    void navigationNextTurnCallback(String str, int i, int i2, byte[] bArr, int i3, int i4);

    void navigationNextTurnDistanceCallback(int i, int i2, int i3, int i4);

    void navigationStateCallback(byte[] bArr);

    void navigationStatusCallback(int i);
}
