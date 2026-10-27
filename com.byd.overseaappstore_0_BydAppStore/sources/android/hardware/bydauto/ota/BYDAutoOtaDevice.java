package android.hardware.bydauto.ota;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.ota.tsimpl.OtaManagerImpl;
import android.hardware.bydauto.power.tsimpl.PowerManagerImpl;
import android.util.Log;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoOtaDevice extends AbsBYDAutoDevice {
    public static final double BATTERY_POWER_VOLTAGE_MAX = 17.0d;
    public static final double BATTERY_POWER_VOLTAGE_MIN = 6.0d;
    public static final double BATTERY_VOLTAGE_MAX = 16.0d;
    public static final double BATTERY_VOLTAGE_MIN = 8.0d;
    public static final int CAN_INFO_SWITCH_CLOSE = 0;
    public static final int CAN_INFO_SWITCH_OPEN = 1;
    public static final int CAN_INST_COM1 = 4;
    public static final int CAN_INST_COM2 = 0;
    public static final int CAN_INST_ECM = 5;
    public static final int CAN_INST_ESC = 1;
    public static final int CAN_INST_EX = 2;
    public static final int CONTACTOR_STATE_CONNECT = 1;
    public static final int CONTACTOR_STATE_DISCONNECT = 0;
    public static final int LF_DOOR_LOCK = 2;
    public static final int LF_DOOR_LOCK_INVALID = 0;
    public static final int LF_DOOR_UNLOCK = 1;
    public static final int LOCAL_DIAG_ENTER = 1;
    public static final int LOCAL_DIAG_FINISH = 0;
    public static final int OTA_COMMAND_BUSY = -2147482647;
    public static final int OTA_COMMAND_FAILED = -2147482648;
    public static final int OTA_COMMAND_INVALID = -2147482645;
    public static final int OTA_COMMAND_SUCCESS = 0;
    public static final int OTA_COMMAND_TIMEOUT = -2147482646;
    public static final int OTA_SCREEN_OFF = 0;
    public static final int OTA_SCREEN_ON = 1;
    public static final int OTA_STATE_INVALID = 0;
    public static final int OTA_STATE_UPGRADE = 1;
    public static final int OTA_TIMECOUNT_CTL_HOUR_INVALID = 31;
    public static final int OTA_TIMECOUNT_CTL_HOUR_MAX = 23;
    public static final int OTA_TIMECOUNT_CTL_HOUR_MIN = 0;
    public static final int OTA_TIMECOUNT_CTL_INSTALL_APPOINTMENT = 2;
    public static final int OTA_TIMECOUNT_CTL_INSTALL_CANCLE = 3;
    public static final int OTA_TIMECOUNT_CTL_INSTALL_INVALID = 0;
    public static final int OTA_TIMECOUNT_CTL_INSTALL_NOW = 1;
    public static final int OTA_TIMECOUNT_CTL_MINUTE_INVALID = 63;
    public static final int OTA_TIMECOUNT_CTL_MINUTE_MAX = 59;
    public static final int OTA_TIMECOUNT_CTL_MINUTE_MIN = 0;
    public static final int OTA_TIMECOUNT_CTL_USER_CANCLE = 2;
    public static final int OTA_TIMECOUNT_CTL_USER_CONFIRM = 1;
    public static final int OTA_TIMECOUNT_CTL_USER_DO_NOTHING = 3;
    public static final int OTA_TIMECOUNT_CTL_USER_INVALID = 0;
    public static final int OTA_TIMECOUNT_STATE_ALLOW = 0;
    public static final int OTA_TIMECOUNT_STATE_NOT_ALLOW = 1;
    public static final String PERMISSION_BYDAUTO_OTA_COMMON = "android.permission.BYDAUTO_OTA_COMMON";
    public static final String PERMISSION_BYDAUTO_OTA_GET = "android.permission.BYDAUTO_OTA_GET";
    public static final String PERMISSION_BYDAUTO_OTA_SET = "android.permission.BYDAUTO_OTA_SET";
    public static final int POWER_OFF_INFORM_INVALID = 0;
    public static final int POWER_OFF_INFORM_POWEROFF = 1;
    public static final int POWER_ON_FORBID_DISABLE = 2;
    public static final int POWER_ON_FORBID_ENABLE = 1;
    public static final int POWER_ON_FORBID_INVALID = 0;
    public static final int SEND_ECU_IDS_PURPOSE_FAULTCODE = 3;
    public static final int SEND_ECU_IDS_PURPOSE_SOFTCODE = 2;
    public static final int SEND_ECU_IDS_PURPOSE_VERSION_INFO = 1;
    public static final int START_REBOOT_RECOVERY = 1;
    public static final int SYNC_MCU_STATE_DONE = 1;
    public static final int SYNC_MCU_STATE_INVALID = 0;
    public static final String TAG = "BYDAutoOTAdevice";
    private static BYDAutoOtaDevice sBydAutoOtaDevice;
    private OtaManagerImpl mManagerImpl;
    private PowerManagerImpl mPowerManagerImpl;

    public BYDAutoOtaDevice(Context context) {
        super(context);
        this.mManagerImpl = null;
        this.mPowerManagerImpl = null;
        Log.e(TAG, "BYDAutoOtaDevice: conn = " + context);
        this.mManagerImpl = OtaManagerImpl.getInstance(context);
        this.mPowerManagerImpl = PowerManagerImpl.getInstance(context);
    }

    public static synchronized BYDAutoOtaDevice getInstance(Context context) {
        if (sBydAutoOtaDevice == null) {
            sBydAutoOtaDevice = new BYDAutoOtaDevice(context);
        }
        return sBydAutoOtaDevice;
    }

    public int FinishOTA() {
        return this.mManagerImpl.FinishOTA();
    }

    @Deprecated
    public void QNXreboot() {
        this.mManagerImpl.QNXreboot();
    }

    public int StartOTA() {
        return this.mManagerImpl.StartOTA();
    }

    public void clearDiagnosticTroubleCode(List<DiagnosticInfo> list, AbsMessageCallback absMessageCallback) {
        this.mManagerImpl.clearDiagnosticTroubleCode(list, absMessageCallback);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        return this.mManagerImpl.get(iArr, cls);
    }

    public boolean get7dfExistState() {
        return this.mManagerImpl.get7dfExistState();
    }

    public boolean getAQSlot(AbsMessageCallback absMessageCallback) {
        return this.mManagerImpl.getAQSlot(absMessageCallback);
    }

    public int getBackLightState() {
        return this.mPowerManagerImpl.getBackLightState();
    }

    @Deprecated
    public int getBatteryManagementDeviceState() {
        return this.mManagerImpl.getBatteryManagementDeviceState();
    }

    public double getBatteryPowerVoltage() {
        return this.mManagerImpl.getBatteryPowerVoltage();
    }

    public int getBatteryTemp(int i) {
        return this.mManagerImpl.getBatteryTemp(i);
    }

    @Deprecated
    public int getBatteryType() {
        return this.mManagerImpl.getBatteryType();
    }

    public int getBatteryVoltage(int i) {
        return this.mManagerImpl.getBatteryVoltage(i);
    }

    public int getBcmOtaState() {
        return this.mManagerImpl.getBcmOtaState();
    }

    @Deprecated
    public int getBurglarState() {
        return this.mManagerImpl.getBurglarState();
    }

    public byte[] getCanInfo() {
        return this.mManagerImpl.getCanInfo();
    }

    public int getChargeAndDischargeSystemState() {
        return this.mManagerImpl.getChargeAndDischargeSystemState();
    }

    @Deprecated
    public int getChargeState() {
        return this.mManagerImpl.getChargeState();
    }

    public int getDcState() {
        return this.mManagerImpl.getDcState();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return this.mManagerImpl.getDevicetype();
    }

    public void getDiagnosticTroubleCode(List<DiagnosticInfo> list, AbsMessageCallback absMessageCallback) {
        this.mManagerImpl.getDiagnosticTroubleCode(list, absMessageCallback);
    }

    public int getDischargeMainContactorState() {
        return this.mManagerImpl.getDischargeMainContactorState();
    }

    public int getDischargeState(int i) {
        return this.mManagerImpl.getDischargeState(i);
    }

    public byte[] getECUSoftcode() {
        return this.mManagerImpl.getECUSoftcode();
    }

    public byte[] getECUVersion() {
        return this.mManagerImpl.getECUVersion();
    }

    public byte[] getECUVersionAndSoftcode() {
        return this.mManagerImpl.getECUVersionAndSoftcode();
    }

    public boolean getEcuInfo(List<Info> list, AbsMessageCallback absMessageCallback) {
        return this.mManagerImpl.getEcuInfo(list, absMessageCallback);
    }

    public int getEpbState() {
        return this.mManagerImpl.getEpbState();
    }

    public byte[] getFaultCodeInfo() {
        return this.mManagerImpl.getFaultCodeInfo();
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return this.mManagerImpl.getFeatureList();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return PERMISSION_BYDAUTO_OTA_GET;
    }

    public int getIg1RelayState() {
        return this.mManagerImpl.getIg1RelayState();
    }

    public int getIg3RelayState() {
        return this.mManagerImpl.getIg3RelayState();
    }

    public int getIg4RelayState() {
        return this.mManagerImpl.getIg4RelayState();
    }

    @Deprecated
    public int getIgnitionState() {
        return this.mManagerImpl.getIgnitionState();
    }

    public int getLFDoorLockState() {
        return this.mManagerImpl.getLFDoorLockState();
    }

    public int getLFDoorLockStatus() {
        return this.mManagerImpl.getLFDoorLockStatus();
    }

    public int getLocalDiagStatus() {
        return this.mManagerImpl.getLocalDiagStatus();
    }

    public byte[] getMCUVersionAndSoftcode() {
        return this.mManagerImpl.getMCUVersionAndSoftcode();
    }

    public byte[] getOTAInfoACK() {
        return this.mManagerImpl.getOTAInfoACK();
    }

    public int getOTAState() {
        return this.mManagerImpl.getOTAState();
    }

    public int getOTATimecountState() {
        return this.mManagerImpl.getOTATimecountState();
    }

    public int getPowerOnForbidState() {
        return this.mManagerImpl.getPowerOnForbidState();
    }

    public int getPoweroffInform() {
        return this.mManagerImpl.getPoweroffInform();
    }

    public int getProhibitPowerOnState() {
        return this.mManagerImpl.getProhibitPowerOnState();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return PERMISSION_BYDAUTO_OTA_SET;
    }

    public int getSmartChargeState(int i) {
        return this.mManagerImpl.getSmartChargeState(i);
    }

    public int getSmartPowerException() {
        return this.mManagerImpl.getSmartPowerException();
    }

    public int getSmartPowerState() {
        return this.mManagerImpl.getSmartPowerState();
    }

    public boolean getSmartPowerUsable() {
        return this.mManagerImpl.getSmartPowerUsable();
    }

    public int getSocValue() {
        return this.mManagerImpl.getSocValue();
    }

    public String getTBoxHardwareVersion() {
        return this.mManagerImpl.getTBoxHardwareVersion();
    }

    public String getTBoxSerialNumber() {
        return this.mManagerImpl.getTBoxSerialNumber();
    }

    public String getTBoxSoftVersion() {
        return this.mManagerImpl.getTBoxSoftVersion();
    }

    public boolean getTBoxUpgradeLog(String str) {
        return this.mManagerImpl.getTBoxUpgradeLog(str);
    }

    public int getTurnOffState() {
        return this.mManagerImpl.getTurnOffState();
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return this.mManagerImpl.getType();
    }

    public float getVehicleSpeed() {
        return this.mManagerImpl.getVehicleSpeed();
    }

    @Deprecated
    public int getVehicleType() {
        return this.mManagerImpl.getVehicleType();
    }

    @Deprecated
    public String getVehicleVersion() {
        return this.mManagerImpl.getVehicleVersion();
    }

    public int notifyTargetID(byte[] bArr) {
        return this.mManagerImpl.notifyTargetID(bArr);
    }

    public int notifyTargetID2(byte[] bArr) {
        return this.mManagerImpl.notifyTargetID2(bArr);
    }

    public void notifyTboxOtaState(int i) {
        this.mManagerImpl.notifyTboxOtaState(i);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int i, int i2, float f, Object obj) {
        return this.mManagerImpl.postEvent(i, i2, f, obj);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int i, int i2, int i3, Object obj) {
        return this.mManagerImpl.postEvent(i, i2, i3, obj);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int i, int i2, byte[] bArr, Object obj) {
        return this.mManagerImpl.postEvent(i, i2, bArr, obj);
    }

    public int queryECUVersionAndSoftcode(byte[] bArr) {
        return this.mManagerImpl.queryECUVersionAndSoftcode(bArr);
    }

    public int queryMCUVersionAndSoftcode(byte[] bArr) {
        return this.mManagerImpl.queryMCUVersionAndSoftcode(bArr);
    }

    public void registerListener(AbsBYDAutoOtaListener absBYDAutoOtaListener) {
        this.mManagerImpl.registerListener(absBYDAutoOtaListener);
    }

    public void registerListener(AbsBYDAutoOtaListener absBYDAutoOtaListener, int[] iArr) {
        this.mManagerImpl.registerListener(absBYDAutoOtaListener, iArr);
    }

    public void resetOTA() {
        this.mManagerImpl.resetOTA();
    }

    public boolean sendCanNM(int i, int i2, AbsMessageCallback absMessageCallback) {
        return this.mManagerImpl.sendCanNM(i, i2, absMessageCallback);
    }

    public int sendECUIds(int i, byte[] bArr) {
        return this.mManagerImpl.sendECUIds(i, bArr);
    }

    public int sendECUIds2(int i, byte[] bArr) {
        return this.mManagerImpl.sendECUIds2(i, bArr);
    }

    public int sendMonitorTable(byte[] bArr) {
        return this.mManagerImpl.sendMonitorTable(bArr);
    }

    public int sendOTACtlCMD(OTAControlCMD oTAControlCMD) {
        return this.mManagerImpl.sendOTACtlCMD(oTAControlCMD);
    }

    public int sendOTAData(byte[] bArr) {
        return this.mManagerImpl.sendOTAData(bArr);
    }

    public int sendOTAData2(byte[] bArr) {
        return this.mManagerImpl.sendOTAData2(bArr);
    }

    public int sendOTAServiceData(byte[] bArr) {
        return this.mManagerImpl.sendOTAServiceData(bArr);
    }

    public int sendOTATimecountCtlCMD(int i, int i2, int i3, int i4) {
        return this.mManagerImpl.sendOTATimecountCtlCMD(i, i2, i3, i4);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int set(int[] iArr, BYDAutoEventValue bYDAutoEventValue) {
        return this.mManagerImpl.set(iArr, bYDAutoEventValue);
    }

    public boolean setAQSlot(int i, AbsMessageCallback absMessageCallback) {
        return this.mManagerImpl.setAQSlot(i, absMessageCallback);
    }

    public void setAllStatus() {
        this.mManagerImpl.setAllStatus();
    }

    public void setAndroidOtaState(int i, AbsMessageCallback absMessageCallback) {
        this.mManagerImpl.setAndroidOtaState(i, absMessageCallback);
    }

    public void setAppointmentTime(int i, int i2, int i3, int i4, int i5, AbsMessageCallback absMessageCallback) {
        this.mManagerImpl.setAppointmentTime(i, i2, i3, i4, i5, absMessageCallback);
    }

    public boolean setBackLightState(int i) {
        return this.mManagerImpl.setBackLightState(i);
    }

    public boolean setBackLightState(int i, int i2) {
        return this.mPowerManagerImpl.setBackLightState(i, i2);
    }

    public boolean setBackupAreaDownload(int i) {
        return this.mManagerImpl.setBackupAreaDownload(i);
    }

    public int setCanInfoSwitchState(int i) {
        return this.mManagerImpl.setCanInfoSwitchState(i);
    }

    public void setCloudServiceListener(AbsMessageCallback absMessageCallback) {
        this.mManagerImpl.setCloudServiceListener(absMessageCallback);
    }

    public boolean setEclPower(int i) {
        return this.mManagerImpl.setEclPower(i);
    }

    public boolean setHighVoltageCmd(int i) {
        return this.mManagerImpl.setHighVoltageCmd(i);
    }

    public int setMcuMessage(int i) {
        return this.mManagerImpl.setMcuMessage(i);
    }

    public boolean setMultimediaSelfUpdate(int i) {
        return this.mManagerImpl.setMultimediaSelfUpdate(i);
    }

    public boolean setOtaModel(int i) {
        return this.mManagerImpl.setOtaModel(i);
    }

    public boolean setOtaPower(int i) {
        return this.mManagerImpl.setOtaPower(i);
    }

    public int setPowerOnForbidState(int i) {
        return this.mManagerImpl.setPowerOnForbidState(i);
    }

    public void setQnxOtaState(int i, AbsMessageCallback absMessageCallback) {
        this.mManagerImpl.setQnxOtaState(i, absMessageCallback);
    }

    public boolean setQuitChargeAndDischarge(int i) {
        return this.mManagerImpl.setQuitChargeAndDischarge(i);
    }

    public boolean setQuitSmartCharge(int i) {
        return this.mManagerImpl.setQuitSmartCharge(i);
    }

    public int setScreenState(int i) {
        return this.mManagerImpl.setScreenState(i);
    }

    public boolean setSmartPower(int i) {
        return this.mManagerImpl.setSmartPower(i);
    }

    public void setUpdateFlag(int i, AbsMessageCallback absMessageCallback) {
        this.mManagerImpl.setUpdateFlag(i, absMessageCallback);
    }

    @Deprecated
    public void startUpdate(int i, IUpdateListener iUpdateListener) {
        this.mManagerImpl.startUpdate(i, null);
    }

    @Deprecated
    public void stopCloudServiceListener() {
        this.mManagerImpl.stopCloudServiceListener();
    }

    @Deprecated
    public void stopUpdate() {
        this.mManagerImpl.stopUpdate();
    }

    public int syncMcuState() {
        return this.mManagerImpl.syncMcuState();
    }

    @Deprecated
    public void trigerTestOtaManagerImplCallback(int i, Object obj) {
        this.mManagerImpl.trigerTestOtaManagerImplCallback(i, obj);
    }

    public void unregisterListener(AbsBYDAutoOtaListener absBYDAutoOtaListener) {
        this.mManagerImpl.unregisterListener(absBYDAutoOtaListener);
    }

    public int updateHsm(String str) {
        return this.mManagerImpl.updateHsm(str);
    }
}
