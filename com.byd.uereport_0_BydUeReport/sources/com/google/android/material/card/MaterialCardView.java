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
import e6.i;
import e6.m;
import e6.p;
import z5.k;

/* JADX INFO: loaded from: classes2.dex */
public class MaterialCardView extends CardView implements Checkable, p {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final int[] f3753m = {R.attr.state_checkable};

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final int[] f3754n = {R.attr.state_checked};

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final int[] f3755o = {R$attr.state_dragged};

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final int f3756p = R$style.Widget_MaterialComponents_CardView;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final q5.a f3757q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f3758r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f3759s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f3760t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public a f3761u;

    public interface a {
        void a(MaterialCardView materialCardView, boolean z7);
    }

    public MaterialCardView(Context context) {
        this(context, null);
    }

    private RectF getBoundsAsRectF() {
        RectF rectF = new RectF();
        rectF.set(this.f3757q.k().getBounds());
        return rectF;
    }

    @Override // androidx.cardview.widget.CardView
    public ColorStateList getCardBackgroundColor() {
        return this.f3757q.l();
    }

    public ColorStateList getCardForegroundColor() {
        return this.f3757q.m();
    }

    public float getCardViewRadius() {
        return super.getRadius();
    }

    public Drawable getCheckedIcon() {
        return this.f3757q.n();
    }

    public ColorStateList getCheckedIconTint() {
        return this.f3757q.o();
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingBottom() {
        return this.f3757q.y().bottom;
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingLeft() {
        return this.f3757q.y().left;
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingRight() {
        return this.f3757q.y().right;
    }

    @Override // androidx.cardview.widget.CardView
    public int getContentPaddingTop() {
        return this.f3757q.y().top;
    }

    public float getProgress() {
        return this.f3757q.s();
    }

    @Override // androidx.cardview.widget.CardView
    public float getRadius() {
        return this.f3757q.q();
    }

    public ColorStateList getRippleColor() {
        return this.f3757q.t();
    }

    public m getShapeAppearanceModel() {
        return this.f3757q.u();
    }

    @Deprecated
    public int getStrokeColor() {
        return this.f3757q.v();
    }

    public ColorStateList getStrokeColorStateList() {
        return this.f3757q.w();
    }

    public int getStrokeWidth() {
        return this.f3757q.x();
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.f3759s;
    }

    public final void j() {
        if (Build.VERSION.SDK_INT > 26) {
            this.f3757q.j();
        }
    }

    public boolean k() {
        q5.a aVar = this.f3757q;
        return aVar != null && aVar.B();
    }

    public boolean l() {
        return this.f3760t;
    }

    public void m(int i8, int i9, int i10, int i11) {
        super.h(i8, i9, i10, i11);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        i.f(this, this.f3757q.k());
    }

    @Override // android.view.ViewGroup, android.view.View
    public int[] onCreateDrawableState(int i8) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i8 + 3);
        if (k()) {
            FrameLayout.mergeDrawableStates(iArrOnCreateDrawableState, f3753m);
        }
        if (isChecked()) {
            FrameLayout.mergeDrawableStates(iArrOnCreateDrawableState, f3754n);
        }
        if (l()) {
            FrameLayout.mergeDrawableStates(iArrOnCreateDrawableState, f3755o);
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
    public void onMeasure(int i8, int i9) {
        super.onMeasure(i8, i9);
        this.f3757q.D(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (this.f3758r) {
            if (!this.f3757q.A()) {
                Log.i("MaterialCardView", "Setting a custom background is not supported.");
                this.f3757q.E(true);
            }
            super.setBackgroundDrawable(drawable);
        }
    }

    public void setBackgroundInternal(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
    }

    @Override // androidx.cardview.widget.CardView
    public void setCardBackgroundColor(int i8) {
        this.f3757q.F(ColorStateList.valueOf(i8));
    }

    @Override // androidx.cardview.widget.CardView
    public void setCardElevation(float f8) {
        super.setCardElevation(f8);
        this.f3757q.V();
    }

    public void setCardForegroundColor(ColorStateList colorStateList) {
        this.f3757q.G(colorStateList);
    }

    public void setCheckable(boolean z7) {
        this.f3757q.H(z7);
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z7) {
        if (this.f3759s != z7) {
            toggle();
        }
    }

    public void setCheckedIcon(Drawable drawable) {
        this.f3757q.I(drawable);
    }

    public void setCheckedIconResource(int i8) {
        this.f3757q.I(m.a.d(getContext(), i8));
    }

    public void setCheckedIconTint(ColorStateList colorStateList) {
        this.f3757q.J(colorStateList);
    }

    @Override // android.view.View
    public void setClickable(boolean z7) {
        super.setClickable(z7);
        q5.a aVar = this.f3757q;
        if (aVar != null) {
            aVar.T();
        }
    }

    public void setDragged(boolean z7) {
        if (this.f3760t != z7) {
            this.f3760t = z7;
            refreshDrawableState();
            j();
            invalidate();
        }
    }

    @Override // androidx.cardview.widget.CardView
    public void setMaxCardElevation(float f8) {
        super.setMaxCardElevation(f8);
        this.f3757q.X();
    }

    public void setOnCheckedChangeListener(a aVar) {
        this.f3761u = aVar;
    }

    @Override // androidx.cardview.widget.CardView
    public void setPreventCornerOverlap(boolean z7) {
        super.setPreventCornerOverlap(z7);
        this.f3757q.X();
        this.f3757q.U();
    }

    public void setProgress(float f8) {
        this.f3757q.L(f8);
    }

    @Override // androidx.cardview.widget.CardView
    public void setRadius(float f8) {
        super.setRadius(f8);
        this.f3757q.K(f8);
    }

    public void setRippleColor(ColorStateList colorStateList) {
        this.f3757q.M(colorStateList);
    }

    public void setRippleColorResource(int i8) {
        this.f3757q.M(m.a.c(getContext(), i8));
    }

    @Override // e6.p
    public void setShapeAppearanceModel(m mVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            setClipToOutline(mVar.u(getBoundsAsRectF()));
        }
        this.f3757q.N(mVar);
    }

    public void setStrokeColor(int i8) {
        this.f3757q.O(ColorStateList.valueOf(i8));
    }

    public void setStrokeWidth(int i8) {
        this.f3757q.P(i8);
    }

    @Override // androidx.cardview.widget.CardView
    public void setUseCompatPadding(boolean z7) {
        super.setUseCompatPadding(z7);
        this.f3757q.X();
        this.f3757q.U();
    }

    @Override // android.widget.Checkable
    public void toggle() {
        if (k() && isEnabled()) {
            this.f3759s = !this.f3759s;
            refreshDrawableState();
            j();
            a aVar = this.f3761u;
            if (aVar != null) {
                aVar.a(this, this.f3759s);
            }
        }
    }

    public MaterialCardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.materialCardViewStyle);
    }

    @Override // androidx.cardview.widget.CardView
    public void setCardBackgroundColor(ColorStateList colorStateList) {
        this.f3757q.F(colorStateList);
    }

    public void setStrokeColor(ColorStateList colorStateList) {
        this.f3757q.O(colorStateList);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialCardView(Context context, AttributeSet attributeSet, int i8) {
        int i9 = f3756p;
        super(i6.a.c(context, attributeSet, i8, i9), attributeSet, i8);
        this.f3759s = false;
        this.f3760t = false;
        this.f3758r = true;
        TypedArray typedArrayH = k.h(getContext(), attributeSet, R$styleable.MaterialCardView, i8, i9, new int[0]);
        q5.a aVar = new q5.a(this, attributeSet, i8, i9);
        this.f3757q = aVar;
        aVar.F(super.getCardBackgroundColor());
        aVar.Q(super.getContentPaddingLeft(), super.getContentPaddingTop(), super.getContentPaddingRight(), super.getContentPaddingBottom());
        aVar.C(typedArrayH);
        typedArrayH.recycle();
    }
}
