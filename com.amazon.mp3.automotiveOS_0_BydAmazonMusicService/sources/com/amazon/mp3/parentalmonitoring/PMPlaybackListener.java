package com.amazon.mp3.parentalmonitoring;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.text.TextUtils;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.util.Log;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.media.playback.PlaybackController;

/* JADX INFO: loaded from: classes3.dex */
public class PMPlaybackListener implements OnPlayStateChangedListener {
    private static final String TAG = "PMPlaybackListener";
    private Context mContext;
    private PlaybackController mPlaybackController = PlaybackControllerProvider.getController(ControlSource.NONE);
    private String mPreviousIdentifier;
    private String mPreviousTitle;

    private enum EventType {
        START,
        STOP
    }

    private PMPlaybackListener(final Context context) {
        this.mContext = context;
    }

    public static PMPlaybackListener register(final Context context) {
        return new PMPlaybackListener(context);
    }

    public void unregister() {
        String str = this.mPreviousIdentifier;
        if (str != null) {
            broadcastMusicEvent(EventType.STOP, str, this.mPreviousTitle);
        }
    }

    private EventType getEventTypeFromPlayStatus(PlayStatus playStatus) {
        if (playStatus == PlayStatus.RENDERING) {
            return EventType.START;
        }
        if (playStatus == PlayStatus.USER_PAUSED || playStatus == PlayStatus.SYSTEM_PAUSED) {
            return EventType.STOP;
        }
        return null;
    }

    @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
    public void onPlayStateChanged() {
        EventType eventTypeFromPlayStatus;
        MediaItem currentMediaItem;
        if (!isParentalMonitoringEnabled() || (eventTypeFromPlayStatus = getEventTypeFromPlayStatus(this.mPlaybackController.getPlayStatus())) == null || (currentMediaItem = this.mPlaybackController.getCurrentMediaItem()) == null) {
            return;
        }
        MediaItemId.Type type = MediaItemId.Type.ASIN;
        if (currentMediaItem.getMediaItemId(type) != null) {
            broadcastMusicEvent(eventTypeFromPlayStatus, currentMediaItem.getMediaItemId(type).getId(), null);
        } else if (currentMediaItem.getMediaItemId() != null) {
            String id = currentMediaItem.getMediaItemId().getId();
            if (id.lastIndexOf(47) != -1) {
                id = id.substring(id.lastIndexOf(47) + 1);
            }
            broadcastMusicEvent(eventTypeFromPlayStatus, id, currentMediaItem.getName());
        }
    }

    private void broadcastMusicEvent(EventType eventType, String identifier, String title) {
        if (eventType == EventType.START) {
            String str = this.mPreviousIdentifier;
            if (str != null) {
                if (str.equals(identifier)) {
                    return;
                } else {
                    broadcastMusicEvent(EventType.STOP, this.mPreviousIdentifier, this.mPreviousTitle);
                }
            }
            this.mPreviousIdentifier = identifier;
            this.mPreviousTitle = title;
        } else {
            String str2 = this.mPreviousIdentifier;
            if (str2 == null || !str2.equals(identifier)) {
                return;
            }
            this.mPreviousIdentifier = null;
            this.mPreviousTitle = null;
        }
        if (TextUtils.isEmpty(identifier)) {
            Log.error(TAG, "Illegal ASIN/identifier");
            return;
        }
        Intent intent = new Intent("com.amazon.pm.SEND_EVENT");
        intent.setComponent(new ComponentName("com.amazon.pm", "com.amazon.pm.receiver.EventReceiver"));
        intent.putExtra("com.amazon.pm.EXTRA_ASIN", identifier);
        intent.putExtra("com.amazon.pm.EXTRA_TYPE", eventType.ordinal());
        intent.putExtra("com.amazon.pm.EXTRA_CATEGORY", 4);
        intent.putExtra("com.amazon.pm.EXTRA_TIMESTAMP", System.currentTimeMillis() / 1000);
        if (title != null) {
            intent.putExtra("com.amazon.pm.EXTRA_TITLE", title);
        }
        this.mContext.sendBroadcast(intent, "com.amazon.pm.permission.SEND_EVENT");
        Log.debug(TAG, identifier + " " + title + " " + eventType);
    }

    private boolean isParentalMonitoringEnabled() {
        return Settings.Secure.getInt(this.mContext.getContentResolver(), "parental_monitoring_enabled", 0) != 0;
    }
}
