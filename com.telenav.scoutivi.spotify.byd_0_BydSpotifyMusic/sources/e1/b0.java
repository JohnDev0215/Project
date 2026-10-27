package e1;

import android.annotation.SuppressLint;
import android.view.View;

/* JADX INFO: compiled from: ViewUtilsApi22.java */
/* JADX INFO: loaded from: classes.dex */
class b0 extends a0 {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static boolean f6046f = true;

    b0() {
    }

    @Override // e1.e0
    @SuppressLint({"NewApi"})
    public void d(View view, int i7, int i8, int i9, int i10) {
        if (f6046f) {
            try {
                view.setLeftTopRightBottom(i7, i8, i9, i10);
            } catch (NoSuchMethodError unused) {
                f6046f = false;
            }
        }
    }
}
