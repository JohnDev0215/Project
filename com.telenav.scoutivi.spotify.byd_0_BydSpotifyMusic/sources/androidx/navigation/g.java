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

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static final Pattern f2678j = Pattern.compile("^[a-zA-Z]+[+\\w\\-.]*:");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ArrayList<String> f2679a = new ArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Map<String, c> f2680b = new HashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Pattern f2681c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f2682d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f2683e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final String f2684f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final String f2685g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Pattern f2686h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final String f2687i;

    /* JADX INFO: compiled from: NavDeepLink.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f2688a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f2689b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private String f2690c;

        a() {
        }

        public g a() {
            return new g(this.f2688a, this.f2689b, this.f2690c);
        }

        public a b(String str) {
            if (str.isEmpty()) {
                throw new IllegalArgumentException("The NavDeepLink cannot have an empty action.");
            }
            this.f2689b = str;
            return this;
        }

        public a c(String str) {
            this.f2690c = str;
            return this;
        }

        public a d(String str) {
            this.f2688a = str;
            return this;
        }
    }

    /* JADX INFO: compiled from: NavDeepLink.java */
    private static class b implements Comparable<b> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        String f2691e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        String f2692g;

        b(String str) {
            String[] strArrSplit = str.split("/", -1);
            this.f2691e = strArrSplit[0];
            this.f2692g = strArrSplit[1];
        }

        @Override // java.lang.Comparable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compareTo(b bVar) {
            int i7 = this.f2691e.equals(bVar.f2691e) ? 2 : 0;
            return this.f2692g.equals(bVar.f2692g) ? i7 + 1 : i7;
        }
    }

    /* JADX INFO: compiled from: NavDeepLink.java */
    private static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f2693a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private ArrayList<String> f2694b = new ArrayList<>();

        c() {
        }

        void a(String str) {
            this.f2694b.add(str);
        }

        String b(int i7) {
            return this.f2694b.get(i7);
        }

        String c() {
            return this.f2693a;
        }

        void d(String str) {
            this.f2693a = str;
        }

        public int e() {
            return this.f2694b.size();
        }
    }

    g(String str, String str2, String str3) {
        this.f2681c = null;
        int i7 = 0;
        this.f2682d = false;
        this.f2683e = false;
        this.f2686h = null;
        this.f2684f = str;
        this.f2685g = str2;
        this.f2687i = str3;
        if (str != null) {
            Uri uri = Uri.parse(str);
            this.f2683e = uri.getQuery() != null;
            StringBuilder sb = new StringBuilder("^");
            if (!f2678j.matcher(str).find()) {
                sb.append("http[s]?://");
            }
            Pattern patternCompile = Pattern.compile("\\{(.+?)\\}");
            if (this.f2683e) {
                Matcher matcher = Pattern.compile("(\\?)").matcher(str);
                if (matcher.find()) {
                    a(str.substring(0, matcher.start()), sb, patternCompile);
                }
                this.f2682d = false;
                for (String str4 : uri.getQueryParameterNames()) {
                    StringBuilder sb2 = new StringBuilder();
                    String queryParameter = uri.getQueryParameter(str4);
                    Matcher matcher2 = patternCompile.matcher(queryParameter);
                    c cVar = new c();
                    int iEnd = i7;
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
                    this.f2680b.put(str4, cVar);
                    i7 = 0;
                }
            } else {
                this.f2682d = a(str, sb, patternCompile);
            }
            this.f2681c = Pattern.compile(sb.toString().replace(".*", "\\E.*\\Q"), 2);
        }
        if (str3 != null) {
            if (!Pattern.compile("^[\\s\\S]+/[\\s\\S]+$").matcher(str3).matches()) {
                throw new IllegalArgumentException("The given mimeType " + str3 + " does not match to required \"type/subtype\" format");
            }
            b bVar = new b(str3);
            this.f2686h = Pattern.compile(("^(" + bVar.f2691e + "|[*]+)/(" + bVar.f2692g + "|[*]+)$").replace("*|[*]", "[\\s\\S]"));
        }
    }

    private boolean a(String str, StringBuilder sb, Pattern pattern) {
        Matcher matcher = pattern.matcher(str);
        boolean z6 = !str.contains(".*");
        int iEnd = 0;
        while (matcher.find()) {
            this.f2679a.add(matcher.group(1));
            sb.append(Pattern.quote(str.substring(iEnd, matcher.start())));
            sb.append("(.+?)");
            iEnd = matcher.end();
            z6 = false;
        }
        if (iEnd < str.length()) {
            sb.append(Pattern.quote(str.substring(iEnd)));
        }
        sb.append("($|(\\?(.)*))");
        return z6;
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
        return this.f2685g;
    }

    Bundle c(Uri uri, Map<String, d> map) {
        Matcher matcher;
        Matcher matcher2 = this.f2681c.matcher(uri.toString());
        if (!matcher2.matches()) {
            return null;
        }
        Bundle bundle = new Bundle();
        int size = this.f2679a.size();
        int i7 = 0;
        while (i7 < size) {
            String str = this.f2679a.get(i7);
            i7++;
            if (f(bundle, str, Uri.decode(matcher2.group(i7)), map.get(str))) {
                return null;
            }
        }
        if (this.f2683e) {
            for (String str2 : this.f2680b.keySet()) {
                c cVar = this.f2680b.get(str2);
                String queryParameter = uri.getQueryParameter(str2);
                if (queryParameter != null) {
                    matcher = Pattern.compile(cVar.c()).matcher(queryParameter);
                    if (!matcher.matches()) {
                        return null;
                    }
                } else {
                    matcher = null;
                }
                for (int i8 = 0; i8 < cVar.e(); i8++) {
                    String strDecode = matcher != null ? Uri.decode(matcher.group(i8 + 1)) : null;
                    String strB = cVar.b(i8);
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
        if (this.f2687i == null || !this.f2686h.matcher(str).matches()) {
            return -1;
        }
        return new b(this.f2687i).compareTo(new b(str));
    }

    boolean e() {
        return this.f2682d;
    }
}
