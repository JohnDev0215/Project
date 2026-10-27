package io.objectbox.internal;

import io.objectbox.InternalAccess;
import io.objectbox.Transaction;
import io.objectbox.exception.DbException;
import java.io.Closeable;

/* JADX INFO: loaded from: classes5.dex */
public class DebugCursor implements Closeable {
    private boolean closed;
    private final long handle;
    private final Transaction tx;

    static native long nativeCreate(long j);

    static native void nativeDestroy(long j);

    static native byte[] nativeGet(long j, byte[] bArr);

    static native byte[] nativeSeekOrNext(long j, byte[] bArr);

    public static DebugCursor create(Transaction transaction) {
        long jNativeCreate = nativeCreate(InternalAccess.getHandle(transaction));
        if (jNativeCreate == 0) {
            throw new DbException("Could not create native debug cursor");
        }
        return new DebugCursor(transaction, jNativeCreate);
    }

    public DebugCursor(Transaction transaction, long j) {
        this.tx = transaction;
        this.handle = j;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        if (!this.closed) {
            this.closed = true;
            if (this.tx != null && !this.tx.getStore().isClosed()) {
                nativeDestroy(this.handle);
            }
        }
    }

    protected void finalize() throws Throwable {
        if (this.closed) {
            return;
        }
        close();
        super.finalize();
    }

    public byte[] get(byte[] bArr) {
        return nativeGet(this.handle, bArr);
    }

    public byte[] seekOrNext(byte[] bArr) {
        return nativeSeekOrNext(this.handle, bArr);
    }
}
