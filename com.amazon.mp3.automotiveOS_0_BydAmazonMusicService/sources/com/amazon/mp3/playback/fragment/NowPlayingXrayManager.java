package com.amazon.mp3.playback.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.BootstrapSingletonTask;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.dmsfcore.providers.DMSFEnvironmentProvider;
import com.amazon.mp3.dmsfcore.providers.DMSFNavigationProvider;
import com.amazon.mp3.dmsfcore.providers.DMSFStorageProvider;
import com.amazon.mp3.dmsfcore.providers.DMSFUserSubscriptionProvider;
import com.amazon.mp3.explore.providers.ExploreCastingProvider;
import com.amazon.mp3.explore.providers.ExplorePlaybackProvider;
import com.amazon.mp3.platform.providers.AuthenticationProviderImpl;
import com.amazon.mp3.platform.providers.CustomerMetadataProviderImpl;
import com.amazon.mp3.playback.activity.NowPlayingFragmentActivity;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.playback.view.MiniPlayerView;
import com.amazon.mp3.playback.view.MiniPlayerViewController;
import com.amazon.mp3.playback.view.NowPlayingPersistentPlayer;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.view.OnSwipeEventListener;
import com.amazon.mp3.view.TouchEventHandler;
import com.amazon.music.explore.fragment.SwipeablePagesFragment;
import com.amazon.music.explore.managers.ExploreManagerFactory;
import com.amazon.music.explore.managers.ExploreXrayManager;
import com.amazon.music.explore.widgets.views.IngressButtonView;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.BuildInfoProvider;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import com.amazon.music.platform.providers.MetricsProvider;
import kotlin.jvm.functions.Function0;
import rx.Subscription;
import rx.functions.Action1;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public class NowPlayingXrayManager implements OnPlayStateChangedListener, LifecycleObserver {
    private static final String XRAY_TAG = SwipeablePagesFragment.class.getSimpleName();
    private NowPlayingFragmentActivity activity;
    private boolean couldNotRequestXray;
    private ExploreXrayManager exploreXrayManager;
    private IngressButtonView ingressButton;
    private boolean isXrayAvailableForTrack;
    private SharedPreferences mDefaultSharedPreference;
    private NPSOverlayMetricsReporter mNPSOverlayMetricsReporter;
    private final OnSwipeEventListener mPersistentPlayerOnSwipeListener;
    private MediaItem mediaItem;
    private PersistentPlayerFragment persistentPlayerFragment;
    private TouchEventHandler persistentPlayerTouchHandler;
    private ActionValidatedPlaybackController playbackController;
    private Subscription subscription;
    private boolean wasXrayCheckedForTrack;
    private TouchEventHandler xrayIngressButtonTouchHandler;
    private MiniPlayerViewController xrayMiniPlayerViewController;

    public NowPlayingXrayManager(final NowPlayingFragmentActivity activity, final SharedPreferences sharedPreferences) {
        OnSwipeEventListener onSwipeEventListener = new OnSwipeEventListener() { // from class: com.amazon.mp3.playback.fragment.NowPlayingXrayManager.1
            @Override // com.amazon.mp3.view.OnSwipeEventListener
            public void onSwipeDown(float y) {
            }

            @Override // com.amazon.mp3.view.OnSwipeEventListener
            public void onSwipeLeft(float x) {
            }

            @Override // com.amazon.mp3.view.OnSwipeEventListener
            public void onSwipeRight(float x) {
            }

            @Override // com.amazon.mp3.view.OnSwipeEventListener
            public void onSwipeUp(float y) {
                if (NowPlayingXrayManager.this.isXrayAvailableForTrack) {
                    NowPlayingXrayManager.this.openXray();
                }
            }
        };
        this.mPersistentPlayerOnSwipeListener = onSwipeEventListener;
        this.activity = activity;
        this.subscription = BootstrapSingletonTask.get().registerUserObserver().take(1).subscribe(new Action1() { // from class: com.amazon.mp3.playback.fragment.NowPlayingXrayManager$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$new$0((Void) obj);
            }
        });
        TouchEventHandler touchEventHandler = new TouchEventHandler();
        this.persistentPlayerTouchHandler = touchEventHandler;
        touchEventHandler.setOnSwipeEventListener(onSwipeEventListener);
        TouchEventHandler touchEventHandler2 = new TouchEventHandler();
        this.xrayIngressButtonTouchHandler = touchEventHandler2;
        touchEventHandler2.setOnSwipeEventListener(onSwipeEventListener);
        this.xrayIngressButtonTouchHandler.setOnClickListener(activity, new View.OnClickListener() { // from class: com.amazon.mp3.playback.fragment.NowPlayingXrayManager$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$new$1(view);
            }
        });
        this.playbackController = PlaybackControllerProvider.getController(ControlSource.APP_UI);
        this.mDefaultSharedPreference = sharedPreferences;
        this.mNPSOverlayMetricsReporter = NPSOverlayMetricsReporter.INSTANCE.getNPSOverlayMetricsReporter();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(Void r1) {
        createXrayManager();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1(View view) {
        openXray();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openXray() {
        IngressButtonView ingressButtonView = this.ingressButton;
        if (ingressButtonView != null) {
            ingressButtonView.onClicked();
        }
        requestXray();
    }

    @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
    public void onPlayStateChanged() {
        MediaItem currentMediaItem = this.playbackController.getCurrentMediaItem();
        if (currentMediaItem == null || currentMediaItem.equals(this.mediaItem)) {
            return;
        }
        this.mediaItem = currentMediaItem;
        checkIfXrayAvailable();
    }

    public void clear() {
        Subscription subscription = this.subscription;
        if (subscription != null && !subscription.isUnsubscribed()) {
            this.subscription.unsubscribe();
        }
        MiniPlayerViewController miniPlayerViewController = this.xrayMiniPlayerViewController;
        if (miniPlayerViewController != null) {
            miniPlayerViewController.clear();
            this.xrayMiniPlayerViewController = null;
        }
        removeOnPlayStateChangedListener();
        this.activity = null;
    }

    public void addOnPlayStateChangedListener() {
        this.playbackController.addOnPlayStateChangedListener(this);
    }

    public void removeOnPlayStateChangedListener() {
        this.playbackController.removeOnPlayStateChangedListener(this);
    }

    public boolean wasXrayCheckedForTrack() {
        return this.wasXrayCheckedForTrack;
    }

    public void setWasXrayCheckedForTrack(final boolean wasXrayCheckedForTrack) {
        this.wasXrayCheckedForTrack = wasXrayCheckedForTrack;
    }

    public void setPersistentPlayerFragment(final PersistentPlayerFragment persistentPlayerFragment) {
        this.persistentPlayerFragment = persistentPlayerFragment;
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public void setXrayButtonTouchListener(final NowPlayingPersistentPlayer persistentPlayer) {
        persistentPlayer.getXrayIngressContainer().setOnTouchListener(this.xrayIngressButtonTouchHandler.getTouchEventListener());
    }

    public void setPersistentPlayerTouchListener(final View persistentPlayerContainer) {
        persistentPlayerContainer.setOnTouchListener(this.persistentPlayerTouchHandler.getTouchEventListener());
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public void checkIfXrayAvailable() {
        ExploreXrayManager exploreXrayManager;
        PersistentPlayerFragment persistentPlayerFragment = this.persistentPlayerFragment;
        if (persistentPlayerFragment == null) {
            MetricsLogger.sendEvent(FlexEvent.builder("xrayCouldNotBeChecked").build());
            if (isXrayOpen()) {
                MetricsLogger.sendEvent(FlexEvent.builder("xrayClosed").build());
                this.activity.onBackPressed();
                return;
            }
            return;
        }
        final NowPlayingPersistentPlayer nowPlayingPersistentPlayer = (NowPlayingPersistentPlayer) persistentPlayerFragment.getPersistentPlayer();
        if (isXrayOpen()) {
            getXrayFragment().removeSwipeablePagesView();
        }
        if (nowPlayingPersistentPlayer != null && nowPlayingPersistentPlayer.getXrayIngressContainer() != null) {
            nowPlayingPersistentPlayer.getXrayIngressContainer().removeAllViews();
        }
        this.wasXrayCheckedForTrack = true;
        this.isXrayAvailableForTrack = false;
        if (NowPlayingUtil.isVideoPlaying()) {
            return;
        }
        final String trackAsin = getTrackAsin();
        if (trackAsin != null && (exploreXrayManager = this.exploreXrayManager) != null) {
            exploreXrayManager.requestXrayButton(trackAsin);
            this.exploreXrayManager.getIngressButton().observe(this.activity, new Observer() { // from class: com.amazon.mp3.playback.fragment.NowPlayingXrayManager$$ExternalSyntheticLambda0
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    this.f$0.lambda$checkIfXrayAvailable$2(trackAsin, nowPlayingPersistentPlayer, (IngressButtonView) obj);
                }
            });
            return;
        }
        this.ingressButton = null;
        this.couldNotRequestXray = true;
        if (!isXrayOpen() || this.exploreXrayManager == null) {
            return;
        }
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.amazon.mp3.playback.fragment.NowPlayingXrayManager$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$checkIfXrayAvailable$3();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$checkIfXrayAvailable$2(String str, NowPlayingPersistentPlayer nowPlayingPersistentPlayer, IngressButtonView ingressButtonView) {
        if (str.equals(getTrackAsin())) {
            this.ingressButton = ingressButtonView;
            if (ingressButtonView != null) {
                this.isXrayAvailableForTrack = true;
                if (isXrayOpen()) {
                    requestXray();
                    return;
                }
                this.ingressButton.setOnTouchListener(this.xrayIngressButtonTouchHandler.getTouchEventListener());
                if (nowPlayingPersistentPlayer != null && nowPlayingPersistentPlayer.getXrayIngressContainer() != null) {
                    nowPlayingPersistentPlayer.getXrayIngressContainer().removeAllViews();
                    nowPlayingPersistentPlayer.addXrayIngressButton(ingressButtonView);
                } else {
                    MetricsLogger.sendEvent(FlexEvent.builder("xrayButtonMissing").build());
                }
                this.ingressButton.onViewed();
                return;
            }
            if (isXrayOpen()) {
                this.activity.onBackPressed();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$checkIfXrayAvailable$3() {
        this.activity.onBackPressed();
    }

    private SwipeablePagesFragment getXrayFragment() {
        return (SwipeablePagesFragment) this.activity.getSupportFragmentManager().findFragmentByTag(XRAY_TAG);
    }

    public boolean isXrayOpen() {
        return getXrayFragment() != null;
    }

    public String getTrackAsin() {
        return MediaItem.getMediaItemIdString(this.playbackController.getCurrentMediaItem(), MediaItemId.Type.ASIN, null);
    }

    private void requestXray() {
        final SwipeablePagesFragment swipeablePagesFragmentCreateXrayFragment;
        if (isXrayOpen()) {
            swipeablePagesFragmentCreateXrayFragment = getXrayFragment();
        } else {
            swipeablePagesFragmentCreateXrayFragment = createXrayFragment();
        }
        swipeablePagesFragmentCreateXrayFragment.getLifecycle().addObserver(this);
        this.exploreXrayManager.requestXray(getTrackAsin());
        this.exploreXrayManager.getView().observe(this.activity, new Observer() { // from class: com.amazon.mp3.playback.fragment.NowPlayingXrayManager$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$requestXray$4(swipeablePagesFragmentCreateXrayFragment, (View) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$requestXray$4(SwipeablePagesFragment swipeablePagesFragment, View view) {
        if (swipeablePagesFragment.getMiniPlayer() == null) {
            swipeablePagesFragment.setMiniPlayer(createXrayMiniPlayer());
        }
        if (swipeablePagesFragment.isVisible()) {
            this.activity.hideActionBar();
        }
        swipeablePagesFragment.addSwipeablePagesView(view);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void onResume() {
        this.mNPSOverlayMetricsReporter.startTimer(NPSViews.XRAY_FULL_SCREEN);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private void onPause() {
        this.mNPSOverlayMetricsReporter.stopTimer(NPSViews.XRAY_FULL_SCREEN, false);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onDestroy() {
        if (getXrayFragment() != null) {
            getXrayFragment().getLifecycle().removeObserver(this);
        }
    }

    private SwipeablePagesFragment createXrayFragment() {
        SwipeablePagesFragment swipeablePagesFragment = new SwipeablePagesFragment();
        addOnPlayStateChangedListener();
        FragmentTransaction fragmentTransactionBeginTransaction = this.activity.getSupportFragmentManager().beginTransaction();
        fragmentTransactionBeginTransaction.replace(R.id.FragmentContainer, swipeablePagesFragment, XRAY_TAG);
        fragmentTransactionBeginTransaction.addToBackStack(null);
        fragmentTransactionBeginTransaction.commit();
        return swipeablePagesFragment;
    }

    private LinearLayout createXrayMiniPlayer() {
        LinearLayout linearLayout = (LinearLayout) this.activity.getLayoutInflater().inflate(R.layout.swipeable_pages_mini_player, (ViewGroup) null);
        FrameLayout frameLayout = (FrameLayout) linearLayout.findViewById(R.id.swipeable_pages_mini_player_container);
        ProgressBar progressBar = (ProgressBar) linearLayout.findViewById(R.id.swipeable_pages_mini_player_progress_bar);
        MiniPlayerView miniPlayerView = new MiniPlayerView(this.activity);
        miniPlayerView.setProgressBar(progressBar);
        frameLayout.addView(miniPlayerView);
        miniPlayerView.setMinimizeViewVisibility(0);
        initializeXrayMiniPlayerViewController(miniPlayerView);
        return linearLayout;
    }

    private void createXrayManager() {
        Context applicationContext = AmazonApplication.getApplication().getApplicationContext();
        DMSFNavigationProvider dMSFNavigationProvider = new DMSFNavigationProvider(this.activity);
        AuthenticationProviderImpl authenticationProviderImpl = new AuthenticationProviderImpl(applicationContext);
        CustomerMetadataProviderImpl customerMetadataProviderImpl = new CustomerMetadataProviderImpl(applicationContext, new Function0() { // from class: com.amazon.mp3.playback.fragment.NowPlayingXrayManager$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AmazonApplication.getAuthInfoMAPR5();
            }
        });
        DMSFStorageProvider dMSFStorageProvider = new DMSFStorageProvider(applicationContext);
        DMSFEnvironmentProvider dMSFEnvironmentProvider = new DMSFEnvironmentProvider(applicationContext);
        ExplorePlaybackProvider explorePlaybackProvider = new ExplorePlaybackProvider(this.activity);
        DMSFUserSubscriptionProvider dMSFUserSubscriptionProvider = new DMSFUserSubscriptionProvider();
        ExploreCastingProvider exploreCastingProvider = new ExploreCastingProvider(this.activity);
        boolean zIsCategoryPostEnabledNonTriggering = AmazonApplication.getCapabilities().isCategoryPostEnabledNonTriggering();
        NowPlayingFragmentActivity nowPlayingFragmentActivity = this.activity;
        Providers providers = Providers.INSTANCE;
        this.exploreXrayManager = new ExploreManagerFactory(nowPlayingFragmentActivity, authenticationProviderImpl, customerMetadataProviderImpl, (DeviceInfoProvider) providers.get(DeviceInfoProvider.class), (BuildInfoProvider) providers.get(BuildInfoProvider.class), dMSFNavigationProvider, dMSFStorageProvider, dMSFEnvironmentProvider, (MetricsProvider) providers.get(MetricsProvider.class), explorePlaybackProvider, dMSFUserSubscriptionProvider, exploreCastingProvider).createXrayManager(this.activity, zIsCategoryPostEnabledNonTriggering);
        if (checkXrayOpeningState()) {
            openXray();
        }
        if (this.couldNotRequestXray) {
            checkIfXrayAvailable();
        }
    }

    private boolean checkXrayOpeningState() {
        SharedPreferences sharedPreferences = this.mDefaultSharedPreference;
        boolean z = sharedPreferences != null && sharedPreferences.getBoolean("com.amazon.mp3.activity.nowplaying.isXrayOpen", false);
        String string = this.mDefaultSharedPreference.getString("com.amazon.mp3.activity.nowplaying.trackASIN", "");
        MediaItem currentMediaItem = this.playbackController.getCurrentMediaItem();
        if (currentMediaItem == null || !z || string.isEmpty()) {
            return false;
        }
        MediaItemId mediaItemId = currentMediaItem.getMediaItemId(MediaItemId.Type.ASIN);
        return (mediaItemId == null || mediaItemId.getId() == null || !mediaItemId.getId().equalsIgnoreCase(string)) ? false : true;
    }

    private void initializeXrayMiniPlayerViewController(final MiniPlayerView miniPlayerView) {
        MiniPlayerViewController miniPlayerViewController = this.xrayMiniPlayerViewController;
        if (miniPlayerViewController != null) {
            miniPlayerViewController.clear();
            this.xrayMiniPlayerViewController = null;
        }
        MiniPlayerViewController miniPlayerViewController2 = new MiniPlayerViewController(this.activity, miniPlayerView, getXrayFragment(), MiniPlayerViewController.MiniPLayerSource.XRAY) { // from class: com.amazon.mp3.playback.fragment.NowPlayingXrayManager.2
            @Override // com.amazon.mp3.playback.view.MiniPlayerViewController, com.amazon.mp3.playback.view.OnPlayerClickListener
            public void onPlayerClicked() {
                NowPlayingXrayManager.this.activity.onBackPressed();
            }

            @Override // com.amazon.mp3.playback.view.MiniPlayerViewController, com.amazon.mp3.playback.PlayerVerticalSwipeListener
            public void onSwipeDown() {
                NowPlayingXrayManager.this.activity.onBackPressed();
            }
        };
        this.xrayMiniPlayerViewController = miniPlayerViewController2;
        miniPlayerViewController2.refreshPlayer();
    }
}
