package defpackage;

import android.view.View;
import com.google.android.material.bottomappbar.BottomAppBar;
import defpackage.om3;
import defpackage.yn3;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: ViewUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class xn3 implements yn3.b {
    public final /* synthetic */ boolean a;
    public final /* synthetic */ boolean b;
    public final /* synthetic */ boolean c;
    public final /* synthetic */ yn3.b d;

    public xn3(boolean z, boolean z2, boolean z3, BottomAppBar.c cVar) {
        this.a = z;
        this.b = z2;
        this.c = z3;
        this.d = cVar;
    }

    @Override // yn3.b
    public final pt3 a(View view, pt3 pt3Var, yn3.c cVar) {
        if (this.a) {
            cVar.d = pt3Var.b() + cVar.d;
        }
        boolean zD = yn3.d(view);
        if (this.b) {
            if (zD) {
                cVar.c = pt3Var.c() + cVar.c;
            } else {
                cVar.a = pt3Var.c() + cVar.a;
            }
        }
        if (this.c) {
            if (zD) {
                cVar.a = pt3Var.d() + cVar.a;
            } else {
                cVar.c = pt3Var.d() + cVar.c;
            }
        }
        int i = cVar.a;
        int i2 = cVar.b;
        int i3 = cVar.c;
        int i4 = cVar.d;
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.e.i(view, i, i2, i3, i4);
        yn3.b bVar = this.d;
        return bVar != null ? bVar.a(view, pt3Var, cVar) : pt3Var;
    }
}
