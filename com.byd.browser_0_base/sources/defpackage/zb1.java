package defpackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: compiled from: _MapsJvm.kt */
/* JADX INFO: loaded from: classes.dex */
public class zb1 extends oj2 {
    public static final Map i0(ArrayList arrayList) {
        hh0 hh0Var = hh0.e;
        int size = arrayList.size();
        if (size == 0) {
            return hh0Var;
        }
        if (size != 1) {
            LinkedHashMap linkedHashMap = new LinkedHashMap(oj2.M(arrayList.size()));
            j0(arrayList, linkedHashMap);
            return linkedHashMap;
        }
        ov1 ov1Var = (ov1) arrayList.get(0);
        i21.e(ov1Var, "pair");
        Map mapSingletonMap = Collections.singletonMap(ov1Var.e, ov1Var.f);
        i21.d(mapSingletonMap, "singletonMap(pair.first, pair.second)");
        return mapSingletonMap;
    }

    public static final void j0(ArrayList arrayList, LinkedHashMap linkedHashMap) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ov1 ov1Var = (ov1) it.next();
            linkedHashMap.put(ov1Var.e, ov1Var.f);
        }
    }
}
