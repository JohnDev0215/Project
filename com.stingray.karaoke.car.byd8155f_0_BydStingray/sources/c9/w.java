package c9;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public final class w {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final char[] f12618j = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f12619a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f12620b;
    public final String c;
    public final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f12621e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ArrayList f12622f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f12623g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String f12624h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f12625i;

    public w(String str, String str2, String str3, String str4, int i7, ArrayList arrayList, ArrayList arrayList2, String str5, String str6) {
        A7.m.f(str, "scheme");
        A7.m.f(str4, "host");
        this.f12619a = str;
        this.f12620b = str2;
        this.c = str3;
        this.d = str4;
        this.f12621e = i7;
        this.f12622f = arrayList2;
        this.f12623g = str5;
        this.f12624h = str6;
        this.f12625i = str.equals("https");
    }

    public final String a() {
        if (this.c.length() == 0) {
            return "";
        }
        int length = this.f12619a.length() + 3;
        String str = this.f12624h;
        String strSubstring = str.substring(Q8.h.J0(str, ':', length, false, 4) + 1, Q8.h.J0(str, '@', 0, false, 6));
        A7.m.e(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public final String b() {
        int length = this.f12619a.length() + 3;
        String str = this.f12624h;
        int iJ0 = Q8.h.J0(str, '/', length, false, 4);
        String strSubstring = str.substring(iJ0, d9.b.f(str, "?#", iJ0, str.length()));
        A7.m.e(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public final ArrayList c() {
        int length = this.f12619a.length() + 3;
        String str = this.f12624h;
        int iJ0 = Q8.h.J0(str, '/', length, false, 4);
        int iF = d9.b.f(str, "?#", iJ0, str.length());
        ArrayList arrayList = new ArrayList();
        while (iJ0 < iF) {
            int i7 = iJ0 + 1;
            int iE = d9.b.e(str, '/', i7, iF);
            String strSubstring = str.substring(i7, iE);
            A7.m.e(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
            arrayList.add(strSubstring);
            iJ0 = iE;
        }
        return arrayList;
    }

    public final String d() {
        if (this.f12622f == null) {
            return null;
        }
        String str = this.f12624h;
        int iJ0 = Q8.h.J0(str, '?', 0, false, 6) + 1;
        String strSubstring = str.substring(iJ0, d9.b.e(str, '#', iJ0, str.length()));
        A7.m.e(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public final String e() {
        if (this.f12620b.length() == 0) {
            return "";
        }
        int length = this.f12619a.length() + 3;
        String str = this.f12624h;
        String strSubstring = str.substring(length, d9.b.f(str, ":@", length, str.length()));
        A7.m.e(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof w) && A7.m.a(((w) obj).f12624h, this.f12624h);
    }

    public final v f() {
        v vVar = new v();
        String str = this.f12619a;
        vVar.d = str;
        vVar.f12613e = e();
        vVar.f12614f = a();
        vVar.f12615g = this.d;
        A7.m.f(str, "scheme");
        int i7 = str.equals("http") ? 80 : str.equals("https") ? 443 : -1;
        int i10 = this.f12621e;
        vVar.f12612b = i10 != i7 ? i10 : -1;
        ArrayList arrayList = vVar.c;
        arrayList.clear();
        arrayList.addAll(c());
        String strD = d();
        String strSubstring = null;
        vVar.f12617i = strD != null ? C1041b.f(C1041b.b(strD, 0, 0, " \"'<>#", true, false, true, false, null, 211)) : null;
        if (this.f12623g != null) {
            String str2 = this.f12624h;
            strSubstring = str2.substring(Q8.h.J0(str2, '#', 0, false, 6) + 1);
            A7.m.e(strSubstring, "this as java.lang.String).substring(startIndex)");
        }
        vVar.f12616h = strSubstring;
        return vVar;
    }

    public final String g() {
        v vVar;
        try {
            vVar = new v();
            vVar.f(this, "/...");
        } catch (IllegalArgumentException unused) {
            vVar = null;
        }
        A7.m.c(vVar);
        vVar.f12613e = C1041b.b("", 0, 0, " \"':;<=>@[]^`{}|/\\?#", false, false, false, false, null, 251);
        vVar.f12614f = C1041b.b("", 0, 0, " \"':;<=>@[]^`{}|/\\?#", false, false, false, false, null, 251);
        return vVar.c().f12624h;
    }

    public final URI h() {
        String strReplaceAll;
        v vVarF = f();
        String str = (String) vVarF.f12615g;
        if (str != null) {
            Pattern patternCompile = Pattern.compile("[\"<>^`{|}]");
            A7.m.e(patternCompile, "compile(...)");
            strReplaceAll = patternCompile.matcher(str).replaceAll("");
            A7.m.e(strReplaceAll, "replaceAll(...)");
        } else {
            strReplaceAll = null;
        }
        vVarF.f12615g = strReplaceAll;
        ArrayList arrayList = vVarF.c;
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            arrayList.set(i7, C1041b.b((String) arrayList.get(i7), 0, 0, "[]", true, true, false, false, null, 227));
        }
        ArrayList arrayList2 = (ArrayList) vVarF.f12617i;
        if (arrayList2 != null) {
            int size2 = arrayList2.size();
            for (int i10 = 0; i10 < size2; i10++) {
                String str2 = (String) arrayList2.get(i10);
                arrayList2.set(i10, str2 != null ? C1041b.b(str2, 0, 0, "\\^`{|}", true, true, true, false, null, 195) : null);
            }
        }
        String str3 = (String) vVarF.f12616h;
        vVarF.f12616h = str3 != null ? C1041b.b(str3, 0, 0, " \"#<>\\^`{|}", true, true, false, true, null, 163) : null;
        String string = vVarF.toString();
        try {
            return new URI(string);
        } catch (URISyntaxException e10) {
            try {
                Pattern patternCompile2 = Pattern.compile("[\\u0000-\\u001F\\u007F-\\u009F\\p{javaWhitespace}]");
                A7.m.e(patternCompile2, "compile(...)");
                String strReplaceAll2 = patternCompile2.matcher(string).replaceAll("");
                A7.m.e(strReplaceAll2, "replaceAll(...)");
                URI uriCreate = URI.create(strReplaceAll2);
                A7.m.e(uriCreate, "{\n      // Unlikely edge…Unexpected!\n      }\n    }");
                return uriCreate;
            } catch (Exception unused) {
                throw new RuntimeException(e10);
            }
        }
    }

    public final int hashCode() {
        return this.f12624h.hashCode();
    }

    public final String toString() {
        return this.f12624h;
    }
}
