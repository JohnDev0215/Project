package com.amazon.music.explore.fragment;

import CoreInterface.v1_0.Template;
import com.amazon.music.explore.providers.AnimationProvider;
import com.amazon.music.explore.providers.CastingProvider;
import com.amazon.music.platform.providers.AuthenticationProvider;
import com.amazon.music.platform.providers.BuildInfoProvider;
import com.amazon.music.platform.providers.CustomerMetadataProvider;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import com.amazon.music.platform.providers.MetricsProvider;
import com.amazon.music.skyfire.ui.providers.EnvironmentProvider;
import com.amazon.music.skyfire.ui.providers.ExternalActionProvider;
import com.amazon.music.skyfire.ui.providers.PlaybackProvider;
import com.amazon.music.skyfire.ui.providers.SharingProvider;
import com.amazon.music.skyfire.ui.providers.StorageProvider;
import com.amazon.music.skyfire.ui.providers.StyleSheetProvider;
import com.amazon.music.skyfire.ui.providers.UpsellProvider;
import dagger.MembersInjector;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class BaseExploreFragment_MembersInjector<T extends Template> implements MembersInjector<BaseExploreFragment<T>> {
    private final Provider<AnimationProvider> animationProvider;
    private final Provider<AuthenticationProvider> authenticationProvider;
    private final Provider<BuildInfoProvider> buildInfoProvider;
    private final Provider<CastingProvider> castingProvider;
    private final Provider<CustomerMetadataProvider> customerMetadataProvider;
    private final Provider<DeviceInfoProvider> deviceInfoProvider;
    private final Provider<EnvironmentProvider> environmentProvider;
    private final Provider<ExternalActionProvider> externalActionProvider;
    private final Provider<MetricsProvider> metricsProvider;
    private final Provider<PlaybackProvider> playbackProvider;
    private final Provider<SharingProvider> sharingProvider;
    private final Provider<StorageProvider> storageProvider;
    private final Provider<StyleSheetProvider> styleSheetProvider;
    private final Provider<UpsellProvider> upsellProvider;

    public BaseExploreFragment_MembersInjector(Provider<AuthenticationProvider> provider, Provider<CustomerMetadataProvider> provider2, Provider<DeviceInfoProvider> provider3, Provider<BuildInfoProvider> provider4, Provider<ExternalActionProvider> provider5, Provider<StorageProvider> provider6, Provider<EnvironmentProvider> provider7, Provider<PlaybackProvider> provider8, Provider<UpsellProvider> provider9, Provider<CastingProvider> provider10, Provider<AnimationProvider> provider11, Provider<StyleSheetProvider> provider12, Provider<MetricsProvider> provider13, Provider<SharingProvider> provider14) {
        this.authenticationProvider = provider;
        this.customerMetadataProvider = provider2;
        this.deviceInfoProvider = provider3;
        this.buildInfoProvider = provider4;
        this.externalActionProvider = provider5;
        this.storageProvider = provider6;
        this.environmentProvider = provider7;
        this.playbackProvider = provider8;
        this.upsellProvider = provider9;
        this.castingProvider = provider10;
        this.animationProvider = provider11;
        this.styleSheetProvider = provider12;
        this.metricsProvider = provider13;
        this.sharingProvider = provider14;
    }

    public static <T extends Template> MembersInjector<BaseExploreFragment<T>> create(Provider<AuthenticationProvider> provider, Provider<CustomerMetadataProvider> provider2, Provider<DeviceInfoProvider> provider3, Provider<BuildInfoProvider> provider4, Provider<ExternalActionProvider> provider5, Provider<StorageProvider> provider6, Provider<EnvironmentProvider> provider7, Provider<PlaybackProvider> provider8, Provider<UpsellProvider> provider9, Provider<CastingProvider> provider10, Provider<AnimationProvider> provider11, Provider<StyleSheetProvider> provider12, Provider<MetricsProvider> provider13, Provider<SharingProvider> provider14) {
        return new BaseExploreFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14);
    }

    public void injectMembers(BaseExploreFragment<T> baseExploreFragment) {
        injectAuthenticationProvider(baseExploreFragment, this.authenticationProvider.get());
        injectCustomerMetadataProvider(baseExploreFragment, this.customerMetadataProvider.get());
        injectDeviceInfoProvider(baseExploreFragment, this.deviceInfoProvider.get());
        injectBuildInfoProvider(baseExploreFragment, this.buildInfoProvider.get());
        injectExternalActionProvider(baseExploreFragment, this.externalActionProvider.get());
        injectStorageProvider(baseExploreFragment, this.storageProvider.get());
        injectEnvironmentProvider(baseExploreFragment, this.environmentProvider.get());
        injectPlaybackProvider(baseExploreFragment, this.playbackProvider.get());
        injectUpsellProvider(baseExploreFragment, this.upsellProvider.get());
        injectCastingProvider(baseExploreFragment, this.castingProvider.get());
        injectAnimationProvider(baseExploreFragment, this.animationProvider.get());
        injectStyleSheetProvider(baseExploreFragment, this.styleSheetProvider.get());
        injectMetricsProvider(baseExploreFragment, this.metricsProvider.get());
        injectSharingProvider(baseExploreFragment, this.sharingProvider.get());
    }

    public static <T extends Template> void injectAuthenticationProvider(BaseExploreFragment<T> baseExploreFragment, AuthenticationProvider authenticationProvider) {
        baseExploreFragment.authenticationProvider = authenticationProvider;
    }

    public static <T extends Template> void injectCustomerMetadataProvider(BaseExploreFragment<T> baseExploreFragment, CustomerMetadataProvider customerMetadataProvider) {
        baseExploreFragment.customerMetadataProvider = customerMetadataProvider;
    }

    public static <T extends Template> void injectDeviceInfoProvider(BaseExploreFragment<T> baseExploreFragment, DeviceInfoProvider deviceInfoProvider) {
        baseExploreFragment.deviceInfoProvider = deviceInfoProvider;
    }

    public static <T extends Template> void injectBuildInfoProvider(BaseExploreFragment<T> baseExploreFragment, BuildInfoProvider buildInfoProvider) {
        baseExploreFragment.buildInfoProvider = buildInfoProvider;
    }

    public static <T extends Template> void injectExternalActionProvider(BaseExploreFragment<T> baseExploreFragment, ExternalActionProvider externalActionProvider) {
        baseExploreFragment.externalActionProvider = externalActionProvider;
    }

    public static <T extends Template> void injectStorageProvider(BaseExploreFragment<T> baseExploreFragment, StorageProvider storageProvider) {
        baseExploreFragment.storageProvider = storageProvider;
    }

    public static <T extends Template> void injectEnvironmentProvider(BaseExploreFragment<T> baseExploreFragment, EnvironmentProvider environmentProvider) {
        baseExploreFragment.environmentProvider = environmentProvider;
    }

    public static <T extends Template> void injectPlaybackProvider(BaseExploreFragment<T> baseExploreFragment, PlaybackProvider playbackProvider) {
        baseExploreFragment.playbackProvider = playbackProvider;
    }

    public static <T extends Template> void injectUpsellProvider(BaseExploreFragment<T> baseExploreFragment, UpsellProvider upsellProvider) {
        baseExploreFragment.upsellProvider = upsellProvider;
    }

    public static <T extends Template> void injectCastingProvider(BaseExploreFragment<T> baseExploreFragment, CastingProvider castingProvider) {
        baseExploreFragment.castingProvider = castingProvider;
    }

    public static <T extends Template> void injectAnimationProvider(BaseExploreFragment<T> baseExploreFragment, AnimationProvider animationProvider) {
        baseExploreFragment.animationProvider = animationProvider;
    }

    public static <T extends Template> void injectStyleSheetProvider(BaseExploreFragment<T> baseExploreFragment, StyleSheetProvider styleSheetProvider) {
        baseExploreFragment.styleSheetProvider = styleSheetProvider;
    }

    public static <T extends Template> void injectMetricsProvider(BaseExploreFragment<T> baseExploreFragment, MetricsProvider metricsProvider) {
        baseExploreFragment.metricsProvider = metricsProvider;
    }

    public static <T extends Template> void injectSharingProvider(BaseExploreFragment<T> baseExploreFragment, SharingProvider sharingProvider) {
        baseExploreFragment.sharingProvider = sharingProvider;
    }
}
