package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.R$styleable;
import androidx.core.view.ViewCompat;

/* JADX INFO: renamed from: androidx.appcompat.widget.h, reason: case insensitive filesystem */
/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
class C0062h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @NonNull
    private final CompoundButton f361a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ColorStateList f362b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private PorterDuff.Mode f363c = null;
    private boolean d = false;
    private boolean e = false;
    private boolean f;

    C0062h(@NonNull CompoundButton compoundButton) {
        this.f361a = compoundButton;
    }

    void a() {
        Drawable drawableA = androidx.core.widget.c.a(this.f361a);
        if (drawableA != null) {
            if (this.d || this.e) {
                Drawable drawableMutate = androidx.core.graphics.drawable.a.q(drawableA).mutate();
                if (this.d) {
                    androidx.core.graphics.drawable.a.n(drawableMutate, this.f362b);
                }
                if (this.e) {
                    androidx.core.graphics.drawable.a.o(drawableMutate, this.f363c);
                }
                if (drawableMutate.isStateful()) {
                    drawableMutate.setState(this.f361a.getDrawableState());
                }
                this.f361a.setButtonDrawable(drawableMutate);
            }
        }
    }

    int b(int i) {
        Drawable drawableA;
        return (Build.VERSION.SDK_INT >= 17 || (drawableA = androidx.core.widget.c.a(this.f361a)) == null) ? i : i + drawableA.getIntrinsicWidth();
    }

    void c(@Nullable AttributeSet attributeSet, int i) {
        boolean z;
        int iM;
        int iM2;
        U u = U.u(this.f361a.getContext(), attributeSet, R$styleable.CompoundButton, i, 0);
        CompoundButton compoundButton = this.f361a;
        ViewCompat.K(compoundButton, compoundButton.getContext(), R$styleable.CompoundButton, attributeSet, u.q(), i, 0);
        try {
            if (!u.r(R$styleable.CompoundButton_buttonCompat) || (iM2 = u.m(R$styleable.CompoundButton_buttonCompat, 0)) == 0) {
                z = false;
            } else {
                try {
                    this.f361a.setButtonDrawable(androidx.appcompat.a.a.a.b(this.f361a.getContext(), iM2));
                    z = true;
                } catch (Resources.NotFoundException unused) {
                    z = false;
                }
            }
            if (!z && u.r(R$styleable.CompoundButton_android_button) && (iM = u.m(R$styleable.CompoundButton_android_button, 0)) != 0) {
                this.f361a.setButtonDrawable(androidx.appcompat.a.a.a.b(this.f361a.getContext(), iM));
            }
            if (u.r(R$styleable.CompoundButton_buttonTint)) {
                androidx.core.widget.c.b(this.f361a, u.c(R$styleable.CompoundButton_buttonTint));
            }
            if (u.r(R$styleable.CompoundButton_buttonTintMode)) {
                androidx.core.widget.c.c(this.f361a, C.e(u.j(R$styleable.CompoundButton_buttonTintMode, -1), null));
            }
        } finally {
            u.v();
        }
    }

    void d() {
        if (this.f) {
            this.f = false;
        } else {
            this.f = true;
            a();
        }
    }

    void e(ColorStateList colorStateList) {
        this.f362b = colorStateList;
        this.d = true;
        a();
    }

    void f(@Nullable PorterDuff.Mode mode) {
        this.f363c = mode;
        this.e = true;
        a();
    }
}
