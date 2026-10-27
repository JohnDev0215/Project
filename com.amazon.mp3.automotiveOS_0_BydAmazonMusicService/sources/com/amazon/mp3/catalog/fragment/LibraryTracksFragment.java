package com.amazon.mp3.catalog.fragment;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.KeyEventDispatcher;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.actionbar.ActionBarProvider;
import com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.catalog.fragment.adapter.BaseRecyclerViewProvider;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsBinderConfiguration;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsBinderProvider;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtil;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtilFactory;
import com.amazon.mp3.catalog.fragment.datasource.dao.LibraryTracksDao;
import com.amazon.mp3.catalog.fragment.datasource.repository.PageDataRepository;
import com.amazon.mp3.catalog.fragment.datasource.repository.RefinablePageDataRepository;
import com.amazon.mp3.catalog.fragment.provider.InfoProviderLibraryImpl;
import com.amazon.mp3.catalog.fragment.provider.LibraryContentEnabilityProvider;
import com.amazon.mp3.catalog.fragment.viewmodel.PageFetchInfo;
import com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelFactoryProvider;
import com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelProvider;
import com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel;
import com.amazon.mp3.detailpages.actions.DefaultTrackInformationFetcher;
import com.amazon.mp3.detailpages.actions.GenericBaseViewModelFetcher;
import com.amazon.mp3.detailpages.track.LibraryTracksViewModel;
import com.amazon.mp3.detailpages.track.ScrollDirection;
import com.amazon.mp3.detailpages.track.ScrollPayload;
import com.amazon.mp3.eux.EUXDestinationLauncher;
import com.amazon.mp3.fragment.headerbar.OverflowMenuReceiver;
import com.amazon.mp3.fragment.viewmodel.OnFilterChangedListener;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.playback.harley.LibraryAccessProviderImpl;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.mp3.providers.DefaultAddToLibraryProvider;
import com.amazon.mp3.providers.DefaultLikesProvider;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.util.extensions.RefinementKt;
import com.amazon.mp3.view.refinements.BaseViewsRefinementOverflowManager;
import com.amazon.mp3.view.refinements.RefinementFilterType;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.binders.ActionIconOnClickListener;
import com.amazon.music.views.library.binders.LargeIconOnClickListener;
import com.amazon.music.views.library.binders.RefinementPillBinder;
import com.amazon.music.views.library.binders.UniversalBinder;
import com.amazon.music.views.library.deeplinks.ContentMetadataOnClickListener;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.models.EmptyStateButtonType;
import com.amazon.music.views.library.models.ExposedRefinementListModel;
import com.amazon.music.views.library.models.FilterCollectionModel;
import com.amazon.music.views.library.models.FilterModel;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.PageHeaderActionIconModel;
import com.amazon.music.views.library.models.RefinementPillModel;
import com.amazon.music.views.library.models.base.BaseViewContentModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.providers.ContentEnabilityProvider;
import com.amazon.music.views.library.providers.EmptyStateActionProvider;
import com.amazon.music.views.library.providers.RefinementCallback;
import com.amazon.music.views.library.providers.RowButtonOnClickProvider;
import com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider;
import com.amazon.music.views.library.recyclerview.EndlessScrollListener;
import com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter;
import com.amazon.music.views.library.recyclerview.indexer.FastScroller;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.views.presentation.ActionBarOverlayable;
import com.amazon.ui.foundations.views.BaseButton;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import rx.functions.Action1;

/* JADX INFO: compiled from: LibraryTracksFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000µ\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t*\u0001j\u0018\u0000 \u008b\u00012\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005:\u0002\u008b\u0001B\t¢\u0006\u0006\b\u0089\u0001\u0010\u008a\u0001J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\u0010\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fH\u0002J1\u0010\u0015\u001a\u00020\b2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0014\u001a\u00020\u0013H\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u0014\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00190\u0017H\u0002J\b\u0010\u001b\u001a\u00020\bH\u0002J\u0010\u0010\u001e\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\u001cH\u0002J\b\u0010\u001f\u001a\u00020\bH\u0002J\u001a\u0010$\u001a\u00020\b2\u0006\u0010!\u001a\u00020 2\b\u0010#\u001a\u0004\u0018\u00010\"H\u0016J\u0011\u0010%\u001a\u0004\u0018\u00010\u0011H\u0014¢\u0006\u0004\b%\u0010&J\u001a\u0010+\u001a\u00020)2\u0006\u0010(\u001a\u00020'2\b\u0010*\u001a\u0004\u0018\u00010)H\u0014J\b\u0010,\u001a\u00020\u0011H\u0014J\u0012\u0010-\u001a\u00020\b2\b\u0010#\u001a\u0004\u0018\u00010\"H\u0016J\b\u0010.\u001a\u00020\bH\u0016J\b\u0010/\u001a\u00020\bH\u0014J\b\u00100\u001a\u00020\u0013H\u0014J\b\u00102\u001a\u000201H\u0016J\b\u00103\u001a\u00020\bH\u0016J\u0010\u00106\u001a\u00020\b2\u0006\u00105\u001a\u000204H\u0016J\u0010\u00108\u001a\u00020\b2\u0006\u00107\u001a\u00020\u0013H\u0016J\u0012\u0010:\u001a\u0004\u0018\u0001092\u0006\u00105\u001a\u000204H\u0014J\b\u0010;\u001a\u00020\u0013H\u0014J\n\u0010=\u001a\u0004\u0018\u00010<H\u0016J\u0016\u0010@\u001a\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030?0>H\u0016J\n\u0010B\u001a\u0004\u0018\u00010AH\u0014J\b\u0010D\u001a\u00020CH\u0016J\"\u0010I\u001a\u00020\b2\b\u0010F\u001a\u0004\u0018\u00010E2\u0006\u0010G\u001a\u00020\u00132\u0006\u0010H\u001a\u00020\u0013H\u0014J\b\u0010J\u001a\u00020\bH\u0014J\n\u0010L\u001a\u0004\u0018\u00010KH\u0014J\b\u0010M\u001a\u00020\bH\u0014J\b\u0010N\u001a\u00020\bH\u0016J\b\u0010O\u001a\u00020\bH\u0014J\b\u0010P\u001a\u00020\u0013H\u0014J\u0012\u0010S\u001a\u00020\b2\b\u0010R\u001a\u0004\u0018\u00010QH\u0016J\u0010\u0010V\u001a\u00020\b2\u0006\u0010U\u001a\u00020TH\u0016J\u001a\u0010Y\u001a\u00020\b2\u0006\u0010X\u001a\u00020W2\b\u0010!\u001a\u0004\u0018\u00010 H\u0016J\b\u0010Z\u001a\u00020\bH\u0016J\u0010\u0010Z\u001a\u00020\b2\u0006\u0010X\u001a\u00020[H\u0016J\n\u0010]\u001a\u0004\u0018\u00010\\H\u0014J\u0010\u0010`\u001a\u00020\b2\u0006\u0010_\u001a\u00020^H\u0016J\b\u0010a\u001a\u00020\u0011H\u0016R8\u0010e\u001a&\u0012\f\u0012\n d*\u0004\u0018\u00010c0c d*\u0012\u0012\f\u0012\n d*\u0004\u0018\u00010c0c\u0018\u00010b0b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\be\u0010fR\u0014\u0010h\u001a\u00020g8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bh\u0010iR\u0014\u0010k\u001a\u00020j8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bk\u0010lR\u0016\u0010m\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bm\u0010nR\u0018\u0010p\u001a\u0004\u0018\u00010o8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bp\u0010qR\u0018\u0010s\u001a\u0004\u0018\u00010r8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bs\u0010tR\u0016\u0010v\u001a\u00020u8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bv\u0010wR\u0016\u0010x\u001a\u00020Q8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bx\u0010yR\u0014\u0010z\u001a\u00020\u00138\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bz\u0010{R\u0018\u0010}\u001a\u0004\u0018\u00010|8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b}\u0010~R\u001b\u0010\u0080\u0001\u001a\u0004\u0018\u00010\u007f8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0080\u0001\u0010\u0081\u0001R\u0018\u0010\u0082\u0001\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\u0082\u0001\u0010nR!\u0010\u0088\u0001\u001a\u00030\u0083\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b\u0084\u0001\u0010\u0085\u0001\u001a\u0006\b\u0086\u0001\u0010\u0087\u0001¨\u0006\u008c\u0001"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/LibraryTracksFragment;", "Lcom/amazon/mp3/catalog/fragment/LibraryBaseViewsFragment;", "Lcom/amazon/music/views/library/providers/RefinementCallback;", "Lcom/amazon/mp3/fragment/headerbar/OverflowMenuReceiver;", "Lcom/amazon/mp3/fragment/viewmodel/OnFilterChangedListener;", "Lcom/amazon/music/views/library/providers/EmptyStateActionProvider;", "Landroidx/recyclerview/widget/RecyclerView;", "recyclerView", "", "requestCurrentPageData", "Lcom/amazon/mp3/detailpages/track/ScrollDirection;", "getScrollDirection", "", "Lcom/amazon/mp3/view/refinements/RefinementFilterType;", "getDefaultSelectedRefinements", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "contentMetadata", "", "positionToStart", "", "shuffle", "startPlayback", "(Lcom/amazon/music/views/library/metadata/ContentMetadata;Ljava/lang/Integer;Z)V", "", "Lcom/amazon/music/views/library/models/PageHeaderActionIconModel$ActionType;", "Lcom/amazon/music/views/library/binders/ActionIconOnClickListener;", "getPageHeaderOnClickListenerMap", "initializeActionBarManager", "Lcom/amazon/ui/foundations/views/BaseButton;", "button", "initEmptyViewResetFiltersButton", "initializeRefinementOverflowManager", "Landroid/view/View;", "view", "Landroid/os/Bundle;", "savedInstanceState", "onViewCreated", "getEmptyLayoutID", "()Ljava/lang/Integer;", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "getInflatedRootView", "getLoadingViewId", "onCreate", "onResume", "onLoadInitPage", "isDisplayingOnlyLibraryContent", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "getPageType", "emitUiPageViewMetric", "Landroidx/fragment/app/FragmentActivity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "initPageModelProvider", "selected", "downloadSelectionChanged", "Lcom/amazon/music/views/library/providers/RowButtonOnClickProvider;", "getRowButtonOnClickProvider", "shouldUseCachedSource", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageDataRepository;", "getPageDataRepository", "", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "getBinderList", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "getContentEnabilityProvider", "Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsDiffUtilFactory;", "getDiffUtilFactory", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "pageGridViewModel", "isInitialPage", "isMorePage", "updatePageModel", "onLoadUpdatedPage", "Lcom/amazon/music/views/library/deeplinks/ContentMetadataOnClickListener;", "getMetadataClickListener", "setupViews", "onDestroy", "onLoadMorePage", "shouldShowFastScroller", "Lcom/amazon/mp3/actionbar/ActionBarProvider;", "provider", "setActionBarProvider", "Lcom/amazon/music/views/library/models/FilterModel;", "filterModel", "onChildFilterClick", "Lcom/amazon/music/views/library/models/RefinementPillModel;", "model", "onClick", "onFiltersReset", "Lcom/amazon/music/views/library/models/FilterCollectionModel;", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/PageLoadLatencyCode;", "getPageLoadLatencyCode", "Lcom/amazon/music/views/library/models/EmptyStateButtonType;", "buttonType", "onEmptyStateButtonClicked", "getPageSize", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "Lcom/amazon/mp3/detailpages/track/ScrollPayload;", "kotlin.jvm.PlatformType", "pageRequestSubject", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "Lio/reactivex/rxjava3/disposables/Disposable;", "pageRequestDisposable", "Lio/reactivex/rxjava3/disposables/Disposable;", "com/amazon/mp3/catalog/fragment/LibraryTracksFragment$onScrollListener$1", "onScrollListener", "Lcom/amazon/mp3/catalog/fragment/LibraryTracksFragment$onScrollListener$1;", "lastVerticalScrollOffset", "I", "Lcom/amazon/mp3/view/refinements/BaseViewsRefinementOverflowManager;", "refinementOverflowManager", "Lcom/amazon/mp3/view/refinements/BaseViewsRefinementOverflowManager;", "Lcom/amazon/music/views/library/models/ExposedRefinementListModel;", "refinementsModel", "Lcom/amazon/music/views/library/models/ExposedRefinementListModel;", "Lcom/amazon/mp3/actionbar/BaseViewsFilterActionBarManager$InfoProvider;", "libraryInfoProvider", "Lcom/amazon/mp3/actionbar/BaseViewsFilterActionBarManager$InfoProvider;", "actionBarProvider", "Lcom/amazon/mp3/actionbar/ActionBarProvider;", "usesPagination", "Z", "Lcom/amazon/mp3/detailpages/track/LibraryTracksViewModel;", "libraryTracksViewModel", "Lcom/amazon/mp3/detailpages/track/LibraryTracksViewModel;", "Lcom/amazon/mp3/actionbar/BaseViewsFilterActionBarManager;", "actionBarManager", "Lcom/amazon/mp3/actionbar/BaseViewsFilterActionBarManager;", "actionBarHeight", "Lcom/amazon/mp3/detailpages/actions/DefaultTrackInformationFetcher;", "trackInformationFetcher$delegate", "Lkotlin/Lazy;", "getTrackInformationFetcher", "()Lcom/amazon/mp3/detailpages/actions/DefaultTrackInformationFetcher;", "trackInformationFetcher", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class LibraryTracksFragment extends LibraryBaseViewsFragment implements RefinementCallback, OverflowMenuReceiver, OnFilterChangedListener, EmptyStateActionProvider {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = LibraryTracksFragment.class.getSimpleName();
    private int actionBarHeight;
    private BaseViewsFilterActionBarManager actionBarManager;
    private ActionBarProvider actionBarProvider;
    private int lastVerticalScrollOffset;
    private BaseViewsFilterActionBarManager.InfoProvider libraryInfoProvider;
    private LibraryTracksViewModel libraryTracksViewModel;
    private final LibraryTracksFragment$onScrollListener$1 onScrollListener;
    private final Disposable pageRequestDisposable;
    private final PublishSubject<ScrollPayload> pageRequestSubject;
    private BaseViewsRefinementOverflowManager refinementOverflowManager;
    private ExposedRefinementListModel refinementsModel;

    /* JADX INFO: renamed from: trackInformationFetcher$delegate, reason: from kotlin metadata */
    private final Lazy trackInformationFetcher;
    private final boolean usesPagination;

    @JvmStatic
    public static final Uri getUri() {
        return INSTANCE.getUri();
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment
    protected Integer getEmptyLayoutID() {
        return null;
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected int getLoadingViewId() {
        return R.id.detail_loading_spinner;
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment
    protected boolean isDisplayingOnlyLibraryContent() {
        return true;
    }

    @Override // com.amazon.music.views.library.providers.RefinementCallback
    public void onChildFilterClick(FilterModel filterModel) {
        Intrinsics.checkNotNullParameter(filterModel, "filterModel");
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected boolean shouldShowFastScroller() {
        return true;
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment
    protected boolean shouldUseCachedSource() {
        return true;
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [com.amazon.mp3.catalog.fragment.LibraryTracksFragment$onScrollListener$1] */
    public LibraryTracksFragment() {
        PublishSubject<ScrollPayload> publishSubjectCreate = PublishSubject.create();
        this.pageRequestSubject = publishSubjectCreate;
        Disposable disposableSubscribe = publishSubjectCreate.debounce(100L, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.computation()).observeOn(Schedulers.single()).filter(new Predicate() { // from class: com.amazon.mp3.catalog.fragment.LibraryTracksFragment$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                return LibraryTracksFragment.m516pageRequestDisposable$lambda0(this.f$0, (ScrollPayload) obj);
            }
        }).subscribe(new Consumer() { // from class: com.amazon.mp3.catalog.fragment.LibraryTracksFragment$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                LibraryTracksFragment.m517pageRequestDisposable$lambda2(this.f$0, (ScrollPayload) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "pageRequestSubject.debou…          }\n            }");
        this.pageRequestDisposable = disposableSubscribe;
        this.onScrollListener = new RecyclerView.OnScrollListener() { // from class: com.amazon.mp3.catalog.fragment.LibraryTracksFragment$onScrollListener$1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
                this.this$0.requestCurrentPageData(recyclerView);
            }
        };
        this.usesPagination = AmazonApplication.getCapabilities().shouldEnablePaginationForBauhausPages();
        this.trackInformationFetcher = LazyKt__LazyJVMKt.lazy(new Function0<DefaultTrackInformationFetcher>() { // from class: com.amazon.mp3.catalog.fragment.LibraryTracksFragment$trackInformationFetcher$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DefaultTrackInformationFetcher invoke() {
                return new DefaultTrackInformationFetcher(new GenericBaseViewModelFetcher(new Function1<BaseViewModel, Boolean>() { // from class: com.amazon.mp3.catalog.fragment.LibraryTracksFragment$trackInformationFetcher$2$trackFilter$1
                    @Override // kotlin.jvm.functions.Function1
                    public final Boolean invoke(BaseViewModel model) {
                        Intrinsics.checkNotNullParameter(model, "model");
                        return Boolean.valueOf((model instanceof BaseViewContentModel) && (((BaseViewContentModel) model).getMetadata() instanceof TrackMetadata));
                    }
                }));
            }
        });
    }

    @Override // com.amazon.mp3.fragment.viewmodel.OnFilterChangedListener
    public boolean shouldOverrideResetFilters() {
        return OnFilterChangedListener.DefaultImpls.shouldOverrideResetFilters(this);
    }

    @Override // com.amazon.mp3.fragment.viewmodel.OnFilterChangedListener
    public boolean shouldOverrideSelectionChange(String str, boolean z) {
        return OnFilterChangedListener.DefaultImpls.shouldOverrideSelectionChange(this, str, z);
    }

    /* JADX INFO: compiled from: LibraryTracksFragment.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u00020\u000bH\u0007J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/LibraryTracksFragment$Companion;", "", "()V", "DEFAULT_TRACK_PLAYBACK_START_POSITION", "", "REQUEST_DEBOUNCE_TIME_MILLIS", "", "TAG", "", "kotlin.jvm.PlatformType", "getUri", "Landroid/net/Uri;", PageUriUtils.SOURCE_QUERY_PARAM, "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Uri getUri() {
            Uri filterContentUri = MediaProvider.Tracks.getFilterContentUri("cirrus", "");
            Intrinsics.checkNotNullExpressionValue(filterContentUri, "getFilterContentUri(Cirr…ediaSource.ID_CIRRUS, \"\")");
            return filterContentUri;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Uri getUri(String source) {
            Uri filterContentUri = MediaProvider.Tracks.getFilterContentUri(source, "");
            Intrinsics.checkNotNullExpressionValue(filterContentUri, "getFilterContentUri(source, \"\")");
            return filterContentUri;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: pageRequestDisposable$lambda-0, reason: not valid java name */
    public static final boolean m516pageRequestDisposable$lambda0(LibraryTracksFragment this$0, ScrollPayload scrollPayload) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        return this$0.usesPagination;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: pageRequestDisposable$lambda-2, reason: not valid java name */
    public static final void m517pageRequestDisposable$lambda2(LibraryTracksFragment this$0, ScrollPayload scrollPayload) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (scrollPayload == null) {
            return;
        }
        PageViewModelProvider pageModelProvider = this$0.getPageModelProvider();
        LibraryTracksViewModel libraryTracksViewModel = pageModelProvider instanceof LibraryTracksViewModel ? (LibraryTracksViewModel) pageModelProvider : null;
        if (libraryTracksViewModel == null) {
            return;
        }
        libraryTracksViewModel.scrolledToIndex(scrollPayload.getFirstVisibleIndex(), scrollPayload.getLastVisibleIndex(), scrollPayload.getScrollDirection());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void requestCurrentPageData(RecyclerView recyclerView) {
        Integer numValueOf;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        Integer numValueOf2 = null;
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            numValueOf2 = Integer.valueOf(linearLayoutManager.findFirstVisibleItemPosition());
            numValueOf = Integer.valueOf(linearLayoutManager.findLastVisibleItemPosition());
        } else if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            numValueOf2 = Integer.valueOf(gridLayoutManager.findFirstVisibleItemPosition());
            numValueOf = Integer.valueOf(gridLayoutManager.findLastVisibleItemPosition());
        } else {
            numValueOf = null;
        }
        if (numValueOf2 == null || numValueOf == null) {
            return;
        }
        ScrollDirection scrollDirection = getScrollDirection(recyclerView);
        if (scrollDirection != null) {
            this.pageRequestSubject.onNext(new ScrollPayload(numValueOf2.intValue(), numValueOf.intValue(), scrollDirection));
        }
        this.lastVerticalScrollOffset = recyclerView.computeVerticalScrollOffset();
    }

    private final ScrollDirection getScrollDirection(RecyclerView recyclerView) {
        int iComputeVerticalScrollOffset = recyclerView.computeVerticalScrollOffset();
        int i = this.lastVerticalScrollOffset;
        if (iComputeVerticalScrollOffset > i) {
            return ScrollDirection.DOWN;
        }
        if (iComputeVerticalScrollOffset < i) {
            return ScrollDirection.UP;
        }
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        ViewGroup root = getRoot();
        BaseButton baseButton = root == null ? null : (BaseButton) root.findViewById(R.id.resetFilterButton);
        if (baseButton == null) {
            return;
        }
        initEmptyViewResetFiltersButton(baseButton);
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected ViewGroup getInflatedRootView(LayoutInflater inflater, ViewGroup container) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R.layout.fragment_detail, container, false);
        if (viewInflate != null) {
            return (ViewGroup) viewInflate;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup");
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment, com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.libraryInfoProvider = new InfoProviderLibraryImpl(this.actionBarHeight, getRecyclerView());
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        initLibraryStateObserver();
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected void onLoadInitPage() {
        super.onLoadInitPage();
        RecyclerView recyclerView = getRecyclerView();
        RecyclerView.Adapter adapter = recyclerView == null ? null : recyclerView.getAdapter();
        UniversalAdapter universalAdapter = adapter instanceof UniversalAdapter ? (UniversalAdapter) adapter : null;
        this.refinementsModel = universalAdapter != null ? RefinementKt.getRefinementsModel(universalAdapter) : null;
    }

    private final DefaultTrackInformationFetcher getTrackInformationFetcher() {
        return (DefaultTrackInformationFetcher) this.trackInformationFetcher.getValue();
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public PageType getPageType() {
        return isSourceLocal() ? PageType.OFFLINE_LIBRARY_SONGS : PageType.CLOUD_LIBRARY_SONGS;
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public void emitUiPageViewMetric() {
        AbstractBaseViewsFragment.sendUiPageViewMetric$default(this, getPageType(), null, null, null, null, null, null, null, 254, null);
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment, com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public void initPageModelProvider(FragmentActivity activity) {
        LibraryTracksViewModel libraryTracksViewModel;
        Intrinsics.checkNotNullParameter(activity, "activity");
        Function0<Unit> function0 = new Function0<Unit>() { // from class: com.amazon.mp3.catalog.fragment.LibraryTracksFragment$initPageModelProvider$refinementSelectionOccurred$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                RecyclerView recyclerView = this.this$0.getRecyclerView();
                if (recyclerView == null) {
                    return;
                }
                recyclerView.scrollToPosition(0);
            }
        };
        PageDataRepository pageDataRepository = getPageDataRepository();
        RefinablePageDataRepository refinablePageDataRepository = pageDataRepository instanceof RefinablePageDataRepository ? (RefinablePageDataRepository) pageDataRepository : null;
        if (refinablePageDataRepository != null) {
            PageViewModelFactoryProvider pageViewModelFactoryProvider = PageViewModelFactoryProvider.INSTANCE;
            Application application = activity.getApplication();
            Intrinsics.checkNotNullExpressionValue(application, "activity.application");
            setPageModelProvider(pageViewModelFactoryProvider.getViewModel(this, new PageViewModelFactoryProvider.LibraryTracksViewModelFactory(application, refinablePageDataRepository, getDefaultSelectedRefinements(), function0)));
        }
        PageViewModelProvider pageModelProvider = getPageModelProvider();
        RefinableViewModel refinableViewModel = pageModelProvider instanceof RefinableViewModel ? (RefinableViewModel) pageModelProvider : null;
        if (refinableViewModel != null) {
            refinableViewModel.setFilterListener(this);
        }
        LibraryTracksViewModel libraryTracksViewModel2 = this.libraryTracksViewModel;
        if (libraryTracksViewModel2 != null) {
            libraryTracksViewModel2.unregisterDownloadStateListener();
        }
        PageViewModelProvider pageModelProvider2 = getPageModelProvider();
        LibraryTracksViewModel libraryTracksViewModel3 = pageModelProvider2 instanceof LibraryTracksViewModel ? (LibraryTracksViewModel) pageModelProvider2 : null;
        this.libraryTracksViewModel = libraryTracksViewModel3;
        if (libraryTracksViewModel3 != null) {
            libraryTracksViewModel3.registerDownloadStateListener(getContentUri());
        }
        ContentEnabilityProvider contentEnabilityProvider = getContentEnabilityProvider();
        if (contentEnabilityProvider == null || (libraryTracksViewModel = this.libraryTracksViewModel) == null) {
            return;
        }
        libraryTracksViewModel.setContentEnabilityProvider(contentEnabilityProvider);
    }

    private final Set<RefinementFilterType> getDefaultSelectedRefinements() {
        Bundle arguments = getArguments();
        Set<RefinementFilterType> enabledRefinementFilters$default = arguments != null ? RefinementFilterType.Companion.getEnabledRefinementFilters$default(RefinementFilterType.INSTANCE, arguments, null, 2, null) : null;
        return ((enabledRefinementFilters$default == null || enabledRefinementFilters$default.isEmpty()) && isSourceLocal()) ? SetsKt__SetsJVMKt.setOf(RefinementFilterType.DOWNLOADED) : enabledRefinementFilters$default;
    }

    @Override // com.amazon.mp3.fragment.viewmodel.OnFilterChangedListener
    public void downloadSelectionChanged(boolean selected) {
        updateSource(selected ? "cirrus-local" : "cirrus");
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment, com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected RowButtonOnClickProvider getRowButtonOnClickProvider(FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (getRowButtonOnClickProvider() == null) {
            if (AmazonApplication.getCapabilities().isLikesEverywhereEnabled()) {
                setRowButtonOnClickProvider(new DefaultLikesProvider(activity, getLibraryStateProviderInstance(), getTrackContextMenuListener(), true, getPageType()));
            } else {
                setRowButtonOnClickProvider(new DefaultAddToLibraryProvider(activity, getLibraryStateProviderInstance(), getTrackContextMenuListener()));
            }
        }
        return getRowButtonOnClickProvider();
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public PageDataRepository getPageDataRepository() {
        Context context = getContext();
        Companion companion = INSTANCE;
        return new RefinablePageDataRepository(new LibraryTracksDao(context, companion.getUri("cirrus"), companion.getUri("cirrus-local"), getPageType(), getPageLoadLatencyCode(), getDefaultSelectedRefinements()));
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public List<UniversalBinder<?, ?>> getBinderList() {
        BaseViewsBinderConfiguration viewsConfiguration = getViewsConfiguration();
        List<UniversalBinder<?, ?>> listBuild = viewsConfiguration != null ? BaseViewsBinderProvider.BinderListBuilder.withVisualRowItems$default(new BaseViewsBinderProvider.BinderListBuilder(viewsConfiguration).withDefaultHeader(getPageHeaderOnClickListenerMap(), getPageType()).withRefinements(this), null, null, 3, null).withEmptyView(this).build() : null;
        return listBuild == null ? CollectionsKt__CollectionsKt.emptyList() : listBuild;
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected ContentEnabilityProvider getContentEnabilityProvider() {
        Context context = getContext();
        Context applicationContext = context == null ? null : context.getApplicationContext();
        if (applicationContext == null) {
            applicationContext = AmazonApplication.getApplication().getApplicationContext();
        }
        Intrinsics.checkNotNullExpressionValue(applicationContext, "this.context?.applicatio…tion().applicationContext");
        return new LibraryContentEnabilityProvider(applicationContext, getActivity());
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public BaseViewsDiffUtilFactory getDiffUtilFactory() {
        return new BaseViewsDiffUtilFactory() { // from class: com.amazon.mp3.catalog.fragment.LibraryTracksFragment.getDiffUtilFactory.1
            @Override // com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtilFactory
            public BaseViewsDiffUtil getBaseViewsDiffUtil(List<? extends BaseViewModel> oldList, List<? extends BaseViewModel> newList) {
                Intrinsics.checkNotNullParameter(oldList, "oldList");
                Intrinsics.checkNotNullParameter(newList, "newList");
                return new BaseViewsDiffUtil(oldList, newList);
            }
        };
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected void updatePageModel(PageGridViewModel pageGridViewModel, boolean isInitialPage, boolean isMorePage) {
        super.updatePageModel(pageGridViewModel, isInitialPage, isMorePage);
        LibraryTracksViewModel libraryTracksViewModel = this.libraryTracksViewModel;
        if (libraryTracksViewModel != null) {
            libraryTracksViewModel.updateAvailabilityState();
        }
        getTrackInformationFetcher().setPageModel(getPageModel());
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected void onLoadUpdatedPage() {
        PageFetchInfo pageFetchInfoCheckLastPageFetchInfo;
        IntRange indicesChanged;
        LibraryTracksViewModel libraryTracksViewModel = this.libraryTracksViewModel;
        Unit unit = null;
        if (libraryTracksViewModel != null && (pageFetchInfoCheckLastPageFetchInfo = libraryTracksViewModel.getLastFetchedPageInfo()) != null && (indicesChanged = pageFetchInfoCheckLastPageFetchInfo.getIndicesChanged()) != null) {
            onLoadPageRangeChanged(indicesChanged);
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            super.onLoadUpdatedPage();
        }
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected ContentMetadataOnClickListener getMetadataClickListener() {
        return new ContentMetadataOnClickListener() { // from class: com.amazon.mp3.catalog.fragment.LibraryTracksFragment.getMetadataClickListener.1
            @Override // com.amazon.music.views.library.deeplinks.ContentMetadataOnClickListener
            public void onContentListClicked(List<? extends ContentMetadata> contentMetadataList, int positionToStart, String contentListTitle) {
                Intrinsics.checkNotNullParameter(contentMetadataList, "contentMetadataList");
            }

            @Override // com.amazon.music.views.library.deeplinks.ContentMetadataOnClickListener
            public void onContentClicked(ContentMetadata contentMetadata) {
                Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
                boolean z = contentMetadata instanceof TrackMetadata;
                MusicTrack track = z ? new CirrusSourceLibraryItemFactory(AmazonApplication.getApplication().getApplicationContext()).getTrack(contentMetadata.getUri()) : null;
                ContentEnabilityProvider contentEnabilityProvider = LibraryTracksFragment.this.getContentEnabilityProvider();
                if (!ConnectivityUtil.hasAnyInternetConnection(LibraryTracksFragment.this.getActivity()) && !LibraryAccessProviderImpl.getInstance(LibraryTracksFragment.this.getActivity()).isAvailableOffline(track, true)) {
                    if (contentEnabilityProvider != null) {
                        contentEnabilityProvider.handleContentAccessUnavailable(contentMetadata);
                        return;
                    } else {
                        ConnectivityUtil.checkConnectivityAndShowDialog(LibraryTracksFragment.this.getActivity());
                        return;
                    }
                }
                if ((contentEnabilityProvider == null || contentEnabilityProvider.isContentAccessAvailable(contentMetadata)) ? false : true) {
                    contentEnabilityProvider.handleContentAccessUnavailable(contentMetadata);
                } else if (z) {
                    LibraryTracksFragment.startPlayback$default(LibraryTracksFragment.this, contentMetadata, ((TrackMetadata) contentMetadata).getTrackPosition(), false, 4, null);
                }
            }
        };
    }

    static /* synthetic */ void startPlayback$default(LibraryTracksFragment libraryTracksFragment, ContentMetadata contentMetadata, Integer num, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            contentMetadata = null;
        }
        if ((i & 2) != 0) {
            num = null;
        }
        if ((i & 4) != 0) {
            z = false;
        }
        libraryTracksFragment.startPlayback(contentMetadata, num, z);
    }

    private final void startPlayback(ContentMetadata contentMetadata, Integer positionToStart, boolean shuffle) {
        Context context = getContext();
        if (context == null) {
            return;
        }
        PageViewModelProvider pageModelProvider = getPageModelProvider();
        LibraryTracksViewModel libraryTracksViewModel = pageModelProvider instanceof LibraryTracksViewModel ? (LibraryTracksViewModel) pageModelProvider : null;
        if (libraryTracksViewModel != null) {
            libraryTracksViewModel.handlePlayback(context, INSTANCE.getUri(getSourceId()), contentMetadata, positionToStart, Boolean.valueOf(shuffle));
        }
        AbstractBaseViewsFragment.sendUiClickMetric$default(this, shuffle ? ActionType.PLAY_SHUFFLE_ALL : ActionType.PLAY_ALL, null, getPageType(), null, null, EntityIdType.CD_OBJECT_ID, null, -1, null, null, null, null, 3930, null);
    }

    private final Map<PageHeaderActionIconModel.ActionType, ActionIconOnClickListener> getPageHeaderOnClickListenerMap() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(PageHeaderActionIconModel.ActionType.PLAY, new ActionIconOnClickListener() { // from class: com.amazon.mp3.catalog.fragment.LibraryTracksFragment.getPageHeaderOnClickListenerMap.1
            @Override // com.amazon.music.views.library.binders.ActionIconOnClickListener
            public void onClick(Context context, ContentMetadata metadata) {
                Intrinsics.checkNotNullParameter(context, "context");
                if (!LibraryTracksFragment.this.isSourceLocal() && UserSubscriptionUtil.isNightwingOnly()) {
                    UpsellUtil.showBlockingUpsell(LibraryTracksFragment.this.getActivity(), null, UpsellReason.SHUFFLE_ALL, UpsellSourceEntity.TRACK);
                } else {
                    LibraryTracksFragment.startPlayback$default(LibraryTracksFragment.this, null, 0, false, 5, null);
                }
            }
        });
        linkedHashMap.put(PageHeaderActionIconModel.ActionType.SHUFFLE, new LargeIconOnClickListener() { // from class: com.amazon.mp3.catalog.fragment.LibraryTracksFragment.getPageHeaderOnClickListenerMap.2
            @Override // com.amazon.music.views.library.binders.LargeIconOnClickListener
            public void onClick(Context context, ContentMetadata metadata, boolean isLargeIcon) {
                Intrinsics.checkNotNullParameter(context, "context");
                if (!LibraryTracksFragment.this.isSourceLocal() && UserSubscriptionUtil.isNightwingOnly()) {
                    UpsellUtil.showBlockingUpsell(LibraryTracksFragment.this.getActivity(), null, UpsellReason.SHUFFLE_ALL, UpsellSourceEntity.TRACK);
                } else {
                    LibraryTracksFragment.startPlayback$default(LibraryTracksFragment.this, null, null, true, 3, null);
                }
            }
        });
        return linkedHashMap;
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected void setupViews() {
        super.setupViews();
        this.libraryInfoProvider = new InfoProviderLibraryImpl(this.actionBarHeight, getRecyclerView());
        setActionBarProvider((ActionBarProvider) requireActivity());
        initializeActionBarManager();
        initializeRefinementOverflowManager();
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager = this.actionBarManager;
        if (baseViewsFilterActionBarManager != null) {
            getLifecycle().addObserver(baseViewsFilterActionBarManager);
        }
        BaseViewsBinderConfiguration viewsConfiguration = getViewsConfiguration();
        if (viewsConfiguration != null) {
            RefinementPillBinder refinementPillBinder = new RefinementPillBinder(viewsConfiguration.getStyleSheet(), this);
            BaseViewModelAdapterProvider adapterProvider = viewsConfiguration.getAdapterProvider();
            BaseViewsFilterActionBarManager.InfoProvider infoProvider = this.libraryInfoProvider;
            if (infoProvider == null) {
                Intrinsics.throwUninitializedPropertyAccessException("libraryInfoProvider");
                infoProvider = null;
            }
            List<FilterModel> refinementFilterModels = infoProvider.getRefinementFilterModels();
            BaseViewsFilterActionBarManager baseViewsFilterActionBarManager2 = this.actionBarManager;
            if (baseViewsFilterActionBarManager2 != null) {
                baseViewsFilterActionBarManager2.setAdapterProviderForActionBarView(adapterProvider, refinementPillBinder, refinementFilterModels);
            }
        }
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager3 = this.actionBarManager;
        int actionBarHeight = baseViewsFilterActionBarManager3 == null ? 0 : baseViewsFilterActionBarManager3.getActionBarHeight();
        KeyEventDispatcher.Component activity = getActivity();
        ActionBarOverlayable actionBarOverlayable = activity instanceof ActionBarOverlayable ? (ActionBarOverlayable) activity : null;
        if (actionBarOverlayable != null) {
            actionBarOverlayable.setContentOffset(getRecyclerView(), actionBarHeight);
        }
        RecyclerView recyclerView = getRecyclerView();
        if (recyclerView != null) {
            recyclerView.addOnScrollListener(this.onScrollListener);
        }
        FastScroller fastScroller = getFastScroller();
        if (fastScroller == null) {
            return;
        }
        fastScroller.addScrollStoppedListener(new Function0<Unit>() { // from class: com.amazon.mp3.catalog.fragment.LibraryTracksFragment.setupViews.3
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                RecyclerView recyclerView2 = LibraryTracksFragment.this.getRecyclerView();
                if (recyclerView2 == null) {
                    return;
                }
                LibraryTracksFragment.this.requestCurrentPageData(recyclerView2);
            }
        });
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment, com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.pageRequestDisposable.dispose();
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager = this.actionBarManager;
        if (baseViewsFilterActionBarManager != null) {
            getLifecycle().removeObserver(baseViewsFilterActionBarManager);
        }
        LibraryTracksViewModel libraryTracksViewModel = this.libraryTracksViewModel;
        if (libraryTracksViewModel != null) {
            libraryTracksViewModel.clearListeners();
        }
        RecyclerView recyclerView = getRecyclerView();
        if (recyclerView == null) {
            return;
        }
        recyclerView.removeOnScrollListener(this.onScrollListener);
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected void onLoadMorePage() {
        IntRange indicesToUpdate;
        LibraryTracksViewModel libraryTracksViewModel = this.libraryTracksViewModel;
        if (libraryTracksViewModel != null && (indicesToUpdate = libraryTracksViewModel.getLastUpdatedIndices()) != null) {
            PageGridViewModel pageModel = getPageModel();
            if (pageModel == null) {
                return;
            }
            BaseRecyclerViewProvider recyclerViewProvider = getRecyclerViewProvider();
            if (recyclerViewProvider != null) {
                recyclerViewProvider.updateAdapterForRange(pageModel.getModels(), indicesToUpdate.getFirst(), CollectionsKt___CollectionsKt.count(indicesToUpdate));
            }
            LibraryTracksViewModel libraryTracksViewModel2 = this.libraryTracksViewModel;
            if (libraryTracksViewModel2 == null) {
                return;
            }
            libraryTracksViewModel2.hasUpdatedPage();
            return;
        }
        super.onLoadMorePage();
    }

    @Override // com.amazon.mp3.fragment.headerbar.OverflowMenuReceiver
    public void setActionBarProvider(ActionBarProvider provider) {
        if (provider == null) {
            return;
        }
        this.actionBarProvider = provider;
    }

    private final void initializeActionBarManager() {
        BaseViewsFilterActionBarManager.InfoProvider infoProvider = this.libraryInfoProvider;
        if (infoProvider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("libraryInfoProvider");
            infoProvider = null;
        }
        RecyclerView recyclerView = getRecyclerView();
        ActionBarProvider actionBarProvider = this.actionBarProvider;
        if (actionBarProvider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("actionBarProvider");
            actionBarProvider = null;
        }
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager = new BaseViewsFilterActionBarManager(this, infoProvider, recyclerView, actionBarProvider);
        this.actionBarManager = baseViewsFilterActionBarManager;
        baseViewsFilterActionBarManager.shouldShowFilterButton();
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager2 = this.actionBarManager;
        if (baseViewsFilterActionBarManager2 != null) {
            BaseViewsFilterActionBarManager.shouldShowExposedRefinementList$default(baseViewsFilterActionBarManager2, false, 1, null);
        }
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager3 = this.actionBarManager;
        if (baseViewsFilterActionBarManager3 == null) {
            return;
        }
        baseViewsFilterActionBarManager3.shouldShowPageComponentInTopAppBar();
    }

    private final void initEmptyViewResetFiltersButton(final BaseButton button) {
        getSubscriptions().add(StyleSheetProvider.getStyleSheetObservable().subscribe(new Action1() { // from class: com.amazon.mp3.catalog.fragment.LibraryTracksFragment$$ExternalSyntheticLambda3
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                LibraryTracksFragment.m513initEmptyViewResetFiltersButton$lambda17(this.f$0, button, (StyleSheet) obj);
            }
        }));
        button.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.catalog.fragment.LibraryTracksFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LibraryTracksFragment.m514initEmptyViewResetFiltersButton$lambda18(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initEmptyViewResetFiltersButton$lambda-17, reason: not valid java name */
    public static final void m513initEmptyViewResetFiltersButton$lambda17(LibraryTracksFragment this$0, BaseButton button, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(button, "$button");
        BaseButton.StyleBuilder buttonBuilder = styleSheet.getButtonBuilder(ButtonSizeKey.MEDIUM, ButtonStyleKey.SOLID);
        if (buttonBuilder == null) {
            return;
        }
        String string = this$0.getResources().getString(R.string.dmusic_reset_all_filters);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…dmusic_reset_all_filters)");
        BaseButton.StyleBuilder styleBuilderWithText = buttonBuilder.withText(string);
        if (styleBuilderWithText == null) {
            return;
        }
        styleBuilderWithText.applyStyle(button);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initEmptyViewResetFiltersButton$lambda-18, reason: not valid java name */
    public static final void m514initEmptyViewResetFiltersButton$lambda18(LibraryTracksFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onFiltersReset();
    }

    private final void initializeRefinementOverflowManager() {
        if (getContext() == null) {
            return;
        }
        getSubscriptions().add(StyleSheetProvider.getStyleSheetObservable().subscribe(new Action1() { // from class: com.amazon.mp3.catalog.fragment.LibraryTracksFragment$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                LibraryTracksFragment.m515initializeRefinementOverflowManager$lambda20$lambda19(this.f$0, (StyleSheet) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initializeRefinementOverflowManager$lambda-20$lambda-19, reason: not valid java name */
    public static final void m515initializeRefinementOverflowManager$lambda20$lambda19(LibraryTracksFragment this$0, StyleSheet it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.refinementOverflowManager = new BaseViewsRefinementOverflowManager(this$0, it, this$0);
    }

    @Override // com.amazon.music.views.library.providers.RefinementClickListenerProvider
    public void onClick(RefinementPillModel model, View view) {
        Intrinsics.checkNotNullParameter(model, "model");
        EndlessScrollListener endlessScrollListener = getEndlessScrollListener();
        if (endlessScrollListener != null) {
            endlessScrollListener.reset();
        }
        PageViewModelProvider pageModelProvider = getPageModelProvider();
        RefinableViewModel refinableViewModel = pageModelProvider instanceof RefinableViewModel ? (RefinableViewModel) pageModelProvider : null;
        if (refinableViewModel == null) {
            return;
        }
        refinableViewModel.onRefinementClicked(this.refinementOverflowManager, getDiffUtilFactory(), getPageType(), model, view);
    }

    @Override // com.amazon.music.views.library.providers.RefinementCallback
    public void onFiltersReset() {
        ExposedRefinementListModel exposedRefinementListModel = this.refinementsModel;
        if (exposedRefinementListModel != null) {
            RefinementKt.resetRefinementFilterModels(exposedRefinementListModel);
        }
        EndlessScrollListener endlessScrollListener = getEndlessScrollListener();
        if (endlessScrollListener != null) {
            endlessScrollListener.reset();
        }
        PageViewModelProvider pageModelProvider = getPageModelProvider();
        RefinableViewModel refinableViewModel = pageModelProvider instanceof RefinableViewModel ? (RefinableViewModel) pageModelProvider : null;
        if (refinableViewModel == null) {
            return;
        }
        refinableViewModel.onFilterReset(getDiffUtilFactory());
    }

    @Override // com.amazon.music.views.library.providers.RefinementCallback
    public void onFiltersReset(FilterCollectionModel model) {
        Intrinsics.checkNotNullParameter(model, "model");
        PageViewModelProvider pageModelProvider = getPageModelProvider();
        RefinableViewModel refinableViewModel = pageModelProvider instanceof RefinableViewModel ? (RefinableViewModel) pageModelProvider : null;
        if (refinableViewModel == null) {
            return;
        }
        refinableViewModel.onFilterReset(model);
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected PageLoadLatencyCode getPageLoadLatencyCode() {
        return PageLoadLatencyCode.LIBRARY_TRACKS;
    }

    @Override // com.amazon.music.views.library.providers.EmptyStateActionProvider
    public void onEmptyStateButtonClicked(EmptyStateButtonType buttonType) {
        Intrinsics.checkNotNullParameter(buttonType, "buttonType");
        if (buttonType instanceof EmptyStateButtonType.EXPLORE) {
            new EUXDestinationLauncher(getContext()).launchHome();
        } else if (buttonType instanceof EmptyStateButtonType.RESET) {
            onFiltersReset();
        } else if (buttonType instanceof EmptyStateButtonType.BUY) {
            new EUXDestinationLauncher(getContext()).launchStore();
        }
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public int getPageSize() {
        LibraryTracksViewModel libraryTracksViewModel = this.libraryTracksViewModel;
        Integer numValueOf = libraryTracksViewModel == null ? null : Integer.valueOf(libraryTracksViewModel.getPageSize());
        return numValueOf == null ? LibraryTracksViewModel.INSTANCE.getPAGE_SIZE() : numValueOf.intValue();
    }
}
