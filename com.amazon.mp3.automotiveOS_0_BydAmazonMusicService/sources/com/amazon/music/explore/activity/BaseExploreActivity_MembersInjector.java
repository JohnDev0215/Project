package com.amazon.music.explore.activity;

import CoreInterface.v1_0.Template;
import com.amazon.music.explore.providers.ExplicitFilterProvider;
import com.amazon.music.explore.providers.WakeWordProvider;
import com.amazon.music.platform.providers.AuthenticationProvider;
import com.amazon.music.platform.providers.BuildInfoProvider;
import com.amazon.music.platform.providers.CustomerMetadataProvider;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import com.amazon.music.platform.providers.MetricsProvider;
import com.amazon.music.skyfire.ui.providers.EnvironmentProvider;
import com.amazon.music.skyfire.ui.providers.ExternalActionProvider;
import com.amazon.music.skyfire.ui.providers.NavigationProvider;
import com.amazon.music.skyfire.ui.providers.PlaybackProvider;
import com.amazon.music.skyfire.ui.providers.SharingProvider;
import com.amazon.music.skyfire.ui.providers.StorageProvider;
import com.amazon.music.skyfire.ui.providers.UserSubscriptionProvider;
import dagger.MembersInjector;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes4.dex */
public final class BaseExploreActivity_MembersInjector<T extends Template> implements MembersInjector<BaseExploreActivity<T>> {
    private final Provider<AuthenticationProvider> authenticationProvider;
    private final Provider<BuildInfoProvider> buildInfoProvider;
    private final Provider<CustomerMetadataProvider> customerMetadataProvider;
    private final Provider<DeviceInfoProvider> deviceInfoProvider;
    private final Provider<EnvironmentProvider> environmentProvider;
    private final Provider<ExplicitFilterProvider> explicitFilterProvider;
    private final Provider<ExternalActionProvider> externalActionProvider;
    private final Provider<MetricsProvider> metricsProvider;
    private final Provider<NavigationProvider> navigationProvider;
    private final Provider<PlaybackProvider> playbackProvider;
    private final Provider<SharingProvider> sharingProvider;
    private final Provider<StorageProvider> storageProvider;
    private final Provider<UserSubscriptionProvider> userSubscriptionProvider;
    private final Provider<WakeWordProvider> wakeWordProvider;

    public BaseExploreActivity_MembersInjector(Provider<AuthenticationProvider> provider, Provider<CustomerMetadataProvider> provider2, Provider<DeviceInfoProvider> provider3, Provider<BuildInfoProvider> provider4, Provider<StorageProvider> provider5, Provider<EnvironmentProvider> provider6, Provider<ExplicitFilterProvider> provider7, Provider<MetricsProvider> provider8, Provider<WakeWordProvider> provider9, Provider<UserSubscriptionProvider> provider10, Provider<SharingProvider> provider11, Provider<ExternalActionProvider> provider12, Provider<PlaybackProvider> provider13, Provider<NavigationProvider> provider14) {
        this.authenticationProvider = provider;
        this.customerMetadataProvider = provider2;
        this.deviceInfoProvider = provider3;
        this.buildInfoProvider = provider4;
        this.storageProvider = provider5;
        this.environmentProvider = provider6;
        this.explicitFilterProvider = provider7;
        this.metricsProvider = provider8;
        this.wakeWordProvider = provider9;
        this.userSubscriptionProvider = provider10;
        this.sharingProvider = provider11;
        this.externalActionProvider = provider12;
        this.playbackProvider = provider13;
        this.navigationProvider = provider14;
    }

    public static <T extends Template> MembersInjector<BaseExploreActivity<T>> create(Provider<AuthenticationProvider> provider, Provider<CustomerMetadataProvider> provider2, Provider<DeviceInfoProvider> provider3, Provider<BuildInfoProvider> provider4, Provider<StorageProvider> provider5, Provider<EnvironmentProvider> provider6, Provider<ExplicitFilterProvider> provider7, Provider<MetricsProvider> provider8, Provider<WakeWordProvider> provider9, Provider<UserSubscriptionProvider> provider10, Provider<SharingProvider> provider11, Provider<ExternalActionProvider> provider12, Provider<PlaybackProvider> provider13, Provider<NavigationProvider> provider14) {
        return new BaseExploreActivity_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14);
    }

    public void injectMembers(BaseExploreActivity<T> baseExploreActivity) {
        injectAuthenticationProvider(baseExploreActivity, this.authenticationProvider.get());
        injectCustomerMetadataProvider(baseExploreActivity, this.customerMetadataProvider.get());
        injectDeviceInfoProvider(baseExploreActivity, this.deviceInfoProvider.get());
        injectBuildInfoProvider(baseExploreActivity, this.buildInfoProvider.get());
        injectStorageProvider(baseExploreActivity, this.storageProvider.get());
        injectEnvironmentProvider(baseExploreActivity, this.environmentProvider.get());
        injectExplicitFilterProvider(baseExploreActivity, this.explicitFilterProvider.get());
        injectMetricsProvider(baseExploreActivity, this.metricsProvider.get());
        injectWakeWordProvider(baseExploreActivity, this.wakeWordProvider.get());
        injectUserSubscriptionProvider(baseExploreActivity, this.userSubscriptionProvider.get());
        injectSharingProvider(baseExploreActivity, this.sharingProvider.get());
        injectExternalActionProvider(baseExploreActivity, this.externalActionProvider.get());
        injectPlaybackProvider(baseExploreActivity, this.playbackProvider.get());
        injectNavigationProvider(baseExploreActivity, this.navigationProvider.get());
    }

    public static <T extends Template> void injectAuthenticationProvider(BaseExploreActivity<T> baseExploreActivity, AuthenticationProvider authenticationProvider) {
        baseExploreActivity.authenticationProvider = authenticationProvider;
    }

    public static <T extends Template> void injectCustomerMetadataProvider(BaseExploreActivity<T> baseExploreActivity, CustomerMetadataProvider customerMetadataProvider) {
        baseExploreActivity.customerMetadataProvider = customerMetadataProvider;
    }

    public static <T extends Template> void injectDeviceInfoProvider(BaseExploreActivity<T> baseExploreActivity, DeviceInfoProvider deviceInfoProvider) {
        baseExploreActivity.deviceInfoProvider = deviceInfoProvider;
    }

    public static <T extends Template> void injectBuildInfoProvider(BaseExploreActivity<T> baseExploreActivity, BuildInfoProvider buildInfoProvider) {
        baseExploreActivity.buildInfoProvider = buildInfoProvider;
    }

    public static <T extends Template> void injectStorageProvider(BaseExploreActivity<T> baseExploreActivity, StorageProvider storageProvider) {
        baseExploreActivity.storageProvider = storageProvider;
    }

    public static <T extends Template> void injectEnvironmentProvider(BaseExploreActivity<T> baseExploreActivity, EnvironmentProvider environmentProvider) {
        baseExploreActivity.environmentProvider = environmentProvider;
    }

    public static <T extends Template> void injectExplicitFilterProvider(BaseExploreActivity<T> baseExploreActivity, ExplicitFilterProvider explicitFilterProvider) {
        baseExploreActivity.explicitFilterProvider = explicitFilterProvider;
    }

    public static <T extends Template> void injectMetricsProvider(BaseExploreActivity<T> baseExploreActivity, MetricsProvider metricsProvider) {
        baseExploreActivity.metricsProvider = metricsProvider;
    }

    public static <T extends Template> void injectWakeWordProvider(BaseExploreActivity<T> baseExploreActivity, WakeWordProvider wakeWordProvider) {
        baseExploreActivity.wakeWordProvider = wakeWordProvider;
    }

    public static <T extends Template> void injectUserSubscriptionProvider(BaseExploreActivity<T> baseExploreActivity, UserSubscriptionProvider userSubscriptionProvider) {
        baseExploreActivity.userSubscriptionProvider = userSubscriptionProvider;
    }

    public static <T extends Template> void injectSharingProvider(BaseExploreActivity<T> baseExploreActivity, SharingProvider sharingProvider) {
        baseExploreActivity.sharingProvider = sharingProvider;
    }

    public static <T extends Template> void injectExternalActionProvider(BaseExploreActivity<T> baseExploreActivity, ExternalActionProvider externalActionProvider) {
        baseExploreActivity.externalActionProvider = externalActionProvider;
    }

    public static <T extends Template> void injectPlaybackProvider(BaseExploreActivity<T> baseExploreActivity, PlaybackProvider playbackProvider) {
        baseExploreActivity.playbackProvider = playbackProvider;
    }

    public static <T extends Template> void injectNavigationProvider(BaseExploreActivity<T> baseExploreActivity, NavigationProvider navigationProvider) {
        baseExploreActivity.navigationProvider = navigationProvider;
    }
}
