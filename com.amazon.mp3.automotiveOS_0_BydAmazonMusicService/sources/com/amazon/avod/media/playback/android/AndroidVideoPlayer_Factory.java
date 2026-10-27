package com.amazon.avod.media.playback.android;

import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.playback.QOSCommunicationService;
import dagger.internal.Factory;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class AndroidVideoPlayer_Factory implements Factory<AndroidVideoPlayer> {
    private final Provider<QOSCommunicationService> qosCommunicationServiceProvider;
    private final Provider<MediaSystemSharedContext> sharedContextProvider;

    public AndroidVideoPlayer_Factory(Provider<MediaSystemSharedContext> provider, Provider<QOSCommunicationService> provider2) {
        this.sharedContextProvider = provider;
        this.qosCommunicationServiceProvider = provider2;
    }

    @Override // javax.inject.Provider
    public AndroidVideoPlayer get() {
        return new AndroidVideoPlayer(this.sharedContextProvider.get(), this.qosCommunicationServiceProvider.get());
    }

    public static Factory<AndroidVideoPlayer> create(Provider<MediaSystemSharedContext> provider, Provider<QOSCommunicationService> provider2) {
        return new AndroidVideoPlayer_Factory(provider, provider2);
    }
}
