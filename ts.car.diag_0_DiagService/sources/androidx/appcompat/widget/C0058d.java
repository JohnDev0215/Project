package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.R$styleable;
import androidx.core.view.ViewCompat;

/* JADX INFO: renamed from: androidx.appcompat.widget.d, reason: case insensitive filesystem */
/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
class C0058d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @NonNull
    private final View f355a;
    private S d;
    private S e;
    private S f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f357c = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final C0063i f356b = C0063i.b();

    C0058d(@NonNull View view) {
        this.f355a = view;
    }

    private boolean a(@NonNull Drawable drawable) {
        if (this.f == null) {
            this.f = new S();
        }
        S s = this.f;
        s.a();
        ColorStateList colorStateListL = ViewCompat.l(this.f355a);
        if (colorStateListL != null) {
            s.d = true;
            s.f301a = colorStateListL;
        }
        PorterDuff.Mode modeM = ViewCompat.m(this.f355a);
        if (modeM != null) {
            s.f303c = true;
            s.f302b = modeM;
        }
        if (!s.d && !s.f303c) {
            return false;
        }
        C0063i.i(drawable, s, this.f355a.getDrawableState());
        return true;
    }

    private boolean k() {
        int i = Build.VERSION.SDK_INT;
        return i > 21 ? this.d != null : i == 21;
    }

    void b() {
        Drawable background = this.f355a.getBackground();
        if (background != null) {
            if (k() && a(background)) {
                return;
            }
            S s = this.e;
            if (s != null) {
                C0063i.i(background, s, this.f355a.getDrawableState());
                return;
            }
            S s2 = this.d;
            if (s2 != null) {
                C0063i.i(background, s2, this.f355a.getDrawableState());
            }
        }
    }

    ColorStateList c() {
        S s = this.e;
        if (s != null) {
            return s.f301a;
        }
        return null;
    }

    PorterDuff.Mode d() {
        S s = this.e;
        if (s != null) {
            return s.f302b;
        }
        return null;
    }

    void e(@Nullable AttributeSet attributeSet, int i) {
        U u = U.u(this.f355a.getContext(), attributeSet, R$styleable.ViewBackgroundHelper, i, 0);
        View view = this.f355a;
        ViewCompat.K(view, view.getContext(), R$styleable.ViewBackgroundHelper, attributeSet, u.q(), i, 0);
        try {
            if (u.r(R$styleable.ViewBackgroundHelper_android_background)) {
                this.f357c = u.m(R$styleable.ViewBackgroundHelper_android_background, -1);
                ColorStateList colorStateListF = this.f356b.f(this.f355a.getContext(), this.f357c);
                if (colorStateListF != null) {
                    h(colorStateListF);
                }
            }
            if (u.r(R$styleable.ViewBackgroundHelper_backgroundTint)) {
                ViewCompat.O(this.f355a, u.c(R$styleable.ViewBackgroundHelper_backgroundTint));
            }
            if (u.r(R$styleable.ViewBackgroundHelper_backgroundTintMode)) {
                ViewCompat.P(this.f355a, C.e(u.j(R$styleable.ViewBackgroundHelper_backgroundTintMode, -1), null));
            }
        } finally {
            u.v();
        }
    }

    void f(Drawable drawable) {
        this.f357c = -1;
        h(null);
        b();
    }

    void g(int i) {
        this.f357c = i;
        C0063i c0063i = this.f356b;
        h(c0063i != null ? c0063i.f(this.f355a.getContext(), i) : null);
        b();
    }

    void h(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.d == null) {
                this.d = new S();
            }
            S s = this.d;
            s.f301a = colorStateList;
            s.d = true;
        } else {
            this.d = null;
        }
        b();
    }

    void i(ColorStateList colorStateList) {
        if (this.e == null) {
            this.e = new S();
        }
        S s = this.e;
        s.f301a = colorStateList;
        s.d = true;
        b();
    }

    void j(PorterDuff.Mode mode) {
        if (this.e == null) {
            this.e = new S();
        }
        S s = this.e;
        s.f302b = mode;
        s.f303c = true;
        b();
    }
}
