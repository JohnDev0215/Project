package w5;

import C1.j;
import F1.C0254m;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import t5.AbstractC2439d;
import t5.C2436a;
import v5.C2552b;
import v5.EnumC2551a;
import x.AbstractC2641j;

/* JADX INFO: renamed from: w5.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2600b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f23327a = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, 37, 38, -1, -1, -1, -1, 39, 40, -1, 41, 42, 43, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 44, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, -1, -1, -1, -1, -1};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Charset f23328b = StandardCharsets.ISO_8859_1;

    public static void a(String str, EnumC2551a enumC2551a, C2436a c2436a, Charset charset) throws I7.a {
        int i7;
        int iOrdinal = enumC2551a.ordinal();
        int i10 = 0;
        if (iOrdinal == 1) {
            int length = str.length();
            while (i10 < length) {
                int iCharAt = str.charAt(i10) - '0';
                int i11 = i10 + 2;
                if (i11 < length) {
                    c2436a.b(((str.charAt(i10 + 1) - '0') * 10) + (iCharAt * 100) + (str.charAt(i11) - '0'), 10);
                    i10 += 3;
                } else {
                    i10++;
                    if (i10 < length) {
                        c2436a.b((iCharAt * 10) + (str.charAt(i10) - '0'), 7);
                        i10 = i11;
                    } else {
                        c2436a.b(iCharAt, 4);
                    }
                }
            }
            return;
        }
        if (iOrdinal == 2) {
            int length2 = str.length();
            while (i10 < length2) {
                char cCharAt = str.charAt(i10);
                int[] iArr = f23327a;
                int i12 = cCharAt < '`' ? iArr[cCharAt] : -1;
                if (i12 == -1) {
                    throw new I7.a();
                }
                int i13 = i10 + 1;
                if (i13 < length2) {
                    char cCharAt2 = str.charAt(i13);
                    int i14 = cCharAt2 < '`' ? iArr[cCharAt2] : -1;
                    if (i14 == -1) {
                        throw new I7.a();
                    }
                    c2436a.b((i12 * 45) + i14, 11);
                    i10 += 2;
                } else {
                    c2436a.b(i12, 6);
                    i10 = i13;
                }
            }
            return;
        }
        if (iOrdinal == 4) {
            byte[] bytes = str.getBytes(charset);
            int length3 = bytes.length;
            while (i10 < length3) {
                c2436a.b(bytes[i10], 8);
                i10++;
            }
            return;
        }
        if (iOrdinal != 6) {
            throw new I7.a("Invalid mode: " + enumC2551a);
        }
        byte[] bytes2 = str.getBytes(AbstractC2439d.f22472b);
        if (bytes2.length % 2 != 0) {
            throw new I7.a("Kanji byte size not even");
        }
        int length4 = bytes2.length - 1;
        while (i10 < length4) {
            int i15 = ((bytes2[i10] & 255) << 8) | (bytes2[i10 + 1] & 255);
            int i16 = 33088;
            if (i15 >= 33088 && i15 <= 40956) {
                i7 = i15 - i16;
            } else if (i15 < 57408 || i15 > 60351) {
                i7 = -1;
            } else {
                i16 = 49472;
                i7 = i15 - i16;
            }
            if (i7 == -1) {
                throw new I7.a("Invalid byte sequence");
            }
            c2436a.b(((i7 >> 8) * 192) + (i7 & 255), 13);
            i10 += 2;
        }
    }

    public static boolean b(String str) {
        byte[] bytes = str.getBytes(AbstractC2439d.f22472b);
        int length = bytes.length;
        if (length % 2 != 0) {
            return false;
        }
        for (int i7 = 0; i7 < length; i7 += 2) {
            int i10 = bytes[i7] & 255;
            if ((i10 < 129 || i10 > 159) && (i10 < 224 || i10 > 235)) {
                return false;
            }
        }
        return true;
    }

    public static boolean c(int i7, C2552b c2552b, int i10) {
        int i11 = c2552b.d;
        j jVar = c2552b.c[AbstractC2641j.c(i10)];
        int i12 = 0;
        for (C0254m c0254m : (C0254m[]) jVar.f944m) {
            i12 += c0254m.f2343b;
        }
        return i11 - (i12 * jVar.l) >= (i7 + 7) / 8;
    }
}
