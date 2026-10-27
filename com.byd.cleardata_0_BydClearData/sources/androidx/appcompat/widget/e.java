package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final View f923a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private t0 f926d;
    private t0 e;
    private t0 f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f925c = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final i f924b = i.b();

    e(View view) {
        this.f923a = view;
    }

    private boolean a(Drawable drawable) {
        if (this.f == null) {
            this.f = new t0();
        }
        t0 t0Var = this.f;
        t0Var.a();
        ColorStateList colorStateListP = a.g.k.t.p(this.f923a);
        if (colorStateListP != null) {
            t0Var.f1025d = true;
            t0Var.f1022a = colorStateListP;
        }
        PorterDuff.Mode modeQ = a.g.k.t.q(this.f923a);
        if (modeQ != null) {
            t0Var.f1024c = true;
            t0Var.f1023b = modeQ;
        }
        if (!t0Var.f1025d && !t0Var.f1024c) {
            return false;
        }
        i.i(drawable, t0Var, this.f923a.getDrawableState());
        return true;
    }

    private boolean k() {
        int i = Build.VERSION.SDK_INT;
        return i > 21 ? this.f926d != null : i == 21;
    }

    void b() {
        Drawable background = this.f923a.getBackground();
        if (background != null) {
            if (k() && a(background)) {
                return;
            }
            t0 t0Var = this.e;
            if (t0Var != null) {
                i.i(background, t0Var, this.f923a.getDrawableState());
                return;
            }
            t0 t0Var2 = this.f926d;
            if (t0Var2 != null) {
                i.i(background, t0Var2, this.f923a.getDrawableState());
            }
        }
    }

    ColorStateList c() {
        t0 t0Var = this.e;
        if (t0Var != null) {
            return t0Var.f1022a;
        }
        return null;
    }

    PorterDuff.Mode d() {
        t0 t0Var = this.e;
        if (t0Var != null) {
            return t0Var.f1023b;
        }
        return null;
    }

    void e(AttributeSet attributeSet, int i) {
        Context context = this.f923a.getContext();
        int[] iArr = a.a.j.D3;
        v0 v0VarV = v0.v(context, attributeSet, iArr, i, 0);
        View view = this.f923a;
        a.g.k.t.f0(view, view.getContext(), iArr, attributeSet, v0VarV.r(), i, 0);
        try {
            int i2 = a.a.j.E3;
            if (v0VarV.s(i2)) {
                this.f925c = v0VarV.n(i2, -1);
                ColorStateList colorStateListF = this.f924b.f(this.f923a.getContext(), this.f925c);
                if (colorStateListF != null) {
                    h(colorStateListF);
                }
            }
            int i3 = a.a.j.F3;
            if (v0VarV.s(i3)) {
                a.g.k.t.l0(this.f923a, v0VarV.c(i3));
            }
            int i4 = a.a.j.G3;
            if (v0VarV.s(i4)) {
                a.g.k.t.m0(this.f923a, d0.d(v0VarV.k(i4, -1), null));
            }
        } finally {
            v0VarV.w();
        }
    }

    void f(Drawable drawable) {
        this.f925c = -1;
        h(null);
        b();
    }

    void g(int i) {
        this.f925c = i;
        i iVar = this.f924b;
        h(iVar != null ? iVar.f(this.f923a.getContext(), i) : null);
        b();
    }

    void h(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.f926d == null) {
                this.f926d = new t0();
            }
            t0 t0Var = this.f926d;
            t0Var.f1022a = colorStateList;
            t0Var.f1025d = true;
        } else {
            this.f926d = null;
        }
        b();
    }

    void i(ColorStateList colorStateList) {
        if (this.e == null) {
            this.e = new t0();
        }
        t0 t0Var = this.e;
        t0Var.f1022a = colorStateList;
        t0Var.f1025d = true;
        b();
    }

    void j(PorterDuff.Mode mode) {
        if (this.e == null) {
            this.e = new t0();
        }
        t0 t0Var = this.e;
        t0Var.f1023b = mode;
        t0Var.f1024c = true;
        b();
    }
}
