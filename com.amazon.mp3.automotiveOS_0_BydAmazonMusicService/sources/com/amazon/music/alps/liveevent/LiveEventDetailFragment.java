package com.amazon.music.alps.liveevent;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.core.view.KeyEventDispatcher;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.music.alps.BaseFragment;
import com.amazon.music.alps.R$id;
import com.amazon.music.alps.R$layout;
import com.amazon.music.alps.liveevent.binder.EventHeaderBinder;
import com.amazon.music.alps.liveevent.binder.IconLinkItemBinder;
import com.amazon.music.alps.liveevent.binder.NewEventHeaderBinder;
import com.amazon.music.alps.liveevent.binder.VerticalIconLinkItemBinder;
import com.amazon.music.alps.liveevent.converters.LiveEventConverterFactory;
import com.amazon.music.alps.liveevent.data.ArtistMerchRepository;
import com.amazon.music.alps.liveevent.data.FireFlyArtistMerchDataSource;
import com.amazon.music.alps.liveevent.data.FireFlyLiveEventDataSource;
import com.amazon.music.alps.liveevent.data.LiveEventRepository;
import com.amazon.music.alps.liveevent.ktx.LiveEventKt;
import com.amazon.music.alps.liveevent.view.EventHeaderView;
import com.amazon.music.alps.liveevent.view.NewEventHeaderView;
import com.amazon.music.alps.liveevent.viewmodel.LiveEventDetailViewModel;
import com.amazon.music.alps.liveevent.viewmodel.LiveEventDetailViewModelFactory;
import com.amazon.music.alps.providers.ChromeProvider;
import com.amazon.music.alps.providers.SharingProvider;
import com.amazon.music.alps.util.Event;
import com.amazon.music.alps.util.FadingBackdropHelper;
import com.amazon.music.deeplink.DeeplinksManager;
import com.amazon.music.deeplink.UnknownDeeplinkException;
import com.amazon.music.destination.LiveEventLineupsDestination;
import com.amazon.music.find.skyfire.Contexts;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContainerType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContentName;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.graphql.generated.fragment.LiveEvent;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.metrics.event.UIContentViewWrapper;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.platform.providers.MetricsProvider;
import com.amazon.music.router.UnknownDestinationException;
import com.amazon.music.skyfire.ui.skyfire.Contexts;
import com.amazon.music.subscription.UserSubscription;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.presentation.ActionBarOverlayable;
import com.amazon.music.views.library.views.presentation.TranslucentActionBarDecorator;
import com.amazon.ui.foundations.ktx.ViewKt;
import dagger.android.support.AndroidSupportInjection;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: LiveEventDetailFragment.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 M2\u00020\u0001:\u0001MB\u0007¢\u0006\u0004\bK\u0010LJ\b\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0002J\u0018\u0010\t\u001a\u00020\u00042\u000e\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u0006H\u0002J\b\u0010\n\u001a\u00020\u0004H\u0002J\u0012\u0010\r\u001a\u00020\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0016J$\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u000e2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0016J\u001a\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00122\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0016J\b\u0010\u0016\u001a\u00020\u0004H\u0016R\"\u0010\u0018\u001a\u00020\u00178\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\"\u0010\u001f\u001a\u00020\u001e8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\"\u0010&\u001a\u00020%8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R(\u0010.\u001a\b\u0012\u0004\u0012\u00020-0,8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b.\u0010/\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u0018\u00104\u001a\u0004\u0018\u00010-8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b4\u00105R\u0016\u00106\u001a\u00020\u00028\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b6\u00107R\u0016\u00108\u001a\u00020\u00128\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b8\u00109R\u0016\u0010:\u001a\u00020\u00128\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b:\u00109R\u0016\u0010;\u001a\u00020\u00128\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b;\u00109R\u0016\u0010=\u001a\u00020<8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b=\u0010>R\u0016\u0010@\u001a\u00020?8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b@\u0010AR\u0016\u0010C\u001a\u00020B8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bC\u0010DR\u0016\u0010F\u001a\u00020E8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bF\u0010GR\u0016\u0010I\u001a\u00020H8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bI\u0010J¨\u0006N"}, d2 = {"Lcom/amazon/music/alps/liveevent/LiveEventDetailFragment;", "Lcom/amazon/music/alps/BaseFragment;", "Lcom/amazon/music/views/library/views/presentation/TranslucentActionBarDecorator;", "createActionBar", "", "observeViewModel", "Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "pageGridModel", "setupViews", "fetchData", "Landroid/os/Bundle;", "savedInstanceState", "onCreate", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "Landroid/view/View;", "onCreateView", "view", "onViewCreated", "onDestroyView", "Lcom/amazon/music/subscription/UserSubscription;", "userSubscription", "Lcom/amazon/music/subscription/UserSubscription;", "getUserSubscription", "()Lcom/amazon/music/subscription/UserSubscription;", "setUserSubscription", "(Lcom/amazon/music/subscription/UserSubscription;)V", "Lcom/amazon/music/alps/providers/SharingProvider;", "sharingProvider", "Lcom/amazon/music/alps/providers/SharingProvider;", "getSharingProvider", "()Lcom/amazon/music/alps/providers/SharingProvider;", "setSharingProvider", "(Lcom/amazon/music/alps/providers/SharingProvider;)V", "Lcom/amazon/music/alps/providers/ChromeProvider;", "chromeProvider", "Lcom/amazon/music/alps/providers/ChromeProvider;", "getChromeProvider", "()Lcom/amazon/music/alps/providers/ChromeProvider;", "setChromeProvider", "(Lcom/amazon/music/alps/providers/ChromeProvider;)V", "Lrx/Observable;", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheetObservable", "Lrx/Observable;", "getStyleSheetObservable", "()Lrx/Observable;", "setStyleSheetObservable", "(Lrx/Observable;)V", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "actionBarView", "Lcom/amazon/music/views/library/views/presentation/TranslucentActionBarDecorator;", "rootView", "Landroid/view/View;", "loadingView", "errorView", "Landroid/widget/ImageView;", "backdropView", "Landroid/widget/ImageView;", "Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout;", "swipeRefreshView", "Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout;", "Landroidx/recyclerview/widget/RecyclerView;", "mainContentView", "Landroidx/recyclerview/widget/RecyclerView;", "Lcom/amazon/music/alps/liveevent/viewmodel/LiveEventDetailViewModel;", "viewModel", "Lcom/amazon/music/alps/liveevent/viewmodel/LiveEventDetailViewModel;", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "<init>", "()V", "Companion", "DMMALPS_release"}, k = 1, mv = {1, 6, 0})
public final class LiveEventDetailFragment extends BaseFragment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private TranslucentActionBarDecorator actionBarView;
    private ImageView backdropView;
    public ChromeProvider chromeProvider;
    private View errorView;
    private View loadingView;
    private RecyclerView mainContentView;
    private PageType pageType;
    private View rootView;
    public SharingProvider sharingProvider;
    private StyleSheet styleSheet;
    public Observable<StyleSheet> styleSheetObservable;
    private SwipeRefreshLayout swipeRefreshView;
    public UserSubscription userSubscription;
    private LiveEventDetailViewModel viewModel;

    public final UserSubscription getUserSubscription() {
        UserSubscription userSubscription = this.userSubscription;
        if (userSubscription != null) {
            return userSubscription;
        }
        Intrinsics.throwUninitializedPropertyAccessException("userSubscription");
        return null;
    }

    public final SharingProvider getSharingProvider() {
        SharingProvider sharingProvider = this.sharingProvider;
        if (sharingProvider != null) {
            return sharingProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("sharingProvider");
        return null;
    }

    public final ChromeProvider getChromeProvider() {
        ChromeProvider chromeProvider = this.chromeProvider;
        if (chromeProvider != null) {
            return chromeProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("chromeProvider");
        return null;
    }

    public final Observable<StyleSheet> getStyleSheetObservable() {
        Observable<StyleSheet> observable = this.styleSheetObservable;
        if (observable != null) {
            return observable;
        }
        Intrinsics.throwUninitializedPropertyAccessException("styleSheetObservable");
        return null;
    }

    @Override // com.amazon.music.alps.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        PageType pageType = null;
        int i = 1;
        LiveEventModelUseCase liveEventModelUseCase = new LiveEventModelUseCase(new LiveEventRepository(new FireFlyLiveEventDataSource(0 == true ? 1 : 0, i, 0 == true ? 1 : 0)), new ArtistMerchRepository(new FireFlyArtistMerchDataSource(0 == true ? 1 : 0, i, 0 == true ? 1 : 0)), new LiveEventConverterFactory(contextRequireContext));
        Bundle arguments = getArguments();
        String string = arguments == null ? null : arguments.getString(Contexts.OpenAlexa.pageType);
        if (string == null) {
            string = "";
        }
        PageType pageTypeValueOf = PageType.valueOf(string);
        this.pageType = pageTypeValueOf;
        if (pageTypeValueOf == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pageType");
        } else {
            pageType = pageTypeValueOf;
        }
        this.viewModel = (LiveEventDetailViewModel) ViewModelProviders.of(this, new LiveEventDetailViewModelFactory(liveEventModelUseCase, pageType, getRefMarker(), getAssociateTag())).get(LiveEventDetailViewModel.class);
        fetchData();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R$layout.live_event_detail_fragment, container, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflater.inflate(R.layou…agment, container, false)");
        this.rootView = viewInflate;
        if (viewInflate != null) {
            return viewInflate;
        }
        Intrinsics.throwUninitializedPropertyAccessException("rootView");
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        KeyEventDispatcher.Component activity = getActivity();
        RecyclerView recyclerView = null;
        ActionBarOverlayable actionBarOverlayable = activity instanceof ActionBarOverlayable ? (ActionBarOverlayable) activity : null;
        if (actionBarOverlayable != null) {
            actionBarOverlayable.setActionBarOverlay(true);
        }
        this.actionBarView = createActionBar();
        View view2 = this.rootView;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
            view2 = null;
        }
        View viewFindViewById = view2.findViewById(R$id.loading_view);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "rootView.findViewById(R.id.loading_view)");
        this.loadingView = viewFindViewById;
        View view3 = this.rootView;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
            view3 = null;
        }
        View viewFindViewById2 = view3.findViewById(R$id.error_view);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "rootView.findViewById(R.id.error_view)");
        this.errorView = viewFindViewById2;
        View view4 = this.rootView;
        if (view4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
            view4 = null;
        }
        View viewFindViewById3 = view4.findViewById(R$id.backdrop_view);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "rootView.findViewById(R.id.backdrop_view)");
        this.backdropView = (ImageView) viewFindViewById3;
        View view5 = this.rootView;
        if (view5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
            view5 = null;
        }
        View viewFindViewById4 = view5.findViewById(R$id.swipe_refresh_layout);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "rootView.findViewById(R.id.swipe_refresh_layout)");
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) viewFindViewById4;
        this.swipeRefreshView = swipeRefreshLayout;
        if (swipeRefreshLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("swipeRefreshView");
            swipeRefreshLayout = null;
        }
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.amazon.music.alps.liveevent.LiveEventDetailFragment$$ExternalSyntheticLambda0
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                LiveEventDetailFragment.m1857onViewCreated$lambda0(this.f$0);
            }
        });
        View view6 = this.rootView;
        if (view6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
            view6 = null;
        }
        View viewFindViewById5 = view6.findViewById(R$id.main_content);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "rootView.findViewById(R.id.main_content)");
        RecyclerView recyclerView2 = (RecyclerView) viewFindViewById5;
        this.mainContentView = recyclerView2;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mainContentView");
            recyclerView2 = null;
        }
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView recyclerView3 = this.mainContentView;
        if (recyclerView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mainContentView");
        } else {
            recyclerView = recyclerView3;
        }
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.amazon.music.alps.liveevent.LiveEventDetailFragment.onViewCreated.2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView rv, int dx, int dy) {
                Intrinsics.checkNotNullParameter(rv, "rv");
                TranslucentActionBarDecorator translucentActionBarDecorator = LiveEventDetailFragment.this.actionBarView;
                if (translucentActionBarDecorator == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("actionBarView");
                    translucentActionBarDecorator = null;
                }
                translucentActionBarDecorator.updateBackgroundOpacity(rv);
            }
        });
        observeViewModel();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-0, reason: not valid java name */
    public static final void m1857onViewCreated$lambda0(LiveEventDetailFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.fetchData();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        KeyEventDispatcher.Component activity = getActivity();
        ActionBarOverlayable actionBarOverlayable = activity instanceof ActionBarOverlayable ? (ActionBarOverlayable) activity : null;
        if (actionBarOverlayable != null) {
            actionBarOverlayable.setActionBarOverlay(false);
        }
        super.onDestroyView();
    }

    private final TranslucentActionBarDecorator createActionBar() {
        ChromeProvider chromeProvider = getChromeProvider();
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity()");
        return chromeProvider.createTranslucentActionBarDecorator(fragmentActivityRequireActivity, false, Boolean.FALSE);
    }

    private final void observeViewModel() {
        getStyleSheetObservable().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.music.alps.liveevent.LiveEventDetailFragment$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                LiveEventDetailFragment.m1847observeViewModel$lambda1(this.f$0, (StyleSheet) obj);
            }
        });
        LiveEventDetailViewModel liveEventDetailViewModel = this.viewModel;
        LiveEventDetailViewModel liveEventDetailViewModel2 = null;
        if (liveEventDetailViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            liveEventDetailViewModel = null;
        }
        liveEventDetailViewModel.getDataLoading().observe(getViewLifecycleOwner(), new Observer() { // from class: com.amazon.music.alps.liveevent.LiveEventDetailFragment$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LiveEventDetailFragment.m1852observeViewModel$lambda2(this.f$0, (Boolean) obj);
            }
        });
        LiveEventDetailViewModel liveEventDetailViewModel3 = this.viewModel;
        if (liveEventDetailViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            liveEventDetailViewModel3 = null;
        }
        liveEventDetailViewModel3.getError().observe(getViewLifecycleOwner(), new Observer() { // from class: com.amazon.music.alps.liveevent.LiveEventDetailFragment$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LiveEventDetailFragment.m1853observeViewModel$lambda3(this.f$0, (Boolean) obj);
            }
        });
        LiveEventDetailViewModel liveEventDetailViewModel4 = this.viewModel;
        if (liveEventDetailViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            liveEventDetailViewModel4 = null;
        }
        liveEventDetailViewModel4.getBackdropImage().observe(getViewLifecycleOwner(), new Observer() { // from class: com.amazon.music.alps.liveevent.LiveEventDetailFragment$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LiveEventDetailFragment.m1854observeViewModel$lambda5(this.f$0, (String) obj);
            }
        });
        LiveEventDetailViewModel liveEventDetailViewModel5 = this.viewModel;
        if (liveEventDetailViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            liveEventDetailViewModel5 = null;
        }
        liveEventDetailViewModel5.getPageGridModel().observe(getViewLifecycleOwner(), new Observer() { // from class: com.amazon.music.alps.liveevent.LiveEventDetailFragment$$ExternalSyntheticLambda5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LiveEventDetailFragment.m1855observeViewModel$lambda6(this.f$0, (BaseViewContainerModel) obj);
            }
        });
        LiveEventDetailViewModel liveEventDetailViewModel6 = this.viewModel;
        if (liveEventDetailViewModel6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            liveEventDetailViewModel6 = null;
        }
        liveEventDetailViewModel6.getOpenUri().observe(getViewLifecycleOwner(), new Observer() { // from class: com.amazon.music.alps.liveevent.LiveEventDetailFragment$$ExternalSyntheticLambda6
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) throws ExecutionException, InterruptedException, MalformedURLException, UnknownDeeplinkException, URISyntaxException, UnsupportedEncodingException, UnknownDestinationException {
                LiveEventDetailFragment.m1856observeViewModel$lambda8(this.f$0, (Event) obj);
            }
        });
        LiveEventDetailViewModel liveEventDetailViewModel7 = this.viewModel;
        if (liveEventDetailViewModel7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            liveEventDetailViewModel7 = null;
        }
        liveEventDetailViewModel7.getOpenExternalUri().observe(getViewLifecycleOwner(), new Observer() { // from class: com.amazon.music.alps.liveevent.LiveEventDetailFragment$$ExternalSyntheticLambda7
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LiveEventDetailFragment.m1848observeViewModel$lambda11(this.f$0, (Event) obj);
            }
        });
        LiveEventDetailViewModel liveEventDetailViewModel8 = this.viewModel;
        if (liveEventDetailViewModel8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            liveEventDetailViewModel8 = null;
        }
        liveEventDetailViewModel8.getOpenCalendar().observe(getViewLifecycleOwner(), new Observer() { // from class: com.amazon.music.alps.liveevent.LiveEventDetailFragment$$ExternalSyntheticLambda8
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LiveEventDetailFragment.m1849observeViewModel$lambda12(this.f$0, (Event) obj);
            }
        });
        LiveEventDetailViewModel liveEventDetailViewModel9 = this.viewModel;
        if (liveEventDetailViewModel9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            liveEventDetailViewModel9 = null;
        }
        liveEventDetailViewModel9.getOpenShare().observe(getViewLifecycleOwner(), new Observer() { // from class: com.amazon.music.alps.liveevent.LiveEventDetailFragment$$ExternalSyntheticLambda9
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LiveEventDetailFragment.m1850observeViewModel$lambda14(this.f$0, (Event) obj);
            }
        });
        LiveEventDetailViewModel liveEventDetailViewModel10 = this.viewModel;
        if (liveEventDetailViewModel10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            liveEventDetailViewModel2 = liveEventDetailViewModel10;
        }
        liveEventDetailViewModel2.getOpenShareExperiment().observe(getViewLifecycleOwner(), new Observer() { // from class: com.amazon.music.alps.liveevent.LiveEventDetailFragment$$ExternalSyntheticLambda10
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LiveEventDetailFragment.m1851observeViewModel$lambda16(this.f$0, (Event) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: observeViewModel$lambda-1, reason: not valid java name */
    public static final void m1847observeViewModel$lambda1(LiveEventDetailFragment this$0, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.styleSheet = styleSheet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:14:0x002c  */
    /* JADX INFO: renamed from: observeViewModel$lambda-2, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m1852observeViewModel$lambda2(com.amazon.music.alps.liveevent.LiveEventDetailFragment r5, java.lang.Boolean r6) {
        /*
            java.lang.String r0 = "this$0"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            android.view.View r0 = r5.loadingView
            r1 = 0
            if (r0 != 0) goto L11
            java.lang.String r0 = "loadingView"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r0 = r1
        L11:
            boolean r2 = r6.booleanValue()
            r3 = 0
            java.lang.String r4 = "swipeRefreshView"
            if (r2 == 0) goto L2c
            androidx.swiperefreshlayout.widget.SwipeRefreshLayout r2 = r5.swipeRefreshView
            if (r2 != 0) goto L23
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r2 = r1
        L23:
            boolean r2 = r2.isRefreshing()
            if (r2 == 0) goto L2a
            goto L2c
        L2a:
            r2 = r3
            goto L2d
        L2c:
            r2 = 1
        L2d:
            com.amazon.ui.foundations.ktx.ViewKt.collapseIf(r0, r2)
            boolean r6 = r6.booleanValue()
            if (r6 != 0) goto L42
            androidx.swiperefreshlayout.widget.SwipeRefreshLayout r5 = r5.swipeRefreshView
            if (r5 != 0) goto L3e
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            goto L3f
        L3e:
            r1 = r5
        L3f:
            r1.setRefreshing(r3)
        L42:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.alps.liveevent.LiveEventDetailFragment.m1852observeViewModel$lambda2(com.amazon.music.alps.liveevent.LiveEventDetailFragment, java.lang.Boolean):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: observeViewModel$lambda-3, reason: not valid java name */
    public static final void m1853observeViewModel$lambda3(LiveEventDetailFragment this$0, Boolean it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        RecyclerView recyclerView = this$0.mainContentView;
        View view = null;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mainContentView");
            recyclerView = null;
        }
        Intrinsics.checkNotNullExpressionValue(it, "it");
        ViewKt.collapseIf(recyclerView, it.booleanValue());
        View view2 = this$0.errorView;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("errorView");
        } else {
            view = view2;
        }
        ViewKt.collapseIf(view, !it.booleanValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: observeViewModel$lambda-5, reason: not valid java name */
    public static final void m1854observeViewModel$lambda5(LiveEventDetailFragment this$0, String imageUrl) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        StyleSheet styleSheet = this$0.styleSheet;
        if (styleSheet == null) {
            return;
        }
        ImageView imageView = null;
        RecyclerView recyclerView = null;
        if (!(imageUrl == null || imageUrl.length() == 0)) {
            ImageView imageView2 = this$0.backdropView;
            if (imageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("backdropView");
                imageView2 = null;
            }
            RecyclerView recyclerView2 = this$0.mainContentView;
            if (recyclerView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mainContentView");
            } else {
                recyclerView = recyclerView2;
            }
            FadingBackdropHelper fadingBackdropHelper = new FadingBackdropHelper(imageView2, recyclerView, styleSheet);
            Intrinsics.checkNotNullExpressionValue(imageUrl, "imageUrl");
            fadingBackdropHelper.setBackdropURL(imageUrl);
            return;
        }
        ImageView imageView3 = this$0.backdropView;
        if (imageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("backdropView");
        } else {
            imageView = imageView3;
        }
        imageView.setVisibility(4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: observeViewModel$lambda-6, reason: not valid java name */
    public static final void m1855observeViewModel$lambda6(LiveEventDetailFragment this$0, BaseViewContainerModel it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.setupViews(it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: observeViewModel$lambda-8, reason: not valid java name */
    public static final void m1856observeViewModel$lambda8(LiveEventDetailFragment this$0, Event event) throws ExecutionException, InterruptedException, MalformedURLException, UnknownDeeplinkException, URISyntaxException, UnsupportedEncodingException, UnknownDestinationException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Uri uri = (Uri) event.getContentIfNotHandled();
        if (uri == null) {
            return;
        }
        DeeplinksManager.get(this$0.requireContext()).handle(uri);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: observeViewModel$lambda-11, reason: not valid java name */
    public static final void m1848observeViewModel$lambda11(LiveEventDetailFragment this$0, Event event) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Uri uri = (Uri) event.getContentIfNotHandled();
        if (uri == null) {
            return;
        }
        Intent intent = new Intent("android.intent.action.VIEW", uri);
        intent.setFlags(268435456);
        this$0.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: observeViewModel$lambda-12, reason: not valid java name */
    public static final void m1849observeViewModel$lambda12(LiveEventDetailFragment this$0, Event event) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LiveEvent liveEvent = (LiveEvent) event.getContentIfNotHandled();
        if (liveEvent == null) {
            return;
        }
        Context contextRequireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        LiveEventKt.createCalendarEvent(liveEvent, contextRequireContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: observeViewModel$lambda-14, reason: not valid java name */
    public static final void m1850observeViewModel$lambda14(LiveEventDetailFragment this$0, Event event) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SharingProvider.ShareContent shareContent = (SharingProvider.ShareContent) event.getContentIfNotHandled();
        if (shareContent == null) {
            return;
        }
        SharingProvider sharingProvider = this$0.getSharingProvider();
        Context contextRequireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        sharingProvider.share(contextRequireContext, shareContent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: observeViewModel$lambda-16, reason: not valid java name */
    public static final void m1851observeViewModel$lambda16(LiveEventDetailFragment this$0, Event event) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SharingProvider.ShareContent shareContent = (SharingProvider.ShareContent) event.getContentIfNotHandled();
        if (shareContent == null) {
            return;
        }
        SharingProvider sharingProvider = this$0.getSharingProvider();
        Context contextRequireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        sharingProvider.share(contextRequireContext, shareContent);
    }

    private final void setupViews(BaseViewContainerModel<? extends BaseViewModel> pageGridModel) {
        LiveEventDetailViewModel liveEventDetailViewModel;
        PageType pageType;
        StyleSheet styleSheet = this.styleSheet;
        if (styleSheet == null) {
            return;
        }
        EventHeaderBinder eventHeaderBinder = new EventHeaderBinder(styleSheet, new EventHeaderView.UIEvents(new Function0<Unit>() { // from class: com.amazon.music.alps.liveevent.LiveEventDetailFragment$setupViews$1$eventHeaderBinder$5
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
                this.this$0.fetchData();
            }
        }, new Function0<Unit>() { // from class: com.amazon.music.alps.liveevent.LiveEventDetailFragment$setupViews$1$eventHeaderBinder$6
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
                this.this$0.fetchData();
            }
        }), new EventHeaderView.UserEvents(new Function1<String, Unit>() { // from class: com.amazon.music.alps.liveevent.LiveEventDetailFragment$setupViews$1$eventHeaderBinder$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String target) {
                Intrinsics.checkNotNullParameter(target, "target");
                LiveEventDetailViewModel liveEventDetailViewModel2 = this.this$0.viewModel;
                if (liveEventDetailViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    liveEventDetailViewModel2 = null;
                }
                liveEventDetailViewModel2.openPrimeVideoLink(target, null);
            }
        }, new Function1<String, Unit>() { // from class: com.amazon.music.alps.liveevent.LiveEventDetailFragment$setupViews$1$eventHeaderBinder$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String target) {
                Intrinsics.checkNotNullParameter(target, "target");
                LiveEventDetailViewModel liveEventDetailViewModel2 = this.this$0.viewModel;
                if (liveEventDetailViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    liveEventDetailViewModel2 = null;
                }
                liveEventDetailViewModel2.openTwitchLink(target, null);
            }
        }, new Function0<Unit>() { // from class: com.amazon.music.alps.liveevent.LiveEventDetailFragment$setupViews$1$eventHeaderBinder$3
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
                LiveEventDetailViewModel liveEventDetailViewModel2 = this.this$0.viewModel;
                if (liveEventDetailViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    liveEventDetailViewModel2 = null;
                }
                liveEventDetailViewModel2.openCalendar();
            }
        }, new Function0<Unit>() { // from class: com.amazon.music.alps.liveevent.LiveEventDetailFragment$setupViews$1$eventHeaderBinder$4
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
                LiveEventDetailViewModel liveEventDetailViewModel2 = this.this$0.viewModel;
                if (liveEventDetailViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    liveEventDetailViewModel2 = null;
                }
                Context contextRequireContext = this.this$0.requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
                liveEventDetailViewModel2.openShare(contextRequireContext);
            }
        }), new Function0<Unit>() { // from class: com.amazon.music.alps.liveevent.LiveEventDetailFragment$setupViews$1$eventHeaderBinder$7
            @Override // kotlin.jvm.functions.Function0
            public final Unit invoke() {
                AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
                String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(MetricsProvider.class));
                Intrinsics.checkNotNull(originalClassName);
                InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
                if (!(interfaceProvider instanceof MetricsProvider)) {
                    interfaceProvider = null;
                }
                MetricsProvider metricsProvider = (MetricsProvider) interfaceProvider;
                if (metricsProvider == null) {
                    return null;
                }
                metricsProvider.sendMetricsEvent(new UIContentViewWrapper.Builder().withContainerType(ContainerType.EVENT_HEADER.name()).withContentName(ContentName.LIVE_EVENT_HEADER.name()).withNumItemsAccessible(1L).withEventTime(Long.valueOf(System.currentTimeMillis())).build());
                return Unit.INSTANCE;
            }
        });
        NewEventHeaderBinder newEventHeaderBinder = new NewEventHeaderBinder(styleSheet, new NewEventHeaderView.UIEvents(new Function0<Unit>() { // from class: com.amazon.music.alps.liveevent.LiveEventDetailFragment$setupViews$1$newEventHeaderBinder$5
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
                this.this$0.fetchData();
            }
        }, new Function0<Unit>() { // from class: com.amazon.music.alps.liveevent.LiveEventDetailFragment$setupViews$1$newEventHeaderBinder$6
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
                this.this$0.fetchData();
            }
        }), new NewEventHeaderView.UserEvents(new Function1<String, Unit>() { // from class: com.amazon.music.alps.liveevent.LiveEventDetailFragment$setupViews$1$newEventHeaderBinder$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String target) {
                Intrinsics.checkNotNullParameter(target, "target");
                LiveEventDetailViewModel liveEventDetailViewModel2 = this.this$0.viewModel;
                if (liveEventDetailViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    liveEventDetailViewModel2 = null;
                }
                liveEventDetailViewModel2.openPrimeVideoLink(target, PageType.LIVE_EVENT_DETAIL_EXPERIMENT.name());
            }
        }, new Function1<String, Unit>() { // from class: com.amazon.music.alps.liveevent.LiveEventDetailFragment$setupViews$1$newEventHeaderBinder$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String target) {
                Intrinsics.checkNotNullParameter(target, "target");
                LiveEventDetailViewModel liveEventDetailViewModel2 = this.this$0.viewModel;
                if (liveEventDetailViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    liveEventDetailViewModel2 = null;
                }
                liveEventDetailViewModel2.openTwitchLink(target, PageType.LIVE_EVENT_DETAIL_EXPERIMENT.name());
            }
        }, new Function0<Unit>() { // from class: com.amazon.music.alps.liveevent.LiveEventDetailFragment$setupViews$1$newEventHeaderBinder$3
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
                LiveEventDetailViewModel liveEventDetailViewModel2 = this.this$0.viewModel;
                if (liveEventDetailViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    liveEventDetailViewModel2 = null;
                }
                liveEventDetailViewModel2.openCalendar();
            }
        }, new Function0<Unit>() { // from class: com.amazon.music.alps.liveevent.LiveEventDetailFragment$setupViews$1$newEventHeaderBinder$4
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
                LiveEventDetailViewModel liveEventDetailViewModel2 = this.this$0.viewModel;
                if (liveEventDetailViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    liveEventDetailViewModel2 = null;
                }
                Context contextRequireContext = this.this$0.requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
                liveEventDetailViewModel2.openShareExperiment(contextRequireContext);
            }
        }), new Function0<Unit>() { // from class: com.amazon.music.alps.liveevent.LiveEventDetailFragment$setupViews$1$newEventHeaderBinder$7
            @Override // kotlin.jvm.functions.Function0
            public final Unit invoke() {
                AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
                String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(MetricsProvider.class));
                Intrinsics.checkNotNull(originalClassName);
                InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
                if (!(interfaceProvider instanceof MetricsProvider)) {
                    interfaceProvider = null;
                }
                MetricsProvider metricsProvider = (MetricsProvider) interfaceProvider;
                if (metricsProvider == null) {
                    return null;
                }
                metricsProvider.sendMetricsEvent(new UIContentViewWrapper.Builder().withContainerType(ContainerType.EVENT_HEADER.name()).withContentName(ContentName.LIVE_EVENT_HEADER_EXPERIMENT.name()).withNumItemsAccessible(1L).withEventTime(Long.valueOf(System.currentTimeMillis())).build());
                return Unit.INSTANCE;
            }
        });
        Function2 function2 = null;
        int i = 4;
        DefaultConstructorMarker defaultConstructorMarker = null;
        IconLinkItemBinder iconLinkItemBinder = new IconLinkItemBinder(styleSheet, new Function2<String, ActionType, Unit>() { // from class: com.amazon.music.alps.liveevent.LiveEventDetailFragment$setupViews$1$iconLinkItemBinder$1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke */
            public /* bridge */ /* synthetic */ Unit mo4invoke(String str, ActionType actionType) {
                invoke2(str, actionType);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String target, ActionType actionType) {
                Intrinsics.checkNotNullParameter(target, "target");
                Intrinsics.checkNotNullParameter(actionType, "actionType");
                LiveEventDetailViewModel liveEventDetailViewModel2 = this.this$0.viewModel;
                if (liveEventDetailViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    liveEventDetailViewModel2 = null;
                }
                liveEventDetailViewModel2.openExternalUri(target, actionType, null);
            }
        }, function2, i, defaultConstructorMarker);
        VerticalIconLinkItemBinder verticalIconLinkItemBinder = new VerticalIconLinkItemBinder(styleSheet, new Function2<String, ActionType, Unit>() { // from class: com.amazon.music.alps.liveevent.LiveEventDetailFragment$setupViews$1$verticalIconLinkItemBinder$1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke */
            public /* bridge */ /* synthetic */ Unit mo4invoke(String str, ActionType actionType) {
                invoke2(str, actionType);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String target, ActionType actionType) {
                Intrinsics.checkNotNullParameter(target, "target");
                Intrinsics.checkNotNullParameter(actionType, "actionType");
                LiveEventDetailViewModel liveEventDetailViewModel2 = this.this$0.viewModel;
                if (liveEventDetailViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    liveEventDetailViewModel2 = null;
                }
                liveEventDetailViewModel2.openExternalUri(target, actionType, PageType.LIVE_EVENT_DETAIL_EXPERIMENT.name());
            }
        }, function2, i, defaultConstructorMarker);
        Context contextRequireContext = requireContext();
        UserSubscription userSubscription = getUserSubscription();
        LiveEventDetailViewModel liveEventDetailViewModel2 = this.viewModel;
        RecyclerView recyclerView = null;
        if (liveEventDetailViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            liveEventDetailViewModel = null;
        } else {
            liveEventDetailViewModel = liveEventDetailViewModel2;
        }
        PageType pageType2 = this.pageType;
        if (pageType2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pageType");
            pageType = null;
        } else {
            pageType = pageType2;
        }
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        LiveEventDetailViewFactory liveEventDetailViewFactory = new LiveEventDetailViewFactory(this, contextRequireContext, styleSheet, userSubscription, liveEventDetailViewModel, iconLinkItemBinder, verticalIconLinkItemBinder, pageType, eventHeaderBinder, newEventHeaderBinder);
        List<T> models = pageGridModel.getModels();
        RecyclerView recyclerView2 = this.mainContentView;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mainContentView");
        } else {
            recyclerView = recyclerView2;
        }
        liveEventDetailViewFactory.createLiveEventDetailViews(models, recyclerView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void fetchData() {
        LiveEventDetailViewModel liveEventDetailViewModel = this.viewModel;
        if (liveEventDetailViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            liveEventDetailViewModel = null;
        }
        Bundle arguments = getArguments();
        liveEventDetailViewModel.start(arguments != null ? arguments.getString(Contexts.LiveEventDetail.eventId) : null);
    }

    /* JADX INFO: compiled from: LiveEventDetailFragment.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/alps/liveevent/LiveEventDetailFragment$Companion;", "", "()V", "ARGUMENT_EVENT_ID", "", "ARGUMENT_PAGE_TYPE", "initLineupsIntent", "", MAPAccountManager.KEY_INTENT, "Landroid/content/Intent;", "destination", "Lcom/amazon/music/destination/LiveEventLineupsDestination;", "initLiveEventDetailIntent", "Lcom/amazon/music/alps/liveevent/LiveEventDetailDestination;", "DMMALPS_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void initLiveEventDetailIntent(Intent intent, LiveEventDetailDestination destination) {
            Intrinsics.checkNotNullParameter(intent, "intent");
            Intrinsics.checkNotNullParameter(destination, "destination");
            String strStringPlus = Intrinsics.stringPlus("liveEventDetail?eventId=", destination.getEventId());
            Bundle bundle = new Bundle();
            bundle.putString(Contexts.LiveEventDetail.eventId, destination.getEventId());
            bundle.putString(Contexts.OpenAlexa.pageType, PageType.LIVE_EVENT_DETAIL.name());
            intent.putExtra("com.amazon.music.alps.ui.Extras", bundle);
            intent.putExtra("com.amazon.music.alps.ui.Fragment", LiveEventDetailFragment.class.getName());
            intent.putExtra("com.amazon.music.alps.ui.Tag", strStringPlus);
        }

        public final void initLineupsIntent(Intent intent, LiveEventLineupsDestination destination) {
            Intrinsics.checkNotNullParameter(intent, "intent");
            Intrinsics.checkNotNullParameter(destination, "destination");
            String str = "liveEventDetail?eventId=" + destination.getEventId() + "/lineup";
            Bundle bundle = new Bundle();
            bundle.putString(Contexts.LiveEventDetail.eventId, destination.getEventId());
            bundle.putString(Contexts.OpenAlexa.pageType, PageType.LIVE_EVENT_LINEUP.name());
            intent.putExtra("com.amazon.music.alps.ui.Extras", bundle);
            intent.putExtra("com.amazon.music.alps.ui.Fragment", LiveEventDetailFragment.class.getName());
            intent.putExtra("com.amazon.music.alps.ui.Tag", str);
        }
    }
}
