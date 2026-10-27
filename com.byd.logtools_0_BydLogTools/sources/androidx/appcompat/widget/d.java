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
    private final View f576a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private c0 f579d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private c0 f580e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private c0 f581f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f578c = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final f f577b = f.b();

    d(View view) {
        this.f576a = view;
    }

    private boolean a(Drawable drawable) {
        if (this.f581f == null) {
            this.f581f = new c0();
        }
        c0 c0Var = this.f581f;
        c0Var.a();
        ColorStateList colorStateListQ = b.g.k.t.q(this.f576a);
        if (colorStateListQ != null) {
            c0Var.f575d = true;
            c0Var.f572a = colorStateListQ;
        }
        PorterDuff.Mode modeR = b.g.k.t.r(this.f576a);
        if (modeR != null) {
            c0Var.f574c = true;
            c0Var.f573b = modeR;
        }
        if (!c0Var.f575d && !c0Var.f574c) {
            return false;
        }
        f.i(drawable, c0Var, this.f576a.getDrawableState());
        return true;
    }

    private boolean k() {
        int i = Build.VERSION.SDK_INT;
        return i > 21 ? this.f579d != null : i == 21;
    }

    void b() {
        Drawable background = this.f576a.getBackground();
        if (background != null) {
            if (k() && a(background)) {
                return;
            }
            c0 c0Var = this.f580e;
            if (c0Var != null) {
                f.i(background, c0Var, this.f576a.getDrawableState());
                return;
            }
            c0 c0Var2 = this.f579d;
            if (c0Var2 != null) {
                f.i(background, c0Var2, this.f576a.getDrawableState());
            }
        }
    }

    ColorStateList c() {
        c0 c0Var = this.f580e;
        if (c0Var != null) {
            return c0Var.f572a;
        }
        return null;
    }

    PorterDuff.Mode d() {
        c0 c0Var = this.f580e;
        if (c0Var != null) {
            return c0Var.f573b;
        }
        return null;
    }

    void e(AttributeSet attributeSet, int i) {
        Context context = this.f576a.getContext();
        int[] iArr = b.a.j.T3;
        e0 e0VarV = e0.v(context, attributeSet, iArr, i, 0);
        View view = this.f576a;
        b.g.k.t.h0(view, view.getContext(), iArr, attributeSet, e0VarV.r(), i, 0);
        try {
            int i2 = b.a.j.U3;
            if (e0VarV.s(i2)) {
                this.f578c = e0VarV.n(i2, -1);
                ColorStateList colorStateListF = this.f577b.f(this.f576a.getContext(), this.f578c);
                if (colorStateListF != null) {
                    h(colorStateListF);
                }
            }
            int i3 = b.a.j.V3;
            if (e0VarV.s(i3)) {
                b.g.k.t.n0(this.f576a, e0VarV.c(i3));
            }
            int i4 = b.a.j.W3;
            if (e0VarV.s(i4)) {
                b.g.k.t.o0(this.f576a, q.e(e0VarV.k(i4, -1), null));
            }
        } finally {
            e0VarV.w();
        }
    }

    void f(Drawable drawable) {
        this.f578c = -1;
        h(null);
        b();
    }

    void g(int i) {
        this.f578c = i;
        f fVar = this.f577b;
        h(fVar != null ? fVar.f(this.f576a.getContext(), i) : null);
        b();
    }

    void h(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.f579d == null) {
                this.f579d = new c0();
            }
            c0 c0Var = this.f579d;
            c0Var.f572a = colorStateList;
            c0Var.f575d = true;
        } else {
            this.f579d = null;
        }
        b();
    }

    void i(ColorStateList colorStateList) {
        if (this.f580e == null) {
            this.f580e = new c0();
        }
        c0 c0Var = this.f580e;
        c0Var.f572a = colorStateList;
        c0Var.f575d = true;
        b();
    }

    void j(PorterDuff.Mode mode) {
        if (this.f580e == null) {
            this.f580e = new c0();
        }
        c0 c0Var = this.f580e;
        c0Var.f573b = mode;
        c0Var.f574c = true;
        b();
    }
}
