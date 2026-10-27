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
    private final ImageView f614a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private c0 f615b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private c0 f616c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private c0 f617d;

    public h(ImageView imageView) {
        this.f614a = imageView;
    }

    private boolean a(Drawable drawable) {
        if (this.f617d == null) {
            this.f617d = new c0();
        }
        c0 c0Var = this.f617d;
        c0Var.a();
        ColorStateList colorStateListA = androidx.core.widget.e.a(this.f614a);
        if (colorStateListA != null) {
            c0Var.f575d = true;
            c0Var.f572a = colorStateListA;
        }
        PorterDuff.Mode modeB = androidx.core.widget.e.b(this.f614a);
        if (modeB != null) {
            c0Var.f574c = true;
            c0Var.f573b = modeB;
        }
        if (!c0Var.f575d && !c0Var.f574c) {
            return false;
        }
        f.i(drawable, c0Var, this.f614a.getDrawableState());
        return true;
    }

    private boolean j() {
        int i = Build.VERSION.SDK_INT;
        return i > 21 ? this.f615b != null : i == 21;
    }

    void b() {
        Drawable drawable = this.f614a.getDrawable();
        if (drawable != null) {
            q.b(drawable);
        }
        if (drawable != null) {
            if (j() && a(drawable)) {
                return;
            }
            c0 c0Var = this.f616c;
            if (c0Var != null) {
                f.i(drawable, c0Var, this.f614a.getDrawableState());
                return;
            }
            c0 c0Var2 = this.f615b;
            if (c0Var2 != null) {
                f.i(drawable, c0Var2, this.f614a.getDrawableState());
            }
        }
    }

    ColorStateList c() {
        c0 c0Var = this.f616c;
        if (c0Var != null) {
            return c0Var.f572a;
        }
        return null;
    }

    PorterDuff.Mode d() {
        c0 c0Var = this.f616c;
        if (c0Var != null) {
            return c0Var.f573b;
        }
        return null;
    }

    boolean e() {
        return Build.VERSION.SDK_INT < 21 || !(this.f614a.getBackground() instanceof RippleDrawable);
    }

    public void f(AttributeSet attributeSet, int i) {
        int iN;
        Context context = this.f614a.getContext();
        int[] iArr = b.a.j.T;
        e0 e0VarV = e0.v(context, attributeSet, iArr, i, 0);
        ImageView imageView = this.f614a;
        b.g.k.t.h0(imageView, imageView.getContext(), iArr, attributeSet, e0VarV.r(), i, 0);
        try {
            Drawable drawable = this.f614a.getDrawable();
            if (drawable == null && (iN = e0VarV.n(b.a.j.U, -1)) != -1 && (drawable = b.a.k.a.a.d(this.f614a.getContext(), iN)) != null) {
                this.f614a.setImageDrawable(drawable);
            }
            if (drawable != null) {
                q.b(drawable);
            }
            int i2 = b.a.j.V;
            if (e0VarV.s(i2)) {
                androidx.core.widget.e.c(this.f614a, e0VarV.c(i2));
            }
            int i3 = b.a.j.W;
            if (e0VarV.s(i3)) {
                androidx.core.widget.e.d(this.f614a, q.e(e0VarV.k(i3, -1), null));
            }
        } finally {
            e0VarV.w();
        }
    }

    public void g(int i) {
        if (i != 0) {
            Drawable drawableD = b.a.k.a.a.d(this.f614a.getContext(), i);
            if (drawableD != null) {
                q.b(drawableD);
            }
            this.f614a.setImageDrawable(drawableD);
        } else {
            this.f614a.setImageDrawable(null);
        }
        b();
    }

    void h(ColorStateList colorStateList) {
        if (this.f616c == null) {
            this.f616c = new c0();
        }
        c0 c0Var = this.f616c;
        c0Var.f572a = colorStateList;
        c0Var.f575d = true;
        b();
    }

    void i(PorterDuff.Mode mode) {
        if (this.f616c == null) {
            this.f616c = new c0();
        }
        c0 c0Var = this.f616c;
        c0Var.f573b = mode;
        c0Var.f574c = true;
        b();
    }
}
