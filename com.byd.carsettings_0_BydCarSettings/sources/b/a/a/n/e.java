package b.a.a.n;

import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.hardware.bydauto.statistic.BYDAutoStatisticDevice;
import androidx.appcompat.widget.ActivityChooserView;
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
    public static final Properties f436a = new Properties();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Charset f437b = Charset.forName("UTF-8");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final char[] f438c = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final boolean[] f439d = new boolean[256];

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final boolean[] f440e = new boolean[256];

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final byte[] f441f;
    public static final byte[] g;
    public static final boolean[] h;
    public static final boolean[] i;
    public static final char[] j;
    public static final char[] k;
    public static final char[] l;
    public static final char[] m;
    public static final char[] n;
    public static final int[] o;
    public static final char[] p;
    public static final int[] q;

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
        char c2 = 0;
        while (true) {
            boolean[] zArr = f439d;
            if (c2 >= zArr.length) {
                break;
            }
            if (c2 >= 'A' && c2 <= 'Z') {
                zArr[c2] = true;
            } else if (c2 >= 'a' && c2 <= 'z') {
                zArr[c2] = true;
            } else if (c2 == '_' || c2 == '$') {
                zArr[c2] = true;
            }
            c2 = (char) (c2 + 1);
        }
        char c3 = 0;
        while (true) {
            boolean[] zArr2 = f440e;
            if (c3 < zArr2.length) {
                if (c3 >= 'A' && c3 <= 'Z') {
                    zArr2[c3] = true;
                } else if (c3 >= 'a' && c3 <= 'z') {
                    zArr2[c3] = true;
                } else if (c3 == '_') {
                    zArr2[c3] = true;
                } else if (c3 >= '0' && c3 <= '9') {
                    zArr2[c3] = true;
                }
                c3 = (char) (c3 + 1);
            } else {
                try {
                    break;
                } catch (Throwable unused) {
                }
            }
        }
        l();
        byte[] bArr = new byte[161];
        f441f = bArr;
        byte[] bArr2 = new byte[161];
        g = bArr2;
        h = new boolean[161];
        i = new boolean[161];
        j = new char[93];
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
        for (int i2 = 14; i2 <= 31; i2++) {
            f441f[i2] = 4;
            g[i2] = 4;
        }
        for (int i3 = 127; i3 < 160; i3++) {
            f441f[i3] = 4;
            g[i3] = 4;
        }
        for (int i4 = 0; i4 < 161; i4++) {
            h[i4] = f441f[i4] != 0;
            i[i4] = g[i4] != 0;
        }
        char[] cArr = j;
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
        k = new char[]{'0', '0', '0', '1', '0', '2', '0', '3', '0', '4', '0', '5', '0', '6', '0', '7', '0', '8', '0', '9', '0', 'A', '0', 'B', '0', 'C', '0', 'D', '0', 'E', '0', 'F', '1', '0', '1', '1', '1', '2', '1', '3', '1', '4', '1', '5', '1', '6', '1', '7', '1', '8', '1', '9', '1', 'A', '1', 'B', '1', 'C', '1', 'D', '1', 'E', '1', 'F', '2', '0', '2', '1', '2', '2', '2', '3', '2', '4', '2', '5', '2', '6', '2', '7', '2', '8', '2', '9', '2', 'A', '2', 'B', '2', 'C', '2', 'D', '2', 'E', '2', 'F'};
        l = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        m = new char[]{'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '4', '4', '4', '4', '4', '4', '4', '4', '4', '4', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '6', '6', '6', '6', '6', '6', '6', '6', '6', '6', '7', '7', '7', '7', '7', '7', '7', '7', '7', '7', '8', '8', '8', '8', '8', '8', '8', '8', '8', '8', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9'};
        n = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        o = new int[]{9, 99, 999, 9999, BYDAutoStatisticDevice.MILEAGE_MAX, BYDAutoStatisticDevice.STATISTIC_MILEAGE_MAX, 9999999, BYDAutoInstrumentDevice.MILE_PH_MAX, 999999999, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED};
        char[] charArray = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
        p = charArray;
        int[] iArr = new int[256];
        q = iArr;
        Arrays.fill(iArr, -1);
        int length = charArray.length;
        for (int i5 = 0; i5 < length; i5++) {
            q[p[i5]] = i5;
        }
        q[61] = 0;
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
        } catch (CharacterCodingException e2) {
            throw new JSONException("utf8 decode error, " + e2.getMessage(), e2);
        }
    }

    public static byte[] c(String str) {
        int i2;
        int length = str.length();
        int i3 = 0;
        if (length == 0) {
            return new byte[0];
        }
        int i4 = length - 1;
        int i5 = 0;
        while (i5 < i4 && q[str.charAt(i5) & 255] < 0) {
            i5++;
        }
        while (i4 > 0 && q[str.charAt(i4) & 255] < 0) {
            i4--;
        }
        int i6 = str.charAt(i4) == '=' ? str.charAt(i4 + (-1)) == '=' ? 2 : 1 : 0;
        int i7 = (i4 - i5) + 1;
        if (length > 76) {
            i2 = (str.charAt(76) == '\r' ? i7 / 78 : 0) << 1;
        } else {
            i2 = 0;
        }
        int i8 = (((i7 - i2) * 6) >> 3) - i6;
        byte[] bArr = new byte[i8];
        int i9 = (i8 / 3) * 3;
        int i10 = 0;
        int i11 = 0;
        while (i10 < i9) {
            int[] iArr = q;
            int i12 = i5 + 1;
            int i13 = i12 + 1;
            int i14 = (iArr[str.charAt(i5)] << 18) | (iArr[str.charAt(i12)] << 12);
            int i15 = i13 + 1;
            int i16 = i14 | (iArr[str.charAt(i13)] << 6);
            int i17 = i15 + 1;
            int i18 = i16 | iArr[str.charAt(i15)];
            int i19 = i10 + 1;
            bArr[i10] = (byte) (i18 >> 16);
            int i20 = i19 + 1;
            bArr[i19] = (byte) (i18 >> 8);
            int i21 = i20 + 1;
            bArr[i20] = (byte) i18;
            if (i2 > 0 && (i11 = i11 + 1) == 19) {
                i17 += 2;
                i11 = 0;
            }
            i5 = i17;
            i10 = i21;
        }
        if (i10 < i8) {
            int i22 = 0;
            while (i5 <= i4 - i6) {
                i3 |= q[str.charAt(i5)] << (18 - (i22 * 6));
                i22++;
                i5++;
            }
            int i23 = 16;
            while (i10 < i8) {
                bArr[i10] = (byte) (i3 >> i23);
                i23 -= 8;
                i10++;
            }
        }
        return bArr;
    }

    public static byte[] d(String str, int i2, int i3) {
        int i4;
        int i5 = 0;
        if (i3 == 0) {
            return new byte[0];
        }
        int i6 = (i2 + i3) - 1;
        while (i2 < i6 && q[str.charAt(i2)] < 0) {
            i2++;
        }
        while (i6 > 0 && q[str.charAt(i6)] < 0) {
            i6--;
        }
        int i7 = str.charAt(i6) == '=' ? str.charAt(i6 + (-1)) == '=' ? 2 : 1 : 0;
        int i8 = (i6 - i2) + 1;
        if (i3 > 76) {
            i4 = (str.charAt(76) == '\r' ? i8 / 78 : 0) << 1;
        } else {
            i4 = 0;
        }
        int i9 = (((i8 - i4) * 6) >> 3) - i7;
        byte[] bArr = new byte[i9];
        int i10 = (i9 / 3) * 3;
        int i11 = 0;
        int i12 = 0;
        while (i11 < i10) {
            int[] iArr = q;
            int i13 = i2 + 1;
            int i14 = i13 + 1;
            int i15 = (iArr[str.charAt(i2)] << 18) | (iArr[str.charAt(i13)] << 12);
            int i16 = i14 + 1;
            int i17 = i15 | (iArr[str.charAt(i14)] << 6);
            int i18 = i16 + 1;
            int i19 = i17 | iArr[str.charAt(i16)];
            int i20 = i11 + 1;
            bArr[i11] = (byte) (i19 >> 16);
            int i21 = i20 + 1;
            bArr[i20] = (byte) (i19 >> 8);
            int i22 = i21 + 1;
            bArr[i21] = (byte) i19;
            if (i4 > 0 && (i12 = i12 + 1) == 19) {
                i18 += 2;
                i12 = 0;
            }
            i2 = i18;
            i11 = i22;
        }
        if (i11 < i9) {
            int i23 = 0;
            while (i2 <= i6 - i7) {
                i5 |= q[str.charAt(i2)] << (18 - (i23 * 6));
                i23++;
                i2++;
            }
            int i24 = 16;
            while (i11 < i9) {
                bArr[i11] = (byte) (i5 >> i24);
                i24 -= 8;
                i11++;
            }
        }
        return bArr;
    }

    public static byte[] e(char[] cArr, int i2, int i3) {
        int i4;
        int i5 = 0;
        if (i3 == 0) {
            return new byte[0];
        }
        int i6 = (i2 + i3) - 1;
        while (i2 < i6 && q[cArr[i2]] < 0) {
            i2++;
        }
        while (i6 > 0 && q[cArr[i6]] < 0) {
            i6--;
        }
        int i7 = cArr[i6] == '=' ? cArr[i6 + (-1)] == '=' ? 2 : 1 : 0;
        int i8 = (i6 - i2) + 1;
        if (i3 > 76) {
            i4 = (cArr[76] == '\r' ? i8 / 78 : 0) << 1;
        } else {
            i4 = 0;
        }
        int i9 = (((i8 - i4) * 6) >> 3) - i7;
        byte[] bArr = new byte[i9];
        int i10 = (i9 / 3) * 3;
        int i11 = 0;
        int i12 = 0;
        while (i11 < i10) {
            int[] iArr = q;
            int i13 = i2 + 1;
            int i14 = i13 + 1;
            int i15 = (iArr[cArr[i2]] << 18) | (iArr[cArr[i13]] << 12);
            int i16 = i14 + 1;
            int i17 = i15 | (iArr[cArr[i14]] << 6);
            int i18 = i16 + 1;
            int i19 = i17 | iArr[cArr[i16]];
            int i20 = i11 + 1;
            bArr[i11] = (byte) (i19 >> 16);
            int i21 = i20 + 1;
            bArr[i20] = (byte) (i19 >> 8);
            int i22 = i21 + 1;
            bArr[i21] = (byte) i19;
            if (i4 > 0 && (i12 = i12 + 1) == 19) {
                i18 += 2;
                i12 = 0;
            }
            i2 = i18;
            i11 = i22;
        }
        if (i11 < i9) {
            int i23 = 0;
            while (i2 <= i6 - i7) {
                i5 |= q[cArr[i2]] << (18 - (i23 * 6));
                i23++;
                i2++;
            }
            int i24 = 16;
            while (i11 < i9) {
                bArr[i11] = (byte) (i5 >> i24);
                i24 -= 8;
                i11++;
            }
        }
        return bArr;
    }

    public static int f(byte[] bArr, int i2, int i3, char[] cArr) {
        int i4 = i2 + i3;
        int iMin = Math.min(i3, cArr.length);
        int i5 = 0;
        while (i5 < iMin && bArr[i2] >= 0) {
            cArr[i5] = (char) bArr[i2];
            i5++;
            i2++;
        }
        while (i2 < i4) {
            int i6 = i2 + 1;
            byte b2 = bArr[i2];
            if (b2 >= 0) {
                cArr[i5] = (char) b2;
                i2 = i6;
                i5++;
            } else {
                if ((b2 >> 5) != -2 || (b2 & 30) == 0) {
                    if ((b2 >> 4) == -2) {
                        int i7 = i6 + 1;
                        if (i7 < i4) {
                            byte b3 = bArr[i6];
                            int i8 = i7 + 1;
                            byte b4 = bArr[i7];
                            if ((b2 != -32 || (b3 & 224) != 128) && (b3 & 192) == 128 && (b4 & 192) == 128) {
                                char c2 = (char) (((b2 << 12) ^ (b3 << 6)) ^ ((-123008) ^ b4));
                                if (c2 >= 55296 && c2 < 57344) {
                                    return -1;
                                }
                                cArr[i5] = c2;
                                i5++;
                                i2 = i8;
                            }
                        }
                        return -1;
                    }
                    if ((b2 >> 3) == -2 && i6 + 2 < i4) {
                        int i9 = i6 + 1;
                        byte b5 = bArr[i6];
                        int i10 = i9 + 1;
                        byte b6 = bArr[i9];
                        int i11 = i10 + 1;
                        byte b7 = bArr[i10];
                        int i12 = (((b2 << 18) ^ (b5 << 12)) ^ (b6 << 6)) ^ (3678080 ^ b7);
                        if ((b5 & 192) == 128 && (b6 & 192) == 128 && (b7 & 192) == 128 && i12 >= 65536 && i12 < 1114112) {
                            int i13 = i5 + 1;
                            cArr[i5] = (char) ((i12 >>> 10) + 55232);
                            i5 = i13 + 1;
                            cArr[i13] = (char) ((i12 & 1023) + 56320);
                            i2 = i11;
                        }
                    }
                    return -1;
                }
                if (i6 >= i4) {
                    return -1;
                }
                int i14 = i6 + 1;
                byte b8 = bArr[i6];
                if ((b8 & 192) != 128) {
                    return -1;
                }
                cArr[i5] = (char) (((b2 << 6) ^ b8) ^ 3968);
                i2 = i14;
                i5++;
            }
        }
        return i5;
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
        throw new UnsupportedOperationException("Method not decompiled: b.a.a.n.e.g(char[], int, int, byte[]):int");
    }

    public static boolean h(char c2) {
        boolean[] zArr = f439d;
        return c2 < zArr.length && zArr[c2];
    }

    public static void i(int i2, int i3, char[] cArr) {
        char c2;
        if (i2 < 0) {
            c2 = '-';
            i2 = -i2;
        } else {
            c2 = 0;
        }
        while (i2 >= 65536) {
            int i4 = i2 / 100;
            int i5 = i2 - (((i4 << 6) + (i4 << 5)) + (i4 << 2));
            int i6 = i3 - 1;
            cArr[i6] = n[i5];
            i3 = i6 - 1;
            cArr[i3] = m[i5];
            i2 = i4;
        }
        while (true) {
            int i7 = (52429 * i2) >>> 19;
            i3--;
            cArr[i3] = l[i2 - ((i7 << 3) + (i7 << 1))];
            if (i7 == 0) {
                break;
            } else {
                i2 = i7;
            }
        }
        if (c2 != 0) {
            cArr[i3 - 1] = c2;
        }
    }

    public static void j(long j2, int i2, char[] cArr) {
        char c2;
        if (j2 < 0) {
            c2 = '-';
            j2 = -j2;
        } else {
            c2 = 0;
        }
        while (j2 > 2147483647L) {
            long j3 = j2 / 100;
            int i3 = (int) (j2 - (((j3 << 6) + (j3 << 5)) + (j3 << 2)));
            int i4 = i2 - 1;
            cArr[i4] = n[i3];
            i2 = i4 - 1;
            cArr[i2] = m[i3];
            j2 = j3;
        }
        int i5 = (int) j2;
        while (i5 >= 65536) {
            int i6 = i5 / 100;
            int i7 = i5 - (((i6 << 6) + (i6 << 5)) + (i6 << 2));
            int i8 = i2 - 1;
            cArr[i8] = n[i7];
            i2 = i8 - 1;
            cArr[i2] = m[i7];
            i5 = i6;
        }
        while (true) {
            int i9 = (52429 * i5) >>> 19;
            i2--;
            cArr[i2] = l[i5 - ((i9 << 3) + (i9 << 1))];
            if (i9 == 0) {
                break;
            } else {
                i5 = i9;
            }
        }
        if (c2 != 0) {
            cArr[i2 - 1] = c2;
        }
    }

    public static String k(String str) {
        String property;
        try {
            property = System.getProperty(str);
        } catch (SecurityException unused) {
            property = null;
        }
        return property == null ? f436a.getProperty(str) : property;
    }

    public static void l() {
        InputStream inputStream = (InputStream) AccessController.doPrivileged(new a());
        if (inputStream != null) {
            try {
                f436a.load(inputStream);
                inputStream.close();
            } catch (IOException unused) {
            }
        }
    }

    public static int m(int i2) {
        int i3 = 0;
        while (i2 > o[i3]) {
            i3++;
        }
        return i3 + 1;
    }

    public static int n(long j2) {
        long j3 = 10;
        for (int i2 = 1; i2 < 19; i2++) {
            if (j2 < j3) {
                return i2;
            }
            j3 *= 10;
        }
        return 19;
    }
}
