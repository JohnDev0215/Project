package E5;

import io.sentry.C1489n1;
import java.io.EOFException;
import r9.C2319i;
import r9.C2322l;

/* JADX INFO: loaded from: classes.dex */
public final class v extends u {

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final C2322l f1824v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final C2322l f1825w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final C2322l f1826x;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final C2319i f1827p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final C2319i f1828q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f1829r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public long f1830s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f1831t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public String f1832u;

    static {
        C2322l c2322l = C2322l.o;
        f1824v = m6.b.p("'\\");
        f1825w = m6.b.p("\"\\");
        f1826x = m6.b.p("{}[]:, \n\t\r\f/\\;#=");
        m6.b.p("\n\r");
        m6.b.p("*/");
    }

    public v(C2319i c2319i) {
        this.f1822m = new int[32];
        this.f1823n = new String[32];
        this.o = new int[32];
        this.f1829r = 0;
        this.f1827p = c2319i;
        this.f1828q = c2319i;
        C(6);
    }

    @Override // E5.u
    public final int F(C1489n1 c1489n1) throws s, EOFException {
        int iN = this.f1829r;
        if (iN == 0) {
            iN = N();
        }
        if (iN < 12 || iN > 15) {
            return -1;
        }
        if (iN == 15) {
            return O(c1489n1, this.f1832u);
        }
        int iX = this.f1827p.x((r9.y) c1489n1.f16625n);
        if (iX != -1) {
            this.f1829r = 0;
            this.f1823n[this.l - 1] = ((String[]) c1489n1.f16624m)[iX];
            return iX;
        }
        String str = this.f1823n[this.l - 1];
        String strR = R();
        int iO = O(c1489n1, strR);
        if (iO == -1) {
            this.f1829r = 15;
            this.f1832u = strR;
            this.f1823n[this.l - 1] = str;
        }
        return iO;
    }

    @Override // E5.u
    public final int G(C1489n1 c1489n1) throws s, EOFException {
        int iN = this.f1829r;
        if (iN == 0) {
            iN = N();
        }
        if (iN < 8 || iN > 11) {
            return -1;
        }
        if (iN == 11) {
            return P(c1489n1, this.f1832u);
        }
        int iX = this.f1827p.x((r9.y) c1489n1.f16625n);
        if (iX != -1) {
            this.f1829r = 0;
            int[] iArr = this.o;
            int i7 = this.l - 1;
            iArr[i7] = iArr[i7] + 1;
            return iX;
        }
        String strY = y();
        int iP = P(c1489n1, strY);
        if (iP == -1) {
            this.f1829r = 11;
            this.f1832u = strY;
            this.o[this.l - 1] = r0[r4] - 1;
        }
        return iP;
    }

    @Override // E5.u
    public final void I() throws s, EOFException {
        int iN = this.f1829r;
        if (iN == 0) {
            iN = N();
        }
        if (iN == 14) {
            long jO = this.f1827p.o(f1826x);
            C2319i c2319i = this.f1828q;
            if (jO == -1) {
                jO = c2319i.f21932m;
            }
            c2319i.R(jO);
        } else if (iN == 13) {
            W(f1825w);
        } else if (iN == 12) {
            W(f1824v);
        } else if (iN != 15) {
            throw new A9.k("Expected a name but was " + A7.k.w(z()) + " at path " + k());
        }
        this.f1829r = 0;
        this.f1823n[this.l - 1] = "null";
    }

    @Override // E5.u
    public final void K() throws s, EOFException {
        int i7 = 0;
        do {
            int iN = this.f1829r;
            if (iN == 0) {
                iN = N();
            }
            if (iN == 3) {
                C(1);
            } else if (iN == 1) {
                C(3);
            } else {
                if (iN == 4) {
                    i7--;
                    if (i7 < 0) {
                        throw new A9.k("Expected a value but was " + A7.k.w(z()) + " at path " + k());
                    }
                    this.l--;
                } else if (iN == 2) {
                    i7--;
                    if (i7 < 0) {
                        throw new A9.k("Expected a value but was " + A7.k.w(z()) + " at path " + k());
                    }
                    this.l--;
                } else {
                    C2319i c2319i = this.f1828q;
                    if (iN == 14 || iN == 10) {
                        long jO = this.f1827p.o(f1826x);
                        if (jO == -1) {
                            jO = c2319i.f21932m;
                        }
                        c2319i.R(jO);
                    } else if (iN == 9 || iN == 13) {
                        W(f1825w);
                    } else if (iN == 8 || iN == 12) {
                        W(f1824v);
                    } else if (iN == 17) {
                        c2319i.R(this.f1831t);
                    } else if (iN == 18) {
                        throw new A9.k("Expected a value but was " + A7.k.w(z()) + " at path " + k());
                    }
                }
                this.f1829r = 0;
            }
            i7++;
            this.f1829r = 0;
        } while (i7 != 0);
        int[] iArr = this.o;
        int i10 = this.l - 1;
        iArr[i10] = iArr[i10] + 1;
        this.f1823n[i10] = "null";
    }

    public final void M() throws s {
        L("Use JsonReader.setLenient(true) to accept malformed JSON");
        throw null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:136:0x019b, code lost:
    
        if (Q(r1) != false) goto L115;
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x019d, code lost:
    
        if (r4 != 2) goto L149;
     */
    /* JADX WARN: Code restructure failed: missing block: B:138:0x019f, code lost:
    
        if (r10 == false) goto L149;
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x01a5, code lost:
    
        if (r11 != Long.MIN_VALUE) goto L142;
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x01a7, code lost:
    
        if (r13 == false) goto L149;
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x01ad, code lost:
    
        if (r11 != 0) goto L145;
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x01af, code lost:
    
        if (r13 != false) goto L149;
     */
    /* JADX WARN: Code restructure failed: missing block: B:145:0x01b1, code lost:
    
        if (r13 == false) goto L147;
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x01b4, code lost:
    
        r11 = -r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x01b5, code lost:
    
        r21.f1830s = r11;
        r7.R(r9);
        r9 = 16;
        r21.f1829r = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x01c0, code lost:
    
        if (r4 == 2) goto L154;
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x01c3, code lost:
    
        if (r4 == 4) goto L154;
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x01c6, code lost:
    
        if (r4 != 7) goto L115;
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x01c8, code lost:
    
        r21.f1831t = r9;
        r9 = 17;
        r21.f1829r = 17;
     */
    /* JADX WARN: Removed duplicated region for block: B:173:0x01f5 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:174:0x01f6  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0115 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0116  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int N() throws E5.s, java.io.EOFException {
        /*
            Method dump skipped, instruction units count: 661
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: E5.v.N():int");
    }

    public final int O(C1489n1 c1489n1, String str) {
        int length = ((String[]) c1489n1.f16624m).length;
        for (int i7 = 0; i7 < length; i7++) {
            if (str.equals(((String[]) c1489n1.f16624m)[i7])) {
                this.f1829r = 0;
                this.f1823n[this.l - 1] = str;
                return i7;
            }
        }
        return -1;
    }

    public final int P(C1489n1 c1489n1, String str) {
        int length = ((String[]) c1489n1.f16624m).length;
        for (int i7 = 0; i7 < length; i7++) {
            if (str.equals(((String[]) c1489n1.f16624m)[i7])) {
                this.f1829r = 0;
                int[] iArr = this.o;
                int i10 = this.l - 1;
                iArr[i10] = iArr[i10] + 1;
                return i7;
            }
        }
        return -1;
    }

    public final boolean Q(int i7) throws s {
        if (i7 == 9 || i7 == 10 || i7 == 12 || i7 == 13 || i7 == 32) {
            return false;
        }
        if (i7 != 35) {
            if (i7 == 44) {
                return false;
            }
            if (i7 != 47 && i7 != 61) {
                if (i7 == 123 || i7 == 125 || i7 == 58) {
                    return false;
                }
                if (i7 != 59) {
                    switch (i7) {
                        case 91:
                        case 93:
                            return false;
                        case 92:
                            break;
                        default:
                            return true;
                    }
                }
            }
        }
        M();
        throw null;
    }

    public final String R() throws s, EOFException {
        String strT;
        int iN = this.f1829r;
        if (iN == 0) {
            iN = N();
        }
        if (iN == 14) {
            strT = U();
        } else if (iN == 13) {
            strT = T(f1825w);
        } else if (iN == 12) {
            strT = T(f1824v);
        } else {
            if (iN != 15) {
                throw new A9.k("Expected a name but was " + A7.k.w(z()) + " at path " + k());
            }
            strT = this.f1832u;
            this.f1832u = null;
        }
        this.f1829r = 0;
        this.f1823n[this.l - 1] = strT;
        return strT;
    }

    public final int S(boolean z6) throws s, EOFException {
        int i7 = 0;
        while (true) {
            int i10 = i7 + 1;
            C2319i c2319i = this.f1827p;
            if (!c2319i.l(i10)) {
                if (z6) {
                    throw new EOFException("End of input");
                }
                return -1;
            }
            long j3 = i7;
            C2319i c2319i2 = this.f1828q;
            byte bK = c2319i2.k(j3);
            if (bK != 10 && bK != 32 && bK != 13 && bK != 9) {
                c2319i2.R(j3);
                if (bK == 47) {
                    if (!c2319i.l(2L)) {
                        return bK;
                    }
                    M();
                    throw null;
                }
                if (bK != 35) {
                    return bK;
                }
                M();
                throw null;
            }
            i7 = i10;
        }
    }

    public final String T(C2322l c2322l) throws s {
        StringBuilder sb = null;
        while (true) {
            long jO = this.f1827p.o(c2322l);
            if (jO == -1) {
                L("Unterminated string");
                throw null;
            }
            C2319i c2319i = this.f1828q;
            if (c2319i.k(jO) != 92) {
                if (sb == null) {
                    String strP = c2319i.P(jO, Q8.a.f7211a);
                    c2319i.z();
                    return strP;
                }
                sb.append(c2319i.P(jO, Q8.a.f7211a));
                c2319i.z();
                return sb.toString();
            }
            if (sb == null) {
                sb = new StringBuilder();
            }
            sb.append(c2319i.P(jO, Q8.a.f7211a));
            c2319i.z();
            sb.append(V());
        }
    }

    public final String U() {
        long jO = this.f1827p.o(f1826x);
        C2319i c2319i = this.f1828q;
        if (jO == -1) {
            return c2319i.Q();
        }
        c2319i.getClass();
        return c2319i.P(jO, Q8.a.f7211a);
    }

    public final char V() throws s, EOFException {
        int i7;
        C2319i c2319i = this.f1827p;
        if (!c2319i.l(1L)) {
            L("Unterminated escape sequence");
            throw null;
        }
        C2319i c2319i2 = this.f1828q;
        byte bZ = c2319i2.z();
        if (bZ == 10 || bZ == 34 || bZ == 39 || bZ == 47 || bZ == 92) {
            return (char) bZ;
        }
        if (bZ == 98) {
            return '\b';
        }
        if (bZ == 102) {
            return '\f';
        }
        if (bZ == 110) {
            return '\n';
        }
        if (bZ == 114) {
            return '\r';
        }
        if (bZ == 116) {
            return '\t';
        }
        if (bZ != 117) {
            L("Invalid escape sequence: \\" + ((char) bZ));
            throw null;
        }
        if (!c2319i.l(4L)) {
            throw new EOFException("Unterminated escape sequence at path " + k());
        }
        char c = 0;
        for (int i10 = 0; i10 < 4; i10++) {
            byte bK = c2319i2.k(i10);
            char c10 = (char) (c << 4);
            if (bK >= 48 && bK <= 57) {
                i7 = bK - 48;
            } else if (bK >= 97 && bK <= 102) {
                i7 = bK - 87;
            } else {
                if (bK < 65 || bK > 70) {
                    L("\\u".concat(c2319i2.P(4L, Q8.a.f7211a)));
                    throw null;
                }
                i7 = bK - 55;
            }
            c = (char) (i7 + c10);
        }
        c2319i2.R(4L);
        return c;
    }

    public final void W(C2322l c2322l) throws s, EOFException {
        while (true) {
            long jO = this.f1827p.o(c2322l);
            if (jO == -1) {
                L("Unterminated string");
                throw null;
            }
            C2319i c2319i = this.f1828q;
            if (c2319i.k(jO) != 92) {
                c2319i.R(jO + 1);
                return;
            } else {
                c2319i.R(jO + 1);
                V();
            }
        }
    }

    @Override // E5.u
    public final void b() throws s, EOFException {
        int iN = this.f1829r;
        if (iN == 0) {
            iN = N();
        }
        if (iN == 3) {
            C(1);
            this.o[this.l - 1] = 0;
            this.f1829r = 0;
        } else {
            throw new A9.k("Expected BEGIN_ARRAY but was " + A7.k.w(z()) + " at path " + k());
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f1829r = 0;
        this.f1822m[0] = 8;
        this.l = 1;
        this.f1828q.b();
    }

    @Override // E5.u
    public final void g() throws s, EOFException {
        int iN = this.f1829r;
        if (iN == 0) {
            iN = N();
        }
        if (iN == 1) {
            C(3);
            this.f1829r = 0;
        } else {
            throw new A9.k("Expected BEGIN_OBJECT but was " + A7.k.w(z()) + " at path " + k());
        }
    }

    @Override // E5.u
    public final void i() throws s, EOFException {
        int iN = this.f1829r;
        if (iN == 0) {
            iN = N();
        }
        if (iN != 4) {
            throw new A9.k("Expected END_ARRAY but was " + A7.k.w(z()) + " at path " + k());
        }
        int i7 = this.l;
        this.l = i7 - 1;
        int[] iArr = this.o;
        int i10 = i7 - 2;
        iArr[i10] = iArr[i10] + 1;
        this.f1829r = 0;
    }

    @Override // E5.u
    public final void j() throws s, EOFException {
        int iN = this.f1829r;
        if (iN == 0) {
            iN = N();
        }
        if (iN != 2) {
            throw new A9.k("Expected END_OBJECT but was " + A7.k.w(z()) + " at path " + k());
        }
        int i7 = this.l;
        int i10 = i7 - 1;
        this.l = i10;
        this.f1823n[i10] = null;
        int[] iArr = this.o;
        int i11 = i7 - 2;
        iArr[i11] = iArr[i11] + 1;
        this.f1829r = 0;
    }

    @Override // E5.u
    public final boolean m() throws s, EOFException {
        int iN = this.f1829r;
        if (iN == 0) {
            iN = N();
        }
        return (iN == 2 || iN == 4 || iN == 18) ? false : true;
    }

    @Override // E5.u
    public final double o() throws s, EOFException {
        int iN = this.f1829r;
        if (iN == 0) {
            iN = N();
        }
        if (iN == 16) {
            this.f1829r = 0;
            int[] iArr = this.o;
            int i7 = this.l - 1;
            iArr[i7] = iArr[i7] + 1;
            return this.f1830s;
        }
        if (iN == 17) {
            long j3 = this.f1831t;
            C2319i c2319i = this.f1828q;
            c2319i.getClass();
            this.f1832u = c2319i.P(j3, Q8.a.f7211a);
        } else if (iN == 9) {
            this.f1832u = T(f1825w);
        } else if (iN == 8) {
            this.f1832u = T(f1824v);
        } else if (iN == 10) {
            this.f1832u = U();
        } else if (iN != 11) {
            throw new A9.k("Expected a double but was " + A7.k.w(z()) + " at path " + k());
        }
        this.f1829r = 11;
        try {
            double d = Double.parseDouble(this.f1832u);
            if (Double.isNaN(d) || Double.isInfinite(d)) {
                throw new s("JSON forbids NaN and infinities: " + d + " at path " + k());
            }
            this.f1832u = null;
            this.f1829r = 0;
            int[] iArr2 = this.o;
            int i10 = this.l - 1;
            iArr2[i10] = iArr2[i10] + 1;
            return d;
        } catch (NumberFormatException unused) {
            throw new A9.k("Expected a double but was " + this.f1832u + " at path " + k());
        }
    }

    @Override // E5.u
    public final int q() throws s, EOFException {
        int iN = this.f1829r;
        if (iN == 0) {
            iN = N();
        }
        if (iN == 16) {
            long j3 = this.f1830s;
            int i7 = (int) j3;
            if (j3 == i7) {
                this.f1829r = 0;
                int[] iArr = this.o;
                int i10 = this.l - 1;
                iArr[i10] = iArr[i10] + 1;
                return i7;
            }
            throw new A9.k("Expected an int but was " + this.f1830s + " at path " + k());
        }
        if (iN == 17) {
            long j10 = this.f1831t;
            C2319i c2319i = this.f1828q;
            c2319i.getClass();
            this.f1832u = c2319i.P(j10, Q8.a.f7211a);
        } else if (iN == 9 || iN == 8) {
            String strT = iN == 9 ? T(f1825w) : T(f1824v);
            this.f1832u = strT;
            try {
                int i11 = Integer.parseInt(strT);
                this.f1829r = 0;
                int[] iArr2 = this.o;
                int i12 = this.l - 1;
                iArr2[i12] = iArr2[i12] + 1;
                return i11;
            } catch (NumberFormatException unused) {
            }
        } else if (iN != 11) {
            throw new A9.k("Expected an int but was " + A7.k.w(z()) + " at path " + k());
        }
        this.f1829r = 11;
        try {
            double d = Double.parseDouble(this.f1832u);
            int i13 = (int) d;
            if (i13 != d) {
                throw new A9.k("Expected an int but was " + this.f1832u + " at path " + k());
            }
            this.f1832u = null;
            this.f1829r = 0;
            int[] iArr3 = this.o;
            int i14 = this.l - 1;
            iArr3[i14] = iArr3[i14] + 1;
            return i13;
        } catch (NumberFormatException unused2) {
            throw new A9.k("Expected an int but was " + this.f1832u + " at path " + k());
        }
    }

    public final String toString() {
        return "JsonReader(" + this.f1827p + ")";
    }

    @Override // E5.u
    public final void v() throws s, EOFException {
        int iN = this.f1829r;
        if (iN == 0) {
            iN = N();
        }
        if (iN == 7) {
            this.f1829r = 0;
            int[] iArr = this.o;
            int i7 = this.l - 1;
            iArr[i7] = iArr[i7] + 1;
            return;
        }
        throw new A9.k("Expected null but was " + A7.k.w(z()) + " at path " + k());
    }

    @Override // E5.u
    public final String y() throws s, EOFException {
        String strP;
        int iN = this.f1829r;
        if (iN == 0) {
            iN = N();
        }
        if (iN == 10) {
            strP = U();
        } else if (iN == 9) {
            strP = T(f1825w);
        } else if (iN == 8) {
            strP = T(f1824v);
        } else if (iN == 11) {
            strP = this.f1832u;
            this.f1832u = null;
        } else if (iN == 16) {
            strP = Long.toString(this.f1830s);
        } else {
            if (iN != 17) {
                throw new A9.k("Expected a string but was " + A7.k.w(z()) + " at path " + k());
            }
            long j3 = this.f1831t;
            C2319i c2319i = this.f1828q;
            c2319i.getClass();
            strP = c2319i.P(j3, Q8.a.f7211a);
        }
        this.f1829r = 0;
        int[] iArr = this.o;
        int i7 = this.l - 1;
        iArr[i7] = iArr[i7] + 1;
        return strP;
    }

    @Override // E5.u
    public final int z() throws s, EOFException {
        int iN = this.f1829r;
        if (iN == 0) {
            iN = N();
        }
        switch (iN) {
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
            case 15:
                return 5;
            case 16:
            case 17:
                return 7;
            case 18:
                return 10;
            default:
                throw new AssertionError();
        }
    }
}
