package com.ts.ota.otasdkmgr;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class OtaSdkManager {
    private static final String TAG = "dota.OtaSdkManager";
    private static OtaSdkManager mImpl;

    public abstract int FinishOTA();

    public abstract void QNXreboot();

    public abstract int StartOTA();

    public abstract void clearDiagnosticTroubleCode(List<DiagnosticAidlInfo> list, IAbsMessageCallback iAbsMessageCallback);

    public abstract int clearTboxLog();

    public abstract boolean decodeAndCheckSign(Bundle bundle);

    public abstract boolean deleteNfcFindKeyV2(byte[] bArr);

    public abstract void deleteNfcKey(int i);

    public abstract void deleteOwnIphoneKey(int i, String str);

    public abstract void deleteSingleNfcKey(int i, String str);

    public abstract int enableSimcardNetwork(int i);

    public abstract boolean executeCMD(Bundle bundle);

    public abstract boolean get7dfExistState();

    public abstract boolean getAQSlot(IAbsMessageCallback iAbsMessageCallback);

    public abstract String getApn1Address();

    public abstract int getApn1FailReson();

    public abstract int getApnParamFromTbox();

    public abstract String getAutoVin();

    public abstract int getBatteryManagementDeviceState();

    public abstract double getBatteryPowerVoltage();

    public abstract int getBatteryTemp(int i);

    public abstract int getBatteryType();

    public abstract int getBatteryVoltage(int i);

    public abstract int getBcmOtaState();

    public abstract int getBurglarState();

    public abstract byte[] getCanInfo();

    public abstract int getChargeAndDischargeSystemState();

    public abstract int getChargeState();

    public abstract int getCloudServiceConnectState();

    public abstract String getCloudServiceVersion();

    public abstract int getCloudServieEv();

    public abstract String getCurNetworkOperator();

    public abstract int getDcState();

    public abstract int getDevicetype();

    public abstract void getDiagnosticTroubleCode(List<DiagnosticAidlInfo> list, IAbsMessageCallback iAbsMessageCallback);

    public abstract int getDischargeMainContactorState();

    public abstract int getDischargeState(int i);

    public abstract byte[] getECUSoftcode();

    public abstract byte[] getECUVersion();

    public abstract byte[] getECUVersionAndSoftcode();

    public abstract boolean getEcuInfo(List<Info> list, IAbsMessageCallback iAbsMessageCallback);

    public abstract int getEpbState();

    public abstract byte[] getFaultCodeInfo();

    public abstract int[] getFeatureList();

    public abstract String getGetPermission();

    public abstract int getIg1RelayState();

    public abstract int getIg3RelayState();

    public abstract int getIg4RelayState();

    public abstract int getIgnitionState();

    public abstract int getLFDoorLockState();

    public abstract int getLFDoorLockStatus();

    public abstract int getLocalDiagStatus();

    public abstract byte[] getMCUVersionAndSoftcode();

    public abstract boolean getMobileNetworkState();

    public abstract int getNetworkOperatorList();

    public abstract byte[] getOTAInfoACK();

    public abstract int getOTAState();

    public abstract int getOTATimecountState();

    public abstract int getPowerOnForbidState();

    public abstract int getPoweroffInform();

    public abstract int getProhibitPowerOnState();

    public abstract String getSetPermission();

    public abstract int getSmartChargeState(int i);

    public abstract int getSmartPowerException();

    public abstract int getSmartPowerState();

    public abstract boolean getSmartPowerUsable();

    public abstract int getSocValue();

    public abstract String getTBoxHardwareVersion();

    public abstract String getTBoxSerialNumber();

    public abstract String getTBoxSoftVersion();

    public abstract boolean getTBoxUpgradeLog(String str);

    public abstract int getTboxNetworkState(int i);

    public abstract int getTboxState();

    public abstract String getTestString();

    public abstract int getTrafficFromTbox(int i, String str);

    public abstract int getTurnOffState();

    public abstract int getType();

    public abstract float getVehicleSpeed();

    public abstract int getVehicleType();

    public abstract String getVehicleVersion();

    public abstract String getYunUuidStr();

    public abstract boolean isConnectTbox();

    public abstract int notifyTargetID(byte[] bArr);

    public abstract int notifyTargetID2(byte[] bArr);

    public abstract int notifyTboxCmdResult(int i, int i2);

    public abstract void notifyTboxOtaState(int i);

    public abstract int notifyTboxTrafficResult(int i);

    public abstract boolean offOnlineRemoveKey97V2(byte[] bArr);

    public abstract boolean offOnlineRemoveKey98V2(byte[] bArr);

    public abstract int queryECUVersionAndSoftcode(byte[] bArr);

    public abstract void queryKeyListReq(int i, int i2, int i3, String str, String str2);

    public abstract void queryKeyListReq(int i, int i2, int i3, String str, byte[] bArr);

    public abstract int queryMCUVersionAndSoftcode(byte[] bArr);

    public abstract boolean ready();

    public abstract void registerAppActivateListener(IAbsBYDTBoxListener iAbsBYDTBoxListener);

    public abstract void registerCallback(IUpdateListener iUpdateListener);

    public abstract void registerListener(IAbsBYDAutoOtaListener iAbsBYDAutoOtaListener);

    public abstract void registerTboxListener(IAbsBYDTBoxListener iAbsBYDTBoxListener);

    public abstract void registerTboxListener(ITboxCallBack iTboxCallBack);

    public abstract void registerTestListener(ITestTboxCallBack iTestTboxCallBack);

    public abstract boolean relayDataToTbox40A(byte[] bArr);

    public abstract boolean reqNetworkOperatorMode();

    public abstract boolean reqOperatorInfo();

    public abstract boolean reqSimCardState();

    public abstract void resetOta();

    public abstract boolean sendCanNM(int i, int i2, IAbsMessageCallback iAbsMessageCallback);

    public abstract int sendECUIds(int i, byte[] bArr);

    public abstract int sendECUIds2(int i, byte[] bArr);

    public abstract int sendMonitorTable(byte[] bArr);

    public abstract int sendOTACtlCMD(byte[] bArr);

    public abstract int sendOTAData(byte[] bArr);

    public abstract int sendOTAData2(byte[] bArr);

    public abstract int sendOTAServiceData(byte[] bArr);

    public abstract int sendOTATimecountCtlCMD(int i, int i2, int i3, int i4);

    public abstract boolean sendRemoteLockOrUnlockStatus(int i, int i2);

    public abstract boolean setAQSlot(int i, IAbsMessageCallback iAbsMessageCallback);

    public abstract void setAllStatus();

    public abstract boolean setAllowStartTimeAfterUnlock(int i, int i2);

    public abstract boolean setAllowStartTimeAfterUnlockV2(byte[] bArr);

    public abstract void setAndroidOtaState(int i, IAbsMessageCallback iAbsMessageCallback);

    public abstract int setApn3State(boolean z);

    public abstract void setAppActivateInfo(byte b, byte b2, byte b3, byte b4, long j, long j2, byte b5, byte b6, String str);

    public abstract void setAppActivateInfo(int i, int i2, int i3, int i4, long j, long j2, int i5, int i6, String str);

    public abstract void setAppointmentTime(int i, int i2, int i3, int i4, int i5, IAbsMessageCallback iAbsMessageCallback);

    public abstract boolean setAutomaticNetwork();

    public abstract boolean setBackLightState(int i);

    public abstract boolean setBackupAreaDownload(int i);

    public abstract int setCanInfoSwitchState(int i);

    public abstract void setCloudServiceListener(IAbsMessageCallback iAbsMessageCallback);

    public abstract int setCloudServieEv(int i);

    public abstract boolean setEclPower(int i);

    public abstract boolean setHighVoltageCmd(int i);

    public abstract int setKeyIdOne(byte[] bArr);

    public abstract int setKeyIdTwo(byte[] bArr);

    public abstract boolean setManuallyStationedNetwork(String str, int i);

    public abstract int setMcuMessage(int i);

    public abstract void setMobileNetworkState(boolean z);

    public abstract boolean setMultimediaSelfUpdate(int i);

    public abstract int setNetworkOperator(String str);

    public abstract int setNonWhiteListNetworkEnable(boolean z);

    public abstract int setOperationCmdAndKeyType(int i, int i2);

    public abstract boolean setOtaModel(int i);

    public abstract boolean setOtaPower(int i);

    public abstract int setPowerOnForbidState(int i);

    public abstract void setQnxOtaState(int i, IAbsMessageCallback iAbsMessageCallback);

    public abstract boolean setQueryOwnersKey(int i, int i2);

    public abstract boolean setQueryOwnersKeyV2(byte[] bArr);

    public abstract boolean setQuitChargeAndDischarge(int i);

    public abstract boolean setQuitSmartCharge(int i);

    public abstract int setQxdmLogState(int i, int i2);

    public abstract boolean setRegisterKeyStartPairing(int i, int i2);

    public abstract boolean setRegisterKeyStartPairingV2(byte[] bArr);

    public abstract int setRestartTbox(int i);

    public abstract int setScreenState(int i);

    public abstract boolean setSmartPower(int i);

    public abstract int setTcpDumpState(boolean z);

    public abstract void setUpdateFlag(int i, IAbsMessageCallback iAbsMessageCallback);

    public abstract void setUsbUpdateState(int i);

    public abstract void startDownloadBackup(String str);

    public abstract void startRegisterNFCKey();

    public abstract void startUpdate(int i, IUpdateListener iUpdateListener);

    public abstract void startUpdate(String str);

    public abstract void startUpdateTbox(String str);

    public abstract void stopCloudServiceListener(IAbsMessageCallback iAbsMessageCallback);

    public abstract void stopUpdate();

    public abstract boolean switchCloudServiceEnv(int i);

    public abstract int syncApnParam(String str, String str2, String str3, String str4, String str5, String str6, String str7);

    public abstract int syncMcuState();

    public abstract void unregisterCallback(IUpdateListener iUpdateListener);

    public abstract void unregisterListener(IAbsBYDAutoOtaListener iAbsBYDAutoOtaListener);

    public abstract void unregisterTboxListener(IAbsBYDTBoxListener iAbsBYDTBoxListener);

    public abstract void unregisterTboxListener(ITboxCallBack iTboxCallBack);

    public abstract void unregisterTestListener(ITestTboxCallBack iTestTboxCallBack);

    public abstract int updateHsm(String str);

    public abstract int uploadCdr(int i, String str);

    public abstract void uploadNfcOpenInfo();

    public static synchronized OtaSdkManager getInstance(Context context) {
        Log.e(TAG, "OtaSdkManager.getInstance context = " + context);
        if (mImpl == null) {
            mImpl = new OtaSdkManagerImpl(context);
        }
        return mImpl;
    }
}
