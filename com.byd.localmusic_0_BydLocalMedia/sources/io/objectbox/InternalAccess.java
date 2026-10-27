package io.objectbox;

import io.objectbox.sync.SyncClient;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public class InternalAccess {
    public static <T> Cursor<T> getReader(Box<T> box) {
        return box.getReader();
    }

    public static long getHandle(BoxStore boxStore) {
        return boxStore.internalHandle();
    }

    public static Transaction getActiveTx(BoxStore boxStore) {
        Transaction transaction = boxStore.activeTx.get();
        if (transaction == null) {
            throw new IllegalStateException("No active transaction");
        }
        transaction.checkOpen();
        return transaction;
    }

    public static long getHandle(Cursor cursor) {
        return cursor.internalHandle();
    }

    public static long getHandle(Transaction transaction) {
        return transaction.internalHandle();
    }

    public static void setSyncClient(BoxStore boxStore, @Nullable SyncClient syncClient) {
        boxStore.setSyncClient(syncClient);
    }

    public static <T> void releaseReader(Box<T> box, Cursor<T> cursor) {
        box.releaseReader(cursor);
    }

    public static <T> Cursor<T> getWriter(Box<T> box) {
        return box.getWriter();
    }

    public static <T> Cursor<T> getActiveTxCursor(Box<T> box) {
        return box.getActiveTxCursor();
    }

    public static <T> long getActiveTxCursorHandle(Box<T> box) {
        return box.getActiveTxCursor().internalHandle();
    }

    public static <T> void releaseWriter(Box<T> box, Cursor<T> cursor) {
        box.releaseWriter(cursor);
    }

    public static <T> void commitWriter(Box<T> box, Cursor<T> cursor) {
        box.commitWriter(cursor);
    }

    public static void enableCreationStackTracking() {
        Transaction.TRACK_CREATION_STACK = true;
        Cursor.TRACK_CREATION_STACK = true;
    }
}
