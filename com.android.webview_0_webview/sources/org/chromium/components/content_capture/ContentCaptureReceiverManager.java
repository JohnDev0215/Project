package org.chromium.components.content_capture;

import defpackage.C0632cx;
import defpackage.Ts;
import defpackage.Vw;
import defpackage.Yw;
import java.util.Arrays;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ContentCaptureReceiverManager {
    public static Boolean b;
    public Vw a;

    public ContentCaptureReceiverManager() {
        if (b == null) {
            b = Boolean.valueOf(Yw.a());
        }
    }

    private void didCaptureContent(Object[] objArr, ContentCaptureData contentCaptureData) {
        Vw vw = this.a;
        if (vw != null) {
            vw.a(a(objArr), contentCaptureData);
        }
        if (b.booleanValue()) {
            Ts.d("ContentCapture", "Captured Content: %s", contentCaptureData);
        }
    }

    private void didRemoveContent(Object[] objArr, long[] jArr) {
        C0632cx c0632cxA = a(objArr);
        Vw vw = this.a;
        if (vw != null) {
            vw.b(c0632cxA, jArr);
        }
        if (b.booleanValue()) {
            Ts.d("ContentCapture", "Removed Content: %s", c0632cxA.get(0) + " " + Arrays.toString(jArr));
        }
    }

    private void didRemoveSession(Object[] objArr) {
        C0632cx c0632cxA = a(objArr);
        Vw vw = this.a;
        if (vw != null) {
            vw.d(c0632cxA);
        }
        if (b.booleanValue()) {
            Ts.d("ContentCapture", "Removed Session: %s", c0632cxA.get(0));
        }
    }

    private void didUpdateContent(Object[] objArr, ContentCaptureData contentCaptureData) {
        Vw vw = this.a;
        if (vw != null) {
            vw.c(a(objArr), contentCaptureData);
        }
        if (b.booleanValue()) {
            Ts.d("ContentCapture", "Updated Content: %s", contentCaptureData);
        }
    }

    public final C0632cx a(Object[] objArr) {
        C0632cx c0632cx = new C0632cx(objArr.length);
        for (Object obj : objArr) {
            c0632cx.add((ContentCaptureData) obj);
        }
        return c0632cx;
    }
}
