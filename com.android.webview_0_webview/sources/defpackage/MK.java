package defpackage;

import android.net.Network;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class MK implements Runnable {
    public final /* synthetic */ Network s;
    public final /* synthetic */ OK t;

    public MK(OK ok, Network network) {
        this.t = ok;
        this.s = network;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.t.b.d.d(TK.e(this.s));
    }
}
