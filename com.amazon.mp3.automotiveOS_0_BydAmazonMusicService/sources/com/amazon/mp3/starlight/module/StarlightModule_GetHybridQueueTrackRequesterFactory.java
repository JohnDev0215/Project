package com.amazon.mp3.starlight.module;

import android.content.Context;
import com.amazon.mp3.prime.requester.HybridQueueTrackRequester;
import com.amazon.mp3.starlight.provider.HybridQueueStateProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class StarlightModule_GetHybridQueueTrackRequesterFactory implements Factory<HybridQueueTrackRequester> {
    private final Provider<Context> contextProvider;
    private final Provider<HybridQueueStateProvider> hybridQueueStateProvider;
    private final StarlightModule module;

    public StarlightModule_GetHybridQueueTrackRequesterFactory(StarlightModule module, Provider<Context> contextProvider, Provider<HybridQueueStateProvider> hybridQueueStateProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
        this.hybridQueueStateProvider = hybridQueueStateProvider;
    }

    @Override // javax.inject.Provider
    public HybridQueueTrackRequester get() {
        return getHybridQueueTrackRequester(this.module, this.contextProvider.get(), this.hybridQueueStateProvider.get());
    }

    public static StarlightModule_GetHybridQueueTrackRequesterFactory create(StarlightModule module, Provider<Context> contextProvider, Provider<HybridQueueStateProvider> hybridQueueStateProvider) {
        return new StarlightModule_GetHybridQueueTrackRequesterFactory(module, contextProvider, hybridQueueStateProvider);
    }

    public static HybridQueueTrackRequester getHybridQueueTrackRequester(StarlightModule instance, Context context, HybridQueueStateProvider hybridQueueStateProvider) {
        return (HybridQueueTrackRequester) Preconditions.checkNotNullFromProvides(instance.getHybridQueueTrackRequester(context, hybridQueueStateProvider));
    }
}
