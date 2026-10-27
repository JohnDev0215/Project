package com.byd.foundation.utils.crypto;

import com.byd.foundation.utils.ConvertUtils;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public class AESEncryptUtils {
    private static final int TAG_BIT_LENGTH = 128;
    private static final String TRANSFORMATION_AES_GCM_NO_PADDING = "AES/GCM/NoPadding";

    public static byte[] encrypt(byte[] bArr, Key key) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        return encrypt(bArr, key, CipherParamsUtils.generateIv());
    }

    public static byte[] encrypt(byte[] bArr, Key key, byte[] bArr2) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        checkArguments(bArr, key, bArr2);
        GCMParameterSpec gCMParameterSpec = new GCMParameterSpec(128, bArr2);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION_AES_GCM_NO_PADDING);
        cipher.init(1, key, gCMParameterSpec);
        byte[] bArrDoFinal = cipher.doFinal(bArr);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bArr2.length + 1 + bArrDoFinal.length);
        byteBufferAllocate.put((byte) bArr2.length);
        byteBufferAllocate.put(bArr2);
        byteBufferAllocate.put(bArrDoFinal);
        return byteBufferAllocate.array();
    }

    public static String encryptBytes2HexString(byte[] bArr, Key key, byte[] bArr2) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        return ConvertUtils.bytes2HexString(encrypt(bArr, key, bArr2));
    }

    public static String encryptString2HexString(String str, Key key) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        return encryptBytes2HexString(ConvertUtils.string2Bytes(str), key, CipherParamsUtils.generateIv());
    }

    public static String encryptString2HexString(String str, Key key, byte[] bArr) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        return encryptBytes2HexString(ConvertUtils.string2Bytes(str), key, bArr);
    }

    public static byte[] decrypt(byte[] bArr, Key key) throws IllegalStateException, BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        checkArguments(bArr, key, null);
        byte b = bArr[0];
        if (b != 12) {
            throw new IllegalStateException("Unexpected iv length");
        }
        Cipher cipher = Cipher.getInstance(TRANSFORMATION_AES_GCM_NO_PADDING);
        cipher.init(2, key, new GCMParameterSpec(128, bArr, 1, b));
        int i = b + 1;
        return cipher.doFinal(bArr, i, bArr.length - i);
    }

    public static byte[] decryptHexString2Bytes(String str, Key key) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        return decrypt(ConvertUtils.hexString2Bytes(str), key);
    }

    public static String decryptHexString2String(String str, Key key) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        return ConvertUtils.bytes2String(decryptHexString2Bytes(str, key));
    }

    private static void checkArguments(byte[] bArr, Key key, byte[] bArr2) throws IllegalArgumentException {
        if (bArr == null || bArr.length == 0) {
            throw new IllegalArgumentException("The encrypt data must not be empty.");
        }
        if (key == null) {
            throw new IllegalArgumentException("The key must not be null.");
        }
        if (bArr2 != null && bArr2.length != 12) {
            throw new IllegalArgumentException("The iv length must be 12, but iv length is " + bArr2.length);
        }
    }
}
