package g.a.f;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;

/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f5728a;

    public static class a implements PrivilegedAction<String> {
        @Override // java.security.PrivilegedAction
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public String run() {
            return System.getProperty("line.separator");
        }
    }

    static {
        try {
            try {
                f5728a = (String) AccessController.doPrivileged(new a());
            } catch (Exception unused) {
                f5728a = String.format("%n", new Object[0]);
            }
        } catch (Exception unused2) {
            f5728a = "\n";
        }
    }

    public static char[] a(byte[] bArr) {
        int length = bArr.length;
        char[] cArr = new char[length];
        for (int i2 = 0; i2 != length; i2++) {
            cArr[i2] = (char) (bArr[i2] & 255);
        }
        return cArr;
    }

    public static String b(byte[] bArr) {
        return new String(a(bArr));
    }

    public static String c(byte[] bArr) {
        char c2;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i3 < bArr.length) {
            i4++;
            if ((bArr[i3] & 240) == 240) {
                i4++;
                i3 += 4;
            } else {
                i3 = (bArr[i3] & 224) == 224 ? i3 + 3 : (bArr[i3] & 192) == 192 ? i3 + 2 : i3 + 1;
            }
        }
        char[] cArr = new char[i4];
        int i5 = 0;
        while (i2 < bArr.length) {
            if ((bArr[i2] & 240) == 240) {
                int i6 = (((((bArr[i2] & 3) << 18) | ((bArr[i2 + 1] & 63) << 12)) | ((bArr[i2 + 2] & 63) << 6)) | (bArr[i2 + 3] & 63)) - 65536;
                char c3 = (char) (55296 | (i6 >> 10));
                c2 = (char) ((i6 & 1023) | 56320);
                cArr[i5] = c3;
                i2 += 4;
                i5++;
            } else if ((bArr[i2] & 224) == 224) {
                c2 = (char) (((bArr[i2] & 15) << 12) | ((bArr[i2 + 1] & 63) << 6) | (bArr[i2 + 2] & 63));
                i2 += 3;
            } else if ((bArr[i2] & 208) == 208 || (bArr[i2] & 192) == 192) {
                int i7 = (bArr[i2] & 31) << 6;
                byte b2 = bArr[i2 + 1];
                c2 = (char) (i7 | (b2 & 63));
                i2 += 2;
            } else {
                c2 = (char) (bArr[i2] & 255);
                i2++;
            }
            cArr[i5] = c2;
            i5++;
        }
        return new String(cArr);
    }

    public static String d() {
        return f5728a;
    }

    public static byte[] e(String str) {
        int length = str.length();
        byte[] bArr = new byte[length];
        for (int i2 = 0; i2 != length; i2++) {
            bArr[i2] = (byte) str.charAt(i2);
        }
        return bArr;
    }

    public static byte[] f(char[] cArr) {
        int length = cArr.length;
        byte[] bArr = new byte[length];
        for (int i2 = 0; i2 != length; i2++) {
            bArr[i2] = (byte) cArr[i2];
        }
        return bArr;
    }

    public static String g(String str) {
        char[] charArray = str.toCharArray();
        boolean z = false;
        for (int i2 = 0; i2 != charArray.length; i2++) {
            char c2 = charArray[i2];
            if ('A' <= c2 && 'Z' >= c2) {
                charArray[i2] = (char) ((c2 - 'A') + 97);
                z = true;
            }
        }
        return z ? new String(charArray) : str;
    }

    public static void h(char[] cArr, OutputStream outputStream) throws IOException {
        int i2;
        int i3;
        int i4 = 0;
        while (i4 < cArr.length) {
            char c2 = cArr[i4];
            int i5 = c2;
            if (c2 >= 128) {
                if (c2 < 2048) {
                    i2 = (c2 >> 6) | 192;
                } else if (c2 < 55296 || c2 > 57343) {
                    outputStream.write((c2 >> '\f') | 224);
                    i2 = ((c2 >> 6) & 63) | 128;
                } else {
                    i4++;
                    if (i4 >= cArr.length) {
                        throw new IllegalStateException("invalid UTF-16 codepoint");
                    }
                    char c3 = cArr[i4];
                    if (c2 > 56319) {
                        throw new IllegalStateException("invalid UTF-16 codepoint");
                    }
                    int i6 = (((c2 & 1023) << 10) | (c3 & 1023)) + 65536;
                    outputStream.write((i6 >> 18) | 240);
                    outputStream.write(((i6 >> 12) & 63) | 128);
                    outputStream.write(((i6 >> 6) & 63) | 128);
                    i3 = i6;
                    i5 = (i3 & 63) | 128;
                }
                outputStream.write(i2);
                i3 = c2;
                i5 = (i3 & 63) | 128;
            }
            outputStream.write(i5);
            i4++;
        }
    }

    public static byte[] i(String str) {
        return j(str.toCharArray());
    }

    public static byte[] j(char[] cArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            h(cArr, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException unused) {
            throw new IllegalStateException("cannot encode string to byte array!");
        }
    }

    public static String k(String str) {
        char[] charArray = str.toCharArray();
        boolean z = false;
        for (int i2 = 0; i2 != charArray.length; i2++) {
            char c2 = charArray[i2];
            if ('a' <= c2 && 'z' >= c2) {
                charArray[i2] = (char) ((c2 - 'a') + 65);
                z = true;
            }
        }
        return z ? new String(charArray) : str;
    }
}
