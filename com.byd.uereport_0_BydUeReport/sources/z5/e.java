package z5;

import android.content.Context;
import android.view.SubMenu;

/* JADX INFO: compiled from: NavigationMenu.java */
/* JADX INFO: loaded from: classes2.dex */
public class e extends q.g {
    public e(Context context) {
        super(context);
    }

    @Override // q.g, android.view.Menu
    public SubMenu addSubMenu(int i8, int i9, int i10, CharSequence charSequence) {
        q.i iVar = (q.i) a(i8, i9, i10, charSequence);
        g gVar = new g(w(), this, iVar);
        iVar.w(gVar);
        return gVar;
    }
}
