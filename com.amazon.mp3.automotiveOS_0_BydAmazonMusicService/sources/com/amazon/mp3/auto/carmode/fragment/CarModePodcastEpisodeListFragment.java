package com.amazon.mp3.auto.carmode.fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.auto.AutoUiEventUtil;
import com.amazon.mp3.auto.CarModeAlexaFabViewController;
import com.amazon.mp3.auto.carmode.fragment.viewmodel.CarModePodcastEpisodeListViewModel;
import com.amazon.mp3.auto.carmode.view.CarModeEmbeddedDialog;
import com.amazon.mp3.auto.playback.CarModeMetadataClickListener;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.brush.BrushViews;
import com.amazon.mp3.catalog.fragment.BrowseViewsFactory;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.browse.brush.BrushLatencyReportingUtil;
import com.amazon.music.metrics.mts.event.types.ExperienceMode;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.subscription.UserSubscription;
import com.amazon.music.uicontentview.ContentViewManager;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.enums.TargetType;
import com.amazon.music.views.library.providers.BrushUriClickListenerProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.views.BaseButton;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/* JADX INFO: compiled from: CarModePodcastEpisodeListFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 =2\u00020\u0001:\u0001=B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010$\u001a\u00020\u00192\u0006\u0010%\u001a\u00020&H\u0002J\b\u0010'\u001a\u00020\u000eH\u0002J\u0012\u0010(\u001a\u00020\u00192\b\u0010)\u001a\u0004\u0018\u00010*H\u0016J\u0012\u0010+\u001a\u00020\u00192\b\u0010)\u001a\u0004\u0018\u00010*H\u0016J&\u0010,\u001a\u0004\u0018\u00010-2\u0006\u0010.\u001a\u00020/2\b\u00100\u001a\u0004\u0018\u0001012\b\u0010)\u001a\u0004\u0018\u00010*H\u0016J\b\u00102\u001a\u00020\u0019H\u0016J\b\u00103\u001a\u00020\u0019H\u0016J\u001a\u00104\u001a\u00020\u00192\u0006\u00105\u001a\u00020-2\b\u0010)\u001a\u0004\u0018\u00010*H\u0016J\b\u00106\u001a\u00020\u0019H\u0002J\u0010\u00107\u001a\u00020\u00192\u0006\u00108\u001a\u000209H\u0002J\u0010\u0010:\u001a\u00020\u00192\u0006\u0010;\u001a\u00020<H\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010\u0016\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0018\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u001e\u001a\u00020\u001f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b \u0010!¨\u0006>"}, d2 = {"Lcom/amazon/mp3/auto/carmode/fragment/CarModePodcastEpisodeListFragment;", "Lcom/amazon/mp3/auto/carmode/fragment/CarModeBaseFragment;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "alexaButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "alexaFabViewController", "Lcom/amazon/mp3/auto/CarModeAlexaFabViewController;", "closeButton", "contentViewManager", "Lcom/amazon/music/uicontentview/ContentViewManager;", "isBrushViewSetup", "", "isViewStylized", "loadingSpinner", "Landroid/widget/ProgressBar;", "metadataClickHandler", "Lcom/amazon/mp3/auto/playback/CarModeMetadataClickListener;", "onErrorDialog", "Lcom/amazon/mp3/auto/carmode/view/CarModeEmbeddedDialog;", "pageErrorCallback", "Lkotlin/Function1;", "", "", "podcastEpisodeListRecyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "podcastShowTitle", "Landroid/widget/TextView;", "viewModel", "Lcom/amazon/mp3/auto/carmode/fragment/viewmodel/CarModePodcastEpisodeListViewModel;", "getViewModel", "()Lcom/amazon/mp3/auto/carmode/fragment/viewmodel/CarModePodcastEpisodeListViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "createBrushRootView", "brushView", "Lcom/amazon/mp3/brush/BrushViews;", "hasData", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onCreate", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroy", "onDestroyView", "onViewCreated", "view", "setupBrushViews", "showErrorDialog", "context", "Landroid/content/Context;", "stylizeViews", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CarModePodcastEpisodeListFragment extends CarModeBaseFragment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private BaseButton alexaButton;
    private CarModeAlexaFabViewController alexaFabViewController;
    private BaseButton closeButton;
    private boolean isBrushViewSetup;
    private boolean isViewStylized;
    private ProgressBar loadingSpinner;
    private CarModeMetadataClickListener metadataClickHandler;
    private CarModeEmbeddedDialog onErrorDialog;
    private Function1<? super Throwable, Unit> pageErrorCallback;
    private RecyclerView podcastEpisodeListRecyclerView;
    private TextView podcastShowTitle;

    /* JADX INFO: renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;
    private final String TAG = CarModePodcastEpisodeListFragment.class.getSimpleName();
    private final ContentViewManager contentViewManager = new ContentViewManager();

    public CarModePodcastEpisodeListFragment() {
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModePodcastEpisodeListFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return this;
            }
        };
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(CarModePodcastEpisodeListViewModel.class), new Function0<ViewModelStore>() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModePodcastEpisodeListFragment$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) function0.invoke()).getViewModelStore();
                Intrinsics.checkExpressionValueIsNotNull(viewModelStore, "ownerProducer().viewModelStore");
                return viewModelStore;
            }
        }, null);
        this.pageErrorCallback = new CarModePodcastEpisodeListFragment$pageErrorCallback$1(this);
    }

    private final CarModePodcastEpisodeListViewModel getViewModel() {
        return (CarModePodcastEpisodeListViewModel) this.viewModel.getValue();
    }

    /* JADX INFO: compiled from: CarModePodcastEpisodeListFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/auto/carmode/fragment/CarModePodcastEpisodeListFragment$Companion;", "", "()V", "newInstance", "Lcom/amazon/mp3/auto/carmode/fragment/CarModePodcastEpisodeListFragment;", "bundle", "Landroid/os/Bundle;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final CarModePodcastEpisodeListFragment newInstance(Bundle bundle) {
            CarModePodcastEpisodeListFragment carModePodcastEpisodeListFragment = new CarModePodcastEpisodeListFragment();
            carModePodcastEpisodeListFragment.setArguments(bundle);
            return carModePodcastEpisodeListFragment;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BrushLatencyReportingUtil.INSTANCE.trackLatency("carmode-podcast-episode-list", LatencyTrackingLeg.INITIALIZING);
        getSubscriptions().add(StyleSheetProvider.getStyleSheetObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModePodcastEpisodeListFragment$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CarModePodcastEpisodeListFragment.m305onCreate$lambda0(this.f$0, (StyleSheet) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModePodcastEpisodeListFragment$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CarModePodcastEpisodeListFragment.m306onCreate$lambda1(this.f$0, (Throwable) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-0, reason: not valid java name */
    public static final void m305onCreate$lambda0(CarModePodcastEpisodeListFragment this$0, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setStyleSheet(styleSheet);
        this$0.setupBrushViews();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-1, reason: not valid java name */
    public static final void m306onCreate$lambda1(CarModePodcastEpisodeListFragment this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.error(this$0.TAG, "Failed to fetch stylesheet", th);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        super.onCreateView(inflater, container, savedInstanceState);
        View viewInflate = inflater.inflate(R.layout.fragment_car_mode_podcast_episode_list, container, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflater.inflate(R.layou…e_list, container, false)");
        setRoot(viewInflate);
        View viewFindViewById = getRoot().findViewById(R.id.car_mode_podcast_episode_list_close_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "root.findViewById(R.id.c…pisode_list_close_button)");
        this.closeButton = (BaseButton) viewFindViewById;
        View viewFindViewById2 = getRoot().findViewById(R.id.car_mode_podcast_episode_list_alexa_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "root.findViewById(R.id.c…pisode_list_alexa_button)");
        this.alexaButton = (BaseButton) viewFindViewById2;
        View viewFindViewById3 = getRoot().findViewById(R.id.car_mode_podcast_episode_list_title);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "root.findViewById(R.id.c…dcast_episode_list_title)");
        this.podcastShowTitle = (TextView) viewFindViewById3;
        View viewFindViewById4 = getRoot().findViewById(R.id.loading_spinner);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "root.findViewById(R.id.loading_spinner)");
        this.loadingSpinner = (ProgressBar) viewFindViewById4;
        View viewFindViewById5 = getRoot().findViewById(R.id.car_mode_on_error_dialog);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "root.findViewById(R.id.car_mode_on_error_dialog)");
        this.onErrorDialog = (CarModeEmbeddedDialog) viewFindViewById5;
        ProgressBar progressBar = this.loadingSpinner;
        CarModeEmbeddedDialog carModeEmbeddedDialog = null;
        if (progressBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("loadingSpinner");
            progressBar = null;
        }
        progressBar.setVisibility(0);
        CarModeEmbeddedDialog carModeEmbeddedDialog2 = this.onErrorDialog;
        if (carModeEmbeddedDialog2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onErrorDialog");
        } else {
            carModeEmbeddedDialog = carModeEmbeddedDialog2;
        }
        carModeEmbeddedDialog.hide();
        View viewFindViewById6 = getRoot().findViewById(R.id.car_mode_podcast_episode_list);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById6, "root.findViewById(R.id.c…ode_podcast_episode_list)");
        this.podcastEpisodeListRecyclerView = (RecyclerView) viewFindViewById6;
        this.isViewStylized = false;
        this.isBrushViewSetup = false;
        setupBrushViews();
        return getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        BaseButton baseButton = this.closeButton;
        BaseButton baseButton2 = null;
        if (baseButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("closeButton");
            baseButton = null;
        }
        baseButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModePodcastEpisodeListFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                CarModePodcastEpisodeListFragment.m307onViewCreated$lambda2(this.f$0, view2);
            }
        });
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        BaseButton baseButton3 = this.alexaButton;
        if (baseButton3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("alexaButton");
        } else {
            baseButton2 = baseButton3;
        }
        this.alexaFabViewController = new CarModeAlexaFabViewController(contextRequireContext, baseButton2, PageType.CAR_MODE_PODCAST_EPISODE_LIST);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-2, reason: not valid java name */
    public static final void m307onViewCreated$lambda2(CarModePodcastEpisodeListFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.getParentFragmentManager().getBackStackEntryCount() == 0) {
            FragmentActivity activity = this$0.getActivity();
            if (activity == null) {
                return;
            }
            activity.onBackPressed();
            return;
        }
        this$0.getParentFragmentManager().popBackStack();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AutoUiEventUtil.sendUiPageViewEvent(PageType.CAR_MODE_PODCAST_EPISODE_LIST, ExperienceMode.CAR_MODE);
        if (getActivity() == null) {
            return;
        }
        getViewModel().getGridPageModel().observe(getViewLifecycleOwner(), new Observer() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModePodcastEpisodeListFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                CarModePodcastEpisodeListFragment.m304onActivityCreated$lambda5$lambda3(this.f$0, (PageGridViewModel) obj);
            }
        });
        if (getViewModel().getGridPageModel().getValue() != null) {
            return;
        }
        Bundle arguments = getArguments();
        String string = arguments == null ? null : arguments.getString("PODCAST_SHOW_ID");
        if (string != null) {
            getViewModel().fetchCarModePodcastEpisodeListPage("carmode-podcast-episode-list", string, this.pageErrorCallback);
        }
        getViewModel().initEqualizer();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onActivityCreated$lambda-5$lambda-3, reason: not valid java name */
    public static final void m304onActivityCreated$lambda5$lambda3(CarModePodcastEpisodeListFragment this$0, PageGridViewModel pageGridViewModel) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BrushLatencyReportingUtil.INSTANCE.trackLatency("carmode-podcast-episode-list", LatencyTrackingLeg.RENDERING);
        this$0.setupBrushViews();
    }

    private final void setupBrushViews() {
        StyleSheet styleSheet = getStyleSheet();
        if (styleSheet == null) {
            return;
        }
        stylizeViews(styleSheet);
        if (this.isBrushViewSetup || !hasData()) {
            return;
        }
        this.isBrushViewSetup = true;
        PageType pageType = PageType.CAR_MODE_PODCAST_EPISODE_LIST;
        CarModeMetadataClickListener carModeMetadataClickListener = new CarModeMetadataClickListener(this, pageType, PlaybackPageType.CAR_MODE_PODCAST_EPISODE_LIST);
        this.metadataClickHandler = carModeMetadataClickListener;
        BrushViews podcastEpisodeListBrushViews = BrowseViewsFactory.createBrowseViewsV2(this, styleSheet, pageType, null, carModeMetadataClickListener, new BrushUriClickListenerProvider() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModePodcastEpisodeListFragment$setupBrushViews$1$podcastEpisodeListBrushViews$1
            @Override // com.amazon.music.views.library.providers.BrushUriClickListenerProvider
            public void onClick(String target, String viewId, TargetType targetType) {
            }
        }, this.contentViewManager);
        Intrinsics.checkNotNullExpressionValue(podcastEpisodeListBrushViews, "podcastEpisodeListBrushViews");
        createBrushRootView(podcastEpisodeListBrushViews);
    }

    private final void createBrushRootView(BrushViews brushView) {
        String title;
        if (hasData()) {
            UserSubscription userSubscription = UserSubscriptionUtil.getUserSubscription();
            Intrinsics.checkNotNullExpressionValue(userSubscription, "getUserSubscription()");
            PageGridViewModel value = getViewModel().getGridPageModel().getValue();
            ProgressBar progressBar = null;
            List<BaseViewModel> models = value == null ? null : value.getModels();
            if (models == null) {
                models = CollectionsKt__CollectionsKt.emptyList();
            }
            RecyclerView recyclerView = this.podcastEpisodeListRecyclerView;
            if (recyclerView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("podcastEpisodeListRecyclerView");
                recyclerView = null;
            }
            brushView.createRootView(userSubscription, models, 2, recyclerView);
            PageGridViewModel value2 = getViewModel().getGridPageModel().getValue();
            if (value2 != null && (title = value2.getTitle()) != null) {
                TextView textView = this.podcastShowTitle;
                if (textView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("podcastShowTitle");
                    textView = null;
                }
                textView.setText(title);
            }
            getViewModel().refreshEqualizer();
            ProgressBar progressBar2 = this.loadingSpinner;
            if (progressBar2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("loadingSpinner");
            } else {
                progressBar = progressBar2;
            }
            progressBar.setVisibility(8);
        }
    }

    private final void stylizeViews(StyleSheet styleSheet) {
        BaseButton.StyleBuilder styleBuilderWithIcon;
        Drawable drawable;
        BaseButton.StyleBuilder styleBuilderWithIcon2;
        if (this.isViewStylized) {
            return;
        }
        this.isViewStylized = true;
        BaseButton.StyleBuilder iconBuilder = styleSheet.getIconBuilder(IconSizeKey.LARGE, IconStyleKey.GLASS);
        BaseButton baseButton = this.closeButton;
        CarModeEmbeddedDialog carModeEmbeddedDialog = null;
        if (baseButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("closeButton");
            baseButton = null;
        }
        Context context = getContext();
        if (context != null && (drawable = ContextCompat.getDrawable(context, R.drawable.car_mode_caret_down)) != null && iconBuilder != null && (styleBuilderWithIcon2 = iconBuilder.withIcon(drawable)) != null) {
            styleBuilderWithIcon2.applyStyle(baseButton);
        }
        Drawable drawable2 = ContextCompat.getDrawable(requireContext(), R.drawable.ic_alexa_bauhaus);
        if (drawable2 != null && iconBuilder != null && (styleBuilderWithIcon = iconBuilder.withIcon(drawable2)) != null) {
            BaseButton baseButton2 = this.alexaButton;
            if (baseButton2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("alexaButton");
                baseButton2 = null;
            }
            styleBuilderWithIcon.applyStyle(baseButton2);
        }
        FontStyle fontStyle = styleSheet.getFontStyle(FontStyleKey.HEADLINE_3);
        if (fontStyle != null) {
            FontUtil fontUtil = FontUtil.INSTANCE;
            TextView textView = this.podcastShowTitle;
            if (textView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("podcastShowTitle");
                textView = null;
            }
            fontUtil.applyFontStyle(textView, fontStyle);
        }
        Context context2 = getContext();
        if (context2 == null) {
            return;
        }
        CarModeEmbeddedDialog carModeEmbeddedDialog2 = this.onErrorDialog;
        if (carModeEmbeddedDialog2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onErrorDialog");
            carModeEmbeddedDialog2 = null;
        }
        String string = context2.getString(R.string.dmusic_car_mode_time_out_error_title);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…ode_time_out_error_title)");
        carModeEmbeddedDialog2.setTitle(string);
        CarModeEmbeddedDialog carModeEmbeddedDialog3 = this.onErrorDialog;
        if (carModeEmbeddedDialog3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onErrorDialog");
            carModeEmbeddedDialog3 = null;
        }
        String string2 = context2.getString(R.string.dmusic_car_mode_time_out_error_message);
        Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.stri…e_time_out_error_message)");
        carModeEmbeddedDialog3.setMessage(string2);
        CarModeEmbeddedDialog carModeEmbeddedDialog4 = this.onErrorDialog;
        if (carModeEmbeddedDialog4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onErrorDialog");
        } else {
            carModeEmbeddedDialog = carModeEmbeddedDialog4;
        }
        carModeEmbeddedDialog.initStyling(styleSheet);
    }

    private final boolean hasData() {
        PageGridViewModel value = getViewModel().getGridPageModel().getValue();
        List<BaseViewModel> models = value == null ? null : value.getModels();
        return !(models == null || models.isEmpty());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showErrorDialog(Context context) {
        CarModeEmbeddedDialog carModeEmbeddedDialog = this.onErrorDialog;
        CarModeEmbeddedDialog carModeEmbeddedDialog2 = null;
        if (carModeEmbeddedDialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onErrorDialog");
            carModeEmbeddedDialog = null;
        }
        String string = context.getString(R.string.dmusic_button_refresh);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.dmusic_button_refresh)");
        carModeEmbeddedDialog.setPositiveButton(string, new View.OnClickListener() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModePodcastEpisodeListFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CarModePodcastEpisodeListFragment.m308showErrorDialog$lambda15(this.f$0, view);
            }
        });
        ProgressBar progressBar = this.loadingSpinner;
        if (progressBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("loadingSpinner");
            progressBar = null;
        }
        progressBar.setVisibility(8);
        CarModeEmbeddedDialog carModeEmbeddedDialog3 = this.onErrorDialog;
        if (carModeEmbeddedDialog3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onErrorDialog");
        } else {
            carModeEmbeddedDialog2 = carModeEmbeddedDialog3;
        }
        carModeEmbeddedDialog2.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showErrorDialog$lambda-15, reason: not valid java name */
    public static final void m308showErrorDialog$lambda15(CarModePodcastEpisodeListFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ProgressBar progressBar = this$0.loadingSpinner;
        if (progressBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("loadingSpinner");
            progressBar = null;
        }
        progressBar.setVisibility(0);
        CarModeEmbeddedDialog carModeEmbeddedDialog = this$0.onErrorDialog;
        if (carModeEmbeddedDialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onErrorDialog");
            carModeEmbeddedDialog = null;
        }
        carModeEmbeddedDialog.hide();
        Bundle arguments = this$0.getArguments();
        String string = arguments != null ? arguments.getString("PODCAST_SHOW_ID") : null;
        if (string == null) {
            return;
        }
        this$0.getViewModel().fetchCarModePodcastEpisodeListPage("carmode-podcast-episode-list", string, this$0.pageErrorCallback);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        CarModeMetadataClickListener carModeMetadataClickListener = this.metadataClickHandler;
        if (carModeMetadataClickListener != null) {
            carModeMetadataClickListener.cleanup();
        }
        CarModeEmbeddedDialog carModeEmbeddedDialog = this.onErrorDialog;
        ProgressBar progressBar = null;
        if (carModeEmbeddedDialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onErrorDialog");
            carModeEmbeddedDialog = null;
        }
        carModeEmbeddedDialog.hide();
        ProgressBar progressBar2 = this.loadingSpinner;
        if (progressBar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("loadingSpinner");
        } else {
            progressBar = progressBar2;
        }
        progressBar.setVisibility(8);
        CarModeAlexaFabViewController carModeAlexaFabViewController = this.alexaFabViewController;
        if (carModeAlexaFabViewController != null) {
            carModeAlexaFabViewController.setAlexaFabButtonVisibility(false);
        }
        super.onDestroyView();
    }

    @Override // com.amazon.mp3.auto.carmode.fragment.CarModeBaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        this.pageErrorCallback = null;
        getViewModel().clear();
        CarModeAlexaFabViewController carModeAlexaFabViewController = this.alexaFabViewController;
        if (carModeAlexaFabViewController != null) {
            carModeAlexaFabViewController.destroy();
        }
        this.alexaFabViewController = null;
        super.onDestroy();
    }
}
