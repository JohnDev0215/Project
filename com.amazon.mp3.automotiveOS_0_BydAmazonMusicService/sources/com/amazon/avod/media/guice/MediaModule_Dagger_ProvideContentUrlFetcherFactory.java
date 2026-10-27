package com.amazon.avod.media.guice;

import com.amazon.avod.content.urlvending.ContentUrlFetcher;
import com.amazon.avod.media.service.AVODContentUrlFetcher;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class MediaModule_Dagger_ProvideContentUrlFetcherFactory implements Factory<ContentUrlFetcher> {
    private final Provider<AVODContentUrlFetcher> fetcherProvider;
    private final MediaModule_Dagger module;

    public MediaModule_Dagger_ProvideContentUrlFetcherFactory(MediaModule_Dagger mediaModule_Dagger, Provider<AVODContentUrlFetcher> provider) {
        this.module = mediaModule_Dagger;
        this.fetcherProvider = provider;
    }

    @Override // javax.inject.Provider
    public ContentUrlFetcher get() {
        return (ContentUrlFetcher) Preconditions.checkNotNull(this.module.provideContentUrlFetcher(this.fetcherProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ContentUrlFetcher> create(MediaModule_Dagger mediaModule_Dagger, Provider<AVODContentUrlFetcher> provider) {
        return new MediaModule_Dagger_ProvideContentUrlFetcherFactory(mediaModule_Dagger, provider);
    }

    public static ContentUrlFetcher proxyProvideContentUrlFetcher(MediaModule_Dagger mediaModule_Dagger, AVODContentUrlFetcher aVODContentUrlFetcher) {
        return mediaModule_Dagger.provideContentUrlFetcher(aVODContentUrlFetcher);
    }
}
