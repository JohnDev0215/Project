package com.amazon.bolthttp.internal;

import com.amazon.bolthttp.BoltConfig;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public final class CPUSharedState extends BaseSharedState<Affinity$CPUAffinity> {
    @Override // com.amazon.bolthttp.internal.BaseSharedState, com.amazon.bolthttp.internal.Commander.SharedState
    @Nonnull
    public /* bridge */ /* synthetic */ BoltConfig getConfig() {
        return super.getConfig();
    }

    @Override // com.amazon.bolthttp.internal.BaseSharedState, com.amazon.bolthttp.internal.Commander.SharedState
    @Nonnull
    public /* bridge */ /* synthetic */ Dispatcher getDispatcher() {
        return super.getDispatcher();
    }

    @Override // com.amazon.bolthttp.internal.BaseSharedState, com.amazon.bolthttp.internal.Commander.SharedState
    @Nonnull
    public /* bridge */ /* synthetic */ Logger getLogger() {
        return super.getLogger();
    }

    CPUSharedState(@Nonnull Dispatcher dispatcher, @Nonnull BoltConfig boltConfig) {
        super(dispatcher, boltConfig);
    }
}
