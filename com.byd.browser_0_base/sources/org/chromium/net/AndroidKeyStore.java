package org.chromium.net;

import android.util.Log;
import defpackage.C0175b;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class AndroidKeyStore {
    @CalledByNative
    public static byte[] encryptWithPrivateKey(PrivateKey privateKey, String str, byte[] bArr) {
        try {
            Cipher cipher = Cipher.getInstance(str);
            try {
                cipher.init(1, privateKey);
                return cipher.doFinal(bArr);
            } catch (Exception e) {
                StringBuilder sbN = C0175b.n("Exception while encrypting input with ", str, " and ");
                sbN.append(privateKey.getAlgorithm());
                sbN.append(" private key (");
                sbN.append(privateKey.getClass().getName());
                sbN.append("): ");
                sbN.append(e);
                Log.e("cr_AndroidKeyStore", sbN.toString());
                return null;
            }
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e2) {
            Log.e("cr_AndroidKeyStore", "Cipher " + str + " not supported: " + e2);
            return null;
        }
    }

    @CalledByNative
    public static String getPrivateKeyClassName(PrivateKey privateKey) {
        return privateKey.getClass().getName();
    }

    @CalledByNative
    public static boolean privateKeySupportsCipher(PrivateKey privateKey, String str) {
        try {
            Cipher.getInstance(str).init(1, privateKey);
            return true;
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException unused) {
            return false;
        } catch (Exception e) {
            Log.e("cr_AndroidKeyStore", "Exception while checking support for " + str + ": " + e);
            return false;
        }
    }

    @CalledByNative
    public static boolean privateKeySupportsSignature(PrivateKey privateKey, String str) {
        try {
            Signature.getInstance(str).initSign(privateKey);
            return true;
        } catch (InvalidKeyException | NoSuchAlgorithmException unused) {
            return false;
        } catch (Exception e) {
            Log.e("cr_AndroidKeyStore", "Exception while checking support for " + str + ": " + e);
            return false;
        }
    }

    @CalledByNative
    public static byte[] signWithPrivateKey(PrivateKey privateKey, String str, byte[] bArr) {
        try {
            Signature signature = Signature.getInstance(str);
            try {
                signature.initSign(privateKey);
                signature.update(bArr);
                return signature.sign();
            } catch (Exception e) {
                StringBuilder sbN = C0175b.n("Exception while signing message with ", str, " and ");
                sbN.append(privateKey.getAlgorithm());
                sbN.append(" private key (");
                sbN.append(privateKey.getClass().getName());
                sbN.append("): ");
                sbN.append(e);
                Log.e("cr_AndroidKeyStore", sbN.toString());
                return null;
            }
        } catch (NoSuchAlgorithmException e2) {
            Log.e("cr_AndroidKeyStore", "Signature algorithm " + str + " not supported: " + e2);
            return null;
        }
    }
}
