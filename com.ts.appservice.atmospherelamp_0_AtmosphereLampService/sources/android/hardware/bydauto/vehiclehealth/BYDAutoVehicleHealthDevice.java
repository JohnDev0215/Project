package android.hardware.bydauto.vehiclehealth;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.vehiclehealth.tsimpl.VehicleHealthManagerImpl;

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
    protected static final String TAG = "BYDAutoVehicleHealthDevice";
    public static final int TRANS_STATE_INVALID = 0;
    public static final int TRANS_STATE_OFF = 2;
    public static final int TRANS_STATE_ON = 1;
    public static final int TRANS_STATE_UNINSTALL = 3;
    public static final int TYRE_FAULT_LOCATION_NOT_SUPPORT = 1;
    public static final int TYRE_FAULT_LOCATION_SUPPORT = 2;
    private static BYDAutoVehicleHealthDevice sBYDAutoVehicleHealthDevice;
    private VehicleHealthManagerImpl mVehicleHealthManagerImpl;

    BYDAutoVehicleHealthDevice(Context con) {
        super(con.getApplicationContext());
        this.mVehicleHealthManagerImpl = VehicleHealthManagerImpl.getsInstance(con.getApplicationContext());
    }

    public static synchronized BYDAutoVehicleHealthDevice getInstance(Context con) {
        if (sBYDAutoVehicleHealthDevice == null) {
            sBYDAutoVehicleHealthDevice = new BYDAutoVehicleHealthDevice(con.getApplicationContext());
        }
        return sBYDAutoVehicleHealthDevice;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return this.mVehicleHealthManagerImpl.getGetPermission();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return this.mVehicleHealthManagerImpl.getSetPermission();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return this.mVehicleHealthManagerImpl.getDevicetype();
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return this.mVehicleHealthManagerImpl.getType();
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return this.mVehicleHealthManagerImpl.getFeatureList();
    }

    public int getOilHint() {
        return this.mVehicleHealthManagerImpl.getOilHint();
    }

    public int getOilLifetime(int type) {
        return this.mVehicleHealthManagerImpl.getOilLifetime(type);
    }

    public int resetOilLifetime(int type) {
        return this.mVehicleHealthManagerImpl.resetOilLifetime(type);
    }

    public long[] getOilRecordList(int type) {
        return this.mVehicleHealthManagerImpl.getOilRecordList(type);
    }

    public int setOilRecordList(int type, long time, long total, long value) {
        return this.mVehicleHealthManagerImpl.setOilRecordList(type, time, total, value);
    }

    public int getOilLevel() {
        return this.mVehicleHealthManagerImpl.getOilLevel();
    }

    public int getFrontWiperCheckState() {
        return this.mVehicleHealthManagerImpl.getFrontWiperCheckState();
    }

    public int setFrontWiperCheckState(int state) {
        return this.mVehicleHealthManagerImpl.setFrontWiperCheckState(state);
    }

    public int getRearWiperCheckState() {
        return this.mVehicleHealthManagerImpl.getRearWiperCheckState();
    }

    public int setRearWiperCheckState(int state) {
        return this.mVehicleHealthManagerImpl.setRearWiperCheckState(state);
    }

    public int resetTirePressure() {
        return this.mVehicleHealthManagerImpl.resetTirePressure();
    }

    public int getMileageValid() {
        return this.mVehicleHealthManagerImpl.getMileageValid();
    }

    public int getOilUpdateState() {
        return this.mVehicleHealthManagerImpl.getOilUpdateState();
    }

    public int getTyreFaultLocation() {
        return this.mVehicleHealthManagerImpl.getTyreFaultLocation();
    }

    public void registerListener(AbsBYDAutoVehicleHealthListener l) {
        this.mVehicleHealthManagerImpl.registerListener(l);
    }

    public void registerListener(AbsBYDAutoVehicleHealthListener listener, int[] featureIds) {
        this.mVehicleHealthManagerImpl.registerListener(listener, featureIds);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] eventTypes, Class<?> type) {
        return this.mVehicleHealthManagerImpl.get(eventTypes, type);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int set(int[] eventTypes, BYDAutoEventValue eventValue) {
        return this.mVehicleHealthManagerImpl.set(eventTypes, eventValue);
    }

    public void unregisterListener(AbsBYDAutoVehicleHealthListener l) {
        this.mVehicleHealthManagerImpl.unregisterListener(l);
    }

    public int getEPBConfig() {
        return this.mVehicleHealthManagerImpl.getEPBConfig();
    }

    public int getEPBType() {
        return this.mVehicleHealthManagerImpl.getEPBType();
    }

    public int setEPBState(int state) {
        return this.mVehicleHealthManagerImpl.setEPBState(state);
    }

    public int getEPBState() {
        return this.mVehicleHealthManagerImpl.getEPBState();
    }

    public int setOilUpdateTag(int value) {
        return this.mVehicleHealthManagerImpl.setOilUpdateTag(value);
    }

    public int getOilMaintainanceHint(int type) {
        return this.mVehicleHealthManagerImpl.getOilMaintainanceHint(type);
    }

    public int setTransportMode(int state) {
        return this.mVehicleHealthManagerImpl.setTransportMode(state);
    }

    public int getTransportMode() {
        return this.mVehicleHealthManagerImpl.getTransportMode();
    }

    public int setPowerOffChargedState(int state) {
        return this.mVehicleHealthManagerImpl.setPowerOffChargedState(state);
    }

    public int getEPBHint() {
        return this.mVehicleHealthManagerImpl.getEpbHint();
    }

    public int getTransportModeStateHint() {
        return this.mVehicleHealthManagerImpl.getTransportModeStateHint();
    }

    public int getOilLifetimeNew(int type) {
        return this.mVehicleHealthManagerImpl.getOilLifetimeNew(type);
    }

    public int getOilMaintainanceDialogHint(int type) {
        return this.mVehicleHealthManagerImpl.getOilMaintainanceDialogHint(type);
    }

    public int setOilMaintainanceNotHint(int type, int value) {
        return this.mVehicleHealthManagerImpl.setOilMaintainanceNotHint(type, value);
    }

    public int getBatteryHealthStatus() {
        return this.mVehicleHealthManagerImpl.getBatteryHealthStatus();
    }
}
