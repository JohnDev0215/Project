package com.amazon.podcast.media.playback;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
final class NotificationsReceiver extends BroadcastReceiver {
    private final Logger logger = LoggerFactory.getLogger("NotificationsReceiver");
    private final Playback playback;

    public NotificationsReceiver(final Playback playback) {
        this.playback = playback;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(final Context context, final Intent intent) {
        String string;
        string = intent.getExtras().getString("action_name");
        string.hashCode();
        switch (string) {
            case "rewind":
                this.playback.rewind();
                break;
            case "fast_forward":
                this.playback.fastForward();
                break;
            case "play_pause":
                this.playback.playPause();
                break;
            default:
                this.logger.error("Incompatible action on notification controls: " + string);
                break;
        }
    }
}
