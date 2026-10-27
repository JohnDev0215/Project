package com.amazon.podcast.followsRecommendedShows;

import Podcast.FollowInterface.v1_0.FollowWriteElement;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.FollowElement;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.HorizontalItemElement;
import Podcast.Touch.WidgetsInterface.v1_0.DescriptiveShowcaseElement;
import SOAAppSyncInterface.v1_0.WriteMethod;
import SOACoreInterface.v1_0.Method;
import android.content.Context;
import com.amazon.podcast.Queues;
import com.amazon.podcast.R$string;
import com.amazon.podcast.bookmark.Bookmark;
import com.amazon.podcast.deeplinks.DeeplinkMethods;
import com.amazon.podcast.metrics.UiMetricAttributes$ActionType;
import com.amazon.podcast.metrics.UiMetricAttributes$ContainerType;
import com.amazon.podcast.metrics.UiMetricAttributes$ContentName;
import com.amazon.podcast.metrics.UiMetricAttributes$EntityIdType;
import com.amazon.podcast.metrics.UiMetricAttributes$EntityType;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.metrics.UiMetricMethods;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class FollowsSuggestedShowsCreateElements {
    public static DescriptiveShowcaseElement createDescriptiveShowcaseElement(final Context context, final boolean shouldCreateMetrics, final List<HorizontalItemElement> horizontalItemElements) {
        List<Method> onViewedMethods;
        if (shouldCreateMetrics) {
            onViewedMethods = getOnViewedMethods();
        } else {
            onViewedMethods = Collections.EMPTY_LIST;
        }
        return DescriptiveShowcaseElement.builder().withTitle(context.getResources().getString(R$string.podcast_follow_shows_you_listened_to)).withOnViewed(onViewedMethods).withItems(horizontalItemElements).build();
    }

    private static List<Method> getOnViewedMethods() {
        return UiMetricMethods.onContentViewed(UiMetricAttributes$PageType.PODCASTS_LIBRARY_SHOWS, UiMetricAttributes$ContainerType.ITEM_LIST, UiMetricAttributes$ContentName.PODCAST_DESCRIPTIVE_SHOWCASE);
    }

    public static HorizontalItemElement createHorizontalItemElement(final int index, final Bookmark bookmark, final Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(DeeplinkMethods.podcast(bookmark, context.getResources()));
        UiMetricAttributes$PageType uiMetricAttributes$PageType = UiMetricAttributes$PageType.PODCASTS_LIBRARY_SHOWS;
        UiMetricAttributes$ActionType uiMetricAttributes$ActionType = UiMetricAttributes$ActionType.SELECT_PODCAST_SHOW;
        UiMetricAttributes$EntityType uiMetricAttributes$EntityType = UiMetricAttributes$EntityType.PODCAST_SHOW;
        UiMetricAttributes$EntityIdType uiMetricAttributes$EntityIdType = UiMetricAttributes$EntityIdType.PODCAST_SHOW_ID;
        String podcastId = bookmark.getPodcastId();
        Integer numValueOf = Integer.valueOf(index);
        UiMetricAttributes$ContentName uiMetricAttributes$ContentName = UiMetricAttributes$ContentName.PODCAST_DESCRIPTIVE_SHOWCASE;
        arrayList.addAll(UiMetricMethods.onClicked(uiMetricAttributes$PageType, uiMetricAttributes$ActionType, uiMetricAttributes$EntityType, uiMetricAttributes$EntityIdType, podcastId, numValueOf, uiMetricAttributes$ContentName));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(WriteMethod.builder().withQueue(Queues.followSync()).withElement(Arrays.asList(FollowWriteElement.builder().withTitle(bookmark.getPodcastTitle()).withImage(bookmark.getPodcastImage()).withPublisher(bookmark.getAuthors()).withFollowed(true).withId(bookmark.getPodcastId()).build())).build());
        arrayList2.addAll(UiMetricMethods.onClicked(uiMetricAttributes$PageType, UiMetricAttributes$ActionType.FOLLOW_PODCAST_SHOW, uiMetricAttributes$EntityType, uiMetricAttributes$EntityIdType, bookmark.getPodcastId(), Integer.valueOf(index), uiMetricAttributes$ContentName));
        return HorizontalItemElement.builder().withPrimaryText(bookmark.getPodcastTitle()).withSecondaryText(updatePublisherTitleForDisplay(bookmark.getAuthors())).withImage(bookmark.getPodcastImage()).withOnItemSelected(arrayList).withFollow(FollowElement.builder().withId(bookmark.getPodcastId()).withOnFollow(arrayList2).withOnUnFollow(Collections.emptyList()).build()).build();
    }

    private static String updatePublisherTitleForDisplay(final String podcastPublisherEntry) {
        return StringUtils.removeEnd(StringUtils.removeStart(podcastPublisherEntry, "[\""), "\"]");
    }
}
