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
    private final ImageView f448a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private c0 f449b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private c0 f450c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private c0 f451d;

    public h(ImageView imageView) {
        this.f448a = imageView;
    }

    private boolean a(Drawable drawable) {
        if (this.f451d == null) {
            this.f451d = new c0();
        }
        c0 c0Var = this.f451d;
        c0Var.a();
        ColorStateList colorStateListA = androidx.core.widget.e.a(this.f448a);
        if (colorStateListA != null) {
            c0Var.f419d = true;
            c0Var.f416a = colorStateListA;
        }
        PorterDuff.Mode modeB = androidx.core.widget.e.b(this.f448a);
        if (modeB != null) {
            c0Var.f418c = true;
            c0Var.f417b = modeB;
        }
        if (!c0Var.f419d && !c0Var.f418c) {
            return false;
        }
        f.i(drawable, c0Var, this.f448a.getDrawableState());
        return true;
    }

    private boolean j() {
        int i = Build.VERSION.SDK_INT;
        return i > 21 ? this.f449b != null : i == 21;
    }

    void b() {
        Drawable drawable = this.f448a.getDrawable();
        if (drawable != null) {
            q.b(drawable);
        }
        if (drawable != null) {
            if (j() && a(drawable)) {
                return;
            }
            c0 c0Var = this.f450c;
            if (c0Var != null) {
                f.i(drawable, c0Var, this.f448a.getDrawableState());
                return;
            }
            c0 c0Var2 = this.f449b;
            if (c0Var2 != null) {
                f.i(drawable, c0Var2, this.f448a.getDrawableState());
            }
        }
    }

    ColorStateList c() {
        c0 c0Var = this.f450c;
        if (c0Var != null) {
            return c0Var.f416a;
        }
        return null;
    }

    PorterDuff.Mode d() {
        c0 c0Var = this.f450c;
        if (c0Var != null) {
            return c0Var.f417b;
        }
        return null;
    }

    boolean e() {
        return Build.VERSION.SDK_INT < 21 || !(this.f448a.getBackground() instanceof RippleDrawable);
    }

    public void f(AttributeSet attributeSet, int i) {
        int iN;
        Context context = this.f448a.getContext();
        int[] iArr = b.a.j.T;
        e0 e0VarV = e0.v(context, attributeSet, iArr, i, 0);
        ImageView imageView = this.f448a;
        b.g.k.t.h0(imageView, imageView.getContext(), iArr, attributeSet, e0VarV.r(), i, 0);
        try {
            Drawable drawable = this.f448a.getDrawable();
            if (drawable == null && (iN = e0VarV.n(b.a.j.U, -1)) != -1 && (drawable = b.a.k.a.a.d(this.f448a.getContext(), iN)) != null) {
                this.f448a.setImageDrawable(drawable);
            }
            if (drawable != null) {
                q.b(drawable);
            }
            int i2 = b.a.j.V;
            if (e0VarV.s(i2)) {
                androidx.core.widget.e.c(this.f448a, e0VarV.c(i2));
            }
            int i3 = b.a.j.W;
            if (e0VarV.s(i3)) {
                androidx.core.widget.e.d(this.f448a, q.e(e0VarV.k(i3, -1), null));
            }
        } finally {
            e0VarV.w();
        }
    }

    public void g(int i) {
        if (i != 0) {
            Drawable drawableD = b.a.k.a.a.d(this.f448a.getContext(), i);
            if (drawableD != null) {
                q.b(drawableD);
            }
            this.f448a.setImageDrawable(drawableD);
        } else {
            this.f448a.setImageDrawable(null);
        }
        b();
    }

    void h(ColorStateList colorStateList) {
        if (this.f450c == null) {
            this.f450c = new c0();
        }
        c0 c0Var = this.f450c;
        c0Var.f416a = colorStateList;
        c0Var.f419d = true;
        b();
    }

    void i(PorterDuff.Mode mode) {
        if (this.f450c == null) {
            this.f450c = new c0();
        }
        c0 c0Var = this.f450c;
        c0Var.f417b = mode;
        c0Var.f418c = true;
        b();
    }
}
