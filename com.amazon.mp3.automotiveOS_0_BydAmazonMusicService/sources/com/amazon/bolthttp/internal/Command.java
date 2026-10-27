package com.amazon.bolthttp.internal;

import com.amazon.bolthttp.internal.Commander;
import com.amazon.bolthttp.internal.Commander.SharedState;
import com.google.common.base.Preconditions;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public abstract class Command<T, S extends Commander.SharedState<T>> {
    private final Class<T> mAffinityClass;
    private Dispatcher mDispatcher;
    private final AtomicBoolean mIsExecuted = new AtomicBoolean(false);
    private Logger mLogger;
    private S mSharedState;

    protected abstract void execute();

    public abstract String getTraceName();

    protected Command(@Nonnull Class<T> cls) {
        this.mAffinityClass = (Class) Preconditions.checkNotNull(cls, "affinityClass");
    }

    @Nonnull
    Class<T> getAffinityClass() {
        return this.mAffinityClass;
    }

    final void execute(@Nonnull S s) {
        this.mSharedState = (S) Preconditions.checkNotNull(s, "sharedState");
        if (!this.mIsExecuted.compareAndSet(false, true)) {
            throw new IllegalStateException("command already executed: " + getClass().getName());
        }
        this.mDispatcher = s.getDispatcher();
        this.mLogger = s.getLogger();
        execute();
    }

    @Nonnull
    protected S getSharedState() {
        return this.mSharedState;
    }

    @Nonnull
    protected Logger getLogger() {
        return this.mLogger;
    }

    protected void dispatch(@Nonnull Command<?, ?> command) {
        this.mDispatcher.dispatch(command);
    }
}
