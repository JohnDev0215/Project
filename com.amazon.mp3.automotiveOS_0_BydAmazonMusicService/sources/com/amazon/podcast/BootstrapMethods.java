package com.amazon.podcast;

import Podcast.DeeplinkInterface.v1_0.DeeplinkClientState;
import Podcast.FollowInterface.v1_0.FollowsTemplate;
import Podcast.FollowInterface.v1_0.FooterElement;
import Podcast.FollowPromptInterface.v1_0.HidePromptPreferenceClientState;
import Podcast.JumpBackInInterface.v1_0.JumpBackInTemplate;
import Podcast.LatencyMetricsInterface.v1_0.LatencyMetricElement;
import Podcast.PlaybackInterface.v1_0.ClearMediaMethod;
import Podcast.PlaybackInterface.v1_0.PlayLocalEpisodesMethod;
import Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.CardNowPlayingTemplate;
import Podcast.Touch.ChromeTemplateInterface.v1_0.ChromeTemplate;
import Podcast.Touch.ChromeTemplateInterface.v1_0.ChromeTemplateShardElement;
import Podcast.Touch.DefaultTemplateInterface.v1_0.DefaultTemplate;
import Podcast.Touch.NowPlayingTemplateInterface.v1_0.NowPlayingTemplate;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCPreferencesClientState;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCTemplate;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCTemplateShardsElement;
import SOACoreInterface.v1_0.Method;
import SOAInteractionInterface.v1_0.InvokeRemoteSkillMethod;
import SOATemplateListInterface.v1_0.CreateAndBindTemplateMethod;
import SOATemplateListInterface.v1_0.CreateTemplateMethod;
import SOATemplateListInterface.v1_0.Shard;
import SOATemplateListInterface.v1_0.Template;
import SOATemplateListInterface.v1_0.TemplateLaunchMode;
import SOATemplateListInterface.v1_0.TemplateScreenMode;
import android.content.Context;
import android.content.res.Resources;
import com.amazon.mp3.environment.Environment;
import com.amazon.podcast.deeplinks.DeeplinkMethods;
import com.amazon.podcast.deeplinks.Deeplinks;
import com.amazon.podcast.deeplinks.PodcastDeeplink;
import com.amazon.podcast.endpoints.Endpoint;
import com.amazon.podcast.endpoints.Endpoints;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import com.amazon.podcast.metrics.UiMetricAttributes$ActionType;
import com.amazon.podcast.metrics.UiMetricAttributes$ContainerType;
import com.amazon.podcast.metrics.UiMetricAttributes$ContentName;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.metrics.UiMetricMethods;
import com.amazon.podcast.metrics.latency.LatencyMetricAttributes$PageType;
import com.amazon.podcast.metrics.latency.LatencyMetricInterface;
import com.amazon.podcast.redhoodPlus.RedhoodPlusSyncPreferences;
import com.amazon.podcast.skills.PlayDownloadedEpisodesSkill;
import com.amazon.podcast.views.downloadsTemplate.DownloadsTemplates;
import com.amazon.podcast.views.latestTemplate.LatestTemplateV2Methods;
import com.amazon.podcast.views.podcastDetailTemplate.PodcastDetailClientState;
import com.amazon.podcast.views.savesTemplate.SavesTemplates;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public final class BootstrapMethods {
    public static List<Method> onBrowseStarted(final Context context, final String marketplaceId, final Resources resources) {
        List<Method> listShows;
        List<Method> listPlaylist;
        ArrayList arrayList = new ArrayList();
        arrayList.add(DeeplinkClientState.class.getCanonicalName());
        String deeplink = getDeeplink();
        String podcastIdFromDeeplink = Deeplinks.getPodcastIdFromDeeplink(deeplink);
        String refinementClientStateName = PodcastDetailClientState.getRefinementClientStateName(podcastIdFromDeeplink);
        if (StringUtils.isNotBlank(refinementClientStateName)) {
            arrayList.add(refinementClientStateName);
        }
        if (StringUtils.isNotBlank(podcastIdFromDeeplink)) {
            arrayList.add(HidePromptPreferenceClientState.class.getCanonicalName());
        }
        arrayList.add(PTCPreferencesClientState.class.getCanonicalName());
        LatencyMetricAttributes$PageType latencyMetricAttributes$PageTypeLatencyPageTypeFromDeeplink = LatencyMetricInterface.latencyPageTypeFromDeeplink(deeplink);
        if (latencyMetricAttributes$PageTypeLatencyPageTypeFromDeeplink == null) {
            latencyMetricAttributes$PageTypeLatencyPageTypeFromDeeplink = LatencyMetricAttributes$PageType.PODCASTS_HOME;
        }
        List<Method> listBuildCreateTemplateMethods = buildCreateTemplateMethods(deeplink, marketplaceId, arrayList, resources, latencyMetricAttributes$PageTypeLatencyPageTypeFromDeeplink);
        ArrayList arrayList2 = new ArrayList();
        boolean zShouldTriggerFullSync = RedhoodPlusSyncPreferences.shouldTriggerFullSync(context);
        if (zShouldTriggerFullSync) {
            listShows = DeeplinkMethods.showsFullSync();
        } else {
            listShows = DeeplinkMethods.shows();
        }
        arrayList2.addAll(listShows);
        if (zShouldTriggerFullSync) {
            listPlaylist = DeeplinkMethods.playlistFullSync();
        } else {
            listPlaylist = DeeplinkMethods.playlist();
        }
        arrayList2.addAll(listPlaylist);
        arrayList2.addAll(DeeplinkMethods.bookmarks());
        arrayList2.addAll(DeeplinkMethods.completed());
        arrayList2.addAll(DeeplinkMethods.jumpBackIn(resources));
        arrayList2.addAll(listBuildCreateTemplateMethods);
        return arrayList2;
    }

    private static List<Method> buildCreateTemplateMethods(final String deeplink, final String marketplaceId, final List<String> clientStates, final Resources resources, final LatencyMetricAttributes$PageType latencyPageType) {
        Template templateBuild;
        Endpoint endpointBrowse = Endpoints.browse(marketplaceId);
        InvokeRemoteSkillMethod invokeRemoteSkillMethodBuild = InvokeRemoteSkillMethod.builder().withQueue(Queues.playback()).withUrl(endpointBrowse.getUrl()).withTarget(endpointBrowse.getTarget()).withExtra(new HashMap()).withPreset("{}").withOnError(DeeplinkMethods.onErrorAlertTemplate(resources)).withClientStates(clientStates).withLatencyMetricElement(LatencyMetricElement.builder().withPageType(latencyPageType != null ? latencyPageType.toString() : null).build()).build();
        if (deeplink.contains("/podcasts/tastes")) {
            templateBuild = PTCTemplate.builder().withShards(PTCTemplateShardsElement.builder().withItems(new ArrayList()).build()).withLaunchMode(TemplateLaunchMode.singleUse).withOnCreated(Collections.singletonList(invokeRemoteSkillMethodBuild)).build();
        } else if (Deeplinks.isPreviewsDeeplink(deeplink)) {
            templateBuild = CardNowPlayingTemplate.builder().withLaunchMode(TemplateLaunchMode.singleUse).withOnCreated(Collections.singletonList(invokeRemoteSkillMethodBuild)).build();
        } else {
            templateBuild = DefaultTemplate.builder().withLaunchMode("standard").withOnCreated(Collections.singletonList(invokeRemoteSkillMethodBuild)).build();
        }
        return Collections.singletonList(CreateTemplateMethod.builder().withQueue(Queues.template()).withTemplate(templateBuild).withScreenMode(TemplateScreenMode.templateList).build());
    }

    public static List<Method> onLibraryStarted(final Context context, final String deeplinkPath, final String source) {
        List<Method> listShows;
        List<Method> listPlaylist;
        if (isSeeMore(deeplinkPath)) {
            return onSeeMore(context, deeplinkPath);
        }
        if (isDownloadedPlay(deeplinkPath)) {
            String episodeIdFromDeeplink = Deeplinks.getEpisodeIdFromDeeplink(deeplinkPath);
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(PlayDownloadedEpisodesSkill.prepareInvoke(episodeIdFromDeeplink, !(source != null && source.equals(PodcastDeeplink.Source.CARMODE.name()))));
            arrayList.addAll(UiMetricMethods.onClicked(episodeIdFromDeeplink, UiMetricAttributes$PageType.PODCASTS_LIBRARY_DOWNLOADS, UiMetricAttributes$ActionType.PLAY_PAUSE_PODCAST_EPISODE, UiMetricAttributes$ContentName.PODCASTS_LIBRARY_DOWNLOADS));
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        Resources resources = context.getResources();
        arrayList2.add(LatestTemplateV2Methods.bootstrapLatestShard(resources));
        ArrayList arrayList3 = new ArrayList();
        UiMetricAttributes$PageType uiMetricAttributes$PageType = UiMetricAttributes$PageType.PODCASTS_LIBRARY_SHOWS;
        arrayList3.addAll(UiMetricMethods.onPageViewed(uiMetricAttributes$PageType));
        arrayList3.addAll(UiMetricMethods.onContentViewed(uiMetricAttributes$PageType, UiMetricAttributes$ContainerType.GALLERY, UiMetricAttributes$ContentName.PODCASTS_LIBRARY_SHOWS));
        arrayList3.addAll(DeeplinkMethods.incrementalSyncOnViewed());
        ChromeTemplateShardElement chromeTemplateShardElementBuild = ChromeTemplateShardElement.builder().withHeader(context.getResources().getString(R$string.podcast_follow_tab_label)).withShard(Shard.builder().withCreateAndBind(FollowsTemplate.builder().withLaunchMode("standard").withOnBound(Collections.emptyList()).withOnCreated(Collections.emptyList()).withOnViewed((List<Method>) arrayList3).withFooter(followFooter(context)).build()).build()).build();
        ChromeTemplateShardElement chromeTemplateShardElementBuild2 = ChromeTemplateShardElement.builder().withHeader(context.getResources().getString(R$string.podcast_download_tab_label)).withShard(Shard.builder().withCreateAndBind(DownloadsTemplates.getTemplate(resources)).build()).build();
        ChromeTemplateShardElement chromeTemplateShardElementBuild3 = ChromeTemplateShardElement.builder().withHeader(context.getResources().getString(R$string.podcast_save_tab_label)).withShard(Shard.builder().withCreateAndBind(SavesTemplates.getTemplateForLibrary(resources)).build()).build();
        arrayList2.add(chromeTemplateShardElementBuild);
        arrayList2.add(chromeTemplateShardElementBuild2);
        arrayList2.add(chromeTemplateShardElementBuild3);
        CreateAndBindTemplateMethod createAndBindTemplateMethodBuild = CreateAndBindTemplateMethod.builder().withQueue(Queues.playback()).withTemplate(ChromeTemplate.builder().withSelectedShardPosition(getSelectedShardPosition(context, deeplinkPath)).withItems(arrayList2).withLaunchMode("standard").withOnCreated(Collections.emptyList()).build()).withScreenMode(TemplateScreenMode.templateList).build();
        ArrayList arrayList4 = new ArrayList();
        boolean zShouldTriggerFullSync = RedhoodPlusSyncPreferences.shouldTriggerFullSync(context);
        if (zShouldTriggerFullSync) {
            listShows = DeeplinkMethods.showsFullSync();
        } else {
            listShows = DeeplinkMethods.shows();
        }
        arrayList4.addAll(listShows);
        if (zShouldTriggerFullSync) {
            listPlaylist = DeeplinkMethods.playlistFullSync();
        } else {
            listPlaylist = DeeplinkMethods.playlist();
        }
        arrayList4.addAll(listPlaylist);
        arrayList4.addAll(DeeplinkMethods.jumpBackIn(resources));
        arrayList4.addAll(DeeplinkMethods.heartbeatFrequencies());
        arrayList4.add(createAndBindTemplateMethodBuild);
        return arrayList4;
    }

    public static final List<Method> onPlaybackStarted(final Context context, final String marketplaceId, final String deeplinkPath, final String source) {
        ClearMediaMethod clearMediaMethodBuild = ClearMediaMethod.builder().withQueue(Queues.playback()).build();
        List<String> listSingletonList = Collections.singletonList(DeeplinkClientState.class.getCanonicalName());
        Endpoint endpointPlayback = Endpoints.playback(marketplaceId);
        InvokeRemoteSkillMethod invokeRemoteSkillMethodBuild = InvokeRemoteSkillMethod.builder().withQueue(Queues.playback()).withUrl(endpointPlayback.getUrl()).withTarget(endpointPlayback.getTarget()).withExtra(new HashMap()).withPreset("{}").withOnError(DeeplinkMethods.onErrorAlertTemplate(context.getResources())).withClientStates(listSingletonList).build();
        String episodeIdFromDeeplink = Deeplinks.getEpisodeIdFromDeeplink(deeplinkPath);
        PlayLocalEpisodesMethod.Builder builderWithQueue = PlayLocalEpisodesMethod.builder().withQueue(Queues.playback());
        Boolean bool = Boolean.FALSE;
        PlayLocalEpisodesMethod playLocalEpisodesMethodBuild = builderWithQueue.withForced(bool).withEpisodeId(episodeIdFromDeeplink).withOnLocalEpisodeNotFound(Collections.singletonList(invokeRemoteSkillMethodBuild)).withOnLocalEpisodeFound(DeeplinkMethods.alreadyPlayingInvokeDeeplink(deeplinkPath)).build();
        ArrayList arrayList = new ArrayList();
        arrayList.add(clearMediaMethodBuild);
        if (isFeaturedPlayDeeplink(deeplinkPath) || source.equals(PodcastDeeplink.Source.CARMODE.name())) {
            arrayList.add(playLocalEpisodesMethodBuild);
            return arrayList;
        }
        arrayList.add(CreateAndBindTemplateMethod.builder().withQueue(Queues.template()).withForced(bool).withScreenMode(TemplateScreenMode.templateList).withTemplate(NowPlayingTemplate.builder().withOnCreated(Collections.singletonList(playLocalEpisodesMethodBuild)).build()).build());
        return arrayList;
    }

    private static FooterElement followFooter(final Context context) {
        String strMarketplaceId = Podcast.getUserInfoProvider().marketplaceId();
        if (PodcastFeatureGating.TASTE_COLLECTION.isEnabled()) {
            String string = context.getResources().getString(R$string.podcast_ptc_lets_get_started);
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(DeeplinkMethods.ptc(context.getResources()));
            arrayList.addAll(UiMetricMethods.onClicked(UiMetricAttributes$PageType.PODCASTS_LIBRARY_SHOWS, UiMetricAttributes$ActionType.GO_PODCASTS_TASTE, UiMetricAttributes$ContentName.PODCASTS_LIBRARY_SHOWS));
            return FooterElement.builder().withText(string).withOnItemSelected(arrayList).build();
        }
        String string2 = context.getResources().getString(R$string.podcast_explore_more);
        List<Method> listOnBrowseStarted = onBrowseStarted(context, strMarketplaceId, context.getResources());
        listOnBrowseStarted.addAll(UiMetricMethods.onClicked(UiMetricAttributes$PageType.PODCASTS_LIBRARY_SHOWS, UiMetricAttributes$ActionType.GO_PODCASTS_HOME, UiMetricAttributes$ContentName.PODCASTS_LIBRARY_SHOWS));
        return FooterElement.builder().withText(string2).withOnItemSelected(listOnBrowseStarted).build();
    }

    private static int getSelectedShardPosition(final Context context, final String deeplinkPath) {
        if (deeplinkPath.contains("latest")) {
            return 0;
        }
        if (deeplinkPath.contains("shows")) {
            return 1;
        }
        if (deeplinkPath.contains(Environment.PLAYLIST_PATH)) {
            return 3;
        }
        return UserPreferences.get(context).readLastVisitedLibraryTab();
    }

    private static boolean isSeeMore(final String deeplinkPath) {
        return deeplinkPath.contains("seeMore=true");
    }

    private static List<Method> onSeeMore(final Context context, final String deeplinkPath) {
        if (deeplinkPath.contains("shows")) {
            return Collections.singletonList(CreateAndBindTemplateMethod.builder().withQueue(Queues.template()).withScreenMode("standard").withTemplate(FollowsTemplate.builder().withLaunchMode("standard").withOnBound(Collections.emptyList()).withOnCreated(Collections.emptyList()).withOnViewed(UiMetricMethods.onPageViewed(UiMetricAttributes$PageType.PODCASTS_FOLLOWED_SHOWS)).withFooter(followFooter(context)).withHeader(context.getResources().getString(R$string.podcast_followed_shows_header)).build()).build());
        }
        if (deeplinkPath.contains(Environment.PLAYLIST_PATH)) {
            return Collections.singletonList(CreateAndBindTemplateMethod.builder().withQueue(Queues.template()).withScreenMode("standard").withTemplate(SavesTemplates.getTemplateForSeeMore(context)).build());
        }
        if (!deeplinkPath.contains("jumpBackIn")) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(UiMetricMethods.onPageViewed(UiMetricAttributes$PageType.PODCASTS_JUMP_BACK_IN_EPISODES));
        return Collections.singletonList(CreateAndBindTemplateMethod.builder().withQueue(Queues.jumpBackIn()).withScreenMode("standard").withTemplate(JumpBackInTemplate.builder().withLaunchMode("standard").withOnBound(Collections.emptyList()).withOnCreated(Collections.emptyList()).withOnViewed((List<Method>) arrayList).withHeader(context.getResources().getString(R$string.podcast_jump_back_in)).build()).build());
    }

    private static boolean isFeaturedPlayDeeplink(final String url) {
        return url != null && url.contains("do=featuredPlay");
    }

    private static String getDeeplink() {
        ClientStates clientState = Podcast.getClientState();
        return (clientState == null || clientState.getDeeplinkInfo() == null) ? "" : clientState.getDeeplinkInfo().getUrl();
    }

    private static boolean isDownloadedPlay(final String url) {
        return url.contains("/downloads/") && url.contains("do=play");
    }
}
