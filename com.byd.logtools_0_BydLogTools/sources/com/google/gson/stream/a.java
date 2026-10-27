package com.google.gson.stream;

import com.google.gson.internal.e;
import com.google.gson.internal.k.f;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: compiled from: JsonReader.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements Closeable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Reader f4157b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f4158c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final char[] f4159d = new char[1024];

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f4160e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f4161f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f4162g = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f4163h = 0;
    int i = 0;
    private long j;
    private int k;
    private String l;
    private int[] m;
    private int n;
    private String[] o;
    private int[] p;

    /* JADX INFO: renamed from: com.google.gson.stream.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: JsonReader.java */
    class C0079a extends e {
        C0079a() {
        }

        @Override // com.google.gson.internal.e
        public void a(a aVar) throws IOException {
            if (aVar instanceof f) {
                ((f) aVar).N();
                return;
            }
            int iE = aVar.i;
            if (iE == 0) {
                iE = aVar.e();
            }
            if (iE == 13) {
                aVar.i = 9;
                return;
            }
            if (iE == 12) {
                aVar.i = 8;
                return;
            }
            if (iE == 14) {
                aVar.i = 10;
                return;
            }
            throw new IllegalStateException("Expected a name but was " + aVar.x() + aVar.m());
        }
    }

    static {
        e.f4046a = new C0079a();
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
        this.f4157b = reader;
    }

    private void A(int i) {
        int i2 = this.n;
        int[] iArr = this.m;
        if (i2 == iArr.length) {
            int i3 = i2 * 2;
            this.m = Arrays.copyOf(iArr, i3);
            this.p = Arrays.copyOf(this.p, i3);
            this.o = (String[]) Arrays.copyOf(this.o, i3);
        }
        int[] iArr2 = this.m;
        int i4 = this.n;
        this.n = i4 + 1;
        iArr2[i4] = i;
    }

    private char B() throws IOException {
        int i;
        int i2;
        if (this.f4160e == this.f4161f && !h(1)) {
            throw I("Unterminated escape sequence");
        }
        char[] cArr = this.f4159d;
        int i3 = this.f4160e;
        int i4 = i3 + 1;
        this.f4160e = i4;
        char c2 = cArr[i3];
        if (c2 == '\n') {
            this.f4162g++;
            this.f4163h = i4;
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
                throw I("Invalid escape sequence");
            }
            if (i4 + 4 > this.f4161f && !h(4)) {
                throw I("Unterminated escape sequence");
            }
            char c3 = 0;
            int i5 = this.f4160e;
            int i6 = i5 + 4;
            while (i5 < i6) {
                char c4 = this.f4159d[i5];
                char c5 = (char) (c3 << 4);
                if (c4 < '0' || c4 > '9') {
                    if (c4 >= 'a' && c4 <= 'f') {
                        i = c4 - 'a';
                    } else {
                        if (c4 < 'A' || c4 > 'F') {
                            throw new NumberFormatException("\\u" + new String(this.f4159d, this.f4160e, 4));
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
            this.f4160e += 4;
            return c3;
        }
        return c2;
    }

    private void D(char c2) throws IOException {
        char[] cArr = this.f4159d;
        do {
            int i = this.f4160e;
            int i2 = this.f4161f;
            while (i < i2) {
                int i3 = i + 1;
                char c3 = cArr[i];
                if (c3 == c2) {
                    this.f4160e = i3;
                    return;
                }
                if (c3 == '\\') {
                    this.f4160e = i3;
                    B();
                    i = this.f4160e;
                    i2 = this.f4161f;
                } else {
                    if (c3 == '\n') {
                        this.f4162g++;
                        this.f4163h = i3;
                    }
                    i = i3;
                }
            }
            this.f4160e = i;
        } while (h(1));
        throw I("Unterminated string");
    }

    private boolean E(String str) throws IOException {
        int length = str.length();
        while (true) {
            if (this.f4160e + length > this.f4161f && !h(length)) {
                return false;
            }
            char[] cArr = this.f4159d;
            int i = this.f4160e;
            if (cArr[i] != '\n') {
                for (int i2 = 0; i2 < length; i2++) {
                    if (this.f4159d[this.f4160e + i2] != str.charAt(i2)) {
                        break;
                    }
                }
                return true;
            }
            this.f4162g++;
            this.f4163h = i + 1;
            this.f4160e++;
        }
    }

    private void F() throws IOException {
        char c2;
        do {
            if (this.f4160e >= this.f4161f && !h(1)) {
                return;
            }
            char[] cArr = this.f4159d;
            int i = this.f4160e;
            int i2 = i + 1;
            this.f4160e = i2;
            c2 = cArr[i];
            if (c2 == '\n') {
                this.f4162g++;
                this.f4163h = i2;
                return;
            }
        } while (c2 != '\r');
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0048, code lost:
    
        c();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void G() throws java.io.IOException {
        /*
            r4 = this;
        L0:
            r0 = 0
        L1:
            int r1 = r4.f4160e
            int r2 = r1 + r0
            int r3 = r4.f4161f
            if (r2 >= r3) goto L51
            char[] r2 = r4.f4159d
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
            r4.c()
        L4b:
            int r1 = r4.f4160e
            int r1 = r1 + r0
            r4.f4160e = r1
            return
        L51:
            int r1 = r1 + r0
            r4.f4160e = r1
            r0 = 1
            boolean r0 = r4.h(r0)
            if (r0 != 0) goto L0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.stream.a.G():void");
    }

    private IOException I(String str) throws IOException {
        throw new MalformedJsonException(str + m());
    }

    private void c() throws IOException {
        if (!this.f4158c) {
            throw I("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    private void d() throws IOException {
        s(true);
        int i = this.f4160e - 1;
        this.f4160e = i;
        if (i + 5 <= this.f4161f || h(5)) {
            char[] cArr = this.f4159d;
            if (cArr[i] == ')' && cArr[i + 1] == ']' && cArr[i + 2] == '}' && cArr[i + 3] == '\'' && cArr[i + 4] == '\n') {
                this.f4160e += 5;
            }
        }
    }

    private boolean h(int i) throws IOException {
        int i2;
        int i3;
        char[] cArr = this.f4159d;
        int i4 = this.f4163h;
        int i5 = this.f4160e;
        this.f4163h = i4 - i5;
        int i6 = this.f4161f;
        if (i6 != i5) {
            int i7 = i6 - i5;
            this.f4161f = i7;
            System.arraycopy(cArr, i5, cArr, 0, i7);
        } else {
            this.f4161f = 0;
        }
        this.f4160e = 0;
        do {
            Reader reader = this.f4157b;
            int i8 = this.f4161f;
            int i9 = reader.read(cArr, i8, cArr.length - i8);
            if (i9 == -1) {
                return false;
            }
            i2 = this.f4161f + i9;
            this.f4161f = i2;
            if (this.f4162g == 0 && (i3 = this.f4163h) == 0 && i2 > 0 && cArr[0] == 65279) {
                this.f4160e++;
                this.f4163h = i3 + 1;
                i++;
            }
        } while (i2 < i);
        return true;
    }

    private boolean l(char c2) throws IOException {
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
        c();
        return false;
    }

    private int s(boolean z) throws IOException {
        char[] cArr = this.f4159d;
        int i = this.f4160e;
        int i2 = this.f4161f;
        while (true) {
            if (i == i2) {
                this.f4160e = i;
                if (!h(1)) {
                    if (!z) {
                        return -1;
                    }
                    throw new EOFException("End of input" + m());
                }
                i = this.f4160e;
                i2 = this.f4161f;
            }
            int i3 = i + 1;
            char c2 = cArr[i];
            if (c2 == '\n') {
                this.f4162g++;
                this.f4163h = i3;
            } else if (c2 != ' ' && c2 != '\r' && c2 != '\t') {
                if (c2 == '/') {
                    this.f4160e = i3;
                    if (i3 == i2) {
                        this.f4160e = i3 - 1;
                        boolean zH = h(2);
                        this.f4160e++;
                        if (!zH) {
                            return c2;
                        }
                    }
                    c();
                    int i4 = this.f4160e;
                    char c3 = cArr[i4];
                    if (c3 == '*') {
                        this.f4160e = i4 + 1;
                        if (!E("*/")) {
                            throw I("Unterminated comment");
                        }
                        i = this.f4160e + 2;
                        i2 = this.f4161f;
                    } else {
                        if (c3 != '/') {
                            return c2;
                        }
                        this.f4160e = i4 + 1;
                        F();
                        i = this.f4160e;
                        i2 = this.f4161f;
                    }
                } else {
                    if (c2 != '#') {
                        this.f4160e = i3;
                        return c2;
                    }
                    this.f4160e = i3;
                    c();
                    F();
                    i = this.f4160e;
                    i2 = this.f4161f;
                }
            }
            i = i3;
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
        r9.f4160e = r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String u(char r10) throws java.io.IOException {
        /*
            r9 = this;
            char[] r0 = r9.f4159d
            r1 = 0
        L3:
            int r2 = r9.f4160e
            int r3 = r9.f4161f
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
            r9.f4160e = r7
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
            r9.f4160e = r7
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
            char r2 = r9.B()
            r1.append(r2)
            int r2 = r9.f4160e
            int r3 = r9.f4161f
            goto L7
        L4f:
            r5 = 10
            if (r2 != r5) goto L5a
            int r2 = r9.f4162g
            int r2 = r2 + r6
            r9.f4162g = r2
            r9.f4163h = r7
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
            r9.f4160e = r2
            boolean r2 = r9.h(r6)
            if (r2 == 0) goto L7a
            goto L3
        L7a:
            java.lang.String r10 = "Unterminated string"
            java.io.IOException r10 = r9.I(r10)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.stream.a.u(char):java.lang.String");
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
    private java.lang.String w() throws java.io.IOException {
        /*
            r6 = this;
            r0 = 0
            r1 = 0
        L2:
            r2 = r0
        L3:
            int r3 = r6.f4160e
            int r4 = r3 + r2
            int r5 = r6.f4161f
            if (r4 >= r5) goto L4e
            char[] r4 = r6.f4159d
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
            char[] r3 = r6.f4159d
            int r3 = r3.length
            if (r2 >= r3) goto L5e
            int r3 = r2 + 1
            boolean r3 = r6.h(r3)
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
            char[] r3 = r6.f4159d
            int r4 = r6.f4160e
            r1.append(r3, r4, r2)
            int r3 = r6.f4160e
            int r3 = r3 + r2
            r6.f4160e = r3
            r2 = 1
            boolean r2 = r6.h(r2)
            if (r2 != 0) goto L2
        L7e:
            if (r1 != 0) goto L8a
            java.lang.String r1 = new java.lang.String
            char[] r2 = r6.f4159d
            int r3 = r6.f4160e
            r1.<init>(r2, r3, r0)
            goto L95
        L8a:
            char[] r2 = r6.f4159d
            int r3 = r6.f4160e
            r1.append(r2, r3, r0)
            java.lang.String r1 = r1.toString()
        L95:
            int r2 = r6.f4160e
            int r2 = r2 + r0
            r6.f4160e = r2
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.stream.a.w():java.lang.String");
    }

    private int y() throws IOException {
        int i;
        String str;
        String str2;
        char c2 = this.f4159d[this.f4160e];
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
            if (this.f4160e + i2 >= this.f4161f && !h(i2 + 1)) {
                return 0;
            }
            char c3 = this.f4159d[this.f4160e + i2];
            if (c3 != str.charAt(i2) && c3 != str2.charAt(i2)) {
                return 0;
            }
        }
        if ((this.f4160e + length < this.f4161f || h(length + 1)) && l(this.f4159d[this.f4160e + length])) {
            return 0;
        }
        this.f4160e += length;
        this.i = i;
        return i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x0091, code lost:
    
        if (l(r14) != false) goto L78;
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
    
        r18.j = r11;
        r18.f4160e += r8;
        r18.i = 15;
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
    
        r18.k = r8;
        r18.i = 16;
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
    private int z() throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 247
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.stream.a.z():int");
    }

    public final void C(boolean z) {
        this.f4158c = z;
    }

    public void H() throws IOException {
        int i = 0;
        do {
            int iE = this.i;
            if (iE == 0) {
                iE = e();
            }
            if (iE == 3) {
                A(1);
            } else if (iE == 1) {
                A(3);
            } else if (iE == 4 || iE == 2) {
                this.n--;
                i--;
                this.i = 0;
            } else {
                if (iE == 14 || iE == 10) {
                    G();
                } else if (iE == 8 || iE == 12) {
                    D('\'');
                } else if (iE == 9 || iE == 13) {
                    D('\"');
                } else if (iE == 16) {
                    this.f4160e += this.k;
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

    public void a() throws IOException {
        int iE = this.i;
        if (iE == 0) {
            iE = e();
        }
        if (iE == 3) {
            A(1);
            this.p[this.n - 1] = 0;
            this.i = 0;
        } else {
            throw new IllegalStateException("Expected BEGIN_ARRAY but was " + x() + m());
        }
    }

    public void b() throws IOException {
        int iE = this.i;
        if (iE == 0) {
            iE = e();
        }
        if (iE == 1) {
            A(3);
            this.i = 0;
        } else {
            throw new IllegalStateException("Expected BEGIN_OBJECT but was " + x() + m());
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.i = 0;
        this.m[0] = 8;
        this.n = 1;
        this.f4157b.close();
    }

    int e() throws IOException {
        int iS;
        int[] iArr = this.m;
        int i = this.n;
        int i2 = iArr[i - 1];
        if (i2 == 1) {
            iArr[i - 1] = 2;
        } else if (i2 == 2) {
            int iS2 = s(true);
            if (iS2 != 44) {
                if (iS2 != 59) {
                    if (iS2 != 93) {
                        throw I("Unterminated array");
                    }
                    this.i = 4;
                    return 4;
                }
                c();
            }
        } else {
            if (i2 == 3 || i2 == 5) {
                iArr[i - 1] = 4;
                if (i2 == 5 && (iS = s(true)) != 44) {
                    if (iS != 59) {
                        if (iS != 125) {
                            throw I("Unterminated object");
                        }
                        this.i = 2;
                        return 2;
                    }
                    c();
                }
                int iS3 = s(true);
                if (iS3 == 34) {
                    this.i = 13;
                    return 13;
                }
                if (iS3 == 39) {
                    c();
                    this.i = 12;
                    return 12;
                }
                if (iS3 == 125) {
                    if (i2 == 5) {
                        throw I("Expected name");
                    }
                    this.i = 2;
                    return 2;
                }
                c();
                this.f4160e--;
                if (!l((char) iS3)) {
                    throw I("Expected name");
                }
                this.i = 14;
                return 14;
            }
            if (i2 == 4) {
                iArr[i - 1] = 5;
                int iS4 = s(true);
                if (iS4 != 58) {
                    if (iS4 != 61) {
                        throw I("Expected ':'");
                    }
                    c();
                    if (this.f4160e < this.f4161f || h(1)) {
                        char[] cArr = this.f4159d;
                        int i3 = this.f4160e;
                        if (cArr[i3] == '>') {
                            this.f4160e = i3 + 1;
                        }
                    }
                }
            } else if (i2 == 6) {
                if (this.f4158c) {
                    d();
                }
                this.m[this.n - 1] = 7;
            } else if (i2 == 7) {
                if (s(false) == -1) {
                    this.i = 17;
                    return 17;
                }
                c();
                this.f4160e--;
            } else if (i2 == 8) {
                throw new IllegalStateException("JsonReader is closed");
            }
        }
        int iS5 = s(true);
        if (iS5 == 34) {
            this.i = 9;
            return 9;
        }
        if (iS5 == 39) {
            c();
            this.i = 8;
            return 8;
        }
        if (iS5 != 44 && iS5 != 59) {
            if (iS5 == 91) {
                this.i = 3;
                return 3;
            }
            if (iS5 != 93) {
                if (iS5 == 123) {
                    this.i = 1;
                    return 1;
                }
                this.f4160e--;
                int iY = y();
                if (iY != 0) {
                    return iY;
                }
                int iZ = z();
                if (iZ != 0) {
                    return iZ;
                }
                if (!l(this.f4159d[this.f4160e])) {
                    throw I("Expected value");
                }
                c();
                this.i = 10;
                return 10;
            }
            if (i2 == 1) {
                this.i = 4;
                return 4;
            }
        }
        if (i2 != 1 && i2 != 2) {
            throw I("Unexpected value");
        }
        c();
        this.f4160e--;
        this.i = 7;
        return 7;
    }

    public void f() throws IOException {
        int iE = this.i;
        if (iE == 0) {
            iE = e();
        }
        if (iE != 4) {
            throw new IllegalStateException("Expected END_ARRAY but was " + x() + m());
        }
        int i = this.n - 1;
        this.n = i;
        int[] iArr = this.p;
        int i2 = i - 1;
        iArr[i2] = iArr[i2] + 1;
        this.i = 0;
    }

    public void g() throws IOException {
        int iE = this.i;
        if (iE == 0) {
            iE = e();
        }
        if (iE != 2) {
            throw new IllegalStateException("Expected END_OBJECT but was " + x() + m());
        }
        int i = this.n - 1;
        this.n = i;
        this.o[i] = null;
        int[] iArr = this.p;
        int i2 = i - 1;
        iArr[i2] = iArr[i2] + 1;
        this.i = 0;
    }

    public String i() {
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

    public boolean j() throws IOException {
        int iE = this.i;
        if (iE == 0) {
            iE = e();
        }
        return (iE == 2 || iE == 4) ? false : true;
    }

    public final boolean k() {
        return this.f4158c;
    }

    String m() {
        return " at line " + (this.f4162g + 1) + " column " + ((this.f4160e - this.f4163h) + 1) + " path " + i();
    }

    public boolean n() throws IOException {
        int iE = this.i;
        if (iE == 0) {
            iE = e();
        }
        if (iE == 5) {
            this.i = 0;
            int[] iArr = this.p;
            int i = this.n - 1;
            iArr[i] = iArr[i] + 1;
            return true;
        }
        if (iE == 6) {
            this.i = 0;
            int[] iArr2 = this.p;
            int i2 = this.n - 1;
            iArr2[i2] = iArr2[i2] + 1;
            return false;
        }
        throw new IllegalStateException("Expected a boolean but was " + x() + m());
    }

    public double o() throws IOException {
        int iE = this.i;
        if (iE == 0) {
            iE = e();
        }
        if (iE == 15) {
            this.i = 0;
            int[] iArr = this.p;
            int i = this.n - 1;
            iArr[i] = iArr[i] + 1;
            return this.j;
        }
        if (iE == 16) {
            this.l = new String(this.f4159d, this.f4160e, this.k);
            this.f4160e += this.k;
        } else if (iE == 8 || iE == 9) {
            this.l = u(iE == 8 ? '\'' : '\"');
        } else if (iE == 10) {
            this.l = w();
        } else if (iE != 11) {
            throw new IllegalStateException("Expected a double but was " + x() + m());
        }
        this.i = 11;
        double d2 = Double.parseDouble(this.l);
        if (!this.f4158c && (Double.isNaN(d2) || Double.isInfinite(d2))) {
            throw new MalformedJsonException("JSON forbids NaN and infinities: " + d2 + m());
        }
        this.l = null;
        this.i = 0;
        int[] iArr2 = this.p;
        int i2 = this.n - 1;
        iArr2[i2] = iArr2[i2] + 1;
        return d2;
    }

    public int p() throws IOException {
        int iE = this.i;
        if (iE == 0) {
            iE = e();
        }
        if (iE == 15) {
            long j = this.j;
            int i = (int) j;
            if (j == i) {
                this.i = 0;
                int[] iArr = this.p;
                int i2 = this.n - 1;
                iArr[i2] = iArr[i2] + 1;
                return i;
            }
            throw new NumberFormatException("Expected an int but was " + this.j + m());
        }
        if (iE == 16) {
            this.l = new String(this.f4159d, this.f4160e, this.k);
            this.f4160e += this.k;
        } else {
            if (iE != 8 && iE != 9 && iE != 10) {
                throw new IllegalStateException("Expected an int but was " + x() + m());
            }
            if (iE == 10) {
                this.l = w();
            } else {
                this.l = u(iE == 8 ? '\'' : '\"');
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
            throw new NumberFormatException("Expected an int but was " + this.l + m());
        }
        this.l = null;
        this.i = 0;
        int[] iArr3 = this.p;
        int i6 = this.n - 1;
        iArr3[i6] = iArr3[i6] + 1;
        return i5;
    }

    public long q() throws IOException {
        int iE = this.i;
        if (iE == 0) {
            iE = e();
        }
        if (iE == 15) {
            this.i = 0;
            int[] iArr = this.p;
            int i = this.n - 1;
            iArr[i] = iArr[i] + 1;
            return this.j;
        }
        if (iE == 16) {
            this.l = new String(this.f4159d, this.f4160e, this.k);
            this.f4160e += this.k;
        } else {
            if (iE != 8 && iE != 9 && iE != 10) {
                throw new IllegalStateException("Expected a long but was " + x() + m());
            }
            if (iE == 10) {
                this.l = w();
            } else {
                this.l = u(iE == 8 ? '\'' : '\"');
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
            throw new NumberFormatException("Expected a long but was " + this.l + m());
        }
        this.l = null;
        this.i = 0;
        int[] iArr3 = this.p;
        int i3 = this.n - 1;
        iArr3[i3] = iArr3[i3] + 1;
        return j2;
    }

    public String r() throws IOException {
        String strU;
        int iE = this.i;
        if (iE == 0) {
            iE = e();
        }
        if (iE == 14) {
            strU = w();
        } else if (iE == 12) {
            strU = u('\'');
        } else {
            if (iE != 13) {
                throw new IllegalStateException("Expected a name but was " + x() + m());
            }
            strU = u('\"');
        }
        this.i = 0;
        this.o[this.n - 1] = strU;
        return strU;
    }

    public void t() throws IOException {
        int iE = this.i;
        if (iE == 0) {
            iE = e();
        }
        if (iE == 7) {
            this.i = 0;
            int[] iArr = this.p;
            int i = this.n - 1;
            iArr[i] = iArr[i] + 1;
            return;
        }
        throw new IllegalStateException("Expected null but was " + x() + m());
    }

    public String toString() {
        return getClass().getSimpleName() + m();
    }

    public String v() throws IOException {
        String str;
        int iE = this.i;
        if (iE == 0) {
            iE = e();
        }
        if (iE == 10) {
            str = w();
        } else if (iE == 8) {
            str = u('\'');
        } else if (iE == 9) {
            str = u('\"');
        } else if (iE == 11) {
            str = this.l;
            this.l = null;
        } else if (iE == 15) {
            str = Long.toString(this.j);
        } else {
            if (iE != 16) {
                throw new IllegalStateException("Expected a string but was " + x() + m());
            }
            str = new String(this.f4159d, this.f4160e, this.k);
            this.f4160e += this.k;
        }
        this.i = 0;
        int[] iArr = this.p;
        int i = this.n - 1;
        iArr[i] = iArr[i] + 1;
        return str;
    }

    public JsonToken x() throws IOException {
        int iE = this.i;
        if (iE == 0) {
            iE = e();
        }
        switch (iE) {
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
}
