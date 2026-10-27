package p;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import com.google.android.material.button.MaterialButton;
import com.spotify.music.R;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class bf1 {
    public final MaterialButton a;
    public oc2 b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public PorterDuff.Mode i;
    public ColorStateList j;
    public ColorStateList k;
    public ColorStateList l;
    public qf1 m;
    public boolean n = false;
    public boolean o = false;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f106p = false;
    public boolean q;
    public RippleDrawable r;
    public int s;

    public bf1(MaterialButton materialButton, oc2 oc2Var) {
        this.a = materialButton;
        this.b = oc2Var;
    }

    public final zc2 a() {
        RippleDrawable rippleDrawable = this.r;
        if (rippleDrawable == null || rippleDrawable.getNumberOfLayers() <= 1) {
            return null;
        }
        return this.r.getNumberOfLayers() > 2 ? (zc2) this.r.getDrawable(2) : (zc2) this.r.getDrawable(1);
    }

    public final qf1 b(boolean z) {
        RippleDrawable rippleDrawable = this.r;
        if (rippleDrawable == null || rippleDrawable.getNumberOfLayers() <= 0) {
            return null;
        }
        return (qf1) ((LayerDrawable) ((InsetDrawable) this.r.getDrawable(0)).getDrawable()).getDrawable(!z ? 1 : 0);
    }

    public final void c(oc2 oc2Var) {
        this.b = oc2Var;
        if (b(false) != null) {
            b(false).setShapeAppearanceModel(oc2Var);
        }
        if (b(true) != null) {
            b(true).setShapeAppearanceModel(oc2Var);
        }
        if (a() != null) {
            a().setShapeAppearanceModel(oc2Var);
        }
    }

    public final void d(int i, int i2) {
        WeakHashMap weakHashMap = zt2.a;
        MaterialButton materialButton = this.a;
        int iF = jt2.f(materialButton);
        int paddingTop = materialButton.getPaddingTop();
        int iE = jt2.e(materialButton);
        int paddingBottom = materialButton.getPaddingBottom();
        int i3 = this.e;
        int i4 = this.f;
        this.f = i2;
        this.e = i;
        if (!this.o) {
            e();
        }
        jt2.k(materialButton, iF, (paddingTop + i) - i3, iE, (paddingBottom + i2) - i4);
    }

    public final void e() {
        qf1 qf1Var = new qf1(this.b);
        MaterialButton materialButton = this.a;
        qf1Var.i(materialButton.getContext());
        ya0.h(qf1Var, this.j);
        PorterDuff.Mode mode = this.i;
        if (mode != null) {
            ya0.i(qf1Var, mode);
        }
        float f = this.h;
        ColorStateList colorStateList = this.k;
        qf1Var.c.k = f;
        qf1Var.invalidateSelf();
        pf1 pf1Var = qf1Var.c;
        if (pf1Var.d != colorStateList) {
            pf1Var.d = colorStateList;
            qf1Var.onStateChange(qf1Var.getState());
        }
        qf1 qf1Var2 = new qf1(this.b);
        qf1Var2.setTint(0);
        float f2 = this.h;
        int iQ = this.n ? f60.q(materialButton, R.attr.colorSurface) : 0;
        qf1Var2.c.k = f2;
        qf1Var2.invalidateSelf();
        ColorStateList colorStateListValueOf = ColorStateList.valueOf(iQ);
        pf1 pf1Var2 = qf1Var2.c;
        if (pf1Var2.d != colorStateListValueOf) {
            pf1Var2.d = colorStateListValueOf;
            qf1Var2.onStateChange(qf1Var2.getState());
        }
        qf1 qf1Var3 = new qf1(this.b);
        this.m = qf1Var3;
        ya0.g(qf1Var3, -1);
        RippleDrawable rippleDrawable = new RippleDrawable(q30.t(this.l), new InsetDrawable((Drawable) new LayerDrawable(new Drawable[]{qf1Var2, qf1Var}), this.c, this.e, this.d, this.f), this.m);
        this.r = rippleDrawable;
        materialButton.setInternalBackground(rippleDrawable);
        qf1 qf1VarB = b(false);
        if (qf1VarB != null) {
            qf1VarB.j(this.s);
        }
    }

    public final void f() {
        qf1 qf1VarB = b(false);
        qf1 qf1VarB2 = b(true);
        if (qf1VarB != null) {
            float f = this.h;
            ColorStateList colorStateList = this.k;
            qf1VarB.c.k = f;
            qf1VarB.invalidateSelf();
            pf1 pf1Var = qf1VarB.c;
            if (pf1Var.d != colorStateList) {
                pf1Var.d = colorStateList;
                qf1VarB.onStateChange(qf1VarB.getState());
            }
            if (qf1VarB2 != null) {
                float f2 = this.h;
                int iQ = this.n ? f60.q(this.a, R.attr.colorSurface) : 0;
                qf1VarB2.c.k = f2;
                qf1VarB2.invalidateSelf();
                ColorStateList colorStateListValueOf = ColorStateList.valueOf(iQ);
                pf1 pf1Var2 = qf1VarB2.c;
                if (pf1Var2.d != colorStateListValueOf) {
                    pf1Var2.d = colorStateListValueOf;
                    qf1VarB2.onStateChange(qf1VarB2.getState());
                }
            }
        }
    }
}
