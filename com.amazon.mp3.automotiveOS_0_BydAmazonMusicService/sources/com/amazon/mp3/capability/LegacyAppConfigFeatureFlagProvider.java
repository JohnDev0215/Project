package com.amazon.mp3.capability;

import com.amazon.music.featureflag.Feature;
import com.amazon.music.featureflag.FeatureFlagProvider;
import com.amazon.music.featuregating.configuration.Configuration;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LegacyAppConfigFeatureFlagProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\bJ\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010R\u001a\u00020Q¢\u0006\u0004\bW\u0010XJ\u0012\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016J\u0012\u0010\u0007\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\b\u001a\u00020\u0004H\u0016J\b\u0010\t\u001a\u00020\u0004H\u0016J\b\u0010\n\u001a\u00020\u0004H\u0016J\b\u0010\u000b\u001a\u00020\u0004H\u0016J\b\u0010\f\u001a\u00020\u0004H\u0016J\b\u0010\r\u001a\u00020\u0004H\u0016J\b\u0010\u000e\u001a\u00020\u0004H\u0016J\b\u0010\u000f\u001a\u00020\u0004H\u0016J\b\u0010\u0010\u001a\u00020\u0004H\u0016J\b\u0010\u0011\u001a\u00020\u0004H\u0016J\b\u0010\u0012\u001a\u00020\u0004H\u0016J\b\u0010\u0013\u001a\u00020\u0004H\u0016J\b\u0010\u0014\u001a\u00020\u0004H\u0016J\b\u0010\u0015\u001a\u00020\u0004H\u0016J\b\u0010\u0016\u001a\u00020\u0004H\u0016J\b\u0010\u0017\u001a\u00020\u0004H\u0016J\b\u0010\u0018\u001a\u00020\u0004H\u0016J\b\u0010\u0019\u001a\u00020\u0004H\u0016J\b\u0010\u001a\u001a\u00020\u0004H\u0016J\b\u0010\u001b\u001a\u00020\u0004H\u0016J\b\u0010\u001c\u001a\u00020\u0004H\u0016J\b\u0010\u001d\u001a\u00020\u0004H\u0016J\b\u0010\u001e\u001a\u00020\u0004H\u0016J\b\u0010\u001f\u001a\u00020\u0004H\u0016J\b\u0010 \u001a\u00020\u0004H\u0016J\b\u0010!\u001a\u00020\u0004H\u0016J\b\u0010\"\u001a\u00020\u0004H\u0016J\b\u0010#\u001a\u00020\u0004H\u0016J\b\u0010$\u001a\u00020\u0004H\u0016J\b\u0010%\u001a\u00020\u0004H\u0016J\b\u0010&\u001a\u00020\u0004H\u0016J\b\u0010'\u001a\u00020\u0004H\u0016J\b\u0010(\u001a\u00020\u0004H\u0016J\b\u0010)\u001a\u00020\u0004H\u0016J\b\u0010*\u001a\u00020\u0006H\u0016J\b\u0010+\u001a\u00020\u0004H\u0016J\b\u0010,\u001a\u00020\u0004H\u0016J\b\u0010-\u001a\u00020\u0004H\u0016J\b\u0010.\u001a\u00020\u0004H\u0016J\b\u0010/\u001a\u00020\u0004H\u0016J\b\u00100\u001a\u00020\u0004H\u0016J\b\u00101\u001a\u00020\u0004H\u0016J\b\u00102\u001a\u00020\u0004H\u0016J\b\u00103\u001a\u00020\u0004H\u0016J\b\u00104\u001a\u00020\u0006H\u0016J\b\u00105\u001a\u00020\u0006H\u0016J\b\u00106\u001a\u00020\u0004H\u0016J\b\u00107\u001a\u00020\u0006H\u0016J\b\u00108\u001a\u00020\u0004H\u0016J\b\u00109\u001a\u00020\u0004H\u0016J\b\u0010:\u001a\u00020\u0004H\u0016J\b\u0010;\u001a\u00020\u0004H\u0016J\b\u0010<\u001a\u00020\u0004H\u0016J\b\u0010=\u001a\u00020\u0004H\u0016J\b\u0010>\u001a\u00020\u0004H\u0016J\b\u0010?\u001a\u00020\u0004H\u0016J\b\u0010@\u001a\u00020\u0004H\u0016J\b\u0010A\u001a\u00020\u0004H\u0016J\b\u0010B\u001a\u00020\u0004H\u0016J\b\u0010C\u001a\u00020\u0004H\u0016J\b\u0010D\u001a\u00020\u0004H\u0016J\b\u0010E\u001a\u00020\u0004H\u0016J\b\u0010F\u001a\u00020\u0004H\u0016J\b\u0010G\u001a\u00020\u0004H\u0016J\b\u0010H\u001a\u00020\u0004H\u0016J\b\u0010I\u001a\u00020\u0004H\u0016J\b\u0010J\u001a\u00020\u0004H\u0016J\b\u0010K\u001a\u00020\u0004H\u0016J\b\u0010L\u001a\u00020\u0004H\u0016J\b\u0010M\u001a\u00020\u0004H\u0016J\b\u0010N\u001a\u00020\u0004H\u0016J\b\u0010O\u001a\u00020\u0004H\u0016J\b\u0010P\u001a\u00020\u0004H\u0016R\u0014\u0010R\u001a\u00020Q8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bR\u0010SR\u0014\u0010U\u001a\u00020T8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bU\u0010V¨\u0006Y"}, d2 = {"Lcom/amazon/mp3/capability/LegacyAppConfigFeatureFlagProvider;", "Lcom/amazon/music/featureflag/FeatureFlagProvider;", "", "key", "", "getBooleanForKey", "", "getIntForKey", "canAccessUnlimited", "canAccessPrime", "canAccessNightwing", "canAccessKatana", "canAccessStore", "canAccessLyrics", "primeBadgingRequired", "supportsLanguagePreferences", "canAccessRecommendationForPrime", "canAccessRecommendationForFreeTier", "canAccessRecommendationForUnlimited", "canAccessCasting", "supportsInAppMessaging", "canAccessVideos", "isArtistFollowSupported", "isCustomerProfileSupported", "isCustomerProfilePictureUploadSupported", "isActivityFeedSupported", "isRecentsSyncSupported", "isHarleyEqualizerSupported", "isRecentTracksSyncSupported", "isMarketPlaceCheckForLyricsSupported", "isAppRatingSupported", "isTrackPopularityBarSupported", "isIvySupported", "areIvyRefinementPillsEnabled", "shouldSuppressIvyCatalogNonTrackAlbumFiltering", "isAlbumBadgingInLibrarySupported", "isLibrarySyncApiDialing", "isPurchasedTrackPlaybackInKatanaQualitySupported", "isBadgingInRecentsTrackSupported", "isBehavioralAdsNotificationSupported", "shouldUploadLogAfterCrash", "isCompressedCoralRequestsSupported", "getAppRatingNoIncidentThreshold", "isIoTSkipLimitSupported", "isDynamicMessagingSupported", "isWoodstockEnabled", "isPodcastEnabled", "isArtistTasteCollectionEnabled", "isMusicNeverStopsEnabled", "isMerchEnabled", "isLiveEventEnabled", "isDownloadTrackButtonEnabled", "getHybridQueueLookaheadWindowSize", "getHybridQueueBatchSize", "isFollowArtistsWidgetEnabled", "getPageLatencySamplingRate", "isFreeTierUserPlaylistEditingEnabled", "isFreeTierContextualUpsellsEnabled", "isCloudQueueEnabled", "isWazeIntegrationEnabled", "isVideoLandingPageEnabled", "isBauhausTopAppBarEnabled", "isBauhausInAppMessagingEnabled", "isAppDisplayLangSettingEnabled", "isRetailLopSyncNotificationEnabled", "isINLanguageExpansionSupported", "isNPSLikesSupported", "isPodcastScrubTranscriptionEnabled", "isBauhausPlaylistDetailPageEnabled", "isBauhausADPEnabled", "isBauhausLibraryArtistDPEnabled", "isBauhausLibraryEnabled", "isTrickPlaySupported", "isTopVideoRecEnabled", "isEarlyUseNotificationEnabled", "isBauhausPaginationEnabled", "isFreeTierScrubbingOnDemandPlaylistsEnabled", "isFreeTierEnabledForSDP", "isFreeTierSkipLimitEducationalTooltipEnabled", "isFreeTierSkipsRemainingTooltipEnabled", "isPlaylistFollowTextChangeEnabled", "Lcom/amazon/music/featuregating/configuration/Configuration;", "appConfig", "Lcom/amazon/music/featuregating/configuration/Configuration;", "Lcom/amazon/mp3/capability/NoOpFeatureFlagProvider;", "noOpFeatureFlagProvider", "Lcom/amazon/mp3/capability/NoOpFeatureFlagProvider;", "<init>", "(Lcom/amazon/music/featuregating/configuration/Configuration;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class LegacyAppConfigFeatureFlagProvider implements FeatureFlagProvider {
    private final Configuration appConfig;
    private final NoOpFeatureFlagProvider noOpFeatureFlagProvider;

    public LegacyAppConfigFeatureFlagProvider(Configuration appConfig) {
        Intrinsics.checkNotNullParameter(appConfig, "appConfig");
        this.appConfig = appConfig;
        this.noOpFeatureFlagProvider = new NoOpFeatureFlagProvider();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean getBooleanForKey(String key) {
        if (key == null) {
            return false;
        }
        return this.appConfig.getBooleanForKey(key);
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public int getIntForKey(String key) {
        if (key == null) {
            return 0;
        }
        return this.appConfig.getIntForKey(key);
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean canAccessUnlimited() {
        Configuration configuration = this.appConfig;
        String text = Feature.UNLIMITED.getText();
        Intrinsics.checkNotNullExpressionValue(text, "UNLIMITED.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.canAccessUnlimited() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean canAccessPrime() {
        Configuration configuration = this.appConfig;
        String text = Feature.PRIME.getText();
        Intrinsics.checkNotNullExpressionValue(text, "PRIME.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.canAccessPrime() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean canAccessNightwing() {
        Configuration configuration = this.appConfig;
        String text = Feature.NIGHTWING.getText();
        Intrinsics.checkNotNullExpressionValue(text, "NIGHTWING.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.canAccessNightwing() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean canAccessKatana() {
        Configuration configuration = this.appConfig;
        String text = Feature.KATANA.getText();
        Intrinsics.checkNotNullExpressionValue(text, "KATANA.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.canAccessKatana() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean canAccessStore() {
        Configuration configuration = this.appConfig;
        String text = Feature.STORE.getText();
        Intrinsics.checkNotNullExpressionValue(text, "STORE.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.canAccessStore() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean canAccessLyrics() {
        Configuration configuration = this.appConfig;
        String text = Feature.LYRICS.getText();
        Intrinsics.checkNotNullExpressionValue(text, "LYRICS.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.canAccessLyrics() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean primeBadgingRequired() {
        Configuration configuration = this.appConfig;
        String text = Feature.PRIME_BADGING.getText();
        Intrinsics.checkNotNullExpressionValue(text, "PRIME_BADGING.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.primeBadgingRequired() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean supportsLanguagePreferences() {
        Configuration configuration = this.appConfig;
        String text = Feature.LANGUAGE_PREFERENCES.getText();
        Intrinsics.checkNotNullExpressionValue(text, "LANGUAGE_PREFERENCES.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.supportsLanguagePreferences() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean canAccessRecommendationForPrime() {
        Configuration configuration = this.appConfig;
        String text = Feature.RECOMMENDATION_FOR_PRIME.getText();
        Intrinsics.checkNotNullExpressionValue(text, "RECOMMENDATION_FOR_PRIME.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.canAccessRecommendationForPrime() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean canAccessRecommendationForFreeTier() {
        Configuration configuration = this.appConfig;
        String text = Feature.RECOMMENDATION_FOR_Free_Tier.getText();
        Intrinsics.checkNotNullExpressionValue(text, "RECOMMENDATION_FOR_Free_Tier.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.canAccessRecommendationForFreeTier() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean canAccessRecommendationForUnlimited() {
        Configuration configuration = this.appConfig;
        String text = Feature.RECOMMENDATION_FOR_UMLIMITED.getText();
        Intrinsics.checkNotNullExpressionValue(text, "RECOMMENDATION_FOR_UMLIMITED.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.canAccessRecommendationForUnlimited() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean canAccessCasting() {
        Configuration configuration = this.appConfig;
        String text = Feature.CASTING.getText();
        Intrinsics.checkNotNullExpressionValue(text, "CASTING.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.canAccessCasting() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean supportsInAppMessaging() {
        Configuration configuration = this.appConfig;
        String text = Feature.IN_APP_MESSAGING.getText();
        Intrinsics.checkNotNullExpressionValue(text, "IN_APP_MESSAGING.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.supportsInAppMessaging() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean canAccessVideos() {
        Configuration configuration = this.appConfig;
        String text = Feature.VIDEOS.getText();
        Intrinsics.checkNotNullExpressionValue(text, "VIDEOS.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.canAccessVideos() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isArtistFollowSupported() {
        Configuration configuration = this.appConfig;
        String text = Feature.ARTIST_FOLLOW.getText();
        Intrinsics.checkNotNullExpressionValue(text, "ARTIST_FOLLOW.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isArtistFollowSupported() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isCustomerProfileSupported() {
        Configuration configuration = this.appConfig;
        String text = Feature.CUSTOMER_PROFILE.getText();
        Intrinsics.checkNotNullExpressionValue(text, "CUSTOMER_PROFILE.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isCustomerProfileSupported() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isCustomerProfilePictureUploadSupported() {
        Configuration configuration = this.appConfig;
        String text = Feature.CUSTOMER_PROFILE_PHOTO_UPLOAD.getText();
        Intrinsics.checkNotNullExpressionValue(text, "CUSTOMER_PROFILE_PHOTO_UPLOAD.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isCustomerProfilePictureUploadSupported() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isActivityFeedSupported() {
        Configuration configuration = this.appConfig;
        String text = Feature.ACTIVITY_FEED.getText();
        Intrinsics.checkNotNullExpressionValue(text, "ACTIVITY_FEED.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isActivityFeedSupported() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isRecentsSyncSupported() {
        Configuration configuration = this.appConfig;
        String text = Feature.SYNC_RECENTS.getText();
        Intrinsics.checkNotNullExpressionValue(text, "SYNC_RECENTS.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isRecentsSyncSupported() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isHarleyEqualizerSupported() {
        Configuration configuration = this.appConfig;
        String text = Feature.HARLEY_EQUALIZER.getText();
        Intrinsics.checkNotNullExpressionValue(text, "HARLEY_EQUALIZER.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isHarleyEqualizerSupported() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isRecentTracksSyncSupported() {
        Configuration configuration = this.appConfig;
        String text = Feature.SYNC_RECENT_TRACKS.getText();
        Intrinsics.checkNotNullExpressionValue(text, "SYNC_RECENT_TRACKS.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isRecentTracksSyncSupported() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isMarketPlaceCheckForLyricsSupported() {
        Configuration configuration = this.appConfig;
        String text = Feature.LYRICS_MARKET.getText();
        Intrinsics.checkNotNullExpressionValue(text, "LYRICS_MARKET.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isMarketPlaceCheckForLyricsSupported() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isAppRatingSupported() {
        Configuration configuration = this.appConfig;
        String text = Feature.APP_RATING.getText();
        Intrinsics.checkNotNullExpressionValue(text, "APP_RATING.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isAppRatingSupported() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isTrackPopularityBarSupported() {
        Configuration configuration = this.appConfig;
        String text = Feature.TRACK_POPULARITY_BAR.getText();
        Intrinsics.checkNotNullExpressionValue(text, "TRACK_POPULARITY_BAR.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isTrackPopularityBarSupported() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isIvySupported() {
        Configuration configuration = this.appConfig;
        String text = Feature.IVY.getText();
        Intrinsics.checkNotNullExpressionValue(text, "IVY.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isIvySupported() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean areIvyRefinementPillsEnabled() {
        Configuration configuration = this.appConfig;
        String text = Feature.IVY_REFINEMENT_PILLS.getText();
        Intrinsics.checkNotNullExpressionValue(text, "IVY_REFINEMENT_PILLS.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.areIvyRefinementPillsEnabled() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean shouldSuppressIvyCatalogNonTrackAlbumFiltering() {
        Configuration configuration = this.appConfig;
        String text = Feature.IVY_CATALOG_SUPPRESS_NON_TRACK_ALBUM_FILTERING.getText();
        Intrinsics.checkNotNullExpressionValue(text, "IVY_CATALOG_SUPPRESS_NON…RACK_ALBUM_FILTERING.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.shouldSuppressIvyCatalogNonTrackAlbumFiltering() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isAlbumBadgingInLibrarySupported() {
        Configuration configuration = this.appConfig;
        String text = Feature.ALBUM_BADGING_LIBRARY.getText();
        Intrinsics.checkNotNullExpressionValue(text, "ALBUM_BADGING_LIBRARY.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isAlbumBadgingInLibrarySupported() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isLibrarySyncApiDialing() {
        Configuration configuration = this.appConfig;
        String text = Feature.LIBRARY_SYNC_API_DIALING.getText();
        Intrinsics.checkNotNullExpressionValue(text, "LIBRARY_SYNC_API_DIALING.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isLibrarySyncApiDialing() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isPurchasedTrackPlaybackInKatanaQualitySupported() {
        Configuration configuration = this.appConfig;
        String text = Feature.PURCHASED_TRACK_PLAYBACK_IN_KATANA_QUALITY.getText();
        Intrinsics.checkNotNullExpressionValue(text, "PURCHASED_TRACK_PLAYBACK_IN_KATANA_QUALITY.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isPurchasedTrackPlaybackInKatanaQualitySupported() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isBadgingInRecentsTrackSupported() {
        Configuration configuration = this.appConfig;
        String text = Feature.BADGING_IN_RECENTS_TRACK.getText();
        Intrinsics.checkNotNullExpressionValue(text, "BADGING_IN_RECENTS_TRACK.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isBadgingInRecentsTrackSupported() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isBehavioralAdsNotificationSupported() {
        Configuration configuration = this.appConfig;
        String text = Feature.BEHAVIORAL_ADS_NOTIFICATION.getText();
        Intrinsics.checkNotNullExpressionValue(text, "BEHAVIORAL_ADS_NOTIFICATION.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isBehavioralAdsNotificationSupported() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean shouldUploadLogAfterCrash() {
        Configuration configuration = this.appConfig;
        String text = Feature.UPLOAD_LOG_AFTER_CRASH.getText();
        Intrinsics.checkNotNullExpressionValue(text, "UPLOAD_LOG_AFTER_CRASH.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.shouldUploadLogAfterCrash() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isCompressedCoralRequestsSupported() {
        Configuration configuration = this.appConfig;
        String text = Feature.COMPRESSED_CORAL_REQUESTS.getText();
        Intrinsics.checkNotNullExpressionValue(text, "COMPRESSED_CORAL_REQUESTS.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isCompressedCoralRequestsSupported() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public int getAppRatingNoIncidentThreshold() {
        Configuration configuration = this.appConfig;
        String text = Feature.APP_RATING_NO_INCIDENT_THRESHOLD.getText();
        Intrinsics.checkNotNullExpressionValue(text, "APP_RATING_NO_INCIDENT_THRESHOLD.text");
        Integer numMo2013int = configuration.mo2013int(text);
        return numMo2013int == null ? this.noOpFeatureFlagProvider.getAppRatingNoIncidentThreshold() : numMo2013int.intValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isIoTSkipLimitSupported() {
        Configuration configuration = this.appConfig;
        String text = Feature.IOT_SKIP_LIMIT.getText();
        Intrinsics.checkNotNullExpressionValue(text, "IOT_SKIP_LIMIT.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isIoTSkipLimitSupported() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isDynamicMessagingSupported() {
        Configuration configuration = this.appConfig;
        String text = Feature.DYNAMIC_MESSAGING.getText();
        Intrinsics.checkNotNullExpressionValue(text, "DYNAMIC_MESSAGING.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isDynamicMessagingSupported() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isWoodstockEnabled() {
        Configuration configuration = this.appConfig;
        String text = Feature.WOODSTOCK_ENABLED.getText();
        Intrinsics.checkNotNullExpressionValue(text, "WOODSTOCK_ENABLED.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isWoodstockEnabled() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isPodcastEnabled() {
        Configuration configuration = this.appConfig;
        String text = Feature.PODCAST_ENABLED.getText();
        Intrinsics.checkNotNullExpressionValue(text, "PODCAST_ENABLED.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isPodcastEnabled() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isArtistTasteCollectionEnabled() {
        Configuration configuration = this.appConfig;
        String text = Feature.ARTIST_TASTE_COLLECTION.getText();
        Intrinsics.checkNotNullExpressionValue(text, "ARTIST_TASTE_COLLECTION.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isArtistTasteCollectionEnabled() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isMusicNeverStopsEnabled() {
        Configuration configuration = this.appConfig;
        String text = Feature.MUSIC_NEVER_STOPS.getText();
        Intrinsics.checkNotNullExpressionValue(text, "MUSIC_NEVER_STOPS.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isMusicNeverStopsEnabled() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isMerchEnabled() {
        Configuration configuration = this.appConfig;
        String text = Feature.MERCH_ENABLED.getText();
        Intrinsics.checkNotNullExpressionValue(text, "MERCH_ENABLED.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isMerchEnabled() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isLiveEventEnabled() {
        Configuration configuration = this.appConfig;
        String text = Feature.LIVE_EVENT_ENABLED.getText();
        Intrinsics.checkNotNullExpressionValue(text, "LIVE_EVENT_ENABLED.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isLiveEventEnabled() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isDownloadTrackButtonEnabled() {
        Configuration configuration = this.appConfig;
        String text = Feature.DOWNLOAD_TRACK_BUTTON.getText();
        Intrinsics.checkNotNullExpressionValue(text, "DOWNLOAD_TRACK_BUTTON.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isDownloadTrackButtonEnabled() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public int getHybridQueueLookaheadWindowSize() {
        Configuration configuration = this.appConfig;
        String text = Feature.HYBRID_QUEUE_LOOKAHEAD_WINDOW_SIZE.getText();
        Intrinsics.checkNotNullExpressionValue(text, "HYBRID_QUEUE_LOOKAHEAD_WINDOW_SIZE.text");
        Integer numMo2013int = configuration.mo2013int(text);
        return numMo2013int == null ? this.noOpFeatureFlagProvider.getHybridQueueLookaheadWindowSize() : numMo2013int.intValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public int getHybridQueueBatchSize() {
        Configuration configuration = this.appConfig;
        String text = Feature.HYBRID_QUEUE_BATCH_SIZE.getText();
        Intrinsics.checkNotNullExpressionValue(text, "HYBRID_QUEUE_BATCH_SIZE.text");
        Integer numMo2013int = configuration.mo2013int(text);
        return numMo2013int == null ? this.noOpFeatureFlagProvider.getHybridQueueBatchSize() : numMo2013int.intValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isFollowArtistsWidgetEnabled() {
        Configuration configuration = this.appConfig;
        String text = Feature.DOWNLOAD_TRACK_BUTTON.getText();
        Intrinsics.checkNotNullExpressionValue(text, "DOWNLOAD_TRACK_BUTTON.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isFollowArtistsWidgetEnabled() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public int getPageLatencySamplingRate() {
        Configuration configuration = this.appConfig;
        String text = Feature.PAGE_LATENCY_SAMPLING_RATE.getText();
        Intrinsics.checkNotNullExpressionValue(text, "PAGE_LATENCY_SAMPLING_RATE.text");
        Integer numMo2013int = configuration.mo2013int(text);
        return numMo2013int == null ? this.noOpFeatureFlagProvider.getPageLatencySamplingRate() : numMo2013int.intValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isFreeTierUserPlaylistEditingEnabled() {
        Configuration configuration = this.appConfig;
        String text = Feature.FREE_TIER_USER_PLAYLIST_EDITING.getText();
        Intrinsics.checkNotNullExpressionValue(text, "FREE_TIER_USER_PLAYLIST_EDITING.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isFreeTierUserPlaylistEditingEnabled() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isFreeTierContextualUpsellsEnabled() {
        Configuration configuration = this.appConfig;
        String text = Feature.FREE_TIER_CONTEXTUAL_UPSELLS.getText();
        Intrinsics.checkNotNullExpressionValue(text, "FREE_TIER_CONTEXTUAL_UPSELLS.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isFreeTierContextualUpsellsEnabled() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isCloudQueueEnabled() {
        Configuration configuration = this.appConfig;
        String text = Feature.FREE_TIER_CONTEXTUAL_UPSELLS.getText();
        Intrinsics.checkNotNullExpressionValue(text, "FREE_TIER_CONTEXTUAL_UPSELLS.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isFreeTierContextualUpsellsEnabled() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isWazeIntegrationEnabled() {
        Configuration configuration = this.appConfig;
        String text = Feature.WAZE_INTEGRATION.getText();
        Intrinsics.checkNotNullExpressionValue(text, "WAZE_INTEGRATION.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isWazeIntegrationEnabled() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isVideoLandingPageEnabled() {
        Configuration configuration = this.appConfig;
        String text = Feature.VIDEO_LANDING_PAGE.getText();
        Intrinsics.checkNotNullExpressionValue(text, "VIDEO_LANDING_PAGE.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isVideoLandingPageEnabled() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isBauhausTopAppBarEnabled() {
        Configuration configuration = this.appConfig;
        String text = Feature.BAUHAUS_TOP_APP_BAR.getText();
        Intrinsics.checkNotNullExpressionValue(text, "BAUHAUS_TOP_APP_BAR.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isBauhausTopAppBarEnabled() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isBauhausInAppMessagingEnabled() {
        Configuration configuration = this.appConfig;
        String text = Feature.IN_APP_MESSAGING.getText();
        Intrinsics.checkNotNullExpressionValue(text, "IN_APP_MESSAGING.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isBauhausInAppMessagingEnabled() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isAppDisplayLangSettingEnabled() {
        Configuration configuration = this.appConfig;
        String text = Feature.MANAGE_APP_DISPLAY_LANGUAGE.getText();
        Intrinsics.checkNotNullExpressionValue(text, "MANAGE_APP_DISPLAY_LANGUAGE.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isAppDisplayLangSettingEnabled() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isRetailLopSyncNotificationEnabled() {
        Configuration configuration = this.appConfig;
        String text = Feature.MANAGE_RETAIL_LOP_SYNC_NOTIFICATION.getText();
        Intrinsics.checkNotNullExpressionValue(text, "MANAGE_RETAIL_LOP_SYNC_NOTIFICATION.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isRetailLopSyncNotificationEnabled() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isINLanguageExpansionSupported() {
        Configuration configuration = this.appConfig;
        String text = Feature.IN_EXTENDED_LANGUAGE_LIST.getText();
        Intrinsics.checkNotNullExpressionValue(text, "IN_EXTENDED_LANGUAGE_LIST.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isINLanguageExpansionSupported() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isNPSLikesSupported() {
        Configuration configuration = this.appConfig;
        String text = Feature.NPS_LIKES.getText();
        Intrinsics.checkNotNullExpressionValue(text, "NPS_LIKES.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isNPSLikesSupported() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isPodcastScrubTranscriptionEnabled() {
        Configuration configuration = this.appConfig;
        String text = Feature.PODCAST_SCRUB_TRANSCRIPTION.getText();
        Intrinsics.checkNotNullExpressionValue(text, "PODCAST_SCRUB_TRANSCRIPTION.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isPodcastScrubTranscriptionEnabled() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isBauhausPlaylistDetailPageEnabled() {
        Configuration configuration = this.appConfig;
        String text = Feature.BAUHAUS_PLAYLIST_DETAIL_PAGE.getText();
        Intrinsics.checkNotNullExpressionValue(text, "BAUHAUS_PLAYLIST_DETAIL_PAGE.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isBauhausPlaylistDetailPageEnabled() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isBauhausADPEnabled() {
        Configuration configuration = this.appConfig;
        String text = Feature.BAUHAUS_ALBUM_DETAIL_PAGE.getText();
        Intrinsics.checkNotNullExpressionValue(text, "BAUHAUS_ALBUM_DETAIL_PAGE.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isBauhausADPEnabled() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isBauhausLibraryArtistDPEnabled() {
        Configuration configuration = this.appConfig;
        String text = Feature.BAUHAUS_LIBRARY_ARTIST_DP.getText();
        Intrinsics.checkNotNullExpressionValue(text, "BAUHAUS_LIBRARY_ARTIST_DP.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isBauhausLibraryArtistDPEnabled() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isBauhausLibraryEnabled() {
        Configuration configuration = this.appConfig;
        String text = Feature.BAUHAUS_LIBRARY_PAGES.getText();
        Intrinsics.checkNotNullExpressionValue(text, "BAUHAUS_LIBRARY_PAGES.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isBauhausLibraryEnabled() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isTrickPlaySupported() {
        Configuration configuration = this.appConfig;
        String text = Feature.TRICKPLAY_CAPABILITY.getText();
        Intrinsics.checkNotNullExpressionValue(text, "TRICKPLAY_CAPABILITY.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isTrickPlaySupported() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isTopVideoRecEnabled() {
        Configuration configuration = this.appConfig;
        String text = Feature.TOP_VIDEO_REC.getText();
        Intrinsics.checkNotNullExpressionValue(text, "TOP_VIDEO_REC.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isTopVideoRecEnabled() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isEarlyUseNotificationEnabled() {
        Configuration configuration = this.appConfig;
        String text = Feature.EARLY_USE_NOTIFICATION.getText();
        Intrinsics.checkNotNullExpressionValue(text, "EARLY_USE_NOTIFICATION.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isEarlyUseNotificationEnabled() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isBauhausPaginationEnabled() {
        Configuration configuration = this.appConfig;
        String text = Feature.BAUHAUS_PAGINATION.getText();
        Intrinsics.checkNotNullExpressionValue(text, "BAUHAUS_PAGINATION.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isBauhausPaginationEnabled() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isFreeTierScrubbingOnDemandPlaylistsEnabled() {
        Configuration configuration = this.appConfig;
        String text = Feature.FREE_TIER_SCRUBBING_ON_DEMAND_PLAYLISTS.getText();
        Intrinsics.checkNotNullExpressionValue(text, "FREE_TIER_SCRUBBING_ON_DEMAND_PLAYLISTS.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isFreeTierScrubbingOnDemandPlaylistsEnabled() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isFreeTierEnabledForSDP() {
        Configuration configuration = this.appConfig;
        String text = Feature.ENABLE_FREE_TIER_FOR_SDP.getText();
        Intrinsics.checkNotNullExpressionValue(text, "ENABLE_FREE_TIER_FOR_SDP.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isFreeTierEnabledForSDP() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isFreeTierSkipLimitEducationalTooltipEnabled() {
        Configuration configuration = this.appConfig;
        String text = Feature.FREE_TIER_SKIP_LIMIT_EDUCATIONAL_TOOLTIP.getText();
        Intrinsics.checkNotNullExpressionValue(text, "FREE_TIER_SKIP_LIMIT_EDUCATIONAL_TOOLTIP.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isFreeTierSkipLimitEducationalTooltipEnabled() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isFreeTierSkipsRemainingTooltipEnabled() {
        Configuration configuration = this.appConfig;
        String text = Feature.FREE_TIER_SKIPS_REMAINING_TOOLTIP.getText();
        Intrinsics.checkNotNullExpressionValue(text, "FREE_TIER_SKIPS_REMAINING_TOOLTIP.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isFreeTierSkipsRemainingTooltipEnabled() : bool.booleanValue();
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isPlaylistFollowTextChangeEnabled() {
        Configuration configuration = this.appConfig;
        String text = Feature.PLAYLIST_FOLLOW_TEXT_CHANGE.getText();
        Intrinsics.checkNotNullExpressionValue(text, "PLAYLIST_FOLLOW_TEXT_CHANGE.text");
        Boolean bool = configuration.bool(text);
        return bool == null ? this.noOpFeatureFlagProvider.isPlaylistFollowTextChangeEnabled() : bool.booleanValue();
    }
}
