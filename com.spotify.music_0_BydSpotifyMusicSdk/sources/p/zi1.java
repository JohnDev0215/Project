package p;

import android.view.ActionProvider;
import android.view.MenuItem;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class zi1 extends yi1 implements ActionProvider.VisibilityListener {
    public oh1 c;

    @Override // p.yi1
    public final boolean a() {
        return this.a.isVisible();
    }

    @Override // p.yi1
    public final View b(MenuItem menuItem) {
        return this.a.onCreateActionView(menuItem);
    }

    @Override // p.yi1
    public final boolean c() {
        return this.a.overridesItemVisibility();
    }

    @Override // p.yi1
    public final void d(oh1 oh1Var) {
        this.c = oh1Var;
        this.a.setVisibilityListener(this);
    }

    @Override // android.view.ActionProvider.VisibilityListener
    public final void onActionProviderVisibilityChanged(boolean z) {
        oh1 oh1Var = this.c;
        if (oh1Var != null) {
            si1 si1Var = ((xi1) oh1Var.e).n;
            si1Var.h = true;
            si1Var.p(true);
        }
    }
}
