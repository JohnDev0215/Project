package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;

/* JADX INFO: compiled from: SelectionPopupBackPressHandler.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ei2 extends nh0 implements vb, t13, w80 {
    public final aq1<Boolean> e = new aq1<>();
    public final td2 f = new td2(23, this);
    public SelectionPopupControllerImpl g;
    public Tab h;

    public ei2(y13 y13Var) {
        y13Var.u().a(this);
    }

    @Override // defpackage.t13
    public final void B0(int i, int i2, Tab tab) {
        this.e.r(Boolean.FALSE);
        SelectionPopupControllerImpl selectionPopupControllerImpl = this.g;
        td2 td2Var = this.f;
        if (selectionPopupControllerImpl != null) {
            selectionPopupControllerImpl.r.d(td2Var);
        }
        if (tab.c() == null) {
            return;
        }
        if (this.h != null) {
            tab.t(this);
        }
        tab.x(this);
        this.h = tab;
        SelectionPopupControllerImpl selectionPopupControllerImplJ = SelectionPopupControllerImpl.j(tab.c());
        this.g = selectionPopupControllerImplJ;
        selectionPopupControllerImplJ.r.p(td2Var);
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void Q(Tab tab, boolean z, boolean z2) {
        aq1<Boolean> aq1Var = this.g.r;
        td2 td2Var = this.f;
        aq1Var.d(td2Var);
        SelectionPopupControllerImpl selectionPopupControllerImplJ = SelectionPopupControllerImpl.j(tab.c());
        this.g = selectionPopupControllerImplJ;
        selectionPopupControllerImplJ.r.p(td2Var);
    }

    @Override // defpackage.w80
    public final void destroy() {
        Tab tab = this.h;
        if (tab != null) {
            tab.t(this);
        }
        this.h = null;
        SelectionPopupControllerImpl selectionPopupControllerImpl = this.g;
        if (selectionPopupControllerImpl != null) {
            selectionPopupControllerImpl.r.d(this.f);
            this.g = null;
        }
    }

    @Override // defpackage.vb
    public final zp1<Boolean> getHandleBackPressChangedSupplier() {
        return this.e;
    }

    @Override // defpackage.vb
    public final int o() {
        SelectionPopupControllerImpl selectionPopupControllerImpl = this.g;
        int i = !selectionPopupControllerImpl.C ? 1 : 0;
        selectionPopupControllerImpl.d();
        return i;
    }
}
