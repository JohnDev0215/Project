package defpackage;

import org.chromium.base.ThreadUtils;
import org.chromium.content.browser.BrowserStartupControllerImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Iy implements Runnable {
    public final /* synthetic */ boolean s;
    public final /* synthetic */ BrowserStartupControllerImpl t;

    public Iy(BrowserStartupControllerImpl browserStartupControllerImpl, boolean z) {
        this.t = browserStartupControllerImpl;
        this.s = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        ThreadUtils.b();
        BrowserStartupControllerImpl browserStartupControllerImpl = this.t;
        if (browserStartupControllerImpl.e) {
            return;
        }
        browserStartupControllerImpl.h = this.s ? 1 : 0;
        if (browserStartupControllerImpl.a() > 0) {
            this.t.b(1);
        }
    }
}
