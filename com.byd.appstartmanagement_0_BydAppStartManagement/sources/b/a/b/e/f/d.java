package b.a.b.e.f;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.widget.LinearLayout;

/* JADX INFO: compiled from: RoundHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class d {
    public static GradientDrawable a(int i, int i2, int i3) {
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, new int[]{i, i});
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadius(i2);
        gradientDrawable.setSize(i3, 0);
        return gradientDrawable;
    }

    public static void b(LinearLayout linearLayout, int i, int i2, int i3, Drawable drawable) {
        GradientDrawable gradientDrawableA;
        if (drawable == null) {
            gradientDrawableA = a(i, i2, i3);
        } else if (drawable instanceof GradientDrawable) {
            gradientDrawableA = (GradientDrawable) drawable;
            gradientDrawableA.setSize(i3, 0);
            gradientDrawableA.setCornerRadius(i2);
        } else {
            linearLayout.setDividerDrawable(drawable);
            gradientDrawableA = null;
        }
        linearLayout.setDividerDrawable(gradientDrawableA);
    }
}
