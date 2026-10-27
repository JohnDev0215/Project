package o0;

import android.graphics.Rect;
import android.os.Build;
import android.view.Gravity;

/* JADX INFO: compiled from: GravityCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class d {
    public static void a(int i8, int i9, int i10, Rect rect, Rect rect2, int i11) {
        if (Build.VERSION.SDK_INT >= 17) {
            Gravity.apply(i8, i9, i10, rect, rect2, i11);
        } else {
            Gravity.apply(i8, i9, i10, rect, rect2);
        }
    }

    public static int b(int i8, int i9) {
        return Build.VERSION.SDK_INT >= 17 ? Gravity.getAbsoluteGravity(i8, i9) : i8 & (-8388609);
    }
}
