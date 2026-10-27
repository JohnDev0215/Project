package android.hardware.bydauto.safetybelt;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.safetybelt.tsimpl.SafetyBeltManagerImpl;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoSafetyBeltDevice extends AbsBYDAutoDevice {
    public static final int DEVICE_HAS_THE_FEATURE = 1;
    public static final int DEVICE_NOT_HAS_THE_FEATURE = 0;
    public static final int DEVICE_THE_FEATURE_LINK_ERROR = 65535;
    public static final int DEVICE_THE_FEATURE_NEVER_GET = 2;
    public static final int MESSAGE_481 = 1;
    public static final int MESSAGE_OFFLINE = 0;
    public static final int MESSAGE_ONLINE = 1;
    public static final String PERMISSION_BYDAUTO_SAFEBELT_COMMON = "android.permission.BYDAUTO_SAFETY_BELT_COMMON";
    public static final String PERMISSION_BYDAUTO_SAFEBELT_GET = "android.permission.BYDAUTO_SAFETY_BELT_GET";
    public static final String PERMISSION_BYDAUTO_SAFEBELT_SET = "android.permission.BYDAUTO_SAFETY_BELT_SET";
    public static final int SAFETY_BELT_AREA_DEPUTY = 2;
    public static final int SAFETY_BELT_AREA_MAIN = 1;
    public static final int SAFETY_BELT_AREA_SECOND_ROW_SEAT_LEFT = 3;
    public static final int SAFETY_BELT_AREA_SECOND_ROW_SEAT_MID = 5;
    public static final int SAFETY_BELT_AREA_SECOND_ROW_SEAT_RIGHT = 4;
    public static final int SAFETY_BELT_AREA_THREE_ROW_SEAT_LEFT = 6;
    public static final int SAFETY_BELT_AREA_THREE_ROW_SEAT_MID = 8;
    public static final int SAFETY_BELT_AREA_THREE_ROW_SEAT_RIGHT = 7;
    public static final int SAFETY_BELT_COMMAND_BUSY = -2147482647;
    public static final int SAFETY_BELT_COMMAND_FAILED = -2147482648;
    public static final int SAFETY_BELT_COMMAND_INVALID_VALUE = -2147482645;
    public static final int SAFETY_BELT_COMMAND_SUCCESS = 0;
    public static final int SAFETY_BELT_COMMAND_TIMEOUT = -2147482646;
    public static final int SAFETY_BELT_MSR_SWITCH_OFF = 1;
    public static final int SAFETY_BELT_MSR_SWITCH_ON = 0;
    public static final int SAFETY_BELT_PASSENGER_DEPUTY = 1;
    public static final int SAFETY_BELT_PASSENGER_SECOND_ROW_SEAT_LEFT = 2;
    public static final int SAFETY_BELT_PASSENGER_SECOND_ROW_SEAT_MID = 4;
    public static final int SAFETY_BELT_PASSENGER_SECOND_ROW_SEAT_RIGHT = 3;
    public static final int SAFETY_BELT_PASSENGER_STATE_INVALID = 2;
    public static final int SAFETY_BELT_PASSENGER_STATE_NOBODY = 0;
    public static final int SAFETY_BELT_PASSENGER_STATE_SOMEBODY = 1;
    public static final int SAFETY_BELT_REMINDER_ENABLED = 1;
    public static final int SAFETY_BELT_SBCD_INVALID = 0;
    public static final int SAFETY_BELT_SBCD_OFF = 1;
    public static final int SAFETY_BELT_SBCD_ON = 2;
    public static final int SAFETY_BELT_STATE_INVALID = 2;
    public static final int SAFETY_BELT_STATE_LOCK = 1;
    public static final int SAFETY_BELT_STATE_UNLOCK = 0;
    protected static final String TAG = "BYDAutoSafetyBeltDevice";
    private static volatile BYDAutoSafetyBeltDevice mBYDAutoSafetyBeltDevice;
    private SafetyBeltManagerImpl mSafetyBeltManagerImpl;

    private BYDAutoSafetyBeltDevice(Context context) {
        super(context);
        this.mSafetyBeltManagerImpl = SafetyBeltManagerImpl.getInstance(context);
    }

    public static BYDAutoSafetyBeltDevice getInstance(Context context) {
        if (mBYDAutoSafetyBeltDevice == null) {
            synchronized (BYDAutoSafetyBeltDevice.class) {
                if (mBYDAutoSafetyBeltDevice == null) {
                    mBYDAutoSafetyBeltDevice = new BYDAutoSafetyBeltDevice(context.getApplicationContext());
                }
            }
        }
        return mBYDAutoSafetyBeltDevice;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        return this.mSafetyBeltManagerImpl.get(iArr, cls);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        throw new RuntimeException("Stub!");
    }

    public int getDriverBeltStatus() {
        return this.mSafetyBeltManagerImpl.getDriverBeltStatus();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int[] getFeatureList() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return PERMISSION_BYDAUTO_SAFEBELT_GET;
    }

    public int getMainSeatState() {
        return this.mSafetyBeltManagerImpl.getMainSeatState();
    }

    public int getMessage5sOnlineState(int i2) {
        throw new RuntimeException("Stub!");
    }

    public int getPassengerStatus(int i2) {
        return this.mSafetyBeltManagerImpl.getPassengerStatus(i2);
    }

    public int getSafetyBeltMsrState() {
        throw new RuntimeException("Stub!");
    }

    public int getSafetyBeltReminder() {
        throw new RuntimeException("Stub!");
    }

    public int getSafetyBeltStatus(int i2) {
        return this.mSafetyBeltManagerImpl.getSafetyBeltStatus(i2);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return PERMISSION_BYDAUTO_SAFEBELT_SET;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getType() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public boolean postEvent(int i2, int i3, int i4, Object obj) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoSafetyBeltListener absBYDAutoSafetyBeltListener) {
        this.mSafetyBeltManagerImpl.registerListener(absBYDAutoSafetyBeltListener);
    }

    public int setSafetyBeltSbcdState(int i2) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(AbsBYDAutoSafetyBeltListener absBYDAutoSafetyBeltListener) {
        this.mSafetyBeltManagerImpl.unregisterListener(absBYDAutoSafetyBeltListener);
    }

    public void registerListener(AbsBYDAutoSafetyBeltListener absBYDAutoSafetyBeltListener, int[] iArr) {
        this.mSafetyBeltManagerImpl.registerListener(absBYDAutoSafetyBeltListener, iArr);
    }
}
