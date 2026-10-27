package defpackage;

import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: StartSurfaceMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class sr2 implements e23 {
    public final /* synthetic */ yr2 e;

    public sr2(yr2 yr2Var) {
        this.e = yr2Var;
    }

    @Override // defpackage.e23
    public final void k(TabModel tabModel) {
        boolean zIsIncognito = tabModel.isIncognito();
        yr2 yr2Var = this.e;
        if (zIsIncognito == yr2Var.y) {
            return;
        }
        yr2Var.y = zIsIncognito;
        PropertyModel.n nVar = n73.b;
        PropertyModel propertyModel = yr2Var.h;
        propertyModel.l(nVar, zIsIncognito);
        yr2Var.j0();
        if (propertyModel.k(zr2.e)) {
            yr2Var.P();
        }
    }
}
