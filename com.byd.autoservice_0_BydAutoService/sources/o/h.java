package o;

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
    public final ImageView f4286a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public c0 f4287b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c0 f4288c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c0 f4289d;

    public h(ImageView imageView) {
        this.f4286a = imageView;
    }

    public final boolean a(Drawable drawable) {
        if (this.f4289d == null) {
            this.f4289d = new c0();
        }
        c0 c0Var = this.f4289d;
        c0Var.a();
        ColorStateList colorStateListA = n0.e.a(this.f4286a);
        if (colorStateListA != null) {
            c0Var.f4238d = true;
            c0Var.f4235a = colorStateListA;
        }
        PorterDuff.Mode modeB = n0.e.b(this.f4286a);
        if (modeB != null) {
            c0Var.f4237c = true;
            c0Var.f4236b = modeB;
        }
        if (!c0Var.f4238d && !c0Var.f4237c) {
            return false;
        }
        f.i(drawable, c0Var, this.f4286a.getDrawableState());
        return true;
    }

    public void b() {
        Drawable drawable = this.f4286a.getDrawable();
        if (drawable != null) {
            q.b(drawable);
        }
        if (drawable != null) {
            if (j() && a(drawable)) {
                return;
            }
            c0 c0Var = this.f4288c;
            if (c0Var != null) {
                f.i(drawable, c0Var, this.f4286a.getDrawableState());
                return;
            }
            c0 c0Var2 = this.f4287b;
            if (c0Var2 != null) {
                f.i(drawable, c0Var2, this.f4286a.getDrawableState());
            }
        }
    }

    public ColorStateList c() {
        c0 c0Var = this.f4288c;
        if (c0Var != null) {
            return c0Var.f4235a;
        }
        return null;
    }

    public PorterDuff.Mode d() {
        c0 c0Var = this.f4288c;
        if (c0Var != null) {
            return c0Var.f4236b;
        }
        return null;
    }

    public boolean e() {
        return Build.VERSION.SDK_INT < 21 || !(this.f4286a.getBackground() instanceof RippleDrawable);
    }

    public void f(AttributeSet attributeSet, int i3) {
        int iN;
        Context context = this.f4286a.getContext();
        int[] iArr = R$styleable.AppCompatImageView;
        e0 e0VarV = e0.v(context, attributeSet, iArr, i3, 0);
        ImageView imageView = this.f4286a;
        l0.t.h0(imageView, imageView.getContext(), iArr, attributeSet, e0VarV.r(), i3, 0);
        try {
            Drawable drawable = this.f4286a.getDrawable();
            if (drawable == null && (iN = e0VarV.n(R$styleable.AppCompatImageView_srcCompat, -1)) != -1 && (drawable = j.a.d(this.f4286a.getContext(), iN)) != null) {
                this.f4286a.setImageDrawable(drawable);
            }
            if (drawable != null) {
                q.b(drawable);
            }
            int i4 = R$styleable.AppCompatImageView_tint;
            if (e0VarV.s(i4)) {
                n0.e.c(this.f4286a, e0VarV.c(i4));
            }
            int i5 = R$styleable.AppCompatImageView_tintMode;
            if (e0VarV.s(i5)) {
                n0.e.d(this.f4286a, q.e(e0VarV.k(i5, -1), null));
            }
        } finally {
            e0VarV.w();
        }
    }

    public void g(int i3) {
        if (i3 != 0) {
            Drawable drawableD = j.a.d(this.f4286a.getContext(), i3);
            if (drawableD != null) {
                q.b(drawableD);
            }
            this.f4286a.setImageDrawable(drawableD);
        } else {
            this.f4286a.setImageDrawable(null);
        }
        b();
    }

    public void h(ColorStateList colorStateList) {
        if (this.f4288c == null) {
            this.f4288c = new c0();
        }
        c0 c0Var = this.f4288c;
        c0Var.f4235a = colorStateList;
        c0Var.f4238d = true;
        b();
    }

    public void i(PorterDuff.Mode mode) {
        if (this.f4288c == null) {
            this.f4288c = new c0();
        }
        c0 c0Var = this.f4288c;
        c0Var.f4236b = mode;
        c0Var.f4237c = true;
        b();
    }

    public final boolean j() {
        int i3 = Build.VERSION.SDK_INT;
        return i3 > 21 ? this.f4287b != null : i3 == 21;
    }
}
