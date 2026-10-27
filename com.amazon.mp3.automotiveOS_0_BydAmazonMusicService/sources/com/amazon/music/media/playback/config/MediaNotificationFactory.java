package com.amazon.music.media.playback.config;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import com.amazon.music.media.playback.PlaybackController;

/* JADX INFO: loaded from: classes4.dex */
public interface MediaNotificationFactory {

    public interface ForceUpdateHandler {
        void forceUpdate();
    }

    public interface MediaIntentProvider {
    }

    @NonNull
    NotificationCompat.Builder createNotification(@NonNull PlaybackController playbackController);

    ForceUpdateHandler getForceUpdateHandler();

    int getNotificationId();

    boolean isAllDataReady(@NonNull PlaybackController playbackController);

    void setForceUpdateHandler(ForceUpdateHandler forceUpdateHandler);

    void setMediaIntentProvider(MediaIntentProvider mediaIntentProvider);
}
