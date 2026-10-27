package f;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;
import okio.Buffer;

/* JADX INFO: compiled from: HttpUrl.java */
/* JADX INFO: loaded from: classes.dex */
public final class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final char[] f5514a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final String f5515b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f5516c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String f5517d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final String f5518e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final int f5519f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final List<String> f5520g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    @Nullable
    private final List<String> f5521h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    @Nullable
    private final String f5522i;
    private final String j;

    /* JADX INFO: compiled from: HttpUrl.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        @Nullable
        String f5523a;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        @Nullable
        String f5526d;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final List<String> f5528f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        @Nullable
        List<String> f5529g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        @Nullable
        String f5530h;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        String f5524b = "";

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        String f5525c = "";

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f5527e = -1;

        public a() {
            ArrayList arrayList = new ArrayList();
            this.f5528f = arrayList;
            arrayList.add("");
        }

        @Nullable
        private static String d(String str, int i2, int i3) {
            return f.k0.e.c(x.t(str, i2, i3, false));
        }

        private boolean h(String str) {
            return str.equals(".") || str.equalsIgnoreCase("%2e");
        }

        private boolean i(String str) {
            return str.equals("..") || str.equalsIgnoreCase("%2e.") || str.equalsIgnoreCase(".%2e") || str.equalsIgnoreCase("%2e%2e");
        }

        private static int k(String str, int i2, int i3) {
            int i4;
            try {
                i4 = Integer.parseInt(x.a(str, i2, i3, "", false, false, false, true, null));
            } catch (NumberFormatException unused) {
            }
            if (i4 <= 0 || i4 > 65535) {
                return -1;
            }
            return i4;
        }

        private void m() {
            if (!this.f5528f.remove(r0.size() - 1).isEmpty() || this.f5528f.isEmpty()) {
                this.f5528f.add("");
            } else {
                this.f5528f.set(r0.size() - 1, "");
            }
        }

        private static int o(String str, int i2, int i3) {
            while (i2 < i3) {
                char cCharAt = str.charAt(i2);
                if (cCharAt == ':') {
                    return i2;
                }
                if (cCharAt == '[') {
                    do {
                        i2++;
                        if (i2 < i3) {
                        }
                    } while (str.charAt(i2) != ']');
                }
                i2++;
            }
            return i3;
        }

        private void p(String str, int i2, int i3, boolean z, boolean z2) {
            String strA = x.a(str, i2, i3, " \"<>^`{}|/\\?#", z2, false, false, true, null);
            if (h(strA)) {
                return;
            }
            if (i(strA)) {
                m();
                return;
            }
            if (this.f5528f.get(r11.size() - 1).isEmpty()) {
                this.f5528f.set(r11.size() - 1, strA);
            } else {
                this.f5528f.add(strA);
            }
            if (z) {
                this.f5528f.add("");
            }
        }

        private void r(String str, int i2, int i3) {
            if (i2 == i3) {
                return;
            }
            char cCharAt = str.charAt(i2);
            if (cCharAt == '/' || cCharAt == '\\') {
                this.f5528f.clear();
                this.f5528f.add("");
                i2++;
            } else {
                List<String> list = this.f5528f;
                list.set(list.size() - 1, "");
            }
            while (true) {
                int i4 = i2;
                if (i4 >= i3) {
                    return;
                }
                i2 = f.k0.e.n(str, i4, i3, "/\\");
                boolean z = i2 < i3;
                p(str, i4, i2, z, true);
                if (z) {
                    i2++;
                }
            }
        }

        private static int t(String str, int i2, int i3) {
            if (i3 - i2 < 2) {
                return -1;
            }
            char cCharAt = str.charAt(i2);
            if ((cCharAt >= 'a' && cCharAt <= 'z') || (cCharAt >= 'A' && cCharAt <= 'Z')) {
                while (true) {
                    i2++;
                    if (i2 >= i3) {
                        break;
                    }
                    char cCharAt2 = str.charAt(i2);
                    if (cCharAt2 < 'a' || cCharAt2 > 'z') {
                        if (cCharAt2 < 'A' || cCharAt2 > 'Z') {
                            if (cCharAt2 < '0' || cCharAt2 > '9') {
                                if (cCharAt2 != '+' && cCharAt2 != '-' && cCharAt2 != '.') {
                                    if (cCharAt2 == ':') {
                                        return i2;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return -1;
        }

        private static int u(String str, int i2, int i3) {
            int i4 = 0;
            while (i2 < i3) {
                char cCharAt = str.charAt(i2);
                if (cCharAt != '\\' && cCharAt != '/') {
                    break;
                }
                i4++;
                i2++;
            }
            return i4;
        }

        public a a(String str, @Nullable String str2) {
            Objects.requireNonNull(str, "encodedName == null");
            if (this.f5529g == null) {
                this.f5529g = new ArrayList();
            }
            this.f5529g.add(x.b(str, " \"'<>#&=", true, false, true, true));
            this.f5529g.add(str2 != null ? x.b(str2, " \"'<>#&=", true, false, true, true) : null);
            return this;
        }

        public a b(String str, @Nullable String str2) {
            Objects.requireNonNull(str, "name == null");
            if (this.f5529g == null) {
                this.f5529g = new ArrayList();
            }
            this.f5529g.add(x.b(str, " !\"#$&'(),/:;<=>?@[]\\^`{|}~", false, false, true, true));
            this.f5529g.add(str2 != null ? x.b(str2, " !\"#$&'(),/:;<=>?@[]\\^`{|}~", false, false, true, true) : null);
            return this;
        }

        public x c() {
            if (this.f5523a == null) {
                throw new IllegalStateException("scheme == null");
            }
            if (this.f5526d != null) {
                return new x(this);
            }
            throw new IllegalStateException("host == null");
        }

        int e() {
            int i2 = this.f5527e;
            return i2 != -1 ? i2 : x.e(this.f5523a);
        }

        public a f(@Nullable String str) {
            this.f5529g = str != null ? x.A(x.b(str, " \"'<>#", true, false, true, true)) : null;
            return this;
        }

        public a g(String str) {
            Objects.requireNonNull(str, "host == null");
            String strD = d(str, 0, str.length());
            if (strD != null) {
                this.f5526d = strD;
                return this;
            }
            throw new IllegalArgumentException("unexpected host: " + str);
        }

        a j(@Nullable x xVar, String str) {
            int iN;
            int i2;
            int iF = f.k0.e.F(str, 0, str.length());
            int iG = f.k0.e.G(str, iF, str.length());
            int iT = t(str, iF, iG);
            if (iT != -1) {
                if (str.regionMatches(true, iF, "https:", 0, 6)) {
                    this.f5523a = "https";
                    iF += 6;
                } else {
                    if (!str.regionMatches(true, iF, "http:", 0, 5)) {
                        throw new IllegalArgumentException("Expected URL scheme 'http' or 'https' but was '" + str.substring(0, iT) + "'");
                    }
                    this.f5523a = "http";
                    iF += 5;
                }
            } else {
                if (xVar == null) {
                    throw new IllegalArgumentException("Expected URL scheme 'http' or 'https' but no colon was found");
                }
                this.f5523a = xVar.f5515b;
            }
            int iU = u(str, iF, iG);
            char c2 = '?';
            char c3 = '#';
            if (iU >= 2 || xVar == null || !xVar.f5515b.equals(this.f5523a)) {
                boolean z = false;
                boolean z2 = false;
                int i3 = iF + iU;
                while (true) {
                    iN = f.k0.e.n(str, i3, iG, "@/\\?#");
                    byte bCharAt = iN != iG ? str.charAt(iN) : (byte) -1;
                    if (bCharAt == -1 || bCharAt == c3 || bCharAt == 47 || bCharAt == 92 || bCharAt == c2) {
                        break;
                    }
                    if (bCharAt == 64) {
                        if (z) {
                            i2 = iN;
                            this.f5525c += "%40" + x.a(str, i3, i2, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true, null);
                        } else {
                            int iM = f.k0.e.m(str, i3, iN, ':');
                            i2 = iN;
                            String strA = x.a(str, i3, iM, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true, null);
                            if (z2) {
                                strA = this.f5524b + "%40" + strA;
                            }
                            this.f5524b = strA;
                            if (iM != i2) {
                                this.f5525c = x.a(str, iM + 1, i2, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true, null);
                                z = true;
                            }
                            z2 = true;
                        }
                        i3 = i2 + 1;
                    }
                    c2 = '?';
                    c3 = '#';
                }
                int iO = o(str, i3, iN);
                int i4 = iO + 1;
                if (i4 < iN) {
                    this.f5526d = d(str, i3, iO);
                    int iK = k(str, i4, iN);
                    this.f5527e = iK;
                    if (iK == -1) {
                        throw new IllegalArgumentException("Invalid URL port: \"" + str.substring(i4, iN) + '\"');
                    }
                } else {
                    this.f5526d = d(str, i3, iO);
                    this.f5527e = x.e(this.f5523a);
                }
                if (this.f5526d == null) {
                    throw new IllegalArgumentException("Invalid URL host: \"" + str.substring(i3, iO) + '\"');
                }
                iF = iN;
            } else {
                this.f5524b = xVar.k();
                this.f5525c = xVar.g();
                this.f5526d = xVar.f5518e;
                this.f5527e = xVar.f5519f;
                this.f5528f.clear();
                this.f5528f.addAll(xVar.i());
                if (iF == iG || str.charAt(iF) == '#') {
                    f(xVar.j());
                }
            }
            int iN2 = f.k0.e.n(str, iF, iG, "?#");
            r(str, iF, iN2);
            if (iN2 < iG && str.charAt(iN2) == '?') {
                int iM2 = f.k0.e.m(str, iN2, iG, '#');
                this.f5529g = x.A(x.a(str, iN2 + 1, iM2, " \"'<>#", true, false, true, true, null));
                iN2 = iM2;
            }
            if (iN2 < iG && str.charAt(iN2) == '#') {
                this.f5530h = x.a(str, 1 + iN2, iG, "", true, false, false, false, null);
            }
            return this;
        }

        public a l(String str) {
            Objects.requireNonNull(str, "password == null");
            this.f5525c = x.b(str, " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
            return this;
        }

        public a n(int i2) {
            if (i2 > 0 && i2 <= 65535) {
                this.f5527e = i2;
                return this;
            }
            throw new IllegalArgumentException("unexpected port: " + i2);
        }

        a q() {
            int size = this.f5528f.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.f5528f.set(i2, x.b(this.f5528f.get(i2), "[]", true, true, false, true));
            }
            List<String> list = this.f5529g;
            if (list != null) {
                int size2 = list.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    String str = this.f5529g.get(i3);
                    if (str != null) {
                        this.f5529g.set(i3, x.b(str, "\\^`{|}", true, true, true, true));
                    }
                }
            }
            String str2 = this.f5530h;
            if (str2 != null) {
                this.f5530h = x.b(str2, " \"#<>\\^`{|}", true, true, false, false);
            }
            return this;
        }

        public a s(String str) {
            Objects.requireNonNull(str, "scheme == null");
            if (str.equalsIgnoreCase("http")) {
                this.f5523a = "http";
            } else {
                if (!str.equalsIgnoreCase("https")) {
                    throw new IllegalArgumentException("unexpected scheme: " + str);
                }
                this.f5523a = "https";
            }
            return this;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            String str = this.f5523a;
            if (str != null) {
                sb.append(str);
                sb.append("://");
            } else {
                sb.append("//");
            }
            if (!this.f5524b.isEmpty() || !this.f5525c.isEmpty()) {
                sb.append(this.f5524b);
                if (!this.f5525c.isEmpty()) {
                    sb.append(':');
                    sb.append(this.f5525c);
                }
                sb.append('@');
            }
            String str2 = this.f5526d;
            if (str2 != null) {
                if (str2.indexOf(58) != -1) {
                    sb.append('[');
                    sb.append(this.f5526d);
                    sb.append(']');
                } else {
                    sb.append(this.f5526d);
                }
            }
            if (this.f5527e != -1 || this.f5523a != null) {
                int iE = e();
                String str3 = this.f5523a;
                if (str3 == null || iE != x.e(str3)) {
                    sb.append(':');
                    sb.append(iE);
                }
            }
            x.s(sb, this.f5528f);
            if (this.f5529g != null) {
                sb.append('?');
                x.o(sb, this.f5529g);
            }
            if (this.f5530h != null) {
                sb.append('#');
                sb.append(this.f5530h);
            }
            return sb.toString();
        }

        public a v(String str) {
            Objects.requireNonNull(str, "username == null");
            this.f5524b = x.b(str, " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
            return this;
        }
    }

    x(a aVar) {
        this.f5515b = aVar.f5523a;
        this.f5516c = u(aVar.f5524b, false);
        this.f5517d = u(aVar.f5525c, false);
        this.f5518e = aVar.f5526d;
        this.f5519f = aVar.e();
        this.f5520g = v(aVar.f5528f, false);
        List<String> list = aVar.f5529g;
        this.f5521h = list != null ? v(list, true) : null;
        String str = aVar.f5530h;
        this.f5522i = str != null ? u(str, false) : null;
        this.j = aVar.toString();
    }

    static List<String> A(String str) {
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (i2 <= str.length()) {
            int iIndexOf = str.indexOf(38, i2);
            if (iIndexOf == -1) {
                iIndexOf = str.length();
            }
            int iIndexOf2 = str.indexOf(61, i2);
            if (iIndexOf2 == -1 || iIndexOf2 > iIndexOf) {
                arrayList.add(str.substring(i2, iIndexOf));
                arrayList.add(null);
            } else {
                arrayList.add(str.substring(i2, iIndexOf2));
                arrayList.add(str.substring(iIndexOf2 + 1, iIndexOf));
            }
            i2 = iIndexOf + 1;
        }
        return arrayList;
    }

    static String a(String str, int i2, int i3, String str2, boolean z, boolean z2, boolean z3, boolean z4, @Nullable Charset charset) {
        int iCharCount = i2;
        while (iCharCount < i3) {
            int iCodePointAt = str.codePointAt(iCharCount);
            if (iCodePointAt < 32 || iCodePointAt == 127 || (iCodePointAt >= 128 && z4)) {
                Buffer buffer = new Buffer();
                buffer.v0(str, i2, iCharCount);
                d(buffer, str, iCharCount, i3, str2, z, z2, z3, z4, charset);
                return buffer.O();
            }
            if (str2.indexOf(iCodePointAt) != -1 || ((iCodePointAt == 37 && (!z || (z2 && !x(str, iCharCount, i3)))) || (iCodePointAt == 43 && z3))) {
                Buffer buffer2 = new Buffer();
                buffer2.v0(str, i2, iCharCount);
                d(buffer2, str, iCharCount, i3, str2, z, z2, z3, z4, charset);
                return buffer2.O();
            }
            iCharCount += Character.charCount(iCodePointAt);
        }
        return str.substring(i2, i3);
    }

    static String b(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        return a(str, 0, str.length(), str2, z, z2, z3, z4, null);
    }

    static String c(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4, @Nullable Charset charset) {
        return a(str, 0, str.length(), str2, z, z2, z3, z4, charset);
    }

    static void d(Buffer buffer, String str, int i2, int i3, String str2, boolean z, boolean z2, boolean z3, boolean z4, @Nullable Charset charset) {
        Buffer buffer2 = null;
        while (i2 < i3) {
            int iCodePointAt = str.codePointAt(i2);
            if (!z || (iCodePointAt != 9 && iCodePointAt != 10 && iCodePointAt != 12 && iCodePointAt != 13)) {
                if (iCodePointAt == 43 && z3) {
                    buffer.D(z ? "+" : "%2B");
                } else if (iCodePointAt < 32 || iCodePointAt == 127 || ((iCodePointAt >= 128 && z4) || str2.indexOf(iCodePointAt) != -1 || (iCodePointAt == 37 && (!z || (z2 && !x(str, i2, i3)))))) {
                    if (buffer2 == null) {
                        buffer2 = new Buffer();
                    }
                    if (charset == null || charset.equals(StandardCharsets.UTF_8)) {
                        buffer2.w0(iCodePointAt);
                    } else {
                        buffer2.t0(str, i2, Character.charCount(iCodePointAt) + i2, charset);
                    }
                    while (!buffer2.I()) {
                        int iP = buffer2.P() & 255;
                        buffer.J(37);
                        char[] cArr = f5514a;
                        buffer.J(cArr[(iP >> 4) & 15]);
                        buffer.J(cArr[iP & 15]);
                    }
                } else {
                    buffer.w0(iCodePointAt);
                }
            }
            i2 += Character.charCount(iCodePointAt);
        }
    }

    public static int e(String str) {
        if (str.equals("http")) {
            return 80;
        }
        return str.equals("https") ? 443 : -1;
    }

    public static x l(String str) {
        return new a().j(null, str).c();
    }

    static void o(StringBuilder sb, List<String> list) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2 += 2) {
            String str = list.get(i2);
            String str2 = list.get(i2 + 1);
            if (i2 > 0) {
                sb.append('&');
            }
            sb.append(str);
            if (str2 != null) {
                sb.append('=');
                sb.append(str2);
            }
        }
    }

    static void s(StringBuilder sb, List<String> list) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            sb.append('/');
            sb.append(list.get(i2));
        }
    }

    static String t(String str, int i2, int i3, boolean z) {
        for (int i4 = i2; i4 < i3; i4++) {
            char cCharAt = str.charAt(i4);
            if (cCharAt == '%' || (cCharAt == '+' && z)) {
                Buffer buffer = new Buffer();
                buffer.v0(str, i2, i4);
                w(buffer, str, i4, i3, z);
                return buffer.O();
            }
        }
        return str.substring(i2, i3);
    }

    static String u(String str, boolean z) {
        return t(str, 0, str.length(), z);
    }

    private List<String> v(List<String> list, boolean z) {
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i2 = 0; i2 < size; i2++) {
            String str = list.get(i2);
            arrayList.add(str != null ? u(str, z) : null);
        }
        return Collections.unmodifiableList(arrayList);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0039  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static void w(okio.Buffer r5, java.lang.String r6, int r7, int r8, boolean r9) {
        /*
        L0:
            if (r7 >= r8) goto L42
            int r0 = r6.codePointAt(r7)
            r1 = 37
            if (r0 != r1) goto L2d
            int r1 = r7 + 2
            if (r1 >= r8) goto L2d
            int r2 = r7 + 1
            char r2 = r6.charAt(r2)
            int r2 = f.k0.e.j(r2)
            char r3 = r6.charAt(r1)
            int r3 = f.k0.e.j(r3)
            r4 = -1
            if (r2 == r4) goto L39
            if (r3 == r4) goto L39
            int r7 = r2 << 4
            int r7 = r7 + r3
            r5.J(r7)
            r7 = r1
            goto L3c
        L2d:
            r1 = 43
            if (r0 != r1) goto L39
            if (r9 == 0) goto L39
            r1 = 32
            r5.J(r1)
            goto L3c
        L39:
            r5.w0(r0)
        L3c:
            int r0 = java.lang.Character.charCount(r0)
            int r7 = r7 + r0
            goto L0
        L42:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: f.x.w(okio.Buffer, java.lang.String, int, int, boolean):void");
    }

    static boolean x(String str, int i2, int i3) {
        int i4 = i2 + 2;
        return i4 < i3 && str.charAt(i2) == '%' && f.k0.e.j(str.charAt(i2 + 1)) != -1 && f.k0.e.j(str.charAt(i4)) != -1;
    }

    public String B() {
        return q("/...").v("").l("").c().toString();
    }

    @Nullable
    public x C(String str) {
        a aVarQ = q(str);
        if (aVarQ != null) {
            return aVarQ.c();
        }
        return null;
    }

    public String D() {
        return this.f5515b;
    }

    public URI E() {
        String string = p().q().toString();
        try {
            return new URI(string);
        } catch (URISyntaxException e2) {
            try {
                return URI.create(string.replaceAll("[\\u0000-\\u001F\\u007F-\\u009F\\p{javaWhitespace}]", ""));
            } catch (Exception unused) {
                throw new RuntimeException(e2);
            }
        }
    }

    public boolean equals(@Nullable Object obj) {
        return (obj instanceof x) && ((x) obj).j.equals(this.j);
    }

    @Nullable
    public String f() {
        if (this.f5522i == null) {
            return null;
        }
        return this.j.substring(this.j.indexOf(35) + 1);
    }

    public String g() {
        if (this.f5517d.isEmpty()) {
            return "";
        }
        return this.j.substring(this.j.indexOf(58, this.f5515b.length() + 3) + 1, this.j.indexOf(64));
    }

    public String h() {
        int iIndexOf = this.j.indexOf(47, this.f5515b.length() + 3);
        String str = this.j;
        return this.j.substring(iIndexOf, f.k0.e.n(str, iIndexOf, str.length(), "?#"));
    }

    public int hashCode() {
        return this.j.hashCode();
    }

    public List<String> i() {
        int iIndexOf = this.j.indexOf(47, this.f5515b.length() + 3);
        String str = this.j;
        int iN = f.k0.e.n(str, iIndexOf, str.length(), "?#");
        ArrayList arrayList = new ArrayList();
        while (iIndexOf < iN) {
            int i2 = iIndexOf + 1;
            int iM = f.k0.e.m(this.j, i2, iN, '/');
            arrayList.add(this.j.substring(i2, iM));
            iIndexOf = iM;
        }
        return arrayList;
    }

    @Nullable
    public String j() {
        if (this.f5521h == null) {
            return null;
        }
        int iIndexOf = this.j.indexOf(63) + 1;
        String str = this.j;
        return this.j.substring(iIndexOf, f.k0.e.m(str, iIndexOf, str.length(), '#'));
    }

    public String k() {
        if (this.f5516c.isEmpty()) {
            return "";
        }
        int length = this.f5515b.length() + 3;
        String str = this.j;
        return this.j.substring(length, f.k0.e.n(str, length, str.length(), ":@"));
    }

    public String m() {
        return this.f5518e;
    }

    public boolean n() {
        return this.f5515b.equals("https");
    }

    public a p() {
        a aVar = new a();
        aVar.f5523a = this.f5515b;
        aVar.f5524b = k();
        aVar.f5525c = g();
        aVar.f5526d = this.f5518e;
        aVar.f5527e = this.f5519f != e(this.f5515b) ? this.f5519f : -1;
        aVar.f5528f.clear();
        aVar.f5528f.addAll(i());
        aVar.f(j());
        aVar.f5530h = f();
        return aVar;
    }

    @Nullable
    public a q(String str) {
        try {
            return new a().j(this, str);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public List<String> r() {
        return this.f5520g;
    }

    public String toString() {
        return this.j;
    }

    public int y() {
        return this.f5519f;
    }

    @Nullable
    public String z() {
        if (this.f5521h == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        o(sb, this.f5521h);
        return sb.toString();
    }
}
