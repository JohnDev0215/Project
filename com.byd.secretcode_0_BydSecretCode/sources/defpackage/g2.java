package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import androidx.appcompat.R$styleable;

/* JADX INFO: compiled from: AppCompatCompoundButtonHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class g2 {
    public final CompoundButton a;
    public ColorStateList b = null;
    public PorterDuff.Mode c = null;
    public boolean d = false;
    public boolean e = false;
    public boolean f;

    public g2(CompoundButton compoundButton) {
        this.a = compoundButton;
    }

    public void a() {
        Drawable drawableA = ka.a(this.a);
        if (drawableA != null) {
            if (this.d || this.e) {
                Drawable drawableMutate = y7.r(drawableA).mutate();
                if (this.d) {
                    y7.o(drawableMutate, this.b);
                }
                if (this.e) {
                    y7.p(drawableMutate, this.c);
                }
                if (drawableMutate.isStateful()) {
                    drawableMutate.setState(this.a.getDrawableState());
                }
                this.a.setButtonDrawable(drawableMutate);
            }
        }
    }

    public int b(int i) {
        Drawable drawableA;
        return (Build.VERSION.SDK_INT >= 17 || (drawableA = ka.a(this.a)) == null) ? i : i + drawableA.getIntrinsicWidth();
    }

    public ColorStateList c() {
        return this.b;
    }

    public PorterDuff.Mode d() {
        return this.c;
    }

    public void e(AttributeSet attributeSet, int i) {
        boolean z;
        int iN;
        int iN2;
        Context context = this.a.getContext();
        int[] iArr = R$styleable.CompoundButton;
        g3 g3VarV = g3.v(context, attributeSet, iArr, i, 0);
        CompoundButton compoundButton = this.a;
        t9.h0(compoundButton, compoundButton.getContext(), iArr, attributeSet, g3VarV.r(), i, 0);
        try {
            int i2 = R$styleable.CompoundButton_buttonCompat;
            if (!g3VarV.s(i2) || (iN2 = g3VarV.n(i2, 0)) == 0) {
                z = false;
            } else {
                try {
                    CompoundButton compoundButton2 = this.a;
                    compoundButton2.setButtonDrawable(u0.d(compoundButton2.getContext(), iN2));
                    z = true;
                } catch (Resources.NotFoundException unused) {
                    z = false;
                }
            }
            if (!z) {
                int i3 = R$styleable.CompoundButton_android_button;
                if (g3VarV.s(i3) && (iN = g3VarV.n(i3, 0)) != 0) {
                    CompoundButton compoundButton3 = this.a;
                    compoundButton3.setButtonDrawable(u0.d(compoundButton3.getContext(), iN));
                }
            }
            int i4 = R$styleable.CompoundButton_buttonTint;
            if (g3VarV.s(i4)) {
                ka.c(this.a, g3VarV.c(i4));
            }
            int i5 = R$styleable.CompoundButton_buttonTintMode;
            if (g3VarV.s(i5)) {
                ka.d(this.a, s2.e(g3VarV.k(i5, -1), null));
            }
        } finally {
            g3VarV.w();
        }
    }

    public void f() {
        if (this.f) {
            this.f = false;
        } else {
            this.f = true;
            a();
        }
    }

    public void g(ColorStateList colorStateList) {
        this.b = colorStateList;
        this.d = true;
        a();
    }

    public void h(PorterDuff.Mode mode) {
        this.c = mode;
        this.e = true;
        a();
    }
}
