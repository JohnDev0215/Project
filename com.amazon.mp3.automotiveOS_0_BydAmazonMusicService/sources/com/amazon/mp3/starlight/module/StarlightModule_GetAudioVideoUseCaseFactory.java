package com.amazon.mp3.starlight.module;

import android.content.Context;
import com.amazon.mp3.playback.usecase.AudioVideoToggleUseCase;
import com.amazon.mp3.prime.requester.HybridQueueTrackRequester;
import com.amazon.mp3.starlight.provider.HybridQueueStateProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class StarlightModule_GetAudioVideoUseCaseFactory implements Factory<AudioVideoToggleUseCase> {
    private final Provider<Context> contextProvider;
    private final Provider<HybridQueueStateProvider> hybridQueueStateProvider;
    private final Provider<HybridQueueTrackRequester> hybridQueueTrackRequesterProvider;
    private final StarlightModule module;

    public StarlightModule_GetAudioVideoUseCaseFactory(StarlightModule module, Provider<Context> contextProvider, Provider<HybridQueueStateProvider> hybridQueueStateProvider, Provider<HybridQueueTrackRequester> hybridQueueTrackRequesterProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
        this.hybridQueueStateProvider = hybridQueueStateProvider;
        this.hybridQueueTrackRequesterProvider = hybridQueueTrackRequesterProvider;
    }

    @Override // javax.inject.Provider
    public AudioVideoToggleUseCase get() {
        return getAudioVideoUseCase(this.module, this.contextProvider.get(), this.hybridQueueStateProvider.get(), this.hybridQueueTrackRequesterProvider.get());
    }

    public static StarlightModule_GetAudioVideoUseCaseFactory create(StarlightModule module, Provider<Context> contextProvider, Provider<HybridQueueStateProvider> hybridQueueStateProvider, Provider<HybridQueueTrackRequester> hybridQueueTrackRequesterProvider) {
        return new StarlightModule_GetAudioVideoUseCaseFactory(module, contextProvider, hybridQueueStateProvider, hybridQueueTrackRequesterProvider);
    }

    public static AudioVideoToggleUseCase getAudioVideoUseCase(StarlightModule instance, Context context, HybridQueueStateProvider hybridQueueStateProvider, HybridQueueTrackRequester hybridQueueTrackRequester) {
        return (AudioVideoToggleUseCase) Preconditions.checkNotNullFromProvides(instance.getAudioVideoUseCase(context, hybridQueueStateProvider, hybridQueueTrackRequester));
    }
}
