package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/* JADX INFO: compiled from: AppCompatBackgroundHelper.java */
/* JADX INFO: loaded from: classes.dex */
class d {

    /* JADX INFO: renamed from: a */
    private final View f591a;

    /* JADX INFO: renamed from: d */
    private d0 f594d;

    /* JADX INFO: renamed from: e */
    private d0 f595e;

    /* JADX INFO: renamed from: f */
    private d0 f596f;

    /* JADX INFO: renamed from: c */
    private int f593c = -1;

    /* JADX INFO: renamed from: b */
    private final f f592b = f.b();

    d(View view) {
        this.f591a = view;
    }

    private boolean a(Drawable drawable) {
        if (this.f596f == null) {
            this.f596f = new d0();
        }
        d0 d0Var = this.f596f;
        d0Var.a();
        ColorStateList colorStateListQ = b.g.k.t.q(this.f591a);
        if (colorStateListQ != null) {
            d0Var.f600d = true;
            d0Var.f597a = colorStateListQ;
        }
        PorterDuff.Mode modeR = b.g.k.t.r(this.f591a);
        if (modeR != null) {
            d0Var.f599c = true;
            d0Var.f598b = modeR;
        }
        if (!d0Var.f600d && !d0Var.f599c) {
            return false;
        }
        f.i(drawable, d0Var, this.f591a.getDrawableState());
        return true;
    }

    private boolean k() {
        int i2 = Build.VERSION.SDK_INT;
        return i2 > 21 ? this.f594d != null : i2 == 21;
    }

    void b() {
        Drawable background = this.f591a.getBackground();
        if (background != null) {
            if (k() && a(background)) {
                return;
            }
            d0 d0Var = this.f595e;
            if (d0Var != null) {
                f.i(background, d0Var, this.f591a.getDrawableState());
                return;
            }
            d0 d0Var2 = this.f594d;
            if (d0Var2 != null) {
                f.i(background, d0Var2, this.f591a.getDrawableState());
            }
        }
    }

    ColorStateList c() {
        d0 d0Var = this.f595e;
        if (d0Var != null) {
            return d0Var.f597a;
        }
        return null;
    }

    PorterDuff.Mode d() {
        d0 d0Var = this.f595e;
        if (d0Var != null) {
            return d0Var.f598b;
        }
        return null;
    }

    void e(AttributeSet attributeSet, int i2) {
        Context context = this.f591a.getContext();
        int[] iArr = b.a.j.ViewBackgroundHelper;
        f0 f0VarV = f0.v(context, attributeSet, iArr, i2, 0);
        View view = this.f591a;
        b.g.k.t.h0(view, view.getContext(), iArr, attributeSet, f0VarV.r(), i2, 0);
        try {
            int i3 = b.a.j.ViewBackgroundHelper_android_background;
            if (f0VarV.s(i3)) {
                this.f593c = f0VarV.n(i3, -1);
                ColorStateList colorStateListF = this.f592b.f(this.f591a.getContext(), this.f593c);
                if (colorStateListF != null) {
                    h(colorStateListF);
                }
            }
            int i4 = b.a.j.ViewBackgroundHelper_backgroundTint;
            if (f0VarV.s(i4)) {
                b.g.k.t.n0(this.f591a, f0VarV.c(i4));
            }
            int i5 = b.a.j.ViewBackgroundHelper_backgroundTintMode;
            if (f0VarV.s(i5)) {
                b.g.k.t.o0(this.f591a, q.e(f0VarV.k(i5, -1), null));
            }
        } finally {
            f0VarV.w();
        }
    }

    void f(Drawable drawable) {
        this.f593c = -1;
        h(null);
        b();
    }

    void g(int i2) {
        this.f593c = i2;
        f fVar = this.f592b;
        h(fVar != null ? fVar.f(this.f591a.getContext(), i2) : null);
        b();
    }

    void h(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.f594d == null) {
                this.f594d = new d0();
            }
            d0 d0Var = this.f594d;
            d0Var.f597a = colorStateList;
            d0Var.f600d = true;
        } else {
            this.f594d = null;
        }
        b();
    }

    void i(ColorStateList colorStateList) {
        if (this.f595e == null) {
            this.f595e = new d0();
        }
        d0 d0Var = this.f595e;
        d0Var.f597a = colorStateList;
        d0Var.f600d = true;
        b();
    }

    void j(PorterDuff.Mode mode) {
        if (this.f595e == null) {
            this.f595e = new d0();
        }
        d0 d0Var = this.f595e;
        d0Var.f598b = mode;
        d0Var.f599c = true;
        b();
    }
}
