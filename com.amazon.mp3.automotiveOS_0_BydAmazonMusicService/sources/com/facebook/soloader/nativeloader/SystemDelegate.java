package com.facebook.soloader.nativeloader;

/* JADX INFO: loaded from: classes6.dex */
public class SystemDelegate implements NativeLoaderDelegate {
    @Override // com.facebook.soloader.nativeloader.NativeLoaderDelegate
    public int getSoSourcesVersion() {
        return 0;
    }

    @Override // com.facebook.soloader.nativeloader.NativeLoaderDelegate
    public boolean loadLibrary(String str, int i) {
        System.loadLibrary(str);
        return true;
    }
}
