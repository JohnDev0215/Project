package com.amazon.mp3.explore.dagger;

import android.content.Context;
import com.amazon.music.explore.managers.ExploreManagerFactory;
import com.amazon.music.explore.providers.CastingProvider;
import com.amazon.music.platform.providers.AuthenticationProvider;
import com.amazon.music.platform.providers.BuildInfoProvider;
import com.amazon.music.platform.providers.CustomerMetadataProvider;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import com.amazon.music.platform.providers.MetricsProvider;
import com.amazon.music.skyfire.ui.providers.EnvironmentProvider;
import com.amazon.music.skyfire.ui.providers.NavigationProvider;
import com.amazon.music.skyfire.ui.providers.PlaybackProvider;
import com.amazon.music.skyfire.ui.providers.StorageProvider;
import com.amazon.music.skyfire.ui.providers.UserSubscriptionProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes3.dex */
public final class ExploreModule_ProvideExploreManagerFactoryFactory implements Factory<ExploreManagerFactory> {
    private final Provider<AuthenticationProvider> authenticationProvider;
    private final Provider<BuildInfoProvider> buildInfoProvider;
    private final Provider<CastingProvider> castingProvider;
    private final Provider<Context> contextProvider;
    private final Provider<CustomerMetadataProvider> customerMetadataProvider;
    private final Provider<DeviceInfoProvider> deviceInfoProvider;
    private final Provider<EnvironmentProvider> environmentProvider;
    private final Provider<MetricsProvider> metricsProvider;
    private final ExploreModule module;
    private final Provider<NavigationProvider> navigationProvider;
    private final Provider<PlaybackProvider> playbackProvider;
    private final Provider<StorageProvider> storageProvider;
    private final Provider<UserSubscriptionProvider> subscriptionProvider;

    public ExploreModule_ProvideExploreManagerFactoryFactory(ExploreModule module, Provider<Context> contextProvider, Provider<AuthenticationProvider> authenticationProvider, Provider<CustomerMetadataProvider> customerMetadataProvider, Provider<DeviceInfoProvider> deviceInfoProvider, Provider<BuildInfoProvider> buildInfoProvider, Provider<NavigationProvider> navigationProvider, Provider<StorageProvider> storageProvider, Provider<EnvironmentProvider> environmentProvider, Provider<MetricsProvider> metricsProvider, Provider<PlaybackProvider> playbackProvider, Provider<UserSubscriptionProvider> subscriptionProvider, Provider<CastingProvider> castingProvider) {
        this.module = module;
        this.contextProvider = contextProvider;
        this.authenticationProvider = authenticationProvider;
        this.customerMetadataProvider = customerMetadataProvider;
        this.deviceInfoProvider = deviceInfoProvider;
        this.buildInfoProvider = buildInfoProvider;
        this.navigationProvider = navigationProvider;
        this.storageProvider = storageProvider;
        this.environmentProvider = environmentProvider;
        this.metricsProvider = metricsProvider;
        this.playbackProvider = playbackProvider;
        this.subscriptionProvider = subscriptionProvider;
        this.castingProvider = castingProvider;
    }

    @Override // javax.inject.Provider
    public ExploreManagerFactory get() {
        return provideExploreManagerFactory(this.module, this.contextProvider.get(), this.authenticationProvider.get(), this.customerMetadataProvider.get(), this.deviceInfoProvider.get(), this.buildInfoProvider.get(), this.navigationProvider.get(), this.storageProvider.get(), this.environmentProvider.get(), this.metricsProvider.get(), this.playbackProvider.get(), this.subscriptionProvider.get(), this.castingProvider.get());
    }

    public static ExploreModule_ProvideExploreManagerFactoryFactory create(ExploreModule module, Provider<Context> contextProvider, Provider<AuthenticationProvider> authenticationProvider, Provider<CustomerMetadataProvider> customerMetadataProvider, Provider<DeviceInfoProvider> deviceInfoProvider, Provider<BuildInfoProvider> buildInfoProvider, Provider<NavigationProvider> navigationProvider, Provider<StorageProvider> storageProvider, Provider<EnvironmentProvider> environmentProvider, Provider<MetricsProvider> metricsProvider, Provider<PlaybackProvider> playbackProvider, Provider<UserSubscriptionProvider> subscriptionProvider, Provider<CastingProvider> castingProvider) {
        return new ExploreModule_ProvideExploreManagerFactoryFactory(module, contextProvider, authenticationProvider, customerMetadataProvider, deviceInfoProvider, buildInfoProvider, navigationProvider, storageProvider, environmentProvider, metricsProvider, playbackProvider, subscriptionProvider, castingProvider);
    }

    public static ExploreManagerFactory provideExploreManagerFactory(ExploreModule instance, Context context, AuthenticationProvider authenticationProvider, CustomerMetadataProvider customerMetadataProvider, DeviceInfoProvider deviceInfoProvider, BuildInfoProvider buildInfoProvider, NavigationProvider navigationProvider, StorageProvider storageProvider, EnvironmentProvider environmentProvider, MetricsProvider metricsProvider, PlaybackProvider playbackProvider, UserSubscriptionProvider subscriptionProvider, CastingProvider castingProvider) {
        return (ExploreManagerFactory) Preconditions.checkNotNullFromProvides(instance.provideExploreManagerFactory(context, authenticationProvider, customerMetadataProvider, deviceInfoProvider, buildInfoProvider, navigationProvider, storageProvider, environmentProvider, metricsProvider, playbackProvider, subscriptionProvider, castingProvider));
    }
}
