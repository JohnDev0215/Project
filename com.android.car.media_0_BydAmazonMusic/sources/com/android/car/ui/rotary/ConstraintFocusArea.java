package com.android.car.ui.rotary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.FocusFinder;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.constraintlayout.widget.ConstraintLayout;
import spi.o4;
import spi.y3;

/* JADX INFO: loaded from: classes2.dex */
public class ConstraintFocusArea extends ConstraintLayout implements o4 {

    @NonNull
    public final y3 a;

    public ConstraintFocusArea(Context context) {
        this(context, null);
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        super.draw(canvas);
        this.a.a(canvas);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final View focusSearch(View view, int i) {
        return this.a.w ? FocusFinder.getInstance().findNextFocus(this, view, i) : super.focusSearch(view, i);
    }

    @Override // android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return "com.android.car.ui.rotary.FocusArea";
    }

    @Override // spi.o4
    public View getDefaultFocusView() {
        return this.a.q;
    }

    @Override // spi.o4
    @NonNull
    @VisibleForTesting
    public y3 getHelper() {
        return this.a;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.a.g();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        this.a.h();
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.a.i(canvas);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.a.j();
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        this.a.k(accessibilityNodeInfo);
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.a.l();
    }

    @Override // android.view.ViewGroup
    public final boolean onRequestFocusInDescendants(int i, Rect rect) {
        return isInTouchMode() ? super.onRequestFocusInDescendants(i, rect) : this.a.m();
    }

    @Override // android.view.View
    public final void onWindowFocusChanged(boolean z) {
        if (this.a.n(hasWindowFocus())) {
            return;
        }
        super.onWindowFocusChanged(z);
    }

    @Override // android.view.View
    public final boolean performAccessibilityAction(int i, Bundle bundle) {
        return this.a.d(i) ? this.a.o(i, bundle) : super.performAccessibilityAction(i, bundle);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean restoreDefaultFocus() {
        return this.a.p();
    }

    public void setDefaultFocus(@NonNull View view) {
        this.a.q = view;
    }

    public void setDefaultFocusOverridesHistory(boolean z) {
        this.a.r = z;
    }

    public void setWrapAround(boolean z) {
        this.a.w = z;
    }

    public ConstraintFocusArea(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ConstraintFocusArea(Context context, @Nullable AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public ConstraintFocusArea(Context context, @Nullable AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.a = new y3(this, attributeSet);
    }
}
