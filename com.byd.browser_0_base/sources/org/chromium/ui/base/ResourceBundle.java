package org.chromium.ui.base;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import defpackage.C0175b;
import defpackage.fa1;
import defpackage.gq2;
import defpackage.xz;
import java.io.IOException;
import java.util.Arrays;
import org.chromium.base.LocaleUtils;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public final class ResourceBundle {
    public static String[] a;

    @CalledByNative
    public static String getLocalePakResourcePath(String str, boolean z, boolean z2) {
        String strG;
        String strC;
        String[] strArr = a;
        if (strArr == null || Arrays.binarySearch(strArr, str) < 0) {
            return null;
        }
        if (!z) {
            strG = "assets/stored-locales/";
        } else if (str.equals("en-US")) {
            strG = "assets/fallback-locales/";
        } else {
            strC = LocaleUtils.c(str);
            strC.getClass();
            strC.hashCode();
            switch (strC) {
                case "he":
                    strC = "iw";
                    break;
                case "id":
                    strC = "in";
                    break;
                case "yi":
                    strC = "ji";
                    break;
                case "fil":
                    strC = "tl";
                    break;
            }
            strG = gq2.g("assets/locales#lang_", strC, "/");
        }
        String strK = C0175b.k(strG, str, ".pak");
        AssetManager assets = xz.a.getAssets();
        try {
            AssetFileDescriptor assetFileDescriptorOpenNonAssetFd = assets.openNonAssetFd(strK);
            if (assetFileDescriptorOpenNonAssetFd != null) {
                assetFileDescriptorOpenNonAssetFd.close();
            }
            return strK;
        } catch (IOException e) {
            String strG2 = gq2.g("assets/locales/", str, ".pak");
            try {
                AssetFileDescriptor assetFileDescriptorOpenNonAssetFd2 = assets.openNonAssetFd(strG2);
                if (assetFileDescriptorOpenNonAssetFd2 != null) {
                    assetFileDescriptorOpenNonAssetFd2.close();
                }
                return strG2;
            } catch (IOException unused) {
                if (z2) {
                    fa1.c("ResourceBundle", "path=%s", strK, e);
                }
                return null;
            }
        }
    }

    @CalledByNative
    public static void setNoAvailableLocalePaks() {
        a = new String[0];
    }
}
