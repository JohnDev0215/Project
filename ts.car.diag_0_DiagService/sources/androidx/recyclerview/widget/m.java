package androidx.recyclerview.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
class m {
    static int a(RecyclerView.x xVar, k kVar, View view, View view2, RecyclerView.m mVar, boolean z) {
        if (mVar.I() == 0 || xVar.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z) {
            return Math.abs(mVar.f0(view) - mVar.f0(view2)) + 1;
        }
        return Math.min(kVar.n(), kVar.d(view2) - kVar.g(view));
    }

    static int b(RecyclerView.x xVar, k kVar, View view, View view2, RecyclerView.m mVar, boolean z, boolean z2) {
        if (mVar.I() == 0 || xVar.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        int iMax = z2 ? Math.max(0, (xVar.b() - Math.max(mVar.f0(view), mVar.f0(view2))) - 1) : Math.max(0, Math.min(mVar.f0(view), mVar.f0(view2)));
        if (z) {
            return Math.round((iMax * (Math.abs(kVar.d(view2) - kVar.g(view)) / (Math.abs(mVar.f0(view) - mVar.f0(view2)) + 1))) + (kVar.m() - kVar.g(view)));
        }
        return iMax;
    }

    static int c(RecyclerView.x xVar, k kVar, View view, View view2, RecyclerView.m mVar, boolean z) {
        if (mVar.I() == 0 || xVar.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z) {
            return xVar.b();
        }
        return (int) (((kVar.d(view2) - kVar.g(view)) / (Math.abs(mVar.f0(view) - mVar.f0(view2)) + 1)) * xVar.b());
    }
}
