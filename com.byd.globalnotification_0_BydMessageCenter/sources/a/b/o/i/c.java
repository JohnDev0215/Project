package a.b.o.i;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;

/* JADX INFO: loaded from: classes.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f144a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public a.e.h<a.h.f.a.b, MenuItem> f145b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public a.e.h<a.h.f.a.c, SubMenu> f146c;

    public c(Context context) {
        this.f144a = context;
    }

    public final MenuItem c(MenuItem menuItem) {
        if (!(menuItem instanceof a.h.f.a.b)) {
            return menuItem;
        }
        a.h.f.a.b bVar = (a.h.f.a.b) menuItem;
        if (this.f145b == null) {
            this.f145b = new a.e.h<>();
        }
        MenuItem orDefault = this.f145b.getOrDefault(menuItem, null);
        if (orDefault != null) {
            return orDefault;
        }
        j jVar = new j(this.f144a, bVar);
        this.f145b.put(bVar, jVar);
        return jVar;
    }

    public final SubMenu d(SubMenu subMenu) {
        if (!(subMenu instanceof a.h.f.a.c)) {
            return subMenu;
        }
        a.h.f.a.c cVar = (a.h.f.a.c) subMenu;
        if (this.f146c == null) {
            this.f146c = new a.e.h<>();
        }
        SubMenu subMenu2 = this.f146c.get(cVar);
        if (subMenu2 != null) {
            return subMenu2;
        }
        s sVar = new s(this.f144a, cVar);
        this.f146c.put(cVar, sVar);
        return sVar;
    }
}
