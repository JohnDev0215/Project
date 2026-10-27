package defpackage;

import defpackage.z2;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes2.dex
 */
/* JADX INFO: renamed from: i0, reason: case insensitive filesystem */
/* JADX INFO: compiled from: AccessibilityVisibilityHandler.java */
/* JADX INFO: loaded from: classes2.dex */
public final class C0190i0 extends z2.b {
    public final /* synthetic */ C0192j0 h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0190i0(C0192j0 c0192j0, z2 z2Var) {
        super(z2Var, false);
        this.h = c0192j0;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void u(Tab tab) {
        C0192j0 c0192j0 = this.h;
        c0192j0.r(c0192j0.f.c(), c0192j0.f.b.b());
    }

    @Override // z2.b
    public final void y1(Tab tab) {
        C0192j0 c0192j0 = this.h;
        TabImpl tabImpl = c0192j0.g;
        if (tabImpl == tab) {
            return;
        }
        if (tabImpl != null) {
            c0192j0.r(false, false);
        }
        TabImpl tabImpl2 = (TabImpl) tab;
        c0192j0.g = tabImpl2;
        if (tabImpl2 != null) {
            l23 l23Var = c0192j0.f;
            c0192j0.r(l23Var.c(), l23Var.b.b());
        }
    }
}
