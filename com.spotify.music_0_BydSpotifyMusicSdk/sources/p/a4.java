package p;

import androidx.appcompat.view.menu.ActionMenuItemView;

/* JADX INFO: loaded from: classes.dex */
public final class a4 extends nw0 {
    public final /* synthetic */ ActionMenuItemView r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a4(ActionMenuItemView actionMenuItemView) {
        super(actionMenuItemView);
        this.r = actionMenuItemView;
    }

    @Override // p.nw0
    public final jd2 b() {
        c4 c4Var;
        b4 b4Var = this.r.u;
        if (b4Var == null || (c4Var = ((d4) b4Var).a.B) == null) {
            return null;
        }
        return c4Var.a();
    }

    @Override // p.nw0
    public final boolean c() {
        jd2 jd2VarB;
        ActionMenuItemView actionMenuItemView = this.r;
        ri1 ri1Var = actionMenuItemView.s;
        return ri1Var != null && ri1Var.d(actionMenuItemView.f4p) && (jd2VarB = b()) != null && jd2VarB.a();
    }
}
