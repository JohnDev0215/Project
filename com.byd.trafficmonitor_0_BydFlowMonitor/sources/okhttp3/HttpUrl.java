package okhttp3;

import f.b0.c;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class HttpUrl {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final char[] f5537a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f5538b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f5539c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f5540d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f5541e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f5542f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final List<String> f5543g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final List<String> f5544h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f5545i;
    public final String j;

    public static final class Builder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f5546a;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f5549d;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final List<String> f5551f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public List<String> f5552g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public String f5553h;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f5547b = "";

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f5548c = "";

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f5550e = -1;

        public enum ParseResult {
            SUCCESS,
            MISSING_SCHEME,
            UNSUPPORTED_SCHEME,
            INVALID_PORT,
            INVALID_HOST
        }

        public Builder() {
            ArrayList arrayList = new ArrayList();
            this.f5551f = arrayList;
            arrayList.add("");
        }

        public static String b(String str, int i2, int i3) {
            String strS = HttpUrl.s(str, i2, i3, false);
            if (!strS.contains(":")) {
                return c.j(strS);
            }
            InetAddress inetAddressD = (strS.startsWith("[") && strS.endsWith("]")) ? d(strS, 1, strS.length() - 1) : d(strS, 0, strS.length());
            if (inetAddressD == null) {
                return null;
            }
            byte[] address = inetAddressD.getAddress();
            if (address.length == 16) {
                return h(address);
            }
            throw new AssertionError();
        }

        public static boolean c(String str, int i2, int i3, byte[] bArr, int i4) {
            int i5 = i4;
            while (i2 < i3) {
                if (i5 == bArr.length) {
                    return false;
                }
                if (i5 != i4) {
                    if (str.charAt(i2) != '.') {
                        return false;
                    }
                    i2++;
                }
                int i6 = i2;
                int i7 = 0;
                while (i6 < i3) {
                    char cCharAt = str.charAt(i6);
                    if (cCharAt < '0' || cCharAt > '9') {
                        break;
                    }
                    if ((i7 == 0 && i2 != i6) || (i7 = ((i7 * 10) + cCharAt) - 48) > 255) {
                        return false;
                    }
                    i6++;
                }
                if (i6 - i2 == 0) {
                    return false;
                }
                bArr[i5] = (byte) i7;
                i5++;
                i2 = i6;
            }
            return i5 == i4 + 4;
        }

        /* JADX WARN: Code restructure failed: missing block: B:42:0x007a, code lost:
        
            if (r4 == 16) goto L50;
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x007c, code lost:
        
            if (r5 != (-1)) goto L45;
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x007e, code lost:
        
            return null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x007f, code lost:
        
            r11 = r4 - r5;
            java.lang.System.arraycopy(r1, r5, r1, 16 - r11, r11);
            java.util.Arrays.fill(r1, r5, (16 - r4) + r5, (byte) 0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x008f, code lost:
        
            return java.net.InetAddress.getByAddress(r1);
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x0095, code lost:
        
            throw new java.lang.AssertionError();
         */
        /* JADX WARN: Removed duplicated region for block: B:31:0x004f  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static java.net.InetAddress d(java.lang.String r11, int r12, int r13) {
            /*
                r0 = 16
                byte[] r1 = new byte[r0]
                r2 = -1
                r3 = 0
                r5 = r2
                r6 = r5
                r4 = r3
            L9:
                r7 = 0
                if (r12 >= r13) goto L7a
                if (r4 != r0) goto Lf
                return r7
            Lf:
                int r8 = r12 + 2
                if (r8 > r13) goto L27
                java.lang.String r9 = "::"
                r10 = 2
                boolean r9 = r11.regionMatches(r12, r9, r3, r10)
                if (r9 == 0) goto L27
                if (r5 == r2) goto L1f
                return r7
            L1f:
                int r4 = r4 + 2
                r5 = r4
                if (r8 != r13) goto L25
                goto L7a
            L25:
                r6 = r8
                goto L4b
            L27:
                if (r4 == 0) goto L4a
                java.lang.String r8 = ":"
                r9 = 1
                boolean r8 = r11.regionMatches(r12, r8, r3, r9)
                if (r8 == 0) goto L35
                int r12 = r12 + 1
                goto L4a
            L35:
                java.lang.String r8 = "."
                boolean r12 = r11.regionMatches(r12, r8, r3, r9)
                if (r12 == 0) goto L49
                int r12 = r4 + (-2)
                boolean r11 = c(r11, r6, r13, r1, r12)
                if (r11 != 0) goto L46
                return r7
            L46:
                int r4 = r4 + 2
                goto L7a
            L49:
                return r7
            L4a:
                r6 = r12
            L4b:
                r8 = r3
                r12 = r6
            L4d:
                if (r12 >= r13) goto L60
                char r9 = r11.charAt(r12)
                int r9 = okhttp3.HttpUrl.d(r9)
                if (r9 != r2) goto L5a
                goto L60
            L5a:
                int r8 = r8 << 4
                int r8 = r8 + r9
                int r12 = r12 + 1
                goto L4d
            L60:
                int r9 = r12 - r6
                if (r9 == 0) goto L79
                r10 = 4
                if (r9 <= r10) goto L68
                goto L79
            L68:
                int r7 = r4 + 1
                int r9 = r8 >>> 8
                r9 = r9 & 255(0xff, float:3.57E-43)
                byte r9 = (byte) r9
                r1[r4] = r9
                int r4 = r7 + 1
                r8 = r8 & 255(0xff, float:3.57E-43)
                byte r8 = (byte) r8
                r1[r7] = r8
                goto L9
            L79:
                return r7
            L7a:
                if (r4 == r0) goto L8b
                if (r5 != r2) goto L7f
                return r7
            L7f:
                int r11 = r4 - r5
                int r12 = 16 - r11
                java.lang.System.arraycopy(r1, r5, r1, r12, r11)
                int r0 = r0 - r4
                int r0 = r0 + r5
                java.util.Arrays.fill(r1, r5, r0, r3)
            L8b:
                java.net.InetAddress r11 = java.net.InetAddress.getByAddress(r1)     // Catch: java.net.UnknownHostException -> L90
                return r11
            L90:
                java.lang.AssertionError r11 = new java.lang.AssertionError
                r11.<init>()
                throw r11
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.HttpUrl.Builder.d(java.lang.String, int, int):java.net.InetAddress");
        }

        public static String h(byte[] bArr) {
            int i2 = 0;
            int i3 = -1;
            int i4 = 0;
            int i5 = 0;
            while (i4 < bArr.length) {
                int i6 = i4;
                while (i6 < 16 && bArr[i6] == 0 && bArr[i6 + 1] == 0) {
                    i6 += 2;
                }
                int i7 = i6 - i4;
                if (i7 > i5) {
                    i3 = i4;
                    i5 = i7;
                }
                i4 = i6 + 2;
            }
            g.c cVar = new g.c();
            while (i2 < bArr.length) {
                if (i2 == i3) {
                    cVar.E(58);
                    i2 += i5;
                    if (i2 == 16) {
                        cVar.E(58);
                    }
                } else {
                    if (i2 > 0) {
                        cVar.E(58);
                    }
                    cVar.l(((bArr[i2] & 255) << 8) | (bArr[i2 + 1] & 255));
                    i2 += 2;
                }
            }
            return cVar.b0();
        }

        public static int l(String str, int i2, int i3) {
            int i4;
            try {
                i4 = Integer.parseInt(HttpUrl.a(str, i2, i3, "", false, false, false, true));
            } catch (NumberFormatException unused) {
            }
            if (i4 <= 0 || i4 > 65535) {
                return -1;
            }
            return i4;
        }

        public static int p(String str, int i2, int i3) {
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

        public static int u(String str, int i2, int i3) {
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

        public static int v(String str, int i2, int i3) {
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

        public HttpUrl a() {
            if (this.f5546a == null) {
                throw new IllegalStateException("scheme == null");
            }
            if (this.f5549d != null) {
                return new HttpUrl(this);
            }
            throw new IllegalStateException("host == null");
        }

        public int e() {
            int i2 = this.f5550e;
            return i2 != -1 ? i2 : HttpUrl.e(this.f5546a);
        }

        public Builder f(String str) {
            this.f5552g = str != null ? HttpUrl.z(HttpUrl.b(str, " \"'<>#", true, false, true, true)) : null;
            return this;
        }

        public Builder g(String str) {
            Objects.requireNonNull(str, "host == null");
            String strB = b(str, 0, str.length());
            if (strB != null) {
                this.f5549d = strB;
                return this;
            }
            throw new IllegalArgumentException("unexpected host: " + str);
        }

        public final boolean i(String str) {
            return str.equals(".") || str.equalsIgnoreCase("%2e");
        }

        public final boolean j(String str) {
            return str.equals("..") || str.equalsIgnoreCase("%2e.") || str.equalsIgnoreCase(".%2e") || str.equalsIgnoreCase("%2e%2e");
        }

        public ParseResult k(HttpUrl httpUrl, String str) {
            int iH;
            int i2;
            int iV = c.v(str, 0, str.length());
            int iW = c.w(str, iV, str.length());
            if (u(str, iV, iW) != -1) {
                if (str.regionMatches(true, iV, "https:", 0, 6)) {
                    this.f5546a = "https";
                    iV += 6;
                } else {
                    if (!str.regionMatches(true, iV, "http:", 0, 5)) {
                        return ParseResult.UNSUPPORTED_SCHEME;
                    }
                    this.f5546a = "http";
                    iV += 5;
                }
            } else {
                if (httpUrl == null) {
                    return ParseResult.MISSING_SCHEME;
                }
                this.f5546a = httpUrl.f5538b;
            }
            int iV2 = v(str, iV, iW);
            char c2 = '?';
            char c3 = '#';
            if (iV2 >= 2 || httpUrl == null || !httpUrl.f5538b.equals(this.f5546a)) {
                boolean z = false;
                int i3 = iV + iV2;
                boolean z2 = false;
                while (true) {
                    iH = c.h(str, i3, iW, "@/\\?#");
                    byte bCharAt = iH != iW ? str.charAt(iH) : (byte) -1;
                    if (bCharAt == -1 || bCharAt == c3 || bCharAt == 47 || bCharAt == 92 || bCharAt == c2) {
                        break;
                    }
                    if (bCharAt == 64) {
                        if (z2) {
                            i2 = iH;
                            this.f5548c += "%40" + HttpUrl.a(str, i3, i2, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
                        } else {
                            int iG = c.g(str, i3, iH, ':');
                            i2 = iH;
                            String strA = HttpUrl.a(str, i3, iG, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
                            if (z) {
                                strA = this.f5547b + "%40" + strA;
                            }
                            this.f5547b = strA;
                            if (iG != i2) {
                                this.f5548c = HttpUrl.a(str, iG + 1, i2, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
                                z2 = true;
                            }
                            z = true;
                        }
                        i3 = i2 + 1;
                    }
                    c2 = '?';
                    c3 = '#';
                }
                int iP = p(str, i3, iH);
                int i4 = iP + 1;
                if (i4 < iH) {
                    this.f5549d = b(str, i3, iP);
                    int iL = l(str, i4, iH);
                    this.f5550e = iL;
                    if (iL == -1) {
                        return ParseResult.INVALID_PORT;
                    }
                } else {
                    this.f5549d = b(str, i3, iP);
                    this.f5550e = HttpUrl.e(this.f5546a);
                }
                if (this.f5549d == null) {
                    return ParseResult.INVALID_HOST;
                }
                iV = iH;
            } else {
                this.f5547b = httpUrl.k();
                this.f5548c = httpUrl.g();
                this.f5549d = httpUrl.f5541e;
                this.f5550e = httpUrl.f5542f;
                this.f5551f.clear();
                this.f5551f.addAll(httpUrl.i());
                if (iV == iW || str.charAt(iV) == '#') {
                    f(httpUrl.j());
                }
            }
            int iH2 = c.h(str, iV, iW, "?#");
            s(str, iV, iH2);
            if (iH2 < iW && str.charAt(iH2) == '?') {
                int iG2 = c.g(str, iH2, iW, '#');
                this.f5552g = HttpUrl.z(HttpUrl.a(str, iH2 + 1, iG2, " \"'<>#", true, false, true, true));
                iH2 = iG2;
            }
            if (iH2 < iW && str.charAt(iH2) == '#') {
                this.f5553h = HttpUrl.a(str, 1 + iH2, iW, "", true, false, false, false);
            }
            return ParseResult.SUCCESS;
        }

        public Builder m(String str) {
            Objects.requireNonNull(str, "password == null");
            this.f5548c = HttpUrl.b(str, " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
            return this;
        }

        public final void n() {
            if (!this.f5551f.remove(r0.size() - 1).isEmpty() || this.f5551f.isEmpty()) {
                this.f5551f.add("");
            } else {
                this.f5551f.set(r0.size() - 1, "");
            }
        }

        public Builder o(int i2) {
            if (i2 > 0 && i2 <= 65535) {
                this.f5550e = i2;
                return this;
            }
            throw new IllegalArgumentException("unexpected port: " + i2);
        }

        public final void q(String str, int i2, int i3, boolean z, boolean z2) {
            String strA = HttpUrl.a(str, i2, i3, " \"<>^`{}|/\\?#", z2, false, false, true);
            if (i(strA)) {
                return;
            }
            if (j(strA)) {
                n();
                return;
            }
            if (this.f5551f.get(r10.size() - 1).isEmpty()) {
                this.f5551f.set(r10.size() - 1, strA);
            } else {
                this.f5551f.add(strA);
            }
            if (z) {
                this.f5551f.add("");
            }
        }

        public Builder r() {
            int size = this.f5551f.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.f5551f.set(i2, HttpUrl.b(this.f5551f.get(i2), "[]", true, true, false, true));
            }
            List<String> list = this.f5552g;
            if (list != null) {
                int size2 = list.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    String str = this.f5552g.get(i3);
                    if (str != null) {
                        this.f5552g.set(i3, HttpUrl.b(str, "\\^`{|}", true, true, true, true));
                    }
                }
            }
            String str2 = this.f5553h;
            if (str2 != null) {
                this.f5553h = HttpUrl.b(str2, " \"#<>\\^`{|}", true, true, false, false);
            }
            return this;
        }

        public final void s(String str, int i2, int i3) {
            if (i2 == i3) {
                return;
            }
            char cCharAt = str.charAt(i2);
            if (cCharAt == '/' || cCharAt == '\\') {
                this.f5551f.clear();
                this.f5551f.add("");
                i2++;
            } else {
                List<String> list = this.f5551f;
                list.set(list.size() - 1, "");
            }
            while (true) {
                int i4 = i2;
                if (i4 >= i3) {
                    return;
                }
                i2 = c.h(str, i4, i3, "/\\");
                boolean z = i2 < i3;
                q(str, i4, i2, z, true);
                if (z) {
                    i2++;
                }
            }
        }

        public Builder t(String str) {
            Objects.requireNonNull(str, "scheme == null");
            if (str.equalsIgnoreCase("http")) {
                this.f5546a = "http";
            } else {
                if (!str.equalsIgnoreCase("https")) {
                    throw new IllegalArgumentException("unexpected scheme: " + str);
                }
                this.f5546a = "https";
            }
            return this;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.f5546a);
            sb.append("://");
            if (!this.f5547b.isEmpty() || !this.f5548c.isEmpty()) {
                sb.append(this.f5547b);
                if (!this.f5548c.isEmpty()) {
                    sb.append(':');
                    sb.append(this.f5548c);
                }
                sb.append('@');
            }
            if (this.f5549d.indexOf(58) != -1) {
                sb.append('[');
                sb.append(this.f5549d);
                sb.append(']');
            } else {
                sb.append(this.f5549d);
            }
            int iE = e();
            if (iE != HttpUrl.e(this.f5546a)) {
                sb.append(':');
                sb.append(iE);
            }
            HttpUrl.r(sb, this.f5551f);
            if (this.f5552g != null) {
                sb.append('?');
                HttpUrl.n(sb, this.f5552g);
            }
            if (this.f5553h != null) {
                sb.append('#');
                sb.append(this.f5553h);
            }
            return sb.toString();
        }

        public Builder w(String str) {
            Objects.requireNonNull(str, "username == null");
            this.f5547b = HttpUrl.b(str, " \"':;<=>@[]^`{}|/\\?#", false, false, false, true);
            return this;
        }
    }

    public HttpUrl(Builder builder) {
        this.f5538b = builder.f5546a;
        this.f5539c = t(builder.f5547b, false);
        this.f5540d = t(builder.f5548c, false);
        this.f5541e = builder.f5549d;
        this.f5542f = builder.e();
        this.f5543g = u(builder.f5551f, false);
        List<String> list = builder.f5552g;
        this.f5544h = list != null ? u(list, true) : null;
        String str = builder.f5553h;
        this.f5545i = str != null ? t(str, false) : null;
        this.j = builder.toString();
    }

    public static String a(String str, int i2, int i3, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        int iCharCount = i2;
        while (iCharCount < i3) {
            int iCodePointAt = str.codePointAt(iCharCount);
            if (iCodePointAt < 32 || iCodePointAt == 127 || (iCodePointAt >= 128 && z4)) {
                g.c cVar = new g.c();
                cVar.s0(str, i2, iCharCount);
                c(cVar, str, iCharCount, i3, str2, z, z2, z3, z4);
                return cVar.b0();
            }
            if (str2.indexOf(iCodePointAt) != -1 || ((iCodePointAt == 37 && (!z || (z2 && !w(str, iCharCount, i3)))) || (iCodePointAt == 43 && z3))) {
                g.c cVar2 = new g.c();
                cVar2.s0(str, i2, iCharCount);
                c(cVar2, str, iCharCount, i3, str2, z, z2, z3, z4);
                return cVar2.b0();
            }
            iCharCount += Character.charCount(iCodePointAt);
        }
        return str.substring(i2, i3);
    }

    public static String b(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        return a(str, 0, str.length(), str2, z, z2, z3, z4);
    }

    public static void c(g.c cVar, String str, int i2, int i3, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        g.c cVar2 = null;
        while (i2 < i3) {
            int iCodePointAt = str.codePointAt(i2);
            if (!z || (iCodePointAt != 9 && iCodePointAt != 10 && iCodePointAt != 12 && iCodePointAt != 13)) {
                if (iCodePointAt == 43 && z3) {
                    cVar.A(z ? "+" : "%2B");
                } else if (iCodePointAt < 32 || iCodePointAt == 127 || ((iCodePointAt >= 128 && z4) || str2.indexOf(iCodePointAt) != -1 || (iCodePointAt == 37 && (!z || (z2 && !w(str, i2, i3)))))) {
                    if (cVar2 == null) {
                        cVar2 = new g.c();
                    }
                    cVar2.t0(iCodePointAt);
                    while (!cVar2.D()) {
                        int iN = cVar2.N() & 255;
                        cVar.E(37);
                        char[] cArr = f5537a;
                        cVar.E(cArr[(iN >> 4) & 15]);
                        cVar.E(cArr[iN & 15]);
                    }
                } else {
                    cVar.t0(iCodePointAt);
                }
            }
            i2 += Character.charCount(iCodePointAt);
        }
    }

    public static int d(char c2) {
        if (c2 >= '0' && c2 <= '9') {
            return c2 - '0';
        }
        char c3 = 'a';
        if (c2 < 'a' || c2 > 'f') {
            c3 = 'A';
            if (c2 < 'A' || c2 > 'F') {
                return -1;
            }
        }
        return (c2 - c3) + 10;
    }

    public static int e(String str) {
        if (str.equals("http")) {
            return 80;
        }
        return str.equals("https") ? 443 : -1;
    }

    public static void n(StringBuilder sb, List<String> list) {
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

    public static HttpUrl q(String str) {
        Builder builder = new Builder();
        if (builder.k(null, str) == Builder.ParseResult.SUCCESS) {
            return builder.a();
        }
        return null;
    }

    public static void r(StringBuilder sb, List<String> list) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            sb.append('/');
            sb.append(list.get(i2));
        }
    }

    public static String s(String str, int i2, int i3, boolean z) {
        for (int i4 = i2; i4 < i3; i4++) {
            char cCharAt = str.charAt(i4);
            if (cCharAt == '%' || (cCharAt == '+' && z)) {
                g.c cVar = new g.c();
                cVar.s0(str, i2, i4);
                v(cVar, str, i4, i3, z);
                return cVar.b0();
            }
        }
        return str.substring(i2, i3);
    }

    public static String t(String str, boolean z) {
        return s(str, 0, str.length(), z);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0039  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void v(g.c r5, java.lang.String r6, int r7, int r8, boolean r9) {
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
            int r2 = d(r2)
            char r3 = r6.charAt(r1)
            int r3 = d(r3)
            r4 = -1
            if (r2 == r4) goto L39
            if (r3 == r4) goto L39
            int r7 = r2 << 4
            int r7 = r7 + r3
            r5.E(r7)
            r7 = r1
            goto L3c
        L2d:
            r1 = 43
            if (r0 != r1) goto L39
            if (r9 == 0) goto L39
            r1 = 32
            r5.E(r1)
            goto L3c
        L39:
            r5.t0(r0)
        L3c:
            int r0 = java.lang.Character.charCount(r0)
            int r7 = r7 + r0
            goto L0
        L42:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.HttpUrl.v(g.c, java.lang.String, int, int, boolean):void");
    }

    public static boolean w(String str, int i2, int i3) {
        int i4 = i2 + 2;
        return i4 < i3 && str.charAt(i2) == '%' && d(str.charAt(i2 + 1)) != -1 && d(str.charAt(i4)) != -1;
    }

    public static List<String> z(String str) {
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

    public String A() {
        return p("/...").w("").m("").a().toString();
    }

    public HttpUrl B(String str) {
        Builder builderP = p(str);
        if (builderP != null) {
            return builderP.a();
        }
        return null;
    }

    public String C() {
        return this.f5538b;
    }

    public URI D() {
        String string = o().r().toString();
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

    public boolean equals(Object obj) {
        return (obj instanceof HttpUrl) && ((HttpUrl) obj).j.equals(this.j);
    }

    public String f() {
        if (this.f5545i == null) {
            return null;
        }
        return this.j.substring(this.j.indexOf(35) + 1);
    }

    public String g() {
        if (this.f5540d.isEmpty()) {
            return "";
        }
        return this.j.substring(this.j.indexOf(58, this.f5538b.length() + 3) + 1, this.j.indexOf(64));
    }

    public String h() {
        int iIndexOf = this.j.indexOf(47, this.f5538b.length() + 3);
        String str = this.j;
        return this.j.substring(iIndexOf, c.h(str, iIndexOf, str.length(), "?#"));
    }

    public int hashCode() {
        return this.j.hashCode();
    }

    public List<String> i() {
        int iIndexOf = this.j.indexOf(47, this.f5538b.length() + 3);
        String str = this.j;
        int iH = c.h(str, iIndexOf, str.length(), "?#");
        ArrayList arrayList = new ArrayList();
        while (iIndexOf < iH) {
            int i2 = iIndexOf + 1;
            int iG = c.g(this.j, i2, iH, '/');
            arrayList.add(this.j.substring(i2, iG));
            iIndexOf = iG;
        }
        return arrayList;
    }

    public String j() {
        if (this.f5544h == null) {
            return null;
        }
        int iIndexOf = this.j.indexOf(63) + 1;
        String str = this.j;
        return this.j.substring(iIndexOf, c.g(str, iIndexOf + 1, str.length(), '#'));
    }

    public String k() {
        if (this.f5539c.isEmpty()) {
            return "";
        }
        int length = this.f5538b.length() + 3;
        String str = this.j;
        return this.j.substring(length, c.h(str, length, str.length(), ":@"));
    }

    public String l() {
        return this.f5541e;
    }

    public boolean m() {
        return this.f5538b.equals("https");
    }

    public Builder o() {
        Builder builder = new Builder();
        builder.f5546a = this.f5538b;
        builder.f5547b = k();
        builder.f5548c = g();
        builder.f5549d = this.f5541e;
        builder.f5550e = this.f5542f != e(this.f5538b) ? this.f5542f : -1;
        builder.f5551f.clear();
        builder.f5551f.addAll(i());
        builder.f(j());
        builder.f5553h = f();
        return builder;
    }

    public Builder p(String str) {
        Builder builder = new Builder();
        if (builder.k(this, str) == Builder.ParseResult.SUCCESS) {
            return builder;
        }
        return null;
    }

    public String toString() {
        return this.j;
    }

    public final List<String> u(List<String> list, boolean z) {
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i2 = 0; i2 < size; i2++) {
            String str = list.get(i2);
            arrayList.add(str != null ? t(str, z) : null);
        }
        return Collections.unmodifiableList(arrayList);
    }

    public int x() {
        return this.f5542f;
    }

    public String y() {
        if (this.f5544h == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        n(sb, this.f5544h);
        return sb.toString();
    }
}
