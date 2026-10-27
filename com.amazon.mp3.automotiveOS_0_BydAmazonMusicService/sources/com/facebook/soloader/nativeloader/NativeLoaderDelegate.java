package com.facebook.soloader.nativeloader;

/* JADX INFO: loaded from: classes6.dex */
public interface NativeLoaderDelegate {
    int getSoSourcesVersion();

    boolean loadLibrary(String str, int i);
}
