package com.amazon.mp3.customerprofile.fragment;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.core.view.KeyEventDispatcher;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.actionbar.view.BauhausActionBarView;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.activity.NavigationActivity;
import com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsBinderConfiguration;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsBinderProvider;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtil;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtilFactory;
import com.amazon.mp3.catalog.fragment.datasource.repository.PageDataRepository;
import com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelFactoryProvider;
import com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelProvider;
import com.amazon.mp3.customerprofile.CustomerProfileClickHandler;
import com.amazon.mp3.customerprofile.CustomerProfileDataProvider;
import com.amazon.mp3.customerprofile.CustomerProfileDetailManager;
import com.amazon.mp3.customerprofile.CustomerProfileNavigation;
import com.amazon.mp3.customerprofile.ShareProfileProviderImpl;
import com.amazon.mp3.customerprofile.viewModels.CustomerProfileViewModel;
import com.amazon.mp3.customerprofile.viewModels.CustomerProfileViewModelFactory;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.library.provider.LibraryStateProvider;
import com.amazon.mp3.playback.state.PageStateManager;
import com.amazon.mp3.providers.CatalogEnabilityProvider;
import com.amazon.mp3.providers.DefaultContextMenuProvider;
import com.amazon.mp3.providers.MetadataContextMenuHandler;
import com.amazon.mp3.providers.MissingSDCardHandler;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageSubType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.uicontentview.ContentViewManager;
import com.amazon.music.views.library.binders.CustomerProfileBinder;
import com.amazon.music.views.library.binders.ImageBinder;
import com.amazon.music.views.library.binders.UniversalBinder;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.providers.ContentEnabilityProvider;
import com.amazon.music.views.library.providers.SeeMoreProvider;
import com.amazon.music.views.library.views.presentation.ActionBarOverlayable;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CustomerProfileFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0089\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t*\u0001\u0014\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\u0016\u0010\u0019\u001a\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u001b0\u001aH\u0016J\n\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014J\n\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\b\u0010 \u001a\u00020!H\u0016J\n\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\n\u0010$\u001a\u0004\u0018\u00010%H\u0016J\n\u0010&\u001a\u0004\u0018\u00010'H\u0014J\u0006\u0010(\u001a\u00020\u0004J\b\u0010)\u001a\u00020*H\u0016J\b\u0010+\u001a\u0004\u0018\u00010\u0004J\u0010\u0010,\u001a\u00020\u00182\u0006\u0010-\u001a\u00020.H\u0016J\b\u0010/\u001a\u000200H\u0002J\u0006\u00101\u001a\u000200J\u0012\u00102\u001a\u00020\u00182\b\u00103\u001a\u0004\u0018\u000104H\u0016J\u0012\u00105\u001a\u00020\u00182\b\u00103\u001a\u0004\u0018\u000104H\u0016J\b\u00106\u001a\u00020\u0018H\u0016J\b\u00107\u001a\u00020\u0018H\u0016J\b\u00108\u001a\u000200H\u0014J\u0012\u00109\u001a\u00020\u00182\b\u0010:\u001a\u0004\u0018\u00010\u000bH\u0002J\u0010\u0010;\u001a\u00020\u00182\u0006\u0010<\u001a\u000200H\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\u0010\f\u001a\u0004\u0018\u00010\r@VX\u0094\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0010\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0015R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006="}, d2 = {"Lcom/amazon/mp3/customerprofile/fragment/CustomerProfileFragment;", "Lcom/amazon/mp3/catalog/fragment/AbstractBaseViewsFragment;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "customerProfileDetailManager", "Lcom/amazon/mp3/customerprofile/CustomerProfileDetailManager;", "customerProfileViewModel", "Lcom/amazon/mp3/customerprofile/viewModels/CustomerProfileViewModel;", "mActionBarOnScrollListener", "Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;", "<set-?>", "Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelProvider;", "pageModelProvider", "getPageModelProvider", "()Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelProvider;", "setPageModelProvider", "(Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelProvider;)V", "playlistsSeeMoreProvider", "com/amazon/mp3/customerprofile/fragment/CustomerProfileFragment$playlistsSeeMoreProvider$1", "Lcom/amazon/mp3/customerprofile/fragment/CustomerProfileFragment$playlistsSeeMoreProvider$1;", "profileId", "emitUiPageViewMetric", "", "getBinderList", "", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "getContentEnabilityProvider", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "getContextMenuProvider", "Lcom/amazon/mp3/providers/MetadataContextMenuHandler;", "getDiffUtilFactory", "Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsDiffUtilFactory;", "getMissingSDCardProvider", "Lcom/amazon/mp3/providers/MissingSDCardHandler;", "getPageDataRepository", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageDataRepository;", "getPageLoadLatencyCode", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/PageLoadLatencyCode;", "getPageSubType", "getPageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "getProfileId", "initPageModelProvider", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "isBauhausTopAppBarEnabled", "", "isSelfView", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onCreate", "onResume", "onStop", "requireNetworkConnection", "setActionBarOnScrollListener", "scrollListener", "updateActionBarOverlayState", "isOverlay", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CustomerProfileFragment extends AbstractBaseViewsFragment {
    private final CustomerProfileDetailManager customerProfileDetailManager;
    private CustomerProfileViewModel customerProfileViewModel;
    private RecyclerView.OnScrollListener mActionBarOnScrollListener;
    private PageViewModelProvider pageModelProvider;
    private final CustomerProfileFragment$playlistsSeeMoreProvider$1 playlistsSeeMoreProvider = new SeeMoreProvider() { // from class: com.amazon.mp3.customerprofile.fragment.CustomerProfileFragment$playlistsSeeMoreProvider$1
        @Override // com.amazon.music.views.library.providers.SeeMoreProvider
        public void onSeeMoreClick(String uri, String viewId, String nextPageToken) {
            FragmentActivity activity = this.this$0.getActivity();
            MusicHomeActivity musicHomeActivity = activity instanceof MusicHomeActivity ? (MusicHomeActivity) activity : null;
            CustomerProfileNavigation customerProfileNavigation = musicHomeActivity != null ? new CustomerProfileNavigation(musicHomeActivity) : null;
            if (customerProfileNavigation == null) {
                return;
            }
            String profileId = this.this$0.getProfileId();
            if (profileId == null) {
                profileId = "";
            }
            customerProfileNavigation.loadPlaylists(profileId);
        }
    };
    private String profileId = "";
    private final String TAG = CustomerProfileFragment.class.getSimpleName();

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public MissingSDCardHandler getMissingSDCardProvider() {
        return null;
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected boolean requireNetworkConnection() {
        return true;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.amazon.mp3.customerprofile.fragment.CustomerProfileFragment$playlistsSeeMoreProvider$1] */
    public CustomerProfileFragment() {
        CustomerProfileDetailManager customerProfileDetailManager = CustomerProfileDetailManager.getInstance();
        Intrinsics.checkNotNullExpressionValue(customerProfileDetailManager, "getInstance()");
        this.customerProfileDetailManager = customerProfileDetailManager;
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected PageViewModelProvider getPageModelProvider() {
        return this.pageModelProvider;
    }

    public void setPageModelProvider(PageViewModelProvider pageViewModelProvider) {
        this.pageModelProvider = pageViewModelProvider;
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        if (arguments != null && !TextUtils.isEmpty(arguments.getString("brush_profileid_extra"))) {
            this.profileId = arguments.getString("brush_profileid_extra");
        }
        super.onCreate(savedInstanceState);
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentActivity activity = getActivity();
        NavigationActivity navigationActivity = activity instanceof NavigationActivity ? (NavigationActivity) activity : null;
        if (navigationActivity == null) {
            return;
        }
        BauhausActionBarView bauhausActionBarView = new BauhausActionBarView(navigationActivity, null, null, null, null, null, 62, null);
        navigationActivity.setHeaderView(bauhausActionBarView);
        navigationActivity.requestHomeButtonAsBack();
        setActionBarOnScrollListener(bauhausActionBarView.getOnScrollListener());
        updateActionBarOverlayState(true);
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
        setPageModelProvider(pageViewModelFactoryProvider.getViewModel(this, new CustomerProfileViewModelFactory(application, pageDataRepository)));
        PageViewModelProvider pageModelProvider = getPageModelProvider();
        this.customerProfileViewModel = pageModelProvider instanceof CustomerProfileViewModel ? (CustomerProfileViewModel) pageModelProvider : null;
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public PageDataRepository getPageDataRepository() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return null;
        }
        return new PageDataRepository(new CustomerProfileDataProvider(activity, this.profileId, getPageLoadLatencyCode()));
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public List<UniversalBinder<?, ?>> getBinderList() {
        BaseViewsBinderConfiguration viewsConfiguration = getViewsConfiguration();
        if (viewsConfiguration != null) {
            ImageBinder imageBinder = new ImageBinder(viewsConfiguration.getContext(), getStyleSheet(), viewsConfiguration.getImageLoader());
            FragmentActivity fragmentActivityRequireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity()");
            CustomerProfileClickHandler customerProfileClickHandler = new CustomerProfileClickHandler(fragmentActivityRequireActivity);
            boolean zIsEnabled = Feature.social_profiles_phase3_enabled.isEnabled();
            CustomerProfileBinder customerProfileBinder = new CustomerProfileBinder(getStyleSheet(), imageBinder, new ShareProfileProviderImpl(viewsConfiguration.getContext()), customerProfileClickHandler, zIsEnabled, zIsEnabled);
            return CollectionsKt___CollectionsKt.plus((Collection) CollectionsKt__CollectionsKt.listOfNotNull(customerProfileBinder), (Iterable) new BaseViewsBinderProvider.BinderListBuilder(viewsConfiguration).withShovelers(new BaseViewsBinderProvider.BinderListBuilder.ShovelerBuilder(new ContentViewManager(), this.playlistsSeeMoreProvider, null, Boolean.TRUE, null, null, null, null, null, 500, null)).build());
        }
        return CollectionsKt__CollectionsKt.emptyList();
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public MetadataContextMenuHandler getContextMenuProvider() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return null;
        }
        if (getContextMenuHandler() == null) {
            Context contextRequireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext, "this.requireContext()");
            setContextMenuHandler(new DefaultContextMenuProvider(activity, this, new LibraryStateProvider(contextRequireContext), getPageType(), new PageStateManager()));
        }
        return getContextMenuHandler();
    }

    public final String getProfileId() {
        return isSelfView() ? this.customerProfileDetailManager.getProfileId() : this.profileId;
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public PageType getPageType() {
        return PageType.CUSTOMER_PROFILE;
    }

    public final String getPageSubType() {
        String metricsValue;
        String str;
        if (isSelfView()) {
            metricsValue = PageSubType.SELF_PROFILE.getMetricsValue();
            str = "SELF_PROFILE.getMetricValue()";
        } else {
            metricsValue = PageSubType.PEER_PROFILE.getMetricsValue();
            str = "PEER_PROFILE.getMetricValue()";
        }
        Intrinsics.checkNotNullExpressionValue(metricsValue, str);
        return metricsValue;
    }

    public final boolean isSelfView() {
        String str = this.profileId;
        return (str == null || str.length() == 0) || Intrinsics.areEqual(this.profileId, this.customerProfileDetailManager.getProfileId());
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public BaseViewsDiffUtilFactory getDiffUtilFactory() {
        return new BaseViewsDiffUtilFactory() { // from class: com.amazon.mp3.customerprofile.fragment.CustomerProfileFragment.getDiffUtilFactory.1
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
        AbstractBaseViewsFragment.sendUiPageViewMetric$default(this, getPageType(), getPageSubType(), getProfileId(), EntityIdType.PROFILE_ID.toString(), null, null, null, null, 240, null);
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected ContentEnabilityProvider getContentEnabilityProvider() {
        return new CatalogEnabilityProvider();
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected PageLoadLatencyCode getPageLoadLatencyCode() {
        return PageLoadLatencyCode.CUSTOMER_PROFILE;
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        updateActionBarOverlayState(false);
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        updateActionBarOverlayState(true);
    }

    private final void setActionBarOnScrollListener(RecyclerView.OnScrollListener scrollListener) {
        RecyclerView recyclerView;
        RecyclerView recyclerView2;
        RecyclerView.OnScrollListener onScrollListener = this.mActionBarOnScrollListener;
        if (onScrollListener != null && (recyclerView2 = getRecyclerView()) != null) {
            recyclerView2.removeOnScrollListener(onScrollListener);
        }
        if (scrollListener != null && (recyclerView = getRecyclerView()) != null) {
            recyclerView.addOnScrollListener(scrollListener);
        }
        this.mActionBarOnScrollListener = scrollListener;
    }

    private final boolean isBauhausTopAppBarEnabled() {
        return AmazonApplication.getCapabilities().isBauhausTopAppBarEnabled();
    }

    private final void updateActionBarOverlayState(boolean isOverlay) {
        KeyEventDispatcher.Component activity = getActivity();
        ActionBarOverlayable actionBarOverlayable = activity instanceof ActionBarOverlayable ? (ActionBarOverlayable) activity : null;
        if (actionBarOverlayable == null) {
            return;
        }
        actionBarOverlayable.setActionBarOverlay(isOverlay & isBauhausTopAppBarEnabled());
    }
}
