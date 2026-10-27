package c.b.a.a.q;

import android.content.Context;
import android.view.SubMenu;

/* JADX INFO: compiled from: NavigationMenu.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends b.b.e.j.g {
    public e(Context context) {
        super(context);
    }

    @Override // b.b.e.j.g, android.view.Menu
    public SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        b.b.e.j.i iVar = (b.b.e.j.i) a(i, i2, i3, charSequence);
        g gVar = new g(w(), this, iVar);
        iVar.x(gVar);
        return gVar;
    }
}
