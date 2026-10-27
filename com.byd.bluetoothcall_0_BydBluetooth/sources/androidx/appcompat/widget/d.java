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
    private final View f571a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private c0 f574d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private c0 f575e;
    private c0 f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f573c = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final f f572b = f.b();

    d(View view) {
        this.f571a = view;
    }

    private boolean a(Drawable drawable) {
        if (this.f == null) {
            this.f = new c0();
        }
        c0 c0Var = this.f;
        c0Var.a();
        ColorStateList colorStateListQ = b.g.k.t.q(this.f571a);
        if (colorStateListQ != null) {
            c0Var.f570d = true;
            c0Var.f567a = colorStateListQ;
        }
        PorterDuff.Mode modeR = b.g.k.t.r(this.f571a);
        if (modeR != null) {
            c0Var.f569c = true;
            c0Var.f568b = modeR;
        }
        if (!c0Var.f570d && !c0Var.f569c) {
            return false;
        }
        f.i(drawable, c0Var, this.f571a.getDrawableState());
        return true;
    }

    private boolean k() {
        int i = Build.VERSION.SDK_INT;
        return i > 21 ? this.f574d != null : i == 21;
    }

    void b() {
        Drawable background = this.f571a.getBackground();
        if (background != null) {
            if (k() && a(background)) {
                return;
            }
            c0 c0Var = this.f575e;
            if (c0Var != null) {
                f.i(background, c0Var, this.f571a.getDrawableState());
                return;
            }
            c0 c0Var2 = this.f574d;
            if (c0Var2 != null) {
                f.i(background, c0Var2, this.f571a.getDrawableState());
            }
        }
    }

    ColorStateList c() {
        c0 c0Var = this.f575e;
        if (c0Var != null) {
            return c0Var.f567a;
        }
        return null;
    }

    PorterDuff.Mode d() {
        c0 c0Var = this.f575e;
        if (c0Var != null) {
            return c0Var.f568b;
        }
        return null;
    }

    void e(AttributeSet attributeSet, int i) {
        Context context = this.f571a.getContext();
        int[] iArr = b.a.j.T3;
        e0 e0VarV = e0.v(context, attributeSet, iArr, i, 0);
        View view = this.f571a;
        b.g.k.t.i0(view, view.getContext(), iArr, attributeSet, e0VarV.r(), i, 0);
        try {
            int i2 = b.a.j.U3;
            if (e0VarV.s(i2)) {
                this.f573c = e0VarV.n(i2, -1);
                ColorStateList colorStateListF = this.f572b.f(this.f571a.getContext(), this.f573c);
                if (colorStateListF != null) {
                    h(colorStateListF);
                }
            }
            int i3 = b.a.j.V3;
            if (e0VarV.s(i3)) {
                b.g.k.t.o0(this.f571a, e0VarV.c(i3));
            }
            int i4 = b.a.j.W3;
            if (e0VarV.s(i4)) {
                b.g.k.t.p0(this.f571a, q.e(e0VarV.k(i4, -1), null));
            }
        } finally {
            e0VarV.w();
        }
    }

    void f(Drawable drawable) {
        this.f573c = -1;
        h(null);
        b();
    }

    void g(int i) {
        this.f573c = i;
        f fVar = this.f572b;
        h(fVar != null ? fVar.f(this.f571a.getContext(), i) : null);
        b();
    }

    void h(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.f574d == null) {
                this.f574d = new c0();
            }
            c0 c0Var = this.f574d;
            c0Var.f567a = colorStateList;
            c0Var.f570d = true;
        } else {
            this.f574d = null;
        }
        b();
    }

    void i(ColorStateList colorStateList) {
        if (this.f575e == null) {
            this.f575e = new c0();
        }
        c0 c0Var = this.f575e;
        c0Var.f567a = colorStateList;
        c0Var.f570d = true;
        b();
    }

    void j(PorterDuff.Mode mode) {
        if (this.f575e == null) {
            this.f575e = new c0();
        }
        c0 c0Var = this.f575e;
        c0Var.f568b = mode;
        c0Var.f569c = true;
        b();
    }
}
