package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R$styleable;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY})
public class O {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final ThreadLocal<TypedValue> f295a = new ThreadLocal<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final int[] f296b = {-16842910};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final int[] f297c = {R.attr.state_focused};
    static final int[] d = {R.attr.state_pressed};
    static final int[] e = {R.attr.state_checked};
    static final int[] f = new int[0];
    private static final int[] g = new int[1];

    public static void a(@NonNull View view, @NonNull Context context) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(R$styleable.AppCompatTheme);
        try {
            if (!typedArrayObtainStyledAttributes.hasValue(R$styleable.AppCompatTheme_windowActionBar)) {
                Log.e("ThemeUtils", "View " + view.getClass() + " is an AppCompat widget that can only be used with a Theme.AppCompat theme (or descendant).");
            }
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public static int b(@NonNull Context context, int i) {
        ColorStateList colorStateListE = e(context, i);
        if (colorStateListE != null && colorStateListE.isStateful()) {
            return colorStateListE.getColorForState(f296b, colorStateListE.getDefaultColor());
        }
        TypedValue typedValueF = f();
        context.getTheme().resolveAttribute(R.attr.disabledAlpha, typedValueF, true);
        return d(context, i, typedValueF.getFloat());
    }

    public static int c(@NonNull Context context, int i) {
        int[] iArr = g;
        iArr[0] = i;
        U uT = U.t(context, null, iArr);
        try {
            return uT.b(0, 0);
        } finally {
            uT.v();
        }
    }

    static int d(@NonNull Context context, int i, float f2) {
        return androidx.core.graphics.a.i(c(context, i), Math.round(Color.alpha(r0) * f2));
    }

    @Nullable
    public static ColorStateList e(@NonNull Context context, int i) {
        int[] iArr = g;
        iArr[0] = i;
        U uT = U.t(context, null, iArr);
        try {
            return uT.c(0);
        } finally {
            uT.v();
        }
    }

    private static TypedValue f() {
        TypedValue typedValue = f295a.get();
        if (typedValue != null) {
            return typedValue;
        }
        TypedValue typedValue2 = new TypedValue();
        f295a.set(typedValue2);
        return typedValue2;
    }
}
