package com.android.bluetooth.avrcp;

import android.content.ComponentName;
import android.content.Context;
import android.media.browse.MediaBrowser;
import android.media.session.PlaybackState;
import android.net.INetd;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.android.bluetooth.avrcp.MediaBrowser;
import com.android.bluetooth.avrcp.MediaController;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class BrowsedPlayerWrapper {
    static final int NUM_CACHED_FOLDERS = 5;
    public static final int STATUS_CONN_ERROR = 1;
    public static final int STATUS_LOOKUP_ERROR = 2;
    public static final int STATUS_PLAYBACK_TIMEOUT_ERROR = 3;
    public static final int STATUS_SUCCESS = 0;
    private ConnectionCallback mCallback;
    private final Context mContext;
    private final Looper mLooper;
    private final String mPackageName;
    private MediaBrowser mWrappedBrowser;
    private static final String TAG = "AvrcpBrowsedPlayerWrapper";
    private static final boolean DEBUG = Log.isLoggable(TAG, 3);
    private final Object mCallbackLock = new Object();
    private String mRoot = INetd.NEXTHOP_NONE;
    LinkedHashMap<String, List<ListItem>> mCachedFolders = new LinkedHashMap<String, List<ListItem>>(5) { // from class: com.android.bluetooth.avrcp.BrowsedPlayerWrapper.1
        @Override // java.util.LinkedHashMap
        protected boolean removeEldestEntry(Map.Entry<String, List<ListItem>> eldest) {
            return size() > 5;
        }
    };

    interface BrowseCallback {
        void run(int i, String str, List<ListItem> list);
    }

    interface ConnectionCallback {
        void run(int i, BrowsedPlayerWrapper browsedPlayerWrapper);
    }

    enum ConnectionState {
        DISCONNECTED,
        CONNECTING,
        CONNECTED
    }

    interface PlaybackCallback {
        void run(int i);
    }

    private BrowsedPlayerWrapper(Context context, Looper looper, String packageName, String className) {
        this.mContext = context;
        this.mPackageName = packageName;
        this.mLooper = looper;
        this.mWrappedBrowser = MediaBrowserFactory.make(context, new ComponentName(packageName, className), new MediaConnectionCallback(), null);
    }

    static BrowsedPlayerWrapper wrap(Context context, Looper looper, String packageName, String className) {
        Log.i(TAG, "Wrapping Media Browser " + packageName);
        BrowsedPlayerWrapper wrapper = new BrowsedPlayerWrapper(context, looper, packageName, className);
        return wrapper;
    }

    boolean connect(final ConnectionCallback cb) {
        if (cb == null) {
            Log.wtf(TAG, "connect: Trying to connect to " + this.mPackageName + "with null callback");
        }
        return setCallbackAndConnect(new ConnectionCallback() { // from class: com.android.bluetooth.avrcp.-$$Lambda$BrowsedPlayerWrapper$XHW8c-wILRxvIsQnQko2h56-3JU
            @Override // com.android.bluetooth.avrcp.BrowsedPlayerWrapper.ConnectionCallback
            public final void run(int i, BrowsedPlayerWrapper browsedPlayerWrapper) {
                this.f$0.lambda$connect$0$BrowsedPlayerWrapper(cb, i, browsedPlayerWrapper);
            }
        });
    }

    public /* synthetic */ void lambda$connect$0$BrowsedPlayerWrapper(ConnectionCallback cb, int status, BrowsedPlayerWrapper wrapper) {
        cb.run(status, wrapper);
        disconnect();
    }

    void disconnect() {
        if (DEBUG) {
            Log.d(TAG, "disconnect: Disconnecting from " + this.mPackageName);
        }
        this.mWrappedBrowser.disconnect();
        clearCallback();
    }

    boolean setCallbackAndConnect(ConnectionCallback callback) {
        synchronized (this.mCallbackLock) {
            if (this.mCallback != null) {
                Log.w(TAG, "setCallbackAndConnect: Already trying to connect to ");
                return false;
            }
            this.mCallback = callback;
            if (DEBUG) {
                Log.d(TAG, "Set mCallback, connecting to " + this.mPackageName);
            }
            this.mWrappedBrowser.connect();
            return true;
        }
    }

    void executeCallback(int status, BrowsedPlayerWrapper player) {
        synchronized (this.mCallbackLock) {
            if (this.mCallback == null) {
                Log.w(TAG, "Callback is NULL. Cannot execute");
                return;
            }
            ConnectionCallback callback = this.mCallback;
            if (DEBUG) {
                Log.d(TAG, "Executing callback");
            }
            callback.run(status, player);
        }
    }

    void clearCallback() {
        synchronized (this.mCallbackLock) {
            this.mCallback = null;
        }
        if (DEBUG) {
            Log.d(TAG, "mCallback = null");
        }
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public String getRootId() {
        return this.mRoot;
    }

    public boolean playItem(final String mediaId) {
        if (DEBUG) {
            Log.d(TAG, "playItem: Play item from media ID: " + mediaId);
        }
        return setCallbackAndConnect(new ConnectionCallback() { // from class: com.android.bluetooth.avrcp.-$$Lambda$BrowsedPlayerWrapper$j-Kal39dYJXRPL8pJIZDsdvePl0
            @Override // com.android.bluetooth.avrcp.BrowsedPlayerWrapper.ConnectionCallback
            public final void run(int i, BrowsedPlayerWrapper browsedPlayerWrapper) {
                this.f$0.lambda$playItem$2$BrowsedPlayerWrapper(mediaId, i, browsedPlayerWrapper);
            }
        });
    }

    public /* synthetic */ void lambda$playItem$2$BrowsedPlayerWrapper(String mediaId, int status, BrowsedPlayerWrapper wrapper) {
        if (DEBUG) {
            Log.d(TAG, "playItem: Connected to browsable player " + this.mPackageName);
        }
        MediaController controller = MediaControllerFactory.make(this.mContext, wrapper.mWrappedBrowser.getSessionToken());
        MediaController.TransportControls ctrl = controller.getTransportControls();
        Log.i(TAG, "playItem: Playing " + mediaId);
        ctrl.playFromMediaId(mediaId, null);
        MediaPlaybackListener mpl = new MediaPlaybackListener(this.mLooper, controller);
        mpl.waitForPlayback(new PlaybackCallback() { // from class: com.android.bluetooth.avrcp.-$$Lambda$BrowsedPlayerWrapper$BeiN_jBR2IYR5otim-y1iH-r98A
            @Override // com.android.bluetooth.avrcp.BrowsedPlayerWrapper.PlaybackCallback
            public final void run(int i) {
                this.f$0.lambda$playItem$1$BrowsedPlayerWrapper(i);
            }
        });
    }

    public /* synthetic */ void lambda$playItem$1$BrowsedPlayerWrapper(int playbackStatus) {
        Log.i(TAG, "playItem: Media item playback returned, status: " + playbackStatus);
        disconnect();
    }

    public boolean getFolderItems(final String mediaId, final BrowseCallback cb) {
        if (this.mCachedFolders.containsKey(mediaId)) {
            Log.i(TAG, "getFolderItems: Grabbing cached data for mediaId: " + mediaId);
            cb.run(0, mediaId, Util.cloneList(this.mCachedFolders.get(mediaId)));
            return true;
        }
        if (cb == null) {
            Log.wtf(TAG, "getFolderItems: Trying to connect to " + this.mPackageName + "with null browse callback");
        }
        if (DEBUG) {
            Log.d(TAG, "getFolderItems: Connecting to browsable player: " + this.mPackageName);
        }
        return setCallbackAndConnect(new ConnectionCallback() { // from class: com.android.bluetooth.avrcp.-$$Lambda$BrowsedPlayerWrapper$ZQfrcP6Q-FZjlAbeV7hnhKJjx00
            @Override // com.android.bluetooth.avrcp.BrowsedPlayerWrapper.ConnectionCallback
            public final void run(int i, BrowsedPlayerWrapper browsedPlayerWrapper) {
                this.f$0.lambda$getFolderItems$3$BrowsedPlayerWrapper(cb, mediaId, i, browsedPlayerWrapper);
            }
        });
    }

    public /* synthetic */ void lambda$getFolderItems$3$BrowsedPlayerWrapper(BrowseCallback cb, String mediaId, int status, BrowsedPlayerWrapper wrapper) {
        Log.i(TAG, "getFolderItems: Connected to browsable player: " + this.mPackageName);
        if (status != 0) {
            cb.run(status, INetd.NEXTHOP_NONE, new ArrayList());
        }
        getFolderItemsInternal(mediaId, cb);
    }

    private boolean getFolderItemsInternal(String mediaId, BrowseCallback cb) {
        this.mWrappedBrowser.subscribe(mediaId, new BrowserSubscriptionCallback(cb));
        return true;
    }

    class MediaConnectionCallback extends MediaBrowser.ConnectionCallback {
        MediaConnectionCallback() {
        }

        @Override // android.media.browse.MediaBrowser.ConnectionCallback
        public void onConnected() {
            Log.i(BrowsedPlayerWrapper.TAG, "onConnected: " + BrowsedPlayerWrapper.this.mPackageName + " is connected");
            BrowsedPlayerWrapper browsedPlayerWrapper = BrowsedPlayerWrapper.this;
            browsedPlayerWrapper.mRoot = browsedPlayerWrapper.mWrappedBrowser.getRoot();
            if (BrowsedPlayerWrapper.this.mRoot == null || BrowsedPlayerWrapper.this.mRoot.isEmpty()) {
                BrowsedPlayerWrapper browsedPlayerWrapper2 = BrowsedPlayerWrapper.this;
                browsedPlayerWrapper2.executeCallback(1, browsedPlayerWrapper2);
            } else {
                BrowsedPlayerWrapper browsedPlayerWrapper3 = BrowsedPlayerWrapper.this;
                browsedPlayerWrapper3.executeCallback(0, browsedPlayerWrapper3);
            }
        }

        @Override // android.media.browse.MediaBrowser.ConnectionCallback
        public void onConnectionFailed() {
            Log.w(BrowsedPlayerWrapper.TAG, "onConnectionFailed: Connection Failed with " + BrowsedPlayerWrapper.this.mPackageName);
            BrowsedPlayerWrapper browsedPlayerWrapper = BrowsedPlayerWrapper.this;
            browsedPlayerWrapper.executeCallback(1, browsedPlayerWrapper);
            BrowsedPlayerWrapper.this.clearCallback();
        }

        @Override // android.media.browse.MediaBrowser.ConnectionCallback
        public void onConnectionSuspended() {
            BrowsedPlayerWrapper browsedPlayerWrapper = BrowsedPlayerWrapper.this;
            browsedPlayerWrapper.executeCallback(1, browsedPlayerWrapper);
            BrowsedPlayerWrapper.this.disconnect();
            Log.i(BrowsedPlayerWrapper.TAG, "onConnectionSuspended: Connection Suspended with " + BrowsedPlayerWrapper.this.mPackageName);
        }
    }

    class TimeoutHandler extends Handler {
        static final long CALLBACK_TIMEOUT_MS = 5000;
        static final int MSG_TIMEOUT = 0;
        private PlaybackCallback mPlaybackCallback;

        TimeoutHandler(Looper looper, PlaybackCallback cb) {
            super(looper);
            this.mPlaybackCallback = null;
            this.mPlaybackCallback = cb;
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (msg.what != 0) {
                Log.wtf(BrowsedPlayerWrapper.TAG, "Unknown message on timeout handler: " + msg.what);
                return;
            }
            Log.e(BrowsedPlayerWrapper.TAG, "Timeout while waiting for playback to begin on " + BrowsedPlayerWrapper.this.mPackageName);
            this.mPlaybackCallback.run(3);
        }
    }

    class MediaPlaybackListener extends MediaController.Callback {
        private MediaController mController;
        private Looper mLooper;
        private PlaybackCallback mPlaybackCallback;
        private Handler mTimeoutHandler;
        private final Object mTimeoutHandlerLock;

        MediaPlaybackListener(Looper looper, MediaController controller) {
            Object obj = new Object();
            this.mTimeoutHandlerLock = obj;
            this.mTimeoutHandler = null;
            this.mLooper = null;
            this.mController = null;
            this.mPlaybackCallback = null;
            synchronized (obj) {
                this.mController = controller;
                this.mLooper = looper;
            }
        }

        void waitForPlayback(PlaybackCallback cb) {
            synchronized (this.mTimeoutHandlerLock) {
                this.mPlaybackCallback = cb;
                PlaybackState state = this.mController.getPlaybackState();
                if (state == null || state.getState() != 3) {
                    Log.d(BrowsedPlayerWrapper.TAG, "MediaPlayback: Waiting for media to play for " + BrowsedPlayerWrapper.this.mPackageName);
                    TimeoutHandler timeoutHandler = BrowsedPlayerWrapper.this.new TimeoutHandler(this.mLooper, this.mPlaybackCallback);
                    this.mTimeoutHandler = timeoutHandler;
                    this.mController.registerCallback(this, timeoutHandler);
                    this.mTimeoutHandler.sendEmptyMessageDelayed(0, 5000L);
                } else {
                    Log.d(BrowsedPlayerWrapper.TAG, "MediaPlayback: Media is already playing for " + BrowsedPlayerWrapper.this.mPackageName);
                    this.mPlaybackCallback.run(0);
                    cleanup();
                }
            }
        }

        void cleanup() {
            synchronized (this.mTimeoutHandlerLock) {
                if (this.mController != null) {
                    this.mController.unregisterCallback(this);
                }
                this.mController = null;
                if (this.mTimeoutHandler != null) {
                    this.mTimeoutHandler.removeMessages(0);
                }
                this.mTimeoutHandler = null;
                this.mPlaybackCallback = null;
            }
        }

        @Override // android.media.session.MediaController.Callback
        public void onPlaybackStateChanged(PlaybackState state) {
            if (BrowsedPlayerWrapper.DEBUG) {
                Log.d(BrowsedPlayerWrapper.TAG, "MediaPlayback: " + BrowsedPlayerWrapper.this.mPackageName + " -> " + state.toString());
            }
            if (state.getState() == 3) {
                this.mTimeoutHandler.removeMessages(0);
                this.mPlaybackCallback.run(0);
                cleanup();
            }
        }
    }

    private class BrowserSubscriptionCallback extends MediaBrowser.SubscriptionCallback {
        BrowseCallback mBrowseCallback;

        BrowserSubscriptionCallback(BrowseCallback cb) {
            this.mBrowseCallback = null;
            this.mBrowseCallback = cb;
        }

        @Override // android.media.browse.MediaBrowser.SubscriptionCallback
        public void onChildrenLoaded(String parentId, List<MediaBrowser.MediaItem> children) {
            if (BrowsedPlayerWrapper.DEBUG) {
                Log.d(BrowsedPlayerWrapper.TAG, "onChildrenLoaded: mediaId=" + parentId + " size= " + children.size());
            }
            if (this.mBrowseCallback == null) {
                Log.w(BrowsedPlayerWrapper.TAG, "onChildrenLoaded: " + BrowsedPlayerWrapper.this.mPackageName + " children loaded while callback is null");
            }
            BrowsedPlayerWrapper.this.mWrappedBrowser.unsubscribe(parentId);
            ArrayList<ListItem> return_list = new ArrayList<>();
            for (MediaBrowser.MediaItem item : children) {
                if (BrowsedPlayerWrapper.DEBUG) {
                    Log.d(BrowsedPlayerWrapper.TAG, "onChildrenLoaded: Child=\"" + item.toString() + "\",  ID=\"" + item.getMediaId() + "\"");
                }
                if (item.isBrowsable()) {
                    CharSequence titleCharSequence = item.getDescription().getTitle();
                    String title = "Not Provided";
                    if (titleCharSequence != null) {
                        title = titleCharSequence.toString();
                    }
                    Folder f = new Folder(item.getMediaId(), false, title);
                    return_list.add(new ListItem(f));
                } else {
                    return_list.add(new ListItem(Util.toMetadata(item)));
                }
            }
            BrowsedPlayerWrapper.this.mCachedFolders.put(parentId, return_list);
            this.mBrowseCallback.run(0, parentId, Util.cloneList(return_list));
            this.mBrowseCallback = null;
            BrowsedPlayerWrapper.this.disconnect();
        }

        @Override // android.media.browse.MediaBrowser.SubscriptionCallback
        public void onError(String id) {
            Log.e(BrowsedPlayerWrapper.TAG, "BrowserSubscriptionCallback: Could not get folder items");
            this.mBrowseCallback.run(2, id, new ArrayList());
            BrowsedPlayerWrapper.this.disconnect();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Browsable Package Name: " + this.mPackageName + "\n");
        sb.append("   Cached Media ID's: ");
        for (String id : this.mCachedFolders.keySet()) {
            sb.append("\"" + id + "\", ");
        }
        sb.append("\n");
        return sb.toString();
    }
}
