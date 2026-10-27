package com.amazon.mp3.video.stories;

import com.amazon.music.explore.skyfire.ExploreAuthentication;
import com.amazon.music.explore.skyfire.ExploreClientInformationCache;
import com.amazon.music.explore.skyfire.ExploreHttpSkillInfo;
import com.amazon.music.explore.skyfire.ExploreMethodsRegistry;
import com.amazon.music.explore.skyfire.containers.ContainerMethodOwner;
import com.amazon.music.explore.skyfire.containers.ContainerTemplateMethodsRegistry;
import com.amazon.music.platform.providers.AuthenticationProvider;
import com.amazon.music.platform.providers.BuildInfoProvider;
import com.amazon.music.platform.providers.CustomerMetadataProvider;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import com.amazon.music.skyfire.SkyFireApplication;
import com.amazon.music.skyfire.core.network.NetworkMethodsRegistry;
import com.amazon.music.skyfire.ui.providers.EnvironmentProvider;
import com.amazon.music.skyfire.ui.providers.StorageProvider;
import com.amazon.music.skyfire.ui.providers.impl.DefaultBuildInfoProvider;
import com.amazon.music.skyfire.ui.providers.impl.DefaultCapabilitiesInfoProvider;
import com.amazon.music.skyfire.ui.providers.impl.DefaultDeviceInfoProvider;
import com.amazon.music.skyfire.ui.providers.impl.DefaultNetworkInterceptorProvider;
import com.amazon.music.skyfire.ui.providers.impl.DefaultSkillLatencyTracker;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VideoStorySkyFireProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JJ\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/video/stories/VideoStorySkyFireProvider;", "", "()V", "getSkyFireApplication", "Lcom/amazon/music/skyfire/SkyFireApplication;", "containerMethodOwner", "Lcom/amazon/music/explore/skyfire/containers/ContainerMethodOwner;", "authenticationProvider", "Lcom/amazon/music/platform/providers/AuthenticationProvider;", "customerMetadataProvider", "Lcom/amazon/music/platform/providers/CustomerMetadataProvider;", "deviceInfoProvider", "Lcom/amazon/music/platform/providers/DeviceInfoProvider;", "buildInfoProvider", "Lcom/amazon/music/platform/providers/BuildInfoProvider;", "storageProvider", "Lcom/amazon/music/skyfire/ui/providers/StorageProvider;", "environmentProvider", "Lcom/amazon/music/skyfire/ui/providers/EnvironmentProvider;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class VideoStorySkyFireProvider {
    public static final VideoStorySkyFireProvider INSTANCE = new VideoStorySkyFireProvider();

    private VideoStorySkyFireProvider() {
    }

    public final SkyFireApplication getSkyFireApplication(ContainerMethodOwner containerMethodOwner, AuthenticationProvider authenticationProvider, CustomerMetadataProvider customerMetadataProvider, DeviceInfoProvider deviceInfoProvider, BuildInfoProvider buildInfoProvider, StorageProvider storageProvider, EnvironmentProvider environmentProvider) {
        Intrinsics.checkNotNullParameter(containerMethodOwner, "containerMethodOwner");
        SkyFireApplication skyFireApplication = new SkyFireApplication();
        ExploreHttpSkillInfo exploreHttpSkillInfo = new ExploreHttpSkillInfo(storageProvider, environmentProvider);
        Intrinsics.checkNotNull(deviceInfoProvider);
        DefaultDeviceInfoProvider defaultDeviceInfoProvider = new DefaultDeviceInfoProvider(deviceInfoProvider);
        Intrinsics.checkNotNull(buildInfoProvider);
        NetworkMethodsRegistry networkMethodsRegistry = new NetworkMethodsRegistry(skyFireApplication, defaultDeviceInfoProvider, new DefaultBuildInfoProvider(deviceInfoProvider, buildInfoProvider), exploreHttpSkillInfo, new DefaultCapabilitiesInfoProvider(), new DefaultNetworkInterceptorProvider(), new DefaultSkillLatencyTracker(), new ExploreAuthentication(authenticationProvider, customerMetadataProvider), ExploreClientInformationCache.INSTANCE);
        networkMethodsRegistry.registerMethods();
        networkMethodsRegistry.setMethodsDispatcher(skyFireApplication);
        new ExploreMethodsRegistry(skyFireApplication, exploreHttpSkillInfo).registerMethods();
        final ContainerTemplateMethodsRegistry containerTemplateMethodsRegistry = new ContainerTemplateMethodsRegistry(skyFireApplication);
        containerTemplateMethodsRegistry.registerMethods();
        containerTemplateMethodsRegistry.setOwnerRegistry(skyFireApplication);
        containerTemplateMethodsRegistry.registerOwner(containerMethodOwner);
        final String ownerId = containerMethodOwner.getOwnerId();
        containerMethodOwner.setCloseCallback(new ContainerMethodOwner.CloseCallback() { // from class: com.amazon.mp3.video.stories.VideoStorySkyFireProvider$$ExternalSyntheticLambda0
            @Override // com.amazon.music.explore.skyfire.containers.ContainerMethodOwner.CloseCallback
            public final void onClose() {
                VideoStorySkyFireProvider.m1680getSkyFireApplication$lambda0(containerTemplateMethodsRegistry, ownerId);
            }
        });
        return skyFireApplication;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getSkyFireApplication$lambda-0, reason: not valid java name */
    public static final void m1680getSkyFireApplication$lambda0(ContainerTemplateMethodsRegistry containerTemplateMethodsRegistry, String str) {
        Intrinsics.checkNotNullParameter(containerTemplateMethodsRegistry, "$containerTemplateMethodsRegistry");
        containerTemplateMethodsRegistry.deregisterOwner(str);
        containerTemplateMethodsRegistry.unRegisterMethods();
    }
}
