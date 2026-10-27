package com.ts.car.timesync;

import android.content.Context;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class ClockSourceFactory {
    private static final String LOCATION = "location";
    private static final String MCU = "mcu";
    private static final String TAG = "ClockSourceFactory";
    private Context mContext;
    private ArrayList<ClockSourceBase> mSourceList = new ArrayList<>();

    public ClockSourceFactory(Context context) {
        this.mContext = context;
    }

    public void createClockSource(String type, int priority) {
        ClockSourceBase source = null;
        if (LOCATION.equals(type)) {
            source = new LocationClockSource(this.mContext);
        } else if (MCU.equals(type)) {
            source = new McuClockSource(this.mContext);
        }
        if (source == null) {
            return;
        }
        source.setPriority(priority);
        this.mSourceList.add(source);
    }

    public McuClockSource getMcuClockSource() {
        LogUtils.debug(TAG, "getMcuClockSource()");
        for (ClockSourceBase source : this.mSourceList) {
            if (source instanceof McuClockSource) {
                return (McuClockSource) source;
            }
        }
        return null;
    }

    public void release() {
        for (ClockSourceBase source : this.mSourceList) {
            source.release();
        }
    }
}
