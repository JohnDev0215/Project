package p;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class zj0 {
    public final LinkedHashMap a = new LinkedHashMap();
    public final LinkedHashMap b = new LinkedHashMap();
    public final LinkedHashMap c = new LinkedHashMap();

    public static String a(rb0 rb0Var) {
        return rb0Var.a + '_' + rb0Var.d;
    }

    public final boolean b(List list, Map map, List list2) {
        v03.j(list, "lastStatsEvents");
        v03.j(map, "lastRateLimitedEvents");
        v03.j(list2, "lastEventStatesCounters");
        LinkedHashMap linkedHashMap = this.a;
        if (linkedHashMap.isEmpty() && (!list.isEmpty())) {
            return true;
        }
        if (!list.isEmpty()) {
            ArrayList arrayList = new ArrayList(lu.W(list));
            Iterator it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(((ik0) it.next()).a);
            }
            if (ou.q0(arrayList).containsAll(linkedHashMap.keySet()) && map.keySet().containsAll(this.b.keySet())) {
                ArrayList arrayList2 = new ArrayList(lu.W(list2));
                Iterator it2 = list2.iterator();
                while (it2.hasNext()) {
                    arrayList2.add(a((rb0) it2.next()));
                }
                if (ou.q0(arrayList2).containsAll(this.c.keySet())) {
                    return true;
                }
            }
        }
        return false;
    }

    public final void c(List list, Map map, List list2) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ik0 ik0Var = (ik0) it.next();
            this.a.put(ik0Var.a, ik0Var);
        }
        for (Map.Entry entry : map.entrySet()) {
            this.b.put((String) entry.getKey(), Integer.valueOf(((Number) entry.getValue()).intValue()));
        }
        Iterator it2 = list2.iterator();
        while (it2.hasNext()) {
            rb0 rb0Var = (rb0) it2.next();
            this.c.put(a(rb0Var), new yj0(rb0Var.b, rb0Var.c));
        }
    }
}
