package b.b.q;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/* JADX INFO: compiled from: AppCompatBackgroundHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f1708a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c0 f1711d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c0 f1712e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public c0 f1713f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1710c = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final f f1709b = f.b();

    public d(View view) {
        this.f1708a = view;
    }

    public final boolean a(Drawable drawable) {
        if (this.f1713f == null) {
            this.f1713f = new c0();
        }
        c0 c0Var = this.f1713f;
        c0Var.a();
        ColorStateList colorStateListQ = b.h.m.t.q(this.f1708a);
        if (colorStateListQ != null) {
            c0Var.f1707d = true;
            c0Var.f1704a = colorStateListQ;
        }
        PorterDuff.Mode modeR = b.h.m.t.r(this.f1708a);
        if (modeR != null) {
            c0Var.f1706c = true;
            c0Var.f1705b = modeR;
        }
        if (!c0Var.f1707d && !c0Var.f1706c) {
            return false;
        }
        f.i(drawable, c0Var, this.f1708a.getDrawableState());
        return true;
    }

    public void b() {
        Drawable background = this.f1708a.getBackground();
        if (background != null) {
            if (k() && a(background)) {
                return;
            }
            c0 c0Var = this.f1712e;
            if (c0Var != null) {
                f.i(background, c0Var, this.f1708a.getDrawableState());
                return;
            }
            c0 c0Var2 = this.f1711d;
            if (c0Var2 != null) {
                f.i(background, c0Var2, this.f1708a.getDrawableState());
            }
        }
    }

    public ColorStateList c() {
        c0 c0Var = this.f1712e;
        if (c0Var != null) {
            return c0Var.f1704a;
        }
        return null;
    }

    public PorterDuff.Mode d() {
        c0 c0Var = this.f1712e;
        if (c0Var != null) {
            return c0Var.f1705b;
        }
        return null;
    }

    public void e(AttributeSet attributeSet, int i2) {
        Context context = this.f1708a.getContext();
        int[] iArr = b.b.j.ViewBackgroundHelper;
        e0 e0VarV = e0.v(context, attributeSet, iArr, i2, 0);
        View view = this.f1708a;
        b.h.m.t.i0(view, view.getContext(), iArr, attributeSet, e0VarV.r(), i2, 0);
        try {
            int i3 = b.b.j.ViewBackgroundHelper_android_background;
            if (e0VarV.s(i3)) {
                this.f1710c = e0VarV.n(i3, -1);
                ColorStateList colorStateListF = this.f1709b.f(this.f1708a.getContext(), this.f1710c);
                if (colorStateListF != null) {
                    h(colorStateListF);
                }
            }
            int i4 = b.b.j.ViewBackgroundHelper_backgroundTint;
            if (e0VarV.s(i4)) {
                b.h.m.t.o0(this.f1708a, e0VarV.c(i4));
            }
            int i5 = b.b.j.ViewBackgroundHelper_backgroundTintMode;
            if (e0VarV.s(i5)) {
                b.h.m.t.p0(this.f1708a, q.e(e0VarV.k(i5, -1), null));
            }
        } finally {
            e0VarV.w();
        }
    }

    public void f(Drawable drawable) {
        this.f1710c = -1;
        h(null);
        b();
    }

    public void g(int i2) {
        this.f1710c = i2;
        f fVar = this.f1709b;
        h(fVar != null ? fVar.f(this.f1708a.getContext(), i2) : null);
        b();
    }

    public void h(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.f1711d == null) {
                this.f1711d = new c0();
            }
            c0 c0Var = this.f1711d;
            c0Var.f1704a = colorStateList;
            c0Var.f1707d = true;
        } else {
            this.f1711d = null;
        }
        b();
    }

    public void i(ColorStateList colorStateList) {
        if (this.f1712e == null) {
            this.f1712e = new c0();
        }
        c0 c0Var = this.f1712e;
        c0Var.f1704a = colorStateList;
        c0Var.f1707d = true;
        b();
    }

    public void j(PorterDuff.Mode mode) {
        if (this.f1712e == null) {
            this.f1712e = new c0();
        }
        c0 c0Var = this.f1712e;
        c0Var.f1705b = mode;
        c0Var.f1706c = true;
        b();
    }

    public final boolean k() {
        int i2 = Build.VERSION.SDK_INT;
        return i2 > 21 ? this.f1711d != null : i2 == 21;
    }
}
