package com.amazon.mp3.dagger;

import android.app.Application;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.AmazonApplication_MembersInjector;
import com.amazon.mp3.alps.dagger.ALPSAndroidComponentsModule_LiveEventDetailFragment$LiveEventDetailFragmentSubcomponent;
import com.amazon.mp3.alps.dagger.ALPSModule;
import com.amazon.mp3.alps.dagger.ALPSModule_ProvideAlpsChromeProviderFactory;
import com.amazon.mp3.alps.dagger.ALPSModule_ProvideAlpsSharingProviderFactory;
import com.amazon.mp3.alps.dagger.ALPSModule_ProvideChromeProviderFactory;
import com.amazon.mp3.alps.dagger.ALPSModule_ProvideSharingProviderFactory;
import com.amazon.mp3.alps.dagger.ALPSModule_ProvideStyleSheetFactory;
import com.amazon.mp3.alps.dagger.ALPSModule_ProvideUserSubscriptionFactory;
import com.amazon.mp3.auto.carmode.detection.CarBluetoothDetector;
import com.amazon.mp3.auto.carmode.detection.CarDetector;
import com.amazon.mp3.auto.carmode.detection.CarDetectorModule;
import com.amazon.mp3.auto.carmode.detection.CarDetectorModule_CarBluetoothDetectorProviderFactory;
import com.amazon.mp3.auto.carmode.detection.CarDetectorModule_CarDetectorProviderFactory;
import com.amazon.mp3.brush.converters.BrushPageGridConverter;
import com.amazon.mp3.casting.CastingSkillService;
import com.amazon.mp3.casting.CastingSkillService_MembersInjector;
import com.amazon.mp3.catalog.fragment.ArtistDetailBrushFragment;
import com.amazon.mp3.catalog.fragment.ArtistDetailBrushFragment_MembersInjector;
import com.amazon.mp3.catalog.fragment.FindBrushFragment;
import com.amazon.mp3.cloudqueue.sequencer.CloudQueueSequencer;
import com.amazon.mp3.cloudqueue.sequencer.CloudQueueSequencer_MembersInjector;
import com.amazon.mp3.connect.dagger.ConnectAndroidComponentsModule_ContactImportWebViewFragment$ContactImportWebViewFragmentSubcomponent;
import com.amazon.mp3.connect.dagger.ConnectAndroidComponentsModule_EditCustomerProfileDialogFragment$EditCustomerProfileFragmentSubcomponent;
import com.amazon.mp3.connect.dagger.ConnectModule;
import com.amazon.mp3.connect.dagger.ConnectModule_ProvideContactImportAppInterfaceFactory;
import com.amazon.mp3.curate.dagger.CurateAndroidComponentsModule_MySoundtrackFragment$MySoundtrackFragmentSubcomponent;
import com.amazon.mp3.curate.dagger.CurateAndroidComponentsModule_PersonalizedPlaylistsFragment$PersonalizedPlaylistsFragmentSubcomponent;
import com.amazon.mp3.curate.dagger.CurateAndroidComponentsModule_PlaylistIntentReceiverService$PlaylistIntentReceiverServiceSubcomponent;
import com.amazon.mp3.curate.dagger.CurateAndroidComponentsModule_PlaylistRecsSeeMoreFragment$PlaylistRecsSeeMoreFragmentSubcomponent;
import com.amazon.mp3.curate.dagger.CurateAndroidComponentsModule_TemplateFragment$TemplateFragmentSubcomponent;
import com.amazon.mp3.curate.dagger.CurateAndroidComponentsModule_UserPlaylistFragment$UserPlaylistFragmentSubcomponent;
import com.amazon.mp3.curate.dagger.CurateAndroidComponentsModule_UserPlaylistsFragment$UserPlaylistsFragmentSubcomponent;
import com.amazon.mp3.curate.dagger.CurateModule;
import com.amazon.mp3.curate.dagger.CurateModule_ProvideDownloadProviderFactory;
import com.amazon.mp3.curate.dagger.CurateModule_ProvideFeatureFlagProviderFactory;
import com.amazon.mp3.curate.dagger.CurateModule_ProvideNavigationProviderFactory;
import com.amazon.mp3.curate.dagger.CurateModule_ProvideNetworkProviderFactory;
import com.amazon.mp3.curate.dagger.CurateModule_ProvideOverflowMenuProviderFactory;
import com.amazon.mp3.curate.dagger.CurateModule_ProvidePlaybackProviderFactory;
import com.amazon.mp3.curate.dagger.CurateModule_ProvidePlaylistProviderFactory;
import com.amazon.mp3.curate.dagger.CurateModule_ProvideRequestInterceptorFactory;
import com.amazon.mp3.curate.dagger.CurateModule_ProvideShareProviderFactory;
import com.amazon.mp3.curate.dagger.CurateModule_ProvideStationProviderFactory;
import com.amazon.mp3.curate.dagger.CurateModule_ProvideSubscriptionProviderFactory;
import com.amazon.mp3.customerprofile.EditCustomerProfileFragment;
import com.amazon.mp3.customerprofile.EditCustomerProfileFragment_MembersInjector;
import com.amazon.mp3.dagger.ApplicationComponent;
import com.amazon.mp3.dagger.modules.FragmentModule_ArtistDetailBrushFragment$ArtistDetailBrushFragmentSubcomponent;
import com.amazon.mp3.dagger.modules.FragmentModule_PlaylistDetailFragment$PlaylistDetailFragmentSubcomponent;
import com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment;
import com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment_MembersInjector;
import com.amazon.mp3.explore.dagger.ExploreAndroidComponentsModule_AmpPlaybackActivity$AmpPlaybackActivitySubcomponent;
import com.amazon.mp3.explore.dagger.ExploreAndroidComponentsModule_CastingSkillService$CastingSkillServiceSubcomponent;
import com.amazon.mp3.explore.dagger.ExploreAndroidComponentsModule_DetailFragment$DetailFragmentSubcomponent;
import com.amazon.mp3.explore.dagger.ExploreAndroidComponentsModule_GalleryFragment$GalleryFragmentSubcomponent;
import com.amazon.mp3.explore.dagger.ExploreAndroidComponentsModule_LiveStreamActivity$LiveStreamActivitySubcomponent;
import com.amazon.mp3.explore.dagger.ExploreAndroidComponentsModule_MultiSelectorFragment$MultiSelectorFragmentSubcomponent;
import com.amazon.mp3.explore.dagger.ExploreAndroidComponentsModule_MultiSelectorTabFragment$MultiSelectorTabFragmentSubcomponent;
import com.amazon.mp3.explore.dagger.ExploreAndroidComponentsModule_PlaybackFragment$PlaybackFragmentSubcomponent;
import com.amazon.mp3.explore.dagger.ExploreAndroidComponentsModule_SelectionFragment$SelectionFragmentSubcomponent;
import com.amazon.mp3.explore.dagger.ExploreAndroidComponentsModule_StageSwipeablePagesFragment$StageSwipeablePagesFragmentSubcomponent;
import com.amazon.mp3.explore.dagger.ExploreAndroidComponentsModule_SwipeablePagesFragment$SwipeablePagesFragmentSubcomponent;
import com.amazon.mp3.explore.dagger.ExploreAndroidComponentsModule_VideoQueueActivity$VideoQueueActivitySubcomponent;
import com.amazon.mp3.explore.dagger.ExploreAndroidComponentsModule_VideoStoryFragment$VideoStoryFragmentSubcomponent;
import com.amazon.mp3.explore.dagger.ExploreAndroidComponentsModule_WatchSessionService$WatchSessionServiceSubcomponent;
import com.amazon.mp3.explore.dagger.ExploreModule;
import com.amazon.mp3.explore.dagger.ExploreModule_ProvideAnimationProviderFactory;
import com.amazon.mp3.explore.dagger.ExploreModule_ProvideCapabilitiesInfoFactory;
import com.amazon.mp3.explore.dagger.ExploreModule_ProvideCastingProviderFactory;
import com.amazon.mp3.explore.dagger.ExploreModule_ProvideChromeProviderFactory;
import com.amazon.mp3.explore.dagger.ExploreModule_ProvideContextMenuProviderFactory;
import com.amazon.mp3.explore.dagger.ExploreModule_ProvideDownloadProviderFactory;
import com.amazon.mp3.explore.dagger.ExploreModule_ProvideElementBindingProviderFactory;
import com.amazon.mp3.explore.dagger.ExploreModule_ProvideEnvironmentProviderFactory;
import com.amazon.mp3.explore.dagger.ExploreModule_ProvideExplicitFilterProviderFactory;
import com.amazon.mp3.explore.dagger.ExploreModule_ProvideExploreManagerFactoryFactory;
import com.amazon.mp3.explore.dagger.ExploreModule_ProvideExternalActionProviderFactory;
import com.amazon.mp3.explore.dagger.ExploreModule_ProvideFeaturedContainerFactory;
import com.amazon.mp3.explore.dagger.ExploreModule_ProvideLibraryProviderFactory;
import com.amazon.mp3.explore.dagger.ExploreModule_ProvideNavigationProviderFactory;
import com.amazon.mp3.explore.dagger.ExploreModule_ProvideNetworkInterceptorFactory;
import com.amazon.mp3.explore.dagger.ExploreModule_ProvideNotificationProviderFactory;
import com.amazon.mp3.explore.dagger.ExploreModule_ProvidePlaybackProviderFactory;
import com.amazon.mp3.explore.dagger.ExploreModule_ProvideSharingProviderFactory;
import com.amazon.mp3.explore.dagger.ExploreModule_ProvideSkillLatencyTrackerFactory;
import com.amazon.mp3.explore.dagger.ExploreModule_ProvideSkyFireConfigurationProviderFactory;
import com.amazon.mp3.explore.dagger.ExploreModule_ProvideSkyFireProviderFactory;
import com.amazon.mp3.explore.dagger.ExploreModule_ProvideStorageProviderFactory;
import com.amazon.mp3.explore.dagger.ExploreModule_ProvideStyleSheetProviderFactory;
import com.amazon.mp3.explore.dagger.ExploreModule_ProvideUpsellProviderFactory;
import com.amazon.mp3.explore.dagger.ExploreModule_ProvideUserSubscriptionProviderFactory;
import com.amazon.mp3.explore.dagger.ExploreModule_ProvideWakeWordProviderFactory;
import com.amazon.mp3.find.dagger.FindAndroidComponentsModule_CarModeSearchBrushFragment$CarModeSearchBrushFragmentSubcomponent;
import com.amazon.mp3.find.dagger.FindAndroidComponentsModule_EntityTrackingIntentService$EntityTrackingIntentServiceSubcomponent;
import com.amazon.mp3.find.dagger.FindAndroidComponentsModule_FindBrushFragment$FindBrushFragmentSubcomponent;
import com.amazon.mp3.find.dagger.FindAndroidComponentsModule_FindFilterDialogFragment$FindFilterDialogFragmentSubcomponent;
import com.amazon.mp3.find.dagger.FindAndroidComponentsModule_FindLandingFragment$FindLandingFragmentSubcomponent;
import com.amazon.mp3.find.dagger.FindAndroidComponentsModule_FindSkyfireFragment$FindSkyfireFragmentSubcomponent;
import com.amazon.mp3.find.dagger.FindAndroidComponentsModule_SearchBrushFragment$SearchBrushFragmentSubcomponent;
import com.amazon.mp3.find.dagger.FindAndroidComponentsModule_SearchGalleryFragment$SearchGalleryFragmentSubcomponent;
import com.amazon.mp3.find.dagger.FindAndroidComponentsModule_SearchHostFragment$SearchHostFragmentSubcomponent;
import com.amazon.mp3.find.dagger.FindModule;
import com.amazon.mp3.find.dagger.FindModule_ProvideAppDatabaseFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvideCacheImageProviderFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvideCapabilitiesInfoFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvideCarModeConverterServiceFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvideCarModeSearchResultConverterFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvideCarModeTransformerServiceFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvideChromeProviderFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvideContextMenuProviderFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvideConverterServiceFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvideDownloadProviderFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvideElementBindingProviderFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvideEntityTrackingServiceFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvideExternalActionProviderFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvideFilterSelectionConverterFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvideFilterViewModelFactoryFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvideFindFeatureConfigurationProviderFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvideInstantSearchSuggestionConfigurationProviderFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvideLibraryProviderFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvideLibraryStateServiceFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvideNavigationProviderFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvideNetworkInterceptorFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvideNotificationProviderFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvidePlaybackProviderFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvideSearchConfigurationProviderFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvideSearchEntityDaoFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvideSearchFeaturesProviderFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvideSearchHistoryConverterFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvideSearchHistoryServiceFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvideSearchHostViewModelFactoryFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvideSearchQueryDaoFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvideSearchResultConverterFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvideSearchSuggestionApiFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvideSearchSuggestionConfigurationProviderFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvideSearchSuggestionConverterFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvideSearchSuggestionServiceFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvideSearchViewModelFactoryFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvideSeeMoreResultConverterFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvideSharingProviderFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvideSkillLatencyTrackerFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvideSkyFireConfigurationProviderFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvideSkyFireProviderFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvideStyleSheetProviderFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvideUIConfigurationServiceFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvideUpsellProviderFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvideUserSettingsServiceFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvideUserSubscriptionProviderFactory;
import com.amazon.mp3.find.dagger.FindModule_ProviderBrushPageGridConverterFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvidesCarModeSearchViewModelFactoryFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvidesCirrusDatabaseProviderFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvidesLibrarySearchApiFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvidesLibrarySearchDaoFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvidesRemoteSearchApiFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvidesSearchEntityFactoryFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvidesSearchMetricsApiFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvidesSearchMetricsServiceFactory;
import com.amazon.mp3.find.dagger.FindModule_ProvidesSearchServiceFactory;
import com.amazon.mp3.find.dagger.InstantSearchSuggestionServiceConfigurationProvider;
import com.amazon.mp3.find.dagger.SearchServiceConfigurationProvider;
import com.amazon.mp3.find.dagger.SearchSuggestionServiceConfigurationProvider;
import com.amazon.mp3.find.view.CarModeSearchBrushFragment;
import com.amazon.mp3.find.view.CarModeSearchBrushFragment_MembersInjector;
import com.amazon.mp3.find.view.FindFilterDialogFragment;
import com.amazon.mp3.find.view.FindFilterDialogFragment_MembersInjector;
import com.amazon.mp3.find.view.FindLandingFragment;
import com.amazon.mp3.find.view.SearchBrushFragment;
import com.amazon.mp3.find.view.SearchBrushFragment_MembersInjector;
import com.amazon.mp3.find.view.SearchHostFragment;
import com.amazon.mp3.find.view.SearchHostFragment_MembersInjector;
import com.amazon.mp3.init.CarDetectionLaunchInitializationTask;
import com.amazon.mp3.init.CarDetectionLaunchInitializationTask_MembersInjector;
import com.amazon.mp3.platform.dagger.PlatformProvidersModule;
import com.amazon.mp3.platform.dagger.PlatformProvidersModule_ProvideAuthenticationProviderFactory;
import com.amazon.mp3.platform.dagger.PlatformProvidersModule_ProvideBuildInfoProviderFactory;
import com.amazon.mp3.platform.dagger.PlatformProvidersModule_ProvideCustomerMetadataProviderFactory;
import com.amazon.mp3.platform.dagger.PlatformProvidersModule_ProvideDeviceInfoProviderFactory;
import com.amazon.mp3.platform.dagger.PlatformProvidersModule_ProvideFeatureGateProviderFactory;
import com.amazon.mp3.platform.dagger.PlatformProvidersModule_ProvideImageUrlRepositoryFactory;
import com.amazon.mp3.platform.dagger.PlatformProvidersModule_ProvideMetricsProviderFactory;
import com.amazon.mp3.playback.fragment.NowPlayingFragment;
import com.amazon.mp3.playback.fragment.NowPlayingFragment_MembersInjector;
import com.amazon.mp3.playback.fragment.StageVideoPlayerFragment;
import com.amazon.mp3.playback.fragment.StageVideoPlayerFragment_MembersInjector;
import com.amazon.mp3.playback.fragment.VideoPlayerFragment;
import com.amazon.mp3.playback.fragment.VideoPlayerFragment_MembersInjector;
import com.amazon.mp3.playback.harley.PlayQueueSequencer;
import com.amazon.mp3.playback.harley.PlayQueueSequencer_MembersInjector;
import com.amazon.mp3.playback.usecase.AudioVideoToggleInteractor;
import com.amazon.mp3.playback.usecase.AudioVideoToggleUseCase;
import com.amazon.mp3.playback.view.lyrics.LyricsListViewContainer;
import com.amazon.mp3.playback.view.lyrics.LyricsListViewContainer_MembersInjector;
import com.amazon.mp3.prime.requester.HybridQueueTrackRequester;
import com.amazon.mp3.sharing.dagger.LyricsSharingComponentsModule_LyricsCardSelectionFragment$LyricsCardSelectionFragmentSubcomponent;
import com.amazon.mp3.sharing.dagger.ShareAndroidComponentsModule_SharingFragment$SharingFragmentSubcomponent;
import com.amazon.mp3.sharing.dagger.ShareAndroidComponentsModule_SharingFragmentV2$SharingFragmentV2Subcomponent;
import com.amazon.mp3.sharing.dagger.ShareModule;
import com.amazon.mp3.sharing.dagger.ShareModule_ProvideEnvironmentProviderFactory;
import com.amazon.mp3.sharing.dagger.ShareModule_ProvideShareHelperFactory;
import com.amazon.mp3.sharing.dagger.ShareModule_ProvideSocialMetadataProviderFactory;
import com.amazon.mp3.sharing.dagger.ShareModule_ProvideStyleSheetProviderFactory;
import com.amazon.mp3.starlight.module.StarlightModule;
import com.amazon.mp3.starlight.module.StarlightModule_GetAudioVideoUseCaseFactory;
import com.amazon.mp3.starlight.module.StarlightModule_GetHybridQueueStateProviderFactory;
import com.amazon.mp3.starlight.module.StarlightModule_GetHybridQueueTrackRequesterFactory;
import com.amazon.mp3.starlight.module.StarlightModule_GetVideoPlayerStateProviderFactory;
import com.amazon.mp3.starlight.provider.HybridQueueStateProvider;
import com.amazon.mp3.starlight.provider.VideoPlayerStateProvider;
import com.amazon.mp3.starlight.subcomponent.NowPlayingComponent;
import com.amazon.mp3.video.stories.BaseVideoStoryFragment_MembersInjector;
import com.amazon.mp3.video.stories.VideoStoryFragment;
import com.amazon.mp3.view.lyrics.sharing.data.LyricsSharingDataModel;
import com.amazon.mp3.view.lyrics.sharing.view.LyricsCardSelectionFragment;
import com.amazon.mp3.view.lyrics.sharing.view.LyricsCardSelectionFragment_MembersInjector;
import com.amazon.mp3.view.lyrics.sharing.view.LyricsSelectionFragment;
import com.amazon.mp3.view.lyrics.sharing.view.LyricsSelectionFragment_MembersInjector;
import com.amazon.mp3.view.stage.StageAutomatedSequencingHandler;
import com.amazon.mp3.view.stage.StageAutomatedSequencingHandler_MembersInjector;
import com.amazon.mp3.view.stage.StageOverflowMenuSecondaryIconStateProvider;
import com.amazon.mp3.view.stage.StageOverlayMetricsReporter;
import com.amazon.mp3.view.stage.StageOverlayMetricsReporter_MembersInjector;
import com.amazon.mp3.view.stage.StageViewPresenter;
import com.amazon.mp3.view.stage.StageViewPresenter_MembersInjector;
import com.amazon.mp3.view.stage.module.StageModule;
import com.amazon.mp3.view.stage.module.StageModule_GetLyricsSharingDataModelFactory;
import com.amazon.mp3.view.stage.module.StageModule_GetOverlayLyricsStateProviderFactory;
import com.amazon.mp3.view.stage.module.StageModule_GetOverlayTypeStateProviderFactory;
import com.amazon.mp3.view.stage.module.StageModule_GetOverlayXrayStateProviderFactory;
import com.amazon.mp3.view.stage.module.StageModule_GetStageAutomatedSequencingHandlerFactory;
import com.amazon.mp3.view.stage.module.StageModule_GetStageOverflowMenuSecondaryIconStateProviderFactory;
import com.amazon.mp3.view.stage.overlay.OverlayLyricsStateProvider;
import com.amazon.mp3.view.stage.overlay.OverlayTypeStateProvider;
import com.amazon.mp3.view.stage.overlay.OverlayXrayStateProvider;
import com.amazon.mp3.watch.WatchSessionService;
import com.amazon.mp3.watch.WatchSessionService_MembersInjector;
import com.amazon.music.alps.liveevent.LiveEventDetailFragment;
import com.amazon.music.alps.liveevent.LiveEventDetailFragment_MembersInjector;
import com.amazon.music.connect.contacts.ContactImportProvider;
import com.amazon.music.connect.contacts.ContactImportWebViewFragment;
import com.amazon.music.connect.contacts.ContactImportWebViewFragment_MembersInjector;
import com.amazon.music.curate.data.PlaylistIntentReceiverService;
import com.amazon.music.curate.data.PlaylistIntentReceiverService_MembersInjector;
import com.amazon.music.curate.provider.OverflowMenuProvider;
import com.amazon.music.curate.provider.StationProvider;
import com.amazon.music.curate.skyfire.views.TemplateFragment;
import com.amazon.music.curate.view.MySoundtrackFragment;
import com.amazon.music.curate.view.MySoundtrackFragment_MembersInjector;
import com.amazon.music.curate.view.PersonalizedPlaylistsFragment;
import com.amazon.music.curate.view.PersonalizedPlaylistsFragment_MembersInjector;
import com.amazon.music.curate.view.PlaylistRecsSeeMoreFragment;
import com.amazon.music.curate.view.PlaylistRecsSeeMoreFragment_MembersInjector;
import com.amazon.music.curate.view.UserPlaylistFragment;
import com.amazon.music.curate.view.UserPlaylistFragment_MembersInjector;
import com.amazon.music.curate.view.UserPlaylistsFragment;
import com.amazon.music.curate.view.UserPlaylistsFragment_MembersInjector;
import com.amazon.music.explore.activity.AmpPlaybackActivity;
import com.amazon.music.explore.activity.BaseExploreActivity_MembersInjector;
import com.amazon.music.explore.activity.LiveStreamActivity;
import com.amazon.music.explore.activity.VideoQueueActivity;
import com.amazon.music.explore.containers.FeaturedContainer;
import com.amazon.music.explore.fragment.BaseExploreFragment_MembersInjector;
import com.amazon.music.explore.fragment.PlaybackFragment;
import com.amazon.music.explore.fragment.StageSwipeablePagesFragment;
import com.amazon.music.explore.fragment.SwipeablePagesFragment;
import com.amazon.music.explore.managers.ExploreManagerFactory;
import com.amazon.music.explore.providers.AnimationProvider;
import com.amazon.music.explore.providers.CastingProvider;
import com.amazon.music.explore.providers.ExplicitFilterProvider;
import com.amazon.music.explore.providers.WakeWordProvider;
import com.amazon.music.find.api.SearchApi;
import com.amazon.music.find.api.searchsuggestion.SearchSuggestionApi;
import com.amazon.music.find.converter.BrushV2Converter;
import com.amazon.music.find.converter.PageGridViewModelConverter;
import com.amazon.music.find.data.database.AppDatabase;
import com.amazon.music.find.data.database.CirrusDatabaseProvider;
import com.amazon.music.find.data.database.LibrarySearchDao;
import com.amazon.music.find.data.database.SearchEntityDao;
import com.amazon.music.find.data.database.SearchQueryDao;
import com.amazon.music.find.fragment.FindSkyfireFragment;
import com.amazon.music.find.fragment.FindSkyfireFragment_MembersInjector;
import com.amazon.music.find.fragment.SearchGalleryFragment;
import com.amazon.music.find.intents.EntityTrackingIntentService;
import com.amazon.music.find.intents.EntityTrackingIntentService_MembersInjector;
import com.amazon.music.find.metrics.SearchMetricsService;
import com.amazon.music.find.model.SearchSuggestion;
import com.amazon.music.find.model.filter.SearchFilterSelections;
import com.amazon.music.find.model.search.GenericSearchResponse;
import com.amazon.music.find.model.search.SearchResponse;
import com.amazon.music.find.model.search.SeeMoreResponse;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import com.amazon.music.find.service.ConverterService;
import com.amazon.music.find.service.EntityTrackingService;
import com.amazon.music.find.service.LibraryStateService;
import com.amazon.music.find.service.SearchHistoryService;
import com.amazon.music.find.service.SearchService;
import com.amazon.music.find.service.SearchSuggestionService;
import com.amazon.music.find.service.UIConfigurationService;
import com.amazon.music.find.service.UserSettingsService;
import com.amazon.music.find.viewmodels.CarModeSearchViewModelFactory;
import com.amazon.music.find.viewmodels.FilterViewModelFactory;
import com.amazon.music.find.viewmodels.SearchHostViewModelFactory;
import com.amazon.music.find.viewmodels.SearchViewModelFactory;
import com.amazon.music.platform.find.service.history.SearchHistoryItem;
import com.amazon.music.platform.providers.AuthenticationProvider;
import com.amazon.music.platform.providers.BuildInfoProvider;
import com.amazon.music.platform.providers.CustomerMetadataProvider;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import com.amazon.music.platform.providers.MetricsProvider;
import com.amazon.music.share.ShareHelper;
import com.amazon.music.share.menu.SharingFragment;
import com.amazon.music.share.menu.SharingFragmentV2;
import com.amazon.music.share.menu.SharingFragmentV2_MembersInjector;
import com.amazon.music.share.menu.SharingFragment_MembersInjector;
import com.amazon.music.share.menu.providers.SocialMetadataProvider;
import com.amazon.music.skyfire.core.network.CapabilitiesInfo;
import com.amazon.music.skyfire.core.network.NetworkInterceptor;
import com.amazon.music.skyfire.core.performance.SkillLatencyTracker;
import com.amazon.music.skyfire.ui.fragment.DetailFragment;
import com.amazon.music.skyfire.ui.fragment.DetailFragment_MembersInjector;
import com.amazon.music.skyfire.ui.fragment.GalleryFragment;
import com.amazon.music.skyfire.ui.fragment.MultiSelectorFragment;
import com.amazon.music.skyfire.ui.fragment.MultiSelectorTabFragment;
import com.amazon.music.skyfire.ui.fragment.MusicHomeFragment_MembersInjector;
import com.amazon.music.skyfire.ui.fragment.SelectionFragment;
import com.amazon.music.skyfire.ui.fragment.SelectionFragment_MembersInjector;
import com.amazon.music.skyfire.ui.fragment.TemplateFragment_MembersInjector;
import com.amazon.music.skyfire.ui.providers.ChromeProvider;
import com.amazon.music.skyfire.ui.providers.ContextMenuProvider;
import com.amazon.music.skyfire.ui.providers.DownloadProvider;
import com.amazon.music.skyfire.ui.providers.ElementBindingProvider;
import com.amazon.music.skyfire.ui.providers.EnvironmentProvider;
import com.amazon.music.skyfire.ui.providers.ExternalActionProvider;
import com.amazon.music.skyfire.ui.providers.LibraryProvider;
import com.amazon.music.skyfire.ui.providers.NavigationProvider;
import com.amazon.music.skyfire.ui.providers.NotificationProvider;
import com.amazon.music.skyfire.ui.providers.PlaybackProvider;
import com.amazon.music.skyfire.ui.providers.SharingProvider;
import com.amazon.music.skyfire.ui.providers.SkyFireConfigurationProvider;
import com.amazon.music.skyfire.ui.providers.SkyFireProvider;
import com.amazon.music.skyfire.ui.providers.StorageProvider;
import com.amazon.music.skyfire.ui.providers.StyleSheetProvider;
import com.amazon.music.skyfire.ui.providers.UpsellProvider;
import com.amazon.music.skyfire.ui.providers.UserSubscriptionProvider;
import com.amazon.music.subscription.UserSubscription;
import com.amazon.music.views.library.styles.StyleSheet;
import com.google.common.collect.ImmutableMap;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication_MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.DispatchingAndroidInjector_Factory;
import dagger.android.support.DaggerApplication;
import dagger.internal.DoubleCheck;
import dagger.internal.InstanceFactory;
import dagger.internal.Preconditions;
import dagger.internal.SingleCheck;
import java.util.Map;
import javax.inject.Provider;
import rx.Observable;

/* JADX INFO: loaded from: classes3.dex */
public final class DaggerApplicationComponent {
    private DaggerApplicationComponent() {
    }

    public static ApplicationComponent.Builder builder() {
        return new Builder();
    }

    private static final class Builder implements ApplicationComponent.Builder {
        private Application application;

        private Builder() {
        }

        @Override // com.amazon.mp3.dagger.ApplicationComponent.Builder
        public Builder application(Application application) {
            this.application = (Application) Preconditions.checkNotNull(application);
            return this;
        }

        @Override // com.amazon.mp3.dagger.ApplicationComponent.Builder
        public ApplicationComponent build() {
            Preconditions.checkBuilderRequirement(this.application, Application.class);
            return new ApplicationComponentImpl(new CarDetectorModule(), new PlatformProvidersModule(), this.application);
        }
    }

    private static final class ArtistDetailBrushFragmentSubcomponentFactory implements FragmentModule_ArtistDetailBrushFragment$ArtistDetailBrushFragmentSubcomponent.Factory {
        private final ApplicationComponentImpl applicationComponentImpl;

        private ArtistDetailBrushFragmentSubcomponentFactory(ApplicationComponentImpl applicationComponentImpl) {
            this.applicationComponentImpl = applicationComponentImpl;
        }

        @Override // com.amazon.mp3.dagger.modules.FragmentModule_ArtistDetailBrushFragment$ArtistDetailBrushFragmentSubcomponent.Factory, dagger.android.AndroidInjector.Factory
        public FragmentModule_ArtistDetailBrushFragment$ArtistDetailBrushFragmentSubcomponent create(ArtistDetailBrushFragment arg0) {
            Preconditions.checkNotNull(arg0);
            return new ArtistDetailBrushFragmentSubcomponentImpl(this.applicationComponentImpl, new ExploreModule(), arg0);
        }
    }

    private static final class PlaylistDetailFragmentSubcomponentFactory implements FragmentModule_PlaylistDetailFragment$PlaylistDetailFragmentSubcomponent.Factory {
        private final ApplicationComponentImpl applicationComponentImpl;

        private PlaylistDetailFragmentSubcomponentFactory(ApplicationComponentImpl applicationComponentImpl) {
            this.applicationComponentImpl = applicationComponentImpl;
        }

        @Override // com.amazon.mp3.dagger.modules.FragmentModule_PlaylistDetailFragment$PlaylistDetailFragmentSubcomponent.Factory, dagger.android.AndroidInjector.Factory
        public FragmentModule_PlaylistDetailFragment$PlaylistDetailFragmentSubcomponent create(PlaylistDetailFragment arg0) {
            Preconditions.checkNotNull(arg0);
            return new PlaylistDetailFragmentSubcomponentImpl(this.applicationComponentImpl, new ExploreModule(), arg0);
        }
    }

    private static final class UserPlaylistFragmentSubcomponentFactory implements CurateAndroidComponentsModule_UserPlaylistFragment$UserPlaylistFragmentSubcomponent.Factory {
        private final ApplicationComponentImpl applicationComponentImpl;

        private UserPlaylistFragmentSubcomponentFactory(ApplicationComponentImpl applicationComponentImpl) {
            this.applicationComponentImpl = applicationComponentImpl;
        }

        @Override // com.amazon.mp3.curate.dagger.CurateAndroidComponentsModule_UserPlaylistFragment$UserPlaylistFragmentSubcomponent.Factory, dagger.android.AndroidInjector.Factory
        public CurateAndroidComponentsModule_UserPlaylistFragment$UserPlaylistFragmentSubcomponent create(UserPlaylistFragment arg0) {
            Preconditions.checkNotNull(arg0);
            return new UserPlaylistFragmentSubcomponentImpl(this.applicationComponentImpl, new CurateModule(), arg0);
        }
    }

    private static final class UserPlaylistsFragmentSubcomponentFactory implements CurateAndroidComponentsModule_UserPlaylistsFragment$UserPlaylistsFragmentSubcomponent.Factory {
        private final ApplicationComponentImpl applicationComponentImpl;

        private UserPlaylistsFragmentSubcomponentFactory(ApplicationComponentImpl applicationComponentImpl) {
            this.applicationComponentImpl = applicationComponentImpl;
        }

        @Override // com.amazon.mp3.curate.dagger.CurateAndroidComponentsModule_UserPlaylistsFragment$UserPlaylistsFragmentSubcomponent.Factory, dagger.android.AndroidInjector.Factory
        public CurateAndroidComponentsModule_UserPlaylistsFragment$UserPlaylistsFragmentSubcomponent create(UserPlaylistsFragment arg0) {
            Preconditions.checkNotNull(arg0);
            return new UserPlaylistsFragmentSubcomponentImpl(this.applicationComponentImpl, new CurateModule(), arg0);
        }
    }

    private static final class PlaylistRecsSeeMoreFragmentSubcomponentFactory implements CurateAndroidComponentsModule_PlaylistRecsSeeMoreFragment$PlaylistRecsSeeMoreFragmentSubcomponent.Factory {
        private final ApplicationComponentImpl applicationComponentImpl;

        private PlaylistRecsSeeMoreFragmentSubcomponentFactory(ApplicationComponentImpl applicationComponentImpl) {
            this.applicationComponentImpl = applicationComponentImpl;
        }

        @Override // com.amazon.mp3.curate.dagger.CurateAndroidComponentsModule_PlaylistRecsSeeMoreFragment$PlaylistRecsSeeMoreFragmentSubcomponent.Factory, dagger.android.AndroidInjector.Factory
        public CurateAndroidComponentsModule_PlaylistRecsSeeMoreFragment$PlaylistRecsSeeMoreFragmentSubcomponent create(PlaylistRecsSeeMoreFragment arg0) {
            Preconditions.checkNotNull(arg0);
            return new PlaylistRecsSeeMoreFragmentSubcomponentImpl(this.applicationComponentImpl, new CurateModule(), arg0);
        }
    }

    private static final class PersonalizedPlaylistsFragmentSubcomponentFactory implements CurateAndroidComponentsModule_PersonalizedPlaylistsFragment$PersonalizedPlaylistsFragmentSubcomponent.Factory {
        private final ApplicationComponentImpl applicationComponentImpl;

        private PersonalizedPlaylistsFragmentSubcomponentFactory(ApplicationComponentImpl applicationComponentImpl) {
            this.applicationComponentImpl = applicationComponentImpl;
        }

        @Override // com.amazon.mp3.curate.dagger.CurateAndroidComponentsModule_PersonalizedPlaylistsFragment$PersonalizedPlaylistsFragmentSubcomponent.Factory, dagger.android.AndroidInjector.Factory
        public CurateAndroidComponentsModule_PersonalizedPlaylistsFragment$PersonalizedPlaylistsFragmentSubcomponent create(PersonalizedPlaylistsFragment arg0) {
            Preconditions.checkNotNull(arg0);
            return new PersonalizedPlaylistsFragmentSubcomponentImpl(this.applicationComponentImpl, new CurateModule(), arg0);
        }
    }

    private static final class MySoundtrackFragmentSubcomponentFactory implements CurateAndroidComponentsModule_MySoundtrackFragment$MySoundtrackFragmentSubcomponent.Factory {
        private final ApplicationComponentImpl applicationComponentImpl;

        private MySoundtrackFragmentSubcomponentFactory(ApplicationComponentImpl applicationComponentImpl) {
            this.applicationComponentImpl = applicationComponentImpl;
        }

        @Override // com.amazon.mp3.curate.dagger.CurateAndroidComponentsModule_MySoundtrackFragment$MySoundtrackFragmentSubcomponent.Factory, dagger.android.AndroidInjector.Factory
        public CurateAndroidComponentsModule_MySoundtrackFragment$MySoundtrackFragmentSubcomponent create(MySoundtrackFragment arg0) {
            Preconditions.checkNotNull(arg0);
            return new MySoundtrackFragmentSubcomponentImpl(this.applicationComponentImpl, new CurateModule(), arg0);
        }
    }

    private static final class TemplateFragmentSubcomponentFactory implements CurateAndroidComponentsModule_TemplateFragment$TemplateFragmentSubcomponent.Factory {
        private final ApplicationComponentImpl applicationComponentImpl;

        private TemplateFragmentSubcomponentFactory(ApplicationComponentImpl applicationComponentImpl) {
            this.applicationComponentImpl = applicationComponentImpl;
        }

        @Override // com.amazon.mp3.curate.dagger.CurateAndroidComponentsModule_TemplateFragment$TemplateFragmentSubcomponent.Factory, dagger.android.AndroidInjector.Factory
        public CurateAndroidComponentsModule_TemplateFragment$TemplateFragmentSubcomponent create(TemplateFragment arg0) {
            Preconditions.checkNotNull(arg0);
            return new TemplateFragmentSubcomponentImpl(this.applicationComponentImpl, arg0);
        }
    }

    private static final class PlaylistIntentReceiverServiceSubcomponentFactory implements CurateAndroidComponentsModule_PlaylistIntentReceiverService$PlaylistIntentReceiverServiceSubcomponent.Factory {
        private final ApplicationComponentImpl applicationComponentImpl;

        private PlaylistIntentReceiverServiceSubcomponentFactory(ApplicationComponentImpl applicationComponentImpl) {
            this.applicationComponentImpl = applicationComponentImpl;
        }

        @Override // com.amazon.mp3.curate.dagger.CurateAndroidComponentsModule_PlaylistIntentReceiverService$PlaylistIntentReceiverServiceSubcomponent.Factory, dagger.android.AndroidInjector.Factory
        public CurateAndroidComponentsModule_PlaylistIntentReceiverService$PlaylistIntentReceiverServiceSubcomponent create(PlaylistIntentReceiverService arg0) {
            Preconditions.checkNotNull(arg0);
            return new PlaylistIntentReceiverServiceSubcomponentImpl(this.applicationComponentImpl, new CurateModule(), arg0);
        }
    }

    private static final class PlaybackFragmentSubcomponentFactory implements ExploreAndroidComponentsModule_PlaybackFragment$PlaybackFragmentSubcomponent.Factory {
        private final ApplicationComponentImpl applicationComponentImpl;

        private PlaybackFragmentSubcomponentFactory(ApplicationComponentImpl applicationComponentImpl) {
            this.applicationComponentImpl = applicationComponentImpl;
        }

        @Override // com.amazon.mp3.explore.dagger.ExploreAndroidComponentsModule_PlaybackFragment$PlaybackFragmentSubcomponent.Factory, dagger.android.AndroidInjector.Factory
        public ExploreAndroidComponentsModule_PlaybackFragment$PlaybackFragmentSubcomponent create(PlaybackFragment arg0) {
            Preconditions.checkNotNull(arg0);
            return new PlaybackFragmentSubcomponentImpl(this.applicationComponentImpl, new ExploreModule(), arg0);
        }
    }

    private static final class SwipeablePagesFragmentSubcomponentFactory implements ExploreAndroidComponentsModule_SwipeablePagesFragment$SwipeablePagesFragmentSubcomponent.Factory {
        private final ApplicationComponentImpl applicationComponentImpl;

        private SwipeablePagesFragmentSubcomponentFactory(ApplicationComponentImpl applicationComponentImpl) {
            this.applicationComponentImpl = applicationComponentImpl;
        }

        @Override // com.amazon.mp3.explore.dagger.ExploreAndroidComponentsModule_SwipeablePagesFragment$SwipeablePagesFragmentSubcomponent.Factory, dagger.android.AndroidInjector.Factory
        public ExploreAndroidComponentsModule_SwipeablePagesFragment$SwipeablePagesFragmentSubcomponent create(SwipeablePagesFragment arg0) {
            Preconditions.checkNotNull(arg0);
            return new SwipeablePagesFragmentSubcomponentImpl(this.applicationComponentImpl, new ExploreModule(), arg0);
        }
    }

    private static final class StageSwipeablePagesFragmentSubcomponentFactory implements ExploreAndroidComponentsModule_StageSwipeablePagesFragment$StageSwipeablePagesFragmentSubcomponent.Factory {
        private final ApplicationComponentImpl applicationComponentImpl;

        private StageSwipeablePagesFragmentSubcomponentFactory(ApplicationComponentImpl applicationComponentImpl) {
            this.applicationComponentImpl = applicationComponentImpl;
        }

        @Override // com.amazon.mp3.explore.dagger.ExploreAndroidComponentsModule_StageSwipeablePagesFragment$StageSwipeablePagesFragmentSubcomponent.Factory, dagger.android.AndroidInjector.Factory
        public ExploreAndroidComponentsModule_StageSwipeablePagesFragment$StageSwipeablePagesFragmentSubcomponent create(StageSwipeablePagesFragment arg0) {
            Preconditions.checkNotNull(arg0);
            return new StageSwipeablePagesFragmentSubcomponentImpl(this.applicationComponentImpl, new ExploreModule(), arg0);
        }
    }

    private static final class LiveStreamActivitySubcomponentFactory implements ExploreAndroidComponentsModule_LiveStreamActivity$LiveStreamActivitySubcomponent.Factory {
        private final ApplicationComponentImpl applicationComponentImpl;

        private LiveStreamActivitySubcomponentFactory(ApplicationComponentImpl applicationComponentImpl) {
            this.applicationComponentImpl = applicationComponentImpl;
        }

        @Override // com.amazon.mp3.explore.dagger.ExploreAndroidComponentsModule_LiveStreamActivity$LiveStreamActivitySubcomponent.Factory, dagger.android.AndroidInjector.Factory
        public ExploreAndroidComponentsModule_LiveStreamActivity$LiveStreamActivitySubcomponent create(LiveStreamActivity arg0) {
            Preconditions.checkNotNull(arg0);
            return new LiveStreamActivitySubcomponentImpl(this.applicationComponentImpl, new ExploreModule(), arg0);
        }
    }

    private static final class AmpPlaybackActivitySubcomponentFactory implements ExploreAndroidComponentsModule_AmpPlaybackActivity$AmpPlaybackActivitySubcomponent.Factory {
        private final ApplicationComponentImpl applicationComponentImpl;

        private AmpPlaybackActivitySubcomponentFactory(ApplicationComponentImpl applicationComponentImpl) {
            this.applicationComponentImpl = applicationComponentImpl;
        }

        @Override // com.amazon.mp3.explore.dagger.ExploreAndroidComponentsModule_AmpPlaybackActivity$AmpPlaybackActivitySubcomponent.Factory, dagger.android.AndroidInjector.Factory
        public ExploreAndroidComponentsModule_AmpPlaybackActivity$AmpPlaybackActivitySubcomponent create(AmpPlaybackActivity arg0) {
            Preconditions.checkNotNull(arg0);
            return new AmpPlaybackActivitySubcomponentImpl(this.applicationComponentImpl, new ExploreModule(), arg0);
        }
    }

    private static final class GalleryFragmentSubcomponentFactory implements ExploreAndroidComponentsModule_GalleryFragment$GalleryFragmentSubcomponent.Factory {
        private final ApplicationComponentImpl applicationComponentImpl;

        private GalleryFragmentSubcomponentFactory(ApplicationComponentImpl applicationComponentImpl) {
            this.applicationComponentImpl = applicationComponentImpl;
        }

        @Override // com.amazon.mp3.explore.dagger.ExploreAndroidComponentsModule_GalleryFragment$GalleryFragmentSubcomponent.Factory, dagger.android.AndroidInjector.Factory
        public ExploreAndroidComponentsModule_GalleryFragment$GalleryFragmentSubcomponent create(GalleryFragment arg0) {
            Preconditions.checkNotNull(arg0);
            return new GalleryFragmentSubcomponentImpl(this.applicationComponentImpl, new ExploreModule(), arg0);
        }
    }

    private static final class VideoQueueActivitySubcomponentFactory implements ExploreAndroidComponentsModule_VideoQueueActivity$VideoQueueActivitySubcomponent.Factory {
        private final ApplicationComponentImpl applicationComponentImpl;

        private VideoQueueActivitySubcomponentFactory(ApplicationComponentImpl applicationComponentImpl) {
            this.applicationComponentImpl = applicationComponentImpl;
        }

        @Override // com.amazon.mp3.explore.dagger.ExploreAndroidComponentsModule_VideoQueueActivity$VideoQueueActivitySubcomponent.Factory, dagger.android.AndroidInjector.Factory
        public ExploreAndroidComponentsModule_VideoQueueActivity$VideoQueueActivitySubcomponent create(VideoQueueActivity arg0) {
            Preconditions.checkNotNull(arg0);
            return new VideoQueueActivitySubcomponentImpl(this.applicationComponentImpl, new ExploreModule(), arg0);
        }
    }

    private static final class DetailFragmentSubcomponentFactory implements ExploreAndroidComponentsModule_DetailFragment$DetailFragmentSubcomponent.Factory {
        private final ApplicationComponentImpl applicationComponentImpl;

        private DetailFragmentSubcomponentFactory(ApplicationComponentImpl applicationComponentImpl) {
            this.applicationComponentImpl = applicationComponentImpl;
        }

        @Override // com.amazon.mp3.explore.dagger.ExploreAndroidComponentsModule_DetailFragment$DetailFragmentSubcomponent.Factory, dagger.android.AndroidInjector.Factory
        public ExploreAndroidComponentsModule_DetailFragment$DetailFragmentSubcomponent create(DetailFragment arg0) {
            Preconditions.checkNotNull(arg0);
            return new DetailFragmentSubcomponentImpl(this.applicationComponentImpl, new ExploreModule(), arg0);
        }
    }

    private static final class SelectionFragmentSubcomponentFactory implements ExploreAndroidComponentsModule_SelectionFragment$SelectionFragmentSubcomponent.Factory {
        private final ApplicationComponentImpl applicationComponentImpl;

        private SelectionFragmentSubcomponentFactory(ApplicationComponentImpl applicationComponentImpl) {
            this.applicationComponentImpl = applicationComponentImpl;
        }

        @Override // com.amazon.mp3.explore.dagger.ExploreAndroidComponentsModule_SelectionFragment$SelectionFragmentSubcomponent.Factory, dagger.android.AndroidInjector.Factory
        public ExploreAndroidComponentsModule_SelectionFragment$SelectionFragmentSubcomponent create(SelectionFragment arg0) {
            Preconditions.checkNotNull(arg0);
            return new SelectionFragmentSubcomponentImpl(this.applicationComponentImpl, new ExploreModule(), arg0);
        }
    }

    private static final class MultiSelectorFragmentSubcomponentFactory implements ExploreAndroidComponentsModule_MultiSelectorFragment$MultiSelectorFragmentSubcomponent.Factory {
        private final ApplicationComponentImpl applicationComponentImpl;

        private MultiSelectorFragmentSubcomponentFactory(ApplicationComponentImpl applicationComponentImpl) {
            this.applicationComponentImpl = applicationComponentImpl;
        }

        @Override // com.amazon.mp3.explore.dagger.ExploreAndroidComponentsModule_MultiSelectorFragment$MultiSelectorFragmentSubcomponent.Factory, dagger.android.AndroidInjector.Factory
        public ExploreAndroidComponentsModule_MultiSelectorFragment$MultiSelectorFragmentSubcomponent create(MultiSelectorFragment arg0) {
            Preconditions.checkNotNull(arg0);
            return new MultiSelectorFragmentSubcomponentImpl(this.applicationComponentImpl, new ExploreModule(), arg0);
        }
    }

    private static final class MultiSelectorTabFragmentSubcomponentFactory implements ExploreAndroidComponentsModule_MultiSelectorTabFragment$MultiSelectorTabFragmentSubcomponent.Factory {
        private final ApplicationComponentImpl applicationComponentImpl;

        private MultiSelectorTabFragmentSubcomponentFactory(ApplicationComponentImpl applicationComponentImpl) {
            this.applicationComponentImpl = applicationComponentImpl;
        }

        @Override // com.amazon.mp3.explore.dagger.ExploreAndroidComponentsModule_MultiSelectorTabFragment$MultiSelectorTabFragmentSubcomponent.Factory, dagger.android.AndroidInjector.Factory
        public ExploreAndroidComponentsModule_MultiSelectorTabFragment$MultiSelectorTabFragmentSubcomponent create(MultiSelectorTabFragment arg0) {
            Preconditions.checkNotNull(arg0);
            return new MultiSelectorTabFragmentSubcomponentImpl(this.applicationComponentImpl, arg0);
        }
    }

    private static final class WatchSessionServiceSubcomponentFactory implements ExploreAndroidComponentsModule_WatchSessionService$WatchSessionServiceSubcomponent.Factory {
        private final ApplicationComponentImpl applicationComponentImpl;

        private WatchSessionServiceSubcomponentFactory(ApplicationComponentImpl applicationComponentImpl) {
            this.applicationComponentImpl = applicationComponentImpl;
        }

        @Override // com.amazon.mp3.explore.dagger.ExploreAndroidComponentsModule_WatchSessionService$WatchSessionServiceSubcomponent.Factory, dagger.android.AndroidInjector.Factory
        public ExploreAndroidComponentsModule_WatchSessionService$WatchSessionServiceSubcomponent create(WatchSessionService arg0) {
            Preconditions.checkNotNull(arg0);
            return new WatchSessionServiceSubcomponentImpl(this.applicationComponentImpl, new ExploreModule(), arg0);
        }
    }

    private static final class VideoStoryFragmentSubcomponentFactory implements ExploreAndroidComponentsModule_VideoStoryFragment$VideoStoryFragmentSubcomponent.Factory {
        private final ApplicationComponentImpl applicationComponentImpl;

        private VideoStoryFragmentSubcomponentFactory(ApplicationComponentImpl applicationComponentImpl) {
            this.applicationComponentImpl = applicationComponentImpl;
        }

        @Override // com.amazon.mp3.explore.dagger.ExploreAndroidComponentsModule_VideoStoryFragment$VideoStoryFragmentSubcomponent.Factory, dagger.android.AndroidInjector.Factory
        public ExploreAndroidComponentsModule_VideoStoryFragment$VideoStoryFragmentSubcomponent create(VideoStoryFragment arg0) {
            Preconditions.checkNotNull(arg0);
            return new VideoStoryFragmentSubcomponentImpl(this.applicationComponentImpl, new ExploreModule(), arg0);
        }
    }

    private static final class CastingSkillServiceSubcomponentFactory implements ExploreAndroidComponentsModule_CastingSkillService$CastingSkillServiceSubcomponent.Factory {
        private final ApplicationComponentImpl applicationComponentImpl;

        private CastingSkillServiceSubcomponentFactory(ApplicationComponentImpl applicationComponentImpl) {
            this.applicationComponentImpl = applicationComponentImpl;
        }

        @Override // com.amazon.mp3.explore.dagger.ExploreAndroidComponentsModule_CastingSkillService$CastingSkillServiceSubcomponent.Factory, dagger.android.AndroidInjector.Factory
        public ExploreAndroidComponentsModule_CastingSkillService$CastingSkillServiceSubcomponent create(CastingSkillService arg0) {
            Preconditions.checkNotNull(arg0);
            return new CastingSkillServiceSubcomponentImpl(this.applicationComponentImpl, new ExploreModule(), arg0);
        }
    }

    private static final class FindBrushFragmentSubcomponentFactory implements FindAndroidComponentsModule_FindBrushFragment$FindBrushFragmentSubcomponent.Factory {
        private final ApplicationComponentImpl applicationComponentImpl;

        private FindBrushFragmentSubcomponentFactory(ApplicationComponentImpl applicationComponentImpl) {
            this.applicationComponentImpl = applicationComponentImpl;
        }

        @Override // com.amazon.mp3.find.dagger.FindAndroidComponentsModule_FindBrushFragment$FindBrushFragmentSubcomponent.Factory, dagger.android.AndroidInjector.Factory
        public FindAndroidComponentsModule_FindBrushFragment$FindBrushFragmentSubcomponent create(FindBrushFragment arg0) {
            Preconditions.checkNotNull(arg0);
            return new FindBrushFragmentSubcomponentImpl(this.applicationComponentImpl, arg0);
        }
    }

    private static final class FindFilterDialogFragmentSubcomponentFactory implements FindAndroidComponentsModule_FindFilterDialogFragment$FindFilterDialogFragmentSubcomponent.Factory {
        private final ApplicationComponentImpl applicationComponentImpl;

        private FindFilterDialogFragmentSubcomponentFactory(ApplicationComponentImpl applicationComponentImpl) {
            this.applicationComponentImpl = applicationComponentImpl;
        }

        @Override // com.amazon.mp3.find.dagger.FindAndroidComponentsModule_FindFilterDialogFragment$FindFilterDialogFragmentSubcomponent.Factory, dagger.android.AndroidInjector.Factory
        public FindAndroidComponentsModule_FindFilterDialogFragment$FindFilterDialogFragmentSubcomponent create(FindFilterDialogFragment arg0) {
            Preconditions.checkNotNull(arg0);
            return new FindFilterDialogFragmentSubcomponentImpl(this.applicationComponentImpl, new FindModule(), arg0);
        }
    }

    private static final class EntityTrackingIntentServiceSubcomponentFactory implements FindAndroidComponentsModule_EntityTrackingIntentService$EntityTrackingIntentServiceSubcomponent.Factory {
        private final ApplicationComponentImpl applicationComponentImpl;

        private EntityTrackingIntentServiceSubcomponentFactory(ApplicationComponentImpl applicationComponentImpl) {
            this.applicationComponentImpl = applicationComponentImpl;
        }

        @Override // com.amazon.mp3.find.dagger.FindAndroidComponentsModule_EntityTrackingIntentService$EntityTrackingIntentServiceSubcomponent.Factory, dagger.android.AndroidInjector.Factory
        public FindAndroidComponentsModule_EntityTrackingIntentService$EntityTrackingIntentServiceSubcomponent create(EntityTrackingIntentService arg0) {
            Preconditions.checkNotNull(arg0);
            return new EntityTrackingIntentServiceSubcomponentImpl(this.applicationComponentImpl, new FindModule(), arg0);
        }
    }

    private static final class SearchBrushFragmentSubcomponentFactory implements FindAndroidComponentsModule_SearchBrushFragment$SearchBrushFragmentSubcomponent.Factory {
        private final ApplicationComponentImpl applicationComponentImpl;

        private SearchBrushFragmentSubcomponentFactory(ApplicationComponentImpl applicationComponentImpl) {
            this.applicationComponentImpl = applicationComponentImpl;
        }

        @Override // com.amazon.mp3.find.dagger.FindAndroidComponentsModule_SearchBrushFragment$SearchBrushFragmentSubcomponent.Factory, dagger.android.AndroidInjector.Factory
        public FindAndroidComponentsModule_SearchBrushFragment$SearchBrushFragmentSubcomponent create(SearchBrushFragment arg0) {
            Preconditions.checkNotNull(arg0);
            return new SearchBrushFragmentSubcomponentImpl(this.applicationComponentImpl, new FindModule(), arg0);
        }
    }

    private static final class CarModeSearchBrushFragmentSubcomponentFactory implements FindAndroidComponentsModule_CarModeSearchBrushFragment$CarModeSearchBrushFragmentSubcomponent.Factory {
        private final ApplicationComponentImpl applicationComponentImpl;

        private CarModeSearchBrushFragmentSubcomponentFactory(ApplicationComponentImpl applicationComponentImpl) {
            this.applicationComponentImpl = applicationComponentImpl;
        }

        @Override // com.amazon.mp3.find.dagger.FindAndroidComponentsModule_CarModeSearchBrushFragment$CarModeSearchBrushFragmentSubcomponent.Factory, dagger.android.AndroidInjector.Factory
        public FindAndroidComponentsModule_CarModeSearchBrushFragment$CarModeSearchBrushFragmentSubcomponent create(CarModeSearchBrushFragment arg0) {
            Preconditions.checkNotNull(arg0);
            return new CarModeSearchBrushFragmentSubcomponentImpl(this.applicationComponentImpl, new FindModule(), arg0);
        }
    }

    private static final class FindLandingFragmentSubcomponentFactory implements FindAndroidComponentsModule_FindLandingFragment$FindLandingFragmentSubcomponent.Factory {
        private final ApplicationComponentImpl applicationComponentImpl;

        private FindLandingFragmentSubcomponentFactory(ApplicationComponentImpl applicationComponentImpl) {
            this.applicationComponentImpl = applicationComponentImpl;
        }

        @Override // com.amazon.mp3.find.dagger.FindAndroidComponentsModule_FindLandingFragment$FindLandingFragmentSubcomponent.Factory, dagger.android.AndroidInjector.Factory
        public FindAndroidComponentsModule_FindLandingFragment$FindLandingFragmentSubcomponent create(FindLandingFragment arg0) {
            Preconditions.checkNotNull(arg0);
            return new FindLandingFragmentSubcomponentImpl(this.applicationComponentImpl, new FindModule(), arg0);
        }
    }

    private static final class FindSkyfireFragmentSubcomponentFactory implements FindAndroidComponentsModule_FindSkyfireFragment$FindSkyfireFragmentSubcomponent.Factory {
        private final ApplicationComponentImpl applicationComponentImpl;

        private FindSkyfireFragmentSubcomponentFactory(ApplicationComponentImpl applicationComponentImpl) {
            this.applicationComponentImpl = applicationComponentImpl;
        }

        @Override // com.amazon.mp3.find.dagger.FindAndroidComponentsModule_FindSkyfireFragment$FindSkyfireFragmentSubcomponent.Factory, dagger.android.AndroidInjector.Factory
        public FindAndroidComponentsModule_FindSkyfireFragment$FindSkyfireFragmentSubcomponent create(FindSkyfireFragment arg0) {
            Preconditions.checkNotNull(arg0);
            return new FindSkyfireFragmentSubcomponentImpl(this.applicationComponentImpl, new FindModule(), arg0);
        }
    }

    private static final class SearchGalleryFragmentSubcomponentFactory implements FindAndroidComponentsModule_SearchGalleryFragment$SearchGalleryFragmentSubcomponent.Factory {
        private final ApplicationComponentImpl applicationComponentImpl;

        private SearchGalleryFragmentSubcomponentFactory(ApplicationComponentImpl applicationComponentImpl) {
            this.applicationComponentImpl = applicationComponentImpl;
        }

        @Override // com.amazon.mp3.find.dagger.FindAndroidComponentsModule_SearchGalleryFragment$SearchGalleryFragmentSubcomponent.Factory, dagger.android.AndroidInjector.Factory
        public FindAndroidComponentsModule_SearchGalleryFragment$SearchGalleryFragmentSubcomponent create(SearchGalleryFragment arg0) {
            Preconditions.checkNotNull(arg0);
            return new SearchGalleryFragmentSubcomponentImpl(this.applicationComponentImpl, new FindModule(), arg0);
        }
    }

    private static final class SearchHostFragmentSubcomponentFactory implements FindAndroidComponentsModule_SearchHostFragment$SearchHostFragmentSubcomponent.Factory {
        private final ApplicationComponentImpl applicationComponentImpl;

        private SearchHostFragmentSubcomponentFactory(ApplicationComponentImpl applicationComponentImpl) {
            this.applicationComponentImpl = applicationComponentImpl;
        }

        @Override // com.amazon.mp3.find.dagger.FindAndroidComponentsModule_SearchHostFragment$SearchHostFragmentSubcomponent.Factory, dagger.android.AndroidInjector.Factory
        public FindAndroidComponentsModule_SearchHostFragment$SearchHostFragmentSubcomponent create(SearchHostFragment arg0) {
            Preconditions.checkNotNull(arg0);
            return new SearchHostFragmentSubcomponentImpl(this.applicationComponentImpl, new FindModule(), arg0);
        }
    }

    private static final class ContactImportWebViewFragmentSubcomponentFactory implements ConnectAndroidComponentsModule_ContactImportWebViewFragment$ContactImportWebViewFragmentSubcomponent.Factory {
        private final ApplicationComponentImpl applicationComponentImpl;

        private ContactImportWebViewFragmentSubcomponentFactory(ApplicationComponentImpl applicationComponentImpl) {
            this.applicationComponentImpl = applicationComponentImpl;
        }

        @Override // com.amazon.mp3.connect.dagger.ConnectAndroidComponentsModule_ContactImportWebViewFragment$ContactImportWebViewFragmentSubcomponent.Factory, dagger.android.AndroidInjector.Factory
        public ConnectAndroidComponentsModule_ContactImportWebViewFragment$ContactImportWebViewFragmentSubcomponent create(ContactImportWebViewFragment arg0) {
            Preconditions.checkNotNull(arg0);
            return new ContactImportWebViewFragmentSubcomponentImpl(this.applicationComponentImpl, new ConnectModule(), arg0);
        }
    }

    private static final class EditCustomerProfileFragmentSubcomponentFactory implements ConnectAndroidComponentsModule_EditCustomerProfileDialogFragment$EditCustomerProfileFragmentSubcomponent.Factory {
        private final ApplicationComponentImpl applicationComponentImpl;

        private EditCustomerProfileFragmentSubcomponentFactory(ApplicationComponentImpl applicationComponentImpl) {
            this.applicationComponentImpl = applicationComponentImpl;
        }

        @Override // com.amazon.mp3.connect.dagger.ConnectAndroidComponentsModule_EditCustomerProfileDialogFragment$EditCustomerProfileFragmentSubcomponent.Factory, dagger.android.AndroidInjector.Factory
        public ConnectAndroidComponentsModule_EditCustomerProfileDialogFragment$EditCustomerProfileFragmentSubcomponent create(EditCustomerProfileFragment arg0) {
            Preconditions.checkNotNull(arg0);
            return new EditCustomerProfileFragmentSubcomponentImpl(this.applicationComponentImpl, arg0);
        }
    }

    private static final class SharingFragmentSubcomponentFactory implements ShareAndroidComponentsModule_SharingFragment$SharingFragmentSubcomponent.Factory {
        private final ApplicationComponentImpl applicationComponentImpl;

        private SharingFragmentSubcomponentFactory(ApplicationComponentImpl applicationComponentImpl) {
            this.applicationComponentImpl = applicationComponentImpl;
        }

        @Override // com.amazon.mp3.sharing.dagger.ShareAndroidComponentsModule_SharingFragment$SharingFragmentSubcomponent.Factory, dagger.android.AndroidInjector.Factory
        public ShareAndroidComponentsModule_SharingFragment$SharingFragmentSubcomponent create(SharingFragment arg0) {
            Preconditions.checkNotNull(arg0);
            return new SharingFragmentSubcomponentImpl(this.applicationComponentImpl, new ShareModule(), arg0);
        }
    }

    private static final class SharingFragmentV2SubcomponentFactory implements ShareAndroidComponentsModule_SharingFragmentV2$SharingFragmentV2Subcomponent.Factory {
        private final ApplicationComponentImpl applicationComponentImpl;

        private SharingFragmentV2SubcomponentFactory(ApplicationComponentImpl applicationComponentImpl) {
            this.applicationComponentImpl = applicationComponentImpl;
        }

        @Override // com.amazon.mp3.sharing.dagger.ShareAndroidComponentsModule_SharingFragmentV2$SharingFragmentV2Subcomponent.Factory, dagger.android.AndroidInjector.Factory
        public ShareAndroidComponentsModule_SharingFragmentV2$SharingFragmentV2Subcomponent create(SharingFragmentV2 arg0) {
            Preconditions.checkNotNull(arg0);
            return new SharingFragmentV2SubcomponentImpl(this.applicationComponentImpl, new ShareModule(), arg0);
        }
    }

    private static final class LyricsCardSelectionFragmentSubcomponentFactory implements LyricsSharingComponentsModule_LyricsCardSelectionFragment$LyricsCardSelectionFragmentSubcomponent.Factory {
        private final ApplicationComponentImpl applicationComponentImpl;

        private LyricsCardSelectionFragmentSubcomponentFactory(ApplicationComponentImpl applicationComponentImpl) {
            this.applicationComponentImpl = applicationComponentImpl;
        }

        @Override // com.amazon.mp3.sharing.dagger.LyricsSharingComponentsModule_LyricsCardSelectionFragment$LyricsCardSelectionFragmentSubcomponent.Factory, dagger.android.AndroidInjector.Factory
        public LyricsSharingComponentsModule_LyricsCardSelectionFragment$LyricsCardSelectionFragmentSubcomponent create(LyricsCardSelectionFragment arg0) {
            Preconditions.checkNotNull(arg0);
            return new LyricsCardSelectionFragmentSubcomponentImpl(this.applicationComponentImpl, new ShareModule(), arg0);
        }
    }

    private static final class LiveEventDetailFragmentSubcomponentFactory implements ALPSAndroidComponentsModule_LiveEventDetailFragment$LiveEventDetailFragmentSubcomponent.Factory {
        private final ApplicationComponentImpl applicationComponentImpl;

        private LiveEventDetailFragmentSubcomponentFactory(ApplicationComponentImpl applicationComponentImpl) {
            this.applicationComponentImpl = applicationComponentImpl;
        }

        @Override // com.amazon.mp3.alps.dagger.ALPSAndroidComponentsModule_LiveEventDetailFragment$LiveEventDetailFragmentSubcomponent.Factory, dagger.android.AndroidInjector.Factory
        public ALPSAndroidComponentsModule_LiveEventDetailFragment$LiveEventDetailFragmentSubcomponent create(LiveEventDetailFragment arg0) {
            Preconditions.checkNotNull(arg0);
            return new LiveEventDetailFragmentSubcomponentImpl(this.applicationComponentImpl, new ALPSModule(), arg0);
        }
    }

    private static final class NowPlayingComponentFactory implements NowPlayingComponent.Factory {
        private final ApplicationComponentImpl applicationComponentImpl;

        private NowPlayingComponentFactory(ApplicationComponentImpl applicationComponentImpl) {
            this.applicationComponentImpl = applicationComponentImpl;
        }

        @Override // com.amazon.mp3.starlight.subcomponent.NowPlayingComponent.Factory
        public NowPlayingComponent create() {
            return new NowPlayingComponentImpl(this.applicationComponentImpl, new StarlightModule(), new StageModule());
        }
    }

    private static final class ArtistDetailBrushFragmentSubcomponentImpl implements FragmentModule_ArtistDetailBrushFragment$ArtistDetailBrushFragmentSubcomponent {
        private final ApplicationComponentImpl applicationComponentImpl;
        private final ArtistDetailBrushFragmentSubcomponentImpl artistDetailBrushFragmentSubcomponentImpl;
        private final ExploreModule exploreModule;
        private Provider<CapabilitiesInfo> provideCapabilitiesInfoProvider;
        private Provider<CastingProvider> provideCastingProvider;
        private Provider<DownloadProvider> provideDownloadProvider;
        private Provider<EnvironmentProvider> provideEnvironmentProvider;
        private Provider<ExploreManagerFactory> provideExploreManagerFactoryProvider;
        private Provider<ExternalActionProvider> provideExternalActionProvider;
        private Provider<LibraryProvider> provideLibraryProvider;
        private Provider<NavigationProvider> provideNavigationProvider;
        private Provider<NetworkInterceptor> provideNetworkInterceptorProvider;
        private Provider<NotificationProvider> provideNotificationProvider;
        private Provider<PlaybackProvider> providePlaybackProvider;
        private Provider<SharingProvider> provideSharingProvider;
        private Provider<SkillLatencyTracker> provideSkillLatencyTrackerProvider;
        private Provider<SkyFireConfigurationProvider> provideSkyFireConfigurationProvider;
        private Provider<SkyFireProvider> provideSkyFireProvider;
        private Provider<StorageProvider> provideStorageProvider;
        private Provider<UserSubscriptionProvider> provideUserSubscriptionProvider;

        private ArtistDetailBrushFragmentSubcomponentImpl(ApplicationComponentImpl applicationComponentImpl, ExploreModule exploreModuleParam, ArtistDetailBrushFragment arg0Param) {
            this.artistDetailBrushFragmentSubcomponentImpl = this;
            this.applicationComponentImpl = applicationComponentImpl;
            this.exploreModule = exploreModuleParam;
            initialize(exploreModuleParam, arg0Param);
        }

        private FeaturedContainer featuredContainer() {
            return ExploreModule_ProvideFeaturedContainerFactory.provideFeaturedContainer(this.exploreModule, this.applicationComponentImpl.application, this.provideSkyFireProvider.get(), this.provideSkyFireConfigurationProvider.get(), this.provideExternalActionProvider.get(), PlatformProvidersModule_ProvideMetricsProviderFactory.provideMetricsProvider(this.applicationComponentImpl.platformProvidersModule), this.provideNavigationProvider.get(), this.provideCastingProvider.get());
        }

        private void initialize(final ExploreModule exploreModuleParam, final ArtistDetailBrushFragment arg0Param) {
            this.provideNavigationProvider = SingleCheck.provider(ExploreModule_ProvideNavigationProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideStorageProvider = SingleCheck.provider(ExploreModule_ProvideStorageProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideEnvironmentProvider = SingleCheck.provider(ExploreModule_ProvideEnvironmentProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.providePlaybackProvider = SingleCheck.provider(ExploreModule_ProvidePlaybackProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideUserSubscriptionProvider = SingleCheck.provider(ExploreModule_ProvideUserSubscriptionProviderFactory.create(exploreModuleParam));
            this.provideCastingProvider = SingleCheck.provider(ExploreModule_ProvideCastingProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideExploreManagerFactoryProvider = SingleCheck.provider(ExploreModule_ProvideExploreManagerFactoryFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider, this.applicationComponentImpl.provideAuthenticationProvider, this.applicationComponentImpl.provideCustomerMetadataProvider, this.applicationComponentImpl.provideDeviceInfoProvider, this.applicationComponentImpl.provideBuildInfoProvider, this.provideNavigationProvider, this.provideStorageProvider, this.provideEnvironmentProvider, this.applicationComponentImpl.provideMetricsProvider, this.providePlaybackProvider, this.provideUserSubscriptionProvider, this.provideCastingProvider));
            this.provideCapabilitiesInfoProvider = SingleCheck.provider(ExploreModule_ProvideCapabilitiesInfoFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideNetworkInterceptorProvider = SingleCheck.provider(ExploreModule_ProvideNetworkInterceptorFactory.create(exploreModuleParam));
            this.provideSkillLatencyTrackerProvider = SingleCheck.provider(ExploreModule_ProvideSkillLatencyTrackerFactory.create(exploreModuleParam));
            this.provideSkyFireConfigurationProvider = SingleCheck.provider(ExploreModule_ProvideSkyFireConfigurationProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideSkyFireProvider = SingleCheck.provider(ExploreModule_ProvideSkyFireProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider, this.applicationComponentImpl.provideAuthenticationProvider, this.applicationComponentImpl.provideCustomerMetadataProvider, this.applicationComponentImpl.provideDeviceInfoProvider, this.applicationComponentImpl.provideBuildInfoProvider, this.providePlaybackProvider, this.provideCapabilitiesInfoProvider, this.provideNetworkInterceptorProvider, this.provideSkillLatencyTrackerProvider, this.provideSkyFireConfigurationProvider));
            this.provideLibraryProvider = SingleCheck.provider(ExploreModule_ProvideLibraryProviderFactory.create(exploreModuleParam));
            this.provideNotificationProvider = SingleCheck.provider(ExploreModule_ProvideNotificationProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideSharingProvider = SingleCheck.provider(ExploreModule_ProvideSharingProviderFactory.create(exploreModuleParam));
            Provider<DownloadProvider> provider = SingleCheck.provider(ExploreModule_ProvideDownloadProviderFactory.create(exploreModuleParam));
            this.provideDownloadProvider = provider;
            this.provideExternalActionProvider = SingleCheck.provider(ExploreModule_ProvideExternalActionProviderFactory.create(exploreModuleParam, this.provideLibraryProvider, this.provideNavigationProvider, this.provideNotificationProvider, this.providePlaybackProvider, this.provideSharingProvider, provider));
        }

        @Override // dagger.android.AndroidInjector
        public void inject(ArtistDetailBrushFragment arg0) {
            injectArtistDetailBrushFragment(arg0);
        }

        private ArtistDetailBrushFragment injectArtistDetailBrushFragment(ArtistDetailBrushFragment instance) {
            ArtistDetailBrushFragment_MembersInjector.injectExploreManagerFactory(instance, this.provideExploreManagerFactoryProvider.get());
            ArtistDetailBrushFragment_MembersInjector.injectFeaturedContainer(instance, featuredContainer());
            return instance;
        }
    }

    private static final class PlaylistDetailFragmentSubcomponentImpl implements FragmentModule_PlaylistDetailFragment$PlaylistDetailFragmentSubcomponent {
        private final ApplicationComponentImpl applicationComponentImpl;
        private final PlaylistDetailFragmentSubcomponentImpl playlistDetailFragmentSubcomponentImpl;
        private Provider<CastingProvider> provideCastingProvider;
        private Provider<EnvironmentProvider> provideEnvironmentProvider;
        private Provider<ExploreManagerFactory> provideExploreManagerFactoryProvider;
        private Provider<NavigationProvider> provideNavigationProvider;
        private Provider<PlaybackProvider> providePlaybackProvider;
        private Provider<StorageProvider> provideStorageProvider;
        private Provider<UserSubscriptionProvider> provideUserSubscriptionProvider;

        private PlaylistDetailFragmentSubcomponentImpl(ApplicationComponentImpl applicationComponentImpl, ExploreModule exploreModuleParam, PlaylistDetailFragment arg0Param) {
            this.playlistDetailFragmentSubcomponentImpl = this;
            this.applicationComponentImpl = applicationComponentImpl;
            initialize(exploreModuleParam, arg0Param);
        }

        private void initialize(final ExploreModule exploreModuleParam, final PlaylistDetailFragment arg0Param) {
            this.provideNavigationProvider = SingleCheck.provider(ExploreModule_ProvideNavigationProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideStorageProvider = SingleCheck.provider(ExploreModule_ProvideStorageProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideEnvironmentProvider = SingleCheck.provider(ExploreModule_ProvideEnvironmentProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.providePlaybackProvider = SingleCheck.provider(ExploreModule_ProvidePlaybackProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideUserSubscriptionProvider = SingleCheck.provider(ExploreModule_ProvideUserSubscriptionProviderFactory.create(exploreModuleParam));
            this.provideCastingProvider = SingleCheck.provider(ExploreModule_ProvideCastingProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideExploreManagerFactoryProvider = SingleCheck.provider(ExploreModule_ProvideExploreManagerFactoryFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider, this.applicationComponentImpl.provideAuthenticationProvider, this.applicationComponentImpl.provideCustomerMetadataProvider, this.applicationComponentImpl.provideDeviceInfoProvider, this.applicationComponentImpl.provideBuildInfoProvider, this.provideNavigationProvider, this.provideStorageProvider, this.provideEnvironmentProvider, this.applicationComponentImpl.provideMetricsProvider, this.providePlaybackProvider, this.provideUserSubscriptionProvider, this.provideCastingProvider));
        }

        @Override // dagger.android.AndroidInjector
        public void inject(PlaylistDetailFragment arg0) {
            injectPlaylistDetailFragment(arg0);
        }

        private PlaylistDetailFragment injectPlaylistDetailFragment(PlaylistDetailFragment instance) {
            PlaylistDetailFragment_MembersInjector.injectExploreManagerFactory(instance, this.provideExploreManagerFactoryProvider.get());
            return instance;
        }
    }

    private static final class UserPlaylistFragmentSubcomponentImpl implements CurateAndroidComponentsModule_UserPlaylistFragment$UserPlaylistFragmentSubcomponent {
        private final ApplicationComponentImpl applicationComponentImpl;
        private final CurateModule curateModule;
        private final UserPlaylistFragmentSubcomponentImpl userPlaylistFragmentSubcomponentImpl;

        private UserPlaylistFragmentSubcomponentImpl(ApplicationComponentImpl applicationComponentImpl, CurateModule curateModuleParam, UserPlaylistFragment arg0Param) {
            this.userPlaylistFragmentSubcomponentImpl = this;
            this.applicationComponentImpl = applicationComponentImpl;
            this.curateModule = curateModuleParam;
        }

        private RequestInterceptor requestInterceptor() {
            return CurateModule_ProvideRequestInterceptorFactory.provideRequestInterceptor(this.curateModule, this.applicationComponentImpl.application);
        }

        private StationProvider stationProvider() {
            return CurateModule_ProvideStationProviderFactory.provideStationProvider(this.curateModule, this.applicationComponentImpl.application);
        }

        private OverflowMenuProvider overflowMenuProvider() {
            return CurateModule_ProvideOverflowMenuProviderFactory.provideOverflowMenuProvider(this.curateModule, this.applicationComponentImpl.application);
        }

        @Override // dagger.android.AndroidInjector
        public void inject(UserPlaylistFragment arg0) {
            injectUserPlaylistFragment(arg0);
        }

        private UserPlaylistFragment injectUserPlaylistFragment(UserPlaylistFragment instance) {
            UserPlaylistFragment_MembersInjector.injectRequestInterceptor(instance, requestInterceptor());
            UserPlaylistFragment_MembersInjector.injectDownloadProvider(instance, CurateModule_ProvideDownloadProviderFactory.provideDownloadProvider(this.curateModule));
            UserPlaylistFragment_MembersInjector.injectShareProvider(instance, CurateModule_ProvideShareProviderFactory.provideShareProvider(this.curateModule));
            UserPlaylistFragment_MembersInjector.injectStationProvider(instance, stationProvider());
            UserPlaylistFragment_MembersInjector.injectPlaybackProvider(instance, CurateModule_ProvidePlaybackProviderFactory.providePlaybackProvider(this.curateModule));
            UserPlaylistFragment_MembersInjector.injectOverflowMenuProvider(instance, overflowMenuProvider());
            UserPlaylistFragment_MembersInjector.injectPlaylistProvider(instance, CurateModule_ProvidePlaylistProviderFactory.providePlaylistProvider(this.curateModule));
            UserPlaylistFragment_MembersInjector.injectFeatureFlagProvider(instance, CurateModule_ProvideFeatureFlagProviderFactory.provideFeatureFlagProvider(this.curateModule));
            UserPlaylistFragment_MembersInjector.injectNetworkProvider(instance, CurateModule_ProvideNetworkProviderFactory.provideNetworkProvider(this.curateModule));
            UserPlaylistFragment_MembersInjector.injectSubscriptionProvider(instance, CurateModule_ProvideSubscriptionProviderFactory.provideSubscriptionProvider(this.curateModule));
            UserPlaylistFragment_MembersInjector.injectDeviceInfoProvider(instance, PlatformProvidersModule_ProvideDeviceInfoProviderFactory.provideDeviceInfoProvider(this.applicationComponentImpl.platformProvidersModule));
            return instance;
        }
    }

    private static final class UserPlaylistsFragmentSubcomponentImpl implements CurateAndroidComponentsModule_UserPlaylistsFragment$UserPlaylistsFragmentSubcomponent {
        private final ApplicationComponentImpl applicationComponentImpl;
        private final CurateModule curateModule;
        private final UserPlaylistsFragmentSubcomponentImpl userPlaylistsFragmentSubcomponentImpl;

        private UserPlaylistsFragmentSubcomponentImpl(ApplicationComponentImpl applicationComponentImpl, CurateModule curateModuleParam, UserPlaylistsFragment arg0Param) {
            this.userPlaylistsFragmentSubcomponentImpl = this;
            this.applicationComponentImpl = applicationComponentImpl;
            this.curateModule = curateModuleParam;
        }

        private RequestInterceptor requestInterceptor() {
            return CurateModule_ProvideRequestInterceptorFactory.provideRequestInterceptor(this.curateModule, this.applicationComponentImpl.application);
        }

        @Override // dagger.android.AndroidInjector
        public void inject(UserPlaylistsFragment arg0) {
            injectUserPlaylistsFragment(arg0);
        }

        private UserPlaylistsFragment injectUserPlaylistsFragment(UserPlaylistsFragment instance) {
            UserPlaylistsFragment_MembersInjector.injectRequestInterceptor(instance, requestInterceptor());
            UserPlaylistsFragment_MembersInjector.injectPlaybackProvider(instance, CurateModule_ProvidePlaybackProviderFactory.providePlaybackProvider(this.curateModule));
            UserPlaylistsFragment_MembersInjector.injectDownloadProvider(instance, CurateModule_ProvideDownloadProviderFactory.provideDownloadProvider(this.curateModule));
            UserPlaylistsFragment_MembersInjector.injectFeatureFlagProvider(instance, CurateModule_ProvideFeatureFlagProviderFactory.provideFeatureFlagProvider(this.curateModule));
            return instance;
        }
    }

    private static final class PlaylistRecsSeeMoreFragmentSubcomponentImpl implements CurateAndroidComponentsModule_PlaylistRecsSeeMoreFragment$PlaylistRecsSeeMoreFragmentSubcomponent {
        private final ApplicationComponentImpl applicationComponentImpl;
        private final CurateModule curateModule;
        private final PlaylistRecsSeeMoreFragmentSubcomponentImpl playlistRecsSeeMoreFragmentSubcomponentImpl;

        private PlaylistRecsSeeMoreFragmentSubcomponentImpl(ApplicationComponentImpl applicationComponentImpl, CurateModule curateModuleParam, PlaylistRecsSeeMoreFragment arg0Param) {
            this.playlistRecsSeeMoreFragmentSubcomponentImpl = this;
            this.applicationComponentImpl = applicationComponentImpl;
            this.curateModule = curateModuleParam;
        }

        private RequestInterceptor requestInterceptor() {
            return CurateModule_ProvideRequestInterceptorFactory.provideRequestInterceptor(this.curateModule, this.applicationComponentImpl.application);
        }

        private OverflowMenuProvider overflowMenuProvider() {
            return CurateModule_ProvideOverflowMenuProviderFactory.provideOverflowMenuProvider(this.curateModule, this.applicationComponentImpl.application);
        }

        @Override // dagger.android.AndroidInjector
        public void inject(PlaylistRecsSeeMoreFragment arg0) {
            injectPlaylistRecsSeeMoreFragment(arg0);
        }

        private PlaylistRecsSeeMoreFragment injectPlaylistRecsSeeMoreFragment(PlaylistRecsSeeMoreFragment instance) {
            PlaylistRecsSeeMoreFragment_MembersInjector.injectRequestInterceptor(instance, requestInterceptor());
            PlaylistRecsSeeMoreFragment_MembersInjector.injectDownloadProvider(instance, CurateModule_ProvideDownloadProviderFactory.provideDownloadProvider(this.curateModule));
            PlaylistRecsSeeMoreFragment_MembersInjector.injectPlaybackProvider(instance, CurateModule_ProvidePlaybackProviderFactory.providePlaybackProvider(this.curateModule));
            PlaylistRecsSeeMoreFragment_MembersInjector.injectOverflowMenuProvider(instance, overflowMenuProvider());
            PlaylistRecsSeeMoreFragment_MembersInjector.injectFeatureFlagProvider(instance, CurateModule_ProvideFeatureFlagProviderFactory.provideFeatureFlagProvider(this.curateModule));
            PlaylistRecsSeeMoreFragment_MembersInjector.injectSubscriptionProvider(instance, CurateModule_ProvideSubscriptionProviderFactory.provideSubscriptionProvider(this.curateModule));
            PlaylistRecsSeeMoreFragment_MembersInjector.injectDeviceInfoProvider(instance, PlatformProvidersModule_ProvideDeviceInfoProviderFactory.provideDeviceInfoProvider(this.applicationComponentImpl.platformProvidersModule));
            return instance;
        }
    }

    private static final class PersonalizedPlaylistsFragmentSubcomponentImpl implements CurateAndroidComponentsModule_PersonalizedPlaylistsFragment$PersonalizedPlaylistsFragmentSubcomponent {
        private final ApplicationComponentImpl applicationComponentImpl;
        private final CurateModule curateModule;
        private final PersonalizedPlaylistsFragmentSubcomponentImpl personalizedPlaylistsFragmentSubcomponentImpl;

        private PersonalizedPlaylistsFragmentSubcomponentImpl(ApplicationComponentImpl applicationComponentImpl, CurateModule curateModuleParam, PersonalizedPlaylistsFragment arg0Param) {
            this.personalizedPlaylistsFragmentSubcomponentImpl = this;
            this.applicationComponentImpl = applicationComponentImpl;
            this.curateModule = curateModuleParam;
        }

        private RequestInterceptor requestInterceptor() {
            return CurateModule_ProvideRequestInterceptorFactory.provideRequestInterceptor(this.curateModule, this.applicationComponentImpl.application);
        }

        private OverflowMenuProvider overflowMenuProvider() {
            return CurateModule_ProvideOverflowMenuProviderFactory.provideOverflowMenuProvider(this.curateModule, this.applicationComponentImpl.application);
        }

        @Override // dagger.android.AndroidInjector
        public void inject(PersonalizedPlaylistsFragment arg0) {
            injectPersonalizedPlaylistsFragment(arg0);
        }

        private PersonalizedPlaylistsFragment injectPersonalizedPlaylistsFragment(PersonalizedPlaylistsFragment instance) {
            PersonalizedPlaylistsFragment_MembersInjector.injectRequestInterceptor(instance, requestInterceptor());
            PersonalizedPlaylistsFragment_MembersInjector.injectPlaybackProvider(instance, CurateModule_ProvidePlaybackProviderFactory.providePlaybackProvider(this.curateModule));
            PersonalizedPlaylistsFragment_MembersInjector.injectOverflowMenuProvider(instance, overflowMenuProvider());
            PersonalizedPlaylistsFragment_MembersInjector.injectDownloadProvider(instance, CurateModule_ProvideDownloadProviderFactory.provideDownloadProvider(this.curateModule));
            PersonalizedPlaylistsFragment_MembersInjector.injectFeatureFlagProvider(instance, CurateModule_ProvideFeatureFlagProviderFactory.provideFeatureFlagProvider(this.curateModule));
            PersonalizedPlaylistsFragment_MembersInjector.injectSubscriptionProvider(instance, CurateModule_ProvideSubscriptionProviderFactory.provideSubscriptionProvider(this.curateModule));
            PersonalizedPlaylistsFragment_MembersInjector.injectNavigationProvider(instance, CurateModule_ProvideNavigationProviderFactory.provideNavigationProvider(this.curateModule));
            PersonalizedPlaylistsFragment_MembersInjector.injectDeviceInfoProvider(instance, PlatformProvidersModule_ProvideDeviceInfoProviderFactory.provideDeviceInfoProvider(this.applicationComponentImpl.platformProvidersModule));
            return instance;
        }
    }

    private static final class MySoundtrackFragmentSubcomponentImpl implements CurateAndroidComponentsModule_MySoundtrackFragment$MySoundtrackFragmentSubcomponent {
        private final ApplicationComponentImpl applicationComponentImpl;
        private final CurateModule curateModule;
        private final MySoundtrackFragmentSubcomponentImpl mySoundtrackFragmentSubcomponentImpl;

        private MySoundtrackFragmentSubcomponentImpl(ApplicationComponentImpl applicationComponentImpl, CurateModule curateModuleParam, MySoundtrackFragment arg0Param) {
            this.mySoundtrackFragmentSubcomponentImpl = this;
            this.applicationComponentImpl = applicationComponentImpl;
            this.curateModule = curateModuleParam;
        }

        private RequestInterceptor requestInterceptor() {
            return CurateModule_ProvideRequestInterceptorFactory.provideRequestInterceptor(this.curateModule, this.applicationComponentImpl.application);
        }

        private OverflowMenuProvider overflowMenuProvider() {
            return CurateModule_ProvideOverflowMenuProviderFactory.provideOverflowMenuProvider(this.curateModule, this.applicationComponentImpl.application);
        }

        @Override // dagger.android.AndroidInjector
        public void inject(MySoundtrackFragment arg0) {
            injectMySoundtrackFragment(arg0);
        }

        private MySoundtrackFragment injectMySoundtrackFragment(MySoundtrackFragment instance) {
            MySoundtrackFragment_MembersInjector.injectRequestInterceptor(instance, requestInterceptor());
            MySoundtrackFragment_MembersInjector.injectPlaybackProvider(instance, CurateModule_ProvidePlaybackProviderFactory.providePlaybackProvider(this.curateModule));
            MySoundtrackFragment_MembersInjector.injectOverflowMenuProvider(instance, overflowMenuProvider());
            MySoundtrackFragment_MembersInjector.injectFeatureFlagProvider(instance, CurateModule_ProvideFeatureFlagProviderFactory.provideFeatureFlagProvider(this.curateModule));
            MySoundtrackFragment_MembersInjector.injectSubscriptionProvider(instance, CurateModule_ProvideSubscriptionProviderFactory.provideSubscriptionProvider(this.curateModule));
            MySoundtrackFragment_MembersInjector.injectDeviceInfoProvider(instance, PlatformProvidersModule_ProvideDeviceInfoProviderFactory.provideDeviceInfoProvider(this.applicationComponentImpl.platformProvidersModule));
            MySoundtrackFragment_MembersInjector.injectNavigationProvider(instance, CurateModule_ProvideNavigationProviderFactory.provideNavigationProvider(this.curateModule));
            MySoundtrackFragment_MembersInjector.injectDownloadProvider(instance, CurateModule_ProvideDownloadProviderFactory.provideDownloadProvider(this.curateModule));
            return instance;
        }
    }

    private static final class TemplateFragmentSubcomponentImpl implements CurateAndroidComponentsModule_TemplateFragment$TemplateFragmentSubcomponent {
        private final ApplicationComponentImpl applicationComponentImpl;
        private final TemplateFragmentSubcomponentImpl templateFragmentSubcomponentImpl;

        @Override // dagger.android.AndroidInjector
        public void inject(TemplateFragment arg0) {
        }

        private TemplateFragmentSubcomponentImpl(ApplicationComponentImpl applicationComponentImpl, TemplateFragment arg0Param) {
            this.templateFragmentSubcomponentImpl = this;
            this.applicationComponentImpl = applicationComponentImpl;
        }
    }

    private static final class PlaylistIntentReceiverServiceSubcomponentImpl implements CurateAndroidComponentsModule_PlaylistIntentReceiverService$PlaylistIntentReceiverServiceSubcomponent {
        private final ApplicationComponentImpl applicationComponentImpl;
        private final CurateModule curateModule;
        private final PlaylistIntentReceiverServiceSubcomponentImpl playlistIntentReceiverServiceSubcomponentImpl;

        private PlaylistIntentReceiverServiceSubcomponentImpl(ApplicationComponentImpl applicationComponentImpl, CurateModule curateModuleParam, PlaylistIntentReceiverService arg0Param) {
            this.playlistIntentReceiverServiceSubcomponentImpl = this;
            this.applicationComponentImpl = applicationComponentImpl;
            this.curateModule = curateModuleParam;
        }

        private RequestInterceptor requestInterceptor() {
            return CurateModule_ProvideRequestInterceptorFactory.provideRequestInterceptor(this.curateModule, this.applicationComponentImpl.application);
        }

        @Override // dagger.android.AndroidInjector
        public void inject(PlaylistIntentReceiverService arg0) {
            injectPlaylistIntentReceiverService(arg0);
        }

        private PlaylistIntentReceiverService injectPlaylistIntentReceiverService(PlaylistIntentReceiverService instance) {
            PlaylistIntentReceiverService_MembersInjector.injectRequestInterceptor(instance, requestInterceptor());
            PlaylistIntentReceiverService_MembersInjector.injectFeatureFlagProvider(instance, CurateModule_ProvideFeatureFlagProviderFactory.provideFeatureFlagProvider(this.curateModule));
            return instance;
        }
    }

    private static final class PlaybackFragmentSubcomponentImpl implements ExploreAndroidComponentsModule_PlaybackFragment$PlaybackFragmentSubcomponent {
        private final ApplicationComponentImpl applicationComponentImpl;
        private final PlaybackFragmentSubcomponentImpl playbackFragmentSubcomponentImpl;
        private Provider<AnimationProvider> provideAnimationProvider;
        private Provider<CastingProvider> provideCastingProvider;
        private Provider<DownloadProvider> provideDownloadProvider;
        private Provider<EnvironmentProvider> provideEnvironmentProvider;
        private Provider<ExternalActionProvider> provideExternalActionProvider;
        private Provider<LibraryProvider> provideLibraryProvider;
        private Provider<NavigationProvider> provideNavigationProvider;
        private Provider<NotificationProvider> provideNotificationProvider;
        private Provider<PlaybackProvider> providePlaybackProvider;
        private Provider<SharingProvider> provideSharingProvider;
        private Provider<StorageProvider> provideStorageProvider;
        private Provider<StyleSheetProvider> provideStyleSheetProvider;
        private Provider<UpsellProvider> provideUpsellProvider;

        private PlaybackFragmentSubcomponentImpl(ApplicationComponentImpl applicationComponentImpl, ExploreModule exploreModuleParam, PlaybackFragment arg0Param) {
            this.playbackFragmentSubcomponentImpl = this;
            this.applicationComponentImpl = applicationComponentImpl;
            initialize(exploreModuleParam, arg0Param);
        }

        private void initialize(final ExploreModule exploreModuleParam, final PlaybackFragment arg0Param) {
            this.provideLibraryProvider = SingleCheck.provider(ExploreModule_ProvideLibraryProviderFactory.create(exploreModuleParam));
            this.provideNavigationProvider = SingleCheck.provider(ExploreModule_ProvideNavigationProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideNotificationProvider = SingleCheck.provider(ExploreModule_ProvideNotificationProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.providePlaybackProvider = SingleCheck.provider(ExploreModule_ProvidePlaybackProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideSharingProvider = SingleCheck.provider(ExploreModule_ProvideSharingProviderFactory.create(exploreModuleParam));
            Provider<DownloadProvider> provider = SingleCheck.provider(ExploreModule_ProvideDownloadProviderFactory.create(exploreModuleParam));
            this.provideDownloadProvider = provider;
            this.provideExternalActionProvider = SingleCheck.provider(ExploreModule_ProvideExternalActionProviderFactory.create(exploreModuleParam, this.provideLibraryProvider, this.provideNavigationProvider, this.provideNotificationProvider, this.providePlaybackProvider, this.provideSharingProvider, provider));
            this.provideStorageProvider = SingleCheck.provider(ExploreModule_ProvideStorageProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideEnvironmentProvider = SingleCheck.provider(ExploreModule_ProvideEnvironmentProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideUpsellProvider = SingleCheck.provider(ExploreModule_ProvideUpsellProviderFactory.create(exploreModuleParam));
            this.provideCastingProvider = SingleCheck.provider(ExploreModule_ProvideCastingProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideAnimationProvider = SingleCheck.provider(ExploreModule_ProvideAnimationProviderFactory.create(exploreModuleParam));
            this.provideStyleSheetProvider = SingleCheck.provider(ExploreModule_ProvideStyleSheetProviderFactory.create(exploreModuleParam));
        }

        @Override // dagger.android.AndroidInjector
        public void inject(PlaybackFragment arg0) {
            injectPlaybackFragment(arg0);
        }

        private PlaybackFragment injectPlaybackFragment(PlaybackFragment instance) {
            BaseExploreFragment_MembersInjector.injectAuthenticationProvider(instance, PlatformProvidersModule_ProvideAuthenticationProviderFactory.provideAuthenticationProvider(this.applicationComponentImpl.platformProvidersModule));
            BaseExploreFragment_MembersInjector.injectCustomerMetadataProvider(instance, PlatformProvidersModule_ProvideCustomerMetadataProviderFactory.provideCustomerMetadataProvider(this.applicationComponentImpl.platformProvidersModule));
            BaseExploreFragment_MembersInjector.injectDeviceInfoProvider(instance, PlatformProvidersModule_ProvideDeviceInfoProviderFactory.provideDeviceInfoProvider(this.applicationComponentImpl.platformProvidersModule));
            BaseExploreFragment_MembersInjector.injectBuildInfoProvider(instance, PlatformProvidersModule_ProvideBuildInfoProviderFactory.provideBuildInfoProvider(this.applicationComponentImpl.platformProvidersModule));
            BaseExploreFragment_MembersInjector.injectExternalActionProvider(instance, this.provideExternalActionProvider.get());
            BaseExploreFragment_MembersInjector.injectStorageProvider(instance, this.provideStorageProvider.get());
            BaseExploreFragment_MembersInjector.injectEnvironmentProvider(instance, this.provideEnvironmentProvider.get());
            BaseExploreFragment_MembersInjector.injectPlaybackProvider(instance, this.providePlaybackProvider.get());
            BaseExploreFragment_MembersInjector.injectUpsellProvider(instance, this.provideUpsellProvider.get());
            BaseExploreFragment_MembersInjector.injectCastingProvider(instance, this.provideCastingProvider.get());
            BaseExploreFragment_MembersInjector.injectAnimationProvider(instance, this.provideAnimationProvider.get());
            BaseExploreFragment_MembersInjector.injectStyleSheetProvider(instance, this.provideStyleSheetProvider.get());
            BaseExploreFragment_MembersInjector.injectMetricsProvider(instance, PlatformProvidersModule_ProvideMetricsProviderFactory.provideMetricsProvider(this.applicationComponentImpl.platformProvidersModule));
            BaseExploreFragment_MembersInjector.injectSharingProvider(instance, this.provideSharingProvider.get());
            return instance;
        }
    }

    private static final class SwipeablePagesFragmentSubcomponentImpl implements ExploreAndroidComponentsModule_SwipeablePagesFragment$SwipeablePagesFragmentSubcomponent {
        private final ApplicationComponentImpl applicationComponentImpl;
        private Provider<AnimationProvider> provideAnimationProvider;
        private Provider<CastingProvider> provideCastingProvider;
        private Provider<DownloadProvider> provideDownloadProvider;
        private Provider<EnvironmentProvider> provideEnvironmentProvider;
        private Provider<ExternalActionProvider> provideExternalActionProvider;
        private Provider<LibraryProvider> provideLibraryProvider;
        private Provider<NavigationProvider> provideNavigationProvider;
        private Provider<NotificationProvider> provideNotificationProvider;
        private Provider<PlaybackProvider> providePlaybackProvider;
        private Provider<SharingProvider> provideSharingProvider;
        private Provider<StorageProvider> provideStorageProvider;
        private Provider<StyleSheetProvider> provideStyleSheetProvider;
        private Provider<UpsellProvider> provideUpsellProvider;
        private final SwipeablePagesFragmentSubcomponentImpl swipeablePagesFragmentSubcomponentImpl;

        private SwipeablePagesFragmentSubcomponentImpl(ApplicationComponentImpl applicationComponentImpl, ExploreModule exploreModuleParam, SwipeablePagesFragment arg0Param) {
            this.swipeablePagesFragmentSubcomponentImpl = this;
            this.applicationComponentImpl = applicationComponentImpl;
            initialize(exploreModuleParam, arg0Param);
        }

        private void initialize(final ExploreModule exploreModuleParam, final SwipeablePagesFragment arg0Param) {
            this.provideLibraryProvider = SingleCheck.provider(ExploreModule_ProvideLibraryProviderFactory.create(exploreModuleParam));
            this.provideNavigationProvider = SingleCheck.provider(ExploreModule_ProvideNavigationProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideNotificationProvider = SingleCheck.provider(ExploreModule_ProvideNotificationProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.providePlaybackProvider = SingleCheck.provider(ExploreModule_ProvidePlaybackProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideSharingProvider = SingleCheck.provider(ExploreModule_ProvideSharingProviderFactory.create(exploreModuleParam));
            Provider<DownloadProvider> provider = SingleCheck.provider(ExploreModule_ProvideDownloadProviderFactory.create(exploreModuleParam));
            this.provideDownloadProvider = provider;
            this.provideExternalActionProvider = SingleCheck.provider(ExploreModule_ProvideExternalActionProviderFactory.create(exploreModuleParam, this.provideLibraryProvider, this.provideNavigationProvider, this.provideNotificationProvider, this.providePlaybackProvider, this.provideSharingProvider, provider));
            this.provideStorageProvider = SingleCheck.provider(ExploreModule_ProvideStorageProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideEnvironmentProvider = SingleCheck.provider(ExploreModule_ProvideEnvironmentProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideUpsellProvider = SingleCheck.provider(ExploreModule_ProvideUpsellProviderFactory.create(exploreModuleParam));
            this.provideCastingProvider = SingleCheck.provider(ExploreModule_ProvideCastingProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideAnimationProvider = SingleCheck.provider(ExploreModule_ProvideAnimationProviderFactory.create(exploreModuleParam));
            this.provideStyleSheetProvider = SingleCheck.provider(ExploreModule_ProvideStyleSheetProviderFactory.create(exploreModuleParam));
        }

        @Override // dagger.android.AndroidInjector
        public void inject(SwipeablePagesFragment arg0) {
            injectSwipeablePagesFragment(arg0);
        }

        private SwipeablePagesFragment injectSwipeablePagesFragment(SwipeablePagesFragment instance) {
            BaseExploreFragment_MembersInjector.injectAuthenticationProvider(instance, PlatformProvidersModule_ProvideAuthenticationProviderFactory.provideAuthenticationProvider(this.applicationComponentImpl.platformProvidersModule));
            BaseExploreFragment_MembersInjector.injectCustomerMetadataProvider(instance, PlatformProvidersModule_ProvideCustomerMetadataProviderFactory.provideCustomerMetadataProvider(this.applicationComponentImpl.platformProvidersModule));
            BaseExploreFragment_MembersInjector.injectDeviceInfoProvider(instance, PlatformProvidersModule_ProvideDeviceInfoProviderFactory.provideDeviceInfoProvider(this.applicationComponentImpl.platformProvidersModule));
            BaseExploreFragment_MembersInjector.injectBuildInfoProvider(instance, PlatformProvidersModule_ProvideBuildInfoProviderFactory.provideBuildInfoProvider(this.applicationComponentImpl.platformProvidersModule));
            BaseExploreFragment_MembersInjector.injectExternalActionProvider(instance, this.provideExternalActionProvider.get());
            BaseExploreFragment_MembersInjector.injectStorageProvider(instance, this.provideStorageProvider.get());
            BaseExploreFragment_MembersInjector.injectEnvironmentProvider(instance, this.provideEnvironmentProvider.get());
            BaseExploreFragment_MembersInjector.injectPlaybackProvider(instance, this.providePlaybackProvider.get());
            BaseExploreFragment_MembersInjector.injectUpsellProvider(instance, this.provideUpsellProvider.get());
            BaseExploreFragment_MembersInjector.injectCastingProvider(instance, this.provideCastingProvider.get());
            BaseExploreFragment_MembersInjector.injectAnimationProvider(instance, this.provideAnimationProvider.get());
            BaseExploreFragment_MembersInjector.injectStyleSheetProvider(instance, this.provideStyleSheetProvider.get());
            BaseExploreFragment_MembersInjector.injectMetricsProvider(instance, PlatformProvidersModule_ProvideMetricsProviderFactory.provideMetricsProvider(this.applicationComponentImpl.platformProvidersModule));
            BaseExploreFragment_MembersInjector.injectSharingProvider(instance, this.provideSharingProvider.get());
            return instance;
        }
    }

    private static final class StageSwipeablePagesFragmentSubcomponentImpl implements ExploreAndroidComponentsModule_StageSwipeablePagesFragment$StageSwipeablePagesFragmentSubcomponent {
        private final ApplicationComponentImpl applicationComponentImpl;
        private Provider<AnimationProvider> provideAnimationProvider;
        private Provider<CastingProvider> provideCastingProvider;
        private Provider<DownloadProvider> provideDownloadProvider;
        private Provider<EnvironmentProvider> provideEnvironmentProvider;
        private Provider<ExternalActionProvider> provideExternalActionProvider;
        private Provider<LibraryProvider> provideLibraryProvider;
        private Provider<NavigationProvider> provideNavigationProvider;
        private Provider<NotificationProvider> provideNotificationProvider;
        private Provider<PlaybackProvider> providePlaybackProvider;
        private Provider<SharingProvider> provideSharingProvider;
        private Provider<StorageProvider> provideStorageProvider;
        private Provider<StyleSheetProvider> provideStyleSheetProvider;
        private Provider<UpsellProvider> provideUpsellProvider;
        private final StageSwipeablePagesFragmentSubcomponentImpl stageSwipeablePagesFragmentSubcomponentImpl;

        private StageSwipeablePagesFragmentSubcomponentImpl(ApplicationComponentImpl applicationComponentImpl, ExploreModule exploreModuleParam, StageSwipeablePagesFragment arg0Param) {
            this.stageSwipeablePagesFragmentSubcomponentImpl = this;
            this.applicationComponentImpl = applicationComponentImpl;
            initialize(exploreModuleParam, arg0Param);
        }

        private void initialize(final ExploreModule exploreModuleParam, final StageSwipeablePagesFragment arg0Param) {
            this.provideLibraryProvider = SingleCheck.provider(ExploreModule_ProvideLibraryProviderFactory.create(exploreModuleParam));
            this.provideNavigationProvider = SingleCheck.provider(ExploreModule_ProvideNavigationProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideNotificationProvider = SingleCheck.provider(ExploreModule_ProvideNotificationProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.providePlaybackProvider = SingleCheck.provider(ExploreModule_ProvidePlaybackProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideSharingProvider = SingleCheck.provider(ExploreModule_ProvideSharingProviderFactory.create(exploreModuleParam));
            Provider<DownloadProvider> provider = SingleCheck.provider(ExploreModule_ProvideDownloadProviderFactory.create(exploreModuleParam));
            this.provideDownloadProvider = provider;
            this.provideExternalActionProvider = SingleCheck.provider(ExploreModule_ProvideExternalActionProviderFactory.create(exploreModuleParam, this.provideLibraryProvider, this.provideNavigationProvider, this.provideNotificationProvider, this.providePlaybackProvider, this.provideSharingProvider, provider));
            this.provideStorageProvider = SingleCheck.provider(ExploreModule_ProvideStorageProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideEnvironmentProvider = SingleCheck.provider(ExploreModule_ProvideEnvironmentProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideUpsellProvider = SingleCheck.provider(ExploreModule_ProvideUpsellProviderFactory.create(exploreModuleParam));
            this.provideCastingProvider = SingleCheck.provider(ExploreModule_ProvideCastingProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideAnimationProvider = SingleCheck.provider(ExploreModule_ProvideAnimationProviderFactory.create(exploreModuleParam));
            this.provideStyleSheetProvider = SingleCheck.provider(ExploreModule_ProvideStyleSheetProviderFactory.create(exploreModuleParam));
        }

        @Override // dagger.android.AndroidInjector
        public void inject(StageSwipeablePagesFragment arg0) {
            injectStageSwipeablePagesFragment(arg0);
        }

        private StageSwipeablePagesFragment injectStageSwipeablePagesFragment(StageSwipeablePagesFragment instance) {
            BaseExploreFragment_MembersInjector.injectAuthenticationProvider(instance, PlatformProvidersModule_ProvideAuthenticationProviderFactory.provideAuthenticationProvider(this.applicationComponentImpl.platformProvidersModule));
            BaseExploreFragment_MembersInjector.injectCustomerMetadataProvider(instance, PlatformProvidersModule_ProvideCustomerMetadataProviderFactory.provideCustomerMetadataProvider(this.applicationComponentImpl.platformProvidersModule));
            BaseExploreFragment_MembersInjector.injectDeviceInfoProvider(instance, PlatformProvidersModule_ProvideDeviceInfoProviderFactory.provideDeviceInfoProvider(this.applicationComponentImpl.platformProvidersModule));
            BaseExploreFragment_MembersInjector.injectBuildInfoProvider(instance, PlatformProvidersModule_ProvideBuildInfoProviderFactory.provideBuildInfoProvider(this.applicationComponentImpl.platformProvidersModule));
            BaseExploreFragment_MembersInjector.injectExternalActionProvider(instance, this.provideExternalActionProvider.get());
            BaseExploreFragment_MembersInjector.injectStorageProvider(instance, this.provideStorageProvider.get());
            BaseExploreFragment_MembersInjector.injectEnvironmentProvider(instance, this.provideEnvironmentProvider.get());
            BaseExploreFragment_MembersInjector.injectPlaybackProvider(instance, this.providePlaybackProvider.get());
            BaseExploreFragment_MembersInjector.injectUpsellProvider(instance, this.provideUpsellProvider.get());
            BaseExploreFragment_MembersInjector.injectCastingProvider(instance, this.provideCastingProvider.get());
            BaseExploreFragment_MembersInjector.injectAnimationProvider(instance, this.provideAnimationProvider.get());
            BaseExploreFragment_MembersInjector.injectStyleSheetProvider(instance, this.provideStyleSheetProvider.get());
            BaseExploreFragment_MembersInjector.injectMetricsProvider(instance, PlatformProvidersModule_ProvideMetricsProviderFactory.provideMetricsProvider(this.applicationComponentImpl.platformProvidersModule));
            BaseExploreFragment_MembersInjector.injectSharingProvider(instance, this.provideSharingProvider.get());
            return instance;
        }
    }

    private static final class LiveStreamActivitySubcomponentImpl implements ExploreAndroidComponentsModule_LiveStreamActivity$LiveStreamActivitySubcomponent {
        private final ApplicationComponentImpl applicationComponentImpl;
        private final LiveStreamActivitySubcomponentImpl liveStreamActivitySubcomponentImpl;
        private Provider<DownloadProvider> provideDownloadProvider;
        private Provider<EnvironmentProvider> provideEnvironmentProvider;
        private Provider<ExplicitFilterProvider> provideExplicitFilterProvider;
        private Provider<ExternalActionProvider> provideExternalActionProvider;
        private Provider<LibraryProvider> provideLibraryProvider;
        private Provider<NavigationProvider> provideNavigationProvider;
        private Provider<NotificationProvider> provideNotificationProvider;
        private Provider<PlaybackProvider> providePlaybackProvider;
        private Provider<SharingProvider> provideSharingProvider;
        private Provider<StorageProvider> provideStorageProvider;
        private Provider<UserSubscriptionProvider> provideUserSubscriptionProvider;
        private Provider<WakeWordProvider> provideWakeWordProvider;

        private LiveStreamActivitySubcomponentImpl(ApplicationComponentImpl applicationComponentImpl, ExploreModule exploreModuleParam, LiveStreamActivity arg0Param) {
            this.liveStreamActivitySubcomponentImpl = this;
            this.applicationComponentImpl = applicationComponentImpl;
            initialize(exploreModuleParam, arg0Param);
        }

        private void initialize(final ExploreModule exploreModuleParam, final LiveStreamActivity arg0Param) {
            this.provideStorageProvider = SingleCheck.provider(ExploreModule_ProvideStorageProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideEnvironmentProvider = SingleCheck.provider(ExploreModule_ProvideEnvironmentProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideExplicitFilterProvider = SingleCheck.provider(ExploreModule_ProvideExplicitFilterProviderFactory.create(exploreModuleParam));
            this.provideWakeWordProvider = SingleCheck.provider(ExploreModule_ProvideWakeWordProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideUserSubscriptionProvider = SingleCheck.provider(ExploreModule_ProvideUserSubscriptionProviderFactory.create(exploreModuleParam));
            this.provideSharingProvider = SingleCheck.provider(ExploreModule_ProvideSharingProviderFactory.create(exploreModuleParam));
            this.provideLibraryProvider = SingleCheck.provider(ExploreModule_ProvideLibraryProviderFactory.create(exploreModuleParam));
            this.provideNavigationProvider = SingleCheck.provider(ExploreModule_ProvideNavigationProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideNotificationProvider = SingleCheck.provider(ExploreModule_ProvideNotificationProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.providePlaybackProvider = SingleCheck.provider(ExploreModule_ProvidePlaybackProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            Provider<DownloadProvider> provider = SingleCheck.provider(ExploreModule_ProvideDownloadProviderFactory.create(exploreModuleParam));
            this.provideDownloadProvider = provider;
            this.provideExternalActionProvider = SingleCheck.provider(ExploreModule_ProvideExternalActionProviderFactory.create(exploreModuleParam, this.provideLibraryProvider, this.provideNavigationProvider, this.provideNotificationProvider, this.providePlaybackProvider, this.provideSharingProvider, provider));
        }

        @Override // dagger.android.AndroidInjector
        public void inject(LiveStreamActivity arg0) {
            injectLiveStreamActivity(arg0);
        }

        private LiveStreamActivity injectLiveStreamActivity(LiveStreamActivity instance) {
            BaseExploreActivity_MembersInjector.injectAuthenticationProvider(instance, PlatformProvidersModule_ProvideAuthenticationProviderFactory.provideAuthenticationProvider(this.applicationComponentImpl.platformProvidersModule));
            BaseExploreActivity_MembersInjector.injectCustomerMetadataProvider(instance, PlatformProvidersModule_ProvideCustomerMetadataProviderFactory.provideCustomerMetadataProvider(this.applicationComponentImpl.platformProvidersModule));
            BaseExploreActivity_MembersInjector.injectDeviceInfoProvider(instance, PlatformProvidersModule_ProvideDeviceInfoProviderFactory.provideDeviceInfoProvider(this.applicationComponentImpl.platformProvidersModule));
            BaseExploreActivity_MembersInjector.injectBuildInfoProvider(instance, PlatformProvidersModule_ProvideBuildInfoProviderFactory.provideBuildInfoProvider(this.applicationComponentImpl.platformProvidersModule));
            BaseExploreActivity_MembersInjector.injectStorageProvider(instance, this.provideStorageProvider.get());
            BaseExploreActivity_MembersInjector.injectEnvironmentProvider(instance, this.provideEnvironmentProvider.get());
            BaseExploreActivity_MembersInjector.injectExplicitFilterProvider(instance, this.provideExplicitFilterProvider.get());
            BaseExploreActivity_MembersInjector.injectMetricsProvider(instance, PlatformProvidersModule_ProvideMetricsProviderFactory.provideMetricsProvider(this.applicationComponentImpl.platformProvidersModule));
            BaseExploreActivity_MembersInjector.injectWakeWordProvider(instance, this.provideWakeWordProvider.get());
            BaseExploreActivity_MembersInjector.injectUserSubscriptionProvider(instance, this.provideUserSubscriptionProvider.get());
            BaseExploreActivity_MembersInjector.injectSharingProvider(instance, this.provideSharingProvider.get());
            BaseExploreActivity_MembersInjector.injectExternalActionProvider(instance, this.provideExternalActionProvider.get());
            BaseExploreActivity_MembersInjector.injectPlaybackProvider(instance, this.providePlaybackProvider.get());
            BaseExploreActivity_MembersInjector.injectNavigationProvider(instance, this.provideNavigationProvider.get());
            return instance;
        }
    }

    private static final class AmpPlaybackActivitySubcomponentImpl implements ExploreAndroidComponentsModule_AmpPlaybackActivity$AmpPlaybackActivitySubcomponent {
        private final AmpPlaybackActivitySubcomponentImpl ampPlaybackActivitySubcomponentImpl;
        private final ApplicationComponentImpl applicationComponentImpl;
        private Provider<DownloadProvider> provideDownloadProvider;
        private Provider<EnvironmentProvider> provideEnvironmentProvider;
        private Provider<ExplicitFilterProvider> provideExplicitFilterProvider;
        private Provider<ExternalActionProvider> provideExternalActionProvider;
        private Provider<LibraryProvider> provideLibraryProvider;
        private Provider<NavigationProvider> provideNavigationProvider;
        private Provider<NotificationProvider> provideNotificationProvider;
        private Provider<PlaybackProvider> providePlaybackProvider;
        private Provider<SharingProvider> provideSharingProvider;
        private Provider<StorageProvider> provideStorageProvider;
        private Provider<UserSubscriptionProvider> provideUserSubscriptionProvider;
        private Provider<WakeWordProvider> provideWakeWordProvider;

        private AmpPlaybackActivitySubcomponentImpl(ApplicationComponentImpl applicationComponentImpl, ExploreModule exploreModuleParam, AmpPlaybackActivity arg0Param) {
            this.ampPlaybackActivitySubcomponentImpl = this;
            this.applicationComponentImpl = applicationComponentImpl;
            initialize(exploreModuleParam, arg0Param);
        }

        private void initialize(final ExploreModule exploreModuleParam, final AmpPlaybackActivity arg0Param) {
            this.provideStorageProvider = SingleCheck.provider(ExploreModule_ProvideStorageProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideEnvironmentProvider = SingleCheck.provider(ExploreModule_ProvideEnvironmentProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideExplicitFilterProvider = SingleCheck.provider(ExploreModule_ProvideExplicitFilterProviderFactory.create(exploreModuleParam));
            this.provideWakeWordProvider = SingleCheck.provider(ExploreModule_ProvideWakeWordProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideUserSubscriptionProvider = SingleCheck.provider(ExploreModule_ProvideUserSubscriptionProviderFactory.create(exploreModuleParam));
            this.provideSharingProvider = SingleCheck.provider(ExploreModule_ProvideSharingProviderFactory.create(exploreModuleParam));
            this.provideLibraryProvider = SingleCheck.provider(ExploreModule_ProvideLibraryProviderFactory.create(exploreModuleParam));
            this.provideNavigationProvider = SingleCheck.provider(ExploreModule_ProvideNavigationProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideNotificationProvider = SingleCheck.provider(ExploreModule_ProvideNotificationProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.providePlaybackProvider = SingleCheck.provider(ExploreModule_ProvidePlaybackProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            Provider<DownloadProvider> provider = SingleCheck.provider(ExploreModule_ProvideDownloadProviderFactory.create(exploreModuleParam));
            this.provideDownloadProvider = provider;
            this.provideExternalActionProvider = SingleCheck.provider(ExploreModule_ProvideExternalActionProviderFactory.create(exploreModuleParam, this.provideLibraryProvider, this.provideNavigationProvider, this.provideNotificationProvider, this.providePlaybackProvider, this.provideSharingProvider, provider));
        }

        @Override // dagger.android.AndroidInjector
        public void inject(AmpPlaybackActivity arg0) {
            injectAmpPlaybackActivity(arg0);
        }

        private AmpPlaybackActivity injectAmpPlaybackActivity(AmpPlaybackActivity instance) {
            BaseExploreActivity_MembersInjector.injectAuthenticationProvider(instance, PlatformProvidersModule_ProvideAuthenticationProviderFactory.provideAuthenticationProvider(this.applicationComponentImpl.platformProvidersModule));
            BaseExploreActivity_MembersInjector.injectCustomerMetadataProvider(instance, PlatformProvidersModule_ProvideCustomerMetadataProviderFactory.provideCustomerMetadataProvider(this.applicationComponentImpl.platformProvidersModule));
            BaseExploreActivity_MembersInjector.injectDeviceInfoProvider(instance, PlatformProvidersModule_ProvideDeviceInfoProviderFactory.provideDeviceInfoProvider(this.applicationComponentImpl.platformProvidersModule));
            BaseExploreActivity_MembersInjector.injectBuildInfoProvider(instance, PlatformProvidersModule_ProvideBuildInfoProviderFactory.provideBuildInfoProvider(this.applicationComponentImpl.platformProvidersModule));
            BaseExploreActivity_MembersInjector.injectStorageProvider(instance, this.provideStorageProvider.get());
            BaseExploreActivity_MembersInjector.injectEnvironmentProvider(instance, this.provideEnvironmentProvider.get());
            BaseExploreActivity_MembersInjector.injectExplicitFilterProvider(instance, this.provideExplicitFilterProvider.get());
            BaseExploreActivity_MembersInjector.injectMetricsProvider(instance, PlatformProvidersModule_ProvideMetricsProviderFactory.provideMetricsProvider(this.applicationComponentImpl.platformProvidersModule));
            BaseExploreActivity_MembersInjector.injectWakeWordProvider(instance, this.provideWakeWordProvider.get());
            BaseExploreActivity_MembersInjector.injectUserSubscriptionProvider(instance, this.provideUserSubscriptionProvider.get());
            BaseExploreActivity_MembersInjector.injectSharingProvider(instance, this.provideSharingProvider.get());
            BaseExploreActivity_MembersInjector.injectExternalActionProvider(instance, this.provideExternalActionProvider.get());
            BaseExploreActivity_MembersInjector.injectPlaybackProvider(instance, this.providePlaybackProvider.get());
            BaseExploreActivity_MembersInjector.injectNavigationProvider(instance, this.provideNavigationProvider.get());
            return instance;
        }
    }

    private static final class GalleryFragmentSubcomponentImpl implements ExploreAndroidComponentsModule_GalleryFragment$GalleryFragmentSubcomponent {
        private final ApplicationComponentImpl applicationComponentImpl;
        private final ExploreModule exploreModule;
        private final GalleryFragmentSubcomponentImpl galleryFragmentSubcomponentImpl;
        private Provider<CapabilitiesInfo> provideCapabilitiesInfoProvider;
        private Provider<ChromeProvider> provideChromeProvider;
        private Provider<ContextMenuProvider> provideContextMenuProvider;
        private Provider<DownloadProvider> provideDownloadProvider;
        private Provider<ExternalActionProvider> provideExternalActionProvider;
        private Provider<LibraryProvider> provideLibraryProvider;
        private Provider<NavigationProvider> provideNavigationProvider;
        private Provider<NetworkInterceptor> provideNetworkInterceptorProvider;
        private Provider<NotificationProvider> provideNotificationProvider;
        private Provider<PlaybackProvider> providePlaybackProvider;
        private Provider<SharingProvider> provideSharingProvider;
        private Provider<SkillLatencyTracker> provideSkillLatencyTrackerProvider;
        private Provider<SkyFireConfigurationProvider> provideSkyFireConfigurationProvider;
        private Provider<SkyFireProvider> provideSkyFireProvider;
        private Provider<StyleSheetProvider> provideStyleSheetProvider;
        private Provider<UpsellProvider> provideUpsellProvider;
        private Provider<UserSubscriptionProvider> provideUserSubscriptionProvider;

        private GalleryFragmentSubcomponentImpl(ApplicationComponentImpl applicationComponentImpl, ExploreModule exploreModuleParam, GalleryFragment arg0Param) {
            this.galleryFragmentSubcomponentImpl = this;
            this.applicationComponentImpl = applicationComponentImpl;
            this.exploreModule = exploreModuleParam;
            initialize(exploreModuleParam, arg0Param);
        }

        private ElementBindingProvider elementBindingProvider() {
            return ExploreModule_ProvideElementBindingProviderFactory.provideElementBindingProvider(this.exploreModule, this.provideUserSubscriptionProvider.get(), this.provideStyleSheetProvider.get(), this.provideDownloadProvider.get());
        }

        private void initialize(final ExploreModule exploreModuleParam, final GalleryFragment arg0Param) {
            this.provideStyleSheetProvider = SingleCheck.provider(ExploreModule_ProvideStyleSheetProviderFactory.create(exploreModuleParam));
            this.provideUserSubscriptionProvider = SingleCheck.provider(ExploreModule_ProvideUserSubscriptionProviderFactory.create(exploreModuleParam));
            this.provideDownloadProvider = SingleCheck.provider(ExploreModule_ProvideDownloadProviderFactory.create(exploreModuleParam));
            this.provideLibraryProvider = SingleCheck.provider(ExploreModule_ProvideLibraryProviderFactory.create(exploreModuleParam));
            this.provideNavigationProvider = SingleCheck.provider(ExploreModule_ProvideNavigationProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideNotificationProvider = SingleCheck.provider(ExploreModule_ProvideNotificationProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.providePlaybackProvider = SingleCheck.provider(ExploreModule_ProvidePlaybackProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            Provider<SharingProvider> provider = SingleCheck.provider(ExploreModule_ProvideSharingProviderFactory.create(exploreModuleParam));
            this.provideSharingProvider = provider;
            this.provideExternalActionProvider = SingleCheck.provider(ExploreModule_ProvideExternalActionProviderFactory.create(exploreModuleParam, this.provideLibraryProvider, this.provideNavigationProvider, this.provideNotificationProvider, this.providePlaybackProvider, provider, this.provideDownloadProvider));
            this.provideCapabilitiesInfoProvider = SingleCheck.provider(ExploreModule_ProvideCapabilitiesInfoFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideNetworkInterceptorProvider = SingleCheck.provider(ExploreModule_ProvideNetworkInterceptorFactory.create(exploreModuleParam));
            this.provideSkillLatencyTrackerProvider = SingleCheck.provider(ExploreModule_ProvideSkillLatencyTrackerFactory.create(exploreModuleParam));
            this.provideSkyFireConfigurationProvider = SingleCheck.provider(ExploreModule_ProvideSkyFireConfigurationProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideSkyFireProvider = SingleCheck.provider(ExploreModule_ProvideSkyFireProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider, this.applicationComponentImpl.provideAuthenticationProvider, this.applicationComponentImpl.provideCustomerMetadataProvider, this.applicationComponentImpl.provideDeviceInfoProvider, this.applicationComponentImpl.provideBuildInfoProvider, this.providePlaybackProvider, this.provideCapabilitiesInfoProvider, this.provideNetworkInterceptorProvider, this.provideSkillLatencyTrackerProvider, this.provideSkyFireConfigurationProvider));
            this.provideContextMenuProvider = SingleCheck.provider(ExploreModule_ProvideContextMenuProviderFactory.create(exploreModuleParam));
            this.provideUpsellProvider = SingleCheck.provider(ExploreModule_ProvideUpsellProviderFactory.create(exploreModuleParam));
            this.provideChromeProvider = SingleCheck.provider(ExploreModule_ProvideChromeProviderFactory.create(exploreModuleParam));
        }

        @Override // dagger.android.AndroidInjector
        public void inject(GalleryFragment arg0) {
            injectGalleryFragment(arg0);
        }

        private GalleryFragment injectGalleryFragment(GalleryFragment instance) {
            TemplateFragment_MembersInjector.injectStyleSheetProvider(instance, this.provideStyleSheetProvider.get());
            TemplateFragment_MembersInjector.injectElementBindingProvider(instance, elementBindingProvider());
            TemplateFragment_MembersInjector.injectExternalActionProvider(instance, this.provideExternalActionProvider.get());
            TemplateFragment_MembersInjector.injectSkyFireProvider(instance, this.provideSkyFireProvider.get());
            TemplateFragment_MembersInjector.injectSkyFireConfigurationProvider(instance, this.provideSkyFireConfigurationProvider.get());
            TemplateFragment_MembersInjector.injectContextMenuProvider(instance, this.provideContextMenuProvider.get());
            TemplateFragment_MembersInjector.injectPlaybackProvider(instance, this.providePlaybackProvider.get());
            TemplateFragment_MembersInjector.injectUpsellProvider(instance, this.provideUpsellProvider.get());
            TemplateFragment_MembersInjector.injectNavigationProvider(instance, this.provideNavigationProvider.get());
            TemplateFragment_MembersInjector.injectSkillLatencyTracker(instance, this.provideSkillLatencyTrackerProvider.get());
            MusicHomeFragment_MembersInjector.injectChromeProvider(instance, this.provideChromeProvider.get());
            return instance;
        }
    }

    private static final class VideoQueueActivitySubcomponentImpl implements ExploreAndroidComponentsModule_VideoQueueActivity$VideoQueueActivitySubcomponent {
        private final ApplicationComponentImpl applicationComponentImpl;
        private Provider<DownloadProvider> provideDownloadProvider;
        private Provider<EnvironmentProvider> provideEnvironmentProvider;
        private Provider<ExplicitFilterProvider> provideExplicitFilterProvider;
        private Provider<ExternalActionProvider> provideExternalActionProvider;
        private Provider<LibraryProvider> provideLibraryProvider;
        private Provider<NavigationProvider> provideNavigationProvider;
        private Provider<NotificationProvider> provideNotificationProvider;
        private Provider<PlaybackProvider> providePlaybackProvider;
        private Provider<SharingProvider> provideSharingProvider;
        private Provider<StorageProvider> provideStorageProvider;
        private Provider<UserSubscriptionProvider> provideUserSubscriptionProvider;
        private Provider<WakeWordProvider> provideWakeWordProvider;
        private final VideoQueueActivitySubcomponentImpl videoQueueActivitySubcomponentImpl;

        private VideoQueueActivitySubcomponentImpl(ApplicationComponentImpl applicationComponentImpl, ExploreModule exploreModuleParam, VideoQueueActivity arg0Param) {
            this.videoQueueActivitySubcomponentImpl = this;
            this.applicationComponentImpl = applicationComponentImpl;
            initialize(exploreModuleParam, arg0Param);
        }

        private void initialize(final ExploreModule exploreModuleParam, final VideoQueueActivity arg0Param) {
            this.provideStorageProvider = SingleCheck.provider(ExploreModule_ProvideStorageProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideEnvironmentProvider = SingleCheck.provider(ExploreModule_ProvideEnvironmentProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideExplicitFilterProvider = SingleCheck.provider(ExploreModule_ProvideExplicitFilterProviderFactory.create(exploreModuleParam));
            this.provideWakeWordProvider = SingleCheck.provider(ExploreModule_ProvideWakeWordProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideUserSubscriptionProvider = SingleCheck.provider(ExploreModule_ProvideUserSubscriptionProviderFactory.create(exploreModuleParam));
            this.provideSharingProvider = SingleCheck.provider(ExploreModule_ProvideSharingProviderFactory.create(exploreModuleParam));
            this.provideLibraryProvider = SingleCheck.provider(ExploreModule_ProvideLibraryProviderFactory.create(exploreModuleParam));
            this.provideNavigationProvider = SingleCheck.provider(ExploreModule_ProvideNavigationProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideNotificationProvider = SingleCheck.provider(ExploreModule_ProvideNotificationProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.providePlaybackProvider = SingleCheck.provider(ExploreModule_ProvidePlaybackProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            Provider<DownloadProvider> provider = SingleCheck.provider(ExploreModule_ProvideDownloadProviderFactory.create(exploreModuleParam));
            this.provideDownloadProvider = provider;
            this.provideExternalActionProvider = SingleCheck.provider(ExploreModule_ProvideExternalActionProviderFactory.create(exploreModuleParam, this.provideLibraryProvider, this.provideNavigationProvider, this.provideNotificationProvider, this.providePlaybackProvider, this.provideSharingProvider, provider));
        }

        @Override // dagger.android.AndroidInjector
        public void inject(VideoQueueActivity arg0) {
            injectVideoQueueActivity(arg0);
        }

        private VideoQueueActivity injectVideoQueueActivity(VideoQueueActivity instance) {
            BaseExploreActivity_MembersInjector.injectAuthenticationProvider(instance, PlatformProvidersModule_ProvideAuthenticationProviderFactory.provideAuthenticationProvider(this.applicationComponentImpl.platformProvidersModule));
            BaseExploreActivity_MembersInjector.injectCustomerMetadataProvider(instance, PlatformProvidersModule_ProvideCustomerMetadataProviderFactory.provideCustomerMetadataProvider(this.applicationComponentImpl.platformProvidersModule));
            BaseExploreActivity_MembersInjector.injectDeviceInfoProvider(instance, PlatformProvidersModule_ProvideDeviceInfoProviderFactory.provideDeviceInfoProvider(this.applicationComponentImpl.platformProvidersModule));
            BaseExploreActivity_MembersInjector.injectBuildInfoProvider(instance, PlatformProvidersModule_ProvideBuildInfoProviderFactory.provideBuildInfoProvider(this.applicationComponentImpl.platformProvidersModule));
            BaseExploreActivity_MembersInjector.injectStorageProvider(instance, this.provideStorageProvider.get());
            BaseExploreActivity_MembersInjector.injectEnvironmentProvider(instance, this.provideEnvironmentProvider.get());
            BaseExploreActivity_MembersInjector.injectExplicitFilterProvider(instance, this.provideExplicitFilterProvider.get());
            BaseExploreActivity_MembersInjector.injectMetricsProvider(instance, PlatformProvidersModule_ProvideMetricsProviderFactory.provideMetricsProvider(this.applicationComponentImpl.platformProvidersModule));
            BaseExploreActivity_MembersInjector.injectWakeWordProvider(instance, this.provideWakeWordProvider.get());
            BaseExploreActivity_MembersInjector.injectUserSubscriptionProvider(instance, this.provideUserSubscriptionProvider.get());
            BaseExploreActivity_MembersInjector.injectSharingProvider(instance, this.provideSharingProvider.get());
            BaseExploreActivity_MembersInjector.injectExternalActionProvider(instance, this.provideExternalActionProvider.get());
            BaseExploreActivity_MembersInjector.injectPlaybackProvider(instance, this.providePlaybackProvider.get());
            BaseExploreActivity_MembersInjector.injectNavigationProvider(instance, this.provideNavigationProvider.get());
            return instance;
        }
    }

    private static final class DetailFragmentSubcomponentImpl implements ExploreAndroidComponentsModule_DetailFragment$DetailFragmentSubcomponent {
        private final ApplicationComponentImpl applicationComponentImpl;
        private final DetailFragmentSubcomponentImpl detailFragmentSubcomponentImpl;
        private final ExploreModule exploreModule;
        private Provider<CapabilitiesInfo> provideCapabilitiesInfoProvider;
        private Provider<ChromeProvider> provideChromeProvider;
        private Provider<ContextMenuProvider> provideContextMenuProvider;
        private Provider<DownloadProvider> provideDownloadProvider;
        private Provider<ExternalActionProvider> provideExternalActionProvider;
        private Provider<LibraryProvider> provideLibraryProvider;
        private Provider<NavigationProvider> provideNavigationProvider;
        private Provider<NetworkInterceptor> provideNetworkInterceptorProvider;
        private Provider<NotificationProvider> provideNotificationProvider;
        private Provider<PlaybackProvider> providePlaybackProvider;
        private Provider<SharingProvider> provideSharingProvider;
        private Provider<SkillLatencyTracker> provideSkillLatencyTrackerProvider;
        private Provider<SkyFireConfigurationProvider> provideSkyFireConfigurationProvider;
        private Provider<SkyFireProvider> provideSkyFireProvider;
        private Provider<StyleSheetProvider> provideStyleSheetProvider;
        private Provider<UpsellProvider> provideUpsellProvider;
        private Provider<UserSubscriptionProvider> provideUserSubscriptionProvider;

        private DetailFragmentSubcomponentImpl(ApplicationComponentImpl applicationComponentImpl, ExploreModule exploreModuleParam, DetailFragment arg0Param) {
            this.detailFragmentSubcomponentImpl = this;
            this.applicationComponentImpl = applicationComponentImpl;
            this.exploreModule = exploreModuleParam;
            initialize(exploreModuleParam, arg0Param);
        }

        private ElementBindingProvider elementBindingProvider() {
            return ExploreModule_ProvideElementBindingProviderFactory.provideElementBindingProvider(this.exploreModule, this.provideUserSubscriptionProvider.get(), this.provideStyleSheetProvider.get(), this.provideDownloadProvider.get());
        }

        private void initialize(final ExploreModule exploreModuleParam, final DetailFragment arg0Param) {
            this.provideStyleSheetProvider = SingleCheck.provider(ExploreModule_ProvideStyleSheetProviderFactory.create(exploreModuleParam));
            this.provideUserSubscriptionProvider = SingleCheck.provider(ExploreModule_ProvideUserSubscriptionProviderFactory.create(exploreModuleParam));
            this.provideDownloadProvider = SingleCheck.provider(ExploreModule_ProvideDownloadProviderFactory.create(exploreModuleParam));
            this.provideLibraryProvider = SingleCheck.provider(ExploreModule_ProvideLibraryProviderFactory.create(exploreModuleParam));
            this.provideNavigationProvider = SingleCheck.provider(ExploreModule_ProvideNavigationProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideNotificationProvider = SingleCheck.provider(ExploreModule_ProvideNotificationProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.providePlaybackProvider = SingleCheck.provider(ExploreModule_ProvidePlaybackProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            Provider<SharingProvider> provider = SingleCheck.provider(ExploreModule_ProvideSharingProviderFactory.create(exploreModuleParam));
            this.provideSharingProvider = provider;
            this.provideExternalActionProvider = SingleCheck.provider(ExploreModule_ProvideExternalActionProviderFactory.create(exploreModuleParam, this.provideLibraryProvider, this.provideNavigationProvider, this.provideNotificationProvider, this.providePlaybackProvider, provider, this.provideDownloadProvider));
            this.provideCapabilitiesInfoProvider = SingleCheck.provider(ExploreModule_ProvideCapabilitiesInfoFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideNetworkInterceptorProvider = SingleCheck.provider(ExploreModule_ProvideNetworkInterceptorFactory.create(exploreModuleParam));
            this.provideSkillLatencyTrackerProvider = SingleCheck.provider(ExploreModule_ProvideSkillLatencyTrackerFactory.create(exploreModuleParam));
            this.provideSkyFireConfigurationProvider = SingleCheck.provider(ExploreModule_ProvideSkyFireConfigurationProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideSkyFireProvider = SingleCheck.provider(ExploreModule_ProvideSkyFireProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider, this.applicationComponentImpl.provideAuthenticationProvider, this.applicationComponentImpl.provideCustomerMetadataProvider, this.applicationComponentImpl.provideDeviceInfoProvider, this.applicationComponentImpl.provideBuildInfoProvider, this.providePlaybackProvider, this.provideCapabilitiesInfoProvider, this.provideNetworkInterceptorProvider, this.provideSkillLatencyTrackerProvider, this.provideSkyFireConfigurationProvider));
            this.provideContextMenuProvider = SingleCheck.provider(ExploreModule_ProvideContextMenuProviderFactory.create(exploreModuleParam));
            this.provideUpsellProvider = SingleCheck.provider(ExploreModule_ProvideUpsellProviderFactory.create(exploreModuleParam));
            this.provideChromeProvider = SingleCheck.provider(ExploreModule_ProvideChromeProviderFactory.create(exploreModuleParam));
        }

        @Override // dagger.android.AndroidInjector
        public void inject(DetailFragment arg0) {
            injectDetailFragment(arg0);
        }

        private DetailFragment injectDetailFragment(DetailFragment instance) {
            TemplateFragment_MembersInjector.injectStyleSheetProvider(instance, this.provideStyleSheetProvider.get());
            TemplateFragment_MembersInjector.injectElementBindingProvider(instance, elementBindingProvider());
            TemplateFragment_MembersInjector.injectExternalActionProvider(instance, this.provideExternalActionProvider.get());
            TemplateFragment_MembersInjector.injectSkyFireProvider(instance, this.provideSkyFireProvider.get());
            TemplateFragment_MembersInjector.injectSkyFireConfigurationProvider(instance, this.provideSkyFireConfigurationProvider.get());
            TemplateFragment_MembersInjector.injectContextMenuProvider(instance, this.provideContextMenuProvider.get());
            TemplateFragment_MembersInjector.injectPlaybackProvider(instance, this.providePlaybackProvider.get());
            TemplateFragment_MembersInjector.injectUpsellProvider(instance, this.provideUpsellProvider.get());
            TemplateFragment_MembersInjector.injectNavigationProvider(instance, this.provideNavigationProvider.get());
            TemplateFragment_MembersInjector.injectSkillLatencyTracker(instance, this.provideSkillLatencyTrackerProvider.get());
            MusicHomeFragment_MembersInjector.injectChromeProvider(instance, this.provideChromeProvider.get());
            DetailFragment_MembersInjector.injectUserSubscriptionProvider(instance, this.provideUserSubscriptionProvider.get());
            return instance;
        }
    }

    private static final class SelectionFragmentSubcomponentImpl implements ExploreAndroidComponentsModule_SelectionFragment$SelectionFragmentSubcomponent {
        private final ApplicationComponentImpl applicationComponentImpl;
        private final ExploreModule exploreModule;
        private Provider<DownloadProvider> provideDownloadProvider;
        private Provider<StyleSheetProvider> provideStyleSheetProvider;
        private Provider<UserSubscriptionProvider> provideUserSubscriptionProvider;
        private final SelectionFragmentSubcomponentImpl selectionFragmentSubcomponentImpl;

        private SelectionFragmentSubcomponentImpl(ApplicationComponentImpl applicationComponentImpl, ExploreModule exploreModuleParam, SelectionFragment arg0Param) {
            this.selectionFragmentSubcomponentImpl = this;
            this.applicationComponentImpl = applicationComponentImpl;
            this.exploreModule = exploreModuleParam;
            initialize(exploreModuleParam, arg0Param);
        }

        private ElementBindingProvider elementBindingProvider() {
            return ExploreModule_ProvideElementBindingProviderFactory.provideElementBindingProvider(this.exploreModule, this.provideUserSubscriptionProvider.get(), this.provideStyleSheetProvider.get(), this.provideDownloadProvider.get());
        }

        private void initialize(final ExploreModule exploreModuleParam, final SelectionFragment arg0Param) {
            this.provideStyleSheetProvider = SingleCheck.provider(ExploreModule_ProvideStyleSheetProviderFactory.create(exploreModuleParam));
            this.provideUserSubscriptionProvider = SingleCheck.provider(ExploreModule_ProvideUserSubscriptionProviderFactory.create(exploreModuleParam));
            this.provideDownloadProvider = SingleCheck.provider(ExploreModule_ProvideDownloadProviderFactory.create(exploreModuleParam));
        }

        @Override // dagger.android.AndroidInjector
        public void inject(SelectionFragment arg0) {
            injectSelectionFragment(arg0);
        }

        private SelectionFragment injectSelectionFragment(SelectionFragment instance) {
            SelectionFragment_MembersInjector.injectStyleSheetProvider(instance, this.provideStyleSheetProvider.get());
            SelectionFragment_MembersInjector.injectElementBindingProvider(instance, elementBindingProvider());
            return instance;
        }
    }

    private static final class MultiSelectorFragmentSubcomponentImpl implements ExploreAndroidComponentsModule_MultiSelectorFragment$MultiSelectorFragmentSubcomponent {
        private final ApplicationComponentImpl applicationComponentImpl;
        private final ExploreModule exploreModule;
        private final MultiSelectorFragmentSubcomponentImpl multiSelectorFragmentSubcomponentImpl;
        private Provider<CapabilitiesInfo> provideCapabilitiesInfoProvider;
        private Provider<ChromeProvider> provideChromeProvider;
        private Provider<ContextMenuProvider> provideContextMenuProvider;
        private Provider<DownloadProvider> provideDownloadProvider;
        private Provider<ExternalActionProvider> provideExternalActionProvider;
        private Provider<LibraryProvider> provideLibraryProvider;
        private Provider<NavigationProvider> provideNavigationProvider;
        private Provider<NetworkInterceptor> provideNetworkInterceptorProvider;
        private Provider<NotificationProvider> provideNotificationProvider;
        private Provider<PlaybackProvider> providePlaybackProvider;
        private Provider<SharingProvider> provideSharingProvider;
        private Provider<SkillLatencyTracker> provideSkillLatencyTrackerProvider;
        private Provider<SkyFireConfigurationProvider> provideSkyFireConfigurationProvider;
        private Provider<SkyFireProvider> provideSkyFireProvider;
        private Provider<StyleSheetProvider> provideStyleSheetProvider;
        private Provider<UpsellProvider> provideUpsellProvider;
        private Provider<UserSubscriptionProvider> provideUserSubscriptionProvider;

        private MultiSelectorFragmentSubcomponentImpl(ApplicationComponentImpl applicationComponentImpl, ExploreModule exploreModuleParam, MultiSelectorFragment arg0Param) {
            this.multiSelectorFragmentSubcomponentImpl = this;
            this.applicationComponentImpl = applicationComponentImpl;
            this.exploreModule = exploreModuleParam;
            initialize(exploreModuleParam, arg0Param);
        }

        private ElementBindingProvider elementBindingProvider() {
            return ExploreModule_ProvideElementBindingProviderFactory.provideElementBindingProvider(this.exploreModule, this.provideUserSubscriptionProvider.get(), this.provideStyleSheetProvider.get(), this.provideDownloadProvider.get());
        }

        private void initialize(final ExploreModule exploreModuleParam, final MultiSelectorFragment arg0Param) {
            this.provideStyleSheetProvider = SingleCheck.provider(ExploreModule_ProvideStyleSheetProviderFactory.create(exploreModuleParam));
            this.provideUserSubscriptionProvider = SingleCheck.provider(ExploreModule_ProvideUserSubscriptionProviderFactory.create(exploreModuleParam));
            this.provideDownloadProvider = SingleCheck.provider(ExploreModule_ProvideDownloadProviderFactory.create(exploreModuleParam));
            this.provideLibraryProvider = SingleCheck.provider(ExploreModule_ProvideLibraryProviderFactory.create(exploreModuleParam));
            this.provideNavigationProvider = SingleCheck.provider(ExploreModule_ProvideNavigationProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideNotificationProvider = SingleCheck.provider(ExploreModule_ProvideNotificationProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.providePlaybackProvider = SingleCheck.provider(ExploreModule_ProvidePlaybackProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            Provider<SharingProvider> provider = SingleCheck.provider(ExploreModule_ProvideSharingProviderFactory.create(exploreModuleParam));
            this.provideSharingProvider = provider;
            this.provideExternalActionProvider = SingleCheck.provider(ExploreModule_ProvideExternalActionProviderFactory.create(exploreModuleParam, this.provideLibraryProvider, this.provideNavigationProvider, this.provideNotificationProvider, this.providePlaybackProvider, provider, this.provideDownloadProvider));
            this.provideCapabilitiesInfoProvider = SingleCheck.provider(ExploreModule_ProvideCapabilitiesInfoFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideNetworkInterceptorProvider = SingleCheck.provider(ExploreModule_ProvideNetworkInterceptorFactory.create(exploreModuleParam));
            this.provideSkillLatencyTrackerProvider = SingleCheck.provider(ExploreModule_ProvideSkillLatencyTrackerFactory.create(exploreModuleParam));
            this.provideSkyFireConfigurationProvider = SingleCheck.provider(ExploreModule_ProvideSkyFireConfigurationProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideSkyFireProvider = SingleCheck.provider(ExploreModule_ProvideSkyFireProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider, this.applicationComponentImpl.provideAuthenticationProvider, this.applicationComponentImpl.provideCustomerMetadataProvider, this.applicationComponentImpl.provideDeviceInfoProvider, this.applicationComponentImpl.provideBuildInfoProvider, this.providePlaybackProvider, this.provideCapabilitiesInfoProvider, this.provideNetworkInterceptorProvider, this.provideSkillLatencyTrackerProvider, this.provideSkyFireConfigurationProvider));
            this.provideContextMenuProvider = SingleCheck.provider(ExploreModule_ProvideContextMenuProviderFactory.create(exploreModuleParam));
            this.provideUpsellProvider = SingleCheck.provider(ExploreModule_ProvideUpsellProviderFactory.create(exploreModuleParam));
            this.provideChromeProvider = SingleCheck.provider(ExploreModule_ProvideChromeProviderFactory.create(exploreModuleParam));
        }

        @Override // dagger.android.AndroidInjector
        public void inject(MultiSelectorFragment arg0) {
            injectMultiSelectorFragment(arg0);
        }

        private MultiSelectorFragment injectMultiSelectorFragment(MultiSelectorFragment instance) {
            TemplateFragment_MembersInjector.injectStyleSheetProvider(instance, this.provideStyleSheetProvider.get());
            TemplateFragment_MembersInjector.injectElementBindingProvider(instance, elementBindingProvider());
            TemplateFragment_MembersInjector.injectExternalActionProvider(instance, this.provideExternalActionProvider.get());
            TemplateFragment_MembersInjector.injectSkyFireProvider(instance, this.provideSkyFireProvider.get());
            TemplateFragment_MembersInjector.injectSkyFireConfigurationProvider(instance, this.provideSkyFireConfigurationProvider.get());
            TemplateFragment_MembersInjector.injectContextMenuProvider(instance, this.provideContextMenuProvider.get());
            TemplateFragment_MembersInjector.injectPlaybackProvider(instance, this.providePlaybackProvider.get());
            TemplateFragment_MembersInjector.injectUpsellProvider(instance, this.provideUpsellProvider.get());
            TemplateFragment_MembersInjector.injectNavigationProvider(instance, this.provideNavigationProvider.get());
            TemplateFragment_MembersInjector.injectSkillLatencyTracker(instance, this.provideSkillLatencyTrackerProvider.get());
            MusicHomeFragment_MembersInjector.injectChromeProvider(instance, this.provideChromeProvider.get());
            return instance;
        }
    }

    private static final class MultiSelectorTabFragmentSubcomponentImpl implements ExploreAndroidComponentsModule_MultiSelectorTabFragment$MultiSelectorTabFragmentSubcomponent {
        private final ApplicationComponentImpl applicationComponentImpl;
        private final MultiSelectorTabFragmentSubcomponentImpl multiSelectorTabFragmentSubcomponentImpl;

        @Override // dagger.android.AndroidInjector
        public void inject(MultiSelectorTabFragment arg0) {
        }

        private MultiSelectorTabFragmentSubcomponentImpl(ApplicationComponentImpl applicationComponentImpl, MultiSelectorTabFragment arg0Param) {
            this.multiSelectorTabFragmentSubcomponentImpl = this;
            this.applicationComponentImpl = applicationComponentImpl;
        }
    }

    private static final class WatchSessionServiceSubcomponentImpl implements ExploreAndroidComponentsModule_WatchSessionService$WatchSessionServiceSubcomponent {
        private final ApplicationComponentImpl applicationComponentImpl;
        private Provider<CapabilitiesInfo> provideCapabilitiesInfoProvider;
        private Provider<DownloadProvider> provideDownloadProvider;
        private Provider<ExternalActionProvider> provideExternalActionProvider;
        private Provider<LibraryProvider> provideLibraryProvider;
        private Provider<NavigationProvider> provideNavigationProvider;
        private Provider<NetworkInterceptor> provideNetworkInterceptorProvider;
        private Provider<NotificationProvider> provideNotificationProvider;
        private Provider<PlaybackProvider> providePlaybackProvider;
        private Provider<SharingProvider> provideSharingProvider;
        private Provider<SkillLatencyTracker> provideSkillLatencyTrackerProvider;
        private Provider<SkyFireConfigurationProvider> provideSkyFireConfigurationProvider;
        private Provider<SkyFireProvider> provideSkyFireProvider;
        private final WatchSessionServiceSubcomponentImpl watchSessionServiceSubcomponentImpl;

        private WatchSessionServiceSubcomponentImpl(ApplicationComponentImpl applicationComponentImpl, ExploreModule exploreModuleParam, WatchSessionService arg0Param) {
            this.watchSessionServiceSubcomponentImpl = this;
            this.applicationComponentImpl = applicationComponentImpl;
            initialize(exploreModuleParam, arg0Param);
        }

        private void initialize(final ExploreModule exploreModuleParam, final WatchSessionService arg0Param) {
            this.providePlaybackProvider = SingleCheck.provider(ExploreModule_ProvidePlaybackProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideCapabilitiesInfoProvider = SingleCheck.provider(ExploreModule_ProvideCapabilitiesInfoFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideNetworkInterceptorProvider = SingleCheck.provider(ExploreModule_ProvideNetworkInterceptorFactory.create(exploreModuleParam));
            this.provideSkillLatencyTrackerProvider = SingleCheck.provider(ExploreModule_ProvideSkillLatencyTrackerFactory.create(exploreModuleParam));
            this.provideSkyFireConfigurationProvider = SingleCheck.provider(ExploreModule_ProvideSkyFireConfigurationProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideSkyFireProvider = SingleCheck.provider(ExploreModule_ProvideSkyFireProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider, this.applicationComponentImpl.provideAuthenticationProvider, this.applicationComponentImpl.provideCustomerMetadataProvider, this.applicationComponentImpl.provideDeviceInfoProvider, this.applicationComponentImpl.provideBuildInfoProvider, this.providePlaybackProvider, this.provideCapabilitiesInfoProvider, this.provideNetworkInterceptorProvider, this.provideSkillLatencyTrackerProvider, this.provideSkyFireConfigurationProvider));
            this.provideLibraryProvider = SingleCheck.provider(ExploreModule_ProvideLibraryProviderFactory.create(exploreModuleParam));
            this.provideNavigationProvider = SingleCheck.provider(ExploreModule_ProvideNavigationProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideNotificationProvider = SingleCheck.provider(ExploreModule_ProvideNotificationProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideSharingProvider = SingleCheck.provider(ExploreModule_ProvideSharingProviderFactory.create(exploreModuleParam));
            Provider<DownloadProvider> provider = SingleCheck.provider(ExploreModule_ProvideDownloadProviderFactory.create(exploreModuleParam));
            this.provideDownloadProvider = provider;
            this.provideExternalActionProvider = SingleCheck.provider(ExploreModule_ProvideExternalActionProviderFactory.create(exploreModuleParam, this.provideLibraryProvider, this.provideNavigationProvider, this.provideNotificationProvider, this.providePlaybackProvider, this.provideSharingProvider, provider));
        }

        @Override // dagger.android.AndroidInjector
        public void inject(WatchSessionService arg0) {
            injectWatchSessionService(arg0);
        }

        private WatchSessionService injectWatchSessionService(WatchSessionService instance) {
            WatchSessionService_MembersInjector.injectSkyFireProvider(instance, this.provideSkyFireProvider.get());
            WatchSessionService_MembersInjector.injectExternalActionProvider(instance, this.provideExternalActionProvider.get());
            WatchSessionService_MembersInjector.injectPlaybackProvider(instance, this.providePlaybackProvider.get());
            return instance;
        }
    }

    private static final class VideoStoryFragmentSubcomponentImpl implements ExploreAndroidComponentsModule_VideoStoryFragment$VideoStoryFragmentSubcomponent {
        private final ApplicationComponentImpl applicationComponentImpl;
        private Provider<AnimationProvider> provideAnimationProvider;
        private Provider<CastingProvider> provideCastingProvider;
        private Provider<DownloadProvider> provideDownloadProvider;
        private Provider<EnvironmentProvider> provideEnvironmentProvider;
        private Provider<ExternalActionProvider> provideExternalActionProvider;
        private Provider<LibraryProvider> provideLibraryProvider;
        private Provider<NavigationProvider> provideNavigationProvider;
        private Provider<NotificationProvider> provideNotificationProvider;
        private Provider<PlaybackProvider> providePlaybackProvider;
        private Provider<SharingProvider> provideSharingProvider;
        private Provider<StorageProvider> provideStorageProvider;
        private Provider<StyleSheetProvider> provideStyleSheetProvider;
        private Provider<UpsellProvider> provideUpsellProvider;
        private final VideoStoryFragmentSubcomponentImpl videoStoryFragmentSubcomponentImpl;

        private VideoStoryFragmentSubcomponentImpl(ApplicationComponentImpl applicationComponentImpl, ExploreModule exploreModuleParam, VideoStoryFragment arg0Param) {
            this.videoStoryFragmentSubcomponentImpl = this;
            this.applicationComponentImpl = applicationComponentImpl;
            initialize(exploreModuleParam, arg0Param);
        }

        private void initialize(final ExploreModule exploreModuleParam, final VideoStoryFragment arg0Param) {
            this.provideLibraryProvider = SingleCheck.provider(ExploreModule_ProvideLibraryProviderFactory.create(exploreModuleParam));
            this.provideNavigationProvider = SingleCheck.provider(ExploreModule_ProvideNavigationProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideNotificationProvider = SingleCheck.provider(ExploreModule_ProvideNotificationProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.providePlaybackProvider = SingleCheck.provider(ExploreModule_ProvidePlaybackProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideSharingProvider = SingleCheck.provider(ExploreModule_ProvideSharingProviderFactory.create(exploreModuleParam));
            Provider<DownloadProvider> provider = SingleCheck.provider(ExploreModule_ProvideDownloadProviderFactory.create(exploreModuleParam));
            this.provideDownloadProvider = provider;
            this.provideExternalActionProvider = SingleCheck.provider(ExploreModule_ProvideExternalActionProviderFactory.create(exploreModuleParam, this.provideLibraryProvider, this.provideNavigationProvider, this.provideNotificationProvider, this.providePlaybackProvider, this.provideSharingProvider, provider));
            this.provideStorageProvider = SingleCheck.provider(ExploreModule_ProvideStorageProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideEnvironmentProvider = SingleCheck.provider(ExploreModule_ProvideEnvironmentProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideUpsellProvider = SingleCheck.provider(ExploreModule_ProvideUpsellProviderFactory.create(exploreModuleParam));
            this.provideCastingProvider = SingleCheck.provider(ExploreModule_ProvideCastingProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideAnimationProvider = SingleCheck.provider(ExploreModule_ProvideAnimationProviderFactory.create(exploreModuleParam));
            this.provideStyleSheetProvider = SingleCheck.provider(ExploreModule_ProvideStyleSheetProviderFactory.create(exploreModuleParam));
        }

        @Override // dagger.android.AndroidInjector
        public void inject(VideoStoryFragment arg0) {
            injectVideoStoryFragment(arg0);
        }

        private VideoStoryFragment injectVideoStoryFragment(VideoStoryFragment instance) {
            BaseVideoStoryFragment_MembersInjector.injectAuthenticationProvider(instance, PlatformProvidersModule_ProvideAuthenticationProviderFactory.provideAuthenticationProvider(this.applicationComponentImpl.platformProvidersModule));
            BaseVideoStoryFragment_MembersInjector.injectCustomerMetadataProvider(instance, PlatformProvidersModule_ProvideCustomerMetadataProviderFactory.provideCustomerMetadataProvider(this.applicationComponentImpl.platformProvidersModule));
            BaseVideoStoryFragment_MembersInjector.injectDeviceInfoProvider(instance, PlatformProvidersModule_ProvideDeviceInfoProviderFactory.provideDeviceInfoProvider(this.applicationComponentImpl.platformProvidersModule));
            BaseVideoStoryFragment_MembersInjector.injectBuildInfoProvider(instance, PlatformProvidersModule_ProvideBuildInfoProviderFactory.provideBuildInfoProvider(this.applicationComponentImpl.platformProvidersModule));
            BaseVideoStoryFragment_MembersInjector.injectExternalActionProvider(instance, this.provideExternalActionProvider.get());
            BaseVideoStoryFragment_MembersInjector.injectStorageProvider(instance, this.provideStorageProvider.get());
            BaseVideoStoryFragment_MembersInjector.injectEnvironmentProvider(instance, this.provideEnvironmentProvider.get());
            BaseVideoStoryFragment_MembersInjector.injectPlaybackProvider(instance, this.providePlaybackProvider.get());
            BaseVideoStoryFragment_MembersInjector.injectUpsellProvider(instance, this.provideUpsellProvider.get());
            BaseVideoStoryFragment_MembersInjector.injectCastingProvider(instance, this.provideCastingProvider.get());
            BaseVideoStoryFragment_MembersInjector.injectAnimationProvider(instance, this.provideAnimationProvider.get());
            BaseVideoStoryFragment_MembersInjector.injectStyleSheetProvider(instance, this.provideStyleSheetProvider.get());
            BaseVideoStoryFragment_MembersInjector.injectMetricsProvider(instance, PlatformProvidersModule_ProvideMetricsProviderFactory.provideMetricsProvider(this.applicationComponentImpl.platformProvidersModule));
            BaseVideoStoryFragment_MembersInjector.injectSharingProvider(instance, this.provideSharingProvider.get());
            return instance;
        }
    }

    private static final class CastingSkillServiceSubcomponentImpl implements ExploreAndroidComponentsModule_CastingSkillService$CastingSkillServiceSubcomponent {
        private final ApplicationComponentImpl applicationComponentImpl;
        private final CastingSkillServiceSubcomponentImpl castingSkillServiceSubcomponentImpl;
        private Provider<CapabilitiesInfo> provideCapabilitiesInfoProvider;
        private Provider<NetworkInterceptor> provideNetworkInterceptorProvider;
        private Provider<PlaybackProvider> providePlaybackProvider;
        private Provider<SkillLatencyTracker> provideSkillLatencyTrackerProvider;
        private Provider<SkyFireConfigurationProvider> provideSkyFireConfigurationProvider;
        private Provider<SkyFireProvider> provideSkyFireProvider;

        private CastingSkillServiceSubcomponentImpl(ApplicationComponentImpl applicationComponentImpl, ExploreModule exploreModuleParam, CastingSkillService arg0Param) {
            this.castingSkillServiceSubcomponentImpl = this;
            this.applicationComponentImpl = applicationComponentImpl;
            initialize(exploreModuleParam, arg0Param);
        }

        private void initialize(final ExploreModule exploreModuleParam, final CastingSkillService arg0Param) {
            this.providePlaybackProvider = SingleCheck.provider(ExploreModule_ProvidePlaybackProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideCapabilitiesInfoProvider = SingleCheck.provider(ExploreModule_ProvideCapabilitiesInfoFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideNetworkInterceptorProvider = SingleCheck.provider(ExploreModule_ProvideNetworkInterceptorFactory.create(exploreModuleParam));
            this.provideSkillLatencyTrackerProvider = SingleCheck.provider(ExploreModule_ProvideSkillLatencyTrackerFactory.create(exploreModuleParam));
            this.provideSkyFireConfigurationProvider = SingleCheck.provider(ExploreModule_ProvideSkyFireConfigurationProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideSkyFireProvider = SingleCheck.provider(ExploreModule_ProvideSkyFireProviderFactory.create(exploreModuleParam, this.applicationComponentImpl.applicationProvider, this.applicationComponentImpl.provideAuthenticationProvider, this.applicationComponentImpl.provideCustomerMetadataProvider, this.applicationComponentImpl.provideDeviceInfoProvider, this.applicationComponentImpl.provideBuildInfoProvider, this.providePlaybackProvider, this.provideCapabilitiesInfoProvider, this.provideNetworkInterceptorProvider, this.provideSkillLatencyTrackerProvider, this.provideSkyFireConfigurationProvider));
        }

        @Override // dagger.android.AndroidInjector
        public void inject(CastingSkillService arg0) {
            injectCastingSkillService(arg0);
        }

        private CastingSkillService injectCastingSkillService(CastingSkillService instance) {
            CastingSkillService_MembersInjector.injectSkyFireProvider(instance, this.provideSkyFireProvider.get());
            return instance;
        }
    }

    private static final class FindBrushFragmentSubcomponentImpl implements FindAndroidComponentsModule_FindBrushFragment$FindBrushFragmentSubcomponent {
        private final ApplicationComponentImpl applicationComponentImpl;
        private final FindBrushFragmentSubcomponentImpl findBrushFragmentSubcomponentImpl;

        @Override // dagger.android.AndroidInjector
        public void inject(FindBrushFragment arg0) {
        }

        private FindBrushFragmentSubcomponentImpl(ApplicationComponentImpl applicationComponentImpl, FindBrushFragment arg0Param) {
            this.findBrushFragmentSubcomponentImpl = this;
            this.applicationComponentImpl = applicationComponentImpl;
        }
    }

    private static final class FindFilterDialogFragmentSubcomponentImpl implements FindAndroidComponentsModule_FindFilterDialogFragment$FindFilterDialogFragmentSubcomponent {
        private final ApplicationComponentImpl applicationComponentImpl;
        private final FindFilterDialogFragmentSubcomponentImpl findFilterDialogFragmentSubcomponentImpl;
        private final FindModule findModule;

        private FindFilterDialogFragmentSubcomponentImpl(ApplicationComponentImpl applicationComponentImpl, FindModule findModuleParam, FindFilterDialogFragment arg0Param) {
            this.findFilterDialogFragmentSubcomponentImpl = this;
            this.applicationComponentImpl = applicationComponentImpl;
            this.findModule = findModuleParam;
        }

        private SearchFeaturesProvider searchFeaturesProvider() {
            return FindModule_ProvideSearchFeaturesProviderFactory.provideSearchFeaturesProvider(this.findModule, this.applicationComponentImpl.application);
        }

        private PageGridViewModelConverter<SearchFilterSelections> pageGridViewModelConverterOfSearchFilterSelections() {
            return FindModule_ProvideFilterSelectionConverterFactory.provideFilterSelectionConverter(this.findModule, this.applicationComponentImpl.application, searchFeaturesProvider());
        }

        private SearchMetricsService searchMetricsService() {
            FindModule findModule = this.findModule;
            return FindModule_ProvidesSearchMetricsServiceFactory.providesSearchMetricsService(findModule, FindModule_ProvidesSearchMetricsApiFactory.providesSearchMetricsApi(findModule), searchFeaturesProvider());
        }

        private FilterViewModelFactory filterViewModelFactory() {
            return FindModule_ProvideFilterViewModelFactoryFactory.provideFilterViewModelFactory(this.findModule, pageGridViewModelConverterOfSearchFilterSelections(), searchMetricsService(), searchFeaturesProvider());
        }

        private BrushPageGridConverter brushPageGridConverter() {
            return FindModule_ProviderBrushPageGridConverterFactory.providerBrushPageGridConverter(this.findModule, this.applicationComponentImpl.application);
        }

        private AppDatabase appDatabase() {
            return FindModule_ProvideAppDatabaseFactory.provideAppDatabase(this.findModule, this.applicationComponentImpl.application);
        }

        private SearchEntityDao searchEntityDao() {
            return FindModule_ProvideSearchEntityDaoFactory.provideSearchEntityDao(this.findModule, appDatabase());
        }

        private SearchSuggestionServiceConfigurationProvider searchSuggestionServiceConfigurationProvider() {
            return FindModule_ProvideSearchSuggestionConfigurationProviderFactory.provideSearchSuggestionConfigurationProvider(this.findModule, this.applicationComponentImpl.application);
        }

        private InstantSearchSuggestionServiceConfigurationProvider instantSearchSuggestionServiceConfigurationProvider() {
            return FindModule_ProvideInstantSearchSuggestionConfigurationProviderFactory.provideInstantSearchSuggestionConfigurationProvider(this.findModule, this.applicationComponentImpl.application);
        }

        private UIConfigurationService uIConfigurationService() {
            return FindModule_ProvideUIConfigurationServiceFactory.provideUIConfigurationService(this.findModule, this.applicationComponentImpl.application, searchFeaturesProvider(), FindModule_ProvideFindFeatureConfigurationProviderFactory.provideFindFeatureConfigurationProvider(this.findModule));
        }

        private SearchSuggestionApi searchSuggestionApi() {
            return FindModule_ProvideSearchSuggestionApiFactory.provideSearchSuggestionApi(this.findModule, searchSuggestionServiceConfigurationProvider(), instantSearchSuggestionServiceConfigurationProvider(), uIConfigurationService(), searchFeaturesProvider());
        }

        private SearchSuggestionService searchSuggestionService() {
            return FindModule_ProvideSearchSuggestionServiceFactory.provideSearchSuggestionService(this.findModule, searchEntityDao(), searchSuggestionApi(), uIConfigurationService(), searchFeaturesProvider());
        }

        private SearchQueryDao searchQueryDao() {
            return FindModule_ProvideSearchQueryDaoFactory.provideSearchQueryDao(this.findModule, appDatabase());
        }

        private UserSettingsService userSettingsService() {
            return FindModule_ProvideUserSettingsServiceFactory.provideUserSettingsService(this.findModule, this.applicationComponentImpl.application, searchFeaturesProvider());
        }

        private SearchHistoryService searchHistoryService() {
            return FindModule_ProvideSearchHistoryServiceFactory.provideSearchHistoryService(this.findModule, searchQueryDao(), searchEntityDao(), uIConfigurationService(), userSettingsService(), searchFeaturesProvider());
        }

        private BrushV2Converter<SearchHistoryItem> brushV2ConverterOfSearchHistoryItem() {
            return FindModule_ProvideSearchHistoryConverterFactory.provideSearchHistoryConverter(this.findModule, this.applicationComponentImpl.application, FindModule_ProvideCacheImageProviderFactory.provideCacheImageProvider(this.findModule), searchFeaturesProvider());
        }

        private BrushV2Converter<SearchSuggestion> brushV2ConverterOfSearchSuggestion() {
            return FindModule_ProvideSearchSuggestionConverterFactory.provideSearchSuggestionConverter(this.findModule, this.applicationComponentImpl.application, FindModule_ProvideCacheImageProviderFactory.provideCacheImageProvider(this.findModule), searchFeaturesProvider(), uIConfigurationService());
        }

        private BrushV2Converter<SearchResponse> namedBrushV2ConverterOfSearchResponse() {
            return FindModule_ProvideSearchResultConverterFactory.provideSearchResultConverter(this.findModule, this.applicationComponentImpl.application, FindModule_ProvideCacheImageProviderFactory.provideCacheImageProvider(this.findModule), searchFeaturesProvider(), searchMetricsService());
        }

        private BrushV2Converter<SeeMoreResponse> brushV2ConverterOfSeeMoreResponse() {
            return FindModule_ProvideSeeMoreResultConverterFactory.provideSeeMoreResultConverter(this.findModule, this.applicationComponentImpl.application, FindModule_ProvideCacheImageProviderFactory.provideCacheImageProvider(this.findModule), searchFeaturesProvider());
        }

        private ConverterService namedConverterService() {
            return FindModule_ProvideConverterServiceFactory.provideConverterService(this.findModule, brushV2ConverterOfSearchHistoryItem(), brushV2ConverterOfSearchSuggestion(), namedBrushV2ConverterOfSearchResponse(), brushV2ConverterOfSeeMoreResponse());
        }

        private SearchServiceConfigurationProvider searchServiceConfigurationProvider() {
            return FindModule_ProvideSearchConfigurationProviderFactory.provideSearchConfigurationProvider(this.findModule, this.applicationComponentImpl.application);
        }

        private CirrusDatabaseProvider cirrusDatabaseProvider() {
            return FindModule_ProvidesCirrusDatabaseProviderFactory.providesCirrusDatabaseProvider(this.findModule, this.applicationComponentImpl.application);
        }

        private LibrarySearchDao librarySearchDao() {
            return FindModule_ProvidesLibrarySearchDaoFactory.providesLibrarySearchDao(this.findModule, cirrusDatabaseProvider(), FindModule_ProvidesSearchEntityFactoryFactory.providesSearchEntityFactory(this.findModule), searchFeaturesProvider());
        }

        private SearchApi<GenericSearchResponse> namedSearchApiOfGenericSearchResponse() {
            return FindModule_ProvidesRemoteSearchApiFactory.providesRemoteSearchApi(this.findModule, searchServiceConfigurationProvider(), searchFeaturesProvider(), FindModule_ProvidesSearchEntityFactoryFactory.providesSearchEntityFactory(this.findModule), uIConfigurationService(), librarySearchDao());
        }

        private SearchApi<GenericSearchResponse> namedSearchApiOfGenericSearchResponse2() {
            return FindModule_ProvidesLibrarySearchApiFactory.providesLibrarySearchApi(this.findModule, librarySearchDao(), searchFeaturesProvider());
        }

        private SearchService searchService() {
            return FindModule_ProvidesSearchServiceFactory.providesSearchService(this.findModule, namedSearchApiOfGenericSearchResponse(), namedSearchApiOfGenericSearchResponse2(), uIConfigurationService(), searchFeaturesProvider());
        }

        private LibraryStateService libraryStateService() {
            return FindModule_ProvideLibraryStateServiceFactory.provideLibraryStateService(this.findModule, librarySearchDao(), searchFeaturesProvider());
        }

        private SearchViewModelFactory searchViewModelFactory() {
            return FindModule_ProvideSearchViewModelFactoryFactory.provideSearchViewModelFactory(this.findModule, brushPageGridConverter(), searchSuggestionService(), searchHistoryService(), namedConverterService(), searchService(), searchMetricsService(), searchFeaturesProvider(), uIConfigurationService(), libraryStateService());
        }

        @Override // dagger.android.AndroidInjector
        public void inject(FindFilterDialogFragment arg0) {
            injectFindFilterDialogFragment(arg0);
        }

        private FindFilterDialogFragment injectFindFilterDialogFragment(FindFilterDialogFragment instance) {
            FindFilterDialogFragment_MembersInjector.injectFilterViewModelFactory(instance, filterViewModelFactory());
            FindFilterDialogFragment_MembersInjector.injectSearchViewModelFactory(instance, searchViewModelFactory());
            return instance;
        }
    }

    private static final class EntityTrackingIntentServiceSubcomponentImpl implements FindAndroidComponentsModule_EntityTrackingIntentService$EntityTrackingIntentServiceSubcomponent {
        private final ApplicationComponentImpl applicationComponentImpl;
        private final EntityTrackingIntentServiceSubcomponentImpl entityTrackingIntentServiceSubcomponentImpl;
        private final FindModule findModule;

        private EntityTrackingIntentServiceSubcomponentImpl(ApplicationComponentImpl applicationComponentImpl, FindModule findModuleParam, EntityTrackingIntentService arg0Param) {
            this.entityTrackingIntentServiceSubcomponentImpl = this;
            this.applicationComponentImpl = applicationComponentImpl;
            this.findModule = findModuleParam;
        }

        private AppDatabase appDatabase() {
            return FindModule_ProvideAppDatabaseFactory.provideAppDatabase(this.findModule, this.applicationComponentImpl.application);
        }

        private SearchEntityDao searchEntityDao() {
            return FindModule_ProvideSearchEntityDaoFactory.provideSearchEntityDao(this.findModule, appDatabase());
        }

        private SearchFeaturesProvider searchFeaturesProvider() {
            return FindModule_ProvideSearchFeaturesProviderFactory.provideSearchFeaturesProvider(this.findModule, this.applicationComponentImpl.application);
        }

        private UIConfigurationService uIConfigurationService() {
            return FindModule_ProvideUIConfigurationServiceFactory.provideUIConfigurationService(this.findModule, this.applicationComponentImpl.application, searchFeaturesProvider(), FindModule_ProvideFindFeatureConfigurationProviderFactory.provideFindFeatureConfigurationProvider(this.findModule));
        }

        private EntityTrackingService entityTrackingService() {
            return FindModule_ProvideEntityTrackingServiceFactory.provideEntityTrackingService(this.findModule, searchEntityDao(), uIConfigurationService(), searchFeaturesProvider());
        }

        @Override // dagger.android.AndroidInjector
        public void inject(EntityTrackingIntentService arg0) {
            injectEntityTrackingIntentService(arg0);
        }

        private EntityTrackingIntentService injectEntityTrackingIntentService(EntityTrackingIntentService instance) {
            EntityTrackingIntentService_MembersInjector.injectEntityTrackingService(instance, entityTrackingService());
            return instance;
        }
    }

    private static final class SearchBrushFragmentSubcomponentImpl implements FindAndroidComponentsModule_SearchBrushFragment$SearchBrushFragmentSubcomponent {
        private final ApplicationComponentImpl applicationComponentImpl;
        private final FindModule findModule;
        private final SearchBrushFragmentSubcomponentImpl searchBrushFragmentSubcomponentImpl;

        private SearchBrushFragmentSubcomponentImpl(ApplicationComponentImpl applicationComponentImpl, FindModule findModuleParam, SearchBrushFragment arg0Param) {
            this.searchBrushFragmentSubcomponentImpl = this;
            this.applicationComponentImpl = applicationComponentImpl;
            this.findModule = findModuleParam;
        }

        private BrushPageGridConverter brushPageGridConverter() {
            return FindModule_ProviderBrushPageGridConverterFactory.providerBrushPageGridConverter(this.findModule, this.applicationComponentImpl.application);
        }

        private AppDatabase appDatabase() {
            return FindModule_ProvideAppDatabaseFactory.provideAppDatabase(this.findModule, this.applicationComponentImpl.application);
        }

        private SearchEntityDao searchEntityDao() {
            return FindModule_ProvideSearchEntityDaoFactory.provideSearchEntityDao(this.findModule, appDatabase());
        }

        private SearchSuggestionServiceConfigurationProvider searchSuggestionServiceConfigurationProvider() {
            return FindModule_ProvideSearchSuggestionConfigurationProviderFactory.provideSearchSuggestionConfigurationProvider(this.findModule, this.applicationComponentImpl.application);
        }

        private InstantSearchSuggestionServiceConfigurationProvider instantSearchSuggestionServiceConfigurationProvider() {
            return FindModule_ProvideInstantSearchSuggestionConfigurationProviderFactory.provideInstantSearchSuggestionConfigurationProvider(this.findModule, this.applicationComponentImpl.application);
        }

        private SearchFeaturesProvider searchFeaturesProvider() {
            return FindModule_ProvideSearchFeaturesProviderFactory.provideSearchFeaturesProvider(this.findModule, this.applicationComponentImpl.application);
        }

        private UIConfigurationService uIConfigurationService() {
            return FindModule_ProvideUIConfigurationServiceFactory.provideUIConfigurationService(this.findModule, this.applicationComponentImpl.application, searchFeaturesProvider(), FindModule_ProvideFindFeatureConfigurationProviderFactory.provideFindFeatureConfigurationProvider(this.findModule));
        }

        private SearchSuggestionApi searchSuggestionApi() {
            return FindModule_ProvideSearchSuggestionApiFactory.provideSearchSuggestionApi(this.findModule, searchSuggestionServiceConfigurationProvider(), instantSearchSuggestionServiceConfigurationProvider(), uIConfigurationService(), searchFeaturesProvider());
        }

        private SearchSuggestionService searchSuggestionService() {
            return FindModule_ProvideSearchSuggestionServiceFactory.provideSearchSuggestionService(this.findModule, searchEntityDao(), searchSuggestionApi(), uIConfigurationService(), searchFeaturesProvider());
        }

        private SearchQueryDao searchQueryDao() {
            return FindModule_ProvideSearchQueryDaoFactory.provideSearchQueryDao(this.findModule, appDatabase());
        }

        private UserSettingsService userSettingsService() {
            return FindModule_ProvideUserSettingsServiceFactory.provideUserSettingsService(this.findModule, this.applicationComponentImpl.application, searchFeaturesProvider());
        }

        private SearchHistoryService searchHistoryService() {
            return FindModule_ProvideSearchHistoryServiceFactory.provideSearchHistoryService(this.findModule, searchQueryDao(), searchEntityDao(), uIConfigurationService(), userSettingsService(), searchFeaturesProvider());
        }

        private BrushV2Converter<SearchHistoryItem> brushV2ConverterOfSearchHistoryItem() {
            return FindModule_ProvideSearchHistoryConverterFactory.provideSearchHistoryConverter(this.findModule, this.applicationComponentImpl.application, FindModule_ProvideCacheImageProviderFactory.provideCacheImageProvider(this.findModule), searchFeaturesProvider());
        }

        private BrushV2Converter<SearchSuggestion> brushV2ConverterOfSearchSuggestion() {
            return FindModule_ProvideSearchSuggestionConverterFactory.provideSearchSuggestionConverter(this.findModule, this.applicationComponentImpl.application, FindModule_ProvideCacheImageProviderFactory.provideCacheImageProvider(this.findModule), searchFeaturesProvider(), uIConfigurationService());
        }

        private SearchMetricsService searchMetricsService() {
            FindModule findModule = this.findModule;
            return FindModule_ProvidesSearchMetricsServiceFactory.providesSearchMetricsService(findModule, FindModule_ProvidesSearchMetricsApiFactory.providesSearchMetricsApi(findModule), searchFeaturesProvider());
        }

        private BrushV2Converter<SearchResponse> namedBrushV2ConverterOfSearchResponse() {
            return FindModule_ProvideSearchResultConverterFactory.provideSearchResultConverter(this.findModule, this.applicationComponentImpl.application, FindModule_ProvideCacheImageProviderFactory.provideCacheImageProvider(this.findModule), searchFeaturesProvider(), searchMetricsService());
        }

        private BrushV2Converter<SeeMoreResponse> brushV2ConverterOfSeeMoreResponse() {
            return FindModule_ProvideSeeMoreResultConverterFactory.provideSeeMoreResultConverter(this.findModule, this.applicationComponentImpl.application, FindModule_ProvideCacheImageProviderFactory.provideCacheImageProvider(this.findModule), searchFeaturesProvider());
        }

        private ConverterService namedConverterService() {
            return FindModule_ProvideConverterServiceFactory.provideConverterService(this.findModule, brushV2ConverterOfSearchHistoryItem(), brushV2ConverterOfSearchSuggestion(), namedBrushV2ConverterOfSearchResponse(), brushV2ConverterOfSeeMoreResponse());
        }

        private SearchServiceConfigurationProvider searchServiceConfigurationProvider() {
            return FindModule_ProvideSearchConfigurationProviderFactory.provideSearchConfigurationProvider(this.findModule, this.applicationComponentImpl.application);
        }

        private CirrusDatabaseProvider cirrusDatabaseProvider() {
            return FindModule_ProvidesCirrusDatabaseProviderFactory.providesCirrusDatabaseProvider(this.findModule, this.applicationComponentImpl.application);
        }

        private LibrarySearchDao librarySearchDao() {
            return FindModule_ProvidesLibrarySearchDaoFactory.providesLibrarySearchDao(this.findModule, cirrusDatabaseProvider(), FindModule_ProvidesSearchEntityFactoryFactory.providesSearchEntityFactory(this.findModule), searchFeaturesProvider());
        }

        private SearchApi<GenericSearchResponse> namedSearchApiOfGenericSearchResponse() {
            return FindModule_ProvidesRemoteSearchApiFactory.providesRemoteSearchApi(this.findModule, searchServiceConfigurationProvider(), searchFeaturesProvider(), FindModule_ProvidesSearchEntityFactoryFactory.providesSearchEntityFactory(this.findModule), uIConfigurationService(), librarySearchDao());
        }

        private SearchApi<GenericSearchResponse> namedSearchApiOfGenericSearchResponse2() {
            return FindModule_ProvidesLibrarySearchApiFactory.providesLibrarySearchApi(this.findModule, librarySearchDao(), searchFeaturesProvider());
        }

        private SearchService searchService() {
            return FindModule_ProvidesSearchServiceFactory.providesSearchService(this.findModule, namedSearchApiOfGenericSearchResponse(), namedSearchApiOfGenericSearchResponse2(), uIConfigurationService(), searchFeaturesProvider());
        }

        private LibraryStateService libraryStateService() {
            return FindModule_ProvideLibraryStateServiceFactory.provideLibraryStateService(this.findModule, librarySearchDao(), searchFeaturesProvider());
        }

        private SearchViewModelFactory searchViewModelFactory() {
            return FindModule_ProvideSearchViewModelFactoryFactory.provideSearchViewModelFactory(this.findModule, brushPageGridConverter(), searchSuggestionService(), searchHistoryService(), namedConverterService(), searchService(), searchMetricsService(), searchFeaturesProvider(), uIConfigurationService(), libraryStateService());
        }

        @Override // dagger.android.AndroidInjector
        public void inject(SearchBrushFragment arg0) {
            injectSearchBrushFragment(arg0);
        }

        private SearchBrushFragment injectSearchBrushFragment(SearchBrushFragment instance) {
            SearchBrushFragment_MembersInjector.injectSearchViewModelFactory(instance, searchViewModelFactory());
            return instance;
        }
    }

    private static final class CarModeSearchBrushFragmentSubcomponentImpl implements FindAndroidComponentsModule_CarModeSearchBrushFragment$CarModeSearchBrushFragmentSubcomponent {
        private final ApplicationComponentImpl applicationComponentImpl;
        private final CarModeSearchBrushFragmentSubcomponentImpl carModeSearchBrushFragmentSubcomponentImpl;
        private final FindModule findModule;

        private CarModeSearchBrushFragmentSubcomponentImpl(ApplicationComponentImpl applicationComponentImpl, FindModule findModuleParam, CarModeSearchBrushFragment arg0Param) {
            this.carModeSearchBrushFragmentSubcomponentImpl = this;
            this.applicationComponentImpl = applicationComponentImpl;
            this.findModule = findModuleParam;
        }

        private BrushPageGridConverter brushPageGridConverter() {
            return FindModule_ProviderBrushPageGridConverterFactory.providerBrushPageGridConverter(this.findModule, this.applicationComponentImpl.application);
        }

        private SearchFeaturesProvider searchFeaturesProvider() {
            return FindModule_ProvideSearchFeaturesProviderFactory.provideSearchFeaturesProvider(this.findModule, this.applicationComponentImpl.application);
        }

        private BrushV2Converter<SearchResponse> namedBrushV2ConverterOfSearchResponse() {
            return FindModule_ProvideCarModeSearchResultConverterFactory.provideCarModeSearchResultConverter(this.findModule, this.applicationComponentImpl.application, FindModule_ProvideCacheImageProviderFactory.provideCacheImageProvider(this.findModule), searchFeaturesProvider());
        }

        private ConverterService namedConverterService() {
            return FindModule_ProvideCarModeConverterServiceFactory.provideCarModeConverterService(this.findModule, namedBrushV2ConverterOfSearchResponse());
        }

        private SearchServiceConfigurationProvider searchServiceConfigurationProvider() {
            return FindModule_ProvideSearchConfigurationProviderFactory.provideSearchConfigurationProvider(this.findModule, this.applicationComponentImpl.application);
        }

        private UIConfigurationService uIConfigurationService() {
            return FindModule_ProvideUIConfigurationServiceFactory.provideUIConfigurationService(this.findModule, this.applicationComponentImpl.application, searchFeaturesProvider(), FindModule_ProvideFindFeatureConfigurationProviderFactory.provideFindFeatureConfigurationProvider(this.findModule));
        }

        private CirrusDatabaseProvider cirrusDatabaseProvider() {
            return FindModule_ProvidesCirrusDatabaseProviderFactory.providesCirrusDatabaseProvider(this.findModule, this.applicationComponentImpl.application);
        }

        private LibrarySearchDao librarySearchDao() {
            return FindModule_ProvidesLibrarySearchDaoFactory.providesLibrarySearchDao(this.findModule, cirrusDatabaseProvider(), FindModule_ProvidesSearchEntityFactoryFactory.providesSearchEntityFactory(this.findModule), searchFeaturesProvider());
        }

        private SearchApi<GenericSearchResponse> namedSearchApiOfGenericSearchResponse() {
            return FindModule_ProvidesRemoteSearchApiFactory.providesRemoteSearchApi(this.findModule, searchServiceConfigurationProvider(), searchFeaturesProvider(), FindModule_ProvidesSearchEntityFactoryFactory.providesSearchEntityFactory(this.findModule), uIConfigurationService(), librarySearchDao());
        }

        private SearchApi<GenericSearchResponse> namedSearchApiOfGenericSearchResponse2() {
            return FindModule_ProvidesLibrarySearchApiFactory.providesLibrarySearchApi(this.findModule, librarySearchDao(), searchFeaturesProvider());
        }

        private SearchService searchService() {
            return FindModule_ProvidesSearchServiceFactory.providesSearchService(this.findModule, namedSearchApiOfGenericSearchResponse(), namedSearchApiOfGenericSearchResponse2(), uIConfigurationService(), searchFeaturesProvider());
        }

        private SearchMetricsService searchMetricsService() {
            FindModule findModule = this.findModule;
            return FindModule_ProvidesSearchMetricsServiceFactory.providesSearchMetricsService(findModule, FindModule_ProvidesSearchMetricsApiFactory.providesSearchMetricsApi(findModule), searchFeaturesProvider());
        }

        private AppDatabase appDatabase() {
            return FindModule_ProvideAppDatabaseFactory.provideAppDatabase(this.findModule, this.applicationComponentImpl.application);
        }

        private SearchQueryDao searchQueryDao() {
            return FindModule_ProvideSearchQueryDaoFactory.provideSearchQueryDao(this.findModule, appDatabase());
        }

        private SearchEntityDao searchEntityDao() {
            return FindModule_ProvideSearchEntityDaoFactory.provideSearchEntityDao(this.findModule, appDatabase());
        }

        private UserSettingsService userSettingsService() {
            return FindModule_ProvideUserSettingsServiceFactory.provideUserSettingsService(this.findModule, this.applicationComponentImpl.application, searchFeaturesProvider());
        }

        private SearchHistoryService searchHistoryService() {
            return FindModule_ProvideSearchHistoryServiceFactory.provideSearchHistoryService(this.findModule, searchQueryDao(), searchEntityDao(), uIConfigurationService(), userSettingsService(), searchFeaturesProvider());
        }

        private CarModeSearchViewModelFactory carModeSearchViewModelFactory() {
            return FindModule_ProvidesCarModeSearchViewModelFactoryFactory.providesCarModeSearchViewModelFactory(this.findModule, brushPageGridConverter(), namedConverterService(), FindModule_ProvideCarModeTransformerServiceFactory.provideCarModeTransformerService(this.findModule), searchService(), searchMetricsService(), searchFeaturesProvider(), uIConfigurationService(), searchHistoryService());
        }

        @Override // dagger.android.AndroidInjector
        public void inject(CarModeSearchBrushFragment arg0) {
            injectCarModeSearchBrushFragment(arg0);
        }

        private CarModeSearchBrushFragment injectCarModeSearchBrushFragment(CarModeSearchBrushFragment instance) {
            CarModeSearchBrushFragment_MembersInjector.injectViewModelFactory(instance, carModeSearchViewModelFactory());
            return instance;
        }
    }

    private static final class FindLandingFragmentSubcomponentImpl implements FindAndroidComponentsModule_FindLandingFragment$FindLandingFragmentSubcomponent {
        private final ApplicationComponentImpl applicationComponentImpl;
        private final FindLandingFragmentSubcomponentImpl findLandingFragmentSubcomponentImpl;
        private final FindModule findModule;
        private Provider<CapabilitiesInfo> provideCapabilitiesInfoProvider;
        private Provider<ContextMenuProvider> provideContextMenuProvider;
        private Provider<DownloadProvider> provideDownloadProvider;
        private Provider<ExternalActionProvider> provideExternalActionProvider;
        private Provider<LibraryProvider> provideLibraryProvider;
        private Provider<NavigationProvider> provideNavigationProvider;
        private Provider<NetworkInterceptor> provideNetworkInterceptorProvider;
        private Provider<NotificationProvider> provideNotificationProvider;
        private Provider<PlaybackProvider> providePlaybackProvider;
        private Provider<SharingProvider> provideSharingProvider;
        private Provider<SkillLatencyTracker> provideSkillLatencyTrackerProvider;
        private Provider<SkyFireConfigurationProvider> provideSkyFireConfigurationProvider;
        private Provider<SkyFireProvider> provideSkyFireProvider;
        private Provider<StyleSheetProvider> provideStyleSheetProvider;
        private Provider<UpsellProvider> provideUpsellProvider;
        private Provider<UserSubscriptionProvider> provideUserSubscriptionProvider;

        private FindLandingFragmentSubcomponentImpl(ApplicationComponentImpl applicationComponentImpl, FindModule findModuleParam, FindLandingFragment arg0Param) {
            this.findLandingFragmentSubcomponentImpl = this;
            this.applicationComponentImpl = applicationComponentImpl;
            this.findModule = findModuleParam;
            initialize(findModuleParam, arg0Param);
        }

        private ElementBindingProvider elementBindingProvider() {
            return FindModule_ProvideElementBindingProviderFactory.provideElementBindingProvider(this.findModule, this.provideUserSubscriptionProvider.get(), this.provideStyleSheetProvider.get(), this.provideDownloadProvider.get());
        }

        private void initialize(final FindModule findModuleParam, final FindLandingFragment arg0Param) {
            this.provideStyleSheetProvider = SingleCheck.provider(FindModule_ProvideStyleSheetProviderFactory.create(findModuleParam));
            this.provideUserSubscriptionProvider = SingleCheck.provider(FindModule_ProvideUserSubscriptionProviderFactory.create(findModuleParam));
            this.provideDownloadProvider = SingleCheck.provider(FindModule_ProvideDownloadProviderFactory.create(findModuleParam));
            this.provideLibraryProvider = SingleCheck.provider(FindModule_ProvideLibraryProviderFactory.create(findModuleParam));
            this.provideNavigationProvider = SingleCheck.provider(FindModule_ProvideNavigationProviderFactory.create(findModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideNotificationProvider = SingleCheck.provider(FindModule_ProvideNotificationProviderFactory.create(findModuleParam, this.applicationComponentImpl.applicationProvider));
            this.providePlaybackProvider = SingleCheck.provider(FindModule_ProvidePlaybackProviderFactory.create(findModuleParam, this.applicationComponentImpl.applicationProvider));
            Provider<SharingProvider> provider = SingleCheck.provider(FindModule_ProvideSharingProviderFactory.create(findModuleParam));
            this.provideSharingProvider = provider;
            this.provideExternalActionProvider = SingleCheck.provider(FindModule_ProvideExternalActionProviderFactory.create(findModuleParam, this.provideLibraryProvider, this.provideNavigationProvider, this.provideNotificationProvider, this.providePlaybackProvider, provider, this.provideDownloadProvider));
            this.provideCapabilitiesInfoProvider = SingleCheck.provider(FindModule_ProvideCapabilitiesInfoFactory.create(findModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideNetworkInterceptorProvider = SingleCheck.provider(FindModule_ProvideNetworkInterceptorFactory.create(findModuleParam));
            this.provideSkillLatencyTrackerProvider = SingleCheck.provider(FindModule_ProvideSkillLatencyTrackerFactory.create(findModuleParam));
            this.provideSkyFireConfigurationProvider = SingleCheck.provider(FindModule_ProvideSkyFireConfigurationProviderFactory.create(findModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideSkyFireProvider = SingleCheck.provider(FindModule_ProvideSkyFireProviderFactory.create(findModuleParam, this.applicationComponentImpl.applicationProvider, this.applicationComponentImpl.provideAuthenticationProvider, this.applicationComponentImpl.provideCustomerMetadataProvider, this.applicationComponentImpl.provideDeviceInfoProvider, this.applicationComponentImpl.provideBuildInfoProvider, this.providePlaybackProvider, this.provideCapabilitiesInfoProvider, this.provideNetworkInterceptorProvider, this.provideSkillLatencyTrackerProvider, this.provideSkyFireConfigurationProvider));
            this.provideContextMenuProvider = SingleCheck.provider(FindModule_ProvideContextMenuProviderFactory.create(findModuleParam));
            this.provideUpsellProvider = SingleCheck.provider(FindModule_ProvideUpsellProviderFactory.create(findModuleParam));
        }

        @Override // dagger.android.AndroidInjector
        public void inject(FindLandingFragment arg0) {
            injectFindLandingFragment(arg0);
        }

        private FindLandingFragment injectFindLandingFragment(FindLandingFragment instance) {
            TemplateFragment_MembersInjector.injectStyleSheetProvider(instance, this.provideStyleSheetProvider.get());
            TemplateFragment_MembersInjector.injectElementBindingProvider(instance, elementBindingProvider());
            TemplateFragment_MembersInjector.injectExternalActionProvider(instance, this.provideExternalActionProvider.get());
            TemplateFragment_MembersInjector.injectSkyFireProvider(instance, this.provideSkyFireProvider.get());
            TemplateFragment_MembersInjector.injectSkyFireConfigurationProvider(instance, this.provideSkyFireConfigurationProvider.get());
            TemplateFragment_MembersInjector.injectContextMenuProvider(instance, this.provideContextMenuProvider.get());
            TemplateFragment_MembersInjector.injectPlaybackProvider(instance, this.providePlaybackProvider.get());
            TemplateFragment_MembersInjector.injectUpsellProvider(instance, this.provideUpsellProvider.get());
            TemplateFragment_MembersInjector.injectNavigationProvider(instance, this.provideNavigationProvider.get());
            TemplateFragment_MembersInjector.injectSkillLatencyTracker(instance, this.provideSkillLatencyTrackerProvider.get());
            return instance;
        }
    }

    private static final class FindSkyfireFragmentSubcomponentImpl implements FindAndroidComponentsModule_FindSkyfireFragment$FindSkyfireFragmentSubcomponent {
        private final ApplicationComponentImpl applicationComponentImpl;
        private final FindModule findModule;
        private final FindSkyfireFragmentSubcomponentImpl findSkyfireFragmentSubcomponentImpl;
        private Provider<CapabilitiesInfo> provideCapabilitiesInfoProvider;
        private Provider<ChromeProvider> provideChromeProvider;
        private Provider<ContextMenuProvider> provideContextMenuProvider;
        private Provider<DownloadProvider> provideDownloadProvider;
        private Provider<ExternalActionProvider> provideExternalActionProvider;
        private Provider<LibraryProvider> provideLibraryProvider;
        private Provider<NavigationProvider> provideNavigationProvider;
        private Provider<NetworkInterceptor> provideNetworkInterceptorProvider;
        private Provider<NotificationProvider> provideNotificationProvider;
        private Provider<PlaybackProvider> providePlaybackProvider;
        private Provider<SharingProvider> provideSharingProvider;
        private Provider<SkillLatencyTracker> provideSkillLatencyTrackerProvider;
        private Provider<SkyFireConfigurationProvider> provideSkyFireConfigurationProvider;
        private Provider<SkyFireProvider> provideSkyFireProvider;
        private Provider<StyleSheetProvider> provideStyleSheetProvider;
        private Provider<UpsellProvider> provideUpsellProvider;
        private Provider<UserSubscriptionProvider> provideUserSubscriptionProvider;

        private FindSkyfireFragmentSubcomponentImpl(ApplicationComponentImpl applicationComponentImpl, FindModule findModuleParam, FindSkyfireFragment arg0Param) {
            this.findSkyfireFragmentSubcomponentImpl = this;
            this.applicationComponentImpl = applicationComponentImpl;
            this.findModule = findModuleParam;
            initialize(findModuleParam, arg0Param);
        }

        private ElementBindingProvider elementBindingProvider() {
            return FindModule_ProvideElementBindingProviderFactory.provideElementBindingProvider(this.findModule, this.provideUserSubscriptionProvider.get(), this.provideStyleSheetProvider.get(), this.provideDownloadProvider.get());
        }

        private SearchFeaturesProvider searchFeaturesProvider() {
            return FindModule_ProvideSearchFeaturesProviderFactory.provideSearchFeaturesProvider(this.findModule, this.applicationComponentImpl.application);
        }

        private void initialize(final FindModule findModuleParam, final FindSkyfireFragment arg0Param) {
            this.provideStyleSheetProvider = SingleCheck.provider(FindModule_ProvideStyleSheetProviderFactory.create(findModuleParam));
            this.provideUserSubscriptionProvider = SingleCheck.provider(FindModule_ProvideUserSubscriptionProviderFactory.create(findModuleParam));
            this.provideDownloadProvider = SingleCheck.provider(FindModule_ProvideDownloadProviderFactory.create(findModuleParam));
            this.provideLibraryProvider = SingleCheck.provider(FindModule_ProvideLibraryProviderFactory.create(findModuleParam));
            this.provideNavigationProvider = SingleCheck.provider(FindModule_ProvideNavigationProviderFactory.create(findModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideNotificationProvider = SingleCheck.provider(FindModule_ProvideNotificationProviderFactory.create(findModuleParam, this.applicationComponentImpl.applicationProvider));
            this.providePlaybackProvider = SingleCheck.provider(FindModule_ProvidePlaybackProviderFactory.create(findModuleParam, this.applicationComponentImpl.applicationProvider));
            Provider<SharingProvider> provider = SingleCheck.provider(FindModule_ProvideSharingProviderFactory.create(findModuleParam));
            this.provideSharingProvider = provider;
            this.provideExternalActionProvider = SingleCheck.provider(FindModule_ProvideExternalActionProviderFactory.create(findModuleParam, this.provideLibraryProvider, this.provideNavigationProvider, this.provideNotificationProvider, this.providePlaybackProvider, provider, this.provideDownloadProvider));
            this.provideCapabilitiesInfoProvider = SingleCheck.provider(FindModule_ProvideCapabilitiesInfoFactory.create(findModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideNetworkInterceptorProvider = SingleCheck.provider(FindModule_ProvideNetworkInterceptorFactory.create(findModuleParam));
            this.provideSkillLatencyTrackerProvider = SingleCheck.provider(FindModule_ProvideSkillLatencyTrackerFactory.create(findModuleParam));
            this.provideSkyFireConfigurationProvider = SingleCheck.provider(FindModule_ProvideSkyFireConfigurationProviderFactory.create(findModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideSkyFireProvider = SingleCheck.provider(FindModule_ProvideSkyFireProviderFactory.create(findModuleParam, this.applicationComponentImpl.applicationProvider, this.applicationComponentImpl.provideAuthenticationProvider, this.applicationComponentImpl.provideCustomerMetadataProvider, this.applicationComponentImpl.provideDeviceInfoProvider, this.applicationComponentImpl.provideBuildInfoProvider, this.providePlaybackProvider, this.provideCapabilitiesInfoProvider, this.provideNetworkInterceptorProvider, this.provideSkillLatencyTrackerProvider, this.provideSkyFireConfigurationProvider));
            this.provideContextMenuProvider = SingleCheck.provider(FindModule_ProvideContextMenuProviderFactory.create(findModuleParam));
            this.provideUpsellProvider = SingleCheck.provider(FindModule_ProvideUpsellProviderFactory.create(findModuleParam));
            this.provideChromeProvider = SingleCheck.provider(FindModule_ProvideChromeProviderFactory.create(findModuleParam));
        }

        @Override // dagger.android.AndroidInjector
        public void inject(FindSkyfireFragment arg0) {
            injectFindSkyfireFragment(arg0);
        }

        private FindSkyfireFragment injectFindSkyfireFragment(FindSkyfireFragment instance) {
            TemplateFragment_MembersInjector.injectStyleSheetProvider(instance, this.provideStyleSheetProvider.get());
            TemplateFragment_MembersInjector.injectElementBindingProvider(instance, elementBindingProvider());
            TemplateFragment_MembersInjector.injectExternalActionProvider(instance, this.provideExternalActionProvider.get());
            TemplateFragment_MembersInjector.injectSkyFireProvider(instance, this.provideSkyFireProvider.get());
            TemplateFragment_MembersInjector.injectSkyFireConfigurationProvider(instance, this.provideSkyFireConfigurationProvider.get());
            TemplateFragment_MembersInjector.injectContextMenuProvider(instance, this.provideContextMenuProvider.get());
            TemplateFragment_MembersInjector.injectPlaybackProvider(instance, this.providePlaybackProvider.get());
            TemplateFragment_MembersInjector.injectUpsellProvider(instance, this.provideUpsellProvider.get());
            TemplateFragment_MembersInjector.injectNavigationProvider(instance, this.provideNavigationProvider.get());
            TemplateFragment_MembersInjector.injectSkillLatencyTracker(instance, this.provideSkillLatencyTrackerProvider.get());
            MusicHomeFragment_MembersInjector.injectChromeProvider(instance, this.provideChromeProvider.get());
            FindSkyfireFragment_MembersInjector.injectSearchFeaturesProvider(instance, searchFeaturesProvider());
            return instance;
        }
    }

    private static final class SearchGalleryFragmentSubcomponentImpl implements FindAndroidComponentsModule_SearchGalleryFragment$SearchGalleryFragmentSubcomponent {
        private final ApplicationComponentImpl applicationComponentImpl;
        private final FindModule findModule;
        private Provider<CapabilitiesInfo> provideCapabilitiesInfoProvider;
        private Provider<ContextMenuProvider> provideContextMenuProvider;
        private Provider<DownloadProvider> provideDownloadProvider;
        private Provider<ExternalActionProvider> provideExternalActionProvider;
        private Provider<LibraryProvider> provideLibraryProvider;
        private Provider<NavigationProvider> provideNavigationProvider;
        private Provider<NetworkInterceptor> provideNetworkInterceptorProvider;
        private Provider<NotificationProvider> provideNotificationProvider;
        private Provider<PlaybackProvider> providePlaybackProvider;
        private Provider<SharingProvider> provideSharingProvider;
        private Provider<SkillLatencyTracker> provideSkillLatencyTrackerProvider;
        private Provider<SkyFireConfigurationProvider> provideSkyFireConfigurationProvider;
        private Provider<SkyFireProvider> provideSkyFireProvider;
        private Provider<StyleSheetProvider> provideStyleSheetProvider;
        private Provider<UpsellProvider> provideUpsellProvider;
        private Provider<UserSubscriptionProvider> provideUserSubscriptionProvider;
        private final SearchGalleryFragmentSubcomponentImpl searchGalleryFragmentSubcomponentImpl;

        private SearchGalleryFragmentSubcomponentImpl(ApplicationComponentImpl applicationComponentImpl, FindModule findModuleParam, SearchGalleryFragment arg0Param) {
            this.searchGalleryFragmentSubcomponentImpl = this;
            this.applicationComponentImpl = applicationComponentImpl;
            this.findModule = findModuleParam;
            initialize(findModuleParam, arg0Param);
        }

        private ElementBindingProvider elementBindingProvider() {
            return FindModule_ProvideElementBindingProviderFactory.provideElementBindingProvider(this.findModule, this.provideUserSubscriptionProvider.get(), this.provideStyleSheetProvider.get(), this.provideDownloadProvider.get());
        }

        private void initialize(final FindModule findModuleParam, final SearchGalleryFragment arg0Param) {
            this.provideStyleSheetProvider = SingleCheck.provider(FindModule_ProvideStyleSheetProviderFactory.create(findModuleParam));
            this.provideUserSubscriptionProvider = SingleCheck.provider(FindModule_ProvideUserSubscriptionProviderFactory.create(findModuleParam));
            this.provideDownloadProvider = SingleCheck.provider(FindModule_ProvideDownloadProviderFactory.create(findModuleParam));
            this.provideLibraryProvider = SingleCheck.provider(FindModule_ProvideLibraryProviderFactory.create(findModuleParam));
            this.provideNavigationProvider = SingleCheck.provider(FindModule_ProvideNavigationProviderFactory.create(findModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideNotificationProvider = SingleCheck.provider(FindModule_ProvideNotificationProviderFactory.create(findModuleParam, this.applicationComponentImpl.applicationProvider));
            this.providePlaybackProvider = SingleCheck.provider(FindModule_ProvidePlaybackProviderFactory.create(findModuleParam, this.applicationComponentImpl.applicationProvider));
            Provider<SharingProvider> provider = SingleCheck.provider(FindModule_ProvideSharingProviderFactory.create(findModuleParam));
            this.provideSharingProvider = provider;
            this.provideExternalActionProvider = SingleCheck.provider(FindModule_ProvideExternalActionProviderFactory.create(findModuleParam, this.provideLibraryProvider, this.provideNavigationProvider, this.provideNotificationProvider, this.providePlaybackProvider, provider, this.provideDownloadProvider));
            this.provideCapabilitiesInfoProvider = SingleCheck.provider(FindModule_ProvideCapabilitiesInfoFactory.create(findModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideNetworkInterceptorProvider = SingleCheck.provider(FindModule_ProvideNetworkInterceptorFactory.create(findModuleParam));
            this.provideSkillLatencyTrackerProvider = SingleCheck.provider(FindModule_ProvideSkillLatencyTrackerFactory.create(findModuleParam));
            this.provideSkyFireConfigurationProvider = SingleCheck.provider(FindModule_ProvideSkyFireConfigurationProviderFactory.create(findModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideSkyFireProvider = SingleCheck.provider(FindModule_ProvideSkyFireProviderFactory.create(findModuleParam, this.applicationComponentImpl.applicationProvider, this.applicationComponentImpl.provideAuthenticationProvider, this.applicationComponentImpl.provideCustomerMetadataProvider, this.applicationComponentImpl.provideDeviceInfoProvider, this.applicationComponentImpl.provideBuildInfoProvider, this.providePlaybackProvider, this.provideCapabilitiesInfoProvider, this.provideNetworkInterceptorProvider, this.provideSkillLatencyTrackerProvider, this.provideSkyFireConfigurationProvider));
            this.provideContextMenuProvider = SingleCheck.provider(FindModule_ProvideContextMenuProviderFactory.create(findModuleParam));
            this.provideUpsellProvider = SingleCheck.provider(FindModule_ProvideUpsellProviderFactory.create(findModuleParam));
        }

        @Override // dagger.android.AndroidInjector
        public void inject(SearchGalleryFragment arg0) {
            injectSearchGalleryFragment(arg0);
        }

        private SearchGalleryFragment injectSearchGalleryFragment(SearchGalleryFragment instance) {
            TemplateFragment_MembersInjector.injectStyleSheetProvider(instance, this.provideStyleSheetProvider.get());
            TemplateFragment_MembersInjector.injectElementBindingProvider(instance, elementBindingProvider());
            TemplateFragment_MembersInjector.injectExternalActionProvider(instance, this.provideExternalActionProvider.get());
            TemplateFragment_MembersInjector.injectSkyFireProvider(instance, this.provideSkyFireProvider.get());
            TemplateFragment_MembersInjector.injectSkyFireConfigurationProvider(instance, this.provideSkyFireConfigurationProvider.get());
            TemplateFragment_MembersInjector.injectContextMenuProvider(instance, this.provideContextMenuProvider.get());
            TemplateFragment_MembersInjector.injectPlaybackProvider(instance, this.providePlaybackProvider.get());
            TemplateFragment_MembersInjector.injectUpsellProvider(instance, this.provideUpsellProvider.get());
            TemplateFragment_MembersInjector.injectNavigationProvider(instance, this.provideNavigationProvider.get());
            TemplateFragment_MembersInjector.injectSkillLatencyTracker(instance, this.provideSkillLatencyTrackerProvider.get());
            return instance;
        }
    }

    private static final class SearchHostFragmentSubcomponentImpl implements FindAndroidComponentsModule_SearchHostFragment$SearchHostFragmentSubcomponent {
        private final ApplicationComponentImpl applicationComponentImpl;
        private final FindModule findModule;
        private final SearchHostFragmentSubcomponentImpl searchHostFragmentSubcomponentImpl;

        private SearchHostFragmentSubcomponentImpl(ApplicationComponentImpl applicationComponentImpl, FindModule findModuleParam, SearchHostFragment arg0Param) {
            this.searchHostFragmentSubcomponentImpl = this;
            this.applicationComponentImpl = applicationComponentImpl;
            this.findModule = findModuleParam;
        }

        private SearchFeaturesProvider searchFeaturesProvider() {
            return FindModule_ProvideSearchFeaturesProviderFactory.provideSearchFeaturesProvider(this.findModule, this.applicationComponentImpl.application);
        }

        private SearchMetricsService searchMetricsService() {
            FindModule findModule = this.findModule;
            return FindModule_ProvidesSearchMetricsServiceFactory.providesSearchMetricsService(findModule, FindModule_ProvidesSearchMetricsApiFactory.providesSearchMetricsApi(findModule), searchFeaturesProvider());
        }

        private UIConfigurationService uIConfigurationService() {
            return FindModule_ProvideUIConfigurationServiceFactory.provideUIConfigurationService(this.findModule, this.applicationComponentImpl.application, searchFeaturesProvider(), FindModule_ProvideFindFeatureConfigurationProviderFactory.provideFindFeatureConfigurationProvider(this.findModule));
        }

        private SearchHostViewModelFactory searchHostViewModelFactory() {
            return FindModule_ProvideSearchHostViewModelFactoryFactory.provideSearchHostViewModelFactory(this.findModule, searchMetricsService(), searchFeaturesProvider(), uIConfigurationService());
        }

        @Override // dagger.android.AndroidInjector
        public void inject(SearchHostFragment arg0) {
            injectSearchHostFragment(arg0);
        }

        private SearchHostFragment injectSearchHostFragment(SearchHostFragment instance) {
            SearchHostFragment_MembersInjector.injectSearchViewModelFactory(instance, searchHostViewModelFactory());
            return instance;
        }
    }

    private static final class ContactImportWebViewFragmentSubcomponentImpl implements ConnectAndroidComponentsModule_ContactImportWebViewFragment$ContactImportWebViewFragmentSubcomponent {
        private final ApplicationComponentImpl applicationComponentImpl;
        private final ContactImportWebViewFragmentSubcomponentImpl contactImportWebViewFragmentSubcomponentImpl;
        private Provider<ContactImportProvider> provideContactImportAppInterfaceProvider;

        private ContactImportWebViewFragmentSubcomponentImpl(ApplicationComponentImpl applicationComponentImpl, ConnectModule connectModuleParam, ContactImportWebViewFragment arg0Param) {
            this.contactImportWebViewFragmentSubcomponentImpl = this;
            this.applicationComponentImpl = applicationComponentImpl;
            initialize(connectModuleParam, arg0Param);
        }

        private void initialize(final ConnectModule connectModuleParam, final ContactImportWebViewFragment arg0Param) {
            this.provideContactImportAppInterfaceProvider = SingleCheck.provider(ConnectModule_ProvideContactImportAppInterfaceFactory.create(connectModuleParam, this.applicationComponentImpl.applicationProvider));
        }

        @Override // dagger.android.AndroidInjector
        public void inject(ContactImportWebViewFragment arg0) {
            injectContactImportWebViewFragment(arg0);
        }

        private ContactImportWebViewFragment injectContactImportWebViewFragment(ContactImportWebViewFragment instance) {
            ContactImportWebViewFragment_MembersInjector.injectAuthenticationProvider(instance, PlatformProvidersModule_ProvideAuthenticationProviderFactory.provideAuthenticationProvider(this.applicationComponentImpl.platformProvidersModule));
            ContactImportWebViewFragment_MembersInjector.injectDeviceInfoProvider(instance, PlatformProvidersModule_ProvideDeviceInfoProviderFactory.provideDeviceInfoProvider(this.applicationComponentImpl.platformProvidersModule));
            ContactImportWebViewFragment_MembersInjector.injectBuildInfoProvider(instance, PlatformProvidersModule_ProvideBuildInfoProviderFactory.provideBuildInfoProvider(this.applicationComponentImpl.platformProvidersModule));
            ContactImportWebViewFragment_MembersInjector.injectMetricsProvider(instance, PlatformProvidersModule_ProvideMetricsProviderFactory.provideMetricsProvider(this.applicationComponentImpl.platformProvidersModule));
            ContactImportWebViewFragment_MembersInjector.injectContactImportAppInterface(instance, this.provideContactImportAppInterfaceProvider.get());
            return instance;
        }
    }

    private static final class EditCustomerProfileFragmentSubcomponentImpl implements ConnectAndroidComponentsModule_EditCustomerProfileDialogFragment$EditCustomerProfileFragmentSubcomponent {
        private final ApplicationComponentImpl applicationComponentImpl;
        private final EditCustomerProfileFragmentSubcomponentImpl editCustomerProfileFragmentSubcomponentImpl;

        private EditCustomerProfileFragmentSubcomponentImpl(ApplicationComponentImpl applicationComponentImpl, EditCustomerProfileFragment arg0Param) {
            this.editCustomerProfileFragmentSubcomponentImpl = this;
            this.applicationComponentImpl = applicationComponentImpl;
        }

        @Override // dagger.android.AndroidInjector
        public void inject(EditCustomerProfileFragment arg0) {
            injectEditCustomerProfileFragment(arg0);
        }

        private EditCustomerProfileFragment injectEditCustomerProfileFragment(EditCustomerProfileFragment instance) {
            EditCustomerProfileFragment_MembersInjector.injectAuthenticationProvider(instance, PlatformProvidersModule_ProvideAuthenticationProviderFactory.provideAuthenticationProvider(this.applicationComponentImpl.platformProvidersModule));
            EditCustomerProfileFragment_MembersInjector.injectMetricsProvider(instance, PlatformProvidersModule_ProvideMetricsProviderFactory.provideMetricsProvider(this.applicationComponentImpl.platformProvidersModule));
            EditCustomerProfileFragment_MembersInjector.injectFeatureGateProvider(instance, PlatformProvidersModule_ProvideFeatureGateProviderFactory.provideFeatureGateProvider(this.applicationComponentImpl.platformProvidersModule));
            return instance;
        }
    }

    private static final class SharingFragmentSubcomponentImpl implements ShareAndroidComponentsModule_SharingFragment$SharingFragmentSubcomponent {
        private final ApplicationComponentImpl applicationComponentImpl;
        private Provider<com.amazon.music.share.menu.providers.EnvironmentProvider> provideEnvironmentProvider;
        private Provider<ShareHelper> provideShareHelperProvider;
        private Provider<SocialMetadataProvider> provideSocialMetadataProvider;
        private Provider<com.amazon.music.share.menu.providers.StyleSheetProvider> provideStyleSheetProvider;
        private final SharingFragmentSubcomponentImpl sharingFragmentSubcomponentImpl;

        private SharingFragmentSubcomponentImpl(ApplicationComponentImpl applicationComponentImpl, ShareModule shareModuleParam, SharingFragment arg0Param) {
            this.sharingFragmentSubcomponentImpl = this;
            this.applicationComponentImpl = applicationComponentImpl;
            initialize(shareModuleParam, arg0Param);
        }

        private void initialize(final ShareModule shareModuleParam, final SharingFragment arg0Param) {
            this.provideStyleSheetProvider = SingleCheck.provider(ShareModule_ProvideStyleSheetProviderFactory.create(shareModuleParam));
            this.provideSocialMetadataProvider = SingleCheck.provider(ShareModule_ProvideSocialMetadataProviderFactory.create(shareModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideEnvironmentProvider = SingleCheck.provider(ShareModule_ProvideEnvironmentProviderFactory.create(shareModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideShareHelperProvider = SingleCheck.provider(ShareModule_ProvideShareHelperFactory.create(shareModuleParam, this.applicationComponentImpl.applicationProvider));
        }

        @Override // dagger.android.AndroidInjector
        public void inject(SharingFragment arg0) {
            injectSharingFragment(arg0);
        }

        private SharingFragment injectSharingFragment(SharingFragment instance) {
            SharingFragment_MembersInjector.injectStyleSheetProvider(instance, this.provideStyleSheetProvider.get());
            SharingFragment_MembersInjector.injectSocialMetadataProvider(instance, this.provideSocialMetadataProvider.get());
            SharingFragment_MembersInjector.injectEnvironmentProvider(instance, this.provideEnvironmentProvider.get());
            SharingFragment_MembersInjector.injectImageUrlRepository(instance, PlatformProvidersModule_ProvideImageUrlRepositoryFactory.provideImageUrlRepository(this.applicationComponentImpl.platformProvidersModule));
            SharingFragment_MembersInjector.injectShareHelper(instance, this.provideShareHelperProvider.get());
            return instance;
        }
    }

    private static final class SharingFragmentV2SubcomponentImpl implements ShareAndroidComponentsModule_SharingFragmentV2$SharingFragmentV2Subcomponent {
        private final ApplicationComponentImpl applicationComponentImpl;
        private Provider<com.amazon.music.share.menu.providers.EnvironmentProvider> provideEnvironmentProvider;
        private Provider<ShareHelper> provideShareHelperProvider;
        private Provider<SocialMetadataProvider> provideSocialMetadataProvider;
        private Provider<com.amazon.music.share.menu.providers.StyleSheetProvider> provideStyleSheetProvider;
        private final SharingFragmentV2SubcomponentImpl sharingFragmentV2SubcomponentImpl;

        private SharingFragmentV2SubcomponentImpl(ApplicationComponentImpl applicationComponentImpl, ShareModule shareModuleParam, SharingFragmentV2 arg0Param) {
            this.sharingFragmentV2SubcomponentImpl = this;
            this.applicationComponentImpl = applicationComponentImpl;
            initialize(shareModuleParam, arg0Param);
        }

        private void initialize(final ShareModule shareModuleParam, final SharingFragmentV2 arg0Param) {
            this.provideStyleSheetProvider = SingleCheck.provider(ShareModule_ProvideStyleSheetProviderFactory.create(shareModuleParam));
            this.provideSocialMetadataProvider = SingleCheck.provider(ShareModule_ProvideSocialMetadataProviderFactory.create(shareModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideEnvironmentProvider = SingleCheck.provider(ShareModule_ProvideEnvironmentProviderFactory.create(shareModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideShareHelperProvider = SingleCheck.provider(ShareModule_ProvideShareHelperFactory.create(shareModuleParam, this.applicationComponentImpl.applicationProvider));
        }

        @Override // dagger.android.AndroidInjector
        public void inject(SharingFragmentV2 arg0) {
            injectSharingFragmentV2(arg0);
        }

        private SharingFragmentV2 injectSharingFragmentV2(SharingFragmentV2 instance) {
            SharingFragmentV2_MembersInjector.injectStyleSheetProvider(instance, this.provideStyleSheetProvider.get());
            SharingFragmentV2_MembersInjector.injectSocialMetadataProvider(instance, this.provideSocialMetadataProvider.get());
            SharingFragmentV2_MembersInjector.injectEnvironmentProvider(instance, this.provideEnvironmentProvider.get());
            SharingFragmentV2_MembersInjector.injectImageUrlRepository(instance, PlatformProvidersModule_ProvideImageUrlRepositoryFactory.provideImageUrlRepository(this.applicationComponentImpl.platformProvidersModule));
            SharingFragmentV2_MembersInjector.injectShareHelper(instance, this.provideShareHelperProvider.get());
            return instance;
        }
    }

    private static final class LyricsCardSelectionFragmentSubcomponentImpl implements LyricsSharingComponentsModule_LyricsCardSelectionFragment$LyricsCardSelectionFragmentSubcomponent {
        private final ApplicationComponentImpl applicationComponentImpl;
        private final LyricsCardSelectionFragmentSubcomponentImpl lyricsCardSelectionFragmentSubcomponentImpl;
        private Provider<com.amazon.music.share.menu.providers.EnvironmentProvider> provideEnvironmentProvider;
        private Provider<SocialMetadataProvider> provideSocialMetadataProvider;
        private Provider<com.amazon.music.share.menu.providers.StyleSheetProvider> provideStyleSheetProvider;

        private LyricsCardSelectionFragmentSubcomponentImpl(ApplicationComponentImpl applicationComponentImpl, ShareModule shareModuleParam, LyricsCardSelectionFragment arg0Param) {
            this.lyricsCardSelectionFragmentSubcomponentImpl = this;
            this.applicationComponentImpl = applicationComponentImpl;
            initialize(shareModuleParam, arg0Param);
        }

        private void initialize(final ShareModule shareModuleParam, final LyricsCardSelectionFragment arg0Param) {
            this.provideStyleSheetProvider = SingleCheck.provider(ShareModule_ProvideStyleSheetProviderFactory.create(shareModuleParam));
            this.provideSocialMetadataProvider = SingleCheck.provider(ShareModule_ProvideSocialMetadataProviderFactory.create(shareModuleParam, this.applicationComponentImpl.applicationProvider));
            this.provideEnvironmentProvider = SingleCheck.provider(ShareModule_ProvideEnvironmentProviderFactory.create(shareModuleParam, this.applicationComponentImpl.applicationProvider));
        }

        @Override // dagger.android.AndroidInjector
        public void inject(LyricsCardSelectionFragment arg0) {
            injectLyricsCardSelectionFragment(arg0);
        }

        private LyricsCardSelectionFragment injectLyricsCardSelectionFragment(LyricsCardSelectionFragment instance) {
            LyricsCardSelectionFragment_MembersInjector.injectStyleSheetProvider(instance, this.provideStyleSheetProvider.get());
            LyricsCardSelectionFragment_MembersInjector.injectSocialMetadataProvider(instance, this.provideSocialMetadataProvider.get());
            LyricsCardSelectionFragment_MembersInjector.injectEnvironmentProvider(instance, this.provideEnvironmentProvider.get());
            return instance;
        }
    }

    private static final class LiveEventDetailFragmentSubcomponentImpl implements ALPSAndroidComponentsModule_LiveEventDetailFragment$LiveEventDetailFragmentSubcomponent {
        private final ApplicationComponentImpl applicationComponentImpl;
        private final LiveEventDetailFragmentSubcomponentImpl liveEventDetailFragmentSubcomponentImpl;
        private Provider<com.amazon.music.alps.providers.ChromeProvider> provideAlpsChromeProvider;
        private Provider<com.amazon.music.alps.providers.SharingProvider> provideAlpsSharingProvider;
        private Provider<ChromeProvider> provideChromeProvider;
        private Provider<SharingProvider> provideSharingProvider;
        private Provider<Observable<StyleSheet>> provideStyleSheetProvider;
        private Provider<UserSubscription> provideUserSubscriptionProvider;

        private LiveEventDetailFragmentSubcomponentImpl(ApplicationComponentImpl applicationComponentImpl, ALPSModule aLPSModuleParam, LiveEventDetailFragment arg0Param) {
            this.liveEventDetailFragmentSubcomponentImpl = this;
            this.applicationComponentImpl = applicationComponentImpl;
            initialize(aLPSModuleParam, arg0Param);
        }

        private void initialize(final ALPSModule aLPSModuleParam, final LiveEventDetailFragment arg0Param) {
            this.provideUserSubscriptionProvider = SingleCheck.provider(ALPSModule_ProvideUserSubscriptionFactory.create(aLPSModuleParam));
            Provider<SharingProvider> provider = SingleCheck.provider(ALPSModule_ProvideSharingProviderFactory.create(aLPSModuleParam));
            this.provideSharingProvider = provider;
            this.provideAlpsSharingProvider = SingleCheck.provider(ALPSModule_ProvideAlpsSharingProviderFactory.create(aLPSModuleParam, provider));
            Provider<ChromeProvider> provider2 = SingleCheck.provider(ALPSModule_ProvideChromeProviderFactory.create(aLPSModuleParam));
            this.provideChromeProvider = provider2;
            this.provideAlpsChromeProvider = SingleCheck.provider(ALPSModule_ProvideAlpsChromeProviderFactory.create(aLPSModuleParam, provider2));
            this.provideStyleSheetProvider = SingleCheck.provider(ALPSModule_ProvideStyleSheetFactory.create(aLPSModuleParam));
        }

        @Override // dagger.android.AndroidInjector
        public void inject(LiveEventDetailFragment arg0) {
            injectLiveEventDetailFragment(arg0);
        }

        private LiveEventDetailFragment injectLiveEventDetailFragment(LiveEventDetailFragment instance) {
            LiveEventDetailFragment_MembersInjector.injectUserSubscription(instance, this.provideUserSubscriptionProvider.get());
            LiveEventDetailFragment_MembersInjector.injectSharingProvider(instance, this.provideAlpsSharingProvider.get());
            LiveEventDetailFragment_MembersInjector.injectChromeProvider(instance, this.provideAlpsChromeProvider.get());
            LiveEventDetailFragment_MembersInjector.injectStyleSheetObservable(instance, this.provideStyleSheetProvider.get());
            return instance;
        }
    }

    private static final class NowPlayingComponentImpl implements NowPlayingComponent {
        private final ApplicationComponentImpl applicationComponentImpl;
        private Provider<AudioVideoToggleUseCase> getAudioVideoUseCaseProvider;
        private Provider<HybridQueueStateProvider> getHybridQueueStateProvider;
        private Provider<HybridQueueTrackRequester> getHybridQueueTrackRequesterProvider;
        private Provider<LyricsSharingDataModel> getLyricsSharingDataModelProvider;
        private Provider<OverlayLyricsStateProvider> getOverlayLyricsStateProvider;
        private Provider<OverlayTypeStateProvider> getOverlayTypeStateProvider;
        private Provider<OverlayXrayStateProvider> getOverlayXrayStateProvider;
        private Provider<StageAutomatedSequencingHandler> getStageAutomatedSequencingHandlerProvider;
        private Provider<StageOverflowMenuSecondaryIconStateProvider> getStageOverflowMenuSecondaryIconStateProvider;
        private Provider<VideoPlayerStateProvider> getVideoPlayerStateProvider;
        private final NowPlayingComponentImpl nowPlayingComponentImpl;

        @Override // com.amazon.mp3.starlight.subcomponent.NowPlayingComponent
        public void inject(AudioVideoToggleInteractor audioVideoToggleInteractor) {
        }

        private NowPlayingComponentImpl(ApplicationComponentImpl applicationComponentImpl, StarlightModule starlightModuleParam, StageModule stageModuleParam) {
            this.nowPlayingComponentImpl = this;
            this.applicationComponentImpl = applicationComponentImpl;
            initialize(starlightModuleParam, stageModuleParam);
        }

        private void initialize(final StarlightModule starlightModuleParam, final StageModule stageModuleParam) {
            this.getHybridQueueStateProvider = DoubleCheck.provider(StarlightModule_GetHybridQueueStateProviderFactory.create(starlightModuleParam));
            this.getHybridQueueTrackRequesterProvider = DoubleCheck.provider(StarlightModule_GetHybridQueueTrackRequesterFactory.create(starlightModuleParam, this.applicationComponentImpl.applicationProvider, this.getHybridQueueStateProvider));
            this.getAudioVideoUseCaseProvider = DoubleCheck.provider(StarlightModule_GetAudioVideoUseCaseFactory.create(starlightModuleParam, this.applicationComponentImpl.applicationProvider, this.getHybridQueueStateProvider, this.getHybridQueueTrackRequesterProvider));
            this.getOverlayTypeStateProvider = DoubleCheck.provider(StageModule_GetOverlayTypeStateProviderFactory.create(stageModuleParam));
            this.getOverlayLyricsStateProvider = DoubleCheck.provider(StageModule_GetOverlayLyricsStateProviderFactory.create(stageModuleParam));
            this.getStageAutomatedSequencingHandlerProvider = DoubleCheck.provider(StageModule_GetStageAutomatedSequencingHandlerFactory.create(stageModuleParam));
            this.getVideoPlayerStateProvider = DoubleCheck.provider(StarlightModule_GetVideoPlayerStateProviderFactory.create(starlightModuleParam));
            this.getOverlayXrayStateProvider = DoubleCheck.provider(StageModule_GetOverlayXrayStateProviderFactory.create(stageModuleParam));
            this.getStageOverflowMenuSecondaryIconStateProvider = DoubleCheck.provider(StageModule_GetStageOverflowMenuSecondaryIconStateProviderFactory.create(stageModuleParam));
            this.getLyricsSharingDataModelProvider = DoubleCheck.provider(StageModule_GetLyricsSharingDataModelFactory.create(stageModuleParam));
        }

        @Override // com.amazon.mp3.starlight.subcomponent.NowPlayingComponent
        public void inject(NowPlayingFragment nowPlayingFragment) {
            injectNowPlayingFragment(nowPlayingFragment);
        }

        @Override // com.amazon.mp3.starlight.subcomponent.NowPlayingComponent
        public void inject(PlayQueueSequencer playQueueSequencer) {
            injectPlayQueueSequencer(playQueueSequencer);
        }

        @Override // com.amazon.mp3.starlight.subcomponent.NowPlayingComponent
        public void inject(VideoPlayerFragment videoPlayerFragment) {
            injectVideoPlayerFragment(videoPlayerFragment);
        }

        @Override // com.amazon.mp3.starlight.subcomponent.NowPlayingComponent
        public void inject(StageVideoPlayerFragment videoPlayerFragment) {
            injectStageVideoPlayerFragment(videoPlayerFragment);
        }

        @Override // com.amazon.mp3.starlight.subcomponent.NowPlayingComponent
        public void inject(CloudQueueSequencer cloudQueueSequencer) {
            injectCloudQueueSequencer(cloudQueueSequencer);
        }

        @Override // com.amazon.mp3.starlight.subcomponent.NowPlayingComponent
        public void inject(StageViewPresenter stageViewPresenter) {
            injectStageViewPresenter(stageViewPresenter);
        }

        @Override // com.amazon.mp3.starlight.subcomponent.NowPlayingComponent
        public void inject(StageOverlayMetricsReporter stageOverlayMetricsReporter) {
            injectStageOverlayMetricsReporter(stageOverlayMetricsReporter);
        }

        @Override // com.amazon.mp3.starlight.subcomponent.NowPlayingComponent
        public void inject(StageAutomatedSequencingHandler stageAutomatedSequencingHandler) {
            injectStageAutomatedSequencingHandler(stageAutomatedSequencingHandler);
        }

        @Override // com.amazon.mp3.starlight.subcomponent.NowPlayingComponent
        public void inject(LyricsSelectionFragment lyricsSelectionFragment) {
            injectLyricsSelectionFragment(lyricsSelectionFragment);
        }

        @Override // com.amazon.mp3.starlight.subcomponent.NowPlayingComponent
        public void inject(LyricsListViewContainer lyricsListViewContainer) {
            injectLyricsListViewContainer(lyricsListViewContainer);
        }

        private NowPlayingFragment injectNowPlayingFragment(NowPlayingFragment instance) {
            NowPlayingFragment_MembersInjector.injectMAudioVideoToggleInteractor(instance, this.getAudioVideoUseCaseProvider.get());
            return instance;
        }

        private PlayQueueSequencer injectPlayQueueSequencer(PlayQueueSequencer instance) {
            PlayQueueSequencer_MembersInjector.injectMHybridQueueStateProvider(instance, this.getHybridQueueStateProvider.get());
            PlayQueueSequencer_MembersInjector.injectMOverlayTypeStateProvider(instance, this.getOverlayTypeStateProvider.get());
            PlayQueueSequencer_MembersInjector.injectMOverlayLyricsStateProvider(instance, this.getOverlayLyricsStateProvider.get());
            PlayQueueSequencer_MembersInjector.injectMStageAutomatedSequencingHandler(instance, this.getStageAutomatedSequencingHandlerProvider.get());
            return instance;
        }

        private VideoPlayerFragment injectVideoPlayerFragment(VideoPlayerFragment instance) {
            VideoPlayerFragment_MembersInjector.injectAudioVideoToggleInteractor(instance, this.getAudioVideoUseCaseProvider.get());
            VideoPlayerFragment_MembersInjector.injectVideoPlayerStateProvider(instance, this.getVideoPlayerStateProvider.get());
            return instance;
        }

        private StageVideoPlayerFragment injectStageVideoPlayerFragment(StageVideoPlayerFragment instance) {
            StageVideoPlayerFragment_MembersInjector.injectAudioVideoToggleInteractor(instance, this.getAudioVideoUseCaseProvider.get());
            StageVideoPlayerFragment_MembersInjector.injectVideoPlayerStateProvider(instance, this.getVideoPlayerStateProvider.get());
            return instance;
        }

        private CloudQueueSequencer injectCloudQueueSequencer(CloudQueueSequencer instance) {
            CloudQueueSequencer_MembersInjector.injectHybridQueueStateProvider(instance, this.getHybridQueueStateProvider.get());
            CloudQueueSequencer_MembersInjector.injectOverlayTypeStateProvider(instance, this.getOverlayTypeStateProvider.get());
            CloudQueueSequencer_MembersInjector.injectOverlayLyricsStateProvider(instance, this.getOverlayLyricsStateProvider.get());
            CloudQueueSequencer_MembersInjector.injectStageAutomatedSequencingHandler(instance, this.getStageAutomatedSequencingHandlerProvider.get());
            return instance;
        }

        private StageViewPresenter injectStageViewPresenter(StageViewPresenter instance) {
            StageViewPresenter_MembersInjector.injectOverlayTypeStateProvider(instance, this.getOverlayTypeStateProvider.get());
            StageViewPresenter_MembersInjector.injectOverlayXrayStateProvider(instance, this.getOverlayXrayStateProvider.get());
            StageViewPresenter_MembersInjector.injectOverlayLyricsStateProvider(instance, this.getOverlayLyricsStateProvider.get());
            StageViewPresenter_MembersInjector.injectStageAutomatedSequencingHandler(instance, this.getStageAutomatedSequencingHandlerProvider.get());
            StageViewPresenter_MembersInjector.injectStageOverflowMenuSecondaryIconStateProvider(instance, this.getStageOverflowMenuSecondaryIconStateProvider.get());
            StageViewPresenter_MembersInjector.injectLyricsSharingDataModel(instance, this.getLyricsSharingDataModelProvider.get());
            return instance;
        }

        private StageOverlayMetricsReporter injectStageOverlayMetricsReporter(StageOverlayMetricsReporter instance) {
            StageOverlayMetricsReporter_MembersInjector.injectOverlayTypeStateProvider(instance, this.getOverlayTypeStateProvider.get());
            return instance;
        }

        private StageAutomatedSequencingHandler injectStageAutomatedSequencingHandler(StageAutomatedSequencingHandler instance) {
            StageAutomatedSequencingHandler_MembersInjector.injectOverlayTypeStateProvider(instance, this.getOverlayTypeStateProvider.get());
            StageAutomatedSequencingHandler_MembersInjector.injectOverlayLyricsStateProvider(instance, this.getOverlayLyricsStateProvider.get());
            return instance;
        }

        private LyricsSelectionFragment injectLyricsSelectionFragment(LyricsSelectionFragment instance) {
            LyricsSelectionFragment_MembersInjector.injectLyricsSharingDataModel(instance, this.getLyricsSharingDataModelProvider.get());
            return instance;
        }

        private LyricsListViewContainer injectLyricsListViewContainer(LyricsListViewContainer instance) {
            LyricsListViewContainer_MembersInjector.injectMLyricsSharingDataModel(instance, this.getLyricsSharingDataModelProvider.get());
            return instance;
        }
    }

    private static final class ApplicationComponentImpl implements ApplicationComponent {
        private Provider<ExploreAndroidComponentsModule_AmpPlaybackActivity$AmpPlaybackActivitySubcomponent.Factory> ampPlaybackActivitySubcomponentFactoryProvider;
        private final Application application;
        private final ApplicationComponentImpl applicationComponentImpl;
        private Provider<Application> applicationProvider;
        private Provider<FragmentModule_ArtistDetailBrushFragment$ArtistDetailBrushFragmentSubcomponent.Factory> artistDetailBrushFragmentSubcomponentFactoryProvider;
        private Provider<CarBluetoothDetector> carBluetoothDetectorProvider;
        private Provider<CarDetector> carDetectorProvider;
        private Provider<FindAndroidComponentsModule_CarModeSearchBrushFragment$CarModeSearchBrushFragmentSubcomponent.Factory> carModeSearchBrushFragmentSubcomponentFactoryProvider;
        private Provider<ExploreAndroidComponentsModule_CastingSkillService$CastingSkillServiceSubcomponent.Factory> castingSkillServiceSubcomponentFactoryProvider;
        private Provider<ConnectAndroidComponentsModule_ContactImportWebViewFragment$ContactImportWebViewFragmentSubcomponent.Factory> contactImportWebViewFragmentSubcomponentFactoryProvider;
        private Provider<ExploreAndroidComponentsModule_DetailFragment$DetailFragmentSubcomponent.Factory> detailFragmentSubcomponentFactoryProvider;
        private Provider<ConnectAndroidComponentsModule_EditCustomerProfileDialogFragment$EditCustomerProfileFragmentSubcomponent.Factory> editCustomerProfileFragmentSubcomponentFactoryProvider;
        private Provider<FindAndroidComponentsModule_EntityTrackingIntentService$EntityTrackingIntentServiceSubcomponent.Factory> entityTrackingIntentServiceSubcomponentFactoryProvider;
        private Provider<FindAndroidComponentsModule_FindBrushFragment$FindBrushFragmentSubcomponent.Factory> findBrushFragmentSubcomponentFactoryProvider;
        private Provider<FindAndroidComponentsModule_FindFilterDialogFragment$FindFilterDialogFragmentSubcomponent.Factory> findFilterDialogFragmentSubcomponentFactoryProvider;
        private Provider<FindAndroidComponentsModule_FindLandingFragment$FindLandingFragmentSubcomponent.Factory> findLandingFragmentSubcomponentFactoryProvider;
        private Provider<FindAndroidComponentsModule_FindSkyfireFragment$FindSkyfireFragmentSubcomponent.Factory> findSkyfireFragmentSubcomponentFactoryProvider;
        private Provider<ExploreAndroidComponentsModule_GalleryFragment$GalleryFragmentSubcomponent.Factory> galleryFragmentSubcomponentFactoryProvider;
        private Provider<ALPSAndroidComponentsModule_LiveEventDetailFragment$LiveEventDetailFragmentSubcomponent.Factory> liveEventDetailFragmentSubcomponentFactoryProvider;
        private Provider<ExploreAndroidComponentsModule_LiveStreamActivity$LiveStreamActivitySubcomponent.Factory> liveStreamActivitySubcomponentFactoryProvider;
        private Provider<LyricsSharingComponentsModule_LyricsCardSelectionFragment$LyricsCardSelectionFragmentSubcomponent.Factory> lyricsCardSelectionFragmentSubcomponentFactoryProvider;
        private Provider<ExploreAndroidComponentsModule_MultiSelectorFragment$MultiSelectorFragmentSubcomponent.Factory> multiSelectorFragmentSubcomponentFactoryProvider;
        private Provider<ExploreAndroidComponentsModule_MultiSelectorTabFragment$MultiSelectorTabFragmentSubcomponent.Factory> multiSelectorTabFragmentSubcomponentFactoryProvider;
        private Provider<CurateAndroidComponentsModule_MySoundtrackFragment$MySoundtrackFragmentSubcomponent.Factory> mySoundtrackFragmentSubcomponentFactoryProvider;
        private Provider<CurateAndroidComponentsModule_PersonalizedPlaylistsFragment$PersonalizedPlaylistsFragmentSubcomponent.Factory> personalizedPlaylistsFragmentSubcomponentFactoryProvider;
        private final PlatformProvidersModule platformProvidersModule;
        private Provider<ExploreAndroidComponentsModule_PlaybackFragment$PlaybackFragmentSubcomponent.Factory> playbackFragmentSubcomponentFactoryProvider;
        private Provider<FragmentModule_PlaylistDetailFragment$PlaylistDetailFragmentSubcomponent.Factory> playlistDetailFragmentSubcomponentFactoryProvider;
        private Provider<CurateAndroidComponentsModule_PlaylistIntentReceiverService$PlaylistIntentReceiverServiceSubcomponent.Factory> playlistIntentReceiverServiceSubcomponentFactoryProvider;
        private Provider<CurateAndroidComponentsModule_PlaylistRecsSeeMoreFragment$PlaylistRecsSeeMoreFragmentSubcomponent.Factory> playlistRecsSeeMoreFragmentSubcomponentFactoryProvider;
        private Provider<AuthenticationProvider> provideAuthenticationProvider;
        private Provider<BuildInfoProvider> provideBuildInfoProvider;
        private Provider<CustomerMetadataProvider> provideCustomerMetadataProvider;
        private Provider<DeviceInfoProvider> provideDeviceInfoProvider;
        private Provider<MetricsProvider> provideMetricsProvider;
        private Provider<FindAndroidComponentsModule_SearchBrushFragment$SearchBrushFragmentSubcomponent.Factory> searchBrushFragmentSubcomponentFactoryProvider;
        private Provider<FindAndroidComponentsModule_SearchGalleryFragment$SearchGalleryFragmentSubcomponent.Factory> searchGalleryFragmentSubcomponentFactoryProvider;
        private Provider<FindAndroidComponentsModule_SearchHostFragment$SearchHostFragmentSubcomponent.Factory> searchHostFragmentSubcomponentFactoryProvider;
        private Provider<ExploreAndroidComponentsModule_SelectionFragment$SelectionFragmentSubcomponent.Factory> selectionFragmentSubcomponentFactoryProvider;
        private Provider<ShareAndroidComponentsModule_SharingFragment$SharingFragmentSubcomponent.Factory> sharingFragmentSubcomponentFactoryProvider;
        private Provider<ShareAndroidComponentsModule_SharingFragmentV2$SharingFragmentV2Subcomponent.Factory> sharingFragmentV2SubcomponentFactoryProvider;
        private Provider<ExploreAndroidComponentsModule_StageSwipeablePagesFragment$StageSwipeablePagesFragmentSubcomponent.Factory> stageSwipeablePagesFragmentSubcomponentFactoryProvider;
        private Provider<ExploreAndroidComponentsModule_SwipeablePagesFragment$SwipeablePagesFragmentSubcomponent.Factory> swipeablePagesFragmentSubcomponentFactoryProvider;
        private Provider<CurateAndroidComponentsModule_TemplateFragment$TemplateFragmentSubcomponent.Factory> templateFragmentSubcomponentFactoryProvider;
        private Provider<CurateAndroidComponentsModule_UserPlaylistFragment$UserPlaylistFragmentSubcomponent.Factory> userPlaylistFragmentSubcomponentFactoryProvider;
        private Provider<CurateAndroidComponentsModule_UserPlaylistsFragment$UserPlaylistsFragmentSubcomponent.Factory> userPlaylistsFragmentSubcomponentFactoryProvider;
        private Provider<ExploreAndroidComponentsModule_VideoQueueActivity$VideoQueueActivitySubcomponent.Factory> videoQueueActivitySubcomponentFactoryProvider;
        private Provider<ExploreAndroidComponentsModule_VideoStoryFragment$VideoStoryFragmentSubcomponent.Factory> videoStoryFragmentSubcomponentFactoryProvider;
        private Provider<ExploreAndroidComponentsModule_WatchSessionService$WatchSessionServiceSubcomponent.Factory> watchSessionServiceSubcomponentFactoryProvider;

        private ApplicationComponentImpl(CarDetectorModule carDetectorModuleParam, PlatformProvidersModule platformProvidersModuleParam, Application applicationParam) {
            this.applicationComponentImpl = this;
            this.application = applicationParam;
            this.platformProvidersModule = platformProvidersModuleParam;
            initialize(carDetectorModuleParam, platformProvidersModuleParam, applicationParam);
        }

        private Map<Class<?>, Provider<AndroidInjector.Factory<?>>> mapOfClassOfAndProviderOfAndroidInjectorFactoryOf() {
            return ImmutableMap.builderWithExpectedSize(38).put(ArtistDetailBrushFragment.class, this.artistDetailBrushFragmentSubcomponentFactoryProvider).put(PlaylistDetailFragment.class, this.playlistDetailFragmentSubcomponentFactoryProvider).put(UserPlaylistFragment.class, this.userPlaylistFragmentSubcomponentFactoryProvider).put(UserPlaylistsFragment.class, this.userPlaylistsFragmentSubcomponentFactoryProvider).put(PlaylistRecsSeeMoreFragment.class, this.playlistRecsSeeMoreFragmentSubcomponentFactoryProvider).put(PersonalizedPlaylistsFragment.class, this.personalizedPlaylistsFragmentSubcomponentFactoryProvider).put(MySoundtrackFragment.class, this.mySoundtrackFragmentSubcomponentFactoryProvider).put(TemplateFragment.class, this.templateFragmentSubcomponentFactoryProvider).put(PlaylistIntentReceiverService.class, this.playlistIntentReceiverServiceSubcomponentFactoryProvider).put(PlaybackFragment.class, this.playbackFragmentSubcomponentFactoryProvider).put(SwipeablePagesFragment.class, this.swipeablePagesFragmentSubcomponentFactoryProvider).put(StageSwipeablePagesFragment.class, this.stageSwipeablePagesFragmentSubcomponentFactoryProvider).put(LiveStreamActivity.class, this.liveStreamActivitySubcomponentFactoryProvider).put(AmpPlaybackActivity.class, this.ampPlaybackActivitySubcomponentFactoryProvider).put(GalleryFragment.class, this.galleryFragmentSubcomponentFactoryProvider).put(VideoQueueActivity.class, this.videoQueueActivitySubcomponentFactoryProvider).put(DetailFragment.class, this.detailFragmentSubcomponentFactoryProvider).put(SelectionFragment.class, this.selectionFragmentSubcomponentFactoryProvider).put(MultiSelectorFragment.class, this.multiSelectorFragmentSubcomponentFactoryProvider).put(MultiSelectorTabFragment.class, this.multiSelectorTabFragmentSubcomponentFactoryProvider).put(WatchSessionService.class, this.watchSessionServiceSubcomponentFactoryProvider).put(VideoStoryFragment.class, this.videoStoryFragmentSubcomponentFactoryProvider).put(CastingSkillService.class, this.castingSkillServiceSubcomponentFactoryProvider).put(FindBrushFragment.class, this.findBrushFragmentSubcomponentFactoryProvider).put(FindFilterDialogFragment.class, this.findFilterDialogFragmentSubcomponentFactoryProvider).put(EntityTrackingIntentService.class, this.entityTrackingIntentServiceSubcomponentFactoryProvider).put(SearchBrushFragment.class, this.searchBrushFragmentSubcomponentFactoryProvider).put(CarModeSearchBrushFragment.class, this.carModeSearchBrushFragmentSubcomponentFactoryProvider).put(FindLandingFragment.class, this.findLandingFragmentSubcomponentFactoryProvider).put(FindSkyfireFragment.class, this.findSkyfireFragmentSubcomponentFactoryProvider).put(SearchGalleryFragment.class, this.searchGalleryFragmentSubcomponentFactoryProvider).put(SearchHostFragment.class, this.searchHostFragmentSubcomponentFactoryProvider).put(ContactImportWebViewFragment.class, this.contactImportWebViewFragmentSubcomponentFactoryProvider).put(EditCustomerProfileFragment.class, this.editCustomerProfileFragmentSubcomponentFactoryProvider).put(SharingFragment.class, this.sharingFragmentSubcomponentFactoryProvider).put(SharingFragmentV2.class, this.sharingFragmentV2SubcomponentFactoryProvider).put(LyricsCardSelectionFragment.class, this.lyricsCardSelectionFragmentSubcomponentFactoryProvider).put(LiveEventDetailFragment.class, this.liveEventDetailFragmentSubcomponentFactoryProvider).build();
        }

        private DispatchingAndroidInjector<Object> dispatchingAndroidInjectorOfObject() {
            return DispatchingAndroidInjector_Factory.newInstance(mapOfClassOfAndProviderOfAndroidInjectorFactoryOf(), ImmutableMap.of());
        }

        private void initialize(final CarDetectorModule carDetectorModuleParam, final PlatformProvidersModule platformProvidersModuleParam, final Application applicationParam) {
            this.artistDetailBrushFragmentSubcomponentFactoryProvider = new Provider<FragmentModule_ArtistDetailBrushFragment$ArtistDetailBrushFragmentSubcomponent.Factory>() { // from class: com.amazon.mp3.dagger.DaggerApplicationComponent.ApplicationComponentImpl.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javax.inject.Provider
                public FragmentModule_ArtistDetailBrushFragment$ArtistDetailBrushFragmentSubcomponent.Factory get() {
                    return new ArtistDetailBrushFragmentSubcomponentFactory(ApplicationComponentImpl.this.applicationComponentImpl);
                }
            };
            this.playlistDetailFragmentSubcomponentFactoryProvider = new Provider<FragmentModule_PlaylistDetailFragment$PlaylistDetailFragmentSubcomponent.Factory>() { // from class: com.amazon.mp3.dagger.DaggerApplicationComponent.ApplicationComponentImpl.2
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javax.inject.Provider
                public FragmentModule_PlaylistDetailFragment$PlaylistDetailFragmentSubcomponent.Factory get() {
                    return new PlaylistDetailFragmentSubcomponentFactory(ApplicationComponentImpl.this.applicationComponentImpl);
                }
            };
            this.userPlaylistFragmentSubcomponentFactoryProvider = new Provider<CurateAndroidComponentsModule_UserPlaylistFragment$UserPlaylistFragmentSubcomponent.Factory>() { // from class: com.amazon.mp3.dagger.DaggerApplicationComponent.ApplicationComponentImpl.3
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javax.inject.Provider
                public CurateAndroidComponentsModule_UserPlaylistFragment$UserPlaylistFragmentSubcomponent.Factory get() {
                    return new UserPlaylistFragmentSubcomponentFactory(ApplicationComponentImpl.this.applicationComponentImpl);
                }
            };
            this.userPlaylistsFragmentSubcomponentFactoryProvider = new Provider<CurateAndroidComponentsModule_UserPlaylistsFragment$UserPlaylistsFragmentSubcomponent.Factory>() { // from class: com.amazon.mp3.dagger.DaggerApplicationComponent.ApplicationComponentImpl.4
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javax.inject.Provider
                public CurateAndroidComponentsModule_UserPlaylistsFragment$UserPlaylistsFragmentSubcomponent.Factory get() {
                    return new UserPlaylistsFragmentSubcomponentFactory(ApplicationComponentImpl.this.applicationComponentImpl);
                }
            };
            this.playlistRecsSeeMoreFragmentSubcomponentFactoryProvider = new Provider<CurateAndroidComponentsModule_PlaylistRecsSeeMoreFragment$PlaylistRecsSeeMoreFragmentSubcomponent.Factory>() { // from class: com.amazon.mp3.dagger.DaggerApplicationComponent.ApplicationComponentImpl.5
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javax.inject.Provider
                public CurateAndroidComponentsModule_PlaylistRecsSeeMoreFragment$PlaylistRecsSeeMoreFragmentSubcomponent.Factory get() {
                    return new PlaylistRecsSeeMoreFragmentSubcomponentFactory(ApplicationComponentImpl.this.applicationComponentImpl);
                }
            };
            this.personalizedPlaylistsFragmentSubcomponentFactoryProvider = new Provider<CurateAndroidComponentsModule_PersonalizedPlaylistsFragment$PersonalizedPlaylistsFragmentSubcomponent.Factory>() { // from class: com.amazon.mp3.dagger.DaggerApplicationComponent.ApplicationComponentImpl.6
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javax.inject.Provider
                public CurateAndroidComponentsModule_PersonalizedPlaylistsFragment$PersonalizedPlaylistsFragmentSubcomponent.Factory get() {
                    return new PersonalizedPlaylistsFragmentSubcomponentFactory(ApplicationComponentImpl.this.applicationComponentImpl);
                }
            };
            this.mySoundtrackFragmentSubcomponentFactoryProvider = new Provider<CurateAndroidComponentsModule_MySoundtrackFragment$MySoundtrackFragmentSubcomponent.Factory>() { // from class: com.amazon.mp3.dagger.DaggerApplicationComponent.ApplicationComponentImpl.7
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javax.inject.Provider
                public CurateAndroidComponentsModule_MySoundtrackFragment$MySoundtrackFragmentSubcomponent.Factory get() {
                    return new MySoundtrackFragmentSubcomponentFactory(ApplicationComponentImpl.this.applicationComponentImpl);
                }
            };
            this.templateFragmentSubcomponentFactoryProvider = new Provider<CurateAndroidComponentsModule_TemplateFragment$TemplateFragmentSubcomponent.Factory>() { // from class: com.amazon.mp3.dagger.DaggerApplicationComponent.ApplicationComponentImpl.8
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javax.inject.Provider
                public CurateAndroidComponentsModule_TemplateFragment$TemplateFragmentSubcomponent.Factory get() {
                    return new TemplateFragmentSubcomponentFactory(ApplicationComponentImpl.this.applicationComponentImpl);
                }
            };
            this.playlistIntentReceiverServiceSubcomponentFactoryProvider = new Provider<CurateAndroidComponentsModule_PlaylistIntentReceiverService$PlaylistIntentReceiverServiceSubcomponent.Factory>() { // from class: com.amazon.mp3.dagger.DaggerApplicationComponent.ApplicationComponentImpl.9
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javax.inject.Provider
                public CurateAndroidComponentsModule_PlaylistIntentReceiverService$PlaylistIntentReceiverServiceSubcomponent.Factory get() {
                    return new PlaylistIntentReceiverServiceSubcomponentFactory(ApplicationComponentImpl.this.applicationComponentImpl);
                }
            };
            this.playbackFragmentSubcomponentFactoryProvider = new Provider<ExploreAndroidComponentsModule_PlaybackFragment$PlaybackFragmentSubcomponent.Factory>() { // from class: com.amazon.mp3.dagger.DaggerApplicationComponent.ApplicationComponentImpl.10
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javax.inject.Provider
                public ExploreAndroidComponentsModule_PlaybackFragment$PlaybackFragmentSubcomponent.Factory get() {
                    return new PlaybackFragmentSubcomponentFactory(ApplicationComponentImpl.this.applicationComponentImpl);
                }
            };
            this.swipeablePagesFragmentSubcomponentFactoryProvider = new Provider<ExploreAndroidComponentsModule_SwipeablePagesFragment$SwipeablePagesFragmentSubcomponent.Factory>() { // from class: com.amazon.mp3.dagger.DaggerApplicationComponent.ApplicationComponentImpl.11
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javax.inject.Provider
                public ExploreAndroidComponentsModule_SwipeablePagesFragment$SwipeablePagesFragmentSubcomponent.Factory get() {
                    return new SwipeablePagesFragmentSubcomponentFactory(ApplicationComponentImpl.this.applicationComponentImpl);
                }
            };
            this.stageSwipeablePagesFragmentSubcomponentFactoryProvider = new Provider<ExploreAndroidComponentsModule_StageSwipeablePagesFragment$StageSwipeablePagesFragmentSubcomponent.Factory>() { // from class: com.amazon.mp3.dagger.DaggerApplicationComponent.ApplicationComponentImpl.12
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javax.inject.Provider
                public ExploreAndroidComponentsModule_StageSwipeablePagesFragment$StageSwipeablePagesFragmentSubcomponent.Factory get() {
                    return new StageSwipeablePagesFragmentSubcomponentFactory(ApplicationComponentImpl.this.applicationComponentImpl);
                }
            };
            this.liveStreamActivitySubcomponentFactoryProvider = new Provider<ExploreAndroidComponentsModule_LiveStreamActivity$LiveStreamActivitySubcomponent.Factory>() { // from class: com.amazon.mp3.dagger.DaggerApplicationComponent.ApplicationComponentImpl.13
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javax.inject.Provider
                public ExploreAndroidComponentsModule_LiveStreamActivity$LiveStreamActivitySubcomponent.Factory get() {
                    return new LiveStreamActivitySubcomponentFactory(ApplicationComponentImpl.this.applicationComponentImpl);
                }
            };
            this.ampPlaybackActivitySubcomponentFactoryProvider = new Provider<ExploreAndroidComponentsModule_AmpPlaybackActivity$AmpPlaybackActivitySubcomponent.Factory>() { // from class: com.amazon.mp3.dagger.DaggerApplicationComponent.ApplicationComponentImpl.14
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javax.inject.Provider
                public ExploreAndroidComponentsModule_AmpPlaybackActivity$AmpPlaybackActivitySubcomponent.Factory get() {
                    return new AmpPlaybackActivitySubcomponentFactory(ApplicationComponentImpl.this.applicationComponentImpl);
                }
            };
            this.galleryFragmentSubcomponentFactoryProvider = new Provider<ExploreAndroidComponentsModule_GalleryFragment$GalleryFragmentSubcomponent.Factory>() { // from class: com.amazon.mp3.dagger.DaggerApplicationComponent.ApplicationComponentImpl.15
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javax.inject.Provider
                public ExploreAndroidComponentsModule_GalleryFragment$GalleryFragmentSubcomponent.Factory get() {
                    return new GalleryFragmentSubcomponentFactory(ApplicationComponentImpl.this.applicationComponentImpl);
                }
            };
            this.videoQueueActivitySubcomponentFactoryProvider = new Provider<ExploreAndroidComponentsModule_VideoQueueActivity$VideoQueueActivitySubcomponent.Factory>() { // from class: com.amazon.mp3.dagger.DaggerApplicationComponent.ApplicationComponentImpl.16
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javax.inject.Provider
                public ExploreAndroidComponentsModule_VideoQueueActivity$VideoQueueActivitySubcomponent.Factory get() {
                    return new VideoQueueActivitySubcomponentFactory(ApplicationComponentImpl.this.applicationComponentImpl);
                }
            };
            this.detailFragmentSubcomponentFactoryProvider = new Provider<ExploreAndroidComponentsModule_DetailFragment$DetailFragmentSubcomponent.Factory>() { // from class: com.amazon.mp3.dagger.DaggerApplicationComponent.ApplicationComponentImpl.17
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javax.inject.Provider
                public ExploreAndroidComponentsModule_DetailFragment$DetailFragmentSubcomponent.Factory get() {
                    return new DetailFragmentSubcomponentFactory(ApplicationComponentImpl.this.applicationComponentImpl);
                }
            };
            this.selectionFragmentSubcomponentFactoryProvider = new Provider<ExploreAndroidComponentsModule_SelectionFragment$SelectionFragmentSubcomponent.Factory>() { // from class: com.amazon.mp3.dagger.DaggerApplicationComponent.ApplicationComponentImpl.18
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javax.inject.Provider
                public ExploreAndroidComponentsModule_SelectionFragment$SelectionFragmentSubcomponent.Factory get() {
                    return new SelectionFragmentSubcomponentFactory(ApplicationComponentImpl.this.applicationComponentImpl);
                }
            };
            this.multiSelectorFragmentSubcomponentFactoryProvider = new Provider<ExploreAndroidComponentsModule_MultiSelectorFragment$MultiSelectorFragmentSubcomponent.Factory>() { // from class: com.amazon.mp3.dagger.DaggerApplicationComponent.ApplicationComponentImpl.19
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javax.inject.Provider
                public ExploreAndroidComponentsModule_MultiSelectorFragment$MultiSelectorFragmentSubcomponent.Factory get() {
                    return new MultiSelectorFragmentSubcomponentFactory(ApplicationComponentImpl.this.applicationComponentImpl);
                }
            };
            this.multiSelectorTabFragmentSubcomponentFactoryProvider = new Provider<ExploreAndroidComponentsModule_MultiSelectorTabFragment$MultiSelectorTabFragmentSubcomponent.Factory>() { // from class: com.amazon.mp3.dagger.DaggerApplicationComponent.ApplicationComponentImpl.20
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javax.inject.Provider
                public ExploreAndroidComponentsModule_MultiSelectorTabFragment$MultiSelectorTabFragmentSubcomponent.Factory get() {
                    return new MultiSelectorTabFragmentSubcomponentFactory(ApplicationComponentImpl.this.applicationComponentImpl);
                }
            };
            this.watchSessionServiceSubcomponentFactoryProvider = new Provider<ExploreAndroidComponentsModule_WatchSessionService$WatchSessionServiceSubcomponent.Factory>() { // from class: com.amazon.mp3.dagger.DaggerApplicationComponent.ApplicationComponentImpl.21
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javax.inject.Provider
                public ExploreAndroidComponentsModule_WatchSessionService$WatchSessionServiceSubcomponent.Factory get() {
                    return new WatchSessionServiceSubcomponentFactory(ApplicationComponentImpl.this.applicationComponentImpl);
                }
            };
            this.videoStoryFragmentSubcomponentFactoryProvider = new Provider<ExploreAndroidComponentsModule_VideoStoryFragment$VideoStoryFragmentSubcomponent.Factory>() { // from class: com.amazon.mp3.dagger.DaggerApplicationComponent.ApplicationComponentImpl.22
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javax.inject.Provider
                public ExploreAndroidComponentsModule_VideoStoryFragment$VideoStoryFragmentSubcomponent.Factory get() {
                    return new VideoStoryFragmentSubcomponentFactory(ApplicationComponentImpl.this.applicationComponentImpl);
                }
            };
            this.castingSkillServiceSubcomponentFactoryProvider = new Provider<ExploreAndroidComponentsModule_CastingSkillService$CastingSkillServiceSubcomponent.Factory>() { // from class: com.amazon.mp3.dagger.DaggerApplicationComponent.ApplicationComponentImpl.23
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javax.inject.Provider
                public ExploreAndroidComponentsModule_CastingSkillService$CastingSkillServiceSubcomponent.Factory get() {
                    return new CastingSkillServiceSubcomponentFactory(ApplicationComponentImpl.this.applicationComponentImpl);
                }
            };
            this.findBrushFragmentSubcomponentFactoryProvider = new Provider<FindAndroidComponentsModule_FindBrushFragment$FindBrushFragmentSubcomponent.Factory>() { // from class: com.amazon.mp3.dagger.DaggerApplicationComponent.ApplicationComponentImpl.24
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javax.inject.Provider
                public FindAndroidComponentsModule_FindBrushFragment$FindBrushFragmentSubcomponent.Factory get() {
                    return new FindBrushFragmentSubcomponentFactory(ApplicationComponentImpl.this.applicationComponentImpl);
                }
            };
            this.findFilterDialogFragmentSubcomponentFactoryProvider = new Provider<FindAndroidComponentsModule_FindFilterDialogFragment$FindFilterDialogFragmentSubcomponent.Factory>() { // from class: com.amazon.mp3.dagger.DaggerApplicationComponent.ApplicationComponentImpl.25
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javax.inject.Provider
                public FindAndroidComponentsModule_FindFilterDialogFragment$FindFilterDialogFragmentSubcomponent.Factory get() {
                    return new FindFilterDialogFragmentSubcomponentFactory(ApplicationComponentImpl.this.applicationComponentImpl);
                }
            };
            this.entityTrackingIntentServiceSubcomponentFactoryProvider = new Provider<FindAndroidComponentsModule_EntityTrackingIntentService$EntityTrackingIntentServiceSubcomponent.Factory>() { // from class: com.amazon.mp3.dagger.DaggerApplicationComponent.ApplicationComponentImpl.26
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javax.inject.Provider
                public FindAndroidComponentsModule_EntityTrackingIntentService$EntityTrackingIntentServiceSubcomponent.Factory get() {
                    return new EntityTrackingIntentServiceSubcomponentFactory(ApplicationComponentImpl.this.applicationComponentImpl);
                }
            };
            this.searchBrushFragmentSubcomponentFactoryProvider = new Provider<FindAndroidComponentsModule_SearchBrushFragment$SearchBrushFragmentSubcomponent.Factory>() { // from class: com.amazon.mp3.dagger.DaggerApplicationComponent.ApplicationComponentImpl.27
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javax.inject.Provider
                public FindAndroidComponentsModule_SearchBrushFragment$SearchBrushFragmentSubcomponent.Factory get() {
                    return new SearchBrushFragmentSubcomponentFactory(ApplicationComponentImpl.this.applicationComponentImpl);
                }
            };
            this.carModeSearchBrushFragmentSubcomponentFactoryProvider = new Provider<FindAndroidComponentsModule_CarModeSearchBrushFragment$CarModeSearchBrushFragmentSubcomponent.Factory>() { // from class: com.amazon.mp3.dagger.DaggerApplicationComponent.ApplicationComponentImpl.28
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javax.inject.Provider
                public FindAndroidComponentsModule_CarModeSearchBrushFragment$CarModeSearchBrushFragmentSubcomponent.Factory get() {
                    return new CarModeSearchBrushFragmentSubcomponentFactory(ApplicationComponentImpl.this.applicationComponentImpl);
                }
            };
            this.findLandingFragmentSubcomponentFactoryProvider = new Provider<FindAndroidComponentsModule_FindLandingFragment$FindLandingFragmentSubcomponent.Factory>() { // from class: com.amazon.mp3.dagger.DaggerApplicationComponent.ApplicationComponentImpl.29
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javax.inject.Provider
                public FindAndroidComponentsModule_FindLandingFragment$FindLandingFragmentSubcomponent.Factory get() {
                    return new FindLandingFragmentSubcomponentFactory(ApplicationComponentImpl.this.applicationComponentImpl);
                }
            };
            this.findSkyfireFragmentSubcomponentFactoryProvider = new Provider<FindAndroidComponentsModule_FindSkyfireFragment$FindSkyfireFragmentSubcomponent.Factory>() { // from class: com.amazon.mp3.dagger.DaggerApplicationComponent.ApplicationComponentImpl.30
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javax.inject.Provider
                public FindAndroidComponentsModule_FindSkyfireFragment$FindSkyfireFragmentSubcomponent.Factory get() {
                    return new FindSkyfireFragmentSubcomponentFactory(ApplicationComponentImpl.this.applicationComponentImpl);
                }
            };
            this.searchGalleryFragmentSubcomponentFactoryProvider = new Provider<FindAndroidComponentsModule_SearchGalleryFragment$SearchGalleryFragmentSubcomponent.Factory>() { // from class: com.amazon.mp3.dagger.DaggerApplicationComponent.ApplicationComponentImpl.31
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javax.inject.Provider
                public FindAndroidComponentsModule_SearchGalleryFragment$SearchGalleryFragmentSubcomponent.Factory get() {
                    return new SearchGalleryFragmentSubcomponentFactory(ApplicationComponentImpl.this.applicationComponentImpl);
                }
            };
            this.searchHostFragmentSubcomponentFactoryProvider = new Provider<FindAndroidComponentsModule_SearchHostFragment$SearchHostFragmentSubcomponent.Factory>() { // from class: com.amazon.mp3.dagger.DaggerApplicationComponent.ApplicationComponentImpl.32
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javax.inject.Provider
                public FindAndroidComponentsModule_SearchHostFragment$SearchHostFragmentSubcomponent.Factory get() {
                    return new SearchHostFragmentSubcomponentFactory(ApplicationComponentImpl.this.applicationComponentImpl);
                }
            };
            this.contactImportWebViewFragmentSubcomponentFactoryProvider = new Provider<ConnectAndroidComponentsModule_ContactImportWebViewFragment$ContactImportWebViewFragmentSubcomponent.Factory>() { // from class: com.amazon.mp3.dagger.DaggerApplicationComponent.ApplicationComponentImpl.33
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javax.inject.Provider
                public ConnectAndroidComponentsModule_ContactImportWebViewFragment$ContactImportWebViewFragmentSubcomponent.Factory get() {
                    return new ContactImportWebViewFragmentSubcomponentFactory(ApplicationComponentImpl.this.applicationComponentImpl);
                }
            };
            this.editCustomerProfileFragmentSubcomponentFactoryProvider = new Provider<ConnectAndroidComponentsModule_EditCustomerProfileDialogFragment$EditCustomerProfileFragmentSubcomponent.Factory>() { // from class: com.amazon.mp3.dagger.DaggerApplicationComponent.ApplicationComponentImpl.34
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javax.inject.Provider
                public ConnectAndroidComponentsModule_EditCustomerProfileDialogFragment$EditCustomerProfileFragmentSubcomponent.Factory get() {
                    return new EditCustomerProfileFragmentSubcomponentFactory(ApplicationComponentImpl.this.applicationComponentImpl);
                }
            };
            this.sharingFragmentSubcomponentFactoryProvider = new Provider<ShareAndroidComponentsModule_SharingFragment$SharingFragmentSubcomponent.Factory>() { // from class: com.amazon.mp3.dagger.DaggerApplicationComponent.ApplicationComponentImpl.35
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javax.inject.Provider
                public ShareAndroidComponentsModule_SharingFragment$SharingFragmentSubcomponent.Factory get() {
                    return new SharingFragmentSubcomponentFactory(ApplicationComponentImpl.this.applicationComponentImpl);
                }
            };
            this.sharingFragmentV2SubcomponentFactoryProvider = new Provider<ShareAndroidComponentsModule_SharingFragmentV2$SharingFragmentV2Subcomponent.Factory>() { // from class: com.amazon.mp3.dagger.DaggerApplicationComponent.ApplicationComponentImpl.36
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javax.inject.Provider
                public ShareAndroidComponentsModule_SharingFragmentV2$SharingFragmentV2Subcomponent.Factory get() {
                    return new SharingFragmentV2SubcomponentFactory(ApplicationComponentImpl.this.applicationComponentImpl);
                }
            };
            this.lyricsCardSelectionFragmentSubcomponentFactoryProvider = new Provider<LyricsSharingComponentsModule_LyricsCardSelectionFragment$LyricsCardSelectionFragmentSubcomponent.Factory>() { // from class: com.amazon.mp3.dagger.DaggerApplicationComponent.ApplicationComponentImpl.37
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javax.inject.Provider
                public LyricsSharingComponentsModule_LyricsCardSelectionFragment$LyricsCardSelectionFragmentSubcomponent.Factory get() {
                    return new LyricsCardSelectionFragmentSubcomponentFactory(ApplicationComponentImpl.this.applicationComponentImpl);
                }
            };
            this.liveEventDetailFragmentSubcomponentFactoryProvider = new Provider<ALPSAndroidComponentsModule_LiveEventDetailFragment$LiveEventDetailFragmentSubcomponent.Factory>() { // from class: com.amazon.mp3.dagger.DaggerApplicationComponent.ApplicationComponentImpl.38
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // javax.inject.Provider
                public ALPSAndroidComponentsModule_LiveEventDetailFragment$LiveEventDetailFragmentSubcomponent.Factory get() {
                    return new LiveEventDetailFragmentSubcomponentFactory(ApplicationComponentImpl.this.applicationComponentImpl);
                }
            };
            Provider<CarBluetoothDetector> provider = DoubleCheck.provider(CarDetectorModule_CarBluetoothDetectorProviderFactory.create(carDetectorModuleParam));
            this.carBluetoothDetectorProvider = provider;
            this.carDetectorProvider = DoubleCheck.provider(CarDetectorModule_CarDetectorProviderFactory.create(carDetectorModuleParam, provider));
            this.applicationProvider = InstanceFactory.create(applicationParam);
            this.provideAuthenticationProvider = PlatformProvidersModule_ProvideAuthenticationProviderFactory.create(platformProvidersModuleParam);
            this.provideCustomerMetadataProvider = PlatformProvidersModule_ProvideCustomerMetadataProviderFactory.create(platformProvidersModuleParam);
            this.provideDeviceInfoProvider = PlatformProvidersModule_ProvideDeviceInfoProviderFactory.create(platformProvidersModuleParam);
            this.provideBuildInfoProvider = PlatformProvidersModule_ProvideBuildInfoProviderFactory.create(platformProvidersModuleParam);
            this.provideMetricsProvider = PlatformProvidersModule_ProvideMetricsProviderFactory.create(platformProvidersModuleParam);
        }

        @Override // dagger.android.AndroidInjector
        public void inject(DaggerApplication arg0) {
            injectDaggerApplication(arg0);
        }

        @Override // com.amazon.mp3.dagger.ApplicationComponent
        public void inject(AmazonApplication application) {
            injectAmazonApplication(application);
        }

        @Override // com.amazon.mp3.dagger.ApplicationComponent
        public NowPlayingComponent.Factory nowPlayingComponent() {
            return new NowPlayingComponentFactory(this.applicationComponentImpl);
        }

        @Override // com.amazon.mp3.dagger.ApplicationComponent
        public void injectCarDetection(CarDetectionLaunchInitializationTask task) {
            injectCarDetectionLaunchInitializationTask(task);
        }

        private DaggerApplication injectDaggerApplication(DaggerApplication instance) {
            DaggerApplication_MembersInjector.injectAndroidInjector(instance, dispatchingAndroidInjectorOfObject());
            return instance;
        }

        private AmazonApplication injectAmazonApplication(AmazonApplication instance) {
            AmazonApplication_MembersInjector.injectMAndroidInjector(instance, dispatchingAndroidInjectorOfObject());
            return instance;
        }

        private CarDetectionLaunchInitializationTask injectCarDetectionLaunchInitializationTask(CarDetectionLaunchInitializationTask instance) {
            CarDetectionLaunchInitializationTask_MembersInjector.injectCarDetector(instance, this.carDetectorProvider.get());
            return instance;
        }
    }
}
