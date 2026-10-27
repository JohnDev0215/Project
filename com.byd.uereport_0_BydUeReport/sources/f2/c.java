package f2;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.Feature;
import java.io.Closeable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* JADX INFO: compiled from: JSONLexerBase.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c implements b, Closeable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final ThreadLocal<char[]> f4876d = new ThreadLocal<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final char[] f4877e = ("\"" + c2.a.DEFAULT_TYPE_KEY + "\":\"").toCharArray();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final int[] f4878f = new int[103];

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4879g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f4880h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f4881i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public char f4882j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f4883k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f4884l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public char[] f4885m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f4886n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f4887o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f4888p;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public String f4893u;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Calendar f4889q = null;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public TimeZone f4890r = c2.a.defaultTimeZone;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public Locale f4891s = c2.a.defaultLocale;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f4892t = 0;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f4894v = 0;

    static {
        for (int i8 = 48; i8 <= 57; i8++) {
            f4878f[i8] = i8 - 48;
        }
        for (int i9 = 97; i9 <= 102; i9++) {
            f4878f[i9] = (i9 - 97) + 10;
        }
        for (int i10 = 65; i10 <= 70; i10++) {
            f4878f[i10] = (i10 - 65) + 10;
        }
    }

    public c(int i8) {
        this.f4893u = null;
        this.f4881i = i8;
        if ((i8 & Feature.InitStringFieldAsEmpty.mask) != 0) {
            this.f4893u = "";
        }
        char[] cArr = f4876d.get();
        this.f4885m = cArr;
        if (cArr == null) {
            this.f4885m = new char[512];
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00e8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String F0(char[] r12, int r13) {
        /*
            Method dump skipped, instruction units count: 290
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: f2.c.F0(char[], int):java.lang.String");
    }

    public static boolean z0(char c8) {
        return c8 <= ' ' && (c8 == ' ' || c8 == '\n' || c8 == '\r' || c8 == '\t' || c8 == '\f' || c8 == '\b');
    }

    public void A0(String str, Object... objArr) {
        this.f4879g = 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x00c7 A[ADDED_TO_REGION] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:57:0x00c9 -> B:53:0x00b7). Please report as a decompilation issue!!! */
    @Override // f2.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public double B(char r21) {
        /*
            Method dump skipped, instruction units count: 437
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: f2.c.B(char):double");
    }

    public final boolean B0(char[] cArr) {
        while (!s0(cArr)) {
            if (!z0(this.f4882j)) {
                return false;
            }
            next();
        }
        int length = this.f4883k + cArr.length;
        this.f4883k = length;
        char cT0 = t0(length);
        this.f4882j = cT0;
        if (cT0 == '{') {
            next();
            this.f4879g = 12;
        } else if (cT0 == '[') {
            next();
            this.f4879g = 14;
        } else if (cT0 == 'S' && t0(this.f4883k + 1) == 'e' && t0(this.f4883k + 2) == 't' && t0(this.f4883k + 3) == '[') {
            int i8 = this.f4883k + 3;
            this.f4883k = i8;
            this.f4882j = t0(i8);
            this.f4879g = 21;
        } else {
            E();
        }
        return true;
    }

    @Override // f2.b
    public final float C(char c8) {
        int i8;
        int i9;
        char cT0;
        int i10;
        int i11;
        float f8;
        this.f4892t = 0;
        char cT02 = t0(this.f4883k + 0);
        boolean z7 = cT02 == '\"';
        if (z7) {
            cT02 = t0(this.f4883k + 1);
            i8 = 2;
        } else {
            i8 = 1;
        }
        boolean z8 = cT02 == '-';
        if (z8) {
            cT02 = t0(this.f4883k + i8);
            i8++;
        }
        if (cT02 < '0' || cT02 > '9') {
            if (cT02 != 'n' || t0(this.f4883k + i8) != 'u' || t0(this.f4883k + i8 + 1) != 'l' || t0(this.f4883k + i8 + 2) != 'l') {
                this.f4892t = -1;
                return 0.0f;
            }
            this.f4892t = 5;
            int i12 = i8 + 3;
            int i13 = i12 + 1;
            char cT03 = t0(this.f4883k + i12);
            if (z7 && cT03 == '\"') {
                cT03 = t0(this.f4883k + i13);
                i13++;
            }
            while (cT03 != ',') {
                if (cT03 == ']') {
                    int i14 = this.f4883k + i13;
                    this.f4883k = i14;
                    this.f4882j = t0(i14);
                    this.f4892t = 5;
                    this.f4879g = 15;
                    return 0.0f;
                }
                if (!z0(cT03)) {
                    this.f4892t = -1;
                    return 0.0f;
                }
                cT03 = t0(this.f4883k + i13);
                i13++;
            }
            int i15 = this.f4883k + i13;
            this.f4883k = i15;
            this.f4882j = t0(i15);
            this.f4892t = 5;
            this.f4879g = 16;
            return 0.0f;
        }
        long j8 = cT02 - '0';
        while (true) {
            i9 = i8 + 1;
            cT0 = t0(this.f4883k + i8);
            if (cT0 < '0' || cT0 > '9') {
                break;
            }
            j8 = (j8 * 10) + ((long) (cT0 - '0'));
            i8 = i9;
        }
        long j9 = 1;
        if (cT0 == '.') {
            int i16 = i9 + 1;
            char cT04 = t0(this.f4883k + i9);
            if (cT04 < '0' || cT04 > '9') {
                this.f4892t = -1;
                return 0.0f;
            }
            j8 = (j8 * 10) + ((long) (cT04 - '0'));
            j9 = 10;
            while (true) {
                i9 = i16 + 1;
                cT0 = t0(this.f4883k + i16);
                if (cT0 < '0' || cT0 > '9') {
                    break;
                }
                j8 = (j8 * 10) + ((long) (cT0 - '0'));
                j9 *= 10;
                i16 = i9;
            }
        }
        long j10 = j9;
        boolean z9 = cT0 == 'e' || cT0 == 'E';
        if (z9) {
            int i17 = i9 + 1;
            char cT05 = t0(this.f4883k + i9);
            if (cT05 == '+' || cT05 == '-') {
                int i18 = i17 + 1;
                cT0 = t0(this.f4883k + i17);
                i9 = i18;
            } else {
                i9 = i17;
                cT0 = cT05;
            }
            while (cT0 >= '0' && cT0 <= '9') {
                int i19 = i9 + 1;
                cT0 = t0(this.f4883k + i9);
                i9 = i19;
            }
        }
        if (!z7) {
            i10 = this.f4883k;
            i11 = ((i10 + i9) - i10) - 1;
        } else {
            if (cT0 != '\"') {
                this.f4892t = -1;
                return 0.0f;
            }
            int i20 = i9 + 1;
            cT0 = t0(this.f4883k + i9);
            int i21 = this.f4883k;
            i10 = i21 + 1;
            i11 = ((i21 + i20) - i10) - 2;
            i9 = i20;
        }
        if (z9 || i11 >= 17) {
            f8 = Float.parseFloat(e1(i10, i11));
        } else {
            f8 = (float) (j8 / j10);
            if (z8) {
                f8 = -f8;
            }
        }
        if (cT0 != c8) {
            this.f4892t = -1;
            return f8;
        }
        int i22 = this.f4883k + i9;
        this.f4883k = i22;
        this.f4882j = t0(i22);
        this.f4892t = 3;
        this.f4879g = 16;
        return f8;
    }

    public final void C0() {
        while (z0(this.f4882j)) {
            next();
        }
        char c8 = this.f4882j;
        if (c8 == '_' || c8 == '$' || Character.isLetter(c8)) {
            V0();
        } else {
            E();
        }
    }

    public final void D0(char c8) {
        this.f4886n = 0;
        while (true) {
            char c9 = this.f4882j;
            if (c9 == c8) {
                next();
                E();
                return;
            }
            if (c9 != ' ' && c9 != '\n' && c9 != '\r' && c9 != '\t' && c9 != '\f' && c9 != '\b') {
                throw new JSONException("not match " + c8 + " - " + this.f4882j + ", info : " + g());
            }
            next();
        }
    }

    @Override // f2.b
    public final void E() {
        this.f4886n = 0;
        while (true) {
            this.f4880h = this.f4883k;
            char c8 = this.f4882j;
            if (c8 == '/') {
                c1();
            } else {
                if (c8 == '\"') {
                    L();
                    return;
                }
                if (c8 == ',') {
                    next();
                    this.f4879g = 16;
                    return;
                }
                if (c8 >= '0' && c8 <= '9') {
                    R();
                    return;
                }
                if (c8 == '-') {
                    R();
                    return;
                }
                switch (c8) {
                    case '\b':
                    case '\t':
                    case '\n':
                    case '\f':
                    case '\r':
                    case ' ':
                        next();
                        break;
                    case '\'':
                        if (!M(Feature.AllowSingleQuotes)) {
                            throw new JSONException("Feature.AllowSingleQuotes is false");
                        }
                        Y0();
                        return;
                    case '(':
                        next();
                        this.f4879g = 10;
                        return;
                    case ')':
                        next();
                        this.f4879g = 11;
                        return;
                    case '+':
                        next();
                        R();
                        return;
                    case '.':
                        next();
                        this.f4879g = 25;
                        return;
                    case ':':
                        next();
                        this.f4879g = 17;
                        return;
                    case ';':
                        next();
                        this.f4879g = 24;
                        return;
                    case 'N':
                    case 'S':
                    case 'T':
                    case 'u':
                        V0();
                        return;
                    case '[':
                        next();
                        this.f4879g = 14;
                        return;
                    case ']':
                        next();
                        this.f4879g = 15;
                        return;
                    case 'f':
                        H0();
                        return;
                    case 'n':
                        W0();
                        return;
                    case 't':
                        a1();
                        return;
                    case 'x':
                        U0();
                        return;
                    case '{':
                        next();
                        this.f4879g = 12;
                        return;
                    case '}':
                        next();
                        this.f4879g = 13;
                        return;
                    default:
                        if (y0()) {
                            if (this.f4879g == 20) {
                                throw new JSONException("EOF error");
                            }
                            this.f4879g = 20;
                            int i8 = this.f4883k;
                            this.f4880h = i8;
                            this.f4884l = i8;
                            return;
                        }
                        char c9 = this.f4882j;
                        if (c9 > 31 && c9 != 127) {
                            A0("illegal.char", String.valueOf((int) c9));
                            next();
                            return;
                        }
                        next();
                        break;
                        break;
                }
            }
        }
    }

    public final void E0(char c8) {
        int i8 = this.f4886n;
        char[] cArr = this.f4885m;
        if (i8 == cArr.length) {
            char[] cArr2 = new char[cArr.length * 2];
            System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
            this.f4885m = cArr2;
        }
        char[] cArr3 = this.f4885m;
        int i9 = this.f4886n;
        this.f4886n = i9 + 1;
        cArr3[i9] = c8;
    }

    @Override // f2.b
    public final char F() {
        return this.f4882j;
    }

    public long G0(char[] cArr) {
        this.f4892t = 0;
        if (!s0(cArr)) {
            this.f4892t = -2;
            return 0L;
        }
        int length = cArr.length;
        int i8 = length + 1;
        if (t0(this.f4883k + length) != '\"') {
            this.f4892t = -1;
            return 0L;
        }
        long j8 = -3750763034362895579L;
        while (true) {
            int i9 = i8 + 1;
            char cT0 = t0(this.f4883k + i8);
            if (cT0 == '\"') {
                int i10 = i9 + 1;
                char cT02 = t0(this.f4883k + i9);
                if (cT02 == ',') {
                    int i11 = this.f4883k + i10;
                    this.f4883k = i11;
                    this.f4882j = t0(i11);
                    this.f4892t = 3;
                    return j8;
                }
                if (cT02 != '}') {
                    this.f4892t = -1;
                    return 0L;
                }
                int i12 = i10 + 1;
                char cT03 = t0(this.f4883k + i10);
                if (cT03 == ',') {
                    this.f4879g = 16;
                    int i13 = this.f4883k + i12;
                    this.f4883k = i13;
                    this.f4882j = t0(i13);
                } else if (cT03 == ']') {
                    this.f4879g = 15;
                    int i14 = this.f4883k + i12;
                    this.f4883k = i14;
                    this.f4882j = t0(i14);
                } else if (cT03 == '}') {
                    this.f4879g = 13;
                    int i15 = this.f4883k + i12;
                    this.f4883k = i15;
                    this.f4882j = t0(i15);
                } else {
                    if (cT03 != 26) {
                        this.f4892t = -1;
                        return 0L;
                    }
                    this.f4879g = 20;
                    this.f4883k += i12 - 1;
                    this.f4882j = (char) 26;
                }
                this.f4892t = 4;
                return j8;
            }
            j8 = (j8 ^ ((long) ((cT0 < 'A' || cT0 > 'Z') ? cT0 : cT0 + ' '))) * 1099511628211L;
            if (cT0 == '\\') {
                this.f4892t = -1;
                return 0L;
            }
            i8 = i9;
        }
    }

    public final void H0() {
        if (this.f4882j != 'f') {
            throw new JSONException("error parse false");
        }
        next();
        if (this.f4882j != 'a') {
            throw new JSONException("error parse false");
        }
        next();
        if (this.f4882j != 'l') {
            throw new JSONException("error parse false");
        }
        next();
        if (this.f4882j != 's') {
            throw new JSONException("error parse false");
        }
        next();
        if (this.f4882j != 'e') {
            throw new JSONException("error parse false");
        }
        next();
        char c8 = this.f4882j;
        if (c8 != ' ' && c8 != ',' && c8 != '}' && c8 != ']' && c8 != '\n' && c8 != '\r' && c8 != '\t' && c8 != 26 && c8 != '\f' && c8 != '\b' && c8 != ':' && c8 != '/') {
            throw new JSONException("scan false error");
        }
        this.f4879g = 7;
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x00ad A[ADDED_TO_REGION] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:54:0x00af -> B:50:0x009d). Please report as a decompilation issue!!! */
    @Override // f2.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.math.BigDecimal I(char r19) {
        /*
            Method dump skipped, instruction units count: 487
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: f2.c.I(char):java.math.BigDecimal");
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0073, code lost:
    
        r16 = false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.math.BigInteger I0(char[] r21) {
        /*
            Method dump skipped, instruction units count: 453
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: f2.c.I0(char[]):java.math.BigInteger");
    }

    public boolean J0(char[] cArr) {
        boolean z7;
        int i8;
        this.f4892t = 0;
        if (!s0(cArr)) {
            this.f4892t = -2;
            return false;
        }
        int length = cArr.length;
        int i9 = length + 1;
        char cT0 = t0(this.f4883k + length);
        if (cT0 == 't') {
            int i10 = i9 + 1;
            if (t0(this.f4883k + i9) != 'r') {
                this.f4892t = -1;
                return false;
            }
            int i11 = i10 + 1;
            if (t0(this.f4883k + i10) != 'u') {
                this.f4892t = -1;
                return false;
            }
            i8 = i11 + 1;
            if (t0(this.f4883k + i11) != 'e') {
                this.f4892t = -1;
                return false;
            }
            z7 = true;
        } else {
            if (cT0 != 'f') {
                this.f4892t = -1;
                return false;
            }
            int i12 = i9 + 1;
            if (t0(this.f4883k + i9) != 'a') {
                this.f4892t = -1;
                return false;
            }
            int i13 = i12 + 1;
            if (t0(this.f4883k + i12) != 'l') {
                this.f4892t = -1;
                return false;
            }
            int i14 = i13 + 1;
            if (t0(this.f4883k + i13) != 's') {
                this.f4892t = -1;
                return false;
            }
            int i15 = i14 + 1;
            if (t0(this.f4883k + i14) != 'e') {
                this.f4892t = -1;
                return false;
            }
            z7 = false;
            i8 = i15;
        }
        int i16 = i8 + 1;
        char cT02 = t0(this.f4883k + i8);
        if (cT02 == ',') {
            int i17 = this.f4883k + i16;
            this.f4883k = i17;
            this.f4882j = t0(i17);
            this.f4892t = 3;
            this.f4879g = 16;
            return z7;
        }
        if (cT02 != '}') {
            this.f4892t = -1;
            return false;
        }
        int i18 = i16 + 1;
        char cT03 = t0(this.f4883k + i16);
        if (cT03 == ',') {
            this.f4879g = 16;
            int i19 = this.f4883k + i18;
            this.f4883k = i19;
            this.f4882j = t0(i19);
        } else if (cT03 == ']') {
            this.f4879g = 15;
            int i20 = this.f4883k + i18;
            this.f4883k = i20;
            this.f4882j = t0(i20);
        } else if (cT03 == '}') {
            this.f4879g = 13;
            int i21 = this.f4883k + i18;
            this.f4883k = i21;
            this.f4882j = t0(i21);
        } else {
            if (cT03 != 26) {
                this.f4892t = -1;
                return false;
            }
            this.f4879g = 20;
            this.f4883k += i18 - 1;
            this.f4882j = (char) 26;
        }
        this.f4892t = 4;
        return z7;
    }

    public Date K0(char[] cArr) {
        int i8;
        long j8;
        Date date;
        int i9;
        char cT0;
        boolean z7 = false;
        this.f4892t = 0;
        if (!s0(cArr)) {
            this.f4892t = -2;
            return null;
        }
        int length = cArr.length;
        int i10 = length + 1;
        char cT02 = t0(this.f4883k + length);
        if (cT02 == '\"') {
            int iX0 = x0('\"', this.f4883k + cArr.length + 1);
            if (iX0 == -1) {
                throw new JSONException("unclosed str");
            }
            int length2 = this.f4883k + cArr.length + 1;
            String strE1 = e1(length2, iX0 - length2);
            if (strE1.indexOf(92) != -1) {
                while (true) {
                    int i11 = 0;
                    for (int i12 = iX0 - 1; i12 >= 0 && t0(i12) == '\\'; i12--) {
                        i11++;
                    }
                    if (i11 % 2 == 0) {
                        break;
                    }
                    iX0 = x0('\"', iX0 + 1);
                }
                int i13 = this.f4883k;
                int length3 = iX0 - ((cArr.length + i13) + 1);
                strE1 = F0(f1(i13 + cArr.length + 1, length3), length3);
            }
            int i14 = this.f4883k;
            int length4 = i10 + (iX0 - ((cArr.length + i14) + 1)) + 1;
            i8 = length4 + 1;
            cT02 = t0(i14 + length4);
            e eVar = new e(strE1);
            try {
                if (!eVar.k1(false)) {
                    this.f4892t = -1;
                    return null;
                }
                date = eVar.w0().getTime();
            } finally {
                eVar.close();
            }
        } else {
            if (cT02 != '-' && (cT02 < '0' || cT02 > '9')) {
                this.f4892t = -1;
                return null;
            }
            if (cT02 == '-') {
                cT02 = t0(this.f4883k + i10);
                i10++;
                z7 = true;
            }
            if (cT02 < '0' || cT02 > '9') {
                i8 = i10;
                j8 = 0;
            } else {
                j8 = cT02 - '0';
                while (true) {
                    i9 = i10 + 1;
                    cT0 = t0(this.f4883k + i10);
                    if (cT0 < '0' || cT0 > '9') {
                        break;
                    }
                    j8 = (j8 * 10) + ((long) (cT0 - '0'));
                    i10 = i9;
                }
                cT02 = cT0;
                i8 = i9;
            }
            if (j8 < 0) {
                this.f4892t = -1;
                return null;
            }
            if (z7) {
                j8 = -j8;
            }
            date = new Date(j8);
        }
        if (cT02 == ',') {
            int i15 = this.f4883k + i8;
            this.f4883k = i15;
            this.f4882j = t0(i15);
            this.f4892t = 3;
            return date;
        }
        if (cT02 != '}') {
            this.f4892t = -1;
            return null;
        }
        int i16 = i8 + 1;
        char cT03 = t0(this.f4883k + i8);
        if (cT03 == ',') {
            this.f4879g = 16;
            int i17 = this.f4883k + i16;
            this.f4883k = i17;
            this.f4882j = t0(i17);
        } else if (cT03 == ']') {
            this.f4879g = 15;
            int i18 = this.f4883k + i16;
            this.f4883k = i18;
            this.f4882j = t0(i18);
        } else if (cT03 == '}') {
            this.f4879g = 13;
            int i19 = this.f4883k + i16;
            this.f4883k = i19;
            this.f4882j = t0(i19);
        } else {
            if (cT03 != 26) {
                this.f4892t = -1;
                return null;
            }
            this.f4879g = 20;
            this.f4883k += i16 - 1;
            this.f4882j = (char) 26;
        }
        this.f4892t = 4;
        return date;
    }

    @Override // f2.b
    public final void L() {
        char next;
        char next2;
        this.f4887o = this.f4883k;
        this.f4888p = false;
        while (true) {
            char next3 = next();
            if (next3 == '\"') {
                this.f4879g = 4;
                this.f4882j = next();
                return;
            }
            if (next3 != 26) {
                boolean z7 = true;
                if (next3 == '\\') {
                    if (!this.f4888p) {
                        this.f4888p = true;
                        int i8 = this.f4886n;
                        char[] cArr = this.f4885m;
                        if (i8 >= cArr.length) {
                            int length = cArr.length * 2;
                            if (i8 <= length) {
                                i8 = length;
                            }
                            char[] cArr2 = new char[i8];
                            System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
                            this.f4885m = cArr2;
                        }
                        u0(this.f4887o + 1, this.f4886n, this.f4885m);
                    }
                    char next4 = next();
                    if (next4 == '\"') {
                        E0('\"');
                    } else if (next4 != '\'') {
                        if (next4 != 'F') {
                            if (next4 == '\\') {
                                E0('\\');
                            } else if (next4 == 'b') {
                                E0('\b');
                            } else if (next4 != 'f') {
                                if (next4 == 'n') {
                                    E0('\n');
                                } else if (next4 == 'r') {
                                    E0('\r');
                                } else if (next4 != 'x') {
                                    switch (next4) {
                                        case '/':
                                            E0('/');
                                            break;
                                        case '0':
                                            E0((char) 0);
                                            break;
                                        case '1':
                                            E0((char) 1);
                                            break;
                                        case '2':
                                            E0((char) 2);
                                            break;
                                        case '3':
                                            E0((char) 3);
                                            break;
                                        case '4':
                                            E0((char) 4);
                                            break;
                                        case '5':
                                            E0((char) 5);
                                            break;
                                        case '6':
                                            E0((char) 6);
                                            break;
                                        case '7':
                                            E0((char) 7);
                                            break;
                                        default:
                                            switch (next4) {
                                                case 't':
                                                    E0('\t');
                                                    break;
                                                case 'u':
                                                    E0((char) Integer.parseInt(new String(new char[]{next(), next(), next(), next()}), 16));
                                                    break;
                                                case 'v':
                                                    E0((char) 11);
                                                    break;
                                                default:
                                                    this.f4882j = next4;
                                                    throw new JSONException("unclosed string : " + next4);
                                            }
                                            break;
                                    }
                                } else {
                                    next = next();
                                    next2 = next();
                                    boolean z8 = (next >= '0' && next <= '9') || (next >= 'a' && next <= 'f') || (next >= 'A' && next <= 'F');
                                    if ((next2 < '0' || next2 > '9') && ((next2 < 'a' || next2 > 'f') && (next2 < 'A' || next2 > 'F'))) {
                                        z7 = false;
                                    }
                                    if (z8 && z7) {
                                        int[] iArr = f4878f;
                                        E0((char) ((iArr[next] * 16) + iArr[next2]));
                                    }
                                }
                            }
                        }
                        E0('\f');
                    } else {
                        E0('\'');
                    }
                } else if (this.f4888p) {
                    int i9 = this.f4886n;
                    char[] cArr3 = this.f4885m;
                    if (i9 == cArr3.length) {
                        E0(next3);
                    } else {
                        this.f4886n = i9 + 1;
                        cArr3[i9] = next3;
                    }
                } else {
                    this.f4886n++;
                }
            } else {
                if (y0()) {
                    throw new JSONException("unclosed string : " + next3);
                }
                E0((char) 26);
            }
        }
        throw new JSONException("invalid escape character \\x" + next + next2);
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x00b8 A[ADDED_TO_REGION] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:56:0x00ba -> B:52:0x00a8). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.math.BigDecimal L0(char[] r18) {
        /*
            Method dump skipped, instruction units count: 513
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: f2.c.L0(char[]):java.math.BigDecimal");
    }

    @Override // f2.b
    public final boolean M(Feature feature) {
        return isEnabled(feature.mask);
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x00dc A[ADDED_TO_REGION] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:59:0x00de -> B:55:0x00ca). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final double M0(char[] r24) {
        /*
            Method dump skipped, instruction units count: 564
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: f2.c.M0(char[]):double");
    }

    @Override // f2.b
    public final int N() {
        int i8;
        boolean z7;
        int i9 = 0;
        if (this.f4887o == -1) {
            this.f4887o = 0;
        }
        int i10 = this.f4887o;
        int i11 = this.f4886n + i10;
        if (t0(i10) == '-') {
            i8 = Integer.MIN_VALUE;
            i10++;
            z7 = true;
        } else {
            i8 = -2147483647;
            z7 = false;
        }
        if (i10 < i11) {
            i9 = -(t0(i10) - '0');
            i10++;
        }
        while (i10 < i11) {
            int i12 = i10 + 1;
            char cT0 = t0(i10);
            if (cT0 == 'L' || cT0 == 'S' || cT0 == 'B') {
                i10 = i12;
                break;
            }
            int i13 = cT0 - '0';
            if (i9 < -214748364) {
                throw new NumberFormatException(l0());
            }
            int i14 = i9 * 10;
            if (i14 < i8 + i13) {
                throw new NumberFormatException(l0());
            }
            i9 = i14 - i13;
            i10 = i12;
        }
        if (!z7) {
            return -i9;
        }
        if (i10 > this.f4887o + 1) {
            return i9;
        }
        throw new NumberFormatException(l0());
    }

    public final float N0(char[] cArr) {
        int i8;
        char cT0;
        boolean z7;
        long j8;
        int length;
        int i9;
        float f8;
        this.f4892t = 0;
        if (!s0(cArr)) {
            this.f4892t = -2;
            return 0.0f;
        }
        int length2 = cArr.length;
        int i10 = length2 + 1;
        char cT02 = t0(this.f4883k + length2);
        boolean z8 = cT02 == '\"';
        if (z8) {
            cT02 = t0(this.f4883k + i10);
            i10++;
        }
        boolean z9 = cT02 == '-';
        if (z9) {
            cT02 = t0(this.f4883k + i10);
            i10++;
        }
        if (cT02 >= '0') {
            char c8 = '9';
            if (cT02 <= '9') {
                long j9 = cT02 - '0';
                while (true) {
                    i8 = i10 + 1;
                    cT0 = t0(this.f4883k + i10);
                    if (cT0 < '0' || cT0 > '9') {
                        break;
                    }
                    j9 = (j9 * 10) + ((long) (cT0 - '0'));
                    i10 = i8;
                }
                if (cT0 == '.') {
                    int i11 = i8 + 1;
                    char cT03 = t0(this.f4883k + i8);
                    if (cT03 < '0' || cT03 > '9') {
                        this.f4892t = -1;
                        return 0.0f;
                    }
                    z7 = z8;
                    j9 = (j9 * 10) + ((long) (cT03 - '0'));
                    j8 = 10;
                    while (true) {
                        i8 = i11 + 1;
                        cT0 = t0(this.f4883k + i11);
                        if (cT0 < '0' || cT0 > c8) {
                            break;
                        }
                        j9 = (j9 * 10) + ((long) (cT0 - '0'));
                        j8 *= 10;
                        i11 = i8;
                        c8 = '9';
                    }
                } else {
                    z7 = z8;
                    j8 = 1;
                }
                boolean z10 = cT0 == 'e' || cT0 == 'E';
                if (z10) {
                    int i12 = i8 + 1;
                    cT0 = t0(this.f4883k + i8);
                    if (cT0 == '+' || cT0 == '-') {
                        int i13 = i12 + 1;
                        cT0 = t0(this.f4883k + i12);
                        i8 = i13;
                    } else {
                        i8 = i12;
                    }
                    while (cT0 >= '0' && cT0 <= '9') {
                        int i14 = i8 + 1;
                        cT0 = t0(this.f4883k + i8);
                        i8 = i14;
                    }
                }
                if (!z7) {
                    int i15 = this.f4883k;
                    length = cArr.length + i15;
                    i9 = ((i15 + i8) - length) - 1;
                } else {
                    if (cT0 != '\"') {
                        this.f4892t = -1;
                        return 0.0f;
                    }
                    int i16 = i8 + 1;
                    cT0 = t0(this.f4883k + i8);
                    int i17 = this.f4883k;
                    length = cArr.length + i17 + 1;
                    i9 = ((i17 + i16) - length) - 2;
                    i8 = i16;
                }
                if (z10 || i9 >= 17) {
                    f8 = Float.parseFloat(e1(length, i9));
                } else {
                    f8 = (float) (j9 / j8);
                    if (z9) {
                        f8 = -f8;
                    }
                }
                if (cT0 == ',') {
                    int i18 = this.f4883k + i8;
                    this.f4883k = i18;
                    this.f4882j = t0(i18);
                    this.f4892t = 3;
                    this.f4879g = 16;
                    return f8;
                }
                if (cT0 != '}') {
                    this.f4892t = -1;
                    return 0.0f;
                }
                int i19 = i8 + 1;
                char cT04 = t0(this.f4883k + i8);
                if (cT04 == ',') {
                    this.f4879g = 16;
                    int i20 = this.f4883k + i19;
                    this.f4883k = i20;
                    this.f4882j = t0(i20);
                } else if (cT04 == ']') {
                    this.f4879g = 15;
                    int i21 = this.f4883k + i19;
                    this.f4883k = i21;
                    this.f4882j = t0(i21);
                } else if (cT04 == '}') {
                    this.f4879g = 13;
                    int i22 = this.f4883k + i19;
                    this.f4883k = i22;
                    this.f4882j = t0(i22);
                } else {
                    if (cT04 != 26) {
                        this.f4892t = -1;
                        return 0.0f;
                    }
                    this.f4883k += i19 - 1;
                    this.f4879g = 20;
                    this.f4882j = (char) 26;
                }
                this.f4892t = 4;
                return f8;
            }
        }
        boolean z11 = z8;
        if (cT02 != 'n' || t0(this.f4883k + i10) != 'u' || t0(this.f4883k + i10 + 1) != 'l' || t0(this.f4883k + i10 + 2) != 'l') {
            this.f4892t = -1;
            return 0.0f;
        }
        this.f4892t = 5;
        int i23 = i10 + 3;
        int i24 = i23 + 1;
        char cT05 = t0(this.f4883k + i23);
        if (z11 && cT05 == '\"') {
            cT05 = t0(this.f4883k + i24);
            i24++;
        }
        while (cT05 != ',') {
            if (cT05 == '}') {
                int i25 = this.f4883k + i24;
                this.f4883k = i25;
                this.f4882j = t0(i25);
                this.f4892t = 5;
                this.f4879g = 13;
                return 0.0f;
            }
            if (!z0(cT05)) {
                this.f4892t = -1;
                return 0.0f;
            }
            cT05 = t0(this.f4883k + i24);
            i24++;
        }
        int i26 = this.f4883k + i24;
        this.f4883k = i26;
        this.f4882j = t0(i26);
        this.f4892t = 5;
        this.f4879g = 16;
        return 0.0f;
    }

    @Override // f2.b
    public final void O() {
        while (true) {
            char c8 = this.f4882j;
            if (c8 > '/') {
                return;
            }
            if (c8 == ' ' || c8 == '\r' || c8 == '\n' || c8 == '\t' || c8 == '\f' || c8 == '\b') {
                next();
            } else if (c8 != '/') {
                return;
            } else {
                c1();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:103:0x01bb, code lost:
    
        r1 = r4;
        r19.f4892t = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x01be, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00a8, code lost:
    
        r19.f4892t = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00aa, code lost:
    
        return r4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final float[] O0(char[] r20) {
        /*
            Method dump skipped, instruction units count: 447
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: f2.c.O0(char[]):float[]");
    }

    @Override // f2.b
    public final void P() {
        this.f4886n = 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x00b1, code lost:
    
        r21.f4892t = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00b3, code lost:
    
        return r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0135, code lost:
    
        r4 = r18 + 1;
        r1 = t0(r21.f4883k + r18);
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0140, code lost:
    
        if (r2 == r3.length) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0142, code lost:
    
        r5 = new float[r2];
        r6 = 0;
        java.lang.System.arraycopy(r3, 0, r5, 0, r2);
        r3 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x014a, code lost:
    
        r6 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x014c, code lost:
    
        if (r8 < r7.length) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x014e, code lost:
    
        r5 = new float[(r7.length * 3) / 2][];
        java.lang.System.arraycopy(r3, r6, r5, r6, r2);
        r7 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0159, code lost:
    
        r5 = r8 + 1;
        r7[r8] = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x015f, code lost:
    
        if (r1 != ',') goto L87;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0161, code lost:
    
        r3 = r4 + 1;
        r2 = t0(r21.f4883k + r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x016f, code lost:
    
        if (r1 != ']') goto L90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0171, code lost:
    
        r3 = r4 + 1;
        r2 = t0(r21.f4883k + r4);
        r8 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x017c, code lost:
    
        r2 = r1;
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x0191, code lost:
    
        r21.f4892t = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x0194, code lost:
    
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final float[][] P0(char[] r22) {
        /*
            Method dump skipped, instruction units count: 529
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: f2.c.P0(char[]):float[][]");
    }

    public int Q0(char[] cArr) {
        int i8;
        char cT0;
        this.f4892t = 0;
        if (!s0(cArr)) {
            this.f4892t = -2;
            return 0;
        }
        int length = cArr.length;
        int i9 = length + 1;
        char cT02 = t0(this.f4883k + length);
        boolean z7 = cT02 == '-';
        if (z7) {
            cT02 = t0(this.f4883k + i9);
            i9++;
        }
        if (cT02 < '0' || cT02 > '9') {
            this.f4892t = -1;
            return 0;
        }
        int i10 = cT02 - '0';
        while (true) {
            i8 = i9 + 1;
            cT0 = t0(this.f4883k + i9);
            if (cT0 < '0' || cT0 > '9') {
                break;
            }
            i10 = (i10 * 10) + (cT0 - '0');
            i9 = i8;
        }
        if (cT0 == '.') {
            this.f4892t = -1;
            return 0;
        }
        if ((i10 < 0 || i8 > cArr.length + 14) && !(i10 == Integer.MIN_VALUE && i8 == 17 && z7)) {
            this.f4892t = -1;
            return 0;
        }
        if (cT0 == ',') {
            int i11 = this.f4883k + i8;
            this.f4883k = i11;
            this.f4882j = t0(i11);
            this.f4892t = 3;
            this.f4879g = 16;
            return z7 ? -i10 : i10;
        }
        if (cT0 != '}') {
            this.f4892t = -1;
            return 0;
        }
        int i12 = i8 + 1;
        char cT03 = t0(this.f4883k + i8);
        if (cT03 == ',') {
            this.f4879g = 16;
            int i13 = this.f4883k + i12;
            this.f4883k = i13;
            this.f4882j = t0(i13);
        } else if (cT03 == ']') {
            this.f4879g = 15;
            int i14 = this.f4883k + i12;
            this.f4883k = i14;
            this.f4882j = t0(i14);
        } else if (cT03 == '}') {
            this.f4879g = 13;
            int i15 = this.f4883k + i12;
            this.f4883k = i15;
            this.f4882j = t0(i15);
        } else {
            if (cT03 != 26) {
                this.f4892t = -1;
                return 0;
            }
            this.f4879g = 20;
            this.f4883k += i12 - 1;
            this.f4882j = (char) 26;
        }
        this.f4892t = 4;
        return z7 ? -i10 : i10;
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00c5  */
    @Override // f2.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void R() {
        /*
            Method dump skipped, instruction units count: 209
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: f2.c.R():void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:63:0x011f, code lost:
    
        r2 = r4;
        r17.f4892t = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0122, code lost:
    
        return r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int[] R0(char[] r18) {
        /*
            Method dump skipped, instruction units count: 291
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: f2.c.R0(char[]):int[]");
    }

    public long S0(char[] cArr) {
        boolean z7;
        int i8;
        char cT0;
        this.f4892t = 0;
        if (!s0(cArr)) {
            this.f4892t = -2;
            return 0L;
        }
        int length = cArr.length;
        int i9 = length + 1;
        char cT02 = t0(this.f4883k + length);
        if (cT02 == '-') {
            cT02 = t0(this.f4883k + i9);
            i9++;
            z7 = true;
        } else {
            z7 = false;
        }
        if (cT02 < '0' || cT02 > '9') {
            this.f4892t = -1;
            return 0L;
        }
        long j8 = cT02 - '0';
        while (true) {
            i8 = i9 + 1;
            cT0 = t0(this.f4883k + i9);
            if (cT0 < '0' || cT0 > '9') {
                break;
            }
            j8 = (j8 * 10) + ((long) (cT0 - '0'));
            i9 = i8;
        }
        if (cT0 == '.') {
            this.f4892t = -1;
            return 0L;
        }
        if (!(i8 - cArr.length < 21 && (j8 >= 0 || (j8 == Long.MIN_VALUE && z7)))) {
            this.f4892t = -1;
            return 0L;
        }
        if (cT0 == ',') {
            int i10 = this.f4883k + i8;
            this.f4883k = i10;
            this.f4882j = t0(i10);
            this.f4892t = 3;
            this.f4879g = 16;
            return z7 ? -j8 : j8;
        }
        if (cT0 != '}') {
            this.f4892t = -1;
            return 0L;
        }
        int i11 = i8 + 1;
        char cT03 = t0(this.f4883k + i8);
        if (cT03 == ',') {
            this.f4879g = 16;
            int i12 = this.f4883k + i11;
            this.f4883k = i12;
            this.f4882j = t0(i12);
        } else if (cT03 == ']') {
            this.f4879g = 15;
            int i13 = this.f4883k + i11;
            this.f4883k = i13;
            this.f4882j = t0(i13);
        } else if (cT03 == '}') {
            this.f4879g = 13;
            int i14 = this.f4883k + i11;
            this.f4883k = i14;
            this.f4882j = t0(i14);
        } else {
            if (cT03 != 26) {
                this.f4892t = -1;
                return 0L;
            }
            this.f4879g = 20;
            this.f4883k += i11 - 1;
            this.f4882j = (char) 26;
        }
        this.f4892t = 4;
        return z7 ? -j8 : j8;
    }

    @Override // f2.b
    public long T(char c8) {
        int i8;
        int i9;
        char cT0;
        char c9;
        this.f4892t = 0;
        char cT02 = t0(this.f4883k + 0);
        boolean z7 = cT02 == '\"';
        if (z7) {
            cT02 = t0(this.f4883k + 1);
            i8 = 2;
        } else {
            i8 = 1;
        }
        boolean z8 = cT02 == '-';
        if (z8) {
            cT02 = t0(this.f4883k + i8);
            i8++;
        }
        if (cT02 >= '0' && cT02 <= '9') {
            long j8 = cT02 - '0';
            while (true) {
                i9 = i8 + 1;
                cT0 = t0(this.f4883k + i8);
                if (cT0 < '0' || cT0 > '9') {
                    break;
                }
                j8 = (j8 * 10) + ((long) (cT0 - '0'));
                i8 = i9;
            }
            if (cT0 == '.') {
                this.f4892t = -1;
                return 0L;
            }
            if (!(j8 >= 0 || (j8 == Long.MIN_VALUE && z8))) {
                throw new NumberFormatException(e1(this.f4883k, i9 - 1));
            }
            if (!z7) {
                c9 = c8;
            } else {
                if (cT0 != '\"') {
                    this.f4892t = -1;
                    return 0L;
                }
                cT0 = t0(this.f4883k + i9);
                c9 = c8;
                i9++;
            }
            while (cT0 != c9) {
                if (!z0(cT0)) {
                    this.f4892t = -1;
                    return j8;
                }
                cT0 = t0(this.f4883k + i9);
                i9++;
            }
            int i10 = this.f4883k + i9;
            this.f4883k = i10;
            this.f4882j = t0(i10);
            this.f4892t = 3;
            this.f4879g = 16;
            return z8 ? -j8 : j8;
        }
        if (cT02 != 'n' || t0(this.f4883k + i8) != 'u' || t0(this.f4883k + i8 + 1) != 'l' || t0(this.f4883k + i8 + 2) != 'l') {
            this.f4892t = -1;
            return 0L;
        }
        this.f4892t = 5;
        int i11 = i8 + 3;
        int i12 = i11 + 1;
        char cT03 = t0(this.f4883k + i11);
        if (z7 && cT03 == '\"') {
            int i13 = i12 + 1;
            cT03 = t0(this.f4883k + i12);
            i12 = i13;
        }
        while (cT03 != ',') {
            if (cT03 == ']') {
                int i14 = this.f4883k + i12;
                this.f4883k = i14;
                this.f4882j = t0(i14);
                this.f4892t = 5;
                this.f4879g = 15;
                return 0L;
            }
            if (!z0(cT03)) {
                this.f4892t = -1;
                return 0L;
            }
            int i15 = i12 + 1;
            cT03 = t0(this.f4883k + i12);
            i12 = i15;
        }
        int i16 = this.f4883k + i12;
        this.f4883k = i16;
        this.f4882j = t0(i16);
        this.f4892t = 5;
        this.f4879g = 16;
        return 0L;
    }

    public String T0(char[] cArr) {
        this.f4892t = 0;
        if (!s0(cArr)) {
            this.f4892t = -2;
            return d1();
        }
        int length = cArr.length;
        int i8 = length + 1;
        if (t0(this.f4883k + length) != '\"') {
            this.f4892t = -1;
            return d1();
        }
        int iX0 = x0('\"', this.f4883k + cArr.length + 1);
        if (iX0 == -1) {
            throw new JSONException("unclosed str");
        }
        int length2 = this.f4883k + cArr.length + 1;
        String strE1 = e1(length2, iX0 - length2);
        if (strE1.indexOf(92) != -1) {
            while (true) {
                int i9 = 0;
                for (int i10 = iX0 - 1; i10 >= 0 && t0(i10) == '\\'; i10--) {
                    i9++;
                }
                if (i9 % 2 == 0) {
                    break;
                }
                iX0 = x0('\"', iX0 + 1);
            }
            int i11 = this.f4883k;
            int length3 = iX0 - ((cArr.length + i11) + 1);
            strE1 = F0(f1(i11 + cArr.length + 1, length3), length3);
        }
        int i12 = this.f4883k;
        int length4 = i8 + (iX0 - ((cArr.length + i12) + 1)) + 1;
        int i13 = length4 + 1;
        char cT0 = t0(i12 + length4);
        if (cT0 == ',') {
            int i14 = this.f4883k + i13;
            this.f4883k = i14;
            this.f4882j = t0(i14);
            this.f4892t = 3;
            return strE1;
        }
        if (cT0 != '}') {
            this.f4892t = -1;
            return d1();
        }
        int i15 = i13 + 1;
        char cT02 = t0(this.f4883k + i13);
        if (cT02 == ',') {
            this.f4879g = 16;
            int i16 = this.f4883k + i15;
            this.f4883k = i16;
            this.f4882j = t0(i16);
        } else if (cT02 == ']') {
            this.f4879g = 15;
            int i17 = this.f4883k + i15;
            this.f4883k = i17;
            this.f4882j = t0(i17);
        } else if (cT02 == '}') {
            this.f4879g = 13;
            int i18 = this.f4883k + i15;
            this.f4883k = i18;
            this.f4882j = t0(i18);
        } else {
            if (cT02 != 26) {
                this.f4892t = -1;
                return d1();
            }
            this.f4879g = 20;
            this.f4883k += i15 - 1;
            this.f4882j = (char) 26;
        }
        this.f4892t = 4;
        return strE1;
    }

    public final void U0() {
        char next;
        if (this.f4882j != 'x') {
            throw new JSONException("illegal state. " + this.f4882j);
        }
        next();
        if (this.f4882j != '\'') {
            throw new JSONException("illegal state. " + this.f4882j);
        }
        this.f4887o = this.f4883k;
        next();
        if (this.f4882j == '\'') {
            next();
            this.f4879g = 26;
            return;
        }
        while (true) {
            next = next();
            if ((next < '0' || next > '9') && (next < 'A' || next > 'F')) {
                break;
            } else {
                this.f4886n++;
            }
        }
        if (next == '\'') {
            this.f4886n++;
            next();
            this.f4879g = 26;
        } else {
            throw new JSONException("illegal state. " + next);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:108:0x007b A[SYNTHETIC] */
    @Override // f2.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void V(int r11) {
        /*
            Method dump skipped, instruction units count: 270
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: f2.c.V(int):void");
    }

    public final void V0() {
        this.f4887o = this.f4883k - 1;
        this.f4888p = false;
        do {
            this.f4886n++;
            next();
        } while (Character.isLetterOrDigit(this.f4882j));
        String strN0 = n0();
        if ("null".equalsIgnoreCase(strN0)) {
            this.f4879g = 8;
            return;
        }
        if ("new".equals(strN0)) {
            this.f4879g = 9;
            return;
        }
        if ("true".equals(strN0)) {
            this.f4879g = 6;
            return;
        }
        if ("false".equals(strN0)) {
            this.f4879g = 7;
            return;
        }
        if ("undefined".equals(strN0)) {
            this.f4879g = 23;
            return;
        }
        if ("Set".equals(strN0)) {
            this.f4879g = 21;
        } else if ("TreeSet".equals(strN0)) {
            this.f4879g = 22;
        } else {
            this.f4879g = 18;
        }
    }

    public final void W0() {
        X0(true);
    }

    @Override // f2.b
    public final String X(i iVar, char c8) {
        String strC;
        this.f4887o = this.f4883k;
        this.f4886n = 0;
        boolean z7 = false;
        int i8 = 0;
        while (true) {
            char next = next();
            if (next == c8) {
                this.f4879g = 4;
                if (z7) {
                    strC = iVar.c(this.f4885m, 0, this.f4886n, i8);
                } else {
                    int i9 = this.f4887o;
                    strC = q0(i9 == -1 ? 0 : i9 + 1, this.f4886n, i8, iVar);
                }
                this.f4886n = 0;
                next();
                return strC;
            }
            if (next == 26) {
                throw new JSONException("unclosed.str");
            }
            if (next == '\\') {
                if (!z7) {
                    int i10 = this.f4886n;
                    char[] cArr = this.f4885m;
                    if (i10 >= cArr.length) {
                        int length = cArr.length * 2;
                        if (i10 <= length) {
                            i10 = length;
                        }
                        char[] cArr2 = new char[i10];
                        System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
                        this.f4885m = cArr2;
                    }
                    r0(this.f4887o + 1, this.f4885m, 0, this.f4886n);
                    z7 = true;
                }
                char next2 = next();
                if (next2 == '\"') {
                    i8 = (i8 * 31) + 34;
                    E0('\"');
                } else if (next2 != '\'') {
                    if (next2 != 'F') {
                        if (next2 == '\\') {
                            i8 = (i8 * 31) + 92;
                            E0('\\');
                        } else if (next2 == 'b') {
                            i8 = (i8 * 31) + 8;
                            E0('\b');
                        } else if (next2 != 'f') {
                            if (next2 == 'n') {
                                i8 = (i8 * 31) + 10;
                                E0('\n');
                            } else if (next2 == 'r') {
                                i8 = (i8 * 31) + 13;
                                E0('\r');
                            } else if (next2 != 'x') {
                                switch (next2) {
                                    case '/':
                                        i8 = (i8 * 31) + 47;
                                        E0('/');
                                        break;
                                    case '0':
                                        i8 = (i8 * 31) + next2;
                                        E0((char) 0);
                                        break;
                                    case '1':
                                        i8 = (i8 * 31) + next2;
                                        E0((char) 1);
                                        break;
                                    case '2':
                                        i8 = (i8 * 31) + next2;
                                        E0((char) 2);
                                        break;
                                    case '3':
                                        i8 = (i8 * 31) + next2;
                                        E0((char) 3);
                                        break;
                                    case '4':
                                        i8 = (i8 * 31) + next2;
                                        E0((char) 4);
                                        break;
                                    case '5':
                                        i8 = (i8 * 31) + next2;
                                        E0((char) 5);
                                        break;
                                    case '6':
                                        i8 = (i8 * 31) + next2;
                                        E0((char) 6);
                                        break;
                                    case '7':
                                        i8 = (i8 * 31) + next2;
                                        E0((char) 7);
                                        break;
                                    default:
                                        switch (next2) {
                                            case 't':
                                                i8 = (i8 * 31) + 9;
                                                E0('\t');
                                                break;
                                            case 'u':
                                                int i11 = Integer.parseInt(new String(new char[]{next(), next(), next(), next()}), 16);
                                                i8 = (i8 * 31) + i11;
                                                E0((char) i11);
                                                break;
                                            case 'v':
                                                i8 = (i8 * 31) + 11;
                                                E0((char) 11);
                                                break;
                                            default:
                                                this.f4882j = next2;
                                                throw new JSONException("unclosed.str.lit");
                                        }
                                        break;
                                }
                            } else {
                                char next3 = next();
                                this.f4882j = next3;
                                char next4 = next();
                                this.f4882j = next4;
                                int[] iArr = f4878f;
                                char c9 = (char) ((iArr[next3] * 16) + iArr[next4]);
                                i8 = (i8 * 31) + c9;
                                E0(c9);
                            }
                        }
                    }
                    i8 = (i8 * 31) + 12;
                    E0('\f');
                } else {
                    i8 = (i8 * 31) + 39;
                    E0('\'');
                }
            } else {
                i8 = (i8 * 31) + next;
                if (z7) {
                    int i12 = this.f4886n;
                    char[] cArr3 = this.f4885m;
                    if (i12 == cArr3.length) {
                        E0(next);
                    } else {
                        this.f4886n = i12 + 1;
                        cArr3[i12] = next;
                    }
                } else {
                    this.f4886n++;
                }
            }
        }
    }

    public final void X0(boolean z7) {
        if (this.f4882j != 'n') {
            throw new JSONException("error parse null or new");
        }
        next();
        char c8 = this.f4882j;
        if (c8 != 'u') {
            if (c8 != 'e') {
                throw new JSONException("error parse new");
            }
            next();
            if (this.f4882j != 'w') {
                throw new JSONException("error parse new");
            }
            next();
            char c9 = this.f4882j;
            if (c9 != ' ' && c9 != ',' && c9 != '}' && c9 != ']' && c9 != '\n' && c9 != '\r' && c9 != '\t' && c9 != 26 && c9 != '\f' && c9 != '\b') {
                throw new JSONException("scan new error");
            }
            this.f4879g = 9;
            return;
        }
        next();
        if (this.f4882j != 'l') {
            throw new JSONException("error parse null");
        }
        next();
        if (this.f4882j != 'l') {
            throw new JSONException("error parse null");
        }
        next();
        char c10 = this.f4882j;
        if (c10 != ' ' && c10 != ',' && c10 != '}' && c10 != ']' && c10 != '\n' && c10 != '\r' && c10 != '\t' && c10 != 26 && ((c10 != ':' || !z7) && c10 != '\f' && c10 != '\b')) {
            throw new JSONException("scan null error");
        }
        this.f4879g = 8;
    }

    @Override // f2.b
    public final void Y() {
        D0(':');
    }

    public final void Y0() {
        char next;
        char next2;
        this.f4887o = this.f4883k;
        this.f4888p = false;
        while (true) {
            char next3 = next();
            if (next3 == '\'') {
                this.f4879g = 4;
                next();
                return;
            }
            if (next3 != 26) {
                boolean z7 = true;
                if (next3 == '\\') {
                    if (!this.f4888p) {
                        this.f4888p = true;
                        int i8 = this.f4886n;
                        char[] cArr = this.f4885m;
                        if (i8 > cArr.length) {
                            char[] cArr2 = new char[i8 * 2];
                            System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
                            this.f4885m = cArr2;
                        }
                        u0(this.f4887o + 1, this.f4886n, this.f4885m);
                    }
                    char next4 = next();
                    if (next4 == '\"') {
                        E0('\"');
                    } else if (next4 != '\'') {
                        if (next4 != 'F') {
                            if (next4 == '\\') {
                                E0('\\');
                            } else if (next4 == 'b') {
                                E0('\b');
                            } else if (next4 != 'f') {
                                if (next4 == 'n') {
                                    E0('\n');
                                } else if (next4 == 'r') {
                                    E0('\r');
                                } else if (next4 != 'x') {
                                    switch (next4) {
                                        case '/':
                                            E0('/');
                                            break;
                                        case '0':
                                            E0((char) 0);
                                            break;
                                        case '1':
                                            E0((char) 1);
                                            break;
                                        case '2':
                                            E0((char) 2);
                                            break;
                                        case '3':
                                            E0((char) 3);
                                            break;
                                        case '4':
                                            E0((char) 4);
                                            break;
                                        case '5':
                                            E0((char) 5);
                                            break;
                                        case '6':
                                            E0((char) 6);
                                            break;
                                        case '7':
                                            E0((char) 7);
                                            break;
                                        default:
                                            switch (next4) {
                                                case 't':
                                                    E0('\t');
                                                    break;
                                                case 'u':
                                                    E0((char) Integer.parseInt(new String(new char[]{next(), next(), next(), next()}), 16));
                                                    break;
                                                case 'v':
                                                    E0((char) 11);
                                                    break;
                                                default:
                                                    this.f4882j = next4;
                                                    throw new JSONException("unclosed single-quote string");
                                            }
                                            break;
                                    }
                                } else {
                                    next = next();
                                    next2 = next();
                                    boolean z8 = (next >= '0' && next <= '9') || (next >= 'a' && next <= 'f') || (next >= 'A' && next <= 'F');
                                    if ((next2 < '0' || next2 > '9') && ((next2 < 'a' || next2 > 'f') && (next2 < 'A' || next2 > 'F'))) {
                                        z7 = false;
                                    }
                                    if (z8 && z7) {
                                        int[] iArr = f4878f;
                                        E0((char) ((iArr[next] * 16) + iArr[next2]));
                                    }
                                }
                            }
                        }
                        E0('\f');
                    } else {
                        E0('\'');
                    }
                } else if (this.f4888p) {
                    int i9 = this.f4886n;
                    char[] cArr3 = this.f4885m;
                    if (i9 == cArr3.length) {
                        E0(next3);
                    } else {
                        this.f4886n = i9 + 1;
                        cArr3[i9] = next3;
                    }
                } else {
                    this.f4886n++;
                }
            } else {
                if (y0()) {
                    throw new JSONException("unclosed single-quote string");
                }
                E0((char) 26);
            }
        }
        throw new JSONException("invalid escape character \\x" + next + next2);
    }

    @Override // f2.b
    public abstract BigDecimal Z();

    public String Z0(i iVar, char c8) {
        int i8 = 0;
        this.f4892t = 0;
        char cT0 = t0(this.f4883k + 0);
        if (cT0 == 'n') {
            if (t0(this.f4883k + 1) != 'u' || t0(this.f4883k + 1 + 1) != 'l' || t0(this.f4883k + 1 + 2) != 'l') {
                this.f4892t = -1;
                return null;
            }
            if (t0(this.f4883k + 4) != c8) {
                this.f4892t = -1;
                return null;
            }
            int i9 = this.f4883k + 5;
            this.f4883k = i9;
            this.f4882j = t0(i9);
            this.f4892t = 3;
            return null;
        }
        if (cT0 != '\"') {
            this.f4892t = -1;
            return null;
        }
        int i10 = 1;
        while (true) {
            int i11 = i10 + 1;
            char cT02 = t0(this.f4883k + i10);
            if (cT02 == '\"') {
                int i12 = this.f4883k;
                int i13 = i12 + 0 + 1;
                String strQ0 = q0(i13, ((i12 + i11) - i13) - 1, i8, iVar);
                int i14 = i11 + 1;
                char cT03 = t0(this.f4883k + i11);
                while (cT03 != c8) {
                    if (!z0(cT03)) {
                        this.f4892t = -1;
                        return strQ0;
                    }
                    cT03 = t0(this.f4883k + i14);
                    i14++;
                }
                int i15 = this.f4883k + i14;
                this.f4883k = i15;
                this.f4882j = t0(i15);
                this.f4892t = 3;
                return strQ0;
            }
            i8 = (i8 * 31) + cT02;
            if (cT02 == '\\') {
                this.f4892t = -1;
                return null;
            }
            i10 = i11;
        }
    }

    @Override // f2.b
    public int a0(char c8) {
        int i8;
        int i9;
        char cT0;
        this.f4892t = 0;
        char cT02 = t0(this.f4883k + 0);
        boolean z7 = cT02 == '\"';
        if (z7) {
            cT02 = t0(this.f4883k + 1);
            i8 = 2;
        } else {
            i8 = 1;
        }
        boolean z8 = cT02 == '-';
        if (z8) {
            cT02 = t0(this.f4883k + i8);
            i8++;
        }
        if (cT02 >= '0' && cT02 <= '9') {
            int i10 = cT02 - '0';
            while (true) {
                i9 = i8 + 1;
                cT0 = t0(this.f4883k + i8);
                if (cT0 < '0' || cT0 > '9') {
                    break;
                }
                i10 = (i10 * 10) + (cT0 - '0');
                i8 = i9;
            }
            if (cT0 == '.') {
                this.f4892t = -1;
                return 0;
            }
            if (i10 < 0) {
                this.f4892t = -1;
                return 0;
            }
            while (cT0 != c8) {
                if (!z0(cT0)) {
                    this.f4892t = -1;
                    return z8 ? -i10 : i10;
                }
                char cT03 = t0(this.f4883k + i9);
                i9++;
                cT0 = cT03;
            }
            int i11 = this.f4883k + i9;
            this.f4883k = i11;
            this.f4882j = t0(i11);
            this.f4892t = 3;
            this.f4879g = 16;
            return z8 ? -i10 : i10;
        }
        if (cT02 != 'n' || t0(this.f4883k + i8) != 'u' || t0(this.f4883k + i8 + 1) != 'l' || t0(this.f4883k + i8 + 2) != 'l') {
            this.f4892t = -1;
            return 0;
        }
        this.f4892t = 5;
        int i12 = i8 + 3;
        int i13 = i12 + 1;
        char cT04 = t0(this.f4883k + i12);
        if (z7 && cT04 == '\"') {
            int i14 = i13 + 1;
            cT04 = t0(this.f4883k + i13);
            i13 = i14;
        }
        while (cT04 != ',') {
            if (cT04 == ']') {
                int i15 = this.f4883k + i13;
                this.f4883k = i15;
                this.f4882j = t0(i15);
                this.f4892t = 5;
                this.f4879g = 15;
                return 0;
            }
            if (!z0(cT04)) {
                this.f4892t = -1;
                return 0;
            }
            int i16 = i13 + 1;
            cT04 = t0(this.f4883k + i13);
            i13 = i16;
        }
        int i17 = this.f4883k + i13;
        this.f4883k = i17;
        this.f4882j = t0(i17);
        this.f4892t = 5;
        this.f4879g = 16;
        return 0;
    }

    public final void a1() {
        if (this.f4882j != 't') {
            throw new JSONException("error parse true");
        }
        next();
        if (this.f4882j != 'r') {
            throw new JSONException("error parse true");
        }
        next();
        if (this.f4882j != 'u') {
            throw new JSONException("error parse true");
        }
        next();
        if (this.f4882j != 'e') {
            throw new JSONException("error parse true");
        }
        next();
        char c8 = this.f4882j;
        if (c8 != ' ' && c8 != ',' && c8 != '}' && c8 != ']' && c8 != '\n' && c8 != '\r' && c8 != '\t' && c8 != 26 && c8 != '\f' && c8 != '\b' && c8 != ':' && c8 != '/') {
            throw new JSONException("scan true error");
        }
        this.f4879g = 6;
    }

    public void b1(TimeZone timeZone) {
        this.f4890r = timeZone;
    }

    @Override // f2.b
    public final String c0() {
        return f.a(this.f4879g);
    }

    public void c1() {
        char c8;
        next();
        char c9 = this.f4882j;
        if (c9 == '/') {
            do {
                next();
                c8 = this.f4882j;
                if (c8 == '\n') {
                    next();
                    return;
                }
            } while (c8 != 26);
            return;
        }
        if (c9 != '*') {
            throw new JSONException("invalid comment");
        }
        next();
        while (true) {
            char c10 = this.f4882j;
            if (c10 == 26) {
                return;
            }
            if (c10 == '*') {
                next();
                if (this.f4882j == '/') {
                    next();
                    return;
                }
            } else {
                next();
            }
        }
    }

    @Override // f2.b, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        char[] cArr = this.f4885m;
        if (cArr.length <= 8192) {
            f4876d.set(cArr);
        }
        this.f4885m = null;
    }

    @Override // f2.b
    public final Number d0(boolean z7) {
        char cT0 = t0((this.f4887o + this.f4886n) - 1);
        try {
            return cT0 == 'F' ? Float.valueOf(Float.parseFloat(l0())) : cT0 == 'D' ? Double.valueOf(Double.parseDouble(l0())) : z7 ? Z() : Double.valueOf(v0());
        } catch (NumberFormatException e8) {
            throw new JSONException(e8.getMessage() + ", " + g());
        }
    }

    public final String d1() {
        return this.f4893u;
    }

    @Override // f2.b
    public final int e() {
        return this.f4880h;
    }

    public abstract String e1(int i8, int i9);

    public abstract char[] f1(int i8, int i9);

    @Override // f2.b
    public String g() {
        return "";
    }

    @Override // f2.b
    public final String g0(i iVar) {
        if (this.f4879g == 1 && this.f4880h == 0 && this.f4883k == 1) {
            this.f4883k = 0;
        }
        boolean[] zArr = l2.e.f6195d;
        int i8 = this.f4882j;
        if (!(i8 >= zArr.length || zArr[i8])) {
            throw new JSONException("illegal identifier : " + this.f4882j + g());
        }
        boolean[] zArr2 = l2.e.f6196e;
        this.f4887o = this.f4883k;
        this.f4886n = 1;
        while (true) {
            char next = next();
            if (next < zArr2.length && !zArr2[next]) {
                break;
            }
            i8 = (i8 * 31) + next;
            this.f4886n++;
        }
        this.f4882j = t0(this.f4883k);
        this.f4879g = 18;
        if (this.f4886n == 4 && i8 == 3392903 && t0(this.f4887o) == 'n' && t0(this.f4887o + 1) == 'u' && t0(this.f4887o + 2) == 'l' && t0(this.f4887o + 3) == 'l') {
            return null;
        }
        return iVar == null ? e1(this.f4887o, this.f4886n) : q0(this.f4887o, this.f4886n, i8, iVar);
    }

    @Override // f2.b
    public Locale h0() {
        return this.f4891s;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0084  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x005b -> B:12:0x0032). Please report as a decompilation issue!!! */
    @Override // f2.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long i() throws java.lang.NumberFormatException {
        /*
            r15 = this;
            int r0 = r15.f4887o
            r1 = 0
            r2 = -1
            if (r0 != r2) goto L8
            r15.f4887o = r1
        L8:
            int r0 = r15.f4887o
            int r2 = r15.f4886n
            int r2 = r2 + r0
            char r3 = r15.t0(r0)
            r4 = 45
            r5 = 1
            if (r3 != r4) goto L1c
            r3 = -9223372036854775808
            int r0 = r0 + 1
            r1 = r5
            goto L21
        L1c:
            r3 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L21:
            r6 = -922337203685477580(0xf333333333333334, double:-8.390303882365713E246)
            if (r0 >= r2) goto L34
            int r8 = r0 + 1
            char r0 = r15.t0(r0)
            int r0 = r0 + (-48)
            int r0 = -r0
            long r9 = (long) r0
        L32:
            r0 = r8
            goto L36
        L34:
            r9 = 0
        L36:
            if (r0 >= r2) goto L72
            int r8 = r0 + 1
            char r0 = r15.t0(r0)
            r11 = 76
            if (r0 == r11) goto L71
            r11 = 83
            if (r0 == r11) goto L71
            r11 = 66
            if (r0 != r11) goto L4b
            goto L71
        L4b:
            int r0 = r0 + (-48)
            int r11 = (r9 > r6 ? 1 : (r9 == r6 ? 0 : -1))
            if (r11 < 0) goto L67
            r11 = 10
            long r9 = r9 * r11
            long r11 = (long) r0
            long r13 = r3 + r11
            int r0 = (r9 > r13 ? 1 : (r9 == r13 ? 0 : -1))
            if (r0 < 0) goto L5d
            long r9 = r9 - r11
            goto L32
        L5d:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.String r1 = r15.l0()
            r0.<init>(r1)
            throw r0
        L67:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.String r1 = r15.l0()
            r0.<init>(r1)
            throw r0
        L71:
            r0 = r8
        L72:
            if (r1 == 0) goto L84
            int r1 = r15.f4887o
            int r1 = r1 + r5
            if (r0 <= r1) goto L7a
            return r9
        L7a:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.String r1 = r15.l0()
            r0.<init>(r1)
            throw r0
        L84:
            long r0 = -r9
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: f2.c.i():long");
    }

    @Override // f2.b
    public final boolean isEnabled(int i8) {
        return (i8 & this.f4881i) != 0;
    }

    @Override // f2.b
    public final Number j() throws NumberFormatException {
        long j8;
        long j9;
        boolean z7 = false;
        if (this.f4887o == -1) {
            this.f4887o = 0;
        }
        int i8 = this.f4887o;
        int i9 = this.f4886n + i8;
        char c8 = ' ';
        char cT0 = t0(i9 - 1);
        if (cT0 == 'B') {
            i9--;
            c8 = 'B';
        } else if (cT0 == 'L') {
            i9--;
            c8 = 'L';
        } else if (cT0 == 'S') {
            i9--;
            c8 = 'S';
        }
        if (t0(this.f4887o) == '-') {
            j8 = Long.MIN_VALUE;
            i8++;
            z7 = true;
        } else {
            j8 = -9223372036854775807L;
        }
        long j10 = -922337203685477580L;
        if (i8 < i9) {
            j9 = -(t0(i8) - '0');
            i8++;
        } else {
            j9 = 0;
        }
        while (i8 < i9) {
            int i10 = i8 + 1;
            int iT0 = t0(i8) - '0';
            if (j9 < j10) {
                return new BigInteger(l0(), 10);
            }
            long j11 = j9 * 10;
            long j12 = iT0;
            if (j11 < j8 + j12) {
                return new BigInteger(l0(), 10);
            }
            j9 = j11 - j12;
            i8 = i10;
            j10 = -922337203685477580L;
        }
        if (!z7) {
            long j13 = -j9;
            return (j13 > 2147483647L || c8 == 'L') ? Long.valueOf(j13) : c8 == 'S' ? Short.valueOf((short) j13) : c8 == 'B' ? Byte.valueOf((byte) j13) : Integer.valueOf((int) j13);
        }
        if (i8 > this.f4887o + 1) {
            return (j9 < -2147483648L || c8 == 'L') ? Long.valueOf(j9) : c8 == 'S' ? Short.valueOf((short) j9) : c8 == 'B' ? Byte.valueOf((byte) j9) : Integer.valueOf((int) j9);
        }
        throw new NumberFormatException(l0());
    }

    @Override // f2.b
    public final boolean k0() {
        return this.f4886n == 4 && t0(this.f4887o + 1) == '$' && t0(this.f4887o + 2) == 'r' && t0(this.f4887o + 3) == 'e' && t0(this.f4887o + 4) == 'f';
    }

    @Override // f2.b
    public abstract String l0();

    @Override // f2.b
    public float m() {
        char cCharAt;
        String strL0 = l0();
        float f8 = Float.parseFloat(strL0);
        if ((f8 != 0.0f && f8 != Float.POSITIVE_INFINITY) || (cCharAt = strL0.charAt(0)) <= '0' || cCharAt > '9') {
            return f8;
        }
        throw new JSONException("float overflow : " + strL0);
    }

    @Override // f2.b
    public final void m0(int i8) {
        D0(':');
    }

    @Override // f2.b
    public Enum<?> n(Class<?> cls, i iVar, char c8) {
        String strZ0 = Z0(iVar, c8);
        if (strZ0 == null) {
            return null;
        }
        return Enum.valueOf(cls, strZ0);
    }

    @Override // f2.b
    public abstract String n0();

    @Override // f2.b
    public abstract char next();

    @Override // f2.b
    public boolean o() {
        int i8 = 0;
        while (true) {
            char cT0 = t0(i8);
            if (cT0 == 26) {
                this.f4879g = 20;
                return true;
            }
            if (!z0(cT0)) {
                return false;
            }
            i8++;
        }
    }

    @Override // f2.b
    public TimeZone o0() {
        return this.f4890r;
    }

    @Override // f2.b
    public final int q() {
        return this.f4879g;
    }

    public abstract String q0(int i8, int i9, int i10, i iVar);

    @Override // f2.b
    public String r(char c8) {
        this.f4892t = 0;
        char cT0 = t0(this.f4883k + 0);
        if (cT0 == 'n') {
            if (t0(this.f4883k + 1) != 'u' || t0(this.f4883k + 1 + 1) != 'l' || t0(this.f4883k + 1 + 2) != 'l') {
                this.f4892t = -1;
                return null;
            }
            if (t0(this.f4883k + 4) != c8) {
                this.f4892t = -1;
                return null;
            }
            int i8 = this.f4883k + 5;
            this.f4883k = i8;
            this.f4882j = t0(i8);
            this.f4892t = 3;
            return null;
        }
        int i9 = 1;
        while (cT0 != '\"') {
            if (!z0(cT0)) {
                this.f4892t = -1;
                return d1();
            }
            cT0 = t0(this.f4883k + i9);
            i9++;
        }
        int i10 = this.f4883k + i9;
        int iX0 = x0('\"', i10);
        if (iX0 == -1) {
            throw new JSONException("unclosed str");
        }
        String strE1 = e1(this.f4883k + i9, iX0 - i10);
        if (strE1.indexOf(92) != -1) {
            while (true) {
                int i11 = 0;
                for (int i12 = iX0 - 1; i12 >= 0 && t0(i12) == '\\'; i12--) {
                    i11++;
                }
                if (i11 % 2 == 0) {
                    break;
                }
                iX0 = x0('\"', iX0 + 1);
            }
            int i13 = iX0 - i10;
            strE1 = F0(f1(this.f4883k + 1, i13), i13);
        }
        int i14 = i9 + (iX0 - i10) + 1;
        int i15 = i14 + 1;
        char cT02 = t0(this.f4883k + i14);
        while (cT02 != c8) {
            if (!z0(cT02)) {
                if (cT02 == ']') {
                    int i16 = this.f4883k + i15;
                    this.f4883k = i16;
                    this.f4882j = t0(i16);
                    this.f4892t = -1;
                }
                return strE1;
            }
            cT02 = t0(this.f4883k + i15);
            i15++;
        }
        int i17 = this.f4883k + i15;
        this.f4883k = i17;
        this.f4882j = t0(i17);
        this.f4892t = 3;
        this.f4879g = 16;
        return strE1;
    }

    public abstract void r0(int i8, char[] cArr, int i9, int i10);

    public abstract boolean s0(char[] cArr);

    /* JADX WARN: Removed duplicated region for block: B:37:0x00ab  */
    @Override // f2.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean t(char r10) {
        /*
            r9 = this;
            r0 = 0
            r9.f4892t = r0
            int r1 = r9.f4883k
            int r1 = r1 + r0
            char r1 = r9.t0(r1)
            r2 = 3
            r3 = 5
            r4 = 101(0x65, float:1.42E-43)
            r5 = -1
            r6 = 2
            r7 = 1
            r8 = 116(0x74, float:1.63E-43)
            if (r1 != r8) goto L42
            int r1 = r9.f4883k
            int r1 = r1 + r7
            char r1 = r9.t0(r1)
            r8 = 114(0x72, float:1.6E-43)
            if (r1 != r8) goto L3f
            int r1 = r9.f4883k
            int r1 = r1 + r7
            int r1 = r1 + r7
            char r1 = r9.t0(r1)
            r8 = 117(0x75, float:1.64E-43)
            if (r1 != r8) goto L3f
            int r1 = r9.f4883k
            int r1 = r1 + r7
            int r1 = r1 + r6
            char r1 = r9.t0(r1)
            if (r1 != r4) goto L3f
            int r0 = r9.f4883k
            int r0 = r0 + 4
            char r1 = r9.t0(r0)
            goto L8b
        L3f:
            r9.f4892t = r5
            return r0
        L42:
            r8 = 102(0x66, float:1.43E-43)
            if (r1 != r8) goto L7f
            int r1 = r9.f4883k
            int r1 = r1 + r7
            char r1 = r9.t0(r1)
            r8 = 97
            if (r1 != r8) goto L7c
            int r1 = r9.f4883k
            int r1 = r1 + r7
            int r1 = r1 + r7
            char r1 = r9.t0(r1)
            r8 = 108(0x6c, float:1.51E-43)
            if (r1 != r8) goto L7c
            int r1 = r9.f4883k
            int r1 = r1 + r7
            int r1 = r1 + r6
            char r1 = r9.t0(r1)
            r6 = 115(0x73, float:1.61E-43)
            if (r1 != r6) goto L7c
            int r1 = r9.f4883k
            int r1 = r1 + r7
            int r1 = r1 + r2
            char r1 = r9.t0(r1)
            if (r1 != r4) goto L7c
            int r1 = r9.f4883k
            r4 = 6
            int r1 = r1 + r3
            char r1 = r9.t0(r1)
            goto Lba
        L7c:
            r9.f4892t = r5
            return r0
        L7f:
            r3 = 49
            if (r1 != r3) goto L8d
            int r0 = r9.f4883k
            int r0 = r0 + r7
            char r1 = r9.t0(r0)
            r3 = r6
        L8b:
            r0 = r7
            goto L9b
        L8d:
            r3 = 48
            if (r1 != r3) goto L9a
            int r1 = r9.f4883k
            int r1 = r1 + r7
            char r1 = r9.t0(r1)
            r3 = r6
            goto L9b
        L9a:
            r3 = r7
        L9b:
            if (r1 != r10) goto Lab
            int r10 = r9.f4883k
            int r10 = r10 + r3
            r9.f4883k = r10
            char r10 = r9.t0(r10)
            r9.f4882j = r10
            r9.f4892t = r2
            return r0
        Lab:
            boolean r1 = z0(r1)
            if (r1 == 0) goto Lbc
            int r1 = r9.f4883k
            int r4 = r3 + 1
            int r1 = r1 + r3
            char r1 = r9.t0(r1)
        Lba:
            r3 = r4
            goto L9b
        Lbc:
            r9.f4892t = r5
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: f2.c.t(char):boolean");
    }

    public abstract char t0(int i8);

    public abstract void u0(int i8, int i9, char[] cArr);

    @Override // f2.b
    public String v(i iVar) {
        return null;
    }

    public double v0() {
        return Double.parseDouble(l0());
    }

    @Override // f2.b
    public final String w(i iVar) {
        O();
        char c8 = this.f4882j;
        if (c8 == '\"') {
            return X(iVar, '\"');
        }
        if (c8 == '\'') {
            if (M(Feature.AllowSingleQuotes)) {
                return X(iVar, '\'');
            }
            throw new JSONException("syntax error");
        }
        if (c8 == '}') {
            next();
            this.f4879g = 13;
            return null;
        }
        if (c8 == ',') {
            next();
            this.f4879g = 16;
            return null;
        }
        if (c8 == 26) {
            this.f4879g = 20;
            return null;
        }
        if (M(Feature.AllowUnQuotedFieldNames)) {
            return g0(iVar);
        }
        throw new JSONException("syntax error");
    }

    public Calendar w0() {
        return this.f4889q;
    }

    @Override // f2.b
    public int x() {
        return this.f4881i;
    }

    public abstract int x0(char c8, int i8);

    public abstract boolean y0();
}
