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
import d.b.a.a.b;
import d.b.a.a.k;
import d.b.a.a.l;
import d.b.a.a.w.i;
import d.b.a.a.w.m;
import d.b.a.a.w.p;

/* JADX INFO: loaded from: classes.dex */
public class MaterialCardView extends CardView implements Checkable, p {
    private static final int[] k = {R.attr.state_checkable};
    private static final int[] l = {R.attr.state_checked};
    private static final int[] m = {b.G};
    private static final int n = k.t;
    private final com.google.android.material.card.a o;
    private boolean p;
    private boolean q;
    private boolean r;
    private a s;

    public interface a {
        void a(MaterialCardView materialCardView, boolean z);
    }

    public MaterialCardView(Context context) {
        this(context, null);
    }

    private RectF getBoundsAsRectF() {
        RectF rectF = new RectF();
        rectF.set(this.o.k().getBounds());
        return rectF;
    }

    private void j() {
        if (Build.VERSION.SDK_INT > 26) {
            this.o.j();
        }
    }

    @Override // androidx.cardview.widget.CardView
    public ColorStateList getCardBackgroundColor() {
        return this.o.l();
    }

    public ColorStateList getCardForegroundColor() {
        return this.o.m();
    }

    float getCardViewRadius() {
        return super.getRadius();
    }

    public Drawable getCheckedIcon() {
        return this.o.n();
    }

    public ColorStateList getCheckedIconTint() {
        return this.o.o();
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingBottom() {
        return this.o.y().bottom;
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingLeft() {
        return this.o.y().left;
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingRight() {
        return this.o.y().right;
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingTop() {
        return this.o.y().top;
    }

    public float getProgress() {
        return this.o.s();
    }

    @Override // androidx.cardview.widget.CardView
    public float getRadius() {
        return this.o.q();
    }

    public ColorStateList getRippleColor() {
        return this.o.t();
    }

    public m getShapeAppearanceModel() {
        return this.o.u();
    }

    @Deprecated
    public int getStrokeColor() {
        return this.o.v();
    }

    public ColorStateList getStrokeColorStateList() {
        return this.o.w();
    }

    public int getStrokeWidth() {
        return this.o.x();
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.q;
    }

    public boolean k() {
        com.google.android.material.card.a aVar = this.o;
        return aVar != null && aVar.B();
    }

    public boolean l() {
        return this.r;
    }

    void m(int i, int i2, int i3, int i4) {
        super.h(i, i2, i3, i4);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        i.f(this, this.o.k());
    }

    @Override // android.view.ViewGroup, android.view.View
    protected int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 3);
        if (k()) {
            FrameLayout.mergeDrawableStates(iArrOnCreateDrawableState, k);
        }
        if (isChecked()) {
            FrameLayout.mergeDrawableStates(iArrOnCreateDrawableState, l);
        }
        if (l()) {
            FrameLayout.mergeDrawableStates(iArrOnCreateDrawableState, m);
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
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.o.D(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (this.p) {
            if (!this.o.A()) {
                Log.i("MaterialCardView", "Setting a custom background is not supported.");
                this.o.E(true);
            }
            super.setBackgroundDrawable(drawable);
        }
    }

    void setBackgroundInternal(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
    }

    @Override // androidx.cardview.widget.CardView
    public void setCardBackgroundColor(int i) {
        this.o.F(ColorStateList.valueOf(i));
    }

    @Override // androidx.cardview.widget.CardView
    public void setCardElevation(float f) {
        super.setCardElevation(f);
        this.o.V();
    }

    public void setCardForegroundColor(ColorStateList colorStateList) {
        this.o.G(colorStateList);
    }

    public void setCheckable(boolean z) {
        this.o.H(z);
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        if (this.q != z) {
            toggle();
        }
    }

    public void setCheckedIcon(Drawable drawable) {
        this.o.I(drawable);
    }

    public void setCheckedIconResource(int i) {
        this.o.I(c.a.k.a.a.d(getContext(), i));
    }

    public void setCheckedIconTint(ColorStateList colorStateList) {
        this.o.J(colorStateList);
    }

    @Override // android.view.View
    public void setClickable(boolean z) {
        super.setClickable(z);
        com.google.android.material.card.a aVar = this.o;
        if (aVar != null) {
            aVar.T();
        }
    }

    public void setDragged(boolean z) {
        if (this.r != z) {
            this.r = z;
            refreshDrawableState();
            j();
            invalidate();
        }
    }

    @Override // androidx.cardview.widget.CardView
    public void setMaxCardElevation(float f) {
        super.setMaxCardElevation(f);
        this.o.X();
    }

    public void setOnCheckedChangeListener(a aVar) {
        this.s = aVar;
    }

    @Override // androidx.cardview.widget.CardView
    public void setPreventCornerOverlap(boolean z) {
        super.setPreventCornerOverlap(z);
        this.o.X();
        this.o.U();
    }

    public void setProgress(float f) {
        this.o.L(f);
    }

    @Override // androidx.cardview.widget.CardView
    public void setRadius(float f) {
        super.setRadius(f);
        this.o.K(f);
    }

    public void setRippleColor(ColorStateList colorStateList) {
        this.o.M(colorStateList);
    }

    public void setRippleColorResource(int i) {
        this.o.M(c.a.k.a.a.c(getContext(), i));
    }

    @Override // d.b.a.a.w.p
    public void setShapeAppearanceModel(m mVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            setClipToOutline(mVar.u(getBoundsAsRectF()));
        }
        this.o.N(mVar);
    }

    public void setStrokeColor(int i) {
        this.o.O(ColorStateList.valueOf(i));
    }

    public void setStrokeWidth(int i) {
        this.o.P(i);
    }

    @Override // androidx.cardview.widget.CardView
    public void setUseCompatPadding(boolean z) {
        super.setUseCompatPadding(z);
        this.o.X();
        this.o.U();
    }

    @Override // android.widget.Checkable
    public void toggle() {
        if (k() && isEnabled()) {
            this.q = !this.q;
            refreshDrawableState();
            j();
            a aVar = this.s;
            if (aVar != null) {
                aVar.a(this, this.q);
            }
        }
    }

    public MaterialCardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, b.z);
    }

    @Override // androidx.cardview.widget.CardView
    public void setCardBackgroundColor(ColorStateList colorStateList) {
        this.o.F(colorStateList);
    }

    public void setStrokeColor(ColorStateList colorStateList) {
        this.o.O(colorStateList);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialCardView(Context context, AttributeSet attributeSet, int i) {
        int i2 = n;
        super(com.google.android.material.theme.a.a.c(context, attributeSet, i, i2), attributeSet, i);
        this.q = false;
        this.r = false;
        this.p = true;
        TypedArray typedArrayH = com.google.android.material.internal.k.h(getContext(), attributeSet, l.K3, i, i2, new int[0]);
        com.google.android.material.card.a aVar = new com.google.android.material.card.a(this, attributeSet, i, i2);
        this.o = aVar;
        aVar.F(super.getCardBackgroundColor());
        aVar.Q(super.getContentPaddingLeft(), super.getContentPaddingTop(), super.getContentPaddingRight(), super.getContentPaddingBottom());
        aVar.C(typedArrayH);
        typedArrayH.recycle();
    }
}
