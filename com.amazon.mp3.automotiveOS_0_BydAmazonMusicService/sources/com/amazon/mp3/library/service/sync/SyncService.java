package com.amazon.mp3.library.service.sync;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import androidx.core.app.NotificationCompat;
import androidx.core.os.EnvironmentCompat;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.cache.likes.LikesStateCacheManager;
import com.amazon.mp3.library.provider.ExternalProvider;
import com.amazon.mp3.ondemandplaylists.api.GetOnDemandPlaylistsService;
import com.amazon.mp3.ondemandplaylists.metrics.GetOnDemandPlaylistsMetricsUtil;
import com.amazon.mp3.service.ServiceUtil;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.service.job.JobSessionService;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UniqueCodeUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.widget.PlayerWidgetController;
import com.amazon.mp3.widget.WidgetMetrics;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
public class SyncService extends JobSessionService {
    private static boolean sDbMigrationSyncNeeded;
    private static volatile boolean sLastSyncFailedWithNetworkError;
    private static boolean sMatchHashesSyncNeeded;
    private static volatile boolean sRunning;
    private static boolean sSyncNotificationVisible;
    private Queue<Intent> mPendingCommands;
    private static final String TAG = SyncService.class.getSimpleName();
    private static final Random RANDOM = new Random();
    private static boolean sPreWidevineDBUpdateNeeded = false;
    private static volatile long sLastSyncTime = 0;
    private static int sFreshnessExpiry = 1800000;
    private static final int RECURRING_SYNC_REQUEST_ID = UniqueCodeUtil.nextUniqueCode();
    private static boolean sBlockSyncing = false;
    private static int sChangesMade = 0;
    private long mJobId = -1;
    private int mLastStartId = 0;
    private final BroadcastReceiver mSyncEventReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.library.service.sync.SyncService.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            int intExtra = intent.getIntExtra("com.amazon.mp3.library.service.sync.EXTRA_SYNC_EVENT_TYPE", 0);
            String str = intExtra != 1 ? intExtra != 2 ? intExtra != 3 ? intExtra != 4 ? intExtra != 5 ? EnvironmentCompat.MEDIA_UNKNOWN : "finished" : NotificationCompat.CATEGORY_PROGRESS : "failed" : "retrying" : "started";
            BauhausToastUtils.showDefaultToast(SyncService.this, "SyncEvent: " + str, 0);
        }
    };

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static synchronized void changesMade() {
        sChangesMade++;
    }

    public static synchronized boolean changesWereMade() {
        return sChangesMade > 0;
    }

    public static synchronized void clearChanges() {
        sChangesMade = 0;
    }

    public static void startTimeCheckedSync(final Context context) {
        if (System.currentTimeMillis() - getLastSyncTime() > sFreshnessExpiry) {
            setLastSyncTime(System.currentTimeMillis());
            AmazonApplication.getDefaultHandler().postDelayed(new Runnable() { // from class: com.amazon.mp3.library.service.sync.SyncService.1
                @Override // java.lang.Runnable
                public void run() {
                    SettingsUtil.getPrefs(context).getLong(context.getString(R.string.setting_key_sync_last_deletion_check_time), -1L);
                    System.currentTimeMillis();
                    SyncService.startSync(context, 14003);
                }
            }, 2000L);
        }
    }

    public static void startSync(Context context, int flags) {
        Intent intent = new Intent(context, (Class<?>) SyncService.class);
        intent.putExtra("com.amazon.mp3.library.service.sync.EXTRA_SYNC_FLAGS", flags);
        ServiceUtil.startService(context, intent);
        refreshOnDemandPlaylists();
        clearTracksLikeStatesCacheForFreeTierAndNonSubscribers(context);
    }

    private static SharedPreferences getSharedPreference(Context context) {
        return context.getSharedPreferences("com.amazon.mp3_SyncService", 4);
    }

    public static boolean isRunning() {
        return sRunning;
    }

    private static synchronized void setRunning(boolean running) {
        sRunning = running;
    }

    public static synchronized void setSyncNotificationVisible(boolean running) {
        sSyncNotificationVisible = running;
    }

    public static synchronized void setMatchHashesSyncNeeded(boolean matchHashesSyncNeeded) {
        sMatchHashesSyncNeeded = matchHashesSyncNeeded;
    }

    public static synchronized void setDbMigrationSyncNeeded(boolean dbMigrationSyncNeeded) {
        sDbMigrationSyncNeeded = dbMigrationSyncNeeded;
    }

    public static boolean isDbMigrationSyncNeeded() {
        return sDbMigrationSyncNeeded;
    }

    public static synchronized void setPreWidevineDBUpdateNeeded(boolean b) {
        sPreWidevineDBUpdateNeeded = b;
    }

    public static boolean isPreWidevineDBUpdateNeeded() {
        return sPreWidevineDBUpdateNeeded;
    }

    private static synchronized void setLastSyncFailedWithNetworkError(boolean error) {
        sLastSyncFailedWithNetworkError = error;
    }

    public static void resetLastCloudSyncTime(Context context) {
        SharedPreferences.Editor editorEdit = getSharedPreference(context).edit();
        editorEdit.remove(context.getResources().getString(R.string.setting_key_sync_last_cloud_sync_operation_time));
        editorEdit.apply();
    }

    public static synchronized long getLastCloudSyncTime(Context context) {
        return getSharedPreference(context).getLong(context.getResources().getString(R.string.setting_key_sync_last_cloud_sync_operation_time), 0L);
    }

    public static synchronized void setLastCloudSyncTime(Context context, long time) {
        SharedPreferences.Editor editorEdit = getSharedPreference(context).edit();
        editorEdit.putLong(context.getResources().getString(R.string.setting_key_sync_last_cloud_sync_operation_time), time);
        editorEdit.apply();
    }

    public static void resetSyncLastCheckpoint(Context context) {
        SharedPreferences.Editor editorEdit = getSharedPreference(context).edit();
        editorEdit.remove(context.getResources().getString(R.string.setting_key_sync_last_sync_checkpoint));
        editorEdit.apply();
    }

    public static synchronized String getSyncLastCheckpoint(Context context) {
        return getFullSyncNeededState(context, R.string.setting_key_sync_last_sync_checkpoint);
    }

    public static synchronized void setSyncLastCheckpoint(Context context, String checkpoint) {
        setFullSyncNeededState(context, R.string.setting_key_sync_last_sync_checkpoint, checkpoint);
    }

    public static synchronized String getExplicitFullTrackSync(Context context) {
        return getFullSyncNeededState(context, R.string.setting_key_explicit_full_track_sync);
    }

    public static synchronized void setExplicitFullTrackSync(Context context, String status) {
        setFullSyncNeededState(context, R.string.setting_key_explicit_full_track_sync, status);
    }

    public static synchronized String getSonicRushFullTrackSync(Context context) {
        return getFullSyncNeededState(context, R.string.setting_key_sonic_rush_full_track_sync);
    }

    public static synchronized void setSonicRushFullTrackSync(Context context, String status) {
        setFullSyncNeededState(context, R.string.setting_key_sonic_rush_full_track_sync, status);
    }

    public static synchronized String getPlaylistsFullSync(Context context) {
        return getFullSyncNeededState(context, R.string.setting_key_playlists_full_sync);
    }

    public static synchronized void setPlaylistsFullSync(Context context, String status) {
        setFullSyncNeededState(context, R.string.setting_key_playlists_full_sync, status);
    }

    public static synchronized String getContentCatalogStatusSync(Context context) {
        return getFullSyncNeededState(context, R.string.setting_key_content_catalog_status_sync);
    }

    public static synchronized void setContentCatalogStatusSync(Context context, String status) {
        setFullSyncNeededState(context, R.string.setting_key_content_catalog_status_sync, status);
    }

    public static synchronized String getFullSyncNeededState(Context context, int preferenceKeyId) {
        return getSharedPreference(context).getString(context.getString(preferenceKeyId), null);
    }

    public static synchronized void setFullSyncNeededState(Context context, int preferenceKeyId, String status) {
        SharedPreferences.Editor editorEdit = getSharedPreference(context).edit();
        editorEdit.putString(context.getString(preferenceKeyId), status);
        editorEdit.apply();
    }

    public static void resetLastSyncTime() {
        sLastSyncTime = 0L;
    }

    public static synchronized long getLastSyncTime() {
        return sLastSyncTime;
    }

    private static synchronized void setLastSyncTime(long time) {
        sLastSyncTime = time;
    }

    private static synchronized void setFreshnessExpiry(int expiry) {
        sFreshnessExpiry = expiry;
    }

    public static synchronized void blockSyncing() {
        sBlockSyncing = true;
    }

    public static synchronized void unblockSyncing() {
        sBlockSyncing = false;
    }

    public static synchronized boolean syncBlocked() {
        return sBlockSyncing;
    }

    public static boolean waitForSync(Context context, final int flags) {
        return waitForSync(context, flags, 300L);
    }

    public static boolean waitForSync(Context context, final int flags, final long timeoutSeconds) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            throw new IllegalStateException("waitForSync may not be called from the UI thread!");
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final String strValueOf = String.valueOf(RANDOM.nextLong());
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.library.service.sync.SyncService.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                String stringExtra = intent.getStringExtra("com.amazon.mp3.library.service.sync.EXTRA_BROADCAST_CODE");
                if (!strValueOf.equals(stringExtra)) {
                    Log.debug(SyncService.TAG, "The codes are different, returning. Code: " + strValueOf + ", broadcastCode: " + stringExtra);
                    return;
                }
                int intExtra = intent.getIntExtra("com.amazon.mp3.library.service.sync.EXTRA_SYNC_EVENT_TYPE", 5);
                if (intExtra == 3) {
                    Log.debug(SyncService.TAG, "Sync has failed.");
                    countDownLatch.countDown();
                } else {
                    if (intExtra != 5) {
                        Log.debug(SyncService.TAG, "Sync result is unknown: " + intExtra);
                        return;
                    }
                    Log.debug(SyncService.TAG, "Sync is finished.");
                    countDownLatch.countDown();
                }
            }
        };
        boolean zAwait = false;
        try {
            try {
                context.registerReceiver(broadcastReceiver, new IntentFilter("com.amazon.mp3.library.service.sync.ACTION_SYNC_EVENT"));
                Intent intent = new Intent(context, (Class<?>) SyncService.class);
                intent.putExtra("com.amazon.mp3.library.service.sync.EXTRA_SYNC_FLAGS", flags | 16);
                intent.putExtra("com.amazon.mp3.library.service.sync.EXTRA_BROADCAST_CODE", strValueOf);
                ServiceUtil.startService(context, intent);
                zAwait = countDownLatch.await(timeoutSeconds, TimeUnit.SECONDS);
            } catch (InterruptedException unused) {
                Log.verbose(TAG, "waitForPlaylistSync: interrupted");
            }
            return zAwait;
        } finally {
            unregisterReceiver(context, broadcastReceiver);
        }
    }

    @Override // com.amazon.mp3.service.job.JobSessionService, android.app.Service
    public void onCreate() {
        super.onCreate();
        if (syncBlocked()) {
            stopSelf();
            return;
        }
        sendBroadcast(new Intent("com.amazon.mp3.library.service.sync.ACTION_SYNC_SERVICE_STARTED"));
        this.mPendingCommands = new LinkedList();
        synchronized (SyncService.class) {
            setFreshnessExpiry(AmazonApplication.getConfiguration(getApplicationContext()).getInteger("market_metadata_sync_expiry", 1800000));
        }
        Log.verbose(TAG, "SyncService created");
    }

    @Override // com.amazon.mp3.service.job.JobSessionService, android.app.Service
    public void onDestroy() {
        super.onDestroy();
        setRunning(false);
        sendBroadcast(new Intent("com.amazon.mp3.library.service.sync.ACTION_SYNC_SERVICE_STOPPED"));
        Log.verbose(TAG, "SyncService destroyed");
    }

    @Override // android.app.Service
    public void onStart(Intent intent, int startId) {
        this.mLastStartId = startId;
        if (syncBlocked()) {
            stopSelf();
            return;
        }
        setRunning(true);
        if (this.mJobId == -1) {
            clearChanges();
            this.mJobId = addJob(new SyncJob(intent));
        } else if (intent != null && (intent.getIntExtra("com.amazon.mp3.library.service.sync.EXTRA_SYNC_FLAGS", 13987) & 16) != 0) {
            this.mPendingCommands.add(intent);
        }
        if (this.mJobId == -1) {
            setLastSyncFailedWithNetworkError(false);
            stopSelf(this.mLastStartId);
        }
    }

    @Override // com.amazon.mp3.service.job.JobSessionService
    protected void onJobFinished(long jobId, Job job, int resultCode, Bundle resultBundle) {
        if (jobId == this.mJobId) {
            this.mJobId = -1L;
            WidgetMetrics widgetMetrics = new WidgetMetrics(this);
            if (PlayerWidgetController.isSupported()) {
                widgetMetrics.checkIfShouldSendFlexEvent(WidgetMetrics.WidgetMetricsType.SUPPORTED, true);
                PlayerWidgetController.getInstance().updateWidget();
            } else {
                widgetMetrics.checkIfShouldSendFlexEvent(WidgetMetrics.WidgetMetricsType.SUPPORTED, false);
                widgetMetrics.checkIfShouldSendFlexEvent(WidgetMetrics.WidgetMetricsType.ENABLED, false);
            }
            int finalResult = ((SyncJob) job).getFinalResult();
            if (finalResult != 0) {
                resetLastSyncTime();
            }
            setLastSyncFailedWithNetworkError(finalResult == 2);
            if (!syncBlocked() && !this.mPendingCommands.isEmpty()) {
                if (!startSyncJobIfUserAuthenticated(this.mPendingCommands.peek())) {
                    stopSelf(this.mLastStartId);
                }
                this.mPendingCommands.remove();
            } else {
                if (changesWereMade() && AmazonApplication.getCapabilities().isExternalProviderEnabled()) {
                    ExternalProvider.fireBulkUpdate(this);
                    clearChanges();
                }
                stopSelf(this.mLastStartId);
            }
        }
    }

    private boolean startSyncJobIfUserAuthenticated(Intent intent) {
        if (syncBlocked()) {
            return false;
        }
        this.mJobId = addJob(new SyncJob(intent));
        return true;
    }

    private static void unregisterReceiver(Context context, BroadcastReceiver receiver) {
        try {
            context.unregisterReceiver(receiver);
        } catch (IllegalArgumentException unused) {
            Log.debug(TAG, "IllegalArgumentException thrown when unregistering receiver: " + receiver.getClass().toString());
        } catch (IllegalStateException unused2) {
            Log.debug(TAG, "IllegalStateException thrown when unregistering receiver: " + receiver.getClass().toString());
        }
    }

    private static void refreshOnDemandPlaylists() {
        if (AmazonApplication.getCapabilities().isOnDemandPlaylistsEnabled()) {
            GetOnDemandPlaylistsMetricsUtil.INSTANCE.trackEvent(GetOnDemandPlaylistsMetricsUtil.EventType.EVENT_CLIENT_SYNC_CYCLE);
            GetOnDemandPlaylistsService.INSTANCE.get().fetchAndCacheOnDemandPlaylists();
        }
    }

    private static void clearTracksLikeStatesCacheForFreeTierAndNonSubscribers(final Context context) {
        if (!UserSubscriptionUtil.getUserSubscription().hasSubscription() || UserSubscriptionUtil.isNightwingOnly()) {
            Log.info(TAG, "Clear track like states cache for Free Tier users or non subscribers");
            LikesStateCacheManager.INSTANCE.getInstance(context).clear();
        }
    }
}
