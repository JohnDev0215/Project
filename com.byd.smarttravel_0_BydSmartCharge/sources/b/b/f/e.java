package b.b.f;

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
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final CompoundButton f757a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ColorStateList f758b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public PorterDuff.Mode f759c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f760d = false;
    public boolean e = false;
    public boolean f;

    public e(CompoundButton compoundButton) {
        this.f757a = compoundButton;
    }

    public void a() {
        Drawable drawableA = b.g.k.c.a(this.f757a);
        if (drawableA != null) {
            if (this.f760d || this.e) {
                Drawable drawableMutate = b.g.c.l.a.r(drawableA).mutate();
                if (this.f760d) {
                    b.g.c.l.a.o(drawableMutate, this.f758b);
                }
                if (this.e) {
                    b.g.c.l.a.p(drawableMutate, this.f759c);
                }
                if (drawableMutate.isStateful()) {
                    drawableMutate.setState(this.f757a.getDrawableState());
                }
                this.f757a.setButtonDrawable(drawableMutate);
            }
        }
    }

    public int b(int i) {
        Drawable drawableA;
        return (Build.VERSION.SDK_INT >= 17 || (drawableA = b.g.k.c.a(this.f757a)) == null) ? i : i + drawableA.getIntrinsicWidth();
    }

    public ColorStateList c() {
        return this.f758b;
    }

    public PorterDuff.Mode d() {
        return this.f759c;
    }

    public void e(AttributeSet attributeSet, int i) {
        boolean z;
        int iN;
        int iN2;
        Context context = this.f757a.getContext();
        int[] iArr = R$styleable.CompoundButton;
        e0 e0VarV = e0.v(context, attributeSet, iArr, i, 0);
        CompoundButton compoundButton = this.f757a;
        b.g.j.t.h0(compoundButton, compoundButton.getContext(), iArr, attributeSet, e0VarV.r(), i, 0);
        try {
            int i2 = R$styleable.CompoundButton_buttonCompat;
            if (!e0VarV.s(i2) || (iN2 = e0VarV.n(i2, 0)) == 0) {
                z = false;
            } else {
                try {
                    CompoundButton compoundButton2 = this.f757a;
                    compoundButton2.setButtonDrawable(b.b.b.a.a.d(compoundButton2.getContext(), iN2));
                    z = true;
                } catch (Resources.NotFoundException unused) {
                    z = false;
                }
            }
            if (!z) {
                int i3 = R$styleable.CompoundButton_android_button;
                if (e0VarV.s(i3) && (iN = e0VarV.n(i3, 0)) != 0) {
                    CompoundButton compoundButton3 = this.f757a;
                    compoundButton3.setButtonDrawable(b.b.b.a.a.d(compoundButton3.getContext(), iN));
                }
            }
            int i4 = R$styleable.CompoundButton_buttonTint;
            if (e0VarV.s(i4)) {
                b.g.k.c.c(this.f757a, e0VarV.c(i4));
            }
            int i5 = R$styleable.CompoundButton_buttonTintMode;
            if (e0VarV.s(i5)) {
                b.g.k.c.d(this.f757a, q.e(e0VarV.k(i5, -1), null));
            }
        } finally {
            e0VarV.w();
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
        this.f758b = colorStateList;
        this.f760d = true;
        a();
    }

    public void h(PorterDuff.Mode mode) {
        this.f759c = mode;
        this.e = true;
        a();
    }
}
