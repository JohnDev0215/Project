package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;

/* JADX INFO: compiled from: BaseMenuWrapper.java */
/* JADX INFO: loaded from: classes.dex */
abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Context f262a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private b.d.g<b.g.e.a.b, MenuItem> f263b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private b.d.g<b.g.e.a.c, SubMenu> f264c;

    c(Context context) {
        this.f262a = context;
    }

    final MenuItem c(MenuItem menuItem) {
        if (!(menuItem instanceof b.g.e.a.b)) {
            return menuItem;
        }
        b.g.e.a.b bVar = (b.g.e.a.b) menuItem;
        if (this.f263b == null) {
            this.f263b = new b.d.g<>();
        }
        MenuItem menuItem2 = this.f263b.get(menuItem);
        if (menuItem2 != null) {
            return menuItem2;
        }
        j jVar = new j(this.f262a, bVar);
        this.f263b.put(bVar, jVar);
        return jVar;
    }

    final SubMenu d(SubMenu subMenu) {
        if (!(subMenu instanceof b.g.e.a.c)) {
            return subMenu;
        }
        b.g.e.a.c cVar = (b.g.e.a.c) subMenu;
        if (this.f264c == null) {
            this.f264c = new b.d.g<>();
        }
        SubMenu subMenu2 = this.f264c.get(cVar);
        if (subMenu2 != null) {
            return subMenu2;
        }
        s sVar = new s(this.f262a, cVar);
        this.f264c.put(cVar, sVar);
        return sVar;
    }

    final void e() {
        b.d.g<b.g.e.a.b, MenuItem> gVar = this.f263b;
        if (gVar != null) {
            gVar.clear();
        }
        b.d.g<b.g.e.a.c, SubMenu> gVar2 = this.f264c;
        if (gVar2 != null) {
            gVar2.clear();
        }
    }

    final void f(int i) {
        if (this.f263b == null) {
            return;
        }
        int i2 = 0;
        while (i2 < this.f263b.size()) {
            if (this.f263b.i(i2).getGroupId() == i) {
                this.f263b.k(i2);
                i2--;
            }
            i2++;
        }
    }

    final void g(int i) {
        if (this.f263b == null) {
            return;
        }
        for (int i2 = 0; i2 < this.f263b.size(); i2++) {
            if (this.f263b.i(i2).getItemId() == i) {
                this.f263b.k(i2);
                return;
            }
        }
    }
}
