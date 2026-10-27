package com.android.car.apps.common.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public final class PagedSmoothScroller extends LinearSmoothScroller {
    public final DecelerateInterpolator a;

    public PagedSmoothScroller(Context context) {
        super(context);
        this.a = new DecelerateInterpolator(1.8f);
    }

    @Override // androidx.recyclerview.widget.LinearSmoothScroller
    public final float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
        return 150.0f / displayMetrics.densityDpi;
    }

    @Override // androidx.recyclerview.widget.LinearSmoothScroller
    public final int calculateTimeForDeceleration(int i) {
        return (int) Math.ceil(calculateTimeForScrolling(i) / 0.45f);
    }

    @Override // androidx.recyclerview.widget.LinearSmoothScroller
    public final int getVerticalSnapPreference() {
        return -1;
    }

    @Override // androidx.recyclerview.widget.LinearSmoothScroller, androidx.recyclerview.widget.RecyclerView.SmoothScroller
    public final void onTargetFound(View view, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
        int iCalculateTimeForDeceleration;
        int iCalculateDyToMakeVisible = calculateDyToMakeVisible(view, -1);
        if (iCalculateDyToMakeVisible != 0 && (iCalculateTimeForDeceleration = calculateTimeForDeceleration(iCalculateDyToMakeVisible)) > 0) {
            action.update(0, -iCalculateDyToMakeVisible, iCalculateTimeForDeceleration, this.a);
        }
    }
}
