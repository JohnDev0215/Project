package b.b.q;

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
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final CompoundButton f1715a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ColorStateList f1716b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public PorterDuff.Mode f1717c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1718d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1719e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f1720f;

    public e(CompoundButton compoundButton) {
        this.f1715a = compoundButton;
    }

    public void a() {
        Drawable drawableA = b.h.n.c.a(this.f1715a);
        if (drawableA != null) {
            if (this.f1718d || this.f1719e) {
                Drawable drawableMutate = b.h.f.l.a.r(drawableA).mutate();
                if (this.f1718d) {
                    b.h.f.l.a.o(drawableMutate, this.f1716b);
                }
                if (this.f1719e) {
                    b.h.f.l.a.p(drawableMutate, this.f1717c);
                }
                if (drawableMutate.isStateful()) {
                    drawableMutate.setState(this.f1715a.getDrawableState());
                }
                this.f1715a.setButtonDrawable(drawableMutate);
            }
        }
    }

    public int b(int i2) {
        Drawable drawableA;
        return (Build.VERSION.SDK_INT >= 17 || (drawableA = b.h.n.c.a(this.f1715a)) == null) ? i2 : i2 + drawableA.getIntrinsicWidth();
    }

    public ColorStateList c() {
        return this.f1716b;
    }

    public PorterDuff.Mode d() {
        return this.f1717c;
    }

    public void e(AttributeSet attributeSet, int i2) {
        boolean z;
        int iN;
        int iN2;
        Context context = this.f1715a.getContext();
        int[] iArr = b.b.j.CompoundButton;
        e0 e0VarV = e0.v(context, attributeSet, iArr, i2, 0);
        CompoundButton compoundButton = this.f1715a;
        b.h.m.t.i0(compoundButton, compoundButton.getContext(), iArr, attributeSet, e0VarV.r(), i2, 0);
        try {
            int i3 = b.b.j.CompoundButton_buttonCompat;
            if (!e0VarV.s(i3) || (iN2 = e0VarV.n(i3, 0)) == 0) {
                z = false;
            } else {
                try {
                    CompoundButton compoundButton2 = this.f1715a;
                    compoundButton2.setButtonDrawable(b.b.l.a.a.d(compoundButton2.getContext(), iN2));
                    z = true;
                } catch (Resources.NotFoundException unused) {
                    z = false;
                }
            }
            if (!z) {
                int i4 = b.b.j.CompoundButton_android_button;
                if (e0VarV.s(i4) && (iN = e0VarV.n(i4, 0)) != 0) {
                    CompoundButton compoundButton3 = this.f1715a;
                    compoundButton3.setButtonDrawable(b.b.l.a.a.d(compoundButton3.getContext(), iN));
                }
            }
            int i5 = b.b.j.CompoundButton_buttonTint;
            if (e0VarV.s(i5)) {
                b.h.n.c.c(this.f1715a, e0VarV.c(i5));
            }
            int i6 = b.b.j.CompoundButton_buttonTintMode;
            if (e0VarV.s(i6)) {
                b.h.n.c.d(this.f1715a, q.e(e0VarV.k(i6, -1), null));
            }
        } finally {
            e0VarV.w();
        }
    }

    public void f() {
        if (this.f1720f) {
            this.f1720f = false;
        } else {
            this.f1720f = true;
            a();
        }
    }

    public void g(ColorStateList colorStateList) {
        this.f1716b = colorStateList;
        this.f1718d = true;
        a();
    }

    public void h(PorterDuff.Mode mode) {
        this.f1717c = mode;
        this.f1719e = true;
        a();
    }
}
