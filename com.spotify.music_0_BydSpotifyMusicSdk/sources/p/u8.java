package p;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;

/* JADX INFO: loaded from: classes.dex */
public final class u8 {
    public static final PorterDuff.Mode b = PorterDuff.Mode.SRC_IN;
    public static u8 c;
    public h42 a;

    public static synchronized u8 a() {
        try {
            if (c == null) {
                c();
            }
        } catch (Throwable th) {
            throw th;
        }
        return c;
    }

    public static synchronized void c() {
        if (c == null) {
            u8 u8Var = new u8();
            c = u8Var;
            u8Var.a = h42.c();
            h42 h42Var = c.a;
            gu0 gu0Var = new gu0(1);
            synchronized (h42Var) {
                h42Var.e = gu0Var;
            }
        }
    }

    public static void d(Drawable drawable, xl2 xl2Var, int[] iArr) {
        PorterDuff.Mode mode = h42.f;
        int[] state = drawable.getState();
        int[] iArr2 = cb0.a;
        if (drawable.mutate() == drawable) {
            if ((drawable instanceof LayerDrawable) && drawable.isStateful()) {
                drawable.setState(new int[0]);
                drawable.setState(state);
            }
            boolean z = xl2Var.b;
            if (!z && !xl2Var.a) {
                drawable.clearColorFilter();
                return;
            }
            PorterDuffColorFilter porterDuffColorFilterG = null;
            ColorStateList colorStateList = z ? (ColorStateList) xl2Var.c : null;
            PorterDuff.Mode mode2 = xl2Var.a ? (PorterDuff.Mode) xl2Var.d : h42.f;
            if (colorStateList != null && mode2 != null) {
                porterDuffColorFilterG = h42.g(colorStateList.getColorForState(iArr, 0), mode2);
            }
            drawable.setColorFilter(porterDuffColorFilterG);
        }
    }

    public final synchronized Drawable b(Context context, int i) {
        return this.a.e(context, i);
    }
}
