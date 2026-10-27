package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.CompoundButton;

/* JADX INFO: compiled from: AppCompatCompoundButtonHelper.java */
/* JADX INFO: loaded from: classes.dex */
class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final CompoundButton f601a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ColorStateList f602b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private PorterDuff.Mode f603c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f604d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f605e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f606f;

    e(CompoundButton compoundButton) {
        this.f601a = compoundButton;
    }

    void a() {
        Drawable drawableA = androidx.core.widget.c.a(this.f601a);
        if (drawableA != null) {
            if (this.f604d || this.f605e) {
                Drawable drawableMutate = androidx.core.graphics.drawable.a.r(drawableA).mutate();
                if (this.f604d) {
                    androidx.core.graphics.drawable.a.o(drawableMutate, this.f602b);
                }
                if (this.f605e) {
                    androidx.core.graphics.drawable.a.p(drawableMutate, this.f603c);
                }
                if (drawableMutate.isStateful()) {
                    drawableMutate.setState(this.f601a.getDrawableState());
                }
                this.f601a.setButtonDrawable(drawableMutate);
            }
        }
    }

    int b(int i2) {
        Drawable drawableA;
        return (Build.VERSION.SDK_INT >= 17 || (drawableA = androidx.core.widget.c.a(this.f601a)) == null) ? i2 : i2 + drawableA.getIntrinsicWidth();
    }

    ColorStateList c() {
        return this.f602b;
    }

    PorterDuff.Mode d() {
        return this.f603c;
    }

    void e(AttributeSet attributeSet, int i2) {
        boolean z;
        int iN;
        int iN2;
        Context context = this.f601a.getContext();
        int[] iArr = b.a.j.CompoundButton;
        f0 f0VarV = f0.v(context, attributeSet, iArr, i2, 0);
        CompoundButton compoundButton = this.f601a;
        b.g.k.t.h0(compoundButton, compoundButton.getContext(), iArr, attributeSet, f0VarV.r(), i2, 0);
        try {
            int i3 = b.a.j.CompoundButton_buttonCompat;
            if (!f0VarV.s(i3) || (iN2 = f0VarV.n(i3, 0)) == 0) {
                z = false;
            } else {
                try {
                    CompoundButton compoundButton2 = this.f601a;
                    compoundButton2.setButtonDrawable(b.a.k.a.a.d(compoundButton2.getContext(), iN2));
                    z = true;
                } catch (Resources.NotFoundException unused) {
                    z = false;
                }
            }
            if (!z) {
                int i4 = b.a.j.CompoundButton_android_button;
                if (f0VarV.s(i4) && (iN = f0VarV.n(i4, 0)) != 0) {
                    CompoundButton compoundButton3 = this.f601a;
                    compoundButton3.setButtonDrawable(b.a.k.a.a.d(compoundButton3.getContext(), iN));
                }
            }
            int i5 = b.a.j.CompoundButton_buttonTint;
            if (f0VarV.s(i5)) {
                androidx.core.widget.c.c(this.f601a, f0VarV.c(i5));
            }
            int i6 = b.a.j.CompoundButton_buttonTintMode;
            if (f0VarV.s(i6)) {
                androidx.core.widget.c.d(this.f601a, q.e(f0VarV.k(i6, -1), null));
            }
        } finally {
            f0VarV.w();
        }
    }

    void f() {
        if (this.f606f) {
            this.f606f = false;
        } else {
            this.f606f = true;
            a();
        }
    }

    void g(ColorStateList colorStateList) {
        this.f602b = colorStateList;
        this.f604d = true;
        a();
    }

    void h(PorterDuff.Mode mode) {
        this.f603c = mode;
        this.f605e = true;
        a();
    }
}
