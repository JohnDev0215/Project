package org.chromium.components.content_capture;

import android.util.Log;
import android.view.contentcapture.ContentCaptureManager;
import defpackage.iv;
import defpackage.v72;

/* JADX INFO: loaded from: classes2.dex */
public class PlatformContentCaptureController {
    public static PlatformContentCaptureController d;
    public boolean a;
    public v72 b;
    public final ContentCaptureManager c;

    /* JADX WARN: Removed duplicated region for block: B:18:0x0064  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public PlatformContentCaptureController(android.content.Context r5) {
        /*
            Method dump skipped, instruction units count: 207
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.content_capture.PlatformContentCaptureController.<init>(android.content.Context):void");
    }

    public static void a(String str) {
        if (iv.e().g("dump-captured-content-to-logcat-for-testing")) {
            Log.i("cr_ContentCapture", str);
        }
    }
}
