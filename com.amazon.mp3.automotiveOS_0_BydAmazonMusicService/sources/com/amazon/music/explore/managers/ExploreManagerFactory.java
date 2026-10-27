package com.amazon.music.explore.managers;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import com.amazon.music.explore.providers.CastingProvider;
import com.amazon.music.explore.skyfire.SkyFireUtils;
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
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public class ExploreManagerFactory {
    private final AuthenticationProvider authenticationProvider;
    private final BuildInfoProvider buildInfoProvider;
    private final CastingProvider castingProvider;
    private final Context context;
    private final CustomerMetadataProvider customerMetadataProvider;
    private final DeviceInfoProvider deviceInfoProvider;
    private final EnvironmentProvider environmentProvider;
    private final MetricsProvider metricsProvider;
    private final NavigationProvider navigationProvider;
    private final PlaybackProvider playbackProvider;
    private final StorageProvider storageProvider;
    private final UserSubscriptionProvider subscriptionProvider;

    public ExploreManagerFactory(Context context, AuthenticationProvider authenticationProvider, CustomerMetadataProvider customerMetadataProvider, DeviceInfoProvider deviceInfoProvider, BuildInfoProvider buildInfoProvider, NavigationProvider navigationProvider, StorageProvider storageProvider, EnvironmentProvider environmentProvider, MetricsProvider metricsProvider, PlaybackProvider playbackProvider, UserSubscriptionProvider userSubscriptionProvider, CastingProvider castingProvider) {
        this.context = context;
        this.authenticationProvider = authenticationProvider;
        this.customerMetadataProvider = customerMetadataProvider;
        this.deviceInfoProvider = deviceInfoProvider;
        this.buildInfoProvider = buildInfoProvider;
        this.navigationProvider = navigationProvider;
        this.storageProvider = storageProvider;
        this.environmentProvider = environmentProvider;
        this.metricsProvider = metricsProvider;
        this.playbackProvider = playbackProvider;
        this.subscriptionProvider = userSubscriptionProvider;
        this.castingProvider = castingProvider;
    }

    public ExploreRootsAndBranchesManager requestRootsAndBranches(LifecycleOwner lifecycleOwner, Fragment fragment, String str) {
        String str2 = String.format("/showArtistRootsAndBranches/%s", str);
        ArrayList arrayList = new ArrayList();
        arrayList.add(SkyFireUtils.getInvokeHttpSkillMethod(str2));
        ExploreRootsAndBranchesManager exploreRootsAndBranchesManager = new ExploreRootsAndBranchesManager(this.authenticationProvider, this.customerMetadataProvider, this.deviceInfoProvider, this.buildInfoProvider, this.navigationProvider, this.storageProvider, this.environmentProvider, this.metricsProvider, this.playbackProvider, lifecycleOwner, fragment);
        exploreRootsAndBranchesManager.setInitialMethods(arrayList);
        return exploreRootsAndBranchesManager;
    }

    public ExploreLiveStreamManager createLiveStreamManager(LifecycleOwner lifecycleOwner) {
        return new ExploreLiveStreamManager(this.context, this.authenticationProvider, this.customerMetadataProvider, this.deviceInfoProvider, this.buildInfoProvider, this.navigationProvider, this.storageProvider, this.environmentProvider, this.metricsProvider, lifecycleOwner);
    }

    public ExploreXrayManager createXrayManager(LifecycleOwner lifecycleOwner, boolean z) {
        return new ExploreXrayManager(this.context, this.authenticationProvider, this.customerMetadataProvider, this.deviceInfoProvider, this.buildInfoProvider, this.navigationProvider, this.storageProvider, this.environmentProvider, lifecycleOwner, false, z);
    }

    public ExploreSpotlightManager createSpotlightManager(LifecycleOwner lifecycleOwner) {
        return new ExploreSpotlightManager(this.context, this.authenticationProvider, this.customerMetadataProvider, this.deviceInfoProvider, this.buildInfoProvider, this.navigationProvider, this.storageProvider, this.environmentProvider, this.castingProvider, this.metricsProvider, lifecycleOwner);
    }

    public ExploreVideoStoryManager createVideoStoryManager(LifecycleOwner lifecycleOwner) {
        return new ExploreVideoStoryManager(this.context, this.authenticationProvider, this.customerMetadataProvider, this.deviceInfoProvider, this.buildInfoProvider, this.navigationProvider, this.storageProvider, this.environmentProvider, this.metricsProvider, this.playbackProvider, lifecycleOwner);
    }
}
