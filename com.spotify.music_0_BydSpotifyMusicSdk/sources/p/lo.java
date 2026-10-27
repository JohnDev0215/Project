package p;

import com.apollographql.apollo3.api.json.JsonReader$Token;
import com.apollographql.apollo3.exception.JsonDataException;
import com.apollographql.apollo3.exception.JsonEncodingException;
import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import okio.ByteString;

/* JADX INFO: loaded from: classes.dex */
public final class lo implements u71 {
    public static final ByteString u;
    public static final ByteString v;
    public static final ByteString w;
    public final ko c;
    public final fo e;
    public int k;
    public long l;
    public int m;
    public String n;
    public final int[] o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f148p;
    public final String[] q;
    public final int[] r;
    public final int[] s;
    public int t;

    static {
        ByteString byteString = ByteString.k;
        u = qo.p("'\\");
        v = qo.p("\"\\");
        w = qo.p("{}[]:, \n\t\r/\\;#=");
        qo.p("\n\r");
    }

    public lo(ko koVar) {
        v03.j(koVar, "source");
        this.c = koVar;
        this.e = koVar.d();
        int[] iArr = new int[256];
        iArr[0] = 6;
        this.o = iArr;
        this.f148p = 1;
        this.q = new String[256];
        this.r = new int[256];
        int[] iArr2 = new int[256];
        iArr2[0] = 0;
        this.s = iArr2;
        this.t = 1;
    }

    @Override // p.u71
    public final void D() throws EOFException {
        int i = 0;
        do {
            Integer numValueOf = Integer.valueOf(this.k);
            if (numValueOf.intValue() == 0) {
                numValueOf = null;
            }
            int iIntValue = numValueOf != null ? numValueOf.intValue() : c();
            fo foVar = this.e;
            switch (iIntValue) {
                case 1:
                    o0(3);
                    i++;
                    break;
                case 2:
                    this.f148p--;
                    i--;
                    break;
                case 3:
                    o0(1);
                    i++;
                    break;
                case 4:
                    this.f148p--;
                    i--;
                    break;
                case 8:
                case 12:
                    G0(u);
                    break;
                case 9:
                case 13:
                    G0(v);
                    break;
                case 10:
                case 14:
                    long jB0 = this.c.b0(w);
                    if (jB0 == -1) {
                        jB0 = foVar.e;
                    }
                    foVar.y(jB0);
                    break;
                case 16:
                    foVar.y(this.m);
                    break;
            }
            this.k = 0;
        } while (i != 0);
        int i2 = this.f148p;
        int i3 = i2 - 1;
        int[] iArr = this.r;
        iArr[i3] = iArr[i3] + 1;
        this.q[i2 - 1] = "null";
    }

    @Override // p.u71
    public final int D0() throws EOFException {
        Integer numValueOf = Integer.valueOf(this.k);
        if (numValueOf.intValue() == 0) {
            numValueOf = null;
        }
        int iIntValue = numValueOf != null ? numValueOf.intValue() : c();
        int[] iArr = this.r;
        if (iIntValue == 15) {
            long j = this.l;
            int i = (int) j;
            if (j == i) {
                this.k = 0;
                int i2 = this.f148p - 1;
                iArr[i2] = iArr[i2] + 1;
                return i;
            }
            throw new JsonDataException("Expected an int but was " + this.l + " at path " + getPath());
        }
        if (iIntValue == 16) {
            long j2 = this.m;
            fo foVar = this.e;
            foVar.getClass();
            this.n = foVar.P0(j2, cs.a);
        } else if (iIntValue == 9 || iIntValue == 8) {
            String strJ = J(iIntValue == 9 ? v : u);
            this.n = strJ;
            try {
                int i3 = Integer.parseInt(strJ);
                this.k = 0;
                int i4 = this.f148p - 1;
                iArr[i4] = iArr[i4] + 1;
                return i3;
            } catch (NumberFormatException unused) {
            }
        } else if (iIntValue != 11) {
            throw new JsonDataException("Expected an int but was " + peek() + " at path " + i());
        }
        this.k = 11;
        try {
            String str = this.n;
            v03.h(str);
            double d = Double.parseDouble(str);
            int i5 = (int) d;
            if (i5 == d) {
                this.n = null;
                this.k = 0;
                int i6 = this.f148p - 1;
                iArr[i6] = iArr[i6] + 1;
                return i5;
            }
            throw new JsonDataException("Expected an int but was " + this.n + " at path " + i());
        } catch (NumberFormatException unused2) {
            throw new JsonDataException("Expected an int but was " + this.n + " at path " + i());
        }
    }

    public final int E(boolean z) throws EOFException {
        int i = 0;
        while (true) {
            long j = i;
            ko koVar = this.c;
            if (!koVar.a(j + 1)) {
                if (z) {
                    throw new EOFException("End of input");
                }
                return -1;
            }
            i++;
            fo foVar = this.e;
            byte bG0 = foVar.G0(j);
            if (bG0 != 10 && bG0 != 32 && bG0 != 13 && bG0 != 9) {
                foVar.y(((long) i) - 1);
                if (bG0 == 47) {
                    if (!koVar.a(2L)) {
                        return bG0;
                    }
                    b();
                    throw null;
                }
                if (bG0 != 35) {
                    return bG0;
                }
                b();
                throw null;
            }
        }
    }

    public final char E0() throws EOFException {
        int i;
        ko koVar = this.c;
        if (!koVar.a(1L)) {
            throw H0("Unterminated escape sequence");
        }
        fo foVar = this.e;
        char c = (char) foVar.readByte();
        if (c != 'u') {
            if (c == 't') {
                return '\t';
            }
            if (c == 'b') {
                return '\b';
            }
            if (c == 'n') {
                return '\n';
            }
            if (c == 'r') {
                return '\r';
            }
            if (c == 'f') {
                return '\f';
            }
            if (c == '\n' || c == '\'' || c == '\"' || c == '\\' || c == '/') {
                return c;
            }
            throw H0("Invalid escape sequence: \\" + c);
        }
        if (!koVar.a(4L)) {
            throw new EOFException("Unterminated escape sequence at path " + getPath());
        }
        char c2 = 0;
        for (int i2 = 0; i2 < 4; i2++) {
            byte bG0 = foVar.G0(i2);
            char c3 = (char) (c2 << 4);
            byte b = (byte) 48;
            if (bG0 < b || bG0 > ((byte) 57)) {
                byte b2 = (byte) 97;
                if ((bG0 < b2 || bG0 > ((byte) 102)) && (bG0 < (b2 = (byte) 65) || bG0 > ((byte) 70))) {
                    throw H0("\\u".concat(foVar.P0(4L, cs.a)));
                }
                i = (bG0 - b2) + 10;
            } else {
                i = bG0 - b;
            }
            c2 = (char) (c3 + i);
        }
        foVar.y(4L);
        return c2;
    }

    public final void G0(ByteString byteString) throws EOFException {
        while (true) {
            long jB0 = this.c.b0(byteString);
            if (jB0 == -1) {
                throw H0("Unterminated string");
            }
            fo foVar = this.e;
            if (foVar.G0(jB0) != ((byte) 92)) {
                foVar.y(jB0 + 1);
                return;
            } else {
                foVar.y(jB0 + 1);
                E0();
            }
        }
    }

    public final JsonEncodingException H0(String str) {
        StringBuilder sbF = hf2.f(str, " at path ");
        sbF.append(getPath());
        return new JsonEncodingException(sbF.toString());
    }

    public final String J(ByteString byteString) throws EOFException {
        StringBuilder sb = null;
        while (true) {
            long jB0 = this.c.b0(byteString);
            if (jB0 == -1) {
                throw H0("Unterminated string");
            }
            fo foVar = this.e;
            if (foVar.G0(jB0) != ((byte) 92)) {
                if (sb == null) {
                    String strP0 = foVar.P0(jB0, cs.a);
                    foVar.readByte();
                    return strP0;
                }
                sb.append(foVar.P0(jB0, cs.a));
                foVar.readByte();
                String string = sb.toString();
                v03.i(string, "{\n        builder.append…uilder.toString()\n      }");
                return string;
            }
            if (sb == null) {
                sb = new StringBuilder();
            }
            sb.append(foVar.P0(jB0, cs.a));
            foVar.readByte();
            sb.append(E0());
        }
    }

    @Override // p.u71
    public final double T() {
        Integer numValueOf = Integer.valueOf(this.k);
        if (numValueOf.intValue() == 0) {
            numValueOf = null;
        }
        int iIntValue = numValueOf != null ? numValueOf.intValue() : c();
        int[] iArr = this.r;
        if (iIntValue == 15) {
            this.k = 0;
            int i = this.f148p - 1;
            iArr[i] = iArr[i] + 1;
            return this.l;
        }
        if (iIntValue == 16) {
            long j = this.m;
            fo foVar = this.e;
            foVar.getClass();
            this.n = foVar.P0(j, cs.a);
        } else if (iIntValue == 9) {
            this.n = J(v);
        } else if (iIntValue == 8) {
            this.n = J(u);
        } else if (iIntValue == 10) {
            this.n = X();
        } else if (iIntValue != 11) {
            throw new JsonDataException("Expected a double but was " + peek() + " at path " + i());
        }
        this.k = 11;
        try {
            String str = this.n;
            v03.h(str);
            double d = Double.parseDouble(str);
            if (Double.isNaN(d) || Double.isInfinite(d)) {
                throw new JsonEncodingException("JSON forbids NaN and infinities: " + d + " at path " + i());
            }
            this.n = null;
            this.k = 0;
            int i2 = this.f148p - 1;
            iArr[i2] = iArr[i2] + 1;
            return d;
        } catch (NumberFormatException unused) {
            throw new JsonDataException("Expected a double but was " + this.n + " at path " + i());
        }
    }

    @Override // p.u71
    public final o71 U() throws EOFException {
        String strU = u();
        v03.h(strU);
        return new o71(strU);
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x004f, code lost:
    
        D();
     */
    @Override // p.u71
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int W(java.util.List r8) throws java.io.EOFException {
        /*
            r7 = this;
            java.lang.String r0 = "names"
            p.v03.j(r8, r0)
            boolean r0 = r8.isEmpty()
            r1 = -1
            if (r0 == 0) goto Ld
            return r1
        Ld:
            boolean r0 = r7.hasNext()
            if (r0 == 0) goto L76
            java.lang.String r0 = r7.r0()
            int r2 = r7.t
            int r2 = r2 + (-1)
            int[] r3 = r7.s
            r2 = r3[r2]
            java.lang.Object r4 = r8.get(r2)
            boolean r4 = p.v03.b(r4, r0)
            r5 = 0
            if (r4 == 0) goto L43
            int r0 = r7.t
            int r1 = r0 + (-1)
            int r4 = r2 + 1
            r3[r1] = r4
            int r0 = r0 + (-1)
            r0 = r3[r0]
            int r8 = r8.size()
            if (r0 != r8) goto L42
            int r8 = r7.t
            int r8 = r8 + (-1)
            r3[r8] = r5
        L42:
            return r2
        L43:
            r4 = r2
        L44:
            int r4 = r4 + 1
            int r6 = r8.size()
            if (r4 != r6) goto L4d
            r4 = r5
        L4d:
            if (r4 != r2) goto L53
            r7.D()
            goto Ld
        L53:
            java.lang.Object r6 = r8.get(r4)
            boolean r6 = p.v03.b(r6, r0)
            if (r6 == 0) goto L44
            int r0 = r7.t
            int r1 = r0 + (-1)
            int r2 = r4 + 1
            r3[r1] = r2
            int r0 = r0 + (-1)
            r0 = r3[r0]
            int r8 = r8.size()
            if (r0 != r8) goto L75
            int r8 = r7.t
            int r8 = r8 + (-1)
            r3[r8] = r5
        L75:
            return r4
        L76:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p.lo.W(java.util.List):int");
    }

    public final String X() {
        long jB0 = this.c.b0(w);
        fo foVar = this.e;
        if (jB0 == -1) {
            return foVar.Q0();
        }
        foVar.getClass();
        return foVar.P0(jB0, cs.a);
    }

    @Override // p.u71
    public final long Z() throws EOFException {
        Integer numValueOf = Integer.valueOf(this.k);
        if (numValueOf.intValue() == 0) {
            numValueOf = null;
        }
        int iIntValue = numValueOf != null ? numValueOf.intValue() : c();
        int[] iArr = this.r;
        if (iIntValue == 15) {
            this.k = 0;
            int i = this.f148p - 1;
            iArr[i] = iArr[i] + 1;
            return this.l;
        }
        if (iIntValue == 16) {
            long j = this.m;
            fo foVar = this.e;
            foVar.getClass();
            this.n = foVar.P0(j, cs.a);
        } else if (iIntValue == 9 || iIntValue == 8) {
            String strJ = J(iIntValue == 9 ? v : u);
            this.n = strJ;
            try {
                long j2 = Long.parseLong(strJ);
                this.k = 0;
                int i2 = this.f148p - 1;
                iArr[i2] = iArr[i2] + 1;
                return j2;
            } catch (NumberFormatException unused) {
            }
        } else if (iIntValue != 11) {
            throw new JsonDataException("Expected a long but was " + peek() + " at path " + i());
        }
        this.k = 11;
        try {
            String str = this.n;
            v03.h(str);
            double d = Double.parseDouble(str);
            long j3 = (long) d;
            if (j3 == d) {
                this.n = null;
                this.k = 0;
                int i3 = this.f148p - 1;
                iArr[i3] = iArr[i3] + 1;
                return j3;
            }
            throw new JsonDataException("Expected a long but was " + this.n + " at path " + i());
        } catch (NumberFormatException unused2) {
            throw new JsonDataException("Expected a long but was " + this.n + " at path " + i());
        }
    }

    public final void b() {
        throw H0("Use JsonReader.setLenient(true) to accept malformed JSON");
    }

    /* JADX WARN: Code restructure failed: missing block: B:198:0x024f, code lost:
    
        if (q(r14) != false) goto L139;
     */
    /* JADX WARN: Code restructure failed: missing block: B:200:0x0252, code lost:
    
        if (r2 != 2) goto L211;
     */
    /* JADX WARN: Code restructure failed: missing block: B:201:0x0254, code lost:
    
        if (r6 == false) goto L206;
     */
    /* JADX WARN: Code restructure failed: missing block: B:203:0x025a, code lost:
    
        if (r3 != Long.MIN_VALUE) goto L207;
     */
    /* JADX WARN: Code restructure failed: missing block: B:204:0x025c, code lost:
    
        if (r5 == false) goto L206;
     */
    /* JADX WARN: Code restructure failed: missing block: B:206:0x025f, code lost:
    
        r3 = 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:207:0x0261, code lost:
    
        if (r5 == false) goto L209;
     */
    /* JADX WARN: Code restructure failed: missing block: B:209:0x0264, code lost:
    
        r3 = -r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:210:0x0265, code lost:
    
        r20.l = r3;
        r15.y(r9);
        r10 = 15;
        r20.k = 15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:211:0x026f, code lost:
    
        r3 = 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:212:0x0270, code lost:
    
        if (r2 == r3) goto L217;
     */
    /* JADX WARN: Code restructure failed: missing block: B:214:0x0273, code lost:
    
        if (r2 == 4) goto L217;
     */
    /* JADX WARN: Code restructure failed: missing block: B:216:0x0276, code lost:
    
        if (r2 != 7) goto L139;
     */
    /* JADX WARN: Code restructure failed: missing block: B:217:0x0278, code lost:
    
        r20.m = r1;
        r10 = 16;
        r20.k = 16;
     */
    /* JADX WARN: Removed duplicated region for block: B:131:0x01ad A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:132:0x01ae  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int c() throws java.io.EOFException {
        /*
            Method dump skipped, instruction units count: 690
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.lo.c():int");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.k = 0;
        this.o[0] = 8;
        this.f148p = 1;
        this.e.X();
        this.c.close();
    }

    @Override // p.u71
    public final u71 f() {
        Integer numValueOf = Integer.valueOf(this.k);
        if (numValueOf.intValue() == 0) {
            numValueOf = null;
        }
        if ((numValueOf != null ? numValueOf.intValue() : c()) != 1) {
            throw new JsonDataException("Expected BEGIN_OBJECT but was " + peek() + " at path " + i());
        }
        o0(3);
        this.k = 0;
        int i = this.t;
        this.t = i + 1;
        this.s[i] = 0;
        return this;
    }

    @Override // p.u71
    public final void g() {
        throw new IllegalStateException("BufferedSourceJsonReader cannot rewind.".toString());
    }

    @Override // p.u71
    public final ArrayList getPath() {
        String str;
        int i = this.f148p;
        int[] iArr = this.o;
        v03.j(iArr, "stack");
        String[] strArr = this.q;
        v03.j(strArr, "pathNames");
        int[] iArr2 = this.r;
        v03.j(iArr2, "pathIndices");
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = iArr[i2];
            if (i3 == 1 || i3 == 2) {
                arrayList.add(Integer.valueOf(iArr2[i2]));
            } else if ((i3 == 3 || i3 == 4 || i3 == 5) && (str = strArr[i2]) != null) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    @Override // p.u71
    public final u71 h() {
        Integer numValueOf = Integer.valueOf(this.k);
        if (numValueOf.intValue() == 0) {
            numValueOf = null;
        }
        if ((numValueOf != null ? numValueOf.intValue() : c()) != 4) {
            throw new JsonDataException("Expected END_ARRAY but was " + peek() + " at path " + i());
        }
        int i = this.f148p;
        this.f148p = i - 1;
        int i2 = i - 2;
        int[] iArr = this.r;
        iArr[i2] = iArr[i2] + 1;
        this.k = 0;
        return this;
    }

    @Override // p.u71
    public final boolean hasNext() {
        Integer numValueOf = Integer.valueOf(this.k);
        if (numValueOf.intValue() == 0) {
            numValueOf = null;
        }
        int iIntValue = numValueOf != null ? numValueOf.intValue() : c();
        return (iIntValue == 2 || iIntValue == 4) ? false : true;
    }

    public final String i() {
        return ou.h0(getPath(), ".", null, null, null, 62);
    }

    @Override // p.u71
    public final u71 j() {
        Integer numValueOf = Integer.valueOf(this.k);
        if (numValueOf.intValue() == 0) {
            numValueOf = null;
        }
        if ((numValueOf != null ? numValueOf.intValue() : c()) == 3) {
            o0(1);
            this.r[this.f148p - 1] = 0;
            this.k = 0;
            return this;
        }
        throw new JsonDataException("Expected BEGIN_ARRAY but was " + peek() + " at path " + i());
    }

    @Override // p.u71
    public final u71 k() {
        Integer numValueOf = Integer.valueOf(this.k);
        if (numValueOf.intValue() == 0) {
            numValueOf = null;
        }
        if ((numValueOf != null ? numValueOf.intValue() : c()) != 2) {
            throw new JsonDataException("Expected END_OBJECT but was " + peek() + " at path " + i());
        }
        int i = this.f148p;
        int i2 = i - 1;
        this.f148p = i2;
        this.q[i2] = null;
        int i3 = i - 2;
        int[] iArr = this.r;
        iArr[i3] = iArr[i3] + 1;
        this.k = 0;
        this.t--;
        return this;
    }

    public final void o0(int i) {
        int i2 = this.f148p;
        int[] iArr = this.o;
        if (i2 != iArr.length) {
            this.f148p = i2 + 1;
            iArr[i2] = i;
        } else {
            throw new JsonDataException("Nesting too deep at " + getPath());
        }
    }

    @Override // p.u71
    public final JsonReader$Token peek() {
        Integer numValueOf = Integer.valueOf(this.k);
        if (numValueOf.intValue() == 0) {
            numValueOf = null;
        }
        switch (numValueOf != null ? numValueOf.intValue() : c()) {
            case 1:
                return JsonReader$Token.k;
            case 2:
                return JsonReader$Token.l;
            case 3:
                return JsonReader$Token.c;
            case 4:
                return JsonReader$Token.e;
            case 5:
            case 6:
                return JsonReader$Token.q;
            case 7:
                return JsonReader$Token.r;
            case 8:
            case 9:
            case 10:
            case 11:
                return JsonReader$Token.n;
            case 12:
            case 13:
            case 14:
                return JsonReader$Token.m;
            case 15:
                return JsonReader$Token.f26p;
            case 16:
                return JsonReader$Token.o;
            case 17:
                return JsonReader$Token.s;
            default:
                throw new AssertionError();
        }
    }

    public final boolean q(char c) {
        if (c != '/' && c != '\\' && c != ';' && c != '#' && c != '=') {
            return !(c == '{' || c == '}' || c == '[' || c == ']' || c == ':' || c == ',' || c == ' ' || c == '\t' || c == '\r' || c == '\n');
        }
        b();
        throw null;
    }

    @Override // p.u71
    public final String r0() throws EOFException {
        String strJ;
        Integer numValueOf = Integer.valueOf(this.k);
        if (numValueOf.intValue() == 0) {
            numValueOf = null;
        }
        switch (numValueOf != null ? numValueOf.intValue() : c()) {
            case 12:
                strJ = J(u);
                break;
            case 13:
                strJ = J(v);
                break;
            case 14:
                strJ = X();
                break;
            default:
                throw new JsonDataException("Expected a name but was " + peek() + " at path " + i());
        }
        this.k = 0;
        this.q[this.f148p - 1] = strJ;
        return strJ;
    }

    @Override // p.u71
    public final String u() throws EOFException {
        Integer numValueOf = Integer.valueOf(this.k);
        String strValueOf = null;
        if (numValueOf.intValue() == 0) {
            numValueOf = null;
        }
        int iIntValue = numValueOf != null ? numValueOf.intValue() : c();
        if (iIntValue == 15) {
            strValueOf = String.valueOf(this.l);
        } else if (iIntValue != 16) {
            switch (iIntValue) {
                case 8:
                    strValueOf = J(u);
                    break;
                case 9:
                    strValueOf = J(v);
                    break;
                case 10:
                    strValueOf = X();
                    break;
                case 11:
                    String str = this.n;
                    if (str != null) {
                        this.n = null;
                        strValueOf = str;
                    }
                    break;
                default:
                    throw new JsonDataException("Expected a string but was " + peek() + " at path " + i());
            }
        } else {
            long j = this.m;
            fo foVar = this.e;
            foVar.getClass();
            strValueOf = foVar.P0(j, cs.a);
        }
        this.k = 0;
        int i = this.f148p - 1;
        int[] iArr = this.r;
        iArr[i] = iArr[i] + 1;
        return strValueOf;
    }

    @Override // p.u71
    public final boolean w0() {
        Integer numValueOf = Integer.valueOf(this.k);
        if (numValueOf.intValue() == 0) {
            numValueOf = null;
        }
        int iIntValue = numValueOf != null ? numValueOf.intValue() : c();
        int[] iArr = this.r;
        if (iIntValue == 5) {
            this.k = 0;
            int i = this.f148p - 1;
            iArr[i] = iArr[i] + 1;
            return true;
        }
        if (iIntValue == 6) {
            this.k = 0;
            int i2 = this.f148p - 1;
            iArr[i2] = iArr[i2] + 1;
            return false;
        }
        throw new JsonDataException("Expected a boolean but was " + peek() + " at path " + i());
    }

    @Override // p.u71
    public final void z0() {
        Integer numValueOf = Integer.valueOf(this.k);
        if (numValueOf.intValue() == 0) {
            numValueOf = null;
        }
        if ((numValueOf != null ? numValueOf.intValue() : c()) == 7) {
            this.k = 0;
            int i = this.f148p - 1;
            int[] iArr = this.r;
            iArr[i] = iArr[i] + 1;
            return;
        }
        throw new JsonDataException("Expected null but was " + peek() + " at path " + i());
    }
}
