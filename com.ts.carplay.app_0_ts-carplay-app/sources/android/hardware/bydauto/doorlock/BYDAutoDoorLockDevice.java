package android.hardware.bydauto.doorlock;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.doorlock.tsimpl.DoorlockManagerImpl;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoDoorLockDevice extends AbsBYDAutoDevice {
    public static final int DOOR_LOCK_AREA_BACK = 5;
    public static final int DOOR_LOCK_AREA_CHILDLOCK_LEFT = 6;
    public static final int DOOR_LOCK_AREA_CHILDLOCK_RIGHT = 7;
    public static final int DOOR_LOCK_AREA_LEFT_FRONT = 1;
    public static final int DOOR_LOCK_AREA_LEFT_REAR = 2;
    public static final int DOOR_LOCK_AREA_RIGHT_FRONT = 3;
    public static final int DOOR_LOCK_AREA_RIGHT_REAR = 4;
    public static final int DOOR_LOCK_COMMAND_BUSY = -2147482647;
    public static final int DOOR_LOCK_COMMAND_FAILED = -2147482648;
    public static final int DOOR_LOCK_COMMAND_INVALID_VALUE = -2147482645;
    public static final int DOOR_LOCK_COMMAND_SUCCESS = 0;
    public static final int DOOR_LOCK_COMMAND_TIMEOUT = -2147482646;
    public static final int DOOR_LOCK_STATE_CLOSED = 1;
    public static final int DOOR_LOCK_STATE_INVALID = 0;
    public static final int DOOR_LOCK_STATE_LOCK = 1;
    public static final int DOOR_LOCK_STATE_OPENED = 2;
    public static final int DOOR_LOCK_STATE_UNLOCK = 2;
    public static final String PERMISSION_BYDAUTO_DOORLOCK_COMMON = "android.permission.BYDAUTO_DOOR_LOCK_COMMON";
    public static final String PERMISSION_BYDAUTO_DOORLOCK_GET = "android.permission.BYDAUTO_DOOR_LOCK_GET";
    public static final String PERMISSION_BYDAUTO_DOORLOCK_SET = "android.permission.BYDAUTO_DOOR_LOCK_SET";
    public static final int SETTING_COMMAND_BUSY = 1001;
    public static final int SETTING_COMMAND_FAILED = 1000;
    public static final int SETTING_COMMAND_INVALID = 1003;
    public static final int SETTING_COMMAND_SUCCESS = 0;
    public static final int SETTING_COMMAND_TIMEOUT = 1002;
    private static final String TAG = "BYDAutoDoorLockDevice";
    private static BYDAutoDoorLockDevice sDevice;
    private final DoorlockManagerImpl mDoorlockManagerImpl;

    BYDAutoDoorLockDevice(Context con) {
        super(con.getApplicationContext());
        this.mDoorlockManagerImpl = DoorlockManagerImpl.getInstance(con.getApplicationContext());
    }

    public static synchronized BYDAutoDoorLockDevice getInstance(Context con) {
        if (sDevice == null) {
            sDevice = new BYDAutoDoorLockDevice(con);
        }
        return sDevice;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return PERMISSION_BYDAUTO_DOORLOCK_SET;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return PERMISSION_BYDAUTO_DOORLOCK_GET;
    }

    public int getDoorLockStatus(int area) {
        return this.mDoorlockManagerImpl.getDoorLockStatus(area);
    }

    public int getDoorStatus(int area) {
        return this.mDoorlockManagerImpl.getDoorStatus(area);
    }

    public int setDoorLockStatus(int areas, int state) {
        return this.mDoorlockManagerImpl.setDoorLockStatus(areas, state);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int device_type, int event_type, int value, Object data) {
        return false;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return 0;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    public void registerListener(AbsBYDAutoDoorLockListener listener) {
        this.mDoorlockManagerImpl.registerListener(listener);
    }

    public void registerListener(AbsBYDAutoDoorLockListener listener, int[] featureIDs) {
        this.mDoorlockManagerImpl.registerListener(listener, featureIDs);
    }

    public void unregisterListener(AbsBYDAutoDoorLockListener listener) {
        this.mDoorlockManagerImpl.unregisterListener(listener);
    }

    public int getControlLockConfig() {
        return this.mDoorlockManagerImpl.getControlLockConfig();
    }

    public int setAllDoorsLock(int state) {
        return this.mDoorlockManagerImpl.setAllDoorsLock(state);
    }

    public int setRearWindowLock(int state) {
        return this.mDoorlockManagerImpl.setRearWindowLock(state);
    }

    public int getLockHeatProtectedState() {
        return this.mDoorlockManagerImpl.getLockHeatProtectedStateR();
    }

    public int getLeftChildLockState() {
        return this.mDoorlockManagerImpl.getLeftChildLockState();
    }

    public int getRightChildLockState() {
        return this.mDoorlockManagerImpl.getRightChildLockState();
    }

    public int getDoorLockCockpitHatchState() {
        return this.mDoorlockManagerImpl.getDoorLockCockpitHatchState();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] eventTypes, Class<?> type) {
        return this.mDoorlockManagerImpl.get(eventTypes, type);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int set(int[] eventTypes, BYDAutoEventValue eventValue) {
        return this.mDoorlockManagerImpl.set(eventTypes, eventValue);
    }
}
