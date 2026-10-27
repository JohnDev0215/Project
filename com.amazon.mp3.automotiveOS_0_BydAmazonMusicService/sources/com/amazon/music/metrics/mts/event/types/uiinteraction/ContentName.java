package com.amazon.music.metrics.mts.event.types.uiinteraction;

import com.amazon.music.metrics.mts.event.types.core.MetricValueType;

/* JADX INFO: loaded from: classes4.dex */
public enum ContentName implements MetricValueType {
    GHOST_LISTENING_DIALOG("ghostListening"),
    HAWKFIRE_UPSELL_DIALOG_FRAGMENT("hawkfireUpsellDialogFragment"),
    HAWKFIRE_SUBSCRIBE_DIALOG_FRAGMENT("hawkfireSubscribeDialogFragment"),
    MIGRATE_EXISTING_DOWNLOADS_DIALOG("migrateExistingDownloadsDialog"),
    MIGRATE_EXISTING_DOWNLOADS_RIBBON("migrateExistingDownloadsRibbon"),
    UPDATE_EXISTING_DOWNLOADS_DIALOG("updateExistingDownloadsDialog"),
    REDOWNLOAD_OFFLINE_MUSIC_DIALOG("redownloadOfflineMusicDialog"),
    DOWNLOADS_QUEUED_RIBBON("downloadsQueuedRibbon"),
    REMOVE_UNAVAILABLE_DONWLOADS_DIALOG("removeUnavailableDownloadsDialog"),
    BAUHAUS_HAWKFIRE_BLOCKING_UPSELL("blockingUpsell"),
    BAUHAUS_HAWKFIRE_NONBLOCKING_UPSELL("nonBlockingUpsell"),
    BAUHAUS_HAWKFIRE_ADS_UPSELL("adUpsell"),
    BAUHAUS_UNLIMITED_UPSELL("upsellToUnlimited"),
    BAUHAUS_UNLIMITED_UPSELL_TO_TRIAL("upsellToUnlimitedTrial"),
    BAUHAUS_UNLIMITED_UPSELL_TO_PAID("upsellToUnlimitedPaid"),
    BAUHAUS_HAWKFIRE_WELCOME_DIALOG_FRAGMENT("bauhausHawkfireWelcomeDialogFragment"),
    NPS_CONTEXTUAL_UPSELL_DIALOG("npsContextualUpsellDialog"),
    DOWNLOAD_SETTINGS("downloadSettings"),
    STREAMING_SETTINGS("streamingSettings"),
    STREAMING_INFORMATION_DIALOG("streamingInformationDialog"),
    PLAYLIST_TOOLTIP("playlistTooltip"),
    CONCURRENT_FAMILY_UPSELL_ONE_CLICK_SIGNUP_TO_TRIAL("concurrentFamilyUpsellOneClickSignupTrial"),
    CONCURRENT_FAMILY_UPSELL_ONE_CLICK_SIGNUP_TO_PAID("concurrentFamilyUpsellOneClickSignupPaid"),
    CONCURRENT_FAMILY_ONE_CLICK_SIGNUP_UPSELL("concurrentFamilyOneClickSignupUpsell"),
    SFA_DIALOG_FRAGMENT("sfaDialogFragment"),
    SFA_NOW_PLAYING("sfaDialogFragment"),
    TOP_HITS("topHits"),
    SIMILAR_ALBUMS_RECOMMENDER("similarAlbumsRecommender"),
    WAZE_BANNER("wazeBanner"),
    WAZE_PLAYLIST_TRAY("wazePlaylistTray"),
    NIGHTWING_PERSISTENT_UPSELL("nightwingPersistentUpsell"),
    PERSISTENT_UPGRADE("persistentUpgrade"),
    ADD_TO_PLAYLIST_CONFIRMATION("addToPlaylistConfirmation"),
    IMPORT_PLAYLISTS_BANNER("importPlaylistsBanner"),
    PLAYLISTS_PRIVACY_ENFORCEMENT_FOLLOWED_RIBBON("playlistsPrivacyEnforcementFollowedRibbon"),
    LYRICS_VIEWED_PREVIEW("lyricsViewedPreview"),
    LYRICS_VIEWED_MANUAL("lyricsViewedManual"),
    LYRICS_VIEWED_FULLSCREEN("lyricsViewedFullScreen"),
    XRAY_VIEWED_PREVIEW("xrayViewedPreview"),
    XRAY_VIEWED_MANUAL("xrayViewedManual"),
    XRAY_VIEWED_FULLSCREEN("xrayViewedFullScreen"),
    ALBUM_ART_VIEWED_PREVIEW("albumArtViewedPreview"),
    ALBUM_ART_VIEWED_MANUAL("albumArtViewedManual"),
    NOW_PLAYING_STAGE_START("nowPlayingStageStart"),
    NOW_PLAYING_STAGE_STOP("nowPlayingStageStop"),
    NOW_PLAYING_START("nowPlayingStart"),
    NOW_PLAYING_STOP("nowPlayingStop"),
    ALBUMS_SHOVELER("albumsShoveler"),
    ARTISTS_SHOVELER("artistsShoveler"),
    ARTIST_FILTER_TOOLTIP("toolTipArtistFilters"),
    ICON_LINK_BUTTON("iconLinkButton"),
    VERTICAL_ICON_LINK_BUTTON("verticalIconLinkButton"),
    LIVE_EVENT_HEADER("liveEventHeader"),
    LIVE_EVENT_HEADER_EXPERIMENT("liveEventHeaderExperiment"),
    LIVE_EVENT_INFO("liveEventInfo"),
    LIVE_EVENT_LINEUP("liveEventLineUp"),
    SPONSOR_IMAGE("sponsorImage"),
    ARTIST_MERCH_SHOVELER("artistMerchShoveler"),
    EVENT_MERCH_SHOVELER("eventMerchShoveler"),
    PLAYLISTS_SHOVELER("playlistsShoveler"),
    FOLLOWED_ARTIST_BANNER("followedArtistBanner"),
    LIBRARY_ARTIST_BANNER("libraryArtistBanner"),
    LIBRARY_SONGS_BANNER("librarySongsBanner"),
    LIBRARY_BANNER("libraryBanner"),
    LIBRARY_LANDING_PROFILE_WIDGET("libraryLandingProfileWidget"),
    UPSELL_BUTTON("upsellButton"),
    DM_RIBBON("dmRibbon"),
    SWIPE_TOOLTIP("miniPlayerSwipeControlTooltip"),
    MINI_PLAYER("miniPlayer");

    private final String mMetricValue;

    ContentName(String str) {
        this.mMetricValue = str;
    }

    @Override // com.amazon.music.metrics.mts.event.types.core.MetricValueType
    public String getMetricValue() {
        return this.mMetricValue;
    }
}
