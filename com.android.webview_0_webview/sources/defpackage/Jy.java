package defpackage;

import org.chromium.content.browser.BrowserStartupControllerImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Jy implements Runnable {
    public final /* synthetic */ int s;
    public final /* synthetic */ BrowserStartupControllerImpl t;

    public Jy(BrowserStartupControllerImpl browserStartupControllerImpl, int i) {
        this.t = browserStartupControllerImpl;
        this.s = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.t.c(this.s);
    }
}
