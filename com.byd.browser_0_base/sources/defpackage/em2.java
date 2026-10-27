package defpackage;

import J.N;
import defpackage.di1;
import defpackage.fm2;
import org.chromium.chrome.browser.browsing_data.ClearBrowsingDataFragment;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: SignOutDialogCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class em2 implements di1.a {
    public final /* synthetic */ fm2 e;

    public em2(fm2 fm2Var) {
        this.e = fm2Var;
    }

    @Override // di1.a
    public final void b(int i) {
        fm2 fm2Var = this.e;
        if (i == 1) {
            N.MX17n_KK(6, fm2Var.b);
        } else {
            N.MX17n_KK(7, fm2Var.b);
        }
    }

    @Override // di1.a
    public final void c(int i, PropertyModel propertyModel) {
        fm2 fm2Var = this.e;
        if (i != 0) {
            if (i == 1) {
                fm2Var.e.b(2, fm2Var.d);
                return;
            }
            return;
        }
        fm2.a aVar = fm2Var.c;
        if (fm2Var.a.getVisibility() == 0) {
            fm2Var.a.isChecked();
        }
        ClearBrowsingDataFragment clearBrowsingDataFragment = (ClearBrowsingDataFragment) aVar;
        if (clearBrowsingDataFragment.i0.l().b(0)) {
            clearBrowsingDataFragment.i0.q(new wv(24, clearBrowsingDataFragment));
        }
        fm2Var.e.b(1, fm2Var.d);
    }
}
