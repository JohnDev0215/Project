package com.google.android.material.card;

import android.R;
import android.content.res.ColorStateList;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Checkable;
import android.widget.FrameLayout;
import e3.n;
import o2.b;
import o2.k;

/* JADX INFO: compiled from: MaterialCardView.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends m.a implements Checkable, n {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private static final int[] f4294r = {R.attr.state_checkable};

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private static final int[] f4295s = {R.attr.state_checked};

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private static final int[] f4296t = {b.state_dragged};

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private static final int f4297u = k.Widget_MaterialComponents_CardView;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private boolean f4298n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private boolean f4299o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private boolean f4300p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private InterfaceC0046a f4301q;

    /* JADX INFO: renamed from: com.google.android.material.card.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: MaterialCardView.java */
    public interface InterfaceC0046a {
        void a(a aVar, boolean z6);
    }

    private void d() {
        throw null;
    }

    private RectF getBoundsAsRectF() {
        new RectF();
        throw null;
    }

    public boolean e() {
        return false;
    }

    public boolean f() {
        return this.f4300p;
    }

    @Override // m.a
    public ColorStateList getCardBackgroundColor() {
        throw null;
    }

    public ColorStateList getCardForegroundColor() {
        throw null;
    }

    float getCardViewRadius() {
        return super.getRadius();
    }

    public Drawable getCheckedIcon() {
        throw null;
    }

    public int getCheckedIconMargin() {
        throw null;
    }

    public int getCheckedIconSize() {
        throw null;
    }

    public ColorStateList getCheckedIconTint() {
        throw null;
    }

    @Override // m.a
    public int getContentPaddingBottom() {
        throw null;
    }

    @Override // m.a
    public int getContentPaddingLeft() {
        throw null;
    }

    @Override // m.a
    public int getContentPaddingRight() {
        throw null;
    }

    @Override // m.a
    public int getContentPaddingTop() {
        throw null;
    }

    public float getProgress() {
        throw null;
    }

    @Override // m.a
    public float getRadius() {
        throw null;
    }

    public ColorStateList getRippleColor() {
        throw null;
    }

    public e3.k getShapeAppearanceModel() {
        throw null;
    }

    @Deprecated
    public int getStrokeColor() {
        throw null;
    }

    public ColorStateList getStrokeColorStateList() {
        throw null;
    }

    public int getStrokeWidth() {
        throw null;
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.f4299o;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        throw null;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected int[] onCreateDrawableState(int i7) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i7 + 3);
        if (e()) {
            FrameLayout.mergeDrawableStates(iArrOnCreateDrawableState, f4294r);
        }
        if (isChecked()) {
            FrameLayout.mergeDrawableStates(iArrOnCreateDrawableState, f4295s);
        }
        if (f()) {
            FrameLayout.mergeDrawableStates(iArrOnCreateDrawableState, f4296t);
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
        accessibilityNodeInfo.setCheckable(e());
        accessibilityNodeInfo.setClickable(isClickable());
        accessibilityNodeInfo.setChecked(isChecked());
    }

    @Override // m.a, android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i7, int i8) {
        super.onMeasure(i7, i8);
        getMeasuredWidth();
        getMeasuredHeight();
        throw null;
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (this.f4298n) {
            throw null;
        }
    }

    void setBackgroundInternal(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
    }

    @Override // m.a
    public void setCardBackgroundColor(int i7) {
        ColorStateList.valueOf(i7);
        throw null;
    }

    @Override // m.a
    public void setCardElevation(float f7) {
        super.setCardElevation(f7);
        throw null;
    }

    public void setCardForegroundColor(ColorStateList colorStateList) {
        throw null;
    }

    public void setCheckable(boolean z6) {
        throw null;
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z6) {
        if (this.f4299o != z6) {
            toggle();
        }
    }

    public void setCheckedIcon(Drawable drawable) {
        throw null;
    }

    public void setCheckedIconMargin(int i7) {
        throw null;
    }

    public void setCheckedIconMarginResource(int i7) {
        if (i7 == -1) {
            return;
        }
        getResources().getDimensionPixelSize(i7);
        throw null;
    }

    public void setCheckedIconResource(int i7) {
        f.a.b(getContext(), i7);
        throw null;
    }

    public void setCheckedIconSize(int i7) {
        throw null;
    }

    public void setCheckedIconSizeResource(int i7) {
        if (i7 == 0) {
            return;
        }
        getResources().getDimensionPixelSize(i7);
        throw null;
    }

    public void setCheckedIconTint(ColorStateList colorStateList) {
        throw null;
    }

    @Override // android.view.View
    public void setClickable(boolean z6) {
        super.setClickable(z6);
    }

    public void setDragged(boolean z6) {
        if (this.f4300p != z6) {
            this.f4300p = z6;
            refreshDrawableState();
            d();
            invalidate();
        }
    }

    @Override // m.a
    public void setMaxCardElevation(float f7) {
        super.setMaxCardElevation(f7);
        throw null;
    }

    public void setOnCheckedChangeListener(InterfaceC0046a interfaceC0046a) {
        this.f4301q = interfaceC0046a;
    }

    @Override // m.a
    public void setPreventCornerOverlap(boolean z6) {
        super.setPreventCornerOverlap(z6);
        throw null;
    }

    public void setProgress(float f7) {
        throw null;
    }

    @Override // m.a
    public void setRadius(float f7) {
        super.setRadius(f7);
        throw null;
    }

    public void setRippleColor(ColorStateList colorStateList) {
        throw null;
    }

    public void setRippleColorResource(int i7) {
        f.a.a(getContext(), i7);
        throw null;
    }

    @Override // e3.n
    public void setShapeAppearanceModel(e3.k kVar) {
        setClipToOutline(kVar.u(getBoundsAsRectF()));
        throw null;
    }

    public void setStrokeColor(int i7) {
        ColorStateList.valueOf(i7);
        throw null;
    }

    public void setStrokeWidth(int i7) {
        throw null;
    }

    @Override // m.a
    public void setUseCompatPadding(boolean z6) {
        super.setUseCompatPadding(z6);
        throw null;
    }

    @Override // android.widget.Checkable
    public void toggle() {
        if (e() && isEnabled()) {
            this.f4299o = !this.f4299o;
            refreshDrawableState();
            d();
            InterfaceC0046a interfaceC0046a = this.f4301q;
            if (interfaceC0046a != null) {
                interfaceC0046a.a(this, this.f4299o);
            }
        }
    }

    @Override // m.a
    public void setCardBackgroundColor(ColorStateList colorStateList) {
        throw null;
    }

    public void setStrokeColor(ColorStateList colorStateList) {
        throw null;
    }
}
