package l0;

import android.graphics.Rect;
import android.os.Build;
import android.view.Gravity;

/* JADX INFO: compiled from: GravityCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class d {
    public static void a(int i3, int i4, int i5, Rect rect, Rect rect2, int i6) {
        if (Build.VERSION.SDK_INT >= 17) {
            Gravity.apply(i3, i4, i5, rect, rect2, i6);
        } else {
            Gravity.apply(i3, i4, i5, rect, rect2);
        }
    }

    public static int b(int i3, int i4) {
        return Build.VERSION.SDK_INT >= 17 ? Gravity.getAbsoluteGravity(i3, i4) : i3 & (-8388609);
    }
}
