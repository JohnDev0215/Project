package com.ts.car.timesync;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class ExtNetWorkClockSource extends ClockSourceBase {
    private static final String TAG = "ExtNetWorkClockSource";
    private static final int TYPE = 3;

    public ExtNetWorkClockSource(Context context) {
        super(context);
        this.mContext = context;
    }

    public void relesae() {
    }
}
