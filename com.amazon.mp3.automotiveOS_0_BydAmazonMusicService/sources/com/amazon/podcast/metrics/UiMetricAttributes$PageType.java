package com.amazon.podcast.metrics;

/* JADX INFO: loaded from: classes5.dex */
public enum UiMetricAttributes$PageType {
    PODCASTS_HOME("podcastsHomePage"),
    PODCASTS_LIBRARY_LATEST("podcastsLibraryLatest"),
    PODCASTS_LIBRARY_SHOWS("podcastsLibraryShows"),
    PODCASTS_LIBRARY_DOWNLOADS("podcastsLibraryDownloads"),
    PODCASTS_LIBRARY_PLAYLIST("podcastsLibraryPlaylist"),
    PODCAST_SHOW_DETAIL("podcastShowDetail"),
    PODCAST_EPISODE_DETAIL("podcastEpisodeDetail"),
    PODCASTS_FOLLOWED_SHOWS("podcastsFollowedShows"),
    PODCASTS_SAVED_EPISODES("podcastsSavedEpisodes"),
    PODCAST_NOW_PLAYING("podcastNowPlaying"),
    PODCAST_CARD_NOW_PLAYING("podcastCardNowPlaying"),
    PODCASTS_JUMP_BACK_IN_EPISODES("podcastsJumpBackInEpisodes"),
    PODCAST_SHOW_ABOUT("podcastShowAbout"),
    PODCASTS_FULL_SCRUB("podcastsFullScrub"),
    PODCASTS_TASTE_SUGGESTED_EPISODES("podcastsTasteSuggestedEpisodes"),
    PODCASTS_PLAYBACK_SPEED("podcastsPlaybackSpeedPage"),
    PODCASTS_CASTING_DEVICE_LIST("podcastsCastingDeviceListPage"),
    PODCASTS_SEE_ALL_EPISODES("podcastsSeeAllEpisodes"),
    PODCASTS_SEE_ALL_SHOWS("podcastsSeeAllShows"),
    BROWSE_HOME("browseHome"),
    NONE(""),
    PODCASTS_TASTE_CATEGORY_SELECTION("podcastsTasteCategorySelection"),
    PODCASTS_TASTE_PODCAST_SELECTION("podcastsTastePodcastSelection");

    private final String pageType;

    @Override // java.lang.Enum
    public final String toString() {
        return this.pageType;
    }

    UiMetricAttributes$PageType(final String pageType) {
        this.pageType = pageType;
    }
}
