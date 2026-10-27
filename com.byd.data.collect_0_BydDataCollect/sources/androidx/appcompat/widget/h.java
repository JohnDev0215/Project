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

    /* JADX INFO: renamed from: a */
    private final ImageView f634a;

    /* JADX INFO: renamed from: b */
    private d0 f635b;

    /* JADX INFO: renamed from: c */
    private d0 f636c;

    /* JADX INFO: renamed from: d */
    private d0 f637d;

    public h(ImageView imageView) {
        this.f634a = imageView;
    }

    private boolean a(Drawable drawable) {
        if (this.f637d == null) {
            this.f637d = new d0();
        }
        d0 d0Var = this.f637d;
        d0Var.a();
        ColorStateList colorStateListA = androidx.core.widget.e.a(this.f634a);
        if (colorStateListA != null) {
            d0Var.f600d = true;
            d0Var.f597a = colorStateListA;
        }
        PorterDuff.Mode modeB = androidx.core.widget.e.b(this.f634a);
        if (modeB != null) {
            d0Var.f599c = true;
            d0Var.f598b = modeB;
        }
        if (!d0Var.f600d && !d0Var.f599c) {
            return false;
        }
        f.i(drawable, d0Var, this.f634a.getDrawableState());
        return true;
    }

    private boolean j() {
        int i2 = Build.VERSION.SDK_INT;
        return i2 > 21 ? this.f635b != null : i2 == 21;
    }

    void b() {
        Drawable drawable = this.f634a.getDrawable();
        if (drawable != null) {
            q.b(drawable);
        }
        if (drawable != null) {
            if (j() && a(drawable)) {
                return;
            }
            d0 d0Var = this.f636c;
            if (d0Var != null) {
                f.i(drawable, d0Var, this.f634a.getDrawableState());
                return;
            }
            d0 d0Var2 = this.f635b;
            if (d0Var2 != null) {
                f.i(drawable, d0Var2, this.f634a.getDrawableState());
            }
        }
    }

    ColorStateList c() {
        d0 d0Var = this.f636c;
        if (d0Var != null) {
            return d0Var.f597a;
        }
        return null;
    }

    PorterDuff.Mode d() {
        d0 d0Var = this.f636c;
        if (d0Var != null) {
            return d0Var.f598b;
        }
        return null;
    }

    boolean e() {
        return Build.VERSION.SDK_INT < 21 || !(this.f634a.getBackground() instanceof RippleDrawable);
    }

    public void f(AttributeSet attributeSet, int i2) {
        int iN;
        Context context = this.f634a.getContext();
        int[] iArr = b.a.j.AppCompatImageView;
        f0 f0VarV = f0.v(context, attributeSet, iArr, i2, 0);
        ImageView imageView = this.f634a;
        b.g.k.t.h0(imageView, imageView.getContext(), iArr, attributeSet, f0VarV.r(), i2, 0);
        try {
            Drawable drawable = this.f634a.getDrawable();
            if (drawable == null && (iN = f0VarV.n(b.a.j.AppCompatImageView_srcCompat, -1)) != -1 && (drawable = b.a.k.a.a.d(this.f634a.getContext(), iN)) != null) {
                this.f634a.setImageDrawable(drawable);
            }
            if (drawable != null) {
                q.b(drawable);
            }
            int i3 = b.a.j.AppCompatImageView_tint;
            if (f0VarV.s(i3)) {
                androidx.core.widget.e.c(this.f634a, f0VarV.c(i3));
            }
            int i4 = b.a.j.AppCompatImageView_tintMode;
            if (f0VarV.s(i4)) {
                androidx.core.widget.e.d(this.f634a, q.e(f0VarV.k(i4, -1), null));
            }
        } finally {
            f0VarV.w();
        }
    }

    public void g(int i2) {
        if (i2 != 0) {
            Drawable drawableD = b.a.k.a.a.d(this.f634a.getContext(), i2);
            if (drawableD != null) {
                q.b(drawableD);
            }
            this.f634a.setImageDrawable(drawableD);
        } else {
            this.f634a.setImageDrawable(null);
        }
        b();
    }

    void h(ColorStateList colorStateList) {
        if (this.f636c == null) {
            this.f636c = new d0();
        }
        d0 d0Var = this.f636c;
        d0Var.f597a = colorStateList;
        d0Var.f600d = true;
        b();
    }

    void i(PorterDuff.Mode mode) {
        if (this.f636c == null) {
            this.f636c = new d0();
        }
        d0 d0Var = this.f636c;
        d0Var.f598b = mode;
        d0Var.f599c = true;
        b();
    }
}
