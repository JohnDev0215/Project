package defpackage;

import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Tx implements Runnable {
    public final /* synthetic */ Vx s;

    public Tx(Vx vx) {
        this.s = vx;
    }

    @Override // java.lang.Runnable
    public void run() {
        ThreadUtils.b();
        this.s.c.start();
    }
}
