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
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.actionbar.ActionBarProvider;
import com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager;
import com.amazon.mp3.activity.ATCWebActivity;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.activity.navigation.FragmentController;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.catalog.fragment.LibraryFollowedArtistFragment;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsBinderConfiguration;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsBinderProvider;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtil;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtilFactory;
import com.amazon.mp3.catalog.fragment.datasource.dao.LibraryFollowedArtistDataProvider;
import com.amazon.mp3.catalog.fragment.datasource.repository.PageDataRepository;
import com.amazon.mp3.catalog.fragment.provider.LibraryContentEnabilityProvider;
import com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelFactoryProvider;
import com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelProvider;
import com.amazon.mp3.detailpages.artist.LibraryFollowedArtistViewModel;
import com.amazon.mp3.fragment.contextmenu.FollowedArtistContextMenuProvider;
import com.amazon.mp3.fragment.headerbar.OverflowMenuReceiver;
import com.amazon.mp3.library.dialog.NetworkErrorDialog;
import com.amazon.mp3.library.fragment.ArtistListFragment;
import com.amazon.mp3.library.mylibrary.LibraryPreferences;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.providers.LibraryContextMenuProviderV2;
import com.amazon.mp3.providers.MetadataContextMenuHandler;
import com.amazon.mp3.providers.MissingSDCardHandler;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.view.BauhausRibbon;
import com.amazon.music.curate.featuregating.CurateFeatureGating;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiContentViewEvent;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContainerType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContentName;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.binders.UniversalBinder;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.ExposedRefinementListModel;
import com.amazon.music.views.library.models.FilterModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.enums.TargetType;
import com.amazon.music.views.library.providers.BrushUriClickListenerProvider;
import com.amazon.music.views.library.providers.ContentEnabilityProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.views.presentation.ActionBarOverlayable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LibraryFollowedArtistFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000ß\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t*\u0001#\u0018\u0000 _2\u00020\u00012\u00020\u00022\u00020\u0003:\u0002_`B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u001cH\u0016J\n\u0010\u001e\u001a\u0004\u0018\u00010\u0006H\u0016J\u0016\u0010\u001f\u001a\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030!0 H\u0016J\r\u0010\"\u001a\u00020#H\u0002¢\u0006\u0002\u0010$J\n\u0010%\u001a\u0004\u0018\u00010&H\u0014J\n\u0010'\u001a\u0004\u0018\u00010(H\u0016J\n\u0010)\u001a\u0004\u0018\u00010*H\u0016J\b\u0010+\u001a\u00020,H\u0016J\u000f\u0010-\u001a\u0004\u0018\u00010.H\u0016¢\u0006\u0002\u0010/J\u001a\u00100\u001a\u0002012\u0006\u00102\u001a\u0002032\b\u00104\u001a\u0004\u0018\u000101H\u0014J\b\u00105\u001a\u00020.H\u0014J\n\u00106\u001a\u0004\u0018\u000107H\u0016J\n\u00108\u001a\u0004\u0018\u000109H\u0016J\n\u0010:\u001a\u0004\u0018\u00010;H\u0014J\b\u0010<\u001a\u00020.H\u0016J\b\u0010=\u001a\u00020>H\u0016J\u000e\u0010?\u001a\b\u0012\u0004\u0012\u00020@0 H\u0016J\n\u0010A\u001a\u0004\u0018\u00010BH\u0016J\u0010\u0010C\u001a\u00020\u001c2\u0006\u0010D\u001a\u00020EH\u0016J\b\u0010F\u001a\u00020\u001cH\u0002J\b\u0010G\u001a\u00020\u001cH\u0002J\"\u0010H\u001a\u00020\u001c2\u0006\u0010I\u001a\u00020.2\u0006\u0010J\u001a\u00020.2\b\u0010K\u001a\u0004\u0018\u00010LH\u0016J\u0010\u0010M\u001a\u00020\u001c2\u0006\u0010N\u001a\u00020OH\u0016J\b\u0010P\u001a\u00020\u001cH\u0016J\b\u0010Q\u001a\u00020\u001cH\u0016J\b\u0010R\u001a\u00020SH\u0014J\u0012\u0010T\u001a\u00020\u001c2\b\u0010U\u001a\u0004\u0018\u00010\u000bH\u0016J\u0010\u0010V\u001a\u00020\u001c2\u0006\u0010W\u001a\u00020XH\u0002J\b\u0010Y\u001a\u00020\u001cH\u0014J\b\u0010Z\u001a\u00020SH\u0016J\u0010\u0010[\u001a\u00020S2\u0006\u0010\\\u001a\u00020.H\u0016J\b\u0010]\u001a\u00020SH\u0016J\b\u0010^\u001a\u00020\u001cH\u0002R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015@VX\u0094\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006a"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/LibraryFollowedArtistFragment;", "Lcom/amazon/mp3/catalog/fragment/AbstractBaseViewsFragment;", "Lcom/amazon/mp3/fragment/headerbar/OverflowMenuReceiver;", "Lcom/amazon/mp3/actionbar/BaseViewsFilterActionBarManager$InfoProvider;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "actionBarManager", "Lcom/amazon/mp3/actionbar/BaseViewsFilterActionBarManager;", "actionBarProvider", "Lcom/amazon/mp3/actionbar/ActionBarProvider;", "artistContextMenuListener", "Lcom/amazon/mp3/fragment/contextmenu/FollowedArtistContextMenuProvider$FollowedArtistContextMenuListener;", "bauhausRibbon", "Lcom/amazon/mp3/view/BauhausRibbon;", "brushUriClickListenerProvider", "Lcom/amazon/music/views/library/providers/BrushUriClickListenerProvider;", "libraryFollowedArtistViewModel", "Lcom/amazon/mp3/detailpages/artist/LibraryFollowedArtistViewModel;", "<set-?>", "Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelProvider;", "pageModelProvider", "getPageModelProvider", "()Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelProvider;", "setPageModelProvider", "(Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelProvider;)V", "dismissBauhausRibbon", "", "emitUiPageViewMetric", "getArtworkUrl", "getBinderList", "", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "getBrushUriClickListenerProvider", "com/amazon/mp3/catalog/fragment/LibraryFollowedArtistFragment$getBrushUriClickListenerProvider$1", "()Lcom/amazon/mp3/catalog/fragment/LibraryFollowedArtistFragment$getBrushUriClickListenerProvider$1;", "getContentEnabilityProvider", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "getContentMetadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "getContextMenuProvider", "Lcom/amazon/mp3/providers/MetadataContextMenuHandler;", "getDiffUtilFactory", "Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsDiffUtilFactory;", "getFilterButtonClickDestination", "", "()Ljava/lang/Integer;", "getInflatedRootView", "Landroid/view/ViewGroup;", "inflater", "Landroid/view/LayoutInflater;", "container", "getLoadingViewId", "getMissingSDCardProvider", "Lcom/amazon/mp3/providers/MissingSDCardHandler;", "getPageDataRepository", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageDataRepository;", "getPageLoadLatencyCode", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/PageLoadLatencyCode;", "getPageSize", "getPageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "getRefinementFilterModels", "Lcom/amazon/music/views/library/models/FilterModel;", "getRefinementListModel", "Lcom/amazon/music/views/library/models/ExposedRefinementListModel;", "initPageModelProvider", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "initializeActionBarManager", "navigateToLibraryArtistsPage", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onAttach", "context", "Landroid/content/Context;", "onDestroy", "onDestroyView", "requireNetworkConnection", "", "setActionBarProvider", "provider", "setupBauhausRibbon", "parent", "Landroid/view/View;", "setupViews", "shouldDisplayFilterAndContextButtons", "shouldDisplayPageComponentsInTopAppBar", "actionBarHeight", "shouldDisplayTitle", "showBauhausRibbon", "Companion", "FollowedArtistNetworkErrorHandler", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LibraryFollowedArtistFragment extends AbstractBaseViewsFragment implements OverflowMenuReceiver, BaseViewsFilterActionBarManager.InfoProvider {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String TAG = LibraryFollowedArtistFragment.class.getSimpleName();
    private BaseViewsFilterActionBarManager actionBarManager;
    private ActionBarProvider actionBarProvider;
    private FollowedArtistContextMenuProvider.FollowedArtistContextMenuListener artistContextMenuListener;
    private BauhausRibbon bauhausRibbon;
    private BrushUriClickListenerProvider brushUriClickListenerProvider;
    private LibraryFollowedArtistViewModel libraryFollowedArtistViewModel;
    private PageViewModelProvider pageModelProvider;

    /* JADX INFO: compiled from: LibraryFollowedArtistFragment.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/LibraryFollowedArtistFragment$FollowedArtistNetworkErrorHandler;", "Lcom/amazon/mp3/library/dialog/NetworkErrorDialog$INetworkErrorDialogHandler;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public interface FollowedArtistNetworkErrorHandler extends NetworkErrorDialog.INetworkErrorDialogHandler {

        /* JADX INFO: compiled from: LibraryFollowedArtistFragment.kt */
        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
        public static final class DefaultImpls {
            public static void onConnectionErrorDialogCancel(FollowedArtistNetworkErrorHandler followedArtistNetworkErrorHandler) {
                Intrinsics.checkNotNullParameter(followedArtistNetworkErrorHandler, "this");
            }

            public static void onDialogDismiss(FollowedArtistNetworkErrorHandler followedArtistNetworkErrorHandler) {
                Intrinsics.checkNotNullParameter(followedArtistNetworkErrorHandler, "this");
            }
        }
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public String getArtworkUrl() {
        return null;
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public ContentMetadata getContentMetadata() {
        return null;
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public Integer getFilterButtonClickDestination() {
        return null;
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected int getLoadingViewId() {
        return R.id.detail_loading_spinner;
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public MissingSDCardHandler getMissingSDCardProvider() {
        return null;
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public int getPageSize() {
        return 50;
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public ExposedRefinementListModel getRefinementListModel() {
        return null;
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected boolean requireNetworkConnection() {
        return true;
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public boolean shouldDisplayFilterAndContextButtons() {
        return false;
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public boolean shouldDisplayPageComponentsInTopAppBar(int actionBarHeight) {
        return false;
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    /* JADX INFO: renamed from: shouldDisplayTitle */
    public boolean getDisplayTitle() {
        return false;
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public boolean doesHaveRefinement() {
        return BaseViewsFilterActionBarManager.InfoProvider.DefaultImpls.doesHaveRefinement(this);
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    /* JADX INFO: renamed from: getTitle */
    public String getTitleText() {
        return BaseViewsFilterActionBarManager.InfoProvider.DefaultImpls.getTitle(this);
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public boolean shouldDisplayContextMenuOverflowButton() {
        return BaseViewsFilterActionBarManager.InfoProvider.DefaultImpls.shouldDisplayContextMenuOverflowButton(this);
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public boolean shouldDisplayFilterButton(RecyclerView recyclerView, int i) {
        return BaseViewsFilterActionBarManager.InfoProvider.DefaultImpls.shouldDisplayFilterButton(this, recyclerView, i);
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public boolean shouldHideRefinementsOnLoad() {
        return BaseViewsFilterActionBarManager.InfoProvider.DefaultImpls.shouldHideRefinementsOnLoad(this);
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected PageViewModelProvider getPageModelProvider() {
        return this.pageModelProvider;
    }

    public void setPageModelProvider(PageViewModelProvider pageViewModelProvider) {
        this.pageModelProvider = pageViewModelProvider;
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        this.artistContextMenuListener = new FollowedArtistContextMenuProvider.FollowedArtistContextMenuListener() { // from class: com.amazon.mp3.catalog.fragment.LibraryFollowedArtistFragment.onAttach.1
            @Override // com.amazon.mp3.fragment.contextmenu.FollowedArtistContextMenuProvider.FollowedArtistContextMenuListener
            public void onArtistUnfollow() {
                PageViewModelProvider pageModelProvider = LibraryFollowedArtistFragment.this.getPageModelProvider();
                if (pageModelProvider == null) {
                    return;
                }
                PageViewModelProvider.DefaultImpls.fetchUpdates$default(pageModelProvider, 50, LibraryFollowedArtistFragment.this.getDiffUtilFactory(), null, 4, null);
            }
        };
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

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected void setupViews() {
        LibraryFollowedArtistViewModel libraryFollowedArtistViewModel;
        LiveData<Boolean> liveDataIsLibraryArtistsEmptyLiveData;
        super.setupViews();
        setActionBarProvider((ActionBarProvider) requireActivity());
        initializeActionBarManager();
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager = this.actionBarManager;
        if (baseViewsFilterActionBarManager != null) {
            getLifecycle().addObserver(baseViewsFilterActionBarManager);
        }
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager2 = this.actionBarManager;
        int actionBarHeight = baseViewsFilterActionBarManager2 == null ? 0 : baseViewsFilterActionBarManager2.getActionBarHeight();
        KeyEventDispatcher.Component activity = getActivity();
        ActionBarOverlayable actionBarOverlayable = activity instanceof ActionBarOverlayable ? (ActionBarOverlayable) activity : null;
        if (actionBarOverlayable != null) {
            actionBarOverlayable.setContentOffset(getRecyclerView(), actionBarHeight);
        }
        LibraryFollowedArtistViewModel libraryFollowedArtistViewModel2 = this.libraryFollowedArtistViewModel;
        if (libraryFollowedArtistViewModel2 != null && (liveDataIsLibraryArtistsEmptyLiveData = libraryFollowedArtistViewModel2.isLibraryArtistsEmptyLiveData()) != null) {
            liveDataIsLibraryArtistsEmptyLiveData.observe(requireActivity(), new Observer() { // from class: com.amazon.mp3.catalog.fragment.LibraryFollowedArtistFragment$$ExternalSyntheticLambda0
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    LibraryFollowedArtistFragment.m504setupViews$lambda2(this.f$0, (Boolean) obj);
                }
            });
        }
        Context context = getContext();
        if (context == null || (libraryFollowedArtistViewModel = this.libraryFollowedArtistViewModel) == null) {
            return;
        }
        libraryFollowedArtistViewModel.updateLibraryArtistsEmptyLiveDataValue(context.getContentResolver());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupViews$lambda-2, reason: not valid java name */
    public static final void m504setupViews$lambda2(LibraryFollowedArtistFragment this$0, Boolean isLibraryArtistsEmpty) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(isLibraryArtistsEmpty, "isLibraryArtistsEmpty");
        if (isLibraryArtistsEmpty.booleanValue()) {
            this$0.dismissBauhausRibbon();
            return;
        }
        ViewGroup root = this$0.getRoot();
        if (root == null) {
            return;
        }
        this$0.setupBauhausRibbon(root);
    }

    private final void setupBauhausRibbon(View parent) {
        Context context;
        final int followedArtistsBannerDismissCount;
        StyleSheet styleSheet = getStyleSheet();
        if (styleSheet == null || (context = getContext()) == null || (followedArtistsBannerDismissCount = LibraryPreferences.INSTANCE.readFollowedArtistsBannerDismissCount(context)) >= 3) {
            return;
        }
        final BauhausRibbon bauhausRibbon = (BauhausRibbon) parent.findViewById(R.id.bauhaus_ribbon);
        if (bauhausRibbon == null) {
            bauhausRibbon = null;
        } else {
            bauhausRibbon.initViewStyling(styleSheet);
            bauhausRibbon.setRibbonTextAlignment(2);
            bauhausRibbon.setRibbonTextStyle(styleSheet, FontStyleKey.SECONDARY);
            bauhausRibbon.setRoundedBackground();
        }
        this.bauhausRibbon = bauhausRibbon;
        if (bauhausRibbon == null) {
            return;
        }
        String string = getString(R.string.dmusic_artists_page_educational_banner);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.dmusi…_page_educational_banner)");
        bauhausRibbon.setRibbonText(string);
        bauhausRibbon.setRibbonDismissIcon(Integer.valueOf(R.drawable.ic_action_cancel2));
        bauhausRibbon.setRibbonNavigationIcon(Integer.valueOf(R.drawable.ic_chevron_right));
        bauhausRibbon.setNavigationButtonClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.catalog.fragment.LibraryFollowedArtistFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LibraryFollowedArtistFragment.m502setupBauhausRibbon$lambda10$lambda9$lambda8$lambda5(this.f$0, view);
            }
        });
        bauhausRibbon.setDismissButtonClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.catalog.fragment.LibraryFollowedArtistFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LibraryFollowedArtistFragment.m503setupBauhausRibbon$lambda10$lambda9$lambda8$lambda7(this.f$0, bauhausRibbon, followedArtistsBannerDismissCount, view);
            }
        });
        showBauhausRibbon();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupBauhausRibbon$lambda-10$lambda-9$lambda-8$lambda-5, reason: not valid java name */
    public static final void m502setupBauhausRibbon$lambda10$lambda9$lambda8$lambda5(LibraryFollowedArtistFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AbstractBaseViewsFragment.sendUiClickMetric$default(this$0, ActionType.GO_LIBRARY_ARTISTS, InteractionType.TAP, this$0.getPageType(), null, null, null, null, null, null, null, null, MetricsLogger.getContentInfo(ContentName.LIBRARY_BANNER.getMetricsValue()), 2040, null);
        this$0.navigateToLibraryArtistsPage();
        this$0.dismissBauhausRibbon();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupBauhausRibbon$lambda-10$lambda-9$lambda-8$lambda-7, reason: not valid java name */
    public static final void m503setupBauhausRibbon$lambda10$lambda9$lambda8$lambda7(LibraryFollowedArtistFragment this$0, BauhausRibbon this_apply, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        AbstractBaseViewsFragment.sendUiClickMetric$default(this$0, ActionType.DISMISS_FOLLOWED_ARTISTS_BANNER, InteractionType.TAP, this$0.getPageType(), null, null, null, null, null, null, null, null, MetricsLogger.getContentInfo(ContentName.LIBRARY_BANNER.getMetricsValue()), 2040, null);
        Context context = this_apply.getContext();
        if (context != null) {
            LibraryPreferences.INSTANCE.writeFollowedArtistsBannerDismissCount(context, i + 1);
        }
        this$0.dismissBauhausRibbon();
    }

    private final void navigateToLibraryArtistsPage() {
        if (getActivity() instanceof MusicHomeActivity) {
            FragmentActivity activity = getActivity();
            if (activity == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.activity.MusicHomeActivity");
            }
            FragmentController fragmentController = ((MusicHomeActivity) activity).getFragmentController();
            Uri filterContentUri = MediaProvider.Artists.getFilterContentUri(getSourceId(), "");
            ArtistListFragment artistListFragment = new ArtistListFragment();
            Bundle arguments = artistListFragment.getArguments();
            if (arguments != null) {
                arguments.putParcelable("com.amazon.mp3.library.EXTRA_CONTENT_URI", filterContentUri);
                arguments.putString("com.amazon.mp3.library.EXTRA_SOURCE_ID", MediaProvider.getSource(filterContentUri));
                arguments.putBoolean("com.amazon.mp3.library.EXTRA_USE_GRIDVIEW", false);
                arguments.putBoolean("com.amazon.mp3.library.EXTRA_FRAGMENT_IN_TAB_HOST", false);
                arguments.putBoolean("LibraryFollowedArtistFragment", true);
            }
            if (fragmentController == null) {
                return;
            }
            fragmentController.changeScreenFragment(artistListFragment, true, true);
        }
    }

    private final void showBauhausRibbon() {
        MetricsLogger.sendEvent(UiContentViewEvent.clientEventBuilder().withContainerType(ContainerType.BANNER).withContentName(ContentName.LIBRARY_BANNER).withImpressionTimestamp(System.currentTimeMillis()).withPageType(getPageType().getMetricsValue()).build());
        BauhausRibbon bauhausRibbon = this.bauhausRibbon;
        if (bauhausRibbon == null) {
            return;
        }
        bauhausRibbon.setVisibility(0);
    }

    private final void dismissBauhausRibbon() {
        BauhausRibbon bauhausRibbon = this.bauhausRibbon;
        if (bauhausRibbon == null) {
            return;
        }
        bauhausRibbon.setVisibility(8);
    }

    @Override // com.amazon.mp3.fragment.headerbar.OverflowMenuReceiver
    public void setActionBarProvider(ActionBarProvider provider) {
        if (provider == null) {
            return;
        }
        this.actionBarProvider = provider;
    }

    private final void initializeActionBarManager() {
        ActionBarProvider actionBarProvider = this.actionBarProvider;
        if (actionBarProvider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("actionBarProvider");
            actionBarProvider = null;
        }
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager = new BaseViewsFilterActionBarManager(this, this, null, actionBarProvider);
        this.actionBarManager = baseViewsFilterActionBarManager;
        baseViewsFilterActionBarManager.hideFilterAndContextButtons();
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public void initPageModelProvider(FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        PageDataRepository pageDataRepository = getPageDataRepository();
        if (pageDataRepository == null) {
            return;
        }
        PageViewModelFactoryProvider pageViewModelFactoryProvider = PageViewModelFactoryProvider.INSTANCE;
        Application application = activity.getApplication();
        Intrinsics.checkNotNullExpressionValue(application, "activity.application");
        setPageModelProvider(pageViewModelFactoryProvider.getViewModel(this, new PageViewModelFactoryProvider.LibraryFollowedArtistViewModelFactory(application, pageDataRepository, true)));
        PageViewModelProvider pageModelProvider = getPageModelProvider();
        this.libraryFollowedArtistViewModel = pageModelProvider instanceof LibraryFollowedArtistViewModel ? (LibraryFollowedArtistViewModel) pageModelProvider : null;
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public PageDataRepository getPageDataRepository() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return null;
        }
        return new PageDataRepository(new LibraryFollowedArtistDataProvider(activity, getPageLoadLatencyCode()));
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public List<UniversalBinder<?, ?>> getBinderList() {
        this.brushUriClickListenerProvider = getBrushUriClickListenerProvider();
        BaseViewsBinderConfiguration viewsConfiguration = getViewsConfiguration();
        List<UniversalBinder<?, ?>> listBuild = null;
        if (viewsConfiguration != null) {
            BaseViewsBinderProvider.BinderListBuilder binderListBuilderWithVisualRowItems$default = BaseViewsBinderProvider.BinderListBuilder.withVisualRowItems$default(new BaseViewsBinderProvider.BinderListBuilder(viewsConfiguration).withDefaultHeader(new LinkedHashMap(), getPageType()), null, null, 3, null);
            BrushUriClickListenerProvider brushUriClickListenerProvider = this.brushUriClickListenerProvider;
            if (brushUriClickListenerProvider == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.providers.BrushUriClickListenerProvider");
            }
            listBuild = binderListBuilderWithVisualRowItems$default.withFeatureBarker(brushUriClickListenerProvider, true).build();
        }
        return listBuild == null ? CollectionsKt__CollectionsKt.emptyList() : listBuild;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.catalog.fragment.LibraryFollowedArtistFragment$getBrushUriClickListenerProvider$1, reason: invalid class name */
    /* JADX INFO: compiled from: LibraryFollowedArtistFragment.kt */
    @Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"com/amazon/mp3/catalog/fragment/LibraryFollowedArtistFragment$getBrushUriClickListenerProvider$1", "Lcom/amazon/music/views/library/providers/BrushUriClickListenerProvider;", "onClick", "", "target", "", "viewId", "targetType", "Lcom/amazon/music/views/library/models/enums/TargetType;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class AnonymousClass1 implements BrushUriClickListenerProvider {
        AnonymousClass1() {
        }

        @Override // com.amazon.music.views.library.providers.BrushUriClickListenerProvider
        public void onClick(final String target, final String viewId, final TargetType targetType) {
            if (!ConnectivityUtil.hasAnyInternetConnection(LibraryFollowedArtistFragment.this.getContext())) {
                NetworkErrorDialog.create(LibraryFollowedArtistFragment.this.getContext(), new FollowedArtistNetworkErrorHandler() { // from class: com.amazon.mp3.catalog.fragment.LibraryFollowedArtistFragment$getBrushUriClickListenerProvider$1$onClick$handler$1
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
                return;
            }
            PageType pageType = LibraryFollowedArtistFragment.this.getPageType();
            AbstractBaseViewsFragment.sendUiClickMetric$default(LibraryFollowedArtistFragment.this, ActionType.ARTIST_TASTE_COLLECTION_VIEW_ACTION, null, pageType, null, null, null, null, null, null, null, null, null, 4090, null);
            Intent intentStart = ATCWebActivity.start(LibraryFollowedArtistFragment.this.getContext());
            intentStart.putExtra("com.amazon.mp3.activity.EXTRA_CUSTOMER_FLOW", ATCWebActivity.CustomerFlow.ingressFromLibrary);
            LibraryFollowedArtistFragment.this.startActivityForResult(intentStart, ATCWebActivity.REQUEST_CODE);
        }
    }

    private final AnonymousClass1 getBrushUriClickListenerProvider() {
        return new AnonymousClass1();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        PageViewModelProvider pageModelProvider;
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ATCWebActivity.REQUEST_CODE && resultCode == -1 && (pageModelProvider = getPageModelProvider()) != null) {
            PageViewModelProvider.DefaultImpls.fetchUpdates$default(pageModelProvider, 50, getDiffUtilFactory(), null, 4, null);
        }
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public MetadataContextMenuHandler getContextMenuProvider() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return null;
        }
        if (getContextMenuHandler() == null) {
            setContextMenuHandler(new LibraryContextMenuProviderV2(activity, this, getPageType(), this.artistContextMenuListener));
        }
        return getContextMenuHandler();
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public BaseViewsDiffUtilFactory getDiffUtilFactory() {
        return new BaseViewsDiffUtilFactory() { // from class: com.amazon.mp3.catalog.fragment.LibraryFollowedArtistFragment.getDiffUtilFactory.1
            @Override // com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtilFactory
            public BaseViewsDiffUtil getBaseViewsDiffUtil(List<? extends BaseViewModel> oldList, List<? extends BaseViewModel> newList) {
                Intrinsics.checkNotNullParameter(oldList, "oldList");
                Intrinsics.checkNotNullParameter(newList, "newList");
                return new BaseViewsDiffUtil(oldList, newList);
            }
        };
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
    public PageType getPageType() {
        return PageType.CLOUD_LIBRARY_FOLLOWED_ARTISTS;
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public void emitUiPageViewMetric() {
        AbstractBaseViewsFragment.sendUiPageViewMetric$default(this, getPageType(), null, null, null, null, null, null, null, 254, null);
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        if (this.artistContextMenuListener != null) {
            this.artistContextMenuListener = null;
        }
        if (this.brushUriClickListenerProvider == null) {
            return;
        }
        this.brushUriClickListenerProvider = null;
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager = this.actionBarManager;
        if (baseViewsFilterActionBarManager == null) {
            return;
        }
        getLifecycle().removeObserver(baseViewsFilterActionBarManager);
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected PageLoadLatencyCode getPageLoadLatencyCode() {
        return PageLoadLatencyCode.LIBRARY_FOLLOWED_ARTISTS;
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public List<FilterModel> getRefinementFilterModels() {
        return new ArrayList();
    }

    /* JADX INFO: compiled from: LibraryFollowedArtistFragment.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/LibraryFollowedArtistFragment$Companion;", "", "()V", "FROM_LIBRARY_FOLLOWED_ARTISTS_FRAGMENT", "", "isLibraryFollowedArtistFragmentSupported", "", "context", "Landroid/content/Context;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean isLibraryFollowedArtistFragmentSupported(Context context) {
            return context != null && AmazonApplication.getCapabilities().isArtistFollowSupported() && CurateFeatureGating.LIBRARY_FOLLOWED_ARTIST.isEnabled() && ConnectivityUtil.hasAnyInternetConnection(context);
        }
    }
}
