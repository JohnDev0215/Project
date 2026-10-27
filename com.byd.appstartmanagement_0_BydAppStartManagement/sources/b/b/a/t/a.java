package b.b.a.t;

import b.b.a.r.d;
import b.b.a.r.i.e;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.MalformedJsonException;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.util.Objects;

/* JADX INFO: compiled from: JsonReader.java */
/* JADX INFO: loaded from: classes2.dex */
public class a implements Closeable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final char[] f499b = ")]}'\n".toCharArray();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Reader f500c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f501d = false;
    public final char[] e = new char[1024];
    public int f = 0;
    public int g = 0;
    public int h = 0;
    public int i = 0;
    public int j = 0;
    public long k;
    public int l;
    public String m;
    public int[] n;
    public int o;
    public String[] p;
    public int[] q;

    /* JADX INFO: renamed from: b.b.a.t.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: JsonReader.java */
    public static class C0032a extends d {
        @Override // b.b.a.r.d
        public void a(a aVar) throws IOException {
            if (aVar instanceof e) {
                ((e) aVar).N();
                return;
            }
            int iF = aVar.j;
            if (iF == 0) {
                iF = aVar.f();
            }
            if (iF == 13) {
                aVar.j = 9;
                return;
            }
            if (iF == 12) {
                aVar.j = 8;
                return;
            }
            if (iF == 14) {
                aVar.j = 10;
                return;
            }
            throw new IllegalStateException("Expected a name but was " + aVar.y() + aVar.n());
        }
    }

    static {
        d.f423a = new C0032a();
    }

    public a(Reader reader) {
        int[] iArr = new int[32];
        this.n = iArr;
        this.o = 0;
        this.o = 0 + 1;
        iArr[0] = 6;
        this.p = new String[32];
        this.q = new int[32];
        Objects.requireNonNull(reader, "in == null");
        this.f500c = reader;
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x0095, code lost:
    
        if (m(r14) != false) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0097, code lost:
    
        if (r9 != 2) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0099, code lost:
    
        if (r10 == false) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x009f, code lost:
    
        if (r11 != Long.MIN_VALUE) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00a1, code lost:
    
        if (r13 == false) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00a3, code lost:
    
        if (r13 == false) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00a6, code lost:
    
        r11 = -r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00a7, code lost:
    
        r18.k = r11;
        r18.f += r8;
        r18.j = 15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00b2, code lost:
    
        return 15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00b3, code lost:
    
        if (r9 == 2) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00b6, code lost:
    
        if (r9 == 4) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00b9, code lost:
    
        if (r9 != 7) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x00bc, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x00be, code lost:
    
        r18.l = r8;
        r18.j = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x00c4, code lost:
    
        return 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x00c5, code lost:
    
        return 0;
     */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x00e7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int A() throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 243
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.b.a.t.a.A():int");
    }

    public final void B(int i) {
        int i2 = this.o;
        int[] iArr = this.n;
        if (i2 == iArr.length) {
            int[] iArr2 = new int[i2 * 2];
            int[] iArr3 = new int[i2 * 2];
            String[] strArr = new String[i2 * 2];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            System.arraycopy(this.q, 0, iArr3, 0, this.o);
            System.arraycopy(this.p, 0, strArr, 0, this.o);
            this.n = iArr2;
            this.q = iArr3;
            this.p = strArr;
        }
        int[] iArr4 = this.n;
        int i3 = this.o;
        this.o = i3 + 1;
        iArr4[i3] = i;
    }

    public final char C() throws IOException {
        int i;
        int i2;
        if (this.f == this.g && !i(1)) {
            throw J("Unterminated escape sequence");
        }
        char[] cArr = this.e;
        int i3 = this.f;
        int i4 = i3 + 1;
        this.f = i4;
        char c2 = cArr[i3];
        if (c2 == '\n') {
            this.h++;
            this.i = i4;
        } else if (c2 != '\"' && c2 != '\'' && c2 != '/' && c2 != '\\') {
            if (c2 == 'b') {
                return '\b';
            }
            if (c2 == 'f') {
                return '\f';
            }
            if (c2 == 'n') {
                return '\n';
            }
            if (c2 == 'r') {
                return '\r';
            }
            if (c2 == 't') {
                return '\t';
            }
            if (c2 != 'u') {
                throw J("Invalid escape sequence");
            }
            if (i4 + 4 > this.g && !i(4)) {
                throw J("Unterminated escape sequence");
            }
            char c3 = 0;
            int i5 = this.f;
            int i6 = i5 + 4;
            while (i5 < i6) {
                char c4 = this.e[i5];
                char c5 = (char) (c3 << 4);
                if (c4 < '0' || c4 > '9') {
                    if (c4 >= 'a' && c4 <= 'f') {
                        i = c4 - 'a';
                    } else {
                        if (c4 < 'A' || c4 > 'F') {
                            throw new NumberFormatException("\\u" + new String(this.e, this.f, 4));
                        }
                        i = c4 - 'A';
                    }
                    i2 = i + 10;
                } else {
                    i2 = c4 - '0';
                }
                c3 = (char) (c5 + i2);
                i5++;
            }
            this.f += 4;
            return c3;
        }
        return c2;
    }

    public final void D(boolean z) {
        this.f501d = z;
    }

    public final void E(char c2) throws IOException {
        char[] cArr = this.e;
        do {
            int i = this.f;
            int i2 = this.g;
            while (i < i2) {
                int i3 = i + 1;
                char c3 = cArr[i];
                if (c3 == c2) {
                    this.f = i3;
                    return;
                }
                if (c3 == '\\') {
                    this.f = i3;
                    C();
                    i = this.f;
                    i2 = this.g;
                } else {
                    if (c3 == '\n') {
                        this.h++;
                        this.i = i3;
                    }
                    i = i3;
                }
            }
            this.f = i;
        } while (i(1));
        throw J("Unterminated string");
    }

    public final boolean F(String str) throws IOException {
        while (true) {
            if (this.f + str.length() > this.g && !i(str.length())) {
                return false;
            }
            char[] cArr = this.e;
            int i = this.f;
            if (cArr[i] != '\n') {
                for (int i2 = 0; i2 < str.length(); i2++) {
                    if (this.e[this.f + i2] != str.charAt(i2)) {
                        break;
                    }
                }
                return true;
            }
            this.h++;
            this.i = i + 1;
            this.f++;
        }
    }

    public final void G() throws IOException {
        char c2;
        do {
            if (this.f >= this.g && !i(1)) {
                return;
            }
            char[] cArr = this.e;
            int i = this.f;
            int i2 = i + 1;
            this.f = i2;
            c2 = cArr[i];
            if (c2 == '\n') {
                this.h++;
                this.i = i2;
                return;
            }
        } while (c2 != '\r');
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0048, code lost:
    
        d();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void H() throws java.io.IOException {
        /*
            r4 = this;
        L0:
            r0 = 0
        L1:
            int r1 = r4.f
            int r2 = r1 + r0
            int r3 = r4.g
            if (r2 >= r3) goto L51
            char[] r2 = r4.e
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
            r4.d()
        L4b:
            int r1 = r4.f
            int r1 = r1 + r0
            r4.f = r1
            return
        L51:
            int r1 = r1 + r0
            r4.f = r1
            r0 = 1
            boolean r0 = r4.i(r0)
            if (r0 != 0) goto L0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: b.b.a.t.a.H():void");
    }

    public void I() throws IOException {
        int i = 0;
        do {
            int iF = this.j;
            if (iF == 0) {
                iF = f();
            }
            if (iF == 3) {
                B(1);
            } else if (iF == 1) {
                B(3);
            } else if (iF == 4 || iF == 2) {
                this.o--;
                i--;
                this.j = 0;
            } else {
                if (iF == 14 || iF == 10) {
                    H();
                } else if (iF == 8 || iF == 12) {
                    E('\'');
                } else if (iF == 9 || iF == 13) {
                    E('\"');
                } else if (iF == 16) {
                    this.f += this.l;
                }
                this.j = 0;
            }
            i++;
            this.j = 0;
        } while (i != 0);
        int[] iArr = this.q;
        int i2 = this.o;
        int i3 = i2 - 1;
        iArr[i3] = iArr[i3] + 1;
        this.p[i2 - 1] = "null";
    }

    public final IOException J(String str) throws IOException {
        throw new MalformedJsonException(str + n());
    }

    public void b() throws IOException {
        int iF = this.j;
        if (iF == 0) {
            iF = f();
        }
        if (iF == 3) {
            B(1);
            this.q[this.o - 1] = 0;
            this.j = 0;
        } else {
            throw new IllegalStateException("Expected BEGIN_ARRAY but was " + y() + n());
        }
    }

    public void c() throws IOException {
        int iF = this.j;
        if (iF == 0) {
            iF = f();
        }
        if (iF == 1) {
            B(3);
            this.j = 0;
        } else {
            throw new IllegalStateException("Expected BEGIN_OBJECT but was " + y() + n());
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.j = 0;
        this.n[0] = 8;
        this.o = 1;
        this.f500c.close();
    }

    public final void d() throws IOException {
        if (!this.f501d) {
            throw J("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    public final void e() throws IOException {
        t(true);
        int i = this.f - 1;
        this.f = i;
        char[] cArr = f499b;
        if (i + cArr.length > this.g && !i(cArr.length)) {
            return;
        }
        int i2 = 0;
        while (true) {
            char[] cArr2 = f499b;
            if (i2 >= cArr2.length) {
                this.f += cArr2.length;
                return;
            } else if (this.e[this.f + i2] != cArr2[i2]) {
                return;
            } else {
                i2++;
            }
        }
    }

    public int f() throws IOException {
        int iT;
        int[] iArr = this.n;
        int i = this.o;
        int i2 = iArr[i - 1];
        if (i2 == 1) {
            iArr[i - 1] = 2;
        } else if (i2 == 2) {
            int iT2 = t(true);
            if (iT2 != 44) {
                if (iT2 != 59) {
                    if (iT2 != 93) {
                        throw J("Unterminated array");
                    }
                    this.j = 4;
                    return 4;
                }
                d();
            }
        } else {
            if (i2 == 3 || i2 == 5) {
                iArr[i - 1] = 4;
                if (i2 == 5 && (iT = t(true)) != 44) {
                    if (iT != 59) {
                        if (iT != 125) {
                            throw J("Unterminated object");
                        }
                        this.j = 2;
                        return 2;
                    }
                    d();
                }
                int iT3 = t(true);
                if (iT3 == 34) {
                    this.j = 13;
                    return 13;
                }
                if (iT3 == 39) {
                    d();
                    this.j = 12;
                    return 12;
                }
                if (iT3 == 125) {
                    if (i2 == 5) {
                        throw J("Expected name");
                    }
                    this.j = 2;
                    return 2;
                }
                d();
                this.f--;
                if (!m((char) iT3)) {
                    throw J("Expected name");
                }
                this.j = 14;
                return 14;
            }
            if (i2 == 4) {
                iArr[i - 1] = 5;
                int iT4 = t(true);
                if (iT4 != 58) {
                    if (iT4 != 61) {
                        throw J("Expected ':'");
                    }
                    d();
                    if (this.f < this.g || i(1)) {
                        char[] cArr = this.e;
                        int i3 = this.f;
                        if (cArr[i3] == '>') {
                            this.f = i3 + 1;
                        }
                    }
                }
            } else if (i2 == 6) {
                if (this.f501d) {
                    e();
                }
                this.n[this.o - 1] = 7;
            } else if (i2 == 7) {
                if (t(false) == -1) {
                    this.j = 17;
                    return 17;
                }
                d();
                this.f--;
            } else if (i2 == 8) {
                throw new IllegalStateException("JsonReader is closed");
            }
        }
        int iT5 = t(true);
        if (iT5 == 34) {
            this.j = 9;
            return 9;
        }
        if (iT5 == 39) {
            d();
            this.j = 8;
            return 8;
        }
        if (iT5 != 44 && iT5 != 59) {
            if (iT5 == 91) {
                this.j = 3;
                return 3;
            }
            if (iT5 != 93) {
                if (iT5 == 123) {
                    this.j = 1;
                    return 1;
                }
                this.f--;
                int iZ = z();
                if (iZ != 0) {
                    return iZ;
                }
                int iA = A();
                if (iA != 0) {
                    return iA;
                }
                if (!m(this.e[this.f])) {
                    throw J("Expected value");
                }
                d();
                this.j = 10;
                return 10;
            }
            if (i2 == 1) {
                this.j = 4;
                return 4;
            }
        }
        if (i2 != 1 && i2 != 2) {
            throw J("Unexpected value");
        }
        d();
        this.f--;
        this.j = 7;
        return 7;
    }

    public void g() throws IOException {
        int iF = this.j;
        if (iF == 0) {
            iF = f();
        }
        if (iF != 4) {
            throw new IllegalStateException("Expected END_ARRAY but was " + y() + n());
        }
        int i = this.o - 1;
        this.o = i;
        int[] iArr = this.q;
        int i2 = i - 1;
        iArr[i2] = iArr[i2] + 1;
        this.j = 0;
    }

    public void h() throws IOException {
        int iF = this.j;
        if (iF == 0) {
            iF = f();
        }
        if (iF != 2) {
            throw new IllegalStateException("Expected END_OBJECT but was " + y() + n());
        }
        int i = this.o - 1;
        this.o = i;
        this.p[i] = null;
        int[] iArr = this.q;
        int i2 = i - 1;
        iArr[i2] = iArr[i2] + 1;
        this.j = 0;
    }

    public final boolean i(int i) throws IOException {
        int i2;
        int i3;
        char[] cArr = this.e;
        int i4 = this.i;
        int i5 = this.f;
        this.i = i4 - i5;
        int i6 = this.g;
        if (i6 != i5) {
            int i7 = i6 - i5;
            this.g = i7;
            System.arraycopy(cArr, i5, cArr, 0, i7);
        } else {
            this.g = 0;
        }
        this.f = 0;
        do {
            Reader reader = this.f500c;
            int i8 = this.g;
            int i9 = reader.read(cArr, i8, cArr.length - i8);
            if (i9 == -1) {
                return false;
            }
            i2 = this.g + i9;
            this.g = i2;
            if (this.h == 0 && (i3 = this.i) == 0 && i2 > 0 && cArr[0] == 65279) {
                this.f++;
                this.i = i3 + 1;
                i++;
            }
        } while (i2 < i);
        return true;
    }

    public String j() {
        StringBuilder sb = new StringBuilder();
        sb.append('$');
        int i = this.o;
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = this.n[i2];
            if (i3 == 1 || i3 == 2) {
                sb.append('[');
                sb.append(this.q[i2]);
                sb.append(']');
            } else if (i3 == 3 || i3 == 4 || i3 == 5) {
                sb.append('.');
                String[] strArr = this.p;
                if (strArr[i2] != null) {
                    sb.append(strArr[i2]);
                }
            }
        }
        return sb.toString();
    }

    public boolean k() throws IOException {
        int iF = this.j;
        if (iF == 0) {
            iF = f();
        }
        return (iF == 2 || iF == 4) ? false : true;
    }

    public final boolean l() {
        return this.f501d;
    }

    public final boolean m(char c2) throws IOException {
        if (c2 == '\t' || c2 == '\n' || c2 == '\f' || c2 == '\r' || c2 == ' ') {
            return false;
        }
        if (c2 != '#') {
            if (c2 == ',') {
                return false;
            }
            if (c2 != '/' && c2 != '=') {
                if (c2 == '{' || c2 == '}' || c2 == ':') {
                    return false;
                }
                if (c2 != ';') {
                    switch (c2) {
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
        d();
        return false;
    }

    public final String n() {
        return " at line " + (this.h + 1) + " column " + ((this.f - this.i) + 1) + " path " + j();
    }

    public boolean o() throws IOException {
        int iF = this.j;
        if (iF == 0) {
            iF = f();
        }
        if (iF == 5) {
            this.j = 0;
            int[] iArr = this.q;
            int i = this.o - 1;
            iArr[i] = iArr[i] + 1;
            return true;
        }
        if (iF == 6) {
            this.j = 0;
            int[] iArr2 = this.q;
            int i2 = this.o - 1;
            iArr2[i2] = iArr2[i2] + 1;
            return false;
        }
        throw new IllegalStateException("Expected a boolean but was " + y() + n());
    }

    public double p() throws IOException {
        int iF = this.j;
        if (iF == 0) {
            iF = f();
        }
        if (iF == 15) {
            this.j = 0;
            int[] iArr = this.q;
            int i = this.o - 1;
            iArr[i] = iArr[i] + 1;
            return this.k;
        }
        if (iF == 16) {
            this.m = new String(this.e, this.f, this.l);
            this.f += this.l;
        } else if (iF == 8 || iF == 9) {
            this.m = v(iF == 8 ? '\'' : '\"');
        } else if (iF == 10) {
            this.m = x();
        } else if (iF != 11) {
            throw new IllegalStateException("Expected a double but was " + y() + n());
        }
        this.j = 11;
        double d2 = Double.parseDouble(this.m);
        if (!this.f501d && (Double.isNaN(d2) || Double.isInfinite(d2))) {
            throw new MalformedJsonException("JSON forbids NaN and infinities: " + d2 + n());
        }
        this.m = null;
        this.j = 0;
        int[] iArr2 = this.q;
        int i2 = this.o - 1;
        iArr2[i2] = iArr2[i2] + 1;
        return d2;
    }

    public int q() throws IOException {
        int iF = this.j;
        if (iF == 0) {
            iF = f();
        }
        if (iF == 15) {
            long j = this.k;
            int i = (int) j;
            if (j == i) {
                this.j = 0;
                int[] iArr = this.q;
                int i2 = this.o - 1;
                iArr[i2] = iArr[i2] + 1;
                return i;
            }
            throw new NumberFormatException("Expected an int but was " + this.k + n());
        }
        if (iF == 16) {
            this.m = new String(this.e, this.f, this.l);
            this.f += this.l;
        } else {
            if (iF != 8 && iF != 9 && iF != 10) {
                throw new IllegalStateException("Expected an int but was " + y() + n());
            }
            if (iF == 10) {
                this.m = x();
            } else {
                this.m = v(iF == 8 ? '\'' : '\"');
            }
            try {
                int i3 = Integer.parseInt(this.m);
                this.j = 0;
                int[] iArr2 = this.q;
                int i4 = this.o - 1;
                iArr2[i4] = iArr2[i4] + 1;
                return i3;
            } catch (NumberFormatException unused) {
            }
        }
        this.j = 11;
        double d2 = Double.parseDouble(this.m);
        int i5 = (int) d2;
        if (i5 != d2) {
            throw new NumberFormatException("Expected an int but was " + this.m + n());
        }
        this.m = null;
        this.j = 0;
        int[] iArr3 = this.q;
        int i6 = this.o - 1;
        iArr3[i6] = iArr3[i6] + 1;
        return i5;
    }

    public long r() throws IOException {
        int iF = this.j;
        if (iF == 0) {
            iF = f();
        }
        if (iF == 15) {
            this.j = 0;
            int[] iArr = this.q;
            int i = this.o - 1;
            iArr[i] = iArr[i] + 1;
            return this.k;
        }
        if (iF == 16) {
            this.m = new String(this.e, this.f, this.l);
            this.f += this.l;
        } else {
            if (iF != 8 && iF != 9 && iF != 10) {
                throw new IllegalStateException("Expected a long but was " + y() + n());
            }
            if (iF == 10) {
                this.m = x();
            } else {
                this.m = v(iF == 8 ? '\'' : '\"');
            }
            try {
                long j = Long.parseLong(this.m);
                this.j = 0;
                int[] iArr2 = this.q;
                int i2 = this.o - 1;
                iArr2[i2] = iArr2[i2] + 1;
                return j;
            } catch (NumberFormatException unused) {
            }
        }
        this.j = 11;
        double d2 = Double.parseDouble(this.m);
        long j2 = (long) d2;
        if (j2 != d2) {
            throw new NumberFormatException("Expected a long but was " + this.m + n());
        }
        this.m = null;
        this.j = 0;
        int[] iArr3 = this.q;
        int i3 = this.o - 1;
        iArr3[i3] = iArr3[i3] + 1;
        return j2;
    }

    public String s() throws IOException {
        String strV;
        int iF = this.j;
        if (iF == 0) {
            iF = f();
        }
        if (iF == 14) {
            strV = x();
        } else if (iF == 12) {
            strV = v('\'');
        } else {
            if (iF != 13) {
                throw new IllegalStateException("Expected a name but was " + y() + n());
            }
            strV = v('\"');
        }
        this.j = 0;
        this.p[this.o - 1] = strV;
        return strV;
    }

    public final int t(boolean z) throws IOException {
        char[] cArr = this.e;
        int i = this.f;
        int i2 = this.g;
        while (true) {
            if (i == i2) {
                this.f = i;
                if (!i(1)) {
                    if (!z) {
                        return -1;
                    }
                    throw new EOFException("End of input" + n());
                }
                i = this.f;
                i2 = this.g;
            }
            int i3 = i + 1;
            char c2 = cArr[i];
            if (c2 == '\n') {
                this.h++;
                this.i = i3;
            } else if (c2 != ' ' && c2 != '\r' && c2 != '\t') {
                if (c2 == '/') {
                    this.f = i3;
                    if (i3 == i2) {
                        this.f = i3 - 1;
                        boolean zI = i(2);
                        this.f++;
                        if (!zI) {
                            return c2;
                        }
                    }
                    d();
                    int i4 = this.f;
                    char c3 = cArr[i4];
                    if (c3 == '*') {
                        this.f = i4 + 1;
                        if (!F("*/")) {
                            throw J("Unterminated comment");
                        }
                        i = this.f + 2;
                        i2 = this.g;
                    } else {
                        if (c3 != '/') {
                            return c2;
                        }
                        this.f = i4 + 1;
                        G();
                        i = this.f;
                        i2 = this.g;
                    }
                } else {
                    if (c2 != '#') {
                        this.f = i3;
                        return c2;
                    }
                    this.f = i3;
                    d();
                    G();
                    i = this.f;
                    i2 = this.g;
                }
            }
            i = i3;
        }
    }

    public String toString() {
        return getClass().getSimpleName() + n();
    }

    public void u() throws IOException {
        int iF = this.j;
        if (iF == 0) {
            iF = f();
        }
        if (iF == 7) {
            this.j = 0;
            int[] iArr = this.q;
            int i = this.o - 1;
            iArr[i] = iArr[i] + 1;
            return;
        }
        throw new IllegalStateException("Expected null but was " + y() + n());
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0046, code lost:
    
        r1.append(r0, r3, r2 - r3);
        r8.f = r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String v(char r9) throws java.io.IOException {
        /*
            r8 = this;
            char[] r0 = r8.e
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
        L7:
            int r2 = r8.f
            int r3 = r8.g
        Lb:
            r4 = r3
            r3 = r2
        Ld:
            r5 = 1
            if (r2 >= r4) goto L46
            int r6 = r2 + 1
            char r2 = r0[r2]
            if (r2 != r9) goto L22
            r8.f = r6
            int r6 = r6 - r3
            int r6 = r6 - r5
            r1.append(r0, r3, r6)
            java.lang.String r9 = r1.toString()
            return r9
        L22:
            r7 = 92
            if (r2 != r7) goto L39
            r8.f = r6
            int r6 = r6 - r3
            int r6 = r6 - r5
            r1.append(r0, r3, r6)
            char r2 = r8.C()
            r1.append(r2)
            int r2 = r8.f
            int r3 = r8.g
            goto Lb
        L39:
            r7 = 10
            if (r2 != r7) goto L44
            int r2 = r8.h
            int r2 = r2 + r5
            r8.h = r2
            r8.i = r6
        L44:
            r2 = r6
            goto Ld
        L46:
            int r4 = r2 - r3
            r1.append(r0, r3, r4)
            r8.f = r2
            boolean r2 = r8.i(r5)
            if (r2 == 0) goto L54
            goto L7
        L54:
            java.lang.String r9 = "Unterminated string"
            java.io.IOException r9 = r8.J(r9)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: b.b.a.t.a.v(char):java.lang.String");
    }

    public String w() throws IOException {
        String str;
        int iF = this.j;
        if (iF == 0) {
            iF = f();
        }
        if (iF == 10) {
            str = x();
        } else if (iF == 8) {
            str = v('\'');
        } else if (iF == 9) {
            str = v('\"');
        } else if (iF == 11) {
            str = this.m;
            this.m = null;
        } else if (iF == 15) {
            str = Long.toString(this.k);
        } else {
            if (iF != 16) {
                throw new IllegalStateException("Expected a string but was " + y() + n());
            }
            str = new String(this.e, this.f, this.l);
            this.f += this.l;
        }
        this.j = 0;
        int[] iArr = this.q;
        int i = this.o - 1;
        iArr[i] = iArr[i] + 1;
        return str;
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x004a, code lost:
    
        d();
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:32:0x0044. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:46:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0084  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String x() throws java.io.IOException {
        /*
            r6 = this;
            r0 = 0
            r1 = 0
        L2:
            r2 = r0
        L3:
            int r3 = r6.f
            int r4 = r3 + r2
            int r5 = r6.g
            if (r4 >= r5) goto L4e
            char[] r4 = r6.e
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
            r6.d()
            goto L5c
        L4e:
            char[] r3 = r6.e
            int r3 = r3.length
            if (r2 >= r3) goto L5e
            int r3 = r2 + 1
            boolean r3 = r6.i(r3)
            if (r3 == 0) goto L5c
            goto L3
        L5c:
            r0 = r2
            goto L78
        L5e:
            if (r1 != 0) goto L65
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
        L65:
            char[] r3 = r6.e
            int r4 = r6.f
            r1.append(r3, r4, r2)
            int r3 = r6.f
            int r3 = r3 + r2
            r6.f = r3
            r2 = 1
            boolean r2 = r6.i(r2)
            if (r2 != 0) goto L2
        L78:
            if (r1 != 0) goto L84
            java.lang.String r1 = new java.lang.String
            char[] r2 = r6.e
            int r3 = r6.f
            r1.<init>(r2, r3, r0)
            goto L8f
        L84:
            char[] r2 = r6.e
            int r3 = r6.f
            r1.append(r2, r3, r0)
            java.lang.String r1 = r1.toString()
        L8f:
            int r2 = r6.f
            int r2 = r2 + r0
            r6.f = r2
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: b.b.a.t.a.x():java.lang.String");
    }

    public JsonToken y() throws IOException {
        int iF = this.j;
        if (iF == 0) {
            iF = f();
        }
        switch (iF) {
            case 1:
                return JsonToken.BEGIN_OBJECT;
            case 2:
                return JsonToken.END_OBJECT;
            case 3:
                return JsonToken.BEGIN_ARRAY;
            case 4:
                return JsonToken.END_ARRAY;
            case 5:
            case 6:
                return JsonToken.BOOLEAN;
            case 7:
                return JsonToken.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return JsonToken.STRING;
            case 12:
            case 13:
            case 14:
                return JsonToken.NAME;
            case 15:
            case 16:
                return JsonToken.NUMBER;
            case 17:
                return JsonToken.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    public final int z() throws IOException {
        int i;
        String str;
        String str2;
        char c2 = this.e[this.f];
        if (c2 == 't' || c2 == 'T') {
            i = 5;
            str = "true";
            str2 = "TRUE";
        } else if (c2 == 'f' || c2 == 'F') {
            i = 6;
            str = "false";
            str2 = "FALSE";
        } else {
            if (c2 != 'n' && c2 != 'N') {
                return 0;
            }
            i = 7;
            str = "null";
            str2 = "NULL";
        }
        int length = str.length();
        for (int i2 = 1; i2 < length; i2++) {
            if (this.f + i2 >= this.g && !i(i2 + 1)) {
                return 0;
            }
            char c3 = this.e[this.f + i2];
            if (c3 != str.charAt(i2) && c3 != str2.charAt(i2)) {
                return 0;
            }
        }
        if ((this.f + length < this.g || i(length + 1)) && m(this.e[this.f + length])) {
            return 0;
        }
        this.f += length;
        this.j = i;
        return i;
    }
}
