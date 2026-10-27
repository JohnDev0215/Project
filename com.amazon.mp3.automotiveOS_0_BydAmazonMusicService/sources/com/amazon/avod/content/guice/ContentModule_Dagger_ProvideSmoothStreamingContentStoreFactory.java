package com.amazon.avod.content.guice;

import com.amazon.avod.content.smoothstream.storage.CompositeSmoothStreamingContentStore;
import com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class ContentModule_Dagger_ProvideSmoothStreamingContentStoreFactory implements Factory<SmoothStreamingContentStore> {
    private final ContentModule_Dagger module;
    private final Provider<CompositeSmoothStreamingContentStore> storeProvider;

    public ContentModule_Dagger_ProvideSmoothStreamingContentStoreFactory(ContentModule_Dagger contentModule_Dagger, Provider<CompositeSmoothStreamingContentStore> provider) {
        this.module = contentModule_Dagger;
        this.storeProvider = provider;
    }

    @Override // javax.inject.Provider
    public SmoothStreamingContentStore get() {
        return (SmoothStreamingContentStore) Preconditions.checkNotNull(this.module.provideSmoothStreamingContentStore(this.storeProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<SmoothStreamingContentStore> create(ContentModule_Dagger contentModule_Dagger, Provider<CompositeSmoothStreamingContentStore> provider) {
        return new ContentModule_Dagger_ProvideSmoothStreamingContentStoreFactory(contentModule_Dagger, provider);
    }
}
