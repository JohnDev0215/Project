package com.byd.auto_emanual.util;

import com.byd.auto_emanual.fragment.ZipInfo;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.shiro.crypto.hash.Md5Hash;

/* JADX INFO: loaded from: classes.dex */
public class MD5Encryption {
    public static String encodeMD5String(String context) {
        byte[] bArrDigest = new byte[0];
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(Md5Hash.ALGORITHM_NAME);
            messageDigest.update(context.getBytes("UTF-8"));
            bArrDigest = messageDigest.digest();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
        }
        return byteArray2String(bArrDigest).toUpperCase();
    }

    private static String byteArray2String(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (byte b : data) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                sb.append(ZipInfo.NO_UPLOAD_TIME);
            }
            sb.append(hexString);
        }
        return sb.toString();
    }
}
