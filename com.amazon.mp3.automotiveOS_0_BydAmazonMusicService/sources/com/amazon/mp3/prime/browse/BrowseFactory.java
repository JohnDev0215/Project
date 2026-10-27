package com.amazon.mp3.prime.browse;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import androidx.annotation.Nullable;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.brush.RxUiPage;
import com.amazon.mp3.brush.RxUiV3Page;
import com.amazon.mp3.config.ApplicationAttributes;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.environment.MusicURL;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.net.RequestInterceptorFactory;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.alps.gating.AlpsFeatureGating;
import com.amazon.music.browse.AlbumLookup;
import com.amazon.music.browse.Browse;
import com.amazon.music.browse.BrowseContentSelector;
import com.amazon.music.browse.BrowseException;
import com.amazon.music.browse.BrowseService;
import com.amazon.music.browse.Configuration;
import com.amazon.music.browse.DefaultBrowseService;
import com.amazon.music.browse.RxBrowse;
import com.amazon.music.browse.TrackLookup;
import com.amazon.music.browse.page.PageRequest;
import com.amazon.music.browse.page.UiPage;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.page.api.model.AutomotiveServicePageRequest;
import com.amazon.music.page.api.model.BasePageRequest;
import com.amazon.music.page.api.model.PageRequest;
import com.amazon.music.page.api.model.Refinements;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/* JADX INFO: loaded from: classes4.dex */
public final class BrowseFactory {
    public static RxBrowse createRxBrowse(final Context context) {
        BrowseService browseServiceCreateBrowseService = createBrowseService(context.getApplicationContext());
        AccountDetailUtil.get(context.getApplicationContext());
        return new RxBrowse(new Browse(browseServiceCreateBrowseService, AccountDetailUtil.getMusicTerritoryOfResidence()));
    }

    public static boolean isTabletDevice() {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        return Math.sqrt(Math.pow((double) (((float) displayMetrics.widthPixels) / displayMetrics.xdpi), 2.0d) + Math.pow((double) (((float) displayMetrics.heightPixels) / displayMetrics.ydpi), 2.0d)) >= 7.0d;
    }

    public static RxUiPage createRxUiPage(final Context context, final boolean withResponseLatency, final boolean withProcessingLatency) {
        return new RxUiPage(new UiPage(createBrowseServiceForBrush(context.getApplicationContext())), withResponseLatency, withProcessingLatency);
    }

    public static UiPage createUiPage(final Context context) {
        return new UiPage(createBrowseServiceForBrush(context.getApplicationContext()));
    }

    public static BrowseService createBrowseService(final Context context) {
        URL museUrl = MusicURL.getMuseUrl();
        URL museUrl2 = MusicURL.getMuseUrl();
        return new DefaultBrowseService(new Configuration.Builder().withRequestInterceptor(new RequestInterceptorFactory(context.getApplicationContext()).provideRequestInterceptor()).withTrackLookupURL(museUrl).withGetHomeURL(museUrl2).withMuseURL(museUrl2).build());
    }

    private static BrowseService createBrowseServiceForBrush(final Context context) {
        URL museBrushUrl = MusicURL.getMuseBrushUrl(SettingsUtil.isCustomEndpointEnabled(context));
        return new DefaultBrowseService(new Configuration.Builder().withRequestInterceptor(new RequestInterceptorFactory(context.getApplicationContext()).provideRequestInterceptor()).withTrackLookupURL(museBrushUrl).withGetHomeURL(museBrushUrl).withMuseURL(museBrushUrl).build());
    }

    @Deprecated
    public static TrackLookup.Response lookupTrack(final Context context, final String trackAsin) throws BrowseException {
        return lookupTrack(context, trackAsin, true);
    }

    @Deprecated
    public static TrackLookup.Response lookupTrack(final Context context, final String trackAsin, final boolean withFeatures) throws BrowseException {
        return new TrackLookup(createBrowseService(context)).get(new TrackLookup.Request(trackAsin, AccountDetailUtil.getMusicTerritoryOfResidence(), new BrowseContentSelectionProvider().getBrowseContentSelectionWithOwnedContent(context, true), AccountCredentialStorage.get(context).getDeviceId(), AccountCredentialStorage.get(context).getDeviceType(), new BrowseLanguageProvider().getLocale(context), AmazonApplication.getCapabilities().isVideosEnabled(), AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled(), ChildUserUtil.INSTANCE.getMusicRequestIdentityContextToken(context)), withFeatures);
    }

    public static AlbumLookup.Response lookupAlbum(final Context context, final String albumAsin) throws BrowseException {
        BrowseContentSelector browseContentSelectionWithOwnedContent = new BrowseContentSelectionProvider().getBrowseContentSelectionWithOwnedContent(context, true);
        return new AlbumLookup(createBrowseService(context)).get(new AlbumLookup.Request(albumAsin, AccountDetailUtil.getMusicTerritoryOfResidence(), browseContentSelectionWithOwnedContent, AccountCredentialStorage.get(context).getDeviceId(), AccountCredentialStorage.get(context).getDeviceType(), new BrowseLanguageProvider().getLocale(context), AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled(), ChildUserUtil.INSTANCE.getMusicRequestIdentityContextToken(context)));
    }

    public static PageRequest createPageRequest(final Context context, String uri, @Nullable String nextPageToken, boolean isArtistUpsell) {
        return populatePageRequestBuilder(context, uri, nextPageToken, isArtistUpsell, isTabletDevice()).build();
    }

    public static PageRequest createPageRequestWithBrowseId(final Context context, String uri, @Nullable String nextPageToken, @Nullable String browseId) {
        return populatePageRequestBuilder(context, uri, nextPageToken, false, isTabletDevice()).withBrowseId(browseId).build();
    }

    private static PageRequest.Builder populatePageRequestBuilder(final Context context, String uri, @Nullable String nextPageToken, boolean isArtistUpsell, boolean isTabletDevice) {
        boolean z;
        AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(context);
        String deviceId = accountCredentialStorage.getDeviceId();
        String deviceType = accountCredentialStorage.getDeviceType();
        Locale locale = new BrowseLanguageProvider().getLocale(context);
        BrowseContentSelector browseContentSelection = new BrowseContentSelectionProvider().getBrowseContentSelection(context);
        AccountDetailUtil.get(context);
        String musicTerritoryOfResidence = AccountDetailUtil.getMusicTerritoryOfResidence();
        String upsellContent = getUpsellContent(context, browseContentSelection);
        boolean z2 = uri.equalsIgnoreCase("home") && AmazonApplication.getCapabilities().isFacetedNavigationEnabled();
        boolean z3 = uri.equalsIgnoreCase("home") && AmazonApplication.getCapabilities().shouldPinPodcastsInFacetedNavigation();
        boolean z4 = uri.equalsIgnoreCase("home") && AmazonApplication.getCapabilities().isAmpShowsEnabled();
        boolean z5 = Feature.feature_play_v2_browse_home.isEnabled() && AlpsFeatureGating.LIVE_EVENT_TOP_ONE_RECOMMENDER.isEnabled();
        boolean zIsEnabled = Feature.artist_commentary.isEnabled();
        PageRequest.Builder builderIsStub = new PageRequest.Builder(browseContentSelection, deviceId, deviceType, musicTerritoryOfResidence, uri).withLocale(locale).withRecentlyPlayed(uri.equalsIgnoreCase("home") || uri.equalsIgnoreCase("home-music")).withVerticalItemBarkers(uri.equalsIgnoreCase("home") || uri.equalsIgnoreCase("home-music")).withVideo(AmazonApplication.getCapabilities().isVideosEnabled()).withVideoStory(AmazonApplication.getCapabilities().isVideoStoriesEnabled() && !isTabletDevice).withNextToken(TextUtils.isEmpty(nextPageToken) ? null : nextPageToken).withArtistUpsellWidget(isArtistUpsell).withCount(5).withUpsellContent(upsellContent).withMerch(AlpsFeatureGating.MERCH_ON_BROWSE_HOME.isEnabled()).setIsExplicitFilterEnabled(AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled()).withFollowSuggestedArtistsWidget(AmazonApplication.getCapabilities().isFollowArtistsWidgetEnabled()).withDeepLinkURLInWidget(true).withPodcastExploreBitesContent(uri.equalsIgnoreCase("home")).withFacetedNavigation(z2).withPinPodcastsInFacetedNavigation(z3).withAmpShows(z4).withLiveEvent(z5).withIncludeCommentary(zIsEnabled).withIncludeCommentaryFlag(uri.equalsIgnoreCase("home") && zIsEnabled).isStub(false);
        ChildUserUtil childUserUtil = ChildUserUtil.INSTANCE;
        if (childUserUtil.isChildUser(context)) {
            builderIsStub.withMusicRequestIdentityContextToken(childUserUtil.getMusicRequestIdentityContextToken(context));
        } else {
            if (uri.equalsIgnoreCase("home-music") || uri.contains("facet")) {
                z = true;
                builderIsStub.withFeaturedPlay(true, false, Feature.t1r_cloud_queue.isEnabled());
            } else {
                builderIsStub.withFeaturedPlay(uri.equalsIgnoreCase("home") || uri.contains("facet"), PodcastFeatureGating.FEATURE_PLAY.isEnabled(), Feature.t1r_cloud_queue.isEnabled()).withPodcastContent(PodcastFeatureGating.PODCAST.isEnabled()).withPodcastCuratedContent(PodcastFeatureGating.CURATED_CONTENT.isEnabled()).withPodcastUserContent(PodcastFeatureGating.USER_CONTENT.isEnabled()).withPodcastEpisodeDescriptiveShoveler().withPodcastSonicRush();
                z = true;
            }
            builderIsStub.withVideo(AmazonApplication.getCapabilities().isVideosEnabled());
            if (AmazonApplication.getCapabilities().isWoodstockEnabled()) {
                builderIsStub.withLiveStreamContent(z);
            }
        }
        return builderIsStub;
    }

    private static String getUpsellContent(final Context context, final BrowseContentSelector contentType) {
        if ((contentType == BrowseContentSelector.PRIME || contentType == BrowseContentSelector.FREE) && AccountDetailUtil.get(context).isUserInHawkfireMarketplace()) {
            return "MUSIC_SUBSCRIPTION";
        }
        return null;
    }

    public static RxUiV3Page createRxUiV3Page(Context context) {
        CompletableFuture<RxUiV3Page> completableFuture = new CompletableFuture<>();
        BrowsePageFactory.INSTANCE.createRxUiV3Page(completableFuture, context);
        try {
            return completableFuture.get();
        } catch (Exception e) {
            Log.error("Error loading page for MusicLandingPageApiGatewayClient {}", e.toString());
            return null;
        }
    }

    public static com.amazon.music.page.api.model.PageRequest createBrushV3PageRequest(final Context context, String uri, @Nullable String nextPageToken, Boolean isFireOS, Map<String, List<String>> refinementLists) {
        return (com.amazon.music.page.api.model.PageRequest) createBrushV3PageRequest(context, uri, nextPageToken, isFireOS, null, null, null, null, null, null, null, refinementLists);
    }

    public static AutomotiveServicePageRequest createAutomotiveBrushV3PageRequest(final Context context, String uri, String platform, @Nullable String presetIds, @Nullable String downloadedIds, @Nullable String recentlyPlayedIds, @Nullable String recentlyModifiedIds, @Nullable String widgetId, @Nullable String podcastShowId) {
        return (AutomotiveServicePageRequest) createBrushV3PageRequest(context, uri, null, null, platform, presetIds, downloadedIds, recentlyPlayedIds, recentlyModifiedIds, widgetId, podcastShowId, null);
    }

    private static BasePageRequest createBrushV3PageRequest(final Context context, String uri, String nextPageToken, Boolean isFireOS, String platform, String presetIds, String downloadedIds, String recentlyPlayedIds, String recentlyModifiedIds, String widgetId, String podcastShowId, Map<String, List<String>> refinementLists) {
        boolean z;
        boolean z2;
        AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(context);
        String deviceId = accountCredentialStorage.getDeviceId();
        String deviceType = accountCredentialStorage.getDeviceType();
        Locale locale = new BrowseLanguageProvider().getLocale(context);
        BrowseContentSelector browseContentSelection = new BrowseContentSelectionProvider().getBrowseContentSelection(context);
        AccountDetailUtil accountDetailUtil = AccountDetailUtil.get(context);
        String musicTerritoryOfResidence = AccountDetailUtil.getMusicTerritoryOfResidence();
        String customerId = accountDetailUtil.getCustomerId();
        boolean zIsExplicitLanguageFilterEnabled = AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled();
        Refinements refinements = new Refinements();
        refinements.setRefinementLists(refinementLists);
        if (uri.equals("carmode") || uri.equals("carmode-home") || uri.equals("carmode-detail") || uri.equals("carmode-podcast-episode-list")) {
            String homeMarketPlaceId = accountDetailUtil.getHomeMarketPlaceId();
            String region = accountDetailUtil.getHomeMarketPlace().getRegion();
            return ((AutomotiveServicePageRequest.Builder) ((AutomotiveServicePageRequest.Builder) ((AutomotiveServicePageRequest.Builder) ((AutomotiveServicePageRequest.Builder) ((AutomotiveServicePageRequest.Builder) new AutomotiveServicePageRequest.Builder(customerId, deviceId, deviceType, musicTerritoryOfResidence, browseContentSelection.value, uri).withLocale(locale)).withRegion(region)).withStage(Environment.AUTOMOTIVE.getCurrentOverrideId())).withPresetIds(presetIds).withDownloadedIds(downloadedIds).withRecentMusicIds(recentlyPlayedIds).withRecentlyCreatedLibraryPlaylistIds(recentlyModifiedIds).withWidgetId(widgetId)).withPodcastShowId(podcastShowId).withCarModeVisualShoveler(AmazonApplication.getCapabilities().isCarModeV2Enabled()).setIsExplicitLanguageFilterEnabled(zIsExplicitLanguageFilterEnabled)).withMarketPlaceId(homeMarketPlaceId).withAppVersion(ApplicationAttributes.getInstance(context).getVersion()).withOSVersion(Build.VERSION.RELEASE).withPlatform(platform).withDeviceModel(Build.MODEL).build();
        }
        boolean zIsVideoLandingPageEnabled = AmazonApplication.getCapabilities().isVideoLandingPageEnabled();
        boolean zIsWoodstockEnabled = AmazonApplication.getCapabilities().isWoodstockEnabled();
        boolean zIsEnabled = PodcastFeatureGating.PODCAST.isEnabled();
        boolean zIsSpatialAudioEnabled = AmazonApplication.getCapabilities().isSpatialAudioEnabled(context);
        ChildUserUtil childUserUtil = ChildUserUtil.INSTANCE;
        boolean zIsChildUser = childUserUtil.isChildUser(context);
        boolean zIsCategoryPostEnabled = AmazonApplication.getCapabilities().isCategoryPostEnabled();
        boolean zIsVideoStoriesEnabled = AmazonApplication.getCapabilities().isVideoStoriesEnabled();
        List<String> orDefault = refinementLists != null ? refinementLists.getOrDefault("GENRE_REFINEMENT", null) : null;
        boolean z3 = false;
        PageRequest.Builder builderWithRefinements = ((PageRequest.Builder) ((PageRequest.Builder) ((PageRequest.Builder) ((PageRequest.Builder) ((PageRequest.Builder) ((PageRequest.Builder) new PageRequest.Builder(customerId, deviceId, deviceType, musicTerritoryOfResidence, browseContentSelection.toString(), uri).withBrowseId(orDefault != null ? orDefault.get(0) : null)).withLocale(locale)).withNextToken(TextUtils.isEmpty(nextPageToken) ? null : nextPageToken)).withCount(10)).isStub(false).isCategoryPostEnabled(zIsCategoryPostEnabled).withRegion(accountDetailUtil.getHomeMarketPlace().getRegion())).setIsExplicitLanguageFilterEnabled(zIsExplicitLanguageFilterEnabled)).withSpatialAudio(zIsSpatialAudioEnabled).withUseBrushV3CategoryPages(true).withPodcastContentEnabled(true).withRefinements(refinements);
        if (zIsChildUser) {
            builderWithRefinements.setMusicRequestIdentityContextToken(childUserUtil.getMusicRequestIdentityContextToken(context));
        } else {
            if (zIsVideoLandingPageEnabled) {
                z = zIsVideoStoriesEnabled;
            } else {
                z = zIsVideoStoriesEnabled;
                if (!uri.contains(ParserUtil.GENRE_SEGMENT_NAME)) {
                    z2 = false;
                }
                PageRequest.Builder builderWithVideoStory = builderWithRefinements.withVideo(z2).withVideoStory(z);
                if (zIsWoodstockEnabled && !isFireOS.booleanValue()) {
                    z3 = true;
                }
                builderWithVideoStory.withLive(z3).withPodcast(zIsEnabled);
            }
            z2 = true;
            PageRequest.Builder builderWithVideoStory2 = builderWithRefinements.withVideo(z2).withVideoStory(z);
            if (zIsWoodstockEnabled) {
                z3 = true;
            }
            builderWithVideoStory2.withLive(z3).withPodcast(zIsEnabled);
        }
        if (SettingsUtil.isCustomEndpointEnabled(context)) {
            builderWithRefinements = (PageRequest.Builder) builderWithRefinements.withStage(Environment.MUSE_URL.getBrushV3Stage());
        }
        return builderWithRefinements.build();
    }
}
