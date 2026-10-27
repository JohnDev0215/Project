package com.amazon.podcast.metrics;

/* JADX INFO: loaded from: classes5.dex */
public enum UiMetricAttributes$ContentName {
    PODCASTS_LIBRARY_LATEST("podcastsLibraryLatest"),
    PODCASTS_LIBRARY_SHOWS("podcastsLibraryShows"),
    PODCASTS_LIBRARY_DOWNLOADS("podcastsLibraryDownloads"),
    PODCASTS_LIBRARY_PLAYLIST("podcastsLibraryPlaylist"),
    PODCAST_SHOW_DETAIL("podcastShowDetail"),
    PODCAST_EPISODE_DETAIL("podcastEpisodeDetail"),
    PODCASTS_LIBRARY_EPISODE_DETAIL("podcastsLibraryEpisodeDetail"),
    PODCASTS_FOLLOWS_SHOVELER("podcastsFollowsShoveler"),
    PODCASTS_SAVES_SHOVELER("podcastsSavesShoveler"),
    PODCASTS_BOOKMARKS_SHOVELER("podcastsBookmarksShoveler"),
    PODCASTS_SAVED_EPISODES("podcastsSavedEpisodes"),
    PODCASTS_PINNED_EPISODE("podcastsPinnedEpisode"),
    PODCASTS_FOLLOWED_SHOWS("podcastsFollowedShows"),
    PODCAST_PLAYBACK_BUTTONS("podcastPlaybackButtons"),
    PODCAST_CONTEXT_MENU("podcastContextMenu"),
    PODCASTS_JUMP_BACK_IN_EPISODES("podcastsJumpBackInEpisodes"),
    PODCASTS_JUMP_BACK_IN_SHOVELER("podcastsJumpBackInShoveler"),
    PODCAST_REFINEMENT_BAR("podcastRefinementBar"),
    PODCAST_SHOW_DETAIL_DOWNLOADS("podcastShowDetailDownloads"),
    PODCAST_NOW_PLAYING("podcastNowPlaying"),
    PODCAST_MINI_PLAYER("podcastMiniPlayer"),
    PODCAST_SHOW_ABOUT("podcastShowAbout"),
    PODCASTS_TASTE_SUGGESTED_EPISODES("podcastsTasteSuggestedEpisodes"),
    PODCASTS_SCRUB_INGRESS("podcastsScrubIngress"),
    PODCASTS_HALF_SCRUB("podcastsHalfScrub"),
    PODCASTS_FULL_SCRUB("podcastsFullScrub"),
    PODCASTS_HOME("podcastsHomePage"),
    PODCASTS_PLAYBACK_SPEED("podcastsPlaybackSpeed"),
    PODCAST_DESCRIPTIVE_SHOWCASE("podcastDescriptiveShowcase"),
    PODCAST_CARD_PTC("podcastCardPTC"),
    PODCASTS_BITES_VISUAL_SHOVELER("podcastsBitesShoveler"),
    PODCAST_BITES_VISUAL_SHOVELER_PLAY_BUTTON("podcastBitesShovelerPlayButton"),
    PODCAST_BITES_VISUAL_SHOVELER_ITEM("podcastBitesShovelerItem");

    private final String contentName;

    @Override // java.lang.Enum
    public final String toString() {
        return this.contentName;
    }

    UiMetricAttributes$ContentName(final String contentName) {
        this.contentName = contentName;
    }
}
