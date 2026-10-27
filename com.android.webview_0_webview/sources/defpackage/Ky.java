package defpackage;

import org.chromium.content.browser.BrowserStartupControllerImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Ky implements Runnable {
    public final /* synthetic */ DD s;
    public final /* synthetic */ BrowserStartupControllerImpl t;

    public Ky(BrowserStartupControllerImpl browserStartupControllerImpl, DD dd) {
        this.t = browserStartupControllerImpl;
        this.s = dd;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.t.g) {
            this.s.a();
        } else {
            this.s.b();
        }
    }
}
