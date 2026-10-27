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
    private final View f410a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private c0 f413d;
    private c0 e;
    private c0 f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f412c = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final f f411b = f.b();

    d(View view) {
        this.f410a = view;
    }

    private boolean a(Drawable drawable) {
        if (this.f == null) {
            this.f = new c0();
        }
        c0 c0Var = this.f;
        c0Var.a();
        ColorStateList colorStateListQ = b.g.k.t.q(this.f410a);
        if (colorStateListQ != null) {
            c0Var.f409d = true;
            c0Var.f406a = colorStateListQ;
        }
        PorterDuff.Mode modeR = b.g.k.t.r(this.f410a);
        if (modeR != null) {
            c0Var.f408c = true;
            c0Var.f407b = modeR;
        }
        if (!c0Var.f409d && !c0Var.f408c) {
            return false;
        }
        f.i(drawable, c0Var, this.f410a.getDrawableState());
        return true;
    }

    private boolean k() {
        int i = Build.VERSION.SDK_INT;
        return i > 21 ? this.f413d != null : i == 21;
    }

    void b() {
        Drawable background = this.f410a.getBackground();
        if (background != null) {
            if (k() && a(background)) {
                return;
            }
            c0 c0Var = this.e;
            if (c0Var != null) {
                f.i(background, c0Var, this.f410a.getDrawableState());
                return;
            }
            c0 c0Var2 = this.f413d;
            if (c0Var2 != null) {
                f.i(background, c0Var2, this.f410a.getDrawableState());
            }
        }
    }

    ColorStateList c() {
        c0 c0Var = this.e;
        if (c0Var != null) {
            return c0Var.f406a;
        }
        return null;
    }

    PorterDuff.Mode d() {
        c0 c0Var = this.e;
        if (c0Var != null) {
            return c0Var.f407b;
        }
        return null;
    }

    void e(AttributeSet attributeSet, int i) {
        Context context = this.f410a.getContext();
        int[] iArr = b.a.j.T3;
        e0 e0VarV = e0.v(context, attributeSet, iArr, i, 0);
        View view = this.f410a;
        b.g.k.t.h0(view, view.getContext(), iArr, attributeSet, e0VarV.r(), i, 0);
        try {
            int i2 = b.a.j.U3;
            if (e0VarV.s(i2)) {
                this.f412c = e0VarV.n(i2, -1);
                ColorStateList colorStateListF = this.f411b.f(this.f410a.getContext(), this.f412c);
                if (colorStateListF != null) {
                    h(colorStateListF);
                }
            }
            int i3 = b.a.j.V3;
            if (e0VarV.s(i3)) {
                b.g.k.t.n0(this.f410a, e0VarV.c(i3));
            }
            int i4 = b.a.j.W3;
            if (e0VarV.s(i4)) {
                b.g.k.t.o0(this.f410a, q.e(e0VarV.k(i4, -1), null));
            }
        } finally {
            e0VarV.w();
        }
    }

    void f(Drawable drawable) {
        this.f412c = -1;
        h(null);
        b();
    }

    void g(int i) {
        this.f412c = i;
        f fVar = this.f411b;
        h(fVar != null ? fVar.f(this.f410a.getContext(), i) : null);
        b();
    }

    void h(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.f413d == null) {
                this.f413d = new c0();
            }
            c0 c0Var = this.f413d;
            c0Var.f406a = colorStateList;
            c0Var.f409d = true;
        } else {
            this.f413d = null;
        }
        b();
    }

    void i(ColorStateList colorStateList) {
        if (this.e == null) {
            this.e = new c0();
        }
        c0 c0Var = this.e;
        c0Var.f406a = colorStateList;
        c0Var.f409d = true;
        b();
    }

    void j(PorterDuff.Mode mode) {
        if (this.e == null) {
            this.e = new c0();
        }
        c0 c0Var = this.e;
        c0Var.f407b = mode;
        c0Var.f408c = true;
        b();
    }
}
