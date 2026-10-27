package com.google.android.projection.protocol;

/* JADX INFO: loaded from: classes.dex */
interface MediaPlaybackStatusCallbacks extends NativeCallback {
    void mediaPlaybackMetadataCallback(String str, String str2, String str3, byte[] bArr, String str4, int i, int i2);

    void mediaPlaybackStatusCallback(int i, String str, int i2, boolean z, boolean z2, boolean z3);
}
