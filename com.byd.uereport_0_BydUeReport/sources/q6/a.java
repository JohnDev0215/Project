package q6;

import com.google.gson.stream.JsonToken;
import com.google.gson.stream.MalformedJsonException;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.util.Objects;
import m6.d;
import n6.e;

/* JADX INFO: compiled from: JsonReader.java */
/* JADX INFO: loaded from: classes2.dex */
public class a implements Closeable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final char[] f7451d = ")]}'\n".toCharArray();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Reader f7452e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f7453f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final char[] f7454g = new char[1024];

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f7455h = 0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f7456i = 0;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f7457j = 0;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f7458k = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f7459l = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f7460m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f7461n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public String f7462o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int[] f7463p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f7464q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public String[] f7465r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int[] f7466s;

    /* JADX INFO: renamed from: q6.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: JsonReader.java */
    public static class C0110a extends d {
        @Override // m6.d
        public void a(a aVar) throws IOException {
            if (aVar instanceof e) {
                ((e) aVar).n0();
                return;
            }
            int iN = aVar.f7459l;
            if (iN == 0) {
                iN = aVar.n();
            }
            if (iN == 13) {
                aVar.f7459l = 9;
                return;
            }
            if (iN == 12) {
                aVar.f7459l = 8;
                return;
            }
            if (iN == 14) {
                aVar.f7459l = 10;
                return;
            }
            throw new IllegalStateException("Expected a name but was " + aVar.R() + aVar.x());
        }
    }

    static {
        d.f6422a = new C0110a();
    }

    public a(Reader reader) {
        int[] iArr = new int[32];
        this.f7463p = iArr;
        this.f7464q = 0;
        this.f7464q = 0 + 1;
        iArr[0] = 6;
        this.f7465r = new String[32];
        this.f7466s = new int[32];
        Objects.requireNonNull(reader, "in == null");
        this.f7452e = reader;
    }

    public boolean B() throws IOException {
        int iN = this.f7459l;
        if (iN == 0) {
            iN = n();
        }
        if (iN == 5) {
            this.f7459l = 0;
            int[] iArr = this.f7466s;
            int i8 = this.f7464q - 1;
            iArr[i8] = iArr[i8] + 1;
            return true;
        }
        if (iN == 6) {
            this.f7459l = 0;
            int[] iArr2 = this.f7466s;
            int i9 = this.f7464q - 1;
            iArr2[i9] = iArr2[i9] + 1;
            return false;
        }
        throw new IllegalStateException("Expected a boolean but was " + R() + x());
    }

    public double C() throws IOException {
        int iN = this.f7459l;
        if (iN == 0) {
            iN = n();
        }
        if (iN == 15) {
            this.f7459l = 0;
            int[] iArr = this.f7466s;
            int i8 = this.f7464q - 1;
            iArr[i8] = iArr[i8] + 1;
            return this.f7460m;
        }
        if (iN == 16) {
            this.f7462o = new String(this.f7454g, this.f7455h, this.f7461n);
            this.f7455h += this.f7461n;
        } else if (iN == 8 || iN == 9) {
            this.f7462o = N(iN == 8 ? '\'' : '\"');
        } else if (iN == 10) {
            this.f7462o = P();
        } else if (iN != 11) {
            throw new IllegalStateException("Expected a double but was " + R() + x());
        }
        this.f7459l = 11;
        double d8 = Double.parseDouble(this.f7462o);
        if (!this.f7453f && (Double.isNaN(d8) || Double.isInfinite(d8))) {
            throw new MalformedJsonException("JSON forbids NaN and infinities: " + d8 + x());
        }
        this.f7462o = null;
        this.f7459l = 0;
        int[] iArr2 = this.f7466s;
        int i9 = this.f7464q - 1;
        iArr2[i9] = iArr2[i9] + 1;
        return d8;
    }

    public int E() throws IOException {
        int iN = this.f7459l;
        if (iN == 0) {
            iN = n();
        }
        if (iN == 15) {
            long j8 = this.f7460m;
            int i8 = (int) j8;
            if (j8 == i8) {
                this.f7459l = 0;
                int[] iArr = this.f7466s;
                int i9 = this.f7464q - 1;
                iArr[i9] = iArr[i9] + 1;
                return i8;
            }
            throw new NumberFormatException("Expected an int but was " + this.f7460m + x());
        }
        if (iN == 16) {
            this.f7462o = new String(this.f7454g, this.f7455h, this.f7461n);
            this.f7455h += this.f7461n;
        } else {
            if (iN != 8 && iN != 9 && iN != 10) {
                throw new IllegalStateException("Expected an int but was " + R() + x());
            }
            if (iN == 10) {
                this.f7462o = P();
            } else {
                this.f7462o = N(iN == 8 ? '\'' : '\"');
            }
            try {
                int i10 = Integer.parseInt(this.f7462o);
                this.f7459l = 0;
                int[] iArr2 = this.f7466s;
                int i11 = this.f7464q - 1;
                iArr2[i11] = iArr2[i11] + 1;
                return i10;
            } catch (NumberFormatException unused) {
            }
        }
        this.f7459l = 11;
        double d8 = Double.parseDouble(this.f7462o);
        int i12 = (int) d8;
        if (i12 != d8) {
            throw new NumberFormatException("Expected an int but was " + this.f7462o + x());
        }
        this.f7462o = null;
        this.f7459l = 0;
        int[] iArr3 = this.f7466s;
        int i13 = this.f7464q - 1;
        iArr3[i13] = iArr3[i13] + 1;
        return i12;
    }

    public long F() throws IOException {
        int iN = this.f7459l;
        if (iN == 0) {
            iN = n();
        }
        if (iN == 15) {
            this.f7459l = 0;
            int[] iArr = this.f7466s;
            int i8 = this.f7464q - 1;
            iArr[i8] = iArr[i8] + 1;
            return this.f7460m;
        }
        if (iN == 16) {
            this.f7462o = new String(this.f7454g, this.f7455h, this.f7461n);
            this.f7455h += this.f7461n;
        } else {
            if (iN != 8 && iN != 9 && iN != 10) {
                throw new IllegalStateException("Expected a long but was " + R() + x());
            }
            if (iN == 10) {
                this.f7462o = P();
            } else {
                this.f7462o = N(iN == 8 ? '\'' : '\"');
            }
            try {
                long j8 = Long.parseLong(this.f7462o);
                this.f7459l = 0;
                int[] iArr2 = this.f7466s;
                int i9 = this.f7464q - 1;
                iArr2[i9] = iArr2[i9] + 1;
                return j8;
            } catch (NumberFormatException unused) {
            }
        }
        this.f7459l = 11;
        double d8 = Double.parseDouble(this.f7462o);
        long j9 = (long) d8;
        if (j9 != d8) {
            throw new NumberFormatException("Expected a long but was " + this.f7462o + x());
        }
        this.f7462o = null;
        this.f7459l = 0;
        int[] iArr3 = this.f7466s;
        int i10 = this.f7464q - 1;
        iArr3[i10] = iArr3[i10] + 1;
        return j9;
    }

    public String I() throws IOException {
        String strN;
        int iN = this.f7459l;
        if (iN == 0) {
            iN = n();
        }
        if (iN == 14) {
            strN = P();
        } else if (iN == 12) {
            strN = N('\'');
        } else {
            if (iN != 13) {
                throw new IllegalStateException("Expected a name but was " + R() + x());
            }
            strN = N('\"');
        }
        this.f7459l = 0;
        this.f7465r[this.f7464q - 1] = strN;
        return strN;
    }

    public final int L(boolean z7) throws IOException {
        char[] cArr = this.f7454g;
        int i8 = this.f7455h;
        int i9 = this.f7456i;
        while (true) {
            if (i8 == i9) {
                this.f7455h = i8;
                if (!r(1)) {
                    if (!z7) {
                        return -1;
                    }
                    throw new EOFException("End of input" + x());
                }
                i8 = this.f7455h;
                i9 = this.f7456i;
            }
            int i10 = i8 + 1;
            char c8 = cArr[i8];
            if (c8 == '\n') {
                this.f7457j++;
                this.f7458k = i10;
            } else if (c8 != ' ' && c8 != '\r' && c8 != '\t') {
                if (c8 == '/') {
                    this.f7455h = i10;
                    if (i10 == i9) {
                        this.f7455h = i10 - 1;
                        boolean zR = r(2);
                        this.f7455h++;
                        if (!zR) {
                            return c8;
                        }
                    }
                    j();
                    int i11 = this.f7455h;
                    char c9 = cArr[i11];
                    if (c9 == '*') {
                        this.f7455h = i11 + 1;
                        if (!c0("*/")) {
                            throw h0("Unterminated comment");
                        }
                        i8 = this.f7455h + 2;
                        i9 = this.f7456i;
                    } else {
                        if (c9 != '/') {
                            return c8;
                        }
                        this.f7455h = i11 + 1;
                        d0();
                        i8 = this.f7455h;
                        i9 = this.f7456i;
                    }
                } else {
                    if (c8 != '#') {
                        this.f7455h = i10;
                        return c8;
                    }
                    this.f7455h = i10;
                    j();
                    d0();
                    i8 = this.f7455h;
                    i9 = this.f7456i;
                }
            }
            i8 = i10;
        }
    }

    public void M() throws IOException {
        int iN = this.f7459l;
        if (iN == 0) {
            iN = n();
        }
        if (iN == 7) {
            this.f7459l = 0;
            int[] iArr = this.f7466s;
            int i8 = this.f7464q - 1;
            iArr[i8] = iArr[i8] + 1;
            return;
        }
        throw new IllegalStateException("Expected null but was " + R() + x());
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0046, code lost:
    
        r1.append(r0, r3, r2 - r3);
        r8.f7455h = r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String N(char r9) throws java.io.IOException {
        /*
            r8 = this;
            char[] r0 = r8.f7454g
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
        L7:
            int r2 = r8.f7455h
            int r3 = r8.f7456i
        Lb:
            r4 = r3
            r3 = r2
        Ld:
            r5 = 1
            if (r2 >= r4) goto L46
            int r6 = r2 + 1
            char r2 = r0[r2]
            if (r2 != r9) goto L22
            r8.f7455h = r6
            int r6 = r6 - r3
            int r6 = r6 - r5
            r1.append(r0, r3, r6)
            java.lang.String r9 = r1.toString()
            return r9
        L22:
            r7 = 92
            if (r2 != r7) goto L39
            r8.f7455h = r6
            int r6 = r6 - r3
            int r6 = r6 - r5
            r1.append(r0, r3, r6)
            char r2 = r8.Y()
            r1.append(r2)
            int r2 = r8.f7455h
            int r3 = r8.f7456i
            goto Lb
        L39:
            r7 = 10
            if (r2 != r7) goto L44
            int r2 = r8.f7457j
            int r2 = r2 + r5
            r8.f7457j = r2
            r8.f7458k = r6
        L44:
            r2 = r6
            goto Ld
        L46:
            int r4 = r2 - r3
            r1.append(r0, r3, r4)
            r8.f7455h = r2
            boolean r2 = r8.r(r5)
            if (r2 == 0) goto L54
            goto L7
        L54:
            java.lang.String r9 = "Unterminated string"
            java.io.IOException r9 = r8.h0(r9)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: q6.a.N(char):java.lang.String");
    }

    public String O() throws IOException {
        String str;
        int iN = this.f7459l;
        if (iN == 0) {
            iN = n();
        }
        if (iN == 10) {
            str = P();
        } else if (iN == 8) {
            str = N('\'');
        } else if (iN == 9) {
            str = N('\"');
        } else if (iN == 11) {
            str = this.f7462o;
            this.f7462o = null;
        } else if (iN == 15) {
            str = Long.toString(this.f7460m);
        } else {
            if (iN != 16) {
                throw new IllegalStateException("Expected a string but was " + R() + x());
            }
            str = new String(this.f7454g, this.f7455h, this.f7461n);
            this.f7455h += this.f7461n;
        }
        this.f7459l = 0;
        int[] iArr = this.f7466s;
        int i8 = this.f7464q - 1;
        iArr[i8] = iArr[i8] + 1;
        return str;
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x004a, code lost:
    
        j();
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:32:0x0044. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:46:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0084  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String P() throws java.io.IOException {
        /*
            r6 = this;
            r0 = 0
            r1 = 0
        L2:
            r2 = r0
        L3:
            int r3 = r6.f7455h
            int r4 = r3 + r2
            int r5 = r6.f7456i
            if (r4 >= r5) goto L4e
            char[] r4 = r6.f7454g
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
            char[] r3 = r6.f7454g
            int r3 = r3.length
            if (r2 >= r3) goto L5e
            int r3 = r2 + 1
            boolean r3 = r6.r(r3)
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
            char[] r3 = r6.f7454g
            int r4 = r6.f7455h
            r1.append(r3, r4, r2)
            int r3 = r6.f7455h
            int r3 = r3 + r2
            r6.f7455h = r3
            r2 = 1
            boolean r2 = r6.r(r2)
            if (r2 != 0) goto L2
        L78:
            if (r1 != 0) goto L84
            java.lang.String r1 = new java.lang.String
            char[] r2 = r6.f7454g
            int r3 = r6.f7455h
            r1.<init>(r2, r3, r0)
            goto L8f
        L84:
            char[] r2 = r6.f7454g
            int r3 = r6.f7455h
            r1.append(r2, r3, r0)
            java.lang.String r1 = r1.toString()
        L8f:
            int r2 = r6.f7455h
            int r2 = r2 + r0
            r6.f7455h = r2
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: q6.a.P():java.lang.String");
    }

    public JsonToken R() throws IOException {
        int iN = this.f7459l;
        if (iN == 0) {
            iN = n();
        }
        switch (iN) {
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

    public final int T() throws IOException {
        int i8;
        String str;
        String str2;
        char c8 = this.f7454g[this.f7455h];
        if (c8 == 't' || c8 == 'T') {
            i8 = 5;
            str = "true";
            str2 = "TRUE";
        } else if (c8 == 'f' || c8 == 'F') {
            i8 = 6;
            str = "false";
            str2 = "FALSE";
        } else {
            if (c8 != 'n' && c8 != 'N') {
                return 0;
            }
            i8 = 7;
            str = "null";
            str2 = "NULL";
        }
        int length = str.length();
        for (int i9 = 1; i9 < length; i9++) {
            if (this.f7455h + i9 >= this.f7456i && !r(i9 + 1)) {
                return 0;
            }
            char c9 = this.f7454g[this.f7455h + i9];
            if (c9 != str.charAt(i9) && c9 != str2.charAt(i9)) {
                return 0;
            }
        }
        if ((this.f7455h + length < this.f7456i || r(length + 1)) && w(this.f7454g[this.f7455h + length])) {
            return 0;
        }
        this.f7455h += length;
        this.f7459l = i8;
        return i8;
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x0095, code lost:
    
        if (w(r14) != false) goto L75;
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
    
        r18.f7460m = r11;
        r18.f7455h += r8;
        r18.f7459l = 15;
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
    
        r18.f7461n = r8;
        r18.f7459l = 16;
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
    public final int V() throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 243
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: q6.a.V():int");
    }

    public final void X(int i8) {
        int i9 = this.f7464q;
        int[] iArr = this.f7463p;
        if (i9 == iArr.length) {
            int[] iArr2 = new int[i9 * 2];
            int[] iArr3 = new int[i9 * 2];
            String[] strArr = new String[i9 * 2];
            System.arraycopy(iArr, 0, iArr2, 0, i9);
            System.arraycopy(this.f7466s, 0, iArr3, 0, this.f7464q);
            System.arraycopy(this.f7465r, 0, strArr, 0, this.f7464q);
            this.f7463p = iArr2;
            this.f7466s = iArr3;
            this.f7465r = strArr;
        }
        int[] iArr4 = this.f7463p;
        int i10 = this.f7464q;
        this.f7464q = i10 + 1;
        iArr4[i10] = i8;
    }

    public final char Y() throws IOException {
        int i8;
        int i9;
        if (this.f7455h == this.f7456i && !r(1)) {
            throw h0("Unterminated escape sequence");
        }
        char[] cArr = this.f7454g;
        int i10 = this.f7455h;
        int i11 = i10 + 1;
        this.f7455h = i11;
        char c8 = cArr[i10];
        if (c8 == '\n') {
            this.f7457j++;
            this.f7458k = i11;
        } else if (c8 != '\"' && c8 != '\'' && c8 != '/' && c8 != '\\') {
            if (c8 == 'b') {
                return '\b';
            }
            if (c8 == 'f') {
                return '\f';
            }
            if (c8 == 'n') {
                return '\n';
            }
            if (c8 == 'r') {
                return '\r';
            }
            if (c8 == 't') {
                return '\t';
            }
            if (c8 != 'u') {
                throw h0("Invalid escape sequence");
            }
            if (i11 + 4 > this.f7456i && !r(4)) {
                throw h0("Unterminated escape sequence");
            }
            char c9 = 0;
            int i12 = this.f7455h;
            int i13 = i12 + 4;
            while (i12 < i13) {
                char c10 = this.f7454g[i12];
                char c11 = (char) (c9 << 4);
                if (c10 < '0' || c10 > '9') {
                    if (c10 >= 'a' && c10 <= 'f') {
                        i8 = c10 - 'a';
                    } else {
                        if (c10 < 'A' || c10 > 'F') {
                            throw new NumberFormatException("\\u" + new String(this.f7454g, this.f7455h, 4));
                        }
                        i8 = c10 - 'A';
                    }
                    i9 = i8 + 10;
                } else {
                    i9 = c10 - '0';
                }
                c9 = (char) (c11 + i9);
                i12++;
            }
            this.f7455h += 4;
            return c9;
        }
        return c8;
    }

    public final void Z(boolean z7) {
        this.f7453f = z7;
    }

    public String a() {
        StringBuilder sb = new StringBuilder();
        sb.append('$');
        int i8 = this.f7464q;
        for (int i9 = 0; i9 < i8; i9++) {
            int i10 = this.f7463p[i9];
            if (i10 == 1 || i10 == 2) {
                sb.append('[');
                sb.append(this.f7466s[i9]);
                sb.append(']');
            } else if (i10 == 3 || i10 == 4 || i10 == 5) {
                sb.append('.');
                String[] strArr = this.f7465r;
                if (strArr[i9] != null) {
                    sb.append(strArr[i9]);
                }
            }
        }
        return sb.toString();
    }

    public final void a0(char c8) throws IOException {
        char[] cArr = this.f7454g;
        do {
            int i8 = this.f7455h;
            int i9 = this.f7456i;
            while (i8 < i9) {
                int i10 = i8 + 1;
                char c9 = cArr[i8];
                if (c9 == c8) {
                    this.f7455h = i10;
                    return;
                }
                if (c9 == '\\') {
                    this.f7455h = i10;
                    Y();
                    i8 = this.f7455h;
                    i9 = this.f7456i;
                } else {
                    if (c9 == '\n') {
                        this.f7457j++;
                        this.f7458k = i10;
                    }
                    i8 = i10;
                }
            }
            this.f7455h = i8;
        } while (r(1));
        throw h0("Unterminated string");
    }

    public final boolean c0(String str) throws IOException {
        while (true) {
            if (this.f7455h + str.length() > this.f7456i && !r(str.length())) {
                return false;
            }
            char[] cArr = this.f7454g;
            int i8 = this.f7455h;
            if (cArr[i8] != '\n') {
                for (int i9 = 0; i9 < str.length(); i9++) {
                    if (this.f7454g[this.f7455h + i9] != str.charAt(i9)) {
                        break;
                    }
                }
                return true;
            }
            this.f7457j++;
            this.f7458k = i8 + 1;
            this.f7455h++;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f7459l = 0;
        this.f7463p[0] = 8;
        this.f7464q = 1;
        this.f7452e.close();
    }

    public final void d0() throws IOException {
        char c8;
        do {
            if (this.f7455h >= this.f7456i && !r(1)) {
                return;
            }
            char[] cArr = this.f7454g;
            int i8 = this.f7455h;
            int i9 = i8 + 1;
            this.f7455h = i9;
            c8 = cArr[i8];
            if (c8 == '\n') {
                this.f7457j++;
                this.f7458k = i9;
                return;
            }
        } while (c8 != '\r');
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0048, code lost:
    
        j();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void e0() throws java.io.IOException {
        /*
            r4 = this;
        L0:
            r0 = 0
        L1:
            int r1 = r4.f7455h
            int r2 = r1 + r0
            int r3 = r4.f7456i
            if (r2 >= r3) goto L51
            char[] r2 = r4.f7454g
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
            int r1 = r4.f7455h
            int r1 = r1 + r0
            r4.f7455h = r1
            return
        L51:
            int r1 = r1 + r0
            r4.f7455h = r1
            r0 = 1
            boolean r0 = r4.r(r0)
            if (r0 != 0) goto L0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: q6.a.e0():void");
    }

    public void g() throws IOException {
        int iN = this.f7459l;
        if (iN == 0) {
            iN = n();
        }
        if (iN == 3) {
            X(1);
            this.f7466s[this.f7464q - 1] = 0;
            this.f7459l = 0;
        } else {
            throw new IllegalStateException("Expected BEGIN_ARRAY but was " + R() + x());
        }
    }

    public void g0() throws IOException {
        int i8 = 0;
        do {
            int iN = this.f7459l;
            if (iN == 0) {
                iN = n();
            }
            if (iN == 3) {
                X(1);
            } else if (iN == 1) {
                X(3);
            } else if (iN == 4 || iN == 2) {
                this.f7464q--;
                i8--;
                this.f7459l = 0;
            } else {
                if (iN == 14 || iN == 10) {
                    e0();
                } else if (iN == 8 || iN == 12) {
                    a0('\'');
                } else if (iN == 9 || iN == 13) {
                    a0('\"');
                } else if (iN == 16) {
                    this.f7455h += this.f7461n;
                }
                this.f7459l = 0;
            }
            i8++;
            this.f7459l = 0;
        } while (i8 != 0);
        int[] iArr = this.f7466s;
        int i9 = this.f7464q;
        int i10 = i9 - 1;
        iArr[i10] = iArr[i10] + 1;
        this.f7465r[i9 - 1] = "null";
    }

    public final IOException h0(String str) throws IOException {
        throw new MalformedJsonException(str + x());
    }

    public void i() throws IOException {
        int iN = this.f7459l;
        if (iN == 0) {
            iN = n();
        }
        if (iN == 1) {
            X(3);
            this.f7459l = 0;
        } else {
            throw new IllegalStateException("Expected BEGIN_OBJECT but was " + R() + x());
        }
    }

    public final void j() throws IOException {
        if (!this.f7453f) {
            throw h0("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    public final void m() throws IOException {
        L(true);
        int i8 = this.f7455h - 1;
        this.f7455h = i8;
        char[] cArr = f7451d;
        if (i8 + cArr.length > this.f7456i && !r(cArr.length)) {
            return;
        }
        int i9 = 0;
        while (true) {
            char[] cArr2 = f7451d;
            if (i9 >= cArr2.length) {
                this.f7455h += cArr2.length;
                return;
            } else if (this.f7454g[this.f7455h + i9] != cArr2[i9]) {
                return;
            } else {
                i9++;
            }
        }
    }

    public int n() throws IOException {
        int iL;
        int[] iArr = this.f7463p;
        int i8 = this.f7464q;
        int i9 = iArr[i8 - 1];
        if (i9 == 1) {
            iArr[i8 - 1] = 2;
        } else if (i9 == 2) {
            int iL2 = L(true);
            if (iL2 != 44) {
                if (iL2 != 59) {
                    if (iL2 != 93) {
                        throw h0("Unterminated array");
                    }
                    this.f7459l = 4;
                    return 4;
                }
                j();
            }
        } else {
            if (i9 == 3 || i9 == 5) {
                iArr[i8 - 1] = 4;
                if (i9 == 5 && (iL = L(true)) != 44) {
                    if (iL != 59) {
                        if (iL != 125) {
                            throw h0("Unterminated object");
                        }
                        this.f7459l = 2;
                        return 2;
                    }
                    j();
                }
                int iL3 = L(true);
                if (iL3 == 34) {
                    this.f7459l = 13;
                    return 13;
                }
                if (iL3 == 39) {
                    j();
                    this.f7459l = 12;
                    return 12;
                }
                if (iL3 == 125) {
                    if (i9 == 5) {
                        throw h0("Expected name");
                    }
                    this.f7459l = 2;
                    return 2;
                }
                j();
                this.f7455h--;
                if (!w((char) iL3)) {
                    throw h0("Expected name");
                }
                this.f7459l = 14;
                return 14;
            }
            if (i9 == 4) {
                iArr[i8 - 1] = 5;
                int iL4 = L(true);
                if (iL4 != 58) {
                    if (iL4 != 61) {
                        throw h0("Expected ':'");
                    }
                    j();
                    if (this.f7455h < this.f7456i || r(1)) {
                        char[] cArr = this.f7454g;
                        int i10 = this.f7455h;
                        if (cArr[i10] == '>') {
                            this.f7455h = i10 + 1;
                        }
                    }
                }
            } else if (i9 == 6) {
                if (this.f7453f) {
                    m();
                }
                this.f7463p[this.f7464q - 1] = 7;
            } else if (i9 == 7) {
                if (L(false) == -1) {
                    this.f7459l = 17;
                    return 17;
                }
                j();
                this.f7455h--;
            } else if (i9 == 8) {
                throw new IllegalStateException("JsonReader is closed");
            }
        }
        int iL5 = L(true);
        if (iL5 == 34) {
            this.f7459l = 9;
            return 9;
        }
        if (iL5 == 39) {
            j();
            this.f7459l = 8;
            return 8;
        }
        if (iL5 != 44 && iL5 != 59) {
            if (iL5 == 91) {
                this.f7459l = 3;
                return 3;
            }
            if (iL5 != 93) {
                if (iL5 == 123) {
                    this.f7459l = 1;
                    return 1;
                }
                this.f7455h--;
                int iT = T();
                if (iT != 0) {
                    return iT;
                }
                int iV = V();
                if (iV != 0) {
                    return iV;
                }
                if (!w(this.f7454g[this.f7455h])) {
                    throw h0("Expected value");
                }
                j();
                this.f7459l = 10;
                return 10;
            }
            if (i9 == 1) {
                this.f7459l = 4;
                return 4;
            }
        }
        if (i9 != 1 && i9 != 2) {
            throw h0("Unexpected value");
        }
        j();
        this.f7455h--;
        this.f7459l = 7;
        return 7;
    }

    public void o() throws IOException {
        int iN = this.f7459l;
        if (iN == 0) {
            iN = n();
        }
        if (iN != 4) {
            throw new IllegalStateException("Expected END_ARRAY but was " + R() + x());
        }
        int i8 = this.f7464q - 1;
        this.f7464q = i8;
        int[] iArr = this.f7466s;
        int i9 = i8 - 1;
        iArr[i9] = iArr[i9] + 1;
        this.f7459l = 0;
    }

    public void q() throws IOException {
        int iN = this.f7459l;
        if (iN == 0) {
            iN = n();
        }
        if (iN != 2) {
            throw new IllegalStateException("Expected END_OBJECT but was " + R() + x());
        }
        int i8 = this.f7464q - 1;
        this.f7464q = i8;
        this.f7465r[i8] = null;
        int[] iArr = this.f7466s;
        int i9 = i8 - 1;
        iArr[i9] = iArr[i9] + 1;
        this.f7459l = 0;
    }

    public final boolean r(int i8) throws IOException {
        int i9;
        int i10;
        char[] cArr = this.f7454g;
        int i11 = this.f7458k;
        int i12 = this.f7455h;
        this.f7458k = i11 - i12;
        int i13 = this.f7456i;
        if (i13 != i12) {
            int i14 = i13 - i12;
            this.f7456i = i14;
            System.arraycopy(cArr, i12, cArr, 0, i14);
        } else {
            this.f7456i = 0;
        }
        this.f7455h = 0;
        do {
            Reader reader = this.f7452e;
            int i15 = this.f7456i;
            int i16 = reader.read(cArr, i15, cArr.length - i15);
            if (i16 == -1) {
                return false;
            }
            i9 = this.f7456i + i16;
            this.f7456i = i9;
            if (this.f7457j == 0 && (i10 = this.f7458k) == 0 && i9 > 0 && cArr[0] == 65279) {
                this.f7455h++;
                this.f7458k = i10 + 1;
                i8++;
            }
        } while (i9 < i8);
        return true;
    }

    public boolean t() throws IOException {
        int iN = this.f7459l;
        if (iN == 0) {
            iN = n();
        }
        return (iN == 2 || iN == 4) ? false : true;
    }

    public String toString() {
        return getClass().getSimpleName() + x();
    }

    public final boolean v() {
        return this.f7453f;
    }

    public final boolean w(char c8) throws IOException {
        if (c8 == '\t' || c8 == '\n' || c8 == '\f' || c8 == '\r' || c8 == ' ') {
            return false;
        }
        if (c8 != '#') {
            if (c8 == ',') {
                return false;
            }
            if (c8 != '/' && c8 != '=') {
                if (c8 == '{' || c8 == '}' || c8 == ':') {
                    return false;
                }
                if (c8 != ';') {
                    switch (c8) {
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

    public final String x() {
        return " at line " + (this.f7457j + 1) + " column " + ((this.f7455h - this.f7458k) + 1) + " path " + a();
    }
}
