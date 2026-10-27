package com.amazon.podcast.bootstrap;

import android.content.Context;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.AdvertisingPreferencesProvider;
import com.amazon.music.platform.providers.AuthenticationProvider;
import com.amazon.music.platform.providers.BuildInfoProvider;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.amazon.podcast.deeplinks.PodcastDeeplink;
import com.amazon.podcast.external.car.CarModeUserPresetHandler;

/* JADX INFO: loaded from: classes5.dex */
public final class Bootstrap {
    private final ActionBarProvider actionBarProvider;
    private final AdvertisingPreferencesProvider advertisingPreferencesProvider;
    private final AuthenticationProvider authenticationInfoProvider;
    private final BuildInfoProvider buildInfoProvider;
    private final CarModeUserPresetHandler carModeUserPresetHandler;
    private final Context context;
    private final PodcastDeeplink deeplink;
    private final DeviceInfoProvider deviceInfoProvider;
    private final DownloadSettingsProvider downloadSettingsProvider;
    private final DynamicMessagingProvider dynamicMessagingProvider;
    private final FeatureGateProvider featureGateProvider;
    private final FragmentInfoProvider fragmentInfoProvider;
    private boolean isRestoringPlayback;
    private final NetworkSettingsProvider networkSettingsProvider;
    private final SharingProvider sharingProvider;
    private final UserInfoProvider userInfoProvider;

    public Bootstrap(final PodcastDeeplink deeplink, final Context context, final UserInfoProvider userInfoProvider, final ActionBarProvider actionBarProvider, final FragmentInfoProvider fragmentInfoProvider, final NetworkSettingsProvider networkSettingsProvider, final DownloadSettingsProvider downloadSettingsProvider, final SharingProvider sharingProvider, final FeatureGateProvider featureGateProvider, final boolean isRestoringPlayback, final DynamicMessagingProvider dynamicMessagingProvider, final CarModeUserPresetHandler carModeUserPresetHandler, final AdvertisingPreferencesProvider advertisingPreferencesProvider) {
        this.deeplink = deeplink;
        this.context = context;
        this.actionBarProvider = actionBarProvider;
        this.userInfoProvider = userInfoProvider;
        this.fragmentInfoProvider = fragmentInfoProvider;
        this.networkSettingsProvider = networkSettingsProvider;
        this.downloadSettingsProvider = downloadSettingsProvider;
        this.sharingProvider = sharingProvider;
        this.isRestoringPlayback = isRestoringPlayback;
        Providers providers = Providers.INSTANCE;
        this.deviceInfoProvider = (DeviceInfoProvider) providers.get(DeviceInfoProvider.class);
        this.buildInfoProvider = (BuildInfoProvider) providers.get(BuildInfoProvider.class);
        this.authenticationInfoProvider = (AuthenticationProvider) providers.get(AuthenticationProvider.class);
        this.featureGateProvider = featureGateProvider;
        this.dynamicMessagingProvider = dynamicMessagingProvider;
        this.carModeUserPresetHandler = carModeUserPresetHandler;
        this.advertisingPreferencesProvider = advertisingPreferencesProvider;
    }

    public PodcastDeeplink getDeeplink() {
        return this.deeplink;
    }

    public boolean isRestoringPlayback() {
        return this.isRestoringPlayback;
    }

    public UserInfoProvider getUserInfoProvider() {
        return this.userInfoProvider;
    }

    public Context getContext() {
        return this.context;
    }

    public FragmentInfoProvider getFragmentInfoProvider() {
        return this.fragmentInfoProvider;
    }

    public ActionBarProvider getActionBarProvider() {
        return this.actionBarProvider;
    }

    public DeviceInfoProvider getDeviceInfoProvider() {
        return this.deviceInfoProvider;
    }

    public BuildInfoProvider getBuildInfoProvider() {
        return this.buildInfoProvider;
    }

    public DownloadSettingsProvider getDownloadSettingsProvider() {
        return this.downloadSettingsProvider;
    }

    public AuthenticationProvider getAuthenticationInfoProvider() {
        return this.authenticationInfoProvider;
    }

    public NetworkSettingsProvider getNetworkSettingsProvider() {
        return this.networkSettingsProvider;
    }

    public SharingProvider getSharingProvider() {
        return this.sharingProvider;
    }

    public FeatureGateProvider getFeatureGateProvider() {
        return this.featureGateProvider;
    }

    public DynamicMessagingProvider getDynamicMessagingProvider() {
        return this.dynamicMessagingProvider;
    }

    public CarModeUserPresetHandler getCarModeUserPresetHandler() {
        return this.carModeUserPresetHandler;
    }

    public AdvertisingPreferencesProvider getAdvertisingPreferencesProvider() {
        return this.advertisingPreferencesProvider;
    }
}
