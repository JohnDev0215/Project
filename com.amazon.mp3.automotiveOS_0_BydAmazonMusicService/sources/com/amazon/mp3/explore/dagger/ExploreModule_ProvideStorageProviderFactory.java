package com.amazon.mp3.explore.dagger;

import android.content.Context;
import com.amazon.music.skyfire.ui.providers.StorageProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class ExploreModule_ProvideStorageProviderFactory implements Factory<StorageProvider> {
    private final Provider<Context> contextProvider;
    private final ExploreModule module;

    public ExploreModule_ProvideStorageProviderFactory(ExploreModule module, Provider<Context> contextProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
    }

    @Override // javax.inject.Provider
    public StorageProvider get() {
        return provideStorageProvider(this.module, this.contextProvider.get());
    }

    public static ExploreModule_ProvideStorageProviderFactory create(ExploreModule module, Provider<Context> contextProvider) {
        return new ExploreModule_ProvideStorageProviderFactory(module, contextProvider);
    }

    public static StorageProvider provideStorageProvider(ExploreModule instance, Context context) {
        return (StorageProvider) Preconditions.checkNotNullFromProvides(instance.provideStorageProvider(context));
    }
}
