package defpackage;

import android.R;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.StateSet;

/* JADX INFO: compiled from: RippleUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class dc2 {
    public static final int[] a = {R.attr.state_pressed};
    public static final int[] b = {R.attr.state_focused};
    public static final int[] c = {R.attr.state_selected, R.attr.state_pressed};
    public static final int[] d = {R.attr.state_selected};

    public static ColorStateList a(ColorStateList colorStateList) {
        int[] iArr = b;
        return new ColorStateList(new int[][]{d, iArr, StateSet.NOTHING}, new int[]{b(colorStateList, c), b(colorStateList, iArr), b(colorStateList, a)});
    }

    public static int b(ColorStateList colorStateList, int[] iArr) {
        int colorForState = colorStateList != null ? colorStateList.getColorForState(iArr, colorStateList.getDefaultColor()) : 0;
        return gv.g(colorForState, Math.min(Color.alpha(colorForState) * 2, 255));
    }

    public static ColorStateList c(ColorStateList colorStateList) {
        return colorStateList != null ? colorStateList : ColorStateList.valueOf(0);
    }

    public static boolean d(int[] iArr) {
        boolean z = false;
        boolean z2 = false;
        for (int i : iArr) {
            if (i == 16842910) {
                z = true;
            } else if (i == 16842908 || i == 16842919 || i == 16843623) {
                z2 = true;
            }
        }
        return z && z2;
    }
}
