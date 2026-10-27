package com.ts.car.timesync;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class ClockSourceBase {
    public Context mContext;
    public int mPriority;

    public ClockSourceBase(Context context) {
        this.mContext = context;
    }

    public void setPriority(int priority) {
        this.mPriority = priority;
    }

    public void setTime(long time, int type) {
    }

    public void release() {
    }
}
