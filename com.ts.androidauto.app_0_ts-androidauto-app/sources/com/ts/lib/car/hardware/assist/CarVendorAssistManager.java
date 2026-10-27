package com.ts.lib.car.hardware.assist;

import com.ts.lib.car.CarNotConnectedException;

/* JADX INFO: loaded from: classes2.dex */
public interface CarVendorAssistManager {
    public static final int ID_HW_RTCTIME = 557969440;
    public static final String PERSIST_DEBUG_LOGGER = "persist.car.vendor.log.assist";
    public static final String TAG = "CAR.VENDOR.ASSIST";

    public interface OnCarAssistEventListener {
        void onAssistErrorEvent(int i, int i2);

        void onAssistEventChange(AssistEventChangeValue assistEventChangeValue);
    }

    void addCarAssistEventListener(OnCarAssistEventListener onCarAssistEventListener) throws CarNotConnectedException;

    int[] getRtcTime() throws CarNotConnectedException;

    void removeCarAssistEventListener(OnCarAssistEventListener onCarAssistEventListener) throws CarNotConnectedException;

    void setRtcTime(int[] iArr) throws CarNotConnectedException;
}
