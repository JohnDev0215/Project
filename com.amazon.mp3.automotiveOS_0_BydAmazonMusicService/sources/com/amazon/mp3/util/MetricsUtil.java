package com.amazon.mp3.util;

import android.content.Context;
import com.amazon.mp3.search.SearchItemUtils;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.metrics.SearchMetricsServiceDefault;
import com.amazon.music.find.model.search.SearchItem;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyEventDescriptor;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyTracker;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiPageViewEvent;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class MetricsUtil {
    private static final String TAG = "MetricsUtil";
    public static final List<ActionType> NOW_PLAYING_ALLOWED_ACTION_TYPES = Arrays.asList(ActionType.PLAY_PLAYLIST, ActionType.PLAY_CURATED_PLAYLIST, ActionType.PLAY_SHUFFLE_ALL, ActionType.PLAY_SONG, ActionType.PLAY_ALBUM, ActionType.PLAY_ARTIST, ActionType.PLAY_STATION, ActionType.PLAY_RECENTLY_PLAYED);
    public static final List<ActionType> PLAYLIST_DETAIL_ALLOWED_ACTION_TYPES = Arrays.asList(ActionType.SELECT_PLAYLIST, ActionType.SELECT_COMMUNITY_PLAYLIST);
    public static final List<ActionType> ALBUM_DETAIL_ALLOWED_ACTION_TYPES = Arrays.asList(ActionType.SELECT_ALBUM, ActionType.VIEW_ALBUM_OVERFLOW);
    public static final List<ActionType> LIBRARY_PLAYLISTS_LIST_ALLOWED_ACTION_TYPES = Arrays.asList(ActionType.GO_LIBRARY_PLAYLISTS);

    public enum MetricsItemType {
        ALBUM,
        ARTIST,
        TRACK,
        PLAYLIST,
        STATION,
        SOCCER,
        VIDEO,
        VIDEO_PLAYLIST,
        PODCAST_SHOW,
        PODCAST_EPISODE
    }

    public static String getContentNamePrefixForSearchContent(final boolean isOfflineSearch) {
        return isOfflineSearch ? SearchMetricsServiceDefault.CONTENT_NAME_BASE_OFFLINE_LIBRARY_SEARCH : SearchMetricsServiceDefault.CONTENT_NAME_BASE_ONLINE_LIBRARY_SEARCH;
    }

    public static Map<String, String> getContentInfoForLibraryContent(final int containerIndex, final String contentName) {
        HashMap map = new HashMap();
        map.put(ContextMappingConstants.CONTENT_SRC, SearchMetricsServiceDefault.CONTENT_SRC_CLIENT);
        map.put("contentName", contentName);
        if (containerIndex != -1) {
            map.put("containerIndex", Integer.toString(containerIndex));
        }
        return map;
    }

    public static String getContentName(final MetricsItemType metricsItemType, final boolean isOfflineSearch) {
        return getContentNamePrefixForSearchContent(isOfflineSearch) + getContentNameSuffixForSearchContent(metricsItemType);
    }

    public static String getContentNameForSearchItem(final SearchItem searchItem, final boolean isOfflineSearch) {
        return getContentName(SearchItemUtils.getMetricsItemType(searchItem), isOfflineSearch);
    }

    private static String getContentNameSuffixForSearchContent(final MetricsItemType metricsItemType) {
        if (metricsItemType == null) {
            return null;
        }
        if (MetricsItemType.ALBUM.equals(metricsItemType)) {
            return SearchMetricsServiceDefault.CONTENT_NAME_SUFFIX_ALBUMS;
        }
        if (MetricsItemType.ARTIST.equals(metricsItemType)) {
            return SearchMetricsServiceDefault.CONTENT_NAME_SUFFIX_ARTISTS;
        }
        if (MetricsItemType.PLAYLIST.equals(metricsItemType)) {
            return SearchMetricsServiceDefault.CONTENT_NAME_SUFFIX_PLAYLISTS;
        }
        if (MetricsItemType.TRACK.equals(metricsItemType)) {
            return SearchMetricsServiceDefault.CONTENT_NAME_SUFFIX_TRACKS;
        }
        return null;
    }

    public static Map<String, String> getContentInfoForSearchLibraryContent(final String searchTerm, final int numItemsAccessible, final String containerType, final int containerIndex, final String contentName) {
        Map<String, String> contentInfoForLibraryContent = getContentInfoForLibraryContent(containerIndex, contentName);
        contentInfoForLibraryContent.put("searchTerm", searchTerm);
        contentInfoForLibraryContent.put("numItemsAccessible", Integer.toString(numItemsAccessible));
        contentInfoForLibraryContent.put("containerType", containerType);
        return contentInfoForLibraryContent;
    }

    public static void sendUiClickMetric(final ActionType actionType, final String entityId, final EntityIdType entityIdType, final EntityType entityType, final Integer position, final PageType pageType, final InteractionType interactionType) {
        UiClickEvent.Builder builderWithEntityIdType = UiClickEvent.builder(actionType).withInteractionType(interactionType).withPageType(pageType).withEventTime(System.currentTimeMillis()).withEntityId(entityId).withEntityType(entityType).withEntityIdType(entityIdType);
        if (position != null) {
            builderWithEntityIdType.withEntityPos(position.intValue());
        }
        MetricsHolder.getManager().handleEvent(builderWithEntityIdType.build());
    }

    public static void sendUiClickMetric(final ActionType actionType, final InteractionType interactionType, final PageType pageType, final String entityId, final EntityIdType entityIdType) {
        emitLatencyStartLegMetricForAllowedActionTypes(actionType);
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(actionType).withInteractionType(interactionType).withPageType(pageType).withEventTime(System.currentTimeMillis()).withEntityId(entityId).withEntityIdType(entityIdType).build());
    }

    public static void sendUiPageViewMetric(final PageType pageType, final String detailPageItemId, final String detailPageItemIdType, final Context context) {
        MetricsHolder.getManager().handleEvent(UiPageViewEvent.builder(pageType.getMetricsValue()).withEventTime(System.currentTimeMillis()).withViewType(ScreenUtil.getScreenViewType(context)).withDetailPageItemId(detailPageItemId).withDetailPageItemIdType(detailPageItemIdType).build());
    }

    public static void emitLatencyStartLegMetricForAllowedActionTypes(final ActionType actionType) {
        if (NOW_PLAYING_ALLOWED_ACTION_TYPES.contains(actionType)) {
            emitLatencyStartLegMetric(PageLoadLatencyCode.NOW_PLAYING);
            return;
        }
        if (PLAYLIST_DETAIL_ALLOWED_ACTION_TYPES.contains(actionType)) {
            emitLatencyStartLegMetric(PageLoadLatencyCode.PLAYLIST_DETAIL);
            return;
        }
        if (LIBRARY_PLAYLISTS_LIST_ALLOWED_ACTION_TYPES.contains(actionType)) {
            emitLatencyStartLegMetric(PageLoadLatencyCode.LIBRARY_PLAYLISTS);
            return;
        }
        if (ALBUM_DETAIL_ALLOWED_ACTION_TYPES.contains(actionType)) {
            emitLatencyStartLegMetric(PageLoadLatencyCode.ALBUM_DETAIL);
            return;
        }
        Log.debug(TAG, "Skipping emitting latency start leg as action type [" + actionType + "] is not supported ");
    }

    public static void emitLatencyStartLegMetric(final PageLoadLatencyCode latencyCode) {
        LatencyTracker latencyTracker = LatencyTracker.INSTANCE;
        latencyTracker.clearLatencyTracker(latencyCode);
        latencyTracker.track(new LatencyEventDescriptor(LatencyTrackingLeg.START, latencyCode));
    }
}
