package defpackage;

import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;
import org.chromium.content.browser.accessibility.d;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: WebContentsAccessibility.java */
/* JADX INFO: loaded from: classes3.dex */
public interface kq3 {
    static WebContentsAccessibilityImpl b(WebContents webContents) {
        lk3 lk3Var;
        mk3 mk3VarP0;
        WebContentsImpl webContentsImpl = (WebContentsImpl) webContents;
        if (webContentsImpl.o && (mk3VarP0 = webContentsImpl.p0()) != null) {
            lk3 lk3VarB = mk3VarP0.b(WebContentsAccessibilityImpl.class);
            if (lk3VarB == null) {
                lk3VarB = mk3VarP0.d(WebContentsAccessibilityImpl.class, new WebContentsAccessibilityImpl(new d(webContentsImpl)));
            }
            lk3Var = (lk3) WebContentsAccessibilityImpl.class.cast(lk3VarB);
        } else {
            lk3Var = null;
        }
        return (WebContentsAccessibilityImpl) lk3Var;
    }
}
