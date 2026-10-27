package defpackage;

import J.N;
import org.chromium.content.browser.TtsPlatformImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class Lz implements Runnable {
    public final TtsPlatformImpl s;
    public final String t;

    public Lz(TtsPlatformImpl ttsPlatformImpl, String str) {
        this.s = ttsPlatformImpl;
        this.t = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        TtsPlatformImpl ttsPlatformImpl = this.s;
        String str = this.t;
        long j = ttsPlatformImpl.a;
        if (j != 0) {
            N.M2$X0reE(j, ttsPlatformImpl, Integer.parseInt(str));
        }
    }
}
