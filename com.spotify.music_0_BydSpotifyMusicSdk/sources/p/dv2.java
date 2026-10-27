package p;

import android.graphics.Rect;
import android.os.Build;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public abstract class dv2 {
    public static final iv2 a;
    public static final sq b;

    static {
        if (Build.VERSION.SDK_INT >= 29) {
            a = new jv2();
        } else {
            a = new iv2();
        }
        b = new sq(Float.class, "translationAlpha", 5);
        new sq(Rect.class, "clipBounds", 6);
    }

    public static void a(View view, int i, int i2, int i3, int i4) {
        a.P(view, i, i2, i3, i4);
    }
}
