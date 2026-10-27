package com.amazon.music.platform;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AtomicMap.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u001a=\u0010\u0006\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0012\u0012\u0006\b\u0000\u0012\u00028\u0000\u0012\u0006\b\u0000\u0012\u00028\u00010\u00022\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0003H\u0086\u0002\u001a@\u0010\t\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0012\u0012\u0006\b\u0000\u0012\u00028\u0000\u0012\u0006\b\u0000\u0012\u00028\u00010\u00022\u0006\u0010\u0007\u001a\u00028\u00002\u0006\u0010\b\u001a\u00028\u0001H\u0086\u0002¢\u0006\u0004\b\t\u0010\n\u001a\u001e\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0002\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001¨\u0006\f"}, d2 = {"K", "V", "Lcom/amazon/music/platform/AtomicMap;", "Lkotlin/Pair;", "pair", "", "plusAssign", "key", "value", "set", "(Lcom/amazon/music/platform/AtomicMap;Ljava/lang/Object;Ljava/lang/Object;)V", "atomicMapOf", "MusicPlatformCore_release"}, k = 2, mv = {1, 7, 1})
public final class AtomicMapKt {
    public static final <K, V> void plusAssign(AtomicMap<? super K, ? super V> atomicMap, Pair<? extends K, ? extends V> pair) {
        Intrinsics.checkNotNullParameter(atomicMap, "<this>");
        Intrinsics.checkNotNullParameter(pair, "pair");
        atomicMap.put(pair.getFirst(), pair.getSecond());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <K, V> void set(AtomicMap<? super K, ? super V> atomicMap, K k, V v) {
        Intrinsics.checkNotNullParameter(atomicMap, "<this>");
        atomicMap.put(k, v);
    }

    public static final <K, V> AtomicMap<K, V> atomicMapOf() {
        return new AtomicMap<>();
    }
}
