package y0;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;

/* JADX INFO: compiled from: ViewUtilsApi23.java */
/* JADX INFO: loaded from: classes.dex */
public class f0 extends e0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static boolean f5902i = true;

    @Override // y0.h0
    @SuppressLint({"NewApi"})
    public void g(View view, int i3) {
        if (Build.VERSION.SDK_INT == 28) {
            super.g(view, i3);
        } else if (f5902i) {
            try {
                view.setTransitionVisibility(i3);
            } catch (NoSuchMethodError unused) {
                f5902i = false;
            }
        }
    }
}
