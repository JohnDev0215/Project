package c.b.a.a.t;

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
import b.h.m.t;
import c.b.a.a.b;
import c.b.a.a.d;
import c.b.a.a.f;
import c.b.a.a.f0.c;
import c.b.a.a.i0.e;
import c.b.a.a.i0.h;
import c.b.a.a.i0.m;
import c.b.a.a.k;
import c.b.a.a.l;
import com.google.android.material.card.MaterialCardView;

/* JADX INFO: compiled from: MaterialCardViewHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f4073a = {R.attr.state_checked};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final double f4074b = Math.cos(Math.toRadians(45.0d));

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final MaterialCardView f4075c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final h f4077e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final h f4078f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f4079g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f4080h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f4081i;
    public Drawable j;
    public Drawable k;
    public ColorStateList l;
    public ColorStateList m;
    public m n;
    public ColorStateList o;
    public Drawable p;
    public LayerDrawable q;
    public h r;
    public h s;
    public boolean u;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Rect f4076d = new Rect();
    public boolean t = false;

    /* JADX INFO: renamed from: c.b.a.a.t.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: MaterialCardViewHelper.java */
    public class C0090a extends InsetDrawable {
        public C0090a(Drawable drawable, int i2, int i3, int i4, int i5) {
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
        this.f4075c = materialCardView;
        h hVar = new h(materialCardView.getContext(), attributeSet, i2, i3);
        this.f4077e = hVar;
        hVar.N(materialCardView.getContext());
        hVar.d0(-12303292);
        m.b bVarV = hVar.D().v();
        TypedArray typedArrayObtainStyledAttributes = materialCardView.getContext().obtainStyledAttributes(attributeSet, l.CardView, i2, k.CardView);
        int i4 = l.CardView_cardCornerRadius;
        if (typedArrayObtainStyledAttributes.hasValue(i4)) {
            bVarV.o(typedArrayObtainStyledAttributes.getDimension(i4, 0.0f));
        }
        this.f4078f = new h();
        N(bVarV.m());
        Resources resources = materialCardView.getResources();
        this.f4079g = resources.getDimensionPixelSize(d.mtrl_card_checked_icon_margin);
        this.f4080h = resources.getDimensionPixelSize(d.mtrl_card_checked_icon_size);
        typedArrayObtainStyledAttributes.recycle();
    }

    public boolean A() {
        return this.t;
    }

    public boolean B() {
        return this.u;
    }

    public void C(TypedArray typedArray) {
        ColorStateList colorStateListA = c.a(this.f4075c.getContext(), typedArray, l.MaterialCardView_strokeColor);
        this.o = colorStateListA;
        if (colorStateListA == null) {
            this.o = ColorStateList.valueOf(-1);
        }
        this.f4081i = typedArray.getDimensionPixelSize(l.MaterialCardView_strokeWidth, 0);
        boolean z = typedArray.getBoolean(l.MaterialCardView_android_checkable, false);
        this.u = z;
        this.f4075c.setLongClickable(z);
        this.m = c.a(this.f4075c.getContext(), typedArray, l.MaterialCardView_checkedIconTint);
        I(c.d(this.f4075c.getContext(), typedArray, l.MaterialCardView_checkedIcon));
        ColorStateList colorStateListA2 = c.a(this.f4075c.getContext(), typedArray, l.MaterialCardView_rippleColor);
        this.l = colorStateListA2;
        if (colorStateListA2 == null) {
            this.l = ColorStateList.valueOf(c.b.a.a.w.a.c(this.f4075c, b.colorControlHighlight));
        }
        G(c.a(this.f4075c.getContext(), typedArray, l.MaterialCardView_cardForegroundColor));
        Y();
        V();
        Z();
        this.f4075c.setBackgroundInternal(z(this.f4077e));
        Drawable drawableP = this.f4075c.isClickable() ? p() : this.f4078f;
        this.j = drawableP;
        this.f4075c.setForeground(z(drawableP));
    }

    public void D(int i2, int i3) {
        int i4;
        int i5;
        if (this.q != null) {
            int i6 = this.f4079g;
            int i7 = this.f4080h;
            int iCeil = (i2 - i6) - i7;
            int iCeil2 = (i3 - i6) - i7;
            if ((Build.VERSION.SDK_INT < 21) || this.f4075c.getUseCompatPadding()) {
                iCeil2 -= (int) Math.ceil(d() * 2.0f);
                iCeil -= (int) Math.ceil(c() * 2.0f);
            }
            int i8 = iCeil2;
            int i9 = this.f4079g;
            if (t.z(this.f4075c) == 1) {
                i5 = iCeil;
                i4 = i9;
            } else {
                i4 = iCeil;
                i5 = i9;
            }
            this.q.setLayerInset(2, i4, this.f4079g, i5, i8);
        }
    }

    public void E(boolean z) {
        this.t = z;
    }

    public void F(ColorStateList colorStateList) {
        this.f4077e.X(colorStateList);
    }

    public void G(ColorStateList colorStateList) {
        h hVar = this.f4078f;
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(0);
        }
        hVar.X(colorStateList);
    }

    public void H(boolean z) {
        this.u = z;
    }

    public void I(Drawable drawable) {
        this.k = drawable;
        if (drawable != null) {
            Drawable drawableR = b.h.f.l.a.r(drawable.mutate());
            this.k = drawableR;
            b.h.f.l.a.o(drawableR, this.m);
        }
        if (this.q != null) {
            this.q.setDrawableByLayerId(f.mtrl_card_checked_layer_id, f());
        }
    }

    public void J(ColorStateList colorStateList) {
        this.m = colorStateList;
        Drawable drawable = this.k;
        if (drawable != null) {
            b.h.f.l.a.o(drawable, colorStateList);
        }
    }

    public void K(float f2) {
        N(this.n.w(f2));
        this.j.invalidateSelf();
        if (S() || R()) {
            U();
        }
        if (S()) {
            X();
        }
    }

    public void L(float f2) {
        this.f4077e.Y(f2);
        h hVar = this.f4078f;
        if (hVar != null) {
            hVar.Y(f2);
        }
        h hVar2 = this.s;
        if (hVar2 != null) {
            hVar2.Y(f2);
        }
    }

    public void M(ColorStateList colorStateList) {
        this.l = colorStateList;
        Y();
    }

    public void N(m mVar) {
        this.n = mVar;
        this.f4077e.setShapeAppearanceModel(mVar);
        this.f4077e.c0(!r0.Q());
        h hVar = this.f4078f;
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

    public void O(ColorStateList colorStateList) {
        if (this.o == colorStateList) {
            return;
        }
        this.o = colorStateList;
        Z();
    }

    public void P(int i2) {
        if (i2 == this.f4081i) {
            return;
        }
        this.f4081i = i2;
        Z();
    }

    public void Q(int i2, int i3, int i4, int i5) {
        this.f4076d.set(i2, i3, i4, i5);
        U();
    }

    public final boolean R() {
        return this.f4075c.getPreventCornerOverlap() && !e();
    }

    public final boolean S() {
        return this.f4075c.getPreventCornerOverlap() && e() && this.f4075c.getUseCompatPadding();
    }

    public void T() {
        Drawable drawable = this.j;
        Drawable drawableP = this.f4075c.isClickable() ? p() : this.f4078f;
        this.j = drawableP;
        if (drawable != drawableP) {
            W(drawableP);
        }
    }

    public void U() {
        int iA = (int) ((R() || S() ? a() : 0.0f) - r());
        MaterialCardView materialCardView = this.f4075c;
        Rect rect = this.f4076d;
        materialCardView.m(rect.left + iA, rect.top + iA, rect.right + iA, rect.bottom + iA);
    }

    public void V() {
        this.f4077e.W(this.f4075c.getCardElevation());
    }

    public final void W(Drawable drawable) {
        if (Build.VERSION.SDK_INT < 23 || !(this.f4075c.getForeground() instanceof InsetDrawable)) {
            this.f4075c.setForeground(z(drawable));
        } else {
            ((InsetDrawable) this.f4075c.getForeground()).setDrawable(drawable);
        }
    }

    public void X() {
        if (!A()) {
            this.f4075c.setBackgroundInternal(z(this.f4077e));
        }
        this.f4075c.setForeground(z(this.j));
    }

    public final void Y() {
        Drawable drawable;
        if (c.b.a.a.g0.b.f3834a && (drawable = this.p) != null) {
            ((RippleDrawable) drawable).setColor(this.l);
            return;
        }
        h hVar = this.r;
        if (hVar != null) {
            hVar.X(this.l);
        }
    }

    public void Z() {
        this.f4078f.h0(this.f4081i, this.o);
    }

    public final float a() {
        return Math.max(Math.max(b(this.n.q(), this.f4077e.G()), b(this.n.s(), this.f4077e.H())), Math.max(b(this.n.k(), this.f4077e.t()), b(this.n.i(), this.f4077e.s())));
    }

    public final float b(c.b.a.a.i0.d dVar, float f2) {
        if (dVar instanceof c.b.a.a.i0.l) {
            return (float) ((1.0d - f4074b) * ((double) f2));
        }
        if (dVar instanceof e) {
            return f2 / 2.0f;
        }
        return 0.0f;
    }

    public final float c() {
        return this.f4075c.getMaxCardElevation() + (S() ? a() : 0.0f);
    }

    public final float d() {
        return (this.f4075c.getMaxCardElevation() * 1.5f) + (S() ? a() : 0.0f);
    }

    public final boolean e() {
        return Build.VERSION.SDK_INT >= 21 && this.f4077e.Q();
    }

    public final Drawable f() {
        StateListDrawable stateListDrawable = new StateListDrawable();
        Drawable drawable = this.k;
        if (drawable != null) {
            stateListDrawable.addState(f4073a, drawable);
        }
        return stateListDrawable;
    }

    public final Drawable g() {
        StateListDrawable stateListDrawable = new StateListDrawable();
        h hVarI = i();
        this.r = hVarI;
        hVarI.X(this.l);
        stateListDrawable.addState(new int[]{R.attr.state_pressed}, this.r);
        return stateListDrawable;
    }

    public final Drawable h() {
        if (!c.b.a.a.g0.b.f3834a) {
            return g();
        }
        h hVarI = i();
        this.s = hVarI;
        return new RippleDrawable(this.l, null, hVarI);
    }

    public final h i() {
        return new h(this.n);
    }

    public void j() {
        Drawable drawable = this.p;
        if (drawable != null) {
            Rect bounds = drawable.getBounds();
            int i2 = bounds.bottom;
            this.p.setBounds(bounds.left, bounds.top, bounds.right, i2 - 1);
            this.p.setBounds(bounds.left, bounds.top, bounds.right, i2);
        }
    }

    public h k() {
        return this.f4077e;
    }

    public ColorStateList l() {
        return this.f4077e.x();
    }

    public ColorStateList m() {
        return this.f4078f.x();
    }

    public Drawable n() {
        return this.k;
    }

    public ColorStateList o() {
        return this.m;
    }

    public final Drawable p() {
        if (this.p == null) {
            this.p = h();
        }
        if (this.q == null) {
            LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{this.p, this.f4078f, f()});
            this.q = layerDrawable;
            layerDrawable.setId(2, f.mtrl_card_checked_layer_id);
        }
        return this.q;
    }

    public float q() {
        return this.f4077e.G();
    }

    public final float r() {
        if (!this.f4075c.getPreventCornerOverlap()) {
            return 0.0f;
        }
        if (Build.VERSION.SDK_INT < 21 || this.f4075c.getUseCompatPadding()) {
            return (float) ((1.0d - f4074b) * ((double) this.f4075c.getCardViewRadius()));
        }
        return 0.0f;
    }

    public float s() {
        return this.f4077e.y();
    }

    public ColorStateList t() {
        return this.l;
    }

    public m u() {
        return this.n;
    }

    public int v() {
        ColorStateList colorStateList = this.o;
        if (colorStateList == null) {
            return -1;
        }
        return colorStateList.getDefaultColor();
    }

    public ColorStateList w() {
        return this.o;
    }

    public int x() {
        return this.f4081i;
    }

    public Rect y() {
        return this.f4076d;
    }

    public final Drawable z(Drawable drawable) {
        int iCeil;
        int i2;
        if ((Build.VERSION.SDK_INT < 21) || this.f4075c.getUseCompatPadding()) {
            int iCeil2 = (int) Math.ceil(d());
            iCeil = (int) Math.ceil(c());
            i2 = iCeil2;
        } else {
            iCeil = 0;
            i2 = 0;
        }
        return new C0090a(drawable, iCeil, i2, iCeil, i2);
    }
}
