package p;

import android.view.MenuItem;

/* JADX INFO: loaded from: classes.dex */
public final class cj1 implements MenuItem.OnMenuItemClickListener {
    public final MenuItem.OnMenuItemClickListener a;
    public final /* synthetic */ dj1 b;

    public cj1(dj1 dj1Var, MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.b = dj1Var;
        this.a = onMenuItemClickListener;
    }

    @Override // android.view.MenuItem.OnMenuItemClickListener
    public final boolean onMenuItemClick(MenuItem menuItem) {
        return this.a.onMenuItemClick(this.b.c(menuItem));
    }
}
