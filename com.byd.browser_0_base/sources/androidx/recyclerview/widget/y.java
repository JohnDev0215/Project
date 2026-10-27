package androidx.recyclerview.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: ScrollbarHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class y {
    public static int a(RecyclerView.w wVar, s sVar, View view, View view2, RecyclerView.m mVar, boolean z) {
        if (mVar.A() == 0 || wVar.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z) {
            return Math.abs(RecyclerView.m.K(view) - RecyclerView.m.K(view2)) + 1;
        }
        return Math.min(sVar.l(), sVar.b(view2) - sVar.e(view));
    }

    public static int b(RecyclerView.w wVar, s sVar, View view, View view2, RecyclerView.m mVar, boolean z, boolean z2) {
        if (mVar.A() == 0 || wVar.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        int iMax = z2 ? Math.max(0, (wVar.b() - Math.max(RecyclerView.m.K(view), RecyclerView.m.K(view2))) - 1) : Math.max(0, Math.min(RecyclerView.m.K(view), RecyclerView.m.K(view2)));
        if (z) {
            return Math.round((iMax * (Math.abs(sVar.b(view2) - sVar.e(view)) / (Math.abs(RecyclerView.m.K(view) - RecyclerView.m.K(view2)) + 1))) + (sVar.k() - sVar.e(view)));
        }
        return iMax;
    }

    public static int c(RecyclerView.w wVar, s sVar, View view, View view2, RecyclerView.m mVar, boolean z) {
        if (mVar.A() == 0 || wVar.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z) {
            return wVar.b();
        }
        return (int) (((sVar.b(view2) - sVar.e(view)) / (Math.abs(RecyclerView.m.K(view) - RecyclerView.m.K(view2)) + 1)) * wVar.b());
    }
}
