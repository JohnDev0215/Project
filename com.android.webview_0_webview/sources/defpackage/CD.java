package defpackage;

import org.chromium.base.ThreadUtils;
import org.chromium.content.browser.BrowserStartupControllerImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class CD implements ED {
    public static ED a() {
        ThreadUtils.b();
        if (BrowserStartupControllerImpl.l == null) {
            BrowserStartupControllerImpl.l = new BrowserStartupControllerImpl();
        }
        return BrowserStartupControllerImpl.l;
    }
}
