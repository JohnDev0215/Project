package org.chromium.components.content_capture;

import J.N;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class ContentCaptureController {
    public static ContentCaptureController b;
    public long a = N.MM$06MFa(this);

    public void a(String[] strArr, boolean[] zArr) {
        N.MMJ$SEno(this.a, this, strArr, zArr);
    }

    public abstract boolean b();

    public abstract void pullWhitelist();
}
