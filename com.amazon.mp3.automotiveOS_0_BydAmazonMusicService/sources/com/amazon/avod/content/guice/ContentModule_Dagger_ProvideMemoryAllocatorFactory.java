package com.amazon.avod.content.guice;

import com.amazon.avod.content.smoothstream.storage.MemoryAllocator;
import com.amazon.avod.media.framework.libraries.PlaybackNativeLibrariesLoader;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class ContentModule_Dagger_ProvideMemoryAllocatorFactory implements Factory<MemoryAllocator> {
    private final ContentModule_Dagger module;
    private final Provider<PlaybackNativeLibrariesLoader> nativeLibrariesLoaderProvider;

    public ContentModule_Dagger_ProvideMemoryAllocatorFactory(ContentModule_Dagger contentModule_Dagger, Provider<PlaybackNativeLibrariesLoader> provider) {
        this.module = contentModule_Dagger;
        this.nativeLibrariesLoaderProvider = provider;
    }

    @Override // javax.inject.Provider
    public MemoryAllocator get() {
        return (MemoryAllocator) Preconditions.checkNotNull(this.module.provideMemoryAllocator(this.nativeLibrariesLoaderProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<MemoryAllocator> create(ContentModule_Dagger contentModule_Dagger, Provider<PlaybackNativeLibrariesLoader> provider) {
        return new ContentModule_Dagger_ProvideMemoryAllocatorFactory(contentModule_Dagger, provider);
    }
}
