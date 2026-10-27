package q;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;

/* JADX INFO: compiled from: BaseMenuWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f7077a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public v.g<i0.b, MenuItem> f7078b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public v.g<i0.c, SubMenu> f7079c;

    public c(Context context) {
        this.f7077a = context;
    }

    public final MenuItem c(MenuItem menuItem) {
        if (!(menuItem instanceof i0.b)) {
            return menuItem;
        }
        i0.b bVar = (i0.b) menuItem;
        if (this.f7078b == null) {
            this.f7078b = new v.g<>();
        }
        MenuItem menuItem2 = this.f7078b.get(menuItem);
        if (menuItem2 != null) {
            return menuItem2;
        }
        j jVar = new j(this.f7077a, bVar);
        this.f7078b.put(bVar, jVar);
        return jVar;
    }

    public final SubMenu d(SubMenu subMenu) {
        if (!(subMenu instanceof i0.c)) {
            return subMenu;
        }
        i0.c cVar = (i0.c) subMenu;
        if (this.f7079c == null) {
            this.f7079c = new v.g<>();
        }
        SubMenu subMenu2 = this.f7079c.get(cVar);
        if (subMenu2 != null) {
            return subMenu2;
        }
        s sVar = new s(this.f7077a, cVar);
        this.f7079c.put(cVar, sVar);
        return sVar;
    }

    public final void e() {
        v.g<i0.b, MenuItem> gVar = this.f7078b;
        if (gVar != null) {
            gVar.clear();
        }
        v.g<i0.c, SubMenu> gVar2 = this.f7079c;
        if (gVar2 != null) {
            gVar2.clear();
        }
    }

    public final void f(int i8) {
        if (this.f7078b == null) {
            return;
        }
        int i9 = 0;
        while (i9 < this.f7078b.size()) {
            if (this.f7078b.i(i9).getGroupId() == i8) {
                this.f7078b.k(i9);
                i9--;
            }
            i9++;
        }
    }

    public final void g(int i8) {
        if (this.f7078b == null) {
            return;
        }
        for (int i9 = 0; i9 < this.f7078b.size(); i9++) {
            if (this.f7078b.i(i9).getItemId() == i8) {
                this.f7078b.k(i9);
                return;
            }
        }
    }
}
