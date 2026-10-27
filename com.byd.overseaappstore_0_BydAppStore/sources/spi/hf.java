package spi;

import androidx.core.app.NotificationCompat;
import com.google.android.material.badge.BadgeDrawable;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class hf {
    public final boolean a;

    @NotNull
    public final String b;

    @NotNull
    public final String c;

    @NotNull
    public final String d;

    @NotNull
    public final String e;
    public final int f;

    @NotNull
    public final List<String> g;
    public final List<String> h;

    @Nullable
    public final String i;
    public final String j;
    public static final b l = new b(null);
    public static final char[] k = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static final class a {
        public static final C0127a i = new C0127a(null);

        @Nullable
        public String a;

        @Nullable
        public String d;

        @NotNull
        public final List<String> f;

        @Nullable
        public List<String> g;

        @Nullable
        public String h;

        @NotNull
        public String b = "";

        @NotNull
        public String c = "";
        public int e = -1;

        /* JADX INFO: renamed from: spi.hf$a$a, reason: collision with other inner class name */
        public static final class C0127a {
            private C0127a() {
            }

            public /* synthetic */ C0127a(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        public a() {
            ArrayList arrayList = new ArrayList();
            this.f = arrayList;
            arrayList.add("");
        }

        /* JADX WARN: Type inference failed for: r1v4, types: [java.lang.Iterable, java.util.ArrayList, java.util.List<java.lang.String>] */
        @NotNull
        public final hf a() {
            int iB;
            ArrayList arrayList;
            String str = this.a;
            if (str == null) {
                throw new IllegalStateException("scheme == null");
            }
            b bVar = hf.l;
            String strD = b.d(bVar, this.b, 0, 0, false, 7);
            String strD2 = b.d(bVar, this.c, 0, 0, false, 7);
            String str2 = this.d;
            if (str2 == null) {
                throw new IllegalStateException("host == null");
            }
            int i2 = this.e;
            if (i2 != -1) {
                iB = i2;
            } else {
                String str3 = this.a;
                Intrinsics.checkNotNull(str3);
                iB = bVar.b(str3);
            }
            ?? r1 = this.f;
            ArrayList arrayList2 = new ArrayList(CollectionsKt.b(r1));
            Iterator it = r1.iterator();
            while (it.hasNext()) {
                arrayList2.add(b.d(hf.l, (String) it.next(), 0, 0, false, 7));
            }
            List<String> list = this.g;
            if (list != null) {
                arrayList = new ArrayList(CollectionsKt.b(list));
                for (String str4 : list) {
                    arrayList.add(str4 != null ? b.d(hf.l, str4, 0, 0, true, 3) : null);
                }
            } else {
                arrayList = null;
            }
            String str5 = this.h;
            return new hf(str, strD, strD2, str2, iB, arrayList2, arrayList, str5 != null ? b.d(hf.l, str5, 0, 0, false, 7) : null, toString());
        }

        /* JADX WARN: Removed duplicated region for block: B:6:0x001c  */
        @org.jetbrains.annotations.NotNull
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final spi.hf.a b(@org.jetbrains.annotations.Nullable java.lang.String r13) {
            /*
                r12 = this;
                if (r13 == 0) goto L1c
                spi.hf$b r11 = spi.hf.l
                r2 = 0
                r3 = 0
                r5 = 1
                r6 = 0
                r7 = 1
                r8 = 0
                r9 = 0
                r10 = 211(0xd3, float:2.96E-43)
                java.lang.String r4 = " \"'<>#"
                r0 = r11
                r1 = r13
                java.lang.String r13 = spi.hf.b.a(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10)
                if (r13 == 0) goto L1c
                java.util.List r13 = r11.e(r13)
                goto L1d
            L1c:
                r13 = 0
            L1d:
                java.util.ArrayList r13 = (java.util.ArrayList) r13
                r12.g = r13
                return r12
            */
            throw new UnsupportedOperationException("Method not decompiled: spi.hf.a.b(java.lang.String):spi.hf$a");
        }

        /* JADX WARN: Removed duplicated region for block: B:42:0x0087  */
        /* JADX WARN: Type inference incomplete: some casts might be missing */
        /* JADX WARN: Unreachable blocks removed: 1, instructions: 3 */
        @org.jetbrains.annotations.NotNull
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final spi.hf.a c(@org.jetbrains.annotations.Nullable spi.hf r28, @org.jetbrains.annotations.NotNull java.lang.String r29) {
            /*
                Method dump skipped, instruction units count: 1167
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: spi.hf.a.c(spi.hf, java.lang.String):spi.hf$a");
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x0032  */
        /* JADX WARN: Removed duplicated region for block: B:41:0x0098  */
        /* JADX WARN: Type inference failed for: r3v7, types: [java.lang.Object, java.util.ArrayList, java.util.List, java.util.List<java.lang.String>] */
        @org.jetbrains.annotations.NotNull
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.String toString() {
            /*
                Method dump skipped, instruction units count: 241
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: spi.hf.a.toString():java.lang.String");
        }
    }

    public static final class b {
        private b() {
        }

        public /* synthetic */ b(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static String a(b bVar, String canonicalize, int i, int i2, String encodeSet, boolean z, boolean z2, boolean z3, boolean z4, Charset charset, int i3) {
            int i4 = (i3 & 1) != 0 ? 0 : i;
            int length = (i3 & 2) != 0 ? canonicalize.length() : i2;
            boolean z5 = (i3 & 8) != 0 ? false : z;
            boolean z6 = (i3 & 16) != 0 ? false : z2;
            boolean z7 = (i3 & 32) != 0 ? false : z3;
            boolean z8 = (i3 & 64) == 0 ? z4 : false;
            int i5 = 128;
            Charset charset2 = (i3 & 128) != 0 ? null : charset;
            Objects.requireNonNull(bVar);
            Intrinsics.checkNotNullParameter(canonicalize, "$this$canonicalize");
            Intrinsics.checkNotNullParameter(encodeSet, "encodeSet");
            int iCharCount = i4;
            while (iCharCount < length) {
                int iCodePointAt = canonicalize.codePointAt(iCharCount);
                int i6 = 32;
                if (iCodePointAt < 32 || iCodePointAt == 127 || ((iCodePointAt >= i5 && !z8) || StringsKt.b(encodeSet, (char) iCodePointAt) || ((iCodePointAt == 37 && (!z5 || (z6 && !bVar.c(canonicalize, iCharCount, length)))) || (iCodePointAt == 43 && z7)))) {
                    g2 g2Var = new g2();
                    g2Var.Z(canonicalize, i4, iCharCount);
                    g2 g2Var2 = null;
                    while (iCharCount < length) {
                        int iCodePointAt2 = canonicalize.codePointAt(iCharCount);
                        if (!z5 || (iCodePointAt2 != 9 && iCodePointAt2 != 10 && iCodePointAt2 != 12 && iCodePointAt2 != 13)) {
                            if (iCodePointAt2 == 43 && z7) {
                                g2Var.Y(z5 ? BadgeDrawable.DEFAULT_EXCEED_MAX_BADGE_NUMBER_SUFFIX : "%2B");
                            } else if (iCodePointAt2 < i6 || iCodePointAt2 == 127 || ((iCodePointAt2 >= 128 && !z8) || StringsKt.b(encodeSet, (char) iCodePointAt2) || (iCodePointAt2 == 37 && (!z5 || (z6 && !bVar.c(canonicalize, iCharCount, length)))))) {
                                if (g2Var2 == null) {
                                    g2Var2 = new g2();
                                }
                                if (charset2 == null || Intrinsics.areEqual(charset2, StandardCharsets.UTF_8)) {
                                    g2Var2.a0(iCodePointAt2);
                                } else {
                                    g2Var2.X(canonicalize, iCharCount, Character.charCount(iCodePointAt2) + iCharCount, charset2);
                                }
                                while (!g2Var2.h()) {
                                    int i7 = g2Var2.readByte() & 255;
                                    g2Var.S(37);
                                    char[] cArr = hf.k;
                                    g2Var.S(cArr[(i7 >> 4) & 15]);
                                    g2Var.S(cArr[i7 & 15]);
                                }
                            } else {
                                g2Var.a0(iCodePointAt2);
                            }
                        }
                        iCharCount += Character.charCount(iCodePointAt2);
                        i6 = 32;
                    }
                    return g2Var.L();
                }
                iCharCount += Character.charCount(iCodePointAt);
                i5 = 128;
            }
            String strSubstring = canonicalize.substring(i4, length);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            return strSubstring;
        }

        public static String d(b bVar, String percentDecode, int i, int i2, boolean z, int i3) {
            int i4;
            if ((i3 & 1) != 0) {
                i = 0;
            }
            if ((i3 & 2) != 0) {
                i2 = percentDecode.length();
            }
            if ((i3 & 4) != 0) {
                z = false;
            }
            Objects.requireNonNull(bVar);
            Intrinsics.checkNotNullParameter(percentDecode, "$this$percentDecode");
            int iCharCount = i;
            while (iCharCount < i2) {
                char cCharAt = percentDecode.charAt(iCharCount);
                if (cCharAt == '%' || (cCharAt == '+' && z)) {
                    g2 g2Var = new g2();
                    g2Var.Z(percentDecode, i, iCharCount);
                    while (iCharCount < i2) {
                        int iCodePointAt = percentDecode.codePointAt(iCharCount);
                        if (iCodePointAt == 37 && (i4 = iCharCount + 2) < i2) {
                            int iQ = uu.q(percentDecode.charAt(iCharCount + 1));
                            int iQ2 = uu.q(percentDecode.charAt(i4));
                            if (iQ == -1 || iQ2 == -1) {
                                g2Var.a0(iCodePointAt);
                                iCharCount += Character.charCount(iCodePointAt);
                            } else {
                                g2Var.S((iQ << 4) + iQ2);
                                iCharCount = Character.charCount(iCodePointAt) + i4;
                            }
                        } else if (iCodePointAt == 43 && z) {
                            g2Var.S(32);
                            iCharCount++;
                        } else {
                            g2Var.a0(iCodePointAt);
                            iCharCount += Character.charCount(iCodePointAt);
                        }
                    }
                    return g2Var.L();
                }
                iCharCount++;
            }
            String strSubstring = percentDecode.substring(i, i2);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            return strSubstring;
        }

        @JvmStatic
        public final int b(@NotNull String scheme) {
            Intrinsics.checkNotNullParameter(scheme, "scheme");
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

        public final boolean c(String str, int i, int i2) {
            int i3 = i + 2;
            return i3 < i2 && str.charAt(i) == '%' && uu.q(str.charAt(i + 1)) != -1 && uu.q(str.charAt(i3)) != -1;
        }

        @NotNull
        public final List<String> e(@NotNull String toQueryNamesAndValues) {
            String strSubstring;
            Intrinsics.checkNotNullParameter(toQueryNamesAndValues, "$this$toQueryNamesAndValues");
            ArrayList arrayList = new ArrayList();
            int i = 0;
            while (i <= toQueryNamesAndValues.length()) {
                int iJ = StringsKt.j(toQueryNamesAndValues, Typography.amp, i, 4);
                if (iJ == -1) {
                    iJ = toQueryNamesAndValues.length();
                }
                int iJ2 = StringsKt.j(toQueryNamesAndValues, '=', i, 4);
                if (iJ2 == -1 || iJ2 > iJ) {
                    String strSubstring2 = toQueryNamesAndValues.substring(i, iJ);
                    Intrinsics.checkNotNullExpressionValue(strSubstring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    arrayList.add(strSubstring2);
                    strSubstring = null;
                } else {
                    String strSubstring3 = toQueryNamesAndValues.substring(i, iJ2);
                    Intrinsics.checkNotNullExpressionValue(strSubstring3, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    arrayList.add(strSubstring3);
                    strSubstring = toQueryNamesAndValues.substring(iJ2 + 1, iJ);
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                }
                arrayList.add(strSubstring);
                i = iJ + 1;
            }
            return arrayList;
        }

        public final void f(@NotNull List<String> toQueryString, @NotNull StringBuilder out) {
            Intrinsics.checkNotNullParameter(toQueryString, "$this$toQueryString");
            Intrinsics.checkNotNullParameter(out, "out");
            IntProgression intProgressionStep = RangesKt.step(RangesKt.until(0, toQueryString.size()), 2);
            int first = intProgressionStep.getFirst();
            int last = intProgressionStep.getLast();
            int step = intProgressionStep.getStep();
            if (step >= 0) {
                if (first > last) {
                    return;
                }
            } else if (first < last) {
                return;
            }
            while (true) {
                String str = toQueryString.get(first);
                String str2 = toQueryString.get(first + 1);
                if (first > 0) {
                    out.append(Typography.amp);
                }
                out.append(str);
                if (str2 != null) {
                    out.append('=');
                    out.append(str2);
                }
                if (first == last) {
                    return;
                } else {
                    first += step;
                }
            }
        }
    }

    public hf(@NotNull String scheme, @NotNull String username, @NotNull String password, @NotNull String host, int i, @NotNull List<String> pathSegments, @Nullable List<String> list, @Nullable String str, @NotNull String url) {
        Intrinsics.checkNotNullParameter(scheme, "scheme");
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(password, "password");
        Intrinsics.checkNotNullParameter(host, "host");
        Intrinsics.checkNotNullParameter(pathSegments, "pathSegments");
        Intrinsics.checkNotNullParameter(url, "url");
        this.b = scheme;
        this.c = username;
        this.d = password;
        this.e = host;
        this.f = i;
        this.g = pathSegments;
        this.h = list;
        this.i = str;
        this.j = url;
        this.a = Intrinsics.areEqual(scheme, "https");
    }

    @JvmStatic
    @JvmName(name = "get")
    @NotNull
    public static final hf f(@NotNull String toHttpUrl) {
        Objects.requireNonNull(l);
        Intrinsics.checkNotNullParameter(toHttpUrl, "$this$toHttpUrl");
        a aVar = new a();
        aVar.c(null, toHttpUrl);
        return aVar.a();
    }

    @JvmName(name = "encodedPassword")
    @NotNull
    public final String a() {
        if (this.d.length() == 0) {
            return "";
        }
        int iJ = StringsKt.j(this.j, ':', this.b.length() + 3, 4) + 1;
        int iJ2 = StringsKt.j(this.j, '@', 0, 6);
        String str = this.j;
        Objects.requireNonNull(str, "null cannot be cast to non-null type java.lang.String");
        String strSubstring = str.substring(iJ, iJ2);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    @JvmName(name = "encodedPath")
    @NotNull
    public final String b() {
        int iJ = StringsKt.j(this.j, '/', this.b.length() + 3, 4);
        String str = this.j;
        int iF = uu.f(str, "?#", iJ, str.length());
        String str2 = this.j;
        Objects.requireNonNull(str2, "null cannot be cast to non-null type java.lang.String");
        String strSubstring = str2.substring(iJ, iF);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    @JvmName(name = "encodedPathSegments")
    @NotNull
    public final List<String> c() {
        int iJ = StringsKt.j(this.j, '/', this.b.length() + 3, 4);
        String str = this.j;
        int iF = uu.f(str, "?#", iJ, str.length());
        ArrayList arrayList = new ArrayList();
        while (iJ < iF) {
            int i = iJ + 1;
            int iE = uu.e(this.j, '/', i, iF);
            String str2 = this.j;
            Objects.requireNonNull(str2, "null cannot be cast to non-null type java.lang.String");
            String strSubstring = str2.substring(i, iE);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            arrayList.add(strSubstring);
            iJ = iE;
        }
        return arrayList;
    }

    @JvmName(name = "encodedQuery")
    @Nullable
    public final String d() {
        if (this.h == null) {
            return null;
        }
        int iJ = StringsKt.j(this.j, '?', 0, 6) + 1;
        String str = this.j;
        int iE = uu.e(str, '#', iJ, str.length());
        String str2 = this.j;
        Objects.requireNonNull(str2, "null cannot be cast to non-null type java.lang.String");
        String strSubstring = str2.substring(iJ, iE);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    @JvmName(name = "encodedUsername")
    @NotNull
    public final String e() {
        if (this.c.length() == 0) {
            return "";
        }
        int length = this.b.length() + 3;
        String str = this.j;
        int iF = uu.f(str, ":@", length, str.length());
        String str2 = this.j;
        Objects.requireNonNull(str2, "null cannot be cast to non-null type java.lang.String");
        String strSubstring = str2.substring(length, iF);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public final boolean equals(@Nullable Object obj) {
        return (obj instanceof hf) && Intrinsics.areEqual(((hf) obj).j, this.j);
    }

    @Nullable
    public final a g(@NotNull String link) {
        Intrinsics.checkNotNullParameter(link, "link");
        try {
            a aVar = new a();
            aVar.c(this, link);
            return aVar;
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    @NotNull
    public final String h() {
        a aVarG = g("/...");
        Intrinsics.checkNotNull(aVarG);
        Objects.requireNonNull(aVarG);
        Intrinsics.checkNotNullParameter("", "username");
        b bVar = l;
        aVarG.b = b.a(bVar, "", 0, 0, " \"':;<=>@[]^`{}|/\\?#", false, false, false, false, null, 251);
        Intrinsics.checkNotNullParameter("", "password");
        aVarG.c = b.a(bVar, "", 0, 0, " \"':;<=>@[]^`{}|/\\?#", false, false, false, false, null, 251);
        return aVarG.a().j;
    }

    public final int hashCode() {
        return this.j.hashCode();
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @JvmName(name = NotificationCompat.MessagingStyle.Message.KEY_DATA_URI)
    @NotNull
    public final URI i() {
        String strSubstring;
        a aVar = new a();
        aVar.a = this.b;
        String strE = e();
        Intrinsics.checkNotNullParameter(strE, "<set-?>");
        aVar.b = strE;
        String strA = a();
        Intrinsics.checkNotNullParameter(strA, "<set-?>");
        aVar.c = strA;
        aVar.d = this.e;
        aVar.e = this.f != l.b(this.b) ? this.f : -1;
        aVar.f.clear();
        aVar.f.addAll(c());
        aVar.b(d());
        if (this.i == null) {
            strSubstring = null;
        } else {
            int iJ = StringsKt.j(this.j, '#', 0, 6) + 1;
            String str = this.j;
            Objects.requireNonNull(str, "null cannot be cast to non-null type java.lang.String");
            strSubstring = str.substring(iJ);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.String).substring(startIndex)");
        }
        aVar.h = strSubstring;
        String str2 = aVar.d;
        aVar.d = str2 != null ? new Regex("[\"<>^`{|}]").replace(str2, "") : null;
        int size = aVar.f.size();
        for (int i = 0; i < size; i++) {
            List<String> list = aVar.f;
            list.set(i, b.a(l, list.get(i), 0, 0, "[]", true, true, false, false, null, 227));
        }
        List<String> list2 = aVar.g;
        if (list2 != null) {
            int size2 = list2.size();
            for (int i2 = 0; i2 < size2; i2++) {
                String str3 = list2.get(i2);
                list2.set(i2, str3 != null ? b.a(l, str3, 0, 0, "\\^`{|}", true, true, true, false, null, 195) : null);
            }
        }
        String str4 = aVar.h;
        aVar.h = str4 != null ? b.a(l, str4, 0, 0, " \"#<>\\^`{|}", true, true, false, true, null, 163) : null;
        String string = aVar.toString();
        try {
            return new URI(string);
        } catch (URISyntaxException e) {
            try {
                URI uriCreate = URI.create(new Regex("[\\u0000-\\u001F\\u007F-\\u009F\\p{javaWhitespace}]").replace(string, ""));
                Intrinsics.checkNotNullExpressionValue(uriCreate, "try {\n        val stripp…e) // Unexpected!\n      }");
                return uriCreate;
            } catch (Exception unused) {
                throw new RuntimeException(e);
            }
        }
    }

    @NotNull
    public final String toString() {
        return this.j;
    }
}
