package defpackage;

import android.content.Context;
import android.view.SubMenu;

/* JADX INFO: compiled from: NavigationMenu.java */
/* JADX INFO: loaded from: classes.dex */
public class dj extends p1 {
    public dj(Context context) {
        super(context);
    }

    @Override // defpackage.p1, android.view.Menu
    public SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        r1 r1Var = (r1) a(i, i2, i3, charSequence);
        fj fjVar = new fj(w(), this, r1Var);
        r1Var.w(fjVar);
        return fjVar;
    }
}
