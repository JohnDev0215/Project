package com.amazon.mp3.catalog.fragment;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.actionbar.ActionBarProvider;
import com.amazon.mp3.actionbar.view.ActionBarView;
import com.amazon.mp3.actionbar.view.BauhausActionBarView;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.activity.NavigationActivity;
import com.amazon.mp3.activity.settings.LastViewedScreenUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.bottomnavbar.BottomNavigationBarContainer;
import com.amazon.mp3.brush.BauhausTooltipExperience;
import com.amazon.mp3.brush.BrushViews;
import com.amazon.mp3.brush.converters.BrushPodcastEpisodeConverter;
import com.amazon.mp3.brush.converters.BrushPodcastShowConverter;
import com.amazon.mp3.brush.v3.converters.BrushTargetTypeUtils;
import com.amazon.mp3.card.prime.ContextMenuHandler;
import com.amazon.mp3.card.prime.ContextMenuSupportingFragment;
import com.amazon.mp3.catalog.fragment.helper.BrushPageScrollHelper;
import com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel;
import com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModelFactory;
import com.amazon.mp3.customerprofile.CustomerProfileDetailManager;
import com.amazon.mp3.dynamicbackground.DynamicBackgroundImagePublisher;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.fragment.navigation.BrushFragmentNavigation;
import com.amazon.mp3.library.adapter.LibraryPagerAdapter;
import com.amazon.mp3.library.provider.LibraryStateProvider;
import com.amazon.mp3.navigation.DeeplinkIntentKey;
import com.amazon.mp3.ondemandplaylists.api.GetOnDemandPlaylistsService;
import com.amazon.mp3.ondemandplaylists.metrics.GetOnDemandPlaylistsMetricsUtil;
import com.amazon.mp3.playback.activity.NowPlayingFragmentActivity;
import com.amazon.mp3.prime.browse.AbstractPrimeTabFragmentHost;
import com.amazon.mp3.prime.browse.PrimeMusicPagerFragment;
import com.amazon.mp3.prime.browse.PrimeTabFragmentHost;
import com.amazon.mp3.providers.DefaultContextMenuProvider;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PlatformUtil;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.video.stories.ThrottleTrackingBus;
import com.amazon.mp3.video.stories.VideoStoriesBrowseHomeHelper;
import com.amazon.mp3.video.stories.VideoStoryAutoplayCoordinator;
import com.amazon.mp3.video.stories.VideoStoryPlaybackProvider;
import com.amazon.music.browse.brush.BrushLatencyReportingUtil;
import com.amazon.music.deeplink.DeeplinksManager;
import com.amazon.music.events.NavigationAppEvent;
import com.amazon.music.find.view.FindSearchBarView;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyEventDescriptor;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyTracker;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageSubType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.uicontentview.ContentViewManager;
import com.amazon.music.views.library.models.ExposedRefinementListModel;
import com.amazon.music.views.library.models.FeaturePlayModel;
import com.amazon.music.views.library.models.FilterItemModel;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.PodcastFeaturePlayModel;
import com.amazon.music.views.library.models.RefinementPillModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.enums.TargetType;
import com.amazon.music.views.library.providers.BrushUriClickListenerProvider;
import com.amazon.music.views.library.providers.EndlessScrollDataProvider;
import com.amazon.music.views.library.providers.RefinementClickListenerProvider;
import com.amazon.music.views.library.providers.SeeMoreProvider;
import com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.OverlayLoadingView;
import com.amazon.music.views.library.views.presentation.ActionBarOverlayable;
import com.amazon.podcast.PodcastDeeplinks;
import com.amazon.podcast.PodcastEpisode;
import com.amazon.podcast.PodcastShow;
import com.amazon.podcast.deeplinks.Deeplinks;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import com.google.android.gms.common.Scopes;
import com.squareup.picasso.Picasso;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.ISODateTimeFormat;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes3.dex */
public class BrushFragment extends Fragment implements PrimeMusicPagerFragment, ContextMenuSupportingFragment {
    private static final String PROFILE_ITEM_ID_TYPE = EntityIdType.PROFILE_ID.toString();
    private static final String TAG = BrushFragment.class.getSimpleName();
    boolean actionBarPreviousVisible;
    private ViewTreeObserver.OnGlobalLayoutListener actionbarListener;
    protected String associateTag;
    protected FragmentManager.OnBackStackChangedListener backStackChangedListener;
    protected BrushPageScrollHelper brushPageScrollHelper;
    protected BrushViews brushViews;
    private BauhausTooltipExperience facetNavToolTip;
    protected ExposedRefinementListModel facetedNavHeader;
    private Boolean hasInitialViewBeenSent;
    private HashMap<String, String> hotSpotExperienceVault;
    protected Boolean isArtistUpsell;
    private RecyclerView.OnScrollListener mActionBarOnScrollListener;
    protected ActionBarView mActionBarView;
    protected ImageView mBackgroundArtworkLayout;
    protected ViewGroup mBackgroundArtworkOverlayLayout;
    protected ContentViewManager mContentViewManager;
    protected DefaultContextMenuProvider mContextMenuProvider;
    protected View mEmptyLayout;
    private String mFacetNavMetricsContentName;
    private String mFacetNavTooltipStateKey;
    private String mFacetNavTooltipText;
    protected LibraryStateProvider mLibraryStateProvider;
    protected View mLoadingView;
    private LibraryPagerAdapter.OnListViewCreatedListener mOnListViewCreatedListener;
    private RecyclerView.ItemDecoration mOverlayOffsetItemDecoration;
    protected BrushViewModel mPageDataModel;
    protected PageGridViewModel mPageModel;
    protected RecyclerView mRecyclerView;
    protected RefinementClickListenerProvider mRefinementClickListenerProvider;
    protected SeeMoreProvider mSeeMoreProvider;
    protected String mStandaloneTitle;
    protected ThrottleTrackingBus mThrottleTrackingBus;
    protected BrushUriClickListenerProvider mUriClickProvider;
    private VideoStoryAutoplayCoordinator mVideoStoryAutoplayCoordinator;
    protected OverlayLoadingView overlayLoadingView;
    protected Action1<ThrottleTrackingBus.VisibleState> recyclerViewItemsTrackerCallbackListener;
    protected Action1<Throwable> recyclerViewItemsTrackerErrorListener;
    protected String refMarker;
    protected HashMap<String, List<String>> refinementLists;
    protected ViewGroup root;
    private Boolean sendUIPageViewMetricOnResume;
    protected StyleSheet styleSheet;
    protected SwipeRefreshLayout swipeRefreshLayout;
    private final float XS_SCREEN_SIZE_INCHES = 5.0f;
    protected String mToolbarTitle = "";
    protected String mPageUri = "home";
    private String mProfileId = "";

    protected boolean shouldErrorLayoutBeVisible() {
        return true;
    }

    protected boolean shouldSwipeBeEnabled() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(String str, String str2, String str3) {
        if (PodcastDeeplinks.isPodcastDeeplink(str)) {
            try {
                handleDeepLink(str);
            } catch (Exception unused) {
                handleBrushUri(str, str3, null, null);
            }
        } else if (str != null && str.contains(FindSearchBarView.URI_PREFIX)) {
            handleBrushUri(str, str3, null, this.refinementLists);
        } else {
            try {
                handleDeepLink(str);
            } catch (Exception unused2) {
                Log.debug(TAG, "The uri passed in see more provider cannot be parsed as a deeplink url");
                handleBrushUri(str, str3, null, null);
            }
        }
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.SELECT_SEE_ALL).withBlockRef(str2).withInteractionType(InteractionType.TAP).withEventTime(System.currentTimeMillis()).build());
    }

    public void setRefinementLists(HashMap<String, List<String>> refinementLists) {
        this.refinementLists = refinementLists;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1(String str, String str2, TargetType targetType) {
        try {
            handleDeepLink(str);
        } catch (Exception unused) {
            handleBrushUri(str, null, null, null);
        }
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(str, str2).withInteractionType(InteractionType.TAP).withEventTime(System.currentTimeMillis()).build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$2(RefinementPillModel refinementPillModel, View view) {
        String metricsValue;
        if (refinementPillModel.getContent() == null || refinementPillModel.getContent().getItemModel() == null) {
            return;
        }
        FilterItemModel itemModel = refinementPillModel.getContent().getItemModel();
        boolean selected = itemModel.getSelected();
        itemModel.setSelected(!selected);
        refinementPillModel.updateFilterModel(itemModel);
        String browseId = itemModel.getBrowseId();
        TargetType targetType = BrushTargetTypeUtils.INSTANCE.getTargetType(browseId);
        if (!selected) {
            if (targetType.equals(TargetType.URI)) {
                PageGridViewModel pageGridViewModel = this.mPageModel;
                if (pageGridViewModel != null) {
                    handleBrushUri(browseId, null, null, null, pageGridViewModel.getHeader());
                }
            } else if (PodcastDeeplinks.isPodcastDeeplink(browseId)) {
                try {
                    handleDeepLink(Deeplinks.modifyUrlForFacetedNavigation(browseId));
                } catch (Exception unused) {
                    Log.debug(TAG, "The uri passed in Faceted Navigation cannot be parsed as a deeplink url");
                }
            } else {
                clearPodcastDeeplink();
            }
        } else if (getActivity() instanceof MusicHomeActivity) {
            ((MusicHomeActivity) getActivity()).getFragmentController().clearBackStack();
        }
        if (selected) {
            metricsValue = ActionType.GO_HOME.getMetricsValue();
        } else if (targetType.equals(TargetType.URI)) {
            metricsValue = "goBrowseFacet";
        } else {
            metricsValue = PodcastDeeplinks.isPodcastCategories(browseId) ? "goPodcastCategory" : ActionType.GO_PODCASTS_HOME.getMetricsValue();
        }
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(metricsValue).withBlockRef(itemModel.getBlockRef()).withUrl(itemModel.getBrowseId()).withInteractionType(InteractionType.TAP).withEventTime(System.currentTimeMillis()).build());
    }

    private void clearPodcastDeeplink() {
        if (getActivity() instanceof MusicHomeActivity) {
            ((MusicHomeActivity) getActivity()).clearPodcastDeeplinkPath();
        }
    }

    public BrushFragment() {
        Boolean bool = Boolean.FALSE;
        this.hasInitialViewBeenSent = bool;
        this.sendUIPageViewMetricOnResume = bool;
        this.isArtistUpsell = bool;
        this.refinementLists = new HashMap<>();
        this.actionBarPreviousVisible = false;
        this.mSeeMoreProvider = new SeeMoreProvider() { // from class: com.amazon.mp3.catalog.fragment.BrushFragment$$ExternalSyntheticLambda4
            @Override // com.amazon.music.views.library.providers.SeeMoreProvider
            public final void onSeeMoreClick(String str, String str2, String str3) {
                this.f$0.lambda$new$0(str, str2, str3);
            }
        };
        this.mUriClickProvider = new BrushUriClickListenerProvider() { // from class: com.amazon.mp3.catalog.fragment.BrushFragment$$ExternalSyntheticLambda5
            @Override // com.amazon.music.views.library.providers.BrushUriClickListenerProvider
            public final void onClick(String str, String str2, TargetType targetType) {
                this.f$0.lambda$new$1(str, str2, targetType);
            }
        };
        this.mRefinementClickListenerProvider = new RefinementClickListenerProvider() { // from class: com.amazon.mp3.catalog.fragment.BrushFragment$$ExternalSyntheticLambda6
            @Override // com.amazon.music.views.library.providers.RefinementClickListenerProvider
            public final void onClick(RefinementPillModel refinementPillModel, View view) {
                this.f$0.lambda$new$2(refinementPillModel, view);
            }
        };
        this.backStackChangedListener = new FragmentManager.OnBackStackChangedListener() { // from class: com.amazon.mp3.catalog.fragment.BrushFragment.1
            @Override // androidx.fragment.app.FragmentManager.OnBackStackChangedListener
            public void onBackStackChanged() {
                PageGridViewModel pageGridViewModel;
                if ((BrushFragment.this.getActivity() instanceof MusicHomeActivity) && BrushFragment.this.getActivity().getSupportFragmentManager().getBackStackEntryCount() == 0 && ((MusicHomeActivity) BrushFragment.this.getActivity()).getFragmentController().getCurrentFragmentName().equals(PrimeTabFragmentHost.class.getSimpleName()) && (pageGridViewModel = BrushFragment.this.mPageModel) != null && pageGridViewModel.getHeader() != null && BrushFragment.this.mPageModel.getHeader().getRefinementList() != null) {
                    for (RefinementPillModel refinementPillModel : BrushFragment.this.mPageModel.getHeader().getRefinementList()) {
                        if (refinementPillModel.getContent() != null && refinementPillModel.getContent().getItemModel() != null) {
                            FilterItemModel itemModel = refinementPillModel.getContent().getItemModel();
                            itemModel.setSelected(false);
                            refinementPillModel.updateFilterModel(itemModel);
                        }
                    }
                }
                View actionBarViewForBrowseHome = BrushFragment.this.getActionBarViewForBrowseHome();
                if (actionBarViewForBrowseHome instanceof BauhausActionBarView) {
                    ((BauhausActionBarView) actionBarViewForBrowseHome).updateBackgroundOpacity(BrushFragment.this.mRecyclerView);
                }
            }
        };
        this.recyclerViewItemsTrackerCallbackListener = new Action1<ThrottleTrackingBus.VisibleState>() { // from class: com.amazon.mp3.catalog.fragment.BrushFragment.2
            @Override // rx.functions.Action1
            public void call(ThrottleTrackingBus.VisibleState visibleState) {
                BrushFragment.this.mVideoStoryAutoplayCoordinator.handleVisibleStateChange(visibleState, BrushFragment.this.mPageModel);
                VideoStoriesBrowseHomeHelper videoStoriesBrowseHomeHelper = VideoStoriesBrowseHomeHelper.INSTANCE;
                BrushFragment brushFragment = BrushFragment.this;
                videoStoriesBrowseHomeHelper.reportContentVideoStoryContentViewMetrics(brushFragment.mContentViewManager, brushFragment.mPageModel, visibleState);
            }
        };
        this.recyclerViewItemsTrackerErrorListener = new Action1<Throwable>() { // from class: com.amazon.mp3.catalog.fragment.BrushFragment.3
            @Override // rx.functions.Action1
            public void call(Throwable throwable) {
                Log.error(BrushFragment.TAG, "Error while tracking recycler view items" + throwable);
            }
        };
        this.mActionBarOnScrollListener = null;
        this.mOverlayOffsetItemDecoration = null;
    }

    public void setFacetedNavigationHeader(final ExposedRefinementListModel facetedNavHeader) {
        this.facetedNavHeader = facetedNavHeader;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDefaultViews();
        handlePageRefresh();
        if (AmazonApplication.getCapabilities().isVideoStoriesMutedAutoPlayEnabled()) {
            this.mVideoStoryAutoplayCoordinator.initialize(this);
        }
    }

    protected void initDefaultViews() {
        Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey("com.amazon.mp3.STANDALONE_FRAGMENT_KEY")) {
            FragmentActivity activity = getActivity();
            int i = arguments.getInt("com.amazon.mp3.STANDALONE_FRAGMENT_TITLE_KEY", 0);
            if (i != 0 && (activity instanceof NavigationActivity)) {
                this.mStandaloneTitle = activity.getResources().getString(i);
            }
        }
        initActionBarView();
        ActionBarProvider actionBarProvider = getActivity() instanceof NowPlayingFragmentActivity ? (NowPlayingFragmentActivity) getActivity() : (MusicHomeActivity) getActivity();
        if ((!"home".equals(this.mPageUri) && !this.mPageUri.contains("facet")) || isFacetedSeeMorePage(this.mPageUri)) {
            actionBarProvider.setHeaderView(this.mActionBarView);
            actionBarProvider.requestHomeButtonAsBack();
        } else {
            actionBarProvider.restoreHomeButton();
        }
    }

    protected void initActionBarView() {
        if ("home".equals(this.mPageUri)) {
            initBauhausActionBarView(true);
            return;
        }
        if (this.mPageUri.contains("facet") && !isFacetedSeeMorePage(this.mPageUri)) {
            initFacetNavigationActionBar();
            return;
        }
        String str = this.mStandaloneTitle;
        if (str == null) {
            str = this.mToolbarTitle;
        }
        PageGridViewModel pageGridViewModel = this.mPageModel;
        String subTitle = pageGridViewModel != null ? pageGridViewModel.getSubTitle() : null;
        ActionBarView actionBarView = this.mActionBarView;
        if (actionBarView == null) {
            this.mActionBarView = new ActionBarView(getActivity(), str, subTitle);
        } else {
            actionBarView.setTitles(str, subTitle);
        }
    }

    private void initBauhausActionBarView(boolean deselectPills) {
        View actionBarViewForBrowseHome = getActionBarViewForBrowseHome();
        if (actionBarViewForBrowseHome instanceof BauhausActionBarView) {
            BauhausActionBarView bauhausActionBarView = (BauhausActionBarView) actionBarViewForBrowseHome;
            bauhausActionBarView.setToolBarBackgroundColor(null);
            bauhausActionBarView.updateBackgroundOpacity(this.mRecyclerView);
            PageGridViewModel pageGridViewModel = this.mPageModel;
            if (pageGridViewModel != null && this.brushViews != null && pageGridViewModel.getHeader() != null) {
                this.brushViews.createHeaderView(UserSubscriptionUtil.getUserSubscription(), this.mPageModel.getHeader(), 2, bauhausActionBarView.getHeaderNavigationView());
                this.mPageModel.getHeader().show(null);
                bauhausActionBarView.showHeaderNavigationView(true);
                if (this.facetNavToolTip == null && getPageType() != null) {
                    initFacetNavTooltip();
                }
                if (deselectPills) {
                    for (RefinementPillModel refinementPillModel : this.mPageModel.getHeader().getRefinementList()) {
                        if (refinementPillModel.getContent() != null && refinementPillModel.getContent().getItemModel() != null) {
                            FilterItemModel itemModel = refinementPillModel.getContent().getItemModel();
                            itemModel.setSelected(false);
                            refinementPillModel.setContent(itemModel);
                        }
                    }
                }
            }
            setActionBarOnScrollListener(bauhausActionBarView.getOnScrollListener());
            if (shouldShowIngressButtons()) {
                bauhausActionBarView.showIngressButtons();
            }
        }
    }

    private void initFacetNavTooltip() {
        BauhausTooltipExperience bauhausTooltipExperience = new BauhausTooltipExperience(this.mFacetNavTooltipStateKey, this.mFacetNavTooltipText, this.styleSheet, getContext(), getPageType(), this.mFacetNavMetricsContentName, this.mRecyclerView, this.brushViews.getHeaderView(), null, "0", 2, false);
        this.facetNavToolTip = bauhausTooltipExperience;
        if (bauhausTooltipExperience.canPresentTooltip()) {
            final Rect rect = new Rect();
            this.actionbarListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.mp3.catalog.fragment.BrushFragment$$ExternalSyntheticLambda1
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public final void onGlobalLayout() {
                    this.f$0.lambda$initFacetNavTooltip$3(rect);
                }
            };
            this.brushViews.getHeaderView().getViewTreeObserver().addOnGlobalLayoutListener(this.actionbarListener);
            this.facetNavToolTip.setContainerCallbacks(new BauhausTooltipExperience.ContainerCallbacks() { // from class: com.amazon.mp3.catalog.fragment.BrushFragment.4
                @Override // com.amazon.mp3.brush.BauhausTooltipExperience.ContainerCallbacks
                public void headerContainerCallback() {
                    BrushFragment.this.facetNavToolTip.dismissTooltip(true);
                }

                @Override // com.amazon.mp3.brush.BauhausTooltipExperience.ContainerCallbacks
                public void pageContainerCallback() {
                    BrushFragment.this.facetNavToolTip.dismissTooltip(false);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initFacetNavTooltip$3(Rect rect) {
        this.brushViews.getHeaderView().getGlobalVisibleRect(rect);
        boolean z = rect.bottom - rect.top > 0;
        if (z && !this.actionBarPreviousVisible) {
            setFacetNavTooltipVisibility(Boolean.TRUE);
        }
        if (!z && this.actionBarPreviousVisible) {
            setFacetNavTooltipVisibility(Boolean.FALSE);
        }
        this.actionBarPreviousVisible = z;
    }

    private void initFacetNavigationActionBar() {
        ExposedRefinementListModel exposedRefinementListModel;
        BauhausActionBarView bauhausActionBarView = new BauhausActionBarView(getActivity(), null, Boolean.FALSE, Boolean.valueOf(!AmazonApplication.getCapabilities().isFacetedNavigationEnabled()));
        PageGridViewModel pageGridViewModel = this.mPageModel;
        if (pageGridViewModel != null && pageGridViewModel.getHeader() == null && (exposedRefinementListModel = this.facetedNavHeader) != null) {
            this.mPageModel.setHeader(exposedRefinementListModel);
        }
        PageGridViewModel pageGridViewModel2 = this.mPageModel;
        if (pageGridViewModel2 != null && pageGridViewModel2.getHeader() != null && this.facetedNavHeader == null) {
            this.facetedNavHeader = this.mPageModel.getHeader();
        }
        PageGridViewModel pageGridViewModel3 = this.mPageModel;
        if (pageGridViewModel3 == null || this.brushViews == null || pageGridViewModel3.getHeader() == null) {
            return;
        }
        this.brushViews.createHeaderView(UserSubscriptionUtil.getUserSubscription(), this.mPageModel.getHeader(), 2, bauhausActionBarView.getHeaderNavigationView());
        this.mPageModel.getHeader().show(null);
        bauhausActionBarView.showHeaderNavigationView(true);
        if (((getActivity() instanceof MusicHomeActivity) && BrushFragment.class.getSimpleName().equals(((MusicHomeActivity) getActivity()).getFragmentController().getCurrentFragmentName())) && this.mPageModel.getHeader().getRefinementList() != null) {
            for (RefinementPillModel refinementPillModel : this.mPageModel.getHeader().getRefinementList()) {
                if (refinementPillModel.getContent() != null && refinementPillModel.getContent().getItemModel() != null) {
                    FilterItemModel itemModel = refinementPillModel.getContent().getItemModel();
                    itemModel.setSelected(itemModel.getBrowseId().contains(this.mPageUri));
                    refinementPillModel.setContent(itemModel);
                }
            }
        }
        setActionBarOnScrollListener(bauhausActionBarView.getOnScrollListener());
        if (shouldShowIngressButtons()) {
            bauhausActionBarView.showIngressButtons();
        }
        ActionBarProvider actionBarProvider = getActivity() instanceof NowPlayingFragmentActivity ? (NowPlayingFragmentActivity) getActivity() : (MusicHomeActivity) getActivity();
        if (actionBarProvider != null) {
            actionBarProvider.setHeaderView(bauhausActionBarView);
        }
        bauhausActionBarView.updateBackgroundOpacity(this.mRecyclerView);
    }

    private boolean shouldShowIngressButtons() {
        return AmazonApplication.getCapabilities().isIngressPillsEnabled(getContext()) && !AmazonApplication.getCapabilities().isFacetedNavigationEnabled();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public View getActionBarViewForBrowseHome() {
        ActionBarView actionBarView = this.mActionBarView;
        if (actionBarView != null) {
            return actionBarView;
        }
        Fragment parentFragment = getParentFragment();
        return parentFragment instanceof AbstractPrimeTabFragmentHost ? ((AbstractPrimeTabFragmentHost) parentFragment).getHeaderView() : actionBarView;
    }

    public void setIsArtistUpsell(boolean isArtistUpsell) {
        this.isArtistUpsell = Boolean.valueOf(isArtistUpsell);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StyleSheetProvider.getStyleSheet().observe(this, new Observer() { // from class: com.amazon.mp3.catalog.fragment.BrushFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$onCreate$4((StyleSheet) obj);
            }
        });
        this.mContentViewManager = new ContentViewManager();
        initPageDataModel();
        if (isBrowseHomeLandingPage()) {
            LatencyTracker.INSTANCE.track(new LatencyEventDescriptor(LatencyTrackingLeg.INITIALIZING, PageLoadLatencyCode.HOME));
        }
        this.brushPageScrollHelper = new BrushPageScrollHelper(getActivity(), this.mPageUri);
        this.mThrottleTrackingBus = new ThrottleTrackingBus(this.recyclerViewItemsTrackerCallbackListener, this.recyclerViewItemsTrackerErrorListener);
        this.mVideoStoryAutoplayCoordinator = VideoStoryPlaybackProvider.INSTANCE.getVideoStoryAutoPlayCoordinator();
        this.hotSpotExperienceVault = ((MusicHomeActivity) getActivity()).getHotSpotExperienceVault();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$4(StyleSheet styleSheet) {
        this.styleSheet = styleSheet;
        if (hasValidBrushData()) {
            setupBrushViews();
        }
    }

    protected void initPageDataModel() {
        Bundle arguments = getArguments();
        if (arguments != null && !TextUtils.isEmpty(arguments.getString("brush_uri_extra"))) {
            this.mPageUri = BrushFragmentNavigation.generateUri(arguments.getString("brush_uri_extra"), arguments);
        }
        if (arguments != null && !TextUtils.isEmpty(arguments.getString("brush_genre_id_extra"))) {
            this.refinementLists.put("GENRE_REFINEMENT", Arrays.asList(arguments.getString("brush_genre_id_extra")));
        }
        if (arguments.getString("brush_profileid_extra") != null) {
            this.mProfileId = arguments.getString("brush_profileid_extra");
        }
        BrushViewModel brushViewModel = (BrushViewModel) ViewModelProviders.of(this, new BrushViewModelFactory(getActivity().getApplication(), this.mPageUri, arguments.getString("brush_nextpagetoken_extra"), this.isArtistUpsell.booleanValue(), true, null, this.refinementLists)).get(BrushViewModel.class);
        this.mPageDataModel = brushViewModel;
        this.mLibraryStateProvider = brushViewModel.getLibraryStateProvider();
        this.mPageDataModel.registerDownloadStateListenerForTracks();
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.browse_home, container, false);
        this.root = viewGroup;
        this.mLoadingView = viewGroup.findViewById(R.id.loading_spinner);
        this.overlayLoadingView = (OverlayLoadingView) this.root.findViewById(R.id.browse_overlay_loading_view);
        this.swipeRefreshLayout = (SwipeRefreshLayout) this.root.findViewById(R.id.swipeRefreshLayout);
        this.mRecyclerView = (RecyclerView) this.root.findViewById(R.id.recyclerView);
        this.mEmptyLayout = this.root.findViewById(R.id.empty_layout);
        this.mToolbarTitle = getArguments().getString(BrushFragmentNavigation.TOOLBAR_TITLE_KEY);
        this.mFacetNavTooltipStateKey = getResources().getString(R.string.dmusic_facet_nav_tooltip_state_key);
        this.mFacetNavTooltipText = getResources().getString(R.string.dmusic_facet_nav_tooltip_text);
        this.mFacetNavMetricsContentName = getResources().getString(R.string.dmusic_facet_nav_metrics_content_name);
        if (AmazonApplication.getCapabilities().isSonicRushEnabled()) {
            ((ProgressBar) this.mLoadingView).setIndeterminateTintList(ColorStateList.valueOf(getResources().getColor(R.color.cyan_accent)));
        } else {
            ((ProgressBar) this.mLoadingView).setIndeterminateTintList(ColorStateList.valueOf(getResources().getColor(R.color.blue_accent)));
        }
        this.mBackgroundArtworkLayout = (ImageView) this.root.findViewById(R.id.artwork);
        this.mBackgroundArtworkOverlayLayout = (ViewGroup) this.root.findViewById(R.id.artworkOverlayLayout);
        int i = getResources().getConfiguration().orientation;
        if (isBrowseHomeLandingPage() && i == 1 && !PlatformUtil.isFireOS() && isLoadingShellEnabled()) {
            if (isFeaturePlayV2Enabled()) {
                this.mLoadingView = this.root.findViewById(R.id.loading_screen_small_t1r);
                if (calculateScreenDiagonalInches() < 5.0f) {
                    this.mLoadingView.setPadding(0, 0, 0, 0);
                }
            } else {
                this.mLoadingView = this.root.findViewById(R.id.loading_screen_big_t1r);
            }
        }
        if (getActivity() != null) {
            this.mPageDataModel.isPageCacheExpired().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Action1() { // from class: com.amazon.mp3.catalog.fragment.BrushFragment$$ExternalSyntheticLambda7
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    this.f$0.lambda$onCreateView$7((Boolean) obj);
                }
            });
        } else {
            this.mLoadingView.setVisibility(8);
        }
        this.mEmptyLayout.setVisibility(8);
        setRecyclerViewListeners();
        if (hasValidBrushData() && this.styleSheet != null) {
            setupBrushViews();
        }
        if (isBrowseHomeLandingPage()) {
            updateActionBarOverlayState(true);
        }
        if (AmazonApplication.getCapabilities().isFacetedNavigationEnabled() && this.mPageUri.equals("home") && (getActivity() instanceof MusicHomeActivity)) {
            getActivity().getSupportFragmentManager().addOnBackStackChangedListener(this.backStackChangedListener);
        }
        return this.root;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateView$7(Boolean bool) {
        if (getActivity() != null) {
            if (bool.booleanValue()) {
                getActivity().runOnUiThread(new Runnable() { // from class: com.amazon.mp3.catalog.fragment.BrushFragment$$ExternalSyntheticLambda8
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onCreateView$5();
                    }
                });
            } else {
                getActivity().runOnUiThread(new Runnable() { // from class: com.amazon.mp3.catalog.fragment.BrushFragment$$ExternalSyntheticLambda9
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onCreateView$6();
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateView$5() {
        this.mLoadingView.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateView$6() {
        this.mLoadingView.setVisibility(8);
    }

    private float calculateScreenDiagonalInches() {
        if (getActivity() == null || !(getActivity() instanceof MusicHomeActivity)) {
            return 5.0f;
        }
        DisplayMetrics displayMetrics = getActivity().getResources().getDisplayMetrics();
        float f = displayMetrics.widthPixels;
        float f2 = displayMetrics.heightPixels;
        float f3 = displayMetrics.densityDpi;
        return (float) Math.sqrt(Math.pow(f / f3, 2.0d) + Math.pow(f2 / f3, 2.0d));
    }

    private boolean isLiveStreamPage() {
        return this.mPageUri.contains("live-streams");
    }

    private void initLiveActionBar() {
        String str = this.mStandaloneTitle;
        if (str == null) {
            str = this.mToolbarTitle;
        }
        PageGridViewModel pageGridViewModel = this.mPageModel;
        String subTitle = pageGridViewModel != null ? pageGridViewModel.getSubTitle() : null;
        ActionBarView actionBarView = this.mActionBarView;
        if (actionBarView == null) {
            this.mActionBarView = new ActionBarView(getActivity(), str, subTitle);
        } else {
            actionBarView.setTitles(str, subTitle);
        }
        ActionBarProvider actionBarProvider = getActivity() instanceof NowPlayingFragmentActivity ? (NowPlayingFragmentActivity) getActivity() : (MusicHomeActivity) getActivity();
        actionBarProvider.setHeaderView(this.mActionBarView);
        actionBarProvider.requestHomeButtonAsBack();
    }

    private boolean isBrowseHomeLandingPage() {
        return this.mPageUri.equals("home") || this.mPageUri.equals("home-music") || (this.mPageUri.contains("facet") && !isFacetedSeeMorePage(this.mPageUri));
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (isBrowseHomeLandingPage()) {
            updateActionBarOverlayState(!hidden);
        }
    }

    private void updateActionBarOverlayState(boolean isOverlay) {
        boolean zIsBauhausTopAppBarEnabled = isOverlay & isBauhausTopAppBarEnabled();
        if (getActivity() instanceof ActionBarOverlayable) {
            ((ActionBarOverlayable) getActivity()).setActionBarOverlay(zIsBauhausTopAppBarEnabled);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        if (this.mContentViewManager != null && getUserVisibleHint()) {
            this.mContentViewManager.sendUiContentViewEvent();
        }
        super.onPause();
        storeScrollPositions();
        this.mPageDataModel.unregisterDownloadStateListener();
        this.mVideoStoryAutoplayCoordinator.pauseAutoPlay(VideoStoryAutoplayCoordinator.AutoplayStateChangeReason.NONE);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        Bundle bundle = new Bundle(getArguments());
        String string = bundle.getString("brush_uri_extra");
        if (string != null && !this.mPageUri.equals(string)) {
            handleBrushUri(string, "", bundle, this.refinementLists);
            cleanupDeeplinkExtras(getArguments());
            getArguments().putString("brush_uri_extra", this.mPageUri);
        } else {
            handleDeeplinks();
            setBottomBarTab();
            handlePageModelUpdate();
        }
        if (isLiveStreamPage()) {
            initLiveActionBar();
        }
        if (isBrowseHomeLandingPage()) {
            updateActionBarOverlayState(true);
        }
        this.mPageDataModel.registerDownloadStateListenerForTracks();
        this.mVideoStoryAutoplayCoordinator.playStoryIfStoryShovelerInFocus(this.mRecyclerView, this.mPageModel);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        this.mPageDataModel.registerDownloadStateListenerForTracks();
        if (AmazonApplication.getCapabilities().isFacetedNavigationEnabled()) {
            if (this.mPageUri.equals("home") || this.mPageUri.contains("facet")) {
                DynamicBackgroundImagePublisher.getInstance().pauseDynamicBackgroundImagePublisher();
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        if (this.mPageUri.equals("home") || this.mPageUri.equals("home-music") || this.mPageUri.contains("facet")) {
            updateActionBarOverlayState(false);
        }
        this.sendUIPageViewMetricOnResume = Boolean.TRUE;
        setFacetNavTooltipVisibility(Boolean.FALSE);
        this.mPageDataModel.unregisterDownloadStateListener();
        if (AmazonApplication.getCapabilities().isFacetedNavigationEnabled()) {
            if (this.mPageUri.equals("home") || this.mPageUri.contains("facet")) {
                DynamicBackgroundImagePublisher.getInstance().resumeDynamicBackgroundImagePublisher();
            }
        }
    }

    protected void handlePageModelUpdate() {
        if (hasValidBrushData()) {
            this.mPageDataModel.onResume();
            if (this.sendUIPageViewMetricOnResume.booleanValue()) {
                sendUiPageViewMetric(removeUriPrefix(getIdentifier()));
                this.sendUIPageViewMetricOnResume = Boolean.FALSE;
                this.hasInitialViewBeenSent = Boolean.TRUE;
            }
        }
    }

    protected void setBottomBarTab() {
        if (BottomNavigationBarContainer.getInstance().isEnabled() && "home".equals(this.mPageUri)) {
            BottomNavigationBarContainer.getInstance().setBottomBarTab(LastViewedScreenUtil.LastViewedScreen.PRIME_MUSIC);
        }
    }

    private void cleanupDeeplinkExtras(final Bundle bundle) {
        if (bundle == null) {
            return;
        }
        bundle.remove(DeeplinkIntentKey.KEY_REF_MARKER.toString());
        bundle.remove(DeeplinkIntentKey.KEY_ASSOCIATE_TAG.toString());
    }

    private String removeUriPrefix(final String uri) {
        return uri != null ? uri.replace(FindSearchBarView.URI_PREFIX, "") : uri;
    }

    protected String getIdentifier() {
        PageType pageType = getPageType();
        if (pageType != null) {
            return pageType.getMetricsValue();
        }
        return this.mPageUri;
    }

    protected PageType getPageType() {
        String str = this.mPageUri;
        if (str == null) {
            return null;
        }
        if (str.equalsIgnoreCase("home")) {
            return PageType.BROWSE_HOME;
        }
        if (this.mPageUri.contains("facet")) {
            return PageType.BROWSE_HOME_FACET;
        }
        if (this.mPageUri.contains(NotificationCompat.CATEGORY_RECOMMENDATION)) {
            return PageType.BROWSE_RECOMMENDED;
        }
        if (this.mPageUri.contains("live-streams")) {
            return PageType.BROWSE_LIVE_STREAMS;
        }
        if (this.mPageUri.contains(Scopes.PROFILE)) {
            return PageType.CUSTOMER_PROFILE;
        }
        if (this.mPageUri.contains("playlists")) {
            return PageType.BROWSE_PLAYLISTS;
        }
        if (this.mPageUri.contains("artist")) {
            return PageType.BROWSE_ARTIST_DETAIL;
        }
        return null;
    }

    protected void sendUiPageViewMetric(final String identifier) {
        String metricsValue;
        Boolean bool;
        String str = this.mPageUri.contains(Scopes.PROFILE) ? PROFILE_ITEM_ID_TYPE : null;
        getArguments();
        String profileId = CustomerProfileDetailManager.getInstance().getProfileId();
        if (this.mPageUri.contains("full")) {
            if (TextUtils.isEmpty(this.mProfileId)) {
                bool = Boolean.TRUE;
            } else if (profileId != null && this.mProfileId.equals(profileId)) {
                bool = Boolean.TRUE;
            } else {
                bool = Boolean.FALSE;
            }
            metricsValue = (bool.booleanValue() ? PageSubType.SELF_PROFILE : PageSubType.PEER_PROFILE).getMetricsValue();
        } else {
            metricsValue = "";
        }
        NavigationAppEvent.builder(identifier).useIdentifierAsPageType().withItemIdentifierSubType(metricsValue).withItemIdentifier(this.mProfileId).withItemIdentifierType(str).withBlockRef(isPageBlockRefValid(this.mPageModel.getViewId()) ? this.mPageModel.getViewId() : null).withRefMarker(this.refMarker).withAssociateTag(this.associateTag).withOrientation(ScreenUtil.getScreenOrientation(getContext())).publish();
    }

    private boolean isPageBlockRefValid(final String blockRef) {
        return Pattern.compile("^[123]\\|P\\|.+").matcher(blockRef).find();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        if (this.mToolbarTitle != null && (getActivity() instanceof MusicHomeActivity)) {
            ((MusicHomeActivity) getActivity()).removeHeaderView();
        }
        BrowseViewsFactory.removeRegisteredSholverImageLoader(getContext());
        if (this.actionbarListener != null) {
            this.brushViews.getHeaderView().getViewTreeObserver().removeOnGlobalLayoutListener(this.actionbarListener);
        }
        this.mContentViewManager = null;
        this.mThrottleTrackingBus.unsubscribe();
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        stopImageRequests();
        storeScrollPositions();
        if (getActivity() != null && (getActivity() instanceof MusicHomeActivity)) {
            getActivity().getSupportFragmentManager().removeOnBackStackChangedListener(this.backStackChangedListener);
        }
        this.swipeRefreshLayout = null;
        this.mEmptyLayout = null;
        this.mLoadingView = null;
        if (this.mRecyclerView != null) {
            setActionBarOnScrollListener(null);
            setOverlayOffsetItemDecoration(null);
            this.mRecyclerView = null;
        }
        this.root = null;
        this.mContextMenuProvider = null;
        super.onDestroyView();
    }

    @Override // com.amazon.mp3.prime.browse.PrimeMusicPagerFragment
    public void setOnListViewCreatedListener(final LibraryPagerAdapter.OnListViewCreatedListener listener) {
        this.mOnListViewCreatedListener = listener;
    }

    @Override // com.amazon.mp3.prime.browse.PrimeMusicPagerFragment
    public void setupTouchListener(final View.OnTouchListener listener) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.setOnTouchListener(listener);
        }
    }

    @Override // com.amazon.mp3.prime.browse.PrimeMusicPagerFragment
    public void scrollToTop() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.scrollToPosition(0);
        }
    }

    @Override // com.amazon.mp3.card.prime.ContextMenuSupportingFragment
    public void openContextMenuForView(ContextMenuHandler contextMenuHandler, View view) {
        registerForContextMenu(view);
        setFacetNavTooltipVisibility(Boolean.FALSE);
        getActivity().openContextMenu(view);
        unregisterForContextMenu(view);
    }

    @Override // androidx.fragment.app.Fragment, android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        DefaultContextMenuProvider defaultContextMenuProvider = this.mContextMenuProvider;
        if (defaultContextMenuProvider != null) {
            defaultContextMenuProvider.onCreateContextMenu(menu, "cirrus");
        }
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onContextItemSelected(MenuItem menuItem) {
        if (this.mContextMenuProvider == null || !getUserVisibleHint()) {
            return false;
        }
        return this.mContextMenuProvider.onContextMenuItemSelected(menuItem, "cirrus");
    }

    private void handleDeeplinks() {
        Bundle arguments = getArguments();
        this.refMarker = (String) arguments.getSerializable(DeeplinkIntentKey.KEY_REF_MARKER.toString());
        this.associateTag = (String) arguments.getSerializable(DeeplinkIntentKey.KEY_ASSOCIATE_TAG.toString());
        cleanupDeeplinkExtras(arguments);
    }

    private void handlePageRefresh() {
        this.mPageDataModel.getPageModelLiveData().observe(getViewLifecycleOwner(), new Observer<PageGridViewModel>() { // from class: com.amazon.mp3.catalog.fragment.BrushFragment.5
            @Override // androidx.lifecycle.Observer
            public void onChanged(@Nullable PageGridViewModel pageGridViewModel) {
                boolean z = false;
                if (pageGridViewModel != null && pageGridViewModel.getPageIndex() == 0) {
                    z = true;
                }
                BrushFragment.this.updatePageModel(pageGridViewModel, z);
                BrushFragment.this.observePodcastUserData();
                if (z) {
                    BrushLatencyReportingUtil.INSTANCE.trackLatency(BrushFragment.this.mPageUri, LatencyTrackingLeg.LOAD_COMPLETE);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void observePodcastUserData() {
        if (PodcastFeatureGating.USER_CONTENT.isEnabled()) {
            if (("home".equals(this.mPageUri) || this.mPageUri.contains("facet")) && hasValidBrushData()) {
                if (!BrushPodcastShowConverter.INSTANCE.containsPodcastFollowedShowsShoveler(this.mPageModel).isEmpty()) {
                    this.mPageDataModel.getPodcastFollowedShows(getActivity()).observe(getViewLifecycleOwner(), new Observer<List<PodcastShow>>() { // from class: com.amazon.mp3.catalog.fragment.BrushFragment.6
                        @Override // androidx.lifecycle.Observer
                        public void onChanged(final List<PodcastShow> podcastShows) {
                            if (BrushFragment.this.hasValidBrushData()) {
                                BrushFragment brushFragment = BrushFragment.this;
                                brushFragment.mPageDataModel.updatePodcastFollowedShows(podcastShows, brushFragment.mPageModel);
                            }
                        }
                    });
                }
                BrushPodcastEpisodeConverter.Companion companion = BrushPodcastEpisodeConverter.INSTANCE;
                if (!companion.containsPodcastSavedEpisodesShoveler(this.mPageModel).isEmpty()) {
                    this.mPageDataModel.getPodcastSavedEpisodes(getActivity()).observe(getViewLifecycleOwner(), new Observer<List<PodcastEpisode>>() { // from class: com.amazon.mp3.catalog.fragment.BrushFragment.7
                        @Override // androidx.lifecycle.Observer
                        public void onChanged(final List<PodcastEpisode> podcastEpisodes) {
                            if (BrushFragment.this.hasValidBrushData()) {
                                BrushFragment brushFragment = BrushFragment.this;
                                brushFragment.mPageDataModel.updatePodcastSavedEpisodes(podcastEpisodes, brushFragment.mPageModel);
                            }
                        }
                    });
                }
                if (companion.containsPodcastJumpBackInShoveler(this.mPageModel).isEmpty()) {
                    return;
                }
                this.mPageDataModel.getPodcastJumpBackInEpisodes(getActivity()).observe(getViewLifecycleOwner(), new Observer<List<PodcastEpisode>>() { // from class: com.amazon.mp3.catalog.fragment.BrushFragment.8
                    @Override // androidx.lifecycle.Observer
                    public void onChanged(final List<PodcastEpisode> podcastEpisodes) {
                        if (BrushFragment.this.hasValidBrushData()) {
                            BrushFragment brushFragment = BrushFragment.this;
                            brushFragment.mPageDataModel.updatePodcastJumpBackInEpisodes(podcastEpisodes, brushFragment.mPageModel);
                        }
                    }
                });
            }
        }
    }

    protected void updatePageModel(@Nullable PageGridViewModel pageGridViewModel, boolean isInitialPage) {
        this.mPageModel = pageGridViewModel;
        if (pageGridViewModel != null) {
            this.mToolbarTitle = pageGridViewModel.getTitle();
            initActionBarView();
            if (!this.hasInitialViewBeenSent.booleanValue()) {
                sendUiPageViewMetric(removeUriPrefix(getIdentifier()));
                this.hasInitialViewBeenSent = Boolean.TRUE;
            }
        }
        if (!hasValidBrushData()) {
            showEmptyView();
        } else if (isInitialPage) {
            onLoadInitPage();
        } else {
            onLoadMorePage();
        }
        this.mVideoStoryAutoplayCoordinator.setShovelerViewId(VideoStoriesBrowseHomeHelper.INSTANCE.getVideoStoryShovelerRefId(this.mPageModel));
        this.swipeRefreshLayout.setRefreshing(false);
    }

    private void showEmptyView() {
        View view = this.mLoadingView;
        if (view != null) {
            view.setVisibility(8);
        }
        if (this.mEmptyLayout != null) {
            if (this.mPageUri.contains("profile/full/")) {
                MetricsLogger.sendEvent(FlexEvent.builder("customerProfile").withFlexStr1("profile.load.error").withFlexStr2("Failed to load brush view from profiles service").build());
            } else if (this.mPageUri.contains("profile/following/")) {
                MetricsLogger.sendEvent(FlexEvent.builder("customerProfile").withFlexStr1("profile.following.load.error").withFlexStr2("Failed to load brush view from profiles service").build());
            } else if (this.mPageUri.contains("profile/follower/")) {
                MetricsLogger.sendEvent(FlexEvent.builder("customerProfile").withFlexStr1("profile.followers.load.error").withFlexStr2("Failed to load brush view from profiles service").build());
            } else if (this.mPageUri.contains("profile/public-playlists/")) {
                MetricsLogger.sendEvent(FlexEvent.builder("customerProfile").withFlexStr1("profile.playlists.load.error").withFlexStr2("Failed to load brush view from profiles service").build());
            }
            if (shouldErrorLayoutBeVisible()) {
                this.mEmptyLayout.setVisibility(0);
            } else {
                this.mRecyclerView.setVisibility(8);
            }
        }
    }

    boolean hasValidBrushData() {
        PageGridViewModel pageGridViewModel = this.mPageModel;
        return (pageGridViewModel == null || pageGridViewModel.getModels() == null || this.mPageModel.getModels().size() <= 0) ? false : true;
    }

    private void onLoadInitPage() {
        if (this.styleSheet != null) {
            setupBrushViews();
            initActionBarView();
        }
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.postInvalidateDelayed(200L);
        }
    }

    private void onLoadMorePage() {
        if (hasValidBrushData() && this.brushViews == null && this.styleSheet != null) {
            setupBrushViews();
        }
        this.brushViews.updateAdapter(this.mPageModel.getModels());
        if ("home".equals(this.mPageUri)) {
            initBauhausActionBarView(false);
        } else if (this.mPageUri.contains("facet")) {
            initFacetNavigationActionBar();
        }
        if (this.mPageModel.getHeader() != null) {
            this.brushViews.updateHeaderAdapter(this.mPageModel.getHeader());
        }
    }

    private void stopImageRequests() {
        Context context = getContext();
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.setOnScrollListener(null);
        }
        Picasso.get().cancelTag(context);
    }

    protected void handleBrushUri(String uri, String nextToken, Bundle bundle, HashMap<String, List<String>> refinementLists) {
        handleBrushUri(uri, nextToken, bundle, refinementLists, null);
    }

    protected void handleBrushUri(String uri, String nextToken, Bundle bundle, HashMap<String, List<String>> refinementLists, ExposedRefinementListModel facetedNavHeader) {
        if (TextUtils.isEmpty(uri)) {
            return;
        }
        BrushFragment brushFragmentCreateFragment = new BrushFragmentNavigation.Builder(uri).withRefinementLists(refinementLists).withToken(nextToken).withBundle(bundle).withFacetedNavigationHeader(facetedNavHeader).createFragment();
        if (getActivity() instanceof NowPlayingFragmentActivity) {
            ((NowPlayingFragmentActivity) getActivity()).changeScreenFragment(brushFragmentCreateFragment, true, true, true);
        } else {
            ((MusicHomeActivity) getActivity()).changeScreenFragment(brushFragmentCreateFragment, true, true, true);
        }
    }

    protected void handleDeepLink(String link) throws Exception {
        DeeplinksManager.get(AmazonApplication.getContext()).handle(Uri.parse(link));
    }

    protected void setupBrushViews() {
        DefaultContextMenuProvider defaultContextMenuProvider = new DefaultContextMenuProvider(getActivity(), this, this.mLibraryStateProvider, getPageType(), this.mPageDataModel.getPageStateManager());
        this.mContextMenuProvider = defaultContextMenuProvider;
        this.brushViews = BrowseViewsFactory.createBrowseViewsV2(this, this.styleSheet, this.mRecyclerView, defaultContextMenuProvider, this.mSeeMoreProvider, this.mUriClickProvider, null, null, new EndlessScrollDataProvider() { // from class: com.amazon.mp3.catalog.fragment.BrushFragment$$ExternalSyntheticLambda2
            @Override // com.amazon.music.views.library.providers.EndlessScrollDataProvider
            public final void loadMore(int i, int i2) {
                this.f$0.lambda$setupBrushViews$8(i, i2);
            }
        }, this.mLibraryStateProvider, this.mContentViewManager, getPageType(), this.mPageDataModel.getFollowStateUseCase(), this.mRefinementClickListenerProvider);
        this.mLoadingView.setVisibility(8);
        createBrushRootView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupBrushViews$8(int i, int i2) {
        this.mPageDataModel.fetchData(i);
    }

    protected void createBrushRootView() {
        this.brushViews.createRootView(UserSubscriptionUtil.getUserSubscription(), this.mPageModel.getModels(), this.mPageModel.getTileType(), this.mRecyclerView, this.swipeRefreshLayout, null);
        setBrushViewsTopOffsetIfNeed();
        setupLayoutAndScrollPosition();
    }

    protected void setBrushViewsTopOffsetIfNeed() {
        PageGridViewModel pageGridViewModel = this.mPageModel;
        if (pageGridViewModel == null) {
            return;
        }
        List<BaseViewModel> models = pageGridViewModel.getModels();
        if (models.isEmpty() || (models.get(0) instanceof FeaturePlayModel) || (models.get(0) instanceof PodcastFeaturePlayModel) || !(getActivity() instanceof ActionBarOverlayable)) {
            return;
        }
        ActionBarOverlayable actionBarOverlayable = (ActionBarOverlayable) getActivity();
        if (actionBarOverlayable.isActionBarOverlay()) {
            setOverlayOffsetItemDecoration(actionBarOverlayable.setContentOffset(this.mRecyclerView, BauhausActionBarView.getActionBarHeight(getResources())));
        }
    }

    protected Pair<Integer, Integer> setupLayoutAndScrollPosition() {
        if (this.mPageModel.getNestedModelViewId() != null) {
            recordPageLevelUIContentViewMetric();
        }
        this.swipeRefreshLayout.setColorSchemeResources(R.color.white);
        this.swipeRefreshLayout.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.glass_2));
        this.swipeRefreshLayout.setVisibility(0);
        if (shouldSwipeBeEnabled()) {
            this.swipeRefreshLayout.setEnabled(true);
        }
        this.swipeRefreshLayout.setRefreshing(false);
        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.amazon.mp3.catalog.fragment.BrushFragment$$ExternalSyntheticLambda3
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                this.f$0.lambda$setupLayoutAndScrollPosition$9();
            }
        });
        Pair<Integer, Integer> lastScrollPosition = this.brushPageScrollHelper.getLastScrollPosition();
        if (((Integer) lastScrollPosition.first).intValue() >= 0) {
            ((LinearLayoutManager) this.mRecyclerView.getLayoutManager()).scrollToPositionWithOffset(((Integer) lastScrollPosition.first).intValue(), ((Integer) lastScrollPosition.second).intValue());
        }
        LibraryPagerAdapter.OnListViewCreatedListener onListViewCreatedListener = this.mOnListViewCreatedListener;
        if (onListViewCreatedListener != null) {
            onListViewCreatedListener.onListViewCreated(this.mRecyclerView);
        }
        return lastScrollPosition;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: handleSwipeRefresh, reason: merged with bridge method [inline-methods] */
    public void lambda$setupLayoutAndScrollPosition$9() {
        if (this.mPageDataModel != null) {
            this.brushPageScrollHelper.reset();
            this.mPageDataModel.fetchNewData();
        }
        PageGridViewModel pageGridViewModel = this.mPageModel;
        if (pageGridViewModel != null && pageGridViewModel.getNextPageToken() == null) {
            this.refinementLists.clear();
        }
        refreshOnDemandPlaylists();
    }

    private void recordPageLevelUIContentViewMetric() {
        if (this.mRecyclerView.getAdapter() instanceof UniversalAdapter) {
            ((UniversalAdapter) this.mRecyclerView.getAdapter()).initContentViewReporting(this.mContentViewManager, this.mPageModel.getNestedModelViewId());
        }
    }

    protected void storeScrollPositions() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null && (recyclerView.getLayoutManager() instanceof LinearLayoutManager)) {
            this.brushPageScrollHelper.saveCurrentScrollPostition(((LinearLayoutManager) this.mRecyclerView.getLayoutManager()).findFirstVisibleItemPosition(), this.mRecyclerView.getChildAt(0) != null ? BrushPageScrollHelper.getTopScrollOffset(this.mRecyclerView, 0) - this.mRecyclerView.getPaddingTop() : 0);
            return;
        }
        this.brushPageScrollHelper.reset();
    }

    protected synchronized void setOverlayOffsetItemDecoration(@Nullable RecyclerView.ItemDecoration itemDecoration) {
        RecyclerView.ItemDecoration itemDecoration2 = this.mOverlayOffsetItemDecoration;
        if (itemDecoration2 != null) {
            this.mRecyclerView.removeItemDecoration(itemDecoration2);
        }
        this.mOverlayOffsetItemDecoration = itemDecoration;
    }

    protected void setActionBarOnScrollListener(@Nullable RecyclerView.OnScrollListener scrollListener) {
        RecyclerView.OnScrollListener onScrollListener = this.mActionBarOnScrollListener;
        if (onScrollListener != null) {
            this.mRecyclerView.removeOnScrollListener(onScrollListener);
        }
        if (scrollListener != null) {
            this.mRecyclerView.addOnScrollListener(scrollListener);
        }
        this.mActionBarOnScrollListener = scrollListener;
    }

    protected void setRecyclerViewListeners() {
        this.mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.amazon.mp3.catalog.fragment.BrushFragment.9
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dx == 0 && dy == 0) {
                    return;
                }
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (linearLayoutManager != null) {
                    BrushFragment.this.mThrottleTrackingBus.postViewEvent(new ThrottleTrackingBus.VisibleState(linearLayoutManager.findFirstCompletelyVisibleItemPosition(), linearLayoutManager.findLastCompletelyVisibleItemPosition(), ISODateTimeFormat.dateTime().print(new DateTime(DateTimeZone.UTC))));
                }
            }
        });
    }

    protected boolean isBauhausTopAppBarEnabled() {
        return AmazonApplication.getCapabilities().isBauhausTopAppBarEnabled();
    }

    protected boolean isFeaturePlayV2Enabled() {
        return Feature.feature_play_v2_browse_home.isEnabled();
    }

    protected boolean isLoadingShellEnabled() {
        return Feature.shell_loading_animation.isEnabled();
    }

    protected void refreshOnDemandPlaylists() {
        if (AmazonApplication.getCapabilities().isOnDemandPlaylistsEnabled()) {
            GetOnDemandPlaylistsMetricsUtil.INSTANCE.trackEvent(GetOnDemandPlaylistsMetricsUtil.EventType.EVENT_BROWSE_HOME_REFRESH);
            GetOnDemandPlaylistsService.INSTANCE.get().fetchAndCacheOnDemandPlaylists();
        }
    }

    protected void setFacetNavTooltipVisibility(Boolean show) {
        if (this.facetNavToolTip == null || isDialogOverlayActive()) {
            return;
        }
        this.facetNavToolTip.setVisibility(show.booleanValue(), this.brushViews.getHeaderView(), this.styleSheet);
    }

    protected boolean isDialogOverlayActive() {
        return getActivity() != null && (getActivity() instanceof MusicHomeActivity) && ((MusicHomeActivity) getActivity()).isDialogOverlayActive();
    }

    private boolean isFacetedSeeMorePage(String uri) {
        return uri.contains("facet") && uri.split("/").length > 5;
    }
}
