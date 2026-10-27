package com.amazon.avod.content.guice;

import com.amazon.avod.content.smoothstream.storage.FileBackedContentStore;
import com.amazon.avod.content.smoothstream.storage.MemoryAllocator;
import com.amazon.avod.content.smoothstream.storage.MemoryBackedContentStore;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class ContentModule_Dagger_ProvideMemoryBackedComponentContentStoreFactory implements Factory<MemoryBackedContentStore> {
    private final Provider<FileBackedContentStore> fileBackedStoreProvider;
    private final Provider<MemoryAllocator> memoryAllocatorProvider;
    private final ContentModule_Dagger module;

    public ContentModule_Dagger_ProvideMemoryBackedComponentContentStoreFactory(ContentModule_Dagger contentModule_Dagger, Provider<MemoryAllocator> provider, Provider<FileBackedContentStore> provider2) {
        this.module = contentModule_Dagger;
        this.memoryAllocatorProvider = provider;
        this.fileBackedStoreProvider = provider2;
    }

    @Override // javax.inject.Provider
    public MemoryBackedContentStore get() {
        return (MemoryBackedContentStore) Preconditions.checkNotNull(this.module.provideMemoryBackedComponentContentStore(this.memoryAllocatorProvider.get(), this.fileBackedStoreProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<MemoryBackedContentStore> create(ContentModule_Dagger contentModule_Dagger, Provider<MemoryAllocator> provider, Provider<FileBackedContentStore> provider2) {
        return new ContentModule_Dagger_ProvideMemoryBackedComponentContentStoreFactory(contentModule_Dagger, provider, provider2);
    }
}
