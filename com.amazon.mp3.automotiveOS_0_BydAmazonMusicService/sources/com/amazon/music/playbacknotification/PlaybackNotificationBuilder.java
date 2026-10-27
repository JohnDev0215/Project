package com.amazon.music.playbacknotification;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.view.ViewCompat;
import androidx.media.app.NotificationCompat;
import androidx.mediarouter.media.MediaItemMetadata;
import com.amazon.music.playbacknotification.featuregate.Feature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class PlaybackNotificationBuilder {
    public static String METRICS_SOURCE = "source";
    private static MediaSessionCompat ms;
    private final Logger LOG = LoggerFactory.getLogger(PlaybackNotificationBuilder.class.getSimpleName());
    private final Context context;
    private NotificationCompat.Builder currentBuilder;
    private final PlaybackNotificationInterface playbackNotificationInterface;
    private final PlaybackNotificationModel playbackNotificationModel;

    public PlaybackNotificationBuilder(Context context, PlaybackNotificationModel playbackNotificationModel, PlaybackNotificationInterface playbackNotificationInterface) {
        this.context = context;
        this.playbackNotificationModel = playbackNotificationModel;
        this.playbackNotificationInterface = playbackNotificationInterface;
    }

    public NotificationCompat.Builder createNotificationBuilder() {
        init();
        if (this.playbackNotificationInterface.hasPlaybackError()) {
            return createPlaybackErrorNotificationBuilder();
        }
        if (this.playbackNotificationInterface.nowPlayingHasSongs()) {
            return createPlaybackNotificationBuilder();
        }
        this.LOG.error("There are no songs and now playing isn't loading...");
        return null;
    }

    private void init() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this.context);
        this.currentBuilder = builder;
        builder.setVisibility(1).setContentIntent(this.playbackNotificationModel.getClickIntent()).setDeleteIntent(this.playbackNotificationModel.getDeleteIntent()).setSmallIcon(R$drawable.statusbar_icon).setColor(ViewCompat.MEASURED_STATE_MASK).setShowWhen(false).setChannelId("android_music_playback_notification").setCategory(NotificationCompat.CATEGORY_TRANSPORT);
        if (this.playbackNotificationInterface.isPlaying()) {
            this.currentBuilder.setOngoing(true);
        } else {
            this.currentBuilder.setOngoing(false);
        }
    }

    private NotificationCompat.Builder createPlaybackNotificationBuilder() {
        boolean z;
        MediaSessionCompat.Token token;
        int[] iArr;
        MediaType mediaType = this.playbackNotificationModel.getMediaType();
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$playbacknotification$MediaType[mediaType.ordinal()];
        if (i != 1) {
            if (i == 2) {
                addPlayPauseButton(this.currentBuilder);
            } else if (i == 3) {
                addStationButtons(this.currentBuilder);
            } else if (i == 4) {
                addVideoButtons(this.currentBuilder);
            } else {
                addPrevPlayNextAddButtons(this.currentBuilder);
            }
            z = true;
        } else {
            z = false;
        }
        this.currentBuilder.setContentTitle(this.playbackNotificationModel.getContentTitle()).setContentText(this.playbackNotificationModel.getContentText()).setContentInfo(this.playbackNotificationModel.getContentHeader());
        if (this.playbackNotificationModel.getToken() != null) {
            token = this.playbackNotificationModel.getToken();
        } else {
            token = getToken();
        }
        Build.MANUFACTURER.equalsIgnoreCase("kyocera");
        if (!z) {
            iArr = new int[0];
        } else if (MediaType.POLDI.equals(mediaType)) {
            iArr = new int[]{0};
        } else {
            iArr = ((mediaType != MediaType.STATION || this.playbackNotificationInterface.canRate()) && mediaType != MediaType.VIDEO) ? new int[]{1, 2, 3} : new int[]{0, 1, 2};
        }
        NotificationCompat.MediaStyle showActionsInCompactView = new NotificationCompat.MediaStyle().setMediaSession(token).setShowActionsInCompactView(iArr);
        if (z) {
            this.currentBuilder.setStyle(showActionsInCompactView);
        }
        Bitmap artworkBitmap = this.playbackNotificationModel.getArtworkBitmap();
        if (artworkBitmap != null) {
            this.currentBuilder.setLargeIcon(artworkBitmap);
        }
        return this.currentBuilder;
    }

    /* JADX INFO: renamed from: com.amazon.music.playbacknotification.PlaybackNotificationBuilder$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$playbacknotification$MediaType;

        static {
            int[] iArr = new int[MediaType.values().length];
            $SwitchMap$com$amazon$music$playbacknotification$MediaType = iArr;
            try {
                iArr[MediaType.CLOUDQUEUE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$playbacknotification$MediaType[MediaType.POLDI.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$playbacknotification$MediaType[MediaType.STATION.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$playbacknotification$MediaType[MediaType.VIDEO.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private NotificationCompat.Builder createPlaybackErrorNotificationBuilder() {
        this.playbackNotificationInterface.getPlaybackError();
        throw null;
    }

    private void addStationButtons(NotificationCompat.Builder builder) {
        boolean zIsEnabled = Feature.should_enable_playback_notification_prev_button.isEnabled();
        addThumbDownButton(builder);
        if (zIsEnabled) {
            addPrevButton(builder);
        } else {
            addDisabledPrevButton(builder);
        }
        addPlayPauseButton(builder);
        addNextButton(builder);
        addThumbUpButton(builder);
    }

    private void addPrevPlayNextAddButtons(NotificationCompat.Builder builder) {
        addAddToLibraryButton(builder);
        addPrevButton(builder);
        addPlayPauseButton(builder);
        addNextButton(builder);
    }

    private void addVideoButtons(NotificationCompat.Builder builder) {
        addPrevButton(builder);
        addPlayPauseButton(builder);
        addNextButton(builder);
    }

    private void addPlayPauseButton(NotificationCompat.Builder builder) {
        if (this.playbackNotificationInterface.isPlaying()) {
            builder.addAction(R$drawable.ic_playback_pause, this.context.getString(R$string.label_pause), buildPendingIntent("com.amazon.music.playbacknotification.pause"));
        } else {
            builder.addAction(R$drawable.ic_playback_play, this.context.getString(R$string.label_play), buildPendingIntent("com.amazon.music.playbacknotification.play"));
        }
    }

    private void addAddToLibraryButton(NotificationCompat.Builder builder) {
        if (this.playbackNotificationInterface.isMediaInLibrary()) {
            builder.addAction(R$drawable.ic_check_larger_disabled, this.context.getString(R$string.label_added), null);
        } else {
            builder.addAction(R$drawable.ic_action_add, this.context.getString(R$string.label_add), buildPendingIntent("com.amazon.music.playbacknotification.add"));
        }
    }

    private void addPrevButton(NotificationCompat.Builder builder) {
        if (this.playbackNotificationInterface.canSkipPrevious()) {
            builder.addAction(R$drawable.ic_playback_previous, this.context.getString(R$string.label_previous), buildPendingIntent("com.amazon.music.playbacknotification.prev"));
        } else {
            builder.addAction(R$drawable.ic_playback_previous_disabled, this.context.getString(R$string.label_previous), null);
        }
    }

    private void addDisabledPrevButton(NotificationCompat.Builder builder) {
        builder.addAction(R$drawable.ic_playback_previous_disabled, this.context.getString(R$string.label_previous), null);
    }

    private void addNextButton(NotificationCompat.Builder builder) {
        if (this.playbackNotificationInterface.hasNext() && this.playbackNotificationInterface.canSkipNext()) {
            builder.addAction(R$drawable.ic_playback_next, this.context.getString(R$string.label_next), buildPendingIntent("com.amazon.music.playbacknotification.next"));
        } else {
            builder.addAction(R$drawable.ic_playback_next_disabled, this.context.getString(R$string.label_next), null);
        }
    }

    private void addThumbUpButton(NotificationCompat.Builder builder) {
        if (this.playbackNotificationInterface.canRate()) {
            if (this.playbackNotificationInterface.isThumbedUp()) {
                builder.addAction(R$drawable.ic_action_like, this.context.getString(R$string.thumbs_up_selected_content_description), buildPendingIntent("com.amazon.music.playbacknotification.thumb_down"));
            } else {
                builder.addAction(R$drawable.ic_action_like_outline, this.context.getString(R$string.thumbs_up_content_description), buildPendingIntent("com.amazon.music.playbacknotification.thumb_down"));
            }
        }
    }

    private void addThumbDownButton(NotificationCompat.Builder builder) {
        if (this.playbackNotificationInterface.canRate()) {
            if (this.playbackNotificationInterface.isThumbedDown()) {
                builder.addAction(R$drawable.ic_action_dislike, this.context.getString(R$string.thumbs_down_selected_content_description), buildPendingIntent("com.amazon.music.playbacknotification.thumb_up"));
            } else {
                builder.addAction(R$drawable.ic_action_dislike_outline, this.context.getString(R$string.thumbs_down_content_description), buildPendingIntent("com.amazon.music.playbacknotification.thumb_up"));
            }
        }
    }

    private PendingIntent buildPendingIntent(String str) {
        Intent intent = new Intent(str);
        intent.putExtra(METRICS_SOURCE, "com.amazon.music.playbacknotification");
        return PendingIntent.getBroadcast(this.context, 0, intent, 335544320);
    }

    private MediaSessionCompat.Token getToken() {
        if (ms == null) {
            ms = new MediaSessionCompat(this.context, PlaybackNotificationBuilder.class.getName());
        }
        ms.setMetadata(new MediaMetadataCompat.Builder().putString(MediaItemMetadata.KEY_ARTIST, this.playbackNotificationModel.getContentText()).putString("android.media.metadata.ALBUM", this.playbackNotificationModel.getContentHeader()).putString(MediaItemMetadata.KEY_TITLE, this.playbackNotificationModel.getContentTitle()).build());
        return ms.getSessionToken();
    }
}
