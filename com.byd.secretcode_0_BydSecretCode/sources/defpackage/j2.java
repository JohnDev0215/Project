package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.appcompat.R$styleable;

/* JADX INFO: compiled from: AppCompatImageHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class j2 {
    public final ImageView a;
    public e3 b;
    public e3 c;
    public e3 d;

    public j2(ImageView imageView) {
        this.a = imageView;
    }

    public final boolean a(Drawable drawable) {
        if (this.d == null) {
            this.d = new e3();
        }
        e3 e3Var = this.d;
        e3Var.a();
        ColorStateList colorStateListA = ma.a(this.a);
        if (colorStateListA != null) {
            e3Var.d = true;
            e3Var.a = colorStateListA;
        }
        PorterDuff.Mode modeB = ma.b(this.a);
        if (modeB != null) {
            e3Var.c = true;
            e3Var.b = modeB;
        }
        if (!e3Var.d && !e3Var.c) {
            return false;
        }
        h2.i(drawable, e3Var, this.a.getDrawableState());
        return true;
    }

    public void b() {
        Drawable drawable = this.a.getDrawable();
        if (drawable != null) {
            s2.b(drawable);
        }
        if (drawable != null) {
            if (j() && a(drawable)) {
                return;
            }
            e3 e3Var = this.c;
            if (e3Var != null) {
                h2.i(drawable, e3Var, this.a.getDrawableState());
                return;
            }
            e3 e3Var2 = this.b;
            if (e3Var2 != null) {
                h2.i(drawable, e3Var2, this.a.getDrawableState());
            }
        }
    }

    public ColorStateList c() {
        e3 e3Var = this.c;
        if (e3Var != null) {
            return e3Var.a;
        }
        return null;
    }

    public PorterDuff.Mode d() {
        e3 e3Var = this.c;
        if (e3Var != null) {
            return e3Var.b;
        }
        return null;
    }

    public boolean e() {
        return Build.VERSION.SDK_INT < 21 || !(this.a.getBackground() instanceof RippleDrawable);
    }

    public void f(AttributeSet attributeSet, int i) {
        int iN;
        Context context = this.a.getContext();
        int[] iArr = R$styleable.AppCompatImageView;
        g3 g3VarV = g3.v(context, attributeSet, iArr, i, 0);
        ImageView imageView = this.a;
        t9.h0(imageView, imageView.getContext(), iArr, attributeSet, g3VarV.r(), i, 0);
        try {
            Drawable drawable = this.a.getDrawable();
            if (drawable == null && (iN = g3VarV.n(R$styleable.AppCompatImageView_srcCompat, -1)) != -1 && (drawable = u0.d(this.a.getContext(), iN)) != null) {
                this.a.setImageDrawable(drawable);
            }
            if (drawable != null) {
                s2.b(drawable);
            }
            int i2 = R$styleable.AppCompatImageView_tint;
            if (g3VarV.s(i2)) {
                ma.c(this.a, g3VarV.c(i2));
            }
            int i3 = R$styleable.AppCompatImageView_tintMode;
            if (g3VarV.s(i3)) {
                ma.d(this.a, s2.e(g3VarV.k(i3, -1), null));
            }
        } finally {
            g3VarV.w();
        }
    }

    public void g(int i) {
        if (i != 0) {
            Drawable drawableD = u0.d(this.a.getContext(), i);
            if (drawableD != null) {
                s2.b(drawableD);
            }
            this.a.setImageDrawable(drawableD);
        } else {
            this.a.setImageDrawable(null);
        }
        b();
    }

    public void h(ColorStateList colorStateList) {
        if (this.c == null) {
            this.c = new e3();
        }
        e3 e3Var = this.c;
        e3Var.a = colorStateList;
        e3Var.d = true;
        b();
    }

    public void i(PorterDuff.Mode mode) {
        if (this.c == null) {
            this.c = new e3();
        }
        e3 e3Var = this.c;
        e3Var.b = mode;
        e3Var.c = true;
        b();
    }

    public final boolean j() {
        int i = Build.VERSION.SDK_INT;
        return i > 21 ? this.b != null : i == 21;
    }
}
