package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;
import defpackage.pw2;
import defpackage.qw2;
import defpackage.wm2;

/* JADX INFO: loaded from: classes.dex */
abstract class BaseMenuWrapper {
    public final Context a;
    public wm2<pw2, MenuItem> b;
    public wm2<qw2, SubMenu> c;

    public BaseMenuWrapper(Context context) {
        this.a = context;
    }

    public final MenuItem c(MenuItem menuItem) {
        if (!(menuItem instanceof pw2)) {
            return menuItem;
        }
        pw2 pw2Var = (pw2) menuItem;
        if (this.b == null) {
            this.b = new wm2<>();
        }
        MenuItem menuItem2 = this.b.get(pw2Var);
        if (menuItem2 != null) {
            return menuItem2;
        }
        MenuItemWrapperICS menuItemWrapperICS = new MenuItemWrapperICS(this.a, pw2Var);
        this.b.put(pw2Var, menuItemWrapperICS);
        return menuItemWrapperICS;
    }

    public final SubMenu d(SubMenu subMenu) {
        if (!(subMenu instanceof qw2)) {
            return subMenu;
        }
        qw2 qw2Var = (qw2) subMenu;
        if (this.c == null) {
            this.c = new wm2<>();
        }
        SubMenu subMenu2 = this.c.get(qw2Var);
        if (subMenu2 != null) {
            return subMenu2;
        }
        SubMenuWrapperICS subMenuWrapperICS = new SubMenuWrapperICS(this.a, qw2Var);
        this.c.put(qw2Var, subMenuWrapperICS);
        return subMenuWrapperICS;
    }
}
