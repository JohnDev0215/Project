package com.byd.mvp.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

/* JADX INFO: loaded from: classes2.dex */
public class BydHScrollView extends HorizontalScrollView {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f710b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public volatile int f711c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f712d;
    public int e;

    public BydHScrollView(Context context) {
        super(context);
        this.f710b = 0;
        this.f711c = 0;
        this.f712d = 0;
        this.e = 0;
        a(context);
    }

    public final void a(Context context) {
        this.f710b = getResources().getDisplayMetrics().widthPixels;
    }

    public final void b(int i, int i2) {
        if (this.f711c - this.f710b <= 0) {
            return;
        }
        int i3 = (i + 1) - (i2 / ((this.f711c / this.f710b) + 1));
        if (i2 <= 0) {
            return;
        }
        int i4 = (this.f711c / i2) * i3;
        if (i4 < 0) {
            i4 = 0;
        }
        super.scrollTo(i4, (int) getY());
    }

    public int getmWidth() {
        return this.f711c;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        b(this.f712d, this.e);
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        ViewGroup viewGroup = (ViewGroup) getChildAt(0);
        int childCount = viewGroup.getChildCount();
        if (childCount <= 0) {
            return;
        }
        int measuredWidth = 0;
        for (int i3 = 0; i3 < childCount; i3++) {
            measuredWidth += viewGroup.getChildAt(i3).getMeasuredWidth();
        }
        this.f711c = measuredWidth;
    }

    public BydHScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f710b = 0;
        this.f711c = 0;
        this.f712d = 0;
        this.e = 0;
        a(context);
    }

    public BydHScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f710b = 0;
        this.f711c = 0;
        this.f712d = 0;
        this.e = 0;
        a(context);
    }
}
