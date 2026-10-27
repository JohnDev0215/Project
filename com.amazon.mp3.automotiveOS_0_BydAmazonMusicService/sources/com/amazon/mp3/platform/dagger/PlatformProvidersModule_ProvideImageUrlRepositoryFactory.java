package com.amazon.mp3.platform.dagger;

import com.amazon.music.platform.data.image.ImageUrlRepository;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes3.dex */
public final class PlatformProvidersModule_ProvideImageUrlRepositoryFactory implements Factory<ImageUrlRepository> {
    public static ImageUrlRepository provideImageUrlRepository(PlatformProvidersModule instance) {
        return (ImageUrlRepository) Preconditions.checkNotNullFromProvides(instance.provideImageUrlRepository());
    }
}
