package p;

import android.view.MenuItem;

/* JADX INFO: loaded from: classes.dex */
public final class bj1 implements MenuItem.OnActionExpandListener {
    public final MenuItem.OnActionExpandListener a;
    public final /* synthetic */ dj1 b;

    public bj1(dj1 dj1Var, MenuItem.OnActionExpandListener onActionExpandListener) {
        this.b = dj1Var;
        this.a = onActionExpandListener;
    }

    @Override // android.view.MenuItem.OnActionExpandListener
    public final boolean onMenuItemActionCollapse(MenuItem menuItem) {
        return this.a.onMenuItemActionCollapse(this.b.c(menuItem));
    }

    @Override // android.view.MenuItem.OnActionExpandListener
    public final boolean onMenuItemActionExpand(MenuItem menuItem) {
        return this.a.onMenuItemActionExpand(this.b.c(menuItem));
    }
}
