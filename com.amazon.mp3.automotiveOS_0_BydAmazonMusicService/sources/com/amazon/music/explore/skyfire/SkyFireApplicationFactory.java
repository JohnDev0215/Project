package com.amazon.music.explore.skyfire;

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
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public class SkyFireApplicationFactory {
    private static ContainerTemplateMethodsRegistry containerTemplateMethodsRegistry;
    private static SkyFireApplication skyFireAppForFragmentStack;
    private static SkyFireApplication skyFireApplication;

    public static SkyFireApplication getSkyFireApplication(ContainerMethodOwner containerMethodOwner, AuthenticationProvider authenticationProvider, CustomerMetadataProvider customerMetadataProvider, DeviceInfoProvider deviceInfoProvider, BuildInfoProvider buildInfoProvider, StorageProvider storageProvider, EnvironmentProvider environmentProvider) {
        if (skyFireApplication == null) {
            skyFireApplication = new SkyFireApplication();
            ExploreHttpSkillInfo exploreHttpSkillInfo = new ExploreHttpSkillInfo(storageProvider, environmentProvider);
            NetworkMethodsRegistry networkMethodsRegistry = new NetworkMethodsRegistry(skyFireApplication, new DefaultDeviceInfoProvider(deviceInfoProvider), new DefaultBuildInfoProvider(deviceInfoProvider, buildInfoProvider), exploreHttpSkillInfo, new DefaultCapabilitiesInfoProvider(), new DefaultNetworkInterceptorProvider(), new DefaultSkillLatencyTracker(), new ExploreAuthentication(authenticationProvider, customerMetadataProvider), ExploreClientInformationCache.INSTANCE);
            networkMethodsRegistry.registerMethods();
            new ExploreMethodsRegistry(skyFireApplication, exploreHttpSkillInfo).registerMethods();
            containerTemplateMethodsRegistry = new ContainerTemplateMethodsRegistry(skyFireApplication);
            networkMethodsRegistry.setMethodsDispatcher(skyFireApplication);
        }
        containerTemplateMethodsRegistry.registerMethods();
        containerTemplateMethodsRegistry.setOwnerRegistry(skyFireApplication);
        containerTemplateMethodsRegistry.registerOwner(containerMethodOwner);
        final String ownerId = containerMethodOwner.getOwnerId();
        containerMethodOwner.setCloseCallback(new ContainerMethodOwner.CloseCallback() { // from class: com.amazon.music.explore.skyfire.SkyFireApplicationFactory$$ExternalSyntheticLambda0
            @Override // com.amazon.music.explore.skyfire.containers.ContainerMethodOwner.CloseCallback
            public final void onClose() {
                SkyFireApplicationFactory.lambda$getSkyFireApplication$0(ownerId);
            }
        });
        return skyFireApplication;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$getSkyFireApplication$0(String str) {
        containerTemplateMethodsRegistry.deregisterOwner(str);
    }

    public static void deregisterContainerMethods() {
        if (skyFireApplication != null) {
            Set<String> containerMethods = containerTemplateMethodsRegistry.getContainerMethods();
            skyFireApplication.deregisterMethods(containerMethods, containerMethods);
        }
    }
}
