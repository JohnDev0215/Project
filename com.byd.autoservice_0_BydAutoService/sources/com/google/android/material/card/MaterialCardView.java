package com.google.android.material.card;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Checkable;
import android.widget.FrameLayout;
import androidx.cardview.widget.CardView;
import com.google.android.material.R$attr;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import i2.k;
import n2.i;
import n2.m;
import n2.p;

/* JADX INFO: loaded from: classes.dex */
public class MaterialCardView extends CardView implements Checkable, p {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final int[] f2170k = {R.attr.state_checkable};

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final int[] f2171l = {R.attr.state_checked};

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final int[] f2172m = {R$attr.state_dragged};

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final int f2173n = R$style.Widget_MaterialComponents_CardView;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final z1.a f2174o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f2175p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f2176q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f2177r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public a f2178s;

    public interface a {
        void a(MaterialCardView materialCardView, boolean z3);
    }

    public MaterialCardView(Context context) {
        this(context, null);
    }

    private RectF getBoundsAsRectF() {
        RectF rectF = new RectF();
        rectF.set(this.f2174o.k().getBounds());
        return rectF;
    }

    @Override // androidx.cardview.widget.CardView
    public ColorStateList getCardBackgroundColor() {
        return this.f2174o.l();
    }

    public ColorStateList getCardForegroundColor() {
        return this.f2174o.m();
    }

    public float getCardViewRadius() {
        return super.getRadius();
    }

    public Drawable getCheckedIcon() {
        return this.f2174o.n();
    }

    public ColorStateList getCheckedIconTint() {
        return this.f2174o.o();
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingBottom() {
        return this.f2174o.y().bottom;
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingLeft() {
        return this.f2174o.y().left;
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingRight() {
        return this.f2174o.y().right;
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingTop() {
        return this.f2174o.y().top;
    }

    public float getProgress() {
        return this.f2174o.s();
    }

    @Override // androidx.cardview.widget.CardView
    public float getRadius() {
        return this.f2174o.q();
    }

    public ColorStateList getRippleColor() {
        return this.f2174o.t();
    }

    public m getShapeAppearanceModel() {
        return this.f2174o.u();
    }

    @Deprecated
    public int getStrokeColor() {
        return this.f2174o.v();
    }

    public ColorStateList getStrokeColorStateList() {
        return this.f2174o.w();
    }

    public int getStrokeWidth() {
        return this.f2174o.x();
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.f2176q;
    }

    public final void j() {
        if (Build.VERSION.SDK_INT > 26) {
            this.f2174o.j();
        }
    }

    public boolean k() {
        z1.a aVar = this.f2174o;
        return aVar != null && aVar.B();
    }

    public boolean l() {
        return this.f2177r;
    }

    public void m(int i3, int i4, int i5, int i6) {
        super.h(i3, i4, i5, i6);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        i.f(this, this.f2174o.k());
    }

    @Override // android.view.ViewGroup, android.view.View
    public int[] onCreateDrawableState(int i3) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i3 + 3);
        if (k()) {
            FrameLayout.mergeDrawableStates(iArrOnCreateDrawableState, f2170k);
        }
        if (isChecked()) {
            FrameLayout.mergeDrawableStates(iArrOnCreateDrawableState, f2171l);
        }
        if (l()) {
            FrameLayout.mergeDrawableStates(iArrOnCreateDrawableState, f2172m);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("androidx.cardview.widget.CardView");
        accessibilityEvent.setChecked(isChecked());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("androidx.cardview.widget.CardView");
        accessibilityNodeInfo.setCheckable(k());
        accessibilityNodeInfo.setClickable(isClickable());
        accessibilityNodeInfo.setChecked(isChecked());
    }

    @Override // androidx.cardview.widget.CardView, android.widget.FrameLayout, android.view.View
    public void onMeasure(int i3, int i4) {
        super.onMeasure(i3, i4);
        this.f2174o.D(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (this.f2175p) {
            if (!this.f2174o.A()) {
                Log.i("MaterialCardView", "Setting a custom background is not supported.");
                this.f2174o.E(true);
            }
            super.setBackgroundDrawable(drawable);
        }
    }

    public void setBackgroundInternal(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
    }

    @Override // androidx.cardview.widget.CardView
    public void setCardBackgroundColor(int i3) {
        this.f2174o.F(ColorStateList.valueOf(i3));
    }

    @Override // androidx.cardview.widget.CardView
    public void setCardElevation(float f3) {
        super.setCardElevation(f3);
        this.f2174o.V();
    }

    public void setCardForegroundColor(ColorStateList colorStateList) {
        this.f2174o.G(colorStateList);
    }

    public void setCheckable(boolean z3) {
        this.f2174o.H(z3);
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z3) {
        if (this.f2176q != z3) {
            toggle();
        }
    }

    public void setCheckedIcon(Drawable drawable) {
        this.f2174o.I(drawable);
    }

    public void setCheckedIconResource(int i3) {
        this.f2174o.I(j.a.d(getContext(), i3));
    }

    public void setCheckedIconTint(ColorStateList colorStateList) {
        this.f2174o.J(colorStateList);
    }

    @Override // android.view.View
    public void setClickable(boolean z3) {
        super.setClickable(z3);
        z1.a aVar = this.f2174o;
        if (aVar != null) {
            aVar.T();
        }
    }

    public void setDragged(boolean z3) {
        if (this.f2177r != z3) {
            this.f2177r = z3;
            refreshDrawableState();
            j();
            invalidate();
        }
    }

    @Override // androidx.cardview.widget.CardView
    public void setMaxCardElevation(float f3) {
        super.setMaxCardElevation(f3);
        this.f2174o.X();
    }

    public void setOnCheckedChangeListener(a aVar) {
        this.f2178s = aVar;
    }

    @Override // androidx.cardview.widget.CardView
    public void setPreventCornerOverlap(boolean z3) {
        super.setPreventCornerOverlap(z3);
        this.f2174o.X();
        this.f2174o.U();
    }

    public void setProgress(float f3) {
        this.f2174o.L(f3);
    }

    @Override // androidx.cardview.widget.CardView
    public void setRadius(float f3) {
        super.setRadius(f3);
        this.f2174o.K(f3);
    }

    public void setRippleColor(ColorStateList colorStateList) {
        this.f2174o.M(colorStateList);
    }

    public void setRippleColorResource(int i3) {
        this.f2174o.M(j.a.c(getContext(), i3));
    }

    @Override // n2.p
    public void setShapeAppearanceModel(m mVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            setClipToOutline(mVar.u(getBoundsAsRectF()));
        }
        this.f2174o.N(mVar);
    }

    public void setStrokeColor(int i3) {
        this.f2174o.O(ColorStateList.valueOf(i3));
    }

    public void setStrokeWidth(int i3) {
        this.f2174o.P(i3);
    }

    @Override // androidx.cardview.widget.CardView
    public void setUseCompatPadding(boolean z3) {
        super.setUseCompatPadding(z3);
        this.f2174o.X();
        this.f2174o.U();
    }

    @Override // android.widget.Checkable
    public void toggle() {
        if (k() && isEnabled()) {
            this.f2176q = !this.f2176q;
            refreshDrawableState();
            j();
            a aVar = this.f2178s;
            if (aVar != null) {
                aVar.a(this, this.f2176q);
            }
        }
    }

    public MaterialCardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.materialCardViewStyle);
    }

    @Override // androidx.cardview.widget.CardView
    public void setCardBackgroundColor(ColorStateList colorStateList) {
        this.f2174o.F(colorStateList);
    }

    public void setStrokeColor(ColorStateList colorStateList) {
        this.f2174o.O(colorStateList);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialCardView(Context context, AttributeSet attributeSet, int i3) {
        int i4 = f2173n;
        super(r2.a.c(context, attributeSet, i3, i4), attributeSet, i3);
        this.f2176q = false;
        this.f2177r = false;
        this.f2175p = true;
        TypedArray typedArrayH = k.h(getContext(), attributeSet, R$styleable.MaterialCardView, i3, i4, new int[0]);
        z1.a aVar = new z1.a(this, attributeSet, i3, i4);
        this.f2174o = aVar;
        aVar.F(super.getCardBackgroundColor());
        aVar.Q(super.getContentPaddingLeft(), super.getContentPaddingTop(), super.getContentPaddingRight(), super.getContentPaddingBottom());
        aVar.C(typedArrayH);
        typedArrayH.recycle();
    }
}
