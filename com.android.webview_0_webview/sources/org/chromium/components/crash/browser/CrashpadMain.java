package org.chromium.components.crash.browser;

import J.N;
import defpackage.AbstractC1679vt;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class CrashpadMain {
    public static void main(String[] strArr) {
        try {
            for (String str : AbstractC1679vt.a) {
                System.loadLibrary(str);
            }
            N.MT6tcXz3(strArr);
        } catch (UnsatisfiedLinkError e) {
            throw new RuntimeException(e);
        }
    }
}
