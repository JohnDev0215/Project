package com.amazon.avod.media.events.dagger;

import com.amazon.avod.media.events.BatchDispatcherListener;
import com.amazon.avod.media.events.MediaEventProducer;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Set;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class MediaEventModule_Dagger_ProvideBatchDispatcherListenerFactory implements Factory<BatchDispatcherListener> {
    private final MediaEventModule_Dagger module;
    private final Provider<Set<MediaEventProducer>> producersProvider;

    public MediaEventModule_Dagger_ProvideBatchDispatcherListenerFactory(MediaEventModule_Dagger mediaEventModule_Dagger, Provider<Set<MediaEventProducer>> provider) {
        this.module = mediaEventModule_Dagger;
        this.producersProvider = provider;
    }

    @Override // javax.inject.Provider
    public BatchDispatcherListener get() {
        return (BatchDispatcherListener) Preconditions.checkNotNull(this.module.provideBatchDispatcherListener(this.producersProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<BatchDispatcherListener> create(MediaEventModule_Dagger mediaEventModule_Dagger, Provider<Set<MediaEventProducer>> provider) {
        return new MediaEventModule_Dagger_ProvideBatchDispatcherListenerFactory(mediaEventModule_Dagger, provider);
    }
}
