package android.hardware.bydauto.qcfs;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoQcfsDevice extends AbsBYDAutoDevice {
    public static final int AFTER_DOWNLOAD_UPGRADE_NO = 12;
    public static final int AFTER_DOWNLOAD_UPGRADE_NOW_YES = 11;
    public static final int AFTER_DOWNLOAD_UPGRADE_RESERVE = 13;
    public static final int BEFORE_DOWNLOAD_UPGRADE_NO = 2;
    public static final int BEFORE_DOWNLOAD_UPGRADE_YES = 1;
    public static final int FS_FEEDBACK_FAKEOK_ENTERING = 1;
    public static final int FS_FEEDBACK_TRUE = 0;
    public static final int QCFS_COMMAND_BUSY = -2147482647;
    public static final int QCFS_COMMAND_FAILED = -2147482648;
    public static final int QCFS_COMMAND_INVALID_VALUE = -2147482645;
    public static final int QCFS_COMMAND_SUCCESS = 0;
    public static final int QCFS_COMMAND_TIMEOUT = -2147482646;
    public static final int QCFS_WAIT_UPGRADE_NO = 0;
    public static final int QCFS_WAIT_UPGRADE_YES = 1;
    public static final int QC_REBOOT_CONFIRM_UPGRADE_END = 4;
    public static final int QC_UPGRADE_BEGIN = 2;
    public static final int QC_UPGRADE_END = 3;
    public static final int QC_WILL_GOTO_RECOVERY = 1;
    public static final int SECURE_IC_UPGRADE_BEGIN = 1;
    public static final int SECURE_IC_UPGRADE_END = 2;
    public static final int SECURE_IC_UPGRADE_ERROR = 3;
    public static final int SEND_KEY = 3;
    public static final int SEND_KEY_BEGIN = 1;
    public static final int SEND_KEY_END = 2;
    protected static final String TAG = "BYDAutoQcfsDevice";

    public BYDAutoQcfsDevice(Context context) {
        super(null);
        throw new RuntimeException("Stub!");
    }

    public static synchronized BYDAutoQcfsDevice getInstance(Context context) {
        throw new RuntimeException("Stub!");
    }

    public void getAllStatus() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int[] getFeatureList() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getType() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public boolean postEvent(int i7, int i10, byte[] bArr, Object obj) {
        throw new RuntimeException("Stub!");
    }

    public int qurCondNewVer(byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    public int qurCondRcvKey() {
        throw new RuntimeException("Stub!");
    }

    public int qurCondUpgrade() {
        throw new RuntimeException("Stub!");
    }

    public int qurEcuVersion() {
        throw new RuntimeException("Stub!");
    }

    public int qurFsCondFakeok() {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoQcfsListener absBYDAutoQcfsListener) {
        throw new RuntimeException("Stub!");
    }

    public int reqSpecifiedEcuVersion(byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    public int sendKeyToFs(byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    public void setAllStatus() {
        throw new RuntimeException("Stub!");
    }

    public int setAllUpgradeStatus(byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    public int setQcomUpgradeStatus(int i7) {
        throw new RuntimeException("Stub!");
    }

    public int setSecureIcUpgradeStatus(int i7) {
        throw new RuntimeException("Stub!");
    }

    public int setUserConfirm(int i7) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(AbsBYDAutoQcfsListener absBYDAutoQcfsListener) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public boolean postEvent(int i7, int i10, int i11, Object obj) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoQcfsListener absBYDAutoQcfsListener, int[] iArr) {
        throw new RuntimeException("Stub!");
    }
}
