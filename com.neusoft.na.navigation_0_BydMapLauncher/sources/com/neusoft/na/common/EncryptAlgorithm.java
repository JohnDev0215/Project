package com.neusoft.na.common;

import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.InvalidParameterException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import kotlin.jvm.internal.ByteCompanionObject;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

/* JADX INFO: loaded from: classes2.dex */
public class EncryptAlgorithm {
    private static final String TAG = "EncryptAlgorithm";

    public static String vehicleVerify(String str, String str2) throws Exception {
        Log.i(TAG, "vehicleVerify builder:" + str);
        String hexString = Hex.toHexString(hash(str.replace("***", str2).getBytes(StandardCharsets.UTF_8)));
        Log.i(TAG, "vehicleVerify verify:" + hexString);
        return hexString;
    }

    public static String vehicleAuth(String str, String str2, String str3) throws Exception {
        try {
            Log.i(TAG, "vehicleAuth vid:" + str);
            byte[] bArrVehicleEnc = vehicleEnc(str.replace("***", str3).getBytes(StandardCharsets.UTF_8), KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Hex.decode(str2))));
            if (bArrVehicleEnc == null) {
                Log.i(TAG, "vehicleAuth encData is null");
                return "";
            }
            String hexString = Hex.toHexString(bArrVehicleEnc);
            Log.i(TAG, "vehicleAuth hexString:" + hexString);
            return hexString;
        } catch (Exception unused) {
            Log.i(TAG, "vehicleAuth catch error!");
            return "";
        }
    }

    public static byte[] vehicleEnc(byte[] bArr, PublicKey publicKey) throws Exception {
        Log.i(TAG, "vehicleEnc start");
        SecretKey secretKeyCreateAesKey = createAesKey();
        if (secretKeyCreateAesKey == null) {
            Log.i(TAG, "vehicleEnc aesKey is null");
            return null;
        }
        Log.i(TAG, "vehicleEnc start1");
        byte[] bArrAesEncrypt = aesEncrypt(bArr, secretKeyCreateAesKey);
        Log.i(TAG, "vehicleEnc start2");
        byte[] bArrRsaEncrypt = rsaEncrypt(secretKeyCreateAesKey.getEncoded(), publicKey);
        Log.i(TAG, "vehicleEnc start3");
        byte[] bArrLink = link(bArrAesEncrypt, bArrRsaEncrypt);
        Log.i(TAG, "vehicleEnc start4");
        byte[] bArrHash = hash(bArrLink);
        if (bArrHash == null) {
            Log.i(TAG, "vehicleEnc signedData is null");
            return null;
        }
        Log.i(TAG, "vehicleEnc start5");
        byte[] bArrLink2 = link(bArrLink, bArrHash);
        Log.i(TAG, "vehicleEnc end");
        return bArrLink2;
    }

    public static SecretKey createAesKey() {
        try {
            Log.i(TAG, "createAesKey start");
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            Log.i(TAG, "createAesKey start1");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            Log.i(TAG, "createAesKey start2");
            secureRandom.setSeed(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8));
            Log.i(TAG, "createAesKey start3");
            keyGenerator.init(256, secureRandom);
            Log.i(TAG, "createAesKey start4");
            return keyGenerator.generateKey();
        } catch (Exception e) {
            Log.i(TAG, "createAesKey catch error:" + e.getMessage());
            return null;
        }
    }

    public static byte[] rsaEncrypt(byte[] bArr, PublicKey publicKey) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            try {
                Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                cipher.init(1, publicKey);
                int i = 0;
                while (i < bArr.length) {
                    int iMin = Math.min(i + 245, bArr.length);
                    byte[] bArrDoFinal = cipher.doFinal(bArr, i, iMin - i);
                    byteArrayOutputStream.write(bArrDoFinal, 0, bArrDoFinal.length);
                    i = iMin;
                }
                return byteArrayOutputStream.toByteArray();
            } catch (Exception e) {
                throw new InvalidParameterException(e.getMessage());
            }
        } finally {
            byteArrayOutputStream.close();
        }
    }

    public static byte[] aesEncrypt(byte[] bArr, SecretKey secretKey) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] bArr2 = new byte[16];
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8));
            secureRandom.nextBytes(bArr2);
            cipher.init(1, secretKey, new IvParameterSpec(bArr2, 0, cipher.getBlockSize()));
            return link(bArr2, cipher.doFinal(bArr));
        } catch (Exception e) {
            throw new InvalidParameterException(e.getMessage());
        }
    }

    public static byte[] link(byte[] bArr, byte[] bArr2) {
        if (Objects.isNull(bArr) || Objects.isNull(bArr2)) {
            return null;
        }
        byte[] bArrFrom = from(bArr.length);
        if (Objects.isNull(bArrFrom) || bArrFrom[0] > 0) {
            return null;
        }
        bArrFrom[0] = ByteCompanionObject.MAX_VALUE;
        byte[] bArr3 = new byte[bArrFrom.length + bArr.length + bArr2.length];
        System.arraycopy(bArrFrom, 0, bArr3, 0, bArrFrom.length);
        System.arraycopy(bArr, 0, bArr3, bArrFrom.length, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArrFrom.length + bArr.length, bArr2.length);
        return bArr3;
    }

    public static byte[] from(int i) {
        byte[] bArr = new byte[4];
        int i2 = 0;
        for (int i3 = 0; i3 < 4; i3++) {
            i2++;
            int iPow = (int) Math.pow(127.0d, 4 - i2);
            int i4 = i / iPow;
            if (i4 > 127) {
                return null;
            }
            bArr[i3] = (byte) i4;
            i %= iPow;
        }
        return bArr;
    }

    public static byte[] hash(byte[] bArr) {
        try {
            new String(bArr, "UTF-8");
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            Log.i(TAG, "hash1");
            messageDigest.update(bArr);
            Log.i(TAG, "hash2");
            return messageDigest.digest();
        } catch (Exception e) {
            Log.i(TAG, "hash catch error:" + e.getMessage());
            return null;
        }
    }

    public static boolean verify(byte[] bArr, byte[] bArr2, PublicKey publicKey) {
        try {
            Signature signature = Signature.getInstance("SHA512WithRSA");
            signature.initVerify(publicKey);
            signature.update(bArr);
            return signature.verify(bArr2);
        } catch (Exception e) {
            throw new InvalidParameterException(e.getMessage());
        }
    }

    public static byte[] aesDecrypt(byte[] bArr, SecretKey secretKey) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            List<byte[]> listSplit = split(bArr);
            byte[] bArr2 = listSplit.get(0);
            byte[] bArr3 = listSplit.get(1);
            cipher.init(2, secretKey, new IvParameterSpec(bArr2, 0, cipher.getBlockSize()));
            return cipher.doFinal(bArr3);
        } catch (Exception e) {
            throw new InvalidParameterException(e.getMessage());
        }
    }

    public static List<byte[]> split(byte[] bArr) {
        ArrayList arrayList = new ArrayList(2);
        if (Objects.isNull(bArr)) {
            return arrayList;
        }
        byte[] bArr2 = new byte[4];
        System.arraycopy(bArr, 0, bArr2, 0, 4);
        if (bArr2[0] != 127) {
            return arrayList;
        }
        bArr2[0] = 0;
        int i = to(bArr2);
        if (i >= bArr.length) {
            return arrayList;
        }
        byte[] bArr3 = new byte[i];
        System.arraycopy(bArr, 4, bArr3, 0, i);
        arrayList.add(bArr3);
        int length = (bArr.length - 4) - i;
        byte[] bArr4 = new byte[length];
        System.arraycopy(bArr, 4 + i, bArr4, 0, length);
        arrayList.add(bArr4);
        return arrayList;
    }

    public static int to(byte[] bArr) {
        int length = bArr.length;
        int i = 0;
        for (byte b : bArr) {
            length--;
            if (length >= 0) {
                int iPow = (int) (((double) b) * Math.pow(127.0d, length));
                if (iPow < 0) {
                    return 0;
                }
                i += iPow;
            }
        }
        return i;
    }

    static {
        Security.addProvider(new BouncyCastleProvider());
    }
}
