package com.amazon.podcast.metrics.latency;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public enum LatencyMetricAttributes$PageType {
    PODCASTS_HOME("podcastsHomePage"),
    PODCASTS_LIBRARY_LATEST("podcastsLibraryLatest"),
    PODCAST_SHOW_ABOUT("podcastShowAbout"),
    PODCASTS_LIBRARY_SHOWS("podcastsLibraryShows"),
    PODCASTS_LIBRARY_DOWNLOADS("podcastsLibraryDownloads"),
    PODCASTS_LIBRARY_PLAYLIST("podcastsLibraryPlaylist"),
    PODCAST_SHOW_DETAIL("podcastShowDetail"),
    PODCAST_EPISODE_DETAIL("podcastEpisodeDetail"),
    PODCASTS_FOLLOWED_SHOWS("podcastsFollowedShows"),
    PODCASTS_SAVED_EPISODES("podcastsSavedEpisodes"),
    PODCAST_NOW_PLAYING("podcastNowPlaying"),
    PODCASTS_JUMP_BACK_IN_EPISODES("podcastsJumpBackInEpisodes"),
    PODCASTS_SEE_ALL_EPISODES("podcastsSeeAllEpisodes"),
    PODCASTS_SEE_ALL_SHOWS("podcastsSeeAllShows"),
    PODCASTS_CURATED_PAGE("podcastsCuratedPage"),
    PODCASTS_TASTE_CATEGORY_SELECTION("podcastsTasteCategorySelection"),
    PODCASTS_TASTE_PODCAST_SELECTION("podcastsTastePodcastSelection"),
    PODCASTS_TASTE_SUGGESTED_EPISODES("podcastsTasteSuggestedEpisodes");

    private static final Map<String, LatencyMetricAttributes$PageType> PAGETYPE_TO_ENUM = new HashMap();
    private final String pageType;

    static {
        for (LatencyMetricAttributes$PageType latencyMetricAttributes$PageType : values()) {
            PAGETYPE_TO_ENUM.put(latencyMetricAttributes$PageType.pageType, latencyMetricAttributes$PageType);
        }
    }

    public static final LatencyMetricAttributes$PageType of(final String pageType) {
        return PAGETYPE_TO_ENUM.getOrDefault(pageType, null);
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.pageType;
    }

    LatencyMetricAttributes$PageType(final String pageType) {
        this.pageType = pageType;
    }
}
