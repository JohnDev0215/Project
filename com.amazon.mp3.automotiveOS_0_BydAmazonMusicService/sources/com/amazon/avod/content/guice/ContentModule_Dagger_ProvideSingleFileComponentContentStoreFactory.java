package com.amazon.avod.content.guice;

import com.amazon.avod.content.smoothstream.storage.FileBackedContentStore;
import com.amazon.avod.content.smoothstream.storage.singlefile.SingleFileContentStore;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class ContentModule_Dagger_ProvideSingleFileComponentContentStoreFactory implements Factory<SingleFileContentStore> {
    private final Provider<FileBackedContentStore> fileBackedContentStoreProvider;
    private final ContentModule_Dagger module;

    public ContentModule_Dagger_ProvideSingleFileComponentContentStoreFactory(ContentModule_Dagger contentModule_Dagger, Provider<FileBackedContentStore> provider) {
        this.module = contentModule_Dagger;
        this.fileBackedContentStoreProvider = provider;
    }

    @Override // javax.inject.Provider
    public SingleFileContentStore get() {
        return (SingleFileContentStore) Preconditions.checkNotNull(this.module.provideSingleFileComponentContentStore(this.fileBackedContentStoreProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<SingleFileContentStore> create(ContentModule_Dagger contentModule_Dagger, Provider<FileBackedContentStore> provider) {
        return new ContentModule_Dagger_ProvideSingleFileComponentContentStoreFactory(contentModule_Dagger, provider);
    }
}
