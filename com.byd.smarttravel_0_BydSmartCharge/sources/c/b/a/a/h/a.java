package c.b.a.a.h;

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
import b.g.j.t;
import c.b.a.a.s.c;
import c.b.a.a.t.b;
import c.b.a.a.v.d;
import c.b.a.a.v.e;
import c.b.a.a.v.h;
import c.b.a.a.v.l;
import c.b.a.a.v.m;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$id;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.card.MaterialCardView;

/* JADX INFO: compiled from: MaterialCardViewHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f1691a = {R.attr.state_checked};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final double f1692b = Math.cos(Math.toRadians(45.0d));

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final MaterialCardView f1693c;
    public final h e;
    public final h f;
    public final int g;
    public final int h;
    public int i;
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
    public final Rect f1694d = new Rect();
    public boolean t = false;

    /* JADX INFO: renamed from: c.b.a.a.h.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: MaterialCardViewHelper.java */
    public class C0050a extends InsetDrawable {
        public C0050a(Drawable drawable, int i, int i2, int i3, int i4) {
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
        this.f1693c = materialCardView;
        h hVar = new h(materialCardView.getContext(), attributeSet, i, i2);
        this.e = hVar;
        hVar.N(materialCardView.getContext());
        hVar.d0(-12303292);
        m.b bVarV = hVar.D().v();
        TypedArray typedArrayObtainStyledAttributes = materialCardView.getContext().obtainStyledAttributes(attributeSet, R$styleable.CardView, i, R$style.CardView);
        int i3 = R$styleable.CardView_cardCornerRadius;
        if (typedArrayObtainStyledAttributes.hasValue(i3)) {
            bVarV.o(typedArrayObtainStyledAttributes.getDimension(i3, 0.0f));
        }
        this.f = new h();
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
        ColorStateList colorStateListA = c.a(this.f1693c.getContext(), typedArray, R$styleable.MaterialCardView_strokeColor);
        this.o = colorStateListA;
        if (colorStateListA == null) {
            this.o = ColorStateList.valueOf(-1);
        }
        this.i = typedArray.getDimensionPixelSize(R$styleable.MaterialCardView_strokeWidth, 0);
        boolean z = typedArray.getBoolean(R$styleable.MaterialCardView_android_checkable, false);
        this.u = z;
        this.f1693c.setLongClickable(z);
        this.m = c.a(this.f1693c.getContext(), typedArray, R$styleable.MaterialCardView_checkedIconTint);
        I(c.d(this.f1693c.getContext(), typedArray, R$styleable.MaterialCardView_checkedIcon));
        ColorStateList colorStateListA2 = c.a(this.f1693c.getContext(), typedArray, R$styleable.MaterialCardView_rippleColor);
        this.l = colorStateListA2;
        if (colorStateListA2 == null) {
            this.l = ColorStateList.valueOf(c.b.a.a.k.a.c(this.f1693c, R$attr.colorControlHighlight));
        }
        G(c.a(this.f1693c.getContext(), typedArray, R$styleable.MaterialCardView_cardForegroundColor));
        Y();
        V();
        Z();
        this.f1693c.setBackgroundInternal(z(this.e));
        Drawable drawableP = this.f1693c.isClickable() ? p() : this.f;
        this.j = drawableP;
        this.f1693c.setForeground(z(drawableP));
    }

    public void D(int i, int i2) {
        int i3;
        int i4;
        if (this.q != null) {
            int i5 = this.g;
            int i6 = this.h;
            int iCeil = (i - i5) - i6;
            int iCeil2 = (i2 - i5) - i6;
            if ((Build.VERSION.SDK_INT < 21) || this.f1693c.getUseCompatPadding()) {
                iCeil2 -= (int) Math.ceil(d() * 2.0f);
                iCeil -= (int) Math.ceil(c() * 2.0f);
            }
            int i7 = iCeil2;
            int i8 = this.g;
            if (t.z(this.f1693c) == 1) {
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
        h hVar = this.f;
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
            Drawable drawableR = b.g.c.l.a.r(drawable.mutate());
            this.k = drawableR;
            b.g.c.l.a.o(drawableR, this.m);
        }
        if (this.q != null) {
            this.q.setDrawableByLayerId(R$id.mtrl_card_checked_layer_id, f());
        }
    }

    public void J(ColorStateList colorStateList) {
        this.m = colorStateList;
        Drawable drawable = this.k;
        if (drawable != null) {
            b.g.c.l.a.o(drawable, colorStateList);
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
        h hVar = this.f;
        if (hVar != null) {
            hVar.Y(f);
        }
        h hVar2 = this.s;
        if (hVar2 != null) {
            hVar2.Y(f);
        }
    }

    public void M(ColorStateList colorStateList) {
        this.l = colorStateList;
        Y();
    }

    public void N(m mVar) {
        this.n = mVar;
        this.e.setShapeAppearanceModel(mVar);
        this.e.c0(!r0.Q());
        h hVar = this.f;
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

    public void P(int i) {
        if (i == this.i) {
            return;
        }
        this.i = i;
        Z();
    }

    public void Q(int i, int i2, int i3, int i4) {
        this.f1694d.set(i, i2, i3, i4);
        U();
    }

    public final boolean R() {
        return this.f1693c.getPreventCornerOverlap() && !e();
    }

    public final boolean S() {
        return this.f1693c.getPreventCornerOverlap() && e() && this.f1693c.getUseCompatPadding();
    }

    public void T() {
        Drawable drawable = this.j;
        Drawable drawableP = this.f1693c.isClickable() ? p() : this.f;
        this.j = drawableP;
        if (drawable != drawableP) {
            W(drawableP);
        }
    }

    public void U() {
        int iA = (int) ((R() || S() ? a() : 0.0f) - r());
        MaterialCardView materialCardView = this.f1693c;
        Rect rect = this.f1694d;
        materialCardView.m(rect.left + iA, rect.top + iA, rect.right + iA, rect.bottom + iA);
    }

    public void V() {
        this.e.W(this.f1693c.getCardElevation());
    }

    public final void W(Drawable drawable) {
        if (Build.VERSION.SDK_INT < 23 || !(this.f1693c.getForeground() instanceof InsetDrawable)) {
            this.f1693c.setForeground(z(drawable));
        } else {
            ((InsetDrawable) this.f1693c.getForeground()).setDrawable(drawable);
        }
    }

    public void X() {
        if (!A()) {
            this.f1693c.setBackgroundInternal(z(this.e));
        }
        this.f1693c.setForeground(z(this.j));
    }

    public final void Y() {
        Drawable drawable;
        if (b.f1838a && (drawable = this.p) != null) {
            ((RippleDrawable) drawable).setColor(this.l);
            return;
        }
        h hVar = this.r;
        if (hVar != null) {
            hVar.X(this.l);
        }
    }

    public void Z() {
        this.f.h0(this.i, this.o);
    }

    public final float a() {
        return Math.max(Math.max(b(this.n.q(), this.e.G()), b(this.n.s(), this.e.H())), Math.max(b(this.n.k(), this.e.t()), b(this.n.i(), this.e.s())));
    }

    public final float b(d dVar, float f) {
        if (dVar instanceof l) {
            return (float) ((1.0d - f1692b) * ((double) f));
        }
        if (dVar instanceof e) {
            return f / 2.0f;
        }
        return 0.0f;
    }

    public final float c() {
        return this.f1693c.getMaxCardElevation() + (S() ? a() : 0.0f);
    }

    public final float d() {
        return (this.f1693c.getMaxCardElevation() * 1.5f) + (S() ? a() : 0.0f);
    }

    public final boolean e() {
        return Build.VERSION.SDK_INT >= 21 && this.e.Q();
    }

    public final Drawable f() {
        StateListDrawable stateListDrawable = new StateListDrawable();
        Drawable drawable = this.k;
        if (drawable != null) {
            stateListDrawable.addState(f1691a, drawable);
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
        if (!b.f1838a) {
            return g();
        }
        this.s = i();
        return new RippleDrawable(this.l, null, this.s);
    }

    public final h i() {
        return new h(this.n);
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

    public h k() {
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
        if (!this.f1693c.getPreventCornerOverlap()) {
            return 0.0f;
        }
        if (Build.VERSION.SDK_INT < 21 || this.f1693c.getUseCompatPadding()) {
            return (float) ((1.0d - f1692b) * ((double) this.f1693c.getCardViewRadius()));
        }
        return 0.0f;
    }

    public float s() {
        return this.e.y();
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
        return this.i;
    }

    public Rect y() {
        return this.f1694d;
    }

    public final Drawable z(Drawable drawable) {
        int iCeil;
        int i;
        if ((Build.VERSION.SDK_INT < 21) || this.f1693c.getUseCompatPadding()) {
            int iCeil2 = (int) Math.ceil(d());
            iCeil = (int) Math.ceil(c());
            i = iCeil2;
        } else {
            iCeil = 0;
            i = 0;
        }
        return new C0050a(drawable, iCeil, i, iCeil, i);
    }
}
