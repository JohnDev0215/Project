package android.hardware.bydauto.tyre;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.tyre.tsimpl.TyreManagerImpl;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoTyreDevice extends AbsBYDAutoDevice {
    public static final int DEVICE_HAS_THE_FEATURE = 1;
    public static final int DEVICE_NOT_HAS_THE_FEATURE = 0;
    public static final int DEVICE_THE_FEATURE_LINK_ERROR = 65535;
    public static final int DEVICE_THE_FEATURE_NEVER_GET = 2;
    public static final String FEATURE_INDIRECT_TYRE_PRESSURE_DISPLAY = "IndirectTyrePressureDisplay";
    public static final String FEATURE_INDIRECT_TYRE_PRESSURE_ONLINE = "IndirectTyrePressureMonitor";
    public static final String FEATURE_TYRE_PRESSURE_ONLINE = "TyrePressureMonitor";
    public static final int INDIRECT_TYRE_PRESSURE_CONFIRM = 1;
    public static final int INDITECT_TYRE_SYSTEM_RESETTING = 1;
    public static final int INDITECT_TYRE_SYSTEM_RESET_FAILED = 3;
    public static final int INDITECT_TYRE_SYSTEM_RESET_SUCCESS = 2;
    public static final String PERMISSION_BYDAUTO_TYRE_COMMON = "android.permission.BYDAUTO_TYRE_COMMON";
    public static final String PERMISSION_BYDAUTO_TYRE_GET = "android.permission.BYDAUTO_TYRE_GET";
    public static final String PERMISSION_BYDAUTO_TYRE_SET = "android.permission.BYDAUTO_TYRE_SET";
    public static final int STATE_HEADLIGHT_AUTO = 2;
    public static final int STATE_HEADLIGHT_LOW_BEAM = 4;
    public static final int STATE_HEADLIGHT_OFF = 1;
    public static final int STATE_HEADLIGHT_SMALL_LIGHT = 3;
    public static final int STATE_MUSIC_NEXT = 2;
    public static final int STATE_MUSIC_PAUSE = 2;
    public static final int STATE_MUSIC_PLAY = 1;
    public static final int STATE_MUSIC_PRE = 1;
    public static final int STATE_MUTE_CANCEL = 2;
    public static final int STATE_MUTE_OK = 1;
    public static final int STATE_RESOURCE_SHOW = 1;
    public static final int STATE_SCREEN_1 = 1;
    public static final int STATE_SCREEN_2 = 2;
    public static final int STATE_VOL_INVALID = 63;
    protected static final String TAG = "BYDAutoTyreDevice";
    public static final int TYRE_AIR_LEAK_STATE_NORMAL = 0;
    public static final int TYRE_AIR_LEAK_STATE_QUICK = 1;
    public static final int TYRE_AIR_LEAK_STATE_SLOW = 2;
    public static final int TYRE_BATTERY_STATE_LOW = 1;
    public static final int TYRE_BATTERY_STATE_NORMAL = 0;
    public static final double TYRE_BATTERY_VOLTAGE_MAX = 40.0d;
    public static final double TYRE_BATTERY_VOLTAGE_MIN = 0.0d;
    public static final int TYRE_COMMAND_AREA_LEFT_FRONT = 1;
    public static final int TYRE_COMMAND_AREA_LEFT_REAR = 3;
    public static final int TYRE_COMMAND_AREA_RIGHT_FRONT = 2;
    public static final int TYRE_COMMAND_AREA_RIGHT_REAR = 4;
    public static final int TYRE_COMMAND_BUSY = -2147482647;
    public static final int TYRE_COMMAND_FAILED = -2147482648;
    public static final int TYRE_COMMAND_INVALID_VALUE = -2147482645;
    public static final int TYRE_COMMAND_SUCCESS = 0;
    public static final int TYRE_COMMAND_TIMEOUT = -2147482646;
    public static final int TYRE_PRESSURE_STATE_NORMAL = 0;
    public static final int TYRE_PRESSURE_STATE_OVERPRESSURE = 1;
    public static final int TYRE_PRESSURE_STATE_UNDERPRESSURE = 2;
    public static final int TYRE_PRESSURE_VALUE_MAX = 4094;
    public static final int TYRE_PRESSURE_VALUE_MIN = 0;
    public static final int TYRE_SIGNAL_STATE_ERROR = 1;
    public static final int TYRE_SIGNAL_STATE_NORMAL = 0;
    public static final int TYRE_SYSTEM_STATE_BREAKDOWN = 3;
    public static final int TYRE_SYSTEM_STATE_MASKED = 4;
    public static final int TYRE_SYSTEM_STATE_NORMAL = 0;
    public static final int TYRE_SYSTEM_STATE_SELF_CHECKING = 1;
    public static final int TYRE_SYSTEM_STATE_SIGNAL_ANOMAL = 2;
    public static final double TYRE_TEMPERATURE_MAX = 369.4d;
    public static final double TYRE_TEMPERATURE_MIN = -40.0d;
    public static final int TYRE_TEMPERATURE_STATE_HIGH = 2;
    public static final int TYRE_TEMPERATURE_STATE_NORMAL = 0;
    public static final int TYRE_TEMPERATURE_STATE_SLEEP = 3;
    public static final int TYRE_TEMPERATURE_STATE_SUPER_HIGH = 1;
    public static final int TYRE_TURN_D_FBACK_R = 2;
    public static final int TYRE_TURN_D_FBACK_RBACK = 6;
    public static final int TYRE_TURN_D_FDRIVE_R = 1;
    public static final int TYRE_TURN_D_FDRIVE_RDRIVE = 5;
    public static final int TYRE_TURN_F_R = 0;
    public static final int TYRE_TURN_R_FBACK_R = 4;
    public static final int TYRE_TURN_R_FBACK_RBACK = 8;
    public static final int TYRE_TURN_R_FDRIVE_R = 3;
    public static final int TYRE_TURN_R_FDRIVE_RDRIVE = 7;
    private static volatile BYDAutoTyreDevice mBYDAutoTyreDevice;
    private TyreManagerImpl mTyreManagerImpl;

    private BYDAutoTyreDevice(Context context) {
        super(context);
        this.mTyreManagerImpl = TyreManagerImpl.getInstance(context);
    }

    public static BYDAutoTyreDevice getInstance(Context context) {
        if (mBYDAutoTyreDevice == null) {
            synchronized (BYDAutoTyreDevice.class) {
                if (mBYDAutoTyreDevice == null) {
                    mBYDAutoTyreDevice = new BYDAutoTyreDevice(context.getApplicationContext());
                }
            }
        }
        return mBYDAutoTyreDevice;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        return this.mTyreManagerImpl.get(iArr, cls);
    }

    public void getAllStatus() {
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return PERMISSION_BYDAUTO_TYRE_GET;
    }

    public int getIndirectTyrePressureState(int i) {
        return this.mTyreManagerImpl.getIndirectTyrePressureState(i);
    }

    public int getIndirectTyreSystemState() {
        return this.mTyreManagerImpl.getIndirectTyreSystemState();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return PERMISSION_BYDAUTO_TYRE_SET;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getType() {
        return 0;
    }

    public int getTyreAirLeakState(int i) {
        return this.mTyreManagerImpl.getTyreAirLeakState(i);
    }

    public int getTyreBatteryState() {
        return this.mTyreManagerImpl.getTyreBatteryState();
    }

    public float getTyreBatteryValue(int i) {
        return this.mTyreManagerImpl.getTyreBatteryValue(i);
    }

    public int getTyreBodyType() {
        return this.mTyreManagerImpl.getTyreBodyType();
    }

    public int getTyreColorByType(int i) {
        return this.mTyreManagerImpl.getTyreColorByType(i);
    }

    public int getTyrePressureState(int i) {
        return this.mTyreManagerImpl.getTyrePressureState(i);
    }

    public int getTyrePressureSysFailHint() {
        return this.mTyreManagerImpl.getTyrePressureSysFailHint();
    }

    public int getTyrePressureValue(int i) {
        return this.mTyreManagerImpl.getTyrePressureValue(i);
    }

    public float getTyrePressureValueByType(int i) {
        return this.mTyreManagerImpl.getTyrePressureValueByType(i);
    }

    public int getTyrePressureWarn(int i) {
        return this.mTyreManagerImpl.getTyrePressureWarn(i);
    }

    public int getTyrePressureWarnHint() {
        return this.mTyreManagerImpl.getTyrePressureWarnHint();
    }

    public int getTyreSignalState(int i) {
        return this.mTyreManagerImpl.getTyreSignalState(i);
    }

    public int getTyreSystemState() {
        return this.mTyreManagerImpl.getTyreSystemState();
    }

    public int getTyreTemperatureState() {
        return this.mTyreManagerImpl.getTyreTemperatureState();
    }

    public int getTyreTurnState() {
        return this.mTyreManagerImpl.getTyreTurnState();
    }

    public int hasFeature(String str) {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public boolean postEvent(int i, int i2, int i3, Object obj) {
        return false;
    }

    public void registerListener(AbsBYDAutoTyreListener absBYDAutoTyreListener) {
        this.mTyreManagerImpl.registerListener(absBYDAutoTyreListener);
    }

    public void registerListener(AbsBYDAutoTyreListener absBYDAutoTyreListener, int[] iArr) {
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int set(int[] iArr, BYDAutoEventValue bYDAutoEventValue) {
        return this.mTyreManagerImpl.set(iArr, bYDAutoEventValue);
    }

    public int setIndirectTyreConfirm(int i) {
        return this.mTyreManagerImpl.setIndirectTyreConfirm(i);
    }

    public void unregisterListener(AbsBYDAutoTyreListener absBYDAutoTyreListener) {
        this.mTyreManagerImpl.unregisterListener(absBYDAutoTyreListener);
    }
}
