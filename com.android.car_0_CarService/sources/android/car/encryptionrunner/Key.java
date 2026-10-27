package android.car.encryptionrunner;

import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

/* JADX INFO: loaded from: classes.dex */
public interface Key {
    byte[] asBytes();

    byte[] decryptData(byte[] bArr) throws SignatureException;

    byte[] encryptData(byte[] bArr);

    byte[] getUniqueSession() throws NoSuchAlgorithmException;
}
