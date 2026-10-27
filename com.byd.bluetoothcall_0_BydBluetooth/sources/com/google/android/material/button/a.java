package com.google.android.material.button;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import b.g.k.t;
import c.b.a.a.b;
import c.b.a.a.l;
import c.b.a.a.t.c;
import c.b.a.a.w.h;
import c.b.a.a.w.m;
import c.b.a.a.w.p;

/* JADX INFO: compiled from: MaterialButtonHelper.java */
/* JADX INFO: loaded from: classes.dex */
class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final boolean f3716a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final MaterialButton f3717b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private m f3718c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f3719d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3720e;
    private int f;
    private int g;
    private int h;
    private int i;
    private PorterDuff.Mode j;
    private ColorStateList k;
    private ColorStateList l;
    private ColorStateList m;
    private Drawable n;
    private boolean o = false;
    private boolean p = false;
    private boolean q = false;
    private boolean r;
    private LayerDrawable s;

    static {
        f3716a = Build.VERSION.SDK_INT >= 21;
    }

    a(MaterialButton materialButton, m mVar) {
        this.f3717b = materialButton;
        this.f3718c = mVar;
    }

    private void A(m mVar) {
        if (d() != null) {
            d().setShapeAppearanceModel(mVar);
        }
        if (l() != null) {
            l().setShapeAppearanceModel(mVar);
        }
        if (c() != null) {
            c().setShapeAppearanceModel(mVar);
        }
    }

    private void C() {
        h hVarD = d();
        h hVarL = l();
        if (hVarD != null) {
            hVarD.h0(this.i, this.l);
            if (hVarL != null) {
                hVarL.g0(this.i, this.o ? c.b.a.a.o.a.c(this.f3717b, b.p) : 0);
            }
        }
    }

    private InsetDrawable D(Drawable drawable) {
        return new InsetDrawable(drawable, this.f3719d, this.f, this.f3720e, this.g);
    }

    private Drawable a() {
        h hVar = new h(this.f3718c);
        hVar.N(this.f3717b.getContext());
        androidx.core.graphics.drawable.a.o(hVar, this.k);
        PorterDuff.Mode mode = this.j;
        if (mode != null) {
            androidx.core.graphics.drawable.a.p(hVar, mode);
        }
        hVar.h0(this.i, this.l);
        h hVar2 = new h(this.f3718c);
        hVar2.setTint(0);
        hVar2.g0(this.i, this.o ? c.b.a.a.o.a.c(this.f3717b, b.p) : 0);
        if (f3716a) {
            h hVar3 = new h(this.f3718c);
            this.n = hVar3;
            androidx.core.graphics.drawable.a.n(hVar3, -1);
            RippleDrawable rippleDrawable = new RippleDrawable(c.b.a.a.u.b.d(this.m), D(new LayerDrawable(new Drawable[]{hVar2, hVar})), this.n);
            this.s = rippleDrawable;
            return rippleDrawable;
        }
        c.b.a.a.u.a aVar = new c.b.a.a.u.a(this.f3718c);
        this.n = aVar;
        androidx.core.graphics.drawable.a.o(aVar, c.b.a.a.u.b.d(this.m));
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{hVar2, hVar, this.n});
        this.s = layerDrawable;
        return D(layerDrawable);
    }

    private h e(boolean z) {
        LayerDrawable layerDrawable = this.s;
        if (layerDrawable == null || layerDrawable.getNumberOfLayers() <= 0) {
            return null;
        }
        return f3716a ? (h) ((LayerDrawable) ((InsetDrawable) this.s.getDrawable(0)).getDrawable()).getDrawable(!z ? 1 : 0) : (h) this.s.getDrawable(!z ? 1 : 0);
    }

    private h l() {
        return e(true);
    }

    void B(int i, int i2) {
        Drawable drawable = this.n;
        if (drawable != null) {
            drawable.setBounds(this.f3719d, this.f, i2 - this.f3720e, i - this.g);
        }
    }

    int b() {
        return this.h;
    }

    public p c() {
        LayerDrawable layerDrawable = this.s;
        if (layerDrawable == null || layerDrawable.getNumberOfLayers() <= 1) {
            return null;
        }
        return this.s.getNumberOfLayers() > 2 ? (p) this.s.getDrawable(2) : (p) this.s.getDrawable(1);
    }

    h d() {
        return e(false);
    }

    ColorStateList f() {
        return this.m;
    }

    m g() {
        return this.f3718c;
    }

    ColorStateList h() {
        return this.l;
    }

    int i() {
        return this.i;
    }

    ColorStateList j() {
        return this.k;
    }

    PorterDuff.Mode k() {
        return this.j;
    }

    boolean m() {
        return this.p;
    }

    boolean n() {
        return this.r;
    }

    void o(TypedArray typedArray) {
        this.f3719d = typedArray.getDimensionPixelOffset(l.U2, 0);
        this.f3720e = typedArray.getDimensionPixelOffset(l.V2, 0);
        this.f = typedArray.getDimensionPixelOffset(l.W2, 0);
        this.g = typedArray.getDimensionPixelOffset(l.X2, 0);
        int i = l.b3;
        if (typedArray.hasValue(i)) {
            int dimensionPixelSize = typedArray.getDimensionPixelSize(i, -1);
            this.h = dimensionPixelSize;
            u(this.f3718c.w(dimensionPixelSize));
            this.q = true;
        }
        this.i = typedArray.getDimensionPixelSize(l.l3, 0);
        this.j = com.google.android.material.internal.p.i(typedArray.getInt(l.a3, -1), PorterDuff.Mode.SRC_IN);
        this.k = c.a(this.f3717b.getContext(), typedArray, l.Z2);
        this.l = c.a(this.f3717b.getContext(), typedArray, l.k3);
        this.m = c.a(this.f3717b.getContext(), typedArray, l.j3);
        this.r = typedArray.getBoolean(l.Y2, false);
        int dimensionPixelSize2 = typedArray.getDimensionPixelSize(l.c3, 0);
        int iE = t.E(this.f3717b);
        int paddingTop = this.f3717b.getPaddingTop();
        int iD = t.D(this.f3717b);
        int paddingBottom = this.f3717b.getPaddingBottom();
        if (typedArray.hasValue(l.T2)) {
            q();
        } else {
            this.f3717b.setInternalBackground(a());
            h hVarD = d();
            if (hVarD != null) {
                hVarD.W(dimensionPixelSize2);
            }
        }
        t.y0(this.f3717b, iE + this.f3719d, paddingTop + this.f, iD + this.f3720e, paddingBottom + this.g);
    }

    void p(int i) {
        if (d() != null) {
            d().setTint(i);
        }
    }

    void q() {
        this.p = true;
        this.f3717b.setSupportBackgroundTintList(this.k);
        this.f3717b.setSupportBackgroundTintMode(this.j);
    }

    void r(boolean z) {
        this.r = z;
    }

    void s(int i) {
        if (this.q && this.h == i) {
            return;
        }
        this.h = i;
        this.q = true;
        u(this.f3718c.w(i));
    }

    void t(ColorStateList colorStateList) {
        if (this.m != colorStateList) {
            this.m = colorStateList;
            boolean z = f3716a;
            if (z && (this.f3717b.getBackground() instanceof RippleDrawable)) {
                ((RippleDrawable) this.f3717b.getBackground()).setColor(c.b.a.a.u.b.d(colorStateList));
            } else {
                if (z || !(this.f3717b.getBackground() instanceof c.b.a.a.u.a)) {
                    return;
                }
                ((c.b.a.a.u.a) this.f3717b.getBackground()).setTintList(c.b.a.a.u.b.d(colorStateList));
            }
        }
    }

    void u(m mVar) {
        this.f3718c = mVar;
        A(mVar);
    }

    void v(boolean z) {
        this.o = z;
        C();
    }

    void w(ColorStateList colorStateList) {
        if (this.l != colorStateList) {
            this.l = colorStateList;
            C();
        }
    }

    void x(int i) {
        if (this.i != i) {
            this.i = i;
            C();
        }
    }

    void y(ColorStateList colorStateList) {
        if (this.k != colorStateList) {
            this.k = colorStateList;
            if (d() != null) {
                androidx.core.graphics.drawable.a.o(d(), this.k);
            }
        }
    }

    void z(PorterDuff.Mode mode) {
        if (this.j != mode) {
            this.j = mode;
            if (d() == null || this.j == null) {
                return;
            }
            androidx.core.graphics.drawable.a.p(d(), this.j);
        }
    }
}
