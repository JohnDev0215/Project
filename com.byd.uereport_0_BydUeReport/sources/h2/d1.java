package h2;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import ts.car.service.carplay.data.config.CarPlayConfig;

/* JADX INFO: compiled from: SerializeWriter.java */
/* JADX INFO: loaded from: classes.dex */
public final class d1 extends Writer {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final ThreadLocal<char[]> f5207d = new ThreadLocal<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final ThreadLocal<byte[]> f5208e = new ThreadLocal<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final char[] f5209f = ":true".toCharArray();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final char[] f5210g = ":false".toCharArray();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static int f5211h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final int f5212i;
    public long A;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public char[] f5213j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f5214k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f5215l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Writer f5216m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f5217n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f5218o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f5219p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f5220q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f5221r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f5222s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f5223t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f5224u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f5225v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f5226w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public char f5227x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f5228y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f5229z;

    static {
        int i8;
        f5211h = CarPlayConfig.IapFeature.IAP_FEATURE_APP_LAUNCH;
        try {
            String strK = l2.e.k("fastjson.serializer_buffer_threshold");
            if (strK != null && strK.length() > 0 && (i8 = Integer.parseInt(strK)) >= 64 && i8 <= 65536) {
                f5211h = i8 * 1024;
            }
        } catch (Throwable unused) {
        }
        f5212i = SerializerFeature.UseSingleQuotes.mask | 0 | SerializerFeature.BrowserCompatible.mask | SerializerFeature.PrettyFormat.mask | SerializerFeature.WriteEnumUsingToString.mask | SerializerFeature.WriteNonStringValueAsString.mask | SerializerFeature.WriteSlashAsSpecial.mask | SerializerFeature.IgnoreErrorGetter.mask | SerializerFeature.WriteClassName.mask | SerializerFeature.NotWriteDefaultValue.mask;
    }

    public d1() {
        this(null);
    }

    public void B(double d8, boolean z7) {
        if (Double.isNaN(d8) || Double.isInfinite(d8)) {
            Z();
            return;
        }
        int i8 = this.f5214k + 24;
        if (i8 > this.f5213j.length) {
            if (this.f5216m != null) {
                String strB = l2.i.b(d8);
                write(strB, 0, strB.length());
                if (z7 && t(SerializerFeature.WriteClassName)) {
                    write(68);
                    return;
                }
                return;
            }
            q(i8);
        }
        this.f5214k += l2.i.a(d8, this.f5213j, this.f5214k);
        if (z7 && t(SerializerFeature.WriteClassName)) {
            write(68);
        }
    }

    public void C(Enum<?> r32) {
        if (r32 == null) {
            Z();
            return;
        }
        String string = null;
        if (this.f5224u && !this.f5225v) {
            string = r32.name();
        } else if (this.f5225v) {
            string = r32.toString();
        }
        if (string == null) {
            V(r32.ordinal());
            return;
        }
        int i8 = t(SerializerFeature.UseSingleQuotes) ? 39 : 34;
        write(i8);
        write(string);
        write(i8);
    }

    public void E(String str) {
        F(str, false);
    }

    public void F(String str, boolean z7) {
        if (str == null) {
            write("null:");
            return;
        }
        if (this.f5217n) {
            if (!this.f5218o) {
                X(str);
                return;
            } else {
                h0(str);
                write(58);
                return;
            }
        }
        if (this.f5218o) {
            g0(str, ':');
            return;
        }
        boolean z8 = true;
        boolean z9 = str.length() == 0;
        int i8 = 0;
        while (true) {
            if (i8 >= str.length()) {
                z8 = z9;
                break;
            }
            char cCharAt = str.charAt(i8);
            if ((cCharAt < '@' && (this.A & (1 << cCharAt)) != 0) || cCharAt == '\\') {
                break;
            } else {
                i8++;
            }
        }
        if (z8) {
            g0(str, ':');
        } else {
            write(str);
            write(58);
        }
    }

    public void I(char c8, String str, double d8) {
        write(c8);
        E(str);
        B(d8, false);
    }

    public void L(char c8, String str, int i8) {
        if (i8 == Integer.MIN_VALUE || !this.f5218o) {
            write(c8);
            E(str);
            V(i8);
            return;
        }
        int iM = i8 < 0 ? l2.e.m(-i8) + 1 : l2.e.m(i8);
        int length = str.length();
        int i9 = this.f5214k + length + 4 + iM;
        if (i9 > this.f5213j.length) {
            if (this.f5216m != null) {
                write(c8);
                E(str);
                V(i8);
                return;
            }
            q(i9);
        }
        int i10 = this.f5214k;
        this.f5214k = i9;
        char[] cArr = this.f5213j;
        cArr[i10] = c8;
        int i11 = i10 + length + 1;
        cArr[i10 + 1] = this.f5227x;
        str.getChars(0, length, cArr, i10 + 2);
        char[] cArr2 = this.f5213j;
        cArr2[i11 + 1] = this.f5227x;
        cArr2[i11 + 2] = ':';
        l2.e.i(i8, this.f5214k, cArr2);
    }

    public void M(char c8, String str, long j8) {
        if (j8 == Long.MIN_VALUE || !this.f5218o || r(SerializerFeature.BrowserCompatible.mask)) {
            write(c8);
            E(str);
            Y(j8);
            return;
        }
        int iN = j8 < 0 ? l2.e.n(-j8) + 1 : l2.e.n(j8);
        int length = str.length();
        int i8 = this.f5214k + length + 4 + iN;
        if (i8 > this.f5213j.length) {
            if (this.f5216m != null) {
                write(c8);
                E(str);
                Y(j8);
                return;
            }
            q(i8);
        }
        int i9 = this.f5214k;
        this.f5214k = i8;
        char[] cArr = this.f5213j;
        cArr[i9] = c8;
        int i10 = i9 + length + 1;
        cArr[i9 + 1] = this.f5227x;
        str.getChars(0, length, cArr, i9 + 2);
        char[] cArr2 = this.f5213j;
        cArr2[i10 + 1] = this.f5227x;
        cArr2[i10 + 2] = ':';
        l2.e.j(j8, this.f5214k, cArr2);
    }

    public void N(char c8, String str, String str2) {
        if (!this.f5218o) {
            write(c8);
            E(str);
            if (str2 == null) {
                Z();
                return;
            } else {
                d0(str2);
                return;
            }
        }
        if (this.f5217n) {
            write(c8);
            E(str);
            if (str2 == null) {
                Z();
                return;
            } else {
                d0(str2);
                return;
            }
        }
        if (!t(SerializerFeature.BrowserCompatible)) {
            P(c8, str, str2);
            return;
        }
        write(c8);
        g0(str, ':');
        g0(str2, (char) 0);
    }

    public void O(char c8, String str, BigDecimal bigDecimal) {
        write(c8);
        E(str);
        if (bigDecimal == null) {
            Z();
        } else {
            int iScale = bigDecimal.scale();
            write((!t(SerializerFeature.WriteBigDecimalAsPlain) || iScale < -100 || iScale >= 100) ? bigDecimal.toString() : bigDecimal.toPlainString());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x009c A[PHI: r1 r4 r10 r12
  0x009c: PHI (r1v40 int) = (r1v29 int), (r1v3 int) binds: [B:55:0x00d7, B:30:0x0098] A[DONT_GENERATE, DONT_INLINE]
  0x009c: PHI (r4v22 int) = (r4v20 int), (r4v24 int) binds: [B:55:0x00d7, B:30:0x0098] A[DONT_GENERATE, DONT_INLINE]
  0x009c: PHI (r10v16 int) = (r10v14 int), (r10v18 int) binds: [B:55:0x00d7, B:30:0x0098] A[DONT_GENERATE, DONT_INLINE]
  0x009c: PHI (r12v21 int) = (r12v3 int), (r12v23 int) binds: [B:55:0x00d7, B:30:0x0098] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00d9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void P(char r21, java.lang.String r22, java.lang.String r23) {
        /*
            Method dump skipped, instruction units count: 776
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: h2.d1.P(char, java.lang.String, java.lang.String):void");
    }

    public void R(float f8, boolean z7) {
        if (f8 != f8 || f8 == Float.POSITIVE_INFINITY || f8 == Float.NEGATIVE_INFINITY) {
            Z();
            return;
        }
        int i8 = this.f5214k + 15;
        if (i8 > this.f5213j.length) {
            if (this.f5216m != null) {
                String strB = l2.j.b(f8);
                write(strB, 0, strB.length());
                if (z7 && t(SerializerFeature.WriteClassName)) {
                    write(70);
                    return;
                }
                return;
            }
            q(i8);
        }
        this.f5214k += l2.j.a(f8, this.f5213j, this.f5214k);
        if (z7 && t(SerializerFeature.WriteClassName)) {
            write(70);
        }
    }

    public void T(byte[] bArr) {
        int length = this.f5214k + (bArr.length * 2) + 3;
        if (length > this.f5213j.length) {
            q(length);
        }
        char[] cArr = this.f5213j;
        int i8 = this.f5214k;
        int i9 = i8 + 1;
        this.f5214k = i9;
        cArr[i8] = 'x';
        this.f5214k = i9 + 1;
        cArr[i9] = '\'';
        for (byte b8 : bArr) {
            int i10 = b8 & 255;
            int i11 = i10 >> 4;
            int i12 = i10 & 15;
            char[] cArr2 = this.f5213j;
            int i13 = this.f5214k;
            int i14 = i13 + 1;
            this.f5214k = i14;
            int i15 = 48;
            cArr2[i13] = (char) (i11 + (i11 < 10 ? 48 : 55));
            this.f5214k = i14 + 1;
            if (i12 >= 10) {
                i15 = 55;
            }
            cArr2[i14] = (char) (i12 + i15);
        }
        char[] cArr3 = this.f5213j;
        int i16 = this.f5214k;
        this.f5214k = i16 + 1;
        cArr3[i16] = '\'';
    }

    public void V(int i8) {
        if (i8 == Integer.MIN_VALUE) {
            write("-2147483648");
            return;
        }
        int iM = i8 < 0 ? l2.e.m(-i8) + 1 : l2.e.m(i8);
        int i9 = this.f5214k + iM;
        if (i9 > this.f5213j.length) {
            if (this.f5216m != null) {
                char[] cArr = new char[iM];
                l2.e.i(i8, iM, cArr);
                write(cArr, 0, iM);
                return;
            }
            q(i9);
        }
        l2.e.i(i8, i9, this.f5213j);
        this.f5214k = i9;
    }

    public final void X(String str) {
        byte[] bArr = l2.e.f6198g;
        int length = str.length();
        boolean z7 = true;
        int i8 = this.f5214k + length + 1;
        int i9 = 0;
        if (i8 > this.f5213j.length) {
            if (this.f5216m != null) {
                if (length == 0) {
                    write(39);
                    write(39);
                    write(58);
                    return;
                }
                int i10 = 0;
                while (true) {
                    if (i10 < length) {
                        char cCharAt = str.charAt(i10);
                        if (cCharAt < bArr.length && bArr[cCharAt] != 0) {
                            break;
                        } else {
                            i10++;
                        }
                    } else {
                        z7 = false;
                        break;
                    }
                }
                if (z7) {
                    write(39);
                }
                while (i9 < length) {
                    char cCharAt2 = str.charAt(i9);
                    if (cCharAt2 >= bArr.length || bArr[cCharAt2] == 0) {
                        write(cCharAt2);
                    } else {
                        write(92);
                        write(l2.e.f6201j[cCharAt2]);
                    }
                    i9++;
                }
                if (z7) {
                    write(39);
                }
                write(58);
                return;
            }
            q(i8);
        }
        if (length == 0) {
            int i11 = this.f5214k;
            if (i11 + 3 > this.f5213j.length) {
                q(i11 + 3);
            }
            char[] cArr = this.f5213j;
            int i12 = this.f5214k;
            int i13 = i12 + 1;
            this.f5214k = i13;
            cArr[i12] = '\'';
            int i14 = i13 + 1;
            this.f5214k = i14;
            cArr[i13] = '\'';
            this.f5214k = i14 + 1;
            cArr[i14] = ':';
            return;
        }
        int i15 = this.f5214k;
        int i16 = i15 + length;
        str.getChars(0, length, this.f5213j, i15);
        this.f5214k = i8;
        int i17 = i15;
        boolean z8 = false;
        while (i17 < i16) {
            char[] cArr2 = this.f5213j;
            char c8 = cArr2[i17];
            if (c8 < bArr.length && bArr[c8] != 0) {
                if (z8) {
                    i8++;
                    if (i8 > cArr2.length) {
                        q(i8);
                    }
                    this.f5214k = i8;
                    char[] cArr3 = this.f5213j;
                    int i18 = i17 + 1;
                    System.arraycopy(cArr3, i18, cArr3, i17 + 2, i16 - i17);
                    char[] cArr4 = this.f5213j;
                    cArr4[i17] = '\\';
                    cArr4[i18] = l2.e.f6201j[c8];
                    i16++;
                    i17 = i18;
                } else {
                    i8 += 3;
                    if (i8 > cArr2.length) {
                        q(i8);
                    }
                    this.f5214k = i8;
                    char[] cArr5 = this.f5213j;
                    int i19 = i17 + 1;
                    System.arraycopy(cArr5, i19, cArr5, i17 + 3, (i16 - i17) - 1);
                    char[] cArr6 = this.f5213j;
                    System.arraycopy(cArr6, i9, cArr6, 1, i17);
                    char[] cArr7 = this.f5213j;
                    cArr7[i15] = '\'';
                    cArr7[i19] = '\\';
                    int i20 = i19 + 1;
                    cArr7[i20] = l2.e.f6201j[c8];
                    i16 += 2;
                    cArr7[this.f5214k - 2] = '\'';
                    i17 = i20;
                    z8 = true;
                }
            }
            i17++;
            i9 = 0;
        }
        this.f5213j[i8 - 1] = ':';
    }

    public void Y(long j8) {
        boolean z7 = t(SerializerFeature.BrowserCompatible) && !t(SerializerFeature.WriteClassName) && (j8 > 9007199254740991L || j8 < -9007199254740991L);
        if (j8 == Long.MIN_VALUE) {
            if (z7) {
                write("\"-9223372036854775808\"");
                return;
            } else {
                write("-9223372036854775808");
                return;
            }
        }
        int iN = j8 < 0 ? l2.e.n(-j8) + 1 : l2.e.n(j8);
        int i8 = this.f5214k + iN;
        if (z7) {
            i8 += 2;
        }
        if (i8 > this.f5213j.length) {
            if (this.f5216m != null) {
                char[] cArr = new char[iN];
                l2.e.j(j8, iN, cArr);
                if (!z7) {
                    write(cArr, 0, iN);
                    return;
                }
                write(34);
                write(cArr, 0, iN);
                write(34);
                return;
            }
            q(i8);
        }
        if (z7) {
            char[] cArr2 = this.f5213j;
            cArr2[this.f5214k] = '\"';
            int i9 = i8 - 1;
            l2.e.j(j8, i9, cArr2);
            this.f5213j[i9] = '\"';
        } else {
            l2.e.j(j8, i8, this.f5213j);
        }
        this.f5214k = i8;
    }

    public void Z() {
        write("null");
    }

    public void a0(int i8, int i9) {
        if ((i8 & i9) == 0 && (this.f5215l & i9) == 0) {
            Z();
            return;
        }
        int i10 = SerializerFeature.WriteMapNullValue.mask;
        if ((i8 & i10) != 0 && (i8 & (~i10) & SerializerFeature.WRITE_MAP_NULL_FEATURES) == 0) {
            Z();
            return;
        }
        if (i9 == SerializerFeature.WriteNullListAsEmpty.mask) {
            write("[]");
            return;
        }
        if (i9 == SerializerFeature.WriteNullStringAsEmpty.mask) {
            d0("");
            return;
        }
        if (i9 == SerializerFeature.WriteNullBooleanAsFalse.mask) {
            write("false");
        } else if (i9 == SerializerFeature.WriteNullNumberAsZero.mask) {
            write(48);
        } else {
            Z();
        }
    }

    public void c0(SerializerFeature serializerFeature) {
        a0(0, serializerFeature.mask);
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.f5216m != null && this.f5214k > 0) {
            flush();
        }
        char[] cArr = this.f5213j;
        if (cArr.length <= f5211h) {
            f5207d.set(cArr);
        }
        this.f5213j = null;
    }

    public void d0(String str) {
        if (this.f5217n) {
            h0(str);
        } else {
            g0(str, (char) 0);
        }
    }

    @Override // java.io.Writer, java.lang.Appendable
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public d1 append(char c8) {
        write(c8);
        return this;
    }

    public void e0(char[] cArr) {
        if (this.f5217n) {
            k0(cArr);
        } else {
            g0(new String(cArr), (char) 0);
        }
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() {
        Writer writer = this.f5216m;
        if (writer == null) {
            return;
        }
        try {
            writer.write(this.f5213j, 0, this.f5214k);
            this.f5216m.flush();
            this.f5214k = 0;
        } catch (IOException e8) {
            throw new JSONException(e8.getMessage(), e8);
        }
    }

    @Override // java.io.Writer, java.lang.Appendable
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public d1 append(CharSequence charSequence) {
        String string = charSequence == null ? "null" : charSequence.toString();
        write(string, 0, string.length());
        return this;
    }

    /* JADX WARN: Removed duplicated region for block: B:144:0x02b2 A[PHI: r4 r5 r8 r12
  0x02b2: PHI (r4v44 int) = (r4v42 int), (r4v46 int) binds: [B:168:0x02f0, B:143:0x02ae] A[DONT_GENERATE, DONT_INLINE]
  0x02b2: PHI (r5v35 int) = (r5v5 int), (r5v37 int) binds: [B:168:0x02f0, B:143:0x02ae] A[DONT_GENERATE, DONT_INLINE]
  0x02b2: PHI (r8v21 int) = (r8v10 int), (r8v3 int) binds: [B:168:0x02f0, B:143:0x02ae] A[DONT_GENERATE, DONT_INLINE]
  0x02b2: PHI (r12v13 int) = (r12v11 int), (r12v15 int) binds: [B:168:0x02f0, B:143:0x02ae] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:166:0x02ed  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x02f2  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x014f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void g0(java.lang.String r23, char r24) {
        /*
            Method dump skipped, instruction units count: 1338
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: h2.d1.g0(java.lang.String, char):void");
    }

    public void h0(String str) {
        int i8 = 0;
        if (str == null) {
            int i9 = this.f5214k + 4;
            if (i9 > this.f5213j.length) {
                q(i9);
            }
            "null".getChars(0, 4, this.f5213j, this.f5214k);
            this.f5214k = i9;
            return;
        }
        int length = str.length();
        int i10 = this.f5214k + length + 2;
        if (i10 > this.f5213j.length) {
            if (this.f5216m != null) {
                write(39);
                while (i8 < str.length()) {
                    char cCharAt = str.charAt(i8);
                    if (cCharAt <= '\r' || cCharAt == '\\' || cCharAt == '\'' || (cCharAt == '/' && t(SerializerFeature.WriteSlashAsSpecial))) {
                        write(92);
                        write(l2.e.f6201j[cCharAt]);
                    } else {
                        write(cCharAt);
                    }
                    i8++;
                }
                write(39);
                return;
            }
            q(i10);
        }
        int i11 = this.f5214k;
        int i12 = i11 + 1;
        int i13 = i12 + length;
        char[] cArr = this.f5213j;
        cArr[i11] = '\'';
        str.getChars(0, length, cArr, i12);
        this.f5214k = i10;
        int i14 = -1;
        char c8 = 0;
        for (int i15 = i12; i15 < i13; i15++) {
            char c9 = this.f5213j[i15];
            if (c9 <= '\r' || c9 == '\\' || c9 == '\'' || (c9 == '/' && t(SerializerFeature.WriteSlashAsSpecial))) {
                i8++;
                i14 = i15;
                c8 = c9;
            }
        }
        int i16 = i10 + i8;
        if (i16 > this.f5213j.length) {
            q(i16);
        }
        this.f5214k = i16;
        if (i8 == 1) {
            char[] cArr2 = this.f5213j;
            int i17 = i14 + 1;
            System.arraycopy(cArr2, i17, cArr2, i14 + 2, (i13 - i14) - 1);
            char[] cArr3 = this.f5213j;
            cArr3[i14] = '\\';
            cArr3[i17] = l2.e.f6201j[c8];
        } else if (i8 > 1) {
            char[] cArr4 = this.f5213j;
            int i18 = i14 + 1;
            System.arraycopy(cArr4, i18, cArr4, i14 + 2, (i13 - i14) - 1);
            char[] cArr5 = this.f5213j;
            cArr5[i14] = '\\';
            cArr5[i18] = l2.e.f6201j[c8];
            int i19 = i13 + 1;
            for (int i20 = i18 - 2; i20 >= i12; i20--) {
                char c10 = this.f5213j[i20];
                if (c10 <= '\r' || c10 == '\\' || c10 == '\'' || (c10 == '/' && t(SerializerFeature.WriteSlashAsSpecial))) {
                    char[] cArr6 = this.f5213j;
                    int i21 = i20 + 1;
                    System.arraycopy(cArr6, i21, cArr6, i20 + 2, (i19 - i20) - 1);
                    char[] cArr7 = this.f5213j;
                    cArr7[i20] = '\\';
                    cArr7[i21] = l2.e.f6201j[c10];
                    i19++;
                }
            }
        }
        this.f5213j[this.f5214k - 1] = '\'';
    }

    @Override // java.io.Writer, java.lang.Appendable
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public d1 append(CharSequence charSequence, int i8, int i9) {
        if (charSequence == null) {
            charSequence = "null";
        }
        String string = charSequence.subSequence(i8, i9).toString();
        write(string, 0, string.length());
        return this;
    }

    public void j() {
        int i8 = this.f5215l;
        boolean z7 = (SerializerFeature.QuoteFieldNames.mask & i8) != 0;
        this.f5218o = z7;
        boolean z8 = (SerializerFeature.UseSingleQuotes.mask & i8) != 0;
        this.f5217n = z8;
        this.f5219p = (SerializerFeature.SortField.mask & i8) != 0;
        this.f5220q = (SerializerFeature.DisableCircularReferenceDetect.mask & i8) != 0;
        boolean z9 = (SerializerFeature.BeanToArray.mask & i8) != 0;
        this.f5221r = z9;
        this.f5222s = (SerializerFeature.WriteNonStringValueAsString.mask & i8) != 0;
        this.f5223t = (SerializerFeature.NotWriteDefaultValue.mask & i8) != 0;
        boolean z10 = (SerializerFeature.WriteEnumUsingName.mask & i8) != 0;
        this.f5224u = z10;
        this.f5225v = (SerializerFeature.WriteEnumUsingToString.mask & i8) != 0;
        this.f5226w = z7 && (f5212i & i8) == 0 && (z9 || z10);
        this.f5227x = z8 ? '\'' : '\"';
        boolean z11 = (SerializerFeature.BrowserSecure.mask & i8) != 0;
        this.f5229z = z11;
        this.A = z11 ? 5764610843043954687L : (i8 & SerializerFeature.WriteSlashAsSpecial.mask) != 0 ? 140758963191807L : 21474836479L;
    }

    public void k0(char[] cArr) {
        int i8 = 0;
        if (cArr == null) {
            int i9 = this.f5214k + 4;
            if (i9 > this.f5213j.length) {
                q(i9);
            }
            "null".getChars(0, 4, this.f5213j, this.f5214k);
            this.f5214k = i9;
            return;
        }
        int length = cArr.length;
        int i10 = this.f5214k + length + 2;
        if (i10 > this.f5213j.length) {
            if (this.f5216m != null) {
                write(39);
                while (i8 < cArr.length) {
                    char c8 = cArr[i8];
                    if (c8 <= '\r' || c8 == '\\' || c8 == '\'' || (c8 == '/' && t(SerializerFeature.WriteSlashAsSpecial))) {
                        write(92);
                        write(l2.e.f6201j[c8]);
                    } else {
                        write(c8);
                    }
                    i8++;
                }
                write(39);
                return;
            }
            q(i10);
        }
        int i11 = this.f5214k;
        int i12 = i11 + 1;
        int i13 = length + i12;
        char[] cArr2 = this.f5213j;
        cArr2[i11] = '\'';
        System.arraycopy(cArr, 0, cArr2, i12, cArr.length);
        this.f5214k = i10;
        int i14 = -1;
        char c9 = 0;
        for (int i15 = i12; i15 < i13; i15++) {
            char c10 = this.f5213j[i15];
            if (c10 <= '\r' || c10 == '\\' || c10 == '\'' || (c10 == '/' && t(SerializerFeature.WriteSlashAsSpecial))) {
                i8++;
                i14 = i15;
                c9 = c10;
            }
        }
        int i16 = i10 + i8;
        if (i16 > this.f5213j.length) {
            q(i16);
        }
        this.f5214k = i16;
        if (i8 == 1) {
            char[] cArr3 = this.f5213j;
            int i17 = i14 + 1;
            System.arraycopy(cArr3, i17, cArr3, i14 + 2, (i13 - i14) - 1);
            char[] cArr4 = this.f5213j;
            cArr4[i14] = '\\';
            cArr4[i17] = l2.e.f6201j[c9];
        } else if (i8 > 1) {
            char[] cArr5 = this.f5213j;
            int i18 = i14 + 1;
            System.arraycopy(cArr5, i18, cArr5, i14 + 2, (i13 - i14) - 1);
            char[] cArr6 = this.f5213j;
            cArr6[i14] = '\\';
            cArr6[i18] = l2.e.f6201j[c9];
            int i19 = i13 + 1;
            for (int i20 = i18 - 2; i20 >= i12; i20--) {
                char c11 = this.f5213j[i20];
                if (c11 <= '\r' || c11 == '\\' || c11 == '\'' || (c11 == '/' && t(SerializerFeature.WriteSlashAsSpecial))) {
                    char[] cArr7 = this.f5213j;
                    int i21 = i20 + 1;
                    System.arraycopy(cArr7, i21, cArr7, i20 + 2, (i19 - i20) - 1);
                    char[] cArr8 = this.f5213j;
                    cArr8[i20] = '\\';
                    cArr8[i21] = l2.e.f6201j[c11];
                    i19++;
                }
            }
        }
        this.f5213j[this.f5214k - 1] = '\'';
    }

    public int l0(OutputStream outputStream, Charset charset) throws IOException {
        if (this.f5216m != null) {
            throw new UnsupportedOperationException("writer not null");
        }
        if (charset == l2.e.f6193b) {
            return n(outputStream);
        }
        byte[] bytes = new String(this.f5213j, 0, this.f5214k).getBytes(charset);
        outputStream.write(bytes);
        return bytes.length;
    }

    public void m(SerializerFeature serializerFeature, boolean z7) {
        if (z7) {
            int mask = this.f5215l | serializerFeature.getMask();
            this.f5215l = mask;
            SerializerFeature serializerFeature2 = SerializerFeature.WriteEnumUsingToString;
            if (serializerFeature == serializerFeature2) {
                this.f5215l = (~SerializerFeature.WriteEnumUsingName.getMask()) & mask;
            } else if (serializerFeature == SerializerFeature.WriteEnumUsingName) {
                this.f5215l = (~serializerFeature2.getMask()) & mask;
            }
        } else {
            this.f5215l = (~serializerFeature.getMask()) & this.f5215l;
        }
        j();
    }

    public final int n(OutputStream outputStream) throws IOException {
        int i8 = (int) (((double) this.f5214k) * 3.0d);
        ThreadLocal<byte[]> threadLocal = f5208e;
        byte[] bArr = threadLocal.get();
        if (bArr == null) {
            bArr = new byte[8192];
            threadLocal.set(bArr);
        }
        byte[] bArr2 = bArr.length < i8 ? new byte[i8] : bArr;
        int iG = l2.e.g(this.f5213j, 0, this.f5214k, bArr2);
        outputStream.write(bArr2, 0, iG);
        if (bArr2 != bArr && bArr2.length <= f5211h) {
            threadLocal.set(bArr2);
        }
        return iG;
    }

    public final byte[] o() {
        int i8 = (int) (((double) this.f5214k) * 3.0d);
        ThreadLocal<byte[]> threadLocal = f5208e;
        byte[] bArr = threadLocal.get();
        if (bArr == null) {
            bArr = new byte[8192];
            threadLocal.set(bArr);
        }
        byte[] bArr2 = bArr.length < i8 ? new byte[i8] : bArr;
        int iG = l2.e.g(this.f5213j, 0, this.f5214k, bArr2);
        byte[] bArr3 = new byte[iG];
        System.arraycopy(bArr2, 0, bArr3, 0, iG);
        if (bArr2 != bArr && bArr2.length <= f5211h) {
            threadLocal.set(bArr2);
        }
        return bArr3;
    }

    public void q(int i8) {
        ThreadLocal<char[]> threadLocal;
        char[] cArr;
        int i9 = this.f5228y;
        if (i9 != -1 && i8 >= i9) {
            throw new JSONException("serialize exceeded MAX_OUTPUT_LENGTH=" + this.f5228y + ", minimumCapacity=" + i8);
        }
        char[] cArr2 = this.f5213j;
        int length = cArr2.length + (cArr2.length >> 1) + 1;
        if (length >= i8) {
            i8 = length;
        }
        char[] cArr3 = new char[i8];
        System.arraycopy(cArr2, 0, cArr3, 0, this.f5214k);
        if (this.f5213j.length < f5211h && ((cArr = (threadLocal = f5207d).get()) == null || cArr.length < this.f5213j.length)) {
            threadLocal.set(this.f5213j);
        }
        this.f5213j = cArr3;
    }

    public boolean r(int i8) {
        return (i8 & this.f5215l) != 0;
    }

    public boolean t(SerializerFeature serializerFeature) {
        return (serializerFeature.mask & this.f5215l) != 0;
    }

    public String toString() {
        return new String(this.f5213j, 0, this.f5214k);
    }

    public byte[] v(Charset charset) {
        if (this.f5216m == null) {
            return charset == l2.e.f6193b ? o() : new String(this.f5213j, 0, this.f5214k).getBytes(charset);
        }
        throw new UnsupportedOperationException("writer not null");
    }

    public void w(boolean z7) {
        if (z7) {
            write("true");
        } else {
            write("false");
        }
    }

    @Override // java.io.Writer
    public void write(int i8) {
        int i9 = 1;
        int i10 = this.f5214k + 1;
        if (i10 <= this.f5213j.length) {
            i9 = i10;
        } else if (this.f5216m == null) {
            q(i10);
            i9 = i10;
        } else {
            flush();
        }
        this.f5213j[this.f5214k] = (char) i8;
        this.f5214k = i9;
    }

    public void x(byte[] bArr) {
        if (r(SerializerFeature.WriteClassName.mask)) {
            T(bArr);
            return;
        }
        int length = bArr.length;
        boolean z7 = this.f5217n;
        char c8 = z7 ? '\'' : '\"';
        if (length == 0) {
            write(z7 ? "''" : "\"\"");
            return;
        }
        char[] cArr = l2.e.f6207p;
        int i8 = (length / 3) * 3;
        int i9 = length - 1;
        int i10 = this.f5214k;
        int i11 = (((i9 / 3) + 1) << 2) + i10 + 2;
        if (i11 > this.f5213j.length) {
            if (this.f5216m != null) {
                write(c8);
                int i12 = 0;
                while (i12 < i8) {
                    int i13 = i12 + 1;
                    int i14 = i13 + 1;
                    int i15 = ((bArr[i12] & 255) << 16) | ((bArr[i13] & 255) << 8) | (bArr[i14] & 255);
                    write(cArr[(i15 >>> 18) & 63]);
                    write(cArr[(i15 >>> 12) & 63]);
                    write(cArr[(i15 >>> 6) & 63]);
                    write(cArr[i15 & 63]);
                    i12 = i14 + 1;
                }
                int i16 = length - i8;
                if (i16 > 0) {
                    int i17 = ((bArr[i8] & 255) << 10) | (i16 == 2 ? (bArr[i9] & 255) << 2 : 0);
                    write(cArr[i17 >> 12]);
                    write(cArr[(i17 >>> 6) & 63]);
                    write(i16 == 2 ? cArr[i17 & 63] : '=');
                    write(61);
                }
                write(c8);
                return;
            }
            q(i11);
        }
        this.f5214k = i11;
        int i18 = i10 + 1;
        this.f5213j[i10] = c8;
        int i19 = 0;
        while (i19 < i8) {
            int i20 = i19 + 1;
            int i21 = i20 + 1;
            int i22 = ((bArr[i19] & 255) << 16) | ((bArr[i20] & 255) << 8);
            int i23 = i21 + 1;
            int i24 = i22 | (bArr[i21] & 255);
            char[] cArr2 = this.f5213j;
            int i25 = i18 + 1;
            cArr2[i18] = cArr[(i24 >>> 18) & 63];
            int i26 = i25 + 1;
            cArr2[i25] = cArr[(i24 >>> 12) & 63];
            int i27 = i26 + 1;
            cArr2[i26] = cArr[(i24 >>> 6) & 63];
            i18 = i27 + 1;
            cArr2[i27] = cArr[i24 & 63];
            i19 = i23;
        }
        int i28 = length - i8;
        if (i28 > 0) {
            int i29 = ((bArr[i8] & 255) << 10) | (i28 == 2 ? (bArr[i9] & 255) << 2 : 0);
            char[] cArr3 = this.f5213j;
            cArr3[i11 - 5] = cArr[i29 >> 12];
            cArr3[i11 - 4] = cArr[(i29 >>> 6) & 63];
            cArr3[i11 - 3] = i28 == 2 ? cArr[i29 & 63] : '=';
            cArr3[i11 - 2] = '=';
        }
        this.f5213j[i11 - 1] = c8;
    }

    public d1(Writer writer) {
        this(writer, c2.a.DEFAULT_GENERATE_FEATURE, SerializerFeature.EMPTY);
    }

    public d1(Writer writer, int i8, SerializerFeature... serializerFeatureArr) {
        this.f5228y = -1;
        this.f5216m = writer;
        ThreadLocal<char[]> threadLocal = f5207d;
        char[] cArr = threadLocal.get();
        this.f5213j = cArr;
        if (cArr != null) {
            threadLocal.set(null);
        } else {
            this.f5213j = new char[2048];
        }
        for (SerializerFeature serializerFeature : serializerFeatureArr) {
            i8 |= serializerFeature.getMask();
        }
        this.f5215l = i8;
        j();
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i8, int i9) {
        int i10;
        if (i8 < 0 || i8 > cArr.length || i9 < 0 || (i10 = i8 + i9) > cArr.length || i10 < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (i9 == 0) {
            return;
        }
        int i11 = this.f5214k + i9;
        if (i11 > this.f5213j.length) {
            if (this.f5216m == null) {
                q(i11);
            } else {
                do {
                    char[] cArr2 = this.f5213j;
                    int length = cArr2.length;
                    int i12 = this.f5214k;
                    int i13 = length - i12;
                    System.arraycopy(cArr, i8, cArr2, i12, i13);
                    this.f5214k = this.f5213j.length;
                    flush();
                    i9 -= i13;
                    i8 += i13;
                } while (i9 > this.f5213j.length);
                i11 = i9;
            }
        }
        System.arraycopy(cArr, i8, this.f5213j, this.f5214k, i9);
        this.f5214k = i11;
    }

    @Override // java.io.Writer
    public void write(String str, int i8, int i9) {
        int i10;
        int i11 = this.f5214k + i9;
        if (i11 > this.f5213j.length) {
            if (this.f5216m == null) {
                q(i11);
            } else {
                while (true) {
                    char[] cArr = this.f5213j;
                    int length = cArr.length;
                    int i12 = this.f5214k;
                    int i13 = length - i12;
                    i10 = i8 + i13;
                    str.getChars(i8, i10, cArr, i12);
                    this.f5214k = this.f5213j.length;
                    flush();
                    i9 -= i13;
                    if (i9 <= this.f5213j.length) {
                        break;
                    } else {
                        i8 = i10;
                    }
                }
                i11 = i9;
                i8 = i10;
            }
        }
        str.getChars(i8, i9 + i8, this.f5213j, this.f5214k);
        this.f5214k = i11;
    }

    @Override // java.io.Writer
    public void write(String str) {
        if (str == null) {
            Z();
        } else {
            write(str, 0, str.length());
        }
    }
}
