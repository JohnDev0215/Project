package defpackage;

import android.util.Log;

/* JADX INFO: renamed from: ak, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0507ak extends AbstractC0117Gj {
    public final C0675dk d;
    public final C0046Ck c = new C0046Ck();
    public final Object e = new Object();
    public boolean f = true;

    public C0507ak(C0675dk c0675dk, AbstractC1176mk abstractC1176mk) {
        this.d = c0675dk;
    }

    @Override // defpackage.AbstractC0117Gj
    public final void a() {
        super.a();
        synchronized (this.e) {
            if (this.f) {
                this.d.d();
                this.f = false;
            }
        }
    }

    public final void finalize() throws Throwable {
        try {
            synchronized (this.e) {
                if (this.f) {
                    Log.w("FaceDetector", "FaceDetector was not released with FaceDetector.release()");
                    a();
                }
            }
        } finally {
            super.finalize();
        }
    }
}
