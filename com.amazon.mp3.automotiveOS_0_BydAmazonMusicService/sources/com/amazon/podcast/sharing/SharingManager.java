package com.amazon.podcast.sharing;

import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.ShareElement;
import android.content.Context;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.metrics.entities.EntityTypes;

/* JADX INFO: loaded from: classes5.dex */
public class SharingManager {
    public static void handlePodcastDetailTemplateV2ShareClick(final Context context, final ShareElement shareElement) {
        if (shareElement == null) {
            return;
        }
        callShare(context, shareElement.getId(), shareElement.getTitle(), shareElement.getSubtitle(), shareElement.getDescription(), shareElement.getLabel(), shareElement.getPrompt(), shareElement.getImageUrl(), shareElement.getDeeplinkUrl(), shareElement.getMetricsEntityType());
    }

    public static void handleEpisodeOptionsShareClick(final Context context, final Podcast.EpisodeOptionsInterface.v1_0.ShareElement shareElement) {
        if (shareElement == null) {
            return;
        }
        callShare(context, shareElement.getId(), shareElement.getTitle(), shareElement.getSubtitle(), shareElement.getDescription(), shareElement.getLabel(), shareElement.getPrompt(), shareElement.getImageUrl(), shareElement.getDeeplinkUrl(), shareElement.getMetricsEntityType());
    }

    public static void handleCompactDetailTemplateShareClick(final Context context, final Podcast.Touch.CompactDetailTemplateInterface.v1_0.ShareElement shareElement) {
        if (shareElement != null) {
            callShare(context, shareElement.getId(), shareElement.getTitle(), shareElement.getSubtitle(), shareElement.getDescription(), shareElement.getLabel(), shareElement.getPrompt(), shareElement.getImageUrl(), shareElement.getDeeplinkUrl(), shareElement.getMetricsEntityType());
        }
    }

    private static void callShare(final Context context, final String id, final String title, final String subtitle, final String description, final String label, final String promptMessage, final String imageUrl, final String deeplinkUrl, final String metricsEntityType) {
        if (metricsEntityType.equalsIgnoreCase(EntityTypes.PODCAST_SHOW.getValue())) {
            Podcast.getSharingProvider().sharePodcastShow(context, new SharePodcastShowElement(id, title, description, label, promptMessage, imageUrl, deeplinkUrl, metricsEntityType));
        } else if (metricsEntityType.equalsIgnoreCase(EntityTypes.PODCAST_EPISODE.getValue())) {
            Podcast.getSharingProvider().sharePodcastEpisode(context, new SharePodcastEpisodeElement(id, title, subtitle, description, label, promptMessage, imageUrl, deeplinkUrl, metricsEntityType));
        } else if (metricsEntityType.equalsIgnoreCase(EntityTypes.PODCAST_BITE.getValue())) {
            Podcast.getSharingProvider().sharePodcastBite(context, new SharePodcastBiteElement(id, title, description, label, promptMessage, imageUrl, deeplinkUrl, metricsEntityType));
        }
    }
}
