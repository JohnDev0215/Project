package com.amazon.avod.media.ads.internal;

import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.playback.QOSCommunicationService;
import dagger.internal.Factory;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class AndroidVideoPlayerV2Cache_Factory implements Factory<AndroidVideoPlayerV2Cache> {
    private final Provider<QOSCommunicationService> qosCommunicationServiceProvider;
    private final Provider<MediaSystemSharedContext> sharedContextProvider;

    public AndroidVideoPlayerV2Cache_Factory(Provider<MediaSystemSharedContext> provider, Provider<QOSCommunicationService> provider2) {
        this.sharedContextProvider = provider;
        this.qosCommunicationServiceProvider = provider2;
    }

    @Override // javax.inject.Provider
    public AndroidVideoPlayerV2Cache get() {
        return new AndroidVideoPlayerV2Cache(this.sharedContextProvider.get(), this.qosCommunicationServiceProvider.get());
    }

    public static Factory<AndroidVideoPlayerV2Cache> create(Provider<MediaSystemSharedContext> provider, Provider<QOSCommunicationService> provider2) {
        return new AndroidVideoPlayerV2Cache_Factory(provider, provider2);
    }
}
