package com.amazon.avod.media;

import android.content.Context;
import com.amazon.avod.content.CompositeContentSessionFactory;
import com.amazon.avod.content.CompositeContentSessionFactory_Factory;
import com.amazon.avod.content.ContentSessionFactory;
import com.amazon.avod.content.ContentStore;
import com.amazon.avod.content.DashMiyagiContentSessionFactory;
import com.amazon.avod.content.DashMiyagiContentSessionFactory_Factory;
import com.amazon.avod.content.SmoothStreamingDashHeuristicsContentSesionFactory;
import com.amazon.avod.content.SmoothStreamingDashHeuristicsContentSesionFactory_Factory;
import com.amazon.avod.content.guice.ContentModule_Dagger;
import com.amazon.avod.content.guice.ContentModule_Dagger_ProvideContentStoreFactory;
import com.amazon.avod.content.guice.ContentModule_Dagger_ProvideFileBackedComponentContentStoreFactory;
import com.amazon.avod.content.guice.ContentModule_Dagger_ProvideManifestComponentContentStoreFactory;
import com.amazon.avod.content.guice.ContentModule_Dagger_ProvideMemoryAllocatorFactory;
import com.amazon.avod.content.guice.ContentModule_Dagger_ProvideMemoryBackedComponentContentStoreFactory;
import com.amazon.avod.content.guice.ContentModule_Dagger_ProvideNetworkHistoryManagerFactory;
import com.amazon.avod.content.guice.ContentModule_Dagger_ProvideSingleFileComponentContentStoreFactory;
import com.amazon.avod.content.guice.ContentModule_Dagger_ProvideSmoothStreamingContentStoreFactory;
import com.amazon.avod.content.guice.ContentModule_Dagger_ProvidesMediaComponentFactory;
import com.amazon.avod.content.guice.ContentModule_Dagger_ProvidesMemoryAllocatorNativeLibraryFactory;
import com.amazon.avod.content.smoothstream.storage.CompositeSmoothStreamingContentStore;
import com.amazon.avod.content.smoothstream.storage.CompositeSmoothStreamingContentStore_Factory;
import com.amazon.avod.content.smoothstream.storage.DynamicManifestContentStore;
import com.amazon.avod.content.smoothstream.storage.FileBackedContentStore;
import com.amazon.avod.content.smoothstream.storage.MemoryAllocator;
import com.amazon.avod.content.smoothstream.storage.MemoryBackedContentStore;
import com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore;
import com.amazon.avod.content.smoothstream.storage.singlefile.SingleFileContentStore;
import com.amazon.avod.content.urlvending.ContentUrlFetcher;
import com.amazon.avod.db.DBOpenHelperSupplier;
import com.amazon.avod.download.PlaybackDownloadExecutorFactory;
import com.amazon.avod.drm.BaseDrmSystem;
import com.amazon.avod.drm.DrmLicenseCleaner;
import com.amazon.avod.drm.DrmLicenseCleaner_Factory;
import com.amazon.avod.drm.DrmModule_Dagger;
import com.amazon.avod.drm.DrmModule_Dagger_ProvideBaseDrmSystemFactory;
import com.amazon.avod.drm.DrmModule_Dagger_ProvideLicenseCleanerFactory;
import com.amazon.avod.drm.playready.DrmSystemImpl;
import com.amazon.avod.drm.playready.DrmSystemImpl_Factory;
import com.amazon.avod.drm.playready.PlayReadyLicensingService;
import com.amazon.avod.drm.widevine.WidevineLicensingService;
import com.amazon.avod.media.MediaSystem;
import com.amazon.avod.media.ads.AdvertisingIdCollector;
import com.amazon.avod.media.ads.internal.AdEnabledVideoModule_Dagger;
import com.amazon.avod.media.ads.internal.AdEnabledVideoModule_Dagger_ProvideAdEnabledVideoPlayerFactory;
import com.amazon.avod.media.ads.internal.AdEnabledVideoModule_Dagger_ProvideAdPlanFactoryFactory;
import com.amazon.avod.media.ads.internal.AdEnabledVideoPlayer;
import com.amazon.avod.media.ads.internal.AdManagerBasedAdPlanFactory;
import com.amazon.avod.media.ads.internal.AdManagerBasedAdPlanFactory_Factory;
import com.amazon.avod.media.ads.internal.AdPlanFactory;
import com.amazon.avod.media.ads.internal.AdUriProxy;
import com.amazon.avod.media.ads.internal.AdUriProxy_Factory;
import com.amazon.avod.media.ads.internal.AndroidVideoPlayerV2Cache;
import com.amazon.avod.media.ads.internal.AndroidVideoPlayerV2Cache_Factory;
import com.amazon.avod.media.aloysius.AloysiusInitializer;
import com.amazon.avod.media.contentcache.ContentCachingModule_Dagger;
import com.amazon.avod.media.contentcache.ContentCachingModule_Dagger_ProvideCacheManagerFactory;
import com.amazon.avod.media.contentcache.VideoCacheManager;
import com.amazon.avod.media.contentcache.internal.ClientCacheConfig;
import com.amazon.avod.media.contentcache.internal.PrioritizingVideoCacheManager;
import com.amazon.avod.media.contentcache.internal.PrioritizingVideoCacheManager_Factory;
import com.amazon.avod.media.downloadservice.DownloadService;
import com.amazon.avod.media.events.AloysiusBootstrapLoader;
import com.amazon.avod.media.events.BatchDispatcherListener;
import com.amazon.avod.media.events.MediaEventDispatcher;
import com.amazon.avod.media.events.MediaEventProducer;
import com.amazon.avod.media.events.MediaEventQueue;
import com.amazon.avod.media.events.MediaReportDispatcherServices;
import com.amazon.avod.media.events.PeriodicMediaEventScheduler;
import com.amazon.avod.media.events.clientapi.MediaReportClient;
import com.amazon.avod.media.events.dagger.MediaEventModule_Dagger;
import com.amazon.avod.media.events.dagger.MediaEventModule_Dagger_ProvideAppBootstrapLoaderFactory;
import com.amazon.avod.media.events.dagger.MediaEventModule_Dagger_ProvideBatchDispatcherListenerFactory;
import com.amazon.avod.media.events.dagger.MediaEventModule_Dagger_ProvideDBOpenHelperSupplierFactory;
import com.amazon.avod.media.events.dagger.MediaEventModule_Dagger_ProvideMediaEventDAOFactory;
import com.amazon.avod.media.events.dagger.MediaEventModule_Dagger_ProvideMediaEventDispatcherFactory;
import com.amazon.avod.media.events.dagger.MediaEventModule_Dagger_ProvideMediaEventQueueFactory;
import com.amazon.avod.media.events.dagger.MediaEventModule_Dagger_ProvideMediaReportClientFactory;
import com.amazon.avod.media.events.dagger.MediaEventModule_Dagger_ProvideMediaReportDAOFactory;
import com.amazon.avod.media.events.dagger.MediaEventModule_Dagger_ProvideMediaReportDispatcherServicesFactory;
import com.amazon.avod.media.events.dagger.MediaEventModule_Dagger_ProvideScheduledExecutorServiceFactory;
import com.amazon.avod.media.events.dao.MediaEventDAO;
import com.amazon.avod.media.events.dao.MediaReportDAO;
import com.amazon.avod.media.framework.MediaComponent;
import com.amazon.avod.media.framework.MediaSystemSharedContext;
import com.amazon.avod.media.framework.MediaSystemSharedContext_Factory;
import com.amazon.avod.media.framework.libraries.LoadableNativeLibrary;
import com.amazon.avod.media.framework.libraries.PlaybackNativeLibrariesLoader;
import com.amazon.avod.media.framework.libraries.PlaybackNativeLibrariesLoader_Factory;
import com.amazon.avod.media.framework.network.MultiNetworkHistoryManager;
import com.amazon.avod.media.framework.network.MultiNetworkHistoryManager_Factory;
import com.amazon.avod.media.framework.network.NetworkHistoryManager;
import com.amazon.avod.media.framework.platform.AndroidPlatform;
import com.amazon.avod.media.framework.platform.AndroidPlatform_Factory;
import com.amazon.avod.media.framework.platform.FileSystem;
import com.amazon.avod.media.framework.platform.power.AndroidLockFactory;
import com.amazon.avod.media.framework.platform.power.AndroidLockFactory_Factory;
import com.amazon.avod.media.framework.platform.power.PowerManagementLockFactory;
import com.amazon.avod.media.framework.profiling.MediaProfiler;
import com.amazon.avod.media.framework.volume.VolumeManager;
import com.amazon.avod.media.framework.volume.VolumeManager_Factory;
import com.amazon.avod.media.guice.MediaModule_Dagger;
import com.amazon.avod.media.guice.MediaModule_Dagger_GetHeuristicsConfigurationSynchronizerAsMediaComponentFactory;
import com.amazon.avod.media.guice.MediaModule_Dagger_ProvideAdEnabledVideoPresentationFactoryFactory;
import com.amazon.avod.media.guice.MediaModule_Dagger_ProvideAndroidVideoPresentationFactoryFactory;
import com.amazon.avod.media.guice.MediaModule_Dagger_ProvideAppContextFactory;
import com.amazon.avod.media.guice.MediaModule_Dagger_ProvideConfigurablePlaybackSupportEvaluatorFactory;
import com.amazon.avod.media.guice.MediaModule_Dagger_ProvideContentUrlFetcherFactory;
import com.amazon.avod.media.guice.MediaModule_Dagger_ProvideDownloadServiceFactory;
import com.amazon.avod.media.guice.MediaModule_Dagger_ProvideExecutorServiceFactory;
import com.amazon.avod.media.guice.MediaModule_Dagger_ProvideFileSystemFactory;
import com.amazon.avod.media.guice.MediaModule_Dagger_ProvideImageVideoPresentationFactoryFactory;
import com.amazon.avod.media.guice.MediaModule_Dagger_ProvideMediaProfilerFactory;
import com.amazon.avod.media.guice.MediaModule_Dagger_ProvidePlayReadyLicensingServiceFactory;
import com.amazon.avod.media.guice.MediaModule_Dagger_ProvidePlaybackSupportEvaluatorFactory;
import com.amazon.avod.media.guice.MediaModule_Dagger_ProvidePowerManagementLockFactoryFactory;
import com.amazon.avod.media.guice.MediaModule_Dagger_ProvideVideoPresentationFactoryFactory;
import com.amazon.avod.media.guice.MediaModule_Dagger_ProvideWidevineLicensingServiceFactory;
import com.amazon.avod.media.playback.FirstMatchVideoPresentationFactory;
import com.amazon.avod.media.playback.FirstMatchVideoPresentationFactory_Factory;
import com.amazon.avod.media.playback.QOSCommunicationService;
import com.amazon.avod.media.playback.VideoPresentationFactory;
import com.amazon.avod.media.playback.android.AndroidVideoPlayer;
import com.amazon.avod.media.playback.android.AndroidVideoPlayerDiagnosticsController_Factory;
import com.amazon.avod.media.playback.android.AndroidVideoPlayer_Factory;
import com.amazon.avod.media.playback.android.AndroidVideoPresentationFactory;
import com.amazon.avod.media.playback.android.AndroidVideoPresentationFactory_Factory;
import com.amazon.avod.media.playback.image.ImageVideoPlayer;
import com.amazon.avod.media.playback.image.ImageVideoPlayerDiagnosticsController_Factory;
import com.amazon.avod.media.playback.image.ImageVideoPlayer_Factory;
import com.amazon.avod.media.playback.image.ImageVideoPresentationFactory;
import com.amazon.avod.media.playback.image.ImageVideoPresentationFactory_Factory;
import com.amazon.avod.media.playback.reporting.EventReporterFactory;
import com.amazon.avod.media.playback.reporting.VideoPresentationEventReporter;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusDeviceReporter;
import com.amazon.avod.media.playback.reporting.aloysius.PlaybackMediaEventReporters;
import com.amazon.avod.media.playback.reporting.aloysius.PlaybackMediaEventReporters_Factory_Factory;
import com.amazon.avod.media.playback.reporting.aloysius.SyeAloysiusReporters;
import com.amazon.avod.media.playback.reporting.aloysius.SyeAloysiusReporters_Factory_Factory;
import com.amazon.avod.media.playback.reporting.aloysius.dagger.AloysiusReporterModule_Dagger;
import com.amazon.avod.media.playback.reporting.aloysius.dagger.AloysiusReporterModule_Dagger_ProvideAloysiusAudioReporterEventProducerFactory;
import com.amazon.avod.media.playback.reporting.aloysius.dagger.AloysiusReporterModule_Dagger_ProvideAloysiusDeviceReporterEventProducerFactory;
import com.amazon.avod.media.playback.reporting.aloysius.dagger.AloysiusReporterModule_Dagger_ProvideAloysiusDeviceReporterFactory;
import com.amazon.avod.media.playback.reporting.aloysius.dagger.AloysiusReporterModule_Dagger_ProvideAloysiusDisplayReporterFactory;
import com.amazon.avod.media.playback.reporting.aloysius.dagger.AloysiusReporterModule_Dagger_ProvideAloysiusNetworkReporterEventProducerFactory;
import com.amazon.avod.media.playback.reporting.aloysius.dagger.AloysiusReporterModule_Dagger_ProvideAloysiusWeblabReporterFactory;
import com.amazon.avod.media.playback.reporting.aloysius.dagger.AloysiusReporterModule_Dagger_ProvidePeriodicMediaEventSchedulerFactory;
import com.amazon.avod.media.playback.reporting.aloysius.dagger.AloysiusReporterModule_Dagger_ProvideQOSCommunicationServiceFactory;
import com.amazon.avod.media.playback.support.ConfigurablePlaybackSupportEvaluator;
import com.amazon.avod.media.playback.support.PlaybackSupportEvaluator;
import com.amazon.avod.media.playback.support.RendererSchemeController;
import com.amazon.avod.media.playback.support.RendererSchemeController_Factory;
import com.amazon.avod.media.service.AVODContentUrlFetcher;
import com.amazon.avod.media.service.AVODContentUrlFetcher_Factory;
import com.amazon.avod.media.service.PlayReadyAvodLicensingService;
import com.amazon.avod.media.service.PlayReadyAvodLicensingService_Factory;
import com.amazon.avod.media.service.WidevineAvodLicensingService;
import com.amazon.avod.media.service.WidevineAvodLicensingService_Factory;
import com.amazon.avod.media.service.playbackoptimizationservice.DefaultHeuristicsConfigurationSynchronizer;
import com.amazon.avod.media.service.playbackoptimizationservice.DefaultHeuristicsConfigurationSynchronizer_Factory;
import com.amazon.avod.playback.capability.BatteryInfoProvider;
import com.amazon.avod.playback.capability.BatteryInfoProvider_Factory;
import com.amazon.avod.playback.session.AdEnabledVideoPresentationFactory;
import com.amazon.avod.playback.session.AdEnabledVideoPresentationFactory_Factory;
import com.amazon.avod.playback.session.AmazonVideoPlayerFactory;
import com.amazon.avod.playback.session.AmazonVideoPlayerFactory_Factory;
import com.amazon.avod.playback.session.PlaybackSessionFactory;
import com.amazon.avod.playback.session.PlaybackSessionFactory_Factory;
import com.amazon.avod.playback.smoothstream.AdaptiveStreamingModule_Dagger;
import com.amazon.avod.playback.smoothstream.AdaptiveStreamingModule_Dagger_ProvideAdIdProviderFactory;
import com.amazon.avod.playback.smoothstream.AdaptiveStreamingModule_Dagger_ProvideContentSessionFactoryFactory;
import com.amazon.avod.playback.smoothstream.AdaptiveStreamingModule_Dagger_ProvidePlaybackClientsConfigFactory;
import com.amazon.avod.playback.smoothstream.AdaptiveStreamingModule_Dagger_ProvideVideoPresentationEventReporterFactory;
import com.amazon.avod.playback.smoothstream.SmoothStreamModule_Dagger;
import com.amazon.avod.playback.smoothstream.SmoothStreamModule_Dagger_ProvideSmoothStreamNativeLibraryFactory;
import com.amazon.avod.playback.sye.SyeVideoPresentationFactory;
import com.amazon.avod.playback.sye.SyeVideoPresentationFactory_Factory;
import com.amazon.avod.playerheuristics.PlayerHeuristicsModule_Dagger;
import com.amazon.avod.playerheuristics.PlayerHeuristicsModule_Dagger_ProvidePlayerHeuristicsNativeLibraryFactory;
import com.amazon.avod.qos.QoSModule_Dagger;
import com.amazon.avod.qos.QoSModule_Dagger_ProvideEventModelMediaComponentFactory;
import com.amazon.avod.qos.QoSModule_Dagger_ProvideEventReporterFactoryFactory;
import com.amazon.avod.qos.SimpleVideoPresentationEventReporter;
import com.amazon.avod.qos.SimpleVideoPresentationEventReporter_Factory;
import com.amazon.avod.qos.SmoothStreamingVideoPresentationEventReporter;
import com.amazon.avod.qos.SmoothStreamingVideoPresentationEventReporter_Factory;
import com.amazon.avod.qos.internal.event.QosEventModelMediaComponent;
import com.amazon.avod.qos.internal.event.QosEventModelMediaComponent_Factory;
import com.amazon.avod.qos.listeners.QoSEventManagerFactory;
import com.amazon.avod.qos.listeners.QoSEventManagerFactory_Factory;
import com.amazon.avod.qos.reporter.QosEventReporterFactory;
import com.amazon.avod.qos.reporter.QosEventReporterFactory_Factory;
import com.amazon.avod.smoothstream.DashContentSessionFactory;
import com.amazon.avod.smoothstream.DashContentSessionFactory_Factory;
import com.amazon.avod.smoothstream.DashDownloadContentSessionFactory;
import com.amazon.avod.smoothstream.DashDownloadContentSessionFactory_Factory;
import com.amazon.avod.smoothstream.DashModule_Dagger;
import com.amazon.avod.smoothstream.DashModule_Dagger_ProvideDashNativeLibraryFactory;
import com.google.common.collect.ImmutableSet;
import dagger.internal.DelegateFactory;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.SetFactory;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class DaggerMediaSystem_MediaSystemComponent implements MediaSystem.MediaSystemComponent {
    private Provider<AVODContentUrlFetcher> aVODContentUrlFetcherProvider;
    private Provider<AdEnabledVideoPresentationFactory> adEnabledVideoPresentationFactoryProvider;
    private Provider<AdManagerBasedAdPlanFactory> adManagerBasedAdPlanFactoryProvider;
    private Provider<AdUriProxy> adUriProxyProvider;
    private AloysiusReporterModule_Dagger aloysiusReporterModule_Dagger;
    private Provider<AmazonVideoPlayerFactory> amazonVideoPlayerFactoryProvider;
    private Provider<AndroidLockFactory> androidLockFactoryProvider;
    private Provider<AndroidVideoPlayer> androidVideoPlayerProvider;
    private Provider<AndroidVideoPlayerV2Cache> androidVideoPlayerV2CacheProvider;
    private Provider<AndroidVideoPresentationFactory> androidVideoPresentationFactoryProvider;
    private Provider<BatteryInfoProvider> batteryInfoProvider;
    private Provider<CompositeContentSessionFactory> compositeContentSessionFactoryProvider;
    private Provider<CompositeSmoothStreamingContentStore> compositeSmoothStreamingContentStoreProvider;
    private ContentModule_Dagger contentModule_Dagger;
    private Provider<DashContentSessionFactory> dashContentSessionFactoryProvider;
    private Provider<DashDownloadContentSessionFactory> dashDownloadContentSessionFactoryProvider;
    private Provider<DashMiyagiContentSessionFactory> dashMiyagiContentSessionFactoryProvider;
    private DashModule_Dagger dashModule_Dagger;
    private Provider<DefaultHeuristicsConfigurationSynchronizer> defaultHeuristicsConfigurationSynchronizerProvider;
    private Provider<DrmLicenseCleaner> drmLicenseCleanerProvider;
    private Provider<DrmSystemImpl> drmSystemImplProvider;
    private Provider<SyeAloysiusReporters.Factory> factoryProvider;
    private Provider<PlaybackMediaEventReporters.Factory> factoryProvider2;
    private Provider<FirstMatchVideoPresentationFactory> firstMatchVideoPresentationFactoryProvider;
    private Provider<MediaComponent> getHeuristicsConfigurationSynchronizerAsMediaComponentProvider;
    private Provider<ImageVideoPlayer> imageVideoPlayerProvider;
    private Provider<ImageVideoPresentationFactory> imageVideoPresentationFactoryProvider;
    private MediaModule_Dagger mediaModule_Dagger;
    private Provider<MediaSystemSharedContext> mediaSystemSharedContextProvider;
    private Provider<MultiNetworkHistoryManager> multiNetworkHistoryManagerProvider;
    private Provider<PlayReadyAvodLicensingService> playReadyAvodLicensingServiceProvider;
    private Provider<PlaybackNativeLibrariesLoader> playbackNativeLibrariesLoaderProvider;
    private Provider<PlaybackSessionFactory> playbackSessionFactoryProvider;
    private PlayerHeuristicsModule_Dagger playerHeuristicsModule_Dagger;
    private Provider<PrioritizingVideoCacheManager> prioritizingVideoCacheManagerProvider;
    private Provider<AdEnabledVideoPlayer> provideAdEnabledVideoPlayerProvider;
    private Provider<VideoPresentationFactory> provideAdEnabledVideoPresentationFactoryProvider;
    private Provider<AdvertisingIdCollector> provideAdIdProvider;
    private Provider<AdPlanFactory> provideAdPlanFactoryProvider;
    private Provider<MediaEventProducer> provideAloysiusAudioReporterEventProducerProvider;
    private Provider<MediaEventProducer> provideAloysiusDeviceReporterEventProducerProvider;
    private Provider<AloysiusDeviceReporter> provideAloysiusDeviceReporterProvider;
    private Provider<MediaEventProducer> provideAloysiusDisplayReporterProvider;
    private Provider<MediaEventProducer> provideAloysiusNetworkReporterEventProducerProvider;
    private Provider<MediaEventProducer> provideAloysiusWeblabReporterProvider;
    private Provider<VideoPresentationFactory> provideAndroidVideoPresentationFactoryProvider;
    private Provider<AloysiusBootstrapLoader> provideAppBootstrapLoaderProvider;
    private Provider<Context> provideAppContextProvider;
    private Provider<BaseDrmSystem> provideBaseDrmSystemProvider;
    private Provider<BatchDispatcherListener> provideBatchDispatcherListenerProvider;
    private Provider<VideoCacheManager> provideCacheManagerProvider;
    private Provider<ConfigurablePlaybackSupportEvaluator> provideConfigurablePlaybackSupportEvaluatorProvider;
    private Provider<ContentSessionFactory> provideContentSessionFactoryProvider;
    private Provider<ContentStore> provideContentStoreProvider;
    private Provider<ContentUrlFetcher> provideContentUrlFetcherProvider;
    private Provider<DBOpenHelperSupplier> provideDBOpenHelperSupplierProvider;
    private Provider<DownloadService> provideDownloadServiceProvider;
    private Provider<MediaComponent> provideEventModelMediaComponentProvider;
    private Provider<EventReporterFactory> provideEventReporterFactoryProvider;
    private Provider<ExecutorService> provideExecutorServiceProvider;
    private Provider<FileBackedContentStore> provideFileBackedComponentContentStoreProvider;
    private Provider<FileSystem> provideFileSystemProvider;
    private Provider<VideoPresentationFactory> provideImageVideoPresentationFactoryProvider;
    private Provider<MediaComponent> provideLicenseCleanerProvider;
    private Provider<DynamicManifestContentStore> provideManifestComponentContentStoreProvider;
    private Provider<MediaEventDAO> provideMediaEventDAOProvider;
    private Provider<MediaEventDispatcher> provideMediaEventDispatcherProvider;
    private Provider<MediaEventQueue> provideMediaEventQueueProvider;
    private Provider<MediaProfiler> provideMediaProfilerProvider;
    private Provider<MediaReportClient> provideMediaReportClientProvider;
    private Provider<MediaReportDAO> provideMediaReportDAOProvider;
    private Provider<MediaReportDispatcherServices> provideMediaReportDispatcherServicesProvider;
    private Provider<MemoryAllocator> provideMemoryAllocatorProvider;
    private Provider<MemoryBackedContentStore> provideMemoryBackedComponentContentStoreProvider;
    private Provider<NetworkHistoryManager> provideNetworkHistoryManagerProvider;
    private Provider<PeriodicMediaEventScheduler> providePeriodicMediaEventSchedulerProvider;
    private Provider<PlayReadyLicensingService> providePlayReadyLicensingServiceProvider;
    private Provider<ClientCacheConfig> providePlaybackClientsConfigProvider;
    private Provider<PlaybackSupportEvaluator> providePlaybackSupportEvaluatorProvider;
    private Provider<PowerManagementLockFactory> providePowerManagementLockFactoryProvider;
    private Provider<QOSCommunicationService> provideQOSCommunicationServiceProvider;
    private Provider<ScheduledExecutorService> provideScheduledExecutorServiceProvider;
    private Provider<SingleFileContentStore> provideSingleFileComponentContentStoreProvider;
    private Provider<SmoothStreamingContentStore> provideSmoothStreamingContentStoreProvider;
    private Provider<VideoPresentationEventReporter> provideVideoPresentationEventReporterProvider;
    private Provider<WidevineLicensingService> provideWidevineLicensingServiceProvider;
    private Provider<MediaComponent> providesMediaComponentProvider;
    private Provider<QoSEventManagerFactory> qoSEventManagerFactoryProvider;
    private QoSModule_Dagger qoSModule_Dagger;
    private Provider<QosEventModelMediaComponent> qosEventModelMediaComponentProvider;
    private Provider<QosEventReporterFactory> qosEventReporterFactoryProvider;
    private Provider<RendererSchemeController> rendererSchemeControllerProvider;
    private Provider<Set<MediaComponent>> setOfMediaComponentProvider;
    private Provider<Set<MediaEventProducer>> setOfMediaEventProducerProvider;
    private Provider<Set<VideoPresentationFactory>> setOfVideoPresentationFactoryProvider;
    private Provider<SimpleVideoPresentationEventReporter> simpleVideoPresentationEventReporterProvider;
    private SmoothStreamModule_Dagger smoothStreamModule_Dagger;
    private Provider<SmoothStreamingDashHeuristicsContentSesionFactory> smoothStreamingDashHeuristicsContentSesionFactoryProvider;
    private Provider<SmoothStreamingVideoPresentationEventReporter> smoothStreamingVideoPresentationEventReporterProvider;
    private Provider<SyeVideoPresentationFactory> syeVideoPresentationFactoryProvider;
    private Provider<VolumeManager> volumeManagerProvider;
    private Provider<WidevineAvodLicensingService> widevineAvodLicensingServiceProvider;

    private DaggerMediaSystem_MediaSystemComponent(Builder builder) {
        initialize(builder);
        initialize2(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    private Context getContext() {
        return (Context) Preconditions.checkNotNull(MediaModule_Dagger_ProvideAppContextFactory.proxyProvideAppContext(this.mediaModule_Dagger, this.mediaSystemSharedContextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    private BatteryInfoProvider getBatteryInfoProvider() {
        return new BatteryInfoProvider(getContext());
    }

    private QosEventReporterFactory getQosEventReporterFactory() {
        return new QosEventReporterFactory(this.mediaSystemSharedContextProvider.get(), getBatteryInfoProvider());
    }

    private MediaEventProducer getMediaEventProducer() {
        return (MediaEventProducer) Preconditions.checkNotNull(this.aloysiusReporterModule_Dagger.provideAloysiusNetworkReporterEventProducer(this.provideMediaEventQueueProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    private MediaEventProducer getMediaEventProducer2() {
        return (MediaEventProducer) Preconditions.checkNotNull(this.aloysiusReporterModule_Dagger.provideAloysiusDeviceReporterEventProducer(this.provideMediaEventQueueProvider.get(), getContext(), this.rendererSchemeControllerProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    private MediaEventProducer getMediaEventProducer3() {
        return (MediaEventProducer) Preconditions.checkNotNull(this.aloysiusReporterModule_Dagger.provideAloysiusDisplayReporter(this.provideMediaEventQueueProvider.get(), getContext()), "Cannot return null from a non-@Nullable @Provides method");
    }

    private MediaEventProducer getMediaEventProducer4() {
        return (MediaEventProducer) Preconditions.checkNotNull(this.aloysiusReporterModule_Dagger.provideAloysiusWeblabReporter(this.provideMediaEventQueueProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    private Set<MediaEventProducer> getSetOfMediaEventProducer() {
        return ImmutableSet.of(getMediaEventProducer(), this.provideAloysiusAudioReporterEventProducerProvider.get(), getMediaEventProducer2(), getMediaEventProducer3(), getMediaEventProducer4());
    }

    private AloysiusDeviceReporter getAloysiusDeviceReporter() {
        return (AloysiusDeviceReporter) Preconditions.checkNotNull(this.aloysiusReporterModule_Dagger.provideAloysiusDeviceReporter(this.provideMediaEventQueueProvider.get(), getContext(), this.rendererSchemeControllerProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    private FileSystem getFileSystem() {
        return (FileSystem) Preconditions.checkNotNull(MediaModule_Dagger_ProvideFileSystemFactory.proxyProvideFileSystem(this.mediaModule_Dagger, new AndroidPlatform()), "Cannot return null from a non-@Nullable @Provides method");
    }

    private Set<LoadableNativeLibrary> getSetOfLoadableNativeLibrary() {
        return ImmutableSet.of(Preconditions.checkNotNull(ContentModule_Dagger_ProvidesMemoryAllocatorNativeLibraryFactory.proxyProvidesMemoryAllocatorNativeLibrary(this.contentModule_Dagger), "Cannot return null from a non-@Nullable @Provides method"), Preconditions.checkNotNull(DashModule_Dagger_ProvideDashNativeLibraryFactory.proxyProvideDashNativeLibrary(this.dashModule_Dagger), "Cannot return null from a non-@Nullable @Provides method"), Preconditions.checkNotNull(SmoothStreamModule_Dagger_ProvideSmoothStreamNativeLibraryFactory.proxyProvideSmoothStreamNativeLibrary(this.smoothStreamModule_Dagger), "Cannot return null from a non-@Nullable @Provides method"), Preconditions.checkNotNull(PlayerHeuristicsModule_Dagger_ProvidePlayerHeuristicsNativeLibraryFactory.proxyProvidePlayerHeuristicsNativeLibrary(this.playerHeuristicsModule_Dagger), "Cannot return null from a non-@Nullable @Provides method"));
    }

    private AloysiusInitializer getAloysiusInitializer() {
        return new AloysiusInitializer(this.provideAppBootstrapLoaderProvider.get(), this.provideMediaEventDispatcherProvider.get(), this.provideMediaEventQueueProvider.get());
    }

    private CompositeSmoothStreamingContentStore getCompositeSmoothStreamingContentStore() {
        return new CompositeSmoothStreamingContentStore(this.provideMemoryBackedComponentContentStoreProvider.get(), this.provideFileBackedComponentContentStoreProvider.get(), this.provideManifestComponentContentStoreProvider.get());
    }

    private AVODContentUrlFetcher getAVODContentUrlFetcher() {
        return new AVODContentUrlFetcher(this.provideMediaProfilerProvider.get(), this.providePlaybackSupportEvaluatorProvider.get(), this.provideAdIdProvider.get(), this.provideQOSCommunicationServiceProvider.get());
    }

    private void initialize(Builder builder) {
        this.mediaModule_Dagger = builder.mediaModule_Dagger;
        this.mediaSystemSharedContextProvider = DoubleCheck.provider(MediaSystemSharedContext_Factory.create());
        this.provideAppContextProvider = MediaModule_Dagger_ProvideAppContextFactory.create(builder.mediaModule_Dagger, this.mediaSystemSharedContextProvider);
        this.provideDBOpenHelperSupplierProvider = DoubleCheck.provider(MediaEventModule_Dagger_ProvideDBOpenHelperSupplierFactory.create(builder.mediaEventModule_Dagger, this.provideAppContextProvider));
        this.provideMediaEventDAOProvider = DoubleCheck.provider(MediaEventModule_Dagger_ProvideMediaEventDAOFactory.create(builder.mediaEventModule_Dagger, this.provideDBOpenHelperSupplierProvider));
        this.provideMediaEventQueueProvider = DoubleCheck.provider(MediaEventModule_Dagger_ProvideMediaEventQueueFactory.create(builder.mediaEventModule_Dagger, this.provideMediaEventDAOProvider));
        this.provideMediaReportDAOProvider = DoubleCheck.provider(MediaEventModule_Dagger_ProvideMediaReportDAOFactory.create(builder.mediaEventModule_Dagger, this.provideDBOpenHelperSupplierProvider, this.provideAppContextProvider));
        this.provideMediaReportClientProvider = DoubleCheck.provider(MediaEventModule_Dagger_ProvideMediaReportClientFactory.create(builder.mediaEventModule_Dagger));
        this.provideMediaReportDispatcherServicesProvider = DoubleCheck.provider(MediaEventModule_Dagger_ProvideMediaReportDispatcherServicesFactory.create(builder.mediaEventModule_Dagger, this.provideMediaReportClientProvider));
        this.provideScheduledExecutorServiceProvider = DoubleCheck.provider(MediaEventModule_Dagger_ProvideScheduledExecutorServiceFactory.create(builder.mediaEventModule_Dagger));
        Factory<BatteryInfoProvider> factoryCreate = BatteryInfoProvider_Factory.create(this.provideAppContextProvider);
        this.batteryInfoProvider = factoryCreate;
        this.qosEventReporterFactoryProvider = QosEventReporterFactory_Factory.create(this.mediaSystemSharedContextProvider, factoryCreate);
        this.provideAloysiusNetworkReporterEventProducerProvider = AloysiusReporterModule_Dagger_ProvideAloysiusNetworkReporterEventProducerFactory.create(builder.aloysiusReporterModule_Dagger, this.provideMediaEventQueueProvider);
        this.volumeManagerProvider = VolumeManager_Factory.create(this.provideAppContextProvider);
        this.provideAloysiusAudioReporterEventProducerProvider = DoubleCheck.provider(AloysiusReporterModule_Dagger_ProvideAloysiusAudioReporterEventProducerFactory.create(builder.aloysiusReporterModule_Dagger, this.provideMediaEventQueueProvider, this.volumeManagerProvider));
        this.rendererSchemeControllerProvider = DoubleCheck.provider(RendererSchemeController_Factory.create());
        this.provideAloysiusDeviceReporterEventProducerProvider = AloysiusReporterModule_Dagger_ProvideAloysiusDeviceReporterEventProducerFactory.create(builder.aloysiusReporterModule_Dagger, this.provideMediaEventQueueProvider, this.provideAppContextProvider, this.rendererSchemeControllerProvider);
        this.provideAloysiusDisplayReporterProvider = AloysiusReporterModule_Dagger_ProvideAloysiusDisplayReporterFactory.create(builder.aloysiusReporterModule_Dagger, this.provideMediaEventQueueProvider, this.provideAppContextProvider);
        this.provideAloysiusWeblabReporterProvider = AloysiusReporterModule_Dagger_ProvideAloysiusWeblabReporterFactory.create(builder.aloysiusReporterModule_Dagger, this.provideMediaEventQueueProvider);
        this.setOfMediaEventProducerProvider = SetFactory.builder(5, 0).addProvider(this.provideAloysiusNetworkReporterEventProducerProvider).addProvider(this.provideAloysiusAudioReporterEventProducerProvider).addProvider(this.provideAloysiusDeviceReporterEventProducerProvider).addProvider(this.provideAloysiusDisplayReporterProvider).addProvider(this.provideAloysiusWeblabReporterProvider).build();
        this.provideBatchDispatcherListenerProvider = DoubleCheck.provider(MediaEventModule_Dagger_ProvideBatchDispatcherListenerFactory.create(builder.mediaEventModule_Dagger, this.setOfMediaEventProducerProvider));
        this.provideMediaEventDispatcherProvider = DoubleCheck.provider(MediaEventModule_Dagger_ProvideMediaEventDispatcherFactory.create(builder.mediaEventModule_Dagger, this.provideMediaEventQueueProvider, this.provideMediaReportDAOProvider, this.provideMediaReportDispatcherServicesProvider, this.provideScheduledExecutorServiceProvider, this.qosEventReporterFactoryProvider, this.provideBatchDispatcherListenerProvider));
        this.providePeriodicMediaEventSchedulerProvider = AloysiusReporterModule_Dagger_ProvidePeriodicMediaEventSchedulerFactory.create(builder.aloysiusReporterModule_Dagger);
        this.provideAppBootstrapLoaderProvider = DoubleCheck.provider(MediaEventModule_Dagger_ProvideAppBootstrapLoaderFactory.create(builder.mediaEventModule_Dagger, this.provideMediaReportClientProvider, this.provideMediaEventDispatcherProvider, this.provideMediaReportDispatcherServicesProvider, this.provideScheduledExecutorServiceProvider));
        Factory<AloysiusDeviceReporter> factoryCreate2 = AloysiusReporterModule_Dagger_ProvideAloysiusDeviceReporterFactory.create(builder.aloysiusReporterModule_Dagger, this.provideMediaEventQueueProvider, this.provideAppContextProvider, this.rendererSchemeControllerProvider);
        this.provideAloysiusDeviceReporterProvider = factoryCreate2;
        this.factoryProvider = SyeAloysiusReporters_Factory_Factory.create(this.provideMediaEventQueueProvider, this.provideMediaEventDispatcherProvider, this.providePeriodicMediaEventSchedulerProvider, this.setOfMediaEventProducerProvider, this.provideAppBootstrapLoaderProvider, this.provideAppContextProvider, factoryCreate2);
        this.provideEventReporterFactoryProvider = QoSModule_Dagger_ProvideEventReporterFactoryFactory.create(builder.qoSModule_Dagger, this.qosEventReporterFactoryProvider);
        Provider<AdvertisingIdCollector> provider = DoubleCheck.provider(AdaptiveStreamingModule_Dagger_ProvideAdIdProviderFactory.create(builder.adaptiveStreamingModule_Dagger));
        this.provideAdIdProvider = provider;
        this.syeVideoPresentationFactoryProvider = SyeVideoPresentationFactory_Factory.create(this.factoryProvider, this.provideAppContextProvider, this.provideEventReporterFactoryProvider, provider, this.mediaSystemSharedContextProvider);
        this.imageVideoPlayerProvider = ImageVideoPlayer_Factory.create(this.mediaSystemSharedContextProvider);
        this.simpleVideoPresentationEventReporterProvider = SimpleVideoPresentationEventReporter_Factory.create(this.qosEventReporterFactoryProvider);
        Factory<VideoPresentationEventReporter> factoryCreate3 = AdaptiveStreamingModule_Dagger_ProvideVideoPresentationEventReporterFactory.create(builder.adaptiveStreamingModule_Dagger, this.simpleVideoPresentationEventReporterProvider);
        this.provideVideoPresentationEventReporterProvider = factoryCreate3;
        this.imageVideoPresentationFactoryProvider = ImageVideoPresentationFactory_Factory.create(this.imageVideoPlayerProvider, factoryCreate3, ImageVideoPlayerDiagnosticsController_Factory.create(), this.provideAppContextProvider);
        this.provideImageVideoPresentationFactoryProvider = MediaModule_Dagger_ProvideImageVideoPresentationFactoryFactory.create(builder.mediaModule_Dagger, this.imageVideoPresentationFactoryProvider);
        Provider<QOSCommunicationService> provider2 = DoubleCheck.provider(AloysiusReporterModule_Dagger_ProvideQOSCommunicationServiceFactory.create(builder.aloysiusReporterModule_Dagger));
        this.provideQOSCommunicationServiceProvider = provider2;
        this.androidVideoPlayerProvider = AndroidVideoPlayer_Factory.create(this.mediaSystemSharedContextProvider, provider2);
        this.androidVideoPlayerV2CacheProvider = DoubleCheck.provider(AndroidVideoPlayerV2Cache_Factory.create(this.mediaSystemSharedContextProvider, this.provideQOSCommunicationServiceProvider));
        this.androidVideoPresentationFactoryProvider = AndroidVideoPresentationFactory_Factory.create(this.androidVideoPlayerProvider, this.provideVideoPresentationEventReporterProvider, AndroidVideoPlayerDiagnosticsController_Factory.create(), this.mediaSystemSharedContextProvider, this.provideQOSCommunicationServiceProvider, this.androidVideoPlayerV2CacheProvider);
        this.provideAndroidVideoPresentationFactoryProvider = MediaModule_Dagger_ProvideAndroidVideoPresentationFactoryFactory.create(builder.mediaModule_Dagger, this.androidVideoPresentationFactoryProvider);
        this.multiNetworkHistoryManagerProvider = DoubleCheck.provider(MultiNetworkHistoryManager_Factory.create(this.provideAppContextProvider));
        this.provideNetworkHistoryManagerProvider = DoubleCheck.provider(ContentModule_Dagger_ProvideNetworkHistoryManagerFactory.create(builder.contentModule_Dagger, this.multiNetworkHistoryManagerProvider));
        this.provideDownloadServiceProvider = DoubleCheck.provider(MediaModule_Dagger_ProvideDownloadServiceFactory.create(builder.mediaModule_Dagger));
        this.provideFileSystemProvider = MediaModule_Dagger_ProvideFileSystemFactory.create(builder.mediaModule_Dagger, AndroidPlatform_Factory.create());
        this.provideMediaProfilerProvider = DoubleCheck.provider(MediaModule_Dagger_ProvideMediaProfilerFactory.create(builder.mediaModule_Dagger));
        this.playbackNativeLibrariesLoaderProvider = DoubleCheck.provider(PlaybackNativeLibrariesLoader_Factory.create(this.mediaSystemSharedContextProvider));
        this.provideMemoryAllocatorProvider = DoubleCheck.provider(ContentModule_Dagger_ProvideMemoryAllocatorFactory.create(builder.contentModule_Dagger, this.playbackNativeLibrariesLoaderProvider));
        this.provideExecutorServiceProvider = MediaModule_Dagger_ProvideExecutorServiceFactory.create(builder.mediaModule_Dagger, this.mediaSystemSharedContextProvider);
        this.provideFileBackedComponentContentStoreProvider = DoubleCheck.provider(ContentModule_Dagger_ProvideFileBackedComponentContentStoreFactory.create(builder.contentModule_Dagger, this.provideAppContextProvider, this.provideFileSystemProvider, this.provideExecutorServiceProvider));
        this.provideMemoryBackedComponentContentStoreProvider = DoubleCheck.provider(ContentModule_Dagger_ProvideMemoryBackedComponentContentStoreFactory.create(builder.contentModule_Dagger, this.provideMemoryAllocatorProvider, this.provideFileBackedComponentContentStoreProvider));
        Provider<DynamicManifestContentStore> provider3 = DoubleCheck.provider(ContentModule_Dagger_ProvideManifestComponentContentStoreFactory.create(builder.contentModule_Dagger));
        this.provideManifestComponentContentStoreProvider = provider3;
        this.compositeSmoothStreamingContentStoreProvider = CompositeSmoothStreamingContentStore_Factory.create(this.provideMemoryBackedComponentContentStoreProvider, this.provideFileBackedComponentContentStoreProvider, provider3);
        this.provideSmoothStreamingContentStoreProvider = ContentModule_Dagger_ProvideSmoothStreamingContentStoreFactory.create(builder.contentModule_Dagger, this.compositeSmoothStreamingContentStoreProvider);
        Provider<SingleFileContentStore> provider4 = DoubleCheck.provider(ContentModule_Dagger_ProvideSingleFileComponentContentStoreFactory.create(builder.contentModule_Dagger, this.provideFileBackedComponentContentStoreProvider));
        this.provideSingleFileComponentContentStoreProvider = provider4;
        this.dashContentSessionFactoryProvider = DoubleCheck.provider(DashContentSessionFactory_Factory.create(this.provideNetworkHistoryManagerProvider, this.mediaSystemSharedContextProvider, this.provideDownloadServiceProvider, this.provideFileSystemProvider, this.provideMediaProfilerProvider, this.provideSmoothStreamingContentStoreProvider, this.playbackNativeLibrariesLoaderProvider, this.rendererSchemeControllerProvider, this.provideFileBackedComponentContentStoreProvider, provider4));
        this.dashMiyagiContentSessionFactoryProvider = DashMiyagiContentSessionFactory_Factory.create(this.provideNetworkHistoryManagerProvider, this.mediaSystemSharedContextProvider, this.provideDownloadServiceProvider, this.provideFileSystemProvider, this.provideMediaProfilerProvider, this.provideSmoothStreamingContentStoreProvider, this.playbackNativeLibrariesLoaderProvider, this.rendererSchemeControllerProvider, this.provideFileBackedComponentContentStoreProvider);
        this.smoothStreamingDashHeuristicsContentSesionFactoryProvider = DoubleCheck.provider(SmoothStreamingDashHeuristicsContentSesionFactory_Factory.create(this.provideNetworkHistoryManagerProvider, this.mediaSystemSharedContextProvider, this.provideDownloadServiceProvider, this.provideFileSystemProvider, this.provideMediaProfilerProvider, this.provideSmoothStreamingContentStoreProvider, this.playbackNativeLibrariesLoaderProvider, this.rendererSchemeControllerProvider, this.provideFileBackedComponentContentStoreProvider));
        this.dashDownloadContentSessionFactoryProvider = DoubleCheck.provider(DashDownloadContentSessionFactory_Factory.create(this.provideNetworkHistoryManagerProvider, this.mediaSystemSharedContextProvider, this.provideDownloadServiceProvider, this.provideFileSystemProvider, this.provideMediaProfilerProvider, this.provideSmoothStreamingContentStoreProvider, this.playbackNativeLibrariesLoaderProvider, this.rendererSchemeControllerProvider, this.provideFileBackedComponentContentStoreProvider, this.provideSingleFileComponentContentStoreProvider));
        Provider<DefaultHeuristicsConfigurationSynchronizer> provider5 = DoubleCheck.provider(DefaultHeuristicsConfigurationSynchronizer_Factory.create(this.provideEventReporterFactoryProvider));
        this.defaultHeuristicsConfigurationSynchronizerProvider = provider5;
        this.compositeContentSessionFactoryProvider = DoubleCheck.provider(CompositeContentSessionFactory_Factory.create(this.dashContentSessionFactoryProvider, this.dashMiyagiContentSessionFactoryProvider, this.smoothStreamingDashHeuristicsContentSesionFactoryProvider, this.dashDownloadContentSessionFactoryProvider, provider5));
        this.provideContentSessionFactoryProvider = DoubleCheck.provider(AdaptiveStreamingModule_Dagger_ProvideContentSessionFactoryFactory.create(builder.adaptiveStreamingModule_Dagger, this.compositeContentSessionFactoryProvider));
        Provider<PlaybackSupportEvaluator> provider6 = DoubleCheck.provider(MediaModule_Dagger_ProvidePlaybackSupportEvaluatorFactory.create(builder.mediaModule_Dagger));
        this.providePlaybackSupportEvaluatorProvider = provider6;
        this.playReadyAvodLicensingServiceProvider = PlayReadyAvodLicensingService_Factory.create(provider6, this.provideQOSCommunicationServiceProvider);
        this.providePlayReadyLicensingServiceProvider = DoubleCheck.provider(MediaModule_Dagger_ProvidePlayReadyLicensingServiceFactory.create(builder.mediaModule_Dagger, this.playReadyAvodLicensingServiceProvider));
        this.widevineAvodLicensingServiceProvider = WidevineAvodLicensingService_Factory.create(this.providePlaybackSupportEvaluatorProvider, this.provideQOSCommunicationServiceProvider);
        Provider<WidevineLicensingService> provider7 = DoubleCheck.provider(MediaModule_Dagger_ProvideWidevineLicensingServiceFactory.create(builder.mediaModule_Dagger, this.widevineAvodLicensingServiceProvider));
        this.provideWidevineLicensingServiceProvider = provider7;
        this.drmSystemImplProvider = DoubleCheck.provider(DrmSystemImpl_Factory.create(this.rendererSchemeControllerProvider, this.provideEventReporterFactoryProvider, this.provideExecutorServiceProvider, this.providePlayReadyLicensingServiceProvider, provider7, this.provideMediaProfilerProvider));
        this.provideBaseDrmSystemProvider = DoubleCheck.provider(DrmModule_Dagger_ProvideBaseDrmSystemFactory.create(builder.drmModule_Dagger, this.drmSystemImplProvider));
        this.provideContentStoreProvider = ContentModule_Dagger_ProvideContentStoreFactory.create(builder.contentModule_Dagger, this.compositeSmoothStreamingContentStoreProvider);
        this.qoSEventManagerFactoryProvider = DoubleCheck.provider(QoSEventManagerFactory_Factory.create(this.mediaSystemSharedContextProvider, this.qosEventReporterFactoryProvider));
        this.aVODContentUrlFetcherProvider = AVODContentUrlFetcher_Factory.create(this.provideMediaProfilerProvider, this.providePlaybackSupportEvaluatorProvider, this.provideAdIdProvider, this.provideQOSCommunicationServiceProvider);
        this.provideContentUrlFetcherProvider = MediaModule_Dagger_ProvideContentUrlFetcherFactory.create(builder.mediaModule_Dagger, this.aVODContentUrlFetcherProvider);
        Provider<ClientCacheConfig> provider8 = DoubleCheck.provider(AdaptiveStreamingModule_Dagger_ProvidePlaybackClientsConfigFactory.create(builder.adaptiveStreamingModule_Dagger));
        this.providePlaybackClientsConfigProvider = provider8;
        this.prioritizingVideoCacheManagerProvider = DoubleCheck.provider(PrioritizingVideoCacheManager_Factory.create(this.provideBaseDrmSystemProvider, this.provideAppContextProvider, this.provideContentSessionFactoryProvider, this.provideFileSystemProvider, this.provideContentStoreProvider, this.qoSEventManagerFactoryProvider, this.provideContentUrlFetcherProvider, this.qosEventReporterFactoryProvider, this.playbackNativeLibrariesLoaderProvider, this.rendererSchemeControllerProvider, provider8));
        this.provideCacheManagerProvider = DoubleCheck.provider(ContentCachingModule_Dagger_ProvideCacheManagerFactory.create(builder.contentCachingModule_Dagger, this.prioritizingVideoCacheManagerProvider));
        this.smoothStreamingVideoPresentationEventReporterProvider = SmoothStreamingVideoPresentationEventReporter_Factory.create(this.qosEventReporterFactoryProvider, this.mediaSystemSharedContextProvider);
        this.firstMatchVideoPresentationFactoryProvider = new DelegateFactory();
        Provider<AdUriProxy> provider9 = DoubleCheck.provider(AdUriProxy_Factory.create(this.provideDownloadServiceProvider, this.provideFileSystemProvider, this.provideExecutorServiceProvider, this.provideQOSCommunicationServiceProvider, this.androidVideoPlayerV2CacheProvider));
        this.adUriProxyProvider = provider9;
        this.adManagerBasedAdPlanFactoryProvider = AdManagerBasedAdPlanFactory_Factory.create(this.mediaSystemSharedContextProvider, this.firstMatchVideoPresentationFactoryProvider, provider9, this.provideDownloadServiceProvider, this.providePlaybackSupportEvaluatorProvider, this.provideQOSCommunicationServiceProvider, this.provideAdIdProvider, this.androidVideoPlayerV2CacheProvider);
        this.provideAdPlanFactoryProvider = AdEnabledVideoModule_Dagger_ProvideAdPlanFactoryFactory.create(builder.adEnabledVideoModule_Dagger, this.adManagerBasedAdPlanFactoryProvider);
        Factory<PlaybackMediaEventReporters.Factory> factoryCreate4 = PlaybackMediaEventReporters_Factory_Factory.create(this.provideMediaEventQueueProvider, this.provideMediaEventDispatcherProvider, this.providePeriodicMediaEventSchedulerProvider, this.setOfMediaEventProducerProvider, this.provideAppBootstrapLoaderProvider, this.provideAppContextProvider, this.provideAloysiusDeviceReporterProvider);
        this.factoryProvider2 = factoryCreate4;
        this.playbackSessionFactoryProvider = DoubleCheck.provider(PlaybackSessionFactory_Factory.create(this.mediaSystemSharedContextProvider, this.provideContentSessionFactoryProvider, this.provideMediaProfilerProvider, this.provideBaseDrmSystemProvider, this.provideCacheManagerProvider, this.provideDownloadServiceProvider, this.smoothStreamingVideoPresentationEventReporterProvider, this.provideContentUrlFetcherProvider, this.providePlaybackSupportEvaluatorProvider, this.rendererSchemeControllerProvider, this.provideAdPlanFactoryProvider, this.adUriProxyProvider, factoryCreate4, this.provideQOSCommunicationServiceProvider));
        this.androidLockFactoryProvider = AndroidLockFactory_Factory.create(this.provideAppContextProvider);
        Factory<PowerManagementLockFactory> factoryCreate5 = MediaModule_Dagger_ProvidePowerManagementLockFactoryFactory.create(builder.mediaModule_Dagger, this.androidLockFactoryProvider);
        this.providePowerManagementLockFactoryProvider = factoryCreate5;
        this.amazonVideoPlayerFactoryProvider = DoubleCheck.provider(AmazonVideoPlayerFactory_Factory.create(this.mediaSystemSharedContextProvider, factoryCreate5));
        Factory<AdEnabledVideoPlayer> factoryCreate6 = AdEnabledVideoModule_Dagger_ProvideAdEnabledVideoPlayerFactory.create(builder.adEnabledVideoModule_Dagger);
        this.provideAdEnabledVideoPlayerProvider = factoryCreate6;
        this.adEnabledVideoPresentationFactoryProvider = DoubleCheck.provider(AdEnabledVideoPresentationFactory_Factory.create(this.playbackSessionFactoryProvider, this.amazonVideoPlayerFactoryProvider, factoryCreate6, this.provideAdPlanFactoryProvider, this.volumeManagerProvider, this.provideAdIdProvider, this.provideExecutorServiceProvider, this.adUriProxyProvider));
        this.provideAdEnabledVideoPresentationFactoryProvider = MediaModule_Dagger_ProvideAdEnabledVideoPresentationFactoryFactory.create(builder.mediaModule_Dagger, this.adEnabledVideoPresentationFactoryProvider);
        SetFactory setFactoryBuild = SetFactory.builder(3, 0).addProvider(this.provideImageVideoPresentationFactoryProvider).addProvider(this.provideAndroidVideoPresentationFactoryProvider).addProvider(this.provideAdEnabledVideoPresentationFactoryProvider).build();
        this.setOfVideoPresentationFactoryProvider = setFactoryBuild;
        DelegateFactory delegateFactory = (DelegateFactory) this.firstMatchVideoPresentationFactoryProvider;
        Provider<FirstMatchVideoPresentationFactory> provider10 = DoubleCheck.provider(FirstMatchVideoPresentationFactory_Factory.create(this.syeVideoPresentationFactoryProvider, setFactoryBuild));
        this.firstMatchVideoPresentationFactoryProvider = provider10;
        delegateFactory.setDelegatedProvider(provider10);
        this.provideConfigurablePlaybackSupportEvaluatorProvider = DoubleCheck.provider(MediaModule_Dagger_ProvideConfigurablePlaybackSupportEvaluatorFactory.create(builder.mediaModule_Dagger, this.rendererSchemeControllerProvider));
        this.qoSModule_Dagger = builder.qoSModule_Dagger;
        this.aloysiusReporterModule_Dagger = builder.aloysiusReporterModule_Dagger;
        this.contentModule_Dagger = builder.contentModule_Dagger;
        this.dashModule_Dagger = builder.dashModule_Dagger;
        this.smoothStreamModule_Dagger = builder.smoothStreamModule_Dagger;
        this.playerHeuristicsModule_Dagger = builder.playerHeuristicsModule_Dagger;
        this.qosEventModelMediaComponentProvider = DoubleCheck.provider(QosEventModelMediaComponent_Factory.create());
        this.provideEventModelMediaComponentProvider = QoSModule_Dagger_ProvideEventModelMediaComponentFactory.create(builder.qoSModule_Dagger, this.qosEventModelMediaComponentProvider);
        this.drmLicenseCleanerProvider = DoubleCheck.provider(DrmLicenseCleaner_Factory.create(this.provideBaseDrmSystemProvider, this.providePlayReadyLicensingServiceProvider, this.provideExecutorServiceProvider));
        this.provideLicenseCleanerProvider = DrmModule_Dagger_ProvideLicenseCleanerFactory.create(builder.drmModule_Dagger, this.drmLicenseCleanerProvider);
    }

    private void initialize2(Builder builder) {
        this.getHeuristicsConfigurationSynchronizerAsMediaComponentProvider = MediaModule_Dagger_GetHeuristicsConfigurationSynchronizerAsMediaComponentFactory.create(builder.mediaModule_Dagger, this.defaultHeuristicsConfigurationSynchronizerProvider);
        this.providesMediaComponentProvider = ContentModule_Dagger_ProvidesMediaComponentFactory.create(builder.contentModule_Dagger, this.provideFileBackedComponentContentStoreProvider);
        this.setOfMediaComponentProvider = SetFactory.builder(4, 0).addProvider(this.provideEventModelMediaComponentProvider).addProvider(this.provideLicenseCleanerProvider).addProvider(this.getHeuristicsConfigurationSynchronizerAsMediaComponentProvider).addProvider(this.providesMediaComponentProvider).build();
    }

    @Override // com.amazon.avod.media.MediaSystem.MediaSystemComponent
    public VideoPresentationFactory getVideoPresentationFactory() {
        return (VideoPresentationFactory) Preconditions.checkNotNull(MediaModule_Dagger_ProvideVideoPresentationFactoryFactory.proxyProvideVideoPresentationFactory(this.mediaModule_Dagger, this.firstMatchVideoPresentationFactoryProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // com.amazon.avod.media.MediaSystem.MediaSystemComponent
    public ConfigurablePlaybackSupportEvaluator getConfigurablePlaybackSupportEvaluator() {
        return this.provideConfigurablePlaybackSupportEvaluatorProvider.get();
    }

    @Override // com.amazon.avod.media.MediaSystem.MediaSystemComponent
    public MediaSystemSharedContext getMediaSystemSharedContext() {
        return this.mediaSystemSharedContextProvider.get();
    }

    @Override // com.amazon.avod.media.MediaSystem.MediaSystemComponent
    public PlaybackDownloadExecutorFactory getPlaybackDownloadExecutorFactory() {
        return new PlaybackDownloadExecutorFactory(this.provideBaseDrmSystemProvider.get(), getContentStore(), this.provideContentSessionFactoryProvider.get(), this.qoSEventManagerFactoryProvider.get(), getContentUrlFetcher(), this.rendererSchemeControllerProvider.get());
    }

    @Override // com.amazon.avod.media.MediaSystem.MediaSystemComponent
    public VideoCacheManager getVideoCacheManager() {
        return this.provideCacheManagerProvider.get();
    }

    @Override // com.amazon.avod.media.MediaSystem.MediaSystemComponent
    public ClientCacheConfig getClientCacheConfig() {
        return this.providePlaybackClientsConfigProvider.get();
    }

    @Override // com.amazon.avod.media.MediaSystem.MediaSystemComponent
    public EventReporterFactory getEventReporterFactory() {
        return (EventReporterFactory) Preconditions.checkNotNull(QoSModule_Dagger_ProvideEventReporterFactoryFactory.proxyProvideEventReporterFactory(this.qoSModule_Dagger, getQosEventReporterFactory()), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // com.amazon.avod.media.MediaSystem.MediaSystemComponent
    public PlaybackMediaEventReporters.Factory getPlaybackMediaEventReportersFactory() {
        return new PlaybackMediaEventReporters.Factory(this.provideMediaEventQueueProvider.get(), this.provideMediaEventDispatcherProvider.get(), this.providePeriodicMediaEventSchedulerProvider, getSetOfMediaEventProducer(), this.provideAppBootstrapLoaderProvider.get(), getContext(), getAloysiusDeviceReporter());
    }

    @Override // com.amazon.avod.media.MediaSystem.MediaSystemComponent
    public MediaSystem injectMediaSystem(MediaSystem mediaSystem) {
        return injectMediaSystem2(mediaSystem);
    }

    @Override // com.amazon.avod.media.MediaSystem.MediaSystemComponent
    public BaseDrmSystem getBaseDrmSystem() {
        return this.provideBaseDrmSystemProvider.get();
    }

    public ContentStore getContentStore() {
        return (ContentStore) Preconditions.checkNotNull(ContentModule_Dagger_ProvideContentStoreFactory.proxyProvideContentStore(this.contentModule_Dagger, getCompositeSmoothStreamingContentStore()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public ContentUrlFetcher getContentUrlFetcher() {
        return (ContentUrlFetcher) Preconditions.checkNotNull(MediaModule_Dagger_ProvideContentUrlFetcherFactory.proxyProvideContentUrlFetcher(this.mediaModule_Dagger, getAVODContentUrlFetcher()), "Cannot return null from a non-@Nullable @Provides method");
    }

    private MediaSystem injectMediaSystem2(MediaSystem mediaSystem) {
        MediaSystem_MembersInjector.injectMBaseDrmSystem(mediaSystem, this.provideBaseDrmSystemProvider);
        MediaSystem_MembersInjector.injectMEventReporterFactory(mediaSystem, getEventReporterFactory());
        MediaSystem_MembersInjector.injectMLibrariesLoader(mediaSystem, this.playbackNativeLibrariesLoaderProvider.get());
        MediaSystem_MembersInjector.injectMFileSystem(mediaSystem, getFileSystem());
        MediaSystem_MembersInjector.injectMProfiler(mediaSystem, this.provideMediaProfilerProvider.get());
        MediaSystem_MembersInjector.injectMLibraries(mediaSystem, getSetOfLoadableNativeLibrary());
        MediaSystem_MembersInjector.injectMRendererSchemeController(mediaSystem, this.rendererSchemeControllerProvider.get());
        MediaSystem_MembersInjector.injectMPlaybackMediaEventReportersFactory(mediaSystem, getPlaybackMediaEventReportersFactory());
        MediaSystem_MembersInjector.injectMAloysiusInitializer(mediaSystem, getAloysiusInitializer());
        MediaSystem_MembersInjector.injectMComponentProvider(mediaSystem, this.setOfMediaComponentProvider);
        return mediaSystem;
    }

    public static final class Builder {
        private AdEnabledVideoModule_Dagger adEnabledVideoModule_Dagger;
        private AdaptiveStreamingModule_Dagger adaptiveStreamingModule_Dagger;
        private AloysiusReporterModule_Dagger aloysiusReporterModule_Dagger;
        private ContentCachingModule_Dagger contentCachingModule_Dagger;
        private ContentModule_Dagger contentModule_Dagger;
        private DashModule_Dagger dashModule_Dagger;
        private DrmModule_Dagger drmModule_Dagger;
        private MediaEventModule_Dagger mediaEventModule_Dagger;
        private MediaModule_Dagger mediaModule_Dagger;
        private PlayerHeuristicsModule_Dagger playerHeuristicsModule_Dagger;
        private QoSModule_Dagger qoSModule_Dagger;
        private SmoothStreamModule_Dagger smoothStreamModule_Dagger;

        private Builder() {
        }

        public MediaSystem.MediaSystemComponent build() {
            if (this.mediaModule_Dagger == null) {
                this.mediaModule_Dagger = new MediaModule_Dagger();
            }
            if (this.mediaEventModule_Dagger == null) {
                this.mediaEventModule_Dagger = new MediaEventModule_Dagger();
            }
            if (this.aloysiusReporterModule_Dagger == null) {
                this.aloysiusReporterModule_Dagger = new AloysiusReporterModule_Dagger();
            }
            if (this.qoSModule_Dagger == null) {
                this.qoSModule_Dagger = new QoSModule_Dagger();
            }
            if (this.adaptiveStreamingModule_Dagger == null) {
                throw new IllegalStateException(AdaptiveStreamingModule_Dagger.class.getCanonicalName() + " must be set");
            }
            if (this.contentModule_Dagger == null) {
                this.contentModule_Dagger = new ContentModule_Dagger();
            }
            if (this.drmModule_Dagger == null) {
                this.drmModule_Dagger = new DrmModule_Dagger();
            }
            if (this.contentCachingModule_Dagger == null) {
                this.contentCachingModule_Dagger = new ContentCachingModule_Dagger();
            }
            if (this.adEnabledVideoModule_Dagger == null) {
                this.adEnabledVideoModule_Dagger = new AdEnabledVideoModule_Dagger();
            }
            if (this.dashModule_Dagger == null) {
                this.dashModule_Dagger = new DashModule_Dagger();
            }
            if (this.smoothStreamModule_Dagger == null) {
                this.smoothStreamModule_Dagger = new SmoothStreamModule_Dagger();
            }
            if (this.playerHeuristicsModule_Dagger == null) {
                this.playerHeuristicsModule_Dagger = new PlayerHeuristicsModule_Dagger();
            }
            return new DaggerMediaSystem_MediaSystemComponent(this);
        }

        public Builder adaptiveStreamingModule_Dagger(AdaptiveStreamingModule_Dagger adaptiveStreamingModule_Dagger) {
            this.adaptiveStreamingModule_Dagger = (AdaptiveStreamingModule_Dagger) Preconditions.checkNotNull(adaptiveStreamingModule_Dagger);
            return this;
        }
    }
}
