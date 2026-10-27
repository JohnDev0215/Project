package com.amazon.mp3.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automaticimport.AutomaticImportManager;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.cache.likes.LikesStateCacheManager;
import com.amazon.mp3.library.item.DeluxeContentUtil;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.provider.source.nowplaying.NowPlayingManager;
import com.amazon.mp3.library.provider.source.nowplaying.RecentItemsManager;
import com.amazon.mp3.library.service.sync.PlaylistSyncHelper;
import com.amazon.mp3.library.service.sync.PlaylistSyncOperation;
import com.amazon.mp3.library.service.sync.SyncOperation;
import com.amazon.mp3.library.service.sync.SyncService;
import com.amazon.mp3.playback.PlayStateMutationReason;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.playback.service.streaming.StreamCache;
import com.amazon.mp3.playlist.PlaylistConfigurationStorage;
import com.amazon.mp3.playlist.db.PrimePlaylistDatabaseManager;
import com.amazon.mp3.prime.CTAPrimeCache;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedManager;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.service.job.JobSessionService;
import com.amazon.mp3.store.html5.webview.StoreWebView;
import com.amazon.mp3.store.util.ImageCache;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PrimeContentUtil;
import com.amazon.mp3.util.Profiler;
import com.amazon.music.curate.skyfire.local.SyncScheduler;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.player.Player;
import com.amazon.music.media.playback.player.impl.HarleyPlayer;

/* JADX INFO: loaded from: classes4.dex */
public class ClearCacheService extends JobSessionService {
    private static final String TAG = ClearCacheService.class.getSimpleName();
    private static boolean sRunning = false;
    private int mFlags;
    private int mLastStartId;
    long mJobId = -1;
    private final BroadcastReceiver mSyncServiceReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.service.ClearCacheService.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            ClearCacheService.this.unregisterReceiver(this);
            ClearCacheService clearCacheService = ClearCacheService.this;
            clearCacheService.mJobId = clearCacheService.addJob(new ClearCacheJob(clearCacheService.mFlags));
        }
    };

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static void startClearCache(Context context, boolean userChanged) {
        int i;
        if (userChanged) {
            Log.info(TAG, "Adding FLAG_CLEAR_CIRRUS_DATABASE, FLAG_CLEAR_CIRRUS_DATABASE_ACCESS, FLAG_CLEAR_PRIME_CONTENT, FLAG_CLEAR_TRACK_LIKE_STATE_CACHE to clear cache job.");
            i = -65793;
        } else {
            i = -108809;
        }
        startClearCache(context, i);
    }

    public static void startClearCache(Context context, int flags) {
        setRunning(true);
        Intent intent = new Intent(context, (Class<?>) ClearCacheService.class);
        intent.putExtra("com.amazon.mp3.activity.EXTRA_CLEAR_FLAGS", flags);
        ServiceUtil.startService(context, intent);
    }

    public static boolean isRunning() {
        return sRunning;
    }

    private static synchronized void setRunning(boolean running) {
        sRunning = running;
    }

    @Override // com.amazon.mp3.service.job.JobSessionService, android.app.Service
    public void onCreate() {
        super.onCreate();
        setRunning(true);
        sendBroadcast(new Intent("com.amazon.mp3.ACTION_CACHE_CLEARING"));
    }

    @Override // android.app.Service
    public void onStart(Intent intent, int startId) {
        String str = TAG;
        Log.info(str, "Starting clear cache");
        super.onStart(intent, startId);
        this.mLastStartId = startId;
        this.mFlags = intent.getIntExtra("com.amazon.mp3.activity.EXTRA_CLEAR_FLAGS", -108809);
        if (this.mJobId == -1) {
            SyncService.blockSyncing();
            if (SyncService.isRunning()) {
                Log.info(str, "Clear cache waiting for sync to complete");
                registerReceiver(this.mSyncServiceReceiver, new IntentFilter("com.amazon.mp3.library.service.sync.ACTION_SYNC_SERVICE_STOPPED"));
            } else {
                Log.info(str, "Starting clear cache");
                this.mJobId = addJob(new ClearCacheJob(this.mFlags));
            }
        }
    }

    @Override // com.amazon.mp3.service.job.JobSessionService
    protected void onJobFinished(long jobId, Job job, int resultCode, Bundle resultBundle) {
        if (jobId == this.mJobId) {
            Log.info(TAG, "clear cache finished");
            this.mJobId = -1L;
            stopSelf(this.mLastStartId);
        }
    }

    public static void clearCacheOnCurrentThread(final Context c, int flags) {
        try {
            Profiler.begin("Clearing cache on current thread");
            setRunning(true);
            c.sendBroadcast(new Intent("com.amazon.mp3.library.activity.nowplaying.clear_and_finish"));
            SharedPreferences.Editor editorEdit = SettingsUtil.getPrefs(c).edit();
            if ((flags & 4096) != 0) {
                Log.info(TAG, "Clearing deluxe content cache");
                DeluxeContentUtil.clearDiskCache();
            }
            if ((flags & 2) != 0) {
                Log.info(TAG, "Clearing store image cache");
                ImageCache.clearDiskCache();
            }
            if ((flags & 4) != 0) {
                Log.info(TAG, "Clearing image cache");
                CacheManager cacheManager = CacheManager.getInstance();
                cacheManager.pause();
                cacheManager.reset();
                cacheManager.clearAllFromDiskCache();
            }
            if ((flags & 1024) != 0) {
                Log.info(TAG, "Clearing web cache");
                AmazonApplication.getDefaultHandler().postAtFrontOfQueue(new Runnable() { // from class: com.amazon.mp3.service.ClearCacheService.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (StoreWebView.instanceCreated()) {
                            StoreWebView.getInstance(c).clearCache(true);
                        }
                    }
                });
            }
            if ((flags & 8) != 0) {
                Log.info(TAG, "Clearing cirrus database");
                try {
                    editorEdit.putLong(getKey(c, R.string.setting_key_similarities_last_sync_time), -1L);
                    editorEdit.putLong(getKey(c, R.string.setting_key_sync_last_updated_time), -1L);
                    editorEdit.putLong(getKey(c, R.string.setting_key_sync_last_local_updated_time), -1L);
                    editorEdit.putLong(getKey(c, R.string.setting_key_sync_last_deletion_check_time), -1L);
                    CirrusDatabase.reset(c, (flags & 2048) != 0);
                    c.getContentResolver().notifyChange(CirrusMediaSource.NOTIFICATION_URI, null);
                    c.getContentResolver().notifyChange(CirrusMediaSource.NOTIFICATION_URI_LOCAL, null);
                    SyncService.resetSyncLastCheckpoint(c);
                    SyncService.resetLastCloudSyncTime(c);
                    SyncService.resetLastSyncTime();
                } catch (Throwable th) {
                    SyncService.resetSyncLastCheckpoint(c);
                    SyncService.resetLastCloudSyncTime(c);
                    SyncService.resetLastSyncTime();
                    throw th;
                }
            }
            if ((flags & 1) != 0) {
                Log.info(TAG, "Clearing streaming cache");
                StreamCache.getInstance(c).clearAll();
                Player localPlayer = Playback.getInstance().getPlaybackConfig().getLocalPlayer(ChildUserUtil.INSTANCE.getMusicRequestIdentityContextToken(c));
                if (localPlayer instanceof HarleyPlayer) {
                    ((HarleyPlayer) localPlayer).clearCache();
                }
            }
            if ((flags & 64) != 0) {
                Log.info(TAG, "Clearing remote configurations");
                AmazonApplication.getConfiguration(c).reloadRemoteEndpoint();
            }
            if ((flags & 128) != 0) {
                Log.info(TAG, "Clearing download preferences");
                editorEdit.putBoolean(getKey(c, R.string.setting_key_cirrus_download_pref), false);
                editorEdit.putBoolean(getKey(c, R.string.setting_key_suppress_post_purchase_dialog), false);
            }
            if ((flags & 256) != 0) {
                Log.info(TAG, "Clearing now playing data");
                MusicTrack currentTrack = NowPlayingManager.getInstance().getCurrentTrack();
                if (currentTrack != null && "cirrus".equals(currentTrack.getSource())) {
                    NowPlayingUtil.clearAndFinishNowPlaying(c, PlayStateMutationReason.CLEAR_CACHE);
                }
                RecentItemsManager.getInstance(c).clearRecentItems();
                RecentlyPlayedManager.getInstance(c).clearAll();
            }
            if ((flags & 16384) != 0) {
                PlaylistSyncHelper.setPlaylistVersionToClear(c);
                clearPlaylistSyncCheckpointAndUpdate(c);
            }
            if ((32768 & flags) != 0) {
                Log.info(TAG, "Removing all PRIME content");
                PrimeContentUtil.removeAllPrimeContent(false);
            }
            if ((flags & 8192) != 0) {
                Log.info(TAG, "Resetting Automatic Import Setting to default value: ENABLED");
                new AutomaticImportManager(c).setAutomaticImportPersistedState(true);
            }
            if (flags == 65536) {
                Log.info(TAG, "Resetting PlaymodeEligibility for all tables to NULL");
                CirrusDatabase.resetPlaymodeEligibility(c);
            } else {
                new CTAPrimeCache(c).clearAll();
                new PrimePlaylistDatabaseManager(c).clearAllBrowsePlaylists();
            }
            if ((131072 & flags) != 0) {
                Log.info(TAG, "Resetting track like states cache");
                LikesStateCacheManager.INSTANCE.getInstance(c).clear();
            }
            SyncScheduler.resetContextMappingTableLastSync();
            editorEdit.apply();
        } finally {
            Intent intent = new Intent("com.amazon.mp3.ACTION_CACHE_CLEARED");
            intent.putExtra("com.amazon.mp3.activity.EXTRA_CLEAR_FLAGS", flags);
            c.sendBroadcast(intent);
            SyncService.unblockSyncing();
            CacheManager.getInstance().unpause();
            setRunning(false);
            Profiler.end();
        }
    }

    private static void clearPlaylistSyncCheckpointAndUpdate(final Context context) {
        Log.info(TAG, "Clearing last playlist sync checkpoint");
        PlaylistConfigurationStorage.get().removePlaylistCheckpoint();
        try {
            new PlaylistSyncOperation(context, CirrusDatabase.getWritableDatabase(context), true).sync();
        } catch (SyncOperation.AccountStatusException e) {
            Log.error(TAG, "Error during playlist sync operation", e);
        }
    }

    public static String getKey(Context c, int id) {
        return c.getString(id);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void unregisterReceiver(BroadcastReceiver receiver) {
        try {
            super.unregisterReceiver(receiver);
        } catch (IllegalArgumentException unused) {
            Log.debug(TAG, "IllegalArgumentException thrown when unregistering receiver");
        } catch (IllegalStateException unused2) {
            Log.debug(TAG, "IllegalStateException thrown when unregistering receiver");
        }
    }

    public static class ClearCacheJob extends Job {
        private final int mFlags;

        @Override // com.amazon.mp3.service.job.Job
        public void cancel() {
        }

        @Override // com.amazon.mp3.service.job.Job
        public boolean restartable() {
            return false;
        }

        ClearCacheJob(int flags) {
            this.mFlags = flags;
        }

        @Override // com.amazon.mp3.service.job.Job
        public int run() throws Exception {
            ClearCacheService.clearCacheOnCurrentThread(getContext(), this.mFlags);
            return 1;
        }
    }
}
