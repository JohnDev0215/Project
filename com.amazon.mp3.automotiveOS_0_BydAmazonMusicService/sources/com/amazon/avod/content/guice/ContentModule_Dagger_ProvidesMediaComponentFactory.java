package com.amazon.avod.content.guice;

import com.amazon.avod.content.smoothstream.storage.FileBackedContentStore;
import com.amazon.avod.media.framework.MediaComponent;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class ContentModule_Dagger_ProvidesMediaComponentFactory implements Factory<MediaComponent> {
    private final ContentModule_Dagger module;
    private final Provider<FileBackedContentStore> storeProvider;

    public ContentModule_Dagger_ProvidesMediaComponentFactory(ContentModule_Dagger contentModule_Dagger, Provider<FileBackedContentStore> provider) {
        this.module = contentModule_Dagger;
        this.storeProvider = provider;
    }

    @Override // javax.inject.Provider
    public MediaComponent get() {
        return (MediaComponent) Preconditions.checkNotNull(this.module.providesMediaComponent(this.storeProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<MediaComponent> create(ContentModule_Dagger contentModule_Dagger, Provider<FileBackedContentStore> provider) {
        return new ContentModule_Dagger_ProvidesMediaComponentFactory(contentModule_Dagger, provider);
    }
}
