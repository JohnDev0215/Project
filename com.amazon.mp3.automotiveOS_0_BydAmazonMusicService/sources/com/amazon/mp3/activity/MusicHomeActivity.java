package com.amazon.mp3.activity;

import android.R;
import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.tracing.Trace;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.AccountRequestUtil;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.actionbar.view.BauhausActionBarView;
import com.amazon.mp3.activity.GrantPermissionActivity;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.activity.navigation.FragmentController;
import com.amazon.mp3.activity.settings.LastViewedScreenUtil;
import com.amazon.mp3.activity.settings.SettingsActivity;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.auto.carmode.CarModeFABViewController;
import com.amazon.mp3.auto.carmode.CarModeUserPresetHandlerImpl;
import com.amazon.mp3.auto.carmode.CarModeUtility;
import com.amazon.mp3.auto.storage.PresetIdentifierHelper;
import com.amazon.mp3.automaticimport.AutomaticImportManager;
import com.amazon.mp3.bluetooth.BluetoothUtil;
import com.amazon.mp3.bottomnavbar.BottomBarUIListener;
import com.amazon.mp3.bottomnavbar.BottomNavigationBar;
import com.amazon.mp3.bottomnavbar.BottomNavigationBarContainer;
import com.amazon.mp3.bottomnavbar.events.BottomNavBarEvent;
import com.amazon.mp3.bottomnavbar.events.BottomNavBarEventType;
import com.amazon.mp3.capability.FeatureFlagsProvider;
import com.amazon.mp3.casting.CastingBannerViewProvider;
import com.amazon.mp3.casting.CastingFragment;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.catalog.fragment.LibraryAlbumsFragment;
import com.amazon.mp3.client.activity.LauncherActivity;
import com.amazon.mp3.cloudqueue.CloudQueueClientSingleton;
import com.amazon.mp3.cookieconsent.CookieConsentManager;
import com.amazon.mp3.customerlookup.CustomerLookupEventObserver;
import com.amazon.mp3.customerlookup.CustomerLookupViewModelProvider;
import com.amazon.mp3.dialog.ErrorDialogButton;
import com.amazon.mp3.dialog.OneButtonErrorDialog;
import com.amazon.mp3.dialog.fragment.AutomaticImportFtuDialogFragment;
import com.amazon.mp3.dynamicbackground.DynamicBackgroundImagePublisher;
import com.amazon.mp3.environment.MusicTerritory;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.feed.FeedEventObserver;
import com.amazon.mp3.feed.FeedFragmentLifecycleCallbacks;
import com.amazon.mp3.feed.FeedViewModelProvider;
import com.amazon.mp3.find.view.SearchTabFragmentHost;
import com.amazon.mp3.fragment.BaseFragment;
import com.amazon.mp3.fragment.headerbar.OverflowMenuReceiver;
import com.amazon.mp3.fragment.navigation.BrushFragmentNavigation;
import com.amazon.mp3.fragment.navigation.NavigationProvider;
import com.amazon.mp3.ftu.FTUMetricsSingleton;
import com.amazon.mp3.ftu.FTUSettingsUtil;
import com.amazon.mp3.inappmessaging.DynamicMessagingManager;
import com.amazon.mp3.inappmessaging.InAppMessagingManager;
import com.amazon.mp3.inappsurvey.InAppSurveyFABViewController;
import com.amazon.mp3.init.ForegroundLaunchInitializationTask;
import com.amazon.mp3.init.InitializationHandler;
import com.amazon.mp3.language.preference.LanguagePreferenceProvider;
import com.amazon.mp3.language.preference.handler.RetailLopSyncHandler;
import com.amazon.mp3.language.preference.persistence.RetailLopSyncManager;
import com.amazon.mp3.library.activity.AddToPlaylistActivity;
import com.amazon.mp3.library.activity.AddToPlaylistTabActivity;
import com.amazon.mp3.library.fragment.AbstractListFragment;
import com.amazon.mp3.library.fragment.LibraryBaseFragment;
import com.amazon.mp3.library.fragment.RatingPromptDialogFragment;
import com.amazon.mp3.library.fragment.RetainedPrimeFragment;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.mylibrary.LibraryFragment;
import com.amazon.mp3.library.mylibrary.LibraryPreferences;
import com.amazon.mp3.library.mylibrary.MyLibraryHomeFragment;
import com.amazon.mp3.library.mylibrary.MyLibraryV2HomeFragment;
import com.amazon.mp3.library.provider.InactivityProvider;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.service.sync.SyncService;
import com.amazon.mp3.library.util.MyLibraryPodcastsToggleHelper;
import com.amazon.mp3.navigation.ActivityStacker;
import com.amazon.mp3.navigation.DeeplinkIntentKey;
import com.amazon.mp3.net.stratus.StratusFeature;
import com.amazon.mp3.net.stratus.preferences.RetailPreferencesRetriever;
import com.amazon.mp3.playback.SkipLimitTooltipPresenter;
import com.amazon.mp3.playback.SwipeControlTooltipExperience;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.playback.harley.PlaybackInitializationHelper;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.playback.video.VideoUtil;
import com.amazon.mp3.playback.view.CloudQueueMiniPlayerView;
import com.amazon.mp3.playback.view.MiniPlayerView;
import com.amazon.mp3.playback.view.MiniPlayerViewController;
import com.amazon.mp3.playback.view.OnPlayerVisibilityChangedListener;
import com.amazon.mp3.prime.ContentUnavailableReason;
import com.amazon.mp3.prime.activity.PrimeActivityFactory;
import com.amazon.mp3.prime.browse.PrimeTabFragmentHost;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.prime.upsell.PrimeUpsellActivity;
import com.amazon.mp3.prime.upsell.nightwing.UpsellManager;
import com.amazon.mp3.ratingprompt.policy.RatingPromptPolicy;
import com.amazon.mp3.reactnative.PrimaryReactInitializer;
import com.amazon.mp3.reactnative.RNRoutes;
import com.amazon.mp3.reactnative.ReactNativeFragmentLifeCycleCallbacks;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.service.job.Session;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.service.metrics.minerva.MinervaMetricsFactory;
import com.amazon.mp3.store.activity.HTMLStoreActivityFactory;
import com.amazon.mp3.store.html5.activity.StoreFragment;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.upsellweb.UpsellSplashPageWebTargetBuilderFactory;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PlatformUtil;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.mp3.util.StatusBarUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.util.extensions.ActivityKt;
import com.amazon.mp3.util.health.AppHealthUtil;
import com.amazon.mp3.video.stories.VideoStoriesUtil;
import com.amazon.mp3.video.stories.VideoStoryPlaybackDataRepository;
import com.amazon.mp3.view.lyrics.sharing.utils.LyricsSharingAssetManager;
import com.amazon.mp3.voice.AlexaIngressHelper;
import com.amazon.mp3.voice.VoiceManagerFactory;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.DataNotReadyException;
import com.amazon.music.account.MusicAccountNotCreatedException;
import com.amazon.music.authentication.AuthDataType;
import com.amazon.music.authentication.AuthenticationException;
import com.amazon.music.casting.CastingCategory;
import com.amazon.music.casting.CastingDevice;
import com.amazon.music.casting.CastingException;
import com.amazon.music.casting.session.CastingConnectionCallback;
import com.amazon.music.casting.session.CastingSessionManager;
import com.amazon.music.castingviews.CastingUIMetricsLogger;
import com.amazon.music.connect.customerlookup.viewmodels.CustomerLookupViewModel;
import com.amazon.music.connect.feed.FeedViewModel;
import com.amazon.music.featureflag.FeatureFlagProvider;
import com.amazon.music.inappmessaging.external.DynamicMessageOrchestratorImpl;
import com.amazon.music.inappmessaging.external.DynamicMessageStyleSheetLoader;
import com.amazon.music.inappmessaging.external.IAMRequest;
import com.amazon.music.inappmessaging.external.InAppMessageBroadcasts;
import com.amazon.music.inappmessaging.external.WeblabHelper;
import com.amazon.music.inappmessaging.external.model.DynamicMessageEvent;
import com.amazon.music.inappmessaging.external.model.DynamicMessageMetricsAttributes;
import com.amazon.music.inappmessaging.external.model.Trigger;
import com.amazon.music.inappmessaging.ui.DynMsgRibbon;
import com.amazon.music.languagepreference.LanguagePreference;
import com.amazon.music.languagepreference.LanguagePreferenceException;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.media.playback.util.ObjectUtils;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyEventDescriptor;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyTracker;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiContentViewEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiPageViewEvent;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContainerType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContentName;
import com.amazon.music.optional.Optional;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.amazon.music.platform.providers.FeatureGateProviderImpl;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.PersistentBannerView;
import com.amazon.music.views.library.views.presentation.ActionBarOverlayable;
import com.amazon.music.voice.VoiceManager;
import com.amazon.music.voice.VoiceManagerSingleton;
import com.amazon.podcast.ActionBarInfo;
import com.amazon.podcast.AdvertisingPreferencesProviderImpl;
import com.amazon.podcast.DownloadSettings;
import com.amazon.podcast.FragmentInfo;
import com.amazon.podcast.NetworkSettings;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.PodcastDeeplinks;
import com.amazon.podcast.PodcastDynamicMessaging;
import com.amazon.podcast.PodcastSharingProvider;
import com.amazon.podcast.UserInfo;
import com.amazon.podcast.bootstrap.Bootstrap;
import com.amazon.podcast.deeplinks.PodcastDeeplink;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import com.amazon.podcast.media.playback.Playback;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.google.android.gms.actions.SearchIntents;
import com.visioncritical.touchpointkit.utils.TouchPointActivity;
import com.waze.sdk.WazeNavigationBar;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.functions.Action;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes3.dex */
public class MusicHomeActivity extends NavigationActivity implements NavigationProvider, BottomBarUIListener, OnPlayerVisibilityChangedListener, DynamicBackgroundImagePublisher.DynamicBackgroundSubscriber, Playback.StateCallback, OnPlayStateChangedListener, ActionBarOverlayable, DefaultHardwareBackBtnHandler {
    private static final String TAG = "MusicHomeActivity";
    private static boolean sWasIamShown;
    private final BroadcastReceiver carBluetoothDetectionEventReceiver;
    private CarModeFABViewController carModeFABViewController;
    private FeedFragmentLifecycleCallbacks feedFragmentLifecycleCallbacks;
    private FeedViewModel feedViewModel;
    private InAppSurveyFABViewController inAppSurveyFABViewController;
    private final Runnable initializeAndDisplayViews;
    private CustomerLookupViewModel lookupViewModel;
    private AOMEventSubscriber mAOMEventSubscriber;
    private AsyncTask<Void, Void, Void> mBackgroundInitializationTask;
    private PersistentBannerView mBannerView;
    private ViewGroup mBottomBarAndMiniPlayerView;
    private BottomNavigationBarContainer mBottomBarContainer;
    private CastingBannerViewProvider mCastingBannerGenerator;
    private CastingConnectionCallback mCastingConnectionCallback;
    private RelativeLayout mCastingHeaderContainer;
    private TextView mCastingHeaderTextView;
    private FragmentController.FragmentChangedCallback mChangedCallback;
    private Boolean mCurrentPrimeStatus;
    private Bundle mDeeplinkExtras;
    private BroadcastReceiver mDeeplinkReceiver;
    private final BroadcastReceiver mDisplayAppStartDynamicMessageReceiver;
    private final BroadcastReceiver mDisplayDeeplinkDynamicMessageReceiver;
    private DynMsgRibbon mDynMsgRibbon;
    private ImageView mDynamicBackgroundImage;
    private DynamicMessagingManager mDynamicMessagingManager;
    private Subscription mDynamicMessagingStylesheetSubscription;
    private FragmentController mFragmentController;
    private Handler mHandler;
    private boolean mHasSavedFragmentState;
    private InAppMessagingManager mInAppMessagingManager;
    private boolean mIsActionBarOverlay;
    private boolean mIsAlexaEnabled;
    private boolean mIsCastingBannerInRestingState;
    private String mLastMarketplace;
    private View mLoadingView;
    private MiniPlayerView mMiniPlayerView;
    private FrameLayout mMiniPlayerViewContainer;
    private MiniPlayerViewController mMiniPlayerViewController;
    private Subscription mOnNextButtonClickedSubscription;
    private final ActionValidatedPlaybackController mPlaybackController;
    private ConstraintLayout mRootContainer;
    private Boolean mShouldHandleIntent;
    private StyleSheet mStyleSheet;
    private Subscription mSubscription;
    private final BroadcastReceiver mSyncEventReceiver;
    private UiPageViewEventSubscriber mUiPageViewEventSubscriber;
    private ProgressBar miniPlayerProgressBar;
    private com.amazon.podcast.nowplaying.MiniPlayerView podcastMiniPlayer;
    private RelativeLayout podcastMiniPlayerLayout;
    private ReactNativeFragmentLifeCycleCallbacks reactNativeFragmentLifecycleCallbacks;
    private final SkipLimitTooltipPresenter skipLimitTooltipPresenter;
    private SwipeControlTooltipExperience swipeControlTooltip;
    private Boolean wasFTUShownSinceLaunch;
    private Boolean wasRetailLopSyncFTUShownSinceLaunch;
    private static final LastViewedScreenUtil.LastViewedSource DEFAULT_LIBRARY_SOURCE = LastViewedScreenUtil.LastViewedSource.CLOUD;
    private static String sLocale = null;
    private static boolean sMiniPLayerContentViewedMetricEmitted = false;
    private static boolean sBluetoothCoachMarkShown = false;
    private static boolean sCastingForegroundCallbackSet = false;
    private static String podcastDeeplinkPath = "";
    private HashMap<String, String> hotSpotExperienceVault = new HashMap<>();
    private long mHandleExpiredJob = -1;
    private boolean mIsExternalAppLaunch = false;
    private boolean mDestroyed = false;
    private boolean mResumed = false;

    public MusicHomeActivity() {
        Boolean bool = Boolean.FALSE;
        this.mShouldHandleIntent = bool;
        this.wasFTUShownSinceLaunch = bool;
        this.wasRetailLopSyncFTUShownSinceLaunch = bool;
        this.mPlaybackController = PlaybackControllerProvider.getController(ControlSource.APP_UI);
        this.skipLimitTooltipPresenter = new SkipLimitTooltipPresenter();
        this.mCastingConnectionCallback = new CastingConnectionCallback() { // from class: com.amazon.mp3.activity.MusicHomeActivity.1
            @Override // com.amazon.music.casting.session.CastingConnectionCallback
            public void onError(final CastingException e) {
            }

            @Override // com.amazon.music.casting.session.CastingConnectionCallback
            public void onConnected(final CastingDevice device) {
                if (AmazonApplication.getCapabilities().isBauhausInAppMessagingEnabled()) {
                    return;
                }
                MusicHomeActivity.this.updateCastingHeaderView(true);
            }

            @Override // com.amazon.music.casting.session.CastingConnectionCallback
            public void onDisconnected(TerminationReason terminationReason) {
                if (AmazonApplication.getCapabilities().isBauhausInAppMessagingEnabled()) {
                    return;
                }
                MusicHomeActivity.this.updateCastingHeaderView(false);
            }
        };
        this.initializeAndDisplayViews = new Runnable() { // from class: com.amazon.mp3.activity.MusicHomeActivity.2
            @Override // java.lang.Runnable
            public void run() {
                MusicHomeActivity.this.init();
                if (MusicHomeActivity.this.canInitializeWithoutUser()) {
                    return;
                }
                MusicHomeActivity.this.initializeAlexa();
            }
        };
        this.mBackgroundInitializationTask = new AnonymousClass3();
        this.mIsActionBarOverlay = false;
        this.mChangedCallback = new FragmentController.FragmentChangedCallback() { // from class: com.amazon.mp3.activity.MusicHomeActivity.10
            @Override // com.amazon.mp3.activity.navigation.FragmentController.FragmentChangedCallback
            public void onChange() {
                if (MusicHomeActivity.this.isRecentlyPrimeMember()) {
                    MusicHomeActivity.this.showPrimeSplashScreen();
                }
            }
        };
        this.mSyncEventReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.activity.MusicHomeActivity.12
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (intent.getIntExtra("com.amazon.mp3.library.service.sync.EXTRA_SYNC_EVENT_TYPE", 0) == 5 && MusicHomeActivity.this.isRecentlyPrimeMember()) {
                    MusicHomeActivity.this.showPrimeSplashScreen();
                }
            }
        };
        this.mDisplayAppStartDynamicMessageReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.activity.MusicHomeActivity.13
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (MusicHomeActivity.this.mDynamicMessagingManager != null) {
                    MusicHomeActivity.this.mDynamicMessagingManager.displayDynamicMessage(MusicHomeActivity.this, new DynamicMessageEvent(Trigger.APP_START), new DynamicMessageMetricsAttributes()).subscribe();
                    MusicHomeActivity.this.mDynamicMessagingManager.displayDynamicMessage(MusicHomeActivity.this, new DynamicMessageEvent(Trigger.APP_START_SEQUENTIAL), new DynamicMessageMetricsAttributes()).subscribe();
                }
            }
        };
        this.mDisplayDeeplinkDynamicMessageReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.activity.MusicHomeActivity.14
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (MusicHomeActivity.this.mDynamicMessagingManager != null) {
                    MusicHomeActivity.this.mDynamicMessagingManager.displayDynamicMessage(MusicHomeActivity.this, new DynamicMessageEvent(URI.create(intent.getStringExtra("dynMsgEvent"))), new DynamicMessageMetricsAttributes()).subscribe();
                }
            }
        };
        this.carBluetoothDetectionEventReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.activity.MusicHomeActivity.15
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                Log.debug(MusicHomeActivity.TAG, "Receive car Bluetooth detection event with action " + action);
                MusicHomeActivity.this.carModeFABViewController.setCarModeFabButtonVisibility();
            }
        };
        this.mDeeplinkReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.activity.MusicHomeActivity.19
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                MusicHomeActivity.this.mDeeplinkExtras = intent.getExtras();
                MusicHomeActivity.this.handleVideoDeeplinkIntent(intent);
            }
        };
    }

    @Override // com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

    /* JADX INFO: renamed from: com.amazon.mp3.activity.MusicHomeActivity$3, reason: invalid class name */
    class AnonymousClass3 extends AsyncTask<Void, Void, Void> {
        AnonymousClass3() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(final Void... params) {
            IAMRequest iAMRequestAppStartIAMRequest;
            MusicHomeActivity musicHomeActivity = MusicHomeActivity.this;
            musicHomeActivity.mUiPageViewEventSubscriber = new UiPageViewEventSubscriber(musicHomeActivity.getApplicationContext());
            EventBus.getDefault().register(MusicHomeActivity.this.mUiPageViewEventSubscriber);
            MusicHomeActivity musicHomeActivity2 = MusicHomeActivity.this;
            musicHomeActivity2.mAOMEventSubscriber = new AOMEventSubscriber(musicHomeActivity2.mBottomBarAndMiniPlayerView, MusicHomeActivity.this.mBottomBarContainer);
            EventBus.getDefault().register(MusicHomeActivity.this.mAOMEventSubscriber);
            MusicHomeActivity musicHomeActivity3 = MusicHomeActivity.this;
            musicHomeActivity3.mLastMarketplace = AccountDetailUtil.get(musicHomeActivity3).getHomeMarketPlaceId();
            MusicHomeActivity.this.sendUserEnteredMusicStoreMetric();
            if (MusicHomeActivity.this.isRecentlyPrimeMember()) {
                MusicHomeActivity.this.showPrimeSplashScreen();
            }
            if (!MusicHomeActivity.this.mHasSavedFragmentState) {
                MusicHomeActivity.this.mShouldHandleIntent = Boolean.TRUE;
            }
            MusicHomeActivity.this.mIsAlexaEnabled = AmazonApplication.getCapabilities().isAlexaEnabled();
            MusicHomeActivity.this.mInAppMessagingManager = new InAppMessagingManager(MusicHomeActivity.this);
            MusicHomeActivity.this.mDynamicMessagingManager = new DynamicMessagingManager(MusicHomeActivity.this);
            if (MusicHomeActivity.this.mDynamicMessagingManager != null && DynamicMessagingManager.isDynamicMessagingSupported() && !MusicHomeActivity.this.mDynamicMessagingManager.isDynMsgInitialized()) {
                MusicHomeActivity.this.mDynamicMessagingManager.initializeDynMsg();
            }
            MusicHomeActivity musicHomeActivity4 = MusicHomeActivity.this;
            musicHomeActivity4.registerReceiver(musicHomeActivity4.mDisplayAppStartDynamicMessageReceiver, new IntentFilter(InAppMessageBroadcasts.DISPLAY_APP_START_DYNAMIC_MESSAGE));
            DynamicMessageOrchestratorImpl.sAppStartReceiverReady = true;
            if (DynamicMessageOrchestratorImpl.sResendAppStartDynMsgBroadcast) {
                MusicHomeActivity.this.getApplicationContext().sendBroadcast(new Intent(InAppMessageBroadcasts.DISPLAY_APP_START_DYNAMIC_MESSAGE));
            }
            MusicHomeActivity musicHomeActivity5 = MusicHomeActivity.this;
            musicHomeActivity5.registerReceiver(musicHomeActivity5.mDisplayDeeplinkDynamicMessageReceiver, new IntentFilter(InAppMessageBroadcasts.DISPLAY_DEEPLINK_DYNAMIC_MESSAGE));
            String stringExtra = MusicHomeActivity.this.getIntent().getStringExtra("com.amazon.mp3.iam.IAM_REQUEST");
            String stringExtra2 = MusicHomeActivity.this.getIntent().getStringExtra("com.amazon.mp3.iam.DM_DEEPLINK_REQUEST");
            if (stringExtra == null) {
                iAMRequestAppStartIAMRequest = MusicHomeActivity.this.mInAppMessagingManager.appStartIAMRequest();
            } else {
                iAMRequestAppStartIAMRequest = IAMRequest.fromJson(stringExtra);
            }
            boolean z = !iAMRequestAppStartIAMRequest.shouldThrottle();
            boolean zCustomerCompletedATCThisSession = FTUSettingsUtil.customerCompletedATCThisSession();
            if (!WeblabHelper.getInstance().isSplashV2Enabled() && !zCustomerCompletedATCThisSession && (!MusicHomeActivity.sWasIamShown || z)) {
                boolean unused = MusicHomeActivity.sWasIamShown = true;
                MusicHomeActivity.this.mInAppMessagingManager.displayInAppMessage(MusicHomeActivity.this, iAMRequestAppStartIAMRequest, Boolean.FALSE);
            }
            if (WeblabHelper.getInstance().isSplashV2Enabled() && !zCustomerCompletedATCThisSession && stringExtra2 != null) {
                MusicHomeActivity.this.mDynamicMessagingManager.loadDeeplinkDynamicMessage(new DynamicMessageEvent(URI.create(stringExtra2)), false);
            }
            if (iAMRequestAppStartIAMRequest.shouldThrottle()) {
                MusicHomeActivity.this.mInAppMessagingManager.getIamShownObservable().take(1).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.activity.MusicHomeActivity$3$$ExternalSyntheticLambda0
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        this.f$0.lambda$doInBackground$0((Boolean) obj);
                    }
                });
            }
            if (MusicHomeActivity.this.canInitializeWithoutUser()) {
                MusicHomeActivity.this.mHandler.post(MusicHomeActivity.this.initializeAndDisplayViews);
            } else {
                MusicHomeActivity musicHomeActivity6 = MusicHomeActivity.this;
                musicHomeActivity6.mSubscription = BootstrapSingletonTask.get(musicHomeActivity6.getApplicationContext()).registerFeatureFlagObserver().take(1).subscribe(new Action1<Void>() { // from class: com.amazon.mp3.activity.MusicHomeActivity.3.1
                    @Override // rx.functions.Action1
                    public void call(final Void aVoid) {
                        MusicHomeActivity.this.mHandler.post(MusicHomeActivity.this.initializeAndDisplayViews);
                    }
                }, new Action1<Throwable>() { // from class: com.amazon.mp3.activity.MusicHomeActivity.3.2
                    @Override // rx.functions.Action1
                    public void call(Throwable throwable) {
                        Log.warning(MusicHomeActivity.TAG, "BootstrapSingletonTask error: " + throwable.getMessage());
                    }
                });
            }
            MusicHomeActivity.this.setupInAppSurvey();
            RxJavaPlugins.setErrorHandler(new Consumer() { // from class: com.amazon.mp3.activity.MusicHomeActivity$3$$ExternalSyntheticLambda1
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) throws Exception {
                    MusicHomeActivity.AnonymousClass3.lambda$doInBackground$1((Throwable) obj);
                }
            });
            io.reactivex.rxjava3.plugins.RxJavaPlugins.setErrorHandler(new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amazon.mp3.activity.MusicHomeActivity$3$$ExternalSyntheticLambda2
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) throws Throwable {
                    MusicHomeActivity.AnonymousClass3.lambda$doInBackground$2((Throwable) obj);
                }
            });
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$doInBackground$0(Boolean bool) {
            if (bool.booleanValue()) {
                return;
            }
            new AlexaIngressHelper(MusicHomeActivity.this).handleAlexaSplashIngress();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$doInBackground$1(Throwable th) throws Exception {
            Log.error(MusicHomeActivity.TAG, "Undeliverable RxJava2 exception received: " + th.getMessage(), th);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$doInBackground$2(Throwable th) throws Throwable {
            Log.error(MusicHomeActivity.TAG, "Undeliverable RxJava3 exception received: " + th.getMessage(), th);
        }
    }

    public static Intent getStartIntent(final Context context) {
        return new Intent(context, (Class<?>) MusicHomeActivity.class);
    }

    public static Intent getStartIntent(final Context context, final LastViewedScreenUtil.LastViewedSource source) {
        Intent startIntent = getStartIntent(context);
        startIntent.putExtra("com.amazon.mp3.library.EXTRA_SOURCE_ID", source.toCirrusMediaSource());
        return startIntent;
    }

    public static Intent getStartIntent(final Context context, final LastViewedScreenUtil.LastViewedSource source, final LastViewedScreenUtil.LastViewedScreen screen) {
        Uri filterContentUri;
        Intent startIntent = getStartIntent(context, source);
        if (AmazonApplication.getCapabilities().isBauhausLibraryEnabled()) {
            filterContentUri = LibraryAlbumsFragment.getUri();
        } else {
            filterContentUri = MediaProvider.Albums.getFilterContentUri(source.toCirrusMediaSource(), "");
        }
        startIntent.putExtra("com.amazon.mp3.library.EXTRA_CONTENT_URI", filterContentUri);
        return startIntent;
    }

    @Override // com.amazon.mp3.playback.view.OnPlayerVisibilityChangedListener
    public void onVisibilityChanged(final int visibility) {
        SwipeControlTooltipExperience swipeControlTooltipExperience;
        StyleSheet styleSheet;
        Trace.beginSection("CustomTrace - MusicHomeActivity.onVisibilityChanged");
        if (this.mMiniPlayerViewContainer.getVisibility() != visibility) {
            Playback playback = Podcast.getPlayback();
            if (playback != null && playback.isPodcastLastPlayed()) {
                this.mMiniPlayerViewContainer.setVisibility(8);
                this.miniPlayerProgressBar.setVisibility(8);
                setPodcastMiniPlayerVisibility(true, playback.getShouldShowMiniPlayerInApp(), !ObjectUtils.isNullOrEmpty(playback.readRestoreMiniPlayerMethods()));
            } else {
                this.mMiniPlayerViewContainer.setVisibility(visibility);
                this.miniPlayerProgressBar.setVisibility(visibility);
                com.amazon.podcast.nowplaying.MiniPlayerView miniPlayerView = this.podcastMiniPlayer;
                if (miniPlayerView != null && miniPlayerView.getVisibility() != 8) {
                    this.podcastMiniPlayer.setVisibility(8);
                    this.podcastMiniPlayerLayout.setVisibility(8);
                }
            }
        }
        if (this.mMiniPlayerViewContainer.getVisibility() == 0 && (swipeControlTooltipExperience = this.swipeControlTooltip) != null && (styleSheet = this.mStyleSheet) != null) {
            swipeControlTooltipExperience.showTooltip(this.mMiniPlayerView, styleSheet, inImmersiveMode());
        }
        if (this.mMiniPlayerViewContainer.getVisibility() == 0 && !sMiniPLayerContentViewedMetricEmitted) {
            sendUIContentViewedMetric();
            sMiniPLayerContentViewedMetricEmitted = true;
        }
        updateBottomBarHeightWithMiniplayer();
        Trace.endSection();
    }

    private void sendUIContentViewedMetric() {
        MetricsLogger.sendEvent(UiContentViewEvent.clientEventBuilder().withContainerType(ContainerType.MINI_TRANSPORT).withContentName(ContentName.MINI_PLAYER).build());
    }

    @Override // com.amazon.mp3.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(final Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        SkipLimitTooltipPresenter skipLimitTooltipPresenter = this.skipLimitTooltipPresenter;
        if (skipLimitTooltipPresenter != null) {
            skipLimitTooltipPresenter.dismissTooltips();
        }
    }

    public static final class UiPageViewEventSubscriber {
        private final Context mContext;

        public UiPageViewEventSubscriber(final Context context) {
            this.mContext = context;
        }

        @Subscribe
        public void onEvent(final Bundle event) {
            String string;
            String string2;
            String str = null;
            if (event != null) {
                DeeplinkIntentKey deeplinkIntentKey = DeeplinkIntentKey.KEY_REF_MARKER;
                string = event.getString(deeplinkIntentKey.toString());
                DeeplinkIntentKey deeplinkIntentKey2 = DeeplinkIntentKey.KEY_ASSOCIATE_TAG;
                string2 = event.getString(deeplinkIntentKey2.toString());
                String string3 = event.getString("UI_PAGE_VIEW_PAGE_ID");
                event.remove(deeplinkIntentKey.toString());
                event.remove(deeplinkIntentKey2.toString());
                str = string3;
            } else {
                string = null;
                string2 = null;
            }
            MetricsHolder.getManager().handleEvent(UiPageViewEvent.builder(str).withViewType(ScreenUtil.getScreenViewType(this.mContext)).withExternalReference(string).withAssociateTag(string2).build());
        }
    }

    private void configureStates(StyleSheet styleSheet) {
        DynamicBackgroundImagePublisher.getInstance().subscribeBackgroundImageUpdate(this, styleSheet);
        DynMsgRibbon dynMsgRibbon = new DynMsgRibbon();
        this.mDynMsgRibbon = dynMsgRibbon;
        dynMsgRibbon.init(getApplicationContext(), (ViewGroup) findViewById(R.id.content), styleSheet, (ConstraintLayout) findViewById(com.amazon.mp3.automotiveOS.R.id.dynmsg_feature_banner_container));
    }

    public void dismissDynMsgRibbon() {
        View viewFindViewById = findViewById(com.amazon.mp3.automotiveOS.R.id.dynmsg_feature_banner_container);
        if (viewFindViewById != null && viewFindViewById.getVisibility() != 8) {
            viewFindViewById.setVisibility(8);
            MetricsLogger.emitUiClickMetricForUpsell(ActionType.AUTO_DISMISS_IAM, ContentName.DM_RIBBON.getMetricsValue());
        }
        DynMsgRibbon dynMsgRibbon = this.mDynMsgRibbon;
        if (dynMsgRibbon != null) {
            dynMsgRibbon.destroy();
        }
    }

    public boolean isDynMsgRibbonVisible() {
        Optional optionalOfNullable = Optional.ofNullable(findViewById(com.amazon.mp3.automotiveOS.R.id.dynmsg_feature_banner_container));
        return optionalOfNullable.isPresent() && ((View) optionalOfNullable.get()).getVisibility() == 0;
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        Trace.beginSection("CustomTrace - MusicHomeActivity.onCreate");
        if (savedInstanceState != null) {
            this.mHasSavedFragmentState = savedInstanceState.getBoolean("fragmentStateSaved", false);
            this.wasFTUShownSinceLaunch = Boolean.valueOf(savedInstanceState.getBoolean("automaticImportPromptState", false));
            this.wasRetailLopSyncFTUShownSinceLaunch = Boolean.valueOf(savedInstanceState.getBoolean("retailLopSyncPromptState", false));
            BottomNavigationBarContainer.onRestoreInstanceState(savedInstanceState);
        }
        if (AccountCredentialUtil.get(this).isSignedIn()) {
            CloudQueueClientSingleton.INSTANCE.initializeClients();
        }
        PresetIdentifierHelper.initialize(this);
        new InitializationHandler(new ForegroundLaunchInitializationTask(this, new AppHealthUtil(PreferenceManager.getDefaultSharedPreferences(this)))).initializeOnNewThread();
        CarModeUtility carModeUtility = CarModeUtility.INSTANCE;
        if (!carModeUtility.isUserExitedCarMode() && !carModeUtility.isAutoEnteredCarMode()) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.amazon.mp3.auto.detection.Car_Bluetooth_Connect");
            intentFilter.addAction("com.amazon.mp3.auto.detection.Car_Bluetooth_Disconnect");
            registerReceiver(this.carBluetoothDetectionEventReceiver, intentFilter);
        }
        super.onCreate(savedInstanceState);
        setContentView(com.amazon.mp3.automotiveOS.R.layout.activity_music_home);
        initializeWazeBanner((WazeNavigationBar) findViewById(com.amazon.mp3.automotiveOS.R.id.wazeNavBar));
        this.mLoadingView = findViewById(com.amazon.mp3.automotiveOS.R.id.loading_view);
        this.mDynamicBackgroundImage = (ImageView) findViewById(com.amazon.mp3.automotiveOS.R.id.dynamicBackgroundImage);
        if (PlatformUtil.isVersionOrGreater(28)) {
            getWindow().getDecorView().setSystemUiVisibility(1024);
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.layoutInDisplayCutoutMode = 2;
            getWindow().setAttributes(attributes);
        }
        this.mHandler = new Handler();
        FragmentController fragmentController = new FragmentController(this, com.amazon.mp3.automotiveOS.R.id.music_screen_container);
        this.mFragmentController = fragmentController;
        fragmentController.setFragmentChangedCallback(this.mChangedCallback);
        if (hasLocaleChanged()) {
            this.mFragmentController.clearBackStack();
        }
        FeedViewModel feedViewModel = new FeedViewModelProvider(this).get();
        this.feedViewModel = feedViewModel;
        if (feedViewModel != null) {
            feedViewModel.getFeedEventLiveData().removeObservers(this);
            this.feedViewModel.getFeedEventLiveData().observe(this, new FeedEventObserver(this, com.amazon.mp3.automotiveOS.R.id.music_screen_container));
        }
        setupActivityFeedReactToolbarCallbacks();
        setupReactNativeToolbarCallbacks();
        CustomerLookupViewModel customerLookupViewModel = new CustomerLookupViewModelProvider(this).get();
        this.lookupViewModel = customerLookupViewModel;
        if (customerLookupViewModel != null) {
            customerLookupViewModel.getCustomerLookupEventLiveData().removeObservers(this);
            this.lookupViewModel.getCustomerLookupEventLiveData().observe(this, new CustomerLookupEventObserver(this, com.amazon.mp3.automotiveOS.R.id.music_screen_container));
        }
        FragmentInfo fragmentInfo = FragmentInfo.INSTANCE;
        fragmentInfo.setFragmentId(com.amazon.mp3.automotiveOS.R.id.music_screen_container);
        fragmentInfo.setFragmentManager(getSupportFragmentManager());
        registerLocalBroadcastActivityReceiver(this.mDeeplinkReceiver, new IntentFilter("com.amazon.mp3.util.handleVideoDeeplink"));
        this.mIsCastingBannerInRestingState = CastingBannerViewProvider.getRestingState(savedInstanceState);
        isSpatialAudioLaunched();
        if (!sBluetoothCoachMarkShown) {
            BluetoothUtil.INSTANCE.initCarDetection(this, new GrantPermissionActivity.PermissionResultReceiver() { // from class: com.amazon.mp3.activity.MusicHomeActivity.4
                @Override // com.amazon.mp3.activity.GrantPermissionActivity.PermissionResultReceiver
                protected void onGranted() {
                    BluetoothUtil.INSTANCE.startCarDetection();
                }
            }, Build.VERSION.SDK_INT);
            sBluetoothCoachMarkShown = true;
        }
        Podcast.create(getBootstrap(podcastDeeplinkPath));
        configureReactNative();
        Trace.endSection();
    }

    private void configureReactNative() {
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        if (featureGateProvider != null) {
            if (featureGateProvider.isFeatureAvailable(Feature.dragonfly_enabled) || featureGateProvider.isFeatureAvailable(Feature.dragonfly_fireos_enabled)) {
                RNRoutes.INSTANCE.parseRNRoutes(this);
                initializeReactNativeBridge();
            }
        }
    }

    private void initializeReactNativeBridge() {
        Trace.beginSection("AMA.MusicHomeActivity.initializeReactNativeBridge");
        Application application = getApplication();
        if (application instanceof AmazonApplication) {
            ReactNativeHost reactNativeHost = ((AmazonApplication) application).getReactNativeHost();
            if (reactNativeHost instanceof PrimaryReactInitializer) {
                ((PrimaryReactInitializer) reactNativeHost).setHostActivity(this);
            }
        }
        Trace.endSection();
    }

    public void clearPodcastDeeplinkPath() {
        podcastDeeplinkPath = "";
    }

    private void updateBottomBarHeightWithMiniplayer() {
        BottomNavigationBar bottomBar = BottomNavigationBarContainer.getInstance().getBottomBar();
        if (bottomBar != null) {
            bottomBar.updateView();
        }
    }

    private void setupActivityFeedReactToolbarCallbacks() {
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        if (featureGateProvider != null && featureGateProvider.isFeatureAvailable(Feature.activity_feed_on_react_native) && featureGateProvider.isFeatureAvailable(Feature.activity_feed_on_react_native_fragment)) {
            if (this.feedFragmentLifecycleCallbacks == null) {
                this.feedFragmentLifecycleCallbacks = new FeedFragmentLifecycleCallbacks(this, this);
            }
            getSupportFragmentManager().registerFragmentLifecycleCallbacks(this.feedFragmentLifecycleCallbacks, true);
        }
    }

    private void setupReactNativeToolbarCallbacks() {
        if (Feature.dragonfly_enabled.isEnabled() || Feature.dragonfly_fireos_enabled.isEnabled()) {
            if (this.reactNativeFragmentLifecycleCallbacks == null) {
                this.reactNativeFragmentLifecycleCallbacks = new ReactNativeFragmentLifeCycleCallbacks(this, this);
            }
            getSupportFragmentManager().registerFragmentLifecycleCallbacks(this.reactNativeFragmentLifecycleCallbacks, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupInAppSurvey() {
        String strFind;
        FeatureGateProvider featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class);
        if (featureGateProvider == null || !featureGateProvider.isFeatureAvailable(Feature.in_app_survey)) {
            return;
        }
        List<? extends HashMap<String, String>> listAsList = Arrays.asList(new HashMap<String, String>() { // from class: com.amazon.mp3.activity.MusicHomeActivity.5
            {
                put("screenName", "Home");
                put("componentName", "SurveyButton");
            }
        });
        try {
            strFind = AmazonApplication.getAuthInfoMAPR5().find(AuthDataType.DIRECTED_ID);
        } catch (AuthenticationException e) {
            Log.error(TAG, "Unable to fetch directed ID from AuthInfo", e);
            strFind = "";
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", strFind);
        TouchPointActivity.INSTANCE.getShared().configure(listAsList, map);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        if (!AccountCredentialUtil.get(this).isSignedIn()) {
            super.onStart();
            startLauncherActivityAndClose();
            return;
        }
        if (this.mRootContainer != null && this.mLastMarketplace != null) {
            init();
        }
        bindMusicPlaybackState();
        this.mPlaybackController.addOnPlayStateChangedListener(this);
        Playback playback = Podcast.getPlayback();
        bindPodcastPlaybackState(playback.getPlaybackState());
        playback.addStateCallback(this);
        if (new UserInfo().isPodcastLaunched() && !isMusicPlaying()) {
            Podcast.create(getOnRestoreMiniPlayerBootstrap()).startPlayback();
        }
        if ((this.mRootContainer == null || this.mLastMarketplace == null) && AsyncTask.Status.PENDING.equals(this.mBackgroundInitializationTask.getStatus())) {
            initBottomBarAndMiniPlayer();
            if (!sCastingForegroundCallbackSet && AmazonApplication.getCapabilities().isAutoConnectExperimentEnabled()) {
                CastingUtil.setForegroundAutoConnectInvoke(getApplicationContext());
                sCastingForegroundCallbackSet = true;
            }
            this.mBackgroundInitializationTask.execute(new Void[0]);
        } else {
            initializeAlexa();
        }
        displayViews();
        if (NowPlayingUtil.isVideoPlaying()) {
            LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent("com.amazon.mp3.library.activity.nowplaying.handlePip"));
        }
        if (PodcastFeatureGating.PODCAST.isEnabled()) {
            Podcast.registerNetworkCallback(getApplicationContext());
        }
        this.carModeFABViewController = new CarModeFABViewController(findViewById(com.amazon.mp3.automotiveOS.R.id.car_mode_fab_container), this);
        this.inAppSurveyFABViewController = new InAppSurveyFABViewController(findViewById(com.amazon.mp3.automotiveOS.R.id.in_app_survey_fab_container), this);
        CloudQueueClientSingleton.INSTANCE.initializeClients();
        super.onStart();
    }

    private void showErrorDialog() {
        new OneButtonErrorDialog(this, getString(com.amazon.mp3.automotiveOS.R.string.dmusic_dialog_prime_kids_content_not_found_title), getString(com.amazon.mp3.automotiveOS.R.string.dmusic_dialog_prime_kids_content_not_found_message), new ErrorDialogButton(getString(com.amazon.mp3.automotiveOS.R.string.dmusic_dialog_prime_kids_content_not_found_button), new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.activity.MusicHomeActivity.6
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                MusicHomeActivity.this.changeScreenFragment(PrimeTabFragmentHost.newInstance(new Bundle()), false, true, false);
            }
        })).show();
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        com.amazon.podcast.nowplaying.MiniPlayerView miniPlayerView;
        Trace.beginSection("CustomTrace - MusicHomeActivity.onResume");
        super.onResume();
        this.mResumed = true;
        CarModeUtility.checkIfAutoLaunchCarMode(this);
        if (PodcastFeatureGating.INCREMENTAL_SYNC_ON_MINI_PLAYER_VIEWED.isEnabled() && (miniPlayerView = this.podcastMiniPlayer) != null && miniPlayerView.getVisibility() == 0) {
            this.podcastMiniPlayer.handleOnViewed();
        }
        handleIntentIfNeeded();
        initializeAlexa();
        Trace.endSection();
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        this.mBackgroundInitializationTask.cancel(true);
        Subscription subscription = this.mSubscription;
        if (subscription != null) {
            subscription.unsubscribe();
            this.mSubscription = null;
        }
        Subscription subscription2 = this.mOnNextButtonClickedSubscription;
        if (subscription2 != null) {
            subscription2.unsubscribe();
            this.mOnNextButtonClickedSubscription = null;
        }
        Subscription subscription3 = this.mDynamicMessagingStylesheetSubscription;
        if (subscription3 != null) {
            subscription3.unsubscribe();
            this.mDynamicMessagingStylesheetSubscription = null;
        }
        if (this.mUiPageViewEventSubscriber != null) {
            EventBus.getDefault().unregister(this.mUiPageViewEventSubscriber);
        }
        if (this.mAOMEventSubscriber != null) {
            EventBus.getDefault().unregister(this.mAOMEventSubscriber);
            this.mAOMEventSubscriber.clear();
            this.mAOMEventSubscriber = null;
        }
        BottomNavigationBarContainer bottomNavigationBarContainer = this.mBottomBarContainer;
        if (bottomNavigationBarContainer != null && bottomNavigationBarContainer.isEnabled()) {
            this.mBottomBarContainer.detach(this);
        }
        MiniPlayerViewController miniPlayerViewController = this.mMiniPlayerViewController;
        if (miniPlayerViewController != null) {
            miniPlayerViewController.clear();
        }
        this.mDestroyed = true;
        CastingSessionManager.getInstance().unregisterListener(this.mCastingConnectionCallback);
        RelativeLayout relativeLayout = this.mCastingHeaderContainer;
        if (relativeLayout != null) {
            relativeLayout.setOnClickListener(null);
        }
        CastingBannerViewProvider castingBannerViewProvider = this.mCastingBannerGenerator;
        if (castingBannerViewProvider != null) {
            castingBannerViewProvider.onDestroy();
        }
        unregisterReceiver(this.mSyncEventReceiver);
        unregisterReceiver(this.mDisplayAppStartDynamicMessageReceiver);
        unregisterReceiver(this.mDisplayDeeplinkDynamicMessageReceiver);
        unregisterReceiver(this.carBluetoothDetectionEventReceiver);
        InAppMessagingManager inAppMessagingManager = this.mInAppMessagingManager;
        if (inAppMessagingManager != null) {
            inAppMessagingManager.onDestroy();
        }
        FeedViewModel feedViewModel = this.feedViewModel;
        if (feedViewModel != null) {
            feedViewModel.getFeedEventLiveData().removeObservers(this);
            this.feedViewModel = null;
        }
        CustomerLookupViewModel customerLookupViewModel = this.lookupViewModel;
        if (customerLookupViewModel != null) {
            customerLookupViewModel.getCustomerLookupEventLiveData().removeObservers(this);
            this.lookupViewModel = null;
        }
        DynMsgRibbon dynMsgRibbon = this.mDynMsgRibbon;
        if (dynMsgRibbon != null) {
            dynMsgRibbon.destroy();
        }
        UpsellManager.getInstance().clearSubscription();
        DynamicBackgroundImagePublisher.getInstance().unSubscribeBackgroundImageUpdate(this);
        unregisterLocalBroadcastActivityReceiver(this.mDeeplinkReceiver);
        if (isFinishing() && !isChangingConfigurations()) {
            CarModeUtility.INSTANCE.destroy();
        }
        SkipLimitTooltipPresenter skipLimitTooltipPresenter = this.skipLimitTooltipPresenter;
        if (skipLimitTooltipPresenter != null) {
            skipLimitTooltipPresenter.destroyExperiences();
        }
        SwipeControlTooltipExperience swipeControlTooltipExperience = this.swipeControlTooltip;
        if (swipeControlTooltipExperience != null) {
            swipeControlTooltipExperience.onDestroy();
        }
        if (this.feedFragmentLifecycleCallbacks != null) {
            getSupportFragmentManager().unregisterFragmentLifecycleCallbacks(this.feedFragmentLifecycleCallbacks);
        }
        if (this.reactNativeFragmentLifecycleCallbacks != null) {
            getSupportFragmentManager().unregisterFragmentLifecycleCallbacks(this.reactNativeFragmentLifecycleCallbacks);
        }
        LyricsSharingAssetManager.INSTANCE.destroy();
        minervaShutdown();
        unregisterToggleArtistForStoriesReceiver();
        super.onDestroy();
    }

    private void unregisterToggleArtistForStoriesReceiver() {
        VideoStoryPlaybackDataRepository.INSTANCE.get().unregisterToggleArtistForStoriesReceiver();
    }

    private void minervaShutdown() {
        ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.mp3.activity.MusicHomeActivity$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                MusicHomeActivity.lambda$minervaShutdown$0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$minervaShutdown$0() {
        try {
            MinervaMetricsFactory.shutdown();
        } catch (Exception e) {
            Log.error(TAG, "Exception shutting down minerva client", e);
        }
    }

    @Override // android.app.Activity
    public boolean onContextItemSelected(final MenuItem item) {
        MiniPlayerViewController miniPlayerViewController = this.mMiniPlayerViewController;
        if (miniPlayerViewController != null) {
            return super.onContextItemSelected(item) & miniPlayerViewController.onContextItemSelected(item);
        }
        return super.onContextItemSelected(item);
    }

    private void initBottomBarAndMiniPlayer() {
        Trace.beginSection("CustomTrace - MusicHomeActivity.initBottomBarAndMiniPlayer");
        if (this.mRootContainer == null) {
            this.mRootContainer = (ConstraintLayout) findViewById(com.amazon.mp3.automotiveOS.R.id.home_menu_drawer);
            initToolbar();
        }
        ViewGroup viewGroup = (ViewGroup) findViewById(com.amazon.mp3.automotiveOS.R.id.bottom_bar_and_mini_player_container);
        this.mBottomBarAndMiniPlayerView = viewGroup;
        viewGroup.setClipToOutline(true);
        this.mBottomBarAndMiniPlayerView.setClipChildren(true);
        this.podcastMiniPlayerLayout = (RelativeLayout) findViewById(com.amazon.mp3.automotiveOS.R.id.podcast_mini_player_layout);
        this.mBannerView = (PersistentBannerView) findViewById(com.amazon.mp3.automotiveOS.R.id.persistent_banner);
        if (this.mBottomBarContainer == null) {
            BottomNavigationBarContainer bottomNavigationBarContainer = BottomNavigationBarContainer.getInstance();
            this.mBottomBarContainer = bottomNavigationBarContainer;
            if (bottomNavigationBarContainer.isEnabled()) {
                this.mBottomBarContainer.attach(this, this.mBottomBarAndMiniPlayerView, new View[]{findViewById(com.amazon.mp3.automotiveOS.R.id.bottom_bar_nav_top_mask), findViewById(com.amazon.mp3.automotiveOS.R.id.bottom_bar_bottom_mask)}, this.mFragmentController, this);
            }
            setBottomBarLayoutListener();
        }
        this.mDynamicMessagingStylesheetSubscription = StyleSheetProvider.getStyleSheetObservable().observeOn(Schedulers.io()).subscribe(new Action1() { // from class: com.amazon.mp3.activity.MusicHomeActivity$$ExternalSyntheticLambda5
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$initBottomBarAndMiniPlayer$1((StyleSheet) obj);
            }
        });
        if (UserSubscriptionUtil.isNightwingOnly()) {
            this.mMiniPlayerView = new CloudQueueMiniPlayerView(this);
        } else {
            this.mMiniPlayerView = new MiniPlayerView(this);
        }
        ProgressBar progressBar = (ProgressBar) findViewById(com.amazon.mp3.automotiveOS.R.id.miniplayer_progress_bar);
        this.miniPlayerProgressBar = progressBar;
        this.mMiniPlayerView.setProgressBar(progressBar);
        FrameLayout frameLayout = (FrameLayout) this.mBottomBarContainer.getRightView();
        this.mMiniPlayerViewContainer = frameLayout;
        if (frameLayout == null) {
            this.mMiniPlayerViewContainer = (FrameLayout) findViewById(com.amazon.mp3.automotiveOS.R.id.mini_player_container);
        }
        this.mMiniPlayerViewContainer.addView(this.mMiniPlayerView);
        Playback playback = Podcast.getPlayback();
        if (playback != null && playback.isPodcastLastPlayed()) {
            this.mMiniPlayerViewContainer.setVisibility(8);
            this.miniPlayerProgressBar.setVisibility(8);
            setPodcastMiniPlayerVisibility(true, playback.getShouldShowMiniPlayerInApp(), !ObjectUtils.isNullOrEmpty(playback.readRestoreMiniPlayerMethods()));
        } else {
            this.mMiniPlayerViewContainer.setVisibility(0);
            com.amazon.podcast.nowplaying.MiniPlayerView miniPlayerView = this.podcastMiniPlayer;
            if (miniPlayerView != null && miniPlayerView.getVisibility() != 8) {
                this.podcastMiniPlayer.setVisibility(8);
                this.podcastMiniPlayerLayout.setVisibility(8);
            }
        }
        if (!getSupportFragmentManager().isDestroyed()) {
            MiniPlayerViewController miniPlayerViewController = new MiniPlayerViewController(this, this.mMiniPlayerView, MiniPlayerViewController.MiniPLayerSource.NONE);
            this.mMiniPlayerViewController = miniPlayerViewController;
            miniPlayerViewController.setOnPlayerVisibilityChangedListener(this);
        }
        SkipLimitTooltipPresenter skipLimitTooltipPresenter = this.skipLimitTooltipPresenter;
        if (skipLimitTooltipPresenter != null) {
            skipLimitTooltipPresenter.setSkipLimitTooltipExperiences(this.mMiniPlayerViewContainer, getApplicationContext());
        }
        if (AmazonApplication.getCapabilities().isCastingIngressExperimentEnabled() && this.swipeControlTooltip == null) {
            this.swipeControlTooltip = new SwipeControlTooltipExperience(getApplicationContext());
        }
        Subscription subscription = this.mOnNextButtonClickedSubscription;
        if (subscription == null || subscription.isUnsubscribed()) {
            this.mOnNextButtonClickedSubscription = this.mMiniPlayerViewController.getOnNextClickedSubject().subscribe(new Action1<Boolean>() { // from class: com.amazon.mp3.activity.MusicHomeActivity.7
                @Override // rx.functions.Action1
                public void call(final Boolean nextClicked) {
                    if (nextClicked.booleanValue() && MusicHomeActivity.this.mMiniPlayerViewContainer.getVisibility() == 0 && MusicHomeActivity.this.canShowSkipTooltip()) {
                        MusicHomeActivity.this.skipLimitTooltipPresenter.showSkipTooltip(MusicHomeActivity.this.mMiniPlayerView, MusicHomeActivity.this.mStyleSheet, MusicHomeActivity.this.inImmersiveMode(), false, false);
                    }
                }
            });
        }
        onVisibilityChanged(8);
        if (playback.isPodcastLastPlayed()) {
            Trace.endSection();
        } else {
            bindPodcastPlaybackState(playback.getPlaybackState());
            Trace.endSection();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initBottomBarAndMiniPlayer$1(StyleSheet styleSheet) {
        this.mStyleSheet = styleSheet;
        DynamicMessageStyleSheetLoader.getInstance().setStyleSheet(styleSheet);
        configureStates(styleSheet);
    }

    private void displayViews() {
        if (this.mRootContainer == null || this.mLoadingView == null) {
            this.mRootContainer = (ConstraintLayout) findViewById(com.amazon.mp3.automotiveOS.R.id.home_menu_drawer);
            this.mLoadingView = findViewById(com.amazon.mp3.automotiveOS.R.id.loading_view);
        }
        this.mRootContainer.setVisibility(0);
        this.mLoadingView.setVisibility(8);
    }

    private void startLauncherActivityAndClose() {
        Intent intent = new Intent(getApplicationContext(), (Class<?>) LauncherActivity.class);
        intent.addFlags(32768);
        intent.addFlags(268435456);
        startActivity(intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean canInitializeWithoutUser() {
        return !AccountCredentialStorage.get(this).shouldPerformSignInTasks();
    }

    private void showCookieConsentUIAndFetchStatusIfNeeded() {
        CookieConsentManager cookieConsentManager = CookieConsentManager.instance;
        if (cookieConsentManager.shouldStartCookieConsentFlow(false)) {
            if (cookieConsentManager.isCookieConsentStatusAvailable()) {
                lambda$showCookieConsentUIAndFetchStatusIfNeeded$2();
            } else {
                cookieConsentManager.cookieConsentSubscription(new Action() { // from class: com.amazon.mp3.activity.MusicHomeActivity$$ExternalSyntheticLambda3
                    @Override // io.reactivex.rxjava3.functions.Action
                    public final void run() throws Throwable {
                        this.f$0.lambda$showCookieConsentUIAndFetchStatusIfNeeded$2();
                    }
                }, new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amazon.mp3.activity.MusicHomeActivity$$ExternalSyntheticLambda4
                    @Override // io.reactivex.rxjava3.functions.Consumer
                    public final void accept(Object obj) throws Throwable {
                        MusicHomeActivity.lambda$showCookieConsentUIAndFetchStatusIfNeeded$3((Throwable) obj);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showCookieConsentUIAndFetchStatusIfNeeded$3(Throwable th) throws Throwable {
        Log.error(TAG, "Failed to fetch cookie consent, error: ", th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showCookieConsentIfRequiredElseRestartUI, reason: merged with bridge method [inline-methods] */
    public void lambda$showCookieConsentUIAndFetchStatusIfNeeded$2() {
        try {
            if (CookieConsentManager.instance.shouldRequestUserForConsent()) {
                showCookieConsentScreen();
            }
        } catch (Exception e) {
            Log.error(TAG, "Failed to show cookie consent UI", e);
        }
    }

    private void showCookieConsentScreen() {
        startActivity(CookieConsentActivity.getStartIntent(this));
    }

    private void showLanguagePref() {
        final LanguagePreference languagePreferenceBuildLanguagePreference = LanguagePreferenceProvider.buildLanguagePreference(this);
        new Thread(new Runnable() { // from class: com.amazon.mp3.activity.MusicHomeActivity.8
            @Override // java.lang.Runnable
            public void run() {
                try {
                    FeatureFlagProvider featureFlagProvider = FeatureFlagsProvider.getFeatureFlagProvider();
                    LanguagePreference languagePreference = languagePreferenceBuildLanguagePreference;
                    if (languagePreference == null || featureFlagProvider == null || !languagePreference.isAvailableOnAppOne(featureFlagProvider)) {
                        return;
                    }
                    MusicHomeActivity.this.startActivity(LanguagePreferenceActivity.getStartIntent(MusicHomeActivity.this));
                } catch (LanguagePreferenceException e) {
                    Log.error(MusicHomeActivity.TAG, "Failed to check should show language preference ", e);
                }
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleIntentIfNeeded() {
        if (this.mShouldHandleIntent.booleanValue()) {
            this.mShouldHandleIntent = Boolean.FALSE;
            Intent intent = getIntent();
            handleIntent(intent, intent.getExtras());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void init() {
        handleIntentIfNeeded();
        if (VoiceManagerFactory.shouldRefresh() && this.mBottomBarContainer.isEnabled()) {
            this.mBottomBarContainer.refreshAlexa();
        }
        showCookieConsentUIAndFetchStatusIfNeeded();
        showLanguagePref();
        String homeMarketPlaceId = AccountDetailUtil.get(this).getHomeMarketPlaceId();
        String str = this.mLastMarketplace;
        if (str != null && !str.equals(homeMarketPlaceId)) {
            Log.debug(TAG, "Marketplace changed");
            this.mLastMarketplace = homeMarketPlaceId;
            if (!AmazonApplication.getCapabilities().shouldBrowseBeSupported() && this.mBottomBarContainer.isEnabled()) {
                this.mBottomBarContainer.navigateToTab(LastViewedScreenUtil.LastViewedScreen.LIBRARY);
            }
        }
        showAppRatingPrompt();
        if (ChildUserUtil.INSTANCE.isChildUser(this)) {
            new AutomaticImportManager(this).setAutomaticImportPersistedState(false);
        } else {
            showAutomaticImportFtuPrompt();
        }
        if (((FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class)).isFeatureAvailable(Feature.retail_lop_sync)) {
            showRetailLopSyncPrompt();
        }
        registerReceiver(this.mSyncEventReceiver, new IntentFilter("com.amazon.mp3.library.service.sync.ACTION_SYNC_EVENT"));
        updateLastKnownPrimeStatus(UserSubscriptionUtil.getUserSubscription().isPrime());
        if (isRecentlyPrimeMember()) {
            showPrimeSplashScreen();
        }
        if (!this.mIsExternalAppLaunch && AccountCredentialUtil.get().isSignedOut()) {
            finish();
        }
        if (!isFinishing()) {
            SyncService.startTimeCheckedSync(this);
        }
        if (this.mIsAlexaEnabled && VoiceManagerSingleton.isInitialized()) {
            if (VoiceManagerSingleton.getInstance().isInitRequired()) {
                VoiceManagerSingleton.getInstance().handleReopenMusicApp(getApplicationContext());
            }
            VoiceManagerSingleton.getInstance().attachView(this, (ViewGroup) findViewById(R.id.content));
        }
        initializeCastingView();
        if (this.mBottomBarContainer.isEnabled()) {
            this.mBottomBarContainer.refreshBottomBar();
        }
        MiniPlayerViewController miniPlayerViewController = this.mMiniPlayerViewController;
        if (miniPlayerViewController != null) {
            miniPlayerViewController.refreshPlayer();
        }
        if (AmazonApplication.getCapabilities().isLyricsSharingEnabled()) {
            LyricsSharingAssetManager.INSTANCE.downloadAndCacheLyricsSharingBackgroundAssets();
        }
        removeAllExtras();
    }

    public void showLoadingView(boolean show) {
        this.mLoadingView.setVisibility(show ? 0 : 8);
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(final Bundle outState) {
        outState.putBoolean("fragmentStateSaved", getSupportFragmentManager().findFragmentById(com.amazon.mp3.automotiveOS.R.id.music_screen_container) != null);
        this.mBottomBarContainer.onSaveInstanceState(outState);
        CastingBannerViewProvider castingBannerViewProvider = this.mCastingBannerGenerator;
        if (castingBannerViewProvider != null) {
            castingBannerViewProvider.onSaveInstanceState(outState);
        }
        outState.putBoolean("automaticImportPromptState", this.wasFTUShownSinceLaunch.booleanValue());
        outState.putBoolean("retailLopSyncPromptState", this.wasRetailLopSyncFTUShownSinceLaunch.booleanValue());
        if (this.mIsAlexaEnabled && VoiceManagerSingleton.isInitialized()) {
            VoiceManager voiceManagerSingleton = VoiceManagerSingleton.getInstance();
            if (voiceManagerSingleton.isAlexaUiVisible()) {
                voiceManagerSingleton.onSaveInstanceState();
            }
        }
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            String string = intent.getExtras().getString("deeplinkUri", null);
            if (!StringUtils.isBlank(string)) {
                podcastDeeplinkPath = string;
            }
        }
        super.onSaveInstanceState(outState);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.FragmentActivity
    public void onAttachFragment(final Fragment fragment) {
        super.onAttachFragment(fragment);
        handleExpired(fragment);
        if (fragment instanceof OverflowMenuReceiver) {
            ((OverflowMenuReceiver) fragment).setActionBarProvider(this);
        }
        if (fragment instanceof BaseFragment) {
            onFragmentShown(fragment);
        }
        try {
            reportFullyDrawn();
        } catch (SecurityException e) {
            Log.error(TAG, "Received Exception when reportFullyDrawn() " + e);
        }
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        this.mResumed = false;
        InactivityProvider.updateActivity(this);
        unregisterReceiver(this.mSyncEventReceiver);
        if (isFinishing()) {
            sBluetoothCoachMarkShown = false;
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        this.mIsExternalAppLaunch = false;
        this.mPlaybackController.removeOnPlayStateChangedListener(this);
        Podcast.getPlayback().removeStateCallback(this);
        if (PodcastFeatureGating.PODCAST.isEnabled()) {
            Podcast.unRegisterNetworkCallback();
        }
        SkipLimitTooltipPresenter skipLimitTooltipPresenter = this.skipLimitTooltipPresenter;
        if (skipLimitTooltipPresenter != null) {
            skipLimitTooltipPresenter.dismissTooltips();
        }
        SwipeControlTooltipExperience swipeControlTooltipExperience = this.swipeControlTooltip;
        if (swipeControlTooltipExperience != null) {
            swipeControlTooltipExperience.dismissTooltip(false);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.mIsExternalAppLaunch) {
            finish();
            return;
        }
        if (this.mIsAlexaEnabled && VoiceManagerSingleton.isInitialized() && VoiceManagerSingleton.getInstance().isAlexaUiVisible()) {
            BottomNavigationBarContainer bottomNavigationBarContainer = this.mBottomBarContainer;
            if (bottomNavigationBarContainer != null) {
                bottomNavigationBarContainer.selectPreviousTab();
            }
            VoiceManagerSingleton.getInstance().closeAlexaUi();
            return;
        }
        Fragment currentFragment = this.mFragmentController.getCurrentFragment();
        if (currentFragment != null && (currentFragment instanceof BaseFragment) && ((BaseFragment) currentFragment).onBackPressed()) {
            return;
        }
        super.onBackPressed();
        Fragment currentFragment2 = this.mFragmentController.getCurrentFragment();
        if (currentFragment2 == null) {
            return;
        }
        if (currentFragment2 instanceof LibraryFragment) {
            ((LibraryFragment) currentFragment2).onLibraryFragmentShownOnBackPressed();
        }
        onFragmentShown(currentFragment2);
        selectPreviousTab(currentFragment2);
    }

    @Override // com.amazon.mp3.bottomnavbar.BottomBarUIListener
    public void onFragmentShown(final Fragment fragment) {
        BottomNavigationBarContainer bottomNavigationBarContainer;
        if (fragment == null || (bottomNavigationBarContainer = this.mBottomBarContainer) == null || !bottomNavigationBarContainer.isEnabled() || (fragment instanceof RetainedPrimeFragment)) {
            return;
        }
        if (fragment instanceof StoreFragment) {
            requestHomeButtonAsStoreClose();
            this.mBottomBarContainer.showBottomBar(false);
            return;
        }
        if (fragment instanceof MyLibraryV2HomeFragment) {
            ((MyLibraryV2HomeFragment) fragment).onLibraryHomeFragmentShownOnBackPressed();
        }
        boolean zIsNativeFindOrSearchFragment = SearchTabFragmentHost.isNativeFindOrSearchFragment(fragment);
        boolean z = fragment instanceof LibraryFragment;
        boolean zIsHeaderNavigationViewShown = (AmazonApplication.getCapabilities().isFacetedNavigationEnabled() && getSupportActionBar() != null && (getSupportActionBar().getCustomView() instanceof BauhausActionBarView)) ? ((BauhausActionBarView) getSupportActionBar().getCustomView()).isHeaderNavigationViewShown() : false;
        if (isBackStackEmpty()) {
            restoreHomeButton();
        } else if (!zIsNativeFindOrSearchFragment && !z && !zIsHeaderNavigationViewShown) {
            requestHomeButtonAsBack();
        }
        this.mBottomBarContainer.showBottomBar(true);
        this.mBottomBarAndMiniPlayerView.setVisibility(0);
        if (SearchTabFragmentHost.isNativeFindOrSearchFragment(fragment)) {
            this.mBottomBarContainer.setBottomBarTab(LastViewedScreenUtil.LastViewedScreen.SEARCH);
        }
    }

    private void showAutomaticImportFtuPrompt() {
        AutomaticImportManager automaticImportManager = new AutomaticImportManager(this);
        if (!automaticImportManager.showAutomaticImportFtu() || this.wasFTUShownSinceLaunch.booleanValue()) {
            return;
        }
        automaticImportManager.setAutomaticImportPersistedState(false);
        automaticImportManager.automaticImportFtuCompleted();
    }

    private void showRetailLopSyncPrompt() {
        if (this.wasRetailLopSyncFTUShownSinceLaunch.booleanValue()) {
            return;
        }
        RetailLopSyncManager retailLopSyncManager = new RetailLopSyncManager(this, PreferenceManager.getDefaultSharedPreferences(this));
        RetailPreferencesRetriever retailPreferencesRetriever = new RetailPreferencesRetriever(this);
        AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(this);
        String customerId = getCustomerId();
        if (StringUtils.isNotBlank(customerId)) {
            final RetailLopSyncHandler retailLopSyncHandler = new RetailLopSyncHandler(retailPreferencesRetriever, retailLopSyncManager, accountCredentialStorage, getSupportFragmentManager(), customerId, getApplicationContext());
            if (retailLopSyncHandler.shouldDisplayRetailLopFtuPrompt().booleanValue()) {
                AmazonApplication.getBackgroundHandler().postAtFrontOfQueue(new Runnable() { // from class: com.amazon.mp3.activity.MusicHomeActivity$$ExternalSyntheticLambda6
                    @Override // java.lang.Runnable
                    public final void run() {
                        retailLopSyncHandler.initiateRetailLopFtuPrompt();
                    }
                });
            }
        }
        this.wasRetailLopSyncFTUShownSinceLaunch = Boolean.TRUE;
    }

    private String getCustomerId() {
        try {
            return AccountManagerSingleton.get().getUser().getCustomerId();
        } catch (DataNotReadyException | MusicAccountNotCreatedException unused) {
            return "";
        }
    }

    private void showAppRatingPrompt() {
        if (!AmazonApplication.getCapabilities().isAppRatingEnabled(this) || RatingPromptDialogFragment.wasShownSinceLaunch()) {
            return;
        }
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (!AmazonApplication.getCapabilities().isAppRatingSupported()) {
            new AppHealthUtil(defaultSharedPreferences).resetAppOpenedNoIncidentCount();
        } else if (new RatingPromptPolicy(defaultSharedPreferences, new AppHealthUtil(defaultSharedPreferences)).shouldShowPrompt()) {
            RatingPromptDialogFragment ratingPromptDialogFragment = new RatingPromptDialogFragment();
            FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransactionBeginTransaction.add(ratingPromptDialogFragment, "AppRatingPromptDialog");
            fragmentTransactionBeginTransaction.commitAllowingStateLoss();
        }
    }

    private void initializeCastingView() {
        if (AmazonApplication.getCapabilities().isBauhausInAppMessagingEnabled()) {
            if (this.mCastingBannerGenerator == null) {
                this.mCastingBannerGenerator = new CastingBannerViewProvider(this, this.mRootContainer, this.mBannerView, Boolean.valueOf(this.mIsCastingBannerInRestingState));
                return;
            }
            return;
        }
        CastingSessionManager castingSessionManager = CastingSessionManager.getInstance();
        if (this.mCastingHeaderContainer == null) {
            this.mCastingHeaderContainer = (RelativeLayout) findViewById(com.amazon.mp3.automotiveOS.R.id.casting_header_container);
            this.mCastingHeaderTextView = (TextView) findViewById(com.amazon.mp3.automotiveOS.R.id.casting_header_textview);
        }
        castingSessionManager.registerListener(this.mCastingConnectionCallback);
        if (!this.mCastingHeaderContainer.hasOnClickListeners()) {
            this.mCastingHeaderContainer.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.activity.MusicHomeActivity.9
                @Override // android.view.View.OnClickListener
                public void onClick(final View v) {
                    Intent startIntent = CastingFragment.getStartIntent(v.getContext());
                    CastingUIMetricsLogger.recordCastingHeaderClickMetric();
                    MusicHomeActivity.this.startActivity(startIntent);
                }
            });
        }
        boolean zIsCasting = CastingUtil.isCasting();
        if (zIsCasting && !CastingUtil.isAlexaCastingEnabled() && !CastingCategory.CHROMECAST.equals(castingSessionManager.getLastDevice().getCategory())) {
            castingSessionManager.disconnect(TerminationReason.USER_DISCONNECT);
        } else {
            updateCastingHeaderView(zIsCasting);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCastingHeaderView(final boolean show) {
        if (show && !AmazonApplication.getCapabilities().isAMConnectUXUpdateEnabled()) {
            this.mCastingHeaderTextView.setText(getString(com.amazon.mp3.automotiveOS.R.string.casting_connected_to_text, CastingUtil.getCastingDeviceName(this)));
            this.mCastingHeaderContainer.setVisibility(0);
            StatusBarUtil.setStatusBarColor(this, com.amazon.mp3.automotiveOS.R.color.casting_header_dark_blue);
            return;
        }
        this.mCastingHeaderContainer.setVisibility(8);
        StatusBarUtil.resetStatusBarColor(this);
    }

    private boolean isBackStackEmpty() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        return supportFragmentManager == null || supportFragmentManager.getBackStackEntryCount() <= 0;
    }

    private void selectPreviousTab(final Fragment fragment) {
        BottomNavigationBarContainer bottomNavigationBarContainer = this.mBottomBarContainer;
        if (bottomNavigationBarContainer == null || !bottomNavigationBarContainer.isEnabled()) {
            return;
        }
        if ((fragment instanceof MyLibraryHomeFragment) || (fragment instanceof MyLibraryV2HomeFragment)) {
            this.mBottomBarContainer.setBottomBarTab(LastViewedScreenUtil.LastViewedScreen.LIBRARY);
            return;
        }
        if (SearchTabFragmentHost.isNativeFindOrSearchFragment(fragment)) {
            this.mBottomBarContainer.setBottomBarTab(LastViewedScreenUtil.LastViewedScreen.SEARCH);
        } else if ((fragment instanceof PrimeTabFragmentHost) || (fragment instanceof StoreFragment)) {
            this.mBottomBarContainer.setBottomBarTab(LastViewedScreenUtil.LastViewedScreen.PRIME_MUSIC);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isRecentlyPrimeMember() {
        boolean zIsPrime = UserSubscriptionUtil.getUserSubscription().isPrime();
        Boolean bool = this.mCurrentPrimeStatus;
        boolean z = false;
        if (bool == null || bool.booleanValue() != zIsPrime) {
            if (getSharedPreferences("com.amazon.mp3_MusicHomeActivity", 0).contains("last_known_prime_status_key") && !readLastKnownPrimeStatus() && zIsPrime) {
                z = true;
            }
            updateLastKnownPrimeStatus(zIsPrime);
        }
        return z;
    }

    private void updateLastKnownPrimeStatus(final boolean isPrime) {
        this.mCurrentPrimeStatus = Boolean.valueOf(isPrime);
        writeLastKnownPrimeStatus(isPrime);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showPrimeSplashScreen() {
        if (InAppMessagingManager.supportsInAppMessaging() || AccountDetailUtil.getMusicTerritoryOfResidence().equals(MusicTerritory.BRAZIL.value)) {
            return;
        }
        Context applicationContext = getApplicationContext();
        startActivityForResult(PrimeUpsellActivity.getWebIntent(applicationContext, new UpsellSplashPageWebTargetBuilderFactory(applicationContext).newBuilder().build()), PrimeUpsellActivity.REQUEST_CODE);
    }

    private boolean readLastKnownPrimeStatus() {
        return getSharedPreferences("com.amazon.mp3_MusicHomeActivity", 0).getBoolean("last_known_prime_status_key", false);
    }

    private void writeLastKnownPrimeStatus(boolean primeStatus) {
        SharedPreferences.Editor editorEdit = getSharedPreferences("com.amazon.mp3_MusicHomeActivity", 0).edit();
        editorEdit.putBoolean("last_known_prime_status_key", primeStatus);
        editorEdit.apply();
    }

    @Override // com.amazon.mp3.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(final Intent intent) {
        String stringExtra;
        String stringExtra2;
        super.onNewIntent(intent);
        setIntent(intent);
        this.mShouldHandleIntent = Boolean.TRUE;
        if (this.mInAppMessagingManager != null && (stringExtra2 = intent.getStringExtra("com.amazon.mp3.iam.IAM_REQUEST")) != null) {
            intent.removeExtra("com.amazon.mp3.iam.IAM_REQUEST");
            IAMRequest iAMRequestFromJson = IAMRequest.fromJson(stringExtra2);
            if (!WeblabHelper.getInstance().isSplashV2Enabled()) {
                this.mInAppMessagingManager.displayInAppMessage(this, iAMRequestFromJson, Boolean.FALSE);
            }
        }
        if (this.mDynamicMessagingManager != null && (stringExtra = intent.getStringExtra("com.amazon.mp3.iam.DM_DEEPLINK_REQUEST")) != null) {
            intent.removeExtra("com.amazon.mp3.iam.DM_DEEPLINK_REQUEST");
            if (WeblabHelper.getInstance().isSplashV2Enabled()) {
                this.mDynamicMessagingManager.loadDeeplinkDynamicMessage(new DynamicMessageEvent(URI.create(stringExtra)), false);
            }
        }
        if (this.mResumed) {
            this.mHandler.post(new Runnable() { // from class: com.amazon.mp3.activity.MusicHomeActivity$$ExternalSyntheticLambda7
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.handleIntentIfNeeded();
                }
            });
        }
    }

    private void handleIntent(final Intent intent, final Bundle extras) {
        if (extras == null) {
            this.mFragmentController.showDefaultLibraryScreen();
            return;
        }
        if (intent.getBooleanExtra("unavailableDeeplinkForChildUser", false)) {
            showErrorDialog();
            return;
        }
        if (extras.containsKey("podcastDeeplink")) {
            handleDeeplinkPodcastIntent(intent, extras);
            return;
        }
        this.mIsExternalAppLaunch = extras.getBoolean("com.amazon.mp3.launch.EXTERNAL", false);
        String string = extras.getString("com.amazon.mp3.library.EXTRA_SOURCE_ID");
        if (!AccountRequestUtil.isAccountValid(this) || !AmazonApplication.getCapabilities().shouldCloudBeSupported()) {
            string = "cirrus-local";
        }
        setSourceId(string);
        if (this.mFragmentController.getCurrentFragment() != null && extras.getBoolean("com.amazon.mp3.navigate.to.nextActivityScreen", false)) {
            startNextActivityIfExists();
            return;
        }
        navigateToIntent(intent, extras);
        if (LastViewedScreenUtil.LastViewedScreen.ALEXA == extras.getSerializable("com.amazon.mp3.navigate.to.screen")) {
            String string2 = extras.getString(DeeplinkIntentKey.KEY_REF_MARKER.toString());
            String string3 = extras.getString(DeeplinkIntentKey.KEY_ASSOCIATE_TAG.toString());
            if (VoiceManagerSingleton.isInitialized()) {
                VoiceManagerSingleton.getInstance().setRefMarkerAndAssociateTag(string2, string3);
            }
            this.mBottomBarContainer.onAlexaTabSelected();
        }
        LastViewedScreenUtil.LastViewedScreen lastViewedScreen = LastViewedScreenUtil.LastViewedScreen.LIBRARY;
        if (lastViewedScreen == extras.getSerializable("com.amazon.mp3.navigate.to.screen")) {
            this.mBottomBarContainer.navigateToTab(lastViewedScreen);
        }
        if (extras.containsKey("com.amazon.mp3.util.videoDeeplink")) {
            this.mDeeplinkExtras = extras;
            handleVideoDeeplinkIntent(intent);
        } else if (extras.containsKey("com.amazon.mp3.util.videoStoryDeeplink")) {
            this.mDeeplinkExtras = extras;
            handleVideoStoryDeeplinkIntent(intent);
        } else {
            startNextActivityIfExists();
        }
    }

    private void navigateToIntent(final Intent intent, final Bundle extras) {
        if (!extras.getBoolean("com.amazon.mp3.launch.LOADCURRENT", false) || "".equals(this.mFragmentController.getCurrentFragmentName())) {
            goToScreen(intent);
        }
    }

    private void goToScreen(final Intent intent) {
        String stringExtra;
        if (this.mFragmentController.navigateFromIntent(intent) || (stringExtra = intent.getStringExtra(SearchIntents.EXTRA_QUERY)) == null || !"android.intent.action.SEARCH".equals(intent.getAction())) {
            return;
        }
        Bundle bundleBundleForSearchString = HTMLStoreActivityFactory.bundleForSearchString(stringExtra);
        bundleBundleForSearchString.putBoolean("com.amazon.mp3.extra.EXTRA_CLEAR_HISTORY", false);
        bundleBundleForSearchString.putString("com.amazon.mp3.store.TITLE", stringExtra);
        Intent intent2 = new Intent();
        intent2.putExtra("com.amazon.mp3.store.extra.STORE_ARGUMENTS_BUNDLE", bundleBundleForSearchString);
        intent2.putExtra("com.amazon.mp3.fragment.extra", StoreFragment.class.getSimpleName());
        this.mFragmentController.navigateFromIntent(intent2);
    }

    @Override // com.amazon.mp3.fragment.navigation.NavigationProvider
    public void changeScreenFragment(final Fragment fragment, final boolean addToBackStack, final boolean forceReplace, final boolean animate) {
        this.mFragmentController.changeScreenFragment(fragment, addToBackStack, forceReplace, animate);
    }

    @Override // com.amazon.mp3.fragment.navigation.NavigationProvider
    public FragmentController getFragmentController() {
        return this.mFragmentController;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onSearchRequested() {
        Fragment currentFragment = this.mFragmentController.getCurrentFragment();
        if (this.mFragmentController.getCurrentFragment() instanceof BaseFragment) {
            return ((BaseFragment) currentFragment).onSearchRequested();
        }
        return false;
    }

    protected void setBottomBarLayoutListener() {
        if (isBottomBarContainerEnabled() && isBottomBarViewTreeObserverAvailable()) {
            this.mBottomBarAndMiniPlayerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.mp3.activity.MusicHomeActivity.11
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    if (MusicHomeActivity.this.isBottomBarViewTreeObserverAvailable()) {
                        MusicHomeActivity.this.mBottomBarAndMiniPlayerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                    MusicHomeActivity.this.mBottomBarContainer.refreshBottomBar();
                }
            });
        }
    }

    private boolean isBottomBarContainerEnabled() {
        BottomNavigationBarContainer bottomNavigationBarContainer = this.mBottomBarContainer;
        return bottomNavigationBarContainer != null && bottomNavigationBarContainer.isEnabled();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isBottomBarViewTreeObserverAvailable() {
        ViewGroup viewGroup = this.mBottomBarAndMiniPlayerView;
        return (viewGroup == null || viewGroup.getViewTreeObserver() == null || !this.mBottomBarAndMiniPlayerView.getViewTreeObserver().isAlive()) ? false : true;
    }

    private boolean hasLocaleChanged() {
        String str = sLocale;
        sLocale = getResources().getConfiguration().locale.toString();
        return !TextUtils.equals(str, r1);
    }

    private void setSourceId(String sourceId) {
        if (sourceId != null && LibraryBaseFragment.isValidLibrarySource(sourceId)) {
            LastViewedScreenUtil.setLastViewedSource(this, LastViewedScreenUtil.LastViewedSource.fromCirrusMediaSource(sourceId));
        }
    }

    @Override // com.amazon.mp3.activity.BaseActivity, android.app.Activity, android.content.ComponentCallbacks2
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Fragment currentFragment = this.mFragmentController.getCurrentFragment();
        if (currentFragment == null || !(currentFragment instanceof StoreFragment)) {
            return;
        }
        ((StoreFragment) currentFragment).onTrimMemory(level);
    }

    private void handleExpired(Fragment fragment) {
        Bundle extras;
        int i;
        if (!(fragment instanceof AbstractListFragment) || (extras = getIntent().getExtras()) == null || (i = extras.getInt("com.amazon.mp3.library.EXTRA_CONTENT_UNAVAILABLE_REASON", -1)) == -1) {
            return;
        }
        Uri uri = (Uri) extras.getParcelable("com.amazon.mp3.library.EXTRA_CONTENT_URI");
        AbstractListFragment abstractListFragment = (AbstractListFragment) fragment;
        if (this.mHandleExpiredJob == -1) {
            this.mHandleExpiredJob = addJob(new HandleExpiredContentJob(uri, i, abstractListFragment));
        }
        extras.remove("com.amazon.mp3.library.EXTRA_CONTENT_UNAVAILABLE_REASON");
    }

    @Override // com.amazon.mp3.activity.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        if (requestCode == SettingsActivity.SETTINGS_REQUEST_CODE && resultCode == 3) {
            finish();
            return;
        }
        if (requestCode == PrimeUpsellActivity.REQUEST_CODE) {
            FTUSettingsUtil.setFTUShown(this);
            if (resultCode != 2 && data != null && ((PrimeUpsellActivity.PrimeUpsellResult) data.getSerializableExtra("com.amazon.mp3.extra.PrimeUpsellActivity.PrimeUpsellResult")) == PrimeUpsellActivity.PrimeUpsellResult.EXPLORE_PRIME_MUSIC) {
                startActivity(PrimeActivityFactory.getPrimeSectionIntent(this));
            }
        } else if (requestCode == AddToPlaylistTabActivity.REQUEST_CODE || requestCode == AddToPlaylistActivity.REQUEST_CODE) {
            getFragmentController().getCurrentFragment().onActivityResult(requestCode, resultCode, data);
        }
        BottomNavigationBarContainer bottomNavigationBarContainer = this.mBottomBarContainer;
        if (bottomNavigationBarContainer != null && bottomNavigationBarContainer.isEnabled()) {
            this.mBottomBarContainer.onActivityResult(requestCode, resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override // com.amazon.mp3.service.job.JobSessionActivity
    public void onJobFinished(final long jobId, final Job job, final int resultCode, final Bundle resultBundle) {
        super.onJobFinished(jobId, job, resultCode, resultBundle);
        BottomNavigationBarContainer bottomNavigationBarContainer = this.mBottomBarContainer;
        if (bottomNavigationBarContainer != null && bottomNavigationBarContainer.isEnabled()) {
            this.mBottomBarContainer.onJobFinished(jobId, job, resultCode, resultBundle);
        }
        if (jobId == this.mHandleExpiredJob && (job instanceof HandleExpiredContentJob)) {
            this.mHandleExpiredJob = -1L;
            HandleExpiredContentJob handleExpiredContentJob = (HandleExpiredContentJob) job;
            handleExpiredContentJob.mFragment.setClickedItem(handleExpiredContentJob.mItem);
            handleExpiredContentJob.mFragment.handleDisabledContentError(ContentUnavailableReason.values()[handleExpiredContentJob.mReason], handleExpiredContentJob.mItem);
        }
    }

    private void removeAllExtras() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Iterator<String> it = extras.keySet().iterator();
            while (it.hasNext()) {
                getIntent().removeExtra(it.next());
            }
        }
    }

    @Override // com.amazon.mp3.service.job.JobSessionActivity
    public Session getSession() {
        return super.getSession();
    }

    private static class HandleExpiredContentJob extends Job {
        private final Uri mContentUri;
        public final AbstractListFragment mFragment;
        public volatile AbstractItem mItem;
        public final int mReason;

        public HandleExpiredContentJob(Uri contentUri, int unavailabilityReason, AbstractListFragment frag) {
            this.mContentUri = contentUri;
            this.mReason = unavailabilityReason;
            this.mFragment = frag;
        }

        @Override // com.amazon.mp3.service.job.Job
        public int run() throws Exception {
            this.mItem = AmazonApplication.getLibraryItemFactory().getItem(this.mContentUri);
            return 0;
        }
    }

    public void sendUserEnteredMusicStoreMetric() {
        this.mHandler.post(new Runnable() { // from class: com.amazon.mp3.activity.MusicHomeActivity.16
            @Override // java.lang.Runnable
            public void run() {
                FTUMetricsSingleton.get().sendFlexEventUserEnteredMusicStore();
            }
        });
    }

    private void startNextActivityIfExists() {
        ActivityStacker activityStacker = new ActivityStacker();
        if (activityStacker.shouldGetNextActivityIntent(getIntent())) {
            try {
                Intent nextActivityIntent = activityStacker.getNextActivityIntent(getApplicationContext(), getIntent());
                nextActivityIntent.setFlags(536870912);
                startActivity(nextActivityIntent);
                if (getIntent().getExtras().getBoolean("com.amazon.mp3.navigate.to.suppressHomeActivity", false)) {
                    finish();
                }
            } catch (ActivityStacker.GetNextActivityIntentException e) {
                Log.error(TAG, "The next activity in the intent was not valid, will not start another activity. Exception: %s: %s", e.getMessage(), e.getCause());
            }
        }
    }

    public static void startPrimeSectionWithRefresh(final Context context) {
        Intent primeSectionIntent = PrimeActivityFactory.getPrimeSectionIntent(context);
        primeSectionIntent.putExtra("com.amazon.mp3.EXTRA_FORCE_REFRESH_FLAG", true);
        if (!(context instanceof Activity)) {
            primeSectionIntent.addFlags(268435456);
        }
        context.startActivity(primeSectionIntent);
    }

    public static final class AOMEventSubscriber {
        private View mBottomBarAndMiniPlayerContainer;
        private BottomNavigationBarContainer mBottomNavigationBarContainer;

        AOMEventSubscriber(final View bottomBarAndMiniPlayerContainer, final BottomNavigationBarContainer bottomNavigationBarContainer) {
            Validate.notNull(bottomBarAndMiniPlayerContainer, "bottomNavigationBarContainer", new Object[0]);
            Validate.notNull(bottomNavigationBarContainer, "bottomNavigationBarContainer cannot be null", new Object[0]);
            this.mBottomBarAndMiniPlayerContainer = bottomBarAndMiniPlayerContainer;
            this.mBottomNavigationBarContainer = bottomNavigationBarContainer;
        }

        @Subscribe(threadMode = ThreadMode.MAIN)
        public void onEvent(final BottomNavBarEvent event) {
            int i = AnonymousClass20.$SwitchMap$com$amazon$mp3$bottomnavbar$events$BottomNavBarEventType[event.getBottomNavBarEventType().ordinal()];
            if (i == 1) {
                this.mBottomNavigationBarContainer.onAlexaTabSelected();
            } else if (i == 2) {
                this.mBottomBarAndMiniPlayerContainer.setVisibility(0);
            } else {
                if (i != 3) {
                    return;
                }
                this.mBottomBarAndMiniPlayerContainer.setVisibility(8);
            }
        }

        public void clear() {
            this.mBottomNavigationBarContainer = null;
            this.mBottomBarAndMiniPlayerContainer = null;
        }
    }

    @Override // com.amazon.mp3.dynamicbackground.DynamicBackgroundImagePublisher.DynamicBackgroundSubscriber
    public void onPauseBackgroundImageUpdate() {
        ImageView imageView = this.mDynamicBackgroundImage;
        if (imageView != null) {
            imageView.setVisibility(4);
        }
    }

    @Override // com.amazon.mp3.dynamicbackground.DynamicBackgroundImagePublisher.DynamicBackgroundSubscriber
    public void onResumeBackgroundImageUpdate() {
        ImageView imageView = this.mDynamicBackgroundImage;
        if (imageView != null) {
            imageView.setVisibility(0);
        }
    }

    @Override // com.amazon.mp3.dynamicbackground.DynamicBackgroundImagePublisher.DynamicBackgroundSubscriber
    public void onBackgroundImageUpdate(Bitmap bitmap) {
        ImageView imageView = this.mDynamicBackgroundImage;
        if (imageView != null) {
            if (bitmap != null) {
                DynamicBackgroundImagePublisher.getInstance().setBackgroundWithTransition(getApplicationContext(), getResources(), this.mDynamicBackgroundImage, bitmap);
            } else {
                imageView.setImageDrawable(null);
            }
        }
    }

    @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
    public void onPlayStateChanged() {
        Trace.beginSection("CustomTrace - MusicHomeActivity.onPlayStateChanged");
        bindMusicPlaybackState();
        Trace.endSection();
    }

    private void bindMusicPlaybackState() {
        Trace.beginSection("CustomTrace - MusicHomeActivity.bindMusicPlaybackState");
        Playback playback = Podcast.getPlayback();
        if (isPodcastPlaying() && !CastingUtil.isCasting()) {
            Trace.endSection();
            return;
        }
        int i = AnonymousClass20.$SwitchMap$com$amazon$music$media$playback$PlayStatus[this.mPlaybackController.getPlayStatus().ordinal()];
        if (i == 1 || i == 2 || i == 3 || i == 4) {
            FrameLayout frameLayout = this.mMiniPlayerViewContainer;
            if (frameLayout != null) {
                if (frameLayout.getVisibility() != 0) {
                    this.mMiniPlayerViewContainer.setVisibility(0);
                    this.miniPlayerProgressBar.setVisibility(0);
                    if (playback != null) {
                        playback.stopPodcastPlayback();
                    }
                }
                com.amazon.podcast.nowplaying.MiniPlayerView miniPlayerView = this.podcastMiniPlayer;
                if (miniPlayerView != null && miniPlayerView.getVisibility() == 0) {
                    this.podcastMiniPlayer.setVisibility(8);
                    this.podcastMiniPlayerLayout.setVisibility(8);
                }
            }
            if (playback != null) {
                if (playback.getPlaybackState() == 0) {
                    Trace.endSection();
                    return;
                }
                playback.clear();
            }
        } else if (i != 5 && playback != null && playback.isPodcastLastPlayed()) {
            this.mMiniPlayerViewContainer.setVisibility(8);
            this.miniPlayerProgressBar.setVisibility(8);
            setPodcastMiniPlayerVisibility(true, playback.getShouldShowMiniPlayerInApp(), !ObjectUtils.isNullOrEmpty(playback.readRestoreMiniPlayerMethods()));
        }
        Trace.endSection();
    }

    /* JADX INFO: renamed from: com.amazon.mp3.activity.MusicHomeActivity$20, reason: invalid class name */
    static /* synthetic */ class AnonymousClass20 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$bottomnavbar$events$BottomNavBarEventType;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$media$playback$PlayStatus;

        static {
            int[] iArr = new int[PlayStatus.values().length];
            $SwitchMap$com$amazon$music$media$playback$PlayStatus = iArr;
            try {
                iArr[PlayStatus.LOADING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlayStatus[PlayStatus.BUFFERING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlayStatus[PlayStatus.PREPARING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlayStatus[PlayStatus.RENDERING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$PlayStatus[PlayStatus.USER_PAUSED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr2 = new int[BottomNavBarEventType.values().length];
            $SwitchMap$com$amazon$mp3$bottomnavbar$events$BottomNavBarEventType = iArr2;
            try {
                iArr2[BottomNavBarEventType.TRIGGER_ALEXA.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$mp3$bottomnavbar$events$BottomNavBarEventType[BottomNavBarEventType.SHOW.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$mp3$bottomnavbar$events$BottomNavBarEventType[BottomNavBarEventType.HIDE.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    @Override // com.amazon.podcast.media.playback.Playback.StateCallback
    public void onPlaybackStateChange(final int state) {
        Trace.beginSection("CustomTrace - MusicHomeActivity.onPlaybackStateChange");
        bindPodcastPlaybackState(state);
    }

    @Override // com.amazon.music.views.library.views.presentation.ActionBarOverlayable
    public void setActionBarOverlay(boolean isOverlay) {
        View viewFindViewById;
        if (isOverlay == this.mIsActionBarOverlay || (viewFindViewById = this.mRootContainer.findViewById(com.amazon.mp3.automotiveOS.R.id.music_screen_container)) == null) {
            return;
        }
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this.mRootContainer);
        if (isOverlay) {
            constraintSet.connect(viewFindViewById.getId(), 3, com.amazon.mp3.automotiveOS.R.id.toolbar, 3);
        } else {
            constraintSet.connect(viewFindViewById.getId(), 3, com.amazon.mp3.automotiveOS.R.id.wazeNavBar, 4);
        }
        this.mIsActionBarOverlay = isOverlay;
        constraintSet.applyTo(this.mRootContainer);
    }

    @Override // com.amazon.music.views.library.views.presentation.ActionBarOverlayable
    public boolean isActionBarOverlay() {
        return this.mIsActionBarOverlay;
    }

    @Override // com.amazon.music.views.library.views.presentation.ActionBarOverlayable
    public RecyclerView.ItemDecoration setContentOffset(RecyclerView recyclerView, int offset) {
        BauhausActionBarView.ContentOffsetItemDecoration contentOffsetItemDecoration = new BauhausActionBarView.ContentOffsetItemDecoration(offset);
        if (recyclerView != null) {
            recyclerView.addItemDecoration(contentOffsetItemDecoration);
        }
        return contentOffsetItemDecoration;
    }

    private boolean isMusicPlaying() {
        PlayStatus playStatus = this.mPlaybackController.getPlayStatus();
        return playStatus == PlayStatus.LOADING || playStatus == PlayStatus.BUFFERING || playStatus == PlayStatus.PREPARING || playStatus == PlayStatus.RENDERING;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.activity.MusicHomeActivity$17, reason: invalid class name */
    class AnonymousClass17 implements Runnable {
        final /* synthetic */ MusicHomeActivity this$0;

        @Override // java.lang.Runnable
        public void run() {
            this.this$0.initializeAlexa();
        }
    }

    public View getAnchorViewForToast() {
        return this.mBottomBarAndMiniPlayerView;
    }

    private void bindPodcastPlaybackState(final int state) {
        Trace.beginSection("CustomTrace - MusicHomeActivity.bindPodcastPlaybackState");
        if (this.podcastMiniPlayer == null) {
            Trace.endSection();
            return;
        }
        Playback playback = Podcast.getPlayback();
        if (state == 0) {
            bindMusicPlaybackState();
            if (this.podcastMiniPlayer.getVisibility() == 8) {
                Trace.endSection();
                return;
            } else {
                this.podcastMiniPlayer.setVisibility(8);
                this.podcastMiniPlayerLayout.setVisibility(8);
            }
        } else if (state != 1) {
            if ((state == 2 || state == 3 || state == 6) && playback.isCasting()) {
                playback.startPlaybackOnChromecast();
            }
            setPodcastMiniPlayerVisibility(true, playback.getShouldShowMiniPlayerInApp(), !ObjectUtils.isNullOrEmpty(playback.readRestoreMiniPlayerMethods()));
        } else {
            setPodcastMiniPlayerVisibility(false, playback.getShouldShowMiniPlayerInApp(), !ObjectUtils.isNullOrEmpty(playback.readRestoreMiniPlayerMethods()));
        }
        Trace.endSection();
    }

    private void setPodcastMiniPlayerVisibility(boolean showPodcasts, boolean shouldShowMiniPlayerInApp, boolean isRestoreMethodsAvailable) {
        Trace.beginSection("CustomTrace - MusicHomeActivity.setPodcastMiniPlayerVisibility");
        if (!shouldShowMiniPlayerInApp || !isRestoreMethodsAvailable) {
            com.amazon.podcast.nowplaying.MiniPlayerView miniPlayerView = this.podcastMiniPlayer;
            if (miniPlayerView != null) {
                miniPlayerView.setVisibility(8);
                this.podcastMiniPlayerLayout.setVisibility(8);
            }
            this.mMiniPlayerViewContainer.setVisibility(8);
            this.miniPlayerProgressBar.setVisibility(8);
        } else if (showPodcasts) {
            com.amazon.podcast.nowplaying.MiniPlayerView miniPlayerView2 = new com.amazon.podcast.nowplaying.MiniPlayerView(this);
            this.podcastMiniPlayer = miniPlayerView2;
            this.podcastMiniPlayerLayout.addView(miniPlayerView2);
            this.podcastMiniPlayer.setVisibility(0);
            this.podcastMiniPlayerLayout.setVisibility(0);
            this.mMiniPlayerViewContainer.setVisibility(8);
            this.miniPlayerProgressBar.setVisibility(8);
        } else {
            com.amazon.podcast.nowplaying.MiniPlayerView miniPlayerView3 = this.podcastMiniPlayer;
            if (miniPlayerView3 != null) {
                miniPlayerView3.setVisibility(8);
                this.podcastMiniPlayerLayout.setVisibility(8);
            }
            this.mMiniPlayerViewContainer.setVisibility(0);
            this.miniPlayerProgressBar.setVisibility(0);
        }
        Trace.endSection();
    }

    private void handleDeeplinkPodcastIntent(final Intent intent, final Bundle extras) {
        String string = extras.getString("deeplinkUri", null);
        if (StringUtils.isBlank(string)) {
            return;
        }
        String string2 = extras.getString("podcastDeeplink", null);
        Log.debug(TAG, "podcast deeplink " + string);
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        Bootstrap bootstrap = getBootstrap(string);
        if (string2.equals(PodcastDeeplinks.DeeplinkType.LIBRARY.getValue())) {
            boolean zContains = string.contains("seeMore=true");
            boolean zIsPodcastsToggleSupported = MyLibraryPodcastsToggleHelper.INSTANCE.isPodcastsToggleSupported();
            LibraryPreferences libraryPreferences = LibraryPreferences.INSTANCE;
            Fragment myLibraryHomeFragment = getMyLibraryHomeFragment(intent);
            if (zContains) {
                navigateToPodcasts(bootstrap, string, zIsPodcastsToggleSupported);
                return;
            }
            libraryPreferences.writeIsLibraryMusicToggleSelected(this, false);
            this.mFragmentController.changeScreenFragment(myLibraryHomeFragment, true, true);
            if (zIsPodcastsToggleSupported) {
                return;
            }
            navigateToPodcasts(bootstrap, string, zIsPodcastsToggleSupported);
            return;
        }
        if (string2.equals(PodcastDeeplinks.DeeplinkType.PLAYBACK.getValue())) {
            if (fragments.size() == 0) {
                this.mFragmentController.changeScreenFragment(new BrushFragmentNavigation.Builder("find").withBundle(extras).createFragment(), true, true);
            }
            Podcast.create(bootstrap).startPlayback();
            return;
        }
        if (fragments.size() == 0) {
            this.mFragmentController.changeScreenFragment(new BrushFragmentNavigation.Builder("find").withBundle(extras).createFragment(), true, true);
        }
        Podcast.create(bootstrap).startBrowse();
    }

    private void navigateToPodcasts(final Bootstrap bootstrap, final String deeplinkPath, final boolean isPodcastToggleSupported) {
        Podcast.create(bootstrap).startLibrary(deeplinkPath, isPodcastToggleSupported);
    }

    public static Fragment getMyLibraryHomeFragment(final Intent intent) {
        LatencyTracker.INSTANCE.track(new LatencyEventDescriptor(LatencyTrackingLeg.INITIALIZING, PageLoadLatencyCode.LIBRARY));
        return LibraryFragment.INSTANCE.newInstance(intent);
    }

    public static String getMyLibraryHomeFragmentName() {
        return LibraryFragment.class.getSimpleName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleVideoDeeplinkIntent(final Intent intent) {
        final String stringExtra = intent.getStringExtra("com.amazon.mp3.util.videoDeeplink");
        if (stringExtra == null) {
            if (AmazonApplication.getCapabilities().isVideoLandingPageEnabled()) {
                navigateToIntent(intent, intent.getExtras());
                return;
            }
            return;
        }
        Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.activity.MusicHomeActivity$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                MusicHomeActivity.lambda$handleVideoDeeplinkIntent$4(stringExtra, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<PrimeTrack>() { // from class: com.amazon.mp3.activity.MusicHomeActivity.18
            @Override // rx.Observer
            public void onCompleted() {
            }

            @Override // rx.Observer
            public void onError(final Throwable throwable) {
                Log.error(MusicHomeActivity.TAG, "Received an Error when looking up Track ASIN:" + stringExtra + "Error: $throwable");
            }

            @Override // rx.Observer
            public void onNext(final PrimeTrack track) {
                VideoUtil.INSTANCE.playVideo(track, MusicHomeActivity.this.mDeeplinkExtras);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$handleVideoDeeplinkIntent$4(String str, Subscriber subscriber) {
        try {
            subscriber.onNext(VideoUtil.INSTANCE.fetchTrack(AmazonApplication.getApplication().getApplicationContext(), str));
            subscriber.onCompleted();
        } catch (Exception e) {
            subscriber.onError(e);
        }
    }

    private void handleVideoStoryDeeplinkIntent(final Intent intent) {
        String stringExtra = intent.getStringExtra("com.amazon.mp3.util.videoStoryDeeplink");
        VideoStoriesUtil.INSTANCE.playVideoStories(this, null, new ArrayList(Collections.singleton(stringExtra)), stringExtra, new ArrayList(Collections.singleton(intent.getStringExtra(DeeplinkIntentKey.KEY_REF_MARKER.toString()))), System.currentTimeMillis(), intent.getStringExtra(DeeplinkIntentKey.KEY_ASSOCIATE_TAG.toString()));
    }

    private Bootstrap getBootstrap(final String deeplinkPath) {
        return new Bootstrap(new PodcastDeeplink(deeplinkPath, false), getApplicationContext(), new UserInfo(), ActionBarInfo.INSTANCE, FragmentInfo.INSTANCE, new NetworkSettings(), new DownloadSettings(this), new PodcastSharingProvider(), new FeatureGateProviderImpl(), false, new PodcastDynamicMessaging(this), new CarModeUserPresetHandlerImpl(), new AdvertisingPreferencesProviderImpl());
    }

    private Bootstrap getOnRestoreMiniPlayerBootstrap() {
        return new Bootstrap(null, getApplicationContext(), new UserInfo(), ActionBarInfo.INSTANCE, FragmentInfo.INSTANCE, new NetworkSettings(), new DownloadSettings(this), new PodcastSharingProvider(), new FeatureGateProviderImpl(), true, new PodcastDynamicMessaging(this), new CarModeUserPresetHandlerImpl(), new AdvertisingPreferencesProviderImpl());
    }

    private void registerLocalBroadcastActivityReceiver(BroadcastReceiver receiver, IntentFilter intentFilter) {
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, intentFilter);
    }

    private void unregisterLocalBroadcastActivityReceiver(BroadcastReceiver receiver) {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

    private void isSpatialAudioLaunched() {
        ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.mp3.activity.MusicHomeActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$isSpatialAudioLaunched$5();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$isSpatialAudioLaunched$5() {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        try {
            if (ConnectivityUtil.hasAnyInternetConnection(this)) {
                boolean featureStatus = StratusFeature.IVY_SPATIAL_AUDIO.getFeatureStatus(this);
                defaultSharedPreferences.edit().putBoolean(getString(com.amazon.mp3.automotiveOS.R.string.dmusic_ivy_is_spatial_audio_launched_key), featureStatus).apply();
                if (featureStatus && !defaultSharedPreferences.contains(getString(com.amazon.mp3.automotiveOS.R.string.dmusic_ivy_inital_spatial_value_key))) {
                    defaultSharedPreferences.edit().putBoolean(getString(com.amazon.mp3.automotiveOS.R.string.dmusic_ivy_inital_spatial_value_key), featureStatus).apply();
                    SettingsUtil.setSpatialMode(this, featureStatus);
                }
                PlaybackInitializationHelper.updateSpatialMode(this);
            }
        } catch (Exception e) {
            Log.error(TAG, "Exception from Stratus isFeatureLaunched:", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean inImmersiveMode() {
        return ActivityKt.inImmersiveMode(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean canShowSkipTooltip() {
        SkipLimitTooltipPresenter skipLimitTooltipPresenter = this.skipLimitTooltipPresenter;
        return (skipLimitTooltipPresenter == null || !skipLimitTooltipPresenter.isConfigured() || this.mStyleSheet == null || this.mMiniPlayerView == null) ? false : true;
    }

    public HashMap<String, String> getHotSpotExperienceVault() {
        return this.hotSpotExperienceVault;
    }

    private boolean isPodcastPlaying() {
        return this.mPlaybackController.getCurrentMediaItem() != null && this.mPlaybackController.getCurrentMediaItem().getType() == MediaItem.Type.PODCAST_CONTENT;
    }

    public boolean isDialogOverlayActive() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        if (supportFragmentManager != null) {
            return (supportFragmentManager.findFragmentByTag("AppRatingPromptDialog") == null && supportFragmentManager.findFragmentByTag(AutomaticImportFtuDialogFragment.class.getSimpleName()) == null) ? false : true;
        }
        return false;
    }
}
