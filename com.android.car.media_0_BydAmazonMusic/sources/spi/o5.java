package spi;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.util.Objects;

/* JADX INFO: compiled from: JsonReader.java */
/* JADX INFO: loaded from: classes2.dex */
public class o5 implements Closeable {
    public static final char[] p = ")]}'\n".toCharArray();
    public final Reader a;
    public long i;
    public int j;
    public String k;
    public int[] l;
    public String[] n;
    public int[] o;
    public boolean b = false;
    public final char[] c = new char[1024];
    public int d = 0;
    public int e = 0;
    public int f = 0;
    public int g = 0;
    public int h = 0;
    public int m = 0 + 1;

    /* JADX INFO: compiled from: JsonReader.java */
    public static class a extends p5 {
    }

    static {
        p5.a = new a();
    }

    public o5(Reader reader) {
        int[] iArr = new int[32];
        this.l = iArr;
        iArr[0] = 6;
        this.n = new String[32];
        this.o = new int[32];
        Objects.requireNonNull(reader, "in == null");
        this.a = reader;
    }

    /* JADX WARN: Code restructure failed: missing block: B:63:0x00a1, code lost:
    
        c();
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:61:0x009b. Please report as an issue. */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void A() throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 212
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.o5.A():void");
    }

    public final IOException B(String str) throws m6 {
        StringBuilder sbK = c.k(str);
        sbK.append(k());
        throw new m6(sbK.toString());
    }

    public void a() throws IOException {
        int iD = this.h;
        if (iD == 0) {
            iD = d();
        }
        if (iD == 3) {
            w(1);
            this.o[this.m - 1] = 0;
            this.h = 0;
        } else {
            StringBuilder sbK = c.k("Expected BEGIN_ARRAY but was ");
            sbK.append(v());
            sbK.append(k());
            throw new IllegalStateException(sbK.toString());
        }
    }

    public void b() throws IOException {
        int iD = this.h;
        if (iD == 0) {
            iD = d();
        }
        if (iD == 1) {
            w(3);
            this.h = 0;
        } else {
            StringBuilder sbK = c.k("Expected BEGIN_OBJECT but was ");
            sbK.append(v());
            sbK.append(k());
            throw new IllegalStateException(sbK.toString());
        }
    }

    public final void c() throws m6 {
        if (this.b) {
            return;
        }
        B("Use JsonReader.setLenient(true) to accept malformed JSON");
        throw null;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.h = 0;
        this.l[0] = 8;
        this.m = 1;
        this.a.close();
    }

    /* JADX WARN: Code restructure failed: missing block: B:161:0x020a, code lost:
    
        r19 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x0210, code lost:
    
        if (j(r6) != false) goto L113;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0174 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x01a5  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0262  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x0270 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0271  */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r2v7 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int d() throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 812
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.o5.d():int");
    }

    public void e() throws IOException {
        int iD = this.h;
        if (iD == 0) {
            iD = d();
        }
        if (iD != 4) {
            StringBuilder sbK = c.k("Expected END_ARRAY but was ");
            sbK.append(v());
            sbK.append(k());
            throw new IllegalStateException(sbK.toString());
        }
        int i = this.m - 1;
        this.m = i;
        int[] iArr = this.o;
        int i2 = i - 1;
        iArr[i2] = iArr[i2] + 1;
        this.h = 0;
    }

    public void f() throws IOException {
        int iD = this.h;
        if (iD == 0) {
            iD = d();
        }
        if (iD != 2) {
            StringBuilder sbK = c.k("Expected END_OBJECT but was ");
            sbK.append(v());
            sbK.append(k());
            throw new IllegalStateException(sbK.toString());
        }
        int i = this.m - 1;
        this.m = i;
        this.n[i] = null;
        int[] iArr = this.o;
        int i2 = i - 1;
        iArr[i2] = iArr[i2] + 1;
        this.h = 0;
    }

    public final boolean g(int i) throws IOException {
        int i2;
        int i3;
        char[] cArr = this.c;
        int i4 = this.g;
        int i5 = this.d;
        this.g = i4 - i5;
        int i6 = this.e;
        if (i6 != i5) {
            int i7 = i6 - i5;
            this.e = i7;
            System.arraycopy(cArr, i5, cArr, 0, i7);
        } else {
            this.e = 0;
        }
        this.d = 0;
        do {
            Reader reader = this.a;
            int i8 = this.e;
            int i9 = reader.read(cArr, i8, cArr.length - i8);
            if (i9 == -1) {
                return false;
            }
            i2 = this.e + i9;
            this.e = i2;
            if (this.f == 0 && (i3 = this.g) == 0 && i2 > 0 && cArr[0] == 65279) {
                this.d++;
                this.g = i3 + 1;
                i++;
            }
        } while (i2 < i);
        return true;
    }

    public String h() {
        StringBuilder sb = new StringBuilder();
        sb.append('$');
        int i = this.m;
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = this.l[i2];
            if (i3 == 1 || i3 == 2) {
                sb.append('[');
                sb.append(this.o[i2]);
                sb.append(']');
            } else if (i3 == 3 || i3 == 4 || i3 == 5) {
                sb.append('.');
                String[] strArr = this.n;
                if (strArr[i2] != null) {
                    sb.append(strArr[i2]);
                }
            }
        }
        return sb.toString();
    }

    public boolean i() throws IOException {
        int iD = this.h;
        if (iD == 0) {
            iD = d();
        }
        return (iD == 2 || iD == 4) ? false : true;
    }

    public final boolean j(char c) throws m6 {
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
        c();
        return false;
    }

    public final String k() {
        StringBuilder sbM = c.m(" at line ", this.f + 1, " column ", (this.d - this.g) + 1, " path ");
        sbM.append(h());
        return sbM.toString();
    }

    public boolean l() throws IOException {
        int iD = this.h;
        if (iD == 0) {
            iD = d();
        }
        if (iD == 5) {
            this.h = 0;
            int[] iArr = this.o;
            int i = this.m - 1;
            iArr[i] = iArr[i] + 1;
            return true;
        }
        if (iD != 6) {
            StringBuilder sbK = c.k("Expected a boolean but was ");
            sbK.append(v());
            sbK.append(k());
            throw new IllegalStateException(sbK.toString());
        }
        this.h = 0;
        int[] iArr2 = this.o;
        int i2 = this.m - 1;
        iArr2[i2] = iArr2[i2] + 1;
        return false;
    }

    public double m() throws IOException {
        int iD = this.h;
        if (iD == 0) {
            iD = d();
        }
        if (iD == 15) {
            this.h = 0;
            int[] iArr = this.o;
            int i = this.m - 1;
            iArr[i] = iArr[i] + 1;
            return this.i;
        }
        if (iD == 16) {
            this.k = new String(this.c, this.d, this.j);
            this.d += this.j;
        } else if (iD == 8 || iD == 9) {
            this.k = s(iD == 8 ? '\'' : '\"');
        } else if (iD == 10) {
            this.k = u();
        } else if (iD != 11) {
            StringBuilder sbK = c.k("Expected a double but was ");
            sbK.append(v());
            sbK.append(k());
            throw new IllegalStateException(sbK.toString());
        }
        this.h = 11;
        double d = Double.parseDouble(this.k);
        if (!this.b && (Double.isNaN(d) || Double.isInfinite(d))) {
            throw new m6("JSON forbids NaN and infinities: " + d + k());
        }
        this.k = null;
        this.h = 0;
        int[] iArr2 = this.o;
        int i2 = this.m - 1;
        iArr2[i2] = iArr2[i2] + 1;
        return d;
    }

    public int n() throws IOException {
        int iD = this.h;
        if (iD == 0) {
            iD = d();
        }
        if (iD == 15) {
            long j = this.i;
            int i = (int) j;
            if (j != i) {
                StringBuilder sbK = c.k("Expected an int but was ");
                sbK.append(this.i);
                sbK.append(k());
                throw new NumberFormatException(sbK.toString());
            }
            this.h = 0;
            int[] iArr = this.o;
            int i2 = this.m - 1;
            iArr[i2] = iArr[i2] + 1;
            return i;
        }
        if (iD == 16) {
            this.k = new String(this.c, this.d, this.j);
            this.d += this.j;
        } else {
            if (iD != 8 && iD != 9 && iD != 10) {
                StringBuilder sbK2 = c.k("Expected an int but was ");
                sbK2.append(v());
                sbK2.append(k());
                throw new IllegalStateException(sbK2.toString());
            }
            if (iD == 10) {
                this.k = u();
            } else {
                this.k = s(iD == 8 ? '\'' : '\"');
            }
            try {
                int i3 = Integer.parseInt(this.k);
                this.h = 0;
                int[] iArr2 = this.o;
                int i4 = this.m - 1;
                iArr2[i4] = iArr2[i4] + 1;
                return i3;
            } catch (NumberFormatException unused) {
            }
        }
        this.h = 11;
        double d = Double.parseDouble(this.k);
        int i5 = (int) d;
        if (i5 != d) {
            StringBuilder sbK3 = c.k("Expected an int but was ");
            sbK3.append(this.k);
            sbK3.append(k());
            throw new NumberFormatException(sbK3.toString());
        }
        this.k = null;
        this.h = 0;
        int[] iArr3 = this.o;
        int i6 = this.m - 1;
        iArr3[i6] = iArr3[i6] + 1;
        return i5;
    }

    public long o() throws IOException {
        int iD = this.h;
        if (iD == 0) {
            iD = d();
        }
        if (iD == 15) {
            this.h = 0;
            int[] iArr = this.o;
            int i = this.m - 1;
            iArr[i] = iArr[i] + 1;
            return this.i;
        }
        if (iD == 16) {
            this.k = new String(this.c, this.d, this.j);
            this.d += this.j;
        } else {
            if (iD != 8 && iD != 9 && iD != 10) {
                StringBuilder sbK = c.k("Expected a long but was ");
                sbK.append(v());
                sbK.append(k());
                throw new IllegalStateException(sbK.toString());
            }
            if (iD == 10) {
                this.k = u();
            } else {
                this.k = s(iD == 8 ? '\'' : '\"');
            }
            try {
                long j = Long.parseLong(this.k);
                this.h = 0;
                int[] iArr2 = this.o;
                int i2 = this.m - 1;
                iArr2[i2] = iArr2[i2] + 1;
                return j;
            } catch (NumberFormatException unused) {
            }
        }
        this.h = 11;
        double d = Double.parseDouble(this.k);
        long j2 = (long) d;
        if (j2 != d) {
            StringBuilder sbK2 = c.k("Expected a long but was ");
            sbK2.append(this.k);
            sbK2.append(k());
            throw new NumberFormatException(sbK2.toString());
        }
        this.k = null;
        this.h = 0;
        int[] iArr3 = this.o;
        int i3 = this.m - 1;
        iArr3[i3] = iArr3[i3] + 1;
        return j2;
    }

    public String p() throws IOException {
        String strS;
        int iD = this.h;
        if (iD == 0) {
            iD = d();
        }
        if (iD == 14) {
            strS = u();
        } else if (iD == 12) {
            strS = s('\'');
        } else {
            if (iD != 13) {
                StringBuilder sbK = c.k("Expected a name but was ");
                sbK.append(v());
                sbK.append(k());
                throw new IllegalStateException(sbK.toString());
            }
            strS = s('\"');
        }
        this.h = 0;
        this.n[this.m - 1] = strS;
        return strS;
    }

    public final int q(boolean z) throws IOException {
        char[] cArr = this.c;
        int i = this.d;
        int i2 = this.e;
        while (true) {
            boolean z2 = true;
            if (i == i2) {
                this.d = i;
                if (!g(1)) {
                    if (!z) {
                        return -1;
                    }
                    StringBuilder sbK = c.k("End of input");
                    sbK.append(k());
                    throw new EOFException(sbK.toString());
                }
                i = this.d;
                i2 = this.e;
            }
            int i3 = i + 1;
            char c = cArr[i];
            if (c == '\n') {
                this.f++;
                this.g = i3;
            } else if (c != ' ' && c != '\r' && c != '\t') {
                if (c == '/') {
                    this.d = i3;
                    if (i3 == i2) {
                        this.d = i3 - 1;
                        boolean zG = g(2);
                        this.d++;
                        if (!zG) {
                            return c;
                        }
                    }
                    c();
                    int i4 = this.d;
                    char c2 = cArr[i4];
                    if (c2 == '*') {
                        this.d = i4 + 1;
                        while (true) {
                            if (this.d + 2 > this.e && !g(2)) {
                                z2 = false;
                                break;
                            }
                            char[] cArr2 = this.c;
                            int i5 = this.d;
                            if (cArr2[i5] != '\n') {
                                for (int i6 = 0; i6 < 2; i6++) {
                                    if (this.c[this.d + i6] != "*/".charAt(i6)) {
                                        break;
                                    }
                                }
                                break;
                            }
                            this.f++;
                            this.g = i5 + 1;
                            this.d++;
                        }
                        if (!z2) {
                            B("Unterminated comment");
                            throw null;
                        }
                        i = this.d + 2;
                        i2 = this.e;
                    } else {
                        if (c2 != '/') {
                            return c;
                        }
                        this.d = i4 + 1;
                        z();
                        i = this.d;
                        i2 = this.e;
                    }
                } else {
                    if (c != '#') {
                        this.d = i3;
                        return c;
                    }
                    this.d = i3;
                    c();
                    z();
                    i = this.d;
                    i2 = this.e;
                }
            }
            i = i3;
        }
    }

    public void r() throws IOException {
        int iD = this.h;
        if (iD == 0) {
            iD = d();
        }
        if (iD != 7) {
            StringBuilder sbK = c.k("Expected null but was ");
            sbK.append(v());
            sbK.append(k());
            throw new IllegalStateException(sbK.toString());
        }
        this.h = 0;
        int[] iArr = this.o;
        int i = this.m - 1;
        iArr[i] = iArr[i] + 1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x005d, code lost:
    
        if (r2 != null) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x005f, code lost:
    
        r2 = new java.lang.StringBuilder(java.lang.Math.max((r3 - r4) * 2, 16));
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x006d, code lost:
    
        r2.append(r0, r4, r3 - r4);
        r10.d = r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String s(char r11) throws spi.m6 {
        /*
            r10 = this;
            char[] r0 = r10.c
            r1 = 0
            r2 = r1
        L4:
            int r3 = r10.d
            int r4 = r10.e
        L8:
            r5 = r4
            r4 = r3
        La:
            r6 = 16
            r7 = 1
            if (r3 >= r5) goto L5d
            int r8 = r3 + 1
            char r3 = r0[r3]
            if (r3 != r11) goto L29
            r10.d = r8
            int r8 = r8 - r4
            int r8 = r8 - r7
            if (r2 != 0) goto L21
            java.lang.String r10 = new java.lang.String
            r10.<init>(r0, r4, r8)
            return r10
        L21:
            r2.append(r0, r4, r8)
            java.lang.String r10 = r2.toString()
            return r10
        L29:
            r9 = 92
            if (r3 != r9) goto L50
            r10.d = r8
            int r8 = r8 - r4
            int r8 = r8 - r7
            if (r2 != 0) goto L41
            int r2 = r8 + 1
            int r2 = r2 * 2
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            int r2 = java.lang.Math.max(r2, r6)
            r3.<init>(r2)
            r2 = r3
        L41:
            r2.append(r0, r4, r8)
            char r3 = r10.x()
            r2.append(r3)
            int r3 = r10.d
            int r4 = r10.e
            goto L8
        L50:
            r6 = 10
            if (r3 != r6) goto L5b
            int r3 = r10.f
            int r3 = r3 + r7
            r10.f = r3
            r10.g = r8
        L5b:
            r3 = r8
            goto La
        L5d:
            if (r2 != 0) goto L6d
            int r2 = r3 - r4
            int r2 = r2 * 2
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            int r2 = java.lang.Math.max(r2, r6)
            r5.<init>(r2)
            r2 = r5
        L6d:
            int r5 = r3 - r4
            r2.append(r0, r4, r5)
            r10.d = r3
            boolean r3 = r10.g(r7)
            if (r3 == 0) goto L7b
            goto L4
        L7b:
            java.lang.String r11 = "Unterminated string"
            r10.B(r11)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.o5.s(char):java.lang.String");
    }

    public String t() throws IOException {
        String str;
        int iD = this.h;
        if (iD == 0) {
            iD = d();
        }
        if (iD == 10) {
            str = u();
        } else if (iD == 8) {
            str = s('\'');
        } else if (iD == 9) {
            str = s('\"');
        } else if (iD == 11) {
            str = this.k;
            this.k = null;
        } else if (iD == 15) {
            str = Long.toString(this.i);
        } else {
            if (iD != 16) {
                StringBuilder sbK = c.k("Expected a string but was ");
                sbK.append(v());
                sbK.append(k());
                throw new IllegalStateException(sbK.toString());
            }
            str = new String(this.c, this.d, this.j);
            this.d += this.j;
        }
        this.h = 0;
        int[] iArr = this.o;
        int i = this.m - 1;
        iArr[i] = iArr[i] + 1;
        return str;
    }

    public String toString() {
        return getClass().getSimpleName() + k();
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x004a, code lost:
    
        c();
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:32:0x0044. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x008a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String u() throws spi.m6 {
        /*
            r6 = this;
            r0 = 0
            r1 = 0
        L2:
            r2 = r0
        L3:
            int r3 = r6.d
            int r4 = r3 + r2
            int r5 = r6.e
            if (r4 >= r5) goto L4e
            char[] r4 = r6.c
            int r3 = r3 + r2
            char r3 = r4[r3]
            r4 = 9
            if (r3 == r4) goto L5c
            r4 = 10
            if (r3 == r4) goto L5c
            r4 = 12
            if (r3 == r4) goto L5c
            r4 = 13
            if (r3 == r4) goto L5c
            r4 = 32
            if (r3 == r4) goto L5c
            r4 = 35
            if (r3 == r4) goto L4a
            r4 = 44
            if (r3 == r4) goto L5c
            r4 = 47
            if (r3 == r4) goto L4a
            r4 = 61
            if (r3 == r4) goto L4a
            r4 = 123(0x7b, float:1.72E-43)
            if (r3 == r4) goto L5c
            r4 = 125(0x7d, float:1.75E-43)
            if (r3 == r4) goto L5c
            r4 = 58
            if (r3 == r4) goto L5c
            r4 = 59
            if (r3 == r4) goto L4a
            switch(r3) {
                case 91: goto L5c;
                case 92: goto L4a;
                case 93: goto L5c;
                default: goto L47;
            }
        L47:
            int r2 = r2 + 1
            goto L3
        L4a:
            r6.c()
            goto L5c
        L4e:
            char[] r3 = r6.c
            int r3 = r3.length
            if (r2 >= r3) goto L5e
            int r3 = r2 + 1
            boolean r3 = r6.g(r3)
            if (r3 == 0) goto L5c
            goto L3
        L5c:
            r0 = r2
            goto L7e
        L5e:
            if (r1 != 0) goto L6b
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r3 = 16
            int r3 = java.lang.Math.max(r2, r3)
            r1.<init>(r3)
        L6b:
            char[] r3 = r6.c
            int r4 = r6.d
            r1.append(r3, r4, r2)
            int r3 = r6.d
            int r3 = r3 + r2
            r6.d = r3
            r2 = 1
            boolean r2 = r6.g(r2)
            if (r2 != 0) goto L2
        L7e:
            if (r1 != 0) goto L8a
            java.lang.String r1 = new java.lang.String
            char[] r2 = r6.c
            int r3 = r6.d
            r1.<init>(r2, r3, r0)
            goto L95
        L8a:
            char[] r2 = r6.c
            int r3 = r6.d
            r1.append(r2, r3, r0)
            java.lang.String r1 = r1.toString()
        L95:
            int r2 = r6.d
            int r2 = r2 + r0
            r6.d = r2
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.o5.u():java.lang.String");
    }

    public s5 v() throws IOException {
        int iD = this.h;
        if (iD == 0) {
            iD = d();
        }
        switch (iD) {
            case 1:
                return s5.BEGIN_OBJECT;
            case 2:
                return s5.END_OBJECT;
            case 3:
                return s5.BEGIN_ARRAY;
            case 4:
                return s5.END_ARRAY;
            case 5:
            case 6:
                return s5.BOOLEAN;
            case 7:
                return s5.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return s5.STRING;
            case 12:
            case 13:
            case 14:
                return s5.NAME;
            case 15:
            case 16:
                return s5.NUMBER;
            case 17:
                return s5.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    public final void w(int i) {
        int i2 = this.m;
        int[] iArr = this.l;
        if (i2 == iArr.length) {
            int[] iArr2 = new int[i2 * 2];
            int[] iArr3 = new int[i2 * 2];
            String[] strArr = new String[i2 * 2];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            System.arraycopy(this.o, 0, iArr3, 0, this.m);
            System.arraycopy(this.n, 0, strArr, 0, this.m);
            this.l = iArr2;
            this.o = iArr3;
            this.n = strArr;
        }
        int[] iArr4 = this.l;
        int i3 = this.m;
        this.m = i3 + 1;
        iArr4[i3] = i;
    }

    public final char x() throws m6 {
        int i;
        int i2;
        if (this.d == this.e && !g(1)) {
            B("Unterminated escape sequence");
            throw null;
        }
        char[] cArr = this.c;
        int i3 = this.d;
        int i4 = i3 + 1;
        this.d = i4;
        char c = cArr[i3];
        if (c == '\n') {
            this.f++;
            this.g = i4;
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
                B("Invalid escape sequence");
                throw null;
            }
            if (i4 + 4 > this.e && !g(4)) {
                B("Unterminated escape sequence");
                throw null;
            }
            char c2 = 0;
            int i5 = this.d;
            int i6 = i5 + 4;
            while (i5 < i6) {
                char c3 = this.c[i5];
                char c4 = (char) (c2 << 4);
                if (c3 < '0' || c3 > '9') {
                    if (c3 >= 'a' && c3 <= 'f') {
                        i = c3 - 'a';
                    } else {
                        if (c3 < 'A' || c3 > 'F') {
                            StringBuilder sbK = c.k("\\u");
                            sbK.append(new String(this.c, this.d, 4));
                            throw new NumberFormatException(sbK.toString());
                        }
                        i = c3 - 'A';
                    }
                    i2 = i + 10;
                } else {
                    i2 = c3 - '0';
                }
                c2 = (char) (i2 + c4);
                i5++;
            }
            this.d += 4;
            return c2;
        }
        return c;
    }

    public final void y(char c) throws m6 {
        char[] cArr = this.c;
        do {
            int i = this.d;
            int i2 = this.e;
            while (i < i2) {
                int i3 = i + 1;
                char c2 = cArr[i];
                if (c2 == c) {
                    this.d = i3;
                    return;
                }
                if (c2 == '\\') {
                    this.d = i3;
                    x();
                    i = this.d;
                    i2 = this.e;
                } else {
                    if (c2 == '\n') {
                        this.f++;
                        this.g = i3;
                    }
                    i = i3;
                }
            }
            this.d = i;
        } while (g(1));
        B("Unterminated string");
        throw null;
    }

    public final void z() {
        char c;
        do {
            if (this.d >= this.e && !g(1)) {
                return;
            }
            char[] cArr = this.c;
            int i = this.d;
            int i2 = i + 1;
            this.d = i2;
            c = cArr[i];
            if (c == '\n') {
                this.f++;
                this.g = i2;
                return;
            }
        } while (c != '\r');
    }
}
