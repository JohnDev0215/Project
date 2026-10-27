package p;

import com.google.gson.stream.JsonToken;
import com.google.gson.stream.MalformedJsonException;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class t71 implements Closeable {
    public final Reader c;
    public long q;
    public int r;
    public String s;
    public int[] t;
    public String[] v;
    public int[] w;
    public boolean e = false;
    public final char[] k = new char[1024];
    public int l = 0;
    public int m = 0;
    public int n = 0;
    public int o = 0;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f185p = 0;
    public int u = 1;

    static {
        z90.k = new z90();
    }

    public t71(Reader reader) {
        int[] iArr = new int[32];
        this.t = iArr;
        iArr[0] = 6;
        this.v = new String[32];
        this.w = new int[32];
        this.c = reader;
    }

    /* JADX WARN: Code restructure failed: missing block: B:63:0x00a1, code lost:
    
        i();
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:61:0x009b. Please report as an issue. */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void D() throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 212
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.t71.D():void");
    }

    public final int D0() throws IOException {
        int iQ = this.f185p;
        if (iQ == 0) {
            iQ = q();
        }
        if (iQ == 15) {
            long j = this.q;
            int i = (int) j;
            if (j != i) {
                throw new NumberFormatException("Expected an int but was " + this.q + G0());
            }
            this.f185p = 0;
            int[] iArr = this.w;
            int i2 = this.u - 1;
            iArr[i2] = iArr[i2] + 1;
            return i;
        }
        if (iQ == 16) {
            this.s = new String(this.k, this.l, this.r);
            this.l += this.r;
        } else {
            if (iQ != 8 && iQ != 9 && iQ != 10) {
                throw new IllegalStateException("Expected an int but was " + K0() + G0());
            }
            if (iQ == 10) {
                this.s = J0();
            } else {
                this.s = I0(iQ == 8 ? '\'' : '\"');
            }
            try {
                int i3 = Integer.parseInt(this.s);
                this.f185p = 0;
                int[] iArr2 = this.w;
                int i4 = this.u - 1;
                iArr2[i4] = iArr2[i4] + 1;
                return i3;
            } catch (NumberFormatException unused) {
            }
        }
        this.f185p = 11;
        double d = Double.parseDouble(this.s);
        int i5 = (int) d;
        if (i5 != d) {
            throw new NumberFormatException("Expected an int but was " + this.s + G0());
        }
        this.s = null;
        this.f185p = 0;
        int[] iArr3 = this.w;
        int i6 = this.u - 1;
        iArr3[i6] = iArr3[i6] + 1;
        return i5;
    }

    public final void E() throws IOException {
        int iQ = this.f185p;
        if (iQ == 0) {
            iQ = q();
        }
        if (iQ != 4) {
            throw new IllegalStateException("Expected END_ARRAY but was " + K0() + G0());
        }
        int i = this.u;
        this.u = i - 1;
        int[] iArr = this.w;
        int i2 = i - 2;
        iArr[i2] = iArr[i2] + 1;
        this.f185p = 0;
    }

    public final boolean E0(char c) throws MalformedJsonException {
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

    public final String G0() {
        return " at line " + (this.n + 1) + " column " + ((this.l - this.o) + 1) + " path " + o0();
    }

    public final int H0(boolean z) throws IOException {
        int i = this.l;
        int i2 = this.m;
        while (true) {
            if (i == i2) {
                this.l = i;
                if (!X(1)) {
                    if (!z) {
                        return -1;
                    }
                    throw new EOFException("End of input" + G0());
                }
                i = this.l;
                i2 = this.m;
            }
            int i3 = i + 1;
            char[] cArr = this.k;
            char c = cArr[i];
            if (c == '\n') {
                this.n++;
                this.o = i3;
            } else if (c != ' ' && c != '\r' && c != '\t') {
                if (c == '/') {
                    this.l = i3;
                    if (i3 == i2) {
                        this.l = i;
                        boolean zX = X(2);
                        this.l++;
                        if (!zX) {
                            return c;
                        }
                    }
                    i();
                    int i4 = this.l;
                    char c2 = cArr[i4];
                    if (c2 == '*') {
                        this.l = i4 + 1;
                        while (true) {
                            if (this.l + 2 > this.m && !X(2)) {
                                P0("Unterminated comment");
                                throw null;
                            }
                            int i5 = this.l;
                            if (cArr[i5] != '\n') {
                                for (int i6 = 0; i6 < 2; i6++) {
                                    if (cArr[this.l + i6] != "*/".charAt(i6)) {
                                        break;
                                    }
                                }
                                i = this.l + 2;
                                i2 = this.m;
                                break;
                            }
                            this.n++;
                            this.o = i5 + 1;
                            this.l++;
                        }
                    } else {
                        if (c2 != '/') {
                            return c;
                        }
                        this.l = i4 + 1;
                        O0();
                        i = this.l;
                        i2 = this.m;
                    }
                } else {
                    if (c != '#') {
                        this.l = i3;
                        return c;
                    }
                    this.l = i3;
                    i();
                    O0();
                    i = this.l;
                    i2 = this.m;
                }
            }
            i = i3;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x002d, code lost:
    
        r10.l = r8;
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
    
        r1.append(r7, r3, r2 - r3);
        r10.l = r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String I0(char r11) throws com.google.gson.stream.MalformedJsonException {
        /*
            r10 = this;
            r0 = 0
            r1 = r0
        L2:
            int r2 = r10.l
            int r3 = r10.m
        L6:
            r4 = r3
            r3 = r2
        L8:
            r5 = 1
            r6 = 16
            char[] r7 = r10.k
            if (r2 >= r4) goto L5b
            int r8 = r2 + 1
            char r2 = r7[r2]
            if (r2 != r11) goto L29
            r10.l = r8
            int r8 = r8 - r3
            int r8 = r8 - r5
            if (r1 != 0) goto L21
            java.lang.String r11 = new java.lang.String
            r11.<init>(r7, r3, r8)
            return r11
        L21:
            r1.append(r7, r3, r8)
            java.lang.String r11 = r1.toString()
            return r11
        L29:
            r9 = 92
            if (r2 != r9) goto L4e
            r10.l = r8
            int r8 = r8 - r3
            int r2 = r8 + (-1)
            if (r1 != 0) goto L3f
            int r8 = r8 * 2
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            int r4 = java.lang.Math.max(r8, r6)
            r1.<init>(r4)
        L3f:
            r1.append(r7, r3, r2)
            char r2 = r10.M0()
            r1.append(r2)
            int r2 = r10.l
            int r3 = r10.m
            goto L6
        L4e:
            r6 = 10
            if (r2 != r6) goto L59
            int r2 = r10.n
            int r2 = r2 + r5
            r10.n = r2
            r10.o = r8
        L59:
            r2 = r8
            goto L8
        L5b:
            if (r1 != 0) goto L6b
            int r1 = r2 - r3
            int r1 = r1 * 2
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            int r1 = java.lang.Math.max(r1, r6)
            r4.<init>(r1)
            r1 = r4
        L6b:
            int r4 = r2 - r3
            r1.append(r7, r3, r4)
            r10.l = r2
            boolean r2 = r10.X(r5)
            if (r2 == 0) goto L79
            goto L2
        L79:
            java.lang.String r11 = "Unterminated string"
            r10.P0(r11)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p.t71.I0(char):java.lang.String");
    }

    public final void J() throws IOException {
        int iQ = this.f185p;
        if (iQ == 0) {
            iQ = q();
        }
        if (iQ != 2) {
            throw new IllegalStateException("Expected END_OBJECT but was " + K0() + G0());
        }
        int i = this.u;
        int i2 = i - 1;
        this.u = i2;
        this.v[i2] = null;
        int[] iArr = this.w;
        int i3 = i - 2;
        iArr[i3] = iArr[i3] + 1;
        this.f185p = 0;
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
    public final java.lang.String J0() throws com.google.gson.stream.MalformedJsonException {
        /*
            r7 = this;
            r0 = 0
            r1 = 0
        L2:
            r2 = r1
        L3:
            int r3 = r7.l
            int r4 = r3 + r2
            int r5 = r7.m
            char[] r6 = r7.k
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
            boolean r3 = r7.X(r3)
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
            int r3 = r7.l
            r0.append(r6, r3, r2)
            int r3 = r7.l
            int r3 = r3 + r2
            r7.l = r3
            r2 = 1
            boolean r2 = r7.X(r2)
            if (r2 != 0) goto L2
        L7a:
            if (r0 != 0) goto L84
            java.lang.String r0 = new java.lang.String
            int r2 = r7.l
            r0.<init>(r6, r2, r1)
            goto L8d
        L84:
            int r2 = r7.l
            r0.append(r6, r2, r1)
            java.lang.String r0 = r0.toString()
        L8d:
            int r2 = r7.l
            int r2 = r2 + r1
            r7.l = r2
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p.t71.J0():java.lang.String");
    }

    public final JsonToken K0() throws IOException {
        int iQ = this.f185p;
        if (iQ == 0) {
            iQ = q();
        }
        switch (iQ) {
            case 1:
                return JsonToken.k;
            case 2:
                return JsonToken.l;
            case 3:
                return JsonToken.c;
            case 4:
                return JsonToken.e;
            case 5:
            case 6:
                return JsonToken.f40p;
            case 7:
                return JsonToken.q;
            case 8:
            case 9:
            case 10:
            case 11:
                return JsonToken.n;
            case 12:
            case 13:
            case 14:
                return JsonToken.m;
            case 15:
            case 16:
                return JsonToken.o;
            case 17:
                return JsonToken.r;
            default:
                throw new AssertionError();
        }
    }

    public final void L0(int i) {
        int i2 = this.u;
        int[] iArr = this.t;
        if (i2 == iArr.length) {
            int i3 = i2 * 2;
            this.t = Arrays.copyOf(iArr, i3);
            this.w = Arrays.copyOf(this.w, i3);
            this.v = (String[]) Arrays.copyOf(this.v, i3);
        }
        int[] iArr2 = this.t;
        int i4 = this.u;
        this.u = i4 + 1;
        iArr2[i4] = i;
    }

    public final char M0() throws MalformedJsonException {
        int i;
        if (this.l == this.m && !X(1)) {
            P0("Unterminated escape sequence");
            throw null;
        }
        int i2 = this.l;
        int i3 = i2 + 1;
        this.l = i3;
        char[] cArr = this.k;
        char c = cArr[i2];
        if (c == '\n') {
            this.n++;
            this.o = i3;
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
                P0("Invalid escape sequence");
                throw null;
            }
            if (i2 + 5 > this.m && !X(4)) {
                P0("Unterminated escape sequence");
                throw null;
            }
            int i4 = this.l;
            int i5 = i4 + 4;
            char c2 = 0;
            while (i4 < i5) {
                char c3 = cArr[i4];
                char c4 = (char) (c2 << 4);
                if (c3 >= '0' && c3 <= '9') {
                    i = c3 - '0';
                } else if (c3 >= 'a' && c3 <= 'f') {
                    i = c3 - 'W';
                } else {
                    if (c3 < 'A' || c3 > 'F') {
                        throw new NumberFormatException("\\u".concat(new String(cArr, this.l, 4)));
                    }
                    i = c3 - '7';
                }
                c2 = (char) (i + c4);
                i4++;
            }
            this.l += 4;
            return c2;
        }
        return c;
    }

    public final void N0(char c) throws MalformedJsonException {
        do {
            int i = this.l;
            int i2 = this.m;
            while (i < i2) {
                int i3 = i + 1;
                char c2 = this.k[i];
                if (c2 == c) {
                    this.l = i3;
                    return;
                }
                if (c2 == '\\') {
                    this.l = i3;
                    M0();
                    i = this.l;
                    i2 = this.m;
                } else {
                    if (c2 == '\n') {
                        this.n++;
                        this.o = i3;
                    }
                    i = i3;
                }
            }
            this.l = i;
        } while (X(1));
        P0("Unterminated string");
        throw null;
    }

    public final void O0() {
        char c;
        do {
            if (this.l >= this.m && !X(1)) {
                return;
            }
            int i = this.l;
            int i2 = i + 1;
            this.l = i2;
            c = this.k[i];
            if (c == '\n') {
                this.n++;
                this.o = i2;
                return;
            }
        } while (c != '\r');
    }

    public final void P0(String str) throws MalformedJsonException {
        throw new MalformedJsonException(str + G0());
    }

    public final double T() throws IOException {
        int iQ = this.f185p;
        if (iQ == 0) {
            iQ = q();
        }
        if (iQ == 15) {
            this.f185p = 0;
            int[] iArr = this.w;
            int i = this.u - 1;
            iArr[i] = iArr[i] + 1;
            return this.q;
        }
        if (iQ == 16) {
            this.s = new String(this.k, this.l, this.r);
            this.l += this.r;
        } else if (iQ == 8 || iQ == 9) {
            this.s = I0(iQ == 8 ? '\'' : '\"');
        } else if (iQ == 10) {
            this.s = J0();
        } else if (iQ != 11) {
            throw new IllegalStateException("Expected a double but was " + K0() + G0());
        }
        this.f185p = 11;
        double d = Double.parseDouble(this.s);
        if (!this.e && (Double.isNaN(d) || Double.isInfinite(d))) {
            throw new MalformedJsonException("JSON forbids NaN and infinities: " + d + G0());
        }
        this.s = null;
        this.f185p = 0;
        int[] iArr2 = this.w;
        int i2 = this.u - 1;
        iArr2[i2] = iArr2[i2] + 1;
        return d;
    }

    public final boolean X(int i) throws IOException {
        int i2;
        int i3;
        int i4 = this.o;
        int i5 = this.l;
        this.o = i4 - i5;
        int i6 = this.m;
        char[] cArr = this.k;
        if (i6 != i5) {
            int i7 = i6 - i5;
            this.m = i7;
            System.arraycopy(cArr, i5, cArr, 0, i7);
        } else {
            this.m = 0;
        }
        this.l = 0;
        do {
            int i8 = this.m;
            int i9 = this.c.read(cArr, i8, cArr.length - i8);
            if (i9 == -1) {
                return false;
            }
            i2 = this.m + i9;
            this.m = i2;
            if (this.n == 0 && (i3 = this.o) == 0 && i2 > 0 && cArr[0] == 65279) {
                this.l++;
                this.o = i3 + 1;
                i++;
            }
        } while (i2 < i);
        return true;
    }

    public final long Z() throws IOException {
        int iQ = this.f185p;
        if (iQ == 0) {
            iQ = q();
        }
        if (iQ == 15) {
            this.f185p = 0;
            int[] iArr = this.w;
            int i = this.u - 1;
            iArr[i] = iArr[i] + 1;
            return this.q;
        }
        if (iQ == 16) {
            this.s = new String(this.k, this.l, this.r);
            this.l += this.r;
        } else {
            if (iQ != 8 && iQ != 9 && iQ != 10) {
                throw new IllegalStateException("Expected a long but was " + K0() + G0());
            }
            if (iQ == 10) {
                this.s = J0();
            } else {
                this.s = I0(iQ == 8 ? '\'' : '\"');
            }
            try {
                long j = Long.parseLong(this.s);
                this.f185p = 0;
                int[] iArr2 = this.w;
                int i2 = this.u - 1;
                iArr2[i2] = iArr2[i2] + 1;
                return j;
            } catch (NumberFormatException unused) {
            }
        }
        this.f185p = 11;
        double d = Double.parseDouble(this.s);
        long j2 = (long) d;
        if (j2 != d) {
            throw new NumberFormatException("Expected a long but was " + this.s + G0());
        }
        this.s = null;
        this.f185p = 0;
        int[] iArr3 = this.w;
        int i3 = this.u - 1;
        iArr3[i3] = iArr3[i3] + 1;
        return j2;
    }

    public final void b() throws IOException {
        int iQ = this.f185p;
        if (iQ == 0) {
            iQ = q();
        }
        if (iQ == 3) {
            L0(1);
            this.w[this.u - 1] = 0;
            this.f185p = 0;
        } else {
            throw new IllegalStateException("Expected BEGIN_ARRAY but was " + K0() + G0());
        }
    }

    public final void c() throws IOException {
        int iQ = this.f185p;
        if (iQ == 0) {
            iQ = q();
        }
        if (iQ == 1) {
            L0(3);
            this.f185p = 0;
        } else {
            throw new IllegalStateException("Expected BEGIN_OBJECT but was " + K0() + G0());
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.f185p = 0;
        this.t[0] = 8;
        this.u = 1;
        this.c.close();
    }

    public final boolean hasNext() throws IOException {
        int iQ = this.f185p;
        if (iQ == 0) {
            iQ = q();
        }
        return (iQ == 2 || iQ == 4) ? false : true;
    }

    public final void i() throws MalformedJsonException {
        if (this.e) {
            return;
        }
        P0("Use JsonReader.setLenient(true) to accept malformed JSON");
        throw null;
    }

    public final String o0() {
        StringBuilder sb = new StringBuilder("$");
        int i = this.u;
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = this.t[i2];
            if (i3 == 1 || i3 == 2) {
                sb.append('[');
                sb.append(this.w[i2]);
                sb.append(']');
            } else if (i3 == 3 || i3 == 4 || i3 == 5) {
                sb.append('.');
                String str = this.v[i2];
                if (str != null) {
                    sb.append(str);
                }
            }
        }
        return sb.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:167:0x0211, code lost:
    
        if (E0(r1) != false) goto L121;
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
    public final int q() throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 813
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.t71.q():int");
    }

    public final String r0() throws IOException {
        String strI0;
        int iQ = this.f185p;
        if (iQ == 0) {
            iQ = q();
        }
        if (iQ == 14) {
            strI0 = J0();
        } else if (iQ == 12) {
            strI0 = I0('\'');
        } else {
            if (iQ != 13) {
                throw new IllegalStateException("Expected a name but was " + K0() + G0());
            }
            strI0 = I0('\"');
        }
        this.f185p = 0;
        this.v[this.u - 1] = strI0;
        return strI0;
    }

    public final String toString() {
        return t71.class.getSimpleName() + G0();
    }

    public final String u() throws IOException {
        String str;
        int iQ = this.f185p;
        if (iQ == 0) {
            iQ = q();
        }
        if (iQ == 10) {
            str = J0();
        } else if (iQ == 8) {
            str = I0('\'');
        } else if (iQ == 9) {
            str = I0('\"');
        } else if (iQ == 11) {
            str = this.s;
            this.s = null;
        } else if (iQ == 15) {
            str = Long.toString(this.q);
        } else {
            if (iQ != 16) {
                throw new IllegalStateException("Expected a string but was " + K0() + G0());
            }
            str = new String(this.k, this.l, this.r);
            this.l += this.r;
        }
        this.f185p = 0;
        int[] iArr = this.w;
        int i = this.u - 1;
        iArr[i] = iArr[i] + 1;
        return str;
    }

    public final boolean w0() throws IOException {
        int iQ = this.f185p;
        if (iQ == 0) {
            iQ = q();
        }
        if (iQ == 5) {
            this.f185p = 0;
            int[] iArr = this.w;
            int i = this.u - 1;
            iArr[i] = iArr[i] + 1;
            return true;
        }
        if (iQ != 6) {
            throw new IllegalStateException("Expected a boolean but was " + K0() + G0());
        }
        this.f185p = 0;
        int[] iArr2 = this.w;
        int i2 = this.u - 1;
        iArr2[i2] = iArr2[i2] + 1;
        return false;
    }

    public final void z0() throws IOException {
        int iQ = this.f185p;
        if (iQ == 0) {
            iQ = q();
        }
        if (iQ != 7) {
            throw new IllegalStateException("Expected null but was " + K0() + G0());
        }
        this.f185p = 0;
        int[] iArr = this.w;
        int i = this.u - 1;
        iArr[i] = iArr[i] + 1;
    }
}
