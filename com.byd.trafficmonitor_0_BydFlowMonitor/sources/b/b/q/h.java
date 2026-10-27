package b.b.q;

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
    public final ImageView f1747a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public c0 f1748b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c0 f1749c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c0 f1750d;

    public h(ImageView imageView) {
        this.f1747a = imageView;
    }

    public final boolean a(Drawable drawable) {
        if (this.f1750d == null) {
            this.f1750d = new c0();
        }
        c0 c0Var = this.f1750d;
        c0Var.a();
        ColorStateList colorStateListA = b.h.n.e.a(this.f1747a);
        if (colorStateListA != null) {
            c0Var.f1707d = true;
            c0Var.f1704a = colorStateListA;
        }
        PorterDuff.Mode modeB = b.h.n.e.b(this.f1747a);
        if (modeB != null) {
            c0Var.f1706c = true;
            c0Var.f1705b = modeB;
        }
        if (!c0Var.f1707d && !c0Var.f1706c) {
            return false;
        }
        f.i(drawable, c0Var, this.f1747a.getDrawableState());
        return true;
    }

    public void b() {
        Drawable drawable = this.f1747a.getDrawable();
        if (drawable != null) {
            q.b(drawable);
        }
        if (drawable != null) {
            if (j() && a(drawable)) {
                return;
            }
            c0 c0Var = this.f1749c;
            if (c0Var != null) {
                f.i(drawable, c0Var, this.f1747a.getDrawableState());
                return;
            }
            c0 c0Var2 = this.f1748b;
            if (c0Var2 != null) {
                f.i(drawable, c0Var2, this.f1747a.getDrawableState());
            }
        }
    }

    public ColorStateList c() {
        c0 c0Var = this.f1749c;
        if (c0Var != null) {
            return c0Var.f1704a;
        }
        return null;
    }

    public PorterDuff.Mode d() {
        c0 c0Var = this.f1749c;
        if (c0Var != null) {
            return c0Var.f1705b;
        }
        return null;
    }

    public boolean e() {
        return Build.VERSION.SDK_INT < 21 || !(this.f1747a.getBackground() instanceof RippleDrawable);
    }

    public void f(AttributeSet attributeSet, int i2) {
        int iN;
        Context context = this.f1747a.getContext();
        int[] iArr = b.b.j.AppCompatImageView;
        e0 e0VarV = e0.v(context, attributeSet, iArr, i2, 0);
        ImageView imageView = this.f1747a;
        b.h.m.t.i0(imageView, imageView.getContext(), iArr, attributeSet, e0VarV.r(), i2, 0);
        try {
            Drawable drawable = this.f1747a.getDrawable();
            if (drawable == null && (iN = e0VarV.n(b.b.j.AppCompatImageView_srcCompat, -1)) != -1 && (drawable = b.b.l.a.a.d(this.f1747a.getContext(), iN)) != null) {
                this.f1747a.setImageDrawable(drawable);
            }
            if (drawable != null) {
                q.b(drawable);
            }
            int i3 = b.b.j.AppCompatImageView_tint;
            if (e0VarV.s(i3)) {
                b.h.n.e.c(this.f1747a, e0VarV.c(i3));
            }
            int i4 = b.b.j.AppCompatImageView_tintMode;
            if (e0VarV.s(i4)) {
                b.h.n.e.d(this.f1747a, q.e(e0VarV.k(i4, -1), null));
            }
        } finally {
            e0VarV.w();
        }
    }

    public void g(int i2) {
        if (i2 != 0) {
            Drawable drawableD = b.b.l.a.a.d(this.f1747a.getContext(), i2);
            if (drawableD != null) {
                q.b(drawableD);
            }
            this.f1747a.setImageDrawable(drawableD);
        } else {
            this.f1747a.setImageDrawable(null);
        }
        b();
    }

    public void h(ColorStateList colorStateList) {
        if (this.f1749c == null) {
            this.f1749c = new c0();
        }
        c0 c0Var = this.f1749c;
        c0Var.f1704a = colorStateList;
        c0Var.f1707d = true;
        b();
    }

    public void i(PorterDuff.Mode mode) {
        if (this.f1749c == null) {
            this.f1749c = new c0();
        }
        c0 c0Var = this.f1749c;
        c0Var.f1705b = mode;
        c0Var.f1706c = true;
        b();
    }

    public final boolean j() {
        int i2 = Build.VERSION.SDK_INT;
        return i2 > 21 ? this.f1748b != null : i2 == 21;
    }
}
