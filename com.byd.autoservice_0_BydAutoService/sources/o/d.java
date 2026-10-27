package o;

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
    public final View f4239a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c0 f4242d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c0 f4243e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public c0 f4244f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4241c = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final f f4240b = f.b();

    public d(View view) {
        this.f4239a = view;
    }

    public final boolean a(Drawable drawable) {
        if (this.f4244f == null) {
            this.f4244f = new c0();
        }
        c0 c0Var = this.f4244f;
        c0Var.a();
        ColorStateList colorStateListQ = l0.t.q(this.f4239a);
        if (colorStateListQ != null) {
            c0Var.f4238d = true;
            c0Var.f4235a = colorStateListQ;
        }
        PorterDuff.Mode modeR = l0.t.r(this.f4239a);
        if (modeR != null) {
            c0Var.f4237c = true;
            c0Var.f4236b = modeR;
        }
        if (!c0Var.f4238d && !c0Var.f4237c) {
            return false;
        }
        f.i(drawable, c0Var, this.f4239a.getDrawableState());
        return true;
    }

    public void b() {
        Drawable background = this.f4239a.getBackground();
        if (background != null) {
            if (k() && a(background)) {
                return;
            }
            c0 c0Var = this.f4243e;
            if (c0Var != null) {
                f.i(background, c0Var, this.f4239a.getDrawableState());
                return;
            }
            c0 c0Var2 = this.f4242d;
            if (c0Var2 != null) {
                f.i(background, c0Var2, this.f4239a.getDrawableState());
            }
        }
    }

    public ColorStateList c() {
        c0 c0Var = this.f4243e;
        if (c0Var != null) {
            return c0Var.f4235a;
        }
        return null;
    }

    public PorterDuff.Mode d() {
        c0 c0Var = this.f4243e;
        if (c0Var != null) {
            return c0Var.f4236b;
        }
        return null;
    }

    public void e(AttributeSet attributeSet, int i3) {
        Context context = this.f4239a.getContext();
        int[] iArr = R$styleable.ViewBackgroundHelper;
        e0 e0VarV = e0.v(context, attributeSet, iArr, i3, 0);
        View view = this.f4239a;
        l0.t.h0(view, view.getContext(), iArr, attributeSet, e0VarV.r(), i3, 0);
        try {
            int i4 = R$styleable.ViewBackgroundHelper_android_background;
            if (e0VarV.s(i4)) {
                this.f4241c = e0VarV.n(i4, -1);
                ColorStateList colorStateListF = this.f4240b.f(this.f4239a.getContext(), this.f4241c);
                if (colorStateListF != null) {
                    h(colorStateListF);
                }
            }
            int i5 = R$styleable.ViewBackgroundHelper_backgroundTint;
            if (e0VarV.s(i5)) {
                l0.t.n0(this.f4239a, e0VarV.c(i5));
            }
            int i6 = R$styleable.ViewBackgroundHelper_backgroundTintMode;
            if (e0VarV.s(i6)) {
                l0.t.o0(this.f4239a, q.e(e0VarV.k(i6, -1), null));
            }
        } finally {
            e0VarV.w();
        }
    }

    public void f(Drawable drawable) {
        this.f4241c = -1;
        h(null);
        b();
    }

    public void g(int i3) {
        this.f4241c = i3;
        f fVar = this.f4240b;
        h(fVar != null ? fVar.f(this.f4239a.getContext(), i3) : null);
        b();
    }

    public void h(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.f4242d == null) {
                this.f4242d = new c0();
            }
            c0 c0Var = this.f4242d;
            c0Var.f4235a = colorStateList;
            c0Var.f4238d = true;
        } else {
            this.f4242d = null;
        }
        b();
    }

    public void i(ColorStateList colorStateList) {
        if (this.f4243e == null) {
            this.f4243e = new c0();
        }
        c0 c0Var = this.f4243e;
        c0Var.f4235a = colorStateList;
        c0Var.f4238d = true;
        b();
    }

    public void j(PorterDuff.Mode mode) {
        if (this.f4243e == null) {
            this.f4243e = new c0();
        }
        c0 c0Var = this.f4243e;
        c0Var.f4236b = mode;
        c0Var.f4237c = true;
        b();
    }

    public final boolean k() {
        int i3 = Build.VERSION.SDK_INT;
        return i3 > 21 ? this.f4242d != null : i3 == 21;
    }
}
