package com.facebook.common.references;

import android.graphics.Bitmap;
import com.facebook.common.internal.Closeables;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import java.io.Closeable;
import java.io.IOException;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: loaded from: classes5.dex */
public abstract class CloseableReference<T> implements Cloneable, Closeable {

    @GuardedBy("this")
    protected boolean mIsClosed = false;
    protected final LeakHandler mLeakHandler;
    protected final SharedReference<T> mSharedReference;

    @Nullable
    protected final Throwable mStacktrace;
    private static Class<CloseableReference> TAG = CloseableReference.class;
    private static int sBitmapCloseableRefType = 0;
    private static final ResourceReleaser<Closeable> DEFAULT_CLOSEABLE_RELEASER = new ResourceReleaser<Closeable>() { // from class: com.facebook.common.references.CloseableReference.1
        @Override // com.facebook.common.references.ResourceReleaser
        public void release(Closeable value) {
            try {
                Closeables.close(value, true);
            } catch (IOException unused) {
            }
        }
    };
    private static final LeakHandler DEFAULT_LEAK_HANDLER = new LeakHandler() { // from class: com.facebook.common.references.CloseableReference.2
        @Override // com.facebook.common.references.CloseableReference.LeakHandler
        public boolean requiresStacktrace() {
            return false;
        }

        @Override // com.facebook.common.references.CloseableReference.LeakHandler
        public void reportLeak(SharedReference<Object> reference, @Nullable Throwable stacktrace) {
            Object obj = reference.get();
            Class cls = CloseableReference.TAG;
            Object[] objArr = new Object[3];
            objArr[0] = Integer.valueOf(System.identityHashCode(this));
            objArr[1] = Integer.valueOf(System.identityHashCode(reference));
            objArr[2] = obj == null ? null : obj.getClass().getName();
            FLog.w((Class<?>) cls, "Finalized without closing: %x %x (type = %s)", objArr);
        }
    };

    public interface LeakHandler {
        void reportLeak(SharedReference<Object> reference, @Nullable Throwable stacktrace);

        boolean requiresStacktrace();
    }

    @Override // 
    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public abstract CloseableReference<T> mo2657clone();

    public static boolean useGc() {
        return sBitmapCloseableRefType == 3;
    }

    public static void setDisableCloseableReferencesForBitmaps(int bitmapCloseableRefType) {
        sBitmapCloseableRefType = bitmapCloseableRefType;
    }

    protected CloseableReference(SharedReference<T> sharedReference, LeakHandler leakHandler, @Nullable Throwable stacktrace) {
        this.mSharedReference = (SharedReference) Preconditions.checkNotNull(sharedReference);
        sharedReference.addReference();
        this.mLeakHandler = leakHandler;
        this.mStacktrace = stacktrace;
    }

    protected CloseableReference(T t, ResourceReleaser<T> resourceReleaser, LeakHandler leakHandler, @Nullable Throwable stacktrace) {
        this.mSharedReference = new SharedReference<>(t, resourceReleaser);
        this.mLeakHandler = leakHandler;
        this.mStacktrace = stacktrace;
    }

    /* JADX WARN: Incorrect types in method signature: <T::Ljava/io/Closeable;>(TT;)Lcom/facebook/common/references/CloseableReference<TT;>; */
    public static CloseableReference of(Closeable t) {
        return of(t, DEFAULT_CLOSEABLE_RELEASER);
    }

    public static <T> CloseableReference<T> of(T t, ResourceReleaser<T> resourceReleaser) {
        return of(t, resourceReleaser, DEFAULT_LEAK_HANDLER);
    }

    /* JADX WARN: Incorrect types in method signature: <T::Ljava/io/Closeable;>(TT;Lcom/facebook/common/references/CloseableReference$LeakHandler;)Lcom/facebook/common/references/CloseableReference<TT;>; */
    public static CloseableReference of(Closeable t, LeakHandler leakHandler) {
        if (t == null) {
            return null;
        }
        return of(t, DEFAULT_CLOSEABLE_RELEASER, leakHandler, leakHandler.requiresStacktrace() ? new Throwable() : null);
    }

    public static <T> CloseableReference<T> of(T t, ResourceReleaser<T> resourceReleaser, LeakHandler leakHandler) {
        if (t == null) {
            return null;
        }
        return of(t, resourceReleaser, leakHandler, leakHandler.requiresStacktrace() ? new Throwable() : null);
    }

    public static <T> CloseableReference<T> of(T t, ResourceReleaser<T> resourceReleaser, LeakHandler leakHandler, @Nullable Throwable stacktrace) {
        if (t == null) {
            return null;
        }
        if ((t instanceof Bitmap) || (t instanceof HasBitmap)) {
            int i = sBitmapCloseableRefType;
            if (i == 1) {
                return new FinalizerCloseableReference(t, resourceReleaser, leakHandler, stacktrace);
            }
            if (i == 2) {
                return new RefCountCloseableReference(t, resourceReleaser, leakHandler, stacktrace);
            }
            if (i == 3) {
                return new NoOpCloseableReference(t, resourceReleaser, leakHandler, stacktrace);
            }
        }
        return new DefaultCloseableReference(t, resourceReleaser, leakHandler, stacktrace);
    }

    public synchronized T get() {
        Preconditions.checkState(!this.mIsClosed);
        return (T) Preconditions.checkNotNull(this.mSharedReference.get());
    }

    @Nullable
    public synchronized CloseableReference<T> cloneOrNull() {
        if (!isValid()) {
            return null;
        }
        return mo2657clone();
    }

    public synchronized boolean isValid() {
        return !this.mIsClosed;
    }

    public int getValueHash() {
        if (isValid()) {
            return System.identityHashCode(this.mSharedReference.get());
        }
        return 0;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        synchronized (this) {
            if (this.mIsClosed) {
                return;
            }
            this.mIsClosed = true;
            this.mSharedReference.deleteReference();
        }
    }

    public static boolean isValid(@Nullable CloseableReference<?> ref) {
        return ref != null && ref.isValid();
    }

    @Nullable
    public static <T> CloseableReference<T> cloneOrNull(@Nullable CloseableReference<T> ref) {
        if (ref != null) {
            return ref.cloneOrNull();
        }
        return null;
    }

    public static void closeSafely(@Nullable CloseableReference<?> ref) {
        if (ref != null) {
            ref.close();
        }
    }

    protected void finalize() throws Throwable {
        try {
            synchronized (this) {
                if (this.mIsClosed) {
                    return;
                }
                this.mLeakHandler.reportLeak(this.mSharedReference, this.mStacktrace);
                close();
            }
        } finally {
            super.finalize();
        }
    }
}
