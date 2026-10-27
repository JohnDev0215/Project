package d0;

import g1.AbstractC1274a;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import l3.P;
import n7.AbstractC1999n;
import n7.z;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class l implements k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final A7.o f13272a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final LinkedHashMap f13273b;
    public final LinkedHashMap c;

    /* JADX WARN: Multi-variable type inference failed */
    public l(Map map, InterfaceC2782k interfaceC2782k) {
        this.f13272a = (A7.o) interfaceC2782k;
        this.f13273b = map != null ? z.z(map) : new LinkedHashMap();
        this.c = new LinkedHashMap();
    }

    @Override // d0.k
    public final j a(String str, InterfaceC2772a interfaceC2772a) {
        int length = str.length();
        for (int i7 = 0; i7 < length; i7++) {
            if (!AbstractC1274a.T(str.charAt(i7))) {
                LinkedHashMap linkedHashMap = this.c;
                Object arrayList = linkedHashMap.get(str);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    linkedHashMap.put(str, arrayList);
                }
                ((List) arrayList).add(interfaceC2772a);
                return new P(this, str, interfaceC2772a);
            }
        }
        throw new IllegalArgumentException("Registered key is empty or blank");
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [A7.o, z7.k] */
    @Override // d0.k
    public final boolean b(Object obj) {
        return ((Boolean) this.f13272a.b(obj)).booleanValue();
    }

    @Override // d0.k
    public final Object c(String str) {
        LinkedHashMap linkedHashMap = this.f13273b;
        List list = (List) linkedHashMap.remove(str);
        if (list == null || list.isEmpty()) {
            return null;
        }
        if (list.size() > 1) {
            linkedHashMap.put(str, list.subList(1, list.size()));
        }
        return list.get(0);
    }

    public final Map d() {
        LinkedHashMap linkedHashMapZ = z.z(this.f13273b);
        for (Map.Entry entry : this.c.entrySet()) {
            String str = (String) entry.getKey();
            List list = (List) entry.getValue();
            if (list.size() == 1) {
                Object objA = ((InterfaceC2772a) list.get(0)).a();
                if (objA == null) {
                    continue;
                } else {
                    if (!b(objA)) {
                        throw new IllegalStateException(w0.c.M(objA).toString());
                    }
                    linkedHashMapZ.put(str, AbstractC1999n.z(objA));
                }
            } else {
                int size = list.size();
                ArrayList arrayList = new ArrayList(size);
                for (int i7 = 0; i7 < size; i7++) {
                    Object objA2 = ((InterfaceC2772a) list.get(i7)).a();
                    if (objA2 != null && !b(objA2)) {
                        throw new IllegalStateException(w0.c.M(objA2).toString());
                    }
                    arrayList.add(objA2);
                }
                linkedHashMapZ.put(str, arrayList);
            }
        }
        return linkedHashMapZ;
    }
}
