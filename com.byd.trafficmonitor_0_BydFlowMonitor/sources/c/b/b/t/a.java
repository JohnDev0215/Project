package c.b.b.t;

import c.b.b.r.d;
import c.b.b.r.i.e;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.MalformedJsonException;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.util.Objects;

/* JADX INFO: compiled from: JsonReader.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements Closeable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final char[] f4301b = ")]}'\n".toCharArray();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Reader f4302c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f4303d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final char[] f4304e = new char[1024];

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4305f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4306g = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f4307h = 0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f4308i = 0;
    public int j = 0;
    public long k;
    public int l;
    public String m;
    public int[] n;
    public int o;
    public String[] p;
    public int[] q;

    /* JADX INFO: renamed from: c.b.b.t.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: JsonReader.java */
    public static class C0099a extends d {
        @Override // c.b.b.r.d
        public void a(a aVar) throws IOException {
            if (aVar instanceof e) {
                ((e) aVar).z0();
                return;
            }
            int iR = aVar.j;
            if (iR == 0) {
                iR = aVar.R();
            }
            if (iR == 13) {
                aVar.j = 9;
                return;
            }
            if (iR == 12) {
                aVar.j = 8;
                return;
            }
            if (iR == 14) {
                aVar.j = 10;
                return;
            }
            throw new IllegalStateException("Expected a name but was " + aVar.k0() + aVar.Z());
        }
    }

    static {
        d.f4211a = new C0099a();
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
        this.f4302c = reader;
    }

    public void O() throws IOException {
        int iR = this.j;
        if (iR == 0) {
            iR = R();
        }
        if (iR == 1) {
            n0(3);
            this.j = 0;
        } else {
            throw new IllegalStateException("Expected BEGIN_OBJECT but was " + k0() + Z());
        }
    }

    public final void P() throws IOException {
        if (!this.f4303d) {
            throw v0("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    public final void Q() throws IOException {
        f0(true);
        int i2 = this.f4305f - 1;
        this.f4305f = i2;
        char[] cArr = f4301b;
        if (i2 + cArr.length > this.f4306g && !U(cArr.length)) {
            return;
        }
        int i3 = 0;
        while (true) {
            char[] cArr2 = f4301b;
            if (i3 >= cArr2.length) {
                this.f4305f += cArr2.length;
                return;
            } else if (this.f4304e[this.f4305f + i3] != cArr2[i3]) {
                return;
            } else {
                i3++;
            }
        }
    }

    public int R() throws IOException {
        int iF0;
        int[] iArr = this.n;
        int i2 = this.o;
        int i3 = iArr[i2 - 1];
        if (i3 == 1) {
            iArr[i2 - 1] = 2;
        } else if (i3 == 2) {
            int iF02 = f0(true);
            if (iF02 != 44) {
                if (iF02 != 59) {
                    if (iF02 != 93) {
                        throw v0("Unterminated array");
                    }
                    this.j = 4;
                    return 4;
                }
                P();
            }
        } else {
            if (i3 == 3 || i3 == 5) {
                iArr[i2 - 1] = 4;
                if (i3 == 5 && (iF0 = f0(true)) != 44) {
                    if (iF0 != 59) {
                        if (iF0 != 125) {
                            throw v0("Unterminated object");
                        }
                        this.j = 2;
                        return 2;
                    }
                    P();
                }
                int iF03 = f0(true);
                if (iF03 == 34) {
                    this.j = 13;
                    return 13;
                }
                if (iF03 == 39) {
                    P();
                    this.j = 12;
                    return 12;
                }
                if (iF03 == 125) {
                    if (i3 == 5) {
                        throw v0("Expected name");
                    }
                    this.j = 2;
                    return 2;
                }
                P();
                this.f4305f--;
                if (!Y((char) iF03)) {
                    throw v0("Expected name");
                }
                this.j = 14;
                return 14;
            }
            if (i3 == 4) {
                iArr[i2 - 1] = 5;
                int iF04 = f0(true);
                if (iF04 != 58) {
                    if (iF04 != 61) {
                        throw v0("Expected ':'");
                    }
                    P();
                    if (this.f4305f < this.f4306g || U(1)) {
                        char[] cArr = this.f4304e;
                        int i4 = this.f4305f;
                        if (cArr[i4] == '>') {
                            this.f4305f = i4 + 1;
                        }
                    }
                }
            } else if (i3 == 6) {
                if (this.f4303d) {
                    Q();
                }
                this.n[this.o - 1] = 7;
            } else if (i3 == 7) {
                if (f0(false) == -1) {
                    this.j = 17;
                    return 17;
                }
                P();
                this.f4305f--;
            } else if (i3 == 8) {
                throw new IllegalStateException("JsonReader is closed");
            }
        }
        int iF05 = f0(true);
        if (iF05 == 34) {
            this.j = 9;
            return 9;
        }
        if (iF05 == 39) {
            P();
            this.j = 8;
            return 8;
        }
        if (iF05 != 44 && iF05 != 59) {
            if (iF05 == 91) {
                this.j = 3;
                return 3;
            }
            if (iF05 != 93) {
                if (iF05 == 123) {
                    this.j = 1;
                    return 1;
                }
                this.f4305f--;
                int iL0 = l0();
                if (iL0 != 0) {
                    return iL0;
                }
                int iM0 = m0();
                if (iM0 != 0) {
                    return iM0;
                }
                if (!Y(this.f4304e[this.f4305f])) {
                    throw v0("Expected value");
                }
                P();
                this.j = 10;
                return 10;
            }
            if (i3 == 1) {
                this.j = 4;
                return 4;
            }
        }
        if (i3 != 1 && i3 != 2) {
            throw v0("Unexpected value");
        }
        P();
        this.f4305f--;
        this.j = 7;
        return 7;
    }

    public void S() throws IOException {
        int iR = this.j;
        if (iR == 0) {
            iR = R();
        }
        if (iR != 4) {
            throw new IllegalStateException("Expected END_ARRAY but was " + k0() + Z());
        }
        int i2 = this.o - 1;
        this.o = i2;
        int[] iArr = this.q;
        int i3 = i2 - 1;
        iArr[i3] = iArr[i3] + 1;
        this.j = 0;
    }

    public void T() throws IOException {
        int iR = this.j;
        if (iR == 0) {
            iR = R();
        }
        if (iR != 2) {
            throw new IllegalStateException("Expected END_OBJECT but was " + k0() + Z());
        }
        int i2 = this.o - 1;
        this.o = i2;
        this.p[i2] = null;
        int[] iArr = this.q;
        int i3 = i2 - 1;
        iArr[i3] = iArr[i3] + 1;
        this.j = 0;
    }

    public final boolean U(int i2) throws IOException {
        int i3;
        int i4;
        char[] cArr = this.f4304e;
        int i5 = this.f4308i;
        int i6 = this.f4305f;
        this.f4308i = i5 - i6;
        int i7 = this.f4306g;
        if (i7 != i6) {
            int i8 = i7 - i6;
            this.f4306g = i8;
            System.arraycopy(cArr, i6, cArr, 0, i8);
        } else {
            this.f4306g = 0;
        }
        this.f4305f = 0;
        do {
            Reader reader = this.f4302c;
            int i9 = this.f4306g;
            int i10 = reader.read(cArr, i9, cArr.length - i9);
            if (i10 == -1) {
                return false;
            }
            i3 = this.f4306g + i10;
            this.f4306g = i3;
            if (this.f4307h == 0 && (i4 = this.f4308i) == 0 && i3 > 0 && cArr[0] == 65279) {
                this.f4305f++;
                this.f4308i = i4 + 1;
                i2++;
            }
        } while (i3 < i2);
        return true;
    }

    public String V() {
        StringBuilder sb = new StringBuilder();
        sb.append('$');
        int i2 = this.o;
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = this.n[i3];
            if (i4 == 1 || i4 == 2) {
                sb.append('[');
                sb.append(this.q[i3]);
                sb.append(']');
            } else if (i4 == 3 || i4 == 4 || i4 == 5) {
                sb.append('.');
                String[] strArr = this.p;
                if (strArr[i3] != null) {
                    sb.append(strArr[i3]);
                }
            }
        }
        return sb.toString();
    }

    public boolean W() throws IOException {
        int iR = this.j;
        if (iR == 0) {
            iR = R();
        }
        return (iR == 2 || iR == 4) ? false : true;
    }

    public final boolean X() {
        return this.f4303d;
    }

    public final boolean Y(char c2) throws IOException {
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
        P();
        return false;
    }

    public final String Z() {
        return " at line " + (this.f4307h + 1) + " column " + ((this.f4305f - this.f4308i) + 1) + " path " + V();
    }

    public boolean a0() throws IOException {
        int iR = this.j;
        if (iR == 0) {
            iR = R();
        }
        if (iR == 5) {
            this.j = 0;
            int[] iArr = this.q;
            int i2 = this.o - 1;
            iArr[i2] = iArr[i2] + 1;
            return true;
        }
        if (iR == 6) {
            this.j = 0;
            int[] iArr2 = this.q;
            int i3 = this.o - 1;
            iArr2[i3] = iArr2[i3] + 1;
            return false;
        }
        throw new IllegalStateException("Expected a boolean but was " + k0() + Z());
    }

    public double b0() throws IOException {
        int iR = this.j;
        if (iR == 0) {
            iR = R();
        }
        if (iR == 15) {
            this.j = 0;
            int[] iArr = this.q;
            int i2 = this.o - 1;
            iArr[i2] = iArr[i2] + 1;
            return this.k;
        }
        if (iR == 16) {
            this.m = new String(this.f4304e, this.f4305f, this.l);
            this.f4305f += this.l;
        } else if (iR == 8 || iR == 9) {
            this.m = h0(iR == 8 ? '\'' : '\"');
        } else if (iR == 10) {
            this.m = j0();
        } else if (iR != 11) {
            throw new IllegalStateException("Expected a double but was " + k0() + Z());
        }
        this.j = 11;
        double d2 = Double.parseDouble(this.m);
        if (!this.f4303d && (Double.isNaN(d2) || Double.isInfinite(d2))) {
            throw new MalformedJsonException("JSON forbids NaN and infinities: " + d2 + Z());
        }
        this.m = null;
        this.j = 0;
        int[] iArr2 = this.q;
        int i3 = this.o - 1;
        iArr2[i3] = iArr2[i3] + 1;
        return d2;
    }

    public int c0() throws IOException {
        int iR = this.j;
        if (iR == 0) {
            iR = R();
        }
        if (iR == 15) {
            long j = this.k;
            int i2 = (int) j;
            if (j == i2) {
                this.j = 0;
                int[] iArr = this.q;
                int i3 = this.o - 1;
                iArr[i3] = iArr[i3] + 1;
                return i2;
            }
            throw new NumberFormatException("Expected an int but was " + this.k + Z());
        }
        if (iR == 16) {
            this.m = new String(this.f4304e, this.f4305f, this.l);
            this.f4305f += this.l;
        } else {
            if (iR != 8 && iR != 9 && iR != 10) {
                throw new IllegalStateException("Expected an int but was " + k0() + Z());
            }
            if (iR == 10) {
                this.m = j0();
            } else {
                this.m = h0(iR == 8 ? '\'' : '\"');
            }
            try {
                int i4 = Integer.parseInt(this.m);
                this.j = 0;
                int[] iArr2 = this.q;
                int i5 = this.o - 1;
                iArr2[i5] = iArr2[i5] + 1;
                return i4;
            } catch (NumberFormatException unused) {
            }
        }
        this.j = 11;
        double d2 = Double.parseDouble(this.m);
        int i6 = (int) d2;
        if (i6 != d2) {
            throw new NumberFormatException("Expected an int but was " + this.m + Z());
        }
        this.m = null;
        this.j = 0;
        int[] iArr3 = this.q;
        int i7 = this.o - 1;
        iArr3[i7] = iArr3[i7] + 1;
        return i6;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.j = 0;
        this.n[0] = 8;
        this.o = 1;
        this.f4302c.close();
    }

    public long d0() throws IOException {
        int iR = this.j;
        if (iR == 0) {
            iR = R();
        }
        if (iR == 15) {
            this.j = 0;
            int[] iArr = this.q;
            int i2 = this.o - 1;
            iArr[i2] = iArr[i2] + 1;
            return this.k;
        }
        if (iR == 16) {
            this.m = new String(this.f4304e, this.f4305f, this.l);
            this.f4305f += this.l;
        } else {
            if (iR != 8 && iR != 9 && iR != 10) {
                throw new IllegalStateException("Expected a long but was " + k0() + Z());
            }
            if (iR == 10) {
                this.m = j0();
            } else {
                this.m = h0(iR == 8 ? '\'' : '\"');
            }
            try {
                long j = Long.parseLong(this.m);
                this.j = 0;
                int[] iArr2 = this.q;
                int i3 = this.o - 1;
                iArr2[i3] = iArr2[i3] + 1;
                return j;
            } catch (NumberFormatException unused) {
            }
        }
        this.j = 11;
        double d2 = Double.parseDouble(this.m);
        long j2 = (long) d2;
        if (j2 != d2) {
            throw new NumberFormatException("Expected a long but was " + this.m + Z());
        }
        this.m = null;
        this.j = 0;
        int[] iArr3 = this.q;
        int i4 = this.o - 1;
        iArr3[i4] = iArr3[i4] + 1;
        return j2;
    }

    public String e0() throws IOException {
        String strH0;
        int iR = this.j;
        if (iR == 0) {
            iR = R();
        }
        if (iR == 14) {
            strH0 = j0();
        } else if (iR == 12) {
            strH0 = h0('\'');
        } else {
            if (iR != 13) {
                throw new IllegalStateException("Expected a name but was " + k0() + Z());
            }
            strH0 = h0('\"');
        }
        this.j = 0;
        this.p[this.o - 1] = strH0;
        return strH0;
    }

    public final int f0(boolean z) throws IOException {
        char[] cArr = this.f4304e;
        int i2 = this.f4305f;
        int i3 = this.f4306g;
        while (true) {
            if (i2 == i3) {
                this.f4305f = i2;
                if (!U(1)) {
                    if (!z) {
                        return -1;
                    }
                    throw new EOFException("End of input" + Z());
                }
                i2 = this.f4305f;
                i3 = this.f4306g;
            }
            int i4 = i2 + 1;
            char c2 = cArr[i2];
            if (c2 == '\n') {
                this.f4307h++;
                this.f4308i = i4;
            } else if (c2 != ' ' && c2 != '\r' && c2 != '\t') {
                if (c2 == '/') {
                    this.f4305f = i4;
                    if (i4 == i3) {
                        this.f4305f = i4 - 1;
                        boolean zU = U(2);
                        this.f4305f++;
                        if (!zU) {
                            return c2;
                        }
                    }
                    P();
                    int i5 = this.f4305f;
                    char c3 = cArr[i5];
                    if (c3 == '*') {
                        this.f4305f = i5 + 1;
                        if (!r0("*/")) {
                            throw v0("Unterminated comment");
                        }
                        i2 = this.f4305f + 2;
                        i3 = this.f4306g;
                    } else {
                        if (c3 != '/') {
                            return c2;
                        }
                        this.f4305f = i5 + 1;
                        s0();
                        i2 = this.f4305f;
                        i3 = this.f4306g;
                    }
                } else {
                    if (c2 != '#') {
                        this.f4305f = i4;
                        return c2;
                    }
                    this.f4305f = i4;
                    P();
                    s0();
                    i2 = this.f4305f;
                    i3 = this.f4306g;
                }
            }
            i2 = i4;
        }
    }

    public void g0() throws IOException {
        int iR = this.j;
        if (iR == 0) {
            iR = R();
        }
        if (iR == 7) {
            this.j = 0;
            int[] iArr = this.q;
            int i2 = this.o - 1;
            iArr[i2] = iArr[i2] + 1;
            return;
        }
        throw new IllegalStateException("Expected null but was " + k0() + Z());
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0046, code lost:
    
        r1.append(r0, r3, r2 - r3);
        r8.f4305f = r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String h0(char r9) throws java.io.IOException {
        /*
            r8 = this;
            char[] r0 = r8.f4304e
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
        L7:
            int r2 = r8.f4305f
            int r3 = r8.f4306g
        Lb:
            r4 = r3
            r3 = r2
        Ld:
            r5 = 1
            if (r2 >= r4) goto L46
            int r6 = r2 + 1
            char r2 = r0[r2]
            if (r2 != r9) goto L22
            r8.f4305f = r6
            int r6 = r6 - r3
            int r6 = r6 - r5
            r1.append(r0, r3, r6)
            java.lang.String r9 = r1.toString()
            return r9
        L22:
            r7 = 92
            if (r2 != r7) goto L39
            r8.f4305f = r6
            int r6 = r6 - r3
            int r6 = r6 - r5
            r1.append(r0, r3, r6)
            char r2 = r8.o0()
            r1.append(r2)
            int r2 = r8.f4305f
            int r3 = r8.f4306g
            goto Lb
        L39:
            r7 = 10
            if (r2 != r7) goto L44
            int r2 = r8.f4307h
            int r2 = r2 + r5
            r8.f4307h = r2
            r8.f4308i = r6
        L44:
            r2 = r6
            goto Ld
        L46:
            int r4 = r2 - r3
            r1.append(r0, r3, r4)
            r8.f4305f = r2
            boolean r2 = r8.U(r5)
            if (r2 == 0) goto L54
            goto L7
        L54:
            java.lang.String r9 = "Unterminated string"
            java.io.IOException r9 = r8.v0(r9)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: c.b.b.t.a.h0(char):java.lang.String");
    }

    public String i0() throws IOException {
        String str;
        int iR = this.j;
        if (iR == 0) {
            iR = R();
        }
        if (iR == 10) {
            str = j0();
        } else if (iR == 8) {
            str = h0('\'');
        } else if (iR == 9) {
            str = h0('\"');
        } else if (iR == 11) {
            str = this.m;
            this.m = null;
        } else if (iR == 15) {
            str = Long.toString(this.k);
        } else {
            if (iR != 16) {
                throw new IllegalStateException("Expected a string but was " + k0() + Z());
            }
            str = new String(this.f4304e, this.f4305f, this.l);
            this.f4305f += this.l;
        }
        this.j = 0;
        int[] iArr = this.q;
        int i2 = this.o - 1;
        iArr[i2] = iArr[i2] + 1;
        return str;
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x004a, code lost:
    
        P();
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:32:0x0044. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:46:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0084  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String j0() throws java.io.IOException {
        /*
            r6 = this;
            r0 = 0
            r1 = 0
        L2:
            r2 = r0
        L3:
            int r3 = r6.f4305f
            int r4 = r3 + r2
            int r5 = r6.f4306g
            if (r4 >= r5) goto L4e
            char[] r4 = r6.f4304e
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
            r6.P()
            goto L5c
        L4e:
            char[] r3 = r6.f4304e
            int r3 = r3.length
            if (r2 >= r3) goto L5e
            int r3 = r2 + 1
            boolean r3 = r6.U(r3)
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
            char[] r3 = r6.f4304e
            int r4 = r6.f4305f
            r1.append(r3, r4, r2)
            int r3 = r6.f4305f
            int r3 = r3 + r2
            r6.f4305f = r3
            r2 = 1
            boolean r2 = r6.U(r2)
            if (r2 != 0) goto L2
        L78:
            if (r1 != 0) goto L84
            java.lang.String r1 = new java.lang.String
            char[] r2 = r6.f4304e
            int r3 = r6.f4305f
            r1.<init>(r2, r3, r0)
            goto L8f
        L84:
            char[] r2 = r6.f4304e
            int r3 = r6.f4305f
            r1.append(r2, r3, r0)
            java.lang.String r1 = r1.toString()
        L8f:
            int r2 = r6.f4305f
            int r2 = r2 + r0
            r6.f4305f = r2
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: c.b.b.t.a.j0():java.lang.String");
    }

    public JsonToken k0() throws IOException {
        int iR = this.j;
        if (iR == 0) {
            iR = R();
        }
        switch (iR) {
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

    public final int l0() throws IOException {
        int i2;
        String str;
        String str2;
        char c2 = this.f4304e[this.f4305f];
        if (c2 == 't' || c2 == 'T') {
            i2 = 5;
            str = "true";
            str2 = "TRUE";
        } else if (c2 == 'f' || c2 == 'F') {
            i2 = 6;
            str = "false";
            str2 = "FALSE";
        } else {
            if (c2 != 'n' && c2 != 'N') {
                return 0;
            }
            i2 = 7;
            str = "null";
            str2 = "NULL";
        }
        int length = str.length();
        for (int i3 = 1; i3 < length; i3++) {
            if (this.f4305f + i3 >= this.f4306g && !U(i3 + 1)) {
                return 0;
            }
            char c3 = this.f4304e[this.f4305f + i3];
            if (c3 != str.charAt(i3) && c3 != str2.charAt(i3)) {
                return 0;
            }
        }
        if ((this.f4305f + length < this.f4306g || U(length + 1)) && Y(this.f4304e[this.f4305f + length])) {
            return 0;
        }
        this.f4305f += length;
        this.j = i2;
        return i2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x0095, code lost:
    
        if (Y(r14) != false) goto L75;
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
        r18.f4305f += r8;
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
    public final int m0() throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 243
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.b.b.t.a.m0():int");
    }

    public final void n0(int i2) {
        int i3 = this.o;
        int[] iArr = this.n;
        if (i3 == iArr.length) {
            int[] iArr2 = new int[i3 * 2];
            int[] iArr3 = new int[i3 * 2];
            String[] strArr = new String[i3 * 2];
            System.arraycopy(iArr, 0, iArr2, 0, i3);
            System.arraycopy(this.q, 0, iArr3, 0, this.o);
            System.arraycopy(this.p, 0, strArr, 0, this.o);
            this.n = iArr2;
            this.q = iArr3;
            this.p = strArr;
        }
        int[] iArr4 = this.n;
        int i4 = this.o;
        this.o = i4 + 1;
        iArr4[i4] = i2;
    }

    public final char o0() throws IOException {
        int i2;
        int i3;
        if (this.f4305f == this.f4306g && !U(1)) {
            throw v0("Unterminated escape sequence");
        }
        char[] cArr = this.f4304e;
        int i4 = this.f4305f;
        int i5 = i4 + 1;
        this.f4305f = i5;
        char c2 = cArr[i4];
        if (c2 == '\n') {
            this.f4307h++;
            this.f4308i = i5;
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
                throw v0("Invalid escape sequence");
            }
            if (i5 + 4 > this.f4306g && !U(4)) {
                throw v0("Unterminated escape sequence");
            }
            char c3 = 0;
            int i6 = this.f4305f;
            int i7 = i6 + 4;
            while (i6 < i7) {
                char c4 = this.f4304e[i6];
                char c5 = (char) (c3 << 4);
                if (c4 < '0' || c4 > '9') {
                    if (c4 >= 'a' && c4 <= 'f') {
                        i2 = c4 - 'a';
                    } else {
                        if (c4 < 'A' || c4 > 'F') {
                            throw new NumberFormatException("\\u" + new String(this.f4304e, this.f4305f, 4));
                        }
                        i2 = c4 - 'A';
                    }
                    i3 = i2 + 10;
                } else {
                    i3 = c4 - '0';
                }
                c3 = (char) (c5 + i3);
                i6++;
            }
            this.f4305f += 4;
            return c3;
        }
        return c2;
    }

    public final void p0(boolean z) {
        this.f4303d = z;
    }

    public final void q0(char c2) throws IOException {
        char[] cArr = this.f4304e;
        do {
            int i2 = this.f4305f;
            int i3 = this.f4306g;
            while (i2 < i3) {
                int i4 = i2 + 1;
                char c3 = cArr[i2];
                if (c3 == c2) {
                    this.f4305f = i4;
                    return;
                }
                if (c3 == '\\') {
                    this.f4305f = i4;
                    o0();
                    i2 = this.f4305f;
                    i3 = this.f4306g;
                } else {
                    if (c3 == '\n') {
                        this.f4307h++;
                        this.f4308i = i4;
                    }
                    i2 = i4;
                }
            }
            this.f4305f = i2;
        } while (U(1));
        throw v0("Unterminated string");
    }

    public final boolean r0(String str) throws IOException {
        while (true) {
            if (this.f4305f + str.length() > this.f4306g && !U(str.length())) {
                return false;
            }
            char[] cArr = this.f4304e;
            int i2 = this.f4305f;
            if (cArr[i2] != '\n') {
                for (int i3 = 0; i3 < str.length(); i3++) {
                    if (this.f4304e[this.f4305f + i3] != str.charAt(i3)) {
                        break;
                    }
                }
                return true;
            }
            this.f4307h++;
            this.f4308i = i2 + 1;
            this.f4305f++;
        }
    }

    public final void s0() throws IOException {
        char c2;
        do {
            if (this.f4305f >= this.f4306g && !U(1)) {
                return;
            }
            char[] cArr = this.f4304e;
            int i2 = this.f4305f;
            int i3 = i2 + 1;
            this.f4305f = i3;
            c2 = cArr[i2];
            if (c2 == '\n') {
                this.f4307h++;
                this.f4308i = i3;
                return;
            }
        } while (c2 != '\r');
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0048, code lost:
    
        P();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void t0() throws java.io.IOException {
        /*
            r4 = this;
        L0:
            r0 = 0
        L1:
            int r1 = r4.f4305f
            int r2 = r1 + r0
            int r3 = r4.f4306g
            if (r2 >= r3) goto L51
            char[] r2 = r4.f4304e
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
            r4.P()
        L4b:
            int r1 = r4.f4305f
            int r1 = r1 + r0
            r4.f4305f = r1
            return
        L51:
            int r1 = r1 + r0
            r4.f4305f = r1
            r0 = 1
            boolean r0 = r4.U(r0)
            if (r0 != 0) goto L0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: c.b.b.t.a.t0():void");
    }

    public String toString() {
        return getClass().getSimpleName() + Z();
    }

    public void u0() throws IOException {
        int i2 = 0;
        do {
            int iR = this.j;
            if (iR == 0) {
                iR = R();
            }
            if (iR == 3) {
                n0(1);
            } else if (iR == 1) {
                n0(3);
            } else if (iR == 4 || iR == 2) {
                this.o--;
                i2--;
                this.j = 0;
            } else {
                if (iR == 14 || iR == 10) {
                    t0();
                } else if (iR == 8 || iR == 12) {
                    q0('\'');
                } else if (iR == 9 || iR == 13) {
                    q0('\"');
                } else if (iR == 16) {
                    this.f4305f += this.l;
                }
                this.j = 0;
            }
            i2++;
            this.j = 0;
        } while (i2 != 0);
        int[] iArr = this.q;
        int i3 = this.o;
        int i4 = i3 - 1;
        iArr[i4] = iArr[i4] + 1;
        this.p[i3 - 1] = "null";
    }

    public final IOException v0(String str) throws IOException {
        throw new MalformedJsonException(str + Z());
    }

    public void z() throws IOException {
        int iR = this.j;
        if (iR == 0) {
            iR = R();
        }
        if (iR == 3) {
            n0(1);
            this.q[this.o - 1] = 0;
            this.j = 0;
        } else {
            throw new IllegalStateException("Expected BEGIN_ARRAY but was " + k0() + Z());
        }
    }
}
