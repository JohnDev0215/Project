package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;

/* JADX INFO: loaded from: classes.dex */
abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Context f789a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private a.d.g<a.g.e.a.b, MenuItem> f790b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private a.d.g<a.g.e.a.c, SubMenu> f791c;

    c(Context context) {
        this.f789a = context;
    }

    final MenuItem c(MenuItem menuItem) {
        if (!(menuItem instanceof a.g.e.a.b)) {
            return menuItem;
        }
        a.g.e.a.b bVar = (a.g.e.a.b) menuItem;
        if (this.f790b == null) {
            this.f790b = new a.d.g<>();
        }
        MenuItem menuItem2 = this.f790b.get(menuItem);
        if (menuItem2 != null) {
            return menuItem2;
        }
        j jVar = new j(this.f789a, bVar);
        this.f790b.put(bVar, jVar);
        return jVar;
    }

    final SubMenu d(SubMenu subMenu) {
        if (!(subMenu instanceof a.g.e.a.c)) {
            return subMenu;
        }
        a.g.e.a.c cVar = (a.g.e.a.c) subMenu;
        if (this.f791c == null) {
            this.f791c = new a.d.g<>();
        }
        SubMenu subMenu2 = this.f791c.get(cVar);
        if (subMenu2 != null) {
            return subMenu2;
        }
        s sVar = new s(this.f789a, cVar);
        this.f791c.put(cVar, sVar);
        return sVar;
    }

    final void e() {
        a.d.g<a.g.e.a.b, MenuItem> gVar = this.f790b;
        if (gVar != null) {
            gVar.clear();
        }
        a.d.g<a.g.e.a.c, SubMenu> gVar2 = this.f791c;
        if (gVar2 != null) {
            gVar2.clear();
        }
    }

    final void f(int i) {
        if (this.f790b == null) {
            return;
        }
        int i2 = 0;
        while (i2 < this.f790b.size()) {
            if (this.f790b.i(i2).getGroupId() == i) {
                this.f790b.k(i2);
                i2--;
            }
            i2++;
        }
    }

    final void g(int i) {
        if (this.f790b == null) {
            return;
        }
        for (int i2 = 0; i2 < this.f790b.size(); i2++) {
            if (this.f790b.i(i2).getItemId() == i) {
                this.f790b.k(i2);
                return;
            }
        }
    }
}
