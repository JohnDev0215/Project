package com.amazon.mp3.find.dagger;

import android.content.Context;
import com.amazon.mp3.brush.converters.BrushPageGridConverter;
import com.amazon.mp3.dmsfcore.providers.DMSFContextMenuProvider;
import com.amazon.mp3.dmsfcore.providers.DMSFDownloadProvider;
import com.amazon.mp3.dmsfcore.providers.DMSFEnvironmentProvider;
import com.amazon.mp3.dmsfcore.providers.DMSFLibraryProvider;
import com.amazon.mp3.dmsfcore.providers.DMSFNavigationProvider;
import com.amazon.mp3.dmsfcore.providers.DMSFNotificationProvider;
import com.amazon.mp3.dmsfcore.providers.DMSFStorageProvider;
import com.amazon.mp3.dmsfcore.providers.DMSFStyleSheetProvider;
import com.amazon.mp3.dmsfcore.providers.DMSFUpsellProvider;
import com.amazon.mp3.dmsfcore.providers.DMSFUserSubscriptionProvider;
import com.amazon.mp3.explore.providers.ExploreAnimationProvider;
import com.amazon.mp3.explore.providers.ExploreCastingProvider;
import com.amazon.mp3.explore.providers.ExploreExplicitFilterProvider;
import com.amazon.mp3.explore.providers.ExplorePlaybackProvider;
import com.amazon.mp3.explore.providers.ExploreSkyFireConfigurationProvider;
import com.amazon.mp3.explore.providers.ExploreWakeWordProvider;
import com.amazon.mp3.find.skyfire.FindExternalActionProvider;
import com.amazon.mp3.find.skyfire.providers.FindChromeProvider;
import com.amazon.music.explore.providers.AnimationProvider;
import com.amazon.music.explore.providers.CastingProvider;
import com.amazon.music.explore.providers.ExplicitFilterProvider;
import com.amazon.music.explore.providers.WakeWordProvider;
import com.amazon.music.find.api.CachedRemoteSearchApi;
import com.amazon.music.find.api.EmptyRemoteSearchApi;
import com.amazon.music.find.api.LocalLibrarySearchApi;
import com.amazon.music.find.api.RemoteSearchApi;
import com.amazon.music.find.api.SearchApi;
import com.amazon.music.find.api.searchsuggestion.EmptySearchSuggestionApi;
import com.amazon.music.find.api.searchsuggestion.SearchSuggestionApi;
import com.amazon.music.find.api.searchsuggestion.SyncInstantSearchSuggestionApi;
import com.amazon.music.find.converter.BlendedSearchHistoryConverterBrushV2;
import com.amazon.music.find.converter.BrushV2Converter;
import com.amazon.music.find.converter.CarModeSearchResultConverterBrushV2;
import com.amazon.music.find.converter.FilterSelectionConverter;
import com.amazon.music.find.converter.LegacySearchHistoryConverterBrushV2;
import com.amazon.music.find.converter.PageGridViewModelConverter;
import com.amazon.music.find.converter.SearchResultConverterBrushV2;
import com.amazon.music.find.converter.SearchSuggestionConverterBrushV2;
import com.amazon.music.find.converter.SeeMoreResultConverterBrushV2;
import com.amazon.music.find.data.database.AppDatabase;
import com.amazon.music.find.data.database.CirrusDatabaseProvider;
import com.amazon.music.find.data.database.LibrarySearchDao;
import com.amazon.music.find.data.database.SearchEntityDao;
import com.amazon.music.find.data.database.SearchQueryDao;
import com.amazon.music.find.data.database.SqliteLibrarySearchDao;
import com.amazon.music.find.featuregating.FindFeatureConfigurationProvider;
import com.amazon.music.find.featuregating.FindFeatureConfigurationProviderDefault;
import com.amazon.music.find.featuregating.FindFeatureGating;
import com.amazon.music.find.metrics.SearchMetricsApi;
import com.amazon.music.find.metrics.SearchMetricsApiDefault;
import com.amazon.music.find.metrics.SearchMetricsService;
import com.amazon.music.find.metrics.SearchMetricsServiceDefault;
import com.amazon.music.find.model.SearchSuggestion;
import com.amazon.music.find.model.filter.SearchFilterSelections;
import com.amazon.music.find.model.search.GenericSearchResponse;
import com.amazon.music.find.model.search.SearchResponse;
import com.amazon.music.find.model.search.SeeMoreResponse;
import com.amazon.music.find.providers.CacheImageProvider;
import com.amazon.music.find.providers.SearchEntityFactory;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import com.amazon.music.find.service.CarModeConverterServiceBrushV2;
import com.amazon.music.find.service.CarModeSearchResultTransformer;
import com.amazon.music.find.service.ConverterService;
import com.amazon.music.find.service.ConverterServiceBrushV2;
import com.amazon.music.find.service.EntityTrackingService;
import com.amazon.music.find.service.EntityTrackingServiceDefault;
import com.amazon.music.find.service.LibraryStateService;
import com.amazon.music.find.service.LibraryStateServiceDefault;
import com.amazon.music.find.service.SearchHistoryService;
import com.amazon.music.find.service.SearchHistoryServiceDefault;
import com.amazon.music.find.service.SearchResultTransformerService;
import com.amazon.music.find.service.SearchService;
import com.amazon.music.find.service.SearchServiceDefault;
import com.amazon.music.find.service.SearchSuggestionService;
import com.amazon.music.find.service.SearchSuggestionServiceDefault;
import com.amazon.music.find.service.UIConfigurationService;
import com.amazon.music.find.service.UIConfigurationServiceDefault;
import com.amazon.music.find.service.UserSettingsService;
import com.amazon.music.find.service.UserSettingsServiceDefault;
import com.amazon.music.find.skyfire.providers.FindElementBindingProvider;
import com.amazon.music.find.skyfire.providers.FindNetworkInterceptorProvider;
import com.amazon.music.find.viewmodels.CarModeSearchViewModelFactory;
import com.amazon.music.find.viewmodels.FilterViewModelFactory;
import com.amazon.music.find.viewmodels.SearchHostViewModelFactory;
import com.amazon.music.find.viewmodels.SearchViewModelFactory;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.find.service.history.SearchHistoryItem;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.providers.AuthenticationProvider;
import com.amazon.music.platform.providers.BuildInfoProvider;
import com.amazon.music.platform.providers.ConfigurationProvider;
import com.amazon.music.platform.providers.CustomerMetadataProvider;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.platform.providers.MetricsProvider;
import com.amazon.music.platform.providers.WeblabProvider;
import com.amazon.music.search.SearchService;
import com.amazon.music.search.suggestion.instantsearch.InstantSearchSuggestionServiceConfiguration;
import com.amazon.music.skyfire.core.network.CapabilitiesInfo;
import com.amazon.music.skyfire.core.network.NetworkInterceptor;
import com.amazon.music.skyfire.core.performance.SkillLatencyTracker;
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
import com.amazon.music.skyfire.ui.providers.impl.DefaultSharingProvider;
import com.amazon.music.skyfire.ui.providers.impl.DefaultSkillLatencyTracker;
import com.amazon.music.skyfire.ui.providers.impl.DefaultSkyFireProvider;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: FindModule.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000È\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u0000 µ\u00012\u00020\u0001:\u0002µ\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\b\u0010\t\u001a\u00020\nH\u0007J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\r\u001a\u00020\u000e2\u000e\b\u0001\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0007J&\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0015H\u0007J\b\u0010\u0016\u001a\u00020\u0017H\u0007J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0007\u001a\u00020\bH\u0007J\b\u0010\u001a\u001a\u00020\u001bH\u0007J\b\u0010\u001c\u001a\u00020\u001dH\u0007JB\u0010\u001e\u001a\u00020\u000e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u00102\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u00102\u000e\b\u0001\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\f\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u0010H\u0007J\b\u0010&\u001a\u00020'H\u0007J \u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020'H\u0007J \u0010/\u001a\u0002002\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u0002042\u0006\u0010\u0014\u001a\u00020\u0015H\u0007J\u0010\u00105\u001a\u0002062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\b\u00107\u001a\u000208H\u0007J8\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020D2\u0006\u0010.\u001a\u00020'H\u0007J\u001e\u0010E\u001a\b\u0012\u0004\u0012\u00020G0F2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u0015H\u0007J&\u0010H\u001a\u00020I2\f\u0010J\u001a\b\u0012\u0004\u0012\u00020G0F2\u0006\u0010K\u001a\u00020L2\u0006\u0010\u0014\u001a\u00020\u0015H\u0007J\b\u0010M\u001a\u00020NH\u0007J\u0010\u0010O\u001a\u00020P2\u0006\u0010\u0007\u001a\u00020\bH\u0007J\b\u0010Q\u001a\u00020<H\u0007J\u0018\u0010R\u001a\u00020S2\u0006\u0010T\u001a\u00020U2\u0006\u0010\u0014\u001a\u00020\u0015H\u0007J\b\u0010V\u001a\u00020WH\u0007J\u0010\u0010X\u001a\u00020>2\u0006\u0010\u0007\u001a\u00020\bH\u0007J\b\u0010Y\u001a\u00020ZH\u0007J\u0010\u0010[\u001a\u00020@2\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010\\\u001a\u00020B2\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010]\u001a\u00020^2\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010_\u001a\u0002022\u0006\u0010`\u001a\u00020\u0006H\u0007J\u0010\u0010a\u001a\u00020\u00152\u0006\u0010\u0007\u001a\u00020\bH\u0007J&\u0010b\u001a\b\u0012\u0004\u0012\u00020 0\u00102\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0015H\u0007J0\u0010c\u001a\u00020d2\u0006\u0010e\u001a\u00020f2\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u0002042\u0006\u0010g\u001a\u00020h2\u0006\u0010\u0014\u001a\u00020\u0015H\u0007J \u0010i\u001a\u00020j2\u0006\u0010K\u001a\u00020L2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u00103\u001a\u000204H\u0007J\u0010\u0010k\u001a\u00020f2\u0006\u0010`\u001a\u00020\u0006H\u0007J.\u0010l\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010K\u001a\u00020LH\u0007J(\u0010m\u001a\u00020n2\u0006\u0010o\u001a\u00020p2\u0006\u0010q\u001a\u00020P2\u0006\u00103\u001a\u0002042\u0006\u0010\u0014\u001a\u00020\u0015H\u0007J\u0010\u0010r\u001a\u00020p2\u0006\u0010\u0007\u001a\u00020\bH\u0007J.\u0010s\u001a\b\u0012\u0004\u0012\u00020\"0\u00102\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u00103\u001a\u000204H\u0007J(\u0010t\u001a\u00020u2\u0006\u00101\u001a\u0002022\u0006\u0010v\u001a\u00020n2\u0006\u00103\u001a\u0002042\u0006\u0010\u0014\u001a\u00020\u0015H\u0007JR\u0010w\u001a\u00020x2\u0006\u0010J\u001a\u00020y2\u0006\u0010z\u001a\u00020u2\u0006\u0010{\u001a\u00020d2\b\b\u0001\u0010|\u001a\u00020\u000e2\u0006\u0010}\u001a\u00020~2\u0006\u0010K\u001a\u00020L2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u00103\u001a\u0002042\u0006\u0010\u007f\u001a\u00020SH\u0007J'\u0010\u0080\u0001\u001a\b\u0012\u0004\u0012\u00020%0\u00102\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0015H\u0007J\t\u0010\u0081\u0001\u001a\u00020DH\u0007J\n\u0010\u0082\u0001\u001a\u00030\u0083\u0001H\u0007J\u0012\u0010\u0084\u0001\u001a\u00030\u0085\u00012\u0006\u0010\u0007\u001a\u00020\bH\u0007Jh\u0010\u0086\u0001\u001a\u00030\u0087\u00012\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\u0088\u0001\u001a\u00030\u0089\u00012\b\u0010\u008a\u0001\u001a\u00030\u008b\u00012\b\u0010\u008c\u0001\u001a\u00030\u008d\u00012\b\u0010\u008e\u0001\u001a\u00030\u008f\u00012\u0006\u0010A\u001a\u00020B2\u0007\u0010\u0090\u0001\u001a\u00020\f2\u0007\u0010\u0091\u0001\u001a\u00020Z2\b\u0010\u0092\u0001\u001a\u00030\u0083\u00012\b\u0010\u0093\u0001\u001a\u00030\u0085\u0001H\u0007J\u0012\u0010\u0094\u0001\u001a\u00030\u0095\u00012\u0006\u0010\u0007\u001a\u00020\bH\u0007J\t\u0010\u0096\u0001\u001a\u00020-H\u0007J\"\u0010\u0097\u0001\u001a\u0002042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u00152\u0007\u0010\u0098\u0001\u001a\u00020NH\u0007J\n\u0010\u0099\u0001\u001a\u00030\u009a\u0001H\u0007J\u0019\u0010\u009b\u0001\u001a\u00020h2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u0015H\u0007J\t\u0010\u009c\u0001\u001a\u00020+H\u0007J\u0012\u0010\u009d\u0001\u001a\u00030\u009e\u00012\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0011\u0010\u009f\u0001\u001a\u00020y2\u0006\u0010\u0007\u001a\u00020\bH\u0007JM\u0010 \u0001\u001a\u00030¡\u00012\u0006\u0010J\u001a\u00020y2\b\b\u0001\u0010|\u001a\u00020\u000e2\u0007\u0010¢\u0001\u001a\u00020\u00172\u0006\u0010}\u001a\u00020~2\u0006\u0010K\u001a\u00020L2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u00103\u001a\u0002042\u0006\u0010{\u001a\u00020dH\u0007J\u0012\u0010£\u0001\u001a\u00030¤\u00012\u0006\u0010\u0007\u001a\u00020\bH\u0007J!\u0010¥\u0001\u001a\n\u0012\u0005\u0012\u00030§\u00010¦\u00012\u0006\u0010T\u001a\u00020U2\u0006\u0010\u0014\u001a\u00020\u0015H\u0007J%\u0010¨\u0001\u001a\u00020U2\b\u0010©\u0001\u001a\u00030¤\u00012\b\u0010ª\u0001\u001a\u00030«\u00012\u0006\u0010\u0014\u001a\u00020\u0015H\u0007J;\u0010¬\u0001\u001a\n\u0012\u0005\u0012\u00030§\u00010¦\u00012\u0006\u0010o\u001a\u00020^2\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010ª\u0001\u001a\u00030«\u00012\u0006\u00103\u001a\u0002042\u0006\u0010T\u001a\u00020UH\u0007J\n\u0010\u00ad\u0001\u001a\u00030«\u0001H\u0007J\n\u0010®\u0001\u001a\u00030¯\u0001H\u0007J\u001b\u0010°\u0001\u001a\u00020L2\b\u0010±\u0001\u001a\u00030¯\u00012\u0006\u0010\u0014\u001a\u00020\u0015H\u0007J?\u0010²\u0001\u001a\u00020~2\u0011\b\u0001\u0010³\u0001\u001a\n\u0012\u0005\u0012\u00030§\u00010¦\u00012\u0011\b\u0001\u0010´\u0001\u001a\n\u0012\u0005\u0012\u00030§\u00010¦\u00012\u0006\u00103\u001a\u0002042\u0006\u0010\u0014\u001a\u00020\u0015H\u0007¨\u0006¶\u0001"}, d2 = {"Lcom/amazon/mp3/find/dagger/FindModule;", "", "()V", "provideAnimationProvider", "Lcom/amazon/music/explore/providers/AnimationProvider;", "provideAppDatabase", "Lcom/amazon/music/find/data/database/AppDatabase;", "context", "Landroid/content/Context;", "provideCacheImageProvider", "Lcom/amazon/music/find/providers/CacheImageProvider;", "provideCapabilitiesInfo", "Lcom/amazon/music/skyfire/core/network/CapabilitiesInfo;", "provideCarModeConverterService", "Lcom/amazon/music/find/service/ConverterService;", "carModeSearchResultConverterBrushV2", "Lcom/amazon/music/find/converter/BrushV2Converter;", "Lcom/amazon/music/find/model/search/SearchResponse;", "provideCarModeSearchResultConverter", "cacheImageProvider", "searchFeaturesProvider", "Lcom/amazon/music/find/providers/SearchFeaturesProvider;", "provideCarModeTransformerService", "Lcom/amazon/music/find/service/SearchResultTransformerService;", "provideCastingProvider", "Lcom/amazon/music/explore/providers/CastingProvider;", "provideChromeProvider", "Lcom/amazon/music/skyfire/ui/providers/ChromeProvider;", "provideContextMenuProvider", "Lcom/amazon/music/skyfire/ui/providers/ContextMenuProvider;", "provideConverterService", "searchHistoryConverterBrushV2", "Lcom/amazon/music/platform/find/service/history/SearchHistoryItem;", "searchSuggestionConverterBrushV2", "Lcom/amazon/music/find/model/SearchSuggestion;", "searchResultConverterBrushV2", "seeMoreResultConverterBrushV2", "Lcom/amazon/music/find/model/search/SeeMoreResponse;", "provideDownloadProvider", "Lcom/amazon/music/skyfire/ui/providers/DownloadProvider;", "provideElementBindingProvider", "Lcom/amazon/music/skyfire/ui/providers/ElementBindingProvider;", "userSubscriptionProvider", "Lcom/amazon/music/skyfire/ui/providers/UserSubscriptionProvider;", "styleSheetProvider", "Lcom/amazon/music/skyfire/ui/providers/StyleSheetProvider;", "downloadProvider", "provideEntityTrackingService", "Lcom/amazon/music/find/service/EntityTrackingService;", "searchEntityDao", "Lcom/amazon/music/find/data/database/SearchEntityDao;", "uiConfigurationService", "Lcom/amazon/music/find/service/UIConfigurationService;", "provideEnvironmentProvider", "Lcom/amazon/music/skyfire/ui/providers/EnvironmentProvider;", "provideExplicitFilterProvider", "Lcom/amazon/music/explore/providers/ExplicitFilterProvider;", "provideExternalActionProvider", "Lcom/amazon/music/skyfire/ui/providers/ExternalActionProvider;", "libraryProvider", "Lcom/amazon/music/skyfire/ui/providers/LibraryProvider;", "navigationProvider", "Lcom/amazon/music/skyfire/ui/providers/NavigationProvider;", "notificationProvider", "Lcom/amazon/music/skyfire/ui/providers/NotificationProvider;", "playbackProvider", "Lcom/amazon/music/skyfire/ui/providers/PlaybackProvider;", "sharingProvider", "Lcom/amazon/music/skyfire/ui/providers/SharingProvider;", "provideFilterSelectionConverter", "Lcom/amazon/music/find/converter/PageGridViewModelConverter;", "Lcom/amazon/music/find/model/filter/SearchFilterSelections;", "provideFilterViewModelFactory", "Lcom/amazon/music/find/viewmodels/FilterViewModelFactory;", "pageGridConverter", "searchMetricsService", "Lcom/amazon/music/find/metrics/SearchMetricsService;", "provideFindFeatureConfigurationProvider", "Lcom/amazon/music/find/featuregating/FindFeatureConfigurationProvider;", "provideInstantSearchSuggestionConfigurationProvider", "Lcom/amazon/mp3/find/dagger/InstantSearchSuggestionServiceConfigurationProvider;", "provideLibraryProvider", "provideLibraryStateService", "Lcom/amazon/music/find/service/LibraryStateService;", "librarySearchDao", "Lcom/amazon/music/find/data/database/LibrarySearchDao;", "provideMetricsProvider", "Lcom/amazon/music/platform/providers/MetricsProvider;", "provideNavigationProvider", "provideNetworkInterceptor", "Lcom/amazon/music/skyfire/core/network/NetworkInterceptor;", "provideNotificationProvider", "providePlaybackProvider", "provideSearchConfigurationProvider", "Lcom/amazon/mp3/find/dagger/SearchServiceConfigurationProvider;", "provideSearchEntityDao", "appDatabase", "provideSearchFeaturesProvider", "provideSearchHistoryConverter", "provideSearchHistoryService", "Lcom/amazon/music/find/service/SearchHistoryService;", "searchQueryDao", "Lcom/amazon/music/find/data/database/SearchQueryDao;", "userSettingsService", "Lcom/amazon/music/find/service/UserSettingsService;", "provideSearchHostViewModelFactory", "Lcom/amazon/music/find/viewmodels/SearchHostViewModelFactory;", "provideSearchQueryDao", "provideSearchResultConverter", "provideSearchSuggestionApi", "Lcom/amazon/music/find/api/searchsuggestion/SearchSuggestionApi;", "configurationProvider", "Lcom/amazon/mp3/find/dagger/SearchSuggestionServiceConfigurationProvider;", "instantSearchConfigProvider", "provideSearchSuggestionConfigurationProvider", "provideSearchSuggestionConverter", "provideSearchSuggestionService", "Lcom/amazon/music/find/service/SearchSuggestionService;", "searchSuggestionApi", "provideSearchViewModelFactory", "Lcom/amazon/music/find/viewmodels/SearchViewModelFactory;", "Lcom/amazon/mp3/brush/converters/BrushPageGridConverter;", "searchSuggestionService", "searchHistoryService", "converterService", "searchService", "Lcom/amazon/music/find/service/SearchService;", "libraryStateService", "provideSeeMoreResultConverter", "provideSharingProvider", "provideSkillLatencyTracker", "Lcom/amazon/music/skyfire/core/performance/SkillLatencyTracker;", "provideSkyFireConfigurationProvider", "Lcom/amazon/music/skyfire/ui/providers/SkyFireConfigurationProvider;", "provideSkyFireProvider", "Lcom/amazon/music/skyfire/ui/providers/SkyFireProvider;", "authenticationProvider", "Lcom/amazon/music/platform/providers/AuthenticationProvider;", "customerMetadataProvider", "Lcom/amazon/music/platform/providers/CustomerMetadataProvider;", "deviceInfoProvider", "Lcom/amazon/music/platform/providers/DeviceInfoProvider;", "buildInfoProvider", "Lcom/amazon/music/platform/providers/BuildInfoProvider;", "capabilitiesInfo", "networkInterceptor", "skillLatencyTracker", "skyfireConfigurationProvider", "provideStorageProvider", "Lcom/amazon/music/skyfire/ui/providers/StorageProvider;", "provideStyleSheetProvider", "provideUIConfigurationService", "findFeatureConfigProvider", "provideUpsellProvider", "Lcom/amazon/music/skyfire/ui/providers/UpsellProvider;", "provideUserSettingsService", "provideUserSubscriptionProvider", "provideWakeWordProvider", "Lcom/amazon/music/explore/providers/WakeWordProvider;", "providerBrushPageGridConverter", "providesCarModeSearchViewModelFactory", "Lcom/amazon/music/find/viewmodels/CarModeSearchViewModelFactory;", "transformerService", "providesCirrusDatabaseProvider", "Lcom/amazon/music/find/data/database/CirrusDatabaseProvider;", "providesLibrarySearchApi", "Lcom/amazon/music/find/api/SearchApi;", "Lcom/amazon/music/find/model/search/GenericSearchResponse;", "providesLibrarySearchDao", "cirrusDatabaseProvider", "searchEntityFactory", "Lcom/amazon/music/find/providers/SearchEntityFactory;", "providesRemoteSearchApi", "providesSearchEntityFactory", "providesSearchMetricsApi", "Lcom/amazon/music/find/metrics/SearchMetricsApi;", "providesSearchMetricsService", "searchMetricsApi", "providesSearchService", "catalogSearchApi", "librarySearchApi", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FindModule {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: FindModule.kt */
    @Metadata(bv = {}, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b(\u0010)J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0003J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0003J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0003J\b\u0010\u000b\u001a\u00020\nH\u0003J\b\u0010\r\u001a\u00020\fH\u0003J\b\u0010\u000f\u001a\u00020\u000eH\u0003J\u0010\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u0002H\u0003J \u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0006H\u0003J6\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u0015H\u0003J\u001e\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u0006H\u0003J4\u0010\"\u001a\u00020!2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0014\u001a\u00020\u0006H\u0003J\b\u0010$\u001a\u00020#H\u0003J \u0010&\u001a\u00020\u00182\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00062\u0006\u0010%\u001a\u00020#H\u0003J\u0010\u0010'\u001a\u00020!2\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¨\u0006*"}, d2 = {"Lcom/amazon/mp3/find/dagger/FindModule$Companion;", "", "Landroid/content/Context;", "context", "Lcom/amazon/mp3/find/dagger/SearchServiceConfigurationProvider;", "createSearchConfigurationProvider", "Lcom/amazon/music/find/providers/SearchFeaturesProvider;", "createSearchFeaturesProvider", "Lcom/amazon/music/skyfire/core/network/CapabilitiesInfo;", "createCapabilitiesInfoProvider", "Lcom/amazon/music/skyfire/core/network/NetworkInterceptor;", "createNetworkInterceptorProvider", "Lcom/amazon/music/skyfire/core/performance/SkillLatencyTracker;", "createSkillLatencyTracker", "Lcom/amazon/music/find/providers/SearchEntityFactory;", "createSearchEntityFactory", "Lcom/amazon/music/find/data/database/CirrusDatabaseProvider;", "createCirrusDatabaseProvider", "cirrusDatabaseProvider", "searchEntityFactory", "searchFeaturesProvider", "Lcom/amazon/music/find/data/database/LibrarySearchDao;", "createLibrarySearchDao", "configurationProvider", "Lcom/amazon/music/find/service/UIConfigurationService;", "uiConfigurationService", "librarySearchDao", "Lcom/amazon/music/find/api/SearchApi;", "Lcom/amazon/music/find/model/search/GenericSearchResponse;", "createRemoteSearchApi", "createLibrarySearchApi", "catalogSearchApi", "librarySearchApi", "Lcom/amazon/music/find/service/SearchService;", "createSearchService", "Lcom/amazon/music/find/featuregating/FindFeatureConfigurationProvider;", "createFindFeatureConfigurationProvider", "findFeatureConfigProvider", "createUiConfigurationService", "initializeSearchService", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final SearchService initializeSearchService(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            SearchServiceConfigurationProvider searchServiceConfigurationProviderCreateSearchConfigurationProvider = createSearchConfigurationProvider(context);
            SearchFeaturesProvider searchFeaturesProviderCreateSearchFeaturesProvider = createSearchFeaturesProvider(context);
            SearchEntityFactory searchEntityFactoryCreateSearchEntityFactory = createSearchEntityFactory();
            CirrusDatabaseProvider cirrusDatabaseProviderCreateCirrusDatabaseProvider = createCirrusDatabaseProvider(context);
            UIConfigurationService uIConfigurationServiceCreateUiConfigurationService = createUiConfigurationService(context, searchFeaturesProviderCreateSearchFeaturesProvider, createFindFeatureConfigurationProvider());
            LibrarySearchDao librarySearchDaoCreateLibrarySearchDao = createLibrarySearchDao(cirrusDatabaseProviderCreateCirrusDatabaseProvider, searchEntityFactoryCreateSearchEntityFactory, searchFeaturesProviderCreateSearchFeaturesProvider);
            return createSearchService(createRemoteSearchApi(searchServiceConfigurationProviderCreateSearchConfigurationProvider, searchFeaturesProviderCreateSearchFeaturesProvider, searchEntityFactoryCreateSearchEntityFactory, uIConfigurationServiceCreateUiConfigurationService, librarySearchDaoCreateLibrarySearchDao), createLibrarySearchApi(librarySearchDaoCreateLibrarySearchDao, searchFeaturesProviderCreateSearchFeaturesProvider), uIConfigurationServiceCreateUiConfigurationService, searchFeaturesProviderCreateSearchFeaturesProvider);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @JvmStatic
        public final SearchServiceConfigurationProvider createSearchConfigurationProvider(Context context) {
            return new SearchServiceConfigurationProvider(context);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @JvmStatic
        public final SearchFeaturesProvider createSearchFeaturesProvider(Context context) {
            return new SearchFeaturesProviderDefault(context);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @JvmStatic
        public final CapabilitiesInfo createCapabilitiesInfoProvider(Context context) {
            return new SearchFeaturesProviderDefault(context);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @JvmStatic
        public final NetworkInterceptor createNetworkInterceptorProvider() {
            return new FindNetworkInterceptorProvider();
        }

        /* JADX INFO: Access modifiers changed from: private */
        @JvmStatic
        public final SkillLatencyTracker createSkillLatencyTracker() {
            return new DefaultSkillLatencyTracker();
        }

        /* JADX INFO: Access modifiers changed from: private */
        @JvmStatic
        public final SearchEntityFactory createSearchEntityFactory() {
            return new SearchEntityFactoryDefault();
        }

        /* JADX INFO: Access modifiers changed from: private */
        @JvmStatic
        public final CirrusDatabaseProvider createCirrusDatabaseProvider(Context context) {
            return new CirrusDatabaseProviderDefault(context);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @JvmStatic
        public final LibrarySearchDao createLibrarySearchDao(CirrusDatabaseProvider cirrusDatabaseProvider, SearchEntityFactory searchEntityFactory, SearchFeaturesProvider searchFeaturesProvider) {
            return new SqliteLibrarySearchDao(cirrusDatabaseProvider, searchEntityFactory, searchFeaturesProvider);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @JvmStatic
        public final SearchApi<GenericSearchResponse> createRemoteSearchApi(SearchServiceConfigurationProvider configurationProvider, SearchFeaturesProvider searchFeaturesProvider, SearchEntityFactory searchEntityFactory, UIConfigurationService uiConfigurationService, LibrarySearchDao librarySearchDao) {
            SearchService.Configuration searchConfiguration = configurationProvider.getSearchConfiguration();
            if (searchConfiguration != null) {
                if (searchFeaturesProvider.isSearchResultsCachingEnabled()) {
                    return new CachedRemoteSearchApi(searchConfiguration, searchFeaturesProvider, searchEntityFactory, uiConfigurationService, librarySearchDao);
                }
                return new RemoteSearchApi(searchConfiguration, searchFeaturesProvider, searchEntityFactory, uiConfigurationService, librarySearchDao);
            }
            return new EmptyRemoteSearchApi();
        }

        /* JADX INFO: Access modifiers changed from: private */
        @JvmStatic
        public final SearchApi<GenericSearchResponse> createLibrarySearchApi(LibrarySearchDao librarySearchDao, SearchFeaturesProvider searchFeaturesProvider) {
            return new LocalLibrarySearchApi(librarySearchDao, searchFeaturesProvider);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @JvmStatic
        public final com.amazon.music.find.service.SearchService createSearchService(SearchApi<GenericSearchResponse> catalogSearchApi, SearchApi<GenericSearchResponse> librarySearchApi, UIConfigurationService uiConfigurationService, SearchFeaturesProvider searchFeaturesProvider) {
            return new SearchServiceDefault(catalogSearchApi, librarySearchApi, uiConfigurationService, searchFeaturesProvider);
        }

        @JvmStatic
        private final FindFeatureConfigurationProvider createFindFeatureConfigurationProvider() {
            Providers providers = Providers.INSTANCE;
            return new FindFeatureConfigurationProviderDefault((ConfigurationProvider) providers.get(ConfigurationProvider.class), (WeblabProvider) providers.get(WeblabProvider.class), (FeatureGateProvider) providers.get(FeatureGateProvider.class));
        }

        /* JADX INFO: Access modifiers changed from: private */
        @JvmStatic
        public final UIConfigurationService createUiConfigurationService(Context context, SearchFeaturesProvider searchFeaturesProvider, FindFeatureConfigurationProvider findFeatureConfigProvider) {
            return new UIConfigurationServiceDefault(context, searchFeaturesProvider, findFeatureConfigProvider);
        }
    }

    public final AppDatabase provideAppDatabase(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return AppDatabase.INSTANCE.getDatabase(context);
    }

    public final SearchQueryDao provideSearchQueryDao(AppDatabase appDatabase) {
        Intrinsics.checkNotNullParameter(appDatabase, "appDatabase");
        return appDatabase.getSearchQueryDao();
    }

    public final SearchEntityDao provideSearchEntityDao(AppDatabase appDatabase) {
        Intrinsics.checkNotNullParameter(appDatabase, "appDatabase");
        return appDatabase.getSearchEntityDao();
    }

    public final UIConfigurationService provideUIConfigurationService(Context context, SearchFeaturesProvider searchFeaturesProvider, FindFeatureConfigurationProvider findFeatureConfigProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        Intrinsics.checkNotNullParameter(findFeatureConfigProvider, "findFeatureConfigProvider");
        return INSTANCE.createUiConfigurationService(context, searchFeaturesProvider, findFeatureConfigProvider);
    }

    public final FindFeatureConfigurationProvider provideFindFeatureConfigurationProvider() {
        Providers providers = Providers.INSTANCE;
        return new FindFeatureConfigurationProviderDefault((ConfigurationProvider) providers.get(ConfigurationProvider.class), (WeblabProvider) providers.get(WeblabProvider.class), (FeatureGateProvider) providers.get(FeatureGateProvider.class));
    }

    public final UserSettingsService provideUserSettingsService(Context context, SearchFeaturesProvider searchFeaturesProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        return new UserSettingsServiceDefault(context, searchFeaturesProvider);
    }

    public final SearchHistoryService provideSearchHistoryService(SearchQueryDao searchQueryDao, SearchEntityDao searchEntityDao, UIConfigurationService uiConfigurationService, UserSettingsService userSettingsService, SearchFeaturesProvider searchFeaturesProvider) {
        Intrinsics.checkNotNullParameter(searchQueryDao, "searchQueryDao");
        Intrinsics.checkNotNullParameter(searchEntityDao, "searchEntityDao");
        Intrinsics.checkNotNullParameter(uiConfigurationService, "uiConfigurationService");
        Intrinsics.checkNotNullParameter(userSettingsService, "userSettingsService");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        return new SearchHistoryServiceDefault(searchQueryDao, searchEntityDao, uiConfigurationService, userSettingsService, searchFeaturesProvider);
    }

    public final SearchSuggestionServiceConfigurationProvider provideSearchSuggestionConfigurationProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new SearchSuggestionServiceConfigurationProvider(context);
    }

    public final InstantSearchSuggestionServiceConfigurationProvider provideInstantSearchSuggestionConfigurationProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new InstantSearchSuggestionServiceConfigurationProvider(context);
    }

    public final SearchSuggestionApi provideSearchSuggestionApi(SearchSuggestionServiceConfigurationProvider configurationProvider, InstantSearchSuggestionServiceConfigurationProvider instantSearchConfigProvider, UIConfigurationService uiConfigurationService, SearchFeaturesProvider searchFeaturesProvider) {
        Intrinsics.checkNotNullParameter(configurationProvider, "configurationProvider");
        Intrinsics.checkNotNullParameter(instantSearchConfigProvider, "instantSearchConfigProvider");
        Intrinsics.checkNotNullParameter(uiConfigurationService, "uiConfigurationService");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        InstantSearchSuggestionServiceConfiguration instantSearchConfiguration = instantSearchConfigProvider.getInstantSearchConfiguration();
        SyncInstantSearchSuggestionApi syncInstantSearchSuggestionApi = instantSearchConfiguration == null ? null : new SyncInstantSearchSuggestionApi(instantSearchConfiguration, uiConfigurationService, searchFeaturesProvider);
        return syncInstantSearchSuggestionApi == null ? new EmptySearchSuggestionApi() : syncInstantSearchSuggestionApi;
    }

    public final EntityTrackingService provideEntityTrackingService(SearchEntityDao searchEntityDao, UIConfigurationService uiConfigurationService, SearchFeaturesProvider searchFeaturesProvider) {
        Intrinsics.checkNotNullParameter(searchEntityDao, "searchEntityDao");
        Intrinsics.checkNotNullParameter(uiConfigurationService, "uiConfigurationService");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        return new EntityTrackingServiceDefault(searchEntityDao, uiConfigurationService, searchFeaturesProvider);
    }

    public final SearchSuggestionService provideSearchSuggestionService(SearchEntityDao searchEntityDao, SearchSuggestionApi searchSuggestionApi, UIConfigurationService uiConfigurationService, SearchFeaturesProvider searchFeaturesProvider) {
        Intrinsics.checkNotNullParameter(searchEntityDao, "searchEntityDao");
        Intrinsics.checkNotNullParameter(searchSuggestionApi, "searchSuggestionApi");
        Intrinsics.checkNotNullParameter(uiConfigurationService, "uiConfigurationService");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        return new SearchSuggestionServiceDefault(searchEntityDao, searchSuggestionApi, uiConfigurationService, searchFeaturesProvider);
    }

    public final CacheImageProvider provideCacheImageProvider() {
        return new FindCacheImageProvider();
    }

    public final BrushV2Converter<SearchHistoryItem> provideSearchHistoryConverter(Context context, CacheImageProvider cacheImageProvider, SearchFeaturesProvider searchFeaturesProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(cacheImageProvider, "cacheImageProvider");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        if (FindFeatureGating.BLENDED_SEARCH_HISTORY.isEnabled((FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class))) {
            return new BlendedSearchHistoryConverterBrushV2(context, cacheImageProvider, searchFeaturesProvider);
        }
        return new LegacySearchHistoryConverterBrushV2(context, cacheImageProvider, searchFeaturesProvider);
    }

    public final BrushV2Converter<SearchSuggestion> provideSearchSuggestionConverter(Context context, CacheImageProvider cacheImageProvider, SearchFeaturesProvider searchFeaturesProvider, UIConfigurationService uiConfigurationService) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(cacheImageProvider, "cacheImageProvider");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        Intrinsics.checkNotNullParameter(uiConfigurationService, "uiConfigurationService");
        return new SearchSuggestionConverterBrushV2(context, cacheImageProvider, searchFeaturesProvider, uiConfigurationService);
    }

    public final BrushV2Converter<SearchResponse> provideSearchResultConverter(Context context, CacheImageProvider cacheImageProvider, SearchFeaturesProvider searchFeaturesProvider, SearchMetricsService searchMetricsService) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(cacheImageProvider, "cacheImageProvider");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        Intrinsics.checkNotNullParameter(searchMetricsService, "searchMetricsService");
        return new SearchResultConverterBrushV2(context, cacheImageProvider, searchFeaturesProvider, searchMetricsService);
    }

    public final BrushV2Converter<SearchResponse> provideCarModeSearchResultConverter(Context context, CacheImageProvider cacheImageProvider, SearchFeaturesProvider searchFeaturesProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(cacheImageProvider, "cacheImageProvider");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        return new CarModeSearchResultConverterBrushV2(context, cacheImageProvider, searchFeaturesProvider);
    }

    public final BrushV2Converter<SeeMoreResponse> provideSeeMoreResultConverter(Context context, CacheImageProvider cacheImageProvider, SearchFeaturesProvider searchFeaturesProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(cacheImageProvider, "cacheImageProvider");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        return new SeeMoreResultConverterBrushV2(context, cacheImageProvider, searchFeaturesProvider);
    }

    public final PageGridViewModelConverter<SearchFilterSelections> provideFilterSelectionConverter(Context context, SearchFeaturesProvider searchFeaturesProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        return new FilterSelectionConverter(context, searchFeaturesProvider);
    }

    public final ConverterService provideConverterService(BrushV2Converter<SearchHistoryItem> searchHistoryConverterBrushV2, BrushV2Converter<SearchSuggestion> searchSuggestionConverterBrushV2, BrushV2Converter<SearchResponse> searchResultConverterBrushV2, BrushV2Converter<SeeMoreResponse> seeMoreResultConverterBrushV2) {
        Intrinsics.checkNotNullParameter(searchHistoryConverterBrushV2, "searchHistoryConverterBrushV2");
        Intrinsics.checkNotNullParameter(searchSuggestionConverterBrushV2, "searchSuggestionConverterBrushV2");
        Intrinsics.checkNotNullParameter(searchResultConverterBrushV2, "searchResultConverterBrushV2");
        Intrinsics.checkNotNullParameter(seeMoreResultConverterBrushV2, "seeMoreResultConverterBrushV2");
        return new ConverterServiceBrushV2(searchHistoryConverterBrushV2, searchSuggestionConverterBrushV2, searchResultConverterBrushV2, seeMoreResultConverterBrushV2);
    }

    public final ConverterService provideCarModeConverterService(BrushV2Converter<SearchResponse> carModeSearchResultConverterBrushV2) {
        Intrinsics.checkNotNullParameter(carModeSearchResultConverterBrushV2, "carModeSearchResultConverterBrushV2");
        return new CarModeConverterServiceBrushV2(carModeSearchResultConverterBrushV2);
    }

    public final SearchResultTransformerService provideCarModeTransformerService() {
        return new CarModeSearchResultTransformer();
    }

    public final SearchServiceConfigurationProvider provideSearchConfigurationProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return INSTANCE.createSearchConfigurationProvider(context);
    }

    public final SearchFeaturesProvider provideSearchFeaturesProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return INSTANCE.createSearchFeaturesProvider(context);
    }

    public final SearchEntityFactory providesSearchEntityFactory() {
        return INSTANCE.createSearchEntityFactory();
    }

    public final SearchApi<GenericSearchResponse> providesRemoteSearchApi(SearchServiceConfigurationProvider configurationProvider, SearchFeaturesProvider searchFeaturesProvider, SearchEntityFactory searchEntityFactory, UIConfigurationService uiConfigurationService, LibrarySearchDao librarySearchDao) {
        Intrinsics.checkNotNullParameter(configurationProvider, "configurationProvider");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        Intrinsics.checkNotNullParameter(searchEntityFactory, "searchEntityFactory");
        Intrinsics.checkNotNullParameter(uiConfigurationService, "uiConfigurationService");
        Intrinsics.checkNotNullParameter(librarySearchDao, "librarySearchDao");
        return INSTANCE.createRemoteSearchApi(configurationProvider, searchFeaturesProvider, searchEntityFactory, uiConfigurationService, librarySearchDao);
    }

    public final CirrusDatabaseProvider providesCirrusDatabaseProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return INSTANCE.createCirrusDatabaseProvider(context);
    }

    public final LibrarySearchDao providesLibrarySearchDao(CirrusDatabaseProvider cirrusDatabaseProvider, SearchEntityFactory searchEntityFactory, SearchFeaturesProvider searchFeaturesProvider) {
        Intrinsics.checkNotNullParameter(cirrusDatabaseProvider, "cirrusDatabaseProvider");
        Intrinsics.checkNotNullParameter(searchEntityFactory, "searchEntityFactory");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        return INSTANCE.createLibrarySearchDao(cirrusDatabaseProvider, searchEntityFactory, searchFeaturesProvider);
    }

    public final SearchApi<GenericSearchResponse> providesLibrarySearchApi(LibrarySearchDao librarySearchDao, SearchFeaturesProvider searchFeaturesProvider) {
        Intrinsics.checkNotNullParameter(librarySearchDao, "librarySearchDao");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        return INSTANCE.createLibrarySearchApi(librarySearchDao, searchFeaturesProvider);
    }

    public final com.amazon.music.find.service.SearchService providesSearchService(SearchApi<GenericSearchResponse> catalogSearchApi, SearchApi<GenericSearchResponse> librarySearchApi, UIConfigurationService uiConfigurationService, SearchFeaturesProvider searchFeaturesProvider) {
        Intrinsics.checkNotNullParameter(catalogSearchApi, "catalogSearchApi");
        Intrinsics.checkNotNullParameter(librarySearchApi, "librarySearchApi");
        Intrinsics.checkNotNullParameter(uiConfigurationService, "uiConfigurationService");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        return INSTANCE.createSearchService(catalogSearchApi, librarySearchApi, uiConfigurationService, searchFeaturesProvider);
    }

    public final SearchMetricsApi providesSearchMetricsApi() {
        return new SearchMetricsApiDefault();
    }

    public final SearchMetricsService providesSearchMetricsService(SearchMetricsApi searchMetricsApi, SearchFeaturesProvider searchFeaturesProvider) {
        Intrinsics.checkNotNullParameter(searchMetricsApi, "searchMetricsApi");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        return new SearchMetricsServiceDefault(searchMetricsApi, searchFeaturesProvider);
    }

    public final BrushPageGridConverter providerBrushPageGridConverter(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new BrushPageGridConverter(context);
    }

    public final SearchViewModelFactory provideSearchViewModelFactory(BrushPageGridConverter pageGridConverter, SearchSuggestionService searchSuggestionService, SearchHistoryService searchHistoryService, ConverterService converterService, com.amazon.music.find.service.SearchService searchService, SearchMetricsService searchMetricsService, SearchFeaturesProvider searchFeaturesProvider, UIConfigurationService uiConfigurationService, LibraryStateService libraryStateService) {
        Intrinsics.checkNotNullParameter(pageGridConverter, "pageGridConverter");
        Intrinsics.checkNotNullParameter(searchSuggestionService, "searchSuggestionService");
        Intrinsics.checkNotNullParameter(searchHistoryService, "searchHistoryService");
        Intrinsics.checkNotNullParameter(converterService, "converterService");
        Intrinsics.checkNotNullParameter(searchService, "searchService");
        Intrinsics.checkNotNullParameter(searchMetricsService, "searchMetricsService");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        Intrinsics.checkNotNullParameter(uiConfigurationService, "uiConfigurationService");
        Intrinsics.checkNotNullParameter(libraryStateService, "libraryStateService");
        return new SearchViewModelFactory(pageGridConverter, searchSuggestionService, searchHistoryService, converterService, searchService, searchMetricsService, searchFeaturesProvider, uiConfigurationService, libraryStateService);
    }

    public final CarModeSearchViewModelFactory providesCarModeSearchViewModelFactory(BrushPageGridConverter pageGridConverter, ConverterService converterService, SearchResultTransformerService transformerService, com.amazon.music.find.service.SearchService searchService, SearchMetricsService searchMetricsService, SearchFeaturesProvider searchFeaturesProvider, UIConfigurationService uiConfigurationService, SearchHistoryService searchHistoryService) {
        Intrinsics.checkNotNullParameter(pageGridConverter, "pageGridConverter");
        Intrinsics.checkNotNullParameter(converterService, "converterService");
        Intrinsics.checkNotNullParameter(transformerService, "transformerService");
        Intrinsics.checkNotNullParameter(searchService, "searchService");
        Intrinsics.checkNotNullParameter(searchMetricsService, "searchMetricsService");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        Intrinsics.checkNotNullParameter(uiConfigurationService, "uiConfigurationService");
        Intrinsics.checkNotNullParameter(searchHistoryService, "searchHistoryService");
        return new CarModeSearchViewModelFactory(pageGridConverter, converterService, transformerService, searchService, searchMetricsService, searchFeaturesProvider, uiConfigurationService, searchHistoryService);
    }

    public final FilterViewModelFactory provideFilterViewModelFactory(PageGridViewModelConverter<SearchFilterSelections> pageGridConverter, SearchMetricsService searchMetricsService, SearchFeaturesProvider searchFeaturesProvider) {
        Intrinsics.checkNotNullParameter(pageGridConverter, "pageGridConverter");
        Intrinsics.checkNotNullParameter(searchMetricsService, "searchMetricsService");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        return new FilterViewModelFactory(pageGridConverter, searchMetricsService, searchFeaturesProvider);
    }

    public final LibraryStateService provideLibraryStateService(LibrarySearchDao librarySearchDao, SearchFeaturesProvider searchFeaturesProvider) {
        Intrinsics.checkNotNullParameter(librarySearchDao, "librarySearchDao");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        return new LibraryStateServiceDefault(librarySearchDao, searchFeaturesProvider);
    }

    public final SearchHostViewModelFactory provideSearchHostViewModelFactory(SearchMetricsService searchMetricsService, SearchFeaturesProvider searchFeaturesProvider, UIConfigurationService uiConfigurationService) {
        Intrinsics.checkNotNullParameter(searchMetricsService, "searchMetricsService");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        Intrinsics.checkNotNullParameter(uiConfigurationService, "uiConfigurationService");
        return new SearchHostViewModelFactory(searchMetricsService, searchFeaturesProvider, uiConfigurationService);
    }

    public final NavigationProvider provideNavigationProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new DMSFNavigationProvider(context);
    }

    public final ContextMenuProvider provideContextMenuProvider() {
        return new DMSFContextMenuProvider();
    }

    public final MetricsProvider provideMetricsProvider() {
        AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(MetricsProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        if (!(interfaceProvider instanceof MetricsProvider)) {
            interfaceProvider = null;
        }
        MetricsProvider metricsProvider = (MetricsProvider) interfaceProvider;
        Intrinsics.checkNotNull(metricsProvider);
        return metricsProvider;
    }

    public final ExplicitFilterProvider provideExplicitFilterProvider() {
        return new ExploreExplicitFilterProvider();
    }

    public final DownloadProvider provideDownloadProvider() {
        return new DMSFDownloadProvider();
    }

    public final StorageProvider provideStorageProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new DMSFStorageProvider(context);
    }

    public final EnvironmentProvider provideEnvironmentProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new DMSFEnvironmentProvider(context);
    }

    public final PlaybackProvider providePlaybackProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new ExplorePlaybackProvider(context);
    }

    public final WakeWordProvider provideWakeWordProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new ExploreWakeWordProvider(context);
    }

    public final UserSubscriptionProvider provideUserSubscriptionProvider() {
        return new DMSFUserSubscriptionProvider();
    }

    public final UpsellProvider provideUpsellProvider() {
        return new DMSFUpsellProvider();
    }

    public final CapabilitiesInfo provideCapabilitiesInfo(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return INSTANCE.createCapabilitiesInfoProvider(context);
    }

    public final NetworkInterceptor provideNetworkInterceptor() {
        return INSTANCE.createNetworkInterceptorProvider();
    }

    public final SkillLatencyTracker provideSkillLatencyTracker() {
        return INSTANCE.createSkillLatencyTracker();
    }

    public final CastingProvider provideCastingProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new ExploreCastingProvider(context);
    }

    public final AnimationProvider provideAnimationProvider() {
        return new ExploreAnimationProvider();
    }

    public final StyleSheetProvider provideStyleSheetProvider() {
        return new DMSFStyleSheetProvider();
    }

    public final ElementBindingProvider provideElementBindingProvider(UserSubscriptionProvider userSubscriptionProvider, StyleSheetProvider styleSheetProvider, DownloadProvider downloadProvider) {
        Intrinsics.checkNotNullParameter(userSubscriptionProvider, "userSubscriptionProvider");
        Intrinsics.checkNotNullParameter(styleSheetProvider, "styleSheetProvider");
        Intrinsics.checkNotNullParameter(downloadProvider, "downloadProvider");
        return new FindElementBindingProvider(userSubscriptionProvider, styleSheetProvider, downloadProvider);
    }

    public final LibraryProvider provideLibraryProvider() {
        return new DMSFLibraryProvider();
    }

    public final NotificationProvider provideNotificationProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new DMSFNotificationProvider(context);
    }

    public final SharingProvider provideSharingProvider() {
        return new DefaultSharingProvider();
    }

    public final ExternalActionProvider provideExternalActionProvider(LibraryProvider libraryProvider, NavigationProvider navigationProvider, NotificationProvider notificationProvider, PlaybackProvider playbackProvider, SharingProvider sharingProvider, DownloadProvider downloadProvider) {
        Intrinsics.checkNotNullParameter(libraryProvider, "libraryProvider");
        Intrinsics.checkNotNullParameter(navigationProvider, "navigationProvider");
        Intrinsics.checkNotNullParameter(notificationProvider, "notificationProvider");
        Intrinsics.checkNotNullParameter(playbackProvider, "playbackProvider");
        Intrinsics.checkNotNullParameter(sharingProvider, "sharingProvider");
        Intrinsics.checkNotNullParameter(downloadProvider, "downloadProvider");
        return new FindExternalActionProvider(libraryProvider, navigationProvider, notificationProvider, playbackProvider, sharingProvider, downloadProvider);
    }

    public final SkyFireConfigurationProvider provideSkyFireConfigurationProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new ExploreSkyFireConfigurationProvider(context);
    }

    public final ChromeProvider provideChromeProvider() {
        return new FindChromeProvider();
    }

    public final SkyFireProvider provideSkyFireProvider(Context context, AuthenticationProvider authenticationProvider, CustomerMetadataProvider customerMetadataProvider, DeviceInfoProvider deviceInfoProvider, BuildInfoProvider buildInfoProvider, PlaybackProvider playbackProvider, CapabilitiesInfo capabilitiesInfo, NetworkInterceptor networkInterceptor, SkillLatencyTracker skillLatencyTracker, SkyFireConfigurationProvider skyfireConfigurationProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(authenticationProvider, "authenticationProvider");
        Intrinsics.checkNotNullParameter(customerMetadataProvider, "customerMetadataProvider");
        Intrinsics.checkNotNullParameter(deviceInfoProvider, "deviceInfoProvider");
        Intrinsics.checkNotNullParameter(buildInfoProvider, "buildInfoProvider");
        Intrinsics.checkNotNullParameter(playbackProvider, "playbackProvider");
        Intrinsics.checkNotNullParameter(capabilitiesInfo, "capabilitiesInfo");
        Intrinsics.checkNotNullParameter(networkInterceptor, "networkInterceptor");
        Intrinsics.checkNotNullParameter(skillLatencyTracker, "skillLatencyTracker");
        Intrinsics.checkNotNullParameter(skyfireConfigurationProvider, "skyfireConfigurationProvider");
        return new DefaultSkyFireProvider(context, authenticationProvider, customerMetadataProvider, deviceInfoProvider, buildInfoProvider, playbackProvider, capabilitiesInfo, networkInterceptor, skillLatencyTracker, skyfireConfigurationProvider);
    }
}
