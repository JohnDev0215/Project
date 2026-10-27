package defpackage;

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
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$id;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.card.MaterialCardView;
import defpackage.mk;

/* JADX INFO: compiled from: MaterialCardViewHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class wh {
    public static final int[] a = {R.attr.state_checked};
    public static final double b = Math.cos(Math.toRadians(45.0d));
    public final MaterialCardView c;
    public final hk e;
    public final hk f;
    public final int g;
    public final int h;
    public int i;
    public Drawable j;
    public Drawable k;
    public ColorStateList l;
    public ColorStateList m;
    public mk n;
    public ColorStateList o;
    public Drawable p;
    public LayerDrawable q;
    public hk r;
    public hk s;
    public boolean u;
    public final Rect d = new Rect();
    public boolean t = false;

    /* JADX INFO: compiled from: MaterialCardViewHelper.java */
    public class a extends InsetDrawable {
        public a(Drawable drawable, int i, int i2, int i3, int i4) {
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

    public wh(MaterialCardView materialCardView, AttributeSet attributeSet, int i, int i2) {
        this.c = materialCardView;
        hk hkVar = new hk(materialCardView.getContext(), attributeSet, i, i2);
        this.e = hkVar;
        hkVar.N(materialCardView.getContext());
        hkVar.d0(-12303292);
        mk.b bVarV = hkVar.D().v();
        TypedArray typedArrayObtainStyledAttributes = materialCardView.getContext().obtainStyledAttributes(attributeSet, R$styleable.CardView, i, R$style.CardView);
        int i3 = R$styleable.CardView_cardCornerRadius;
        if (typedArrayObtainStyledAttributes.hasValue(i3)) {
            bVarV.o(typedArrayObtainStyledAttributes.getDimension(i3, 0.0f));
        }
        this.f = new hk();
        N(bVarV.m());
        Resources resources = materialCardView.getResources();
        this.g = resources.getDimensionPixelSize(R$dimen.mtrl_card_checked_icon_margin);
        this.h = resources.getDimensionPixelSize(R$dimen.mtrl_card_checked_icon_size);
        typedArrayObtainStyledAttributes.recycle();
    }

    public boolean A() {
        return this.t;
    }

    public boolean B() {
        return this.u;
    }

    public void C(TypedArray typedArray) {
        ColorStateList colorStateListA = sj.a(this.c.getContext(), typedArray, R$styleable.MaterialCardView_strokeColor);
        this.o = colorStateListA;
        if (colorStateListA == null) {
            this.o = ColorStateList.valueOf(-1);
        }
        this.i = typedArray.getDimensionPixelSize(R$styleable.MaterialCardView_strokeWidth, 0);
        boolean z = typedArray.getBoolean(R$styleable.MaterialCardView_android_checkable, false);
        this.u = z;
        this.c.setLongClickable(z);
        this.m = sj.a(this.c.getContext(), typedArray, R$styleable.MaterialCardView_checkedIconTint);
        I(sj.d(this.c.getContext(), typedArray, R$styleable.MaterialCardView_checkedIcon));
        ColorStateList colorStateListA2 = sj.a(this.c.getContext(), typedArray, R$styleable.MaterialCardView_rippleColor);
        this.l = colorStateListA2;
        if (colorStateListA2 == null) {
            this.l = ColorStateList.valueOf(bi.c(this.c, R$attr.colorControlHighlight));
        }
        G(sj.a(this.c.getContext(), typedArray, R$styleable.MaterialCardView_cardForegroundColor));
        Y();
        V();
        Z();
        this.c.setBackgroundInternal(z(this.e));
        Drawable drawableP = this.c.isClickable() ? p() : this.f;
        this.j = drawableP;
        this.c.setForeground(z(drawableP));
    }

    public void D(int i, int i2) {
        int i3;
        int i4;
        if (this.q != null) {
            int i5 = this.g;
            int i6 = this.h;
            int iCeil = (i - i5) - i6;
            int iCeil2 = (i2 - i5) - i6;
            if ((Build.VERSION.SDK_INT < 21) || this.c.getUseCompatPadding()) {
                iCeil2 -= (int) Math.ceil(d() * 2.0f);
                iCeil -= (int) Math.ceil(c() * 2.0f);
            }
            int i7 = iCeil2;
            int i8 = this.g;
            if (t9.z(this.c) == 1) {
                i4 = iCeil;
                i3 = i8;
            } else {
                i3 = iCeil;
                i4 = i8;
            }
            this.q.setLayerInset(2, i3, this.g, i4, i7);
        }
    }

    public void E(boolean z) {
        this.t = z;
    }

    public void F(ColorStateList colorStateList) {
        this.e.X(colorStateList);
    }

    public void G(ColorStateList colorStateList) {
        hk hkVar = this.f;
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(0);
        }
        hkVar.X(colorStateList);
    }

    public void H(boolean z) {
        this.u = z;
    }

    public void I(Drawable drawable) {
        this.k = drawable;
        if (drawable != null) {
            Drawable drawableR = y7.r(drawable.mutate());
            this.k = drawableR;
            y7.o(drawableR, this.m);
        }
        if (this.q != null) {
            this.q.setDrawableByLayerId(R$id.mtrl_card_checked_layer_id, f());
        }
    }

    public void J(ColorStateList colorStateList) {
        this.m = colorStateList;
        Drawable drawable = this.k;
        if (drawable != null) {
            y7.o(drawable, colorStateList);
        }
    }

    public void K(float f) {
        N(this.n.w(f));
        this.j.invalidateSelf();
        if (S() || R()) {
            U();
        }
        if (S()) {
            X();
        }
    }

    public void L(float f) {
        this.e.Y(f);
        hk hkVar = this.f;
        if (hkVar != null) {
            hkVar.Y(f);
        }
        hk hkVar2 = this.s;
        if (hkVar2 != null) {
            hkVar2.Y(f);
        }
    }

    public void M(ColorStateList colorStateList) {
        this.l = colorStateList;
        Y();
    }

    public void N(mk mkVar) {
        this.n = mkVar;
        this.e.setShapeAppearanceModel(mkVar);
        this.e.c0(!r0.Q());
        hk hkVar = this.f;
        if (hkVar != null) {
            hkVar.setShapeAppearanceModel(mkVar);
        }
        hk hkVar2 = this.s;
        if (hkVar2 != null) {
            hkVar2.setShapeAppearanceModel(mkVar);
        }
        hk hkVar3 = this.r;
        if (hkVar3 != null) {
            hkVar3.setShapeAppearanceModel(mkVar);
        }
    }

    public void O(ColorStateList colorStateList) {
        if (this.o == colorStateList) {
            return;
        }
        this.o = colorStateList;
        Z();
    }

    public void P(int i) {
        if (i == this.i) {
            return;
        }
        this.i = i;
        Z();
    }

    public void Q(int i, int i2, int i3, int i4) {
        this.d.set(i, i2, i3, i4);
        U();
    }

    public final boolean R() {
        return this.c.getPreventCornerOverlap() && !e();
    }

    public final boolean S() {
        return this.c.getPreventCornerOverlap() && e() && this.c.getUseCompatPadding();
    }

    public void T() {
        Drawable drawable = this.j;
        Drawable drawableP = this.c.isClickable() ? p() : this.f;
        this.j = drawableP;
        if (drawable != drawableP) {
            W(drawableP);
        }
    }

    public void U() {
        int iA = (int) ((R() || S() ? a() : 0.0f) - r());
        MaterialCardView materialCardView = this.c;
        Rect rect = this.d;
        materialCardView.m(rect.left + iA, rect.top + iA, rect.right + iA, rect.bottom + iA);
    }

    public void V() {
        this.e.W(this.c.getCardElevation());
    }

    public final void W(Drawable drawable) {
        if (Build.VERSION.SDK_INT < 23 || !(this.c.getForeground() instanceof InsetDrawable)) {
            this.c.setForeground(z(drawable));
        } else {
            ((InsetDrawable) this.c.getForeground()).setDrawable(drawable);
        }
    }

    public void X() {
        if (!A()) {
            this.c.setBackgroundInternal(z(this.e));
        }
        this.c.setForeground(z(this.j));
    }

    public final void Y() {
        Drawable drawable;
        if (xj.a && (drawable = this.p) != null) {
            ((RippleDrawable) drawable).setColor(this.l);
            return;
        }
        hk hkVar = this.r;
        if (hkVar != null) {
            hkVar.X(this.l);
        }
    }

    public void Z() {
        this.f.h0(this.i, this.o);
    }

    public final float a() {
        return Math.max(Math.max(b(this.n.q(), this.e.G()), b(this.n.s(), this.e.H())), Math.max(b(this.n.k(), this.e.t()), b(this.n.i(), this.e.s())));
    }

    public final float b(dk dkVar, float f) {
        if (dkVar instanceof lk) {
            return (float) ((1.0d - b) * ((double) f));
        }
        if (dkVar instanceof ek) {
            return f / 2.0f;
        }
        return 0.0f;
    }

    public final float c() {
        return this.c.getMaxCardElevation() + (S() ? a() : 0.0f);
    }

    public final float d() {
        return (this.c.getMaxCardElevation() * 1.5f) + (S() ? a() : 0.0f);
    }

    public final boolean e() {
        return Build.VERSION.SDK_INT >= 21 && this.e.Q();
    }

    public final Drawable f() {
        StateListDrawable stateListDrawable = new StateListDrawable();
        Drawable drawable = this.k;
        if (drawable != null) {
            stateListDrawable.addState(a, drawable);
        }
        return stateListDrawable;
    }

    public final Drawable g() {
        StateListDrawable stateListDrawable = new StateListDrawable();
        hk hkVarI = i();
        this.r = hkVarI;
        hkVarI.X(this.l);
        stateListDrawable.addState(new int[]{R.attr.state_pressed}, this.r);
        return stateListDrawable;
    }

    public final Drawable h() {
        if (!xj.a) {
            return g();
        }
        hk hkVarI = i();
        this.s = hkVarI;
        return new RippleDrawable(this.l, null, hkVarI);
    }

    public final hk i() {
        return new hk(this.n);
    }

    public void j() {
        Drawable drawable = this.p;
        if (drawable != null) {
            Rect bounds = drawable.getBounds();
            int i = bounds.bottom;
            this.p.setBounds(bounds.left, bounds.top, bounds.right, i - 1);
            this.p.setBounds(bounds.left, bounds.top, bounds.right, i);
        }
    }

    public hk k() {
        return this.e;
    }

    public ColorStateList l() {
        return this.e.x();
    }

    public ColorStateList m() {
        return this.f.x();
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
            LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{this.p, this.f, f()});
            this.q = layerDrawable;
            layerDrawable.setId(2, R$id.mtrl_card_checked_layer_id);
        }
        return this.q;
    }

    public float q() {
        return this.e.G();
    }

    public final float r() {
        if (!this.c.getPreventCornerOverlap()) {
            return 0.0f;
        }
        if (Build.VERSION.SDK_INT < 21 || this.c.getUseCompatPadding()) {
            return (float) ((1.0d - b) * ((double) this.c.getCardViewRadius()));
        }
        return 0.0f;
    }

    public float s() {
        return this.e.y();
    }

    public ColorStateList t() {
        return this.l;
    }

    public mk u() {
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
        return this.i;
    }

    public Rect y() {
        return this.d;
    }

    public final Drawable z(Drawable drawable) {
        int iCeil;
        int i;
        if ((Build.VERSION.SDK_INT < 21) || this.c.getUseCompatPadding()) {
            int iCeil2 = (int) Math.ceil(d());
            iCeil = (int) Math.ceil(c());
            i = iCeil2;
        } else {
            iCeil = 0;
            i = 0;
        }
        return new a(drawable, iCeil, i, iCeil, i);
    }
}
