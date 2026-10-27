package t5;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import b6.b;

/* JADX INFO: compiled from: MaterialColors.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {
    public static int a(Context context, int i8, int i9) {
        TypedValue typedValueA = b.a(context, i8);
        return typedValueA != null ? typedValueA.data : i9;
    }

    public static int b(Context context, int i8, String str) {
        return b.c(context, i8, str);
    }

    public static int c(View view, int i8) {
        return b.d(view, i8);
    }

    public static int d(View view, int i8, int i9) {
        return a(view.getContext(), i8, i9);
    }

    public static int e(int i8, int i9) {
        return g0.a.b(i9, i8);
    }

    public static int f(int i8, int i9, float f8) {
        return e(i8, g0.a.d(i9, Math.round(Color.alpha(i9) * f8)));
    }

    public static int g(View view, int i8, int i9, float f8) {
        return f(c(view, i8), c(view, i9), f8);
    }
}
