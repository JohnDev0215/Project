package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Context f216a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private b.a.g<androidx.core.b.a.b, MenuItem> f217b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private b.a.g<androidx.core.b.a.c, SubMenu> f218c;

    c(Context context) {
        this.f216a = context;
    }

    final MenuItem c(MenuItem menuItem) {
        if (!(menuItem instanceof androidx.core.b.a.b)) {
            return menuItem;
        }
        androidx.core.b.a.b bVar = (androidx.core.b.a.b) menuItem;
        if (this.f217b == null) {
            this.f217b = new b.a.g<>();
        }
        MenuItem menuItem2 = this.f217b.get(menuItem);
        if (menuItem2 != null) {
            return menuItem2;
        }
        i iVar = new i(this.f216a, bVar);
        this.f217b.put(bVar, iVar);
        return iVar;
    }

    final SubMenu d(SubMenu subMenu) {
        if (!(subMenu instanceof androidx.core.b.a.c)) {
            return subMenu;
        }
        androidx.core.b.a.c cVar = (androidx.core.b.a.c) subMenu;
        if (this.f218c == null) {
            this.f218c = new b.a.g<>();
        }
        SubMenu subMenu2 = this.f218c.get(cVar);
        if (subMenu2 != null) {
            return subMenu2;
        }
        r rVar = new r(this.f216a, cVar);
        this.f218c.put(cVar, rVar);
        return rVar;
    }

    final void e() {
        b.a.g<androidx.core.b.a.b, MenuItem> gVar = this.f217b;
        if (gVar != null) {
            gVar.clear();
        }
        b.a.g<androidx.core.b.a.c, SubMenu> gVar2 = this.f218c;
        if (gVar2 != null) {
            gVar2.clear();
        }
    }

    final void f(int i) {
        if (this.f217b == null) {
            return;
        }
        int i2 = 0;
        while (i2 < this.f217b.size()) {
            if (this.f217b.i(i2).getGroupId() == i) {
                this.f217b.j(i2);
                i2--;
            }
            i2++;
        }
    }

    final void g(int i) {
        if (this.f217b == null) {
            return;
        }
        for (int i2 = 0; i2 < this.f217b.size(); i2++) {
            if (this.f217b.i(i2).getItemId() == i) {
                this.f217b.j(i2);
                return;
            }
        }
    }
}
