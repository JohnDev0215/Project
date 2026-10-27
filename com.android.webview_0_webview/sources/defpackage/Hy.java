package defpackage;

import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;
import org.chromium.content.browser.BrowserStartupControllerImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Hy implements Runnable {
    public final /* synthetic */ BrowserStartupControllerImpl s;

    public Hy(BrowserStartupControllerImpl browserStartupControllerImpl) {
        this.s = browserStartupControllerImpl;
    }

    @Override // java.lang.Runnable
    public void run() {
        BrowserStartupControllerImpl browserStartupControllerImpl = this.s;
        Gy gy = new Gy(this);
        Objects.requireNonNull(browserStartupControllerImpl);
        ThreadUtils.b();
        if (browserStartupControllerImpl.f) {
            PostTask.a(AbstractC0538bE.e, new Ky(browserStartupControllerImpl, gy), 0L);
        } else {
            browserStartupControllerImpl.a.add(gy);
        }
    }
}
