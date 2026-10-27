package b.x;

import android.annotation.SuppressLint;
import android.view.View;

/* JADX INFO: compiled from: ViewUtilsApi19.java */
/* JADX INFO: loaded from: classes.dex */
public class d0 extends i0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static boolean f3236e = true;

    @Override // b.x.i0
    public void a(View view) {
    }

    @Override // b.x.i0
    @SuppressLint({"NewApi"})
    public float c(View view) {
        if (f3236e) {
            try {
                return view.getTransitionAlpha();
            } catch (NoSuchMethodError unused) {
                f3236e = false;
            }
        }
        return view.getAlpha();
    }

    @Override // b.x.i0
    public void d(View view) {
    }

    @Override // b.x.i0
    @SuppressLint({"NewApi"})
    public void f(View view, float f2) {
        if (f3236e) {
            try {
                view.setTransitionAlpha(f2);
                return;
            } catch (NoSuchMethodError unused) {
                f3236e = false;
            }
        }
        view.setAlpha(f2);
    }
}
