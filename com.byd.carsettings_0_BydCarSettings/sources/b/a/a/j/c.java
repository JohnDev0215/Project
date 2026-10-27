package b.a.a.j;

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

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ThreadLocal<char[]> f239a = new ThreadLocal<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final char[] f240b = ("\"" + b.a.a.a.DEFAULT_TYPE_KEY + "\":\"").toCharArray();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f241c = new int[103];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f242d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f243e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f244f;
    public char g;
    public int h;
    public int i;
    public char[] j;
    public int k;
    public int l;
    public boolean m;
    public String r;
    public Calendar n = null;
    public TimeZone o = b.a.a.a.defaultTimeZone;
    public Locale p = b.a.a.a.defaultLocale;
    public int q = 0;
    public int s = 0;

    static {
        for (int i = 48; i <= 57; i++) {
            f241c[i] = i - 48;
        }
        for (int i2 = 97; i2 <= 102; i2++) {
            f241c[i2] = (i2 - 97) + 10;
        }
        for (int i3 = 65; i3 <= 70; i3++) {
            f241c[i3] = (i3 - 65) + 10;
        }
    }

    public c(int i) {
        this.r = null;
        this.f244f = i;
        if ((i & Feature.InitStringFieldAsEmpty.mask) != 0) {
            this.r = "";
        }
        char[] cArr = f239a.get();
        this.j = cArr;
        if (cArr == null) {
            this.j = new char[512];
        }
    }

    public static boolean g0(char c2) {
        return c2 <= ' ' && (c2 == ' ' || c2 == '\n' || c2 == '\r' || c2 == '\t' || c2 == '\f' || c2 == '\b');
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00e8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m0(char[] r12, int r13) {
        /*
            Method dump skipped, instruction units count: 290
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.a.j.c.m0(char[], int):java.lang.String");
    }

    @Override // b.a.a.j.b
    public final void A() {
        while (true) {
            char c2 = this.g;
            if (c2 > '/') {
                return;
            }
            if (c2 == ' ' || c2 == '\r' || c2 == '\n' || c2 == '\t' || c2 == '\f' || c2 == '\b') {
                next();
            } else if (c2 != '/') {
                return;
            } else {
                J0();
            }
        }
    }

    public String A0(char[] cArr) {
        this.q = 0;
        if (!Z(cArr)) {
            this.q = -2;
            return K0();
        }
        int length = cArr.length;
        int i = length + 1;
        if (a0(this.h + length) != '\"') {
            this.q = -1;
            return K0();
        }
        int iE0 = e0('\"', this.h + cArr.length + 1);
        if (iE0 == -1) {
            throw new JSONException("unclosed str");
        }
        int length2 = this.h + cArr.length + 1;
        String strL0 = L0(length2, iE0 - length2);
        if (strL0.indexOf(92) != -1) {
            while (true) {
                int i2 = 0;
                for (int i3 = iE0 - 1; i3 >= 0 && a0(i3) == '\\'; i3--) {
                    i2++;
                }
                if (i2 % 2 == 0) {
                    break;
                }
                iE0 = e0('\"', iE0 + 1);
            }
            int i4 = this.h;
            int length3 = iE0 - ((cArr.length + i4) + 1);
            strL0 = m0(M0(i4 + cArr.length + 1, length3), length3);
        }
        int i5 = this.h;
        int length4 = i + (iE0 - ((cArr.length + i5) + 1)) + 1;
        int i6 = length4 + 1;
        char cA0 = a0(i5 + length4);
        if (cA0 == ',') {
            int i7 = this.h + i6;
            this.h = i7;
            this.g = a0(i7);
            this.q = 3;
            return strL0;
        }
        if (cA0 != '}') {
            this.q = -1;
            return K0();
        }
        int i8 = i6 + 1;
        char cA02 = a0(this.h + i6);
        if (cA02 == ',') {
            this.f242d = 16;
            int i9 = this.h + i8;
            this.h = i9;
            this.g = a0(i9);
        } else if (cA02 == ']') {
            this.f242d = 15;
            int i10 = this.h + i8;
            this.h = i10;
            this.g = a0(i10);
        } else if (cA02 == '}') {
            this.f242d = 13;
            int i11 = this.h + i8;
            this.h = i11;
            this.g = a0(i11);
        } else {
            if (cA02 != 26) {
                this.q = -1;
                return K0();
            }
            this.f242d = 20;
            this.h += i8 - 1;
            this.g = (char) 26;
        }
        this.q = 4;
        return strL0;
    }

    @Override // b.a.a.j.b
    public final void B() {
        this.k = 0;
    }

    public final void B0() {
        char next;
        if (this.g != 'x') {
            throw new JSONException("illegal state. " + this.g);
        }
        next();
        if (this.g != '\'') {
            throw new JSONException("illegal state. " + this.g);
        }
        this.l = this.h;
        next();
        if (this.g == '\'') {
            next();
            this.f242d = 26;
            return;
        }
        while (true) {
            next = next();
            if ((next < '0' || next > '9') && (next < 'A' || next > 'F')) {
                break;
            } else {
                this.k++;
            }
        }
        if (next == '\'') {
            this.k++;
            next();
            this.f242d = 26;
        } else {
            throw new JSONException("illegal state. " + next);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00c5  */
    @Override // b.a.a.j.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void C() {
        /*
            Method dump skipped, instruction units count: 209
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.a.j.c.C():void");
    }

    public final void C0() {
        this.l = this.h - 1;
        this.m = false;
        do {
            this.k++;
            next();
        } while (Character.isLetterOrDigit(this.g));
        String strV = V();
        if ("null".equalsIgnoreCase(strV)) {
            this.f242d = 8;
            return;
        }
        if ("new".equals(strV)) {
            this.f242d = 9;
            return;
        }
        if ("true".equals(strV)) {
            this.f242d = 6;
            return;
        }
        if ("false".equals(strV)) {
            this.f242d = 7;
            return;
        }
        if ("undefined".equals(strV)) {
            this.f242d = 23;
            return;
        }
        if ("Set".equals(strV)) {
            this.f242d = 21;
        } else if ("TreeSet".equals(strV)) {
            this.f242d = 22;
        } else {
            this.f242d = 18;
        }
    }

    public final void D0() {
        E0(true);
    }

    @Override // b.a.a.j.b
    public long E(char c2) {
        int i;
        int i2;
        char cA0;
        char c3;
        this.q = 0;
        char cA02 = a0(this.h + 0);
        boolean z = cA02 == '\"';
        if (z) {
            cA02 = a0(this.h + 1);
            i = 2;
        } else {
            i = 1;
        }
        boolean z2 = cA02 == '-';
        if (z2) {
            cA02 = a0(this.h + i);
            i++;
        }
        if (cA02 >= '0' && cA02 <= '9') {
            long j = cA02 - '0';
            while (true) {
                i2 = i + 1;
                cA0 = a0(this.h + i);
                if (cA0 < '0' || cA0 > '9') {
                    break;
                }
                j = (j * 10) + ((long) (cA0 - '0'));
                i = i2;
            }
            if (cA0 == '.') {
                this.q = -1;
                return 0L;
            }
            if (!(j >= 0 || (j == Long.MIN_VALUE && z2))) {
                throw new NumberFormatException(L0(this.h, i2 - 1));
            }
            if (!z) {
                c3 = c2;
            } else {
                if (cA0 != '\"') {
                    this.q = -1;
                    return 0L;
                }
                cA0 = a0(this.h + i2);
                c3 = c2;
                i2++;
            }
            while (cA0 != c3) {
                if (!g0(cA0)) {
                    this.q = -1;
                    return j;
                }
                cA0 = a0(this.h + i2);
                i2++;
            }
            int i3 = this.h + i2;
            this.h = i3;
            this.g = a0(i3);
            this.q = 3;
            this.f242d = 16;
            return z2 ? -j : j;
        }
        if (cA02 != 'n' || a0(this.h + i) != 'u' || a0(this.h + i + 1) != 'l' || a0(this.h + i + 2) != 'l') {
            this.q = -1;
            return 0L;
        }
        this.q = 5;
        int i4 = i + 3;
        int i5 = i4 + 1;
        char cA03 = a0(this.h + i4);
        if (z && cA03 == '\"') {
            int i6 = i5 + 1;
            cA03 = a0(this.h + i5);
            i5 = i6;
        }
        while (cA03 != ',') {
            if (cA03 == ']') {
                int i7 = this.h + i5;
                this.h = i7;
                this.g = a0(i7);
                this.q = 5;
                this.f242d = 15;
                return 0L;
            }
            if (!g0(cA03)) {
                this.q = -1;
                return 0L;
            }
            int i8 = i5 + 1;
            cA03 = a0(this.h + i5);
            i5 = i8;
        }
        int i9 = this.h + i5;
        this.h = i9;
        this.g = a0(i9);
        this.q = 5;
        this.f242d = 16;
        return 0L;
    }

    public final void E0(boolean z) {
        if (this.g != 'n') {
            throw new JSONException("error parse null or new");
        }
        next();
        char c2 = this.g;
        if (c2 != 'u') {
            if (c2 != 'e') {
                throw new JSONException("error parse new");
            }
            next();
            if (this.g != 'w') {
                throw new JSONException("error parse new");
            }
            next();
            char c3 = this.g;
            if (c3 != ' ' && c3 != ',' && c3 != '}' && c3 != ']' && c3 != '\n' && c3 != '\r' && c3 != '\t' && c3 != 26 && c3 != '\f' && c3 != '\b') {
                throw new JSONException("scan new error");
            }
            this.f242d = 9;
            return;
        }
        next();
        if (this.g != 'l') {
            throw new JSONException("error parse null");
        }
        next();
        if (this.g != 'l') {
            throw new JSONException("error parse null");
        }
        next();
        char c4 = this.g;
        if (c4 != ' ' && c4 != ',' && c4 != '}' && c4 != ']' && c4 != '\n' && c4 != '\r' && c4 != '\t' && c4 != 26 && ((c4 != ':' || !z) && c4 != '\f' && c4 != '\b')) {
            throw new JSONException("scan null error");
        }
        this.f242d = 8;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:108:0x007b A[SYNTHETIC] */
    @Override // b.a.a.j.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void F(int r11) {
        /*
            Method dump skipped, instruction units count: 270
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.a.j.c.F(int):void");
    }

    public final void F0() {
        char next;
        char next2;
        this.l = this.h;
        this.m = false;
        while (true) {
            char next3 = next();
            if (next3 == '\'') {
                this.f242d = 4;
                next();
                return;
            }
            if (next3 != 26) {
                boolean z = true;
                if (next3 == '\\') {
                    if (!this.m) {
                        this.m = true;
                        int i = this.k;
                        char[] cArr = this.j;
                        if (i > cArr.length) {
                            char[] cArr2 = new char[i * 2];
                            System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
                            this.j = cArr2;
                        }
                        b0(this.l + 1, this.k, this.j);
                    }
                    char next4 = next();
                    if (next4 == '\"') {
                        l0('\"');
                    } else if (next4 != '\'') {
                        if (next4 != 'F') {
                            if (next4 == '\\') {
                                l0('\\');
                            } else if (next4 == 'b') {
                                l0('\b');
                            } else if (next4 != 'f') {
                                if (next4 == 'n') {
                                    l0('\n');
                                } else if (next4 == 'r') {
                                    l0('\r');
                                } else if (next4 != 'x') {
                                    switch (next4) {
                                        case '/':
                                            l0('/');
                                            break;
                                        case '0':
                                            l0((char) 0);
                                            break;
                                        case '1':
                                            l0((char) 1);
                                            break;
                                        case '2':
                                            l0((char) 2);
                                            break;
                                        case '3':
                                            l0((char) 3);
                                            break;
                                        case '4':
                                            l0((char) 4);
                                            break;
                                        case '5':
                                            l0((char) 5);
                                            break;
                                        case '6':
                                            l0((char) 6);
                                            break;
                                        case '7':
                                            l0((char) 7);
                                            break;
                                        default:
                                            switch (next4) {
                                                case 't':
                                                    l0('\t');
                                                    break;
                                                case 'u':
                                                    l0((char) Integer.parseInt(new String(new char[]{next(), next(), next(), next()}), 16));
                                                    break;
                                                case 'v':
                                                    l0((char) 11);
                                                    break;
                                                default:
                                                    this.g = next4;
                                                    throw new JSONException("unclosed single-quote string");
                                            }
                                            break;
                                    }
                                } else {
                                    next = next();
                                    next2 = next();
                                    boolean z2 = (next >= '0' && next <= '9') || (next >= 'a' && next <= 'f') || (next >= 'A' && next <= 'F');
                                    if ((next2 < '0' || next2 > '9') && ((next2 < 'a' || next2 > 'f') && (next2 < 'A' || next2 > 'F'))) {
                                        z = false;
                                    }
                                    if (z2 && z) {
                                        int[] iArr = f241c;
                                        l0((char) ((iArr[next] * 16) + iArr[next2]));
                                    }
                                }
                            }
                        }
                        l0('\f');
                    } else {
                        l0('\'');
                    }
                } else if (this.m) {
                    int i2 = this.k;
                    char[] cArr3 = this.j;
                    if (i2 == cArr3.length) {
                        l0(next3);
                    } else {
                        this.k = i2 + 1;
                        cArr3[i2] = next3;
                    }
                } else {
                    this.k++;
                }
            } else {
                if (f0()) {
                    throw new JSONException("unclosed single-quote string");
                }
                l0((char) 26);
            }
        }
        throw new JSONException("invalid escape character \\x" + next + next2);
    }

    @Override // b.a.a.j.b
    public final String G(i iVar, char c2) {
        String strC;
        this.l = this.h;
        this.k = 0;
        boolean z = false;
        int i = 0;
        while (true) {
            char next = next();
            if (next == c2) {
                this.f242d = 4;
                if (z) {
                    strC = iVar.c(this.j, 0, this.k, i);
                } else {
                    int i2 = this.l;
                    strC = X(i2 == -1 ? 0 : i2 + 1, this.k, i, iVar);
                }
                this.k = 0;
                next();
                return strC;
            }
            if (next == 26) {
                throw new JSONException("unclosed.str");
            }
            if (next == '\\') {
                if (!z) {
                    int i3 = this.k;
                    char[] cArr = this.j;
                    if (i3 >= cArr.length) {
                        int length = cArr.length * 2;
                        if (i3 <= length) {
                            i3 = length;
                        }
                        char[] cArr2 = new char[i3];
                        System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
                        this.j = cArr2;
                    }
                    Y(this.l + 1, this.j, 0, this.k);
                    z = true;
                }
                char next2 = next();
                if (next2 == '\"') {
                    i = (i * 31) + 34;
                    l0('\"');
                } else if (next2 != '\'') {
                    if (next2 != 'F') {
                        if (next2 == '\\') {
                            i = (i * 31) + 92;
                            l0('\\');
                        } else if (next2 == 'b') {
                            i = (i * 31) + 8;
                            l0('\b');
                        } else if (next2 != 'f') {
                            if (next2 == 'n') {
                                i = (i * 31) + 10;
                                l0('\n');
                            } else if (next2 == 'r') {
                                i = (i * 31) + 13;
                                l0('\r');
                            } else if (next2 != 'x') {
                                switch (next2) {
                                    case '/':
                                        i = (i * 31) + 47;
                                        l0('/');
                                        break;
                                    case '0':
                                        i = (i * 31) + next2;
                                        l0((char) 0);
                                        break;
                                    case '1':
                                        i = (i * 31) + next2;
                                        l0((char) 1);
                                        break;
                                    case '2':
                                        i = (i * 31) + next2;
                                        l0((char) 2);
                                        break;
                                    case '3':
                                        i = (i * 31) + next2;
                                        l0((char) 3);
                                        break;
                                    case '4':
                                        i = (i * 31) + next2;
                                        l0((char) 4);
                                        break;
                                    case '5':
                                        i = (i * 31) + next2;
                                        l0((char) 5);
                                        break;
                                    case '6':
                                        i = (i * 31) + next2;
                                        l0((char) 6);
                                        break;
                                    case '7':
                                        i = (i * 31) + next2;
                                        l0((char) 7);
                                        break;
                                    default:
                                        switch (next2) {
                                            case 't':
                                                i = (i * 31) + 9;
                                                l0('\t');
                                                break;
                                            case 'u':
                                                int i4 = Integer.parseInt(new String(new char[]{next(), next(), next(), next()}), 16);
                                                i = (i * 31) + i4;
                                                l0((char) i4);
                                                break;
                                            case 'v':
                                                i = (i * 31) + 11;
                                                l0((char) 11);
                                                break;
                                            default:
                                                this.g = next2;
                                                throw new JSONException("unclosed.str.lit");
                                        }
                                        break;
                                }
                            } else {
                                char next3 = next();
                                this.g = next3;
                                char next4 = next();
                                this.g = next4;
                                int[] iArr = f241c;
                                char c3 = (char) ((iArr[next3] * 16) + iArr[next4]);
                                i = (i * 31) + c3;
                                l0(c3);
                            }
                        }
                    }
                    i = (i * 31) + 12;
                    l0('\f');
                } else {
                    i = (i * 31) + 39;
                    l0('\'');
                }
            } else {
                i = (i * 31) + next;
                if (z) {
                    int i5 = this.k;
                    char[] cArr3 = this.j;
                    if (i5 == cArr3.length) {
                        l0(next);
                    } else {
                        this.k = i5 + 1;
                        cArr3[i5] = next;
                    }
                } else {
                    this.k++;
                }
            }
        }
    }

    public String G0(i iVar, char c2) {
        int i = 0;
        this.q = 0;
        char cA0 = a0(this.h + 0);
        if (cA0 == 'n') {
            if (a0(this.h + 1) != 'u' || a0(this.h + 1 + 1) != 'l' || a0(this.h + 1 + 2) != 'l') {
                this.q = -1;
                return null;
            }
            if (a0(this.h + 4) != c2) {
                this.q = -1;
                return null;
            }
            int i2 = this.h + 5;
            this.h = i2;
            this.g = a0(i2);
            this.q = 3;
            return null;
        }
        if (cA0 != '\"') {
            this.q = -1;
            return null;
        }
        int i3 = 1;
        while (true) {
            int i4 = i3 + 1;
            char cA02 = a0(this.h + i3);
            if (cA02 == '\"') {
                int i5 = this.h;
                int i6 = i5 + 0 + 1;
                String strX = X(i6, ((i5 + i4) - i6) - 1, i, iVar);
                int i7 = i4 + 1;
                char cA03 = a0(this.h + i4);
                while (cA03 != c2) {
                    if (!g0(cA03)) {
                        this.q = -1;
                        return strX;
                    }
                    cA03 = a0(this.h + i7);
                    i7++;
                }
                int i8 = this.h + i7;
                this.h = i8;
                this.g = a0(i8);
                this.q = 3;
                return strX;
            }
            i = (i * 31) + cA02;
            if (cA02 == '\\') {
                this.q = -1;
                return null;
            }
            i3 = i4;
        }
    }

    @Override // b.a.a.j.b
    public final void H() {
        k0(':');
    }

    public final void H0() {
        if (this.g != 't') {
            throw new JSONException("error parse true");
        }
        next();
        if (this.g != 'r') {
            throw new JSONException("error parse true");
        }
        next();
        if (this.g != 'u') {
            throw new JSONException("error parse true");
        }
        next();
        if (this.g != 'e') {
            throw new JSONException("error parse true");
        }
        next();
        char c2 = this.g;
        if (c2 != ' ' && c2 != ',' && c2 != '}' && c2 != ']' && c2 != '\n' && c2 != '\r' && c2 != '\t' && c2 != 26 && c2 != '\f' && c2 != '\b' && c2 != ':' && c2 != '/') {
            throw new JSONException("scan true error");
        }
        this.f242d = 6;
    }

    public void I0(TimeZone timeZone) {
        this.o = timeZone;
    }

    @Override // b.a.a.j.b
    public abstract BigDecimal J();

    public void J0() {
        char c2;
        next();
        char c3 = this.g;
        if (c3 == '/') {
            do {
                next();
                c2 = this.g;
                if (c2 == '\n') {
                    next();
                    return;
                }
            } while (c2 != 26);
            return;
        }
        if (c3 != '*') {
            throw new JSONException("invalid comment");
        }
        next();
        while (true) {
            char c4 = this.g;
            if (c4 == 26) {
                return;
            }
            if (c4 == '*') {
                next();
                if (this.g == '/') {
                    next();
                    return;
                }
            } else {
                next();
            }
        }
    }

    public final String K0() {
        return this.r;
    }

    @Override // b.a.a.j.b
    public int L(char c2) {
        int i;
        int i2;
        char cA0;
        this.q = 0;
        char cA02 = a0(this.h + 0);
        boolean z = cA02 == '\"';
        if (z) {
            cA02 = a0(this.h + 1);
            i = 2;
        } else {
            i = 1;
        }
        boolean z2 = cA02 == '-';
        if (z2) {
            cA02 = a0(this.h + i);
            i++;
        }
        if (cA02 >= '0' && cA02 <= '9') {
            int i3 = cA02 - '0';
            while (true) {
                i2 = i + 1;
                cA0 = a0(this.h + i);
                if (cA0 < '0' || cA0 > '9') {
                    break;
                }
                i3 = (i3 * 10) + (cA0 - '0');
                i = i2;
            }
            if (cA0 == '.') {
                this.q = -1;
                return 0;
            }
            if (i3 < 0) {
                this.q = -1;
                return 0;
            }
            while (cA0 != c2) {
                if (!g0(cA0)) {
                    this.q = -1;
                    return z2 ? -i3 : i3;
                }
                char cA03 = a0(this.h + i2);
                i2++;
                cA0 = cA03;
            }
            int i4 = this.h + i2;
            this.h = i4;
            this.g = a0(i4);
            this.q = 3;
            this.f242d = 16;
            return z2 ? -i3 : i3;
        }
        if (cA02 != 'n' || a0(this.h + i) != 'u' || a0(this.h + i + 1) != 'l' || a0(this.h + i + 2) != 'l') {
            this.q = -1;
            return 0;
        }
        this.q = 5;
        int i5 = i + 3;
        int i6 = i5 + 1;
        char cA04 = a0(this.h + i5);
        if (z && cA04 == '\"') {
            int i7 = i6 + 1;
            cA04 = a0(this.h + i6);
            i6 = i7;
        }
        while (cA04 != ',') {
            if (cA04 == ']') {
                int i8 = this.h + i6;
                this.h = i8;
                this.g = a0(i8);
                this.q = 5;
                this.f242d = 15;
                return 0;
            }
            if (!g0(cA04)) {
                this.q = -1;
                return 0;
            }
            int i9 = i6 + 1;
            cA04 = a0(this.h + i6);
            i6 = i9;
        }
        int i10 = this.h + i6;
        this.h = i10;
        this.g = a0(i10);
        this.q = 5;
        this.f242d = 16;
        return 0;
    }

    public abstract String L0(int i, int i2);

    @Override // b.a.a.j.b
    public final String M() {
        return f.a(this.f242d);
    }

    public abstract char[] M0(int i, int i2);

    @Override // b.a.a.j.b
    public final Number N(boolean z) {
        char cA0 = a0((this.l + this.k) - 1);
        try {
            return cA0 == 'F' ? Float.valueOf(Float.parseFloat(T())) : cA0 == 'D' ? Double.valueOf(Double.parseDouble(T())) : z ? J() : Double.valueOf(c0());
        } catch (NumberFormatException e2) {
            throw new JSONException(e2.getMessage() + ", " + c());
        }
    }

    @Override // b.a.a.j.b
    public final String Q(i iVar) {
        if (this.f242d == 1 && this.f243e == 0 && this.h == 1) {
            this.h = 0;
        }
        boolean[] zArr = b.a.a.n.e.f439d;
        int i = this.g;
        if (!(i >= zArr.length || zArr[i])) {
            throw new JSONException("illegal identifier : " + this.g + c());
        }
        boolean[] zArr2 = b.a.a.n.e.f440e;
        this.l = this.h;
        this.k = 1;
        while (true) {
            char next = next();
            if (next < zArr2.length && !zArr2[next]) {
                break;
            }
            i = (i * 31) + next;
            this.k++;
        }
        this.g = a0(this.h);
        this.f242d = 18;
        if (this.k == 4 && i == 3392903 && a0(this.l) == 'n' && a0(this.l + 1) == 'u' && a0(this.l + 2) == 'l' && a0(this.l + 3) == 'l') {
            return null;
        }
        return iVar == null ? L0(this.l, this.k) : X(this.l, this.k, i, iVar);
    }

    @Override // b.a.a.j.b
    public Locale R() {
        return this.p;
    }

    @Override // b.a.a.j.b
    public final boolean S() {
        return this.k == 4 && a0(this.l + 1) == '$' && a0(this.l + 2) == 'r' && a0(this.l + 3) == 'e' && a0(this.l + 4) == 'f';
    }

    @Override // b.a.a.j.b
    public abstract String T();

    @Override // b.a.a.j.b
    public final void U(int i) {
        k0(':');
    }

    @Override // b.a.a.j.b
    public abstract String V();

    @Override // b.a.a.j.b
    public TimeZone W() {
        return this.o;
    }

    public abstract String X(int i, int i2, int i3, i iVar);

    public abstract void Y(int i, char[] cArr, int i2, int i3);

    public abstract boolean Z(char[] cArr);

    @Override // b.a.a.j.b
    public final int a() {
        return this.f243e;
    }

    public abstract char a0(int i);

    public abstract void b0(int i, int i2, char[] cArr);

    @Override // b.a.a.j.b
    public String c() {
        return "";
    }

    public double c0() {
        return Double.parseDouble(T());
    }

    @Override // b.a.a.j.b, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        char[] cArr = this.j;
        if (cArr.length <= 8192) {
            f239a.set(cArr);
        }
        this.j = null;
    }

    public Calendar d0() {
        return this.n;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0084  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x005b -> B:12:0x0032). Please report as a decompilation issue!!! */
    @Override // b.a.a.j.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long e() throws java.lang.NumberFormatException {
        /*
            r15 = this;
            int r0 = r15.l
            r1 = 0
            r2 = -1
            if (r0 != r2) goto L8
            r15.l = r1
        L8:
            int r0 = r15.l
            int r2 = r15.k
            int r2 = r2 + r0
            char r3 = r15.a0(r0)
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
            char r0 = r15.a0(r0)
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
            char r0 = r15.a0(r0)
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
            java.lang.String r1 = r15.T()
            r0.<init>(r1)
            throw r0
        L67:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.String r1 = r15.T()
            r0.<init>(r1)
            throw r0
        L71:
            r0 = r8
        L72:
            if (r1 == 0) goto L84
            int r1 = r15.l
            int r1 = r1 + r5
            if (r0 <= r1) goto L7a
            return r9
        L7a:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.String r1 = r15.T()
            r0.<init>(r1)
            throw r0
        L84:
            long r0 = -r9
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.a.j.c.e():long");
    }

    public abstract int e0(char c2, int i);

    @Override // b.a.a.j.b
    public final Number f() throws NumberFormatException {
        long j;
        long j2;
        boolean z = false;
        if (this.l == -1) {
            this.l = 0;
        }
        int i = this.l;
        int i2 = this.k + i;
        char c2 = ' ';
        char cA0 = a0(i2 - 1);
        if (cA0 == 'B') {
            i2--;
            c2 = 'B';
        } else if (cA0 == 'L') {
            i2--;
            c2 = 'L';
        } else if (cA0 == 'S') {
            i2--;
            c2 = 'S';
        }
        if (a0(this.l) == '-') {
            j = Long.MIN_VALUE;
            i++;
            z = true;
        } else {
            j = -9223372036854775807L;
        }
        long j3 = -922337203685477580L;
        if (i < i2) {
            j2 = -(a0(i) - '0');
            i++;
        } else {
            j2 = 0;
        }
        while (i < i2) {
            int i3 = i + 1;
            int iA0 = a0(i) - '0';
            if (j2 < j3) {
                return new BigInteger(T(), 10);
            }
            long j4 = j2 * 10;
            long j5 = iA0;
            if (j4 < j + j5) {
                return new BigInteger(T(), 10);
            }
            j2 = j4 - j5;
            i = i3;
            j3 = -922337203685477580L;
        }
        if (!z) {
            long j6 = -j2;
            return (j6 > 2147483647L || c2 == 'L') ? Long.valueOf(j6) : c2 == 'S' ? Short.valueOf((short) j6) : c2 == 'B' ? Byte.valueOf((byte) j6) : Integer.valueOf((int) j6);
        }
        if (i > this.l + 1) {
            return (j2 < -2147483648L || c2 == 'L') ? Long.valueOf(j2) : c2 == 'S' ? Short.valueOf((short) j2) : c2 == 'B' ? Byte.valueOf((byte) j2) : Integer.valueOf((int) j2);
        }
        throw new NumberFormatException(T());
    }

    public abstract boolean f0();

    @Override // b.a.a.j.b
    public float g() {
        char cCharAt;
        String strT = T();
        float f2 = Float.parseFloat(strT);
        if ((f2 != 0.0f && f2 != Float.POSITIVE_INFINITY) || (cCharAt = strT.charAt(0)) <= '0' || cCharAt > '9') {
            return f2;
        }
        throw new JSONException("float overflow : " + strT);
    }

    @Override // b.a.a.j.b
    public Enum<?> h(Class<?> cls, i iVar, char c2) {
        String strG0 = G0(iVar, c2);
        if (strG0 == null) {
            return null;
        }
        return Enum.valueOf(cls, strG0);
    }

    public void h0(String str, Object... objArr) {
        this.f242d = 1;
    }

    @Override // b.a.a.j.b
    public boolean i() {
        int i = 0;
        while (true) {
            char cA0 = a0(i);
            if (cA0 == 26) {
                this.f242d = 20;
                return true;
            }
            if (!g0(cA0)) {
                return false;
            }
            i++;
        }
    }

    public final boolean i0(char[] cArr) {
        while (!Z(cArr)) {
            if (!g0(this.g)) {
                return false;
            }
            next();
        }
        int length = this.h + cArr.length;
        this.h = length;
        char cA0 = a0(length);
        this.g = cA0;
        if (cA0 == '{') {
            next();
            this.f242d = 12;
        } else if (cA0 == '[') {
            next();
            this.f242d = 14;
        } else if (cA0 == 'S' && a0(this.h + 1) == 'e' && a0(this.h + 2) == 't' && a0(this.h + 3) == '[') {
            int i = this.h + 3;
            this.h = i;
            this.g = a0(i);
            this.f242d = 21;
        } else {
            u();
        }
        return true;
    }

    @Override // b.a.a.j.b
    public final boolean isEnabled(int i) {
        return (i & this.f244f) != 0;
    }

    @Override // b.a.a.j.b
    public final int j() {
        return this.f242d;
    }

    public final void j0() {
        while (g0(this.g)) {
            next();
        }
        char c2 = this.g;
        if (c2 == '_' || c2 == '$' || Character.isLetter(c2)) {
            C0();
        } else {
            u();
        }
    }

    @Override // b.a.a.j.b
    public String k(char c2) {
        this.q = 0;
        char cA0 = a0(this.h + 0);
        if (cA0 == 'n') {
            if (a0(this.h + 1) != 'u' || a0(this.h + 1 + 1) != 'l' || a0(this.h + 1 + 2) != 'l') {
                this.q = -1;
                return null;
            }
            if (a0(this.h + 4) != c2) {
                this.q = -1;
                return null;
            }
            int i = this.h + 5;
            this.h = i;
            this.g = a0(i);
            this.q = 3;
            return null;
        }
        int i2 = 1;
        while (cA0 != '\"') {
            if (!g0(cA0)) {
                this.q = -1;
                return K0();
            }
            cA0 = a0(this.h + i2);
            i2++;
        }
        int i3 = this.h + i2;
        int iE0 = e0('\"', i3);
        if (iE0 == -1) {
            throw new JSONException("unclosed str");
        }
        String strL0 = L0(this.h + i2, iE0 - i3);
        if (strL0.indexOf(92) != -1) {
            while (true) {
                int i4 = 0;
                for (int i5 = iE0 - 1; i5 >= 0 && a0(i5) == '\\'; i5--) {
                    i4++;
                }
                if (i4 % 2 == 0) {
                    break;
                }
                iE0 = e0('\"', iE0 + 1);
            }
            int i6 = iE0 - i3;
            strL0 = m0(M0(this.h + 1, i6), i6);
        }
        int i7 = i2 + (iE0 - i3) + 1;
        int i8 = i7 + 1;
        char cA02 = a0(this.h + i7);
        while (cA02 != c2) {
            if (!g0(cA02)) {
                if (cA02 == ']') {
                    int i9 = this.h + i8;
                    this.h = i9;
                    this.g = a0(i9);
                    this.q = -1;
                }
                return strL0;
            }
            cA02 = a0(this.h + i8);
            i8++;
        }
        int i10 = this.h + i8;
        this.h = i10;
        this.g = a0(i10);
        this.q = 3;
        this.f242d = 16;
        return strL0;
    }

    public final void k0(char c2) {
        this.k = 0;
        while (true) {
            char c3 = this.g;
            if (c3 == c2) {
                next();
                u();
                return;
            }
            if (c3 != ' ' && c3 != '\n' && c3 != '\r' && c3 != '\t' && c3 != '\f' && c3 != '\b') {
                throw new JSONException("not match " + c2 + " - " + this.g + ", info : " + c());
            }
            next();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00ab  */
    @Override // b.a.a.j.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean l(char r10) {
        /*
            r9 = this;
            r0 = 0
            r9.q = r0
            int r1 = r9.h
            int r1 = r1 + r0
            char r1 = r9.a0(r1)
            r2 = 3
            r3 = 5
            r4 = 101(0x65, float:1.42E-43)
            r5 = -1
            r6 = 2
            r7 = 1
            r8 = 116(0x74, float:1.63E-43)
            if (r1 != r8) goto L42
            int r1 = r9.h
            int r1 = r1 + r7
            char r1 = r9.a0(r1)
            r8 = 114(0x72, float:1.6E-43)
            if (r1 != r8) goto L3f
            int r1 = r9.h
            int r1 = r1 + r7
            int r1 = r1 + r7
            char r1 = r9.a0(r1)
            r8 = 117(0x75, float:1.64E-43)
            if (r1 != r8) goto L3f
            int r1 = r9.h
            int r1 = r1 + r7
            int r1 = r1 + r6
            char r1 = r9.a0(r1)
            if (r1 != r4) goto L3f
            int r0 = r9.h
            int r0 = r0 + 4
            char r1 = r9.a0(r0)
            goto L8b
        L3f:
            r9.q = r5
            return r0
        L42:
            r8 = 102(0x66, float:1.43E-43)
            if (r1 != r8) goto L7f
            int r1 = r9.h
            int r1 = r1 + r7
            char r1 = r9.a0(r1)
            r8 = 97
            if (r1 != r8) goto L7c
            int r1 = r9.h
            int r1 = r1 + r7
            int r1 = r1 + r7
            char r1 = r9.a0(r1)
            r8 = 108(0x6c, float:1.51E-43)
            if (r1 != r8) goto L7c
            int r1 = r9.h
            int r1 = r1 + r7
            int r1 = r1 + r6
            char r1 = r9.a0(r1)
            r6 = 115(0x73, float:1.61E-43)
            if (r1 != r6) goto L7c
            int r1 = r9.h
            int r1 = r1 + r7
            int r1 = r1 + r2
            char r1 = r9.a0(r1)
            if (r1 != r4) goto L7c
            int r1 = r9.h
            r4 = 6
            int r1 = r1 + r3
            char r1 = r9.a0(r1)
            goto Lba
        L7c:
            r9.q = r5
            return r0
        L7f:
            r3 = 49
            if (r1 != r3) goto L8d
            int r0 = r9.h
            int r0 = r0 + r7
            char r1 = r9.a0(r0)
            r3 = r6
        L8b:
            r0 = r7
            goto L9b
        L8d:
            r3 = 48
            if (r1 != r3) goto L9a
            int r1 = r9.h
            int r1 = r1 + r7
            char r1 = r9.a0(r1)
            r3 = r6
            goto L9b
        L9a:
            r3 = r7
        L9b:
            if (r1 != r10) goto Lab
            int r10 = r9.h
            int r10 = r10 + r3
            r9.h = r10
            char r10 = r9.a0(r10)
            r9.g = r10
            r9.q = r2
            return r0
        Lab:
            boolean r1 = g0(r1)
            if (r1 == 0) goto Lbc
            int r1 = r9.h
            int r4 = r3 + 1
            int r1 = r1 + r3
            char r1 = r9.a0(r1)
        Lba:
            r3 = r4
            goto L9b
        Lbc:
            r9.q = r5
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.a.j.c.l(char):boolean");
    }

    public final void l0(char c2) {
        int i = this.k;
        char[] cArr = this.j;
        if (i == cArr.length) {
            char[] cArr2 = new char[cArr.length * 2];
            System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
            this.j = cArr2;
        }
        char[] cArr3 = this.j;
        int i2 = this.k;
        this.k = i2 + 1;
        cArr3[i2] = c2;
    }

    @Override // b.a.a.j.b
    public String n(i iVar) {
        return null;
    }

    public long n0(char[] cArr) {
        this.q = 0;
        if (!Z(cArr)) {
            this.q = -2;
            return 0L;
        }
        int length = cArr.length;
        int i = length + 1;
        if (a0(this.h + length) != '\"') {
            this.q = -1;
            return 0L;
        }
        long j = -3750763034362895579L;
        while (true) {
            int i2 = i + 1;
            char cA0 = a0(this.h + i);
            if (cA0 == '\"') {
                int i3 = i2 + 1;
                char cA02 = a0(this.h + i2);
                if (cA02 == ',') {
                    int i4 = this.h + i3;
                    this.h = i4;
                    this.g = a0(i4);
                    this.q = 3;
                    return j;
                }
                if (cA02 != '}') {
                    this.q = -1;
                    return 0L;
                }
                int i5 = i3 + 1;
                char cA03 = a0(this.h + i3);
                if (cA03 == ',') {
                    this.f242d = 16;
                    int i6 = this.h + i5;
                    this.h = i6;
                    this.g = a0(i6);
                } else if (cA03 == ']') {
                    this.f242d = 15;
                    int i7 = this.h + i5;
                    this.h = i7;
                    this.g = a0(i7);
                } else if (cA03 == '}') {
                    this.f242d = 13;
                    int i8 = this.h + i5;
                    this.h = i8;
                    this.g = a0(i8);
                } else {
                    if (cA03 != 26) {
                        this.q = -1;
                        return 0L;
                    }
                    this.f242d = 20;
                    this.h += i5 - 1;
                    this.g = (char) 26;
                }
                this.q = 4;
                return j;
            }
            j = (j ^ ((long) ((cA0 < 'A' || cA0 > 'Z') ? cA0 : cA0 + ' '))) * 1099511628211L;
            if (cA0 == '\\') {
                this.q = -1;
                return 0L;
            }
            i = i2;
        }
    }

    @Override // b.a.a.j.b
    public abstract char next();

    @Override // b.a.a.j.b
    public final String o(i iVar) {
        A();
        char c2 = this.g;
        if (c2 == '\"') {
            return G(iVar, '\"');
        }
        if (c2 == '\'') {
            if (y(Feature.AllowSingleQuotes)) {
                return G(iVar, '\'');
            }
            throw new JSONException("syntax error");
        }
        if (c2 == '}') {
            next();
            this.f242d = 13;
            return null;
        }
        if (c2 == ',') {
            next();
            this.f242d = 16;
            return null;
        }
        if (c2 == 26) {
            this.f242d = 20;
            return null;
        }
        if (y(Feature.AllowUnQuotedFieldNames)) {
            return Q(iVar);
        }
        throw new JSONException("syntax error");
    }

    public final void o0() {
        if (this.g != 'f') {
            throw new JSONException("error parse false");
        }
        next();
        if (this.g != 'a') {
            throw new JSONException("error parse false");
        }
        next();
        if (this.g != 'l') {
            throw new JSONException("error parse false");
        }
        next();
        if (this.g != 's') {
            throw new JSONException("error parse false");
        }
        next();
        if (this.g != 'e') {
            throw new JSONException("error parse false");
        }
        next();
        char c2 = this.g;
        if (c2 != ' ' && c2 != ',' && c2 != '}' && c2 != ']' && c2 != '\n' && c2 != '\r' && c2 != '\t' && c2 != 26 && c2 != '\f' && c2 != '\b' && c2 != ':' && c2 != '/') {
            throw new JSONException("scan false error");
        }
        this.f242d = 7;
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0073, code lost:
    
        r16 = false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.math.BigInteger p0(char[] r21) {
        /*
            Method dump skipped, instruction units count: 453
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.a.j.c.p0(char[]):java.math.BigInteger");
    }

    @Override // b.a.a.j.b
    public int q() {
        return this.f244f;
    }

    public boolean q0(char[] cArr) {
        boolean z;
        int i;
        this.q = 0;
        if (!Z(cArr)) {
            this.q = -2;
            return false;
        }
        int length = cArr.length;
        int i2 = length + 1;
        char cA0 = a0(this.h + length);
        if (cA0 == 't') {
            int i3 = i2 + 1;
            if (a0(this.h + i2) != 'r') {
                this.q = -1;
                return false;
            }
            int i4 = i3 + 1;
            if (a0(this.h + i3) != 'u') {
                this.q = -1;
                return false;
            }
            i = i4 + 1;
            if (a0(this.h + i4) != 'e') {
                this.q = -1;
                return false;
            }
            z = true;
        } else {
            if (cA0 != 'f') {
                this.q = -1;
                return false;
            }
            int i5 = i2 + 1;
            if (a0(this.h + i2) != 'a') {
                this.q = -1;
                return false;
            }
            int i6 = i5 + 1;
            if (a0(this.h + i5) != 'l') {
                this.q = -1;
                return false;
            }
            int i7 = i6 + 1;
            if (a0(this.h + i6) != 's') {
                this.q = -1;
                return false;
            }
            int i8 = i7 + 1;
            if (a0(this.h + i7) != 'e') {
                this.q = -1;
                return false;
            }
            z = false;
            i = i8;
        }
        int i9 = i + 1;
        char cA02 = a0(this.h + i);
        if (cA02 == ',') {
            int i10 = this.h + i9;
            this.h = i10;
            this.g = a0(i10);
            this.q = 3;
            this.f242d = 16;
            return z;
        }
        if (cA02 != '}') {
            this.q = -1;
            return false;
        }
        int i11 = i9 + 1;
        char cA03 = a0(this.h + i9);
        if (cA03 == ',') {
            this.f242d = 16;
            int i12 = this.h + i11;
            this.h = i12;
            this.g = a0(i12);
        } else if (cA03 == ']') {
            this.f242d = 15;
            int i13 = this.h + i11;
            this.h = i13;
            this.g = a0(i13);
        } else if (cA03 == '}') {
            this.f242d = 13;
            int i14 = this.h + i11;
            this.h = i14;
            this.g = a0(i14);
        } else {
            if (cA03 != 26) {
                this.q = -1;
                return false;
            }
            this.f242d = 20;
            this.h += i11 - 1;
            this.g = (char) 26;
        }
        this.q = 4;
        return z;
    }

    public Date r0(char[] cArr) {
        int i;
        long j;
        Date date;
        int i2;
        char cA0;
        boolean z = false;
        this.q = 0;
        if (!Z(cArr)) {
            this.q = -2;
            return null;
        }
        int length = cArr.length;
        int i3 = length + 1;
        char cA02 = a0(this.h + length);
        if (cA02 == '\"') {
            int iE0 = e0('\"', this.h + cArr.length + 1);
            if (iE0 == -1) {
                throw new JSONException("unclosed str");
            }
            int length2 = this.h + cArr.length + 1;
            String strL0 = L0(length2, iE0 - length2);
            if (strL0.indexOf(92) != -1) {
                while (true) {
                    int i4 = 0;
                    for (int i5 = iE0 - 1; i5 >= 0 && a0(i5) == '\\'; i5--) {
                        i4++;
                    }
                    if (i4 % 2 == 0) {
                        break;
                    }
                    iE0 = e0('\"', iE0 + 1);
                }
                int i6 = this.h;
                int length3 = iE0 - ((cArr.length + i6) + 1);
                strL0 = m0(M0(i6 + cArr.length + 1, length3), length3);
            }
            int i7 = this.h;
            int length4 = i3 + (iE0 - ((cArr.length + i7) + 1)) + 1;
            i = length4 + 1;
            cA02 = a0(i7 + length4);
            e eVar = new e(strL0);
            try {
                if (!eVar.R0(false)) {
                    this.q = -1;
                    return null;
                }
                date = eVar.d0().getTime();
            } finally {
                eVar.close();
            }
        } else {
            if (cA02 != '-' && (cA02 < '0' || cA02 > '9')) {
                this.q = -1;
                return null;
            }
            if (cA02 == '-') {
                cA02 = a0(this.h + i3);
                i3++;
                z = true;
            }
            if (cA02 < '0' || cA02 > '9') {
                i = i3;
                j = 0;
            } else {
                j = cA02 - '0';
                while (true) {
                    i2 = i3 + 1;
                    cA0 = a0(this.h + i3);
                    if (cA0 < '0' || cA0 > '9') {
                        break;
                    }
                    j = (j * 10) + ((long) (cA0 - '0'));
                    i3 = i2;
                }
                cA02 = cA0;
                i = i2;
            }
            if (j < 0) {
                this.q = -1;
                return null;
            }
            if (z) {
                j = -j;
            }
            date = new Date(j);
        }
        if (cA02 == ',') {
            int i8 = this.h + i;
            this.h = i8;
            this.g = a0(i8);
            this.q = 3;
            return date;
        }
        if (cA02 != '}') {
            this.q = -1;
            return null;
        }
        int i9 = i + 1;
        char cA03 = a0(this.h + i);
        if (cA03 == ',') {
            this.f242d = 16;
            int i10 = this.h + i9;
            this.h = i10;
            this.g = a0(i10);
        } else if (cA03 == ']') {
            this.f242d = 15;
            int i11 = this.h + i9;
            this.h = i11;
            this.g = a0(i11);
        } else if (cA03 == '}') {
            this.f242d = 13;
            int i12 = this.h + i9;
            this.h = i12;
            this.g = a0(i12);
        } else {
            if (cA03 != 26) {
                this.q = -1;
                return null;
            }
            this.f242d = 20;
            this.h += i9 - 1;
            this.g = (char) 26;
        }
        this.q = 4;
        return date;
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x00c7 A[ADDED_TO_REGION] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:57:0x00c9 -> B:53:0x00b7). Please report as a decompilation issue!!! */
    @Override // b.a.a.j.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public double s(char r21) {
        /*
            Method dump skipped, instruction units count: 437
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.a.j.c.s(char):double");
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x00b8 A[ADDED_TO_REGION] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:56:0x00ba -> B:52:0x00a8). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.math.BigDecimal s0(char[] r18) {
        /*
            Method dump skipped, instruction units count: 513
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.a.j.c.s0(char[]):java.math.BigDecimal");
    }

    @Override // b.a.a.j.b
    public final float t(char c2) {
        int i;
        int i2;
        char cA0;
        int i3;
        int i4;
        float f2;
        this.q = 0;
        char cA02 = a0(this.h + 0);
        boolean z = cA02 == '\"';
        if (z) {
            cA02 = a0(this.h + 1);
            i = 2;
        } else {
            i = 1;
        }
        boolean z2 = cA02 == '-';
        if (z2) {
            cA02 = a0(this.h + i);
            i++;
        }
        if (cA02 < '0' || cA02 > '9') {
            if (cA02 != 'n' || a0(this.h + i) != 'u' || a0(this.h + i + 1) != 'l' || a0(this.h + i + 2) != 'l') {
                this.q = -1;
                return 0.0f;
            }
            this.q = 5;
            int i5 = i + 3;
            int i6 = i5 + 1;
            char cA03 = a0(this.h + i5);
            if (z && cA03 == '\"') {
                cA03 = a0(this.h + i6);
                i6++;
            }
            while (cA03 != ',') {
                if (cA03 == ']') {
                    int i7 = this.h + i6;
                    this.h = i7;
                    this.g = a0(i7);
                    this.q = 5;
                    this.f242d = 15;
                    return 0.0f;
                }
                if (!g0(cA03)) {
                    this.q = -1;
                    return 0.0f;
                }
                cA03 = a0(this.h + i6);
                i6++;
            }
            int i8 = this.h + i6;
            this.h = i8;
            this.g = a0(i8);
            this.q = 5;
            this.f242d = 16;
            return 0.0f;
        }
        long j = cA02 - '0';
        while (true) {
            i2 = i + 1;
            cA0 = a0(this.h + i);
            if (cA0 < '0' || cA0 > '9') {
                break;
            }
            j = (j * 10) + ((long) (cA0 - '0'));
            i = i2;
        }
        long j2 = 1;
        if (cA0 == '.') {
            int i9 = i2 + 1;
            char cA04 = a0(this.h + i2);
            if (cA04 < '0' || cA04 > '9') {
                this.q = -1;
                return 0.0f;
            }
            j = (j * 10) + ((long) (cA04 - '0'));
            j2 = 10;
            while (true) {
                i2 = i9 + 1;
                cA0 = a0(this.h + i9);
                if (cA0 < '0' || cA0 > '9') {
                    break;
                }
                j = (j * 10) + ((long) (cA0 - '0'));
                j2 *= 10;
                i9 = i2;
            }
        }
        long j3 = j2;
        boolean z3 = cA0 == 'e' || cA0 == 'E';
        if (z3) {
            int i10 = i2 + 1;
            char cA05 = a0(this.h + i2);
            if (cA05 == '+' || cA05 == '-') {
                int i11 = i10 + 1;
                cA0 = a0(this.h + i10);
                i2 = i11;
            } else {
                i2 = i10;
                cA0 = cA05;
            }
            while (cA0 >= '0' && cA0 <= '9') {
                int i12 = i2 + 1;
                cA0 = a0(this.h + i2);
                i2 = i12;
            }
        }
        if (!z) {
            i3 = this.h;
            i4 = ((i3 + i2) - i3) - 1;
        } else {
            if (cA0 != '\"') {
                this.q = -1;
                return 0.0f;
            }
            int i13 = i2 + 1;
            cA0 = a0(this.h + i2);
            int i14 = this.h;
            i3 = i14 + 1;
            i4 = ((i14 + i13) - i3) - 2;
            i2 = i13;
        }
        if (z3 || i4 >= 17) {
            f2 = Float.parseFloat(L0(i3, i4));
        } else {
            f2 = (float) (j / j3);
            if (z2) {
                f2 = -f2;
            }
        }
        if (cA0 != c2) {
            this.q = -1;
            return f2;
        }
        int i15 = this.h + i2;
        this.h = i15;
        this.g = a0(i15);
        this.q = 3;
        this.f242d = 16;
        return f2;
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x00dc A[ADDED_TO_REGION] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:59:0x00de -> B:55:0x00ca). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final double t0(char[] r24) {
        /*
            Method dump skipped, instruction units count: 564
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.a.j.c.t0(char[]):double");
    }

    @Override // b.a.a.j.b
    public final void u() {
        this.k = 0;
        while (true) {
            this.f243e = this.h;
            char c2 = this.g;
            if (c2 == '/') {
                J0();
            } else {
                if (c2 == '\"') {
                    x();
                    return;
                }
                if (c2 == ',') {
                    next();
                    this.f242d = 16;
                    return;
                }
                if (c2 >= '0' && c2 <= '9') {
                    C();
                    return;
                }
                if (c2 == '-') {
                    C();
                    return;
                }
                switch (c2) {
                    case '\b':
                    case '\t':
                    case '\n':
                    case '\f':
                    case '\r':
                    case ' ':
                        next();
                        break;
                    case '\'':
                        if (!y(Feature.AllowSingleQuotes)) {
                            throw new JSONException("Feature.AllowSingleQuotes is false");
                        }
                        F0();
                        return;
                    case '(':
                        next();
                        this.f242d = 10;
                        return;
                    case ')':
                        next();
                        this.f242d = 11;
                        return;
                    case '+':
                        next();
                        C();
                        return;
                    case '.':
                        next();
                        this.f242d = 25;
                        return;
                    case ':':
                        next();
                        this.f242d = 17;
                        return;
                    case ';':
                        next();
                        this.f242d = 24;
                        return;
                    case 'N':
                    case 'S':
                    case 'T':
                    case 'u':
                        C0();
                        return;
                    case '[':
                        next();
                        this.f242d = 14;
                        return;
                    case ']':
                        next();
                        this.f242d = 15;
                        return;
                    case 'f':
                        o0();
                        return;
                    case 'n':
                        D0();
                        return;
                    case 't':
                        H0();
                        return;
                    case 'x':
                        B0();
                        return;
                    case '{':
                        next();
                        this.f242d = 12;
                        return;
                    case '}':
                        next();
                        this.f242d = 13;
                        return;
                    default:
                        if (f0()) {
                            if (this.f242d == 20) {
                                throw new JSONException("EOF error");
                            }
                            this.f242d = 20;
                            int i = this.h;
                            this.f243e = i;
                            this.i = i;
                            return;
                        }
                        char c3 = this.g;
                        if (c3 > 31 && c3 != 127) {
                            h0("illegal.char", String.valueOf((int) c3));
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

    public final float u0(char[] cArr) {
        int i;
        char cA0;
        boolean z;
        long j;
        int length;
        int i2;
        float f2;
        this.q = 0;
        if (!Z(cArr)) {
            this.q = -2;
            return 0.0f;
        }
        int length2 = cArr.length;
        int i3 = length2 + 1;
        char cA02 = a0(this.h + length2);
        boolean z2 = cA02 == '\"';
        if (z2) {
            cA02 = a0(this.h + i3);
            i3++;
        }
        boolean z3 = cA02 == '-';
        if (z3) {
            cA02 = a0(this.h + i3);
            i3++;
        }
        if (cA02 >= '0') {
            char c2 = '9';
            if (cA02 <= '9') {
                long j2 = cA02 - '0';
                while (true) {
                    i = i3 + 1;
                    cA0 = a0(this.h + i3);
                    if (cA0 < '0' || cA0 > '9') {
                        break;
                    }
                    j2 = (j2 * 10) + ((long) (cA0 - '0'));
                    i3 = i;
                }
                if (cA0 == '.') {
                    int i4 = i + 1;
                    char cA03 = a0(this.h + i);
                    if (cA03 < '0' || cA03 > '9') {
                        this.q = -1;
                        return 0.0f;
                    }
                    z = z2;
                    j2 = (j2 * 10) + ((long) (cA03 - '0'));
                    j = 10;
                    while (true) {
                        i = i4 + 1;
                        cA0 = a0(this.h + i4);
                        if (cA0 < '0' || cA0 > c2) {
                            break;
                        }
                        j2 = (j2 * 10) + ((long) (cA0 - '0'));
                        j *= 10;
                        i4 = i;
                        c2 = '9';
                    }
                } else {
                    z = z2;
                    j = 1;
                }
                boolean z4 = cA0 == 'e' || cA0 == 'E';
                if (z4) {
                    int i5 = i + 1;
                    cA0 = a0(this.h + i);
                    if (cA0 == '+' || cA0 == '-') {
                        int i6 = i5 + 1;
                        cA0 = a0(this.h + i5);
                        i = i6;
                    } else {
                        i = i5;
                    }
                    while (cA0 >= '0' && cA0 <= '9') {
                        int i7 = i + 1;
                        cA0 = a0(this.h + i);
                        i = i7;
                    }
                }
                if (!z) {
                    int i8 = this.h;
                    length = cArr.length + i8;
                    i2 = ((i8 + i) - length) - 1;
                } else {
                    if (cA0 != '\"') {
                        this.q = -1;
                        return 0.0f;
                    }
                    int i9 = i + 1;
                    cA0 = a0(this.h + i);
                    int i10 = this.h;
                    length = cArr.length + i10 + 1;
                    i2 = ((i10 + i9) - length) - 2;
                    i = i9;
                }
                if (z4 || i2 >= 17) {
                    f2 = Float.parseFloat(L0(length, i2));
                } else {
                    f2 = (float) (j2 / j);
                    if (z3) {
                        f2 = -f2;
                    }
                }
                if (cA0 == ',') {
                    int i11 = this.h + i;
                    this.h = i11;
                    this.g = a0(i11);
                    this.q = 3;
                    this.f242d = 16;
                    return f2;
                }
                if (cA0 != '}') {
                    this.q = -1;
                    return 0.0f;
                }
                int i12 = i + 1;
                char cA04 = a0(this.h + i);
                if (cA04 == ',') {
                    this.f242d = 16;
                    int i13 = this.h + i12;
                    this.h = i13;
                    this.g = a0(i13);
                } else if (cA04 == ']') {
                    this.f242d = 15;
                    int i14 = this.h + i12;
                    this.h = i14;
                    this.g = a0(i14);
                } else if (cA04 == '}') {
                    this.f242d = 13;
                    int i15 = this.h + i12;
                    this.h = i15;
                    this.g = a0(i15);
                } else {
                    if (cA04 != 26) {
                        this.q = -1;
                        return 0.0f;
                    }
                    this.h += i12 - 1;
                    this.f242d = 20;
                    this.g = (char) 26;
                }
                this.q = 4;
                return f2;
            }
        }
        boolean z5 = z2;
        if (cA02 != 'n' || a0(this.h + i3) != 'u' || a0(this.h + i3 + 1) != 'l' || a0(this.h + i3 + 2) != 'l') {
            this.q = -1;
            return 0.0f;
        }
        this.q = 5;
        int i16 = i3 + 3;
        int i17 = i16 + 1;
        char cA05 = a0(this.h + i16);
        if (z5 && cA05 == '\"') {
            cA05 = a0(this.h + i17);
            i17++;
        }
        while (cA05 != ',') {
            if (cA05 == '}') {
                int i18 = this.h + i17;
                this.h = i18;
                this.g = a0(i18);
                this.q = 5;
                this.f242d = 13;
                return 0.0f;
            }
            if (!g0(cA05)) {
                this.q = -1;
                return 0.0f;
            }
            cA05 = a0(this.h + i17);
            i17++;
        }
        int i19 = this.h + i17;
        this.h = i19;
        this.g = a0(i19);
        this.q = 5;
        this.f242d = 16;
        return 0.0f;
    }

    @Override // b.a.a.j.b
    public final char v() {
        return this.g;
    }

    /* JADX WARN: Code restructure failed: missing block: B:103:0x01bb, code lost:
    
        r1 = r4;
        r19.q = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x01be, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00a8, code lost:
    
        r19.q = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00aa, code lost:
    
        return r4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final float[] v0(char[] r20) {
        /*
            Method dump skipped, instruction units count: 447
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.a.j.c.v0(char[]):float[]");
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x00ad A[ADDED_TO_REGION] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:54:0x00af -> B:50:0x009d). Please report as a decompilation issue!!! */
    @Override // b.a.a.j.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.math.BigDecimal w(char r19) {
        /*
            Method dump skipped, instruction units count: 487
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.a.j.c.w(char):java.math.BigDecimal");
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x00b1, code lost:
    
        r21.q = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00b3, code lost:
    
        return r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0135, code lost:
    
        r4 = r18 + 1;
        r1 = a0(r21.h + r18);
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
        r2 = a0(r21.h + r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x016f, code lost:
    
        if (r1 != ']') goto L90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0171, code lost:
    
        r3 = r4 + 1;
        r2 = a0(r21.h + r4);
        r8 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x017c, code lost:
    
        r2 = r1;
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x0191, code lost:
    
        r21.q = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x0194, code lost:
    
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final float[][] w0(char[] r22) {
        /*
            Method dump skipped, instruction units count: 529
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.a.j.c.w0(char[]):float[][]");
    }

    @Override // b.a.a.j.b
    public final void x() {
        char next;
        char next2;
        this.l = this.h;
        this.m = false;
        while (true) {
            char next3 = next();
            if (next3 == '\"') {
                this.f242d = 4;
                this.g = next();
                return;
            }
            if (next3 != 26) {
                boolean z = true;
                if (next3 == '\\') {
                    if (!this.m) {
                        this.m = true;
                        int i = this.k;
                        char[] cArr = this.j;
                        if (i >= cArr.length) {
                            int length = cArr.length * 2;
                            if (i <= length) {
                                i = length;
                            }
                            char[] cArr2 = new char[i];
                            System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
                            this.j = cArr2;
                        }
                        b0(this.l + 1, this.k, this.j);
                    }
                    char next4 = next();
                    if (next4 == '\"') {
                        l0('\"');
                    } else if (next4 != '\'') {
                        if (next4 != 'F') {
                            if (next4 == '\\') {
                                l0('\\');
                            } else if (next4 == 'b') {
                                l0('\b');
                            } else if (next4 != 'f') {
                                if (next4 == 'n') {
                                    l0('\n');
                                } else if (next4 == 'r') {
                                    l0('\r');
                                } else if (next4 != 'x') {
                                    switch (next4) {
                                        case '/':
                                            l0('/');
                                            break;
                                        case '0':
                                            l0((char) 0);
                                            break;
                                        case '1':
                                            l0((char) 1);
                                            break;
                                        case '2':
                                            l0((char) 2);
                                            break;
                                        case '3':
                                            l0((char) 3);
                                            break;
                                        case '4':
                                            l0((char) 4);
                                            break;
                                        case '5':
                                            l0((char) 5);
                                            break;
                                        case '6':
                                            l0((char) 6);
                                            break;
                                        case '7':
                                            l0((char) 7);
                                            break;
                                        default:
                                            switch (next4) {
                                                case 't':
                                                    l0('\t');
                                                    break;
                                                case 'u':
                                                    l0((char) Integer.parseInt(new String(new char[]{next(), next(), next(), next()}), 16));
                                                    break;
                                                case 'v':
                                                    l0((char) 11);
                                                    break;
                                                default:
                                                    this.g = next4;
                                                    throw new JSONException("unclosed string : " + next4);
                                            }
                                            break;
                                    }
                                } else {
                                    next = next();
                                    next2 = next();
                                    boolean z2 = (next >= '0' && next <= '9') || (next >= 'a' && next <= 'f') || (next >= 'A' && next <= 'F');
                                    if ((next2 < '0' || next2 > '9') && ((next2 < 'a' || next2 > 'f') && (next2 < 'A' || next2 > 'F'))) {
                                        z = false;
                                    }
                                    if (z2 && z) {
                                        int[] iArr = f241c;
                                        l0((char) ((iArr[next] * 16) + iArr[next2]));
                                    }
                                }
                            }
                        }
                        l0('\f');
                    } else {
                        l0('\'');
                    }
                } else if (this.m) {
                    int i2 = this.k;
                    char[] cArr3 = this.j;
                    if (i2 == cArr3.length) {
                        l0(next3);
                    } else {
                        this.k = i2 + 1;
                        cArr3[i2] = next3;
                    }
                } else {
                    this.k++;
                }
            } else {
                if (f0()) {
                    throw new JSONException("unclosed string : " + next3);
                }
                l0((char) 26);
            }
        }
        throw new JSONException("invalid escape character \\x" + next + next2);
    }

    public int x0(char[] cArr) {
        int i;
        char cA0;
        this.q = 0;
        if (!Z(cArr)) {
            this.q = -2;
            return 0;
        }
        int length = cArr.length;
        int i2 = length + 1;
        char cA02 = a0(this.h + length);
        boolean z = cA02 == '-';
        if (z) {
            cA02 = a0(this.h + i2);
            i2++;
        }
        if (cA02 < '0' || cA02 > '9') {
            this.q = -1;
            return 0;
        }
        int i3 = cA02 - '0';
        while (true) {
            i = i2 + 1;
            cA0 = a0(this.h + i2);
            if (cA0 < '0' || cA0 > '9') {
                break;
            }
            i3 = (i3 * 10) + (cA0 - '0');
            i2 = i;
        }
        if (cA0 == '.') {
            this.q = -1;
            return 0;
        }
        if ((i3 < 0 || i > cArr.length + 14) && !(i3 == Integer.MIN_VALUE && i == 17 && z)) {
            this.q = -1;
            return 0;
        }
        if (cA0 == ',') {
            int i4 = this.h + i;
            this.h = i4;
            this.g = a0(i4);
            this.q = 3;
            this.f242d = 16;
            return z ? -i3 : i3;
        }
        if (cA0 != '}') {
            this.q = -1;
            return 0;
        }
        int i5 = i + 1;
        char cA03 = a0(this.h + i);
        if (cA03 == ',') {
            this.f242d = 16;
            int i6 = this.h + i5;
            this.h = i6;
            this.g = a0(i6);
        } else if (cA03 == ']') {
            this.f242d = 15;
            int i7 = this.h + i5;
            this.h = i7;
            this.g = a0(i7);
        } else if (cA03 == '}') {
            this.f242d = 13;
            int i8 = this.h + i5;
            this.h = i8;
            this.g = a0(i8);
        } else {
            if (cA03 != 26) {
                this.q = -1;
                return 0;
            }
            this.f242d = 20;
            this.h += i5 - 1;
            this.g = (char) 26;
        }
        this.q = 4;
        return z ? -i3 : i3;
    }

    @Override // b.a.a.j.b
    public final boolean y(Feature feature) {
        return isEnabled(feature.mask);
    }

    /* JADX WARN: Code restructure failed: missing block: B:63:0x011f, code lost:
    
        r2 = r4;
        r17.q = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0122, code lost:
    
        return r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int[] y0(char[] r18) {
        /*
            Method dump skipped, instruction units count: 291
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.a.j.c.y0(char[]):int[]");
    }

    @Override // b.a.a.j.b
    public final int z() {
        int i;
        boolean z;
        int i2 = 0;
        if (this.l == -1) {
            this.l = 0;
        }
        int i3 = this.l;
        int i4 = this.k + i3;
        if (a0(i3) == '-') {
            i = Integer.MIN_VALUE;
            i3++;
            z = true;
        } else {
            i = -2147483647;
            z = false;
        }
        if (i3 < i4) {
            i2 = -(a0(i3) - '0');
            i3++;
        }
        while (i3 < i4) {
            int i5 = i3 + 1;
            char cA0 = a0(i3);
            if (cA0 == 'L' || cA0 == 'S' || cA0 == 'B') {
                i3 = i5;
                break;
            }
            int i6 = cA0 - '0';
            if (i2 < -214748364) {
                throw new NumberFormatException(T());
            }
            int i7 = i2 * 10;
            if (i7 < i + i6) {
                throw new NumberFormatException(T());
            }
            i2 = i7 - i6;
            i3 = i5;
        }
        if (!z) {
            return -i2;
        }
        if (i3 > this.l + 1) {
            return i2;
        }
        throw new NumberFormatException(T());
    }

    public long z0(char[] cArr) {
        boolean z;
        int i;
        char cA0;
        this.q = 0;
        if (!Z(cArr)) {
            this.q = -2;
            return 0L;
        }
        int length = cArr.length;
        int i2 = length + 1;
        char cA02 = a0(this.h + length);
        if (cA02 == '-') {
            cA02 = a0(this.h + i2);
            i2++;
            z = true;
        } else {
            z = false;
        }
        if (cA02 < '0' || cA02 > '9') {
            this.q = -1;
            return 0L;
        }
        long j = cA02 - '0';
        while (true) {
            i = i2 + 1;
            cA0 = a0(this.h + i2);
            if (cA0 < '0' || cA0 > '9') {
                break;
            }
            j = (j * 10) + ((long) (cA0 - '0'));
            i2 = i;
        }
        if (cA0 == '.') {
            this.q = -1;
            return 0L;
        }
        if (!(i - cArr.length < 21 && (j >= 0 || (j == Long.MIN_VALUE && z)))) {
            this.q = -1;
            return 0L;
        }
        if (cA0 == ',') {
            int i3 = this.h + i;
            this.h = i3;
            this.g = a0(i3);
            this.q = 3;
            this.f242d = 16;
            return z ? -j : j;
        }
        if (cA0 != '}') {
            this.q = -1;
            return 0L;
        }
        int i4 = i + 1;
        char cA03 = a0(this.h + i);
        if (cA03 == ',') {
            this.f242d = 16;
            int i5 = this.h + i4;
            this.h = i5;
            this.g = a0(i5);
        } else if (cA03 == ']') {
            this.f242d = 15;
            int i6 = this.h + i4;
            this.h = i6;
            this.g = a0(i6);
        } else if (cA03 == '}') {
            this.f242d = 13;
            int i7 = this.h + i4;
            this.h = i7;
            this.g = a0(i7);
        } else {
            if (cA03 != 26) {
                this.q = -1;
                return 0L;
            }
            this.f242d = 20;
            this.h += i4 - 1;
            this.g = (char) 26;
        }
        this.q = 4;
        return z ? -j : j;
    }
}
