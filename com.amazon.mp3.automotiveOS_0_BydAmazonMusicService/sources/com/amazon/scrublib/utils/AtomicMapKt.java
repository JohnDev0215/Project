package com.amazon.scrublib.utils;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AtomicMap.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0014\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a@\u0010\u0006\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u0012\u0012\u0006\b\u0000\u0012\u00028\u0000\u0012\u0006\b\u0000\u0012\u00028\u00010\u00022\u0006\u0010\u0003\u001a\u00028\u00002\u0006\u0010\u0004\u001a\u00028\u0001H\u0086\u0002¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"K", "V", "Lcom/amazon/scrublib/utils/AtomicMap;", "key", "value", "", "set", "(Lcom/amazon/scrublib/utils/AtomicMap;Ljava/lang/Object;Ljava/lang/Object;)V", "DMPScrubCore_release"}, k = 2, mv = {1, 7, 1})
public final class AtomicMapKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static final <K, V> void set(AtomicMap<? super K, ? super V> atomicMap, K k, V v) {
        Intrinsics.checkNotNullParameter(atomicMap, "<this>");
        atomicMap.put(k, v);
    }
}
