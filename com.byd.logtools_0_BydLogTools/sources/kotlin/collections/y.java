package kotlin.collections;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.TypeCastException;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: Maps.kt */
/* JADX INFO: loaded from: classes.dex */
public class y extends x {
    public static <K, V> Map<K, V> d() {
        EmptyMap emptyMap = EmptyMap.INSTANCE;
        if (emptyMap != null) {
            return emptyMap;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.Map<K, V>");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <K, V> Map<K, V> e(Map<K, ? extends V> map) {
        kotlin.jvm.internal.i.c(map, "$this$optimizeReadOnlyMap");
        int size = map.size();
        return size != 0 ? size != 1 ? map : x.c(map) : d();
    }

    public static final <K, V> void f(Map<? super K, ? super V> map, Iterable<? extends Pair<? extends K, ? extends V>> iterable) {
        kotlin.jvm.internal.i.c(map, "$this$putAll");
        kotlin.jvm.internal.i.c(iterable, "pairs");
        for (Pair<? extends K, ? extends V> pair : iterable) {
            map.put(pair.component1(), pair.component2());
        }
    }

    public static <K, V> Map<K, V> g(Iterable<? extends Pair<? extends K, ? extends V>> iterable) {
        kotlin.jvm.internal.i.c(iterable, "$this$toMap");
        if (!(iterable instanceof Collection)) {
            return e(h(iterable, new LinkedHashMap()));
        }
        Collection collection = (Collection) iterable;
        int size = collection.size();
        if (size == 0) {
            return d();
        }
        if (size != 1) {
            return h(iterable, new LinkedHashMap(x.a(collection.size())));
        }
        return x.b(iterable instanceof List ? (Pair<? extends K, ? extends V>) ((List) iterable).get(0) : iterable.iterator().next());
    }

    public static final <K, V, M extends Map<? super K, ? super V>> M h(Iterable<? extends Pair<? extends K, ? extends V>> iterable, M m) {
        kotlin.jvm.internal.i.c(iterable, "$this$toMap");
        kotlin.jvm.internal.i.c(m, "destination");
        f(m, iterable);
        return m;
    }

    public static <K, V> Map<K, V> i(Map<? extends K, ? extends V> map) {
        kotlin.jvm.internal.i.c(map, "$this$toMap");
        int size = map.size();
        return size != 0 ? size != 1 ? j(map) : x.c(map) : d();
    }

    public static <K, V> Map<K, V> j(Map<? extends K, ? extends V> map) {
        kotlin.jvm.internal.i.c(map, "$this$toMutableMap");
        return new LinkedHashMap(map);
    }
}
