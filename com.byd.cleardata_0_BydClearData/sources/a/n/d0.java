package a.n;

import android.annotation.SuppressLint;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
class d0 extends i0 {
    private static boolean e = true;

    d0() {
    }

    @Override // a.n.i0
    public void a(View view) {
    }

    @Override // a.n.i0
    @SuppressLint({"NewApi"})
    public float c(View view) {
        if (e) {
            try {
                return view.getTransitionAlpha();
            } catch (NoSuchMethodError unused) {
                e = false;
            }
        }
        return view.getAlpha();
    }

    @Override // a.n.i0
    public void d(View view) {
    }

    @Override // a.n.i0
    @SuppressLint({"NewApi"})
    public void f(View view, float f) {
        if (e) {
            try {
                view.setTransitionAlpha(f);
                return;
            } catch (NoSuchMethodError unused) {
                e = false;
            }
        }
        view.setAlpha(f);
    }
}
