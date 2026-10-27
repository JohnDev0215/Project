package com.google.android.material.button;

import a.g.k.t;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import b.a.a.a.b;
import b.a.a.a.b0.g;
import b.a.a.a.b0.k;
import b.a.a.a.b0.n;
import b.a.a.a.l;
import b.a.a.a.y.c;
import com.google.android.material.internal.j;

/* JADX INFO: loaded from: classes.dex */
class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final boolean f3024a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final MaterialButton f3025b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private k f3026c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f3027d;
    private int e;
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
        f3024a = Build.VERSION.SDK_INT >= 21;
    }

    a(MaterialButton materialButton, k kVar) {
        this.f3025b = materialButton;
        this.f3026c = kVar;
    }

    private void A(k kVar) {
        if (d() != null) {
            d().setShapeAppearanceModel(kVar);
        }
        if (l() != null) {
            l().setShapeAppearanceModel(kVar);
        }
        if (c() != null) {
            c().setShapeAppearanceModel(kVar);
        }
    }

    private void C() {
        g gVarD = d();
        g gVarL = l();
        if (gVarD != null) {
            gVarD.c0(this.i, this.l);
            if (gVarL != null) {
                gVarL.b0(this.i, this.o ? b.a.a.a.s.a.c(this.f3025b, b.l) : 0);
            }
        }
    }

    private InsetDrawable D(Drawable drawable) {
        return new InsetDrawable(drawable, this.f3027d, this.f, this.e, this.g);
    }

    private Drawable a() {
        g gVar = new g(this.f3026c);
        gVar.M(this.f3025b.getContext());
        androidx.core.graphics.drawable.a.o(gVar, this.k);
        PorterDuff.Mode mode = this.j;
        if (mode != null) {
            androidx.core.graphics.drawable.a.p(gVar, mode);
        }
        gVar.c0(this.i, this.l);
        g gVar2 = new g(this.f3026c);
        gVar2.setTint(0);
        gVar2.b0(this.i, this.o ? b.a.a.a.s.a.c(this.f3025b, b.l) : 0);
        if (f3024a) {
            g gVar3 = new g(this.f3026c);
            this.n = gVar3;
            androidx.core.graphics.drawable.a.n(gVar3, -1);
            RippleDrawable rippleDrawable = new RippleDrawable(b.a.a.a.z.b.d(this.m), D(new LayerDrawable(new Drawable[]{gVar2, gVar})), this.n);
            this.s = rippleDrawable;
            return rippleDrawable;
        }
        b.a.a.a.z.a aVar = new b.a.a.a.z.a(this.f3026c);
        this.n = aVar;
        androidx.core.graphics.drawable.a.o(aVar, b.a.a.a.z.b.d(this.m));
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{gVar2, gVar, this.n});
        this.s = layerDrawable;
        return D(layerDrawable);
    }

    private g e(boolean z) {
        LayerDrawable layerDrawable = this.s;
        if (layerDrawable == null || layerDrawable.getNumberOfLayers() <= 0) {
            return null;
        }
        return (g) (f3024a ? (LayerDrawable) ((InsetDrawable) this.s.getDrawable(0)).getDrawable() : this.s).getDrawable(!z ? 1 : 0);
    }

    private g l() {
        return e(true);
    }

    void B(int i, int i2) {
        Drawable drawable = this.n;
        if (drawable != null) {
            drawable.setBounds(this.f3027d, this.f, i2 - this.e, i - this.g);
        }
    }

    int b() {
        return this.h;
    }

    public n c() {
        LayerDrawable layerDrawable = this.s;
        if (layerDrawable == null || layerDrawable.getNumberOfLayers() <= 1) {
            return null;
        }
        return (n) (this.s.getNumberOfLayers() > 2 ? this.s.getDrawable(2) : this.s.getDrawable(1));
    }

    g d() {
        return e(false);
    }

    ColorStateList f() {
        return this.m;
    }

    k g() {
        return this.f3026c;
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
        this.f3027d = typedArray.getDimensionPixelOffset(l.z1, 0);
        this.e = typedArray.getDimensionPixelOffset(l.A1, 0);
        this.f = typedArray.getDimensionPixelOffset(l.B1, 0);
        this.g = typedArray.getDimensionPixelOffset(l.C1, 0);
        int i = l.G1;
        if (typedArray.hasValue(i)) {
            int dimensionPixelSize = typedArray.getDimensionPixelSize(i, -1);
            this.h = dimensionPixelSize;
            u(this.f3026c.w(dimensionPixelSize));
            this.q = true;
        }
        this.i = typedArray.getDimensionPixelSize(l.Q1, 0);
        this.j = j.e(typedArray.getInt(l.F1, -1), PorterDuff.Mode.SRC_IN);
        this.k = c.a(this.f3025b.getContext(), typedArray, l.E1);
        this.l = c.a(this.f3025b.getContext(), typedArray, l.P1);
        this.m = c.a(this.f3025b.getContext(), typedArray, l.O1);
        this.r = typedArray.getBoolean(l.D1, false);
        int dimensionPixelSize2 = typedArray.getDimensionPixelSize(l.H1, 0);
        int iD = t.D(this.f3025b);
        int paddingTop = this.f3025b.getPaddingTop();
        int iC = t.C(this.f3025b);
        int paddingBottom = this.f3025b.getPaddingBottom();
        if (typedArray.hasValue(l.y1)) {
            q();
        } else {
            this.f3025b.setInternalBackground(a());
            g gVarD = d();
            if (gVarD != null) {
                gVarD.V(dimensionPixelSize2);
            }
        }
        t.t0(this.f3025b, iD + this.f3027d, paddingTop + this.f, iC + this.e, paddingBottom + this.g);
    }

    void p(int i) {
        if (d() != null) {
            d().setTint(i);
        }
    }

    void q() {
        this.p = true;
        this.f3025b.setSupportBackgroundTintList(this.k);
        this.f3025b.setSupportBackgroundTintMode(this.j);
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
        u(this.f3026c.w(i));
    }

    void t(ColorStateList colorStateList) {
        if (this.m != colorStateList) {
            this.m = colorStateList;
            boolean z = f3024a;
            if (z && (this.f3025b.getBackground() instanceof RippleDrawable)) {
                ((RippleDrawable) this.f3025b.getBackground()).setColor(b.a.a.a.z.b.d(colorStateList));
            } else {
                if (z || !(this.f3025b.getBackground() instanceof b.a.a.a.z.a)) {
                    return;
                }
                ((b.a.a.a.z.a) this.f3025b.getBackground()).setTintList(b.a.a.a.z.b.d(colorStateList));
            }
        }
    }

    void u(k kVar) {
        this.f3026c = kVar;
        A(kVar);
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
