package com.amazon.mp3.find.view;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.brush.BrushViews;
import com.amazon.mp3.card.prime.ContextMenuHandler;
import com.amazon.mp3.card.prime.ContextMenuSupportingFragment;
import com.amazon.mp3.catalog.fragment.BrowseViewsFactory;
import com.amazon.mp3.find.util.BrushViewModelWrapper;
import com.amazon.mp3.fragment.contextmenu.ContextMenuItemClickEvent;
import com.amazon.mp3.library.provider.LibraryStateProvider;
import com.amazon.mp3.providers.DefaultAddToLibraryProvider;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.find.providers.SpellCorrectionListenerClickProvider;
import com.amazon.music.find.viewmodels.FilterViewModel;
import com.amazon.music.find.viewmodels.FilterViewModelFactory;
import com.amazon.music.find.viewmodels.SearchViewModel;
import com.amazon.music.find.viewmodels.SearchViewModelFactory;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.search.SpellCorrections;
import com.amazon.music.subscription.UserSubscription;
import com.amazon.music.uicontentview.ContentViewManager;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.enums.TargetType;
import com.amazon.music.views.library.providers.BrushUriClickListenerProvider;
import com.amazon.music.views.library.providers.EndlessScrollDataProvider;
import com.amazon.music.views.library.providers.RowButtonOnClickProvider;
import com.amazon.music.views.library.providers.SeeMoreProvider;
import com.amazon.music.views.library.providers.StateChangeProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.ui.foundations.views.BaseButton;
import dagger.android.support.AndroidSupportInjection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: FindFilterDialogFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000Ï\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t*\u0005WZ]`c\u0018\u0000 n2\u00020\u00012\u00020\u0002:\u0001nB\u0007¢\u0006\u0004\bl\u0010mJ\b\u0010\u0004\u001a\u00020\u0003H\u0002J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0006\u001a\u00020\u0005H\u0002J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\t\u001a\u00020\u0005H\u0002J\b\u0010\r\u001a\u00020\fH\u0002J\u001a\u0010\u0012\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0011\u001a\u00020\u0010H\u0002J\u0012\u0010\u0014\u001a\u00020\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u000eH\u0002J\b\u0010\u0015\u001a\u00020\fH\u0002J\u0018\u0010\u0016\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0010H\u0002J\u0010\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u000f\u001a\u00020\u000eH\u0002J\b\u0010\u0019\u001a\u00020\fH\u0002J\u0018\u0010\u001e\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001cH\u0002J\u0010\u0010!\u001a\u00020\f2\u0006\u0010 \u001a\u00020\u001fH\u0016J\b\u0010\"\u001a\u00020\fH\u0016J\u0012\u0010%\u001a\u00020\f2\b\u0010$\u001a\u0004\u0018\u00010#H\u0016J&\u0010+\u001a\u0004\u0018\u00010*2\u0006\u0010'\u001a\u00020&2\b\u0010)\u001a\u0004\u0018\u00010(2\b\u0010$\u001a\u0004\u0018\u00010#H\u0016J\b\u0010,\u001a\u00020\fH\u0016J\u0012\u0010-\u001a\u00020\f2\b\u0010$\u001a\u0004\u0018\u00010#H\u0016J\u001c\u00101\u001a\u00020\f2\b\u0010/\u001a\u0004\u0018\u00010.2\b\u00100\u001a\u0004\u0018\u00010*H\u0016J\b\u00102\u001a\u00020\fH\u0016R\"\u00104\u001a\u0002038\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b4\u00105\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\"\u0010;\u001a\u00020:8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b;\u0010<\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R\u0018\u0010A\u001a\u0004\u0018\u00010\u001c8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bA\u0010BR\u0016\u0010D\u001a\u00020C8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bD\u0010ER\u0018\u0010F\u001a\u0004\u0018\u00010\u00078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bF\u0010GR\u0018\u0010H\u001a\u0004\u0018\u00010\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bH\u0010IR\u0018\u0010J\u001a\u0004\u0018\u00010\u00178\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bJ\u0010KR\u0016\u0010M\u001a\u00020L8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bM\u0010NR\u0016\u0010O\u001a\u00020\u001a8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bO\u0010PR\u0016\u0010R\u001a\u00020Q8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bR\u0010SR\u001c\u0010U\u001a\b\u0012\u0004\u0012\u00020\u00030T8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bU\u0010VR\u0014\u0010X\u001a\u00020W8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bX\u0010YR\u0014\u0010[\u001a\u00020Z8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b[\u0010\\R\u0014\u0010^\u001a\u00020]8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b^\u0010_R\u0014\u0010a\u001a\u00020`8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\ba\u0010bR\u0014\u0010d\u001a\u00020c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bd\u0010eR\u0018\u0010g\u001a\u0004\u0018\u00010f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bg\u0010hR\u0011\u0010k\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\bi\u0010j¨\u0006o"}, d2 = {"Lcom/amazon/mp3/find/view/FindFilterDialogFragment;", "Landroidx/fragment/app/DialogFragment;", "Lcom/amazon/mp3/card/prime/ContextMenuSupportingFragment;", "Lcom/amazon/music/find/viewmodels/FilterViewModel;", "createViewModel", "Landroidx/fragment/app/FragmentActivity;", "fragmentActivity", "Lcom/amazon/mp3/find/view/BrushContextMenuProvider;", "createContextMenuProvider", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Lcom/amazon/music/views/library/providers/RowButtonOnClickProvider;", "createRowButtonOnClickProvider", "", "handlePageRefresh", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "pageGridViewModel", "", "isInitialPage", "updatePageModel", "pageModel", "hasValidBrushData", "showEmptyView", "setupBrushViews", "Lcom/amazon/mp3/brush/BrushViews;", "createBrushViews", "handlePageVisibilityChange", "Lcom/amazon/ui/foundations/views/BaseButton;", "dismissButton", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "setupDismissButtonStyle", "Landroid/content/Context;", "context", "onAttach", "onStart", "Landroid/os/Bundle;", "savedInstanceState", "onActivityCreated", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "Landroid/view/View;", "onCreateView", "onResume", "onCreate", "Lcom/amazon/mp3/card/prime/ContextMenuHandler;", "contextMenuHandler", "view", "openContextMenuForView", "onDestroy", "Lcom/amazon/music/find/viewmodels/FilterViewModelFactory;", "filterViewModelFactory", "Lcom/amazon/music/find/viewmodels/FilterViewModelFactory;", "getFilterViewModelFactory", "()Lcom/amazon/music/find/viewmodels/FilterViewModelFactory;", "setFilterViewModelFactory", "(Lcom/amazon/music/find/viewmodels/FilterViewModelFactory;)V", "Lcom/amazon/music/find/viewmodels/SearchViewModelFactory;", "searchViewModelFactory", "Lcom/amazon/music/find/viewmodels/SearchViewModelFactory;", "getSearchViewModelFactory", "()Lcom/amazon/music/find/viewmodels/SearchViewModelFactory;", "setSearchViewModelFactory", "(Lcom/amazon/music/find/viewmodels/SearchViewModelFactory;)V", "mStyleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "Lcom/amazon/music/uicontentview/ContentViewManager;", "mContentViewManager", "Lcom/amazon/music/uicontentview/ContentViewManager;", "mContextMenuProvider", "Lcom/amazon/mp3/find/view/BrushContextMenuProvider;", "mRowButtonOnClickProvider", "Lcom/amazon/music/views/library/providers/RowButtonOnClickProvider;", "mBrushViews", "Lcom/amazon/mp3/brush/BrushViews;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "mConstraintLayout", "Landroidx/constraintlayout/widget/ConstraintLayout;", "mDismissButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "Landroidx/recyclerview/widget/RecyclerView;", "mRecyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "Lcom/amazon/mp3/find/util/BrushViewModelWrapper;", "mViewModelWrapper", "Lcom/amazon/mp3/find/util/BrushViewModelWrapper;", "com/amazon/mp3/find/view/FindFilterDialogFragment$mUriClickProvider$1", "mUriClickProvider", "Lcom/amazon/mp3/find/view/FindFilterDialogFragment$mUriClickProvider$1;", "com/amazon/mp3/find/view/FindFilterDialogFragment$mSeeMoreProvider$1", "mSeeMoreProvider", "Lcom/amazon/mp3/find/view/FindFilterDialogFragment$mSeeMoreProvider$1;", "com/amazon/mp3/find/view/FindFilterDialogFragment$mEndlessScrollDataProvider$1", "mEndlessScrollDataProvider", "Lcom/amazon/mp3/find/view/FindFilterDialogFragment$mEndlessScrollDataProvider$1;", "com/amazon/mp3/find/view/FindFilterDialogFragment$mSpellCorrectionClickProvider$1", "mSpellCorrectionClickProvider", "Lcom/amazon/mp3/find/view/FindFilterDialogFragment$mSpellCorrectionClickProvider$1;", "com/amazon/mp3/find/view/FindFilterDialogFragment$mStateChangeProvider$1", "mStateChangeProvider", "Lcom/amazon/mp3/find/view/FindFilterDialogFragment$mStateChangeProvider$1;", "Lcom/amazon/mp3/find/view/SearchMetadataClickListener;", "mMetadataClickListener", "Lcom/amazon/mp3/find/view/SearchMetadataClickListener;", "getViewModel", "()Lcom/amazon/music/find/viewmodels/FilterViewModel;", "viewModel", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class FindFilterDialogFragment extends DialogFragment implements ContextMenuSupportingFragment {
    public FilterViewModelFactory filterViewModelFactory;
    private BrushViews mBrushViews;
    private ConstraintLayout mConstraintLayout;
    private ContentViewManager mContentViewManager;
    private BrushContextMenuProvider mContextMenuProvider;
    private BaseButton mDismissButton;
    private SearchMetadataClickListener mMetadataClickListener;
    private RecyclerView mRecyclerView;
    private RowButtonOnClickProvider mRowButtonOnClickProvider;
    private StyleSheet mStyleSheet;
    private BrushViewModelWrapper<FilterViewModel> mViewModelWrapper;
    public SearchViewModelFactory searchViewModelFactory;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Logger logger = LoggerFactory.getLogger(FindFilterDialogFragment.class.getName());
    private static final String FRAGMENT_TAG = FindFilterDialogFragment.class.getName();
    private final FindFilterDialogFragment$mUriClickProvider$1 mUriClickProvider = new BrushUriClickListenerProvider() { // from class: com.amazon.mp3.find.view.FindFilterDialogFragment$mUriClickProvider$1
        @Override // com.amazon.music.views.library.providers.BrushUriClickListenerProvider
        public void onClick(String target, String viewId, TargetType targetType) {
            if (targetType == TargetType.APPLY_FILTERS) {
                this.this$0.getViewModel().applyFilters();
            } else {
                if (target == null) {
                    return;
                }
                this.this$0.getViewModel().updateFilterSelection(target);
            }
        }
    };
    private final FindFilterDialogFragment$mSeeMoreProvider$1 mSeeMoreProvider = new SeeMoreProvider() { // from class: com.amazon.mp3.find.view.FindFilterDialogFragment$mSeeMoreProvider$1
        @Override // com.amazon.music.views.library.providers.SeeMoreProvider
        public void onSeeMoreClick(String uri, String viewId, String nextPageToken) {
        }
    };
    private final FindFilterDialogFragment$mEndlessScrollDataProvider$1 mEndlessScrollDataProvider = new EndlessScrollDataProvider() { // from class: com.amazon.mp3.find.view.FindFilterDialogFragment$mEndlessScrollDataProvider$1
        @Override // com.amazon.music.views.library.providers.EndlessScrollDataProvider
        public void loadMore(int page, int totalItemsCount) {
        }
    };
    private final FindFilterDialogFragment$mSpellCorrectionClickProvider$1 mSpellCorrectionClickProvider = new SpellCorrectionListenerClickProvider() { // from class: com.amazon.mp3.find.view.FindFilterDialogFragment$mSpellCorrectionClickProvider$1
        @Override // com.amazon.music.find.providers.SpellCorrectionListenerClickProvider
        public void onClick(String linkText, SpellCorrections spellCorrections) {
            Intrinsics.checkNotNullParameter(linkText, "linkText");
            Intrinsics.checkNotNullParameter(spellCorrections, "spellCorrections");
        }
    };
    private final FindFilterDialogFragment$mStateChangeProvider$1 mStateChangeProvider = new StateChangeProvider() { // from class: com.amazon.mp3.find.view.FindFilterDialogFragment$mStateChangeProvider$1
        @Override // com.amazon.music.views.library.providers.StateChangeProvider
        public void onResetState(String viewId) {
            this.this$0.getViewModel().resetFilterSelection();
        }
    };

    /* JADX INFO: compiled from: FindFilterDialogFragment.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000b\u001a\u00020\fR\u0019\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n \u0005*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/find/view/FindFilterDialogFragment$Companion;", "", "()V", "FRAGMENT_TAG", "", "kotlin.jvm.PlatformType", "getFRAGMENT_TAG", "()Ljava/lang/String;", "TAG", "logger", "Lorg/slf4j/Logger;", "newInstance", "Lcom/amazon/mp3/find/view/FindFilterDialogFragment;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getFRAGMENT_TAG() {
            return FindFilterDialogFragment.FRAGMENT_TAG;
        }

        public final FindFilterDialogFragment newInstance() {
            return new FindFilterDialogFragment();
        }
    }

    public final FilterViewModelFactory getFilterViewModelFactory() {
        FilterViewModelFactory filterViewModelFactory = this.filterViewModelFactory;
        if (filterViewModelFactory != null) {
            return filterViewModelFactory;
        }
        Intrinsics.throwUninitializedPropertyAccessException("filterViewModelFactory");
        return null;
    }

    public final SearchViewModelFactory getSearchViewModelFactory() {
        SearchViewModelFactory searchViewModelFactory = this.searchViewModelFactory;
        if (searchViewModelFactory != null) {
            return searchViewModelFactory;
        }
        Intrinsics.throwUninitializedPropertyAccessException("searchViewModelFactory");
        return null;
    }

    public final FilterViewModel getViewModel() {
        BrushViewModelWrapper<FilterViewModel> brushViewModelWrapper = this.mViewModelWrapper;
        if (brushViewModelWrapper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModelWrapper");
            brushViewModelWrapper = null;
        }
        return (FilterViewModel) brushViewModelWrapper.getViewModel();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        Window window;
        logger.debug("SPL::onStart");
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog == null || (window = dialog.getWindow()) == null) {
            return;
        }
        window.setLayout(-1, -1);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        logger.debug("SPL::onActivityCreated");
        super.onActivityCreated(savedInstanceState);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            this.mContextMenuProvider = createContextMenuProvider(activity);
            this.mRowButtonOnClickProvider = createRowButtonOnClickProvider(activity);
        }
        handlePageRefresh();
        handlePageVisibilityChange();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup viewGroup;
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        logger.debug("SPL::onCreateView");
        if (getViewModel().showIvyAudioFilters()) {
            View viewInflate = inflater.inflate(R.layout.find_filter_view_ivy, container, false);
            if (viewInflate == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup");
            }
            viewGroup = (ViewGroup) viewInflate;
        } else {
            View viewInflate2 = inflater.inflate(R.layout.find_filter_view, container, false);
            if (viewInflate2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup");
            }
            viewGroup = (ViewGroup) viewInflate2;
        }
        View viewFindViewById = viewGroup.findViewById(R.id.filter_options);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "root.findViewById<Recycl…iew>(R.id.filter_options)");
        this.mRecyclerView = (RecyclerView) viewFindViewById;
        View viewFindViewById2 = viewGroup.findViewById(R.id.filter_dialog);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "root.findViewById<Constr…yout>(R.id.filter_dialog)");
        this.mConstraintLayout = (ConstraintLayout) viewFindViewById2;
        View viewFindViewById3 = viewGroup.findViewById(R.id.dismiss_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "root.findViewById<BaseButton>(R.id.dismiss_button)");
        BaseButton baseButton = (BaseButton) viewFindViewById3;
        this.mDismissButton = baseButton;
        if (baseButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDismissButton");
            baseButton = null;
        }
        baseButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.find.view.FindFilterDialogFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FindFilterDialogFragment.m1020onCreateView$lambda2$lambda1(this.f$0, view);
            }
        });
        return viewGroup;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateView$lambda-2$lambda-1, reason: not valid java name */
    public static final void m1020onCreateView$lambda2$lambda1(FindFilterDialogFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FilterViewModel.hide$default(this$0.getViewModel(), null, 1, null);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        logger.debug("SPL::onResume");
        super.onResume();
        BrushViewModelWrapper<FilterViewModel> brushViewModelWrapper = this.mViewModelWrapper;
        if (brushViewModelWrapper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModelWrapper");
            brushViewModelWrapper = null;
        }
        brushViewModelWrapper.onResume();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        logger.debug("SPL::onCreate");
        super.onCreate(savedInstanceState);
        this.mContentViewManager = new ContentViewManager();
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        Intrinsics.checkNotNullExpressionValue(context, "context!!");
        this.mViewModelWrapper = new BrushViewModelWrapper<>(context, createViewModel());
        StyleSheetProvider.getStyleSheet().observe(this, new Observer() { // from class: com.amazon.mp3.find.view.FindFilterDialogFragment$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FindFilterDialogFragment.m1019onCreate$lambda3(this.f$0, (StyleSheet) obj);
            }
        });
        setStyle(1, R.style.SheetDialog);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-3, reason: not valid java name */
    public static final void m1019onCreate$lambda3(FindFilterDialogFragment this$0, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this$0.mStyleSheet = styleSheet;
        BaseButton baseButton = this$0.mDismissButton;
        if (baseButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDismissButton");
            baseButton = null;
        }
        this$0.setupDismissButtonStyle(baseButton, styleSheet);
    }

    @Override // com.amazon.mp3.card.prime.ContextMenuSupportingFragment
    public void openContextMenuForView(ContextMenuHandler contextMenuHandler, View view) {
        Intrinsics.checkNotNull(view);
        registerForContextMenu(view);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.openContextMenu(view);
        }
        unregisterForContextMenu(view);
    }

    private final FilterViewModel createViewModel() {
        logger.debug("SPL::createViewModel");
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity);
        Intrinsics.checkNotNullExpressionValue(activity, "activity!!");
        SearchViewModel searchViewModel = (SearchViewModel) new ViewModelProvider(activity, getSearchViewModelFactory()).get(SearchViewModel.class);
        FilterViewModel filterViewModel = (FilterViewModel) getFilterViewModelFactory().create(FilterViewModel.class);
        filterViewModel.setParentViewModel(searchViewModel);
        return filterViewModel;
    }

    private final BrushContextMenuProvider createContextMenuProvider(FragmentActivity fragmentActivity) {
        BrushViewModelWrapper<FilterViewModel> brushViewModelWrapper = this.mViewModelWrapper;
        BrushViewModelWrapper<FilterViewModel> brushViewModelWrapper2 = null;
        if (brushViewModelWrapper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModelWrapper");
            brushViewModelWrapper = null;
        }
        LibraryStateProvider mLibraryStateProvider = brushViewModelWrapper.getMLibraryStateProvider();
        BrushViewModelWrapper<FilterViewModel> brushViewModelWrapper3 = this.mViewModelWrapper;
        if (brushViewModelWrapper3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModelWrapper");
        } else {
            brushViewModelWrapper2 = brushViewModelWrapper3;
        }
        return new SearchContextMenuProvider(fragmentActivity, this, mLibraryStateProvider, brushViewModelWrapper2.getMPageStateManager(), new Function2<ContentMetadata, ContextMenuItemClickEvent, Boolean>() { // from class: com.amazon.mp3.find.view.FindFilterDialogFragment.createContextMenuProvider.1
            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
            public final Boolean mo4invoke(ContentMetadata contentMetadata, ContextMenuItemClickEvent contextMenuItemClickEvent) {
                return Boolean.TRUE;
            }
        }, new Function0<PageType>() { // from class: com.amazon.mp3.find.view.FindFilterDialogFragment.createContextMenuProvider.2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PageType invoke() {
                return FindFilterDialogFragment.this.getViewModel().getUiPageType();
            }
        });
    }

    private final RowButtonOnClickProvider createRowButtonOnClickProvider(FragmentActivity activity) {
        BrushViewModelWrapper<FilterViewModel> brushViewModelWrapper = this.mViewModelWrapper;
        if (brushViewModelWrapper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModelWrapper");
            brushViewModelWrapper = null;
        }
        return new DefaultAddToLibraryProvider(activity, brushViewModelWrapper.getMLibraryStateProvider(), null, 4, null);
    }

    private final void handlePageRefresh() {
        BrushViewModelWrapper<FilterViewModel> brushViewModelWrapper = this.mViewModelWrapper;
        if (brushViewModelWrapper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModelWrapper");
            brushViewModelWrapper = null;
        }
        brushViewModelWrapper.getPageData().observe(getViewLifecycleOwner(), new Observer() { // from class: com.amazon.mp3.find.view.FindFilterDialogFragment$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FindFilterDialogFragment.m1017handlePageRefresh$lambda4(this.f$0, (PageGridViewModel) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handlePageRefresh$lambda-4, reason: not valid java name */
    public static final void m1017handlePageRefresh$lambda4(FindFilterDialogFragment this$0, PageGridViewModel pageGridViewModel) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        boolean z = false;
        if (pageGridViewModel != null && pageGridViewModel.getPageIndex() == 0) {
            z = true;
        }
        this$0.updatePageModel(pageGridViewModel, z);
    }

    private final void updatePageModel(PageGridViewModel pageGridViewModel, boolean isInitialPage) {
        if (pageGridViewModel == null || !hasValidBrushData(pageGridViewModel)) {
            showEmptyView();
        } else {
            setupBrushViews(pageGridViewModel, isInitialPage);
        }
    }

    private final boolean hasValidBrushData(PageGridViewModel pageModel) {
        List<BaseViewModel> models;
        if (pageModel == null || (models = pageModel.getModels()) == null) {
            return false;
        }
        return !models.isEmpty();
    }

    private final void showEmptyView() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mRecyclerView");
            recyclerView = null;
        }
        recyclerView.setVisibility(8);
    }

    private final void setupBrushViews(PageGridViewModel pageGridViewModel, boolean isInitialPage) {
        RecyclerView recyclerView;
        ConstraintLayout constraintLayout;
        if (this.mStyleSheet == null) {
            return;
        }
        List<BaseViewModel> models = pageGridViewModel.getModels();
        BrushViews brushViews = this.mBrushViews;
        if (brushViews != null && !isInitialPage) {
            if (brushViews == null) {
                return;
            }
            brushViews.updateAdapter(models);
            return;
        }
        BrushViews brushViewsCreateBrushViews = createBrushViews(pageGridViewModel);
        UserSubscription userSubscription = UserSubscriptionUtil.getUserSubscription();
        Intrinsics.checkNotNullExpressionValue(userSubscription, "getUserSubscription()");
        int tileType = pageGridViewModel.getTileType();
        RecyclerView recyclerView2 = this.mRecyclerView;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mRecyclerView");
            recyclerView = null;
        } else {
            recyclerView = recyclerView2;
        }
        ConstraintLayout constraintLayout2 = this.mConstraintLayout;
        if (constraintLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mConstraintLayout");
            constraintLayout = null;
        } else {
            constraintLayout = constraintLayout2;
        }
        BrushViews.createRootView$default(brushViewsCreateBrushViews, userSubscription, models, tileType, recyclerView, constraintLayout, null, 32, null);
        this.mBrushViews = brushViewsCreateBrushViews;
    }

    private final BrushViews createBrushViews(PageGridViewModel pageGridViewModel) {
        RecyclerView recyclerView;
        ContentViewManager contentViewManager;
        PageType uiPageType = getViewModel().getUiPageType();
        if (uiPageType == null) {
            uiPageType = PageType.UNKNOWN;
        }
        this.mMetadataClickListener = new SearchMetadataClickListener(this, uiPageType);
        StyleSheet styleSheet = this.mStyleSheet;
        RecyclerView recyclerView2 = this.mRecyclerView;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mRecyclerView");
            recyclerView = null;
        } else {
            recyclerView = recyclerView2;
        }
        BrushContextMenuProvider brushContextMenuProvider = this.mContextMenuProvider;
        FindFilterDialogFragment$mSeeMoreProvider$1 findFilterDialogFragment$mSeeMoreProvider$1 = this.mSeeMoreProvider;
        SearchMetadataClickListener searchMetadataClickListener = this.mMetadataClickListener;
        FindFilterDialogFragment$mUriClickProvider$1 findFilterDialogFragment$mUriClickProvider$1 = this.mUriClickProvider;
        FindFilterDialogFragment$mSpellCorrectionClickProvider$1 findFilterDialogFragment$mSpellCorrectionClickProvider$1 = this.mSpellCorrectionClickProvider;
        FindFilterDialogFragment$mStateChangeProvider$1 findFilterDialogFragment$mStateChangeProvider$1 = this.mStateChangeProvider;
        FindFilterDialogFragment$mEndlessScrollDataProvider$1 findFilterDialogFragment$mEndlessScrollDataProvider$1 = this.mEndlessScrollDataProvider;
        BrushViewModelWrapper<FilterViewModel> brushViewModelWrapper = this.mViewModelWrapper;
        if (brushViewModelWrapper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mViewModelWrapper");
            brushViewModelWrapper = null;
        }
        LibraryStateProvider mLibraryStateProvider = brushViewModelWrapper.getMLibraryStateProvider();
        ContentViewManager contentViewManager2 = this.mContentViewManager;
        if (contentViewManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContentViewManager");
            contentViewManager = null;
        } else {
            contentViewManager = contentViewManager2;
        }
        BrushViews brushViewsCreateBrowseViewsV2 = BrowseViewsFactory.createBrowseViewsV2(this, styleSheet, recyclerView, brushContextMenuProvider, findFilterDialogFragment$mSeeMoreProvider$1, searchMetadataClickListener, findFilterDialogFragment$mUriClickProvider$1, findFilterDialogFragment$mSpellCorrectionClickProvider$1, findFilterDialogFragment$mStateChangeProvider$1, findFilterDialogFragment$mEndlessScrollDataProvider$1, mLibraryStateProvider, contentViewManager, null, null, this.mRowButtonOnClickProvider, null, null, null, null, null);
        Intrinsics.checkNotNullExpressionValue(brushViewsCreateBrowseViewsV2, "createBrowseViewsV2(\n   …           null\n        )");
        return brushViewsCreateBrowseViewsV2;
    }

    private final void handlePageVisibilityChange() {
        getViewModel().isFilterDialogVisible().observe(getViewLifecycleOwner(), new Observer() { // from class: com.amazon.mp3.find.view.FindFilterDialogFragment$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FindFilterDialogFragment.m1018handlePageVisibilityChange$lambda8(this.f$0, (Boolean) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handlePageVisibilityChange$lambda-8, reason: not valid java name */
    public static final void m1018handlePageVisibilityChange$lambda8(FindFilterDialogFragment this$0, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (bool == null || bool.booleanValue()) {
            return;
        }
        this$0.dismiss();
    }

    private final void setupDismissButtonStyle(BaseButton dismissButton, StyleSheet styleSheet) {
        Resources resources;
        Drawable drawable;
        BaseButton.StyleBuilder iconBuilder;
        BaseButton.StyleBuilder styleBuilderWithIcon;
        Context context = getContext();
        if (context == null || (resources = context.getResources()) == null || (drawable = resources.getDrawable(R.drawable.ic_action_cancel, null)) == null || (iconBuilder = styleSheet.getIconBuilder(IconSizeKey.MEDIUM, IconStyleKey.GLASS)) == null || (styleBuilderWithIcon = iconBuilder.withIcon(drawable)) == null) {
            return;
        }
        styleBuilderWithIcon.applyStyle(dismissButton);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        BaseButton baseButton = this.mDismissButton;
        if (baseButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDismissButton");
            baseButton = null;
        }
        baseButton.setOnClickListener(null);
        this.mStyleSheet = null;
        super.onDestroy();
    }
}
