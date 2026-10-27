package com.ts.lib.car.hardware.doorlock;

import com.ts.lib.car.CarNotConnectedException;

/* JADX INFO: loaded from: classes.dex */
public interface CarVendorDoorlockManager {
    public static final int ID_ALL_DOORS_LOCK = 557856519;
    public static final int ID_DOOR_LOCK_AREA_BACK = 557856513;
    public static final int ID_DOOR_LOCK_AREA_BACK_STATUSR = 557856525;
    public static final int ID_DOOR_LOCK_AREA_CHILDLOCK_LEFT = 557856518;
    public static final int ID_DOOR_LOCK_AREA_CHILDLOCK_LEFTR = 557856512;
    public static final int ID_DOOR_LOCK_AREA_CHILDLOCK_RIGHT = 557856526;
    public static final int ID_DOOR_LOCK_AREA_CHILDLOCK_RIGHT_STATUSS = 557856520;
    public static final int ID_DOOR_LOCK_AREA_LEFT_FRONT = 557856514;
    public static final int ID_DOOR_LOCK_AREA_LEFT_FRONT_STATUSR = 557856521;
    public static final int ID_DOOR_LOCK_AREA_LEFT_REAR = 557856516;
    public static final int ID_DOOR_LOCK_AREA_LEFT_REAR_STATUSR = 557856523;
    public static final int ID_DOOR_LOCK_AREA_RIGHT_FRONT = 557856515;
    public static final int ID_DOOR_LOCK_AREA_RIGHT_FRONT_STATUSR = 557856522;
    public static final int ID_DOOR_LOCK_AREA_RIGHT_REAR = 557856517;
    public static final int ID_DOOR_LOCK_AREA_RIGHT_REAR_STATUSR = 557856524;
    public static final int ID_DOOR_LOCK_COCKPIT_HATCH_STATE_R = 557856531;
    public static final int ID_LEFT_CHILD_LOCK_STATE_R = 557856529;
    public static final int ID_LOCK_HEAT_PROTECTED_STATE_R = 557856528;
    public static final int ID_REAR_WINDOW_LOCK_S = 557856527;
    public static final int ID_RIGHT_CHILD_LOCK_STATE_R = 557856530;
    public static final String PERSIST_DEBUG_LOGGER = "persist.car.vendor.log.doorlock";
    public static final String TAG = "CAR.VENDOR.DOORLOCK";

    public interface OnCarDoorlockEventListener {
        void onDoorlockErrorEvent(int i, int i2);

        void onDoorlockEventChange(DoorlockEventChangeValue doorlockEventChangeValue);
    }

    void addCarDoorlockEventListener(OnCarDoorlockEventListener onCarDoorlockEventListener) throws CarNotConnectedException;

    int getDoorLockCockpitHatchStateR() throws CarNotConnectedException;

    int getDoorLockStatusAreaBack() throws CarNotConnectedException;

    int getDoorLockStatusAreaChildLockLeftR() throws CarNotConnectedException;

    int getDoorLockStatusAreaChildLockRight() throws CarNotConnectedException;

    int getDoorLockStatusAreaLeftFront() throws CarNotConnectedException;

    int getDoorLockStatusAreaRightRear() throws CarNotConnectedException;

    int getDoorLockStatusLeftRear() throws CarNotConnectedException;

    int getDoorLockStatusRightFront() throws CarNotConnectedException;

    int getDoorStatusAreaBackStatusR() throws CarNotConnectedException;

    int getDoorStatusAreaLeftFrontStatusR() throws CarNotConnectedException;

    int getDoorStatusAreaLeftRearStatusR() throws CarNotConnectedException;

    int getDoorStatusAreaRightFrontStatusR() throws CarNotConnectedException;

    int getDoorStatusAreaRightRearStatusR() throws CarNotConnectedException;

    int getLeftChildLockStateR() throws CarNotConnectedException;

    int getLockHeatProtectedStateR() throws CarNotConnectedException;

    int getRightChildLockStateR() throws CarNotConnectedException;

    void removeCarDoorlockEventListener(OnCarDoorlockEventListener onCarDoorlockEventListener) throws CarNotConnectedException;

    void setAllDoorsLock(int i) throws CarNotConnectedException;

    void setDoorLockStatusAreaChildlockLeftS(int i) throws CarNotConnectedException;

    void setDoorLockStatusAreaChildlockRightStatusS(int i) throws CarNotConnectedException;

    void setRearWindowLockS(int i) throws CarNotConnectedException;
}
