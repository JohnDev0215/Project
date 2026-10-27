package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

/* JADX INFO: compiled from: AppCompatBackgroundHelper.java */
/* JADX INFO: loaded from: classes.dex */
class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final View f1018a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private q0 f1021d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private q0 f1022e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private q0 f1023f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f1020c = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final i f1019b = i.b();

    d(View view) {
        this.f1018a = view;
    }

    private boolean a(Drawable drawable) {
        if (this.f1023f == null) {
            this.f1023f = new q0();
        }
        q0 q0Var = this.f1023f;
        q0Var.a();
        ColorStateList colorStateListP = i0.u.p(this.f1018a);
        if (colorStateListP != null) {
            q0Var.f1179d = true;
            q0Var.f1176a = colorStateListP;
        }
        PorterDuff.Mode modeQ = i0.u.q(this.f1018a);
        if (modeQ != null) {
            q0Var.f1178c = true;
            q0Var.f1177b = modeQ;
        }
        if (!q0Var.f1179d && !q0Var.f1178c) {
            return false;
        }
        i.i(drawable, q0Var, this.f1018a.getDrawableState());
        return true;
    }

    private boolean k() {
        return this.f1021d != null;
    }

    void b() {
        Drawable background = this.f1018a.getBackground();
        if (background != null) {
            if (k() && a(background)) {
                return;
            }
            q0 q0Var = this.f1022e;
            if (q0Var != null) {
                i.i(background, q0Var, this.f1018a.getDrawableState());
                return;
            }
            q0 q0Var2 = this.f1021d;
            if (q0Var2 != null) {
                i.i(background, q0Var2, this.f1018a.getDrawableState());
            }
        }
    }

    ColorStateList c() {
        q0 q0Var = this.f1022e;
        if (q0Var != null) {
            return q0Var.f1176a;
        }
        return null;
    }

    PorterDuff.Mode d() {
        q0 q0Var = this.f1022e;
        if (q0Var != null) {
            return q0Var.f1177b;
        }
        return null;
    }

    void e(AttributeSet attributeSet, int i7) {
        Context context = this.f1018a.getContext();
        int[] iArr = e.j.ViewBackgroundHelper;
        s0 s0VarV = s0.v(context, attributeSet, iArr, i7, 0);
        View view = this.f1018a;
        i0.u.i0(view, view.getContext(), iArr, attributeSet, s0VarV.r(), i7, 0);
        try {
            int i8 = e.j.ViewBackgroundHelper_android_background;
            if (s0VarV.s(i8)) {
                this.f1020c = s0VarV.n(i8, -1);
                ColorStateList colorStateListF = this.f1019b.f(this.f1018a.getContext(), this.f1020c);
                if (colorStateListF != null) {
                    h(colorStateListF);
                }
            }
            int i9 = e.j.ViewBackgroundHelper_backgroundTint;
            if (s0VarV.s(i9)) {
                i0.u.o0(this.f1018a, s0VarV.c(i9));
            }
            int i10 = e.j.ViewBackgroundHelper_backgroundTintMode;
            if (s0VarV.s(i10)) {
                i0.u.p0(this.f1018a, b0.c(s0VarV.k(i10, -1), null));
            }
        } finally {
            s0VarV.w();
        }
    }

    void f(Drawable drawable) {
        this.f1020c = -1;
        h(null);
        b();
    }

    void g(int i7) {
        this.f1020c = i7;
        i iVar = this.f1019b;
        h(iVar != null ? iVar.f(this.f1018a.getContext(), i7) : null);
        b();
    }

    void h(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.f1021d == null) {
                this.f1021d = new q0();
            }
            q0 q0Var = this.f1021d;
            q0Var.f1176a = colorStateList;
            q0Var.f1179d = true;
        } else {
            this.f1021d = null;
        }
        b();
    }

    void i(ColorStateList colorStateList) {
        if (this.f1022e == null) {
            this.f1022e = new q0();
        }
        q0 q0Var = this.f1022e;
        q0Var.f1176a = colorStateList;
        q0Var.f1179d = true;
        b();
    }

    void j(PorterDuff.Mode mode) {
        if (this.f1022e == null) {
            this.f1022e = new q0();
        }
        q0 q0Var = this.f1022e;
        q0Var.f1177b = mode;
        q0Var.f1178c = true;
        b();
    }
}
