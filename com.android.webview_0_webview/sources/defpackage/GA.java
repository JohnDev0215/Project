package defpackage;

import android.view.Surface;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class GA implements Runnable {
    public final /* synthetic */ Surface s;
    public final /* synthetic */ JA t;

    public GA(JA ja, Surface surface) {
        this.t = ja;
        this.s = surface;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.t.t.A(this.s);
    }
}
