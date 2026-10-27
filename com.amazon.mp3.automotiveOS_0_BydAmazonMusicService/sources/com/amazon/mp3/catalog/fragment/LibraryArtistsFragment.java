package com.amazon.mp3.catalog.fragment;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.KeyEventDispatcher;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.actionbar.ActionBarProvider;
import com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager;
import com.amazon.mp3.activity.ATCWebActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.catalog.fragment.LibraryFollowedArtistFragment;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsBinderConfiguration;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsBinderProvider;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtil;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtilFactory;
import com.amazon.mp3.catalog.fragment.datasource.dao.LibraryArtistsDao;
import com.amazon.mp3.catalog.fragment.datasource.repository.PageDataRepository;
import com.amazon.mp3.catalog.fragment.datasource.repository.RefinablePageDataRepository;
import com.amazon.mp3.catalog.fragment.provider.InfoProviderLibraryImpl;
import com.amazon.mp3.catalog.fragment.provider.LibraryContentEnabilityProvider;
import com.amazon.mp3.catalog.fragment.viewmodel.PageFetchInfo;
import com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelFactoryProvider;
import com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelProvider;
import com.amazon.mp3.catalog.fragment.viewmodel.RefinableViewModel;
import com.amazon.mp3.catalog.fragment.viewmodel.RefinementLoadingListener;
import com.amazon.mp3.detailpages.artist.LibraryArtistViewModel;
import com.amazon.mp3.eux.EUXDestinationLauncher;
import com.amazon.mp3.fragment.headerbar.OverflowMenuReceiver;
import com.amazon.mp3.fragment.viewmodel.OnFilterChangedListener;
import com.amazon.mp3.library.dialog.NetworkErrorDialog;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.providers.DefaultAddToLibraryProvider;
import com.amazon.mp3.providers.DefaultLikesProvider;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.extensions.RefinementKt;
import com.amazon.mp3.view.refinements.BaseViewsRefinementOverflowManager;
import com.amazon.mp3.view.refinements.RefinementFilterType;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
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
import com.amazon.music.views.library.models.enums.TargetType;
import com.amazon.music.views.library.providers.BrushUriClickListenerProvider;
import com.amazon.music.views.library.providers.ContentEnabilityProvider;
import com.amazon.music.views.library.providers.EmptyStateActionProvider;
import com.amazon.music.views.library.providers.RefinementCallback;
import com.amazon.music.views.library.providers.RowButtonOnClickProvider;
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
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import rx.functions.Action1;

/* JADX INFO: compiled from: LibraryArtistsFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0097\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006*\u0001U\u0018\u0000 v2\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u0006:\u0001vB\u0007¢\u0006\u0004\bt\u0010uJ\u001a\u0010\u000b\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u00072\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0014J\b\u0010\r\u001a\u00020\fH\u0014J\u0011\u0010\u000e\u001a\u0004\u0018\u00010\fH\u0014¢\u0006\u0004\b\u000e\u0010\u000fJ\u001a\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0011\u001a\u00020\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0016J\u0010\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0016H\u0016J\u0010\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u0019H\u0016J\b\u0010\u001c\u001a\u00020\u0019H\u0014J\b\u0010\u001d\u001a\u00020\u0014H\u0016J\b\u0010\u001e\u001a\u00020\u0014H\u0014J\b\u0010\u001f\u001a\u00020\u0014H\u0016J\b\u0010 \u001a\u00020\u0014H\u0016J\b\u0010!\u001a\u00020\u0019H\u0014J\b\u0010#\u001a\u00020\"H\u0016J\b\u0010$\u001a\u00020\u0014H\u0016J\n\u0010&\u001a\u0004\u0018\u00010%H\u0016J\u0016\u0010)\u001a\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030(0'H\u0016J\"\u0010.\u001a\u00020\u00142\u0006\u0010*\u001a\u00020\f2\u0006\u0010+\u001a\u00020\f2\b\u0010-\u001a\u0004\u0018\u00010,H\u0016J\n\u00100\u001a\u0004\u0018\u00010/H\u0014J\b\u00102\u001a\u000201H\u0016J\b\u00103\u001a\u00020\u0014H\u0014J\b\u00104\u001a\u00020\u0014H\u0014J\u0012\u00107\u001a\u00020\u00142\b\u00106\u001a\u0004\u0018\u000105H\u0016J\u0010\u0010:\u001a\u00020\u00142\u0006\u00109\u001a\u000208H\u0016J\u001a\u0010=\u001a\u00020\u00142\u0006\u0010<\u001a\u00020;2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0016J\b\u0010>\u001a\u00020\u0014H\u0016J\u0010\u0010>\u001a\u00020\u00142\u0006\u0010<\u001a\u00020?H\u0016J\n\u0010A\u001a\u0004\u0018\u00010@H\u0014J\u0010\u0010D\u001a\u00020\u00142\u0006\u0010C\u001a\u00020BH\u0016J\u0010\u0010F\u001a\u00020\u00142\u0006\u0010E\u001a\u00020\u0019H\u0016J\"\u0010K\u001a\u00020\u00142\b\u0010H\u001a\u0004\u0018\u00010G2\u0006\u0010I\u001a\u00020\u00192\u0006\u0010J\u001a\u00020\u0019H\u0014J\u0012\u0010M\u001a\u0004\u0018\u00010L2\u0006\u0010\u0017\u001a\u00020\u0016H\u0014J\u0010\u0010P\u001a\u00020\u00142\u0006\u0010O\u001a\u00020NH\u0002J\u0010\u0010S\u001a\n\u0012\u0004\u0012\u00020R\u0018\u00010QH\u0002J\b\u0010T\u001a\u00020\u0014H\u0002J\u000f\u0010V\u001a\u00020UH\u0002¢\u0006\u0004\bV\u0010WJ\u0014\u0010[\u001a\u000e\u0012\u0004\u0012\u00020Y\u0012\u0004\u0012\u00020Z0XH\u0002J\b\u0010\\\u001a\u00020\u0014H\u0002J\b\u0010]\u001a\u00020\u0014H\u0002R\u0016\u0010_\u001a\u00020^8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b_\u0010`R\u0016\u0010a\u001a\u0002058\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\ba\u0010bR\u0018\u0010d\u001a\u0004\u0018\u00010c8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bd\u0010eR\u0016\u0010f\u001a\u00020\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bf\u0010gR\u0018\u0010i\u001a\u0004\u0018\u00010h8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bi\u0010jR\u0018\u0010l\u001a\u0004\u0018\u00010k8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bl\u0010mR\u0018\u0010o\u001a\u0004\u0018\u00010n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bo\u0010pR\u0018\u0010r\u001a\u0004\u0018\u00010q8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\br\u0010s¨\u0006w"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/LibraryArtistsFragment;", "Lcom/amazon/mp3/catalog/fragment/LibraryBaseViewsFragment;", "Lcom/amazon/music/views/library/providers/RefinementCallback;", "Lcom/amazon/mp3/fragment/headerbar/OverflowMenuReceiver;", "Lcom/amazon/mp3/fragment/viewmodel/OnFilterChangedListener;", "Lcom/amazon/music/views/library/providers/EmptyStateActionProvider;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/RefinementLoadingListener;", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "getInflatedRootView", "", "getLoadingViewId", "getEmptyLayoutID", "()Ljava/lang/Integer;", "Landroid/view/View;", "view", "Landroid/os/Bundle;", "savedInstanceState", "", "onViewCreated", "Landroidx/fragment/app/FragmentActivity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "initPageModelProvider", "", "selected", "downloadSelectionChanged", "shouldUseCachedSource", "onResume", "onLoadInitPage", "onDestroyView", "onDestroy", "isDisplayingOnlyLibraryContent", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "getPageType", "emitUiPageViewMetric", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageDataRepository;", "getPageDataRepository", "", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "getBinderList", "requestCode", "resultCode", "Landroid/content/Intent;", "data", "onActivityResult", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "getContentEnabilityProvider", "Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsDiffUtilFactory;", "getDiffUtilFactory", "setupViews", "onLoadUpdatedPage", "Lcom/amazon/mp3/actionbar/ActionBarProvider;", "provider", "setActionBarProvider", "Lcom/amazon/music/views/library/models/FilterModel;", "filterModel", "onChildFilterClick", "Lcom/amazon/music/views/library/models/RefinementPillModel;", "model", "onClick", "onFiltersReset", "Lcom/amazon/music/views/library/models/FilterCollectionModel;", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/PageLoadLatencyCode;", "getPageLoadLatencyCode", "Lcom/amazon/music/views/library/models/EmptyStateButtonType;", "buttonType", "onEmptyStateButtonClicked", "display", "displayRefinementLoading", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "pageGridViewModel", "isInitialPage", "isMorePage", "updatePageModel", "Lcom/amazon/music/views/library/providers/RowButtonOnClickProvider;", "getRowButtonOnClickProvider", "Lcom/amazon/ui/foundations/views/BaseButton;", "button", "initEmptyViewResetFiltersButton", "", "Lcom/amazon/mp3/view/refinements/RefinementFilterType;", "getDefaultSelectedRefinements", "updatePageModelProvider", "com/amazon/mp3/catalog/fragment/LibraryArtistsFragment$getBrushUriClickListenerProvider$1", "getBrushUriClickListenerProvider", "()Lcom/amazon/mp3/catalog/fragment/LibraryArtistsFragment$getBrushUriClickListenerProvider$1;", "", "Lcom/amazon/music/views/library/models/PageHeaderActionIconModel$ActionType;", "Lcom/amazon/music/views/library/binders/ActionIconOnClickListener;", "getPageHeaderOnClickListenerMap", "initializeActionBarManager", "initializeRefinementOverflowManager", "Lcom/amazon/mp3/actionbar/BaseViewsFilterActionBarManager$InfoProvider;", "libraryInfoProvider", "Lcom/amazon/mp3/actionbar/BaseViewsFilterActionBarManager$InfoProvider;", "actionBarProvider", "Lcom/amazon/mp3/actionbar/ActionBarProvider;", "Lcom/amazon/mp3/actionbar/BaseViewsFilterActionBarManager;", "actionBarManager", "Lcom/amazon/mp3/actionbar/BaseViewsFilterActionBarManager;", "actionBarHeight", "I", "Lcom/amazon/music/views/library/models/ExposedRefinementListModel;", "refinementsModel", "Lcom/amazon/music/views/library/models/ExposedRefinementListModel;", "Lcom/amazon/mp3/detailpages/artist/LibraryArtistViewModel;", "libraryArtistViewModel", "Lcom/amazon/mp3/detailpages/artist/LibraryArtistViewModel;", "Lcom/amazon/music/views/library/providers/BrushUriClickListenerProvider;", "brushUriClickListenerProvider", "Lcom/amazon/music/views/library/providers/BrushUriClickListenerProvider;", "Lcom/amazon/mp3/view/refinements/BaseViewsRefinementOverflowManager;", "refinementOverflowManager", "Lcom/amazon/mp3/view/refinements/BaseViewsRefinementOverflowManager;", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class LibraryArtistsFragment extends LibraryBaseViewsFragment implements RefinementCallback, OverflowMenuReceiver, OnFilterChangedListener, EmptyStateActionProvider, RefinementLoadingListener {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private int actionBarHeight;
    private BaseViewsFilterActionBarManager actionBarManager;
    private ActionBarProvider actionBarProvider;
    private BrushUriClickListenerProvider brushUriClickListenerProvider;
    private LibraryArtistViewModel libraryArtistViewModel;
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

    /* JADX INFO: compiled from: LibraryArtistsFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/LibraryArtistsFragment$Companion;", "", "()V", "getUri", "Landroid/net/Uri;", PageUriUtils.SOURCE_QUERY_PARAM, "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
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

        @JvmStatic
        public final Uri getUri(String source) {
            Intrinsics.checkNotNullParameter(source, "source");
            Uri filterContentUri = MediaProvider.Artists.getFilterContentUri(source, "");
            Intrinsics.checkNotNullExpressionValue(filterContentUri, "getFilterContentUri(source, \"\")");
            return filterContentUri;
        }
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

    private final void initEmptyViewResetFiltersButton(final BaseButton button) {
        getSubscriptions().add(StyleSheetProvider.getStyleSheetObservable().subscribe(new Action1() { // from class: com.amazon.mp3.catalog.fragment.LibraryArtistsFragment$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                LibraryArtistsFragment.m493initEmptyViewResetFiltersButton$lambda1(this.f$0, button, (StyleSheet) obj);
            }
        }));
        button.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.catalog.fragment.LibraryArtistsFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LibraryArtistsFragment.m494initEmptyViewResetFiltersButton$lambda2(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initEmptyViewResetFiltersButton$lambda-1, reason: not valid java name */
    public static final void m493initEmptyViewResetFiltersButton$lambda1(LibraryArtistsFragment this$0, BaseButton button, StyleSheet styleSheet) {
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
    /* JADX INFO: renamed from: initEmptyViewResetFiltersButton$lambda-2, reason: not valid java name */
    public static final void m494initEmptyViewResetFiltersButton$lambda2(LibraryArtistsFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onFiltersReset();
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment, com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public void initPageModelProvider(FragmentActivity activity) {
        LibraryArtistViewModel libraryArtistViewModel;
        Intrinsics.checkNotNullParameter(activity, "activity");
        PageDataRepository pageDataRepository = getPageDataRepository();
        RefinablePageDataRepository refinablePageDataRepository = pageDataRepository instanceof RefinablePageDataRepository ? (RefinablePageDataRepository) pageDataRepository : null;
        if (refinablePageDataRepository != null) {
            PageViewModelFactoryProvider pageViewModelFactoryProvider = PageViewModelFactoryProvider.INSTANCE;
            Application application = activity.getApplication();
            Intrinsics.checkNotNullExpressionValue(application, "activity.application");
            setPageModelProvider(pageViewModelFactoryProvider.getViewModel(this, new PageViewModelFactoryProvider.LibraryArtistViewModelFactory(application, refinablePageDataRepository, getDefaultSelectedRefinements())));
        }
        PageViewModelProvider pageModelProvider = getPageModelProvider();
        RefinableViewModel refinableViewModel = pageModelProvider instanceof RefinableViewModel ? (RefinableViewModel) pageModelProvider : null;
        if (refinableViewModel != null) {
            refinableViewModel.setFilterListener(this);
        }
        LibraryArtistViewModel libraryArtistViewModel2 = this.libraryArtistViewModel;
        if (libraryArtistViewModel2 != null) {
            libraryArtistViewModel2.unregisterDownloadStateListener();
        }
        PageViewModelProvider pageModelProvider2 = getPageModelProvider();
        LibraryArtistViewModel libraryArtistViewModel3 = pageModelProvider2 instanceof LibraryArtistViewModel ? (LibraryArtistViewModel) pageModelProvider2 : null;
        this.libraryArtistViewModel = libraryArtistViewModel3;
        if (libraryArtistViewModel3 != null) {
            libraryArtistViewModel3.registerDownloadStateListener(getContentUri());
        }
        LibraryArtistViewModel libraryArtistViewModel4 = this.libraryArtistViewModel;
        if (libraryArtistViewModel4 != null) {
            libraryArtistViewModel4.setLoadingListener(this);
        }
        ContentEnabilityProvider contentEnabilityProvider = getContentEnabilityProvider();
        if (contentEnabilityProvider == null || (libraryArtistViewModel = this.libraryArtistViewModel) == null) {
            return;
        }
        libraryArtistViewModel.setContentEnabilityProvider(contentEnabilityProvider);
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

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        LibraryArtistViewModel libraryArtistViewModel = this.libraryArtistViewModel;
        if (libraryArtistViewModel != null) {
            libraryArtistViewModel.clearListeners();
        }
        if (this.brushUriClickListenerProvider == null) {
            return;
        }
        this.brushUriClickListenerProvider = null;
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
        return isSourceLocal() ? PageType.OFFLINE_LIBRARY_ARTISTS : PageType.CLOUD_LIBRARY_ARTISTS;
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public void emitUiPageViewMetric() {
        AbstractBaseViewsFragment.sendUiPageViewMetric$default(this, getPageType(), null, null, null, null, null, null, null, 254, null);
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public PageDataRepository getPageDataRepository() {
        Context context = getContext();
        Companion companion = INSTANCE;
        return new RefinablePageDataRepository(new LibraryArtistsDao(context, companion.getUri("cirrus"), companion.getUri("cirrus-local"), getPageType(), getPageLoadLatencyCode(), getDefaultSelectedRefinements()));
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public List<UniversalBinder<?, ?>> getBinderList() {
        BaseViewsBinderConfiguration viewsConfiguration = getViewsConfiguration();
        List<UniversalBinder<?, ?>> listBuild = null;
        if (viewsConfiguration != null) {
            BaseViewsBinderProvider.BinderListBuilder binderListBuilder = new BaseViewsBinderProvider.BinderListBuilder(viewsConfiguration);
            binderListBuilder.withDefaultHeader(getPageHeaderOnClickListenerMap(), getPageType());
            binderListBuilder.withRefinements(this);
            BaseViewsBinderProvider.BinderListBuilder.withVisualRowItems$default(binderListBuilder, null, null, 3, null);
            binderListBuilder.withEmptyView(this);
            if (AmazonApplication.getCapabilities().isTrueLikesAndFollowsEnabled()) {
                AnonymousClass1 brushUriClickListenerProvider = getBrushUriClickListenerProvider();
                this.brushUriClickListenerProvider = brushUriClickListenerProvider;
                if (brushUriClickListenerProvider == null) {
                    throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.providers.BrushUriClickListenerProvider");
                }
                binderListBuilder.withFeatureBarker(brushUriClickListenerProvider, true);
            }
            listBuild = binderListBuilder.build();
        }
        return listBuild == null ? CollectionsKt__CollectionsKt.emptyList() : listBuild;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ATCWebActivity.REQUEST_CODE && resultCode == -1) {
            updatePageModelProvider();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updatePageModelProvider() {
        PageViewModelProvider pageModelProvider = getPageModelProvider();
        if (pageModelProvider == null) {
            return;
        }
        PageViewModelProvider.DefaultImpls.fetchUpdates$default(pageModelProvider, 50, getDiffUtilFactory(), null, 4, null);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.catalog.fragment.LibraryArtistsFragment$getBrushUriClickListenerProvider$1, reason: invalid class name */
    /* JADX INFO: compiled from: LibraryArtistsFragment.kt */
    @Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"com/amazon/mp3/catalog/fragment/LibraryArtistsFragment$getBrushUriClickListenerProvider$1", "Lcom/amazon/music/views/library/providers/BrushUriClickListenerProvider;", "onClick", "", "target", "", "viewId", "targetType", "Lcom/amazon/music/views/library/models/enums/TargetType;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class AnonymousClass1 implements BrushUriClickListenerProvider {
        AnonymousClass1() {
        }

        @Override // com.amazon.music.views.library.providers.BrushUriClickListenerProvider
        public void onClick(final String target, final String viewId, final TargetType targetType) {
            if (!ConnectivityUtil.hasAnyInternetConnection(LibraryArtistsFragment.this.getContext())) {
                NetworkErrorDialog.create(LibraryArtistsFragment.this.getContext(), new LibraryFollowedArtistFragment.FollowedArtistNetworkErrorHandler() { // from class: com.amazon.mp3.catalog.fragment.LibraryArtistsFragment$getBrushUriClickListenerProvider$1$onClick$handler$1
                    @Override // com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
                    public void onConnectionErrorDialogCancel() {
                        LibraryFollowedArtistFragment.FollowedArtistNetworkErrorHandler.DefaultImpls.onConnectionErrorDialogCancel(this);
                    }

                    @Override // com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
                    public void onDialogDismiss() {
                        LibraryFollowedArtistFragment.FollowedArtistNetworkErrorHandler.DefaultImpls.onDialogDismiss(this);
                    }

                    @Override // com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
                    public void onRetryConnection() {
                        this.this$0.onClick(target, viewId, targetType);
                    }
                }).show();
            } else {
                Intent intentStart = ATCWebActivity.start(LibraryArtistsFragment.this.getContext());
                intentStart.putExtra("com.amazon.mp3.activity.EXTRA_CUSTOMER_FLOW", ATCWebActivity.CustomerFlow.ingressFromLibrary);
                LibraryArtistsFragment.this.startActivityForResult(intentStart, ATCWebActivity.REQUEST_CODE);
            }
            PageType pageType = LibraryArtistsFragment.this.getPageType();
            AbstractBaseViewsFragment.sendUiClickMetric$default(LibraryArtistsFragment.this, ActionType.ARTIST_TASTE_COLLECTION_VIEW_ACTION, null, pageType, null, null, null, null, null, null, null, null, null, 4090, null);
        }
    }

    private final AnonymousClass1 getBrushUriClickListenerProvider() {
        return new AnonymousClass1();
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
        return new BaseViewsDiffUtilFactory() { // from class: com.amazon.mp3.catalog.fragment.LibraryArtistsFragment.getDiffUtilFactory.1
            @Override // com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtilFactory
            public BaseViewsDiffUtil getBaseViewsDiffUtil(List<? extends BaseViewModel> oldList, List<? extends BaseViewModel> newList) {
                Intrinsics.checkNotNullParameter(oldList, "oldList");
                Intrinsics.checkNotNullParameter(newList, "newList");
                return new BaseViewsDiffUtil(oldList, newList);
            }
        };
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
    protected void onLoadUpdatedPage() {
        PageFetchInfo pageFetchInfoCheckLastPageFetchInfo;
        IntRange indicesChanged;
        LibraryArtistViewModel libraryArtistViewModel = this.libraryArtistViewModel;
        Unit unit = null;
        if (libraryArtistViewModel != null && (pageFetchInfoCheckLastPageFetchInfo = libraryArtistViewModel.getLastFetchedPageInfo()) != null && (indicesChanged = pageFetchInfoCheckLastPageFetchInfo.getIndicesChanged()) != null) {
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
        getSubscriptions().add(StyleSheetProvider.getStyleSheetObservable().subscribe(new Action1() { // from class: com.amazon.mp3.catalog.fragment.LibraryArtistsFragment$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                LibraryArtistsFragment.m495initializeRefinementOverflowManager$lambda14(this.f$0, (StyleSheet) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initializeRefinementOverflowManager$lambda-14, reason: not valid java name */
    public static final void m495initializeRefinementOverflowManager$lambda14(LibraryArtistsFragment this$0, StyleSheet it) {
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
        return PageLoadLatencyCode.LIBRARY_ARTISTS;
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

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected void updatePageModel(PageGridViewModel pageGridViewModel, boolean isInitialPage, boolean isMorePage) {
        super.updatePageModel(pageGridViewModel, isInitialPage, isMorePage);
        LibraryArtistViewModel libraryArtistViewModel = this.libraryArtistViewModel;
        if (libraryArtistViewModel == null) {
            return;
        }
        libraryArtistViewModel.updateAvailabilityState();
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment, com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected RowButtonOnClickProvider getRowButtonOnClickProvider(FragmentActivity activity) {
        RowButtonOnClickProvider defaultAddToLibraryProvider;
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (getRowButtonOnClickProvider() == null) {
            if (AmazonApplication.getCapabilities().isTrueLikesAndFollowsEnabled()) {
                DefaultLikesProvider defaultLikesProvider = new DefaultLikesProvider(activity, getLibraryStateProviderInstance(), null, false, getPageType(), 12, null);
                defaultLikesProvider.setFollowStateCallback(new Function1<Boolean, Unit>() { // from class: com.amazon.mp3.catalog.fragment.LibraryArtistsFragment$getRowButtonOnClickProvider$1$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                        invoke(bool.booleanValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(boolean z) {
                        this.this$0.updatePageModelProvider();
                    }
                });
                defaultAddToLibraryProvider = defaultLikesProvider;
            } else {
                defaultAddToLibraryProvider = new DefaultAddToLibraryProvider(activity, getLibraryStateProviderInstance(), getTrackContextMenuListener());
            }
            setRowButtonOnClickProvider(defaultAddToLibraryProvider);
        }
        return getRowButtonOnClickProvider();
    }
}
