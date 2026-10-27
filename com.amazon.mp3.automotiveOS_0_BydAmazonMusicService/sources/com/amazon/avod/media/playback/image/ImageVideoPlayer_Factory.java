package com.amazon.avod.media.playback.image;

import com.amazon.avod.media.framework.MediaSystemSharedContext;
import dagger.internal.Factory;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class ImageVideoPlayer_Factory implements Factory<ImageVideoPlayer> {
    private final Provider<MediaSystemSharedContext> sharedContextProvider;

    public ImageVideoPlayer_Factory(Provider<MediaSystemSharedContext> provider) {
        this.sharedContextProvider = provider;
    }

    @Override // javax.inject.Provider
    public ImageVideoPlayer get() {
        return new ImageVideoPlayer(this.sharedContextProvider.get());
    }

    public static Factory<ImageVideoPlayer> create(Provider<MediaSystemSharedContext> provider) {
        return new ImageVideoPlayer_Factory(provider);
    }
}
