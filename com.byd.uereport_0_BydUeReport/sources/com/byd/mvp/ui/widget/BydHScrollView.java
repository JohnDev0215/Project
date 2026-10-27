package com.byd.mvp.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

/* JADX INFO: loaded from: classes2.dex */
public class BydHScrollView extends HorizontalScrollView {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2846d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile int f2847e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2848f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f2849g;

    public BydHScrollView(Context context) {
        super(context);
        this.f2846d = 0;
        this.f2847e = 0;
        this.f2848f = 0;
        this.f2849g = 0;
        a(context);
    }

    public final void a(Context context) {
        this.f2846d = getResources().getDisplayMetrics().widthPixels;
    }

    public final void b(int i8, int i9) {
        if (this.f2847e - this.f2846d <= 0) {
            return;
        }
        int i10 = (i8 + 1) - (i9 / ((this.f2847e / this.f2846d) + 1));
        if (i9 <= 0) {
            return;
        }
        int i11 = (this.f2847e / i9) * i10;
        if (i11 < 0) {
            i11 = 0;
        }
        super.scrollTo(i11, (int) getY());
    }

    public int getmWidth() {
        return this.f2847e;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        super.onLayout(z7, i8, i9, i10, i11);
        b(this.f2848f, this.f2849g);
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    public void onMeasure(int i8, int i9) {
        super.onMeasure(i8, i9);
        ViewGroup viewGroup = (ViewGroup) getChildAt(0);
        int childCount = viewGroup.getChildCount();
        if (childCount <= 0) {
            return;
        }
        int measuredWidth = 0;
        for (int i10 = 0; i10 < childCount; i10++) {
            measuredWidth += viewGroup.getChildAt(i10).getMeasuredWidth();
        }
        this.f2847e = measuredWidth;
    }

    public BydHScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2846d = 0;
        this.f2847e = 0;
        this.f2848f = 0;
        this.f2849g = 0;
        a(context);
    }

    public BydHScrollView(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f2846d = 0;
        this.f2847e = 0;
        this.f2848f = 0;
        this.f2849g = 0;
        a(context);
    }
}
