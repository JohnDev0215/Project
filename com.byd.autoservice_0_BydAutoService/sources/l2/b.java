package l2;

import android.R;
import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.util.StateSet;

/* JADX INFO: compiled from: RippleUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final boolean f3717a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f3718b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f3719c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[] f3720d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int[] f3721e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final int[] f3722f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final int[] f3723g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final int[] f3724h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final int[] f3725i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final int[] f3726j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final int[] f3727k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final String f3728l;

    static {
        f3717a = Build.VERSION.SDK_INT >= 21;
        f3718b = new int[]{R.attr.state_pressed};
        f3719c = new int[]{R.attr.state_hovered, R.attr.state_focused};
        f3720d = new int[]{R.attr.state_focused};
        f3721e = new int[]{R.attr.state_hovered};
        f3722f = new int[]{R.attr.state_selected, R.attr.state_pressed};
        f3723g = new int[]{R.attr.state_selected, R.attr.state_hovered, R.attr.state_focused};
        f3724h = new int[]{R.attr.state_selected, R.attr.state_focused};
        f3725i = new int[]{R.attr.state_selected, R.attr.state_hovered};
        f3726j = new int[]{R.attr.state_selected};
        f3727k = new int[]{R.attr.state_enabled, R.attr.state_pressed};
        f3728l = b.class.getSimpleName();
    }

    public static ColorStateList a(ColorStateList colorStateList) {
        if (f3717a) {
            return new ColorStateList(new int[][]{f3726j, StateSet.NOTHING}, new int[]{c(colorStateList, f3722f), c(colorStateList, f3718b)});
        }
        int[] iArr = f3722f;
        int[] iArr2 = f3723g;
        int[] iArr3 = f3724h;
        int[] iArr4 = f3725i;
        int[] iArr5 = f3718b;
        int[] iArr6 = f3719c;
        int[] iArr7 = f3720d;
        int[] iArr8 = f3721e;
        return new ColorStateList(new int[][]{iArr, iArr2, iArr3, iArr4, f3726j, iArr5, iArr6, iArr7, iArr8, StateSet.NOTHING}, new int[]{c(colorStateList, iArr), c(colorStateList, iArr2), c(colorStateList, iArr3), c(colorStateList, iArr4), 0, c(colorStateList, iArr5), c(colorStateList, iArr6), c(colorStateList, iArr7), c(colorStateList, iArr8), 0});
    }

    @TargetApi(21)
    public static int b(int i3) {
        return d0.a.d(i3, Math.min(Color.alpha(i3) * 2, 255));
    }

    public static int c(ColorStateList colorStateList, int[] iArr) {
        int colorForState = colorStateList != null ? colorStateList.getColorForState(iArr, colorStateList.getDefaultColor()) : 0;
        return f3717a ? b(colorForState) : colorForState;
    }

    public static ColorStateList d(ColorStateList colorStateList) {
        if (colorStateList == null) {
            return ColorStateList.valueOf(0);
        }
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 22 && i3 <= 27 && Color.alpha(colorStateList.getDefaultColor()) == 0 && Color.alpha(colorStateList.getColorForState(f3727k, 0)) != 0) {
            Log.w(f3728l, "Use a non-transparent color for the default color as it will be used to finish ripple animations.");
        }
        return colorStateList;
    }

    public static boolean e(int[] iArr) {
        boolean z3 = false;
        boolean z4 = false;
        for (int i3 : iArr) {
            if (i3 == 16842910) {
                z3 = true;
            } else if (i3 == 16842908 || i3 == 16842919 || i3 == 16843623) {
                z4 = true;
            }
        }
        return z3 && z4;
    }
}
