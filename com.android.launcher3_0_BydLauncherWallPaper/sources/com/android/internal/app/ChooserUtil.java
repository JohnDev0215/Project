package com.android.internal.app;

import android.security.keystore.KeyProperties;
import com.android.internal.midi.MidiConstants;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: classes4.dex */
public class ChooserUtil {
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    public static String md5(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(KeyProperties.DIGEST_MD5);
            messageDigest.update(str.getBytes(UTF_8));
            return convertBytesToHexString(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    private static String convertBytesToHexString(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            int i2 = i * 2;
            cArr[i2] = Character.forDigit((b >> 4) & 15, 16);
            cArr[i2 + 1] = Character.forDigit(b & MidiConstants.STATUS_CHANNEL_MASK, 16);
        }
        return new String(cArr);
    }

    private ChooserUtil() {
    }
}
