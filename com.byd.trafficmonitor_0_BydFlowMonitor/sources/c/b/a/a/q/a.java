package c.b.a.a.q;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;
import b.b.p.j.g;
import b.b.p.j.i;

/* JADX INFO: compiled from: BottomNavigationMenu.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends g {
    public a(Context context) {
        super(context);
    }

    @Override // b.b.p.j.g
    public MenuItem a(int i2, int i3, int i4, CharSequence charSequence) {
        if (size() + 1 > 5) {
            throw new IllegalArgumentException("Maximum number of items supported by BottomNavigationView is 5. Limit can be checked with BottomNavigationView#getMaxItemCount()");
        }
        h0();
        MenuItem menuItemA = super.a(i2, i3, i4, charSequence);
        if (menuItemA instanceof i) {
            ((i) menuItemA).s(true);
        }
        g0();
        return menuItemA;
    }

    @Override // b.b.p.j.g, android.view.Menu
    public SubMenu addSubMenu(int i2, int i3, int i4, CharSequence charSequence) {
        throw new UnsupportedOperationException("BottomNavigationView does not support submenus");
    }
}
