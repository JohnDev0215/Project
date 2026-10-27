package c2;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import k2.b;

/* JADX INFO: compiled from: MaterialColors.java */
/* JADX INFO: loaded from: classes.dex */
public class a {
    public static int a(Context context, int i3, int i4) {
        TypedValue typedValueA = b.a(context, i3);
        return typedValueA != null ? typedValueA.data : i4;
    }

    public static int b(Context context, int i3, String str) {
        return b.c(context, i3, str);
    }

    public static int c(View view, int i3) {
        return b.d(view, i3);
    }

    public static int d(View view, int i3, int i4) {
        return a(view.getContext(), i3, i4);
    }

    public static int e(int i3, int i4) {
        return d0.a.b(i4, i3);
    }

    public static int f(int i3, int i4, float f3) {
        return e(i3, d0.a.d(i4, Math.round(Color.alpha(i4) * f3)));
    }

    public static int g(View view, int i3, int i4, float f3) {
        return f(c(view, i3), c(view, i4), f3);
    }
}
