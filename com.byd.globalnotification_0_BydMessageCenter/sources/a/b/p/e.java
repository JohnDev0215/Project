package a.b.p;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f245a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public v0 f248d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public v0 f249e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public v0 f250f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f247c = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final j f246b = j.a();

    public e(View view) {
        this.f245a = view;
    }

    public void a() {
        Drawable background = this.f245a.getBackground();
        if (background != null) {
            boolean z = true;
            if (this.f248d != null) {
                if (this.f250f == null) {
                    this.f250f = new v0();
                }
                v0 v0Var = this.f250f;
                v0Var.f366a = null;
                v0Var.f369d = false;
                v0Var.f367b = null;
                v0Var.f368c = false;
                ColorStateList colorStateListI = a.h.k.q.i(this.f245a);
                if (colorStateListI != null) {
                    v0Var.f369d = true;
                    v0Var.f366a = colorStateListI;
                }
                PorterDuff.Mode backgroundTintMode = this.f245a.getBackgroundTintMode();
                if (backgroundTintMode != null) {
                    v0Var.f368c = true;
                    v0Var.f367b = backgroundTintMode;
                }
                if (v0Var.f369d || v0Var.f368c) {
                    j.f(background, v0Var, this.f245a.getDrawableState());
                } else {
                    z = false;
                }
                if (z) {
                    return;
                }
            }
            v0 v0Var2 = this.f249e;
            if (v0Var2 != null) {
                j.f(background, v0Var2, this.f245a.getDrawableState());
                return;
            }
            v0 v0Var3 = this.f248d;
            if (v0Var3 != null) {
                j.f(background, v0Var3, this.f245a.getDrawableState());
            }
        }
    }

    public ColorStateList b() {
        v0 v0Var = this.f249e;
        if (v0Var != null) {
            return v0Var.f366a;
        }
        return null;
    }

    public PorterDuff.Mode c() {
        v0 v0Var = this.f249e;
        if (v0Var != null) {
            return v0Var.f367b;
        }
        return null;
    }

    public void d(AttributeSet attributeSet, int i) {
        x0 x0VarQ = x0.q(this.f245a.getContext(), attributeSet, a.b.j.ViewBackgroundHelper, i, 0);
        View view = this.f245a;
        a.h.k.q.T(view, view.getContext(), a.b.j.ViewBackgroundHelper, attributeSet, x0VarQ.f390b, i, 0);
        try {
            if (x0VarQ.o(a.b.j.ViewBackgroundHelper_android_background)) {
                this.f247c = x0VarQ.l(a.b.j.ViewBackgroundHelper_android_background, -1);
                ColorStateList colorStateListD = this.f246b.d(this.f245a.getContext(), this.f247c);
                if (colorStateListD != null) {
                    g(colorStateListD);
                }
            }
            if (x0VarQ.o(a.b.j.ViewBackgroundHelper_backgroundTint)) {
                this.f245a.setBackgroundTintList(x0VarQ.c(a.b.j.ViewBackgroundHelper_backgroundTint));
            }
            if (x0VarQ.o(a.b.j.ViewBackgroundHelper_backgroundTintMode)) {
                this.f245a.setBackgroundTintMode(e0.c(x0VarQ.j(a.b.j.ViewBackgroundHelper_backgroundTintMode, -1), null));
            }
            x0VarQ.f390b.recycle();
        } catch (Throwable th) {
            x0VarQ.f390b.recycle();
            throw th;
        }
    }

    public void e() {
        this.f247c = -1;
        g(null);
        a();
    }

    public void f(int i) {
        this.f247c = i;
        j jVar = this.f246b;
        g(jVar != null ? jVar.d(this.f245a.getContext(), i) : null);
        a();
    }

    public void g(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.f248d == null) {
                this.f248d = new v0();
            }
            v0 v0Var = this.f248d;
            v0Var.f366a = colorStateList;
            v0Var.f369d = true;
        } else {
            this.f248d = null;
        }
        a();
    }

    public void h(ColorStateList colorStateList) {
        if (this.f249e == null) {
            this.f249e = new v0();
        }
        v0 v0Var = this.f249e;
        v0Var.f366a = colorStateList;
        v0Var.f369d = true;
        a();
    }

    public void i(PorterDuff.Mode mode) {
        if (this.f249e == null) {
            this.f249e = new v0();
        }
        v0 v0Var = this.f249e;
        v0Var.f367b = mode;
        v0Var.f368c = true;
        a();
    }
}
