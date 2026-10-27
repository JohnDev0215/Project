package com.google.android.material.button;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import b3.c;
import e3.g;
import e3.k;
import e3.n;
import i0.u;
import o2.b;
import o2.l;

/* JADX INFO: compiled from: MaterialButtonHelper.java */
/* JADX INFO: loaded from: classes.dex */
class a {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private static final boolean f4274t = true;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final MaterialButton f4275a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private k f4276b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f4277c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f4278d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f4279e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f4280f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f4281g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f4282h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private PorterDuff.Mode f4283i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private ColorStateList f4284j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private ColorStateList f4285k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private ColorStateList f4286l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private Drawable f4287m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private boolean f4288n = false;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private boolean f4289o = false;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private boolean f4290p = false;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private boolean f4291q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private LayerDrawable f4292r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private int f4293s;

    a(MaterialButton materialButton, k kVar) {
        this.f4275a = materialButton;
        this.f4276b = kVar;
    }

    private void E(int i7, int i8) {
        int iE = u.E(this.f4275a);
        int paddingTop = this.f4275a.getPaddingTop();
        int iD = u.D(this.f4275a);
        int paddingBottom = this.f4275a.getPaddingBottom();
        int i9 = this.f4279e;
        int i10 = this.f4280f;
        this.f4280f = i8;
        this.f4279e = i7;
        if (!this.f4289o) {
            F();
        }
        u.w0(this.f4275a, iE, (paddingTop + i7) - i9, iD, (paddingBottom + i8) - i10);
    }

    private void F() {
        this.f4275a.setInternalBackground(a());
        g gVarF = f();
        if (gVarF != null) {
            gVarF.W(this.f4293s);
        }
    }

    private void G(k kVar) {
        if (f() != null) {
            f().setShapeAppearanceModel(kVar);
        }
        if (n() != null) {
            n().setShapeAppearanceModel(kVar);
        }
        if (e() != null) {
            e().setShapeAppearanceModel(kVar);
        }
    }

    private void H() {
        g gVarF = f();
        g gVarN = n();
        if (gVarF != null) {
            gVarF.c0(this.f4282h, this.f4285k);
            if (gVarN != null) {
                gVarN.b0(this.f4282h, this.f4288n ? u2.a.c(this.f4275a, b.colorSurface) : 0);
            }
        }
    }

    private InsetDrawable I(Drawable drawable) {
        return new InsetDrawable(drawable, this.f4277c, this.f4279e, this.f4278d, this.f4280f);
    }

    private Drawable a() {
        g gVar = new g(this.f4276b);
        gVar.M(this.f4275a.getContext());
        a0.a.i(gVar, this.f4284j);
        PorterDuff.Mode mode = this.f4283i;
        if (mode != null) {
            a0.a.j(gVar, mode);
        }
        gVar.c0(this.f4282h, this.f4285k);
        g gVar2 = new g(this.f4276b);
        gVar2.setTint(0);
        gVar2.b0(this.f4282h, this.f4288n ? u2.a.c(this.f4275a, b.colorSurface) : 0);
        if (f4274t) {
            g gVar3 = new g(this.f4276b);
            this.f4287m = gVar3;
            a0.a.h(gVar3, -1);
            RippleDrawable rippleDrawable = new RippleDrawable(c3.b.d(this.f4286l), I(new LayerDrawable(new Drawable[]{gVar2, gVar})), this.f4287m);
            this.f4292r = rippleDrawable;
            return rippleDrawable;
        }
        c3.a aVar = new c3.a(this.f4276b);
        this.f4287m = aVar;
        a0.a.i(aVar, c3.b.d(this.f4286l));
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{gVar2, gVar, this.f4287m});
        this.f4292r = layerDrawable;
        return I(layerDrawable);
    }

    private g g(boolean z6) {
        LayerDrawable layerDrawable = this.f4292r;
        if (layerDrawable == null || layerDrawable.getNumberOfLayers() <= 0) {
            return null;
        }
        return f4274t ? (g) ((LayerDrawable) ((InsetDrawable) this.f4292r.getDrawable(0)).getDrawable()).getDrawable(!z6 ? 1 : 0) : (g) this.f4292r.getDrawable(!z6 ? 1 : 0);
    }

    private g n() {
        return g(true);
    }

    void A(ColorStateList colorStateList) {
        if (this.f4285k != colorStateList) {
            this.f4285k = colorStateList;
            H();
        }
    }

    void B(int i7) {
        if (this.f4282h != i7) {
            this.f4282h = i7;
            H();
        }
    }

    void C(ColorStateList colorStateList) {
        if (this.f4284j != colorStateList) {
            this.f4284j = colorStateList;
            if (f() != null) {
                a0.a.i(f(), this.f4284j);
            }
        }
    }

    void D(PorterDuff.Mode mode) {
        if (this.f4283i != mode) {
            this.f4283i = mode;
            if (f() == null || this.f4283i == null) {
                return;
            }
            a0.a.j(f(), this.f4283i);
        }
    }

    int b() {
        return this.f4281g;
    }

    public int c() {
        return this.f4280f;
    }

    public int d() {
        return this.f4279e;
    }

    public n e() {
        LayerDrawable layerDrawable = this.f4292r;
        if (layerDrawable == null || layerDrawable.getNumberOfLayers() <= 1) {
            return null;
        }
        return this.f4292r.getNumberOfLayers() > 2 ? (n) this.f4292r.getDrawable(2) : (n) this.f4292r.getDrawable(1);
    }

    g f() {
        return g(false);
    }

    ColorStateList h() {
        return this.f4286l;
    }

    k i() {
        return this.f4276b;
    }

    ColorStateList j() {
        return this.f4285k;
    }

    int k() {
        return this.f4282h;
    }

    ColorStateList l() {
        return this.f4284j;
    }

    PorterDuff.Mode m() {
        return this.f4283i;
    }

    boolean o() {
        return this.f4289o;
    }

    boolean p() {
        return this.f4291q;
    }

    void q(TypedArray typedArray) {
        this.f4277c = typedArray.getDimensionPixelOffset(l.MaterialButton_android_insetLeft, 0);
        this.f4278d = typedArray.getDimensionPixelOffset(l.MaterialButton_android_insetRight, 0);
        this.f4279e = typedArray.getDimensionPixelOffset(l.MaterialButton_android_insetTop, 0);
        this.f4280f = typedArray.getDimensionPixelOffset(l.MaterialButton_android_insetBottom, 0);
        int i7 = l.MaterialButton_cornerRadius;
        if (typedArray.hasValue(i7)) {
            int dimensionPixelSize = typedArray.getDimensionPixelSize(i7, -1);
            this.f4281g = dimensionPixelSize;
            y(this.f4276b.w(dimensionPixelSize));
            this.f4290p = true;
        }
        this.f4282h = typedArray.getDimensionPixelSize(l.MaterialButton_strokeWidth, 0);
        this.f4283i = com.google.android.material.internal.l.e(typedArray.getInt(l.MaterialButton_backgroundTintMode, -1), PorterDuff.Mode.SRC_IN);
        this.f4284j = c.a(this.f4275a.getContext(), typedArray, l.MaterialButton_backgroundTint);
        this.f4285k = c.a(this.f4275a.getContext(), typedArray, l.MaterialButton_strokeColor);
        this.f4286l = c.a(this.f4275a.getContext(), typedArray, l.MaterialButton_rippleColor);
        this.f4291q = typedArray.getBoolean(l.MaterialButton_android_checkable, false);
        this.f4293s = typedArray.getDimensionPixelSize(l.MaterialButton_elevation, 0);
        int iE = u.E(this.f4275a);
        int paddingTop = this.f4275a.getPaddingTop();
        int iD = u.D(this.f4275a);
        int paddingBottom = this.f4275a.getPaddingBottom();
        if (typedArray.hasValue(l.MaterialButton_android_background)) {
            s();
        } else {
            F();
        }
        u.w0(this.f4275a, iE + this.f4277c, paddingTop + this.f4279e, iD + this.f4278d, paddingBottom + this.f4280f);
    }

    void r(int i7) {
        if (f() != null) {
            f().setTint(i7);
        }
    }

    void s() {
        this.f4289o = true;
        this.f4275a.setSupportBackgroundTintList(this.f4284j);
        this.f4275a.setSupportBackgroundTintMode(this.f4283i);
    }

    void t(boolean z6) {
        this.f4291q = z6;
    }

    void u(int i7) {
        if (this.f4290p && this.f4281g == i7) {
            return;
        }
        this.f4281g = i7;
        this.f4290p = true;
        y(this.f4276b.w(i7));
    }

    public void v(int i7) {
        E(this.f4279e, i7);
    }

    public void w(int i7) {
        E(i7, this.f4280f);
    }

    void x(ColorStateList colorStateList) {
        if (this.f4286l != colorStateList) {
            this.f4286l = colorStateList;
            boolean z6 = f4274t;
            if (z6 && (this.f4275a.getBackground() instanceof RippleDrawable)) {
                ((RippleDrawable) this.f4275a.getBackground()).setColor(c3.b.d(colorStateList));
            } else {
                if (z6 || !(this.f4275a.getBackground() instanceof c3.a)) {
                    return;
                }
                ((c3.a) this.f4275a.getBackground()).setTintList(c3.b.d(colorStateList));
            }
        }
    }

    void y(k kVar) {
        this.f4276b = kVar;
        G(kVar);
    }

    void z(boolean z6) {
        this.f4288n = z6;
        H();
    }
}
