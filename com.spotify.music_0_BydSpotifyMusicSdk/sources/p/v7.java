package p;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class v7 {
    public final View a;
    public xl2 d;
    public xl2 e;
    public xl2 f;
    public int c = -1;
    public final u8 b = u8.a();

    public v7(View view) {
        this.a = view;
    }

    public final void a() {
        View view = this.a;
        Drawable background = view.getBackground();
        if (background != null) {
            if (this.d != null) {
                if (this.f == null) {
                    this.f = new xl2(0);
                }
                xl2 xl2Var = this.f;
                xl2Var.c = null;
                xl2Var.b = false;
                xl2Var.d = null;
                xl2Var.a = false;
                WeakHashMap weakHashMap = zt2.a;
                ColorStateList colorStateListG = ot2.g(view);
                if (colorStateListG != null) {
                    xl2Var.b = true;
                    xl2Var.c = colorStateListG;
                }
                PorterDuff.Mode modeH = ot2.h(view);
                if (modeH != null) {
                    xl2Var.a = true;
                    xl2Var.d = modeH;
                }
                if (xl2Var.b || xl2Var.a) {
                    u8.d(background, xl2Var, view.getDrawableState());
                    return;
                }
            }
            xl2 xl2Var2 = this.e;
            if (xl2Var2 != null) {
                u8.d(background, xl2Var2, view.getDrawableState());
                return;
            }
            xl2 xl2Var3 = this.d;
            if (xl2Var3 != null) {
                u8.d(background, xl2Var3, view.getDrawableState());
            }
        }
    }

    public final ColorStateList b() {
        xl2 xl2Var = this.e;
        if (xl2Var != null) {
            return (ColorStateList) xl2Var.c;
        }
        return null;
    }

    public final PorterDuff.Mode c() {
        xl2 xl2Var = this.e;
        if (xl2Var != null) {
            return (PorterDuff.Mode) xl2Var.d;
        }
        return null;
    }

    public final void d(AttributeSet attributeSet, int i) {
        ColorStateList colorStateListH;
        View view = this.a;
        Context context = view.getContext();
        int[] iArr = yz1.z;
        v4 v4VarJ = v4.J(context, attributeSet, iArr, i);
        View view2 = this.a;
        zt2.j(view2, view2.getContext(), iArr, attributeSet, (TypedArray) v4VarJ.k, i);
        try {
            if (v4VarJ.G(0)) {
                this.c = v4VarJ.y(0, -1);
                u8 u8Var = this.b;
                Context context2 = view.getContext();
                int i2 = this.c;
                synchronized (u8Var) {
                    colorStateListH = u8Var.a.h(context2, i2);
                }
                if (colorStateListH != null) {
                    g(colorStateListH);
                }
            }
            if (v4VarJ.G(1)) {
                ot2.q(view, v4VarJ.q(1));
            }
            if (v4VarJ.G(2)) {
                ot2.r(view, cb0.b(v4VarJ.w(2, -1), null));
            }
            v4VarJ.L();
        } catch (Throwable th) {
            v4VarJ.L();
            throw th;
        }
    }

    public final void e() {
        this.c = -1;
        g(null);
        a();
    }

    public final void f(int i) {
        ColorStateList colorStateListH;
        this.c = i;
        u8 u8Var = this.b;
        if (u8Var != null) {
            Context context = this.a.getContext();
            synchronized (u8Var) {
                colorStateListH = u8Var.a.h(context, i);
            }
        } else {
            colorStateListH = null;
        }
        g(colorStateListH);
        a();
    }

    public final void g(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.d == null) {
                this.d = new xl2(0);
            }
            xl2 xl2Var = this.d;
            xl2Var.c = colorStateList;
            xl2Var.b = true;
        } else {
            this.d = null;
        }
        a();
    }

    public final void h(ColorStateList colorStateList) {
        if (this.e == null) {
            this.e = new xl2(0);
        }
        xl2 xl2Var = this.e;
        xl2Var.c = colorStateList;
        xl2Var.b = true;
        a();
    }

    public final void i(PorterDuff.Mode mode) {
        if (this.e == null) {
            this.e = new xl2(0);
        }
        xl2 xl2Var = this.e;
        xl2Var.d = mode;
        xl2Var.a = true;
        a();
    }
}
