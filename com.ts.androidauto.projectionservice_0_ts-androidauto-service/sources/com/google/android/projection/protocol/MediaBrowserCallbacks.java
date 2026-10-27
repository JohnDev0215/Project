package com.google.android.projection.protocol;

/* JADX INFO: loaded from: classes.dex */
interface MediaBrowserCallbacks extends NativeCallback {
    int listNodeCallback(String str, String str2, byte[] bArr, int i, int i2, int i3, String[] strArr, String[] strArr2, String[] strArr3, String[] strArr4);

    int rootNodeCallback(String str, String[] strArr, String[] strArr2, byte[][] bArr);

    int songNodeCallback(String str, String str2, String str3, String str4, byte[] bArr, int i);

    int sourceNodeCallback(String str, String str2, byte[] bArr, int i, int i2, String[] strArr, String[] strArr2, byte[][] bArr2, int[] iArr);
}
