package com.amazon.avod.media;

import amazon.android.config.ConfigurationValue;
import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;
import com.amazon.avod.app.init.components.ReentrantInitializer;
import com.amazon.avod.core.InitializationException;
import com.amazon.avod.download.PlaybackDownloadExecutorFactory;
import com.amazon.avod.drm.BaseDrmSystem;
import com.amazon.avod.drm.db.DrmPersistence;
import com.amazon.avod.http.PlaybackAuthProvider;
import com.amazon.avod.identity.IdentityShim;
import com.amazon.avod.media.ads.AdvertisingIdCollector;
import com.amazon.avod.media.ads.internal.config.AdsConfig;
import com.amazon.avod.media.aloysius.AloysiusInitializer;
import com.amazon.avod.media.contentcache.VideoCacheManager;
import com.amazon.avod.media.contentcache.internal.ClientCacheConfig;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.media.downloadservice.internal.OkHttpClientFactory;
import com.amazon.avod.media.downloadservice.internal.PrioritizingDownloadService;
import com.amazon.avod.media.framework.MediaComponent;
import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.framework.MediaSystemSharedDependencies;
import com.amazon.avod.media.framework.MediaSystemSharedPrefs;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.framework.libraries.LoadableNativeLibrary;
import com.amazon.avod.media.framework.libraries.PlaybackNativeLibrariesLoader;
import com.amazon.avod.media.framework.platform.FileSystem;
import com.amazon.avod.media.framework.profiling.MediaProfiler;
import com.amazon.avod.media.playback.VideoPresentationFactory;
import com.amazon.avod.media.playback.reporting.EventReporterFactory;
import com.amazon.avod.media.playback.reporting.MediaSystemLoadReporter;
import com.amazon.avod.media.playback.reporting.aloysius.PlaybackMediaEventReporters;
import com.amazon.avod.media.playback.support.AutoEvalCanaryPolicy;
import com.amazon.avod.media.playback.support.CachingPlaybackSupportEvaluator;
import com.amazon.avod.media.playback.support.ConfigurablePlaybackSupportEvaluator;
import com.amazon.avod.media.playback.support.RendererScheme;
import com.amazon.avod.media.playback.support.RendererSchemeController;
import com.amazon.avod.media.playback.support.RendererSchemeOverrides;
import com.amazon.avod.media.playback.support.RendererSchemeResolverConfig;
import com.amazon.avod.media.service.PlayerResourcesExternalParamHolder;
import com.amazon.avod.media.service.cache.ForwardingPlayerResourcesCache;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.SimpleTimerMetric;
import com.amazon.avod.perf.TraceKey;
import com.amazon.avod.playback.capability.DeviceIdentity;
import com.amazon.avod.playback.core.PlayerInitializationErrorCode;
import com.amazon.avod.playback.renderer.RendererSchemeResolver;
import com.amazon.avod.playback.smoothstream.AdaptiveStreamingModule_Dagger;
import com.amazon.avod.prs.ClientResourcesAndParams$ClientResourcesExternalParamHolder;
import com.amazon.avod.threading.ExecutorBuilder;
import com.amazon.avod.util.AppStartConfig;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.InitializationLatch;
import com.amazon.video.sdk.player.HdcpLevelProvider;
import com.amazon.video.sdk.player.PlaybackEnvelopeValidator;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public class MediaSystem extends ReentrantInitializer {
    private AdvertisingIdCollector mAdvertisingIdCollector;
    AloysiusInitializer mAloysiusInitializer;
    Provider<BaseDrmSystem> mBaseDrmSystem;
    Provider<Set<MediaComponent>> mComponentProvider;
    private Context mContext;
    private DeviceIdentity mDeviceIdentity;
    private Supplier<DrmPersistence> mDrmPersistence;
    EventReporterFactory mEventReporterFactory;
    FileSystem mFileSystem;
    Set<LoadableNativeLibrary> mLibraries;
    PlaybackNativeLibrariesLoader mLibrariesLoader;
    private MediaSystemComponent mMediaSystemComponent;
    protected PlaybackMediaEventReporters.Factory mPlaybackMediaEventReportersFactory;
    private String mPlayerName;
    private String mPlayerSdkVersion;
    MediaProfiler mProfiler;
    RendererSchemeController mRendererSchemeController;
    private ImmutableList<RendererScheme> mRendererSchemeOrderedList;
    private RendererSchemeOverrides mRendererSchemeOverrides;
    private String mResolutionChain;
    private MediaSystemSharedContext mSharedContext;
    private final InitializationLatch mInitializationLatch = new InitializationLatch(String.format(Locale.US, "%s:%s", getClass().getSimpleName(), "Initialization"));
    private final Supplier<CachingPlaybackSupportEvaluator> mPlaybackSupportEvaluator = Suppliers.memoize(new Supplier() { // from class: com.amazon.avod.media.MediaSystem$$ExternalSyntheticLambda0
        @Override // com.google.common.base.Supplier
        public final Object get() {
            return CachingPlaybackSupportEvaluator.getInstance();
        }
    });

    public interface MediaSystemComponent {
        BaseDrmSystem getBaseDrmSystem();

        ClientCacheConfig getClientCacheConfig();

        ConfigurablePlaybackSupportEvaluator getConfigurablePlaybackSupportEvaluator();

        EventReporterFactory getEventReporterFactory();

        MediaSystemSharedContext getMediaSystemSharedContext();

        PlaybackDownloadExecutorFactory getPlaybackDownloadExecutorFactory();

        PlaybackMediaEventReporters.Factory getPlaybackMediaEventReportersFactory();

        VideoCacheManager getVideoCacheManager();

        VideoPresentationFactory getVideoPresentationFactory();

        MediaSystem injectMediaSystem(MediaSystem mediaSystem);
    }

    private static class Holder {
        private static volatile MediaSystem sInstance = new MediaSystem();

        private Holder() {
        }
    }

    @Nonnull
    public static MediaSystem getInstance() {
        return Holder.sInstance;
    }

    MediaSystem() {
    }

    public void setInitializationDependencies(@Nonnull Context context, @Nonnull Supplier<DrmPersistence> supplier, @Nonnull AdvertisingIdCollector advertisingIdCollector, @Nonnull DeviceIdentity deviceIdentity, @Nonnull String str, @Nonnull String str2, @Nullable String str3, @Nonnull RendererSchemeOverrides rendererSchemeOverrides, @Nonnull PlaybackAuthProvider playbackAuthProvider, @Nonnull IdentityShim identityShim, @Nonnull PlaybackEnvelopeValidator playbackEnvelopeValidator, @Nonnull HdcpLevelProvider hdcpLevelProvider) {
        Preconditions.checkNotNull(context, "context");
        Preconditions.checkNotNull(supplier, "drmPersistence");
        Preconditions.checkNotNull(advertisingIdCollector, "advertisingIdCollector");
        Preconditions.checkNotNull(deviceIdentity, "deviceIdentity");
        Preconditions.checkNotNull(str, "terminatorId");
        Preconditions.checkNotNull(str2, "playerName");
        Preconditions.checkNotNull(rendererSchemeOverrides, "rendererSchemeOverrides");
        Preconditions.checkNotNull(playbackAuthProvider, "playbackAuthProvider");
        Preconditions.checkNotNull(identityShim, "identityShim");
        Preconditions.checkNotNull(playbackEnvelopeValidator, "playbackEnvelopeValidator");
        Preconditions.checkNotNull(hdcpLevelProvider, "hdcpLevelProvider");
        this.mDependenciesLatch.start(30L, TimeUnit.SECONDS);
        this.mContext = context.getApplicationContext();
        this.mDrmPersistence = supplier;
        this.mAdvertisingIdCollector = advertisingIdCollector;
        this.mDeviceIdentity = deviceIdentity;
        this.mPlayerName = str2;
        this.mPlayerSdkVersion = str3;
        this.mRendererSchemeOverrides = rendererSchemeOverrides;
        MediaSystemSharedDependencies.getInstance().initialize(this.mDeviceIdentity, str, str2, playbackAuthProvider, identityShim, playbackEnvelopeValidator, hdcpLevelProvider);
        this.mDependenciesLatch.complete();
    }

    @Override // com.amazon.avod.app.init.components.ReentrantInitializer
    protected void initialize() throws InitializationException {
        Preconditions.checkNotNull(this.mContext);
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (AppStartConfig.getInstance().isFirstStart()) {
            TraceKey traceKeyBeginTrace = Profiler.beginTrace(Profiler.TraceLevel.INFO, "MediaSystem:FirstStartWaitForDependencies");
            this.mDeviceIdentity.waitOnInitialized();
            Profiler.endTrace(traceKeyBeginTrace);
        }
        AppStartConfig.getInstance().waitOnSuccessfulAppStartConfigCallUninterruptedly();
        this.mInitializationLatch.start(180L, TimeUnit.SECONDS);
        MediaSystemSharedPrefs mediaSystemSharedPrefs = MediaSystemSharedPrefs.getInstance();
        mediaSystemSharedPrefs.initialize(this.mContext);
        this.mInitializationLatch.updateProgress("ResolveRendererScheme");
        RendererSchemeResolver rendererSchemeResolver = new RendererSchemeResolver();
        try {
            this.mRendererSchemeOrderedList = rendererSchemeResolver.resolveSchemes(this.mContext, this.mDeviceIdentity, this.mRendererSchemeOverrides);
            this.mResolutionChain = rendererSchemeResolver.getResolutionChain();
            this.mInitializationLatch.updateProgress("CreateComponent");
            this.mMediaSystemComponent = new MediaSystemComponentFactory().newMediaSystemComponent(this.mAdvertisingIdCollector);
            this.mInitializationLatch.updateProgress("InjectMembers");
            MediaSystemSharedContext mediaSystemSharedContext = this.mMediaSystemComponent.getMediaSystemSharedContext();
            this.mSharedContext = mediaSystemSharedContext;
            mediaSystemSharedContext.initialize(this.mContext, this.mDeviceIdentity);
            this.mMediaSystemComponent.injectMediaSystem(this);
            this.mRendererSchemeController.initialize(this.mRendererSchemeOrderedList, mediaSystemSharedPrefs, RendererSchemeResolverConfig.getInstance(), AutoEvalCanaryPolicy.getInstance(), this.mContext);
            this.mInitializationLatch.updateProgress("InitializeRendererSchemes");
            UnmodifiableIterator<RendererScheme> it = this.mRendererSchemeOrderedList.iterator();
            while (it.hasNext()) {
                it.next().initialize(this.mSharedContext, this.mProfiler, this.mFileSystem, this.mLibrariesLoader, this.mRendererSchemeOverrides);
            }
            this.mInitializationLatch.updateProgress("LoadLibrariesAsync");
            HashSet hashSet = new HashSet();
            UnmodifiableIterator<RendererScheme> it2 = this.mRendererSchemeOrderedList.iterator();
            while (it2.hasNext()) {
                hashSet.addAll(it2.next().getNativeLibraries());
            }
            this.mLibrariesLoader.loadLibrariesAsync(Sets.union(this.mLibraries, hashSet), this.mDeviceIdentity, RendererSchemeResolverConfig.getInstance().shouldDirectLoadNativeLibraries());
            this.mInitializationLatch.updateProgress("InitializeComponents_GetComponents");
            Set<MediaComponent> set = this.mComponentProvider.get();
            this.mInitializationLatch.updateProgress("InitializeComponents_MainLoop");
            for (MediaComponent mediaComponent : set) {
                DLog.logf("Initializing media component %s", mediaComponent.getClass().getSimpleName());
                mediaComponent.initialize();
            }
            this.mAloysiusInitializer.initialize();
            this.mInitializationLatch.updateProgress("InitializeComponents_advertisingIdCollector");
            ClientResourcesAndParams$ClientResourcesExternalParamHolder.adsInitialize(this.mAdvertisingIdCollector, AdsConfig.getInstance().getGPAIDFetchTimeOut().getTotalMilliseconds());
            AsyncTask.execute(new Runnable() { // from class: com.amazon.avod.media.MediaSystem.1
                @Override // java.lang.Runnable
                @SuppressFBWarnings(justification = "https://sim.amazon.com/issues/AIVPLAYERS-14290", value = {"RV_RETURN_VALUE_IGNORED_NO_SIDE_EFFECT"})
                public void run() {
                    MediaSystem.this.mAdvertisingIdCollector.get(AdsConfig.getInstance().getGPAIDFetchTimeOut().getTotalMilliseconds(), false);
                }
            });
            this.mInitializationLatch.updateProgress("InitializeComponents_DRM");
            BaseDrmSystem baseDrmSystem = this.mBaseDrmSystem.get();
            baseDrmSystem.addResetObserver(this.mRendererSchemeOrderedList.get(0));
            baseDrmSystem.initialize(this.mDrmPersistence.get());
            this.mInitializationLatch.updateProgress("InitializeComponents_PlaybackSupportEvaluator");
            this.mPlaybackSupportEvaluator.get().initialize(this.mMediaSystemComponent.getConfigurablePlaybackSupportEvaluator());
            this.mInitializationLatch.updateProgress("InitializeComponents_PlayerResourcesAndParams");
            PlayerResourcesExternalParamHolder.initialize(this.mPlaybackSupportEvaluator.get(), this.mAdvertisingIdCollector, this.mRendererSchemeController);
            this.mInitializationLatch.updateProgress("InitializeComponents_OkHttpClientFactory");
            OkHttpClientFactory.getInstance().initialize(this.mContext);
            this.mInitializationLatch.complete();
            Profiler.reportTimerMetric(new SimpleTimerMetric("MediaSystem:Initialization", jElapsedRealtime, SystemClock.elapsedRealtime() - jElapsedRealtime));
            handleInitializationResult(this.mContext);
            handleHardwareToSoftwareRendererSchemeTransition();
        } catch (MediaException e) {
            if (RendererSchemeResolverConfig.getInstance().shouldThrowInitializationExceptionInsteadOfISE()) {
                throw new InitializationException(PlayerInitializationErrorCode.NO_SUPPORTED_RENDERER_SCHEMES, "Failed to resolve renderer scheme", e);
            }
            throw new IllegalStateException("Failed to resolve renderer scheme", e);
        }
    }

    private void handleHardwareToSoftwareRendererSchemeTransition() {
        if (RendererSchemeResolverConfig.getInstance().shouldHandleHardwareToSoftwareRendererSchemeTransition() && this.mDeviceIdentity.isThirdParty()) {
            ThreadPoolExecutor threadPoolExecutorBuild = ExecutorBuilder.newBuilderFor(this, "handleHardwareToSoftwareRendererSchemeTransition").withProfilerTraceLevel(Profiler.TraceLevel.DEBUG).build();
            threadPoolExecutorBuild.execute(new Runnable() { // from class: com.amazon.avod.media.MediaSystem$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$handleHardwareToSoftwareRendererSchemeTransition$1();
                }
            });
            threadPoolExecutorBuild.shutdown();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$handleHardwareToSoftwareRendererSchemeTransition$1() {
        try {
            ConfigurationValue<Boolean> hardwareAcceleratedRendererSchemeFlag = RendererSchemeResolverConfig.getInstance().getHardwareAcceleratedRendererSchemeFlag();
            if (isHardwareDrmSchemeResolved()) {
                hardwareAcceleratedRendererSchemeFlag.updateValue(Boolean.TRUE);
            } else if (hardwareAcceleratedRendererSchemeFlag.getValue().booleanValue()) {
                hardwareAcceleratedRendererSchemeFlag.updateValue(Boolean.FALSE);
                DLog.warnf("MediaSystem: Clearing ALL whisper cache data and playback resources on renderer scheme transition from hardware to software!");
                getVideoCacheManager().clearCache();
                ForwardingPlayerResourcesCache.getInstance().clear();
            }
        } catch (Exception e) {
            DLog.warnf("MediaSystem failed to clear cache on renderer scheme transition %s", e);
        }
    }

    private boolean isHardwareDrmSchemeResolved() {
        ImmutableList<RendererScheme> immutableList = this.mRendererSchemeOrderedList;
        if (immutableList == null) {
            return false;
        }
        UnmodifiableIterator<RendererScheme> it = immutableList.iterator();
        while (it.hasNext()) {
            if (it.next().getSchemeType().isHardwareDrm()) {
                return true;
            }
        }
        return false;
    }

    private void handleInitializationResult(@Nonnull final Context context) {
        ThreadPoolExecutor threadPoolExecutorBuild = ExecutorBuilder.newBuilderFor(this, "handleInitializationResult").withProfilerTraceLevel(Profiler.TraceLevel.DEBUG).build();
        threadPoolExecutorBuild.execute(new Runnable() { // from class: com.amazon.avod.media.MediaSystem.2
            @Override // java.lang.Runnable
            public void run() {
                if (MediaSystem.this.mEventReporterFactory == null) {
                    DLog.errorf("Failed to load the event reporter");
                    return;
                }
                MediaSystemLoadReporter mediaSystemLoadReporterNewLoadReporter = new LoadReporterFactory().newLoadReporter(context, MediaSystem.this.mEventReporterFactory, String.format(Locale.US, "MediaSystemInitialization_%s", UUID.randomUUID().toString()));
                if (MediaSystem.this.mRendererSchemeOrderedList != null) {
                    mediaSystemLoadReporterNewLoadReporter.reportLoadMetric(MediaSystem.this.mRendererSchemeOrderedList, MediaSystem.this.mResolutionChain);
                } else {
                    DLog.errorf("Failed to load the playback evaluator");
                }
            }
        });
        threadPoolExecutorBuild.shutdown();
    }

    public void waitOnInitializationUninterruptibly() {
        this.mInitializationLatch.waitOnInitializationUninterruptibly();
    }

    public boolean isInitialized() {
        return this.mInitializationLatch.isInitialized();
    }

    @Nonnull
    public DownloadService getDownloadService() {
        return PrioritizingDownloadService.getInstance();
    }

    @Nonnull
    public VideoPresentationFactory getVideoPresentationFactory() {
        this.mInitializationLatch.waitOnInitializationUninterruptibly();
        return this.mMediaSystemComponent.getVideoPresentationFactory();
    }

    @Nonnull
    public PlaybackDownloadExecutorFactory getPlaybackDownloadExecutorFactory() {
        this.mInitializationLatch.waitOnInitializationUninterruptibly();
        return this.mMediaSystemComponent.getPlaybackDownloadExecutorFactory();
    }

    @Nonnull
    public VideoCacheManager getVideoCacheManager() {
        this.mInitializationLatch.waitOnInitializationUninterruptibly();
        return this.mMediaSystemComponent.getVideoCacheManager();
    }

    @Nonnull
    public ClientCacheConfig getClientCacheConfig() {
        this.mInitializationLatch.waitOnInitializationUninterruptibly();
        return this.mMediaSystemComponent.getClientCacheConfig();
    }

    @Nonnull
    public EventReporterFactory getEventReporterFactory() {
        this.mInitializationLatch.waitOnInitializationUninterruptibly();
        return this.mMediaSystemComponent.getEventReporterFactory();
    }

    @Nonnull
    public PlaybackMediaEventReporters.Factory getPlaybackMediaEventReportersFactory() {
        this.mInitializationLatch.waitOnInitializationUninterruptibly();
        return this.mMediaSystemComponent.getPlaybackMediaEventReportersFactory();
    }

    @Nonnull
    public AloysiusInitializer getAloysiusInitializer() {
        this.mInitializationLatch.waitOnInitializationUninterruptibly();
        return this.mAloysiusInitializer;
    }

    @Nonnull
    public DeviceIdentity getDeviceIdentity() {
        return this.mDeviceIdentity;
    }

    @Nonnull
    public String getPlayerName() {
        return this.mPlayerName;
    }

    @Nullable
    public String getPlayerSdkVersion() {
        return this.mPlayerSdkVersion;
    }

    @Nonnull
    public BaseDrmSystem getBaseDrmSystem() {
        this.mInitializationLatch.waitOnInitializationUninterruptibly();
        return this.mBaseDrmSystem.get();
    }

    public MediaSystemComponent getMediaSystemComponent() {
        this.mInitializationLatch.waitOnInitializationUninterruptibly();
        return this.mMediaSystemComponent;
    }

    static class LoadReporterFactory {
        LoadReporterFactory() {
        }

        public MediaSystemLoadReporter newLoadReporter(Context context, EventReporterFactory eventReporterFactory, String str) {
            return new MediaSystemLoadReporter(context, eventReporterFactory, str);
        }
    }

    static class MediaSystemComponentFactory {
        MediaSystemComponentFactory() {
        }

        MediaSystemComponent newMediaSystemComponent(@Nonnull AdvertisingIdCollector advertisingIdCollector) {
            return DaggerMediaSystem_MediaSystemComponent.builder().adaptiveStreamingModule_Dagger(new AdaptiveStreamingModule_Dagger(advertisingIdCollector)).build();
        }
    }
}
