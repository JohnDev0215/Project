package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

/* JADX INFO: loaded from: classes.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ImageView f976a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private t0 f977b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private t0 f978c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private t0 f979d;

    public m(ImageView imageView) {
        this.f976a = imageView;
    }

    private boolean a(Drawable drawable) {
        if (this.f979d == null) {
            this.f979d = new t0();
        }
        t0 t0Var = this.f979d;
        t0Var.a();
        ColorStateList colorStateListA = androidx.core.widget.e.a(this.f976a);
        if (colorStateListA != null) {
            t0Var.f1025d = true;
            t0Var.f1022a = colorStateListA;
        }
        PorterDuff.Mode modeB = androidx.core.widget.e.b(this.f976a);
        if (modeB != null) {
            t0Var.f1024c = true;
            t0Var.f1023b = modeB;
        }
        if (!t0Var.f1025d && !t0Var.f1024c) {
            return false;
        }
        i.i(drawable, t0Var, this.f976a.getDrawableState());
        return true;
    }

    private boolean j() {
        int i = Build.VERSION.SDK_INT;
        return i > 21 ? this.f977b != null : i == 21;
    }

    void b() {
        Drawable drawable = this.f976a.getDrawable();
        if (drawable != null) {
            d0.b(drawable);
        }
        if (drawable != null) {
            if (j() && a(drawable)) {
                return;
            }
            t0 t0Var = this.f978c;
            if (t0Var != null) {
                i.i(drawable, t0Var, this.f976a.getDrawableState());
                return;
            }
            t0 t0Var2 = this.f977b;
            if (t0Var2 != null) {
                i.i(drawable, t0Var2, this.f976a.getDrawableState());
            }
        }
    }

    ColorStateList c() {
        t0 t0Var = this.f978c;
        if (t0Var != null) {
            return t0Var.f1022a;
        }
        return null;
    }

    PorterDuff.Mode d() {
        t0 t0Var = this.f978c;
        if (t0Var != null) {
            return t0Var.f1023b;
        }
        return null;
    }

    boolean e() {
        return Build.VERSION.SDK_INT < 21 || !(this.f976a.getBackground() instanceof RippleDrawable);
    }

    public void f(AttributeSet attributeSet, int i) {
        int iN;
        Context context = this.f976a.getContext();
        int[] iArr = a.a.j.R;
        v0 v0VarV = v0.v(context, attributeSet, iArr, i, 0);
        ImageView imageView = this.f976a;
        a.g.k.t.f0(imageView, imageView.getContext(), iArr, attributeSet, v0VarV.r(), i, 0);
        try {
            Drawable drawable = this.f976a.getDrawable();
            if (drawable == null && (iN = v0VarV.n(a.a.j.S, -1)) != -1 && (drawable = a.a.k.a.a.d(this.f976a.getContext(), iN)) != null) {
                this.f976a.setImageDrawable(drawable);
            }
            if (drawable != null) {
                d0.b(drawable);
            }
            int i2 = a.a.j.T;
            if (v0VarV.s(i2)) {
                androidx.core.widget.e.c(this.f976a, v0VarV.c(i2));
            }
            int i3 = a.a.j.U;
            if (v0VarV.s(i3)) {
                androidx.core.widget.e.d(this.f976a, d0.d(v0VarV.k(i3, -1), null));
            }
        } finally {
            v0VarV.w();
        }
    }

    public void g(int i) {
        if (i != 0) {
            Drawable drawableD = a.a.k.a.a.d(this.f976a.getContext(), i);
            if (drawableD != null) {
                d0.b(drawableD);
            }
            this.f976a.setImageDrawable(drawableD);
        } else {
            this.f976a.setImageDrawable(null);
        }
        b();
    }

    void h(ColorStateList colorStateList) {
        if (this.f978c == null) {
            this.f978c = new t0();
        }
        t0 t0Var = this.f978c;
        t0Var.f1022a = colorStateList;
        t0Var.f1025d = true;
        b();
    }

    void i(PorterDuff.Mode mode) {
        if (this.f978c == null) {
            this.f978c = new t0();
        }
        t0 t0Var = this.f978c;
        t0Var.f1023b = mode;
        t0Var.f1024c = true;
        b();
    }
}
