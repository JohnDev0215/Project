package t1;

import android.os.Parcelable;
import android.view.MotionEvent;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* JADX INFO: renamed from: t1.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2425a {
    public boolean a(View view) {
        return false;
    }

    public void b(View view) {
    }

    public void c(C2428d c2428d) {
    }

    public boolean d(View view, View view2) {
        return false;
    }

    public void e() {
    }

    public boolean f(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        return false;
    }

    public abstract boolean g(CoordinatorLayout coordinatorLayout, View view, int i7);

    public boolean h(CoordinatorLayout coordinatorLayout, View view) {
        return false;
    }

    public boolean i(View view) {
        return false;
    }

    public void j(CoordinatorLayout coordinatorLayout, View view, View view2, int i7, int i10, int[] iArr, int i11) {
    }

    public void k(CoordinatorLayout coordinatorLayout, View view, int i7, int i10, int i11, int[] iArr) {
        iArr[0] = iArr[0] + i10;
        iArr[1] = iArr[1] + i11;
    }

    public void l(CoordinatorLayout coordinatorLayout, View view) {
    }

    public void m(View view, Parcelable parcelable) {
    }

    public Parcelable n(View view) {
        return View.BaseSavedState.EMPTY_STATE;
    }

    public boolean o(View view, int i7, int i10) {
        return false;
    }

    public void p(View view, View view2, int i7) {
    }

    public boolean q(View view, MotionEvent motionEvent) {
        return false;
    }
}
