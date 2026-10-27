package b.b.p.j;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;

/* JADX INFO: compiled from: BaseMenuWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f919a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b.d.g<b.f.h.a.b, MenuItem> f920b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b.d.g<b.f.h.a.c, SubMenu> f921c;

    public c(Context context) {
        this.f919a = context;
    }

    public final MenuItem c(MenuItem menuItem) {
        if (!(menuItem instanceof b.f.h.a.b)) {
            return menuItem;
        }
        b.f.h.a.b bVar = (b.f.h.a.b) menuItem;
        if (this.f920b == null) {
            this.f920b = new b.d.g<>();
        }
        MenuItem menuItem2 = this.f920b.get(menuItem);
        if (menuItem2 != null) {
            return menuItem2;
        }
        j jVar = new j(this.f919a, bVar);
        this.f920b.put(bVar, jVar);
        return jVar;
    }

    public final SubMenu d(SubMenu subMenu) {
        if (!(subMenu instanceof b.f.h.a.c)) {
            return subMenu;
        }
        b.f.h.a.c cVar = (b.f.h.a.c) subMenu;
        if (this.f921c == null) {
            this.f921c = new b.d.g<>();
        }
        SubMenu subMenu2 = this.f921c.get(cVar);
        if (subMenu2 != null) {
            return subMenu2;
        }
        s sVar = new s(this.f919a, cVar);
        this.f921c.put(cVar, sVar);
        return sVar;
    }

    public final void e() {
        b.d.g<b.f.h.a.b, MenuItem> gVar = this.f920b;
        if (gVar != null) {
            gVar.clear();
        }
        b.d.g<b.f.h.a.c, SubMenu> gVar2 = this.f921c;
        if (gVar2 != null) {
            gVar2.clear();
        }
    }

    public final void f(int i2) {
        if (this.f920b == null) {
            return;
        }
        int i3 = 0;
        while (i3 < this.f920b.size()) {
            if (this.f920b.i(i3).getGroupId() == i2) {
                this.f920b.j(i3);
                i3--;
            }
            i3++;
        }
    }

    public final void g(int i2) {
        if (this.f920b == null) {
            return;
        }
        for (int i3 = 0; i3 < this.f920b.size(); i3++) {
            if (this.f920b.i(i3).getItemId() == i2) {
                this.f920b.j(i3);
                return;
            }
        }
    }
}
