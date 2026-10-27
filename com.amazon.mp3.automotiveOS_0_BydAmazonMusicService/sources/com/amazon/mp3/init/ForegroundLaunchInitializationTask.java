package com.amazon.mp3.init;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.BootstrapSingletonTask;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automaticimport.AutomaticImportManager;
import com.amazon.mp3.config.ApplicationAttributes;
import com.amazon.mp3.det.PendingCrashLogs;
import com.amazon.mp3.det.PendingReports;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.external.ads.AmazonAds;
import com.amazon.mp3.library.service.sync.SyncService;
import com.amazon.mp3.service.metrics.CampaignMetrics;
import com.amazon.mp3.skiplimit.impl.HardCodedRefreshStrategy;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.health.AppHealthEventMonitor;
import com.amazon.music.wakeword.config.WakeWordConfigurationProvider;
import rx.functions.Action1;

/* JADX INFO: loaded from: classes3.dex */
public class ForegroundLaunchInitializationTask implements InitializationTask {
    private static final String TAG = "ForegroundLaunchInitializationTask";
    private static final Handler mForegroundHandler = new Handler(Looper.getMainLooper());
    private final AppHealthEventMonitor mAppHealthEventMonitor;
    private final Context mContext;

    @Override // com.amazon.mp3.init.InitializationTask
    public Action1 getOnNextAction() {
        return null;
    }

    public ForegroundLaunchInitializationTask(final Context context, AppHealthEventMonitor appHealthEventMonitor) {
        this.mContext = context.getApplicationContext();
        this.mAppHealthEventMonitor = appHealthEventMonitor;
    }

    @Override // com.amazon.mp3.init.InitializationTask
    public String getName() {
        return TAG;
    }

    @Override // com.amazon.mp3.init.InitializationTask
    public void initialize() {
        subscribeToBootstrap();
        StyleSheetProvider.initializeStyleSheet(this.mContext);
        this.mAppHealthEventMonitor.appOpened(ApplicationAttributes.getInstance(this.mContext).getVersionShort());
        PendingReports.submitPendingReports(this.mContext);
        activateRedownloadFlowOnFirstWidevineLaunch();
    }

    private void activateRedownloadFlowOnFirstWidevineLaunch() {
        if (!SettingsUtil.hasCompletedWidevineDatabaseUpdate(this.mContext)) {
            SyncService.setPreWidevineDBUpdateNeeded(true);
        } else {
            Log.info(TAG, "Widevine update flow has already been activated. Skipping.");
        }
    }

    private void subscribeToBootstrap() {
        BootstrapSingletonTask.get().registerUserObserver().subscribe(new Action1<Void>() { // from class: com.amazon.mp3.init.ForegroundLaunchInitializationTask.1
            @Override // rx.functions.Action1
            public void call(Void aVoid) {
                ForegroundLaunchInitializationTask.this.initAmazonAds();
                new Thread(new Runnable() { // from class: com.amazon.mp3.init.ForegroundLaunchInitializationTask.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        WakeWordConfigurationProvider.init();
                        ForegroundLaunchInitializationTask.this.cleanupSharedPreferences();
                        ForegroundLaunchInitializationTask foregroundLaunchInitializationTask = ForegroundLaunchInitializationTask.this;
                        foregroundLaunchInitializationTask.uploadLogsAfterCrash(foregroundLaunchInitializationTask.mContext);
                        MusicDownloader.getInstance(ForegroundLaunchInitializationTask.this.mContext);
                        HardCodedRefreshStrategy.INSTANCE.tryRefreshHardCodeLimitCount();
                        ForegroundLaunchInitializationTask foregroundLaunchInitializationTask2 = ForegroundLaunchInitializationTask.this;
                        foregroundLaunchInitializationTask2.checkAutoImportRemovalSuccessful(foregroundLaunchInitializationTask2.mContext);
                    }
                }).start();
            }
        }, new Action1<Throwable>() { // from class: com.amazon.mp3.init.ForegroundLaunchInitializationTask.2
            @Override // rx.functions.Action1
            public void call(Throwable throwable) {
                Log.warning(ForegroundLaunchInitializationTask.TAG, "BootstrapSingletonTask error. Could not complete initialization: " + throwable.getMessage());
            }
        });
        HarleyTombstoneTask.uploadHarleyTombstones(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initAmazonAds() {
        if (AmazonApplication.getCapabilities().isAmazonAdsEnabled()) {
            mForegroundHandler.postDelayed(new Runnable() { // from class: com.amazon.mp3.init.ForegroundLaunchInitializationTask.3
                @Override // java.lang.Runnable
                public void run() {
                    if (!SettingsUtil.hasFetchedReferrerInformation(ForegroundLaunchInitializationTask.this.mContext)) {
                        Log.debug(ForegroundLaunchInitializationTask.TAG, "Fetching referrer information from install referrer API");
                        CampaignMetrics.fetchAndSaveCampaignReferrer(ForegroundLaunchInitializationTask.this.mContext);
                    } else {
                        Log.debug(ForegroundLaunchInitializationTask.TAG, "Registering user to SIS");
                        new AmazonAds(ForegroundLaunchInitializationTask.this.mContext).identifyCustomer();
                    }
                }
            }, 25000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkAutoImportRemovalSuccessful(final Context context) {
        AutomaticImportManager automaticImportManager = new AutomaticImportManager(context);
        if (automaticImportManager.isAutomaticImportEnabled() || automaticImportManager.wasRemoveImportContentSuccessful()) {
            return;
        }
        Log.info(TAG, "Automatic import is disabled and last imported content removal operation was not completed successfully,  run imported content removal operation again");
        automaticImportManager.removeImportedContent(AmazonApplication.getBackgroundHandler());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cleanupSharedPreferences() {
        SharedPreferences sharedPreferences = this.mContext.getSharedPreferences("com.amazon.mp3.playback.service.AudioEffectController", 0);
        if (sharedPreferences.contains("virtualizer")) {
            sharedPreferences.edit().remove("virtualizer").apply();
            Log.debug(TAG, "Virtualizer SharedPreference entry has been removed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uploadLogsAfterCrash(Context context) {
        new PendingCrashLogs().uploadPendingCrashLogsIfRequired(context);
    }
}
