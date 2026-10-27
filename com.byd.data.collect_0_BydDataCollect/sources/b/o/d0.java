package b.o;

import android.annotation.SuppressLint;
import android.view.View;

/* JADX INFO: compiled from: ViewUtilsApi19.java */
/* JADX INFO: loaded from: classes.dex */
class d0 extends i0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static boolean f2836e = true;

    d0() {
    }

    @Override // b.o.i0
    public void a(View view) {
    }

    @Override // b.o.i0
    @SuppressLint({"NewApi"})
    public float c(View view) {
        if (f2836e) {
            try {
                return view.getTransitionAlpha();
            } catch (NoSuchMethodError unused) {
                f2836e = false;
            }
        }
        return view.getAlpha();
    }

    @Override // b.o.i0
    public void d(View view) {
    }

    @Override // b.o.i0
    @SuppressLint({"NewApi"})
    public void f(View view, float f2) {
        if (f2836e) {
            try {
                view.setTransitionAlpha(f2);
                return;
            } catch (NoSuchMethodError unused) {
                f2836e = false;
            }
        }
        view.setAlpha(f2);
    }
}
