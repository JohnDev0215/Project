package com.google.android.material.card;

import android.R;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.AttributeSet;
import b.g.k.t;
import d.b.a.a.d;
import d.b.a.a.f;
import d.b.a.a.k;
import d.b.a.a.l;
import d.b.a.a.t.c;
import d.b.a.a.u.b;
import d.b.a.a.w.e;
import d.b.a.a.w.h;
import d.b.a.a.w.m;

/* JADX INFO: compiled from: MaterialCardViewHelper.java */
/* JADX INFO: loaded from: classes.dex */
class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int[] f3465a = {R.attr.state_checked};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final double f3466b = Math.cos(Math.toRadians(45.0d));

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final MaterialCardView f3467c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final h f3469e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final h f3470f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final int f3471g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final int f3472h;
    private int i;
    private Drawable j;
    private Drawable k;
    private ColorStateList l;
    private ColorStateList m;
    private m n;
    private ColorStateList o;
    private Drawable p;
    private LayerDrawable q;
    private h r;
    private h s;
    private boolean u;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Rect f3468d = new Rect();
    private boolean t = false;

    /* JADX INFO: renamed from: com.google.android.material.card.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: MaterialCardViewHelper.java */
    class C0063a extends InsetDrawable {
        C0063a(Drawable drawable, int i, int i2, int i3, int i4) {
            super(drawable, i, i2, i3, i4);
        }

        @Override // android.graphics.drawable.Drawable
        public int getMinimumHeight() {
            return -1;
        }

        @Override // android.graphics.drawable.Drawable
        public int getMinimumWidth() {
            return -1;
        }

        @Override // android.graphics.drawable.InsetDrawable, android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
        public boolean getPadding(Rect rect) {
            return false;
        }
    }

    public a(MaterialCardView materialCardView, AttributeSet attributeSet, int i, int i2) {
        this.f3467c = materialCardView;
        h hVar = new h(materialCardView.getContext(), attributeSet, i, i2);
        this.f3469e = hVar;
        hVar.N(materialCardView.getContext());
        hVar.d0(-12303292);
        m.b bVarV = hVar.D().v();
        TypedArray typedArrayObtainStyledAttributes = materialCardView.getContext().obtainStyledAttributes(attributeSet, l.x0, i, k.f4744a);
        int i3 = l.y0;
        if (typedArrayObtainStyledAttributes.hasValue(i3)) {
            bVarV.o(typedArrayObtainStyledAttributes.getDimension(i3, 0.0f));
        }
        this.f3470f = new h();
        N(bVarV.m());
        Resources resources = materialCardView.getResources();
        this.f3471g = resources.getDimensionPixelSize(d.B);
        this.f3472h = resources.getDimensionPixelSize(d.C);
        typedArrayObtainStyledAttributes.recycle();
    }

    private boolean R() {
        return this.f3467c.getPreventCornerOverlap() && !e();
    }

    private boolean S() {
        return this.f3467c.getPreventCornerOverlap() && e() && this.f3467c.getUseCompatPadding();
    }

    private void W(Drawable drawable) {
        if (Build.VERSION.SDK_INT < 23 || !(this.f3467c.getForeground() instanceof InsetDrawable)) {
            this.f3467c.setForeground(z(drawable));
        } else {
            ((InsetDrawable) this.f3467c.getForeground()).setDrawable(drawable);
        }
    }

    private void Y() {
        Drawable drawable;
        if (b.f4810a && (drawable = this.p) != null) {
            ((RippleDrawable) drawable).setColor(this.l);
            return;
        }
        h hVar = this.r;
        if (hVar != null) {
            hVar.X(this.l);
        }
    }

    private float a() {
        return Math.max(Math.max(b(this.n.q(), this.f3469e.G()), b(this.n.s(), this.f3469e.H())), Math.max(b(this.n.k(), this.f3469e.t()), b(this.n.i(), this.f3469e.s())));
    }

    private float b(d.b.a.a.w.d dVar, float f2) {
        if (dVar instanceof d.b.a.a.w.l) {
            return (float) ((1.0d - f3466b) * ((double) f2));
        }
        if (dVar instanceof e) {
            return f2 / 2.0f;
        }
        return 0.0f;
    }

    private float c() {
        return this.f3467c.getMaxCardElevation() + (S() ? a() : 0.0f);
    }

    private float d() {
        return (this.f3467c.getMaxCardElevation() * 1.5f) + (S() ? a() : 0.0f);
    }

    private boolean e() {
        return Build.VERSION.SDK_INT >= 21 && this.f3469e.Q();
    }

    private Drawable f() {
        StateListDrawable stateListDrawable = new StateListDrawable();
        Drawable drawable = this.k;
        if (drawable != null) {
            stateListDrawable.addState(f3465a, drawable);
        }
        return stateListDrawable;
    }

    private Drawable g() {
        StateListDrawable stateListDrawable = new StateListDrawable();
        h hVarI = i();
        this.r = hVarI;
        hVarI.X(this.l);
        stateListDrawable.addState(new int[]{R.attr.state_pressed}, this.r);
        return stateListDrawable;
    }

    private Drawable h() {
        if (!b.f4810a) {
            return g();
        }
        h hVarI = i();
        this.s = hVarI;
        return new RippleDrawable(this.l, null, hVarI);
    }

    private h i() {
        return new h(this.n);
    }

    private Drawable p() {
        if (this.p == null) {
            this.p = h();
        }
        if (this.q == null) {
            LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{this.p, this.f3470f, f()});
            this.q = layerDrawable;
            layerDrawable.setId(2, f.p);
        }
        return this.q;
    }

    private float r() {
        if (!this.f3467c.getPreventCornerOverlap()) {
            return 0.0f;
        }
        if (Build.VERSION.SDK_INT < 21 || this.f3467c.getUseCompatPadding()) {
            return (float) ((1.0d - f3466b) * ((double) this.f3467c.getCardViewRadius()));
        }
        return 0.0f;
    }

    private Drawable z(Drawable drawable) {
        int iCeil;
        int i;
        if ((Build.VERSION.SDK_INT < 21) || this.f3467c.getUseCompatPadding()) {
            int iCeil2 = (int) Math.ceil(d());
            iCeil = (int) Math.ceil(c());
            i = iCeil2;
        } else {
            iCeil = 0;
            i = 0;
        }
        return new C0063a(drawable, iCeil, i, iCeil, i);
    }

    boolean A() {
        return this.t;
    }

    boolean B() {
        return this.u;
    }

    void C(TypedArray typedArray) {
        ColorStateList colorStateListA = c.a(this.f3467c.getContext(), typedArray, l.Q3);
        this.o = colorStateListA;
        if (colorStateListA == null) {
            this.o = ColorStateList.valueOf(-1);
        }
        this.i = typedArray.getDimensionPixelSize(l.R3, 0);
        boolean z = typedArray.getBoolean(l.L3, false);
        this.u = z;
        this.f3467c.setLongClickable(z);
        this.m = c.a(this.f3467c.getContext(), typedArray, l.O3);
        I(c.d(this.f3467c.getContext(), typedArray, l.N3));
        ColorStateList colorStateListA2 = c.a(this.f3467c.getContext(), typedArray, l.P3);
        this.l = colorStateListA2;
        if (colorStateListA2 == null) {
            this.l = ColorStateList.valueOf(d.b.a.a.o.a.c(this.f3467c, d.b.a.a.b.k));
        }
        G(c.a(this.f3467c.getContext(), typedArray, l.M3));
        Y();
        V();
        Z();
        this.f3467c.setBackgroundInternal(z(this.f3469e));
        Drawable drawableP = this.f3467c.isClickable() ? p() : this.f3470f;
        this.j = drawableP;
        this.f3467c.setForeground(z(drawableP));
    }

    void D(int i, int i2) {
        int i3;
        int i4;
        if (this.q != null) {
            int i5 = this.f3471g;
            int i6 = this.f3472h;
            int iCeil = (i - i5) - i6;
            int iCeil2 = (i2 - i5) - i6;
            if ((Build.VERSION.SDK_INT < 21) || this.f3467c.getUseCompatPadding()) {
                iCeil2 -= (int) Math.ceil(d() * 2.0f);
                iCeil -= (int) Math.ceil(c() * 2.0f);
            }
            int i7 = iCeil2;
            int i8 = this.f3471g;
            if (t.z(this.f3467c) == 1) {
                i4 = iCeil;
                i3 = i8;
            } else {
                i3 = iCeil;
                i4 = i8;
            }
            this.q.setLayerInset(2, i3, this.f3471g, i4, i7);
        }
    }

    void E(boolean z) {
        this.t = z;
    }

    void F(ColorStateList colorStateList) {
        this.f3469e.X(colorStateList);
    }

    void G(ColorStateList colorStateList) {
        h hVar = this.f3470f;
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(0);
        }
        hVar.X(colorStateList);
    }

    void H(boolean z) {
        this.u = z;
    }

    void I(Drawable drawable) {
        this.k = drawable;
        if (drawable != null) {
            Drawable drawableR = androidx.core.graphics.drawable.a.r(drawable.mutate());
            this.k = drawableR;
            androidx.core.graphics.drawable.a.o(drawableR, this.m);
        }
        if (this.q != null) {
            this.q.setDrawableByLayerId(f.p, f());
        }
    }

    void J(ColorStateList colorStateList) {
        this.m = colorStateList;
        Drawable drawable = this.k;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.o(drawable, colorStateList);
        }
    }

    void K(float f2) {
        N(this.n.w(f2));
        this.j.invalidateSelf();
        if (S() || R()) {
            U();
        }
        if (S()) {
            X();
        }
    }

    void L(float f2) {
        this.f3469e.Y(f2);
        h hVar = this.f3470f;
        if (hVar != null) {
            hVar.Y(f2);
        }
        h hVar2 = this.s;
        if (hVar2 != null) {
            hVar2.Y(f2);
        }
    }

    void M(ColorStateList colorStateList) {
        this.l = colorStateList;
        Y();
    }

    void N(m mVar) {
        this.n = mVar;
        this.f3469e.setShapeAppearanceModel(mVar);
        this.f3469e.c0(!r0.Q());
        h hVar = this.f3470f;
        if (hVar != null) {
            hVar.setShapeAppearanceModel(mVar);
        }
        h hVar2 = this.s;
        if (hVar2 != null) {
            hVar2.setShapeAppearanceModel(mVar);
        }
        h hVar3 = this.r;
        if (hVar3 != null) {
            hVar3.setShapeAppearanceModel(mVar);
        }
    }

    void O(ColorStateList colorStateList) {
        if (this.o == colorStateList) {
            return;
        }
        this.o = colorStateList;
        Z();
    }

    void P(int i) {
        if (i == this.i) {
            return;
        }
        this.i = i;
        Z();
    }

    void Q(int i, int i2, int i3, int i4) {
        this.f3468d.set(i, i2, i3, i4);
        U();
    }

    void T() {
        Drawable drawable = this.j;
        Drawable drawableP = this.f3467c.isClickable() ? p() : this.f3470f;
        this.j = drawableP;
        if (drawable != drawableP) {
            W(drawableP);
        }
    }

    void U() {
        int iA = (int) ((R() || S() ? a() : 0.0f) - r());
        MaterialCardView materialCardView = this.f3467c;
        Rect rect = this.f3468d;
        materialCardView.m(rect.left + iA, rect.top + iA, rect.right + iA, rect.bottom + iA);
    }

    void V() {
        this.f3469e.W(this.f3467c.getCardElevation());
    }

    void X() {
        if (!A()) {
            this.f3467c.setBackgroundInternal(z(this.f3469e));
        }
        this.f3467c.setForeground(z(this.j));
    }

    void Z() {
        this.f3470f.h0(this.i, this.o);
    }

    void j() {
        Drawable drawable = this.p;
        if (drawable != null) {
            Rect bounds = drawable.getBounds();
            int i = bounds.bottom;
            this.p.setBounds(bounds.left, bounds.top, bounds.right, i - 1);
            this.p.setBounds(bounds.left, bounds.top, bounds.right, i);
        }
    }

    h k() {
        return this.f3469e;
    }

    ColorStateList l() {
        return this.f3469e.x();
    }

    ColorStateList m() {
        return this.f3470f.x();
    }

    Drawable n() {
        return this.k;
    }

    ColorStateList o() {
        return this.m;
    }

    float q() {
        return this.f3469e.G();
    }

    float s() {
        return this.f3469e.y();
    }

    ColorStateList t() {
        return this.l;
    }

    m u() {
        return this.n;
    }

    int v() {
        ColorStateList colorStateList = this.o;
        if (colorStateList == null) {
            return -1;
        }
        return colorStateList.getDefaultColor();
    }

    ColorStateList w() {
        return this.o;
    }

    int x() {
        return this.i;
    }

    Rect y() {
        return this.f3468d;
    }
}
