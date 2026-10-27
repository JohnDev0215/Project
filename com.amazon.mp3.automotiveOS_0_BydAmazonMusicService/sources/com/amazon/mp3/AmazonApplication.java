package com.amazon.mp3;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.database.sqlite.SQLiteDatabase;
import android.os.Debug;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.PowerManager;
import android.os.Process;
import android.os.SystemClock;
import android.os.Trace;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.ProcessLifecycleOwner;
import com.amazon.client.metrics.common.AndroidMetricsFactoryImpl;
import com.amazon.device.crashmanager.CrashDetectionHelper;
import com.amazon.device.crashmanager.Domain;
import com.amazon.device.crashmanager.DomainChooser;
import com.amazon.hermes.Hermes;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.account.AccountManagerFactory;
import com.amazon.mp3.account.AccountRequestUtil;
import com.amazon.mp3.account.activity.AccountMigratedDialogActivity;
import com.amazon.mp3.account.activity.AccountWebViewActivity;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.activity.BootstrapSingletonTask;
import com.amazon.mp3.activity.ReportCrashDialogActivity;
import com.amazon.mp3.activity.UpdateCheckActivity;
import com.amazon.mp3.activity.settings.LastViewedScreenUtil;
import com.amazon.mp3.activity.settings.SettingsActivity;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.auto.GoogleAssistantSearchHandler;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.billing.BillingClientManager;
import com.amazon.mp3.capability.Capabilities;
import com.amazon.mp3.capability.DevModeCapabilities;
import com.amazon.mp3.capability.FeatureFlagsProvider;
import com.amazon.mp3.client.activity.LauncherActivity;
import com.amazon.mp3.config.ApplicationAttributes;
import com.amazon.mp3.config.RemoteConfigAttributes;
import com.amazon.mp3.config.RemoteConfigLoader;
import com.amazon.mp3.dagger.ApplicationComponent;
import com.amazon.mp3.dagger.DaggerApplicationComponent;
import com.amazon.mp3.det.PendingCrashLogs;
import com.amazon.mp3.det.report.ReportType;
import com.amazon.mp3.det.report.UnexpectedEventReport;
import com.amazon.mp3.download.redownload.ReDownloadOfflineMusicService;
import com.amazon.mp3.elf.ExplicitLanguageManager;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.ftu.FTUSettingsUtil;
import com.amazon.mp3.init.InitializationHandler;
import com.amazon.mp3.init.SystemConfigInitializationTask;
import com.amazon.mp3.language.preference.util.LocaleManager;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory;
import com.amazon.mp3.library.item.factory.LibraryItemFactory;
import com.amazon.mp3.library.mylibrary.likesandfollows.LikesAndFollowsWorkManagerInitializer;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.provider.source.nowplaying.GhostListeningSubscriber;
import com.amazon.mp3.library.provider.source.nowplaying.NowPlayingQueueHelper;
import com.amazon.mp3.library.provider.source.nowplaying.NowPlayingQueueUtil;
import com.amazon.mp3.library.provider.source.nowplaying.PlayQueue;
import com.amazon.mp3.library.service.sync.SyncService;
import com.amazon.mp3.navigation.Deeplinks;
import com.amazon.mp3.net.NetConnectStrategy;
import com.amazon.mp3.net.cirrus.BlueMoonExceptionsUtil;
import com.amazon.mp3.ondemandplaylists.api.GetOnDemandPlaylistsService;
import com.amazon.mp3.parentalmonitoring.PMPlaybackListener;
import com.amazon.mp3.platform.locator.PlatformProvidersUtil;
import com.amazon.mp3.platform.providers.AppConfigProvider;
import com.amazon.mp3.playback.harley.PlaybackInitializationHelper;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.playback.service.streaming.StreamingBlueMoonErrorHandler;
import com.amazon.mp3.push.PushInitializer;
import com.amazon.mp3.reactnative.PrimaryReactInitializer;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedDatabase;
import com.amazon.mp3.recentlyplayed.RecentsPlaybackListener;
import com.amazon.mp3.service.ClearCacheService;
import com.amazon.mp3.service.ComponentCheckService;
import com.amazon.mp3.service.error.CirrusError;
import com.amazon.mp3.service.error.CirrusErrorLogger;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.service.metrics.minerva.MinervaMetricsFactory;
import com.amazon.mp3.service.metrics.mts.AnonymousMusicUploadFactory;
import com.amazon.mp3.service.metrics.mts.MTSCounterMetadataProviderImpl;
import com.amazon.mp3.service.metrics.mts.MTSMinervaMetricsProviderImpl;
import com.amazon.mp3.service.metrics.mts.MusicEventDataProvider;
import com.amazon.mp3.service.metrics.mts.MusicUploadFactory;
import com.amazon.mp3.stability.StabilityEventHandler;
import com.amazon.mp3.stability.StabilityManager;
import com.amazon.mp3.storage.ExternalStorageListener;
import com.amazon.mp3.task.DefaultExceptionHandlingStrategy;
import com.amazon.mp3.task.DefaultRetryStrategy;
import com.amazon.mp3.task.ExecutionController;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.ExitCheck;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.LogFiles;
import com.amazon.mp3.util.PlatformUtil;
import com.amazon.mp3.util.ScreenWakeLock;
import com.amazon.mp3.util.StorageInfo;
import com.amazon.mp3.util.StringUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.util.health.AppHealthUtil;
import com.amazon.mp3.watch.WatchSessionService;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.AccountStateChangeListener;
import com.amazon.music.account.AccountStateField;
import com.amazon.music.authentication.AuthInfo;
import com.amazon.music.authentication.mapr5.AuthInfoMAPR5;
import com.amazon.music.config.ConfigurationFactory;
import com.amazon.music.deeplink.DeeplinksManager;
import com.amazon.music.explore.picasso.VideoRequestHandler;
import com.amazon.music.featureflag.FeatureFlagUnavailableException;
import com.amazon.music.find.featuregating.FindFeatureGating;
import com.amazon.music.logger.AmazonMusicMarketLogger;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.MetricsManager;
import com.amazon.music.metrics.mts.AnonymousRealTimeMTSEventHandler;
import com.amazon.music.metrics.mts.MTSEventHandler;
import com.amazon.music.metrics.mts.MTSFileConfig;
import com.amazon.music.metrics.mts.NoOpErrorReporter;
import com.amazon.music.metrics.mts.RealTimeMTSEventHandler;
import com.amazon.music.metrics.mts.android.MetricsFilePathUtil;
import com.amazon.music.metrics.mts.android.StorageNotAvailableException;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyTracker;
import com.amazon.music.okhttp3.AMOkHttpClient;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.amazon.music.playback.subscriber.HLSRequiredRebufferingEventSubscriber;
import com.amazon.music.playback.subscriber.HLSTrackSegmentCachedEventSubscriber;
import com.amazon.music.playback.subscriber.LatencyEventSubscriber;
import com.amazon.music.playback.subscriber.ProgressiveDownloadEventSubscriber;
import com.amazon.music.playback.subscriber.RequiredRebufferingEventSubscriber;
import com.amazon.music.playback.subscriber.StreamingTerminatedEventSubscriber;
import com.amazon.music.playback.subscriber.SubscriberExceptionEventSubscriber;
import com.amazon.music.subscription.UserSubscription;
import com.amazon.net.okhttp3.UnifiedOkHttpClient;
import com.amazonaws.auth.AWS4Signer;
import com.amazonaws.logging.LogFactory;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.modules.network.OkHttpClientFactory;
import com.facebook.react.modules.network.OkHttpClientProvider;
import com.facebook.react.modules.network.ReactCookieJarContainer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.perf.FirebasePerformance;
import com.google.firebase.perf.network.FirebasePerfOkHttpClient;
import com.shopify.reactnativeperformance.ReactNativePerformance;
import com.squareup.picasso.Downloader;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;
import com.visualon.OSMPUtils.voOSType;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;
import io.branch.referral.Branch;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.functions.Function0;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.greenrobot.eventbus.EventBus;
import rx.functions.Action1;

/* JADX INFO: loaded from: classes3.dex */
public class AmazonApplication extends Application implements HasAndroidInjector, ReactApplication {
    private static final long CLIENT_INFO_EVENT_DELAY_MS;
    private static final long DEFAULT_REACT_NATIVE_PREWARMING_DELAY_MS;
    private static final long DEVICE_INFO_EVENT_DELAY_BACKGROUND_MS;
    private static final long DEVICE_INFO_EVENT_DELAY_FOREGROUND_MS;
    private static final long STABILITY_MANAGER_RESYNC_DELAY_BACKGROUND_MS;
    private static final long STARTUP_TRACING_DELAY_WAIT_MS;
    private static Locale deviceLocale;
    private static final Runnable mDeviceInfoMetricRunnable;
    private static final ObjectMapper mObjectMapper;
    private static AmazonApplication sApplication;
    private static AuthInfoMAPR5 sAuthInfoMAPR5;
    private static Handler sBackgroundHandler;
    private static Capabilities sCapabilities;
    private static ClientInfoMetricsHelper sClientInfoMetricsHelper;
    private static Context sContext;
    private static final DevModeCapabilities sDevModeCapabilities;
    private static boolean sDisplayedLowMemoryWarning;
    private static String sExternalStorageAppDirectory;
    private static final ApplicationForegroundStateMonitor sForegroundStateMonitor;
    private static boolean sIsExiting;
    private static LibraryItemFactory sLibItemFactory;
    private static Locale sLocale;
    private static Handler sMainThreadHandler;
    private static ExecutionController sNetExecutionController;
    private static boolean sPaused;
    private static boolean sPlaybackUnderrunWhileIdle;
    private static long sTimeLastDeviceInfoMetricEvent;
    private static final StabilityManager stabilityManager;
    private LifecycleObserver likesAndFollowsObserver;
    public DispatchingAndroidInjector<Object> mAndroidInjector;
    private ApplicationComponent mApplicationComponent;
    private PushInitializer mPushInitializer;
    private PMPlaybackListener pmPlaybackListener;
    private RecentsPlaybackListener recentsPlaybackListener;
    private static final String TAG = AmazonApplication.class.getSimpleName();
    public static final boolean BETA = false;
    private final TrackSyncRunnable mTrackSync = new TrackSyncRunnable();
    private boolean mPendingTrackSyncOperations = false;
    private long mTimeSinceLastTrackSync = 0;
    private final PlaybackController playbackController = PlaybackControllerProvider.getController(ControlSource.NONE);
    private final ReactNativeHost mReactNativeHost = new PrimaryReactInitializer(this);
    private final BroadcastReceiver mIdleModeReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.AmazonApplication.15
        @Override // android.content.BroadcastReceiver
        @TargetApi(23)
        public void onReceive(final Context context, Intent intent) {
            if (PlatformUtil.isVersionOrGreater(23)) {
                PowerManager powerManager = (PowerManager) context.getSystemService("power");
                boolean zIsDeviceIdleMode = powerManager != null ? powerManager.isDeviceIdleMode() : false;
                Log.info(AmazonApplication.TAG, "Device Idle mode changed to " + zIsDeviceIdleMode);
                if (zIsDeviceIdleMode || !AmazonApplication.sPlaybackUnderrunWhileIdle) {
                    return;
                }
                Log.info(AmazonApplication.TAG, "Sending flex event for playbackUnderrunWhileIdle");
                MetricsLogger.sendEvent(FlexEvent.builder("playbackUnderrunWhileIdle").build());
                boolean unused = AmazonApplication.sPlaybackUnderrunWhileIdle = false;
            }
        }
    };
    private final BroadcastReceiver mRemoteConfigLoadedReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.AmazonApplication.16
        @Override // android.content.BroadcastReceiver
        public void onReceive(final Context context, Intent intent) {
            UpdateCheckActivity.refreshUpdateInformation(context);
        }
    };
    private final BroadcastReceiver mStreamingBlueMoonExceptionReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.AmazonApplication.17
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (AmazonApplication.getApplication().getPauseState()) {
                StreamingBlueMoonErrorHandler.showNotification(context, intent);
            } else {
                StreamingBlueMoonErrorHandler.startIfTriggered(context);
            }
        }
    };
    private final BroadcastReceiver mBroadcastDisplayBlueMoonDialogReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.AmazonApplication.18
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            BlueMoonExceptionsUtil.processBroadcastDisplayDialogEvent();
        }
    };
    private final BroadcastReceiver mWidevineAutomigrationReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.AmazonApplication.19
        @Override // android.content.BroadcastReceiver
        public void onReceive(final Context context, final Intent intent) {
            if (intent.getIntExtra("com.amazon.mp3.library.service.sync.EXTRA_SYNC_EVENT_TYPE", 0) == 5) {
                if (!SettingsUtil.getBooleanPref(context, R.string.setting_key_redownload_started)) {
                    SettingsUtil.setBooleanPref(context, R.string.setting_key_redownload_ribbon_dismissed, true);
                    ReDownloadOfflineMusicService.INSTANCE.queueOrStart(false);
                }
                AmazonApplication.getContext().unregisterReceiver(this);
            }
        }
    };
    private final AccountStateChangeListener mAccountStateChangeListener = new AccountStateChangeListener() { // from class: com.amazon.mp3.AmazonApplication.20
        @Override // com.amazon.music.account.AccountStateChangeListener
        public void onAccountStateChange(final Set<AccountStateField> set) {
            clearPlaymodeEligibilityOnBenefitsChange(set);
            forceFullSyncOnHawkfireLaunch(set);
            clearSplashScreenCheckpointOnUnlimitedLaunch(set);
            if (hasAccountBecomeValid(set)) {
                if (!AccountCredentialStorage.get(AmazonApplication.this).shouldPerformSignInTasks()) {
                    AccountCredentialStorage.get(AmazonApplication.this).setShouldPerformSignInTasks(true);
                    startIntentWhenAppIsInForeground(new Intent(AmazonApplication.this, (Class<?>) LauncherActivity.class), LauncherActivity.class.getSimpleName());
                }
            } else if (hasAccountBecomeInvalid(set)) {
                startIntentWhenAppIsInForeground(AccountWebViewActivity.getIntent(AmazonApplication.this), AccountWebViewActivity.class.getSimpleName());
            } else if (hasAccountMigrated(set)) {
                startIntentWhenAppIsInForeground(AccountMigratedDialogActivity.getStartIntent(AmazonApplication.this.getApplicationContext()), AccountWebViewActivity.class.getSimpleName());
            }
            updateGetOnDemandPlaylistsServiceCache(set);
            logAccountStateChange(set);
        }

        private void updateGetOnDemandPlaylistsServiceCache(final Set<AccountStateField> accountStateFieldsSet) {
            GetOnDemandPlaylistsService getOnDemandPlaylistsService = GetOnDemandPlaylistsService.INSTANCE.get();
            if (accountStateFieldsSet.contains(AccountStateField.USER_BENEFITS)) {
                getOnDemandPlaylistsService.clearOnDemandPlaylists();
                getOnDemandPlaylistsService.updateEligibilityTier();
            } else if (accountStateFieldsSet.contains(AccountStateField.USER_MUSIC_TERRITORY_OF_RESIDENCE)) {
                getOnDemandPlaylistsService.clearOnDemandPlaylists();
            }
            getOnDemandPlaylistsService.fetchAndCacheOnDemandPlaylists();
        }

        private void startIntentWhenAppIsInForeground(final Intent intent, final String className) {
            AmazonApplication.subscribeForegroundObserver(new Action1<Void>() { // from class: com.amazon.mp3.AmazonApplication.20.1
                @Override // rx.functions.Action1
                public void call(Void aVoid) {
                    AmazonApplication.this.startActivity(intent);
                }
            }, new Action1<Throwable>() { // from class: com.amazon.mp3.AmazonApplication.20.2
                @Override // rx.functions.Action1
                public void call(Throwable throwable) {
                    Log.error(AmazonApplication.TAG, "Unable to launch " + className + " onAccountStateChange event:" + throwable);
                }
            });
        }

        private boolean hasAccountBecomeValid(final Set<AccountStateField> set) {
            if (!set.contains(AccountStateField.IS_ACCOUNT_VALID)) {
                return false;
            }
            boolean zIsAccountValid = AccountRequestUtil.isAccountValid(AmazonApplication.this);
            Log.info(AmazonApplication.TAG, "User's account state has changed!  Current IS_ACCOUNT_VALID: " + zIsAccountValid);
            return zIsAccountValid;
        }

        private boolean hasAccountBecomeInvalid(final Set<AccountStateField> set) {
            if (!set.contains(AccountStateField.IS_ACCOUNT_VALID)) {
                return false;
            }
            boolean zIsAccountValid = AccountRequestUtil.isAccountValid(AmazonApplication.this);
            Log.info(AmazonApplication.TAG, "User's account state has changed!  Current IS_ACCOUNT_VALID: " + zIsAccountValid);
            return !zIsAccountValid;
        }

        private boolean hasAccountMigrated(final Set<AccountStateField> set) {
            if (!set.contains(AccountStateField.ACCOUNT_MIGRATED)) {
                return false;
            }
            Log.info(AmazonApplication.TAG, "User's Music Territory of Residence changed!");
            return true;
        }

        private void forceFullSyncOnHawkfireLaunch(final Set<AccountStateField> set) {
            if (set.contains(AccountStateField.USER_IS_IN_HAWKFIRE_MARKETPLACE) && AccountDetailUtil.get(AmazonApplication.this.getApplicationContext()).isUserInHawkfireMarketplace()) {
                CirrusDatabase.resetForFullResync(AmazonApplication.this);
                SyncService.startSync(AmazonApplication.this, 13955);
            }
        }

        private void clearPlaymodeEligibilityOnBenefitsChange(final Set<AccountStateField> set) {
            if (set.contains(AccountStateField.USER_BENEFITS)) {
                new Thread("Clear Cirrus DB") { // from class: com.amazon.mp3.AmazonApplication.20.3
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        Log.info(AmazonApplication.TAG, "clearPlaymodeEligibilityOnBenefitsChange:  Starting clear cache job (on the current thread).");
                        ClearCacheService.clearCacheOnCurrentThread(AmazonApplication.this, 65536);
                    }
                }.start();
            }
        }

        private void clearSplashScreenCheckpointOnUnlimitedLaunch(final Set<AccountStateField> set) {
            if (set.contains(AccountStateField.USER_IS_IN_HAWKFIRE_MARKETPLACE) && AccountDetailUtil.get(AmazonApplication.this.getApplicationContext()).isUserInHawkfireMarketplace()) {
                FTUSettingsUtil.resetFTUShown(AmazonApplication.this);
            }
        }

        private void logAccountStateChange(final Set<AccountStateField> set) {
            if (set.contains(AccountStateField.USER_BENEFITS)) {
                UserSubscription userSubscription = UserSubscriptionUtil.getUserSubscription();
                Log.info(AmazonApplication.TAG, "User's account state has changed!  Current status: , isPrime: " + userSubscription.isPrime() + " , isSonicRush: " + userSubscription.isSonicRush() + " , isUnlimitedAllDevices: " + userSubscription.isHawkfireAllDevices() + " , isUnlimitedHomeOnly: " + userSubscription.isHawkfireHome());
            }
        }
    };

    public static String getExternalStorageTempDirectory() {
        return "temp";
    }

    static {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        STARTUP_TRACING_DELAY_WAIT_MS = timeUnit.toMillis(10L);
        CLIENT_INFO_EVENT_DELAY_MS = timeUnit.toMillis(50L);
        DEVICE_INFO_EVENT_DELAY_FOREGROUND_MS = TimeUnit.MINUTES.toMillis(30L);
        DEVICE_INFO_EVENT_DELAY_BACKGROUND_MS = TimeUnit.HOURS.toMillis(4L);
        STABILITY_MANAGER_RESYNC_DELAY_BACKGROUND_MS = timeUnit.toMillis(60L);
        DEFAULT_REACT_NATIVE_PREWARMING_DELAY_MS = timeUnit.toMillis(4L);
        sDevModeCapabilities = new DevModeCapabilities();
        sForegroundStateMonitor = new ApplicationForegroundStateMonitor();
        mObjectMapper = new ObjectMapper();
        sTimeLastDeviceInfoMetricEvent = 0L;
        sIsExiting = false;
        sPlaybackUnderrunWhileIdle = false;
        sDisplayedLowMemoryWarning = false;
        stabilityManager = StabilityManager.INSTANCE.getInstance();
        mDeviceInfoMetricRunnable = new Runnable() { // from class: com.amazon.mp3.AmazonApplication.13
            @Override // java.lang.Runnable
            public void run() {
                MetricsLogger.deviceInfo(AmazonApplication.sContext);
                if (AmazonApplication.isApplicationForeground()) {
                    AmazonApplication.sBackgroundHandler.postDelayed(this, AmazonApplication.DEVICE_INFO_EVENT_DELAY_FOREGROUND_MS);
                } else {
                    AmazonApplication.sBackgroundHandler.postDelayed(this, AmazonApplication.DEVICE_INFO_EVENT_DELAY_BACKGROUND_MS);
                }
                long unused = AmazonApplication.sTimeLastDeviceInfoMetricEvent = SystemClock.elapsedRealtime();
            }
        };
        sPaused = false;
    }

    public static ObjectMapper getObjectMapper() {
        return mObjectMapper;
    }

    public ApplicationComponent getApplicationComponent() {
        return this.mApplicationComponent;
    }

    public static void setDisplyedLowMemoryWarning(boolean displayed) {
        sDisplayedLowMemoryWarning = displayed;
    }

    public static boolean hasDisplayedLowMemoryWarning() {
        return sDisplayedLowMemoryWarning;
    }

    public static synchronized ExecutionController getNetExecutionController() {
        if (sNetExecutionController == null) {
            sNetExecutionController = new ExecutionController(new DefaultRetryStrategy(), new DefaultExceptionHandlingStrategy(), new NetConnectStrategy());
        }
        return sNetExecutionController;
    }

    private static synchronized void createHandlers() {
        if (sMainThreadHandler == null) {
            sMainThreadHandler = new Handler(Looper.getMainLooper());
        }
        if (sBackgroundHandler == null) {
            HandlerThread handlerThread = new HandlerThread("BackgroundHandlerThread", 10);
            handlerThread.start();
            sBackgroundHandler = new Handler(handlerThread.getLooper());
        }
    }

    @Deprecated
    static synchronized void setContext(final Context context) {
        sContext = context;
    }

    private static synchronized void setApplication(final AmazonApplication application) {
        sApplication = application;
    }

    private static void setExternalStorageAppDirectory(String dir) {
        sExternalStorageAppDirectory = dir;
    }

    public static String getExternalStorageAppDirectory() {
        return sExternalStorageAppDirectory;
    }

    public static boolean isApplicationForeground() {
        return sForegroundStateMonitor.isApplicationForeground();
    }

    public static boolean isApplicationBackground() {
        return !isApplicationForeground();
    }

    @Override // dagger.android.HasAndroidInjector
    public AndroidInjector<Object> androidInjector() {
        return this.mAndroidInjector;
    }

    @Override // com.facebook.react.ReactApplication
    public ReactNativeHost getReactNativeHost() {
        return this.mReactNativeHost;
    }

    private static class ApplicationContextWrapper extends ContextWrapper {
        private final Context mContext;

        public ApplicationContextWrapper(final Context context) {
            super(context);
            this.mContext = context;
        }

        @Override // android.content.ContextWrapper, android.content.Context
        public Context getApplicationContext() {
            Context applicationContext = super.getApplicationContext();
            return applicationContext == null ? this.mContext : applicationContext;
        }

        @Override // android.content.ContextWrapper
        public Context getBaseContext() {
            Context baseContext = super.getBaseContext();
            return baseContext == null ? this.mContext : baseContext;
        }
    }

    @Override // android.content.ContextWrapper
    protected void attachBaseContext(final Context base) {
        super.attachBaseContext(base);
        setContext(base);
        setExternalStorageAppDirectory(getCapabilities().getStorageDir());
        ConnectivityUtil.initialize(new ApplicationContextWrapper(base));
    }

    @Override // android.app.Application
    public void onCreate() {
        Trace.beginSection("AMA.AmazonApplication.onCreate");
        Trace.beginSection("AMA.onAppStarted.onCreate.ReactNativePerformance.onAppStarted");
        ReactNativePerformance.onAppStarted();
        Trace.endSection();
        super.onCreate();
        initializeLogger(this, BETA);
        Context applicationContext = getApplicationContext();
        setContext(applicationContext);
        setApplication(this);
        sLibItemFactory = new CirrusSourceLibraryItemFactory(this);
        createHandlers();
        initializeStabilityManager();
        BootstrapSingletonTask.initialize(this);
        initializeUnifiedOkHttpClient(applicationContext);
        initializeReactNativeNetworking();
        initializeHermes(applicationContext);
        initializeSystemConfig();
        initializePicasso(applicationContext);
        if (getCapabilities().isCrashHandlingEnabled(applicationContext)) {
            setDefaultExceptionHandler();
        }
        setRxJavaErrorHandlers();
        initializeAccountManager(applicationContext);
        initializeConfigurationProvider(applicationContext);
        BootstrapSingletonTask.get().execute();
        initializeCrashDetectionHelper(applicationContext);
        initChildUserAccount(applicationContext);
        initPlayerComponent(applicationContext);
        registerReceivers();
        initPlayQueue(applicationContext);
        setDeviceLocale();
        cacheInitialLocaleInformation();
        ComponentCheckService.startOnApplicationStarted(applicationContext);
        new Thread() { // from class: com.amazon.mp3.AmazonApplication.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                CacheManager.getInstance();
            }
        }.start();
        initializeMetrics(applicationContext);
        registerEventBusSubscribers();
        Branch.disableDeviceIDFetch(Boolean.TRUE);
        Branch.getAutoInstance(this);
        registerDeeplinks(applicationContext);
        subscribeBootstrapTask();
        initLazyLoadTasks(applicationContext);
        sendBroadcast(new Intent("com.amazon.mp3.app.foreground.start"));
        PlatformProvidersUtil.init(this);
        PushInitializer pushInitializer = new PushInitializer(applicationContext);
        this.mPushInitializer = pushInitializer;
        pushInitializer.initializePush();
        ApplicationComponent applicationComponentBuild = DaggerApplicationComponent.builder().application(this).build();
        this.mApplicationComponent = applicationComponentBuild;
        applicationComponentBuild.inject(this);
        initializeWatchSession();
        Trace.endSection();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupFirebasePerformance() {
        FirebasePerformance.getInstance().setPerformanceCollectionEnabled(Feature.firebase_performance_monitoring.isEnabled());
    }

    private void initializeWatchSession() {
        Trace.beginSection("AMA.AmazonApplication.initializeWatchSession");
        sBackgroundHandler.post(new Runnable() { // from class: com.amazon.mp3.AmazonApplication.2
            @Override // java.lang.Runnable
            public void run() {
                if (WatchSessionService.INSTANCE.isBound()) {
                    return;
                }
                Context applicationContext = AmazonApplication.this.getApplicationContext();
                applicationContext.bindService(new Intent(applicationContext, (Class<?>) WatchSessionService.class), new ServiceConnection() { // from class: com.amazon.mp3.AmazonApplication.2.1
                    @Override // android.content.ServiceConnection
                    public void onServiceConnected(ComponentName name, IBinder service) {
                    }

                    @Override // android.content.ServiceConnection
                    public void onServiceDisconnected(ComponentName name) {
                    }
                }, 1);
            }
        });
        Trace.endSection();
    }

    private void initializeStabilityManager() {
        Trace.beginSection("AMA.AmazonApplication.initializeStabilityManager");
        stabilityManager.init(this);
        sBackgroundHandler.postDelayed(new Runnable() { // from class: com.amazon.mp3.AmazonApplication$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$initializeStabilityManager$0();
            }
        }, STABILITY_MANAGER_RESYNC_DELAY_BACKGROUND_MS);
        Trace.endSection();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initializeStabilityManager$0() {
        stabilityManager.resyncBugsnag(getApplicationContext());
    }

    private void setDeviceLocale() {
        synchronized (AmazonApplication.class) {
            if (deviceLocale == null) {
                Locale locale = getResources().getConfiguration().locale;
                deviceLocale = locale;
                Log.debug(TAG, "Device Locale of app %s", locale.toString());
            }
        }
    }

    public static Locale getDeviceLocale() {
        return deviceLocale;
    }

    private void initLazyLoadTasks(final Context context) {
        new Thread(new Runnable() { // from class: com.amazon.mp3.AmazonApplication.3
            @Override // java.lang.Runnable
            public void run() {
                ExternalStorageListener externalStorageListener = new ExternalStorageListener();
                if (AmazonApplication.getCapabilities().secondaryStoragePresent(context)) {
                    externalStorageListener.onAppLaunchWithAvailableSDCard(context);
                } else {
                    externalStorageListener.onAppLaunchWithNoAvailableSDCard(context);
                }
            }
        }).start();
    }

    private void subscribeBootstrapTask() {
        Trace.beginSection("AMA.AmazonApplication.subscribeBootstrapTask");
        final Context applicationContext = getApplicationContext();
        BootstrapSingletonTask.get().registerUserObserver().subscribe(new Action1() { // from class: com.amazon.mp3.AmazonApplication.4
            @Override // rx.functions.Action1
            public void call(final Object o) {
                AmazonApplication.getBackgroundHandler().post(new Runnable() { // from class: com.amazon.mp3.AmazonApplication.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Context context = applicationContext;
                        ConfigurationFactory.notifyUserInfoReady(context, RemoteConfigLoader.getInstance(context), RemoteConfigAttributes.getInstance(applicationContext));
                    }
                });
            }
        }, new Action1<Throwable>() { // from class: com.amazon.mp3.AmazonApplication.5
            @Override // rx.functions.Action1
            public void call(final Throwable throwable) {
                Log.warning(AmazonApplication.TAG, "BootstrapSingletonTask error: " + throwable.getMessage(), throwable);
            }
        });
        BootstrapSingletonTask.get().registerUserObserver().subscribe(new Action1() { // from class: com.amazon.mp3.AmazonApplication.6
            @Override // rx.functions.Action1
            public void call(final Object o) {
                AmazonApplication.getBackgroundHandler().post(new Runnable() { // from class: com.amazon.mp3.AmazonApplication.6.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Context context = applicationContext;
                        ConfigurationFactory.notifyWeblabControllerInitComplete(context, RemoteConfigLoader.getInstance(context), RemoteConfigAttributes.getInstance(applicationContext));
                        if (AmazonApplication.getCapabilities().shouldUpsellWithGoogleInAppBilling()) {
                            BillingClientManager.getInstance(AmazonApplication.sContext).onCreate();
                        }
                    }
                });
                AmazonApplication.this.registerDeviceChargingReceiver(applicationContext);
                AmazonApplication.changeLocaleIfNeeded(applicationContext);
                AmazonApplication.this.setupFirebasePerformance();
                AmazonApplication.this.setupReactNativePrewarming();
            }
        }, new Action1<Throwable>() { // from class: com.amazon.mp3.AmazonApplication.7
            @Override // rx.functions.Action1
            public void call(final Throwable throwable) {
                Log.warning(AmazonApplication.TAG, "BootstrapSingletonTask error: " + throwable.getMessage(), throwable);
            }
        });
        getBackgroundHandler().post(new Runnable() { // from class: com.amazon.mp3.AmazonApplication$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$subscribeBootstrapTask$3();
            }
        });
        Trace.endSection();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$subscribeBootstrapTask$1() {
        return Boolean.valueOf(ConnectivityUtil.isWifiConnected(getApplicationContext()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$subscribeBootstrapTask$3() {
        try {
            LatencyTracker latencyTracker = LatencyTracker.INSTANCE;
            latencyTracker.registerIsWifiAvailableCheck(new Function0() { // from class: com.amazon.mp3.AmazonApplication$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return this.f$0.lambda$subscribeBootstrapTask$1();
                }
            });
            latencyTracker.registerIsNetworkAvailableCheck(new Function0() { // from class: com.amazon.mp3.AmazonApplication$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return this.f$0.lambda$subscribeBootstrapTask$2();
                }
            });
            latencyTracker.setPageLatencySamplingRate(BETA ? 100 : FeatureFlagsProvider.getFeatureFlagProvider().getPageLatencySamplingRate());
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "FeatureFlagUnavailableException, falling back to default sampling rate " + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$subscribeBootstrapTask$2() {
        return Boolean.valueOf(ConnectivityUtil.isAvailable(getApplicationContext()));
    }

    private void initializeSystemConfig() {
        Trace.beginSection("AMA.AmazonApplication.initializeSystemConfig");
        new InitializationHandler(new SystemConfigInitializationTask(getApplicationContext())).initializeOnNewThread();
        Trace.endSection();
    }

    private void initChildUserAccount(Context context) {
        Trace.beginSection("AMA.AmazonApplication.initChildUserAccount");
        if (ChildUserUtil.INSTANCE.isChildUser(context) && ConnectivityUtil.hasAnyInternetConnection(context) && !getCapabilities().isExplicitLanguageFilterEnabled()) {
            new ExplicitLanguageManager(context).setExplicitFilterSetting(true, null);
        }
        Trace.endSection();
    }

    private void registerDeeplinks(final Context context) {
        Trace.beginSection("AMA.AmazonApplication.registerDeeplinks");
        new Deeplinks(DeeplinksManager.get(this), context).initialize();
        Trace.endSection();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isSignedIn() {
        return AccountCredentialUtil.get(this).isSignedIn();
    }

    private void initPlayerComponent(final Context context) {
        Trace.beginSection("AMA.AmazonApplication.initPlayerComponent");
        new PlaybackInitializationHelper(context).init();
        Playback.getInstance().getPlaybackConfig().setOnMediaSearchHandler(new GoogleAssistantSearchHandler(null, context, Playback.getInstance().getMediaSession()));
        Trace.endSection();
    }

    private void registerEventBusSubscribers() {
        Trace.beginSection("AMA.AmazonApplication.registerEventBusSubscribers");
        EventBus eventBus = EventBus.getDefault();
        MetricsManager manager = MetricsHolder.getManager();
        eventBus.register(new ProgressiveDownloadEventSubscriber());
        eventBus.register(new HLSTrackSegmentCachedEventSubscriber());
        eventBus.register(new StreamingTerminatedEventSubscriber(manager));
        eventBus.register(new RequiredRebufferingEventSubscriber(manager));
        eventBus.register(new HLSRequiredRebufferingEventSubscriber(manager));
        eventBus.register(new SubscriberExceptionEventSubscriber());
        eventBus.register(new LatencyEventSubscriber(manager));
        eventBus.register(new GhostListeningSubscriber());
        Trace.endSection();
    }

    private void minervaShutdown() {
        getBackgroundHandler().post(new Runnable() { // from class: com.amazon.mp3.AmazonApplication$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                AmazonApplication.lambda$minervaShutdown$4();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$minervaShutdown$4() {
        try {
            MinervaMetricsFactory.shutdown();
        } catch (Exception e) {
            Log.error(TAG, "Exception shutting down minerva client", e);
        }
    }

    private void shutdownServices() {
        minervaShutdown();
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        changeLocaleIfNeeded(getBaseContext());
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onLowMemory() {
        Log.warning(TAG, "Handling low memory, clearing cache to free up memory");
        super.onLowMemory();
        CacheManager.getInstance().clearImages();
        StringUtil.clearFormattedTimeCache();
        StringUtil.clearNormalizeCache();
        sendBroadcast(new Intent("com.amazon.mp3.action.LOW_MEMORY"));
    }

    @Override // android.app.Application, android.content.ComponentCallbacks2
    public void onTrimMemory(final int level) {
        String str = TAG;
        Log.info(str, "onTrimMemory triggered by OS");
        super.onTrimMemory(level);
        if (level == 5) {
            Log.warning(str, "Memory level: TRIM_MEMORY_RUNNING_MODERATE, app is not an expendable background process but device is running moderately low on memory");
        } else if (level == 10) {
            Log.warning(str, "Memory level: TRIM_MEMORY_RUNNING_LOW, app is not an expendable background process but device is running low on memory");
        } else if (level == 15) {
            Log.warning(str, "Memory level: TRIM_MEMORY_RUNNING_CRITICAL, app is not an expendable background process but device is running extremely low on memory");
        } else if (level == 20) {
            Log.info(str, "Memory level: TRIM_MEMORY_UI_HIDDEN, app no longer showing an UI, reschedule the deviceInfo metric event with the background interval");
            Handler handler = sBackgroundHandler;
            if (handler != null) {
                Runnable runnable = mDeviceInfoMetricRunnable;
                handler.removeCallbacks(runnable);
                sBackgroundHandler.postDelayed(runnable, DEVICE_INFO_EVENT_DELAY_BACKGROUND_MS);
            }
        } else if (level == 60) {
            Log.warning(str, "Memory level: TRIM_MEMORY_MODERATE, device low on memory, app is in middle of line to be killed if more memory is not found");
        } else if (level == 80) {
            Log.warning(str, "Memory level: TRIM_MEMORY_COMPLETE, device low on memory, app will be one of the first to be killed if more memory is not found");
        } else {
            Log.info(str, "Memory level: " + level);
        }
        if (level >= 20) {
            SQLiteDatabase.releaseMemory();
            if (level <= 40) {
                Log.info(str, "Weakening cache to free up memory");
                CacheManager.getInstance().weakenCache();
            } else {
                onLowMemory();
            }
        }
    }

    @Override // android.app.Application
    public void onTerminate() {
        RecentlyPlayedDatabase.close();
        unregisterReceivers();
        shutdownServices();
        super.onTerminate();
    }

    public static Locale getLocale() {
        Locale locale = sLocale;
        return locale != null ? locale : Locale.US;
    }

    public static boolean localeHasChanged(Context context) {
        Locale locale = sLocale;
        sLocale = context.getResources().getConfiguration().locale;
        CirrusDatabase.reopenDatabase(context);
        initPlayQueue(context);
        return !sLocale.equals(locale);
    }

    @Deprecated
    public static Context getContext() {
        return sContext;
    }

    public static synchronized LibraryItemFactory getLibraryItemFactory() {
        if (sLibItemFactory == null) {
            sLibItemFactory = new CirrusSourceLibraryItemFactory(getContext());
        }
        return sLibItemFactory;
    }

    public static synchronized Capabilities getCapabilities() {
        if (sCapabilities == null) {
            sCapabilities = new Capabilities();
        }
        return sCapabilities;
    }

    public static synchronized com.amazon.music.config.Configuration getConfiguration(final Context context) {
        return ConfigurationFactory.getInstance(context, RemoteConfigLoader.getInstance(context), RemoteConfigAttributes.getInstance(context));
    }

    public static DevModeCapabilities getDevModeCapabilities() {
        return sDevModeCapabilities;
    }

    @Deprecated
    public static Handler getDefaultHandler() {
        return sMainThreadHandler;
    }

    public static Handler getBackgroundHandler() {
        return sBackgroundHandler;
    }

    public static boolean onMainThread() {
        return sMainThreadHandler.getLooper().getThread().equals(Thread.currentThread());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void dumpThreads() {
        for (Map.Entry<Thread, StackTraceElement[]> entry : Thread.getAllStackTraces().entrySet()) {
            StringBuilder sb = new StringBuilder(64);
            Thread key = entry.getKey();
            sb.append(String.format(Locale.US, "\"%s\" %s", key.getName(), key.getState().toString()));
            for (StackTraceElement stackTraceElement : entry.getValue()) {
                sb.append("\n\t");
                sb.append(stackTraceElement);
            }
            Log.warning("Threads", sb.toString());
        }
    }

    private void setDefaultExceptionHandler() {
        final Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        final AppHealthUtil appHealthUtil = new AppHealthUtil(PreferenceManager.getDefaultSharedPreferences(sContext));
        final PendingCrashLogs pendingCrashLogs = new PendingCrashLogs();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: com.amazon.mp3.AmazonApplication.8
            @Override // java.lang.Thread.UncaughtExceptionHandler
            public void uncaughtException(final Thread deadThread, final Throwable uncaught) {
                appHealthUtil.appCrash();
                StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);
                uncaught.printStackTrace(printWriter);
                printWriter.close();
                CirrusErrorLogger.getInstance().log(CirrusError.UNEXPECTED_SHUTDOWN, stringWriter.toString());
                if ((uncaught instanceof OutOfMemoryError) || (uncaught.getCause() instanceof OutOfMemoryError)) {
                    ActivityManager activityManager = (ActivityManager) AmazonApplication.sContext.getSystemService(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME);
                    Debug.MemoryInfo[] processMemoryInfo = activityManager != null ? activityManager.getProcessMemoryInfo(new int[]{Process.myPid()}) : new Debug.MemoryInfo[0];
                    if (processMemoryInfo.length > 0) {
                        Debug.MemoryInfo memoryInfo = processMemoryInfo[0];
                        Log.warning(AmazonApplication.TAG, "Dalvik PSS %s; Dirty %s; Shared %s", Integer.valueOf(memoryInfo.dalvikPss), Integer.valueOf(memoryInfo.dalvikPrivateDirty), Integer.valueOf(memoryInfo.dalvikSharedDirty));
                        Log.warning(AmazonApplication.TAG, "Native PSS %s; Dirty %s; Shared %s", Integer.valueOf(memoryInfo.nativePss), Integer.valueOf(memoryInfo.nativePrivateDirty), Integer.valueOf(memoryInfo.nativeSharedDirty));
                        Log.warning(AmazonApplication.TAG, "Other PSS %s; Dirty %s; Shared %s", Integer.valueOf(memoryInfo.otherPss), Integer.valueOf(memoryInfo.otherPrivateDirty), Integer.valueOf(memoryInfo.otherSharedDirty));
                        ApplicationInfo appInfo = AmazonApplication.getAppInfo();
                        Log.warning(AmazonApplication.TAG, "Heap Limit: %s", Float.valueOf(appInfo != null && (appInfo.flags & 1048576) != 0 ? activityManager.getLargeMemoryClass() : activityManager.getMemoryClass()));
                    }
                }
                AmazonApplication.dumpThreads();
                Context applicationContext = AmazonApplication.this.getApplicationContext();
                pendingCrashLogs.scheduleLogsUpload(applicationContext, new UnexpectedEventReport(applicationContext, uncaught, ReportType.UNCAUGHT_EXCEPTION).getCrashDescriptor());
                Looper mainLooper = Looper.getMainLooper();
                if (AmazonApplication.this.isSignedIn() || deadThread == mainLooper.getThread()) {
                    ExitCheck.clearActivityCheck(AmazonApplication.sContext, true);
                    defaultUncaughtExceptionHandler.uncaughtException(deadThread, uncaught);
                } else {
                    promptUserToReportCrashByEmail(new ReportCrashDialogActivity.CallBack() { // from class: com.amazon.mp3.AmazonApplication.8.1
                        @Override // com.amazon.mp3.activity.ReportCrashDialogActivity.CallBack
                        public void action() {
                            defaultUncaughtExceptionHandler.uncaughtException(deadThread, uncaught);
                        }
                    });
                }
            }

            private void promptUserToReportCrashByEmail(final ReportCrashDialogActivity.CallBack killCallBack) {
                final Intent startIntent = ReportCrashDialogActivity.getStartIntent(AmazonApplication.this, killCallBack);
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.amazon.mp3.AmazonApplication.8.2
                    @Override // java.lang.Runnable
                    public void run() {
                        AmazonApplication.this.startActivity(startIntent);
                    }
                });
            }
        });
        RxJavaPlugins.setErrorHandler(new Consumer() { // from class: com.amazon.mp3.AmazonApplication$$ExternalSyntheticLambda7
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) throws Exception {
                AmazonApplication.lambda$setDefaultExceptionHandler$5((Throwable) obj);
            }
        });
        io.reactivex.rxjava3.plugins.RxJavaPlugins.setErrorHandler(new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amazon.mp3.AmazonApplication$$ExternalSyntheticLambda8
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                AmazonApplication.lambda$setDefaultExceptionHandler$6((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$setDefaultExceptionHandler$5(Throwable th) throws Exception {
        Log.error(TAG, "Unhandled RxJava2 exception received: " + th.getMessage(), th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$setDefaultExceptionHandler$6(Throwable th) throws Throwable {
        Log.error(TAG, "Unhandled RxJava3 exception received: " + th.getMessage(), th);
    }

    private void setRxJavaErrorHandlers() {
        RxJavaPlugins.setErrorHandler(new Consumer() { // from class: com.amazon.mp3.AmazonApplication$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) throws Exception {
                AmazonApplication.lambda$setRxJavaErrorHandlers$7((Throwable) obj);
            }
        });
        io.reactivex.rxjava3.plugins.RxJavaPlugins.setErrorHandler(new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amazon.mp3.AmazonApplication$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                AmazonApplication.lambda$setRxJavaErrorHandlers$8((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$setRxJavaErrorHandlers$7(Throwable th) throws Exception {
        Log.error(TAG, "Unhandled RxJava2 exception received: " + th.getMessage(), th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$setRxJavaErrorHandlers$8(Throwable th) throws Throwable {
        Log.error(TAG, "Unhandled RxJava3 exception received: " + th.getMessage(), th);
    }

    public static void changeLocaleIfNeeded(final Context context) {
        boolean zIsAppDisplayLangSettingEnabled = getCapabilities().isAppDisplayLangSettingEnabled();
        boolean zIsLocaleSupportedForUserInArcus = getCapabilities().isLocaleSupportedForUserInArcus();
        if (zIsAppDisplayLangSettingEnabled) {
            LocaleManager.INSTANCE.setLocale(context);
        } else {
            if (zIsLocaleSupportedForUserInArcus) {
                return;
            }
            Locale locale = new Locale("en", "US");
            Log.debug(TAG, "Locale is not supported for the user, falling back to %s", locale);
            LocaleManager.INSTANCE.setLocale(context, locale);
        }
    }

    private void initializeCrashDetectionHelper(final Context context) {
        Trace.beginSection("AMA.AmazonApplication.initializeCrashDetectionHelper");
        if (PlatformUtil.isFireOS()) {
            return;
        }
        AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(context);
        AccountDetailUtil accountDetailUtil = AccountDetailUtil.get(context);
        String deviceId = accountCredentialStorage.getDeviceId();
        String deviceType = accountCredentialStorage.getDeviceType();
        String homeMarketPlaceId = accountDetailUtil.getHomeMarketPlaceId();
        String countryCode = accountDetailUtil.getHomeMarketPlace().getCountryCode();
        CrashDetectionHelper upCrashDetection = CrashDetectionHelper.setUpCrashDetection(deviceType, deviceId, AndroidMetricsFactoryImpl.getInstance(context), new DomainChooser() { // from class: com.amazon.mp3.AmazonApplication$$ExternalSyntheticLambda6
            @Override // com.amazon.device.crashmanager.DomainChooser
            public final Domain chooseDomain() {
                return Domain.PROD;
            }
        }, this, true);
        CrashDetectionHelper.enableNDKCrashDetection(this);
        upCrashDetection.setMarketPlaceId(homeMarketPlaceId);
        upCrashDetection.setCountryOfResidence(countryCode);
        upCrashDetection.appendCrashDetailsCollector(stabilityManager.getStabilityCrashDetailsCollector());
        Log.debug(TAG, "Initialized Crash Detection Helper with deviceId: %s, deviceType: %s, homeMarketPlaceId: %s countryCode: %s", deviceId, deviceType, homeMarketPlaceId, countryCode);
        Trace.endSection();
    }

    private static void initializeLogger(Context context, final boolean debugBuild) {
        Trace.beginSection("AMA.AmazonApplication.initializeLogger");
        AmazonMusicMarketLogger.initialize(LogFiles.createAppLogConfig(context), debugBuild);
        LogFactory.getLog((Class<?>) AWS4Signer.class).setLevel(LogFactory.Level.INFO);
        Trace.endSection();
    }

    private void initializeUnifiedOkHttpClient(final Context context) {
        Trace.beginSection("AMA.AmazonApplication.initializeUnifiedOkHttpClient");
        AMOkHttpClient.initializeUnifiedOkHttpClient(context);
        BootstrapSingletonTask.get().registerUserObserver().subscribe(new Action1() { // from class: com.amazon.mp3.AmazonApplication$$ExternalSyntheticLambda4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                AMOkHttpClient.activateUnifiedOkHttpClient(context);
            }
        });
        Trace.endSection();
    }

    private void initializeReactNativeNetworking() {
        Trace.beginSection("AMA.AmazonApplication.initializeReactNativeNetworking");
        OkHttpClientProvider.setOkHttpClientFactory(new OkHttpClientFactory() { // from class: com.amazon.mp3.AmazonApplication.9
            private OkHttpClient okHttpClient = null;

            @Override // com.facebook.react.modules.network.OkHttpClientFactory
            public OkHttpClient createNewNetworkModuleClient() {
                if (this.okHttpClient == null) {
                    OkHttpClient.Builder builderNewBuilder = UnifiedOkHttpClient.newBuilder();
                    TimeUnit timeUnit = TimeUnit.MILLISECONDS;
                    this.okHttpClient = builderNewBuilder.connectTimeout(0L, timeUnit).readTimeout(0L, timeUnit).writeTimeout(0L, timeUnit).cookieJar(new ReactCookieJarContainer()).build();
                }
                return this.okHttpClient;
            }
        });
        Trace.endSection();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupReactNativePrewarming() {
        if (Feature.dragonfly_enabled.isEnabled() || Feature.dragonfly_fireos_enabled.isEnabled()) {
            long intForKey = AppConfigProvider.getInstance(getApplicationContext()).getConfiguration().getIntForKey("rn_prewarming_delay_ms");
            if (intForKey <= 0) {
                intForKey = DEFAULT_REACT_NATIVE_PREWARMING_DELAY_MS;
            }
            boolean zIsEnabled = FindFeatureGating.FIND_LANDING_REACT_NATIVE.isEnabled((FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class));
            boolean z = LastViewedScreenUtil.getLastViewedScreen(getApplicationContext()) == LastViewedScreenUtil.LastViewedScreen.SEARCH;
            if (zIsEnabled && z) {
                ((PrimaryReactInitializer) getReactNativeHost()).initializeBridge();
            } else {
                sMainThreadHandler.postDelayed(new Runnable() { // from class: com.amazon.mp3.AmazonApplication$$ExternalSyntheticLambda11
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$setupReactNativePrewarming$11();
                    }
                }, intForKey);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupReactNativePrewarming$11() {
        ((PrimaryReactInitializer) getReactNativeHost()).initializeBridge();
    }

    private void initializePicasso(Context context) {
        Trace.beginSection("AMA.AmazonApplication.initializePicasso");
        int heapSize = ((((int) Capabilities.getHeapSize(context)) * 1024) * 1024) / 10;
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.addRequestHandler(new VideoRequestHandler());
        builder.memoryCache(new LruCache(heapSize));
        builder.downloader(new Downloader() { // from class: com.amazon.mp3.AmazonApplication.10
            private final OkHttpClient okHttpClient = UnifiedOkHttpClient.newClient();

            @Override // com.squareup.picasso.Downloader
            @NonNull
            public Response load(@NonNull Request request) throws IOException {
                return FirebasePerfOkHttpClient.execute(this.okHttpClient.newCall(request));
            }
        });
        Picasso picassoBuild = builder.build();
        Picasso.setSingletonInstance(picassoBuild);
        picassoBuild.setIndicatorsEnabled(SettingsActivity.isPicassoDebugOn(context));
        Trace.endSection();
    }

    private static void initializeHermes(final Context context) {
        Trace.beginSection("AMA.AmazonApplication.initializeHermes");
        Hermes.ignite(context, mObjectMapper);
        Trace.endSection();
    }

    private static void initializeAccountManager(final Context context) {
        Trace.beginSection("AMA.AmazonApplication.initializeAccountManager");
        AccountManagerSingleton.initialize(new AccountManagerFactory().buildAccountManager(context));
        Trace.endSection();
    }

    private void initializeMetrics(final Context context) {
        Trace.beginSection("AMA.AmazonApplication.initializeMetrics");
        MetricsManager manager = MetricsHolder.getManager();
        MusicEventDataProvider musicEventDataProvider = new MusicEventDataProvider(context, getCapabilities().getPlatformName());
        MusicUploadFactory musicUploadFactory = new MusicUploadFactory(context);
        AnonymousMusicUploadFactory anonymousMusicUploadFactory = new AnonymousMusicUploadFactory(context);
        long metricsUploadIntervalMilliseconds = getMetricsUploadIntervalMilliseconds();
        long metricsFileMaxSizeBytes = getMetricsFileMaxSizeBytes(context);
        NoOpErrorReporter noOpErrorReporter = new NoOpErrorReporter();
        sClientInfoMetricsHelper = new ClientInfoMetricsHelper(getApplicationContext(), sMainThreadHandler);
        sBackgroundHandler.postDelayed(new Runnable() { // from class: com.amazon.mp3.AmazonApplication.11
            @Override // java.lang.Runnable
            public void run() {
                if (AmazonApplication.isApplicationForeground()) {
                    Log.debug(AmazonApplication.TAG, "Application launched in foreground");
                    MinervaMetricsFactory.record("vrzx4wil", "yj4i/2/03330400", "Counter", 1L, "MetricName", "AppLaunchedForeground");
                } else {
                    Log.debug(AmazonApplication.TAG, "Application launched in background");
                    MinervaMetricsFactory.record("vrzx4wil", "e0xy/2/03330400", "Counter", 1L, "MetricName", "AppLaunchedBackground");
                }
            }
        }, 300L);
        try {
            MTSEventHandler mTSEventHandler = new MTSEventHandler(musicEventDataProvider, new MTSFileConfig(MetricsFilePathUtil.getDefaultMetricsPath(context), metricsFileMaxSizeBytes), musicUploadFactory, noOpErrorReporter, metricsUploadIntervalMilliseconds, new MTSCounterMetadataProviderImpl(context), new MTSFileConfig(MetricsFilePathUtil.getPriorityMetricsPath(context), metricsFileMaxSizeBytes), new MTSMinervaMetricsProviderImpl(context));
            RealTimeMTSEventHandler realTimeMTSEventHandler = new RealTimeMTSEventHandler(musicUploadFactory, mTSEventHandler, musicEventDataProvider);
            AnonymousRealTimeMTSEventHandler anonymousRealTimeMTSEventHandler = new AnonymousRealTimeMTSEventHandler(anonymousMusicUploadFactory, mTSEventHandler, musicEventDataProvider);
            StabilityEventHandler stabilityEventHandler = new StabilityEventHandler(stabilityManager);
            manager.registerEventHandler(mTSEventHandler);
            manager.registerEventHandler(realTimeMTSEventHandler);
            manager.registerEventHandler(anonymousRealTimeMTSEventHandler);
            manager.registerEventHandler(stabilityEventHandler);
            sClientInfoMetricsHelper.sendClientInfoMetricForApplicationStart();
            Log.info(TAG, "ApplicationStartup: " + ApplicationAttributes.getInstance(this).toString());
        } catch (StorageNotAvailableException e) {
            Log.error(TAG, "Unable to initialize Metrics Handler", e);
        }
        Trace.endSection();
    }

    public static void initPlayQueue(final Context context) {
        Trace.beginSection("AMA.AmazonApplication.initPlayQueue");
        final PlayQueue playQueue = PlayQueue.getInstance();
        new Thread(new Runnable() { // from class: com.amazon.mp3.AmazonApplication.12
            @Override // java.lang.Runnable
            public void run() {
                playQueue.init(new NowPlayingQueueHelper(context), CirrusDatabase.getWritableDatabase(context));
                NowPlayingQueueUtil.getInstance().initRxPlayQueue(context, playQueue);
            }
        }).start();
        Trace.endSection();
    }

    private static long getMetricsFileMaxSizeBytes(Context context) {
        return getConfiguration(context).getLong("amp_metrics_max_file_size_bytes", 60000L);
    }

    private static long getMetricsUploadIntervalMilliseconds() {
        return MTSEventHandler.DEFAULT_UPLOAD_DELAY_MS;
    }

    private void cacheInitialLocaleInformation() {
        synchronized (AmazonApplication.class) {
            if (sLocale == null) {
                sLocale = getResources().getConfiguration().locale;
            }
        }
    }

    private void registerReceivers() {
        registerReceiver(this.mRemoteConfigLoadedReceiver, new IntentFilter("com.amazon.mp3.configuration.REMOTE_CONFIG_LOADED"));
        getContentResolver().registerContentObserver(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, true, new ContentObserver(getBackgroundHandler()) { // from class: com.amazon.mp3.AmazonApplication.14
            @Override // android.database.ContentObserver
            public void onChange(boolean selfChange) {
                synchronized (AmazonApplication.this.mTrackSync) {
                    if (!AmazonApplication.this.mPendingTrackSyncOperations) {
                        if (AmazonApplication.this.mTimeSinceLastTrackSync + 10000 < SystemClock.elapsedRealtime()) {
                            AmazonApplication.this.mPendingTrackSyncOperations = true;
                            AmazonApplication.getDefaultHandler().post(AmazonApplication.this.mTrackSync);
                        } else {
                            AmazonApplication.this.mPendingTrackSyncOperations = true;
                            AmazonApplication.getDefaultHandler().postDelayed(AmazonApplication.this.mTrackSync, (AmazonApplication.this.mTimeSinceLastTrackSync + 10000) - SystemClock.elapsedRealtime());
                        }
                    }
                }
            }
        });
        registerStreamingBlueMoonExceptionReceiver();
        registerBroadcastDisplayBlueMoonDialogReceiver();
        registerActivityLifecycleCallbacks(sForegroundStateMonitor);
        registerAccountStateChangeListener();
        registerIdleModeReceiver();
        registerRecentsPlaybackListener();
        registerPMPlaybackListener();
        registerWidevineAutomigrationReceiver();
        registerLikesAndFollowsReceiver();
    }

    private void registerWidevineAutomigrationReceiver() {
        registerReceiver(this.mWidevineAutomigrationReceiver, new IntentFilter("com.amazon.mp3.library.service.sync.ACTION_SYNC_EVENT"));
    }

    private void registerPMPlaybackListener() {
        if (PlatformUtil.isFireOS()) {
            this.pmPlaybackListener = PMPlaybackListener.register(getApplicationContext());
        }
    }

    private void unregisterPMPlaybackListener() {
        PMPlaybackListener pMPlaybackListener = this.pmPlaybackListener;
        if (pMPlaybackListener != null) {
            pMPlaybackListener.unregister();
            this.pmPlaybackListener = null;
        }
    }

    private void registerRecentsPlaybackListener() {
        RecentsPlaybackListener recentsPlaybackListener = new RecentsPlaybackListener(getApplicationContext());
        this.recentsPlaybackListener = recentsPlaybackListener;
        this.playbackController.addOnPlayStateChangedListener(recentsPlaybackListener);
    }

    private void unregisterRecentsPlaybackListener() {
        this.playbackController.removeOnPlayStateChangedListener(this.recentsPlaybackListener);
        this.recentsPlaybackListener = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerDeviceChargingReceiver(final Context context) {
        if (getCapabilities().isWakeWordEnabled(context)) {
            ScreenWakeLock.registerDeviceChargingReceiver(context);
        }
    }

    private void unregisterDeviceChargingReceiver() {
        if (getCapabilities().isWakeWordEnabled(this)) {
            ScreenWakeLock.unregisterDeviceChargingReceiver(this);
        }
    }

    private void unregisterReceivers() {
        unregisterReceiver(this.mRemoteConfigLoadedReceiver);
        unregisterReceiver(this.mWidevineAutomigrationReceiver);
        unregisterStreamingBlueMoonExceptionReceiver();
        unregisterBroadcastDisplayBlueMoonDialogReceiver();
        unregisterActivityLifecycleCallbacks(sForegroundStateMonitor);
        unregisterAccountStateChangeListener();
        unregisterIdleModeReceiver();
        unregisterDeviceChargingReceiver();
        unregisterRecentsPlaybackListener();
        unregisterPMPlaybackListener();
        unregisterLikesAndFollowsReceiver();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void unregisterReceiver(BroadcastReceiver receiver) {
        try {
            super.unregisterReceiver(receiver);
        } catch (IllegalArgumentException e) {
            Log.debug(TAG, "IllegalArgumentException thrown when unregistering receiver: " + receiver.getClass().toString(), e);
        } catch (IllegalStateException e2) {
            Log.debug(TAG, "IllegalStateException thrown when unregistering receiver: " + receiver.getClass().toString(), e2);
        }
    }

    public static void setExiting() {
        sIsExiting = true;
    }

    public static boolean isExiting() {
        return sIsExiting;
    }

    public void setPausedState(boolean pause) {
        sPaused = pause;
        if (pause) {
            return;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime() - sTimeLastDeviceInfoMetricEvent;
        Handler handler = sBackgroundHandler;
        Runnable runnable = mDeviceInfoMetricRunnable;
        handler.removeCallbacks(runnable);
        long j = DEVICE_INFO_EVENT_DELAY_FOREGROUND_MS;
        if (jElapsedRealtime >= j) {
            sBackgroundHandler.post(runnable);
        } else {
            sBackgroundHandler.postDelayed(runnable, j - jElapsedRealtime);
        }
    }

    public boolean getPauseState() {
        return sPaused;
    }

    private void unregisterIdleModeReceiver() {
        if (PlatformUtil.isVersionOrGreater(23)) {
            unregisterReceiver(this.mIdleModeReceiver);
        }
    }

    private void registerIdleModeReceiver() {
        if (PlatformUtil.isVersionOrGreater(23)) {
            registerReceiver(this.mIdleModeReceiver, new IntentFilter("android.os.action.DEVICE_IDLE_MODE_CHANGED"));
        }
    }

    private void registerStreamingBlueMoonExceptionReceiver() {
        registerReceiver(this.mStreamingBlueMoonExceptionReceiver, new IntentFilter("com.amazon.mp3.playback.service.ACTION_STREAMING_BLUE_MOON_EXCEPTION"));
    }

    private void unregisterStreamingBlueMoonExceptionReceiver() {
        unregisterReceiver(this.mStreamingBlueMoonExceptionReceiver);
    }

    private void registerBroadcastDisplayBlueMoonDialogReceiver() {
        registerReceiver(this.mBroadcastDisplayBlueMoonDialogReceiver, new IntentFilter("com.amazon.mp3.net.cirrus.BlueMoonExceptionsUtil.Display_BlueMoon_Dialog_Broadcast"));
    }

    private void unregisterBroadcastDisplayBlueMoonDialogReceiver() {
        unregisterReceiver(this.mBroadcastDisplayBlueMoonDialogReceiver);
    }

    public static void subscribeForegroundObserver(final Action1<Void> nextAction, final Action1<Throwable> errorAction) {
        sForegroundStateMonitor.subscribeForegroundObserver(nextAction, errorAction);
    }

    public static void subscribeBackgroundObserver(final Action1<Void> nextAction, final Action1<Throwable> errorAction) {
        sForegroundStateMonitor.subscribeBackgroundObserver(nextAction, errorAction);
    }

    private void registerAccountStateChangeListener() {
        AccountManagerSingleton.get().registerListener(this.mAccountStateChangeListener);
    }

    private void unregisterAccountStateChangeListener() {
        AccountManagerSingleton.get().deregisterListener(this.mAccountStateChangeListener);
    }

    private class TrackSyncRunnable implements Runnable {
        private TrackSyncRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (AmazonApplication.this.mTrackSync) {
                AmazonApplication.this.mPendingTrackSyncOperations = false;
                AmazonApplication.this.mTimeSinceLastTrackSync = SystemClock.elapsedRealtime();
            }
            SyncService.startSync(AmazonApplication.getContext(), voOSType.VOOSMP_PID_ANALYTICS_AGENT);
            StorageInfo.updateNotification(AmazonApplication.getContext());
        }
    }

    public static ApplicationInfo getAppInfo() {
        AmazonApplication amazonApplication = sApplication;
        if (amazonApplication != null) {
            return amazonApplication.getApplicationInfo();
        }
        return null;
    }

    public static AmazonApplication getApplication() {
        return sApplication;
    }

    public static AuthInfo getAuthInfoMAPR5() {
        if (sAuthInfoMAPR5 == null) {
            sAuthInfoMAPR5 = new AuthInfoMAPR5(sContext, Environment.STORE.get().toHost(), false);
        }
        return sAuthInfoMAPR5;
    }

    public static ClientInfoMetricsHelper getClientInfoMetricsHelper() {
        return sClientInfoMetricsHelper;
    }

    private void initializeConfigurationProvider(final Context context) {
        Trace.beginSection("AMA.AmazonApplication.initializeConfigurationProvider");
        FeatureFlagsProvider.init(AppConfigProvider.getInstance(context).getConfiguration());
        Trace.endSection();
    }

    private void registerLikesAndFollowsReceiver() {
        if (getCapabilities().isTrueLikesAndFollowsEnabled()) {
            this.likesAndFollowsObserver = new LikesAndFollowsWorkManagerInitializer();
            ProcessLifecycleOwner.get().getLifecycle().addObserver(this.likesAndFollowsObserver);
            Log.debug(TAG, "Likes and Follows registered");
        }
    }

    private void unregisterLikesAndFollowsReceiver() {
        if (this.likesAndFollowsObserver != null) {
            ProcessLifecycleOwner.get().getLifecycle().removeObserver(this.likesAndFollowsObserver);
            this.likesAndFollowsObserver = null;
            Log.debug(TAG, "Likes and Follows unregistered");
        }
    }
}
