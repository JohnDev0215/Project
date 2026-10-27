package com.amazon.podcast.skills;

import Podcast.BookmarkInterface.v1_0.BookmarkWriteElement;
import Podcast.CompletedInterface.v1_0.CompletedWriteElement;
import Podcast.DeeplinkInterface.v1_0.NavigateToDeeplinkMethod;
import Podcast.EpisodeOptionsInterface.v1_0.CarPresetElement;
import Podcast.EpisodeOptionsInterface.v1_0.CompletedElement;
import Podcast.EpisodeOptionsInterface.v1_0.DownloadElement;
import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElement;
import Podcast.EpisodeOptionsInterface.v1_0.FollowElement;
import Podcast.EpisodeOptionsInterface.v1_0.GoToPodcastElement;
import Podcast.EpisodeOptionsInterface.v1_0.PlayEpisodeElement;
import Podcast.EpisodeOptionsInterface.v1_0.PodcastShowCarPresetElement;
import Podcast.EpisodeOptionsInterface.v1_0.ReportElement;
import Podcast.EpisodeOptionsInterface.v1_0.SaveElement;
import Podcast.EpisodeOptionsInterface.v1_0.ShareElement;
import Podcast.FollowInterface.v1_0.FollowWriteElement;
import Podcast.JumpBackInInterface.v1_0.JumpBackInCacheItemElement;
import Podcast.PlaybackInterface.v1_0.NavigateToPodcastMethod;
import Podcast.SaveInterface.v1_0.SaveWriteElement;
import SOAAppSyncInterface.v1_0.WriteMethod;
import SOACoreInterface.v1_0.Method;
import android.content.res.Resources;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.Queues;
import com.amazon.podcast.R$string;
import com.amazon.podcast.deeplinks.DeeplinkMethods;
import com.amazon.podcast.deeplinks.Deeplinks;
import com.amazon.podcast.downloads.Download;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
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
import com.amazon.podcast.save.Save;
import com.amazon.podcast.views.Strings;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class EpisodeOptionsElements {
    private static final Logger logger = LoggerFactory.getLogger("EpisodeOptionsElements");

    public final EpisodeOptionsElement episodeOptionsElementWithoutHeader(final Download download, final UiMetricAttributes$PageType pageType, final Resources resources, final boolean isPlayable) {
        return EpisodeOptionsElement.builder().withSave(saveElement(download, pageType)).withShare(shareElement(download, pageType, resources)).withDownload(isPlayable ? downloadElement(download, pageType) : null).withGoToPodcast(goToPodcastElement(download, pageType, resources)).withPlayEpisode(isPlayable ? playEpisodeElement(download, pageType, resources) : null).withFollow(followElement(download, pageType, resources)).withCompleted(isPlayable ? completedElement(download, pageType) : null).withOnEpisodeOptionsShow(UiMetricMethods.onClicked(pageType, UiMetricAttributes$ActionType.SELECT_PODCAST_CONTEXT_MENU, UiMetricAttributes$EntityType.PODCAST_EPISODE, UiMetricAttributes$EntityIdType.PODCAST_EPISODE_ID, download.getId(), (Integer) null, UiMetricAttributes$ContentName.PODCAST_CONTEXT_MENU)).withOnEpisodeOptionsDismiss(uiMetricClickMethods(MetricsEntityIds.podcastEpisode(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), download.getId(), download.getPodcastEpisodeVariantId()), UiMetricAttributes$ActionType.CLOSE_PODCAST_OVERLAY_CONTEXT_MENU, pageType)).withCarPreset(podcastShowCarPresetElement(download.getPodcastId())).withReport(reportElement(download, pageType)).build();
    }

    public final EpisodeOptionsElement episodeOptionsElementForNPS(final Download download, final UiMetricAttributes$PageType pageType, final Resources resources) {
        String strPodcastEpisode = MetricsEntityIds.podcastEpisode(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), download.getId(), download.getPodcastEpisodeVariantId());
        return EpisodeOptionsElement.builder().withSave(saveElement(download, pageType)).withShare(shareElement(download, UiMetricAttributes$PageType.PODCAST_NOW_PLAYING, resources)).withGoToPodcast(goToPodcastElementForNPS(MetricsEntityIds.podcastShow(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), download.getPodcastId(), download.getPodcastShowVariantId()), pageType)).withFollow(followElement(download, pageType, resources)).withCompleted(completedElement(download, pageType)).withOnEpisodeOptionsShow(UiMetricMethods.onClicked(pageType, UiMetricAttributes$ActionType.SELECT_PODCAST_CONTEXT_MENU, UiMetricAttributes$EntityType.PODCAST_EPISODE, UiMetricAttributes$EntityIdType.PODCAST_EPISODE_ID, download.getId(), (Integer) null, UiMetricAttributes$ContentName.PODCAST_CONTEXT_MENU)).withOnEpisodeOptionsDismiss(uiMetricClickMethods(strPodcastEpisode, UiMetricAttributes$ActionType.CLOSE_PODCAST_OVERLAY_CONTEXT_MENU, pageType)).withCarPreset(podcastShowCarPresetElement(download.getPodcastId())).withReport(reportElement(download, pageType)).build();
    }

    public final EpisodeOptionsElement episodeOptionsElementWithHeader(final Download download, final UiMetricAttributes$PageType pageType, final Resources resources, final boolean isPlayable) {
        return EpisodeOptionsElement.builder().withImage(download.getImage()).withTitle(download.getTitle()).withSubTitle(download.getPodcastTitle()).withSave(saveElement(download, pageType)).withShare(shareElement(download, pageType, resources)).withDownload(isPlayable ? downloadElement(download, pageType) : null).withGoToPodcast(goToPodcastElement(download, pageType, resources)).withPlayEpisode(isPlayable ? playEpisodeElement(download, pageType, resources) : null).withFollow(followElement(download, pageType, resources)).withCompleted(isPlayable ? completedElement(download, pageType) : null).withOnEpisodeOptionsShow(UiMetricMethods.onClicked(pageType, UiMetricAttributes$ActionType.SELECT_PODCAST_CONTEXT_MENU, UiMetricAttributes$EntityType.PODCAST_EPISODE, UiMetricAttributes$EntityIdType.PODCAST_EPISODE_ID, download.getId(), (Integer) null, UiMetricAttributes$ContentName.PODCAST_CONTEXT_MENU)).withOnEpisodeOptionsDismiss(uiMetricClickMethods(MetricsEntityIds.podcastEpisode(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), download.getId(), download.getPodcastEpisodeVariantId()), UiMetricAttributes$ActionType.CLOSE_PODCAST_OVERLAY_CONTEXT_MENU, pageType)).withCarPreset(podcastShowCarPresetElement(download.getPodcastId())).withReport(reportElement(download, pageType)).build();
    }

    public final EpisodeOptionsElement episodeOptionsElementWithoutHeader(final Save save, final UiMetricAttributes$PageType pageType, final Resources resources, final boolean isPlayable) {
        return EpisodeOptionsElement.builder().withSave(saveElement(save, pageType)).withShare(shareElement(save, pageType, resources)).withDownload(isPlayable ? downloadElement(save, pageType) : null).withGoToPodcast(goToPodcastElement(save, pageType, resources)).withPlayEpisode(isPlayable ? playEpisodeElement(save, pageType, resources) : null).withFollow(followElement(save, pageType, resources)).withCompleted(isPlayable ? completedElement(save, pageType) : null).withOnEpisodeOptionsShow(UiMetricMethods.onClicked(pageType, UiMetricAttributes$ActionType.SELECT_PODCAST_CONTEXT_MENU, UiMetricAttributes$EntityType.PODCAST_EPISODE, UiMetricAttributes$EntityIdType.PODCAST_EPISODE_ID, save.getId(), (Integer) null, UiMetricAttributes$ContentName.PODCAST_CONTEXT_MENU)).withOnEpisodeOptionsDismiss(uiMetricClickMethods(MetricsEntityIds.podcastEpisode(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), save.getId(), save.getPodcastEpisodeVariantId()), UiMetricAttributes$ActionType.CLOSE_PODCAST_OVERLAY_CONTEXT_MENU, pageType)).withCarPreset(podcastShowCarPresetElement(save.getPodcastId())).withReport(reportElement(save, pageType)).build();
    }

    public final EpisodeOptionsElement episodeOptionsElementWithHeader(final Save save, final UiMetricAttributes$PageType pageType, final Resources resources, final boolean isPlayable) {
        return EpisodeOptionsElement.builder().withImage(save.getImage()).withTitle(save.getTitle()).withSubTitle(save.getPodcastTitle()).withSave(saveElement(save, pageType)).withShare(shareElement(save, pageType, resources)).withDownload(isPlayable ? downloadElement(save, pageType) : null).withGoToPodcast(goToPodcastElement(save, pageType, resources)).withPlayEpisode(isPlayable ? playEpisodeElement(save, pageType, resources) : null).withFollow(followElement(save, pageType, resources)).withCompleted(isPlayable ? completedElement(save, pageType) : null).withOnEpisodeOptionsShow(UiMetricMethods.onClicked(pageType, UiMetricAttributes$ActionType.SELECT_PODCAST_CONTEXT_MENU, UiMetricAttributes$EntityType.PODCAST_EPISODE, UiMetricAttributes$EntityIdType.PODCAST_EPISODE_ID, save.getId(), (Integer) null, UiMetricAttributes$ContentName.PODCAST_CONTEXT_MENU)).withOnEpisodeOptionsDismiss(uiMetricClickMethods(MetricsEntityIds.podcastEpisode(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), save.getId(), save.getPodcastEpisodeVariantId()), UiMetricAttributes$ActionType.CLOSE_PODCAST_OVERLAY_CONTEXT_MENU, pageType)).withCarPreset(podcastShowCarPresetElement(save.getPodcastId())).withReport(reportElement(save, pageType)).build();
    }

    public EpisodeOptionsElement episodeOptionsElementsWithHeader(final String blockRef, final String episodeId, final String podcastId, final String metricsPodcastEpisodeEntityId, final String metricsPodcastShowEntityId, final String imageUrl, final String title, final String podcastTitle, final UiMetricAttributes$PageType pageType, final long durationMilliseconds, final String publishDate, final Resources resources) {
        return EpisodeOptionsElement.builder().withImage(imageUrl).withTitle(title).withSubTitle(podcastTitle).withSave(saveElement(episodeId, metricsPodcastEpisodeEntityId, title, imageUrl, podcastId, podcastTitle, durationMilliseconds, publishDate, pageType)).withShare(shareElement(episodeId, metricsPodcastEpisodeEntityId, title, imageUrl, podcastId, podcastTitle, pageType, resources)).withDownload(downloadElement(episodeId, metricsPodcastEpisodeEntityId, pageType)).withPlayEpisode(playEpisodeElement(episodeId, podcastId, metricsPodcastEpisodeEntityId, title, podcastTitle, pageType, resources)).withGoToPodcast(goToPodcastElement(podcastId, metricsPodcastShowEntityId, podcastTitle, pageType, resources)).withFollow(followElement(podcastId, metricsPodcastShowEntityId, podcastTitle, pageType, resources)).withCompleted(completedElement(episodeId, podcastId, metricsPodcastEpisodeEntityId, title, podcastTitle, imageUrl, publishDate, durationMilliseconds, pageType)).withOnEpisodeOptionsShow(UiMetricMethods.onClicked(blockRef, UiMetricAttributes$ActionType.SELECT_PODCAST_CONTEXT_MENU, pageType, (UiMetricAttributes$ContentName) null)).withOnEpisodeOptionsDismiss(uiMetricClickMethods(metricsPodcastShowEntityId, UiMetricAttributes$ActionType.CLOSE_PODCAST_OVERLAY_CONTEXT_MENU, pageType)).withCarPreset(podcastShowCarPresetElement(podcastId)).withReport(reportElement(episodeId, pageType)).build();
    }

    private CompletedElement completedElement(final String episodeId, final String podcastId, final String metricsEntityId, final String title, final String podcastTitle, final String image, final String publishDate, final long durationMilliseconds, final UiMetricAttributes$PageType pageType) {
        return CompletedElement.builder().withId(episodeId).withOnMarkPlayed(onCompletedMethods(episodeId, podcastId, metricsEntityId, title, podcastTitle, image, publishDate, durationMilliseconds, true, pageType)).withOnMarkUnplayed(onCompletedMethods(episodeId, podcastId, metricsEntityId, title, podcastTitle, image, publishDate, durationMilliseconds, false, pageType)).build();
    }

    private List<Method> onCompletedMethods(final String episodeId, final String podcastId, final String metricsEntityId, final String title, final String podcastTitle, final String image, final String publishDate, final long durationMilliseconds, final boolean isCompleted, final UiMetricAttributes$PageType pageType) {
        UiMetricAttributes$ActionType uiMetricAttributes$ActionType;
        CompletedWriteElement completedWriteElement = completedWriteElement(episodeId, podcastId, title, podcastTitle, image, publishDate, isCompleted);
        BookmarkWriteElement bookmarkWriteElement = bookmarkWriteElement(episodeId, podcastId, title, podcastTitle, image, publishDate, durationMilliseconds);
        ArrayList arrayList = new ArrayList();
        arrayList.add(completedWriteElement);
        arrayList.add(bookmarkWriteElement);
        WriteMethod writeMethodBuild = WriteMethod.builder().withQueue(Queues.appSync()).withElement(arrayList).build();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(writeMethodBuild);
        if (isCompleted) {
            uiMetricAttributes$ActionType = UiMetricAttributes$ActionType.MARK_PODCAST_EPISODE_PLAYED;
        } else {
            uiMetricAttributes$ActionType = UiMetricAttributes$ActionType.MARK_PODCAST_EPISODE_UNPLAYED;
        }
        arrayList2.addAll(uiMetricClickMethods(metricsEntityId, uiMetricAttributes$ActionType, pageType));
        arrayList2.addAll(DeeplinkMethods.syncEpisodeMetadata(episodeId));
        return arrayList2;
    }

    private BookmarkWriteElement bookmarkWriteElement(final String episodeId, final String podcastId, final String title, final String podcastTitle, final String image, final String publishDate, final long durationMilliseconds) {
        return BookmarkWriteElement.builder().withId(episodeId).withAuthors(Collections.EMPTY_LIST).withDescription("").withTitle(title).withImage(image).withPodcastImage("").withPodcastId(podcastId).withPodcastTitle(podcastTitle).withSeasonNumber(null).withPublishDate(publishDate).withTotalDurationMilliseconds(Long.valueOf(durationMilliseconds)).withProgressMilliSeconds(0L).build();
    }

    private CompletedWriteElement completedWriteElement(final String episodeId, final String podcastId, final String title, final String podcastTitle, final String image, final String publishDate, final boolean isCompleted) {
        return CompletedWriteElement.builder().withId(episodeId).withAuthors(Collections.EMPTY_LIST).withDescription("").withTitle(title).withImage(image).withPodcastImage("").withPodcastId(podcastId).withPodcastTitle(podcastTitle).withCompleted(isCompleted).withSeasonNumber(null).withPublishDate(publishDate).build();
    }

    private FollowElement followElement(final String podcastId, final String metricsPodcastShowEntityId, final String podcastTitle, final UiMetricAttributes$PageType pageType, final Resources resources) {
        HashMap map = new HashMap();
        String string = resources.getString(R$string.podcast_follow_label);
        map.put("FOLLOW", resources.getString(R$string.podcast_unFollow_label));
        map.put("UNFOLLOW", string);
        List<Method> listUpdateFollowMethods = updateFollowMethods(podcastId, podcastTitle, true);
        List<Method> listUpdateFollowMethods2 = updateFollowMethods(podcastId, podcastTitle, false);
        listUpdateFollowMethods.addAll(uiMetricClickMethods(metricsPodcastShowEntityId, UiMetricAttributes$ActionType.FOLLOW_PODCAST_SHOW, pageType));
        listUpdateFollowMethods.addAll(DeeplinkMethods.syncPodcastMetadata(podcastId));
        listUpdateFollowMethods2.addAll(uiMetricClickMethods(metricsPodcastShowEntityId, UiMetricAttributes$ActionType.UNFOLLOW_PODCAST_SHOW, pageType));
        listUpdateFollowMethods2.addAll(DeeplinkMethods.syncPodcastMetadata(podcastId));
        return FollowElement.builder().withId(podcastId).withDescriptions(map).withOnFollow(listUpdateFollowMethods).withOnUnFollow(listUpdateFollowMethods2).build();
    }

    private static final List<Method> updateFollowMethods(final String podcastId, final String podcasTitle, final boolean isGoingToFollow) {
        WriteMethod writeMethodBuild = WriteMethod.builder().withElement(Collections.singletonList(FollowWriteElement.builder().withId(podcastId).withTitle(podcasTitle).withPublisher("").withImage("").withDescription("").withFollowed(isGoingToFollow).build())).withCondition(null).withForced(Boolean.FALSE).withQueue(Queues.followSync()).build();
        ArrayList arrayList = new ArrayList();
        arrayList.add(writeMethodBuild);
        return arrayList;
    }

    private PlayEpisodeElement playEpisodeElement(final String episodeId, final String podcastId, final String metricsPodcastEpisodeEntityId, final String title, final String podcastTitle, final UiMetricAttributes$PageType pageType, final Resources resources) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(DeeplinkMethods.episodePlay(episodeId, podcastId, title, podcastTitle, resources));
        arrayList.addAll(uiMetricClickMethods(metricsPodcastEpisodeEntityId, UiMetricAttributes$ActionType.PLAY_PODCAST_EPISODE, pageType));
        return PlayEpisodeElement.builder().withOnItemSelected(arrayList).build();
    }

    private GoToPodcastElement goToPodcastElement(final String podcastId, final String metricsPodcastShowEntityId, final String podcastTitle, final UiMetricAttributes$PageType pageType, final Resources resources) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(DeeplinkMethods.podcast(podcastId, podcastTitle, resources));
        arrayList.addAll(uiMetricClickMethods(metricsPodcastShowEntityId, UiMetricAttributes$ActionType.GO_PODCAST_SHOW, pageType));
        return GoToPodcastElement.builder().withOnItemSelected(arrayList).build();
    }

    private DownloadElement downloadElement(final String episodeId, final String metricsPodcastEpisodeEntityId, final UiMetricAttributes$PageType pageType) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(DeeplinkMethods.download(episodeId));
        arrayList.addAll(uiMetricClickMethods(metricsPodcastEpisodeEntityId, UiMetricAttributes$ActionType.DOWNLOAD_PODCAST_EPISODE, pageType));
        return DownloadElement.builder().withId(episodeId).withOnDownload(arrayList).build();
    }

    private ShareElement shareElement(final String episodeId, final String metricsPodcastEpisodeEntityId, final String title, final String image, final String podcastId, final String podcastTitle, final UiMetricAttributes$PageType pageType, final Resources resources) {
        String strEpisodeForShare = Deeplinks.episodeForShare(podcastId, episodeId, podcastTitle, title);
        String value = EntityTypes.PODCAST_EPISODE.getValue();
        String string = resources.getString(R$string.podcast_share_episode_label);
        String string2 = resources.getString(R$string.podcast_share_episode_prompt);
        if (!StringUtils.isBlank(metricsPodcastEpisodeEntityId)) {
            episodeId = metricsPodcastEpisodeEntityId;
        }
        return ShareElement.builder().withId(episodeId).withTitle(title).withSubtitle(podcastTitle).withImageUrl(image).withDeeplinkUrl(strEpisodeForShare).withLabel(string).withPrompt(string2).withMetricsEntityType(value).withOnShare(uiMetricClickMethods(metricsPodcastEpisodeEntityId, UiMetricAttributes$ActionType.SHARE_PODCAST_EPISODE, pageType)).build();
    }

    private SaveElement saveElement(final String episodeId, final String metricsPodcastEpisodeEntityId, final String title, final String image, final String podcastId, final String podcastTitle, final long durationMilliseconds, final String publishDate, final UiMetricAttributes$PageType pageType) {
        return SaveElement.builder().withId(episodeId).withOnSave(onSaveMethods(episodeId, metricsPodcastEpisodeEntityId, title, image, podcastId, podcastTitle, durationMilliseconds, publishDate, pageType)).withOnUnsave(onUnSaveMethods(episodeId, metricsPodcastEpisodeEntityId, title, image, podcastId, podcastTitle, durationMilliseconds, publishDate, pageType)).build();
    }

    private List<Method> onSaveMethods(final String episodeId, final String metricsPodcastEpisodeEntityId, final String title, final String image, final String podcastId, final String podcastTitle, final long durationMilliseconds, final String publishDate, final UiMetricAttributes$PageType pageType) {
        WriteMethod writeMethodBuild = WriteMethod.builder().withQueue(Queues.appSync()).withElement(Collections.singletonList(saveWriteElement(episodeId, title, image, podcastId, podcastTitle, durationMilliseconds, publishDate, true))).build();
        ArrayList arrayList = new ArrayList();
        arrayList.add(writeMethodBuild);
        arrayList.addAll(uiMetricClickMethods(metricsPodcastEpisodeEntityId, UiMetricAttributes$ActionType.SAVE_PODCAST_EPISODE, pageType));
        arrayList.addAll(DeeplinkMethods.syncEpisodeMetadata(episodeId));
        return arrayList;
    }

    private List<Method> onUnSaveMethods(final String episodeId, final String metricsPodcastEpisodeEntityId, final String title, final String image, final String podcastId, final String podcastTitle, final long durationMilliseconds, final String publishDate, final UiMetricAttributes$PageType pageType) {
        WriteMethod writeMethodBuild = WriteMethod.builder().withQueue(Queues.appSync()).withElement(Collections.singletonList(saveWriteElement(episodeId, title, image, podcastId, podcastTitle, durationMilliseconds, publishDate, false))).build();
        ArrayList arrayList = new ArrayList();
        arrayList.add(writeMethodBuild);
        arrayList.addAll(uiMetricClickMethods(metricsPodcastEpisodeEntityId, UiMetricAttributes$ActionType.UNSAVE_PODCAST_EPISODE, pageType));
        arrayList.addAll(DeeplinkMethods.syncEpisodeMetadata(episodeId));
        return arrayList;
    }

    private SaveWriteElement saveWriteElement(final String episodeId, final String title, final String image, final String podcastId, final String podcastTitle, final long durationMilliseconds, final String publishDate, final boolean isSaved) {
        return SaveWriteElement.builder().withId(episodeId).withTitle(title).withAuthors(Collections.EMPTY_LIST).withDescription("").withImage(image).withPodcastId(podcastId).withPodcastTitle(podcastTitle).withTotalDurationMilliseconds(Long.valueOf(durationMilliseconds)).withPublishDate(publishDate).withSaved(isSaved).build();
    }

    public final EpisodeOptionsElement episodeOptionsElementWithoutHeader(final JumpBackInCacheItemElement jumpBackIn, final UiMetricAttributes$PageType pageType, final Resources resources, final boolean isPlayable) {
        return EpisodeOptionsElement.builder().withSave(saveElement(jumpBackIn, pageType)).withShare(shareElement(jumpBackIn, pageType, resources)).withDownload(isPlayable ? downloadElement(jumpBackIn, pageType) : null).withPlayEpisode(isPlayable ? playEpisodeElement(jumpBackIn, pageType, resources) : null).withGoToPodcast(goToPodcastElement(jumpBackIn, pageType, resources)).withFollow(followElement(jumpBackIn, pageType, resources)).withCompleted(isPlayable ? completedElement(jumpBackIn, pageType) : null).withOnEpisodeOptionsShow(UiMetricMethods.onClicked(pageType, UiMetricAttributes$ActionType.SELECT_PODCAST_CONTEXT_MENU, UiMetricAttributes$EntityType.PODCAST_EPISODE, UiMetricAttributes$EntityIdType.PODCAST_EPISODE_ID, jumpBackIn.getEpisodeId(), (Integer) null, UiMetricAttributes$ContentName.PODCAST_CONTEXT_MENU)).withOnEpisodeOptionsDismiss(uiMetricClickMethods(MetricsEntityIds.podcastEpisode(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), jumpBackIn.getEpisodeId(), jumpBackIn.getPodcastEpisodeVariantId()), UiMetricAttributes$ActionType.CLOSE_PODCAST_OVERLAY_CONTEXT_MENU, pageType)).withCarPreset(podcastShowCarPresetElement(jumpBackIn.getPodcastId())).withReport(reportElement(jumpBackIn, pageType)).build();
    }

    public final EpisodeOptionsElement episodeOptionsElementWithHeader(final JumpBackInCacheItemElement jumpBackIn, final UiMetricAttributes$PageType pageType, final Resources resources, final boolean isPlayable) {
        return EpisodeOptionsElement.builder().withImage(jumpBackIn.getImage()).withTitle(jumpBackIn.getTitle()).withSubTitle(jumpBackIn.getPodcastTitle()).withSave(saveElement(jumpBackIn, pageType)).withShare(shareElement(jumpBackIn, pageType, resources)).withDownload(isPlayable ? downloadElement(jumpBackIn, pageType) : null).withPlayEpisode(isPlayable ? playEpisodeElement(jumpBackIn, pageType, resources) : null).withGoToPodcast(goToPodcastElement(jumpBackIn, pageType, resources)).withFollow(followElement(jumpBackIn, pageType, resources)).withCompleted(isPlayable ? completedElement(jumpBackIn, pageType) : null).withOnEpisodeOptionsShow(UiMetricMethods.onClicked(pageType, UiMetricAttributes$ActionType.SELECT_PODCAST_CONTEXT_MENU, UiMetricAttributes$EntityType.PODCAST_EPISODE, UiMetricAttributes$EntityIdType.PODCAST_EPISODE_ID, jumpBackIn.getEpisodeId(), (Integer) null, UiMetricAttributes$ContentName.PODCAST_CONTEXT_MENU)).withOnEpisodeOptionsDismiss(uiMetricClickMethods(MetricsEntityIds.podcastEpisode(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), jumpBackIn.getEpisodeId(), jumpBackIn.getPodcastEpisodeVariantId()), UiMetricAttributes$ActionType.CLOSE_PODCAST_OVERLAY_CONTEXT_MENU, pageType)).withCarPreset(podcastShowCarPresetElement(jumpBackIn.getPodcastId())).withReport(reportElement(jumpBackIn, pageType)).build();
    }

    public DownloadElement downloadElement(final Download download, final UiMetricAttributes$PageType pageType) {
        ArrayList arrayList = new ArrayList();
        String strPodcastEpisode = MetricsEntityIds.podcastEpisode(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), download.getId(), download.getPodcastEpisodeVariantId());
        arrayList.addAll(DeeplinkMethods.download(download.getId()));
        arrayList.addAll(uiMetricClickMethods(strPodcastEpisode, UiMetricAttributes$ActionType.DOWNLOAD_PODCAST_EPISODE, pageType));
        return DownloadElement.builder().withId(download.getId()).withOnDownload(arrayList).build();
    }

    public ShareElement shareElement(final Download download, final UiMetricAttributes$PageType pageType, final Resources resources) {
        String strPodcastEpisode = MetricsEntityIds.podcastEpisode(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), download.getId(), download.getPodcastEpisodeVariantId());
        String str = String.format(resources.getString(R$string.podcast_share_episode_description), download.getTitle(), download.getPodcastTitle());
        String string = resources.getString(R$string.podcast_share_episode_label);
        return ShareElement.builder().withId(strPodcastEpisode).withTitle(download.getTitle()).withSubtitle(download.getPodcastTitle()).withImageUrl(download.getImage()).withDescription(str).withDeeplinkUrl(Deeplinks.episodeForShare(download.getPodcastId(), download.getId(), download.getPodcastTitle(), download.getTitle())).withLabel(string).withPrompt(resources.getString(R$string.podcast_share_episode_prompt)).withMetricsEntityType(EntityTypes.PODCAST_EPISODE.getValue()).withOnShare(uiMetricClickMethods(strPodcastEpisode, UiMetricAttributes$ActionType.SHARE_PODCAST_EPISODE, pageType)).build();
    }

    public ShareElement shareElement(final JumpBackInCacheItemElement jumpBackIn, final UiMetricAttributes$PageType pageType, final Resources resources) {
        String strPodcastEpisode = MetricsEntityIds.podcastEpisode(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), jumpBackIn.getEpisodeId(), jumpBackIn.getPodcastEpisodeVariantId());
        String str = String.format(resources.getString(R$string.podcast_share_episode_description), jumpBackIn.getTitle(), jumpBackIn.getPodcastTitle());
        String string = resources.getString(R$string.podcast_share_episode_label);
        return ShareElement.builder().withId(strPodcastEpisode).withTitle(jumpBackIn.getTitle()).withSubtitle(jumpBackIn.getPodcastTitle()).withImageUrl(jumpBackIn.getImage()).withDescription(str).withDeeplinkUrl(Deeplinks.episodeForShare(jumpBackIn.getPodcastId(), jumpBackIn.getEpisodeId(), jumpBackIn.getPodcastTitle(), jumpBackIn.getTitle())).withLabel(string).withPrompt(resources.getString(R$string.podcast_share_episode_prompt)).withMetricsEntityType(EntityTypes.PODCAST_EPISODE.getValue()).withOnShare(uiMetricClickMethods(strPodcastEpisode, UiMetricAttributes$ActionType.SHARE_PODCAST_EPISODE, pageType)).build();
    }

    public DownloadElement downloadElement(final JumpBackInCacheItemElement jumpBackIn, final UiMetricAttributes$PageType pageType) {
        String strPodcastEpisode = MetricsEntityIds.podcastEpisode(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), jumpBackIn.getEpisodeId(), jumpBackIn.getPodcastEpisodeVariantId());
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(DeeplinkMethods.download(jumpBackIn.getEpisodeId()));
        arrayList.addAll(uiMetricClickMethods(strPodcastEpisode, UiMetricAttributes$ActionType.DOWNLOAD_PODCAST_EPISODE, pageType));
        return DownloadElement.builder().withId(jumpBackIn.getEpisodeId()).withOnDownload(arrayList).build();
    }

    public CompletedElement completedElement(final JumpBackInCacheItemElement jumpBackIn, final UiMetricAttributes$PageType pageType) {
        return CompletedElement.builder().withId(jumpBackIn.getEpisodeId()).withOnMarkPlayed(onCompletedMethods(jumpBackIn, true, pageType)).withOnMarkUnplayed(onCompletedMethods(jumpBackIn, false, pageType)).build();
    }

    public GoToPodcastElement goToPodcastElementForNPS(final String metricsPodcastShowEntityId, final UiMetricAttributes$PageType pageType) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(NavigateToPodcastMethod.builder().withQueue(Queues.playback()).build());
        arrayList.addAll(uiMetricClickMethods(metricsPodcastShowEntityId, UiMetricAttributes$ActionType.GO_PODCAST_SHOW, pageType));
        return GoToPodcastElement.builder().withOnItemSelected(arrayList).build();
    }

    public GoToPodcastElement goToPodcastElement(final Download download, final UiMetricAttributes$PageType pageType, final Resources resources) {
        String strPodcastShow = MetricsEntityIds.podcastShow(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), download.getPodcastId(), download.getPodcastShowVariantId());
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(DeeplinkMethods.podcast(download, resources));
        arrayList.addAll(uiMetricClickMethods(strPodcastShow, UiMetricAttributes$ActionType.GO_PODCAST_SHOW, pageType));
        return GoToPodcastElement.builder().withOnItemSelected(arrayList).build();
    }

    public PlayEpisodeElement playEpisodeElement(final Download download, final UiMetricAttributes$PageType pageType, final Resources resources) {
        String strPodcastEpisode = MetricsEntityIds.podcastEpisode(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), download.getId(), download.getPodcastEpisodeVariantId());
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(PlayDownloadedEpisodesMethods.playEpisode(download, resources));
        arrayList.addAll(uiMetricClickMethods(strPodcastEpisode, UiMetricAttributes$ActionType.PLAY_PODCAST_EPISODE, pageType));
        return PlayEpisodeElement.builder().withOnItemSelected(arrayList).build();
    }

    public SaveElement saveElement(final Download download, final UiMetricAttributes$PageType pageType) {
        return SaveElement.builder().withId(download.getId()).withOnSave(onSaveMethods(download, pageType)).withOnUnsave(onUnSaveMethods(download, pageType)).build();
    }

    public FollowElement followElement(final Download download, final UiMetricAttributes$PageType pageType, final Resources resources) {
        HashMap map = new HashMap();
        String string = resources.getString(R$string.podcast_follow_label);
        map.put("FOLLOW", resources.getString(R$string.podcast_unFollow_label));
        map.put("UNFOLLOW", string);
        List<Method> listUpdateFollowMethods = updateFollowMethods(download, true);
        List<Method> listUpdateFollowMethods2 = updateFollowMethods(download, false);
        String strPodcastShow = MetricsEntityIds.podcastShow(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), download.getPodcastId(), download.getPodcastShowVariantId());
        listUpdateFollowMethods.addAll(uiMetricClickMethods(strPodcastShow, UiMetricAttributes$ActionType.FOLLOW_PODCAST_SHOW, pageType));
        listUpdateFollowMethods2.addAll(uiMetricClickMethods(strPodcastShow, UiMetricAttributes$ActionType.UNFOLLOW_PODCAST_SHOW, pageType));
        return FollowElement.builder().withId(download.getPodcastId()).withDescriptions(map).withOnFollow(listUpdateFollowMethods).withOnUnFollow(listUpdateFollowMethods2).build();
    }

    public FollowElement followElement(final JumpBackInCacheItemElement jumpBackIn, final UiMetricAttributes$PageType pageType, final Resources resources) {
        HashMap map = new HashMap();
        String string = resources.getString(R$string.podcast_follow_label);
        map.put("FOLLOW", resources.getString(R$string.podcast_unFollow_label));
        map.put("UNFOLLOW", string);
        List<Method> listUpdateFollowMethods = updateFollowMethods(jumpBackIn, true);
        List<Method> listUpdateFollowMethods2 = updateFollowMethods(jumpBackIn, false);
        String strPodcastShow = MetricsEntityIds.podcastShow(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), jumpBackIn.getPodcastId(), jumpBackIn.getPodcastShowVariantId());
        listUpdateFollowMethods.addAll(uiMetricClickMethods(strPodcastShow, UiMetricAttributes$ActionType.FOLLOW_PODCAST_SHOW, pageType));
        listUpdateFollowMethods2.addAll(uiMetricClickMethods(strPodcastShow, UiMetricAttributes$ActionType.UNFOLLOW_PODCAST_SHOW, pageType));
        return FollowElement.builder().withId(jumpBackIn.getPodcastId()).withDescriptions(map).withOnFollow(listUpdateFollowMethods).withOnUnFollow(listUpdateFollowMethods2).build();
    }

    private List<Method> onSaveMethods(final Download download, final UiMetricAttributes$PageType pageType) {
        ArrayList arrayList = new ArrayList();
        WriteMethod writeMethodBuild = WriteMethod.builder().withQueue(Queues.appSync()).withElement(Collections.singletonList(saveWriteElement(download, true))).build();
        String strPodcastEpisode = MetricsEntityIds.podcastEpisode(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), download.getId(), download.getPodcastEpisodeVariantId());
        arrayList.add(writeMethodBuild);
        arrayList.addAll(uiMetricClickMethods(strPodcastEpisode, UiMetricAttributes$ActionType.SAVE_PODCAST_EPISODE, pageType));
        return arrayList;
    }

    private List<Method> onUnSaveMethods(final Download download, final UiMetricAttributes$PageType pageType) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(WriteMethod.builder().withQueue(Queues.appSync()).withElement(Collections.singletonList(saveWriteElement(download, false))).build());
        arrayList.addAll(uiMetricClickMethods(MetricsEntityIds.podcastEpisode(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), download.getId(), download.getPodcastEpisodeVariantId()), UiMetricAttributes$ActionType.UNSAVE_PODCAST_EPISODE, pageType));
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
        return SaveWriteElement.builder().withId(download.getId()).withAuthors(arrayList).withDescription(download.getDescription()).withTitle(download.getTitle()).withImage(download.getImage()).withPodcastImage(download.getPodcastImage()).withPodcastId(download.getPodcastId()).withPodcastTitle(download.getPodcastTitle()).withSaved(isSaved).withTotalDurationMilliseconds(Long.valueOf(download.getDurationMilliseconds())).withSeasonNumber(StringUtils.isNumeric(seasonNumber) ? Integer.valueOf(Integer.parseInt(seasonNumber)) : null).withPublishDate(download.getPublishDate()).withPlaybackMode(PlaybackModes.mode(download.getPlaybackMode())).withAvailableUpsells(Upsells.types(download.getAvailableUpsells())).withContentTraits(ContentTraits.traits(download.getContentTraits())).withAvailabilityDate(download.getAvailabilityDate()).withPodcastEpisodeVariantId(download.getPodcastEpisodeVariantId()).withPodcastShowVariantId(download.getPodcastShowVariantId()).build();
    }

    private static final List<Method> updateFollowMethods(final Download download, final boolean isGoingToFollow) {
        WriteMethod writeMethodBuild = WriteMethod.builder().withElement(Collections.singletonList(FollowWriteElement.builder().withId(download.getPodcastId()).withTitle(download.getPodcastTitle()).withPublisher("").withImage(download.getPodcastImage()).withDescription("").withFollowed(isGoingToFollow).withPodcastShowVariantId(download.getPodcastShowVariantId()).build())).withCondition(null).withForced(Boolean.FALSE).withQueue(Queues.followSync()).build();
        ArrayList arrayList = new ArrayList();
        arrayList.add(writeMethodBuild);
        return arrayList;
    }

    public DownloadElement downloadElement(final Save save, final UiMetricAttributes$PageType pageType) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(DeeplinkMethods.download(save.getId()));
        arrayList.addAll(uiMetricClickMethods(MetricsEntityIds.podcastEpisode(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), save.getId(), save.getPodcastEpisodeVariantId()), UiMetricAttributes$ActionType.DOWNLOAD_PODCAST_EPISODE, pageType));
        return DownloadElement.builder().withId(save.getId()).withOnDownload(arrayList).build();
    }

    public ShareElement shareElement(final Save save, final UiMetricAttributes$PageType pageType, final Resources resources) {
        String str = String.format(resources.getString(R$string.podcast_share_episode_description), save.getTitle(), save.getPodcastTitle());
        String string = resources.getString(R$string.podcast_share_episode_label);
        String string2 = resources.getString(R$string.podcast_share_episode_prompt);
        String strEpisodeForShare = Deeplinks.episodeForShare(save.getPodcastId(), save.getId(), save.getPodcastTitle(), save.getTitle());
        String value = EntityTypes.PODCAST_EPISODE.getValue();
        String strPodcastEpisode = MetricsEntityIds.podcastEpisode(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), save.getId(), save.getPodcastEpisodeVariantId());
        return ShareElement.builder().withId(strPodcastEpisode).withTitle(save.getTitle()).withSubtitle(save.getPodcastTitle()).withImageUrl(save.getImage()).withDescription(str).withDeeplinkUrl(strEpisodeForShare).withLabel(string).withPrompt(string2).withMetricsEntityType(value).withOnShare(uiMetricClickMethods(strPodcastEpisode, UiMetricAttributes$ActionType.SHARE_PODCAST_EPISODE, pageType)).build();
    }

    public GoToPodcastElement goToPodcastElement(final Save save, final UiMetricAttributes$PageType pageType, final Resources resources) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(DeeplinkMethods.podcast(save, resources));
        arrayList.addAll(uiMetricClickMethods(MetricsEntityIds.podcastShow(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), save.getPodcastId(), save.getPodcastShowVariantId()), UiMetricAttributes$ActionType.GO_PODCAST_SHOW, pageType));
        return GoToPodcastElement.builder().withOnItemSelected(arrayList).build();
    }

    public GoToPodcastElement goToPodcastElement(final JumpBackInCacheItemElement jumpBackIn, final UiMetricAttributes$PageType pageType, final Resources resources) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(DeeplinkMethods.podcast(jumpBackIn, resources));
        arrayList.addAll(uiMetricClickMethods(MetricsEntityIds.podcastShow(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), jumpBackIn.getPodcastId(), jumpBackIn.getPodcastShowVariantId()), UiMetricAttributes$ActionType.GO_PODCAST_SHOW, pageType));
        return GoToPodcastElement.builder().withOnItemSelected(arrayList).build();
    }

    public PlayEpisodeElement playEpisodeElement(final Save save, final UiMetricAttributes$PageType pageType, final Resources resources) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(DeeplinkMethods.savedEpisodePlay(save.getId(), resources));
        arrayList.addAll(uiMetricClickMethods(MetricsEntityIds.podcastEpisode(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), save.getId(), save.getPodcastEpisodeVariantId()), UiMetricAttributes$ActionType.PLAY_PODCAST_EPISODE, pageType));
        return PlayEpisodeElement.builder().withOnItemSelected(arrayList).build();
    }

    public SaveElement saveElement(final Save save, final UiMetricAttributes$PageType pageType) {
        return SaveElement.builder().withId(save.getId()).withOnSave(onSaveMethods(save, pageType)).withOnUnsave(onUnSaveMethods(save, pageType)).build();
    }

    public FollowElement followElement(final Save save, final UiMetricAttributes$PageType pageType, final Resources resources) {
        HashMap map = new HashMap();
        String string = resources.getString(R$string.podcast_follow_label);
        map.put("FOLLOW", resources.getString(R$string.podcast_unFollow_label));
        map.put("UNFOLLOW", string);
        List<Method> listUpdateFollowMethods = updateFollowMethods(save, true);
        List<Method> listUpdateFollowMethods2 = updateFollowMethods(save, false);
        String strPodcastShow = MetricsEntityIds.podcastShow(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), save.getPodcastId(), save.getPodcastShowVariantId());
        listUpdateFollowMethods.addAll(uiMetricClickMethods(strPodcastShow, UiMetricAttributes$ActionType.FOLLOW_PODCAST_SHOW, pageType));
        listUpdateFollowMethods2.addAll(uiMetricClickMethods(strPodcastShow, UiMetricAttributes$ActionType.UNFOLLOW_PODCAST_SHOW, pageType));
        return FollowElement.builder().withId(save.getPodcastId()).withDescriptions(map).withOnFollow(listUpdateFollowMethods).withOnUnFollow(listUpdateFollowMethods2).build();
    }

    public CompletedElement completedElement(final Save save, final UiMetricAttributes$PageType pageType) {
        return CompletedElement.builder().withId(save.getId()).withOnMarkPlayed(onCompletedMethods(save, true, pageType)).withOnMarkUnplayed(onCompletedMethods(save, false, pageType)).build();
    }

    public CompletedElement completedElement(final Download download, final UiMetricAttributes$PageType pageType) {
        return CompletedElement.builder().withId(download.getId()).withOnMarkPlayed(onCompletedMethods(download, true, pageType)).withOnMarkUnplayed(onCompletedMethods(download, false, pageType)).build();
    }

    private List<Method> onCompletedMethods(final JumpBackInCacheItemElement jumpBackIn, final boolean isCompleted, final UiMetricAttributes$PageType pageType) {
        UiMetricAttributes$ActionType uiMetricAttributes$ActionType;
        CompletedWriteElement completedWriteElement = completedWriteElement(jumpBackIn, isCompleted);
        BookmarkWriteElement bookmarkWriteElement = bookmarkWriteElement(jumpBackIn);
        ArrayList arrayList = new ArrayList();
        arrayList.add(completedWriteElement);
        arrayList.add(bookmarkWriteElement);
        WriteMethod writeMethodBuild = WriteMethod.builder().withQueue(Queues.appSync()).withElement(arrayList).build();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(writeMethodBuild);
        String strPodcastEpisode = MetricsEntityIds.podcastEpisode(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), jumpBackIn.getEpisodeId(), jumpBackIn.getPodcastEpisodeVariantId());
        if (isCompleted) {
            uiMetricAttributes$ActionType = UiMetricAttributes$ActionType.MARK_PODCAST_EPISODE_PLAYED;
        } else {
            uiMetricAttributes$ActionType = UiMetricAttributes$ActionType.MARK_PODCAST_EPISODE_UNPLAYED;
        }
        arrayList2.addAll(uiMetricClickMethods(strPodcastEpisode, uiMetricAttributes$ActionType, pageType));
        return arrayList2;
    }

    private List<Method> onCompletedMethods(final Save save, final boolean isCompleted, final UiMetricAttributes$PageType pageType) {
        UiMetricAttributes$ActionType uiMetricAttributes$ActionType;
        CompletedWriteElement completedWriteElement = completedWriteElement(save, isCompleted);
        BookmarkWriteElement bookmarkWriteElement = bookmarkWriteElement(save);
        ArrayList arrayList = new ArrayList();
        arrayList.add(completedWriteElement);
        arrayList.add(bookmarkWriteElement);
        WriteMethod writeMethodBuild = WriteMethod.builder().withQueue(Queues.appSync()).withElement(arrayList).build();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(writeMethodBuild);
        String strPodcastEpisode = MetricsEntityIds.podcastEpisode(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), save.getId(), save.getPodcastEpisodeVariantId());
        if (isCompleted) {
            uiMetricAttributes$ActionType = UiMetricAttributes$ActionType.MARK_PODCAST_EPISODE_PLAYED;
        } else {
            uiMetricAttributes$ActionType = UiMetricAttributes$ActionType.MARK_PODCAST_EPISODE_UNPLAYED;
        }
        arrayList2.addAll(uiMetricClickMethods(strPodcastEpisode, uiMetricAttributes$ActionType, pageType));
        return arrayList2;
    }

    private List<Method> onCompletedMethods(final Download download, final boolean isCompleted, final UiMetricAttributes$PageType pageType) {
        UiMetricAttributes$ActionType uiMetricAttributes$ActionType;
        CompletedWriteElement completedWriteElement = completedWriteElement(download, isCompleted);
        BookmarkWriteElement bookmarkWriteElement = bookmarkWriteElement(download);
        ArrayList arrayList = new ArrayList();
        arrayList.add(completedWriteElement);
        arrayList.add(bookmarkWriteElement);
        WriteMethod writeMethodBuild = WriteMethod.builder().withQueue(Queues.appSync()).withElement(arrayList).build();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(writeMethodBuild);
        String strPodcastEpisode = MetricsEntityIds.podcastEpisode(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), download.getId(), download.getPodcastEpisodeVariantId());
        if (isCompleted) {
            uiMetricAttributes$ActionType = UiMetricAttributes$ActionType.MARK_PODCAST_EPISODE_PLAYED;
        } else {
            uiMetricAttributes$ActionType = UiMetricAttributes$ActionType.MARK_PODCAST_EPISODE_UNPLAYED;
        }
        arrayList2.addAll(uiMetricClickMethods(strPodcastEpisode, uiMetricAttributes$ActionType, pageType));
        return arrayList2;
    }

    private List<Method> onSaveMethods(final Save save, final UiMetricAttributes$PageType pageType) {
        WriteMethod writeMethodBuild = WriteMethod.builder().withQueue(Queues.appSync()).withElement(Collections.singletonList(saveWriteElement(save, true))).build();
        ArrayList arrayList = new ArrayList();
        arrayList.add(writeMethodBuild);
        arrayList.addAll(uiMetricClickMethods(MetricsEntityIds.podcastEpisode(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), save.getId(), save.getPodcastEpisodeVariantId()), UiMetricAttributes$ActionType.SAVE_PODCAST_EPISODE, pageType));
        return arrayList;
    }

    private List<Method> onUnSaveMethods(final Save save, final UiMetricAttributes$PageType pageType) {
        WriteMethod writeMethodBuild = WriteMethod.builder().withQueue(Queues.appSync()).withElement(Collections.singletonList(saveWriteElement(save, false))).build();
        ArrayList arrayList = new ArrayList();
        arrayList.add(writeMethodBuild);
        arrayList.addAll(uiMetricClickMethods(MetricsEntityIds.podcastEpisode(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), save.getId(), save.getPodcastEpisodeVariantId()), UiMetricAttributes$ActionType.UNSAVE_PODCAST_EPISODE, pageType));
        return arrayList;
    }

    private SaveWriteElement saveWriteElement(final Save save, final boolean isSaved) {
        ArrayList arrayList = new ArrayList();
        try {
            arrayList.addAll(Strings.convertStringToList(save.getAuthors()));
        } catch (IOException e) {
            logger.error("Error converting authors string to list", (Throwable) e);
        }
        String seasonNumber = save.getSeasonNumber();
        return SaveWriteElement.builder().withId(save.getId()).withAuthors(arrayList).withDescription(save.getDescription()).withTitle(save.getTitle()).withImage(save.getImage()).withPodcastImage(save.getPodcastImage()).withPodcastId(save.getPodcastId()).withPodcastTitle(save.getPodcastTitle()).withSaved(isSaved).withTotalDurationMilliseconds(Long.valueOf(save.getTotalDurationMilliseconds())).withSeasonNumber(StringUtils.isNumeric(seasonNumber) ? Integer.valueOf(Integer.parseInt(seasonNumber)) : null).withPublishDate(save.getPublishDate()).withPlaybackMode(PlaybackModes.mode(save.getPlaybackMode())).withAvailableUpsells(Upsells.types(save.getAvailableUpsells())).withContentTraits(ContentTraits.traits(save.getContentTraits())).withAvailabilityDate(save.getAvailabilityDate()).withPodcastEpisodeVariantId(save.getPodcastEpisodeVariantId()).withPodcastShowVariantId(save.getPodcastShowVariantId()).build();
    }

    private CompletedWriteElement completedWriteElement(final JumpBackInCacheItemElement jumpBackIn, final boolean isCompleted) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(jumpBackIn.getAuthors());
        return CompletedWriteElement.builder().withId(jumpBackIn.getEpisodeId()).withAuthors(arrayList).withDescription(jumpBackIn.getDescription()).withTitle(jumpBackIn.getTitle()).withImage(jumpBackIn.getImage()).withPodcastImage(jumpBackIn.getPodcastImage()).withPodcastId(jumpBackIn.getPodcastId()).withPodcastTitle(jumpBackIn.getPodcastTitle()).withCompleted(isCompleted).withSeasonNumber(jumpBackIn.getSeasonNumber()).withPublishDate(jumpBackIn.getPublishDate()).build();
    }

    private CompletedWriteElement completedWriteElement(final Save save, final boolean isCompleted) {
        ArrayList arrayList = new ArrayList();
        try {
            arrayList.addAll(Strings.convertStringToList(save.getAuthors()));
        } catch (IOException e) {
            logger.error("Error converting authors string to list", (Throwable) e);
        }
        String seasonNumber = save.getSeasonNumber();
        return CompletedWriteElement.builder().withId(save.getId()).withAuthors(arrayList).withDescription(save.getDescription()).withTitle(save.getTitle()).withImage(save.getImage()).withPodcastImage(save.getPodcastImage()).withPodcastId(save.getPodcastId()).withPodcastTitle(save.getPodcastTitle()).withCompleted(isCompleted).withSeasonNumber(StringUtils.isNumeric(seasonNumber) ? Integer.valueOf(Integer.parseInt(seasonNumber)) : null).withPublishDate(save.getPublishDate()).build();
    }

    private CompletedWriteElement completedWriteElement(final Download download, final boolean isCompleted) {
        ArrayList arrayList = new ArrayList();
        try {
            arrayList.addAll(Strings.convertStringToList(download.getAuthors()));
        } catch (IOException e) {
            logger.error("Error converting authors string to list", (Throwable) e);
        }
        String seasonNumber = download.getSeasonNumber();
        return CompletedWriteElement.builder().withId(download.getId()).withAuthors(arrayList).withDescription(download.getDescription()).withTitle(download.getTitle()).withImage(download.getImage()).withPodcastImage(download.getPodcastImage()).withPodcastId(download.getPodcastId()).withPodcastTitle(download.getPodcastTitle()).withCompleted(isCompleted).withSeasonNumber(StringUtils.isNumeric(seasonNumber) ? Integer.valueOf(Integer.parseInt(seasonNumber)) : null).withPublishDate(download.getPublishDate()).build();
    }

    private BookmarkWriteElement bookmarkWriteElement(final Download download) {
        ArrayList arrayList = new ArrayList();
        try {
            arrayList.addAll(Strings.convertStringToList(download.getAuthors()));
        } catch (IOException e) {
            logger.error("Error converting authors string to list", (Throwable) e);
        }
        String seasonNumber = download.getSeasonNumber();
        return BookmarkWriteElement.builder().withId(download.getId()).withAuthors(arrayList).withDescription(download.getDescription()).withTitle(download.getTitle()).withImage(download.getImage()).withPodcastImage(download.getPodcastImage()).withPodcastId(download.getPodcastId()).withPodcastTitle(download.getPodcastTitle()).withTotalDurationMilliseconds(Long.valueOf(download.getDurationMilliseconds())).withProgressMilliSeconds(0L).withSeasonNumber(StringUtils.isNumeric(seasonNumber) ? Integer.valueOf(Integer.parseInt(seasonNumber)) : null).withPublishDate(download.getPublishDate()).build();
    }

    private BookmarkWriteElement bookmarkWriteElement(final Save save) {
        ArrayList arrayList = new ArrayList();
        try {
            arrayList.addAll(Strings.convertStringToList(save.getAuthors()));
        } catch (IOException e) {
            logger.error("Error converting authors string to list", (Throwable) e);
        }
        String seasonNumber = save.getSeasonNumber();
        return BookmarkWriteElement.builder().withId(save.getId()).withAuthors(arrayList).withDescription(save.getDescription()).withTitle(save.getTitle()).withImage(save.getImage()).withPodcastImage(save.getPodcastImage()).withPodcastId(save.getPodcastId()).withPodcastTitle(save.getPodcastTitle()).withSeasonNumber(StringUtils.isNumeric(seasonNumber) ? Integer.valueOf(Integer.parseInt(seasonNumber)) : null).withPublishDate(save.getPublishDate()).withTotalDurationMilliseconds(Long.valueOf(save.getTotalDurationMilliseconds())).withProgressMilliSeconds(0L).build();
    }

    private BookmarkWriteElement bookmarkWriteElement(final JumpBackInCacheItemElement jumpBackIn) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(jumpBackIn.getAuthors());
        return BookmarkWriteElement.builder().withId(jumpBackIn.getEpisodeId()).withAuthors(arrayList).withDescription(jumpBackIn.getDescription()).withTitle(jumpBackIn.getTitle()).withImage(jumpBackIn.getImage()).withPodcastImage(jumpBackIn.getPodcastImage()).withPodcastId(jumpBackIn.getPodcastId()).withPodcastTitle(jumpBackIn.getPodcastTitle()).withSeasonNumber(jumpBackIn.getSeasonNumber()).withPublishDate(jumpBackIn.getPublishDate()).withTotalDurationMilliseconds(jumpBackIn.getTotalDurationMilliseconds()).withProgressMilliSeconds(0L).build();
    }

    private static final List<Method> updateFollowMethods(final Save save, final boolean isGoingToFollow) {
        WriteMethod writeMethodBuild = WriteMethod.builder().withElement(Collections.singletonList(FollowWriteElement.builder().withId(save.getPodcastId()).withTitle(save.getPodcastTitle()).withPublisher("").withImage(save.getPodcastImage()).withDescription("").withFollowed(isGoingToFollow).withPodcastShowVariantId(save.getPodcastShowVariantId()).build())).withCondition(null).withForced(Boolean.FALSE).withQueue(Queues.followSync()).build();
        ArrayList arrayList = new ArrayList();
        arrayList.add(writeMethodBuild);
        return arrayList;
    }

    public PlayEpisodeElement playEpisodeElement(final JumpBackInCacheItemElement jumpBackIn, final UiMetricAttributes$PageType pageType, final Resources resources) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(DeeplinkMethods.savedEpisodePlay(jumpBackIn.getEpisodeId(), resources));
        arrayList.addAll(uiMetricClickMethods(MetricsEntityIds.podcastEpisode(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), jumpBackIn.getEpisodeId(), jumpBackIn.getPodcastEpisodeVariantId()), UiMetricAttributes$ActionType.PLAY_PODCAST_EPISODE, pageType));
        return PlayEpisodeElement.builder().withOnItemSelected(arrayList).build();
    }

    public SaveElement saveElement(final JumpBackInCacheItemElement jumpBackIn, final UiMetricAttributes$PageType pageType) {
        return SaveElement.builder().withId(jumpBackIn.getEpisodeId()).withOnSave(onSaveMethods(jumpBackIn, pageType)).withOnUnsave(onUnSaveMethods(jumpBackIn, pageType)).build();
    }

    private List<Method> onSaveMethods(final JumpBackInCacheItemElement jumpBackIn, final UiMetricAttributes$PageType pageType) {
        WriteMethod writeMethodBuild = WriteMethod.builder().withQueue(Queues.appSync()).withElement(Collections.singletonList(saveWriteElement(jumpBackIn, true))).build();
        ArrayList arrayList = new ArrayList();
        arrayList.add(writeMethodBuild);
        arrayList.addAll(uiMetricClickMethods(MetricsEntityIds.podcastEpisode(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), jumpBackIn.getEpisodeId(), jumpBackIn.getPodcastEpisodeVariantId()), UiMetricAttributes$ActionType.SAVE_PODCAST_EPISODE, pageType));
        return arrayList;
    }

    private List<Method> onUnSaveMethods(final JumpBackInCacheItemElement jumpBackIn, final UiMetricAttributes$PageType pageType) {
        WriteMethod writeMethodBuild = WriteMethod.builder().withQueue(Queues.appSync()).withElement(Collections.singletonList(saveWriteElement(jumpBackIn, false))).build();
        ArrayList arrayList = new ArrayList();
        arrayList.add(writeMethodBuild);
        arrayList.addAll(uiMetricClickMethods(MetricsEntityIds.podcastEpisode(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), jumpBackIn.getEpisodeId(), jumpBackIn.getPodcastEpisodeVariantId()), UiMetricAttributes$ActionType.UNSAVE_PODCAST_EPISODE, pageType));
        return arrayList;
    }

    private SaveWriteElement saveWriteElement(final JumpBackInCacheItemElement jumpBackIn, final boolean isSaved) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(jumpBackIn.getAuthors());
        return SaveWriteElement.builder().withId(jumpBackIn.getEpisodeId()).withAuthors(arrayList).withDescription(jumpBackIn.getDescription()).withTitle(jumpBackIn.getTitle()).withImage(jumpBackIn.getImage()).withPodcastImage(jumpBackIn.getPodcastImage()).withPodcastId(jumpBackIn.getPodcastId()).withPodcastTitle(jumpBackIn.getPodcastTitle()).withSaved(isSaved).withTotalDurationMilliseconds(jumpBackIn.getTotalDurationMilliseconds()).withSeasonNumber(jumpBackIn.getSeasonNumber()).withPublishDate(jumpBackIn.getPublishDate()).withPlaybackMode(PlaybackModes.mode(jumpBackIn.getPlaybackMode())).withAvailableUpsells(Upsells.types(jumpBackIn.getAvailableUpsells())).withContentTraits(ContentTraits.traits(jumpBackIn.getContentTraits())).withAvailabilityDate(jumpBackIn.getAvailabilityDate()).withPodcastEpisodeVariantId(jumpBackIn.getPodcastEpisodeVariantId()).withPodcastShowVariantId(jumpBackIn.getPodcastShowVariantId()).build();
    }

    private static final List<Method> updateFollowMethods(final JumpBackInCacheItemElement jumpBackIn, final boolean isGoingToFollow) {
        WriteMethod writeMethodBuild = WriteMethod.builder().withElement(Collections.singletonList(FollowWriteElement.builder().withId(jumpBackIn.getPodcastId()).withTitle(jumpBackIn.getPodcastTitle()).withPublisher("").withImage(jumpBackIn.getPodcastImage()).withDescription(jumpBackIn.getDescription()).withFollowed(isGoingToFollow).withPodcastShowVariantId(jumpBackIn.getPodcastShowVariantId()).build())).withCondition(null).withForced(Boolean.FALSE).withQueue(Queues.followSync()).build();
        ArrayList arrayList = new ArrayList();
        arrayList.add(writeMethodBuild);
        return arrayList;
    }

    private static List<Method> uiMetricClickMethods(final UiMetricAttributes$ActionType actionType, final UiMetricAttributes$PageType pageType) {
        return UiMetricMethods.onClicked(pageType, actionType, UiMetricAttributes$ContentName.PODCAST_CONTEXT_MENU);
    }

    private static List<Method> uiMetricClickMethods(final String id, final UiMetricAttributes$ActionType actionType, final UiMetricAttributes$PageType pageType) {
        return UiMetricMethods.onClicked(id, pageType, actionType, UiMetricAttributes$ContentName.PODCAST_CONTEXT_MENU);
    }

    private CarPresetElement podcastShowCarPresetElement(final String podcastId) {
        if (PodcastFeatureGating.PODCAST_ADD_TO_PRESETS.isEnabled()) {
            return PodcastShowCarPresetElement.builder().withId(podcastId).build();
        }
        return null;
    }

    private ReportElement reportElement(final Download download, final UiMetricAttributes$PageType pageType) {
        if (!PodcastFeatureGating.PODCAST_EPISODE_REPORTING.isEnabled() || Podcast.isFireOS()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(navigateToReportEpisodeMethod(download.getId(), pageType));
        arrayList.addAll(uiMetricClickMethods(UiMetricAttributes$ActionType.REPORT_PODCAST_EPISODE, pageType));
        return ReportElement.builder().withId(download.getId()).withOnReport(arrayList).build();
    }

    private ReportElement reportElement(final Save save, final UiMetricAttributes$PageType pageType) {
        if (!PodcastFeatureGating.PODCAST_EPISODE_REPORTING.isEnabled() || Podcast.isFireOS()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(navigateToReportEpisodeMethod(save.getId(), pageType));
        arrayList.addAll(uiMetricClickMethods(UiMetricAttributes$ActionType.REPORT_PODCAST_EPISODE, pageType));
        return ReportElement.builder().withId(save.getId()).withOnReport(arrayList).build();
    }

    private ReportElement reportElement(final JumpBackInCacheItemElement jumpBackIn, final UiMetricAttributes$PageType pageType) {
        if (!PodcastFeatureGating.PODCAST_EPISODE_REPORTING.isEnabled() || Podcast.isFireOS()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(navigateToReportEpisodeMethod(jumpBackIn.getEpisodeId(), pageType));
        arrayList.addAll(uiMetricClickMethods(UiMetricAttributes$ActionType.REPORT_PODCAST_EPISODE, pageType));
        return ReportElement.builder().withId(jumpBackIn.getEpisodeId()).withOnReport(arrayList).build();
    }

    private ReportElement reportElement(final String episodeId, final UiMetricAttributes$PageType pageType) {
        if (!PodcastFeatureGating.PODCAST_EPISODE_REPORTING.isEnabled() || Podcast.isFireOS()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(navigateToReportEpisodeMethod(episodeId, pageType));
        arrayList.addAll(uiMetricClickMethods(UiMetricAttributes$ActionType.REPORT_PODCAST_EPISODE, pageType));
        return ReportElement.builder().withId(episodeId).withOnReport(arrayList).build();
    }

    private NavigateToDeeplinkMethod navigateToReportEpisodeMethod(final String id, final UiMetricAttributes$PageType pageType) {
        return NavigateToDeeplinkMethod.builder().withUrl(Deeplinks.internalReportEpisodeDeeplink(id, pageType)).withQueue(Queues.template()).build();
    }
}
