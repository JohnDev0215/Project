package com.amazon.bolthttp.internal;

import com.amazon.bolthttp.BoltConfig;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class OverlordSharedState extends BaseSharedState<Affinity$OverlordAffinity> {
    private final ResponseHandlerMultimap mResponseHandlerMultimap;

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

    OverlordSharedState(@Nonnull Dispatcher dispatcher, @Nonnull BoltConfig boltConfig) {
        super(dispatcher, boltConfig);
        this.mResponseHandlerMultimap = new ResponseHandlerMultimap();
    }

    @Nonnull
    public ResponseHandlerMultimap getResponseHandlerMultimap() {
        return this.mResponseHandlerMultimap;
    }
}
