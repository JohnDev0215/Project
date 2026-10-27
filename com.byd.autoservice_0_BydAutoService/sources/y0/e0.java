package y0;

import android.annotation.SuppressLint;
import android.view.View;

/* JADX INFO: compiled from: ViewUtilsApi22.java */
/* JADX INFO: loaded from: classes.dex */
public class e0 extends d0 {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static boolean f5901h = true;

    @Override // y0.h0
    @SuppressLint({"NewApi"})
    public void e(View view, int i3, int i4, int i5, int i6) {
        if (f5901h) {
            try {
                view.setLeftTopRightBottom(i3, i4, i5, i6);
            } catch (NoSuchMethodError unused) {
                f5901h = false;
            }
        }
    }
}
