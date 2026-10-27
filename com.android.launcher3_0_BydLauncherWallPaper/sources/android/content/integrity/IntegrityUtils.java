package android.content.integrity;

import com.android.internal.midi.MidiConstants;
import com.android.internal.util.Preconditions;

/* JADX INFO: loaded from: classes.dex */
public class IntegrityUtils {
    private static final char[] HEX_CHARS = "0123456789ABCDEF".toCharArray();

    public static byte[] getBytesFromHexDigest(String str) {
        Preconditions.checkArgument(str.length() % 2 == 0, "Invalid hex encoding " + str + ": must have even length");
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (hexToDec(str.charAt(i2 + 1)) | (hexToDec(str.charAt(i2)) << 4));
        }
        return bArr;
    }

    public static String getHexDigest(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = (bArr[i] >>> 4) & 15;
            int i3 = bArr[i] & MidiConstants.STATUS_CHANNEL_MASK;
            int i4 = i * 2;
            cArr[i4] = decToHex(i2);
            cArr[i4 + 1] = decToHex(i3);
        }
        return new String(cArr);
    }

    private static int hexToDec(int i) {
        if (i >= 48 && i <= 57) {
            return i - 48;
        }
        int i2 = 97;
        if (i < 97 || i > 102) {
            i2 = 65;
            if (i < 65 || i > 70) {
                throw new IllegalArgumentException("Invalid hex char " + i);
            }
        }
        return (i - i2) + 10;
    }

    private static char decToHex(int i) {
        if (i >= 0) {
            char[] cArr = HEX_CHARS;
            if (i < cArr.length) {
                return cArr[i];
            }
        }
        throw new IllegalArgumentException("Invalid dec value to be converted to hex digit " + i);
    }
}
