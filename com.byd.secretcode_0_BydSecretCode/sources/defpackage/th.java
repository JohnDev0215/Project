package defpackage;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;

/* JADX INFO: compiled from: BottomNavigationMenu.java */
/* JADX INFO: loaded from: classes.dex */
public final class th extends p1 {
    public th(Context context) {
        super(context);
    }

    @Override // defpackage.p1
    public MenuItem a(int i, int i2, int i3, CharSequence charSequence) {
        if (size() + 1 > 5) {
            throw new IllegalArgumentException("Maximum number of items supported by BottomNavigationView is 5. Limit can be checked with BottomNavigationView#getMaxItemCount()");
        }
        h0();
        MenuItem menuItemA = super.a(i, i2, i3, charSequence);
        if (menuItemA instanceof r1) {
            ((r1) menuItemA).s(true);
        }
        g0();
        return menuItemA;
    }

    @Override // defpackage.p1, android.view.Menu
    public SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        throw new UnsupportedOperationException("BottomNavigationView does not support submenus");
    }
}
