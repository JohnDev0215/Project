package com.amazon.bolthttp.internal;

import com.amazon.bolthttp.BoltConfig;
import com.amazon.bolthttp.internal.Commander.SharedState;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public abstract class Commander<T, S extends SharedState<T>> {
    private final S mSharedState;

    /* JADX INFO: Access modifiers changed from: package-private */
    public interface SharedState<T> {
        @Nonnull
        BoltConfig getConfig();

        @Nonnull
        Dispatcher getDispatcher();

        @Nonnull
        Logger getLogger();
    }

    abstract void handleCommand(@Nonnull Command<T, S> command);

    Commander(@Nonnull S s) {
        this.mSharedState = (S) Preconditions.checkNotNull(s, "sharedState");
    }

    @Nonnull
    S getSharedState() {
        return this.mSharedState;
    }
}
