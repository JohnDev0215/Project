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

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final View f939a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private c0 f942d;
    private c0 e;
    private c0 f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f941c = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final f f940b = f.b();

    d(View view) {
        this.f939a = view;
    }

    private boolean a(Drawable drawable) {
        if (this.f == null) {
            this.f = new c0();
        }
        c0 c0Var = this.f;
        c0Var.a();
        ColorStateList colorStateListQ = c.g.k.t.q(this.f939a);
        if (colorStateListQ != null) {
            c0Var.f938d = true;
            c0Var.f935a = colorStateListQ;
        }
        PorterDuff.Mode modeR = c.g.k.t.r(this.f939a);
        if (modeR != null) {
            c0Var.f937c = true;
            c0Var.f936b = modeR;
        }
        if (!c0Var.f938d && !c0Var.f937c) {
            return false;
        }
        f.i(drawable, c0Var, this.f939a.getDrawableState());
        return true;
    }

    private boolean k() {
        int i = Build.VERSION.SDK_INT;
        return i > 21 ? this.f942d != null : i == 21;
    }

    void b() {
        Drawable background = this.f939a.getBackground();
        if (background != null) {
            if (k() && a(background)) {
                return;
            }
            c0 c0Var = this.e;
            if (c0Var != null) {
                f.i(background, c0Var, this.f939a.getDrawableState());
                return;
            }
            c0 c0Var2 = this.f942d;
            if (c0Var2 != null) {
                f.i(background, c0Var2, this.f939a.getDrawableState());
            }
        }
    }

    ColorStateList c() {
        c0 c0Var = this.e;
        if (c0Var != null) {
            return c0Var.f935a;
        }
        return null;
    }

    PorterDuff.Mode d() {
        c0 c0Var = this.e;
        if (c0Var != null) {
            return c0Var.f936b;
        }
        return null;
    }

    void e(AttributeSet attributeSet, int i) {
        Context context = this.f939a.getContext();
        int[] iArr = c.a.j.T3;
        e0 e0VarV = e0.v(context, attributeSet, iArr, i, 0);
        View view = this.f939a;
        c.g.k.t.h0(view, view.getContext(), iArr, attributeSet, e0VarV.r(), i, 0);
        try {
            int i2 = c.a.j.U3;
            if (e0VarV.s(i2)) {
                this.f941c = e0VarV.n(i2, -1);
                ColorStateList colorStateListF = this.f940b.f(this.f939a.getContext(), this.f941c);
                if (colorStateListF != null) {
                    h(colorStateListF);
                }
            }
            int i3 = c.a.j.V3;
            if (e0VarV.s(i3)) {
                c.g.k.t.n0(this.f939a, e0VarV.c(i3));
            }
            int i4 = c.a.j.W3;
            if (e0VarV.s(i4)) {
                c.g.k.t.o0(this.f939a, q.e(e0VarV.k(i4, -1), null));
            }
        } finally {
            e0VarV.w();
        }
    }

    void f(Drawable drawable) {
        this.f941c = -1;
        h(null);
        b();
    }

    void g(int i) {
        this.f941c = i;
        f fVar = this.f940b;
        h(fVar != null ? fVar.f(this.f939a.getContext(), i) : null);
        b();
    }

    void h(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.f942d == null) {
                this.f942d = new c0();
            }
            c0 c0Var = this.f942d;
            c0Var.f935a = colorStateList;
            c0Var.f938d = true;
        } else {
            this.f942d = null;
        }
        b();
    }

    void i(ColorStateList colorStateList) {
        if (this.e == null) {
            this.e = new c0();
        }
        c0 c0Var = this.e;
        c0Var.f935a = colorStateList;
        c0Var.f938d = true;
        b();
    }

    void j(PorterDuff.Mode mode) {
        if (this.e == null) {
            this.e = new c0();
        }
        c0 c0Var = this.e;
        c0Var.f936b = mode;
        c0Var.f937c = true;
        b();
    }
}
