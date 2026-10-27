package defpackage;

import android.view.View;
import defpackage.om3;
import defpackage.yn3;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: BottomNavigationView.java */
/* JADX INFO: loaded from: classes.dex */
public final class ei implements yn3.b {
    @Override // yn3.b
    public final pt3 a(View view, pt3 pt3Var, yn3.c cVar) {
        cVar.d = pt3Var.b() + cVar.d;
        WeakHashMap<View, in3> weakHashMap = om3.a;
        boolean z = om3.e.c(view) == 1;
        int iC = pt3Var.c();
        int iD = pt3Var.d();
        int i = cVar.a + (z ? iD : iC);
        cVar.a = i;
        int i2 = cVar.c;
        if (!z) {
            iC = iD;
        }
        int i3 = i2 + iC;
        cVar.c = i3;
        om3.e.i(view, i, cVar.b, i3, cVar.d);
        return pt3Var;
    }
}
