package io.objectbox;

import io.objectbox.relation.ToMany;
import java.io.Closeable;
import java.util.List;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public abstract class Cursor<T> implements Closeable {
    static boolean LOG_READ_NOT_CLOSED = false;
    protected static final int PUT_FLAG_COMPLETE = 2;
    protected static final int PUT_FLAG_FIRST = 1;
    static boolean TRACK_CREATION_STACK;
    protected final BoxStore boxStoreForEntities;
    protected boolean closed;
    private final Throwable creationThrowable;
    protected final long cursor;
    protected final EntityInfo<T> entityInfo;
    protected final boolean readOnly;
    protected final Transaction tx;

    protected static native long collect002033(long j, long j2, int i, int i2, long j3, int i3, long j4, int i4, float f, int i5, float f2, int i6, float f3, int i7, double d, int i8, double d2, int i9, double d3);

    protected static native long collect004000(long j, long j2, int i, int i2, long j3, int i3, long j4, int i4, long j5, int i5, long j6);

    protected static native long collect313311(long j, long j2, int i, int i2, @Nullable String str, int i3, @Nullable String str2, int i4, @Nullable String str3, int i5, @Nullable byte[] bArr, int i6, long j3, int i7, long j4, int i8, long j5, int i9, int i10, int i11, int i12, int i13, int i14, int i15, float f, int i16, double d);

    protected static native long collect400000(long j, long j2, int i, int i2, @Nullable String str, int i3, @Nullable String str2, int i4, @Nullable String str3, int i5, @Nullable String str4);

    protected static native long collect430000(long j, long j2, int i, int i2, @Nullable String str, int i3, @Nullable String str2, int i4, @Nullable String str3, int i5, @Nullable String str4, int i6, @Nullable byte[] bArr, int i7, @Nullable byte[] bArr2, int i8, @Nullable byte[] bArr3);

    protected static native long collectStringArray(long j, long j2, int i, int i2, @Nullable String[] strArr);

    protected static native long collectStringList(long j, long j2, int i, int i2, @Nullable List<String> list);

    static native boolean nativeDeleteEntity(long j, long j2);

    static native Object nativeFirstEntity(long j);

    static native Object nativeGetEntity(long j, long j2);

    static native long nativeLookupKeyUsingIndex(long j, int i, String str);

    static native Object nativeNextEntity(long j);

    static native boolean nativeSeek(long j, long j2);

    protected abstract long getId(T t);

    native long nativeCount(long j, long j2);

    native void nativeDeleteAll(long j);

    native void nativeDestroy(long j);

    native List<T> nativeGetAllEntities(long j);

    native List<T> nativeGetBacklinkEntities(long j, int i, int i2, long j2);

    native long[] nativeGetBacklinkIds(long j, int i, int i2, long j2);

    native long nativeGetCursorFor(long j, int i);

    native List<T> nativeGetRelationEntities(long j, int i, int i2, long j2, boolean z);

    native long[] nativeGetRelationIds(long j, int i, int i2, long j2, boolean z);

    native void nativeModifyRelations(long j, int i, long j2, long[] jArr, boolean z);

    native void nativeModifyRelationsSingle(long j, int i, long j2, long j3, boolean z);

    native int nativePropertyId(long j, String str);

    native long nativeRenew(long j);

    native void nativeSetBoxStoreForEntities(long j, Object obj);

    public abstract long put(T t);

    protected Cursor(Transaction transaction, long j, EntityInfo<T> entityInfo, BoxStore boxStore) {
        if (transaction == null) {
            throw new IllegalArgumentException("Transaction is null");
        }
        this.tx = transaction;
        this.readOnly = transaction.isReadOnly();
        this.cursor = j;
        this.entityInfo = entityInfo;
        this.boxStoreForEntities = boxStore;
        for (Property<T> property : entityInfo.getAllProperties()) {
            if (!property.isIdVerified()) {
                property.verifyId(getPropertyId(property.dbName));
            }
        }
        this.creationThrowable = TRACK_CREATION_STACK ? new Throwable() : null;
        nativeSetBoxStoreForEntities(j, boxStore);
    }

    protected void finalize() throws Throwable {
        if (this.closed) {
            return;
        }
        if (!this.readOnly || LOG_READ_NOT_CLOSED) {
            System.err.println("Cursor was not closed.");
            if (this.creationThrowable != null) {
                System.err.println("Cursor was initially created here:");
                this.creationThrowable.printStackTrace();
            }
            System.err.flush();
        }
        close();
        super.finalize();
    }

    public EntityInfo<T> getEntityInfo() {
        return this.entityInfo;
    }

    public T get(long j) {
        return (T) nativeGetEntity(this.cursor, j);
    }

    public T next() {
        return (T) nativeNextEntity(this.cursor);
    }

    public T first() {
        return (T) nativeFirstEntity(this.cursor);
    }

    public List<T> getAll() {
        return nativeGetAllEntities(this.cursor);
    }

    public boolean deleteEntity(long j) {
        return nativeDeleteEntity(this.cursor, j);
    }

    public void deleteAll() {
        nativeDeleteAll(this.cursor);
    }

    public boolean seek(long j) {
        return nativeSeek(this.cursor, j);
    }

    public long count(long j) {
        return nativeCount(this.cursor, j);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        if (!this.closed) {
            this.closed = true;
            if (this.tx != null && !this.tx.getStore().isClosed()) {
                nativeDestroy(this.cursor);
            }
        }
    }

    public int getPropertyId(String str) {
        return nativePropertyId(this.cursor, str);
    }

    long lookupKeyUsingIndex(int i, String str) {
        return nativeLookupKeyUsingIndex(this.cursor, i, str);
    }

    public Transaction getTx() {
        return this.tx;
    }

    public boolean isObsolete() {
        return this.tx.isObsolete();
    }

    public boolean isClosed() {
        return this.closed;
    }

    protected <TARGET> Cursor<TARGET> getRelationTargetCursor(Class<TARGET> cls) {
        EntityInfo<T> entityInfo = this.boxStoreForEntities.getEntityInfo(cls);
        return entityInfo.getCursorFactory().createCursor(this.tx, nativeGetCursorFor(this.cursor, entityInfo.getEntityId()), this.boxStoreForEntities);
    }

    public void renew() {
        nativeRenew(this.cursor);
    }

    long internalHandle() {
        return this.cursor;
    }

    List<T> getBacklinkEntities(int i, Property<?> property, long j) {
        try {
            return nativeGetBacklinkEntities(this.cursor, i, property.getId(), j);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Please check if the given property belongs to a valid @Relation: " + property, e);
        }
    }

    long[] getBacklinkIds(int i, Property<?> property, long j) {
        try {
            return nativeGetBacklinkIds(this.cursor, i, property.getId(), j);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Please check if the given property belongs to a valid @Relation: " + property, e);
        }
    }

    public List<T> getRelationEntities(int i, int i2, long j, boolean z) {
        return nativeGetRelationEntities(this.cursor, i, i2, j, z);
    }

    public long[] getRelationIds(int i, int i2, long j, boolean z) {
        return nativeGetRelationIds(this.cursor, i, i2, j, z);
    }

    public void modifyRelations(int i, long j, long[] jArr, boolean z) {
        nativeModifyRelations(this.cursor, i, j, jArr, z);
    }

    public void modifyRelationsSingle(int i, long j, long j2, boolean z) {
        nativeModifyRelationsSingle(this.cursor, i, j, j2, z);
    }

    protected <TARGET> void checkApplyToManyToDb(List<TARGET> list, Class<TARGET> cls) {
        if (list instanceof ToMany) {
            ToMany toMany = (ToMany) list;
            if (toMany.internalCheckApplyToDbRequired()) {
                Cursor<TARGET> relationTargetCursor = getRelationTargetCursor(cls);
                try {
                    toMany.internalApplyToDb(this, relationTargetCursor);
                    if (relationTargetCursor != null) {
                        relationTargetCursor.close();
                    }
                } catch (Throwable th) {
                    if (relationTargetCursor != null) {
                        try {
                            relationTargetCursor.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cursor ");
        sb.append(Long.toString(this.cursor, 16));
        sb.append(isClosed() ? "(closed)" : "");
        return sb.toString();
    }
}
