package com.android.car.apps.common.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public final class PagedSmoothScroller extends LinearSmoothScroller {
    private final Interpolator mInterpolator;
    private static final float MILLISECONDS_PER_INCH = 150.0f;
    private static final float DECELERATION_TIME_DIVISOR = 0.45f;

    public PagedSmoothScroller(Context context) {
        super(context);
        this.mInterpolator = new DecelerateInterpolator(1.8f);
    }

    @Override // androidx.recyclerview.widget.LinearSmoothScroller
    protected int getVerticalSnapPreference() {
        return -1;
    }

    @Override // androidx.recyclerview.widget.LinearSmoothScroller, androidx.recyclerview.widget.RecyclerView.SmoothScroller
    protected void onTargetFound(View targetView, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
        int time;
        int dy = calculateDyToMakeVisible(targetView, -1);
        if (dy != 0 && (time = calculateTimeForDeceleration(dy)) > 0) {
            action.update(0, -dy, time, this.mInterpolator);
        }
    }

    @Override // androidx.recyclerview.widget.LinearSmoothScroller
    protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
        return MILLISECONDS_PER_INCH / displayMetrics.densityDpi;
    }

    @Override // androidx.recyclerview.widget.LinearSmoothScroller
    protected int calculateTimeForDeceleration(int dx) {
        return (int) Math.ceil(calculateTimeForScrolling(dx) / DECELERATION_TIME_DIVISOR);
    }
}
