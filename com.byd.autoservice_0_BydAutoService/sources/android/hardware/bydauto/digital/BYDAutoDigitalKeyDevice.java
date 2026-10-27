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

    public BYDAutoDigitalKeyDevice(Context context) {
        super(context.getApplicationContext());
        this.mDigitalKeyManagerImpl = DigitalKeyManagerImpl.getsInstance(context.getApplicationContext());
    }

    public static synchronized BYDAutoDigitalKeyDevice getInstance(Context context) {
        if (sDevice == null) {
            sDevice = new BYDAutoDigitalKeyDevice(context);
        }
        return sDevice;
    }

    public boolean deleteNfcFindKeyV2(byte[] bArr) {
        return this.mDigitalKeyManagerImpl.deleteNfcFindKeyV2(bArr);
    }

    @Deprecated
    public void deleteNfcKey(int i3) {
        this.mDigitalKeyManagerImpl.deleteNfcKey(i3);
    }

    public void deleteNfcKeyByKeyNo(int i3, String str) {
        this.mDigitalKeyManagerImpl.deleteNfcKey(i3, str);
    }

    public void deleteNfcKeyByType(int i3) {
        this.mDigitalKeyManagerImpl.deleteNfcKey(i3);
    }

    public void deleteOwnIphoneKey(int i3, String str) {
        this.mDigitalKeyManagerImpl.deleteOwnIphoneKey(i3, str);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        return this.mDigitalKeyManagerImpl.get(iArr, cls);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return null;
    }

    public int getIkConfigKeylessTime() {
        return this.mDigitalKeyManagerImpl.getIkConfigKeylessTime();
    }

    public int getKeyFindingResults(int i3) {
        return this.mDigitalKeyManagerImpl.getKeyFindingResults(i3);
    }

    public int getNFCConfigurationInformation() {
        return this.mDigitalKeyManagerImpl.getNFCConfigurationInformation();
    }

    public int getOperateCmd() {
        return this.mDigitalKeyManagerImpl.getOperateCmd();
    }

    public int getOperateResult() {
        return this.mDigitalKeyManagerImpl.getOperateResult();
    }

    public int getOperationCommand() {
        return this.mDigitalKeyManagerImpl.getOperationCommand();
    }

    public int getOperationCommand19C33() {
        return this.mDigitalKeyManagerImpl.getOperationCommand19C33();
    }

    public int getOperationResult19C33() {
        return this.mDigitalKeyManagerImpl.getOperationResult19C33();
    }

    public int getOperationType() {
        return this.mDigitalKeyManagerImpl.getOperationType();
    }

    public int getOperationType19C33() {
        return this.mDigitalKeyManagerImpl.getOperationType19C33();
    }

    public int getOwnerKeyIdentifierResult(int i3) {
        return this.mDigitalKeyManagerImpl.getOwnerKeyIdentifierResult(i3);
    }

    public int getPadOfflineOperationResp() {
        return this.mDigitalKeyManagerImpl.getPadOfflineOperationResp();
    }

    public int getPairingProcess() {
        return this.mDigitalKeyManagerImpl.getPairingProcess();
    }

    public int getResponseFeedbackInform() {
        return this.mDigitalKeyManagerImpl.getResponseFeedbackInform();
    }

    public int getResultsOfEnforcement() {
        return this.mDigitalKeyManagerImpl.getResultsOfEnforcement();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return null;
    }

    public int getStatusOfLockSetting() {
        return this.mDigitalKeyManagerImpl.getStatusOfLockSetting();
    }

    public int getStatusOfUnlockSetting() {
        return this.mDigitalKeyManagerImpl.getStatusOfUnlockSetting();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getType() {
        return 0;
    }

    public boolean offOnlineRemoveKey97V2(byte[] bArr) {
        return this.mDigitalKeyManagerImpl.offOnlineRemoveKey97V2(bArr);
    }

    public boolean offOnlineRemoveKey98V2(byte[] bArr) {
        return this.mDigitalKeyManagerImpl.offOnlineRemoveKey98V2(bArr);
    }

    @Deprecated
    public void queryKeyListReq(int i3, int i4, int i5, String str, String str2) {
        this.mDigitalKeyManagerImpl.queryKeyListReq(i3, i4, i5, str, str2);
    }

    public void registerListener(IDigitalKeyLisenter iDigitalKeyLisenter) {
        this.mDigitalKeyManagerImpl.registerListener(iDigitalKeyLisenter);
    }

    public void registerTboxListener(IDigitalKeyTboxListener iDigitalKeyTboxListener) {
        this.mDigitalKeyManagerImpl.registerTboxListener(iDigitalKeyTboxListener);
    }

    public boolean relayDataToTbox40a(byte[] bArr) {
        return this.mDigitalKeyManagerImpl.relayDataToTbox40A(bArr);
    }

    public boolean sendRemoteLockOrUnlockStatus(int i3, int i4) {
        return this.mDigitalKeyManagerImpl.sendRemoteLockOrUnlockStatus(i3, i4);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int set(int[] iArr, BYDAutoEventValue bYDAutoEventValue) {
        return this.mDigitalKeyManagerImpl.set(iArr, bYDAutoEventValue);
    }

    @Deprecated
    public boolean setAllowStartTimeAfterUnlock(int i3, int i4) {
        return this.mDigitalKeyManagerImpl.setAllowStartTimeAfterUnlock(i3, i4);
    }

    public boolean setAllowStartTimeAfterUnlockV2(byte[] bArr) {
        return this.mDigitalKeyManagerImpl.setAllowStartTimeAfterUnlockV2(bArr);
    }

    public int setKeyId_1(byte[] bArr) {
        return this.mDigitalKeyManagerImpl.setKeyIdOne(bArr);
    }

    public int setKeyId_2(byte[] bArr) {
        return this.mDigitalKeyManagerImpl.setKeyIdTwo(bArr);
    }

    public int setOperationCmdAndKeyType(int i3, int i4) {
        return this.mDigitalKeyManagerImpl.setOperationCmdAndKeyType(i3, i4);
    }

    @Deprecated
    public boolean setQueryOwnersKey(int i3, int i4) {
        return this.mDigitalKeyManagerImpl.setQueryOwnersKey(i3, i4);
    }

    public boolean setQueryOwnersKeyV2(byte[] bArr) {
        return this.mDigitalKeyManagerImpl.setQueryOwnersKeyV2(bArr);
    }

    @Deprecated
    public boolean setRegisterKeyStartPairing(int i3, int i4) {
        return this.mDigitalKeyManagerImpl.setRegisterKeyStartPairing(i3, i4);
    }

    public boolean setRegisterKeyStartPairingV2(byte[] bArr) {
        return this.mDigitalKeyManagerImpl.setRegisterKeyStartPairingV2(bArr);
    }

    public void startRegisterNFCKey() {
        this.mDigitalKeyManagerImpl.startRegisterNFCKey();
    }

    public void unregisterListener(IDigitalKeyLisenter iDigitalKeyLisenter) {
        this.mDigitalKeyManagerImpl.unregisterListener(iDigitalKeyLisenter);
    }

    public void unregisterTboxListener(IDigitalKeyTboxListener iDigitalKeyTboxListener) {
        this.mDigitalKeyManagerImpl.unregisterTboxListener(iDigitalKeyTboxListener);
    }

    public void uploadNfcOpenInfo() {
        this.mDigitalKeyManagerImpl.uploadNfcOpenInfo();
    }

    @Deprecated
    public void deleteNfcKey(int i3, String str) {
        this.mDigitalKeyManagerImpl.deleteNfcKey(i3, str);
    }

    public void queryKeyListReq(int i3, int i4, int i5, String str, byte[] bArr) {
        this.mDigitalKeyManagerImpl.queryKeyListReq(i3, i4, i5, str, bArr);
    }
}
