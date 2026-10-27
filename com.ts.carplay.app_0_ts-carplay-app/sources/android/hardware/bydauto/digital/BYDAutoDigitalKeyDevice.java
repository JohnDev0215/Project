package android.hardware.bydauto.digital;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.digital.tsimpl.DigitalKeyManagerImpl;
import android.hardware.bydauto.digital.tsimpl.IDigitalKeyTboxListener;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoDigitalKeyDevice extends AbsBYDAutoDevice {
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int ZERO = 0;
    private static BYDAutoDigitalKeyDevice sDevice;
    private final DigitalKeyManagerImpl mDigitalKeyManagerImpl;

    BYDAutoDigitalKeyDevice(Context con) {
        super(con.getApplicationContext());
        this.mDigitalKeyManagerImpl = DigitalKeyManagerImpl.getsInstance(con.getApplicationContext());
    }

    public static synchronized BYDAutoDigitalKeyDevice getInstance(Context con) {
        if (sDevice == null) {
            sDevice = new BYDAutoDigitalKeyDevice(con);
        }
        return sDevice;
    }

    public int getNFCConfigurationInformation() {
        return this.mDigitalKeyManagerImpl.getNFCConfigurationInformation();
    }

    @Deprecated
    public boolean setQueryOwnersKey(int config, int op) {
        return this.mDigitalKeyManagerImpl.setQueryOwnersKey(config, op);
    }

    public boolean setQueryOwnersKeyV2(byte[] data) {
        return this.mDigitalKeyManagerImpl.setQueryOwnersKeyV2(data);
    }

    @Deprecated
    public boolean setRegisterKeyStartPairing(int config, int op) {
        return this.mDigitalKeyManagerImpl.setRegisterKeyStartPairing(config, op);
    }

    public boolean setRegisterKeyStartPairingV2(byte[] data) {
        return this.mDigitalKeyManagerImpl.setRegisterKeyStartPairingV2(data);
    }

    public int getIkConfigKeylessTime() {
        return this.mDigitalKeyManagerImpl.getIkConfigKeylessTime();
    }

    @Deprecated
    public boolean setAllowStartTimeAfterUnlock(int config, int value) {
        return this.mDigitalKeyManagerImpl.setAllowStartTimeAfterUnlock(config, value);
    }

    public boolean setAllowStartTimeAfterUnlockV2(byte[] data) {
        return this.mDigitalKeyManagerImpl.setAllowStartTimeAfterUnlockV2(data);
    }

    public boolean deleteNfcFindKeyV2(byte[] data) {
        return this.mDigitalKeyManagerImpl.deleteNfcFindKeyV2(data);
    }

    public boolean offOnlineRemoveKey97V2(byte[] data) {
        return this.mDigitalKeyManagerImpl.offOnlineRemoveKey97V2(data);
    }

    public boolean offOnlineRemoveKey98V2(byte[] data) {
        return this.mDigitalKeyManagerImpl.offOnlineRemoveKey98V2(data);
    }

    @Deprecated
    public void queryKeyListReq(int carAccount, int entityKeyTag, int keyType, String userId, String keyId) {
        this.mDigitalKeyManagerImpl.queryKeyListReq(carAccount, entityKeyTag, keyType, userId, keyId);
    }

    public void queryKeyListReq(int carAccount, int entityKeyTag, int keyType, String userId, byte[] keyId) {
        this.mDigitalKeyManagerImpl.queryKeyListReq(carAccount, entityKeyTag, keyType, userId, keyId);
    }

    public void uploadNfcOpenInfo() {
        this.mDigitalKeyManagerImpl.uploadNfcOpenInfo();
    }

    @Deprecated
    public void deleteNfcKey(int op) {
        this.mDigitalKeyManagerImpl.deleteNfcKey(op);
    }

    @Deprecated
    public void deleteNfcKey(int op, String no) {
        this.mDigitalKeyManagerImpl.deleteNfcKey(op, no);
    }

    public void deleteNfcKeyByType(int type) {
        this.mDigitalKeyManagerImpl.deleteNfcKey(type);
    }

    public void deleteNfcKeyByKeyNo(int type, String keyNo) {
        this.mDigitalKeyManagerImpl.deleteNfcKey(type, keyNo);
    }

    public void deleteOwnIphoneKey(int type, String keyNo) {
        this.mDigitalKeyManagerImpl.deleteOwnIphoneKey(type, keyNo);
    }

    public void startRegisterNFCKey() {
        this.mDigitalKeyManagerImpl.startRegisterNFCKey();
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return 0;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return null;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return null;
    }

    public void registerListener(IDigitalKeyLisenter listener) {
        this.mDigitalKeyManagerImpl.registerListener(listener);
    }

    public void registerTboxListener(IDigitalKeyTboxListener listener) {
        this.mDigitalKeyManagerImpl.registerTboxListener(listener);
    }

    public void unregisterListener(IDigitalKeyLisenter listener) {
        this.mDigitalKeyManagerImpl.unregisterListener(listener);
    }

    public void unregisterTboxListener(IDigitalKeyTboxListener listener) {
        this.mDigitalKeyManagerImpl.unregisterTboxListener(listener);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] eventTypes, Class<?> type) {
        return this.mDigitalKeyManagerImpl.get(eventTypes, type);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int set(int[] eventTypes, BYDAutoEventValue eventValue) {
        return this.mDigitalKeyManagerImpl.set(eventTypes, eventValue);
    }

    public int getKeyFindingResults(int config) {
        return this.mDigitalKeyManagerImpl.getKeyFindingResults(config);
    }

    public int getOwnerKeyIdentifierResult(int config) {
        return this.mDigitalKeyManagerImpl.getOwnerKeyIdentifierResult(config);
    }

    public int getPairingProcess() {
        return this.mDigitalKeyManagerImpl.getPairingProcess();
    }

    public int getOperateResult() {
        return this.mDigitalKeyManagerImpl.getOperateResult();
    }

    public int getOperateCmd() {
        return this.mDigitalKeyManagerImpl.getOperateCmd();
    }

    public int getOperationType19C33() {
        return this.mDigitalKeyManagerImpl.getOperationType19C33();
    }

    public int getOperationCommand19C33() {
        return this.mDigitalKeyManagerImpl.getOperationCommand19C33();
    }

    public int getOperationResult19C33() {
        return this.mDigitalKeyManagerImpl.getOperationResult19C33();
    }

    public int getResponseFeedbackInform() {
        return this.mDigitalKeyManagerImpl.getResponseFeedbackInform();
    }

    public int setOperationCmdAndKeyType(int cmd, int type) {
        return this.mDigitalKeyManagerImpl.setOperationCmdAndKeyType(cmd, type);
    }

    public int setKeyId_1(byte[] arr) {
        return this.mDigitalKeyManagerImpl.setKeyIdOne(arr);
    }

    public int setKeyId_2(byte[] arr) {
        return this.mDigitalKeyManagerImpl.setKeyIdTwo(arr);
    }

    public int getPadOfflineOperationResp() {
        return this.mDigitalKeyManagerImpl.getPadOfflineOperationResp();
    }

    public int getOperationType() {
        return this.mDigitalKeyManagerImpl.getOperationType();
    }

    public int getOperationCommand() {
        return this.mDigitalKeyManagerImpl.getOperationCommand();
    }

    public int getResultsOfEnforcement() {
        return this.mDigitalKeyManagerImpl.getResultsOfEnforcement();
    }

    public int getStatusOfUnlockSetting() {
        return this.mDigitalKeyManagerImpl.getStatusOfUnlockSetting();
    }

    public int getStatusOfLockSetting() {
        return this.mDigitalKeyManagerImpl.getStatusOfLockSetting();
    }

    public boolean relayDataToTbox40a(byte[] data) {
        return this.mDigitalKeyManagerImpl.relayDataToTbox40A(data);
    }

    public boolean sendRemoteLockOrUnlockStatus(int type, int status) {
        return this.mDigitalKeyManagerImpl.sendRemoteLockOrUnlockStatus(type, status);
    }

    public void sendDrivingPasswordSetting(int type) {
        this.mDigitalKeyManagerImpl.sendDrivingPasswordSetting(type);
    }

    public int supportOpcodeStartup() {
        return this.mDigitalKeyManagerImpl.supportOpcodeStartup();
    }

    public int getNfcKeyStatus() {
        return this.mDigitalKeyManagerImpl.getNfcKeyStatus();
    }

    public int getNfcKeyUnlockType() {
        return this.mDigitalKeyManagerImpl.getNfcKeyUnlockType();
    }

    public int getNfcKeyUnlockResult() {
        return this.mDigitalKeyManagerImpl.getNfcKeyUnlockResult();
    }

    public void sendOpcodeSwitchState(int stats) {
        this.mDigitalKeyManagerImpl.sendOpcodeSwitchState(stats);
    }

    public void sendCurAccountSwitchFlag(int state) {
        this.mDigitalKeyManagerImpl.sendCurAccountSwitchFlag(state);
    }

    public void sendOpcodeSwitchStateSetting(int stats) {
        this.mDigitalKeyManagerImpl.sendOpcodeSwitchStateSetting(stats);
    }

    public int getOpcodeStartFunction() {
        return this.mDigitalKeyManagerImpl.getOpcodeStartFunction();
    }

    public int getPadExitPinCodeProcess() {
        return this.mDigitalKeyManagerImpl.getPadExitPinCodeProcess();
    }

    public void sendOpcodeFunctionalFailure(int state) {
        this.mDigitalKeyManagerImpl.sendOpcodeFunctionalFailure(state);
    }

    public int getOpcodeDataLength() {
        return this.mDigitalKeyManagerImpl.getOpcodeDataLength();
    }

    public int getOpcodeChecksumValue() {
        return this.mDigitalKeyManagerImpl.getOpcodeChecksumValue();
    }

    public int getOpcodeCommand() {
        return this.mDigitalKeyManagerImpl.getOpcodeCommand();
    }

    public int getResponseResultOfOpcode() {
        return this.mDigitalKeyManagerImpl.getResponseResultOfOpcode();
    }

    public byte[] getOpcodeDataValue() {
        return this.mDigitalKeyManagerImpl.getOpcodeDataValue();
    }

    public int getDrivingPasswordSettingConfig() {
        return this.mDigitalKeyManagerImpl.getDrivingPasswordSettingConfig();
    }

    public int getPadOpcodeConfig() {
        return this.mDigitalKeyManagerImpl.getPadOpcodeConfig();
    }

    public int getBluetoothUnlockType() {
        return this.mDigitalKeyManagerImpl.getBluetoothUnlockType();
    }

    public void setMultiplexor(int data) {
        this.mDigitalKeyManagerImpl.setMultiplexor(data);
    }

    public void sendOpcodeDataToSEC3(long userid, int random, int nfc) {
        this.mDigitalKeyManagerImpl.sendOpcodeDataToSEC3(userid, random, nfc);
    }

    public void sendOpcodeDataToSEC4(long userid, long opcode, int random, int nfc) {
        this.mDigitalKeyManagerImpl.sendOpcodeDataToSEC4(userid, opcode, random, nfc);
    }
}
