package c.b.a.v;

import c.b.a.t.d;
import c.b.a.t.i.e;
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

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final char[] f3036c = ")]}'\n".toCharArray();
    public long I3;
    public int J3;
    public String K3;
    public int[] L3;
    public int M3;
    public String[] N3;
    public int[] O3;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Reader f3037d;
    public boolean s = false;
    public final char[] x = new char[1024];
    public int y = 0;
    public int E3 = 0;
    public int F3 = 0;
    public int G3 = 0;
    public int H3 = 0;

    /* JADX INFO: renamed from: c.b.a.v.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: JsonReader.java */
    public static class C0071a extends d {
        @Override // c.b.a.t.d
        public void a(a aVar) throws IOException {
            if (aVar instanceof e) {
                ((e) aVar).q0();
                return;
            }
            int I = aVar.H3;
            if (I == 0) {
                I = aVar.I();
            }
            if (I == 13) {
                aVar.H3 = 9;
                return;
            }
            if (I == 12) {
                aVar.H3 = 8;
                return;
            }
            if (I == 14) {
                aVar.H3 = 10;
                return;
            }
            throw new IllegalStateException("Expected a name but was " + aVar.b0() + aVar.Q());
        }
    }

    static {
        d.f2948a = new C0071a();
    }

    public a(Reader reader) {
        int[] iArr = new int[32];
        this.L3 = iArr;
        this.M3 = 0;
        this.M3 = 0 + 1;
        iArr[0] = 6;
        this.N3 = new String[32];
        this.O3 = new int[32];
        Objects.requireNonNull(reader, "in == null");
        this.f3037d = reader;
    }

    public final void H() throws IOException {
        W(true);
        int i2 = this.y - 1;
        this.y = i2;
        char[] cArr = f3036c;
        if (i2 + cArr.length > this.E3 && !L(cArr.length)) {
            return;
        }
        int i3 = 0;
        while (true) {
            char[] cArr2 = f3036c;
            if (i3 >= cArr2.length) {
                this.y += cArr2.length;
                return;
            } else if (this.x[this.y + i3] != cArr2[i3]) {
                return;
            } else {
                i3++;
            }
        }
    }

    public int I() throws IOException {
        int iW;
        int[] iArr = this.L3;
        int i2 = this.M3;
        int i3 = iArr[i2 - 1];
        if (i3 == 1) {
            iArr[i2 - 1] = 2;
        } else if (i3 == 2) {
            int iW2 = W(true);
            if (iW2 != 44) {
                if (iW2 != 59) {
                    if (iW2 != 93) {
                        throw m0("Unterminated array");
                    }
                    this.H3 = 4;
                    return 4;
                }
                v();
            }
        } else {
            if (i3 == 3 || i3 == 5) {
                iArr[i2 - 1] = 4;
                if (i3 == 5 && (iW = W(true)) != 44) {
                    if (iW != 59) {
                        if (iW != 125) {
                            throw m0("Unterminated object");
                        }
                        this.H3 = 2;
                        return 2;
                    }
                    v();
                }
                int iW3 = W(true);
                if (iW3 == 34) {
                    this.H3 = 13;
                    return 13;
                }
                if (iW3 == 39) {
                    v();
                    this.H3 = 12;
                    return 12;
                }
                if (iW3 == 125) {
                    if (i3 == 5) {
                        throw m0("Expected name");
                    }
                    this.H3 = 2;
                    return 2;
                }
                v();
                this.y--;
                if (!P((char) iW3)) {
                    throw m0("Expected name");
                }
                this.H3 = 14;
                return 14;
            }
            if (i3 == 4) {
                iArr[i2 - 1] = 5;
                int iW4 = W(true);
                if (iW4 != 58) {
                    if (iW4 != 61) {
                        throw m0("Expected ':'");
                    }
                    v();
                    if (this.y < this.E3 || L(1)) {
                        char[] cArr = this.x;
                        int i4 = this.y;
                        if (cArr[i4] == '>') {
                            this.y = i4 + 1;
                        }
                    }
                }
            } else if (i3 == 6) {
                if (this.s) {
                    H();
                }
                this.L3[this.M3 - 1] = 7;
            } else if (i3 == 7) {
                if (W(false) == -1) {
                    this.H3 = 17;
                    return 17;
                }
                v();
                this.y--;
            } else if (i3 == 8) {
                throw new IllegalStateException("JsonReader is closed");
            }
        }
        int iW5 = W(true);
        if (iW5 == 34) {
            this.H3 = 9;
            return 9;
        }
        if (iW5 == 39) {
            v();
            this.H3 = 8;
            return 8;
        }
        if (iW5 != 44 && iW5 != 59) {
            if (iW5 == 91) {
                this.H3 = 3;
                return 3;
            }
            if (iW5 != 93) {
                if (iW5 == 123) {
                    this.H3 = 1;
                    return 1;
                }
                this.y--;
                int iC0 = c0();
                if (iC0 != 0) {
                    return iC0;
                }
                int iD0 = d0();
                if (iD0 != 0) {
                    return iD0;
                }
                if (!P(this.x[this.y])) {
                    throw m0("Expected value");
                }
                v();
                this.H3 = 10;
                return 10;
            }
            if (i3 == 1) {
                this.H3 = 4;
                return 4;
            }
        }
        if (i3 != 1 && i3 != 2) {
            throw m0("Unexpected value");
        }
        v();
        this.y--;
        this.H3 = 7;
        return 7;
    }

    public void J() throws IOException {
        int I = this.H3;
        if (I == 0) {
            I = I();
        }
        if (I != 4) {
            throw new IllegalStateException("Expected END_ARRAY but was " + b0() + Q());
        }
        int i2 = this.M3 - 1;
        this.M3 = i2;
        int[] iArr = this.O3;
        int i3 = i2 - 1;
        iArr[i3] = iArr[i3] + 1;
        this.H3 = 0;
    }

    public void K() throws IOException {
        int I = this.H3;
        if (I == 0) {
            I = I();
        }
        if (I != 2) {
            throw new IllegalStateException("Expected END_OBJECT but was " + b0() + Q());
        }
        int i2 = this.M3 - 1;
        this.M3 = i2;
        this.N3[i2] = null;
        int[] iArr = this.O3;
        int i3 = i2 - 1;
        iArr[i3] = iArr[i3] + 1;
        this.H3 = 0;
    }

    public final boolean L(int i2) throws IOException {
        int i3;
        int i4;
        char[] cArr = this.x;
        int i5 = this.G3;
        int i6 = this.y;
        this.G3 = i5 - i6;
        int i7 = this.E3;
        if (i7 != i6) {
            int i8 = i7 - i6;
            this.E3 = i8;
            System.arraycopy(cArr, i6, cArr, 0, i8);
        } else {
            this.E3 = 0;
        }
        this.y = 0;
        do {
            Reader reader = this.f3037d;
            int i9 = this.E3;
            int i10 = reader.read(cArr, i9, cArr.length - i9);
            if (i10 == -1) {
                return false;
            }
            i3 = this.E3 + i10;
            this.E3 = i3;
            if (this.F3 == 0 && (i4 = this.G3) == 0 && i3 > 0 && cArr[0] == 65279) {
                this.y++;
                this.G3 = i4 + 1;
                i2++;
            }
        } while (i3 < i2);
        return true;
    }

    public String M() {
        StringBuilder sb = new StringBuilder();
        sb.append('$');
        int i2 = this.M3;
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = this.L3[i3];
            if (i4 == 1 || i4 == 2) {
                sb.append('[');
                sb.append(this.O3[i3]);
                sb.append(']');
            } else if (i4 == 3 || i4 == 4 || i4 == 5) {
                sb.append('.');
                String[] strArr = this.N3;
                if (strArr[i3] != null) {
                    sb.append(strArr[i3]);
                }
            }
        }
        return sb.toString();
    }

    public boolean N() throws IOException {
        int I = this.H3;
        if (I == 0) {
            I = I();
        }
        return (I == 2 || I == 4) ? false : true;
    }

    public final boolean O() {
        return this.s;
    }

    public final boolean P(char c2) throws IOException {
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
        v();
        return false;
    }

    public final String Q() {
        return " at line " + (this.F3 + 1) + " column " + ((this.y - this.G3) + 1) + " path " + M();
    }

    public boolean R() throws IOException {
        int I = this.H3;
        if (I == 0) {
            I = I();
        }
        if (I == 5) {
            this.H3 = 0;
            int[] iArr = this.O3;
            int i2 = this.M3 - 1;
            iArr[i2] = iArr[i2] + 1;
            return true;
        }
        if (I == 6) {
            this.H3 = 0;
            int[] iArr2 = this.O3;
            int i3 = this.M3 - 1;
            iArr2[i3] = iArr2[i3] + 1;
            return false;
        }
        throw new IllegalStateException("Expected a boolean but was " + b0() + Q());
    }

    public double S() throws IOException {
        int I = this.H3;
        if (I == 0) {
            I = I();
        }
        if (I == 15) {
            this.H3 = 0;
            int[] iArr = this.O3;
            int i2 = this.M3 - 1;
            iArr[i2] = iArr[i2] + 1;
            return this.I3;
        }
        if (I == 16) {
            this.K3 = new String(this.x, this.y, this.J3);
            this.y += this.J3;
        } else if (I == 8 || I == 9) {
            this.K3 = Y(I == 8 ? '\'' : '\"');
        } else if (I == 10) {
            this.K3 = a0();
        } else if (I != 11) {
            throw new IllegalStateException("Expected a double but was " + b0() + Q());
        }
        this.H3 = 11;
        double d2 = Double.parseDouble(this.K3);
        if (!this.s && (Double.isNaN(d2) || Double.isInfinite(d2))) {
            throw new MalformedJsonException("JSON forbids NaN and infinities: " + d2 + Q());
        }
        this.K3 = null;
        this.H3 = 0;
        int[] iArr2 = this.O3;
        int i3 = this.M3 - 1;
        iArr2[i3] = iArr2[i3] + 1;
        return d2;
    }

    public int T() throws IOException {
        int I = this.H3;
        if (I == 0) {
            I = I();
        }
        if (I == 15) {
            long j = this.I3;
            int i2 = (int) j;
            if (j == i2) {
                this.H3 = 0;
                int[] iArr = this.O3;
                int i3 = this.M3 - 1;
                iArr[i3] = iArr[i3] + 1;
                return i2;
            }
            throw new NumberFormatException("Expected an int but was " + this.I3 + Q());
        }
        if (I == 16) {
            this.K3 = new String(this.x, this.y, this.J3);
            this.y += this.J3;
        } else {
            if (I != 8 && I != 9 && I != 10) {
                throw new IllegalStateException("Expected an int but was " + b0() + Q());
            }
            if (I == 10) {
                this.K3 = a0();
            } else {
                this.K3 = Y(I == 8 ? '\'' : '\"');
            }
            try {
                int i4 = Integer.parseInt(this.K3);
                this.H3 = 0;
                int[] iArr2 = this.O3;
                int i5 = this.M3 - 1;
                iArr2[i5] = iArr2[i5] + 1;
                return i4;
            } catch (NumberFormatException unused) {
            }
        }
        this.H3 = 11;
        double d2 = Double.parseDouble(this.K3);
        int i6 = (int) d2;
        if (i6 != d2) {
            throw new NumberFormatException("Expected an int but was " + this.K3 + Q());
        }
        this.K3 = null;
        this.H3 = 0;
        int[] iArr3 = this.O3;
        int i7 = this.M3 - 1;
        iArr3[i7] = iArr3[i7] + 1;
        return i6;
    }

    public long U() throws IOException {
        int I = this.H3;
        if (I == 0) {
            I = I();
        }
        if (I == 15) {
            this.H3 = 0;
            int[] iArr = this.O3;
            int i2 = this.M3 - 1;
            iArr[i2] = iArr[i2] + 1;
            return this.I3;
        }
        if (I == 16) {
            this.K3 = new String(this.x, this.y, this.J3);
            this.y += this.J3;
        } else {
            if (I != 8 && I != 9 && I != 10) {
                throw new IllegalStateException("Expected a long but was " + b0() + Q());
            }
            if (I == 10) {
                this.K3 = a0();
            } else {
                this.K3 = Y(I == 8 ? '\'' : '\"');
            }
            try {
                long j = Long.parseLong(this.K3);
                this.H3 = 0;
                int[] iArr2 = this.O3;
                int i3 = this.M3 - 1;
                iArr2[i3] = iArr2[i3] + 1;
                return j;
            } catch (NumberFormatException unused) {
            }
        }
        this.H3 = 11;
        double d2 = Double.parseDouble(this.K3);
        long j2 = (long) d2;
        if (j2 != d2) {
            throw new NumberFormatException("Expected a long but was " + this.K3 + Q());
        }
        this.K3 = null;
        this.H3 = 0;
        int[] iArr3 = this.O3;
        int i4 = this.M3 - 1;
        iArr3[i4] = iArr3[i4] + 1;
        return j2;
    }

    public String V() throws IOException {
        String strY;
        int I = this.H3;
        if (I == 0) {
            I = I();
        }
        if (I == 14) {
            strY = a0();
        } else if (I == 12) {
            strY = Y('\'');
        } else {
            if (I != 13) {
                throw new IllegalStateException("Expected a name but was " + b0() + Q());
            }
            strY = Y('\"');
        }
        this.H3 = 0;
        this.N3[this.M3 - 1] = strY;
        return strY;
    }

    public final int W(boolean z) throws IOException {
        char[] cArr = this.x;
        int i2 = this.y;
        int i3 = this.E3;
        while (true) {
            if (i2 == i3) {
                this.y = i2;
                if (!L(1)) {
                    if (!z) {
                        return -1;
                    }
                    throw new EOFException("End of input" + Q());
                }
                i2 = this.y;
                i3 = this.E3;
            }
            int i4 = i2 + 1;
            char c2 = cArr[i2];
            if (c2 == '\n') {
                this.F3++;
                this.G3 = i4;
            } else if (c2 != ' ' && c2 != '\r' && c2 != '\t') {
                if (c2 == '/') {
                    this.y = i4;
                    if (i4 == i3) {
                        this.y = i4 - 1;
                        boolean zL = L(2);
                        this.y++;
                        if (!zL) {
                            return c2;
                        }
                    }
                    v();
                    int i5 = this.y;
                    char c3 = cArr[i5];
                    if (c3 == '*') {
                        this.y = i5 + 1;
                        if (!i0("*/")) {
                            throw m0("Unterminated comment");
                        }
                        i2 = this.y + 2;
                        i3 = this.E3;
                    } else {
                        if (c3 != '/') {
                            return c2;
                        }
                        this.y = i5 + 1;
                        j0();
                        i2 = this.y;
                        i3 = this.E3;
                    }
                } else {
                    if (c2 != '#') {
                        this.y = i4;
                        return c2;
                    }
                    this.y = i4;
                    v();
                    j0();
                    i2 = this.y;
                    i3 = this.E3;
                }
            }
            i2 = i4;
        }
    }

    public void X() throws IOException {
        int I = this.H3;
        if (I == 0) {
            I = I();
        }
        if (I == 7) {
            this.H3 = 0;
            int[] iArr = this.O3;
            int i2 = this.M3 - 1;
            iArr[i2] = iArr[i2] + 1;
            return;
        }
        throw new IllegalStateException("Expected null but was " + b0() + Q());
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0046, code lost:
    
        r1.append(r0, r3, r2 - r3);
        r8.y = r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String Y(char r9) throws java.io.IOException {
        /*
            r8 = this;
            char[] r0 = r8.x
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
        L7:
            int r2 = r8.y
            int r3 = r8.E3
        Lb:
            r4 = r3
            r3 = r2
        Ld:
            r5 = 1
            if (r2 >= r4) goto L46
            int r6 = r2 + 1
            char r2 = r0[r2]
            if (r2 != r9) goto L22
            r8.y = r6
            int r6 = r6 - r3
            int r6 = r6 - r5
            r1.append(r0, r3, r6)
            java.lang.String r9 = r1.toString()
            return r9
        L22:
            r7 = 92
            if (r2 != r7) goto L39
            r8.y = r6
            int r6 = r6 - r3
            int r6 = r6 - r5
            r1.append(r0, r3, r6)
            char r2 = r8.f0()
            r1.append(r2)
            int r2 = r8.y
            int r3 = r8.E3
            goto Lb
        L39:
            r7 = 10
            if (r2 != r7) goto L44
            int r2 = r8.F3
            int r2 = r2 + r5
            r8.F3 = r2
            r8.G3 = r6
        L44:
            r2 = r6
            goto Ld
        L46:
            int r4 = r2 - r3
            r1.append(r0, r3, r4)
            r8.y = r2
            boolean r2 = r8.L(r5)
            if (r2 == 0) goto L54
            goto L7
        L54:
            java.lang.String r9 = "Unterminated string"
            java.io.IOException r9 = r8.m0(r9)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: c.b.a.v.a.Y(char):java.lang.String");
    }

    public String Z() throws IOException {
        String str;
        int I = this.H3;
        if (I == 0) {
            I = I();
        }
        if (I == 10) {
            str = a0();
        } else if (I == 8) {
            str = Y('\'');
        } else if (I == 9) {
            str = Y('\"');
        } else if (I == 11) {
            str = this.K3;
            this.K3 = null;
        } else if (I == 15) {
            str = Long.toString(this.I3);
        } else {
            if (I != 16) {
                throw new IllegalStateException("Expected a string but was " + b0() + Q());
            }
            str = new String(this.x, this.y, this.J3);
            this.y += this.J3;
        }
        this.H3 = 0;
        int[] iArr = this.O3;
        int i2 = this.M3 - 1;
        iArr[i2] = iArr[i2] + 1;
        return str;
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x004a, code lost:
    
        v();
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:32:0x0044. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:46:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0084  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String a0() throws java.io.IOException {
        /*
            r6 = this;
            r0 = 0
            r1 = 0
        L2:
            r2 = r0
        L3:
            int r3 = r6.y
            int r4 = r3 + r2
            int r5 = r6.E3
            if (r4 >= r5) goto L4e
            char[] r4 = r6.x
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
            r6.v()
            goto L5c
        L4e:
            char[] r3 = r6.x
            int r3 = r3.length
            if (r2 >= r3) goto L5e
            int r3 = r2 + 1
            boolean r3 = r6.L(r3)
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
            char[] r3 = r6.x
            int r4 = r6.y
            r1.append(r3, r4, r2)
            int r3 = r6.y
            int r3 = r3 + r2
            r6.y = r3
            r2 = 1
            boolean r2 = r6.L(r2)
            if (r2 != 0) goto L2
        L78:
            if (r1 != 0) goto L84
            java.lang.String r1 = new java.lang.String
            char[] r2 = r6.x
            int r3 = r6.y
            r1.<init>(r2, r3, r0)
            goto L8f
        L84:
            char[] r2 = r6.x
            int r3 = r6.y
            r1.append(r2, r3, r0)
            java.lang.String r1 = r1.toString()
        L8f:
            int r2 = r6.y
            int r2 = r2 + r0
            r6.y = r2
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: c.b.a.v.a.a0():java.lang.String");
    }

    public JsonToken b0() throws IOException {
        int I = this.H3;
        if (I == 0) {
            I = I();
        }
        switch (I) {
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

    public final int c0() throws IOException {
        int i2;
        String str;
        String str2;
        char c2 = this.x[this.y];
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
            if (this.y + i3 >= this.E3 && !L(i3 + 1)) {
                return 0;
            }
            char c3 = this.x[this.y + i3];
            if (c3 != str.charAt(i3) && c3 != str2.charAt(i3)) {
                return 0;
            }
        }
        if ((this.y + length < this.E3 || L(length + 1)) && P(this.x[this.y + length])) {
            return 0;
        }
        this.y += length;
        this.H3 = i2;
        return i2;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.H3 = 0;
        this.L3[0] = 8;
        this.M3 = 1;
        this.f3037d.close();
    }

    public void d() throws IOException {
        int I = this.H3;
        if (I == 0) {
            I = I();
        }
        if (I == 3) {
            e0(1);
            this.O3[this.M3 - 1] = 0;
            this.H3 = 0;
        } else {
            throw new IllegalStateException("Expected BEGIN_ARRAY but was " + b0() + Q());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x0095, code lost:
    
        if (P(r14) != false) goto L75;
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
    
        r18.I3 = r11;
        r18.y += r8;
        r18.H3 = 15;
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
    
        r18.J3 = r8;
        r18.H3 = 16;
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
    public final int d0() throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 243
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.b.a.v.a.d0():int");
    }

    public final void e0(int i2) {
        int i3 = this.M3;
        int[] iArr = this.L3;
        if (i3 == iArr.length) {
            int[] iArr2 = new int[i3 * 2];
            int[] iArr3 = new int[i3 * 2];
            String[] strArr = new String[i3 * 2];
            System.arraycopy(iArr, 0, iArr2, 0, i3);
            System.arraycopy(this.O3, 0, iArr3, 0, this.M3);
            System.arraycopy(this.N3, 0, strArr, 0, this.M3);
            this.L3 = iArr2;
            this.O3 = iArr3;
            this.N3 = strArr;
        }
        int[] iArr4 = this.L3;
        int i4 = this.M3;
        this.M3 = i4 + 1;
        iArr4[i4] = i2;
    }

    public final char f0() throws IOException {
        int i2;
        int i3;
        if (this.y == this.E3 && !L(1)) {
            throw m0("Unterminated escape sequence");
        }
        char[] cArr = this.x;
        int i4 = this.y;
        int i5 = i4 + 1;
        this.y = i5;
        char c2 = cArr[i4];
        if (c2 == '\n') {
            this.F3++;
            this.G3 = i5;
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
                throw m0("Invalid escape sequence");
            }
            if (i5 + 4 > this.E3 && !L(4)) {
                throw m0("Unterminated escape sequence");
            }
            char c3 = 0;
            int i6 = this.y;
            int i7 = i6 + 4;
            while (i6 < i7) {
                char c4 = this.x[i6];
                char c5 = (char) (c3 << 4);
                if (c4 < '0' || c4 > '9') {
                    if (c4 >= 'a' && c4 <= 'f') {
                        i2 = c4 - 'a';
                    } else {
                        if (c4 < 'A' || c4 > 'F') {
                            throw new NumberFormatException("\\u" + new String(this.x, this.y, 4));
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
            this.y += 4;
            return c3;
        }
        return c2;
    }

    public final void g0(boolean z) {
        this.s = z;
    }

    public final void h0(char c2) throws IOException {
        char[] cArr = this.x;
        do {
            int i2 = this.y;
            int i3 = this.E3;
            while (i2 < i3) {
                int i4 = i2 + 1;
                char c3 = cArr[i2];
                if (c3 == c2) {
                    this.y = i4;
                    return;
                }
                if (c3 == '\\') {
                    this.y = i4;
                    f0();
                    i2 = this.y;
                    i3 = this.E3;
                } else {
                    if (c3 == '\n') {
                        this.F3++;
                        this.G3 = i4;
                    }
                    i2 = i4;
                }
            }
            this.y = i2;
        } while (L(1));
        throw m0("Unterminated string");
    }

    public final boolean i0(String str) throws IOException {
        while (true) {
            if (this.y + str.length() > this.E3 && !L(str.length())) {
                return false;
            }
            char[] cArr = this.x;
            int i2 = this.y;
            if (cArr[i2] != '\n') {
                for (int i3 = 0; i3 < str.length(); i3++) {
                    if (this.x[this.y + i3] != str.charAt(i3)) {
                        break;
                    }
                }
                return true;
            }
            this.F3++;
            this.G3 = i2 + 1;
            this.y++;
        }
    }

    public final void j0() throws IOException {
        char c2;
        do {
            if (this.y >= this.E3 && !L(1)) {
                return;
            }
            char[] cArr = this.x;
            int i2 = this.y;
            int i3 = i2 + 1;
            this.y = i3;
            c2 = cArr[i2];
            if (c2 == '\n') {
                this.F3++;
                this.G3 = i3;
                return;
            }
        } while (c2 != '\r');
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0048, code lost:
    
        v();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void k0() throws java.io.IOException {
        /*
            r4 = this;
        L0:
            r0 = 0
        L1:
            int r1 = r4.y
            int r2 = r1 + r0
            int r3 = r4.E3
            if (r2 >= r3) goto L51
            char[] r2 = r4.x
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
            r4.v()
        L4b:
            int r1 = r4.y
            int r1 = r1 + r0
            r4.y = r1
            return
        L51:
            int r1 = r1 + r0
            r4.y = r1
            r0 = 1
            boolean r0 = r4.L(r0)
            if (r0 != 0) goto L0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: c.b.a.v.a.k0():void");
    }

    public void l0() throws IOException {
        int i2 = 0;
        do {
            int I = this.H3;
            if (I == 0) {
                I = I();
            }
            if (I == 3) {
                e0(1);
            } else if (I == 1) {
                e0(3);
            } else if (I == 4 || I == 2) {
                this.M3--;
                i2--;
                this.H3 = 0;
            } else {
                if (I == 14 || I == 10) {
                    k0();
                } else if (I == 8 || I == 12) {
                    h0('\'');
                } else if (I == 9 || I == 13) {
                    h0('\"');
                } else if (I == 16) {
                    this.y += this.J3;
                }
                this.H3 = 0;
            }
            i2++;
            this.H3 = 0;
        } while (i2 != 0);
        int[] iArr = this.O3;
        int i3 = this.M3;
        int i4 = i3 - 1;
        iArr[i4] = iArr[i4] + 1;
        this.N3[i3 - 1] = "null";
    }

    public final IOException m0(String str) throws IOException {
        throw new MalformedJsonException(str + Q());
    }

    public void s() throws IOException {
        int I = this.H3;
        if (I == 0) {
            I = I();
        }
        if (I == 1) {
            e0(3);
            this.H3 = 0;
        } else {
            throw new IllegalStateException("Expected BEGIN_OBJECT but was " + b0() + Q());
        }
    }

    public String toString() {
        return getClass().getSimpleName() + Q();
    }

    public final void v() throws IOException {
        if (!this.s) {
            throw m0("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }
}
