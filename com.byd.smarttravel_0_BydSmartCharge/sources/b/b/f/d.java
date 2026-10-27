package b.b.f;

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
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f752a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c0 f755d;
    public c0 e;
    public c0 f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f754c = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final f f753b = f.b();

    public d(View view) {
        this.f752a = view;
    }

    public final boolean a(Drawable drawable) {
        if (this.f == null) {
            this.f = new c0();
        }
        c0 c0Var = this.f;
        c0Var.a();
        ColorStateList colorStateListQ = b.g.j.t.q(this.f752a);
        if (colorStateListQ != null) {
            c0Var.f751d = true;
            c0Var.f748a = colorStateListQ;
        }
        PorterDuff.Mode modeR = b.g.j.t.r(this.f752a);
        if (modeR != null) {
            c0Var.f750c = true;
            c0Var.f749b = modeR;
        }
        if (!c0Var.f751d && !c0Var.f750c) {
            return false;
        }
        f.i(drawable, c0Var, this.f752a.getDrawableState());
        return true;
    }

    public void b() {
        Drawable background = this.f752a.getBackground();
        if (background != null) {
            if (k() && a(background)) {
                return;
            }
            c0 c0Var = this.e;
            if (c0Var != null) {
                f.i(background, c0Var, this.f752a.getDrawableState());
                return;
            }
            c0 c0Var2 = this.f755d;
            if (c0Var2 != null) {
                f.i(background, c0Var2, this.f752a.getDrawableState());
            }
        }
    }

    public ColorStateList c() {
        c0 c0Var = this.e;
        if (c0Var != null) {
            return c0Var.f748a;
        }
        return null;
    }

    public PorterDuff.Mode d() {
        c0 c0Var = this.e;
        if (c0Var != null) {
            return c0Var.f749b;
        }
        return null;
    }

    public void e(AttributeSet attributeSet, int i) {
        Context context = this.f752a.getContext();
        int[] iArr = R$styleable.ViewBackgroundHelper;
        e0 e0VarV = e0.v(context, attributeSet, iArr, i, 0);
        View view = this.f752a;
        b.g.j.t.h0(view, view.getContext(), iArr, attributeSet, e0VarV.r(), i, 0);
        try {
            int i2 = R$styleable.ViewBackgroundHelper_android_background;
            if (e0VarV.s(i2)) {
                this.f754c = e0VarV.n(i2, -1);
                ColorStateList colorStateListF = this.f753b.f(this.f752a.getContext(), this.f754c);
                if (colorStateListF != null) {
                    h(colorStateListF);
                }
            }
            int i3 = R$styleable.ViewBackgroundHelper_backgroundTint;
            if (e0VarV.s(i3)) {
                b.g.j.t.n0(this.f752a, e0VarV.c(i3));
            }
            int i4 = R$styleable.ViewBackgroundHelper_backgroundTintMode;
            if (e0VarV.s(i4)) {
                b.g.j.t.o0(this.f752a, q.e(e0VarV.k(i4, -1), null));
            }
        } finally {
            e0VarV.w();
        }
    }

    public void f(Drawable drawable) {
        this.f754c = -1;
        h(null);
        b();
    }

    public void g(int i) {
        this.f754c = i;
        f fVar = this.f753b;
        h(fVar != null ? fVar.f(this.f752a.getContext(), i) : null);
        b();
    }

    public void h(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.f755d == null) {
                this.f755d = new c0();
            }
            c0 c0Var = this.f755d;
            c0Var.f748a = colorStateList;
            c0Var.f751d = true;
        } else {
            this.f755d = null;
        }
        b();
    }

    public void i(ColorStateList colorStateList) {
        if (this.e == null) {
            this.e = new c0();
        }
        c0 c0Var = this.e;
        c0Var.f748a = colorStateList;
        c0Var.f751d = true;
        b();
    }

    public void j(PorterDuff.Mode mode) {
        if (this.e == null) {
            this.e = new c0();
        }
        c0 c0Var = this.e;
        c0Var.f749b = mode;
        c0Var.f750c = true;
        b();
    }

    public final boolean k() {
        int i = Build.VERSION.SDK_INT;
        return i > 21 ? this.f755d != null : i == 21;
    }
}
