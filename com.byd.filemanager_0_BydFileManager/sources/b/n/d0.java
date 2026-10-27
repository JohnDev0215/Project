package b.n;

import android.annotation.SuppressLint;
import android.view.View;

/* JADX INFO: compiled from: ViewUtilsApi19.java */
/* JADX INFO: loaded from: classes.dex */
class d0 extends i0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static boolean f2114e = true;

    d0() {
    }

    @Override // b.n.i0
    public void a(View view) {
    }

    @Override // b.n.i0
    @SuppressLint({"NewApi"})
    public float c(View view) {
        if (f2114e) {
            try {
                return view.getTransitionAlpha();
            } catch (NoSuchMethodError unused) {
                f2114e = false;
            }
        }
        return view.getAlpha();
    }

    @Override // b.n.i0
    public void d(View view) {
    }

    @Override // b.n.i0
    @SuppressLint({"NewApi"})
    public void f(View view, float f) {
        if (f2114e) {
            try {
                view.setTransitionAlpha(f);
                return;
            } catch (NoSuchMethodError unused) {
                f2114e = false;
            }
        }
        view.setAlpha(f);
    }
}
