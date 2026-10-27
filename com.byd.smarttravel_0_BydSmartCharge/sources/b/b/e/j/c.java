package b.b.e.j;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;

/* JADX INFO: compiled from: BaseMenuWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f685a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b.e.g<b.g.d.a.b, MenuItem> f686b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b.e.g<b.g.d.a.c, SubMenu> f687c;

    public c(Context context) {
        this.f685a = context;
    }

    public final MenuItem c(MenuItem menuItem) {
        if (!(menuItem instanceof b.g.d.a.b)) {
            return menuItem;
        }
        b.g.d.a.b bVar = (b.g.d.a.b) menuItem;
        if (this.f686b == null) {
            this.f686b = new b.e.g<>();
        }
        MenuItem menuItem2 = this.f686b.get(menuItem);
        if (menuItem2 != null) {
            return menuItem2;
        }
        j jVar = new j(this.f685a, bVar);
        this.f686b.put(bVar, jVar);
        return jVar;
    }

    public final SubMenu d(SubMenu subMenu) {
        if (!(subMenu instanceof b.g.d.a.c)) {
            return subMenu;
        }
        b.g.d.a.c cVar = (b.g.d.a.c) subMenu;
        if (this.f687c == null) {
            this.f687c = new b.e.g<>();
        }
        SubMenu subMenu2 = this.f687c.get(cVar);
        if (subMenu2 != null) {
            return subMenu2;
        }
        s sVar = new s(this.f685a, cVar);
        this.f687c.put(cVar, sVar);
        return sVar;
    }

    public final void e() {
        b.e.g<b.g.d.a.b, MenuItem> gVar = this.f686b;
        if (gVar != null) {
            gVar.clear();
        }
        b.e.g<b.g.d.a.c, SubMenu> gVar2 = this.f687c;
        if (gVar2 != null) {
            gVar2.clear();
        }
    }

    public final void f(int i) {
        if (this.f686b == null) {
            return;
        }
        int i2 = 0;
        while (i2 < this.f686b.size()) {
            if (this.f686b.i(i2).getGroupId() == i) {
                this.f686b.k(i2);
                i2--;
            }
            i2++;
        }
    }

    public final void g(int i) {
        if (this.f686b == null) {
            return;
        }
        for (int i2 = 0; i2 < this.f686b.size(); i2++) {
            if (this.f686b.i(i2).getItemId() == i) {
                this.f686b.k(i2);
                return;
            }
        }
    }
}
