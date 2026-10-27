package com.amazon.avod.playback.session;

import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.framework.platform.power.PowerManagementLockFactory;
import dagger.internal.Factory;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class AmazonVideoPlayerFactory_Factory implements Factory<AmazonVideoPlayerFactory> {
    private final Provider<PowerManagementLockFactory> lockFactoryProvider;
    private final Provider<MediaSystemSharedContext> sharedContextProvider;

    public AmazonVideoPlayerFactory_Factory(Provider<MediaSystemSharedContext> provider, Provider<PowerManagementLockFactory> provider2) {
        this.sharedContextProvider = provider;
        this.lockFactoryProvider = provider2;
    }

    @Override // javax.inject.Provider
    public AmazonVideoPlayerFactory get() {
        return new AmazonVideoPlayerFactory(this.sharedContextProvider.get(), this.lockFactoryProvider.get());
    }

    public static Factory<AmazonVideoPlayerFactory> create(Provider<MediaSystemSharedContext> provider, Provider<PowerManagementLockFactory> provider2) {
        return new AmazonVideoPlayerFactory_Factory(provider, provider2);
    }
}
