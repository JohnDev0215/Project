package com.amazon.mp3.auto.carmode.fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.auto.AutoUiEventUtil;
import com.amazon.mp3.auto.CarModeAlexaFabViewController;
import com.amazon.mp3.auto.carmode.CarModeAction;
import com.amazon.mp3.auto.carmode.CarModeNavigation;
import com.amazon.mp3.auto.carmode.CarModePresetsUtility;
import com.amazon.mp3.auto.carmode.fragment.viewmodel.CarModeDetailViewModel;
import com.amazon.mp3.auto.carmode.snaphelper.FlickAndStickSnapHelper;
import com.amazon.mp3.auto.carmode.view.CarModeEmbeddedDialog;
import com.amazon.mp3.auto.carmode.view.CarModeMiniPlayerView;
import com.amazon.mp3.auto.carmode.view.CarModeMiniPlayerViewController;
import com.amazon.mp3.auto.carmode.view.CarModePlayerViewController;
import com.amazon.mp3.auto.playback.CarModeMetadataClickListener;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.brush.BrushViews;
import com.amazon.mp3.catalog.fragment.BrowseViewsFactory;
import com.amazon.mp3.playback.PlayerNavigationListener;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.playback.state.LibraryEqualizerStateManager;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.browse.brush.BrushLatencyReportingUtil;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.metrics.mts.event.types.ExperienceMode;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
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
import com.amazon.podcast.external.car.PodcastMediaMetadata;
import com.amazon.podcast.media.playback.Media;
import com.amazon.podcast.media.playback.PodcastMediaItem;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.styles.GridSize;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.views.BaseButton;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/* JADX INFO: compiled from: CarModeDetailFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000Ì\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 Q2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001QB\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010/\u001a\u00020&2\u0006\u00100\u001a\u000201H\u0002J\b\u00102\u001a\u00020\u0017H\u0002J\u0012\u00103\u001a\u00020&2\b\u00104\u001a\u0004\u0018\u000105H\u0016J\u0012\u00106\u001a\u00020&2\b\u00104\u001a\u0004\u0018\u000105H\u0016J$\u00107\u001a\u0002082\u0006\u00109\u001a\u00020:2\b\u0010;\u001a\u0004\u0018\u00010<2\b\u00104\u001a\u0004\u0018\u000105H\u0016J\b\u0010=\u001a\u00020&H\u0016J\b\u0010>\u001a\u00020&H\u0016J\b\u0010?\u001a\u00020&H\u0016J\b\u0010@\u001a\u00020&H\u0016J\u0012\u0010A\u001a\u00020&2\b\u0010B\u001a\u0004\u0018\u00010CH\u0016J\u001a\u0010D\u001a\u00020&2\u0006\u0010E\u001a\u0002082\b\u00104\u001a\u0004\u0018\u000105H\u0016J\u0010\u0010F\u001a\u00020&2\u0006\u0010G\u001a\u00020HH\u0002J\u0010\u0010I\u001a\u00020&2\u0006\u0010J\u001a\u00020KH\u0002J\b\u0010L\u001a\u00020&H\u0002J\u0010\u0010M\u001a\u00020&2\u0006\u0010J\u001a\u00020KH\u0002J\u0010\u0010N\u001a\u00020&2\u0006\u0010O\u001a\u00020PH\u0002R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010#\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010%\u0012\u0004\u0012\u00020&\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010)\u001a\u00020*8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b-\u0010.\u001a\u0004\b+\u0010,¨\u0006R"}, d2 = {"Lcom/amazon/mp3/auto/carmode/fragment/CarModeDetailFragment;", "Lcom/amazon/mp3/auto/carmode/fragment/CarModeBaseFragment;", "Lcom/amazon/podcast/media/playback/Media$PodcastMediaMetadataCallback;", "Lcom/amazon/music/media/playback/OnPlayStateChangedListener;", "()V", "alexaFabViewController", "Lcom/amazon/mp3/auto/CarModeAlexaFabViewController;", "alexaFloatingActionButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "backButton", "backgroundImageView", "Landroidx/appcompat/widget/AppCompatImageView;", "carModeDetailPageTitle", "Landroidx/appcompat/widget/AppCompatTextView;", "carModeDetailRecyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "carModeMiniPlayerView", "Lcom/amazon/mp3/auto/carmode/view/CarModeMiniPlayerView;", "contentViewManager", "Lcom/amazon/music/uicontentview/ContentViewManager;", "flickAndStickSnapHelper", "Lcom/amazon/mp3/auto/carmode/snaphelper/FlickAndStickSnapHelper;", "isBrushViewSetup", "", "isViewStylized", "lastMediaItem", "Lcom/amazon/music/media/playback/MediaItem;", "loadingSpinner", "Landroid/widget/ProgressBar;", "metadataClickHandler", "Lcom/amazon/mp3/auto/playback/CarModeMetadataClickListener;", "miniPlayerViewController", "Lcom/amazon/mp3/auto/carmode/view/CarModeMiniPlayerViewController;", "onErrorDialog", "Lcom/amazon/mp3/auto/carmode/view/CarModeEmbeddedDialog;", "pageErrorCallback", "Lkotlin/Function1;", "", "", "playbackController", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "viewModel", "Lcom/amazon/mp3/auto/carmode/fragment/viewmodel/CarModeDetailViewModel;", "getViewModel", "()Lcom/amazon/mp3/auto/carmode/fragment/viewmodel/CarModeDetailViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "createBrushRootView", "brushView", "Lcom/amazon/mp3/brush/BrushViews;", "hasData", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onCreate", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroy", "onDestroyView", "onPause", "onPlayStateChanged", "onPodcastMediaMetadataChange", "metadata", "Lcom/amazon/podcast/external/car/PodcastMediaMetadata;", "onViewCreated", "view", "reportPageLoadLatency", "leg", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/LatencyTrackingLeg;", "setRecyclerViewMargins", "context", "Landroid/content/Context;", "setupBrushViews", "showErrorDialog", "stylizeViews", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CarModeDetailFragment extends CarModeBaseFragment implements Media.PodcastMediaMetadataCallback, OnPlayStateChangedListener {
    private static final String TAG = CarModeDetailFragment.class.getSimpleName();
    private CarModeAlexaFabViewController alexaFabViewController;
    private BaseButton alexaFloatingActionButton;
    private BaseButton backButton;
    private AppCompatImageView backgroundImageView;
    private AppCompatTextView carModeDetailPageTitle;
    private RecyclerView carModeDetailRecyclerView;
    private CarModeMiniPlayerView carModeMiniPlayerView;
    private final ContentViewManager contentViewManager;
    private final FlickAndStickSnapHelper flickAndStickSnapHelper;
    private boolean isBrushViewSetup;
    private boolean isViewStylized;
    private MediaItem lastMediaItem;
    private ProgressBar loadingSpinner;
    private CarModeMetadataClickListener metadataClickHandler;
    private CarModeMiniPlayerViewController miniPlayerViewController;
    private CarModeEmbeddedDialog onErrorDialog;
    private Function1<? super Throwable, Unit> pageErrorCallback;
    private final ActionValidatedPlaybackController playbackController;

    /* JADX INFO: renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    public CarModeDetailFragment() {
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeDetailFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return this;
            }
        };
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(CarModeDetailViewModel.class), new Function0<ViewModelStore>() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeDetailFragment$special$$inlined$viewModels$default$2
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
        this.playbackController = PlaybackControllerProvider.INSTANCE.getController(ControlSource.APP_UI);
        this.contentViewManager = new ContentViewManager();
        this.flickAndStickSnapHelper = new FlickAndStickSnapHelper(0.0f, 4, 1, null);
        this.pageErrorCallback = new CarModeDetailFragment$pageErrorCallback$1(this);
    }

    private final CarModeDetailViewModel getViewModel() {
        return (CarModeDetailViewModel) this.viewModel.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BrushLatencyReportingUtil.INSTANCE.trackLatency("carmode-detail", LatencyTrackingLeg.INITIALIZING);
        getSubscriptions().add(StyleSheetProvider.getStyleSheetObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeDetailFragment$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CarModeDetailFragment.m272onCreate$lambda0(this.f$0, (StyleSheet) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeDetailFragment$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CarModeDetailFragment.m273onCreate$lambda1((Throwable) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-0, reason: not valid java name */
    public static final void m272onCreate$lambda0(CarModeDetailFragment this$0, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setStyleSheet(styleSheet);
        this$0.setupBrushViews();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-1, reason: not valid java name */
    public static final void m273onCreate$lambda1(Throwable th) {
        Log.error(TAG, Intrinsics.stringPlus("Failed to fetch stylesheet with error ", th));
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        super.onCreateView(inflater, container, savedInstanceState);
        View viewInflate = inflater.inflate(R.layout.car_mode_detail, container, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflater.inflate(R.layou…detail, container, false)");
        setRoot(viewInflate);
        View viewFindViewById = getRoot().findViewById(R.id.car_mode_list);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "root.findViewById(R.id.car_mode_list)");
        this.carModeDetailRecyclerView = (RecyclerView) viewFindViewById;
        View viewFindViewById2 = getRoot().findViewById(R.id.car_mode_detail_back_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "root.findViewById(R.id.c…_mode_detail_back_button)");
        this.backButton = (BaseButton) viewFindViewById2;
        View viewFindViewById3 = getRoot().findViewById(R.id.car_mode_detail_alexa_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "root.findViewById(R.id.c…mode_detail_alexa_button)");
        this.alexaFloatingActionButton = (BaseButton) viewFindViewById3;
        View viewFindViewById4 = getRoot().findViewById(R.id.car_mode_detail_title);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "root.findViewById(R.id.car_mode_detail_title)");
        this.carModeDetailPageTitle = (AppCompatTextView) viewFindViewById4;
        View viewFindViewById5 = getRoot().findViewById(R.id.car_mode_mini_player);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "root.findViewById(R.id.car_mode_mini_player)");
        this.carModeMiniPlayerView = (CarModeMiniPlayerView) viewFindViewById5;
        View viewFindViewById6 = getRoot().findViewById(R.id.car_mode_blur_background);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById6, "root.findViewById(R.id.car_mode_blur_background)");
        this.backgroundImageView = (AppCompatImageView) viewFindViewById6;
        View viewFindViewById7 = getRoot().findViewById(R.id.loading_spinner);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById7, "root.findViewById(R.id.loading_spinner)");
        this.loadingSpinner = (ProgressBar) viewFindViewById7;
        View viewFindViewById8 = getRoot().findViewById(R.id.car_mode_on_error_dialog);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById8, "root.findViewById(R.id.car_mode_on_error_dialog)");
        this.onErrorDialog = (CarModeEmbeddedDialog) viewFindViewById8;
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
        this.isBrushViewSetup = false;
        this.isViewStylized = false;
        setupBrushViews();
        return getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        BaseButton baseButton = this.backButton;
        BaseButton baseButton2 = null;
        if (baseButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("backButton");
            baseButton = null;
        }
        baseButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeDetailFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                CarModeDetailFragment.m274onViewCreated$lambda2(this.f$0, view2);
            }
        });
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        BaseButton baseButton3 = this.alexaFloatingActionButton;
        if (baseButton3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("alexaFloatingActionButton");
        } else {
            baseButton2 = baseButton3;
        }
        this.alexaFabViewController = new CarModeAlexaFabViewController(contextRequireContext, baseButton2, PageType.CAR_MODE_DETAIL);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-2, reason: not valid java name */
    public static final void m274onViewCreated$lambda2(CarModeDetailFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AutoUiEventUtil.sendUiClickEvent$default(ActionType.GO_BACK, PageType.CAR_MODE_DETAIL, ExperienceMode.CAR_MODE, null, 8, null);
        FragmentActivity activity = this$0.getActivity();
        if (activity == null) {
            return;
        }
        activity.onBackPressed();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        CarModeMiniPlayerView carModeMiniPlayerView;
        super.onActivityCreated(savedInstanceState);
        PageType pageType = PageType.CAR_MODE_DETAIL;
        AutoUiEventUtil.sendUiPageViewEvent(pageType, ExperienceMode.CAR_MODE);
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        CarModeMiniPlayerView carModeMiniPlayerView2 = this.carModeMiniPlayerView;
        if (carModeMiniPlayerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("carModeMiniPlayerView");
            carModeMiniPlayerView = null;
        } else {
            carModeMiniPlayerView = carModeMiniPlayerView2;
        }
        AppCompatImageView appCompatImageView = this.backgroundImageView;
        if (appCompatImageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("backgroundImageView");
            appCompatImageView = null;
        }
        CarModeMiniPlayerViewController carModeMiniPlayerViewController = new CarModeMiniPlayerViewController(activity, carModeMiniPlayerView, this, pageType, getBitmapChangeListener(appCompatImageView), new PlayerNavigationListener() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeDetailFragment$onActivityCreated$1$1
            @Override // com.amazon.mp3.playback.PlayerNavigationListener
            public void addTrackToLibrary() {
            }

            @Override // com.amazon.mp3.playback.PlayerNavigationListener
            public void onCreateContextMenu(ContextMenu menu) {
            }

            @Override // com.amazon.mp3.playback.PlayerNavigationListener
            public void showCastingView() {
            }

            @Override // com.amazon.mp3.playback.PlayerNavigationListener
            public void showPlayQueue() {
            }

            @Override // com.amazon.mp3.playback.PlayerNavigationListener
            public void showPodcastEpisodeList() {
            }

            @Override // com.amazon.mp3.playback.PlayerNavigationListener
            public void showNowPlaying(InteractionType type) {
                CarModeNavigation navigationListener = this.this$0.getNavigationListener();
                if (navigationListener == null) {
                    return;
                }
                CarModeNavigation.DefaultImpls.navigateTo$default(navigationListener, CarModeAction.SHOW_NOW_PLAYING_PAGE, null, 2, null);
            }
        });
        carModeMiniPlayerViewController.refreshPlayer();
        this.miniPlayerViewController = carModeMiniPlayerViewController;
        if (!AmazonApplication.getCapabilities().isPodcastHarleyEnabled()) {
            if (LibraryEqualizerStateManager.INSTANCE.isPodcastContentPlaying()) {
                onPodcastMediaMetadataChange(CarModePresetsUtility.INSTANCE.getPodcastMediaMetadata());
            }
            this.playbackController.addOnPlayStateChangedListener(this);
            CarModePresetsUtility.INSTANCE.addPodcastMediaMetadataCallback(this);
        }
        getViewModel().getGridPageModel().observe(getViewLifecycleOwner(), new Observer() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeDetailFragment$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                CarModeDetailFragment.m271onActivityCreated$lambda6$lambda4(this.f$0, (PageGridViewModel) obj);
            }
        });
        if (getViewModel().getGridPageModel().getValue() == null) {
            Bundle arguments = getArguments();
            String string = arguments != null ? arguments.getString("carModeDetailPageUri") : null;
            if (string != null) {
                getViewModel().fetchCarModeDetailPage(string, this.pageErrorCallback);
            }
            getViewModel().initEqualizer();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onActivityCreated$lambda-6$lambda-4, reason: not valid java name */
    public static final void m271onActivityCreated$lambda6$lambda4(CarModeDetailFragment this$0, PageGridViewModel pageGridViewModel) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BrushLatencyReportingUtil.INSTANCE.trackLatency("carmode-detail", LatencyTrackingLeg.RENDERING);
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
        PageType pageType = PageType.CAR_MODE_DETAIL;
        CarModeMetadataClickListener carModeMetadataClickListener = new CarModeMetadataClickListener(this, pageType, PlaybackPageType.CAR_MODE_DETAIL);
        this.metadataClickHandler = carModeMetadataClickListener;
        BrushViews carModeBrushView = BrowseViewsFactory.createBrowseViewsV2(this, styleSheet, pageType, null, carModeMetadataClickListener, new BrushUriClickListenerProvider() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeDetailFragment$setupBrushViews$1$carModeBrushView$1
            @Override // com.amazon.music.views.library.providers.BrushUriClickListenerProvider
            public void onClick(String target, String viewId, TargetType targetType) {
            }
        }, this.contentViewManager);
        Intrinsics.checkNotNullExpressionValue(carModeBrushView, "carModeBrushView");
        createBrushRootView(carModeBrushView);
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
            PageGridViewModel value2 = getViewModel().getGridPageModel().getValue();
            int tileType = value2 == null ? 21 : value2.getTileType();
            RecyclerView recyclerView = this.carModeDetailRecyclerView;
            if (recyclerView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("carModeDetailRecyclerView");
                recyclerView = null;
            }
            brushView.createRootView(userSubscription, models, tileType, recyclerView);
            Context context = getContext();
            if (context != null) {
                setRecyclerViewMargins(context);
                RecyclerView recyclerView2 = this.carModeDetailRecyclerView;
                if (recyclerView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("carModeDetailRecyclerView");
                    recyclerView2 = null;
                }
                recyclerView2.setItemAnimator(null);
                RecyclerView recyclerView3 = this.carModeDetailRecyclerView;
                if (recyclerView3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("carModeDetailRecyclerView");
                    recyclerView3 = null;
                }
                recyclerView3.setClipToPadding(false);
                int deviceHeight = ScreenUtil.getDeviceHeight();
                RecyclerView recyclerView4 = this.carModeDetailRecyclerView;
                if (recyclerView4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("carModeDetailRecyclerView");
                    recyclerView4 = null;
                }
                recyclerView4.setPadding(0, 0, 0, deviceHeight);
                FlickAndStickSnapHelper flickAndStickSnapHelper = this.flickAndStickSnapHelper;
                RecyclerView recyclerView5 = this.carModeDetailRecyclerView;
                if (recyclerView5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("carModeDetailRecyclerView");
                    recyclerView5 = null;
                }
                flickAndStickSnapHelper.attachToRecyclerView(recyclerView5);
            }
            PageGridViewModel value3 = getViewModel().getGridPageModel().getValue();
            if (value3 != null && (title = value3.getTitle()) != null) {
                AppCompatTextView appCompatTextView = this.carModeDetailPageTitle;
                if (appCompatTextView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("carModeDetailPageTitle");
                    appCompatTextView = null;
                }
                appCompatTextView.setText(title);
            }
            getViewModel().refreshEqualizer();
            ProgressBar progressBar2 = this.loadingSpinner;
            if (progressBar2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("loadingSpinner");
            } else {
                progressBar = progressBar2;
            }
            progressBar.setVisibility(8);
            reportPageLoadLatency(LatencyTrackingLeg.LOAD_COMPLETE);
        }
    }

    private final void setRecyclerViewMargins(Context context) {
        GridSize gridSize;
        StyleSheet styleSheet = getStyleSheet();
        if (styleSheet == null || (gridSize = styleSheet.getGridSize(context)) == null) {
            return;
        }
        RecyclerView recyclerView = this.carModeDetailRecyclerView;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("carModeDetailRecyclerView");
            recyclerView = null;
        }
        ViewGroup.LayoutParams layoutParams = recyclerView.getLayoutParams();
        if (layoutParams != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            marginLayoutParams.leftMargin = (gridSize.getColumnSpace() / 2) + gridSize.getMarginSpace();
            marginLayoutParams.rightMargin = (gridSize.getColumnSpace() / 2) + gridSize.getMarginSpace();
            recyclerView.setLayoutParams(marginLayoutParams);
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
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
        carModeEmbeddedDialog.setPositiveButton(string, new View.OnClickListener() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeDetailFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CarModeDetailFragment.m275showErrorDialog$lambda13(this.f$0, view);
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
    /* JADX INFO: renamed from: showErrorDialog$lambda-13, reason: not valid java name */
    public static final void m275showErrorDialog$lambda13(CarModeDetailFragment this$0, View view) {
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
        String string = arguments != null ? arguments.getString("carModeDetailPageUri") : null;
        if (string == null) {
            return;
        }
        this$0.getViewModel().fetchCarModeDetailPage(string, this$0.pageErrorCallback);
    }

    private final void reportPageLoadLatency(LatencyTrackingLeg leg) {
        if (getViewModel().getInitPageLoad()) {
            BrushLatencyReportingUtil.INSTANCE.trackLatency("carmode-detail", leg);
        }
    }

    private final void stylizeViews(StyleSheet styleSheet) {
        BaseButton.StyleBuilder styleBuilderWithIcon;
        BaseButton.StyleBuilder styleBuilderWithIcon2;
        if (this.isViewStylized) {
            return;
        }
        this.isViewStylized = true;
        BaseButton.StyleBuilder iconBuilder = styleSheet.getIconBuilder(IconSizeKey.LARGE, IconStyleKey.GLASS);
        Drawable drawable = ContextCompat.getDrawable(requireContext(), R.drawable.car_mode_caret_left);
        CarModeEmbeddedDialog carModeEmbeddedDialog = null;
        if (drawable != null && iconBuilder != null && (styleBuilderWithIcon2 = iconBuilder.withIcon(drawable)) != null) {
            BaseButton baseButton = this.backButton;
            if (baseButton == null) {
                Intrinsics.throwUninitializedPropertyAccessException("backButton");
                baseButton = null;
            }
            styleBuilderWithIcon2.applyStyle(baseButton);
        }
        Drawable drawable2 = ContextCompat.getDrawable(requireContext(), R.drawable.ic_alexa_bauhaus);
        if (drawable2 != null && iconBuilder != null && (styleBuilderWithIcon = iconBuilder.withIcon(drawable2)) != null) {
            BaseButton baseButton2 = this.alexaFloatingActionButton;
            if (baseButton2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("alexaFloatingActionButton");
                baseButton2 = null;
            }
            styleBuilderWithIcon.applyStyle(baseButton2);
        }
        FontStyle fontStyle = styleSheet.getFontStyle(FontStyleKey.PRIMARY_HEADLINE_4_BIG_SHARPGROTESK);
        if (fontStyle != null) {
            FontUtil fontUtil = FontUtil.INSTANCE;
            AppCompatTextView appCompatTextView = this.carModeDetailPageTitle;
            if (appCompatTextView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("carModeDetailPageTitle");
                appCompatTextView = null;
            }
            fontUtil.applyFontStyle(appCompatTextView, fontStyle);
        }
        CarModeMiniPlayerView carModeMiniPlayerView = this.carModeMiniPlayerView;
        if (carModeMiniPlayerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("carModeMiniPlayerView");
            carModeMiniPlayerView = null;
        }
        carModeMiniPlayerView.setStyleSheet(styleSheet);
        Context context = getContext();
        if (context == null) {
            return;
        }
        CarModeEmbeddedDialog carModeEmbeddedDialog2 = this.onErrorDialog;
        if (carModeEmbeddedDialog2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onErrorDialog");
            carModeEmbeddedDialog2 = null;
        }
        String string = context.getString(R.string.dmusic_car_mode_time_out_error_title);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…ode_time_out_error_title)");
        carModeEmbeddedDialog2.setTitle(string);
        CarModeEmbeddedDialog carModeEmbeddedDialog3 = this.onErrorDialog;
        if (carModeEmbeddedDialog3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onErrorDialog");
            carModeEmbeddedDialog3 = null;
        }
        String string2 = context.getString(R.string.dmusic_car_mode_time_out_error_message);
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

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        this.contentViewManager.sendUiContentViewEvent(ExperienceMode.CAR_MODE.name());
        super.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        CarModeMiniPlayerViewController carModeMiniPlayerViewController = this.miniPlayerViewController;
        if (carModeMiniPlayerViewController != null) {
            carModeMiniPlayerViewController.clear();
        }
        this.miniPlayerViewController = null;
        CarModeMetadataClickListener carModeMetadataClickListener = this.metadataClickHandler;
        if (carModeMetadataClickListener != null) {
            carModeMetadataClickListener.cleanup();
        }
        CarModeEmbeddedDialog carModeEmbeddedDialog = this.onErrorDialog;
        if (carModeEmbeddedDialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onErrorDialog");
            carModeEmbeddedDialog = null;
        }
        carModeEmbeddedDialog.hide();
        CarModeAlexaFabViewController carModeAlexaFabViewController = this.alexaFabViewController;
        if (carModeAlexaFabViewController != null) {
            carModeAlexaFabViewController.setAlexaFabButtonVisibility(false);
        }
        this.playbackController.removeOnPlayStateChangedListener(this);
        CarModePresetsUtility.INSTANCE.removePodcastMediaMetadataCallback(this);
        getViewModel().setInitPageLoad(false);
        this.lastMediaItem = null;
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

    @Override // com.amazon.podcast.media.playback.Media.PodcastMediaMetadataCallback
    public void onPodcastMediaMetadataChange(PodcastMediaMetadata metadata) {
        if (metadata != null) {
            CarModeMiniPlayerViewController carModeMiniPlayerViewController = this.miniPlayerViewController;
            if (carModeMiniPlayerViewController != null) {
                CarModePlayerViewController.refreshPlayerView$default(carModeMiniPlayerViewController, null, true, 1, null);
            }
            CarModeMiniPlayerViewController carModeMiniPlayerViewController2 = this.miniPlayerViewController;
            if (carModeMiniPlayerViewController2 == null) {
                return;
            }
            carModeMiniPlayerViewController2.updatePlayerView();
        }
    }

    @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
    public void onPlayStateChanged() {
        MediaItem currentMediaItem = this.playbackController.getCurrentMediaItem();
        if (currentMediaItem == null || (currentMediaItem instanceof PodcastMediaItem) || Intrinsics.areEqual(currentMediaItem, this.lastMediaItem)) {
            return;
        }
        this.lastMediaItem = currentMediaItem;
        CarModeMiniPlayerViewController carModeMiniPlayerViewController = this.miniPlayerViewController;
        if (carModeMiniPlayerViewController != null) {
            CarModePlayerViewController.refreshPlayerView$default(carModeMiniPlayerViewController, currentMediaItem, false, 2, null);
        }
        CarModeMiniPlayerViewController carModeMiniPlayerViewController2 = this.miniPlayerViewController;
        if (carModeMiniPlayerViewController2 == null) {
            return;
        }
        carModeMiniPlayerViewController2.refreshPlayer();
    }
}
