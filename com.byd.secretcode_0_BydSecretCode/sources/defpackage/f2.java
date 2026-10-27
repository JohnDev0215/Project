package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.R$styleable;

/* JADX INFO: compiled from: AppCompatBackgroundHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class f2 {
    public final View a;
    public e3 d;
    public e3 e;
    public e3 f;
    public int c = -1;
    public final h2 b = h2.b();

    public f2(View view) {
        this.a = view;
    }

    public final boolean a(Drawable drawable) {
        if (this.f == null) {
            this.f = new e3();
        }
        e3 e3Var = this.f;
        e3Var.a();
        ColorStateList colorStateListQ = t9.q(this.a);
        if (colorStateListQ != null) {
            e3Var.d = true;
            e3Var.a = colorStateListQ;
        }
        PorterDuff.Mode modeR = t9.r(this.a);
        if (modeR != null) {
            e3Var.c = true;
            e3Var.b = modeR;
        }
        if (!e3Var.d && !e3Var.c) {
            return false;
        }
        h2.i(drawable, e3Var, this.a.getDrawableState());
        return true;
    }

    public void b() {
        Drawable background = this.a.getBackground();
        if (background != null) {
            if (k() && a(background)) {
                return;
            }
            e3 e3Var = this.e;
            if (e3Var != null) {
                h2.i(background, e3Var, this.a.getDrawableState());
                return;
            }
            e3 e3Var2 = this.d;
            if (e3Var2 != null) {
                h2.i(background, e3Var2, this.a.getDrawableState());
            }
        }
    }

    public ColorStateList c() {
        e3 e3Var = this.e;
        if (e3Var != null) {
            return e3Var.a;
        }
        return null;
    }

    public PorterDuff.Mode d() {
        e3 e3Var = this.e;
        if (e3Var != null) {
            return e3Var.b;
        }
        return null;
    }

    public void e(AttributeSet attributeSet, int i) {
        Context context = this.a.getContext();
        int[] iArr = R$styleable.ViewBackgroundHelper;
        g3 g3VarV = g3.v(context, attributeSet, iArr, i, 0);
        View view = this.a;
        t9.h0(view, view.getContext(), iArr, attributeSet, g3VarV.r(), i, 0);
        try {
            int i2 = R$styleable.ViewBackgroundHelper_android_background;
            if (g3VarV.s(i2)) {
                this.c = g3VarV.n(i2, -1);
                ColorStateList colorStateListF = this.b.f(this.a.getContext(), this.c);
                if (colorStateListF != null) {
                    h(colorStateListF);
                }
            }
            int i3 = R$styleable.ViewBackgroundHelper_backgroundTint;
            if (g3VarV.s(i3)) {
                t9.n0(this.a, g3VarV.c(i3));
            }
            int i4 = R$styleable.ViewBackgroundHelper_backgroundTintMode;
            if (g3VarV.s(i4)) {
                t9.o0(this.a, s2.e(g3VarV.k(i4, -1), null));
            }
        } finally {
            g3VarV.w();
        }
    }

    public void f(Drawable drawable) {
        this.c = -1;
        h(null);
        b();
    }

    public void g(int i) {
        this.c = i;
        h2 h2Var = this.b;
        h(h2Var != null ? h2Var.f(this.a.getContext(), i) : null);
        b();
    }

    public void h(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.d == null) {
                this.d = new e3();
            }
            e3 e3Var = this.d;
            e3Var.a = colorStateList;
            e3Var.d = true;
        } else {
            this.d = null;
        }
        b();
    }

    public void i(ColorStateList colorStateList) {
        if (this.e == null) {
            this.e = new e3();
        }
        e3 e3Var = this.e;
        e3Var.a = colorStateList;
        e3Var.d = true;
        b();
    }

    public void j(PorterDuff.Mode mode) {
        if (this.e == null) {
            this.e = new e3();
        }
        e3 e3Var = this.e;
        e3Var.b = mode;
        e3Var.c = true;
        b();
    }

    public final boolean k() {
        int i = Build.VERSION.SDK_INT;
        return i > 21 ? this.d != null : i == 21;
    }
}
