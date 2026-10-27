package com.amazon.podcast.deeplinks;

import Podcast.BookmarkInterface.v1_0.BookmarksLastSyncTimeClientState;
import Podcast.CompletedInterface.v1_0.CompletedLastSyncTimeClientState;
import Podcast.DeeplinkInterface.v1_0.InvokeDeeplinkMethod;
import Podcast.FollowInterface.v1_0.FollowsLastSyncTimeClientState;
import Podcast.FollowPromptInterface.v1_0.HidePromptPreferenceClientState;
import Podcast.JumpBackInInterface.v1_0.JumpBackInCacheItemElement;
import Podcast.LatencyMetricsInterface.v1_0.LatencyMetricElement;
import Podcast.PlaybackInterface.v1_0.ClearMediaMethod;
import Podcast.PlaybackInterface.v1_0.LocalEpisodeAlreadyPlayingClientState;
import Podcast.PlaybackInterface.v1_0.PlayLocalEpisodesMethod;
import Podcast.SaveInterface.v1_0.SavesLastSyncTimeClientState;
import Podcast.Touch.AlertTemplateInterface.v1_0.AlertTemplate;
import Podcast.Touch.AlertTemplateInterface.v1_0.PositiveButtonElement;
import Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.CardNowPlayingTemplate;
import Podcast.Touch.NowPlayingTemplateInterface.v1_0.NowPlayingTemplate;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCTemplate;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCTemplateShardsElement;
import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.HeaderElement;
import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.PodcastDetailTemplate;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Queue;
import SOATemplateListInterface.v1_0.CreateAndBindTemplateMethod;
import SOATemplateListInterface.v1_0.CreateTemplateMethod;
import SOATemplateListInterface.v1_0.RemoveTemplateMethod;
import SOATemplateListInterface.v1_0.TemplateLaunchMode;
import SOATemplateListInterface.v1_0.TemplateScreenMode;
import android.content.res.Resources;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.Queues;
import com.amazon.podcast.R$string;
import com.amazon.podcast.bookmark.Bookmark;
import com.amazon.podcast.downloads.Download;
import com.amazon.podcast.downloads.DownloadRequestCompletedMethod;
import com.amazon.podcast.downloads.DownloadRequestedMethod;
import com.amazon.podcast.endpoints.Endpoint;
import com.amazon.podcast.endpoints.Endpoints;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import com.amazon.podcast.follow.Follow;
import com.amazon.podcast.metrics.latency.LatencyMetricAttributes$PageType;
import com.amazon.podcast.save.Save;
import com.amazon.podcast.views.podcastDetailTemplate.PodcastDetailClientState;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public final class DeeplinkMethods {
    private static final String HIDE_PROMPT_PREFERENCES_CLIENT_STATE = HidePromptPreferenceClientState.class.getCanonicalName();

    public static List<Method> podcast(final Follow follow, final Resources resources) {
        return Collections.singletonList(CreateTemplateMethod.builder().withQueue(Queues.template()).withTemplate(PodcastDetailTemplate.builder().withBackgroundImage(follow.getImage()).withHeader(HeaderElement.builder().withImage(follow.getImage()).withPrimaryText(follow.getTitle()).build()).withOnCreated(Collections.singletonList(invokeDeeplinkMethod(Queues.template(), Endpoints.browse(Podcast.getUserInfoProvider().marketplaceId()), Deeplinks.podcast(follow.getId(), follow.getTitle()), onErrorAlertTemplate(resources), LatencyMetricAttributes$PageType.PODCAST_SHOW_DETAIL, PodcastDetailClientState.getRefinementClientStateName(follow.getId()), HIDE_PROMPT_PREFERENCES_CLIENT_STATE))).build()).withScreenMode(TemplateScreenMode.templateList).build());
    }

    public static List<Method> podcast(final Save save, final Resources resources) {
        return Collections.singletonList(CreateTemplateMethod.builder().withQueue(Queues.template()).withTemplate(PodcastDetailTemplate.builder().withHeader(HeaderElement.builder().withPrimaryText(save.getPodcastTitle()).build()).withOnCreated(Collections.singletonList(invokeDeeplinkMethod(Queues.template(), Endpoints.browse(Podcast.getUserInfoProvider().marketplaceId()), Deeplinks.podcast(save.getPodcastId(), save.getPodcastTitle()), onErrorAlertTemplate(resources), LatencyMetricAttributes$PageType.PODCAST_SHOW_DETAIL, PodcastDetailClientState.getRefinementClientStateName(save.getPodcastId()), HIDE_PROMPT_PREFERENCES_CLIENT_STATE))).build()).withScreenMode(TemplateScreenMode.templateList).build());
    }

    public static List<Method> podcast(final Bookmark bookmark, final Resources resources) {
        return Collections.singletonList(CreateTemplateMethod.builder().withQueue(Queues.template()).withTemplate(PodcastDetailTemplate.builder().withHeader(HeaderElement.builder().withPrimaryText(bookmark.getPodcastTitle()).build()).withOnCreated(Collections.singletonList(invokeDeeplinkMethod(Queues.template(), Endpoints.browse(Podcast.getUserInfoProvider().marketplaceId()), Deeplinks.podcast(bookmark.getPodcastId(), bookmark.getPodcastTitle()), onErrorAlertTemplate(resources), PodcastDetailClientState.getRefinementClientStateName(bookmark.getPodcastId()), HIDE_PROMPT_PREFERENCES_CLIENT_STATE))).build()).withScreenMode(TemplateScreenMode.templateList).build());
    }

    public static List<Method> podcast(final JumpBackInCacheItemElement jumpBackIn, final Resources resources) {
        return Collections.singletonList(CreateTemplateMethod.builder().withQueue(Queues.template()).withTemplate(PodcastDetailTemplate.builder().withHeader(HeaderElement.builder().withPrimaryText(jumpBackIn.getPodcastTitle()).build()).withOnCreated(Collections.singletonList(invokeDeeplinkMethod(Queues.template(), Endpoints.browse(Podcast.getUserInfoProvider().marketplaceId()), Deeplinks.podcast(jumpBackIn.getPodcastId(), jumpBackIn.getPodcastTitle()), onErrorAlertTemplate(resources), PodcastDetailClientState.getRefinementClientStateName(jumpBackIn.getPodcastId()), HIDE_PROMPT_PREFERENCES_CLIENT_STATE))).build()).withScreenMode(TemplateScreenMode.templateList).build());
    }

    public static List<Method> podcast(final Download download, final Resources resources) {
        return Collections.singletonList(CreateTemplateMethod.builder().withQueue(Queues.template()).withTemplate(PodcastDetailTemplate.builder().withHeader(HeaderElement.builder().withPrimaryText(download.getPodcastTitle()).build()).withOnCreated(Collections.singletonList(invokeDeeplinkMethod(Queues.template(), Endpoints.browse(Podcast.getUserInfoProvider().marketplaceId()), Deeplinks.podcast(download.getPodcastId(), download.getPodcastTitle()), onErrorAlertTemplate(resources), LatencyMetricAttributes$PageType.PODCAST_SHOW_DETAIL, PodcastDetailClientState.getRefinementClientStateName(download.getPodcastId()), HIDE_PROMPT_PREFERENCES_CLIENT_STATE))).build()).withScreenMode(TemplateScreenMode.templateList).build());
    }

    public static List<Method> podcast(final String podcastId, final String podcasTitle, final Resources resources) {
        return Collections.singletonList(CreateTemplateMethod.builder().withQueue(Queues.template()).withTemplate(PodcastDetailTemplate.builder().withHeader(HeaderElement.builder().withPrimaryText(podcasTitle).build()).withOnCreated(Collections.singletonList(invokeDeeplinkMethod(Queues.template(), Endpoints.browse(Podcast.getUserInfoProvider().marketplaceId()), Deeplinks.podcast(podcastId, podcasTitle), onErrorAlertTemplate(resources), LatencyMetricAttributes$PageType.PODCAST_SHOW_DETAIL, PodcastDetailClientState.getRefinementClientStateName(podcastId), HIDE_PROMPT_PREFERENCES_CLIENT_STATE))).build()).withScreenMode(TemplateScreenMode.templateList).build());
    }

    public static List<Method> episodePlay(final String episodeId, final String podcastId, final String title, final String podcastTitle, final Resources resources) {
        return Arrays.asList(ClearMediaMethod.builder().withQueue(Queues.playback()).build(), CreateAndBindTemplateMethod.builder().withQueue(Queues.template()).withForced(Boolean.FALSE).withScreenMode(TemplateScreenMode.templateList).withTemplate(NowPlayingTemplate.builder().withLaunchMode("standard").withOnCreated(Collections.singletonList(invokeDeeplinkMethod(Queues.playback(), Endpoints.playback(Podcast.getUserInfoProvider().marketplaceId()), Deeplinks.episodePlay(podcastId, episodeId, podcastTitle, title), onErrorAlertTemplate(resources), new String[0]))).build()).build());
    }

    public static List<Method> episodePlay(final Bookmark bookmark, final Resources resources) {
        ClearMediaMethod clearMediaMethodBuild = ClearMediaMethod.builder().withQueue(Queues.playback()).build();
        String strMarketplaceId = Podcast.getUserInfoProvider().marketplaceId();
        String strEpisodePlay = Deeplinks.episodePlay(bookmark.getPodcastId(), bookmark.getId(), bookmark.getPodcastTitle(), bookmark.getTitle());
        InvokeDeeplinkMethod invokeDeeplinkMethod = invokeDeeplinkMethod(Queues.playback(), Endpoints.playback(strMarketplaceId), strEpisodePlay, onErrorAlertTemplate(resources), new String[0]);
        PlayLocalEpisodesMethod.Builder builderWithQueue = PlayLocalEpisodesMethod.builder().withQueue(Queues.playback());
        Boolean bool = Boolean.FALSE;
        return Arrays.asList(clearMediaMethodBuild, CreateAndBindTemplateMethod.builder().withQueue(Queues.template()).withForced(bool).withScreenMode(TemplateScreenMode.templateList).withTemplate(NowPlayingTemplate.builder().withLaunchMode("standard").withOnCreated(Collections.singletonList(builderWithQueue.withForced(bool).withEpisodeId(bookmark.getId()).withOnLocalEpisodeNotFound(Collections.singletonList(invokeDeeplinkMethod)).withOnLocalEpisodeFound(alreadyPlayingInvokeDeeplink(strEpisodePlay)).build())).build()).build());
    }

    public static List<Method> jumpBackInEpisodePlay(final String episodeId, final String playDeeplink, final Resources resources) {
        ClearMediaMethod clearMediaMethodBuild = ClearMediaMethod.builder().withQueue(Queues.playback()).build();
        InvokeDeeplinkMethod invokeDeeplinkMethod = invokeDeeplinkMethod(Queues.playback(), Endpoints.playback(Podcast.getUserInfoProvider().marketplaceId()), playDeeplink, onErrorAlertTemplate(resources), new String[0]);
        PlayLocalEpisodesMethod.Builder builderWithQueue = PlayLocalEpisodesMethod.builder().withQueue(Queues.playback());
        Boolean bool = Boolean.FALSE;
        return Arrays.asList(clearMediaMethodBuild, CreateAndBindTemplateMethod.builder().withQueue(Queues.template()).withForced(bool).withScreenMode(TemplateScreenMode.templateList).withTemplate(NowPlayingTemplate.builder().withLaunchMode("standard").withOnCreated(Collections.singletonList(builderWithQueue.withForced(bool).withEpisodeId(episodeId).withOnLocalEpisodeNotFound(Collections.singletonList(invokeDeeplinkMethod)).withOnLocalEpisodeFound(alreadyPlayingInvokeDeeplink(playDeeplink)).build())).build()).build());
    }

    public static List<Method> savedEpisodePlay(final String episodeId, final Resources resources) {
        ClearMediaMethod clearMediaMethodBuild = ClearMediaMethod.builder().withQueue(Queues.playback()).build();
        String strMarketplaceId = Podcast.getUserInfoProvider().marketplaceId();
        String strSavedEpisodePlay = Deeplinks.savedEpisodePlay(episodeId);
        InvokeDeeplinkMethod invokeDeeplinkMethod = invokeDeeplinkMethod(Queues.playback(), Endpoints.playback(strMarketplaceId), strSavedEpisodePlay, onErrorAlertTemplate(resources), new String[0]);
        PlayLocalEpisodesMethod.Builder builderWithQueue = PlayLocalEpisodesMethod.builder().withQueue(Queues.playback());
        Boolean bool = Boolean.FALSE;
        return Arrays.asList(clearMediaMethodBuild, CreateAndBindTemplateMethod.builder().withQueue(Queues.template()).withForced(bool).withScreenMode(TemplateScreenMode.templateList).withTemplate(NowPlayingTemplate.builder().withLaunchMode("standard").withOnCreated(Collections.singletonList(builderWithQueue.withForced(bool).withEpisodeId(episodeId).withOnLocalEpisodeNotFound(Collections.singletonList(invokeDeeplinkMethod)).withOnLocalEpisodeFound(alreadyPlayingInvokeDeeplink(strSavedEpisodePlay)).build())).build()).build());
    }

    public static List<Method> heartbeatFrequencies() {
        if (!PodcastFeatureGating.LOCAL_HEARTBEAT.isEnabled()) {
            return Collections.EMPTY_LIST;
        }
        return Collections.singletonList(invokeDeeplinkMethod(Queues.playback(), Endpoints.playback(Podcast.getUserInfoProvider().marketplaceId()), Deeplinks.heartbeatFrequencies(), Collections.emptyList(), new String[0]));
    }

    public static List<Method> latest(final Resources resources) {
        return Collections.singletonList(invokeDeeplinkMethod(Queues.latestSync(), Endpoints.library(Podcast.getUserInfoProvider().marketplaceId()), Deeplinks.latest(), Collections.emptyList(), LatencyMetricAttributes$PageType.PODCASTS_LIBRARY_LATEST, new String[0]));
    }

    public static List<Method> shows() {
        if (Podcast.getUserInfoProvider() == null || Podcast.getUserInfoProvider().marketplaceId() == null) {
            return Collections.EMPTY_LIST;
        }
        return Collections.singletonList(invokeDeeplinkMethod(Queues.followSync(), Endpoints.library(Podcast.getUserInfoProvider().marketplaceId()), Deeplinks.shows(), Collections.emptyList(), FollowsLastSyncTimeClientState.class.getCanonicalName()));
    }

    public static List<Method> showsFullSync() {
        if (Podcast.getUserInfoProvider() == null || Podcast.getUserInfoProvider().marketplaceId() == null) {
            return Collections.EMPTY_LIST;
        }
        return Collections.singletonList(invokeDeeplinkMethod(Queues.followSync(), Endpoints.library(Podcast.getUserInfoProvider().marketplaceId()), Deeplinks.shows(), Collections.emptyList(), new String[0]));
    }

    public static List<Method> completed() {
        if (Podcast.getUserInfoProvider() == null || Podcast.getUserInfoProvider().marketplaceId() == null) {
            return Collections.EMPTY_LIST;
        }
        return Collections.singletonList(invokeDeeplinkMethod(Queues.completedSync(), Endpoints.library(Podcast.getUserInfoProvider().marketplaceId()), Deeplinks.completed(), Collections.emptyList(), CompletedLastSyncTimeClientState.class.getCanonicalName()));
    }

    public static List<Method> alreadyPlayingInvokeDeeplink(final String playDeeplink) {
        return Collections.singletonList(invokeDeeplinkMethod(Queues.playback(), Endpoints.playback(Podcast.getUserInfoProvider().marketplaceId()), playDeeplink, Collections.EMPTY_LIST, LocalEpisodeAlreadyPlayingClientState.class.getCanonicalName()));
    }

    public static List<Method> incrementalSyncOnViewed() {
        ArrayList arrayList = new ArrayList();
        if (PodcastFeatureGating.ON_PAGE_VIEWED_INCREMENTAL_SYNC_WITH_OFFLINE_FIX.isEnabled()) {
            arrayList.addAll(bookmarks());
            arrayList.addAll(shows());
            arrayList.addAll(playlist());
            arrayList.addAll(completed());
        }
        return arrayList;
    }

    public static List<Method> bookmarks() {
        if (Podcast.getUserInfoProvider() == null || Podcast.getUserInfoProvider().marketplaceId() == null) {
            return Collections.EMPTY_LIST;
        }
        return Collections.singletonList(invokeDeeplinkMethod(Queues.bookmarkSync(), Endpoints.library(Podcast.getUserInfoProvider().marketplaceId()), Deeplinks.bookmarks(), Collections.emptyList(), BookmarksLastSyncTimeClientState.class.getCanonicalName()));
    }

    public static List<Method> playlist() {
        if (Podcast.getUserInfoProvider() == null || Podcast.getUserInfoProvider().marketplaceId() == null) {
            return Collections.EMPTY_LIST;
        }
        return Collections.singletonList(invokeDeeplinkMethod(Queues.saveSync(), Endpoints.library(Podcast.getUserInfoProvider().marketplaceId()), Deeplinks.playlist(), Collections.emptyList(), SavesLastSyncTimeClientState.class.getCanonicalName()));
    }

    public static List<Method> playlistFullSync() {
        if (Podcast.getUserInfoProvider() == null || Podcast.getUserInfoProvider().marketplaceId() == null) {
            return Collections.EMPTY_LIST;
        }
        return Collections.singletonList(invokeDeeplinkMethod(Queues.saveSync(), Endpoints.library(Podcast.getUserInfoProvider().marketplaceId()), Deeplinks.playlist(), Collections.emptyList(), new String[0]));
    }

    public static List<Method> download(final String episodeId) {
        String strMarketplaceId = Podcast.getUserInfoProvider().marketplaceId();
        DownloadRequestedMethod downloadRequestedMethod = new DownloadRequestedMethod(episodeId, Queues.downloadSync());
        DownloadRequestCompletedMethod downloadRequestCompletedMethod = new DownloadRequestCompletedMethod(episodeId, Queues.downloadSync());
        Endpoint endpointLibrary = Endpoints.library(strMarketplaceId);
        return Collections.singletonList(InvokeDeeplinkMethod.builder().withQueue(Queues.appSync()).withUrl(endpointLibrary.getUrl()).withDeeplinkUrl(Deeplinks.download(episodeId)).withTarget(endpointLibrary.getTarget()).withExtra(new HashMap()).withPreset("{}").withBefore(Collections.singletonList(downloadRequestedMethod)).withAfter(Collections.singletonList(downloadRequestCompletedMethod)).build());
    }

    public static List<Method> jumpBackIn(final Resources resources) {
        return Collections.singletonList(invokeDeeplinkMethod(Queues.jumpBackIn(), Endpoints.browse(Podcast.getUserInfoProvider().marketplaceId()), Deeplinks.jumpBackIn(), Collections.emptyList(), new String[0]));
    }

    public static List<Method> ptc(final Resources resources) {
        InvokeDeeplinkMethod invokeDeeplinkMethod = invokeDeeplinkMethod(Queues.template(), Endpoints.browse(Podcast.getUserInfoProvider().marketplaceId()), Deeplinks.ptc(), onErrorAlertTemplate(resources), new String[0]);
        return Collections.singletonList(CreateTemplateMethod.builder().withQueue(Queues.template()).withTemplate(PTCTemplate.builder().withShards(PTCTemplateShardsElement.builder().withItems(new ArrayList()).build()).withLaunchMode(TemplateLaunchMode.singleUse).withOnCreated(Collections.singletonList(invokeDeeplinkMethod)).build()).withScreenMode("standard").build());
    }

    public static List<Method> bites(final Resources resources, final boolean includeFollowShow) {
        return Collections.singletonList(CreateTemplateMethod.builder().withQueue(Queues.template()).withTemplate(CardNowPlayingTemplate.builder().withLaunchMode(TemplateLaunchMode.singleUse).withOnCreated(Collections.singletonList(invokeDeeplinkMethod(Queues.template(), Endpoints.browse(Podcast.getUserInfoProvider().marketplaceId()), Deeplinks.bites(includeFollowShow), onErrorAlertTemplate(resources), new String[0]))).build()).withScreenMode("standard").build());
    }

    public static InvokeDeeplinkMethod invokeDeeplinkMethod(final Queue queue, final Endpoint endpoint, final String deeplinkUrl, final List<Method> onError, final String... clientStates) {
        return invokeDeeplinkMethod(queue, endpoint, deeplinkUrl, onError, null, clientStates);
    }

    public static InvokeDeeplinkMethod invokeDeeplinkMethod(final Queue queue, final Endpoint endpoint, final String deeplinkUrl, final List<Method> onError, final LatencyMetricAttributes$PageType latencyPageType, final String... clientStates) {
        return InvokeDeeplinkMethod.builder().withQueue(queue).withClientStates(Arrays.asList(clientStates)).withUrl(endpoint.getUrl()).withDeeplinkUrl(deeplinkUrl).withTarget(endpoint.getTarget()).withExtra(new HashMap()).withPreset("{}").withOnError(onError).withLatencyMetricElement(latencyPageType != null ? LatencyMetricElement.builder().withPageType(latencyPageType.toString()).build() : null).build();
    }

    public static final List<Method> onErrorAlertTemplate(final Resources resources) {
        return Arrays.asList(CreateAndBindTemplateMethod.builder().withForced(Boolean.FALSE).withQueue(Queues.template()).withTemplate(AlertTemplate.builder().withTitle(resources.getString(R$string.podcast_no_connection_title)).withSubtitle(resources.getString(R$string.podcast_no_connection_desc_generic)).withPositiveButton(PositiveButtonElement.builder().withOnItemSelected(Collections.emptyList()).withText(resources.getString(R$string.podcast_alert_ok)).build()).withLaunchMode(TemplateLaunchMode.singleUse).build()).withScreenMode("standard").build(), RemoveTemplateMethod.builder().withQueue(Queues.template()).build());
    }

    public static final List<Method> syncEpisodeMetadata(final String episodeId) {
        if (StringUtils.isBlank(episodeId)) {
            return Collections.EMPTY_LIST;
        }
        return Collections.singletonList(invokeDeeplinkMethod(Queues.appSync(), Endpoints.browse(Podcast.getUserInfoProvider().marketplaceId()), Deeplinks.syncEpisodeMetadataDeeplink(episodeId), Collections.EMPTY_LIST, new String[0]));
    }

    public static final List<Method> syncPodcastMetadata(final String podcastId) {
        if (StringUtils.isBlank(podcastId)) {
            return Collections.EMPTY_LIST;
        }
        return Collections.singletonList(invokeDeeplinkMethod(Queues.appSync(), Endpoints.browse(Podcast.getUserInfoProvider().marketplaceId()), Deeplinks.syncPodcastMetadataDeeplink(podcastId), Collections.EMPTY_LIST, new String[0]));
    }
}
