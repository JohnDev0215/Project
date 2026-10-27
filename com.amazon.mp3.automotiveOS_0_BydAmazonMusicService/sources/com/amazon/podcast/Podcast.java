package com.amazon.podcast;

import Podcast.Touch.AlertTemplateInterface.v1_0.AlertTemplate;
import Podcast.Touch.AlertTemplateInterface.v1_0.PositiveButtonElement;
import SOAAppSyncInterface.v1_0.StartMethod;
import SOACoreInterface.v1_0.Method;
import SOATemplateListInterface.v1_0.CreateAndBindTemplateMethod;
import SOATemplateListInterface.v1_0.RemoveTemplateMethod;
import SOATemplateListInterface.v1_0.TemplateLaunchMode;
import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import com.amazon.music.platform.providers.AdvertisingPreferencesProvider;
import com.amazon.music.platform.providers.AuthenticationProvider;
import com.amazon.music.platform.providers.BuildInfoProvider;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.amazon.podcast.appsync.AppSync;
import com.amazon.podcast.bootstrap.Bootstrap;
import com.amazon.podcast.bootstrap.DownloadSettingsProvider;
import com.amazon.podcast.bootstrap.DynamicMessagingProvider;
import com.amazon.podcast.bootstrap.FragmentInfoProvider;
import com.amazon.podcast.bootstrap.NetworkSettingsProvider;
import com.amazon.podcast.bootstrap.SharingProvider;
import com.amazon.podcast.bootstrap.UserInfoProvider;
import com.amazon.podcast.caches.Caches;
import com.amazon.podcast.deeplinks.PodcastDeeplink;
import com.amazon.podcast.downloads.DownloadState;
import com.amazon.podcast.downloads.Downloads;
import com.amazon.podcast.downloads.Storage;
import com.amazon.podcast.external.car.CarModeUserPresetHandler;
import com.amazon.podcast.featureGates.FeatureGatePreferences;
import com.amazon.podcast.featureGates.FeatureGates;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import com.amazon.podcast.followPrompt.FollowPrompt;
import com.amazon.podcast.interaction.InteractionCore;
import com.amazon.podcast.media.playback.Playback;
import com.amazon.podcast.metrics.ui.UIContentViewCache.UIContentViewWidgetIndexCache;
import com.amazon.podcast.ptc.PodcastTasteCollection;
import com.amazon.podcast.skills.LocalSkillService;
import com.amazon.podcast.storage.PodcastRoomDatabase;
import com.amazon.podcast.views.Strings;
import com.amazon.podcast.views.downloadsTemplate.DownloadRefinementPreferences;
import com.amazon.podcast.views.podcastDetailTemplate.PodcastDetailClientState;
import com.amazon.podcast.views.savesTemplate.SaveRefinementPreferences;
import com.amazon.soa.core.Engine;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public final class Podcast {
    private static AdvertisingPreferencesProvider advertisingPreferencesProvider;
    private static AppSync appsync;
    private static AuthenticationProvider authenticationProvider;
    private static BuildInfoProvider buildInfoProvider;
    private static CarModeUserPresetHandler carModeUserPresetHandler;
    private static ClientStates clientStates;
    private static ConnectivityManager connectivityManager;
    private static PodcastRoomDatabase database;
    private static String defaultOwnerId;
    private static DeviceInfoProvider deviceInfoProvider;
    private static DownloadSettingsProvider downloadSettingsProvider;
    private static Downloads downloads;
    private static DynamicMessagingProvider dynamicMessagingProvider;
    private static final Engine engine;
    private static FeatureGatePreferences featureGatePreferences;
    private static FeatureGateProvider featureGateProvider;
    private static FeatureGates featureGates;
    private static boolean firstLaunch;
    private static FollowPrompt followPrompt;
    private static FragmentInfoProvider fragmentInfoProvider;
    private static String ingressSource;
    private static InteractionCore interactioncore;
    private static boolean isAlertsDisabled;
    private static boolean isHarleyEnabled;
    private static boolean isInitiated;
    private static Boolean isPodcastLaunched;
    private static Boolean isRestoringPlayback;
    private static LocalSkillService localSkillService;
    private static String musicTerritory;
    private static ConnectivityManager.NetworkCallback networkCallback;
    private static NetworkRequest networkRequest;
    private static NetworkSettingsProvider networkSettingsProvider;
    private static final String ownerId;
    private static final Playback playback;
    private static UUID playbackInstanceId;
    private static PodcastTasteCollection ptc;
    private static RuntimeStyleSheet runtimeStyleSheet;
    private static SharingProvider sharingProvider;
    private static final Templates templates;
    private static UserInfoProvider userInfoProvider;
    private final Context context;
    private final TemplateList templateList;
    private static Integer bookmarksUpperBoundPercentage = 95;
    private static Long bookmarksLowerBoundProgressMS = Long.valueOf(TimeUnit.SECONDS.toMillis(15));
    private static UIContentViewWidgetIndexCache contentViewCache = UIContentViewWidgetIndexCache.INSTANCE;

    static {
        String simpleName = Podcast.class.getSimpleName();
        ownerId = simpleName;
        Engine engine2 = new Engine();
        engine = engine2;
        engine2.registerOwner(simpleName);
        templates = new Templates(engine2);
        playback = new Playback(engine2);
        ptc = new PodcastTasteCollection(engine2);
        followPrompt = new FollowPrompt(engine2);
        featureGates = new FeatureGates(engine2);
        firstLaunch = false;
        isInitiated = false;
    }

    public static Podcast create(final Bootstrap bootstrap) {
        return new Podcast(bootstrap);
    }

    public static Engine getEngine() {
        return engine;
    }

    public static FragmentInfoProvider getFragmentInfoProvider() {
        return fragmentInfoProvider;
    }

    public static FeatureGatePreferences getFeatureGatePreferences() {
        return featureGatePreferences;
    }

    public static RuntimeStyleSheet getRuntimeStyleSheet() {
        return runtimeStyleSheet;
    }

    public static Playback getPlayback() {
        return playback;
    }

    public static Downloads getDownloads() {
        return downloads;
    }

    public static AppSync getAppSync() {
        return appsync;
    }

    public static Templates getTemplates() {
        return templates;
    }

    public static DownloadSettingsProvider getDownloadSettingsProvider() {
        return downloadSettingsProvider;
    }

    public static NetworkSettingsProvider getNetworkSettingsProvider() {
        return networkSettingsProvider;
    }

    public static AuthenticationProvider getAuthenticationProvider() {
        return authenticationProvider;
    }

    public static String getIngressSource() {
        return ingressSource;
    }

    public static String getPlaybackInstanceId() {
        return Strings.convertUUIDToString(playbackInstanceId);
    }

    public static void setPlaybackInstanceId(final UUID newPlaybackInstanceId) {
        playbackInstanceId = newPlaybackInstanceId;
    }

    public static void setDisableAlerts(final boolean disableAlerts) {
        isAlertsDisabled = disableAlerts;
    }

    public static boolean isAlertsDisabled() {
        return isAlertsDisabled;
    }

    public static void clearAppState(final Context context) {
        Handlers.INSTANCE.getBackground().post(new Runnable() { // from class: com.amazon.podcast.Podcast.1
            @Override // java.lang.Runnable
            public void run() {
                AppSync.clearData(context);
                PodcastRoomDatabase.get(context).clearAllTables();
                new Storage(context).deleteDirectory();
            }
        });
        playback.clear();
        UserPreferences.get(context).clear();
        Caches.INSTANCE.clear();
        UserPreferences.get(context).clear();
        PodcastDetailClientState.INSTANCE.clear(context);
        ptc.clear(context);
        followPrompt.clear(context);
        featureGates.clear();
        DownloadRefinementPreferences.INSTANCE.clear(context);
        SaveRefinementPreferences.INSTANCE.clear(context);
        unRegisterNetworkCallback();
        contentViewCache.clearCache();
    }

    public static void unRegisterNetworkCallback() {
        ConnectivityManager.NetworkCallback networkCallback2;
        ConnectivityManager connectivityManager2 = connectivityManager;
        if (connectivityManager2 == null || (networkCallback2 = networkCallback) == null) {
            return;
        }
        connectivityManager2.unregisterNetworkCallback(networkCallback2);
        networkCallback = null;
    }

    public static void registerNetworkCallback(final Context context) {
        connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        networkCallback = new ConnectivityManager.NetworkCallback() { // from class: com.amazon.podcast.Podcast.2
            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onAvailable(final Network network) {
                Handlers.INSTANCE.getForeground().postDelayed(new Runnable() { // from class: com.amazon.podcast.Podcast.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Podcast.engine.handleMethod(Podcast.ownerId, StartMethod.builder().withQueue(Queues.appSync()).build());
                    }
                }, TimeUnit.SECONDS.toMillis(1L));
            }
        };
        NetworkRequest networkRequestBuild = new NetworkRequest.Builder().build();
        networkRequest = networkRequestBuild;
        connectivityManager.registerNetworkCallback(networkRequestBuild, networkCallback);
    }

    public static UserInfoProvider getUserInfoProvider() {
        return userInfoProvider;
    }

    public static DynamicMessagingProvider getDynamicMessagingProvider() {
        return dynamicMessagingProvider;
    }

    public static DeviceInfoProvider getDeviceInfoProvider() {
        return deviceInfoProvider;
    }

    public static BuildInfoProvider getBuildInfoProvider() {
        return buildInfoProvider;
    }

    public static SharingProvider getSharingProvider() {
        return sharingProvider;
    }

    public static CarModeUserPresetHandler getCarModeUserPresetHandler() {
        return carModeUserPresetHandler;
    }

    public static AdvertisingPreferencesProvider getAdvertisingPreferencesProvider() {
        return advertisingPreferencesProvider;
    }

    public static String getMusicTerritory() {
        return musicTerritory;
    }

    public JumpBackIns getJumpBackIns() {
        return JumpBackIns.INSTANCE;
    }

    public Follows getFollows() {
        return Follows.INSTANCE;
    }

    public Saves getSaves() {
        return Saves.INSTANCE;
    }

    public static String getDefaultOwnerId() {
        return defaultOwnerId;
    }

    public void startBrowse() {
        if (!isPodcastLaunched.booleanValue()) {
            engine.handleMethods(this.templateList.getOwnerId(), onInvalidMarketPlaceErrorMethods(this.context));
            return;
        }
        String strMarketplaceId = getUserInfoProvider().marketplaceId();
        musicTerritory = getUserInfoProvider().musicTerritory();
        Engine engine2 = engine;
        String ownerId2 = this.templateList.getOwnerId();
        Context context = this.context;
        engine2.handleMethods(ownerId2, BootstrapMethods.onBrowseStarted(context, strMarketplaceId, context.getResources()));
    }

    public void startLibrary(final String deeplinkPath, final boolean isLibraryPodcastToggleSupported) {
        if (!isPodcastLaunched.booleanValue()) {
            engine.handleMethods(this.templateList.getOwnerId(), onInvalidMarketPlaceErrorMethods(this.context));
            return;
        }
        ingressSource = PodcastDeeplink.Source.LIBRARY.name();
        musicTerritory = getUserInfoProvider().musicTerritory();
        this.templateList.setIsLibraryPodcastToggleSupported(isLibraryPodcastToggleSupported);
        engine.handleMethods(this.templateList.getOwnerId(), BootstrapMethods.onLibraryStarted(this.context, deeplinkPath, clientStates.getDeeplinkInfo().getSource()));
    }

    public void startPlayback() {
        List<Method> restoreMiniPlayerMethods;
        if (!isPodcastLaunched.booleanValue()) {
            engine.handleMethods(this.templateList.getOwnerId(), onInvalidMarketPlaceErrorMethods(this.context));
            return;
        }
        String strMarketplaceId = getUserInfoProvider().marketplaceId();
        musicTerritory = getUserInfoProvider().musicTerritory();
        if (clientStates.getDeeplinkInfo() != null && !isRestoringPlayback.booleanValue()) {
            engine.handleMethods(this.templateList.getOwnerId(), BootstrapMethods.onPlaybackStarted(this.context, strMarketplaceId, clientStates.getDeeplinkInfo().getUrl(), clientStates.getDeeplinkInfo().getSource()));
            return;
        }
        Playback playback2 = playback;
        if (!playback2.isPodcastLastPlayed() || podcastPlayingOrBufferingOrPaused() || (restoreMiniPlayerMethods = playback2.readRestoreMiniPlayerMethods()) == null || restoreMiniPlayerMethods.isEmpty()) {
            return;
        }
        playback2.setRestoringPlayback(true);
        engine.handleMethods(ownerId, restoreMiniPlayerMethods);
    }

    public static String getMediaId() {
        return playback.getMedia().mediaId();
    }

    public static String getMediaCollectionId() {
        return playback.getMedia().mediaCollectionId();
    }

    private Podcast(final Bootstrap bootstrap) {
        Context context = bootstrap.getContext();
        this.context = context;
        isRestoringPlayback = Boolean.valueOf(bootstrap.isRestoringPlayback());
        playback.init(context);
        ptc.init(context);
        followPrompt.init(context);
        featureGates.init(context);
        if (downloads == null) {
            downloads = new Downloads(engine, context);
        }
        if (database == null) {
            database = PodcastRoomDatabase.get(context);
        }
        if (!firstLaunch) {
            clearPendingDownloads();
        }
        if (appsync == null) {
            appsync = new AppSync(engine, context, database);
        }
        if (clientStates == null) {
            clientStates = new ClientStates(appsync, context);
        }
        PodcastDeeplink deeplink = bootstrap.getDeeplink();
        if (!isRestoringPlayback.booleanValue()) {
            clientStates.setDeeplinkInfo(deeplink);
        }
        if (interactioncore == null) {
            interactioncore = new InteractionCore(engine, context, clientStates, bootstrap.getUserInfoProvider(), bootstrap.getAuthenticationInfoProvider(), bootstrap.getDeviceInfoProvider(), bootstrap.getBuildInfoProvider());
        }
        if (localSkillService == null) {
            localSkillService = new LocalSkillService(context, engine, database);
        }
        downloadSettingsProvider = bootstrap.getDownloadSettingsProvider();
        networkSettingsProvider = bootstrap.getNetworkSettingsProvider();
        authenticationProvider = bootstrap.getAuthenticationInfoProvider();
        userInfoProvider = bootstrap.getUserInfoProvider();
        dynamicMessagingProvider = bootstrap.getDynamicMessagingProvider();
        deviceInfoProvider = bootstrap.getDeviceInfoProvider();
        buildInfoProvider = bootstrap.getBuildInfoProvider();
        sharingProvider = bootstrap.getSharingProvider();
        carModeUserPresetHandler = bootstrap.getCarModeUserPresetHandler();
        advertisingPreferencesProvider = bootstrap.getAdvertisingPreferencesProvider();
        Templates templates2 = templates;
        templates2.setApplicationContext(context);
        featureGateProvider = bootstrap.getFeatureGateProvider();
        fragmentInfoProvider = bootstrap.getFragmentInfoProvider();
        featureGatePreferences = new FeatureGatePreferences(context);
        initializeRuntimeStyleSheet(context);
        TemplateList templateListCreateTemplateList = templates2.createTemplateList(context, bootstrap.getActionBarProvider(), fragmentInfoProvider, isRestoringPlayback.booleanValue());
        this.templateList = templateListCreateTemplateList;
        String ownerId2 = templateListCreateTemplateList.getOwnerId();
        defaultOwnerId = ownerId2;
        Engine engine2 = engine;
        engine2.registerOwner(ownerId2);
        ingressSource = deeplink == null ? null : bootstrap.getDeeplink().getSource();
        isPodcastLaunched = Boolean.valueOf(userInfoProvider.isPodcastLaunched());
        musicTerritory = getUserInfoProvider().musicTerritory();
        if (isInitiated) {
            return;
        }
        Caches.INSTANCE.init(engine2);
        JumpBackIns jumpBackIns = JumpBackIns.INSTANCE;
        String str = ownerId;
        jumpBackIns.init(context, engine2, str);
        Follows.INSTANCE.init(context, engine2, str);
        Saves.INSTANCE.init(context, engine2, str);
        isInitiated = true;
    }

    private void clearPendingDownloads() {
        Handlers.INSTANCE.getBackground().post(new Runnable() { // from class: com.amazon.podcast.Podcast.3
            @Override // java.lang.Runnable
            public void run() {
                Podcast.database.downloadsDao().deleteAllContainingState(DownloadState.IN_PROGRESS.getValue());
                Podcast.database.downloadsDao().deleteAllContainingState(DownloadState.QUEUED.getValue());
            }
        });
        firstLaunch = true;
    }

    private static List<Method> onInvalidMarketPlaceErrorMethods(final Context context) {
        Resources resources = context.getResources();
        return Arrays.asList(CreateAndBindTemplateMethod.builder().withForced(Boolean.FALSE).withQueue(Queues.template()).withTemplate(AlertTemplate.builder().withTitle(resources.getString(R$string.podcast_invalid_market_place_title)).withSubtitle(resources.getString(R$string.podcast_invalid_market_place_desc_generic)).withPositiveButton(PositiveButtonElement.builder().withOnItemSelected(Collections.emptyList()).withText(resources.getString(R$string.podcast_alert_ok)).build()).withLaunchMode(TemplateLaunchMode.singleUse).build()).withScreenMode("standard").build(), RemoveTemplateMethod.builder().withQueue(Queues.template()).build());
    }

    private boolean podcastPlayingOrBufferingOrPaused() {
        Playback playback2 = playback;
        return playback2.getPlaybackState() == 3 || playback2.getPlaybackState() == 6 || playback2.getPlaybackState() == 2;
    }

    public static Integer getBookmarksUpperBoundPercentage() {
        return bookmarksUpperBoundPercentage;
    }

    public static Long getBookmarksLowerBoundProgressMS() {
        return bookmarksLowerBoundProgressMS;
    }

    public static ClientStates getClientState() {
        return clientStates;
    }

    public static boolean isFireOS() {
        try {
            Class.forName("com.amazon.mp3.FireOSApplication");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static void initializeRuntimeStyleSheet(Context context) {
        if (runtimeStyleSheet == null) {
            runtimeStyleSheet = new RuntimeStyleSheet(context);
        }
    }

    public static void setHarleyEnabled() {
        isHarleyEnabled = PodcastFeatureGating.PODCAST_ON_HARLEY.isEnabled() && PodcastFeatureGating.PODCAST_ON_HARLEY_WEBLAB.isEnabled();
    }

    public static boolean isHarleyEnabled() {
        return isHarleyEnabled;
    }
}
