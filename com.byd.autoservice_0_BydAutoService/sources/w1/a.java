package w1;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;
import n.g;
import n.i;

/* JADX INFO: compiled from: BottomNavigationMenu.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends g {
    public a(Context context) {
        super(context);
    }

    @Override // n.g
    public MenuItem a(int i3, int i4, int i5, CharSequence charSequence) {
        if (size() + 1 > 5) {
            throw new IllegalArgumentException("Maximum number of items supported by BottomNavigationView is 5. Limit can be checked with BottomNavigationView#getMaxItemCount()");
        }
        h0();
        MenuItem menuItemA = super.a(i3, i4, i5, charSequence);
        if (menuItemA instanceof i) {
            ((i) menuItemA).s(true);
        }
        g0();
        return menuItemA;
    }

    @Override // n.g, android.view.Menu
    public SubMenu addSubMenu(int i3, int i4, int i5, CharSequence charSequence) {
        throw new UnsupportedOperationException("BottomNavigationView does not support submenus");
    }
}
