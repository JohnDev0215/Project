package i2;

import android.content.Context;
import android.view.SubMenu;

/* JADX INFO: compiled from: NavigationMenu.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends n.g {
    public e(Context context) {
        super(context);
    }

    @Override // n.g, android.view.Menu
    public SubMenu addSubMenu(int i3, int i4, int i5, CharSequence charSequence) {
        n.i iVar = (n.i) a(i3, i4, i5, charSequence);
        g gVar = new g(w(), this, iVar);
        iVar.w(gVar);
        return gVar;
    }
}
