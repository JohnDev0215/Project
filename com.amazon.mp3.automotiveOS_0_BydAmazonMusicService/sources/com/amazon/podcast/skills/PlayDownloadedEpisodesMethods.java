package com.amazon.podcast.skills;

import PlaybackMetricsInterface.v1_0.PlaybackSignalType;
import Podcast.BookmarkInterface.v1_0.BookmarkWriteElement;
import Podcast.CompletedInterface.v1_0.CompletedWriteElement;
import Podcast.PlaybackInterface.v1_0.BufferMediaMethod;
import Podcast.PlaybackInterface.v1_0.ClearMediaMethod;
import Podcast.PlaybackInterface.v1_0.FastForwardElement;
import Podcast.PlaybackInterface.v1_0.MediaActionsElement;
import Podcast.PlaybackInterface.v1_0.MediaControlsElement;
import Podcast.PlaybackInterface.v1_0.MediaEventsElement;
import Podcast.PlaybackInterface.v1_0.MediaMetadataElement;
import Podcast.PlaybackInterface.v1_0.PlayMediaMethod;
import Podcast.PlaybackInterface.v1_0.PlayPauseElement;
import Podcast.PlaybackInterface.v1_0.PlaybackSpeedElement;
import Podcast.PlaybackInterface.v1_0.RewindElement;
import Podcast.PlaybackInterface.v1_0.SetMediaMethod;
import Podcast.PlaybackInterface.v1_0.SetMediaOffsetMethod;
import Podcast.PlaybackInterface.v1_0.SleepTimerElement;
import Podcast.PlaybackMetricsInterface.v1_0.PlaybackMetricWriteElement;
import Podcast.Touch.AlertTemplateInterface.v1_0.AlertTemplate;
import Podcast.Touch.AlertTemplateInterface.v1_0.PositiveButtonElement;
import Podcast.Touch.NowPlayingTemplateInterface.v1_0.NowPlayingTemplate;
import SOAAppSyncInterface.v1_0.WriteLocalMethod;
import SOAAppSyncInterface.v1_0.WriteMethod;
import SOACoreInterface.v1_0.Method;
import SOATemplateListInterface.v1_0.CreateAndBindTemplateMethod;
import SOATemplateListInterface.v1_0.TemplateLaunchMode;
import SOATemplateListInterface.v1_0.TemplateScreenMode;
import android.content.res.Resources;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.Queues;
import com.amazon.podcast.R$string;
import com.amazon.podcast.deeplinks.DeeplinkMethods;
import com.amazon.podcast.deeplinks.Deeplinks;
import com.amazon.podcast.downloads.Download;
import com.amazon.podcast.downloads.DownloadState;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import com.amazon.podcast.metrics.UiMetricAttributes$ActionType;
import com.amazon.podcast.metrics.UiMetricAttributes$ContentName;
import com.amazon.podcast.metrics.UiMetricAttributes$EntityIdType;
import com.amazon.podcast.metrics.UiMetricAttributes$EntityType;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.metrics.UiMetricMethods;
import com.amazon.podcast.redhoodPlus.MetricsEntityIds;
import com.amazon.podcast.views.Strings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class PlayDownloadedEpisodesMethods {
    private static final Logger logger = LoggerFactory.getLogger("PlayDownloadedEpisodesM");

    public static List<Method> playEpisode(final Download download, final Resources resources) {
        if (DownloadState.lookup(download.getState()) != DownloadState.DOWNLOADED) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(clearMediaMethod());
        arrayList.addAll(createAndBindTemplate());
        arrayList.addAll(playMethods(download, resources));
        return arrayList;
    }

    public static List<Method> playEpisode(final Download download, final boolean launchNowPlaying, final Resources resources) {
        if (DownloadState.lookup(download.getState()) != DownloadState.DOWNLOADED) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(clearMediaMethod());
        if (launchNowPlaying) {
            arrayList.addAll(createAndBindTemplate());
        }
        arrayList.addAll(playMethods(download, resources));
        return arrayList;
    }

    public static List<Method> playNextEpisode(final Download download, final Resources resources) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(ClearMediaMethod.builder().withQueue(Queues.playback()).build());
        arrayList.addAll(playMethods(download, resources));
        return arrayList;
    }

    public static List<Method> playPreviousEpisode(final Download download, final Resources resources) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(ClearMediaMethod.builder().withQueue(Queues.playback()).build());
        arrayList.addAll(playMethods(download, resources));
        return arrayList;
    }

    public static List<Method> restoreEpisode(final Download download, final Resources resources) {
        EpisodeOptionsElements episodeOptionsElements = new EpisodeOptionsElements();
        if (DownloadState.lookup(download.getState()) != DownloadState.DOWNLOADED) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(clearMediaMethod());
        SetMediaMethod.Builder builderWithQueue = SetMediaMethod.builder().withQueue(Queues.playback());
        Boolean bool = Boolean.TRUE;
        arrayList.add(builderWithQueue.withForced(bool).withMetadata(mediaMetadata(download)).withActions(mediaActions()).withControls(mediaControls()).withEvents(mediaEvents(download, resources)).withIgnorable(bool).withEpisodeOptions(episodeOptionsElements.episodeOptionsElementForNPS(download, UiMetricAttributes$PageType.PODCAST_NOW_PLAYING, resources)).build());
        arrayList.add(BufferMediaMethod.builder().withQueue(Queues.playback()).withForced(bool).withMediaId(download.getId()).withAudioUri(download.getLocalAudioUri()).withIgnorable(bool).build());
        arrayList.add(SetMediaOffsetMethod.builder().withQueue(Queues.playback()).withMediaId(download.getId()).withRewindOffsetMilliseconds(Long.valueOf(TimeUnit.SECONDS.toMillis(10L))).withForced(Boolean.FALSE).withIgnorable(bool).build());
        return arrayList;
    }

    public static List<Method> restoreAndStartEpisode(final Download download, final Resources resources) {
        EpisodeOptionsElements episodeOptionsElements = new EpisodeOptionsElements();
        if (DownloadState.lookup(download.getState()) != DownloadState.DOWNLOADED) {
            return Collections.emptyList();
        }
        InvokeLocalSkillMethod invokeLocalSkillMethod = new InvokeLocalSkillMethod(new RestoreAndStartDownloadedEpisodesPlaybackTarget(download.getId()), Queues.playback());
        ArrayList arrayList = new ArrayList();
        arrayList.add(invokeLocalSkillMethod);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.addAll(clearMediaMethod());
        SetMediaMethod.Builder builderWithQueue = SetMediaMethod.builder().withQueue(Queues.playback());
        Boolean bool = Boolean.TRUE;
        SetMediaMethod.Builder builderWithEvents = builderWithQueue.withForced(bool).withMetadata(mediaMetadata(download)).withActions(mediaActions()).withControls(mediaControls()).withEvents(mediaEvents(download, resources));
        Boolean bool2 = Boolean.FALSE;
        arrayList2.add(builderWithEvents.withIgnorable(bool2).withEpisodeOptions(episodeOptionsElements.episodeOptionsElementForNPS(download, UiMetricAttributes$PageType.PODCAST_NOW_PLAYING, resources)).withOnRestoreAndStart(arrayList).build());
        arrayList2.add(BufferMediaMethod.builder().withQueue(Queues.playback()).withForced(bool).withMediaId(download.getId()).withAudioUri(download.getLocalAudioUri()).withIgnorable(bool2).build());
        arrayList2.add(PlayMediaMethod.builder().withQueue(Queues.playback()).withMediaId(download.getId()).withResumeAtBookmark(bool).withRewindOffsetMilliseconds(Long.valueOf(TimeUnit.SECONDS.toMillis(10L))).withForced(bool).build());
        return arrayList2;
    }

    public static List<Method> playMethods(final Download download, final Resources resources) {
        EpisodeOptionsElements episodeOptionsElements = new EpisodeOptionsElements();
        SetMediaMethod.Builder builderWithQueue = SetMediaMethod.builder().withQueue(Queues.playback());
        Boolean bool = Boolean.TRUE;
        return Arrays.asList(builderWithQueue.withForced(bool).withMetadata(mediaMetadata(download)).withActions(mediaActions()).withControls(mediaControls()).withEvents(mediaEvents(download, resources)).withEpisodeOptions(episodeOptionsElements.episodeOptionsElementForNPS(download, UiMetricAttributes$PageType.PODCAST_NOW_PLAYING, resources)).build(), BufferMediaMethod.builder().withQueue(Queues.playback()).withForced(bool).withMediaId(download.getId()).withAudioUri(download.getLocalAudioUri()).build(), PlayMediaMethod.builder().withQueue(Queues.playback()).withMediaId(download.getId()).withResumeAtBookmark(bool).withForced(bool).build());
    }

    private static MediaMetadataElement mediaMetadata(final Download download) {
        return MediaMetadataElement.builder().withMediaId(download.getId()).withTitle(download.getTitle()).withArtwork(download.getImage()).withSubtitle(download.getPodcastTitle()).withDescription(download.getDescription()).withMediaCollectionId(download.getPodcastId()).withDurationSeconds(Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(download.getDurationMilliseconds()))).withPodcastDeeplink(Deeplinks.podcast(download.getPodcastId(), download.getPodcastTitle())).withOnAdditionalMetadataRequired(Collections.emptyList()).build();
    }

    private static MediaActionsElement mediaActions() {
        return MediaActionsElement.builder().withOnNext(PlayNextDownloadedEpisodeSkill.prepareInvoke()).withOnPrevious(PlayPreviousDownloadedEpisodeSkill.prepareInvoke()).build();
    }

    private static MediaControlsElement mediaControls() {
        return MediaControlsElement.builder().withPlayPause(playPause()).withFastForward(fastForward()).withRewind(rewind()).withPlaybackSpeed(playbackSpeed()).withSleepTimer(sleepTimer()).build();
    }

    private static PlayPauseElement playPause() {
        return PlayPauseElement.builder().build();
    }

    private static RewindElement rewind() {
        return RewindElement.builder().build();
    }

    private static FastForwardElement fastForward() {
        return FastForwardElement.builder().build();
    }

    private static PlaybackSpeedElement playbackSpeed() {
        return PlaybackSpeedElement.builder().withOnItemSelected(Collections.emptyList()).build();
    }

    private static SleepTimerElement sleepTimer() {
        return SleepTimerElement.builder().withOnItemSelected(Collections.emptyList()).build();
    }

    private static MediaEventsElement mediaEvents(final Download download, final Resources resources) {
        List<Method> listWriteLocalBookmarkMethods;
        boolean zIsEnabled = PodcastFeatureGating.LOCAL_HEARTBEAT.isEnabled();
        Long lValueOf = Long.valueOf(System.currentTimeMillis());
        List<Method> listWriteBookmarkMethods = writeBookmarkMethods(download);
        List<Method> list = zIsEnabled ? Collections.EMPTY_LIST : listWriteBookmarkMethods;
        if (zIsEnabled) {
            listWriteLocalBookmarkMethods = writeLocalBookmarkMethods(download);
        } else {
            listWriteLocalBookmarkMethods = Collections.EMPTY_LIST;
        }
        List<Method> list2 = zIsEnabled ? listWriteBookmarkMethods : Collections.EMPTY_LIST;
        List<Method> listWriteCompletedMethods = writeCompletedMethods(download);
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(PlayNextDownloadedEpisodeSkill.prepareInvoke());
        arrayList.addAll(listWriteBookmarkMethods);
        arrayList.addAll(writePlaybackMetricMethods(download, lValueOf, PlaybackSignalType.PLAYBACK_FINISHED));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.addAll(list);
        arrayList2.addAll(writePlaybackMetricMethods(download, lValueOf, PlaybackSignalType.PLAYBACK_STARTED));
        ArrayList arrayList3 = new ArrayList();
        arrayList3.addAll(list);
        arrayList3.addAll(writePlaybackMetricMethods(download, lValueOf, PlaybackSignalType.PLAYBACK_RESUMED));
        arrayList3.addAll(uiMetricClickMethods(download, UiMetricAttributes$ActionType.PLAY_PODCAST_EPISODE));
        ArrayList arrayList4 = new ArrayList();
        arrayList4.addAll(listWriteBookmarkMethods);
        arrayList4.addAll(writePlaybackMetricMethods(download, lValueOf, PlaybackSignalType.PLAYBACK_PAUSED));
        arrayList4.addAll(uiMetricClickMethods(download, UiMetricAttributes$ActionType.PLAY_PAUSE_PODCAST_EPISODE));
        ArrayList arrayList5 = new ArrayList();
        arrayList5.addAll(listWriteBookmarkMethods);
        arrayList5.addAll(writePlaybackMetricMethods(download, lValueOf, PlaybackSignalType.PLAYBACK_STOPPED));
        ArrayList arrayList6 = new ArrayList();
        arrayList6.addAll(list);
        arrayList6.addAll(writePlaybackMetricMethods(download, lValueOf, PlaybackSignalType.PLAYBACK_SCRUBBED));
        ArrayList arrayList7 = new ArrayList();
        arrayList7.addAll(writePlaybackMetricMethods(download, lValueOf, PlaybackSignalType.PLAYBACK_REBUFFERED));
        ArrayList arrayList8 = new ArrayList();
        arrayList8.addAll(list);
        arrayList8.addAll(writePlaybackMetricMethods(download, lValueOf, PlaybackSignalType.PLAYBACK_SCRUBBED));
        arrayList8.addAll(uiMetricClickMethods(download, UiMetricAttributes$ActionType.FAST_FORWARD_PODCAST_EPISODE));
        ArrayList arrayList9 = new ArrayList();
        arrayList9.addAll(list);
        arrayList9.addAll(writePlaybackMetricMethods(download, lValueOf, PlaybackSignalType.PLAYBACK_SCRUBBED));
        arrayList9.addAll(uiMetricClickMethods(download, UiMetricAttributes$ActionType.REWIND_PODCAST_EPISODE));
        ArrayList arrayList10 = new ArrayList();
        arrayList10.addAll(uiMetricClickMethods(download, UiMetricAttributes$ActionType.SELECT_PODCAST_SLEEP_TIMER));
        ArrayList arrayList11 = new ArrayList();
        arrayList11.addAll(onErrorAlertTemplate(resources));
        arrayList11.addAll(writePlaybackMetricMethods(download, lValueOf, PlaybackSignalType.PLAYBACK_ERROR));
        ArrayList arrayList12 = new ArrayList();
        arrayList12.addAll(listWriteLocalBookmarkMethods);
        ArrayList arrayList13 = new ArrayList();
        arrayList13.addAll(list2);
        ArrayList arrayList14 = new ArrayList();
        arrayList14.addAll(listWriteCompletedMethods);
        return MediaEventsElement.builder().withOnStarted(arrayList2).withOnResumed(arrayList3).withOnScrubbed(arrayList6).withOnPaused(arrayList4).withOnFinished(arrayList).withOnStopped(arrayList5).withOnRebuffered(arrayList7).withOnFastForward(arrayList8).withOnRewind(arrayList9).withOnPlaybackSpeedModified(Collections.emptyList()).withOnSleepTimerSet(arrayList10).withOnSleepTimerElapsed(Collections.emptyList()).withOnCompletedThresholdElapsed(arrayList14).withOnWriteFrequencyReached(arrayList12).withOnWriteAndReportFrequencyReached(arrayList13).withOnError(arrayList11).build();
    }

    private static final List<Method> writeBookmarkMethods(final Download download) {
        List<String> listConvertStringToList;
        try {
            listConvertStringToList = Strings.convertStringToList(download.getAuthors());
        } catch (Exception e) {
            ArrayList arrayList = new ArrayList();
            logger.error("Error converting authors string to list", (Throwable) e);
            listConvertStringToList = arrayList;
        }
        String seasonNumber = download.getSeasonNumber();
        return Collections.singletonList(WriteMethod.builder().withElement(Collections.singletonList(BookmarkWriteElement.builder().withId(download.getId()).withSeasonNumber(StringUtils.isNotBlank(seasonNumber) ? Integer.valueOf(Integer.parseInt(seasonNumber)) : null).withImage(download.getImage()).withPodcastImage(download.getPodcastImage()).withPodcastId(download.getPodcastId()).withPodcastTitle(download.getPodcastTitle()).withTitle(download.getTitle()).withDescription(download.getDescription()).withAuthors(listConvertStringToList).withTotalDurationMilliseconds(Long.valueOf(download.getDurationMilliseconds())).build())).withForced(Boolean.FALSE).withQueue(Queues.bookmarkSync()).build());
    }

    private static final List<Method> writeLocalBookmarkMethods(final Download download) {
        List<String> listConvertStringToList;
        try {
            listConvertStringToList = Strings.convertStringToList(download.getAuthors());
        } catch (Exception e) {
            ArrayList arrayList = new ArrayList();
            logger.error("Error converting authors string to list", (Throwable) e);
            listConvertStringToList = arrayList;
        }
        String seasonNumber = download.getSeasonNumber();
        return Collections.singletonList(WriteLocalMethod.builder().withElement(Collections.singletonList(BookmarkWriteElement.builder().withId(download.getId()).withSeasonNumber(StringUtils.isNotBlank(seasonNumber) ? Integer.valueOf(Integer.parseInt(seasonNumber)) : null).withImage(download.getImage()).withPodcastImage(download.getPodcastImage()).withPodcastId(download.getPodcastId()).withPodcastTitle(download.getPodcastTitle()).withTitle(download.getTitle()).withDescription(download.getDescription()).withAuthors(listConvertStringToList).withTotalDurationMilliseconds(Long.valueOf(download.getDurationMilliseconds())).build())).withForced(Boolean.FALSE).withQueue(Queues.bookmarkSync()).build());
    }

    private static final List<Method> writeCompletedMethods(final Download download) {
        List<String> listConvertStringToList;
        try {
            listConvertStringToList = Strings.convertStringToList(download.getAuthors());
        } catch (Exception e) {
            ArrayList arrayList = new ArrayList();
            logger.error("Error converting authors string to list", (Throwable) e);
            listConvertStringToList = arrayList;
        }
        String seasonNumber = download.getSeasonNumber();
        return Collections.singletonList(WriteMethod.builder().withElement(Collections.singletonList(CompletedWriteElement.builder().withId(download.getId()).withPodcastId(download.getPodcastId()).withPodcastTitle(download.getPodcastTitle()).withTitle(download.getTitle()).withPublishDate(download.getPublishDate()).withDescription(download.getDescription()).withAuthors(listConvertStringToList).withSeasonNumber(StringUtils.isNotBlank(seasonNumber) ? Integer.valueOf(Integer.parseInt(seasonNumber)) : null).withImage(download.getImage()).withPodcastImage(download.getPodcastImage()).withCompleted(true).build())).withForced(Boolean.FALSE).withQueue(Queues.completedSync()).build());
    }

    private static final List<Method> writePlaybackMetricMethods(final Download download, final Long playbackRequestedAtTimestampMilliseconds, final String signalType) {
        return Arrays.asList(WriteMethod.builder().withElement(Arrays.asList(PlaybackMetricWriteElement.builder().withId(download.getId()).withMediaId(download.getId()).withMediaTitle(download.getTitle()).withMediaCollectionId(download.getPodcastId()).withMediaSubTitle(download.getPodcastTitle()).withPlaybackRequestedAtTimestampMilliseconds(playbackRequestedAtTimestampMilliseconds).withMediaCollectionType("DOWNLOADED_EPISODES").withIsMediaDownloaded(true).withPlaybackSignalType(signalType).build())).withForced(Boolean.FALSE).withQueue(Queues.playbackMetricsSync()).build());
    }

    private static final List<Method> clearMediaMethod() {
        return Collections.singletonList(ClearMediaMethod.builder().withQueue(Queues.playback()).withForced(Boolean.TRUE).build());
    }

    private static final List<Method> createAndBindTemplate() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(DeeplinkMethods.incrementalSyncOnViewed());
        return Collections.singletonList(CreateAndBindTemplateMethod.builder().withQueue(Queues.template()).withTemplate(NowPlayingTemplate.builder().withLaunchMode(TemplateLaunchMode.singleInstance).withOnViewed((List<Method>) arrayList).build()).withScreenMode(TemplateScreenMode.templateList).build());
    }

    private static final List<Method> onErrorAlertTemplate(final Resources resources) {
        return Arrays.asList(CreateAndBindTemplateMethod.builder().withForced(Boolean.FALSE).withQueue(Queues.template()).withTemplate(AlertTemplate.builder().withTitle(resources.getString(R$string.podcast_playback_error_title)).withSubtitle(resources.getString(R$string.podcast_playback_error_subtitle)).withPositiveButton(PositiveButtonElement.builder().withOnItemSelected(Collections.emptyList()).withText(resources.getString(R$string.podcast_alert_ok)).build()).withLaunchMode(TemplateLaunchMode.singleUse).build()).withScreenMode("standard").build());
    }

    private static List<Method> uiMetricClickMethods(final Download download, final UiMetricAttributes$ActionType actionType) {
        return UiMetricMethods.onClicked(UiMetricAttributes$PageType.NONE, actionType, UiMetricAttributes$EntityType.PODCAST_EPISODE, UiMetricAttributes$EntityIdType.PODCAST_EPISODE_ID, MetricsEntityIds.podcastEpisode(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), download.getId(), download.getPodcastEpisodeVariantId()), (Integer) null, UiMetricAttributes$ContentName.PODCAST_PLAYBACK_BUTTONS);
    }
}
