package b.m;

import android.annotation.SuppressLint;
import android.view.View;

/* JADX INFO: compiled from: ViewUtilsApi19.java */
/* JADX INFO: loaded from: classes.dex */
class d0 extends i0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static boolean f2452e = true;

    d0() {
    }

    @Override // b.m.i0
    public void a(View view) {
    }

    @Override // b.m.i0
    @SuppressLint({"NewApi"})
    public float c(View view) {
        if (f2452e) {
            try {
                return view.getTransitionAlpha();
            } catch (NoSuchMethodError unused) {
                f2452e = false;
            }
        }
        return view.getAlpha();
    }

    @Override // b.m.i0
    public void d(View view) {
    }

    @Override // b.m.i0
    @SuppressLint({"NewApi"})
    public void f(View view, float f) {
        if (f2452e) {
            try {
                view.setTransitionAlpha(f);
                return;
            } catch (NoSuchMethodError unused) {
                f2452e = false;
            }
        }
        view.setAlpha(f);
    }
}
