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

    public BYDAutoDoorLockDevice(Context context) {
        super(context.getApplicationContext());
        this.mDoorlockManagerImpl = DoorlockManagerImpl.getInstance(context.getApplicationContext());
    }

    public static synchronized BYDAutoDoorLockDevice getInstance(Context context) {
        if (sDevice == null) {
            sDevice = new BYDAutoDoorLockDevice(context);
        }
        return sDevice;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        return this.mDoorlockManagerImpl.get(iArr, cls);
    }

    public int getControlLockConfig() {
        return this.mDoorlockManagerImpl.getControlLockConfig();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    public int getDoorLockCockpitHatchState() {
        return this.mDoorlockManagerImpl.getDoorLockCockpitHatchState();
    }

    public int getDoorLockStatus(int i2) {
        return this.mDoorlockManagerImpl.getDoorLockStatus(i2);
    }

    public int getDoorStatus(int i2) {
        return this.mDoorlockManagerImpl.getDoorStatus(i2);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return PERMISSION_BYDAUTO_DOORLOCK_GET;
    }

    public int getLeftChildLockState() {
        return this.mDoorlockManagerImpl.getLeftChildLockState();
    }

    public int getLockHeatProtectedState() {
        return this.mDoorlockManagerImpl.getLockHeatProtectedStateR();
    }

    public int getRightChildLockState() {
        return this.mDoorlockManagerImpl.getRightChildLockState();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return PERMISSION_BYDAUTO_DOORLOCK_SET;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getType() {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public boolean postEvent(int i2, int i3, int i4, Object obj) {
        return false;
    }

    public void registerListener(AbsBYDAutoDoorLockListener absBYDAutoDoorLockListener) {
        this.mDoorlockManagerImpl.registerListener(absBYDAutoDoorLockListener);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int set(int[] iArr, BYDAutoEventValue bYDAutoEventValue) {
        return this.mDoorlockManagerImpl.set(iArr, bYDAutoEventValue);
    }

    public int setAllDoorsLock(int i2) {
        return this.mDoorlockManagerImpl.setAllDoorsLock(i2);
    }

    public int setDoorLockStatus(int i2, int i3) {
        return this.mDoorlockManagerImpl.setDoorLockStatus(i2, i3);
    }

    public int setRearWindowLock(int i2) {
        return this.mDoorlockManagerImpl.setRearWindowLock(i2);
    }

    public void unregisterListener(AbsBYDAutoDoorLockListener absBYDAutoDoorLockListener) {
        this.mDoorlockManagerImpl.unregisterListener(absBYDAutoDoorLockListener);
    }

    public void registerListener(AbsBYDAutoDoorLockListener absBYDAutoDoorLockListener, int[] iArr) {
        this.mDoorlockManagerImpl.registerListener(absBYDAutoDoorLockListener, iArr);
    }
}
