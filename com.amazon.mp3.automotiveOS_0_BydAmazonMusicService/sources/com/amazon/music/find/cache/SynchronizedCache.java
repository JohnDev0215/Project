package com.amazon.music.find.cache;

import com.amazon.music.find.performance.TimestampSupplier;
import com.google.firebase.perf.plugin.util.AsmString;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: SynchronizedCache.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\b\u0005\b\u0000\u0018\u0000*\n\b\u0000\u0010\u0001 \u0000*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0004:\u0003=>?B+\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\b\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u001dH\u0002J\u0017\u0010\u001f\u001a\u0004\u0018\u00018\u00012\u0006\u0010 \u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010!JU\u0010\u001f\u001a\u00028\u00012\u0006\u0010 \u001a\u00028\u00002\u0016\u0010\"\u001a\u0012\u0012\u0004\u0012\u00028\u00010#j\b\u0012\u0004\u0012\u00028\u0001`$2&\u0010%\u001a\"\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\u001d\u0018\u00010&j\n\u0012\u0004\u0012\u00028\u0001\u0018\u0001`'H\u0016¢\u0006\u0002\u0010(J\b\u0010)\u001a\u00020\u001dH\u0016J\u001d\u0010*\u001a\u00020\u001d2\u0006\u0010 \u001a\u00028\u00002\u0006\u0010+\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010,J\u001c\u0010-\u001a\u00020\u001d2\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000fH\bJ\u001c\u0010/\u001a\u00020\u001d2\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000fH\bJ\u0015\u00100\u001a\u00020\u001d2\u0006\u0010 \u001a\u00028\u0000H\u0016¢\u0006\u0002\u00101J\b\u00102\u001a\u00020\u001dH\u0016Jf\u00103\u001a\u00020\u001d2\u0018\u00104\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000f0\u000e2\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000f2.\u00105\u001a*\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000f06j\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000f`7H\bJ\b\u00108\u001a\u000209H\u0016J\u0016\u0010:\u001a\u0010\u0012\u0006\b\u0000\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010;H\u0016J\u0018\u0010<\u001a\u00020\u0018*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000fH\bR\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\fR \u0010\r\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000f0\u000eX\u0088\u0004¢\u0006\u0002\n\u0000R&\u0010\u0010\u001a\u001a\u0012\u0004\u0012\u00028\u0000\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000f0\u0011X\u0088\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0013X\u0088\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0015X\u0088\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u001b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000f0\u000eX\u0088\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\fR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\f¨\u0006@"}, d2 = {"Lcom/amazon/music/find/cache/SynchronizedCache;", "Key", "", "Value", "Lcom/amazon/music/find/cache/Cache;", "accessExpireTimeMilliseconds", "", "writeExpireTimeMilliseconds", "maxCacheSize", "timestampSupplier", "Lcom/amazon/music/find/performance/TimestampSupplier;", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Lcom/amazon/music/find/performance/TimestampSupplier;)V", "Ljava/lang/Long;", "mAccessQueue", "", "Lcom/amazon/music/find/cache/SynchronizedCache$CacheEntry;", "mCacheEntries", "", "mCacheEntriesLastsAccessTimeComparator", "Lcom/amazon/music/find/cache/SynchronizedCache$CacheEntryLastAccessTimeComparator;", "mCacheEntriesLastsWriteTimeComparator", "Lcom/amazon/music/find/cache/SynchronizedCache$CacheEntryLastWriteTimeComparator;", "mCacheEntriesLock", "mEvictBySize", "", "mExpiresAfterAccess", "mExpiresAfterWrite", "mWriteQueue", "evictEntries", "", "expireEntries", "get", "key", AsmString.METHOD_DESC_FIREBASE_PERF_URL_CONNECTION_INSTRUMENT_WITH_OBJECT, "loader", "Lkotlin/Function0;", "Lcom/amazon/music/find/cache/CacheLoader;", "callback", "Lkotlin/Function2;", "Lcom/amazon/music/find/cache/CacheCallback;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "invalidate", "put", "value", "(Ljava/lang/Object;Ljava/lang/Object;)V", "recordRead", "cacheEntry", "recordWrite", "remove", "(Ljava/lang/Object;)V", "removeAll", "reshuffleQueue", "queue", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "size", "", "toMap", "", "isExpired", "CacheEntry", "CacheEntryLastAccessTimeComparator", "CacheEntryLastWriteTimeComparator", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SynchronizedCache<Key, Value> implements Cache<Key, Value> {
    private final Long accessExpireTimeMilliseconds;
    private final List<CacheEntry<Key, Value>> mAccessQueue;
    private final Map<Key, CacheEntry<Key, Value>> mCacheEntries;
    private final CacheEntryLastAccessTimeComparator<Key, Value> mCacheEntriesLastsAccessTimeComparator;
    private final CacheEntryLastWriteTimeComparator<Key, Value> mCacheEntriesLastsWriteTimeComparator;
    private final Object mCacheEntriesLock;
    private final boolean mEvictBySize;
    private final boolean mExpiresAfterAccess;
    private final boolean mExpiresAfterWrite;
    private final List<CacheEntry<Key, Value>> mWriteQueue;
    private final Long maxCacheSize;
    private final TimestampSupplier timestampSupplier;
    private final Long writeExpireTimeMilliseconds;

    public SynchronizedCache(Long l, Long l2, Long l3, TimestampSupplier timestampSupplier) {
        Intrinsics.checkNotNullParameter(timestampSupplier, "timestampSupplier");
        this.accessExpireTimeMilliseconds = l;
        this.writeExpireTimeMilliseconds = l2;
        this.maxCacheSize = l3;
        this.timestampSupplier = timestampSupplier;
        this.mCacheEntriesLock = new Object();
        this.mCacheEntries = new LinkedHashMap();
        this.mAccessQueue = new ArrayList();
        this.mWriteQueue = new ArrayList();
        this.mExpiresAfterAccess = l != null && l.longValue() > 0;
        this.mExpiresAfterWrite = l2 != null && l2.longValue() > 0;
        this.mEvictBySize = l3 != null && l3.longValue() > 0;
        this.mCacheEntriesLastsAccessTimeComparator = new CacheEntryLastAccessTimeComparator<>();
        this.mCacheEntriesLastsWriteTimeComparator = new CacheEntryLastWriteTimeComparator<>();
    }

    @Override // com.amazon.music.find.cache.Cache
    public int size() {
        return this.mCacheEntries.size();
    }

    @Override // com.amazon.music.find.cache.Cache
    public Map<? super Key, Value> toMap() {
        Collection<CacheEntry<Key, Value>> collectionValues = this.mCacheEntries.values();
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt___RangesKt.coerceAtLeast(MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(collectionValues, 10)), 16));
        Iterator<T> it = collectionValues.iterator();
        while (it.hasNext()) {
            CacheEntry cacheEntry = (CacheEntry) it.next();
            Pair pair = TuplesKt.to(cacheEntry.getKey(), cacheEntry.getValue());
            linkedHashMap.put(pair.getFirst(), pair.getSecond());
        }
        return linkedHashMap;
    }

    @Override // com.amazon.music.find.cache.Cache
    public Value get(Key key) {
        Intrinsics.checkNotNullParameter(key, "key");
        CacheEntry<Key, Value> cacheEntry = this.mCacheEntries.get(key);
        if (cacheEntry == null) {
            return null;
        }
        if (isExpired(cacheEntry)) {
            synchronized (this.mCacheEntriesLock) {
                expireEntries();
                Unit unit = Unit.INSTANCE;
            }
            return null;
        }
        synchronized (this.mCacheEntriesLock) {
            recordRead(cacheEntry);
            Unit unit2 = Unit.INSTANCE;
        }
        return cacheEntry.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amazon.music.find.cache.Cache
    public Value get(Key key, Function0<? extends Value> loader, Function2<? super Boolean, ? super Value, Unit> callback) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(loader, "loader");
        Value valueInvoke = (Object) get(key);
        if (valueInvoke == null) {
            valueInvoke = loader.invoke();
            put(key, valueInvoke);
            if (callback != null) {
                callback.mo4invoke(Boolean.FALSE, valueInvoke);
            }
        } else if (callback != null) {
            callback.mo4invoke(Boolean.TRUE, valueInvoke);
        }
        return (Value) valueInvoke;
    }

    @Override // com.amazon.music.find.cache.Cache
    public void put(Key key, Value value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        synchronized (this.mCacheEntriesLock) {
            expireEntries();
            Unit unit = Unit.INSTANCE;
        }
        CacheEntry<Key, Value> cacheEntry = this.mCacheEntries.get(key);
        if (cacheEntry != null) {
            synchronized (this.mCacheEntriesLock) {
                recordRead(cacheEntry);
                recordWrite(cacheEntry);
                cacheEntry.setValue(value);
            }
        } else {
            long jNow = this.timestampSupplier.now();
            CacheEntry<Key, Value> cacheEntry2 = new CacheEntry<>(key, value, jNow, jNow);
            synchronized (this.mCacheEntriesLock) {
                recordRead(cacheEntry2);
                recordWrite(cacheEntry2);
                this.mCacheEntries.put(key, cacheEntry2);
            }
        }
        synchronized (this.mCacheEntriesLock) {
            evictEntries();
        }
    }

    @Override // com.amazon.music.find.cache.Cache
    public void remove(Key key) {
        Intrinsics.checkNotNullParameter(key, "key");
        synchronized (this.mCacheEntriesLock) {
            expireEntries();
            CacheEntry<Key, Value> cacheEntryRemove = this.mCacheEntries.remove(key);
            if (cacheEntryRemove != null) {
                this.mAccessQueue.remove(cacheEntryRemove);
                if (this.mExpiresAfterWrite) {
                    this.mWriteQueue.remove(cacheEntryRemove);
                }
            }
        }
    }

    @Override // com.amazon.music.find.cache.Cache
    public void removeAll() {
        synchronized (this.mCacheEntriesLock) {
            this.mCacheEntries.clear();
            this.mWriteQueue.clear();
            this.mAccessQueue.clear();
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.amazon.music.find.cache.Cache
    public void invalidate() {
        synchronized (this.mCacheEntriesLock) {
            expireEntries();
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void expireEntries() {
        List[] listArr = new List[2];
        listArr[0] = this.mExpiresAfterAccess ? this.mAccessQueue : null;
        listArr[1] = this.mExpiresAfterWrite ? this.mWriteQueue : null;
        Iterator it = CollectionsKt__CollectionsKt.listOfNotNull((Object[]) listArr).iterator();
        while (it.hasNext()) {
            Iterator it2 = ((List) it.next()).iterator();
            while (it2.hasNext()) {
                CacheEntry<Key, Value> cacheEntry = (CacheEntry) it2.next();
                if (isExpired(cacheEntry)) {
                    this.mCacheEntries.remove(cacheEntry.getKey());
                    it2.remove();
                }
            }
        }
    }

    private final void recordRead(CacheEntry<Key, Value> cacheEntry) {
        cacheEntry.setLastAccessedTime(this.timestampSupplier.now());
        reshuffleQueue(this.mAccessQueue, cacheEntry, this.mCacheEntriesLastsAccessTimeComparator);
    }

    private final void recordWrite(CacheEntry<Key, Value> cacheEntry) {
        if (this.mExpiresAfterWrite) {
            cacheEntry.setLastWriteTime(this.timestampSupplier.now());
            reshuffleQueue(this.mWriteQueue, cacheEntry, this.mCacheEntriesLastsWriteTimeComparator);
        }
    }

    private final void evictEntries() {
        Long l;
        if (this.mEvictBySize && (l = this.maxCacheSize) != null) {
            long jLongValue = l.longValue();
            int size = this.mCacheEntries.size();
            while (this.mCacheEntries.size() > jLongValue) {
                CacheEntry cacheEntry = (CacheEntry) CollectionsKt___CollectionsKt.firstOrNull((List) this.mAccessQueue);
                if (cacheEntry != null) {
                    this.mCacheEntries.remove(cacheEntry.getKey());
                    this.mWriteQueue.remove(cacheEntry);
                    this.mAccessQueue.remove(cacheEntry);
                }
                if (size == this.mCacheEntries.size()) {
                    return;
                } else {
                    size = this.mCacheEntries.size();
                }
            }
        }
    }

    private final boolean isExpired(CacheEntry<Key, Value> cacheEntry) {
        long jNow = this.timestampSupplier.now();
        return (this.accessExpireTimeMilliseconds != null && this.timestampSupplier.toMilliseconds(jNow - cacheEntry.getLastAccessedTime()) > this.accessExpireTimeMilliseconds.longValue()) || (this.writeExpireTimeMilliseconds != null && this.timestampSupplier.toMilliseconds(jNow - cacheEntry.getLastWriteTime()) > this.writeExpireTimeMilliseconds.longValue());
    }

    private final void reshuffleQueue(List<CacheEntry<Key, Value>> queue, CacheEntry<Key, Value> cacheEntry, Comparator<CacheEntry<Key, Value>> comparator) {
        queue.remove(cacheEntry);
        int iBinarySearch$default = CollectionsKt__CollectionsKt.binarySearch$default(queue, cacheEntry, comparator, 0, 0, 12, null);
        if (iBinarySearch$default < 0) {
            iBinarySearch$default = (iBinarySearch$default + 1) * (-1);
        }
        queue.add(iBinarySearch$default, cacheEntry);
    }

    /* JADX INFO: compiled from: SynchronizedCache.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\b\b\u0002\u0010\u0001*\u00020\u0002*\b\b\u0003\u0010\u0003*\u00020\u00022\u00020\u0002B%\u0012\u0006\u0010\u0004\u001a\u00028\u0002\u0012\u0006\u0010\u0005\u001a\u00028\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tJ\u000e\u0010\u0016\u001a\u00028\u0002HÆ\u0003¢\u0006\u0002\u0010\u000bJ\u000e\u0010\u0017\u001a\u00028\u0003HÆ\u0003¢\u0006\u0002\u0010\u000bJ\t\u0010\u0018\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0007HÆ\u0003JB\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u00002\b\b\u0002\u0010\u0004\u001a\u00028\u00022\b\b\u0002\u0010\u0005\u001a\u00028\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007HÆ\u0001¢\u0006\u0002\u0010\u001bJ\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\t\u0010!\u001a\u00020\"HÖ\u0001R\u0013\u0010\u0004\u001a\u00028\u0002¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\b\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u000e\"\u0004\b\u0012\u0010\u0010R\u001c\u0010\u0005\u001a\u00028\u0003X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\f\u001a\u0004\b\u0013\u0010\u000b\"\u0004\b\u0014\u0010\u0015¨\u0006#"}, d2 = {"Lcom/amazon/music/find/cache/SynchronizedCache$CacheEntry;", "Key", "", "Value", "key", "value", "lastAccessedTime", "", "lastWriteTime", "(Ljava/lang/Object;Ljava/lang/Object;JJ)V", "getKey", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getLastAccessedTime", "()J", "setLastAccessedTime", "(J)V", "getLastWriteTime", "setLastWriteTime", "getValue", "setValue", "(Ljava/lang/Object;)V", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Object;Ljava/lang/Object;JJ)Lcom/amazon/music/find/cache/SynchronizedCache$CacheEntry;", "equals", "", "other", "hashCode", "", "toString", "", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class CacheEntry<Key, Value> {
        private final Key key;
        private long lastAccessedTime;
        private long lastWriteTime;
        private Value value;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ CacheEntry copy$default(CacheEntry cacheEntry, Object obj, Object obj2, long j, long j2, int i, Object obj3) {
            if ((i & 1) != 0) {
                obj = cacheEntry.key;
            }
            if ((i & 2) != 0) {
                obj2 = cacheEntry.value;
            }
            Object obj4 = obj2;
            if ((i & 4) != 0) {
                j = cacheEntry.lastAccessedTime;
            }
            long j3 = j;
            if ((i & 8) != 0) {
                j2 = cacheEntry.lastWriteTime;
            }
            return cacheEntry.copy(obj, obj4, j3, j2);
        }

        public final Key component1() {
            return this.key;
        }

        public final Value component2() {
            return this.value;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final long getLastAccessedTime() {
            return this.lastAccessedTime;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final long getLastWriteTime() {
            return this.lastWriteTime;
        }

        public final CacheEntry<Key, Value> copy(Key key, Value value, long lastAccessedTime, long lastWriteTime) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(value, "value");
            return new CacheEntry<>(key, value, lastAccessedTime, lastWriteTime);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CacheEntry)) {
                return false;
            }
            CacheEntry cacheEntry = (CacheEntry) other;
            return Intrinsics.areEqual(this.key, cacheEntry.key) && Intrinsics.areEqual(this.value, cacheEntry.value) && this.lastAccessedTime == cacheEntry.lastAccessedTime && this.lastWriteTime == cacheEntry.lastWriteTime;
        }

        public int hashCode() {
            return (((((this.key.hashCode() * 31) + this.value.hashCode()) * 31) + Long.hashCode(this.lastAccessedTime)) * 31) + Long.hashCode(this.lastWriteTime);
        }

        public String toString() {
            return "CacheEntry(key=" + this.key + ", value=" + this.value + ", lastAccessedTime=" + this.lastAccessedTime + ", lastWriteTime=" + this.lastWriteTime + ')';
        }

        public CacheEntry(Key key, Value value, long j, long j2) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(value, "value");
            this.key = key;
            this.value = value;
            this.lastAccessedTime = j;
            this.lastWriteTime = j2;
        }

        public final Key getKey() {
            return this.key;
        }

        public final Value getValue() {
            return this.value;
        }

        public final void setValue(Value value) {
            Intrinsics.checkNotNullParameter(value, "<set-?>");
            this.value = value;
        }

        public final long getLastAccessedTime() {
            return this.lastAccessedTime;
        }

        public final void setLastAccessedTime(long j) {
            this.lastAccessedTime = j;
        }

        public final long getLastWriteTime() {
            return this.lastWriteTime;
        }

        public final void setLastWriteTime(long j) {
            this.lastWriteTime = j;
        }
    }

    /* JADX INFO: compiled from: SynchronizedCache.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000*\b\b\u0002\u0010\u0001*\u00020\u0002*\b\b\u0003\u0010\u0003*\u00020\u00022*\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u00050\u0004j\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0005`\u0006B\u0005¢\u0006\u0002\u0010\u0007J4\u0010\b\u001a\u00020\t2\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u0003\u0018\u00010\u00052\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u0003\u0018\u00010\u0005H\u0016¨\u0006\f"}, d2 = {"Lcom/amazon/music/find/cache/SynchronizedCache$CacheEntryLastAccessTimeComparator;", "Key", "", "Value", "Ljava/util/Comparator;", "Lcom/amazon/music/find/cache/SynchronizedCache$CacheEntry;", "Lkotlin/Comparator;", "()V", "compare", "", "o1", "o2", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class CacheEntryLastAccessTimeComparator<Key, Value> implements Comparator<CacheEntry<Key, Value>> {
        @Override // java.util.Comparator
        public int compare(CacheEntry<Key, Value> o1, CacheEntry<Key, Value> o2) {
            if (o1 == null || o2 == null) {
                return 0;
            }
            return Intrinsics.compare(o1.getLastAccessedTime(), o2.getLastAccessedTime());
        }
    }

    /* JADX INFO: compiled from: SynchronizedCache.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000*\b\b\u0002\u0010\u0001*\u00020\u0002*\b\b\u0003\u0010\u0003*\u00020\u00022*\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u00050\u0004j\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0005`\u0006B\u0005¢\u0006\u0002\u0010\u0007J4\u0010\b\u001a\u00020\t2\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u0003\u0018\u00010\u00052\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u0003\u0018\u00010\u0005H\u0016¨\u0006\f"}, d2 = {"Lcom/amazon/music/find/cache/SynchronizedCache$CacheEntryLastWriteTimeComparator;", "Key", "", "Value", "Ljava/util/Comparator;", "Lcom/amazon/music/find/cache/SynchronizedCache$CacheEntry;", "Lkotlin/Comparator;", "()V", "compare", "", "o1", "o2", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class CacheEntryLastWriteTimeComparator<Key, Value> implements Comparator<CacheEntry<Key, Value>> {
        @Override // java.util.Comparator
        public int compare(CacheEntry<Key, Value> o1, CacheEntry<Key, Value> o2) {
            if (o1 == null || o2 == null) {
                return 0;
            }
            return Intrinsics.compare(o1.getLastWriteTime(), o2.getLastWriteTime());
        }
    }
}
