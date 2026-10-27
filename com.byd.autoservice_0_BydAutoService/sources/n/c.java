package n;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;

/* JADX INFO: compiled from: BaseMenuWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f3878a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public s.g<f0.b, MenuItem> f3879b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public s.g<f0.c, SubMenu> f3880c;

    public c(Context context) {
        this.f3878a = context;
    }

    public final MenuItem c(MenuItem menuItem) {
        if (!(menuItem instanceof f0.b)) {
            return menuItem;
        }
        f0.b bVar = (f0.b) menuItem;
        if (this.f3879b == null) {
            this.f3879b = new s.g<>();
        }
        MenuItem menuItem2 = this.f3879b.get(menuItem);
        if (menuItem2 != null) {
            return menuItem2;
        }
        j jVar = new j(this.f3878a, bVar);
        this.f3879b.put(bVar, jVar);
        return jVar;
    }

    public final SubMenu d(SubMenu subMenu) {
        if (!(subMenu instanceof f0.c)) {
            return subMenu;
        }
        f0.c cVar = (f0.c) subMenu;
        if (this.f3880c == null) {
            this.f3880c = new s.g<>();
        }
        SubMenu subMenu2 = this.f3880c.get(cVar);
        if (subMenu2 != null) {
            return subMenu2;
        }
        s sVar = new s(this.f3878a, cVar);
        this.f3880c.put(cVar, sVar);
        return sVar;
    }

    public final void e() {
        s.g<f0.b, MenuItem> gVar = this.f3879b;
        if (gVar != null) {
            gVar.clear();
        }
        s.g<f0.c, SubMenu> gVar2 = this.f3880c;
        if (gVar2 != null) {
            gVar2.clear();
        }
    }

    public final void f(int i3) {
        if (this.f3879b == null) {
            return;
        }
        int i4 = 0;
        while (i4 < this.f3879b.size()) {
            if (this.f3879b.i(i4).getGroupId() == i3) {
                this.f3879b.k(i4);
                i4--;
            }
            i4++;
        }
    }

    public final void g(int i3) {
        if (this.f3879b == null) {
            return;
        }
        for (int i4 = 0; i4 < this.f3879b.size(); i4++) {
            if (this.f3879b.i(i4).getItemId() == i3) {
                this.f3879b.k(i4);
                return;
            }
        }
    }
}
