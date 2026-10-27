package h5;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: Maps.kt */
/* JADX INFO: loaded from: classes.dex */
public class h0 extends g0 {
    public static <K, V> Map<K, V> f() {
        return a0.f6639e;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <K, V> Map<K, V> g(Map<K, ? extends V> map) {
        kotlin.jvm.internal.l.e(map, "<this>");
        int size = map.size();
        return size != 0 ? size != 1 ? map : g0.e(map) : f();
    }

    public static final <K, V> void h(Map<? super K, ? super V> map, Iterable<? extends g5.k<? extends K, ? extends V>> pairs) {
        kotlin.jvm.internal.l.e(map, "<this>");
        kotlin.jvm.internal.l.e(pairs, "pairs");
        for (g5.k<? extends K, ? extends V> kVar : pairs) {
            map.put(kVar.a(), kVar.b());
        }
    }

    public static <K, V> Map<K, V> i(Iterable<? extends g5.k<? extends K, ? extends V>> iterable) {
        kotlin.jvm.internal.l.e(iterable, "<this>");
        if (!(iterable instanceof Collection)) {
            return g(j(iterable, new LinkedHashMap()));
        }
        Collection collection = (Collection) iterable;
        int size = collection.size();
        if (size == 0) {
            return f();
        }
        if (size != 1) {
            return j(iterable, new LinkedHashMap(g0.c(collection.size())));
        }
        return g0.d(iterable instanceof List ? (g5.k<? extends K, ? extends V>) ((List) iterable).get(0) : iterable.iterator().next());
    }

    public static final <K, V, M extends Map<? super K, ? super V>> M j(Iterable<? extends g5.k<? extends K, ? extends V>> iterable, M destination) {
        kotlin.jvm.internal.l.e(iterable, "<this>");
        kotlin.jvm.internal.l.e(destination, "destination");
        h(destination, iterable);
        return destination;
    }

    public static <K, V> Map<K, V> k(Map<? extends K, ? extends V> map) {
        kotlin.jvm.internal.l.e(map, "<this>");
        int size = map.size();
        return size != 0 ? size != 1 ? l(map) : g0.e(map) : f();
    }

    public static <K, V> Map<K, V> l(Map<? extends K, ? extends V> map) {
        kotlin.jvm.internal.l.e(map, "<this>");
        return new LinkedHashMap(map);
    }
}
