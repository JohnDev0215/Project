package android.hardware.bydauto.pki.tsimpl;

import android.content.Context;
import android.hardware.bydauto.pki.PkiManager;
import android.util.Log;
import com.ts.lib.pki.PkiSdkManager;
import com.ts.lib.pki.bean.PkiFileData;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PkiManagerImpl {
    private static final int S_FOR_TEST_VALUE = 0;
    private static final String S_KEY_FOR_TEST = "key_for_test_PkiManagerImpl";
    protected static final String TAG = "PkiManagerImpl";
    private static PkiManagerImpl sPkiManagerImpl = null;
    private Context mContext;
    private int mForTest = 1;
    private boolean mIgnoreBydPermission = false;
    private PkiSdkManager mSdkManager;

    public PkiManagerImpl(Context con) {
        this.mContext = null;
        this.mContext = con;
        this.mSdkManager = PkiSdkManager.getInstance(con);
        enforcePermission(PkiManager.PERMISSION_BYDAUTO_PKI_COMMON);
    }

    public static synchronized PkiManagerImpl getInstance(Context con) {
        if (sPkiManagerImpl == null) {
            sPkiManagerImpl = new PkiManagerImpl(con);
        }
        return sPkiManagerImpl;
    }

    private void enforcePermission(String permissionName) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(permissionName) != 0) {
            throw new SecurityException("requires permission " + permissionName);
        }
    }

    public String decodeEccHSM(String eDataStr) {
        if (this.mForTest == 0) {
            Log.e(TAG, " decodeEccHSM mForTestValue is 0 execute return");
            return "ForTest";
        }
        enforcePermission(PkiManager.PERMISSION_BYDAUTO_PKI_GET);
        return this.mSdkManager.decodeEccHsm(eDataStr);
    }

    public String decodeRsaHSM(String dataStr) {
        if (this.mForTest == 0) {
            Log.e(TAG, " decodeRsaHSM mForTestValue is 0 execute return");
            return "ForTest";
        }
        enforcePermission(PkiManager.PERMISSION_BYDAUTO_PKI_GET);
        return this.mSdkManager.decodeRsaHsm(dataStr);
    }

    public boolean verifyCertHSM(String cert) {
        if (this.mForTest == 0) {
            Log.e(TAG, " verifyCertHSM mForTestValue is 0 execute return");
            return false;
        }
        enforcePermission(PkiManager.PERMISSION_BYDAUTO_PKI_GET);
        return this.mSdkManager.verifyCertHsm(cert);
    }

    public String encryptStrHSM(String data) {
        if (this.mForTest == 0) {
            Log.e(TAG, " encryptStrHSM mForTestValue is 0 execute return");
            return "ForTest";
        }
        enforcePermission(PkiManager.PERMISSION_BYDAUTO_PKI_GET);
        return this.mSdkManager.encryptStrHsm(data);
    }

    public byte[] getRsa2048PrivateKeyP12() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getRsa2048PrivateKeyP12 mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(PkiManager.PERMISSION_BYDAUTO_PKI_GET);
        PkiFileData pkiFileData = this.mSdkManager.getRsa2048PrivateKeyP12();
        return getByteData(pkiFileData);
    }

    public byte[] getRootRsa2048CertP12() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getRootRsa2048CertP12 mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(PkiManager.PERMISSION_BYDAUTO_PKI_GET);
        PkiFileData pkiFileData = this.mSdkManager.getRootRsa2048CertP12();
        return getByteData(pkiFileData);
    }

    public byte[] getIdentityRsa2048CertP12() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getIdentityRsa2048CertP12 mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(PkiManager.PERMISSION_BYDAUTO_PKI_GET);
        PkiFileData pkiFileData = this.mSdkManager.getIdentityRsa2048CertP12();
        return getByteData(pkiFileData);
    }

    public byte[] getDeviceRsa2048CertP12() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getDeviceRsa2048CertP12 mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(PkiManager.PERMISSION_BYDAUTO_PKI_GET);
        PkiFileData pkiFileData = this.mSdkManager.getDeviceRsa2048CertP12();
        return getByteData(pkiFileData);
    }

    public byte[] getTEEECC256PrivateKeyP12() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getTEEECC256PrivateKeyP12 mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(PkiManager.PERMISSION_BYDAUTO_PKI_GET);
        PkiFileData pkiFileData = this.mSdkManager.getTeeEcc256PrivateKeyP12();
        return getByteData(pkiFileData);
    }

    public String getAESKey(String type) {
        if (this.mForTest == 0) {
            Log.e(TAG, " getAESKey mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(PkiManager.PERMISSION_BYDAUTO_PKI_GET);
        return this.mSdkManager.getAesKey(type);
    }

    public byte[] getDeviceRsaCertP12() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getDeviceRsaCertP12 mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(PkiManager.PERMISSION_BYDAUTO_PKI_GET);
        PkiFileData pkiFileData = this.mSdkManager.getDeviceRsaCertP12();
        return getByteData(pkiFileData);
    }

    public byte[] getDeviceRsaKeyP12() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getDeviceRsaKeyP12 mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(PkiManager.PERMISSION_BYDAUTO_PKI_GET);
        PkiFileData pkiFileData = this.mSdkManager.getDeviceRsaKeyP12();
        return getByteData(pkiFileData);
    }

    public byte[] getSecretForP12(String type) {
        if (this.mForTest == 0) {
            Log.e(TAG, " getSecretForP12 mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(PkiManager.PERMISSION_BYDAUTO_PKI_GET);
        PkiFileData pkiFileData = this.mSdkManager.getSecretForP12(type);
        return getByteData(pkiFileData);
    }

    public boolean updateAESKeyHSM(String src) {
        if (this.mForTest == 0) {
            Log.e(TAG, " upateAESKeyHSM mForTestValue is 0 execute return");
            return false;
        }
        enforcePermission(PkiManager.PERMISSION_BYDAUTO_PKI_SET);
        return this.mSdkManager.updateAesKeyHsm(src);
    }

    public boolean updateAESKeyTEE(String srcPath) {
        if (this.mForTest == 0) {
            Log.e(TAG, " upateAESKeyTEE mForTestValue is 0 execute return");
            return false;
        }
        enforcePermission(PkiManager.PERMISSION_BYDAUTO_PKI_SET);
        return this.mSdkManager.updateAesKeyTee(srcPath);
    }

    public boolean isDeviceHasHSM() {
        if (this.mForTest == 0) {
            Log.e(TAG, " isDeviceHasHSM mForTestValue is 0 execute return");
            return false;
        }
        enforcePermission(PkiManager.PERMISSION_BYDAUTO_PKI_GET);
        return this.mSdkManager.isDeviceHasHsm();
    }

    public byte[] getByteData(PkiFileData pkiFileData) {
        if (pkiFileData == null) {
            Log.e(TAG, "getByteData pkiFileData is null");
            return null;
        }
        List<Byte> data = pkiFileData.getData();
        if (data == null) {
            Log.e(TAG, "getByteData data is null");
            return null;
        }
        byte[] byteData = new byte[data.size()];
        for (int i = 0; i < data.size(); i++) {
            byteData[i] = data.get(i).byteValue();
        }
        return byteData;
    }

    public byte[] getDeviceRsaCertHSM() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getDeviceRsaCertHSM mForTestValue is " + this.mForTest + " execute return");
            return null;
        }
        enforcePermission(PkiManager.PERMISSION_BYDAUTO_PKI_GET);
        PkiFileData pkiFileData = this.mSdkManager.getDeviceRsaCertHsm();
        return getByteData(pkiFileData);
    }

    public byte[] getDevicePublicKey() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getDeviceRsaCertHSM mForTestValue is " + this.mForTest + " execute return");
            return null;
        }
        enforcePermission(PkiManager.PERMISSION_BYDAUTO_PKI_GET);
        PkiFileData pkiFileData = this.mSdkManager.getEngineerPublicKey();
        return getByteData(pkiFileData);
    }

    public String signEccHSM(String str) {
        if (this.mForTest == 0) {
            Log.e(TAG, " signEccHSM mForTestValue is " + this.mForTest + " execute return");
            return null;
        }
        enforcePermission(PkiManager.PERMISSION_BYDAUTO_PKI_GET);
        return this.mSdkManager.signEccHsm(str);
    }

    public byte[] getHttpsSSLFactory() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getHttpsSSLFactory mForTestValue is " + this.mForTest + " execute return");
            return null;
        }
        enforcePermission(PkiManager.PERMISSION_BYDAUTO_PKI_GET);
        PkiFileData pkiFileData = this.mSdkManager.getHalRsa2048IdentityCertData();
        return getByteData(pkiFileData);
    }
}
