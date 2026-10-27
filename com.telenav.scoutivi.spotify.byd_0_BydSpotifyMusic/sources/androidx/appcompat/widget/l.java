package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/* JADX INFO: compiled from: AppCompatImageHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ImageView f1110a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private q0 f1111b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private q0 f1112c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private q0 f1113d;

    public l(ImageView imageView) {
        this.f1110a = imageView;
    }

    private boolean a(Drawable drawable) {
        if (this.f1113d == null) {
            this.f1113d = new q0();
        }
        q0 q0Var = this.f1113d;
        q0Var.a();
        ColorStateList colorStateListA = androidx.core.widget.e.a(this.f1110a);
        if (colorStateListA != null) {
            q0Var.f1179d = true;
            q0Var.f1176a = colorStateListA;
        }
        PorterDuff.Mode modeB = androidx.core.widget.e.b(this.f1110a);
        if (modeB != null) {
            q0Var.f1178c = true;
            q0Var.f1177b = modeB;
        }
        if (!q0Var.f1179d && !q0Var.f1178c) {
            return false;
        }
        i.i(drawable, q0Var, this.f1110a.getDrawableState());
        return true;
    }

    private boolean j() {
        return this.f1111b != null;
    }

    void b() {
        Drawable drawable = this.f1110a.getDrawable();
        if (drawable != null) {
            b0.b(drawable);
        }
        if (drawable != null) {
            if (j() && a(drawable)) {
                return;
            }
            q0 q0Var = this.f1112c;
            if (q0Var != null) {
                i.i(drawable, q0Var, this.f1110a.getDrawableState());
                return;
            }
            q0 q0Var2 = this.f1111b;
            if (q0Var2 != null) {
                i.i(drawable, q0Var2, this.f1110a.getDrawableState());
            }
        }
    }

    ColorStateList c() {
        q0 q0Var = this.f1112c;
        if (q0Var != null) {
            return q0Var.f1176a;
        }
        return null;
    }

    PorterDuff.Mode d() {
        q0 q0Var = this.f1112c;
        if (q0Var != null) {
            return q0Var.f1177b;
        }
        return null;
    }

    boolean e() {
        return !(this.f1110a.getBackground() instanceof RippleDrawable);
    }

    public void f(AttributeSet attributeSet, int i7) {
        int iN;
        Context context = this.f1110a.getContext();
        int[] iArr = e.j.AppCompatImageView;
        s0 s0VarV = s0.v(context, attributeSet, iArr, i7, 0);
        ImageView imageView = this.f1110a;
        i0.u.i0(imageView, imageView.getContext(), iArr, attributeSet, s0VarV.r(), i7, 0);
        try {
            Drawable drawable = this.f1110a.getDrawable();
            if (drawable == null && (iN = s0VarV.n(e.j.AppCompatImageView_srcCompat, -1)) != -1 && (drawable = f.a.b(this.f1110a.getContext(), iN)) != null) {
                this.f1110a.setImageDrawable(drawable);
            }
            if (drawable != null) {
                b0.b(drawable);
            }
            int i8 = e.j.AppCompatImageView_tint;
            if (s0VarV.s(i8)) {
                androidx.core.widget.e.c(this.f1110a, s0VarV.c(i8));
            }
            int i9 = e.j.AppCompatImageView_tintMode;
            if (s0VarV.s(i9)) {
                androidx.core.widget.e.d(this.f1110a, b0.c(s0VarV.k(i9, -1), null));
            }
        } finally {
            s0VarV.w();
        }
    }

    public void g(int i7) {
        if (i7 != 0) {
            Drawable drawableB = f.a.b(this.f1110a.getContext(), i7);
            if (drawableB != null) {
                b0.b(drawableB);
            }
            this.f1110a.setImageDrawable(drawableB);
        } else {
            this.f1110a.setImageDrawable(null);
        }
        b();
    }

    void h(ColorStateList colorStateList) {
        if (this.f1112c == null) {
            this.f1112c = new q0();
        }
        q0 q0Var = this.f1112c;
        q0Var.f1176a = colorStateList;
        q0Var.f1179d = true;
        b();
    }

    void i(PorterDuff.Mode mode) {
        if (this.f1112c == null) {
            this.f1112c = new q0();
        }
        q0 q0Var = this.f1112c;
        q0Var.f1177b = mode;
        q0Var.f1178c = true;
        b();
    }
}
