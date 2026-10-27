package defpackage;

import J.N;
import android.hardware.Camera;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class NH implements Camera.ErrorCallback {
    public final /* synthetic */ C0598cI a;

    public NH(C0598cI c0598cI, LH lh) {
        this.a = c0598cI;
    }

    @Override // android.hardware.Camera.ErrorCallback
    public void onError(int i, Camera camera) {
        C0598cI c0598cI = this.a;
        N.MhmwjISE(c0598cI.e, c0598cI, 68, Z7.a("Error id: ", i));
        synchronized (this.a.g) {
            if (this.a.h == 0) {
                return;
            }
            this.a.d(this.a.h);
            this.a.h = 0L;
        }
    }
}
