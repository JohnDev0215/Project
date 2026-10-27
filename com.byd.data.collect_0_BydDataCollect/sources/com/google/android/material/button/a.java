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
import com.google.android.material.internal.q;
import d.b.a.a.b;
import d.b.a.a.l;
import d.b.a.a.u.c;
import d.b.a.a.x.h;
import d.b.a.a.x.m;
import d.b.a.a.x.p;

/* JADX INFO: compiled from: MaterialButtonHelper.java */
/* JADX INFO: loaded from: classes.dex */
class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final boolean f3847a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final MaterialButton f3848b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private m f3849c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f3850d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3851e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f3852f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f3853g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f3854h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f3855i;
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
        f3847a = Build.VERSION.SDK_INT >= 21;
    }

    a(MaterialButton materialButton, m mVar) {
        this.f3848b = materialButton;
        this.f3849c = mVar;
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
            hVarD.h0(this.f3855i, this.l);
            if (hVarL != null) {
                hVarL.g0(this.f3855i, this.o ? d.b.a.a.o.a.c(this.f3848b, b.colorSurface) : 0);
            }
        }
    }

    private InsetDrawable D(Drawable drawable) {
        return new InsetDrawable(drawable, this.f3850d, this.f3852f, this.f3851e, this.f3853g);
    }

    private Drawable a() {
        h hVar = new h(this.f3849c);
        hVar.N(this.f3848b.getContext());
        androidx.core.graphics.drawable.a.o(hVar, this.k);
        PorterDuff.Mode mode = this.j;
        if (mode != null) {
            androidx.core.graphics.drawable.a.p(hVar, mode);
        }
        hVar.h0(this.f3855i, this.l);
        h hVar2 = new h(this.f3849c);
        hVar2.setTint(0);
        hVar2.g0(this.f3855i, this.o ? d.b.a.a.o.a.c(this.f3848b, b.colorSurface) : 0);
        if (f3847a) {
            h hVar3 = new h(this.f3849c);
            this.n = hVar3;
            androidx.core.graphics.drawable.a.n(hVar3, -1);
            RippleDrawable rippleDrawable = new RippleDrawable(d.b.a.a.v.b.d(this.m), D(new LayerDrawable(new Drawable[]{hVar2, hVar})), this.n);
            this.s = rippleDrawable;
            return rippleDrawable;
        }
        d.b.a.a.v.a aVar = new d.b.a.a.v.a(this.f3849c);
        this.n = aVar;
        androidx.core.graphics.drawable.a.o(aVar, d.b.a.a.v.b.d(this.m));
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{hVar2, hVar, this.n});
        this.s = layerDrawable;
        return D(layerDrawable);
    }

    private h e(boolean z) {
        LayerDrawable layerDrawable = this.s;
        if (layerDrawable == null || layerDrawable.getNumberOfLayers() <= 0) {
            return null;
        }
        return f3847a ? (h) ((LayerDrawable) ((InsetDrawable) this.s.getDrawable(0)).getDrawable()).getDrawable(!z ? 1 : 0) : (h) this.s.getDrawable(!z ? 1 : 0);
    }

    private h l() {
        return e(true);
    }

    void B(int i2, int i3) {
        Drawable drawable = this.n;
        if (drawable != null) {
            drawable.setBounds(this.f3850d, this.f3852f, i3 - this.f3851e, i2 - this.f3853g);
        }
    }

    int b() {
        return this.f3854h;
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
        return this.f3849c;
    }

    ColorStateList h() {
        return this.l;
    }

    int i() {
        return this.f3855i;
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
        this.f3850d = typedArray.getDimensionPixelOffset(l.MaterialButton_android_insetLeft, 0);
        this.f3851e = typedArray.getDimensionPixelOffset(l.MaterialButton_android_insetRight, 0);
        this.f3852f = typedArray.getDimensionPixelOffset(l.MaterialButton_android_insetTop, 0);
        this.f3853g = typedArray.getDimensionPixelOffset(l.MaterialButton_android_insetBottom, 0);
        int i2 = l.MaterialButton_cornerRadius;
        if (typedArray.hasValue(i2)) {
            int dimensionPixelSize = typedArray.getDimensionPixelSize(i2, -1);
            this.f3854h = dimensionPixelSize;
            u(this.f3849c.w(dimensionPixelSize));
            this.q = true;
        }
        this.f3855i = typedArray.getDimensionPixelSize(l.MaterialButton_strokeWidth, 0);
        this.j = q.i(typedArray.getInt(l.MaterialButton_backgroundTintMode, -1), PorterDuff.Mode.SRC_IN);
        this.k = c.a(this.f3848b.getContext(), typedArray, l.MaterialButton_backgroundTint);
        this.l = c.a(this.f3848b.getContext(), typedArray, l.MaterialButton_strokeColor);
        this.m = c.a(this.f3848b.getContext(), typedArray, l.MaterialButton_rippleColor);
        this.r = typedArray.getBoolean(l.MaterialButton_android_checkable, false);
        int dimensionPixelSize2 = typedArray.getDimensionPixelSize(l.MaterialButton_elevation, 0);
        int iE = t.E(this.f3848b);
        int paddingTop = this.f3848b.getPaddingTop();
        int iD = t.D(this.f3848b);
        int paddingBottom = this.f3848b.getPaddingBottom();
        if (typedArray.hasValue(l.MaterialButton_android_background)) {
            q();
        } else {
            this.f3848b.setInternalBackground(a());
            h hVarD = d();
            if (hVarD != null) {
                hVarD.W(dimensionPixelSize2);
            }
        }
        t.w0(this.f3848b, iE + this.f3850d, paddingTop + this.f3852f, iD + this.f3851e, paddingBottom + this.f3853g);
    }

    void p(int i2) {
        if (d() != null) {
            d().setTint(i2);
        }
    }

    void q() {
        this.p = true;
        this.f3848b.setSupportBackgroundTintList(this.k);
        this.f3848b.setSupportBackgroundTintMode(this.j);
    }

    void r(boolean z) {
        this.r = z;
    }

    void s(int i2) {
        if (this.q && this.f3854h == i2) {
            return;
        }
        this.f3854h = i2;
        this.q = true;
        u(this.f3849c.w(i2));
    }

    void t(ColorStateList colorStateList) {
        if (this.m != colorStateList) {
            this.m = colorStateList;
            boolean z = f3847a;
            if (z && (this.f3848b.getBackground() instanceof RippleDrawable)) {
                ((RippleDrawable) this.f3848b.getBackground()).setColor(d.b.a.a.v.b.d(colorStateList));
            } else {
                if (z || !(this.f3848b.getBackground() instanceof d.b.a.a.v.a)) {
                    return;
                }
                ((d.b.a.a.v.a) this.f3848b.getBackground()).setTintList(d.b.a.a.v.b.d(colorStateList));
            }
        }
    }

    void u(m mVar) {
        this.f3849c = mVar;
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

    void x(int i2) {
        if (this.f3855i != i2) {
            this.f3855i = i2;
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
