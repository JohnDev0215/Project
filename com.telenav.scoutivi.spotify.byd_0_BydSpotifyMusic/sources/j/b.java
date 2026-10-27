package j;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;
import n.g;

/* JADX INFO: compiled from: BaseMenuWrapper.java */
/* JADX INFO: loaded from: classes.dex */
abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Context f7091a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private g<b0.b, MenuItem> f7092b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private g<b0.c, SubMenu> f7093c;

    b(Context context) {
        this.f7091a = context;
    }

    final MenuItem c(MenuItem menuItem) {
        if (!(menuItem instanceof b0.b)) {
            return menuItem;
        }
        b0.b bVar = (b0.b) menuItem;
        if (this.f7092b == null) {
            this.f7092b = new g<>();
        }
        MenuItem menuItem2 = this.f7092b.get(menuItem);
        if (menuItem2 != null) {
            return menuItem2;
        }
        c cVar = new c(this.f7091a, bVar);
        this.f7092b.put(bVar, cVar);
        return cVar;
    }

    final SubMenu d(SubMenu subMenu) {
        if (!(subMenu instanceof b0.c)) {
            return subMenu;
        }
        b0.c cVar = (b0.c) subMenu;
        if (this.f7093c == null) {
            this.f7093c = new g<>();
        }
        SubMenu subMenu2 = this.f7093c.get(cVar);
        if (subMenu2 != null) {
            return subMenu2;
        }
        f fVar = new f(this.f7091a, cVar);
        this.f7093c.put(cVar, fVar);
        return fVar;
    }

    final void e() {
        g<b0.b, MenuItem> gVar = this.f7092b;
        if (gVar != null) {
            gVar.clear();
        }
        g<b0.c, SubMenu> gVar2 = this.f7093c;
        if (gVar2 != null) {
            gVar2.clear();
        }
    }

    final void f(int i7) {
        if (this.f7092b == null) {
            return;
        }
        int i8 = 0;
        while (i8 < this.f7092b.size()) {
            if (this.f7092b.k(i8).getGroupId() == i7) {
                this.f7092b.m(i8);
                i8--;
            }
            i8++;
        }
    }

    final void g(int i7) {
        if (this.f7092b == null) {
            return;
        }
        for (int i8 = 0; i8 < this.f7092b.size(); i8++) {
            if (this.f7092b.k(i8).getItemId() == i7) {
                this.f7092b.m(i8);
                return;
            }
        }
    }
}
