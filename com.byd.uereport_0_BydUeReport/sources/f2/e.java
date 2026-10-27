package f2;

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

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final String f4899w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final int f4900x;

    public e(String str) {
        this(str, c2.a.DEFAULT_PARSER_FEATURE);
    }

    public static boolean g1(String str, int i8, char[] cArr) {
        int length = cArr.length;
        if (length + i8 > str.length()) {
            return false;
        }
        for (int i9 = 0; i9 < length; i9++) {
            if (cArr[i9] != str.charAt(i8 + i9)) {
                return false;
            }
        }
        return true;
    }

    public static boolean h1(char c8, char c9, char c10, char c11, char c12, char c13, int i8, int i9) {
        if (c8 >= '0' && c8 <= '9' && c9 >= '0' && c9 <= '9' && c10 >= '0' && c10 <= '9' && c11 >= '0' && c11 <= '9') {
            if (c12 == '0') {
                if (c13 < '1' || c13 > '9') {
                    return false;
                }
            } else if (c12 != '1' || (c13 != '0' && c13 != '1' && c13 != '2')) {
                return false;
            }
            if (i8 == 48) {
                return i9 >= 49 && i9 <= 57;
            }
            if (i8 != 49 && i8 != 50) {
                return i8 == 51 && (i9 == 48 || i9 == 49);
            }
            if (i9 >= 48 && i9 <= 57) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x00c0  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:57:0x00c4 -> B:52:0x00b4). Please report as a decompilation issue!!! */
    @Override // f2.c, f2.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public double B(char r22) {
        /*
            Method dump skipped, instruction units count: 391
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: f2.e.B(char):double");
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x00f3 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0104  */
    @Override // f2.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean J0(char[] r11) {
        /*
            Method dump skipped, instruction units count: 386
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: f2.e.J0(char[]):boolean");
    }

    @Override // f2.c
    public Date K0(char[] cArr) {
        char cT0;
        long j8;
        char cT02;
        Date date;
        int i8;
        boolean z7 = false;
        this.f4892t = 0;
        int i9 = this.f4883k;
        char c8 = this.f4882j;
        if (!g1(this.f4899w, i9, cArr)) {
            this.f4892t = -2;
            return null;
        }
        int length = this.f4883k + cArr.length;
        int i10 = length + 1;
        char cT03 = t0(length);
        if (cT03 == '\"') {
            int iX0 = x0('\"', i10);
            if (iX0 == -1) {
                throw new JSONException("unclosed str");
            }
            this.f4883k = i10;
            if (!l1(false, iX0 - i10)) {
                this.f4883k = i9;
                this.f4892t = -1;
                return null;
            }
            date = this.f4889q.getTime();
            cT02 = t0(iX0 + 1);
            this.f4883k = i9;
            while (cT02 != ',' && cT02 != '}') {
                if (!c.z0(cT02)) {
                    this.f4892t = -1;
                    return null;
                }
                iX0++;
                cT02 = t0(iX0 + 1);
            }
            this.f4883k = iX0 + 1;
            this.f4882j = cT02;
        } else {
            char c9 = '9';
            char c10 = '0';
            if (cT03 != '-' && (cT03 < '0' || cT03 > '9')) {
                this.f4892t = -1;
                return null;
            }
            if (cT03 == '-') {
                cT03 = t0(i10);
                i10++;
                z7 = true;
            }
            if (cT03 < '0' || cT03 > '9') {
                cT0 = cT03;
                j8 = 0;
            } else {
                j8 = cT03 - '0';
                while (true) {
                    i8 = i10 + 1;
                    cT0 = t0(i10);
                    if (cT0 < c10 || cT0 > c9) {
                        break;
                    }
                    j8 = (j8 * 10) + ((long) (cT0 - '0'));
                    i10 = i8;
                    c9 = '9';
                    c10 = '0';
                }
                if (cT0 == ',' || cT0 == '}') {
                    this.f4883k = i8 - 1;
                }
            }
            if (j8 < 0) {
                this.f4892t = -1;
                return null;
            }
            if (z7) {
                j8 = -j8;
            }
            cT02 = cT0;
            date = new Date(j8);
        }
        if (cT02 == ',') {
            int i11 = this.f4883k + 1;
            this.f4883k = i11;
            this.f4882j = t0(i11);
            this.f4892t = 3;
            this.f4879g = 16;
            return date;
        }
        int i12 = this.f4883k + 1;
        this.f4883k = i12;
        char cT04 = t0(i12);
        if (cT04 == ',') {
            this.f4879g = 16;
            int i13 = this.f4883k + 1;
            this.f4883k = i13;
            this.f4882j = t0(i13);
        } else if (cT04 == ']') {
            this.f4879g = 15;
            int i14 = this.f4883k + 1;
            this.f4883k = i14;
            this.f4882j = t0(i14);
        } else if (cT04 == '}') {
            this.f4879g = 13;
            int i15 = this.f4883k + 1;
            this.f4883k = i15;
            this.f4882j = t0(i15);
        } else {
            if (cT04 != 26) {
                this.f4883k = i9;
                this.f4882j = c8;
                this.f4892t = -1;
                return null;
            }
            this.f4879g = 20;
        }
        this.f4892t = 4;
        return date;
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0065, code lost:
    
        if (r15 != '.') goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0067, code lost:
    
        r14.f4892t = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0069, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x006a, code lost:
    
        if (r3 >= 0) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x006c, code lost:
    
        r14.f4892t = -1;
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
    
        r14.f4892t = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0075, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0076, code lost:
    
        r15 = r11 + 1;
        r4 = t0(r11);
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
    
        if (f2.c.z0(r15) == false) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x008d, code lost:
    
        r15 = r11 + 1;
        r4 = t0(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0094, code lost:
    
        r14.f4892t = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0096, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0097, code lost:
    
        r11 = r11 - 1;
        r14.f4883k = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x009c, code lost:
    
        if (r15 != ',') goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x009e, code lost:
    
        r11 = r11 + 1;
        r14.f4883k = r11;
        r14.f4882j = t0(r11);
        r14.f4892t = 3;
        r14.f4879g = 16;
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
    
        r14.f4883k = r11;
        r11 = r11 + 1;
        r14.f4883k = r11;
        r15 = t0(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00bb, code lost:
    
        if (r15 != ',') goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00bd, code lost:
    
        r14.f4879g = 16;
        r15 = r14.f4883k + 1;
        r14.f4883k = r15;
        r14.f4882j = t0(r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00cd, code lost:
    
        if (r15 != ']') goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00cf, code lost:
    
        r14.f4879g = 15;
        r15 = r14.f4883k + 1;
        r14.f4883k = r15;
        r14.f4882j = t0(r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00df, code lost:
    
        if (r15 != '}') goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00e1, code lost:
    
        r14.f4879g = 13;
        r15 = r14.f4883k + 1;
        r14.f4883k = r15;
        r14.f4882j = t0(r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00f3, code lost:
    
        if (r15 != 26) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00f5, code lost:
    
        r14.f4879g = 20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x00f9, code lost:
    
        r14.f4892t = 4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0101, code lost:
    
        if (f2.c.z0(r15) == false) goto L93;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0103, code lost:
    
        r15 = r14.f4883k + 1;
        r14.f4883k = r15;
        r15 = t0(r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x010d, code lost:
    
        r14.f4883k = r1;
        r14.f4882j = r2;
        r14.f4892t = -1;
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
    @Override // f2.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int Q0(char[] r15) {
        /*
            Method dump skipped, instruction units count: 283
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: f2.e.Q0(char[]):int");
    }

    @Override // f2.c
    public long S0(char[] cArr) {
        boolean z7;
        int i8;
        char cT0;
        this.f4892t = 0;
        int i9 = this.f4883k;
        char c8 = this.f4882j;
        if (!g1(this.f4899w, i9, cArr)) {
            this.f4892t = -2;
            return 0L;
        }
        int length = this.f4883k + cArr.length;
        int i10 = length + 1;
        char cT02 = t0(length);
        boolean z8 = cT02 == '\"';
        if (z8) {
            cT02 = t0(i10);
            i10++;
        }
        if (cT02 == '-') {
            z7 = true;
            cT02 = t0(i10);
            i10++;
        } else {
            z7 = false;
        }
        if (cT02 >= '0') {
            char c9 = '9';
            if (cT02 <= '9') {
                long j8 = cT02 - '0';
                while (true) {
                    i8 = i10 + 1;
                    cT0 = t0(i10);
                    if (cT0 < '0' || cT0 > c9) {
                        break;
                    }
                    j8 = (j8 * 10) + ((long) (cT0 - '0'));
                    i10 = i8;
                    c9 = '9';
                }
                if (cT0 == '.') {
                    this.f4892t = -1;
                    return 0L;
                }
                if (z8) {
                    if (cT0 != '\"') {
                        this.f4892t = -1;
                        return 0L;
                    }
                    int i11 = i8 + 1;
                    char cT03 = t0(i8);
                    i8 = i11;
                    cT0 = cT03;
                }
                if (cT0 == ',' || cT0 == '}') {
                    this.f4883k = i8 - 1;
                }
                if (!(j8 >= 0 || (j8 == Long.MIN_VALUE && z7))) {
                    this.f4883k = i9;
                    this.f4882j = c8;
                    this.f4892t = -1;
                    return 0L;
                }
                while (cT0 != ',') {
                    if (cT0 == '}') {
                        int i12 = this.f4883k + 1;
                        this.f4883k = i12;
                        char cT04 = t0(i12);
                        while (true) {
                            if (cT04 == ',') {
                                this.f4879g = 16;
                                int i13 = this.f4883k + 1;
                                this.f4883k = i13;
                                this.f4882j = t0(i13);
                                break;
                            }
                            if (cT04 == ']') {
                                this.f4879g = 15;
                                int i14 = this.f4883k + 1;
                                this.f4883k = i14;
                                this.f4882j = t0(i14);
                                break;
                            }
                            if (cT04 == '}') {
                                this.f4879g = 13;
                                int i15 = this.f4883k + 1;
                                this.f4883k = i15;
                                this.f4882j = t0(i15);
                                break;
                            }
                            if (cT04 == 26) {
                                this.f4879g = 20;
                                break;
                            }
                            if (!c.z0(cT04)) {
                                this.f4883k = i9;
                                this.f4882j = c8;
                                this.f4892t = -1;
                                return 0L;
                            }
                            int i16 = this.f4883k + 1;
                            this.f4883k = i16;
                            cT04 = t0(i16);
                        }
                        this.f4892t = 4;
                        return z7 ? -j8 : j8;
                    }
                    if (!c.z0(cT0)) {
                        this.f4892t = -1;
                        return 0L;
                    }
                    this.f4883k = i8;
                    int i17 = i8 + 1;
                    char cT05 = t0(i8);
                    i8 = i17;
                    cT0 = cT05;
                }
                int i18 = this.f4883k + 1;
                this.f4883k = i18;
                this.f4882j = t0(i18);
                this.f4892t = 3;
                this.f4879g = 16;
                return z7 ? -j8 : j8;
            }
        }
        this.f4883k = i9;
        this.f4882j = c8;
        this.f4892t = -1;
        return 0L;
    }

    @Override // f2.c, f2.b
    public long T(char c8) {
        int i8;
        char cT0;
        boolean z7 = false;
        this.f4892t = 0;
        int i9 = this.f4883k;
        int i10 = i9 + 1;
        char cT02 = t0(i9);
        boolean z8 = cT02 == '\"';
        if (z8) {
            int i11 = i10 + 1;
            char cT03 = t0(i10);
            i10 = i11;
            cT02 = cT03;
        }
        boolean z9 = cT02 == '-';
        if (z9) {
            int i12 = i10 + 1;
            char cT04 = t0(i10);
            i10 = i12;
            cT02 = cT04;
        }
        char c9 = '0';
        if (cT02 >= '0' && cT02 <= '9') {
            long j8 = cT02 - '0';
            while (true) {
                i8 = i10 + 1;
                cT0 = t0(i10);
                if (cT0 < c9 || cT0 > '9') {
                    break;
                }
                j8 = (j8 * 10) + ((long) (cT0 - '0'));
                i10 = i8;
                c9 = '0';
            }
            if (cT0 == '.') {
                this.f4892t = -1;
                return 0L;
            }
            if (z8) {
                if (cT0 != '\"') {
                    this.f4892t = -1;
                    return 0L;
                }
                cT0 = t0(i8);
                i8++;
            }
            if (j8 >= 0 || (j8 == Long.MIN_VALUE && z9)) {
                z7 = true;
            }
            if (!z7) {
                this.f4892t = -1;
                return 0L;
            }
            while (cT0 != c8) {
                if (!c.z0(cT0)) {
                    this.f4892t = -1;
                    return j8;
                }
                cT0 = t0(i8);
                i8++;
            }
            this.f4883k = i8;
            this.f4882j = t0(i8);
            this.f4892t = 3;
            this.f4879g = 16;
            return z9 ? -j8 : j8;
        }
        if (cT02 == 'n') {
            int i13 = i10 + 1;
            if (t0(i10) == 'u') {
                int i14 = i13 + 1;
                if (t0(i13) == 'l') {
                    int i15 = i14 + 1;
                    if (t0(i14) == 'l') {
                        this.f4892t = 5;
                        int i16 = i15 + 1;
                        char cT05 = t0(i15);
                        if (z8 && cT05 == '\"') {
                            int i17 = i16 + 1;
                            char cT06 = t0(i16);
                            i16 = i17;
                            cT05 = cT06;
                        }
                        while (cT05 != ',') {
                            if (cT05 == ']') {
                                this.f4883k = i16;
                                this.f4882j = t0(i16);
                                this.f4892t = 5;
                                this.f4879g = 15;
                                return 0L;
                            }
                            if (!c.z0(cT05)) {
                                this.f4892t = -1;
                                return 0L;
                            }
                            int i18 = i16 + 1;
                            char cT07 = t0(i16);
                            i16 = i18;
                            cT05 = cT07;
                        }
                        this.f4883k = i16;
                        this.f4882j = t0(i16);
                        this.f4892t = 5;
                        this.f4879g = 16;
                        return 0L;
                    }
                }
            }
        }
        this.f4892t = -1;
        return 0L;
    }

    @Override // f2.c
    public String T0(char[] cArr) {
        this.f4892t = 0;
        int i8 = this.f4883k;
        char c8 = this.f4882j;
        while (!g1(this.f4899w, this.f4883k, cArr)) {
            if (!c.z0(this.f4882j)) {
                this.f4892t = -2;
                return d1();
            }
            next();
            while (c.z0(this.f4882j)) {
                next();
            }
        }
        int length = this.f4883k + cArr.length;
        int i9 = length + 1;
        char cT0 = t0(length);
        int i10 = 0;
        if (cT0 != '\"') {
            while (c.z0(cT0)) {
                i10++;
                int i11 = i9 + 1;
                char cT02 = t0(i9);
                i9 = i11;
                cT0 = cT02;
            }
            if (cT0 != '\"') {
                this.f4892t = -1;
                return d1();
            }
        }
        int iX0 = x0('\"', i9);
        if (iX0 == -1) {
            throw new JSONException("unclosed str");
        }
        String strE1 = e1(i9, iX0 - i9);
        if (strE1.indexOf(92) != -1) {
            while (true) {
                int i12 = 0;
                for (int i13 = iX0 - 1; i13 >= 0 && t0(i13) == '\\'; i13--) {
                    i12++;
                }
                if (i12 % 2 == 0) {
                    break;
                }
                iX0 = x0('\"', iX0 + 1);
            }
            int i14 = this.f4883k;
            int length2 = iX0 - (((cArr.length + i14) + 1) + i10);
            strE1 = c.F0(f1(i14 + cArr.length + 1 + i10, length2), length2);
        }
        if ((this.f4881i & Feature.TrimStringFieldValue.mask) != 0) {
            strE1 = strE1.trim();
        }
        char cT03 = t0(iX0 + 1);
        while (cT03 != ',' && cT03 != '}') {
            if (!c.z0(cT03)) {
                this.f4892t = -1;
                return d1();
            }
            iX0++;
            cT03 = t0(iX0 + 1);
        }
        int i15 = iX0 + 1;
        this.f4883k = i15;
        this.f4882j = cT03;
        if (cT03 == ',') {
            int i16 = i15 + 1;
            this.f4883k = i16;
            this.f4882j = t0(i16);
            this.f4892t = 3;
            return strE1;
        }
        int i17 = i15 + 1;
        this.f4883k = i17;
        char cT04 = t0(i17);
        if (cT04 == ',') {
            this.f4879g = 16;
            int i18 = this.f4883k + 1;
            this.f4883k = i18;
            this.f4882j = t0(i18);
        } else if (cT04 == ']') {
            this.f4879g = 15;
            int i19 = this.f4883k + 1;
            this.f4883k = i19;
            this.f4882j = t0(i19);
        } else if (cT04 == '}') {
            this.f4879g = 13;
            int i20 = this.f4883k + 1;
            this.f4883k = i20;
            this.f4882j = t0(i20);
        } else {
            if (cT04 != 26) {
                this.f4883k = i8;
                this.f4882j = c8;
                this.f4892t = -1;
                return d1();
            }
            this.f4879g = 20;
        }
        this.f4892t = 4;
        return strE1;
    }

    @Override // f2.c, f2.b
    public final BigDecimal Z() {
        char cT0 = t0((this.f4887o + this.f4886n) - 1);
        int i8 = this.f4886n;
        if (cT0 == 'L' || cT0 == 'S' || cT0 == 'B' || cT0 == 'F' || cT0 == 'D') {
            i8--;
        }
        if (i8 > 65535) {
            throw new JSONException("decimal overflow");
        }
        int i9 = this.f4887o;
        char[] cArr = this.f4885m;
        if (i8 < cArr.length) {
            this.f4899w.getChars(i9, i9 + i8, cArr, 0);
            return new BigDecimal(this.f4885m, 0, i8, MathContext.UNLIMITED);
        }
        char[] cArr2 = new char[i8];
        this.f4899w.getChars(i9, i9 + i8, cArr2, 0);
        return new BigDecimal(cArr2, 0, i8, MathContext.UNLIMITED);
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0080, code lost:
    
        if (r3 != '.') goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0082, code lost:
    
        r16.f4892t = -1;
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
    
        r16.f4892t = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x008b, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x008c, code lost:
    
        r3 = t0(r13);
        r13 = r13 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0093, code lost:
    
        if (r4 >= 0) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0095, code lost:
    
        r16.f4892t = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0097, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x009a, code lost:
    
        if (r3 != r17) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x009c, code lost:
    
        r16.f4883k = r13;
        r16.f4882j = t0(r13);
        r16.f4892t = 3;
        r16.f4879g = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00a9, code lost:
    
        if (r8 == false) goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00ac, code lost:
    
        return -r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00b1, code lost:
    
        if (f2.c.z0(r3) == false) goto L89;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00b3, code lost:
    
        r3 = t0(r13);
        r13 = r13 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00bb, code lost:
    
        r16.f4892t = -1;
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
    @Override // f2.c, f2.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int a0(char r17) {
        /*
            Method dump skipped, instruction units count: 302
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: f2.e.a0(char):int");
    }

    @Override // f2.b
    public byte[] e0() {
        if (this.f4879g != 26) {
            return !this.f4888p ? l2.e.d(this.f4899w, this.f4887o + 1, this.f4886n) : l2.e.c(new String(this.f4885m, 0, this.f4886n));
        }
        int i8 = this.f4887o + 1;
        int i9 = this.f4886n;
        if (i9 % 2 != 0) {
            throw new JSONException("illegal state. " + i9);
        }
        int i10 = i9 / 2;
        byte[] bArr = new byte[i10];
        for (int i11 = 0; i11 < i10; i11++) {
            int i12 = (i11 * 2) + i8;
            char cCharAt = this.f4899w.charAt(i12);
            char cCharAt2 = this.f4899w.charAt(i12 + 1);
            char c8 = '0';
            int i13 = cCharAt - (cCharAt <= '9' ? '0' : '7');
            if (cCharAt2 > '9') {
                c8 = '7';
            }
            bArr[i11] = (byte) ((i13 << 4) | (cCharAt2 - c8));
        }
        return bArr;
    }

    @Override // f2.c
    public final String e1(int i8, int i9) {
        if (!l2.b.f6168b) {
            return this.f4899w.substring(i8, i9 + i8);
        }
        char[] cArr = this.f4885m;
        if (i9 < cArr.length) {
            this.f4899w.getChars(i8, i8 + i9, cArr, 0);
            return new String(this.f4885m, 0, i9);
        }
        char[] cArr2 = new char[i9];
        this.f4899w.getChars(i8, i9 + i8, cArr2, 0);
        return new String(cArr2);
    }

    @Override // f2.c
    public final char[] f1(int i8, int i9) {
        if (l2.b.f6168b) {
            char[] cArr = this.f4885m;
            if (i9 < cArr.length) {
                this.f4899w.getChars(i8, i9 + i8, cArr, 0);
                return this.f4885m;
            }
        }
        char[] cArr2 = new char[i9];
        this.f4899w.getChars(i8, i9 + i8, cArr2, 0);
        return cArr2;
    }

    @Override // f2.c, f2.b
    public String g() {
        StringBuilder sb = new StringBuilder();
        int i8 = 0;
        int i9 = 1;
        int i10 = 1;
        while (i8 < this.f4883k) {
            if (this.f4899w.charAt(i8) == '\n') {
                i9++;
                i10 = 1;
            }
            i8++;
            i10++;
        }
        sb.append("pos ");
        sb.append(this.f4883k);
        sb.append(", line ");
        sb.append(i9);
        sb.append(", column ");
        sb.append(i10);
        if (this.f4899w.length() < 65535) {
            sb.append(this.f4899w);
        } else {
            sb.append(this.f4899w.substring(0, 65535));
        }
        return sb.toString();
    }

    public final boolean i1(char c8, char c9, char c10, char c11, char c12, char c13) {
        if (c8 == '0') {
            if (c9 < '0' || c9 > '9') {
                return false;
            }
        } else {
            if (c8 != '1') {
                if (c8 == '2' && c9 >= '0' && c9 <= '4') {
                }
                return false;
            }
            if (c9 < '0' || c9 > '9') {
                return false;
            }
        }
        if (c10 < '0' || c10 > '5') {
            if (c10 != '6' || c11 != '0') {
                return false;
            }
        } else if (c11 < '0' || c11 > '9') {
            return false;
        }
        return (c12 < '0' || c12 > '5') ? c12 == '6' && c13 == '0' : c13 >= '0' && c13 <= '9';
    }

    public boolean j1() {
        return k1(true);
    }

    public boolean k1(boolean z7) {
        return l1(z7, this.f4900x - this.f4883k);
    }

    @Override // f2.c, f2.b
    public final String l0() {
        char cT0 = t0((this.f4887o + this.f4886n) - 1);
        int i8 = this.f4886n;
        if (cT0 == 'L' || cT0 == 'S' || cT0 == 'B' || cT0 == 'F' || cT0 == 'D') {
            i8--;
        }
        return e1(this.f4887o, i8);
    }

    public final boolean l1(boolean z7, int i8) {
        int i9;
        boolean z8;
        char c8;
        char cT0;
        char c9;
        char c10;
        char c11;
        int i10;
        int i11;
        int i12;
        int i13;
        char c12;
        char c13;
        char cT02;
        char c14;
        char c15;
        char c16;
        int i14;
        char c17;
        char c18;
        char c19;
        char c20;
        int i15;
        char c21;
        char c22;
        char c23;
        int i16;
        char cT03;
        char cT04;
        char cT05;
        int i17;
        char cT06;
        char cT07;
        char cT08;
        if (i8 < 8) {
            return false;
        }
        char cT09 = t0(this.f4883k);
        char cT010 = t0(this.f4883k + 1);
        char cT011 = t0(this.f4883k + 2);
        int i18 = 3;
        char cT012 = t0(this.f4883k + 3);
        char cT013 = t0(this.f4883k + 4);
        int i19 = 5;
        char cT014 = t0(this.f4883k + 5);
        char cT015 = t0(this.f4883k + 6);
        char cT016 = t0(this.f4883k + 7);
        if (!z7) {
            if (i8 > 13) {
                char cT017 = t0((this.f4883k + i8) - 1);
                char cT018 = t0((this.f4883k + i8) - 2);
                if (cT09 == '/' && cT010 == 'D' && cT011 == 'a' && cT012 == 't' && cT013 == 'e' && cT014 == '(' && cT017 == '/' && cT018 == ')') {
                    int i20 = -1;
                    for (int i21 = 6; i21 < i8; i21++) {
                        char cT019 = t0(this.f4883k + i21);
                        if (cT019 != '+') {
                            if (cT019 < '0' || cT019 > '9') {
                                break;
                            }
                        } else {
                            i20 = i21;
                        }
                    }
                    if (i20 == -1) {
                        return false;
                    }
                    int i22 = this.f4883k;
                    int i23 = i22 + 6;
                    long j8 = Long.parseLong(e1(i23, (i22 + i20) - i23));
                    Calendar calendar = Calendar.getInstance(this.f4890r, this.f4891s);
                    this.f4889q = calendar;
                    calendar.setTimeInMillis(j8);
                    this.f4879g = 5;
                    return true;
                }
            }
            i19 = 5;
        }
        if (i8 == 8 || i8 == 14) {
            i9 = i19;
            z8 = false;
            c8 = ':';
        } else {
            if (!(i8 == 16 && ((cT08 = t0(this.f4883k + 10)) == 'T' || cT08 == ' ')) && (i8 != 17 || t0(this.f4883k + 6) == '-')) {
                if (i8 < 9) {
                    return false;
                }
                char cT020 = t0(this.f4883k + 8);
                char cT021 = t0(this.f4883k + 9);
                if ((cT013 == '-' && cT016 == '-') || (cT013 == '/' && cT016 == '/')) {
                    if (cT021 == ' ') {
                        c17 = cT014;
                        c18 = cT012;
                        c19 = cT09;
                        c20 = cT010;
                        c21 = '0';
                        i15 = 9;
                        cT010 = cT020;
                        c23 = cT015;
                        c22 = cT011;
                    } else {
                        c18 = cT012;
                        c19 = cT09;
                        i15 = 10;
                        c21 = cT020;
                        c23 = cT015;
                        c22 = cT011;
                        c17 = cT014;
                        c20 = cT010;
                        cT010 = cT021;
                    }
                } else if (cT013 == '-' && cT015 == '-') {
                    if (cT020 == ' ') {
                        c22 = cT011;
                        c23 = cT014;
                        c19 = cT09;
                        c20 = cT010;
                        c17 = '0';
                        i15 = 8;
                        cT010 = cT016;
                        c18 = cT012;
                        c21 = '0';
                    } else {
                        c22 = cT011;
                        c19 = cT09;
                        c17 = '0';
                        i15 = 9;
                        c23 = cT014;
                        c20 = cT010;
                        cT010 = cT020;
                        c21 = cT016;
                        c18 = cT012;
                    }
                } else if ((cT011 == '.' && cT014 == '.') || (cT011 == '-' && cT014 == '-')) {
                    c20 = cT016;
                    c18 = cT021;
                    c17 = cT012;
                    c21 = cT09;
                    i15 = 10;
                    c23 = cT013;
                    c19 = cT015;
                    c22 = cT020;
                } else if (cT020 == 'T') {
                    c17 = cT013;
                    c23 = cT014;
                    c19 = cT09;
                    c20 = cT010;
                    i15 = 8;
                    cT010 = cT016;
                    c18 = cT012;
                    c21 = cT015;
                    c22 = cT011;
                } else {
                    if (cT013 != 24180 && cT013 != 45380) {
                        return false;
                    }
                    if (cT016 != 26376 && cT016 != 50900) {
                        if (cT015 != 26376 && cT015 != 50900) {
                            return false;
                        }
                        if (cT020 == 26085 || cT020 == 51068) {
                            c22 = cT011;
                            c23 = cT014;
                            c19 = cT09;
                            c20 = cT010;
                            i15 = 10;
                            c17 = '0';
                            cT010 = cT016;
                            c18 = cT012;
                            c21 = '0';
                        } else {
                            if (cT021 != 26085 && cT021 != 51068) {
                                return false;
                            }
                            c22 = cT011;
                            c19 = cT09;
                            i15 = 10;
                            c17 = '0';
                            c23 = cT014;
                            c20 = cT010;
                            cT010 = cT020;
                            c21 = cT016;
                            c18 = cT012;
                        }
                    } else if (cT021 == 26085 || cT021 == 51068) {
                        c17 = cT014;
                        c18 = cT012;
                        c19 = cT09;
                        c20 = cT010;
                        i15 = 10;
                        c21 = '0';
                        cT010 = cT020;
                        c23 = cT015;
                        c22 = cT011;
                    } else {
                        if (t0(this.f4883k + 10) != 26085 && t0(this.f4883k + 10) != 51068) {
                            return false;
                        }
                        c18 = cT012;
                        c19 = cT09;
                        i15 = 11;
                        c21 = cT020;
                        c23 = cT015;
                        c22 = cT011;
                        c17 = cT014;
                        c20 = cT010;
                        cT010 = cT021;
                    }
                }
                if (!h1(c19, c20, c22, c18, c17, c23, c21, cT010)) {
                    return false;
                }
                m1(c19, c20, c22, c18, c17, c23, c21, cT010);
                char cT022 = t0(this.f4883k + i15);
                char c24 = 'T';
                if (cT022 == 'T') {
                    if (i8 == 16 && i15 == 8 && t0(this.f4883k + 15) == 'Z') {
                        char cT023 = t0(this.f4883k + i15 + 1);
                        char cT024 = t0(this.f4883k + i15 + 2);
                        char cT025 = t0(this.f4883k + i15 + 3);
                        char cT026 = t0(this.f4883k + i15 + 4);
                        char cT027 = t0(this.f4883k + i15 + 5);
                        char cT028 = t0(this.f4883k + i15 + 6);
                        if (!i1(cT023, cT024, cT025, cT026, cT027, cT028)) {
                            return false;
                        }
                        n1(cT023, cT024, cT025, cT026, cT027, cT028);
                        this.f4889q.set(14, 0);
                        if (this.f4889q.getTimeZone().getRawOffset() != 0) {
                            String[] availableIDs = TimeZone.getAvailableIDs(0);
                            if (availableIDs.length > 0) {
                                this.f4889q.setTimeZone(TimeZone.getTimeZone(availableIDs[0]));
                            }
                        }
                        this.f4879g = 5;
                        return true;
                    }
                    c24 = 'T';
                }
                if (cT022 != c24 && (cT022 != ' ' || z7)) {
                    if (cT022 == '\"' || cT022 == 26 || cT022 == 26085 || cT022 == 51068) {
                        this.f4889q.set(11, 0);
                        this.f4889q.set(12, 0);
                        this.f4889q.set(13, 0);
                        this.f4889q.set(14, 0);
                        int i24 = this.f4883k + i15;
                        this.f4883k = i24;
                        this.f4882j = t0(i24);
                        this.f4879g = 5;
                        return true;
                    }
                    if ((cT022 != '+' && cT022 != '-') || this.f4900x != i15 + 6 || t0(this.f4883k + i15 + 3) != ':' || t0(this.f4883k + i15 + 4) != '0' || t0(this.f4883k + i15 + 5) != '0') {
                        return false;
                    }
                    n1('0', '0', '0', '0', '0', '0');
                    this.f4889q.set(14, 0);
                    o1(cT022, t0(this.f4883k + i15 + 1), t0(this.f4883k + i15 + 2));
                    return true;
                }
                if (i8 < i15 + 9 || t0(this.f4883k + i15 + 3) != ':' || t0(this.f4883k + i15 + 6) != ':') {
                    return false;
                }
                char cT029 = t0(this.f4883k + i15 + 1);
                char cT030 = t0(this.f4883k + i15 + 2);
                char cT031 = t0(this.f4883k + i15 + 4);
                char cT032 = t0(this.f4883k + i15 + 5);
                char cT033 = t0(this.f4883k + i15 + 7);
                char cT034 = t0(this.f4883k + i15 + 8);
                if (!i1(cT029, cT030, cT031, cT032, cT033, cT034)) {
                    return false;
                }
                n1(cT029, cT030, cT031, cT032, cT033, cT034);
                int i25 = -1;
                if (t0(this.f4883k + i15 + 9) == '.') {
                    int i26 = i15 + 11;
                    if (i8 < i26 || (cT05 = t0(this.f4883k + i15 + 10)) < '0' || cT05 > '9') {
                        return false;
                    }
                    int i27 = cT05 - '0';
                    if (i8 <= i26 || (cT07 = t0(this.f4883k + i15 + 11)) < '0' || cT07 > '9') {
                        i17 = 1;
                    } else {
                        i27 = (i27 * 10) + (cT07 - '0');
                        i17 = 2;
                    }
                    if (i17 != 2 || (cT06 = t0(this.f4883k + i15 + 12)) < '0' || cT06 > '9') {
                        i16 = i27;
                        i25 = i17;
                    } else {
                        i16 = (cT06 - '0') + (i27 * 10);
                        i25 = 3;
                    }
                } else {
                    i16 = 0;
                }
                this.f4889q.set(14, i16);
                char cT035 = t0(this.f4883k + i15 + 10 + i25);
                if (cT035 == ' ') {
                    i25++;
                    cT035 = t0(this.f4883k + i15 + 10 + i25);
                }
                int i28 = i25;
                char c25 = cT035;
                if (c25 == '+' || c25 == '-') {
                    char cT036 = t0(this.f4883k + i15 + 10 + i28 + 1);
                    if (cT036 < '0' || cT036 > '1' || (cT03 = t0(this.f4883k + i15 + 10 + i28 + 2)) < '0' || cT03 > '9') {
                        return false;
                    }
                    char cT037 = t0(this.f4883k + i15 + 10 + i28 + 3);
                    char c26 = '3';
                    if (cT037 == ':') {
                        char cT038 = t0(this.f4883k + i15 + 10 + i28 + 4);
                        cT04 = t0(this.f4883k + i15 + 10 + i28 + 5);
                        if (cT038 == '4' && cT04 == '5') {
                            if (cT036 != '1' || (cT03 != '2' && cT03 != '3')) {
                                if (cT036 != '0') {
                                    return false;
                                }
                                if (cT03 != '5' && cT03 != '8') {
                                    return false;
                                }
                            }
                        } else if ((cT038 != '0' && cT038 != '3') || cT04 != '0') {
                            return false;
                        }
                        c26 = cT038;
                        i18 = 6;
                    } else {
                        if (cT037 == '0') {
                            char cT039 = t0(this.f4883k + i15 + 10 + i28 + 4);
                            if (cT039 != '0' && cT039 != '3') {
                                return false;
                            }
                            c26 = cT039;
                        } else {
                            if (cT037 != '3' || t0(this.f4883k + i15 + 10 + i28 + 4) != '0') {
                                if (cT037 == '4' && t0(this.f4883k + i15 + 10 + i28 + 4) == '5') {
                                    cT04 = '5';
                                    i18 = 5;
                                    c26 = '4';
                                } else {
                                    c26 = '0';
                                }
                            }
                            cT04 = '0';
                        }
                        i18 = 5;
                        cT04 = '0';
                    }
                    p1(c25, cT036, cT03, c26, cT04);
                } else if (c25 == 'Z') {
                    if (this.f4889q.getTimeZone().getRawOffset() != 0) {
                        String[] availableIDs2 = TimeZone.getAvailableIDs(0);
                        if (availableIDs2.length > 0) {
                            this.f4889q.setTimeZone(TimeZone.getTimeZone(availableIDs2[0]));
                        }
                    }
                    i18 = 1;
                } else {
                    i18 = 0;
                }
                int i29 = i15 + 10 + i28 + i18;
                char cT040 = t0(this.f4883k + i29);
                if (cT040 != 26 && cT040 != '\"') {
                    return false;
                }
                int i30 = this.f4883k + i29;
                this.f4883k = i30;
                this.f4882j = t0(i30);
                this.f4879g = 5;
                return true;
            }
            z8 = false;
            c8 = ':';
            i9 = 5;
        }
        if (z7) {
            return z8;
        }
        char cT041 = t0(this.f4883k + 8);
        boolean z9 = cT013 == '-' && cT016 == '-';
        boolean z10 = z9 && i8 == 16;
        boolean z11 = z9 && i8 == 17;
        if (z11 || z10) {
            cT0 = t0(this.f4883k + 9);
            c9 = cT014;
            c10 = cT015;
            c11 = cT041;
        } else if (cT013 == '-' && cT015 == '-') {
            c10 = cT014;
            cT0 = cT016;
            c9 = '0';
            c11 = '0';
        } else {
            c9 = cT013;
            c10 = cT014;
            c11 = cT015;
            cT0 = cT016;
        }
        if (!h1(cT09, cT010, cT011, cT012, c9, c10, c11, cT0)) {
            return false;
        }
        m1(cT09, cT010, cT011, cT012, c9, c10, c11, cT0);
        if (i8 != 8) {
            char cT042 = t0(this.f4883k + 9);
            char cT043 = t0(this.f4883k + 10);
            char cT044 = t0(this.f4883k + 11);
            char cT045 = t0(this.f4883k + 12);
            char cT046 = t0(this.f4883k + 13);
            if ((z11 && cT043 == 'T' && cT046 == c8 && t0(this.f4883k + 16) == 'Z') || (z10 && ((cT043 == ' ' || cT043 == 'T') && cT046 == c8))) {
                char cT047 = t0(this.f4883k + 14);
                cT02 = t0(this.f4883k + 15);
                c13 = cT047;
                c15 = cT044;
                c12 = cT045;
                c14 = '0';
                c16 = '0';
            } else {
                c12 = cT042;
                c13 = cT043;
                cT02 = cT044;
                c14 = cT045;
                c15 = cT041;
                c16 = cT046;
            }
            if (!i1(c15, c12, c13, cT02, c14, c16)) {
                return false;
            }
            if (i8 != 17 || z11) {
                i14 = 0;
            } else {
                char cT048 = t0(this.f4883k + 14);
                char cT049 = t0(this.f4883k + 15);
                char cT050 = t0(this.f4883k + 16);
                if (cT048 < '0' || cT048 > '9' || cT049 < '0' || cT049 > '9' || cT050 < '0' || cT050 > '9') {
                    return false;
                }
                i14 = ((cT048 - '0') * 100) + ((cT049 - '0') * 10) + (cT050 - '0');
            }
            i11 = (c16 - '0') + ((c14 - '0') * 10);
            i13 = ((c15 - '0') * 10) + (c12 - '0');
            i12 = i14;
            i10 = ((c13 - '0') * 10) + (cT02 - '0');
        } else {
            i10 = 0;
            i11 = 0;
            i12 = 0;
            i13 = 0;
        }
        this.f4889q.set(11, i13);
        this.f4889q.set(12, i10);
        this.f4889q.set(13, i11);
        this.f4889q.set(14, i12);
        this.f4879g = i9;
        return true;
    }

    public final void m1(char c8, char c9, char c10, char c11, char c12, char c13, char c14, char c15) {
        Calendar calendar = Calendar.getInstance(this.f4890r, this.f4891s);
        this.f4889q = calendar;
        calendar.set(1, ((c8 - '0') * 1000) + ((c9 - '0') * 100) + ((c10 - '0') * 10) + (c11 - '0'));
        this.f4889q.set(2, (((c12 - '0') * 10) + (c13 - '0')) - 1);
        this.f4889q.set(5, ((c14 - '0') * 10) + (c15 - '0'));
    }

    @Override // f2.c, f2.b
    public final String n0() {
        return !this.f4888p ? e1(this.f4887o + 1, this.f4886n) : new String(this.f4885m, 0, this.f4886n);
    }

    public void n1(char c8, char c9, char c10, char c11, char c12, char c13) {
        this.f4889q.set(11, ((c8 - '0') * 10) + (c9 - '0'));
        this.f4889q.set(12, ((c10 - '0') * 10) + (c11 - '0'));
        this.f4889q.set(13, ((c12 - '0') * 10) + (c13 - '0'));
    }

    @Override // f2.c, f2.b
    public final char next() {
        int i8 = this.f4883k + 1;
        this.f4883k = i8;
        char cCharAt = i8 >= this.f4900x ? (char) 26 : this.f4899w.charAt(i8);
        this.f4882j = cCharAt;
        return cCharAt;
    }

    public void o1(char c8, char c9, char c10) {
        p1(c8, c9, c10, '0', '0');
    }

    public void p1(char c8, char c9, char c10, char c11, char c12) {
        int i8 = ((((c9 - '0') * 10) + (c10 - '0')) * 3600 * 1000) + ((((c11 - '0') * 10) + (c12 - '0')) * 60 * 1000);
        if (c8 == '-') {
            i8 = -i8;
        }
        if (this.f4889q.getTimeZone().getRawOffset() != i8) {
            this.f4889q.setTimeZone(new SimpleTimeZone(i8, Integer.toString(i8)));
        }
    }

    @Override // f2.c
    public final String q0(int i8, int i9, int i10, i iVar) {
        return iVar.a(this.f4899w, i8, i9, i10);
    }

    public final void q1(boolean z7) {
        int i8 = this.f4883k;
        boolean z8 = false;
        int i9 = 0;
        while (i8 < this.f4899w.length()) {
            char cCharAt = this.f4899w.charAt(i8);
            if (cCharAt == '\\') {
                if (i8 >= this.f4900x - 1) {
                    this.f4882j = cCharAt;
                    this.f4883k = i8;
                    throw new JSONException("illegal str, " + g());
                }
                i8++;
            } else if (cCharAt == '\"') {
                z8 = !z8;
            } else if (cCharAt != '[') {
                if (cCharAt == '{' && z7) {
                    int i10 = this.f4883k + 1;
                    this.f4883k = i10;
                    this.f4882j = i10 < this.f4899w.length() ? this.f4899w.charAt(i10) : (char) 26;
                    r1(z7);
                } else if (cCharAt == ']' && !z8 && i9 - 1 == -1) {
                    int i11 = i8 + 1;
                    this.f4883k = i11;
                    if (i11 == this.f4899w.length()) {
                        this.f4882j = (char) 26;
                        this.f4879g = 20;
                        return;
                    } else {
                        this.f4882j = this.f4899w.charAt(this.f4883k);
                        V(16);
                        return;
                    }
                }
            } else if (!z8) {
                i9++;
            }
            i8++;
        }
        if (i8 != this.f4899w.length()) {
            return;
        }
        throw new JSONException("illegal str, " + g());
    }

    @Override // f2.c
    public final void r0(int i8, char[] cArr, int i9, int i10) {
        this.f4899w.getChars(i8, i10 + i8, cArr, i9);
    }

    public final void r1(boolean z7) {
        int i8 = this.f4883k;
        boolean z8 = false;
        int i9 = 0;
        while (i8 < this.f4899w.length()) {
            char cCharAt = this.f4899w.charAt(i8);
            if (cCharAt == '\\') {
                if (i8 >= this.f4900x - 1) {
                    this.f4882j = cCharAt;
                    this.f4883k = i8;
                    throw new JSONException("illegal str, " + g());
                }
                i8++;
            } else if (cCharAt == '\"') {
                z8 = !z8;
            } else if (cCharAt != '{') {
                if (cCharAt == '}' && !z8 && i9 - 1 == -1) {
                    int i10 = i8 + 1;
                    this.f4883k = i10;
                    if (i10 == this.f4899w.length()) {
                        this.f4882j = (char) 26;
                        this.f4879g = 20;
                        return;
                    }
                    char cCharAt2 = this.f4899w.charAt(this.f4883k);
                    this.f4882j = cCharAt2;
                    if (cCharAt2 == ',') {
                        this.f4879g = 16;
                        int i11 = this.f4883k + 1;
                        this.f4883k = i11;
                        this.f4882j = i11 < this.f4899w.length() ? this.f4899w.charAt(i11) : (char) 26;
                        return;
                    }
                    if (cCharAt2 == '}') {
                        this.f4879g = 13;
                        next();
                        return;
                    } else if (cCharAt2 != ']') {
                        V(16);
                        return;
                    } else {
                        this.f4879g = 15;
                        next();
                        return;
                    }
                }
            } else if (!z8) {
                i9++;
            }
            i8++;
        }
        for (int i12 = 0; i12 < this.f4883k; i12++) {
            if (i12 < this.f4899w.length() && this.f4899w.charAt(i12) == ' ') {
                i8++;
            }
        }
        if (i8 != this.f4899w.length()) {
            return;
        }
        throw new JSONException("illegal str, " + g());
    }

    @Override // f2.c
    public final boolean s0(char[] cArr) {
        return g1(this.f4899w, this.f4883k, cArr);
    }

    @Override // f2.c
    public final char t0(int i8) {
        if (i8 >= this.f4900x) {
            return (char) 26;
        }
        return this.f4899w.charAt(i8);
    }

    @Override // f2.c
    public final void u0(int i8, int i9, char[] cArr) {
        this.f4899w.getChars(i8, i9 + i8, cArr, 0);
    }

    @Override // f2.c, f2.b
    public String v(i iVar) {
        int iIndexOf;
        if (!this.f4899w.startsWith("\"@type\":\"", this.f4883k) || (iIndexOf = this.f4899w.indexOf(34, this.f4883k + 9)) == -1) {
            return null;
        }
        int i8 = this.f4883k + 9;
        this.f4883k = i8;
        int iCharAt = 0;
        while (i8 < iIndexOf) {
            iCharAt = (iCharAt * 31) + this.f4899w.charAt(i8);
            i8++;
        }
        int i9 = this.f4883k;
        String strQ0 = q0(i9, iIndexOf - i9, iCharAt, iVar);
        char cCharAt = this.f4899w.charAt(iIndexOf + 1);
        if (cCharAt != ',' && cCharAt != ']') {
            return null;
        }
        int i10 = iIndexOf + 2;
        this.f4883k = i10;
        this.f4882j = this.f4899w.charAt(i10);
        return strQ0;
    }

    @Override // f2.c
    public final int x0(char c8, int i8) {
        return this.f4899w.indexOf(c8, i8);
    }

    @Override // f2.c
    public boolean y0() {
        int i8 = this.f4883k;
        int i9 = this.f4900x;
        if (i8 != i9) {
            return this.f4882j == 26 && i8 + 1 >= i9;
        }
        return true;
    }

    public e(String str, int i8) {
        super(i8);
        this.f4899w = str;
        this.f4900x = str.length();
        this.f4883k = -1;
        next();
        if (this.f4882j == 65279) {
            next();
        }
    }

    public e(char[] cArr, int i8, int i9) {
        this(new String(cArr, 0, i8), i9);
    }
}
