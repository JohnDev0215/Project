package com.google.gson.w;

import com.google.gson.internal.e;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.util.Objects;

/* JADX INFO: compiled from: JsonReader.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements Closeable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final char[] f4605b = ")]}'\n".toCharArray();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Reader f4606c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f4607d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final char[] f4608e = new char[1024];

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f4609f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f4610g = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f4611h = 0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f4612i = 0;
    int j = 0;
    private long k;
    private int l;
    private String m;
    private int[] n;
    private int o;
    private String[] p;
    private int[] q;

    /* JADX INFO: renamed from: com.google.gson.w.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: JsonReader.java */
    static class C0087a extends e {
        C0087a() {
        }

        @Override // com.google.gson.internal.e
        public void a(a aVar) throws IOException {
            if (aVar instanceof com.google.gson.internal.bind.a) {
                ((com.google.gson.internal.bind.a) aVar).y0();
                return;
            }
            int iC = aVar.j;
            if (iC == 0) {
                iC = aVar.C();
            }
            if (iC == 13) {
                aVar.j = 9;
                return;
            }
            if (iC == 12) {
                aVar.j = 8;
                return;
            }
            if (iC == 14) {
                aVar.j = 10;
                return;
            }
            throw new IllegalStateException("Expected a name but was " + aVar.j0() + aVar.Y());
        }
    }

    static {
        e.f4563a = new C0087a();
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
        this.f4606c = reader;
    }

    private boolean T(int i2) throws IOException {
        int i3;
        int i4;
        char[] cArr = this.f4608e;
        int i5 = this.f4612i;
        int i6 = this.f4609f;
        this.f4612i = i5 - i6;
        int i7 = this.f4610g;
        if (i7 != i6) {
            int i8 = i7 - i6;
            this.f4610g = i8;
            System.arraycopy(cArr, i6, cArr, 0, i8);
        } else {
            this.f4610g = 0;
        }
        this.f4609f = 0;
        do {
            Reader reader = this.f4606c;
            int i9 = this.f4610g;
            int i10 = reader.read(cArr, i9, cArr.length - i9);
            if (i10 == -1) {
                return false;
            }
            i3 = this.f4610g + i10;
            this.f4610g = i3;
            if (this.f4611h == 0 && (i4 = this.f4612i) == 0 && i3 > 0 && cArr[0] == 65279) {
                this.f4609f++;
                this.f4612i = i4 + 1;
                i2++;
            }
        } while (i3 < i2);
        return true;
    }

    private boolean X(char c2) throws IOException {
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
        j();
        return false;
    }

    private int e0(boolean z) throws IOException {
        char[] cArr = this.f4608e;
        int i2 = this.f4609f;
        int i3 = this.f4610g;
        while (true) {
            if (i2 == i3) {
                this.f4609f = i2;
                if (!T(1)) {
                    if (!z) {
                        return -1;
                    }
                    throw new EOFException("End of input" + Y());
                }
                i2 = this.f4609f;
                i3 = this.f4610g;
            }
            int i4 = i2 + 1;
            char c2 = cArr[i2];
            if (c2 == '\n') {
                this.f4611h++;
                this.f4612i = i4;
            } else if (c2 != ' ' && c2 != '\r' && c2 != '\t') {
                if (c2 == '/') {
                    this.f4609f = i4;
                    if (i4 == i3) {
                        this.f4609f = i4 - 1;
                        boolean zT = T(2);
                        this.f4609f++;
                        if (!zT) {
                            return c2;
                        }
                    }
                    j();
                    int i5 = this.f4609f;
                    char c3 = cArr[i5];
                    if (c3 == '*') {
                        this.f4609f = i5 + 1;
                        if (!q0("*/")) {
                            throw u0("Unterminated comment");
                        }
                        i2 = this.f4609f + 2;
                        i3 = this.f4610g;
                    } else {
                        if (c3 != '/') {
                            return c2;
                        }
                        this.f4609f = i5 + 1;
                        r0();
                        i2 = this.f4609f;
                        i3 = this.f4610g;
                    }
                } else {
                    if (c2 != '#') {
                        this.f4609f = i4;
                        return c2;
                    }
                    this.f4609f = i4;
                    j();
                    r0();
                    i2 = this.f4609f;
                    i3 = this.f4610g;
                }
            }
            i2 = i4;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x005c, code lost:
    
        if (r1 != null) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x005e, code lost:
    
        r1 = new java.lang.StringBuilder(java.lang.Math.max((r2 - r3) * 2, 16));
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x006c, code lost:
    
        r1.append(r0, r3, r2 - r3);
        r9.f4609f = r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String g0(char r10) throws java.io.IOException {
        /*
            r9 = this;
            char[] r0 = r9.f4608e
            r1 = 0
        L3:
            int r2 = r9.f4609f
            int r3 = r9.f4610g
        L7:
            r4 = r3
            r3 = r2
        L9:
            r5 = 16
            r6 = 1
            if (r2 >= r4) goto L5c
            int r7 = r2 + 1
            char r2 = r0[r2]
            if (r2 != r10) goto L28
            r9.f4609f = r7
            int r7 = r7 - r3
            int r7 = r7 - r6
            if (r1 != 0) goto L20
            java.lang.String r10 = new java.lang.String
            r10.<init>(r0, r3, r7)
            return r10
        L20:
            r1.append(r0, r3, r7)
            java.lang.String r10 = r1.toString()
            return r10
        L28:
            r8 = 92
            if (r2 != r8) goto L4f
            r9.f4609f = r7
            int r7 = r7 - r3
            int r7 = r7 - r6
            if (r1 != 0) goto L40
            int r1 = r7 + 1
            int r1 = r1 * 2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            int r1 = java.lang.Math.max(r1, r5)
            r2.<init>(r1)
            r1 = r2
        L40:
            r1.append(r0, r3, r7)
            char r2 = r9.n0()
            r1.append(r2)
            int r2 = r9.f4609f
            int r3 = r9.f4610g
            goto L7
        L4f:
            r5 = 10
            if (r2 != r5) goto L5a
            int r2 = r9.f4611h
            int r2 = r2 + r6
            r9.f4611h = r2
            r9.f4612i = r7
        L5a:
            r2 = r7
            goto L9
        L5c:
            if (r1 != 0) goto L6c
            int r1 = r2 - r3
            int r1 = r1 * 2
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            int r1 = java.lang.Math.max(r1, r5)
            r4.<init>(r1)
            r1 = r4
        L6c:
            int r4 = r2 - r3
            r1.append(r0, r3, r4)
            r9.f4609f = r2
            boolean r2 = r9.T(r6)
            if (r2 == 0) goto L7a
            goto L3
        L7a:
            java.lang.String r10 = "Unterminated string"
            java.io.IOException r10 = r9.u0(r10)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.w.a.g0(char):java.lang.String");
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x004a, code lost:
    
        j();
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:32:0x0044. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x008a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String i0() throws java.io.IOException {
        /*
            r6 = this;
            r0 = 0
            r1 = 0
        L2:
            r2 = r0
        L3:
            int r3 = r6.f4609f
            int r4 = r3 + r2
            int r5 = r6.f4610g
            if (r4 >= r5) goto L4e
            char[] r4 = r6.f4608e
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
            r6.j()
            goto L5c
        L4e:
            char[] r3 = r6.f4608e
            int r3 = r3.length
            if (r2 >= r3) goto L5e
            int r3 = r2 + 1
            boolean r3 = r6.T(r3)
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
            char[] r3 = r6.f4608e
            int r4 = r6.f4609f
            r1.append(r3, r4, r2)
            int r3 = r6.f4609f
            int r3 = r3 + r2
            r6.f4609f = r3
            r2 = 1
            boolean r2 = r6.T(r2)
            if (r2 != 0) goto L2
        L7e:
            if (r1 != 0) goto L8a
            java.lang.String r1 = new java.lang.String
            char[] r2 = r6.f4608e
            int r3 = r6.f4609f
            r1.<init>(r2, r3, r0)
            goto L95
        L8a:
            char[] r2 = r6.f4608e
            int r3 = r6.f4609f
            r1.append(r2, r3, r0)
            java.lang.String r1 = r1.toString()
        L95:
            int r2 = r6.f4609f
            int r2 = r2 + r0
            r6.f4609f = r2
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.w.a.i0():java.lang.String");
    }

    private void j() throws IOException {
        if (!this.f4607d) {
            throw u0("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    private int k0() {
        int i2;
        String str;
        String str2;
        char c2 = this.f4608e[this.f4609f];
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
            if (this.f4609f + i3 >= this.f4610g && !T(i3 + 1)) {
                return 0;
            }
            char c3 = this.f4608e[this.f4609f + i3];
            if (c3 != str.charAt(i3) && c3 != str2.charAt(i3)) {
                return 0;
            }
        }
        if ((this.f4609f + length < this.f4610g || T(length + 1)) && X(this.f4608e[this.f4609f + length])) {
            return 0;
        }
        this.f4609f += length;
        this.j = i2;
        return i2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x0091, code lost:
    
        if (X(r14) != false) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0093, code lost:
    
        if (r9 != 2) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0095, code lost:
    
        if (r10 == false) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x009b, code lost:
    
        if (r11 != Long.MIN_VALUE) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x009d, code lost:
    
        if (r13 == false) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00a3, code lost:
    
        if (r11 != 0) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00a5, code lost:
    
        if (r13 != false) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00a7, code lost:
    
        if (r13 == false) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00aa, code lost:
    
        r11 = -r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00ab, code lost:
    
        r18.k = r11;
        r18.f4609f += r8;
        r18.j = 15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00b6, code lost:
    
        return 15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00b7, code lost:
    
        if (r9 == 2) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00ba, code lost:
    
        if (r9 == 4) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x00bd, code lost:
    
        if (r9 != 7) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x00c0, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x00c2, code lost:
    
        r18.l = r8;
        r18.j = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x00c8, code lost:
    
        return 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x00c9, code lost:
    
        return 0;
     */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x00eb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int l0() {
        /*
            Method dump skipped, instruction units count: 247
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.w.a.l0():int");
    }

    private void m0(int i2) {
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

    private char n0() throws IOException {
        int i2;
        int i3;
        if (this.f4609f == this.f4610g && !T(1)) {
            throw u0("Unterminated escape sequence");
        }
        char[] cArr = this.f4608e;
        int i4 = this.f4609f;
        int i5 = i4 + 1;
        this.f4609f = i5;
        char c2 = cArr[i4];
        if (c2 == '\n') {
            this.f4611h++;
            this.f4612i = i5;
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
                throw u0("Invalid escape sequence");
            }
            if (i5 + 4 > this.f4610g && !T(4)) {
                throw u0("Unterminated escape sequence");
            }
            char c3 = 0;
            int i6 = this.f4609f;
            int i7 = i6 + 4;
            while (i6 < i7) {
                char c4 = this.f4608e[i6];
                char c5 = (char) (c3 << 4);
                if (c4 < '0' || c4 > '9') {
                    if (c4 >= 'a' && c4 <= 'f') {
                        i2 = c4 - 'a';
                    } else {
                        if (c4 < 'A' || c4 > 'F') {
                            throw new NumberFormatException("\\u" + new String(this.f4608e, this.f4609f, 4));
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
            this.f4609f += 4;
            return c3;
        }
        return c2;
    }

    private void p0(char c2) throws IOException {
        char[] cArr = this.f4608e;
        do {
            int i2 = this.f4609f;
            int i3 = this.f4610g;
            while (i2 < i3) {
                int i4 = i2 + 1;
                char c3 = cArr[i2];
                if (c3 == c2) {
                    this.f4609f = i4;
                    return;
                }
                if (c3 == '\\') {
                    this.f4609f = i4;
                    n0();
                    i2 = this.f4609f;
                    i3 = this.f4610g;
                } else {
                    if (c3 == '\n') {
                        this.f4611h++;
                        this.f4612i = i4;
                    }
                    i2 = i4;
                }
            }
            this.f4609f = i2;
        } while (T(1));
        throw u0("Unterminated string");
    }

    private boolean q0(String str) {
        int length = str.length();
        while (true) {
            if (this.f4609f + length > this.f4610g && !T(length)) {
                return false;
            }
            char[] cArr = this.f4608e;
            int i2 = this.f4609f;
            if (cArr[i2] != '\n') {
                for (int i3 = 0; i3 < length; i3++) {
                    if (this.f4608e[this.f4609f + i3] != str.charAt(i3)) {
                        break;
                    }
                }
                return true;
            }
            this.f4611h++;
            this.f4612i = i2 + 1;
            this.f4609f++;
        }
    }

    private void r0() {
        char c2;
        do {
            if (this.f4609f >= this.f4610g && !T(1)) {
                return;
            }
            char[] cArr = this.f4608e;
            int i2 = this.f4609f;
            int i3 = i2 + 1;
            this.f4609f = i3;
            c2 = cArr[i2];
            if (c2 == '\n') {
                this.f4611h++;
                this.f4612i = i3;
                return;
            }
        } while (c2 != '\r');
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0048, code lost:
    
        j();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void s0() throws java.io.IOException {
        /*
            r4 = this;
        L0:
            r0 = 0
        L1:
            int r1 = r4.f4609f
            int r2 = r1 + r0
            int r3 = r4.f4610g
            if (r2 >= r3) goto L51
            char[] r2 = r4.f4608e
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
            r4.j()
        L4b:
            int r1 = r4.f4609f
            int r1 = r1 + r0
            r4.f4609f = r1
            return
        L51:
            int r1 = r1 + r0
            r4.f4609f = r1
            r0 = 1
            boolean r0 = r4.T(r0)
            if (r0 != 0) goto L0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.w.a.s0():void");
    }

    private IOException u0(String str) throws d {
        throw new d(str + Y());
    }

    private void y() throws IOException {
        e0(true);
        int i2 = this.f4609f - 1;
        this.f4609f = i2;
        char[] cArr = f4605b;
        if (i2 + cArr.length > this.f4610g && !T(cArr.length)) {
            return;
        }
        int i3 = 0;
        while (true) {
            char[] cArr2 = f4605b;
            if (i3 >= cArr2.length) {
                this.f4609f += cArr2.length;
                return;
            } else if (this.f4608e[this.f4609f + i3] != cArr2[i3]) {
                return;
            } else {
                i3++;
            }
        }
    }

    int C() throws IOException {
        int iE0;
        int[] iArr = this.n;
        int i2 = this.o;
        int i3 = iArr[i2 - 1];
        if (i3 == 1) {
            iArr[i2 - 1] = 2;
        } else if (i3 == 2) {
            int iE02 = e0(true);
            if (iE02 != 44) {
                if (iE02 != 59) {
                    if (iE02 != 93) {
                        throw u0("Unterminated array");
                    }
                    this.j = 4;
                    return 4;
                }
                j();
            }
        } else {
            if (i3 == 3 || i3 == 5) {
                iArr[i2 - 1] = 4;
                if (i3 == 5 && (iE0 = e0(true)) != 44) {
                    if (iE0 != 59) {
                        if (iE0 != 125) {
                            throw u0("Unterminated object");
                        }
                        this.j = 2;
                        return 2;
                    }
                    j();
                }
                int iE03 = e0(true);
                if (iE03 == 34) {
                    this.j = 13;
                    return 13;
                }
                if (iE03 == 39) {
                    j();
                    this.j = 12;
                    return 12;
                }
                if (iE03 == 125) {
                    if (i3 == 5) {
                        throw u0("Expected name");
                    }
                    this.j = 2;
                    return 2;
                }
                j();
                this.f4609f--;
                if (!X((char) iE03)) {
                    throw u0("Expected name");
                }
                this.j = 14;
                return 14;
            }
            if (i3 == 4) {
                iArr[i2 - 1] = 5;
                int iE04 = e0(true);
                if (iE04 != 58) {
                    if (iE04 != 61) {
                        throw u0("Expected ':'");
                    }
                    j();
                    if (this.f4609f < this.f4610g || T(1)) {
                        char[] cArr = this.f4608e;
                        int i4 = this.f4609f;
                        if (cArr[i4] == '>') {
                            this.f4609f = i4 + 1;
                        }
                    }
                }
            } else if (i3 == 6) {
                if (this.f4607d) {
                    y();
                }
                this.n[this.o - 1] = 7;
            } else if (i3 == 7) {
                if (e0(false) == -1) {
                    this.j = 17;
                    return 17;
                }
                j();
                this.f4609f--;
            } else if (i3 == 8) {
                throw new IllegalStateException("JsonReader is closed");
            }
        }
        int iE05 = e0(true);
        if (iE05 == 34) {
            this.j = 9;
            return 9;
        }
        if (iE05 == 39) {
            j();
            this.j = 8;
            return 8;
        }
        if (iE05 != 44 && iE05 != 59) {
            if (iE05 == 91) {
                this.j = 3;
                return 3;
            }
            if (iE05 != 93) {
                if (iE05 == 123) {
                    this.j = 1;
                    return 1;
                }
                this.f4609f--;
                int iK0 = k0();
                if (iK0 != 0) {
                    return iK0;
                }
                int iL0 = l0();
                if (iL0 != 0) {
                    return iL0;
                }
                if (!X(this.f4608e[this.f4609f])) {
                    throw u0("Expected value");
                }
                j();
                this.j = 10;
                return 10;
            }
            if (i3 == 1) {
                this.j = 4;
                return 4;
            }
        }
        if (i3 != 1 && i3 != 2) {
            throw u0("Unexpected value");
        }
        j();
        this.f4609f--;
        this.j = 7;
        return 7;
    }

    public void R() throws IOException {
        int iC = this.j;
        if (iC == 0) {
            iC = C();
        }
        if (iC != 4) {
            throw new IllegalStateException("Expected END_ARRAY but was " + j0() + Y());
        }
        int i2 = this.o - 1;
        this.o = i2;
        int[] iArr = this.q;
        int i3 = i2 - 1;
        iArr[i3] = iArr[i3] + 1;
        this.j = 0;
    }

    public void S() throws IOException {
        int iC = this.j;
        if (iC == 0) {
            iC = C();
        }
        if (iC != 2) {
            throw new IllegalStateException("Expected END_OBJECT but was " + j0() + Y());
        }
        int i2 = this.o - 1;
        this.o = i2;
        this.p[i2] = null;
        int[] iArr = this.q;
        int i3 = i2 - 1;
        iArr[i3] = iArr[i3] + 1;
        this.j = 0;
    }

    public String U() {
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

    public boolean V() throws IOException {
        int iC = this.j;
        if (iC == 0) {
            iC = C();
        }
        return (iC == 2 || iC == 4) ? false : true;
    }

    public final boolean W() {
        return this.f4607d;
    }

    String Y() {
        return " at line " + (this.f4611h + 1) + " column " + ((this.f4609f - this.f4612i) + 1) + " path " + U();
    }

    public boolean Z() throws IOException {
        int iC = this.j;
        if (iC == 0) {
            iC = C();
        }
        if (iC == 5) {
            this.j = 0;
            int[] iArr = this.q;
            int i2 = this.o - 1;
            iArr[i2] = iArr[i2] + 1;
            return true;
        }
        if (iC == 6) {
            this.j = 0;
            int[] iArr2 = this.q;
            int i3 = this.o - 1;
            iArr2[i3] = iArr2[i3] + 1;
            return false;
        }
        throw new IllegalStateException("Expected a boolean but was " + j0() + Y());
    }

    public void a() throws IOException {
        int iC = this.j;
        if (iC == 0) {
            iC = C();
        }
        if (iC == 3) {
            m0(1);
            this.q[this.o - 1] = 0;
            this.j = 0;
        } else {
            throw new IllegalStateException("Expected BEGIN_ARRAY but was " + j0() + Y());
        }
    }

    public double a0() throws IOException {
        int iC = this.j;
        if (iC == 0) {
            iC = C();
        }
        if (iC == 15) {
            this.j = 0;
            int[] iArr = this.q;
            int i2 = this.o - 1;
            iArr[i2] = iArr[i2] + 1;
            return this.k;
        }
        if (iC == 16) {
            this.m = new String(this.f4608e, this.f4609f, this.l);
            this.f4609f += this.l;
        } else if (iC == 8 || iC == 9) {
            this.m = g0(iC == 8 ? '\'' : '\"');
        } else if (iC == 10) {
            this.m = i0();
        } else if (iC != 11) {
            throw new IllegalStateException("Expected a double but was " + j0() + Y());
        }
        this.j = 11;
        double d2 = Double.parseDouble(this.m);
        if (!this.f4607d && (Double.isNaN(d2) || Double.isInfinite(d2))) {
            throw new d("JSON forbids NaN and infinities: " + d2 + Y());
        }
        this.m = null;
        this.j = 0;
        int[] iArr2 = this.q;
        int i3 = this.o - 1;
        iArr2[i3] = iArr2[i3] + 1;
        return d2;
    }

    public int b0() throws IOException {
        int iC = this.j;
        if (iC == 0) {
            iC = C();
        }
        if (iC == 15) {
            long j = this.k;
            int i2 = (int) j;
            if (j == i2) {
                this.j = 0;
                int[] iArr = this.q;
                int i3 = this.o - 1;
                iArr[i3] = iArr[i3] + 1;
                return i2;
            }
            throw new NumberFormatException("Expected an int but was " + this.k + Y());
        }
        if (iC == 16) {
            this.m = new String(this.f4608e, this.f4609f, this.l);
            this.f4609f += this.l;
        } else {
            if (iC != 8 && iC != 9 && iC != 10) {
                throw new IllegalStateException("Expected an int but was " + j0() + Y());
            }
            if (iC == 10) {
                this.m = i0();
            } else {
                this.m = g0(iC == 8 ? '\'' : '\"');
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
            throw new NumberFormatException("Expected an int but was " + this.m + Y());
        }
        this.m = null;
        this.j = 0;
        int[] iArr3 = this.q;
        int i7 = this.o - 1;
        iArr3[i7] = iArr3[i7] + 1;
        return i6;
    }

    public long c0() throws IOException {
        int iC = this.j;
        if (iC == 0) {
            iC = C();
        }
        if (iC == 15) {
            this.j = 0;
            int[] iArr = this.q;
            int i2 = this.o - 1;
            iArr[i2] = iArr[i2] + 1;
            return this.k;
        }
        if (iC == 16) {
            this.m = new String(this.f4608e, this.f4609f, this.l);
            this.f4609f += this.l;
        } else {
            if (iC != 8 && iC != 9 && iC != 10) {
                throw new IllegalStateException("Expected a long but was " + j0() + Y());
            }
            if (iC == 10) {
                this.m = i0();
            } else {
                this.m = g0(iC == 8 ? '\'' : '\"');
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
            throw new NumberFormatException("Expected a long but was " + this.m + Y());
        }
        this.m = null;
        this.j = 0;
        int[] iArr3 = this.q;
        int i4 = this.o - 1;
        iArr3[i4] = iArr3[i4] + 1;
        return j2;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.j = 0;
        this.n[0] = 8;
        this.o = 1;
        this.f4606c.close();
    }

    public void d() throws IOException {
        int iC = this.j;
        if (iC == 0) {
            iC = C();
        }
        if (iC == 1) {
            m0(3);
            this.j = 0;
        } else {
            throw new IllegalStateException("Expected BEGIN_OBJECT but was " + j0() + Y());
        }
    }

    public String d0() throws IOException {
        String strG0;
        int iC = this.j;
        if (iC == 0) {
            iC = C();
        }
        if (iC == 14) {
            strG0 = i0();
        } else if (iC == 12) {
            strG0 = g0('\'');
        } else {
            if (iC != 13) {
                throw new IllegalStateException("Expected a name but was " + j0() + Y());
            }
            strG0 = g0('\"');
        }
        this.j = 0;
        this.p[this.o - 1] = strG0;
        return strG0;
    }

    public void f0() throws IOException {
        int iC = this.j;
        if (iC == 0) {
            iC = C();
        }
        if (iC == 7) {
            this.j = 0;
            int[] iArr = this.q;
            int i2 = this.o - 1;
            iArr[i2] = iArr[i2] + 1;
            return;
        }
        throw new IllegalStateException("Expected null but was " + j0() + Y());
    }

    public String h0() {
        String str;
        int iC = this.j;
        if (iC == 0) {
            iC = C();
        }
        if (iC == 10) {
            str = i0();
        } else if (iC == 8) {
            str = g0('\'');
        } else if (iC == 9) {
            str = g0('\"');
        } else if (iC == 11) {
            str = this.m;
            this.m = null;
        } else if (iC == 15) {
            str = Long.toString(this.k);
        } else {
            if (iC != 16) {
                throw new IllegalStateException("Expected a string but was " + j0() + Y());
            }
            str = new String(this.f4608e, this.f4609f, this.l);
            this.f4609f += this.l;
        }
        this.j = 0;
        int[] iArr = this.q;
        int i2 = this.o - 1;
        iArr[i2] = iArr[i2] + 1;
        return str;
    }

    public b j0() throws IOException {
        int iC = this.j;
        if (iC == 0) {
            iC = C();
        }
        switch (iC) {
            case 1:
                return b.BEGIN_OBJECT;
            case 2:
                return b.END_OBJECT;
            case 3:
                return b.BEGIN_ARRAY;
            case 4:
                return b.END_ARRAY;
            case 5:
            case 6:
                return b.BOOLEAN;
            case 7:
                return b.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return b.STRING;
            case 12:
            case 13:
            case 14:
                return b.NAME;
            case 15:
            case 16:
                return b.NUMBER;
            case 17:
                return b.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    public final void o0(boolean z) {
        this.f4607d = z;
    }

    public void t0() throws IOException {
        int i2 = 0;
        do {
            int iC = this.j;
            if (iC == 0) {
                iC = C();
            }
            if (iC == 3) {
                m0(1);
            } else if (iC == 1) {
                m0(3);
            } else if (iC == 4 || iC == 2) {
                this.o--;
                i2--;
                this.j = 0;
            } else {
                if (iC == 14 || iC == 10) {
                    s0();
                } else if (iC == 8 || iC == 12) {
                    p0('\'');
                } else if (iC == 9 || iC == 13) {
                    p0('\"');
                } else if (iC == 16) {
                    this.f4609f += this.l;
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

    public String toString() {
        return getClass().getSimpleName() + Y();
    }
}
