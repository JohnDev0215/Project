package r;

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
    public final CompoundButton f7505a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ColorStateList f7506b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public PorterDuff.Mode f7507c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f7508d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f7509e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f7510f;

    public e(CompoundButton compoundButton) {
        this.f7505a = compoundButton;
    }

    public void a() {
        Drawable drawableA = r0.c.a(this.f7505a);
        if (drawableA != null) {
            if (this.f7508d || this.f7509e) {
                Drawable drawableMutate = h0.a.r(drawableA).mutate();
                if (this.f7508d) {
                    h0.a.o(drawableMutate, this.f7506b);
                }
                if (this.f7509e) {
                    h0.a.p(drawableMutate, this.f7507c);
                }
                if (drawableMutate.isStateful()) {
                    drawableMutate.setState(this.f7505a.getDrawableState());
                }
                this.f7505a.setButtonDrawable(drawableMutate);
            }
        }
    }

    public int b(int i8) {
        Drawable drawableA;
        return (Build.VERSION.SDK_INT >= 17 || (drawableA = r0.c.a(this.f7505a)) == null) ? i8 : i8 + drawableA.getIntrinsicWidth();
    }

    public ColorStateList c() {
        return this.f7506b;
    }

    public PorterDuff.Mode d() {
        return this.f7507c;
    }

    public void e(AttributeSet attributeSet, int i8) {
        boolean z7;
        int iN;
        int iN2;
        Context context = this.f7505a.getContext();
        int[] iArr = R$styleable.CompoundButton;
        e0 e0VarV = e0.v(context, attributeSet, iArr, i8, 0);
        CompoundButton compoundButton = this.f7505a;
        o0.t.j0(compoundButton, compoundButton.getContext(), iArr, attributeSet, e0VarV.r(), i8, 0);
        try {
            int i9 = R$styleable.CompoundButton_buttonCompat;
            if (!e0VarV.s(i9) || (iN2 = e0VarV.n(i9, 0)) == 0) {
                z7 = false;
            } else {
                try {
                    CompoundButton compoundButton2 = this.f7505a;
                    compoundButton2.setButtonDrawable(m.a.d(compoundButton2.getContext(), iN2));
                    z7 = true;
                } catch (Resources.NotFoundException unused) {
                    z7 = false;
                }
            }
            if (!z7) {
                int i10 = R$styleable.CompoundButton_android_button;
                if (e0VarV.s(i10) && (iN = e0VarV.n(i10, 0)) != 0) {
                    CompoundButton compoundButton3 = this.f7505a;
                    compoundButton3.setButtonDrawable(m.a.d(compoundButton3.getContext(), iN));
                }
            }
            int i11 = R$styleable.CompoundButton_buttonTint;
            if (e0VarV.s(i11)) {
                r0.c.c(this.f7505a, e0VarV.c(i11));
            }
            int i12 = R$styleable.CompoundButton_buttonTintMode;
            if (e0VarV.s(i12)) {
                r0.c.d(this.f7505a, q.e(e0VarV.k(i12, -1), null));
            }
        } finally {
            e0VarV.w();
        }
    }

    public void f() {
        if (this.f7510f) {
            this.f7510f = false;
        } else {
            this.f7510f = true;
            a();
        }
    }

    public void g(ColorStateList colorStateList) {
        this.f7506b = colorStateList;
        this.f7508d = true;
        a();
    }

    public void h(PorterDuff.Mode mode) {
        this.f7507c = mode;
        this.f7509e = true;
        a();
    }
}
