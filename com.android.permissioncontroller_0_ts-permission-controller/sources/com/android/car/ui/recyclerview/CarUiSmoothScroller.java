package com.android.car.ui.recyclerview;

import android.content.Context;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.ui.R;
import com.android.car.ui.utils.CarUiUtils;

/* JADX INFO: loaded from: classes7.dex */
public class CarUiSmoothScroller extends LinearSmoothScroller {
    float mDecelerationTimeDivisor;
    int mDensityDpi;
    Interpolator mInterpolator;
    float mMillisecondsPerInch;
    float mMillisecondsPerPixel;

    public CarUiSmoothScroller(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        this.mMillisecondsPerInch = CarUiUtils.getFloat(context.getResources(), R.dimen.car_ui_scrollbar_milliseconds_per_inch);
        this.mDecelerationTimeDivisor = CarUiUtils.getFloat(context.getResources(), R.dimen.car_ui_scrollbar_deceleration_times_divisor);
        this.mInterpolator = new DecelerateInterpolator(CarUiUtils.getFloat(context.getResources(), R.dimen.car_ui_scrollbar_decelerate_interpolator_factor));
        int i = context.getResources().getDisplayMetrics().densityDpi;
        this.mDensityDpi = i;
        this.mMillisecondsPerPixel = this.mMillisecondsPerInch / i;
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
    protected int calculateTimeForScrolling(int dx) {
        return (int) Math.ceil(Math.abs(dx) * this.mMillisecondsPerPixel);
    }

    @Override // androidx.recyclerview.widget.LinearSmoothScroller
    protected int calculateTimeForDeceleration(int dx) {
        return (int) Math.ceil(calculateTimeForScrolling(dx) / this.mDecelerationTimeDivisor);
    }
}
