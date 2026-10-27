package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CompoundButton;

/* JADX INFO: compiled from: AppCompatCompoundButtonHelper.java */
/* JADX INFO: loaded from: classes.dex */
class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final CompoundButton f1070a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ColorStateList f1071b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private PorterDuff.Mode f1072c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f1073d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f1074e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f1075f;

    h(CompoundButton compoundButton) {
        this.f1070a = compoundButton;
    }

    void a() {
        Drawable drawableA = androidx.core.widget.c.a(this.f1070a);
        if (drawableA != null) {
            if (this.f1073d || this.f1074e) {
                Drawable drawableMutate = a0.a.l(drawableA).mutate();
                if (this.f1073d) {
                    a0.a.i(drawableMutate, this.f1071b);
                }
                if (this.f1074e) {
                    a0.a.j(drawableMutate, this.f1072c);
                }
                if (drawableMutate.isStateful()) {
                    drawableMutate.setState(this.f1070a.getDrawableState());
                }
                this.f1070a.setButtonDrawable(drawableMutate);
            }
        }
    }

    int b(int i7) {
        return i7;
    }

    ColorStateList c() {
        return this.f1071b;
    }

    PorterDuff.Mode d() {
        return this.f1072c;
    }

    void e(AttributeSet attributeSet, int i7) {
        boolean z6;
        int iN;
        int iN2;
        Context context = this.f1070a.getContext();
        int[] iArr = e.j.CompoundButton;
        s0 s0VarV = s0.v(context, attributeSet, iArr, i7, 0);
        CompoundButton compoundButton = this.f1070a;
        i0.u.i0(compoundButton, compoundButton.getContext(), iArr, attributeSet, s0VarV.r(), i7, 0);
        try {
            int i8 = e.j.CompoundButton_buttonCompat;
            if (!s0VarV.s(i8) || (iN2 = s0VarV.n(i8, 0)) == 0) {
                z6 = false;
            } else {
                try {
                    CompoundButton compoundButton2 = this.f1070a;
                    compoundButton2.setButtonDrawable(f.a.b(compoundButton2.getContext(), iN2));
                    z6 = true;
                } catch (Resources.NotFoundException unused) {
                    z6 = false;
                }
            }
            if (!z6) {
                int i9 = e.j.CompoundButton_android_button;
                if (s0VarV.s(i9) && (iN = s0VarV.n(i9, 0)) != 0) {
                    CompoundButton compoundButton3 = this.f1070a;
                    compoundButton3.setButtonDrawable(f.a.b(compoundButton3.getContext(), iN));
                }
            }
            int i10 = e.j.CompoundButton_buttonTint;
            if (s0VarV.s(i10)) {
                androidx.core.widget.c.c(this.f1070a, s0VarV.c(i10));
            }
            int i11 = e.j.CompoundButton_buttonTintMode;
            if (s0VarV.s(i11)) {
                androidx.core.widget.c.d(this.f1070a, b0.c(s0VarV.k(i11, -1), null));
            }
        } finally {
            s0VarV.w();
        }
    }

    void f() {
        if (this.f1075f) {
            this.f1075f = false;
        } else {
            this.f1075f = true;
            a();
        }
    }

    void g(ColorStateList colorStateList) {
        this.f1071b = colorStateList;
        this.f1073d = true;
        a();
    }

    void h(PorterDuff.Mode mode) {
        this.f1072c = mode;
        this.f1074e = true;
        a();
    }
}
