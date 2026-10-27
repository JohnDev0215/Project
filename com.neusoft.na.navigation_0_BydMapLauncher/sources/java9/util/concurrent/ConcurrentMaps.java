package java9.util.concurrent;

import SevenZip.CRC;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java9.util.Objects;
import java9.util.function.BiConsumer;
import java9.util.function.BiFunction;
import java9.util.function.Function;

/* JADX INFO: loaded from: classes3.dex */
public final class ConcurrentMaps {
    public static <K, V> V merge(ConcurrentMap<K, V> concurrentMap, K k, V v, BiFunction<? super V, ? super V, ? extends V> biFunction) {
        Objects.requireNonNull(concurrentMap);
        Objects.requireNonNull(biFunction);
        Objects.requireNonNull(v);
        while (true) {
            V v2 = (Object) concurrentMap.get(k);
            while (v2 == null) {
                v2 = (Object) concurrentMap.putIfAbsent(k, v);
                if (v2 == null) {
                    return v;
                }
            }
            V vApply = biFunction.apply(v2, v);
            if (vApply != null) {
                if (concurrentMap.replace(k, v2, vApply)) {
                    return vApply;
                }
            } else if (concurrentMap.remove(k, v2)) {
                return null;
            }
        }
    }

    public static <K, V> V computeIfAbsent(ConcurrentMap<K, V> concurrentMap, K k, Function<? super K, ? extends V> function) {
        V vApply;
        Objects.requireNonNull(concurrentMap);
        Objects.requireNonNull(function);
        V vPutIfAbsent = concurrentMap.get(k);
        return (vPutIfAbsent == null && (vApply = function.apply(k)) != null && (vPutIfAbsent = concurrentMap.putIfAbsent(k, vApply)) == null) ? vApply : vPutIfAbsent;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <K, V> V computeIfPresent(ConcurrentMap<K, V> concurrentMap, K k, BiFunction<? super K, ? super V, ? extends V> biFunction) {
        V vApply;
        Objects.requireNonNull(concurrentMap);
        Objects.requireNonNull(biFunction);
        while (true) {
            CRC crc = (Object) concurrentMap.get(k);
            if (crc == 0) {
                return null;
            }
            vApply = biFunction.apply(k, crc);
            if (vApply == null) {
                if (concurrentMap.remove(k, crc)) {
                    break;
                }
            } else if (concurrentMap.replace(k, crc, vApply)) {
                break;
            }
        }
        return vApply;
    }

    public static <K, V> V compute(ConcurrentMap<K, V> concurrentMap, K k, BiFunction<? super K, ? super V, ? extends V> biFunction) {
        V vApply;
        Objects.requireNonNull(concurrentMap);
        while (true) {
            V v = (Object) concurrentMap.get(k);
            do {
                vApply = biFunction.apply(k, v);
                if (vApply != null) {
                    if (v != null) {
                        if (concurrentMap.replace(k, v, vApply)) {
                            return vApply;
                        }
                    } else {
                        v = (Object) concurrentMap.putIfAbsent(k, vApply);
                    }
                } else if (v == null || concurrentMap.remove(k, v)) {
                    return null;
                }
            } while (v != null);
            return vApply;
        }
    }

    public static <K, V> void replaceAll(ConcurrentMap<K, V> concurrentMap, BiFunction<? super K, ? super V, ? extends V> biFunction) {
        Objects.requireNonNull(concurrentMap);
        Objects.requireNonNull(biFunction);
        forEach(concurrentMap, ConcurrentMaps$$Lambda$1.lambdaFactory$(concurrentMap, biFunction));
    }

    static /* synthetic */ void lambda$replaceAll$0(ConcurrentMap concurrentMap, BiFunction biFunction, Object obj, Object obj2) {
        while (!concurrentMap.replace(obj, obj2, biFunction.apply(obj, obj2)) && (obj2 = concurrentMap.get(obj)) != null) {
        }
    }

    public static <K, V> V getOrDefault(ConcurrentMap<K, V> concurrentMap, Object obj, V v) {
        Objects.requireNonNull(concurrentMap);
        V v2 = concurrentMap.get(obj);
        return v2 != null ? v2 : v;
    }

    public static <K, V> void forEach(ConcurrentMap<K, V> concurrentMap, BiConsumer<? super K, ? super V> biConsumer) {
        Objects.requireNonNull(concurrentMap);
        Objects.requireNonNull(biConsumer);
        for (Map.Entry<K, V> entry : concurrentMap.entrySet()) {
            try {
                biConsumer.accept(entry.getKey(), entry.getValue());
            } catch (IllegalStateException unused) {
            }
        }
    }

    private ConcurrentMaps() {
    }
}
