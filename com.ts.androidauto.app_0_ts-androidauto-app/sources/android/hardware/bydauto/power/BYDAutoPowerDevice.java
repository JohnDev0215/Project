package android.hardware.bydauto.power;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.power.tsimpl.PowerManagerImpl;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoPowerDevice extends AbsBYDAutoDevice {
    public static final int ACC_STATUS = -1728053193;
    public static final int BATTERY_VOLTAGE_LOW = 1;
    public static final int BATTERY_VOLTAGE_NORMAL = 0;
    public static final int DEVICE_PAD = 1;
    public static final double MAX_BATTERY_REMAIN_POWER = 409.4d;
    public static final int MCU_SLEEP = 0;
    public static final int MCU_WAKE = 1;
    public static final double MIN_BATTERY_REMAIN_POWER = 0.0d;
    public static final String PERMISSION_BYDAUTO_POWER_COMMON = "android.permission.BYDAUTO_POWER_COMMON";
    public static final String PERMISSION_BYDAUTO_POWER_GET = "android.permission.BYDAUTO_POWER_GET";
    public static final String PERMISSION_BYDAUTO_POWER_SET = "android.permission.BYDAUTO_POWER_SET";
    public static final int POWER_COMMAND_BUSY = -2147482647;
    public static final int POWER_COMMAND_FAILED = -2147482648;
    public static final int POWER_COMMAND_INVALID_VALUE = -2147482645;
    public static final int POWER_COMMAND_SUCCESS = 0;
    public static final int POWER_COMMAND_TIMEOUT = -2147482646;
    public static final int POWER_CTL_STATE_OFF = 0;
    public static final int POWER_CTL_STATE_ON = 1;
    public static final int QCOM_REBOOT_STATE_DONE = 2;
    public static final int QCOM_REBOOT_STATE_INVALID = 0;
    public static final int QCOM_REBOOT_STATE_START = 1;
    public static final int SHUTDOWN_INFO_INVALID = 0;
    public static final int SHUTDOWN_INFO_WARNING = 1;
    public static final int START_GBDATA_TRANSMIT = 1;
    protected static final String TAG = "BYDAutoPowerDevice";
    public static final int TFT_BACKLIGHT_MAX = 11;
    public static final int TFT_BACKLIGHT_MIN = 0;
    public static final int TP_DISPLAY_FREESCALE = 2;
    public static final int TP_DISPLAY_PAD = 1;
    public static final int WAKEUP_ABNORMAL_RESTART = 2;
    public static final int WAKEUP_ACC_ON = 1;
    public static final int WAKEUP_EMERGENCY = 4;
    public static final int WAKEUP_NORMAL_START = 0;
    public static final int WAKEUP_REMOTE = 5;
    public static final int WAKEUP_UPGRADE = 3;
    private static BYDAutoPowerDevice mBYDAutoPowerDevice;
    private final PowerManagerImpl mManagerImpl;

    BYDAutoPowerDevice(Context con) {
        super(con);
        this.mManagerImpl = PowerManagerImpl.getInstance(con);
    }

    public static synchronized BYDAutoPowerDevice getInstance(Context con) {
        if (mBYDAutoPowerDevice == null) {
            mBYDAutoPowerDevice = new BYDAutoPowerDevice(con);
        }
        return mBYDAutoPowerDevice;
    }

    public void trigerTestPowerManagerImplCallback(int what, Object para1object) {
        this.mManagerImpl.trigerTestPowerManagerImplCallback(what, para1object);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return this.mManagerImpl.getDevicetype();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return PERMISSION_BYDAUTO_POWER_SET;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return PERMISSION_BYDAUTO_POWER_GET;
    }

    public int setPowerCtlStatus(int event_type, int value) {
        return this.mManagerImpl.setPowerCtlStatus(event_type, value);
    }

    public int dspReset() {
        return this.mManagerImpl.dspReset();
    }

    public int mcuReset() {
        return this.mManagerImpl.mcuReset();
    }

    public int qcomWakeup(int reason) {
        return this.mManagerImpl.qcomWakeup(reason);
    }

    public int padReset() {
        return this.mManagerImpl.padReset();
    }

    public int getBatteryRemainPowerEV() {
        return this.mManagerImpl.getBatteryRemainPowerEV();
    }

    public int getPowerCtlStatus(int event_type) {
        return this.mManagerImpl.getPowerCtlStatus(event_type);
    }

    public int setTpDisplayController(int value) {
        return this.mManagerImpl.setTpDisplayController(value);
    }

    public int getTpDisplayController() {
        return this.mManagerImpl.getTpDisplayController();
    }

    public int setTftBacklight(int value) {
        return this.mManagerImpl.setTftBacklight(value);
    }

    public int getTftBacklight() {
        return this.mManagerImpl.getTftBacklight();
    }

    public int wakeUpMcu() {
        return this.mManagerImpl.wakeUpMcu();
    }

    public int getMcuStatus() {
        return this.mManagerImpl.getMcuStatus();
    }

    public int setBCMPowerOnLevel() {
        return this.mManagerImpl.setBCMPowerOnLevel();
    }

    public int getShutdownInfo(int device) {
        return this.mManagerImpl.getShutdownInfo(device);
    }

    public int sendQcomRebootState(int state) {
        return this.mManagerImpl.sendQcomRebootState(state);
    }

    public int getBatteryLowVoltageState() {
        return this.mManagerImpl.getBatteryLowVoltageState();
    }

    public void setAllStatus() {
        this.mManagerImpl.setAllStatus();
    }

    public void getAllStatus() {
        this.mManagerImpl.getAllStatus();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int device_type, int event_type, int value, Object data) {
        return this.mManagerImpl.postEvent(device_type, event_type, value, data);
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return this.mManagerImpl.getType();
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return this.mManagerImpl.getFeatureList();
    }

    public int getPowerLevel() {
        return this.mManagerImpl.getPowerLevel();
    }

    public void registerListener(AbsBYDAutoPowerListener listener) {
        this.mManagerImpl.registerListener(listener);
    }

    public void registerListener(AbsBYDAutoPowerListener listener, int[] featureIDs) {
        this.mManagerImpl.registerListener(listener, featureIDs);
    }

    public void unregisterListener(AbsBYDAutoPowerListener listener) {
        this.mManagerImpl.unregisterListener(listener);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] eventTypes, Class<?> type) {
        return this.mManagerImpl.get(eventTypes, type);
    }
}
