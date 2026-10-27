package com.amazon.scrublib.utils;

import com.google.firebase.perf.plugin.util.AsmString;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AtomicMap.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010&\n\u0002\b\u0006\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003B\u0007¢\u0006\u0004\b\u0017\u0010\u0018J\u001a\u0010\u0005\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u0004\u001a\u00028\u0000H\u0086\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u001d\u0010\t\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00028\u0001¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00028\u0000¢\u0006\u0004\b\f\u0010\rR&\u0010\u0010\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000f0\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011R#\u0010\u0016\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00130\u00128F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0019"}, d2 = {"Lcom/amazon/scrublib/utils/AtomicMap;", "K", "V", "", "key", "get", AsmString.METHOD_DESC_FIREBASE_PERF_URL_CONNECTION_INSTRUMENT_WITH_OBJECT, "value", "", "put", "(Ljava/lang/Object;Ljava/lang/Object;)V", "", "containsKey", "(Ljava/lang/Object;)Z", "Ljava/util/concurrent/atomic/AtomicReference;", "", "atomicMapReference", "Ljava/util/concurrent/atomic/AtomicReference;", "", "", "getEntries", "()Ljava/util/Set;", "entries", "<init>", "()V", "DMPScrubCore_release"}, k = 1, mv = {1, 7, 1})
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

    public final boolean containsKey(K key) {
        return get(key) != null;
    }

    public final Set<Map.Entry<K, V>> getEntries() {
        return this.atomicMapReference.get().entrySet();
    }
}
