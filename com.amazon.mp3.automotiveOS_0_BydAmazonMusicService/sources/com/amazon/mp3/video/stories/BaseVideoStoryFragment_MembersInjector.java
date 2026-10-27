package com.amazon.mp3.video.stories;

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

/* JADX INFO: loaded from: classes4.dex */
public final class BaseVideoStoryFragment_MembersInjector<T extends Template> implements MembersInjector<BaseVideoStoryFragment<T>> {
    public static <T extends Template> void injectAuthenticationProvider(BaseVideoStoryFragment<T> instance, AuthenticationProvider authenticationProvider) {
        instance.authenticationProvider = authenticationProvider;
    }

    public static <T extends Template> void injectCustomerMetadataProvider(BaseVideoStoryFragment<T> instance, CustomerMetadataProvider customerMetadataProvider) {
        instance.customerMetadataProvider = customerMetadataProvider;
    }

    public static <T extends Template> void injectDeviceInfoProvider(BaseVideoStoryFragment<T> instance, DeviceInfoProvider deviceInfoProvider) {
        instance.deviceInfoProvider = deviceInfoProvider;
    }

    public static <T extends Template> void injectBuildInfoProvider(BaseVideoStoryFragment<T> instance, BuildInfoProvider buildInfoProvider) {
        instance.buildInfoProvider = buildInfoProvider;
    }

    public static <T extends Template> void injectExternalActionProvider(BaseVideoStoryFragment<T> instance, ExternalActionProvider externalActionProvider) {
        instance.externalActionProvider = externalActionProvider;
    }

    public static <T extends Template> void injectStorageProvider(BaseVideoStoryFragment<T> instance, StorageProvider storageProvider) {
        instance.storageProvider = storageProvider;
    }

    public static <T extends Template> void injectEnvironmentProvider(BaseVideoStoryFragment<T> instance, EnvironmentProvider environmentProvider) {
        instance.environmentProvider = environmentProvider;
    }

    public static <T extends Template> void injectPlaybackProvider(BaseVideoStoryFragment<T> instance, PlaybackProvider playbackProvider) {
        instance.playbackProvider = playbackProvider;
    }

    public static <T extends Template> void injectUpsellProvider(BaseVideoStoryFragment<T> instance, UpsellProvider upsellProvider) {
        instance.upsellProvider = upsellProvider;
    }

    public static <T extends Template> void injectCastingProvider(BaseVideoStoryFragment<T> instance, CastingProvider castingProvider) {
        instance.castingProvider = castingProvider;
    }

    public static <T extends Template> void injectAnimationProvider(BaseVideoStoryFragment<T> instance, AnimationProvider animationProvider) {
        instance.animationProvider = animationProvider;
    }

    public static <T extends Template> void injectStyleSheetProvider(BaseVideoStoryFragment<T> instance, StyleSheetProvider styleSheetProvider) {
        instance.styleSheetProvider = styleSheetProvider;
    }

    public static <T extends Template> void injectMetricsProvider(BaseVideoStoryFragment<T> instance, MetricsProvider metricsProvider) {
        instance.metricsProvider = metricsProvider;
    }

    public static <T extends Template> void injectSharingProvider(BaseVideoStoryFragment<T> instance, SharingProvider sharingProvider) {
        instance.sharingProvider = sharingProvider;
    }
}
