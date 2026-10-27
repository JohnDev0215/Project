package androidx.navigation;

import android.net.Uri;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: NavDeepLink.java */
/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Pattern f1680a = Pattern.compile("^[a-zA-Z]+[+\\w\\-.]*:");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ArrayList<String> f1681b = new ArrayList<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Map<String, c> f1682c = new HashMap();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Pattern f1683d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f1684e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f1685f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final String f1686g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final String f1687h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private Pattern f1688i;
    private final String j;

    /* JADX INFO: compiled from: NavDeepLink.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f1689a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f1690b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private String f1691c;

        a() {
        }

        public g a() {
            return new g(this.f1689a, this.f1690b, this.f1691c);
        }

        public a b(String str) {
            if (str.isEmpty()) {
                throw new IllegalArgumentException("The NavDeepLink cannot have an empty action.");
            }
            this.f1690b = str;
            return this;
        }

        public a c(String str) {
            this.f1691c = str;
            return this;
        }

        public a d(String str) {
            this.f1689a = str;
            return this;
        }
    }

    /* JADX INFO: compiled from: NavDeepLink.java */
    private static class b implements Comparable<b> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        String f1692b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        String f1693c;

        b(String str) {
            String[] strArrSplit = str.split("/", -1);
            this.f1692b = strArrSplit[0];
            this.f1693c = strArrSplit[1];
        }

        @Override // java.lang.Comparable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compareTo(b bVar) {
            int i2 = this.f1692b.equals(bVar.f1692b) ? 2 : 0;
            return this.f1693c.equals(bVar.f1693c) ? i2 + 1 : i2;
        }
    }

    /* JADX INFO: compiled from: NavDeepLink.java */
    private static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f1694a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private ArrayList<String> f1695b = new ArrayList<>();

        c() {
        }

        void a(String str) {
            this.f1695b.add(str);
        }

        String b(int i2) {
            return this.f1695b.get(i2);
        }

        String c() {
            return this.f1694a;
        }

        void d(String str) {
            this.f1694a = str;
        }

        public int e() {
            return this.f1695b.size();
        }
    }

    g(String str, String str2, String str3) {
        this.f1683d = null;
        int i2 = 0;
        this.f1684e = false;
        this.f1685f = false;
        this.f1688i = null;
        this.f1686g = str;
        this.f1687h = str2;
        this.j = str3;
        if (str != null) {
            Uri uri = Uri.parse(str);
            this.f1685f = uri.getQuery() != null;
            StringBuilder sb = new StringBuilder("^");
            if (!f1680a.matcher(str).find()) {
                sb.append("http[s]?://");
            }
            Pattern patternCompile = Pattern.compile("\\{(.+?)\\}");
            if (this.f1685f) {
                Matcher matcher = Pattern.compile("(\\?)").matcher(str);
                if (matcher.find()) {
                    a(str.substring(0, matcher.start()), sb, patternCompile);
                }
                this.f1684e = false;
                for (String str4 : uri.getQueryParameterNames()) {
                    StringBuilder sb2 = new StringBuilder();
                    String queryParameter = uri.getQueryParameter(str4);
                    Matcher matcher2 = patternCompile.matcher(queryParameter);
                    c cVar = new c();
                    int iEnd = i2;
                    while (matcher2.find()) {
                        cVar.a(matcher2.group(1));
                        sb2.append(Pattern.quote(queryParameter.substring(iEnd, matcher2.start())));
                        sb2.append("(.+?)?");
                        iEnd = matcher2.end();
                    }
                    if (iEnd < queryParameter.length()) {
                        sb2.append(Pattern.quote(queryParameter.substring(iEnd)));
                    }
                    cVar.d(sb2.toString().replace(".*", "\\E.*\\Q"));
                    this.f1682c.put(str4, cVar);
                    i2 = 0;
                }
            } else {
                this.f1684e = a(str, sb, patternCompile);
            }
            this.f1683d = Pattern.compile(sb.toString().replace(".*", "\\E.*\\Q"), 2);
        }
        if (str3 != null) {
            if (!Pattern.compile("^[\\s\\S]+/[\\s\\S]+$").matcher(str3).matches()) {
                throw new IllegalArgumentException("The given mimeType " + str3 + " does not match to required \"type/subtype\" format");
            }
            b bVar = new b(str3);
            this.f1688i = Pattern.compile(("^(" + bVar.f1692b + "|[*]+)/(" + bVar.f1693c + "|[*]+)$").replace("*|[*]", "[\\s\\S]"));
        }
    }

    private boolean a(String str, StringBuilder sb, Pattern pattern) {
        Matcher matcher = pattern.matcher(str);
        boolean z = !str.contains(".*");
        int iEnd = 0;
        while (matcher.find()) {
            this.f1681b.add(matcher.group(1));
            sb.append(Pattern.quote(str.substring(iEnd, matcher.start())));
            sb.append("(.+?)");
            iEnd = matcher.end();
            z = false;
        }
        if (iEnd < str.length()) {
            sb.append(Pattern.quote(str.substring(iEnd)));
        }
        sb.append("($|(\\?(.)*))");
        return z;
    }

    private boolean f(Bundle bundle, String str, String str2, d dVar) {
        if (dVar == null) {
            bundle.putString(str, str2);
            return false;
        }
        try {
            dVar.a().g(bundle, str, str2);
            return false;
        } catch (IllegalArgumentException unused) {
            return true;
        }
    }

    public String b() {
        return this.f1687h;
    }

    Bundle c(Uri uri, Map<String, d> map) {
        Matcher matcher;
        Matcher matcher2 = this.f1683d.matcher(uri.toString());
        if (!matcher2.matches()) {
            return null;
        }
        Bundle bundle = new Bundle();
        int size = this.f1681b.size();
        int i2 = 0;
        while (i2 < size) {
            String str = this.f1681b.get(i2);
            i2++;
            if (f(bundle, str, Uri.decode(matcher2.group(i2)), map.get(str))) {
                return null;
            }
        }
        if (this.f1685f) {
            for (String str2 : this.f1682c.keySet()) {
                c cVar = this.f1682c.get(str2);
                String queryParameter = uri.getQueryParameter(str2);
                if (queryParameter != null) {
                    matcher = Pattern.compile(cVar.c()).matcher(queryParameter);
                    if (!matcher.matches()) {
                        return null;
                    }
                } else {
                    matcher = null;
                }
                for (int i3 = 0; i3 < cVar.e(); i3++) {
                    String strDecode = matcher != null ? Uri.decode(matcher.group(i3 + 1)) : null;
                    String strB = cVar.b(i3);
                    d dVar = map.get(strB);
                    if (strDecode != null && !strDecode.replaceAll("[{}]", "").equals(strB) && f(bundle, strB, strDecode, dVar)) {
                        return null;
                    }
                }
            }
        }
        return bundle;
    }

    int d(String str) {
        if (this.j == null || !this.f1688i.matcher(str).matches()) {
            return -1;
        }
        return new b(this.j).compareTo(new b(str));
    }

    boolean e() {
        return this.f1684e;
    }
}
