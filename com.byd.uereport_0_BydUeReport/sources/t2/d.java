package t2;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.widget.LinearLayout;

/* JADX INFO: compiled from: RoundHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class d {
    public static GradientDrawable a(int i8, int i9, int i10) {
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, new int[]{i8, i8});
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadius(i9);
        gradientDrawable.setSize(i10, 0);
        return gradientDrawable;
    }

    public static void b(LinearLayout linearLayout, int i8, int i9, int i10, Drawable drawable) {
        GradientDrawable gradientDrawableA;
        if (drawable == null) {
            gradientDrawableA = a(i8, i9, i10);
        } else if (drawable instanceof GradientDrawable) {
            gradientDrawableA = (GradientDrawable) drawable;
            gradientDrawableA.setSize(i10, 0);
            gradientDrawableA.setCornerRadius(i9);
        } else {
            linearLayout.setDividerDrawable(drawable);
            gradientDrawableA = null;
        }
        linearLayout.setDividerDrawable(gradientDrawableA);
    }
}
