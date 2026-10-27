package k1;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;

/* JADX INFO: compiled from: ViewUtilsApi23.java */
/* JADX INFO: loaded from: classes.dex */
public class f0 extends e0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static boolean f5746i = true;

    @Override // k1.h0
    @SuppressLint({"NewApi"})
    public void g(View view, int i8) {
        if (Build.VERSION.SDK_INT == 28) {
            super.g(view, i8);
        } else if (f5746i) {
            try {
                view.setTransitionVisibility(i8);
            } catch (NoSuchMethodError unused) {
                f5746i = false;
            }
        }
    }
}
