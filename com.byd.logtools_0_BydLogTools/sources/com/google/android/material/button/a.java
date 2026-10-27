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
import d.b.a.a.b;
import d.b.a.a.l;
import d.b.a.a.t.c;
import d.b.a.a.w.h;
import d.b.a.a.w.m;
import d.b.a.a.w.p;

/* JADX INFO: compiled from: MaterialButtonHelper.java */
/* JADX INFO: loaded from: classes.dex */
class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final boolean f3457a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final MaterialButton f3458b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private m f3459c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f3460d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3461e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f3462f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f3463g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f3464h;
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
        f3457a = Build.VERSION.SDK_INT >= 21;
    }

    a(MaterialButton materialButton, m mVar) {
        this.f3458b = materialButton;
        this.f3459c = mVar;
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
                hVarL.g0(this.i, this.o ? d.b.a.a.o.a.c(this.f3458b, b.p) : 0);
            }
        }
    }

    private InsetDrawable D(Drawable drawable) {
        return new InsetDrawable(drawable, this.f3460d, this.f3462f, this.f3461e, this.f3463g);
    }

    private Drawable a() {
        h hVar = new h(this.f3459c);
        hVar.N(this.f3458b.getContext());
        androidx.core.graphics.drawable.a.o(hVar, this.k);
        PorterDuff.Mode mode = this.j;
        if (mode != null) {
            androidx.core.graphics.drawable.a.p(hVar, mode);
        }
        hVar.h0(this.i, this.l);
        h hVar2 = new h(this.f3459c);
        hVar2.setTint(0);
        hVar2.g0(this.i, this.o ? d.b.a.a.o.a.c(this.f3458b, b.p) : 0);
        if (f3457a) {
            h hVar3 = new h(this.f3459c);
            this.n = hVar3;
            androidx.core.graphics.drawable.a.n(hVar3, -1);
            RippleDrawable rippleDrawable = new RippleDrawable(d.b.a.a.u.b.d(this.m), D(new LayerDrawable(new Drawable[]{hVar2, hVar})), this.n);
            this.s = rippleDrawable;
            return rippleDrawable;
        }
        d.b.a.a.u.a aVar = new d.b.a.a.u.a(this.f3459c);
        this.n = aVar;
        androidx.core.graphics.drawable.a.o(aVar, d.b.a.a.u.b.d(this.m));
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{hVar2, hVar, this.n});
        this.s = layerDrawable;
        return D(layerDrawable);
    }

    private h e(boolean z) {
        LayerDrawable layerDrawable = this.s;
        if (layerDrawable == null || layerDrawable.getNumberOfLayers() <= 0) {
            return null;
        }
        return f3457a ? (h) ((LayerDrawable) ((InsetDrawable) this.s.getDrawable(0)).getDrawable()).getDrawable(!z ? 1 : 0) : (h) this.s.getDrawable(!z ? 1 : 0);
    }

    private h l() {
        return e(true);
    }

    void B(int i, int i2) {
        Drawable drawable = this.n;
        if (drawable != null) {
            drawable.setBounds(this.f3460d, this.f3462f, i2 - this.f3461e, i - this.f3463g);
        }
    }

    int b() {
        return this.f3464h;
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
        return this.f3459c;
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
        this.f3460d = typedArray.getDimensionPixelOffset(l.U2, 0);
        this.f3461e = typedArray.getDimensionPixelOffset(l.V2, 0);
        this.f3462f = typedArray.getDimensionPixelOffset(l.W2, 0);
        this.f3463g = typedArray.getDimensionPixelOffset(l.X2, 0);
        int i = l.b3;
        if (typedArray.hasValue(i)) {
            int dimensionPixelSize = typedArray.getDimensionPixelSize(i, -1);
            this.f3464h = dimensionPixelSize;
            u(this.f3459c.w(dimensionPixelSize));
            this.q = true;
        }
        this.i = typedArray.getDimensionPixelSize(l.l3, 0);
        this.j = com.google.android.material.internal.p.i(typedArray.getInt(l.a3, -1), PorterDuff.Mode.SRC_IN);
        this.k = c.a(this.f3458b.getContext(), typedArray, l.Z2);
        this.l = c.a(this.f3458b.getContext(), typedArray, l.k3);
        this.m = c.a(this.f3458b.getContext(), typedArray, l.j3);
        this.r = typedArray.getBoolean(l.Y2, false);
        int dimensionPixelSize2 = typedArray.getDimensionPixelSize(l.c3, 0);
        int iE = t.E(this.f3458b);
        int paddingTop = this.f3458b.getPaddingTop();
        int iD = t.D(this.f3458b);
        int paddingBottom = this.f3458b.getPaddingBottom();
        if (typedArray.hasValue(l.T2)) {
            q();
        } else {
            this.f3458b.setInternalBackground(a());
            h hVarD = d();
            if (hVarD != null) {
                hVarD.W(dimensionPixelSize2);
            }
        }
        t.w0(this.f3458b, iE + this.f3460d, paddingTop + this.f3462f, iD + this.f3461e, paddingBottom + this.f3463g);
    }

    void p(int i) {
        if (d() != null) {
            d().setTint(i);
        }
    }

    void q() {
        this.p = true;
        this.f3458b.setSupportBackgroundTintList(this.k);
        this.f3458b.setSupportBackgroundTintMode(this.j);
    }

    void r(boolean z) {
        this.r = z;
    }

    void s(int i) {
        if (this.q && this.f3464h == i) {
            return;
        }
        this.f3464h = i;
        this.q = true;
        u(this.f3459c.w(i));
    }

    void t(ColorStateList colorStateList) {
        if (this.m != colorStateList) {
            this.m = colorStateList;
            boolean z = f3457a;
            if (z && (this.f3458b.getBackground() instanceof RippleDrawable)) {
                ((RippleDrawable) this.f3458b.getBackground()).setColor(d.b.a.a.u.b.d(colorStateList));
            } else {
                if (z || !(this.f3458b.getBackground() instanceof d.b.a.a.u.a)) {
                    return;
                }
                ((d.b.a.a.u.a) this.f3458b.getBackground()).setTintList(d.b.a.a.u.b.d(colorStateList));
            }
        }
    }

    void u(m mVar) {
        this.f3459c = mVar;
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
