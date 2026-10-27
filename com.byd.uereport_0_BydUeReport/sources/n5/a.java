package n5;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;
import q.g;
import q.i;

/* JADX INFO: compiled from: BottomNavigationMenu.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a extends g {
    public a(Context context) {
        super(context);
    }

    @Override // q.g
    public MenuItem a(int i8, int i9, int i10, CharSequence charSequence) {
        if (size() + 1 > 5) {
            throw new IllegalArgumentException("Maximum number of items supported by BottomNavigationView is 5. Limit can be checked with BottomNavigationView#getMaxItemCount()");
        }
        h0();
        MenuItem menuItemA = super.a(i8, i9, i10, charSequence);
        if (menuItemA instanceof i) {
            ((i) menuItemA).s(true);
        }
        g0();
        return menuItemA;
    }

    @Override // q.g, android.view.Menu
    public SubMenu addSubMenu(int i8, int i9, int i10, CharSequence charSequence) {
        throw new UnsupportedOperationException("BottomNavigationView does not support submenus");
    }
}
