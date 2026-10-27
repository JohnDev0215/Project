package defpackage;

import J.N;
import org.chromium.content.browser.BrowserStartupControllerImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Ly implements Runnable {
    public final /* synthetic */ boolean s;
    public final /* synthetic */ Runnable t;
    public final /* synthetic */ BrowserStartupControllerImpl u;

    public Ly(BrowserStartupControllerImpl browserStartupControllerImpl, boolean z, Runnable runnable) {
        this.u = browserStartupControllerImpl;
        this.s = z;
        this.t = runnable;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (!this.u.d) {
            AbstractC0745ez.a();
            N.MwoPtAzD(this.s);
            this.u.d = true;
        }
        Runnable runnable = this.t;
        if (runnable != null) {
            runnable.run();
        }
    }
}
