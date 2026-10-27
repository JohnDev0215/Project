package u2;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import b3.b;

/* JADX INFO: compiled from: MaterialColors.java */
/* JADX INFO: loaded from: classes.dex */
public class a {
    public static int a(Context context, int i7, int i8) {
        TypedValue typedValueA = b.a(context, i7);
        return typedValueA != null ? typedValueA.data : i8;
    }

    public static int b(Context context, int i7, String str) {
        return b.c(context, i7, str);
    }

    public static int c(View view, int i7) {
        return b.d(view, i7);
    }

    public static int d(View view, int i7, int i8) {
        return a(view.getContext(), i7, i8);
    }

    public static int e(int i7, int i8) {
        return z.a.b(i8, i7);
    }

    public static int f(int i7, int i8, float f7) {
        return e(i7, z.a.d(i8, Math.round(Color.alpha(i8) * f7)));
    }

    public static int g(View view, int i7, int i8, float f7) {
        return f(c(view, i7), c(view, i8), f7);
    }
}
