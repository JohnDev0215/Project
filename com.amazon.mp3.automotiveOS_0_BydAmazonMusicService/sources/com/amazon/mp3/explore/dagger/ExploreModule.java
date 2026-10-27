package com.amazon.mp3.explore.dagger;

import android.content.Context;
import com.amazon.mp3.dmsfcore.providers.DMSFCapabilitiesInfoProvider;
import com.amazon.mp3.dmsfcore.providers.DMSFChromeProvider;
import com.amazon.mp3.dmsfcore.providers.DMSFContextMenuProvider;
import com.amazon.mp3.dmsfcore.providers.DMSFDownloadProvider;
import com.amazon.mp3.dmsfcore.providers.DMSFEnvironmentProvider;
import com.amazon.mp3.dmsfcore.providers.DMSFLibraryProvider;
import com.amazon.mp3.dmsfcore.providers.DMSFNavigationProvider;
import com.amazon.mp3.dmsfcore.providers.DMSFNotificationProvider;
import com.amazon.mp3.dmsfcore.providers.DMSFStorageProvider;
import com.amazon.mp3.dmsfcore.providers.DMSFStyleSheetProvider;
import com.amazon.mp3.dmsfcore.providers.DMSFUpsellProvider;
import com.amazon.mp3.dmsfcore.providers.DMSFUserSubscriptionProvider;
import com.amazon.mp3.explore.providers.ExploreAnimationProvider;
import com.amazon.mp3.explore.providers.ExploreCastingProvider;
import com.amazon.mp3.explore.providers.ExploreExplicitFilterProvider;
import com.amazon.mp3.explore.providers.ExplorePlaybackProvider;
import com.amazon.mp3.explore.providers.ExploreSkyFireConfigurationProvider;
import com.amazon.mp3.explore.providers.ExploreWakeWordProvider;
import com.amazon.music.explore.containers.FeaturedContainer;
import com.amazon.music.explore.managers.ExploreManagerFactory;
import com.amazon.music.explore.providers.AnimationProvider;
import com.amazon.music.explore.providers.CastingProvider;
import com.amazon.music.explore.providers.ExplicitFilterProvider;
import com.amazon.music.explore.providers.WakeWordProvider;
import com.amazon.music.explore.providers.impl.ExploreElementBindingProvider;
import com.amazon.music.explore.providers.impl.ExploreExternalActionProvider;
import com.amazon.music.platform.providers.AuthenticationProvider;
import com.amazon.music.platform.providers.BuildInfoProvider;
import com.amazon.music.platform.providers.CustomerMetadataProvider;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import com.amazon.music.platform.providers.MetricsProvider;
import com.amazon.music.skyfire.core.network.CapabilitiesInfo;
import com.amazon.music.skyfire.core.network.NetworkInterceptor;
import com.amazon.music.skyfire.core.performance.SkillLatencyTracker;
import com.amazon.music.skyfire.ui.providers.ChromeProvider;
import com.amazon.music.skyfire.ui.providers.ContextMenuProvider;
import com.amazon.music.skyfire.ui.providers.DownloadProvider;
import com.amazon.music.skyfire.ui.providers.ElementBindingProvider;
import com.amazon.music.skyfire.ui.providers.EnvironmentProvider;
import com.amazon.music.skyfire.ui.providers.ExternalActionProvider;
import com.amazon.music.skyfire.ui.providers.LibraryProvider;
import com.amazon.music.skyfire.ui.providers.NavigationProvider;
import com.amazon.music.skyfire.ui.providers.NotificationProvider;
import com.amazon.music.skyfire.ui.providers.PlaybackProvider;
import com.amazon.music.skyfire.ui.providers.SharingProvider;
import com.amazon.music.skyfire.ui.providers.SkyFireConfigurationProvider;
import com.amazon.music.skyfire.ui.providers.SkyFireProvider;
import com.amazon.music.skyfire.ui.providers.StorageProvider;
import com.amazon.music.skyfire.ui.providers.StyleSheetProvider;
import com.amazon.music.skyfire.ui.providers.UpsellProvider;
import com.amazon.music.skyfire.ui.providers.UserSubscriptionProvider;
import com.amazon.music.skyfire.ui.providers.impl.DefaultNetworkInterceptorProvider;
import com.amazon.music.skyfire.ui.providers.impl.DefaultSharingProvider;
import com.amazon.music.skyfire.ui.providers.impl.DefaultSkillLatencyTracker;
import com.amazon.music.skyfire.ui.providers.impl.DefaultSkyFireProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ExploreModule.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000Ú\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bH\u0007J\b\u0010\u000b\u001a\u00020\fH\u0007J\b\u0010\r\u001a\u00020\u000eH\u0007J\b\u0010\u000f\u001a\u00020\u0010H\u0007J \u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0010H\u0007J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0007\u001a\u00020\bH\u0007J\b\u0010\u001a\u001a\u00020\u001bH\u0007Jh\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u00192\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020\u00142\u0006\u00100\u001a\u00020\nH\u0007J8\u00101\u001a\u0002022\u0006\u00103\u001a\u0002042\u0006\u0010&\u001a\u00020'2\u0006\u00105\u001a\u0002062\u0006\u0010-\u001a\u00020.2\u0006\u00107\u001a\u0002082\u0006\u0010\u0017\u001a\u00020\u0010H\u0007J@\u00109\u001a\u00020:2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u0002022\u0006\u0010+\u001a\u00020,2\u0006\u0010&\u001a\u00020'2\u0006\u00100\u001a\u00020\nH\u0007J\b\u0010@\u001a\u000204H\u0007J\u0010\u0010A\u001a\u00020'2\u0006\u0010\u0007\u001a\u00020\bH\u0007J\b\u0010B\u001a\u00020CH\u0007J\u0010\u0010D\u001a\u0002062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010E\u001a\u00020.2\u0006\u0010\u0007\u001a\u00020\bH\u0007J\b\u0010F\u001a\u000208H\u0007J\b\u0010G\u001a\u00020HH\u0007J\u0010\u0010I\u001a\u00020>2\u0006\u0010\u0007\u001a\u00020\bH\u0007JX\u0010J\u001a\u00020<2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010-\u001a\u00020.2\u0006\u0010K\u001a\u00020\u00062\u0006\u0010L\u001a\u00020C2\u0006\u0010M\u001a\u00020H2\u0006\u0010N\u001a\u00020>H\u0007J\u0010\u0010O\u001a\u00020)2\u0006\u0010\u0007\u001a\u00020\bH\u0007J\b\u0010P\u001a\u00020\u0016H\u0007J\b\u0010Q\u001a\u00020RH\u0007J\b\u0010S\u001a\u00020\u0014H\u0007J\u0010\u0010T\u001a\u00020U2\u0006\u0010\u0007\u001a\u00020\bH\u0007¨\u0006V"}, d2 = {"Lcom/amazon/mp3/explore/dagger/ExploreModule;", "", "()V", "provideAnimationProvider", "Lcom/amazon/music/explore/providers/AnimationProvider;", "provideCapabilitiesInfo", "Lcom/amazon/music/skyfire/core/network/CapabilitiesInfo;", "context", "Landroid/content/Context;", "provideCastingProvider", "Lcom/amazon/music/explore/providers/CastingProvider;", "provideChromeProvider", "Lcom/amazon/music/skyfire/ui/providers/ChromeProvider;", "provideContextMenuProvider", "Lcom/amazon/music/skyfire/ui/providers/ContextMenuProvider;", "provideDownloadProvider", "Lcom/amazon/music/skyfire/ui/providers/DownloadProvider;", "provideElementBindingProvider", "Lcom/amazon/music/skyfire/ui/providers/ElementBindingProvider;", "userSubscriptionProvider", "Lcom/amazon/music/skyfire/ui/providers/UserSubscriptionProvider;", "styleSheetProvider", "Lcom/amazon/music/skyfire/ui/providers/StyleSheetProvider;", "downloadProvider", "provideEnvironmentProvider", "Lcom/amazon/music/skyfire/ui/providers/EnvironmentProvider;", "provideExplicitFilterProvider", "Lcom/amazon/music/explore/providers/ExplicitFilterProvider;", "provideExploreManagerFactory", "Lcom/amazon/music/explore/managers/ExploreManagerFactory;", "authenticationProvider", "Lcom/amazon/music/platform/providers/AuthenticationProvider;", "customerMetadataProvider", "Lcom/amazon/music/platform/providers/CustomerMetadataProvider;", "deviceInfoProvider", "Lcom/amazon/music/platform/providers/DeviceInfoProvider;", "buildInfoProvider", "Lcom/amazon/music/platform/providers/BuildInfoProvider;", "navigationProvider", "Lcom/amazon/music/skyfire/ui/providers/NavigationProvider;", "storageProvider", "Lcom/amazon/music/skyfire/ui/providers/StorageProvider;", "environmentProvider", "metricsProvider", "Lcom/amazon/music/platform/providers/MetricsProvider;", "playbackProvider", "Lcom/amazon/music/skyfire/ui/providers/PlaybackProvider;", "subscriptionProvider", "castingProvider", "provideExternalActionProvider", "Lcom/amazon/music/skyfire/ui/providers/ExternalActionProvider;", "libraryProvider", "Lcom/amazon/music/skyfire/ui/providers/LibraryProvider;", "notificationProvider", "Lcom/amazon/music/skyfire/ui/providers/NotificationProvider;", "linkSharingProvider", "Lcom/amazon/music/skyfire/ui/providers/SharingProvider;", "provideFeaturedContainer", "Lcom/amazon/music/explore/containers/FeaturedContainer;", "skyFireProvider", "Lcom/amazon/music/skyfire/ui/providers/SkyFireProvider;", "skyFireConfigurationProvider", "Lcom/amazon/music/skyfire/ui/providers/SkyFireConfigurationProvider;", "externalActionProvider", "provideLibraryProvider", "provideNavigationProvider", "provideNetworkInterceptor", "Lcom/amazon/music/skyfire/core/network/NetworkInterceptor;", "provideNotificationProvider", "providePlaybackProvider", "provideSharingProvider", "provideSkillLatencyTracker", "Lcom/amazon/music/skyfire/core/performance/SkillLatencyTracker;", "provideSkyFireConfigurationProvider", "provideSkyFireProvider", "capabilitiesInfo", "networkInterceptor", "skillLatencyTracker", "skyfireConfigurationProvider", "provideStorageProvider", "provideStyleSheetProvider", "provideUpsellProvider", "Lcom/amazon/music/skyfire/ui/providers/UpsellProvider;", "provideUserSubscriptionProvider", "provideWakeWordProvider", "Lcom/amazon/music/explore/providers/WakeWordProvider;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ExploreModule {
    public final NavigationProvider provideNavigationProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new DMSFNavigationProvider(context);
    }

    public final ContextMenuProvider provideContextMenuProvider() {
        return new DMSFContextMenuProvider();
    }

    public final StorageProvider provideStorageProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new DMSFStorageProvider(context);
    }

    public final EnvironmentProvider provideEnvironmentProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new DMSFEnvironmentProvider(context);
    }

    public final UserSubscriptionProvider provideUserSubscriptionProvider() {
        return new DMSFUserSubscriptionProvider();
    }

    public final StyleSheetProvider provideStyleSheetProvider() {
        return new DMSFStyleSheetProvider();
    }

    public final SharingProvider provideSharingProvider() {
        return new DefaultSharingProvider();
    }

    public final UpsellProvider provideUpsellProvider() {
        return new DMSFUpsellProvider();
    }

    public final CapabilitiesInfo provideCapabilitiesInfo(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new DMSFCapabilitiesInfoProvider(context);
    }

    public final NetworkInterceptor provideNetworkInterceptor() {
        return new DefaultNetworkInterceptorProvider();
    }

    public final SkillLatencyTracker provideSkillLatencyTracker() {
        return new DefaultSkillLatencyTracker();
    }

    public final NotificationProvider provideNotificationProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new DMSFNotificationProvider(context);
    }

    public final LibraryProvider provideLibraryProvider() {
        return new DMSFLibraryProvider();
    }

    public final ExternalActionProvider provideExternalActionProvider(LibraryProvider libraryProvider, NavigationProvider navigationProvider, NotificationProvider notificationProvider, PlaybackProvider playbackProvider, SharingProvider linkSharingProvider, DownloadProvider downloadProvider) {
        Intrinsics.checkNotNullParameter(libraryProvider, "libraryProvider");
        Intrinsics.checkNotNullParameter(navigationProvider, "navigationProvider");
        Intrinsics.checkNotNullParameter(notificationProvider, "notificationProvider");
        Intrinsics.checkNotNullParameter(playbackProvider, "playbackProvider");
        Intrinsics.checkNotNullParameter(linkSharingProvider, "linkSharingProvider");
        Intrinsics.checkNotNullParameter(downloadProvider, "downloadProvider");
        return new ExploreExternalActionProvider(libraryProvider, navigationProvider, notificationProvider, playbackProvider, linkSharingProvider, downloadProvider);
    }

    public final ElementBindingProvider provideElementBindingProvider(UserSubscriptionProvider userSubscriptionProvider, StyleSheetProvider styleSheetProvider, DownloadProvider downloadProvider) {
        Intrinsics.checkNotNullParameter(userSubscriptionProvider, "userSubscriptionProvider");
        Intrinsics.checkNotNullParameter(styleSheetProvider, "styleSheetProvider");
        Intrinsics.checkNotNullParameter(downloadProvider, "downloadProvider");
        return new ExploreElementBindingProvider(userSubscriptionProvider, styleSheetProvider, downloadProvider);
    }

    public final ExplicitFilterProvider provideExplicitFilterProvider() {
        return new ExploreExplicitFilterProvider();
    }

    public final PlaybackProvider providePlaybackProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new ExplorePlaybackProvider(context);
    }

    public final WakeWordProvider provideWakeWordProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new ExploreWakeWordProvider(context);
    }

    public final CastingProvider provideCastingProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new ExploreCastingProvider(context);
    }

    public final AnimationProvider provideAnimationProvider() {
        return new ExploreAnimationProvider();
    }

    public final DownloadProvider provideDownloadProvider() {
        return new DMSFDownloadProvider();
    }

    public final SkyFireConfigurationProvider provideSkyFireConfigurationProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new ExploreSkyFireConfigurationProvider(context);
    }

    public final ChromeProvider provideChromeProvider() {
        return new DMSFChromeProvider();
    }

    public final FeaturedContainer provideFeaturedContainer(Context context, SkyFireProvider skyFireProvider, SkyFireConfigurationProvider skyFireConfigurationProvider, ExternalActionProvider externalActionProvider, MetricsProvider metricsProvider, NavigationProvider navigationProvider, CastingProvider castingProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(skyFireProvider, "skyFireProvider");
        Intrinsics.checkNotNullParameter(skyFireConfigurationProvider, "skyFireConfigurationProvider");
        Intrinsics.checkNotNullParameter(externalActionProvider, "externalActionProvider");
        Intrinsics.checkNotNullParameter(metricsProvider, "metricsProvider");
        Intrinsics.checkNotNullParameter(navigationProvider, "navigationProvider");
        Intrinsics.checkNotNullParameter(castingProvider, "castingProvider");
        return new FeaturedContainer(context, skyFireProvider, skyFireConfigurationProvider, externalActionProvider, metricsProvider, navigationProvider, castingProvider);
    }

    public final SkyFireProvider provideSkyFireProvider(Context context, AuthenticationProvider authenticationProvider, CustomerMetadataProvider customerMetadataProvider, DeviceInfoProvider deviceInfoProvider, BuildInfoProvider buildInfoProvider, PlaybackProvider playbackProvider, CapabilitiesInfo capabilitiesInfo, NetworkInterceptor networkInterceptor, SkillLatencyTracker skillLatencyTracker, SkyFireConfigurationProvider skyfireConfigurationProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(authenticationProvider, "authenticationProvider");
        Intrinsics.checkNotNullParameter(customerMetadataProvider, "customerMetadataProvider");
        Intrinsics.checkNotNullParameter(deviceInfoProvider, "deviceInfoProvider");
        Intrinsics.checkNotNullParameter(buildInfoProvider, "buildInfoProvider");
        Intrinsics.checkNotNullParameter(playbackProvider, "playbackProvider");
        Intrinsics.checkNotNullParameter(capabilitiesInfo, "capabilitiesInfo");
        Intrinsics.checkNotNullParameter(networkInterceptor, "networkInterceptor");
        Intrinsics.checkNotNullParameter(skillLatencyTracker, "skillLatencyTracker");
        Intrinsics.checkNotNullParameter(skyfireConfigurationProvider, "skyfireConfigurationProvider");
        return new DefaultSkyFireProvider(context, authenticationProvider, customerMetadataProvider, deviceInfoProvider, buildInfoProvider, playbackProvider, capabilitiesInfo, networkInterceptor, skillLatencyTracker, skyfireConfigurationProvider);
    }

    public final ExploreManagerFactory provideExploreManagerFactory(Context context, AuthenticationProvider authenticationProvider, CustomerMetadataProvider customerMetadataProvider, DeviceInfoProvider deviceInfoProvider, BuildInfoProvider buildInfoProvider, NavigationProvider navigationProvider, StorageProvider storageProvider, EnvironmentProvider environmentProvider, MetricsProvider metricsProvider, PlaybackProvider playbackProvider, UserSubscriptionProvider subscriptionProvider, CastingProvider castingProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(authenticationProvider, "authenticationProvider");
        Intrinsics.checkNotNullParameter(customerMetadataProvider, "customerMetadataProvider");
        Intrinsics.checkNotNullParameter(deviceInfoProvider, "deviceInfoProvider");
        Intrinsics.checkNotNullParameter(buildInfoProvider, "buildInfoProvider");
        Intrinsics.checkNotNullParameter(navigationProvider, "navigationProvider");
        Intrinsics.checkNotNullParameter(storageProvider, "storageProvider");
        Intrinsics.checkNotNullParameter(environmentProvider, "environmentProvider");
        Intrinsics.checkNotNullParameter(metricsProvider, "metricsProvider");
        Intrinsics.checkNotNullParameter(playbackProvider, "playbackProvider");
        Intrinsics.checkNotNullParameter(subscriptionProvider, "subscriptionProvider");
        Intrinsics.checkNotNullParameter(castingProvider, "castingProvider");
        return new ExploreManagerFactory(context, authenticationProvider, customerMetadataProvider, deviceInfoProvider, buildInfoProvider, navigationProvider, storageProvider, environmentProvider, metricsProvider, playbackProvider, subscriptionProvider, castingProvider);
    }
}
