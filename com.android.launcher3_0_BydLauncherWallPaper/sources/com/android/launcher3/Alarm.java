package com.android.launcher3;

import android.os.Handler;
import android.os.SystemClock;

/* JADX INFO: loaded from: classes4.dex */
public class Alarm implements Runnable {
    private OnAlarmListener mAlarmListener;
    private long mAlarmTriggerTime;
    private boolean mWaitingForCallback;
    private boolean mAlarmPending = false;
    private Handler mHandler = new Handler();

    public void setOnAlarmListener(OnAlarmListener alarmListener) {
        this.mAlarmListener = alarmListener;
    }

    public void setAlarm(long millisecondsInFuture) {
        long jUptimeMillis = SystemClock.uptimeMillis();
        this.mAlarmPending = true;
        long j = this.mAlarmTriggerTime;
        long j2 = millisecondsInFuture + jUptimeMillis;
        this.mAlarmTriggerTime = j2;
        if (this.mWaitingForCallback && j > j2) {
            this.mHandler.removeCallbacks(this);
            this.mWaitingForCallback = false;
        }
        if (this.mWaitingForCallback) {
            return;
        }
        this.mHandler.postDelayed(this, this.mAlarmTriggerTime - jUptimeMillis);
        this.mWaitingForCallback = true;
    }

    public void cancelAlarm() {
        this.mAlarmPending = false;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.mWaitingForCallback = false;
        if (this.mAlarmPending) {
            long jUptimeMillis = SystemClock.uptimeMillis();
            long j = this.mAlarmTriggerTime;
            if (j > jUptimeMillis) {
                this.mHandler.postDelayed(this, Math.max(0L, j - jUptimeMillis));
                this.mWaitingForCallback = true;
                return;
            }
            this.mAlarmPending = false;
            OnAlarmListener onAlarmListener = this.mAlarmListener;
            if (onAlarmListener != null) {
                onAlarmListener.onAlarm(this);
            }
        }
    }

    public boolean alarmPending() {
        return this.mAlarmPending;
    }
}
