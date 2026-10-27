package defpackage;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;

/* JADX INFO: compiled from: BaseMenuWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class l1 {
    public final Context a;
    public g4<f8, MenuItem> b;
    public g4<g8, SubMenu> c;

    public l1(Context context) {
        this.a = context;
    }

    public final MenuItem c(MenuItem menuItem) {
        if (!(menuItem instanceof f8)) {
            return menuItem;
        }
        f8 f8Var = (f8) menuItem;
        if (this.b == null) {
            this.b = new g4<>();
        }
        MenuItem menuItem2 = this.b.get(menuItem);
        if (menuItem2 != null) {
            return menuItem2;
        }
        s1 s1Var = new s1(this.a, f8Var);
        this.b.put(f8Var, s1Var);
        return s1Var;
    }

    public final SubMenu d(SubMenu subMenu) {
        if (!(subMenu instanceof g8)) {
            return subMenu;
        }
        g8 g8Var = (g8) subMenu;
        if (this.c == null) {
            this.c = new g4<>();
        }
        SubMenu subMenu2 = this.c.get(g8Var);
        if (subMenu2 != null) {
            return subMenu2;
        }
        b2 b2Var = new b2(this.a, g8Var);
        this.c.put(g8Var, b2Var);
        return b2Var;
    }

    public final void e() {
        g4<f8, MenuItem> g4Var = this.b;
        if (g4Var != null) {
            g4Var.clear();
        }
        g4<g8, SubMenu> g4Var2 = this.c;
        if (g4Var2 != null) {
            g4Var2.clear();
        }
    }

    public final void f(int i) {
        if (this.b == null) {
            return;
        }
        int i2 = 0;
        while (i2 < this.b.size()) {
            if (this.b.i(i2).getGroupId() == i) {
                this.b.k(i2);
                i2--;
            }
            i2++;
        }
    }

    public final void g(int i) {
        if (this.b == null) {
            return;
        }
        for (int i2 = 0; i2 < this.b.size(); i2++) {
            if (this.b.i(i2).getItemId() == i) {
                this.b.k(i2);
                return;
            }
        }
    }
}
