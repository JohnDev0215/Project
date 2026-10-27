package io.objectbox;

import io.objectbox.exception.DbException;
import io.objectbox.internal.CursorFactory;
import java.io.Closeable;

/* JADX INFO: loaded from: classes5.dex */
public class Transaction implements Closeable {
    static boolean TRACK_CREATION_STACK;
    private volatile boolean closed;
    private final Throwable creationThrowable;
    private int initialCommitCount;
    private final boolean readOnly;
    private final BoxStore store;
    private final long transaction;

    native void nativeAbort(long j);

    native int[] nativeCommit(long j);

    native long nativeCreateCursor(long j, String str, Class<?> cls);

    native long nativeCreateKeyValueCursor(long j);

    native void nativeDestroy(long j);

    native boolean nativeIsActive(long j);

    native boolean nativeIsOwnerThread(long j);

    native boolean nativeIsReadOnly(long j);

    native boolean nativeIsRecycled(long j);

    native void nativeRecycle(long j);

    native void nativeRenew(long j);

    native void nativeReset(long j);

    public Transaction(BoxStore boxStore, long j, int i) {
        this.store = boxStore;
        this.transaction = j;
        this.initialCommitCount = i;
        this.readOnly = nativeIsReadOnly(j);
        this.creationThrowable = TRACK_CREATION_STACK ? new Throwable() : null;
    }

    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }

    void checkOpen() {
        if (this.closed) {
            throw new IllegalStateException("Transaction is closed");
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        if (!this.closed) {
            this.closed = true;
            this.store.unregisterTransaction(this);
            if (!nativeIsOwnerThread(this.transaction)) {
                boolean zNativeIsActive = nativeIsActive(this.transaction);
                boolean zNativeIsRecycled = nativeIsRecycled(this.transaction);
                if (zNativeIsActive || zNativeIsRecycled) {
                    String str = " (initial commit count: " + this.initialCommitCount + ").";
                    if (zNativeIsActive) {
                        System.err.println("Transaction is still active" + str);
                    } else {
                        System.out.println("Hint: use closeThreadResources() to avoid finalizing recycled transactions" + str);
                        System.out.flush();
                    }
                    if (this.creationThrowable != null) {
                        System.err.println("Transaction was initially created here:");
                        this.creationThrowable.printStackTrace();
                    }
                    System.err.flush();
                }
            }
            if (!this.store.isClosed()) {
                nativeDestroy(this.transaction);
            }
        }
    }

    public void commit() {
        checkOpen();
        this.store.txCommitted(this, nativeCommit(this.transaction));
    }

    public void commitAndClose() {
        commit();
        close();
    }

    public void abort() {
        checkOpen();
        nativeAbort(this.transaction);
    }

    public void reset() {
        checkOpen();
        this.initialCommitCount = this.store.commitCount;
        nativeReset(this.transaction);
    }

    public void recycle() {
        checkOpen();
        nativeRecycle(this.transaction);
    }

    public void renew() {
        checkOpen();
        this.initialCommitCount = this.store.commitCount;
        nativeRenew(this.transaction);
    }

    public KeyValueCursor createKeyValueCursor() {
        checkOpen();
        return new KeyValueCursor(nativeCreateKeyValueCursor(this.transaction));
    }

    public <T> Cursor<T> createCursor(Class<T> cls) {
        checkOpen();
        EntityInfo<T> entityInfo = this.store.getEntityInfo(cls);
        CursorFactory<T> cursorFactory = entityInfo.getCursorFactory();
        long jNativeCreateCursor = nativeCreateCursor(this.transaction, entityInfo.getDbName(), cls);
        if (jNativeCreateCursor == 0) {
            throw new DbException("Could not create native cursor");
        }
        return cursorFactory.createCursor(this, jNativeCreateCursor, this.store);
    }

    public BoxStore getStore() {
        return this.store;
    }

    public boolean isActive() {
        checkOpen();
        return nativeIsActive(this.transaction);
    }

    public boolean isRecycled() {
        checkOpen();
        return nativeIsRecycled(this.transaction);
    }

    public boolean isClosed() {
        return this.closed;
    }

    public boolean isReadOnly() {
        return this.readOnly;
    }

    public boolean isObsolete() {
        return this.initialCommitCount != this.store.commitCount;
    }

    long internalHandle() {
        return this.transaction;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TX ");
        sb.append(Long.toString(this.transaction, 16));
        sb.append(" (");
        sb.append(this.readOnly ? "read-only" : "write");
        sb.append(", initialCommitCount=");
        sb.append(this.initialCommitCount);
        sb.append(")");
        return sb.toString();
    }
}
