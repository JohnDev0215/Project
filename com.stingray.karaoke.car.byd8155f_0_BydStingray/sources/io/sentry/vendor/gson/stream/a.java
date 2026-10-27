package io.sentry.vendor.gson.stream;

import E5.s;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class a implements Closeable {
    public final Reader l;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public long f16965t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f16966u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public String f16967v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int[] f16968w;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public String[] f16970y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int[] f16971z;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f16959m = false;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final char[] f16960n = new char[1024];
    public int o = 0;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f16961p = 0;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f16962q = 0;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f16963r = 0;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f16964s = 0;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f16969x = 1;

    public a(Reader reader) {
        int[] iArr = new int[32];
        this.f16968w = iArr;
        iArr[0] = 6;
        this.f16970y = new String[32];
        this.f16971z = new int[32];
        this.l = reader;
    }

    public final double C() throws IOException {
        int iJ = this.f16964s;
        if (iJ == 0) {
            iJ = j();
        }
        if (iJ == 15) {
            this.f16964s = 0;
            int[] iArr = this.f16971z;
            int i7 = this.f16969x - 1;
            iArr[i7] = iArr[i7] + 1;
            return this.f16965t;
        }
        if (iJ == 16) {
            this.f16967v = new String(this.f16960n, this.o, this.f16966u);
            this.o += this.f16966u;
        } else if (iJ == 8 || iJ == 9) {
            this.f16967v = M(iJ == 8 ? '\'' : '\"');
        } else if (iJ == 10) {
            this.f16967v = O();
        } else if (iJ != 11) {
            throw new IllegalStateException("Expected a double but was " + P() + y());
        }
        this.f16964s = 11;
        double d = Double.parseDouble(this.f16967v);
        if (!this.f16959m && (Double.isNaN(d) || Double.isInfinite(d))) {
            throw new s("JSON forbids NaN and infinities: " + d + y());
        }
        this.f16967v = null;
        this.f16964s = 0;
        int[] iArr2 = this.f16971z;
        int i10 = this.f16969x - 1;
        iArr2[i10] = iArr2[i10] + 1;
        return d;
    }

    public final int F() throws IOException {
        int iJ = this.f16964s;
        if (iJ == 0) {
            iJ = j();
        }
        if (iJ == 15) {
            long j3 = this.f16965t;
            int i7 = (int) j3;
            if (j3 != i7) {
                throw new NumberFormatException("Expected an int but was " + this.f16965t + y());
            }
            this.f16964s = 0;
            int[] iArr = this.f16971z;
            int i10 = this.f16969x - 1;
            iArr[i10] = iArr[i10] + 1;
            return i7;
        }
        if (iJ == 16) {
            this.f16967v = new String(this.f16960n, this.o, this.f16966u);
            this.o += this.f16966u;
        } else {
            if (iJ != 8 && iJ != 9 && iJ != 10) {
                throw new IllegalStateException("Expected an int but was " + P() + y());
            }
            if (iJ == 10) {
                this.f16967v = O();
            } else {
                this.f16967v = M(iJ == 8 ? '\'' : '\"');
            }
            try {
                int i11 = Integer.parseInt(this.f16967v);
                this.f16964s = 0;
                int[] iArr2 = this.f16971z;
                int i12 = this.f16969x - 1;
                iArr2[i12] = iArr2[i12] + 1;
                return i11;
            } catch (NumberFormatException unused) {
            }
        }
        this.f16964s = 11;
        double d = Double.parseDouble(this.f16967v);
        int i13 = (int) d;
        if (i13 != d) {
            throw new NumberFormatException("Expected an int but was " + this.f16967v + y());
        }
        this.f16967v = null;
        this.f16964s = 0;
        int[] iArr3 = this.f16971z;
        int i14 = this.f16969x - 1;
        iArr3[i14] = iArr3[i14] + 1;
        return i13;
    }

    public final long G() throws IOException {
        int iJ = this.f16964s;
        if (iJ == 0) {
            iJ = j();
        }
        if (iJ == 15) {
            this.f16964s = 0;
            int[] iArr = this.f16971z;
            int i7 = this.f16969x - 1;
            iArr[i7] = iArr[i7] + 1;
            return this.f16965t;
        }
        if (iJ == 16) {
            this.f16967v = new String(this.f16960n, this.o, this.f16966u);
            this.o += this.f16966u;
        } else {
            if (iJ != 8 && iJ != 9 && iJ != 10) {
                throw new IllegalStateException("Expected a long but was " + P() + y());
            }
            if (iJ == 10) {
                this.f16967v = O();
            } else {
                this.f16967v = M(iJ == 8 ? '\'' : '\"');
            }
            try {
                long j3 = Long.parseLong(this.f16967v);
                this.f16964s = 0;
                int[] iArr2 = this.f16971z;
                int i10 = this.f16969x - 1;
                iArr2[i10] = iArr2[i10] + 1;
                return j3;
            } catch (NumberFormatException unused) {
            }
        }
        this.f16964s = 11;
        double d = Double.parseDouble(this.f16967v);
        long j10 = (long) d;
        if (j10 != d) {
            throw new NumberFormatException("Expected a long but was " + this.f16967v + y());
        }
        this.f16967v = null;
        this.f16964s = 0;
        int[] iArr3 = this.f16971z;
        int i11 = this.f16969x - 1;
        iArr3[i11] = iArr3[i11] + 1;
        return j10;
    }

    public final String I() {
        String strM;
        int iJ = this.f16964s;
        if (iJ == 0) {
            iJ = j();
        }
        if (iJ == 14) {
            strM = O();
        } else if (iJ == 12) {
            strM = M('\'');
        } else {
            if (iJ != 13) {
                throw new IllegalStateException("Expected a name but was " + P() + y());
            }
            strM = M('\"');
        }
        this.f16964s = 0;
        this.f16970y[this.f16969x - 1] = strM;
        return strM;
    }

    public final int K(boolean z6) throws IOException {
        int i7 = this.o;
        int i10 = this.f16961p;
        while (true) {
            if (i7 == i10) {
                this.o = i7;
                if (!o(1)) {
                    if (!z6) {
                        return -1;
                    }
                    throw new EOFException("End of input" + y());
                }
                i7 = this.o;
                i10 = this.f16961p;
            }
            int i11 = i7 + 1;
            char[] cArr = this.f16960n;
            char c = cArr[i7];
            if (c == '\n') {
                this.f16962q++;
                this.f16963r = i11;
            } else if (c != ' ' && c != '\r' && c != '\t') {
                if (c == '/') {
                    this.o = i11;
                    if (i11 == i10) {
                        this.o = i7;
                        boolean zO = o(2);
                        this.o++;
                        if (!zO) {
                            return c;
                        }
                    }
                    i();
                    int i12 = this.o;
                    char c10 = cArr[i12];
                    if (c10 == '*') {
                        this.o = i12 + 1;
                        while (true) {
                            if (this.o + 2 > this.f16961p && !o(2)) {
                                U("Unterminated comment");
                                throw null;
                            }
                            int i13 = this.o;
                            if (cArr[i13] != '\n') {
                                for (int i14 = 0; i14 < 2; i14++) {
                                    if (cArr[this.o + i14] != "*/".charAt(i14)) {
                                        break;
                                    }
                                }
                                i7 = this.o + 2;
                                i10 = this.f16961p;
                                break;
                            }
                            this.f16962q++;
                            this.f16963r = i13 + 1;
                            this.o++;
                        }
                    } else {
                        if (c10 != '/') {
                            return c;
                        }
                        this.o = i12 + 1;
                        T();
                        i7 = this.o;
                        i10 = this.f16961p;
                    }
                } else {
                    if (c != '#') {
                        this.o = i11;
                        return c;
                    }
                    this.o = i11;
                    i();
                    T();
                    i7 = this.o;
                    i10 = this.f16961p;
                }
            }
            i7 = i11;
        }
    }

    public final void L() {
        int iJ = this.f16964s;
        if (iJ == 0) {
            iJ = j();
        }
        if (iJ != 7) {
            throw new IllegalStateException("Expected null but was " + P() + y());
        }
        this.f16964s = 0;
        int[] iArr = this.f16971z;
        int i7 = this.f16969x - 1;
        iArr[i7] = iArr[i7] + 1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x002d, code lost:
    
        r10.o = r8;
        r8 = r8 - r3;
        r2 = r8 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0032, code lost:
    
        if (r1 != null) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0034, code lost:
    
        r1 = new java.lang.StringBuilder(java.lang.Math.max(r8 * 2, 16));
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x005b, code lost:
    
        if (r1 != null) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x005d, code lost:
    
        r1 = new java.lang.StringBuilder(java.lang.Math.max((r2 - r3) * 2, 16));
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x006b, code lost:
    
        r1.append(r5, r3, r2 - r3);
        r10.o = r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String M(char r11) throws E5.s {
        /*
            r10 = this;
            r0 = 0
            r1 = r0
        L2:
            int r2 = r10.o
            int r3 = r10.f16961p
        L6:
            r4 = r3
            r3 = r2
        L8:
            char[] r5 = r10.f16960n
            r6 = 1
            r7 = 16
            if (r2 >= r4) goto L5b
            int r8 = r2 + 1
            char r2 = r5[r2]
            if (r2 != r11) goto L29
            r10.o = r8
            int r8 = r8 - r3
            int r8 = r8 - r6
            if (r1 != 0) goto L21
            java.lang.String r10 = new java.lang.String
            r10.<init>(r5, r3, r8)
            return r10
        L21:
            r1.append(r5, r3, r8)
            java.lang.String r10 = r1.toString()
            return r10
        L29:
            r9 = 92
            if (r2 != r9) goto L4e
            r10.o = r8
            int r8 = r8 - r3
            int r2 = r8 + (-1)
            if (r1 != 0) goto L3f
            int r8 = r8 * 2
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            int r4 = java.lang.Math.max(r8, r7)
            r1.<init>(r4)
        L3f:
            r1.append(r5, r3, r2)
            char r2 = r10.R()
            r1.append(r2)
            int r2 = r10.o
            int r3 = r10.f16961p
            goto L6
        L4e:
            r5 = 10
            if (r2 != r5) goto L59
            int r2 = r10.f16962q
            int r2 = r2 + r6
            r10.f16962q = r2
            r10.f16963r = r8
        L59:
            r2 = r8
            goto L8
        L5b:
            if (r1 != 0) goto L6b
            int r1 = r2 - r3
            int r1 = r1 * 2
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            int r1 = java.lang.Math.max(r1, r7)
            r4.<init>(r1)
            r1 = r4
        L6b:
            int r4 = r2 - r3
            r1.append(r5, r3, r4)
            r10.o = r2
            boolean r2 = r10.o(r6)
            if (r2 == 0) goto L79
            goto L2
        L79:
            java.lang.String r11 = "Unterminated string"
            r10.U(r11)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.vendor.gson.stream.a.M(char):java.lang.String");
    }

    public final String N() {
        String str;
        int iJ = this.f16964s;
        if (iJ == 0) {
            iJ = j();
        }
        if (iJ == 10) {
            str = O();
        } else if (iJ == 8) {
            str = M('\'');
        } else if (iJ == 9) {
            str = M('\"');
        } else if (iJ == 11) {
            str = this.f16967v;
            this.f16967v = null;
        } else if (iJ == 15) {
            str = Long.toString(this.f16965t);
        } else {
            if (iJ != 16) {
                throw new IllegalStateException("Expected a string but was " + P() + y());
            }
            str = new String(this.f16960n, this.o, this.f16966u);
            this.o += this.f16966u;
        }
        this.f16964s = 0;
        int[] iArr = this.f16971z;
        int i7 = this.f16969x - 1;
        iArr[i7] = iArr[i7] + 1;
        return str;
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x004a, code lost:
    
        i();
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:32:0x0044. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:46:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0084  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String O() throws E5.s {
        /*
            r7 = this;
            r0 = 0
            r1 = 0
        L2:
            r2 = r1
        L3:
            int r3 = r7.o
            int r4 = r3 + r2
            int r5 = r7.f16961p
            char[] r6 = r7.f16960n
            if (r4 >= r5) goto L4e
            int r3 = r3 + r2
            char r3 = r6[r3]
            r4 = 9
            if (r3 == r4) goto L5a
            r4 = 10
            if (r3 == r4) goto L5a
            r4 = 12
            if (r3 == r4) goto L5a
            r4 = 13
            if (r3 == r4) goto L5a
            r4 = 32
            if (r3 == r4) goto L5a
            r4 = 35
            if (r3 == r4) goto L4a
            r4 = 44
            if (r3 == r4) goto L5a
            r4 = 47
            if (r3 == r4) goto L4a
            r4 = 61
            if (r3 == r4) goto L4a
            r4 = 123(0x7b, float:1.72E-43)
            if (r3 == r4) goto L5a
            r4 = 125(0x7d, float:1.75E-43)
            if (r3 == r4) goto L5a
            r4 = 58
            if (r3 == r4) goto L5a
            r4 = 59
            if (r3 == r4) goto L4a
            switch(r3) {
                case 91: goto L5a;
                case 92: goto L4a;
                case 93: goto L5a;
                default: goto L47;
            }
        L47:
            int r2 = r2 + 1
            goto L3
        L4a:
            r7.i()
            goto L5a
        L4e:
            int r3 = r6.length
            if (r2 >= r3) goto L5c
            int r3 = r2 + 1
            boolean r3 = r7.o(r3)
            if (r3 == 0) goto L5a
            goto L3
        L5a:
            r1 = r2
            goto L7a
        L5c:
            if (r0 != 0) goto L69
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r3 = 16
            int r3 = java.lang.Math.max(r2, r3)
            r0.<init>(r3)
        L69:
            int r3 = r7.o
            r0.append(r6, r3, r2)
            int r3 = r7.o
            int r3 = r3 + r2
            r7.o = r3
            r2 = 1
            boolean r2 = r7.o(r2)
            if (r2 != 0) goto L2
        L7a:
            if (r0 != 0) goto L84
            java.lang.String r0 = new java.lang.String
            int r2 = r7.o
            r0.<init>(r6, r2, r1)
            goto L8d
        L84:
            int r2 = r7.o
            r0.append(r6, r2, r1)
            java.lang.String r0 = r0.toString()
        L8d:
            int r2 = r7.o
            int r2 = r2 + r1
            r7.o = r2
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.vendor.gson.stream.a.O():java.lang.String");
    }

    public final b P() throws IOException {
        int iJ = this.f16964s;
        if (iJ == 0) {
            iJ = j();
        }
        switch (iJ) {
            case 1:
                return b.BEGIN_OBJECT;
            case 2:
                return b.END_OBJECT;
            case 3:
                return b.BEGIN_ARRAY;
            case 4:
                return b.END_ARRAY;
            case 5:
            case 6:
                return b.BOOLEAN;
            case 7:
                return b.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return b.STRING;
            case 12:
            case 13:
            case 14:
                return b.NAME;
            case 15:
            case 16:
                return b.NUMBER;
            case 17:
                return b.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    public final void Q(int i7) {
        int i10 = this.f16969x;
        int[] iArr = this.f16968w;
        if (i10 == iArr.length) {
            int i11 = i10 * 2;
            this.f16968w = Arrays.copyOf(iArr, i11);
            this.f16971z = Arrays.copyOf(this.f16971z, i11);
            this.f16970y = (String[]) Arrays.copyOf(this.f16970y, i11);
        }
        int[] iArr2 = this.f16968w;
        int i12 = this.f16969x;
        this.f16969x = i12 + 1;
        iArr2[i12] = i7;
    }

    public final char R() throws s {
        int i7;
        if (this.o == this.f16961p && !o(1)) {
            U("Unterminated escape sequence");
            throw null;
        }
        int i10 = this.o;
        int i11 = i10 + 1;
        this.o = i11;
        char[] cArr = this.f16960n;
        char c = cArr[i10];
        if (c == '\n') {
            this.f16962q++;
            this.f16963r = i11;
        } else if (c != '\"' && c != '\'' && c != '/' && c != '\\') {
            if (c == 'b') {
                return '\b';
            }
            if (c == 'f') {
                return '\f';
            }
            if (c == 'n') {
                return '\n';
            }
            if (c == 'r') {
                return '\r';
            }
            if (c == 't') {
                return '\t';
            }
            if (c != 'u') {
                U("Invalid escape sequence");
                throw null;
            }
            if (i10 + 5 > this.f16961p && !o(4)) {
                U("Unterminated escape sequence");
                throw null;
            }
            int i12 = this.o;
            int i13 = i12 + 4;
            char c10 = 0;
            while (i12 < i13) {
                char c11 = cArr[i12];
                char c12 = (char) (c10 << 4);
                if (c11 >= '0' && c11 <= '9') {
                    i7 = c11 - '0';
                } else if (c11 >= 'a' && c11 <= 'f') {
                    i7 = c11 - 'W';
                } else {
                    if (c11 < 'A' || c11 > 'F') {
                        throw new NumberFormatException("\\u".concat(new String(cArr, this.o, 4)));
                    }
                    i7 = c11 - '7';
                }
                c10 = (char) (i7 + c12);
                i12++;
            }
            this.o += 4;
            return c10;
        }
        return c;
    }

    public final void S(char c) throws s {
        do {
            int i7 = this.o;
            int i10 = this.f16961p;
            while (i7 < i10) {
                int i11 = i7 + 1;
                char c10 = this.f16960n[i7];
                if (c10 == c) {
                    this.o = i11;
                    return;
                }
                if (c10 == '\\') {
                    this.o = i11;
                    R();
                    i7 = this.o;
                    i10 = this.f16961p;
                } else {
                    if (c10 == '\n') {
                        this.f16962q++;
                        this.f16963r = i11;
                    }
                    i7 = i11;
                }
            }
            this.o = i7;
        } while (o(1));
        U("Unterminated string");
        throw null;
    }

    public final void T() {
        char c;
        do {
            if (this.o >= this.f16961p && !o(1)) {
                return;
            }
            int i7 = this.o;
            int i10 = i7 + 1;
            this.o = i10;
            c = this.f16960n[i7];
            if (c == '\n') {
                this.f16962q++;
                this.f16963r = i10;
                return;
            }
        } while (c != '\r');
    }

    public final void U(String str) throws s {
        StringBuilder sbI = M5.b.i(str);
        sbI.append(y());
        throw new s(sbI.toString());
    }

    public final void b() {
        int iJ = this.f16964s;
        if (iJ == 0) {
            iJ = j();
        }
        if (iJ == 3) {
            Q(1);
            this.f16971z[this.f16969x - 1] = 0;
            this.f16964s = 0;
        } else {
            throw new IllegalStateException("Expected BEGIN_ARRAY but was " + P() + y());
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f16964s = 0;
        this.f16968w[0] = 8;
        this.f16969x = 1;
        this.l.close();
    }

    public final void g() {
        int iJ = this.f16964s;
        if (iJ == 0) {
            iJ = j();
        }
        if (iJ == 1) {
            Q(3);
            this.f16964s = 0;
        } else {
            throw new IllegalStateException("Expected BEGIN_OBJECT but was " + P() + y());
        }
    }

    public final void i() throws s {
        if (this.f16959m) {
            return;
        }
        U("Use JsonReader.setLenient(true) to accept malformed JSON");
        throw null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:167:0x0211, code lost:
    
        if (v(r1) != false) goto L121;
     */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0179 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:116:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0269  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x0279 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:208:0x027a  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x02c3  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00e4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int j() throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 813
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.vendor.gson.stream.a.j():int");
    }

    public final void k() {
        int iJ = this.f16964s;
        if (iJ == 0) {
            iJ = j();
        }
        if (iJ != 4) {
            throw new IllegalStateException("Expected END_ARRAY but was " + P() + y());
        }
        int i7 = this.f16969x;
        this.f16969x = i7 - 1;
        int[] iArr = this.f16971z;
        int i10 = i7 - 2;
        iArr[i10] = iArr[i10] + 1;
        this.f16964s = 0;
    }

    public final void m() {
        int iJ = this.f16964s;
        if (iJ == 0) {
            iJ = j();
        }
        if (iJ != 2) {
            throw new IllegalStateException("Expected END_OBJECT but was " + P() + y());
        }
        int i7 = this.f16969x;
        int i10 = i7 - 1;
        this.f16969x = i10;
        this.f16970y[i10] = null;
        int[] iArr = this.f16971z;
        int i11 = i7 - 2;
        iArr[i11] = iArr[i11] + 1;
        this.f16964s = 0;
    }

    public final boolean o(int i7) throws IOException {
        int i10;
        int i11;
        int i12 = this.f16963r;
        int i13 = this.o;
        this.f16963r = i12 - i13;
        int i14 = this.f16961p;
        char[] cArr = this.f16960n;
        if (i14 != i13) {
            int i15 = i14 - i13;
            this.f16961p = i15;
            System.arraycopy(cArr, i13, cArr, 0, i15);
        } else {
            this.f16961p = 0;
        }
        this.o = 0;
        do {
            int i16 = this.f16961p;
            int i17 = this.l.read(cArr, i16, cArr.length - i16);
            if (i17 == -1) {
                return false;
            }
            i10 = this.f16961p + i17;
            this.f16961p = i10;
            if (this.f16962q == 0 && (i11 = this.f16963r) == 0 && i10 > 0 && cArr[0] == 65279) {
                this.o++;
                this.f16963r = i11 + 1;
                i7++;
            }
        } while (i10 < i7);
        return true;
    }

    public final boolean q() throws IOException {
        int iJ = this.f16964s;
        if (iJ == 0) {
            iJ = j();
        }
        return (iJ == 2 || iJ == 4) ? false : true;
    }

    public final String toString() {
        return a.class.getSimpleName() + y();
    }

    public final boolean v(char c) throws s {
        if (c == '\t' || c == '\n' || c == '\f' || c == '\r' || c == ' ') {
            return false;
        }
        if (c != '#') {
            if (c == ',') {
                return false;
            }
            if (c != '/' && c != '=') {
                if (c == '{' || c == '}' || c == ':') {
                    return false;
                }
                if (c != ';') {
                    switch (c) {
                        case '[':
                        case ']':
                            return false;
                        case '\\':
                            break;
                        default:
                            return true;
                    }
                }
            }
        }
        i();
        return false;
    }

    public final String y() {
        StringBuilder sbL = M5.b.l(" at line ", " column ", this.f16962q + 1, (this.o - this.f16963r) + 1, " path ");
        StringBuilder sb = new StringBuilder("$");
        int i7 = this.f16969x;
        for (int i10 = 0; i10 < i7; i10++) {
            int i11 = this.f16968w[i10];
            if (i11 == 1 || i11 == 2) {
                sb.append('[');
                sb.append(this.f16971z[i10]);
                sb.append(']');
            } else if (i11 == 3 || i11 == 4 || i11 == 5) {
                sb.append('.');
                String str = this.f16970y[i10];
                if (str != null) {
                    sb.append(str);
                }
            }
        }
        sbL.append(sb.toString());
        return sbL.toString();
    }

    public final boolean z() throws IOException {
        int iJ = this.f16964s;
        if (iJ == 0) {
            iJ = j();
        }
        if (iJ == 5) {
            this.f16964s = 0;
            int[] iArr = this.f16971z;
            int i7 = this.f16969x - 1;
            iArr[i7] = iArr[i7] + 1;
            return true;
        }
        if (iJ != 6) {
            throw new IllegalStateException("Expected a boolean but was " + P() + y());
        }
        this.f16964s = 0;
        int[] iArr2 = this.f16971z;
        int i10 = this.f16969x - 1;
        iArr2[i10] = iArr2[i10] + 1;
        return false;
    }
}
