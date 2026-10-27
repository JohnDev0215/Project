package android.hardware.bydauto.ota;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.ota.tsimpl.OtaManagerImpl;
import android.hardware.bydauto.power.tsimpl.PowerManagerImpl;
import h6.n;
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
    protected static final String TAG = "BYDAutoOTAdevice";
    private static BYDAutoOtaDevice sBydAutoOtaDevice;
    private OtaManagerImpl mManagerImpl;
    private PowerManagerImpl mPowerManagerImpl;

    public BYDAutoOtaDevice(Context context) {
        super(context);
        this.mManagerImpl = null;
        this.mPowerManagerImpl = null;
        n.j(TAG, "BYDAutoOtaDevice: conn = " + context);
        this.mManagerImpl = OtaManagerImpl.getInstance(context);
        this.mPowerManagerImpl = PowerManagerImpl.getInstance(context);
    }

    public static synchronized BYDAutoOtaDevice getInstance(Context context) {
        try {
            if (sBydAutoOtaDevice == null) {
                sBydAutoOtaDevice = new BYDAutoOtaDevice(context);
            }
        } catch (Throwable th) {
            throw th;
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

    public int getBatteryTemp(int i7) {
        return this.mManagerImpl.getBatteryTemp(i7);
    }

    @Deprecated
    public int getBatteryType() {
        return this.mManagerImpl.getBatteryType();
    }

    public int getBatteryVoltage(int i7) {
        return this.mManagerImpl.getBatteryVoltage(i7);
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

    public int getDischargeState(int i7) {
        return this.mManagerImpl.getDischargeState(i7);
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

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
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

    public int getSmartChargeState(int i7) {
        return this.mManagerImpl.getSmartChargeState(i7);
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

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
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

    public void notifyTboxOtaState(int i7) {
        this.mManagerImpl.notifyTboxOtaState(i7);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public boolean postEvent(int i7, int i10, byte[] bArr, Object obj) {
        return this.mManagerImpl.postEvent(i7, i10, bArr, obj);
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

    public void resetOTA() {
        this.mManagerImpl.resetOTA();
    }

    public boolean sendCanNM(int i7, int i10, AbsMessageCallback absMessageCallback) {
        return this.mManagerImpl.sendCanNM(i7, i10, absMessageCallback);
    }

    public int sendECUIds(int i7, byte[] bArr) {
        return this.mManagerImpl.sendECUIds(i7, bArr);
    }

    public int sendECUIds2(int i7, byte[] bArr) {
        return this.mManagerImpl.sendECUIds2(i7, bArr);
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

    public int sendOTATimecountCtlCMD(int i7, int i10, int i11, int i12) {
        return this.mManagerImpl.sendOTATimecountCtlCMD(i7, i10, i11, i12);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int set(int[] iArr, BYDAutoEventValue bYDAutoEventValue) {
        return this.mManagerImpl.set(iArr, bYDAutoEventValue);
    }

    public boolean setAQSlot(int i7, AbsMessageCallback absMessageCallback) {
        return this.mManagerImpl.setAQSlot(i7, absMessageCallback);
    }

    public void setAllStatus() {
        this.mManagerImpl.setAllStatus();
    }

    public void setAndroidOtaState(int i7, AbsMessageCallback absMessageCallback) {
        this.mManagerImpl.setAndroidOtaState(i7, absMessageCallback);
    }

    public void setAppointmentTime(int i7, int i10, int i11, int i12, int i13, AbsMessageCallback absMessageCallback) {
        this.mManagerImpl.setAppointmentTime(i7, i10, i11, i12, i13, absMessageCallback);
    }

    public boolean setBackLightState(int i7) {
        return this.mManagerImpl.setBackLightState(i7);
    }

    public boolean setBackupAreaDownload(int i7) {
        return this.mManagerImpl.setBackupAreaDownload(i7);
    }

    public int setCanInfoSwitchState(int i7) {
        return this.mManagerImpl.setCanInfoSwitchState(i7);
    }

    public void setCloudServiceListener(AbsMessageCallback absMessageCallback) {
        this.mManagerImpl.setCloudServiceListener(absMessageCallback);
    }

    public boolean setEclPower(int i7) {
        return this.mManagerImpl.setEclPower(i7);
    }

    public boolean setHighVoltageCmd(int i7) {
        return this.mManagerImpl.setHighVoltageCmd(i7);
    }

    public int setMcuMessage(int i7) {
        return this.mManagerImpl.setMcuMessage(i7);
    }

    public boolean setMultimediaSelfUpdate(int i7) {
        return this.mManagerImpl.setMultimediaSelfUpdate(i7);
    }

    public boolean setOtaModel(int i7) {
        return this.mManagerImpl.setOtaModel(i7);
    }

    public boolean setOtaPower(int i7) {
        return this.mManagerImpl.setOtaPower(i7);
    }

    public int setPowerOnForbidState(int i7) {
        return this.mManagerImpl.setPowerOnForbidState(i7);
    }

    public void setQnxOtaState(int i7, AbsMessageCallback absMessageCallback) {
        this.mManagerImpl.setQnxOtaState(i7, absMessageCallback);
    }

    public boolean setQuitChargeAndDischarge(int i7) {
        return this.mManagerImpl.setQuitChargeAndDischarge(i7);
    }

    public boolean setQuitSmartCharge(int i7) {
        return this.mManagerImpl.setQuitSmartCharge(i7);
    }

    public int setScreenState(int i7) {
        return this.mManagerImpl.setScreenState(i7);
    }

    public boolean setSmartPower(int i7) {
        return this.mManagerImpl.setSmartPower(i7);
    }

    public void setUpdateFlag(int i7, AbsMessageCallback absMessageCallback) {
        this.mManagerImpl.setUpdateFlag(i7, absMessageCallback);
    }

    @Deprecated
    public void startUpdate(int i7, IUpdateListener iUpdateListener) {
        this.mManagerImpl.startUpdate(i7, null);
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
    public void trigerTestOtaManagerImplCallback(int i7, Object obj) {
        this.mManagerImpl.trigerTestOtaManagerImplCallback(i7, obj);
    }

    public void unregisterListener(AbsBYDAutoOtaListener absBYDAutoOtaListener) {
        this.mManagerImpl.unregisterListener(absBYDAutoOtaListener);
    }

    public int updateHsm(String str) {
        return this.mManagerImpl.updateHsm(str);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public boolean postEvent(int i7, int i10, int i11, Object obj) {
        return this.mManagerImpl.postEvent(i7, i10, i11, obj);
    }

    public void registerListener(AbsBYDAutoOtaListener absBYDAutoOtaListener, int[] iArr) {
        this.mManagerImpl.registerListener(absBYDAutoOtaListener, iArr);
    }

    public boolean setBackLightState(int i7, int i10) {
        return this.mPowerManagerImpl.setBackLightState(i7, i10);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public boolean postEvent(int i7, int i10, float f10, Object obj) {
        return this.mManagerImpl.postEvent(i7, i10, f10, obj);
    }
}
