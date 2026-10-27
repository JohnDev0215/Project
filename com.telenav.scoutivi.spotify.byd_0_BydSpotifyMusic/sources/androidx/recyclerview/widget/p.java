package androidx.recyclerview.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: ScrollbarHelper.java */
/* JADX INFO: loaded from: classes.dex */
class p {
    static int a(RecyclerView.z zVar, m mVar, View view, View view2, RecyclerView.o oVar, boolean z6) {
        if (oVar.J() == 0 || zVar.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z6) {
            return Math.abs(oVar.h0(view) - oVar.h0(view2)) + 1;
        }
        return Math.min(mVar.n(), mVar.d(view2) - mVar.g(view));
    }

    static int b(RecyclerView.z zVar, m mVar, View view, View view2, RecyclerView.o oVar, boolean z6, boolean z7) {
        if (oVar.J() == 0 || zVar.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        int iMax = z7 ? Math.max(0, (zVar.b() - Math.max(oVar.h0(view), oVar.h0(view2))) - 1) : Math.max(0, Math.min(oVar.h0(view), oVar.h0(view2)));
        if (z6) {
            return Math.round((iMax * (Math.abs(mVar.d(view2) - mVar.g(view)) / (Math.abs(oVar.h0(view) - oVar.h0(view2)) + 1))) + (mVar.m() - mVar.g(view)));
        }
        return iMax;
    }

    static int c(RecyclerView.z zVar, m mVar, View view, View view2, RecyclerView.o oVar, boolean z6) {
        if (oVar.J() == 0 || zVar.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z6) {
            return zVar.b();
        }
        return (int) (((mVar.d(view2) - mVar.g(view)) / (Math.abs(oVar.h0(view) - oVar.h0(view2)) + 1)) * zVar.b());
    }
}
