package com.amazon.mp3.catalog.fragment;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.compose.runtime.ComposerKt;
import androidx.core.view.KeyEventDispatcher;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.actionbar.ActionBarProvider;
import com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsBinderConfiguration;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsBinderProvider;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtil;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtilFactory;
import com.amazon.mp3.catalog.fragment.datasource.dao.LibraryAlbumsDao;
import com.amazon.mp3.catalog.fragment.datasource.repository.PageDataRepository;
import com.amazon.mp3.catalog.fragment.datasource.repository.RefinablePageDataRepository;
import com.amazon.mp3.catalog.fragment.provider.InfoProviderLibraryImpl;
import com.amazon.mp3.catalog.fragment.provider.LibraryContentEnabilityProvider;
import com.amazon.mp3.catalog.fragment.viewmodel.PageFetchInfo;
import com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelFactoryProvider;
import com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelProvider;
import com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel;
import com.amazon.mp3.catalog.fragment.viewmodel.RefinementLoadingListener;
import com.amazon.mp3.detailpages.playlist.LibraryAlbumsViewModel;
import com.amazon.mp3.eux.EUXDestinationLauncher;
import com.amazon.mp3.fragment.contextmenu.AlbumContextMenuProvider;
import com.amazon.mp3.fragment.contextmenu.ContextMenuPageType;
import com.amazon.mp3.fragment.headerbar.OverflowMenuReceiver;
import com.amazon.mp3.fragment.viewmodel.OnFilterChangedListener;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.navigation.DeeplinkIntentKey;
import com.amazon.mp3.providers.LibraryContextMenuProvider;
import com.amazon.mp3.providers.MetadataContextMenuHandler;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.extensions.RefinementKt;
import com.amazon.mp3.view.refinements.BaseViewsRefinementOverflowManager;
import com.amazon.mp3.view.refinements.RefinementFilterType;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.binders.ActionIconOnClickListener;
import com.amazon.music.views.library.binders.RefinementPillBinder;
import com.amazon.music.views.library.binders.UniversalBinder;
import com.amazon.music.views.library.models.EmptyStateButtonType;
import com.amazon.music.views.library.models.ExposedRefinementListModel;
import com.amazon.music.views.library.models.FilterCollectionModel;
import com.amazon.music.views.library.models.FilterModel;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.PageHeaderActionIconModel;
import com.amazon.music.views.library.models.RefinementPillModel;
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
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import rx.functions.Action1;

/* JADX INFO: compiled from: LibraryAlbumsFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0082\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 g2\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u0006:\u0001gB\u0005¢\u0006\u0002\u0010\u0007J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u001bH\u0016J\b\u0010\u001e\u001a\u00020\u0019H\u0016J\u0016\u0010\u001f\u001a\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030!0 H\u0016J\n\u0010\"\u001a\u0004\u0018\u00010#H\u0014J\n\u0010$\u001a\u0004\u0018\u00010%H\u0016J\u0010\u0010&\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010'H\u0002J\b\u0010)\u001a\u00020*H\u0016J\u000f\u0010+\u001a\u0004\u0018\u00010\tH\u0014¢\u0006\u0002\u0010,J\u001a\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u00010.H\u0014J\b\u00102\u001a\u00020\tH\u0014J\n\u00103\u001a\u0004\u0018\u000104H\u0016J\u0014\u00105\u001a\u000e\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u00020806H\u0002J\n\u00109\u001a\u0004\u0018\u00010:H\u0014J\b\u0010;\u001a\u00020<H\u0016J\u0010\u0010=\u001a\u00020\u00192\u0006\u0010>\u001a\u00020?H\u0002J\u0010\u0010@\u001a\u00020\u00192\u0006\u0010A\u001a\u00020BH\u0016J\b\u0010C\u001a\u00020\u0019H\u0002J\b\u0010D\u001a\u00020\u0019H\u0002J\b\u0010E\u001a\u00020\u001bH\u0014J\u0010\u0010F\u001a\u00020\u00192\u0006\u0010G\u001a\u00020HH\u0016J\u0010\u0010I\u001a\u00020\u00192\u0006\u0010J\u001a\u00020KH\u0016J\u001a\u0010L\u001a\u00020\u00192\u0006\u0010M\u001a\u00020N2\b\u0010O\u001a\u0004\u0018\u00010PH\u0016J\b\u0010Q\u001a\u00020\u0019H\u0016J\b\u0010R\u001a\u00020\u0019H\u0016J\u0010\u0010S\u001a\u00020\u00192\u0006\u0010T\u001a\u00020UH\u0016J\b\u0010V\u001a\u00020\u0019H\u0016J\u0010\u0010V\u001a\u00020\u00192\u0006\u0010M\u001a\u00020WH\u0016J\b\u0010X\u001a\u00020\u0019H\u0014J\b\u0010Y\u001a\u00020\u0019H\u0014J\b\u0010Z\u001a\u00020\u0019H\u0016J\u001a\u0010[\u001a\u00020\u00192\u0006\u0010O\u001a\u00020P2\b\u0010\\\u001a\u0004\u0018\u00010]H\u0016J\u0012\u0010^\u001a\u00020\u00192\b\u0010_\u001a\u0004\u0018\u00010\rH\u0016J\b\u0010`\u001a\u00020\u0019H\u0014J\b\u0010a\u001a\u00020\u001bH\u0014J\"\u0010b\u001a\u00020\u00192\b\u0010c\u001a\u0004\u0018\u00010d2\u0006\u0010e\u001a\u00020\u001b2\u0006\u0010f\u001a\u00020\u001bH\u0014R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006h"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/LibraryAlbumsFragment;", "Lcom/amazon/mp3/catalog/fragment/LibraryBaseViewsFragment;", "Lcom/amazon/music/views/library/providers/RefinementCallback;", "Lcom/amazon/mp3/fragment/headerbar/OverflowMenuReceiver;", "Lcom/amazon/mp3/fragment/viewmodel/OnFilterChangedListener;", "Lcom/amazon/music/views/library/providers/EmptyStateActionProvider;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/RefinementLoadingListener;", "()V", "actionBarHeight", "", "actionBarManager", "Lcom/amazon/mp3/actionbar/BaseViewsFilterActionBarManager;", "actionBarProvider", "Lcom/amazon/mp3/actionbar/ActionBarProvider;", "albumContextMenuProviderListener", "Lcom/amazon/mp3/fragment/contextmenu/AlbumContextMenuProvider$AlbumContextMenuProviderListener;", "libraryAlbumsViewModel", "Lcom/amazon/mp3/detailpages/playlist/LibraryAlbumsViewModel;", "libraryInfoProvider", "Lcom/amazon/mp3/actionbar/BaseViewsFilterActionBarManager$InfoProvider;", "refinementOverflowManager", "Lcom/amazon/mp3/view/refinements/BaseViewsRefinementOverflowManager;", "refinementsModel", "Lcom/amazon/music/views/library/models/ExposedRefinementListModel;", "displayRefinementLoading", "", "display", "", "downloadSelectionChanged", "selected", "emitUiPageViewMetric", "getBinderList", "", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "getContentEnabilityProvider", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "getContextMenuProvider", "Lcom/amazon/mp3/providers/MetadataContextMenuHandler;", "getDefaultSelectedRefinements", "", "Lcom/amazon/mp3/view/refinements/RefinementFilterType;", "getDiffUtilFactory", "Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsDiffUtilFactory;", "getEmptyLayoutID", "()Ljava/lang/Integer;", "getInflatedRootView", "Landroid/view/ViewGroup;", "inflater", "Landroid/view/LayoutInflater;", "container", "getLoadingViewId", "getPageDataRepository", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageDataRepository;", "getPageHeaderOnClickListenerMap", "", "Lcom/amazon/music/views/library/models/PageHeaderActionIconModel$ActionType;", "Lcom/amazon/music/views/library/binders/ActionIconOnClickListener;", "getPageLoadLatencyCode", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/PageLoadLatencyCode;", "getPageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "initEmptyViewResetFiltersButton", "button", "Lcom/amazon/ui/foundations/views/BaseButton;", "initPageModelProvider", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "initializeActionBarManager", "initializeRefinementOverflowManager", "isDisplayingOnlyLibraryContent", "onAttach", "context", "Landroid/content/Context;", "onChildFilterClick", "filterModel", "Lcom/amazon/music/views/library/models/FilterModel;", "onClick", "model", "Lcom/amazon/music/views/library/models/RefinementPillModel;", "view", "Landroid/view/View;", "onDestroy", "onDestroyView", "onEmptyStateButtonClicked", "buttonType", "Lcom/amazon/music/views/library/models/EmptyStateButtonType;", "onFiltersReset", "Lcom/amazon/music/views/library/models/FilterCollectionModel;", "onLoadInitPage", "onLoadUpdatedPage", "onResume", "onViewCreated", "savedInstanceState", "Landroid/os/Bundle;", "setActionBarProvider", "provider", "setupViews", "shouldUseCachedSource", "updatePageModel", "pageGridViewModel", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "isInitialPage", "isMorePage", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LibraryAlbumsFragment extends LibraryBaseViewsFragment implements RefinementCallback, OverflowMenuReceiver, OnFilterChangedListener, EmptyStateActionProvider, RefinementLoadingListener {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private int actionBarHeight;
    private BaseViewsFilterActionBarManager actionBarManager;
    private ActionBarProvider actionBarProvider;
    private AlbumContextMenuProvider.AlbumContextMenuProviderListener albumContextMenuProviderListener;
    private LibraryAlbumsViewModel libraryAlbumsViewModel;
    private BaseViewsFilterActionBarManager.InfoProvider libraryInfoProvider;
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

    /* JADX INFO: compiled from: LibraryAlbumsFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/LibraryAlbumsFragment$Companion;", "", "()V", "getUri", "Landroid/net/Uri;", PageUriUtils.SOURCE_QUERY_PARAM, "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
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
            Uri filterContentUri = MediaProvider.Albums.getFilterContentUri(source, "");
            Intrinsics.checkNotNullExpressionValue(filterContentUri, "getFilterContentUri(source, \"\")");
            return filterContentUri;
        }
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected void onLoadInitPage() {
        super.onLoadInitPage();
        RecyclerView recyclerView = getRecyclerView();
        RecyclerView.Adapter adapter = recyclerView == null ? null : recyclerView.getAdapter();
        UniversalAdapter universalAdapter = adapter instanceof UniversalAdapter ? (UniversalAdapter) adapter : null;
        this.refinementsModel = universalAdapter != null ? RefinementKt.getRefinementsModel(universalAdapter) : null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        PageType pageType = getPageType();
        Bundle arguments = getArguments();
        String string = arguments == null ? null : arguments.getString(DeeplinkIntentKey.KEY_REF_MARKER.toString());
        Bundle arguments2 = getArguments();
        AbstractBaseViewsFragment.sendUiPageViewMetric$default(this, pageType, null, null, null, string, arguments2 == null ? null : arguments2.getString(DeeplinkIntentKey.KEY_ASSOCIATE_TAG.toString()), null, null, ComposerKt.referenceKey, null);
        super.onViewCreated(view, savedInstanceState);
        ViewGroup root = getRoot();
        BaseButton baseButton = root != null ? (BaseButton) root.findViewById(R.id.resetFilterButton) : null;
        if (baseButton == null) {
            return;
        }
        initEmptyViewResetFiltersButton(baseButton);
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        initLibraryStateObserver();
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        LibraryAlbumsViewModel libraryAlbumsViewModel = this.libraryAlbumsViewModel;
        if (libraryAlbumsViewModel == null) {
            return;
        }
        libraryAlbumsViewModel.clearListeners();
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

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public PageType getPageType() {
        return isSourceLocal() ? PageType.OFFLINE_LIBRARY_ALBUMS : PageType.CLOUD_LIBRARY_ALBUMS;
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        if (getActivity() == null) {
            return;
        }
        this.albumContextMenuProviderListener = new AlbumContextMenuProvider.AlbumContextMenuProviderListener() { // from class: com.amazon.mp3.catalog.fragment.LibraryAlbumsFragment$onAttach$1$1
            @Override // com.amazon.mp3.fragment.contextmenu.AlbumContextMenuProvider.AlbumContextMenuProviderListener
            public void removeDeleteAlbumMenuItem(ContextMenu menu) {
            }

            @Override // com.amazon.mp3.fragment.contextmenu.AlbumContextMenuProvider.AlbumContextMenuProviderListener
            public void removeDownloadAlbumMenuItem(ContextMenu menu) {
            }

            @Override // com.amazon.mp3.fragment.contextmenu.AlbumContextMenuProvider.AlbumContextMenuProviderListener
            public boolean shouldDisplayAddToPlaylist() {
                return true;
            }

            @Override // com.amazon.mp3.fragment.contextmenu.AlbumContextMenuProvider.AlbumContextMenuProviderListener
            public ContextMenuPageType getContextMenuPageType() {
                return ContextMenuPageType.LIBRARY;
            }
        };
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public PageDataRepository getPageDataRepository() {
        Context context = getContext();
        Companion companion = INSTANCE;
        return new RefinablePageDataRepository(new LibraryAlbumsDao(context, companion.getUri("cirrus"), companion.getUri("cirrus-local"), getPageType(), getPageLoadLatencyCode(), getDefaultSelectedRefinements()));
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public List<UniversalBinder<?, ?>> getBinderList() {
        BaseViewsBinderConfiguration viewsConfiguration = getViewsConfiguration();
        if (viewsConfiguration != null) {
            return BaseViewsBinderProvider.BinderListBuilder.withVisualRowItems$default(new BaseViewsBinderProvider.BinderListBuilder(viewsConfiguration).withDefaultHeader(getPageHeaderOnClickListenerMap(), getPageType()).withRefinements(this), null, null, 3, null).withEmptyView(this).build();
        }
        return CollectionsKt__CollectionsKt.emptyList();
    }

    private final Map<PageHeaderActionIconModel.ActionType, ActionIconOnClickListener> getPageHeaderOnClickListenerMap() {
        return new LinkedHashMap();
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
    public BaseViewsDiffUtilFactory getDiffUtilFactory() {
        return new BaseViewsDiffUtilFactory() { // from class: com.amazon.mp3.catalog.fragment.LibraryAlbumsFragment.getDiffUtilFactory.1
            @Override // com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtilFactory
            public BaseViewsDiffUtil getBaseViewsDiffUtil(List<? extends BaseViewModel> oldList, List<? extends BaseViewModel> newList) {
                Intrinsics.checkNotNullParameter(oldList, "oldList");
                Intrinsics.checkNotNullParameter(newList, "newList");
                return new BaseViewsDiffUtil(oldList, newList);
            }
        };
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public void emitUiPageViewMetric() {
        AbstractBaseViewsFragment.sendUiPageViewMetric$default(this, getPageType(), null, null, null, null, null, null, null, 254, null);
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
    protected void onLoadUpdatedPage() {
        PageFetchInfo pageFetchInfoCheckLastPageFetchInfo;
        IntRange indicesChanged;
        LibraryAlbumsViewModel libraryAlbumsViewModel = this.libraryAlbumsViewModel;
        Unit unit = null;
        if (libraryAlbumsViewModel != null && (pageFetchInfoCheckLastPageFetchInfo = libraryAlbumsViewModel.getLastFetchedPageInfo()) != null && (indicesChanged = pageFetchInfoCheckLastPageFetchInfo.getIndicesChanged()) != null) {
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
        getSubscriptions().add(StyleSheetProvider.getStyleSheetObservable().subscribe(new Action1() { // from class: com.amazon.mp3.catalog.fragment.LibraryAlbumsFragment$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                LibraryAlbumsFragment.m490initializeRefinementOverflowManager$lambda8(this.f$0, (StyleSheet) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initializeRefinementOverflowManager$lambda-8, reason: not valid java name */
    public static final void m490initializeRefinementOverflowManager$lambda8(LibraryAlbumsFragment this$0, StyleSheet it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.refinementOverflowManager = new BaseViewsRefinementOverflowManager(this$0, it, this$0);
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment, com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public void initPageModelProvider(FragmentActivity activity) {
        LibraryAlbumsViewModel libraryAlbumsViewModel;
        Intrinsics.checkNotNullParameter(activity, "activity");
        super.initPageModelProvider(activity);
        PageDataRepository pageDataRepository = getPageDataRepository();
        RefinablePageDataRepository refinablePageDataRepository = pageDataRepository instanceof RefinablePageDataRepository ? (RefinablePageDataRepository) pageDataRepository : null;
        if (refinablePageDataRepository != null) {
            PageViewModelFactoryProvider pageViewModelFactoryProvider = PageViewModelFactoryProvider.INSTANCE;
            Application application = activity.getApplication();
            Intrinsics.checkNotNullExpressionValue(application, "activity.application");
            setPageModelProvider(pageViewModelFactoryProvider.getViewModel(this, new PageViewModelFactoryProvider.LibraryAlbumsViewModelFactory(application, refinablePageDataRepository, getDefaultSelectedRefinements())));
        }
        PageViewModelProvider pageModelProvider = getPageModelProvider();
        RefinableViewModel refinableViewModel = pageModelProvider instanceof RefinableViewModel ? (RefinableViewModel) pageModelProvider : null;
        if (refinableViewModel != null) {
            refinableViewModel.setFilterListener(this);
        }
        LibraryAlbumsViewModel libraryAlbumsViewModel2 = this.libraryAlbumsViewModel;
        if (libraryAlbumsViewModel2 != null) {
            libraryAlbumsViewModel2.unregisterDownloadStateListener();
        }
        PageViewModelProvider pageModelProvider2 = getPageModelProvider();
        LibraryAlbumsViewModel libraryAlbumsViewModel3 = pageModelProvider2 instanceof LibraryAlbumsViewModel ? (LibraryAlbumsViewModel) pageModelProvider2 : null;
        this.libraryAlbumsViewModel = libraryAlbumsViewModel3;
        if (libraryAlbumsViewModel3 != null) {
            libraryAlbumsViewModel3.registerDownloadStateListener(getContentUri());
        }
        LibraryAlbumsViewModel libraryAlbumsViewModel4 = this.libraryAlbumsViewModel;
        if (libraryAlbumsViewModel4 != null) {
            libraryAlbumsViewModel4.setLoadingListener(this);
        }
        ContentEnabilityProvider contentEnabilityProvider = getContentEnabilityProvider();
        if (contentEnabilityProvider == null || (libraryAlbumsViewModel = this.libraryAlbumsViewModel) == null) {
            return;
        }
        libraryAlbumsViewModel.setContentEnabilityProvider(contentEnabilityProvider);
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
        getSubscriptions().add(StyleSheetProvider.getStyleSheetObservable().subscribe(new Action1() { // from class: com.amazon.mp3.catalog.fragment.LibraryAlbumsFragment$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                LibraryAlbumsFragment.m488initEmptyViewResetFiltersButton$lambda12(this.f$0, button, (StyleSheet) obj);
            }
        }));
        button.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.catalog.fragment.LibraryAlbumsFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LibraryAlbumsFragment.m489initEmptyViewResetFiltersButton$lambda13(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initEmptyViewResetFiltersButton$lambda-12, reason: not valid java name */
    public static final void m488initEmptyViewResetFiltersButton$lambda12(LibraryAlbumsFragment this$0, BaseButton button, StyleSheet styleSheet) {
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
    /* JADX INFO: renamed from: initEmptyViewResetFiltersButton$lambda-13, reason: not valid java name */
    public static final void m489initEmptyViewResetFiltersButton$lambda13(LibraryAlbumsFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onFiltersReset();
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected PageLoadLatencyCode getPageLoadLatencyCode() {
        return PageLoadLatencyCode.LIBRARY_ALBUMS;
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

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.RefinementLoadingListener
    public void displayRefinementLoading(boolean display) {
        if (display) {
            showLoadingView(false);
        } else {
            displayViewState();
        }
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment, com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public MetadataContextMenuHandler getContextMenuProvider() {
        if (getContextMenuHandler() == null) {
            FragmentActivity activity = getActivity();
            BaseActivity baseActivity = activity instanceof BaseActivity ? (BaseActivity) activity : null;
            if (baseActivity != null) {
                setContextMenuHandler(new LibraryContextMenuProvider(baseActivity, this, PageType.UNKNOWN, getTrackContextMenuListener(), null, this.albumContextMenuProviderListener, null, LifecycleOwnerKt.getLifecycleScope(this), 80, null));
            }
        }
        return getContextMenuHandler();
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected void updatePageModel(PageGridViewModel pageGridViewModel, boolean isInitialPage, boolean isMorePage) {
        super.updatePageModel(pageGridViewModel, isInitialPage, isMorePage);
        LibraryAlbumsViewModel libraryAlbumsViewModel = this.libraryAlbumsViewModel;
        if (libraryAlbumsViewModel == null) {
            return;
        }
        libraryAlbumsViewModel.updateAvailabilityState();
    }
}
