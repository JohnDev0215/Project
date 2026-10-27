package com.amazon.music.featureflag;

/* JADX INFO: loaded from: classes4.dex */
@Deprecated
public interface FeatureFlagProvider {
    boolean areIvyRefinementPillsEnabled() throws FeatureFlagUnavailableException;

    boolean canAccessCasting() throws FeatureFlagUnavailableException;

    boolean canAccessKatana() throws FeatureFlagUnavailableException;

    boolean canAccessLyrics() throws FeatureFlagUnavailableException;

    boolean canAccessNightwing() throws FeatureFlagUnavailableException;

    boolean canAccessPrime() throws FeatureFlagUnavailableException;

    boolean canAccessRecommendationForFreeTier() throws FeatureFlagUnavailableException;

    boolean canAccessRecommendationForPrime() throws FeatureFlagUnavailableException;

    boolean canAccessRecommendationForUnlimited() throws FeatureFlagUnavailableException;

    boolean canAccessStore() throws FeatureFlagUnavailableException;

    boolean canAccessUnlimited() throws FeatureFlagUnavailableException;

    boolean canAccessVideos() throws FeatureFlagUnavailableException;

    int getAppRatingNoIncidentThreshold() throws FeatureFlagUnavailableException;

    boolean getBooleanForKey(String str) throws FeatureFlagUnavailableException;

    int getHybridQueueBatchSize() throws FeatureFlagUnavailableException;

    int getHybridQueueLookaheadWindowSize() throws FeatureFlagUnavailableException;

    int getIntForKey(String str) throws FeatureFlagUnavailableException;

    int getPageLatencySamplingRate() throws FeatureFlagUnavailableException;

    boolean isActivityFeedSupported() throws FeatureFlagUnavailableException;

    boolean isAlbumBadgingInLibrarySupported() throws FeatureFlagUnavailableException;

    boolean isAppDisplayLangSettingEnabled() throws FeatureFlagUnavailableException;

    boolean isAppRatingSupported() throws FeatureFlagUnavailableException;

    boolean isArtistFollowSupported() throws FeatureFlagUnavailableException;

    boolean isArtistTasteCollectionEnabled() throws FeatureFlagUnavailableException;

    boolean isBadgingInRecentsTrackSupported() throws FeatureFlagUnavailableException;

    boolean isBauhausADPEnabled() throws FeatureFlagUnavailableException;

    boolean isBauhausInAppMessagingEnabled() throws FeatureFlagUnavailableException;

    boolean isBauhausLibraryArtistDPEnabled() throws FeatureFlagUnavailableException;

    boolean isBauhausLibraryEnabled() throws FeatureFlagUnavailableException;

    boolean isBauhausPaginationEnabled() throws FeatureFlagUnavailableException;

    boolean isBauhausPlaylistDetailPageEnabled() throws FeatureFlagUnavailableException;

    boolean isBauhausTopAppBarEnabled() throws FeatureFlagUnavailableException;

    boolean isBehavioralAdsNotificationSupported() throws FeatureFlagUnavailableException;

    boolean isCloudQueueEnabled() throws FeatureFlagUnavailableException;

    boolean isCompressedCoralRequestsSupported() throws FeatureFlagUnavailableException;

    boolean isCustomerProfilePictureUploadSupported() throws FeatureFlagUnavailableException;

    boolean isCustomerProfileSupported() throws FeatureFlagUnavailableException;

    boolean isDownloadTrackButtonEnabled() throws FeatureFlagUnavailableException;

    boolean isDynamicMessagingSupported() throws FeatureFlagUnavailableException;

    boolean isEarlyUseNotificationEnabled() throws FeatureFlagUnavailableException;

    boolean isFollowArtistsWidgetEnabled() throws FeatureFlagUnavailableException;

    boolean isFreeTierContextualUpsellsEnabled() throws FeatureFlagUnavailableException;

    boolean isFreeTierEnabledForSDP() throws FeatureFlagUnavailableException;

    boolean isFreeTierScrubbingOnDemandPlaylistsEnabled() throws FeatureFlagUnavailableException;

    boolean isFreeTierSkipLimitEducationalTooltipEnabled() throws FeatureFlagUnavailableException;

    boolean isFreeTierSkipsRemainingTooltipEnabled() throws FeatureFlagUnavailableException;

    boolean isFreeTierUserPlaylistEditingEnabled() throws FeatureFlagUnavailableException;

    boolean isHarleyEqualizerSupported() throws FeatureFlagUnavailableException;

    boolean isINLanguageExpansionSupported() throws FeatureFlagUnavailableException;

    boolean isIoTSkipLimitSupported() throws FeatureFlagUnavailableException;

    boolean isIvySupported() throws FeatureFlagUnavailableException;

    boolean isLibrarySyncApiDialing() throws FeatureFlagUnavailableException;

    boolean isLiveEventEnabled() throws FeatureFlagUnavailableException;

    boolean isMarketPlaceCheckForLyricsSupported() throws FeatureFlagUnavailableException;

    boolean isMerchEnabled() throws FeatureFlagUnavailableException;

    boolean isMusicNeverStopsEnabled() throws FeatureFlagUnavailableException;

    boolean isNPSLikesSupported() throws FeatureFlagUnavailableException;

    boolean isPlaylistFollowTextChangeEnabled() throws FeatureFlagUnavailableException;

    boolean isPodcastEnabled() throws FeatureFlagUnavailableException;

    boolean isPodcastScrubTranscriptionEnabled() throws FeatureFlagUnavailableException;

    boolean isPurchasedTrackPlaybackInKatanaQualitySupported() throws FeatureFlagUnavailableException;

    boolean isRecentTracksSyncSupported() throws FeatureFlagUnavailableException;

    boolean isRecentsSyncSupported() throws FeatureFlagUnavailableException;

    boolean isRetailLopSyncNotificationEnabled() throws FeatureFlagUnavailableException;

    boolean isTopVideoRecEnabled() throws FeatureFlagUnavailableException;

    boolean isTrackPopularityBarSupported() throws FeatureFlagUnavailableException;

    boolean isTrickPlaySupported() throws FeatureFlagUnavailableException;

    boolean isVideoLandingPageEnabled() throws FeatureFlagUnavailableException;

    boolean isWazeIntegrationEnabled() throws FeatureFlagUnavailableException;

    boolean isWoodstockEnabled() throws FeatureFlagUnavailableException;

    boolean primeBadgingRequired() throws FeatureFlagUnavailableException;

    boolean shouldSuppressIvyCatalogNonTrackAlbumFiltering() throws FeatureFlagUnavailableException;

    boolean shouldUploadLogAfterCrash() throws FeatureFlagUnavailableException;

    boolean supportsInAppMessaging() throws FeatureFlagUnavailableException;

    boolean supportsLanguagePreferences() throws FeatureFlagUnavailableException;
}
