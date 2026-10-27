package com.amazon.mp3.find.view;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import com.amazon.mp3.actionbar.view.BauhausActionBarView;
import com.amazon.mp3.actionbar.view.BauhausFilterActionBarView;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.activity.NavigationActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.catalog.fragment.BrowseViewsFactory;
import com.amazon.mp3.find.skyfire.FindSkyfireFactory;
import com.amazon.mp3.find.util.HostViewModelWrapper;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.music.find.service.ExecutionContext;
import com.amazon.music.find.skyfire.common.Refreshable;
import com.amazon.music.find.viewmodels.BaseHostViewModel;
import com.amazon.music.find.viewmodels.ChildFragmentType;
import com.amazon.music.find.viewmodels.FindPageGridViewModelContext;
import com.amazon.music.find.viewmodels.SearchHostViewData;
import com.amazon.music.views.library.models.PageGridViewModelContext;
import com.amazon.music.views.library.styles.StyleSheet;
import com.squareup.picasso.Picasso;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: BaseHostFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b%\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\b&\u0018\u0000 x*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0003:\u0001xB\u0007¢\u0006\u0004\bv\u0010wJ\u0012\u0010\u0007\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0016J&\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010\t\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0016J\u0012\u0010\u000e\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0016J\b\u0010\u000f\u001a\u00020\u0006H\u0004J$\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u00122\b\u0010\u0014\u001a\u0004\u0018\u00010\u0012H\u0014J\u001e\u0010\u0018\u001a\u0004\u0018\u00010\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010\u0017\u001a\u0004\u0018\u00010\u0004H\u0004J\u0019\u0010\u0019\u001a\u00028\u00002\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H$¢\u0006\u0004\b\u0019\u0010\u001aJ\b\u0010\u001b\u001a\u00020\u0006H\u0014J\b\u0010\u001c\u001a\u00020\u0006H\u0016J\b\u0010\u001d\u001a\u00020\u0006H\u0016J\b\u0010\u001e\u001a\u00020\u0006H\u0016J\b\u0010\u001f\u001a\u00020\u0006H\u0016J\b\u0010 \u001a\u00020\u0006H\u0016J\u0012\u0010#\u001a\u00020\u00062\b\u0010\"\u001a\u0004\u0018\u00010!H\u0004J\u0010\u0010&\u001a\u00020\u00062\u0006\u0010%\u001a\u00020$H\u0014J\b\u0010'\u001a\u00020\u0006H\u0004J\b\u0010(\u001a\u00020\u0006H\u0004J\b\u0010*\u001a\u00020)H\u0014J\b\u0010+\u001a\u00020\u0006H\u0004J\b\u0010,\u001a\u00020\u0006H\u0004J\u0012\u0010/\u001a\u00020\u00062\b\u0010.\u001a\u0004\u0018\u00010-H\u0004J\u0012\u00102\u001a\u00020\u00062\b\u00101\u001a\u0004\u0018\u000100H\u0004J\u0012\u00103\u001a\u00020\u00062\b\u00101\u001a\u0004\u0018\u000100H\u0014J\u0012\u00104\u001a\u00020\u00062\b\u00101\u001a\u0004\u0018\u000100H\u0014R\"\u00105\u001a\u00020\f8\u0004@\u0004X\u0084.¢\u0006\u0012\n\u0004\b5\u00106\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\"\u0010;\u001a\u00020\f8\u0004@\u0004X\u0084.¢\u0006\u0012\n\u0004\b;\u00106\u001a\u0004\b<\u00108\"\u0004\b=\u0010:R\"\u0010>\u001a\u00020\f8\u0004@\u0004X\u0084.¢\u0006\u0012\n\u0004\b>\u00106\u001a\u0004\b?\u00108\"\u0004\b@\u0010:R$\u0010A\u001a\u0004\u0018\u00010\u00158\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\bA\u0010B\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR\"\u0010G\u001a\u00020\n8\u0004@\u0004X\u0084.¢\u0006\u0012\n\u0004\bG\u0010H\u001a\u0004\bI\u0010J\"\u0004\bK\u0010LR$\u0010M\u001a\u0004\u0018\u00010\u00128\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\bM\u0010N\u001a\u0004\bO\u0010P\"\u0004\bQ\u0010RR$\u0010S\u001a\u0004\u0018\u00010\u00128\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\bS\u0010N\u001a\u0004\bT\u0010P\"\u0004\bU\u0010RR\"\u0010W\u001a\u00020V8\u0004@\u0004X\u0084.¢\u0006\u0012\n\u0004\bW\u0010X\u001a\u0004\bY\u0010Z\"\u0004\b[\u0010\\R\"\u0010]\u001a\u00020\n8\u0004@\u0004X\u0084.¢\u0006\u0012\n\u0004\b]\u0010H\u001a\u0004\b^\u0010J\"\u0004\b_\u0010LR$\u0010a\u001a\u0004\u0018\u00010`8\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\ba\u0010b\u001a\u0004\bc\u0010d\"\u0004\be\u0010fR\u001a\u0010h\u001a\u00020g8\u0004X\u0084\u0004¢\u0006\f\n\u0004\bh\u0010i\u001a\u0004\bj\u0010kR(\u0010m\u001a\b\u0012\u0004\u0012\u00028\u00000l8\u0004@\u0004X\u0084.¢\u0006\u0012\n\u0004\bm\u0010n\u001a\u0004\bo\u0010p\"\u0004\bq\u0010rR\u0014\u0010u\u001a\u00028\u00008DX\u0084\u0004¢\u0006\u0006\u001a\u0004\bs\u0010t¨\u0006y"}, d2 = {"Lcom/amazon/mp3/find/view/BaseHostFragment;", "Lcom/amazon/music/find/viewmodels/BaseHostViewModel;", "T", "Landroidx/fragment/app/Fragment;", "Landroid/os/Bundle;", "savedInstanceState", "", "onCreate", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "Landroid/view/View;", "onCreateView", "onActivityCreated", "initDefaultViews", "Landroidx/fragment/app/FragmentActivity;", "fragmentActivity", "", "title", "subTitle", "Lcom/amazon/mp3/actionbar/view/BauhausActionBarView;", "createActionBarView", "bundle", "getStandaloneTitle", "createViewModel", "(Landroid/os/Bundle;)Lcom/amazon/music/find/viewmodels/BaseHostViewModel;", "handlePageRefresh", "onPause", "onResume", "onDestroy", "onDestroyView", "onStop", "Lcom/amazon/music/find/viewmodels/SearchHostViewData;", "searchHostViewData", "updatePageModel", "Lcom/amazon/mp3/actionbar/view/BauhausFilterActionBarView;", "actionBar", "setupActionBarPills", "showEmptyView", "hideEmptyView", "", "shouldErrorLayoutBeVisible", "stopImageRequests", "stopViewModelWrapper", "Lio/reactivex/rxjava3/disposables/Disposable;", "disposable", "addDisposable", "Lcom/amazon/music/find/viewmodels/FindPageGridViewModelContext;", "pageGridViewModelContext", "markRenderingStarted", "afterRenderingCompleted", "notifyUpdatePageModelCompleted", "mLoadingView", "Landroid/view/View;", "getMLoadingView", "()Landroid/view/View;", "setMLoadingView", "(Landroid/view/View;)V", "mEmptyLayout", "getMEmptyLayout", "setMEmptyLayout", "mPlaceholderLayout", "getMPlaceholderLayout", "setMPlaceholderLayout", "mActionBarView", "Lcom/amazon/mp3/actionbar/view/BauhausActionBarView;", "getMActionBarView", "()Lcom/amazon/mp3/actionbar/view/BauhausActionBarView;", "setMActionBarView", "(Lcom/amazon/mp3/actionbar/view/BauhausActionBarView;)V", "root", "Landroid/view/ViewGroup;", "getRoot", "()Landroid/view/ViewGroup;", "setRoot", "(Landroid/view/ViewGroup;)V", "mToolbarTitle", "Ljava/lang/String;", "getMToolbarTitle", "()Ljava/lang/String;", "setMToolbarTitle", "(Ljava/lang/String;)V", "mStandaloneTitle", "getMStandaloneTitle", "setMStandaloneTitle", "Landroid/widget/ImageView;", "mBackgroundArtworkLayout", "Landroid/widget/ImageView;", "getMBackgroundArtworkLayout", "()Landroid/widget/ImageView;", "setMBackgroundArtworkLayout", "(Landroid/widget/ImageView;)V", "mBackgroundArtworkOverlayLayout", "getMBackgroundArtworkOverlayLayout", "setMBackgroundArtworkOverlayLayout", "Lcom/amazon/music/views/library/styles/StyleSheet;", "mStyleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getMStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "setMStyleSheet", "(Lcom/amazon/music/views/library/styles/StyleSheet;)V", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "mCompositeDisposable", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "getMCompositeDisposable", "()Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "Lcom/amazon/mp3/find/util/HostViewModelWrapper;", "mViewModelWrapper", "Lcom/amazon/mp3/find/util/HostViewModelWrapper;", "getMViewModelWrapper", "()Lcom/amazon/mp3/find/util/HostViewModelWrapper;", "setMViewModelWrapper", "(Lcom/amazon/mp3/find/util/HostViewModelWrapper;)V", "getViewModel", "()Lcom/amazon/music/find/viewmodels/BaseHostViewModel;", "viewModel", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public abstract class BaseHostFragment<T extends BaseHostViewModel> extends Fragment {
    private static final Logger logger = LoggerFactory.getLogger(BaseHostFragment.class.getName());
    private BauhausActionBarView mActionBarView;
    protected ImageView mBackgroundArtworkLayout;
    protected ViewGroup mBackgroundArtworkOverlayLayout;
    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    protected View mEmptyLayout;
    protected View mLoadingView;
    protected View mPlaceholderLayout;
    private String mStandaloneTitle;
    private StyleSheet mStyleSheet;
    private String mToolbarTitle;
    protected HostViewModelWrapper<T> mViewModelWrapper;
    protected ViewGroup root;

    /* JADX INFO: compiled from: BaseHostFragment.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ChildFragmentType.values().length];
            iArr[ChildFragmentType.Skyfire.ordinal()] = 1;
            iArr[ChildFragmentType.Native.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    protected void afterRenderingCompleted(FindPageGridViewModelContext pageGridViewModelContext) {
    }

    protected abstract T createViewModel(Bundle savedInstanceState);

    protected void setupActionBarPills(BauhausFilterActionBarView actionBar) {
        Intrinsics.checkNotNullParameter(actionBar, "actionBar");
    }

    protected boolean shouldErrorLayoutBeVisible() {
        return true;
    }

    protected final View getMLoadingView() {
        View view = this.mLoadingView;
        if (view != null) {
            return view;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mLoadingView");
        return null;
    }

    protected final void setMLoadingView(View view) {
        Intrinsics.checkNotNullParameter(view, "<set-?>");
        this.mLoadingView = view;
    }

    protected final View getMEmptyLayout() {
        View view = this.mEmptyLayout;
        if (view != null) {
            return view;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mEmptyLayout");
        return null;
    }

    protected final void setMEmptyLayout(View view) {
        Intrinsics.checkNotNullParameter(view, "<set-?>");
        this.mEmptyLayout = view;
    }

    protected final View getMPlaceholderLayout() {
        View view = this.mPlaceholderLayout;
        if (view != null) {
            return view;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mPlaceholderLayout");
        return null;
    }

    protected final void setMPlaceholderLayout(View view) {
        Intrinsics.checkNotNullParameter(view, "<set-?>");
        this.mPlaceholderLayout = view;
    }

    protected final BauhausActionBarView getMActionBarView() {
        return this.mActionBarView;
    }

    protected final void setMActionBarView(BauhausActionBarView bauhausActionBarView) {
        this.mActionBarView = bauhausActionBarView;
    }

    protected final ViewGroup getRoot() {
        ViewGroup viewGroup = this.root;
        if (viewGroup != null) {
            return viewGroup;
        }
        Intrinsics.throwUninitializedPropertyAccessException("root");
        return null;
    }

    protected final void setRoot(ViewGroup viewGroup) {
        Intrinsics.checkNotNullParameter(viewGroup, "<set-?>");
        this.root = viewGroup;
    }

    protected final String getMToolbarTitle() {
        return this.mToolbarTitle;
    }

    protected final String getMStandaloneTitle() {
        return this.mStandaloneTitle;
    }

    protected final void setMBackgroundArtworkLayout(ImageView imageView) {
        Intrinsics.checkNotNullParameter(imageView, "<set-?>");
        this.mBackgroundArtworkLayout = imageView;
    }

    protected final void setMBackgroundArtworkOverlayLayout(ViewGroup viewGroup) {
        Intrinsics.checkNotNullParameter(viewGroup, "<set-?>");
        this.mBackgroundArtworkOverlayLayout = viewGroup;
    }

    protected final StyleSheet getMStyleSheet() {
        return this.mStyleSheet;
    }

    protected final CompositeDisposable getMCompositeDisposable() {
        return this.mCompositeDisposable;
    }

    protected final HostViewModelWrapper<T> getMViewModelWrapper() {
        HostViewModelWrapper<T> hostViewModelWrapper = this.mViewModelWrapper;
        if (hostViewModelWrapper != null) {
            return hostViewModelWrapper;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mViewModelWrapper");
        return null;
    }

    protected final void setMViewModelWrapper(HostViewModelWrapper<T> hostViewModelWrapper) {
        Intrinsics.checkNotNullParameter(hostViewModelWrapper, "<set-?>");
        this.mViewModelWrapper = hostViewModelWrapper;
    }

    protected final T getViewModel() {
        return (T) getMViewModelWrapper().getViewModel();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        logger.debug("SPL::onCreate");
        super.onCreate(savedInstanceState);
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        setMViewModelWrapper(new HostViewModelWrapper<>(contextRequireContext, createViewModel(savedInstanceState)));
        StyleSheetProvider.getStyleSheet().observe(this, new Observer() { // from class: com.amazon.mp3.find.view.BaseHostFragment$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                BaseHostFragment.m1010onCreate$lambda0(this.f$0, (StyleSheet) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-0, reason: not valid java name */
    public static final void m1010onCreate$lambda0(BaseHostFragment this$0, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        logger.debug("SPL::styleSheet loaded");
        this$0.mStyleSheet = styleSheet;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        logger.debug("SPL::onCreateView");
        View viewInflate = inflater.inflate(R.layout.search_host, container, false);
        if (viewInflate == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup");
        }
        setRoot((ViewGroup) viewInflate);
        ViewGroup root = getRoot();
        View viewFindViewById = root.findViewById(R.id.loading_spinner);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "it.findViewById(R.id.loading_spinner)");
        setMLoadingView(viewFindViewById);
        getMLoadingView().setVisibility(8);
        View viewFindViewById2 = root.findViewById(R.id.empty_layout);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "it.findViewById(R.id.empty_layout)");
        setMEmptyLayout(viewFindViewById2);
        getMEmptyLayout().setVisibility(8);
        View viewFindViewById3 = root.findViewById(R.id.placeholder_container);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "it.findViewById(R.id.placeholder_container)");
        setMPlaceholderLayout(viewFindViewById3);
        getMPlaceholderLayout().setVisibility(8);
        View viewFindViewById4 = root.findViewById(R.id.artwork);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "it.findViewById(R.id.artwork)");
        setMBackgroundArtworkLayout((ImageView) viewFindViewById4);
        View viewFindViewById5 = root.findViewById(R.id.artworkOverlayLayout);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "it.findViewById(R.id.artworkOverlayLayout)");
        setMBackgroundArtworkOverlayLayout((ViewGroup) viewFindViewById5);
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        logger.debug("SPL::onActivityCreated");
        super.onActivityCreated(savedInstanceState);
        initDefaultViews();
        handlePageRefresh();
    }

    protected final void initDefaultViews() {
        this.mStandaloneTitle = getStandaloneTitle(getActivity(), getArguments());
        FragmentActivity activity = getActivity();
        MusicHomeActivity musicHomeActivity = activity instanceof MusicHomeActivity ? (MusicHomeActivity) activity : null;
        if (musicHomeActivity == null) {
            return;
        }
        String mStandaloneTitle = getMStandaloneTitle();
        if (mStandaloneTitle == null) {
            mStandaloneTitle = getMToolbarTitle();
        }
        setMActionBarView(createActionBarView(musicHomeActivity, mStandaloneTitle, null));
        musicHomeActivity.setHeaderView(getMActionBarView());
        musicHomeActivity.restoreHomeButton();
    }

    protected BauhausActionBarView createActionBarView(FragmentActivity fragmentActivity, String title, String subTitle) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "fragmentActivity");
        return new BauhausActionBarView(fragmentActivity, title, subTitle);
    }

    protected final String getStandaloneTitle(FragmentActivity fragmentActivity, Bundle bundle) {
        int i;
        Resources resources;
        if (bundle == null || !bundle.containsKey("com.amazon.mp3.STANDALONE_FRAGMENT_KEY") || (i = bundle.getInt("com.amazon.mp3.STANDALONE_FRAGMENT_TITLE_KEY", 0)) == 0 || !(fragmentActivity instanceof NavigationActivity) || (resources = ((NavigationActivity) fragmentActivity).getResources()) == null) {
            return null;
        }
        return resources.getString(i);
    }

    protected void handlePageRefresh() {
        getMViewModelWrapper().getPageData().observe(getViewLifecycleOwner(), new Observer() { // from class: com.amazon.mp3.find.view.BaseHostFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                BaseHostFragment.m1009handlePageRefresh$lambda4(this.f$0, (SearchHostViewData) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handlePageRefresh$lambda-4, reason: not valid java name */
    public static final void m1009handlePageRefresh$lambda4(BaseHostFragment this$0, SearchHostViewData searchHostViewData) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.updatePageModel(searchHostViewData);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        logger.debug("SPL::onPause");
        super.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        logger.debug("SPL::onResume");
        super.onResume();
        getMViewModelWrapper().onResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        Logger logger2 = logger;
        logger2.debug("SPL::onDestroy");
        FragmentActivity activity = getActivity();
        MusicHomeActivity musicHomeActivity = activity instanceof MusicHomeActivity ? (MusicHomeActivity) activity : null;
        if (musicHomeActivity != null) {
            musicHomeActivity.removeHeaderView();
        }
        BrowseViewsFactory.removeRegisteredSholverImageLoader(getContext());
        if (!this.mCompositeDisposable.isDisposed()) {
            logger2.debug("SPL::onDestroy. Disposing composite disposable...");
            this.mCompositeDisposable.dispose();
        }
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        logger.debug("SPL::onDestroyView");
        stopImageRequests();
        stopViewModelWrapper();
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        logger.debug("SPL::onStop. Cleaning composite disposable...");
        this.mCompositeDisposable.clear();
        super.onStop();
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected final void updatePageModel(SearchHostViewData searchHostViewData) {
        String fragmentName;
        Logger logger2 = logger;
        Fragment fragmentCreateSearchPage = null;
        logger2.debug(Intrinsics.stringPlus("SPL::updatePageModel. searchHostViewData=", searchHostViewData == null ? null : Integer.valueOf(searchHostViewData.hashCode())));
        if (searchHostViewData == null) {
            showEmptyView();
            afterRenderingCompleted(null);
            notifyUpdatePageModelCompleted(null);
            return;
        }
        PageGridViewModelContext context = searchHostViewData.getContext();
        FindPageGridViewModelContext findPageGridViewModelContext = context instanceof FindPageGridViewModelContext ? (FindPageGridViewModelContext) context : null;
        markRenderingStarted(findPageGridViewModelContext);
        String title = searchHostViewData.getTitle();
        this.mToolbarTitle = title;
        BauhausActionBarView bauhausActionBarView = this.mActionBarView;
        if (bauhausActionBarView != null) {
            String str = this.mStandaloneTitle;
            if (str != null) {
                title = str;
            }
            bauhausActionBarView.setTitles(title, searchHostViewData.getSubTitle());
        }
        BauhausActionBarView bauhausActionBarView2 = this.mActionBarView;
        if (bauhausActionBarView2 instanceof BauhausFilterActionBarView) {
            if (bauhausActionBarView2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.actionbar.view.BauhausFilterActionBarView");
            }
            setupActionBarPills((BauhausFilterActionBarView) bauhausActionBarView2);
        }
        ChildFragmentType fragmentType = searchHostViewData.getFragmentType();
        int[] iArr = WhenMappings.$EnumSwitchMapping$0;
        int i = iArr[fragmentType.ordinal()];
        if (i != 1) {
            fragmentName = i != 2 ? null : searchHostViewData.getFragmentName();
        } else {
            fragmentName = "Skyfire";
        }
        Fragment fragmentFindFragmentByTag = getChildFragmentManager().findFragmentByTag(fragmentName);
        if (fragmentFindFragmentByTag == null) {
            logger2.debug(Intrinsics.stringPlus("SPL::updatePageModel. mounting new fragment ", fragmentName));
            int i2 = iArr[searchHostViewData.getFragmentType().ordinal()];
            if (i2 == 1) {
                FindSkyfireFactory findSkyfireFactory = FindSkyfireFactory.INSTANCE;
                String skillPath = searchHostViewData.getSkillPath();
                Intrinsics.checkNotNull(skillPath);
                fragmentCreateSearchPage = findSkyfireFactory.createSearchPage(skillPath, searchHostViewData.getQueryParams(), searchHostViewData.getExtras());
            } else if (i2 == 2) {
                fragmentCreateSearchPage = new Fragment();
            }
            if (fragmentCreateSearchPage != null) {
                hideEmptyView();
                FragmentTransaction fragmentTransactionBeginTransaction = getChildFragmentManager().beginTransaction();
                fragmentTransactionBeginTransaction.replace(R.id.placeholder_container, fragmentCreateSearchPage, fragmentName);
                fragmentTransactionBeginTransaction.commitNow();
            }
        } else {
            logger2.debug("SPL::updatePageModel. refreshing fragment");
            Refreshable refreshable = fragmentFindFragmentByTag instanceof Refreshable ? (Refreshable) fragmentFindFragmentByTag : null;
            if (refreshable != null) {
                refreshable.refresh(searchHostViewData.getSkillPath(), searchHostViewData.getQueryParams());
            }
        }
        afterRenderingCompleted(findPageGridViewModelContext);
        notifyUpdatePageModelCompleted(findPageGridViewModelContext);
    }

    protected final void showEmptyView() {
        logger.debug("SPL::showEmptyView");
        getMLoadingView().setVisibility(8);
        if (shouldErrorLayoutBeVisible()) {
            getMEmptyLayout().setVisibility(0);
        } else {
            getMEmptyLayout().setVisibility(8);
            getMPlaceholderLayout().setVisibility(8);
        }
    }

    protected final void hideEmptyView() {
        logger.debug("SPL::hideEmptyView");
        getMLoadingView().setVisibility(8);
        getMEmptyLayout().setVisibility(8);
        getMPlaceholderLayout().setVisibility(0);
    }

    protected final void stopImageRequests() {
        Context context = getContext();
        if (context == null) {
            return;
        }
        Picasso.get().cancelTag(context);
    }

    protected final void stopViewModelWrapper() {
        HostViewModelWrapper<T> mViewModelWrapper = getMViewModelWrapper();
        if (mViewModelWrapper == null) {
            return;
        }
        mViewModelWrapper.onCleared();
    }

    protected final void addDisposable(Disposable disposable) {
        if (disposable == null) {
            return;
        }
        getMCompositeDisposable().add(disposable);
    }

    protected final void markRenderingStarted(FindPageGridViewModelContext pageGridViewModelContext) {
        ExecutionContext executionContext;
        if (pageGridViewModelContext == null || (executionContext = pageGridViewModelContext.getExecutionContext()) == null) {
            return;
        }
        executionContext.markRenderingStarted();
    }

    protected void notifyUpdatePageModelCompleted(FindPageGridViewModelContext pageGridViewModelContext) {
        if (pageGridViewModelContext == null) {
            return;
        }
        getViewModel().completeOperation(pageGridViewModelContext.getExecutionContext());
    }
}
