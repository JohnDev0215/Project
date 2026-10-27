package com.amazon.podcast.media.playback;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.amazon.podcast.Handlers;
import com.amazon.podcast.Podcast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class BluetoothActionsReceiver extends BroadcastReceiver {
    private final Logger logger = LoggerFactory.getLogger("PodcastBluetoothActionsReceiver");

    @Override // android.content.BroadcastReceiver
    public void onReceive(final Context context, final Intent intent) {
        Handlers.INSTANCE.runInBackground(new Runnable() { // from class: com.amazon.podcast.media.playback.BluetoothActionsReceiver.1
            @Override // java.lang.Runnable
            public void run() {
                String action = intent.getAction();
                Playback playback = Podcast.getPlayback();
                int playbackState = playback.getPlaybackState();
                if (BluetoothActionsReceiver.this.isBluetoothDeviceDisconnected(intent, action) && playbackState == 3) {
                    BluetoothActionsReceiver.this.logger.debug("Bluetooth is disconnected. Pausing the playback");
                    playback.playPause();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isBluetoothDeviceDisconnected(final Intent intent, final String action) {
        return action.equals("android.bluetooth.device.action.ACL_DISCONNECTED") || action.equals("android.bluetooth.device.action.ACL_DISCONNECT_REQUESTED") || intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1) == 0;
    }
}
