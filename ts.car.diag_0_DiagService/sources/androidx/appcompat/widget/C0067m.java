package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R$styleable;
import androidx.core.view.ViewCompat;

/* JADX INFO: renamed from: androidx.appcompat.widget.m, reason: case insensitive filesystem */
/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class C0067m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @NonNull
    private final ImageView f372a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private S f373b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private S f374c;
    private S d;

    public C0067m(@NonNull ImageView imageView) {
        this.f372a = imageView;
    }

    private boolean a(@NonNull Drawable drawable) {
        if (this.d == null) {
            this.d = new S();
        }
        S s = this.d;
        s.a();
        ColorStateList colorStateListA = androidx.core.widget.e.a(this.f372a);
        if (colorStateListA != null) {
            s.d = true;
            s.f301a = colorStateListA;
        }
        PorterDuff.Mode modeB = androidx.core.widget.e.b(this.f372a);
        if (modeB != null) {
            s.f303c = true;
            s.f302b = modeB;
        }
        if (!s.d && !s.f303c) {
            return false;
        }
        C0063i.i(drawable, s, this.f372a.getDrawableState());
        return true;
    }

    private boolean j() {
        int i = Build.VERSION.SDK_INT;
        return i > 21 ? this.f373b != null : i == 21;
    }

    void b() {
        Drawable drawable = this.f372a.getDrawable();
        if (drawable != null) {
            C.b(drawable);
        }
        if (drawable != null) {
            if (j() && a(drawable)) {
                return;
            }
            S s = this.f374c;
            if (s != null) {
                C0063i.i(drawable, s, this.f372a.getDrawableState());
                return;
            }
            S s2 = this.f373b;
            if (s2 != null) {
                C0063i.i(drawable, s2, this.f372a.getDrawableState());
            }
        }
    }

    ColorStateList c() {
        S s = this.f374c;
        if (s != null) {
            return s.f301a;
        }
        return null;
    }

    PorterDuff.Mode d() {
        S s = this.f374c;
        if (s != null) {
            return s.f302b;
        }
        return null;
    }

    boolean e() {
        return Build.VERSION.SDK_INT < 21 || !(this.f372a.getBackground() instanceof RippleDrawable);
    }

    public void f(AttributeSet attributeSet, int i) {
        int iM;
        U u = U.u(this.f372a.getContext(), attributeSet, R$styleable.AppCompatImageView, i, 0);
        ImageView imageView = this.f372a;
        ViewCompat.K(imageView, imageView.getContext(), R$styleable.AppCompatImageView, attributeSet, u.q(), i, 0);
        try {
            Drawable drawable = this.f372a.getDrawable();
            if (drawable == null && (iM = u.m(R$styleable.AppCompatImageView_srcCompat, -1)) != -1 && (drawable = androidx.appcompat.a.a.a.b(this.f372a.getContext(), iM)) != null) {
                this.f372a.setImageDrawable(drawable);
            }
            if (drawable != null) {
                C.b(drawable);
            }
            if (u.r(R$styleable.AppCompatImageView_tint)) {
                androidx.core.widget.e.c(this.f372a, u.c(R$styleable.AppCompatImageView_tint));
            }
            if (u.r(R$styleable.AppCompatImageView_tintMode)) {
                androidx.core.widget.e.d(this.f372a, C.e(u.j(R$styleable.AppCompatImageView_tintMode, -1), null));
            }
        } finally {
            u.v();
        }
    }

    public void g(int i) {
        if (i != 0) {
            Drawable drawableB = androidx.appcompat.a.a.a.b(this.f372a.getContext(), i);
            if (drawableB != null) {
                C.b(drawableB);
            }
            this.f372a.setImageDrawable(drawableB);
        } else {
            this.f372a.setImageDrawable(null);
        }
        b();
    }

    void h(ColorStateList colorStateList) {
        if (this.f374c == null) {
            this.f374c = new S();
        }
        S s = this.f374c;
        s.f301a = colorStateList;
        s.d = true;
        b();
    }

    void i(PorterDuff.Mode mode) {
        if (this.f374c == null) {
            this.f374c = new S();
        }
        S s = this.f374c;
        s.f302b = mode;
        s.f303c = true;
        b();
    }
}
