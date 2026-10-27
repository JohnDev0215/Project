package com.byd.mvp.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

/* JADX INFO: loaded from: classes2.dex */
public class BydHScrollView extends HorizontalScrollView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3778a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile int f3779b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f3780c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3781d;

    public BydHScrollView(Context context) {
        super(context);
        this.f3778a = 0;
        this.f3779b = 0;
        this.f3780c = 0;
        this.f3781d = 0;
        a(context);
    }

    public final void a(Context context) {
        this.f3778a = getResources().getDisplayMetrics().widthPixels;
    }

    public final void b(int i, int i2) {
        if (this.f3779b - this.f3778a <= 0) {
            return;
        }
        int i3 = (i + 1) - (i2 / ((this.f3779b / this.f3778a) + 1));
        if (i2 <= 0) {
            return;
        }
        int i4 = (this.f3779b / i2) * i3;
        if (i4 < 0) {
            i4 = 0;
        }
        super.scrollTo(i4, (int) getY());
    }

    public int getmWidth() {
        return this.f3779b;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        b(this.f3780c, this.f3781d);
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
        this.f3779b = measuredWidth;
    }

    public BydHScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3778a = 0;
        this.f3779b = 0;
        this.f3780c = 0;
        this.f3781d = 0;
        a(context);
    }

    public BydHScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3778a = 0;
        this.f3779b = 0;
        this.f3780c = 0;
        this.f3781d = 0;
        a(context);
    }
}
