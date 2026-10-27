package b.d.a.a.c0;

import android.R;
import android.content.res.ColorStateList;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final boolean f1815a = true;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f1816b = {R.attr.state_enabled, R.attr.state_pressed};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String f1817c = a.class.getSimpleName();

    public static ColorStateList a(ColorStateList colorStateList) {
        return colorStateList != null ? colorStateList : ColorStateList.valueOf(0);
    }

    public static boolean b(int[] iArr) {
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
