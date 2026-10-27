package M4;

import F1.H;
import K.L0;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import t1.AbstractC2425a;

/* JADX INFO: loaded from: classes.dex */
public abstract class a extends AbstractC2425a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public L0 f4196a;

    @Override // t1.AbstractC2425a
    public boolean g(CoordinatorLayout coordinatorLayout, View view, int i7) {
        r(coordinatorLayout, view, i7);
        if (this.f4196a == null) {
            this.f4196a = new L0(view);
        }
        L0 l02 = this.f4196a;
        View view2 = (View) l02.o;
        l02.f3180m = view2.getTop();
        l02.f3181n = view2.getLeft();
        L0 l03 = this.f4196a;
        View view3 = (View) l03.o;
        int top = 0 - (view3.getTop() - l03.f3180m);
        int[] iArr = H.f2303a;
        view3.offsetTopAndBottom(top);
        view3.offsetLeftAndRight(0 - (view3.getLeft() - l03.f3181n));
        return true;
    }

    public void r(CoordinatorLayout coordinatorLayout, View view, int i7) {
        coordinatorLayout.q(view, i7);
    }
}
