package org.chromium.android_webview;

import J.N;
import java.io.File;
import java.io.IOException;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AwDebug {
    public static boolean dumpWithoutCrashing(File file) {
        try {
            return N.MmSf62mU(file.getCanonicalPath());
        } catch (IOException unused) {
            return false;
        }
    }
}
