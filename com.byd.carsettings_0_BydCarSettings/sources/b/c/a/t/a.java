package b.c.a.t;

import b.c.a.r.d;
import b.c.a.r.i.e;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.MalformedJsonException;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.util.Objects;

/* JADX INFO: compiled from: JsonReader.java */
/* JADX INFO: loaded from: classes3.dex */
public class a implements Closeable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final char[] f2322a = ")]}'\n".toCharArray();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Reader f2323b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f2324c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final char[] f2325d = new char[1024];

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2326e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2327f = 0;
    public int g = 0;
    public int h = 0;
    public int i = 0;
    public long j;
    public int k;
    public String l;
    public int[] m;
    public int n;
    public String[] o;
    public int[] p;

    /* JADX INFO: renamed from: b.c.a.t.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: JsonReader.java */
    public static class C0094a extends d {
        @Override // b.c.a.r.d
        public void a(a aVar) throws IOException {
            if (aVar instanceof e) {
                ((e) aVar).W();
                return;
            }
            int iH = aVar.i;
            if (iH == 0) {
                iH = aVar.h();
            }
            if (iH == 13) {
                aVar.i = 9;
                return;
            }
            if (iH == 12) {
                aVar.i = 8;
                return;
            }
            if (iH == 14) {
                aVar.i = 10;
                return;
            }
            throw new IllegalStateException("Expected a name but was " + aVar.E() + aVar.s());
        }
    }

    static {
        d.f2239a = new C0094a();
    }

    public a(Reader reader) {
        int[] iArr = new int[32];
        this.m = iArr;
        this.n = 0;
        this.n = 0 + 1;
        iArr[0] = 6;
        this.o = new String[32];
        this.p = new int[32];
        Objects.requireNonNull(reader, "in == null");
        this.f2323b = reader;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0046, code lost:
    
        r1.append(r0, r3, r2 - r3);
        r8.f2326e = r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String A(char r9) throws java.io.IOException {
        /*
            r8 = this;
            char[] r0 = r8.f2325d
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
        L7:
            int r2 = r8.f2326e
            int r3 = r8.f2327f
        Lb:
            r4 = r3
            r3 = r2
        Ld:
            r5 = 1
            if (r2 >= r4) goto L46
            int r6 = r2 + 1
            char r2 = r0[r2]
            if (r2 != r9) goto L22
            r8.f2326e = r6
            int r6 = r6 - r3
            int r6 = r6 - r5
            r1.append(r0, r3, r6)
            java.lang.String r9 = r1.toString()
            return r9
        L22:
            r7 = 92
            if (r2 != r7) goto L39
            r8.f2326e = r6
            int r6 = r6 - r3
            int r6 = r6 - r5
            r1.append(r0, r3, r6)
            char r2 = r8.J()
            r1.append(r2)
            int r2 = r8.f2326e
            int r3 = r8.f2327f
            goto Lb
        L39:
            r7 = 10
            if (r2 != r7) goto L44
            int r2 = r8.g
            int r2 = r2 + r5
            r8.g = r2
            r8.h = r6
        L44:
            r2 = r6
            goto Ld
        L46:
            int r4 = r2 - r3
            r1.append(r0, r3, r4)
            r8.f2326e = r2
            boolean r2 = r8.k(r5)
            if (r2 == 0) goto L54
            goto L7
        L54:
            java.lang.String r9 = "Unterminated string"
            java.io.IOException r9 = r8.S(r9)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: b.c.a.t.a.A(char):java.lang.String");
    }

    public String B() throws IOException {
        String str;
        int iH = this.i;
        if (iH == 0) {
            iH = h();
        }
        if (iH == 10) {
            str = C();
        } else if (iH == 8) {
            str = A('\'');
        } else if (iH == 9) {
            str = A('\"');
        } else if (iH == 11) {
            str = this.l;
            this.l = null;
        } else if (iH == 15) {
            str = Long.toString(this.j);
        } else {
            if (iH != 16) {
                throw new IllegalStateException("Expected a string but was " + E() + s());
            }
            str = new String(this.f2325d, this.f2326e, this.k);
            this.f2326e += this.k;
        }
        this.i = 0;
        int[] iArr = this.p;
        int i = this.n - 1;
        iArr[i] = iArr[i] + 1;
        return str;
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x004a, code lost:
    
        f();
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:32:0x0044. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:46:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0084  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String C() throws java.io.IOException {
        /*
            r6 = this;
            r0 = 0
            r1 = 0
        L2:
            r2 = r0
        L3:
            int r3 = r6.f2326e
            int r4 = r3 + r2
            int r5 = r6.f2327f
            if (r4 >= r5) goto L4e
            char[] r4 = r6.f2325d
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
            r6.f()
            goto L5c
        L4e:
            char[] r3 = r6.f2325d
            int r3 = r3.length
            if (r2 >= r3) goto L5e
            int r3 = r2 + 1
            boolean r3 = r6.k(r3)
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
            char[] r3 = r6.f2325d
            int r4 = r6.f2326e
            r1.append(r3, r4, r2)
            int r3 = r6.f2326e
            int r3 = r3 + r2
            r6.f2326e = r3
            r2 = 1
            boolean r2 = r6.k(r2)
            if (r2 != 0) goto L2
        L78:
            if (r1 != 0) goto L84
            java.lang.String r1 = new java.lang.String
            char[] r2 = r6.f2325d
            int r3 = r6.f2326e
            r1.<init>(r2, r3, r0)
            goto L8f
        L84:
            char[] r2 = r6.f2325d
            int r3 = r6.f2326e
            r1.append(r2, r3, r0)
            java.lang.String r1 = r1.toString()
        L8f:
            int r2 = r6.f2326e
            int r2 = r2 + r0
            r6.f2326e = r2
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: b.c.a.t.a.C():java.lang.String");
    }

    public JsonToken E() throws IOException {
        int iH = this.i;
        if (iH == 0) {
            iH = h();
        }
        switch (iH) {
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

    public final int F() throws IOException {
        int i;
        String str;
        String str2;
        char c2 = this.f2325d[this.f2326e];
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
            if (this.f2326e + i2 >= this.f2327f && !k(i2 + 1)) {
                return 0;
            }
            char c3 = this.f2325d[this.f2326e + i2];
            if (c3 != str.charAt(i2) && c3 != str2.charAt(i2)) {
                return 0;
            }
        }
        if ((this.f2326e + length < this.f2327f || k(length + 1)) && q(this.f2325d[this.f2326e + length])) {
            return 0;
        }
        this.f2326e += length;
        this.i = i;
        return i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x0095, code lost:
    
        if (q(r14) != false) goto L75;
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
    
        r18.j = r11;
        r18.f2326e += r8;
        r18.i = 15;
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
    
        r18.k = r8;
        r18.i = 16;
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
    public final int G() throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 243
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.c.a.t.a.G():int");
    }

    public final void H(int i) {
        int i2 = this.n;
        int[] iArr = this.m;
        if (i2 == iArr.length) {
            int[] iArr2 = new int[i2 * 2];
            int[] iArr3 = new int[i2 * 2];
            String[] strArr = new String[i2 * 2];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            System.arraycopy(this.p, 0, iArr3, 0, this.n);
            System.arraycopy(this.o, 0, strArr, 0, this.n);
            this.m = iArr2;
            this.p = iArr3;
            this.o = strArr;
        }
        int[] iArr4 = this.m;
        int i3 = this.n;
        this.n = i3 + 1;
        iArr4[i3] = i;
    }

    public final char J() throws IOException {
        int i;
        int i2;
        if (this.f2326e == this.f2327f && !k(1)) {
            throw S("Unterminated escape sequence");
        }
        char[] cArr = this.f2325d;
        int i3 = this.f2326e;
        int i4 = i3 + 1;
        this.f2326e = i4;
        char c2 = cArr[i3];
        if (c2 == '\n') {
            this.g++;
            this.h = i4;
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
                throw S("Invalid escape sequence");
            }
            if (i4 + 4 > this.f2327f && !k(4)) {
                throw S("Unterminated escape sequence");
            }
            char c3 = 0;
            int i5 = this.f2326e;
            int i6 = i5 + 4;
            while (i5 < i6) {
                char c4 = this.f2325d[i5];
                char c5 = (char) (c3 << 4);
                if (c4 < '0' || c4 > '9') {
                    if (c4 >= 'a' && c4 <= 'f') {
                        i = c4 - 'a';
                    } else {
                        if (c4 < 'A' || c4 > 'F') {
                            throw new NumberFormatException("\\u" + new String(this.f2325d, this.f2326e, 4));
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
            this.f2326e += 4;
            return c3;
        }
        return c2;
    }

    public final void L(boolean z) {
        this.f2324c = z;
    }

    public final void M(char c2) throws IOException {
        char[] cArr = this.f2325d;
        do {
            int i = this.f2326e;
            int i2 = this.f2327f;
            while (i < i2) {
                int i3 = i + 1;
                char c3 = cArr[i];
                if (c3 == c2) {
                    this.f2326e = i3;
                    return;
                }
                if (c3 == '\\') {
                    this.f2326e = i3;
                    J();
                    i = this.f2326e;
                    i2 = this.f2327f;
                } else {
                    if (c3 == '\n') {
                        this.g++;
                        this.h = i3;
                    }
                    i = i3;
                }
            }
            this.f2326e = i;
        } while (k(1));
        throw S("Unterminated string");
    }

    public final boolean N(String str) throws IOException {
        while (true) {
            if (this.f2326e + str.length() > this.f2327f && !k(str.length())) {
                return false;
            }
            char[] cArr = this.f2325d;
            int i = this.f2326e;
            if (cArr[i] != '\n') {
                for (int i2 = 0; i2 < str.length(); i2++) {
                    if (this.f2325d[this.f2326e + i2] != str.charAt(i2)) {
                        break;
                    }
                }
                return true;
            }
            this.g++;
            this.h = i + 1;
            this.f2326e++;
        }
    }

    public final void O() throws IOException {
        char c2;
        do {
            if (this.f2326e >= this.f2327f && !k(1)) {
                return;
            }
            char[] cArr = this.f2325d;
            int i = this.f2326e;
            int i2 = i + 1;
            this.f2326e = i2;
            c2 = cArr[i];
            if (c2 == '\n') {
                this.g++;
                this.h = i2;
                return;
            }
        } while (c2 != '\r');
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0048, code lost:
    
        f();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void Q() throws java.io.IOException {
        /*
            r4 = this;
        L0:
            r0 = 0
        L1:
            int r1 = r4.f2326e
            int r2 = r1 + r0
            int r3 = r4.f2327f
            if (r2 >= r3) goto L51
            char[] r2 = r4.f2325d
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
            r4.f()
        L4b:
            int r1 = r4.f2326e
            int r1 = r1 + r0
            r4.f2326e = r1
            return
        L51:
            int r1 = r1 + r0
            r4.f2326e = r1
            r0 = 1
            boolean r0 = r4.k(r0)
            if (r0 != 0) goto L0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: b.c.a.t.a.Q():void");
    }

    public void R() throws IOException {
        int i = 0;
        do {
            int iH = this.i;
            if (iH == 0) {
                iH = h();
            }
            if (iH == 3) {
                H(1);
            } else if (iH == 1) {
                H(3);
            } else if (iH == 4 || iH == 2) {
                this.n--;
                i--;
                this.i = 0;
            } else {
                if (iH == 14 || iH == 10) {
                    Q();
                } else if (iH == 8 || iH == 12) {
                    M('\'');
                } else if (iH == 9 || iH == 13) {
                    M('\"');
                } else if (iH == 16) {
                    this.f2326e += this.k;
                }
                this.i = 0;
            }
            i++;
            this.i = 0;
        } while (i != 0);
        int[] iArr = this.p;
        int i2 = this.n;
        int i3 = i2 - 1;
        iArr[i3] = iArr[i3] + 1;
        this.o[i2 - 1] = "null";
    }

    public final IOException S(String str) throws IOException {
        throw new MalformedJsonException(str + s());
    }

    public void c() throws IOException {
        int iH = this.i;
        if (iH == 0) {
            iH = h();
        }
        if (iH == 3) {
            H(1);
            this.p[this.n - 1] = 0;
            this.i = 0;
        } else {
            throw new IllegalStateException("Expected BEGIN_ARRAY but was " + E() + s());
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.i = 0;
        this.m[0] = 8;
        this.n = 1;
        this.f2323b.close();
    }

    public void e() throws IOException {
        int iH = this.i;
        if (iH == 0) {
            iH = h();
        }
        if (iH == 1) {
            H(3);
            this.i = 0;
        } else {
            throw new IllegalStateException("Expected BEGIN_OBJECT but was " + E() + s());
        }
    }

    public final void f() throws IOException {
        if (!this.f2324c) {
            throw S("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    public final void g() throws IOException {
        y(true);
        int i = this.f2326e - 1;
        this.f2326e = i;
        char[] cArr = f2322a;
        if (i + cArr.length > this.f2327f && !k(cArr.length)) {
            return;
        }
        int i2 = 0;
        while (true) {
            char[] cArr2 = f2322a;
            if (i2 >= cArr2.length) {
                this.f2326e += cArr2.length;
                return;
            } else if (this.f2325d[this.f2326e + i2] != cArr2[i2]) {
                return;
            } else {
                i2++;
            }
        }
    }

    public int h() throws IOException {
        int iY;
        int[] iArr = this.m;
        int i = this.n;
        int i2 = iArr[i - 1];
        if (i2 == 1) {
            iArr[i - 1] = 2;
        } else if (i2 == 2) {
            int iY2 = y(true);
            if (iY2 != 44) {
                if (iY2 != 59) {
                    if (iY2 != 93) {
                        throw S("Unterminated array");
                    }
                    this.i = 4;
                    return 4;
                }
                f();
            }
        } else {
            if (i2 == 3 || i2 == 5) {
                iArr[i - 1] = 4;
                if (i2 == 5 && (iY = y(true)) != 44) {
                    if (iY != 59) {
                        if (iY != 125) {
                            throw S("Unterminated object");
                        }
                        this.i = 2;
                        return 2;
                    }
                    f();
                }
                int iY3 = y(true);
                if (iY3 == 34) {
                    this.i = 13;
                    return 13;
                }
                if (iY3 == 39) {
                    f();
                    this.i = 12;
                    return 12;
                }
                if (iY3 == 125) {
                    if (i2 == 5) {
                        throw S("Expected name");
                    }
                    this.i = 2;
                    return 2;
                }
                f();
                this.f2326e--;
                if (!q((char) iY3)) {
                    throw S("Expected name");
                }
                this.i = 14;
                return 14;
            }
            if (i2 == 4) {
                iArr[i - 1] = 5;
                int iY4 = y(true);
                if (iY4 != 58) {
                    if (iY4 != 61) {
                        throw S("Expected ':'");
                    }
                    f();
                    if (this.f2326e < this.f2327f || k(1)) {
                        char[] cArr = this.f2325d;
                        int i3 = this.f2326e;
                        if (cArr[i3] == '>') {
                            this.f2326e = i3 + 1;
                        }
                    }
                }
            } else if (i2 == 6) {
                if (this.f2324c) {
                    g();
                }
                this.m[this.n - 1] = 7;
            } else if (i2 == 7) {
                if (y(false) == -1) {
                    this.i = 17;
                    return 17;
                }
                f();
                this.f2326e--;
            } else if (i2 == 8) {
                throw new IllegalStateException("JsonReader is closed");
            }
        }
        int iY5 = y(true);
        if (iY5 == 34) {
            this.i = 9;
            return 9;
        }
        if (iY5 == 39) {
            f();
            this.i = 8;
            return 8;
        }
        if (iY5 != 44 && iY5 != 59) {
            if (iY5 == 91) {
                this.i = 3;
                return 3;
            }
            if (iY5 != 93) {
                if (iY5 == 123) {
                    this.i = 1;
                    return 1;
                }
                this.f2326e--;
                int iF = F();
                if (iF != 0) {
                    return iF;
                }
                int iG = G();
                if (iG != 0) {
                    return iG;
                }
                if (!q(this.f2325d[this.f2326e])) {
                    throw S("Expected value");
                }
                f();
                this.i = 10;
                return 10;
            }
            if (i2 == 1) {
                this.i = 4;
                return 4;
            }
        }
        if (i2 != 1 && i2 != 2) {
            throw S("Unexpected value");
        }
        f();
        this.f2326e--;
        this.i = 7;
        return 7;
    }

    public void i() throws IOException {
        int iH = this.i;
        if (iH == 0) {
            iH = h();
        }
        if (iH != 4) {
            throw new IllegalStateException("Expected END_ARRAY but was " + E() + s());
        }
        int i = this.n - 1;
        this.n = i;
        int[] iArr = this.p;
        int i2 = i - 1;
        iArr[i2] = iArr[i2] + 1;
        this.i = 0;
    }

    public void j() throws IOException {
        int iH = this.i;
        if (iH == 0) {
            iH = h();
        }
        if (iH != 2) {
            throw new IllegalStateException("Expected END_OBJECT but was " + E() + s());
        }
        int i = this.n - 1;
        this.n = i;
        this.o[i] = null;
        int[] iArr = this.p;
        int i2 = i - 1;
        iArr[i2] = iArr[i2] + 1;
        this.i = 0;
    }

    public final boolean k(int i) throws IOException {
        int i2;
        int i3;
        char[] cArr = this.f2325d;
        int i4 = this.h;
        int i5 = this.f2326e;
        this.h = i4 - i5;
        int i6 = this.f2327f;
        if (i6 != i5) {
            int i7 = i6 - i5;
            this.f2327f = i7;
            System.arraycopy(cArr, i5, cArr, 0, i7);
        } else {
            this.f2327f = 0;
        }
        this.f2326e = 0;
        do {
            Reader reader = this.f2323b;
            int i8 = this.f2327f;
            int i9 = reader.read(cArr, i8, cArr.length - i8);
            if (i9 == -1) {
                return false;
            }
            i2 = this.f2327f + i9;
            this.f2327f = i2;
            if (this.g == 0 && (i3 = this.h) == 0 && i2 > 0 && cArr[0] == 65279) {
                this.f2326e++;
                this.h = i3 + 1;
                i++;
            }
        } while (i2 < i);
        return true;
    }

    public String l() {
        StringBuilder sb = new StringBuilder();
        sb.append('$');
        int i = this.n;
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = this.m[i2];
            if (i3 == 1 || i3 == 2) {
                sb.append('[');
                sb.append(this.p[i2]);
                sb.append(']');
            } else if (i3 == 3 || i3 == 4 || i3 == 5) {
                sb.append('.');
                String[] strArr = this.o;
                if (strArr[i2] != null) {
                    sb.append(strArr[i2]);
                }
            }
        }
        return sb.toString();
    }

    public boolean n() throws IOException {
        int iH = this.i;
        if (iH == 0) {
            iH = h();
        }
        return (iH == 2 || iH == 4) ? false : true;
    }

    public final boolean o() {
        return this.f2324c;
    }

    public final boolean q(char c2) throws IOException {
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
        f();
        return false;
    }

    public final String s() {
        return " at line " + (this.g + 1) + " column " + ((this.f2326e - this.h) + 1) + " path " + l();
    }

    public boolean t() throws IOException {
        int iH = this.i;
        if (iH == 0) {
            iH = h();
        }
        if (iH == 5) {
            this.i = 0;
            int[] iArr = this.p;
            int i = this.n - 1;
            iArr[i] = iArr[i] + 1;
            return true;
        }
        if (iH == 6) {
            this.i = 0;
            int[] iArr2 = this.p;
            int i2 = this.n - 1;
            iArr2[i2] = iArr2[i2] + 1;
            return false;
        }
        throw new IllegalStateException("Expected a boolean but was " + E() + s());
    }

    public String toString() {
        return getClass().getSimpleName() + s();
    }

    public double u() throws IOException {
        int iH = this.i;
        if (iH == 0) {
            iH = h();
        }
        if (iH == 15) {
            this.i = 0;
            int[] iArr = this.p;
            int i = this.n - 1;
            iArr[i] = iArr[i] + 1;
            return this.j;
        }
        if (iH == 16) {
            this.l = new String(this.f2325d, this.f2326e, this.k);
            this.f2326e += this.k;
        } else if (iH == 8 || iH == 9) {
            this.l = A(iH == 8 ? '\'' : '\"');
        } else if (iH == 10) {
            this.l = C();
        } else if (iH != 11) {
            throw new IllegalStateException("Expected a double but was " + E() + s());
        }
        this.i = 11;
        double d2 = Double.parseDouble(this.l);
        if (!this.f2324c && (Double.isNaN(d2) || Double.isInfinite(d2))) {
            throw new MalformedJsonException("JSON forbids NaN and infinities: " + d2 + s());
        }
        this.l = null;
        this.i = 0;
        int[] iArr2 = this.p;
        int i2 = this.n - 1;
        iArr2[i2] = iArr2[i2] + 1;
        return d2;
    }

    public int v() throws IOException {
        int iH = this.i;
        if (iH == 0) {
            iH = h();
        }
        if (iH == 15) {
            long j = this.j;
            int i = (int) j;
            if (j == i) {
                this.i = 0;
                int[] iArr = this.p;
                int i2 = this.n - 1;
                iArr[i2] = iArr[i2] + 1;
                return i;
            }
            throw new NumberFormatException("Expected an int but was " + this.j + s());
        }
        if (iH == 16) {
            this.l = new String(this.f2325d, this.f2326e, this.k);
            this.f2326e += this.k;
        } else {
            if (iH != 8 && iH != 9 && iH != 10) {
                throw new IllegalStateException("Expected an int but was " + E() + s());
            }
            if (iH == 10) {
                this.l = C();
            } else {
                this.l = A(iH == 8 ? '\'' : '\"');
            }
            try {
                int i3 = Integer.parseInt(this.l);
                this.i = 0;
                int[] iArr2 = this.p;
                int i4 = this.n - 1;
                iArr2[i4] = iArr2[i4] + 1;
                return i3;
            } catch (NumberFormatException unused) {
            }
        }
        this.i = 11;
        double d2 = Double.parseDouble(this.l);
        int i5 = (int) d2;
        if (i5 != d2) {
            throw new NumberFormatException("Expected an int but was " + this.l + s());
        }
        this.l = null;
        this.i = 0;
        int[] iArr3 = this.p;
        int i6 = this.n - 1;
        iArr3[i6] = iArr3[i6] + 1;
        return i5;
    }

    public long w() throws IOException {
        int iH = this.i;
        if (iH == 0) {
            iH = h();
        }
        if (iH == 15) {
            this.i = 0;
            int[] iArr = this.p;
            int i = this.n - 1;
            iArr[i] = iArr[i] + 1;
            return this.j;
        }
        if (iH == 16) {
            this.l = new String(this.f2325d, this.f2326e, this.k);
            this.f2326e += this.k;
        } else {
            if (iH != 8 && iH != 9 && iH != 10) {
                throw new IllegalStateException("Expected a long but was " + E() + s());
            }
            if (iH == 10) {
                this.l = C();
            } else {
                this.l = A(iH == 8 ? '\'' : '\"');
            }
            try {
                long j = Long.parseLong(this.l);
                this.i = 0;
                int[] iArr2 = this.p;
                int i2 = this.n - 1;
                iArr2[i2] = iArr2[i2] + 1;
                return j;
            } catch (NumberFormatException unused) {
            }
        }
        this.i = 11;
        double d2 = Double.parseDouble(this.l);
        long j2 = (long) d2;
        if (j2 != d2) {
            throw new NumberFormatException("Expected a long but was " + this.l + s());
        }
        this.l = null;
        this.i = 0;
        int[] iArr3 = this.p;
        int i3 = this.n - 1;
        iArr3[i3] = iArr3[i3] + 1;
        return j2;
    }

    public String x() throws IOException {
        String strA;
        int iH = this.i;
        if (iH == 0) {
            iH = h();
        }
        if (iH == 14) {
            strA = C();
        } else if (iH == 12) {
            strA = A('\'');
        } else {
            if (iH != 13) {
                throw new IllegalStateException("Expected a name but was " + E() + s());
            }
            strA = A('\"');
        }
        this.i = 0;
        this.o[this.n - 1] = strA;
        return strA;
    }

    public final int y(boolean z) throws IOException {
        char[] cArr = this.f2325d;
        int i = this.f2326e;
        int i2 = this.f2327f;
        while (true) {
            if (i == i2) {
                this.f2326e = i;
                if (!k(1)) {
                    if (!z) {
                        return -1;
                    }
                    throw new EOFException("End of input" + s());
                }
                i = this.f2326e;
                i2 = this.f2327f;
            }
            int i3 = i + 1;
            char c2 = cArr[i];
            if (c2 == '\n') {
                this.g++;
                this.h = i3;
            } else if (c2 != ' ' && c2 != '\r' && c2 != '\t') {
                if (c2 == '/') {
                    this.f2326e = i3;
                    if (i3 == i2) {
                        this.f2326e = i3 - 1;
                        boolean zK = k(2);
                        this.f2326e++;
                        if (!zK) {
                            return c2;
                        }
                    }
                    f();
                    int i4 = this.f2326e;
                    char c3 = cArr[i4];
                    if (c3 == '*') {
                        this.f2326e = i4 + 1;
                        if (!N("*/")) {
                            throw S("Unterminated comment");
                        }
                        i = this.f2326e + 2;
                        i2 = this.f2327f;
                    } else {
                        if (c3 != '/') {
                            return c2;
                        }
                        this.f2326e = i4 + 1;
                        O();
                        i = this.f2326e;
                        i2 = this.f2327f;
                    }
                } else {
                    if (c2 != '#') {
                        this.f2326e = i3;
                        return c2;
                    }
                    this.f2326e = i3;
                    f();
                    O();
                    i = this.f2326e;
                    i2 = this.f2327f;
                }
            }
            i = i3;
        }
    }

    public void z() throws IOException {
        int iH = this.i;
        if (iH == 0) {
            iH = h();
        }
        if (iH == 7) {
            this.i = 0;
            int[] iArr = this.p;
            int i = this.n - 1;
            iArr[i] = iArr[i] + 1;
            return;
        }
        throw new IllegalStateException("Expected null but was " + E() + s());
    }
}
