package com.amazon.mp3.capability;

import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Environment;
import android.preference.PreferenceManager;
import androidx.annotation.VisibleForTesting;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.AccountRequestUtil;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.activity.settings.SettingsActivity;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.bottomnavbar.BottomNavigationBarContainer;
import com.amazon.mp3.elf.ExplicitLanguageManager;
import com.amazon.mp3.environment.MusicTerritory;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.ftu.FTUSettingsUtil;
import com.amazon.mp3.platform.providers.CachedFeatureFlagProviderImpl;
import com.amazon.mp3.storage.ExternalStorageListener;
import com.amazon.mp3.util.AmazonDevice;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PlatformUtil;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.mp3.util.StringUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.weblab.ExperimentWeblabs;
import com.amazon.mp3.weblab.LegacyWeblabController;
import com.amazon.mp3.weblab.Weblab;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.DataNotReadyException;
import com.amazon.music.account.MusicAccountNotCreatedException;
import com.amazon.music.curate.featuregating.CurateFeatureGating;
import com.amazon.music.externalstorage.ExternalStorageNotSupportedException;
import com.amazon.music.externalstorage.StorageLocationFileManager;
import com.amazon.music.externalstorage.StorageState;
import com.amazon.music.featureflag.FeatureFlagProvider;
import com.amazon.music.featureflag.FeatureFlagUnavailableException;
import com.amazon.music.featuregating.configuration.Configuration;
import com.amazon.music.find.featuregating.FindFeatureGating;
import com.amazon.music.freetier.featuregating.FMPMFeatureGating;
import com.amazon.music.marketplace.Marketplace;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.platform.data.WeblabTreatment;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.ConfigurationProvider;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.amazon.music.platform.providers.WeblabProvider;
import com.amazon.music.subscription.UserSubscription;
import com.amazon.music.voice.featuregating.AlexaFeatureGating;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import com.amazon.queueexperience.feature.DynamicPlaylistFeatureGating;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class Capabilities {
    public static final String TAG = "Capabilities";
    static float sHeapSize;

    public String getStorageDir() {
        return "amazonmp3";
    }

    public boolean hasWidgets() {
        return true;
    }

    public boolean isAddToPlaylistShovelerEnabled() {
        return true;
    }

    public boolean isAppToWebSSOEnabled() {
        return true;
    }

    public boolean isMyLibraryV2Enabled() {
        return true;
    }

    public boolean isXRayEnabled() {
        return true;
    }

    public boolean shouldCloudBeSupported() {
        return true;
    }

    public boolean shouldSplitDBAccess() {
        return false;
    }

    public boolean supportsLocalizedCollation() {
        return true;
    }

    public boolean shouldUseGridView() {
        return AmazonApplication.getContext().getResources().getBoolean(R.bool.use_grid_view);
    }

    public boolean useOldStyleCarouselBadging() {
        return !shouldUseGridView();
    }

    public boolean isExternalProviderEnabled() {
        return AmazonDevice.isGrover() || AmazonDevice.isCanary();
    }

    public boolean supportsSecondaryLogin(final Context context) {
        return isFeatureEnabledLocally(context, R.bool.SecondaryLogin) && !AmazonDevice.isGrover();
    }

    public String getPlatformName() {
        return PlatformNameProvider.getPlatformName();
    }

    public boolean isStoreSupported() {
        try {
            if (FeatureFlagsProvider.getFeatureFlagProvider().canAccessStore() && isConcurrentFamilyUpsellEnabled()) {
                if (!ChildUserUtil.INSTANCE.isChildUser(AmazonApplication.getApplication().getApplicationContext())) {
                    return true;
                }
            }
            return false;
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Feature flag provider unavailable ", e);
            return FeatureFlagsProvider.getNoOpProvider().canAccessStore();
        }
    }

    public boolean isConcurrentFamilyUpsellEnabled() {
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        if (featureGateProvider == null) {
            Log.error(TAG, "Error fetching Feature gate provider");
        }
        return featureGateProvider != null && featureGateProvider.isFeatureAvailable(Feature.concurrent_family_upsell_enabled);
    }

    public boolean isGPBLaunchedInTerritory() {
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        if (featureGateProvider == null) {
            Log.error(TAG, "Error fetching Feature gate provider");
        }
        return featureGateProvider != null && featureGateProvider.isFeatureAvailable(Feature.google_iab_launched_in_territory);
    }

    public boolean shouldRemoveBMWV() {
        return Feature.remove_bluemoon_web_views.isEnabled();
    }

    public boolean shouldUpsellWithGoogleInAppBilling() {
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        if (featureGateProvider == null) {
            Log.error(TAG, "Error fetching Feature gate provider");
        }
        return featureGateProvider != null && featureGateProvider.isFeatureAvailable(Feature.upsell_with_google_iab);
    }

    public boolean shouldPullEmpSkuFromDeeplink() {
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        if (featureGateProvider == null) {
            Log.error(TAG, "Error fetching Feature gate provider");
        }
        return featureGateProvider != null && featureGateProvider.isFeatureAvailable(Feature.pull_sku_from_deeplink);
    }

    public boolean shouldUseDynamicMessagingForAdsSettingsOnDemandListeningUpsell() {
        return shouldUpsellWithGoogleInAppBilling() || Feature.use_dynamic_messaging_for_non_blocking_upsell.isEnabled();
    }

    public boolean shouldBrowseBeSupported() {
        return UserSubscriptionUtil.getUserSubscription().isInSubscriptionMarketplace();
    }

    public boolean isOnlineMusicEnabled() {
        try {
            AccountManagerSingleton.get().getUser();
            return true;
        } catch (DataNotReadyException unused) {
            return false;
        } catch (MusicAccountNotCreatedException unused2) {
            return true;
        }
    }

    public boolean playlistFollowButtonChangeEnabled() {
        try {
            boolean zIsPlaylistFollowTextChangeEnabled = FeatureFlagsProvider.getFeatureFlagProvider().isPlaylistFollowTextChangeEnabled();
            Log.debug(TAG, "Arcus Feature Flag PLAYLIST_FOLLOW_TEXT_CHANGE: %b", Boolean.valueOf(zIsPlaylistFollowTextChangeEnabled));
            return zIsPlaylistFollowTextChangeEnabled;
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Unable to retrieve state of isPlaylistFollowTextChangeEnabled()", e);
            return FeatureFlagsProvider.getNoOpProvider().isPlaylistFollowTextChangeEnabled();
        }
    }

    public boolean canFreeTierUserFollowPlaylists() {
        return FMPMFeatureGating.ENABLE_PLAYLIST_FOLLOW.isEnabled();
    }

    public boolean isAmazonAdsEnabled() {
        return isFeatureEnabledLocally(AmazonApplication.getContext(), R.bool.AmazonAdsEnabled);
    }

    public boolean isAlexaEnabled() {
        if (!isFeatureEnabledLocally(AmazonApplication.getContext(), R.bool.ALEXAEnabled)) {
            return false;
        }
        if (UserSubscriptionUtil.isNightwingOnly()) {
            return isAOMFreeTierEnabled() && canAccessAlexa();
        }
        return canAccessAlexa();
    }

    @VisibleForTesting
    public boolean canAccessAlexa() {
        return AlexaFeatureGating.ALEXA.isEnabled();
    }

    private boolean isAOMFreeTierEnabled() {
        return FMPMFeatureGating.AOM_FREE_TIER.isEnabled();
    }

    public boolean canAccessCasting() {
        FeatureFlagProvider featureFlagProvider = FeatureFlagsProvider.getFeatureFlagProvider();
        if (featureFlagProvider == null) {
            return false;
        }
        try {
            return featureFlagProvider.canAccessCasting();
        } catch (FeatureFlagUnavailableException unused) {
            Log.error(TAG, "Feature flag not available when checking for Casting.");
            return false;
        }
    }

    public boolean isCastingEnabled() {
        return isFeatureEnabledLocally(AmazonApplication.getContext(), R.bool.Casting);
    }

    boolean isFeatureEnabledLocally(final Context context, final int id) {
        return context.getResources().getBoolean(id);
    }

    public boolean isWakeWordEnabled(final Context context) {
        return SettingsUtil.isWakeWordUserSettingEnabled(context) && isAlexaEnabled() && canAccessWakeWord();
    }

    public boolean canAccessWakeWord() {
        return AlexaFeatureGating.WAKEWORD.isEnabled() && isAlexaEnabled();
    }

    public boolean isCpuArchitectureSupported() {
        String[] strArr = Build.SUPPORTED_ABIS;
        if (strArr != null && strArr.length != 0) {
            boolean z = "armeabi-v7a".equals(strArr[0]) || "arm64-v8a".equals(strArr[0]);
            boolean zHasSystemFeature = AmazonApplication.getApplication().getPackageManager().hasSystemFeature("org.chromium.arc");
            String str = TAG;
            Log.debug(str, "suppotedAbis: " + Arrays.toString(strArr) + " isFirstABISupported: " + z + " isChromeOs: " + zHasSystemFeature);
            if (z && !zHasSystemFeature) {
                return true;
            }
            Log.warning(str, "Videos not supported on this device");
        }
        return false;
    }

    public boolean isPackageAutomotive() {
        return AmazonApplication.getApplication().getApplicationContext().getPackageName().equals("com.amazon.mp3.automotiveOS");
    }

    public boolean isVideosEnabled() {
        Log.debug(TAG, "isMusicVideosEnabled: " + isMusicVideosEnabled() + " , isBOPVideosEnabled: " + isBOPVideosEnabled());
        return isMusicVideosEnabled() || (isBOPVideosEnabled() && !ChildUserUtil.INSTANCE.isChildUser(AmazonApplication.getApplication().getApplicationContext()));
    }

    public boolean isMusicVideosEnabled() {
        return isFeatureEnabledLocally(AmazonApplication.getContext(), R.bool.VideosEnabled) && ((FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class)).isFeatureAvailable(Feature.Music_Videos) && !isPackageAutomotive() && isVideosWeblabEnabledInNewTerritories();
    }

    public boolean isCategoryPostEnabled() {
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        if (featureGateProvider == null) {
            Log.error(TAG, "Error fetching Feature gate provider");
        }
        return featureGateProvider != null && featureGateProvider.isFeatureAvailable(Feature.Category_Post_Launch);
    }

    public boolean isCategoryPostEnabledNonTriggering() {
        WeblabProvider weblabProvider;
        if (!isCategoryPostEnabled() || (weblabProvider = (WeblabProvider) Providers.INSTANCE.get(WeblabProvider.class)) == null) {
            return false;
        }
        WeblabTreatment treatment = weblabProvider.getTreatment(Weblab.DM_EXPERIMENT_CATEGORY_PAGE_YOUNG_ADULTS.toString(), false);
        return treatment == WeblabTreatment.T1 || treatment == WeblabTreatment.T3;
    }

    public boolean isVideoLandingPageEnabled() {
        return isFeatureEnabledLocally(AmazonApplication.getContext(), R.bool.VideosEnabled) && ((FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class)).isFeatureAvailable(Feature.Video_Landing_Page) && !isPackageAutomotive();
    }

    public boolean isVideoStoriesEnabled() {
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        if (featureGateProvider == null) {
            Log.error(TAG, "Error fetching Feature gate provider");
        }
        return featureGateProvider != null && featureGateProvider.isFeatureAvailable(Feature.Video_Stories) && !isPackageAutomotive() && isVideosWeblabEnabledInNewTerritories();
    }

    public boolean isBOPVideosEnabled() {
        return Feature.BOP_Videos.isEnabled() && !isPackageAutomotive() && isVideosWeblabEnabledInNewTerritories();
    }

    public boolean isVideoToggleEnabled() {
        return !isExplicitLanguageFilterEnabled() && isMusicVideosEnabled() && isVideosWeblabEnabledInNewTerritories();
    }

    private boolean isInSubscriptionMarketplace() {
        return UserSubscriptionUtil.getUserSubscription().isInSubscriptionMarketplace();
    }

    public boolean shouldShowStations() {
        return isInSubscriptionMarketplace();
    }

    public boolean shouldShowStationsRecommendations() {
        return shouldShowStationsRecommendations(AmazonApplication.getContext());
    }

    public boolean shouldShowStationsRecommendations(Context context) {
        return shouldShowStations() && doesMarketSupportFeature(context, "stations_recs_supported_markets") && shouldShowRecommendations();
    }

    public boolean shouldHideArtistLink() {
        return MusicTerritory.INDIA.equalsIgnoreCase(AccountDetailUtil.getMusicTerritoryOfResidence());
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x001f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean isCloudQueuePlaybackEnabled() {
        /*
            r5 = this;
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 0
            r2 = 26
            if (r0 >= r2) goto L8
            return r1
        L8:
            com.amazon.music.featureflag.FeatureFlagProvider r0 = com.amazon.mp3.capability.FeatureFlagsProvider.getFeatureFlagProvider()
            r2 = 1
            if (r0 == 0) goto L1f
            boolean r0 = r0.isCloudQueueEnabled()     // Catch: com.amazon.music.featureflag.FeatureFlagUnavailableException -> L17
            if (r0 == 0) goto L1f
            r0 = r2
            goto L20
        L17:
            r0 = move-exception
            java.lang.String r3 = com.amazon.mp3.capability.Capabilities.TAG
            java.lang.String r4 = "Feature flag not available when checking for CloudQueue."
            com.amazon.mp3.util.Log.error(r3, r4, r0)
        L1f:
            r0 = r1
        L20:
            if (r0 != 0) goto L2a
            com.amazon.mp3.weblab.Weblab r0 = com.amazon.mp3.weblab.Weblab.DM_ANDROID_CLOUD_QUEUE
            boolean r0 = r5.isTreatmentEnabledViaWeblabT1(r0)
            if (r0 == 0) goto L2b
        L2a:
            r1 = r2
        L2b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.capability.Capabilities.isCloudQueuePlaybackEnabled():boolean");
    }

    public boolean isAMConnectUXUpdateEnabled() {
        return isTreatmentEnabledViaWeblabT1(Weblab.DM_MUSICACCESS_AMCONNECT_CASTING_UX_UPDATES_ANDROID);
    }

    public boolean isLyricsSupported() {
        try {
            return FeatureFlagsProvider.getFeatureFlagProvider().canAccessLyrics();
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Feature flag provider unavailable ", e);
            return FeatureFlagsProvider.getNoOpProvider().canAccessLyrics();
        }
    }

    public boolean isStandaloneExperienceSupported(final Context context) {
        return isFeatureEnabledLocally(context, R.bool.StandaloneExperience);
    }

    private boolean doesMarketSupportFeature(Context context, String key) {
        return StringUtil.csvContains(AmazonApplication.getConfiguration(context).getString(key, ""), AccountDetailUtil.get(context).getHomeMarketPlaceId());
    }

    public int defaultPrefetchImageSize() {
        return shouldUseGridView() ? AmazonApplication.getContext().getResources().getDimensionPixelSize(R.dimen.default_gridview_art_size) : AmazonApplication.getContext().getResources().getDimensionPixelSize(R.dimen.default_listview_art_size);
    }

    public static float getHeapSize(Context context) {
        if (sHeapSize == 0.0f) {
            ApplicationInfo appInfo = AmazonApplication.getAppInfo();
            boolean z = (appInfo == null || (appInfo.flags & 1048576) == 0) ? false : true;
            ActivityManager activityManager = (ActivityManager) context.getSystemService(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME);
            sHeapSize = z ? activityManager.getLargeMemoryClass() : activityManager.getMemoryClass();
        }
        return sHeapSize;
    }

    public boolean isMemoryConstrainted() {
        return getHeapSize(AmazonApplication.getContext()) <= 16.0f;
    }

    public boolean shouldForceRefreshButNotOverATC(final Context context) {
        return shouldForceRefreshBrowseTab() && FTUSettingsUtil.customerCompletedATC(context);
    }

    public boolean shouldForceRefreshBrowseTab() {
        return isUnlimitedRecommendationSupported() != isPrimeRecommendationSupported();
    }

    public boolean shouldShowRecommendations() {
        UserSubscription userSubscription = UserSubscriptionUtil.getUserSubscription();
        if (userSubscription.isNightwingOnly()) {
            return isFreeTierRecommendationSupported();
        }
        if (!userSubscription.isPrimeOnly() && AccountDetailUtil.get().isUserInHawkfireMarketplace()) {
            return isUnlimitedRecommendationSupported();
        }
        if (AccountDetailUtil.get().isPrimeMusicSupported()) {
            return isPrimeRecommendationSupported();
        }
        return false;
    }

    private boolean isUnlimitedRecommendationSupported() {
        try {
            return FeatureFlagsProvider.getFeatureFlagProvider().canAccessRecommendationForUnlimited();
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Unable to retrieve state of canAccessRecommendationForUnlimited()", e);
            return false;
        }
    }

    private boolean isPrimeRecommendationSupported() {
        try {
            return FeatureFlagsProvider.getFeatureFlagProvider().canAccessRecommendationForPrime();
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Unable to retrieve state of canAccessRecommendationForPrime()", e);
            return false;
        }
    }

    private boolean isFreeTierRecommendationSupported() {
        try {
            return FeatureFlagsProvider.getFeatureFlagProvider().canAccessRecommendationForFreeTier();
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Unable to retrieve state of canAccessRecommendationForFreeTier()", e);
            return false;
        }
    }

    public boolean secondaryStoragePresent(Context context) {
        StorageState storageLocationState;
        try {
            storageLocationState = StorageLocationFileManager.create(context, Environment.DIRECTORY_MUSIC).getStorageLocationState(ExternalStorageListener.getSecondaryStorageLocation());
        } catch (ExternalStorageNotSupportedException unused) {
            storageLocationState = StorageState.STORAGE_REMOVED;
        }
        return storageLocationState == StorageState.STORAGE_MOUNTED;
    }

    public boolean isSharingSupported() {
        return isInSubscriptionMarketplace();
    }

    public boolean shouldShowStoreIcon() {
        if (BottomNavigationBarContainer.getInstance().isEnabled() && isStoreSupported()) {
            return subscriptionLevelMustShowStoreIcon() || SettingsUtil.getBooleanPref(R.string.setting_key_enable_store);
        }
        return false;
    }

    public boolean isCrashHandlingEnabled(final Context context) {
        return isFeatureEnabledLocally(context, R.bool.CrashHandlingEnabled);
    }

    public boolean isCustomerProfileSupported() {
        try {
            return FeatureFlagsProvider.getFeatureFlagProvider().isCustomerProfileSupported();
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Feature flag provider unavailable ", e);
            return FeatureFlagsProvider.getNoOpProvider().isCustomerProfileSupported();
        }
    }

    public boolean isCustomerProfilePictureUploadSupported() {
        try {
            return FeatureFlagsProvider.getFeatureFlagProvider().isCustomerProfilePictureUploadSupported();
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Feature flag provider unavailable ", e);
            return FeatureFlagsProvider.getNoOpProvider().isCustomerProfilePictureUploadSupported();
        }
    }

    public boolean isActivityFeedSupported() {
        try {
            return FeatureFlagsProvider.getFeatureFlagProvider().isActivityFeedSupported();
        } catch (FeatureFlagUnavailableException unused) {
            return FeatureFlagsProvider.getNoOpProvider().isActivityFeedSupported();
        }
    }

    public boolean isEntityModerationSupported() {
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        return featureGateProvider != null && featureGateProvider.isFeatureAvailable(Feature.entity_moderation);
    }

    public boolean isTrackModerationSupported() {
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        return featureGateProvider != null && featureGateProvider.isFeatureAvailable(Feature.track_moderation);
    }

    public boolean isAlbumModerationSupported() {
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        return featureGateProvider != null && featureGateProvider.isFeatureAvailable(Feature.album_moderation);
    }

    public boolean isAppRatingEnabled(final Context context) {
        return isFeatureEnabledLocally(context, R.bool.AppRatingEnabled);
    }

    public boolean isSignOutSettingEnabled(final Context context) {
        return isFeatureEnabledLocally(context, R.bool.SignOutInSettingEnabled);
    }

    public boolean shouldShowStoreSetting() {
        return isStoreSupported() && !subscriptionLevelMustShowStoreIcon();
    }

    private boolean subscriptionLevelMustShowStoreIcon() {
        return ((FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class)).isFeatureAvailable(Feature.store_icon);
    }

    public boolean isExplicitLanguageFilterSupported() {
        return ExplicitLanguageManager.isEnabled() && AccountRequestUtil.isAccountValid(null);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0028  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean isExplicitLanguageFilterEnabled() {
        /*
            r4 = this;
            com.amazon.mp3.util.ChildUserUtil r0 = com.amazon.mp3.util.ChildUserUtil.INSTANCE
            com.amazon.mp3.AmazonApplication r1 = com.amazon.mp3.AmazonApplication.getApplication()
            android.content.Context r1 = r1.getApplicationContext()
            boolean r0 = r0.isChildUser(r1)
            r1 = 1
            if (r0 == 0) goto L12
            return r1
        L12:
            r0 = 0
            com.amazon.music.account.AccountManager r2 = com.amazon.music.account.AccountManagerSingleton.get()     // Catch: com.amazon.music.account.MusicAccountNotCreatedException -> L2b com.amazon.music.account.DataNotReadyException -> L2d
            com.amazon.music.account.User r2 = r2.getUser()     // Catch: com.amazon.music.account.MusicAccountNotCreatedException -> L2b com.amazon.music.account.DataNotReadyException -> L2d
            boolean r2 = r2.isExplicitFilterEnabledOnDevice()     // Catch: com.amazon.music.account.MusicAccountNotCreatedException -> L2b com.amazon.music.account.DataNotReadyException -> L2d
            if (r2 == 0) goto L28
            boolean r2 = r4.isExplicitLanguageFilterSupported()     // Catch: com.amazon.music.account.MusicAccountNotCreatedException -> L2b com.amazon.music.account.DataNotReadyException -> L2d
            if (r2 == 0) goto L28
            goto L29
        L28:
            r1 = r0
        L29:
            r0 = r1
            goto L35
        L2b:
            r1 = move-exception
            goto L2e
        L2d:
            r1 = move-exception
        L2e:
            java.lang.String r2 = com.amazon.mp3.capability.Capabilities.TAG
            java.lang.String r3 = "User data not ready"
            com.amazon.mp3.util.Log.error(r2, r3, r1)
        L35:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.capability.Capabilities.isExplicitLanguageFilterEnabled():boolean");
    }

    public boolean isArtistFollowSupported() {
        try {
            return FeatureFlagsProvider.getFeatureFlagProvider().isArtistFollowSupported();
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Feature flag provider unavailable ", e);
            return FeatureFlagsProvider.getNoOpProvider().isArtistFollowSupported();
        }
    }

    public boolean isRecentsSyncSupported() {
        try {
            return FeatureFlagsProvider.getFeatureFlagProvider().isRecentsSyncSupported();
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Feature flag provider unavailable ", e);
            return FeatureFlagsProvider.getNoOpProvider().isRecentsSyncSupported();
        }
    }

    public boolean isDeviceSDCapable(final Context context) {
        if (Environment.isExternalStorageRemovable()) {
            return true;
        }
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (defaultSharedPreferences.getBoolean(context.getString(R.string.setting_key_sd_card_status), false)) {
            return true;
        }
        if (!AmazonApplication.getCapabilities().secondaryStoragePresent(context)) {
            return false;
        }
        defaultSharedPreferences.edit().putBoolean(context.getString(R.string.setting_key_sd_card_status), true).apply();
        return true;
    }

    public boolean isEqualizerSupported() {
        try {
            return FeatureFlagsProvider.getFeatureFlagProvider().isHarleyEqualizerSupported();
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Feature flag provider unavailable ", e);
            return FeatureFlagsProvider.getNoOpProvider().isHarleyEqualizerSupported();
        }
    }

    public boolean shouldDisableLyrics(Context context) {
        return Marketplace.JAPAN.equals(AccountDetailUtil.get(context).getHomeMarketPlace()) && !ConnectivityUtil.isAvailable(context);
    }

    public boolean isRecentTracksSyncSupported() {
        try {
            return FeatureFlagsProvider.getFeatureFlagProvider().isRecentTracksSyncSupported();
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Feature flag provider unavailable ", e);
            return FeatureFlagsProvider.getNoOpProvider().isRecentTracksSyncSupported();
        }
    }

    public boolean isMarketIdCheckedForLyricsSupport() {
        try {
            return FeatureFlagsProvider.getFeatureFlagProvider().isMarketPlaceCheckForLyricsSupported();
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Feature flag provider unavailable ", e);
            return FeatureFlagsProvider.getNoOpProvider().isMarketPlaceCheckForLyricsSupported();
        }
    }

    public boolean isAppRatingSupported() {
        try {
            return FeatureFlagsProvider.getFeatureFlagProvider().isAppRatingSupported();
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Feature flag provider unavailable ", e);
            return FeatureFlagsProvider.getNoOpProvider().isAppRatingSupported();
        }
    }

    public boolean isKatanaSupported() {
        return UserSubscriptionUtil.getUserSubscription().isKatana();
    }

    public boolean areIvyRefinementPillsEnabled() {
        try {
            return FeatureFlagsProvider.getFeatureFlagProvider().areIvyRefinementPillsEnabled();
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Feature flag provider unavailable ", e);
            return FeatureFlagsProvider.getNoOpProvider().areIvyRefinementPillsEnabled();
        }
    }

    public boolean shouldSuppressIvyCatalogNonTrackAlbumFiltering() {
        try {
            return FeatureFlagsProvider.getFeatureFlagProvider().shouldSuppressIvyCatalogNonTrackAlbumFiltering();
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Feature flag provider unavailable ", e);
            return FeatureFlagsProvider.getNoOpProvider().shouldSuppressIvyCatalogNonTrackAlbumFiltering();
        }
    }

    public boolean isSearchResultsCachingEnabled() {
        return ((FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class)).isFeatureAvailable(FindFeatureGating.CACHE_SEARCH_RESULTS);
    }

    public boolean isTrackPopularityBarSupported() {
        try {
            return FeatureFlagsProvider.getFeatureFlagProvider().isTrackPopularityBarSupported();
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Feature flag provider unavailable ", e);
            return FeatureFlagsProvider.getNoOpProvider().isTrackPopularityBarSupported();
        }
    }

    public boolean isAlbumBadgingInLibrarySupported() {
        try {
            return FeatureFlagsProvider.getFeatureFlagProvider().isAlbumBadgingInLibrarySupported();
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Feature flag provider unavailable ", e);
            return FeatureFlagsProvider.getNoOpProvider().isAlbumBadgingInLibrarySupported();
        }
    }

    public boolean isLibrarySyncApiDialing() {
        try {
            return FeatureFlagsProvider.getFeatureFlagProvider().isLibrarySyncApiDialing();
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Feature flag provider unavailable ", e);
            return FeatureFlagsProvider.getNoOpProvider().isLibrarySyncApiDialing();
        }
    }

    public boolean isPurchasedTrackPlaybackInKatanaQualitySupported() {
        try {
            return FeatureFlagsProvider.getFeatureFlagProvider().isPurchasedTrackPlaybackInKatanaQualitySupported();
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Feature flag provider unavailable ", e);
            return FeatureFlagsProvider.getNoOpProvider().isPurchasedTrackPlaybackInKatanaQualitySupported();
        }
    }

    public boolean isBadgingInRecentsTrackSupported() {
        try {
            return FeatureFlagsProvider.getFeatureFlagProvider().isBadgingInRecentsTrackSupported();
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Feature flag provider unavailable ", e);
            return FeatureFlagsProvider.getNoOpProvider().isBadgingInRecentsTrackSupported();
        }
    }

    public boolean shouldUploadLogAfterCrash() {
        try {
            boolean zShouldUploadLogAfterCrash = FeatureFlagsProvider.getFeatureFlagProvider().shouldUploadLogAfterCrash();
            Log.debug(TAG, "Arcus Feature Flag UPLOAD_LOG_AFTER_CRASH: %b", Boolean.valueOf(zShouldUploadLogAfterCrash));
            return zShouldUploadLogAfterCrash;
        } catch (FeatureFlagUnavailableException e) {
            Log.warning(TAG, "The UPLOAD_LOG_AFTER_CRASH feature flag is unavailable.", e);
            return FeatureFlagsProvider.getNoOpProvider().shouldUploadLogAfterCrash();
        }
    }

    public boolean isCompressedCoralRequestsSupported() {
        try {
            boolean zIsCompressedCoralRequestsSupported = FeatureFlagsProvider.getFeatureFlagProvider().isCompressedCoralRequestsSupported();
            Log.debug(TAG, "Arcus Feature Flag COMPRESSED_CORAL_REQUESTS: %b", Boolean.valueOf(zIsCompressedCoralRequestsSupported));
            return zIsCompressedCoralRequestsSupported;
        } catch (FeatureFlagUnavailableException e) {
            Log.warning(TAG, "The COMPRESSED_CORAL_REQUESTS feature flag is unavailable.", e);
            return FeatureFlagsProvider.getNoOpProvider().shouldUploadLogAfterCrash();
        }
    }

    public int getAppRatingNoIncidentThreshold() {
        try {
            int appRatingNoIncidentThreshold = FeatureFlagsProvider.getFeatureFlagProvider().getAppRatingNoIncidentThreshold();
            Log.debug(TAG, "Arcus Feature Flag APP_RATING_NO_INCIDENT_THRESHOLD: %d", Integer.valueOf(appRatingNoIncidentThreshold));
            return appRatingNoIncidentThreshold;
        } catch (FeatureFlagUnavailableException e) {
            Log.warning(TAG, "The APP_RATING_NO_INCIDENT_THRESHOLD feature flag is unavailable.", e);
            return FeatureFlagsProvider.getNoOpProvider().getAppRatingNoIncidentThreshold();
        }
    }

    public boolean isPipEnabled() {
        return Build.VERSION.SDK_INT >= 26 && AmazonApplication.getApplication().getPackageManager().hasSystemFeature("android.software.picture_in_picture");
    }

    public boolean isWoodstockEnabled() {
        try {
            boolean zIsWoodstockEnabled = FeatureFlagsProvider.getFeatureFlagProvider().isWoodstockEnabled();
            Log.debug(TAG, "Arcus Feature Flag WOODSTOCK_ENABLED: %b", Boolean.valueOf(zIsWoodstockEnabled));
            return zIsWoodstockEnabled;
        } catch (FeatureFlagUnavailableException e) {
            Log.warning(TAG, "The WOODSTOCK_ENABLED feature flag is unavailable.", e);
            return FeatureFlagsProvider.getNoOpProvider().isWoodstockEnabled();
        }
    }

    public boolean isMerchEnabled() {
        try {
            boolean zIsMerchEnabled = FeatureFlagsProvider.getFeatureFlagProvider().isMerchEnabled();
            Log.debug(TAG, "Feature Flag MERCH_ENABLED: %b", Boolean.valueOf(zIsMerchEnabled));
            return zIsMerchEnabled;
        } catch (FeatureFlagUnavailableException e) {
            Log.warning(TAG, "The MERCH_ENABLED feature flag is unavailable.", e);
            return FeatureFlagsProvider.getNoOpProvider().isMerchEnabled();
        }
    }

    public boolean isLiveEventEnabled() {
        try {
            boolean zIsLiveEventEnabled = FeatureFlagsProvider.getFeatureFlagProvider().isLiveEventEnabled();
            Log.debug(TAG, "Feature Flag LIVE_EVENT_ENABLED: %b", Boolean.valueOf(zIsLiveEventEnabled));
            return zIsLiveEventEnabled;
        } catch (FeatureFlagUnavailableException e) {
            Log.warning(TAG, "The LIVE_EVENT_ENABLED feature flag is unavailable.", e);
            return FeatureFlagsProvider.getNoOpProvider().isLiveEventEnabled();
        }
    }

    public boolean isMusicNeverStopsEnabled() {
        try {
            return FeatureFlagsProvider.getFeatureFlagProvider().isMusicNeverStopsEnabled();
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Feature flag provider unavailable ", e);
            return FeatureFlagsProvider.getNoOpProvider().isMusicNeverStopsEnabled();
        }
    }

    public boolean isDownloadTrackButtonEnabled() {
        try {
            boolean zIsDownloadTrackButtonEnabled = FeatureFlagsProvider.getFeatureFlagProvider().isDownloadTrackButtonEnabled();
            Log.debug(TAG, "Arcus Feature Flag DOWNLOAD_TRACK_BUTTON: %b", Boolean.valueOf(zIsDownloadTrackButtonEnabled));
            if (!zIsDownloadTrackButtonEnabled || !UserSubscriptionUtil.getUserSubscription().isPrime()) {
                return false;
            }
            ExperimentWeblabs experimentWeblabs = ExperimentWeblabs.DM_LPN_OFFLINE_DOWNLOAD_BUTTON;
            try {
                LegacyWeblabController.getInstance().getTreatmentAndRecordTrigger(experimentWeblabs.getAaWeblab());
                return LegacyWeblabController.getInstance().getTreatmentAndRecordTrigger(experimentWeblabs.getAbWeblab()) == WeblabTreatment.T1;
            } catch (IllegalStateException e) {
                Log.warning(TAG, "The weblab %s is not defined: %s", experimentWeblabs, e.getMessage());
                return false;
            }
        } catch (FeatureFlagUnavailableException e2) {
            Log.error(TAG, "Feature flag provider unavailable ", e2);
            return FeatureFlagsProvider.getNoOpProvider().isDownloadTrackButtonEnabled();
        }
    }

    public int getHybridQueueLookaheadWindowSize() {
        try {
            int hybridQueueLookaheadWindowSize = FeatureFlagsProvider.getFeatureFlagProvider().getHybridQueueLookaheadWindowSize();
            Log.debug(TAG, "Arcus Feature Flag HYBRID_QUEUE_LOOKAHEAD_WINDOW_SIZE: %d", Integer.valueOf(hybridQueueLookaheadWindowSize));
            return hybridQueueLookaheadWindowSize;
        } catch (FeatureFlagUnavailableException e) {
            Log.warning(TAG, "The HYBRID_QUEUE_LOOKAHEAD_WINDOW_SIZE feature flag is unavailable.", e);
            return FeatureFlagsProvider.getNoOpProvider().getHybridQueueLookaheadWindowSize();
        }
    }

    public int getHybridQueueBatchSize() {
        try {
            int hybridQueueBatchSize = FeatureFlagsProvider.getFeatureFlagProvider().getHybridQueueBatchSize();
            Log.debug(TAG, "Arcus Feature Flag HYBRID_QUEUE_BATCH_SIZE: %d", Integer.valueOf(hybridQueueBatchSize));
            return hybridQueueBatchSize;
        } catch (FeatureFlagUnavailableException e) {
            Log.warning(TAG, "The HYBRID_QUEUE_BATCH_SIZE feature flag is unavailable.", e);
            return FeatureFlagsProvider.getNoOpProvider().getHybridQueueBatchSize();
        }
    }

    public boolean isIoTSkipLimitSupported() {
        try {
            boolean zIsIoTSkipLimitSupported = FeatureFlagsProvider.getFeatureFlagProvider().isIoTSkipLimitSupported();
            Log.debug(TAG, "Arcus Feature Flag IOT_SKIP_LIMIT: %b", Boolean.valueOf(zIsIoTSkipLimitSupported));
            return zIsIoTSkipLimitSupported;
        } catch (FeatureFlagUnavailableException e) {
            Log.warning(TAG, "The IOT_SKIP_LIMIT feature flag is unavailable.", e);
            return FeatureFlagsProvider.getNoOpProvider().isIoTSkipLimitSupported();
        }
    }

    public boolean isFollowArtistsWidgetEnabled() {
        try {
            return FeatureFlagsProvider.getFeatureFlagProvider().isFollowArtistsWidgetEnabled();
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Feature flag provider unavailable ", e);
            return FeatureFlagsProvider.getNoOpProvider().isFollowArtistsWidgetEnabled();
        }
    }

    public boolean isFreeTierUserPlaylistEditingEnabled() {
        try {
            boolean zIsFreeTierUserPlaylistEditingEnabled = FeatureFlagsProvider.getFeatureFlagProvider().isFreeTierUserPlaylistEditingEnabled();
            Log.debug(TAG, "Arcus Feature Flag FREE_TIER_USER_PLAYLIST_EDITING: %b", Boolean.valueOf(zIsFreeTierUserPlaylistEditingEnabled));
            return zIsFreeTierUserPlaylistEditingEnabled;
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Feature flag provider unavailable ", e);
            return FeatureFlagsProvider.getNoOpProvider().isFreeTierUserPlaylistEditingEnabled();
        }
    }

    public boolean isFreeTierContextualUpsellsEnabled() {
        if (!UserSubscriptionUtil.getUserSubscription().isNightwingOnly()) {
            return false;
        }
        try {
            boolean zIsFreeTierContextualUpsellsEnabled = FeatureFlagsProvider.getFeatureFlagProvider().isFreeTierContextualUpsellsEnabled();
            Log.debug(TAG, "Arcus Feature Flag FREE_TIER_CONTEXTUAL_UPSELL: %b", Boolean.valueOf(zIsFreeTierContextualUpsellsEnabled));
            return zIsFreeTierContextualUpsellsEnabled;
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Unable to retrieve state of isFreeTierContextualUpsellsEnabled()", e);
            return FeatureFlagsProvider.getNoOpProvider().isFreeTierContextualUpsellsEnabled();
        }
    }

    public boolean isAlexaEmpPlaybackEnabled() {
        return AlexaFeatureGating.ALEXA_EMP_PLAYBACK.isEnabled();
    }

    public boolean isFireOS(final Context context) {
        return isFeatureEnabledLocally(context, R.bool.FireOS);
    }

    public boolean isBauhausTopAppBarEnabled() {
        try {
            return FeatureFlagsProvider.getFeatureFlagProvider().isBauhausTopAppBarEnabled();
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Feature flag provider unavailable ", e);
            return FeatureFlagsProvider.getNoOpProvider().isBauhausTopAppBarEnabled();
        }
    }

    public boolean isBauhausInAppMessagingEnabled() {
        try {
            return FeatureFlagsProvider.getFeatureFlagProvider().isBauhausInAppMessagingEnabled();
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Feature flag provider unavailable ", e);
            return FeatureFlagsProvider.getNoOpProvider().isBauhausInAppMessagingEnabled();
        }
    }

    public boolean isPodcastEnabled() {
        try {
            return FeatureFlagsProvider.getFeatureFlagProvider().isPodcastEnabled();
        } catch (FeatureFlagUnavailableException unused) {
            Log.error(TAG, "Feature flag not available when checking for Podcasts");
            return FeatureFlagsProvider.getNoOpProvider().isPodcastEnabled();
        }
    }

    public boolean isSpatialAudioEnabled(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(context.getString(R.string.dmusic_ivy_is_spatial_audio_launched_key), false) && UserSubscriptionUtil.isHawkfireKatana();
    }

    public boolean isTrackLookupBrowseContentSelectorAllStreamable() {
        WeblabProvider weblabProvider = (WeblabProvider) Providers.INSTANCE.get(WeblabProvider.class);
        return weblabProvider != null && weblabProvider.getTreatment(Weblab.DM_TRACKLOOKUP_BROWSECONTENTSELECTOR_ALLSTREAMABLE.toString(), true) == WeblabTreatment.T1;
    }

    private boolean isTreatmentEnabledViaWeblabT1(final Weblab weblabName) {
        WeblabProvider weblabProvider = (WeblabProvider) Providers.INSTANCE.get(WeblabProvider.class);
        return weblabProvider != null && weblabProvider.getTreatment(weblabName.toString(), true) == WeblabTreatment.T1;
    }

    private boolean isVideosWeblabEnabledInNewTerritories() {
        if (!MusicTerritory.INDIA.equals(AccountDetailUtil.getMusicTerritoryOfResidence()) && !MusicTerritory.CANADA.equals(AccountDetailUtil.getMusicTerritoryOfResidence())) {
            return true;
        }
        WeblabProvider weblabProvider = (WeblabProvider) Providers.INSTANCE.get(WeblabProvider.class);
        return weblabProvider != null && weblabProvider.getTreatment("DM_VIDEO_STARLIGHT_677159", true) == WeblabTreatment.T1;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0052  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean isNPSLikesSupported() {
        /*
            r5 = this;
            com.amazon.music.featureflag.FeatureFlagProvider r0 = com.amazon.mp3.capability.FeatureFlagsProvider.getFeatureFlagProvider()     // Catch: com.amazon.music.featureflag.FeatureFlagUnavailableException -> L9
            boolean r0 = r0.isNPSLikesSupported()     // Catch: com.amazon.music.featureflag.FeatureFlagUnavailableException -> L9
            goto L11
        L9:
            com.amazon.mp3.capability.NoOpFeatureFlagProvider r0 = com.amazon.mp3.capability.FeatureFlagsProvider.getNoOpProvider()
            boolean r0 = r0.isNPSLikesSupported()
        L11:
            com.amazon.music.platform.locator.Providers r1 = com.amazon.music.platform.locator.Providers.INSTANCE
            java.lang.Class<com.amazon.music.platform.providers.WeblabProvider> r2 = com.amazon.music.platform.providers.WeblabProvider.class
            com.amazon.music.platform.providers.InterfaceProvider r1 = r1.get(r2)
            com.amazon.music.platform.providers.WeblabProvider r1 = (com.amazon.music.platform.providers.WeblabProvider) r1
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L52
            boolean r4 = com.amazon.mp3.util.PlatformUtil.isFireOS()
            if (r4 == 0) goto L39
            com.amazon.mp3.weblab.Weblab r4 = com.amazon.mp3.weblab.Weblab.DM_CURATE_LIKES_ON_NOW_PLAYING_FIRE_TABLET_AA
            java.lang.String r4 = r4.toString()
            r1.getTreatment(r4, r3)
            com.amazon.mp3.weblab.Weblab r4 = com.amazon.mp3.weblab.Weblab.DM_CURATE_LIKES_ON_NOW_PLAYING_FIRE_TABLET_AB
            java.lang.String r4 = r4.toString()
            com.amazon.music.platform.data.WeblabTreatment r1 = r1.getTreatment(r4, r3)
            goto L4c
        L39:
            com.amazon.mp3.weblab.Weblab r4 = com.amazon.mp3.weblab.Weblab.DM_CURATE_LIKES_ON_NOW_PLAYING_AA
            java.lang.String r4 = r4.toString()
            r1.getTreatment(r4, r3)
            com.amazon.mp3.weblab.Weblab r4 = com.amazon.mp3.weblab.Weblab.DM_CURATE_LIKES_ON_NOW_PLAYING_AB
            java.lang.String r4 = r4.toString()
            com.amazon.music.platform.data.WeblabTreatment r1 = r1.getTreatment(r4, r3)
        L4c:
            com.amazon.music.platform.data.WeblabTreatment r4 = com.amazon.music.platform.data.WeblabTreatment.T1
            if (r4 != r1) goto L52
            r1 = r3
            goto L53
        L52:
            r1 = r2
        L53:
            if (r0 != 0) goto L57
            if (r1 == 0) goto L58
        L57:
            r2 = r3
        L58:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.capability.Capabilities.isNPSLikesSupported():boolean");
    }

    public boolean isLikesEverywhereEnabled() {
        return isLikesEverywhereEnabled(false);
    }

    public boolean isLikesEverywhereEnabled(boolean useCachedValues) {
        if (PlatformUtil.isFireOS()) {
            return CurateFeatureGating.LIKES_EVERYWHERE_FIREOS.isEnabled();
        }
        if (useCachedValues) {
            return isFeatureEnabled(CurateFeatureGating.LIKES_EVERYWHERE_ANDROID, true);
        }
        return CurateFeatureGating.LIKES_EVERYWHERE_ANDROID.isEnabled();
    }

    public boolean isTrueLikesAndFollowsEnabled() {
        if (PlatformUtil.isFireOS()) {
            return CurateFeatureGating.TRUE_LIKES_AND_FOLLOWS_FIRE_OS.isEnabled();
        }
        return CurateFeatureGating.TRUE_LIKES_AND_FOLLOWS_ANDROID.isEnabled();
    }

    private boolean isFeatureEnabled(final com.amazon.music.platform.featuregate.Feature feature, final boolean useCache) {
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        if (featureGateProvider == null) {
            Log.error(TAG, "Error fetching feature gate provider got null: ${this.name}");
            return false;
        }
        if (featureGateProvider instanceof CachedFeatureFlagProviderImpl) {
            return ((CachedFeatureFlagProviderImpl) featureGateProvider).isFeatureAvailable(feature, useCache);
        }
        return featureGateProvider.isFeatureAvailable(feature);
    }

    public boolean isTrickPlaySupprted() {
        if (!(isMusicVideosEnabled() || isBOPVideosEnabled())) {
            return false;
        }
        try {
            return FeatureFlagsProvider.getFeatureFlagProvider().isTrickPlaySupported();
        } catch (FeatureFlagUnavailableException unused) {
            return FeatureFlagsProvider.getNoOpProvider().isTrickPlaySupported();
        }
    }

    public boolean isTopVideoRecEnabled() {
        try {
            return FeatureFlagsProvider.getFeatureFlagProvider().isTopVideoRecEnabled();
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Feature flag provider unavailable ", e);
            return FeatureFlagsProvider.getNoOpProvider().isTopVideoRecEnabled();
        }
    }

    public boolean isBauhausADPEnabled() {
        try {
            if (FeatureFlagsProvider.getFeatureFlagProvider().isBauhausADPEnabled()) {
                if (!SettingsActivity.shouldDisableBauhausPages(AmazonApplication.getApplication().getApplicationContext())) {
                    return true;
                }
            }
            return false;
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Feature flag provider unavailable ", e);
            return FeatureFlagsProvider.getNoOpProvider().isBauhausADPEnabled();
        }
    }

    public boolean isBauhausLibraryArtistDPEnabled() {
        try {
            if (FeatureFlagsProvider.getFeatureFlagProvider().isBauhausLibraryArtistDPEnabled()) {
                if (!SettingsActivity.shouldDisableBauhausPages(AmazonApplication.getApplication().getApplicationContext())) {
                    return true;
                }
            }
            return false;
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Feature flag provider unavailable ", e);
            return FeatureFlagsProvider.getNoOpProvider().isBauhausLibraryArtistDPEnabled();
        }
    }

    public boolean isBauhausPlaylistDetailEnabled() {
        try {
            return FeatureFlagsProvider.getFeatureFlagProvider().isBauhausPlaylistDetailPageEnabled();
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Feature flag provider unavailable ", e);
            return FeatureFlagsProvider.getNoOpProvider().isBauhausPlaylistDetailPageEnabled();
        }
    }

    public boolean isBauhausLibraryEnabled() {
        try {
            if (FeatureFlagsProvider.getFeatureFlagProvider().isBauhausLibraryEnabled()) {
                if (!SettingsActivity.shouldDisableBauhausPages(AmazonApplication.getApplication().getApplicationContext())) {
                    return true;
                }
            }
            return false;
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Feature flag provider unavailable ", e);
            return FeatureFlagsProvider.getNoOpProvider().isBauhausLibraryEnabled();
        }
    }

    public boolean shouldEnablePaginationForBauhausPages() {
        try {
            return FeatureFlagsProvider.getFeatureFlagProvider().isBauhausPaginationEnabled();
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Feature flag provider unavailable ", e);
            return FeatureFlagsProvider.getNoOpProvider().isBauhausPaginationEnabled();
        }
    }

    public boolean isAppDisplayLangSettingEnabled() {
        try {
            boolean z = FeatureFlagsProvider.getFeatureFlagProvider().isAppDisplayLangSettingEnabled() && !AmazonApplication.getApplication().getApplicationContext().getPackageName().equals("com.amazon.mp3.automotiveOS");
            Log.debug(TAG, "Arcus Feature Flag MANAGE_APP_DISPLAY_LANGUAGE: %b", Boolean.valueOf(z));
            return z;
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Feature flag provider unavailable ", e);
            return FeatureFlagsProvider.getNoOpProvider().isAppDisplayLangSettingEnabled();
        }
    }

    public boolean isRetailLopSyncNotificationEnabled() {
        try {
            boolean zIsRetailLopSyncNotificationEnabled = FeatureFlagsProvider.getFeatureFlagProvider().isRetailLopSyncNotificationEnabled();
            Log.debug(TAG, "Arcus Feature Flag MANAGE_RETAIL_LOP_SYNC_NOTIFICATION: %b", Boolean.valueOf(zIsRetailLopSyncNotificationEnabled));
            return zIsRetailLopSyncNotificationEnabled;
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Feature flag provider unavailable ", e);
            return FeatureFlagsProvider.getNoOpProvider().isRetailLopSyncNotificationEnabled();
        }
    }

    public boolean isINLanguageExpansionSupported() {
        try {
            boolean zIsINLanguageExpansionSupported = FeatureFlagsProvider.getFeatureFlagProvider().isINLanguageExpansionSupported();
            Log.debug(TAG, "Arcus Feature Flag IN_EXTENDED_LANGUAGE_LIST: %b", Boolean.valueOf(zIsINLanguageExpansionSupported));
            return zIsINLanguageExpansionSupported;
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Feature flag provider unavailable ", e);
            return FeatureFlagsProvider.getNoOpProvider().isINLanguageExpansionSupported();
        }
    }

    public boolean isIngressPillsEnabled(final Context context) {
        if (ChildUserUtil.INSTANCE.isChildUser(context)) {
            Log.debug(TAG, "Ingress pills are hidden since user is a Child User");
            return false;
        }
        boolean zIsEnabled = PodcastFeatureGating.PODCAST.isEnabled();
        Log.debug(TAG, "Arcus Feature Flag PODCAST_ENABLED: %b", Boolean.valueOf(zIsEnabled));
        return zIsEnabled;
    }

    public boolean isEntityPlaylistSupported() {
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        if (featureGateProvider != null) {
            return featureGateProvider.isFeatureAvailable(DynamicPlaylistFeatureGating.supports_dynamic_playlists);
        }
        return false;
    }

    public boolean isEntityPlaylistAutoPlaybackSupported() {
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        if (featureGateProvider != null) {
            return featureGateProvider.isFeatureAvailable(DynamicPlaylistFeatureGating.dynamic_playlists_t3);
        }
        return false;
    }

    public boolean isCarModeAutoPlayFirstPresetDisabled() {
        return Feature.car_mode_auto_play.isEnabled();
    }

    public boolean isCarModeV2Enabled() {
        return Feature.car_mode_v2.isEnabled();
    }

    public boolean isCarModePodcastEpisodeListEnabled() {
        return Feature.car_mode_podcast_episode_list.isEnabled();
    }

    public boolean isPodcastHarleyEnabled() {
        return PodcastFeatureGating.PODCAST_ON_HARLEY.isEnabled() && PodcastFeatureGating.PODCAST_ON_HARLEY_WEBLAB.isEnabled();
    }

    public boolean isPodcastTranscriptFeatureEnabled() {
        try {
            return FeatureFlagsProvider.getFeatureFlagProvider().isPodcastScrubTranscriptionEnabled();
        } catch (FeatureFlagUnavailableException unused) {
            Log.error(TAG, "Feature flag not available when checking for Podcast Transcript Feature");
            return false;
        }
    }

    public boolean isHyperloopCapabilityEnabled() {
        return AlexaFeatureGating.HYPERLOOP_CAPABILITY.isEnabled();
    }

    public boolean isOnDemandPlaylistsEnabled() {
        return FMPMFeatureGating.ON_DEMAND_PLAYLISTS.isEnabled();
    }

    public boolean isShowTrackDetailsEnabled() {
        return FMPMFeatureGating.SHOW_TRACK_DETAILS_TRACK_SFA.isEnabled() || FMPMFeatureGating.SHOW_TRACK_DETAILS_ARTIST_AS.isEnabled();
    }

    public boolean isAOMTextCapabilityEnabled() {
        return AlexaFeatureGating.AOM_TEXT_CAPABILITY.isEnabled();
    }

    public boolean isRelatedEntitySearchEnabled() {
        return FindFeatureGating.RELATED_ENTITY_SEARCH.isEnabled((FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class));
    }

    public boolean isCommunityPlaylistEnabled() {
        FindFeatureGating findFeatureGating = FindFeatureGating.COMMUNITY_PLAYLIST_ENABLED;
        Providers providers = Providers.INSTANCE;
        return findFeatureGating.isEnabled((FeatureGateProvider) providers.get(FeatureGateProvider.class)) || FindFeatureGating.COMMUNITY_PLAYLIST_FREE_TIER_ENABLED.isEnabled((FeatureGateProvider) providers.get(FeatureGateProvider.class));
    }

    public boolean isBluetoothSearchEnabled() {
        return FindFeatureGating.BLUETOOTH_SEARCH_ENABLED.isEnabled((FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class));
    }

    public boolean isAlexaSearchFindBrowseEnabled() {
        return FindFeatureGating.ALEXA_SEARCH_FIND_BROWSE.isEnabled((FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class));
    }

    public boolean isAlexaSearchOrdinalPlaybackEnabled() {
        return FindFeatureGating.ALEXA_SEARCH_ORDINAL_PLAYBACK.isEnabled((FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class));
    }

    public boolean isAlexaSearchFindBrowseInCarModeEnabled() {
        return FindFeatureGating.ALEXA_SEARCH_FIND_BROWSE_CAR_MODE.isEnabled((FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class));
    }

    public boolean isSkyfireSearchFragmentEnabled() {
        return FindFeatureGating.SEARCH_SKYFIRE_FRAGMENT.isEnabled((FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class));
    }

    public boolean isSkyfireFindLandingFragmentEnabled() {
        return FindFeatureGating.FIND_LANDING_SKYFIRE_FRAGMENT.isEnabled((FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class));
    }

    public boolean isSkyfireFindLandingPageGroupingsAndCategoriesEnabled() {
        return FindFeatureGating.FIND_LANDING_SKYFIRE_GROUPINGS_CATEGORIES.isEnabled((FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class));
    }

    public static boolean isPrioritizeDownloadsSupported() {
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        if (featureGateProvider == null) {
            Log.error(TAG, "Error fetching Feature gate provider");
        }
        return featureGateProvider != null && featureGateProvider.isFeatureAvailable(Feature.prioritize_downloads);
    }

    public boolean isCloudQueueEnabledForAOM(final ControlSource controlSource) {
        if (!isCloudQueuePlaybackEnabled()) {
            return false;
        }
        if (controlSource.equals(ControlSource.ALEXA)) {
            return AlexaFeatureGating.DM_AOM_ANDROID_CQE_LAUNCH.isEnabled();
        }
        return true;
    }

    public boolean isAOMHindiSupportEnabled() {
        return AlexaFeatureGating.AOM_HINDI_SUPPORT.isEnabled();
    }

    public boolean isEarlyUseNotificationEnabled() {
        try {
            return FeatureFlagsProvider.getFeatureFlagProvider().isEarlyUseNotificationEnabled();
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Feature flag provider unavailable ", e);
            return false;
        }
    }

    public boolean isFreeTierScrubbingOnDemandPlaylistsEnabled() {
        if (!UserSubscriptionUtil.getUserSubscription().isNightwingOnly()) {
            return false;
        }
        try {
            boolean zIsFreeTierScrubbingOnDemandPlaylistsEnabled = FeatureFlagsProvider.getFeatureFlagProvider().isFreeTierScrubbingOnDemandPlaylistsEnabled();
            Log.debug(TAG, "Arcus Feature Flag FREE_TIER_SCRUBBING_ON_DEMAND_PLAYLISTS: %b", Boolean.valueOf(zIsFreeTierScrubbingOnDemandPlaylistsEnabled));
            return zIsFreeTierScrubbingOnDemandPlaylistsEnabled;
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Unable to retrieve state of isFreeTierScrubbingOnDemandPlaylistsEnabled()", e);
            return FeatureFlagsProvider.getNoOpProvider().isFreeTierScrubbingOnDemandPlaylistsEnabled();
        }
    }

    public boolean isFreeTierSkipLimitEducationalTooltipEnabled() {
        if (!UserSubscriptionUtil.getUserSubscription().isNightwingOnly()) {
            return false;
        }
        try {
            boolean zIsFreeTierSkipLimitEducationalTooltipEnabled = FeatureFlagsProvider.getFeatureFlagProvider().isFreeTierSkipLimitEducationalTooltipEnabled();
            Log.debug(TAG, "Arcus Feature Flag FREE_TIER_SKIP_LIMIT_EDUCATIONAL_TOOLTIP: %b", Boolean.valueOf(zIsFreeTierSkipLimitEducationalTooltipEnabled));
            return zIsFreeTierSkipLimitEducationalTooltipEnabled;
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Unable to retrieve state of isFreeTierSkipLimitEducationalTooltipEnabled()", e);
            return FeatureFlagsProvider.getNoOpProvider().isFreeTierSkipLimitEducationalTooltipEnabled();
        }
    }

    public boolean isFreeTierSkipsRemainingTooltipEnabled() {
        if (!Feature.is_skips_remaining_tooltip_enabled.isEnabled()) {
            return false;
        }
        try {
            boolean zIsFreeTierSkipsRemainingTooltipEnabled = FeatureFlagsProvider.getFeatureFlagProvider().isFreeTierSkipsRemainingTooltipEnabled();
            Log.debug(TAG, "Arcus Feature Flag FREE_TIER_SKIPS_REMAINING_TOOLTIP: %b", Boolean.valueOf(zIsFreeTierSkipsRemainingTooltipEnabled));
            return zIsFreeTierSkipsRemainingTooltipEnabled;
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Unable to retrieve state of isFreeTierSkipsRemainingTooltipEnabled()", e);
            return FeatureFlagsProvider.getNoOpProvider().isFreeTierSkipsRemainingTooltipEnabled();
        }
    }

    public boolean isSonicRushEnabled() {
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        if (featureGateProvider == null) {
            return false;
        }
        return featureGateProvider.isFeatureAvailable(Feature.sonic_rush);
    }

    public boolean eligibilityBasedPlaybackIcon() {
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        if (featureGateProvider == null) {
            return false;
        }
        return featureGateProvider.isFeatureAvailable(Feature.eligibility_based_playback_icon);
    }

    public boolean isYAImmerseEnabled() {
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        if (ScreenUtil.isLargeOrExtraLargeScreen()) {
            return featureGateProvider.isFeatureAvailable(Feature.ya_immerse) && featureGateProvider.isFeatureAvailable(Feature.ya_immerse_on_large_screen_devices);
        }
        return featureGateProvider.isFeatureAvailable(Feature.ya_immerse);
    }

    public boolean isPlaylistsSortingEnabled() {
        return ((FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class)).isFeatureAvailable(Feature.cfy_playlists_sorting);
    }

    public boolean isTrackDownloadFeatureAvailable() {
        ExperimentWeblabs experimentWeblabs = ExperimentWeblabs.DM_LPN_OFFLINE_DOWNLOAD_BUTTON_PHASE_2;
        WeblabProvider weblabProvider = (WeblabProvider) Providers.INSTANCE.get(WeblabProvider.class);
        if (weblabProvider == null) {
            return false;
        }
        weblabProvider.getTreatment(experimentWeblabs.getAaWeblab(), true);
        return weblabProvider.getTreatment(experimentWeblabs.getAbWeblab(), true) == WeblabTreatment.T1;
    }

    public boolean isPodcastRedhoodPlusUXEnabled() {
        boolean zIsRedhoodPlusUXEnabled = Podcast.getFeatureGatePreferences().isRedhoodPlusUXEnabled();
        Log.debug(TAG, "\"Weblab PODCAST_REDHOODPLUS_UX_ENABLED: %b", Boolean.valueOf(zIsRedhoodPlusUXEnabled));
        return zIsRedhoodPlusUXEnabled;
    }

    public boolean shouldOverrideAugmentedShuffleWithSfa() {
        return UserSubscriptionUtil.isNightwingOnly() && FMPMFeatureGating.OVERRIDE_AUGMENTED_SHUFFLE_WITH_SFA.isEnabled();
    }

    public List<TooltipMetadata> getTooltipConfigurations(Context context) {
        Configuration configuration = (Configuration) Providers.INSTANCE.get(ConfigurationProvider.class);
        if (configuration == null) {
            Log.error(TAG, "Error fetching Feature gate provider");
        }
        return new TooltipConfig(configuration, context).getHotspots();
    }

    public List<String> getAllAccessHighlightConfigurations(String title) {
        Configuration configuration = (Configuration) Providers.INSTANCE.get(ConfigurationProvider.class);
        if (configuration == null) {
            Log.error(TAG, "Error fetching Feature gate provider");
        }
        return new AllAccessTitleHighlight(configuration).getHighlightMap(title);
    }

    public boolean isPlaylistCreationPublicToggleEnabled() {
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        if (featureGateProvider == null) {
            Log.error(TAG, "Error fetching Feature gate provider");
        }
        return featureGateProvider != null && featureGateProvider.isFeatureAvailable(Feature.playlist_creation_public_toggle);
    }

    public boolean isPlaylistSongRecommendationWidgetEnabled() {
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        if (featureGateProvider == null) {
            Log.error(TAG, "Error fetching Feature gate provider");
        }
        return (featureGateProvider == null || PlatformUtil.isFireOS() || !featureGateProvider.isFeatureAvailable(Feature.playlist_recommendations_widget)) ? false : true;
    }

    public boolean isLocaleSupportedForUserInArcus() {
        return Feature.locale_supported.isEnabled();
    }

    public boolean shouldShowSRPageHeaders(final boolean useCache) {
        return isFeatureEnabled(Feature.SR_page_headers, useCache) && isFeatureEnabled(Feature.sonic_rush, useCache);
    }

    public boolean isFacetedNavigationEnabled() {
        if (!UserSubscriptionUtil.getUserSubscription().isHawkfireAllDevices()) {
            return false;
        }
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        if (featureGateProvider == null) {
            Log.error(TAG, "Error fetching feature gate provider");
        }
        return featureGateProvider != null && featureGateProvider.isFeatureAvailable(Feature.faceted_navigation_browse_home);
    }

    public boolean isAmpShowsEnabled() {
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        if (featureGateProvider == null) {
            Log.error(TAG, "Error fetching feature gate provider");
            return false;
        }
        return featureGateProvider.isFeatureAvailable(Feature.include_amp_shows);
    }

    public boolean shouldPinPodcastsInFacetedNavigation() {
        if (!UserSubscriptionUtil.getUserSubscription().isHawkfireAllDevices()) {
            return false;
        }
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        if (featureGateProvider == null) {
            Log.error(TAG, "Error fetching feature gate provider");
        }
        return featureGateProvider != null && featureGateProvider.isFeatureAvailable(Feature.faceted_navigation_pin_podcasts_pill);
    }

    public boolean isCastingIngressExperimentEnabled() {
        WeblabProvider weblabProvider;
        return (UserSubscriptionUtil.getUserSubscription().isNightwingOnly() || (weblabProvider = (WeblabProvider) Providers.INSTANCE.get(WeblabProvider.class)) == null || weblabProvider.getTreatment(Weblab.DM_MUSICACCESS_AMCONNECT_CASTING_INGRESS_ANDROID_EXPERIMENT.toString(), true) == WeblabTreatment.C) ? false : true;
    }

    public WeblabTreatment getCastingIngressExperimentTreatment() {
        WeblabProvider weblabProvider = (WeblabProvider) Providers.INSTANCE.get(WeblabProvider.class);
        if (weblabProvider != null) {
            return weblabProvider.getTreatment(Weblab.DM_MUSICACCESS_AMCONNECT_CASTING_INGRESS_ANDROID_EXPERIMENT.toString(), true);
        }
        return WeblabTreatment.C;
    }

    public boolean isAutoConnectExperimentEnabled() {
        return isTreatmentEnabledViaWeblabT1(Weblab.DM_MUSICACCESS_AMCONNECT_AUTO_CONNECT_ANDROID_EXPERIMENT);
    }

    public boolean isHapticsOnPlaybackExperimentEnabled() {
        if (Build.VERSION.SDK_INT < 29 || PlatformUtil.isFireOS()) {
            return false;
        }
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        if (featureGateProvider == null) {
            Log.error(TAG, "Error fetching Feature gate provider");
        }
        return featureGateProvider != null && featureGateProvider.isFeatureAvailable(Feature.haptics_on_playback);
    }

    public boolean isPlaylistSongSearchEnabled() {
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        if (featureGateProvider == null) {
            Log.error(TAG, "Error fetching Feature gate provider");
        }
        return featureGateProvider != null && featureGateProvider.isFeatureAvailable(Feature.playlist_song_search);
    }

    public boolean isCarModePlayQueueEnabled() {
        return Feature.is_car_mode_play_queue_enabled.isEnabled() && Feature.display_queue_view.isEnabled();
    }

    public boolean isRestartSigninFlowOptimizationEnabled() {
        WeblabProvider weblabProvider = (WeblabProvider) Providers.INSTANCE.get(WeblabProvider.class);
        if (weblabProvider != null) {
            return weblabProvider.getTreatment(Weblab.DM_EARLYUSE_RESTART_SIGNIN_OPTIMIZATION.toString(), true) == WeblabTreatment.T1;
        }
        Log.error(TAG, "Error fetching Weblab provider");
        return false;
    }

    public boolean isLyricsFeedbackSupported() {
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        if (featureGateProvider != null) {
            return isLyricsSupported() && featureGateProvider.isFeatureAvailable(Feature.is_lyrics_feedback_enabled);
        }
        Log.error(TAG, "Error fetching Feature gate provider");
        return false;
    }

    public static boolean shouldShowBannerToImportPlaylists() {
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        if (featureGateProvider == null) {
            Log.error(TAG, "Error fetching feature gate provider");
        }
        return featureGateProvider != null && featureGateProvider.isFeatureAvailable(Feature.playlist_import);
    }

    public boolean isArtistFollowForVideoStoriesSupported() {
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        if (featureGateProvider == null) {
            Log.error(TAG, "Error fetching feature gate provider");
        }
        return featureGateProvider != null && featureGateProvider.isFeatureAvailable(Feature.video_stories_artist_follow_supported) && isVideosWeblabEnabledInNewTerritories();
    }

    public boolean isVideoStoriesMutedAutoPlayEnabled() {
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        if (featureGateProvider == null) {
            Log.error(TAG, "Error fetching feature gate provider");
        }
        return featureGateProvider != null && featureGateProvider.isFeatureAvailable(Feature.video_stories_muted_autoPlay) && isVideosWeblabEnabledInNewTerritories();
    }

    public long getCarBluetoothEventThrottleValue() {
        if (((Configuration) Providers.INSTANCE.get(ConfigurationProvider.class)) == null) {
            Log.error(TAG, "Error fetching Feature gate provider");
            return 100L;
        }
        return r0.getIntForKey("car_detection_bluetooth_event_throttle_ms");
    }

    public long getCarDetectionThrottleValue() {
        if (((Configuration) Providers.INSTANCE.get(ConfigurationProvider.class)) == null) {
            Log.error(TAG, "Error fetching Feature gate provider");
            return 1000L;
        }
        return r0.getIntForKey("car_detection_throttle_ms");
    }

    public boolean isLyricsSharingEnabled() {
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        if (featureGateProvider == null) {
            Log.error(TAG, "Error fetching feature gate provider");
        }
        return (featureGateProvider == null || !featureGateProvider.isFeatureAvailable(Feature.lyrics_sharing) || ScreenUtil.isLargeOrExtraLargeScreen()) ? false : true;
    }

    public boolean isBugsnagBreadcrumbsEnabled() {
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        if (featureGateProvider == null) {
            Log.error(TAG, "Error fetching Feature gate provider");
        }
        return featureGateProvider != null && featureGateProvider.isFeatureAvailable(Feature.bugsnag_breadcrumbs_is_enabled);
    }
}
