package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;

/* JADX INFO: compiled from: BaseMenuWrapper.java */
/* JADX INFO: loaded from: classes.dex */
abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Context f213a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private b.d.g<b.g.e.a.b, MenuItem> f214b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private b.d.g<b.g.e.a.c, SubMenu> f215c;

    c(Context context) {
        this.f213a = context;
    }

    final MenuItem c(MenuItem menuItem) {
        if (!(menuItem instanceof b.g.e.a.b)) {
            return menuItem;
        }
        b.g.e.a.b bVar = (b.g.e.a.b) menuItem;
        if (this.f214b == null) {
            this.f214b = new b.d.g<>();
        }
        MenuItem menuItem2 = this.f214b.get(menuItem);
        if (menuItem2 != null) {
            return menuItem2;
        }
        j jVar = new j(this.f213a, bVar);
        this.f214b.put(bVar, jVar);
        return jVar;
    }

    final SubMenu d(SubMenu subMenu) {
        if (!(subMenu instanceof b.g.e.a.c)) {
            return subMenu;
        }
        b.g.e.a.c cVar = (b.g.e.a.c) subMenu;
        if (this.f215c == null) {
            this.f215c = new b.d.g<>();
        }
        SubMenu subMenu2 = this.f215c.get(cVar);
        if (subMenu2 != null) {
            return subMenu2;
        }
        s sVar = new s(this.f213a, cVar);
        this.f215c.put(cVar, sVar);
        return sVar;
    }

    final void e() {
        b.d.g<b.g.e.a.b, MenuItem> gVar = this.f214b;
        if (gVar != null) {
            gVar.clear();
        }
        b.d.g<b.g.e.a.c, SubMenu> gVar2 = this.f215c;
        if (gVar2 != null) {
            gVar2.clear();
        }
    }

    final void f(int i) {
        if (this.f214b == null) {
            return;
        }
        int i2 = 0;
        while (i2 < this.f214b.size()) {
            if (this.f214b.i(i2).getGroupId() == i) {
                this.f214b.k(i2);
                i2--;
            }
            i2++;
        }
    }

    final void g(int i) {
        if (this.f214b == null) {
            return;
        }
        for (int i2 = 0; i2 < this.f214b.size(); i2++) {
            if (this.f214b.i(i2).getItemId() == i) {
                this.f214b.k(i2);
                return;
            }
        }
    }
}
