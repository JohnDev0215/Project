package defpackage;

import android.view.View;
import defpackage.l23;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;
import org.chromium.content_public.browser.WebContents;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes2.dex
 */
/* JADX INFO: renamed from: j0, reason: case insensitive filesystem */
/* JADX INFO: compiled from: AccessibilityVisibilityHandler.java */
/* JADX INFO: loaded from: classes2.dex */
public final class C0192j0 implements v80, l23.a {
    public final C0190i0 e;
    public final l23 f;
    public TabImpl g;

    public C0192j0(r2 r2Var, z2 z2Var, l23 l23Var) {
        this.e = new C0190i0(this, z2Var);
        this.f = l23Var;
        l23Var.b(this);
        r2Var.f(this);
    }

    @Override // defpackage.v80
    public final void onDestroy() {
        this.e.destroy();
        this.f.e(this);
        this.g = null;
    }

    @Override // l23.a
    public final void r(boolean z, boolean z2) {
        TabImpl tabImpl = this.g;
        if (tabImpl == null) {
            return;
        }
        boolean z3 = z || tabImpl.s();
        TabImpl tabImpl2 = this.g;
        View view = tabImpl2.getView();
        if (view != null) {
            int i = z3 ? 4 : 1;
            if (view.getImportantForAccessibility() != i) {
                view.setImportantForAccessibility(i);
                view.sendAccessibilityEvent(2048);
            }
        }
        WebContents webContents = tabImpl2.g;
        WebContentsAccessibilityImpl webContentsAccessibilityImplB = webContents != null ? kq3.b(webContents) : null;
        if (webContentsAccessibilityImplB == null || tabImpl2.G == z3) {
            return;
        }
        webContentsAccessibilityImplB.G = Boolean.valueOf(z3);
        webContentsAccessibilityImplB.A(-1, 2048);
        tabImpl2.G = z3;
    }
}
