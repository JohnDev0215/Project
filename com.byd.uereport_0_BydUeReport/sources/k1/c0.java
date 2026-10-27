package k1;

import android.annotation.SuppressLint;
import android.view.View;

/* JADX INFO: compiled from: ViewUtilsApi19.java */
/* JADX INFO: loaded from: classes.dex */
public class c0 extends h0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static boolean f5724e = true;

    @Override // k1.h0
    public void a(View view) {
    }

    @Override // k1.h0
    @SuppressLint({"NewApi"})
    public float c(View view) {
        if (f5724e) {
            try {
                return view.getTransitionAlpha();
            } catch (NoSuchMethodError unused) {
                f5724e = false;
            }
        }
        return view.getAlpha();
    }

    @Override // k1.h0
    public void d(View view) {
    }

    @Override // k1.h0
    @SuppressLint({"NewApi"})
    public void f(View view, float f8) {
        if (f5724e) {
            try {
                view.setTransitionAlpha(f8);
                return;
            } catch (NoSuchMethodError unused) {
                f5724e = false;
            }
        }
        view.setAlpha(f8);
    }
}
