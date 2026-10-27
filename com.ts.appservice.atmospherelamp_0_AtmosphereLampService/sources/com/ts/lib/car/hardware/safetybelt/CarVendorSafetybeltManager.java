package com.ts.lib.car.hardware.safetybelt;

import com.ts.lib.car.CarNotConnectedException;

/* JADX INFO: loaded from: classes3.dex */
public interface CarVendorSafetybeltManager {
    public static final int ID_DRIVER_BELT_STATUS_R = 557860875;
    public static final int ID_MAIN_SEAT_STATE_R = 557860876;
    public static final int ID_SAFETY_BELT_STATUS_DEPUTY = 557860865;
    public static final int ID_SAFETY_BELT_STATUS_LEFT_THREE_REAR = 557860869;
    public static final int ID_SAFETY_BELT_STATUS_LEFT_TWO_REAR = 557860866;
    public static final int ID_SAFETY_BELT_STATUS_MAIN = 557860864;
    public static final int ID_SAFETY_BELT_STATUS_MID_THREE_REAR = 557860870;
    public static final int ID_SAFETY_BELT_STATUS_MID_TWO_REAR = 557860867;
    public static final int ID_SAFETY_BELT_STATUS_RIGHT_THREE_REAR = 557860871;
    public static final int ID_SAFETY_BELT_STATUS_RIGHT_TWO_REAR = 557860868;
    public static final String PERSIST_DEBUG_LOGGER = "persist.car.vendor.log.safetybelt";
    public static final String TAG = "CAR.VENDOR.SAFETYBELT";

    public interface OnCarSafetybeltEventListener {
        void onSafetybeltErrorEvent(int i, int i2);

        void onSafetybeltEventChange(SafetybeltEventChangeValue safetybeltEventChangeValue);
    }

    void addCarSafetybeltEventListener(OnCarSafetybeltEventListener onCarSafetybeltEventListener) throws CarNotConnectedException;

    int getDriverBeltStatusR() throws CarNotConnectedException;

    int getMainSeatStateR() throws CarNotConnectedException;

    int getSafetyBeltStatusDeputy() throws CarNotConnectedException;

    int getSafetyBeltStatusLeftThreeRear() throws CarNotConnectedException;

    int getSafetyBeltStatusLeftTwoRear() throws CarNotConnectedException;

    int getSafetyBeltStatusMain() throws CarNotConnectedException;

    int getSafetyBeltStatusMidThreeRear() throws CarNotConnectedException;

    int getSafetyBeltStatusMidTwoRear() throws CarNotConnectedException;

    int getSafetyBeltStatusRightThreeRear() throws CarNotConnectedException;

    int getSafetyBeltStatusRightTwoRear() throws CarNotConnectedException;

    void removeCarSafetybeltEventListener(OnCarSafetybeltEventListener onCarSafetybeltEventListener) throws CarNotConnectedException;
}
