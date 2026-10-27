package android.content.res.loader;

import android.content.res.AssetFileDescriptor;

/* JADX INFO: loaded from: classes.dex */
public interface AssetsProvider {
    default AssetFileDescriptor loadAssetFd(String str, int i) {
        return null;
    }
}
