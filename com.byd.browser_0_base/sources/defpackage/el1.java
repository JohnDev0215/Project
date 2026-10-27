package defpackage;

import android.view.View;
import com.google.android.material.navigationrail.NavigationRailView;
import defpackage.om3;
import defpackage.yn3;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: NavigationRailView.java */
/* JADX INFO: loaded from: classes.dex */
public final class el1 implements yn3.b {
    public final /* synthetic */ NavigationRailView a;

    public el1(NavigationRailView navigationRailView) {
        this.a = navigationRailView;
    }

    @Override // yn3.b
    public final pt3 a(View view, pt3 pt3Var, yn3.c cVar) {
        boolean zA;
        boolean zA2;
        NavigationRailView navigationRailView = this.a;
        Boolean bool = navigationRailView.m;
        if (bool != null) {
            zA = bool.booleanValue();
        } else {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            zA = om3.d.a(navigationRailView);
        }
        if (zA) {
            cVar.b += pt3Var.a(7).b;
        }
        Boolean bool2 = navigationRailView.n;
        if (bool2 != null) {
            zA2 = bool2.booleanValue();
        } else {
            WeakHashMap<View, in3> weakHashMap2 = om3.a;
            zA2 = om3.d.a(navigationRailView);
        }
        if (zA2) {
            cVar.d += pt3Var.a(7).d;
        }
        WeakHashMap<View, in3> weakHashMap3 = om3.a;
        boolean z = om3.e.c(view) == 1;
        int iC = pt3Var.c();
        int iD = pt3Var.d();
        int i = cVar.a;
        if (z) {
            iC = iD;
        }
        int i2 = i + iC;
        cVar.a = i2;
        om3.e.i(view, i2, cVar.b, cVar.c, cVar.d);
        return pt3Var;
    }
}
