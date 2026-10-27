package com.amazon.mp3.platform.dagger;

import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.data.image.ImageUrlRepository;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.providers.AuthenticationProvider;
import com.amazon.music.platform.providers.BuildInfoProvider;
import com.amazon.music.platform.providers.CustomerMetadataProvider;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import com.amazon.music.platform.providers.ExperimentFeatureGateProvider;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.amazon.music.platform.providers.FireFlyServiceProvider;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.platform.providers.MetricsProvider;
import com.amazon.music.platform.providers.NetworkInfoProvider;
import com.amazon.music.platform.providers.PlaybackProvider;
import com.amazon.music.platform.providers.WeblabProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: PlatformProvidersModule.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007J\b\u0010\u0005\u001a\u00020\u0006H\u0007J\b\u0010\u0007\u001a\u00020\bH\u0007J\b\u0010\t\u001a\u00020\nH\u0007J\b\u0010\u000b\u001a\u00020\fH\u0007J\b\u0010\r\u001a\u00020\u000eH\u0007J\b\u0010\u000f\u001a\u00020\u0010H\u0007J\b\u0010\u0011\u001a\u00020\u0012H\u0007J\b\u0010\u0013\u001a\u00020\u0014H\u0007J\b\u0010\u0015\u001a\u00020\u0016H\u0007J\b\u0010\u0017\u001a\u00020\u0018H\u0007J\b\u0010\u0019\u001a\u00020\u001aH\u0007¨\u0006\u001b"}, d2 = {"Lcom/amazon/mp3/platform/dagger/PlatformProvidersModule;", "", "()V", "prividePlaybackRequestProvider", "Lcom/amazon/music/platform/providers/PlaybackProvider;", "provideAuthenticationProvider", "Lcom/amazon/music/platform/providers/AuthenticationProvider;", "provideBuildInfoProvider", "Lcom/amazon/music/platform/providers/BuildInfoProvider;", "provideCustomerMetadataProvider", "Lcom/amazon/music/platform/providers/CustomerMetadataProvider;", "provideDeviceInfoProvider", "Lcom/amazon/music/platform/providers/DeviceInfoProvider;", "provideExperimentFeatureGateProvider", "Lcom/amazon/music/platform/providers/ExperimentFeatureGateProvider;", "provideFeatureGateProvider", "Lcom/amazon/music/platform/providers/FeatureGateProvider;", "provideFireflyServiceProvider", "Lcom/amazon/music/platform/providers/FireFlyServiceProvider;", "provideImageUrlRepository", "Lcom/amazon/music/platform/data/image/ImageUrlRepository;", "provideMetricsProvider", "Lcom/amazon/music/platform/providers/MetricsProvider;", "provideNetworkInfoProvider", "Lcom/amazon/music/platform/providers/NetworkInfoProvider;", "provideWeblabProvider", "Lcom/amazon/music/platform/providers/WeblabProvider;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlatformProvidersModule {
    public final AuthenticationProvider provideAuthenticationProvider() {
        AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(AuthenticationProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        if (!(interfaceProvider instanceof AuthenticationProvider)) {
            interfaceProvider = null;
        }
        AuthenticationProvider authenticationProvider = (AuthenticationProvider) interfaceProvider;
        if (authenticationProvider != null) {
            return authenticationProvider;
        }
        throw new IllegalStateException("AuthenticationProvider is not initialized");
    }

    public final BuildInfoProvider provideBuildInfoProvider() {
        AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(BuildInfoProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        if (!(interfaceProvider instanceof BuildInfoProvider)) {
            interfaceProvider = null;
        }
        BuildInfoProvider buildInfoProvider = (BuildInfoProvider) interfaceProvider;
        if (buildInfoProvider != null) {
            return buildInfoProvider;
        }
        throw new IllegalStateException("BuildInfoProvider is not initialized");
    }

    public final DeviceInfoProvider provideDeviceInfoProvider() {
        AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(DeviceInfoProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        if (!(interfaceProvider instanceof DeviceInfoProvider)) {
            interfaceProvider = null;
        }
        DeviceInfoProvider deviceInfoProvider = (DeviceInfoProvider) interfaceProvider;
        if (deviceInfoProvider != null) {
            return deviceInfoProvider;
        }
        throw new IllegalStateException("DeviceInfoProvider is not initialized");
    }

    public final CustomerMetadataProvider provideCustomerMetadataProvider() {
        AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(CustomerMetadataProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        if (!(interfaceProvider instanceof CustomerMetadataProvider)) {
            interfaceProvider = null;
        }
        CustomerMetadataProvider customerMetadataProvider = (CustomerMetadataProvider) interfaceProvider;
        if (customerMetadataProvider != null) {
            return customerMetadataProvider;
        }
        throw new IllegalStateException("CustomerMetadataProvider is not initialized");
    }

    public final MetricsProvider provideMetricsProvider() {
        AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(MetricsProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        if (!(interfaceProvider instanceof MetricsProvider)) {
            interfaceProvider = null;
        }
        MetricsProvider metricsProvider = (MetricsProvider) interfaceProvider;
        if (metricsProvider != null) {
            return metricsProvider;
        }
        throw new IllegalStateException("MetricsProvider is not initialized");
    }

    public final NetworkInfoProvider provideNetworkInfoProvider() {
        AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(NetworkInfoProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        if (!(interfaceProvider instanceof NetworkInfoProvider)) {
            interfaceProvider = null;
        }
        NetworkInfoProvider networkInfoProvider = (NetworkInfoProvider) interfaceProvider;
        if (networkInfoProvider != null) {
            return networkInfoProvider;
        }
        throw new IllegalStateException("NetworkInfoProvider is not initialized");
    }

    public final FeatureGateProvider provideFeatureGateProvider() {
        AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(FeatureGateProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        if (!(interfaceProvider instanceof FeatureGateProvider)) {
            interfaceProvider = null;
        }
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) interfaceProvider;
        if (featureGateProvider != null) {
            return featureGateProvider;
        }
        throw new IllegalStateException("FeatureGateProvider is not initialized");
    }

    public final WeblabProvider provideWeblabProvider() {
        AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(WeblabProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        if (!(interfaceProvider instanceof WeblabProvider)) {
            interfaceProvider = null;
        }
        WeblabProvider weblabProvider = (WeblabProvider) interfaceProvider;
        if (weblabProvider != null) {
            return weblabProvider;
        }
        throw new IllegalStateException("FeatureGateProvider is not initialized");
    }

    public final ExperimentFeatureGateProvider provideExperimentFeatureGateProvider() {
        AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(ExperimentFeatureGateProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        if (!(interfaceProvider instanceof ExperimentFeatureGateProvider)) {
            interfaceProvider = null;
        }
        ExperimentFeatureGateProvider experimentFeatureGateProvider = (ExperimentFeatureGateProvider) interfaceProvider;
        if (experimentFeatureGateProvider != null) {
            return experimentFeatureGateProvider;
        }
        throw new IllegalStateException("ExperimentFeatureGateProvider is not initialized");
    }

    public final FireFlyServiceProvider provideFireflyServiceProvider() {
        AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(FireFlyServiceProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        if (!(interfaceProvider instanceof FireFlyServiceProvider)) {
            interfaceProvider = null;
        }
        FireFlyServiceProvider fireFlyServiceProvider = (FireFlyServiceProvider) interfaceProvider;
        if (fireFlyServiceProvider != null) {
            return fireFlyServiceProvider;
        }
        throw new IllegalStateException("FireFlyServiceProvider is not initialized");
    }

    public final PlaybackProvider prividePlaybackRequestProvider() {
        AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(PlaybackProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        if (!(interfaceProvider instanceof PlaybackProvider)) {
            interfaceProvider = null;
        }
        PlaybackProvider playbackProvider = (PlaybackProvider) interfaceProvider;
        if (playbackProvider != null) {
            return playbackProvider;
        }
        throw new IllegalStateException("PlaybackProvider is not initialized");
    }

    public final ImageUrlRepository provideImageUrlRepository() {
        return new ImageUrlRepository(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }
}
