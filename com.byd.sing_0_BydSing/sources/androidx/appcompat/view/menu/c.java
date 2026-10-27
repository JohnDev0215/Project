package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;

/* JADX INFO: compiled from: BaseMenuWrapper.java */
/* JADX INFO: loaded from: classes.dex */
abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Context f724a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private c.d.g<c.g.e.a.b, MenuItem> f725b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private c.d.g<c.g.e.a.c, SubMenu> f726c;

    c(Context context) {
        this.f724a = context;
    }

    final MenuItem c(MenuItem menuItem) {
        if (!(menuItem instanceof c.g.e.a.b)) {
            return menuItem;
        }
        c.g.e.a.b bVar = (c.g.e.a.b) menuItem;
        if (this.f725b == null) {
            this.f725b = new c.d.g<>();
        }
        MenuItem menuItem2 = this.f725b.get(menuItem);
        if (menuItem2 != null) {
            return menuItem2;
        }
        j jVar = new j(this.f724a, bVar);
        this.f725b.put(bVar, jVar);
        return jVar;
    }

    final SubMenu d(SubMenu subMenu) {
        if (!(subMenu instanceof c.g.e.a.c)) {
            return subMenu;
        }
        c.g.e.a.c cVar = (c.g.e.a.c) subMenu;
        if (this.f726c == null) {
            this.f726c = new c.d.g<>();
        }
        SubMenu subMenu2 = this.f726c.get(cVar);
        if (subMenu2 != null) {
            return subMenu2;
        }
        s sVar = new s(this.f724a, cVar);
        this.f726c.put(cVar, sVar);
        return sVar;
    }

    final void e() {
        c.d.g<c.g.e.a.b, MenuItem> gVar = this.f725b;
        if (gVar != null) {
            gVar.clear();
        }
        c.d.g<c.g.e.a.c, SubMenu> gVar2 = this.f726c;
        if (gVar2 != null) {
            gVar2.clear();
        }
    }

    final void f(int i) {
        if (this.f725b == null) {
            return;
        }
        int i2 = 0;
        while (i2 < this.f725b.size()) {
            if (this.f725b.i(i2).getGroupId() == i) {
                this.f725b.k(i2);
                i2--;
            }
            i2++;
        }
    }

    final void g(int i) {
        if (this.f725b == null) {
            return;
        }
        for (int i2 = 0; i2 < this.f725b.size(); i2++) {
            if (this.f725b.i(i2).getItemId() == i) {
                this.f725b.k(i2);
                return;
            }
        }
    }
}
