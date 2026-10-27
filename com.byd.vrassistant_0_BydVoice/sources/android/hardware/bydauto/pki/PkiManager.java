package android.hardware.bydauto.pki;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.pki.tsimpl.PkiManagerImpl;

/* JADX INFO: loaded from: classes.dex */
public class PkiManager extends AbsBYDAutoDevice {
    public static final String PERMISSION_BYDAUTO_PKI_COMMON = "android.permission.BYDAUTO_PKI_COMMON";
    public static final String PERMISSION_BYDAUTO_PKI_GET = "android.permission.BYDAUTO_PKI_GET";
    public static final String PERMISSION_BYDAUTO_PKI_SET = "android.permission.BYDAUTO_PKI_SET";
    private static PkiManager sInstance;
    private final PkiManagerImpl mManagerImpl;

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return PERMISSION_BYDAUTO_PKI_GET;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return PERMISSION_BYDAUTO_PKI_SET;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return 0;
    }

    PkiManager(Context context) {
        super(context);
        this.mManagerImpl = PkiManagerImpl.getInstance(context);
    }

    public static synchronized PkiManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PkiManager(context);
        }
        return sInstance;
    }

    public String decodeEccHSM(String str) {
        return this.mManagerImpl.decodeEccHSM(str);
    }

    public String decodeRsaHSM(String str) {
        return this.mManagerImpl.decodeRsaHSM(str);
    }

    public boolean verifyCertHSM(String str) {
        return this.mManagerImpl.verifyCertHSM(str);
    }

    public String encryptStrHSM(String str) {
        return this.mManagerImpl.encryptStrHSM(str);
    }

    public byte[] getRsa2048PrivateKeyP12() {
        return this.mManagerImpl.getRsa2048PrivateKeyP12();
    }

    public byte[] getRootRsa2048CertP12() {
        return this.mManagerImpl.getRootRsa2048CertP12();
    }

    public byte[] getIdentityRsa2048CertP12() {
        return this.mManagerImpl.getIdentityRsa2048CertP12();
    }

    public byte[] getDeviceRsa2048CertP12() {
        return this.mManagerImpl.getDeviceRsa2048CertP12();
    }

    public byte[] getTEEECC256PrivateKeyP12() {
        return this.mManagerImpl.getTEEECC256PrivateKeyP12();
    }

    public String getAESKey(String str) {
        return this.mManagerImpl.getAESKey(str);
    }

    public byte[] getDeviceRsaCertP12() {
        return this.mManagerImpl.getDeviceRsaCertP12();
    }

    public byte[] getDeviceRsaKeyP12() {
        return this.mManagerImpl.getDeviceRsaKeyP12();
    }

    public byte[] getSecretForP12(String str) {
        return this.mManagerImpl.getSecretForP12(str);
    }

    public boolean updateAESKeyHSM(String str) {
        return this.mManagerImpl.updateAESKeyHSM(str);
    }

    public boolean updateAESKeyTEE(String str) {
        return this.mManagerImpl.updateAESKeyTEE(str);
    }

    public boolean isDeviceHasHSM() {
        return this.mManagerImpl.isDeviceHasHSM();
    }

    public byte[] getDeviceRsaCertHSM() {
        return this.mManagerImpl.getDeviceRsaCertHSM();
    }

    public byte[] getDevicePublicKey() {
        return this.mManagerImpl.getDevicePublicKey();
    }

    public String signEccHSM(String str) {
        return this.mManagerImpl.signEccHSM(str);
    }

    public byte[] getHttpsSSLFactory() {
        return this.mManagerImpl.getHttpsSSLFactory();
    }
}
