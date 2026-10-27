package b.a.a.j;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.Feature;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

/* JADX INFO: compiled from: JSONScanner.java */
/* JADX INFO: loaded from: classes.dex */
public final class e extends c {
    public final String t;
    public final int u;

    public e(String str) {
        this(str, b.a.a.a.DEFAULT_PARSER_FEATURE);
    }

    public static boolean N0(String str, int i, char[] cArr) {
        int length = cArr.length;
        if (length + i > str.length()) {
            return false;
        }
        for (int i2 = 0; i2 < length; i2++) {
            if (cArr[i2] != str.charAt(i + i2)) {
                return false;
            }
        }
        return true;
    }

    public static boolean O0(char c2, char c3, char c4, char c5, char c6, char c7, int i, int i2) {
        if (c2 >= '0' && c2 <= '9' && c3 >= '0' && c3 <= '9' && c4 >= '0' && c4 <= '9' && c5 >= '0' && c5 <= '9') {
            if (c6 == '0') {
                if (c7 < '1' || c7 > '9') {
                    return false;
                }
            } else if (c6 != '1' || (c7 != '0' && c7 != '1' && c7 != '2')) {
                return false;
            }
            if (i == 48) {
                return i2 >= 49 && i2 <= 57;
            }
            if (i != 49 && i != 50) {
                return i == 51 && (i2 == 48 || i2 == 49);
            }
            if (i2 >= 48 && i2 <= 57) {
                return true;
            }
        }
        return false;
    }

    @Override // b.a.a.j.c
    public String A0(char[] cArr) {
        this.q = 0;
        int i = this.h;
        char c2 = this.g;
        while (!N0(this.t, this.h, cArr)) {
            if (!c.g0(this.g)) {
                this.q = -2;
                return K0();
            }
            next();
            while (c.g0(this.g)) {
                next();
            }
        }
        int length = this.h + cArr.length;
        int i2 = length + 1;
        char cA0 = a0(length);
        int i3 = 0;
        if (cA0 != '\"') {
            while (c.g0(cA0)) {
                i3++;
                int i4 = i2 + 1;
                char cA02 = a0(i2);
                i2 = i4;
                cA0 = cA02;
            }
            if (cA0 != '\"') {
                this.q = -1;
                return K0();
            }
        }
        int iE0 = e0('\"', i2);
        if (iE0 == -1) {
            throw new JSONException("unclosed str");
        }
        String strL0 = L0(i2, iE0 - i2);
        if (strL0.indexOf(92) != -1) {
            while (true) {
                int i5 = 0;
                for (int i6 = iE0 - 1; i6 >= 0 && a0(i6) == '\\'; i6--) {
                    i5++;
                }
                if (i5 % 2 == 0) {
                    break;
                }
                iE0 = e0('\"', iE0 + 1);
            }
            int i7 = this.h;
            int length2 = iE0 - (((cArr.length + i7) + 1) + i3);
            strL0 = c.m0(M0(i7 + cArr.length + 1 + i3, length2), length2);
        }
        if ((this.f244f & Feature.TrimStringFieldValue.mask) != 0) {
            strL0 = strL0.trim();
        }
        char cA03 = a0(iE0 + 1);
        while (cA03 != ',' && cA03 != '}') {
            if (!c.g0(cA03)) {
                this.q = -1;
                return K0();
            }
            iE0++;
            cA03 = a0(iE0 + 1);
        }
        int i8 = iE0 + 1;
        this.h = i8;
        this.g = cA03;
        if (cA03 == ',') {
            int i9 = i8 + 1;
            this.h = i9;
            this.g = a0(i9);
            this.q = 3;
            return strL0;
        }
        int i10 = i8 + 1;
        this.h = i10;
        char cA04 = a0(i10);
        if (cA04 == ',') {
            this.f242d = 16;
            int i11 = this.h + 1;
            this.h = i11;
            this.g = a0(i11);
        } else if (cA04 == ']') {
            this.f242d = 15;
            int i12 = this.h + 1;
            this.h = i12;
            this.g = a0(i12);
        } else if (cA04 == '}') {
            this.f242d = 13;
            int i13 = this.h + 1;
            this.h = i13;
            this.g = a0(i13);
        } else {
            if (cA04 != 26) {
                this.h = i;
                this.g = c2;
                this.q = -1;
                return K0();
            }
            this.f242d = 20;
        }
        this.q = 4;
        return strL0;
    }

    @Override // b.a.a.j.c, b.a.a.j.b
    public long E(char c2) {
        int i;
        char cA0;
        boolean z = false;
        this.q = 0;
        int i2 = this.h;
        int i3 = i2 + 1;
        char cA02 = a0(i2);
        boolean z2 = cA02 == '\"';
        if (z2) {
            int i4 = i3 + 1;
            char cA03 = a0(i3);
            i3 = i4;
            cA02 = cA03;
        }
        boolean z3 = cA02 == '-';
        if (z3) {
            int i5 = i3 + 1;
            char cA04 = a0(i3);
            i3 = i5;
            cA02 = cA04;
        }
        char c3 = '0';
        if (cA02 >= '0' && cA02 <= '9') {
            long j = cA02 - '0';
            while (true) {
                i = i3 + 1;
                cA0 = a0(i3);
                if (cA0 < c3 || cA0 > '9') {
                    break;
                }
                j = (j * 10) + ((long) (cA0 - '0'));
                i3 = i;
                c3 = '0';
            }
            if (cA0 == '.') {
                this.q = -1;
                return 0L;
            }
            if (z2) {
                if (cA0 != '\"') {
                    this.q = -1;
                    return 0L;
                }
                cA0 = a0(i);
                i++;
            }
            if (j >= 0 || (j == Long.MIN_VALUE && z3)) {
                z = true;
            }
            if (!z) {
                this.q = -1;
                return 0L;
            }
            while (cA0 != c2) {
                if (!c.g0(cA0)) {
                    this.q = -1;
                    return j;
                }
                cA0 = a0(i);
                i++;
            }
            this.h = i;
            this.g = a0(i);
            this.q = 3;
            this.f242d = 16;
            return z3 ? -j : j;
        }
        if (cA02 == 'n') {
            int i6 = i3 + 1;
            if (a0(i3) == 'u') {
                int i7 = i6 + 1;
                if (a0(i6) == 'l') {
                    int i8 = i7 + 1;
                    if (a0(i7) == 'l') {
                        this.q = 5;
                        int i9 = i8 + 1;
                        char cA05 = a0(i8);
                        if (z2 && cA05 == '\"') {
                            int i10 = i9 + 1;
                            char cA06 = a0(i9);
                            i9 = i10;
                            cA05 = cA06;
                        }
                        while (cA05 != ',') {
                            if (cA05 == ']') {
                                this.h = i9;
                                this.g = a0(i9);
                                this.q = 5;
                                this.f242d = 15;
                                return 0L;
                            }
                            if (!c.g0(cA05)) {
                                this.q = -1;
                                return 0L;
                            }
                            int i11 = i9 + 1;
                            char cA07 = a0(i9);
                            i9 = i11;
                            cA05 = cA07;
                        }
                        this.h = i9;
                        this.g = a0(i9);
                        this.q = 5;
                        this.f242d = 16;
                        return 0L;
                    }
                }
            }
        }
        this.q = -1;
        return 0L;
    }

    @Override // b.a.a.j.c, b.a.a.j.b
    public final BigDecimal J() {
        char cA0 = a0((this.l + this.k) - 1);
        int i = this.k;
        if (cA0 == 'L' || cA0 == 'S' || cA0 == 'B' || cA0 == 'F' || cA0 == 'D') {
            i--;
        }
        if (i > 65535) {
            throw new JSONException("decimal overflow");
        }
        int i2 = this.l;
        char[] cArr = this.j;
        if (i < cArr.length) {
            this.t.getChars(i2, i2 + i, cArr, 0);
            return new BigDecimal(this.j, 0, i, MathContext.UNLIMITED);
        }
        char[] cArr2 = new char[i];
        this.t.getChars(i2, i2 + i, cArr2, 0);
        return new BigDecimal(cArr2, 0, i, MathContext.UNLIMITED);
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0080, code lost:
    
        if (r3 != '.') goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0082, code lost:
    
        r16.q = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0084, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0085, code lost:
    
        if (r7 == false) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0087, code lost:
    
        if (r3 == '\"') goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0089, code lost:
    
        r16.q = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x008b, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x008c, code lost:
    
        r3 = a0(r13);
        r13 = r13 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0093, code lost:
    
        if (r4 >= 0) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0095, code lost:
    
        r16.q = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0097, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x009a, code lost:
    
        if (r3 != r17) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x009c, code lost:
    
        r16.h = r13;
        r16.g = a0(r13);
        r16.q = 3;
        r16.f242d = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00a9, code lost:
    
        if (r8 == false) goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00ac, code lost:
    
        return -r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00b1, code lost:
    
        if (b.a.a.j.c.g0(r3) == false) goto L89;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00b3, code lost:
    
        r3 = a0(r13);
        r13 = r13 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00bb, code lost:
    
        r16.q = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00bd, code lost:
    
        if (r8 == false) goto L96;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00c0, code lost:
    
        return -r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:?, code lost:
    
        return r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:?, code lost:
    
        return r4;
     */
    @Override // b.a.a.j.c, b.a.a.j.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int L(char r17) {
        /*
            Method dump skipped, instruction units count: 302
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.a.j.e.L(char):int");
    }

    @Override // b.a.a.j.c
    public final String L0(int i, int i2) {
        if (!b.a.a.n.b.f428b) {
            return this.t.substring(i, i2 + i);
        }
        char[] cArr = this.j;
        if (i2 < cArr.length) {
            this.t.getChars(i, i + i2, cArr, 0);
            return new String(this.j, 0, i2);
        }
        char[] cArr2 = new char[i2];
        this.t.getChars(i, i2 + i, cArr2, 0);
        return new String(cArr2);
    }

    @Override // b.a.a.j.c
    public final char[] M0(int i, int i2) {
        if (b.a.a.n.b.f428b) {
            char[] cArr = this.j;
            if (i2 < cArr.length) {
                this.t.getChars(i, i2 + i, cArr, 0);
                return this.j;
            }
        }
        char[] cArr2 = new char[i2];
        this.t.getChars(i, i2 + i, cArr2, 0);
        return cArr2;
    }

    @Override // b.a.a.j.b
    public byte[] O() {
        if (this.f242d != 26) {
            return !this.m ? b.a.a.n.e.d(this.t, this.l + 1, this.k) : b.a.a.n.e.c(new String(this.j, 0, this.k));
        }
        int i = this.l + 1;
        int i2 = this.k;
        if (i2 % 2 != 0) {
            throw new JSONException("illegal state. " + i2);
        }
        int i3 = i2 / 2;
        byte[] bArr = new byte[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = (i4 * 2) + i;
            char cCharAt = this.t.charAt(i5);
            char cCharAt2 = this.t.charAt(i5 + 1);
            char c2 = '0';
            int i6 = cCharAt - (cCharAt <= '9' ? '0' : '7');
            if (cCharAt2 > '9') {
                c2 = '7';
            }
            bArr[i4] = (byte) ((i6 << 4) | (cCharAt2 - c2));
        }
        return bArr;
    }

    public final boolean P0(char c2, char c3, char c4, char c5, char c6, char c7) {
        if (c2 == '0') {
            if (c3 < '0' || c3 > '9') {
                return false;
            }
        } else {
            if (c2 != '1') {
                if (c2 == '2' && c3 >= '0' && c3 <= '4') {
                }
                return false;
            }
            if (c3 < '0' || c3 > '9') {
                return false;
            }
        }
        if (c4 < '0' || c4 > '5') {
            if (c4 != '6' || c5 != '0') {
                return false;
            }
        } else if (c5 < '0' || c5 > '9') {
            return false;
        }
        return (c6 < '0' || c6 > '5') ? c6 == '6' && c7 == '0' : c7 >= '0' && c7 <= '9';
    }

    public boolean Q0() {
        return R0(true);
    }

    public boolean R0(boolean z) {
        return S0(z, this.u - this.h);
    }

    public final boolean S0(boolean z, int i) {
        int i2;
        boolean z2;
        char c2;
        char cA0;
        char c3;
        char c4;
        char c5;
        int i3;
        int i4;
        int i5;
        int i6;
        char c6;
        char c7;
        char cA02;
        char c8;
        char c9;
        char c10;
        int i7;
        char c11;
        char c12;
        char c13;
        char c14;
        int i8;
        char c15;
        char c16;
        char c17;
        int i9;
        char cA03;
        char cA04;
        char cA05;
        int i10;
        char cA06;
        char cA07;
        char cA08;
        if (i < 8) {
            return false;
        }
        char cA09 = a0(this.h);
        char cA010 = a0(this.h + 1);
        char cA011 = a0(this.h + 2);
        int i11 = 3;
        char cA012 = a0(this.h + 3);
        char cA013 = a0(this.h + 4);
        int i12 = 5;
        char cA014 = a0(this.h + 5);
        char cA015 = a0(this.h + 6);
        char cA016 = a0(this.h + 7);
        if (!z) {
            if (i > 13) {
                char cA017 = a0((this.h + i) - 1);
                char cA018 = a0((this.h + i) - 2);
                if (cA09 == '/' && cA010 == 'D' && cA011 == 'a' && cA012 == 't' && cA013 == 'e' && cA014 == '(' && cA017 == '/' && cA018 == ')') {
                    int i13 = -1;
                    for (int i14 = 6; i14 < i; i14++) {
                        char cA019 = a0(this.h + i14);
                        if (cA019 != '+') {
                            if (cA019 < '0' || cA019 > '9') {
                                break;
                            }
                        } else {
                            i13 = i14;
                        }
                    }
                    if (i13 == -1) {
                        return false;
                    }
                    int i15 = this.h;
                    int i16 = i15 + 6;
                    long j = Long.parseLong(L0(i16, (i15 + i13) - i16));
                    Calendar calendar = Calendar.getInstance(this.o, this.p);
                    this.n = calendar;
                    calendar.setTimeInMillis(j);
                    this.f242d = 5;
                    return true;
                }
            }
            i12 = 5;
        }
        if (i == 8 || i == 14) {
            i2 = i12;
            z2 = false;
            c2 = ':';
        } else {
            if (!(i == 16 && ((cA08 = a0(this.h + 10)) == 'T' || cA08 == ' ')) && (i != 17 || a0(this.h + 6) == '-')) {
                if (i < 9) {
                    return false;
                }
                char cA020 = a0(this.h + 8);
                char cA021 = a0(this.h + 9);
                if ((cA013 == '-' && cA016 == '-') || (cA013 == '/' && cA016 == '/')) {
                    if (cA021 == ' ') {
                        c11 = cA014;
                        c12 = cA012;
                        c13 = cA09;
                        c14 = cA010;
                        c15 = '0';
                        i8 = 9;
                        cA010 = cA020;
                        c17 = cA015;
                        c16 = cA011;
                    } else {
                        c12 = cA012;
                        c13 = cA09;
                        i8 = 10;
                        c15 = cA020;
                        c17 = cA015;
                        c16 = cA011;
                        c11 = cA014;
                        c14 = cA010;
                        cA010 = cA021;
                    }
                } else if (cA013 == '-' && cA015 == '-') {
                    if (cA020 == ' ') {
                        c16 = cA011;
                        c17 = cA014;
                        c13 = cA09;
                        c14 = cA010;
                        c11 = '0';
                        i8 = 8;
                        cA010 = cA016;
                        c12 = cA012;
                        c15 = '0';
                    } else {
                        c16 = cA011;
                        c13 = cA09;
                        c11 = '0';
                        i8 = 9;
                        c17 = cA014;
                        c14 = cA010;
                        cA010 = cA020;
                        c15 = cA016;
                        c12 = cA012;
                    }
                } else if ((cA011 == '.' && cA014 == '.') || (cA011 == '-' && cA014 == '-')) {
                    c14 = cA016;
                    c12 = cA021;
                    c11 = cA012;
                    c15 = cA09;
                    i8 = 10;
                    c17 = cA013;
                    c13 = cA015;
                    c16 = cA020;
                } else if (cA020 == 'T') {
                    c11 = cA013;
                    c17 = cA014;
                    c13 = cA09;
                    c14 = cA010;
                    i8 = 8;
                    cA010 = cA016;
                    c12 = cA012;
                    c15 = cA015;
                    c16 = cA011;
                } else {
                    if (cA013 != 24180 && cA013 != 45380) {
                        return false;
                    }
                    if (cA016 != 26376 && cA016 != 50900) {
                        if (cA015 != 26376 && cA015 != 50900) {
                            return false;
                        }
                        if (cA020 == 26085 || cA020 == 51068) {
                            c16 = cA011;
                            c17 = cA014;
                            c13 = cA09;
                            c14 = cA010;
                            i8 = 10;
                            c11 = '0';
                            cA010 = cA016;
                            c12 = cA012;
                            c15 = '0';
                        } else {
                            if (cA021 != 26085 && cA021 != 51068) {
                                return false;
                            }
                            c16 = cA011;
                            c13 = cA09;
                            i8 = 10;
                            c11 = '0';
                            c17 = cA014;
                            c14 = cA010;
                            cA010 = cA020;
                            c15 = cA016;
                            c12 = cA012;
                        }
                    } else if (cA021 == 26085 || cA021 == 51068) {
                        c11 = cA014;
                        c12 = cA012;
                        c13 = cA09;
                        c14 = cA010;
                        i8 = 10;
                        c15 = '0';
                        cA010 = cA020;
                        c17 = cA015;
                        c16 = cA011;
                    } else {
                        if (a0(this.h + 10) != 26085 && a0(this.h + 10) != 51068) {
                            return false;
                        }
                        c12 = cA012;
                        c13 = cA09;
                        i8 = 11;
                        c15 = cA020;
                        c17 = cA015;
                        c16 = cA011;
                        c11 = cA014;
                        c14 = cA010;
                        cA010 = cA021;
                    }
                }
                if (!O0(c13, c14, c16, c12, c11, c17, c15, cA010)) {
                    return false;
                }
                T0(c13, c14, c16, c12, c11, c17, c15, cA010);
                char cA022 = a0(this.h + i8);
                char c18 = 'T';
                if (cA022 == 'T') {
                    if (i == 16 && i8 == 8 && a0(this.h + 15) == 'Z') {
                        char cA023 = a0(this.h + i8 + 1);
                        char cA024 = a0(this.h + i8 + 2);
                        char cA025 = a0(this.h + i8 + 3);
                        char cA026 = a0(this.h + i8 + 4);
                        char cA027 = a0(this.h + i8 + 5);
                        char cA028 = a0(this.h + i8 + 6);
                        if (!P0(cA023, cA024, cA025, cA026, cA027, cA028)) {
                            return false;
                        }
                        U0(cA023, cA024, cA025, cA026, cA027, cA028);
                        this.n.set(14, 0);
                        if (this.n.getTimeZone().getRawOffset() != 0) {
                            String[] availableIDs = TimeZone.getAvailableIDs(0);
                            if (availableIDs.length > 0) {
                                this.n.setTimeZone(TimeZone.getTimeZone(availableIDs[0]));
                            }
                        }
                        this.f242d = 5;
                        return true;
                    }
                    c18 = 'T';
                }
                if (cA022 != c18 && (cA022 != ' ' || z)) {
                    if (cA022 == '\"' || cA022 == 26 || cA022 == 26085 || cA022 == 51068) {
                        this.n.set(11, 0);
                        this.n.set(12, 0);
                        this.n.set(13, 0);
                        this.n.set(14, 0);
                        int i17 = this.h + i8;
                        this.h = i17;
                        this.g = a0(i17);
                        this.f242d = 5;
                        return true;
                    }
                    if ((cA022 != '+' && cA022 != '-') || this.u != i8 + 6 || a0(this.h + i8 + 3) != ':' || a0(this.h + i8 + 4) != '0' || a0(this.h + i8 + 5) != '0') {
                        return false;
                    }
                    U0('0', '0', '0', '0', '0', '0');
                    this.n.set(14, 0);
                    V0(cA022, a0(this.h + i8 + 1), a0(this.h + i8 + 2));
                    return true;
                }
                if (i < i8 + 9 || a0(this.h + i8 + 3) != ':' || a0(this.h + i8 + 6) != ':') {
                    return false;
                }
                char cA029 = a0(this.h + i8 + 1);
                char cA030 = a0(this.h + i8 + 2);
                char cA031 = a0(this.h + i8 + 4);
                char cA032 = a0(this.h + i8 + 5);
                char cA033 = a0(this.h + i8 + 7);
                char cA034 = a0(this.h + i8 + 8);
                if (!P0(cA029, cA030, cA031, cA032, cA033, cA034)) {
                    return false;
                }
                U0(cA029, cA030, cA031, cA032, cA033, cA034);
                int i18 = -1;
                if (a0(this.h + i8 + 9) == '.') {
                    int i19 = i8 + 11;
                    if (i < i19 || (cA05 = a0(this.h + i8 + 10)) < '0' || cA05 > '9') {
                        return false;
                    }
                    int i20 = cA05 - '0';
                    if (i <= i19 || (cA07 = a0(this.h + i8 + 11)) < '0' || cA07 > '9') {
                        i10 = 1;
                    } else {
                        i20 = (i20 * 10) + (cA07 - '0');
                        i10 = 2;
                    }
                    if (i10 != 2 || (cA06 = a0(this.h + i8 + 12)) < '0' || cA06 > '9') {
                        i9 = i20;
                        i18 = i10;
                    } else {
                        i9 = (cA06 - '0') + (i20 * 10);
                        i18 = 3;
                    }
                } else {
                    i9 = 0;
                }
                this.n.set(14, i9);
                char cA035 = a0(this.h + i8 + 10 + i18);
                if (cA035 == ' ') {
                    i18++;
                    cA035 = a0(this.h + i8 + 10 + i18);
                }
                int i21 = i18;
                char c19 = cA035;
                if (c19 == '+' || c19 == '-') {
                    char cA036 = a0(this.h + i8 + 10 + i21 + 1);
                    if (cA036 < '0' || cA036 > '1' || (cA03 = a0(this.h + i8 + 10 + i21 + 2)) < '0' || cA03 > '9') {
                        return false;
                    }
                    char cA037 = a0(this.h + i8 + 10 + i21 + 3);
                    char c20 = '3';
                    if (cA037 == ':') {
                        char cA038 = a0(this.h + i8 + 10 + i21 + 4);
                        cA04 = a0(this.h + i8 + 10 + i21 + 5);
                        if (cA038 == '4' && cA04 == '5') {
                            if (cA036 != '1' || (cA03 != '2' && cA03 != '3')) {
                                if (cA036 != '0') {
                                    return false;
                                }
                                if (cA03 != '5' && cA03 != '8') {
                                    return false;
                                }
                            }
                        } else if ((cA038 != '0' && cA038 != '3') || cA04 != '0') {
                            return false;
                        }
                        c20 = cA038;
                        i11 = 6;
                    } else {
                        if (cA037 == '0') {
                            char cA039 = a0(this.h + i8 + 10 + i21 + 4);
                            if (cA039 != '0' && cA039 != '3') {
                                return false;
                            }
                            c20 = cA039;
                        } else {
                            if (cA037 != '3' || a0(this.h + i8 + 10 + i21 + 4) != '0') {
                                if (cA037 == '4' && a0(this.h + i8 + 10 + i21 + 4) == '5') {
                                    cA04 = '5';
                                    i11 = 5;
                                    c20 = '4';
                                } else {
                                    c20 = '0';
                                }
                            }
                            cA04 = '0';
                        }
                        i11 = 5;
                        cA04 = '0';
                    }
                    W0(c19, cA036, cA03, c20, cA04);
                } else if (c19 == 'Z') {
                    if (this.n.getTimeZone().getRawOffset() != 0) {
                        String[] availableIDs2 = TimeZone.getAvailableIDs(0);
                        if (availableIDs2.length > 0) {
                            this.n.setTimeZone(TimeZone.getTimeZone(availableIDs2[0]));
                        }
                    }
                    i11 = 1;
                } else {
                    i11 = 0;
                }
                int i22 = i8 + 10 + i21 + i11;
                char cA040 = a0(this.h + i22);
                if (cA040 != 26 && cA040 != '\"') {
                    return false;
                }
                int i23 = this.h + i22;
                this.h = i23;
                this.g = a0(i23);
                this.f242d = 5;
                return true;
            }
            z2 = false;
            c2 = ':';
            i2 = 5;
        }
        if (z) {
            return z2;
        }
        char cA041 = a0(this.h + 8);
        boolean z3 = cA013 == '-' && cA016 == '-';
        boolean z4 = z3 && i == 16;
        boolean z5 = z3 && i == 17;
        if (z5 || z4) {
            cA0 = a0(this.h + 9);
            c3 = cA014;
            c4 = cA015;
            c5 = cA041;
        } else if (cA013 == '-' && cA015 == '-') {
            c4 = cA014;
            cA0 = cA016;
            c3 = '0';
            c5 = '0';
        } else {
            c3 = cA013;
            c4 = cA014;
            c5 = cA015;
            cA0 = cA016;
        }
        if (!O0(cA09, cA010, cA011, cA012, c3, c4, c5, cA0)) {
            return false;
        }
        T0(cA09, cA010, cA011, cA012, c3, c4, c5, cA0);
        if (i != 8) {
            char cA042 = a0(this.h + 9);
            char cA043 = a0(this.h + 10);
            char cA044 = a0(this.h + 11);
            char cA045 = a0(this.h + 12);
            char cA046 = a0(this.h + 13);
            if ((z5 && cA043 == 'T' && cA046 == c2 && a0(this.h + 16) == 'Z') || (z4 && ((cA043 == ' ' || cA043 == 'T') && cA046 == c2))) {
                char cA047 = a0(this.h + 14);
                cA02 = a0(this.h + 15);
                c7 = cA047;
                c9 = cA044;
                c6 = cA045;
                c8 = '0';
                c10 = '0';
            } else {
                c6 = cA042;
                c7 = cA043;
                cA02 = cA044;
                c8 = cA045;
                c9 = cA041;
                c10 = cA046;
            }
            if (!P0(c9, c6, c7, cA02, c8, c10)) {
                return false;
            }
            if (i != 17 || z5) {
                i7 = 0;
            } else {
                char cA048 = a0(this.h + 14);
                char cA049 = a0(this.h + 15);
                char cA050 = a0(this.h + 16);
                if (cA048 < '0' || cA048 > '9' || cA049 < '0' || cA049 > '9' || cA050 < '0' || cA050 > '9') {
                    return false;
                }
                i7 = ((cA048 - '0') * 100) + ((cA049 - '0') * 10) + (cA050 - '0');
            }
            i4 = (c10 - '0') + ((c8 - '0') * 10);
            i6 = ((c9 - '0') * 10) + (c6 - '0');
            i5 = i7;
            i3 = ((c7 - '0') * 10) + (cA02 - '0');
        } else {
            i3 = 0;
            i4 = 0;
            i5 = 0;
            i6 = 0;
        }
        this.n.set(11, i6);
        this.n.set(12, i3);
        this.n.set(13, i4);
        this.n.set(14, i5);
        this.f242d = i2;
        return true;
    }

    @Override // b.a.a.j.c, b.a.a.j.b
    public final String T() {
        char cA0 = a0((this.l + this.k) - 1);
        int i = this.k;
        if (cA0 == 'L' || cA0 == 'S' || cA0 == 'B' || cA0 == 'F' || cA0 == 'D') {
            i--;
        }
        return L0(this.l, i);
    }

    public final void T0(char c2, char c3, char c4, char c5, char c6, char c7, char c8, char c9) {
        Calendar calendar = Calendar.getInstance(this.o, this.p);
        this.n = calendar;
        calendar.set(1, ((c2 - '0') * 1000) + ((c3 - '0') * 100) + ((c4 - '0') * 10) + (c5 - '0'));
        this.n.set(2, (((c6 - '0') * 10) + (c7 - '0')) - 1);
        this.n.set(5, ((c8 - '0') * 10) + (c9 - '0'));
    }

    public void U0(char c2, char c3, char c4, char c5, char c6, char c7) {
        this.n.set(11, ((c2 - '0') * 10) + (c3 - '0'));
        this.n.set(12, ((c4 - '0') * 10) + (c5 - '0'));
        this.n.set(13, ((c6 - '0') * 10) + (c7 - '0'));
    }

    @Override // b.a.a.j.c, b.a.a.j.b
    public final String V() {
        return !this.m ? L0(this.l + 1, this.k) : new String(this.j, 0, this.k);
    }

    public void V0(char c2, char c3, char c4) {
        W0(c2, c3, c4, '0', '0');
    }

    public void W0(char c2, char c3, char c4, char c5, char c6) {
        int i = ((((c3 - '0') * 10) + (c4 - '0')) * 3600 * 1000) + ((((c5 - '0') * 10) + (c6 - '0')) * 60 * 1000);
        if (c2 == '-') {
            i = -i;
        }
        if (this.n.getTimeZone().getRawOffset() != i) {
            this.n.setTimeZone(new SimpleTimeZone(i, Integer.toString(i)));
        }
    }

    @Override // b.a.a.j.c
    public final String X(int i, int i2, int i3, i iVar) {
        return iVar.a(this.t, i, i2, i3);
    }

    public final void X0(boolean z) {
        int i = this.h;
        boolean z2 = false;
        int i2 = 0;
        while (i < this.t.length()) {
            char cCharAt = this.t.charAt(i);
            if (cCharAt == '\\') {
                if (i >= this.u - 1) {
                    this.g = cCharAt;
                    this.h = i;
                    throw new JSONException("illegal str, " + c());
                }
                i++;
            } else if (cCharAt == '\"') {
                z2 = !z2;
            } else if (cCharAt != '[') {
                if (cCharAt == '{' && z) {
                    int i3 = this.h + 1;
                    this.h = i3;
                    this.g = i3 < this.t.length() ? this.t.charAt(i3) : (char) 26;
                    Y0(z);
                } else if (cCharAt == ']' && !z2 && i2 - 1 == -1) {
                    int i4 = i + 1;
                    this.h = i4;
                    if (i4 == this.t.length()) {
                        this.g = (char) 26;
                        this.f242d = 20;
                        return;
                    } else {
                        this.g = this.t.charAt(this.h);
                        F(16);
                        return;
                    }
                }
            } else if (!z2) {
                i2++;
            }
            i++;
        }
        if (i != this.t.length()) {
            return;
        }
        throw new JSONException("illegal str, " + c());
    }

    @Override // b.a.a.j.c
    public final void Y(int i, char[] cArr, int i2, int i3) {
        this.t.getChars(i, i3 + i, cArr, i2);
    }

    public final void Y0(boolean z) {
        int i = this.h;
        boolean z2 = false;
        int i2 = 0;
        while (i < this.t.length()) {
            char cCharAt = this.t.charAt(i);
            if (cCharAt == '\\') {
                if (i >= this.u - 1) {
                    this.g = cCharAt;
                    this.h = i;
                    throw new JSONException("illegal str, " + c());
                }
                i++;
            } else if (cCharAt == '\"') {
                z2 = !z2;
            } else if (cCharAt != '{') {
                if (cCharAt == '}' && !z2 && i2 - 1 == -1) {
                    int i3 = i + 1;
                    this.h = i3;
                    if (i3 == this.t.length()) {
                        this.g = (char) 26;
                        this.f242d = 20;
                        return;
                    }
                    char cCharAt2 = this.t.charAt(this.h);
                    this.g = cCharAt2;
                    if (cCharAt2 == ',') {
                        this.f242d = 16;
                        int i4 = this.h + 1;
                        this.h = i4;
                        this.g = i4 < this.t.length() ? this.t.charAt(i4) : (char) 26;
                        return;
                    }
                    if (cCharAt2 == '}') {
                        this.f242d = 13;
                        next();
                        return;
                    } else if (cCharAt2 != ']') {
                        F(16);
                        return;
                    } else {
                        this.f242d = 15;
                        next();
                        return;
                    }
                }
            } else if (!z2) {
                i2++;
            }
            i++;
        }
        for (int i5 = 0; i5 < this.h; i5++) {
            if (i5 < this.t.length() && this.t.charAt(i5) == ' ') {
                i++;
            }
        }
        if (i != this.t.length()) {
            return;
        }
        throw new JSONException("illegal str, " + c());
    }

    @Override // b.a.a.j.c
    public final boolean Z(char[] cArr) {
        return N0(this.t, this.h, cArr);
    }

    @Override // b.a.a.j.c
    public final char a0(int i) {
        if (i >= this.u) {
            return (char) 26;
        }
        return this.t.charAt(i);
    }

    @Override // b.a.a.j.c
    public final void b0(int i, int i2, char[] cArr) {
        this.t.getChars(i, i2 + i, cArr, 0);
    }

    @Override // b.a.a.j.c, b.a.a.j.b
    public String c() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int i2 = 1;
        int i3 = 1;
        while (i < this.h) {
            if (this.t.charAt(i) == '\n') {
                i2++;
                i3 = 1;
            }
            i++;
            i3++;
        }
        sb.append("pos ");
        sb.append(this.h);
        sb.append(", line ");
        sb.append(i2);
        sb.append(", column ");
        sb.append(i3);
        if (this.t.length() < 65535) {
            sb.append(this.t);
        } else {
            sb.append(this.t.substring(0, 65535));
        }
        return sb.toString();
    }

    @Override // b.a.a.j.c
    public final int e0(char c2, int i) {
        return this.t.indexOf(c2, i);
    }

    @Override // b.a.a.j.c
    public boolean f0() {
        int i = this.h;
        int i2 = this.u;
        if (i != i2) {
            return this.g == 26 && i + 1 >= i2;
        }
        return true;
    }

    @Override // b.a.a.j.c, b.a.a.j.b
    public String n(i iVar) {
        int iIndexOf;
        if (!this.t.startsWith("\"@type\":\"", this.h) || (iIndexOf = this.t.indexOf(34, this.h + 9)) == -1) {
            return null;
        }
        int i = this.h + 9;
        this.h = i;
        int iCharAt = 0;
        while (i < iIndexOf) {
            iCharAt = (iCharAt * 31) + this.t.charAt(i);
            i++;
        }
        int i2 = this.h;
        String strX = X(i2, iIndexOf - i2, iCharAt, iVar);
        char cCharAt = this.t.charAt(iIndexOf + 1);
        if (cCharAt != ',' && cCharAt != ']') {
            return null;
        }
        int i3 = iIndexOf + 2;
        this.h = i3;
        this.g = this.t.charAt(i3);
        return strX;
    }

    @Override // b.a.a.j.c, b.a.a.j.b
    public final char next() {
        int i = this.h + 1;
        this.h = i;
        char cCharAt = i >= this.u ? (char) 26 : this.t.charAt(i);
        this.g = cCharAt;
        return cCharAt;
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x00f3 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0104  */
    @Override // b.a.a.j.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean q0(char[] r11) {
        /*
            Method dump skipped, instruction units count: 386
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.a.j.e.q0(char[]):boolean");
    }

    @Override // b.a.a.j.c
    public Date r0(char[] cArr) {
        char cA0;
        long j;
        char cA02;
        Date date;
        int i;
        boolean z = false;
        this.q = 0;
        int i2 = this.h;
        char c2 = this.g;
        if (!N0(this.t, i2, cArr)) {
            this.q = -2;
            return null;
        }
        int length = this.h + cArr.length;
        int i3 = length + 1;
        char cA03 = a0(length);
        if (cA03 == '\"') {
            int iE0 = e0('\"', i3);
            if (iE0 == -1) {
                throw new JSONException("unclosed str");
            }
            this.h = i3;
            if (!S0(false, iE0 - i3)) {
                this.h = i2;
                this.q = -1;
                return null;
            }
            date = this.n.getTime();
            cA02 = a0(iE0 + 1);
            this.h = i2;
            while (cA02 != ',' && cA02 != '}') {
                if (!c.g0(cA02)) {
                    this.q = -1;
                    return null;
                }
                iE0++;
                cA02 = a0(iE0 + 1);
            }
            this.h = iE0 + 1;
            this.g = cA02;
        } else {
            char c3 = '9';
            char c4 = '0';
            if (cA03 != '-' && (cA03 < '0' || cA03 > '9')) {
                this.q = -1;
                return null;
            }
            if (cA03 == '-') {
                cA03 = a0(i3);
                i3++;
                z = true;
            }
            if (cA03 < '0' || cA03 > '9') {
                cA0 = cA03;
                j = 0;
            } else {
                j = cA03 - '0';
                while (true) {
                    i = i3 + 1;
                    cA0 = a0(i3);
                    if (cA0 < c4 || cA0 > c3) {
                        break;
                    }
                    j = (j * 10) + ((long) (cA0 - '0'));
                    i3 = i;
                    c3 = '9';
                    c4 = '0';
                }
                if (cA0 == ',' || cA0 == '}') {
                    this.h = i - 1;
                }
            }
            if (j < 0) {
                this.q = -1;
                return null;
            }
            if (z) {
                j = -j;
            }
            cA02 = cA0;
            date = new Date(j);
        }
        if (cA02 == ',') {
            int i4 = this.h + 1;
            this.h = i4;
            this.g = a0(i4);
            this.q = 3;
            this.f242d = 16;
            return date;
        }
        int i5 = this.h + 1;
        this.h = i5;
        char cA04 = a0(i5);
        if (cA04 == ',') {
            this.f242d = 16;
            int i6 = this.h + 1;
            this.h = i6;
            this.g = a0(i6);
        } else if (cA04 == ']') {
            this.f242d = 15;
            int i7 = this.h + 1;
            this.h = i7;
            this.g = a0(i7);
        } else if (cA04 == '}') {
            this.f242d = 13;
            int i8 = this.h + 1;
            this.h = i8;
            this.g = a0(i8);
        } else {
            if (cA04 != 26) {
                this.h = i2;
                this.g = c2;
                this.q = -1;
                return null;
            }
            this.f242d = 20;
        }
        this.q = 4;
        return date;
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x00c0  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:57:0x00c4 -> B:52:0x00b4). Please report as a decompilation issue!!! */
    @Override // b.a.a.j.c, b.a.a.j.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public double s(char r22) {
        /*
            Method dump skipped, instruction units count: 391
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.a.j.e.s(char):double");
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0065, code lost:
    
        if (r15 != '.') goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0067, code lost:
    
        r14.q = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0069, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x006a, code lost:
    
        if (r3 >= 0) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x006c, code lost:
    
        r14.q = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x006e, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x006f, code lost:
    
        if (r6 == false) goto L89;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0071, code lost:
    
        if (r15 == '\"') goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0073, code lost:
    
        r14.q = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0075, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0076, code lost:
    
        r15 = r11 + 1;
        r4 = a0(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x007c, code lost:
    
        r11 = r15;
        r15 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0082, code lost:
    
        if (r15 == ',') goto L87;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0084, code lost:
    
        if (r15 != '}') goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x008b, code lost:
    
        if (b.a.a.j.c.g0(r15) == false) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x008d, code lost:
    
        r15 = r11 + 1;
        r4 = a0(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0094, code lost:
    
        r14.q = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0096, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0097, code lost:
    
        r11 = r11 - 1;
        r14.h = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x009c, code lost:
    
        if (r15 != ',') goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x009e, code lost:
    
        r11 = r11 + 1;
        r14.h = r11;
        r14.g = a0(r11);
        r14.q = 3;
        r14.f242d = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00ac, code lost:
    
        if (r7 == false) goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00af, code lost:
    
        return -r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00b0, code lost:
    
        if (r15 != '}') goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00b2, code lost:
    
        r14.h = r11;
        r11 = r11 + 1;
        r14.h = r11;
        r15 = a0(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00bb, code lost:
    
        if (r15 != ',') goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00bd, code lost:
    
        r14.f242d = 16;
        r15 = r14.h + 1;
        r14.h = r15;
        r14.g = a0(r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00cd, code lost:
    
        if (r15 != ']') goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00cf, code lost:
    
        r14.f242d = 15;
        r15 = r14.h + 1;
        r14.h = r15;
        r14.g = a0(r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00df, code lost:
    
        if (r15 != '}') goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00e1, code lost:
    
        r14.f242d = 13;
        r15 = r14.h + 1;
        r14.h = r15;
        r14.g = a0(r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00f3, code lost:
    
        if (r15 != 26) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00f5, code lost:
    
        r14.f242d = 20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x00f9, code lost:
    
        r14.q = 4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0101, code lost:
    
        if (b.a.a.j.c.g0(r15) == false) goto L93;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0103, code lost:
    
        r15 = r14.h + 1;
        r14.h = r15;
        r15 = a0(r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x010d, code lost:
    
        r14.h = r1;
        r14.g = r2;
        r14.q = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0113, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0114, code lost:
    
        if (r7 == false) goto L96;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0117, code lost:
    
        return -r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x007c, code lost:
    
        r11 = r15;
        r15 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:?, code lost:
    
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:?, code lost:
    
        return r3;
     */
    @Override // b.a.a.j.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int x0(char[] r15) {
        /*
            Method dump skipped, instruction units count: 283
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.a.j.e.x0(char[]):int");
    }

    @Override // b.a.a.j.c
    public long z0(char[] cArr) {
        boolean z;
        int i;
        char cA0;
        this.q = 0;
        int i2 = this.h;
        char c2 = this.g;
        if (!N0(this.t, i2, cArr)) {
            this.q = -2;
            return 0L;
        }
        int length = this.h + cArr.length;
        int i3 = length + 1;
        char cA02 = a0(length);
        boolean z2 = cA02 == '\"';
        if (z2) {
            cA02 = a0(i3);
            i3++;
        }
        if (cA02 == '-') {
            z = true;
            cA02 = a0(i3);
            i3++;
        } else {
            z = false;
        }
        if (cA02 >= '0') {
            char c3 = '9';
            if (cA02 <= '9') {
                long j = cA02 - '0';
                while (true) {
                    i = i3 + 1;
                    cA0 = a0(i3);
                    if (cA0 < '0' || cA0 > c3) {
                        break;
                    }
                    j = (j * 10) + ((long) (cA0 - '0'));
                    i3 = i;
                    c3 = '9';
                }
                if (cA0 == '.') {
                    this.q = -1;
                    return 0L;
                }
                if (z2) {
                    if (cA0 != '\"') {
                        this.q = -1;
                        return 0L;
                    }
                    int i4 = i + 1;
                    char cA03 = a0(i);
                    i = i4;
                    cA0 = cA03;
                }
                if (cA0 == ',' || cA0 == '}') {
                    this.h = i - 1;
                }
                if (!(j >= 0 || (j == Long.MIN_VALUE && z))) {
                    this.h = i2;
                    this.g = c2;
                    this.q = -1;
                    return 0L;
                }
                while (cA0 != ',') {
                    if (cA0 == '}') {
                        int i5 = this.h + 1;
                        this.h = i5;
                        char cA04 = a0(i5);
                        while (true) {
                            if (cA04 == ',') {
                                this.f242d = 16;
                                int i6 = this.h + 1;
                                this.h = i6;
                                this.g = a0(i6);
                                break;
                            }
                            if (cA04 == ']') {
                                this.f242d = 15;
                                int i7 = this.h + 1;
                                this.h = i7;
                                this.g = a0(i7);
                                break;
                            }
                            if (cA04 == '}') {
                                this.f242d = 13;
                                int i8 = this.h + 1;
                                this.h = i8;
                                this.g = a0(i8);
                                break;
                            }
                            if (cA04 == 26) {
                                this.f242d = 20;
                                break;
                            }
                            if (!c.g0(cA04)) {
                                this.h = i2;
                                this.g = c2;
                                this.q = -1;
                                return 0L;
                            }
                            int i9 = this.h + 1;
                            this.h = i9;
                            cA04 = a0(i9);
                        }
                        this.q = 4;
                        return z ? -j : j;
                    }
                    if (!c.g0(cA0)) {
                        this.q = -1;
                        return 0L;
                    }
                    this.h = i;
                    int i10 = i + 1;
                    char cA05 = a0(i);
                    i = i10;
                    cA0 = cA05;
                }
                int i11 = this.h + 1;
                this.h = i11;
                this.g = a0(i11);
                this.q = 3;
                this.f242d = 16;
                return z ? -j : j;
            }
        }
        this.h = i2;
        this.g = c2;
        this.q = -1;
        return 0L;
    }

    public e(String str, int i) {
        super(i);
        this.t = str;
        this.u = str.length();
        this.h = -1;
        next();
        if (this.g == 65279) {
            next();
        }
    }

    public e(char[] cArr, int i, int i2) {
        this(new String(cArr, 0, i), i2);
    }
}
