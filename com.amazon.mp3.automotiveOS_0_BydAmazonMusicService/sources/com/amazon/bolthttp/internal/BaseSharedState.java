package com.amazon.bolthttp.internal;

import com.amazon.bolthttp.BoltConfig;
import com.amazon.bolthttp.internal.Commander;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
abstract class BaseSharedState<T> implements Commander.SharedState<T> {
    private final BoltConfig mConfig;
    private final Dispatcher mDispatcher;
    private final Logger mLogger;

    BaseSharedState(@Nonnull Dispatcher dispatcher, @Nonnull BoltConfig boltConfig) {
        this.mDispatcher = (Dispatcher) Preconditions.checkNotNull(dispatcher, "dispatcher");
        this.mConfig = (BoltConfig) Preconditions.checkNotNull(boltConfig, "config");
        this.mLogger = new Logger(boltConfig);
    }

    @Override // com.amazon.bolthttp.internal.Commander.SharedState
    @Nonnull
    public Dispatcher getDispatcher() {
        return this.mDispatcher;
    }

    @Override // com.amazon.bolthttp.internal.Commander.SharedState
    @Nonnull
    public BoltConfig getConfig() {
        return this.mConfig;
    }

    @Override // com.amazon.bolthttp.internal.Commander.SharedState
    @Nonnull
    public Logger getLogger() {
        return this.mLogger;
    }
}
