package com.google.zxing.aztec.decoder;

import com.bydux.api.common.tools.cmd.BydAdbHelper;
import com.bydux.api.common.tools.util.ListUtils;
import com.google.zxing.FormatException;
import com.google.zxing.common.b;
import com.google.zxing.common.d;
import com.google.zxing.common.reedsolomon.ReedSolomonException;
import com.google.zxing.common.reedsolomon.c;
import java.util.Arrays;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public final class Decoder {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String[] f4179a = {"CTRL_PS", " ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "CTRL_LL", "CTRL_ML", "CTRL_DL", "CTRL_BS"};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String[] f4180b = {"CTRL_PS", " ", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "CTRL_US", "CTRL_ML", "CTRL_DL", "CTRL_BS"};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String[] f4181c = {"CTRL_PS", " ", "\u0001", "\u0002", "\u0003", "\u0004", "\u0005", "\u0006", "\u0007", "\b", "\t", BydAdbHelper.COMMAND_LINE_END, "\u000b", "\f", "\r", "\u001b", "\u001c", "\u001d", "\u001e", "\u001f", "@", "\\", "^", "_", "`", "|", "~", "\u007f", "CTRL_LL", "CTRL_UL", "CTRL_PL", "CTRL_BS"};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String[] f4182d = {HttpUrl.FRAGMENT_ENCODE_SET, "\r", "\r\n", ". ", ", ", ": ", "!", "\"", "#", "$", "%", "&", "'", "(", ")", "*", "+", ListUtils.DEFAULT_JOIN_SEPARATOR, "-", ".", "/", ":", ";", "<", "=", ">", "?", "[", "]", "{", "}", "CTRL_UL"};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final String[] f4183e = {"CTRL_PS", " ", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ListUtils.DEFAULT_JOIN_SEPARATOR, ".", "CTRL_UL", "CTRL_US"};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private com.google.zxing.k.a f4184f;

    private enum Table {
        UPPER,
        LOWER,
        MIXED,
        DIGIT,
        PUNCT,
        BINARY
    }

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f4186a;

        static {
            int[] iArr = new int[Table.values().length];
            f4186a = iArr;
            try {
                iArr[Table.UPPER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f4186a[Table.LOWER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f4186a[Table.MIXED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f4186a[Table.PUNCT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f4186a[Table.DIGIT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    static byte[] a(boolean[] zArr) {
        int length = (zArr.length + 7) / 8;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[i] = h(zArr, i << 3);
        }
        return bArr;
    }

    private boolean[] b(boolean[] zArr) throws FormatException {
        com.google.zxing.common.reedsolomon.a aVar;
        int i = 8;
        if (this.f4184f.d() <= 2) {
            i = 6;
            aVar = com.google.zxing.common.reedsolomon.a.f4236c;
        } else if (this.f4184f.d() <= 8) {
            aVar = com.google.zxing.common.reedsolomon.a.f4240g;
        } else if (this.f4184f.d() <= 22) {
            i = 10;
            aVar = com.google.zxing.common.reedsolomon.a.f4235b;
        } else {
            i = 12;
            aVar = com.google.zxing.common.reedsolomon.a.f4234a;
        }
        int iC = this.f4184f.c();
        int length = zArr.length / i;
        if (length < iC) {
            throw FormatException.getFormatInstance();
        }
        int length2 = zArr.length % i;
        int[] iArr = new int[length];
        int i2 = 0;
        while (i2 < length) {
            iArr[i2] = i(zArr, length2, i);
            i2++;
            length2 += i;
        }
        try {
            new c(aVar).a(iArr, length - iC);
            int i3 = (1 << i) - 1;
            int i4 = 0;
            for (int i5 = 0; i5 < iC; i5++) {
                int i6 = iArr[i5];
                if (i6 == 0 || i6 == i3) {
                    throw FormatException.getFormatInstance();
                }
                if (i6 == 1 || i6 == i3 - 1) {
                    i4++;
                }
            }
            boolean[] zArr2 = new boolean[(iC * i) - i4];
            int i7 = 0;
            for (int i8 = 0; i8 < iC; i8++) {
                int i9 = iArr[i8];
                if (i9 == 1 || i9 == i3 - 1) {
                    Arrays.fill(zArr2, i7, (i7 + i) - 1, i9 > 1);
                    i7 += i - 1;
                } else {
                    int i10 = i - 1;
                    while (i10 >= 0) {
                        int i11 = i7 + 1;
                        zArr2[i7] = ((1 << i10) & i9) != 0;
                        i10--;
                        i7 = i11;
                    }
                }
            }
            return zArr2;
        } catch (ReedSolomonException e2) {
            throw FormatException.getFormatInstance(e2);
        }
    }

    private boolean[] d(b bVar) {
        boolean zE = this.f4184f.e();
        int iD = this.f4184f.d();
        int i = (zE ? 11 : 14) + (iD << 2);
        int[] iArr = new int[i];
        boolean[] zArr = new boolean[j(iD, zE)];
        int i2 = 2;
        if (zE) {
            for (int i3 = 0; i3 < i; i3++) {
                iArr[i3] = i3;
            }
        } else {
            int i4 = i / 2;
            int i5 = ((i + 1) + (((i4 - 1) / 15) * 2)) / 2;
            for (int i6 = 0; i6 < i4; i6++) {
                iArr[(i4 - i6) - 1] = (i5 - r12) - 1;
                iArr[i4 + i6] = (i6 / 15) + i6 + i5 + 1;
            }
        }
        int i7 = 0;
        int i8 = 0;
        while (i7 < iD) {
            int i9 = ((iD - i7) << i2) + (zE ? 9 : 12);
            int i10 = i7 << 1;
            int i11 = (i - 1) - i10;
            int i12 = 0;
            while (i12 < i9) {
                int i13 = i12 << 1;
                int i14 = 0;
                while (i14 < i2) {
                    int i15 = i10 + i14;
                    int i16 = i10 + i12;
                    zArr[i8 + i13 + i14] = bVar.d(iArr[i15], iArr[i16]);
                    int i17 = iArr[i16];
                    int i18 = i11 - i14;
                    zArr[(i9 * 2) + i8 + i13 + i14] = bVar.d(i17, iArr[i18]);
                    int i19 = i11 - i12;
                    zArr[(i9 * 4) + i8 + i13 + i14] = bVar.d(iArr[i18], iArr[i19]);
                    zArr[(i9 * 6) + i8 + i13 + i14] = bVar.d(iArr[i19], iArr[i15]);
                    i14++;
                    iD = iD;
                    zE = zE;
                    i2 = 2;
                }
                i12++;
                i2 = 2;
            }
            i8 += i9 << 3;
            i7++;
            i2 = 2;
        }
        return zArr;
    }

    private static String e(Table table, int i) {
        int i2 = a.f4186a[table.ordinal()];
        if (i2 == 1) {
            return f4179a[i];
        }
        if (i2 == 2) {
            return f4180b[i];
        }
        if (i2 == 3) {
            return f4181c[i];
        }
        if (i2 == 4) {
            return f4182d[i];
        }
        if (i2 == 5) {
            return f4183e[i];
        }
        throw new IllegalStateException("Bad table");
    }

    private static String f(boolean[] zArr) {
        int length = zArr.length;
        Table table = Table.UPPER;
        StringBuilder sb = new StringBuilder(20);
        Table tableG = table;
        int i = 0;
        while (i < length) {
            if (table != Table.BINARY) {
                int i2 = table == Table.DIGIT ? 4 : 5;
                if (length - i < i2) {
                    break;
                }
                int i3 = i(zArr, i, i2);
                i += i2;
                String strE = e(table, i3);
                if (strE.startsWith("CTRL_")) {
                    tableG = g(strE.charAt(5));
                    if (strE.charAt(6) != 'L') {
                        tableG = table;
                        table = tableG;
                    }
                } else {
                    sb.append(strE);
                }
                table = tableG;
            } else {
                if (length - i < 5) {
                    break;
                }
                int i4 = i(zArr, i, 5);
                i += 5;
                if (i4 == 0) {
                    if (length - i < 11) {
                        break;
                    }
                    i4 = i(zArr, i, 11) + 31;
                    i += 11;
                }
                int i5 = 0;
                while (true) {
                    if (i5 >= i4) {
                        break;
                    }
                    if (length - i < 8) {
                        i = length;
                        break;
                    }
                    sb.append((char) i(zArr, i, 8));
                    i += 8;
                    i5++;
                }
                table = tableG;
            }
        }
        return sb.toString();
    }

    private static Table g(char c2) {
        return c2 != 'B' ? c2 != 'D' ? c2 != 'P' ? c2 != 'L' ? c2 != 'M' ? Table.UPPER : Table.MIXED : Table.LOWER : Table.PUNCT : Table.DIGIT : Table.BINARY;
    }

    private static byte h(boolean[] zArr, int i) {
        int length = zArr.length - i;
        return (byte) (length >= 8 ? i(zArr, i, 8) : i(zArr, i, length) << (8 - length));
    }

    private static int i(boolean[] zArr, int i, int i2) {
        int i3 = 0;
        for (int i4 = i; i4 < i + i2; i4++) {
            i3 <<= 1;
            if (zArr[i4]) {
                i3 |= 1;
            }
        }
        return i3;
    }

    private static int j(int i, boolean z) {
        return ((z ? 88 : 112) + (i << 4)) * i;
    }

    public d c(com.google.zxing.k.a aVar) throws FormatException {
        this.f4184f = aVar;
        boolean[] zArrB = b(d(aVar.a()));
        d dVar = new d(a(zArrB), f(zArrB), null, null);
        dVar.l(zArrB.length);
        return dVar;
    }
}
