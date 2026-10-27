package org.chromium.components.translate;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.material.tabs.TabLayout;
import defpackage.h21;

/* JADX INFO: loaded from: classes3.dex */
public class TranslateTabLayout extends TabLayout {
    public TabLayout.g a0;
    public ObjectAnimator b0;
    public final int c0;
    public final int d0;

    public class a extends AnimatorListenerAdapter {
        public a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            TranslateTabLayout.this.b0 = null;
        }
    }

    @SuppressLint({"CustomViewStyleable"})
    public TranslateTabLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.TabLayout, 0, R$style.Widget_MaterialComponents_TabLayout);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.TabLayout_tabPadding, 0);
        this.d0 = dimensionPixelSize;
        this.c0 = dimensionPixelSize;
        this.c0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.TabLayout_tabPaddingStart, dimensionPixelSize);
        this.d0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.TabLayout_tabPaddingEnd, dimensionPixelSize);
    }

    private int getTabsTotalWidth() {
        int width = 0;
        for (int i = 0; i < getTabCount(); i++) {
            width += k(i) == null ? 0 : k(i).f.getWidth() + this.c0 + this.d0;
        }
        return width;
    }

    @Override // com.google.android.material.tabs.TabLayout
    public final void b(TabLayout.g gVar, int i, boolean z) {
        if (!(gVar.f instanceof TranslateTabContent)) {
            throw new IllegalArgumentException();
        }
        super.b(gVar, i, z);
    }

    @Override // com.google.android.material.tabs.TabLayout
    public final void d(TabLayout.g gVar, boolean z) {
        if (!(gVar.f instanceof TranslateTabContent)) {
            throw new IllegalArgumentException();
        }
        super.d(gVar, z);
    }

    @Override // com.google.android.material.tabs.TabLayout, android.widget.HorizontalScrollView, android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.a0 != null) {
            return true;
        }
        ObjectAnimator objectAnimator = this.b0;
        if (objectAnimator != null) {
            objectAnimator.end();
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public final void v() {
        if (1 >= getTabCount() || this.a0 != null) {
            return;
        }
        TabLayout.g gVarK = k(1);
        this.a0 = gVarK;
        View view = gVarK.f;
        if (view instanceof TranslateTabContent) {
            TranslateTabContent translateTabContent = (TranslateTabContent) view;
            translateTabContent.e.setVisibility(4);
            translateTabContent.f.setVisibility(0);
        }
    }

    @SuppressLint({"ObjectAnimatorBinding"})
    public final void w() {
        int tabsTotalWidth = getTabsTotalWidth() - getWidth();
        if (tabsTotalWidth <= 0) {
            tabsTotalWidth = 0;
        }
        if (tabsTotalWidth == 0) {
            return;
        }
        int[] iArr = new int[1];
        if (getLayoutDirection() == 1) {
            tabsTotalWidth = 0;
        }
        iArr[0] = tabsTotalWidth;
        ObjectAnimator objectAnimatorOfInt = ObjectAnimator.ofInt(this, "scrollX", iArr);
        this.b0 = objectAnimatorOfInt;
        objectAnimatorOfInt.setStartDelay(1000L);
        this.b0.setDuration(300L);
        this.b0.setInterpolator(h21.a);
        this.b0.addListener(new a());
        this.b0.start();
    }
}
