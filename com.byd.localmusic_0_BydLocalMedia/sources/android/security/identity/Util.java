package android.security.identity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPoint;
import java.util.Collection;
import java.util.Iterator;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes3.dex */
class Util {
    private static final String TAG = "Util";

    Util() {
    }

    static int[] integerCollectionToArray(Collection<Integer> collection) {
        int[] iArr = new int[collection.size()];
        Iterator<Integer> it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            iArr[i] = it.next().intValue();
            i++;
        }
        return iArr;
    }

    static byte[] stripLeadingZeroes(byte[] bArr) {
        int i = 0;
        int i2 = 0;
        while (i2 < bArr.length && bArr[i2] == 0) {
            i2++;
        }
        byte[] bArr2 = new byte[bArr.length - i2];
        while (i2 < bArr.length) {
            bArr2[i] = bArr[i2];
            i++;
            i2++;
        }
        return bArr2;
    }

    static byte[] publicKeyEncodeUncompressedForm(PublicKey publicKey) {
        ECPoint w = ((ECPublicKey) publicKey).getW();
        byte[] bArrStripLeadingZeroes = stripLeadingZeroes(w.getAffineX().toByteArray());
        byte[] bArrStripLeadingZeroes2 = stripLeadingZeroes(w.getAffineY().toByteArray());
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(4);
            byteArrayOutputStream.write(bArrStripLeadingZeroes);
            byteArrayOutputStream.write(bArrStripLeadingZeroes2);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Unexpected IOException", e);
        }
    }

    static byte[] computeHkdf(String str, byte[] bArr, byte[] bArr2, byte[] bArr3, int i) {
        try {
            Mac mac = Mac.getInstance(str);
            if (i > mac.getMacLength() * 255) {
                throw new RuntimeException("size too large");
            }
            if (bArr2 != null) {
                try {
                    if (bArr2.length == 0) {
                        mac.init(new SecretKeySpec(new byte[mac.getMacLength()], str));
                    } else {
                        mac.init(new SecretKeySpec(bArr2, str));
                    }
                } catch (InvalidKeyException e) {
                    throw new RuntimeException("Error MACing", e);
                }
            } else {
                mac.init(new SecretKeySpec(new byte[mac.getMacLength()], str));
            }
            byte[] bArr4 = new byte[i];
            mac.init(new SecretKeySpec(mac.doFinal(bArr), str));
            byte[] bArrDoFinal = new byte[0];
            int i2 = 1;
            int length = 0;
            while (true) {
                mac.update(bArrDoFinal);
                mac.update(bArr3);
                mac.update((byte) i2);
                bArrDoFinal = mac.doFinal();
                if (bArrDoFinal.length + length < i) {
                    System.arraycopy(bArrDoFinal, 0, bArr4, length, bArrDoFinal.length);
                    length += bArrDoFinal.length;
                    i2++;
                } else {
                    System.arraycopy(bArrDoFinal, 0, bArr4, length, i - length);
                    return bArr4;
                }
            }
        } catch (NoSuchAlgorithmException e2) {
            throw new RuntimeException("No such algorithm: " + str, e2);
        }
    }
}
