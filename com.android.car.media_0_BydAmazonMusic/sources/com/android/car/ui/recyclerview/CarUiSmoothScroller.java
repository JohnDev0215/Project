package com.android.car.ui.recyclerview;

import android.content.Context;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import androidx.annotation.VisibleForTesting;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.ui.R$dimen;
import spi.b2;

/* JADX INFO: loaded from: classes2.dex */
public class CarUiSmoothScroller extends LinearSmoothScroller {

    @VisibleForTesting
    public float a;

    @VisibleForTesting
    public float b;

    @VisibleForTesting
    public float c;

    @VisibleForTesting
    public DecelerateInterpolator d;

    public CarUiSmoothScroller(Context context) {
        super(context);
        this.a = b2.g(context.getResources(), R$dimen.car_ui_scrollbar_milliseconds_per_inch);
        this.b = b2.g(context.getResources(), R$dimen.car_ui_scrollbar_deceleration_times_divisor);
        this.d = new DecelerateInterpolator(b2.g(context.getResources(), R$dimen.car_ui_scrollbar_decelerate_interpolator_factor));
        this.c = this.a / context.getResources().getDisplayMetrics().densityDpi;
    }

    @Override // androidx.recyclerview.widget.LinearSmoothScroller
    public final int calculateTimeForDeceleration(int i) {
        return (int) Math.ceil(calculateTimeForScrolling(i) / this.b);
    }

    @Override // androidx.recyclerview.widget.LinearSmoothScroller
    public final int calculateTimeForScrolling(int i) {
        return (int) Math.ceil(Math.abs(i) * this.c);
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
            action.update(0, -iCalculateDyToMakeVisible, iCalculateTimeForDeceleration, this.d);
        }
    }
}
