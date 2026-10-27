package com.amazon.avod.playback.smoothstream;

import com.amazon.avod.content.CompositeContentSessionFactory;
import com.amazon.avod.content.ContentSessionFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class AdaptiveStreamingModule_Dagger_ProvideContentSessionFactoryFactory implements Factory<ContentSessionFactory> {
    private final Provider<CompositeContentSessionFactory> compositeContentSessionFactoryProvider;
    private final AdaptiveStreamingModule_Dagger module;

    public AdaptiveStreamingModule_Dagger_ProvideContentSessionFactoryFactory(AdaptiveStreamingModule_Dagger adaptiveStreamingModule_Dagger, Provider<CompositeContentSessionFactory> provider) {
        this.module = adaptiveStreamingModule_Dagger;
        this.compositeContentSessionFactoryProvider = provider;
    }

    @Override // javax.inject.Provider
    public ContentSessionFactory get() {
        return (ContentSessionFactory) Preconditions.checkNotNull(this.module.provideContentSessionFactory(this.compositeContentSessionFactoryProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ContentSessionFactory> create(AdaptiveStreamingModule_Dagger adaptiveStreamingModule_Dagger, Provider<CompositeContentSessionFactory> provider) {
        return new AdaptiveStreamingModule_Dagger_ProvideContentSessionFactoryFactory(adaptiveStreamingModule_Dagger, provider);
    }
}
