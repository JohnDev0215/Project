package Z3;

import A9.k;
import E5.s;
import io.sentry.internal.debugmeta.c;
import java.io.EOFException;
import java.io.IOException;
import r9.C2319i;
import r9.C2322l;
import r9.D;
import r9.y;

/* JADX INFO: loaded from: classes.dex */
public final class b extends a {

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final C2322l f10888w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final C2322l f10889x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final C2322l f10890y;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final D f10891q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final C2319i f10892r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f10893s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public long f10894t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f10895u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public String f10896v;

    static {
        C2322l c2322l = C2322l.o;
        f10888w = m6.b.p("'\\");
        f10889x = m6.b.p("\"\\");
        f10890y = m6.b.p("{}[]:, \n\t\r\f/\\;#=");
        m6.b.p("\n\r");
        m6.b.p("*/");
    }

    public b(D d) {
        this.f10886m = new int[32];
        this.f10887n = new String[32];
        this.o = new int[32];
        this.f10893s = 0;
        this.f10891q = d;
        this.f10892r = d.f21897m;
        C(6);
    }

    @Override // Z3.a
    public final int F(c cVar) {
        int iM = this.f10893s;
        if (iM == 0) {
            iM = M();
        }
        if (iM < 12 || iM > 15) {
            return -1;
        }
        if (iM == 15) {
            return N(this.f10896v, cVar);
        }
        int iX = this.f10891q.x((y) cVar.f16570n);
        if (iX != -1) {
            this.f10893s = 0;
            this.f10887n[this.l - 1] = ((String[]) cVar.f16569m)[iX];
            return iX;
        }
        String str = this.f10887n[this.l - 1];
        String strP = P();
        int iN = N(strP, cVar);
        if (iN == -1) {
            this.f10893s = 15;
            this.f10896v = strP;
            this.f10887n[this.l - 1] = str;
        }
        return iN;
    }

    @Override // Z3.a
    public final void G() {
        int iM = this.f10893s;
        if (iM == 0) {
            iM = M();
        }
        if (iM == 14) {
            long jI = this.f10891q.i(f10890y);
            C2319i c2319i = this.f10892r;
            if (jI == -1) {
                jI = c2319i.f21932m;
            }
            c2319i.R(jI);
        } else if (iM == 13) {
            U(f10889x);
        } else if (iM == 12) {
            U(f10888w);
        } else if (iM != 15) {
            throw new k("Expected a name but was " + M5.b.v(z()) + " at path " + k());
        }
        this.f10893s = 0;
        this.f10887n[this.l - 1] = "null";
    }

    @Override // Z3.a
    public final void I() {
        int i7 = 0;
        do {
            int iM = this.f10893s;
            if (iM == 0) {
                iM = M();
            }
            if (iM == 3) {
                C(1);
            } else if (iM == 1) {
                C(3);
            } else {
                if (iM == 4) {
                    i7--;
                    if (i7 < 0) {
                        throw new k("Expected a value but was " + M5.b.v(z()) + " at path " + k());
                    }
                    this.l--;
                } else if (iM == 2) {
                    i7--;
                    if (i7 < 0) {
                        throw new k("Expected a value but was " + M5.b.v(z()) + " at path " + k());
                    }
                    this.l--;
                } else {
                    C2319i c2319i = this.f10892r;
                    if (iM == 14 || iM == 10) {
                        long jI = this.f10891q.i(f10890y);
                        if (jI == -1) {
                            jI = c2319i.f21932m;
                        }
                        c2319i.R(jI);
                    } else if (iM == 9 || iM == 13) {
                        U(f10889x);
                    } else if (iM == 8 || iM == 12) {
                        U(f10888w);
                    } else if (iM == 17) {
                        c2319i.R(this.f10895u);
                    } else if (iM == 18) {
                        throw new k("Expected a value but was " + M5.b.v(z()) + " at path " + k());
                    }
                }
                this.f10893s = 0;
            }
            i7++;
            this.f10893s = 0;
        } while (i7 != 0);
        int[] iArr = this.o;
        int i10 = this.l - 1;
        iArr[i10] = iArr[i10] + 1;
        this.f10887n[i10] = "null";
    }

    public final void L() throws s {
        K("Use JsonReader.setLenient(true) to accept malformed JSON");
        throw null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:135:0x0198, code lost:
    
        if (O(r1) != false) goto L114;
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x019a, code lost:
    
        if (r4 != 2) goto L149;
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x019c, code lost:
    
        if (r10 == false) goto L149;
     */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x01a2, code lost:
    
        if (r11 != Long.MIN_VALUE) goto L141;
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x01a4, code lost:
    
        if (r13 == false) goto L149;
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x01aa, code lost:
    
        if (r11 != 0) goto L144;
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x01ac, code lost:
    
        if (r13 != false) goto L149;
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x01ae, code lost:
    
        if (r13 == false) goto L146;
     */
    /* JADX WARN: Code restructure failed: missing block: B:146:0x01b1, code lost:
    
        r11 = -r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x01b2, code lost:
    
        r21.f10894t = r11;
        r9.R(r5);
        r1 = 16;
        r21.f10893s = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x01bc, code lost:
    
        r16 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x01bf, code lost:
    
        if (r4 == 2) goto L154;
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x01c2, code lost:
    
        if (r4 == 4) goto L154;
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x01c5, code lost:
    
        if (r4 != 7) goto L114;
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x01c7, code lost:
    
        r21.f10895u = r5;
        r1 = 17;
        r21.f10893s = 17;
     */
    /* JADX WARN: Removed duplicated region for block: B:173:0x01f4 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:174:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x0239  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0111 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0112  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int M() throws E5.s, java.io.EOFException {
        /*
            Method dump skipped, instruction units count: 669
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: Z3.b.M():int");
    }

    public final int N(String str, c cVar) {
        int length = ((String[]) cVar.f16569m).length;
        for (int i7 = 0; i7 < length; i7++) {
            if (str.equals(((String[]) cVar.f16569m)[i7])) {
                this.f10893s = 0;
                this.f10887n[this.l - 1] = str;
                return i7;
            }
        }
        return -1;
    }

    public final boolean O(int i7) throws s {
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
        L();
        throw null;
    }

    public final String P() {
        String strR;
        int iM = this.f10893s;
        if (iM == 0) {
            iM = M();
        }
        if (iM == 14) {
            strR = S();
        } else if (iM == 13) {
            strR = R(f10889x);
        } else if (iM == 12) {
            strR = R(f10888w);
        } else {
            if (iM != 15) {
                throw new k("Expected a name but was " + M5.b.v(z()) + " at path " + k());
            }
            strR = this.f10896v;
        }
        this.f10893s = 0;
        this.f10887n[this.l - 1] = strR;
        return strR;
    }

    public final int Q(boolean z6) throws s, EOFException {
        int i7 = 0;
        while (true) {
            int i10 = i7 + 1;
            D d = this.f10891q;
            if (!d.l(i10)) {
                if (z6) {
                    throw new EOFException("End of input");
                }
                return -1;
            }
            long j3 = i7;
            C2319i c2319i = this.f10892r;
            byte bK = c2319i.k(j3);
            if (bK != 10 && bK != 32 && bK != 13 && bK != 9) {
                c2319i.R(j3);
                if (bK == 47) {
                    if (!d.l(2L)) {
                        return bK;
                    }
                    L();
                    throw null;
                }
                if (bK != 35) {
                    return bK;
                }
                L();
                throw null;
            }
            i7 = i10;
        }
    }

    public final String R(C2322l c2322l) throws s {
        StringBuilder sb = null;
        while (true) {
            long jI = this.f10891q.i(c2322l);
            if (jI == -1) {
                K("Unterminated string");
                throw null;
            }
            C2319i c2319i = this.f10892r;
            if (c2319i.k(jI) != 92) {
                if (sb == null) {
                    String strP = c2319i.P(jI, Q8.a.f7211a);
                    c2319i.z();
                    return strP;
                }
                sb.append(c2319i.P(jI, Q8.a.f7211a));
                c2319i.z();
                return sb.toString();
            }
            if (sb == null) {
                sb = new StringBuilder();
            }
            sb.append(c2319i.P(jI, Q8.a.f7211a));
            c2319i.z();
            sb.append(T());
        }
    }

    public final String S() {
        long jI = this.f10891q.i(f10890y);
        C2319i c2319i = this.f10892r;
        if (jI == -1) {
            return c2319i.Q();
        }
        c2319i.getClass();
        return c2319i.P(jI, Q8.a.f7211a);
    }

    public final char T() throws s, EOFException {
        int i7;
        D d = this.f10891q;
        if (!d.l(1L)) {
            K("Unterminated escape sequence");
            throw null;
        }
        C2319i c2319i = this.f10892r;
        byte bZ = c2319i.z();
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
            K("Invalid escape sequence: \\" + ((char) bZ));
            throw null;
        }
        if (!d.l(4L)) {
            throw new EOFException("Unterminated escape sequence at path " + k());
        }
        char c = 0;
        for (int i10 = 0; i10 < 4; i10++) {
            byte bK = c2319i.k(i10);
            char c10 = (char) (c << 4);
            if (bK >= 48 && bK <= 57) {
                i7 = bK - 48;
            } else if (bK >= 97 && bK <= 102) {
                i7 = bK - 87;
            } else {
                if (bK < 65 || bK > 70) {
                    K("\\u".concat(c2319i.P(4L, Q8.a.f7211a)));
                    throw null;
                }
                i7 = bK - 55;
            }
            c = (char) (i7 + c10);
        }
        c2319i.R(4L);
        return c;
    }

    public final void U(C2322l c2322l) throws s, EOFException {
        while (true) {
            long jI = this.f10891q.i(c2322l);
            if (jI == -1) {
                K("Unterminated string");
                throw null;
            }
            C2319i c2319i = this.f10892r;
            if (c2319i.k(jI) != 92) {
                c2319i.R(jI + 1);
                return;
            } else {
                c2319i.R(jI + 1);
                T();
            }
        }
    }

    @Override // Z3.a
    public final void b() {
        int iM = this.f10893s;
        if (iM == 0) {
            iM = M();
        }
        if (iM == 3) {
            C(1);
            this.o[this.l - 1] = 0;
            this.f10893s = 0;
        } else {
            throw new k("Expected BEGIN_ARRAY but was " + M5.b.v(z()) + " at path " + k());
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.f10893s = 0;
        this.f10886m[0] = 8;
        this.l = 1;
        this.f10892r.b();
        this.f10891q.close();
    }

    @Override // Z3.a
    public final void g() {
        int iM = this.f10893s;
        if (iM == 0) {
            iM = M();
        }
        if (iM == 1) {
            C(3);
            this.f10893s = 0;
        } else {
            throw new k("Expected BEGIN_OBJECT but was " + M5.b.v(z()) + " at path " + k());
        }
    }

    @Override // Z3.a
    public final void i() {
        int iM = this.f10893s;
        if (iM == 0) {
            iM = M();
        }
        if (iM != 4) {
            throw new k("Expected END_ARRAY but was " + M5.b.v(z()) + " at path " + k());
        }
        int i7 = this.l;
        this.l = i7 - 1;
        int[] iArr = this.o;
        int i10 = i7 - 2;
        iArr[i10] = iArr[i10] + 1;
        this.f10893s = 0;
    }

    @Override // Z3.a
    public final void j() {
        int iM = this.f10893s;
        if (iM == 0) {
            iM = M();
        }
        if (iM != 2) {
            throw new k("Expected END_OBJECT but was " + M5.b.v(z()) + " at path " + k());
        }
        int i7 = this.l;
        int i10 = i7 - 1;
        this.l = i10;
        this.f10887n[i10] = null;
        int[] iArr = this.o;
        int i11 = i7 - 2;
        iArr[i11] = iArr[i11] + 1;
        this.f10893s = 0;
    }

    @Override // Z3.a
    public final boolean m() throws s, EOFException {
        int iM = this.f10893s;
        if (iM == 0) {
            iM = M();
        }
        return (iM == 2 || iM == 4 || iM == 18) ? false : true;
    }

    @Override // Z3.a
    public final boolean o() {
        int iM = this.f10893s;
        if (iM == 0) {
            iM = M();
        }
        if (iM == 5) {
            this.f10893s = 0;
            int[] iArr = this.o;
            int i7 = this.l - 1;
            iArr[i7] = iArr[i7] + 1;
            return true;
        }
        if (iM == 6) {
            this.f10893s = 0;
            int[] iArr2 = this.o;
            int i10 = this.l - 1;
            iArr2[i10] = iArr2[i10] + 1;
            return false;
        }
        throw new k("Expected a boolean but was " + M5.b.v(z()) + " at path " + k());
    }

    @Override // Z3.a
    public final double q() throws s, EOFException {
        int iM = this.f10893s;
        if (iM == 0) {
            iM = M();
        }
        if (iM == 16) {
            this.f10893s = 0;
            int[] iArr = this.o;
            int i7 = this.l - 1;
            iArr[i7] = iArr[i7] + 1;
            return this.f10894t;
        }
        if (iM == 17) {
            long j3 = this.f10895u;
            C2319i c2319i = this.f10892r;
            c2319i.getClass();
            this.f10896v = c2319i.P(j3, Q8.a.f7211a);
        } else if (iM == 9) {
            this.f10896v = R(f10889x);
        } else if (iM == 8) {
            this.f10896v = R(f10888w);
        } else if (iM == 10) {
            this.f10896v = S();
        } else if (iM != 11) {
            throw new k("Expected a double but was " + M5.b.v(z()) + " at path " + k());
        }
        this.f10893s = 11;
        try {
            double d = Double.parseDouble(this.f10896v);
            if (Double.isNaN(d) || Double.isInfinite(d)) {
                throw new s("JSON forbids NaN and infinities: " + d + " at path " + k());
            }
            this.f10896v = null;
            this.f10893s = 0;
            int[] iArr2 = this.o;
            int i10 = this.l - 1;
            iArr2[i10] = iArr2[i10] + 1;
            return d;
        } catch (NumberFormatException unused) {
            throw new k("Expected a double but was " + this.f10896v + " at path " + k());
        }
    }

    public final String toString() {
        return "JsonReader(" + this.f10891q + ")";
    }

    @Override // Z3.a
    public final int v() {
        int iM = this.f10893s;
        if (iM == 0) {
            iM = M();
        }
        if (iM == 16) {
            long j3 = this.f10894t;
            int i7 = (int) j3;
            if (j3 == i7) {
                this.f10893s = 0;
                int[] iArr = this.o;
                int i10 = this.l - 1;
                iArr[i10] = iArr[i10] + 1;
                return i7;
            }
            throw new k("Expected an int but was " + this.f10894t + " at path " + k());
        }
        if (iM == 17) {
            long j10 = this.f10895u;
            C2319i c2319i = this.f10892r;
            c2319i.getClass();
            this.f10896v = c2319i.P(j10, Q8.a.f7211a);
        } else if (iM == 9 || iM == 8) {
            String strR = iM == 9 ? R(f10889x) : R(f10888w);
            this.f10896v = strR;
            try {
                int i11 = Integer.parseInt(strR);
                this.f10893s = 0;
                int[] iArr2 = this.o;
                int i12 = this.l - 1;
                iArr2[i12] = iArr2[i12] + 1;
                return i11;
            } catch (NumberFormatException unused) {
            }
        } else if (iM != 11) {
            throw new k("Expected an int but was " + M5.b.v(z()) + " at path " + k());
        }
        this.f10893s = 11;
        try {
            double d = Double.parseDouble(this.f10896v);
            int i13 = (int) d;
            if (i13 != d) {
                throw new k("Expected an int but was " + this.f10896v + " at path " + k());
            }
            this.f10896v = null;
            this.f10893s = 0;
            int[] iArr3 = this.o;
            int i14 = this.l - 1;
            iArr3[i14] = iArr3[i14] + 1;
            return i13;
        } catch (NumberFormatException unused2) {
            throw new k("Expected an int but was " + this.f10896v + " at path " + k());
        }
    }

    @Override // Z3.a
    public final String y() {
        String strP;
        int iM = this.f10893s;
        if (iM == 0) {
            iM = M();
        }
        if (iM == 10) {
            strP = S();
        } else if (iM == 9) {
            strP = R(f10889x);
        } else if (iM == 8) {
            strP = R(f10888w);
        } else if (iM == 11) {
            strP = this.f10896v;
            this.f10896v = null;
        } else if (iM == 16) {
            strP = Long.toString(this.f10894t);
        } else {
            if (iM != 17) {
                throw new k("Expected a string but was " + M5.b.v(z()) + " at path " + k());
            }
            long j3 = this.f10895u;
            C2319i c2319i = this.f10892r;
            c2319i.getClass();
            strP = c2319i.P(j3, Q8.a.f7211a);
        }
        this.f10893s = 0;
        int[] iArr = this.o;
        int i7 = this.l - 1;
        iArr[i7] = iArr[i7] + 1;
        return strP;
    }

    @Override // Z3.a
    public final int z() throws s, EOFException {
        int iM = this.f10893s;
        if (iM == 0) {
            iM = M();
        }
        switch (iM) {
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
