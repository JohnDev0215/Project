package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.CompoundButton;

/* JADX INFO: loaded from: classes.dex */
class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final CompoundButton f941a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ColorStateList f942b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private PorterDuff.Mode f943c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f944d = false;
    private boolean e = false;
    private boolean f;

    h(CompoundButton compoundButton) {
        this.f941a = compoundButton;
    }

    void a() {
        Drawable drawableA = androidx.core.widget.c.a(this.f941a);
        if (drawableA != null) {
            if (this.f944d || this.e) {
                Drawable drawableMutate = androidx.core.graphics.drawable.a.r(drawableA).mutate();
                if (this.f944d) {
                    androidx.core.graphics.drawable.a.o(drawableMutate, this.f942b);
                }
                if (this.e) {
                    androidx.core.graphics.drawable.a.p(drawableMutate, this.f943c);
                }
                if (drawableMutate.isStateful()) {
                    drawableMutate.setState(this.f941a.getDrawableState());
                }
                this.f941a.setButtonDrawable(drawableMutate);
            }
        }
    }

    int b(int i) {
        Drawable drawableA;
        return (Build.VERSION.SDK_INT >= 17 || (drawableA = androidx.core.widget.c.a(this.f941a)) == null) ? i : i + drawableA.getIntrinsicWidth();
    }

    ColorStateList c() {
        return this.f942b;
    }

    PorterDuff.Mode d() {
        return this.f943c;
    }

    void e(AttributeSet attributeSet, int i) {
        boolean z;
        int iN;
        int iN2;
        Context context = this.f941a.getContext();
        int[] iArr = a.a.j.R0;
        v0 v0VarV = v0.v(context, attributeSet, iArr, i, 0);
        CompoundButton compoundButton = this.f941a;
        a.g.k.t.f0(compoundButton, compoundButton.getContext(), iArr, attributeSet, v0VarV.r(), i, 0);
        try {
            int i2 = a.a.j.T0;
            if (!v0VarV.s(i2) || (iN2 = v0VarV.n(i2, 0)) == 0) {
                z = false;
            } else {
                try {
                    CompoundButton compoundButton2 = this.f941a;
                    compoundButton2.setButtonDrawable(a.a.k.a.a.d(compoundButton2.getContext(), iN2));
                    z = true;
                } catch (Resources.NotFoundException unused) {
                    z = false;
                }
            }
            if (!z) {
                int i3 = a.a.j.S0;
                if (v0VarV.s(i3) && (iN = v0VarV.n(i3, 0)) != 0) {
                    CompoundButton compoundButton3 = this.f941a;
                    compoundButton3.setButtonDrawable(a.a.k.a.a.d(compoundButton3.getContext(), iN));
                }
            }
            int i4 = a.a.j.U0;
            if (v0VarV.s(i4)) {
                androidx.core.widget.c.c(this.f941a, v0VarV.c(i4));
            }
            int i5 = a.a.j.V0;
            if (v0VarV.s(i5)) {
                androidx.core.widget.c.d(this.f941a, d0.d(v0VarV.k(i5, -1), null));
            }
        } finally {
            v0VarV.w();
        }
    }

    void f() {
        if (this.f) {
            this.f = false;
        } else {
            this.f = true;
            a();
        }
    }

    void g(ColorStateList colorStateList) {
        this.f942b = colorStateList;
        this.f944d = true;
        a();
    }

    void h(PorterDuff.Mode mode) {
        this.f943c = mode;
        this.e = true;
        a();
    }
}
