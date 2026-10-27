package org.chromium.android_webview;

import defpackage.C1574ty;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class AwVariationsSeedBridge {
    public static C1574ty a;
    public static boolean b;

    public static void clearSeed() {
        a = null;
    }

    public static String getCountry() {
        return a.b;
    }

    public static byte[] getData() {
        return a.e;
    }

    public static long getDate() {
        return a.c;
    }

    public static boolean getIsGzipCompressed() {
        return a.d;
    }

    public static String getSignature() {
        return a.a;
    }

    public static boolean haveSeed() {
        return a != null;
    }

    public static boolean isLoadedSeedFresh() {
        return b;
    }
}
