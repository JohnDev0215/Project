package org.chromium.base;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import java.io.IOException;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("base::android")
public class ApkAssets {
    private static final String LOGTAG = "ApkAssets";

    public static long[] open(Context context, String str) {
        AssetFileDescriptor assetFileDescriptorOpenNonAssetFd = null;
        try {
            try {
                assetFileDescriptorOpenNonAssetFd = context.getAssets().openNonAssetFd(str);
                long[] jArr = {assetFileDescriptorOpenNonAssetFd.getParcelFileDescriptor().detachFd(), assetFileDescriptorOpenNonAssetFd.getStartOffset(), assetFileDescriptorOpenNonAssetFd.getLength()};
                if (assetFileDescriptorOpenNonAssetFd != null) {
                    try {
                        assetFileDescriptorOpenNonAssetFd.close();
                    } catch (IOException e) {
                        android.util.Log.e(LOGTAG, "Unable to close AssetFileDescriptor", e);
                    }
                }
                return jArr;
            } catch (Throwable th) {
                if (assetFileDescriptorOpenNonAssetFd != null) {
                    try {
                        assetFileDescriptorOpenNonAssetFd.close();
                    } catch (IOException e2) {
                        android.util.Log.e(LOGTAG, "Unable to close AssetFileDescriptor", e2);
                    }
                }
                throw th;
            }
        } catch (IOException e3) {
            if (!e3.getMessage().equals("") && !e3.getMessage().equals(str)) {
                android.util.Log.e(LOGTAG, "Error while loading asset " + str + ": " + e3);
            }
            long[] jArr2 = {-1, -1, -1};
            if (assetFileDescriptorOpenNonAssetFd != null) {
                try {
                    assetFileDescriptorOpenNonAssetFd.close();
                } catch (IOException e4) {
                    android.util.Log.e(LOGTAG, "Unable to close AssetFileDescriptor", e4);
                }
            }
            return jArr2;
        }
    }
}
