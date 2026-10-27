package c6;

import android.R;
import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.util.StateSet;

/* JADX INFO: compiled from: RippleUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final boolean f2328a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f2329b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f2330c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[] f2331d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int[] f2332e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final int[] f2333f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final int[] f2334g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final int[] f2335h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final int[] f2336i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final int[] f2337j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final int[] f2338k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final String f2339l;

    static {
        f2328a = Build.VERSION.SDK_INT >= 21;
        f2329b = new int[]{R.attr.state_pressed};
        f2330c = new int[]{R.attr.state_hovered, R.attr.state_focused};
        f2331d = new int[]{R.attr.state_focused};
        f2332e = new int[]{R.attr.state_hovered};
        f2333f = new int[]{R.attr.state_selected, R.attr.state_pressed};
        f2334g = new int[]{R.attr.state_selected, R.attr.state_hovered, R.attr.state_focused};
        f2335h = new int[]{R.attr.state_selected, R.attr.state_focused};
        f2336i = new int[]{R.attr.state_selected, R.attr.state_hovered};
        f2337j = new int[]{R.attr.state_selected};
        f2338k = new int[]{R.attr.state_enabled, R.attr.state_pressed};
        f2339l = b.class.getSimpleName();
    }

    public static ColorStateList a(ColorStateList colorStateList) {
        if (f2328a) {
            return new ColorStateList(new int[][]{f2337j, StateSet.NOTHING}, new int[]{c(colorStateList, f2333f), c(colorStateList, f2329b)});
        }
        int[] iArr = f2333f;
        int[] iArr2 = f2334g;
        int[] iArr3 = f2335h;
        int[] iArr4 = f2336i;
        int[] iArr5 = f2329b;
        int[] iArr6 = f2330c;
        int[] iArr7 = f2331d;
        int[] iArr8 = f2332e;
        return new ColorStateList(new int[][]{iArr, iArr2, iArr3, iArr4, f2337j, iArr5, iArr6, iArr7, iArr8, StateSet.NOTHING}, new int[]{c(colorStateList, iArr), c(colorStateList, iArr2), c(colorStateList, iArr3), c(colorStateList, iArr4), 0, c(colorStateList, iArr5), c(colorStateList, iArr6), c(colorStateList, iArr7), c(colorStateList, iArr8), 0});
    }

    @TargetApi(21)
    public static int b(int i8) {
        return g0.a.d(i8, Math.min(Color.alpha(i8) * 2, 255));
    }

    public static int c(ColorStateList colorStateList, int[] iArr) {
        int colorForState = colorStateList != null ? colorStateList.getColorForState(iArr, colorStateList.getDefaultColor()) : 0;
        return f2328a ? b(colorForState) : colorForState;
    }

    public static ColorStateList d(ColorStateList colorStateList) {
        if (colorStateList == null) {
            return ColorStateList.valueOf(0);
        }
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 22 && i8 <= 27 && Color.alpha(colorStateList.getDefaultColor()) == 0 && Color.alpha(colorStateList.getColorForState(f2338k, 0)) != 0) {
            Log.w(f2339l, "Use a non-transparent color for the default color as it will be used to finish ripple animations.");
        }
        return colorStateList;
    }

    public static boolean e(int[] iArr) {
        boolean z7 = false;
        boolean z8 = false;
        for (int i8 : iArr) {
            if (i8 == 16842910) {
                z7 = true;
            } else if (i8 == 16842908 || i8 == 16842919 || i8 == 16843623) {
                z8 = true;
            }
        }
        return z7 && z8;
    }
}
