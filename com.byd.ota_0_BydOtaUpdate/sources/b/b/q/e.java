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
    public final CompoundButton f1006a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ColorStateList f1007b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public PorterDuff.Mode f1008c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1009d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1010e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f1011f;

    public e(CompoundButton compoundButton) {
        this.f1006a = compoundButton;
    }

    public void a() {
        Drawable drawableA = b.f.n.c.a(this.f1006a);
        if (drawableA != null) {
            if (this.f1009d || this.f1010e) {
                Drawable drawableMutate = b.f.g.l.a.q(drawableA).mutate();
                if (this.f1009d) {
                    b.f.g.l.a.n(drawableMutate, this.f1007b);
                }
                if (this.f1010e) {
                    b.f.g.l.a.o(drawableMutate, this.f1008c);
                }
                if (drawableMutate.isStateful()) {
                    drawableMutate.setState(this.f1006a.getDrawableState());
                }
                this.f1006a.setButtonDrawable(drawableMutate);
            }
        }
    }

    public int b(int i2) {
        Drawable drawableA;
        return (Build.VERSION.SDK_INT >= 17 || (drawableA = b.f.n.c.a(this.f1006a)) == null) ? i2 : i2 + drawableA.getIntrinsicWidth();
    }

    public ColorStateList c() {
        return this.f1007b;
    }

    public PorterDuff.Mode d() {
        return this.f1008c;
    }

    public void e(AttributeSet attributeSet, int i2) {
        boolean z;
        int iM;
        int iM2;
        Context context = this.f1006a.getContext();
        int[] iArr = b.b.j.T0;
        e0 e0VarU = e0.u(context, attributeSet, iArr, i2, 0);
        CompoundButton compoundButton = this.f1006a;
        b.f.m.s.R(compoundButton, compoundButton.getContext(), iArr, attributeSet, e0VarU.q(), i2, 0);
        try {
            int i3 = b.b.j.V0;
            if (!e0VarU.r(i3) || (iM2 = e0VarU.m(i3, 0)) == 0) {
                z = false;
            } else {
                try {
                    CompoundButton compoundButton2 = this.f1006a;
                    compoundButton2.setButtonDrawable(b.b.l.a.a.d(compoundButton2.getContext(), iM2));
                    z = true;
                } catch (Resources.NotFoundException unused) {
                    z = false;
                }
            }
            if (!z) {
                int i4 = b.b.j.U0;
                if (e0VarU.r(i4) && (iM = e0VarU.m(i4, 0)) != 0) {
                    CompoundButton compoundButton3 = this.f1006a;
                    compoundButton3.setButtonDrawable(b.b.l.a.a.d(compoundButton3.getContext(), iM));
                }
            }
            int i5 = b.b.j.W0;
            if (e0VarU.r(i5)) {
                b.f.n.c.b(this.f1006a, e0VarU.c(i5));
            }
            int i6 = b.b.j.X0;
            if (e0VarU.r(i6)) {
                b.f.n.c.c(this.f1006a, q.e(e0VarU.j(i6, -1), null));
            }
        } finally {
            e0VarU.v();
        }
    }

    public void f() {
        if (this.f1011f) {
            this.f1011f = false;
        } else {
            this.f1011f = true;
            a();
        }
    }

    public void g(ColorStateList colorStateList) {
        this.f1007b = colorStateList;
        this.f1009d = true;
        a();
    }

    public void h(PorterDuff.Mode mode) {
        this.f1008c = mode;
        this.f1010e = true;
        a();
    }
}
