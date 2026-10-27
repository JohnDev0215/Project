package com.amazon.avod.content.smoothstream.storage;

import dagger.internal.Factory;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class CompositeSmoothStreamingContentStore_Factory implements Factory<CompositeSmoothStreamingContentStore> {
    private final Provider<FileBackedContentStore> fileBackedStoreProvider;
    private final Provider<DynamicManifestContentStore> manifestStoreProvider;
    private final Provider<MemoryBackedContentStore> memoryBackedStoreProvider;

    public CompositeSmoothStreamingContentStore_Factory(Provider<MemoryBackedContentStore> provider, Provider<FileBackedContentStore> provider2, Provider<DynamicManifestContentStore> provider3) {
        this.memoryBackedStoreProvider = provider;
        this.fileBackedStoreProvider = provider2;
        this.manifestStoreProvider = provider3;
    }

    @Override // javax.inject.Provider
    public CompositeSmoothStreamingContentStore get() {
        return new CompositeSmoothStreamingContentStore(this.memoryBackedStoreProvider.get(), this.fileBackedStoreProvider.get(), this.manifestStoreProvider.get());
    }

    public static Factory<CompositeSmoothStreamingContentStore> create(Provider<MemoryBackedContentStore> provider, Provider<FileBackedContentStore> provider2, Provider<DynamicManifestContentStore> provider3) {
        return new CompositeSmoothStreamingContentStore_Factory(provider, provider2, provider3);
    }
}
