package com.amazon.podcast.skills;

import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElement;
import Podcast.PlaybackInterface.v1_0.PauseMediaMethod;
import Podcast.PlaybackInterface.v1_0.ResumeMediaMethod;
import Podcast.SaveInterface.v1_0.SaveWriteElement;
import Podcast.Touch.CompactDetailTemplateInterface.v1_0.BookmarkElement;
import Podcast.Touch.CompactDetailTemplateInterface.v1_0.DownloadElement;
import Podcast.Touch.CompactDetailTemplateInterface.v1_0.SaveElement;
import Podcast.Touch.CompactDetailTemplateInterface.v1_0.ShareElement;
import SOAAppSyncInterface.v1_0.WriteMethod;
import SOACoreInterface.v1_0.Method;
import android.content.res.Resources;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.Queues;
import com.amazon.podcast.R$string;
import com.amazon.podcast.deeplinks.DeeplinkMethods;
import com.amazon.podcast.deeplinks.Deeplinks;
import com.amazon.podcast.downloads.Download;
import com.amazon.podcast.metrics.UiMetricAttributes$ActionType;
import com.amazon.podcast.metrics.UiMetricAttributes$ContentName;
import com.amazon.podcast.metrics.UiMetricAttributes$EntityIdType;
import com.amazon.podcast.metrics.UiMetricAttributes$EntityType;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.metrics.UiMetricMethods;
import com.amazon.podcast.metrics.entities.EntityTypes;
import com.amazon.podcast.redhoodPlus.ContentTraits;
import com.amazon.podcast.redhoodPlus.MetricsEntityIds;
import com.amazon.podcast.redhoodPlus.PlaybackModes;
import com.amazon.podcast.redhoodPlus.Upsells;
import com.amazon.podcast.views.Strings;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
final class DownloadCompactDetailElements {
    private static final Logger logger = LoggerFactory.getLogger("DownloadCompactDetailElements");

    DownloadCompactDetailElements() {
    }

    public BookmarkElement bookmarkElement(final Download download) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(PlayDownloadedEpisodesSkill.prepareInvoke(download.getId()));
        UiMetricAttributes$ActionType uiMetricAttributes$ActionType = UiMetricAttributes$ActionType.PLAY_PODCAST_EPISODE;
        arrayList.addAll(uiMetricClickMethods(download, uiMetricAttributes$ActionType));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.addAll(pause());
        arrayList2.addAll(uiMetricClickMethods(download, UiMetricAttributes$ActionType.PLAY_PAUSE_PODCAST_EPISODE));
        ArrayList arrayList3 = new ArrayList();
        arrayList3.addAll(resume());
        arrayList3.addAll(uiMetricClickMethods(download, uiMetricAttributes$ActionType));
        return BookmarkElement.builder().withId(download.getId()).withTotalDurationMilliseconds(Long.valueOf(download.getDurationMilliseconds())).withOnPlaySelected(arrayList).withOnPauseSelected(arrayList2).withOnResumeSelected(arrayList3).build();
    }

    public DownloadElement downloadElement(final Download download) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(DeeplinkMethods.download(download.getId()));
        arrayList.addAll(uiMetricClickMethods(download, UiMetricAttributes$ActionType.DOWNLOAD_PODCAST_EPISODE));
        return DownloadElement.builder().withId(download.getId()).withOnDownload(arrayList).build();
    }

    public ShareElement shareElement(final Download download, final Resources resources) {
        String str = String.format(resources.getString(R$string.podcast_share_episode_description), download.getTitle(), download.getPodcastTitle());
        String string = resources.getString(R$string.podcast_share_episode_label);
        return ShareElement.builder().withId(MetricsEntityIds.podcastEpisode(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), download.getId(), download.getPodcastEpisodeVariantId())).withTitle(download.getTitle()).withSubtitle(download.getPodcastTitle()).withImageUrl(download.getImage()).withDescription(str).withDeeplinkUrl(Deeplinks.episodeForShare(download.getPodcastId(), download.getId(), download.getPodcastTitle(), download.getTitle())).withLabel(string).withPrompt(resources.getString(R$string.podcast_share_episode_prompt)).withMetricsEntityType(EntityTypes.PODCAST_EPISODE.getValue()).build();
    }

    public SaveElement saveElement(final Download download) {
        return SaveElement.builder().withId(download.getId()).withOnSave(onSaveMethods(download)).withOnUnsave(onUnSaveMethods(download)).build();
    }

    public final EpisodeOptionsElement episodeOptionsElementWithoutHeader(final Download download, final Resources resources, final boolean isPlayable) {
        return new EpisodeOptionsElements().episodeOptionsElementWithoutHeader(download, UiMetricAttributes$PageType.PODCAST_EPISODE_DETAIL, resources, isPlayable);
    }

    private List<Method> onSaveMethods(final Download download) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(WriteMethod.builder().withQueue(Queues.appSync()).withElement(Collections.singletonList(saveWriteElement(download, true))).build());
        arrayList.addAll(uiMetricClickMethods(download, UiMetricAttributes$ActionType.SAVE_PODCAST_EPISODE));
        return arrayList;
    }

    private List<Method> onUnSaveMethods(final Download download) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(WriteMethod.builder().withQueue(Queues.appSync()).withElement(Collections.singletonList(saveWriteElement(download, false))).build());
        arrayList.addAll(uiMetricClickMethods(download, UiMetricAttributes$ActionType.UNSAVE_PODCAST_EPISODE));
        return arrayList;
    }

    private SaveWriteElement saveWriteElement(final Download download, final boolean isSaved) {
        ArrayList arrayList = new ArrayList();
        try {
            arrayList.addAll(Strings.convertStringToList(download.getAuthors()));
        } catch (IOException e) {
            logger.error("Error converting authors string to list", (Throwable) e);
        }
        String seasonNumber = download.getSeasonNumber();
        return SaveWriteElement.builder().withId(download.getId()).withAuthors(arrayList).withDescription(download.getDescription()).withTitle(download.getTitle()).withImage(download.getImage()).withPodcastImage(download.getPodcastImage()).withPodcastId(download.getPodcastId()).withPodcastTitle(download.getPodcastTitle()).withSaved(isSaved).withTotalDurationMilliseconds(Long.valueOf(download.getDurationMilliseconds())).withSeasonNumber(StringUtils.isNumeric(seasonNumber) ? Integer.valueOf(Integer.parseInt(seasonNumber)) : null).withPublishDate(download.getPublishDate()).withPlaybackMode(PlaybackModes.mode(download.getPlaybackMode())).withAvailabilityDate(download.getAvailabilityDate()).withAvailableUpsells(Upsells.types(download.getAvailableUpsells())).withContentTraits(ContentTraits.traits(download.getContentTraits())).withPodcastEpisodeVariantId(download.getPodcastEpisodeVariantId()).withPodcastShowVariantId(download.getPodcastShowVariantId()).build();
    }

    private List<Method> pause() {
        return Arrays.asList(PauseMediaMethod.builder().withQueue(Queues.playback()).withForced(Boolean.FALSE).build());
    }

    private List<Method> resume() {
        return Arrays.asList(ResumeMediaMethod.builder().withQueue(Queues.playback()).withForced(Boolean.FALSE).build());
    }

    private List<Method> uiMetricClickMethods(final Download download, final UiMetricAttributes$ActionType actionType) {
        return UiMetricMethods.onClicked(UiMetricAttributes$PageType.PODCAST_EPISODE_DETAIL, actionType, UiMetricAttributes$EntityType.PODCAST_EPISODE, UiMetricAttributes$EntityIdType.PODCAST_EPISODE_ID, MetricsEntityIds.podcastEpisode(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), download.getId(), download.getPodcastEpisodeVariantId()), (Integer) null, UiMetricAttributes$ContentName.PODCASTS_LIBRARY_EPISODE_DETAIL);
    }
}
