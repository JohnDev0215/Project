package org.chromium.base;

import android.content.res.AssetFileDescriptor;
import android.util.Log;
import defpackage.Is;
import java.io.IOException;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ApkAssets {
    public static long[] open(String str) {
        AssetFileDescriptor assetFileDescriptorOpenNonAssetFd = null;
        try {
            try {
                assetFileDescriptorOpenNonAssetFd = Is.a.getAssets().openNonAssetFd(str);
                long[] jArr = {assetFileDescriptorOpenNonAssetFd.getParcelFileDescriptor().detachFd(), assetFileDescriptorOpenNonAssetFd.getStartOffset(), assetFileDescriptorOpenNonAssetFd.getLength()};
                try {
                    assetFileDescriptorOpenNonAssetFd.close();
                } catch (IOException e) {
                    Log.e("ApkAssets", "Unable to close AssetFileDescriptor", e);
                }
                return jArr;
            } catch (Throwable th) {
                if (assetFileDescriptorOpenNonAssetFd != null) {
                    try {
                        assetFileDescriptorOpenNonAssetFd.close();
                    } catch (IOException e2) {
                        Log.e("ApkAssets", "Unable to close AssetFileDescriptor", e2);
                    }
                }
                throw th;
            }
        } catch (IOException e3) {
            if (!e3.getMessage().equals("") && !e3.getMessage().equals(str)) {
                Log.e("ApkAssets", "Error while loading asset " + str + ": " + e3);
            }
            long[] jArr2 = {-1, -1, -1};
            if (assetFileDescriptorOpenNonAssetFd != null) {
                try {
                    assetFileDescriptorOpenNonAssetFd.close();
                } catch (IOException e4) {
                    Log.e("ApkAssets", "Unable to close AssetFileDescriptor", e4);
                }
            }
            return jArr2;
        }
    }
}
