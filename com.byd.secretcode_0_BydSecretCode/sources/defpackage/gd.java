package defpackage;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: ScrollbarHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class gd {
    public static int a(RecyclerView.z zVar, dd ddVar, View view, View view2, RecyclerView.o oVar, boolean z) {
        if (oVar.J() == 0 || zVar.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z) {
            return Math.abs(oVar.h0(view) - oVar.h0(view2)) + 1;
        }
        return Math.min(ddVar.n(), ddVar.d(view2) - ddVar.g(view));
    }

    public static int b(RecyclerView.z zVar, dd ddVar, View view, View view2, RecyclerView.o oVar, boolean z, boolean z2) {
        if (oVar.J() == 0 || zVar.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        int iMax = z2 ? Math.max(0, (zVar.b() - Math.max(oVar.h0(view), oVar.h0(view2))) - 1) : Math.max(0, Math.min(oVar.h0(view), oVar.h0(view2)));
        if (z) {
            return Math.round((iMax * (Math.abs(ddVar.d(view2) - ddVar.g(view)) / (Math.abs(oVar.h0(view) - oVar.h0(view2)) + 1))) + (ddVar.m() - ddVar.g(view)));
        }
        return iMax;
    }

    public static int c(RecyclerView.z zVar, dd ddVar, View view, View view2, RecyclerView.o oVar, boolean z) {
        if (oVar.J() == 0 || zVar.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z) {
            return zVar.b();
        }
        return (int) (((ddVar.d(view2) - ddVar.g(view)) / (Math.abs(oVar.h0(view) - oVar.h0(view2)) + 1)) * zVar.b());
    }
}
