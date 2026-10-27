package com.amazon.podcast.media.playback;

import Podcast.PlaybackInterface.v1_0.MediaMetadataElement;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.media.MediaBrowserServiceCompat;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$string;
import com.visualon.OSMPUtils.voMimeTypes;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class MediaBrowserService extends MediaBrowserServiceCompat implements PodcastPlayerCallback {
    private static final String TAG = MediaBrowserService.class.getSimpleName();
    private AudioFocus audioFocus;
    private BroadcastReceiver bluetoothActionReceiver;
    private Context context;
    private boolean hasPlayerStarted;
    private boolean isRunningAsForeground;
    private MediaMetadataCompat.Builder mediaMetadataBuilder;
    private MediaSessionCompat mediaSession;
    private MediaSessionCallback mediaSessionCallback;
    private Notifications notifications;
    private Playback playback;
    private PlaybackStateCompat.Builder playbackStateBuilder;
    private PodcastPlaybackController player;
    private SleepTimer sleepTimer;

    @Override // android.app.Service
    public int onStartCommand(final Intent intent, final int flags, final int startId) {
        return 2;
    }

    @Override // androidx.media.MediaBrowserServiceCompat, android.app.Service
    public void onCreate() {
        super.onCreate();
        this.context = getApplicationContext();
        this.playback = Podcast.getPlayback();
        Podcast.setHarleyEnabled();
        this.hasPlayerStarted = false;
        this.mediaSession = new MediaSessionCompat(this.context, TAG);
        this.playbackStateBuilder = new PlaybackStateCompat.Builder();
        this.mediaMetadataBuilder = new MediaMetadataCompat.Builder();
        initMediaSession();
        BluetoothActionsReceiver bluetoothActionsReceiver = new BluetoothActionsReceiver();
        this.bluetoothActionReceiver = bluetoothActionsReceiver;
        registerReceiver(bluetoothActionsReceiver, new IntentFilter("android.bluetooth.device.action.ACL_DISCONNECTED"));
        registerReceiver(this.bluetoothActionReceiver, new IntentFilter("android.bluetooth.device.action.ACL_DISCONNECT_REQUESTED"));
        registerReceiver(this.bluetoothActionReceiver, new IntentFilter("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED"));
        this.sleepTimer = new SleepTimer();
    }

    private void initMediaSession() {
        initPodcastPlayer();
        AudioFocus audioFocus = new AudioFocus(this.player, (AudioManager) getSystemService(voMimeTypes.VOBASE_TYPE_AUDIO), this.mediaSession);
        this.audioFocus = audioFocus;
        this.mediaSessionCallback = new MediaSessionCallback(this.playback, this.player, audioFocus);
        setSessionToken(this.mediaSession.getSessionToken());
        this.mediaSession.setFlags(3);
        this.mediaSession.setCallback(this.mediaSessionCallback);
        setMediaSessionActions();
    }

    @Override // com.amazon.podcast.media.playback.PodcastPlayerCallback
    public void onMediaBuffered(final long duration) {
        this.playback.setPlaybackDuration(duration);
    }

    @Override // com.amazon.podcast.media.playback.PodcastPlayerCallback
    public void onPlaybackStarted() {
        if (!this.mediaSession.isActive()) {
            this.mediaSession.setActive(true);
        }
        startService(this.playback.getMedia().getMediaMetadataElement());
    }

    private void startService(MediaMetadataElement mediaMetadata) {
        if (!this.playback.getPlaybackCapabilities().isLockScreenEnabled() || this.isRunningAsForeground) {
            return;
        }
        int i = Build.VERSION.SDK_INT;
        if (i >= 31) {
            if (isApplicationForeground()) {
                startForegroundService(new Intent(this.context, (Class<?>) MediaBrowserService.class));
                startNotification(mediaMetadata);
                return;
            }
            return;
        }
        if (i >= 26) {
            startForegroundService(new Intent(this.context, (Class<?>) MediaBrowserService.class));
        } else {
            startService(new Intent(this.context, (Class<?>) MediaBrowserService.class));
        }
        startNotification(mediaMetadata);
    }

    private void startNotification(final MediaMetadataElement mediaMetadata) {
        if (this.notifications == null) {
            this.notifications = new Notifications(this.context, this.playback, this.mediaSession);
        }
        startForeground(this.notifications.getOngoingNotificationId(), this.notifications.getNotification(mediaMetadata, null));
        this.isRunningAsForeground = true;
    }

    @Override // com.amazon.podcast.media.playback.PodcastPlayerCallback
    public void onPlaybackPaused() {
        stopForegroundServiceAndReset(false);
    }

    @Override // com.amazon.podcast.media.playback.PodcastPlayerCallback
    public void onPlaybackCleared() {
        this.mediaSession.setActive(false);
        this.audioFocus.abandonAudioFocus();
        stopForegroundServiceAndReset(true);
        stopSelf();
    }

    @Override // com.amazon.podcast.media.playback.PodcastPlayerCallback
    public void onPlaybackRemovedFromLockScreen() {
        Notifications notifications = this.notifications;
        if (notifications == null) {
            return;
        }
        notifications.clearNotifications();
        this.notifications = null;
    }

    @Override // androidx.media.MediaBrowserServiceCompat
    @Nullable
    public MediaBrowserServiceCompat.BrowserRoot onGetRoot(@NonNull final String packageName, final int clientUid, @Nullable final Bundle bundle) {
        return new MediaBrowserServiceCompat.BrowserRoot(getString(R$string.podcast_media_browser_service_identifier), null);
    }

    @Override // com.amazon.podcast.media.playback.PodcastPlayerCallback
    public void onPlaybackPositionChanged(final long position) {
        this.playback.setPlaybackPosition(position);
    }

    @Override // com.amazon.podcast.media.playback.PodcastPlayerCallback
    public void onBufferedPositionChanged(final long position) {
        this.playback.setBufferedPosition(position);
    }

    @Override // com.amazon.podcast.media.playback.PodcastPlayerCallback
    public void onHeartbeatPositionChanged(final long position) {
        this.playback.heartbeat(position);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.bluetoothActionReceiver);
        this.mediaSession.release();
        this.player.removeCallback();
        Notifications notifications = this.notifications;
        if (notifications != null) {
            notifications.clearNotifications();
        }
        this.notifications = null;
    }

    @Override // android.app.Service
    public void onTaskRemoved(final Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        this.playback.pause();
        Notifications notifications = this.notifications;
        if (notifications != null) {
            notifications.clearNotifications();
        }
        this.notifications = null;
    }

    @Override // androidx.media.MediaBrowserServiceCompat
    public void onLoadChildren(@NonNull final String parentMediaId, @NonNull final MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result) {
        result.sendResult(Collections.emptyList());
    }

    @Override // androidx.media.MediaBrowserServiceCompat
    public void onCustomAction(final String action, final Bundle extras, final MediaBrowserServiceCompat.Result<Bundle> result) {
        if (action.equals("setSleepTimer")) {
            long j = extras.getLong("timerDuration", 0L);
            if (j >= 0) {
                this.sleepTimer.startTimer(j);
            }
        } else if (action.equals("stopSleepTimer")) {
            this.sleepTimer.stopTimer();
        }
        result.sendResult(extras);
    }

    private void setMediaSessionActions() {
        this.playbackStateBuilder.setActions(1543L);
        this.mediaSession.setPlaybackState(this.playbackStateBuilder.build());
    }

    private void stopForegroundServiceAndReset(final boolean isRemoveNotification) {
        Notifications notifications;
        this.isRunningAsForeground = false;
        stopForeground(isRemoveNotification);
        if (!isRemoveNotification || (notifications = this.notifications) == null) {
            return;
        }
        notifications.clearNotifications();
        this.notifications = null;
    }

    private void initPodcastPlayer() {
        if (this.hasPlayerStarted) {
            return;
        }
        if (Podcast.isHarleyEnabled()) {
            initHarleyPlayer();
        } else {
            initExoplayer();
        }
        this.hasPlayerStarted = true;
    }

    private void initHarleyPlayer() {
        this.player = new HarleyPodcastPlayer(this.context, this, this.mediaSession, this.playback.getPlaybackSpeed().getValue(), this.playbackStateBuilder, this.mediaMetadataBuilder);
    }

    private void initExoplayer() {
        this.player = new PodcastPlayer(this.context, this, this.mediaSession, this.playback.getPlaybackSpeed().getValue(), this.playbackStateBuilder, this.mediaMetadataBuilder);
    }

    private boolean isApplicationForeground() {
        ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
        ActivityManager.getMyMemoryState(runningAppProcessInfo);
        int i = runningAppProcessInfo.importance;
        return i == 100 || i == 200;
    }
}
