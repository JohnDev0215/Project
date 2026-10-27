package androidx.recyclerview.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: ScrollbarHelper.java */
/* JADX INFO: loaded from: classes.dex */
class n {
    static int a(RecyclerView.z zVar, k kVar, View view, View view2, RecyclerView.o oVar, boolean z) {
        if (oVar.T() == 0 || zVar.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z) {
            return Math.abs(oVar.n0(view) - oVar.n0(view2)) + 1;
        }
        return Math.min(kVar.n(), kVar.d(view2) - kVar.g(view));
    }

    static int b(RecyclerView.z zVar, k kVar, View view, View view2, RecyclerView.o oVar, boolean z, boolean z2) {
        if (oVar.T() == 0 || zVar.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        int iMax = z2 ? Math.max(0, (zVar.b() - Math.max(oVar.n0(view), oVar.n0(view2))) - 1) : Math.max(0, Math.min(oVar.n0(view), oVar.n0(view2)));
        if (z) {
            return Math.round((iMax * (Math.abs(kVar.d(view2) - kVar.g(view)) / (Math.abs(oVar.n0(view) - oVar.n0(view2)) + 1))) + (kVar.m() - kVar.g(view)));
        }
        return iMax;
    }

    static int c(RecyclerView.z zVar, k kVar, View view, View view2, RecyclerView.o oVar, boolean z) {
        if (oVar.T() == 0 || zVar.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z) {
            return zVar.b();
        }
        return (int) (((kVar.d(view2) - kVar.g(view)) / (Math.abs(oVar.n0(view) - oVar.n0(view2)) + 1)) * zVar.b());
    }
}
