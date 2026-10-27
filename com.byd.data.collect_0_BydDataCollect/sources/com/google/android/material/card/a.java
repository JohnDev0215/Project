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
import d.b.a.a.u.c;
import d.b.a.a.v.b;
import d.b.a.a.x.e;
import d.b.a.a.x.h;
import d.b.a.a.x.m;

/* JADX INFO: compiled from: MaterialCardViewHelper.java */
/* JADX INFO: loaded from: classes.dex */
class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int[] f3856a = {R.attr.state_checked};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final double f3857b = Math.cos(Math.toRadians(45.0d));

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final MaterialCardView f3858c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final h f3860e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final h f3861f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final int f3862g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final int f3863h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f3864i;
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
    private final Rect f3859d = new Rect();
    private boolean t = false;

    /* JADX INFO: renamed from: com.google.android.material.card.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: MaterialCardViewHelper.java */
    class C0071a extends InsetDrawable {
        C0071a(Drawable drawable, int i2, int i3, int i4, int i5) {
            super(drawable, i2, i3, i4, i5);
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

    public a(MaterialCardView materialCardView, AttributeSet attributeSet, int i2, int i3) {
        this.f3858c = materialCardView;
        h hVar = new h(materialCardView.getContext(), attributeSet, i2, i3);
        this.f3860e = hVar;
        hVar.N(materialCardView.getContext());
        hVar.d0(-12303292);
        m.b bVarV = hVar.D().v();
        TypedArray typedArrayObtainStyledAttributes = materialCardView.getContext().obtainStyledAttributes(attributeSet, l.CardView, i2, k.CardView);
        int i4 = l.CardView_cardCornerRadius;
        if (typedArrayObtainStyledAttributes.hasValue(i4)) {
            bVarV.o(typedArrayObtainStyledAttributes.getDimension(i4, 0.0f));
        }
        this.f3861f = new h();
        N(bVarV.m());
        Resources resources = materialCardView.getResources();
        this.f3862g = resources.getDimensionPixelSize(d.mtrl_card_checked_icon_margin);
        this.f3863h = resources.getDimensionPixelSize(d.mtrl_card_checked_icon_size);
        typedArrayObtainStyledAttributes.recycle();
    }

    private boolean R() {
        return this.f3858c.getPreventCornerOverlap() && !e();
    }

    private boolean S() {
        return this.f3858c.getPreventCornerOverlap() && e() && this.f3858c.getUseCompatPadding();
    }

    private void W(Drawable drawable) {
        if (Build.VERSION.SDK_INT < 23 || !(this.f3858c.getForeground() instanceof InsetDrawable)) {
            this.f3858c.setForeground(z(drawable));
        } else {
            ((InsetDrawable) this.f3858c.getForeground()).setDrawable(drawable);
        }
    }

    private void Y() {
        Drawable drawable;
        if (b.f4729a && (drawable = this.p) != null) {
            ((RippleDrawable) drawable).setColor(this.l);
            return;
        }
        h hVar = this.r;
        if (hVar != null) {
            hVar.X(this.l);
        }
    }

    private float a() {
        return Math.max(Math.max(b(this.n.q(), this.f3860e.G()), b(this.n.s(), this.f3860e.H())), Math.max(b(this.n.k(), this.f3860e.t()), b(this.n.i(), this.f3860e.s())));
    }

    private float b(d.b.a.a.x.d dVar, float f2) {
        if (dVar instanceof d.b.a.a.x.l) {
            return (float) ((1.0d - f3857b) * ((double) f2));
        }
        if (dVar instanceof e) {
            return f2 / 2.0f;
        }
        return 0.0f;
    }

    private float c() {
        return this.f3858c.getMaxCardElevation() + (S() ? a() : 0.0f);
    }

    private float d() {
        return (this.f3858c.getMaxCardElevation() * 1.5f) + (S() ? a() : 0.0f);
    }

    private boolean e() {
        return Build.VERSION.SDK_INT >= 21 && this.f3860e.Q();
    }

    private Drawable f() {
        StateListDrawable stateListDrawable = new StateListDrawable();
        Drawable drawable = this.k;
        if (drawable != null) {
            stateListDrawable.addState(f3856a, drawable);
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
        if (!b.f4729a) {
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
            LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{this.p, this.f3861f, f()});
            this.q = layerDrawable;
            layerDrawable.setId(2, f.mtrl_card_checked_layer_id);
        }
        return this.q;
    }

    private float r() {
        if (!this.f3858c.getPreventCornerOverlap()) {
            return 0.0f;
        }
        if (Build.VERSION.SDK_INT < 21 || this.f3858c.getUseCompatPadding()) {
            return (float) ((1.0d - f3857b) * ((double) this.f3858c.getCardViewRadius()));
        }
        return 0.0f;
    }

    private Drawable z(Drawable drawable) {
        int iCeil;
        int i2;
        if ((Build.VERSION.SDK_INT < 21) || this.f3858c.getUseCompatPadding()) {
            int iCeil2 = (int) Math.ceil(d());
            iCeil = (int) Math.ceil(c());
            i2 = iCeil2;
        } else {
            iCeil = 0;
            i2 = 0;
        }
        return new C0071a(drawable, iCeil, i2, iCeil, i2);
    }

    boolean A() {
        return this.t;
    }

    boolean B() {
        return this.u;
    }

    void C(TypedArray typedArray) {
        ColorStateList colorStateListA = c.a(this.f3858c.getContext(), typedArray, l.MaterialCardView_strokeColor);
        this.o = colorStateListA;
        if (colorStateListA == null) {
            this.o = ColorStateList.valueOf(-1);
        }
        this.f3864i = typedArray.getDimensionPixelSize(l.MaterialCardView_strokeWidth, 0);
        boolean z = typedArray.getBoolean(l.MaterialCardView_android_checkable, false);
        this.u = z;
        this.f3858c.setLongClickable(z);
        this.m = c.a(this.f3858c.getContext(), typedArray, l.MaterialCardView_checkedIconTint);
        I(c.d(this.f3858c.getContext(), typedArray, l.MaterialCardView_checkedIcon));
        ColorStateList colorStateListA2 = c.a(this.f3858c.getContext(), typedArray, l.MaterialCardView_rippleColor);
        this.l = colorStateListA2;
        if (colorStateListA2 == null) {
            this.l = ColorStateList.valueOf(d.b.a.a.o.a.c(this.f3858c, d.b.a.a.b.colorControlHighlight));
        }
        G(c.a(this.f3858c.getContext(), typedArray, l.MaterialCardView_cardForegroundColor));
        Y();
        V();
        Z();
        this.f3858c.setBackgroundInternal(z(this.f3860e));
        Drawable drawableP = this.f3858c.isClickable() ? p() : this.f3861f;
        this.j = drawableP;
        this.f3858c.setForeground(z(drawableP));
    }

    void D(int i2, int i3) {
        int i4;
        int i5;
        if (this.q != null) {
            int i6 = this.f3862g;
            int i7 = this.f3863h;
            int iCeil = (i2 - i6) - i7;
            int iCeil2 = (i3 - i6) - i7;
            if ((Build.VERSION.SDK_INT < 21) || this.f3858c.getUseCompatPadding()) {
                iCeil2 -= (int) Math.ceil(d() * 2.0f);
                iCeil -= (int) Math.ceil(c() * 2.0f);
            }
            int i8 = iCeil2;
            int i9 = this.f3862g;
            if (t.z(this.f3858c) == 1) {
                i5 = iCeil;
                i4 = i9;
            } else {
                i4 = iCeil;
                i5 = i9;
            }
            this.q.setLayerInset(2, i4, this.f3862g, i5, i8);
        }
    }

    void E(boolean z) {
        this.t = z;
    }

    void F(ColorStateList colorStateList) {
        this.f3860e.X(colorStateList);
    }

    void G(ColorStateList colorStateList) {
        h hVar = this.f3861f;
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
            this.q.setDrawableByLayerId(f.mtrl_card_checked_layer_id, f());
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
        this.f3860e.Y(f2);
        h hVar = this.f3861f;
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
        this.f3860e.setShapeAppearanceModel(mVar);
        this.f3860e.c0(!r0.Q());
        h hVar = this.f3861f;
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

    void P(int i2) {
        if (i2 == this.f3864i) {
            return;
        }
        this.f3864i = i2;
        Z();
    }

    void Q(int i2, int i3, int i4, int i5) {
        this.f3859d.set(i2, i3, i4, i5);
        U();
    }

    void T() {
        Drawable drawable = this.j;
        Drawable drawableP = this.f3858c.isClickable() ? p() : this.f3861f;
        this.j = drawableP;
        if (drawable != drawableP) {
            W(drawableP);
        }
    }

    void U() {
        int iA = (int) ((R() || S() ? a() : 0.0f) - r());
        MaterialCardView materialCardView = this.f3858c;
        Rect rect = this.f3859d;
        materialCardView.m(rect.left + iA, rect.top + iA, rect.right + iA, rect.bottom + iA);
    }

    void V() {
        this.f3860e.W(this.f3858c.getCardElevation());
    }

    void X() {
        if (!A()) {
            this.f3858c.setBackgroundInternal(z(this.f3860e));
        }
        this.f3858c.setForeground(z(this.j));
    }

    void Z() {
        this.f3861f.h0(this.f3864i, this.o);
    }

    void j() {
        Drawable drawable = this.p;
        if (drawable != null) {
            Rect bounds = drawable.getBounds();
            int i2 = bounds.bottom;
            this.p.setBounds(bounds.left, bounds.top, bounds.right, i2 - 1);
            this.p.setBounds(bounds.left, bounds.top, bounds.right, i2);
        }
    }

    h k() {
        return this.f3860e;
    }

    ColorStateList l() {
        return this.f3860e.x();
    }

    ColorStateList m() {
        return this.f3861f.x();
    }

    Drawable n() {
        return this.k;
    }

    ColorStateList o() {
        return this.m;
    }

    float q() {
        return this.f3860e.G();
    }

    float s() {
        return this.f3860e.y();
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
        return this.f3864i;
    }

    Rect y() {
        return this.f3859d;
    }
}
