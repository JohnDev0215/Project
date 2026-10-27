package r5;

import h2.AbstractC1347d;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import n0.C1886o;

/* JADX INFO: renamed from: r5.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C2297a implements Closeable {
    public final StringReader l;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public long f21850s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f21851t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public String f21852u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int[] f21853v;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public String[] f21855x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int[] f21856y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f21857z = 2;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final char[] f21845m = new char[1024];

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f21846n = 0;
    public int o = 0;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f21847p = 0;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f21848q = 0;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f21849r = 0;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f21854w = 1;

    static {
        C1886o.f19443m = new C1886o(7);
    }

    public C2297a(StringReader stringReader) {
        int[] iArr = new int[32];
        this.f21853v = iArr;
        iArr[0] = 6;
        this.f21855x = new String[32];
        this.f21856y = new int[32];
        this.l = stringReader;
    }

    public final boolean C() throws IOException {
        int iJ = this.f21849r;
        if (iJ == 0) {
            iJ = j();
        }
        if (iJ == 5) {
            this.f21849r = 0;
            int[] iArr = this.f21856y;
            int i7 = this.f21854w - 1;
            iArr[i7] = iArr[i7] + 1;
            return true;
        }
        if (iJ != 6) {
            throw Y("a boolean");
        }
        this.f21849r = 0;
        int[] iArr2 = this.f21856y;
        int i10 = this.f21854w - 1;
        iArr2[i10] = iArr2[i10] + 1;
        return false;
    }

    public final double F() throws IOException {
        int iJ = this.f21849r;
        if (iJ == 0) {
            iJ = j();
        }
        if (iJ == 15) {
            this.f21849r = 0;
            int[] iArr = this.f21856y;
            int i7 = this.f21854w - 1;
            iArr[i7] = iArr[i7] + 1;
            return this.f21850s;
        }
        if (iJ == 16) {
            this.f21852u = new String(this.f21845m, this.f21846n, this.f21851t);
            this.f21846n += this.f21851t;
        } else if (iJ == 8 || iJ == 9) {
            this.f21852u = N(iJ == 8 ? '\'' : '\"');
        } else if (iJ == 10) {
            this.f21852u = P();
        } else if (iJ != 11) {
            throw Y("a double");
        }
        this.f21849r = 11;
        double d = Double.parseDouble(this.f21852u);
        if (this.f21857z != 1 && (Double.isNaN(d) || Double.isInfinite(d))) {
            X("JSON forbids NaN and infinities: " + d);
            throw null;
        }
        this.f21852u = null;
        this.f21849r = 0;
        int[] iArr2 = this.f21856y;
        int i10 = this.f21854w - 1;
        iArr2[i10] = iArr2[i10] + 1;
        return d;
    }

    public final int G() throws IOException {
        int iJ = this.f21849r;
        if (iJ == 0) {
            iJ = j();
        }
        if (iJ == 15) {
            long j3 = this.f21850s;
            int i7 = (int) j3;
            if (j3 != i7) {
                throw new NumberFormatException("Expected an int but was " + this.f21850s + z());
            }
            this.f21849r = 0;
            int[] iArr = this.f21856y;
            int i10 = this.f21854w - 1;
            iArr[i10] = iArr[i10] + 1;
            return i7;
        }
        if (iJ == 16) {
            this.f21852u = new String(this.f21845m, this.f21846n, this.f21851t);
            this.f21846n += this.f21851t;
        } else {
            if (iJ != 8 && iJ != 9 && iJ != 10) {
                throw Y("an int");
            }
            if (iJ == 10) {
                this.f21852u = P();
            } else {
                this.f21852u = N(iJ == 8 ? '\'' : '\"');
            }
            try {
                int i11 = Integer.parseInt(this.f21852u);
                this.f21849r = 0;
                int[] iArr2 = this.f21856y;
                int i12 = this.f21854w - 1;
                iArr2[i12] = iArr2[i12] + 1;
                return i11;
            } catch (NumberFormatException unused) {
            }
        }
        this.f21849r = 11;
        double d = Double.parseDouble(this.f21852u);
        int i13 = (int) d;
        if (i13 != d) {
            throw new NumberFormatException("Expected an int but was " + this.f21852u + z());
        }
        this.f21852u = null;
        this.f21849r = 0;
        int[] iArr3 = this.f21856y;
        int i14 = this.f21854w - 1;
        iArr3[i14] = iArr3[i14] + 1;
        return i13;
    }

    public final long I() throws IOException {
        int iJ = this.f21849r;
        if (iJ == 0) {
            iJ = j();
        }
        if (iJ == 15) {
            this.f21849r = 0;
            int[] iArr = this.f21856y;
            int i7 = this.f21854w - 1;
            iArr[i7] = iArr[i7] + 1;
            return this.f21850s;
        }
        if (iJ == 16) {
            this.f21852u = new String(this.f21845m, this.f21846n, this.f21851t);
            this.f21846n += this.f21851t;
        } else {
            if (iJ != 8 && iJ != 9 && iJ != 10) {
                throw Y("a long");
            }
            if (iJ == 10) {
                this.f21852u = P();
            } else {
                this.f21852u = N(iJ == 8 ? '\'' : '\"');
            }
            try {
                long j3 = Long.parseLong(this.f21852u);
                this.f21849r = 0;
                int[] iArr2 = this.f21856y;
                int i10 = this.f21854w - 1;
                iArr2[i10] = iArr2[i10] + 1;
                return j3;
            } catch (NumberFormatException unused) {
            }
        }
        this.f21849r = 11;
        double d = Double.parseDouble(this.f21852u);
        long j10 = (long) d;
        if (j10 != d) {
            throw new NumberFormatException("Expected a long but was " + this.f21852u + z());
        }
        this.f21852u = null;
        this.f21849r = 0;
        int[] iArr3 = this.f21856y;
        int i11 = this.f21854w - 1;
        iArr3[i11] = iArr3[i11] + 1;
        return j10;
    }

    public final String K() throws IOException {
        String strN;
        int iJ = this.f21849r;
        if (iJ == 0) {
            iJ = j();
        }
        if (iJ == 14) {
            strN = P();
        } else if (iJ == 12) {
            strN = N('\'');
        } else {
            if (iJ != 13) {
                throw Y("a name");
            }
            strN = N('\"');
        }
        this.f21849r = 0;
        this.f21855x[this.f21854w - 1] = strN;
        return strN;
    }

    public final int L(boolean z6) throws IOException {
        int i7 = this.f21846n;
        int i10 = this.o;
        while (true) {
            if (i7 == i10) {
                this.f21846n = i7;
                if (!o(1)) {
                    if (!z6) {
                        return -1;
                    }
                    throw new EOFException("End of input" + z());
                }
                i7 = this.f21846n;
                i10 = this.o;
            }
            int i11 = i7 + 1;
            char[] cArr = this.f21845m;
            char c = cArr[i7];
            if (c == '\n') {
                this.f21847p++;
                this.f21848q = i11;
            } else if (c != ' ' && c != '\r' && c != '\t') {
                if (c == '/') {
                    this.f21846n = i11;
                    if (i11 == i10) {
                        this.f21846n = i7;
                        boolean zO = o(2);
                        this.f21846n++;
                        if (!zO) {
                            return c;
                        }
                    }
                    i();
                    int i12 = this.f21846n;
                    char c10 = cArr[i12];
                    if (c10 == '*') {
                        this.f21846n = i12 + 1;
                        while (true) {
                            if (this.f21846n + 2 > this.o && !o(2)) {
                                X("Unterminated comment");
                                throw null;
                            }
                            int i13 = this.f21846n;
                            if (cArr[i13] != '\n') {
                                for (int i14 = 0; i14 < 2; i14++) {
                                    if (cArr[this.f21846n + i14] != "*/".charAt(i14)) {
                                        break;
                                    }
                                }
                                i7 = this.f21846n + 2;
                                i10 = this.o;
                                break;
                            }
                            this.f21847p++;
                            this.f21848q = i13 + 1;
                            this.f21846n++;
                        }
                    } else {
                        if (c10 != '/') {
                            return c;
                        }
                        this.f21846n = i12 + 1;
                        U();
                        i7 = this.f21846n;
                        i10 = this.o;
                    }
                } else {
                    if (c != '#') {
                        this.f21846n = i11;
                        return c;
                    }
                    this.f21846n = i11;
                    i();
                    U();
                    i7 = this.f21846n;
                    i10 = this.o;
                }
            }
            i7 = i11;
        }
    }

    public final void M() throws IOException {
        int iJ = this.f21849r;
        if (iJ == 0) {
            iJ = j();
        }
        if (iJ != 7) {
            throw Y("null");
        }
        this.f21849r = 0;
        int[] iArr = this.f21856y;
        int i7 = this.f21854w - 1;
        iArr[i7] = iArr[i7] + 1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x003d, code lost:
    
        r11.f21846n = r8;
        r8 = r8 - r3;
        r2 = r8 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0042, code lost:
    
        if (r1 != null) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0044, code lost:
    
        r1 = new java.lang.StringBuilder(java.lang.Math.max(r8 * 2, 16));
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x006b, code lost:
    
        if (r1 != null) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x006d, code lost:
    
        r1 = new java.lang.StringBuilder(java.lang.Math.max((r2 - r3) * 2, 16));
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x007b, code lost:
    
        r1.append(r5, r3, r2 - r3);
        r11.f21846n = r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String N(char r12) throws r5.C2299c {
        /*
            r11 = this;
            r0 = 0
            r1 = r0
        L2:
            int r2 = r11.f21846n
            int r3 = r11.o
        L6:
            r4 = r3
            r3 = r2
        L8:
            char[] r5 = r11.f21845m
            r6 = 1
            r7 = 16
            if (r2 >= r4) goto L6b
            int r8 = r2 + 1
            char r2 = r5[r2]
            int r9 = r11.f21857z
            r10 = 3
            if (r9 != r10) goto L23
            r9 = 32
            if (r2 < r9) goto L1d
            goto L23
        L1d:
            java.lang.String r12 = "Unescaped control characters (\\u0000-\\u001F) are not allowed in strict mode"
            r11.X(r12)
            throw r0
        L23:
            if (r2 != r12) goto L39
            r11.f21846n = r8
            int r8 = r8 - r3
            int r8 = r8 - r6
            if (r1 != 0) goto L31
            java.lang.String r11 = new java.lang.String
            r11.<init>(r5, r3, r8)
            return r11
        L31:
            r1.append(r5, r3, r8)
            java.lang.String r11 = r1.toString()
            return r11
        L39:
            r9 = 92
            if (r2 != r9) goto L5e
            r11.f21846n = r8
            int r8 = r8 - r3
            int r2 = r8 + (-1)
            if (r1 != 0) goto L4f
            int r8 = r8 * 2
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            int r4 = java.lang.Math.max(r8, r7)
            r1.<init>(r4)
        L4f:
            r1.append(r5, r3, r2)
            char r2 = r11.S()
            r1.append(r2)
            int r2 = r11.f21846n
            int r3 = r11.o
            goto L6
        L5e:
            r5 = 10
            if (r2 != r5) goto L69
            int r2 = r11.f21847p
            int r2 = r2 + r6
            r11.f21847p = r2
            r11.f21848q = r8
        L69:
            r2 = r8
            goto L8
        L6b:
            if (r1 != 0) goto L7b
            int r1 = r2 - r3
            int r1 = r1 * 2
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            int r1 = java.lang.Math.max(r1, r7)
            r4.<init>(r1)
            r1 = r4
        L7b:
            int r4 = r2 - r3
            r1.append(r5, r3, r4)
            r11.f21846n = r2
            boolean r2 = r11.o(r6)
            if (r2 == 0) goto L8a
            goto L2
        L8a:
            java.lang.String r12 = "Unterminated string"
            r11.X(r12)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: r5.C2297a.N(char):java.lang.String");
    }

    public final String O() throws IOException {
        String str;
        int iJ = this.f21849r;
        if (iJ == 0) {
            iJ = j();
        }
        if (iJ == 10) {
            str = P();
        } else if (iJ == 8) {
            str = N('\'');
        } else if (iJ == 9) {
            str = N('\"');
        } else if (iJ == 11) {
            str = this.f21852u;
            this.f21852u = null;
        } else if (iJ == 15) {
            str = Long.toString(this.f21850s);
        } else {
            if (iJ != 16) {
                throw Y("a string");
            }
            str = new String(this.f21845m, this.f21846n, this.f21851t);
            this.f21846n += this.f21851t;
        }
        this.f21849r = 0;
        int[] iArr = this.f21856y;
        int i7 = this.f21854w - 1;
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
    public final java.lang.String P() throws r5.C2299c {
        /*
            r7 = this;
            r0 = 0
            r1 = 0
        L2:
            r2 = r1
        L3:
            int r3 = r7.f21846n
            int r4 = r3 + r2
            int r5 = r7.o
            char[] r6 = r7.f21845m
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
            int r3 = r7.f21846n
            r0.append(r6, r3, r2)
            int r3 = r7.f21846n
            int r3 = r3 + r2
            r7.f21846n = r3
            r2 = 1
            boolean r2 = r7.o(r2)
            if (r2 != 0) goto L2
        L7a:
            if (r0 != 0) goto L84
            java.lang.String r0 = new java.lang.String
            int r2 = r7.f21846n
            r0.<init>(r6, r2, r1)
            goto L8d
        L84:
            int r2 = r7.f21846n
            r0.append(r6, r2, r1)
            java.lang.String r0 = r0.toString()
        L8d:
            int r2 = r7.f21846n
            int r2 = r2 + r1
            r7.f21846n = r2
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: r5.C2297a.P():java.lang.String");
    }

    public final int Q() throws IOException {
        int iJ = this.f21849r;
        if (iJ == 0) {
            iJ = j();
        }
        switch (iJ) {
            case 1:
                return 3;
            case 2:
                return 4;
            case 3:
                return 1;
            case 4:
                return 2;
            case 5:
            case 6:
                return 8;
            case 7:
                return 9;
            case 8:
            case 9:
            case 10:
            case 11:
                return 6;
            case 12:
            case 13:
            case 14:
                return 5;
            case 15:
            case 16:
                return 7;
            case 17:
                return 10;
            default:
                throw new AssertionError();
        }
    }

    public final void R(int i7) {
        int i10 = this.f21854w;
        int[] iArr = this.f21853v;
        if (i10 == iArr.length) {
            int i11 = i10 * 2;
            this.f21853v = Arrays.copyOf(iArr, i11);
            this.f21856y = Arrays.copyOf(this.f21856y, i11);
            this.f21855x = (String[]) Arrays.copyOf(this.f21855x, i11);
        }
        int[] iArr2 = this.f21853v;
        int i12 = this.f21854w;
        this.f21854w = i12 + 1;
        iArr2[i12] = i7;
    }

    public final char S() throws C2299c {
        int i7;
        if (this.f21846n == this.o && !o(1)) {
            X("Unterminated escape sequence");
            throw null;
        }
        int i10 = this.f21846n;
        int i11 = i10 + 1;
        this.f21846n = i11;
        char[] cArr = this.f21845m;
        char c = cArr[i10];
        if (c != '\n') {
            if (c != '\"') {
                if (c != '\'') {
                    if (c != '/' && c != '\\') {
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
                            X("Invalid escape sequence");
                            throw null;
                        }
                        if (i10 + 5 > this.o && !o(4)) {
                            X("Unterminated escape sequence");
                            throw null;
                        }
                        int i12 = this.f21846n;
                        int i13 = i12 + 4;
                        int i14 = 0;
                        while (i12 < i13) {
                            char c10 = cArr[i12];
                            int i15 = i14 << 4;
                            if (c10 >= '0' && c10 <= '9') {
                                i7 = c10 - '0';
                            } else if (c10 >= 'a' && c10 <= 'f') {
                                i7 = c10 - 'W';
                            } else {
                                if (c10 < 'A' || c10 > 'F') {
                                    X("Malformed Unicode escape \\u".concat(new String(cArr, this.f21846n, 4)));
                                    throw null;
                                }
                                i7 = c10 - '7';
                            }
                            i14 = i7 + i15;
                            i12++;
                        }
                        this.f21846n += 4;
                        return (char) i14;
                    }
                }
            }
            return c;
        }
        if (this.f21857z == 3) {
            X("Cannot escape a newline character in strict mode");
            throw null;
        }
        this.f21847p++;
        this.f21848q = i11;
        if (this.f21857z == 3) {
            X("Invalid escaped character \"'\" in strict mode");
            throw null;
        }
        return c;
    }

    public final void T(char c) throws C2299c {
        do {
            int i7 = this.f21846n;
            int i10 = this.o;
            while (i7 < i10) {
                int i11 = i7 + 1;
                char c10 = this.f21845m[i7];
                if (c10 == c) {
                    this.f21846n = i11;
                    return;
                }
                if (c10 == '\\') {
                    this.f21846n = i11;
                    S();
                    i7 = this.f21846n;
                    i10 = this.o;
                } else {
                    if (c10 == '\n') {
                        this.f21847p++;
                        this.f21848q = i11;
                    }
                    i7 = i11;
                }
            }
            this.f21846n = i7;
        } while (o(1));
        X("Unterminated string");
        throw null;
    }

    public final void U() {
        char c;
        do {
            if (this.f21846n >= this.o && !o(1)) {
                return;
            }
            int i7 = this.f21846n;
            int i10 = i7 + 1;
            this.f21846n = i10;
            c = this.f21845m[i7];
            if (c == '\n') {
                this.f21847p++;
                this.f21848q = i10;
                return;
            }
        } while (c != '\r');
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0048, code lost:
    
        i();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void V() throws r5.C2299c {
        /*
            r4 = this;
        L0:
            r0 = 0
        L1:
            int r1 = r4.f21846n
            int r2 = r1 + r0
            int r3 = r4.o
            if (r2 >= r3) goto L51
            char[] r2 = r4.f21845m
            int r1 = r1 + r0
            char r1 = r2[r1]
            r2 = 9
            if (r1 == r2) goto L4b
            r2 = 10
            if (r1 == r2) goto L4b
            r2 = 12
            if (r1 == r2) goto L4b
            r2 = 13
            if (r1 == r2) goto L4b
            r2 = 32
            if (r1 == r2) goto L4b
            r2 = 35
            if (r1 == r2) goto L48
            r2 = 44
            if (r1 == r2) goto L4b
            r2 = 47
            if (r1 == r2) goto L48
            r2 = 61
            if (r1 == r2) goto L48
            r2 = 123(0x7b, float:1.72E-43)
            if (r1 == r2) goto L4b
            r2 = 125(0x7d, float:1.75E-43)
            if (r1 == r2) goto L4b
            r2 = 58
            if (r1 == r2) goto L4b
            r2 = 59
            if (r1 == r2) goto L48
            switch(r1) {
                case 91: goto L4b;
                case 92: goto L48;
                case 93: goto L4b;
                default: goto L45;
            }
        L45:
            int r0 = r0 + 1
            goto L1
        L48:
            r4.i()
        L4b:
            int r1 = r4.f21846n
            int r1 = r1 + r0
            r4.f21846n = r1
            return
        L51:
            int r1 = r1 + r0
            r4.f21846n = r1
            r0 = 1
            boolean r0 = r4.o(r0)
            if (r0 != 0) goto L0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: r5.C2297a.V():void");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final void W() throws IOException {
        int i7 = 0;
        do {
            int iJ = this.f21849r;
            if (iJ == 0) {
                iJ = j();
            }
            switch (iJ) {
                case 1:
                    R(3);
                    i7++;
                    this.f21849r = 0;
                    break;
                case 2:
                    if (i7 == 0) {
                        this.f21855x[this.f21854w - 1] = null;
                    }
                    this.f21854w--;
                    i7--;
                    this.f21849r = 0;
                    break;
                case 3:
                    R(1);
                    i7++;
                    this.f21849r = 0;
                    break;
                case 4:
                    this.f21854w--;
                    i7--;
                    this.f21849r = 0;
                    break;
                case 5:
                case 6:
                case 7:
                case 11:
                case 15:
                default:
                    this.f21849r = 0;
                    break;
                case 8:
                    T('\'');
                    this.f21849r = 0;
                    break;
                case 9:
                    T('\"');
                    this.f21849r = 0;
                    break;
                case 10:
                    V();
                    this.f21849r = 0;
                    break;
                case 12:
                    T('\'');
                    if (i7 == 0) {
                        this.f21855x[this.f21854w - 1] = "<skipped>";
                    }
                    this.f21849r = 0;
                    break;
                case 13:
                    T('\"');
                    if (i7 == 0) {
                        this.f21855x[this.f21854w - 1] = "<skipped>";
                    }
                    this.f21849r = 0;
                    break;
                case 14:
                    V();
                    if (i7 == 0) {
                        this.f21855x[this.f21854w - 1] = "<skipped>";
                    }
                    this.f21849r = 0;
                    break;
                case 16:
                    this.f21846n += this.f21851t;
                    this.f21849r = 0;
                    break;
                case 17:
                    break;
            }
            return;
        } while (i7 > 0);
        int[] iArr = this.f21856y;
        int i10 = this.f21854w - 1;
        iArr[i10] = iArr[i10] + 1;
    }

    public final void X(String str) throws C2299c {
        StringBuilder sbI = M5.b.i(str);
        sbI.append(z());
        sbI.append("\nSee ");
        sbI.append("https://github.com/google/gson/blob/main/Troubleshooting.md#".concat("malformed-json"));
        throw new C2299c(sbI.toString());
    }

    public final IllegalStateException Y(String str) {
        String str2 = Q() == 9 ? "adapter-not-null-safe" : "unexpected-json-structure";
        StringBuilder sbJ = AbstractC1347d.j("Expected ", str, " but was ");
        sbJ.append(AbstractC1347d.v(Q()));
        sbJ.append(z());
        sbJ.append("\nSee ");
        sbJ.append("https://github.com/google/gson/blob/main/Troubleshooting.md#".concat(str2));
        return new IllegalStateException(sbJ.toString());
    }

    public final void b() throws IOException {
        int iJ = this.f21849r;
        if (iJ == 0) {
            iJ = j();
        }
        if (iJ != 3) {
            throw Y("BEGIN_ARRAY");
        }
        R(1);
        this.f21856y[this.f21854w - 1] = 0;
        this.f21849r = 0;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.f21849r = 0;
        this.f21853v[0] = 8;
        this.f21854w = 1;
        this.l.close();
    }

    public final void g() throws IOException {
        int iJ = this.f21849r;
        if (iJ == 0) {
            iJ = j();
        }
        if (iJ != 1) {
            throw Y("BEGIN_OBJECT");
        }
        R(3);
        this.f21849r = 0;
    }

    public final void i() throws C2299c {
        if (this.f21857z == 1) {
            return;
        }
        X("Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed JSON");
        throw null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:171:0x0219, code lost:
    
        if (y(r1) != false) goto L125;
     */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0184 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x026f  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x027f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:213:0x0280  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x02ca  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00e6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int j() throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 823
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: r5.C2297a.j():int");
    }

    public final void k() throws IOException {
        int iJ = this.f21849r;
        if (iJ == 0) {
            iJ = j();
        }
        if (iJ != 4) {
            throw Y("END_ARRAY");
        }
        int i7 = this.f21854w;
        this.f21854w = i7 - 1;
        int[] iArr = this.f21856y;
        int i10 = i7 - 2;
        iArr[i10] = iArr[i10] + 1;
        this.f21849r = 0;
    }

    public final void m() throws IOException {
        int iJ = this.f21849r;
        if (iJ == 0) {
            iJ = j();
        }
        if (iJ != 2) {
            throw Y("END_OBJECT");
        }
        int i7 = this.f21854w;
        int i10 = i7 - 1;
        this.f21854w = i10;
        this.f21855x[i10] = null;
        int[] iArr = this.f21856y;
        int i11 = i7 - 2;
        iArr[i11] = iArr[i11] + 1;
        this.f21849r = 0;
    }

    public final boolean o(int i7) throws IOException {
        int i10;
        int i11;
        int i12 = this.f21848q;
        int i13 = this.f21846n;
        this.f21848q = i12 - i13;
        int i14 = this.o;
        char[] cArr = this.f21845m;
        if (i14 != i13) {
            int i15 = i14 - i13;
            this.o = i15;
            System.arraycopy(cArr, i13, cArr, 0, i15);
        } else {
            this.o = 0;
        }
        this.f21846n = 0;
        do {
            int i16 = this.o;
            int i17 = this.l.read(cArr, i16, cArr.length - i16);
            if (i17 == -1) {
                return false;
            }
            i10 = this.o + i17;
            this.o = i10;
            if (this.f21847p == 0 && (i11 = this.f21848q) == 0 && i10 > 0 && cArr[0] == 65279) {
                this.f21846n++;
                this.f21848q = i11 + 1;
                i7++;
            }
        } while (i10 < i7);
        return true;
    }

    public final String q(boolean z6) {
        StringBuilder sb = new StringBuilder("$");
        int i7 = 0;
        while (true) {
            int i10 = this.f21854w;
            if (i7 >= i10) {
                return sb.toString();
            }
            int i11 = this.f21853v[i7];
            switch (i11) {
                case 1:
                case 2:
                    int i12 = this.f21856y[i7];
                    if (z6 && i12 > 0 && i7 == i10 - 1) {
                        i12--;
                    }
                    sb.append('[');
                    sb.append(i12);
                    sb.append(']');
                    break;
                case 3:
                case 4:
                case 5:
                    sb.append('.');
                    String str = this.f21855x[i7];
                    if (str != null) {
                        sb.append(str);
                    }
                    break;
                case 6:
                case 7:
                case 8:
                    break;
                default:
                    throw new AssertionError(android.hardware.bydauto.ac.tsimpl.a.e(i11, "Unknown scope value: "));
            }
            i7++;
        }
    }

    public final String toString() {
        return C2297a.class.getSimpleName() + z();
    }

    public final boolean v() throws IOException {
        int iJ = this.f21849r;
        if (iJ == 0) {
            iJ = j();
        }
        return (iJ == 2 || iJ == 4 || iJ == 17) ? false : true;
    }

    public final boolean y(char c) throws C2299c {
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

    public final String z() {
        StringBuilder sbL = M5.b.l(" at line ", " column ", this.f21847p + 1, (this.f21846n - this.f21848q) + 1, " path ");
        sbL.append(q(false));
        return sbL.toString();
    }
}
