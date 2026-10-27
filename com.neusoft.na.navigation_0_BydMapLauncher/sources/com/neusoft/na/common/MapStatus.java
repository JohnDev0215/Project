package com.neusoft.na.common;

import android.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MapStatus.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\u0006J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0006J\u000e\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\bJ\u0006\u0010\u000f\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/neusoft/na/common/MapStatus;", "", "()V", "TAG", "", "dayNightMode", "", "dayNightModeChangedCallback", "Lcom/neusoft/na/common/DayNightModeChangedCallback;", "getDayNightMode", "setDayNightMode", "", ServiceBase.PAYLOAD_MODE, "setDayNightModeChanged", "callback", "unSetDayNightModeChanged", "common_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class MapStatus {
    public static final MapStatus INSTANCE = new MapStatus();
    private static final String TAG = "XJMapStatus";
    private static int dayNightMode = 1;
    private static DayNightModeChangedCallback dayNightModeChangedCallback;

    private MapStatus() {
    }

    public final void setDayNightModeChanged(DayNightModeChangedCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        dayNightModeChangedCallback = callback;
    }

    public final void unSetDayNightModeChanged() {
        dayNightModeChangedCallback = null;
    }

    public final void setDayNightMode(int mode) {
        Log.i(TAG, "setdayNightMode::" + mode);
        dayNightMode = mode;
        DayNightModeChangedCallback dayNightModeChangedCallback2 = dayNightModeChangedCallback;
        if (dayNightModeChangedCallback2 != null) {
            dayNightModeChangedCallback2.changed(mode);
        }
    }

    public final int getDayNightMode() {
        Log.i(TAG, "getdayNightMode::" + dayNightMode);
        return dayNightMode;
    }
}
