package io.objectbox.tree;

import io.objectbox.BoxStore;
import io.objectbox.InternalAccess;
import java.io.Closeable;
import java.util.concurrent.Callable;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public class Tree implements Closeable {
    private long handle;
    private String pathSeparatorRegex = "\\.";
    private long rootId;
    private final BoxStore store;

    static native long nativeCreate(long j, long j2);

    static native long nativeCreateWithUid(long j, String str);

    static native void nativeDelete(long j);

    native void nativeClearTransaction(long j);

    native LeafNode nativeGetLeafById(long j, long j2);

    native long nativeGetRootId(long j);

    native long nativePutBranch(long j, long j2, long j3, long j4, @Nullable String str);

    native long nativePutMetaBranch(long j, long j2, long j3, String str, @Nullable String str2);

    native long[] nativePutMetaBranches(long j, long j2, String[] strArr);

    native long nativePutMetaLeaf(long j, long j2, long j3, String str, short s, boolean z, @Nullable String str2);

    native long nativePutValueFP(long j, long j2, long j3, long j4, double d);

    native long nativePutValueInteger(long j, long j2, long j3, long j4, long j5);

    native long nativePutValueString(long j, long j2, long j3, long j4, @Nullable String str);

    native boolean nativeSetTransaction(long j, long j2);

    public Tree(BoxStore boxStore, String str) {
        this.store = boxStore;
        if (boxStore == null) {
            throw new IllegalArgumentException("store must not be null");
        }
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("uid must be 0 or not empty");
        }
        this.handle = nativeCreateWithUid(boxStore.getNativeStore(), str);
    }

    public Tree(BoxStore boxStore, long j) {
        this.store = boxStore;
        this.rootId = j;
        if (boxStore == null) {
            throw new IllegalArgumentException("store must not be null");
        }
        this.handle = nativeCreate(boxStore.getNativeStore(), j);
    }

    long getHandle() {
        return this.handle;
    }

    public String getPathSeparatorRegex() {
        return this.pathSeparatorRegex;
    }

    public void setPathSeparatorRegex(String str) {
        this.pathSeparatorRegex = str;
    }

    public long getRootId() {
        return this.rootId;
    }

    public BoxStore getStore() {
        return this.store;
    }

    public Branch getRoot() {
        return new Branch(this, nativeGetRootId(this.handle));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        nativeDelete(this.handle);
        this.handle = 0L;
    }

    public void runInTx(Runnable runnable) {
        this.store.runInTx(createTxRunnable(runnable));
    }

    public void runInReadTx(Runnable runnable) {
        this.store.runInReadTx(createTxRunnable(runnable));
    }

    public <T> T callInTx(Callable<T> callable) throws Exception {
        return (T) this.store.callInTx(createTxCallable(callable));
    }

    public <T> T callInTxNoThrow(Callable<T> callable) {
        try {
            return (T) this.store.callInTx(createTxCallable(callable));
        } catch (Exception e) {
            throw new RuntimeException("Callable threw exception", e);
        }
    }

    public <T> T callInReadTx(Callable<T> callable) {
        return (T) this.store.callInReadTx(createTxCallable(callable));
    }

    private Runnable createTxRunnable(final Runnable runnable) {
        return new Runnable() { // from class: io.objectbox.tree.-$$Lambda$Tree$4dR3Lll8DVUexnT7dPr2kW-oHfk
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$createTxRunnable$0$Tree(runnable);
            }
        };
    }

    public /* synthetic */ void lambda$createTxRunnable$0$Tree(Runnable runnable) {
        boolean zNativeSetTransaction = nativeSetTransaction(this.handle, InternalAccess.getHandle(InternalAccess.getActiveTx(this.store)));
        try {
            runnable.run();
        } finally {
            if (zNativeSetTransaction) {
                nativeClearTransaction(this.handle);
            }
        }
    }

    private <T> Callable<T> createTxCallable(final Callable<T> callable) {
        return new Callable() { // from class: io.objectbox.tree.-$$Lambda$Tree$F4Htj9ZwaDEOmsOdY-zWGzvVr1U
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$createTxCallable$1$Tree(callable);
            }
        };
    }

    public /* synthetic */ Object lambda$createTxCallable$1$Tree(Callable callable) throws Exception {
        boolean zNativeSetTransaction = nativeSetTransaction(this.handle, InternalAccess.getHandle(InternalAccess.getActiveTx(this.store)));
        try {
            return callable.call();
        } finally {
            if (zNativeSetTransaction) {
                nativeClearTransaction(this.handle);
            }
        }
    }

    @Nullable
    public Leaf getLeaf(long j) {
        LeafNode leafNodeNativeGetLeafById = nativeGetLeafById(this.handle, j);
        if (leafNodeNativeGetLeafById == null) {
            return null;
        }
        return new Leaf(leafNodeNativeGetLeafById);
    }

    @Nullable
    public String getString(long j) {
        Leaf leaf = getLeaf(j);
        if (leaf != null) {
            return leaf.asString();
        }
        return null;
    }

    @Nullable
    public Long getInteger(long j) {
        Leaf leaf = getLeaf(j);
        if (leaf != null) {
            return leaf.asInt();
        }
        return null;
    }

    @Nullable
    public Double getDouble(long j) {
        Leaf leaf = getLeaf(j);
        if (leaf != null) {
            return leaf.asDouble();
        }
        return null;
    }

    public long putMetaBranch(long j, long j2, String str) {
        return nativePutMetaBranch(this.handle, j, j2, str, null);
    }

    public long putMetaBranch(long j, long j2, String str, @Nullable String str2) {
        return nativePutMetaBranch(this.handle, j, j2, str, str2);
    }

    public long[] putMetaBranches(String[] strArr) {
        return nativePutMetaBranches(this.handle, 0L, strArr);
    }

    public long[] putMetaBranches(long j, String[] strArr) {
        return nativePutMetaBranches(this.handle, j, strArr);
    }

    public long putMetaLeaf(long j, long j2, String str, short s) {
        return nativePutMetaLeaf(this.handle, j, j2, str, s, false, null);
    }

    public long putMetaLeaf(long j, long j2, String str, short s, boolean z) {
        return nativePutMetaLeaf(this.handle, j, j2, str, s, z, null);
    }

    public long putMetaLeaf(long j, long j2, String str, short s, boolean z, @Nullable String str2) {
        return nativePutMetaLeaf(this.handle, j, j2, str, s, z, str2);
    }

    public long putBranch(long j, long j2, long j3, @Nullable String str) {
        return nativePutBranch(this.handle, j, j2, j3, str);
    }

    public long putBranch(long j, long j2, @Nullable String str) {
        return nativePutBranch(this.handle, 0L, j, j2, str);
    }

    public long putBranch(long j, long j2) {
        return nativePutBranch(this.handle, 0L, j, j2, null);
    }

    public long putValue(long j, long j2, long j3, long j4) {
        return nativePutValueInteger(this.handle, j, j2, j3, j4);
    }

    public long putValue(long j, long j2, long j3) {
        return nativePutValueInteger(this.handle, 0L, j, j2, j3);
    }

    public long putValue(long j, long j2, double d) {
        return nativePutValueFP(this.handle, 0L, j, j2, d);
    }

    public long putValue(long j, long j2, long j3, double d) {
        return nativePutValueFP(this.handle, j, j2, j3, d);
    }

    public long putValue(long j, long j2, long j3, String str) {
        return nativePutValueString(this.handle, j, j2, j3, str);
    }

    public long putValue(long j, long j2, String str) {
        return nativePutValueString(this.handle, 0L, j, j2, str);
    }

    public long put(Leaf leaf) {
        long id = leaf.getId();
        long parentBranchId = leaf.getParentBranchId();
        long metaId = leaf.getMetaId();
        short valueType = leaf.getValueType();
        if (valueType != 23) {
            switch (valueType) {
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    return nativePutValueInteger(this.handle, id, parentBranchId, metaId, leaf.getInt());
                case 7:
                case 8:
                    return nativePutValueFP(this.handle, id, parentBranchId, metaId, leaf.getDouble());
                case 9:
                    break;
                default:
                    throw new UnsupportedOperationException("Unsupported value type: " + ((int) leaf.getValueType()));
            }
        }
        return nativePutValueString(this.handle, id, parentBranchId, metaId, leaf.getString());
    }
}
