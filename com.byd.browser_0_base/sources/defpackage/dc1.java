package defpackage;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.view.View;
import com.google.android.material.R$attr;
import com.google.android.material.button.MaterialButton;
import defpackage.om3;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: MaterialButtonHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class dc1 {
    public final MaterialButton a;
    public uj2 b;
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
    public sc1 m;
    public boolean n = false;
    public boolean o = false;
    public boolean p = false;
    public boolean q;
    public RippleDrawable r;
    public int s;

    public dc1(MaterialButton materialButton, uj2 uj2Var) {
        this.a = materialButton;
        this.b = uj2Var;
    }

    public final yj2 a() {
        RippleDrawable rippleDrawable = this.r;
        if (rippleDrawable == null || rippleDrawable.getNumberOfLayers() <= 1) {
            return null;
        }
        return this.r.getNumberOfLayers() > 2 ? (yj2) this.r.getDrawable(2) : (yj2) this.r.getDrawable(1);
    }

    public final sc1 b(boolean z) {
        RippleDrawable rippleDrawable = this.r;
        if (rippleDrawable == null || rippleDrawable.getNumberOfLayers() <= 0) {
            return null;
        }
        return (sc1) ((LayerDrawable) ((InsetDrawable) this.r.getDrawable(0)).getDrawable()).getDrawable(!z ? 1 : 0);
    }

    public final void c(uj2 uj2Var) {
        this.b = uj2Var;
        if (b(false) != null) {
            b(false).setShapeAppearanceModel(uj2Var);
        }
        if (b(true) != null) {
            b(true).setShapeAppearanceModel(uj2Var);
        }
        if (a() != null) {
            a().setShapeAppearanceModel(uj2Var);
        }
    }

    public final void d(int i, int i2) {
        WeakHashMap<View, in3> weakHashMap = om3.a;
        MaterialButton materialButton = this.a;
        int iE = om3.e.e(materialButton);
        int paddingTop = materialButton.getPaddingTop();
        int iD = om3.e.d(materialButton);
        int paddingBottom = materialButton.getPaddingBottom();
        int i3 = this.e;
        int i4 = this.f;
        this.f = i2;
        this.e = i;
        if (!this.o) {
            e();
        }
        om3.e.i(materialButton, iE, (paddingTop + i) - i3, iD, (paddingBottom + i2) - i4);
    }

    public final void e() {
        sc1 sc1Var = new sc1(this.b);
        MaterialButton materialButton = this.a;
        sc1Var.k(materialButton.getContext());
        sc1Var.setTintList(this.j);
        PorterDuff.Mode mode = this.i;
        if (mode != null) {
            sc1Var.setTintMode(mode);
        }
        float f = this.h;
        ColorStateList colorStateList = this.k;
        sc1Var.e.k = f;
        sc1Var.invalidateSelf();
        sc1Var.s(colorStateList);
        sc1 sc1Var2 = new sc1(this.b);
        sc1Var2.setTint(0);
        float f2 = this.h;
        int iP = this.n ? oj2.p(materialButton, R$attr.colorSurface) : 0;
        sc1Var2.e.k = f2;
        sc1Var2.invalidateSelf();
        sc1Var2.s(ColorStateList.valueOf(iP));
        sc1 sc1Var3 = new sc1(this.b);
        this.m = sc1Var3;
        sc1Var3.setTint(-1);
        RippleDrawable rippleDrawable = new RippleDrawable(dc2.c(this.l), new InsetDrawable((Drawable) new LayerDrawable(new Drawable[]{sc1Var2, sc1Var}), this.c, this.e, this.d, this.f), this.m);
        this.r = rippleDrawable;
        materialButton.setInternalBackground(rippleDrawable);
        sc1 sc1VarB = b(false);
        if (sc1VarB != null) {
            sc1VarB.m(this.s);
            sc1VarB.setState(materialButton.getDrawableState());
        }
    }

    public final void f() {
        sc1 sc1VarB = b(false);
        sc1 sc1VarB2 = b(true);
        if (sc1VarB != null) {
            float f = this.h;
            ColorStateList colorStateList = this.k;
            sc1VarB.e.k = f;
            sc1VarB.invalidateSelf();
            sc1VarB.s(colorStateList);
            if (sc1VarB2 != null) {
                float f2 = this.h;
                int iP = this.n ? oj2.p(this.a, R$attr.colorSurface) : 0;
                sc1VarB2.e.k = f2;
                sc1VarB2.invalidateSelf();
                sc1VarB2.s(ColorStateList.valueOf(iP));
            }
        }
    }
}
