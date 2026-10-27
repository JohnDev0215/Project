package p;

import com.spotify.eventsender.droppedevents.EventState;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public interface ub0 {
    static LinkedHashMap f(ub0 ub0Var) {
        wb0 wb0Var = (wb0) ub0Var;
        LinkedHashMap linkedHashMapK = wb0Var.k(wb0Var.f);
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : linkedHashMapK.entrySet()) {
            String str = (String) entry.getKey();
            i41 i41VarY0 = kotlin.collections.b.y0(((gk0) entry.getValue()).a);
            ArrayList<h41> arrayList2 = new ArrayList();
            for (Object obj : i41VarY0) {
                arrayList2.add(obj);
            }
            ArrayList arrayList3 = new ArrayList(lu.W(arrayList2));
            for (h41 h41Var : arrayList2) {
                long jB = ((qb0) h41Var.b).b();
                qb0 qb0Var = (qb0) h41Var.b;
                long jB2 = qb0Var.b() - qb0Var.a();
                EventState eventState = (EventState) EventState.c.get(Integer.valueOf(h41Var.a));
                if (eventState == null) {
                    eventState = EventState.UNKNOWN;
                }
                arrayList3.add(new rb0(str, jB, jB2, eventState));
            }
            nu.X(arrayList3, arrayList);
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj2 : arrayList) {
            String str2 = ((rb0) obj2).a;
            Object arrayList4 = linkedHashMap.get(str2);
            if (arrayList4 == null) {
                arrayList4 = new ArrayList();
                linkedHashMap.put(str2, arrayList4);
            }
            ((List) arrayList4).add(obj2);
        }
        return linkedHashMap;
    }
}
