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
    protected static final String TAG = "BYDAutoOTAdevice";
    private static BYDAutoOtaDevice sBydAutoOtaDevice = null;
    private OtaManagerImpl mManagerImpl;
    private PowerManagerImpl mPowerManagerImpl;

    BYDAutoOtaDevice(Context con) {
        super(con);
        this.mManagerImpl = null;
        this.mPowerManagerImpl = null;
        Log.e(TAG, "BYDAutoOtaDevice: conn = " + con);
        this.mManagerImpl = OtaManagerImpl.getInstance(con);
        this.mPowerManagerImpl = PowerManagerImpl.getInstance(con);
    }

    public static synchronized BYDAutoOtaDevice getInstance(Context con) {
        if (sBydAutoOtaDevice == null) {
            sBydAutoOtaDevice = new BYDAutoOtaDevice(con);
        }
        return sBydAutoOtaDevice;
    }

    @Deprecated
    public void trigerTestOtaManagerImplCallback(int what, Object para1object) {
        this.mManagerImpl.trigerTestOtaManagerImplCallback(what, para1object);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return this.mManagerImpl.getDevicetype();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return PERMISSION_BYDAUTO_OTA_SET;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return PERMISSION_BYDAUTO_OTA_GET;
    }

    public byte[] getOTAInfoACK() {
        return this.mManagerImpl.getOTAInfoACK();
    }

    public int notifyTargetID(byte[] id) {
        return this.mManagerImpl.notifyTargetID(id);
    }

    public int notifyTargetID2(byte[] channelAndId) {
        return this.mManagerImpl.notifyTargetID2(channelAndId);
    }

    public int sendOTAData(byte[] data) {
        return this.mManagerImpl.sendOTAData(data);
    }

    public int sendOTAData2(byte[] channelAndData) {
        return this.mManagerImpl.sendOTAData2(channelAndData);
    }

    public int StartOTA() {
        return this.mManagerImpl.StartOTA();
    }

    public int FinishOTA() {
        return this.mManagerImpl.FinishOTA();
    }

    public int sendOTAServiceData(byte[] data) {
        return this.mManagerImpl.sendOTAServiceData(data);
    }

    public int sendECUIds(int purpose, byte[] ids) {
        return this.mManagerImpl.sendECUIds(purpose, ids);
    }

    public int sendOTACtlCMD(OTAControlCMD controlCmd) {
        return this.mManagerImpl.sendOTACtlCMD(controlCmd);
    }

    public byte[] getECUVersion() {
        return this.mManagerImpl.getECUVersion();
    }

    public byte[] getECUSoftcode() {
        return this.mManagerImpl.getECUSoftcode();
    }

    public byte[] getECUVersionAndSoftcode() {
        return this.mManagerImpl.getECUVersionAndSoftcode();
    }

    public int queryECUVersionAndSoftcode(byte[] ids) {
        return this.mManagerImpl.queryECUVersionAndSoftcode(ids);
    }

    public byte[] getMCUVersionAndSoftcode() {
        return this.mManagerImpl.getMCUVersionAndSoftcode();
    }

    public int queryMCUVersionAndSoftcode(byte[] ids) {
        return this.mManagerImpl.queryMCUVersionAndSoftcode(ids);
    }

    public int getPoweroffInform() {
        return this.mManagerImpl.getPoweroffInform();
    }

    public int getOTAState() {
        return this.mManagerImpl.getOTAState();
    }

    public int getOTATimecountState() {
        return this.mManagerImpl.getOTATimecountState();
    }

    public int sendOTATimecountCtlCMD(int install, int user, int hour, int minute) {
        return this.mManagerImpl.sendOTATimecountCtlCMD(install, user, hour, minute);
    }

    public int setScreenState(int state) {
        return this.mManagerImpl.setScreenState(state);
    }

    public int getDischargeMainContactorState() {
        return this.mManagerImpl.getDischargeMainContactorState();
    }

    public int sendECUIds2(int purpose, byte[] ids) {
        return this.mManagerImpl.sendECUIds2(purpose, ids);
    }

    public int getPowerOnForbidState() {
        return this.mManagerImpl.getPowerOnForbidState();
    }

    public int getLFDoorLockState() {
        return this.mManagerImpl.getLFDoorLockState();
    }

    public int setPowerOnForbidState(int state) {
        return this.mManagerImpl.setPowerOnForbidState(state);
    }

    public byte[] getCanInfo() {
        return this.mManagerImpl.getCanInfo();
    }

    public int setCanInfoSwitchState(int state) {
        return this.mManagerImpl.setCanInfoSwitchState(state);
    }

    public double getBatteryPowerVoltage() {
        return this.mManagerImpl.getBatteryPowerVoltage();
    }

    public byte[] getFaultCodeInfo() {
        return this.mManagerImpl.getFaultCodeInfo();
    }

    public int getLocalDiagStatus() {
        return this.mManagerImpl.getLocalDiagStatus();
    }

    public int setMcuMessage(int message) {
        return this.mManagerImpl.setMcuMessage(message);
    }

    public int syncMcuState() {
        return this.mManagerImpl.syncMcuState();
    }

    public int sendMonitorTable(byte[] id) {
        return this.mManagerImpl.sendMonitorTable(id);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int device_type, int event_type, byte[] value, Object data) {
        return this.mManagerImpl.postEvent(device_type, event_type, value, data);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int device_type, int event_type, int value, Object data) {
        return this.mManagerImpl.postEvent(device_type, event_type, value, data);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int device_type, int event_type, float value, Object data) {
        return this.mManagerImpl.postEvent(device_type, event_type, value, data);
    }

    public void setAllStatus() {
        this.mManagerImpl.setAllStatus();
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return this.mManagerImpl.getType();
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return this.mManagerImpl.getFeatureList();
    }

    public void registerListener(AbsBYDAutoOtaListener listener) {
        this.mManagerImpl.registerListener(listener);
    }

    public void registerListener(AbsBYDAutoOtaListener listener, int[] featureIDs) {
        this.mManagerImpl.registerListener(listener, featureIDs);
    }

    public void unregisterListener(AbsBYDAutoOtaListener listener) {
        this.mManagerImpl.unregisterListener(listener);
    }

    @Deprecated
    public String getVehicleVersion() {
        return this.mManagerImpl.getVehicleVersion();
    }

    public boolean getEcuInfo(List<Info> infos, AbsMessageCallback callback) {
        return this.mManagerImpl.getEcuInfo(infos, callback);
    }

    public boolean getAQSlot(AbsMessageCallback callback) {
        return this.mManagerImpl.getAQSlot(callback);
    }

    public boolean setAQSlot(int slot, AbsMessageCallback callback) {
        return this.mManagerImpl.setAQSlot(slot, callback);
    }

    @Deprecated
    public void QNXreboot() {
        this.mManagerImpl.QNXreboot();
    }

    @Deprecated
    public int getVehicleType() {
        return this.mManagerImpl.getVehicleType();
    }

    public int getTurnOffState() {
        return this.mManagerImpl.getTurnOffState();
    }

    @Deprecated
    public int getIgnitionState() {
        return this.mManagerImpl.getIgnitionState();
    }

    @Deprecated
    public int getBurglarState() {
        return this.mManagerImpl.getBurglarState();
    }

    public int getBatteryTemp(int batteryType) {
        return this.mManagerImpl.getBatteryTemp(batteryType);
    }

    public int getEpbState() {
        return this.mManagerImpl.getEpbState();
    }

    public boolean getSmartPowerUsable() {
        return this.mManagerImpl.getSmartPowerUsable();
    }

    public int getSmartPowerState() {
        return this.mManagerImpl.getSmartPowerState();
    }

    public int getSmartPowerException() {
        return this.mManagerImpl.getSmartPowerException();
    }

    public int getDcState() {
        return this.mManagerImpl.getDcState();
    }

    @Deprecated
    public int getBatteryType() {
        return this.mManagerImpl.getBatteryType();
    }

    public int getBatteryVoltage(int batteryType) {
        return this.mManagerImpl.getBatteryVoltage(batteryType);
    }

    @Deprecated
    public int getChargeState() {
        return this.mManagerImpl.getChargeState();
    }

    @Deprecated
    public int getBatteryManagementDeviceState() {
        return this.mManagerImpl.getBatteryManagementDeviceState();
    }

    public int getDischargeState(int type) {
        return this.mManagerImpl.getDischargeState(type);
    }

    public int getChargeAndDischargeSystemState() {
        return this.mManagerImpl.getChargeAndDischargeSystemState();
    }

    public int getSmartChargeState(int batteryType) {
        return this.mManagerImpl.getSmartChargeState(batteryType);
    }

    public int getIg3RelayState() {
        return this.mManagerImpl.getIg3RelayState();
    }

    public int getBcmOtaState() {
        return this.mManagerImpl.getBcmOtaState();
    }

    public boolean get7dfExistState() {
        return this.mManagerImpl.get7dfExistState();
    }

    public boolean setSmartPower(int state) {
        return this.mManagerImpl.setSmartPower(state);
    }

    public boolean setEclPower(int state) {
        return this.mManagerImpl.setEclPower(state);
    }

    public boolean setQuitSmartCharge(int state) {
        return this.mManagerImpl.setQuitSmartCharge(state);
    }

    public boolean setQuitChargeAndDischarge(int state) {
        return this.mManagerImpl.setQuitChargeAndDischarge(state);
    }

    public boolean setOtaModel(int state) {
        return this.mManagerImpl.setOtaModel(state);
    }

    public boolean setOtaPower(int state) {
        return this.mManagerImpl.setOtaPower(state);
    }

    public boolean setHighVoltageCmd(int command) {
        return this.mManagerImpl.setHighVoltageCmd(command);
    }

    public boolean setBackupAreaDownload(int state) {
        return this.mManagerImpl.setBackupAreaDownload(state);
    }

    public boolean setBackLightState(int state) {
        return this.mManagerImpl.setBackLightState(state);
    }

    public boolean setBackLightState(int state, int type) {
        return this.mPowerManagerImpl.setBackLightState(state, type);
    }

    public int getBackLightState() {
        return this.mPowerManagerImpl.getBackLightState();
    }

    @Deprecated
    public void startUpdate(int type, IUpdateListener listener) {
        this.mManagerImpl.startUpdate(type, null);
    }

    @Deprecated
    public void stopUpdate() {
        this.mManagerImpl.stopUpdate();
    }

    public String getTBoxSerialNumber() {
        return this.mManagerImpl.getTBoxSerialNumber();
    }

    public String getTBoxSoftVersion() {
        return this.mManagerImpl.getTBoxSoftVersion();
    }

    public String getTBoxHardwareVersion() {
        return this.mManagerImpl.getTBoxHardwareVersion();
    }

    public boolean getTBoxUpgradeLog(String path) {
        return this.mManagerImpl.getTBoxUpgradeLog(path);
    }

    public float getVehicleSpeed() {
        return this.mManagerImpl.getVehicleSpeed();
    }

    public int getSocValue() {
        return this.mManagerImpl.getSocValue();
    }

    public void setAppointmentTime(int day, int hours, int minutes, int type, int wakeup, AbsMessageCallback callback) {
        this.mManagerImpl.setAppointmentTime(day, hours, minutes, type, wakeup, callback);
    }

    public boolean sendCanNM(int source, int state, AbsMessageCallback callback) {
        return this.mManagerImpl.sendCanNM(source, state, callback);
    }

    public void setUpdateFlag(int flag, AbsMessageCallback callback) {
        this.mManagerImpl.setUpdateFlag(flag, callback);
    }

    public void setQnxOtaState(int state, AbsMessageCallback callback) {
        this.mManagerImpl.setQnxOtaState(state, callback);
    }

    public void setAndroidOtaState(int state, AbsMessageCallback callback) {
        this.mManagerImpl.setAndroidOtaState(state, callback);
    }

    public void setCloudServiceListener(AbsMessageCallback callback) {
        this.mManagerImpl.setCloudServiceListener(callback);
    }

    @Deprecated
    public void stopCloudServiceListener() {
        this.mManagerImpl.stopCloudServiceListener();
    }

    public void notifyTboxOtaState(int state) {
        this.mManagerImpl.notifyTboxOtaState(state);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] eventTypes, Class<?> type) {
        return this.mManagerImpl.get(eventTypes, type);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int set(int[] eventTypes, BYDAutoEventValue eventValue) {
        return this.mManagerImpl.set(eventTypes, eventValue);
    }

    public int getLFDoorLockStatus() {
        return this.mManagerImpl.getLFDoorLockStatus();
    }

    public void getDiagnosticTroubleCode(List<DiagnosticInfo> info, AbsMessageCallback callback) {
        this.mManagerImpl.getDiagnosticTroubleCode(info, callback);
    }

    public void clearDiagnosticTroubleCode(List<DiagnosticInfo> info, AbsMessageCallback callback) {
        this.mManagerImpl.clearDiagnosticTroubleCode(info, callback);
    }

    public int getProhibitPowerOnState() {
        return this.mManagerImpl.getProhibitPowerOnState();
    }

    public boolean setMultimediaSelfUpdate(int state) {
        return this.mManagerImpl.setMultimediaSelfUpdate(state);
    }

    public int getIg1RelayState() {
        return this.mManagerImpl.getIg1RelayState();
    }

    public int getIg4RelayState() {
        return this.mManagerImpl.getIg4RelayState();
    }

    public void resetOTA() {
        this.mManagerImpl.resetOTA();
    }

    public int updateHsm(String path) {
        return this.mManagerImpl.updateHsm(path);
    }

    public void setUsbUpdateState(int state) {
        this.mManagerImpl.setUsbUpdateState(state);
    }
}
