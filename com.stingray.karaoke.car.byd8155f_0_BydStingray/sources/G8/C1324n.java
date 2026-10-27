package g8;

import io.sentry.C1489n1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import n7.AbstractC2000o;
import n7.x;
import n7.z;
import w8.EnumC2609d;

/* JADX INFO: renamed from: g8.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1324n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f15021a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f15022b = new ArrayList();
    public m7.i c = new m7.i("V", null);

    public C1324n(C1489n1 c1489n1, String str) {
        this.f15021a = str;
    }

    public final void a(String str, C1315e... c1315eArr) {
        C1326p c1326p;
        A7.m.f(str, "type");
        ArrayList arrayList = this.f15022b;
        if (c1315eArr.length == 0) {
            c1326p = null;
        } else {
            P8.p pVar = new P8.p(new A3.g(c1315eArr, 8), 2);
            int iQ = z.q(AbstractC2000o.J(pVar, 10));
            if (iQ < 16) {
                iQ = 16;
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap(iQ);
            Iterator it = pVar.iterator();
            while (true) {
                P8.b bVar = (P8.b) it;
                if (!bVar.f5459m.hasNext()) {
                    break;
                }
                x xVar = (x) bVar.next();
                linkedHashMap.put(Integer.valueOf(xVar.f20159a), (C1315e) xVar.f20160b);
            }
            c1326p = new C1326p(linkedHashMap);
        }
        arrayList.add(new m7.i(str, c1326p));
    }

    public final void b(String str, C1315e... c1315eArr) {
        A7.m.f(str, "type");
        P8.p pVar = new P8.p(new A3.g(c1315eArr, 8), 2);
        int iQ = z.q(AbstractC2000o.J(pVar, 10));
        if (iQ < 16) {
            iQ = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iQ);
        Iterator it = pVar.iterator();
        while (true) {
            P8.b bVar = (P8.b) it;
            if (!bVar.f5459m.hasNext()) {
                this.c = new m7.i(str, new C1326p(linkedHashMap));
                return;
            } else {
                x xVar = (x) bVar.next();
                linkedHashMap.put(Integer.valueOf(xVar.f20159a), (C1315e) xVar.f20160b);
            }
        }
    }

    public final void c(EnumC2609d enumC2609d) {
        A7.m.f(enumC2609d, "type");
        String strC = enumC2609d.c();
        A7.m.e(strC, "type.desc");
        this.c = new m7.i(strC, null);
    }
}
