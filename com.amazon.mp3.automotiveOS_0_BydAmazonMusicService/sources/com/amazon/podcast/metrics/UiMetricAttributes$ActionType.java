package com.amazon.podcast.metrics;

/* JADX INFO: loaded from: classes5.dex */
public enum UiMetricAttributes$ActionType {
    SELECT_PODCAST_SHOW("selectPodcastShow"),
    SELECT_PODCAST_EPISODE("selectPodcastEpisode"),
    PLAY_PODCAST_EPISODE("playPodcastEpisode"),
    PLAY_PAUSE_PODCAST_EPISODE("playPausePodcastEpisode"),
    FAST_FORWARD_PODCAST_EPISODE("fastForwardPodcastEpisode"),
    REWIND_PODCAST_EPISODE("rewindPodcastEpisode"),
    SELECT_PODCAST_PLAYBACK_SPEED("selectPodcastPlaybackSpeed"),
    SELECT_PODCAST_SLEEP_TIMER("selectPodcastSleepTimer"),
    DOWNLOAD_PODCAST_EPISODE("downloadPodcastEpisode"),
    REMOVE_DOWNLOAD_PODCAST_EPISODE("removeDownloadPodcastEpisode"),
    SAVE_PODCAST_EPISODE("savePodcastEpisode"),
    UNSAVE_PODCAST_EPISODE("unsavePodcastEpisode"),
    FOLLOW_PODCAST_SHOW("followPodcastShow"),
    UNFOLLOW_PODCAST_SHOW("unfollowPodcastShow"),
    GO_PODCAST_SHOW("goPodcastShow"),
    GO_PODCASTS_HOME("goPodcastsHome"),
    GO_PODCASTS_TASTE("goPodcastsTaste"),
    GO_PODCASTS_BITES("goPodcastsBites"),
    EXPAND_PODCAST_EPISODE_ABOUT("expandPodcastEpisodeAbout"),
    EXPAND_PODCAST_PLAYBACK_SPEED("expandPodcastPlaybackSpeed"),
    EXPAND_READ_MORE_READ_LESS("expandReadMoreReadLess"),
    EXPAND_PODCAST_MINI_PLAYER("expandPodcastMiniPlayer"),
    SELECT_PODCAST_CONTEXT_MENU("selectPodcastContextMenu"),
    SHARE_PODCAST_EPISODE("sharePodcastEpisode"),
    MARK_PODCAST_EPISODE_PLAYED("markPodcastEpisodePlayed"),
    MARK_PODCAST_EPISODE_UNPLAYED("markPodcastEpisodeUnplayed"),
    CLOSE_PODCAST_OVERLAY_CONTEXT_MENU("closePodcastOverlayContextMenu"),
    SELECT_PODCAST_FILTER("selectPodcastFilter"),
    EXPAND_PODCAST_REFINEMENT_BAR_SORT_MENU("expandPodcastRefinementBarSortMenu"),
    SELECT_SORT_APPLY("selectSortApply"),
    SELECT_SORT_RESET("selectSortReset"),
    SELECT_DISMISS_PODCAST_REFINEMENT_BAR_SORT_MENU("selectDismissPodcastRefinementBarSortMenu"),
    SELECT_FILTER_UNPLAYED_PODCAST_EPISODE("selectFilterUnplayedPodcastEpisode"),
    SELECT_SORT_DATE_DOWNLOADED("selectSortDateDownloaded"),
    SELECT_SORT_DATE_ADDED("selectSortDateAdded"),
    SELECT_SORT_CHRONOLOGICAL_OLDEST_FIRST("selectSortChronologicalOldestFirst"),
    SELECT_SORT_CHRONOLOGICAL_NEWEST_FIRST("selectSortChronologicalNewestFirst"),
    SELECT_SORT_SHOW_NAME("selectSortShowName"),
    EXPAND_FULL_SCRUB("expandFullScrub"),
    EXPAND_CARD_PLAYER("expandCardPlayer"),
    SELECT_SCRUB_SEGMENT("selectScrubSegment"),
    CLOSE_SCRUB("closeScrub"),
    SELECT_PODCAST_UPSELL("selectPodcastUpsell"),
    REPORT_PODCAST_EPISODE("reportPodcastEpisode");

    private final String actionType;

    @Override // java.lang.Enum
    public final String toString() {
        return this.actionType;
    }

    UiMetricAttributes$ActionType(final String actionType) {
        this.actionType = actionType;
    }
}
