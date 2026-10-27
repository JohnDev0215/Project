package com.amazon.podcast.skills;

import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElement;
import Podcast.JumpBackInInterface.v1_0.JumpBackInCacheItemElement;
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
import com.amazon.podcast.metrics.UiMetricAttributes$ActionType;
import com.amazon.podcast.metrics.UiMetricAttributes$ContentName;
import com.amazon.podcast.metrics.UiMetricAttributes$DetailPageItemIdType;
import com.amazon.podcast.metrics.UiMetricAttributes$EntityIdType;
import com.amazon.podcast.metrics.UiMetricAttributes$EntityType;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.metrics.UiMetricMethods;
import com.amazon.podcast.metrics.entities.EntityTypes;
import com.amazon.podcast.redhoodPlus.ContentTraits;
import com.amazon.podcast.redhoodPlus.MetricsEntityIds;
import com.amazon.podcast.redhoodPlus.PlaybackModes;
import com.amazon.podcast.redhoodPlus.Upsells;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
final class JumpBackInCompactDetailElements {
    private static final Logger logger = LoggerFactory.getLogger("JumpBackInCompactDetailElements");

    JumpBackInCompactDetailElements() {
    }

    public BookmarkElement jumpBackInElement(final JumpBackInCacheItemElement jumpBackIn, final Resources resources) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(DeeplinkMethods.jumpBackInEpisodePlay(jumpBackIn.getEpisodeId(), jumpBackIn.getPlayDeeplink(), resources));
        UiMetricAttributes$ActionType uiMetricAttributes$ActionType = UiMetricAttributes$ActionType.PLAY_PODCAST_EPISODE;
        arrayList.addAll(uiMetricClickMethods(jumpBackIn, uiMetricAttributes$ActionType));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.addAll(pause());
        arrayList2.addAll(uiMetricClickMethods(jumpBackIn, UiMetricAttributes$ActionType.PLAY_PAUSE_PODCAST_EPISODE));
        ArrayList arrayList3 = new ArrayList();
        arrayList3.addAll(resume());
        arrayList3.addAll(uiMetricClickMethods(jumpBackIn, uiMetricAttributes$ActionType));
        new ArrayList().addAll(UiMetricMethods.onPageViewed(UiMetricAttributes$PageType.PODCAST_EPISODE_DETAIL, UiMetricAttributes$DetailPageItemIdType.PODCAST_EPISODE_ID, jumpBackIn.getEpisodeId()));
        return BookmarkElement.builder().withId(jumpBackIn.getEpisodeId()).withTotalDurationMilliseconds(jumpBackIn.getTotalDurationMilliseconds()).withOnPlaySelected(arrayList).withOnPauseSelected(arrayList2).withOnResumeSelected(arrayList3).build();
    }

    private List<Method> pause() {
        return Collections.singletonList(PauseMediaMethod.builder().withQueue(Queues.playback()).withForced(Boolean.FALSE).build());
    }

    private List<Method> resume() {
        return Collections.singletonList(ResumeMediaMethod.builder().withQueue(Queues.playback()).withForced(Boolean.FALSE).build());
    }

    public DownloadElement downloadElement(final JumpBackInCacheItemElement jumbBackIn) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(DeeplinkMethods.download(jumbBackIn.getEpisodeId()));
        arrayList.addAll(uiMetricClickMethods(jumbBackIn, UiMetricAttributes$ActionType.DOWNLOAD_PODCAST_EPISODE));
        return DownloadElement.builder().withId(jumbBackIn.getEpisodeId()).withOnDownload(arrayList).build();
    }

    public SaveElement saveElement(final JumpBackInCacheItemElement jumpBackIn) {
        return SaveElement.builder().withId(jumpBackIn.getEpisodeId()).withOnSave(onSaveMethods(jumpBackIn)).withOnUnsave(onUnSaveMethods(jumpBackIn)).build();
    }

    public ShareElement shareElement(final JumpBackInCacheItemElement jumbBackIn, final Resources resources) {
        String str = String.format(resources.getString(R$string.podcast_share_episode_description), jumbBackIn.getTitle(), jumbBackIn.getPodcastTitle());
        String string = resources.getString(R$string.podcast_share_episode_label);
        return ShareElement.builder().withId(MetricsEntityIds.podcastEpisode(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), jumbBackIn.getEpisodeId(), jumbBackIn.getPodcastEpisodeVariantId())).withTitle(jumbBackIn.getTitle()).withSubtitle(jumbBackIn.getPodcastTitle()).withImageUrl(jumbBackIn.getImage()).withDescription(str).withDeeplinkUrl(Deeplinks.episodeForShare(jumbBackIn.getPodcastId(), jumbBackIn.getEpisodeId(), jumbBackIn.getPodcastTitle(), jumbBackIn.getTitle())).withLabel(string).withPrompt(resources.getString(R$string.podcast_share_episode_prompt)).withMetricsEntityType(EntityTypes.PODCAST_EPISODE.getValue()).build();
    }

    public final EpisodeOptionsElement episodeOptionsElementWithoutHeader(final JumpBackInCacheItemElement jumpBackIn, final Resources resources, final boolean isPlayable) {
        return new EpisodeOptionsElements().episodeOptionsElementWithoutHeader(jumpBackIn, UiMetricAttributes$PageType.PODCAST_EPISODE_DETAIL, resources, isPlayable);
    }

    private List<Method> onSaveMethods(final JumpBackInCacheItemElement jumpBackIn) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(WriteMethod.builder().withQueue(Queues.appSync()).withElement(Collections.singletonList(saveWriteElement(jumpBackIn, true))).build());
        arrayList.addAll(uiMetricClickMethods(jumpBackIn, UiMetricAttributes$ActionType.SAVE_PODCAST_EPISODE));
        return arrayList;
    }

    private List<Method> onUnSaveMethods(final JumpBackInCacheItemElement jumpBackIn) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(WriteMethod.builder().withQueue(Queues.appSync()).withElement(Collections.singletonList(saveWriteElement(jumpBackIn, false))).build());
        arrayList.addAll(uiMetricClickMethods(jumpBackIn, UiMetricAttributes$ActionType.UNSAVE_PODCAST_EPISODE));
        return arrayList;
    }

    private SaveWriteElement saveWriteElement(final JumpBackInCacheItemElement jumpBackIn, final boolean isSaved) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(jumpBackIn.getAuthors());
        return SaveWriteElement.builder().withId(jumpBackIn.getEpisodeId()).withAuthors(arrayList).withDescription(jumpBackIn.getDescription()).withTitle(jumpBackIn.getTitle()).withImage(jumpBackIn.getImage()).withPodcastImage(jumpBackIn.getPodcastImage()).withPodcastId(jumpBackIn.getPodcastId()).withPodcastTitle(jumpBackIn.getPodcastTitle()).withSaved(isSaved).withTotalDurationMilliseconds(jumpBackIn.getTotalDurationMilliseconds()).withSeasonNumber(jumpBackIn.getSeasonNumber()).withPlaybackMode(PlaybackModes.mode(jumpBackIn.getPlaybackMode())).withAvailableUpsells(Upsells.types(jumpBackIn.getAvailableUpsells())).withContentTraits(ContentTraits.traits(jumpBackIn.getContentTraits())).withAvailabilityDate(jumpBackIn.getAvailabilityDate()).withPodcastEpisodeVariantId(jumpBackIn.getPodcastEpisodeVariantId()).withPodcastShowVariantId(jumpBackIn.getPodcastShowVariantId()).build();
    }

    private List<Method> uiMetricClickMethods(final JumpBackInCacheItemElement jumpBackInCacheItemElement, final UiMetricAttributes$ActionType actionType) {
        return UiMetricMethods.onClicked(UiMetricAttributes$PageType.PODCAST_EPISODE_DETAIL, actionType, UiMetricAttributes$EntityType.PODCAST_EPISODE, UiMetricAttributes$EntityIdType.PODCAST_EPISODE_ID, MetricsEntityIds.podcastEpisode(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), jumpBackInCacheItemElement.getEpisodeId(), jumpBackInCacheItemElement.getPodcastEpisodeVariantId()), (Integer) null, UiMetricAttributes$ContentName.PODCASTS_LIBRARY_EPISODE_DETAIL);
    }
}
