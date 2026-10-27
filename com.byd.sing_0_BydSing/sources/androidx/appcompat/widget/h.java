package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

/* JADX INFO: compiled from: AppCompatImageHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ImageView f967a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private c0 f968b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private c0 f969c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private c0 f970d;

    public h(ImageView imageView) {
        this.f967a = imageView;
    }

    private boolean a(Drawable drawable) {
        if (this.f970d == null) {
            this.f970d = new c0();
        }
        c0 c0Var = this.f970d;
        c0Var.a();
        ColorStateList colorStateListA = androidx.core.widget.e.a(this.f967a);
        if (colorStateListA != null) {
            c0Var.f938d = true;
            c0Var.f935a = colorStateListA;
        }
        PorterDuff.Mode modeB = androidx.core.widget.e.b(this.f967a);
        if (modeB != null) {
            c0Var.f937c = true;
            c0Var.f936b = modeB;
        }
        if (!c0Var.f938d && !c0Var.f937c) {
            return false;
        }
        f.i(drawable, c0Var, this.f967a.getDrawableState());
        return true;
    }

    private boolean j() {
        int i = Build.VERSION.SDK_INT;
        return i > 21 ? this.f968b != null : i == 21;
    }

    void b() {
        Drawable drawable = this.f967a.getDrawable();
        if (drawable != null) {
            q.b(drawable);
        }
        if (drawable != null) {
            if (j() && a(drawable)) {
                return;
            }
            c0 c0Var = this.f969c;
            if (c0Var != null) {
                f.i(drawable, c0Var, this.f967a.getDrawableState());
                return;
            }
            c0 c0Var2 = this.f968b;
            if (c0Var2 != null) {
                f.i(drawable, c0Var2, this.f967a.getDrawableState());
            }
        }
    }

    ColorStateList c() {
        c0 c0Var = this.f969c;
        if (c0Var != null) {
            return c0Var.f935a;
        }
        return null;
    }

    PorterDuff.Mode d() {
        c0 c0Var = this.f969c;
        if (c0Var != null) {
            return c0Var.f936b;
        }
        return null;
    }

    boolean e() {
        return Build.VERSION.SDK_INT < 21 || !(this.f967a.getBackground() instanceof RippleDrawable);
    }

    public void f(AttributeSet attributeSet, int i) {
        int iN;
        Context context = this.f967a.getContext();
        int[] iArr = c.a.j.T;
        e0 e0VarV = e0.v(context, attributeSet, iArr, i, 0);
        ImageView imageView = this.f967a;
        c.g.k.t.h0(imageView, imageView.getContext(), iArr, attributeSet, e0VarV.r(), i, 0);
        try {
            Drawable drawable = this.f967a.getDrawable();
            if (drawable == null && (iN = e0VarV.n(c.a.j.U, -1)) != -1 && (drawable = c.a.k.a.a.d(this.f967a.getContext(), iN)) != null) {
                this.f967a.setImageDrawable(drawable);
            }
            if (drawable != null) {
                q.b(drawable);
            }
            int i2 = c.a.j.V;
            if (e0VarV.s(i2)) {
                androidx.core.widget.e.c(this.f967a, e0VarV.c(i2));
            }
            int i3 = c.a.j.W;
            if (e0VarV.s(i3)) {
                androidx.core.widget.e.d(this.f967a, q.e(e0VarV.k(i3, -1), null));
            }
        } finally {
            e0VarV.w();
        }
    }

    public void g(int i) {
        if (i != 0) {
            Drawable drawableD = c.a.k.a.a.d(this.f967a.getContext(), i);
            if (drawableD != null) {
                q.b(drawableD);
            }
            this.f967a.setImageDrawable(drawableD);
        } else {
            this.f967a.setImageDrawable(null);
        }
        b();
    }

    void h(ColorStateList colorStateList) {
        if (this.f969c == null) {
            this.f969c = new c0();
        }
        c0 c0Var = this.f969c;
        c0Var.f935a = colorStateList;
        c0Var.f938d = true;
        b();
    }

    void i(PorterDuff.Mode mode) {
        if (this.f969c == null) {
            this.f969c = new c0();
        }
        c0 c0Var = this.f969c;
        c0Var.f936b = mode;
        c0Var.f937c = true;
        b();
    }
}
