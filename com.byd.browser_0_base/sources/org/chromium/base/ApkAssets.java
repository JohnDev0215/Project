package org.chromium.base;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.text.TextUtils;
import android.util.Log;
import defpackage.xz;
import java.io.IOException;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public class ApkAssets {
    @CalledByNative
    public static long[] open(String str, String str2) {
        AssetFileDescriptor assetFileDescriptorOpenNonAssetFd = null;
        try {
            try {
                Context contextA = xz.a;
                if (!TextUtils.isEmpty(str2) && BundleUtils.d(str2)) {
                    contextA = BundleUtils.a(contextA, str2);
                }
                assetFileDescriptorOpenNonAssetFd = contextA.getAssets().openNonAssetFd(str);
                long[] jArr = {assetFileDescriptorOpenNonAssetFd.getParcelFileDescriptor().detachFd(), assetFileDescriptorOpenNonAssetFd.getStartOffset(), assetFileDescriptorOpenNonAssetFd.getLength()};
                try {
                    assetFileDescriptorOpenNonAssetFd.close();
                } catch (IOException e) {
                    Log.e("ApkAssets", "Unable to close AssetFileDescriptor", e);
                }
                return jArr;
            } catch (IOException e2) {
                if (!e2.getMessage().equals("") && !e2.getMessage().equals(str)) {
                    Log.e("ApkAssets", "Error while loading asset " + str + ": " + e2);
                }
                long[] jArr2 = {-1, -1, -1};
                if (assetFileDescriptorOpenNonAssetFd != null) {
                    try {
                        assetFileDescriptorOpenNonAssetFd.close();
                    } catch (IOException e3) {
                        Log.e("ApkAssets", "Unable to close AssetFileDescriptor", e3);
                    }
                }
                return jArr2;
            }
        } catch (Throwable th) {
            if (assetFileDescriptorOpenNonAssetFd != null) {
                try {
                    assetFileDescriptorOpenNonAssetFd.close();
                } catch (IOException e4) {
                    Log.e("ApkAssets", "Unable to close AssetFileDescriptor", e4);
                }
            }
            throw th;
        }
    }
}
