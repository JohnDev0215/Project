package com.amazon.mp3.find.dagger;

import com.amazon.music.find.providers.CacheImageProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class FindModule_ProvideCacheImageProviderFactory implements Factory<CacheImageProvider> {
    public static CacheImageProvider provideCacheImageProvider(FindModule instance) {
        return (CacheImageProvider) Preconditions.checkNotNullFromProvides(instance.provideCacheImageProvider());
    }
}
