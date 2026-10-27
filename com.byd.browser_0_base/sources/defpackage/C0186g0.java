package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes.dex
 */
/* JADX INFO: renamed from: g0, reason: case insensitive filesystem */
/* JADX INFO: compiled from: AccessibilityTabHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0186g0 extends nh0 implements lk3 {
    public final Tab e;
    public WebContents f;

    public C0186g0(TabImpl tabImpl) {
        this.e = tabImpl;
        tabImpl.x(this);
        WebContents webContents = tabImpl.g;
        this.f = webContents;
        if (webContents != null) {
            y1(tabImpl);
        }
    }

    public static void y1(Tab tab) {
        WebContentsAccessibilityImpl webContentsAccessibilityImplB = kq3.b(tab.c());
        webContentsAccessibilityImplB.E = true;
        webContentsAccessibilityImplB.F = !tab.isCustomTab();
    }

    @Override // defpackage.n23
    public final void P0(Tab tab, WindowAndroid windowAndroid) {
        if (tab.c() != null) {
            y1(tab);
        }
    }

    @Override // defpackage.lk3
    public final void destroy() {
        this.e.t(this);
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void u(Tab tab) {
        if (this.f == tab.c()) {
            return;
        }
        if (tab.c() != null) {
            y1(tab);
        }
        this.f = tab.c();
    }
}
