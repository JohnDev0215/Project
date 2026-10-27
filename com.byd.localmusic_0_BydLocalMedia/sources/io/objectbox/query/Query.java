package io.objectbox.query;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.InternalAccess;
import io.objectbox.Property;
import io.objectbox.internal.CallWithHandle;
import io.objectbox.reactive.DataSubscriptionList;
import io.objectbox.reactive.SubscriptionBuilder;
import io.objectbox.relation.RelationInfo;
import io.objectbox.relation.ToOne;
import java.io.Closeable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public class Query<T> implements Closeable {
    private static final int INITIAL_RETRY_BACK_OFF_IN_MS = 10;
    final Box<T> box;

    @Nullable
    private final Comparator<T> comparator;

    @Nullable
    private final List<EagerRelation<T, ?>> eagerRelations;

    @Nullable
    private final QueryFilter<T> filter;
    volatile long handle;
    private final QueryPublisher<T> publisher;
    private final int queryAttempts;
    private final BoxStore store;

    private native void nativeSetParameters(long j, int i, int i2, @Nullable String str, String str2, String str3);

    native long nativeClone(long j);

    native long nativeCount(long j, long j2);

    native String nativeDescribeParameters(long j);

    native void nativeDestroy(long j);

    native List<T> nativeFind(long j, long j2, long j3, long j4) throws Exception;

    native Object nativeFindFirst(long j, long j2);

    native long nativeFindFirstId(long j, long j2);

    native long[] nativeFindIds(long j, long j2, long j3, long j4);

    native Object nativeFindUnique(long j, long j2);

    native long nativeFindUniqueId(long j, long j2);

    native long nativeRemove(long j, long j2);

    native void nativeSetParameter(long j, int i, int i2, @Nullable String str, double d);

    native void nativeSetParameter(long j, int i, int i2, @Nullable String str, long j2);

    native void nativeSetParameter(long j, int i, int i2, @Nullable String str, String str2);

    native void nativeSetParameter(long j, int i, int i2, @Nullable String str, byte[] bArr);

    native void nativeSetParameters(long j, int i, int i2, @Nullable String str, double d, double d2);

    native void nativeSetParameters(long j, int i, int i2, @Nullable String str, long j2, long j3);

    native void nativeSetParameters(long j, int i, int i2, @Nullable String str, int[] iArr);

    native void nativeSetParameters(long j, int i, int i2, @Nullable String str, long[] jArr);

    native void nativeSetParameters(long j, int i, int i2, @Nullable String str, String[] strArr);

    native String nativeToString(long j);

    Query(Box<T> box, long j, @Nullable List<EagerRelation<T, ?>> list, @Nullable QueryFilter<T> queryFilter, @Nullable Comparator<T> comparator) {
        this.box = box;
        BoxStore store = box.getStore();
        this.store = store;
        this.queryAttempts = store.internalQueryAttempts();
        this.handle = j;
        this.publisher = new QueryPublisher<>(this, box);
        this.eagerRelations = list;
        this.filter = queryFilter;
        this.comparator = comparator;
    }

    private Query(Query<T> query, long j) {
        this(query.box, j, query.eagerRelations, query.filter, query.comparator);
    }

    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        if (this.handle != 0) {
            long j = this.handle;
            this.handle = 0L;
            nativeDestroy(j);
        }
    }

    public Query<T> copy() {
        return new Query<>(this, nativeClone(this.handle));
    }

    long cursorHandle() {
        return InternalAccess.getActiveTxCursorHandle(this.box);
    }

    @Nullable
    public T findFirst() {
        ensureNoFilterNoComparator();
        return (T) callInReadTx(new Callable() { // from class: io.objectbox.query.-$$Lambda$Query$gtKVLlr9BeMjbd2t4PhB-bGimdE
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$findFirst$0$Query();
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ Object lambda$findFirst$0$Query() throws Exception {
        Object objNativeFindFirst = nativeFindFirst(this.handle, cursorHandle());
        resolveEagerRelation(objNativeFindFirst);
        return objNativeFindFirst;
    }

    private void ensureNoFilterNoComparator() {
        ensureNoFilter();
        ensureNoComparator();
    }

    private void ensureNoFilter() {
        if (this.filter != null) {
            throw new UnsupportedOperationException("Does not work with a filter. Only find() and forEach() support filters.");
        }
    }

    private void ensureNoComparator() {
        if (this.comparator != null) {
            throw new UnsupportedOperationException("Does not work with a sorting comparator. Only find() supports sorting with a comparator.");
        }
    }

    @Nullable
    public T findUnique() {
        ensureNoFilter();
        return (T) callInReadTx(new Callable() { // from class: io.objectbox.query.-$$Lambda$Query$y1NtPLxmIVhSTrx34d0OVDk_Is8
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$findUnique$1$Query();
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ Object lambda$findUnique$1$Query() throws Exception {
        Object objNativeFindUnique = nativeFindUnique(this.handle, cursorHandle());
        resolveEagerRelation(objNativeFindUnique);
        return objNativeFindUnique;
    }

    @Nonnull
    public List<T> find() {
        return (List) callInReadTx(new Callable() { // from class: io.objectbox.query.-$$Lambda$Query$ovRjxPApUS12yGDt7cTU1SPw8Ec
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$find$2$Query();
            }
        });
    }

    public /* synthetic */ List lambda$find$2$Query() throws Exception {
        List<T> listNativeFind = nativeFind(this.handle, cursorHandle(), 0L, 0L);
        if (this.filter != null) {
            Iterator<T> it = listNativeFind.iterator();
            while (it.hasNext()) {
                if (!this.filter.keep(it.next())) {
                    it.remove();
                }
            }
        }
        resolveEagerRelations(listNativeFind);
        Comparator<T> comparator = this.comparator;
        if (comparator != null) {
            Collections.sort(listNativeFind, comparator);
        }
        return listNativeFind;
    }

    @Nonnull
    public List<T> find(final long j, final long j2) {
        ensureNoFilterNoComparator();
        return (List) callInReadTx(new Callable() { // from class: io.objectbox.query.-$$Lambda$Query$mNJoN8xiH8Tg6VlLPlXX0ClHG-s
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$find$3$Query(j, j2);
            }
        });
    }

    public /* synthetic */ List lambda$find$3$Query(long j, long j2) throws Exception {
        List<T> listNativeFind = nativeFind(this.handle, cursorHandle(), j, j2);
        resolveEagerRelations(listNativeFind);
        return listNativeFind;
    }

    public long findFirstId() {
        checkOpen();
        return ((Long) this.box.internalCallWithReaderHandle(new CallWithHandle() { // from class: io.objectbox.query.-$$Lambda$Query$CbWbmiEYBKic0lnC1D6AVgIcxeQ
            @Override // io.objectbox.internal.CallWithHandle
            public final Object call(long j) {
                return this.f$0.lambda$findFirstId$4$Query(j);
            }
        })).longValue();
    }

    public /* synthetic */ Long lambda$findFirstId$4$Query(long j) {
        return Long.valueOf(nativeFindFirstId(this.handle, j));
    }

    public long findUniqueId() {
        checkOpen();
        return ((Long) this.box.internalCallWithReaderHandle(new CallWithHandle() { // from class: io.objectbox.query.-$$Lambda$Query$eqTX0_HbBTIXGbxwC3coJyrAfeM
            @Override // io.objectbox.internal.CallWithHandle
            public final Object call(long j) {
                return this.f$0.lambda$findUniqueId$5$Query(j);
            }
        })).longValue();
    }

    public /* synthetic */ Long lambda$findUniqueId$5$Query(long j) {
        return Long.valueOf(nativeFindUniqueId(this.handle, j));
    }

    @Nonnull
    public long[] findIds() {
        return findIds(0L, 0L);
    }

    @Nonnull
    public long[] findIds(final long j, final long j2) {
        checkOpen();
        return (long[]) this.box.internalCallWithReaderHandle(new CallWithHandle() { // from class: io.objectbox.query.-$$Lambda$Query$k5abX_gWHsIyqgYKRSDIgsDiIi4
            @Override // io.objectbox.internal.CallWithHandle
            public final Object call(long j3) {
                return this.f$0.lambda$findIds$6$Query(j, j2, j3);
            }
        });
    }

    public /* synthetic */ long[] lambda$findIds$6$Query(long j, long j2, long j3) {
        return nativeFindIds(this.handle, j3, j, j2);
    }

    @Nonnull
    public LazyList<T> findLazy() {
        ensureNoFilterNoComparator();
        return new LazyList<>(this.box, findIds(), false);
    }

    @Nonnull
    public LazyList<T> findLazyCached() {
        ensureNoFilterNoComparator();
        return new LazyList<>(this.box, findIds(), true);
    }

    public PropertyQuery property(Property<T> property) {
        return new PropertyQuery(this, property);
    }

    <R> R callInReadTx(Callable<R> callable) {
        checkOpen();
        return (R) this.store.callInReadTxWithRetry(callable, this.queryAttempts, 10, true);
    }

    public void forEach(final QueryConsumer<T> queryConsumer) {
        ensureNoComparator();
        checkOpen();
        this.box.getStore().runInReadTx(new Runnable() { // from class: io.objectbox.query.-$$Lambda$Query$aH65N6pVjEU6D8oCRyjMLvTTRmw
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$forEach$7$Query(queryConsumer);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ void lambda$forEach$7$Query(QueryConsumer queryConsumer) {
        LazyList lazyList = new LazyList(this.box, findIds(), false);
        int size = lazyList.size();
        for (int i = 0; i < size; i++) {
            Object obj = lazyList.get(i);
            if (obj == null) {
                throw new IllegalStateException("Internal error: data object was null");
            }
            QueryFilter<T> queryFilter = this.filter;
            if (queryFilter == null || queryFilter.keep((T) obj)) {
                if (this.eagerRelations != null) {
                    resolveEagerRelationForNonNullEagerRelations(obj, i);
                }
                try {
                    queryConsumer.accept(obj);
                } catch (BreakForEach unused) {
                    return;
                }
            }
        }
    }

    void resolveEagerRelations(List<T> list) {
        if (this.eagerRelations != null) {
            int i = 0;
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                resolveEagerRelationForNonNullEagerRelations(it.next(), i);
                i++;
            }
        }
    }

    void resolveEagerRelationForNonNullEagerRelations(@Nonnull T t, int i) {
        for (EagerRelation<T, ?> eagerRelation : this.eagerRelations) {
            if (eagerRelation.limit == 0 || i < eagerRelation.limit) {
                resolveEagerRelation(t, eagerRelation);
            }
        }
    }

    void resolveEagerRelation(@Nullable T t) {
        List<EagerRelation<T, ?>> list = this.eagerRelations;
        if (list == null || t == null) {
            return;
        }
        Iterator<EagerRelation<T, ?>> it = list.iterator();
        while (it.hasNext()) {
            resolveEagerRelation(t, it.next());
        }
    }

    void resolveEagerRelation(@Nonnull T t, EagerRelation<T, ?> eagerRelation) {
        if (this.eagerRelations != null) {
            RelationInfo<T, ?> relationInfo = eagerRelation.relationInfo;
            if (relationInfo.toOneGetter != null) {
                ToOne<?> toOne = relationInfo.toOneGetter.getToOne(t);
                if (toOne != null) {
                    toOne.getTarget();
                    return;
                }
                return;
            }
            if (relationInfo.toManyGetter == null) {
                throw new IllegalStateException("Relation info without relation getter: " + relationInfo);
            }
            List<?> toMany = relationInfo.toManyGetter.getToMany(t);
            if (toMany != null) {
                toMany.size();
            }
        }
    }

    public long count() {
        checkOpen();
        ensureNoFilter();
        return ((Long) this.box.internalCallWithReaderHandle(new CallWithHandle() { // from class: io.objectbox.query.-$$Lambda$Query$i36i_BlxyxFkbW6DWLElCe4pz4s
            @Override // io.objectbox.internal.CallWithHandle
            public final Object call(long j) {
                return this.f$0.lambda$count$8$Query(j);
            }
        })).longValue();
    }

    public /* synthetic */ Long lambda$count$8$Query(long j) {
        return Long.valueOf(nativeCount(this.handle, j));
    }

    public Query<T> setParameter(Property<?> property, String str) {
        checkOpen();
        nativeSetParameter(this.handle, property.getEntityId(), property.getId(), (String) null, str);
        return this;
    }

    public Query<T> setParameter(String str, String str2) {
        checkOpen();
        nativeSetParameter(this.handle, 0, 0, str, str2);
        return this;
    }

    public Query<T> setParameter(Property<?> property, long j) {
        checkOpen();
        nativeSetParameter(this.handle, property.getEntityId(), property.getId(), (String) null, j);
        return this;
    }

    public Query<T> setParameter(String str, long j) {
        checkOpen();
        nativeSetParameter(this.handle, 0, 0, str, j);
        return this;
    }

    public Query<T> setParameter(Property<?> property, double d) {
        checkOpen();
        nativeSetParameter(this.handle, property.getEntityId(), property.getId(), (String) null, d);
        return this;
    }

    public Query<T> setParameter(String str, double d) {
        checkOpen();
        nativeSetParameter(this.handle, 0, 0, str, d);
        return this;
    }

    public Query<T> setParameter(Property<?> property, Date date) {
        return setParameter(property, date.getTime());
    }

    public Query<T> setParameter(String str, Date date) {
        return setParameter(str, date.getTime());
    }

    public Query<T> setParameter(Property<?> property, boolean z) {
        return setParameter(property, z ? 1L : 0L);
    }

    public Query<T> setParameter(String str, boolean z) {
        return setParameter(str, z ? 1L : 0L);
    }

    public Query<T> setParameters(Property<?> property, long j, long j2) {
        checkOpen();
        nativeSetParameters(this.handle, property.getEntityId(), property.getId(), (String) null, j, j2);
        return this;
    }

    public Query<T> setParameters(String str, long j, long j2) {
        checkOpen();
        nativeSetParameters(this.handle, 0, 0, str, j, j2);
        return this;
    }

    public Query<T> setParameters(Property<?> property, int[] iArr) {
        checkOpen();
        nativeSetParameters(this.handle, property.getEntityId(), property.getId(), (String) null, iArr);
        return this;
    }

    public Query<T> setParameters(String str, int[] iArr) {
        checkOpen();
        nativeSetParameters(this.handle, 0, 0, str, iArr);
        return this;
    }

    public Query<T> setParameters(Property<?> property, long[] jArr) {
        checkOpen();
        nativeSetParameters(this.handle, property.getEntityId(), property.getId(), (String) null, jArr);
        return this;
    }

    public Query<T> setParameters(String str, long[] jArr) {
        checkOpen();
        nativeSetParameters(this.handle, 0, 0, str, jArr);
        return this;
    }

    public Query<T> setParameters(Property<?> property, double d, double d2) {
        checkOpen();
        nativeSetParameters(this.handle, property.getEntityId(), property.getId(), (String) null, d, d2);
        return this;
    }

    public Query<T> setParameters(String str, double d, double d2) {
        checkOpen();
        nativeSetParameters(this.handle, 0, 0, str, d, d2);
        return this;
    }

    public Query<T> setParameters(Property<?> property, String[] strArr) {
        checkOpen();
        nativeSetParameters(this.handle, property.getEntityId(), property.getId(), (String) null, strArr);
        return this;
    }

    public Query<T> setParameters(String str, String[] strArr) {
        checkOpen();
        nativeSetParameters(this.handle, 0, 0, str, strArr);
        return this;
    }

    public Query<T> setParameters(Property<?> property, String str, String str2) {
        checkOpen();
        nativeSetParameters(this.handle, property.getEntityId(), property.getId(), (String) null, str, str2);
        return this;
    }

    public Query<T> setParameters(String str, String str2, String str3) {
        checkOpen();
        nativeSetParameters(this.handle, 0, 0, str, str2, str3);
        return this;
    }

    public Query<T> setParameter(Property<?> property, byte[] bArr) {
        checkOpen();
        nativeSetParameter(this.handle, property.getEntityId(), property.getId(), (String) null, bArr);
        return this;
    }

    public Query<T> setParameter(String str, byte[] bArr) {
        checkOpen();
        nativeSetParameter(this.handle, 0, 0, str, bArr);
        return this;
    }

    public long remove() {
        checkOpen();
        ensureNoFilter();
        return ((Long) this.box.internalCallWithWriterHandle(new CallWithHandle() { // from class: io.objectbox.query.-$$Lambda$Query$sHQFqI5E3G0cM9aNZQYy-xg-1RQ
            @Override // io.objectbox.internal.CallWithHandle
            public final Object call(long j) {
                return this.f$0.lambda$remove$9$Query(j);
            }
        })).longValue();
    }

    public /* synthetic */ Long lambda$remove$9$Query(long j) {
        return Long.valueOf(nativeRemove(this.handle, j));
    }

    public SubscriptionBuilder<List<T>> subscribe() {
        checkOpen();
        return new SubscriptionBuilder<>(this.publisher, null);
    }

    public SubscriptionBuilder<List<T>> subscribe(DataSubscriptionList dataSubscriptionList) {
        SubscriptionBuilder<List<T>> subscriptionBuilderSubscribe = subscribe();
        subscriptionBuilderSubscribe.dataSubscriptionList(dataSubscriptionList);
        return subscriptionBuilderSubscribe;
    }

    public void publish() {
        this.publisher.publish();
    }

    public String describe() {
        checkOpen();
        return nativeToString(this.handle);
    }

    public String describeParameters() {
        checkOpen();
        return nativeDescribeParameters(this.handle);
    }

    private void checkOpen() {
        if (this.handle == 0) {
            throw new IllegalStateException("This query is closed. Build and use a new one.");
        }
    }
}
