package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: BottomSheetFocusHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class oi extends bh0 {
    public final WindowAndroid e;
    public final ji f;

    public oi(ji jiVar, WindowAndroid windowAndroid) {
        this.f = jiVar;
        this.e = windowAndroid;
    }

    @Override // defpackage.bh0, defpackage.vi
    public final void c(int i) {
        WebContentsAccessibilityImpl webContentsAccessibilityImplB;
        int i2;
        Tab tabU = g23.u(this.e);
        WebContents webContentsC = tabU != null ? tabU.c() : null;
        if (webContentsC != null && (webContentsAccessibilityImplB = kq3.b(webContentsC)) != null && webContentsAccessibilityImplB.m() && (i2 = webContentsAccessibilityImplB.t) != -1) {
            webContentsAccessibilityImplB.q(i2);
            webContentsAccessibilityImplB.z(webContentsAccessibilityImplB.t);
        }
        this.f.e(this);
    }
}
