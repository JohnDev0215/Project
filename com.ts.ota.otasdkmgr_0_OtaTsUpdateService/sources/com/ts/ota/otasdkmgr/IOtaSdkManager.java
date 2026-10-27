package com.ts.ota.otasdkmgr;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ts.ota.otasdkmgr.IAbsBYDAutoOtaListener;
import com.ts.ota.otasdkmgr.IAbsBYDTBoxListener;
import com.ts.ota.otasdkmgr.IAbsMessageCallback;
import com.ts.ota.otasdkmgr.ITestCallback;
import com.ts.ota.otasdkmgr.IUpdateListener;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IOtaSdkManager extends IInterface {
    int FinishOTA() throws RemoteException;

    void QNXreboot() throws RemoteException;

    int StartOTA() throws RemoteException;

    void clearDiagnosticTroubleCode(List<DiagnosticAidlInfo> list, IAbsMessageCallback iAbsMessageCallback) throws RemoteException;

    int clearTboxLog() throws RemoteException;

    boolean decodeAndCheckSign(Bundle bundle) throws RemoteException;

    boolean deleteNfcFindKeyV2(byte[] bArr) throws RemoteException;

    void deleteNfcKey(int i) throws RemoteException;

    void deleteOwnIphoneKey(int i, String str) throws RemoteException;

    void deleteSingleNfcKey(int i, String str) throws RemoteException;

    int enableSimcardNetwork(int i) throws RemoteException;

    boolean executeCMD(Bundle bundle) throws RemoteException;

    boolean get7dfExistState() throws RemoteException;

    boolean getAQSlot(IAbsMessageCallback iAbsMessageCallback) throws RemoteException;

    String getApn1Address() throws RemoteException;

    int getApn1FailReson() throws RemoteException;

    int getApnParamFromTbox() throws RemoteException;

    String getAutoVin() throws RemoteException;

    int getBatteryManagementDeviceState() throws RemoteException;

    double getBatteryPowerVoltage() throws RemoteException;

    int getBatteryTemp(int i) throws RemoteException;

    int getBatteryVoltage(int i) throws RemoteException;

    int getBcmOtaState() throws RemoteException;

    int getBurglarState() throws RemoteException;

    byte[] getCanInfo() throws RemoteException;

    int getChargeAndDischargeSystemState() throws RemoteException;

    int getChargeState() throws RemoteException;

    int getCloudServiceConnectState() throws RemoteException;

    String getCloudServiceVersion() throws RemoteException;

    int getCloudServieEv() throws RemoteException;

    String getCurNetworkOperator() throws RemoteException;

    int getDcState() throws RemoteException;

    int getDevicetype() throws RemoteException;

    void getDiagnosticTroubleCode(List<DiagnosticAidlInfo> list, IAbsMessageCallback iAbsMessageCallback) throws RemoteException;

    int getDischargeMainContactorState() throws RemoteException;

    int getDischargeState(int i) throws RemoteException;

    byte[] getECUSoftcode() throws RemoteException;

    byte[] getECUVersion() throws RemoteException;

    byte[] getECUVersionAndSoftcode() throws RemoteException;

    boolean getEcuInfo(List<Info> list, IAbsMessageCallback iAbsMessageCallback) throws RemoteException;

    int getEpbState() throws RemoteException;

    byte[] getFaultCodeInfo() throws RemoteException;

    int[] getFeatureList() throws RemoteException;

    String getGetPermission() throws RemoteException;

    int getIg1RelayState() throws RemoteException;

    int getIg3RelayState() throws RemoteException;

    int getIg4RelayState() throws RemoteException;

    int getIgnitionState() throws RemoteException;

    int getLFDoorLockState() throws RemoteException;

    int getLFDoorLockStatus() throws RemoteException;

    int getLocalDiagStatus() throws RemoteException;

    byte[] getMCUVersionAndSoftcode() throws RemoteException;

    boolean getMobileNetworkState() throws RemoteException;

    int getNetworkOperatorList() throws RemoteException;

    byte[] getOTAInfoACK() throws RemoteException;

    int getOTAState() throws RemoteException;

    int getOTATimecountState() throws RemoteException;

    int getPowerOnForbidState() throws RemoteException;

    int getPoweroffInform() throws RemoteException;

    int getProhibitPowerOnState() throws RemoteException;

    String getSetPermission() throws RemoteException;

    int getSmartChargeState(int i) throws RemoteException;

    int getSmartPowerException() throws RemoteException;

    int getSmartPowerState() throws RemoteException;

    boolean getSmartPowerUsable() throws RemoteException;

    int getSocValue() throws RemoteException;

    String getTBoxHardwareVersion() throws RemoteException;

    String getTBoxSerialNumber() throws RemoteException;

    String getTBoxSoftVersion() throws RemoteException;

    boolean getTBoxUpgradeLog(String str) throws RemoteException;

    int getTboxNetworkState(int i) throws RemoteException;

    int getTboxState() throws RemoteException;

    String getTestString() throws RemoteException;

    int getTrafficFromTbox(int i, String str) throws RemoteException;

    int getTurnOffState() throws RemoteException;

    int getType() throws RemoteException;

    float getVehicleSpeed() throws RemoteException;

    int getVehicleType() throws RemoteException;

    String getVehicleVersion() throws RemoteException;

    String getYunUuidStr() throws RemoteException;

    boolean isConnectTbox() throws RemoteException;

    int notifyTargetID(byte[] bArr) throws RemoteException;

    int notifyTargetID2(byte[] bArr) throws RemoteException;

    int notifyTboxCmdResult(int i, int i2) throws RemoteException;

    void notifyTboxOtaState(int i) throws RemoteException;

    int notifyTboxTrafficResult(int i) throws RemoteException;

    boolean offOnlineRemoveKey97V2(byte[] bArr) throws RemoteException;

    boolean offOnlineRemoveKey98V2(byte[] bArr) throws RemoteException;

    int queryECUVersionAndSoftcode(byte[] bArr) throws RemoteException;

    void queryKeyListByKeyArray(int i, int i2, int i3, String str, byte[] bArr) throws RemoteException;

    void queryKeyListReq(int i, int i2, int i3, String str, String str2) throws RemoteException;

    int queryMCUVersionAndSoftcode(byte[] bArr) throws RemoteException;

    void registerAppActivateListener(IAbsBYDTBoxListener iAbsBYDTBoxListener) throws RemoteException;

    void registerCallback(IUpdateListener iUpdateListener) throws RemoteException;

    void registerListener(IAbsBYDAutoOtaListener iAbsBYDAutoOtaListener) throws RemoteException;

    void registerTboxListener(IAbsBYDTBoxListener iAbsBYDTBoxListener) throws RemoteException;

    void registerTestListener(ITestCallback iTestCallback) throws RemoteException;

    boolean reqNetworkOperatorMode() throws RemoteException;

    boolean reqOperatorInfo() throws RemoteException;

    boolean reqSimCardState() throws RemoteException;

    void resetOta() throws RemoteException;

    boolean sendCanNM(int i, int i2, IAbsMessageCallback iAbsMessageCallback) throws RemoteException;

    int sendECUIds(int i, byte[] bArr) throws RemoteException;

    int sendECUIds2(int i, byte[] bArr) throws RemoteException;

    int sendMonitorTable(byte[] bArr) throws RemoteException;

    int sendOTACtlCMD(byte[] bArr) throws RemoteException;

    int sendOTAData(byte[] bArr) throws RemoteException;

    int sendOTAData2(byte[] bArr) throws RemoteException;

    int sendOTAServiceData(byte[] bArr) throws RemoteException;

    int sendOTATimecountCtlCMD(int i, int i2, int i3, int i4) throws RemoteException;

    boolean setAQSlot(int i, IAbsMessageCallback iAbsMessageCallback) throws RemoteException;

    void setAllStatus() throws RemoteException;

    boolean setAllowStartTimeAfterUnlock(int i, int i2) throws RemoteException;

    boolean setAllowStartTimeAfterUnlockV2(byte[] bArr) throws RemoteException;

    void setAndroidOtaState(int i, IAbsMessageCallback iAbsMessageCallback) throws RemoteException;

    int setApn3State(boolean z) throws RemoteException;

    void setAppActivateInfo(byte b, byte b2, byte b3, byte b4, long j, long j2, byte b5, byte b6, String str) throws RemoteException;

    void setAppActivateInfoV2(int i, int i2, int i3, int i4, long j, long j2, int i5, int i6, String str) throws RemoteException;

    void setAppointmentTime(int i, int i2, int i3, int i4, int i5, IAbsMessageCallback iAbsMessageCallback) throws RemoteException;

    boolean setAutomaticNetwork() throws RemoteException;

    boolean setBackLightState(int i) throws RemoteException;

    boolean setBackupAreaDownload(int i) throws RemoteException;

    int setCanInfoSwitchState(int i) throws RemoteException;

    void setCloudServiceListener(IAbsMessageCallback iAbsMessageCallback) throws RemoteException;

    int setCloudServieEv(int i) throws RemoteException;

    boolean setEclPower(int i) throws RemoteException;

    boolean setHighVoltageCmd(int i) throws RemoteException;

    int setKeyIdOne(byte[] bArr) throws RemoteException;

    int setKeyIdTwo(byte[] bArr) throws RemoteException;

    boolean setManuallyStationedNetwork(String str, int i) throws RemoteException;

    int setMcuMessage(int i) throws RemoteException;

    void setMobileNetworkState(boolean z) throws RemoteException;

    boolean setMultimediaSelfUpdate(int i) throws RemoteException;

    int setNetworkOperator(String str) throws RemoteException;

    int setNonWhiteListNetworkEnable(boolean z) throws RemoteException;

    int setOperationCmdAndKeyType(int i, int i2) throws RemoteException;

    boolean setOtaModel(int i) throws RemoteException;

    boolean setOtaPower(int i) throws RemoteException;

    int setPowerOnForbidState(int i) throws RemoteException;

    void setQnxOtaState(int i, IAbsMessageCallback iAbsMessageCallback) throws RemoteException;

    boolean setQueryOwnersKey(int i, int i2) throws RemoteException;

    boolean setQueryOwnersKeyV2(byte[] bArr) throws RemoteException;

    boolean setQuitChargeAndDischarge(int i) throws RemoteException;

    boolean setQuitSmartCharge(int i) throws RemoteException;

    int setQxdmLogState(int i, int i2) throws RemoteException;

    boolean setRegisterKeyStartPairing(int i, int i2) throws RemoteException;

    boolean setRegisterKeyStartPairingV2(byte[] bArr) throws RemoteException;

    int setRestartTbox(int i) throws RemoteException;

    int setScreenState(int i) throws RemoteException;

    boolean setSmartPower(int i) throws RemoteException;

    int setTcpDumpState(boolean z) throws RemoteException;

    void setUpdateFlag(int i, IAbsMessageCallback iAbsMessageCallback) throws RemoteException;

    void setUsbUpdateState(int i) throws RemoteException;

    void startDownloadBackup(String str) throws RemoteException;

    void startRegisterNFCKey() throws RemoteException;

    void startUpdate(String str) throws RemoteException;

    void startUpdateTbox(String str) throws RemoteException;

    void stopCloudServiceListener(IAbsMessageCallback iAbsMessageCallback) throws RemoteException;

    boolean switchCloudServiceEnv(int i) throws RemoteException;

    int syncApnParam(String str, String str2, String str3, String str4, String str5, String str6, String str7) throws RemoteException;

    int syncMcuState() throws RemoteException;

    void unregisterCallback(IUpdateListener iUpdateListener) throws RemoteException;

    void unregisterListener(IAbsBYDAutoOtaListener iAbsBYDAutoOtaListener) throws RemoteException;

    void unregisterTboxListener(IAbsBYDTBoxListener iAbsBYDTBoxListener) throws RemoteException;

    void unregisterTestListener(ITestCallback iTestCallback) throws RemoteException;

    int updateHsm(String str) throws RemoteException;

    int uploadCdr(int i, String str) throws RemoteException;

    void uploadNfcOpenInfo() throws RemoteException;

    public static class Default implements IOtaSdkManager {
        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public String getTestString() throws RemoteException {
            return null;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int getDevicetype() throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public String getSetPermission() throws RemoteException {
            return null;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public String getGetPermission() throws RemoteException {
            return null;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public byte[] getOTAInfoACK() throws RemoteException {
            return null;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int notifyTargetID(byte[] id) throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int notifyTargetID2(byte[] channelAndId) throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int sendOTAData(byte[] data) throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int sendOTAData2(byte[] channelAndData) throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int StartOTA() throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int FinishOTA() throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int sendOTAServiceData(byte[] data) throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int sendECUIds(int purpose, byte[] ids) throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int sendOTACtlCMD(byte[] controlCmd) throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public byte[] getECUVersion() throws RemoteException {
            return null;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public byte[] getECUSoftcode() throws RemoteException {
            return null;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public byte[] getECUVersionAndSoftcode() throws RemoteException {
            return null;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int queryECUVersionAndSoftcode(byte[] ids) throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public byte[] getMCUVersionAndSoftcode() throws RemoteException {
            return null;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int queryMCUVersionAndSoftcode(byte[] ids) throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int getPoweroffInform() throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int getOTAState() throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int getOTATimecountState() throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int sendOTATimecountCtlCMD(int install, int user, int hour, int minute) throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int setScreenState(int state) throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int getDischargeMainContactorState() throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int sendECUIds2(int purpose, byte[] ids) throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int getPowerOnForbidState() throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int getLFDoorLockState() throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int setPowerOnForbidState(int state) throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public byte[] getCanInfo() throws RemoteException {
            return null;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int setCanInfoSwitchState(int state) throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public double getBatteryPowerVoltage() throws RemoteException {
            return 0.0d;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public byte[] getFaultCodeInfo() throws RemoteException {
            return null;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int getLocalDiagStatus() throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int setMcuMessage(int message) throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int syncMcuState() throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int sendMonitorTable(byte[] id) throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public void setAllStatus() throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int getType() throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int[] getFeatureList() throws RemoteException {
            return null;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public String getVehicleVersion() throws RemoteException {
            return null;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public boolean getEcuInfo(List<Info> infos, IAbsMessageCallback callback) throws RemoteException {
            return false;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public boolean getAQSlot(IAbsMessageCallback callback) throws RemoteException {
            return false;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public boolean setAQSlot(int slot, IAbsMessageCallback callback) throws RemoteException {
            return false;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public void QNXreboot() throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int getVehicleType() throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int getTurnOffState() throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int getIgnitionState() throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int getBurglarState() throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int getBatteryTemp(int batteryType) throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int getEpbState() throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public boolean getSmartPowerUsable() throws RemoteException {
            return false;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int getSmartPowerState() throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int getSmartPowerException() throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int getDcState() throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int getBatteryVoltage(int batteryType) throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int getChargeState() throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int getBatteryManagementDeviceState() throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int getDischargeState(int type) throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int getChargeAndDischargeSystemState() throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int getSmartChargeState(int batteryType) throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int getIg3RelayState() throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int getBcmOtaState() throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public boolean get7dfExistState() throws RemoteException {
            return false;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public boolean setSmartPower(int state) throws RemoteException {
            return false;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public boolean setEclPower(int state) throws RemoteException {
            return false;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public boolean setQuitSmartCharge(int state) throws RemoteException {
            return false;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public boolean setQuitChargeAndDischarge(int state) throws RemoteException {
            return false;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public boolean setOtaModel(int state) throws RemoteException {
            return false;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public boolean setOtaPower(int state) throws RemoteException {
            return false;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public boolean setHighVoltageCmd(int command) throws RemoteException {
            return false;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public boolean setBackupAreaDownload(int state) throws RemoteException {
            return false;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public boolean setBackLightState(int state) throws RemoteException {
            return false;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public void registerCallback(IUpdateListener listener) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public void unregisterCallback(IUpdateListener listener) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public void startUpdate(String path) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public void startDownloadBackup(String path) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public void startUpdateTbox(String path) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public void registerListener(IAbsBYDAutoOtaListener listener) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public void registerTboxListener(IAbsBYDTBoxListener listener) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public void registerAppActivateListener(IAbsBYDTBoxListener listener) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public void unregisterListener(IAbsBYDAutoOtaListener listener) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public void unregisterTboxListener(IAbsBYDTBoxListener listener) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public String getTBoxSerialNumber() throws RemoteException {
            return null;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public String getTBoxSoftVersion() throws RemoteException {
            return null;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public String getTBoxHardwareVersion() throws RemoteException {
            return null;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public boolean getTBoxUpgradeLog(String path) throws RemoteException {
            return false;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public boolean decodeAndCheckSign(Bundle bundle) throws RemoteException {
            return false;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public float getVehicleSpeed() throws RemoteException {
            return 0.0f;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int getSocValue() throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public void setAppointmentTime(int day, int hours, int minutes, int type, int wakeup, IAbsMessageCallback callback) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public void setUpdateFlag(int flag, IAbsMessageCallback callback) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public void setQnxOtaState(int state, IAbsMessageCallback callback) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public void setAndroidOtaState(int state, IAbsMessageCallback callback) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public void setCloudServiceListener(IAbsMessageCallback callback) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public void stopCloudServiceListener(IAbsMessageCallback callback) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public void notifyTboxOtaState(int state) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public boolean switchCloudServiceEnv(int env) throws RemoteException {
            return false;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public boolean executeCMD(Bundle cmd) throws RemoteException {
            return false;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public void setMobileNetworkState(boolean state) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public void setAppActivateInfo(byte supplierType, byte autoVinResult, byte supplierName, byte activateState, long activateTime, long licenseTime, byte activateCountry, byte activateRegion, String appPkgName) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public void setAppActivateInfoV2(int supplierType, int autoVinResult, int supplierName, int activateState, long activateTime, long licenseTime, int activateCountry, int activateRegion, String appPkgName) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int getLFDoorLockStatus() throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public boolean getMobileNetworkState() throws RemoteException {
            return false;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public void getDiagnosticTroubleCode(List<DiagnosticAidlInfo> info, IAbsMessageCallback callback) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public void clearDiagnosticTroubleCode(List<DiagnosticAidlInfo> info, IAbsMessageCallback callback) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int getProhibitPowerOnState() throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public boolean setMultimediaSelfUpdate(int state) throws RemoteException {
            return false;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int notifyTboxTrafficResult(int result) throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public String getCloudServiceVersion() throws RemoteException {
            return null;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int getCloudServieEv() throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int getCloudServiceConnectState() throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int getNetworkOperatorList() throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public String getCurNetworkOperator() throws RemoteException {
            return null;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int setNetworkOperator(String code) throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int setRestartTbox(int state) throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int getTboxState() throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public void registerTestListener(ITestCallback testCallback) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public void unregisterTestListener(ITestCallback testCallback) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int setCloudServieEv(int ev) throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public boolean isConnectTbox() throws RemoteException {
            return false;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public void queryKeyListReq(int carAccount, int entityKeyTag, int keyType, String userId, String keyId) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public void queryKeyListByKeyArray(int carAccount, int entityKeyTag, int keyType, String userId, byte[] keyId) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public void uploadNfcOpenInfo() throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public void deleteNfcKey(int op) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public void deleteSingleNfcKey(int op, String no) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public void deleteOwnIphoneKey(int op, String no) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public void startRegisterNFCKey() throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int getIg1RelayState() throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int getIg4RelayState() throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public void resetOta() throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int notifyTboxCmdResult(int cmd, int result) throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int uploadCdr(int length, String cdrStr) throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public boolean sendCanNM(int source, int state, IAbsMessageCallback callback) throws RemoteException {
            return false;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public String getAutoVin() throws RemoteException {
            return null;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int updateHsm(String path) throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int setTcpDumpState(boolean state) throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int clearTboxLog() throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int setApn3State(boolean state) throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int getApn1FailReson() throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public String getApn1Address() throws RemoteException {
            return null;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public String getYunUuidStr() throws RemoteException {
            return null;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public boolean setQueryOwnersKey(int config, int op) throws RemoteException {
            return false;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public boolean setRegisterKeyStartPairing(int config, int op) throws RemoteException {
            return false;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public boolean setAllowStartTimeAfterUnlock(int config, int value) throws RemoteException {
            return false;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int setOperationCmdAndKeyType(int cmd, int type) throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int setKeyIdOne(byte[] arr) throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int setKeyIdTwo(byte[] arr) throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public boolean setQueryOwnersKeyV2(byte[] data) throws RemoteException {
            return false;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public boolean setRegisterKeyStartPairingV2(byte[] data) throws RemoteException {
            return false;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public boolean setAllowStartTimeAfterUnlockV2(byte[] data) throws RemoteException {
            return false;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public boolean deleteNfcFindKeyV2(byte[] data) throws RemoteException {
            return false;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public boolean offOnlineRemoveKey97V2(byte[] data) throws RemoteException {
            return false;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public boolean offOnlineRemoveKey98V2(byte[] data) throws RemoteException {
            return false;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public boolean reqNetworkOperatorMode() throws RemoteException {
            return false;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public boolean reqOperatorInfo() throws RemoteException {
            return false;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public boolean setManuallyStationedNetwork(String network, int sigLevel) throws RemoteException {
            return false;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public boolean setAutomaticNetwork() throws RemoteException {
            return false;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public boolean reqSimCardState() throws RemoteException {
            return false;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int setQxdmLogState(int logState, int logType) throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public void setUsbUpdateState(int state) throws RemoteException {
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int enableSimcardNetwork(int simCardType) throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int syncApnParam(String apn, String username, String pwd, String apnType, String apnProtocol, String proxy, String port) throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int getTrafficFromTbox(int apnType, String iccid) throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int setNonWhiteListNetworkEnable(boolean enableFlag) throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int getApnParamFromTbox() throws RemoteException {
            return 0;
        }

        @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
        public int getTboxNetworkState(int apnType) throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IOtaSdkManager {
        private static final String DESCRIPTOR = "com.ts.ota.otasdkmgr.IOtaSdkManager";
        static final int TRANSACTION_FinishOTA = 11;
        static final int TRANSACTION_QNXreboot = 46;
        static final int TRANSACTION_StartOTA = 10;
        static final int TRANSACTION_clearDiagnosticTroubleCode = 107;
        static final int TRANSACTION_clearTboxLog = 139;
        static final int TRANSACTION_decodeAndCheckSign = 89;
        static final int TRANSACTION_deleteNfcFindKeyV2 = 153;
        static final int TRANSACTION_deleteNfcKey = 126;
        static final int TRANSACTION_deleteOwnIphoneKey = 128;
        static final int TRANSACTION_deleteSingleNfcKey = 127;
        static final int TRANSACTION_enableSimcardNetwork = 163;
        static final int TRANSACTION_executeCMD = 100;
        static final int TRANSACTION_get7dfExistState = 65;
        static final int TRANSACTION_getAQSlot = 44;
        static final int TRANSACTION_getApn1Address = 142;
        static final int TRANSACTION_getApn1FailReson = 141;
        static final int TRANSACTION_getApnParamFromTbox = 167;
        static final int TRANSACTION_getAutoVin = 136;
        static final int TRANSACTION_getBatteryManagementDeviceState = 59;
        static final int TRANSACTION_getBatteryPowerVoltage = 33;
        static final int TRANSACTION_getBatteryTemp = 51;
        static final int TRANSACTION_getBatteryVoltage = 57;
        static final int TRANSACTION_getBcmOtaState = 64;
        static final int TRANSACTION_getBurglarState = 50;
        static final int TRANSACTION_getCanInfo = 31;
        static final int TRANSACTION_getChargeAndDischargeSystemState = 61;
        static final int TRANSACTION_getChargeState = 58;
        static final int TRANSACTION_getCloudServiceConnectState = 113;
        static final int TRANSACTION_getCloudServiceVersion = 111;
        static final int TRANSACTION_getCloudServieEv = 112;
        static final int TRANSACTION_getCurNetworkOperator = 115;
        static final int TRANSACTION_getDcState = 56;
        static final int TRANSACTION_getDevicetype = 2;
        static final int TRANSACTION_getDiagnosticTroubleCode = 106;
        static final int TRANSACTION_getDischargeMainContactorState = 26;
        static final int TRANSACTION_getDischargeState = 60;
        static final int TRANSACTION_getECUSoftcode = 16;
        static final int TRANSACTION_getECUVersion = 15;
        static final int TRANSACTION_getECUVersionAndSoftcode = 17;
        static final int TRANSACTION_getEcuInfo = 43;
        static final int TRANSACTION_getEpbState = 52;
        static final int TRANSACTION_getFaultCodeInfo = 34;
        static final int TRANSACTION_getFeatureList = 41;
        static final int TRANSACTION_getGetPermission = 4;
        static final int TRANSACTION_getIg1RelayState = 130;
        static final int TRANSACTION_getIg3RelayState = 63;
        static final int TRANSACTION_getIg4RelayState = 131;
        static final int TRANSACTION_getIgnitionState = 49;
        static final int TRANSACTION_getLFDoorLockState = 29;
        static final int TRANSACTION_getLFDoorLockStatus = 104;
        static final int TRANSACTION_getLocalDiagStatus = 35;
        static final int TRANSACTION_getMCUVersionAndSoftcode = 19;
        static final int TRANSACTION_getMobileNetworkState = 105;
        static final int TRANSACTION_getNetworkOperatorList = 114;
        static final int TRANSACTION_getOTAInfoACK = 5;
        static final int TRANSACTION_getOTAState = 22;
        static final int TRANSACTION_getOTATimecountState = 23;
        static final int TRANSACTION_getPowerOnForbidState = 28;
        static final int TRANSACTION_getPoweroffInform = 21;
        static final int TRANSACTION_getProhibitPowerOnState = 108;
        static final int TRANSACTION_getSetPermission = 3;
        static final int TRANSACTION_getSmartChargeState = 62;
        static final int TRANSACTION_getSmartPowerException = 55;
        static final int TRANSACTION_getSmartPowerState = 54;
        static final int TRANSACTION_getSmartPowerUsable = 53;
        static final int TRANSACTION_getSocValue = 91;
        static final int TRANSACTION_getTBoxHardwareVersion = 87;
        static final int TRANSACTION_getTBoxSerialNumber = 85;
        static final int TRANSACTION_getTBoxSoftVersion = 86;
        static final int TRANSACTION_getTBoxUpgradeLog = 88;
        static final int TRANSACTION_getTboxNetworkState = 168;
        static final int TRANSACTION_getTboxState = 118;
        static final int TRANSACTION_getTestString = 1;
        static final int TRANSACTION_getTrafficFromTbox = 165;
        static final int TRANSACTION_getTurnOffState = 48;
        static final int TRANSACTION_getType = 40;
        static final int TRANSACTION_getVehicleSpeed = 90;
        static final int TRANSACTION_getVehicleType = 47;
        static final int TRANSACTION_getVehicleVersion = 42;
        static final int TRANSACTION_getYunUuidStr = 143;
        static final int TRANSACTION_isConnectTbox = 122;
        static final int TRANSACTION_notifyTargetID = 6;
        static final int TRANSACTION_notifyTargetID2 = 7;
        static final int TRANSACTION_notifyTboxCmdResult = 133;
        static final int TRANSACTION_notifyTboxOtaState = 98;
        static final int TRANSACTION_notifyTboxTrafficResult = 110;
        static final int TRANSACTION_offOnlineRemoveKey97V2 = 154;
        static final int TRANSACTION_offOnlineRemoveKey98V2 = 155;
        static final int TRANSACTION_queryECUVersionAndSoftcode = 18;
        static final int TRANSACTION_queryKeyListByKeyArray = 124;
        static final int TRANSACTION_queryKeyListReq = 123;
        static final int TRANSACTION_queryMCUVersionAndSoftcode = 20;
        static final int TRANSACTION_registerAppActivateListener = 82;
        static final int TRANSACTION_registerCallback = 75;
        static final int TRANSACTION_registerListener = 80;
        static final int TRANSACTION_registerTboxListener = 81;
        static final int TRANSACTION_registerTestListener = 119;
        static final int TRANSACTION_reqNetworkOperatorMode = 156;
        static final int TRANSACTION_reqOperatorInfo = 157;
        static final int TRANSACTION_reqSimCardState = 160;
        static final int TRANSACTION_resetOta = 132;
        static final int TRANSACTION_sendCanNM = 135;
        static final int TRANSACTION_sendECUIds = 13;
        static final int TRANSACTION_sendECUIds2 = 27;
        static final int TRANSACTION_sendMonitorTable = 38;
        static final int TRANSACTION_sendOTACtlCMD = 14;
        static final int TRANSACTION_sendOTAData = 8;
        static final int TRANSACTION_sendOTAData2 = 9;
        static final int TRANSACTION_sendOTAServiceData = 12;
        static final int TRANSACTION_sendOTATimecountCtlCMD = 24;
        static final int TRANSACTION_setAQSlot = 45;
        static final int TRANSACTION_setAllStatus = 39;
        static final int TRANSACTION_setAllowStartTimeAfterUnlock = 146;
        static final int TRANSACTION_setAllowStartTimeAfterUnlockV2 = 152;
        static final int TRANSACTION_setAndroidOtaState = 95;
        static final int TRANSACTION_setApn3State = 140;
        static final int TRANSACTION_setAppActivateInfo = 102;
        static final int TRANSACTION_setAppActivateInfoV2 = 103;
        static final int TRANSACTION_setAppointmentTime = 92;
        static final int TRANSACTION_setAutomaticNetwork = 159;
        static final int TRANSACTION_setBackLightState = 74;
        static final int TRANSACTION_setBackupAreaDownload = 73;
        static final int TRANSACTION_setCanInfoSwitchState = 32;
        static final int TRANSACTION_setCloudServiceListener = 96;
        static final int TRANSACTION_setCloudServieEv = 121;
        static final int TRANSACTION_setEclPower = 67;
        static final int TRANSACTION_setHighVoltageCmd = 72;
        static final int TRANSACTION_setKeyIdOne = 148;
        static final int TRANSACTION_setKeyIdTwo = 149;
        static final int TRANSACTION_setManuallyStationedNetwork = 158;
        static final int TRANSACTION_setMcuMessage = 36;
        static final int TRANSACTION_setMobileNetworkState = 101;
        static final int TRANSACTION_setMultimediaSelfUpdate = 109;
        static final int TRANSACTION_setNetworkOperator = 116;
        static final int TRANSACTION_setNonWhiteListNetworkEnable = 166;
        static final int TRANSACTION_setOperationCmdAndKeyType = 147;
        static final int TRANSACTION_setOtaModel = 70;
        static final int TRANSACTION_setOtaPower = 71;
        static final int TRANSACTION_setPowerOnForbidState = 30;
        static final int TRANSACTION_setQnxOtaState = 94;
        static final int TRANSACTION_setQueryOwnersKey = 144;
        static final int TRANSACTION_setQueryOwnersKeyV2 = 150;
        static final int TRANSACTION_setQuitChargeAndDischarge = 69;
        static final int TRANSACTION_setQuitSmartCharge = 68;
        static final int TRANSACTION_setQxdmLogState = 161;
        static final int TRANSACTION_setRegisterKeyStartPairing = 145;
        static final int TRANSACTION_setRegisterKeyStartPairingV2 = 151;
        static final int TRANSACTION_setRestartTbox = 117;
        static final int TRANSACTION_setScreenState = 25;
        static final int TRANSACTION_setSmartPower = 66;
        static final int TRANSACTION_setTcpDumpState = 138;
        static final int TRANSACTION_setUpdateFlag = 93;
        static final int TRANSACTION_setUsbUpdateState = 162;
        static final int TRANSACTION_startDownloadBackup = 78;
        static final int TRANSACTION_startRegisterNFCKey = 129;
        static final int TRANSACTION_startUpdate = 77;
        static final int TRANSACTION_startUpdateTbox = 79;
        static final int TRANSACTION_stopCloudServiceListener = 97;
        static final int TRANSACTION_switchCloudServiceEnv = 99;
        static final int TRANSACTION_syncApnParam = 164;
        static final int TRANSACTION_syncMcuState = 37;
        static final int TRANSACTION_unregisterCallback = 76;
        static final int TRANSACTION_unregisterListener = 83;
        static final int TRANSACTION_unregisterTboxListener = 84;
        static final int TRANSACTION_unregisterTestListener = 120;
        static final int TRANSACTION_updateHsm = 137;
        static final int TRANSACTION_uploadCdr = 134;
        static final int TRANSACTION_uploadNfcOpenInfo = 125;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IOtaSdkManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IOtaSdkManager)) {
                return (IOtaSdkManager) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            Bundle bundle;
            Bundle bundle2;
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    String testString = getTestString();
                    parcel2.writeNoException();
                    parcel2.writeString(testString);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    int devicetype = getDevicetype();
                    parcel2.writeNoException();
                    parcel2.writeInt(devicetype);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    String setPermission = getSetPermission();
                    parcel2.writeNoException();
                    parcel2.writeString(setPermission);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    String getPermission = getGetPermission();
                    parcel2.writeNoException();
                    parcel2.writeString(getPermission);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    byte[] oTAInfoACK = getOTAInfoACK();
                    parcel2.writeNoException();
                    parcel2.writeByteArray(oTAInfoACK);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iNotifyTargetID = notifyTargetID(parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(iNotifyTargetID);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iNotifyTargetID2 = notifyTargetID2(parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(iNotifyTargetID2);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iSendOTAData = sendOTAData(parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(iSendOTAData);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iSendOTAData2 = sendOTAData2(parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(iSendOTAData2);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iStartOTA = StartOTA();
                    parcel2.writeNoException();
                    parcel2.writeInt(iStartOTA);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iFinishOTA = FinishOTA();
                    parcel2.writeNoException();
                    parcel2.writeInt(iFinishOTA);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iSendOTAServiceData = sendOTAServiceData(parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(iSendOTAServiceData);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iSendECUIds = sendECUIds(parcel.readInt(), parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(iSendECUIds);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iSendOTACtlCMD = sendOTACtlCMD(parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(iSendOTACtlCMD);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    byte[] eCUVersion = getECUVersion();
                    parcel2.writeNoException();
                    parcel2.writeByteArray(eCUVersion);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    byte[] eCUSoftcode = getECUSoftcode();
                    parcel2.writeNoException();
                    parcel2.writeByteArray(eCUSoftcode);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    byte[] eCUVersionAndSoftcode = getECUVersionAndSoftcode();
                    parcel2.writeNoException();
                    parcel2.writeByteArray(eCUVersionAndSoftcode);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iQueryECUVersionAndSoftcode = queryECUVersionAndSoftcode(parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(iQueryECUVersionAndSoftcode);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    byte[] mCUVersionAndSoftcode = getMCUVersionAndSoftcode();
                    parcel2.writeNoException();
                    parcel2.writeByteArray(mCUVersionAndSoftcode);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iQueryMCUVersionAndSoftcode = queryMCUVersionAndSoftcode(parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(iQueryMCUVersionAndSoftcode);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    int poweroffInform = getPoweroffInform();
                    parcel2.writeNoException();
                    parcel2.writeInt(poweroffInform);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    int oTAState = getOTAState();
                    parcel2.writeNoException();
                    parcel2.writeInt(oTAState);
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    int oTATimecountState = getOTATimecountState();
                    parcel2.writeNoException();
                    parcel2.writeInt(oTATimecountState);
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iSendOTATimecountCtlCMD = sendOTATimecountCtlCMD(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iSendOTATimecountCtlCMD);
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    int screenState = setScreenState(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(screenState);
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    int dischargeMainContactorState = getDischargeMainContactorState();
                    parcel2.writeNoException();
                    parcel2.writeInt(dischargeMainContactorState);
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iSendECUIds2 = sendECUIds2(parcel.readInt(), parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(iSendECUIds2);
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    int powerOnForbidState = getPowerOnForbidState();
                    parcel2.writeNoException();
                    parcel2.writeInt(powerOnForbidState);
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    int lFDoorLockState = getLFDoorLockState();
                    parcel2.writeNoException();
                    parcel2.writeInt(lFDoorLockState);
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    int powerOnForbidState2 = setPowerOnForbidState(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(powerOnForbidState2);
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    byte[] canInfo = getCanInfo();
                    parcel2.writeNoException();
                    parcel2.writeByteArray(canInfo);
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    int canInfoSwitchState = setCanInfoSwitchState(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(canInfoSwitchState);
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    double batteryPowerVoltage = getBatteryPowerVoltage();
                    parcel2.writeNoException();
                    parcel2.writeDouble(batteryPowerVoltage);
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    byte[] faultCodeInfo = getFaultCodeInfo();
                    parcel2.writeNoException();
                    parcel2.writeByteArray(faultCodeInfo);
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    int localDiagStatus = getLocalDiagStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(localDiagStatus);
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    int mcuMessage = setMcuMessage(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(mcuMessage);
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iSyncMcuState = syncMcuState();
                    parcel2.writeNoException();
                    parcel2.writeInt(iSyncMcuState);
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iSendMonitorTable = sendMonitorTable(parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(iSendMonitorTable);
                    return true;
                case 39:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAllStatus();
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_getType /* 40 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int type = getType();
                    parcel2.writeNoException();
                    parcel2.writeInt(type);
                    return true;
                case TRANSACTION_getFeatureList /* 41 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int[] featureList = getFeatureList();
                    parcel2.writeNoException();
                    parcel2.writeIntArray(featureList);
                    return true;
                case TRANSACTION_getVehicleVersion /* 42 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    String vehicleVersion = getVehicleVersion();
                    parcel2.writeNoException();
                    parcel2.writeString(vehicleVersion);
                    return true;
                case 43:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean ecuInfo = getEcuInfo(parcel.createTypedArrayList(Info.CREATOR), IAbsMessageCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(ecuInfo ? 1 : 0);
                    return true;
                case TRANSACTION_getAQSlot /* 44 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean aQSlot = getAQSlot(IAbsMessageCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(aQSlot ? 1 : 0);
                    return true;
                case TRANSACTION_setAQSlot /* 45 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean aQSlot2 = setAQSlot(parcel.readInt(), IAbsMessageCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(aQSlot2 ? 1 : 0);
                    return true;
                case TRANSACTION_QNXreboot /* 46 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    QNXreboot();
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_getVehicleType /* 47 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int vehicleType = getVehicleType();
                    parcel2.writeNoException();
                    parcel2.writeInt(vehicleType);
                    return true;
                case TRANSACTION_getTurnOffState /* 48 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int turnOffState = getTurnOffState();
                    parcel2.writeNoException();
                    parcel2.writeInt(turnOffState);
                    return true;
                case TRANSACTION_getIgnitionState /* 49 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int ignitionState = getIgnitionState();
                    parcel2.writeNoException();
                    parcel2.writeInt(ignitionState);
                    return true;
                case TRANSACTION_getBurglarState /* 50 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int burglarState = getBurglarState();
                    parcel2.writeNoException();
                    parcel2.writeInt(burglarState);
                    return true;
                case TRANSACTION_getBatteryTemp /* 51 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int batteryTemp = getBatteryTemp(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(batteryTemp);
                    return true;
                case TRANSACTION_getEpbState /* 52 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int epbState = getEpbState();
                    parcel2.writeNoException();
                    parcel2.writeInt(epbState);
                    return true;
                case TRANSACTION_getSmartPowerUsable /* 53 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean smartPowerUsable = getSmartPowerUsable();
                    parcel2.writeNoException();
                    parcel2.writeInt(smartPowerUsable ? 1 : 0);
                    return true;
                case TRANSACTION_getSmartPowerState /* 54 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int smartPowerState = getSmartPowerState();
                    parcel2.writeNoException();
                    parcel2.writeInt(smartPowerState);
                    return true;
                case TRANSACTION_getSmartPowerException /* 55 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int smartPowerException = getSmartPowerException();
                    parcel2.writeNoException();
                    parcel2.writeInt(smartPowerException);
                    return true;
                case TRANSACTION_getDcState /* 56 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int dcState = getDcState();
                    parcel2.writeNoException();
                    parcel2.writeInt(dcState);
                    return true;
                case TRANSACTION_getBatteryVoltage /* 57 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int batteryVoltage = getBatteryVoltage(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(batteryVoltage);
                    return true;
                case TRANSACTION_getChargeState /* 58 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int chargeState = getChargeState();
                    parcel2.writeNoException();
                    parcel2.writeInt(chargeState);
                    return true;
                case TRANSACTION_getBatteryManagementDeviceState /* 59 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int batteryManagementDeviceState = getBatteryManagementDeviceState();
                    parcel2.writeNoException();
                    parcel2.writeInt(batteryManagementDeviceState);
                    return true;
                case TRANSACTION_getDischargeState /* 60 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int dischargeState = getDischargeState(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(dischargeState);
                    return true;
                case TRANSACTION_getChargeAndDischargeSystemState /* 61 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int chargeAndDischargeSystemState = getChargeAndDischargeSystemState();
                    parcel2.writeNoException();
                    parcel2.writeInt(chargeAndDischargeSystemState);
                    return true;
                case TRANSACTION_getSmartChargeState /* 62 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int smartChargeState = getSmartChargeState(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(smartChargeState);
                    return true;
                case TRANSACTION_getIg3RelayState /* 63 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int ig3RelayState = getIg3RelayState();
                    parcel2.writeNoException();
                    parcel2.writeInt(ig3RelayState);
                    return true;
                case TRANSACTION_getBcmOtaState /* 64 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int bcmOtaState = getBcmOtaState();
                    parcel2.writeNoException();
                    parcel2.writeInt(bcmOtaState);
                    return true;
                case TRANSACTION_get7dfExistState /* 65 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean z = get7dfExistState();
                    parcel2.writeNoException();
                    parcel2.writeInt(z ? 1 : 0);
                    return true;
                case TRANSACTION_setSmartPower /* 66 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean smartPower = setSmartPower(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(smartPower ? 1 : 0);
                    return true;
                case TRANSACTION_setEclPower /* 67 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean eclPower = setEclPower(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(eclPower ? 1 : 0);
                    return true;
                case TRANSACTION_setQuitSmartCharge /* 68 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean quitSmartCharge = setQuitSmartCharge(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(quitSmartCharge ? 1 : 0);
                    return true;
                case 69:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean quitChargeAndDischarge = setQuitChargeAndDischarge(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(quitChargeAndDischarge ? 1 : 0);
                    return true;
                case TRANSACTION_setOtaModel /* 70 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean otaModel = setOtaModel(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(otaModel ? 1 : 0);
                    return true;
                case TRANSACTION_setOtaPower /* 71 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean otaPower = setOtaPower(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(otaPower ? 1 : 0);
                    return true;
                case TRANSACTION_setHighVoltageCmd /* 72 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean highVoltageCmd = setHighVoltageCmd(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(highVoltageCmd ? 1 : 0);
                    return true;
                case TRANSACTION_setBackupAreaDownload /* 73 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean backupAreaDownload = setBackupAreaDownload(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(backupAreaDownload ? 1 : 0);
                    return true;
                case TRANSACTION_setBackLightState /* 74 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean backLightState = setBackLightState(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(backLightState ? 1 : 0);
                    return true;
                case TRANSACTION_registerCallback /* 75 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerCallback(IUpdateListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_unregisterCallback /* 76 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterCallback(IUpdateListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_startUpdate /* 77 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    startUpdate(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_startDownloadBackup /* 78 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    startDownloadBackup(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 79:
                    parcel.enforceInterface(DESCRIPTOR);
                    startUpdateTbox(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_registerListener /* 80 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerListener(IAbsBYDAutoOtaListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_registerTboxListener /* 81 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerTboxListener(IAbsBYDTBoxListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_registerAppActivateListener /* 82 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerAppActivateListener(IAbsBYDTBoxListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_unregisterListener /* 83 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterListener(IAbsBYDAutoOtaListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_unregisterTboxListener /* 84 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterTboxListener(IAbsBYDTBoxListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_getTBoxSerialNumber /* 85 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    String tBoxSerialNumber = getTBoxSerialNumber();
                    parcel2.writeNoException();
                    parcel2.writeString(tBoxSerialNumber);
                    return true;
                case TRANSACTION_getTBoxSoftVersion /* 86 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    String tBoxSoftVersion = getTBoxSoftVersion();
                    parcel2.writeNoException();
                    parcel2.writeString(tBoxSoftVersion);
                    return true;
                case TRANSACTION_getTBoxHardwareVersion /* 87 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    String tBoxHardwareVersion = getTBoxHardwareVersion();
                    parcel2.writeNoException();
                    parcel2.writeString(tBoxHardwareVersion);
                    return true;
                case TRANSACTION_getTBoxUpgradeLog /* 88 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean tBoxUpgradeLog = getTBoxUpgradeLog(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(tBoxUpgradeLog ? 1 : 0);
                    return true;
                case TRANSACTION_decodeAndCheckSign /* 89 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    } else {
                        bundle = null;
                    }
                    boolean zDecodeAndCheckSign = decodeAndCheckSign(bundle);
                    parcel2.writeNoException();
                    parcel2.writeInt(zDecodeAndCheckSign ? 1 : 0);
                    return true;
                case TRANSACTION_getVehicleSpeed /* 90 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    float vehicleSpeed = getVehicleSpeed();
                    parcel2.writeNoException();
                    parcel2.writeFloat(vehicleSpeed);
                    return true;
                case TRANSACTION_getSocValue /* 91 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int socValue = getSocValue();
                    parcel2.writeNoException();
                    parcel2.writeInt(socValue);
                    return true;
                case TRANSACTION_setAppointmentTime /* 92 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAppointmentTime(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), IAbsMessageCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_setUpdateFlag /* 93 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    setUpdateFlag(parcel.readInt(), IAbsMessageCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_setQnxOtaState /* 94 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    setQnxOtaState(parcel.readInt(), IAbsMessageCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_setAndroidOtaState /* 95 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAndroidOtaState(parcel.readInt(), IAbsMessageCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_setCloudServiceListener /* 96 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    setCloudServiceListener(IAbsMessageCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 97:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopCloudServiceListener(IAbsMessageCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_notifyTboxOtaState /* 98 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    notifyTboxOtaState(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_switchCloudServiceEnv /* 99 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zSwitchCloudServiceEnv = switchCloudServiceEnv(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zSwitchCloudServiceEnv ? 1 : 0);
                    return true;
                case 100:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        bundle2 = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    } else {
                        bundle2 = null;
                    }
                    boolean zExecuteCMD = executeCMD(bundle2);
                    parcel2.writeNoException();
                    parcel2.writeInt(zExecuteCMD ? 1 : 0);
                    return true;
                case TRANSACTION_setMobileNetworkState /* 101 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    setMobileNetworkState(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_setAppActivateInfo /* 102 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAppActivateInfo(parcel.readByte(), parcel.readByte(), parcel.readByte(), parcel.readByte(), parcel.readLong(), parcel.readLong(), parcel.readByte(), parcel.readByte(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_setAppActivateInfoV2 /* 103 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAppActivateInfoV2(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readLong(), parcel.readLong(), parcel.readInt(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_getLFDoorLockStatus /* 104 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int lFDoorLockStatus = getLFDoorLockStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(lFDoorLockStatus);
                    return true;
                case TRANSACTION_getMobileNetworkState /* 105 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean mobileNetworkState = getMobileNetworkState();
                    parcel2.writeNoException();
                    parcel2.writeInt(mobileNetworkState ? 1 : 0);
                    return true;
                case TRANSACTION_getDiagnosticTroubleCode /* 106 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    getDiagnosticTroubleCode(parcel.createTypedArrayList(DiagnosticAidlInfo.CREATOR), IAbsMessageCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_clearDiagnosticTroubleCode /* 107 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    clearDiagnosticTroubleCode(parcel.createTypedArrayList(DiagnosticAidlInfo.CREATOR), IAbsMessageCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_getProhibitPowerOnState /* 108 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int prohibitPowerOnState = getProhibitPowerOnState();
                    parcel2.writeNoException();
                    parcel2.writeInt(prohibitPowerOnState);
                    return true;
                case TRANSACTION_setMultimediaSelfUpdate /* 109 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean multimediaSelfUpdate = setMultimediaSelfUpdate(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(multimediaSelfUpdate ? 1 : 0);
                    return true;
                case 110:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iNotifyTboxTrafficResult = notifyTboxTrafficResult(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iNotifyTboxTrafficResult);
                    return true;
                case TRANSACTION_getCloudServiceVersion /* 111 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    String cloudServiceVersion = getCloudServiceVersion();
                    parcel2.writeNoException();
                    parcel2.writeString(cloudServiceVersion);
                    return true;
                case TRANSACTION_getCloudServieEv /* 112 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int cloudServieEv = getCloudServieEv();
                    parcel2.writeNoException();
                    parcel2.writeInt(cloudServieEv);
                    return true;
                case TRANSACTION_getCloudServiceConnectState /* 113 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int cloudServiceConnectState = getCloudServiceConnectState();
                    parcel2.writeNoException();
                    parcel2.writeInt(cloudServiceConnectState);
                    return true;
                case TRANSACTION_getNetworkOperatorList /* 114 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int networkOperatorList = getNetworkOperatorList();
                    parcel2.writeNoException();
                    parcel2.writeInt(networkOperatorList);
                    return true;
                case TRANSACTION_getCurNetworkOperator /* 115 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    String curNetworkOperator = getCurNetworkOperator();
                    parcel2.writeNoException();
                    parcel2.writeString(curNetworkOperator);
                    return true;
                case TRANSACTION_setNetworkOperator /* 116 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int networkOperator = setNetworkOperator(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(networkOperator);
                    return true;
                case TRANSACTION_setRestartTbox /* 117 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int restartTbox = setRestartTbox(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(restartTbox);
                    return true;
                case TRANSACTION_getTboxState /* 118 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int tboxState = getTboxState();
                    parcel2.writeNoException();
                    parcel2.writeInt(tboxState);
                    return true;
                case 119:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerTestListener(ITestCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 120:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterTestListener(ITestCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_setCloudServieEv /* 121 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int cloudServieEv2 = setCloudServieEv(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(cloudServieEv2);
                    return true;
                case TRANSACTION_isConnectTbox /* 122 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsConnectTbox = isConnectTbox();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsConnectTbox ? 1 : 0);
                    return true;
                case 123:
                    parcel.enforceInterface(DESCRIPTOR);
                    queryKeyListReq(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_queryKeyListByKeyArray /* 124 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    queryKeyListByKeyArray(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.createByteArray());
                    parcel2.writeNoException();
                    return true;
                case 125:
                    parcel.enforceInterface(DESCRIPTOR);
                    uploadNfcOpenInfo();
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_deleteNfcKey /* 126 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    deleteNfcKey(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_deleteSingleNfcKey /* 127 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    deleteSingleNfcKey(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 128:
                    parcel.enforceInterface(DESCRIPTOR);
                    deleteOwnIphoneKey(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 129:
                    parcel.enforceInterface(DESCRIPTOR);
                    startRegisterNFCKey();
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_getIg1RelayState /* 130 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int ig1RelayState = getIg1RelayState();
                    parcel2.writeNoException();
                    parcel2.writeInt(ig1RelayState);
                    return true;
                case TRANSACTION_getIg4RelayState /* 131 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int ig4RelayState = getIg4RelayState();
                    parcel2.writeNoException();
                    parcel2.writeInt(ig4RelayState);
                    return true;
                case TRANSACTION_resetOta /* 132 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    resetOta();
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_notifyTboxCmdResult /* 133 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iNotifyTboxCmdResult = notifyTboxCmdResult(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iNotifyTboxCmdResult);
                    return true;
                case TRANSACTION_uploadCdr /* 134 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iUploadCdr = uploadCdr(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iUploadCdr);
                    return true;
                case TRANSACTION_sendCanNM /* 135 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zSendCanNM = sendCanNM(parcel.readInt(), parcel.readInt(), IAbsMessageCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zSendCanNM ? 1 : 0);
                    return true;
                case TRANSACTION_getAutoVin /* 136 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    String autoVin = getAutoVin();
                    parcel2.writeNoException();
                    parcel2.writeString(autoVin);
                    return true;
                case TRANSACTION_updateHsm /* 137 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iUpdateHsm = updateHsm(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iUpdateHsm);
                    return true;
                case TRANSACTION_setTcpDumpState /* 138 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int tcpDumpState = setTcpDumpState(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(tcpDumpState);
                    return true;
                case TRANSACTION_clearTboxLog /* 139 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iClearTboxLog = clearTboxLog();
                    parcel2.writeNoException();
                    parcel2.writeInt(iClearTboxLog);
                    return true;
                case TRANSACTION_setApn3State /* 140 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int apn3State = setApn3State(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(apn3State);
                    return true;
                case TRANSACTION_getApn1FailReson /* 141 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int apn1FailReson = getApn1FailReson();
                    parcel2.writeNoException();
                    parcel2.writeInt(apn1FailReson);
                    return true;
                case TRANSACTION_getApn1Address /* 142 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    String apn1Address = getApn1Address();
                    parcel2.writeNoException();
                    parcel2.writeString(apn1Address);
                    return true;
                case 143:
                    parcel.enforceInterface(DESCRIPTOR);
                    String yunUuidStr = getYunUuidStr();
                    parcel2.writeNoException();
                    parcel2.writeString(yunUuidStr);
                    return true;
                case TRANSACTION_setQueryOwnersKey /* 144 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean queryOwnersKey = setQueryOwnersKey(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(queryOwnersKey ? 1 : 0);
                    return true;
                case TRANSACTION_setRegisterKeyStartPairing /* 145 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean registerKeyStartPairing = setRegisterKeyStartPairing(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(registerKeyStartPairing ? 1 : 0);
                    return true;
                case TRANSACTION_setAllowStartTimeAfterUnlock /* 146 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean allowStartTimeAfterUnlock = setAllowStartTimeAfterUnlock(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(allowStartTimeAfterUnlock ? 1 : 0);
                    return true;
                case TRANSACTION_setOperationCmdAndKeyType /* 147 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int operationCmdAndKeyType = setOperationCmdAndKeyType(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(operationCmdAndKeyType);
                    return true;
                case TRANSACTION_setKeyIdOne /* 148 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int keyIdOne = setKeyIdOne(parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(keyIdOne);
                    return true;
                case TRANSACTION_setKeyIdTwo /* 149 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int keyIdTwo = setKeyIdTwo(parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(keyIdTwo);
                    return true;
                case 150:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean queryOwnersKeyV2 = setQueryOwnersKeyV2(parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(queryOwnersKeyV2 ? 1 : 0);
                    return true;
                case TRANSACTION_setRegisterKeyStartPairingV2 /* 151 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean registerKeyStartPairingV2 = setRegisterKeyStartPairingV2(parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(registerKeyStartPairingV2 ? 1 : 0);
                    return true;
                case TRANSACTION_setAllowStartTimeAfterUnlockV2 /* 152 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean allowStartTimeAfterUnlockV2 = setAllowStartTimeAfterUnlockV2(parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(allowStartTimeAfterUnlockV2 ? 1 : 0);
                    return true;
                case TRANSACTION_deleteNfcFindKeyV2 /* 153 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zDeleteNfcFindKeyV2 = deleteNfcFindKeyV2(parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(zDeleteNfcFindKeyV2 ? 1 : 0);
                    return true;
                case TRANSACTION_offOnlineRemoveKey97V2 /* 154 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zOffOnlineRemoveKey97V2 = offOnlineRemoveKey97V2(parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(zOffOnlineRemoveKey97V2 ? 1 : 0);
                    return true;
                case TRANSACTION_offOnlineRemoveKey98V2 /* 155 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zOffOnlineRemoveKey98V2 = offOnlineRemoveKey98V2(parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(zOffOnlineRemoveKey98V2 ? 1 : 0);
                    return true;
                case TRANSACTION_reqNetworkOperatorMode /* 156 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqNetworkOperatorMode = reqNetworkOperatorMode();
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqNetworkOperatorMode ? 1 : 0);
                    return true;
                case TRANSACTION_reqOperatorInfo /* 157 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqOperatorInfo = reqOperatorInfo();
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqOperatorInfo ? 1 : 0);
                    return true;
                case TRANSACTION_setManuallyStationedNetwork /* 158 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean manuallyStationedNetwork = setManuallyStationedNetwork(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(manuallyStationedNetwork ? 1 : 0);
                    return true;
                case TRANSACTION_setAutomaticNetwork /* 159 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean automaticNetwork = setAutomaticNetwork();
                    parcel2.writeNoException();
                    parcel2.writeInt(automaticNetwork ? 1 : 0);
                    return true;
                case TRANSACTION_reqSimCardState /* 160 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReqSimCardState = reqSimCardState();
                    parcel2.writeNoException();
                    parcel2.writeInt(zReqSimCardState ? 1 : 0);
                    return true;
                case TRANSACTION_setQxdmLogState /* 161 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int qxdmLogState = setQxdmLogState(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(qxdmLogState);
                    return true;
                case TRANSACTION_setUsbUpdateState /* 162 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    setUsbUpdateState(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_enableSimcardNetwork /* 163 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iEnableSimcardNetwork = enableSimcardNetwork(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iEnableSimcardNetwork);
                    return true;
                case TRANSACTION_syncApnParam /* 164 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iSyncApnParam = syncApnParam(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iSyncApnParam);
                    return true;
                case TRANSACTION_getTrafficFromTbox /* 165 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int trafficFromTbox = getTrafficFromTbox(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(trafficFromTbox);
                    return true;
                case TRANSACTION_setNonWhiteListNetworkEnable /* 166 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int nonWhiteListNetworkEnable = setNonWhiteListNetworkEnable(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(nonWhiteListNetworkEnable);
                    return true;
                case TRANSACTION_getApnParamFromTbox /* 167 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int apnParamFromTbox = getApnParamFromTbox();
                    parcel2.writeNoException();
                    parcel2.writeInt(apnParamFromTbox);
                    return true;
                case TRANSACTION_getTboxNetworkState /* 168 */:
                    parcel.enforceInterface(DESCRIPTOR);
                    int tboxNetworkState = getTboxNetworkState(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(tboxNetworkState);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IOtaSdkManager {
            public static IOtaSdkManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public String getTestString() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTestString();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int getDevicetype() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDevicetype();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public String getSetPermission() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSetPermission();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public String getGetPermission() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getGetPermission();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public byte[] getOTAInfoACK() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getOTAInfoACK();
                    }
                    _reply.readException();
                    byte[] _result = _reply.createByteArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int notifyTargetID(byte[] id) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(id);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().notifyTargetID(id);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int notifyTargetID2(byte[] channelAndId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(channelAndId);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().notifyTargetID2(channelAndId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int sendOTAData(byte[] data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(data);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().sendOTAData(data);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int sendOTAData2(byte[] channelAndData) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(channelAndData);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().sendOTAData2(channelAndData);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int StartOTA() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().StartOTA();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int FinishOTA() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().FinishOTA();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int sendOTAServiceData(byte[] data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(data);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().sendOTAServiceData(data);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int sendECUIds(int purpose, byte[] ids) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(purpose);
                    _data.writeByteArray(ids);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().sendECUIds(purpose, ids);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int sendOTACtlCMD(byte[] controlCmd) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(controlCmd);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().sendOTACtlCMD(controlCmd);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public byte[] getECUVersion() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getECUVersion();
                    }
                    _reply.readException();
                    byte[] _result = _reply.createByteArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public byte[] getECUSoftcode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getECUSoftcode();
                    }
                    _reply.readException();
                    byte[] _result = _reply.createByteArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public byte[] getECUVersionAndSoftcode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(17, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getECUVersionAndSoftcode();
                    }
                    _reply.readException();
                    byte[] _result = _reply.createByteArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int queryECUVersionAndSoftcode(byte[] ids) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(ids);
                    boolean _status = this.mRemote.transact(18, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().queryECUVersionAndSoftcode(ids);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public byte[] getMCUVersionAndSoftcode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(19, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMCUVersionAndSoftcode();
                    }
                    _reply.readException();
                    byte[] _result = _reply.createByteArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int queryMCUVersionAndSoftcode(byte[] ids) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(ids);
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().queryMCUVersionAndSoftcode(ids);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int getPoweroffInform() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(21, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPoweroffInform();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int getOTAState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(22, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getOTAState();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int getOTATimecountState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(23, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getOTATimecountState();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int sendOTATimecountCtlCMD(int install, int user, int hour, int minute) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(install);
                    _data.writeInt(user);
                    _data.writeInt(hour);
                    _data.writeInt(minute);
                    boolean _status = this.mRemote.transact(24, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().sendOTATimecountCtlCMD(install, user, hour, minute);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int setScreenState(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(25, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setScreenState(state);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int getDischargeMainContactorState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(26, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDischargeMainContactorState();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int sendECUIds2(int purpose, byte[] ids) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(purpose);
                    _data.writeByteArray(ids);
                    boolean _status = this.mRemote.transact(27, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().sendECUIds2(purpose, ids);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int getPowerOnForbidState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(28, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPowerOnForbidState();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int getLFDoorLockState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(29, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLFDoorLockState();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int setPowerOnForbidState(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(30, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setPowerOnForbidState(state);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public byte[] getCanInfo() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(31, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCanInfo();
                    }
                    _reply.readException();
                    byte[] _result = _reply.createByteArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int setCanInfoSwitchState(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(32, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setCanInfoSwitchState(state);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public double getBatteryPowerVoltage() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(33, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBatteryPowerVoltage();
                    }
                    _reply.readException();
                    double _result = _reply.readDouble();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public byte[] getFaultCodeInfo() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(34, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFaultCodeInfo();
                    }
                    _reply.readException();
                    byte[] _result = _reply.createByteArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int getLocalDiagStatus() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(35, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLocalDiagStatus();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int setMcuMessage(int message) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(message);
                    boolean _status = this.mRemote.transact(36, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setMcuMessage(message);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int syncMcuState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(37, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().syncMcuState();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int sendMonitorTable(byte[] id) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(id);
                    boolean _status = this.mRemote.transact(38, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().sendMonitorTable(id);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public void setAllStatus() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(39, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAllStatus();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int getType() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getType, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getType();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int[] getFeatureList() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getFeatureList, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFeatureList();
                    }
                    _reply.readException();
                    int[] _result = _reply.createIntArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public String getVehicleVersion() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getVehicleVersion, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVehicleVersion();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public boolean getEcuInfo(List<Info> infos, IAbsMessageCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeTypedList(infos);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(43, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEcuInfo(infos, callback);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public boolean getAQSlot(IAbsMessageCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getAQSlot, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAQSlot(callback);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public boolean setAQSlot(int slot, IAbsMessageCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(slot);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setAQSlot, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setAQSlot(slot, callback);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public void QNXreboot() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_QNXreboot, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().QNXreboot();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int getVehicleType() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getVehicleType, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVehicleType();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int getTurnOffState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getTurnOffState, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTurnOffState();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int getIgnitionState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getIgnitionState, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getIgnitionState();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int getBurglarState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getBurglarState, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBurglarState();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int getBatteryTemp(int batteryType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(batteryType);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getBatteryTemp, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBatteryTemp(batteryType);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int getEpbState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getEpbState, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEpbState();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public boolean getSmartPowerUsable() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getSmartPowerUsable, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSmartPowerUsable();
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int getSmartPowerState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getSmartPowerState, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSmartPowerState();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int getSmartPowerException() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getSmartPowerException, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSmartPowerException();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int getDcState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getDcState, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDcState();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int getBatteryVoltage(int batteryType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(batteryType);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getBatteryVoltage, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBatteryVoltage(batteryType);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int getChargeState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getChargeState, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getChargeState();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int getBatteryManagementDeviceState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getBatteryManagementDeviceState, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBatteryManagementDeviceState();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int getDischargeState(int type) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(type);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getDischargeState, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDischargeState(type);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int getChargeAndDischargeSystemState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getChargeAndDischargeSystemState, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getChargeAndDischargeSystemState();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int getSmartChargeState(int batteryType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(batteryType);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getSmartChargeState, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSmartChargeState(batteryType);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int getIg3RelayState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getIg3RelayState, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getIg3RelayState();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int getBcmOtaState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getBcmOtaState, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBcmOtaState();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public boolean get7dfExistState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_get7dfExistState, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().get7dfExistState();
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public boolean setSmartPower(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setSmartPower, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setSmartPower(state);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public boolean setEclPower(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setEclPower, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setEclPower(state);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public boolean setQuitSmartCharge(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setQuitSmartCharge, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setQuitSmartCharge(state);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public boolean setQuitChargeAndDischarge(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(69, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setQuitChargeAndDischarge(state);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public boolean setOtaModel(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setOtaModel, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setOtaModel(state);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public boolean setOtaPower(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setOtaPower, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setOtaPower(state);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public boolean setHighVoltageCmd(int command) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(command);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setHighVoltageCmd, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setHighVoltageCmd(command);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public boolean setBackupAreaDownload(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setBackupAreaDownload, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setBackupAreaDownload(state);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public boolean setBackLightState(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setBackLightState, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setBackLightState(state);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public void registerCallback(IUpdateListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_registerCallback, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerCallback(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public void unregisterCallback(IUpdateListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_unregisterCallback, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterCallback(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public void startUpdate(String path) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(path);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_startUpdate, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startUpdate(path);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public void startDownloadBackup(String path) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(path);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_startDownloadBackup, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startDownloadBackup(path);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public void startUpdateTbox(String path) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(path);
                    boolean _status = this.mRemote.transact(79, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startUpdateTbox(path);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public void registerListener(IAbsBYDAutoOtaListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_registerListener, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerListener(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public void registerTboxListener(IAbsBYDTBoxListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_registerTboxListener, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerTboxListener(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public void registerAppActivateListener(IAbsBYDTBoxListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_registerAppActivateListener, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerAppActivateListener(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public void unregisterListener(IAbsBYDAutoOtaListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_unregisterListener, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterListener(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public void unregisterTboxListener(IAbsBYDTBoxListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_unregisterTboxListener, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterTboxListener(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public String getTBoxSerialNumber() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getTBoxSerialNumber, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTBoxSerialNumber();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public String getTBoxSoftVersion() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getTBoxSoftVersion, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTBoxSoftVersion();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public String getTBoxHardwareVersion() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getTBoxHardwareVersion, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTBoxHardwareVersion();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public boolean getTBoxUpgradeLog(String path) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(path);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getTBoxUpgradeLog, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTBoxUpgradeLog(path);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public boolean decodeAndCheckSign(Bundle bundle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        _data.writeInt(1);
                        bundle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_decodeAndCheckSign, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().decodeAndCheckSign(bundle);
                    }
                    _reply.readException();
                    boolean _result = _reply.readInt() != 0;
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public float getVehicleSpeed() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getVehicleSpeed, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVehicleSpeed();
                    }
                    _reply.readException();
                    float _result = _reply.readFloat();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int getSocValue() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getSocValue, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSocValue();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public void setAppointmentTime(int day, int hours, int minutes, int type, int wakeup, IAbsMessageCallback callback) throws Throwable {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    _data.writeInt(day);
                } catch (Throwable th2) {
                    th = th2;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
                try {
                    _data.writeInt(hours);
                } catch (Throwable th3) {
                    th = th3;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
                try {
                    _data.writeInt(minutes);
                    try {
                        _data.writeInt(type);
                        try {
                            _data.writeInt(wakeup);
                            _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                        } catch (Throwable th4) {
                            th = th4;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                    try {
                        boolean _status = this.mRemote.transact(Stub.TRANSACTION_setAppointmentTime, _data, _reply, 0);
                        if (!_status && Stub.getDefaultImpl() != null) {
                            Stub.getDefaultImpl().setAppointmentTime(day, hours, minutes, type, wakeup, callback);
                            _reply.recycle();
                            _data.recycle();
                        } else {
                            _reply.readException();
                            _reply.recycle();
                            _data.recycle();
                        }
                    } catch (Throwable th6) {
                        th = th6;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th7) {
                    th = th7;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public void setUpdateFlag(int flag, IAbsMessageCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(flag);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setUpdateFlag, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setUpdateFlag(flag, callback);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public void setQnxOtaState(int state, IAbsMessageCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setQnxOtaState, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setQnxOtaState(state, callback);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public void setAndroidOtaState(int state, IAbsMessageCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setAndroidOtaState, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAndroidOtaState(state, callback);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public void setCloudServiceListener(IAbsMessageCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setCloudServiceListener, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setCloudServiceListener(callback);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public void stopCloudServiceListener(IAbsMessageCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(97, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopCloudServiceListener(callback);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public void notifyTboxOtaState(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_notifyTboxOtaState, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().notifyTboxOtaState(state);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public boolean switchCloudServiceEnv(int env) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(env);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_switchCloudServiceEnv, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().switchCloudServiceEnv(env);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public boolean executeCMD(Bundle cmd) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (cmd != null) {
                        _data.writeInt(1);
                        cmd.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(100, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().executeCMD(cmd);
                    }
                    _reply.readException();
                    boolean _result = _reply.readInt() != 0;
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public void setMobileNetworkState(boolean state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state ? 1 : 0);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setMobileNetworkState, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setMobileNetworkState(state);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public void setAppActivateInfo(byte supplierType, byte autoVinResult, byte supplierName, byte activateState, long activateTime, long licenseTime, byte activateCountry, byte activateRegion, String appPkgName) throws Throwable {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    _data.writeByte(supplierType);
                    _data.writeByte(autoVinResult);
                    _data.writeByte(supplierName);
                    _data.writeByte(activateState);
                    _data.writeLong(activateTime);
                    _data.writeLong(licenseTime);
                    _data.writeByte(activateCountry);
                    _data.writeByte(activateRegion);
                    _data.writeString(appPkgName);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setAppActivateInfo, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAppActivateInfo(supplierType, autoVinResult, supplierName, activateState, activateTime, licenseTime, activateCountry, activateRegion, appPkgName);
                        _reply.recycle();
                        _data.recycle();
                    } else {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public void setAppActivateInfoV2(int supplierType, int autoVinResult, int supplierName, int activateState, long activateTime, long licenseTime, int activateCountry, int activateRegion, String appPkgName) throws Throwable {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    _data.writeInt(supplierType);
                    _data.writeInt(autoVinResult);
                    _data.writeInt(supplierName);
                    _data.writeInt(activateState);
                    _data.writeLong(activateTime);
                    _data.writeLong(licenseTime);
                    _data.writeInt(activateCountry);
                    _data.writeInt(activateRegion);
                    _data.writeString(appPkgName);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setAppActivateInfoV2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAppActivateInfoV2(supplierType, autoVinResult, supplierName, activateState, activateTime, licenseTime, activateCountry, activateRegion, appPkgName);
                        _reply.recycle();
                        _data.recycle();
                    } else {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int getLFDoorLockStatus() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getLFDoorLockStatus, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLFDoorLockStatus();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public boolean getMobileNetworkState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getMobileNetworkState, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMobileNetworkState();
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public void getDiagnosticTroubleCode(List<DiagnosticAidlInfo> info, IAbsMessageCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeTypedList(info);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getDiagnosticTroubleCode, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getDiagnosticTroubleCode(info, callback);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public void clearDiagnosticTroubleCode(List<DiagnosticAidlInfo> info, IAbsMessageCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeTypedList(info);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_clearDiagnosticTroubleCode, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clearDiagnosticTroubleCode(info, callback);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int getProhibitPowerOnState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getProhibitPowerOnState, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getProhibitPowerOnState();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public boolean setMultimediaSelfUpdate(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setMultimediaSelfUpdate, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setMultimediaSelfUpdate(state);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int notifyTboxTrafficResult(int result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(result);
                    boolean _status = this.mRemote.transact(110, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().notifyTboxTrafficResult(result);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public String getCloudServiceVersion() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getCloudServiceVersion, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCloudServiceVersion();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int getCloudServieEv() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getCloudServieEv, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCloudServieEv();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int getCloudServiceConnectState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getCloudServiceConnectState, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCloudServiceConnectState();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int getNetworkOperatorList() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getNetworkOperatorList, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNetworkOperatorList();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public String getCurNetworkOperator() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getCurNetworkOperator, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurNetworkOperator();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int setNetworkOperator(String code) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(code);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setNetworkOperator, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setNetworkOperator(code);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int setRestartTbox(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setRestartTbox, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setRestartTbox(state);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int getTboxState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getTboxState, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTboxState();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public void registerTestListener(ITestCallback testCallback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(testCallback != null ? testCallback.asBinder() : null);
                    boolean _status = this.mRemote.transact(119, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerTestListener(testCallback);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public void unregisterTestListener(ITestCallback testCallback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(testCallback != null ? testCallback.asBinder() : null);
                    boolean _status = this.mRemote.transact(120, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterTestListener(testCallback);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int setCloudServieEv(int ev) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(ev);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setCloudServieEv, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setCloudServieEv(ev);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public boolean isConnectTbox() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_isConnectTbox, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isConnectTbox();
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public void queryKeyListReq(int carAccount, int entityKeyTag, int keyType, String userId, String keyId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(carAccount);
                    _data.writeInt(entityKeyTag);
                    _data.writeInt(keyType);
                    _data.writeString(userId);
                    _data.writeString(keyId);
                    boolean _status = this.mRemote.transact(123, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().queryKeyListReq(carAccount, entityKeyTag, keyType, userId, keyId);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public void queryKeyListByKeyArray(int carAccount, int entityKeyTag, int keyType, String userId, byte[] keyId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(carAccount);
                    _data.writeInt(entityKeyTag);
                    _data.writeInt(keyType);
                    _data.writeString(userId);
                    _data.writeByteArray(keyId);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_queryKeyListByKeyArray, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().queryKeyListByKeyArray(carAccount, entityKeyTag, keyType, userId, keyId);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public void uploadNfcOpenInfo() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(125, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().uploadNfcOpenInfo();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public void deleteNfcKey(int op) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(op);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_deleteNfcKey, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deleteNfcKey(op);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public void deleteSingleNfcKey(int op, String no) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(op);
                    _data.writeString(no);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_deleteSingleNfcKey, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deleteSingleNfcKey(op, no);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public void deleteOwnIphoneKey(int op, String no) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(op);
                    _data.writeString(no);
                    boolean _status = this.mRemote.transact(128, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deleteOwnIphoneKey(op, no);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public void startRegisterNFCKey() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(129, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startRegisterNFCKey();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int getIg1RelayState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getIg1RelayState, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getIg1RelayState();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int getIg4RelayState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getIg4RelayState, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getIg4RelayState();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public void resetOta() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_resetOta, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().resetOta();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int notifyTboxCmdResult(int cmd, int result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(cmd);
                    _data.writeInt(result);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_notifyTboxCmdResult, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().notifyTboxCmdResult(cmd, result);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int uploadCdr(int length, String cdrStr) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(length);
                    _data.writeString(cdrStr);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_uploadCdr, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().uploadCdr(length, cdrStr);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public boolean sendCanNM(int source, int state, IAbsMessageCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(source);
                    _data.writeInt(state);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_sendCanNM, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().sendCanNM(source, state, callback);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public String getAutoVin() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getAutoVin, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAutoVin();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int updateHsm(String path) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(path);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_updateHsm, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().updateHsm(path);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int setTcpDumpState(boolean state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state ? 1 : 0);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setTcpDumpState, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setTcpDumpState(state);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int clearTboxLog() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_clearTboxLog, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().clearTboxLog();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int setApn3State(boolean state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state ? 1 : 0);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setApn3State, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setApn3State(state);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int getApn1FailReson() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getApn1FailReson, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getApn1FailReson();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public String getApn1Address() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getApn1Address, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getApn1Address();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public String getYunUuidStr() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(143, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getYunUuidStr();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public boolean setQueryOwnersKey(int config, int op) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(config);
                    _data.writeInt(op);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setQueryOwnersKey, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setQueryOwnersKey(config, op);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public boolean setRegisterKeyStartPairing(int config, int op) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(config);
                    _data.writeInt(op);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setRegisterKeyStartPairing, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setRegisterKeyStartPairing(config, op);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public boolean setAllowStartTimeAfterUnlock(int config, int value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(config);
                    _data.writeInt(value);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setAllowStartTimeAfterUnlock, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setAllowStartTimeAfterUnlock(config, value);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int setOperationCmdAndKeyType(int cmd, int type) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(cmd);
                    _data.writeInt(type);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setOperationCmdAndKeyType, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setOperationCmdAndKeyType(cmd, type);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int setKeyIdOne(byte[] arr) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(arr);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setKeyIdOne, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setKeyIdOne(arr);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int setKeyIdTwo(byte[] arr) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(arr);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setKeyIdTwo, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setKeyIdTwo(arr);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public boolean setQueryOwnersKeyV2(byte[] data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(data);
                    boolean _status = this.mRemote.transact(150, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setQueryOwnersKeyV2(data);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public boolean setRegisterKeyStartPairingV2(byte[] data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(data);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setRegisterKeyStartPairingV2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setRegisterKeyStartPairingV2(data);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public boolean setAllowStartTimeAfterUnlockV2(byte[] data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(data);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setAllowStartTimeAfterUnlockV2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setAllowStartTimeAfterUnlockV2(data);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public boolean deleteNfcFindKeyV2(byte[] data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(data);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_deleteNfcFindKeyV2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().deleteNfcFindKeyV2(data);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public boolean offOnlineRemoveKey97V2(byte[] data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(data);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_offOnlineRemoveKey97V2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().offOnlineRemoveKey97V2(data);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public boolean offOnlineRemoveKey98V2(byte[] data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(data);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_offOnlineRemoveKey98V2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().offOnlineRemoveKey98V2(data);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public boolean reqNetworkOperatorMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqNetworkOperatorMode, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reqNetworkOperatorMode();
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public boolean reqOperatorInfo() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqOperatorInfo, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reqOperatorInfo();
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public boolean setManuallyStationedNetwork(String network, int sigLevel) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(network);
                    _data.writeInt(sigLevel);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setManuallyStationedNetwork, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setManuallyStationedNetwork(network, sigLevel);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public boolean setAutomaticNetwork() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setAutomaticNetwork, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setAutomaticNetwork();
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public boolean reqSimCardState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_reqSimCardState, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().reqSimCardState();
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int setQxdmLogState(int logState, int logType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(logState);
                    _data.writeInt(logType);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setQxdmLogState, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setQxdmLogState(logState, logType);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public void setUsbUpdateState(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setUsbUpdateState, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setUsbUpdateState(state);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int enableSimcardNetwork(int simCardType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(simCardType);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_enableSimcardNetwork, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enableSimcardNetwork(simCardType);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int syncApnParam(String apn, String username, String pwd, String apnType, String apnProtocol, String proxy, String port) throws Throwable {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    _data.writeString(apn);
                } catch (Throwable th2) {
                    th = th2;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
                try {
                    _data.writeString(username);
                } catch (Throwable th3) {
                    th = th3;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
                try {
                    _data.writeString(pwd);
                } catch (Throwable th4) {
                    th = th4;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
                try {
                    _data.writeString(apnType);
                } catch (Throwable th5) {
                    th = th5;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
                try {
                    _data.writeString(apnProtocol);
                    _data.writeString(proxy);
                    _data.writeString(port);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_syncApnParam, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        int iSyncApnParam = Stub.getDefaultImpl().syncApnParam(apn, username, pwd, apnType, apnProtocol, proxy, port);
                        _reply.recycle();
                        _data.recycle();
                        return iSyncApnParam;
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } catch (Throwable th6) {
                    th = th6;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int getTrafficFromTbox(int apnType, String iccid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(apnType);
                    _data.writeString(iccid);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getTrafficFromTbox, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTrafficFromTbox(apnType, iccid);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int setNonWhiteListNetworkEnable(boolean enableFlag) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(enableFlag ? 1 : 0);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setNonWhiteListNetworkEnable, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setNonWhiteListNetworkEnable(enableFlag);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int getApnParamFromTbox() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getApnParamFromTbox, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getApnParamFromTbox();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.ota.otasdkmgr.IOtaSdkManager
            public int getTboxNetworkState(int apnType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(apnType);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getTboxNetworkState, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTboxNetworkState(apnType);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOtaSdkManager impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IOtaSdkManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
