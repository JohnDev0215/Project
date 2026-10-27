package o;

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
    public final CompoundButton f4246a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ColorStateList f4247b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public PorterDuff.Mode f4248c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f4249d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f4250e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f4251f;

    public e(CompoundButton compoundButton) {
        this.f4246a = compoundButton;
    }

    public void a() {
        Drawable drawableA = n0.c.a(this.f4246a);
        if (drawableA != null) {
            if (this.f4249d || this.f4250e) {
                Drawable drawableMutate = e0.a.r(drawableA).mutate();
                if (this.f4249d) {
                    e0.a.o(drawableMutate, this.f4247b);
                }
                if (this.f4250e) {
                    e0.a.p(drawableMutate, this.f4248c);
                }
                if (drawableMutate.isStateful()) {
                    drawableMutate.setState(this.f4246a.getDrawableState());
                }
                this.f4246a.setButtonDrawable(drawableMutate);
            }
        }
    }

    public int b(int i3) {
        Drawable drawableA;
        return (Build.VERSION.SDK_INT >= 17 || (drawableA = n0.c.a(this.f4246a)) == null) ? i3 : i3 + drawableA.getIntrinsicWidth();
    }

    public ColorStateList c() {
        return this.f4247b;
    }

    public PorterDuff.Mode d() {
        return this.f4248c;
    }

    public void e(AttributeSet attributeSet, int i3) {
        boolean z3;
        int iN;
        int iN2;
        Context context = this.f4246a.getContext();
        int[] iArr = R$styleable.CompoundButton;
        e0 e0VarV = e0.v(context, attributeSet, iArr, i3, 0);
        CompoundButton compoundButton = this.f4246a;
        l0.t.h0(compoundButton, compoundButton.getContext(), iArr, attributeSet, e0VarV.r(), i3, 0);
        try {
            int i4 = R$styleable.CompoundButton_buttonCompat;
            if (!e0VarV.s(i4) || (iN2 = e0VarV.n(i4, 0)) == 0) {
                z3 = false;
            } else {
                try {
                    CompoundButton compoundButton2 = this.f4246a;
                    compoundButton2.setButtonDrawable(j.a.d(compoundButton2.getContext(), iN2));
                    z3 = true;
                } catch (Resources.NotFoundException unused) {
                    z3 = false;
                }
            }
            if (!z3) {
                int i5 = R$styleable.CompoundButton_android_button;
                if (e0VarV.s(i5) && (iN = e0VarV.n(i5, 0)) != 0) {
                    CompoundButton compoundButton3 = this.f4246a;
                    compoundButton3.setButtonDrawable(j.a.d(compoundButton3.getContext(), iN));
                }
            }
            int i6 = R$styleable.CompoundButton_buttonTint;
            if (e0VarV.s(i6)) {
                n0.c.c(this.f4246a, e0VarV.c(i6));
            }
            int i7 = R$styleable.CompoundButton_buttonTintMode;
            if (e0VarV.s(i7)) {
                n0.c.d(this.f4246a, q.e(e0VarV.k(i7, -1), null));
            }
        } finally {
            e0VarV.w();
        }
    }

    public void f() {
        if (this.f4251f) {
            this.f4251f = false;
        } else {
            this.f4251f = true;
            a();
        }
    }

    public void g(ColorStateList colorStateList) {
        this.f4247b = colorStateList;
        this.f4249d = true;
        a();
    }

    public void h(PorterDuff.Mode mode) {
        this.f4248c = mode;
        this.f4250e = true;
        a();
    }
}
