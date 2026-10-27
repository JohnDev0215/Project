package c9;

import h2.AbstractC1347d;
import java.net.Proxy;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import n7.AbstractC2000o;

/* JADX INFO: loaded from: classes.dex */
public final class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f12611a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f12612b;
    public final ArrayList c;
    public Object d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f12613e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f12614f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Object f12615g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Object f12616h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Object f12617i;

    public v(C1040a c1040a, e7.f fVar, g9.j jVar, q qVar) {
        List listL;
        this.f12611a = 1;
        A7.m.f(fVar, "routeDatabase");
        A7.m.f(jVar, "call");
        A7.m.f(qVar, "eventListener");
        this.d = c1040a;
        this.f12613e = fVar;
        this.f12614f = jVar;
        this.f12615g = qVar;
        n7.u uVar = n7.u.l;
        this.f12616h = uVar;
        this.f12617i = uVar;
        this.c = new ArrayList();
        w wVar = c1040a.f12540h;
        qVar.p(jVar, wVar);
        URI uriH = wVar.h();
        if (uriH.getHost() == null) {
            listL = d9.b.l(Proxy.NO_PROXY);
        } else {
            List<Proxy> listSelect = c1040a.f12539g.select(uriH);
            listL = (listSelect == null || listSelect.isEmpty()) ? d9.b.l(Proxy.NO_PROXY) : d9.b.x(listSelect);
        }
        this.f12616h = listL;
        this.f12612b = 0;
        qVar.o(jVar, wVar, listL);
    }

    public void a(String str) {
        A7.m.f(str, "pathSegments");
        int i7 = 0;
        do {
            int iF = d9.b.f(str, "/\\", i7, str.length());
            g(str, i7, iF, iF < str.length(), false);
            i7 = iF + 1;
        } while (i7 <= str.length());
    }

    public void b(String str, String str2) {
        A7.m.f(str, "name");
        if (((ArrayList) this.f12617i) == null) {
            this.f12617i = new ArrayList();
        }
        ArrayList arrayList = (ArrayList) this.f12617i;
        A7.m.c(arrayList);
        arrayList.add(C1041b.b(str, 0, 0, " !\"#$&'(),/:;<=>?@[]\\^`{|}~", false, false, true, false, null, 219));
        ArrayList arrayList2 = (ArrayList) this.f12617i;
        A7.m.c(arrayList2);
        arrayList2.add(str2 != null ? C1041b.b(str2, 0, 0, " !\"#$&'(),/:;<=>?@[]\\^`{|}~", false, false, true, false, null, 219) : null);
    }

    public w c() {
        ArrayList arrayList;
        String str = (String) this.d;
        if (str == null) {
            throw new IllegalStateException("scheme == null");
        }
        String strE = C1041b.e((String) this.f12613e, 0, 0, false, 7);
        String strE2 = C1041b.e((String) this.f12614f, 0, 0, false, 7);
        String str2 = (String) this.f12615g;
        if (str2 == null) {
            throw new IllegalStateException("host == null");
        }
        int iD = d();
        ArrayList arrayList2 = this.c;
        ArrayList arrayList3 = new ArrayList(AbstractC2000o.J(arrayList2, 10));
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList3.add(C1041b.e((String) it.next(), 0, 0, false, 7));
        }
        ArrayList<String> arrayList4 = (ArrayList) this.f12617i;
        if (arrayList4 != null) {
            arrayList = new ArrayList(AbstractC2000o.J(arrayList4, 10));
            for (String str3 : arrayList4) {
                arrayList.add(str3 != null ? C1041b.e(str3, 0, 0, true, 3) : null);
            }
        } else {
            arrayList = null;
        }
        String str4 = (String) this.f12616h;
        return new w(str, strE, strE2, str2, iD, arrayList3, arrayList, str4 != null ? C1041b.e(str4, 0, 0, false, 7) : null, toString());
    }

    public int d() {
        int i7;
        int i10 = this.f12612b;
        if (i10 != -1) {
            return i10;
        }
        String str = (String) this.d;
        A7.m.c(str);
        if (str.equals("http")) {
            i7 = 80;
        } else {
            if (!str.equals("https")) {
                return -1;
            }
            i7 = 443;
        }
        return i7;
    }

    public boolean e() {
        return this.f12612b < ((List) this.f12616h).size() || !this.c.isEmpty();
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0141  */
    /* JADX WARN: Unreachable blocks removed: 1, instructions: 2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void f(c9.w r34, java.lang.String r35) {
        /*
            Method dump skipped, instruction units count: 995
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c9.v.f(c9.w, java.lang.String):void");
    }

    public void g(String str, int i7, int i10, boolean z6, boolean z10) {
        String strB = C1041b.b(str, i7, i10, " \"<>^`{}|/\\?#", z10, false, false, false, null, 240);
        if (strB.equals(".") || strB.equalsIgnoreCase("%2e")) {
            return;
        }
        boolean zEquals = strB.equals("..");
        ArrayList arrayList = this.c;
        if (zEquals || strB.equalsIgnoreCase("%2e.") || strB.equalsIgnoreCase(".%2e") || strB.equalsIgnoreCase("%2e%2e")) {
            if (((String) arrayList.remove(arrayList.size() - 1)).length() != 0 || arrayList.isEmpty()) {
                arrayList.add("");
                return;
            } else {
                arrayList.set(arrayList.size() - 1, "");
                return;
            }
        }
        if (((CharSequence) AbstractC1347d.e(1, arrayList)).length() == 0) {
            arrayList.set(arrayList.size() - 1, strB);
        } else {
            arrayList.add(strB);
        }
        if (z6) {
            arrayList.add("");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x00ab  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String toString() {
        /*
            Method dump skipped, instruction units count: 264
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c9.v.toString():java.lang.String");
    }

    public v() {
        this.f12611a = 0;
        this.f12613e = "";
        this.f12614f = "";
        this.f12612b = -1;
        ArrayList arrayList = new ArrayList();
        this.c = arrayList;
        arrayList.add("");
    }
}
