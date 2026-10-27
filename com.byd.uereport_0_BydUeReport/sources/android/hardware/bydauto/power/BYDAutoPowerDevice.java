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
    public static final String TAG = "BYDAutoPowerDevice";
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

    public BYDAutoPowerDevice(Context context) {
        super(context);
        this.mManagerImpl = PowerManagerImpl.getInstance(context);
    }

    public static synchronized BYDAutoPowerDevice getInstance(Context context) {
        if (mBYDAutoPowerDevice == null) {
            mBYDAutoPowerDevice = new BYDAutoPowerDevice(context);
        }
        return mBYDAutoPowerDevice;
    }

    public int dspReset() {
        return this.mManagerImpl.dspReset();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        return this.mManagerImpl.get(iArr, cls);
    }

    public void getAllStatus() {
        this.mManagerImpl.getAllStatus();
    }

    public int getBatteryLowVoltageState() {
        return this.mManagerImpl.getBatteryLowVoltageState();
    }

    public int getBatteryRemainPowerEV() {
        return this.mManagerImpl.getBatteryRemainPowerEV();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return this.mManagerImpl.getDevicetype();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int[] getFeatureList() {
        return this.mManagerImpl.getFeatureList();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return PERMISSION_BYDAUTO_POWER_GET;
    }

    public int getMcuStatus() {
        return this.mManagerImpl.getMcuStatus();
    }

    public int getPowerCtlStatus(int i8) {
        return this.mManagerImpl.getPowerCtlStatus(i8);
    }

    public int getPowerLevel() {
        return this.mManagerImpl.getPowerLevel();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return PERMISSION_BYDAUTO_POWER_SET;
    }

    public int getShutdownInfo(int i8) {
        return this.mManagerImpl.getShutdownInfo(i8);
    }

    public int getTftBacklight() {
        return this.mManagerImpl.getTftBacklight();
    }

    public int getTpDisplayController() {
        return this.mManagerImpl.getTpDisplayController();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getType() {
        return this.mManagerImpl.getType();
    }

    public int mcuReset() {
        return this.mManagerImpl.mcuReset();
    }

    public int padReset() {
        return this.mManagerImpl.padReset();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public boolean postEvent(int i8, int i9, int i10, Object obj) {
        return this.mManagerImpl.postEvent(i8, i9, i10, obj);
    }

    public int qcomWakeup(int i8) {
        return this.mManagerImpl.qcomWakeup(i8);
    }

    public void registerListener(AbsBYDAutoPowerListener absBYDAutoPowerListener) {
        this.mManagerImpl.registerListener(absBYDAutoPowerListener);
    }

    public int sendQcomRebootState(int i8) {
        return this.mManagerImpl.sendQcomRebootState(i8);
    }

    public void setAllStatus() {
        this.mManagerImpl.setAllStatus();
    }

    public int setBCMPowerOnLevel() {
        return this.mManagerImpl.setBCMPowerOnLevel();
    }

    public int setPowerCtlStatus(int i8, int i9) {
        return this.mManagerImpl.setPowerCtlStatus(i8, i9);
    }

    public int setTftBacklight(int i8) {
        return this.mManagerImpl.setTftBacklight(i8);
    }

    public int setTpDisplayController(int i8) {
        return this.mManagerImpl.setTpDisplayController(i8);
    }

    public void trigerTestPowerManagerImplCallback(int i8, Object obj) {
        this.mManagerImpl.trigerTestPowerManagerImplCallback(i8, obj);
    }

    public void unregisterListener(AbsBYDAutoPowerListener absBYDAutoPowerListener) {
        this.mManagerImpl.unregisterListener(absBYDAutoPowerListener);
    }

    public int wakeUpMcu() {
        return this.mManagerImpl.wakeUpMcu();
    }

    public void registerListener(AbsBYDAutoPowerListener absBYDAutoPowerListener, int[] iArr) {
        this.mManagerImpl.registerListener(absBYDAutoPowerListener, iArr);
    }
}
