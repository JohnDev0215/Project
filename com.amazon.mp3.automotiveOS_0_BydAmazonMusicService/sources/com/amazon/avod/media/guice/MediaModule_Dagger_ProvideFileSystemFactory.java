package com.amazon.avod.media.guice;

import com.amazon.avod.media.framework.platform.AndroidPlatform;
import com.amazon.avod.media.framework.platform.FileSystem;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class MediaModule_Dagger_ProvideFileSystemFactory implements Factory<FileSystem> {
    private final MediaModule_Dagger module;
    private final Provider<AndroidPlatform> platformProvider;

    public MediaModule_Dagger_ProvideFileSystemFactory(MediaModule_Dagger mediaModule_Dagger, Provider<AndroidPlatform> provider) {
        this.module = mediaModule_Dagger;
        this.platformProvider = provider;
    }

    @Override // javax.inject.Provider
    public FileSystem get() {
        return (FileSystem) Preconditions.checkNotNull(this.module.provideFileSystem(this.platformProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<FileSystem> create(MediaModule_Dagger mediaModule_Dagger, Provider<AndroidPlatform> provider) {
        return new MediaModule_Dagger_ProvideFileSystemFactory(mediaModule_Dagger, provider);
    }

    public static FileSystem proxyProvideFileSystem(MediaModule_Dagger mediaModule_Dagger, AndroidPlatform androidPlatform) {
        return mediaModule_Dagger.provideFileSystem(androidPlatform);
    }
}
