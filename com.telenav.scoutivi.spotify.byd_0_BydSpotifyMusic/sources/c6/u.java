package c6;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: compiled from: HttpUrl.kt */
/* JADX INFO: loaded from: classes.dex */
public final class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f3848a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f3849b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f3850c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String f3851d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final String f3852e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final int f3853f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final List<String> f3854g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final List<String> f3855h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final String f3856i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final String f3857j;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final b f3847l = new b(null);

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private static final char[] f3846k = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* JADX INFO: compiled from: HttpUrl.kt */
    public static final class a {

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public static final C0044a f3858i = new C0044a(null);

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f3859a;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private String f3862d;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private final List<String> f3864f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private List<String> f3865g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private String f3866h;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f3860b = "";

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private String f3861c = "";

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f3863e = -1;

        /* JADX INFO: renamed from: c6.u$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: HttpUrl.kt */
        public static final class C0044a {
            private C0044a() {
            }

            public /* synthetic */ C0044a(kotlin.jvm.internal.g gVar) {
                this();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final int e(String str, int i7, int i8) {
                try {
                    int i9 = Integer.parseInt(b.b(u.f3847l, str, i7, i8, "", false, false, false, false, null, 248, null));
                    if (1 <= i9 && 65535 >= i9) {
                        return i9;
                    }
                    return -1;
                } catch (NumberFormatException unused) {
                    return -1;
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final int f(String str, int i7, int i8) {
                while (i7 < i8) {
                    char cCharAt = str.charAt(i7);
                    if (cCharAt == ':') {
                        return i7;
                    }
                    if (cCharAt == '[') {
                        do {
                            i7++;
                            if (i7 < i8) {
                            }
                        } while (str.charAt(i7) != ']');
                    }
                    i7++;
                }
                return i8;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final int g(String str, int i7, int i8) {
                if (i8 - i7 < 2) {
                    return -1;
                }
                char cCharAt = str.charAt(i7);
                if ((kotlin.jvm.internal.l.g(cCharAt, 97) < 0 || kotlin.jvm.internal.l.g(cCharAt, 122) > 0) && (kotlin.jvm.internal.l.g(cCharAt, 65) < 0 || kotlin.jvm.internal.l.g(cCharAt, 90) > 0)) {
                    return -1;
                }
                while (true) {
                    i7++;
                    if (i7 >= i8) {
                        return -1;
                    }
                    char cCharAt2 = str.charAt(i7);
                    if ('a' > cCharAt2 || 'z' < cCharAt2) {
                        if ('A' > cCharAt2 || 'Z' < cCharAt2) {
                            if ('0' > cCharAt2 || '9' < cCharAt2) {
                                if (cCharAt2 != '+' && cCharAt2 != '-' && cCharAt2 != '.') {
                                    if (cCharAt2 == ':') {
                                        return i7;
                                    }
                                    return -1;
                                }
                            }
                        }
                    }
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final int h(String str, int i7, int i8) {
                int i9 = 0;
                while (i7 < i8) {
                    char cCharAt = str.charAt(i7);
                    if (cCharAt != '\\' && cCharAt != '/') {
                        break;
                    }
                    i9++;
                    i7++;
                }
                return i9;
            }
        }

        public a() {
            ArrayList arrayList = new ArrayList();
            this.f3864f = arrayList;
            arrayList.add("");
        }

        private final int b() {
            int i7 = this.f3863e;
            if (i7 != -1) {
                return i7;
            }
            b bVar = u.f3847l;
            String str = this.f3859a;
            kotlin.jvm.internal.l.c(str);
            return bVar.c(str);
        }

        private final boolean f(String str) {
            return kotlin.jvm.internal.l.a(str, ".") || y5.p.o(str, "%2e", true);
        }

        private final boolean g(String str) {
            return kotlin.jvm.internal.l.a(str, "..") || y5.p.o(str, "%2e.", true) || y5.p.o(str, ".%2e", true) || y5.p.o(str, "%2e%2e", true);
        }

        private final void j() {
            List<String> list = this.f3864f;
            if (!(list.remove(list.size() - 1).length() == 0) || !(!this.f3864f.isEmpty())) {
                this.f3864f.add("");
            } else {
                List<String> list2 = this.f3864f;
                list2.set(list2.size() - 1, "");
            }
        }

        private final void l(String str, int i7, int i8, boolean z6, boolean z7) {
            String strB = b.b(u.f3847l, str, i7, i8, " \"<>^`{}|/\\?#", z7, false, false, false, null, 240, null);
            if (f(strB)) {
                return;
            }
            if (g(strB)) {
                j();
                return;
            }
            List<String> list = this.f3864f;
            if (list.get(list.size() - 1).length() == 0) {
                List<String> list2 = this.f3864f;
                list2.set(list2.size() - 1, strB);
            } else {
                this.f3864f.add(strB);
            }
            if (z6) {
                this.f3864f.add("");
            }
        }

        private final void n(String str, int i7, int i8) {
            if (i7 == i8) {
                return;
            }
            char cCharAt = str.charAt(i7);
            if (cCharAt == '/' || cCharAt == '\\') {
                this.f3864f.clear();
                this.f3864f.add("");
                i7++;
            } else {
                List<String> list = this.f3864f;
                list.set(list.size() - 1, "");
            }
            while (true) {
                int i9 = i7;
                if (i9 >= i8) {
                    return;
                }
                i7 = d6.b.m(str, "/\\", i9, i8);
                boolean z6 = i7 < i8;
                l(str, i9, i7, z6, true);
                if (z6) {
                    i7++;
                }
            }
        }

        public final u a() {
            ArrayList arrayList;
            String str = this.f3859a;
            if (str == null) {
                throw new IllegalStateException("scheme == null");
            }
            b bVar = u.f3847l;
            String strG = b.g(bVar, this.f3860b, 0, 0, false, 7, null);
            String strG2 = b.g(bVar, this.f3861c, 0, 0, false, 7, null);
            String str2 = this.f3862d;
            if (str2 == null) {
                throw new IllegalStateException("host == null");
            }
            int iB = b();
            List<String> list = this.f3864f;
            ArrayList arrayList2 = new ArrayList(h5.q.p(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList2.add(b.g(u.f3847l, (String) it.next(), 0, 0, false, 7, null));
            }
            List<String> list2 = this.f3865g;
            if (list2 != null) {
                arrayList = new ArrayList(h5.q.p(list2, 10));
                for (String str3 : list2) {
                    arrayList.add(str3 != null ? b.g(u.f3847l, str3, 0, 0, true, 3, null) : null);
                }
            } else {
                arrayList = null;
            }
            String str4 = this.f3866h;
            return new u(str, strG, strG2, str2, iB, arrayList2, arrayList, str4 != null ? b.g(u.f3847l, str4, 0, 0, false, 7, null) : null, toString());
        }

        /* JADX WARN: Removed duplicated region for block: B:6:0x001d  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final c6.u.a c(java.lang.String r14) {
            /*
                r13 = this;
                if (r14 == 0) goto L1d
                c6.u$b r12 = c6.u.f3847l
                r2 = 0
                r3 = 0
                r5 = 1
                r6 = 0
                r7 = 1
                r8 = 0
                r9 = 0
                r10 = 211(0xd3, float:2.96E-43)
                r11 = 0
                java.lang.String r4 = " \"'<>#"
                r0 = r12
                r1 = r14
                java.lang.String r14 = c6.u.b.b(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
                if (r14 == 0) goto L1d
                java.util.List r14 = r12.i(r14)
                goto L1e
            L1d:
                r14 = 0
            L1e:
                r13.f3865g = r14
                return r13
            */
            throw new UnsupportedOperationException("Method not decompiled: c6.u.a.c(java.lang.String):c6.u$a");
        }

        public final List<String> d() {
            return this.f3864f;
        }

        public final a e(String host) {
            kotlin.jvm.internal.l.e(host, "host");
            String strE = d6.a.e(b.g(u.f3847l, host, 0, 0, false, 7, null));
            if (strE != null) {
                this.f3862d = strE;
                return this;
            }
            throw new IllegalArgumentException("unexpected host: " + host);
        }

        public final a h(u uVar, String input) {
            int iM;
            int i7;
            int i8;
            String str;
            boolean z6;
            int i9;
            String str2;
            int i10;
            boolean z7;
            boolean z8;
            kotlin.jvm.internal.l.e(input, "input");
            int iW = d6.b.w(input, 0, 0, 3, null);
            int iY = d6.b.y(input, iW, 0, 2, null);
            C0044a c0044a = f3858i;
            int iG = c0044a.g(input, iW, iY);
            String str3 = "(this as java.lang.Strin…ing(startIndex, endIndex)";
            byte b7 = -1;
            boolean z9 = true;
            if (iG != -1) {
                if (y5.p.x(input, "https:", iW, true)) {
                    this.f3859a = "https";
                    iW += 6;
                } else {
                    if (!y5.p.x(input, "http:", iW, true)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Expected URL scheme 'http' or 'https' but was '");
                        String strSubstring = input.substring(0, iG);
                        kotlin.jvm.internal.l.d(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                        sb.append(strSubstring);
                        sb.append("'");
                        throw new IllegalArgumentException(sb.toString());
                    }
                    this.f3859a = "http";
                    iW += 5;
                }
            } else {
                if (uVar == null) {
                    throw new IllegalArgumentException("Expected URL scheme 'http' or 'https' but no colon was found");
                }
                this.f3859a = uVar.p();
            }
            int iH = c0044a.h(input, iW, iY);
            byte b8 = 63;
            byte b9 = 35;
            if (iH >= 2 || uVar == null || (!kotlin.jvm.internal.l.a(uVar.p(), this.f3859a))) {
                int i11 = iW + iH;
                boolean z10 = false;
                boolean z11 = false;
                while (true) {
                    iM = d6.b.m(input, "@/\\?#", i11, iY);
                    byte bCharAt = iM != iY ? input.charAt(iM) : b7;
                    if (bCharAt == b7 || bCharAt == b9 || bCharAt == 47 || bCharAt == 92 || bCharAt == b8) {
                        break;
                    }
                    if (bCharAt != 64) {
                        z6 = z9;
                        str2 = str3;
                        i9 = iY;
                    } else {
                        if (z10) {
                            z6 = z9;
                            i9 = iY;
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append(this.f3861c);
                            sb2.append("%40");
                            str2 = str3;
                            i10 = iM;
                            sb2.append(b.b(u.f3847l, input, i11, iM, " \"':;<=>@[]^`{}|/\\?#", true, false, false, false, null, 240, null));
                            this.f3861c = sb2.toString();
                            z7 = z11;
                        } else {
                            int iL = d6.b.l(input, ':', i11, iM);
                            b bVar = u.f3847l;
                            z6 = z9;
                            i9 = iY;
                            String str4 = str3;
                            String strB = b.b(bVar, input, i11, iL, " \"':;<=>@[]^`{}|/\\?#", true, false, false, false, null, 240, null);
                            if (z11) {
                                strB = this.f3860b + "%40" + strB;
                            }
                            this.f3860b = strB;
                            if (iL != iM) {
                                this.f3861c = b.b(bVar, input, iL + 1, iM, " \"':;<=>@[]^`{}|/\\?#", true, false, false, false, null, 240, null);
                                z8 = z6;
                            } else {
                                z8 = z10;
                            }
                            z10 = z8;
                            str2 = str4;
                            z7 = z6;
                            i10 = iM;
                        }
                        i11 = i10 + 1;
                        z11 = z7;
                    }
                    str3 = str2;
                    z9 = z6;
                    iY = i9;
                    b9 = 35;
                    b8 = 63;
                    b7 = -1;
                }
                boolean z12 = z9;
                String str5 = str3;
                i7 = iY;
                C0044a c0044a2 = f3858i;
                int iF = c0044a2.f(input, i11, iM);
                int i12 = iF + 1;
                if (i12 < iM) {
                    i8 = i11;
                    this.f3862d = d6.a.e(b.g(u.f3847l, input, i11, iF, false, 4, null));
                    int iE = c0044a2.e(input, i12, iM);
                    this.f3863e = iE;
                    if (!(iE != -1 ? z12 : false)) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("Invalid URL port: \"");
                        String strSubstring2 = input.substring(i12, iM);
                        kotlin.jvm.internal.l.d(strSubstring2, str5);
                        sb3.append(strSubstring2);
                        sb3.append('\"');
                        throw new IllegalArgumentException(sb3.toString().toString());
                    }
                    str = str5;
                } else {
                    i8 = i11;
                    str = str5;
                    b bVar2 = u.f3847l;
                    this.f3862d = d6.a.e(b.g(bVar2, input, i8, iF, false, 4, null));
                    String str6 = this.f3859a;
                    kotlin.jvm.internal.l.c(str6);
                    this.f3863e = bVar2.c(str6);
                }
                if (!(this.f3862d != null ? z12 : false)) {
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("Invalid URL host: \"");
                    String strSubstring3 = input.substring(i8, iF);
                    kotlin.jvm.internal.l.d(strSubstring3, str);
                    sb4.append(strSubstring3);
                    sb4.append('\"');
                    throw new IllegalArgumentException(sb4.toString().toString());
                }
                iW = iM;
            } else {
                this.f3860b = uVar.g();
                this.f3861c = uVar.c();
                this.f3862d = uVar.h();
                this.f3863e = uVar.l();
                this.f3864f.clear();
                this.f3864f.addAll(uVar.e());
                if (iW == iY || input.charAt(iW) == '#') {
                    c(uVar.f());
                }
                i7 = iY;
            }
            int i13 = i7;
            int iM2 = d6.b.m(input, "?#", iW, i13);
            n(input, iW, iM2);
            if (iM2 < i13 && input.charAt(iM2) == '?') {
                int iL2 = d6.b.l(input, '#', iM2, i13);
                b bVar3 = u.f3847l;
                this.f3865g = bVar3.i(b.b(bVar3, input, iM2 + 1, iL2, " \"'<>#", true, false, true, false, null, 208, null));
                iM2 = iL2;
            }
            if (iM2 < i13 && input.charAt(iM2) == '#') {
                this.f3866h = b.b(u.f3847l, input, iM2 + 1, i13, "", true, false, false, true, null, 176, null);
            }
            return this;
        }

        public final a i(String password) {
            kotlin.jvm.internal.l.e(password, "password");
            this.f3861c = b.b(u.f3847l, password, 0, 0, " \"':;<=>@[]^`{}|/\\?#", false, false, false, false, null, 251, null);
            return this;
        }

        public final a k(int i7) {
            if (1 <= i7 && 65535 >= i7) {
                this.f3863e = i7;
                return this;
            }
            throw new IllegalArgumentException(("unexpected port: " + i7).toString());
        }

        public final a m() {
            String str = this.f3862d;
            this.f3862d = str != null ? new y5.f("[\"<>^`{|}]").b(str, "") : null;
            int size = this.f3864f.size();
            for (int i7 = 0; i7 < size; i7++) {
                List<String> list = this.f3864f;
                list.set(i7, b.b(u.f3847l, list.get(i7), 0, 0, "[]", true, true, false, false, null, 227, null));
            }
            List<String> list2 = this.f3865g;
            if (list2 != null) {
                int size2 = list2.size();
                for (int i8 = 0; i8 < size2; i8++) {
                    String str2 = list2.get(i8);
                    list2.set(i8, str2 != null ? b.b(u.f3847l, str2, 0, 0, "\\^`{|}", true, true, true, false, null, 195, null) : null);
                }
            }
            String str3 = this.f3866h;
            this.f3866h = str3 != null ? b.b(u.f3847l, str3, 0, 0, " \"#<>\\^`{|}", true, true, false, true, null, 163, null) : null;
            return this;
        }

        public final a o(String scheme) {
            kotlin.jvm.internal.l.e(scheme, "scheme");
            if (y5.p.o(scheme, "http", true)) {
                this.f3859a = "http";
            } else {
                if (!y5.p.o(scheme, "https", true)) {
                    throw new IllegalArgumentException("unexpected scheme: " + scheme);
                }
                this.f3859a = "https";
            }
            return this;
        }

        public final void p(String str) {
            this.f3866h = str;
        }

        public final void q(String str) {
            kotlin.jvm.internal.l.e(str, "<set-?>");
            this.f3861c = str;
        }

        public final void r(String str) {
            kotlin.jvm.internal.l.e(str, "<set-?>");
            this.f3860b = str;
        }

        public final void s(String str) {
            this.f3862d = str;
        }

        public final void t(int i7) {
            this.f3863e = i7;
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x0035  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x0093  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.String toString() {
            /*
                Method dump skipped, instruction units count: 201
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: c6.u.a.toString():java.lang.String");
        }

        public final void u(String str) {
            this.f3859a = str;
        }

        public final a v(String username) {
            kotlin.jvm.internal.l.e(username, "username");
            this.f3860b = b.b(u.f3847l, username, 0, 0, " \"':;<=>@[]^`{}|/\\?#", false, false, false, false, null, 251, null);
            return this;
        }
    }

    /* JADX INFO: compiled from: HttpUrl.kt */
    public static final class b {
        private b() {
        }

        public /* synthetic */ b(kotlin.jvm.internal.g gVar) {
            this();
        }

        public static /* synthetic */ String b(b bVar, String str, int i7, int i8, String str2, boolean z6, boolean z7, boolean z8, boolean z9, Charset charset, int i9, Object obj) {
            return bVar.a(str, (i9 & 1) != 0 ? 0 : i7, (i9 & 2) != 0 ? str.length() : i8, str2, (i9 & 8) != 0 ? false : z6, (i9 & 16) != 0 ? false : z7, (i9 & 32) != 0 ? false : z8, (i9 & 64) != 0 ? false : z9, (i9 & 128) != 0 ? null : charset);
        }

        private final boolean e(String str, int i7, int i8) {
            int i9 = i7 + 2;
            return i9 < i8 && str.charAt(i7) == '%' && d6.b.D(str.charAt(i7 + 1)) != -1 && d6.b.D(str.charAt(i9)) != -1;
        }

        public static /* synthetic */ String g(b bVar, String str, int i7, int i8, boolean z6, int i9, Object obj) {
            if ((i9 & 1) != 0) {
                i7 = 0;
            }
            if ((i9 & 2) != 0) {
                i8 = str.length();
            }
            if ((i9 & 4) != 0) {
                z6 = false;
            }
            return bVar.f(str, i7, i8, z6);
        }

        /* JADX WARN: Removed duplicated region for block: B:38:0x0068  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x006d  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x0074  */
        /* JADX WARN: Removed duplicated region for block: B:49:0x008d  */
        /* JADX WARN: Removed duplicated region for block: B:52:0x0096 A[LOOP:1: B:50:0x0090->B:52:0x0096, LOOP_END] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private final void k(p6.e r15, java.lang.String r16, int r17, int r18, java.lang.String r19, boolean r20, boolean r21, boolean r22, boolean r23, java.nio.charset.Charset r24) {
            /*
                r14 = this;
                r0 = r15
                r1 = r16
                r2 = r18
                r3 = r24
                r4 = 0
                r5 = r17
                r6 = r4
            Lb:
                if (r5 >= r2) goto Lbf
                java.lang.String r7 = "null cannot be cast to non-null type java.lang.String"
                java.util.Objects.requireNonNull(r1, r7)
                int r7 = r1.codePointAt(r5)
                if (r20 == 0) goto L2e
                r8 = 9
                if (r7 == r8) goto L29
                r8 = 10
                if (r7 == r8) goto L29
                r8 = 12
                if (r7 == r8) goto L29
                r8 = 13
                if (r7 == r8) goto L29
                goto L2e
            L29:
                r8 = r14
                r12 = r19
                goto Lb8
            L2e:
                r8 = 43
                if (r7 != r8) goto L3f
                if (r22 == 0) goto L3f
                if (r20 == 0) goto L39
                java.lang.String r8 = "+"
                goto L3b
            L39:
                java.lang.String r8 = "%2B"
            L3b:
                r15.H(r8)
                goto L29
            L3f:
                r8 = 32
                r9 = 37
                if (r7 < r8) goto L6f
                r8 = 127(0x7f, float:1.78E-43)
                if (r7 == r8) goto L6f
                r8 = 128(0x80, float:1.8E-43)
                if (r7 < r8) goto L4f
                if (r23 == 0) goto L6f
            L4f:
                char r8 = (char) r7
                r10 = 0
                r11 = 2
                r12 = r19
                boolean r8 = y5.g.E(r12, r8, r10, r11, r4)
                if (r8 != 0) goto L6d
                if (r7 != r9) goto L68
                if (r20 == 0) goto L6d
                if (r21 == 0) goto L68
                r8 = r14
                boolean r10 = r14.e(r1, r5, r2)
                if (r10 != 0) goto L69
                goto L72
            L68:
                r8 = r14
            L69:
                r15.y0(r7)
                goto Lb8
            L6d:
                r8 = r14
                goto L72
            L6f:
                r8 = r14
                r12 = r19
            L72:
                if (r6 != 0) goto L79
                p6.e r6 = new p6.e
                r6.<init>()
            L79:
                if (r3 == 0) goto L8d
                java.nio.charset.Charset r10 = java.nio.charset.StandardCharsets.UTF_8
                boolean r10 = kotlin.jvm.internal.l.a(r3, r10)
                if (r10 == 0) goto L84
                goto L8d
            L84:
                int r10 = java.lang.Character.charCount(r7)
                int r10 = r10 + r5
                r6.v0(r1, r5, r10, r3)
                goto L90
            L8d:
                r6.y0(r7)
            L90:
                boolean r10 = r6.J()
                if (r10 != 0) goto Lb8
                byte r10 = r6.Y()
                r10 = r10 & 255(0xff, float:3.57E-43)
                r15.M(r9)
                char[] r11 = c6.u.a()
                int r13 = r10 >> 4
                r13 = r13 & 15
                char r11 = r11[r13]
                r15.M(r11)
                char[] r11 = c6.u.a()
                r10 = r10 & 15
                char r10 = r11[r10]
                r15.M(r10)
                goto L90
            Lb8:
                int r7 = java.lang.Character.charCount(r7)
                int r5 = r5 + r7
                goto Lb
            Lbf:
                r8 = r14
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: c6.u.b.k(p6.e, java.lang.String, int, int, java.lang.String, boolean, boolean, boolean, boolean, java.nio.charset.Charset):void");
        }

        private final void l(p6.e eVar, String str, int i7, int i8, boolean z6) {
            int i9;
            while (i7 < i8) {
                Objects.requireNonNull(str, "null cannot be cast to non-null type java.lang.String");
                int iCodePointAt = str.codePointAt(i7);
                if (iCodePointAt == 37 && (i9 = i7 + 2) < i8) {
                    int iD = d6.b.D(str.charAt(i7 + 1));
                    int iD2 = d6.b.D(str.charAt(i9));
                    if (iD == -1 || iD2 == -1) {
                        eVar.y0(iCodePointAt);
                        i7 += Character.charCount(iCodePointAt);
                    } else {
                        eVar.M((iD << 4) + iD2);
                        i7 = Character.charCount(iCodePointAt) + i9;
                    }
                } else if (iCodePointAt == 43 && z6) {
                    eVar.M(32);
                    i7++;
                } else {
                    eVar.y0(iCodePointAt);
                    i7 += Character.charCount(iCodePointAt);
                }
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:20:0x003e  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.String a(java.lang.String r14, int r15, int r16, java.lang.String r17, boolean r18, boolean r19, boolean r20, boolean r21, java.nio.charset.Charset r22) {
            /*
                r13 = this;
                r2 = r14
                r4 = r16
                r5 = r17
                java.lang.String r0 = "$this$canonicalize"
                kotlin.jvm.internal.l.e(r14, r0)
                java.lang.String r0 = "encodeSet"
                kotlin.jvm.internal.l.e(r5, r0)
                r3 = r15
            L10:
                if (r3 >= r4) goto L6f
                int r0 = r14.codePointAt(r3)
                r1 = 32
                if (r0 < r1) goto L4c
                r1 = 127(0x7f, float:1.78E-43)
                if (r0 == r1) goto L4c
                r1 = 128(0x80, float:1.8E-43)
                if (r0 < r1) goto L24
                if (r21 == 0) goto L4c
            L24:
                char r1 = (char) r0
                r6 = 0
                r7 = 2
                r8 = 0
                boolean r1 = y5.g.E(r5, r1, r6, r7, r8)
                if (r1 != 0) goto L4c
                r1 = 37
                if (r0 != r1) goto L3e
                if (r18 == 0) goto L4c
                if (r19 == 0) goto L3e
                r11 = r13
                boolean r1 = r13.e(r14, r3, r4)
                if (r1 == 0) goto L4d
                goto L3f
            L3e:
                r11 = r13
            L3f:
                r1 = 43
                if (r0 != r1) goto L46
                if (r20 == 0) goto L46
                goto L4d
            L46:
                int r0 = java.lang.Character.charCount(r0)
                int r3 = r3 + r0
                goto L10
            L4c:
                r11 = r13
            L4d:
                p6.e r12 = new p6.e
                r12.<init>()
                r0 = r15
                r12.x0(r14, r15, r3)
                r0 = r13
                r1 = r12
                r2 = r14
                r4 = r16
                r5 = r17
                r6 = r18
                r7 = r19
                r8 = r20
                r9 = r21
                r10 = r22
                r0.k(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10)
                java.lang.String r0 = r12.g0()
                return r0
            L6f:
                r11 = r13
                r0 = r15
                java.lang.String r0 = r14.substring(r15, r16)
                java.lang.String r1 = "(this as java.lang.Strin…ing(startIndex, endIndex)"
                kotlin.jvm.internal.l.d(r0, r1)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: c6.u.b.a(java.lang.String, int, int, java.lang.String, boolean, boolean, boolean, boolean, java.nio.charset.Charset):java.lang.String");
        }

        public final int c(String scheme) {
            kotlin.jvm.internal.l.e(scheme, "scheme");
            int iHashCode = scheme.hashCode();
            if (iHashCode != 3213448) {
                if (iHashCode == 99617003 && scheme.equals("https")) {
                    return 443;
                }
            } else if (scheme.equals("http")) {
                return 80;
            }
            return -1;
        }

        public final u d(String toHttpUrl) {
            kotlin.jvm.internal.l.e(toHttpUrl, "$this$toHttpUrl");
            return new a().h(null, toHttpUrl).a();
        }

        public final String f(String percentDecode, int i7, int i8, boolean z6) {
            kotlin.jvm.internal.l.e(percentDecode, "$this$percentDecode");
            for (int i9 = i7; i9 < i8; i9++) {
                char cCharAt = percentDecode.charAt(i9);
                if (cCharAt == '%' || (cCharAt == '+' && z6)) {
                    p6.e eVar = new p6.e();
                    eVar.x0(percentDecode, i7, i9);
                    l(eVar, percentDecode, i9, i8, z6);
                    return eVar.g0();
                }
            }
            String strSubstring = percentDecode.substring(i7, i8);
            kotlin.jvm.internal.l.d(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            return strSubstring;
        }

        public final void h(List<String> toPathString, StringBuilder out) {
            kotlin.jvm.internal.l.e(toPathString, "$this$toPathString");
            kotlin.jvm.internal.l.e(out, "out");
            int size = toPathString.size();
            for (int i7 = 0; i7 < size; i7++) {
                out.append('/');
                out.append(toPathString.get(i7));
            }
        }

        public final List<String> i(String toQueryNamesAndValues) {
            kotlin.jvm.internal.l.e(toQueryNamesAndValues, "$this$toQueryNamesAndValues");
            ArrayList arrayList = new ArrayList();
            int i7 = 0;
            while (i7 <= toQueryNamesAndValues.length()) {
                int iP = y5.q.P(toQueryNamesAndValues, '&', i7, false, 4, null);
                if (iP == -1) {
                    iP = toQueryNamesAndValues.length();
                }
                int i8 = iP;
                int iP2 = y5.q.P(toQueryNamesAndValues, '=', i7, false, 4, null);
                if (iP2 == -1 || iP2 > i8) {
                    String strSubstring = toQueryNamesAndValues.substring(i7, i8);
                    kotlin.jvm.internal.l.d(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    arrayList.add(strSubstring);
                    arrayList.add(null);
                } else {
                    String strSubstring2 = toQueryNamesAndValues.substring(i7, iP2);
                    kotlin.jvm.internal.l.d(strSubstring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    arrayList.add(strSubstring2);
                    String strSubstring3 = toQueryNamesAndValues.substring(iP2 + 1, i8);
                    kotlin.jvm.internal.l.d(strSubstring3, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    arrayList.add(strSubstring3);
                }
                i7 = i8 + 1;
            }
            return arrayList;
        }

        public final void j(List<String> toQueryString, StringBuilder out) {
            kotlin.jvm.internal.l.e(toQueryString, "$this$toQueryString");
            kotlin.jvm.internal.l.e(out, "out");
            v5.a aVarH = v5.h.h(v5.h.i(0, toQueryString.size()), 2);
            int iC = aVarH.c();
            int iD = aVarH.d();
            int iE = aVarH.e();
            if (iE >= 0) {
                if (iC > iD) {
                    return;
                }
            } else if (iC < iD) {
                return;
            }
            while (true) {
                String str = toQueryString.get(iC);
                String str2 = toQueryString.get(iC + 1);
                if (iC > 0) {
                    out.append('&');
                }
                out.append(str);
                if (str2 != null) {
                    out.append('=');
                    out.append(str2);
                }
                if (iC == iD) {
                    return;
                } else {
                    iC += iE;
                }
            }
        }
    }

    public u(String scheme, String username, String password, String host, int i7, List<String> pathSegments, List<String> list, String str, String url) {
        kotlin.jvm.internal.l.e(scheme, "scheme");
        kotlin.jvm.internal.l.e(username, "username");
        kotlin.jvm.internal.l.e(password, "password");
        kotlin.jvm.internal.l.e(host, "host");
        kotlin.jvm.internal.l.e(pathSegments, "pathSegments");
        kotlin.jvm.internal.l.e(url, "url");
        this.f3849b = scheme;
        this.f3850c = username;
        this.f3851d = password;
        this.f3852e = host;
        this.f3853f = i7;
        this.f3854g = pathSegments;
        this.f3855h = list;
        this.f3856i = str;
        this.f3857j = url;
        this.f3848a = kotlin.jvm.internal.l.a(scheme, "https");
    }

    public final String b() {
        if (this.f3856i == null) {
            return null;
        }
        int iP = y5.q.P(this.f3857j, '#', 0, false, 6, null) + 1;
        String str = this.f3857j;
        Objects.requireNonNull(str, "null cannot be cast to non-null type java.lang.String");
        String strSubstring = str.substring(iP);
        kotlin.jvm.internal.l.d(strSubstring, "(this as java.lang.String).substring(startIndex)");
        return strSubstring;
    }

    public final String c() {
        if (this.f3851d.length() == 0) {
            return "";
        }
        int iP = y5.q.P(this.f3857j, ':', this.f3849b.length() + 3, false, 4, null) + 1;
        int iP2 = y5.q.P(this.f3857j, '@', 0, false, 6, null);
        String str = this.f3857j;
        Objects.requireNonNull(str, "null cannot be cast to non-null type java.lang.String");
        String strSubstring = str.substring(iP, iP2);
        kotlin.jvm.internal.l.d(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public final String d() {
        int iP = y5.q.P(this.f3857j, '/', this.f3849b.length() + 3, false, 4, null);
        String str = this.f3857j;
        int iM = d6.b.m(str, "?#", iP, str.length());
        String str2 = this.f3857j;
        Objects.requireNonNull(str2, "null cannot be cast to non-null type java.lang.String");
        String strSubstring = str2.substring(iP, iM);
        kotlin.jvm.internal.l.d(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public final List<String> e() {
        int iP = y5.q.P(this.f3857j, '/', this.f3849b.length() + 3, false, 4, null);
        String str = this.f3857j;
        int iM = d6.b.m(str, "?#", iP, str.length());
        ArrayList arrayList = new ArrayList();
        while (iP < iM) {
            int i7 = iP + 1;
            int iL = d6.b.l(this.f3857j, '/', i7, iM);
            String str2 = this.f3857j;
            Objects.requireNonNull(str2, "null cannot be cast to non-null type java.lang.String");
            String strSubstring = str2.substring(i7, iL);
            kotlin.jvm.internal.l.d(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            arrayList.add(strSubstring);
            iP = iL;
        }
        return arrayList;
    }

    public boolean equals(Object obj) {
        return (obj instanceof u) && kotlin.jvm.internal.l.a(((u) obj).f3857j, this.f3857j);
    }

    public final String f() {
        if (this.f3855h == null) {
            return null;
        }
        int iP = y5.q.P(this.f3857j, '?', 0, false, 6, null) + 1;
        String str = this.f3857j;
        int iL = d6.b.l(str, '#', iP, str.length());
        String str2 = this.f3857j;
        Objects.requireNonNull(str2, "null cannot be cast to non-null type java.lang.String");
        String strSubstring = str2.substring(iP, iL);
        kotlin.jvm.internal.l.d(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public final String g() {
        if (this.f3850c.length() == 0) {
            return "";
        }
        int length = this.f3849b.length() + 3;
        String str = this.f3857j;
        int iM = d6.b.m(str, ":@", length, str.length());
        String str2 = this.f3857j;
        Objects.requireNonNull(str2, "null cannot be cast to non-null type java.lang.String");
        String strSubstring = str2.substring(length, iM);
        kotlin.jvm.internal.l.d(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public final String h() {
        return this.f3852e;
    }

    public int hashCode() {
        return this.f3857j.hashCode();
    }

    public final boolean i() {
        return this.f3848a;
    }

    public final a j() {
        a aVar = new a();
        aVar.u(this.f3849b);
        aVar.r(g());
        aVar.q(c());
        aVar.s(this.f3852e);
        aVar.t(this.f3853f != f3847l.c(this.f3849b) ? this.f3853f : -1);
        aVar.d().clear();
        aVar.d().addAll(e());
        aVar.c(f());
        aVar.p(b());
        return aVar;
    }

    public final a k(String link) {
        kotlin.jvm.internal.l.e(link, "link");
        try {
            return new a().h(this, link);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public final int l() {
        return this.f3853f;
    }

    public final String m() {
        if (this.f3855h == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        f3847l.j(this.f3855h, sb);
        return sb.toString();
    }

    public final String n() {
        a aVarK = k("/...");
        kotlin.jvm.internal.l.c(aVarK);
        return aVarK.v("").i("").a().toString();
    }

    public final u o(String link) {
        kotlin.jvm.internal.l.e(link, "link");
        a aVarK = k(link);
        if (aVarK != null) {
            return aVarK.a();
        }
        return null;
    }

    public final String p() {
        return this.f3849b;
    }

    public final URI q() {
        String string = j().m().toString();
        try {
            return new URI(string);
        } catch (URISyntaxException e7) {
            try {
                URI uriCreate = URI.create(new y5.f("[\\u0000-\\u001F\\u007F-\\u009F\\p{javaWhitespace}]").b(string, ""));
                kotlin.jvm.internal.l.d(uriCreate, "try {\n        val stripp…e) // Unexpected!\n      }");
                return uriCreate;
            } catch (Exception unused) {
                throw new RuntimeException(e7);
            }
        }
    }

    public final URL r() {
        try {
            return new URL(this.f3857j);
        } catch (MalformedURLException e7) {
            throw new RuntimeException(e7);
        }
    }

    public String toString() {
        return this.f3857j;
    }
}
