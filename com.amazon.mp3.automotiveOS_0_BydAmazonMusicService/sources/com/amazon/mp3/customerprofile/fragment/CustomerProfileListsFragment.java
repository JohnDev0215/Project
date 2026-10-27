package com.amazon.mp3.customerprofile.fragment;

import android.app.Application;
import android.content.Context;
import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.actionbar.view.BauhausActionBarView;
import com.amazon.mp3.activity.NavigationActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsBinderConfiguration;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsBinderProvider;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtil;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtilFactory;
import com.amazon.mp3.catalog.fragment.datasource.repository.PageDataRepository;
import com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelFactoryProvider;
import com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelProvider;
import com.amazon.mp3.customerprofile.CustomerProfileListsViewModel;
import com.amazon.mp3.customerprofile.CustomerProfileListsViewModelFactory;
import com.amazon.mp3.customerprofile.CustomerProfileNavigation;
import com.amazon.mp3.providers.MetadataContextMenuHandler;
import com.amazon.mp3.providers.MissingSDCardHandler;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.binders.UniversalBinder;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.providers.ContentEnabilityProvider;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CustomerProfileListsFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0016\u0010\u0014\u001a\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00160\u0015H\u0016J\n\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014J\n\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u001eH\u0014J\n\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\n\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\n\u0010#\u001a\u0004\u0018\u00010$H\u0014J\b\u0010%\u001a\u00020\u001eH\u0016J\b\u0010&\u001a\u00020'H\u0016J\u0010\u0010(\u001a\u00020\u00132\u0006\u0010)\u001a\u00020*H\u0016J\b\u0010+\u001a\u00020,H\u0014J\b\u0010-\u001a\u00020\u0013H\u0014R\u0016\u0010\u0007\u001a\n \b*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\f@VX\u0094\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/amazon/mp3/customerprofile/fragment/CustomerProfileListsFragment;", "Lcom/amazon/mp3/catalog/fragment/AbstractBaseViewsFragment;", "profileId", "", "listType", "Lcom/amazon/mp3/customerprofile/CustomerProfileNavigation$Companion$LIST_TYPE;", "(Ljava/lang/String;Lcom/amazon/mp3/customerprofile/CustomerProfileNavigation$Companion$LIST_TYPE;)V", "TAG", "kotlin.jvm.PlatformType", "customerProfileListsViewModel", "Lcom/amazon/mp3/customerprofile/CustomerProfileListsViewModel;", "<set-?>", "Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelProvider;", "pageModelProvider", "getPageModelProvider", "()Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelProvider;", "setPageModelProvider", "(Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelProvider;)V", "emitUiPageViewMetric", "", "getBinderList", "", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "getContentEnabilityProvider", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "getContextMenuProvider", "Lcom/amazon/mp3/providers/MetadataContextMenuHandler;", "getDiffUtilFactory", "Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsDiffUtilFactory;", "getLoadingViewId", "", "getMissingSDCardProvider", "Lcom/amazon/mp3/providers/MissingSDCardHandler;", "getPageDataRepository", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageDataRepository;", "getPageLoadLatencyCode", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/PageLoadLatencyCode;", "getPageSize", "getPageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "initPageModelProvider", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "requireNetworkConnection", "", "setupViews", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CustomerProfileListsFragment extends AbstractBaseViewsFragment {
    private final String TAG;
    private CustomerProfileListsViewModel customerProfileListsViewModel;
    private final CustomerProfileNavigation.Companion.LIST_TYPE listType;
    private PageViewModelProvider pageModelProvider;
    private final String profileId;

    /* JADX INFO: compiled from: CustomerProfileListsFragment.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CustomerProfileNavigation.Companion.LIST_TYPE.values().length];
            iArr[CustomerProfileNavigation.Companion.LIST_TYPE.FOLLOWERS.ordinal()] = 1;
            iArr[CustomerProfileNavigation.Companion.LIST_TYPE.FOLLOWING.ordinal()] = 2;
            iArr[CustomerProfileNavigation.Companion.LIST_TYPE.PLAYLISTS.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected ContentEnabilityProvider getContentEnabilityProvider() {
        return null;
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public MetadataContextMenuHandler getContextMenuProvider() {
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
    public PageDataRepository getPageDataRepository() {
        return null;
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public int getPageSize() {
        return 20;
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected boolean requireNetworkConnection() {
        return true;
    }

    public CustomerProfileListsFragment(String profileId, CustomerProfileNavigation.Companion.LIST_TYPE listType) {
        Intrinsics.checkNotNullParameter(profileId, "profileId");
        Intrinsics.checkNotNullParameter(listType, "listType");
        this.profileId = profileId;
        this.listType = listType;
        this.TAG = CustomerProfileListsFragment.class.getSimpleName();
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected PageViewModelProvider getPageModelProvider() {
        return this.pageModelProvider;
    }

    public void setPageModelProvider(PageViewModelProvider pageViewModelProvider) {
        this.pageModelProvider = pageViewModelProvider;
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected void setupViews() {
        super.setupViews();
        FragmentActivity activity = getActivity();
        String string = null;
        NavigationActivity navigationActivity = activity instanceof NavigationActivity ? (NavigationActivity) activity : null;
        if (navigationActivity == null) {
            return;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[this.listType.ordinal()];
        if (i == 1) {
            Context context = getContext();
            if (context != null) {
                string = context.getString(R.string.dmusic_profile_followers);
            }
        } else if (i == 2) {
            Context context2 = getContext();
            if (context2 != null) {
                string = context2.getString(R.string.dmusic_profile_following);
            }
        } else {
            if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
            Context context3 = getContext();
            if (context3 != null) {
                string = context3.getString(R.string.dmusic_profile_playlists);
            }
        }
        navigationActivity.setHeaderView(new BauhausActionBarView(navigationActivity, string));
        navigationActivity.requestHomeButtonAsBack();
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
        setPageModelProvider(pageViewModelFactoryProvider.getViewModel(this, new CustomerProfileListsViewModelFactory(application, pageDataRepository, true)));
        PageViewModelProvider pageModelProvider = getPageModelProvider();
        this.customerProfileListsViewModel = pageModelProvider instanceof CustomerProfileListsViewModel ? (CustomerProfileListsViewModel) pageModelProvider : null;
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public List<UniversalBinder<?, ?>> getBinderList() {
        BaseViewsBinderConfiguration viewsConfiguration = getViewsConfiguration();
        List<UniversalBinder<?, ?>> listBuild = viewsConfiguration != null ? BaseViewsBinderProvider.BinderListBuilder.withVisualRowItems$default(new BaseViewsBinderProvider.BinderListBuilder(viewsConfiguration).withDefaultHeader(new LinkedHashMap(), getPageType()), null, null, 3, null).build() : null;
        return listBuild == null ? CollectionsKt__CollectionsKt.emptyList() : listBuild;
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public BaseViewsDiffUtilFactory getDiffUtilFactory() {
        return new BaseViewsDiffUtilFactory() { // from class: com.amazon.mp3.customerprofile.fragment.CustomerProfileListsFragment.getDiffUtilFactory.1
            @Override // com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtilFactory
            public BaseViewsDiffUtil getBaseViewsDiffUtil(List<? extends BaseViewModel> oldList, List<? extends BaseViewModel> newList) {
                Intrinsics.checkNotNullParameter(oldList, "oldList");
                Intrinsics.checkNotNullParameter(newList, "newList");
                return new BaseViewsDiffUtil(oldList, newList);
            }
        };
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public PageType getPageType() {
        return PageType.CUSTOMER_PROFILE;
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public void emitUiPageViewMetric() {
        AbstractBaseViewsFragment.sendUiPageViewMetric$default(this, getPageType(), null, null, null, null, null, null, null, 254, null);
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected PageLoadLatencyCode getPageLoadLatencyCode() {
        return PageLoadLatencyCode.CUSTOMER_PROFILE;
    }
}
