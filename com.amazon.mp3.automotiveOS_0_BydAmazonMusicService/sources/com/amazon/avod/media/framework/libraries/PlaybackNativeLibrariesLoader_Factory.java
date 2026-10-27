package com.amazon.avod.media.framework.libraries;

import com.amazon.avod.media.framework.MediaSystemSharedContext;
import dagger.internal.Factory;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class PlaybackNativeLibrariesLoader_Factory implements Factory<PlaybackNativeLibrariesLoader> {
    private final Provider<MediaSystemSharedContext> sharedContextProvider;

    public PlaybackNativeLibrariesLoader_Factory(Provider<MediaSystemSharedContext> provider) {
        this.sharedContextProvider = provider;
    }

    @Override // javax.inject.Provider
    public PlaybackNativeLibrariesLoader get() {
        return new PlaybackNativeLibrariesLoader(this.sharedContextProvider.get());
    }

    public static Factory<PlaybackNativeLibrariesLoader> create(Provider<MediaSystemSharedContext> provider) {
        return new PlaybackNativeLibrariesLoader_Factory(provider);
    }
}
