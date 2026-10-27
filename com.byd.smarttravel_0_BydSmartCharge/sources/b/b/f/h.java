package b.b.f;

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
    public final ImageView f780a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public c0 f781b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c0 f782c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c0 f783d;

    public h(ImageView imageView) {
        this.f780a = imageView;
    }

    public final boolean a(Drawable drawable) {
        if (this.f783d == null) {
            this.f783d = new c0();
        }
        c0 c0Var = this.f783d;
        c0Var.a();
        ColorStateList colorStateListA = b.g.k.e.a(this.f780a);
        if (colorStateListA != null) {
            c0Var.f751d = true;
            c0Var.f748a = colorStateListA;
        }
        PorterDuff.Mode modeB = b.g.k.e.b(this.f780a);
        if (modeB != null) {
            c0Var.f750c = true;
            c0Var.f749b = modeB;
        }
        if (!c0Var.f751d && !c0Var.f750c) {
            return false;
        }
        f.i(drawable, c0Var, this.f780a.getDrawableState());
        return true;
    }

    public void b() {
        Drawable drawable = this.f780a.getDrawable();
        if (drawable != null) {
            q.b(drawable);
        }
        if (drawable != null) {
            if (j() && a(drawable)) {
                return;
            }
            c0 c0Var = this.f782c;
            if (c0Var != null) {
                f.i(drawable, c0Var, this.f780a.getDrawableState());
                return;
            }
            c0 c0Var2 = this.f781b;
            if (c0Var2 != null) {
                f.i(drawable, c0Var2, this.f780a.getDrawableState());
            }
        }
    }

    public ColorStateList c() {
        c0 c0Var = this.f782c;
        if (c0Var != null) {
            return c0Var.f748a;
        }
        return null;
    }

    public PorterDuff.Mode d() {
        c0 c0Var = this.f782c;
        if (c0Var != null) {
            return c0Var.f749b;
        }
        return null;
    }

    public boolean e() {
        return Build.VERSION.SDK_INT < 21 || !(this.f780a.getBackground() instanceof RippleDrawable);
    }

    public void f(AttributeSet attributeSet, int i) {
        int iN;
        Context context = this.f780a.getContext();
        int[] iArr = R$styleable.AppCompatImageView;
        e0 e0VarV = e0.v(context, attributeSet, iArr, i, 0);
        ImageView imageView = this.f780a;
        b.g.j.t.h0(imageView, imageView.getContext(), iArr, attributeSet, e0VarV.r(), i, 0);
        try {
            Drawable drawable = this.f780a.getDrawable();
            if (drawable == null && (iN = e0VarV.n(R$styleable.AppCompatImageView_srcCompat, -1)) != -1 && (drawable = b.b.b.a.a.d(this.f780a.getContext(), iN)) != null) {
                this.f780a.setImageDrawable(drawable);
            }
            if (drawable != null) {
                q.b(drawable);
            }
            int i2 = R$styleable.AppCompatImageView_tint;
            if (e0VarV.s(i2)) {
                b.g.k.e.c(this.f780a, e0VarV.c(i2));
            }
            int i3 = R$styleable.AppCompatImageView_tintMode;
            if (e0VarV.s(i3)) {
                b.g.k.e.d(this.f780a, q.e(e0VarV.k(i3, -1), null));
            }
        } finally {
            e0VarV.w();
        }
    }

    public void g(int i) {
        if (i != 0) {
            Drawable drawableD = b.b.b.a.a.d(this.f780a.getContext(), i);
            if (drawableD != null) {
                q.b(drawableD);
            }
            this.f780a.setImageDrawable(drawableD);
        } else {
            this.f780a.setImageDrawable(null);
        }
        b();
    }

    public void h(ColorStateList colorStateList) {
        if (this.f782c == null) {
            this.f782c = new c0();
        }
        c0 c0Var = this.f782c;
        c0Var.f748a = colorStateList;
        c0Var.f751d = true;
        b();
    }

    public void i(PorterDuff.Mode mode) {
        if (this.f782c == null) {
            this.f782c = new c0();
        }
        c0 c0Var = this.f782c;
        c0Var.f749b = mode;
        c0Var.f750c = true;
        b();
    }

    public final boolean j() {
        int i = Build.VERSION.SDK_INT;
        return i > 21 ? this.f781b != null : i == 21;
    }
}
