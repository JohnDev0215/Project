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
    private final CompoundButton f944a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ColorStateList f945b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private PorterDuff.Mode f946c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f947d = false;
    private boolean e = false;
    private boolean f;

    e(CompoundButton compoundButton) {
        this.f944a = compoundButton;
    }

    void a() {
        Drawable drawableA = androidx.core.widget.c.a(this.f944a);
        if (drawableA != null) {
            if (this.f947d || this.e) {
                Drawable drawableMutate = androidx.core.graphics.drawable.a.r(drawableA).mutate();
                if (this.f947d) {
                    androidx.core.graphics.drawable.a.o(drawableMutate, this.f945b);
                }
                if (this.e) {
                    androidx.core.graphics.drawable.a.p(drawableMutate, this.f946c);
                }
                if (drawableMutate.isStateful()) {
                    drawableMutate.setState(this.f944a.getDrawableState());
                }
                this.f944a.setButtonDrawable(drawableMutate);
            }
        }
    }

    int b(int i) {
        Drawable drawableA;
        return (Build.VERSION.SDK_INT >= 17 || (drawableA = androidx.core.widget.c.a(this.f944a)) == null) ? i : i + drawableA.getIntrinsicWidth();
    }

    ColorStateList c() {
        return this.f945b;
    }

    PorterDuff.Mode d() {
        return this.f946c;
    }

    void e(AttributeSet attributeSet, int i) {
        boolean z;
        int iN;
        int iN2;
        Context context = this.f944a.getContext();
        int[] iArr = c.a.j.T0;
        e0 e0VarV = e0.v(context, attributeSet, iArr, i, 0);
        CompoundButton compoundButton = this.f944a;
        c.g.k.t.h0(compoundButton, compoundButton.getContext(), iArr, attributeSet, e0VarV.r(), i, 0);
        try {
            int i2 = c.a.j.V0;
            if (!e0VarV.s(i2) || (iN2 = e0VarV.n(i2, 0)) == 0) {
                z = false;
            } else {
                try {
                    CompoundButton compoundButton2 = this.f944a;
                    compoundButton2.setButtonDrawable(c.a.k.a.a.d(compoundButton2.getContext(), iN2));
                    z = true;
                } catch (Resources.NotFoundException unused) {
                    z = false;
                }
            }
            if (!z) {
                int i3 = c.a.j.U0;
                if (e0VarV.s(i3) && (iN = e0VarV.n(i3, 0)) != 0) {
                    CompoundButton compoundButton3 = this.f944a;
                    compoundButton3.setButtonDrawable(c.a.k.a.a.d(compoundButton3.getContext(), iN));
                }
            }
            int i4 = c.a.j.W0;
            if (e0VarV.s(i4)) {
                androidx.core.widget.c.c(this.f944a, e0VarV.c(i4));
            }
            int i5 = c.a.j.X0;
            if (e0VarV.s(i5)) {
                androidx.core.widget.c.d(this.f944a, q.e(e0VarV.k(i5, -1), null));
            }
        } finally {
            e0VarV.w();
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
        this.f945b = colorStateList;
        this.f947d = true;
        a();
    }

    void h(PorterDuff.Mode mode) {
        this.f946c = mode;
        this.e = true;
        a();
    }
}
