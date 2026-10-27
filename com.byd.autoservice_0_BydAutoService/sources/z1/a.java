package z1;

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
import k2.c;
import l0.t;
import l2.b;
import n2.d;
import n2.e;
import n2.h;
import n2.l;
import n2.m;

/* JADX INFO: compiled from: MaterialCardViewHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f6110a = {R.attr.state_checked};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final double f6111b = Math.cos(Math.toRadians(45.0d));

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final MaterialCardView f6112c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final h f6114e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final h f6115f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f6116g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f6117h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f6118i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Drawable f6119j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Drawable f6120k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public ColorStateList f6121l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ColorStateList f6122m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public m f6123n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public ColorStateList f6124o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Drawable f6125p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public LayerDrawable f6126q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public h f6127r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public h f6128s;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f6130u;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Rect f6113d = new Rect();

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f6129t = false;

    /* JADX INFO: renamed from: z1.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: MaterialCardViewHelper.java */
    public class C0080a extends InsetDrawable {
        public C0080a(Drawable drawable, int i3, int i4, int i5, int i6) {
            super(drawable, i3, i4, i5, i6);
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

    public a(MaterialCardView materialCardView, AttributeSet attributeSet, int i3, int i4) {
        this.f6112c = materialCardView;
        h hVar = new h(materialCardView.getContext(), attributeSet, i3, i4);
        this.f6114e = hVar;
        hVar.N(materialCardView.getContext());
        hVar.d0(-12303292);
        m.b bVarV = hVar.D().v();
        TypedArray typedArrayObtainStyledAttributes = materialCardView.getContext().obtainStyledAttributes(attributeSet, R$styleable.CardView, i3, R$style.CardView);
        int i5 = R$styleable.CardView_cardCornerRadius;
        if (typedArrayObtainStyledAttributes.hasValue(i5)) {
            bVarV.o(typedArrayObtainStyledAttributes.getDimension(i5, 0.0f));
        }
        this.f6115f = new h();
        N(bVarV.m());
        Resources resources = materialCardView.getResources();
        this.f6116g = resources.getDimensionPixelSize(R$dimen.mtrl_card_checked_icon_margin);
        this.f6117h = resources.getDimensionPixelSize(R$dimen.mtrl_card_checked_icon_size);
        typedArrayObtainStyledAttributes.recycle();
    }

    public boolean A() {
        return this.f6129t;
    }

    public boolean B() {
        return this.f6130u;
    }

    public void C(TypedArray typedArray) {
        ColorStateList colorStateListA = c.a(this.f6112c.getContext(), typedArray, R$styleable.MaterialCardView_strokeColor);
        this.f6124o = colorStateListA;
        if (colorStateListA == null) {
            this.f6124o = ColorStateList.valueOf(-1);
        }
        this.f6118i = typedArray.getDimensionPixelSize(R$styleable.MaterialCardView_strokeWidth, 0);
        boolean z3 = typedArray.getBoolean(R$styleable.MaterialCardView_android_checkable, false);
        this.f6130u = z3;
        this.f6112c.setLongClickable(z3);
        this.f6122m = c.a(this.f6112c.getContext(), typedArray, R$styleable.MaterialCardView_checkedIconTint);
        I(c.d(this.f6112c.getContext(), typedArray, R$styleable.MaterialCardView_checkedIcon));
        ColorStateList colorStateListA2 = c.a(this.f6112c.getContext(), typedArray, R$styleable.MaterialCardView_rippleColor);
        this.f6121l = colorStateListA2;
        if (colorStateListA2 == null) {
            this.f6121l = ColorStateList.valueOf(c2.a.c(this.f6112c, R$attr.colorControlHighlight));
        }
        G(c.a(this.f6112c.getContext(), typedArray, R$styleable.MaterialCardView_cardForegroundColor));
        Y();
        V();
        Z();
        this.f6112c.setBackgroundInternal(z(this.f6114e));
        Drawable drawableP = this.f6112c.isClickable() ? p() : this.f6115f;
        this.f6119j = drawableP;
        this.f6112c.setForeground(z(drawableP));
    }

    public void D(int i3, int i4) {
        int i5;
        int i6;
        if (this.f6126q != null) {
            int i7 = this.f6116g;
            int i8 = this.f6117h;
            int iCeil = (i3 - i7) - i8;
            int iCeil2 = (i4 - i7) - i8;
            if ((Build.VERSION.SDK_INT < 21) || this.f6112c.getUseCompatPadding()) {
                iCeil2 -= (int) Math.ceil(d() * 2.0f);
                iCeil -= (int) Math.ceil(c() * 2.0f);
            }
            int i9 = iCeil2;
            int i10 = this.f6116g;
            if (t.z(this.f6112c) == 1) {
                i6 = iCeil;
                i5 = i10;
            } else {
                i5 = iCeil;
                i6 = i10;
            }
            this.f6126q.setLayerInset(2, i5, this.f6116g, i6, i9);
        }
    }

    public void E(boolean z3) {
        this.f6129t = z3;
    }

    public void F(ColorStateList colorStateList) {
        this.f6114e.X(colorStateList);
    }

    public void G(ColorStateList colorStateList) {
        h hVar = this.f6115f;
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(0);
        }
        hVar.X(colorStateList);
    }

    public void H(boolean z3) {
        this.f6130u = z3;
    }

    public void I(Drawable drawable) {
        this.f6120k = drawable;
        if (drawable != null) {
            Drawable drawableR = e0.a.r(drawable.mutate());
            this.f6120k = drawableR;
            e0.a.o(drawableR, this.f6122m);
        }
        if (this.f6126q != null) {
            this.f6126q.setDrawableByLayerId(R$id.mtrl_card_checked_layer_id, f());
        }
    }

    public void J(ColorStateList colorStateList) {
        this.f6122m = colorStateList;
        Drawable drawable = this.f6120k;
        if (drawable != null) {
            e0.a.o(drawable, colorStateList);
        }
    }

    public void K(float f3) {
        N(this.f6123n.w(f3));
        this.f6119j.invalidateSelf();
        if (S() || R()) {
            U();
        }
        if (S()) {
            X();
        }
    }

    public void L(float f3) {
        this.f6114e.Y(f3);
        h hVar = this.f6115f;
        if (hVar != null) {
            hVar.Y(f3);
        }
        h hVar2 = this.f6128s;
        if (hVar2 != null) {
            hVar2.Y(f3);
        }
    }

    public void M(ColorStateList colorStateList) {
        this.f6121l = colorStateList;
        Y();
    }

    public void N(m mVar) {
        this.f6123n = mVar;
        this.f6114e.setShapeAppearanceModel(mVar);
        this.f6114e.c0(!r0.Q());
        h hVar = this.f6115f;
        if (hVar != null) {
            hVar.setShapeAppearanceModel(mVar);
        }
        h hVar2 = this.f6128s;
        if (hVar2 != null) {
            hVar2.setShapeAppearanceModel(mVar);
        }
        h hVar3 = this.f6127r;
        if (hVar3 != null) {
            hVar3.setShapeAppearanceModel(mVar);
        }
    }

    public void O(ColorStateList colorStateList) {
        if (this.f6124o == colorStateList) {
            return;
        }
        this.f6124o = colorStateList;
        Z();
    }

    public void P(int i3) {
        if (i3 == this.f6118i) {
            return;
        }
        this.f6118i = i3;
        Z();
    }

    public void Q(int i3, int i4, int i5, int i6) {
        this.f6113d.set(i3, i4, i5, i6);
        U();
    }

    public final boolean R() {
        return this.f6112c.getPreventCornerOverlap() && !e();
    }

    public final boolean S() {
        return this.f6112c.getPreventCornerOverlap() && e() && this.f6112c.getUseCompatPadding();
    }

    public void T() {
        Drawable drawable = this.f6119j;
        Drawable drawableP = this.f6112c.isClickable() ? p() : this.f6115f;
        this.f6119j = drawableP;
        if (drawable != drawableP) {
            W(drawableP);
        }
    }

    public void U() {
        int iA = (int) ((R() || S() ? a() : 0.0f) - r());
        MaterialCardView materialCardView = this.f6112c;
        Rect rect = this.f6113d;
        materialCardView.m(rect.left + iA, rect.top + iA, rect.right + iA, rect.bottom + iA);
    }

    public void V() {
        this.f6114e.W(this.f6112c.getCardElevation());
    }

    public final void W(Drawable drawable) {
        if (Build.VERSION.SDK_INT < 23 || !(this.f6112c.getForeground() instanceof InsetDrawable)) {
            this.f6112c.setForeground(z(drawable));
        } else {
            ((InsetDrawable) this.f6112c.getForeground()).setDrawable(drawable);
        }
    }

    public void X() {
        if (!A()) {
            this.f6112c.setBackgroundInternal(z(this.f6114e));
        }
        this.f6112c.setForeground(z(this.f6119j));
    }

    public final void Y() {
        Drawable drawable;
        if (b.f3717a && (drawable = this.f6125p) != null) {
            ((RippleDrawable) drawable).setColor(this.f6121l);
            return;
        }
        h hVar = this.f6127r;
        if (hVar != null) {
            hVar.X(this.f6121l);
        }
    }

    public void Z() {
        this.f6115f.h0(this.f6118i, this.f6124o);
    }

    public final float a() {
        return Math.max(Math.max(b(this.f6123n.q(), this.f6114e.G()), b(this.f6123n.s(), this.f6114e.H())), Math.max(b(this.f6123n.k(), this.f6114e.t()), b(this.f6123n.i(), this.f6114e.s())));
    }

    public final float b(d dVar, float f3) {
        if (dVar instanceof l) {
            return (float) ((1.0d - f6111b) * ((double) f3));
        }
        if (dVar instanceof e) {
            return f3 / 2.0f;
        }
        return 0.0f;
    }

    public final float c() {
        return this.f6112c.getMaxCardElevation() + (S() ? a() : 0.0f);
    }

    public final float d() {
        return (this.f6112c.getMaxCardElevation() * 1.5f) + (S() ? a() : 0.0f);
    }

    public final boolean e() {
        return Build.VERSION.SDK_INT >= 21 && this.f6114e.Q();
    }

    public final Drawable f() {
        StateListDrawable stateListDrawable = new StateListDrawable();
        Drawable drawable = this.f6120k;
        if (drawable != null) {
            stateListDrawable.addState(f6110a, drawable);
        }
        return stateListDrawable;
    }

    public final Drawable g() {
        StateListDrawable stateListDrawable = new StateListDrawable();
        h hVarI = i();
        this.f6127r = hVarI;
        hVarI.X(this.f6121l);
        stateListDrawable.addState(new int[]{R.attr.state_pressed}, this.f6127r);
        return stateListDrawable;
    }

    public final Drawable h() {
        if (!b.f3717a) {
            return g();
        }
        h hVarI = i();
        this.f6128s = hVarI;
        return new RippleDrawable(this.f6121l, null, hVarI);
    }

    public final h i() {
        return new h(this.f6123n);
    }

    public void j() {
        Drawable drawable = this.f6125p;
        if (drawable != null) {
            Rect bounds = drawable.getBounds();
            int i3 = bounds.bottom;
            this.f6125p.setBounds(bounds.left, bounds.top, bounds.right, i3 - 1);
            this.f6125p.setBounds(bounds.left, bounds.top, bounds.right, i3);
        }
    }

    public h k() {
        return this.f6114e;
    }

    public ColorStateList l() {
        return this.f6114e.x();
    }

    public ColorStateList m() {
        return this.f6115f.x();
    }

    public Drawable n() {
        return this.f6120k;
    }

    public ColorStateList o() {
        return this.f6122m;
    }

    public final Drawable p() {
        if (this.f6125p == null) {
            this.f6125p = h();
        }
        if (this.f6126q == null) {
            LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{this.f6125p, this.f6115f, f()});
            this.f6126q = layerDrawable;
            layerDrawable.setId(2, R$id.mtrl_card_checked_layer_id);
        }
        return this.f6126q;
    }

    public float q() {
        return this.f6114e.G();
    }

    public final float r() {
        if (!this.f6112c.getPreventCornerOverlap()) {
            return 0.0f;
        }
        if (Build.VERSION.SDK_INT < 21 || this.f6112c.getUseCompatPadding()) {
            return (float) ((1.0d - f6111b) * ((double) this.f6112c.getCardViewRadius()));
        }
        return 0.0f;
    }

    public float s() {
        return this.f6114e.y();
    }

    public ColorStateList t() {
        return this.f6121l;
    }

    public m u() {
        return this.f6123n;
    }

    public int v() {
        ColorStateList colorStateList = this.f6124o;
        if (colorStateList == null) {
            return -1;
        }
        return colorStateList.getDefaultColor();
    }

    public ColorStateList w() {
        return this.f6124o;
    }

    public int x() {
        return this.f6118i;
    }

    public Rect y() {
        return this.f6113d;
    }

    public final Drawable z(Drawable drawable) {
        int iCeil;
        int i3;
        if ((Build.VERSION.SDK_INT < 21) || this.f6112c.getUseCompatPadding()) {
            int iCeil2 = (int) Math.ceil(d());
            iCeil = (int) Math.ceil(c());
            i3 = iCeil2;
        } else {
            iCeil = 0;
            i3 = 0;
        }
        return new C0080a(drawable, iCeil, i3, iCeil, i3);
    }
}
