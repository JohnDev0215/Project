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
    public final View f999a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c0 f1002d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c0 f1003e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public c0 f1004f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1001c = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final f f1000b = f.b();

    public d(View view) {
        this.f999a = view;
    }

    public final boolean a(Drawable drawable) {
        if (this.f1004f == null) {
            this.f1004f = new c0();
        }
        c0 c0Var = this.f1004f;
        c0Var.a();
        ColorStateList colorStateListN = b.f.m.s.n(this.f999a);
        if (colorStateListN != null) {
            c0Var.f998d = true;
            c0Var.f995a = colorStateListN;
        }
        PorterDuff.Mode modeO = b.f.m.s.o(this.f999a);
        if (modeO != null) {
            c0Var.f997c = true;
            c0Var.f996b = modeO;
        }
        if (!c0Var.f998d && !c0Var.f997c) {
            return false;
        }
        f.i(drawable, c0Var, this.f999a.getDrawableState());
        return true;
    }

    public void b() {
        Drawable background = this.f999a.getBackground();
        if (background != null) {
            if (k() && a(background)) {
                return;
            }
            c0 c0Var = this.f1003e;
            if (c0Var != null) {
                f.i(background, c0Var, this.f999a.getDrawableState());
                return;
            }
            c0 c0Var2 = this.f1002d;
            if (c0Var2 != null) {
                f.i(background, c0Var2, this.f999a.getDrawableState());
            }
        }
    }

    public ColorStateList c() {
        c0 c0Var = this.f1003e;
        if (c0Var != null) {
            return c0Var.f995a;
        }
        return null;
    }

    public PorterDuff.Mode d() {
        c0 c0Var = this.f1003e;
        if (c0Var != null) {
            return c0Var.f996b;
        }
        return null;
    }

    public void e(AttributeSet attributeSet, int i2) {
        Context context = this.f999a.getContext();
        int[] iArr = b.b.j.T3;
        e0 e0VarU = e0.u(context, attributeSet, iArr, i2, 0);
        View view = this.f999a;
        b.f.m.s.R(view, view.getContext(), iArr, attributeSet, e0VarU.q(), i2, 0);
        try {
            int i3 = b.b.j.U3;
            if (e0VarU.r(i3)) {
                this.f1001c = e0VarU.m(i3, -1);
                ColorStateList colorStateListF = this.f1000b.f(this.f999a.getContext(), this.f1001c);
                if (colorStateListF != null) {
                    h(colorStateListF);
                }
            }
            int i4 = b.b.j.V3;
            if (e0VarU.r(i4)) {
                b.f.m.s.V(this.f999a, e0VarU.c(i4));
            }
            int i5 = b.b.j.W3;
            if (e0VarU.r(i5)) {
                b.f.m.s.W(this.f999a, q.e(e0VarU.j(i5, -1), null));
            }
        } finally {
            e0VarU.v();
        }
    }

    public void f(Drawable drawable) {
        this.f1001c = -1;
        h(null);
        b();
    }

    public void g(int i2) {
        this.f1001c = i2;
        f fVar = this.f1000b;
        h(fVar != null ? fVar.f(this.f999a.getContext(), i2) : null);
        b();
    }

    public void h(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.f1002d == null) {
                this.f1002d = new c0();
            }
            c0 c0Var = this.f1002d;
            c0Var.f995a = colorStateList;
            c0Var.f998d = true;
        } else {
            this.f1002d = null;
        }
        b();
    }

    public void i(ColorStateList colorStateList) {
        if (this.f1003e == null) {
            this.f1003e = new c0();
        }
        c0 c0Var = this.f1003e;
        c0Var.f995a = colorStateList;
        c0Var.f998d = true;
        b();
    }

    public void j(PorterDuff.Mode mode) {
        if (this.f1003e == null) {
            this.f1003e = new c0();
        }
        c0 c0Var = this.f1003e;
        c0Var.f996b = mode;
        c0Var.f997c = true;
        b();
    }

    public final boolean k() {
        int i2 = Build.VERSION.SDK_INT;
        return i2 > 21 ? this.f1002d != null : i2 == 21;
    }
}
