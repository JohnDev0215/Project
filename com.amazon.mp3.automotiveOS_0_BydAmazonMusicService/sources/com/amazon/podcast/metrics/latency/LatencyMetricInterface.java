package com.amazon.podcast.metrics.latency;

import com.amazon.music.metrics.mts.event.definition.latency.LatencyEventDescriptor;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyTracker;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import com.amazon.podcast.deeplinks.Deeplinks;
import com.amazonaws.util.StringUtils;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public class LatencyMetricInterface {
    private static final LatencyTracker LATENCY_TRACKER = LatencyTracker.INSTANCE;
    private static final Logger LOGGER = LoggerFactory.getLogger("LatencyMetricInterface");
    private static final Map<LatencyMetricAttributes$PageType, PageLoadLatencyCode> PAGE_TYPE_TO_LATENCY_CODE;

    static {
        HashMap map = new HashMap();
        PAGE_TYPE_TO_LATENCY_CODE = map;
        map.put(LatencyMetricAttributes$PageType.PODCASTS_HOME, PageLoadLatencyCode.PODCASTS_HOME);
        map.put(LatencyMetricAttributes$PageType.PODCASTS_LIBRARY_LATEST, PageLoadLatencyCode.PODCASTS_LIBRARY_LATEST);
        map.put(LatencyMetricAttributes$PageType.PODCAST_SHOW_ABOUT, PageLoadLatencyCode.PODCAST_SHOW_ABOUT);
        map.put(LatencyMetricAttributes$PageType.PODCASTS_LIBRARY_SHOWS, PageLoadLatencyCode.PODCASTS_LIBRARY_SHOWS);
        map.put(LatencyMetricAttributes$PageType.PODCASTS_LIBRARY_DOWNLOADS, PageLoadLatencyCode.PODCASTS_LIBRARY_DOWNLOADS);
        map.put(LatencyMetricAttributes$PageType.PODCASTS_LIBRARY_PLAYLIST, PageLoadLatencyCode.PODCASTS_LIBRARY_PLAYLIST);
        map.put(LatencyMetricAttributes$PageType.PODCAST_SHOW_DETAIL, PageLoadLatencyCode.PODCAST_SHOW_DETAIL);
        map.put(LatencyMetricAttributes$PageType.PODCAST_EPISODE_DETAIL, PageLoadLatencyCode.PODCAST_EPISODE_DETAIL);
        map.put(LatencyMetricAttributes$PageType.PODCASTS_FOLLOWED_SHOWS, PageLoadLatencyCode.PODCASTS_FOLLOWED_SHOWS);
        map.put(LatencyMetricAttributes$PageType.PODCASTS_SAVED_EPISODES, PageLoadLatencyCode.PODCASTS_SAVED_EPISODES);
        map.put(LatencyMetricAttributes$PageType.PODCAST_NOW_PLAYING, PageLoadLatencyCode.PODCAST_NOW_PLAYING);
        map.put(LatencyMetricAttributes$PageType.PODCASTS_JUMP_BACK_IN_EPISODES, PageLoadLatencyCode.PODCASTS_JUMP_BACK_IN_EPISODES);
        map.put(LatencyMetricAttributes$PageType.PODCASTS_SEE_ALL_EPISODES, PageLoadLatencyCode.PODCASTS_SEE_ALL_EPISODES);
        map.put(LatencyMetricAttributes$PageType.PODCASTS_SEE_ALL_SHOWS, PageLoadLatencyCode.PODCASTS_SEE_ALL_SHOWS);
        map.put(LatencyMetricAttributes$PageType.PODCASTS_CURATED_PAGE, PageLoadLatencyCode.PODCASTS_CURATED_PAGE);
        map.put(LatencyMetricAttributes$PageType.PODCASTS_TASTE_CATEGORY_SELECTION, PageLoadLatencyCode.PODCASTS_TASTE_CATEGORY_SELECTION);
        map.put(LatencyMetricAttributes$PageType.PODCASTS_TASTE_PODCAST_SELECTION, PageLoadLatencyCode.PODCASTS_TASTE_PODCAST_SELECTION);
        map.put(LatencyMetricAttributes$PageType.PODCASTS_TASTE_SUGGESTED_EPISODES, PageLoadLatencyCode.PODCASTS_TASTE_SUGGESTED_EPISODES);
    }

    public static void trackLatency(final LatencyTrackingLeg leg, final String pageType) {
        if (leg == null || pageType == null) {
            return;
        }
        try {
            trackLatency(leg, LatencyMetricAttributes$PageType.of(pageType));
        } catch (Exception e) {
            LOGGER.error(String.format("Unable to log latency metric for leg %s on page %s.", leg, pageType), (Throwable) e);
        }
    }

    public static void trackLatency(final LatencyTrackingLeg leg, final LatencyMetricAttributes$PageType pageType) {
        if (leg == null || pageType == null) {
            return;
        }
        try {
            LATENCY_TRACKER.track(new LatencyEventDescriptor(leg, pageLoadLatencyCode(pageType)));
        } catch (Exception e) {
            LOGGER.error(String.format("Unable to log latency metric for leg %s on page %s.", leg, pageType), (Throwable) e);
        }
    }

    public static LatencyMetricAttributes$PageType latencyPageTypeFromDeeplink(final String deeplink) {
        if (StringUtils.isBlank(deeplink)) {
            return null;
        }
        if (Deeplinks.isPodcastDeeplink(deeplink)) {
            return LatencyMetricAttributes$PageType.PODCAST_SHOW_DETAIL;
        }
        if (Deeplinks.isEpisodeDeeplink(deeplink)) {
            return LatencyMetricAttributes$PageType.PODCAST_EPISODE_DETAIL;
        }
        if (Deeplinks.isPodcastHomeDeeplink(deeplink)) {
            return LatencyMetricAttributes$PageType.PODCASTS_HOME;
        }
        if (Deeplinks.isPodcastTasteCollectionDeeplink(deeplink)) {
            return LatencyMetricAttributes$PageType.PODCASTS_TASTE_CATEGORY_SELECTION;
        }
        if (Deeplinks.isPodcastsCuratedPageDeeplink(deeplink)) {
            return LatencyMetricAttributes$PageType.PODCASTS_CURATED_PAGE;
        }
        return null;
    }

    private static PageLoadLatencyCode pageLoadLatencyCode(final LatencyMetricAttributes$PageType pageType) {
        if (pageType == null) {
            LOGGER.warn("Received null pageType to derive pageLoadLatencyCode.");
            return null;
        }
        Map<LatencyMetricAttributes$PageType, PageLoadLatencyCode> map = PAGE_TYPE_TO_LATENCY_CODE;
        if (!map.containsKey(pageType)) {
            LOGGER.warn(String.format("Unable to derive page load latency code from: %s", pageType));
            return null;
        }
        return map.get(pageType);
    }
}
