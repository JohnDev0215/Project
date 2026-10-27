package defpackage;

import android.R;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.util.StateSet;
import android.view.View;

/* JADX INFO: renamed from: qP, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1383qP {
    public static final int[] d = {R.attr.state_pressed};
    public static final int[] e = {R.attr.state_selected};
    public static final int[] f = {R.attr.state_selected, R.attr.state_pressed};
    public final View a;
    public ColorStateList b;
    public GradientDrawable c;

    public C1383qP(View view, int i, int i2, int i3, int i4) {
        int i5 = AbstractC0299Qm.w1;
        this.a = view;
        AbstractC1251o3.o(view);
        this.a.getPaddingTop();
        this.a.getPaddingEnd();
        this.a.getPaddingBottom();
        View view2 = this.a;
        ColorStateList colorStateListA = AbstractC1631v.a(view.getContext(), i2);
        ColorStateList colorStateListA2 = AbstractC1631v.a(view.getContext(), R.color.transparent);
        int dimensionPixelSize = view.getResources().getDimensionPixelSize(i5);
        GradientDrawable gradientDrawable = new GradientDrawable();
        this.c = gradientDrawable;
        float f2 = i3;
        gradientDrawable.setCornerRadius(f2);
        if (dimensionPixelSize > 0) {
            this.c.setStroke(dimensionPixelSize, colorStateListA2);
        }
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setCornerRadius(f2);
        gradientDrawable2.setColor(-1);
        int[][] iArr = {e, StateSet.NOTHING};
        int colorForState = colorStateListA.getColorForState(f, colorStateListA.getDefaultColor());
        int colorForState2 = colorStateListA.getColorForState(d, colorStateListA.getDefaultColor());
        view2.setBackground(a(new RippleDrawable(new ColorStateList(iArr, new int[]{AbstractC1250o2.c(colorForState, Math.min(Color.alpha(colorForState) * 2, 255)), AbstractC1250o2.c(colorForState2, Math.min(Color.alpha(colorForState2) * 2, 255))}), this.c, gradientDrawable2), i4));
        ColorStateList colorStateListA3 = AbstractC1631v.a(view.getContext(), i);
        if (colorStateListA3 == this.b) {
            return;
        }
        this.b = colorStateListA3;
        this.c.setColor(colorStateListA3);
    }

    public static Drawable a(Drawable drawable, int i) {
        return i == 0 ? drawable : new InsetDrawable(drawable, 0, i, 0, i);
    }
}
