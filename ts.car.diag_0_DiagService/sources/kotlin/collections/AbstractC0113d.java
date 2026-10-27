package kotlin.collections;

import java.util.Map;
import kotlin.Metadata;
import kotlin.SinceKotlin;

/* JADX WARN: Unexpected interfaces in signature: [java.lang.Object] */
/* JADX INFO: renamed from: kotlin.collections.d, reason: case insensitive filesystem */
/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@SinceKotlin(version = "1.1")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b'\u0018\u0000 \r*\u0004\b\u0000\u0010\u0001*\u0006\b\u0001\u0010\u0002 \u00012\u00020\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\u000b\u0010\f¨\u0006\u000e"}, d2 = {"Lkotlin/collections/d;", "K", "V", "Ljava/util/Map;", "Ljava/util/Map$Entry;", "entry", "Lkotlin/String;", "c", "(Ljava/util/Map$Entry;)Ljava/lang/String;", "Lkotlin/Any;", "o", "b", "(Ljava/lang/Object;)Ljava/lang/String;", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
public abstract class AbstractC0113d<K, V> implements Map<K, V> {
    private final String b(Object o) {
        return o == this ? "(this Map)" : String.valueOf(o);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String c(Map.Entry<? extends K, ? extends V> entry) {
        return b(entry.getKey()) + "=" + b(entry.getValue());
    }
}
