package com.amazon.music.skyfire.ui.providers.impl;

import android.content.Context;
import com.amazon.music.platform.providers.AuthenticationProvider;
import com.amazon.music.platform.providers.BuildInfoProvider;
import com.amazon.music.platform.providers.CustomerMetadataProvider;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import com.amazon.music.skyfire.core.network.CapabilitiesInfo;
import com.amazon.music.skyfire.core.network.NetworkInterceptor;
import com.amazon.music.skyfire.core.performance.SkillLatencyTracker;
import com.amazon.music.skyfire.ui.providers.PlaybackProvider;
import com.amazon.music.skyfire.ui.providers.SkyFireConfigurationProvider;
import dagger.internal.Factory;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class DefaultSkyFireProvider_Factory implements Factory<DefaultSkyFireProvider> {
    private final Provider<AuthenticationProvider> authenticationProvider;
    private final Provider<BuildInfoProvider> buildInfoProvider;
    private final Provider<CapabilitiesInfo> capabilitiesInfoProvider;
    private final Provider<Context> contextProvider;
    private final Provider<CustomerMetadataProvider> customerMetadataProvider;
    private final Provider<DeviceInfoProvider> deviceInfoProvider;
    private final Provider<NetworkInterceptor> networkInterceptorProvider;
    private final Provider<PlaybackProvider> playbackProvider;
    private final Provider<SkillLatencyTracker> skillLatencyTrackerProvider;
    private final Provider<SkyFireConfigurationProvider> skyFireConfigurationProvider;

    public DefaultSkyFireProvider_Factory(Provider<Context> provider, Provider<AuthenticationProvider> provider2, Provider<CustomerMetadataProvider> provider3, Provider<DeviceInfoProvider> provider4, Provider<BuildInfoProvider> provider5, Provider<PlaybackProvider> provider6, Provider<CapabilitiesInfo> provider7, Provider<NetworkInterceptor> provider8, Provider<SkillLatencyTracker> provider9, Provider<SkyFireConfigurationProvider> provider10) {
        this.contextProvider = provider;
        this.authenticationProvider = provider2;
        this.customerMetadataProvider = provider3;
        this.deviceInfoProvider = provider4;
        this.buildInfoProvider = provider5;
        this.playbackProvider = provider6;
        this.capabilitiesInfoProvider = provider7;
        this.networkInterceptorProvider = provider8;
        this.skillLatencyTrackerProvider = provider9;
        this.skyFireConfigurationProvider = provider10;
    }

    @Override // javax.inject.Provider
    public DefaultSkyFireProvider get() {
        return newInstance(this.contextProvider.get(), this.authenticationProvider.get(), this.customerMetadataProvider.get(), this.deviceInfoProvider.get(), this.buildInfoProvider.get(), this.playbackProvider.get(), this.capabilitiesInfoProvider.get(), this.networkInterceptorProvider.get(), this.skillLatencyTrackerProvider.get(), this.skyFireConfigurationProvider.get());
    }

    public static DefaultSkyFireProvider_Factory create(Provider<Context> provider, Provider<AuthenticationProvider> provider2, Provider<CustomerMetadataProvider> provider3, Provider<DeviceInfoProvider> provider4, Provider<BuildInfoProvider> provider5, Provider<PlaybackProvider> provider6, Provider<CapabilitiesInfo> provider7, Provider<NetworkInterceptor> provider8, Provider<SkillLatencyTracker> provider9, Provider<SkyFireConfigurationProvider> provider10) {
        return new DefaultSkyFireProvider_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10);
    }

    public static DefaultSkyFireProvider newInstance(Context context, AuthenticationProvider authenticationProvider, CustomerMetadataProvider customerMetadataProvider, DeviceInfoProvider deviceInfoProvider, BuildInfoProvider buildInfoProvider, PlaybackProvider playbackProvider, CapabilitiesInfo capabilitiesInfo, NetworkInterceptor networkInterceptor, SkillLatencyTracker skillLatencyTracker, SkyFireConfigurationProvider skyFireConfigurationProvider) {
        return new DefaultSkyFireProvider(context, authenticationProvider, customerMetadataProvider, deviceInfoProvider, buildInfoProvider, playbackProvider, capabilitiesInfo, networkInterceptor, skillLatencyTracker, skyFireConfigurationProvider);
    }
}
