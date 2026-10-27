package org.chromium.components.metrics;

import defpackage.Fs;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AndroidMetricsLogUploader {
    public static volatile Fs a;

    public static void uploadLog(byte[] bArr) {
        Fs fs = a;
        if (fs != null) {
            fs.a(bArr);
        }
    }
}
