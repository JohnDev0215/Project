package defpackage;

import org.chromium.base.ThreadUtils;
import org.chromium.content.browser.BrowserStartupControllerImpl;

/* JADX INFO: compiled from: BrowserStartupControllerImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public final class rj implements Runnable {
    public final /* synthetic */ boolean e;
    public final /* synthetic */ BrowserStartupControllerImpl f;

    public rj(BrowserStartupControllerImpl browserStartupControllerImpl, boolean z) {
        this.f = browserStartupControllerImpl;
        this.e = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object obj = ThreadUtils.a;
        BrowserStartupControllerImpl browserStartupControllerImpl = this.f;
        if (browserStartupControllerImpl.e) {
            return;
        }
        browserStartupControllerImpl.h = this.e ? 1 : 0;
        if (browserStartupControllerImpl.c() > 0) {
            browserStartupControllerImpl.d();
        }
    }
}
