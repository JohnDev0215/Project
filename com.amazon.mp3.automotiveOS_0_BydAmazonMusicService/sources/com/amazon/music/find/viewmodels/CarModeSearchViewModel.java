package com.amazon.music.find.viewmodels;

import android.net.Uri;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.amazon.music.destination.FindDestination;
import com.amazon.music.find.R;
import com.amazon.music.find.metrics.SearchMetricsService;
import com.amazon.music.find.model.ContentMetadataPlaybackState;
import com.amazon.music.find.model.EntityMetaData;
import com.amazon.music.find.model.filter.SearchFilterSelections;
import com.amazon.music.find.model.search.ContentOwnershipItem;
import com.amazon.music.find.model.search.SearchRequest;
import com.amazon.music.find.model.search.SearchResponse;
import com.amazon.music.find.model.search.SeeMoreRequest;
import com.amazon.music.find.model.search.SeeMoreResponse;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import com.amazon.music.find.service.ConverterService;
import com.amazon.music.find.service.ExecutionContext;
import com.amazon.music.find.service.ExecutionContextDefault;
import com.amazon.music.find.service.SearchHistoryService;
import com.amazon.music.find.service.SearchResultTransformerService;
import com.amazon.music.find.service.SearchService;
import com.amazon.music.find.service.UIConfigurationService;
import com.amazon.music.find.utils.ContentUtils;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.platform.find.common.EntityType;
import com.amazon.music.views.library.converter.BaseContainerModelConverter;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.NavigationMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.models.HeaderModel;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.SimpleHorizontalTileModel;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.voice.launcher.CannotHandle;
import com.amazon.music.voice.launcher.LaunchTargetError;
import com.amazon.music.voice.launcher.LauncherTokens;
import com.amazon.musicensembleservice.brush.Page;
import com.amazon.musicensembleservice.brush.PageResponse;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.common.internal.ImagesContract;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.ExceptionsKt__ExceptionsKt;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: CarModeSearchViewModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000Î\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\b\u0016\u0018\u0000 «\u00012\u00020\u0001:\u0006©\u0001ª\u0001«\u0001BK\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012¢\u0006\u0002\u0010\u0013J\u0006\u0010<\u001a\u00020\u001bJ\u0014\u0010=\u001a\u0004\u0018\u00010>2\b\u0010?\u001a\u0004\u0018\u00010@H\u0002J\u0012\u0010A\u001a\u0004\u0018\u00010>2\u0006\u0010B\u001a\u00020CH\u0002J\u0018\u0010D\u001a\u00020\u001b2\u0006\u0010E\u001a\u00020.2\u0006\u0010F\u001a\u00020.H\u0002J\u000e\u0010G\u001a\u00020\u001b2\u0006\u0010H\u001a\u00020IJB\u0010J\u001a\u00020\u001b2\f\u0010K\u001a\b\u0012\u0004\u0012\u00020\u001f0L2\f\u0010M\u001a\b\u0012\u0004\u0012\u00020*0L2\u001c\u0010N\u001a\u0018\u0012\n\u0012\b\u0012\u0004\u0012\u00020P0L\u0012\u0004\u0012\u00020\u001b0Oj\u0002`QH\u0016J*\u0010R\u001a\u00020\u001b2\u0006\u0010S\u001a\u00020T2\u0006\u0010U\u001a\u00020*2\u0006\u0010V\u001a\u00020W2\b\u0010X\u001a\u0004\u0018\u00010*H\u0002JF\u0010Y\u001a\u00020\u001b2\u0006\u0010S\u001a\u00020T2\u0006\u0010U\u001a\u00020*2\u0006\u0010H\u001a\u00020I2\u0006\u0010Z\u001a\u00020[2\n\b\u0002\u0010\\\u001a\u0004\u0018\u00010*2\u0006\u0010]\u001a\u00020.2\b\u0010X\u001a\u0004\u0018\u00010*H\u0002J\n\u0010^\u001a\u0004\u0018\u00010_H\u0002J\u0006\u0010`\u001a\u00020aJ\u0010\u0010b\u001a\u00020c2\u0006\u00101\u001a\u00020*H\u0002J0\u0010d\u001a\b\u0012\u0004\u0012\u00020>0\u00152\u0006\u0010S\u001a\u00020T2\u0006\u0010U\u001a\u00020*2\u0006\u0010V\u001a\u00020W2\b\u0010X\u001a\u0004\u0018\u00010*H\u0002JJ\u0010e\u001a\b\u0012\u0004\u0012\u00020>0\u00152\u0006\u0010S\u001a\u00020T2\u0006\u0010U\u001a\u00020*2\u0006\u0010V\u001a\u00020W2\u0006\u0010H\u001a\u00020I2\b\u0010\\\u001a\u0004\u0018\u00010*2\u0006\u0010]\u001a\u00020.2\b\u0010X\u001a\u0004\u0018\u00010*H\u0002J\n\u0010f\u001a\u0004\u0018\u00010gH\u0016J\u0006\u0010h\u001a\u00020\u001bJP\u0010i\u001a\u00020\u001b2\u0006\u0010j\u001a\u00020k2\u0006\u0010l\u001a\u00020m28\u0010n\u001a4\u0012\u0015\u0012\u0013\u0018\u00010p¢\u0006\f\bq\u0012\b\br\u0012\u0004\b\b(s\u0012\u0013\u0012\u00110m¢\u0006\f\bq\u0012\b\br\u0012\u0004\b\b(l\u0012\u0004\u0012\u00020\u001b0oJ\u000e\u0010t\u001a\u00020\u001b2\u0006\u0010u\u001a\u00020vJ \u0010w\u001a\u00020.2\u0006\u0010x\u001a\u00020y2\u0006\u0010z\u001a\u00020[2\u0006\u0010{\u001a\u00020|H\u0002J\u0010\u0010}\u001a\u00020\u001b2\u0006\u0010S\u001a\u00020TH\u0014J\u0010\u0010~\u001a\u00020.2\u0006\u00101\u001a\u00020*H\u0002J\u0006\u0010\u007f\u001a\u00020.J\u0006\u0010]\u001a\u00020.J\t\u0010\u0080\u0001\u001a\u00020\u001bH\u0002J\t\u0010\u0081\u0001\u001a\u00020\u001bH\u0016J\t\u0010\u0082\u0001\u001a\u00020\u001bH\u0016J\"\u0010\u0083\u0001\u001a\t\u0012\u0005\u0012\u00030\u0084\u00010L2\u0010\u0010\u0085\u0001\u001a\u000b\u0012\u0005\u0012\u00030\u0084\u0001\u0018\u00010LH\u0002J\u000f\u0010\u0086\u0001\u001a\u00020\u001b2\u0006\u0010F\u001a\u00020.J\t\u0010\u0087\u0001\u001a\u00020\u001bH\u0002J\u001f\u0010\u0088\u0001\u001a\u00020\u001b2\r\u0010\u0089\u0001\u001a\b\u0012\u0004\u0012\u00020y0L2\u0007\u0010\u008a\u0001\u001a\u00020[J\u0012\u0010\u008b\u0001\u001a\u00020\u001b2\t\u0010\u008c\u0001\u001a\u0004\u0018\u00010*J\u0010\u0010\u008d\u0001\u001a\u00020\u001b2\u0007\u0010\u008e\u0001\u001a\u000200J\u0010\u0010\u008f\u0001\u001a\u00020\u001b2\u0007\u0010\u0090\u0001\u001a\u00020*J8\u0010\u0091\u0001\u001a\u00020\u001b2\b\u0010\u0092\u0001\u001a\u00030\u0093\u00012\b\u0010x\u001a\u0004\u0018\u00010y2\b\u0010\u0094\u0001\u001a\u00030\u0095\u00012\u000b\b\u0002\u0010\u0096\u0001\u001a\u0004\u0018\u00010[¢\u0006\u0003\u0010\u0097\u0001J\u0007\u0010\u0098\u0001\u001a\u00020\u001bJ\u001d\u0010\u0099\u0001\u001a\u00020\u001b2\t\u0010\u009a\u0001\u001a\u0004\u0018\u00010*2\t\u0010\u009b\u0001\u001a\u0004\u0018\u00010*J0\u0010\u009c\u0001\u001a\u00020\u001b2\n\u0010\u009d\u0001\u001a\u0005\u0018\u00010\u009e\u00012\b\u0010\u0094\u0001\u001a\u00030\u0095\u00012\u000b\b\u0002\u0010\u0096\u0001\u001a\u0004\u0018\u00010[¢\u0006\u0003\u0010\u009f\u0001J\u0010\u0010 \u0001\u001a\u00020\u001b2\u0007\u0010¡\u0001\u001a\u00020WJ\u0012\u0010¢\u0001\u001a\u00020\u001b2\u0007\u0010S\u001a\u00030£\u0001H\u0002J\u0019\u0010¤\u0001\u001a\u00020\u001b2\u0006\u0010S\u001a\u00020T2\u0006\u0010U\u001a\u00020*H\u0002J\t\u0010¥\u0001\u001a\u00020\u001bH\u0002J\t\u0010¦\u0001\u001a\u00020\u001bH\u0002J\u0012\u0010§\u0001\u001a\u00020\u001b2\u0007\u0010¨\u0001\u001a\u00020cH\u0002R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00158F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a8F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u001d\u0010\u001e\u001a\u0004\u0018\u00010\u001f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b \u0010!R\u0010\u0010$\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00160\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010(\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010+\u001a\b\u0012\u0004\u0012\u00020*0\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010,\u001a\b\u0012\u0004\u0012\u00020.0-X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010/\u001a\u0004\u0018\u000100X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u00101\u001a\u0004\u0018\u00010*8F¢\u0006\u0006\u001a\u0004\b2\u00103R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u00104\u001a\b\u0012\u0004\u0012\u00020*0\u00158F¢\u0006\u0006\u001a\u0004\b5\u0010\u0018R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u00106\u001a\u0004\u0018\u00010*8F¢\u0006\u0006\u001a\u0004\b7\u00103R\u0017\u00108\u001a\b\u0012\u0004\u0012\u00020.098F¢\u0006\u0006\u001a\u0004\b:\u0010;R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006¬\u0001"}, d2 = {"Lcom/amazon/music/find/viewmodels/CarModeSearchViewModel;", "Lcom/amazon/music/find/viewmodels/BasePageGridViewModel;", "pageGridConverter", "Lcom/amazon/music/views/library/converter/BaseContainerModelConverter;", "Lcom/amazon/musicensembleservice/brush/Page;", "converterService", "Lcom/amazon/music/find/service/ConverterService;", "transformerService", "Lcom/amazon/music/find/service/SearchResultTransformerService;", "searchService", "Lcom/amazon/music/find/service/SearchService;", "searchMetricsService", "Lcom/amazon/music/find/metrics/SearchMetricsService;", "searchFeaturesProvider", "Lcom/amazon/music/find/providers/SearchFeaturesProvider;", "uiConfigurationService", "Lcom/amazon/music/find/service/UIConfigurationService;", "searchHistoryService", "Lcom/amazon/music/find/service/SearchHistoryService;", "(Lcom/amazon/music/views/library/converter/BaseContainerModelConverter;Lcom/amazon/music/find/service/ConverterService;Lcom/amazon/music/find/service/SearchResultTransformerService;Lcom/amazon/music/find/service/SearchService;Lcom/amazon/music/find/metrics/SearchMetricsService;Lcom/amazon/music/find/providers/SearchFeaturesProvider;Lcom/amazon/music/find/service/UIConfigurationService;Lcom/amazon/music/find/service/SearchHistoryService;)V", "contentMetadataPlaybackStateUpdated", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/amazon/music/find/model/ContentMetadataPlaybackState;", "getContentMetadataPlaybackStateUpdated", "()Lio/reactivex/rxjava3/core/Observable;", "externalNavigationRequested", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "", "getExternalNavigationRequested", "()Lio/reactivex/rxjava3/subjects/PublishSubject;", "invalidateRecycleViewDelayedDuration", "", "getInvalidateRecycleViewDelayedDuration", "()Ljava/lang/Long;", "invalidateRecycleViewDelayedDuration$delegate", "Lkotlin/Lazy;", "mContentMetadataPlaybackState", "mContentMetadataPlaybackStateUpdated", "mData", "Lcom/amazon/music/find/viewmodels/CarModeSearchViewModel$CarModeSearchViewModelData;", "mExternalNavigationRequested", "mPageUri", "", "mSearchQueryTextUpdated", "mShouldShowLoadingSpinner", "Landroidx/lifecycle/MutableLiveData;", "", "mVoiceContentMetadataHandlerFactory", "Lcom/amazon/music/find/viewmodels/ContentMetadataHandlerFactory;", "pageUri", "getPageUri", "()Ljava/lang/String;", "searchQueryTextUpdated", "getSearchQueryTextUpdated", "searchTerm", "getSearchTerm", "shouldShowLoadingSpinner", "Landroidx/lifecycle/LiveData;", "getShouldShowLoadingSpinner", "()Landroidx/lifecycle/LiveData;", "clearTrackPlaybackState", "convertSearchResponse", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "searchResponse", "Lcom/amazon/music/find/model/search/SearchResponse;", "convertSeeMoreResponse", "seeMoreResponse", "Lcom/amazon/music/find/model/search/SeeMoreResponse;", "executeSearch", "currentIsOfflineMode", "forceNewQuery", "executeSeeMore", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "Lcom/amazon/music/platform/find/common/EntityType;", "fetchRequestState", "trackIds", "", "trackAsins", "callback", "Lkotlin/Function1;", "Lcom/amazon/music/find/model/search/ContentOwnershipItem;", "Lcom/amazon/music/find/model/search/ContentOwnershipCallback;", "fetchSearchResultsPageData", "executionContext", "Lcom/amazon/music/find/service/ExecutionContext;", SearchIntents.EXTRA_QUERY, "searchFilterSelections", "Lcom/amazon/music/find/model/filter/SearchFilterSelections;", PageUriUtils.QUERYMETADATA_QUERY_PARAM, "fetchSeeMorePageData", "pageIndex", "", "pageToken", "isOffline", "getHeaderModel", "Lcom/amazon/music/views/library/models/HeaderModel;", "getPlaybackPageType", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "getResumeContextDataFromModeAndPageUri", "Lcom/amazon/music/find/viewmodels/CarModeSearchViewModel$CarModeResumeContextData;", "getSearchResultsPageData", "getSeeMoreResultsPageData", "getUiPageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "goBack", "handleDeeplink", "uri", "Landroid/net/Uri;", "launcherTokens", "Lcom/amazon/music/voice/launcher/LauncherTokens;", "onComplete", "Lkotlin/Function2;", "Lcom/amazon/music/voice/launcher/LaunchTargetError;", "Lkotlin/ParameterName;", "name", "launchTargetError", "handleDestination", "findDestination", "Lcom/amazon/music/destination/FindDestination;", "handleEntityActionDeeplink", "contentMetadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "indexToStart", "action", "Lcom/amazon/music/find/viewmodels/SearchAction;", "handleOperationCompleted", "handlePlaybackDeeplink", "isLibrary", "markDeeplinkAsHandled", "onRefresh", "onResume", "processResultModels", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "childrenModels", "refreshSearch", "resumeFromContextData", "saveTrackPlaybackState", "contentMetadataList", "positionToStart", "setPageUri", "value", "setVoiceContentMetadataHandlerFactory", "contentMetadataHandlerFactory", "toggleFilterSelection", "filterId", "trackContentMetadataUiClickEvent", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "interactionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", "entityPos", "(Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;Lcom/amazon/music/views/library/metadata/ContentMetadata;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;Ljava/lang/Integer;)V", "trackFindHomeUiPageView", "trackGenericUiClickEvent", ImagesContract.URL, "blockRef", "trackNavigationMetadataUiClickEvent", "navigationMetadata", "Lcom/amazon/music/views/library/metadata/NavigationMetadata;", "(Lcom/amazon/music/views/library/metadata/NavigationMetadata;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;Ljava/lang/Integer;)V", "updateFilterSelections", "filterSelections", "updatePageDataForRefresh", "Lcom/amazon/music/find/service/ExecutionContextDefault;", "updateSearchHistory", "updateSearchTerm", "updateTrackPlaybackState", "updateViewModelDataFromResumeContext", "resumeContextData", "CarModeResumeContextData", "CarModeSearchViewModelData", "Companion", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class CarModeSearchViewModel extends BasePageGridViewModel {
    public static final int RESET_ORDINAL_VALUE = 0;
    private static final int SEARCH_PAGE_SIZE = 10;
    private static final int SEE_MORE_PAGE_SIZE = 5;
    public static final boolean SHOW_ORDINALS = true;
    public static final boolean SHOW_SPELL_CORRECTIONS = false;
    private static final String TAG = "SPL";
    private final ConverterService converterService;

    /* JADX INFO: renamed from: invalidateRecycleViewDelayedDuration$delegate, reason: from kotlin metadata */
    private final Lazy invalidateRecycleViewDelayedDuration;
    private ContentMetadataPlaybackState mContentMetadataPlaybackState;
    private final PublishSubject<ContentMetadataPlaybackState> mContentMetadataPlaybackStateUpdated;
    private final CarModeSearchViewModelData mData;
    private final PublishSubject<Unit> mExternalNavigationRequested;
    private String mPageUri;
    private final PublishSubject<String> mSearchQueryTextUpdated;
    private final MutableLiveData<Boolean> mShouldShowLoadingSpinner;
    private ContentMetadataHandlerFactory mVoiceContentMetadataHandlerFactory;
    private final BaseContainerModelConverter<Page> pageGridConverter;
    private final SearchFeaturesProvider searchFeaturesProvider;
    private final SearchHistoryService searchHistoryService;
    private final SearchMetricsService searchMetricsService;
    private final SearchService searchService;
    private final SearchResultTransformerService transformerService;
    private final UIConfigurationService uiConfigurationService;
    private static final Logger logger = LoggerFactory.getLogger(CarModeSearchViewModel.class.getName());

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updateSearchHistory$lambda-22, reason: not valid java name */
    public static final void m2077updateSearchHistory$lambda22(Unit unit) {
    }

    @Override // com.amazon.music.find.viewmodels.BaseViewModel
    public void fetchRequestState(List<Long> trackIds, List<String> trackAsins, Function1<? super List<ContentOwnershipItem>, Unit> callback) {
        Intrinsics.checkNotNullParameter(trackIds, "trackIds");
        Intrinsics.checkNotNullParameter(trackAsins, "trackAsins");
        Intrinsics.checkNotNullParameter(callback, "callback");
    }

    public CarModeSearchViewModel(BaseContainerModelConverter<Page> pageGridConverter, ConverterService converterService, SearchResultTransformerService transformerService, SearchService searchService, SearchMetricsService searchMetricsService, SearchFeaturesProvider searchFeaturesProvider, UIConfigurationService uiConfigurationService, SearchHistoryService searchHistoryService) {
        Intrinsics.checkNotNullParameter(pageGridConverter, "pageGridConverter");
        Intrinsics.checkNotNullParameter(converterService, "converterService");
        Intrinsics.checkNotNullParameter(transformerService, "transformerService");
        Intrinsics.checkNotNullParameter(searchService, "searchService");
        Intrinsics.checkNotNullParameter(searchMetricsService, "searchMetricsService");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        Intrinsics.checkNotNullParameter(uiConfigurationService, "uiConfigurationService");
        Intrinsics.checkNotNullParameter(searchHistoryService, "searchHistoryService");
        this.pageGridConverter = pageGridConverter;
        this.converterService = converterService;
        this.transformerService = transformerService;
        this.searchService = searchService;
        this.searchMetricsService = searchMetricsService;
        this.searchFeaturesProvider = searchFeaturesProvider;
        this.uiConfigurationService = uiConfigurationService;
        this.searchHistoryService = searchHistoryService;
        this.mShouldShowLoadingSpinner = new MutableLiveData<>();
        this.mData = new CarModeSearchViewModelData("", null, null, false, new SearchFilterSelections(uiConfigurationService.getFilterMetadata(false), new ArrayList()), null, new LinkedHashMap(), null, 164, null);
        PublishSubject<String> publishSubjectCreate = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate, "create()");
        this.mSearchQueryTextUpdated = publishSubjectCreate;
        PublishSubject<Unit> publishSubjectCreate2 = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate2, "create()");
        this.mExternalNavigationRequested = publishSubjectCreate2;
        PublishSubject<ContentMetadataPlaybackState> publishSubjectCreate3 = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate3, "create()");
        this.mContentMetadataPlaybackStateUpdated = publishSubjectCreate3;
        this.invalidateRecycleViewDelayedDuration = LazyKt__LazyJVMKt.lazy(new Function0<Long>() { // from class: com.amazon.music.find.viewmodels.CarModeSearchViewModel$invalidateRecycleViewDelayedDuration$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Long invoke() {
                return this.this$0.uiConfigurationService.getRecycleViewConfiguration().getInvalidateDelayedDurationInMilliseconds();
            }
        });
    }

    public final LiveData<Boolean> getShouldShowLoadingSpinner() {
        return this.mShouldShowLoadingSpinner;
    }

    /* JADX INFO: renamed from: getPageUri, reason: from getter */
    public final String getMPageUri() {
        return this.mPageUri;
    }

    public final String getSearchTerm() {
        return this.mData.getExecutedTerm();
    }

    public final Observable<String> getSearchQueryTextUpdated() {
        return this.mSearchQueryTextUpdated;
    }

    public final PublishSubject<Unit> getExternalNavigationRequested() {
        return this.mExternalNavigationRequested;
    }

    public final Observable<ContentMetadataPlaybackState> getContentMetadataPlaybackStateUpdated() {
        return this.mContentMetadataPlaybackStateUpdated;
    }

    public final Long getInvalidateRecycleViewDelayedDuration() {
        return (Long) this.invalidateRecycleViewDelayedDuration.getValue();
    }

    public final void setPageUri(String value) {
        logger.debug(Intrinsics.stringPlus("SPL::setPageUri. value=", value));
        this.mPageUri = value;
    }

    @Override // com.amazon.music.find.viewmodels.BasePageGridViewModel
    public PageType getUiPageType() {
        return PageType.CAR_MODE_BROWSE_SEARCH;
    }

    public final PlaybackPageType getPlaybackPageType() {
        return PlaybackPageType.CAR_MODE_BROWSE_SEARCH;
    }

    public final boolean isOffline() {
        return this.searchFeaturesProvider.isOffline();
    }

    public final boolean isLibrary() {
        return this.mData.getFilterSelections().hasMyMusicFilter();
    }

    public final void setVoiceContentMetadataHandlerFactory(ContentMetadataHandlerFactory contentMetadataHandlerFactory) {
        Intrinsics.checkNotNullParameter(contentMetadataHandlerFactory, "contentMetadataHandlerFactory");
        this.mVoiceContentMetadataHandlerFactory = contentMetadataHandlerFactory;
    }

    public final void handleDeeplink(Uri uri, LauncherTokens launcherTokens, Function2<? super LaunchTargetError, ? super LauncherTokens, Unit> onComplete) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(launcherTokens, "launcherTokens");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        String string = uri.toString();
        Intrinsics.checkNotNullExpressionValue(string, "uri.toString()");
        if (handlePlaybackDeeplink(string)) {
            onComplete.mo4invoke(null, launcherTokens);
            return;
        }
        onComplete.mo4invoke(new CannotHandle(((Object) CarModeSearchViewModel.class.getName()) + " is unable to handle App Interaction Uri - " + uri), launcherTokens);
    }

    private final boolean handlePlaybackDeeplink(String pageUri) {
        Integer contentPosition;
        SearchAction searchAction = PageUriUtils.getSearchAction(pageUri);
        if (searchAction == null) {
            return false;
        }
        if ((searchAction != SearchAction.PLAY && searchAction != SearchAction.PLAY_FULL) || (contentPosition = PageUriUtils.getContentPosition(pageUri)) == null) {
            return false;
        }
        int iIntValue = contentPosition.intValue();
        ContentMetadata contentMetadata = this.mData.getItemMap().get(Integer.valueOf(iIntValue));
        if (contentMetadata == null) {
            return false;
        }
        return handleEntityActionDeeplink(contentMetadata, iIntValue - 1, searchAction);
    }

    private final boolean handleEntityActionDeeplink(ContentMetadata contentMetadata, int indexToStart, SearchAction action) {
        if (contentMetadata instanceof TrackMetadata) {
            ContentMetadataHandlerFactory contentMetadataHandlerFactory = this.mVoiceContentMetadataHandlerFactory;
            if (contentMetadataHandlerFactory == null) {
                return false;
            }
            contentMetadataHandlerFactory.handle(CollectionsKt___CollectionsKt.toMutableList((Collection) this.mData.getItemMap().values()), indexToStart, action, InteractionType.VOICE);
            markDeeplinkAsHandled();
            return true;
        }
        ContentMetadataHandlerFactory contentMetadataHandlerFactory2 = this.mVoiceContentMetadataHandlerFactory;
        if (contentMetadataHandlerFactory2 == null) {
            return false;
        }
        contentMetadataHandlerFactory2.handle(contentMetadata, action, InteractionType.VOICE);
        markDeeplinkAsHandled();
        return true;
    }

    public final void handleDestination(FindDestination findDestination) {
        Intrinsics.checkNotNullParameter(findDestination, "findDestination");
        setPageUri(PageUriUtils.constructPageUri(findDestination));
        resumeFromContextData();
    }

    public final void goBack() {
        CarModeSearchViewModelData.reset$default(this.mData, false, true, false, 5, null);
        this.mExternalNavigationRequested.onNext(Unit.INSTANCE);
        SearchMetricsService searchMetricsService = this.searchMetricsService;
        boolean zIsLibrary = isLibrary();
        searchMetricsService.trackGoBackButtonUiClick(null, Boolean.valueOf(zIsLibrary), this.mData.getSeeMoreEntityType());
    }

    public final void refreshSearch(boolean forceNewQuery) {
        Unit unit;
        boolean zIsOffline = isOffline();
        if (this.mData.getExecutedTerm() != null) {
            updateSearchTerm();
            EntityType seeMoreEntityType = this.mData.getSeeMoreEntityType();
            if (seeMoreEntityType == null) {
                unit = null;
            } else {
                executeSeeMore(seeMoreEntityType);
                unit = Unit.INSTANCE;
            }
            if (unit == null) {
                executeSearch(zIsOffline, forceNewQuery);
            }
        }
        String str = this.mPageUri;
        if (str == null) {
            return;
        }
        this.searchMetricsService.trackExecuteSearchUiClick(null, Boolean.valueOf(isLibrary()), PageUriUtils.getInteractionType(str));
        markDeeplinkAsHandled();
    }

    private final void executeSearch(boolean currentIsOfflineMode, boolean forceNewQuery) {
        ExecutionContextDefault executionContextDefaultBuild = ExecutionContextDefault.Builder.withPageContext$default(new ExecutionContextDefault.Builder().withSearchOperation(SearchOperation.REFRESH_SEARCH), SearchExperienceMode.SEARCH_RESULT, Boolean.valueOf(currentIsOfflineMode), Boolean.valueOf(isLibrary()), null, 8, null).withUiPageView(forceNewQuery).build();
        executionContextDefaultBuild.markOperationStarted();
        if (forceNewQuery) {
            fetchSearchResultsPageData(executionContextDefaultBuild, StringsKt__StringsKt.trim((CharSequence) this.mData.getQueryString()).toString(), this.mData.getFilterSelections(), this.mData.getQueryMetadata());
        } else {
            updatePageDataForRefresh(executionContextDefaultBuild);
        }
    }

    private final void updatePageDataForRefresh(ExecutionContextDefault executionContext) {
        PageGridViewModel value = getMPageModel().getValue();
        if (value == null) {
            return;
        }
        updatePageData(executionContext, value);
    }

    public final void executeSeeMore(EntityType entityType) {
        Intrinsics.checkNotNullParameter(entityType, "entityType");
        boolean zIsOffline = isOffline();
        ExecutionContextDefault executionContextDefaultBuild = new ExecutionContextDefault.Builder().withSearchOperation(SearchOperation.EXECUTE_SEE_MORE).withPageContext(SearchExperienceMode.SEE_ALL, Boolean.valueOf(zIsOffline), Boolean.valueOf(isLibrary()), entityType).withUiPageView(true).build();
        executionContextDefaultBuild.markOperationStarted();
        String executedTerm = this.mData.getExecutedTerm();
        if (executedTerm != null) {
            if (StringsKt__StringsKt.trim((CharSequence) executedTerm).toString().length() > 0) {
                fetchSeeMorePageData(executionContextDefaultBuild, executedTerm, entityType, 0, null, zIsOffline, this.mData.getQueryMetadata());
                this.searchMetricsService.trackSeeMoreButtonUiClick(null, Boolean.valueOf(isLibrary()), null, InteractionType.VOICE);
            }
        }
    }

    static /* synthetic */ void fetchSeeMorePageData$default(CarModeSearchViewModel carModeSearchViewModel, ExecutionContext executionContext, String str, EntityType entityType, int i, String str2, boolean z, String str3, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: fetchSeeMorePageData");
        }
        carModeSearchViewModel.fetchSeeMorePageData(executionContext, str, entityType, i, (i2 & 16) != 0 ? null : str2, z, str3);
    }

    private final void fetchSeeMorePageData(final ExecutionContext executionContext, final String query, EntityType entityType, final int pageIndex, String pageToken, boolean isOffline, String queryMetadata) {
        setIsFetching(true);
        this.mData.updateQueryString(query);
        addDisposable(getSeeMoreResultsPageData(executionContext, query, this.mData.getFilterSelections(), entityType, pageToken, isOffline, queryMetadata).subscribe(new Consumer() { // from class: com.amazon.music.find.viewmodels.CarModeSearchViewModel$$ExternalSyntheticLambda7
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                CarModeSearchViewModel.m2073fetchSeeMorePageData$lambda8(executionContext, pageIndex, this, query, (PageGridViewModel) obj);
            }
        }, new Consumer() { // from class: com.amazon.music.find.viewmodels.CarModeSearchViewModel$$ExternalSyntheticLambda8
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                CarModeSearchViewModel.m2074fetchSeeMorePageData$lambda9(this.f$0, (Throwable) obj);
            }
        }, new Action() { // from class: com.amazon.music.find.viewmodels.CarModeSearchViewModel$$ExternalSyntheticLambda9
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                CarModeSearchViewModel.m2072fetchSeeMorePageData$lambda10(this.f$0);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchSeeMorePageData$lambda-8, reason: not valid java name */
    public static final void m2073fetchSeeMorePageData$lambda8(ExecutionContext executionContext, int i, CarModeSearchViewModel this$0, String query, PageGridViewModel pageGridViewModel) {
        Intrinsics.checkNotNullParameter(executionContext, "$executionContext");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(query, "$query");
        if (pageGridViewModel == null) {
            return;
        }
        executionContext.markConversionCompleted();
        if (i != 0) {
            PageGridViewModel value = this$0.getMPageModel().getValue();
            if (value != null) {
                this$0.updatePageData(executionContext, value.copy((1839 & 1) != 0 ? value.viewId : null, (1839 & 2) != 0 ? value.nestedModelViewId : null, (1839 & 4) != 0 ? value.title : null, (1839 & 8) != 0 ? value.subTitle : null, (1839 & 16) != 0 ? value.getModels() : CollectionsKt___CollectionsKt.plus((Collection) value.getModels(), (Iterable) value.getModels()), (1839 & 32) != 0 ? value.getTileType() : 0, (1839 & 64) != 0 ? value.pageIndex : i, (1839 & 128) != 0 ? value.nextPageToken : value.getNextPageToken(), (1839 & 256) != 0 ? value.filters : null, (1839 & 512) != 0 ? value.context : null, (1839 & 1024) != 0 ? value.header : null));
            }
        } else {
            this$0.updatePageData(executionContext, pageGridViewModel);
        }
        this$0.updateSearchHistory(executionContext, query);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchSeeMorePageData$lambda-9, reason: not valid java name */
    public static final void m2074fetchSeeMorePageData$lambda9(CarModeSearchViewModel this$0, Throwable error) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setIsFetching(false);
        Logger logger2 = logger;
        Intrinsics.checkNotNullExpressionValue(error, "error");
        logger2.error(Intrinsics.stringPlus("Error fetching see more page data: ", ExceptionsKt__ExceptionsKt.stackTraceToString(error)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchSeeMorePageData$lambda-10, reason: not valid java name */
    public static final void m2072fetchSeeMorePageData$lambda10(CarModeSearchViewModel this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setIsFetching(false);
    }

    private final Observable<PageGridViewModel> getSeeMoreResultsPageData(final ExecutionContext executionContext, String query, SearchFilterSelections searchFilterSelections, EntityType entityType, String pageToken, boolean isOffline, String queryMetadata) {
        SeeMoreRequest seeMoreRequest = new SeeMoreRequest(query, entityType, false, null, searchFilterSelections, pageToken, isOffline, queryMetadata, 5, 12, null);
        executionContext.markResponseStarted();
        Observable map = this.searchService.seeMore(executionContext, seeMoreRequest).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).map(new Function() { // from class: com.amazon.music.find.viewmodels.CarModeSearchViewModel$$ExternalSyntheticLambda3
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return CarModeSearchViewModel.m2076getSeeMoreResultsPageData$lambda11(executionContext, this, (SeeMoreResponse) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "searchService.seeMore(ex…ponse = it)\n            }");
        return map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getSeeMoreResultsPageData$lambda-11, reason: not valid java name */
    public static final PageGridViewModel m2076getSeeMoreResultsPageData$lambda11(ExecutionContext executionContext, CarModeSearchViewModel this$0, SeeMoreResponse it) {
        Intrinsics.checkNotNullParameter(executionContext, "$executionContext");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        executionContext.markResponseCompleted();
        executionContext.markConversionStarted();
        Intrinsics.checkNotNullExpressionValue(it, "it");
        return this$0.convertSeeMoreResponse(it);
    }

    private final PageGridViewModel convertSeeMoreResponse(SeeMoreResponse seeMoreResponse) {
        PageResponse pageResponseConvertSearchResults;
        if (seeMoreResponse.getSearchItems().isEmpty()) {
            pageResponseConvertSearchResults = this.converterService.convertEmptySearchResults(this.mData.getQueryString(), null);
        } else {
            pageResponseConvertSearchResults = this.converterService.convertSearchResults(this.transformerService.transform(seeMoreResponse), true, false);
        }
        List<com.amazon.music.views.library.models.base.BaseViewModel> childrenModels = this.pageGridConverter.getChildrenModels(pageResponseConvertSearchResults);
        return (PageGridViewModel) this.pageGridConverter.convert(pageResponseConvertSearchResults, processResultModels(childrenModels != null ? CollectionsKt___CollectionsKt.toMutableList((Collection) childrenModels) : null));
    }

    public final void saveTrackPlaybackState(List<? extends ContentMetadata> contentMetadataList, int positionToStart) {
        Intrinsics.checkNotNullParameter(contentMetadataList, "contentMetadataList");
        logger.debug("SPL::saveTrackPlaybackState. contentMetadataList: " + contentMetadataList + ", positionToStart: " + positionToStart);
        this.mContentMetadataPlaybackState = new ContentMetadataPlaybackState(contentMetadataList, positionToStart);
    }

    public final void clearTrackPlaybackState() {
        logger.debug("SPL::clearTrackPlaybackState");
        this.mContentMetadataPlaybackState = null;
    }

    private final Observable<PageGridViewModel> getSearchResultsPageData(final ExecutionContext executionContext, String query, SearchFilterSelections searchFilterSelections, String queryMetadata) {
        SearchRequest searchRequest = new SearchRequest(query, false, null, searchFilterSelections, false, queryMetadata, 10, 22, null);
        executionContext.markResponseStarted();
        Observable map = this.searchService.search(executionContext, searchRequest).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).map(new Function() { // from class: com.amazon.music.find.viewmodels.CarModeSearchViewModel$$ExternalSyntheticLambda6
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return CarModeSearchViewModel.m2075getSearchResultsPageData$lambda12(executionContext, this, (SearchResponse) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "searchService.search(exe…e = it)\n                }");
        return map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getSearchResultsPageData$lambda-12, reason: not valid java name */
    public static final PageGridViewModel m2075getSearchResultsPageData$lambda12(ExecutionContext executionContext, CarModeSearchViewModel this$0, SearchResponse searchResponse) {
        Intrinsics.checkNotNullParameter(executionContext, "$executionContext");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        executionContext.markResponseCompleted();
        executionContext.markConversionStarted();
        this$0.mData.setSearchResponse(searchResponse);
        return this$0.convertSearchResponse(searchResponse);
    }

    private final PageGridViewModel convertSearchResponse(SearchResponse searchResponse) {
        PageResponse pageResponseConvertSearchResults;
        if (searchResponse == null) {
            return null;
        }
        if (searchResponse.isNothingFound()) {
            pageResponseConvertSearchResults = this.converterService.convertEmptySearchResults(searchResponse.getOriginalQuery(), null);
        } else {
            SearchResponse searchResponseTransform = this.transformerService.transform(searchResponse);
            if (searchResponseTransform.isNothingFound()) {
                pageResponseConvertSearchResults = this.converterService.convertEmptySearchResults(searchResponse.getOriginalQuery(), null);
            } else {
                pageResponseConvertSearchResults = this.converterService.convertSearchResults(searchResponseTransform, true, false);
            }
        }
        return (PageGridViewModel) this.pageGridConverter.convert(pageResponseConvertSearchResults, processResultModels(this.pageGridConverter.getChildrenModels(pageResponseConvertSearchResults)));
    }

    private final HeaderModel getHeaderModel() {
        String searchTerm = getSearchTerm();
        if (searchTerm == null) {
            return null;
        }
        return new HeaderModel(null, this.searchFeaturesProvider.getLocalizedString(R.string.car_mode_results_for), FontStyleKey.PRIMARY, searchTerm, null, null, null, null, null, null, null, 2033, null);
    }

    private final List<com.amazon.music.views.library.models.base.BaseViewModel> processResultModels(List<? extends com.amazon.music.views.library.models.base.BaseViewModel> childrenModels) {
        List<com.amazon.music.views.library.models.base.BaseViewModel> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) this.converterService.processChildrenModels(childrenModels));
        for (com.amazon.music.views.library.models.base.BaseViewModel baseViewModel : mutableList) {
            SimpleHorizontalTileModel simpleHorizontalTileModel = baseViewModel instanceof SimpleHorizontalTileModel ? (SimpleHorizontalTileModel) baseViewModel : null;
            if (simpleHorizontalTileModel != null) {
                this.mData.updateItemMap(simpleHorizontalTileModel);
            }
        }
        HeaderModel headerModel = getHeaderModel();
        if (headerModel != null) {
            mutableList.add(0, headerModel);
        }
        return mutableList;
    }

    private final void fetchSearchResultsPageData(final ExecutionContext executionContext, final String query, SearchFilterSelections searchFilterSelections, String queryMetadata) {
        setIsFetching(true);
        this.mShouldShowLoadingSpinner.postValue(Boolean.TRUE);
        this.mData.updateQueryString(query);
        addDisposable(getSearchResultsPageData(executionContext, query, searchFilterSelections, queryMetadata).subscribe(new Consumer() { // from class: com.amazon.music.find.viewmodels.CarModeSearchViewModel$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                CarModeSearchViewModel.m2069fetchSearchResultsPageData$lambda19(executionContext, this, query, (PageGridViewModel) obj);
            }
        }, new Consumer() { // from class: com.amazon.music.find.viewmodels.CarModeSearchViewModel$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                CarModeSearchViewModel.m2070fetchSearchResultsPageData$lambda20(this.f$0, (Throwable) obj);
            }
        }, new Action() { // from class: com.amazon.music.find.viewmodels.CarModeSearchViewModel$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                CarModeSearchViewModel.m2071fetchSearchResultsPageData$lambda21(this.f$0);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchSearchResultsPageData$lambda-19, reason: not valid java name */
    public static final void m2069fetchSearchResultsPageData$lambda19(ExecutionContext executionContext, CarModeSearchViewModel this$0, String query, PageGridViewModel pageGridViewModel) {
        Intrinsics.checkNotNullParameter(executionContext, "$executionContext");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(query, "$query");
        if (pageGridViewModel == null) {
            return;
        }
        executionContext.markConversionCompleted();
        this$0.updatePageData(executionContext, pageGridViewModel);
        this$0.mShouldShowLoadingSpinner.postValue(Boolean.FALSE);
        this$0.updateSearchHistory(executionContext, query);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchSearchResultsPageData$lambda-20, reason: not valid java name */
    public static final void m2070fetchSearchResultsPageData$lambda20(CarModeSearchViewModel this$0, Throwable error) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setIsFetching(false);
        Logger logger2 = logger;
        Intrinsics.checkNotNullExpressionValue(error, "error");
        logger2.error(Intrinsics.stringPlus("Error fetching search result page data: ", ExceptionsKt__ExceptionsKt.stackTraceToString(error)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchSearchResultsPageData$lambda-21, reason: not valid java name */
    public static final void m2071fetchSearchResultsPageData$lambda21(CarModeSearchViewModel this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setIsFetching(false);
    }

    private final void updateSearchHistory(ExecutionContext executionContext, String query) {
        addDisposable(this.searchHistoryService.putRecentSearchQuery(executionContext, query, System.currentTimeMillis()).subscribeOn(Schedulers.io()).subscribe(new Consumer() { // from class: com.amazon.music.find.viewmodels.CarModeSearchViewModel$$ExternalSyntheticLambda4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                CarModeSearchViewModel.m2077updateSearchHistory$lambda22((Unit) obj);
            }
        }, new Consumer() { // from class: com.amazon.music.find.viewmodels.CarModeSearchViewModel$$ExternalSyntheticLambda5
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                CarModeSearchViewModel.m2078updateSearchHistory$lambda23((Throwable) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updateSearchHistory$lambda-23, reason: not valid java name */
    public static final void m2078updateSearchHistory$lambda23(Throwable th) {
        logger.error(Intrinsics.stringPlus("Error storing search query: ", th));
    }

    @Override // com.amazon.music.find.viewmodels.BaseViewModelLifecycle
    public void onResume() {
        resumeFromContextData();
    }

    private final void resumeFromContextData() {
        String str = this.mPageUri;
        if (str == null) {
            return;
        }
        CarModeResumeContextData resumeContextDataFromModeAndPageUri = getResumeContextDataFromModeAndPageUri(str);
        updateViewModelDataFromResumeContext(resumeContextDataFromModeAndPageUri);
        refreshSearch(resumeContextDataFromModeAndPageUri.getForceNewQuery());
    }

    private final CarModeResumeContextData getResumeContextDataFromModeAndPageUri(String pageUri) {
        String searchTerm = PageUriUtils.getSearchTerm(pageUri);
        boolean z = true;
        boolean z2 = !Intrinsics.areEqual(searchTerm, this.mData.getExecutedTerm());
        EntityType seeMoreEntityType = PageUriUtils.getSeeMoreEntityType(pageUri);
        boolean z3 = seeMoreEntityType != this.mData.getSeeMoreEntityType();
        SearchFilterSelections filterSelections = this.mData.getFilterSelections();
        SearchFilterSelections searchFilterSelections = new SearchFilterSelections(UIConfigurationService.DefaultImpls.getFilterMetadata$default(this.uiConfigurationService, false, 1, null), PageUriUtils.containsLibraryFilter(pageUri) ? CollectionsKt__CollectionsKt.mutableListOf(this.uiConfigurationService.getMyMusicFilterElement()) : new ArrayList());
        boolean z4 = !searchFilterSelections.selectionsEqual(filterSelections.getSelections());
        if (!z2 && !z4 && !z3) {
            z = false;
        }
        return new CarModeResumeContextData(z, z4, searchFilterSelections, z2, seeMoreEntityType, searchTerm);
    }

    private final void updateViewModelDataFromResumeContext(CarModeResumeContextData resumeContextData) {
        String searchTerm = resumeContextData.getSearchTerm();
        if (searchTerm != null && resumeContextData.getSearchTermChanged()) {
            this.mData.updateExecutedTerm(searchTerm);
            this.mData.updateQueryString(searchTerm);
        }
        if (resumeContextData.getFilterSelectionsChanged()) {
            this.mData.updateFilterSelections(resumeContextData.getFilterSelections());
        }
        this.mData.updateSeeMoreEntityType(resumeContextData.getSeeMoreEntityType());
    }

    @Override // com.amazon.music.find.viewmodels.BaseViewModelLifecycle
    public void onRefresh() {
        refreshSearch(false);
        updateTrackPlaybackState();
        clearTrackPlaybackState();
    }

    @Override // com.amazon.music.find.viewmodels.BaseViewModel
    protected void handleOperationCompleted(ExecutionContext executionContext) {
        Intrinsics.checkNotNullParameter(executionContext, "executionContext");
        logger.debug(Intrinsics.stringPlus("SPL::handleOperationCompleted. operation: ", executionContext.getSearchOperation()));
        executionContext.markOperationCompleted();
        executionContext.complete(this.searchMetricsService);
    }

    public final void trackGenericUiClickEvent(String url, String blockRef) {
        this.searchMetricsService.trackGenericUiClick(url, blockRef);
    }

    public final void trackFindHomeUiPageView() {
        this.searchMetricsService.trackFindHomeUiPageView(Boolean.valueOf(isLibrary()), null);
    }

    public static /* synthetic */ void trackContentMetadataUiClickEvent$default(CarModeSearchViewModel carModeSearchViewModel, ActionType actionType, ContentMetadata contentMetadata, InteractionType interactionType, Integer num, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: trackContentMetadataUiClickEvent");
        }
        if ((i & 8) != 0) {
            num = null;
        }
        carModeSearchViewModel.trackContentMetadataUiClickEvent(actionType, contentMetadata, interactionType, num);
    }

    public final void trackContentMetadataUiClickEvent(ActionType actionType, ContentMetadata contentMetadata, InteractionType interactionType, Integer entityPos) {
        Intrinsics.checkNotNullParameter(actionType, "actionType");
        Intrinsics.checkNotNullParameter(interactionType, "interactionType");
        if (contentMetadata == null) {
            return;
        }
        EntityMetaData entityInfoFromContentMetaData = this.searchFeaturesProvider.getEntityInfoFromContentMetaData(contentMetadata);
        String viewId = contentMetadata.getViewId();
        if (entityInfoFromContentMetaData == null) {
            return;
        }
        SearchMetricsService searchMetricsService = this.searchMetricsService;
        boolean zIsLibrary = isLibrary();
        SearchMetricsService.DefaultImpls.trackMetadataUiClickEvent$default(searchMetricsService, null, Boolean.valueOf(zIsLibrary), actionType, viewId, entityInfoFromContentMetaData.getEntityType(), entityInfoFromContentMetaData.getEntityIdType(), entityInfoFromContentMetaData.getEntityId(), entityPos, this.mData.getSeeMoreEntityType(), interactionType, null, 1024, null);
    }

    public static /* synthetic */ void trackNavigationMetadataUiClickEvent$default(CarModeSearchViewModel carModeSearchViewModel, NavigationMetadata navigationMetadata, InteractionType interactionType, Integer num, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: trackNavigationMetadataUiClickEvent");
        }
        if ((i & 4) != 0) {
            num = null;
        }
        carModeSearchViewModel.trackNavigationMetadataUiClickEvent(navigationMetadata, interactionType, num);
    }

    public final void trackNavigationMetadataUiClickEvent(NavigationMetadata navigationMetadata, InteractionType interactionType, Integer entityPos) {
        EntityMetaData entityInfoFromNavigationMetaData;
        Intrinsics.checkNotNullParameter(interactionType, "interactionType");
        if (navigationMetadata == null || (entityInfoFromNavigationMetaData = this.searchFeaturesProvider.getEntityInfoFromNavigationMetaData(navigationMetadata)) == null) {
            return;
        }
        entityInfoFromNavigationMetaData.getActionType();
    }

    public final void toggleFilterSelection(String filterId) {
        Intrinsics.checkNotNullParameter(filterId, "filterId");
        if (this.mData.getFilterSelections().containsSelection(filterId)) {
            this.mData.getFilterSelections().removeSelection(filterId);
        } else {
            this.mData.getFilterSelections().addSelection(filterId);
        }
        updateFilterSelections(this.mData.getFilterSelections());
    }

    public final void updateFilterSelections(SearchFilterSelections filterSelections) {
        Intrinsics.checkNotNullParameter(filterSelections, "filterSelections");
        boolean zIsOffline = isOffline();
        this.mData.updateFilterSelections(filterSelections);
        ExecutionContextDefault executionContextDefaultBuild = ExecutionContextDefault.Builder.withPageContext$default(new ExecutionContextDefault.Builder().withSearchOperation(SearchOperation.EXECUTE_SEARCH_FROM_APPLY_FILTER), SearchExperienceMode.SEARCH_RESULT, Boolean.valueOf(zIsOffline), Boolean.valueOf(isLibrary()), null, 8, null).withUiPageView(true).withPageLatencies().withSearchViewFocus(false).build();
        executionContextDefaultBuild.markOperationStarted();
        fetchSearchResultsPageData(executionContextDefaultBuild, StringsKt__StringsKt.trim((CharSequence) this.mData.getQueryString()).toString(), filterSelections, this.mData.getQueryMetadata());
    }

    private final void updateSearchTerm() {
        this.mSearchQueryTextUpdated.onNext(this.mData.getQueryString());
    }

    private final void updateTrackPlaybackState() {
        ContentMetadataPlaybackState contentMetadataPlaybackState = this.mContentMetadataPlaybackState;
        if (contentMetadataPlaybackState == null) {
            return;
        }
        this.mContentMetadataPlaybackStateUpdated.onNext(contentMetadataPlaybackState);
    }

    private final void markDeeplinkAsHandled() {
        setPageUri(null);
    }

    /* JADX INFO: compiled from: CarModeSearchViewModel.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b*\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001Bg\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0012J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u00101\u001a\u00020\bHÆ\u0003J\t\u00102\u001a\u00020\nHÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\fHÆ\u0003J\u0015\u00104\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000eHÆ\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\u0003HÆ\u0003Jm\u00106\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u00107\u001a\u00020\b2\b\u00108\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00109\u001a\u00020\u000fHÖ\u0001J$\u0010:\u001a\u00020;2\b\b\u0002\u0010<\u001a\u00020\b2\b\b\u0002\u0010\u0011\u001a\u00020\b2\b\b\u0002\u0010=\u001a\u00020\bJ\t\u0010>\u001a\u00020\u0003HÖ\u0001J\u0010\u0010?\u001a\u00020;2\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003J\u000e\u0010@\u001a\u00020;2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010A\u001a\u00020;2\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010B\u001a\u00020;2\u0006\u0010C\u001a\u00020DJ\u000e\u0010E\u001a\u00020;2\u0006\u0010\u0002\u001a\u00020\u0003J\u0010\u0010F\u001a\u00020;2\b\u0010G\u001a\u0004\u0018\u00010\fR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\u001b\"\u0004\b\u001c\u0010\u001dR&\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0014\"\u0004\b#\u0010\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0014\"\u0004\b%\u0010\u0016R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-¨\u0006H"}, d2 = {"Lcom/amazon/music/find/viewmodels/CarModeSearchViewModel$CarModeSearchViewModelData;", "", "queryString", "", "executedTerm", "searchResponse", "Lcom/amazon/music/find/model/search/SearchResponse;", "isOffline", "", "filterSelections", "Lcom/amazon/music/find/model/filter/SearchFilterSelections;", "seeMoreEntityType", "Lcom/amazon/music/platform/find/common/EntityType;", "itemMap", "", "", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", PageUriUtils.QUERYMETADATA_QUERY_PARAM, "(Ljava/lang/String;Ljava/lang/String;Lcom/amazon/music/find/model/search/SearchResponse;ZLcom/amazon/music/find/model/filter/SearchFilterSelections;Lcom/amazon/music/platform/find/common/EntityType;Ljava/util/Map;Ljava/lang/String;)V", "getExecutedTerm", "()Ljava/lang/String;", "setExecutedTerm", "(Ljava/lang/String;)V", "getFilterSelections", "()Lcom/amazon/music/find/model/filter/SearchFilterSelections;", "setFilterSelections", "(Lcom/amazon/music/find/model/filter/SearchFilterSelections;)V", "()Z", "setOffline", "(Z)V", "getItemMap", "()Ljava/util/Map;", "setItemMap", "(Ljava/util/Map;)V", "getQueryMetadata", "setQueryMetadata", "getQueryString", "setQueryString", "getSearchResponse", "()Lcom/amazon/music/find/model/search/SearchResponse;", "setSearchResponse", "(Lcom/amazon/music/find/model/search/SearchResponse;)V", "getSeeMoreEntityType", "()Lcom/amazon/music/platform/find/common/EntityType;", "setSeeMoreEntityType", "(Lcom/amazon/music/platform/find/common/EntityType;)V", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "reset", "", ContentUtils.KEY_SEARCH_QUERY, "all", "toString", "updateExecutedTerm", "updateFilterSelections", "updateIsOffline", "updateItemMap", "model", "Lcom/amazon/music/views/library/models/SimpleHorizontalTileModel;", "updateQueryString", "updateSeeMoreEntityType", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class CarModeSearchViewModelData {
        private String executedTerm;
        private SearchFilterSelections filterSelections;
        private boolean isOffline;
        private Map<Integer, ContentMetadata> itemMap;
        private String queryMetadata;
        private String queryString;
        private SearchResponse searchResponse;
        private EntityType seeMoreEntityType;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getQueryString() {
            return this.queryString;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getExecutedTerm() {
            return this.executedTerm;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final SearchResponse getSearchResponse() {
            return this.searchResponse;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final boolean getIsOffline() {
            return this.isOffline;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final SearchFilterSelections getFilterSelections() {
            return this.filterSelections;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final EntityType getSeeMoreEntityType() {
            return this.seeMoreEntityType;
        }

        public final Map<Integer, ContentMetadata> component7() {
            return this.itemMap;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getQueryMetadata() {
            return this.queryMetadata;
        }

        public final CarModeSearchViewModelData copy(String queryString, String executedTerm, SearchResponse searchResponse, boolean isOffline, SearchFilterSelections filterSelections, EntityType seeMoreEntityType, Map<Integer, ContentMetadata> itemMap, String queryMetadata) {
            Intrinsics.checkNotNullParameter(queryString, "queryString");
            Intrinsics.checkNotNullParameter(filterSelections, "filterSelections");
            Intrinsics.checkNotNullParameter(itemMap, "itemMap");
            return new CarModeSearchViewModelData(queryString, executedTerm, searchResponse, isOffline, filterSelections, seeMoreEntityType, itemMap, queryMetadata);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CarModeSearchViewModelData)) {
                return false;
            }
            CarModeSearchViewModelData carModeSearchViewModelData = (CarModeSearchViewModelData) other;
            return Intrinsics.areEqual(this.queryString, carModeSearchViewModelData.queryString) && Intrinsics.areEqual(this.executedTerm, carModeSearchViewModelData.executedTerm) && Intrinsics.areEqual(this.searchResponse, carModeSearchViewModelData.searchResponse) && this.isOffline == carModeSearchViewModelData.isOffline && Intrinsics.areEqual(this.filterSelections, carModeSearchViewModelData.filterSelections) && this.seeMoreEntityType == carModeSearchViewModelData.seeMoreEntityType && Intrinsics.areEqual(this.itemMap, carModeSearchViewModelData.itemMap) && Intrinsics.areEqual(this.queryMetadata, carModeSearchViewModelData.queryMetadata);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v17 */
        /* JADX WARN: Type inference failed for: r1v20 */
        /* JADX WARN: Type inference failed for: r1v7, types: [int] */
        public int hashCode() {
            int iHashCode = this.queryString.hashCode() * 31;
            String str = this.executedTerm;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            SearchResponse searchResponse = this.searchResponse;
            int iHashCode3 = (iHashCode2 + (searchResponse == null ? 0 : searchResponse.hashCode())) * 31;
            boolean z = this.isOffline;
            ?? r1 = z;
            if (z) {
                r1 = 1;
            }
            int iHashCode4 = (((iHashCode3 + r1) * 31) + this.filterSelections.hashCode()) * 31;
            EntityType entityType = this.seeMoreEntityType;
            int iHashCode5 = (((iHashCode4 + (entityType == null ? 0 : entityType.hashCode())) * 31) + this.itemMap.hashCode()) * 31;
            String str2 = this.queryMetadata;
            return iHashCode5 + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "CarModeSearchViewModelData(queryString=" + this.queryString + ", executedTerm=" + ((Object) this.executedTerm) + ", searchResponse=" + this.searchResponse + ", isOffline=" + this.isOffline + ", filterSelections=" + this.filterSelections + ", seeMoreEntityType=" + this.seeMoreEntityType + ", itemMap=" + this.itemMap + ", queryMetadata=" + ((Object) this.queryMetadata) + ')';
        }

        public CarModeSearchViewModelData(String queryString, String str, SearchResponse searchResponse, boolean z, SearchFilterSelections filterSelections, EntityType entityType, Map<Integer, ContentMetadata> itemMap, String str2) {
            Intrinsics.checkNotNullParameter(queryString, "queryString");
            Intrinsics.checkNotNullParameter(filterSelections, "filterSelections");
            Intrinsics.checkNotNullParameter(itemMap, "itemMap");
            this.queryString = queryString;
            this.executedTerm = str;
            this.searchResponse = searchResponse;
            this.isOffline = z;
            this.filterSelections = filterSelections;
            this.seeMoreEntityType = entityType;
            this.itemMap = itemMap;
            this.queryMetadata = str2;
        }

        public final String getQueryString() {
            return this.queryString;
        }

        public final void setQueryString(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.queryString = str;
        }

        public final String getExecutedTerm() {
            return this.executedTerm;
        }

        public final void setExecutedTerm(String str) {
            this.executedTerm = str;
        }

        public final SearchResponse getSearchResponse() {
            return this.searchResponse;
        }

        public final void setSearchResponse(SearchResponse searchResponse) {
            this.searchResponse = searchResponse;
        }

        public final boolean isOffline() {
            return this.isOffline;
        }

        public final void setOffline(boolean z) {
            this.isOffline = z;
        }

        public final SearchFilterSelections getFilterSelections() {
            return this.filterSelections;
        }

        public final void setFilterSelections(SearchFilterSelections searchFilterSelections) {
            Intrinsics.checkNotNullParameter(searchFilterSelections, "<set-?>");
            this.filterSelections = searchFilterSelections;
        }

        public final EntityType getSeeMoreEntityType() {
            return this.seeMoreEntityType;
        }

        public final void setSeeMoreEntityType(EntityType entityType) {
            this.seeMoreEntityType = entityType;
        }

        public /* synthetic */ CarModeSearchViewModelData(String str, String str2, SearchResponse searchResponse, boolean z, SearchFilterSelections searchFilterSelections, EntityType entityType, Map map, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : searchResponse, (i & 8) != 0 ? false : z, searchFilterSelections, (i & 32) != 0 ? null : entityType, (i & 64) != 0 ? new LinkedHashMap() : map, (i & 128) != 0 ? null : str3);
        }

        public final Map<Integer, ContentMetadata> getItemMap() {
            return this.itemMap;
        }

        public final void setItemMap(Map<Integer, ContentMetadata> map) {
            Intrinsics.checkNotNullParameter(map, "<set-?>");
            this.itemMap = map;
        }

        public final String getQueryMetadata() {
            return this.queryMetadata;
        }

        public final void setQueryMetadata(String str) {
            this.queryMetadata = str;
        }

        public static /* synthetic */ void reset$default(CarModeSearchViewModelData carModeSearchViewModelData, boolean z, boolean z2, boolean z3, int i, Object obj) {
            if ((i & 1) != 0) {
                z = false;
            }
            if ((i & 2) != 0) {
                z2 = false;
            }
            if ((i & 4) != 0) {
                z3 = false;
            }
            carModeSearchViewModelData.reset(z, z2, z3);
        }

        public final void reset(boolean searchQuery, boolean queryMetadata, boolean all) {
            if (all || searchQuery) {
                this.queryString = "";
                this.executedTerm = null;
            }
            if (all || searchQuery) {
                this.itemMap.clear();
            }
            if (all || queryMetadata) {
                this.queryMetadata = null;
            }
        }

        public final void updateSeeMoreEntityType(EntityType entityType) {
            this.seeMoreEntityType = entityType;
        }

        public final void updateIsOffline(boolean isOffline) {
            this.isOffline = isOffline;
        }

        public final void updateQueryString(String queryString) {
            Intrinsics.checkNotNullParameter(queryString, "queryString");
            this.queryString = queryString;
        }

        public final void updateExecutedTerm(String executedTerm) {
            this.executedTerm = executedTerm;
        }

        public final void updateFilterSelections(SearchFilterSelections filterSelections) {
            Intrinsics.checkNotNullParameter(filterSelections, "filterSelections");
            this.filterSelections = filterSelections;
        }

        public final void updateItemMap(SimpleHorizontalTileModel model) {
            Intrinsics.checkNotNullParameter(model, "model");
            Integer ordinal = model.getOrdinal();
            if (ordinal == null) {
                return;
            }
            getItemMap().put(Integer.valueOf(ordinal.intValue()), model.getMetadata());
        }
    }

    /* JADX INFO: compiled from: CarModeSearchViewModel.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u000bHÆ\u0003JI\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bHÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u00032\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\u000bHÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006#"}, d2 = {"Lcom/amazon/music/find/viewmodels/CarModeSearchViewModel$CarModeResumeContextData;", "", "forceNewQuery", "", "filterSelectionsChanged", "filterSelections", "Lcom/amazon/music/find/model/filter/SearchFilterSelections;", "searchTermChanged", "seeMoreEntityType", "Lcom/amazon/music/platform/find/common/EntityType;", "searchTerm", "", "(ZZLcom/amazon/music/find/model/filter/SearchFilterSelections;ZLcom/amazon/music/platform/find/common/EntityType;Ljava/lang/String;)V", "getFilterSelections", "()Lcom/amazon/music/find/model/filter/SearchFilterSelections;", "getFilterSelectionsChanged", "()Z", "getForceNewQuery", "getSearchTerm", "()Ljava/lang/String;", "getSearchTermChanged", "getSeeMoreEntityType", "()Lcom/amazon/music/platform/find/common/EntityType;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class CarModeResumeContextData {
        private final SearchFilterSelections filterSelections;
        private final boolean filterSelectionsChanged;
        private final boolean forceNewQuery;
        private final String searchTerm;
        private final boolean searchTermChanged;
        private final EntityType seeMoreEntityType;

        public static /* synthetic */ CarModeResumeContextData copy$default(CarModeResumeContextData carModeResumeContextData, boolean z, boolean z2, SearchFilterSelections searchFilterSelections, boolean z3, EntityType entityType, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                z = carModeResumeContextData.forceNewQuery;
            }
            if ((i & 2) != 0) {
                z2 = carModeResumeContextData.filterSelectionsChanged;
            }
            boolean z4 = z2;
            if ((i & 4) != 0) {
                searchFilterSelections = carModeResumeContextData.filterSelections;
            }
            SearchFilterSelections searchFilterSelections2 = searchFilterSelections;
            if ((i & 8) != 0) {
                z3 = carModeResumeContextData.searchTermChanged;
            }
            boolean z5 = z3;
            if ((i & 16) != 0) {
                entityType = carModeResumeContextData.seeMoreEntityType;
            }
            EntityType entityType2 = entityType;
            if ((i & 32) != 0) {
                str = carModeResumeContextData.searchTerm;
            }
            return carModeResumeContextData.copy(z, z4, searchFilterSelections2, z5, entityType2, str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getForceNewQuery() {
            return this.forceNewQuery;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getFilterSelectionsChanged() {
            return this.filterSelectionsChanged;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final SearchFilterSelections getFilterSelections() {
            return this.filterSelections;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final boolean getSearchTermChanged() {
            return this.searchTermChanged;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final EntityType getSeeMoreEntityType() {
            return this.seeMoreEntityType;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getSearchTerm() {
            return this.searchTerm;
        }

        public final CarModeResumeContextData copy(boolean forceNewQuery, boolean filterSelectionsChanged, SearchFilterSelections filterSelections, boolean searchTermChanged, EntityType seeMoreEntityType, String searchTerm) {
            Intrinsics.checkNotNullParameter(filterSelections, "filterSelections");
            return new CarModeResumeContextData(forceNewQuery, filterSelectionsChanged, filterSelections, searchTermChanged, seeMoreEntityType, searchTerm);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CarModeResumeContextData)) {
                return false;
            }
            CarModeResumeContextData carModeResumeContextData = (CarModeResumeContextData) other;
            return this.forceNewQuery == carModeResumeContextData.forceNewQuery && this.filterSelectionsChanged == carModeResumeContextData.filterSelectionsChanged && Intrinsics.areEqual(this.filterSelections, carModeResumeContextData.filterSelections) && this.searchTermChanged == carModeResumeContextData.searchTermChanged && this.seeMoreEntityType == carModeResumeContextData.seeMoreEntityType && Intrinsics.areEqual(this.searchTerm, carModeResumeContextData.searchTerm);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v12 */
        /* JADX WARN: Type inference failed for: r0v13 */
        /* JADX WARN: Type inference failed for: r1v0 */
        /* JADX WARN: Type inference failed for: r1v1, types: [int] */
        /* JADX WARN: Type inference failed for: r1v7 */
        /* JADX WARN: Type inference failed for: r2v1, types: [int] */
        /* JADX WARN: Type inference failed for: r2v8 */
        /* JADX WARN: Type inference failed for: r2v9 */
        public int hashCode() {
            boolean z = this.forceNewQuery;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            int i = r0 * 31;
            boolean z2 = this.filterSelectionsChanged;
            ?? r2 = z2;
            if (z2) {
                r2 = 1;
            }
            int iHashCode = (((i + r2) * 31) + this.filterSelections.hashCode()) * 31;
            boolean z3 = this.searchTermChanged;
            int i2 = (iHashCode + (z3 ? 1 : z3)) * 31;
            EntityType entityType = this.seeMoreEntityType;
            int iHashCode2 = (i2 + (entityType == null ? 0 : entityType.hashCode())) * 31;
            String str = this.searchTerm;
            return iHashCode2 + (str != null ? str.hashCode() : 0);
        }

        public String toString() {
            return "CarModeResumeContextData(forceNewQuery=" + this.forceNewQuery + ", filterSelectionsChanged=" + this.filterSelectionsChanged + ", filterSelections=" + this.filterSelections + ", searchTermChanged=" + this.searchTermChanged + ", seeMoreEntityType=" + this.seeMoreEntityType + ", searchTerm=" + ((Object) this.searchTerm) + ')';
        }

        public CarModeResumeContextData(boolean z, boolean z2, SearchFilterSelections filterSelections, boolean z3, EntityType entityType, String str) {
            Intrinsics.checkNotNullParameter(filterSelections, "filterSelections");
            this.forceNewQuery = z;
            this.filterSelectionsChanged = z2;
            this.filterSelections = filterSelections;
            this.searchTermChanged = z3;
            this.seeMoreEntityType = entityType;
            this.searchTerm = str;
        }

        public /* synthetic */ CarModeResumeContextData(boolean z, boolean z2, SearchFilterSelections searchFilterSelections, boolean z3, EntityType entityType, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(z, z2, searchFilterSelections, z3, (i & 16) != 0 ? null : entityType, (i & 32) != 0 ? null : str);
        }

        public final boolean getForceNewQuery() {
            return this.forceNewQuery;
        }

        public final boolean getFilterSelectionsChanged() {
            return this.filterSelectionsChanged;
        }

        public final SearchFilterSelections getFilterSelections() {
            return this.filterSelections;
        }

        public final boolean getSearchTermChanged() {
            return this.searchTermChanged;
        }

        public final EntityType getSeeMoreEntityType() {
            return this.seeMoreEntityType;
        }

        public final String getSearchTerm() {
            return this.searchTerm;
        }
    }
}
