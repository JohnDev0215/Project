package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;

/* JADX INFO: compiled from: BaseMenuWrapper.java */
/* JADX INFO: loaded from: classes.dex */
abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Context f270a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private b.d.g<b.g.e.a.b, MenuItem> f271b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private b.d.g<b.g.e.a.c, SubMenu> f272c;

    c(Context context) {
        this.f270a = context;
    }

    final MenuItem c(MenuItem menuItem) {
        if (!(menuItem instanceof b.g.e.a.b)) {
            return menuItem;
        }
        b.g.e.a.b bVar = (b.g.e.a.b) menuItem;
        if (this.f271b == null) {
            this.f271b = new b.d.g<>();
        }
        MenuItem menuItem2 = this.f271b.get(menuItem);
        if (menuItem2 != null) {
            return menuItem2;
        }
        j jVar = new j(this.f270a, bVar);
        this.f271b.put(bVar, jVar);
        return jVar;
    }

    final SubMenu d(SubMenu subMenu) {
        if (!(subMenu instanceof b.g.e.a.c)) {
            return subMenu;
        }
        b.g.e.a.c cVar = (b.g.e.a.c) subMenu;
        if (this.f272c == null) {
            this.f272c = new b.d.g<>();
        }
        SubMenu subMenu2 = this.f272c.get(cVar);
        if (subMenu2 != null) {
            return subMenu2;
        }
        s sVar = new s(this.f270a, cVar);
        this.f272c.put(cVar, sVar);
        return sVar;
    }

    final void e() {
        b.d.g<b.g.e.a.b, MenuItem> gVar = this.f271b;
        if (gVar != null) {
            gVar.clear();
        }
        b.d.g<b.g.e.a.c, SubMenu> gVar2 = this.f272c;
        if (gVar2 != null) {
            gVar2.clear();
        }
    }

    final void f(int i2) {
        if (this.f271b == null) {
            return;
        }
        int i3 = 0;
        while (i3 < this.f271b.size()) {
            if (this.f271b.i(i3).getGroupId() == i2) {
                this.f271b.k(i3);
                i3--;
            }
            i3++;
        }
    }

    final void g(int i2) {
        if (this.f271b == null) {
            return;
        }
        for (int i3 = 0; i3 < this.f271b.size(); i3++) {
            if (this.f271b.i(i3).getItemId() == i2) {
                this.f271b.k(i3);
                return;
            }
        }
    }
}
