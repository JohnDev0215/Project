package q5;

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
import b6.c;
import c6.b;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$id;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.card.MaterialCardView;
import e6.d;
import e6.e;
import e6.h;
import e6.l;
import e6.m;
import o0.t;

/* JADX INFO: compiled from: MaterialCardViewHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f7429a = {R.attr.state_checked};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final double f7430b = Math.cos(Math.toRadians(45.0d));

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final MaterialCardView f7431c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final h f7433e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final h f7434f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f7435g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f7436h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f7437i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Drawable f7438j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Drawable f7439k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public ColorStateList f7440l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ColorStateList f7441m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public m f7442n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public ColorStateList f7443o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Drawable f7444p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public LayerDrawable f7445q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public h f7446r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public h f7447s;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f7449u;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Rect f7432d = new Rect();

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f7448t = false;

    /* JADX INFO: renamed from: q5.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: MaterialCardViewHelper.java */
    public class C0109a extends InsetDrawable {
        public C0109a(Drawable drawable, int i8, int i9, int i10, int i11) {
            super(drawable, i8, i9, i10, i11);
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

    public a(MaterialCardView materialCardView, AttributeSet attributeSet, int i8, int i9) {
        this.f7431c = materialCardView;
        h hVar = new h(materialCardView.getContext(), attributeSet, i8, i9);
        this.f7433e = hVar;
        hVar.N(materialCardView.getContext());
        hVar.d0(-12303292);
        m.b bVarV = hVar.D().v();
        TypedArray typedArrayObtainStyledAttributes = materialCardView.getContext().obtainStyledAttributes(attributeSet, R$styleable.CardView, i8, R$style.CardView);
        int i10 = R$styleable.CardView_cardCornerRadius;
        if (typedArrayObtainStyledAttributes.hasValue(i10)) {
            bVarV.o(typedArrayObtainStyledAttributes.getDimension(i10, 0.0f));
        }
        this.f7434f = new h();
        N(bVarV.m());
        Resources resources = materialCardView.getResources();
        this.f7435g = resources.getDimensionPixelSize(R$dimen.mtrl_card_checked_icon_margin);
        this.f7436h = resources.getDimensionPixelSize(R$dimen.mtrl_card_checked_icon_size);
        typedArrayObtainStyledAttributes.recycle();
    }

    public boolean A() {
        return this.f7448t;
    }

    public boolean B() {
        return this.f7449u;
    }

    public void C(TypedArray typedArray) {
        ColorStateList colorStateListA = c.a(this.f7431c.getContext(), typedArray, R$styleable.MaterialCardView_strokeColor);
        this.f7443o = colorStateListA;
        if (colorStateListA == null) {
            this.f7443o = ColorStateList.valueOf(-1);
        }
        this.f7437i = typedArray.getDimensionPixelSize(R$styleable.MaterialCardView_strokeWidth, 0);
        boolean z7 = typedArray.getBoolean(R$styleable.MaterialCardView_android_checkable, false);
        this.f7449u = z7;
        this.f7431c.setLongClickable(z7);
        this.f7441m = c.a(this.f7431c.getContext(), typedArray, R$styleable.MaterialCardView_checkedIconTint);
        I(c.d(this.f7431c.getContext(), typedArray, R$styleable.MaterialCardView_checkedIcon));
        ColorStateList colorStateListA2 = c.a(this.f7431c.getContext(), typedArray, R$styleable.MaterialCardView_rippleColor);
        this.f7440l = colorStateListA2;
        if (colorStateListA2 == null) {
            this.f7440l = ColorStateList.valueOf(t5.a.c(this.f7431c, R$attr.colorControlHighlight));
        }
        G(c.a(this.f7431c.getContext(), typedArray, R$styleable.MaterialCardView_cardForegroundColor));
        Y();
        V();
        Z();
        this.f7431c.setBackgroundInternal(z(this.f7433e));
        Drawable drawableP = this.f7431c.isClickable() ? p() : this.f7434f;
        this.f7438j = drawableP;
        this.f7431c.setForeground(z(drawableP));
    }

    public void D(int i8, int i9) {
        int i10;
        int i11;
        if (this.f7445q != null) {
            int i12 = this.f7435g;
            int i13 = this.f7436h;
            int iCeil = (i8 - i12) - i13;
            int iCeil2 = (i9 - i12) - i13;
            if ((Build.VERSION.SDK_INT < 21) || this.f7431c.getUseCompatPadding()) {
                iCeil2 -= (int) Math.ceil(d() * 2.0f);
                iCeil -= (int) Math.ceil(c() * 2.0f);
            }
            int i14 = iCeil2;
            int i15 = this.f7435g;
            if (t.A(this.f7431c) == 1) {
                i11 = iCeil;
                i10 = i15;
            } else {
                i10 = iCeil;
                i11 = i15;
            }
            this.f7445q.setLayerInset(2, i10, this.f7435g, i11, i14);
        }
    }

    public void E(boolean z7) {
        this.f7448t = z7;
    }

    public void F(ColorStateList colorStateList) {
        this.f7433e.X(colorStateList);
    }

    public void G(ColorStateList colorStateList) {
        h hVar = this.f7434f;
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(0);
        }
        hVar.X(colorStateList);
    }

    public void H(boolean z7) {
        this.f7449u = z7;
    }

    public void I(Drawable drawable) {
        this.f7439k = drawable;
        if (drawable != null) {
            Drawable drawableR = h0.a.r(drawable.mutate());
            this.f7439k = drawableR;
            h0.a.o(drawableR, this.f7441m);
        }
        if (this.f7445q != null) {
            this.f7445q.setDrawableByLayerId(R$id.mtrl_card_checked_layer_id, f());
        }
    }

    public void J(ColorStateList colorStateList) {
        this.f7441m = colorStateList;
        Drawable drawable = this.f7439k;
        if (drawable != null) {
            h0.a.o(drawable, colorStateList);
        }
    }

    public void K(float f8) {
        N(this.f7442n.w(f8));
        this.f7438j.invalidateSelf();
        if (S() || R()) {
            U();
        }
        if (S()) {
            X();
        }
    }

    public void L(float f8) {
        this.f7433e.Y(f8);
        h hVar = this.f7434f;
        if (hVar != null) {
            hVar.Y(f8);
        }
        h hVar2 = this.f7447s;
        if (hVar2 != null) {
            hVar2.Y(f8);
        }
    }

    public void M(ColorStateList colorStateList) {
        this.f7440l = colorStateList;
        Y();
    }

    public void N(m mVar) {
        this.f7442n = mVar;
        this.f7433e.setShapeAppearanceModel(mVar);
        this.f7433e.c0(!r0.Q());
        h hVar = this.f7434f;
        if (hVar != null) {
            hVar.setShapeAppearanceModel(mVar);
        }
        h hVar2 = this.f7447s;
        if (hVar2 != null) {
            hVar2.setShapeAppearanceModel(mVar);
        }
        h hVar3 = this.f7446r;
        if (hVar3 != null) {
            hVar3.setShapeAppearanceModel(mVar);
        }
    }

    public void O(ColorStateList colorStateList) {
        if (this.f7443o == colorStateList) {
            return;
        }
        this.f7443o = colorStateList;
        Z();
    }

    public void P(int i8) {
        if (i8 == this.f7437i) {
            return;
        }
        this.f7437i = i8;
        Z();
    }

    public void Q(int i8, int i9, int i10, int i11) {
        this.f7432d.set(i8, i9, i10, i11);
        U();
    }

    public final boolean R() {
        return this.f7431c.getPreventCornerOverlap() && !e();
    }

    public final boolean S() {
        return this.f7431c.getPreventCornerOverlap() && e() && this.f7431c.getUseCompatPadding();
    }

    public void T() {
        Drawable drawable = this.f7438j;
        Drawable drawableP = this.f7431c.isClickable() ? p() : this.f7434f;
        this.f7438j = drawableP;
        if (drawable != drawableP) {
            W(drawableP);
        }
    }

    public void U() {
        int iA = (int) ((R() || S() ? a() : 0.0f) - r());
        MaterialCardView materialCardView = this.f7431c;
        Rect rect = this.f7432d;
        materialCardView.m(rect.left + iA, rect.top + iA, rect.right + iA, rect.bottom + iA);
    }

    public void V() {
        this.f7433e.W(this.f7431c.getCardElevation());
    }

    public final void W(Drawable drawable) {
        if (Build.VERSION.SDK_INT < 23 || !(this.f7431c.getForeground() instanceof InsetDrawable)) {
            this.f7431c.setForeground(z(drawable));
        } else {
            ((InsetDrawable) this.f7431c.getForeground()).setDrawable(drawable);
        }
    }

    public void X() {
        if (!A()) {
            this.f7431c.setBackgroundInternal(z(this.f7433e));
        }
        this.f7431c.setForeground(z(this.f7438j));
    }

    public final void Y() {
        Drawable drawable;
        if (b.f2328a && (drawable = this.f7444p) != null) {
            ((RippleDrawable) drawable).setColor(this.f7440l);
            return;
        }
        h hVar = this.f7446r;
        if (hVar != null) {
            hVar.X(this.f7440l);
        }
    }

    public void Z() {
        this.f7434f.h0(this.f7437i, this.f7443o);
    }

    public final float a() {
        return Math.max(Math.max(b(this.f7442n.q(), this.f7433e.G()), b(this.f7442n.s(), this.f7433e.H())), Math.max(b(this.f7442n.k(), this.f7433e.t()), b(this.f7442n.i(), this.f7433e.s())));
    }

    public final float b(d dVar, float f8) {
        if (dVar instanceof l) {
            return (float) ((1.0d - f7430b) * ((double) f8));
        }
        if (dVar instanceof e) {
            return f8 / 2.0f;
        }
        return 0.0f;
    }

    public final float c() {
        return this.f7431c.getMaxCardElevation() + (S() ? a() : 0.0f);
    }

    public final float d() {
        return (this.f7431c.getMaxCardElevation() * 1.5f) + (S() ? a() : 0.0f);
    }

    public final boolean e() {
        return Build.VERSION.SDK_INT >= 21 && this.f7433e.Q();
    }

    public final Drawable f() {
        StateListDrawable stateListDrawable = new StateListDrawable();
        Drawable drawable = this.f7439k;
        if (drawable != null) {
            stateListDrawable.addState(f7429a, drawable);
        }
        return stateListDrawable;
    }

    public final Drawable g() {
        StateListDrawable stateListDrawable = new StateListDrawable();
        h hVarI = i();
        this.f7446r = hVarI;
        hVarI.X(this.f7440l);
        stateListDrawable.addState(new int[]{R.attr.state_pressed}, this.f7446r);
        return stateListDrawable;
    }

    public final Drawable h() {
        if (!b.f2328a) {
            return g();
        }
        h hVarI = i();
        this.f7447s = hVarI;
        return new RippleDrawable(this.f7440l, null, hVarI);
    }

    public final h i() {
        return new h(this.f7442n);
    }

    public void j() {
        Drawable drawable = this.f7444p;
        if (drawable != null) {
            Rect bounds = drawable.getBounds();
            int i8 = bounds.bottom;
            this.f7444p.setBounds(bounds.left, bounds.top, bounds.right, i8 - 1);
            this.f7444p.setBounds(bounds.left, bounds.top, bounds.right, i8);
        }
    }

    public h k() {
        return this.f7433e;
    }

    public ColorStateList l() {
        return this.f7433e.x();
    }

    public ColorStateList m() {
        return this.f7434f.x();
    }

    public Drawable n() {
        return this.f7439k;
    }

    public ColorStateList o() {
        return this.f7441m;
    }

    public final Drawable p() {
        if (this.f7444p == null) {
            this.f7444p = h();
        }
        if (this.f7445q == null) {
            LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{this.f7444p, this.f7434f, f()});
            this.f7445q = layerDrawable;
            layerDrawable.setId(2, R$id.mtrl_card_checked_layer_id);
        }
        return this.f7445q;
    }

    public float q() {
        return this.f7433e.G();
    }

    public final float r() {
        if (!this.f7431c.getPreventCornerOverlap()) {
            return 0.0f;
        }
        if (Build.VERSION.SDK_INT < 21 || this.f7431c.getUseCompatPadding()) {
            return (float) ((1.0d - f7430b) * ((double) this.f7431c.getCardViewRadius()));
        }
        return 0.0f;
    }

    public float s() {
        return this.f7433e.y();
    }

    public ColorStateList t() {
        return this.f7440l;
    }

    public m u() {
        return this.f7442n;
    }

    public int v() {
        ColorStateList colorStateList = this.f7443o;
        if (colorStateList == null) {
            return -1;
        }
        return colorStateList.getDefaultColor();
    }

    public ColorStateList w() {
        return this.f7443o;
    }

    public int x() {
        return this.f7437i;
    }

    public Rect y() {
        return this.f7432d;
    }

    public final Drawable z(Drawable drawable) {
        int iCeil;
        int i8;
        if ((Build.VERSION.SDK_INT < 21) || this.f7431c.getUseCompatPadding()) {
            int iCeil2 = (int) Math.ceil(d());
            iCeil = (int) Math.ceil(c());
            i8 = iCeil2;
        } else {
            iCeil = 0;
            i8 = 0;
        }
        return new C0109a(drawable, iCeil, i8, iCeil, i8);
    }
}
