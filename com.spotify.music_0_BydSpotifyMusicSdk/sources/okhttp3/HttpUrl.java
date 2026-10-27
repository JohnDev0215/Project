package okhttp3;

import com.spotify.connectivity.httpwebgate.WebgateHelper;
import com.spotify.webapi.service.models.Search;
import java.io.EOFException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.text.Regex;
import kotlin.text.b;
import okhttp3.internal.Util;
import p.fo;
import p.g51;
import p.lu;
import p.v03;
import p.vr2;

/* JADX INFO: loaded from: classes.dex */
public final class HttpUrl {
    public static final Companion k = new Companion(0);
    public static final char[] l = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    public final String a;
    public final String b;
    public final String c;
    public final String d;
    public final int e;
    public final List f;
    public final List g;
    public final String h;
    public final String i;
    public final boolean j;

    public static final class Builder {
        public static final Companion i = new Companion(0);
        public String a;
        public String d;
        public final ArrayList f;
        public ArrayList g;
        public String h;
        public String b = "";
        public String c = "";
        public int e = -1;

        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(int i) {
                this();
            }
        }

        public Builder() {
            ArrayList arrayList = new ArrayList();
            this.f = arrayList;
            arrayList.add("");
        }

        public final HttpUrl a() {
            int iB;
            ArrayList arrayList;
            String str = this.a;
            if (str == null) {
                throw new IllegalStateException("scheme == null");
            }
            Companion companion = HttpUrl.k;
            String strE = Companion.e(companion, this.b, 0, 0, false, 7);
            String strE2 = Companion.e(companion, this.c, 0, 0, false, 7);
            String str2 = this.d;
            if (str2 == null) {
                throw new IllegalStateException("host == null");
            }
            int i2 = this.e;
            if (i2 != -1) {
                iB = i2;
            } else {
                String str3 = this.a;
                v03.h(str3);
                companion.getClass();
                iB = Companion.b(str3);
            }
            ArrayList arrayList2 = this.f;
            ArrayList arrayList3 = new ArrayList(lu.W(arrayList2));
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                arrayList3.add(Companion.e(HttpUrl.k, (String) it.next(), 0, 0, false, 7));
            }
            ArrayList<String> arrayList4 = this.g;
            if (arrayList4 != null) {
                arrayList = new ArrayList(lu.W(arrayList4));
                for (String str4 : arrayList4) {
                    arrayList.add(str4 != null ? Companion.e(HttpUrl.k, str4, 0, 0, true, 3) : null);
                }
            } else {
                arrayList = null;
            }
            String str5 = this.h;
            return new HttpUrl(str, strE, strE2, str2, iB, arrayList3, arrayList, str5 != null ? Companion.e(HttpUrl.k, str5, 0, 0, false, 7) : null, toString());
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:4:0x002d  */
        /* JADX WARN: Removed duplicated region for block: B:73:0x0149  */
        /* JADX WARN: Type inference failed for: r14v15, types: [boolean, int] */
        /* JADX WARN: Type inference failed for: r14v16 */
        /* JADX WARN: Type inference failed for: r14v17 */
        /* JADX WARN: Type inference failed for: r16v6 */
        /* JADX WARN: Type inference failed for: r16v7 */
        /* JADX WARN: Type inference failed for: r16v8 */
        /* JADX WARN: Unreachable blocks removed: 1, instructions: 3 */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void b(okhttp3.HttpUrl r32, java.lang.String r33) throws java.io.EOFException {
            /*
                Method dump skipped, instruction units count: 1155
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.HttpUrl.Builder.b(okhttp3.HttpUrl, java.lang.String):void");
        }

        /* JADX WARN: Removed duplicated region for block: B:32:0x008c  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.String toString() {
            /*
                Method dump skipped, instruction units count: 228
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.HttpUrl.Builder.toString():java.lang.String");
        }
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }

        public static String a(Companion companion, String str, int i, int i2, String str2, boolean z, boolean z2, boolean z3, boolean z4, Charset charset, int i3) throws EOFException {
            int i4 = (i3 & 1) != 0 ? 0 : i;
            int length = (i3 & 2) != 0 ? str.length() : i2;
            boolean z5 = (i3 & 8) != 0 ? false : z;
            boolean z6 = (i3 & 16) != 0 ? false : z2;
            boolean z7 = (i3 & 32) != 0 ? false : z3;
            boolean z8 = (i3 & 64) == 0 ? z4 : false;
            int i5 = 128;
            Charset charset2 = (i3 & 128) != 0 ? null : charset;
            companion.getClass();
            v03.j(str, "<this>");
            int iCharCount = i4;
            while (iCharCount < length) {
                int iCodePointAt = str.codePointAt(iCharCount);
                int i6 = 32;
                int i7 = 43;
                int i8 = 127;
                if (iCodePointAt < 32 || iCodePointAt == 127 || ((iCodePointAt >= i5 && !z8) || b.h0(str2, (char) iCodePointAt) || ((iCodePointAt == 37 && (!z5 || (z6 && !d(str, iCharCount, length)))) || (iCodePointAt == 43 && z7)))) {
                    fo foVar = new fo();
                    foVar.e1(str, i4, iCharCount);
                    fo foVar2 = null;
                    while (iCharCount < length) {
                        int iCodePointAt2 = str.codePointAt(iCharCount);
                        if (!z5 || (iCodePointAt2 != 9 && iCodePointAt2 != 10 && iCodePointAt2 != 12 && iCodePointAt2 != 13)) {
                            if (iCodePointAt2 == i7 && z7) {
                                foVar.d1(z5 ? "+" : "%2B");
                            } else {
                                if (iCodePointAt2 >= i6 && iCodePointAt2 != i8) {
                                    if ((iCodePointAt2 < 128 || z8) && !b.h0(str2, (char) iCodePointAt2) && (iCodePointAt2 != 37 || (z5 && (!z6 || d(str, iCharCount, length))))) {
                                        foVar.f1(iCodePointAt2);
                                    }
                                }
                                if (foVar2 == null) {
                                    foVar2 = new fo();
                                }
                                if (charset2 == null || v03.b(charset2, StandardCharsets.UTF_8)) {
                                    foVar2.f1(iCodePointAt2);
                                } else {
                                    foVar2.c1(str, iCharCount, Character.charCount(iCodePointAt2) + iCharCount, charset2);
                                }
                                while (!foVar2.Q()) {
                                    byte b = foVar2.readByte();
                                    foVar.W0(37);
                                    char[] cArr = HttpUrl.l;
                                    foVar.W0(cArr[((b & 255) >> 4) & 15]);
                                    foVar.W0(cArr[b & 15]);
                                }
                            }
                        }
                        iCharCount += Character.charCount(iCodePointAt2);
                        i8 = 127;
                        i6 = 32;
                        i7 = 43;
                    }
                    return foVar.Q0();
                }
                iCharCount += Character.charCount(iCodePointAt);
                i5 = 128;
            }
            String strSubstring = str.substring(i4, length);
            v03.i(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
            return strSubstring;
        }

        public static int b(String str) {
            v03.j(str, "scheme");
            if (v03.b(str, "http")) {
                return 80;
            }
            return v03.b(str, WebgateHelper.DEFAULT_WEBGATE_PROTOCOL) ? 443 : -1;
        }

        public static HttpUrl c(String str) throws EOFException {
            v03.j(str, "<this>");
            Builder builder = new Builder();
            builder.b(null, str);
            return builder.a();
        }

        public static boolean d(String str, int i, int i2) {
            int i3 = i + 2;
            return i3 < i2 && str.charAt(i) == '%' && Util.r(str.charAt(i + 1)) != -1 && Util.r(str.charAt(i3)) != -1;
        }

        public static String e(Companion companion, String str, int i, int i2, boolean z, int i3) {
            int i4;
            if ((i3 & 1) != 0) {
                i = 0;
            }
            if ((i3 & 2) != 0) {
                i2 = str.length();
            }
            if ((i3 & 4) != 0) {
                z = false;
            }
            companion.getClass();
            v03.j(str, "<this>");
            int iCharCount = i;
            while (iCharCount < i2) {
                char cCharAt = str.charAt(iCharCount);
                if (cCharAt == '%' || (cCharAt == '+' && z)) {
                    fo foVar = new fo();
                    foVar.e1(str, i, iCharCount);
                    while (iCharCount < i2) {
                        int iCodePointAt = str.codePointAt(iCharCount);
                        if (iCodePointAt == 37 && (i4 = iCharCount + 2) < i2) {
                            int iR = Util.r(str.charAt(iCharCount + 1));
                            int iR2 = Util.r(str.charAt(i4));
                            if (iR == -1 || iR2 == -1) {
                                foVar.f1(iCodePointAt);
                                iCharCount += Character.charCount(iCodePointAt);
                            } else {
                                foVar.W0((iR << 4) + iR2);
                                iCharCount = Character.charCount(iCodePointAt) + i4;
                            }
                        } else if (iCodePointAt == 43 && z) {
                            foVar.W0(32);
                            iCharCount++;
                        } else {
                            foVar.f1(iCodePointAt);
                            iCharCount += Character.charCount(iCodePointAt);
                        }
                    }
                    return foVar.Q0();
                }
                iCharCount++;
            }
            String strSubstring = str.substring(i, i2);
            v03.i(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
            return strSubstring;
        }

        public static ArrayList f(String str) {
            ArrayList arrayList = new ArrayList();
            int i = 0;
            while (i <= str.length()) {
                int iN0 = b.n0(str, '&', i, false, 4);
                if (iN0 == -1) {
                    iN0 = str.length();
                }
                int iN02 = b.n0(str, '=', i, false, 4);
                if (iN02 == -1 || iN02 > iN0) {
                    String strSubstring = str.substring(i, iN0);
                    v03.i(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
                    arrayList.add(strSubstring);
                    arrayList.add(null);
                } else {
                    String strSubstring2 = str.substring(i, iN02);
                    v03.i(strSubstring2, "this as java.lang.String…ing(startIndex, endIndex)");
                    arrayList.add(strSubstring2);
                    String strSubstring3 = str.substring(iN02 + 1, iN0);
                    v03.i(strSubstring3, "this as java.lang.String…ing(startIndex, endIndex)");
                    arrayList.add(strSubstring3);
                }
                i = iN0 + 1;
            }
            return arrayList;
        }

        public static void g(List list, StringBuilder sb) {
            v03.j(list, "<this>");
            g51 g51VarT = vr2.t(vr2.x(0, list.size()), 2);
            int i = g51VarT.c;
            int i2 = g51VarT.e;
            int i3 = g51VarT.k;
            if ((i3 <= 0 || i > i2) && (i3 >= 0 || i2 > i)) {
                return;
            }
            while (true) {
                String str = (String) list.get(i);
                String str2 = (String) list.get(i + 1);
                if (i > 0) {
                    sb.append('&');
                }
                sb.append(str);
                if (str2 != null) {
                    sb.append('=');
                    sb.append(str2);
                }
                if (i == i2) {
                    return;
                } else {
                    i += i3;
                }
            }
        }
    }

    public HttpUrl(String str, String str2, String str3, String str4, int i, ArrayList arrayList, ArrayList arrayList2, String str5, String str6) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = i;
        this.f = arrayList;
        this.g = arrayList2;
        this.h = str5;
        this.i = str6;
        this.j = v03.b(str, WebgateHelper.DEFAULT_WEBGATE_PROTOCOL);
    }

    public final String a() {
        if (this.c.length() == 0) {
            return "";
        }
        int length = this.a.length() + 3;
        String str = this.i;
        String strSubstring = str.substring(b.n0(str, ':', length, false, 4) + 1, b.n0(str, '@', 0, false, 6));
        v03.i(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public final String b() {
        int length = this.a.length() + 3;
        String str = this.i;
        int iN0 = b.n0(str, '/', length, false, 4);
        String strSubstring = str.substring(iN0, Util.e(iN0, str, str.length(), "?#"));
        v03.i(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public final ArrayList c() {
        int length = this.a.length() + 3;
        String str = this.i;
        int iN0 = b.n0(str, '/', length, false, 4);
        int iE = Util.e(iN0, str, str.length(), "?#");
        ArrayList arrayList = new ArrayList();
        while (iN0 < iE) {
            int i = iN0 + 1;
            int iF = Util.f(str, '/', i, iE);
            String strSubstring = str.substring(i, iF);
            v03.i(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
            arrayList.add(strSubstring);
            iN0 = iF;
        }
        return arrayList;
    }

    public final String d() {
        if (this.g == null) {
            return null;
        }
        String str = this.i;
        int iN0 = b.n0(str, '?', 0, false, 6) + 1;
        String strSubstring = str.substring(iN0, Util.f(str, '#', iN0, str.length()));
        v03.i(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public final String e() {
        if (this.b.length() == 0) {
            return "";
        }
        int length = this.a.length() + 3;
        String str = this.i;
        String strSubstring = str.substring(length, Util.e(length, str, str.length(), ":@"));
        v03.i(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof HttpUrl) && v03.b(((HttpUrl) obj).i, this.i);
    }

    public final Builder f(String str) {
        v03.j(str, Search.Type.LINK);
        try {
            Builder builder = new Builder();
            builder.b(this, str);
            return builder;
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public final String g() {
        Builder builderF = f("/...");
        v03.h(builderF);
        Companion companion = k;
        builderF.b = Companion.a(companion, "", 0, 0, " \"':;<=>@[]^`{}|/\\?#", false, false, false, false, null, 251);
        builderF.c = Companion.a(companion, "", 0, 0, " \"':;<=>@[]^`{}|/\\?#", false, false, false, false, null, 251);
        return builderF.a().i;
    }

    public final URI h() {
        String strSubstring;
        Builder builder = new Builder();
        String str = this.a;
        builder.a = str;
        builder.b = e();
        builder.c = a();
        builder.d = this.d;
        Companion companion = k;
        companion.getClass();
        int iB = Companion.b(str);
        int i = this.e;
        if (i == iB) {
            i = -1;
        }
        builder.e = i;
        ArrayList arrayList = builder.f;
        arrayList.clear();
        arrayList.addAll(c());
        String strD = d();
        builder.g = strD != null ? Companion.f(Companion.a(companion, strD, 0, 0, " \"'<>#", true, false, true, false, null, 211)) : null;
        if (this.h == null) {
            strSubstring = null;
        } else {
            String str2 = this.i;
            strSubstring = str2.substring(b.n0(str2, '#', 0, false, 6) + 1);
            v03.i(strSubstring, "this as java.lang.String).substring(startIndex)");
        }
        builder.h = strSubstring;
        String str3 = builder.d;
        builder.d = str3 != null ? new Regex("[\"<>^`{|}]").b(str3, "") : null;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            arrayList.set(i2, Companion.a(k, (String) arrayList.get(i2), 0, 0, "[]", true, true, false, false, null, 227));
        }
        ArrayList arrayList2 = builder.g;
        if (arrayList2 != null) {
            int size2 = arrayList2.size();
            for (int i3 = 0; i3 < size2; i3++) {
                String str4 = (String) arrayList2.get(i3);
                arrayList2.set(i3, str4 != null ? Companion.a(k, str4, 0, 0, "\\^`{|}", true, true, true, false, null, 195) : null);
            }
        }
        String str5 = builder.h;
        builder.h = str5 != null ? Companion.a(k, str5, 0, 0, " \"#<>\\^`{|}", true, true, false, true, null, 163) : null;
        String string = builder.toString();
        try {
            return new URI(string);
        } catch (URISyntaxException e) {
            try {
                URI uriCreate = URI.create(new Regex("[\\u0000-\\u001F\\u007F-\\u009F\\p{javaWhitespace}]").b(string, ""));
                v03.i(uriCreate, "{\n      // Unlikely edge…Unexpected!\n      }\n    }");
                return uriCreate;
            } catch (Exception unused) {
                throw new RuntimeException(e);
            }
        }
    }

    public final int hashCode() {
        return this.i.hashCode();
    }

    public final String toString() {
        return this.i;
    }
}
