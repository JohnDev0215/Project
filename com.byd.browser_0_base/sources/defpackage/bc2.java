package defpackage;

import android.R;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.util.StateSet;
import android.view.View;

/* JADX INFO: compiled from: RippleBackgroundHelper.java */
/* JADX INFO: loaded from: classes3.dex */
public final class bc2 {
    public static final int[] f = {R.attr.state_pressed};
    public static final int[] g = {R.attr.state_selected};
    public static final int[] h = {R.attr.state_selected, R.attr.state_pressed};
    public ColorStateList a;
    public ColorStateList b;
    public GradientDrawable c;
    public GradientDrawable d;
    public LayerDrawable e;

    public bc2(View view, int i, int i2, float[] fArr, int i3, int i4, int i5) {
        ColorStateList colorStateListB = bz.b(view.getContext(), i2);
        ColorStateList colorStateListB2 = bz.b(view.getContext(), i3);
        int dimensionPixelSize = view.getResources().getDimensionPixelSize(i4);
        GradientDrawable gradientDrawable = new GradientDrawable();
        this.c = gradientDrawable;
        gradientDrawable.setCornerRadii(fArr);
        if (dimensionPixelSize > 0) {
            this.c.setStroke(dimensionPixelSize, colorStateListB2);
        }
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        this.d = gradientDrawable2;
        gradientDrawable2.setCornerRadii(fArr);
        this.d.setStroke(dimensionPixelSize, 0);
        this.e = new LayerDrawable(new Drawable[]{this.c, this.d});
        GradientDrawable gradientDrawable3 = new GradientDrawable();
        gradientDrawable3.setCornerRadii(fArr);
        gradientDrawable3.setColor(-1);
        int[][] iArr = {g, StateSet.NOTHING};
        int colorForState = colorStateListB.getColorForState(h, colorStateListB.getDefaultColor());
        int colorForState2 = colorStateListB.getColorForState(f, colorStateListB.getDefaultColor());
        ColorStateList colorStateList = new ColorStateList(iArr, new int[]{gv.g(colorForState, Math.min(Color.alpha(colorForState) * 2, 255)), gv.g(colorForState2, Math.min(Color.alpha(colorForState2) * 2, 255))});
        Drawable drawable = this.e;
        view.setBackground(new RippleDrawable(colorStateList, i5 != 0 ? new InsetDrawable(drawable, 0, i5, 0, i5) : drawable, gradientDrawable3));
        ColorStateList colorStateListB3 = bz.b(view.getContext(), i);
        if (colorStateListB3 == this.a) {
            return;
        }
        this.a = colorStateListB3;
        this.c.setColor(colorStateListB3);
    }
}
