package com.android.launcher3.home.pageutil;

import android.content.Context;
import android.os.CountDownTimer;

/* JADX INFO: loaded from: classes4.dex */
public class CountTimer extends CountDownTimer {
    private Context context;
    private CurrentTimeListener mCurrentTimeListener;

    public interface CurrentTimeListener {
        void currentTime(long currentTime);

        void onFinish();

        void onStartTime();
    }

    public CountTimer(long millisInFuture, long countDownInterval, Context context) {
        super(millisInFuture, countDownInterval);
        this.context = context;
    }

    @Override // android.os.CountDownTimer
    public void onTick(long millisUntilFinished) {
        CurrentTimeListener currentTimeListener = this.mCurrentTimeListener;
        if (currentTimeListener != null) {
            currentTimeListener.currentTime(millisUntilFinished);
        }
    }

    @Override // android.os.CountDownTimer
    public void onFinish() {
        CurrentTimeListener currentTimeListener = this.mCurrentTimeListener;
        if (currentTimeListener != null) {
            currentTimeListener.onFinish();
        }
    }

    public void onActionMove() {
        this.mCurrentTimeListener.onStartTime();
    }

    public void addCurrentTimeListener(CurrentTimeListener currentTimeListener) {
        this.mCurrentTimeListener = currentTimeListener;
    }
}
