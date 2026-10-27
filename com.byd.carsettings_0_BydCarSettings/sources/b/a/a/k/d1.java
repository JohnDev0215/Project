package b.a.a.k;

import androidx.exifinterface.media.ExifInterface;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.math.BigDecimal;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: SerializeWriter.java */
/* JADX INFO: loaded from: classes.dex */
public final class d1 extends Writer {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ThreadLocal<char[]> f354a = new ThreadLocal<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ThreadLocal<byte[]> f355b = new ThreadLocal<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final char[] f356c = ":true".toCharArray();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final char[] f357d = ":false".toCharArray();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static int f358e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final int f359f;
    public char[] g;
    public int h;
    public int i;
    public final Writer j;
    public boolean k;
    public boolean l;
    public boolean m;
    public boolean n;
    public boolean o;
    public boolean p;
    public boolean q;
    public boolean r;
    public boolean s;
    public boolean t;
    public char u;
    public int v;
    public boolean w;
    public long x;

    static {
        int i;
        f358e = 131072;
        try {
            String strK = b.a.a.n.e.k("fastjson.serializer_buffer_threshold");
            if (strK != null && strK.length() > 0 && (i = Integer.parseInt(strK)) >= 64 && i <= 65536) {
                f358e = i * 1024;
            }
        } catch (Throwable unused) {
        }
        f359f = SerializerFeature.UseSingleQuotes.mask | 0 | SerializerFeature.BrowserCompatible.mask | SerializerFeature.PrettyFormat.mask | SerializerFeature.WriteEnumUsingToString.mask | SerializerFeature.WriteNonStringValueAsString.mask | SerializerFeature.WriteSlashAsSpecial.mask | SerializerFeature.IgnoreErrorGetter.mask | SerializerFeature.WriteClassName.mask | SerializerFeature.NotWriteDefaultValue.mask;
    }

    public d1() {
        this(null);
    }

    public void A(char c2, String str, BigDecimal bigDecimal) {
        write(c2);
        u(str);
        if (bigDecimal == null) {
            J();
        } else {
            int iScale = bigDecimal.scale();
            write((!l(SerializerFeature.WriteBigDecimalAsPlain) || iScale < -100 || iScale >= 100) ? bigDecimal.toString() : bigDecimal.toPlainString());
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
    public void B(char r21, java.lang.String r22, java.lang.String r23) {
        /*
            Method dump skipped, instruction units count: 776
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.a.k.d1.B(char, java.lang.String, java.lang.String):void");
    }

    public void C(float f2, boolean z) {
        if (f2 != f2 || f2 == Float.POSITIVE_INFINITY || f2 == Float.NEGATIVE_INFINITY) {
            J();
            return;
        }
        int i = this.h + 15;
        if (i > this.g.length) {
            if (this.j != null) {
                String strB = b.a.a.n.j.b(f2);
                write(strB, 0, strB.length());
                if (z && l(SerializerFeature.WriteClassName)) {
                    write(70);
                    return;
                }
                return;
            }
            j(i);
        }
        this.h += b.a.a.n.j.a(f2, this.g, this.h);
        if (z && l(SerializerFeature.WriteClassName)) {
            write(70);
        }
    }

    public void E(byte[] bArr) {
        int length = this.h + (bArr.length * 2) + 3;
        if (length > this.g.length) {
            j(length);
        }
        char[] cArr = this.g;
        int i = this.h;
        int i2 = i + 1;
        this.h = i2;
        cArr[i] = 'x';
        this.h = i2 + 1;
        cArr[i2] = '\'';
        for (byte b2 : bArr) {
            int i3 = b2 & ExifInterface.MARKER;
            int i4 = i3 >> 4;
            int i5 = i3 & 15;
            char[] cArr2 = this.g;
            int i6 = this.h;
            int i7 = i6 + 1;
            this.h = i7;
            int i8 = 48;
            cArr2[i6] = (char) (i4 + (i4 < 10 ? 48 : 55));
            this.h = i7 + 1;
            if (i5 >= 10) {
                i8 = 55;
            }
            cArr2[i7] = (char) (i5 + i8);
        }
        char[] cArr3 = this.g;
        int i9 = this.h;
        this.h = i9 + 1;
        cArr3[i9] = '\'';
    }

    public void F(int i) {
        if (i == Integer.MIN_VALUE) {
            write("-2147483648");
            return;
        }
        int iM = i < 0 ? b.a.a.n.e.m(-i) + 1 : b.a.a.n.e.m(i);
        int i2 = this.h + iM;
        if (i2 > this.g.length) {
            if (this.j != null) {
                char[] cArr = new char[iM];
                b.a.a.n.e.i(i, iM, cArr);
                write(cArr, 0, iM);
                return;
            }
            j(i2);
        }
        b.a.a.n.e.i(i, i2, this.g);
        this.h = i2;
    }

    public final void G(String str) {
        byte[] bArr = b.a.a.n.e.g;
        int length = str.length();
        boolean z = true;
        int i = this.h + length + 1;
        int i2 = 0;
        if (i > this.g.length) {
            if (this.j != null) {
                if (length == 0) {
                    write(39);
                    write(39);
                    write(58);
                    return;
                }
                int i3 = 0;
                while (true) {
                    if (i3 < length) {
                        char cCharAt = str.charAt(i3);
                        if (cCharAt < bArr.length && bArr[cCharAt] != 0) {
                            break;
                        } else {
                            i3++;
                        }
                    } else {
                        z = false;
                        break;
                    }
                }
                if (z) {
                    write(39);
                }
                while (i2 < length) {
                    char cCharAt2 = str.charAt(i2);
                    if (cCharAt2 >= bArr.length || bArr[cCharAt2] == 0) {
                        write(cCharAt2);
                    } else {
                        write(92);
                        write(b.a.a.n.e.j[cCharAt2]);
                    }
                    i2++;
                }
                if (z) {
                    write(39);
                }
                write(58);
                return;
            }
            j(i);
        }
        if (length == 0) {
            int i4 = this.h;
            if (i4 + 3 > this.g.length) {
                j(i4 + 3);
            }
            char[] cArr = this.g;
            int i5 = this.h;
            int i6 = i5 + 1;
            this.h = i6;
            cArr[i5] = '\'';
            int i7 = i6 + 1;
            this.h = i7;
            cArr[i6] = '\'';
            this.h = i7 + 1;
            cArr[i7] = ':';
            return;
        }
        int i8 = this.h;
        int i9 = i8 + length;
        str.getChars(0, length, this.g, i8);
        this.h = i;
        int i10 = i8;
        boolean z2 = false;
        while (i10 < i9) {
            char[] cArr2 = this.g;
            char c2 = cArr2[i10];
            if (c2 < bArr.length && bArr[c2] != 0) {
                if (z2) {
                    i++;
                    if (i > cArr2.length) {
                        j(i);
                    }
                    this.h = i;
                    char[] cArr3 = this.g;
                    int i11 = i10 + 1;
                    System.arraycopy(cArr3, i11, cArr3, i10 + 2, i9 - i10);
                    char[] cArr4 = this.g;
                    cArr4[i10] = '\\';
                    cArr4[i11] = b.a.a.n.e.j[c2];
                    i9++;
                    i10 = i11;
                } else {
                    i += 3;
                    if (i > cArr2.length) {
                        j(i);
                    }
                    this.h = i;
                    char[] cArr5 = this.g;
                    int i12 = i10 + 1;
                    System.arraycopy(cArr5, i12, cArr5, i10 + 3, (i9 - i10) - 1);
                    char[] cArr6 = this.g;
                    System.arraycopy(cArr6, i2, cArr6, 1, i10);
                    char[] cArr7 = this.g;
                    cArr7[i8] = '\'';
                    cArr7[i12] = '\\';
                    int i13 = i12 + 1;
                    cArr7[i13] = b.a.a.n.e.j[c2];
                    i9 += 2;
                    cArr7[this.h - 2] = '\'';
                    i10 = i13;
                    z2 = true;
                }
            }
            i10++;
            i2 = 0;
        }
        this.g[i - 1] = ':';
    }

    public void H(long j) {
        boolean z = l(SerializerFeature.BrowserCompatible) && !l(SerializerFeature.WriteClassName) && (j > 9007199254740991L || j < -9007199254740991L);
        if (j == Long.MIN_VALUE) {
            if (z) {
                write("\"-9223372036854775808\"");
                return;
            } else {
                write("-9223372036854775808");
                return;
            }
        }
        int iN = j < 0 ? b.a.a.n.e.n(-j) + 1 : b.a.a.n.e.n(j);
        int i = this.h + iN;
        if (z) {
            i += 2;
        }
        if (i > this.g.length) {
            if (this.j != null) {
                char[] cArr = new char[iN];
                b.a.a.n.e.j(j, iN, cArr);
                if (!z) {
                    write(cArr, 0, iN);
                    return;
                }
                write(34);
                write(cArr, 0, iN);
                write(34);
                return;
            }
            j(i);
        }
        if (z) {
            char[] cArr2 = this.g;
            cArr2[this.h] = '\"';
            int i2 = i - 1;
            b.a.a.n.e.j(j, i2, cArr2);
            this.g[i2] = '\"';
        } else {
            b.a.a.n.e.j(j, i, this.g);
        }
        this.h = i;
    }

    public void J() {
        write("null");
    }

    public void L(int i, int i2) {
        if ((i & i2) == 0 && (this.i & i2) == 0) {
            J();
            return;
        }
        int i3 = SerializerFeature.WriteMapNullValue.mask;
        if ((i & i3) != 0 && (i & (~i3) & SerializerFeature.WRITE_MAP_NULL_FEATURES) == 0) {
            J();
            return;
        }
        if (i2 == SerializerFeature.WriteNullListAsEmpty.mask) {
            write("[]");
            return;
        }
        if (i2 == SerializerFeature.WriteNullStringAsEmpty.mask) {
            N("");
            return;
        }
        if (i2 == SerializerFeature.WriteNullBooleanAsFalse.mask) {
            write("false");
        } else if (i2 == SerializerFeature.WriteNullNumberAsZero.mask) {
            write(48);
        } else {
            J();
        }
    }

    public void M(SerializerFeature serializerFeature) {
        L(0, serializerFeature.mask);
    }

    public void N(String str) {
        if (this.k) {
            R(str);
        } else {
            Q(str, (char) 0);
        }
    }

    public void O(char[] cArr) {
        if (this.k) {
            S(cArr);
        } else {
            Q(new String(cArr), (char) 0);
        }
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
    public void Q(java.lang.String r23, char r24) {
        /*
            Method dump skipped, instruction units count: 1338
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.a.k.d1.Q(java.lang.String, char):void");
    }

    public void R(String str) {
        int i = 0;
        if (str == null) {
            int i2 = this.h + 4;
            if (i2 > this.g.length) {
                j(i2);
            }
            "null".getChars(0, 4, this.g, this.h);
            this.h = i2;
            return;
        }
        int length = str.length();
        int i3 = this.h + length + 2;
        if (i3 > this.g.length) {
            if (this.j != null) {
                write(39);
                while (i < str.length()) {
                    char cCharAt = str.charAt(i);
                    if (cCharAt <= '\r' || cCharAt == '\\' || cCharAt == '\'' || (cCharAt == '/' && l(SerializerFeature.WriteSlashAsSpecial))) {
                        write(92);
                        write(b.a.a.n.e.j[cCharAt]);
                    } else {
                        write(cCharAt);
                    }
                    i++;
                }
                write(39);
                return;
            }
            j(i3);
        }
        int i4 = this.h;
        int i5 = i4 + 1;
        int i6 = i5 + length;
        char[] cArr = this.g;
        cArr[i4] = '\'';
        str.getChars(0, length, cArr, i5);
        this.h = i3;
        int i7 = -1;
        char c2 = 0;
        for (int i8 = i5; i8 < i6; i8++) {
            char c3 = this.g[i8];
            if (c3 <= '\r' || c3 == '\\' || c3 == '\'' || (c3 == '/' && l(SerializerFeature.WriteSlashAsSpecial))) {
                i++;
                i7 = i8;
                c2 = c3;
            }
        }
        int i9 = i3 + i;
        if (i9 > this.g.length) {
            j(i9);
        }
        this.h = i9;
        if (i == 1) {
            char[] cArr2 = this.g;
            int i10 = i7 + 1;
            System.arraycopy(cArr2, i10, cArr2, i7 + 2, (i6 - i7) - 1);
            char[] cArr3 = this.g;
            cArr3[i7] = '\\';
            cArr3[i10] = b.a.a.n.e.j[c2];
        } else if (i > 1) {
            char[] cArr4 = this.g;
            int i11 = i7 + 1;
            System.arraycopy(cArr4, i11, cArr4, i7 + 2, (i6 - i7) - 1);
            char[] cArr5 = this.g;
            cArr5[i7] = '\\';
            cArr5[i11] = b.a.a.n.e.j[c2];
            int i12 = i6 + 1;
            for (int i13 = i11 - 2; i13 >= i5; i13--) {
                char c4 = this.g[i13];
                if (c4 <= '\r' || c4 == '\\' || c4 == '\'' || (c4 == '/' && l(SerializerFeature.WriteSlashAsSpecial))) {
                    char[] cArr6 = this.g;
                    int i14 = i13 + 1;
                    System.arraycopy(cArr6, i14, cArr6, i13 + 2, (i12 - i13) - 1);
                    char[] cArr7 = this.g;
                    cArr7[i13] = '\\';
                    cArr7[i14] = b.a.a.n.e.j[c4];
                    i12++;
                }
            }
        }
        this.g[this.h - 1] = '\'';
    }

    public void S(char[] cArr) {
        int i = 0;
        if (cArr == null) {
            int i2 = this.h + 4;
            if (i2 > this.g.length) {
                j(i2);
            }
            "null".getChars(0, 4, this.g, this.h);
            this.h = i2;
            return;
        }
        int length = cArr.length;
        int i3 = this.h + length + 2;
        if (i3 > this.g.length) {
            if (this.j != null) {
                write(39);
                while (i < cArr.length) {
                    char c2 = cArr[i];
                    if (c2 <= '\r' || c2 == '\\' || c2 == '\'' || (c2 == '/' && l(SerializerFeature.WriteSlashAsSpecial))) {
                        write(92);
                        write(b.a.a.n.e.j[c2]);
                    } else {
                        write(c2);
                    }
                    i++;
                }
                write(39);
                return;
            }
            j(i3);
        }
        int i4 = this.h;
        int i5 = i4 + 1;
        int i6 = length + i5;
        char[] cArr2 = this.g;
        cArr2[i4] = '\'';
        System.arraycopy(cArr, 0, cArr2, i5, cArr.length);
        this.h = i3;
        int i7 = -1;
        char c3 = 0;
        for (int i8 = i5; i8 < i6; i8++) {
            char c4 = this.g[i8];
            if (c4 <= '\r' || c4 == '\\' || c4 == '\'' || (c4 == '/' && l(SerializerFeature.WriteSlashAsSpecial))) {
                i++;
                i7 = i8;
                c3 = c4;
            }
        }
        int i9 = i3 + i;
        if (i9 > this.g.length) {
            j(i9);
        }
        this.h = i9;
        if (i == 1) {
            char[] cArr3 = this.g;
            int i10 = i7 + 1;
            System.arraycopy(cArr3, i10, cArr3, i7 + 2, (i6 - i7) - 1);
            char[] cArr4 = this.g;
            cArr4[i7] = '\\';
            cArr4[i10] = b.a.a.n.e.j[c3];
        } else if (i > 1) {
            char[] cArr5 = this.g;
            int i11 = i7 + 1;
            System.arraycopy(cArr5, i11, cArr5, i7 + 2, (i6 - i7) - 1);
            char[] cArr6 = this.g;
            cArr6[i7] = '\\';
            cArr6[i11] = b.a.a.n.e.j[c3];
            int i12 = i6 + 1;
            for (int i13 = i11 - 2; i13 >= i5; i13--) {
                char c5 = this.g[i13];
                if (c5 <= '\r' || c5 == '\\' || c5 == '\'' || (c5 == '/' && l(SerializerFeature.WriteSlashAsSpecial))) {
                    char[] cArr7 = this.g;
                    int i14 = i13 + 1;
                    System.arraycopy(cArr7, i14, cArr7, i13 + 2, (i12 - i13) - 1);
                    char[] cArr8 = this.g;
                    cArr8[i13] = '\\';
                    cArr8[i14] = b.a.a.n.e.j[c5];
                    i12++;
                }
            }
        }
        this.g[this.h - 1] = '\'';
    }

    public int T(OutputStream outputStream, Charset charset) throws IOException {
        if (this.j != null) {
            throw new UnsupportedOperationException("writer not null");
        }
        if (charset == b.a.a.n.e.f437b) {
            return h(outputStream);
        }
        byte[] bytes = new String(this.g, 0, this.h).getBytes(charset);
        outputStream.write(bytes);
        return bytes.length;
    }

    @Override // java.io.Writer, java.lang.Appendable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public d1 append(char c2) {
        write(c2);
        return this;
    }

    @Override // java.io.Writer, java.lang.Appendable
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public d1 append(CharSequence charSequence) {
        String string = charSequence == null ? "null" : charSequence.toString();
        write(string, 0, string.length());
        return this;
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.j != null && this.h > 0) {
            flush();
        }
        char[] cArr = this.g;
        if (cArr.length <= f358e) {
            f354a.set(cArr);
        }
        this.g = null;
    }

    @Override // java.io.Writer, java.lang.Appendable
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public d1 append(CharSequence charSequence, int i, int i2) {
        if (charSequence == null) {
            charSequence = "null";
        }
        String string = charSequence.subSequence(i, i2).toString();
        write(string, 0, string.length());
        return this;
    }

    public void f() {
        int i = this.i;
        boolean z = (SerializerFeature.QuoteFieldNames.mask & i) != 0;
        this.l = z;
        boolean z2 = (SerializerFeature.UseSingleQuotes.mask & i) != 0;
        this.k = z2;
        this.m = (SerializerFeature.SortField.mask & i) != 0;
        this.n = (SerializerFeature.DisableCircularReferenceDetect.mask & i) != 0;
        boolean z3 = (SerializerFeature.BeanToArray.mask & i) != 0;
        this.o = z3;
        this.p = (SerializerFeature.WriteNonStringValueAsString.mask & i) != 0;
        this.q = (SerializerFeature.NotWriteDefaultValue.mask & i) != 0;
        boolean z4 = (SerializerFeature.WriteEnumUsingName.mask & i) != 0;
        this.r = z4;
        this.s = (SerializerFeature.WriteEnumUsingToString.mask & i) != 0;
        this.t = z && (f359f & i) == 0 && (z3 || z4);
        this.u = z2 ? '\'' : '\"';
        boolean z5 = (SerializerFeature.BrowserSecure.mask & i) != 0;
        this.w = z5;
        this.x = z5 ? 5764610843043954687L : (i & SerializerFeature.WriteSlashAsSpecial.mask) != 0 ? 140758963191807L : 21474836479L;
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() {
        Writer writer = this.j;
        if (writer == null) {
            return;
        }
        try {
            writer.write(this.g, 0, this.h);
            this.j.flush();
            this.h = 0;
        } catch (IOException e2) {
            throw new JSONException(e2.getMessage(), e2);
        }
    }

    public void g(SerializerFeature serializerFeature, boolean z) {
        if (z) {
            int mask = this.i | serializerFeature.getMask();
            this.i = mask;
            SerializerFeature serializerFeature2 = SerializerFeature.WriteEnumUsingToString;
            if (serializerFeature == serializerFeature2) {
                this.i = (~SerializerFeature.WriteEnumUsingName.getMask()) & mask;
            } else if (serializerFeature == SerializerFeature.WriteEnumUsingName) {
                this.i = (~serializerFeature2.getMask()) & mask;
            }
        } else {
            this.i = (~serializerFeature.getMask()) & this.i;
        }
        f();
    }

    public final int h(OutputStream outputStream) throws IOException {
        int i = (int) (((double) this.h) * 3.0d);
        ThreadLocal<byte[]> threadLocal = f355b;
        byte[] bArr = threadLocal.get();
        if (bArr == null) {
            bArr = new byte[8192];
            threadLocal.set(bArr);
        }
        byte[] bArr2 = bArr.length < i ? new byte[i] : bArr;
        int iG = b.a.a.n.e.g(this.g, 0, this.h, bArr2);
        outputStream.write(bArr2, 0, iG);
        if (bArr2 != bArr && bArr2.length <= f358e) {
            threadLocal.set(bArr2);
        }
        return iG;
    }

    public final byte[] i() {
        int i = (int) (((double) this.h) * 3.0d);
        ThreadLocal<byte[]> threadLocal = f355b;
        byte[] bArr = threadLocal.get();
        if (bArr == null) {
            bArr = new byte[8192];
            threadLocal.set(bArr);
        }
        byte[] bArr2 = bArr.length < i ? new byte[i] : bArr;
        int iG = b.a.a.n.e.g(this.g, 0, this.h, bArr2);
        byte[] bArr3 = new byte[iG];
        System.arraycopy(bArr2, 0, bArr3, 0, iG);
        if (bArr2 != bArr && bArr2.length <= f358e) {
            threadLocal.set(bArr2);
        }
        return bArr3;
    }

    public void j(int i) {
        ThreadLocal<char[]> threadLocal;
        char[] cArr;
        int i2 = this.v;
        if (i2 != -1 && i >= i2) {
            throw new JSONException("serialize exceeded MAX_OUTPUT_LENGTH=" + this.v + ", minimumCapacity=" + i);
        }
        char[] cArr2 = this.g;
        int length = cArr2.length + (cArr2.length >> 1) + 1;
        if (length >= i) {
            i = length;
        }
        char[] cArr3 = new char[i];
        System.arraycopy(cArr2, 0, cArr3, 0, this.h);
        if (this.g.length < f358e && ((cArr = (threadLocal = f354a).get()) == null || cArr.length < this.g.length)) {
            threadLocal.set(this.g);
        }
        this.g = cArr3;
    }

    public boolean k(int i) {
        return (i & this.i) != 0;
    }

    public boolean l(SerializerFeature serializerFeature) {
        return (serializerFeature.mask & this.i) != 0;
    }

    public byte[] n(Charset charset) {
        if (this.j == null) {
            return charset == b.a.a.n.e.f437b ? i() : new String(this.g, 0, this.h).getBytes(charset);
        }
        throw new UnsupportedOperationException("writer not null");
    }

    public void o(boolean z) {
        if (z) {
            write("true");
        } else {
            write("false");
        }
    }

    public void q(byte[] bArr) {
        if (k(SerializerFeature.WriteClassName.mask)) {
            E(bArr);
            return;
        }
        int length = bArr.length;
        boolean z = this.k;
        char c2 = z ? '\'' : '\"';
        if (length == 0) {
            write(z ? "''" : "\"\"");
            return;
        }
        char[] cArr = b.a.a.n.e.p;
        int i = (length / 3) * 3;
        int i2 = length - 1;
        int i3 = this.h;
        int i4 = (((i2 / 3) + 1) << 2) + i3 + 2;
        if (i4 > this.g.length) {
            if (this.j != null) {
                write(c2);
                int i5 = 0;
                while (i5 < i) {
                    int i6 = i5 + 1;
                    int i7 = i6 + 1;
                    int i8 = ((bArr[i5] & ExifInterface.MARKER) << 16) | ((bArr[i6] & ExifInterface.MARKER) << 8) | (bArr[i7] & ExifInterface.MARKER);
                    write(cArr[(i8 >>> 18) & 63]);
                    write(cArr[(i8 >>> 12) & 63]);
                    write(cArr[(i8 >>> 6) & 63]);
                    write(cArr[i8 & 63]);
                    i5 = i7 + 1;
                }
                int i9 = length - i;
                if (i9 > 0) {
                    int i10 = ((bArr[i] & ExifInterface.MARKER) << 10) | (i9 == 2 ? (bArr[i2] & ExifInterface.MARKER) << 2 : 0);
                    write(cArr[i10 >> 12]);
                    write(cArr[(i10 >>> 6) & 63]);
                    write(i9 == 2 ? cArr[i10 & 63] : '=');
                    write(61);
                }
                write(c2);
                return;
            }
            j(i4);
        }
        this.h = i4;
        int i11 = i3 + 1;
        this.g[i3] = c2;
        int i12 = 0;
        while (i12 < i) {
            int i13 = i12 + 1;
            int i14 = i13 + 1;
            int i15 = ((bArr[i12] & ExifInterface.MARKER) << 16) | ((bArr[i13] & ExifInterface.MARKER) << 8);
            int i16 = i14 + 1;
            int i17 = i15 | (bArr[i14] & ExifInterface.MARKER);
            char[] cArr2 = this.g;
            int i18 = i11 + 1;
            cArr2[i11] = cArr[(i17 >>> 18) & 63];
            int i19 = i18 + 1;
            cArr2[i18] = cArr[(i17 >>> 12) & 63];
            int i20 = i19 + 1;
            cArr2[i19] = cArr[(i17 >>> 6) & 63];
            i11 = i20 + 1;
            cArr2[i20] = cArr[i17 & 63];
            i12 = i16;
        }
        int i21 = length - i;
        if (i21 > 0) {
            int i22 = ((bArr[i] & ExifInterface.MARKER) << 10) | (i21 == 2 ? (bArr[i2] & ExifInterface.MARKER) << 2 : 0);
            char[] cArr3 = this.g;
            cArr3[i4 - 5] = cArr[i22 >> 12];
            cArr3[i4 - 4] = cArr[(i22 >>> 6) & 63];
            cArr3[i4 - 3] = i21 == 2 ? cArr[i22 & 63] : '=';
            cArr3[i4 - 2] = '=';
        }
        this.g[i4 - 1] = c2;
    }

    public void s(double d2, boolean z) {
        if (Double.isNaN(d2) || Double.isInfinite(d2)) {
            J();
            return;
        }
        int i = this.h + 24;
        if (i > this.g.length) {
            if (this.j != null) {
                String strB = b.a.a.n.i.b(d2);
                write(strB, 0, strB.length());
                if (z && l(SerializerFeature.WriteClassName)) {
                    write(68);
                    return;
                }
                return;
            }
            j(i);
        }
        this.h += b.a.a.n.i.a(d2, this.g, this.h);
        if (z && l(SerializerFeature.WriteClassName)) {
            write(68);
        }
    }

    public void t(Enum<?> r3) {
        if (r3 == null) {
            J();
            return;
        }
        String string = null;
        if (this.r && !this.s) {
            string = r3.name();
        } else if (this.s) {
            string = r3.toString();
        }
        if (string == null) {
            F(r3.ordinal());
            return;
        }
        int i = l(SerializerFeature.UseSingleQuotes) ? 39 : 34;
        write(i);
        write(string);
        write(i);
    }

    public String toString() {
        return new String(this.g, 0, this.h);
    }

    public void u(String str) {
        v(str, false);
    }

    public void v(String str, boolean z) {
        if (str == null) {
            write("null:");
            return;
        }
        if (this.k) {
            if (!this.l) {
                G(str);
                return;
            } else {
                R(str);
                write(58);
                return;
            }
        }
        if (this.l) {
            Q(str, ':');
            return;
        }
        boolean z2 = true;
        boolean z3 = str.length() == 0;
        int i = 0;
        while (true) {
            if (i >= str.length()) {
                z2 = z3;
                break;
            }
            char cCharAt = str.charAt(i);
            if ((cCharAt < '@' && (this.x & (1 << cCharAt)) != 0) || cCharAt == '\\') {
                break;
            } else {
                i++;
            }
        }
        if (z2) {
            Q(str, ':');
        } else {
            write(str);
            write(58);
        }
    }

    public void w(char c2, String str, double d2) {
        write(c2);
        u(str);
        s(d2, false);
    }

    @Override // java.io.Writer
    public void write(int i) {
        int i2 = 1;
        int i3 = this.h + 1;
        if (i3 <= this.g.length) {
            i2 = i3;
        } else if (this.j == null) {
            j(i3);
            i2 = i3;
        } else {
            flush();
        }
        this.g[this.h] = (char) i;
        this.h = i2;
    }

    public void x(char c2, String str, int i) {
        if (i == Integer.MIN_VALUE || !this.l) {
            write(c2);
            u(str);
            F(i);
            return;
        }
        int iM = i < 0 ? b.a.a.n.e.m(-i) + 1 : b.a.a.n.e.m(i);
        int length = str.length();
        int i2 = this.h + length + 4 + iM;
        if (i2 > this.g.length) {
            if (this.j != null) {
                write(c2);
                u(str);
                F(i);
                return;
            }
            j(i2);
        }
        int i3 = this.h;
        this.h = i2;
        char[] cArr = this.g;
        cArr[i3] = c2;
        int i4 = i3 + length + 1;
        cArr[i3 + 1] = this.u;
        str.getChars(0, length, cArr, i3 + 2);
        char[] cArr2 = this.g;
        cArr2[i4 + 1] = this.u;
        cArr2[i4 + 2] = ':';
        b.a.a.n.e.i(i, this.h, cArr2);
    }

    public void y(char c2, String str, long j) {
        if (j == Long.MIN_VALUE || !this.l || k(SerializerFeature.BrowserCompatible.mask)) {
            write(c2);
            u(str);
            H(j);
            return;
        }
        int iN = j < 0 ? b.a.a.n.e.n(-j) + 1 : b.a.a.n.e.n(j);
        int length = str.length();
        int i = this.h + length + 4 + iN;
        if (i > this.g.length) {
            if (this.j != null) {
                write(c2);
                u(str);
                H(j);
                return;
            }
            j(i);
        }
        int i2 = this.h;
        this.h = i;
        char[] cArr = this.g;
        cArr[i2] = c2;
        int i3 = i2 + length + 1;
        cArr[i2 + 1] = this.u;
        str.getChars(0, length, cArr, i2 + 2);
        char[] cArr2 = this.g;
        cArr2[i3 + 1] = this.u;
        cArr2[i3 + 2] = ':';
        b.a.a.n.e.j(j, this.h, cArr2);
    }

    public void z(char c2, String str, String str2) {
        if (!this.l) {
            write(c2);
            u(str);
            if (str2 == null) {
                J();
                return;
            } else {
                N(str2);
                return;
            }
        }
        if (this.k) {
            write(c2);
            u(str);
            if (str2 == null) {
                J();
                return;
            } else {
                N(str2);
                return;
            }
        }
        if (!l(SerializerFeature.BrowserCompatible)) {
            B(c2, str, str2);
            return;
        }
        write(c2);
        Q(str, ':');
        Q(str2, (char) 0);
    }

    public d1(Writer writer) {
        this(writer, b.a.a.a.DEFAULT_GENERATE_FEATURE, SerializerFeature.EMPTY);
    }

    public d1(Writer writer, int i, SerializerFeature... serializerFeatureArr) {
        this.v = -1;
        this.j = writer;
        ThreadLocal<char[]> threadLocal = f354a;
        char[] cArr = threadLocal.get();
        this.g = cArr;
        if (cArr != null) {
            threadLocal.set(null);
        } else {
            this.g = new char[2048];
        }
        for (SerializerFeature serializerFeature : serializerFeatureArr) {
            i |= serializerFeature.getMask();
        }
        this.i = i;
        f();
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i, int i2) {
        int i3;
        if (i < 0 || i > cArr.length || i2 < 0 || (i3 = i + i2) > cArr.length || i3 < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (i2 == 0) {
            return;
        }
        int i4 = this.h + i2;
        if (i4 > this.g.length) {
            if (this.j == null) {
                j(i4);
            } else {
                do {
                    char[] cArr2 = this.g;
                    int length = cArr2.length;
                    int i5 = this.h;
                    int i6 = length - i5;
                    System.arraycopy(cArr, i, cArr2, i5, i6);
                    this.h = this.g.length;
                    flush();
                    i2 -= i6;
                    i += i6;
                } while (i2 > this.g.length);
                i4 = i2;
            }
        }
        System.arraycopy(cArr, i, this.g, this.h, i2);
        this.h = i4;
    }

    @Override // java.io.Writer
    public void write(String str, int i, int i2) {
        int i3;
        int i4 = this.h + i2;
        if (i4 > this.g.length) {
            if (this.j == null) {
                j(i4);
            } else {
                while (true) {
                    char[] cArr = this.g;
                    int length = cArr.length;
                    int i5 = this.h;
                    int i6 = length - i5;
                    i3 = i + i6;
                    str.getChars(i, i3, cArr, i5);
                    this.h = this.g.length;
                    flush();
                    i2 -= i6;
                    if (i2 <= this.g.length) {
                        break;
                    } else {
                        i = i3;
                    }
                }
                i4 = i2;
                i = i3;
            }
        }
        str.getChars(i, i2 + i, this.g, this.h);
        this.h = i4;
    }

    @Override // java.io.Writer
    public void write(String str) {
        if (str == null) {
            J();
        } else {
            write(str, 0, str.length());
        }
    }
}
