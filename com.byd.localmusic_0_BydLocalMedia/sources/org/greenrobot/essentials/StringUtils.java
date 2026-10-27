package org.greenrobot.essentials;

import android.security.keystore.KeyProperties;
import android.telecom.Logging.Session;
import android.text.format.DateFormat;
import com.bumptech.glide.load.Key;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class StringUtils {
    private static final char[] HEX_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', DateFormat.CAPITAL_AM_PM, 'B', 'C', 'D', DateFormat.DAY, 'F'};

    public static String[] split(String str, char c) {
        String[] strArr = new String[32];
        int i = 0;
        int i2 = 0;
        while (true) {
            int iIndexOf = str.indexOf(c, i);
            if (iIndexOf != -1) {
                if (i2 == strArr.length) {
                    strArr = (String[]) Arrays.copyOf(strArr, strArr.length * 2);
                }
                strArr[i2] = str.substring(i, iIndexOf);
                i = iIndexOf + 1;
                i2++;
            } else {
                String[] strArr2 = (String[]) Arrays.copyOf(strArr, i2 + 1);
                strArr2[i2] = str.substring(i);
                return strArr2;
            }
        }
    }

    public static String encodeUrl(String str) {
        try {
            return URLEncoder.encode(str, Key.STRING_CHARSET_NAME);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String encodeUrlIso(String str) {
        try {
            return URLEncoder.encode(str, "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String decodeUrl(String str) {
        try {
            return URLDecoder.decode(str, Key.STRING_CHARSET_NAME);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String decodeUrlIso(String str) {
        try {
            return URLDecoder.decode(str, "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String md5(String str) {
        return digest(str, KeyProperties.DIGEST_MD5, Key.STRING_CHARSET_NAME);
    }

    public static String sha1(String str) {
        return digest(str, KeyProperties.DIGEST_SHA1, Key.STRING_CHARSET_NAME);
    }

    public static String sha256(String str) {
        return digest(str, "SHA-256", Key.STRING_CHARSET_NAME);
    }

    public static String digest(String str, String str2, String str3) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str2);
            messageDigest.update(str.getBytes(str3));
            return hex(messageDigest.digest());
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String hex(byte[] bArr) {
        int length = bArr.length * 2;
        char[] cArr = new char[length];
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = i2 + 1;
            int i4 = bArr[i2] & 255;
            int i5 = i + 1;
            char[] cArr2 = HEX_CHARS;
            cArr[i] = cArr2[i4 >>> 4];
            i = i5 + 1;
            cArr[i5] = cArr2[i4 & 15];
            i2 = i3;
        }
        return new String(cArr);
    }

    public static byte[] parseHex(String str) {
        int length = str.length();
        if (length % 2 != 0) {
            throw new IllegalArgumentException("Illegal string length: " + length);
        }
        int i = length / 2;
        byte[] bArr = new byte[i];
        int i2 = 0;
        int i3 = 0;
        while (i2 < i) {
            int i4 = i3 + 1;
            bArr[i2] = (byte) ((parseHexDigit(str.charAt(i3)) << 4) | parseHexDigit(str.charAt(i4)));
            i2++;
            i3 = i4 + 1;
        }
        return bArr;
    }

    public static int parseHexDigit(char c) {
        if ('0' <= c && c <= '9') {
            return c - '0';
        }
        char c2 = DateFormat.CAPITAL_AM_PM;
        if ('A' > c || c > 'F') {
            c2 = DateFormat.AM_PM;
            if ('a' > c || c > 'f') {
                throw new IllegalArgumentException("Illegal hex digit: " + c);
            }
        }
        return (c - c2) + 10;
    }

    public static String resolveEntity(String str) {
        if (str.length() <= 1 || str.charAt(0) != '#') {
            return str.equals("apos") ? "'" : str.equals("quot") ? "\"" : str.equals("gt") ? ">" : str.equals("lt") ? "<" : str.equals("amp") ? "&" : str;
        }
        if (str.charAt(1) == 'x') {
            return String.valueOf((char) Integer.parseInt(str.substring(2), 16));
        }
        return String.valueOf((char) Integer.parseInt(str.substring(1)));
    }

    public static String ellipsize(String str, int i) {
        return ellipsize(str, i, Session.TRUNCATE_STRING);
    }

    public static String ellipsize(String str, int i, String str2) {
        if (str == null || str.length() <= i) {
            return str;
        }
        return str.substring(0, i - str2.length()) + str2;
    }

    public static String[] splitLines(String str, boolean z) {
        if (z) {
            return str.split("[\n\r]+");
        }
        return str.split("\\r?\\n");
    }

    public static List<String> findLinesContaining(String str, String str2) {
        String[] strArrSplitLines = splitLines(str, true);
        ArrayList arrayList = new ArrayList();
        for (String str3 : strArrSplitLines) {
            if (str3.contains(str2)) {
                arrayList.add(str3);
            }
        }
        return arrayList;
    }

    public static String join(Iterable<?> iterable, String str) {
        if (iterable == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Iterator<?> it = iterable.iterator();
        char cCharAt = str.length() == 1 ? str.charAt(0) : (char) 0;
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                if (cCharAt != 0) {
                    sb.append(cCharAt);
                } else {
                    sb.append(str);
                }
            }
        }
        return sb.toString();
    }

    public static String join(int[] iArr, String str) {
        if (iArr == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder(Math.max(16, (str.length() + 1) * iArr.length));
        char cCharAt = str.length() == 1 ? str.charAt(0) : (char) 0;
        for (int i = 0; i < iArr.length; i++) {
            if (i != 0) {
                if (cCharAt != 0) {
                    sb.append(cCharAt);
                } else {
                    sb.append(str);
                }
            }
            sb.append(iArr[i]);
        }
        return sb.toString();
    }

    public static String join(String[] strArr, String str) {
        if (strArr == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder(Math.max(16, (str.length() + 1) * strArr.length));
        char cCharAt = str.length() == 1 ? str.charAt(0) : (char) 0;
        for (int i = 0; i < strArr.length; i++) {
            if (i != 0) {
                if (cCharAt != 0) {
                    sb.append(cCharAt);
                } else {
                    sb.append(str);
                }
            }
            sb.append(strArr[i]);
        }
        return sb.toString();
    }
}
