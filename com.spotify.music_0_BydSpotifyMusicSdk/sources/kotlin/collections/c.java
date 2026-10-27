package kotlin.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Pair;
import p.he1;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public abstract class c extends he1 {
    public static Map h0() {
        EmptyMap emptyMap = EmptyMap.c;
        v03.g(emptyMap, "null cannot be cast to non-null type kotlin.collections.Map<K of kotlin.collections.MapsKt__MapsKt.emptyMap, V of kotlin.collections.MapsKt__MapsKt.emptyMap>");
        return emptyMap;
    }

    public static Map i0(Pair... pairArr) {
        if (pairArr.length <= 0) {
            return h0();
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(he1.B(pairArr.length));
        j0(linkedHashMap, pairArr);
        return linkedHashMap;
    }

    public static final void j0(LinkedHashMap linkedHashMap, Pair[] pairArr) {
        for (Pair pair : pairArr) {
            linkedHashMap.put(pair.a(), pair.b());
        }
    }

    public static Map k0(ArrayList arrayList) {
        int size = arrayList.size();
        if (size == 0) {
            return h0();
        }
        if (size == 1) {
            return he1.C((Pair) arrayList.get(0));
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(he1.B(arrayList.size()));
        m0(arrayList, linkedHashMap);
        return linkedHashMap;
    }

    public static Map l0(Map map) {
        v03.j(map, "<this>");
        int size = map.size();
        return size != 0 ? size != 1 ? n0(map) : he1.V(map) : h0();
    }

    public static final void m0(ArrayList arrayList, LinkedHashMap linkedHashMap) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            linkedHashMap.put(pair.a(), pair.b());
        }
    }

    public static LinkedHashMap n0(Map map) {
        v03.j(map, "<this>");
        return new LinkedHashMap(map);
    }
}
