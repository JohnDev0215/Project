package b.b.p.j;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;

/* JADX INFO: compiled from: BaseMenuWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f1596a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b.e.g<b.h.g.a.b, MenuItem> f1597b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b.e.g<b.h.g.a.c, SubMenu> f1598c;

    public c(Context context) {
        this.f1596a = context;
    }

    public final MenuItem c(MenuItem menuItem) {
        if (!(menuItem instanceof b.h.g.a.b)) {
            return menuItem;
        }
        b.h.g.a.b bVar = (b.h.g.a.b) menuItem;
        if (this.f1597b == null) {
            this.f1597b = new b.e.g<>();
        }
        MenuItem menuItem2 = this.f1597b.get(menuItem);
        if (menuItem2 != null) {
            return menuItem2;
        }
        j jVar = new j(this.f1596a, bVar);
        this.f1597b.put(bVar, jVar);
        return jVar;
    }

    public final SubMenu d(SubMenu subMenu) {
        if (!(subMenu instanceof b.h.g.a.c)) {
            return subMenu;
        }
        b.h.g.a.c cVar = (b.h.g.a.c) subMenu;
        if (this.f1598c == null) {
            this.f1598c = new b.e.g<>();
        }
        SubMenu subMenu2 = this.f1598c.get(cVar);
        if (subMenu2 != null) {
            return subMenu2;
        }
        s sVar = new s(this.f1596a, cVar);
        this.f1598c.put(cVar, sVar);
        return sVar;
    }

    public final void e() {
        b.e.g<b.h.g.a.b, MenuItem> gVar = this.f1597b;
        if (gVar != null) {
            gVar.clear();
        }
        b.e.g<b.h.g.a.c, SubMenu> gVar2 = this.f1598c;
        if (gVar2 != null) {
            gVar2.clear();
        }
    }

    public final void f(int i2) {
        if (this.f1597b == null) {
            return;
        }
        int i3 = 0;
        while (i3 < this.f1597b.size()) {
            if (this.f1597b.i(i3).getGroupId() == i2) {
                this.f1597b.k(i3);
                i3--;
            }
            i3++;
        }
    }

    public final void g(int i2) {
        if (this.f1597b == null) {
            return;
        }
        for (int i3 = 0; i3 < this.f1597b.size(); i3++) {
            if (this.f1597b.i(i3).getItemId() == i2) {
                this.f1597b.k(i3);
                return;
            }
        }
    }
}
