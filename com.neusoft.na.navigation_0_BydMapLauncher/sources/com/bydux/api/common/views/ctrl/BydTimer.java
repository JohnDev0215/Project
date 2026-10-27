package com.bydux.api.common.views.ctrl;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

/* JADX INFO: loaded from: classes2.dex */
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

    public void setBydTimerListener(BydTimerListener listener) {
        this.mListener = listener;
    }

    public void startTimer(long ms) {
        startTimer(ms, ms);
    }

    public void startTimer(long ms, long delay) {
        this.mTotalMs = ms;
        this.mDelayMs = delay;
        this.mHandler.removeMessages(1000);
        this.mTimerWorking = false;
        if (this.mTotalMs > 0) {
            this.mHandler.sendEmptyMessageDelayed(1000, this.mDelayMs);
            this.mTimerWorking = true;
        }
    }

    public void stopTimer() {
        startTimer(0L);
    }

    public boolean isTimerWorking() {
        return this.mTimerWorking;
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
            public void handleMessage(Message msg) {
                if (msg.what == 1000 && BydTimer.this.mTimerWorking) {
                    if (BydTimer.this.mListener != null) {
                        BydTimer.this.mListener.onTimeUp(BydTimer.this);
                    }
                    BydTimer.this.mHandler.sendEmptyMessageDelayed(1000, BydTimer.this.mTotalMs);
                }
            }
        };
    }

    public BydTimer(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.TAG = getClass().getSimpleName();
        this.mListener = null;
        this.mTotalMs = 0L;
        this.mDelayMs = 0L;
        this.mTimerWorking = false;
        this.TIME_UP = 1000;
        this.mHandler = new Handler() { // from class: com.bydux.api.common.views.ctrl.BydTimer.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                if (msg.what == 1000 && BydTimer.this.mTimerWorking) {
                    if (BydTimer.this.mListener != null) {
                        BydTimer.this.mListener.onTimeUp(BydTimer.this);
                    }
                    BydTimer.this.mHandler.sendEmptyMessageDelayed(1000, BydTimer.this.mTotalMs);
                }
            }
        };
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startTimer(this.mTotalMs, this.mDelayMs);
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mHandler.removeMessages(1000);
        this.mTimerWorking = false;
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(0, 0);
    }
}
