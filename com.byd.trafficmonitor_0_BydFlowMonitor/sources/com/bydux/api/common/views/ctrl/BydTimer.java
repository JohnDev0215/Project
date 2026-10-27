package com.bydux.api.common.views.ctrl;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class BydTimer extends View {
    private final String TAG;
    private final int TIME_UP;
    private long mDelayMs;
    private Handler mHandler;
    private BydTimerListener mListener;
    private boolean mTimerWorking;
    private long mTotalMs;

    public interface BydTimerListener {
        void onTimeUp(View view);
    }

    public BydTimer(Context context) {
        super(context);
        this.TAG = getClass().getSimpleName();
        this.mListener = null;
        this.mTotalMs = 0L;
        this.mDelayMs = 0L;
        this.mTimerWorking = false;
        this.TIME_UP = 1000;
        this.mHandler = new Handler() { // from class: com.bydux.api.common.views.ctrl.BydTimer.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == 1000 && BydTimer.this.mTimerWorking) {
                    if (BydTimer.this.mListener != null) {
                        BydTimer.this.mListener.onTimeUp(BydTimer.this);
                    }
                    BydTimer.this.mHandler.sendEmptyMessageDelayed(1000, BydTimer.this.mTotalMs);
                }
            }
        };
    }

    public boolean isTimerWorking() {
        return this.mTimerWorking;
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        startTimer(this.mTotalMs, this.mDelayMs);
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mHandler.removeMessages(1000);
        this.mTimerWorking = false;
    }

    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        setMeasuredDimension(0, 0);
    }

    public void setBydTimerListener(BydTimerListener bydTimerListener) {
        this.mListener = bydTimerListener;
    }

    public void startTimer(long j) {
        startTimer(j, j);
    }

    public void stopTimer() {
        startTimer(0L);
    }

    public void startTimer(long j, long j2) {
        this.mTotalMs = j;
        this.mDelayMs = j2;
        this.mHandler.removeMessages(1000);
        this.mTimerWorking = false;
        if (this.mTotalMs > 0) {
            this.mHandler.sendEmptyMessageDelayed(1000, this.mDelayMs);
            this.mTimerWorking = true;
        }
    }

    public BydTimer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = getClass().getSimpleName();
        this.mListener = null;
        this.mTotalMs = 0L;
        this.mDelayMs = 0L;
        this.mTimerWorking = false;
        this.TIME_UP = 1000;
        this.mHandler = new Handler() { // from class: com.bydux.api.common.views.ctrl.BydTimer.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == 1000 && BydTimer.this.mTimerWorking) {
                    if (BydTimer.this.mListener != null) {
                        BydTimer.this.mListener.onTimeUp(BydTimer.this);
                    }
                    BydTimer.this.mHandler.sendEmptyMessageDelayed(1000, BydTimer.this.mTotalMs);
                }
            }
        };
    }
}
