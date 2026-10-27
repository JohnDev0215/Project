package r;

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
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ImageView f7545a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public c0 f7546b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c0 f7547c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c0 f7548d;

    public h(ImageView imageView) {
        this.f7545a = imageView;
    }

    public final boolean a(Drawable drawable) {
        if (this.f7548d == null) {
            this.f7548d = new c0();
        }
        c0 c0Var = this.f7548d;
        c0Var.a();
        ColorStateList colorStateListA = r0.e.a(this.f7545a);
        if (colorStateListA != null) {
            c0Var.f7497d = true;
            c0Var.f7494a = colorStateListA;
        }
        PorterDuff.Mode modeB = r0.e.b(this.f7545a);
        if (modeB != null) {
            c0Var.f7496c = true;
            c0Var.f7495b = modeB;
        }
        if (!c0Var.f7497d && !c0Var.f7496c) {
            return false;
        }
        f.i(drawable, c0Var, this.f7545a.getDrawableState());
        return true;
    }

    public void b() {
        Drawable drawable = this.f7545a.getDrawable();
        if (drawable != null) {
            q.b(drawable);
        }
        if (drawable != null) {
            if (j() && a(drawable)) {
                return;
            }
            c0 c0Var = this.f7547c;
            if (c0Var != null) {
                f.i(drawable, c0Var, this.f7545a.getDrawableState());
                return;
            }
            c0 c0Var2 = this.f7546b;
            if (c0Var2 != null) {
                f.i(drawable, c0Var2, this.f7545a.getDrawableState());
            }
        }
    }

    public ColorStateList c() {
        c0 c0Var = this.f7547c;
        if (c0Var != null) {
            return c0Var.f7494a;
        }
        return null;
    }

    public PorterDuff.Mode d() {
        c0 c0Var = this.f7547c;
        if (c0Var != null) {
            return c0Var.f7495b;
        }
        return null;
    }

    public boolean e() {
        return Build.VERSION.SDK_INT < 21 || !(this.f7545a.getBackground() instanceof RippleDrawable);
    }

    public void f(AttributeSet attributeSet, int i8) {
        int iN;
        Context context = this.f7545a.getContext();
        int[] iArr = R$styleable.AppCompatImageView;
        e0 e0VarV = e0.v(context, attributeSet, iArr, i8, 0);
        ImageView imageView = this.f7545a;
        o0.t.j0(imageView, imageView.getContext(), iArr, attributeSet, e0VarV.r(), i8, 0);
        try {
            Drawable drawable = this.f7545a.getDrawable();
            if (drawable == null && (iN = e0VarV.n(R$styleable.AppCompatImageView_srcCompat, -1)) != -1 && (drawable = m.a.d(this.f7545a.getContext(), iN)) != null) {
                this.f7545a.setImageDrawable(drawable);
            }
            if (drawable != null) {
                q.b(drawable);
            }
            int i9 = R$styleable.AppCompatImageView_tint;
            if (e0VarV.s(i9)) {
                r0.e.c(this.f7545a, e0VarV.c(i9));
            }
            int i10 = R$styleable.AppCompatImageView_tintMode;
            if (e0VarV.s(i10)) {
                r0.e.d(this.f7545a, q.e(e0VarV.k(i10, -1), null));
            }
        } finally {
            e0VarV.w();
        }
    }

    public void g(int i8) {
        if (i8 != 0) {
            Drawable drawableD = m.a.d(this.f7545a.getContext(), i8);
            if (drawableD != null) {
                q.b(drawableD);
            }
            this.f7545a.setImageDrawable(drawableD);
        } else {
            this.f7545a.setImageDrawable(null);
        }
        b();
    }

    public void h(ColorStateList colorStateList) {
        if (this.f7547c == null) {
            this.f7547c = new c0();
        }
        c0 c0Var = this.f7547c;
        c0Var.f7494a = colorStateList;
        c0Var.f7497d = true;
        b();
    }

    public void i(PorterDuff.Mode mode) {
        if (this.f7547c == null) {
            this.f7547c = new c0();
        }
        c0 c0Var = this.f7547c;
        c0Var.f7495b = mode;
        c0Var.f7496c = true;
        b();
    }

    public final boolean j() {
        int i8 = Build.VERSION.SDK_INT;
        return i8 > 21 ? this.f7546b != null : i8 == 21;
    }
}
