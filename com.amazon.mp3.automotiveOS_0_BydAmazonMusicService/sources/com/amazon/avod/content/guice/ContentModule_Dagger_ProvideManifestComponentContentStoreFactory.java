package com.amazon.avod.content.guice;

import com.amazon.avod.content.smoothstream.storage.DynamicManifestContentStore;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public final class ContentModule_Dagger_ProvideManifestComponentContentStoreFactory implements Factory<DynamicManifestContentStore> {
    private final ContentModule_Dagger module;

    public ContentModule_Dagger_ProvideManifestComponentContentStoreFactory(ContentModule_Dagger contentModule_Dagger) {
        this.module = contentModule_Dagger;
    }

    @Override // javax.inject.Provider
    public DynamicManifestContentStore get() {
        return (DynamicManifestContentStore) Preconditions.checkNotNull(this.module.provideManifestComponentContentStore(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<DynamicManifestContentStore> create(ContentModule_Dagger contentModule_Dagger) {
        return new ContentModule_Dagger_ProvideManifestComponentContentStoreFactory(contentModule_Dagger);
    }
}
