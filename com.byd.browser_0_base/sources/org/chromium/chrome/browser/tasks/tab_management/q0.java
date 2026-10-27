package org.chromium.chrome.browser.tasks.tab_management;

import defpackage.a10;
import defpackage.ad0;
import defpackage.c13;
import defpackage.g72;
import defpackage.ke0;
import defpackage.l02;
import defpackage.rh3;
import defpackage.x00;
import defpackage.x10;
import defpackage.xl2;
import defpackage.y00;
import org.chromium.base.Callback;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tasks.tab_management.h0;
import org.chromium.chrome.browser.tasks.tab_management.j0;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class q0 implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ q0(Object obj, int i, Object obj2) {
        this.e = i;
        this.f = obj;
        this.g = obj2;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i = this.e;
        int i2 = 0;
        Object obj2 = this.g;
        Object obj3 = this.f;
        switch (i) {
            case 0:
                ((TabListRecyclerView) obj3).c1 = false;
                ((Runnable) obj2).run();
                break;
            case 1:
                CouponCardView couponCardView = (CouponCardView) obj3;
                PropertyModel propertyModel = (PropertyModel) obj2;
                if (((xl2.c) obj) == null) {
                    j0.g gVar = (j0.g) propertyModel.j(u0.y);
                    int i3 = 4;
                    x10 x10Var = new x10(i3, couponCardView);
                    gVar.getClass();
                    ad0 ad0Var = new ad0(x10Var, i3);
                    String[] strArr = a10.q;
                    Tab tab = gVar.a;
                    if (tab == null || tab.isDestroyed() || tab.isIncognito() || tab.isCustomTab()) {
                        PostTask.c(rh3.a, new x00(ad0Var, 0));
                    } else {
                        l02.e(tab, new ke0(19, tab), new y00(i2, tab), a10.class, ad0Var);
                    }
                } else {
                    couponCardView.setVisibility(8);
                }
                break;
            default:
                j0 j0Var = (j0) obj3;
                h0.c cVar = (h0.c) obj;
                c13 c13Var = j0.I;
                j0Var.getClass();
                int iC = ((g72) obj2).c();
                n0 n0Var = j0Var.f;
                int iF = n0Var.F(iC);
                if (iF != -1 && cVar != null) {
                    n0Var.get(iF).b.p(u0.d, cVar);
                    break;
                }
                break;
        }
    }
}
