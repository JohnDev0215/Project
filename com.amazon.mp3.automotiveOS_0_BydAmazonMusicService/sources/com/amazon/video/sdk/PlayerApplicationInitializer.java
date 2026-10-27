package com.amazon.video.sdk;

import amazon.android.di.AppInitializationTracker;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import com.amazon.avod.acos.StorageHelper;
import com.amazon.avod.connectivity.NetworkConnectionManager;
import com.amazon.avod.core.ApplicationComponentsBase;
import com.amazon.avod.core.InitializationException;
import com.amazon.avod.core.PrimeVideoMediaSystemDependencies;
import com.amazon.avod.drm.db.DrmPersistence;
import com.amazon.avod.events.EventManager;
import com.amazon.avod.http.PlaybackAuthProvider;
import com.amazon.avod.http.PrimeVideoLegacyQOSHackAuthProvider;
import com.amazon.avod.http.ServiceClient;
import com.amazon.avod.http.internal.TokenKey;
import com.amazon.avod.identity.IdentityShim;
import com.amazon.avod.media.MediaSystem;
import com.amazon.avod.media.ads.AdvertisingIdCollector;
import com.amazon.avod.media.downloadservice.internal.PrioritizingDownloadService;
import com.amazon.avod.media.framework.MediaSystemSharedDependencies;
import com.amazon.avod.media.playback.reporting.EventReporterFactory;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusDownloadReporter;
import com.amazon.avod.media.playback.reporting.aloysius.NoopDownloadReporter;
import com.amazon.avod.media.playback.support.RendererSchemeOverrides;
import com.amazon.avod.playback.capability.DeviceIdentity;
import com.amazon.avod.playback.core.AndroidDeviceIdentity;
import com.amazon.avod.playback.core.NoOpIdentityShim;
import com.amazon.avod.playback.core.PlayerApplicationComponents;
import com.amazon.avod.postmanifest.PostManifestNoOpServiceClient;
import com.amazon.avod.postmanifest.PostManifestService;
import com.amazon.avod.purchase.AssociateTagProviderProxy;
import com.amazon.avod.purchase.NoOpAssociateTagProvider;
import com.amazon.avod.qahooks.PlaybackQAHookInitializer;
import com.amazon.avod.qahooks.PlaybackQASettings;
import com.amazon.avod.qahooks.QAAutomationReceiver;
import com.amazon.avod.qahooks.QaSettings;
import com.amazon.avod.qos.QoSConfig;
import com.amazon.avod.userdownload.MixedDownloadDrmPersistenceProxy;
import com.amazon.avod.userdownload.MixedDownloadManagerPlayerShim;
import com.amazon.avod.userdownload.PVDownloadsPlayerShim;
import com.amazon.avod.userdownload.PlaybackDownloadPlayreadyManager;
import com.amazon.avod.userdownload.PlaybackDownloads;
import com.amazon.avod.userdownload.internal.PlaybackDownloadSharedComponents;
import com.amazon.avod.userdownload.internal.PlaybackDownloadsComponentFactory;
import com.amazon.avod.userdownload.rights.NoOpRightsManager;
import com.amazon.avod.util.AppVisibilityTracker;
import com.amazon.avod.util.CastUtils;
import com.amazon.avod.util.DLog;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.video.sdk.AuthContext;
import com.amazon.video.sdk.avod.playbackclient.subtitle.download.SubtitleContentPlugin;
import com.amazon.video.sdk.avod.playbackclient.trickplay.download.TrickplayPlugin;
import com.amazon.video.sdk.download.SDCardStorageUtils;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
class PlayerApplicationInitializer {
    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ DrmPersistence lambda$init$1(MixedDownloadDrmPersistenceProxy mixedDownloadDrmPersistenceProxy) {
        return mixedDownloadDrmPersistenceProxy;
    }

    PlayerApplicationInitializer() {
    }

    static void init(@Nonnull PlayerSdkConfig playerSdkConfig, @Nonnull String str) {
        Supplier<DrmPersistence> supplierMemoize;
        AdvertisingIdCollector tenantAdvertisingIdCollector;
        DeviceIdentity androidDeviceIdentity;
        IdentityShim noOpIdentityShim;
        String str2;
        Context context = (Context) Preconditions.checkNotNull(playerSdkConfig.getContext(), "context");
        final AuthContext authContext = (AuthContext) Preconditions.checkNotNull(playerSdkConfig.getAuthContext(), "authContext");
        String str3 = (String) Preconditions.checkNotNull(playerSdkConfig.getRoute(), "route");
        Preconditions.checkNotNull(str, "sdkVersion");
        Preconditions.checkArgument(context.getApplicationContext() instanceof Application);
        Application application = (Application) context.getApplicationContext();
        RendererDrmFlag rendererDrmFlag = RendererDrmFlag.INSTANCE;
        RendererSchemeOverrides.RendererSchemeOverridesData rendererSchemeOverridesData = new RendererSchemeOverrides.RendererSchemeOverridesData(rendererDrmFlag.mediaCodecSoftwarePlayReady(playerSdkConfig.getRendererDrmFlags()), rendererDrmFlag.mediaCodecNoDrmOverride(playerSdkConfig.getRendererDrmFlags()) || rendererDrmFlag.mediaCodecWidevine(playerSdkConfig.getRendererDrmFlags()), rendererDrmFlag.widevine(playerSdkConfig.getRendererDrmFlags()));
        PlaybackAuthProvider playbackAuthProvider = new PlaybackAuthProvider() { // from class: com.amazon.video.sdk.PlayerApplicationInitializer.1
            @Override // com.amazon.avod.http.PlaybackAuthProvider
            @Nonnull
            public Map<String, String> getHeaders(@Nonnull Map<String, String> map) throws PlaybackAuthProvider.InvalidSessionContextException {
                try {
                    return authContext.getHeaders(map);
                } catch (AuthContext.InvalidSessionContextException unused) {
                    throw new PlaybackAuthProvider.InvalidSessionContextException();
                }
            }

            @Override // com.amazon.avod.http.PrimeVideoLegacyQOSHackAuthProvider
            @Nullable
            public TokenKey getTokenKeyForCurrentUser() {
                PrimeVideoLegacyQOSHackAuthProvider primeVideoLegacyQOSHackAuthProvider = (PrimeVideoLegacyQOSHackAuthProvider) CastUtils.castTo(authContext, PrimeVideoLegacyQOSHackAuthProvider.class);
                if (primeVideoLegacyQOSHackAuthProvider != null) {
                    return primeVideoLegacyQOSHackAuthProvider.getTokenKeyForCurrentUser();
                }
                return null;
            }
        };
        PrimeVideoMediaSystemDependencies primeVideoMediaSystemDependencies = PrimeVideoMediaSystemDependencies.getInstance();
        PlaybackDownloadSharedComponents playbackDownloadSharedComponents = new PlaybackDownloadSharedComponents(MediaSystemSharedDependencies.getInstance(), NetworkConnectionManager.getInstance(), ServiceClient.getInstance(), StorageHelper.getInstance());
        Executors.newSingleThreadExecutor().submit(new Runnable() { // from class: com.amazon.video.sdk.PlayerApplicationInitializer$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                PlayerApplicationInitializer.lambda$init$0();
            }
        });
        PlaybackDownloadsComponentFactory playbackDownloadsComponentFactory = new PlaybackDownloadsComponentFactory(playbackDownloadSharedComponents, Suppliers.memoize(new Supplier() { // from class: com.amazon.video.sdk.PlayerApplicationInitializer$$ExternalSyntheticLambda1
            @Override // com.google.common.base.Supplier
            public final Object get() {
                return new NoOpRightsManager();
            }
        }), context);
        if (primeVideoMediaSystemDependencies.isInitialized()) {
            PlaybackDownloads.initializePlaybackDownloadsSingleton();
            PlaybackDownloads.getInstance().initialize(playbackDownloadsComponentFactory);
            PVDownloadsPlayerShim.getInstance().setPlayerDownloadManagerProxy(new MixedDownloadManagerPlayerShim(PVDownloadsPlayerShim.getInstance().getPlayerDownloadManagerProxy(), PlaybackDownloads.getInstance().getDownloadManager()));
            final MixedDownloadDrmPersistenceProxy mixedDownloadDrmPersistenceProxy = new MixedDownloadDrmPersistenceProxy(primeVideoMediaSystemDependencies.getDrmPersistence().get(), PlaybackDownloads.getInstance().getDownloadManager());
            supplierMemoize = Suppliers.memoize(new Supplier() { // from class: com.amazon.video.sdk.PlayerApplicationInitializer$$ExternalSyntheticLambda2
                @Override // com.google.common.base.Supplier
                public final Object get() {
                    return PlayerApplicationInitializer.lambda$init$1(mixedDownloadDrmPersistenceProxy);
                }
            });
            tenantAdvertisingIdCollector = primeVideoMediaSystemDependencies.getAdvertisingIdCollector();
            androidDeviceIdentity = primeVideoMediaSystemDependencies.getDeviceIdentity();
            noOpIdentityShim = primeVideoMediaSystemDependencies.getIdentityShim();
            str2 = "Android Player";
        } else {
            PlaybackDownloads.initializeSingleton();
            PlaybackDownloads.getInstance().initialize(playbackDownloadsComponentFactory);
            supplierMemoize = Suppliers.memoize(new Supplier() { // from class: com.amazon.video.sdk.PlayerApplicationInitializer$$ExternalSyntheticLambda3
                @Override // com.google.common.base.Supplier
                public final Object get() {
                    return PlayerApplicationInitializer.lambda$init$2();
                }
            });
            tenantAdvertisingIdCollector = new TenantAdvertisingIdCollector(playerSdkConfig.getAdConfigProvider());
            androidDeviceIdentity = AndroidDeviceIdentity.getInstance();
            noOpIdentityShim = new NoOpIdentityShim();
            str2 = "Android Player SDK";
        }
        String str4 = str2;
        MediaSystem mediaSystem = MediaSystem.getInstance();
        mediaSystem.setInitializationDependencies(application, supplierMemoize, tenantAdvertisingIdCollector, androidDeviceIdentity, str3, str4, str, rendererSchemeOverridesData, playbackAuthProvider, noOpIdentityShim, playerSdkConfig.getPlaybackEnvelopeValidator(), playerSdkConfig.getHdcpLevelProvider());
        if (!primeVideoMediaSystemDependencies.isInitialized()) {
            initializePlayerApplication(playerSdkConfig).waitForFullInitialization();
            AppInitializationTracker.getInstance().releaseApplicationInitializationLatch();
        }
        StorageHelper.getInstance().waitOnExternalStorageInitializationUninterruptibly();
        mediaSystem.startInitializationAsync();
        mediaSystem.waitOnInitializationUninterruptibly();
        if (primeVideoMediaSystemDependencies.isInitialized()) {
            PlaybackDownloads.getInstance().getLocationConfig().setDownloadLocationRootPrefix(String.valueOf(application.getApplicationContext().getApplicationInfo().uid));
            try {
                PlaybackDownloads.getInstance().getDownloadManager().initialize();
                PlaybackDownloadPlayreadyManager playbackDownloadPlayreadyManager = new PlaybackDownloadPlayreadyManager(mediaSystem.getMediaSystemComponent().getBaseDrmSystem());
                EventReporterFactory eventReporterFactory = mediaSystem.getEventReporterFactory();
                AloysiusDownloadReporter aloysiusDownloadReporterOrNull = mediaSystem.getAloysiusInitializer().getAloysiusDownloadReporter().orNull();
                PlaybackDownloads.getInstance().getDownloadManager().initializeWithMediaComponents(eventReporterFactory, playbackDownloadPlayreadyManager, mediaSystem.getPlaybackDownloadExecutorFactory(), ImmutableList.of((SubtitleContentPlugin.PluginProvider) new TrickplayPlugin.PluginProvider(application.getApplicationContext(), PrioritizingDownloadService.getInstance()), new SubtitleContentPlugin.PluginProvider(PrioritizingDownloadService.getInstance())), aloysiusDownloadReporterOrNull != null ? aloysiusDownloadReporterOrNull : new NoopDownloadReporter());
            } catch (InitializationException e) {
                AppInitializationTracker.getInstance().notifyInitializationError(e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$init$0() {
        StorageHelper.getInstance().waitOnInitializationUninterruptibly();
        StorageHelper.getInstance().initializeExternalStorage();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ DrmPersistence lambda$init$2() {
        return PlaybackDownloads.getInstance().getDownloadManager();
    }

    private static ApplicationComponentsBase initializePlayerApplication(@Nonnull PlayerSdkConfig playerSdkConfig) {
        Context context = (Context) Preconditions.checkNotNull(playerSdkConfig.getContext(), "context");
        final String str = (String) Preconditions.checkNotNull(playerSdkConfig.getDeviceId(), Splash.PARAMS_DEVICE_ID);
        final String str2 = (String) Preconditions.checkNotNull(playerSdkConfig.getDeviceTypeId(), "deviceTypeId");
        Context applicationContext = context.getApplicationContext();
        Preconditions.checkArgument(applicationContext instanceof Application);
        final Application application = (Application) applicationContext;
        QaSettings.getInstance().inferAppMode(applicationContext);
        PlaybackQAHookInitializer playbackQAHookInitializer = PlaybackQAHookInitializer.getInstance();
        playbackQAHookInitializer.initializeOverrides(context);
        playbackQAHookInitializer.initializeQAHooks(context, true);
        if (PlaybackQASettings.getInstance().isPlaybackQAHooksEnabled()) {
            applicationContext.registerReceiver(QAAutomationReceiver.getInstance(), QAAutomationReceiver.getInstance().getIntentFilter());
        }
        AppVisibilityTracker.getInstance().initializeOnAppCreate(application);
        StorageHelper.getInstance().initialize(application, new SDCardStorageUtils(context, str), false);
        final PlayerApplicationComponents playerApplicationComponents = PlayerApplicationComponents.getInstance();
        playerApplicationComponents.addBackgroundInitializationCall(new ApplicationComponentsBase.InitializationTask() { // from class: com.amazon.video.sdk.PlayerApplicationInitializer$$ExternalSyntheticLambda4
            @Override // com.amazon.avod.core.ApplicationComponentsBase.InitializationTask
            public final void initialize() throws InitializationException {
                PlayerApplicationInitializer.lambda$initializePlayerApplication$3();
            }
        }, "AssociateTagProviderProxy");
        final EventManager eventManager = EventManager.getInstance();
        eventManager.setInitializationDependencies(context);
        playerApplicationComponents.addBlockingInitializationCall(new ApplicationComponentsBase.InitializationTask() { // from class: com.amazon.video.sdk.PlayerApplicationInitializer$$ExternalSyntheticLambda5
            @Override // com.amazon.avod.core.ApplicationComponentsBase.InitializationTask
            public final void initialize() {
                eventManager.initialize();
            }
        }, "EventManager");
        PostManifestService.getInstance().initialize(new PostManifestNoOpServiceClient());
        final MediaSystem mediaSystem = MediaSystem.getInstance();
        playerApplicationComponents.addBackgroundInitializationCall(new ApplicationComponentsBase.InitializationTask() { // from class: com.amazon.video.sdk.PlayerApplicationInitializer$$ExternalSyntheticLambda6
            @Override // com.amazon.avod.core.ApplicationComponentsBase.InitializationTask
            public final void initialize() throws InitializationException {
                PlayerApplicationInitializer.lambda$initializePlayerApplication$4(mediaSystem, application);
            }
        }, "PlaybackDownloads:Execution");
        playerApplicationComponents.addBlockingInitializationCall(new ApplicationComponentsBase.InitializationTask() { // from class: com.amazon.video.sdk.PlayerApplicationInitializer$$ExternalSyntheticLambda7
            @Override // com.amazon.avod.core.ApplicationComponentsBase.InitializationTask
            public final void initialize() throws InitializationException {
                PlayerApplicationInitializer.lambda$initializePlayerApplication$5();
            }
        }, "PlaybackDownloads:Persistence");
        playerApplicationComponents.addBackgroundInitializationCall(new ApplicationComponentsBase.InitializationTask() { // from class: com.amazon.video.sdk.PlayerApplicationInitializer$$ExternalSyntheticLambda8
            @Override // com.amazon.avod.core.ApplicationComponentsBase.InitializationTask
            public final void initialize() throws InitializationException {
                PlayerApplicationInitializer.lambda$initializePlayerApplication$6(eventManager);
            }
        }, "LegacyQos:Cleanup");
        new Handler(context.getMainLooper()).post(new Runnable() { // from class: com.amazon.video.sdk.PlayerApplicationInitializer$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                PlayerApplicationInitializer.lambda$initializePlayerApplication$7(playerApplicationComponents, application, str, str2);
            }
        });
        return playerApplicationComponents;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$initializePlayerApplication$3() throws InitializationException {
        AssociateTagProviderProxy.getInstance().initialize(new NoOpAssociateTagProvider());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$initializePlayerApplication$4(MediaSystem mediaSystem, Application application) throws InitializationException {
        mediaSystem.waitOnInitializationUninterruptibly();
        PlaybackDownloadPlayreadyManager playbackDownloadPlayreadyManager = new PlaybackDownloadPlayreadyManager(mediaSystem.getMediaSystemComponent().getBaseDrmSystem());
        EventReporterFactory eventReporterFactory = mediaSystem.getEventReporterFactory();
        AloysiusDownloadReporter aloysiusDownloadReporterOrNull = mediaSystem.getAloysiusInitializer().getAloysiusDownloadReporter().orNull();
        PlaybackDownloads.getInstance().getDownloadManager().initializeWithMediaComponents(eventReporterFactory, playbackDownloadPlayreadyManager, mediaSystem.getPlaybackDownloadExecutorFactory(), ImmutableList.of((SubtitleContentPlugin.PluginProvider) new TrickplayPlugin.PluginProvider(application.getApplicationContext(), PrioritizingDownloadService.getInstance()), new SubtitleContentPlugin.PluginProvider(PrioritizingDownloadService.getInstance())), aloysiusDownloadReporterOrNull != null ? aloysiusDownloadReporterOrNull : new NoopDownloadReporter());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$initializePlayerApplication$5() throws InitializationException {
        PlaybackDownloads.getInstance().getDownloadManager().initialize();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$initializePlayerApplication$6(EventManager eventManager) throws InitializationException {
        if (QoSConfig.INSTANCE.shouldDropQoSEventsForSdkPlayer()) {
            eventManager.waitOnInitializationUninterruptibly();
            DLog.logf("Dropping any enqueued QoS events...");
            eventManager.cleanUpEvents();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$initializePlayerApplication$7(PlayerApplicationComponents playerApplicationComponents, Application application, String str, String str2) {
        playerApplicationComponents.preInitializeWithValidContext(application);
        playerApplicationComponents.startFullInitializationAsync(new PlayerApplicationComponents.InitParams(application, str, str2));
    }

    private static class TenantAdvertisingIdCollector implements AdvertisingIdCollector {
        private final Callable<AdConfig> mAdConfigCallable;
        private final ExecutorService mExecutor;
        private AdvertisingIdCollector.AdvertisingInfo mLastUpdatedData;

        TenantAdvertisingIdCollector(@Nonnull final AdConfigProvider adConfigProvider) {
            Preconditions.checkNotNull(adConfigProvider, "adConfigProvider");
            adConfigProvider.getClass();
            this.mAdConfigCallable = new Callable() { // from class: com.amazon.video.sdk.PlayerApplicationInitializer$TenantAdvertisingIdCollector$$ExternalSyntheticLambda0
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return adConfigProvider.getAdConfig();
                }
            };
            this.mExecutor = Executors.newSingleThreadExecutor();
        }

        @Override // com.amazon.avod.media.ads.AdvertisingIdCollector
        @Nonnull
        public AdvertisingIdCollector.AdvertisingInfo get(long j, boolean z) {
            AdvertisingIdCollector.AdvertisingInfo advertisingInfo;
            if (z && (advertisingInfo = this.mLastUpdatedData) != null) {
                return advertisingInfo;
            }
            AdvertisingIdCollector.AdvertisingInfo advertisingInfo2 = getAdvertisingInfo(j);
            this.mLastUpdatedData = advertisingInfo2;
            return advertisingInfo2;
        }

        @Nonnull
        private AdvertisingIdCollector.AdvertisingInfo getAdvertisingInfo(long j) {
            try {
                AdConfig adConfig = (AdConfig) this.mExecutor.submit(this.mAdConfigCallable).get(j, TimeUnit.MILLISECONDS);
                return new AdvertisingIdCollector.AdvertisingInfo(adConfig.getAdvertisingId(), Boolean.valueOf(adConfig.getOptOut()));
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                DLog.exceptionf(e, "Exception fetching adInfo from tenant, using default values", new Object[0]);
                return new AdvertisingIdCollector.AdvertisingInfo("null", Boolean.TRUE);
            }
        }
    }
}
