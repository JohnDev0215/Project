package com.amazon.avod.content.guice;

import android.content.Context;
import com.amazon.avod.content.smoothstream.storage.FileBackedContentStore;
import com.amazon.avod.media.framework.platform.FileSystem;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.concurrent.ExecutorService;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class ContentModule_Dagger_ProvideFileBackedComponentContentStoreFactory implements Factory<FileBackedContentStore> {
    private final Provider<Context> contextProvider;
    private final Provider<ExecutorService> executorProvider;
    private final Provider<FileSystem> fileSystemProvider;
    private final ContentModule_Dagger module;

    public ContentModule_Dagger_ProvideFileBackedComponentContentStoreFactory(ContentModule_Dagger contentModule_Dagger, Provider<Context> provider, Provider<FileSystem> provider2, Provider<ExecutorService> provider3) {
        this.module = contentModule_Dagger;
        this.contextProvider = provider;
        this.fileSystemProvider = provider2;
        this.executorProvider = provider3;
    }

    @Override // javax.inject.Provider
    public FileBackedContentStore get() {
        return (FileBackedContentStore) Preconditions.checkNotNull(this.module.provideFileBackedComponentContentStore(this.contextProvider.get(), this.fileSystemProvider.get(), this.executorProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<FileBackedContentStore> create(ContentModule_Dagger contentModule_Dagger, Provider<Context> provider, Provider<FileSystem> provider2, Provider<ExecutorService> provider3) {
        return new ContentModule_Dagger_ProvideFileBackedComponentContentStoreFactory(contentModule_Dagger, provider, provider2, provider3);
    }
}
