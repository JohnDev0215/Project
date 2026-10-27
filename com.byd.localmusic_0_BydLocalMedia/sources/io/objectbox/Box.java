package io.objectbox;

import io.objectbox.exception.DbException;
import io.objectbox.internal.CallWithHandle;
import io.objectbox.internal.IdGetter;
import io.objectbox.internal.ReflectionCache;
import io.objectbox.query.QueryBuilder;
import io.objectbox.query.QueryCondition;
import io.objectbox.relation.RelationInfo;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public class Box<T> {
    private volatile Field boxStoreField;
    private final Class<T> entityClass;
    private EntityInfo<T> entityInfo;
    private final IdGetter<T> idGetter;
    private final BoxStore store;
    final ThreadLocal<Cursor<T>> activeTxCursor = new ThreadLocal<>();
    private final ThreadLocal<Cursor<T>> threadLocalReader = new ThreadLocal<>();

    private boolean isChanged(T t) {
        return false;
    }

    private boolean putIfChanged(T t) {
        return false;
    }

    Box(BoxStore boxStore, Class<T> cls) {
        this.store = boxStore;
        this.entityClass = cls;
        this.idGetter = boxStore.getEntityInfo(cls).getIdGetter();
    }

    Cursor<T> getReader() {
        Cursor<T> activeTxCursor = getActiveTxCursor();
        if (activeTxCursor != null) {
            return activeTxCursor;
        }
        Cursor<T> cursor = this.threadLocalReader.get();
        if (cursor != null) {
            Transaction transaction = cursor.tx;
            if (transaction.isClosed() || !transaction.isRecycled()) {
                throw new IllegalStateException("Illegal reader TX state");
            }
            transaction.renew();
            cursor.renew();
            return cursor;
        }
        Cursor<T> cursorCreateCursor = this.store.beginReadTx().createCursor(this.entityClass);
        this.threadLocalReader.set(cursorCreateCursor);
        return cursorCreateCursor;
    }

    Cursor<T> getActiveTxCursor() {
        Transaction transaction = this.store.activeTx.get();
        if (transaction == null) {
            return null;
        }
        if (transaction.isClosed()) {
            throw new IllegalStateException("Active TX is closed");
        }
        Cursor<T> cursor = this.activeTxCursor.get();
        if (cursor != null && !cursor.getTx().isClosed()) {
            return cursor;
        }
        Cursor<T> cursorCreateCursor = transaction.createCursor(this.entityClass);
        this.activeTxCursor.set(cursorCreateCursor);
        return cursorCreateCursor;
    }

    Cursor<T> getWriter() {
        Cursor<T> activeTxCursor = getActiveTxCursor();
        if (activeTxCursor != null) {
            return activeTxCursor;
        }
        Transaction transactionBeginTx = this.store.beginTx();
        try {
            return transactionBeginTx.createCursor(this.entityClass);
        } catch (RuntimeException e) {
            transactionBeginTx.close();
            throw e;
        }
    }

    void commitWriter(Cursor<T> cursor) {
        if (this.activeTxCursor.get() == null) {
            cursor.close();
            cursor.getTx().commitAndClose();
        }
    }

    void releaseWriter(Cursor<T> cursor) {
        if (this.activeTxCursor.get() == null) {
            Transaction tx = cursor.getTx();
            if (tx.isClosed()) {
                return;
            }
            cursor.close();
            tx.abort();
            tx.close();
        }
    }

    void releaseReader(Cursor<T> cursor) {
        if (this.activeTxCursor.get() == null) {
            Transaction tx = cursor.getTx();
            if (tx.isClosed() || tx.isRecycled() || !tx.isReadOnly()) {
                throw new IllegalStateException("Illegal reader TX state");
            }
            tx.recycle();
        }
    }

    public void closeThreadResources() {
        Cursor<T> cursor = this.threadLocalReader.get();
        if (cursor != null) {
            cursor.close();
            cursor.getTx().close();
            this.threadLocalReader.remove();
        }
    }

    void txCommitted(Transaction transaction) {
        Cursor<T> cursor = this.activeTxCursor.get();
        if (cursor != null) {
            this.activeTxCursor.remove();
            cursor.close();
        }
    }

    void readTxFinished(Transaction transaction) {
        Cursor<T> cursor = this.activeTxCursor.get();
        if (cursor == null || cursor.getTx() != transaction) {
            return;
        }
        this.activeTxCursor.remove();
        cursor.close();
    }

    int getPropertyId(String str) {
        Cursor<T> reader = getReader();
        try {
            return reader.getPropertyId(str);
        } finally {
            releaseReader(reader);
        }
    }

    public long getId(T t) {
        return this.idGetter.getId(t);
    }

    public T get(long j) {
        Cursor<T> reader = getReader();
        try {
            return reader.get(j);
        } finally {
            releaseReader(reader);
        }
    }

    public List<T> get(Iterable<Long> iterable) {
        ArrayList arrayList = new ArrayList();
        Cursor<T> reader = getReader();
        try {
            Iterator<Long> it = iterable.iterator();
            while (it.hasNext()) {
                T t = reader.get(it.next().longValue());
                if (t != null) {
                    arrayList.add(t);
                }
            }
            return arrayList;
        } finally {
            releaseReader(reader);
        }
    }

    public List<T> get(long[] jArr) {
        ArrayList arrayList = new ArrayList(jArr.length);
        Cursor<T> reader = getReader();
        try {
            for (long j : jArr) {
                T t = reader.get(Long.valueOf(j).longValue());
                if (t != null) {
                    arrayList.add(t);
                }
            }
            return arrayList;
        } finally {
            releaseReader(reader);
        }
    }

    public Map<Long, T> getMap(Iterable<Long> iterable) {
        HashMap map = new HashMap();
        Cursor<T> reader = getReader();
        try {
            for (Long l : iterable) {
                map.put(l, reader.get(l.longValue()));
            }
            return map;
        } finally {
            releaseReader(reader);
        }
    }

    public long count() {
        return count(0L);
    }

    public long count(long j) {
        Cursor<T> reader = getReader();
        try {
            return reader.count(j);
        } finally {
            releaseReader(reader);
        }
    }

    public boolean isEmpty() {
        return count(1L) == 0;
    }

    public List<T> getAll() {
        ArrayList arrayList = new ArrayList();
        Cursor<T> reader = getReader();
        try {
            for (T tFirst = reader.first(); tFirst != null; tFirst = reader.next()) {
                arrayList.add(tFirst);
            }
            return arrayList;
        } finally {
            releaseReader(reader);
        }
    }

    public boolean contains(long j) {
        Cursor<T> reader = getReader();
        try {
            return reader.seek(j);
        } finally {
            releaseReader(reader);
        }
    }

    public long put(T t) {
        Cursor<T> writer = getWriter();
        try {
            long jPut = writer.put(t);
            commitWriter(writer);
            return jPut;
        } finally {
            releaseWriter(writer);
        }
    }

    @SafeVarargs
    public final void put(@Nullable T... tArr) {
        if (tArr == null || tArr.length == 0) {
            return;
        }
        Cursor<T> writer = getWriter();
        try {
            for (T t : tArr) {
                writer.put(t);
            }
            commitWriter(writer);
        } finally {
            releaseWriter(writer);
        }
    }

    public void put(@Nullable Collection<T> collection) {
        if (collection == null || collection.isEmpty()) {
            return;
        }
        Cursor<T> writer = getWriter();
        try {
            Iterator<T> it = collection.iterator();
            while (it.hasNext()) {
                writer.put(it.next());
            }
            commitWriter(writer);
        } finally {
            releaseWriter(writer);
        }
    }

    public void putBatched(@Nullable Collection<T> collection, int i) {
        if (i < 1) {
            throw new IllegalArgumentException("Batch size must be 1 or greater but was " + i);
        }
        if (collection == null) {
            return;
        }
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            Cursor<T> writer = getWriter();
            int i2 = 0;
            while (true) {
                int i3 = i2 + 1;
                if (i2 >= i) {
                    break;
                }
                try {
                    if (!it.hasNext()) {
                        break;
                    }
                    writer.put(it.next());
                    i2 = i3;
                } finally {
                    releaseWriter(writer);
                }
            }
            commitWriter(writer);
        }
    }

    public boolean remove(long j) {
        Cursor<T> writer = getWriter();
        try {
            boolean zDeleteEntity = writer.deleteEntity(j);
            commitWriter(writer);
            return zDeleteEntity;
        } finally {
            releaseWriter(writer);
        }
    }

    public void remove(@Nullable long... jArr) {
        if (jArr == null || jArr.length == 0) {
            return;
        }
        Cursor<T> writer = getWriter();
        try {
            for (long j : jArr) {
                writer.deleteEntity(j);
            }
            commitWriter(writer);
        } finally {
            releaseWriter(writer);
        }
    }

    @Deprecated
    public void removeByKeys(@Nullable Collection<Long> collection) {
        removeByIds(collection);
    }

    public void removeByIds(@Nullable Collection<Long> collection) {
        if (collection == null || collection.isEmpty()) {
            return;
        }
        Cursor<T> writer = getWriter();
        try {
            Iterator<Long> it = collection.iterator();
            while (it.hasNext()) {
                writer.deleteEntity(it.next().longValue());
            }
            commitWriter(writer);
        } finally {
            releaseWriter(writer);
        }
    }

    public boolean remove(T t) {
        Cursor<T> writer = getWriter();
        try {
            boolean zDeleteEntity = writer.deleteEntity(writer.getId(t));
            commitWriter(writer);
            return zDeleteEntity;
        } finally {
            releaseWriter(writer);
        }
    }

    @SafeVarargs
    public final void remove(@Nullable T... tArr) {
        if (tArr == null || tArr.length == 0) {
            return;
        }
        Cursor<T> writer = getWriter();
        try {
            for (T t : tArr) {
                writer.deleteEntity(writer.getId(t));
            }
            commitWriter(writer);
        } finally {
            releaseWriter(writer);
        }
    }

    public void remove(@Nullable Collection<T> collection) {
        if (collection == null || collection.isEmpty()) {
            return;
        }
        Cursor<T> writer = getWriter();
        try {
            Iterator<T> it = collection.iterator();
            while (it.hasNext()) {
                writer.deleteEntity(writer.getId(it.next()));
            }
            commitWriter(writer);
        } finally {
            releaseWriter(writer);
        }
    }

    public void removeAll() {
        Cursor<T> writer = getWriter();
        try {
            writer.deleteAll();
            commitWriter(writer);
        } finally {
            releaseWriter(writer);
        }
    }

    public long panicModeRemoveAll() {
        return this.store.panicModeRemoveAllObjects(getEntityInfo().getEntityId());
    }

    public QueryBuilder<T> query() {
        return new QueryBuilder<>(this, this.store.internalHandle(), this.store.getDbName(this.entityClass));
    }

    public QueryBuilder<T> query(QueryCondition<T> queryCondition) {
        return query().apply(queryCondition);
    }

    public BoxStore getStore() {
        return this.store;
    }

    public synchronized EntityInfo<T> getEntityInfo() {
        if (this.entityInfo == null) {
            Cursor<T> reader = getReader();
            try {
                this.entityInfo = reader.getEntityInfo();
                releaseReader(reader);
            } catch (Throwable th) {
                releaseReader(reader);
                throw th;
            }
        }
        return this.entityInfo;
    }

    public void attach(T t) {
        if (this.boxStoreField == null) {
            try {
                this.boxStoreField = ReflectionCache.getInstance().getField(this.entityClass, "__boxStore");
            } catch (Exception e) {
                throw new DbException("Entity cannot be attached - only active entities with relationships support attaching (class has no __boxStore field(?)) : " + this.entityClass, e);
            }
        }
        try {
            this.boxStoreField.set(t, this.store);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        }
    }

    public Class<T> getEntityClass() {
        return this.entityClass;
    }

    public List<T> internalGetBacklinkEntities(int i, Property<?> property, long j) {
        Cursor<T> reader = getReader();
        try {
            return reader.getBacklinkEntities(i, property, j);
        } finally {
            releaseReader(reader);
        }
    }

    public List<T> internalGetRelationEntities(int i, int i2, long j, boolean z) {
        Cursor<T> reader = getReader();
        try {
            return reader.getRelationEntities(i, i2, j, z);
        } finally {
            releaseReader(reader);
        }
    }

    public long[] internalGetRelationIds(int i, int i2, long j, boolean z) {
        Cursor<T> reader = getReader();
        try {
            return reader.getRelationIds(i, i2, j, z);
        } finally {
            releaseReader(reader);
        }
    }

    public List<T> getRelationEntities(RelationInfo<?, T> relationInfo, long j) {
        return internalGetRelationEntities(relationInfo.sourceInfo.getEntityId(), relationInfo.relationId, j, false);
    }

    public List<T> getRelationBacklinkEntities(RelationInfo<T, ?> relationInfo, long j) {
        return internalGetRelationEntities(relationInfo.sourceInfo.getEntityId(), relationInfo.relationId, j, true);
    }

    public long[] getRelationIds(RelationInfo<?, T> relationInfo, long j) {
        return internalGetRelationIds(relationInfo.sourceInfo.getEntityId(), relationInfo.relationId, j, false);
    }

    public long[] getRelationBacklinkIds(RelationInfo<T, ?> relationInfo, long j) {
        return internalGetRelationIds(relationInfo.sourceInfo.getEntityId(), relationInfo.relationId, j, true);
    }

    public <RESULT> RESULT internalCallWithReaderHandle(CallWithHandle<RESULT> callWithHandle) {
        Cursor<T> reader = getReader();
        try {
            return callWithHandle.call(reader.internalHandle());
        } finally {
            releaseReader(reader);
        }
    }

    public <RESULT> RESULT internalCallWithWriterHandle(CallWithHandle<RESULT> callWithHandle) {
        Cursor<T> writer = getWriter();
        try {
            RESULT resultCall = callWithHandle.call(writer.internalHandle());
            commitWriter(writer);
            return resultCall;
        } finally {
            releaseWriter(writer);
        }
    }

    public String getReaderDebugInfo() {
        Cursor<T> reader = getReader();
        try {
            return reader + " with " + reader.getTx() + "; store's commit count: " + getStore().commitCount;
        } finally {
            releaseReader(reader);
        }
    }
}
