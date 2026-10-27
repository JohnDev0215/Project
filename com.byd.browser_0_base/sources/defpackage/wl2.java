package defpackage;

import J.N;
import defpackage.xl2;
import org.chromium.chrome.browser.optimization_guide.OptimizationGuideBridge;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content_public.browser.NavigationHandle;

/* JADX INFO: compiled from: ShoppingPersistedTabData.java */
/* JADX INFO: loaded from: classes2.dex */
public final class wl2 extends nh0 {
    public final /* synthetic */ xl2 e;

    public wl2(xl2 xl2Var) {
        this.e = xl2Var;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void C0(TabImpl tabImpl, NavigationHandle navigationHandle) {
        if (navigationHandle.c || tabImpl.getUrl().equals(navigationHandle.e)) {
            return;
        }
        this.e.u();
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void o1(TabImpl tabImpl, NavigationHandle navigationHandle) {
        if (navigationHandle.c || !navigationHandle.g) {
            return;
        }
        boolean z = (navigationHandle.d & 33554432) != 0;
        boolean z2 = navigationHandle.k;
        xl2 xl2Var = this.e;
        if (z2 || z) {
            xl2Var.u();
        }
        if (xl2.r()) {
            xl2Var.getClass();
            if (navigationHandle.a) {
                OptimizationGuideBridge optimizationGuideBridgeA = xl2.a.a.a();
                ec0 ec0Var = new ec0(xl2Var, tabImpl, (Object) null, navigationHandle);
                long j = optimizationGuideBridgeA.a;
                if (j == 0) {
                    ec0Var.j(0, null);
                } else {
                    N.MiCQ6k4j(j, navigationHandle.e, 18, ec0Var);
                }
            }
        }
    }
}
