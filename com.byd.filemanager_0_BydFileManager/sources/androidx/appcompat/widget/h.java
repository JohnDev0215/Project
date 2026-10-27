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
    private final ImageView f492a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private c0 f493b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private c0 f494c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private c0 f495d;

    public h(ImageView imageView) {
        this.f492a = imageView;
    }

    private boolean a(Drawable drawable) {
        if (this.f495d == null) {
            this.f495d = new c0();
        }
        c0 c0Var = this.f495d;
        c0Var.a();
        ColorStateList colorStateListA = androidx.core.widget.e.a(this.f492a);
        if (colorStateListA != null) {
            c0Var.f459d = true;
            c0Var.f456a = colorStateListA;
        }
        PorterDuff.Mode modeB = androidx.core.widget.e.b(this.f492a);
        if (modeB != null) {
            c0Var.f458c = true;
            c0Var.f457b = modeB;
        }
        if (!c0Var.f459d && !c0Var.f458c) {
            return false;
        }
        f.i(drawable, c0Var, this.f492a.getDrawableState());
        return true;
    }

    private boolean j() {
        int i = Build.VERSION.SDK_INT;
        return i > 21 ? this.f493b != null : i == 21;
    }

    void b() {
        Drawable drawable = this.f492a.getDrawable();
        if (drawable != null) {
            q.b(drawable);
        }
        if (drawable != null) {
            if (j() && a(drawable)) {
                return;
            }
            c0 c0Var = this.f494c;
            if (c0Var != null) {
                f.i(drawable, c0Var, this.f492a.getDrawableState());
                return;
            }
            c0 c0Var2 = this.f493b;
            if (c0Var2 != null) {
                f.i(drawable, c0Var2, this.f492a.getDrawableState());
            }
        }
    }

    ColorStateList c() {
        c0 c0Var = this.f494c;
        if (c0Var != null) {
            return c0Var.f456a;
        }
        return null;
    }

    PorterDuff.Mode d() {
        c0 c0Var = this.f494c;
        if (c0Var != null) {
            return c0Var.f457b;
        }
        return null;
    }

    boolean e() {
        return Build.VERSION.SDK_INT < 21 || !(this.f492a.getBackground() instanceof RippleDrawable);
    }

    public void f(AttributeSet attributeSet, int i) {
        int iN;
        Context context = this.f492a.getContext();
        int[] iArr = b.a.j.T;
        e0 e0VarV = e0.v(context, attributeSet, iArr, i, 0);
        ImageView imageView = this.f492a;
        b.g.k.t.h0(imageView, imageView.getContext(), iArr, attributeSet, e0VarV.r(), i, 0);
        try {
            Drawable drawable = this.f492a.getDrawable();
            if (drawable == null && (iN = e0VarV.n(b.a.j.U, -1)) != -1 && (drawable = b.a.k.a.a.d(this.f492a.getContext(), iN)) != null) {
                this.f492a.setImageDrawable(drawable);
            }
            if (drawable != null) {
                q.b(drawable);
            }
            int i2 = b.a.j.V;
            if (e0VarV.s(i2)) {
                androidx.core.widget.e.c(this.f492a, e0VarV.c(i2));
            }
            int i3 = b.a.j.W;
            if (e0VarV.s(i3)) {
                androidx.core.widget.e.d(this.f492a, q.e(e0VarV.k(i3, -1), null));
            }
        } finally {
            e0VarV.w();
        }
    }

    public void g(int i) {
        if (i != 0) {
            Drawable drawableD = b.a.k.a.a.d(this.f492a.getContext(), i);
            if (drawableD != null) {
                q.b(drawableD);
            }
            this.f492a.setImageDrawable(drawableD);
        } else {
            this.f492a.setImageDrawable(null);
        }
        b();
    }

    void h(ColorStateList colorStateList) {
        if (this.f494c == null) {
            this.f494c = new c0();
        }
        c0 c0Var = this.f494c;
        c0Var.f456a = colorStateList;
        c0Var.f459d = true;
        b();
    }

    void i(PorterDuff.Mode mode) {
        if (this.f494c == null) {
            this.f494c = new c0();
        }
        c0 c0Var = this.f494c;
        c0Var.f457b = mode;
        c0Var.f458c = true;
        b();
    }
}
