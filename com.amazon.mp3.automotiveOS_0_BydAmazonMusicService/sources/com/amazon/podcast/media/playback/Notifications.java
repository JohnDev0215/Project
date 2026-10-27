package com.amazon.podcast.media.playback;

import Podcast.PlaybackInterface.v1_0.MediaMetadataElement;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.media.session.MediaSession;
import android.os.Build;
import android.support.v4.media.session.MediaSessionCompat;
import androidx.core.app.NotificationCompat;
import androidx.media.app.NotificationCompat;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$string;
import com.amazon.podcast.cast.ChromecastMediaProvider;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import com.amazon.podcast.media.playback.Media;
import com.amazon.podcast.media.playback.Playback;
import com.amazon.podcast.nowplayingstage.NowPlayingActivity;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
final class Notifications implements Media.MediaMetadataCallback, Playback.StateCallback {
    private Target artworkTarget;
    private final Context context;
    private final MediaSessionCompat mediaSession;
    private NotificationManager notificationManager;
    private BroadcastReceiver notificationReceiver;
    private final Playback playback;
    private final Logger logger = LoggerFactory.getLogger("PodcastPlaybackNotification");
    private Bitmap persistentNotificationArtwork = null;

    public int getOngoingNotificationId() {
        return 9475792;
    }

    public Notifications(final Context context, final Playback playback, final MediaSessionCompat mediaSession) {
        this.playback = playback;
        this.context = context;
        this.mediaSession = mediaSession;
        this.notificationManager = (NotificationManager) context.getSystemService("notification");
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel("amazon_music_podcast_playback_notification", "Podcast Playback Controls", 2);
            notificationChannel.setShowBadge(false);
            this.notificationManager.createNotificationChannel(notificationChannel);
            notificationChannel.setLockscreenVisibility(1);
        }
        this.notificationReceiver = new NotificationsReceiver(playback);
        bindArtwork();
        playback.getMedia().addMediaMetadataCallback(this);
        playback.addStateCallback(this);
        context.registerReceiver(this.notificationReceiver, new IntentFilter("notification_actions"));
    }

    public Notification getNotification(final MediaMetadataElement mediaMetadata, final Bitmap artwork) {
        if (artwork != null) {
            this.persistentNotificationArtwork = artwork;
        }
        String title = mediaMetadata == null ? "" : mediaMetadata.getTitle();
        String subtitle = mediaMetadata != null ? mediaMetadata.getSubtitle() : "";
        if (artwork == null && mediaMetadata != null) {
            Picasso.get().load(mediaMetadata.getArtwork()).placeholder(R$drawable.artwork_placeholder).into(this.artworkTarget);
        }
        if (Build.VERSION.SDK_INT >= 26) {
            return buildHigherSdkNotification(title, subtitle);
        }
        return buildLowerSdkNotification(title, subtitle);
    }

    public void clearNotifications() {
        this.playback.removeStateCallback(this);
        this.playback.getMedia().removeMediaMetadataCallback(this);
        if (Build.VERSION.SDK_INT >= 26) {
            this.notificationManager.cancel(9475792);
        }
        this.notificationManager.cancelAll();
        this.context.unregisterReceiver(this.notificationReceiver);
    }

    @Override // com.amazon.podcast.media.playback.Media.MediaMetadataCallback
    public void onMediaMetadataChange(final MediaMetadataElement metadataElement) {
        this.notificationManager.notify(9475792, getNotification(metadataElement, null));
    }

    @Override // com.amazon.podcast.media.playback.Playback.StateCallback
    public void onPlaybackStateChange(final int state) {
        this.notificationManager.notify(9475792, getNotification(this.playback.getMedia().getMediaMetadataElement(), this.persistentNotificationArtwork));
    }

    private PendingIntent getNotificationIntent() {
        Intent intent;
        if (PodcastFeatureGating.PODCAST_YA_IMMERSE_NPS.isEnabled()) {
            intent = new Intent(this.context, (Class<?>) NowPlayingActivity.class);
        } else {
            intent = new Intent(this.context, (Class<?>) com.amazon.podcast.nowplaying.NowPlayingActivity.class);
        }
        return PendingIntent.getActivity(this.context, 99, intent, 201326592);
    }

    private Notification buildHigherSdkNotification(final String title, final String subtitle) {
        if (Build.VERSION.SDK_INT < 26) {
            return null;
        }
        return new Notification.Builder(this.context, "amazon_music_podcast_playback_notification").setContentTitle(title).setContentText(subtitle).setSmallIcon(R$drawable.ic_amazonmusic_sm_logo_white).setLargeIcon(this.persistentNotificationArtwork).addAction(new Notification.Action.Builder(Icon.createWithResource(this.context, R$drawable.ic_playback_rewind_notification), this.context.getResources().getString(R$string.podcast_rewind_label), getRewindPendingIntent()).build()).addAction(new Notification.Action.Builder(Icon.createWithResource(this.context, getPlayPauseResourceId()), getPlayPauseLabel(), getPlayPauseIntent()).build()).addAction(new Notification.Action.Builder(Icon.createWithResource(this.context, R$drawable.ic_playback_fast_forward_notification), this.context.getResources().getString(R$string.podcast_fast_forward_label), getFastForwardPendingIntent()).build()).setStyle(new Notification.MediaStyle().setShowActionsInCompactView(0, 1, 2).setMediaSession((MediaSession.Token) this.mediaSession.getSessionToken().getToken())).setVisibility(1).setCategory(NotificationCompat.CATEGORY_TRANSPORT).setContentIntent(getNotificationIntent()).setOngoing(this.playback.getPlaybackState() == 3).build();
    }

    private Notification buildLowerSdkNotification(final String title, final String subtitle) {
        return new NotificationCompat.Builder(this.context, "amazon_music_podcast_playback_notification").setContentTitle(title).setContentText(subtitle).setSmallIcon(R$drawable.ic_amazonmusic_sm_logo_white).setLargeIcon(this.persistentNotificationArtwork).addAction(R$drawable.ic_playback_rewind_notification, this.context.getResources().getString(R$string.podcast_rewind_label), getRewindPendingIntent()).addAction(getPlayPauseResourceId(), getPlayPauseLabel(), getPlayPauseIntent()).addAction(R$drawable.ic_playback_fast_forward_notification, this.context.getResources().getString(R$string.podcast_fast_forward_label), getFastForwardPendingIntent()).setStyle(new NotificationCompat.MediaStyle().setShowActionsInCompactView(0, 1, 2).setMediaSession(this.mediaSession.getSessionToken())).setPriority(1).setCategory(androidx.core.app.NotificationCompat.CATEGORY_TRANSPORT).setContentIntent(getNotificationIntent()).setOngoing(this.playback.getPlaybackState() == 3).build();
    }

    private void bindArtwork() {
        this.artworkTarget = new Target() { // from class: com.amazon.podcast.media.playback.Notifications.1
            @Override // com.squareup.picasso.Target
            public void onPrepareLoad(final Drawable drawable) {
            }

            @Override // com.squareup.picasso.Target
            public void onBitmapLoaded(final Bitmap bitmap, final Picasso.LoadedFrom loadedFrom) {
                NotificationManager notificationManager = Notifications.this.notificationManager;
                Notifications notifications = Notifications.this;
                notificationManager.notify(9475792, notifications.getNotification(notifications.playback.getMedia().getMediaMetadataElement(), bitmap));
            }

            @Override // com.squareup.picasso.Target
            public void onBitmapFailed(final Exception e, final Drawable drawable) {
                Notifications.this.logger.error("Error loading artwork for notification: " + e.getMessage());
            }
        };
    }

    private PendingIntent getRewindPendingIntent() {
        return PendingIntent.getBroadcast(this.context, 0, new Intent(this.context, (Class<?>) NotificationActionReceiver.class).setAction("rewind"), 201326592);
    }

    private PendingIntent getPlayPauseIntent() {
        ChromecastMediaProvider.setPlaybackOnChromecastPaused(false);
        return PendingIntent.getBroadcast(this.context, 0, new Intent(this.context, (Class<?>) NotificationActionReceiver.class).setAction("play_pause"), 201326592);
    }

    private PendingIntent getFastForwardPendingIntent() {
        return PendingIntent.getBroadcast(this.context, 0, new Intent(this.context, (Class<?>) NotificationActionReceiver.class).setAction("fast_forward"), 201326592);
    }

    private int getPlayPauseResourceId() {
        if (this.playback.getPlaybackState() == 3) {
            return R$drawable.ic_playback_pause_notification;
        }
        return R$drawable.ic_playback_play_notification;
    }

    private String getPlayPauseLabel() {
        if (getPlayPauseResourceId() == R$drawable.ic_playback_play) {
            return this.context.getResources().getString(R$string.podcast_play_label);
        }
        return this.context.getResources().getString(R$string.podcast_pause_label);
    }
}
