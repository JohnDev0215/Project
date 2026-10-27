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

    BYDAutoDigitalKeyDevice(Context context) {
        super(context.getApplicationContext());
        this.mDigitalKeyManagerImpl = DigitalKeyManagerImpl.getsInstance(context.getApplicationContext());
    }

    public static synchronized BYDAutoDigitalKeyDevice getInstance(Context context) {
        if (sDevice == null) {
            sDevice = new BYDAutoDigitalKeyDevice(context);
        }
        return sDevice;
    }

    @Deprecated
    public void deleteNfcKey(int i) {
        this.mDigitalKeyManagerImpl.deleteNfcKey(i);
    }

    public void deleteNfcKeyByKeyNo(int i, String str) {
        this.mDigitalKeyManagerImpl.deleteNfcKey(i, str);
    }

    public void deleteNfcKeyByType(int i) {
        this.mDigitalKeyManagerImpl.deleteNfcKey(i);
    }

    public void deleteOwnIphoneKey(int i, String str) {
        this.mDigitalKeyManagerImpl.deleteOwnIphoneKey(i, str);
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

    public int getKeyFindingResults(int i) {
        return this.mDigitalKeyManagerImpl.getKeyFindingResults(i);
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

    public int getOperationCommand19C33() {
        return this.mDigitalKeyManagerImpl.getOperationCommand19C33();
    }

    public int getOperationResult19C33() {
        return this.mDigitalKeyManagerImpl.getOperationResult19C33();
    }

    public int getOperationType19C33() {
        return this.mDigitalKeyManagerImpl.getOperationType19C33();
    }

    public int getOwnerKeyIdentifierResult(int i) {
        return this.mDigitalKeyManagerImpl.getOwnerKeyIdentifierResult(i);
    }

    public int getPairingProcess() {
        return this.mDigitalKeyManagerImpl.getPairingProcess();
    }

    public int getResponseFeedbackInform() {
        return this.mDigitalKeyManagerImpl.getResponseFeedbackInform();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return null;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getType() {
        return 0;
    }

    @Deprecated
    public void queryKeyListReq(int i, int i2, int i3, String str, String str2) {
        this.mDigitalKeyManagerImpl.queryKeyListReq(i, i2, i3, str, str2);
    }

    public void registerListener(IDigitalKeyLisenter iDigitalKeyLisenter) {
        this.mDigitalKeyManagerImpl.registerListener(iDigitalKeyLisenter);
    }

    public void registerTboxListener(IDigitalKeyTboxListener iDigitalKeyTboxListener) {
        this.mDigitalKeyManagerImpl.registerTboxListener(iDigitalKeyTboxListener);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int set(int[] iArr, BYDAutoEventValue bYDAutoEventValue) {
        return this.mDigitalKeyManagerImpl.set(iArr, bYDAutoEventValue);
    }

    public void setAllowStartTimeAfterUnlock(int i, int i2) {
        if (i == 0) {
            this.mDigitalKeyManagerImpl.setAllowStartTimeAfterUnlockOut(i2);
        } else if (i == 1) {
            this.mDigitalKeyManagerImpl.setAllowStartTimeAfterUnlockInOut(i2);
        }
    }

    public void setQueryOwnersKey(int i, int i2) {
        if (i == 0) {
            this.mDigitalKeyManagerImpl.setQueryOwnersKeyOut(i2);
        } else if (i == 1) {
            this.mDigitalKeyManagerImpl.setQueryOwnersKeyInOut(i2);
        }
    }

    public void setRegisterKeyStartPairing(int i, int i2) {
        if (i == 0) {
            this.mDigitalKeyManagerImpl.setRegisterKeyStartPairingOut(i2);
        } else if (i == 1) {
            this.mDigitalKeyManagerImpl.setRegisterKeyStartPairingInOut(i2);
        }
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
    public void deleteNfcKey(int i, String str) {
        this.mDigitalKeyManagerImpl.deleteNfcKey(i, str);
    }

    public void queryKeyListReq(int i, int i2, int i3, String str, byte[] bArr) {
        this.mDigitalKeyManagerImpl.queryKeyListReq(i, i2, i3, str, bArr);
    }
}
