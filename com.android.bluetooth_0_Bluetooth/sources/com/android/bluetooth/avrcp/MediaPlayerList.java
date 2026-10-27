package com.android.bluetooth.avrcp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.media.AudioManager;
import android.media.AudioPlaybackConfiguration;
import android.media.session.MediaSession;
import android.media.session.MediaSessionManager;
import android.media.session.PlaybackState;
import android.net.INetd;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import androidx.media.MediaBrowserServiceCompat;
import com.android.bluetooth.Utils;
import com.android.bluetooth.avrcp.AvrcpTargetService;
import com.android.bluetooth.avrcp.BrowsablePlayerConnector;
import com.android.bluetooth.avrcp.BrowsedPlayerWrapper;
import com.android.bluetooth.avrcp.MediaPlayerWrapper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class MediaPlayerList {
    private static final int ACTIVE_PLAYER_LOGGER_SIZE = 5;
    private static final String ACTIVE_PLAYER_LOGGER_TITLE = "Active Player Events";
    private static final int AUDIO_PLAYBACK_STATE_LOGGER_SIZE = 15;
    private static final String AUDIO_PLAYBACK_STATE_LOGGER_TITLE = "Audio Playback State Events";
    private static final int BLUETOOTH_PLAYER_ID = 0;
    private static final String BLUETOOTH_PLAYER_NAME = "Bluetooth Player";
    private static final String BROWSE_ID_PATTERN = "\\d\\d.*";
    private static final boolean DEBUG = true;
    private static final String NOW_PLAYING_ID_PATTERN = "NowPlayingId([0-9]*)";
    private static final int NO_ACTIVE_PLAYER = 0;
    private static final String PACKAGE_SCHEME = "package";
    private static final String TAG = "AvrcpMediaPlayerList";
    static boolean sTesting = false;
    private final AudioManager mAudioManager;
    private BrowsablePlayerConnector mBrowsablePlayerConnector;
    private AvrcpTargetService.ListCallback mCallback;
    private Context mContext;
    private Looper mLooper;
    private MediaSessionManager mMediaSessionManager;
    private PackageManager mPackageManager;
    private MediaData mCurrMediaData = null;
    private final AvrcpEventLogger mActivePlayerLogger = new AvrcpEventLogger(5, ACTIVE_PLAYER_LOGGER_TITLE);
    private final AvrcpEventLogger mAudioPlaybackStateLogger = new AvrcpEventLogger(15, AUDIO_PLAYBACK_STATE_LOGGER_TITLE);
    private Map<Integer, MediaPlayerWrapper> mMediaPlayers = Collections.synchronizedMap(new HashMap());
    private Map<String, Integer> mMediaPlayerIds = Collections.synchronizedMap(new HashMap());
    private Map<Integer, BrowsedPlayerWrapper> mBrowsablePlayers = Collections.synchronizedMap(new HashMap());
    private int mActivePlayerId = 0;
    private boolean mAudioPlaybackIsActive = false;
    private final MediaSessionManager.OnActiveSessionsChangedListener mActiveSessionsChangedListener = new MediaSessionManager.OnActiveSessionsChangedListener() { // from class: com.android.bluetooth.avrcp.MediaPlayerList.1
        @Override // android.media.session.MediaSessionManager.OnActiveSessionsChangedListener
        public void onActiveSessionsChanged(List<android.media.session.MediaController> newControllers) {
            synchronized (MediaPlayerList.this) {
                Log.v(MediaPlayerList.TAG, "onActiveSessionsChanged: number of controllers: " + newControllers.size());
                if (newControllers.size() == 0) {
                    return;
                }
                HashSet<String> addedPackages = new HashSet<>();
                for (int i = 0; i < newControllers.size(); i++) {
                    Log.d(MediaPlayerList.TAG, "onActiveSessionsChanged: controller: " + newControllers.get(i).getPackageName());
                    if (!addedPackages.contains(newControllers.get(i).getPackageName())) {
                        addedPackages.add(newControllers.get(i).getPackageName());
                        MediaPlayerList.this.addMediaPlayer(newControllers.get(i));
                    }
                }
            }
        }
    };
    private final BroadcastReceiver mPackageChangedBroadcastReceiver = new BroadcastReceiver() { // from class: com.android.bluetooth.avrcp.MediaPlayerList.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String packageName;
            String action = intent.getAction();
            Log.v(MediaPlayerList.TAG, "mPackageChangedBroadcastReceiver: action: " + action);
            if (action.equals("android.intent.action.PACKAGE_REMOVED") || action.equals("android.intent.action.PACKAGE_DATA_CLEARED")) {
                if (intent.getBooleanExtra("android.intent.extra.REPLACING", false)) {
                    return;
                }
                String packageName2 = intent.getData().getSchemeSpecificPart();
                if (MediaPlayerList.this.haveMediaPlayer(packageName2)) {
                    MediaPlayerList mediaPlayerList = MediaPlayerList.this;
                    mediaPlayerList.removeMediaPlayer(((Integer) mediaPlayerList.mMediaPlayerIds.get(packageName2)).intValue());
                    return;
                }
                return;
            }
            if ((action.equals("android.intent.action.PACKAGE_ADDED") || action.equals("android.intent.action.PACKAGE_CHANGED")) && (packageName = intent.getData().getSchemeSpecificPart()) != null) {
                Log.d(MediaPlayerList.TAG, "Name of package changed: " + packageName);
            }
        }
    };
    private final AudioManager.AudioPlaybackCallback mAudioPlaybackCallback = new AudioManager.AudioPlaybackCallback() { // from class: com.android.bluetooth.avrcp.MediaPlayerList.3
        @Override // android.media.AudioManager.AudioPlaybackCallback
        public void onPlaybackConfigChanged(List<AudioPlaybackConfiguration> configs) {
            if (configs == null) {
                return;
            }
            boolean isActive = false;
            AudioPlaybackConfiguration activeConfig = null;
            for (AudioPlaybackConfiguration config : configs) {
                if (config.isActive() && config.getAudioAttributes().getUsage() == 12 && config.getAudioAttributes().getContentType() == 1) {
                    activeConfig = config;
                    isActive = true;
                }
            }
            if (isActive != MediaPlayerList.this.mAudioPlaybackIsActive) {
                AvrcpEventLogger avrcpEventLogger = MediaPlayerList.this.mAudioPlaybackStateLogger;
                StringBuilder sb = new StringBuilder();
                sb.append("onPlaybackConfigChanged: ");
                sb.append(MediaPlayerList.this.mAudioPlaybackIsActive ? "Active" : "Non-active");
                sb.append(" -> ");
                sb.append(isActive ? "Active" : "Non-active");
                avrcpEventLogger.logd(true, MediaPlayerList.TAG, sb.toString());
                if (isActive) {
                    MediaPlayerList.this.mAudioPlaybackStateLogger.logd(true, MediaPlayerList.TAG, "onPlaybackConfigChanged: active config: " + activeConfig);
                }
                MediaPlayerList.this.mAudioPlaybackIsActive = isActive;
                MediaPlayerList.this.updateMediaForAudioPlayback();
            }
        }
    };
    private final MediaPlayerWrapper.Callback mMediaPlayerCallback = new MediaPlayerWrapper.Callback() { // from class: com.android.bluetooth.avrcp.MediaPlayerList.4
        @Override // com.android.bluetooth.avrcp.MediaPlayerWrapper.Callback
        public void mediaUpdatedCallback(MediaData data) {
            if (data.metadata == null) {
                Log.d(MediaPlayerList.TAG, "mediaUpdatedCallback(): metadata is null");
                return;
            }
            if (data.state != null) {
                if (!MediaPlayerList.this.mAudioPlaybackIsActive || data.state.getState() == 3) {
                    MediaPlayerList.this.sendMediaUpdate(data);
                    return;
                } else {
                    Log.d(MediaPlayerList.TAG, "Some audio playbacks are still active, drop it");
                    return;
                }
            }
            Log.w(MediaPlayerList.TAG, "mediaUpdatedCallback(): Tried to update with null state");
        }

        @Override // com.android.bluetooth.avrcp.MediaPlayerWrapper.Callback
        public void sessionUpdatedCallback(String packageName) {
            if (MediaPlayerList.this.haveMediaPlayer(packageName)) {
                Log.d(MediaPlayerList.TAG, "sessionUpdatedCallback(): packageName: " + packageName);
                MediaPlayerList mediaPlayerList = MediaPlayerList.this;
                mediaPlayerList.removeMediaPlayer(((Integer) mediaPlayerList.mMediaPlayerIds.get(packageName)).intValue());
            }
        }
    };
    private final MediaSessionManager.OnMediaKeyEventSessionChangedListener mMediaKeyEventSessionChangedListener = new MediaSessionManager.OnMediaKeyEventSessionChangedListener() { // from class: com.android.bluetooth.avrcp.MediaPlayerList.5
        @Override // android.media.session.MediaSessionManager.OnMediaKeyEventSessionChangedListener
        public void onMediaKeyEventSessionChanged(String packageName, MediaSession.Token token) {
            if (MediaPlayerList.this.mMediaSessionManager == null) {
                Log.w(MediaPlayerList.TAG, "onMediaKeyEventSessionChanged(): Unexpected callback from the MediaSessionManager, pkg" + packageName + ", token=" + token);
                return;
            }
            if (TextUtils.isEmpty(packageName)) {
                return;
            }
            if (token != null) {
                android.media.session.MediaController controller = new android.media.session.MediaController(MediaPlayerList.this.mContext, token);
                if (!MediaPlayerList.this.haveMediaPlayer(controller.getPackageName())) {
                    Log.w(MediaPlayerList.TAG, "onMediaKeyEventSessionChanged(Token): Addressed Player changed to a player we didn't have a session for");
                    MediaPlayerList.this.addMediaPlayer(controller);
                }
                Log.i(MediaPlayerList.TAG, "onMediaKeyEventSessionChanged: token=" + controller.getPackageName());
                MediaPlayerList mediaPlayerList = MediaPlayerList.this;
                mediaPlayerList.setActivePlayer(((Integer) mediaPlayerList.mMediaPlayerIds.get(controller.getPackageName())).intValue());
                return;
            }
            if (!MediaPlayerList.this.haveMediaPlayer(packageName)) {
                MediaPlayerList.e("onMediaKeyEventSessionChanged(PackageName): Media key event session changed to a player we don't have a session for");
                return;
            }
            Log.i(MediaPlayerList.TAG, "onMediaKeyEventSessionChanged: packageName=" + packageName);
            MediaPlayerList mediaPlayerList2 = MediaPlayerList.this;
            mediaPlayerList2.setActivePlayer(((Integer) mediaPlayerList2.mMediaPlayerIds.get(packageName)).intValue());
        }
    };

    interface FolderUpdateCallback {
        void run(boolean z, boolean z2, boolean z3);
    }

    interface GetFolderItemsCallback {
        void run(String str, List<ListItem> list);
    }

    interface GetPlayerRootCallback {
        void run(int i, boolean z, String str, int i2);
    }

    interface MediaUpdateCallback {
        void run(MediaData mediaData);
    }

    MediaPlayerList(Looper looper, Context context) {
        Log.v(TAG, "Creating MediaPlayerList");
        this.mLooper = looper;
        this.mContext = context;
        IntentFilter pkgFilter = new IntentFilter();
        pkgFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        pkgFilter.addAction("android.intent.action.PACKAGE_DATA_CLEARED");
        pkgFilter.addAction("android.intent.action.PACKAGE_ADDED");
        pkgFilter.addAction("android.intent.action.PACKAGE_CHANGED");
        pkgFilter.addDataScheme(PACKAGE_SCHEME);
        context.registerReceiver(this.mPackageChangedBroadcastReceiver, pkgFilter);
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        this.mAudioManager = audioManager;
        audioManager.registerAudioPlaybackCallback(this.mAudioPlaybackCallback, new Handler(this.mLooper));
        MediaSessionManager mediaSessionManager = (MediaSessionManager) context.getSystemService("media_session");
        this.mMediaSessionManager = mediaSessionManager;
        mediaSessionManager.addOnActiveSessionsChangedListener(this.mActiveSessionsChangedListener, null, new Handler(looper));
        this.mMediaSessionManager.addOnMediaKeyEventSessionChangedListener(this.mContext.getMainExecutor(), this.mMediaKeyEventSessionChangedListener);
    }

    void init(AvrcpTargetService.ListCallback callback) {
        Log.v(TAG, "Initializing MediaPlayerList");
        this.mCallback = callback;
        Intent intent = new Intent(MediaBrowserServiceCompat.SERVICE_INTERFACE);
        List<ResolveInfo> playerList = this.mContext.getApplicationContext().getPackageManager().queryIntentServices(intent, 131072);
        this.mBrowsablePlayerConnector = BrowsablePlayerConnector.connectToPlayers(this.mContext, this.mLooper, playerList, new BrowsablePlayerConnector.PlayerListCallback() { // from class: com.android.bluetooth.avrcp.-$$Lambda$MediaPlayerList$igObBs4BI0Rh_MdySAaee3CwLhA
            @Override // com.android.bluetooth.avrcp.BrowsablePlayerConnector.PlayerListCallback
            public final void run(List list) {
                this.f$0.lambda$init$1$MediaPlayerList(list);
            }
        });
    }

    public /* synthetic */ void lambda$init$1$MediaPlayerList(List players) {
        Log.i(TAG, "init: Browsable Player list size is " + players.size());
        if (this.mMediaSessionManager == null) {
            return;
        }
        Iterator it = players.iterator();
        while (it.hasNext()) {
            BrowsedPlayerWrapper wrapper = (BrowsedPlayerWrapper) it.next();
            if (!havePlayerId(wrapper.getPackageName())) {
                this.mMediaPlayerIds.put(wrapper.getPackageName(), Integer.valueOf(getFreeMediaPlayerId()));
            }
            d("Adding Browser Wrapper for " + wrapper.getPackageName() + " with id " + this.mMediaPlayerIds.get(wrapper.getPackageName()));
            this.mBrowsablePlayers.put(this.mMediaPlayerIds.get(wrapper.getPackageName()), wrapper);
            wrapper.getFolderItems(wrapper.getRootId(), new BrowsedPlayerWrapper.BrowseCallback() { // from class: com.android.bluetooth.avrcp.-$$Lambda$MediaPlayerList$mGTItNIqyZg9XtVDDr_evulck_s
                @Override // com.android.bluetooth.avrcp.BrowsedPlayerWrapper.BrowseCallback
                public final void run(int i, String str, List list) {
                    MediaPlayerList.d("Got the contents for: " + str + " : num results=" + list.size());
                }
            });
        }
        d("Initializing list of current media players");
        List<android.media.session.MediaController> controllers = this.mMediaSessionManager.getActiveSessions(null);
        for (android.media.session.MediaController controller : controllers) {
            addMediaPlayer(controller);
        }
        if (this.mActivePlayerId != 0 || this.mMediaPlayers.size() <= 0) {
            return;
        }
        setActivePlayer(1);
    }

    void cleanup() {
        this.mContext.unregisterReceiver(this.mPackageChangedBroadcastReceiver);
        this.mActivePlayerId = 0;
        this.mMediaSessionManager.removeOnActiveSessionsChangedListener(this.mActiveSessionsChangedListener);
        this.mMediaSessionManager.removeOnMediaKeyEventSessionChangedListener(this.mMediaKeyEventSessionChangedListener);
        this.mMediaSessionManager = null;
        this.mAudioManager.unregisterAudioPlaybackCallback(this.mAudioPlaybackCallback);
        this.mMediaPlayerIds.clear();
        for (MediaPlayerWrapper player : this.mMediaPlayers.values()) {
            player.cleanup();
        }
        this.mMediaPlayers.clear();
        BrowsablePlayerConnector browsablePlayerConnector = this.mBrowsablePlayerConnector;
        if (browsablePlayerConnector != null) {
            browsablePlayerConnector.cleanup();
        }
        for (BrowsedPlayerWrapper player2 : this.mBrowsablePlayers.values()) {
            player2.disconnect();
        }
        this.mBrowsablePlayers.clear();
    }

    int getCurrentPlayerId() {
        return 0;
    }

    int getFreeMediaPlayerId() {
        int id = 1;
        while (this.mMediaPlayerIds.containsValue(Integer.valueOf(id))) {
            id++;
        }
        return id;
    }

    MediaPlayerWrapper getActivePlayer() {
        return this.mMediaPlayers.get(Integer.valueOf(this.mActivePlayerId));
    }

    void getPlayerRoot(int playerId, GetPlayerRootCallback cb) {
        cb.run(playerId, playerId == 0, INetd.NEXTHOP_NONE, this.mBrowsablePlayers.size());
    }

    List<PlayerInfo> getMediaPlayerList() {
        PlayerInfo info = new PlayerInfo();
        info.id = 0;
        info.name = BLUETOOTH_PLAYER_NAME;
        info.browsable = true;
        if (this.mBrowsablePlayers.size() == 0) {
            info.browsable = false;
        }
        List<PlayerInfo> ret = new ArrayList<>();
        ret.add(info);
        return ret;
    }

    String getCurrentMediaId() {
        MediaPlayerWrapper player = getActivePlayer();
        if (player == null) {
            return INetd.NEXTHOP_NONE;
        }
        PlaybackState state = player.getPlaybackState();
        List<Metadata> queue = player.getCurrentQueue();
        if (state == null || state.getActiveQueueItemId() == -1 || queue.size() == 0) {
            d("getCurrentMediaId: No active queue item Id sending empty mediaId: PlaybackState=" + state);
            return INetd.NEXTHOP_NONE;
        }
        return Util.NOW_PLAYING_PREFIX + state.getActiveQueueItemId();
    }

    Metadata getCurrentSongInfo() {
        MediaPlayerWrapper player = getActivePlayer();
        return player == null ? Util.empty_data() : player.getCurrentMetadata();
    }

    PlaybackState getCurrentPlayStatus() {
        MediaPlayerWrapper player = getActivePlayer();
        if (player == null) {
            return null;
        }
        PlaybackState state = player.getPlaybackState();
        if (this.mAudioPlaybackIsActive && (state == null || state.getState() != 3)) {
            return new PlaybackState.Builder().setState(3, state == null ? 0L : state.getPosition(), 1.0f).build();
        }
        return state;
    }

    List<Metadata> getNowPlayingList() {
        if (getCurrentMediaId().equals(INetd.NEXTHOP_NONE)) {
            List<Metadata> ret = new ArrayList<>();
            Metadata data = getCurrentSongInfo();
            data.mediaId = INetd.NEXTHOP_NONE;
            ret.add(data);
            return ret;
        }
        return getActivePlayer().getCurrentQueue();
    }

    void playItem(int playerId, boolean nowPlaying, String mediaId) {
        if (nowPlaying) {
            playNowPlayingItem(mediaId);
        } else {
            playFolderItem(mediaId);
        }
    }

    private void playNowPlayingItem(String mediaId) {
        d("playNowPlayingItem: mediaId=" + mediaId);
        Pattern regex = Pattern.compile(NOW_PLAYING_ID_PATTERN);
        Matcher m = regex.matcher(mediaId);
        if (!m.find()) {
            Log.wtf(TAG, "playNowPlayingItem: Couldn't match mediaId to pattern: mediaId=" + mediaId);
        }
        long queueItemId = Long.parseLong(m.group(1));
        if (getActivePlayer() != null) {
            getActivePlayer().playItemFromQueue(queueItemId);
        }
    }

    private void playFolderItem(String mediaId) {
        d("playFolderItem: mediaId=" + mediaId);
        if (!mediaId.matches(BROWSE_ID_PATTERN)) {
            Log.wtf(TAG, "playFolderItem: mediaId didn't match pattern: mediaId=" + mediaId);
        }
        int playerIndex = Integer.parseInt(mediaId.substring(0, 2));
        String itemId = mediaId.substring(2);
        if (!haveMediaBrowser(playerIndex)) {
            e("playFolderItem: Do not have the a browsable player with ID " + playerIndex);
            return;
        }
        this.mBrowsablePlayers.get(Integer.valueOf(playerIndex)).playItem(itemId);
    }

    void getFolderItemsMediaPlayerList(GetFolderItemsCallback cb) {
        d("getFolderItemsMediaPlayerList: Sending Media Player list for root directory");
        ArrayList<ListItem> playerList = new ArrayList<>();
        for (BrowsedPlayerWrapper player : this.mBrowsablePlayers.values()) {
            String displayName = Util.getDisplayName(this.mContext, player.getPackageName());
            int id = this.mMediaPlayerIds.get(player.getPackageName()).intValue();
            d("getFolderItemsMediaPlayerList: Adding player " + displayName);
            Folder playerFolder = new Folder(String.format("%02d", Integer.valueOf(id)), false, displayName);
            playerList.add(new ListItem(playerFolder));
        }
        cb.run(INetd.NEXTHOP_NONE, playerList);
    }

    void getFolderItems(int playerId, final String mediaId, final GetFolderItemsCallback cb) {
        d("getFolderItems(): playerId=" + playerId + ", mediaId=" + mediaId);
        if (mediaId.equals(INetd.NEXTHOP_NONE)) {
            getFolderItemsMediaPlayerList(cb);
            return;
        }
        if (!mediaId.matches(BROWSE_ID_PATTERN)) {
            Log.wtf(TAG, "getFolderItems: mediaId didn't match pattern: mediaId=" + mediaId);
        }
        final int playerIndex = Integer.parseInt(mediaId.substring(0, 2));
        String itemId = mediaId.substring(2);
        if (haveMediaBrowser(playerIndex)) {
            BrowsedPlayerWrapper wrapper = this.mBrowsablePlayers.get(Integer.valueOf(playerIndex));
            if (itemId.equals(INetd.NEXTHOP_NONE)) {
                Log.i(TAG, "Empty media id, getting the root for " + wrapper.getPackageName());
                itemId = wrapper.getRootId();
            }
            wrapper.getFolderItems(itemId, new BrowsedPlayerWrapper.BrowseCallback() { // from class: com.android.bluetooth.avrcp.-$$Lambda$MediaPlayerList$RgCGuiaKEOqIViMEnFASlfkfeGM
                @Override // com.android.bluetooth.avrcp.BrowsedPlayerWrapper.BrowseCallback
                public final void run(int i, String str, List list) {
                    MediaPlayerList.lambda$getFolderItems$2(cb, mediaId, playerIndex, i, str, list);
                }
            });
            return;
        }
        cb.run(mediaId, new ArrayList());
    }

    static /* synthetic */ void lambda$getFolderItems$2(GetFolderItemsCallback cb, String mediaId, int playerIndex, int status, String id, List results) {
        if (status != 0) {
            cb.run(mediaId, new ArrayList());
            return;
        }
        String playerPrefix = String.format("%02d", Integer.valueOf(playerIndex));
        Iterator it = results.iterator();
        while (it.hasNext()) {
            ListItem item = (ListItem) it.next();
            if (item.isFolder) {
                item.folder.mediaId = playerPrefix.concat(item.folder.mediaId);
            } else {
                item.song.mediaId = playerPrefix.concat(item.song.mediaId);
            }
        }
        cb.run(mediaId, results);
    }

    int addMediaPlayer(MediaController controller) {
        String packageName = controller.getPackageName();
        if (!havePlayerId(packageName)) {
            this.mMediaPlayerIds.put(packageName, Integer.valueOf(getFreeMediaPlayerId()));
        }
        int playerId = this.mMediaPlayerIds.get(packageName).intValue();
        if (haveMediaPlayer(playerId)) {
            d("Already have a controller for the player: " + packageName + ", updating instead");
            MediaPlayerWrapper player = this.mMediaPlayers.get(Integer.valueOf(playerId));
            player.updateMediaController(controller);
            if (playerId == this.mActivePlayerId) {
                sendMediaUpdate(getActivePlayer().getCurrentMediaData());
            }
            return playerId;
        }
        MediaPlayerWrapper newPlayer = MediaPlayerWrapperFactory.wrap(controller, this.mLooper);
        Log.i(TAG, "Adding wrapped media player: " + packageName + " at key: " + this.mMediaPlayerIds.get(controller.getPackageName()));
        this.mMediaPlayers.put(Integer.valueOf(playerId), newPlayer);
        return playerId;
    }

    int addMediaPlayer(android.media.session.MediaController controller) {
        if (controller == null) {
            e("Trying to add a null MediaController");
            return -1;
        }
        return addMediaPlayer(MediaControllerFactory.wrap(controller));
    }

    boolean havePlayerId(String packageName) {
        if (packageName == null) {
            return false;
        }
        return this.mMediaPlayerIds.containsKey(packageName);
    }

    boolean haveMediaPlayer(String packageName) {
        if (!havePlayerId(packageName)) {
            return false;
        }
        int playerId = this.mMediaPlayerIds.get(packageName).intValue();
        return this.mMediaPlayers.containsKey(Integer.valueOf(playerId));
    }

    boolean haveMediaPlayer(int playerId) {
        return this.mMediaPlayers.containsKey(Integer.valueOf(playerId));
    }

    boolean haveMediaBrowser(int playerId) {
        return this.mBrowsablePlayers.containsKey(Integer.valueOf(playerId));
    }

    void removeMediaPlayer(int playerId) {
        if (!haveMediaPlayer(playerId)) {
            e("Trying to remove nonexistent media player: " + playerId);
            return;
        }
        if (playerId == this.mActivePlayerId && playerId != 0) {
            getActivePlayer().unregisterCallback();
            this.mActivePlayerId = 0;
            List<Metadata> queue = new ArrayList<>();
            queue.add(Util.empty_data());
            MediaData newData = new MediaData(Util.empty_data(), null, queue);
            sendMediaUpdate(newData);
        }
        MediaPlayerWrapper wrapper = this.mMediaPlayers.get(Integer.valueOf(playerId));
        d("Removing media player " + wrapper.getPackageName());
        this.mMediaPlayers.remove(Integer.valueOf(playerId));
        if (!haveMediaBrowser(playerId)) {
            d(wrapper.getPackageName() + " doesn't have a browse service. Recycle player ID.");
            this.mMediaPlayerIds.remove(wrapper.getPackageName());
        }
        wrapper.cleanup();
    }

    void setActivePlayer(int playerId) {
        if (!haveMediaPlayer(playerId)) {
            e("Player doesn't exist in list(): " + playerId);
            return;
        }
        int i = this.mActivePlayerId;
        if (playerId == i) {
            Log.w(TAG, getActivePlayer().getPackageName() + " is already the active player");
            return;
        }
        if (i != 0) {
            getActivePlayer().unregisterCallback();
        }
        this.mActivePlayerId = playerId;
        getActivePlayer().registerCallback(this.mMediaPlayerCallback);
        this.mActivePlayerLogger.logd(TAG, "setActivePlayer(): setting player to " + getActivePlayer().getPackageName());
        if (!getActivePlayer().isMetadataSynced()) {
            Log.w(TAG, "setActivePlayer(): Metadata not synced on new player");
            return;
        }
        if (Utils.isPtsTestMode()) {
            sendFolderUpdate(true, true, false);
        }
        MediaData data = getActivePlayer().getCurrentMediaData();
        if (this.mAudioPlaybackIsActive) {
            data.state = this.mCurrMediaData.state;
            Log.d(TAG, "setActivePlayer mAudioPlaybackIsActive=true, state=" + data.state);
        }
        sendMediaUpdate(data);
    }

    void sendMediaKeyEvent(int i, boolean z) {
        d("sendMediaKeyEvent: key=" + i + " pushed=" + z);
        this.mMediaSessionManager.dispatchMediaKeyEvent(new KeyEvent(!z ? 1 : 0, AvrcpPassthrough.toKeyCode(i)));
    }

    private void sendFolderUpdate(boolean availablePlayers, boolean addressedPlayers, boolean uids) {
        d("sendFolderUpdate");
        AvrcpTargetService.ListCallback listCallback = this.mCallback;
        if (listCallback == null) {
            return;
        }
        listCallback.run(availablePlayers, addressedPlayers, uids);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMediaUpdate(MediaData data) {
        d("sendMediaUpdate");
        if (this.mCallback == null) {
            return;
        }
        if (data.queue.size() == 0) {
            Log.i(TAG, "sendMediaUpdate: Creating a one item queue for a player with no queue");
            data.queue.add(data.metadata);
        }
        Log.d(TAG, "sendMediaUpdate state=" + data.state);
        this.mCurrMediaData = data;
        this.mCallback.run(data);
    }

    void updateMediaForAudioPlayback() {
        MediaData currMediaData;
        PlaybackState currState = null;
        if (getActivePlayer() == null) {
            Log.d(TAG, "updateMediaForAudioPlayback: no active player");
            PlaybackState.Builder builder = new PlaybackState.Builder().setState(1, 0L, 0.0f);
            List<Metadata> queue = new ArrayList<>();
            queue.add(Util.empty_data());
            currMediaData = new MediaData(Util.empty_data(), builder.build(), queue);
        } else {
            currMediaData = getActivePlayer().getCurrentMediaData();
            currState = currMediaData.state;
        }
        if (currState != null && currState.getState() == 3) {
            Log.i(TAG, "updateMediaForAudioPlayback: Active player is playing, drop it");
            return;
        }
        if (this.mAudioPlaybackIsActive) {
            PlaybackState.Builder builder2 = new PlaybackState.Builder().setState(3, currState != null ? currState.getPosition() : 0L, 1.0f);
            currMediaData.state = builder2.build();
        }
        this.mAudioPlaybackStateLogger.logd(TAG, "updateMediaForAudioPlayback: update state=" + currMediaData.state);
        sendMediaUpdate(currMediaData);
    }

    void injectAudioPlaybacActive(boolean isActive) {
        this.mAudioPlaybackIsActive = isActive;
        updateMediaForAudioPlayback();
    }

    void dump(StringBuilder sb) {
        sb.append("List of MediaControllers: size=" + this.mMediaPlayers.size() + "\n");
        Iterator<Integer> it = this.mMediaPlayers.keySet().iterator();
        while (it.hasNext()) {
            int id = it.next().intValue();
            if (id == this.mActivePlayerId) {
                sb.append("<Active> ");
            }
            MediaPlayerWrapper player = this.mMediaPlayers.get(Integer.valueOf(id));
            sb.append("  Media Player " + id + ": " + player.getPackageName() + "\n");
            sb.append(player.toString().replaceAll("(?m)^", "  "));
            sb.append("\n");
        }
        sb.append("List of Browsers: size=" + this.mBrowsablePlayers.size() + "\n");
        Iterator<BrowsedPlayerWrapper> it2 = this.mBrowsablePlayers.values().iterator();
        while (it2.hasNext()) {
            sb.append(it2.next().toString().replaceAll("(?m)^", "  "));
            sb.append("\n");
        }
        this.mActivePlayerLogger.dump(sb);
        sb.append("\n");
        this.mAudioPlaybackStateLogger.dump(sb);
        sb.append("\n");
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
    public static void d(String message) {
        Log.d(TAG, message);
    }
}
