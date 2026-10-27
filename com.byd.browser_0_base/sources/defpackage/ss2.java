package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: StaticLayout.java */
/* JADX INFO: loaded from: classes.dex */
public final class ss2 extends j23 {
    public final /* synthetic */ ts2 i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ss2(ts2 ts2Var, y13 y13Var) {
        super(y13Var);
        this.i = ts2Var;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void A0(TabImpl tabImpl) {
        this.i.Q(tabImpl);
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void M(int i, Tab tab) {
        this.i.Q(tab);
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void a1(Tab tab, GURL gurl) {
        ts2 ts2Var = this.i;
        if (ts2Var.f98J) {
            int id = tab.getId();
            PropertyModel propertyModel = ts2Var.w;
            if (propertyModel.h(m61.e) == id && propertyModel.k(m61.A) && ts2Var.B) {
                ts2Var.J();
            }
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void l0(int i, Tab tab) {
        ts2 ts2Var = this.i;
        if (ts2Var.w.h(m61.e) != tab.getId()) {
            ts2Var.P(tab);
        } else {
            ts2Var.Q(tab);
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void u(Tab tab) {
        this.i.Q(tab);
    }
}
