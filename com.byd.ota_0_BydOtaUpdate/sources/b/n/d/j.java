package b.n.d;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: ScrollbarHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class j {
    public static int a(RecyclerView.z zVar, h hVar, View view, View view2, RecyclerView.o oVar, boolean z) {
        if (oVar.I() == 0 || zVar.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z) {
            return Math.abs(oVar.f0(view) - oVar.f0(view2)) + 1;
        }
        return Math.min(hVar.n(), hVar.d(view2) - hVar.g(view));
    }

    public static int b(RecyclerView.z zVar, h hVar, View view, View view2, RecyclerView.o oVar, boolean z, boolean z2) {
        if (oVar.I() == 0 || zVar.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        int iMax = z2 ? Math.max(0, (zVar.b() - Math.max(oVar.f0(view), oVar.f0(view2))) - 1) : Math.max(0, Math.min(oVar.f0(view), oVar.f0(view2)));
        if (z) {
            return Math.round((iMax * (Math.abs(hVar.d(view2) - hVar.g(view)) / (Math.abs(oVar.f0(view) - oVar.f0(view2)) + 1))) + (hVar.m() - hVar.g(view)));
        }
        return iMax;
    }

    public static int c(RecyclerView.z zVar, h hVar, View view, View view2, RecyclerView.o oVar, boolean z) {
        if (oVar.I() == 0 || zVar.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z) {
            return zVar.b();
        }
        return (int) (((hVar.d(view2) - hVar.g(view)) / (Math.abs(oVar.f0(view) - oVar.f0(view2)) + 1)) * zVar.b());
    }
}
