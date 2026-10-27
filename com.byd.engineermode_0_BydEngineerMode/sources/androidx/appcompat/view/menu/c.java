package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;

/* JADX INFO: compiled from: BaseMenuWrapper.java */
/* JADX INFO: loaded from: classes.dex */
abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Context f195a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private b.d.g<b.g.e.a.b, MenuItem> f196b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private b.d.g<b.g.e.a.c, SubMenu> f197c;

    c(Context context) {
        this.f195a = context;
    }

    final MenuItem c(MenuItem menuItem) {
        if (!(menuItem instanceof b.g.e.a.b)) {
            return menuItem;
        }
        b.g.e.a.b bVar = (b.g.e.a.b) menuItem;
        if (this.f196b == null) {
            this.f196b = new b.d.g<>();
        }
        MenuItem menuItem2 = this.f196b.get(menuItem);
        if (menuItem2 != null) {
            return menuItem2;
        }
        j jVar = new j(this.f195a, bVar);
        this.f196b.put(bVar, jVar);
        return jVar;
    }

    final SubMenu d(SubMenu subMenu) {
        if (!(subMenu instanceof b.g.e.a.c)) {
            return subMenu;
        }
        b.g.e.a.c cVar = (b.g.e.a.c) subMenu;
        if (this.f197c == null) {
            this.f197c = new b.d.g<>();
        }
        SubMenu subMenu2 = this.f197c.get(cVar);
        if (subMenu2 != null) {
            return subMenu2;
        }
        s sVar = new s(this.f195a, cVar);
        this.f197c.put(cVar, sVar);
        return sVar;
    }

    final void e() {
        b.d.g<b.g.e.a.b, MenuItem> gVar = this.f196b;
        if (gVar != null) {
            gVar.clear();
        }
        b.d.g<b.g.e.a.c, SubMenu> gVar2 = this.f197c;
        if (gVar2 != null) {
            gVar2.clear();
        }
    }

    final void f(int i) {
        if (this.f196b == null) {
            return;
        }
        int i2 = 0;
        while (i2 < this.f196b.size()) {
            if (this.f196b.i(i2).getGroupId() == i) {
                this.f196b.k(i2);
                i2--;
            }
            i2++;
        }
    }

    final void g(int i) {
        if (this.f196b == null) {
            return;
        }
        for (int i2 = 0; i2 < this.f196b.size(); i2++) {
            if (this.f196b.i(i2).getItemId() == i) {
                this.f196b.k(i2);
                return;
            }
        }
    }
}
