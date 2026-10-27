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

    PkiManager(Context con) {
        super(con);
        this.mManagerImpl = PkiManagerImpl.getInstance(con);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return PERMISSION_BYDAUTO_PKI_SET;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return PERMISSION_BYDAUTO_PKI_GET;
    }

    public static synchronized PkiManager getInstance(Context con) {
        if (sInstance == null) {
            sInstance = new PkiManager(con);
        }
        return sInstance;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return 0;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    public String decodeEccHSM(String eDataStr) {
        return this.mManagerImpl.decodeEccHSM(eDataStr);
    }

    public String decodeRsaHSM(String dataStr) {
        return this.mManagerImpl.decodeRsaHSM(dataStr);
    }

    public boolean verifyCertHSM(String cert) {
        return this.mManagerImpl.verifyCertHSM(cert);
    }

    public String encryptStrHSM(String data) {
        return this.mManagerImpl.encryptStrHSM(data);
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

    public String getAESKey(String type) {
        return this.mManagerImpl.getAESKey(type);
    }

    public byte[] getDeviceRsaCertP12() {
        return this.mManagerImpl.getDeviceRsaCertP12();
    }

    public byte[] getDeviceRsaKeyP12() {
        return this.mManagerImpl.getDeviceRsaKeyP12();
    }

    public byte[] getSecretForP12(String type) {
        return this.mManagerImpl.getSecretForP12(type);
    }

    public boolean updateAESKeyHSM(String src) {
        return this.mManagerImpl.updateAESKeyHSM(src);
    }

    public boolean updateAESKeyTEE(String srcPath) {
        return this.mManagerImpl.updateAESKeyTEE(srcPath);
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
