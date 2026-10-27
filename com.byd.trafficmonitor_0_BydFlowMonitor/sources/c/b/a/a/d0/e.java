package c.b.a.a.d0;

import android.content.Context;
import android.view.SubMenu;

/* JADX INFO: compiled from: NavigationMenu.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends b.b.p.j.g {
    public e(Context context) {
        super(context);
    }

    @Override // b.b.p.j.g, android.view.Menu
    public SubMenu addSubMenu(int i2, int i3, int i4, CharSequence charSequence) {
        b.b.p.j.i iVar = (b.b.p.j.i) a(i2, i3, i4, charSequence);
        g gVar = new g(w(), this, iVar);
        iVar.w(gVar);
        return gVar;
    }
}
