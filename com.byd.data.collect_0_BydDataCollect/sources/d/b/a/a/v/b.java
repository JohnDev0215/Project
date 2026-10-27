package d.b.a.a.v;

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
    public static final boolean f4729a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int[] f4730b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int[] f4731c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final int[] f4732d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final int[] f4733e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final int[] f4734f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final int[] f4735g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final int[] f4736h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static final int[] f4737i;
    private static final int[] j;
    private static final int[] k;
    static final String l;

    static {
        f4729a = Build.VERSION.SDK_INT >= 21;
        f4730b = new int[]{R.attr.state_pressed};
        f4731c = new int[]{R.attr.state_hovered, R.attr.state_focused};
        f4732d = new int[]{R.attr.state_focused};
        f4733e = new int[]{R.attr.state_hovered};
        f4734f = new int[]{R.attr.state_selected, R.attr.state_pressed};
        f4735g = new int[]{R.attr.state_selected, R.attr.state_hovered, R.attr.state_focused};
        f4736h = new int[]{R.attr.state_selected, R.attr.state_focused};
        f4737i = new int[]{R.attr.state_selected, R.attr.state_hovered};
        j = new int[]{R.attr.state_selected};
        k = new int[]{R.attr.state_enabled, R.attr.state_pressed};
        l = b.class.getSimpleName();
    }

    private b() {
    }

    public static ColorStateList a(ColorStateList colorStateList) {
        if (f4729a) {
            return new ColorStateList(new int[][]{j, StateSet.NOTHING}, new int[]{c(colorStateList, f4734f), c(colorStateList, f4730b)});
        }
        int[] iArr = f4734f;
        int[] iArr2 = f4735g;
        int[] iArr3 = f4736h;
        int[] iArr4 = f4737i;
        int[] iArr5 = f4730b;
        int[] iArr6 = f4731c;
        int[] iArr7 = f4732d;
        int[] iArr8 = f4733e;
        return new ColorStateList(new int[][]{iArr, iArr2, iArr3, iArr4, j, iArr5, iArr6, iArr7, iArr8, StateSet.NOTHING}, new int[]{c(colorStateList, iArr), c(colorStateList, iArr2), c(colorStateList, iArr3), c(colorStateList, iArr4), 0, c(colorStateList, iArr5), c(colorStateList, iArr6), c(colorStateList, iArr7), c(colorStateList, iArr8), 0});
    }

    @TargetApi(21)
    private static int b(int i2) {
        return b.g.d.a.d(i2, Math.min(Color.alpha(i2) * 2, 255));
    }

    private static int c(ColorStateList colorStateList, int[] iArr) {
        int colorForState = colorStateList != null ? colorStateList.getColorForState(iArr, colorStateList.getDefaultColor()) : 0;
        return f4729a ? b(colorForState) : colorForState;
    }

    public static ColorStateList d(ColorStateList colorStateList) {
        if (colorStateList == null) {
            return ColorStateList.valueOf(0);
        }
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 22 && i2 <= 27 && Color.alpha(colorStateList.getDefaultColor()) == 0 && Color.alpha(colorStateList.getColorForState(k, 0)) != 0) {
            Log.w(l, "Use a non-transparent color for the default color as it will be used to finish ripple animations.");
        }
        return colorStateList;
    }

    public static boolean e(int[] iArr) {
        boolean z = false;
        boolean z2 = false;
        for (int i2 : iArr) {
            if (i2 == 16842910) {
                z = true;
            } else if (i2 == 16842908 || i2 == 16842919 || i2 == 16843623) {
                z2 = true;
            }
        }
        return z && z2;
    }
}
