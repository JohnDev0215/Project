package defpackage;

import org.chromium.base.ThreadUtils;
import org.chromium.content.browser.BrowserStartupControllerImpl;

/* JADX INFO: compiled from: BrowserStartupController.java */
/* JADX INFO: loaded from: classes3.dex */
public interface qj {

    /* JADX INFO: compiled from: BrowserStartupController.java */
    public interface a {
        void a();

        void b();
    }

    static BrowserStartupControllerImpl a() {
        Object obj = ThreadUtils.a;
        if (BrowserStartupControllerImpl.l == null) {
            BrowserStartupControllerImpl.l = new BrowserStartupControllerImpl();
        }
        return BrowserStartupControllerImpl.l;
    }
}
