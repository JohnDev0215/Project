package com.amazon.mp3.catalog.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.actionbar.view.ActionBarView;
import com.amazon.mp3.actionbar.view.BauhausActionBarView;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.brush.BrushViews;
import com.amazon.mp3.feed.FeedButton;
import com.amazon.mp3.find.util.AlexaUtil;
import com.amazon.mp3.find.view.AlexaTooltipExperience;
import com.amazon.mp3.find.view.SearchTabFragmentHost;
import com.amazon.mp3.library.provider.LibraryStateProvider;
import com.amazon.mp3.providers.DefaultContextMenuProvider;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PlatformUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.find.view.FindSearchBarView;
import com.amazon.music.find.viewmodels.SearchExperienceMode;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.subscription.UserSubscription;
import com.amazon.music.views.library.models.LinkNavigatorModel;
import com.amazon.music.views.library.models.SectionGridViewModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.enums.TargetType;
import com.amazon.music.views.library.providers.BrushUriClickListenerProvider;
import com.amazon.music.views.library.providers.EndlessScrollDataProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.views.presentation.ActionBarDecorator;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: FindBrushFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000y\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b*\u0001\f\u0018\u0000 72\u00020\u0001:\u00017B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\n\u0010\u0010\u001a\u0004\u0018\u00010\u0004H\u0002J\b\u0010\u0011\u001a\u00020\u000fH\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0014J\b\u0010\u0014\u001a\u00020\u0015H\u0014J\u0010\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u000fH\u0014J\b\u0010\u001a\u001a\u00020\u000fH\u0014J\b\u0010\u001b\u001a\u00020\u000fH\u0014J\u0012\u0010\u001c\u001a\u00020\u000f2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J&\u0010\u001f\u001a\u0004\u0018\u00010\u00042\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\b\u0010$\u001a\u00020\u000fH\u0016J\b\u0010%\u001a\u00020\u000fH\u0016J\b\u0010&\u001a\u00020\u000fH\u0014J\u0016\u0010'\u001a\u00020\u000f2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)H\u0002J\b\u0010+\u001a\u00020\u000fH\u0014J\u0012\u0010,\u001a\u00020\u000f2\b\u0010-\u001a\u0004\u0018\u00010.H\u0016J\b\u0010/\u001a\u000200H\u0014J\b\u00101\u001a\u000200H\u0014J\b\u00102\u001a\u00020\u000fH\u0002J\b\u00103\u001a\u00020\u000fH\u0002J\u0010\u00104\u001a\u00020\u000f2\u0006\u00105\u001a\u000200H\u0002J\b\u00106\u001a\u00020\u000fH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\r¨\u00068"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/FindBrushFragment;", "Lcom/amazon/mp3/catalog/fragment/BrushFragment;", "()V", "actionBarView", "Landroid/view/View;", "findSearchBarView", "Lcom/amazon/music/find/view/FindSearchBarView;", "mAlexaTooltipExperience", "Lcom/amazon/mp3/find/view/AlexaTooltipExperience;", "mFeedButton", "Lcom/amazon/mp3/feed/FeedButton;", "mFindUriClickProvider", "com/amazon/mp3/catalog/fragment/FindBrushFragment$mFindUriClickProvider$1", "Lcom/amazon/mp3/catalog/fragment/FindBrushFragment$mFindUriClickProvider$1;", "connectActionBarView", "", "createActionBarView", "disconnectActionBarView", "getIdentifier", "", "getPageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "goSearch", "mode", "Lcom/amazon/music/find/viewmodels/SearchExperienceMode;", "handlePageModelUpdate", "initActionBarView", "initDefaultViews", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyView", "onResume", "setBottomBarTab", "setPodcastNewBadgeIfNecessary", "models", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "setupBrushViews", "setupTouchListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Landroid/view/View$OnTouchListener;", "shouldErrorLayoutBeVisible", "", "shouldSwipeBeEnabled", "styleActionBar", "styleSearchBarViewMargins", "updateKeyboardVisibility", "isVisible", "updateQueryHint", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FindBrushFragment extends BrushFragment {
    private static final String TAG = FindBrushFragment.class.getSimpleName();
    private static final Logger logger = LoggerFactory.getLogger(FindBrushFragment.class.getName());
    private FindSearchBarView findSearchBarView;
    private AlexaTooltipExperience mAlexaTooltipExperience;
    private FeedButton mFeedButton;
    private View actionBarView = this.mActionBarView;
    private final FindBrushFragment$mFindUriClickProvider$1 mFindUriClickProvider = new BrushUriClickListenerProvider() { // from class: com.amazon.mp3.catalog.fragment.FindBrushFragment$mFindUriClickProvider$1

        /* JADX INFO: compiled from: FindBrushFragment.kt */
        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[TargetType.values().length];
                iArr[TargetType.DEEPLINK.ordinal()] = 1;
                iArr[TargetType.URI.ordinal()] = 2;
                $EnumSwitchMapping$0 = iArr;
            }
        }

        @Override // com.amazon.music.views.library.providers.BrushUriClickListenerProvider
        public void onClick(String target, String viewId, TargetType targetType) {
            int i = targetType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[targetType.ordinal()];
            if (i == -1) {
                try {
                    this.this$0.handleDeepLink(target);
                } catch (Exception unused) {
                    this.this$0.handleBrushUri(target, null, null, null);
                }
                setDefaultEvent(target, viewId);
            } else if (i == 1) {
                try {
                    this.this$0.handleDeepLink(target);
                } catch (Exception unused2) {
                    Log.error(FindBrushFragment.TAG, Intrinsics.stringPlus("Unable to handle onClick DEEPLINK target: ", target));
                }
                setDefaultEvent(target, viewId);
            } else {
                if (i != 2) {
                    return;
                }
                this.this$0.handleBrushUri(target, null, null, null);
                setDefaultEvent(target, viewId);
            }
        }

        public final void setDefaultEvent(String target, String viewId) {
            MetricsHolder.getManager().handleEvent(UiClickEvent.builder(target, viewId).withInteractionType(InteractionType.TAP).withEventTime(System.currentTimeMillis()).build());
        }
    };

    @Override // com.amazon.mp3.catalog.fragment.BrushFragment
    protected boolean shouldSwipeBeEnabled() {
        return false;
    }

    @Override // com.amazon.mp3.catalog.fragment.BrushFragment, com.amazon.mp3.prime.browse.PrimeMusicPagerFragment
    public void setupTouchListener(View.OnTouchListener listener) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.setOnTouchListener(new View.OnTouchListener() { // from class: com.amazon.mp3.catalog.fragment.FindBrushFragment$$ExternalSyntheticLambda4
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    return FindBrushFragment.m486setupTouchListener$lambda0(this.f$0, view, motionEvent);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupTouchListener$lambda-0, reason: not valid java name */
    public static final boolean m486setupTouchListener$lambda0(FindBrushFragment this$0, View view, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.updateKeyboardVisibility(false);
        return false;
    }

    @Override // com.amazon.mp3.catalog.fragment.BrushFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewOnCreateView = super.onCreateView(inflater, container, savedInstanceState);
        if (viewOnCreateView == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup");
        }
        this.root = (ViewGroup) viewOnCreateView;
        this.swipeRefreshLayout.setEnabled(false);
        return this.root;
    }

    @Override // com.amazon.mp3.catalog.fragment.BrushFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        connectActionBarView();
    }

    @Override // com.amazon.mp3.catalog.fragment.BrushFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        this.mRecyclerView.setOnTouchListener(null);
        this.root.setOnTouchListener(null);
        disconnectActionBarView();
        FeedButton feedButton = this.mFeedButton;
        if (feedButton != null) {
            FragmentActivity fragmentActivityRequireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity()");
            feedButton.removeObservers(fragmentActivityRequireActivity);
        }
        this.mFeedButton = null;
        AlexaTooltipExperience alexaTooltipExperience = this.mAlexaTooltipExperience;
        if (alexaTooltipExperience != null) {
            alexaTooltipExperience.onDestroy();
        }
        super.onDestroyView();
    }

    @Override // com.amazon.mp3.catalog.fragment.BrushFragment, androidx.fragment.app.Fragment
    public void onResume() {
        setBottomBarTab();
        styleActionBar();
        super.onResume();
    }

    @Override // com.amazon.mp3.catalog.fragment.BrushFragment
    protected void setupBrushViews() {
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        LibraryStateProvider mLibraryStateProvider = this.mLibraryStateProvider;
        Intrinsics.checkNotNullExpressionValue(mLibraryStateProvider, "mLibraryStateProvider");
        this.mContextMenuProvider = new DefaultContextMenuProvider(fragmentActivityRequireActivity, this, mLibraryStateProvider, null, this.mPageDataModel.getPageStateManager());
        this.brushViews = BrowseViewsFactory.createBrowseViewsV2(this, this.styleSheet, this.mRecyclerView, this.mContextMenuProvider, this.mSeeMoreProvider, this.mFindUriClickProvider, null, null, new EndlessScrollDataProvider() { // from class: com.amazon.mp3.catalog.fragment.FindBrushFragment$setupBrushViews$endlessScrollDataProvider$1
            @Override // com.amazon.music.views.library.providers.EndlessScrollDataProvider
            public void loadMore(int page, int totalItemsCount) {
                this.this$0.mPageDataModel.fetchData(page);
            }
        }, this.mLibraryStateProvider, this.mContentViewManager, getPageType(), this.mPageDataModel.getFollowStateUseCase(), null);
        this.mLoadingView.setVisibility(8);
        List<BaseViewModel> models = this.mPageModel.getModels();
        setPodcastNewBadgeIfNecessary(models);
        BrushViews brushViews = this.brushViews;
        UserSubscription userSubscription = UserSubscriptionUtil.getUserSubscription();
        Intrinsics.checkNotNullExpressionValue(userSubscription, "getUserSubscription()");
        int tileType = this.mPageModel.getTileType();
        RecyclerView mRecyclerView = this.mRecyclerView;
        Intrinsics.checkNotNullExpressionValue(mRecyclerView, "mRecyclerView");
        brushViews.createRootView(userSubscription, models, tileType, mRecyclerView);
        setupLayoutAndScrollPosition();
    }

    @Override // com.amazon.mp3.catalog.fragment.BrushFragment
    protected String getIdentifier() {
        String metricsValue = PageType.BROWSE_FIND.getMetricsValue();
        Intrinsics.checkNotNullExpressionValue(metricsValue, "BROWSE_FIND.metricValue");
        return metricsValue;
    }

    @Override // com.amazon.mp3.catalog.fragment.BrushFragment
    protected void initActionBarView() {
        FragmentActivity activity;
        if (this.actionBarView == null && (activity = getActivity()) != null) {
            FindSearchBarView findSearchBarView = new FindSearchBarView(activity);
            this.findSearchBarView = findSearchBarView;
            findSearchBarView.disableSearchViewKeyboard();
            View viewCreateActionBarView = createActionBarView();
            this.actionBarView = viewCreateActionBarView;
            if (viewCreateActionBarView != null) {
                this.mFeedButton = new FeedButton(viewCreateActionBarView, activity);
                updateQueryHint();
            } else {
                throw new NullPointerException("null cannot be cast to non-null type android.view.View");
            }
        }
        MusicHomeActivity musicHomeActivity = (MusicHomeActivity) getActivity();
        if (musicHomeActivity != null) {
            musicHomeActivity.setHeaderView(this.actionBarView);
            musicHomeActivity.restoreHomeButton();
        }
        KeyEvent.Callback callback = this.actionBarView;
        ActionBarDecorator actionBarDecorator = callback instanceof ActionBarDecorator ? (ActionBarDecorator) callback : null;
        if (actionBarDecorator == null) {
            return;
        }
        actionBarDecorator.setBackButtonVisible(false);
        actionBarDecorator.addTopMargin();
        actionBarDecorator.setFeedButtonVisible(!PlatformUtil.isFireOS());
        if (actionBarDecorator instanceof ActionBarView) {
            actionBarDecorator.setLogoVisible(true);
        }
        if (PlatformUtil.isFireOS()) {
            return;
        }
        actionBarDecorator.setFeedButtonVisible(true);
    }

    @Override // com.amazon.mp3.catalog.fragment.BrushFragment
    protected PageType getPageType() {
        return PageType.BROWSE_FIND;
    }

    @Override // com.amazon.mp3.catalog.fragment.BrushFragment
    protected void initDefaultViews() {
        initActionBarView();
    }

    @Override // com.amazon.mp3.catalog.fragment.BrushFragment
    protected void setBottomBarTab() {
        SearchTabFragmentHost.setBottomBarTab();
    }

    private final void updateKeyboardVisibility(boolean isVisible) {
        FindSearchBarView findSearchBarView;
        if (!isVisible) {
            if (isVisible || (findSearchBarView = this.findSearchBarView) == null) {
                return;
            }
            findSearchBarView.clearFocus();
            return;
        }
        FindSearchBarView findSearchBarView2 = this.findSearchBarView;
        if (findSearchBarView2 != null) {
            findSearchBarView2.requestFocus();
        }
        FindSearchBarView findSearchBarView3 = this.findSearchBarView;
        if (findSearchBarView3 == null) {
            return;
        }
        FindSearchBarView.showKeyboard$default(findSearchBarView3, false, 1, null);
    }

    private final void updateQueryHint() {
        Context context = getContext();
        if (context == null) {
            return;
        }
        int i = !ConnectivityUtil.hasAnyInternetConnection(context) ? R.string.dmusic_prime_search_library_offline_query_hint : PodcastFeatureGating.PODCAST.isEnabled() && !ChildUserUtil.INSTANCE.isChildUser(context) ? R.string.dmusic_prime_search_music_and_podcasts_query_hint : R.string.dmusic_prime_search_prime_query_hint;
        FindSearchBarView findSearchBarView = this.findSearchBarView;
        if (findSearchBarView == null) {
            return;
        }
        String string = context.getString(i);
        Intrinsics.checkNotNullExpressionValue(string, "it.getString(hintId)");
        findSearchBarView.setQueryHint(string);
    }

    @Override // com.amazon.mp3.catalog.fragment.BrushFragment
    protected boolean shouldErrorLayoutBeVisible() {
        Context context = getContext();
        if (context == null || ConnectivityUtil.hasAnyInternetConnection(context)) {
            return super.shouldErrorLayoutBeVisible();
        }
        return false;
    }

    @Override // com.amazon.mp3.catalog.fragment.BrushFragment
    protected void handlePageModelUpdate() {
        Context context = getContext();
        if (context != null) {
            if (!ConnectivityUtil.hasAnyInternetConnection(context)) {
                this.mRecyclerView.setVisibility(8);
                return;
            }
            this.mRecyclerView.setVisibility(0);
        }
        super.handlePageModelUpdate();
    }

    private final void disconnectActionBarView() {
        FindSearchBarView findSearchBarView = this.findSearchBarView;
        if (findSearchBarView != null) {
            findSearchBarView.clearFocus();
        }
        FindSearchBarView findSearchBarView2 = this.findSearchBarView;
        if (findSearchBarView2 == null) {
            return;
        }
        findSearchBarView2.clearAllListeners();
    }

    private final void goSearch(SearchExperienceMode mode) {
        FragmentActivity activity = getActivity();
        SearchTabFragmentHost.selectSearchTab(activity instanceof MusicHomeActivity ? (MusicHomeActivity) activity : null, mode, null, false, true);
    }

    private final void connectActionBarView() {
        View.OnKeyListener onKeyListener = new View.OnKeyListener() { // from class: com.amazon.mp3.catalog.fragment.FindBrushFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                return FindBrushFragment.m482connectActionBarView$lambda11(this.f$0, view, i, keyEvent);
            }
        };
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.catalog.fragment.FindBrushFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FindBrushFragment.m483connectActionBarView$lambda12(this.f$0, view);
            }
        };
        View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() { // from class: com.amazon.mp3.catalog.fragment.FindBrushFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                FindBrushFragment.m484connectActionBarView$lambda13(this.f$0, view, z);
            }
        };
        View.OnClickListener onClickListener2 = new View.OnClickListener() { // from class: com.amazon.mp3.catalog.fragment.FindBrushFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FindBrushFragment.m485connectActionBarView$lambda14(this.f$0, view);
            }
        };
        FindSearchBarView findSearchBarView = this.findSearchBarView;
        if (findSearchBarView == null) {
            return;
        }
        findSearchBarView.showBackButton(false);
        FindSearchBarView.showFilterButton$default(findSearchBarView, false, false, false, 6, null);
        findSearchBarView.showAlexaButton(AlexaUtil.INSTANCE.shouldShowAlexaButton());
        findSearchBarView.setSearchViewOnKeyListener(onKeyListener);
        findSearchBarView.setSearchViewOnClickListener(onClickListener);
        findSearchBarView.setSearchViewFocusChangeListener(onFocusChangeListener);
        findSearchBarView.setAlexaButtonListener(onClickListener2);
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        this.mAlexaTooltipExperience = new AlexaTooltipExperience(contextRequireContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: connectActionBarView$lambda-11, reason: not valid java name */
    public static final boolean m482connectActionBarView$lambda11(FindBrushFragment this$0, View view, int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.goSearch(SearchExperienceMode.SEARCH_HISTORY);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: connectActionBarView$lambda-12, reason: not valid java name */
    public static final void m483connectActionBarView$lambda12(FindBrushFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.goSearch(SearchExperienceMode.SEARCH_HISTORY);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: connectActionBarView$lambda-13, reason: not valid java name */
    public static final void m484connectActionBarView$lambda13(FindBrushFragment this$0, View view, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z) {
            this$0.goSearch(SearchExperienceMode.SEARCH_HISTORY);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: connectActionBarView$lambda-14, reason: not valid java name */
    public static final void m485connectActionBarView$lambda14(FindBrushFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AlexaUtil.INSTANCE.triggerAlexa(this$0.getPageType());
    }

    private final void styleActionBar() {
        FindSearchBarView findSearchBarView;
        AlexaTooltipExperience alexaTooltipExperience;
        StyleSheet styleSheet = this.styleSheet;
        if (styleSheet != null && (findSearchBarView = this.findSearchBarView) != null) {
            findSearchBarView.styleFindSearchBarView(styleSheet);
            if (AlexaUtil.INSTANCE.shouldShowAlexaButton() && (alexaTooltipExperience = this.mAlexaTooltipExperience) != null) {
                alexaTooltipExperience.showTooltip(findSearchBarView, styleSheet);
            }
        }
        styleSearchBarViewMargins();
        updateQueryHint();
    }

    private final void styleSearchBarViewMargins() {
        StyleSheet styleSheet = this.styleSheet;
        if (styleSheet == null) {
            return;
        }
        KeyEvent.Callback callback = this.actionBarView;
        ActionBarDecorator actionBarDecorator = callback instanceof ActionBarDecorator ? (ActionBarDecorator) callback : null;
        if (actionBarDecorator == null) {
            return;
        }
        Integer spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.MEDIUM);
        actionBarDecorator.setSearchBarMargins(spacerInPixels, spacerInPixels, spacerInPixels, spacerInPixels);
        actionBarDecorator.addTopMargin();
    }

    private final View createActionBarView() {
        if (getActivity() == null) {
            return null;
        }
        if (isBauhausTopAppBarEnabled()) {
            return new BauhausActionBarView(getActivity(), null, Boolean.valueOf(AmazonApplication.getCapabilities().isCustomerProfileSupported()), Boolean.TRUE, this.findSearchBarView);
        }
        ActionBarView actionBarView = this.mActionBarView;
        return actionBarView == null ? new ActionBarView(getActivity(), null, AmazonApplication.getCapabilities().isCustomerProfileSupported(), true, this.findSearchBarView) : actionBarView;
    }

    private final void setPodcastNewBadgeIfNecessary(List<? extends BaseViewModel> models) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : models) {
            if (obj instanceof SectionGridViewModel) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList2, ((SectionGridViewModel) it.next()).getModels());
        }
        ArrayList arrayList3 = new ArrayList();
        for (Object obj2 : arrayList2) {
            if (obj2 instanceof LinkNavigatorModel) {
                arrayList3.add(obj2);
            }
        }
        ArrayList arrayList4 = new ArrayList();
        for (Object obj3 : arrayList3) {
            if (StringsKt__StringsKt.contains$default((CharSequence) ((LinkNavigatorModel) obj3).getTarget(), (CharSequence) "/podcasts", false, 2, (Object) null)) {
                arrayList4.add(obj3);
            }
        }
        Iterator it2 = arrayList4.iterator();
        while (it2.hasNext()) {
            ((LinkNavigatorModel) it2.next()).setShouldShowNewBadge(true);
        }
    }
}
