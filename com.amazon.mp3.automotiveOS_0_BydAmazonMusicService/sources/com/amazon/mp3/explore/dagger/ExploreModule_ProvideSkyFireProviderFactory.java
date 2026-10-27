package com.amazon.mp3.explore.dagger;

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
import com.amazon.music.skyfire.ui.providers.SkyFireProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class ExploreModule_ProvideSkyFireProviderFactory implements Factory<SkyFireProvider> {
    private final Provider<AuthenticationProvider> authenticationProvider;
    private final Provider<BuildInfoProvider> buildInfoProvider;
    private final Provider<CapabilitiesInfo> capabilitiesInfoProvider;
    private final Provider<Context> contextProvider;
    private final Provider<CustomerMetadataProvider> customerMetadataProvider;
    private final Provider<DeviceInfoProvider> deviceInfoProvider;
    private final ExploreModule module;
    private final Provider<NetworkInterceptor> networkInterceptorProvider;
    private final Provider<PlaybackProvider> playbackProvider;
    private final Provider<SkillLatencyTracker> skillLatencyTrackerProvider;
    private final Provider<SkyFireConfigurationProvider> skyfireConfigurationProvider;

    public ExploreModule_ProvideSkyFireProviderFactory(ExploreModule module, Provider<Context> contextProvider, Provider<AuthenticationProvider> authenticationProvider, Provider<CustomerMetadataProvider> customerMetadataProvider, Provider<DeviceInfoProvider> deviceInfoProvider, Provider<BuildInfoProvider> buildInfoProvider, Provider<PlaybackProvider> playbackProvider, Provider<CapabilitiesInfo> capabilitiesInfoProvider, Provider<NetworkInterceptor> networkInterceptorProvider, Provider<SkillLatencyTracker> skillLatencyTrackerProvider, Provider<SkyFireConfigurationProvider> skyfireConfigurationProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
        this.authenticationProvider = authenticationProvider;
        this.customerMetadataProvider = customerMetadataProvider;
        this.deviceInfoProvider = deviceInfoProvider;
        this.buildInfoProvider = buildInfoProvider;
        this.playbackProvider = playbackProvider;
        this.capabilitiesInfoProvider = capabilitiesInfoProvider;
        this.networkInterceptorProvider = networkInterceptorProvider;
        this.skillLatencyTrackerProvider = skillLatencyTrackerProvider;
        this.skyfireConfigurationProvider = skyfireConfigurationProvider;
    }

    @Override // javax.inject.Provider
    public SkyFireProvider get() {
        return provideSkyFireProvider(this.module, this.contextProvider.get(), this.authenticationProvider.get(), this.customerMetadataProvider.get(), this.deviceInfoProvider.get(), this.buildInfoProvider.get(), this.playbackProvider.get(), this.capabilitiesInfoProvider.get(), this.networkInterceptorProvider.get(), this.skillLatencyTrackerProvider.get(), this.skyfireConfigurationProvider.get());
    }

    public static ExploreModule_ProvideSkyFireProviderFactory create(ExploreModule module, Provider<Context> contextProvider, Provider<AuthenticationProvider> authenticationProvider, Provider<CustomerMetadataProvider> customerMetadataProvider, Provider<DeviceInfoProvider> deviceInfoProvider, Provider<BuildInfoProvider> buildInfoProvider, Provider<PlaybackProvider> playbackProvider, Provider<CapabilitiesInfo> capabilitiesInfoProvider, Provider<NetworkInterceptor> networkInterceptorProvider, Provider<SkillLatencyTracker> skillLatencyTrackerProvider, Provider<SkyFireConfigurationProvider> skyfireConfigurationProvider) {
        return new ExploreModule_ProvideSkyFireProviderFactory(module, contextProvider, authenticationProvider, customerMetadataProvider, deviceInfoProvider, buildInfoProvider, playbackProvider, capabilitiesInfoProvider, networkInterceptorProvider, skillLatencyTrackerProvider, skyfireConfigurationProvider);
    }

    public static SkyFireProvider provideSkyFireProvider(ExploreModule instance, Context context, AuthenticationProvider authenticationProvider, CustomerMetadataProvider customerMetadataProvider, DeviceInfoProvider deviceInfoProvider, BuildInfoProvider buildInfoProvider, PlaybackProvider playbackProvider, CapabilitiesInfo capabilitiesInfo, NetworkInterceptor networkInterceptor, SkillLatencyTracker skillLatencyTracker, SkyFireConfigurationProvider skyfireConfigurationProvider) {
        return (SkyFireProvider) Preconditions.checkNotNullFromProvides(instance.provideSkyFireProvider(context, authenticationProvider, customerMetadataProvider, deviceInfoProvider, buildInfoProvider, playbackProvider, capabilitiesInfo, networkInterceptor, skillLatencyTracker, skyfireConfigurationProvider));
    }
}
