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
    private final ImageView f603a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private c0 f604b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private c0 f605c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private c0 f606d;

    public h(ImageView imageView) {
        this.f603a = imageView;
    }

    private boolean a(Drawable drawable) {
        if (this.f606d == null) {
            this.f606d = new c0();
        }
        c0 c0Var = this.f606d;
        c0Var.a();
        ColorStateList colorStateListA = androidx.core.widget.e.a(this.f603a);
        if (colorStateListA != null) {
            c0Var.f570d = true;
            c0Var.f567a = colorStateListA;
        }
        PorterDuff.Mode modeB = androidx.core.widget.e.b(this.f603a);
        if (modeB != null) {
            c0Var.f569c = true;
            c0Var.f568b = modeB;
        }
        if (!c0Var.f570d && !c0Var.f569c) {
            return false;
        }
        f.i(drawable, c0Var, this.f603a.getDrawableState());
        return true;
    }

    private boolean j() {
        int i = Build.VERSION.SDK_INT;
        return i > 21 ? this.f604b != null : i == 21;
    }

    void b() {
        Drawable drawable = this.f603a.getDrawable();
        if (drawable != null) {
            q.b(drawable);
        }
        if (drawable != null) {
            if (j() && a(drawable)) {
                return;
            }
            c0 c0Var = this.f605c;
            if (c0Var != null) {
                f.i(drawable, c0Var, this.f603a.getDrawableState());
                return;
            }
            c0 c0Var2 = this.f604b;
            if (c0Var2 != null) {
                f.i(drawable, c0Var2, this.f603a.getDrawableState());
            }
        }
    }

    ColorStateList c() {
        c0 c0Var = this.f605c;
        if (c0Var != null) {
            return c0Var.f567a;
        }
        return null;
    }

    PorterDuff.Mode d() {
        c0 c0Var = this.f605c;
        if (c0Var != null) {
            return c0Var.f568b;
        }
        return null;
    }

    boolean e() {
        return Build.VERSION.SDK_INT < 21 || !(this.f603a.getBackground() instanceof RippleDrawable);
    }

    public void f(AttributeSet attributeSet, int i) {
        int iN;
        Context context = this.f603a.getContext();
        int[] iArr = b.a.j.T;
        e0 e0VarV = e0.v(context, attributeSet, iArr, i, 0);
        ImageView imageView = this.f603a;
        b.g.k.t.i0(imageView, imageView.getContext(), iArr, attributeSet, e0VarV.r(), i, 0);
        try {
            Drawable drawable = this.f603a.getDrawable();
            if (drawable == null && (iN = e0VarV.n(b.a.j.U, -1)) != -1 && (drawable = b.a.k.a.a.d(this.f603a.getContext(), iN)) != null) {
                this.f603a.setImageDrawable(drawable);
            }
            if (drawable != null) {
                q.b(drawable);
            }
            int i2 = b.a.j.V;
            if (e0VarV.s(i2)) {
                androidx.core.widget.e.c(this.f603a, e0VarV.c(i2));
            }
            int i3 = b.a.j.W;
            if (e0VarV.s(i3)) {
                androidx.core.widget.e.d(this.f603a, q.e(e0VarV.k(i3, -1), null));
            }
        } finally {
            e0VarV.w();
        }
    }

    public void g(int i) {
        if (i != 0) {
            Drawable drawableD = b.a.k.a.a.d(this.f603a.getContext(), i);
            if (drawableD != null) {
                q.b(drawableD);
            }
            this.f603a.setImageDrawable(drawableD);
        } else {
            this.f603a.setImageDrawable(null);
        }
        b();
    }

    void h(ColorStateList colorStateList) {
        if (this.f605c == null) {
            this.f605c = new c0();
        }
        c0 c0Var = this.f605c;
        c0Var.f567a = colorStateList;
        c0Var.f570d = true;
        b();
    }

    void i(PorterDuff.Mode mode) {
        if (this.f605c == null) {
            this.f605c = new c0();
        }
        c0 c0Var = this.f605c;
        c0Var.f568b = mode;
        c0Var.f569c = true;
        b();
    }
}
