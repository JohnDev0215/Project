package y0;

import android.annotation.SuppressLint;
import android.view.View;

/* JADX INFO: compiled from: ViewUtilsApi19.java */
/* JADX INFO: loaded from: classes.dex */
public class c0 extends h0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static boolean f5880e = true;

    @Override // y0.h0
    public void a(View view) {
    }

    @Override // y0.h0
    @SuppressLint({"NewApi"})
    public float c(View view) {
        if (f5880e) {
            try {
                return view.getTransitionAlpha();
            } catch (NoSuchMethodError unused) {
                f5880e = false;
            }
        }
        return view.getAlpha();
    }

    @Override // y0.h0
    public void d(View view) {
    }

    @Override // y0.h0
    @SuppressLint({"NewApi"})
    public void f(View view, float f3) {
        if (f5880e) {
            try {
                view.setTransitionAlpha(f3);
                return;
            } catch (NoSuchMethodError unused) {
                f5880e = false;
            }
        }
        view.setAlpha(f3);
    }
}
