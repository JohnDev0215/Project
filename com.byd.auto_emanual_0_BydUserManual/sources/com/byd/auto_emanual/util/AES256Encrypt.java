package com.byd.auto_emanual.util;

import android.util.Log;
import com.byd.foundation.utils.crypto.CipherParamsUtils;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes.dex */
public class AES256Encrypt {
    private static final String TAG = "AES256Encrypt";
    private static IvParameterSpec iv = new IvParameterSpec(new byte[16]);

    public static String stringAESEncrypt(String enStr, String key) {
        byte[] bArrDoFinal = new byte[0];
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(key.getBytes());
            SecretKeySpec secretKeySpec = new SecretKeySpec(messageDigest.digest(), CipherParamsUtils.KEY_ALGORITHM_AES);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(1, secretKeySpec, iv);
            bArrDoFinal = cipher.doFinal(enStr.getBytes());
        } catch (InvalidAlgorithmParameterException e) {
            Log.e(TAG, "  ==== InvalidAlgorithmParameterException ====> " + e.getMessage());
            e.printStackTrace();
        } catch (InvalidKeyException e2) {
            Log.e(TAG, "  ==== InvalidKeyException ====> " + e2.getMessage());
            e2.printStackTrace();
        } catch (NoSuchAlgorithmException e3) {
            Log.e(TAG, "  ==== NoSuchAlgorithmException ====> " + e3.getMessage());
            e3.printStackTrace();
        } catch (BadPaddingException e4) {
            Log.e(TAG, "  ==== BadPaddingException ====> " + e4.getMessage());
            e4.printStackTrace();
        } catch (IllegalBlockSizeException e5) {
            Log.e(TAG, "  ==== IllegalBlockSizeException ====> " + e5.getMessage());
            e5.printStackTrace();
        } catch (NoSuchPaddingException e6) {
            Log.e(TAG, "  ==== NoSuchPaddingException ====> " + e6.getMessage());
            e6.printStackTrace();
        }
        return byteToHexString(bArrDoFinal);
    }

    public static String byteToHexString(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bytes) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                stringBuffer.append(0);
            }
            stringBuffer.append(hexString);
        }
        return stringBuffer.toString();
    }

    public static byte[] stringHexToByte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] bArr = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int i2 = i * 2;
            int i3 = i2 + 1;
            bArr[i] = (byte) ((Integer.parseInt(hexStr.substring(i2, i3), 16) * 16) + Integer.parseInt(hexStr.substring(i3, i2 + 2), 16));
        }
        return bArr;
    }

    public static String stringAESDecipher(String deStr, String key) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        byte[] bArrStringHexToByte = stringHexToByte(deStr);
        String str = TAG;
        Log.e(str, "deByte ======>    " + bArrStringHexToByte);
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(key.getBytes());
        SecretKeySpec secretKeySpec = new SecretKeySpec(messageDigest.digest(), CipherParamsUtils.KEY_ALGORITHM_AES);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(2, secretKeySpec, iv);
        byte[] bArrDoFinal = cipher.doFinal(bArrStringHexToByte);
        Log.e(str, "rebyte ======>    " + bArrDoFinal);
        return new String(bArrDoFinal);
    }
}
