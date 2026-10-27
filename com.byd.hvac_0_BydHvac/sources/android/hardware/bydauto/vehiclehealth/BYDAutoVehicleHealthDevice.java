package android.hardware.bydauto.vehiclehealth;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.vehiclehealth.tsimpl.VehicleHealthManagerImpl;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoVehicleHealthDevice extends AbsBYDAutoDevice {
    public static final int EPB_ENTITY = 0;
    public static final int EPB_MODE_NORMAL = 0;
    public static final int EPB_MODE_TRAILER = 1;
    public static final int EPB_P_GEAR = 1;
    public static final int EPB_STATE_INVALID = 0;
    public static final int EPB_STATE_NORMAL = 1;
    public static final int EPB_STATE_TRAILER = 2;
    public static final int EPB_TYPE_DOMAIN = 0;
    public static final int EPB_TYPE_SARK = 1;
    public static final int HINT_STATE_EXIT_FAIL = 4;
    public static final int HINT_STATE_INVALID = 0;
    public static final int HINT_STATE_IN_FAIL = 2;
    public static final int HINT_STATE_ON_POWER_OFF = 3;
    public static final int HINT_STATE_SUCCESS = 1;
    public static final int HINT_STATE_UNINSTALL_FAIL = 5;
    public static final int MILEAGE_NOT_VALID = 1;
    public static final int MILEAGE_VALID = 0;
    public static final int OIL_HINT_CHANGE_EXPIRED = 3;
    public static final int OIL_HINT_CHANGE_NOW = 2;
    public static final int OIL_HINT_CHANGE_WAIT = 1;
    public static final int OIL_HINT_RESET_FAIL = 5;
    public static final int OIL_HINT_RESET_SUCCESS = 4;
    public static final int OIL_LEVEL_BELOW_MIN = 0;
    public static final int OIL_LEVEL_CONFIG = 255;
    public static final int OIL_LEVEL_FAULT = 13;
    public static final int OIL_LEVEL_FIVE_EIGHTH_MAX = 6;
    public static final int OIL_LEVEL_FOUR_EIGHTH_MAX = 5;
    public static final int OIL_LEVEL_MAX = 9;
    public static final int OIL_LEVEL_MEASUREMENT = 11;
    public static final int OIL_LEVEL_MIN = 1;
    public static final int OIL_LEVEL_OK = 12;
    public static final int OIL_LEVEL_ONE_EIGHTH_MAX = 2;
    public static final int OIL_LEVEL_OVERFILL = 10;
    public static final int OIL_LEVEL_SEVEN_EIGHTH_MAX = 8;
    public static final int OIL_LEVEL_SIX_EIGHTH_MAX = 7;
    public static final int OIL_LEVEL_THREE_EIGHTH_MAX = 4;
    public static final int OIL_LEVEL_TWO_EIGHTH_MAX = 3;
    public static final int OIL_TYPE_BRAKE = 2;
    public static final int OIL_TYPE_COOL_BLADE_BATTERY = 3;
    public static final int OIL_TYPE_COOL_MOTOR_ELEC = 4;
    public static final int OIL_TYPE_ENGINE = 0;
    public static final int OIL_TYPE_TRANSMISSION = 1;
    public static final int OIL_UPDATE_ALLOW = 1;
    public static final int OIL_UPDATE_END_TAG = 1;
    public static final int OIL_UPDATE_NOT_ALLOW = 0;
    public static final int OIL_UPDATE_START_TAG = 0;
    public static final int STATE_CHECK_UNSUPPORT = 3;
    public static final int STATE_FAIL = 2;
    public static final int STATE_INVAILD = 0;
    public static final int STATE_INVALID = 0;
    public static final int STATE_NOT_HINT = 1;
    public static final int STATE_OFF = 2;
    public static final int STATE_ON = 1;
    public static final int STATE_SUCCESS = 1;
    public static final int STATE_TYRE_RESETTING = 1;
    public static final int STATE_TYRE_RESET_FAIL = 3;
    public static final int STATE_TYRE_RESET_INVAILD = 0;
    public static final int STATE_TYRE_RESET_SUCCESS = 2;
    public static final String TAG = "BYDAutoVehicleHealthDevice";
    public static final int TRANS_STATE_INVALID = 0;
    public static final int TRANS_STATE_OFF = 2;
    public static final int TRANS_STATE_ON = 1;
    public static final int TRANS_STATE_UNINSTALL = 3;
    public static final int TYRE_FAULT_LOCATION_NOT_SUPPORT = 1;
    public static final int TYRE_FAULT_LOCATION_SUPPORT = 2;
    private static BYDAutoVehicleHealthDevice sBYDAutoVehicleHealthDevice;
    private VehicleHealthManagerImpl mVehicleHealthManagerImpl;

    public BYDAutoVehicleHealthDevice(Context context) {
        super(context.getApplicationContext());
        this.mVehicleHealthManagerImpl = VehicleHealthManagerImpl.getsInstance(context.getApplicationContext());
    }

    public static synchronized BYDAutoVehicleHealthDevice getInstance(Context context) {
        if (sBYDAutoVehicleHealthDevice == null) {
            sBYDAutoVehicleHealthDevice = new BYDAutoVehicleHealthDevice(context.getApplicationContext());
        }
        return sBYDAutoVehicleHealthDevice;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        return this.mVehicleHealthManagerImpl.get(iArr, cls);
    }

    public int getBatteryHealthStatus() {
        return this.mVehicleHealthManagerImpl.getBatteryHealthStatus();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return this.mVehicleHealthManagerImpl.getDevicetype();
    }

    public int getEPBConfig() {
        return this.mVehicleHealthManagerImpl.getEPBConfig();
    }

    public int getEPBHint() {
        return this.mVehicleHealthManagerImpl.getEpbHint();
    }

    public int getEPBState() {
        return this.mVehicleHealthManagerImpl.getEPBState();
    }

    public int getEPBType() {
        return this.mVehicleHealthManagerImpl.getEPBType();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int[] getFeatureList() {
        return this.mVehicleHealthManagerImpl.getFeatureList();
    }

    public int getFrontWiperCheckState() {
        return this.mVehicleHealthManagerImpl.getFrontWiperCheckState();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return this.mVehicleHealthManagerImpl.getGetPermission();
    }

    public int getMileageValid() {
        return this.mVehicleHealthManagerImpl.getMileageValid();
    }

    public int getOilHint() {
        return this.mVehicleHealthManagerImpl.getOilHint();
    }

    public int getOilLevel() {
        return this.mVehicleHealthManagerImpl.getOilLevel();
    }

    public int getOilLifetime(int i) {
        return this.mVehicleHealthManagerImpl.getOilLifetime(i);
    }

    public int getOilLifetimeNew(int i) {
        return this.mVehicleHealthManagerImpl.getOilLifetimeNew(i);
    }

    public int getOilMaintainanceDialogHint(int i) {
        return this.mVehicleHealthManagerImpl.getOilMaintainanceDialogHint(i);
    }

    public int getOilMaintainanceHint(int i) {
        return this.mVehicleHealthManagerImpl.getOilMaintainanceHint(i);
    }

    public long[] getOilRecordList(int i) {
        return this.mVehicleHealthManagerImpl.getOilRecordList(i);
    }

    public int getOilUpdateState() {
        return this.mVehicleHealthManagerImpl.getOilUpdateState();
    }

    public int getRearWiperCheckState() {
        return this.mVehicleHealthManagerImpl.getRearWiperCheckState();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return this.mVehicleHealthManagerImpl.getSetPermission();
    }

    public int getTransportMode() {
        return this.mVehicleHealthManagerImpl.getTransportMode();
    }

    public int getTransportModeStateHint() {
        return this.mVehicleHealthManagerImpl.getTransportModeStateHint();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getType() {
        return this.mVehicleHealthManagerImpl.getType();
    }

    public int getTyreFaultLocation() {
        return this.mVehicleHealthManagerImpl.getTyreFaultLocation();
    }

    public void registerListener(AbsBYDAutoVehicleHealthListener absBYDAutoVehicleHealthListener) {
        this.mVehicleHealthManagerImpl.registerListener(absBYDAutoVehicleHealthListener);
    }

    public int resetOilLifetime(int i) {
        return this.mVehicleHealthManagerImpl.resetOilLifetime(i);
    }

    public int resetTirePressure() {
        return this.mVehicleHealthManagerImpl.resetTirePressure();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int set(int[] iArr, BYDAutoEventValue bYDAutoEventValue) {
        return this.mVehicleHealthManagerImpl.set(iArr, bYDAutoEventValue);
    }

    public int setEPBState(int i) {
        return this.mVehicleHealthManagerImpl.setEPBState(i);
    }

    public int setFrontWiperCheckState(int i) {
        return this.mVehicleHealthManagerImpl.setFrontWiperCheckState(i);
    }

    public int setOilMaintainanceNotHint(int i, int i2) {
        return this.mVehicleHealthManagerImpl.setOilMaintainanceNotHint(i, i2);
    }

    public int setOilRecordList(int i, long j, long j2, long j3) {
        return this.mVehicleHealthManagerImpl.setOilRecordList(i, j, j2, j3);
    }

    public int setOilUpdateTag(int i) {
        return this.mVehicleHealthManagerImpl.setOilUpdateTag(i);
    }

    public int setPowerOffChargedState(int i) {
        return this.mVehicleHealthManagerImpl.setPowerOffChargedState(i);
    }

    public int setRearWiperCheckState(int i) {
        return this.mVehicleHealthManagerImpl.setRearWiperCheckState(i);
    }

    public int setTransportMode(int i) {
        return this.mVehicleHealthManagerImpl.setTransportMode(i);
    }

    public void unregisterListener(AbsBYDAutoVehicleHealthListener absBYDAutoVehicleHealthListener) {
        this.mVehicleHealthManagerImpl.unregisterListener(absBYDAutoVehicleHealthListener);
    }

    public void registerListener(AbsBYDAutoVehicleHealthListener absBYDAutoVehicleHealthListener, int[] iArr) {
        this.mVehicleHealthManagerImpl.registerListener(absBYDAutoVehicleHealthListener, iArr);
    }
}
