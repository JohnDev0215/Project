package com.amazon.avod.content.guice;

import com.amazon.avod.content.ContentStore;
import com.amazon.avod.content.smoothstream.storage.CompositeSmoothStreamingContentStore;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class ContentModule_Dagger_ProvideContentStoreFactory implements Factory<ContentStore> {
    private final ContentModule_Dagger module;
    private final Provider<CompositeSmoothStreamingContentStore> storeProvider;

    public ContentModule_Dagger_ProvideContentStoreFactory(ContentModule_Dagger contentModule_Dagger, Provider<CompositeSmoothStreamingContentStore> provider) {
        this.module = contentModule_Dagger;
        this.storeProvider = provider;
    }

    @Override // javax.inject.Provider
    public ContentStore get() {
        return (ContentStore) Preconditions.checkNotNull(this.module.provideContentStore(this.storeProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ContentStore> create(ContentModule_Dagger contentModule_Dagger, Provider<CompositeSmoothStreamingContentStore> provider) {
        return new ContentModule_Dagger_ProvideContentStoreFactory(contentModule_Dagger, provider);
    }

    public static ContentStore proxyProvideContentStore(ContentModule_Dagger contentModule_Dagger, CompositeSmoothStreamingContentStore compositeSmoothStreamingContentStore) {
        return contentModule_Dagger.provideContentStore(compositeSmoothStreamingContentStore);
    }
}
