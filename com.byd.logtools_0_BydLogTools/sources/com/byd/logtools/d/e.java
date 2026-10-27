package com.byd.logtools.d;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import okhttp3.HttpUrl;

/* JADX INFO: compiled from: FileAESUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static String f3162a = "AES";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static String f3163b = "AES/CBC/PKCS7Padding";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static int f3164c = 32;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static int f3165d = 16;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static boolean f3166e = true;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static int f3167f = 1000;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static int f3168g = 1001;

    public static void a(String str, String str2, String str3, int i) throws Exception {
        f3166e = true;
        Cipher cipherC = c(str3, 1);
        FileInputStream fileInputStream = new FileInputStream(str);
        FileOutputStream fileOutputStream = new FileOutputStream(str2);
        CipherInputStream cipherInputStream = new CipherInputStream(fileInputStream, cipherC);
        byte[] bArr = new byte[4096];
        while (true) {
            int i2 = cipherInputStream.read(bArr);
            if (i2 != -1) {
                if (!f3166e && i == f3167f) {
                    BydLogHelper.logE("aesEncryptFile isUDiskMount = " + f3166e);
                    break;
                }
                fileOutputStream.write(bArr, 0, i2);
                fileOutputStream.flush();
            } else {
                break;
            }
        }
        fileOutputStream.getFD().sync();
        cipherInputStream.close();
        fileInputStream.close();
        fileOutputStream.close();
    }

    private static SecretKeySpec b(String str) {
        byte[] bytes;
        if (str == null) {
            str = HttpUrl.FRAGMENT_ENCODE_SET;
        }
        StringBuffer stringBuffer = new StringBuffer(f3164c);
        stringBuffer.append(str);
        while (stringBuffer.length() < f3164c) {
            stringBuffer.append("0");
        }
        int length = stringBuffer.length();
        int i = f3164c;
        if (length > i) {
            stringBuffer.setLength(i);
        }
        try {
            bytes = stringBuffer.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            bytes = null;
        }
        return new SecretKeySpec(bytes, f3162a);
    }

    public static Cipher c(String str, int i) throws Exception {
        byte[] bArrDigest = new Digester(DigestAlgorithm.MD5).digest(str.substring(str.length() - 8));
        byte[] bArr = new byte[4];
        byte[] bArr2 = new byte[4];
        for (int i2 = 0; i2 < 8; i2++) {
            byte b2 = (byte) (~bArrDigest[i2]);
            if (i2 < 4) {
                bArr[i2] = b2;
            } else {
                bArr2[i2 - 4] = b2;
            }
        }
        String strA = c.a.a.a.d.a(bArrDigest);
        byte[] bArrEncrypt = cn.hutool.crypto.d.d(strA.getBytes(StandardCharsets.UTF_8)).encrypt(bArr);
        byte[] bytes = strA.getBytes(StandardCharsets.UTF_8);
        int length = bytes.length / 2;
        for (int i3 = 0; i3 < length; i3++) {
            byte b3 = bytes[i3];
            int length2 = (bytes.length - i3) - 1;
            bytes[i3] = bytes[length2];
            bytes[length2] = b3;
        }
        byte[] bArrEncrypt2 = cn.hutool.crypto.d.d(bytes).encrypt(bArr2);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
        byteBufferAllocate.put(bArrEncrypt, 0, 4);
        byte[] bArrArray = byteBufferAllocate.put(bArrEncrypt2, 0, 4).array();
        ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(8);
        byteBufferAllocate2.put(bArrEncrypt2, 4, 4);
        byte[] bArrArray2 = byteBufferAllocate2.put(bArrEncrypt, 4, 4).array();
        SecretKeySpec secretKeySpecB = b(cn.hutool.core.util.d.d(bArrArray));
        byte[] bArrD = d(cn.hutool.core.util.d.d(bArrArray2));
        Cipher cipher = Cipher.getInstance(f3163b);
        cipher.init(i, secretKeySpecB, new IvParameterSpec(bArrD));
        return cipher;
    }

    private static byte[] d(String str) {
        if (str == null) {
            str = HttpUrl.FRAGMENT_ENCODE_SET;
        }
        StringBuffer stringBuffer = new StringBuffer(f3165d);
        stringBuffer.append(str);
        while (stringBuffer.length() < f3165d) {
            stringBuffer.append("0");
        }
        int length = stringBuffer.length();
        int i = f3165d;
        if (length > i) {
            stringBuffer.setLength(i);
        }
        try {
            return stringBuffer.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
