package p;

import android.os.Parcelable;
import android.view.MotionEvent;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* JADX INFO: loaded from: classes.dex */
public abstract class j00 {
    public boolean a(View view) {
        return false;
    }

    public void b(View view) {
    }

    public void c(m00 m00Var) {
    }

    public boolean d(View view, View view2) {
        return false;
    }

    public void e() {
    }

    public boolean f(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        return false;
    }

    public abstract boolean g(CoordinatorLayout coordinatorLayout, View view, int i);

    public boolean h(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3) {
        return false;
    }

    public boolean i(View view) {
        return false;
    }

    public void j(View view, View view2, int i, int[] iArr, int i2) {
    }

    public void k(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3, int[] iArr) {
        iArr[0] = iArr[0] + i2;
        iArr[1] = iArr[1] + i3;
    }

    public void l(CoordinatorLayout coordinatorLayout, View view) {
    }

    public void m(View view, Parcelable parcelable) {
    }

    public Parcelable n(View view) {
        return View.BaseSavedState.EMPTY_STATE;
    }

    public boolean o(View view, int i, int i2) {
        return false;
    }

    public void p(View view, View view2, int i) {
    }

    public boolean q(View view, MotionEvent motionEvent) {
        return false;
    }
}
