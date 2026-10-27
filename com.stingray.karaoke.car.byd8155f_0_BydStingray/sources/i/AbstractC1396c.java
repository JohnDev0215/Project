package i;

import F0.C0225r0;
import android.R;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.a0;
import h.n;
import h6.q;

/* JADX INFO: renamed from: i.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1396c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ViewGroup.LayoutParams f15639a = new ViewGroup.LayoutParams(-2, -2);

    public static void a(n nVar, b0.b bVar) {
        View childAt = ((ViewGroup) nVar.getWindow().getDecorView().findViewById(R.id.content)).getChildAt(0);
        C0225r0 c0225r0 = childAt instanceof C0225r0 ? (C0225r0) childAt : null;
        if (c0225r0 != null) {
            c0225r0.setParentCompositionContext(null);
            c0225r0.setContent(bVar);
            return;
        }
        C0225r0 c0225r02 = new C0225r0(nVar);
        c0225r02.setParentCompositionContext(null);
        c0225r02.setContent(bVar);
        View decorView = nVar.getWindow().getDecorView();
        if (a0.f(decorView) == null) {
            a0.j(decorView, nVar);
        }
        if (a0.g(decorView) == null) {
            a0.k(decorView, nVar);
        }
        if (q.s(decorView) == null) {
            q.z(decorView, nVar);
        }
        nVar.setContentView(c0225r02, f15639a);
    }
}
