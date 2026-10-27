package w0;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: ScrollbarHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class m {
    public static int a(RecyclerView.z zVar, j jVar, View view, View view2, RecyclerView.o oVar, boolean z3) {
        if (oVar.J() == 0 || zVar.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z3) {
            return Math.abs(oVar.h0(view) - oVar.h0(view2)) + 1;
        }
        return Math.min(jVar.n(), jVar.d(view2) - jVar.g(view));
    }

    public static int b(RecyclerView.z zVar, j jVar, View view, View view2, RecyclerView.o oVar, boolean z3, boolean z4) {
        if (oVar.J() == 0 || zVar.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        int iMax = z4 ? Math.max(0, (zVar.b() - Math.max(oVar.h0(view), oVar.h0(view2))) - 1) : Math.max(0, Math.min(oVar.h0(view), oVar.h0(view2)));
        if (z3) {
            return Math.round((iMax * (Math.abs(jVar.d(view2) - jVar.g(view)) / (Math.abs(oVar.h0(view) - oVar.h0(view2)) + 1))) + (jVar.m() - jVar.g(view)));
        }
        return iMax;
    }

    public static int c(RecyclerView.z zVar, j jVar, View view, View view2, RecyclerView.o oVar, boolean z3) {
        if (oVar.J() == 0 || zVar.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z3) {
            return zVar.b();
        }
        return (int) (((jVar.d(view2) - jVar.g(view)) / (Math.abs(oVar.h0(view) - oVar.h0(view2)) + 1)) * zVar.b());
    }
}
