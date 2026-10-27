package f.b0.k;

import javax.security.auth.x500.X500Principal;

/* JADX INFO: compiled from: DistinguishedNameParser.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f5287a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f5288b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f5289c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f5290d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f5291e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f5292f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public char[] f5293g;

    public c(X500Principal x500Principal) {
        String name = x500Principal.getName("RFC2253");
        this.f5287a = name;
        this.f5288b = name.length();
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0053, code lost:
    
        r1 = r8.f5293g;
        r2 = r8.f5290d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x005f, code lost:
    
        return new java.lang.String(r1, r2, r8.f5291e - r2);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String a() {
        /*
            r8 = this;
            int r0 = r8.f5289c
            r8.f5290d = r0
            r8.f5291e = r0
        L6:
            int r0 = r8.f5289c
            int r1 = r8.f5288b
            if (r0 < r1) goto L19
            java.lang.String r0 = new java.lang.String
            char[] r1 = r8.f5293g
            int r2 = r8.f5290d
            int r3 = r8.f5291e
            int r3 = r3 - r2
            r0.<init>(r1, r2, r3)
            return r0
        L19:
            char[] r1 = r8.f5293g
            char r2 = r1[r0]
            r3 = 44
            r4 = 43
            r5 = 59
            r6 = 32
            if (r2 == r6) goto L60
            if (r2 == r5) goto L53
            r5 = 92
            if (r2 == r5) goto L40
            if (r2 == r4) goto L53
            if (r2 == r3) goto L53
            int r2 = r8.f5291e
            int r3 = r2 + 1
            r8.f5291e = r3
            char r3 = r1[r0]
            r1[r2] = r3
            int r0 = r0 + 1
            r8.f5289c = r0
            goto L6
        L40:
            int r0 = r8.f5291e
            int r2 = r0 + 1
            r8.f5291e = r2
            char r2 = r8.d()
            r1[r0] = r2
            int r0 = r8.f5289c
            int r0 = r0 + 1
            r8.f5289c = r0
            goto L6
        L53:
            java.lang.String r0 = new java.lang.String
            char[] r1 = r8.f5293g
            int r2 = r8.f5290d
            int r3 = r8.f5291e
            int r3 = r3 - r2
            r0.<init>(r1, r2, r3)
            return r0
        L60:
            int r2 = r8.f5291e
            r8.f5292f = r2
            int r0 = r0 + 1
            r8.f5289c = r0
            int r0 = r2 + 1
            r8.f5291e = r0
            r1[r2] = r6
        L6e:
            int r0 = r8.f5289c
            int r1 = r8.f5288b
            if (r0 >= r1) goto L87
            char[] r2 = r8.f5293g
            char r7 = r2[r0]
            if (r7 != r6) goto L87
            int r1 = r8.f5291e
            int r7 = r1 + 1
            r8.f5291e = r7
            r2[r1] = r6
            int r0 = r0 + 1
            r8.f5289c = r0
            goto L6e
        L87:
            if (r0 == r1) goto L97
            char[] r1 = r8.f5293g
            char r2 = r1[r0]
            if (r2 == r3) goto L97
            char r2 = r1[r0]
            if (r2 == r4) goto L97
            char r0 = r1[r0]
            if (r0 != r5) goto L6
        L97:
            java.lang.String r0 = new java.lang.String
            char[] r1 = r8.f5293g
            int r2 = r8.f5290d
            int r3 = r8.f5292f
            int r3 = r3 - r2
            r0.<init>(r1, r2, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: f.b0.k.c.a():java.lang.String");
    }

    public String b(String str) {
        this.f5289c = 0;
        this.f5290d = 0;
        this.f5291e = 0;
        this.f5292f = 0;
        this.f5293g = this.f5287a.toCharArray();
        String strG = g();
        if (strG == null) {
            return null;
        }
        do {
            int i2 = this.f5289c;
            if (i2 == this.f5288b) {
                return null;
            }
            char c2 = this.f5293g[i2];
            String strA = c2 != '\"' ? c2 != '#' ? (c2 == '+' || c2 == ',' || c2 == ';') ? "" : a() : f() : h();
            if (str.equalsIgnoreCase(strG)) {
                return strA;
            }
            int i3 = this.f5289c;
            if (i3 >= this.f5288b) {
                return null;
            }
            char[] cArr = this.f5293g;
            if (cArr[i3] != ',' && cArr[i3] != ';' && cArr[i3] != '+') {
                throw new IllegalStateException("Malformed DN: " + this.f5287a);
            }
            this.f5289c = i3 + 1;
            strG = g();
        } while (strG != null);
        throw new IllegalStateException("Malformed DN: " + this.f5287a);
    }

    public final int c(int i2) {
        int i3;
        int i4;
        int i5 = i2 + 1;
        if (i5 >= this.f5288b) {
            throw new IllegalStateException("Malformed DN: " + this.f5287a);
        }
        char[] cArr = this.f5293g;
        char c2 = cArr[i2];
        if (c2 >= '0' && c2 <= '9') {
            i3 = c2 - '0';
        } else if (c2 >= 'a' && c2 <= 'f') {
            i3 = c2 - 'W';
        } else {
            if (c2 < 'A' || c2 > 'F') {
                throw new IllegalStateException("Malformed DN: " + this.f5287a);
            }
            i3 = c2 - '7';
        }
        char c3 = cArr[i5];
        if (c3 >= '0' && c3 <= '9') {
            i4 = c3 - '0';
        } else if (c3 >= 'a' && c3 <= 'f') {
            i4 = c3 - 'W';
        } else {
            if (c3 < 'A' || c3 > 'F') {
                throw new IllegalStateException("Malformed DN: " + this.f5287a);
            }
            i4 = c3 - '7';
        }
        return (i3 << 4) + i4;
    }

    public final char d() {
        int i2 = this.f5289c + 1;
        this.f5289c = i2;
        if (i2 == this.f5288b) {
            throw new IllegalStateException("Unexpected end of DN: " + this.f5287a);
        }
        char[] cArr = this.f5293g;
        char c2 = cArr[i2];
        if (c2 != ' ' && c2 != '%' && c2 != '\\' && c2 != '_' && c2 != '\"' && c2 != '#') {
            switch (c2) {
                case '*':
                case '+':
                case ',':
                    break;
                default:
                    switch (c2) {
                        case ';':
                        case '<':
                        case '=':
                        case '>':
                            break;
                        default:
                            return e();
                    }
                    break;
            }
        }
        return cArr[i2];
    }

    public final char e() {
        int i2;
        int i3;
        int iC = c(this.f5289c);
        this.f5289c++;
        if (iC < 128) {
            return (char) iC;
        }
        if (iC < 192 || iC > 247) {
            return '?';
        }
        if (iC <= 223) {
            i3 = iC & 31;
            i2 = 1;
        } else if (iC <= 239) {
            i2 = 2;
            i3 = iC & 15;
        } else {
            i2 = 3;
            i3 = iC & 7;
        }
        for (int i4 = 0; i4 < i2; i4++) {
            int i5 = this.f5289c + 1;
            this.f5289c = i5;
            if (i5 == this.f5288b || this.f5293g[i5] != '\\') {
                return '?';
            }
            int i6 = i5 + 1;
            this.f5289c = i6;
            int iC2 = c(i6);
            this.f5289c++;
            if ((iC2 & 192) != 128) {
                return '?';
            }
            i3 = (i3 << 6) + (iC2 & 63);
        }
        return (char) i3;
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x005f, code lost:
    
        r6.f5291e = r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String f() {
        /*
            r6 = this;
            int r0 = r6.f5289c
            int r1 = r0 + 4
            int r2 = r6.f5288b
            java.lang.String r3 = "Unexpected end of DN: "
            if (r1 >= r2) goto La3
            r6.f5290d = r0
            int r0 = r0 + 1
            r6.f5289c = r0
        L10:
            int r0 = r6.f5289c
            int r1 = r6.f5288b
            if (r0 == r1) goto L5f
            char[] r1 = r6.f5293g
            char r2 = r1[r0]
            r4 = 43
            if (r2 == r4) goto L5f
            char r2 = r1[r0]
            r4 = 44
            if (r2 == r4) goto L5f
            char r2 = r1[r0]
            r4 = 59
            if (r2 != r4) goto L2b
            goto L5f
        L2b:
            char r2 = r1[r0]
            r4 = 32
            if (r2 != r4) goto L48
            r6.f5291e = r0
            int r0 = r0 + 1
            r6.f5289c = r0
        L37:
            int r0 = r6.f5289c
            int r1 = r6.f5288b
            if (r0 >= r1) goto L61
            char[] r1 = r6.f5293g
            char r1 = r1[r0]
            if (r1 != r4) goto L61
            int r0 = r0 + 1
            r6.f5289c = r0
            goto L37
        L48:
            char r2 = r1[r0]
            r5 = 65
            if (r2 < r5) goto L5a
            char r2 = r1[r0]
            r5 = 70
            if (r2 > r5) goto L5a
            char r2 = r1[r0]
            int r2 = r2 + r4
            char r2 = (char) r2
            r1[r0] = r2
        L5a:
            int r0 = r0 + 1
            r6.f5289c = r0
            goto L10
        L5f:
            r6.f5291e = r0
        L61:
            int r0 = r6.f5291e
            int r1 = r6.f5290d
            int r0 = r0 - r1
            r2 = 5
            if (r0 < r2) goto L8c
            r2 = r0 & 1
            if (r2 == 0) goto L8c
            int r2 = r0 / 2
            byte[] r3 = new byte[r2]
            r4 = 0
            int r1 = r1 + 1
        L74:
            if (r4 >= r2) goto L82
            int r5 = r6.c(r1)
            byte r5 = (byte) r5
            r3[r4] = r5
            int r1 = r1 + 2
            int r4 = r4 + 1
            goto L74
        L82:
            java.lang.String r1 = new java.lang.String
            char[] r2 = r6.f5293g
            int r3 = r6.f5290d
            r1.<init>(r2, r3, r0)
            return r1
        L8c:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r3)
            java.lang.String r2 = r6.f5287a
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        La3:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r3)
            java.lang.String r2 = r6.f5287a
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: f.b0.k.c.f():java.lang.String");
    }

    public final String g() {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        while (true) {
            i2 = this.f5289c;
            i3 = this.f5288b;
            if (i2 >= i3 || this.f5293g[i2] != ' ') {
                break;
            }
            this.f5289c = i2 + 1;
        }
        if (i2 == i3) {
            return null;
        }
        this.f5290d = i2;
        this.f5289c = i2 + 1;
        while (true) {
            i4 = this.f5289c;
            i5 = this.f5288b;
            if (i4 >= i5) {
                break;
            }
            char[] cArr = this.f5293g;
            if (cArr[i4] == '=' || cArr[i4] == ' ') {
                break;
            }
            this.f5289c = i4 + 1;
        }
        if (i4 >= i5) {
            throw new IllegalStateException("Unexpected end of DN: " + this.f5287a);
        }
        this.f5291e = i4;
        if (this.f5293g[i4] == ' ') {
            while (true) {
                i6 = this.f5289c;
                i7 = this.f5288b;
                if (i6 >= i7) {
                    break;
                }
                char[] cArr2 = this.f5293g;
                if (cArr2[i6] == '=' || cArr2[i6] != ' ') {
                    break;
                }
                this.f5289c = i6 + 1;
            }
            if (this.f5293g[i6] != '=' || i6 == i7) {
                throw new IllegalStateException("Unexpected end of DN: " + this.f5287a);
            }
        }
        this.f5289c++;
        while (true) {
            int i8 = this.f5289c;
            if (i8 >= this.f5288b || this.f5293g[i8] != ' ') {
                break;
            }
            this.f5289c = i8 + 1;
        }
        int i9 = this.f5291e;
        int i10 = this.f5290d;
        if (i9 - i10 > 4) {
            char[] cArr3 = this.f5293g;
            if (cArr3[i10 + 3] == '.' && ((cArr3[i10] == 'O' || cArr3[i10] == 'o') && ((cArr3[i10 + 1] == 'I' || cArr3[i10 + 1] == 'i') && (cArr3[i10 + 2] == 'D' || cArr3[i10 + 2] == 'd')))) {
                this.f5290d = i10 + 4;
            }
        }
        char[] cArr4 = this.f5293g;
        int i11 = this.f5290d;
        return new String(cArr4, i11, this.f5291e - i11);
    }

    public final String h() {
        int i2 = this.f5289c + 1;
        this.f5289c = i2;
        this.f5290d = i2;
        this.f5291e = i2;
        while (true) {
            int i3 = this.f5289c;
            if (i3 == this.f5288b) {
                throw new IllegalStateException("Unexpected end of DN: " + this.f5287a);
            }
            char[] cArr = this.f5293g;
            if (cArr[i3] == '\"') {
                this.f5289c = i3 + 1;
                while (true) {
                    int i4 = this.f5289c;
                    if (i4 >= this.f5288b || this.f5293g[i4] != ' ') {
                        break;
                    }
                    this.f5289c = i4 + 1;
                }
                char[] cArr2 = this.f5293g;
                int i5 = this.f5290d;
                return new String(cArr2, i5, this.f5291e - i5);
            }
            if (cArr[i3] == '\\') {
                cArr[this.f5291e] = d();
            } else {
                cArr[this.f5291e] = cArr[i3];
            }
            this.f5289c++;
            this.f5291e++;
        }
    }
}
