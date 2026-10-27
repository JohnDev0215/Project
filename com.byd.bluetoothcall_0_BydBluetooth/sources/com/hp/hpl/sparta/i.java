package com.hp.hpl.sparta;

import java.io.IOException;
import java.io.Reader;
import java.util.Hashtable;

/* JADX INFO: loaded from: classes.dex */
class i implements l {

    /* JADX INFO: renamed from: c */
    private static final char[] f4178c = {'.', '-', '_', ':'};

    /* JADX INFO: renamed from: d */
    private static final boolean[] f4179d = new boolean[128];

    /* JADX INFO: renamed from: e */
    private static final char[] f4180e;
    private static final char[] f;
    private static final char[] g;
    private static final char[] h;
    private static final char[] i;
    private static final char[] j;
    private static final char[] k;
    private static final char[] l;
    private static final char[] m;
    private static final char[] n;
    private static final char[] o;
    private static final char[] p;
    private static final char[] q;
    private static final char[] r;
    private static final char[] s;
    private static final char[] t;
    private static final char[] u;
    private static final char[] v;
    private static final char[] w;
    private final Hashtable A;
    private final Hashtable B;
    private final k C;
    private final String D;
    private int E;
    private boolean F;
    private final int G;
    private final char[] H;
    private int I;
    private int J;
    private boolean K;
    private final char[] L;
    private int M;
    private final j N;
    private String x;
    private String y;
    private final Reader z;

    static {
        for (char c2 = 0; c2 < 128; c2 = (char) (c2 + 1)) {
            f4179d[c2] = y(c2);
        }
        f4180e = "<!--".toCharArray();
        f = "-->".toCharArray();
        g = "<?".toCharArray();
        h = "?>".toCharArray();
        i = "<!DOCTYPE".toCharArray();
        j = "<?xml".toCharArray();
        k = "encoding".toCharArray();
        l = "version".toCharArray();
        m = new char[]{'_', '.', ':', '-'};
        n = "<!".toCharArray();
        o = "&#".toCharArray();
        p = "<!ENTITY".toCharArray();
        q = "NDATA".toCharArray();
        r = "SYSTEM".toCharArray();
        s = "PUBLIC".toCharArray();
        t = "<![CDATA[".toCharArray();
        u = "]]>".toCharArray();
        v = "/>".toCharArray();
        w = "</".toCharArray();
    }

    public i(String str, Reader reader, k kVar, String str2, j jVar) throws ParseException, IOException {
        this(str, reader, null, kVar, str2, jVar);
    }

    public i(String str, Reader reader, char[] cArr, k kVar, String str2, j jVar) throws ParseException, IOException {
        this.y = null;
        Hashtable hashtable = new Hashtable();
        this.A = hashtable;
        this.B = new Hashtable();
        this.E = -2;
        this.F = false;
        this.G = 1024;
        this.I = 0;
        this.J = 0;
        this.K = false;
        this.L = new char[255];
        this.M = -1;
        this.M = 1;
        kVar = kVar == null ? l.f4181a : kVar;
        this.C = kVar;
        this.D = str2 == null ? null : str2.toLowerCase();
        hashtable.put("lt", "<");
        hashtable.put("gt", ">");
        hashtable.put("amp", "&");
        hashtable.put("apos", "'");
        hashtable.put("quot", "\"");
        if (cArr != null) {
            this.H = cArr;
            this.I = 0;
            this.J = cArr.length;
            this.K = true;
            this.z = null;
        } else {
            this.z = reader;
            this.H = new char[1024];
            a();
        }
        this.x = str;
        this.N = jVar;
        jVar.b(this);
        j0();
        jVar.f();
        d dVarU = U();
        String str3 = this.y;
        if (str3 != null && !str3.equals(dVarU.v())) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("DOCTYPE name \"");
            stringBuffer.append(this.y);
            stringBuffer.append("\" not same as tag name, \"");
            stringBuffer.append(dVarU.v());
            stringBuffer.append("\" of root element");
            kVar.c(stringBuffer.toString(), this.x, e());
        }
        while (w()) {
            d0();
        }
        Reader reader2 = this.z;
        if (reader2 != null) {
            reader2.close();
        }
        this.N.a();
    }

    private final boolean A() throws ParseException, IOException {
        return D(g);
    }

    private final boolean B() throws ParseException, IOException {
        return i('&');
    }

    private final boolean C() throws ParseException, IOException {
        return k(' ', '\t', '\r', '\n');
    }

    private final boolean D(char[] cArr) throws ParseException, IOException {
        int length = cArr.length;
        if (this.J - this.I < length && b(length) <= 0) {
            this.E = -1;
            return false;
        }
        char[] cArr2 = this.H;
        int i2 = this.J;
        this.E = cArr2[i2 - 1];
        if (i2 - this.I < length) {
            return false;
        }
        for (int i3 = 0; i3 < length; i3++) {
            if (this.H[this.I + i3] != cArr[i3]) {
                return false;
            }
        }
        return true;
    }

    private boolean E() throws ParseException, IOException {
        char cG = G();
        return Character.isDigit(cG) || ('a' <= cG && cG <= 'z') || (('Z' <= cG && cG <= 'Z') || u(cG, m));
    }

    private boolean F() throws ParseException, IOException {
        return D(j);
    }

    private final char G() throws ParseException, IOException {
        if (this.I < this.J || a() != -1) {
            return this.H[this.I];
        }
        throw new ParseException(this, "unexpected end of expression.");
    }

    private String H() throws ParseException, IOException {
        char cL = L('\'', '\"');
        StringBuffer stringBuffer = new StringBuffer();
        while (!i(cL)) {
            if (B()) {
                stringBuffer.append(l0());
            } else {
                stringBuffer.append(K());
            }
        }
        N(cL);
        return stringBuffer.toString();
    }

    private void I(d dVar) throws ParseException, IOException {
        String strE0 = e0();
        a0();
        String strH = H();
        if (dVar.s(strE0) != null) {
            k kVar = this.C;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Element ");
            stringBuffer.append(this);
            stringBuffer.append(" contains attribute ");
            stringBuffer.append(strE0);
            stringBuffer.append("more than once");
            kVar.c(stringBuffer.toString(), this.x, e());
        }
        dVar.x(strE0, strH);
    }

    private void J() throws ParseException, IOException {
        char[] cArr;
        n0(t);
        StringBuffer stringBuffer = null;
        int i2 = 0;
        while (true) {
            cArr = u;
            if (D(cArr)) {
                break;
            }
            if (i2 >= 255) {
                if (stringBuffer == null) {
                    stringBuffer = new StringBuffer(i2);
                    stringBuffer.append(this.L, 0, i2);
                } else {
                    stringBuffer.append(this.L, 0, i2);
                }
                i2 = 0;
            }
            this.L[i2] = K();
            i2++;
        }
        n0(cArr);
        if (stringBuffer == null) {
            this.N.e(this.L, 0, i2);
            return;
        }
        stringBuffer.append(this.L, 0, i2);
        char[] charArray = stringBuffer.toString().toCharArray();
        this.N.e(charArray, 0, charArray.length);
    }

    private final char K() throws ParseException, IOException {
        if (this.I >= this.J && a() == -1) {
            throw new ParseException(this, "unexpected end of expression.");
        }
        char[] cArr = this.H;
        int i2 = this.I;
        if (cArr[i2] == '\n') {
            this.M++;
        }
        this.I = i2 + 1;
        return cArr[i2];
    }

    private final char L(char c2, char c3) throws ParseException, IOException {
        char cK = K();
        if (cK == c2 || cK == c3) {
            return cK;
        }
        throw new ParseException(this, cK, new char[]{c2, c3});
    }

    private final char M(char c2, char c3, char c4, char c5) throws ParseException, IOException {
        char cK = K();
        if (cK == c2 || cK == c3 || cK == c4 || cK == c5) {
            return cK;
        }
        throw new ParseException(this, cK, new char[]{c2, c3, c4, c5});
    }

    private final void N(char c2) throws ParseException, IOException {
        char cK = K();
        if (cK != c2) {
            throw new ParseException(this, cK, c2);
        }
    }

    private char O() throws ParseException, IOException {
        int i2;
        n0(o);
        if (i('x')) {
            K();
            i2 = 16;
        } else {
            i2 = 10;
        }
        int i3 = 0;
        while (!i(';')) {
            int i4 = i3 + 1;
            this.L[i3] = K();
            if (i4 >= 255) {
                this.C.c("Tmp buffer overflow on readCharRef", this.x, e());
                return ' ';
            }
            i3 = i4;
        }
        N(';');
        String str = new String(this.L, 0, i3);
        try {
            return (char) Integer.parseInt(str, i2);
        } catch (NumberFormatException unused) {
            k kVar = this.C;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("\"");
            stringBuffer.append(str);
            stringBuffer.append("\" is not a valid ");
            stringBuffer.append(i2 == 16 ? "hexadecimal" : "decimal");
            stringBuffer.append(" number");
            kVar.c(stringBuffer.toString(), this.x, e());
            return ' ';
        }
    }

    private final void P() throws ParseException, IOException {
        n0(f4180e);
        while (true) {
            char[] cArr = f;
            if (D(cArr)) {
                n0(cArr);
                return;
            }
            K();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x000e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void Q() throws com.hp.hpl.sparta.ParseException, java.io.IOException {
        /*
            r5 = this;
            r5.i0()
            r0 = 1
        L4:
            if (r0 != 0) goto L7
            return
        L7:
            boolean r1 = r5.o()
            r2 = 0
            if (r1 == 0) goto L10
        Le:
            r0 = r2
            goto L4a
        L10:
            boolean r1 = r5.B()
            if (r1 == 0) goto L21
            char[] r1 = r5.l0()
            com.hp.hpl.sparta.j r3 = r5.N
            int r4 = r1.length
            r3.e(r1, r2, r4)
            goto L4a
        L21:
            boolean r1 = r5.h()
            if (r1 == 0) goto L2b
            r5.J()
            goto L4a
        L2b:
            boolean r1 = r5.A()
            if (r1 == 0) goto L35
            r5.h0()
            goto L4a
        L35:
            boolean r1 = r5.l()
            if (r1 == 0) goto L3f
            r5.P()
            goto L4a
        L3f:
            r1 = 60
            boolean r1 = r5.i(r1)
            if (r1 == 0) goto Le
            r5.U()
        L4a:
            r5.i0()
            goto L4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hp.hpl.sparta.i.Q():void");
    }

    private void R() throws ParseException, IOException {
        if (z()) {
            g0();
        } else {
            m0();
        }
    }

    private void S() throws ParseException, IOException {
        n0(i);
        m0();
        this.y = e0();
        if (C()) {
            m0();
            if (!i('>') && !i('[')) {
                this.F = true;
                b0();
                if (C()) {
                    m0();
                }
            }
        }
        if (i('[')) {
            K();
            while (!i(']')) {
                if (m()) {
                    R();
                } else {
                    c0();
                }
            }
            N(']');
            if (C()) {
                m0();
            }
        }
        N('>');
    }

    private void T(d dVar) throws ParseException, IOException {
        n0(w);
        String strE0 = e0();
        if (!strE0.equals(dVar.v())) {
            k kVar = this.C;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("end tag (");
            stringBuffer.append(strE0);
            stringBuffer.append(") does not match begin tag (");
            stringBuffer.append(dVar.v());
            stringBuffer.append(")");
            kVar.c(stringBuffer.toString(), this.x, e());
        }
        if (C()) {
            m0();
        }
        N('>');
    }

    private final d U() throws ParseException, IOException {
        d dVar = new d();
        boolean zV = V(dVar);
        this.N.d(dVar);
        if (zV) {
            Q();
            T(dVar);
        }
        this.N.c(dVar);
        return dVar;
    }

    private boolean V(d dVar) throws ParseException, IOException {
        N('<');
        dVar.y(e0());
        while (C()) {
            m0();
            if (!j('/', '>')) {
                I(dVar);
            }
        }
        if (C()) {
            m0();
        }
        boolean zI = i('>');
        if (zI) {
            N('>');
        } else {
            n0(v);
        }
        return zI;
    }

    private String W() throws ParseException, IOException {
        n0(k);
        a0();
        char cL = L('\'', '\"');
        StringBuffer stringBuffer = new StringBuffer();
        while (!i(cL)) {
            stringBuffer.append(K());
        }
        N(cL);
        return stringBuffer.toString();
    }

    private void X() throws ParseException, IOException {
        String strE0;
        String strB0;
        Hashtable hashtable;
        n0(p);
        m0();
        if (i('%')) {
            N('%');
            m0();
            strE0 = e0();
            m0();
            strB0 = r() ? Z() : b0();
            hashtable = this.B;
        } else {
            strE0 = e0();
            m0();
            if (r()) {
                strB0 = Z();
            } else {
                if (!t()) {
                    throw new ParseException(this, "expecting double-quote, \"PUBLIC\" or \"SYSTEM\" while reading entity declaration");
                }
                strB0 = b0();
                if (C()) {
                    m0();
                }
                char[] cArr = q;
                if (D(cArr)) {
                    n0(cArr);
                    m0();
                    e0();
                }
            }
            hashtable = this.A;
        }
        hashtable.put(strE0, strB0);
        if (C()) {
            m0();
        }
        N('>');
    }

    private String Y() throws ParseException, IOException {
        k kVar;
        StringBuffer stringBuffer;
        String str;
        N('&');
        String strE0 = e0();
        String str2 = (String) this.A.get(strE0);
        if (str2 == null) {
            if (this.F) {
                kVar = this.C;
                stringBuffer = new StringBuffer();
                stringBuffer.append("&");
                stringBuffer.append(strE0);
                str = "; not found -- possibly defined in external DTD)";
            } else {
                kVar = this.C;
                stringBuffer = new StringBuffer();
                stringBuffer.append("No declaration of &");
                stringBuffer.append(strE0);
                str = ";";
            }
            stringBuffer.append(str);
            kVar.c(stringBuffer.toString(), this.x, e());
            str2 = "";
        }
        N(';');
        return str2;
    }

    private final String Z() throws ParseException, IOException {
        char cL = L('\'', '\"');
        StringBuffer stringBuffer = new StringBuffer();
        while (!i(cL)) {
            if (z()) {
                stringBuffer.append(g0());
            } else if (B()) {
                stringBuffer.append(l0());
            } else {
                stringBuffer.append(K());
            }
        }
        N(cL);
        return stringBuffer.toString();
    }

    private int a() throws IOException {
        if (this.K) {
            return -1;
        }
        int i2 = this.J;
        char[] cArr = this.H;
        if (i2 == cArr.length) {
            this.J = 0;
            this.I = 0;
        }
        Reader reader = this.z;
        int i3 = this.J;
        int i4 = reader.read(cArr, i3, cArr.length - i3);
        if (i4 <= 0) {
            this.K = true;
            return -1;
        }
        this.J += i4;
        return i4;
    }

    private final void a0() throws ParseException, IOException {
        if (C()) {
            m0();
        }
        N('=');
        if (C()) {
            m0();
        }
    }

    private int b(int i2) throws IOException {
        int i3;
        int i4;
        if (this.K) {
            return -1;
        }
        int i5 = 0;
        if (this.H.length - this.I < i2) {
            int i6 = 0;
            while (true) {
                i3 = this.I;
                int i7 = i3 + i6;
                i4 = this.J;
                if (i7 >= i4) {
                    break;
                }
                char[] cArr = this.H;
                cArr[i6] = cArr[i3 + i6];
                i6++;
            }
            int i8 = i4 - i3;
            this.J = i8;
            this.I = 0;
            i5 = i8;
        }
        int iA = a();
        if (iA != -1) {
            return i5 + iA;
        }
        if (i5 == 0) {
            return -1;
        }
        return i5;
    }

    private String b0() throws ParseException, IOException {
        char[] cArr = r;
        if (D(cArr)) {
            n0(cArr);
        } else {
            char[] cArr2 = s;
            if (!D(cArr2)) {
                throw new ParseException(this, "expecting \"SYSTEM\" or \"PUBLIC\" while reading external ID");
            }
            n0(cArr2);
            m0();
            k0();
        }
        m0();
        o0();
        return "(WARNING: external ID not read)";
    }

    private void c0() throws ParseException, IOException {
        if (A()) {
            h0();
            return;
        }
        if (l()) {
            P();
            return;
        }
        if (q()) {
            X();
            return;
        }
        if (!D(n)) {
            throw new ParseException(this, "expecting processing instruction, comment, or \"<!\"");
        }
        while (!i('>')) {
            if (j('\'', '\"')) {
                char cK = K();
                while (!i(cK)) {
                    K();
                }
                N(cK);
            } else {
                K();
            }
        }
        N('>');
    }

    private void d0() throws ParseException, IOException {
        if (l()) {
            P();
        } else if (A()) {
            h0();
        } else {
            if (!C()) {
                throw new ParseException(this, "expecting comment or processing instruction or space");
            }
            m0();
        }
    }

    private final String e0() throws ParseException, IOException {
        this.L[0] = f0();
        int i2 = 1;
        StringBuffer stringBuffer = null;
        while (x()) {
            if (i2 >= 255) {
                if (stringBuffer == null) {
                    stringBuffer = new StringBuffer(i2);
                    stringBuffer.append(this.L, 0, i2);
                } else {
                    stringBuffer.append(this.L, 0, i2);
                }
                i2 = 0;
            }
            this.L[i2] = K();
            i2++;
        }
        if (stringBuffer == null) {
            return Sparta.a(new String(this.L, 0, i2));
        }
        stringBuffer.append(this.L, 0, i2);
        return stringBuffer.toString();
    }

    private char f0() throws ParseException, IOException {
        char cK = K();
        if (v(cK) || cK == '_' || cK == ':') {
            return cK;
        }
        throw new ParseException(this, cK, "letter, underscore, colon");
    }

    private String g0() throws ParseException, IOException {
        N('%');
        String strE0 = e0();
        String str = (String) this.B.get(strE0);
        if (str == null) {
            k kVar = this.C;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("No declaration of %");
            stringBuffer.append(strE0);
            stringBuffer.append(";");
            kVar.c(stringBuffer.toString(), this.x, e());
            str = "";
        }
        N(';');
        return str;
    }

    private boolean h() throws ParseException, IOException {
        return D(t);
    }

    private final void h0() throws ParseException, IOException {
        n0(g);
        while (true) {
            char[] cArr = h;
            if (D(cArr)) {
                n0(cArr);
                return;
            }
            K();
        }
    }

    private final boolean i(char c2) throws ParseException, IOException {
        if (this.I < this.J || a() != -1) {
            return this.H[this.I] == c2;
        }
        throw new ParseException(this, "unexpected end of expression.");
    }

    private void i0() throws ParseException, IOException {
        int i2;
        loop0: while (true) {
            i2 = 0;
            while (!i('<') && !i('&') && !D(u)) {
                this.L[i2] = K();
                if (this.L[i2] == '\r' && G() == '\n') {
                    this.L[i2] = K();
                }
                i2++;
                if (i2 == 255) {
                    break;
                }
            }
            this.N.e(this.L, 0, 255);
        }
        if (i2 > 0) {
            this.N.e(this.L, 0, i2);
        }
    }

    private final boolean j(char c2, char c3) throws ParseException, IOException {
        if (this.I >= this.J && a() == -1) {
            return false;
        }
        char c4 = this.H[this.I];
        return c4 == c2 || c4 == c3;
    }

    private void j0() throws ParseException, IOException {
        if (F()) {
            r0();
        }
        while (w()) {
            d0();
        }
        if (n()) {
            S();
            while (w()) {
                d0();
            }
        }
    }

    private final boolean k(char c2, char c3, char c4, char c5) throws ParseException, IOException {
        if (this.I >= this.J && a() == -1) {
            return false;
        }
        char c6 = this.H[this.I];
        return c6 == c2 || c6 == c3 || c6 == c4 || c6 == c5;
    }

    private final void k0() throws ParseException, IOException {
        o0();
    }

    private final boolean l() throws ParseException, IOException {
        return D(f4180e);
    }

    private final char[] l0() throws ParseException, IOException {
        return D(o) ? new char[]{O()} : Y().toCharArray();
    }

    private boolean m() throws ParseException, IOException {
        return z() || C();
    }

    private final void m0() throws ParseException, IOException {
        M(' ', '\t', '\r', '\n');
        while (k(' ', '\t', '\r', '\n')) {
            K();
        }
    }

    private boolean n() throws ParseException, IOException {
        return D(i);
    }

    private final void n0(char[] cArr) throws ParseException, IOException {
        int length = cArr.length;
        if (this.J - this.I < length && b(length) <= 0) {
            this.E = -1;
            throw new ParseException(this, "end of XML file", cArr);
        }
        char[] cArr2 = this.H;
        int i2 = this.J;
        this.E = cArr2[i2 - 1];
        if (i2 - this.I < length) {
            throw new ParseException(this, "end of XML file", cArr);
        }
        for (int i3 = 0; i3 < length; i3++) {
            if (this.H[this.I + i3] != cArr[i3]) {
                throw new ParseException(this, new String(this.H, this.I, length), cArr);
            }
        }
        this.I += length;
    }

    private boolean o() throws ParseException, IOException {
        return D(w);
    }

    private final void o0() throws ParseException, IOException {
        char cK = K();
        while (G() != cK) {
            K();
        }
        N(cK);
    }

    private boolean p() throws ParseException, IOException {
        return D(k);
    }

    private void p0() throws ParseException, IOException {
        m0();
        n0(l);
        a0();
        char cL = L('\'', '\"');
        q0();
        N(cL);
    }

    private boolean q() throws ParseException, IOException {
        return D(p);
    }

    private void q0() throws ParseException, IOException {
        do {
            K();
        } while (E());
    }

    private final boolean r() throws ParseException, IOException {
        return j('\'', '\"');
    }

    private void r0() throws ParseException, IOException {
        n0(j);
        p0();
        if (C()) {
            m0();
        }
        if (p()) {
            String strW = W();
            if (this.D != null && !strW.toLowerCase().equals(this.D)) {
                throw new EncodingMismatchException(this.x, strW, this.D);
            }
        }
        while (true) {
            char[] cArr = h;
            if (D(cArr)) {
                n0(cArr);
                return;
            }
            K();
        }
    }

    private static boolean s(char c2) {
        if (c2 == 183 || c2 == 903 || c2 == 1600 || c2 == 3654 || c2 == 3782 || c2 == 12293 || c2 == 720 || c2 == 721 || c2 == 12445 || c2 == 12446) {
            return true;
        }
        switch (c2) {
            case 12337:
            case 12338:
            case 12339:
            case 12340:
            case 12341:
                return true;
            default:
                switch (c2) {
                    case 12540:
                    case 12541:
                    case 12542:
                        return true;
                    default:
                        return false;
                }
        }
    }

    private boolean t() throws ParseException, IOException {
        return D(r) || D(s);
    }

    private static final boolean u(char c2, char[] cArr) {
        for (char c3 : cArr) {
            if (c2 == c3) {
                return true;
            }
        }
        return false;
    }

    private static boolean v(char c2) {
        return "abcdefghijklmnopqrstuvwxyz".indexOf(Character.toLowerCase(c2)) != -1;
    }

    private boolean w() throws ParseException, IOException {
        return l() || A() || C();
    }

    private boolean x() throws ParseException, IOException {
        char cG = G();
        return cG < 128 ? f4179d[cG] : y(cG);
    }

    private static boolean y(char c2) {
        return Character.isDigit(c2) || v(c2) || u(c2, f4178c) || s(c2);
    }

    private boolean z() throws ParseException, IOException {
        return i('%');
    }

    final String c() {
        return "";
    }

    int d() {
        return this.E;
    }

    public int e() {
        return this.M;
    }

    k f() {
        return this.C;
    }

    public String g() {
        return this.x;
    }

    @Override // com.hp.hpl.sparta.l
    public String toString() {
        return this.x;
    }
}
