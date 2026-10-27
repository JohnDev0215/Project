package com.android.car.ui.recyclerview;

import android.content.Context;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import com.android.car.ui.R;

/* JADX INFO: loaded from: classes2.dex */
public class OnContinuousScrollListener implements View.OnTouchListener {
    private final int mInitialDelay;
    private boolean mIsLongPressed;
    private final View.OnClickListener mOnClickListener;
    private final int mRepeatInterval;
    private View mTouchedView;
    private final Handler mHandler = new Handler();
    private final Runnable mPeriodicRunnable = new Runnable() { // from class: com.android.car.ui.recyclerview.OnContinuousScrollListener.1
        @Override // java.lang.Runnable
        public void run() {
            if (OnContinuousScrollListener.this.mTouchedView.isEnabled()) {
                OnContinuousScrollListener.this.mHandler.postDelayed(this, OnContinuousScrollListener.this.mRepeatInterval);
                OnContinuousScrollListener.this.mOnClickListener.onClick(OnContinuousScrollListener.this.mTouchedView);
                OnContinuousScrollListener.this.mIsLongPressed = true;
                return;
            }
            OnContinuousScrollListener.this.mIsLongPressed = false;
        }
    };

    public OnContinuousScrollListener(Context context, View.OnClickListener clickListener) {
        this.mInitialDelay = context.getResources().getInteger(R.integer.car_ui_scrollbar_longpress_initial_delay);
        int integer = context.getResources().getInteger(R.integer.car_ui_scrollbar_longpress_repeat_interval);
        this.mRepeatInterval = integer;
        if (this.mInitialDelay < 0 || integer < 0) {
            throw new IllegalArgumentException("negative intervals are not allowed");
        }
        this.mOnClickListener = clickListener;
    }

    public void cancelPendingScroll() {
        this.mHandler.removeCallbacks(this.mPeriodicRunnable);
        this.mIsLongPressed = false;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        this.mTouchedView = view;
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mHandler.removeCallbacks(this.mPeriodicRunnable);
            this.mHandler.postDelayed(this.mPeriodicRunnable, this.mInitialDelay);
            this.mTouchedView.setPressed(true);
            return true;
        }
        if (action != 1 && action != 3) {
            return false;
        }
        if (!this.mIsLongPressed) {
            this.mOnClickListener.onClick(view);
        }
        this.mHandler.removeCallbacks(this.mPeriodicRunnable);
        this.mTouchedView.setPressed(false);
        this.mIsLongPressed = false;
        return true;
    }
}
