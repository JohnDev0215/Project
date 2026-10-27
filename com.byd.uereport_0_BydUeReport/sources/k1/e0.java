package k1;

import android.annotation.SuppressLint;
import android.view.View;

/* JADX INFO: compiled from: ViewUtilsApi22.java */
/* JADX INFO: loaded from: classes.dex */
public class e0 extends d0 {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static boolean f5745h = true;

    @Override // k1.h0
    @SuppressLint({"NewApi"})
    public void e(View view, int i8, int i9, int i10, int i11) {
        if (f5745h) {
            try {
                view.setLeftTopRightBottom(i8, i9, i10, i11);
            } catch (NoSuchMethodError unused) {
                f5745h = false;
            }
        }
    }
}
