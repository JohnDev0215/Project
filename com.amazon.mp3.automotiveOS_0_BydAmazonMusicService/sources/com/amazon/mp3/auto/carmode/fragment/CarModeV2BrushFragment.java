package com.amazon.mp3.auto.carmode.fragment;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.auto.AutoUiEventUtil;
import com.amazon.mp3.auto.CarModeAlexaFabViewController;
import com.amazon.mp3.auto.carmode.CarModeAction;
import com.amazon.mp3.auto.carmode.CarModeAlexaActionsHandler;
import com.amazon.mp3.auto.carmode.CarModeIngressAutoPlayTypes;
import com.amazon.mp3.auto.carmode.CarModeNavigation;
import com.amazon.mp3.auto.carmode.CarModePresetsUtility;
import com.amazon.mp3.auto.carmode.fragment.viewmodel.CarModeBrushViewModel;
import com.amazon.mp3.auto.carmode.snaphelper.FlickAndStickSnapHelper;
import com.amazon.mp3.auto.carmode.view.CarModeEmbeddedDialog;
import com.amazon.mp3.auto.carmode.view.CarModeMiniPlayerView;
import com.amazon.mp3.auto.carmode.view.CarModeMiniPlayerViewController;
import com.amazon.mp3.auto.carmode.view.CarModePlayerViewController;
import com.amazon.mp3.auto.playback.CarModeMetadataClickListener;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.brush.BrushViews;
import com.amazon.mp3.catalog.fragment.BrowseViewsFactory;
import com.amazon.mp3.navigation.ContextAwareDeeplinkManager;
import com.amazon.mp3.playback.PlayerNavigationListener;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.playback.state.LibraryEqualizerStateManager;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PlaybackHandlerUtil;
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
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.SimpleShovelerModel;
import com.amazon.music.views.library.models.SimpleVerticalTileModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.enums.TargetType;
import com.amazon.music.views.library.providers.BrushUriClickListenerProvider;
import com.amazon.music.views.library.providers.SeeMoreProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.podcast.external.car.PodcastMediaMetadata;
import com.amazon.podcast.media.playback.Media;
import com.amazon.podcast.media.playback.PodcastMediaItem;
import com.amazon.ui.foundations.views.BaseButton;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/* JADX INFO: compiled from: CarModeV2BrushFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000ñ\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001*\u0018\u0000 b2\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0001bB\u0005¢\u0006\u0002\u0010\u0005J\b\u00102\u001a\u00020&H\u0002J\u0010\u00103\u001a\u00020&2\u0006\u00104\u001a\u000205H\u0002JD\u00106\u001a\u00020&2:\u00107\u001a6\u0012\u0013\u0012\u001109¢\u0006\f\b:\u0012\b\b;\u0012\u0004\b\b(<\u0012\u0015\u0012\u0013\u0018\u00010=¢\u0006\f\b:\u0012\b\b;\u0012\u0004\b\b(>\u0012\u0006\u0012\u0004\u0018\u00010&08H\u0016JL\u0010?\u001a\u00020&2\u0006\u0010@\u001a\u00020A2:\u00107\u001a6\u0012\u0013\u0012\u001109¢\u0006\f\b:\u0012\b\b;\u0012\u0004\b\b(<\u0012\u0015\u0012\u0013\u0018\u00010=¢\u0006\f\b:\u0012\b\b;\u0012\u0004\b\b(>\u0012\u0006\u0012\u0004\u0018\u00010&08H\u0016JD\u0010B\u001a\u00020&2:\u00107\u001a6\u0012\u0013\u0012\u001109¢\u0006\f\b:\u0012\b\b;\u0012\u0004\b\b(<\u0012\u0015\u0012\u0013\u0018\u00010=¢\u0006\f\b:\u0012\b\b;\u0012\u0004\b\b(>\u0012\u0006\u0012\u0004\u0018\u00010&08H\u0016JD\u0010C\u001a\u00020&2:\u00107\u001a6\u0012\u0013\u0012\u001109¢\u0006\f\b:\u0012\b\b;\u0012\u0004\b\b(<\u0012\u0015\u0012\u0013\u0018\u00010=¢\u0006\f\b:\u0012\b\b;\u0012\u0004\b\b(>\u0012\u0006\u0012\u0004\u0018\u00010&08H\u0016J\b\u0010D\u001a\u00020\u0016H\u0002J\u0012\u0010E\u001a\u00020&2\b\u0010F\u001a\u0004\u0018\u00010GH\u0016J\u0012\u0010H\u001a\u00020&2\b\u0010F\u001a\u0004\u0018\u00010GH\u0016J$\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020L2\b\u0010M\u001a\u0004\u0018\u00010N2\b\u0010F\u001a\u0004\u0018\u00010GH\u0016J\b\u0010O\u001a\u00020&H\u0016J\b\u0010P\u001a\u00020&H\u0016J\b\u0010Q\u001a\u00020&H\u0016J\b\u0010R\u001a\u00020&H\u0016J\u0012\u0010S\u001a\u00020&2\b\u0010T\u001a\u0004\u0018\u00010UH\u0016J\u001a\u0010V\u001a\u00020&2\u0006\u0010W\u001a\u00020J2\b\u0010F\u001a\u0004\u0018\u00010GH\u0016J\u0010\u0010X\u001a\u00020&2\u0006\u0010Y\u001a\u00020ZH\u0002J\b\u0010[\u001a\u00020&H\u0002J\u0010\u0010\\\u001a\u00020&2\u0006\u0010]\u001a\u00020^H\u0002J\u0010\u0010_\u001a\u00020&2\u0006\u0010`\u001a\u00020aH\u0002R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010#\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010%\u0012\u0004\u0012\u00020&\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u00020*X\u0082\u0004¢\u0006\u0004\n\u0002\u0010+R\u001b\u0010,\u001a\u00020-8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b0\u00101\u001a\u0004\b.\u0010/¨\u0006c"}, d2 = {"Lcom/amazon/mp3/auto/carmode/fragment/CarModeV2BrushFragment;", "Lcom/amazon/mp3/auto/carmode/fragment/CarModeBaseFragment;", "Lcom/amazon/mp3/auto/carmode/CarModeAlexaActionsHandler;", "Lcom/amazon/podcast/media/playback/Media$PodcastMediaMetadataCallback;", "Lcom/amazon/music/media/playback/OnPlayStateChangedListener;", "()V", "alexaFabViewController", "Lcom/amazon/mp3/auto/CarModeAlexaFabViewController;", "alexaFloatingActionButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "backgroundImageView", "Landroidx/appcompat/widget/AppCompatImageView;", "carModeCloseButton", "carModeHomeRecyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "carModeMiniPlayerView", "Lcom/amazon/mp3/auto/carmode/view/CarModeMiniPlayerView;", "contentViewManager", "Lcom/amazon/music/uicontentview/ContentViewManager;", "flickAndStickSnapHelper", "Lcom/amazon/mp3/auto/carmode/snaphelper/FlickAndStickSnapHelper;", "isAutoPlayHandledOnIngress", "", "isBrushViewSetup", "isViewStylized", "lastMediaItem", "Lcom/amazon/music/media/playback/MediaItem;", "loadingSpinner", "Landroid/widget/ProgressBar;", "metadataClickHandler", "Lcom/amazon/mp3/auto/playback/CarModeMetadataClickListener;", "miniPlayerViewController", "Lcom/amazon/mp3/auto/carmode/view/CarModeMiniPlayerViewController;", "onErrorDialog", "Lcom/amazon/mp3/auto/carmode/view/CarModeEmbeddedDialog;", "pageErrorCallback", "Lkotlin/Function1;", "", "", "playbackController", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "seeMoreProvider", "com/amazon/mp3/auto/carmode/fragment/CarModeV2BrushFragment$seeMoreProvider$1", "Lcom/amazon/mp3/auto/carmode/fragment/CarModeV2BrushFragment$seeMoreProvider$1;", "viewModel", "Lcom/amazon/mp3/auto/carmode/fragment/viewmodel/CarModeBrushViewModel;", "getViewModel", "()Lcom/amazon/mp3/auto/carmode/fragment/viewmodel/CarModeBrushViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "autoPlayFirstEntity", "createBrushRootView", "brushView", "Lcom/amazon/mp3/brush/BrushViews;", "handleExitCarMode", "onComplete", "Lkotlin/Function2;", "Lcom/amazon/mp3/navigation/ContextAwareDeeplinkManager$Companion$Status;", "Lkotlin/ParameterName;", "name", NotificationCompat.CATEGORY_STATUS, "", "message", "handlePresetPlayback", "presetNumber", "", "handlePresetsPlaybackNext", "handlePresetsPlaybackPrevious", "hasData", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onCreate", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroy", "onDestroyView", "onPause", "onPlayStateChanged", "onPodcastMediaMetadataChange", "metadata", "Lcom/amazon/podcast/external/car/PodcastMediaMetadata;", "onViewCreated", "view", "reportPageLoadLatency", "leg", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/LatencyTrackingLeg;", "setupBrushViews", "showErrorDialog", "context", "Landroid/content/Context;", "stylizeViews", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CarModeV2BrushFragment extends CarModeBaseFragment implements CarModeAlexaActionsHandler, Media.PodcastMediaMetadataCallback, OnPlayStateChangedListener {
    private static final String TAG = CarModeV2BrushFragment.class.getSimpleName();
    private CarModeAlexaFabViewController alexaFabViewController;
    private BaseButton alexaFloatingActionButton;
    private AppCompatImageView backgroundImageView;
    private BaseButton carModeCloseButton;
    private RecyclerView carModeHomeRecyclerView;
    private CarModeMiniPlayerView carModeMiniPlayerView;
    private boolean isAutoPlayHandledOnIngress;
    private boolean isBrushViewSetup;
    private boolean isViewStylized;
    private MediaItem lastMediaItem;
    private ProgressBar loadingSpinner;
    private CarModeMetadataClickListener metadataClickHandler;
    private CarModeMiniPlayerViewController miniPlayerViewController;
    private CarModeEmbeddedDialog onErrorDialog;

    /* JADX INFO: renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(CarModeBrushViewModel.class), new Function0<ViewModelStore>() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeV2BrushFragment$special$$inlined$activityViewModels$default$1
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ViewModelStore invoke() {
            FragmentActivity fragmentActivityRequireActivity = this.requireActivity();
            Intrinsics.checkExpressionValueIsNotNull(fragmentActivityRequireActivity, "requireActivity()");
            ViewModelStore viewModelStore = fragmentActivityRequireActivity.getViewModelStore();
            Intrinsics.checkExpressionValueIsNotNull(viewModelStore, "requireActivity().viewModelStore");
            return viewModelStore;
        }
    }, new Function0<ViewModelProvider.Factory>() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeV2BrushFragment$special$$inlined$activityViewModels$default$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ViewModelProvider.Factory invoke() {
            FragmentActivity fragmentActivityRequireActivity = this.requireActivity();
            Intrinsics.checkExpressionValueIsNotNull(fragmentActivityRequireActivity, "requireActivity()");
            ViewModelProvider.Factory defaultViewModelProviderFactory = fragmentActivityRequireActivity.getDefaultViewModelProviderFactory();
            Intrinsics.checkExpressionValueIsNotNull(defaultViewModelProviderFactory, "requireActivity().defaultViewModelProviderFactory");
            return defaultViewModelProviderFactory;
        }
    });
    private final ActionValidatedPlaybackController playbackController = PlaybackControllerProvider.INSTANCE.getController(ControlSource.APP_UI);
    private final ContentViewManager contentViewManager = new ContentViewManager();
    private final FlickAndStickSnapHelper flickAndStickSnapHelper = new FlickAndStickSnapHelper(0.0f, 0, 3, null);
    private Function1<? super Throwable, Unit> pageErrorCallback = new CarModeV2BrushFragment$pageErrorCallback$1(this);
    private final CarModeV2BrushFragment$seeMoreProvider$1 seeMoreProvider = new SeeMoreProvider() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeV2BrushFragment$seeMoreProvider$1
        @Override // com.amazon.music.views.library.providers.SeeMoreProvider
        public void onSeeMoreClick(String uri, String viewId, String nextPageToken) {
            AutoUiEventUtil.sendUiClickEvent(ActionType.SELECT_SEE_ALL, PageType.CAR_MODE_HOME, ExperienceMode.CAR_MODE, viewId);
            Bundle bundle = new Bundle();
            bundle.putString("carModeDetailPageUri", uri);
            CarModeNavigation navigationListener = this.this$0.getNavigationListener();
            if (navigationListener == null) {
                return;
            }
            navigationListener.navigateTo(CarModeAction.SHOW_DETAIL_PAGE, bundle);
        }
    };

    private final CarModeBrushViewModel getViewModel() {
        return (CarModeBrushViewModel) this.viewModel.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BrushLatencyReportingUtil.INSTANCE.trackLatency("carmode-home", LatencyTrackingLeg.INITIALIZING);
        getSubscriptions().add(StyleSheetProvider.getStyleSheetObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeV2BrushFragment$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CarModeV2BrushFragment.m314onCreate$lambda0(this.f$0, (StyleSheet) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeV2BrushFragment$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CarModeV2BrushFragment.m315onCreate$lambda1((Throwable) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-0, reason: not valid java name */
    public static final void m314onCreate$lambda0(CarModeV2BrushFragment this$0, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setStyleSheet(styleSheet);
        this$0.setupBrushViews();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-1, reason: not valid java name */
    public static final void m315onCreate$lambda1(Throwable th) {
        Log.error(TAG, Intrinsics.stringPlus("Failed to fetch stylesheet with error ", th));
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        OnBackPressedDispatcher onBackPressedDispatcher;
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        super.onCreateView(inflater, container, savedInstanceState);
        FragmentActivity activity = getActivity();
        if (activity != null && (onBackPressedDispatcher = activity.getOnBackPressedDispatcher()) != null) {
            onBackPressedDispatcher.addCallback(getViewLifecycleOwner(), new OnBackPressedCallback() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeV2BrushFragment.onCreateView.1
                {
                    super(true);
                }

                @Override // androidx.activity.OnBackPressedCallback
                public void handleOnBackPressed() {
                    CarModeNavigation navigationListener;
                    if (CarModeV2BrushFragment.this.getContext() == null || (navigationListener = CarModeV2BrushFragment.this.getNavigationListener()) == null) {
                        return;
                    }
                    CarModeNavigation.DefaultImpls.navigateTo$default(navigationListener, CarModeAction.SHOW_EXIT_PAGE, null, 2, null);
                }
            });
        }
        View viewInflate = inflater.inflate(R.layout.car_mode_home, container, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflater.inflate(R.layou…e_home, container, false)");
        setRoot(viewInflate);
        View viewFindViewById = getRoot().findViewById(R.id.car_mode_blur_background);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "root.findViewById(R.id.car_mode_blur_background)");
        this.backgroundImageView = (AppCompatImageView) viewFindViewById;
        View viewFindViewById2 = getRoot().findViewById(R.id.car_mode_list);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "root.findViewById(R.id.car_mode_list)");
        this.carModeHomeRecyclerView = (RecyclerView) viewFindViewById2;
        View viewFindViewById3 = getRoot().findViewById(R.id.car_mode_mini_player);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "root.findViewById(R.id.car_mode_mini_player)");
        this.carModeMiniPlayerView = (CarModeMiniPlayerView) viewFindViewById3;
        View viewFindViewById4 = getRoot().findViewById(R.id.car_mode_close_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "root.findViewById(R.id.car_mode_close_button)");
        this.carModeCloseButton = (BaseButton) viewFindViewById4;
        View viewFindViewById5 = getRoot().findViewById(R.id.car_mode_alexa_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "root.findViewById(R.id.car_mode_alexa_button)");
        this.alexaFloatingActionButton = (BaseButton) viewFindViewById5;
        View viewFindViewById6 = getRoot().findViewById(R.id.loading_spinner);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById6, "root.findViewById(R.id.loading_spinner)");
        this.loadingSpinner = (ProgressBar) viewFindViewById6;
        View viewFindViewById7 = getRoot().findViewById(R.id.car_mode_on_error_dialog);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById7, "root.findViewById(R.id.car_mode_on_error_dialog)");
        this.onErrorDialog = (CarModeEmbeddedDialog) viewFindViewById7;
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
        BaseButton baseButton = this.carModeCloseButton;
        BaseButton baseButton2 = null;
        if (baseButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("carModeCloseButton");
            baseButton = null;
        }
        baseButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeV2BrushFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                CarModeV2BrushFragment.m316onViewCreated$lambda2(this.f$0, view2);
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
        this.alexaFabViewController = new CarModeAlexaFabViewController(contextRequireContext, baseButton2, PageType.CAR_MODE_HOME);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-2, reason: not valid java name */
    public static final void m316onViewCreated$lambda2(CarModeV2BrushFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CarModeNavigation navigationListener = this$0.getNavigationListener();
        if (navigationListener == null) {
            return;
        }
        CarModeNavigation.DefaultImpls.navigateTo$default(navigationListener, CarModeAction.SHOW_EXIT_PAGE, null, 2, null);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        CarModeMiniPlayerView carModeMiniPlayerView;
        super.onActivityCreated(savedInstanceState);
        PageType pageType = PageType.CAR_MODE_HOME;
        AutoUiEventUtil.sendUiPageViewEvent(pageType, ExperienceMode.CAR_MODE);
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        CarModeMiniPlayerView carModeMiniPlayerView2 = this.carModeMiniPlayerView;
        AppCompatImageView appCompatImageView = null;
        if (carModeMiniPlayerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("carModeMiniPlayerView");
            carModeMiniPlayerView = null;
        } else {
            carModeMiniPlayerView = carModeMiniPlayerView2;
        }
        AppCompatImageView appCompatImageView2 = this.backgroundImageView;
        if (appCompatImageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("backgroundImageView");
        } else {
            appCompatImageView = appCompatImageView2;
        }
        CarModeMiniPlayerViewController carModeMiniPlayerViewController = new CarModeMiniPlayerViewController(activity, carModeMiniPlayerView, this, pageType, getBitmapChangeListener(appCompatImageView), new PlayerNavigationListener() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeV2BrushFragment$onActivityCreated$1$1
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
        getViewModel().getGridPageModel().observe(getViewLifecycleOwner(), new Observer() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeV2BrushFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                CarModeV2BrushFragment.m313onActivityCreated$lambda5$lambda4(this.f$0, (PageGridViewModel) obj);
            }
        });
        if (getViewModel().getGridPageModel().getValue() == null) {
            getViewModel().fetchCarModePage("carmode-home", this.pageErrorCallback);
            getViewModel().initEqualizer();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onActivityCreated$lambda-5$lambda-4, reason: not valid java name */
    public static final void m313onActivityCreated$lambda5$lambda4(CarModeV2BrushFragment this$0, PageGridViewModel pageGridViewModel) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BrushLatencyReportingUtil.INSTANCE.trackLatency("carmode-home", LatencyTrackingLeg.RENDERING);
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
        PageType pageType = PageType.CAR_MODE_HOME;
        CarModeMetadataClickListener carModeMetadataClickListener = new CarModeMetadataClickListener(this, pageType, PlaybackPageType.CAR_MODE_HOME);
        this.metadataClickHandler = carModeMetadataClickListener;
        BrushViews carModeBrushView = BrowseViewsFactory.createBrowseViewsV2(this, styleSheet, pageType, this.seeMoreProvider, carModeMetadataClickListener, new BrushUriClickListenerProvider() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeV2BrushFragment$setupBrushViews$1$carModeBrushView$1
            @Override // com.amazon.music.views.library.providers.BrushUriClickListenerProvider
            public void onClick(String target, String viewId, TargetType targetType) {
            }
        }, this.contentViewManager);
        Intrinsics.checkNotNullExpressionValue(carModeBrushView, "carModeBrushView");
        createBrushRootView(carModeBrushView);
    }

    private final void createBrushRootView(BrushViews brushView) {
        RecyclerView recyclerView;
        if (hasData()) {
            UserSubscription userSubscription = UserSubscriptionUtil.getUserSubscription();
            Intrinsics.checkNotNullExpressionValue(userSubscription, "getUserSubscription()");
            PageGridViewModel value = getViewModel().getGridPageModel().getValue();
            ProgressBar progressBar = null;
            List<BaseViewModel> models = value == null ? null : value.getModels();
            if (models == null) {
                models = CollectionsKt__CollectionsKt.emptyList();
            }
            List<BaseViewModel> list = models;
            RecyclerView recyclerView2 = this.carModeHomeRecyclerView;
            if (recyclerView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("carModeHomeRecyclerView");
                recyclerView = null;
            } else {
                recyclerView = recyclerView2;
            }
            BrushViews.createRootView$default(brushView, userSubscription, list, 2, recyclerView, (ViewGroup) getRoot(), null, 32, null);
            if (getContext() != null) {
                RecyclerView recyclerView3 = this.carModeHomeRecyclerView;
                if (recyclerView3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("carModeHomeRecyclerView");
                    recyclerView3 = null;
                }
                recyclerView3.setItemAnimator(null);
                RecyclerView recyclerView4 = this.carModeHomeRecyclerView;
                if (recyclerView4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("carModeHomeRecyclerView");
                    recyclerView4 = null;
                }
                recyclerView4.setLayoutManager(new LinearLayoutManager(getContext()));
                RecyclerView recyclerView5 = this.carModeHomeRecyclerView;
                if (recyclerView5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("carModeHomeRecyclerView");
                    recyclerView5 = null;
                }
                recyclerView5.addItemDecoration(new RecyclerView.ItemDecoration() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeV2BrushFragment$createBrushRootView$1$1
                    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
                    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                        Intrinsics.checkNotNullParameter(outRect, "outRect");
                        Intrinsics.checkNotNullParameter(view, "view");
                        Intrinsics.checkNotNullParameter(parent, "parent");
                        Intrinsics.checkNotNullParameter(state, "state");
                        outRect.bottom = this.this$0.getResources().getDimensionPixelSize(R.dimen.spacer_36);
                    }
                });
                RecyclerView recyclerView6 = this.carModeHomeRecyclerView;
                if (recyclerView6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("carModeHomeRecyclerView");
                    recyclerView6 = null;
                }
                recyclerView6.setClipToPadding(false);
                int deviceHeight = ScreenUtil.getDeviceHeight();
                RecyclerView recyclerView7 = this.carModeHomeRecyclerView;
                if (recyclerView7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("carModeHomeRecyclerView");
                    recyclerView7 = null;
                }
                recyclerView7.setPadding(0, 0, 0, deviceHeight);
                FlickAndStickSnapHelper flickAndStickSnapHelper = this.flickAndStickSnapHelper;
                RecyclerView recyclerView8 = this.carModeHomeRecyclerView;
                if (recyclerView8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("carModeHomeRecyclerView");
                    recyclerView8 = null;
                }
                flickAndStickSnapHelper.attachToRecyclerView(recyclerView8);
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
            Log.info(TAG, Intrinsics.stringPlus("Auto Play status:: ", Boolean.valueOf(this.isAutoPlayHandledOnIngress)));
            if (this.isAutoPlayHandledOnIngress) {
                return;
            }
            this.isAutoPlayHandledOnIngress = true;
            if (getViewModel().isPlayQueueActive()) {
                getViewModel().autoPlayIfMusicOrPodcastPaused(this.playbackController);
            } else {
                autoPlayFirstEntity();
                PlaybackHandlerUtil.INSTANCE.emitAutoPlayFlexEvent(CarModeIngressAutoPlayTypes.EMPTY_PLAY_QUEUE_PLAY_FIRST_PRESET);
            }
        }
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
        carModeEmbeddedDialog.setPositiveButton(string, new View.OnClickListener() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeV2BrushFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CarModeV2BrushFragment.m317showErrorDialog$lambda8(this.f$0, view);
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
    /* JADX INFO: renamed from: showErrorDialog$lambda-8, reason: not valid java name */
    public static final void m317showErrorDialog$lambda8(CarModeV2BrushFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ProgressBar progressBar = this$0.loadingSpinner;
        CarModeEmbeddedDialog carModeEmbeddedDialog = null;
        if (progressBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("loadingSpinner");
            progressBar = null;
        }
        progressBar.setVisibility(0);
        CarModeEmbeddedDialog carModeEmbeddedDialog2 = this$0.onErrorDialog;
        if (carModeEmbeddedDialog2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onErrorDialog");
        } else {
            carModeEmbeddedDialog = carModeEmbeddedDialog2;
        }
        carModeEmbeddedDialog.hide();
        this$0.getViewModel().fetchCarModePage("carmode-home", this$0.pageErrorCallback);
    }

    private final void reportPageLoadLatency(LatencyTrackingLeg leg) {
        if (getViewModel().getInitPageLoad()) {
            BrushLatencyReportingUtil.INSTANCE.trackLatency("carmode-home", leg);
        }
    }

    private final void stylizeViews(StyleSheet styleSheet) {
        BaseButton.StyleBuilder styleBuilderWithIcon;
        if (this.isViewStylized) {
            return;
        }
        this.isViewStylized = true;
        BaseButton.StyleBuilder iconBuilder = styleSheet.getIconBuilder(IconSizeKey.LARGE, IconStyleKey.GLASS);
        BaseButton baseButton = this.carModeCloseButton;
        CarModeEmbeddedDialog carModeEmbeddedDialog = null;
        if (baseButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("carModeCloseButton");
            baseButton = null;
        }
        baseButton.setBackgroundResource(R.drawable.ic_car_mode_close_button);
        if (iconBuilder != null) {
            BaseButton baseButton2 = this.carModeCloseButton;
            if (baseButton2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("carModeCloseButton");
                baseButton2 = null;
            }
            iconBuilder.applyStyle(baseButton2);
        }
        Drawable drawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_alexa_bauhaus);
        if (drawable != null && iconBuilder != null && (styleBuilderWithIcon = iconBuilder.withIcon(drawable)) != null) {
            BaseButton baseButton3 = this.alexaFloatingActionButton;
            if (baseButton3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("alexaFloatingActionButton");
                baseButton3 = null;
            }
            styleBuilderWithIcon.applyStyle(baseButton3);
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

    private final void autoPlayFirstEntity() {
        List<BaseViewModel> models;
        CarModeMetadataClickListener carModeMetadataClickListener;
        PageGridViewModel value = getViewModel().getGridPageModel().getValue();
        List<BaseViewModel> models2 = value == null ? null : value.getModels();
        if (models2 == null || models2.isEmpty()) {
            return;
        }
        BaseViewModel baseViewModel = models2.get(0);
        SimpleShovelerModel simpleShovelerModel = baseViewModel instanceof SimpleShovelerModel ? (SimpleShovelerModel) baseViewModel : null;
        BaseViewModel baseViewModel2 = (simpleShovelerModel == null || (models = simpleShovelerModel.getModels()) == null) ? null : models.get(0);
        SimpleVerticalTileModel simpleVerticalTileModel = baseViewModel2 instanceof SimpleVerticalTileModel ? (SimpleVerticalTileModel) baseViewModel2 : null;
        ContentMetadata metadata = simpleVerticalTileModel != null ? simpleVerticalTileModel.getMetadata() : null;
        if (metadata == null || (carModeMetadataClickListener = this.metadataClickHandler) == null) {
            return;
        }
        carModeMetadataClickListener.onContentClicked(metadata);
    }

    @Override // com.amazon.mp3.auto.carmode.CarModeAlexaActionsHandler
    public void handlePresetsPlaybackNext(Function2<? super ContextAwareDeeplinkManager.Companion.Status, ? super String, Unit> onComplete) {
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        onComplete.mo4invoke(ContextAwareDeeplinkManager.Companion.Status.FAILURE, "Preset @next not supported");
    }

    @Override // com.amazon.mp3.auto.carmode.CarModeAlexaActionsHandler
    public void handlePresetsPlaybackPrevious(Function2<? super ContextAwareDeeplinkManager.Companion.Status, ? super String, Unit> onComplete) {
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        onComplete.mo4invoke(ContextAwareDeeplinkManager.Companion.Status.FAILURE, "Preset @previous not supported");
    }

    @Override // com.amazon.mp3.auto.carmode.CarModeAlexaActionsHandler
    public void handlePresetPlayback(int presetNumber, Function2<? super ContextAwareDeeplinkManager.Companion.Status, ? super String, Unit> onComplete) {
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        onComplete.mo4invoke(ContextAwareDeeplinkManager.Companion.Status.FAILURE, "Preset @" + presetNumber + " not supported");
    }

    @Override // com.amazon.mp3.auto.carmode.CarModeAlexaActionsHandler
    public void handleExitCarMode(Function2<? super ContextAwareDeeplinkManager.Companion.Status, ? super String, Unit> onComplete) {
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        onComplete.mo4invoke(ContextAwareDeeplinkManager.Companion.Status.SUCCESS, null);
        if (getContext() == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putBoolean("carModeUserExit", true);
        CarModeNavigation navigationListener = getNavigationListener();
        if (navigationListener == null) {
            return;
        }
        navigationListener.navigateTo(CarModeAction.EXIT_CAR_MODE, bundle);
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
