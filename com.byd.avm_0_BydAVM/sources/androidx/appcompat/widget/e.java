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
    private final CompoundButton f425a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ColorStateList f426b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private PorterDuff.Mode f427c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f428d = false;
    private boolean e = false;
    private boolean f;

    e(CompoundButton compoundButton) {
        this.f425a = compoundButton;
    }

    void a() {
        Drawable drawableA = androidx.core.widget.c.a(this.f425a);
        if (drawableA != null) {
            if (this.f428d || this.e) {
                Drawable drawableMutate = androidx.core.graphics.drawable.a.r(drawableA).mutate();
                if (this.f428d) {
                    androidx.core.graphics.drawable.a.o(drawableMutate, this.f426b);
                }
                if (this.e) {
                    androidx.core.graphics.drawable.a.p(drawableMutate, this.f427c);
                }
                if (drawableMutate.isStateful()) {
                    drawableMutate.setState(this.f425a.getDrawableState());
                }
                this.f425a.setButtonDrawable(drawableMutate);
            }
        }
    }

    int b(int i) {
        Drawable drawableA;
        return (Build.VERSION.SDK_INT >= 17 || (drawableA = androidx.core.widget.c.a(this.f425a)) == null) ? i : i + drawableA.getIntrinsicWidth();
    }

    ColorStateList c() {
        return this.f426b;
    }

    PorterDuff.Mode d() {
        return this.f427c;
    }

    void e(AttributeSet attributeSet, int i) {
        boolean z;
        int iN;
        int iN2;
        Context context = this.f425a.getContext();
        int[] iArr = b.a.j.T0;
        e0 e0VarV = e0.v(context, attributeSet, iArr, i, 0);
        CompoundButton compoundButton = this.f425a;
        b.g.k.t.h0(compoundButton, compoundButton.getContext(), iArr, attributeSet, e0VarV.r(), i, 0);
        try {
            int i2 = b.a.j.V0;
            if (!e0VarV.s(i2) || (iN2 = e0VarV.n(i2, 0)) == 0) {
                z = false;
            } else {
                try {
                    CompoundButton compoundButton2 = this.f425a;
                    compoundButton2.setButtonDrawable(b.a.k.a.a.d(compoundButton2.getContext(), iN2));
                    z = true;
                } catch (Resources.NotFoundException unused) {
                    z = false;
                }
            }
            if (!z) {
                int i3 = b.a.j.U0;
                if (e0VarV.s(i3) && (iN = e0VarV.n(i3, 0)) != 0) {
                    CompoundButton compoundButton3 = this.f425a;
                    compoundButton3.setButtonDrawable(b.a.k.a.a.d(compoundButton3.getContext(), iN));
                }
            }
            int i4 = b.a.j.W0;
            if (e0VarV.s(i4)) {
                androidx.core.widget.c.c(this.f425a, e0VarV.c(i4));
            }
            int i5 = b.a.j.X0;
            if (e0VarV.s(i5)) {
                androidx.core.widget.c.d(this.f425a, q.e(e0VarV.k(i5, -1), null));
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
        this.f426b = colorStateList;
        this.f428d = true;
        a();
    }

    void h(PorterDuff.Mode mode) {
        this.f427c = mode;
        this.e = true;
        a();
    }
}
