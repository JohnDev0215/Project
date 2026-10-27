package l2;

import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.hardware.bydauto.statistic.BYDAutoStatisticDevice;
import com.alibaba.fastjson.JSONException;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.Properties;

/* JADX INFO: compiled from: IOUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Properties f6192a = new Properties();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Charset f6193b = Charset.forName("UTF-8");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final char[] f6194c = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final boolean[] f6195d = new boolean[256];

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final boolean[] f6196e = new boolean[256];

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final byte[] f6197f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final byte[] f6198g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final boolean[] f6199h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final boolean[] f6200i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final char[] f6201j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final char[] f6202k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final char[] f6203l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final char[] f6204m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final char[] f6205n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final int[] f6206o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final char[] f6207p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final int[] f6208q;

    /* JADX INFO: compiled from: IOUtils.java */
    public static class a implements PrivilegedAction<InputStream> {
        @Override // java.security.PrivilegedAction
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public InputStream run() {
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            return contextClassLoader != null ? contextClassLoader.getResourceAsStream("fastjson.properties") : ClassLoader.getSystemResourceAsStream("fastjson.properties");
        }
    }

    static {
        char c8 = 0;
        while (true) {
            boolean[] zArr = f6195d;
            if (c8 >= zArr.length) {
                break;
            }
            if (c8 >= 'A' && c8 <= 'Z') {
                zArr[c8] = true;
            } else if (c8 >= 'a' && c8 <= 'z') {
                zArr[c8] = true;
            } else if (c8 == '_' || c8 == '$') {
                zArr[c8] = true;
            }
            c8 = (char) (c8 + 1);
        }
        char c9 = 0;
        while (true) {
            boolean[] zArr2 = f6196e;
            if (c9 < zArr2.length) {
                if (c9 >= 'A' && c9 <= 'Z') {
                    zArr2[c9] = true;
                } else if (c9 >= 'a' && c9 <= 'z') {
                    zArr2[c9] = true;
                } else if (c9 == '_') {
                    zArr2[c9] = true;
                } else if (c9 >= '0' && c9 <= '9') {
                    zArr2[c9] = true;
                }
                c9 = (char) (c9 + 1);
            } else {
                try {
                    break;
                } catch (Throwable unused) {
                }
            }
        }
        l();
        byte[] bArr = new byte[161];
        f6197f = bArr;
        byte[] bArr2 = new byte[161];
        f6198g = bArr2;
        f6199h = new boolean[161];
        f6200i = new boolean[161];
        f6201j = new char[93];
        bArr[0] = 4;
        bArr[1] = 4;
        bArr[2] = 4;
        bArr[3] = 4;
        bArr[4] = 4;
        bArr[5] = 4;
        bArr[6] = 4;
        bArr[7] = 4;
        bArr[8] = 1;
        bArr[9] = 1;
        bArr[10] = 1;
        bArr[11] = 4;
        bArr[12] = 1;
        bArr[13] = 1;
        bArr[34] = 1;
        bArr[92] = 1;
        bArr2[0] = 4;
        bArr2[1] = 4;
        bArr2[2] = 4;
        bArr2[3] = 4;
        bArr2[4] = 4;
        bArr2[5] = 4;
        bArr2[6] = 4;
        bArr2[7] = 4;
        bArr2[8] = 1;
        bArr2[9] = 1;
        bArr2[10] = 1;
        bArr2[11] = 4;
        bArr2[12] = 1;
        bArr2[13] = 1;
        bArr2[92] = 1;
        bArr2[39] = 1;
        for (int i8 = 14; i8 <= 31; i8++) {
            f6197f[i8] = 4;
            f6198g[i8] = 4;
        }
        for (int i9 = 127; i9 < 160; i9++) {
            f6197f[i9] = 4;
            f6198g[i9] = 4;
        }
        for (int i10 = 0; i10 < 161; i10++) {
            f6199h[i10] = f6197f[i10] != 0;
            f6200i[i10] = f6198g[i10] != 0;
        }
        char[] cArr = f6201j;
        cArr[0] = '0';
        cArr[1] = '1';
        cArr[2] = '2';
        cArr[3] = '3';
        cArr[4] = '4';
        cArr[5] = '5';
        cArr[6] = '6';
        cArr[7] = '7';
        cArr[8] = 'b';
        cArr[9] = 't';
        cArr[10] = 'n';
        cArr[11] = 'v';
        cArr[12] = 'f';
        cArr[13] = 'r';
        cArr[34] = '\"';
        cArr[39] = '\'';
        cArr[47] = '/';
        cArr[92] = '\\';
        f6202k = new char[]{'0', '0', '0', '1', '0', '2', '0', '3', '0', '4', '0', '5', '0', '6', '0', '7', '0', '8', '0', '9', '0', 'A', '0', 'B', '0', 'C', '0', 'D', '0', 'E', '0', 'F', '1', '0', '1', '1', '1', '2', '1', '3', '1', '4', '1', '5', '1', '6', '1', '7', '1', '8', '1', '9', '1', 'A', '1', 'B', '1', 'C', '1', 'D', '1', 'E', '1', 'F', '2', '0', '2', '1', '2', '2', '2', '3', '2', '4', '2', '5', '2', '6', '2', '7', '2', '8', '2', '9', '2', 'A', '2', 'B', '2', 'C', '2', 'D', '2', 'E', '2', 'F'};
        f6203l = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        f6204m = new char[]{'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '4', '4', '4', '4', '4', '4', '4', '4', '4', '4', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '6', '6', '6', '6', '6', '6', '6', '6', '6', '6', '7', '7', '7', '7', '7', '7', '7', '7', '7', '7', '8', '8', '8', '8', '8', '8', '8', '8', '8', '8', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9'};
        f6205n = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        f6206o = new int[]{9, 99, 999, 9999, BYDAutoStatisticDevice.MILEAGE_MAX, BYDAutoStatisticDevice.STATISTIC_MILEAGE_MAX, 9999999, BYDAutoInstrumentDevice.MILE_PH_MAX, 999999999, Integer.MAX_VALUE};
        char[] charArray = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
        f6207p = charArray;
        int[] iArr = new int[256];
        f6208q = iArr;
        Arrays.fill(iArr, -1);
        int length = charArray.length;
        for (int i11 = 0; i11 < length; i11++) {
            f6208q[f6207p[i11]] = i11;
        }
        f6208q[61] = 0;
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception unused) {
            }
        }
    }

    public static void b(CharsetDecoder charsetDecoder, ByteBuffer byteBuffer, CharBuffer charBuffer) {
        try {
            CoderResult coderResultDecode = charsetDecoder.decode(byteBuffer, charBuffer, true);
            if (!coderResultDecode.isUnderflow()) {
                coderResultDecode.throwException();
            }
            CoderResult coderResultFlush = charsetDecoder.flush(charBuffer);
            if (coderResultFlush.isUnderflow()) {
                return;
            }
            coderResultFlush.throwException();
        } catch (CharacterCodingException e8) {
            throw new JSONException("utf8 decode error, " + e8.getMessage(), e8);
        }
    }

    public static byte[] c(String str) {
        int i8;
        int length = str.length();
        int i9 = 0;
        if (length == 0) {
            return new byte[0];
        }
        int i10 = length - 1;
        int i11 = 0;
        while (i11 < i10 && f6208q[str.charAt(i11) & 255] < 0) {
            i11++;
        }
        while (i10 > 0 && f6208q[str.charAt(i10) & 255] < 0) {
            i10--;
        }
        int i12 = str.charAt(i10) == '=' ? str.charAt(i10 + (-1)) == '=' ? 2 : 1 : 0;
        int i13 = (i10 - i11) + 1;
        if (length > 76) {
            i8 = (str.charAt(76) == '\r' ? i13 / 78 : 0) << 1;
        } else {
            i8 = 0;
        }
        int i14 = (((i13 - i8) * 6) >> 3) - i12;
        byte[] bArr = new byte[i14];
        int i15 = (i14 / 3) * 3;
        int i16 = 0;
        int i17 = 0;
        while (i16 < i15) {
            int[] iArr = f6208q;
            int i18 = i11 + 1;
            int i19 = i18 + 1;
            int i20 = (iArr[str.charAt(i11)] << 18) | (iArr[str.charAt(i18)] << 12);
            int i21 = i19 + 1;
            int i22 = i20 | (iArr[str.charAt(i19)] << 6);
            int i23 = i21 + 1;
            int i24 = i22 | iArr[str.charAt(i21)];
            int i25 = i16 + 1;
            bArr[i16] = (byte) (i24 >> 16);
            int i26 = i25 + 1;
            bArr[i25] = (byte) (i24 >> 8);
            int i27 = i26 + 1;
            bArr[i26] = (byte) i24;
            if (i8 > 0 && (i17 = i17 + 1) == 19) {
                i23 += 2;
                i17 = 0;
            }
            i11 = i23;
            i16 = i27;
        }
        if (i16 < i14) {
            int i28 = 0;
            while (i11 <= i10 - i12) {
                i9 |= f6208q[str.charAt(i11)] << (18 - (i28 * 6));
                i28++;
                i11++;
            }
            int i29 = 16;
            while (i16 < i14) {
                bArr[i16] = (byte) (i9 >> i29);
                i29 -= 8;
                i16++;
            }
        }
        return bArr;
    }

    public static byte[] d(String str, int i8, int i9) {
        int i10;
        int i11 = 0;
        if (i9 == 0) {
            return new byte[0];
        }
        int i12 = (i8 + i9) - 1;
        while (i8 < i12 && f6208q[str.charAt(i8)] < 0) {
            i8++;
        }
        while (i12 > 0 && f6208q[str.charAt(i12)] < 0) {
            i12--;
        }
        int i13 = str.charAt(i12) == '=' ? str.charAt(i12 + (-1)) == '=' ? 2 : 1 : 0;
        int i14 = (i12 - i8) + 1;
        if (i9 > 76) {
            i10 = (str.charAt(76) == '\r' ? i14 / 78 : 0) << 1;
        } else {
            i10 = 0;
        }
        int i15 = (((i14 - i10) * 6) >> 3) - i13;
        byte[] bArr = new byte[i15];
        int i16 = (i15 / 3) * 3;
        int i17 = 0;
        int i18 = 0;
        while (i17 < i16) {
            int[] iArr = f6208q;
            int i19 = i8 + 1;
            int i20 = i19 + 1;
            int i21 = (iArr[str.charAt(i8)] << 18) | (iArr[str.charAt(i19)] << 12);
            int i22 = i20 + 1;
            int i23 = i21 | (iArr[str.charAt(i20)] << 6);
            int i24 = i22 + 1;
            int i25 = i23 | iArr[str.charAt(i22)];
            int i26 = i17 + 1;
            bArr[i17] = (byte) (i25 >> 16);
            int i27 = i26 + 1;
            bArr[i26] = (byte) (i25 >> 8);
            int i28 = i27 + 1;
            bArr[i27] = (byte) i25;
            if (i10 > 0 && (i18 = i18 + 1) == 19) {
                i24 += 2;
                i18 = 0;
            }
            i8 = i24;
            i17 = i28;
        }
        if (i17 < i15) {
            int i29 = 0;
            while (i8 <= i12 - i13) {
                i11 |= f6208q[str.charAt(i8)] << (18 - (i29 * 6));
                i29++;
                i8++;
            }
            int i30 = 16;
            while (i17 < i15) {
                bArr[i17] = (byte) (i11 >> i30);
                i30 -= 8;
                i17++;
            }
        }
        return bArr;
    }

    public static byte[] e(char[] cArr, int i8, int i9) {
        int i10;
        int i11 = 0;
        if (i9 == 0) {
            return new byte[0];
        }
        int i12 = (i8 + i9) - 1;
        while (i8 < i12 && f6208q[cArr[i8]] < 0) {
            i8++;
        }
        while (i12 > 0 && f6208q[cArr[i12]] < 0) {
            i12--;
        }
        int i13 = cArr[i12] == '=' ? cArr[i12 + (-1)] == '=' ? 2 : 1 : 0;
        int i14 = (i12 - i8) + 1;
        if (i9 > 76) {
            i10 = (cArr[76] == '\r' ? i14 / 78 : 0) << 1;
        } else {
            i10 = 0;
        }
        int i15 = (((i14 - i10) * 6) >> 3) - i13;
        byte[] bArr = new byte[i15];
        int i16 = (i15 / 3) * 3;
        int i17 = 0;
        int i18 = 0;
        while (i17 < i16) {
            int[] iArr = f6208q;
            int i19 = i8 + 1;
            int i20 = i19 + 1;
            int i21 = (iArr[cArr[i8]] << 18) | (iArr[cArr[i19]] << 12);
            int i22 = i20 + 1;
            int i23 = i21 | (iArr[cArr[i20]] << 6);
            int i24 = i22 + 1;
            int i25 = i23 | iArr[cArr[i22]];
            int i26 = i17 + 1;
            bArr[i17] = (byte) (i25 >> 16);
            int i27 = i26 + 1;
            bArr[i26] = (byte) (i25 >> 8);
            int i28 = i27 + 1;
            bArr[i27] = (byte) i25;
            if (i10 > 0 && (i18 = i18 + 1) == 19) {
                i24 += 2;
                i18 = 0;
            }
            i8 = i24;
            i17 = i28;
        }
        if (i17 < i15) {
            int i29 = 0;
            while (i8 <= i12 - i13) {
                i11 |= f6208q[cArr[i8]] << (18 - (i29 * 6));
                i29++;
                i8++;
            }
            int i30 = 16;
            while (i17 < i15) {
                bArr[i17] = (byte) (i11 >> i30);
                i30 -= 8;
                i17++;
            }
        }
        return bArr;
    }

    public static int f(byte[] bArr, int i8, int i9, char[] cArr) {
        int i10 = i8 + i9;
        int iMin = Math.min(i9, cArr.length);
        int i11 = 0;
        while (i11 < iMin && bArr[i8] >= 0) {
            cArr[i11] = (char) bArr[i8];
            i11++;
            i8++;
        }
        while (i8 < i10) {
            int i12 = i8 + 1;
            byte b8 = bArr[i8];
            if (b8 >= 0) {
                cArr[i11] = (char) b8;
                i8 = i12;
                i11++;
            } else {
                if ((b8 >> 5) != -2 || (b8 & 30) == 0) {
                    if ((b8 >> 4) == -2) {
                        int i13 = i12 + 1;
                        if (i13 < i10) {
                            byte b9 = bArr[i12];
                            int i14 = i13 + 1;
                            byte b10 = bArr[i13];
                            if ((b8 != -32 || (b9 & 224) != 128) && (b9 & 192) == 128 && (b10 & 192) == 128) {
                                char c8 = (char) (((b8 << 12) ^ (b9 << 6)) ^ ((-123008) ^ b10));
                                if (c8 >= 55296 && c8 < 57344) {
                                    return -1;
                                }
                                cArr[i11] = c8;
                                i11++;
                                i8 = i14;
                            }
                        }
                        return -1;
                    }
                    if ((b8 >> 3) == -2 && i12 + 2 < i10) {
                        int i15 = i12 + 1;
                        byte b11 = bArr[i12];
                        int i16 = i15 + 1;
                        byte b12 = bArr[i15];
                        int i17 = i16 + 1;
                        byte b13 = bArr[i16];
                        int i18 = (((b8 << 18) ^ (b11 << 12)) ^ (b12 << 6)) ^ (3678080 ^ b13);
                        if ((b11 & 192) == 128 && (b12 & 192) == 128 && (b13 & 192) == 128 && i18 >= 65536 && i18 < 1114112) {
                            int i19 = i11 + 1;
                            cArr[i11] = (char) ((i18 >>> 10) + 55232);
                            i11 = i19 + 1;
                            cArr[i19] = (char) ((i18 & 1023) + 56320);
                            i8 = i17;
                        }
                    }
                    return -1;
                }
                if (i12 >= i10) {
                    return -1;
                }
                int i20 = i12 + 1;
                byte b14 = bArr[i12];
                if ((b14 & 192) != 128) {
                    return -1;
                }
                cArr[i11] = (char) (((b8 << 6) ^ b14) ^ 3968);
                i8 = i20;
                i11++;
            }
        }
        return i11;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0086  */
    /* JADX WARN: Type inference failed for: r10v16, types: [int] */
    /* JADX WARN: Type inference failed for: r10v25, types: [int] */
    /* JADX WARN: Type inference failed for: r10v26 */
    /* JADX WARN: Type inference failed for: r10v3, types: [char, int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int g(char[] r9, int r10, int r11, byte[] r12) {
        /*
            Method dump skipped, instruction units count: 202
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: l2.e.g(char[], int, int, byte[]):int");
    }

    public static boolean h(char c8) {
        boolean[] zArr = f6195d;
        return c8 < zArr.length && zArr[c8];
    }

    public static void i(int i8, int i9, char[] cArr) {
        char c8;
        if (i8 < 0) {
            c8 = '-';
            i8 = -i8;
        } else {
            c8 = 0;
        }
        while (i8 >= 65536) {
            int i10 = i8 / 100;
            int i11 = i8 - (((i10 << 6) + (i10 << 5)) + (i10 << 2));
            int i12 = i9 - 1;
            cArr[i12] = f6205n[i11];
            i9 = i12 - 1;
            cArr[i9] = f6204m[i11];
            i8 = i10;
        }
        while (true) {
            int i13 = (52429 * i8) >>> 19;
            i9--;
            cArr[i9] = f6203l[i8 - ((i13 << 3) + (i13 << 1))];
            if (i13 == 0) {
                break;
            } else {
                i8 = i13;
            }
        }
        if (c8 != 0) {
            cArr[i9 - 1] = c8;
        }
    }

    public static void j(long j8, int i8, char[] cArr) {
        char c8;
        if (j8 < 0) {
            c8 = '-';
            j8 = -j8;
        } else {
            c8 = 0;
        }
        while (j8 > 2147483647L) {
            long j9 = j8 / 100;
            int i9 = (int) (j8 - (((j9 << 6) + (j9 << 5)) + (j9 << 2)));
            int i10 = i8 - 1;
            cArr[i10] = f6205n[i9];
            i8 = i10 - 1;
            cArr[i8] = f6204m[i9];
            j8 = j9;
        }
        int i11 = (int) j8;
        while (i11 >= 65536) {
            int i12 = i11 / 100;
            int i13 = i11 - (((i12 << 6) + (i12 << 5)) + (i12 << 2));
            int i14 = i8 - 1;
            cArr[i14] = f6205n[i13];
            i8 = i14 - 1;
            cArr[i8] = f6204m[i13];
            i11 = i12;
        }
        while (true) {
            int i15 = (52429 * i11) >>> 19;
            i8--;
            cArr[i8] = f6203l[i11 - ((i15 << 3) + (i15 << 1))];
            if (i15 == 0) {
                break;
            } else {
                i11 = i15;
            }
        }
        if (c8 != 0) {
            cArr[i8 - 1] = c8;
        }
    }

    public static String k(String str) {
        String property;
        try {
            property = System.getProperty(str);
        } catch (SecurityException unused) {
            property = null;
        }
        return property == null ? f6192a.getProperty(str) : property;
    }

    public static void l() {
        InputStream inputStream = (InputStream) AccessController.doPrivileged(new a());
        if (inputStream != null) {
            try {
                f6192a.load(inputStream);
                inputStream.close();
            } catch (IOException unused) {
            }
        }
    }

    public static int m(int i8) {
        int i9 = 0;
        while (i8 > f6206o[i9]) {
            i9++;
        }
        return i9 + 1;
    }

    public static int n(long j8) {
        long j9 = 10;
        for (int i8 = 1; i8 < 19; i8++) {
            if (j8 < j9) {
                return i8;
            }
            j9 *= 10;
        }
        return 19;
    }
}
