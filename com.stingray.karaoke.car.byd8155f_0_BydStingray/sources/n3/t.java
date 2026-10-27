package n3;

import android.net.Uri;
import io.sentry.F1;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import n7.AbstractC1998m;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class t extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f19893m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ v f19894n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ t(v vVar, int i7) {
        super(0);
        this.f19893m = i7;
        this.f19894n = vVar;
    }

    /* JADX WARN: Type inference failed for: r14v16, types: [java.lang.Object, m7.f] */
    /* JADX WARN: Type inference failed for: r14v2, types: [java.lang.Object, m7.f] */
    /* JADX WARN: Type inference failed for: r14v22, types: [java.lang.Object, m7.f] */
    @Override // z7.InterfaceC2772a
    public final Object a() {
        List list;
        switch (this.f19893m) {
            case 0:
                m7.i iVar = (m7.i) this.f19894n.f19904h.getValue();
                return (iVar == null || (list = (List) iVar.l) == null) ? new ArrayList() : list;
            case 1:
                String str = this.f19894n.f19899a;
                if (Uri.parse(str).getFragment() == null) {
                    return null;
                }
                ArrayList arrayList = new ArrayList();
                String fragment = Uri.parse(str).getFragment();
                StringBuilder sb = new StringBuilder();
                A7.m.c(fragment);
                v.a(fragment, arrayList, sb);
                String string = sb.toString();
                A7.m.e(string, "fragRegex.toString()");
                return new m7.i(arrayList, string);
            case 2:
                String str2 = (String) this.f19894n.f19906j.getValue();
                if (str2 != null) {
                    return Pattern.compile(str2, 2);
                }
                return null;
            case 3:
                m7.i iVar2 = (m7.i) this.f19894n.f19904h.getValue();
                if (iVar2 != null) {
                    return (String) iVar2.f19259m;
                }
                return null;
            case 4:
                String str3 = this.f19894n.f19899a;
                return Boolean.valueOf((str3 == null || Uri.parse(str3).getQuery() == null) ? false : true);
            case 5:
                this.f19894n.getClass();
                return null;
            case 6:
                String str4 = this.f19894n.c;
                if (str4 != null) {
                    return Pattern.compile(str4, 2);
                }
                return null;
            default:
                v vVar = this.f19894n;
                vVar.getClass();
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                if (((Boolean) vVar.f19901e.getValue()).booleanValue()) {
                    String str5 = vVar.f19899a;
                    Uri uri = Uri.parse(str5);
                    for (String str6 : uri.getQueryParameterNames()) {
                        StringBuilder sb2 = new StringBuilder();
                        List<String> queryParameters = uri.getQueryParameters(str6);
                        if (queryParameters.size() > 1) {
                            throw new IllegalArgumentException(("Query parameter " + str6 + " must only be present once in " + str5 + ". To support repeated query parameters, use an array type for your argument and the pattern provided in your URI will be used to parse each query parameter instance.").toString());
                        }
                        String str7 = (String) AbstractC1998m.a0(queryParameters);
                        if (str7 == null) {
                            vVar.f19903g = true;
                            str7 = str6;
                        }
                        Matcher matcher = v.f19898n.matcher(str7);
                        s sVar = new s();
                        int iEnd = 0;
                        while (matcher.find()) {
                            String strGroup = matcher.group(1);
                            A7.m.d(strGroup, "null cannot be cast to non-null type kotlin.String");
                            sVar.f19892b.add(strGroup);
                            A7.m.e(str7, "queryParam");
                            String strSubstring = str7.substring(iEnd, matcher.start());
                            A7.m.e(strSubstring, "substring(...)");
                            sb2.append(Pattern.quote(strSubstring));
                            sb2.append("(.+?)?");
                            iEnd = matcher.end();
                        }
                        if (iEnd < str7.length()) {
                            String strSubstring2 = str7.substring(iEnd);
                            A7.m.e(strSubstring2, "substring(...)");
                            sb2.append(Pattern.quote(strSubstring2));
                        }
                        String string2 = sb2.toString();
                        A7.m.e(string2, "argRegex.toString()");
                        sVar.f19891a = Q8.o.x0(string2, F1.DEFAULT_PROPAGATION_TARGETS, "\\E.*\\Q");
                        A7.m.e(str6, "paramName");
                        linkedHashMap.put(str6, sVar);
                    }
                }
                return linkedHashMap;
        }
    }
}
