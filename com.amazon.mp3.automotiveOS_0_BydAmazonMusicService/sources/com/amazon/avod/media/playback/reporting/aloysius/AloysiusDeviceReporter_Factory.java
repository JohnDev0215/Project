package com.amazon.avod.media.playback.reporting.aloysius;

import android.content.Context;
import com.amazon.avod.media.events.MediaEventQueue;
import com.amazon.avod.media.playback.support.RendererSchemeController;
import com.amazon.avod.playback.capability.DeviceIdentity;
import com.amazon.avod.purchase.AssociateTagProviderProxy;
import dagger.internal.Factory;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class AloysiusDeviceReporter_Factory implements Factory<AloysiusDeviceReporter> {
    private final Provider<AssociateTagProviderProxy> associateTagProviderProxyProvider;
    private final Provider<Context> contextProvider;
    private final Provider<DeviceIdentity> deviceIdentityProvider;
    private final Provider<MediaEventQueue> mediaEventQueueProvider;
    private final Provider<String> playerNameAndPlayerSdkVersionProvider;
    private final Provider<RendererSchemeController> rendererSchemeControllerProvider;

    public AloysiusDeviceReporter_Factory(Provider<MediaEventQueue> provider, Provider<DeviceIdentity> provider2, Provider<String> provider3, Provider<RendererSchemeController> provider4, Provider<Context> provider5, Provider<AssociateTagProviderProxy> provider6) {
        this.mediaEventQueueProvider = provider;
        this.deviceIdentityProvider = provider2;
        this.playerNameAndPlayerSdkVersionProvider = provider3;
        this.rendererSchemeControllerProvider = provider4;
        this.contextProvider = provider5;
        this.associateTagProviderProxyProvider = provider6;
    }

    @Override // javax.inject.Provider
    public AloysiusDeviceReporter get() {
        return new AloysiusDeviceReporter(this.mediaEventQueueProvider.get(), this.deviceIdentityProvider.get(), this.playerNameAndPlayerSdkVersionProvider.get(), this.playerNameAndPlayerSdkVersionProvider.get(), this.rendererSchemeControllerProvider.get(), this.contextProvider.get(), this.associateTagProviderProxyProvider.get());
    }

    public static Factory<AloysiusDeviceReporter> create(Provider<MediaEventQueue> provider, Provider<DeviceIdentity> provider2, Provider<String> provider3, Provider<RendererSchemeController> provider4, Provider<Context> provider5, Provider<AssociateTagProviderProxy> provider6) {
        return new AloysiusDeviceReporter_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }
}
