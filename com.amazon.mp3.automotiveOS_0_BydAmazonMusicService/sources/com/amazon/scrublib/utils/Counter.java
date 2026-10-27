package com.amazon.scrublib.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Counter.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u001d\u0010\u001eJ\u001d\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00028\u00002\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\t\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00028\u0000¢\u0006\u0004\b\t\u0010\nJ\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\f2\u0006\u0010\u000b\u001a\u00020\u0004J\u0015\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00028\u0000¢\u0006\u0004\b\u000e\u0010\u000fR#\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00040\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0016\u001a\u00020\u00158\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u001c\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001f"}, d2 = {"Lcom/amazon/scrublib/utils/Counter;", "T", "", "item", "", "v", "", "add", "(Ljava/lang/Object;I)V", "put", "(Ljava/lang/Object;)V", "n", "", "mostCommon", "getCount", "(Ljava/lang/Object;)I", "Lcom/amazon/scrublib/utils/AtomicMap;", "counts", "Lcom/amazon/scrublib/utils/AtomicMap;", "getCounts", "()Lcom/amazon/scrublib/utils/AtomicMap;", "Lcom/amazon/scrublib/utils/AtomicInt;", "backingInt", "Lcom/amazon/scrublib/utils/AtomicInt;", "getBackingInt", "()Lcom/amazon/scrublib/utils/AtomicInt;", "getTotalCount", "()I", "totalCount", "<init>", "()V", "DMPScrubCore_release"}, k = 1, mv = {1, 7, 1})
public final class Counter<T> {
    private final AtomicMap<T, Integer> counts = new AtomicMap<>();
    private final AtomicInt backingInt = new AtomicInt(0);

    public final int getTotalCount() {
        return this.backingInt.get();
    }

    public final void add(T item, int v) {
        if (this.counts.containsKey(item)) {
            AtomicMap<T, Integer> atomicMap = this.counts;
            Integer num = atomicMap.get(item);
            Intrinsics.checkNotNull(num);
            AtomicMapKt.set(atomicMap, item, Integer.valueOf(num.intValue() + v));
        } else {
            AtomicMapKt.set(this.counts, item, Integer.valueOf(v));
        }
        AtomicInt atomicInt = this.backingInt;
        atomicInt.set(atomicInt.get() + v);
    }

    public final void put(T item) {
        add(item, 1);
    }

    public final List<T> mostCommon(int n) {
        List listSubList = CollectionsKt___CollectionsKt.sortedWith(this.counts.getEntries(), new Comparator() { // from class: com.amazon.scrublib.utils.Counter$mostCommon$$inlined$sortedByDescending$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt__ComparisonsKt.compareValues((Integer) ((Map.Entry) t2).getValue(), (Integer) ((Map.Entry) t).getValue());
            }
        }).subList(0, Math.min(getTotalCount(), n));
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listSubList, 10));
        Iterator<T> it = listSubList.iterator();
        while (it.hasNext()) {
            arrayList.add(((Map.Entry) it.next()).getKey());
        }
        return arrayList;
    }

    public final int getCount(T item) {
        if (!this.counts.containsKey(item)) {
            return 0;
        }
        Integer num = this.counts.get(item);
        Intrinsics.checkNotNull(num);
        return num.intValue();
    }
}
