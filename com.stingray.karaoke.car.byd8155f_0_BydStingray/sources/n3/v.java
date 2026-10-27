package n3;

import android.net.Uri;
import android.os.Bundle;
import h8.AbstractC1387d;
import io.sentry.F1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import n7.AbstractC1998m;
import n7.AbstractC1999n;
import n7.AbstractC2000o;
import n7.AbstractC2004s;

/* JADX INFO: loaded from: classes.dex */
public final class v {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final Pattern f19897m = Pattern.compile("^[a-zA-Z]+[+\\w\\-.]*:");

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final Pattern f19898n = Pattern.compile("\\{(.+?)\\}");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f19899a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f19900b;
    public final String c;
    public final m7.n d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final m7.n f19901e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f19902f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f19903g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Object f19904h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f19905i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Object f19906j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final m7.n f19907k;
    public final boolean l;

    public v(String str) {
        this.f19899a = str;
        ArrayList arrayList = new ArrayList();
        this.f19900b = arrayList;
        this.d = h6.r.o(new t(this, 6));
        this.f19901e = h6.r.o(new t(this, 4));
        m7.g gVar = m7.g.f19257m;
        this.f19902f = h6.r.n(gVar, new t(this, 7));
        this.f19904h = h6.r.n(gVar, new t(this, 1));
        this.f19905i = h6.r.n(gVar, new t(this, 0));
        this.f19906j = h6.r.n(gVar, new t(this, 3));
        this.f19907k = h6.r.o(new t(this, 2));
        h6.r.o(new t(this, 5));
        StringBuilder sb = new StringBuilder("^");
        if (!f19897m.matcher(str).find()) {
            sb.append("http[s]?://");
        }
        Matcher matcher = Pattern.compile("(\\?|\\#|$)").matcher(str);
        matcher.find();
        boolean z6 = false;
        String strSubstring = str.substring(0, matcher.start());
        A7.m.e(strSubstring, "substring(...)");
        a(strSubstring, arrayList, sb);
        if (!Q8.h.C0(sb, F1.DEFAULT_PROPAGATION_TARGETS, false) && !Q8.h.C0(sb, "([^/]+?)", false)) {
            z6 = true;
        }
        this.l = z6;
        sb.append("($|(\\?(.)*)|(\\#(.)*))");
        String string = sb.toString();
        A7.m.e(string, "uriRegex.toString()");
        this.c = Q8.o.x0(string, F1.DEFAULT_PROPAGATION_TARGETS, "\\E.*\\Q");
    }

    public static void a(String str, ArrayList arrayList, StringBuilder sb) {
        Matcher matcher = f19898n.matcher(str);
        int iEnd = 0;
        while (matcher.find()) {
            String strGroup = matcher.group(1);
            A7.m.d(strGroup, "null cannot be cast to non-null type kotlin.String");
            arrayList.add(strGroup);
            if (matcher.start() > iEnd) {
                String strSubstring = str.substring(iEnd, matcher.start());
                A7.m.e(strSubstring, "substring(...)");
                sb.append(Pattern.quote(strSubstring));
            }
            sb.append("([^/]*?|)");
            iEnd = matcher.end();
        }
        if (iEnd < str.length()) {
            String strSubstring2 = str.substring(iEnd);
            A7.m.e(strSubstring2, "substring(...)");
            sb.append(Pattern.quote(strSubstring2));
        }
    }

    public static void g(Bundle bundle, String str, String str2, C1941f c1941f) {
        if (c1941f == null) {
            bundle.putString(str, str2);
            return;
        }
        M m10 = c1941f.f19858a;
        A7.m.f(str, "key");
        m10.e(bundle, str, m10.d(str2));
    }

    public final int b(Uri uri) {
        if (uri == null) {
            return 0;
        }
        String str = this.f19899a;
        List<String> pathSegments = uri.getPathSegments();
        List<String> pathSegments2 = Uri.parse(str).getPathSegments();
        A7.m.e(pathSegments, "requestedPathSegments");
        A7.m.e(pathSegments2, "uriPathSegments");
        Set setC0 = AbstractC1998m.C0(pathSegments);
        setC0.retainAll(pathSegments2);
        return setC0.size();
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.Object, m7.f] */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.lang.Object, m7.f] */
    public final ArrayList c() {
        ArrayList arrayList = this.f19900b;
        Collection collectionValues = ((Map) this.f19902f.getValue()).values();
        ArrayList arrayList2 = new ArrayList();
        Iterator it = collectionValues.iterator();
        while (it.hasNext()) {
            AbstractC2004s.L(arrayList2, ((s) it.next()).f19892b);
        }
        return AbstractC1998m.n0(AbstractC1998m.n0(arrayList, arrayList2), (List) this.f19905i.getValue());
    }

    /* JADX WARN: Type inference failed for: r8v4, types: [java.lang.Object, m7.f] */
    public final Bundle d(Uri uri, LinkedHashMap linkedHashMap) {
        A7.m.f(uri, "deepLink");
        A7.m.f(linkedHashMap, "arguments");
        Pattern pattern = (Pattern) this.d.getValue();
        Matcher matcher = pattern != null ? pattern.matcher(uri.toString()) : null;
        if (matcher == null || !matcher.matches()) {
            return null;
        }
        Bundle bundle = new Bundle();
        if (!e(matcher, bundle, linkedHashMap)) {
            return null;
        }
        if (((Boolean) this.f19901e.getValue()).booleanValue() && !f(uri, bundle, linkedHashMap)) {
            return null;
        }
        String fragment = uri.getFragment();
        Pattern pattern2 = (Pattern) this.f19907k.getValue();
        Matcher matcher2 = pattern2 != null ? pattern2.matcher(String.valueOf(fragment)) : null;
        if (matcher2 != null && matcher2.matches()) {
            List list = (List) this.f19905i.getValue();
            ArrayList arrayList = new ArrayList(AbstractC2000o.J(list, 10));
            int i7 = 0;
            for (Object obj : list) {
                int i10 = i7 + 1;
                if (i7 < 0) {
                    AbstractC1999n.I();
                    throw null;
                }
                String str = (String) obj;
                String strDecode = Uri.decode(matcher2.group(i10));
                C1941f c1941f = (C1941f) linkedHashMap.get(str);
                try {
                    A7.m.e(strDecode, "value");
                    g(bundle, str, strDecode, c1941f);
                    arrayList.add(m7.q.f19267a);
                    i7 = i10;
                } catch (IllegalArgumentException unused) {
                }
            }
        }
        if (h6.e.p(linkedHashMap, new u(0, bundle)).isEmpty()) {
            return bundle;
        }
        return null;
    }

    public final boolean e(Matcher matcher, Bundle bundle, LinkedHashMap linkedHashMap) {
        ArrayList arrayList = this.f19900b;
        ArrayList arrayList2 = new ArrayList(AbstractC2000o.J(arrayList, 10));
        int i7 = 0;
        for (Object obj : arrayList) {
            int i10 = i7 + 1;
            if (i7 < 0) {
                AbstractC1999n.I();
                throw null;
            }
            String str = (String) obj;
            String strDecode = Uri.decode(matcher.group(i10));
            C1941f c1941f = (C1941f) linkedHashMap.get(str);
            try {
                A7.m.e(strDecode, "value");
                g(bundle, str, strDecode, c1941f);
                arrayList2.add(m7.q.f19267a);
                i7 = i10;
            } catch (IllegalArgumentException unused) {
                return false;
            }
        }
        return true;
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof v)) {
            return false;
        }
        return this.f19899a.equals(((v) obj).f19899a) && A7.m.a(null, null) && A7.m.a(null, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.Object, m7.f] */
    public final boolean f(Uri uri, Bundle bundle, LinkedHashMap linkedHashMap) {
        Object objValueOf;
        boolean z6;
        Object obj;
        String query;
        for (Map.Entry entry : ((Map) this.f19902f.getValue()).entrySet()) {
            String str = (String) entry.getKey();
            s sVar = (s) entry.getValue();
            List<String> queryParameters = uri.getQueryParameters(str);
            if (this.f19903g && (query = uri.getQuery()) != null && !query.equals(uri.toString())) {
                queryParameters = AbstractC1387d.j(query);
            }
            A7.m.e(queryParameters, "inputParams");
            m7.q qVar = m7.q.f19267a;
            int i7 = 0;
            Bundle bundleF = w0.c.F(new m7.i[0]);
            for (String str2 : sVar.f19892b) {
                C1941f c1941f = (C1941f) linkedHashMap.get(str2);
                M m10 = c1941f != null ? c1941f.f19858a : null;
                if ((m10 instanceof C1935J) && !c1941f.c) {
                    ((C1935J) m10).getClass();
                    char c = 0;
                    switch (c) {
                        case 0:
                            obj = new boolean[0];
                            break;
                        case 1:
                            obj = new float[0];
                            break;
                        case 2:
                            obj = new int[0];
                            break;
                        case 3:
                            obj = new long[0];
                            break;
                        default:
                            obj = new String[0];
                            break;
                    }
                    m10.e(bundleF, str2, obj);
                }
            }
            for (String str3 : queryParameters) {
                String str4 = sVar.f19891a;
                Matcher matcher = str4 != null ? Pattern.compile(str4, 32).matcher(str3) : null;
                if (matcher == null || !matcher.matches()) {
                    return i7;
                }
                ArrayList arrayList = sVar.f19892b;
                ArrayList arrayList2 = new ArrayList(AbstractC2000o.J(arrayList, 10));
                int i10 = i7;
                for (Object obj2 : arrayList) {
                    int i11 = i10 + 1;
                    if (i10 < 0) {
                        AbstractC1999n.I();
                        throw null;
                    }
                    String str5 = (String) obj2;
                    String strGroup = matcher.group(i11);
                    if (strGroup == null) {
                        strGroup = "";
                    }
                    C1941f c1941f2 = (C1941f) linkedHashMap.get(str5);
                    if (bundleF.containsKey(str5)) {
                        if (bundleF.containsKey(str5)) {
                            if (c1941f2 != null) {
                                M m11 = c1941f2.f19858a;
                                Object objA = m11.a(str5, bundleF);
                                if (!bundleF.containsKey(str5)) {
                                    throw new IllegalArgumentException("There is no previous value in this bundle.");
                                }
                                m11.e(bundleF, str5, m11.c(objA, strGroup));
                            }
                            z6 = false;
                        } else {
                            z6 = true;
                        }
                        objValueOf = Boolean.valueOf(z6);
                        arrayList2.add(objValueOf);
                        i10 = i11;
                        i7 = 0;
                    } else {
                        g(bundleF, str5, strGroup, c1941f2);
                    }
                    objValueOf = qVar;
                    arrayList2.add(objValueOf);
                    i10 = i11;
                    i7 = 0;
                }
            }
            bundle.putAll(bundleF);
        }
        return true;
    }

    public final int hashCode() {
        return this.f19899a.hashCode() * 961;
    }
}
