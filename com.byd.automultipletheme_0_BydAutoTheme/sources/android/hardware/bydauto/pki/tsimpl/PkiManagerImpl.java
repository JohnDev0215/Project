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
    private static PkiManagerImpl sPkiManagerImpl;
    private Context mContext;
    private int mForTest = 1;
    private boolean mIgnoreBydPermission = false;
    private PkiSdkManager mSdkManager;

    public PkiManagerImpl(Context context) {
        this.mContext = context;
        this.mSdkManager = PkiSdkManager.getInstance(context);
        enforcePermission(PkiManager.PERMISSION_BYDAUTO_PKI_COMMON);
    }

    public static synchronized PkiManagerImpl getInstance(Context context) {
        if (sPkiManagerImpl == null) {
            sPkiManagerImpl = new PkiManagerImpl(context);
        }
        return sPkiManagerImpl;
    }

    private void enforcePermission(String str) {
        if (this.mIgnoreBydPermission || this.mContext.checkCallingOrSelfPermission(str) == 0) {
            return;
        }
        throw new SecurityException("requires permission " + str);
    }

    public String decodeEccHSM(String str) {
        if (this.mForTest == 0) {
            Log.e(TAG, " decodeEccHSM mForTestValue is 0 execute return");
            return "ForTest";
        }
        enforcePermission(PkiManager.PERMISSION_BYDAUTO_PKI_GET);
        return this.mSdkManager.decodeEccHsm(str);
    }

    public String decodeRsaHSM(String str) {
        if (this.mForTest == 0) {
            Log.e(TAG, " decodeRsaHSM mForTestValue is 0 execute return");
            return "ForTest";
        }
        enforcePermission(PkiManager.PERMISSION_BYDAUTO_PKI_GET);
        return this.mSdkManager.decodeRsaHsm(str);
    }

    public boolean verifyCertHSM(String str) {
        if (this.mForTest == 0) {
            Log.e(TAG, " verifyCertHSM mForTestValue is 0 execute return");
            return false;
        }
        enforcePermission(PkiManager.PERMISSION_BYDAUTO_PKI_GET);
        return this.mSdkManager.verifyCertHsm(str);
    }

    public String encryptStrHSM(String str) {
        if (this.mForTest == 0) {
            Log.e(TAG, " encryptStrHSM mForTestValue is 0 execute return");
            return "ForTest";
        }
        enforcePermission(PkiManager.PERMISSION_BYDAUTO_PKI_GET);
        return this.mSdkManager.encryptStrHsm(str);
    }

    public byte[] getRsa2048PrivateKeyP12() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getRsa2048PrivateKeyP12 mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(PkiManager.PERMISSION_BYDAUTO_PKI_GET);
        return getByteData(this.mSdkManager.getRsa2048PrivateKeyP12());
    }

    public byte[] getRootRsa2048CertP12() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getRootRsa2048CertP12 mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(PkiManager.PERMISSION_BYDAUTO_PKI_GET);
        return getByteData(this.mSdkManager.getRootRsa2048CertP12());
    }

    public byte[] getIdentityRsa2048CertP12() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getIdentityRsa2048CertP12 mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(PkiManager.PERMISSION_BYDAUTO_PKI_GET);
        return getByteData(this.mSdkManager.getIdentityRsa2048CertP12());
    }

    public byte[] getDeviceRsa2048CertP12() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getDeviceRsa2048CertP12 mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(PkiManager.PERMISSION_BYDAUTO_PKI_GET);
        return getByteData(this.mSdkManager.getDeviceRsa2048CertP12());
    }

    public byte[] getTEEECC256PrivateKeyP12() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getTEEECC256PrivateKeyP12 mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(PkiManager.PERMISSION_BYDAUTO_PKI_GET);
        return getByteData(this.mSdkManager.getTeeEcc256PrivateKeyP12());
    }

    public String getAESKey(String str) {
        if (this.mForTest == 0) {
            Log.e(TAG, " getAESKey mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(PkiManager.PERMISSION_BYDAUTO_PKI_GET);
        return this.mSdkManager.getAesKey(str);
    }

    public byte[] getDeviceRsaCertP12() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getDeviceRsaCertP12 mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(PkiManager.PERMISSION_BYDAUTO_PKI_GET);
        return getByteData(this.mSdkManager.getDeviceRsaCertP12());
    }

    public byte[] getDeviceRsaKeyP12() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getDeviceRsaKeyP12 mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(PkiManager.PERMISSION_BYDAUTO_PKI_GET);
        return getByteData(this.mSdkManager.getDeviceRsaKeyP12());
    }

    public byte[] getSecretForP12(String str) {
        if (this.mForTest == 0) {
            Log.e(TAG, " getSecretForP12 mForTestValue is 0 execute return");
            return null;
        }
        enforcePermission(PkiManager.PERMISSION_BYDAUTO_PKI_GET);
        return getByteData(this.mSdkManager.getSecretForP12(str));
    }

    public boolean updateAESKeyHSM(String str) {
        if (this.mForTest == 0) {
            Log.e(TAG, " upateAESKeyHSM mForTestValue is 0 execute return");
            return false;
        }
        enforcePermission(PkiManager.PERMISSION_BYDAUTO_PKI_SET);
        return this.mSdkManager.updateAesKeyHsm(str);
    }

    public boolean updateAESKeyTEE(String str) {
        if (this.mForTest == 0) {
            Log.e(TAG, " upateAESKeyTEE mForTestValue is 0 execute return");
            return false;
        }
        enforcePermission(PkiManager.PERMISSION_BYDAUTO_PKI_SET);
        return this.mSdkManager.updateAesKeyTee(str);
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
        List data = pkiFileData.getData();
        if (data == null) {
            Log.e(TAG, "getByteData data is null");
            return null;
        }
        byte[] bArr = new byte[data.size()];
        for (int i = 0; i < data.size(); i++) {
            bArr[i] = ((Byte) data.get(i)).byteValue();
        }
        return bArr;
    }

    public byte[] getDeviceRsaCertHSM() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getDeviceRsaCertHSM mForTestValue is " + this.mForTest + " execute return");
            return null;
        }
        enforcePermission(PkiManager.PERMISSION_BYDAUTO_PKI_GET);
        return getByteData(this.mSdkManager.getDeviceRsaCertHsm());
    }

    public byte[] getDevicePublicKey() {
        if (this.mForTest == 0) {
            Log.e(TAG, " getDeviceRsaCertHSM mForTestValue is " + this.mForTest + " execute return");
            return null;
        }
        enforcePermission(PkiManager.PERMISSION_BYDAUTO_PKI_GET);
        return getByteData(this.mSdkManager.getEngineerPublicKey());
    }

    public String signEccHSM(String str) {
        if (this.mForTest == 0) {
            Log.e(TAG, " signEccHSM mForTestValue is " + this.mForTest + " execute return");
            return null;
        }
        enforcePermission(PkiManager.PERMISSION_BYDAUTO_PKI_GET);
        return this.mSdkManager.signEccHsm(str);
    }
}
