package com.amazon.mp3.view.stage;

import Touch.SwipeablePagesTemplateInterface.v1_0.SwipeablePagesTemplate;
import Touch.WidgetsInterface.v1_0.PillNavigatorWidgetItemElement;
import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.BootstrapSingletonTask;
import com.amazon.mp3.activity.navigation.FragmentController;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.dmsfcore.providers.DMSFEnvironmentProvider;
import com.amazon.mp3.dmsfcore.providers.DMSFNavigationProvider;
import com.amazon.mp3.dmsfcore.providers.DMSFStorageProvider;
import com.amazon.mp3.platform.providers.AuthenticationProviderImpl;
import com.amazon.mp3.platform.providers.CustomerMetadataProviderImpl;
import com.amazon.mp3.playback.activity.NowPlayingFragmentActivity;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.playback.view.MiniPlayerView;
import com.amazon.mp3.playback.view.MiniPlayerViewController;
import com.amazon.mp3.util.CountDownTimer;
import com.amazon.mp3.view.TouchEventHandler;
import com.amazon.mp3.view.stage.StageDataModel;
import com.amazon.music.explore.events.SingleLiveEvent;
import com.amazon.music.explore.fragment.StageSwipeablePagesFragment;
import com.amazon.music.explore.managers.ExploreXrayManager;
import com.amazon.music.explore.views.swipeablePages.StageSwipeablePagesView;
import com.amazon.music.explore.views.swipeablePages.SwipeablePagesStyleSheet;
import com.amazon.music.explore.widgets.views.PillNavigatorWidgetItemView;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.BuildInfoProvider;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.ui.foundations.views.BaseButton;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import rx.Subscription;
import rx.functions.Action1;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

/* JADX INFO: compiled from: StageXRayManager.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0086\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 \u0085\u00012\u00020\u0001:\u0006\u0085\u0001\u0086\u0001\u0087\u0001BM\u0012\u0006\u0010A\u001a\u00020@\u0012\f\u0010E\u001a\b\u0012\u0004\u0012\u00020D0C\u0012\b\u0010H\u001a\u0004\u0018\u00010G\u0012\u0006\u0010K\u001a\u00020J\u0012\f\u0010N\u001a\b\u0012\u0004\u0012\u00020\u00040M\u0012\f\u0010P\u001a\b\u0012\u0004\u0012\u00020\u00040M¢\u0006\u0006\b\u0083\u0001\u0010\u0084\u0001J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0002H\u0002J\b\u0010\b\u001a\u00020\u0004H\u0002J\n\u0010\n\u001a\u0004\u0018\u00010\tH\u0002J\n\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0002J\u0010\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\rH\u0002J\b\u0010\u0010\u001a\u00020\u0004H\u0002J\u000e\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0011J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u00162\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014J\u0010\u0010\u0018\u001a\u00020\u00042\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014J\u000e\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0019J\u0010\u0010\u001d\u001a\u00020\u00042\b\u0010\u001c\u001a\u0004\u0018\u00010\u0014J\u000e\u0010 \u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u001eJ\u0006\u0010!\u001a\u00020\u0004J\u0006\u0010\"\u001a\u00020\u0004J\u000e\u0010%\u001a\u00020\u00042\u0006\u0010$\u001a\u00020#J\u0010\u0010(\u001a\u00020\u00042\b\u0010'\u001a\u0004\u0018\u00010&J1\u0010/\u001a\u00020\u00042\b\u0010*\u001a\u0004\u0018\u00010)2\u0006\u0010,\u001a\u00020+2\u0006\u0010-\u001a\u00020+2\b\u0010.\u001a\u0004\u0018\u00010\u001f¢\u0006\u0004\b/\u00100J\u0006\u00101\u001a\u00020\u0004J\u0006\u00102\u001a\u00020\u0004J\u0006\u00103\u001a\u00020\u001fJ\b\u00105\u001a\u0004\u0018\u000104J\u0006\u00106\u001a\u00020\u0004J\u000e\u00107\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u0010\u00109\u001a\u0004\u0018\u0001082\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010;\u001a\u00020\u00042\u0006\u0010:\u001a\u00020\u001fJ\u000e\u0010<\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\u001fJ\u0010\u0010?\u001a\u00020\u00042\b\u0010>\u001a\u0004\u0018\u00010=R\u0014\u0010A\u001a\u00020@8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bA\u0010BR\u001a\u0010E\u001a\b\u0012\u0004\u0012\u00020D0C8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bE\u0010FR\u0016\u0010H\u001a\u0004\u0018\u00010G8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bH\u0010IR\u0014\u0010K\u001a\u00020J8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bK\u0010LR\u001a\u0010N\u001a\b\u0012\u0004\u0012\u00020\u00040M8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bN\u0010OR\u001a\u0010P\u001a\b\u0012\u0004\u0012\u00020\u00040M8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bP\u0010OR\u0014\u0010R\u001a\u00020Q8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bR\u0010SR\u0018\u0010U\u001a\u0004\u0018\u00010T8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bU\u0010VR\u0014\u0010W\u001a\u00020\t8\u0002X\u0082D¢\u0006\u0006\n\u0004\bW\u0010XR\u0014\u0010Y\u001a\u00020\t8\u0002X\u0082D¢\u0006\u0006\n\u0004\bY\u0010XR\u0014\u0010[\u001a\u00020Z8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b[\u0010\\R\u0014\u0010^\u001a\u00020]8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b^\u0010_R\u0014\u0010a\u001a\u00020`8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\ba\u0010bR\u0018\u0010c\u001a\u0004\u0018\u0001048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bc\u0010dR\u0016\u0010f\u001a\u00020e8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bf\u0010gR\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00148\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0015\u0010hR\u0018\u0010j\u001a\u0004\u0018\u00010i8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bj\u0010kR$\u0010m\u001a\u0004\u0018\u00010l8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bm\u0010n\u001a\u0004\bo\u0010p\"\u0004\b\u0007\u0010qR/\u0010s\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u001f0r0\u001e8\u0006¢\u0006\f\n\u0004\bs\u0010t\u001a\u0004\bu\u0010vR\u0018\u0010w\u001a\u0004\u0018\u00010=8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bw\u0010xR\u0016\u0010y\u001a\u00020\u001f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\by\u0010zR\u0016\u0010{\u001a\u00020\u001f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b{\u0010zR\u0016\u0010|\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b|\u0010}R\u0015\u0010\u007f\u001a\u00020~8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u007f\u0010\u0080\u0001R\u0014\u0010\u0081\u0001\u001a\u00020\u001f8F¢\u0006\b\u001a\u0006\b\u0081\u0001\u0010\u0082\u0001¨\u0006\u0088\u0001"}, d2 = {"Lcom/amazon/mp3/view/stage/StageXRayManager;", "", "Landroid/view/View;", "view", "", "setXrayPillButton", "xrayView", "setXrayContainer", "createExploreXrayManager", "", "getTrackAsin", "Landroid/widget/LinearLayout;", "createXrayMiniPlayer", "Lcom/amazon/mp3/playback/view/MiniPlayerView;", "miniPlayerView", "initializeXrayMiniPlayerViewController", "setInteractionListener", "Lcom/amazon/mp3/view/stage/StageXRayManager$XrayState;", "xrayState", "requestXrayOverlayIfXrayAvailable", "Lcom/amazon/mp3/view/stage/StageOverlayButtonsLayout;", "overlayButtonsContainerLayout", "Lcom/amazon/music/explore/widgets/views/PillNavigatorWidgetItemView;", "findXrayButton", "removeXrayPillButtonIfAlreadyExists", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "initViewStyling", "stageOverlayButtonsLayout", "setOverlayButtonContainer", "Lrx/subjects/PublishSubject;", "", "getXrayFinishEvent", "clearXray", "clearXrayManager", "Lcom/amazon/music/media/playback/MediaItem;", "mediaItem", "setContainerForPartialOverlays", "LTouch/WidgetsInterface/v1_0/PillNavigatorWidgetItemElement;", "pillNavigatorWidgetItemElement", "setXrayPillButtonView", "LTouch/SwipeablePagesTemplateInterface/v1_0/SwipeablePagesTemplate;", "template", "", "currentPageIndex", "currentSectionIndex", "hasUserInteracted", "setXrayContainerView", "(LTouch/SwipeablePagesTemplateInterface/v1_0/SwipeablePagesTemplate;IILjava/lang/Boolean;)V", "selectXrayPillButton", "deselectXrayPillButton", "isFullScreenXrayOpen", "Lcom/amazon/ui/foundations/views/BaseButton;", "getXrayPillButton", "removeViewFromFullScreenFragment", "addViewInFullScreenFragment", "Lcom/amazon/music/explore/fragment/StageSwipeablePagesFragment;", "getXrayFullScreenFragment", "isFullScreen", "setIsXrayFullScreen", "setHasUserInteracted", "Lcom/amazon/mp3/view/stage/StageXRayManager$XrayWindowInteractionListener;", "xrayListener", "setXrayWindowInteractionListener", "Landroid/content/Context;", "context", "Landroid/content/Context;", "Ljava/lang/ref/WeakReference;", "Landroidx/fragment/app/FragmentActivity;", "fragmentActivity", "Ljava/lang/ref/WeakReference;", "Lcom/amazon/mp3/activity/navigation/FragmentController;", "fragmentController", "Lcom/amazon/mp3/activity/navigation/FragmentController;", "Lcom/amazon/mp3/view/stage/StageViewModel;", "stageViewModel", "Lcom/amazon/mp3/view/stage/StageViewModel;", "Lkotlin/Function0;", "intializeXrayContainer", "Lkotlin/jvm/functions/Function0;", "onXrayButtonClick", "Lcom/amazon/mp3/util/CountDownTimer;", "xraySkillTimeoutManager", "Lcom/amazon/mp3/util/CountDownTimer;", "Lcom/amazon/music/explore/managers/ExploreXrayManager;", "exploreXrayManager", "Lcom/amazon/music/explore/managers/ExploreXrayManager;", "XRAY_FULL_SCREEN_TAG", "Ljava/lang/String;", "XRAY_BUTTON_TAG", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "playbackController", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "Lcom/amazon/mp3/playback/activity/NowPlayingFragmentActivity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Lcom/amazon/mp3/playback/activity/NowPlayingFragmentActivity;", "Lrx/Subscription;", "xrayManagerSingletonTask", "Lrx/Subscription;", "xrayPillButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "Lcom/amazon/music/explore/views/swipeablePages/SwipeablePagesStyleSheet;", "bauhausStyleSheet", "Lcom/amazon/music/explore/views/swipeablePages/SwipeablePagesStyleSheet;", "Lcom/amazon/mp3/view/stage/StageOverlayButtonsLayout;", "Lcom/amazon/mp3/playback/view/MiniPlayerViewController;", "xrayMiniPlayerViewController", "Lcom/amazon/mp3/playback/view/MiniPlayerViewController;", "Lcom/amazon/music/explore/views/swipeablePages/StageSwipeablePagesView;", "xrayContainer", "Lcom/amazon/music/explore/views/swipeablePages/StageSwipeablePagesView;", "getXrayContainer", "()Lcom/amazon/music/explore/views/swipeablePages/StageSwipeablePagesView;", "(Lcom/amazon/music/explore/views/swipeablePages/StageSwipeablePagesView;)V", "Lkotlin/Triple;", "xrayContainerSubject", "Lrx/subjects/PublishSubject;", "getXrayContainerSubject", "()Lrx/subjects/PublishSubject;", "xrayWindowInteractionListener", "Lcom/amazon/mp3/view/stage/StageXRayManager$XrayWindowInteractionListener;", "isXrayCheckedForTrack", "Z", "couldNotRequestXray", "currentXrayState", "Lcom/amazon/mp3/view/stage/StageXRayManager$XrayState;", "Lcom/amazon/music/explore/fragment/StageSwipeablePagesFragment$ContextItemSelectionListener;", "contextItemSelectionListener", "Lcom/amazon/music/explore/fragment/StageSwipeablePagesFragment$ContextItemSelectionListener;", "isLyricsSharingEnabled", "()Z", "<init>", "(Landroid/content/Context;Ljava/lang/ref/WeakReference;Lcom/amazon/mp3/activity/navigation/FragmentController;Lcom/amazon/mp3/view/stage/StageViewModel;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "Companion", "XrayState", "XrayWindowInteractionListener", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class StageXRayManager {
    private static final String TAG;
    private static final long XRAY_SKILL_TIMEOUT_MS;
    private final String XRAY_BUTTON_TAG;
    private final String XRAY_FULL_SCREEN_TAG;
    private final NowPlayingFragmentActivity activity;
    private SwipeablePagesStyleSheet bauhausStyleSheet;
    private final Context context;
    private final StageSwipeablePagesFragment.ContextItemSelectionListener contextItemSelectionListener;
    private boolean couldNotRequestXray;
    private XrayState currentXrayState;
    private ExploreXrayManager exploreXrayManager;
    private final WeakReference<FragmentActivity> fragmentActivity;
    private final FragmentController fragmentController;
    private final Function0<Unit> intializeXrayContainer;
    private boolean isXrayCheckedForTrack;
    private final Function0<Unit> onXrayButtonClick;
    private StageOverlayButtonsLayout overlayButtonsContainerLayout;
    private final ActionValidatedPlaybackController playbackController;
    private final StageViewModel stageViewModel;
    private StageSwipeablePagesView xrayContainer;
    private final PublishSubject<Triple<MediaItem, View, Boolean>> xrayContainerSubject;
    private final Subscription xrayManagerSingletonTask;
    private MiniPlayerViewController xrayMiniPlayerViewController;
    private BaseButton xrayPillButton;
    private final CountDownTimer xraySkillTimeoutManager;
    private XrayWindowInteractionListener xrayWindowInteractionListener;

    /* JADX INFO: compiled from: StageXRayManager.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/view/stage/StageXRayManager$XrayState;", "", "(Ljava/lang/String;I)V", "PUSH_STATE", "USER_INITIATED_STATE", "HIDDEN", "FULL_SCREEN", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum XrayState {
        PUSH_STATE,
        USER_INITIATED_STATE,
        HIDDEN,
        FULL_SCREEN
    }

    /* JADX INFO: compiled from: StageXRayManager.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0002H&¨\u0006\u0004"}, d2 = {"Lcom/amazon/mp3/view/stage/StageXRayManager$XrayWindowInteractionListener;", "", "", "onXrayWindowClicked", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public interface XrayWindowInteractionListener {
        void onXrayWindowClicked();
    }

    public StageXRayManager(Context context, WeakReference<FragmentActivity> fragmentActivity, FragmentController fragmentController, StageViewModel stageViewModel, Function0<Unit> intializeXrayContainer, Function0<Unit> onXrayButtonClick) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(fragmentActivity, "fragmentActivity");
        Intrinsics.checkNotNullParameter(stageViewModel, "stageViewModel");
        Intrinsics.checkNotNullParameter(intializeXrayContainer, "intializeXrayContainer");
        Intrinsics.checkNotNullParameter(onXrayButtonClick, "onXrayButtonClick");
        this.context = context;
        this.fragmentActivity = fragmentActivity;
        this.fragmentController = fragmentController;
        this.stageViewModel = stageViewModel;
        this.intializeXrayContainer = intializeXrayContainer;
        this.onXrayButtonClick = onXrayButtonClick;
        final long j = XRAY_SKILL_TIMEOUT_MS;
        this.xraySkillTimeoutManager = new CountDownTimer(j, j) { // from class: com.amazon.mp3.view.stage.StageXRayManager$xraySkillTimeoutManager$1
            @Override // com.amazon.mp3.util.CountDownTimer
            public void onTick(long millisUntilFinished) {
            }

            @Override // com.amazon.mp3.util.CountDownTimer
            public void onFinish() {
                StageDataModel stageDataModel;
                BehaviorSubject<StageDataModel.State> xRayAvailability;
                if (this.this$0.getXrayContainer() != null || (stageDataModel = this.this$0.stageViewModel.getStageDataModel()) == null || (xRayAvailability = stageDataModel.getXRayAvailability()) == null) {
                    return;
                }
                xRayAvailability.onNext(StageDataModel.State.UNAVAILABLE);
            }
        };
        this.XRAY_FULL_SCREEN_TAG = "StageSwipeablePagesFragment";
        this.XRAY_BUTTON_TAG = "xrayPillButton";
        this.playbackController = PlaybackControllerProvider.INSTANCE.getController(ControlSource.APP_UI);
        FragmentActivity fragmentActivity2 = fragmentActivity.get();
        if (fragmentActivity2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.playback.activity.NowPlayingFragmentActivity");
        }
        this.activity = (NowPlayingFragmentActivity) fragmentActivity2;
        PublishSubject<Triple<MediaItem, View, Boolean>> publishSubjectCreate = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate, "create()");
        this.xrayContainerSubject = publishSubjectCreate;
        this.currentXrayState = XrayState.HIDDEN;
        this.contextItemSelectionListener = new StageSwipeablePagesFragment.ContextItemSelectionListener() { // from class: com.amazon.mp3.view.stage.StageXRayManager$$ExternalSyntheticLambda2
            @Override // com.amazon.music.explore.fragment.StageSwipeablePagesFragment.ContextItemSelectionListener
            public final boolean onContextItemSelected(MenuItem menuItem) {
                return StageXRayManager.m1801contextItemSelectionListener$lambda0(this.f$0, menuItem);
            }
        };
        Subscription subscriptionSubscribe = BootstrapSingletonTask.get().registerUserObserver().take(1).subscribe(new Action1() { // from class: com.amazon.mp3.view.stage.StageXRayManager$$ExternalSyntheticLambda3
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                StageXRayManager.m1800_init_$lambda1(this.f$0, (Void) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(subscriptionSubscribe, "get()\n                .r…ateExploreXrayManager() }");
        this.xrayManagerSingletonTask = subscriptionSubscribe;
    }

    static {
        String simpleName = StageXRayManager.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "StageXRayManager::class.java.simpleName");
        TAG = simpleName;
        XRAY_SKILL_TIMEOUT_MS = 4000L;
    }

    public final StageSwipeablePagesView getXrayContainer() {
        return this.xrayContainer;
    }

    public final PublishSubject<Triple<MediaItem, View, Boolean>> getXrayContainerSubject() {
        return this.xrayContainerSubject;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: contextItemSelectionListener$lambda-0, reason: not valid java name */
    public static final boolean m1801contextItemSelectionListener$lambda0(StageXRayManager this$0, MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MiniPlayerViewController miniPlayerViewController = this$0.xrayMiniPlayerViewController;
        if (miniPlayerViewController == null) {
            return false;
        }
        return miniPlayerViewController.onContextItemSelected(menuItem);
    }

    public final boolean isLyricsSharingEnabled() {
        return AmazonApplication.getCapabilities().isLyricsSharingEnabled();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-1, reason: not valid java name */
    public static final void m1800_init_$lambda1(StageXRayManager this$0, Void r1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.createExploreXrayManager();
    }

    public final void requestXrayOverlayIfXrayAvailable(final XrayState xrayState) {
        SingleLiveEvent<PillNavigatorWidgetItemView> pillButton;
        Intrinsics.checkNotNullParameter(xrayState, "xrayState");
        final MediaItem currentMediaItem = this.playbackController.getCurrentMediaItem();
        final String mediaItemIdString = MediaItem.getMediaItemIdString(currentMediaItem, MediaItemId.Type.ASIN, null);
        this.currentXrayState = xrayState;
        boolean z = true;
        if (mediaItemIdString != null && this.exploreXrayManager != null) {
            if (this.isXrayCheckedForTrack) {
                return;
            }
            this.isXrayCheckedForTrack = true;
            StageSwipeablePagesView stageSwipeablePagesView = this.xrayContainer;
            if (stageSwipeablePagesView == null) {
                this.xraySkillTimeoutManager.start();
                ExploreXrayManager exploreXrayManager = this.exploreXrayManager;
                if (exploreXrayManager != null) {
                    exploreXrayManager.requestXrayButton(mediaItemIdString);
                }
                ExploreXrayManager exploreXrayManager2 = this.exploreXrayManager;
                if (exploreXrayManager2 == null || (pillButton = exploreXrayManager2.getPillButton()) == null) {
                    return;
                }
                pillButton.observe(this.activity, new Observer() { // from class: com.amazon.mp3.view.stage.StageXRayManager$$ExternalSyntheticLambda0
                    @Override // androidx.lifecycle.Observer
                    public final void onChanged(Object obj) {
                        StageXRayManager.m1803requestXrayOverlayIfXrayAvailable$lambda3(mediaItemIdString, this, xrayState, currentMediaItem, (PillNavigatorWidgetItemView) obj);
                    }
                });
                return;
            }
            XrayState xrayState2 = XrayState.PUSH_STATE;
            if (xrayState != xrayState2 && stageSwipeablePagesView != null) {
                stageSwipeablePagesView.setHasUserInteracted(xrayState == XrayState.USER_INITIATED_STATE || xrayState == XrayState.FULL_SCREEN);
            }
            XrayState xrayState3 = this.currentXrayState;
            if (xrayState3 != XrayState.HIDDEN) {
                if (xrayState3 != XrayState.FULL_SCREEN) {
                    StageSwipeablePagesView stageSwipeablePagesView2 = this.xrayContainer;
                    if (stageSwipeablePagesView2 != null) {
                        stageSwipeablePagesView2.setIsFullScreen(false);
                    }
                } else {
                    StageSwipeablePagesView stageSwipeablePagesView3 = this.xrayContainer;
                    if (stageSwipeablePagesView3 != null) {
                        stageSwipeablePagesView3.setIsFullScreen(true);
                    }
                }
                StageSwipeablePagesView stageSwipeablePagesView4 = this.xrayContainer;
                if (stageSwipeablePagesView4 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.explore.views.swipeablePages.StageSwipeablePagesView");
                }
                Boolean boolShouldShowInAutoSequencing = stageSwipeablePagesView4.shouldShowInAutoSequencing();
                Intrinsics.checkNotNullExpressionValue(boolShouldShowInAutoSequencing, "xrayContainer as StageSw…uldShowInAutoSequencing()");
                if (!boolShouldShowInAutoSequencing.booleanValue() && xrayState == xrayState2) {
                    z = false;
                }
                this.xrayContainerSubject.onNext(new Triple<>(currentMediaItem, this.xrayContainer, Boolean.valueOf(z)));
            }
            this.isXrayCheckedForTrack = false;
            return;
        }
        this.couldNotRequestXray = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: requestXrayOverlayIfXrayAvailable$lambda-3, reason: not valid java name */
    public static final void m1803requestXrayOverlayIfXrayAvailable$lambda3(String str, final StageXRayManager this$0, final XrayState xrayState, final MediaItem mediaItem, final View view) {
        BehaviorSubject<StageDataModel.State> xRayAvailability;
        SingleLiveEvent<View> view2;
        BehaviorSubject<StageDataModel.State> xRayAvailability2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(xrayState, "$xrayState");
        if (view != null && Intrinsics.areEqual(str, this$0.getTrackAsin()) && !this$0.stageViewModel.isAdOrInterLude()) {
            StageDataModel stageDataModel = this$0.stageViewModel.getStageDataModel();
            if (stageDataModel != null && (xRayAvailability2 = stageDataModel.getXRayAvailability()) != null) {
                xRayAvailability2.onNext(StageDataModel.State.AVAILABLE);
            }
            this$0.xraySkillTimeoutManager.cancel();
            ExploreXrayManager exploreXrayManager = this$0.exploreXrayManager;
            if (exploreXrayManager != null) {
                exploreXrayManager.requestXray(str);
            }
            ExploreXrayManager exploreXrayManager2 = this$0.exploreXrayManager;
            if (exploreXrayManager2 == null || (view2 = exploreXrayManager2.getView()) == null) {
                return;
            }
            view2.observe(this$0.activity, new Observer() { // from class: com.amazon.mp3.view.stage.StageXRayManager$$ExternalSyntheticLambda1
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    StageXRayManager.m1804requestXrayOverlayIfXrayAvailable$lambda3$lambda2(xrayState, this$0, view, mediaItem, (View) obj);
                }
            });
            return;
        }
        StageDataModel stageDataModel2 = this$0.stageViewModel.getStageDataModel();
        if (stageDataModel2 != null && (xRayAvailability = stageDataModel2.getXRayAvailability()) != null) {
            xRayAvailability.onNext(StageDataModel.State.UNAVAILABLE);
        }
        this$0.removeXrayPillButtonIfAlreadyExists(this$0.overlayButtonsContainerLayout);
        this$0.xraySkillTimeoutManager.cancel();
        this$0.isXrayCheckedForTrack = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0029  */
    /* JADX INFO: renamed from: requestXrayOverlayIfXrayAvailable$lambda-3$lambda-2, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m1804requestXrayOverlayIfXrayAvailable$lambda3$lambda2(com.amazon.mp3.view.stage.StageXRayManager.XrayState r4, com.amazon.mp3.view.stage.StageXRayManager r5, android.view.View r6, com.amazon.music.media.playback.MediaItem r7, android.view.View r8) {
        /*
            java.lang.String r0 = "$xrayState"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "this$0"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            boolean r0 = r8 instanceof com.amazon.music.explore.views.swipeablePages.StageSwipeablePagesView
            r1 = 0
            if (r0 == 0) goto L29
            r2 = r8
            com.amazon.music.explore.views.swipeablePages.StageSwipeablePagesView r2 = (com.amazon.music.explore.views.swipeablePages.StageSwipeablePagesView) r2
            java.lang.Boolean r2 = r2.shouldShowInAutoSequencing()
            java.lang.String r3 = "xrayView.shouldShowInAutoSequencing()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            boolean r2 = r2.booleanValue()
            if (r2 != 0) goto L27
            com.amazon.mp3.view.stage.StageXRayManager$XrayState r2 = com.amazon.mp3.view.stage.StageXRayManager.XrayState.PUSH_STATE
            if (r4 == r2) goto L29
        L27:
            r4 = 1
            goto L2a
        L29:
            r4 = r1
        L2a:
            if (r0 == 0) goto L2f
            r5.setXrayPillButton(r6)
        L2f:
            java.lang.String r6 = "xrayView"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r6)
            r5.setXrayContainer(r8)
            com.amazon.mp3.view.stage.StageXRayManager$XrayState r6 = r5.currentXrayState
            com.amazon.mp3.view.stage.StageXRayManager$XrayState r0 = com.amazon.mp3.view.stage.StageXRayManager.XrayState.HIDDEN
            if (r6 == r0) goto L4c
            rx.subjects.PublishSubject<kotlin.Triple<com.amazon.music.media.playback.MediaItem, android.view.View, java.lang.Boolean>> r6 = r5.xrayContainerSubject
            kotlin.Triple r0 = new kotlin.Triple
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)
            r0.<init>(r7, r8, r4)
            r6.onNext(r0)
        L4c:
            r5.isXrayCheckedForTrack = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.view.stage.StageXRayManager.m1804requestXrayOverlayIfXrayAvailable$lambda3$lambda2(com.amazon.mp3.view.stage.StageXRayManager$XrayState, com.amazon.mp3.view.stage.StageXRayManager, android.view.View, com.amazon.music.media.playback.MediaItem, android.view.View):void");
    }

    private final void setXrayPillButton(View view) {
        LinearLayout overlayButtonsLinearLayout;
        if (findXrayButton(this.overlayButtonsContainerLayout) == null) {
            PillNavigatorWidgetItemView pillNavigatorWidgetItemView = (PillNavigatorWidgetItemView) view;
            this.xrayPillButton = pillNavigatorWidgetItemView.getPillButton();
            deselectXrayPillButton();
            pillNavigatorWidgetItemView.getPillButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.view.stage.StageXRayManager$$ExternalSyntheticLambda6
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    StageXRayManager.m1806setXrayPillButton$lambda4(this.f$0, view2);
                }
            });
            StageOverlayButtonsLayout stageOverlayButtonsLayout = this.overlayButtonsContainerLayout;
            if (stageOverlayButtonsLayout != null) {
                BaseButton pillButton = pillNavigatorWidgetItemView.getPillButton();
                Intrinsics.checkNotNullExpressionValue(pillButton, "xrayPillButtonViewExplore.pillButton");
                stageOverlayButtonsLayout.initViewStylingForButton(pillButton);
            }
            pillNavigatorWidgetItemView.setTag(this.XRAY_BUTTON_TAG);
            StageOverlayButtonsLayout stageOverlayButtonsLayout2 = this.overlayButtonsContainerLayout;
            if (stageOverlayButtonsLayout2 != null && (overlayButtonsLinearLayout = stageOverlayButtonsLayout2.getOverlayButtonsLinearLayout()) != null) {
                overlayButtonsLinearLayout.addView(pillNavigatorWidgetItemView);
            }
            pillNavigatorWidgetItemView.onViewed();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setXrayPillButton$lambda-4, reason: not valid java name */
    public static final void m1806setXrayPillButton$lambda4(StageXRayManager this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onXrayButtonClick.invoke();
    }

    private final void setXrayContainer(View xrayView) {
        if (xrayView instanceof StageSwipeablePagesView) {
            StageSwipeablePagesView stageSwipeablePagesView = (StageSwipeablePagesView) xrayView;
            SwipeablePagesStyleSheet swipeablePagesStyleSheet = this.bauhausStyleSheet;
            if (swipeablePagesStyleSheet == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bauhausStyleSheet");
                swipeablePagesStyleSheet = null;
            }
            stageSwipeablePagesView.initStyleSheet(swipeablePagesStyleSheet);
            XrayState xrayState = this.currentXrayState;
            stageSwipeablePagesView.setHasUserInteracted(xrayState == XrayState.USER_INITIATED_STATE || xrayState == XrayState.FULL_SCREEN);
            this.xrayContainer = stageSwipeablePagesView;
            setInteractionListener();
        }
    }

    private final void createExploreXrayManager() {
        Context applicationContext = AmazonApplication.getApplication().getApplicationContext();
        DMSFNavigationProvider dMSFNavigationProvider = new DMSFNavigationProvider(this.activity);
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        AuthenticationProviderImpl authenticationProviderImpl = new AuthenticationProviderImpl(applicationContext, null, 2, null);
        CustomerMetadataProviderImpl customerMetadataProviderImpl = new CustomerMetadataProviderImpl(applicationContext, null, 2, null);
        DMSFStorageProvider dMSFStorageProvider = new DMSFStorageProvider(applicationContext);
        DMSFEnvironmentProvider dMSFEnvironmentProvider = new DMSFEnvironmentProvider(applicationContext);
        boolean zIsCategoryPostEnabledNonTriggering = AmazonApplication.getCapabilities().isCategoryPostEnabledNonTriggering();
        NowPlayingFragmentActivity nowPlayingFragmentActivity = this.activity;
        Providers providers = Providers.INSTANCE;
        this.exploreXrayManager = new ExploreXrayManager(nowPlayingFragmentActivity, authenticationProviderImpl, customerMetadataProviderImpl, (DeviceInfoProvider) providers.get(DeviceInfoProvider.class), (BuildInfoProvider) providers.get(BuildInfoProvider.class), dMSFNavigationProvider, dMSFStorageProvider, dMSFEnvironmentProvider, this.activity, true, zIsCategoryPostEnabledNonTriggering);
        this.intializeXrayContainer.invoke();
        if (this.couldNotRequestXray) {
            requestXrayOverlayIfXrayAvailable(this.currentXrayState);
        }
    }

    public final PillNavigatorWidgetItemView findXrayButton(StageOverlayButtonsLayout overlayButtonsContainerLayout) {
        LinearLayout overlayButtonsLinearLayout;
        if (overlayButtonsContainerLayout == null || (overlayButtonsLinearLayout = overlayButtonsContainerLayout.getOverlayButtonsLinearLayout()) == null) {
            return null;
        }
        return (PillNavigatorWidgetItemView) overlayButtonsLinearLayout.findViewWithTag(this.XRAY_BUTTON_TAG);
    }

    public final void removeXrayPillButtonIfAlreadyExists(StageOverlayButtonsLayout overlayButtonsContainerLayout) {
        PillNavigatorWidgetItemView pillNavigatorWidgetItemViewFindXrayButton = findXrayButton(overlayButtonsContainerLayout);
        if (pillNavigatorWidgetItemViewFindXrayButton != null) {
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.MainScope(), null, null, new C04191(overlayButtonsContainerLayout, pillNavigatorWidgetItemViewFindXrayButton, null), 3, null);
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.view.stage.StageXRayManager$removeXrayPillButtonIfAlreadyExists$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: StageXRayManager.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.view.stage.StageXRayManager$removeXrayPillButtonIfAlreadyExists$1", f = "StageXRayManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C04191 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ StageOverlayButtonsLayout $overlayButtonsContainerLayout;
        final /* synthetic */ PillNavigatorWidgetItemView $view;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04191(StageOverlayButtonsLayout stageOverlayButtonsLayout, PillNavigatorWidgetItemView pillNavigatorWidgetItemView, Continuation<? super C04191> continuation) {
            super(2, continuation);
            this.$overlayButtonsContainerLayout = stageOverlayButtonsLayout;
            this.$view = pillNavigatorWidgetItemView;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C04191(this.$overlayButtonsContainerLayout, this.$view, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04191) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            LinearLayout overlayButtonsLinearLayout;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            StageOverlayButtonsLayout stageOverlayButtonsLayout = this.$overlayButtonsContainerLayout;
            if (stageOverlayButtonsLayout != null && (overlayButtonsLinearLayout = stageOverlayButtonsLayout.getOverlayButtonsLinearLayout()) != null) {
                overlayButtonsLinearLayout.removeView(this.$view);
            }
            return Unit.INSTANCE;
        }
    }

    public final void initViewStyling(StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.bauhausStyleSheet = new SwipeablePagesStyleSheet(this.context, styleSheet);
    }

    public final void setOverlayButtonContainer(StageOverlayButtonsLayout stageOverlayButtonsLayout) {
        this.overlayButtonsContainerLayout = stageOverlayButtonsLayout;
    }

    public final PublishSubject<Boolean> getXrayFinishEvent() {
        StageSwipeablePagesView stageSwipeablePagesView = this.xrayContainer;
        if (stageSwipeablePagesView == null) {
            return null;
        }
        return stageSwipeablePagesView.getXrayFinishEvent();
    }

    public final void clearXray() {
        this.currentXrayState = XrayState.HIDDEN;
        this.xraySkillTimeoutManager.cancel();
        this.xrayPillButton = null;
        this.xrayContainer = null;
        this.isXrayCheckedForTrack = false;
    }

    public final void clearXrayManager() {
        clearXray();
        if (!this.xrayManagerSingletonTask.isUnsubscribed()) {
            this.xrayManagerSingletonTask.unsubscribe();
        }
        this.exploreXrayManager = null;
        MiniPlayerViewController miniPlayerViewController = this.xrayMiniPlayerViewController;
        if (miniPlayerViewController != null) {
            miniPlayerViewController.clear();
        }
        this.xrayMiniPlayerViewController = null;
        StageSwipeablePagesView stageSwipeablePagesView = this.xrayContainer;
        if (stageSwipeablePagesView != null) {
            stageSwipeablePagesView.setOnClickListener(null);
        }
        StageSwipeablePagesView stageSwipeablePagesView2 = this.xrayContainer;
        if (stageSwipeablePagesView2 == null) {
            return;
        }
        stageSwipeablePagesView2.setOnTouchListener(null);
    }

    private final String getTrackAsin() {
        return MediaItem.getMediaItemIdString(this.playbackController.getCurrentMediaItem(), MediaItemId.Type.ASIN, null);
    }

    public final void setContainerForPartialOverlays(MediaItem mediaItem) {
        Intrinsics.checkNotNullParameter(mediaItem, "mediaItem");
        StageSwipeablePagesView stageSwipeablePagesView = this.xrayContainer;
        if (stageSwipeablePagesView != null) {
            this.xrayContainerSubject.onNext(new Triple<>(mediaItem, stageSwipeablePagesView, Boolean.TRUE));
        }
    }

    public final void setXrayPillButtonView(PillNavigatorWidgetItemElement pillNavigatorWidgetItemElement) {
        ExploreXrayManager exploreXrayManager = this.exploreXrayManager;
        PillNavigatorWidgetItemView pillNavigatorWidgetItemViewCreateStagePillNavigatorItemView = exploreXrayManager == null ? null : exploreXrayManager.createStagePillNavigatorItemView(pillNavigatorWidgetItemElement);
        if (pillNavigatorWidgetItemViewCreateStagePillNavigatorItemView == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.View");
        }
        setXrayPillButton(pillNavigatorWidgetItemViewCreateStagePillNavigatorItemView);
    }

    public final void setXrayContainerView(SwipeablePagesTemplate template, int currentPageIndex, int currentSectionIndex, Boolean hasUserInteracted) {
        ExploreXrayManager exploreXrayManager = this.exploreXrayManager;
        SwipeablePagesStyleSheet swipeablePagesStyleSheet = null;
        StageSwipeablePagesView stageSwipeablePagesViewCreateStageSwipeablePagesView = exploreXrayManager == null ? null : exploreXrayManager.createStageSwipeablePagesView(template, currentPageIndex, currentSectionIndex, Intrinsics.areEqual(hasUserInteracted, Boolean.TRUE));
        this.xrayContainer = stageSwipeablePagesViewCreateStageSwipeablePagesView;
        if (stageSwipeablePagesViewCreateStageSwipeablePagesView != null) {
            SwipeablePagesStyleSheet swipeablePagesStyleSheet2 = this.bauhausStyleSheet;
            if (swipeablePagesStyleSheet2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bauhausStyleSheet");
            } else {
                swipeablePagesStyleSheet = swipeablePagesStyleSheet2;
            }
            stageSwipeablePagesViewCreateStageSwipeablePagesView.initStyleSheet(swipeablePagesStyleSheet);
        }
        setInteractionListener();
    }

    private final LinearLayout createXrayMiniPlayer() {
        View viewInflate = this.activity.getLayoutInflater().inflate(R.layout.swipeable_pages_mini_player, (ViewGroup) null);
        if (viewInflate != null) {
            LinearLayout linearLayout = (LinearLayout) viewInflate;
            View viewFindViewById = linearLayout.findViewById(R.id.swipeable_pages_mini_player_container);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "miniPlayer.findViewById(…es_mini_player_container)");
            FrameLayout frameLayout = (FrameLayout) viewFindViewById;
            View viewFindViewById2 = linearLayout.findViewById(R.id.swipeable_pages_mini_player_progress_bar);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "miniPlayer.findViewById(…mini_player_progress_bar)");
            ProgressBar progressBar = (ProgressBar) viewFindViewById2;
            MiniPlayerView miniPlayerView = new MiniPlayerView(this.activity);
            if (!isLyricsSharingEnabled()) {
                miniPlayerView.setProgressBar(progressBar);
            } else {
                progressBar.setVisibility(8);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                layoutParams.bottomMargin = this.context.getResources().getDimensionPixelSize(R.dimen.spacer_8);
                frameLayout.setLayoutParams(layoutParams);
            }
            frameLayout.addView(miniPlayerView);
            miniPlayerView.setMinimizeViewVisibility(0);
            initializeXrayMiniPlayerViewController(miniPlayerView);
            return linearLayout;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.widget.LinearLayout");
    }

    private final void initializeXrayMiniPlayerViewController(MiniPlayerView miniPlayerView) {
        MiniPlayerViewController miniPlayerViewController = this.xrayMiniPlayerViewController;
        if (miniPlayerViewController != null) {
            miniPlayerViewController.clear();
        }
        MiniPlayerViewController miniPlayerViewController2 = new MiniPlayerViewController(this, this.activity, MiniPlayerViewController.MiniPLayerSource.XRAY) { // from class: com.amazon.mp3.view.stage.StageXRayManager.initializeXrayMiniPlayerViewController.1
            final /* synthetic */ StageXRayManager this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(nowPlayingFragmentActivity, this.$miniPlayerView, miniPLayerSource);
                this.this$0 = this;
            }

            @Override // com.amazon.mp3.playback.view.MiniPlayerViewController, com.amazon.mp3.playback.view.OnPlayerClickListener
            public void onPlayerClicked() {
                PageType pageType = getPageType();
                if (pageType != null) {
                    sendUiClickMetric(pageType, "dismiss", InteractionType.TAP);
                }
                this.this$0.activity.onBackPressed();
            }

            @Override // com.amazon.mp3.playback.view.MiniPlayerViewController, com.amazon.mp3.playback.PlayerVerticalSwipeListener
            public void onSwipeDown() {
                this.this$0.activity.onBackPressed();
            }
        };
        this.xrayMiniPlayerViewController = miniPlayerViewController2;
        miniPlayerViewController2.refreshPlayer();
    }

    public final void selectXrayPillButton() {
        BaseButton baseButton = this.xrayPillButton;
        if (baseButton != null) {
            baseButton.setActive(true);
        }
        BaseButton baseButton2 = this.xrayPillButton;
        if (baseButton2 == null) {
            return;
        }
        baseButton2.setContentDescription(this.context.getResources().getString(R.string.active_xray_pill_description));
    }

    public final void deselectXrayPillButton() {
        BaseButton baseButton = this.xrayPillButton;
        if (baseButton != null) {
            baseButton.setActive(false);
        }
        BaseButton baseButton2 = this.xrayPillButton;
        if (baseButton2 == null) {
            return;
        }
        baseButton2.setContentDescription(this.context.getResources().getString(R.string.non_active_xray_pill_description));
    }

    public final boolean isFullScreenXrayOpen() {
        FragmentController fragmentController = this.fragmentController;
        return StringsKt__StringsJVMKt.equals$default(fragmentController == null ? null : fragmentController.getCurrentFragmentName(), this.XRAY_FULL_SCREEN_TAG, false, 2, null);
    }

    public final BaseButton getXrayPillButton() {
        return this.xrayPillButton;
    }

    public final void removeViewFromFullScreenFragment() {
        if (isFullScreenXrayOpen()) {
            FragmentController fragmentController = this.fragmentController;
            Fragment currentFragment = fragmentController == null ? null : fragmentController.getCurrentFragment();
            if (currentFragment == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.explore.fragment.StageSwipeablePagesFragment");
            }
            ((StageSwipeablePagesFragment) currentFragment).removeStageSwipeablePagesView();
        }
    }

    public final void addViewInFullScreenFragment(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (isFullScreenXrayOpen()) {
            FragmentController fragmentController = this.fragmentController;
            Fragment currentFragment = fragmentController == null ? null : fragmentController.getCurrentFragment();
            if (currentFragment == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.explore.fragment.StageSwipeablePagesFragment");
            }
            ((StageSwipeablePagesFragment) currentFragment).addStageSwipeablePagesView(view);
        }
    }

    public final StageSwipeablePagesFragment getXrayFullScreenFragment(View view) {
        final LinearLayout miniPlayer;
        Intrinsics.checkNotNullParameter(view, "view");
        if (isFullScreenXrayOpen()) {
            FragmentController fragmentController = this.fragmentController;
            Fragment currentFragment = fragmentController == null ? null : fragmentController.getCurrentFragment();
            if (currentFragment != null) {
                return (StageSwipeablePagesFragment) currentFragment;
            }
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.explore.fragment.StageSwipeablePagesFragment");
        }
        StageSwipeablePagesFragment stageSwipeablePagesFragment = new StageSwipeablePagesFragment(this.contextItemSelectionListener, isLyricsSharingEnabled());
        if (stageSwipeablePagesFragment.getMiniPlayer() == null) {
            stageSwipeablePagesFragment.setMiniPlayer(createXrayMiniPlayer());
            if (!isLyricsSharingEnabled() && (miniPlayer = stageSwipeablePagesFragment.getMiniPlayer()) != null) {
                ViewCompat.setOnApplyWindowInsetsListener(miniPlayer, new OnApplyWindowInsetsListener() { // from class: com.amazon.mp3.view.stage.StageXRayManager$$ExternalSyntheticLambda4
                    @Override // androidx.core.view.OnApplyWindowInsetsListener
                    public final WindowInsetsCompat onApplyWindowInsets(View view2, WindowInsetsCompat windowInsetsCompat) {
                        return StageXRayManager.m1802getXrayFullScreenFragment$lambda6$lambda5(miniPlayer, view2, windowInsetsCompat);
                    }
                });
            }
        }
        stageSwipeablePagesFragment.setSwipeablesPagesView((StageSwipeablePagesView) view);
        return stageSwipeablePagesFragment;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getXrayFullScreenFragment$lambda-6$lambda-5, reason: not valid java name */
    public static final WindowInsetsCompat m1802getXrayFullScreenFragment$lambda6$lambda5(LinearLayout it, View view, WindowInsetsCompat windowInsets) {
        Intrinsics.checkNotNullParameter(it, "$it");
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(windowInsets, "windowInsets");
        Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
        Intrinsics.checkNotNullExpressionValue(insets, "windowInsets.getInsets(W…Compat.Type.systemBars())");
        ViewGroup.LayoutParams layoutParams = it.getLayoutParams();
        if (layoutParams != null) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            ViewGroup.LayoutParams layoutParams3 = it.getLayoutParams();
            ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams3 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams3 : null;
            int i = marginLayoutParams != null ? marginLayoutParams.leftMargin : 0;
            int i2 = insets.top;
            ViewGroup.LayoutParams layoutParams4 = it.getLayoutParams();
            ViewGroup.MarginLayoutParams marginLayoutParams2 = layoutParams4 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams4 : null;
            int i3 = marginLayoutParams2 != null ? marginLayoutParams2.rightMargin : 0;
            ViewGroup.LayoutParams layoutParams5 = it.getLayoutParams();
            ViewGroup.MarginLayoutParams marginLayoutParams3 = layoutParams5 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams5 : null;
            layoutParams2.setMargins(i, i2, i3, marginLayoutParams3 != null ? marginLayoutParams3.bottomMargin : 0);
            it.setLayoutParams(layoutParams2);
            return WindowInsetsCompat.CONSUMED;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
    }

    private final void setInteractionListener() {
        TouchEventHandler touchEventHandler = new TouchEventHandler();
        StageSwipeablePagesView stageSwipeablePagesView = this.xrayContainer;
        if (stageSwipeablePagesView != null) {
            stageSwipeablePagesView.setOnTouchListener(touchEventHandler.getTouchEventListener());
        }
        touchEventHandler.setOnClickListener(this.context, new View.OnClickListener() { // from class: com.amazon.mp3.view.stage.StageXRayManager$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                StageXRayManager.m1805setInteractionListener$lambda7(this.f$0, view);
            }
        });
        this.stageViewModel.getStageViewPresenter().setupSwipeGestures(touchEventHandler);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setInteractionListener$lambda-7, reason: not valid java name */
    public static final void m1805setInteractionListener$lambda7(StageXRayManager this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        XrayWindowInteractionListener xrayWindowInteractionListener = this$0.xrayWindowInteractionListener;
        if (xrayWindowInteractionListener == null) {
            return;
        }
        xrayWindowInteractionListener.onXrayWindowClicked();
    }

    public final void setIsXrayFullScreen(boolean isFullScreen) {
        StageSwipeablePagesView stageSwipeablePagesView = this.xrayContainer;
        if (stageSwipeablePagesView == null) {
            return;
        }
        stageSwipeablePagesView.setIsFullScreen(isFullScreen);
    }

    public final void setHasUserInteracted(boolean hasUserInteracted) {
        StageSwipeablePagesView stageSwipeablePagesView = this.xrayContainer;
        if (stageSwipeablePagesView == null) {
            return;
        }
        stageSwipeablePagesView.setHasUserInteracted(hasUserInteracted);
    }

    public final void setXrayWindowInteractionListener(XrayWindowInteractionListener xrayListener) {
        this.xrayWindowInteractionListener = xrayListener;
    }
}
