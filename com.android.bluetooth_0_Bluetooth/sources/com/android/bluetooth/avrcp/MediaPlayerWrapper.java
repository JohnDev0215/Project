package com.android.bluetooth.avrcp;

import android.media.MediaMetadata;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.android.bluetooth.avrcp.MediaController;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
class MediaPlayerWrapper {
    private static final boolean DEBUG = false;
    private static final int PLAYBACK_STATE_CHANGE_EVENT_LOGGER_SIZE = 5;
    private static final String PLAYBACK_STATE_CHANGE_LOGGER_EVENT_TITLE = "Playback State change Event";
    private static final long PLAYSTATE_BOUNCE_IGNORE_PERIOD = 500;
    private static final String TAG = "AvrcpMediaPlayerWrapper";
    static boolean sTesting = false;
    private MediaData mCurrentData;
    private Looper mLooper;
    private MediaController mMediaController;
    private String mPackageName;
    private MediaControllerListener mControllerCallbacks = null;
    private final Object mCallbackLock = new Object();
    private Callback mRegisteredCallback = null;
    private final AvrcpEventLogger mPlaybackStateChangeEventLogger = new AvrcpEventLogger(5, PLAYBACK_STATE_CHANGE_LOGGER_EVENT_TITLE);

    public interface Callback {
        void mediaUpdatedCallback(MediaData mediaData);

        void sessionUpdatedCallback(String str);
    }

    boolean isPlaybackStateReady() {
        if (getPlaybackState() == null) {
            d("isPlaybackStateReady(): PlaybackState is null");
            return false;
        }
        return true;
    }

    boolean isMetadataReady() {
        if (getMetadata() == null) {
            d("isMetadataReady(): Metadata is null");
            return false;
        }
        return true;
    }

    MediaPlayerWrapper(MediaController controller, Looper looper) {
        this.mMediaController = controller;
        this.mPackageName = controller.getPackageName();
        this.mLooper = looper;
        MediaData mediaData = new MediaData(null, null, null);
        this.mCurrentData = mediaData;
        mediaData.queue = Util.toMetadataList(getQueue());
        this.mCurrentData.metadata = Util.toMetadata(getMetadata());
        this.mCurrentData.state = getPlaybackState();
    }

    void cleanup() {
        unregisterCallback();
        this.mMediaController = null;
        this.mLooper = null;
    }

    String getPackageName() {
        return this.mPackageName;
    }

    protected List<MediaSession.QueueItem> getQueue() {
        return this.mMediaController.getQueue();
    }

    protected MediaMetadata getMetadata() {
        return this.mMediaController.getMetadata();
    }

    Metadata getCurrentMetadata() {
        return Util.toMetadata(getMetadata());
    }

    PlaybackState getPlaybackState() {
        return this.mMediaController.getPlaybackState();
    }

    long getActiveQueueID() {
        PlaybackState state = this.mMediaController.getPlaybackState();
        if (state == null) {
            return -1L;
        }
        return state.getActiveQueueItemId();
    }

    List<Metadata> getCurrentQueue() {
        return this.mCurrentData.queue;
    }

    MediaData getCurrentMediaData() {
        MediaData data = new MediaData(getCurrentMetadata(), getPlaybackState(), getCurrentQueue());
        return data;
    }

    void playItemFromQueue(long qid) {
        if (getQueue() == null) {
            Log.w(TAG, "playItemFromQueue: Trying to play item for player that has no queue: " + this.mPackageName);
            return;
        }
        MediaController.TransportControls controller = this.mMediaController.getTransportControls();
        controller.skipToQueueItem(qid);
    }

    boolean isShuffleSupported() {
        return false;
    }

    boolean isRepeatSupported() {
        return false;
    }

    void toggleShuffle(boolean on) {
    }

    void toggleRepeat(boolean on) {
    }

    boolean isMetadataSynced() {
        if (getQueue() != null && getActiveQueueID() != -1) {
            MediaSession.QueueItem currItem = null;
            Iterator<MediaSession.QueueItem> it = getQueue().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                MediaSession.QueueItem item = it.next();
                if (item.getQueueId() == getActiveQueueID()) {
                    currItem = item;
                    break;
                }
            }
            Metadata qitem = Util.toMetadata(currItem);
            Metadata mdata = Util.toMetadata(getMetadata());
            if (currItem == null || !qitem.equals(mdata)) {
                return false;
            }
            return true;
        }
        return true;
    }

    void registerCallback(Callback callback) {
        if (callback == null) {
            e("Cannot register null callbacks for " + this.mPackageName);
            return;
        }
        synchronized (this.mCallbackLock) {
            this.mRegisteredCallback = callback;
        }
        this.mCurrentData = new MediaData(Util.toMetadata(getMetadata()), getPlaybackState(), Util.toMetadataList(getQueue()));
        this.mControllerCallbacks = new MediaControllerListener(this.mMediaController, this.mLooper);
    }

    void unregisterCallback() {
        synchronized (this.mCallbackLock) {
            this.mRegisteredCallback = null;
        }
        MediaControllerListener mediaControllerListener = this.mControllerCallbacks;
        if (mediaControllerListener == null) {
            return;
        }
        mediaControllerListener.cleanup();
        this.mControllerCallbacks = null;
    }

    void updateMediaController(MediaController newController) {
        if (newController == this.mMediaController) {
            return;
        }
        this.mMediaController = newController;
        synchronized (this.mCallbackLock) {
            if (this.mRegisteredCallback != null && this.mControllerCallbacks != null) {
                this.mControllerCallbacks.cleanup();
                this.mCurrentData = new MediaData(Util.toMetadata(getMetadata()), getPlaybackState(), Util.toMetadataList(getQueue()));
                this.mControllerCallbacks = new MediaControllerListener(this.mMediaController, this.mLooper);
                d("Controller for " + this.mPackageName + " was updated.");
                return;
            }
            d("Controller for " + this.mPackageName + " maybe is not activated.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMediaUpdate() {
        MediaData newData = new MediaData(Util.toMetadata(getMetadata()), getPlaybackState(), Util.toMetadataList(getQueue()));
        if (newData.equals(this.mCurrentData)) {
            Log.v(TAG, "Trying to update with last sent metadata");
            return;
        }
        synchronized (this.mCallbackLock) {
            if (this.mRegisteredCallback == null) {
                Log.e(TAG, this.mPackageName + ": Trying to send an update with no registered callback");
                return;
            }
            Log.v(TAG, "trySendMediaUpdate(): Metadata has been updated for " + this.mPackageName);
            this.mRegisteredCallback.mediaUpdatedCallback(newData);
            this.mCurrentData = newData;
        }
    }

    class TimeoutHandler extends Handler {
        private static final long CALLBACK_TIMEOUT_MS = 2000;
        private static final int MSG_TIMEOUT = 0;

        TimeoutHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (msg.what != 0) {
                Log.wtf(MediaPlayerWrapper.TAG, "Unknown message on timeout handler: " + msg.what);
                return;
            }
            Log.e(MediaPlayerWrapper.TAG, "Timeout while waiting for metadata to sync for " + MediaPlayerWrapper.this.mPackageName);
            Log.e(MediaPlayerWrapper.TAG, "  └ Current Metadata: " + Util.toMetadata(MediaPlayerWrapper.this.getMetadata()));
            Log.e(MediaPlayerWrapper.TAG, "  └ Current Playstate: " + MediaPlayerWrapper.this.getPlaybackState());
            List<Metadata> current_queue = Util.toMetadataList(MediaPlayerWrapper.this.getQueue());
            for (int i = 0; i < current_queue.size(); i++) {
                Log.e(MediaPlayerWrapper.TAG, "  └ QueueItem(" + i + "): " + current_queue.get(i));
            }
            MediaPlayerWrapper.this.sendMediaUpdate();
            if (MediaPlayerWrapper.sTesting) {
                Log.wtf(MediaPlayerWrapper.TAG, "Crashing the stack");
            }
        }
    }

    class MediaControllerListener extends MediaController.Callback {
        private MediaController mController;
        private Handler mTimeoutHandler;
        private final Object mTimeoutHandlerLock;

        MediaControllerListener(MediaController controller, Looper newLooper) {
            Object obj = new Object();
            this.mTimeoutHandlerLock = obj;
            synchronized (obj) {
                TimeoutHandler timeoutHandler = MediaPlayerWrapper.this.new TimeoutHandler(newLooper);
                this.mTimeoutHandler = timeoutHandler;
                this.mController = controller;
                controller.registerCallback(this, timeoutHandler);
            }
        }

        void cleanup() {
            synchronized (this.mTimeoutHandlerLock) {
                this.mController.unregisterCallback(this);
                this.mController = null;
                this.mTimeoutHandler.removeMessages(0);
                this.mTimeoutHandler = null;
            }
        }

        void trySendMediaUpdate() {
            synchronized (this.mTimeoutHandlerLock) {
                if (this.mTimeoutHandler == null) {
                    return;
                }
                this.mTimeoutHandler.removeMessages(0);
                if (!MediaPlayerWrapper.this.isMetadataSynced()) {
                    MediaPlayerWrapper.this.d("trySendMediaUpdate(): Starting media update timeout");
                    this.mTimeoutHandler.sendEmptyMessageDelayed(0, 2000L);
                } else {
                    MediaPlayerWrapper.this.sendMediaUpdate();
                }
            }
        }

        @Override // android.media.session.MediaController.Callback
        public void onMetadataChanged(MediaMetadata metadata) {
            if (!MediaPlayerWrapper.this.isMetadataReady()) {
                Log.v(MediaPlayerWrapper.TAG, "onMetadataChanged(): " + MediaPlayerWrapper.this.mPackageName + " tried to update with no queue");
                return;
            }
            if (!Objects.equals(metadata, MediaPlayerWrapper.this.getMetadata())) {
                MediaPlayerWrapper.e("The callback metadata doesn't match controller metadata");
            }
            if (Objects.equals(metadata, MediaPlayerWrapper.this.mCurrentData.metadata)) {
                Log.w(MediaPlayerWrapper.TAG, "onMetadataChanged(): " + MediaPlayerWrapper.this.mPackageName + " tried to update with no new data");
                return;
            }
            trySendMediaUpdate();
        }

        @Override // android.media.session.MediaController.Callback
        public void onPlaybackStateChanged(PlaybackState state) {
            if (MediaPlayerWrapper.this.isPlaybackStateReady()) {
                MediaPlayerWrapper.this.mPlaybackStateChangeEventLogger.logv(MediaPlayerWrapper.TAG, "onPlaybackStateChanged(): " + MediaPlayerWrapper.this.mPackageName + " : " + state.toString());
                if (!MediaPlayerWrapper.playstateEquals(state, MediaPlayerWrapper.this.getPlaybackState())) {
                    MediaPlayerWrapper.e("The callback playback state doesn't match the current state");
                }
                if (MediaPlayerWrapper.playstateEquals(state, MediaPlayerWrapper.this.mCurrentData.state)) {
                    Log.w(MediaPlayerWrapper.TAG, "onPlaybackStateChanged(): " + MediaPlayerWrapper.this.mPackageName + " tried to update with no new data");
                    return;
                }
                if (state.getState() == 0) {
                    Log.v(MediaPlayerWrapper.TAG, "Waiting to send update as controller has no playback state");
                    return;
                } else {
                    trySendMediaUpdate();
                    return;
                }
            }
            Log.v(MediaPlayerWrapper.TAG, "onPlaybackStateChanged(): " + MediaPlayerWrapper.this.mPackageName + " tried to update with no queue");
        }

        @Override // android.media.session.MediaController.Callback
        public void onQueueChanged(List<MediaSession.QueueItem> queue) {
            if (!MediaPlayerWrapper.this.isPlaybackStateReady() || !MediaPlayerWrapper.this.isMetadataReady()) {
                Log.v(MediaPlayerWrapper.TAG, "onQueueChanged(): " + MediaPlayerWrapper.this.mPackageName + " tried to update with no queue");
                return;
            }
            Log.v(MediaPlayerWrapper.TAG, "onQueueChanged(): " + MediaPlayerWrapper.this.mPackageName);
            if (!Objects.equals(queue, MediaPlayerWrapper.this.getQueue())) {
                MediaPlayerWrapper.e("The callback queue isn't the current queue");
            }
            List<Metadata> current_queue = Util.toMetadataList(queue);
            if (current_queue.equals(MediaPlayerWrapper.this.mCurrentData.queue)) {
                Log.w(MediaPlayerWrapper.TAG, "onQueueChanged(): " + MediaPlayerWrapper.this.mPackageName + " tried to update with no new data");
                return;
            }
            trySendMediaUpdate();
        }

        @Override // android.media.session.MediaController.Callback
        public void onSessionDestroyed() {
            Log.w(MediaPlayerWrapper.TAG, "The session was destroyed " + MediaPlayerWrapper.this.mPackageName);
            MediaPlayerWrapper.this.mRegisteredCallback.sessionUpdatedCallback(MediaPlayerWrapper.this.mPackageName);
        }

        Handler getTimeoutHandler() {
            return this.mTimeoutHandler;
        }
    }

    static boolean playstateEquals(PlaybackState a, PlaybackState b) {
        if (a == b) {
            return true;
        }
        if (a != null && b != null && a.getState() == b.getState() && a.getActiveQueueItemId() == b.getActiveQueueItemId() && Math.abs(a.getPosition() - b.getPosition()) < PLAYSTATE_BOUNCE_IGNORE_PERIOD) {
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(String message) {
        if (sTesting) {
            Log.wtf(TAG, message);
        } else {
            Log.e(TAG, message);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String message) {
    }

    Handler getTimeoutHandler() {
        MediaControllerListener mediaControllerListener = this.mControllerCallbacks;
        if (mediaControllerListener == null) {
            return null;
        }
        return mediaControllerListener.getTimeoutHandler();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mMediaController.toString() + "\n");
        sb.append("Current Data:\n");
        sb.append("  Song: " + this.mCurrentData.metadata + "\n");
        sb.append("  PlayState: " + this.mCurrentData.state + "\n");
        sb.append("  Queue: size=" + this.mCurrentData.queue.size() + "\n");
        for (Metadata data : this.mCurrentData.queue) {
            sb.append("    " + data + "\n");
        }
        this.mPlaybackStateChangeEventLogger.dump(sb);
        return sb.toString();
    }
}
