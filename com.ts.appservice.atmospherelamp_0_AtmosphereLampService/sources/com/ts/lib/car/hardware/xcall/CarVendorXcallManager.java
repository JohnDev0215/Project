package com.ts.lib.car.hardware.xcall;

import com.ts.lib.car.CarNotConnectedException;

/* JADX INFO: loaded from: classes3.dex */
public interface CarVendorXcallManager {
    public static final int ID_CALL_BACKMODER = 557872644;
    public static final int ID_CALL_STATUSR = 557872643;
    public static final int ID_CALL_TYPER = 557872642;
    public static final int ID_CALL_TYPES = 557872640;
    public static final int ID_REQUEST_TYPES = 557872641;
    public static final String PERSIST_DEBUG_LOGGER = "persist.car.vendor.log.xcall";
    public static final String TAG = "CAR.VENDOR.XCALL";

    public interface OnCarXcallEventListener {
        void onXcallErrorEvent(int i, int i2);

        void onXcallEventChange(XcallEventChangeValue xcallEventChangeValue);
    }

    void addCarXcallEventListener(OnCarXcallEventListener onCarXcallEventListener) throws CarNotConnectedException;

    int getCallBackModeR() throws CarNotConnectedException;

    int getCallStatusR() throws CarNotConnectedException;

    int getCallTypeR() throws CarNotConnectedException;

    void removeCarXcallEventListener(OnCarXcallEventListener onCarXcallEventListener) throws CarNotConnectedException;

    void setCallTypeS(int i) throws CarNotConnectedException;

    void setRequestTypeS(int i) throws CarNotConnectedException;
}
