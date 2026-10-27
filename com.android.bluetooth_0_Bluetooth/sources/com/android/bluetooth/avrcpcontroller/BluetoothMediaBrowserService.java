package com.android.bluetooth.avrcpcontroller;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import androidx.media.MediaBrowserServiceCompat;
import com.android.bluetooth.BluetoothPrefs;
import com.android.bluetooth.R;
import com.android.bluetooth.avrcpcontroller.BrowseTree;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothMediaBrowserService extends MediaBrowserServiceCompat {
    public static final String CONTENT_STYLE_BROWSABLE_HINT = "android.media.browse.CONTENT_STYLE_BROWSABLE_HINT";
    public static final int CONTENT_STYLE_GRID_ITEM_HINT_VALUE = 2;
    public static final int CONTENT_STYLE_LIST_ITEM_HINT_VALUE = 1;
    public static final String CONTENT_STYLE_PLAYABLE_HINT = "android.media.browse.CONTENT_STYLE_PLAYABLE_HINT";
    private static final String CONTENT_STYLE_SUPPORTED = "android.media.browse.CONTENT_STYLE_SUPPORTED";
    public static final String ERROR_RESOLUTION_ACTION_INTENT = "android.media.extras.ERROR_RESOLUTION_ACTION_INTENT";
    public static final String ERROR_RESOLUTION_ACTION_LABEL = "android.media.extras.ERROR_RESOLUTION_ACTION_LABEL";
    private static BluetoothMediaBrowserService sBluetoothMediaBrowserService;
    private List<MediaSessionCompat.QueueItem> mMediaQueue = new ArrayList();
    private MediaSessionCompat mSession;
    private static final String TAG = "BluetoothMediaBrowserService";
    private static final boolean DBG = Log.isLoggable(TAG, 3);

    @Override // androidx.media.MediaBrowserServiceCompat, android.app.Service
    public void onCreate() {
        if (DBG) {
            Log.d(TAG, "onCreate");
        }
        super.onCreate();
        MediaSessionCompat mediaSessionCompat = new MediaSessionCompat(this, TAG);
        this.mSession = mediaSessionCompat;
        setSessionToken(mediaSessionCompat.getSessionToken());
        this.mSession.setFlags(3);
        this.mSession.setQueueTitle(getString(R.string.bluetooth_a2dp_sink_queue_name));
        this.mSession.setQueue(this.mMediaQueue);
        setErrorPlaybackState();
        sBluetoothMediaBrowserService = this;
    }

    List<MediaBrowserCompat.MediaItem> getContents(String parentMediaId) {
        AvrcpControllerService avrcpControllerService = AvrcpControllerService.getAvrcpControllerService();
        if (avrcpControllerService == null) {
            return new ArrayList(0);
        }
        return avrcpControllerService.getContents(parentMediaId);
    }

    private void setErrorPlaybackState() {
        Bundle extras = new Bundle();
        extras.putString(ERROR_RESOLUTION_ACTION_LABEL, getString(R.string.bluetooth_connect_action));
        Intent launchIntent = new Intent();
        launchIntent.setAction(BluetoothPrefs.BLUETOOTH_SETTING_ACTION);
        launchIntent.addCategory(BluetoothPrefs.BLUETOOTH_SETTING_CATEGORY);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, launchIntent, 201326592);
        extras.putParcelable(ERROR_RESOLUTION_ACTION_INTENT, pendingIntent);
        PlaybackStateCompat errorState = new PlaybackStateCompat.Builder().setErrorMessage(getString(R.string.bluetooth_disconnected)).setExtras(extras).setState(7, 0L, 0.0f).build();
        this.mSession.setPlaybackState(errorState);
    }

    private Bundle getDefaultStyle() {
        Bundle style = new Bundle();
        style.putBoolean(CONTENT_STYLE_SUPPORTED, true);
        style.putInt(CONTENT_STYLE_BROWSABLE_HINT, 2);
        style.putInt(CONTENT_STYLE_PLAYABLE_HINT, 1);
        return style;
    }

    @Override // androidx.media.MediaBrowserServiceCompat
    public synchronized void onLoadChildren(String parentMediaId, MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result) {
        if (DBG) {
            Log.d(TAG, "onLoadChildren parentMediaId=" + parentMediaId);
        }
        List<MediaBrowserCompat.MediaItem> contents = getContents(parentMediaId);
        if (contents == null) {
            result.detach();
        } else {
            result.sendResult(contents);
        }
    }

    @Override // androidx.media.MediaBrowserServiceCompat
    public MediaBrowserServiceCompat.BrowserRoot onGetRoot(String clientPackageName, int clientUid, Bundle rootHints) {
        if (DBG) {
            Log.d(TAG, "onGetRoot");
        }
        Bundle style = getDefaultStyle();
        return new MediaBrowserServiceCompat.BrowserRoot(BrowseTree.ROOT, style);
    }

    private void updateNowPlayingQueue(BrowseTree.BrowseNode node) {
        List<MediaBrowserCompat.MediaItem> songList = node.getContents();
        this.mMediaQueue.clear();
        if (songList != null) {
            for (MediaBrowserCompat.MediaItem song : songList) {
                this.mMediaQueue.add(new MediaSessionCompat.QueueItem(song.getDescription(), this.mMediaQueue.size()));
            }
        }
        this.mSession.setQueue(this.mMediaQueue);
    }

    private void clearNowPlayingQueue() {
        this.mMediaQueue.clear();
        this.mSession.setQueue(this.mMediaQueue);
    }

    static synchronized void notifyChanged(BrowseTree.BrowseNode node) {
        if (sBluetoothMediaBrowserService != null) {
            if (node.getScope() == 3) {
                sBluetoothMediaBrowserService.updateNowPlayingQueue(node);
            } else {
                sBluetoothMediaBrowserService.notifyChildrenChanged(node.getID());
            }
        }
    }

    static synchronized void addressedPlayerChanged(MediaSessionCompat.Callback callback) {
        if (sBluetoothMediaBrowserService != null) {
            if (callback == null) {
                sBluetoothMediaBrowserService.setErrorPlaybackState();
                sBluetoothMediaBrowserService.clearNowPlayingQueue();
            }
            sBluetoothMediaBrowserService.mSession.setCallback(callback);
        } else {
            Log.w(TAG, "addressedPlayerChanged Unavailable");
        }
    }

    static synchronized void trackChanged(AvrcpItem track) {
        if (DBG) {
            Log.d(TAG, "trackChanged setMetadata=" + track);
        }
        if (sBluetoothMediaBrowserService != null) {
            if (track != null) {
                sBluetoothMediaBrowserService.mSession.setMetadata(track.toMediaMetadata());
            } else {
                sBluetoothMediaBrowserService.mSession.setMetadata(null);
            }
        } else {
            Log.w(TAG, "trackChanged Unavailable");
        }
    }

    static synchronized void notifyChanged(PlaybackStateCompat playbackState) {
        Log.d(TAG, "notifyChanged PlaybackState" + playbackState);
        if (sBluetoothMediaBrowserService != null) {
            sBluetoothMediaBrowserService.mSession.setPlaybackState(playbackState);
        } else {
            Log.w(TAG, "notifyChanged Unavailable");
        }
    }

    public static synchronized void play() {
        if (sBluetoothMediaBrowserService != null) {
            Log.w(TAG, "play");
            sBluetoothMediaBrowserService.mSession.getController().getTransportControls().play();
        } else {
            Log.w(TAG, "play Unavailable");
        }
    }

    public static synchronized void pause() {
        if (sBluetoothMediaBrowserService != null) {
            Log.w(TAG, "pause");
            sBluetoothMediaBrowserService.mSession.getController().getTransportControls().pause();
        } else {
            Log.w(TAG, "pause Unavailable");
        }
    }

    public static synchronized int getPlaybackState() {
        PlaybackStateCompat currentPlaybackState;
        if (sBluetoothMediaBrowserService != null && (currentPlaybackState = sBluetoothMediaBrowserService.mSession.getController().getPlaybackState()) != null) {
            return currentPlaybackState.getState();
        }
        return 7;
    }

    public static synchronized MediaControllerCompat.TransportControls getTransportControls() {
        if (sBluetoothMediaBrowserService != null) {
            return sBluetoothMediaBrowserService.mSession.getController().getTransportControls();
        }
        Log.w(TAG, "transportControls Unavailable");
        return null;
    }

    public static synchronized void setActive(boolean active) {
        if (sBluetoothMediaBrowserService != null) {
            sBluetoothMediaBrowserService.mSession.setActive(active);
        } else {
            Log.w(TAG, "setActive Unavailable");
        }
    }

    public static synchronized MediaSessionCompat getSession() {
        if (sBluetoothMediaBrowserService != null) {
            return sBluetoothMediaBrowserService.mSession;
        }
        Log.w(TAG, "getSession Unavailable");
        return null;
    }
}
