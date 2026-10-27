package com.amazon.avod.media.contentcache;

import com.amazon.avod.media.contentcache.internal.PrioritizingVideoCacheManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class ContentCachingModule_Dagger_ProvideCacheManagerFactory implements Factory<VideoCacheManager> {
    private final Provider<PrioritizingVideoCacheManager> managerProvider;
    private final ContentCachingModule_Dagger module;

    public ContentCachingModule_Dagger_ProvideCacheManagerFactory(ContentCachingModule_Dagger contentCachingModule_Dagger, Provider<PrioritizingVideoCacheManager> provider) {
        this.module = contentCachingModule_Dagger;
        this.managerProvider = provider;
    }

    @Override // javax.inject.Provider
    public VideoCacheManager get() {
        return (VideoCacheManager) Preconditions.checkNotNull(this.module.provideCacheManager(this.managerProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<VideoCacheManager> create(ContentCachingModule_Dagger contentCachingModule_Dagger, Provider<PrioritizingVideoCacheManager> provider) {
        return new ContentCachingModule_Dagger_ProvideCacheManagerFactory(contentCachingModule_Dagger, provider);
    }
}
