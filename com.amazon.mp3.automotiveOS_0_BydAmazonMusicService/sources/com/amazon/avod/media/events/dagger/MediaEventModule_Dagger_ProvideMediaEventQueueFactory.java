package com.amazon.avod.media.events.dagger;

import com.amazon.avod.media.events.MediaEventQueue;
import com.amazon.avod.media.events.dao.MediaEventDAO;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class MediaEventModule_Dagger_ProvideMediaEventQueueFactory implements Factory<MediaEventQueue> {
    private final Provider<MediaEventDAO> mediaEventDAOProvider;
    private final MediaEventModule_Dagger module;

    public MediaEventModule_Dagger_ProvideMediaEventQueueFactory(MediaEventModule_Dagger mediaEventModule_Dagger, Provider<MediaEventDAO> provider) {
        this.module = mediaEventModule_Dagger;
        this.mediaEventDAOProvider = provider;
    }

    @Override // javax.inject.Provider
    public MediaEventQueue get() {
        return (MediaEventQueue) Preconditions.checkNotNull(this.module.provideMediaEventQueue(this.mediaEventDAOProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<MediaEventQueue> create(MediaEventModule_Dagger mediaEventModule_Dagger, Provider<MediaEventDAO> provider) {
        return new MediaEventModule_Dagger_ProvideMediaEventQueueFactory(mediaEventModule_Dagger, provider);
    }
}
