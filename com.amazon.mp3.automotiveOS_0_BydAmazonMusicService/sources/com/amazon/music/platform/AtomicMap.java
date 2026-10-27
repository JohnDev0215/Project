package com.amazon.music.platform;

import com.google.firebase.perf.plugin.util.AsmString;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AtomicMap.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010%\n\u0002\b\u0005\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003B\u0007¢\u0006\u0004\b\u0013\u0010\u0014J\u001a\u0010\u0005\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0004\u001a\u00028\u0000H\u0086\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u001d\u0010\t\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00028\u0001¢\u0006\u0004\b\t\u0010\nJ \u0010\u000e\u001a\u00020\b2\u0018\u0010\r\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\f0\u000bR&\u0010\u0011\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00100\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/platform/AtomicMap;", "K", "V", "", "key", "get", AsmString.METHOD_DESC_FIREBASE_PERF_URL_CONNECTION_INSTRUMENT_WITH_OBJECT, "value", "", "put", "(Ljava/lang/Object;Ljava/lang/Object;)V", "", "Lkotlin/Pair;", "pairs", "putAll", "Ljava/util/concurrent/atomic/AtomicReference;", "", "atomicMapReference", "Ljava/util/concurrent/atomic/AtomicReference;", "<init>", "()V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final class AtomicMap<K, V> {
    private final AtomicReference<Map<K, V>> atomicMapReference;

    public AtomicMap() {
        AtomicReference<Map<K, V>> atomicReference = new AtomicReference<>();
        this.atomicMapReference = atomicReference;
        atomicReference.set(new LinkedHashMap());
    }

    public final V get(K key) {
        return this.atomicMapReference.get().get(key);
    }

    public final void put(K key, V value) {
        Map<K, V> map = this.atomicMapReference.get();
        Intrinsics.checkNotNullExpressionValue(map, "atomicMapReference.get()");
        map.put(key, value);
    }

    public final void putAll(Iterable<? extends Pair<? extends K, ? extends V>> pairs) {
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        Map<K, V> map = this.atomicMapReference.get();
        Intrinsics.checkNotNullExpressionValue(map, "atomicMapReference.get()");
        MapsKt__MapsKt.putAll(map, pairs);
    }
}
