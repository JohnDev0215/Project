package com.amazon.music.featureflag;

/* JADX INFO: loaded from: classes4.dex */
@Deprecated
public class DefaultFeatureFlagProvider implements FeatureFlagProvider {
    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean areIvyRefinementPillsEnabled() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean canAccessCasting() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean canAccessKatana() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean canAccessLyrics() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean canAccessNightwing() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean canAccessPrime() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean canAccessRecommendationForFreeTier() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean canAccessRecommendationForPrime() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean canAccessRecommendationForUnlimited() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean canAccessStore() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean canAccessUnlimited() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean canAccessVideos() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public int getAppRatingNoIncidentThreshold() {
        return 0;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean getBooleanForKey(String str) throws FeatureFlagUnavailableException {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public int getHybridQueueBatchSize() {
        return 0;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public int getHybridQueueLookaheadWindowSize() {
        return 0;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public int getIntForKey(String str) throws FeatureFlagUnavailableException {
        return 0;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public int getPageLatencySamplingRate() {
        return 0;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isActivityFeedSupported() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isAlbumBadgingInLibrarySupported() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isAppDisplayLangSettingEnabled() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isAppRatingSupported() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isArtistFollowSupported() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isArtistTasteCollectionEnabled() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isBadgingInRecentsTrackSupported() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isBauhausADPEnabled() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isBauhausInAppMessagingEnabled() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isBauhausLibraryArtistDPEnabled() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isBauhausLibraryEnabled() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isBauhausPaginationEnabled() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isBauhausPlaylistDetailPageEnabled() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isBauhausTopAppBarEnabled() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isBehavioralAdsNotificationSupported() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isCloudQueueEnabled() throws FeatureFlagUnavailableException {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isCompressedCoralRequestsSupported() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isCustomerProfilePictureUploadSupported() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isCustomerProfileSupported() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isDownloadTrackButtonEnabled() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isDynamicMessagingSupported() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isEarlyUseNotificationEnabled() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isFollowArtistsWidgetEnabled() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isFreeTierContextualUpsellsEnabled() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isFreeTierEnabledForSDP() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isFreeTierScrubbingOnDemandPlaylistsEnabled() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isFreeTierSkipLimitEducationalTooltipEnabled() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isFreeTierSkipsRemainingTooltipEnabled() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isFreeTierUserPlaylistEditingEnabled() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isHarleyEqualizerSupported() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isINLanguageExpansionSupported() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isIoTSkipLimitSupported() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isIvySupported() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isLibrarySyncApiDialing() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isLiveEventEnabled() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isMarketPlaceCheckForLyricsSupported() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isMerchEnabled() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isMusicNeverStopsEnabled() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isNPSLikesSupported() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isPlaylistFollowTextChangeEnabled() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isPodcastEnabled() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isPodcastScrubTranscriptionEnabled() throws FeatureFlagUnavailableException {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isPurchasedTrackPlaybackInKatanaQualitySupported() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isRecentTracksSyncSupported() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isRecentsSyncSupported() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isRetailLopSyncNotificationEnabled() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isTopVideoRecEnabled() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isTrackPopularityBarSupported() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isTrickPlaySupported() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isVideoLandingPageEnabled() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isWazeIntegrationEnabled() throws FeatureFlagUnavailableException {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean isWoodstockEnabled() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean primeBadgingRequired() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean shouldSuppressIvyCatalogNonTrackAlbumFiltering() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean shouldUploadLogAfterCrash() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean supportsInAppMessaging() {
        return false;
    }

    @Override // com.amazon.music.featureflag.FeatureFlagProvider
    public boolean supportsLanguagePreferences() {
        return false;
    }
}
