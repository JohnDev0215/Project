package r;

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
    public final View f7498a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c0 f7501d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c0 f7502e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public c0 f7503f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f7500c = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final f f7499b = f.b();

    public d(View view) {
        this.f7498a = view;
    }

    public final boolean a(Drawable drawable) {
        if (this.f7503f == null) {
            this.f7503f = new c0();
        }
        c0 c0Var = this.f7503f;
        c0Var.a();
        ColorStateList colorStateListR = o0.t.r(this.f7498a);
        if (colorStateListR != null) {
            c0Var.f7497d = true;
            c0Var.f7494a = colorStateListR;
        }
        PorterDuff.Mode modeS = o0.t.s(this.f7498a);
        if (modeS != null) {
            c0Var.f7496c = true;
            c0Var.f7495b = modeS;
        }
        if (!c0Var.f7497d && !c0Var.f7496c) {
            return false;
        }
        f.i(drawable, c0Var, this.f7498a.getDrawableState());
        return true;
    }

    public void b() {
        Drawable background = this.f7498a.getBackground();
        if (background != null) {
            if (k() && a(background)) {
                return;
            }
            c0 c0Var = this.f7502e;
            if (c0Var != null) {
                f.i(background, c0Var, this.f7498a.getDrawableState());
                return;
            }
            c0 c0Var2 = this.f7501d;
            if (c0Var2 != null) {
                f.i(background, c0Var2, this.f7498a.getDrawableState());
            }
        }
    }

    public ColorStateList c() {
        c0 c0Var = this.f7502e;
        if (c0Var != null) {
            return c0Var.f7494a;
        }
        return null;
    }

    public PorterDuff.Mode d() {
        c0 c0Var = this.f7502e;
        if (c0Var != null) {
            return c0Var.f7495b;
        }
        return null;
    }

    public void e(AttributeSet attributeSet, int i8) {
        Context context = this.f7498a.getContext();
        int[] iArr = R$styleable.ViewBackgroundHelper;
        e0 e0VarV = e0.v(context, attributeSet, iArr, i8, 0);
        View view = this.f7498a;
        o0.t.j0(view, view.getContext(), iArr, attributeSet, e0VarV.r(), i8, 0);
        try {
            int i9 = R$styleable.ViewBackgroundHelper_android_background;
            if (e0VarV.s(i9)) {
                this.f7500c = e0VarV.n(i9, -1);
                ColorStateList colorStateListF = this.f7499b.f(this.f7498a.getContext(), this.f7500c);
                if (colorStateListF != null) {
                    h(colorStateListF);
                }
            }
            int i10 = R$styleable.ViewBackgroundHelper_backgroundTint;
            if (e0VarV.s(i10)) {
                o0.t.p0(this.f7498a, e0VarV.c(i10));
            }
            int i11 = R$styleable.ViewBackgroundHelper_backgroundTintMode;
            if (e0VarV.s(i11)) {
                o0.t.q0(this.f7498a, q.e(e0VarV.k(i11, -1), null));
            }
        } finally {
            e0VarV.w();
        }
    }

    public void f(Drawable drawable) {
        this.f7500c = -1;
        h(null);
        b();
    }

    public void g(int i8) {
        this.f7500c = i8;
        f fVar = this.f7499b;
        h(fVar != null ? fVar.f(this.f7498a.getContext(), i8) : null);
        b();
    }

    public void h(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.f7501d == null) {
                this.f7501d = new c0();
            }
            c0 c0Var = this.f7501d;
            c0Var.f7494a = colorStateList;
            c0Var.f7497d = true;
        } else {
            this.f7501d = null;
        }
        b();
    }

    public void i(ColorStateList colorStateList) {
        if (this.f7502e == null) {
            this.f7502e = new c0();
        }
        c0 c0Var = this.f7502e;
        c0Var.f7494a = colorStateList;
        c0Var.f7497d = true;
        b();
    }

    public void j(PorterDuff.Mode mode) {
        if (this.f7502e == null) {
            this.f7502e = new c0();
        }
        c0 c0Var = this.f7502e;
        c0Var.f7495b = mode;
        c0Var.f7496c = true;
        b();
    }

    public final boolean k() {
        int i8 = Build.VERSION.SDK_INT;
        return i8 > 21 ? this.f7501d != null : i8 == 21;
    }
}
