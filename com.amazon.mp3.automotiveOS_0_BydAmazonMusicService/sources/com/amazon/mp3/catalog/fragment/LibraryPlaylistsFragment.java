package com.amazon.mp3.catalog.fragment;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.KeyEventDispatcher;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.actionbar.ActionBarProvider;
import com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager;
import com.amazon.mp3.activity.NetworkErrorDialogActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsBinderConfiguration;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsBinderProvider;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtil;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtilFactory;
import com.amazon.mp3.catalog.fragment.datasource.dao.LibraryPlaylistsDao;
import com.amazon.mp3.catalog.fragment.datasource.repository.PageDataRepository;
import com.amazon.mp3.catalog.fragment.datasource.repository.RefinablePageDataRepository;
import com.amazon.mp3.catalog.fragment.helper.CreatePlaylistHelper;
import com.amazon.mp3.catalog.fragment.provider.InfoProviderLibraryImpl;
import com.amazon.mp3.catalog.fragment.provider.LibraryContentEnabilityProvider;
import com.amazon.mp3.catalog.fragment.viewmodel.CreatePlaylistResultViewModel;
import com.amazon.mp3.catalog.fragment.viewmodel.FetchType;
import com.amazon.mp3.catalog.fragment.viewmodel.PageFetchInfo;
import com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelFactoryProvider;
import com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelProvider;
import com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel;
import com.amazon.mp3.catalog.fragment.viewmodel.RefinementLoadingListener;
import com.amazon.mp3.detailpages.playlist.LibraryPlaylistsViewModel;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.eux.EUXDestinationLauncher;
import com.amazon.mp3.fragment.contextmenu.AbstractPlaylistContextMenuListener;
import com.amazon.mp3.fragment.contextmenu.ContextMenuPageType;
import com.amazon.mp3.fragment.headerbar.OverflowMenuReceiver;
import com.amazon.mp3.fragment.viewmodel.OnFilterChangedListener;
import com.amazon.mp3.library.activity.NumberOfPlaylistsLimitReachedActivity;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.playback.state.PageStateManager;
import com.amazon.mp3.playlist.CreatePlaylistDialogFragment;
import com.amazon.mp3.providers.LibraryContextMenuProvider;
import com.amazon.mp3.providers.MetadataContextMenuHandler;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.util.extensions.RefinementKt;
import com.amazon.mp3.view.refinements.BaseViewsRefinementOverflowManager;
import com.amazon.mp3.view.refinements.RefinementFilterType;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.subscription.UserSubscription;
import com.amazon.music.views.library.binders.ActionIconOnClickListener;
import com.amazon.music.views.library.binders.RefinementPillBinder;
import com.amazon.music.views.library.binders.UniversalBinder;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.models.EmptyStateButtonType;
import com.amazon.music.views.library.models.ExposedRefinementListModel;
import com.amazon.music.views.library.models.FilterCollectionModel;
import com.amazon.music.views.library.models.FilterModel;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.PageHeaderActionIconModel;
import com.amazon.music.views.library.models.RefinementPillModel;
import com.amazon.music.views.library.models.TableRowItemModel;
import com.amazon.music.views.library.models.base.BaseViewContentModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.providers.ContentEnabilityProvider;
import com.amazon.music.views.library.providers.EmptyStateActionProvider;
import com.amazon.music.views.library.providers.RefinementCallback;
import com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider;
import com.amazon.music.views.library.recyclerview.EndlessScrollListener;
import com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.views.presentation.ActionBarOverlayable;
import com.amazon.ui.foundations.views.BaseButton;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;
import rx.functions.Action1;

/* JADX INFO: compiled from: LibraryPlaylistsFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000¬\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 v2\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u0006:\u0001vB\u0005¢\u0006\u0002\u0010\u0007J\b\u0010\u001a\u001a\u00020\u001bH\u0002J\u0010\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0010\u0010\u001f\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\u001eH\u0016J\b\u0010!\u001a\u00020\u001bH\u0016J\u0016\u0010\"\u001a\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030$0#H\u0016J\n\u0010%\u001a\u0004\u0018\u00010&H\u0014J\n\u0010'\u001a\u0004\u0018\u00010(H\u0016J\u0010\u0010)\u001a\n\u0012\u0004\u0012\u00020+\u0018\u00010*H\u0002J\b\u0010,\u001a\u00020-H\u0016J\u000f\u0010.\u001a\u0004\u0018\u00010\tH\u0014¢\u0006\u0002\u0010/J\u001a\u00100\u001a\u0002012\u0006\u00102\u001a\u0002032\b\u00104\u001a\u0004\u0018\u000101H\u0014J\b\u00105\u001a\u00020\tH\u0014J\n\u00106\u001a\u0004\u0018\u000107H\u0016J\u0014\u00108\u001a\u000e\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020;09H\u0002J\n\u0010<\u001a\u0004\u0018\u00010=H\u0014J\b\u0010>\u001a\u00020?H\u0016J\u0016\u0010@\u001a\u00020\u001b2\u0006\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020DJ\u0010\u0010E\u001a\u00020\u001b2\u0006\u0010F\u001a\u00020GH\u0002J\u0010\u0010H\u001a\u00020\u001b2\u0006\u0010I\u001a\u00020JH\u0016J\b\u0010K\u001a\u00020\u001bH\u0002J\b\u0010L\u001a\u00020\u001bH\u0002J\b\u0010M\u001a\u00020\u001eH\u0014J\u0010\u0010N\u001a\u00020\u001b2\u0006\u0010O\u001a\u00020PH\u0016J\u0010\u0010Q\u001a\u00020\u001b2\u0006\u0010R\u001a\u00020SH\u0016J\u001a\u0010T\u001a\u00020\u001b2\u0006\u0010U\u001a\u00020V2\b\u0010W\u001a\u0004\u0018\u00010XH\u0016J\u0012\u0010Y\u001a\u00020\u001b2\b\u0010Z\u001a\u0004\u0018\u00010[H\u0016J\b\u0010\\\u001a\u00020\u001bH\u0016J\b\u0010]\u001a\u00020\u001bH\u0016J\u0010\u0010^\u001a\u00020\u001b2\u0006\u0010_\u001a\u00020`H\u0016J\b\u0010a\u001a\u00020\u001bH\u0016J\u0010\u0010a\u001a\u00020\u001b2\u0006\u0010U\u001a\u00020bH\u0016J\b\u0010c\u001a\u00020\u001bH\u0014J\b\u0010d\u001a\u00020\u001bH\u0014J\b\u0010e\u001a\u00020\u001bH\u0016J\u001a\u0010f\u001a\u00020\u001b2\u0006\u0010W\u001a\u00020X2\b\u0010Z\u001a\u0004\u0018\u00010[H\u0016J\u0012\u0010g\u001a\u00020\u001b2\b\u0010h\u001a\u0004\u0018\u00010\rH\u0016J\b\u0010i\u001a\u00020\u001bH\u0014J\b\u0010j\u001a\u00020\u001eH\u0014J\"\u0010k\u001a\u00020\u001b2\b\u0010l\u001a\u0004\u0018\u00010m2\u0006\u0010n\u001a\u00020\u001e2\u0006\u0010o\u001a\u00020\u001eH\u0014J \u0010p\u001a\u00020\u001e2\u0006\u0010U\u001a\u00020q2\u0006\u0010r\u001a\u00020s2\u0006\u0010t\u001a\u00020uH\u0002R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006w"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/LibraryPlaylistsFragment;", "Lcom/amazon/mp3/catalog/fragment/LibraryBaseViewsFragment;", "Lcom/amazon/music/views/library/providers/RefinementCallback;", "Lcom/amazon/mp3/fragment/headerbar/OverflowMenuReceiver;", "Lcom/amazon/mp3/fragment/viewmodel/OnFilterChangedListener;", "Lcom/amazon/music/views/library/providers/EmptyStateActionProvider;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/RefinementLoadingListener;", "()V", "actionBarHeight", "", "actionBarManager", "Lcom/amazon/mp3/actionbar/BaseViewsFilterActionBarManager;", "actionBarProvider", "Lcom/amazon/mp3/actionbar/ActionBarProvider;", "createPlaylistResultViewModel", "Lcom/amazon/mp3/catalog/fragment/viewmodel/CreatePlaylistResultViewModel;", "libraryInfoProvider", "Lcom/amazon/mp3/actionbar/BaseViewsFilterActionBarManager$InfoProvider;", "libraryPlaylistsViewModel", "Lcom/amazon/mp3/detailpages/playlist/LibraryPlaylistsViewModel;", "playlistContextMenuListener", "Lcom/amazon/mp3/fragment/contextmenu/AbstractPlaylistContextMenuListener;", "refinementOverflowManager", "Lcom/amazon/mp3/view/refinements/BaseViewsRefinementOverflowManager;", "refinementsModel", "Lcom/amazon/music/views/library/models/ExposedRefinementListModel;", "createPlaylist", "", "displayRefinementLoading", "display", "", "downloadSelectionChanged", "selected", "emitUiPageViewMetric", "getBinderList", "", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "getContentEnabilityProvider", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "getContextMenuProvider", "Lcom/amazon/mp3/providers/MetadataContextMenuHandler;", "getDefaultSelectedRefinements", "", "Lcom/amazon/mp3/view/refinements/RefinementFilterType;", "getDiffUtilFactory", "Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsDiffUtilFactory;", "getEmptyLayoutID", "()Ljava/lang/Integer;", "getInflatedRootView", "Landroid/view/ViewGroup;", "inflater", "Landroid/view/LayoutInflater;", "container", "getLoadingViewId", "getPageDataRepository", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageDataRepository;", "getPageHeaderOnClickListenerMap", "", "Lcom/amazon/music/views/library/models/PageHeaderActionIconModel$ActionType;", "Lcom/amazon/music/views/library/binders/ActionIconOnClickListener;", "getPageLoadLatencyCode", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/PageLoadLatencyCode;", "getPageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "handleAddTracksToPlaylist", "createPlaylistResult", "Lcom/amazon/mp3/catalog/fragment/helper/CreatePlaylistHelper$PlaylistCreationResult;", "result", "Lcom/amazon/mp3/catalog/fragment/helper/CreatePlaylistHelper$PlaylistInfo;", "initEmptyViewResetFiltersButton", "button", "Lcom/amazon/ui/foundations/views/BaseButton;", "initPageModelProvider", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "initializeActionBarManager", "initializeRefinementOverflowManager", "isDisplayingOnlyLibraryContent", "onAttach", "context", "Landroid/content/Context;", "onChildFilterClick", "filterModel", "Lcom/amazon/music/views/library/models/FilterModel;", "onClick", "model", "Lcom/amazon/music/views/library/models/RefinementPillModel;", "view", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onDestroyView", "onEmptyStateButtonClicked", "buttonType", "Lcom/amazon/music/views/library/models/EmptyStateButtonType;", "onFiltersReset", "Lcom/amazon/music/views/library/models/FilterCollectionModel;", "onLoadInitPage", "onLoadUpdatedPage", "onResume", "onViewCreated", "setActionBarProvider", "provider", "setupViews", "shouldUseCachedSource", "updatePageModel", "pageGridViewModel", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "isInitialPage", "isMorePage", "updatePlaylistName", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", Environment.PLAYLIST_PATH, "Lcom/amazon/mp3/library/item/Playlist;", "title", "", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LibraryPlaylistsFragment extends LibraryBaseViewsFragment implements RefinementCallback, OverflowMenuReceiver, OnFilterChangedListener, EmptyStateActionProvider, RefinementLoadingListener {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private int actionBarHeight;
    private BaseViewsFilterActionBarManager actionBarManager;
    private ActionBarProvider actionBarProvider;
    private CreatePlaylistResultViewModel createPlaylistResultViewModel;
    private BaseViewsFilterActionBarManager.InfoProvider libraryInfoProvider;
    private LibraryPlaylistsViewModel libraryPlaylistsViewModel;
    private AbstractPlaylistContextMenuListener playlistContextMenuListener;
    private BaseViewsRefinementOverflowManager refinementOverflowManager;
    private ExposedRefinementListModel refinementsModel;

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

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment
    protected boolean shouldUseCachedSource() {
        return true;
    }

    @Override // com.amazon.mp3.fragment.viewmodel.OnFilterChangedListener
    public boolean shouldOverrideResetFilters() {
        return OnFilterChangedListener.DefaultImpls.shouldOverrideResetFilters(this);
    }

    @Override // com.amazon.mp3.fragment.viewmodel.OnFilterChangedListener
    public boolean shouldOverrideSelectionChange(String str, boolean z) {
        return OnFilterChangedListener.DefaultImpls.shouldOverrideSelectionChange(this, str, z);
    }

    /* JADX INFO: compiled from: LibraryPlaylistsFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/LibraryPlaylistsFragment$Companion;", "", "()V", "getUri", "Landroid/net/Uri;", PageUriUtils.SOURCE_QUERY_PARAM, "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Uri getUri() {
            return getUri("cirrus");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Uri getUri(String source) {
            Uri filterContentUri = MediaProvider.Playlists.getFilterContentUri(source, "");
            Intrinsics.checkNotNullExpressionValue(filterContentUri, "getFilterContentUri(source, \"\")");
            return filterContentUri;
        }
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment, com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        MutableLiveData<Boolean> didCreatePlaylistEntryInDb;
        MutableLiveData<CreatePlaylistHelper.PlaylistInfo> playlistInfoLiveData;
        super.onCreate(savedInstanceState);
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity()");
        this.createPlaylistResultViewModel = (CreatePlaylistResultViewModel) new ViewModelProvider(fragmentActivityRequireActivity).get(CreatePlaylistResultViewModel.class);
        Observer<? super CreatePlaylistHelper.PlaylistInfo> observer = new Observer() { // from class: com.amazon.mp3.catalog.fragment.LibraryPlaylistsFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LibraryPlaylistsFragment.m510onCreate$lambda0(this.f$0, (CreatePlaylistHelper.PlaylistInfo) obj);
            }
        };
        Observer<? super Boolean> observer2 = new Observer() { // from class: com.amazon.mp3.catalog.fragment.LibraryPlaylistsFragment$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LibraryPlaylistsFragment.m511onCreate$lambda1(this.f$0, (Boolean) obj);
            }
        };
        CreatePlaylistResultViewModel createPlaylistResultViewModel = this.createPlaylistResultViewModel;
        if (createPlaylistResultViewModel != null && (playlistInfoLiveData = createPlaylistResultViewModel.getPlaylistInfoLiveData()) != null) {
            playlistInfoLiveData.observe(this, observer);
        }
        CreatePlaylistResultViewModel createPlaylistResultViewModel2 = this.createPlaylistResultViewModel;
        if (createPlaylistResultViewModel2 == null || (didCreatePlaylistEntryInDb = createPlaylistResultViewModel2.getDidCreatePlaylistEntryInDb()) == null) {
            return;
        }
        didCreatePlaylistEntryInDb.observe(this, observer2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-0, reason: not valid java name */
    public static final void m510onCreate$lambda0(LibraryPlaylistsFragment this$0, CreatePlaylistHelper.PlaylistInfo playlistInfo) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.getViewLifecycleOwner().getLifecycle().getCurrentState() == Lifecycle.State.RESUMED || this$0.getViewLifecycleOwner().getLifecycle().getCurrentState() == Lifecycle.State.STARTED) {
            if ((playlistInfo == null ? null : playlistInfo.getMPlaylistId()) == null) {
                LifecycleOwner viewLifecycleOwner = this$0.getViewLifecycleOwner();
                Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "viewLifecycleOwner");
                BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner), Dispatchers.getMain(), null, new LibraryPlaylistsFragment$onCreate$createPlaylistResultObserver$1$1(playlistInfo, this$0, null), 2, null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-1, reason: not valid java name */
    public static final void m511onCreate$lambda1(LibraryPlaylistsFragment this$0, Boolean it) {
        PageViewModelProvider pageModelProvider;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        if (it.booleanValue()) {
            if ((this$0.getViewLifecycleOwner().getLifecycle().getCurrentState() == Lifecycle.State.RESUMED || this$0.getViewLifecycleOwner().getLifecycle().getCurrentState() == Lifecycle.State.STARTED) && (pageModelProvider = this$0.getPageModelProvider()) != null) {
                pageModelProvider.fetchUpdates(20, this$0.getDiffUtilFactory(), FetchType.UPDATE);
            }
        }
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        final FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        this.playlistContextMenuListener = new AbstractPlaylistContextMenuListener(this) { // from class: com.amazon.mp3.catalog.fragment.LibraryPlaylistsFragment$onAttach$1$1
            final /* synthetic */ LibraryPlaylistsFragment this$0;

            @Override // com.amazon.mp3.fragment.contextmenu.PlaylistContextMenuProvider.PlaylistContextMenuProviderListener
            public void removeDownloadPlaylistMenuItem(ContextMenu menu) {
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.$it);
                this.this$0 = this;
                Intrinsics.checkNotNullExpressionValue(this.$it, "it");
            }

            @Override // com.amazon.mp3.fragment.contextmenu.PlaylistContextMenuProvider.PlaylistContextMenuProviderListener
            public void deleteContent(int position, AbstractItem item) {
                Intrinsics.checkNotNullParameter(item, "item");
                final LibraryPlaylistsFragment libraryPlaylistsFragment = this.this$0;
                deleteContent(position, item, new Function0<Unit>() { // from class: com.amazon.mp3.catalog.fragment.LibraryPlaylistsFragment$onAttach$1$1$deleteContent$completionBlock$1
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
                        PageViewModelProvider pageModelProvider = libraryPlaylistsFragment.getPageModelProvider();
                        if (pageModelProvider == null) {
                            return;
                        }
                        PageViewModelProvider.DefaultImpls.fetchUpdates$default(pageModelProvider, 20, libraryPlaylistsFragment.getDiffUtilFactory(), null, 4, null);
                    }
                });
            }

            @Override // com.amazon.mp3.dialog.RenamePlaylistDialog.OnPlaylistRenamedListener
            public void onRenameCompleted(String title, Playlist playlist) {
                PageStateManager pageStateManager;
                Intrinsics.checkNotNullParameter(title, "title");
                Intrinsics.checkNotNullParameter(playlist, "playlist");
                PageViewModelProvider pageModelProvider = this.this$0.getPageModelProvider();
                if (pageModelProvider == null || (pageStateManager = pageModelProvider.getPageStateManager()) == null) {
                    return;
                }
                PageStateManager.updateModelState$default(pageStateManager, playlist, title, new LibraryPlaylistsFragment$onAttach$1$1$onRenameCompleted$1(this.this$0), null, 8, null);
            }

            @Override // com.amazon.mp3.fragment.contextmenu.PlaylistContextMenuProvider.PlaylistContextMenuProviderListener
            public void removeDeletePlaylistMenuItem(ContextMenu menu) {
                if (this.this$0.shouldSupportDeletePlaylist()) {
                    return;
                }
                if (menu != null) {
                    menu.removeItem(R.id.MenuPlaylistDelete);
                }
                if (menu == null) {
                    return;
                }
                menu.removeItem(R.id.MenuPlaylistContextDeleteDevice);
            }

            @Override // com.amazon.mp3.fragment.contextmenu.PlaylistContextMenuProvider.PlaylistContextMenuProviderListener
            public ContextMenuPageType getContextMenuPageType() {
                return ContextMenuPageType.LIBRARY;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean updatePlaylistName(BaseViewModel model, Playlist playlist, String title) {
        if (!(model instanceof BaseViewContentModel)) {
            return false;
        }
        ContentMetadata metadata = ((BaseViewContentModel) model).getMetadata();
        if (metadata instanceof PlaylistMetadata) {
            PlaylistMetadata playlistMetadata = (PlaylistMetadata) metadata;
            if (Intrinsics.areEqual(playlistMetadata.getLuid(), playlist.getLuid())) {
                if (!(model instanceof TableRowItemModel)) {
                    return true;
                }
                ((TableRowItemModel) model).updatePrimaryText(title);
                playlistMetadata.setTitle(title);
                return true;
            }
        }
        return false;
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
    protected void onLoadInitPage() {
        super.onLoadInitPage();
        RecyclerView recyclerView = getRecyclerView();
        RecyclerView.Adapter adapter = recyclerView == null ? null : recyclerView.getAdapter();
        UniversalAdapter universalAdapter = adapter instanceof UniversalAdapter ? (UniversalAdapter) adapter : null;
        this.refinementsModel = universalAdapter != null ? RefinementKt.getRefinementsModel(universalAdapter) : null;
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        LibraryPlaylistsViewModel libraryPlaylistsViewModel = this.libraryPlaylistsViewModel;
        if (libraryPlaylistsViewModel == null) {
            return;
        }
        libraryPlaylistsViewModel.clearListeners();
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment, com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager = this.actionBarManager;
        if (baseViewsFilterActionBarManager == null) {
            return;
        }
        getLifecycle().removeObserver(baseViewsFilterActionBarManager);
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment, com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public MetadataContextMenuHandler getContextMenuProvider() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return null;
        }
        if (getContextMenuHandler() == null) {
            setContextMenuHandler(new LibraryContextMenuProvider(activity, this, getPageType(), getTrackContextMenuListener(), this.playlistContextMenuListener, null, null, LifecycleOwnerKt.getLifecycleScope(this), 96, null));
        }
        return getContextMenuHandler();
    }

    public final void handleAddTracksToPlaylist(CreatePlaylistHelper.PlaylistCreationResult createPlaylistResult, CreatePlaylistHelper.PlaylistInfo result) {
        Intrinsics.checkNotNullParameter(createPlaylistResult, "createPlaylistResult");
        Intrinsics.checkNotNullParameter(result, "result");
        UserSubscription userSubscription = UserSubscriptionUtil.getUserSubscription();
        boolean zIsFreeTierUserPlaylistEditingEnabled = AmazonApplication.getCapabilities().isFreeTierUserPlaylistEditingEnabled();
        if (((userSubscription.isHawkfireHome() || !userSubscription.hasSubscription()) && !zIsFreeTierUserPlaylistEditingEnabled) || isSourceLocal()) {
            PlaylistUtil.startEditPlaylist(getActivity(), createPlaylistResult.getMPlaylistUri(), true);
            return;
        }
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity()");
        CreatePlaylistHelper createPlaylistHelper = new CreatePlaylistHelper(fragmentActivityRequireActivity);
        String mPlaylistLuid = createPlaylistResult.getMPlaylistLuid();
        String mPlaylistName = result.getMPlaylistName();
        if (mPlaylistName == null) {
            mPlaylistName = "";
        }
        createPlaylistHelper.showPlaylistCreateExperiment(mPlaylistLuid, mPlaylistName, createPlaylistResult.getMPlaylistUri());
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public PageType getPageType() {
        return isSourceLocal() ? PageType.OFFLINE_LIBRARY_PLAYLISTS : PageType.CLOUD_LIBRARY_PLAYLISTS;
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public void emitUiPageViewMetric() {
        AbstractBaseViewsFragment.sendUiPageViewMetric$default(this, getPageType(), null, null, null, null, null, null, null, 254, null);
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public PageDataRepository getPageDataRepository() {
        Context context = getContext();
        Companion companion = INSTANCE;
        return new RefinablePageDataRepository(new LibraryPlaylistsDao(context, companion.getUri("cirrus"), companion.getUri("cirrus-local"), getPageType(), getPageLoadLatencyCode(), getDefaultSelectedRefinements()));
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
        return new BaseViewsDiffUtilFactory() { // from class: com.amazon.mp3.catalog.fragment.LibraryPlaylistsFragment.getDiffUtilFactory.1
            @Override // com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtilFactory
            public BaseViewsDiffUtil getBaseViewsDiffUtil(List<? extends BaseViewModel> oldList, List<? extends BaseViewModel> newList) {
                Intrinsics.checkNotNullParameter(oldList, "oldList");
                Intrinsics.checkNotNullParameter(newList, "newList");
                return new BaseViewsDiffUtil(oldList, newList);
            }
        };
    }

    private final Map<PageHeaderActionIconModel.ActionType, ActionIconOnClickListener> getPageHeaderOnClickListenerMap() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(PageHeaderActionIconModel.ActionType.ADD, new ActionIconOnClickListener() { // from class: com.amazon.mp3.catalog.fragment.LibraryPlaylistsFragment.getPageHeaderOnClickListenerMap.1
            @Override // com.amazon.music.views.library.binders.ActionIconOnClickListener
            public void onClick(Context context, ContentMetadata metadata) {
                Intrinsics.checkNotNullParameter(context, "context");
                LibraryPlaylistsFragment.this.createPlaylist();
            }
        });
        return linkedHashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void createPlaylist() {
        if (ConnectivityUtil.hasAnyInternetConnection(getContext()) || !Intrinsics.areEqual("cirrus", getSourceId())) {
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new AnonymousClass1(null), 2, null);
        } else {
            startActivity(NetworkErrorDialogActivity.getStartIntent(getActivity()));
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.catalog.fragment.LibraryPlaylistsFragment$createPlaylist$1, reason: invalid class name */
    /* JADX INFO: compiled from: LibraryPlaylistsFragment.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.catalog.fragment.LibraryPlaylistsFragment$createPlaylist$1", f = "LibraryPlaylistsFragment.kt", i = {}, l = {338}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return LibraryPlaylistsFragment.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                boolean zMaxNumberOfPlaylistsReached = PlaylistUtil.maxNumberOfPlaylistsReached(LibraryPlaylistsFragment.this.getActivity());
                MainCoroutineDispatcher main2 = Dispatchers.getMain();
                C00741 c00741 = new C00741(zMaxNumberOfPlaylistsReached, LibraryPlaylistsFragment.this, null);
                this.label = 1;
                if (BuildersKt.withContext(main2, c00741, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.amazon.mp3.catalog.fragment.LibraryPlaylistsFragment$createPlaylist$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: LibraryPlaylistsFragment.kt */
        @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
        @DebugMetadata(c = "com.amazon.mp3.catalog.fragment.LibraryPlaylistsFragment$createPlaylist$1$1", f = "LibraryPlaylistsFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C00741 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ boolean $isMaxNumberOfPlaylistsReached;
            int label;
            final /* synthetic */ LibraryPlaylistsFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00741(boolean z, LibraryPlaylistsFragment libraryPlaylistsFragment, Continuation<? super C00741> continuation) {
                super(2, continuation);
                this.$isMaxNumberOfPlaylistsReached = z;
                this.this$0 = libraryPlaylistsFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00741(this.$isMaxNumberOfPlaylistsReached, this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
            public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00741) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                if (this.$isMaxNumberOfPlaylistsReached) {
                    this.this$0.startActivity(NumberOfPlaylistsLimitReachedActivity.getStartIntent());
                } else {
                    CreatePlaylistDialogFragment.create(this.this$0.getPageType()).build().show(this.this$0.requireActivity().getSupportFragmentManager(), CreatePlaylistDialogFragment.class.getSimpleName());
                    LibraryPlaylistsFragment libraryPlaylistsFragment = this.this$0;
                    AbstractBaseViewsFragment.sendUiClickMetric$default(libraryPlaylistsFragment, ActionType.CREATE_PLAYLIST_ATTEMPT, null, libraryPlaylistsFragment.getPageType(), null, null, null, null, null, null, null, null, null, 4090, null);
                }
                return Unit.INSTANCE;
            }
        }
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        initLibraryStateObserver();
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
        if (actionBarOverlayable == null) {
            return;
        }
        actionBarOverlayable.setContentOffset(getRecyclerView(), actionBarHeight);
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected void onLoadUpdatedPage() {
        PageFetchInfo pageFetchInfoCheckLastPageFetchInfo;
        IntRange indicesChanged;
        LibraryPlaylistsViewModel libraryPlaylistsViewModel = this.libraryPlaylistsViewModel;
        Unit unit = null;
        if (libraryPlaylistsViewModel != null && (pageFetchInfoCheckLastPageFetchInfo = libraryPlaylistsViewModel.getLastFetchedPageInfo()) != null && (indicesChanged = pageFetchInfoCheckLastPageFetchInfo.getIndicesChanged()) != null) {
            onLoadPageRangeChanged(indicesChanged);
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            super.onLoadUpdatedPage();
        }
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

    private final void initializeRefinementOverflowManager() {
        getSubscriptions().add(StyleSheetProvider.getStyleSheetObservable().subscribe(new Action1() { // from class: com.amazon.mp3.catalog.fragment.LibraryPlaylistsFragment$$ExternalSyntheticLambda4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                LibraryPlaylistsFragment.m509initializeRefinementOverflowManager$lambda11(this.f$0, (StyleSheet) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initializeRefinementOverflowManager$lambda-11, reason: not valid java name */
    public static final void m509initializeRefinementOverflowManager$lambda11(LibraryPlaylistsFragment this$0, StyleSheet it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.refinementOverflowManager = new BaseViewsRefinementOverflowManager(this$0, it, this$0);
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment, com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public void initPageModelProvider(FragmentActivity activity) {
        LibraryPlaylistsViewModel libraryPlaylistsViewModel;
        Intrinsics.checkNotNullParameter(activity, "activity");
        super.initPageModelProvider(activity);
        PageDataRepository pageDataRepository = getPageDataRepository();
        RefinablePageDataRepository refinablePageDataRepository = pageDataRepository instanceof RefinablePageDataRepository ? (RefinablePageDataRepository) pageDataRepository : null;
        if (refinablePageDataRepository != null) {
            PageViewModelFactoryProvider pageViewModelFactoryProvider = PageViewModelFactoryProvider.INSTANCE;
            Application application = activity.getApplication();
            Intrinsics.checkNotNullExpressionValue(application, "activity.application");
            setPageModelProvider(pageViewModelFactoryProvider.getViewModel(this, new PageViewModelFactoryProvider.LibraryPlaylistsViewModelFactory(application, refinablePageDataRepository, getDefaultSelectedRefinements())));
        }
        PageViewModelProvider pageModelProvider = getPageModelProvider();
        RefinableViewModel refinableViewModel = pageModelProvider instanceof RefinableViewModel ? (RefinableViewModel) pageModelProvider : null;
        if (refinableViewModel != null) {
            refinableViewModel.setFilterListener(this);
        }
        LibraryPlaylistsViewModel libraryPlaylistsViewModel2 = this.libraryPlaylistsViewModel;
        if (libraryPlaylistsViewModel2 != null) {
            libraryPlaylistsViewModel2.unregisterDownloadStateListener();
        }
        PageViewModelProvider pageModelProvider2 = getPageModelProvider();
        LibraryPlaylistsViewModel libraryPlaylistsViewModel3 = pageModelProvider2 instanceof LibraryPlaylistsViewModel ? (LibraryPlaylistsViewModel) pageModelProvider2 : null;
        this.libraryPlaylistsViewModel = libraryPlaylistsViewModel3;
        if (libraryPlaylistsViewModel3 != null) {
            libraryPlaylistsViewModel3.registerDownloadStateListener(getContentUri());
        }
        LibraryPlaylistsViewModel libraryPlaylistsViewModel4 = this.libraryPlaylistsViewModel;
        if (libraryPlaylistsViewModel4 != null) {
            libraryPlaylistsViewModel4.setLoadingListener(this);
        }
        ContentEnabilityProvider contentEnabilityProvider = getContentEnabilityProvider();
        if (contentEnabilityProvider == null || (libraryPlaylistsViewModel = this.libraryPlaylistsViewModel) == null) {
            return;
        }
        libraryPlaylistsViewModel.setContentEnabilityProvider(contentEnabilityProvider);
    }

    private final Set<RefinementFilterType> getDefaultSelectedRefinements() {
        Bundle arguments = getArguments();
        Set<RefinementFilterType> enabledRefinementFilters$default = arguments != null ? RefinementFilterType.Companion.getEnabledRefinementFilters$default(RefinementFilterType.INSTANCE, arguments, null, 2, null) : null;
        boolean z = false;
        if ((enabledRefinementFilters$default == null || enabledRefinementFilters$default.isEmpty()) && isSourceLocal()) {
            return SetsKt__SetsKt.setOf((Object[]) new RefinementFilterType[]{RefinementFilterType.DOWNLOADED, RefinementFilterType.TITLE});
        }
        if (enabledRefinementFilters$default != null && (!enabledRefinementFilters$default.isEmpty())) {
            z = true;
        }
        if (z) {
            return enabledRefinementFilters$default;
        }
        if (AmazonApplication.getCapabilities().isPlaylistsSortingEnabled()) {
            return SetsKt__SetsJVMKt.setOf(RefinementFilterType.LAST_TOUCHED);
        }
        return SetsKt__SetsJVMKt.setOf(RefinementFilterType.TITLE);
    }

    @Override // com.amazon.mp3.fragment.viewmodel.OnFilterChangedListener
    public void downloadSelectionChanged(boolean selected) {
        updateSource(selected ? "cirrus-local" : "cirrus");
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
    protected ViewGroup getInflatedRootView(LayoutInflater inflater, ViewGroup container) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R.layout.fragment_detail, container, false);
        if (viewInflate != null) {
            return (ViewGroup) viewInflate;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup");
    }

    private final void initEmptyViewResetFiltersButton(final BaseButton button) {
        getSubscriptions().add(StyleSheetProvider.getStyleSheetObservable().subscribe(new Action1() { // from class: com.amazon.mp3.catalog.fragment.LibraryPlaylistsFragment$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                LibraryPlaylistsFragment.m507initEmptyViewResetFiltersButton$lambda15(this.f$0, button, (StyleSheet) obj);
            }
        }));
        button.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.catalog.fragment.LibraryPlaylistsFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LibraryPlaylistsFragment.m508initEmptyViewResetFiltersButton$lambda16(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initEmptyViewResetFiltersButton$lambda-15, reason: not valid java name */
    public static final void m507initEmptyViewResetFiltersButton$lambda15(LibraryPlaylistsFragment this$0, BaseButton button, StyleSheet styleSheet) {
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
    /* JADX INFO: renamed from: initEmptyViewResetFiltersButton$lambda-16, reason: not valid java name */
    public static final void m508initEmptyViewResetFiltersButton$lambda16(LibraryPlaylistsFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onFiltersReset();
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected PageLoadLatencyCode getPageLoadLatencyCode() {
        return PageLoadLatencyCode.LIBRARY_PLAYLISTS;
    }

    @Override // com.amazon.music.views.library.providers.EmptyStateActionProvider
    public void onEmptyStateButtonClicked(EmptyStateButtonType buttonType) {
        Intrinsics.checkNotNullParameter(buttonType, "buttonType");
        if (buttonType instanceof EmptyStateButtonType.EXPLORE) {
            new EUXDestinationLauncher(getContext()).launchPlaylists();
        } else if (buttonType instanceof EmptyStateButtonType.RESET) {
            onFiltersReset();
        } else if (buttonType instanceof EmptyStateButtonType.BUY) {
            new EUXDestinationLauncher(getContext()).launchStore();
        }
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.RefinementLoadingListener
    public void displayRefinementLoading(boolean display) {
        if (display) {
            showLoadingView(false);
        } else {
            displayViewState();
        }
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected void updatePageModel(PageGridViewModel pageGridViewModel, boolean isInitialPage, boolean isMorePage) {
        super.updatePageModel(pageGridViewModel, isInitialPage, isMorePage);
        LibraryPlaylistsViewModel libraryPlaylistsViewModel = this.libraryPlaylistsViewModel;
        if (libraryPlaylistsViewModel == null) {
            return;
        }
        libraryPlaylistsViewModel.updateAvailabilityState();
    }
}
