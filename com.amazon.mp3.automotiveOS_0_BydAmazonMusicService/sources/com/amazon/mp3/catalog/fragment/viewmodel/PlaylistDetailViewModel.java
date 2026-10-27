package com.amazon.mp3.catalog.fragment.viewmodel;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import androidx.lifecycle.ViewModelKt;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.baseviews.model.configuration.TrackModelConfiguration;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtilFactory;
import com.amazon.mp3.catalog.fragment.datasource.repository.PageDataRepository;
import com.amazon.mp3.catalog.fragment.datasource.repository.PlaylistDetailDataRepository;
import com.amazon.mp3.catalog.fragment.viewmodel.BaseViewsRefinementManager;
import com.amazon.mp3.catalog.fragment.viewmodel.GroupDownloadStateModelProvider;
import com.amazon.mp3.curate.provider.CurateFeatureFlagProvider;
import com.amazon.mp3.curate.provider.CuratePlaylistProvider;
import com.amazon.mp3.detailpages.BaseViewsDragDropManager;
import com.amazon.mp3.detailpages.refinement.RefinementsMapping;
import com.amazon.mp3.fragment.viewmodel.OnFilterChangedListener;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.source.cirrus.dbutils.PlaylistRepositoryEventsKt;
import com.amazon.mp3.library.provider.source.cirrus.dbutils.RepositoryEventType;
import com.amazon.mp3.library.refinement.sort.DbSort;
import com.amazon.mp3.playback.state.PageStateManager;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.view.refinements.RefinementFilterType;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.models.CollectionDetailHeaderModel;
import com.amazon.music.views.library.models.DownloadStateModel;
import com.amazon.music.views.library.models.EmptyStateModel;
import com.amazon.music.views.library.models.ExposedRefinementListModel;
import com.amazon.music.views.library.models.FilterItemModel;
import com.amazon.music.views.library.models.HighlightableMessageModel;
import com.amazon.music.views.library.models.MultiStateButtonModel;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.PageHeaderModel;
import com.amazon.music.views.library.models.StoryPageHeaderModel;
import com.amazon.music.views.library.models.TableRowItemModel;
import com.amazon.music.views.library.models.TextRowItemModel;
import com.amazon.music.views.library.models.VisualRowItemModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.base.Hideable;
import com.amazon.music.views.library.providers.ContentEnabilityProvider;
import com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter;
import com.amazon.music.views.library.utils.BaseViewContainerModelUtil;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsJvmKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: PlaylistDetailViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u008e\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006*\u0002\u0087\u0001\u0018\u0000 \u009a\u00012\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005:\u0002\u009a\u0001B:\u0012\b\u0010\u0094\u0001\u001a\u00030\u0093\u0001\u0012\b\u0010\u0096\u0001\u001a\u00030\u0095\u0001\u0012\u0006\u0010b\u001a\u000202\u0012\t\b\u0002\u0010\u0097\u0001\u001a\u000202\u0012\b\u0010d\u001a\u0004\u0018\u00010@¢\u0006\u0006\b\u0098\u0001\u0010\u0099\u0001J\n\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0002J\n\u0010\t\u001a\u0004\u0018\u00010\bH\u0002J\b\u0010\u000b\u001a\u00020\nH\u0002J\u001e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002J\u0012\u0010\u0012\u001a\u00020\n2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0002J\u001c\u0010\u0015\u001a\u00020\n2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u0002J\u000e\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0016J\u0014\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0010J\b\u0010\u001d\u001a\u0004\u0018\u00010\u001cJ\u0006\u0010\u001e\u001a\u00020\nJ\u0012\u0010\u001f\u001a\u00020\n2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0014J$\u0010$\u001a\u00020\n2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010!\u001a\u00020 2\b\u0010#\u001a\u0004\u0018\u00010\"H\u0014J/\u0010'\u001a\u00020\n2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\r0\f2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0094@ø\u0001\u0000¢\u0006\u0004\b'\u0010(J\u0012\u0010)\u001a\u00020\n2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0014J\u0010\u0010*\u001a\u00020\n2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010J\u0014\u0010,\u001a\u0004\u0018\u00010+2\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0010J\b\u0010-\u001a\u0004\u0018\u00010\u001cJ\u000f\u0010/\u001a\u0004\u0018\u00010.¢\u0006\u0004\b/\u00100J\u000f\u00101\u001a\u0004\u0018\u00010.¢\u0006\u0004\b1\u00100J\u0006\u00103\u001a\u000202J\u0006\u00104\u001a\u000202J\u000e\u00106\u001a\u00020\n2\u0006\u00105\u001a\u000202J\n\u00108\u001a\u0004\u0018\u000107H\u0016J\u001a\u00109\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\f2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010J\u0019\u0010:\u001a\u0004\u0018\u00010.2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b:\u0010;J\u0019\u0010<\u001a\u0004\u0018\u00010.2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b<\u0010;J\u0010\u0010?\u001a\u0004\u0018\u00010>2\u0006\u0010=\u001a\u00020\rJ\u0010\u0010B\u001a\u00020\n2\b\u0010A\u001a\u0004\u0018\u00010@J\u0006\u0010C\u001a\u00020\nJ\u0014\u0010D\u001a\u0004\u0018\u00010>2\b\u0010A\u001a\u0004\u0018\u00010@H\u0016J\u0010\u0010G\u001a\u00020\n2\b\u0010F\u001a\u0004\u0018\u00010EJ\u0018\u0010J\u001a\u00020\n2\u0006\u0010H\u001a\u00020.2\u0006\u0010I\u001a\u00020.H\u0016J\u0018\u0010M\u001a\u0002022\u0006\u0010=\u001a\u00020K2\u0006\u0010L\u001a\u00020KH\u0016J \u0010Q\u001a\u00020\n2\u0006\u0010N\u001a\u00020.2\u0006\u0010O\u001a\u00020.2\u0006\u0010P\u001a\u00020\u0010H\u0016J\b\u0010R\u001a\u00020\nH\u0014J\u0010\u0010S\u001a\u00020\n2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0010J\u0006\u0010T\u001a\u00020\nJ\u001a\u0010V\u001a\u00020\n2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010U\u001a\u000202J\u0014\u0010S\u001a\u00020\n2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\fJ\u0016\u0010X\u001a\u00020\n2\u000e\u0010W\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fJ\u0006\u0010Y\u001a\u000202J\u0006\u0010Z\u001a\u00020\nJ\u000e\u0010]\u001a\u00020\n2\u0006\u0010\\\u001a\u00020[J\u0015\u0010^\u001a\u0004\u0018\u00010.H\u0086@ø\u0001\u0000¢\u0006\u0004\b^\u0010_J\b\u0010a\u001a\u0004\u0018\u00010`R\u0014\u0010b\u001a\u0002028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bb\u0010cR\u0016\u0010d\u001a\u0004\u0018\u00010@8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bd\u0010eR\u0016\u0010g\u001a\u0004\u0018\u00010f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bg\u0010hR\u0014\u0010j\u001a\u00020i8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bj\u0010kR\u0018\u0010l\u001a\u0004\u0018\u0001078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bl\u0010mR\u0018\u0010o\u001a\u0004\u0018\u00010n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bo\u0010pR\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u00168\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0017\u0010qR \u0010s\u001a\b\u0012\u0004\u0012\u00020r0\f8\u0010X\u0090\u0004¢\u0006\f\n\u0004\bs\u0010t\u001a\u0004\bu\u0010vR$\u0010x\u001a\u0004\u0018\u00010w8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bx\u0010y\u001a\u0004\bz\u0010{\"\u0004\b|\u0010}R\u001f\u0010\u0083\u0001\u001a\u00020~8BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b\u007f\u0010\u0080\u0001\u001a\u0006\b\u0081\u0001\u0010\u0082\u0001R\u0018\u0010\u0085\u0001\u001a\u00030\u0084\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0085\u0001\u0010\u0086\u0001R\u0018\u0010\u0088\u0001\u001a\u00030\u0087\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0088\u0001\u0010\u0089\u0001R\u001d\u0010\u008b\u0001\u001a\u00030\u008a\u00018\u0006¢\u0006\u0010\n\u0006\b\u008b\u0001\u0010\u008c\u0001\u001a\u0006\b\u008d\u0001\u0010\u008e\u0001R)\u0010\u0090\u0001\u001a\u0002022\u0007\u0010\u008f\u0001\u001a\u0002028\u0002@BX\u0082\u000e¢\u0006\u000f\n\u0005\b\u0090\u0001\u0010c\"\u0006\b\u0091\u0001\u0010\u0092\u0001\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u009b\u0001"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/PlaylistDetailViewModel;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/BaseAndroidViewModel;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/BaseViewsRefinementManager$Provider;", "Lcom/amazon/music/views/library/recyclerview/adapters/UniversalAdapter$ItemMovedListener;", "Lcom/amazon/mp3/detailpages/BaseViewsDragDropManager$Listener;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/MusicTrackFetcher;", "Lcom/amazon/music/views/library/models/ExposedRefinementListModel;", "refinementModel", "Lcom/amazon/music/views/library/models/FilterItemModel;", "currentSortModel", "", "initRefinementsManager", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "models", "getTracklistSection", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "pageModel", "fetchInitialPlaylistDownloadState", "Lcom/amazon/music/views/library/models/DownloadStateModel;", "downloadState", "updateDownloadState", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "contentEnabilityProvider", "setContentEnabilityProvider", "pageGridViewModel", "Lcom/amazon/music/views/library/models/PageHeaderModel;", "getPageHeaderModel", "", "sortOrder", "updateAvailabilityState", "handleInitPageResponse", "Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsDiffUtilFactory;", "diffUtilFactory", "Lcom/amazon/mp3/catalog/fragment/viewmodel/FetchType;", "fetchType", "handleUpdatedPageResponse", "oldModels", "newModels", "checkModelsForUpdates", "(Ljava/util/List;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "postUpdatedModels", "postSuggestions", "Lcom/amazon/music/views/library/metadata/PlaylistMetadata;", "getPlaylistMetadata", "getArtworkUrl", "", "findPositionOfRefinementList", "()Ljava/lang/Integer;", "findPositionOfFirstTrack", "", "isPlaylistDownloaded", "isPartiallyOwned", "isFollowed", "updateFollowState", "Lcom/amazon/mp3/catalog/fragment/viewmodel/BaseViewsRefinementManager;", "getRefinementManager", "retrievePlaylistTrackAsinList", "getFilteredTrackCount", "(Lcom/amazon/music/views/library/models/PageGridViewModel;)Ljava/lang/Integer;", "firstAvailableTrackIndex", "model", "Lcom/amazon/mp3/library/item/MusicTrack;", "getTrackForModel", "Landroid/net/Uri;", "uri", "registerDownloadStateListener", "unregisterDownloadStateListener", "getMusicTrack", "Lcom/amazon/mp3/fragment/viewmodel/OnFilterChangedListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setDownloadChangedListener", "fromIndex", "toIndex", "onItemMoved", "", "targetModel", "shouldMoveItem", "from", "to", "newDataModel", "onModelsReordered", "onCleared", "updateRowNumber", "toggleTracklistVisibility", "hidden", "setTracklistModelsVisibility", "trackList", "refreshTracksDownloadState", "isFreeTier", "willDisplayFilteredData", "Lkotlin/ranges/IntRange;", "indicesChanged", "onLoadPageRangeChanged", "fetchFollowerCount", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lcom/amazon/mp3/library/item/AbstractItem;", "getPlaylist", "isCatalog", "Z", "contentUri", "Landroid/net/Uri;", "Landroid/content/Context;", "context", "Landroid/content/Context;", "Lcom/amazon/mp3/curate/provider/CurateFeatureFlagProvider;", "curateFeatureFlagProvider", "Lcom/amazon/mp3/curate/provider/CurateFeatureFlagProvider;", "refinementsManager", "Lcom/amazon/mp3/catalog/fragment/viewmodel/BaseViewsRefinementManager;", "Lio/reactivex/disposables/Disposable;", "reorderingDisposable", "Lio/reactivex/disposables/Disposable;", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "Lcom/amazon/mp3/library/provider/source/cirrus/dbutils/RepositoryEventType;", "repositoryUpdatesEventTypeAllowList", "Ljava/util/List;", "getRepositoryUpdatesEventTypeAllowList$DigitalMusicAndroid3P_automotiveProdRelease", "()Ljava/util/List;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/PageFetchInfo;", "lastFetchedPageInfo", "Lcom/amazon/mp3/catalog/fragment/viewmodel/PageFetchInfo;", "getLastFetchedPageInfo", "()Lcom/amazon/mp3/catalog/fragment/viewmodel/PageFetchInfo;", "setLastFetchedPageInfo", "(Lcom/amazon/mp3/catalog/fragment/viewmodel/PageFetchInfo;)V", "Lcom/amazon/mp3/detailpages/BaseViewsDragDropManager;", "dragDropManager$delegate", "Lkotlin/Lazy;", "getDragDropManager", "()Lcom/amazon/mp3/detailpages/BaseViewsDragDropManager;", "dragDropManager", "Lcom/amazon/mp3/catalog/fragment/viewmodel/PlaylistDownloadStateModelProvider;", "groupDownloadStateModelProvider", "Lcom/amazon/mp3/catalog/fragment/viewmodel/PlaylistDownloadStateModelProvider;", "com/amazon/mp3/catalog/fragment/viewmodel/PlaylistDetailViewModel$downloadStateModelProviderListener$1", "downloadStateModelProviderListener", "Lcom/amazon/mp3/catalog/fragment/viewmodel/PlaylistDetailViewModel$downloadStateModelProviderListener$1;", "Lcom/amazon/mp3/curate/provider/CuratePlaylistProvider;", "curatePlaylistProvider", "Lcom/amazon/mp3/curate/provider/CuratePlaylistProvider;", "getCuratePlaylistProvider", "()Lcom/amazon/mp3/curate/provider/CuratePlaylistProvider;", "value", "isTracklistVisible", "setTracklistVisible", "(Z)V", "Landroid/app/Application;", "application", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/PlaylistDetailDataRepository;", "dataRepository", "initEqualizer", "<init>", "(Landroid/app/Application;Lcom/amazon/mp3/catalog/fragment/datasource/repository/PlaylistDetailDataRepository;ZZLandroid/net/Uri;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class PlaylistDetailViewModel extends BaseAndroidViewModel implements BaseViewsRefinementManager.Provider, UniversalAdapter.ItemMovedListener, BaseViewsDragDropManager.Listener, MusicTrackFetcher {
    private static final String TAG = PlaylistDetailViewModel.class.getSimpleName();
    private ContentEnabilityProvider contentEnabilityProvider;
    private final Uri contentUri;
    private final Context context;
    private final CurateFeatureFlagProvider curateFeatureFlagProvider;
    private final CuratePlaylistProvider curatePlaylistProvider;
    private final PlaylistDetailViewModel$downloadStateModelProviderListener$1 downloadStateModelProviderListener;

    /* JADX INFO: renamed from: dragDropManager$delegate, reason: from kotlin metadata */
    private final Lazy dragDropManager;
    private final PlaylistDownloadStateModelProvider groupDownloadStateModelProvider;
    private final boolean isCatalog;
    private boolean isTracklistVisible;
    private PageFetchInfo lastFetchedPageInfo;
    private BaseViewsRefinementManager refinementsManager;
    private Disposable reorderingDisposable;
    private final List<RepositoryEventType> repositoryUpdatesEventTypeAllowList;

    /* JADX INFO: renamed from: com.amazon.mp3.catalog.fragment.viewmodel.PlaylistDetailViewModel$checkModelsForUpdates$1, reason: invalid class name */
    /* JADX INFO: compiled from: PlaylistDetailViewModel.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.mp3.catalog.fragment.viewmodel.PlaylistDetailViewModel", f = "PlaylistDetailViewModel.kt", i = {0}, l = {196}, m = "checkModelsForUpdates", n = {"this"}, s = {"L$0"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PlaylistDetailViewModel.this.checkModelsForUpdates(null, null, this);
        }
    }

    public final PageHeaderModel getPageHeaderModel(PageGridViewModel pageGridViewModel) {
        CollectionDetailHeaderModel collectionDetailHeaderModel = pageGridViewModel == null ? null : (CollectionDetailHeaderModel) BaseViewContainerModelUtil.INSTANCE.findChildByType(pageGridViewModel, CollectionDetailHeaderModel.class);
        if (collectionDetailHeaderModel == null) {
            return pageGridViewModel != null ? (StoryPageHeaderModel) BaseViewContainerModelUtil.INSTANCE.findChildByType(pageGridViewModel, StoryPageHeaderModel.class) : null;
        }
        return collectionDetailHeaderModel;
    }

    public final PlaylistMetadata getPlaylistMetadata(PageGridViewModel pageGridViewModel) {
        PageHeaderModel pageHeaderModel = pageGridViewModel == null ? null : (CollectionDetailHeaderModel) BaseViewContainerModelUtil.INSTANCE.findChildByType(pageGridViewModel, CollectionDetailHeaderModel.class);
        if (pageHeaderModel == null) {
            pageHeaderModel = pageGridViewModel == null ? null : (StoryPageHeaderModel) BaseViewContainerModelUtil.INSTANCE.findChildByType(pageGridViewModel, StoryPageHeaderModel.class);
        }
        ContentMetadata metadata = pageHeaderModel == null ? null : pageHeaderModel.getMetadata();
        if (metadata instanceof PlaylistMetadata) {
            return (PlaylistMetadata) metadata;
        }
        return null;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r10v7, types: [com.amazon.mp3.catalog.fragment.viewmodel.PlaylistDetailViewModel$downloadStateModelProviderListener$1] */
    public PlaylistDetailViewModel(Application application, PlaylistDetailDataRepository dataRepository, boolean z, boolean z2, Uri uri) {
        super(application, dataRepository, false, z2, 4, null);
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(dataRepository, "dataRepository");
        this.isCatalog = z;
        this.contentUri = uri;
        this.context = application.getApplicationContext();
        this.curateFeatureFlagProvider = new CurateFeatureFlagProvider();
        this.repositoryUpdatesEventTypeAllowList = PlaylistRepositoryEventsKt.getPlaylistDetailPageUpdateEventTypes();
        this.dragDropManager = LazyKt__LazyJVMKt.lazy(new Function0<BaseViewsDragDropManager>() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.PlaylistDetailViewModel$dragDropManager$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseViewsDragDropManager invoke() {
                return new BaseViewsDragDropManager(this.this$0);
            }
        });
        Context applicationContext = application.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "application.applicationContext");
        this.groupDownloadStateModelProvider = new PlaylistDownloadStateModelProvider(applicationContext, z);
        final boolean z3 = !z;
        this.downloadStateModelProviderListener = new GroupDownloadStateModelProvider.BauhausDefaultListener(z3) { // from class: com.amazon.mp3.catalog.fragment.viewmodel.PlaylistDetailViewModel$downloadStateModelProviderListener$1
            @Override // com.amazon.mp3.catalog.fragment.viewmodel.GroupDownloadStateModelProvider.BauhausDefaultListener
            /* JADX INFO: renamed from: getPageModel */
            public PageGridViewModel get$pageGridViewModel() {
                return this.this$0.getPageData().getValue();
            }

            @Override // com.amazon.mp3.catalog.fragment.viewmodel.GroupDownloadStateModelProvider.BauhausDefaultListener
            public PageStateManager getPageStateManager() {
                return this.this$0.getPageStateManager();
            }
        };
        this.curatePlaylistProvider = new CuratePlaylistProvider();
        initRefinementsManager();
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel
    public List<RepositoryEventType> getRepositoryUpdatesEventTypeAllowList$DigitalMusicAndroid3P_automotiveProdRelease() {
        return this.repositoryUpdatesEventTypeAllowList;
    }

    public final PageFetchInfo getLastFetchedPageInfo() {
        return this.lastFetchedPageInfo;
    }

    public final void setLastFetchedPageInfo(PageFetchInfo pageFetchInfo) {
        this.lastFetchedPageInfo = pageFetchInfo;
    }

    private final BaseViewsDragDropManager getDragDropManager() {
        return (BaseViewsDragDropManager) this.dragDropManager.getValue();
    }

    public final CuratePlaylistProvider getCuratePlaylistProvider() {
        return this.curatePlaylistProvider;
    }

    public final void setContentEnabilityProvider(ContentEnabilityProvider contentEnabilityProvider) {
        Intrinsics.checkNotNullParameter(contentEnabilityProvider, "contentEnabilityProvider");
        this.contentEnabilityProvider = contentEnabilityProvider;
    }

    public static /* synthetic */ PageHeaderModel getPageHeaderModel$default(PlaylistDetailViewModel playlistDetailViewModel, PageGridViewModel pageGridViewModel, int i, Object obj) {
        if ((i & 1) != 0) {
            pageGridViewModel = playlistDetailViewModel.getPageData().getValue();
        }
        return playlistDetailViewModel.getPageHeaderModel(pageGridViewModel);
    }

    public final String sortOrder() {
        Map<RefinementFilterType, DbSort<MusicTrack>> trackSortMapping;
        FilterItemModel filterItemModelCurrentSortModel = currentSortModel();
        if (filterItemModelCurrentSortModel == null) {
            return null;
        }
        RefinementFilterType refinementTypeFromDisplayName = RefinementFilterType.INSTANCE.getRefinementTypeFromDisplayName(filterItemModelCurrentSortModel.getTitle());
        if (this.isCatalog) {
            trackSortMapping = RefinementsMapping.INSTANCE.getCatalogTrackSortMapping();
        } else {
            trackSortMapping = RefinementsMapping.INSTANCE.getTrackSortMapping();
        }
        DbSort<MusicTrack> dbSort = trackSortMapping.get(refinementTypeFromDisplayName);
        if (dbSort == null) {
            return null;
        }
        return dbSort.getColumnName();
    }

    private final ExposedRefinementListModel refinementModel() {
        List<BaseViewModel> models;
        PageGridViewModel value = getPageData().getValue();
        if (value == null || (models = value.getModels()) == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : models) {
            if (obj instanceof ExposedRefinementListModel) {
                arrayList.add(obj);
            }
        }
        return (ExposedRefinementListModel) CollectionsKt___CollectionsKt.getOrNull(arrayList, 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0048 A[EDGE_INSN: B:34:0x0048->B:24:0x0048 BREAK  A[LOOP:0: B:9:0x0014->B:37:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:? A[LOOP:0: B:9:0x0014->B:37:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final com.amazon.music.views.library.models.FilterItemModel currentSortModel() {
        /*
            r6 = this;
            com.amazon.music.views.library.models.ExposedRefinementListModel r0 = r6.refinementModel()
            r1 = 0
            if (r0 != 0) goto L8
            return r1
        L8:
            java.util.List r0 = r0.getFilterModels()
            if (r0 != 0) goto L10
            r2 = r1
            goto L4a
        L10:
            java.util.Iterator r0 = r0.iterator()
        L14:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L47
            java.lang.Object r2 = r0.next()
            r3 = r2
            com.amazon.music.views.library.models.FilterModel r3 = (com.amazon.music.views.library.models.FilterModel) r3
            boolean r4 = r3.isCollection()
            if (r4 == 0) goto L43
            com.amazon.music.views.library.models.FilterCollectionModel r3 = r3.getCollectionModel()
            if (r3 != 0) goto L2f
            r3 = r1
            goto L33
        L2f:
            java.lang.String r3 = r3.getTitle()
        L33:
            com.amazon.mp3.view.refinements.RefinementFilterType$Companion r4 = com.amazon.mp3.view.refinements.RefinementFilterType.INSTANCE
            com.amazon.mp3.view.refinements.RefinementFilterType r5 = com.amazon.mp3.view.refinements.RefinementFilterType.SORT
            java.lang.String r4 = r4.getDisplayName(r5)
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r4)
            if (r3 == 0) goto L43
            r3 = 1
            goto L44
        L43:
            r3 = 0
        L44:
            if (r3 == 0) goto L14
            goto L48
        L47:
            r2 = r1
        L48:
            com.amazon.music.views.library.models.FilterModel r2 = (com.amazon.music.views.library.models.FilterModel) r2
        L4a:
            boolean r0 = r2 instanceof com.amazon.music.views.library.models.FilterCollectionModel
            if (r0 == 0) goto L51
            com.amazon.music.views.library.models.FilterCollectionModel r2 = (com.amazon.music.views.library.models.FilterCollectionModel) r2
            goto L52
        L51:
            r2 = r1
        L52:
            if (r2 != 0) goto L55
            goto L59
        L55:
            com.amazon.music.views.library.models.FilterItemModel r1 = r2.getSelectedSortingFilterModel()
        L59:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.catalog.fragment.viewmodel.PlaylistDetailViewModel.currentSortModel():com.amazon.music.views.library.models.FilterItemModel");
    }

    /* JADX INFO: renamed from: com.amazon.mp3.catalog.fragment.viewmodel.PlaylistDetailViewModel$updateAvailabilityState$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PlaylistDetailViewModel.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.catalog.fragment.viewmodel.PlaylistDetailViewModel$updateAvailabilityState$1", f = "PlaylistDetailViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03291 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C03291(Continuation<? super C03291> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C03291 c03291 = PlaylistDetailViewModel.this.new C03291(continuation);
            c03291.L$0 = obj;
            return c03291;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03291) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            List<BaseViewModel> models;
            List<TableRowItemModel> listFilterIsInstance;
            ContentEnabilityProvider contentEnabilityProvider;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            PageGridViewModel value = PlaylistDetailViewModel.this.getPageData().getValue();
            if (value != null && (models = value.getModels()) != null && (listFilterIsInstance = CollectionsKt___CollectionsJvmKt.filterIsInstance(models, TableRowItemModel.class)) != null) {
                PlaylistDetailViewModel playlistDetailViewModel = PlaylistDetailViewModel.this;
                for (TableRowItemModel tableRowItemModel : listFilterIsInstance) {
                    if (CoroutineScopeKt.isActive(coroutineScope) && (contentEnabilityProvider = playlistDetailViewModel.contentEnabilityProvider) != null) {
                        tableRowItemModel.updateAvailabilityState(contentEnabilityProvider.getAvailabilityState(tableRowItemModel.getMetadata()));
                    }
                }
            }
            return Unit.INSTANCE;
        }
    }

    public final void updateAvailabilityState() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C03291(null), 2, null);
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel
    protected void handleInitPageResponse(PageGridViewModel pageModel) {
        this.lastFetchedPageInfo = new PageFetchInfo(FetchType.INIT, null, 2, null);
        BaseViewsRefinementManager baseViewsRefinementManager = this.refinementsManager;
        if (baseViewsRefinementManager != null) {
            baseViewsRefinementManager.updateUnfilteredData(pageModel);
        }
        fetchInitialPlaylistDownloadState(pageModel);
        super.handleInitPageResponse(pageModel);
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel
    protected synchronized void handleUpdatedPageResponse(PageGridViewModel pageModel, BaseViewsDiffUtilFactory diffUtilFactory, FetchType fetchType) {
        List<BaseViewModel> models;
        PageHeaderModel pageHeaderModel;
        List<BaseViewModel> models2;
        ExposedRefinementListModel exposedRefinementListModel;
        List<BaseViewModel> models3;
        EmptyStateModel emptyStateModel;
        List<BaseViewModel> models4;
        HighlightableMessageModel highlightableMessageModel;
        BaseViewsRefinementManager baseViewsRefinementManager;
        Intrinsics.checkNotNullParameter(diffUtilFactory, "diffUtilFactory");
        if (fetchType == null) {
            fetchType = FetchType.UPDATE;
        }
        List<BaseViewModel> filteredDataList = null;
        this.lastFetchedPageInfo = new PageFetchInfo(fetchType, null, 2, null);
        BaseViewsRefinementManager baseViewsRefinementManager2 = this.refinementsManager;
        if (baseViewsRefinementManager2 != null) {
            baseViewsRefinementManager2.updateUnfilteredData(pageModel);
        }
        ArrayList arrayList = new ArrayList();
        if (pageModel == null || (models = pageModel.getModels()) == null) {
            pageHeaderModel = null;
        } else {
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : models) {
                if (obj instanceof PageHeaderModel) {
                    arrayList2.add(obj);
                }
            }
            pageHeaderModel = (PageHeaderModel) CollectionsKt___CollectionsKt.getOrNull(arrayList2, 0);
        }
        if (pageModel == null || (models2 = pageModel.getModels()) == null) {
            exposedRefinementListModel = null;
        } else {
            ArrayList arrayList3 = new ArrayList();
            for (Object obj2 : models2) {
                if (obj2 instanceof ExposedRefinementListModel) {
                    arrayList3.add(obj2);
                }
            }
            exposedRefinementListModel = (ExposedRefinementListModel) CollectionsKt___CollectionsKt.getOrNull(arrayList3, 0);
        }
        if (pageModel == null || (models3 = pageModel.getModels()) == null) {
            emptyStateModel = null;
        } else {
            ArrayList arrayList4 = new ArrayList();
            for (Object obj3 : models3) {
                if (obj3 instanceof EmptyStateModel) {
                    arrayList4.add(obj3);
                }
            }
            emptyStateModel = (EmptyStateModel) CollectionsKt___CollectionsKt.getOrNull(arrayList4, 0);
        }
        if (pageModel == null || (models4 = pageModel.getModels()) == null) {
            highlightableMessageModel = null;
        } else {
            ArrayList arrayList5 = new ArrayList();
            for (Object obj4 : models4) {
                if (obj4 instanceof HighlightableMessageModel) {
                    arrayList5.add(obj4);
                }
            }
            highlightableMessageModel = (HighlightableMessageModel) CollectionsKt___CollectionsKt.getOrNull(arrayList5, 0);
        }
        if (pageHeaderModel != null) {
            arrayList.add(pageHeaderModel);
        }
        if (exposedRefinementListModel != null) {
            arrayList.add(exposedRefinementListModel);
        }
        if (emptyStateModel != null) {
            arrayList.add(emptyStateModel);
        }
        if (highlightableMessageModel != null) {
            arrayList.add(highlightableMessageModel);
        }
        Context context = this.context;
        if (context != null && (baseViewsRefinementManager = this.refinementsManager) != null) {
            filteredDataList = baseViewsRefinementManager.getFilteredDataList(context);
        }
        if (filteredDataList == null) {
            filteredDataList = CollectionsKt__CollectionsKt.emptyList();
        }
        arrayList.addAll(filteredDataList);
        compareModelsForUpdates(pageModel, arrayList, diffUtilFactory);
        fetchInitialPlaylistDownloadState(pageModel);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected java.lang.Object checkModelsForUpdates(java.util.List<? extends com.amazon.music.views.library.models.base.BaseViewModel> r5, java.util.List<? extends com.amazon.music.views.library.models.base.BaseViewModel> r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof com.amazon.mp3.catalog.fragment.viewmodel.PlaylistDetailViewModel.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r7
            com.amazon.mp3.catalog.fragment.viewmodel.PlaylistDetailViewModel$checkModelsForUpdates$1 r0 = (com.amazon.mp3.catalog.fragment.viewmodel.PlaylistDetailViewModel.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.mp3.catalog.fragment.viewmodel.PlaylistDetailViewModel$checkModelsForUpdates$1 r0 = new com.amazon.mp3.catalog.fragment.viewmodel.PlaylistDetailViewModel$checkModelsForUpdates$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r5 = r0.L$0
            com.amazon.mp3.catalog.fragment.viewmodel.PlaylistDetailViewModel r5 = (com.amazon.mp3.catalog.fragment.viewmodel.PlaylistDetailViewModel) r5
            kotlin.ResultKt.throwOnFailure(r7)
            goto L47
        L2d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L35:
            kotlin.ResultKt.throwOnFailure(r7)
            r4.updateRowNumber(r6)
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = super.checkModelsForUpdates(r5, r6, r0)
            if (r5 != r1) goto L46
            return r1
        L46:
            r5 = r4
        L47:
            r5.updateAvailabilityState()
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.catalog.fragment.viewmodel.PlaylistDetailViewModel.checkModelsForUpdates(java.util.List, java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel
    protected void postUpdatedModels(PageGridViewModel pageModel) {
        List<BaseViewModel> models;
        ArrayList arrayList;
        BaseViewsRefinementManager baseViewsRefinementManager;
        if (pageModel == null || (models = pageModel.getModels()) == null) {
            arrayList = null;
        } else {
            arrayList = new ArrayList();
            for (Object obj : models) {
                BaseViewModel baseViewModel = (BaseViewModel) obj;
                if (((baseViewModel instanceof VisualRowItemModel) || (baseViewModel instanceof TextRowItemModel)) ? false : true) {
                    arrayList.add(obj);
                }
            }
        }
        List<BaseViewModel> cappedSuggestions = AmazonApplication.getCapabilities().isPlaylistSongRecommendationWidgetEnabled() ? this.curatePlaylistProvider.getCappedSuggestions() : null;
        Context context = this.context;
        if (context == null || (baseViewsRefinementManager = this.refinementsManager) == null) {
            return;
        }
        baseViewsRefinementManager.applyRefinementFilters(context, arrayList, cappedSuggestions);
    }

    public final void postSuggestions(PageGridViewModel pageModel) {
        List<BaseViewModel> models = pageModel == null ? null : pageModel.getModels();
        List<BaseViewModel> cappedSuggestions = this.curatePlaylistProvider.getCappedSuggestions();
        if (pageModel != null) {
            pageModel.updateModels(CollectionsKt__IterablesKt.flatten(CollectionsKt__CollectionsKt.listOfNotNull((Object[]) new List[]{models, cappedSuggestions})));
        }
        getPageData().postValue(pageModel);
    }

    public static /* synthetic */ PlaylistMetadata getPlaylistMetadata$default(PlaylistDetailViewModel playlistDetailViewModel, PageGridViewModel pageGridViewModel, int i, Object obj) {
        if ((i & 1) != 0) {
            pageGridViewModel = playlistDetailViewModel.getPageData().getValue();
        }
        return playlistDetailViewModel.getPlaylistMetadata(pageGridViewModel);
    }

    public final String getArtworkUrl() {
        PageGridViewModel value = getPageModelLiveData().getValue();
        if (value == null) {
            return null;
        }
        for (BaseViewModel baseViewModel : value.getModels()) {
            if (baseViewModel instanceof CollectionDetailHeaderModel) {
                ContentMetadata metadata = ((CollectionDetailHeaderModel) baseViewModel).getMetadata();
                PlaylistMetadata playlistMetadata = metadata instanceof PlaylistMetadata ? (PlaylistMetadata) metadata : null;
                if (playlistMetadata == null) {
                    return null;
                }
                return playlistMetadata.getImageUrl();
            }
        }
        return null;
    }

    public final Integer findPositionOfRefinementList() {
        PageGridViewModel value = getPageModelLiveData().getValue();
        if (value == null) {
            return null;
        }
        int i = 0;
        Iterator<BaseViewModel> it = value.getModels().iterator();
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            if (it.next() instanceof ExposedRefinementListModel) {
                break;
            }
            i++;
        }
        if (i == -1) {
            return null;
        }
        return Integer.valueOf(i);
    }

    public final Integer findPositionOfFirstTrack() {
        PageGridViewModel value = getPageModelLiveData().getValue();
        if (value == null) {
            return null;
        }
        int i = 0;
        Iterator<BaseViewModel> it = value.getModels().iterator();
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            if (it.next() instanceof VisualRowItemModel) {
                break;
            }
            i++;
        }
        if (i == -1) {
            return null;
        }
        return Integer.valueOf(i);
    }

    public final boolean isPlaylistDownloaded() {
        Object next;
        DownloadStateModel downloadStateModel;
        PageGridViewModel value = getPageModelLiveData().getValue();
        if (value != null) {
            Iterator<T> it = value.getModels().iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                if (((BaseViewModel) next) instanceof PageHeaderModel) {
                    break;
                }
            }
            BaseViewModel baseViewModel = (BaseViewModel) next;
            if (baseViewModel != null) {
                PageHeaderModel pageHeaderModel = baseViewModel instanceof PageHeaderModel ? (PageHeaderModel) baseViewModel : null;
                if (pageHeaderModel != null && (downloadStateModel = pageHeaderModel.getDownloadStateModel()) != null) {
                    return downloadStateModel.getDownloadState() == DownloadStateModel.DownloadState.DOWNLOADED || downloadStateModel.getDownloadState() == DownloadStateModel.DownloadState.REDOWNLOAD;
                }
            }
        }
        return false;
    }

    public final boolean isPartiallyOwned() {
        Boolean boolValueOf;
        Object next;
        PageGridViewModel value = getPageModelLiveData().getValue();
        if (value == null) {
            return false;
        }
        Iterator<T> it = value.getModels().iterator();
        while (true) {
            boolValueOf = null;
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (((BaseViewModel) next) instanceof PageHeaderModel) {
                break;
            }
        }
        BaseViewModel baseViewModel = (BaseViewModel) next;
        if (baseViewModel != null) {
            PageHeaderModel pageHeaderModel = baseViewModel instanceof PageHeaderModel ? (PageHeaderModel) baseViewModel : null;
            ContentMetadata metadata = pageHeaderModel == null ? null : pageHeaderModel.getMetadata();
            PlaylistMetadata playlistMetadata = metadata instanceof PlaylistMetadata ? (PlaylistMetadata) metadata : null;
            boolValueOf = Boolean.valueOf(playlistMetadata != null && playlistMetadata.getIsPartiallyOwned());
        }
        if (boolValueOf == null) {
            return false;
        }
        return boolValueOf.booleanValue();
    }

    public final void updateFollowState(boolean isFollowed) {
        List<BaseViewModel> models;
        PageGridViewModel value = getPageData().getValue();
        PageHeaderModel pageHeaderModel = null;
        if (value != null && (models = value.getModels()) != null) {
            ArrayList arrayList = new ArrayList();
            for (Object obj : models) {
                if (obj instanceof PageHeaderModel) {
                    arrayList.add(obj);
                }
            }
            pageHeaderModel = (PageHeaderModel) arrayList.get(0);
        }
        if (pageHeaderModel == null) {
            return;
        }
        pageHeaderModel.updateFollowState(isFollowed);
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.BaseViewsRefinementManager.Provider
    /* JADX INFO: renamed from: getRefinementManager, reason: from getter */
    public BaseViewsRefinementManager getRefinementsManager() {
        return this.refinementsManager;
    }

    public static /* synthetic */ List retrievePlaylistTrackAsinList$default(PlaylistDetailViewModel playlistDetailViewModel, PageGridViewModel pageGridViewModel, int i, Object obj) {
        if ((i & 1) != 0) {
            pageGridViewModel = playlistDetailViewModel.getPageModelLiveData().getValue();
        }
        return playlistDetailViewModel.retrievePlaylistTrackAsinList(pageGridViewModel);
    }

    public final List<String> retrievePlaylistTrackAsinList(PageGridViewModel pageModel) {
        String asin;
        if (pageModel == null) {
            return null;
        }
        List<BaseViewModel> models = pageModel.getModels();
        ArrayList arrayList = new ArrayList();
        for (Object obj : models) {
            if (obj instanceof VisualRowItemModel) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ContentMetadata metadata = ((VisualRowItemModel) it.next()).getMetadata();
            TrackMetadata trackMetadata = metadata instanceof TrackMetadata ? (TrackMetadata) metadata : null;
            if (trackMetadata != null && (asin = trackMetadata.getAsin()) != null) {
                arrayList2.add(asin);
            }
        }
        return arrayList2;
    }

    public final Integer getFilteredTrackCount(PageGridViewModel pageModel) {
        List<BaseViewModel> models;
        if (pageModel == null || (models = pageModel.getModels()) == null) {
            return null;
        }
        Iterator<BaseViewModel> it = models.iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            if (it.next() instanceof VisualRowItemModel) {
                break;
            }
            i++;
        }
        return Integer.valueOf(i >= 0 ? models.size() - i : 0);
    }

    public final Integer firstAvailableTrackIndex(PageGridViewModel pageModel) {
        if ((pageModel == null ? null : pageModel.getModels()) == null) {
            return null;
        }
        int i = 0;
        Iterator<T> it = pageModel.getModels().iterator();
        while (it.hasNext()) {
            MusicTrack trackForModel = getTrackForModel((BaseViewModel) it.next());
            if (trackForModel != null) {
                if (trackForModel.isAvailable()) {
                    return Integer.valueOf(i);
                }
                i++;
            }
        }
        return null;
    }

    public final MusicTrack getTrackForModel(BaseViewModel model) {
        Intrinsics.checkNotNullParameter(model, "model");
        VisualRowItemModel visualRowItemModel = model instanceof VisualRowItemModel ? (VisualRowItemModel) model : null;
        ContentMetadata metadata = visualRowItemModel == null ? null : visualRowItemModel.getMetadata();
        TrackMetadata trackMetadata = metadata instanceof TrackMetadata ? (TrackMetadata) metadata : null;
        if (trackMetadata == null) {
            return null;
        }
        return getMusicTrack(trackMetadata.getUri());
    }

    private final List<BaseViewModel> getTracklistSection(List<? extends BaseViewModel> models) {
        int iNextIndex;
        Iterator<? extends BaseViewModel> it = models.iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            if (it.next() instanceof MultiStateButtonModel) {
                break;
            }
            i++;
        }
        ListIterator<? extends BaseViewModel> listIterator = models.listIterator(models.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                iNextIndex = -1;
                break;
            }
            if (listIterator.previous() instanceof TableRowItemModel) {
                iNextIndex = listIterator.nextIndex();
                break;
            }
        }
        if (i == -1 || iNextIndex == -1) {
            return null;
        }
        return models.subList(i + 1, iNextIndex + 1);
    }

    public final void registerDownloadStateListener(Uri uri) {
        this.groupDownloadStateModelProvider.registerDownloadStateModelListener(this.downloadStateModelProviderListener, uri);
        fetchInitialPlaylistDownloadState(null);
    }

    public final void unregisterDownloadStateListener() {
        this.groupDownloadStateModelProvider.unregisterDownloadStateModelListener();
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.MusicTrackFetcher
    public MusicTrack getMusicTrack(Uri uri) {
        PageDataRepository pageDataRepository = getPageDataRepository();
        PlaylistDetailDataRepository playlistDetailDataRepository = pageDataRepository instanceof PlaylistDetailDataRepository ? (PlaylistDetailDataRepository) pageDataRepository : null;
        if (playlistDetailDataRepository == null) {
            return null;
        }
        return playlistDetailDataRepository.getMusicTrack(uri);
    }

    private final void initRefinementsManager() {
        BaseViewsRefinementManager baseViewsRefinementManager = new BaseViewsRefinementManager(new VisualRowRefinementDataProvider());
        this.refinementsManager = baseViewsRefinementManager;
        baseViewsRefinementManager.observePageData(getPageModelLiveData());
    }

    public final void setDownloadChangedListener(OnFilterChangedListener listener) {
        BaseViewsRefinementManager baseViewsRefinementManager = this.refinementsManager;
        if (baseViewsRefinementManager == null) {
            return;
        }
        baseViewsRefinementManager.setChangedListener(listener);
    }

    @Override // com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter.ItemMovedListener
    public void onItemMoved(final int fromIndex, final int toIndex) {
        Observable<String> observableReorderPlaylist;
        List<BaseViewModel> models;
        PageGridViewModel value = getPageData().getValue();
        int iCalculateRowNumberOffset = 0;
        if (value != null && (models = value.getModels()) != null) {
            iCalculateRowNumberOffset = getDragDropManager().calculateRowNumberOffset(models);
        }
        final int i = fromIndex - iCalculateRowNumberOffset;
        final int i2 = toIndex - iCalculateRowNumberOffset;
        Disposable disposable = this.reorderingDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        PageDataRepository pageDataRepository = getPageDataRepository();
        Disposable disposableSubscribe = null;
        PlaylistDetailDataRepository playlistDetailDataRepository = pageDataRepository instanceof PlaylistDetailDataRepository ? (PlaylistDetailDataRepository) pageDataRepository : null;
        if (playlistDetailDataRepository != null && (observableReorderPlaylist = playlistDetailDataRepository.reorderPlaylist(i, i2)) != null) {
            disposableSubscribe = observableReorderPlaylist.subscribe(new Consumer() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.PlaylistDetailViewModel$$ExternalSyntheticLambda0
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    PlaylistDetailViewModel.m668onItemMoved$lambda33(this.f$0, fromIndex, toIndex, (String) obj);
                }
            }, new Consumer() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.PlaylistDetailViewModel$$ExternalSyntheticLambda1
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    PlaylistDetailViewModel.m669onItemMoved$lambda34(this.f$0, i, i2, (Throwable) obj);
                }
            }, new Action() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.PlaylistDetailViewModel$$ExternalSyntheticLambda2
                @Override // io.reactivex.functions.Action
                public final void run() {
                    PlaylistDetailViewModel.m670onItemMoved$lambda35(this.f$0);
                }
            });
        }
        this.reorderingDisposable = disposableSubscribe;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onItemMoved$lambda-33, reason: not valid java name */
    public static final void m668onItemMoved$lambda33(PlaylistDetailViewModel this$0, int i, int i2, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getDragDropManager().reorderModel(i, i2, this$0.getPageData().getValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onItemMoved$lambda-34, reason: not valid java name */
    public static final void m669onItemMoved$lambda34(PlaylistDetailViewModel this$0, int i, int i2, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getPageData().postValue(this$0.getPageData().getValue());
        Log.error(TAG, "Action failed moving a track from " + i + " to " + i2 + ", error: " + ((Object) th.getMessage()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onItemMoved$lambda-35, reason: not valid java name */
    public static final void m670onItemMoved$lambda35(PlaylistDetailViewModel this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.reorderingDisposable = null;
    }

    @Override // com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter.ItemMovedListener
    public boolean shouldMoveItem(Object model, Object targetModel) {
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(targetModel, "targetModel");
        return targetModel instanceof VisualRowItemModel;
    }

    @Override // com.amazon.mp3.detailpages.BaseViewsDragDropManager.Listener
    public void onModelsReordered(int from, int to, PageGridViewModel newDataModel) {
        Intrinsics.checkNotNullParameter(newDataModel, "newDataModel");
        BaseViewsRefinementManager baseViewsRefinementManager = this.refinementsManager;
        if (baseViewsRefinementManager == null) {
            return;
        }
        baseViewsRefinementManager.updateUnfilteredData(newDataModel);
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.BaseAndroidViewModel, androidx.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        this.groupDownloadStateModelProvider.unregisterDownloadStateModelListener();
        Disposable disposable = this.reorderingDisposable;
        if (disposable == null) {
            return;
        }
        disposable.dispose();
    }

    public final void updateRowNumber(PageGridViewModel pageGridViewModel) {
        if (pageGridViewModel == null) {
            return;
        }
        updateRowNumber(pageGridViewModel.getModels());
    }

    public final void toggleTracklistVisibility() {
        setTracklistVisible(!this.isTracklistVisible);
    }

    private final void setTracklistVisible(boolean z) {
        if (this.isTracklistVisible == z) {
            return;
        }
        this.isTracklistVisible = z;
        PageGridViewModel value = getPageModelLiveData().getValue();
        if (value == null) {
            return;
        }
        setTracklistModelsVisibility$default(this, value, false, 2, null);
        getPageData().postValue(value);
    }

    public static /* synthetic */ void setTracklistModelsVisibility$default(PlaylistDetailViewModel playlistDetailViewModel, PageGridViewModel pageGridViewModel, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = !playlistDetailViewModel.isTracklistVisible;
        }
        playlistDetailViewModel.setTracklistModelsVisibility(pageGridViewModel, z);
    }

    public final void setTracklistModelsVisibility(PageGridViewModel pageModel, boolean hidden) {
        if (pageModel == null) {
            return;
        }
        List<BaseViewModel> tracklistSection = getTracklistSection(pageModel.getModels());
        if (tracklistSection != null) {
            ArrayList arrayList = new ArrayList();
            for (Object obj : tracklistSection) {
                if (obj instanceof Hideable) {
                    arrayList.add(obj);
                }
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((Hideable) it.next()).updateVisibility(hidden);
            }
        }
        List<BaseViewModel> models = pageModel.getModels();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : models) {
            if (obj2 instanceof MultiStateButtonModel) {
                arrayList2.add(obj2);
            }
        }
        MultiStateButtonModel multiStateButtonModel = (MultiStateButtonModel) CollectionsKt___CollectionsKt.firstOrNull((List) arrayList2);
        if (multiStateButtonModel == null) {
            return;
        }
        multiStateButtonModel.updateDisplayText(hidden);
    }

    public final void updateRowNumber(List<? extends BaseViewModel> models) {
        Intrinsics.checkNotNullParameter(models, "models");
        boolean zShouldShowPlaylistTrackNumber = new TrackModelConfiguration(null, 1, 0 == true ? 1 : 0).shouldShowPlaylistTrackNumber();
        ArrayList arrayList = new ArrayList();
        for (Object obj : models) {
            if (obj instanceof VisualRowItemModel) {
                arrayList.add(obj);
            }
        }
        Iterator it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            int i2 = i + 1;
            ((VisualRowItemModel) it.next()).setRowNumber(!zShouldShowPlaylistTrackNumber ? null : Integer.valueOf(i));
            i = i2;
        }
    }

    public final void refreshTracksDownloadState(List<? extends BaseViewModel> trackList) {
        this.groupDownloadStateModelProvider.fetchCachedTracksDownloadState(trackList);
    }

    public final boolean isFreeTier() {
        return UserSubscriptionUtil.isNightwingOnly();
    }

    public final void willDisplayFilteredData() {
        List<BaseViewModel> models;
        PageGridViewModel value = getPageModelLiveData().getValue();
        if (value == null || (models = value.getModels()) == null) {
            return;
        }
        int i = 0;
        Iterator<BaseViewModel> it = models.iterator();
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            } else if (it.next() instanceof VisualRowItemModel) {
                break;
            } else {
                i++;
            }
        }
        setLastFetchedPageInfo(new PageFetchInfo(FetchType.UPDATE, RangesKt___RangesKt.until(i, models.size())));
    }

    public final void onLoadPageRangeChanged(IntRange indicesChanged) {
        Intrinsics.checkNotNullParameter(indicesChanged, "indicesChanged");
        this.lastFetchedPageInfo = null;
    }

    private final void fetchInitialPlaylistDownloadState(final PageGridViewModel pageModel) {
        Single<DownloadStateModel> initialGroupDownloadState;
        updateDownloadState(pageModel, this.groupDownloadStateModelProvider.lastDownloadState());
        PlaylistMetadata playlistMetadata = getPlaylistMetadata(pageModel);
        Uri uri = playlistMetadata == null ? null : playlistMetadata.getUri();
        if (uri == null) {
            uri = this.contentUri;
        }
        if (uri == null) {
            return;
        }
        if (this.isCatalog) {
            initialGroupDownloadState = this.groupDownloadStateModelProvider.getInitialGroupDownloadState(uri, true);
        } else {
            initialGroupDownloadState = this.groupDownloadStateModelProvider.getInitialGroupDownloadState(uri, true);
        }
        initialGroupDownloadState.subscribeOn(Schedulers.io()).subscribe(new SingleObserver<DownloadStateModel>() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.PlaylistDetailViewModel$fetchInitialPlaylistDownloadState$1$1
            @Override // io.reactivex.SingleObserver
            public void onSubscribe(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
            }

            @Override // io.reactivex.SingleObserver
            public void onSuccess(DownloadStateModel t) {
                Intrinsics.checkNotNullParameter(t, "t");
                this.this$0.updateDownloadState(pageModel, t);
            }

            @Override // io.reactivex.SingleObserver
            public void onError(Throwable e) {
                Intrinsics.checkNotNullParameter(e, "e");
                Log.error(PlaylistDetailViewModel.TAG, Intrinsics.stringPlus("Action failed for fetching initial download state for playlist, error message: ", e.getMessage()));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateDownloadState(PageGridViewModel pageModel, DownloadStateModel downloadState) {
        if (downloadState == null) {
            return;
        }
        PageHeaderModel pageHeaderModel = getPageHeaderModel(pageModel);
        if (pageHeaderModel != null) {
            pageHeaderModel.updateDownloadState(downloadState);
        }
        PageHeaderModel pageHeaderModel$default = getPageHeaderModel$default(this, null, 1, null);
        if (pageHeaderModel$default == null) {
            return;
        }
        pageHeaderModel$default.updateDownloadState(downloadState);
    }

    public final Object fetchFollowerCount(Continuation<? super Integer> continuation) {
        PageDataRepository pageDataRepository = getPageDataRepository();
        PlaylistDetailDataRepository playlistDetailDataRepository = pageDataRepository instanceof PlaylistDetailDataRepository ? (PlaylistDetailDataRepository) pageDataRepository : null;
        if (playlistDetailDataRepository == null) {
            return null;
        }
        return playlistDetailDataRepository.getFollowerCount(continuation);
    }

    public final AbstractItem getPlaylist() {
        PageDataRepository pageDataRepository = getPageDataRepository();
        PlaylistDetailDataRepository playlistDetailDataRepository = pageDataRepository instanceof PlaylistDetailDataRepository ? (PlaylistDetailDataRepository) pageDataRepository : null;
        if (playlistDetailDataRepository == null) {
            return null;
        }
        return playlistDetailDataRepository.getPlaylist();
    }
}
