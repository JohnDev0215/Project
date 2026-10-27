package com.amazon.music.media.playback.impl;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.media.MediaBrowserCompat;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.media.MediaBrowserServiceCompat;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.OnLibraryChangedListener;
import com.amazon.music.media.playback.OnMetadataChangedListener;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.media.playback.config.LibraryAccessProvider;
import com.amazon.music.media.playback.config.MediaNotificationFactory;
import com.amazon.music.media.playback.config.PlaybackConfig;
import com.amazon.music.media.playback.featuregate.Feature;
import com.amazon.music.media.playback.metrics.mts.ServiceDeathMonitor;
import com.amazon.music.media.playback.util.AndroidUtils;
import com.amazon.music.media.playback.util.RateLimiter;
import com.amazon.music.media.playback.util.StrictMode;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.visualon.OSMPUtils.voOSType;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class PlaybackService extends MediaBrowserServiceCompat {
    private static PlaybackService instance;
    private static final Logger logger = LoggerFactory.getLogger(PlaybackService.class.getSimpleName());
    private boolean hasEverBeenInForeground;
    private LibraryAccessProvider library;
    private MediaItem mediaItem;
    private int notificationId;
    private PlayStatus playStatus;
    private PlaybackController playbackController;
    private PendingIntent removeNotificationIntent;
    private ServiceDeathMonitor serviceDeathMonitor;
    private boolean serviceInForeground;
    private boolean showingNotification;
    private PendingIntent thumbsDownIntent;
    private PendingIntent thumbsUpIntent;
    private PendingIntent toggleRepeatModeIntent;
    private PendingIntent toggleShuffleModeIntent;
    private int rating = -1;
    private final RateLimiter rateLimiter = new RateLimiter(new Runnable() { // from class: com.amazon.music.media.playback.impl.PlaybackService.1
        @Override // java.lang.Runnable
        public void run() {
            PlaybackService.this.updateNotification(false);
        }
    }, 250, 0);
    private OnPlayStateChangedListener onPlayStateChangedListener = new OnPlayStateChangedListener() { // from class: com.amazon.music.media.playback.impl.PlaybackService.2
        @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
        public void onPlayStateChanged() {
            PlaybackService.this.serviceDeathMonitor.onPlayStateChanged();
            MediaItem currentMediaItem = PlaybackService.this.playbackController.getCurrentMediaItem();
            PlayStatus playStatus = PlaybackService.this.playbackController.getPlayStatus();
            if ((currentMediaItem == null || currentMediaItem.equals(PlaybackService.this.mediaItem)) && PlaybackService.this.playStatus != null && PlaybackService.this.playStatus.willPlay() == playStatus.willPlay() && PlaybackService.this.rating == PlaybackService.this.playbackController.getCurrentItemRating()) {
                return;
            }
            PlaybackService.this.updateNotification(true);
        }
    };
    private OnLibraryChangedListener onLibraryChangedListener = new OnLibraryChangedListener() { // from class: com.amazon.music.media.playback.impl.PlaybackService.3
        @Override // com.amazon.music.media.playback.OnLibraryChangedListener
        public void OnLibraryChanged() {
            PlaybackService.this.updateNotification(true);
        }
    };
    private OnMetadataChangedListener onMetadataChangedListener = new OnMetadataChangedListener() { // from class: com.amazon.music.media.playback.impl.PlaybackService.4
        @Override // com.amazon.music.media.playback.OnMetadataChangedListener
        public void onMetadataChanged(MediaItem mediaItem) {
            if (mediaItem == PlaybackService.this.mediaItem) {
                PlaybackService.this.updateNotification(true);
            }
        }

        @Override // com.amazon.music.media.playback.OnMetadataChangedListener
        public void onMediaCollectionInfoChanged(MediaCollectionInfo mediaCollectionInfo) {
            PlaybackService.this.updateNotification(true);
        }
    };
    private AndroidUtils.ForegroundListener foregroundListener = new AndroidUtils.ForegroundListener() { // from class: com.amazon.music.media.playback.impl.PlaybackService.5
        @Override // com.amazon.music.media.playback.util.AndroidUtils.ForegroundListener
        public void onApplicationInForegroundChanged(boolean z) {
            if (z) {
                PlaybackService.this.serviceDeathMonitor.onAppInForeground();
            } else {
                PlaybackService.this.serviceDeathMonitor.onAppInBackground();
            }
            boolean zShouldShowNotificationInForeground = PlaybackService.this.playbackController.getPlayback().getPlaybackConfig().shouldShowNotificationInForeground();
            if (z && zShouldShowNotificationInForeground) {
                return;
            }
            PlaybackService.this.updateNotification(true);
        }
    };
    private MediaNotificationFactory.ForceUpdateHandler forceUpdateHandler = new MediaNotificationFactory.ForceUpdateHandler() { // from class: com.amazon.music.media.playback.impl.PlaybackService.6
        @Override // com.amazon.music.media.playback.config.MediaNotificationFactory.ForceUpdateHandler
        public void forceUpdate() {
            ThreadUtils.runOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.impl.PlaybackService.6.1
                @Override // java.lang.Runnable
                public void run() {
                    PlaybackService.this.updateNotification(true);
                }
            });
        }
    };
    private MediaNotificationFactory.MediaIntentProvider mediaIntentProvider = new MediaNotificationFactory.MediaIntentProvider() { // from class: com.amazon.music.media.playback.impl.PlaybackService.7
    };

    @Override // androidx.media.MediaBrowserServiceCompat, android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static void startIfNeeded(PlaybackConfig playbackConfig) {
        if (instance != null) {
            return;
        }
        PlaybackController playbackController = playbackConfig.getPlayback().getPlaybackController(ControlSource.NONE);
        if (playbackController.getPlayStatus().willPlay()) {
            if (playbackConfig.shouldShowNotificationInForeground() || !AndroidUtils.isApplicationInForeground()) {
                MediaItem currentMediaItem = playbackController.getCurrentMediaItem();
                if (currentMediaItem == null || currentMediaItem.getType() != MediaItem.Type.PODCAST_CONTENT) {
                    Context context = playbackConfig.getContext();
                    Intent intent = new Intent().setClass(context, PlaybackService.class);
                    logger.info("starting PlaybackService");
                    if (Build.VERSION.SDK_INT >= 26) {
                        context.startForegroundService(intent);
                    } else {
                        context.startService(intent);
                    }
                }
            }
        }
    }

    private PendingIntent getBroadcastIntent(Context context, int i, Intent intent) {
        return PendingIntent.getBroadcast(context, i, intent, voOSType.VOOSMP_SRC_FFAUDIO_MIDI);
    }

    @Override // androidx.media.MediaBrowserServiceCompat
    @Nullable
    public MediaBrowserServiceCompat.BrowserRoot onGetRoot(@NonNull String str, int i, @Nullable Bundle bundle) {
        return new MediaBrowserServiceCompat.BrowserRoot("Amazon Music", null);
    }

    @Override // androidx.media.MediaBrowserServiceCompat
    public void onLoadChildren(@NonNull String str, @NonNull MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result) {
        result.sendResult(null);
    }

    @Override // androidx.media.MediaBrowserServiceCompat, android.app.Service
    public void onCreate() {
        Logger logger2 = logger;
        logger2.info("onCreate()");
        super.onCreate();
        instance = this;
        ServiceDeathMonitor serviceDeathMonitor = new ServiceDeathMonitor(Playback.getInstance());
        this.serviceDeathMonitor = serviceDeathMonitor;
        serviceDeathMonitor.onServiceCreate();
        PlaybackController playbackController = Playback.getInstance().getPlaybackController(ControlSource.EXTERNAL);
        this.playbackController = playbackController;
        playbackController.addOnPlayStateChangedListener(this.onPlayStateChangedListener);
        this.playbackController.addOnMetadataChangedListener(this.onMetadataChangedListener);
        AndroidUtils.addForegroundListener(this.foregroundListener);
        Intent intent = new Intent(getApplicationContext(), (Class<?>) MediaButtonReceiver.class);
        intent.setAction("action_removed_notification");
        this.removeNotificationIntent = getBroadcastIntent(getApplicationContext(), 0, intent);
        Intent intent2 = new Intent(getApplicationContext(), (Class<?>) MediaButtonReceiver.class);
        intent2.setAction("action_thumbs_up");
        this.thumbsUpIntent = getBroadcastIntent(getApplicationContext(), 0, intent2);
        Intent intent3 = new Intent(getApplicationContext(), (Class<?>) MediaButtonReceiver.class);
        intent3.setAction("action_thumbs_down");
        this.thumbsDownIntent = getBroadcastIntent(getApplicationContext(), 0, intent3);
        Intent intent4 = new Intent(getApplicationContext(), (Class<?>) MediaButtonReceiver.class);
        intent4.setAction("action_toggle_repeat_mode");
        this.toggleRepeatModeIntent = getBroadcastIntent(getApplicationContext(), 0, intent4);
        Intent intent5 = new Intent(getApplicationContext(), (Class<?>) MediaButtonReceiver.class);
        intent5.setAction("action_toggle_shuffle_mode");
        this.toggleShuffleModeIntent = getBroadcastIntent(getApplicationContext(), 0, intent5);
        PlaybackConfig playbackConfig = this.playbackController.getPlayback().getPlaybackConfig();
        LibraryAccessProvider libraryAccessProvider = playbackConfig.getLibraryAccessProvider();
        this.library = libraryAccessProvider;
        libraryAccessProvider.addOnLibraryChangedListener(this.onLibraryChangedListener);
        MediaNotificationFactory mediaNotificationFactory = playbackConfig.getMediaNotificationFactory();
        if (mediaNotificationFactory == null) {
            StrictMode.crashIfStrict("You must set the MediaNotificationFactory of the PlaybackConfig in Application.onCreate()!");
        } else {
            NotificationManagerCompat.from(this).cancel(mediaNotificationFactory.getNotificationId());
            logger2.info("startForeground with notification");
            startForeground(this.notificationId, createNotification(mediaNotificationFactory));
        }
        updateNotification(false);
    }

    @Override // android.app.Service
    public void onDestroy() {
        logger.info("onDestroy()");
        super.onDestroy();
        instance = null;
        this.serviceDeathMonitor.onServiceDestroy();
        NotificationManagerCompat.from(this).cancel(this.notificationId);
        this.playbackController.removeOnPlayStateChangedListener(this.onPlayStateChangedListener);
        this.playbackController.removeOnMetadataChangedListener(this.onMetadataChangedListener);
        this.library.removeOnLibraryChangedListener(this.onLibraryChangedListener);
        AndroidUtils.removeForegroundListener(this.foregroundListener);
        this.playbackController.getPlayback().getMediaSession();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        Logger logger2 = logger;
        logger2.info("onStartCommand()");
        MediaNotificationFactory mediaNotificationFactory = this.playbackController.getPlayback().getPlaybackConfig().getMediaNotificationFactory();
        this.mediaItem = this.playbackController.getCurrentMediaItem();
        if (mediaNotificationFactory == null) {
            return 2;
        }
        logger2.info("startForeground with notification");
        startForeground(this.notificationId, createNotification(mediaNotificationFactory));
        MediaItem mediaItem = this.mediaItem;
        if (mediaItem == null || mediaItem.getType() != MediaItem.Type.PODCAST_CONTENT) {
            return 2;
        }
        stopForeground(true);
        return 2;
    }

    @Override // android.app.Service
    public void onTaskRemoved(Intent intent) {
        if (this.playbackController.shouldPauseWhenTaskRemoved()) {
            this.playbackController.pause();
        }
        stopSelf();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateNotification(boolean z) {
        this.mediaItem = this.playbackController.getCurrentMediaItem();
        this.playStatus = this.playbackController.getPlayStatus();
        this.rating = this.playbackController.getCurrentItemRating();
        PlaybackConfig playbackConfig = this.playbackController.getPlayback().getPlaybackConfig();
        boolean zShouldShowNotificationInForeground = playbackConfig.shouldShowNotificationInForeground();
        MediaNotificationFactory mediaNotificationFactory = playbackConfig.getMediaNotificationFactory();
        if (mediaNotificationFactory == null) {
            return;
        }
        mediaNotificationFactory.setForceUpdateHandler(this.forceUpdateHandler);
        mediaNotificationFactory.setMediaIntentProvider(this.mediaIntentProvider);
        boolean z2 = this.serviceInForeground;
        boolean z3 = this.showingNotification;
        boolean z4 = ((z3 || this.playStatus.willPlay()) && (zShouldShowNotificationInForeground || !AndroidUtils.isApplicationInForeground()) && this.playbackController.canPlay()) || !this.hasEverBeenInForeground;
        this.showingNotification = z4;
        boolean z5 = (z4 && this.playStatus.willPlay()) || !this.hasEverBeenInForeground;
        this.serviceInForeground = z5;
        if (z5 && !z2) {
            this.notificationId = mediaNotificationFactory.getNotificationId();
            this.serviceDeathMonitor.onServiceInForeground();
            if (Feature.start_foreground_on_update_notification.isEnabled()) {
                logger.info("startForeground with notification");
                startForeground(this.notificationId, createNotification(mediaNotificationFactory));
            } else {
                logger.info("updating notification with NotificationManagerCompat");
                NotificationManagerCompat.from(this).notify(this.notificationId, createNotification(mediaNotificationFactory));
            }
            this.rateLimiter.onUpdated();
            if (!this.hasEverBeenInForeground) {
                this.hasEverBeenInForeground = true;
                this.rateLimiter.onDataChanged(mediaNotificationFactory.isAllDataReady(this.playbackController));
            }
        } else if (z2 && !z5) {
            this.serviceDeathMonitor.onServiceInBackground();
            if (Build.VERSION.SDK_INT < 26) {
                stopForeground(!this.showingNotification);
                logger.info("stopForeground()");
            }
            if (this.showingNotification) {
                NotificationManagerCompat.from(this).notify(this.notificationId, createNotification(mediaNotificationFactory));
                this.rateLimiter.onUpdated();
            }
        } else if (z3 && !this.showingNotification) {
            NotificationManagerCompat.from(this).cancel(this.notificationId);
        } else if (z3 && z) {
            this.rateLimiter.onDataChanged(mediaNotificationFactory.isAllDataReady(this.playbackController));
        } else if (z3) {
            NotificationManagerCompat.from(this).notify(this.notificationId, createNotification(mediaNotificationFactory));
            this.rateLimiter.onUpdated();
        }
        MediaItem mediaItem = this.mediaItem;
        if (mediaItem == null || mediaItem.getType() != MediaItem.Type.PODCAST_CONTENT) {
            return;
        }
        NotificationManagerCompat.from(this).cancel(this.notificationId);
        stopForeground(true);
    }

    private Notification createNotification(MediaNotificationFactory mediaNotificationFactory) {
        NotificationCompat.Builder builderCreateNotification = mediaNotificationFactory.createNotification(this.playbackController);
        builderCreateNotification.setDeleteIntent(this.removeNotificationIntent);
        builderCreateNotification.setLocalOnly(true);
        builderCreateNotification.setOngoing(this.serviceInForeground);
        return builderCreateNotification.build();
    }
}
