package e1;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;

/* JADX INFO: compiled from: ViewUtilsApi23.java */
/* JADX INFO: loaded from: classes.dex */
class c0 extends b0 {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static boolean f6073g = true;

    c0() {
    }

    @Override // e1.e0
    @SuppressLint({"NewApi"})
    public void f(View view, int i7) {
        if (Build.VERSION.SDK_INT == 28) {
            super.f(view, i7);
        } else if (f6073g) {
            try {
                view.setTransitionVisibility(i7);
            } catch (NoSuchMethodError unused) {
                f6073g = false;
            }
        }
    }
}
