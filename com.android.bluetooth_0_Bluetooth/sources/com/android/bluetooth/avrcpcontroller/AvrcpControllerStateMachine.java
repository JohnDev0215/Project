package com.android.bluetooth.avrcpcontroller;

import android.bluetooth.BluetoothDevice;
import android.car.Car;
import android.car.CarNotConnectedException;
import android.car.media.CarAudioManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemProperties;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import android.util.SparseArray;
import com.android.bluetooth.BluetoothMetricsProto;
import com.android.bluetooth.R;
import com.android.bluetooth.Utils;
import com.android.bluetooth.a2dpsink.A2dpSinkService;
import com.android.bluetooth.avrcpcontroller.AvrcpCoverArtManager;
import com.android.bluetooth.avrcpcontroller.BrowseTree;
import com.android.bluetooth.btservice.AdapterService;
import com.android.bluetooth.btservice.MetricsLogger;
import com.android.bluetooth.btservice.ProfileService;
import com.android.bluetooth.statemachine.State;
import com.android.bluetooth.statemachine.StateMachine;
import com.android.vcard.VCardConfig;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
class AvrcpControllerStateMachine extends StateMachine {
    private static final int ABS_VOL_BASE = 127;
    static final int ABS_VOL_TIMEOUT_MILLIS = 1000;
    public static final String ACTION_CUSTOM_ACTION_RESULT = "android.bluetooth.avrcp-controller.profile.action.CUSTOM_ACTION_RESULT";
    private static final int AVRC_CHARSET_UTF8 = 106;
    private static final String BT_MEDIA_ACTIVE = "sys.mediacenter.bluetooth.active";
    protected static final int CLEANUP = 100;
    static final int CMD_TIMEOUT_MILLIS = 10000;
    public static final int CONNECT = 1;
    private static final int CONNECT_TIMEOUT = 101;
    public static final String CUSTOM_ACTION_ABORT_CONTINUING_RESPONSE = "android.bluetooth.avrcp-controller.profile.action.CUSTOM_ACTION_ABORT_CONTINUING_RESPONSE";
    public static final String CUSTOM_ACTION_ADD_TO_NOW_PLAYING = "android.bluetooth.avrcp-controller.profile.action.CUSTOM_ACTION_ADD_TO_NOW_PLAYING";
    public static final String CUSTOM_ACTION_GET_ELEMENT_ATTR = "android.bluetooth.avrcp-controller.profile.action.CUSTOM_ACTION_GET_ELEMENT_ATTR";
    public static final String CUSTOM_ACTION_GET_FOLDER_ITEM = "android.bluetooth.avrcp-controller.profile.action.CUSTOM_ACTION_GET_FOLDER_ITEM";
    public static final String CUSTOM_ACTION_GET_ITEM_ATTR = "android.bluetooth.avrcp-controller.profile.action.CUSTOM_ACTION_GET_ITEM_ATTR";
    public static final String CUSTOM_ACTION_PLAY_ITEM = "android.bluetooth.avrcp-controller.profile.action.CUSTOM_ACTION_PLAY_ITEM";
    public static final String CUSTOM_ACTION_REQUEST_CONTINUING_RESPONSE = "android.bluetooth.avrcp-controller.profile.action.CUSTOM_ACTION_REQUEST_CONTINUING_RESPONSE";
    public static final String CUSTOM_ACTION_SEARCH = "android.bluetooth.avrcp-controller.profile.action.CUSTOM_ACTION_SEARCH";
    public static final String CUSTOM_ACTION_SEND_PASS_THRU_CMD = "android.bluetooth.avrcp-controller.profile.action.CUSTOM_ACTION_SEND_PASS_THRU_CMD";
    public static final String CUSTOM_ACTION_SET_ADDRESSED_PLAYER = "android.bluetooth.avrcp-controller.profile.action.CUSTOM_ACTION_SET_ADDRESSED_PLAYER";
    public static final int DISCONNECT = 2;
    public static final String EXTRA_CUSTOM_ACTION = "android.bluetooth.avrcp-controller.profile.extra.CUSTOM_ACTION";
    public static final String EXTRA_CUSTOM_ACTION_RESULT = "android.bluetooth.avrcp-controller.profile.extra.CUSTOM_ACTION_RESULT";
    public static final String EXTRA_NUM_OF_ITEMS = "android.bluetooth.avrcp-controller.profile.extra.NUM_OF_ITEMS";
    static final int ITEM_PAGE_SIZE = 20;
    public static final String KEY_ATTRIBUTE_ID = "attribute_id";
    public static final String KEY_BROWSE_SCOPE = "scope";
    public static final String KEY_CMD = "cmd";
    public static final String KEY_END = "end";
    public static final String KEY_PDU_ID = "pdu_id";
    public static final String KEY_PLAYER_ID = "player_id";
    public static final String KEY_SEARCH = "search";
    public static final String KEY_START = "start";
    public static final String KEY_STATE = "state";
    static final int MESSAGE_GET_FOLDER_ITEMS = 300;
    static final int MESSAGE_INTERNAL_ABS_VOL_TIMEOUT = 102;
    static final int MESSAGE_INTERNAL_CMD_TIMEOUT = 201;
    static final int MESSAGE_PLAY_ITEM = 301;
    static final int MESSAGE_PROCESS_ADDRESSED_PLAYER_CHANGED = 215;
    static final int MESSAGE_PROCESS_ADD_TO_NOW_PLAYING = 224;
    static final int MESSAGE_PROCESS_AVAILABLE_PLAYER_CHANGED = 219;
    static final int MESSAGE_PROCESS_CURRENT_APPLICATION_SETTINGS = 218;
    static final int MESSAGE_PROCESS_FOLDER_PATH = 212;
    static final int MESSAGE_PROCESS_GET_FOLDER_ITEMS = 209;
    static final int MESSAGE_PROCESS_GET_FOLDER_ITEMS_OUT_OF_RANGE = 210;
    static final int MESSAGE_PROCESS_GET_PLAYER_ITEMS = 211;
    static final int MESSAGE_PROCESS_IMAGE_DOWNLOADED = 400;
    static final int MESSAGE_PROCESS_NOW_PLAYING_CONTENTS_CHANGED = 216;
    static final int MESSAGE_PROCESS_PLAY_POS_CHANGED = 206;
    static final int MESSAGE_PROCESS_PLAY_STATUS_CHANGED = 207;
    static final int MESSAGE_PROCESS_RC_FEATURES = 223;
    static final int MESSAGE_PROCESS_RECEIVED_COVER_ART_PSM = 220;
    static final int MESSAGE_PROCESS_REGISTER_ABS_VOL_NOTIFICATION = 204;
    static final int MESSAGE_PROCESS_SEARCH_RESP = 221;
    static final int MESSAGE_PROCESS_SET_ABS_VOL_CMD = 203;
    static final int MESSAGE_PROCESS_SET_ADDRESSED_PLAYER = 214;
    static final int MESSAGE_PROCESS_SET_BROWSED_PLAYER = 213;
    static final int MESSAGE_PROCESS_SUPPORTED_APPLICATION_SETTINGS = 217;
    static final int MESSAGE_PROCESS_TRACK_CHANGED = 205;
    static final int MESSAGE_PROCESS_UIDS_CHANGED = 222;
    static final int MESSAGE_PROCESS_VOLUME_CHANGED_NOTIFICATION = 208;
    static final int MSG_AVRCP_ABORT_CONTINUING_RESPONSE = 359;
    static final int MSG_AVRCP_ADD_TO_NOW_PLAYING = 360;
    static final int MSG_AVRCP_FETCH_COVER_ART = 450;
    static final int MSG_AVRCP_GET_ELEMENT_ATTR = 356;
    static final int MSG_AVRCP_GET_FOLDER_ITEMS_PTS = 357;
    static final int MSG_AVRCP_GET_ITEM_ATTR = 355;
    static final int MSG_AVRCP_PASSTHRU = 350;
    static final int MSG_AVRCP_PASSTHRU_EXT = 354;
    static final int MSG_AVRCP_PLAY_ITEM_PTS = 362;
    static final int MSG_AVRCP_REQUEST_CONTINUING_RESPONSE = 358;
    static final int MSG_AVRCP_SEARCH = 353;
    static final int MSG_AVRCP_SET_ADDRESSED_PLAYER_PTS = 361;
    static final int MSG_AVRCP_SET_REPEAT = 352;
    static final int MSG_AVRCP_SET_SHUFFLE = 351;
    private static final byte NOTIFICATION_RSP_TYPE_CHANGED = 1;
    private static final byte NOTIFICATION_RSP_TYPE_INTERIM = 0;
    public static final int RESULT_ERROR = 1;
    public static final int RESULT_INVALID_PARAMETER = 2;
    public static final int RESULT_NOT_SUPPORTED = 3;
    public static final int RESULT_SUCCESS = 0;
    public static final int RESULT_TIMEOUT = 4;
    static final int STACK_EVENT = 200;
    private static int mMaxVolume;
    private static int mVolumeGroupId;
    private static BluetoothDevice sActiveDevice;
    AddToNowPlaying mAddToNowPlaying;
    private AvrcpPlayer mAddressedPlayer;
    private int mAddressedPlayerId;
    private SparseArray<AvrcpPlayer> mAvailablePlayerList;
    final BrowseTree mBrowseTree;
    boolean mBrowsingConnected;
    private Car mCar;
    private CarAudioManager mCarAudioManager;
    protected final Connected mConnected;
    protected final Connecting mConnecting;
    private final ServiceConnection mConnection;
    protected final AvrcpCoverArtManager mCoverArtManager;
    protected int mCoverArtPsm;
    protected final BluetoothDevice mDevice;
    protected final byte[] mDeviceAddress;
    protected final Disconnected mDisconnected;
    protected final Disconnecting mDisconnecting;
    GetFolderList mGetFolderList;
    protected int mMostRecentState;
    boolean mRemoteControlConnected;
    private int mRemoteFeatures;
    protected final Search mSearch;
    protected final AvrcpControllerService mService;
    MediaSessionCompat.Callback mSessionCallbacks;
    private int mUidCounter;
    private final CarAudioManager.CarVolumeCallback mVolumeChangeCallback;
    private int mVolumeChangedNotificationsToIgnore;
    private int mVolumeNotificationLabel;
    static final String TAG = "AvrcpControllerStateMachine";
    static final boolean DBG = Log.isLoggable(TAG, 3);

    static /* synthetic */ int access$508(AvrcpControllerStateMachine x0) {
        int i = x0.mVolumeChangedNotificationsToIgnore;
        x0.mVolumeChangedNotificationsToIgnore = i + 1;
        return i;
    }

    AvrcpControllerStateMachine(BluetoothDevice device, AvrcpControllerService service) {
        super(TAG);
        this.mMostRecentState = 0;
        this.mRemoteControlConnected = false;
        this.mBrowsingConnected = false;
        this.mAddressedPlayer = new AvrcpPlayer();
        this.mAddressedPlayerId = -1;
        this.mUidCounter = 0;
        this.mAvailablePlayerList = new SparseArray<>();
        this.mVolumeChangedNotificationsToIgnore = 0;
        this.mVolumeNotificationLabel = -1;
        this.mGetFolderList = null;
        this.mAddToNowPlaying = null;
        this.mVolumeChangeCallback = new CarAudioManager.CarVolumeCallback() { // from class: com.android.bluetooth.avrcpcontroller.AvrcpControllerStateMachine.1
            @Override // android.car.media.CarAudioManager.CarVolumeCallback
            public void onGroupVolumeChanged(int zoneId, int groupId, int flags) {
                AvrcpControllerStateMachine.logD("zoneId:" + zoneId + ", groupId:" + groupId);
                if (zoneId == 0 && groupId == AvrcpControllerStateMachine.mVolumeGroupId) {
                    int volume = AvrcpControllerStateMachine.this.getAbsVolume();
                    AvrcpControllerStateMachine.logD("notify abs volume changed: " + volume);
                    AvrcpControllerStateMachine.this.mService.sendRegisterAbsVolRspNative(AvrcpControllerStateMachine.this.mDeviceAddress, (byte) 1, volume, AvrcpControllerStateMachine.this.mVolumeNotificationLabel);
                }
            }
        };
        this.mConnection = new ServiceConnection() { // from class: com.android.bluetooth.avrcpcontroller.AvrcpControllerStateMachine.2
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName name, IBinder service2) {
                try {
                    AvrcpControllerStateMachine.this.mCarAudioManager = (CarAudioManager) AvrcpControllerStateMachine.this.mCar.getCarManager("audio");
                    if (AvrcpControllerStateMachine.this.mCarAudioManager != null) {
                        int unused = AvrcpControllerStateMachine.mVolumeGroupId = AvrcpControllerStateMachine.this.mCarAudioManager.getVolumeGroupIdForUsage(1);
                        int unused2 = AvrcpControllerStateMachine.mMaxVolume = AvrcpControllerStateMachine.this.mCarAudioManager.getGroupMaxVolume(AvrcpControllerStateMachine.mVolumeGroupId);
                        AvrcpControllerStateMachine.this.mCarAudioManager.registerCarVolumeCallback(AvrcpControllerStateMachine.this.mVolumeChangeCallback);
                    }
                } catch (CarNotConnectedException e) {
                    Log.e(AvrcpControllerStateMachine.TAG, "Car is not connected!", e);
                } catch (NullPointerException e2) {
                    Log.e(AvrcpControllerStateMachine.TAG, "mCarAudioManager is NULL!", e2);
                }
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName name) {
                Log.e(AvrcpControllerStateMachine.TAG, "Car service is disconnected");
            }
        };
        this.mSessionCallbacks = new MediaSessionCompat.Callback() { // from class: com.android.bluetooth.avrcpcontroller.AvrcpControllerStateMachine.3
            @Override // android.support.v4.media.session.MediaSessionCompat.Callback
            public void onPlay() {
                AvrcpControllerStateMachine.logD("onPlay");
                AvrcpControllerStateMachine.this.requestAudioFocus();
                if (AvrcpControllerStateMachine.this.hasAudioFocus()) {
                    AvrcpControllerStateMachine.logD("onPlay has AudioFocus send Play CMD");
                    AvrcpControllerStateMachine.this.sendMessage(AvrcpControllerStateMachine.MSG_AVRCP_PASSTHRU, 68);
                }
            }

            @Override // android.support.v4.media.session.MediaSessionCompat.Callback
            public void onPause() {
                AvrcpControllerStateMachine.logD("onPause");
                AvrcpControllerStateMachine.this.sendMessage(AvrcpControllerStateMachine.MSG_AVRCP_PASSTHRU, 70);
            }

            @Override // android.support.v4.media.session.MediaSessionCompat.Callback
            public void onSkipToNext() {
                AvrcpControllerStateMachine.logD("onSkipToNext");
                AvrcpControllerStateMachine.this.requestAudioFocus();
                if (AvrcpControllerStateMachine.this.hasAudioFocus()) {
                    AvrcpControllerStateMachine.logD("onSkipToNext has AudioFocus send FORWARD CMD");
                    AvrcpControllerStateMachine.this.sendMessage(AvrcpControllerStateMachine.MSG_AVRCP_PASSTHRU, 75);
                }
            }

            @Override // android.support.v4.media.session.MediaSessionCompat.Callback
            public void onSkipToPrevious() {
                AvrcpControllerStateMachine.logD("onSkipToPrevious");
                AvrcpControllerStateMachine.this.requestAudioFocus();
                if (AvrcpControllerStateMachine.this.hasAudioFocus()) {
                    AvrcpControllerStateMachine.logD("onSkipToPrevious has AudioFocus send BACKWARD CMD");
                    AvrcpControllerStateMachine.this.sendMessage(AvrcpControllerStateMachine.MSG_AVRCP_PASSTHRU, 76);
                }
            }

            @Override // android.support.v4.media.session.MediaSessionCompat.Callback
            public void onSkipToQueueItem(long id) {
                AvrcpControllerStateMachine.logD("onSkipToQueueItem id=" + id);
                AvrcpControllerStateMachine.this.requestAudioFocus();
                BrowseTree.BrowseNode node = AvrcpControllerStateMachine.this.mBrowseTree.getTrackFromNowPlayingList((int) id);
                if (node != null) {
                    AvrcpControllerStateMachine.this.sendMessage(AvrcpControllerStateMachine.MESSAGE_PLAY_ITEM, node);
                }
            }

            @Override // android.support.v4.media.session.MediaSessionCompat.Callback
            public void onStop() {
                AvrcpControllerStateMachine.logD("onStop");
                AvrcpControllerStateMachine.this.sendMessage(AvrcpControllerStateMachine.MSG_AVRCP_PASSTHRU, 69);
            }

            @Override // android.support.v4.media.session.MediaSessionCompat.Callback
            public void onPrepare() {
                AvrcpControllerStateMachine.logD("onPrepare");
            }

            @Override // android.support.v4.media.session.MediaSessionCompat.Callback
            public void onRewind() {
                AvrcpControllerStateMachine.logD("onRewind");
                AvrcpControllerStateMachine.this.sendMessage(AvrcpControllerStateMachine.MSG_AVRCP_PASSTHRU, 72);
            }

            @Override // android.support.v4.media.session.MediaSessionCompat.Callback
            public void onFastForward() {
                AvrcpControllerStateMachine.logD("onFastForward");
                AvrcpControllerStateMachine.this.sendMessage(AvrcpControllerStateMachine.MSG_AVRCP_PASSTHRU, 73);
            }

            @Override // android.support.v4.media.session.MediaSessionCompat.Callback
            public void onPlayFromMediaId(String mediaId, Bundle extras) {
                AvrcpControllerStateMachine.logD("onPlayFromMediaId");
                AvrcpControllerStateMachine.this.requestAudioFocus();
                BrowseTree.BrowseNode node = AvrcpControllerStateMachine.this.mBrowseTree.findBrowseNodeByID(mediaId);
                if (node != null) {
                    AvrcpControllerStateMachine.this.sendMessage(AvrcpControllerStateMachine.MESSAGE_PLAY_ITEM, node);
                } else {
                    AvrcpControllerStateMachine.this.sendMessage(AvrcpControllerStateMachine.MSG_AVRCP_PASSTHRU, 70);
                    AvrcpControllerStateMachine.this.mService.playItem(mediaId);
                }
            }

            @Override // android.support.v4.media.session.MediaSessionCompat.Callback
            public void onCustomAction(String action, Bundle extras) {
                AvrcpControllerStateMachine.logD("onCustomAction:" + action);
                if (AvrcpControllerStateMachine.CUSTOM_ACTION_SEARCH.equals(action)) {
                    AvrcpControllerStateMachine.this.handleCustomActionSearch(extras);
                    return;
                }
                if (AvrcpControllerStateMachine.CUSTOM_ACTION_SEND_PASS_THRU_CMD.equals(action)) {
                    AvrcpControllerStateMachine.this.handleCustomActionSendPassThruCmd(extras);
                    return;
                }
                if (AvrcpControllerStateMachine.CUSTOM_ACTION_GET_ITEM_ATTR.equals(action)) {
                    AvrcpControllerStateMachine.this.handleCustomActionGetItemAttributes(extras);
                    return;
                }
                if (AvrcpControllerStateMachine.CUSTOM_ACTION_GET_ELEMENT_ATTR.equals(action)) {
                    AvrcpControllerStateMachine.this.handleCustomActionGetElementAttributes(extras);
                    return;
                }
                if (AvrcpControllerStateMachine.CUSTOM_ACTION_GET_FOLDER_ITEM.equals(action)) {
                    AvrcpControllerStateMachine.this.handleCustomActionGetFolderItems(extras);
                    return;
                }
                if (AvrcpControllerStateMachine.CUSTOM_ACTION_REQUEST_CONTINUING_RESPONSE.equals(action)) {
                    AvrcpControllerStateMachine.this.handleCustomActionRequestContinuingResponse(extras);
                    return;
                }
                if (AvrcpControllerStateMachine.CUSTOM_ACTION_ABORT_CONTINUING_RESPONSE.equals(action)) {
                    AvrcpControllerStateMachine.this.handleCustomActionAbortContinuingResponse(extras);
                    return;
                }
                if (AvrcpControllerStateMachine.CUSTOM_ACTION_ADD_TO_NOW_PLAYING.equals(action)) {
                    AvrcpControllerStateMachine.this.handleCustomActionAddToNowPlaying(extras);
                    return;
                }
                if (AvrcpControllerStateMachine.CUSTOM_ACTION_SET_ADDRESSED_PLAYER.equals(action)) {
                    AvrcpControllerStateMachine.this.handleCustomActionSetAddressedPlayer(extras);
                    return;
                }
                if (AvrcpControllerStateMachine.CUSTOM_ACTION_PLAY_ITEM.equals(action)) {
                    AvrcpControllerStateMachine.this.handleCustomActionPlayItem(extras);
                    return;
                }
                Log.w(AvrcpControllerStateMachine.TAG, "Custom action " + action + " not supported.");
            }

            @Override // android.support.v4.media.session.MediaSessionCompat.Callback
            public void onSetRepeatMode(int repeatMode) {
                AvrcpControllerStateMachine.logD("onSetRepeatMode");
                AvrcpControllerStateMachine.this.sendMessage(AvrcpControllerStateMachine.MSG_AVRCP_SET_REPEAT, repeatMode);
            }

            @Override // android.support.v4.media.session.MediaSessionCompat.Callback
            public void onSetShuffleMode(int shuffleMode) {
                AvrcpControllerStateMachine.logD("onSetShuffleMode");
                AvrcpControllerStateMachine.this.sendMessage(AvrcpControllerStateMachine.MSG_AVRCP_SET_SHUFFLE, shuffleMode);
            }
        };
        this.mDevice = device;
        this.mDeviceAddress = Utils.getByteAddress(device);
        this.mService = service;
        this.mRemoteFeatures = 0;
        this.mCoverArtPsm = 0;
        this.mCoverArtManager = service.getCoverArtManager();
        logD(device.toString());
        this.mBrowseTree = new BrowseTree(this.mDevice);
        this.mDisconnected = new Disconnected();
        this.mConnecting = new Connecting();
        this.mConnected = new Connected();
        this.mDisconnecting = new Disconnecting();
        addState(this.mDisconnected);
        addState(this.mConnecting);
        addState(this.mConnected);
        addState(this.mDisconnecting);
        GetFolderList getFolderList = new GetFolderList();
        this.mGetFolderList = getFolderList;
        addState(getFolderList, this.mConnected);
        Search search = new Search();
        this.mSearch = search;
        addState(search, this.mConnected);
        AddToNowPlaying addToNowPlaying = new AddToNowPlaying();
        this.mAddToNowPlaying = addToNowPlaying;
        addState(addToNowPlaying, this.mConnected);
        Car carCreateCar = Car.createCar(service.getApplicationContext(), this.mConnection);
        this.mCar = carCreateCar;
        carCreateCar.connect();
        setInitialState(this.mDisconnected);
    }

    public void doQuit() {
        logD("doQuit");
        CarAudioManager carAudioManager = this.mCarAudioManager;
        if (carAudioManager != null) {
            carAudioManager.unregisterCarVolumeCallback(this.mVolumeChangeCallback);
        }
        Car car = this.mCar;
        if (car != null && car.isConnected()) {
            this.mCar.disconnect();
            this.mCar = null;
        }
        quitNow();
    }

    BrowseTree.BrowseNode findNode(String parentMediaId) {
        logD("FindNode");
        return this.mBrowseTree.findBrowseNodeByID(parentMediaId);
    }

    public int getState() {
        return this.mMostRecentState;
    }

    public synchronized BluetoothDevice getDevice() {
        return this.mDevice;
    }

    public synchronized void setRemoteFeatures(int remoteFeatures) {
        this.mRemoteFeatures = remoteFeatures;
    }

    public synchronized int getRemoteFeatures() {
        return this.mRemoteFeatures;
    }

    public boolean connect(StackEvent event) {
        if (event.mBrowsingConnected) {
            onBrowsingConnected();
        }
        this.mRemoteControlConnected = event.mRemoteControlConnected;
        sendMessage(1);
        return true;
    }

    public void disconnect() {
        sendMessage(2);
    }

    public AvrcpItem getCurrentTrack() {
        return this.mAddressedPlayer.getCurrentTrack();
    }

    public void dump(StringBuilder sb) {
        ProfileService.println(sb, "mDevice: " + this.mDevice.getAddressOnlyforLog() + "(" + this.mDevice.getName() + ") " + toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append("isActive: ");
        sb2.append(isActive());
        ProfileService.println(sb, sb2.toString());
    }

    boolean isActive() {
        return this.mDevice == sActiveDevice;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean requestActive() {
        if (sActiveDevice == null || BluetoothMediaBrowserService.getPlaybackState() != 3) {
            return setActive(true);
        }
        return false;
    }

    boolean setActive(boolean becomeActive) {
        logD("setActive(" + becomeActive + ")");
        if (becomeActive) {
            if (isActive()) {
                return true;
            }
            if (AvrcpControllerService.sBrowseTree != null) {
                AvrcpControllerService.sBrowseTree.updateSearchNode(this.mBrowseTree.mSearchNode);
            }
            A2dpSinkService a2dpSinkService = A2dpSinkService.getA2dpSinkService();
            if (a2dpSinkService == null) {
                return false;
            }
            if (a2dpSinkService.setActiveDeviceNative(this.mDeviceAddress)) {
                sActiveDevice = this.mDevice;
                BluetoothMediaBrowserService.addressedPlayerChanged(this.mSessionCallbacks);
                BluetoothMediaBrowserService.notifyChanged(this.mAddressedPlayer.getPlaybackState());
                BluetoothMediaBrowserService.notifyChanged(this.mBrowseTree.mNowPlayingNode);
            }
            return this.mDevice == sActiveDevice;
        }
        if (isActive()) {
            refreshSearchNode(false);
            sActiveDevice = null;
            BluetoothMediaBrowserService.trackChanged(null);
            BluetoothMediaBrowserService.addressedPlayerChanged(null);
        }
        return true;
    }

    @Override // com.android.bluetooth.statemachine.StateMachine
    protected void unhandledMessage(Message msg) {
        Log.w(TAG, "Unhandled message in state " + getCurrentState() + "msg.what=" + msg.what);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void logD(String message) {
        if (DBG) {
            Log.d(TAG, message);
        }
    }

    synchronized void onBrowsingConnected() {
        if (this.mBrowsingConnected) {
            return;
        }
        if (AvrcpControllerService.sBrowseTree != null && AvrcpControllerService.sBrowseTree.mRootNode != null) {
            AvrcpControllerService.sBrowseTree.mRootNode.addChild(this.mBrowseTree.mRootNode);
            BluetoothMediaBrowserService.notifyChanged(AvrcpControllerService.sBrowseTree.mRootNode);
            this.mBrowsingConnected = true;
        }
    }

    synchronized void onBrowsingDisconnected() {
        if (this.mBrowsingConnected) {
            this.mAddressedPlayer.setPlayStatus(7);
            AvrcpItem previousTrack = this.mAddressedPlayer.getCurrentTrack();
            String previousTrackUuid = previousTrack != null ? previousTrack.getCoverArtUuid() : null;
            this.mAddressedPlayer.updateCurrentTrack(null);
            this.mBrowseTree.mNowPlayingNode.setCached(false);
            if (isActive()) {
                BluetoothMediaBrowserService.notifyChanged(this.mBrowseTree.mNowPlayingNode);
            }
            if (AvrcpControllerService.sBrowseTree != null && AvrcpControllerService.sBrowseTree.mRootNode != null) {
                AvrcpControllerService.sBrowseTree.mRootNode.removeChild(this.mBrowseTree.mRootNode);
                BluetoothMediaBrowserService.notifyChanged(AvrcpControllerService.sBrowseTree.mRootNode);
                removeUnusedArtwork(previousTrackUuid);
                removeUnusedArtworkFromBrowseTree();
                this.mBrowsingConnected = false;
            }
        }
    }

    synchronized void connectCoverArt() {
        if (this.mCoverArtManager != null && this.mCoverArtPsm != 0 && this.mCoverArtManager.getState(this.mDevice) != 2) {
            logD("Attempting to connect to AVRCP BIP, psm: " + this.mCoverArtPsm);
            this.mCoverArtManager.connect(this.mDevice, this.mCoverArtPsm);
        }
    }

    synchronized void refreshCoverArt() {
        if (this.mCoverArtManager != null && this.mCoverArtPsm != 0 && this.mCoverArtManager.getState(this.mDevice) == 2) {
            logD("Attempting to refresh AVRCP BIP OBEX session, psm: " + this.mCoverArtPsm);
            this.mCoverArtManager.refreshSession(this.mDevice);
        }
    }

    synchronized void disconnectCoverArt() {
        if (this.mCoverArtManager != null) {
            logD("Disconnect BIP cover artwork");
            this.mCoverArtManager.disconnect(this.mDevice);
        }
    }

    synchronized void removeUnusedArtwork(String previousTrackUuid) {
        logD("removeUnusedArtwork(" + previousTrackUuid + ")");
        if (this.mCoverArtManager == null) {
            return;
        }
        AvrcpItem currentTrack = getCurrentTrack();
        String currentTrackUuid = currentTrack != null ? currentTrack.getCoverArtUuid() : null;
        if (previousTrackUuid != null && !previousTrackUuid.equals(currentTrackUuid) && this.mBrowseTree.getNodesUsingCoverArt(previousTrackUuid).isEmpty()) {
            this.mCoverArtManager.removeImage(this.mDevice, previousTrackUuid);
        }
    }

    synchronized void removeUnusedArtworkFromBrowseTree() {
        logD("removeUnusedArtworkFromBrowseTree()");
        if (this.mCoverArtManager == null) {
            return;
        }
        AvrcpItem currentTrack = getCurrentTrack();
        String currentTrackUuid = currentTrack != null ? currentTrack.getCoverArtUuid() : null;
        ArrayList<String> unusedArtwork = this.mBrowseTree.getAndClearUnusedCoverArt();
        for (String uuid : unusedArtwork) {
            if (!uuid.equals(currentTrackUuid)) {
                this.mCoverArtManager.removeImage(this.mDevice, uuid);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyChanged(BrowseTree.BrowseNode node) {
        int scope = node.getScope();
        if (scope != 3 || (scope == 3 && isActive())) {
            BluetoothMediaBrowserService.notifyChanged(node);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyChanged(PlaybackStateCompat state) {
        if (isActive()) {
            BluetoothMediaBrowserService.notifyChanged(state);
        }
    }

    void requestContents(BrowseTree.BrowseNode node) {
        sendMessage(MESSAGE_GET_FOLDER_ITEMS, node);
        logD("Fetching " + node);
    }

    public void playItem(BrowseTree.BrowseNode node) {
        sendMessage(MESSAGE_PLAY_ITEM, node);
    }

    void nowPlayingContentChanged() {
        this.mBrowseTree.mNowPlayingNode.setCached(false);
        removeUnusedArtworkFromBrowseTree();
        sendMessage(MESSAGE_GET_FOLDER_ITEMS, this.mBrowseTree.mNowPlayingNode);
    }

    void refreshSearchNode(boolean isAddNode) {
        this.mBrowseTree.mSearchNode.setCached(false);
        BrowseTree.BrowseNode currBrPlayer = this.mBrowseTree.getCurrentBrowsedPlayer();
        if (currBrPlayer != null) {
            if (isAddNode) {
                currBrPlayer.addChild(this.mBrowseTree.mSearchNode);
            } else {
                currBrPlayer.removeChild(this.mBrowseTree.mSearchNode);
            }
            BluetoothMediaBrowserService.notifyChanged(currBrPlayer);
            return;
        }
        Log.d(TAG, "currBrPlayer is NULL");
    }

    protected class Disconnected extends State {
        protected Disconnected() {
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            AvrcpControllerStateMachine.logD("Enter Disconnected");
            if (AvrcpControllerStateMachine.this.isActive()) {
                AvrcpControllerStateMachine.this.refreshSearchNode(false);
            }
            if (AvrcpControllerStateMachine.this.mMostRecentState != 0) {
                AvrcpControllerStateMachine.this.sendMessage(100);
            }
            AvrcpControllerStateMachine.this.broadcastConnectionStateChanged(0);
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public boolean processMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                AvrcpControllerStateMachine.logD("Connect");
                AvrcpControllerStateMachine avrcpControllerStateMachine = AvrcpControllerStateMachine.this;
                avrcpControllerStateMachine.transitionTo(avrcpControllerStateMachine.mConnecting);
            } else if (i == 100) {
                AvrcpControllerStateMachine.this.mService.removeStateMachine(AvrcpControllerStateMachine.this);
            } else if (i == AvrcpControllerStateMachine.MESSAGE_PROCESS_RECEIVED_COVER_ART_PSM) {
                AvrcpControllerStateMachine.this.mCoverArtPsm = message.arg1;
            }
            return true;
        }
    }

    protected class Connecting extends State {
        protected Connecting() {
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            AvrcpControllerStateMachine.logD("Enter Connecting");
            AvrcpControllerStateMachine.this.broadcastConnectionStateChanged(1);
            AvrcpControllerStateMachine avrcpControllerStateMachine = AvrcpControllerStateMachine.this;
            avrcpControllerStateMachine.transitionTo(avrcpControllerStateMachine.mConnected);
        }
    }

    class Connected extends State {
        private static final String STATE_TAG = "Avrcp.ConnectedAvrcpController";
        private int mCurrentlyHeldKey = 0;

        Connected() {
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            if (AvrcpControllerStateMachine.this.mMostRecentState == 1) {
                AvrcpControllerStateMachine.this.requestActive();
                AvrcpControllerStateMachine.this.broadcastConnectionStateChanged(2);
                AvrcpControllerStateMachine.this.connectCoverArt();
            } else {
                AvrcpControllerStateMachine.logD("ReEnteringConnected");
            }
            super.enter();
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public boolean processMessage(Message msg) {
            AvrcpControllerStateMachine.logD("Avrcp.ConnectedAvrcpController processMessage " + msg.what);
            int i = msg.what;
            if (i == 2) {
                AvrcpControllerStateMachine avrcpControllerStateMachine = AvrcpControllerStateMachine.this;
                avrcpControllerStateMachine.transitionTo(avrcpControllerStateMachine.mDisconnecting);
                return true;
            }
            if (i == AvrcpControllerStateMachine.MESSAGE_PROCESS_ADDRESSED_PLAYER_CHANGED) {
                AvrcpControllerStateMachine.this.mAddressedPlayerId = msg.arg1;
                AvrcpControllerStateMachine.logD("AddressedPlayer = " + AvrcpControllerStateMachine.this.mAddressedPlayerId);
                AvrcpControllerStateMachine.this.mBrowseTree.mNowPlayingNode.setCached(false);
                if (AvrcpControllerStateMachine.this.isActive()) {
                    BluetoothMediaBrowserService.notifyChanged(AvrcpControllerStateMachine.this.mBrowseTree.mNowPlayingNode);
                }
                AvrcpPlayer updatedPlayer = (AvrcpPlayer) AvrcpControllerStateMachine.this.mAvailablePlayerList.get(AvrcpControllerStateMachine.this.mAddressedPlayerId);
                if (updatedPlayer != null) {
                    AvrcpControllerStateMachine.this.mAddressedPlayer = updatedPlayer;
                    if (AvrcpControllerStateMachine.this.mAddressedPlayer.supportsFeature(65)) {
                        AvrcpControllerStateMachine avrcpControllerStateMachine2 = AvrcpControllerStateMachine.this;
                        avrcpControllerStateMachine2.sendMessage(AvrcpControllerStateMachine.MESSAGE_GET_FOLDER_ITEMS, avrcpControllerStateMachine2.mBrowseTree.mNowPlayingNode);
                    }
                    AvrcpControllerStateMachine.logD("AddressedPlayer = " + AvrcpControllerStateMachine.this.mAddressedPlayer.getName());
                } else {
                    AvrcpControllerStateMachine.logD("Addressed player changed to unknown ID=" + AvrcpControllerStateMachine.this.mAddressedPlayerId);
                    AvrcpControllerStateMachine.this.mBrowseTree.mRootNode.setCached(false);
                    AvrcpControllerStateMachine.this.mBrowseTree.mRootNode.setExpectedChildren(255);
                    BluetoothMediaBrowserService.notifyChanged(AvrcpControllerStateMachine.this.mBrowseTree.mRootNode);
                }
                AvrcpControllerStateMachine.this.removeUnusedArtworkFromBrowseTree();
                return true;
            }
            if (i == 400) {
                AvrcpCoverArtManager.DownloadEvent event = (AvrcpCoverArtManager.DownloadEvent) msg.obj;
                String uuid = event.getUuid();
                Uri uri = event.getUri();
                AvrcpControllerStateMachine.logD("Received image for " + uuid + " at " + uri.toString());
                boolean addedArtwork = AvrcpControllerStateMachine.this.mAddressedPlayer.notifyImageDownload(uuid, uri);
                if (addedArtwork && AvrcpControllerStateMachine.this.isActive()) {
                    BluetoothMediaBrowserService.trackChanged(AvrcpControllerStateMachine.this.mAddressedPlayer.getCurrentTrack());
                }
                Set<BrowseTree.BrowseNode> nodes = AvrcpControllerStateMachine.this.mBrowseTree.notifyImageDownload(uuid, uri);
                for (BrowseTree.BrowseNode node : nodes) {
                    AvrcpControllerStateMachine.this.notifyChanged(node);
                }
                if (!addedArtwork && nodes.isEmpty()) {
                    AvrcpControllerStateMachine.this.removeUnusedArtwork(uuid);
                    AvrcpControllerStateMachine.this.removeUnusedArtworkFromBrowseTree();
                }
                return true;
            }
            if (i == AvrcpControllerStateMachine.MSG_AVRCP_FETCH_COVER_ART) {
                AvrcpControllerStateMachine.this.mCoverArtManager.updateImageProperties();
                AvrcpControllerStateMachine.this.downloadImageIfNeeded(AvrcpControllerStateMachine.this.mAddressedPlayer.getCurrentTrack(), true);
                return true;
            }
            if (i == AvrcpControllerStateMachine.MESSAGE_PROCESS_UIDS_CHANGED) {
                AvrcpControllerStateMachine.this.processUIDSChange(msg);
                return true;
            }
            if (i == AvrcpControllerStateMachine.MESSAGE_PROCESS_RC_FEATURES) {
                AvrcpControllerStateMachine.this.setRemoteFeatures(msg.arg1);
                return true;
            }
            if (i == AvrcpControllerStateMachine.MESSAGE_GET_FOLDER_ITEMS) {
                AvrcpControllerStateMachine avrcpControllerStateMachine3 = AvrcpControllerStateMachine.this;
                avrcpControllerStateMachine3.transitionTo(avrcpControllerStateMachine3.mGetFolderList);
                return true;
            }
            if (i != AvrcpControllerStateMachine.MESSAGE_PLAY_ITEM) {
                switch (i) {
                    case AvrcpControllerStateMachine.MESSAGE_PROCESS_SET_ABS_VOL_CMD /* 203 */:
                        AvrcpControllerStateMachine.access$508(AvrcpControllerStateMachine.this);
                        AvrcpControllerStateMachine.this.removeMessages(AvrcpControllerStateMachine.MESSAGE_INTERNAL_ABS_VOL_TIMEOUT);
                        AvrcpControllerStateMachine.this.sendMessageDelayed(AvrcpControllerStateMachine.MESSAGE_INTERNAL_ABS_VOL_TIMEOUT, 1000L);
                        AvrcpControllerStateMachine.this.handleAbsVolumeRequest(msg.arg1, msg.arg2);
                        return true;
                    case AvrcpControllerStateMachine.MESSAGE_PROCESS_REGISTER_ABS_VOL_NOTIFICATION /* 204 */:
                        AvrcpControllerStateMachine.this.mVolumeNotificationLabel = msg.arg1;
                        AvrcpControllerStateMachine.this.mService.sendRegisterAbsVolRspNative(AvrcpControllerStateMachine.this.mDeviceAddress, (byte) 0, AvrcpControllerStateMachine.this.getAbsVolume(), AvrcpControllerStateMachine.this.mVolumeNotificationLabel);
                        return true;
                    case AvrcpControllerStateMachine.MESSAGE_PROCESS_TRACK_CHANGED /* 205 */:
                        AvrcpItem track = (AvrcpItem) msg.obj;
                        AvrcpItem previousTrack = AvrcpControllerStateMachine.this.mAddressedPlayer.getCurrentTrack();
                        AvrcpControllerStateMachine.this.downloadImageIfNeeded(track);
                        AvrcpControllerStateMachine.this.mAddressedPlayer.updateCurrentTrack(track);
                        if (AvrcpControllerStateMachine.this.isActive()) {
                            BluetoothMediaBrowserService.trackChanged(track);
                        }
                        if (previousTrack != null) {
                            AvrcpControllerStateMachine.this.removeUnusedArtwork(previousTrack.getCoverArtUuid());
                            AvrcpControllerStateMachine.this.removeUnusedArtworkFromBrowseTree();
                        }
                        return true;
                    case AvrcpControllerStateMachine.MESSAGE_PROCESS_PLAY_POS_CHANGED /* 206 */:
                        if (msg.arg2 != -1) {
                            AvrcpControllerStateMachine.this.mAddressedPlayer.setPlayTime(msg.arg2);
                            AvrcpControllerStateMachine avrcpControllerStateMachine4 = AvrcpControllerStateMachine.this;
                            avrcpControllerStateMachine4.notifyChanged(avrcpControllerStateMachine4.mAddressedPlayer.getPlaybackState());
                        }
                        return true;
                    case AvrcpControllerStateMachine.MESSAGE_PROCESS_PLAY_STATUS_CHANGED /* 207 */:
                        AvrcpControllerStateMachine.this.mAddressedPlayer.setPlayStatus(msg.arg1);
                        if (!AvrcpControllerStateMachine.this.isActive()) {
                            AvrcpControllerStateMachine.logD("to pause; not active device");
                            AvrcpControllerStateMachine.this.sendMessage(AvrcpControllerStateMachine.MSG_AVRCP_PASSTHRU, 70);
                            return true;
                        }
                        PlaybackStateCompat playbackState = AvrcpControllerStateMachine.this.mAddressedPlayer.getPlaybackState();
                        BluetoothMediaBrowserService.notifyChanged(playbackState);
                        int focusState = -1;
                        A2dpSinkService a2dpSinkService = A2dpSinkService.getA2dpSinkService();
                        if (a2dpSinkService != null) {
                            focusState = a2dpSinkService.getFocusState();
                        }
                        if (focusState == -1) {
                            AvrcpControllerStateMachine.logD("to pause; focusState == AudioManager.ERROR");
                            AvrcpControllerStateMachine.this.sendMessage(AvrcpControllerStateMachine.MSG_AVRCP_PASSTHRU, 70);
                            return true;
                        }
                        if (playbackState.getState() == 3) {
                            String sact = SystemProperties.get(AvrcpControllerStateMachine.BT_MEDIA_ACTIVE, "0");
                            AvrcpControllerStateMachine.logD("BT_MEDIA_ACTIVE: " + sact);
                            if (focusState == 0) {
                                if (AvrcpControllerStateMachine.this.shouldRequestFocus() || sact.equals("1")) {
                                    AvrcpControllerStateMachine.this.requestAudioFocus();
                                    if (!AvrcpControllerStateMachine.this.hasAudioFocus()) {
                                        AvrcpControllerStateMachine.logD("no audioFocus, send Pause");
                                        AvrcpControllerStateMachine.this.sendMessage(AvrcpControllerStateMachine.MSG_AVRCP_PASSTHRU, 70);
                                    }
                                } else if (!AdapterService.getAdapterService().isAndroidAutoConnect()) {
                                    AvrcpControllerStateMachine.logD("NO AA connected. to pause");
                                    AvrcpControllerStateMachine.this.sendMessage(AvrcpControllerStateMachine.MSG_AVRCP_PASSTHRU, 70);
                                }
                            } else if (!sact.equals("1")) {
                                AvrcpControllerStateMachine.logD("The app does not allow playback,send Pause");
                                AvrcpControllerStateMachine.this.sendMessage(AvrcpControllerStateMachine.MSG_AVRCP_PASSTHRU, 70);
                            }
                        }
                        return true;
                    default:
                        switch (i) {
                            case AvrcpControllerStateMachine.MESSAGE_PROCESS_SUPPORTED_APPLICATION_SETTINGS /* 217 */:
                                AvrcpControllerStateMachine.this.mAddressedPlayer.setSupportedPlayerApplicationSettings((PlayerApplicationSettings) msg.obj);
                                AvrcpControllerStateMachine avrcpControllerStateMachine5 = AvrcpControllerStateMachine.this;
                                avrcpControllerStateMachine5.notifyChanged(avrcpControllerStateMachine5.mAddressedPlayer.getPlaybackState());
                                return true;
                            case AvrcpControllerStateMachine.MESSAGE_PROCESS_CURRENT_APPLICATION_SETTINGS /* 218 */:
                                AvrcpControllerStateMachine.this.mAddressedPlayer.setCurrentPlayerApplicationSettings((PlayerApplicationSettings) msg.obj);
                                AvrcpControllerStateMachine avrcpControllerStateMachine6 = AvrcpControllerStateMachine.this;
                                avrcpControllerStateMachine6.notifyChanged(avrcpControllerStateMachine6.mAddressedPlayer.getPlaybackState());
                                return true;
                            case AvrcpControllerStateMachine.MESSAGE_PROCESS_AVAILABLE_PLAYER_CHANGED /* 219 */:
                                processAvailablePlayerChanged();
                                return true;
                            case AvrcpControllerStateMachine.MESSAGE_PROCESS_RECEIVED_COVER_ART_PSM /* 220 */:
                                AvrcpControllerStateMachine.this.mCoverArtPsm = msg.arg1;
                                AvrcpControllerStateMachine.this.connectCoverArt();
                                return true;
                            default:
                                switch (i) {
                                    case AvrcpControllerStateMachine.MSG_AVRCP_PASSTHRU /* 350 */:
                                        passThru(msg.arg1);
                                        return true;
                                    case AvrcpControllerStateMachine.MSG_AVRCP_SET_SHUFFLE /* 351 */:
                                        setShuffle(msg.arg1);
                                        return true;
                                    case AvrcpControllerStateMachine.MSG_AVRCP_SET_REPEAT /* 352 */:
                                        setRepeat(msg.arg1);
                                        return true;
                                    case AvrcpControllerStateMachine.MSG_AVRCP_SEARCH /* 353 */:
                                        AvrcpControllerStateMachine.this.refreshSearchNode(false);
                                        processSearchReq((String) msg.obj);
                                        return true;
                                    case AvrcpControllerStateMachine.MSG_AVRCP_PASSTHRU_EXT /* 354 */:
                                        passThru(msg.arg1, msg.arg2);
                                        return true;
                                    case AvrcpControllerStateMachine.MSG_AVRCP_GET_ITEM_ATTR /* 355 */:
                                        getItemAttributes((Bundle) msg.obj);
                                        return true;
                                    case AvrcpControllerStateMachine.MSG_AVRCP_GET_ELEMENT_ATTR /* 356 */:
                                        getElementAttributes((Bundle) msg.obj);
                                        return true;
                                    case AvrcpControllerStateMachine.MSG_AVRCP_GET_FOLDER_ITEMS_PTS /* 357 */:
                                        getFolderItems((Bundle) msg.obj);
                                        AvrcpControllerStateMachine avrcpControllerStateMachine7 = AvrcpControllerStateMachine.this;
                                        avrcpControllerStateMachine7.transitionTo(avrcpControllerStateMachine7.mGetFolderList);
                                        return true;
                                    case AvrcpControllerStateMachine.MSG_AVRCP_REQUEST_CONTINUING_RESPONSE /* 358 */:
                                        RequestContinuingResponse(msg.arg1);
                                        return true;
                                    case AvrcpControllerStateMachine.MSG_AVRCP_ABORT_CONTINUING_RESPONSE /* 359 */:
                                        AbortContinuingResponse(msg.arg1);
                                        return true;
                                    case AvrcpControllerStateMachine.MSG_AVRCP_ADD_TO_NOW_PLAYING /* 360 */:
                                        AvrcpControllerStateMachine avrcpControllerStateMachine8 = AvrcpControllerStateMachine.this;
                                        avrcpControllerStateMachine8.transitionTo(avrcpControllerStateMachine8.mAddToNowPlaying);
                                        return true;
                                    case AvrcpControllerStateMachine.MSG_AVRCP_SET_ADDRESSED_PLAYER_PTS /* 361 */:
                                        int playerId = ((Bundle) msg.obj).getInt(AvrcpControllerStateMachine.KEY_PLAYER_ID, 0);
                                        setAddressedPlayer(playerId);
                                        return true;
                                    case AvrcpControllerStateMachine.MSG_AVRCP_PLAY_ITEM_PTS /* 362 */:
                                        String mediaId = ((Bundle) msg.obj).getString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID);
                                        BrowseTree.BrowseNode node2 = AvrcpControllerStateMachine.this.mBrowseTree.findBrowseNodeByID(mediaId);
                                        int scope = ((Bundle) msg.obj).getInt(AvrcpControllerStateMachine.KEY_BROWSE_SCOPE, 0);
                                        playItemPTS(scope, node2);
                                        return true;
                                    default:
                                        return super.processMessage(msg);
                                }
                        }
                }
            }
            processPlayItem((BrowseTree.BrowseNode) msg.obj);
            return true;
        }

        private void processPlayItem(BrowseTree.BrowseNode node) {
            AvrcpControllerStateMachine.this.setActive(true);
            if (node == null) {
                Log.w(AvrcpControllerStateMachine.TAG, "Invalid item to play");
            } else {
                AvrcpControllerStateMachine.this.mService.playItemNative(AvrcpControllerStateMachine.this.mDeviceAddress, node.getScope(), node.getBluetoothID(), AvrcpControllerStateMachine.this.mUidCounter);
            }
        }

        private void setAddressedPlayer(int playerId) {
            AvrcpControllerStateMachine.this.mService.setAddressedPlayerNative(AvrcpControllerStateMachine.this.mDeviceAddress, playerId);
        }

        private void playItemPTS(int scope, BrowseTree.BrowseNode node) {
            if (scope == 3) {
                BrowseTree.BrowseNode node2 = AvrcpControllerStateMachine.this.mBrowseTree.mNowPlayingNode;
                if (node2.getChildrenCount() == 0) {
                    AvrcpControllerStateMachine.this.nowPlayingContentChanged();
                    return;
                }
                node = node2.getChild(0);
            }
            AvrcpControllerStateMachine.this.mService.playItemNative(AvrcpControllerStateMachine.this.mDeviceAddress, node.getScope(), node.getBluetoothID(), AvrcpControllerStateMachine.this.mUidCounter);
        }

        private synchronized void passThru(int cmd) {
            AvrcpControllerStateMachine.logD("msgPassThru " + cmd);
            if (this.mCurrentlyHeldKey != 0) {
                AvrcpControllerStateMachine.this.mService.sendPassThroughCommandNative(AvrcpControllerStateMachine.this.mDeviceAddress, this.mCurrentlyHeldKey, 1);
                if (this.mCurrentlyHeldKey == cmd) {
                    this.mCurrentlyHeldKey = 0;
                    return;
                }
                this.mCurrentlyHeldKey = 0;
            }
            AvrcpControllerStateMachine.this.mService.sendPassThroughCommandNative(AvrcpControllerStateMachine.this.mDeviceAddress, cmd, 0);
            if (isHoldableKey(cmd)) {
                this.mCurrentlyHeldKey = cmd;
            } else {
                AvrcpControllerStateMachine.this.mService.sendPassThroughCommandNative(AvrcpControllerStateMachine.this.mDeviceAddress, cmd, 1);
            }
        }

        private boolean isHoldableKey(int cmd) {
            return cmd == 72 || cmd == 73;
        }

        private synchronized void passThru(int cmd, int state) {
            AvrcpControllerStateMachine.logD("msgPassThru " + cmd + ", key state " + state);
            AvrcpControllerStateMachine.this.mService.sendPassThroughCommandNative(AvrcpControllerStateMachine.this.mDeviceAddress, cmd, state);
        }

        private void setRepeat(int repeatMode) {
            AvrcpControllerStateMachine.this.mService.setPlayerApplicationSettingValuesNative(AvrcpControllerStateMachine.this.mDeviceAddress, (byte) 1, new byte[]{2}, new byte[]{PlayerApplicationSettings.mapAvrcpPlayerSettingstoBTattribVal(2, repeatMode)});
        }

        private void setShuffle(int shuffleMode) {
            AvrcpControllerStateMachine.this.mService.setPlayerApplicationSettingValuesNative(AvrcpControllerStateMachine.this.mDeviceAddress, (byte) 1, new byte[]{3}, new byte[]{PlayerApplicationSettings.mapAvrcpPlayerSettingstoBTattribVal(3, shuffleMode)});
        }

        private synchronized void getItemAttributes(Bundle extras) {
            int scope = extras.getInt(AvrcpControllerStateMachine.KEY_BROWSE_SCOPE, 0);
            String mediaId = extras.getString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID);
            int[] attributeId = extras.getIntArray(AvrcpControllerStateMachine.KEY_ATTRIBUTE_ID);
            if (mediaId == null) {
                AvrcpControllerStateMachine.logD("processGetItemAttrReq GetElementAttributes");
            } else {
                BrowseTree.BrowseNode currItem = AvrcpControllerStateMachine.this.mBrowseTree.findBrowseNodeByID(mediaId);
                AvrcpControllerStateMachine.logD("processGetItemAttrReq mediaId=" + mediaId + " node=" + currItem);
                if (currItem != null) {
                    int features = AvrcpControllerStateMachine.this.getRemoteFeatures();
                    if ((features & 4) == 0) {
                        AvrcpControllerStateMachine.logD("Browsing channel not supported!!!");
                    } else {
                        AvrcpControllerService.getItemAttributesNative(AvrcpControllerStateMachine.this.mDeviceAddress, (byte) scope, currItem.getBluetoothID(), AvrcpControllerStateMachine.this.mUidCounter, (byte) attributeId.length, attributeId);
                    }
                }
            }
        }

        private synchronized void getElementAttributes(Bundle extras) {
            int[] attributeId = extras.getIntArray(AvrcpControllerStateMachine.KEY_ATTRIBUTE_ID);
            AvrcpControllerService.getElementAttributesNative(AvrcpControllerStateMachine.this.mDeviceAddress, (byte) attributeId.length, attributeId);
        }

        private synchronized void getFolderItems(Bundle extras) {
            int scope = extras.getInt(AvrcpControllerStateMachine.KEY_BROWSE_SCOPE, 0);
            int start = extras.getInt(AvrcpControllerStateMachine.KEY_START, 0);
            int end = extras.getInt(AvrcpControllerStateMachine.KEY_END, 255);
            int[] attributeId = extras.getIntArray(AvrcpControllerStateMachine.KEY_ATTRIBUTE_ID);
            AvrcpControllerService.getFolderItemsNative(AvrcpControllerStateMachine.this.mDeviceAddress, (byte) scope, (byte) start, (byte) end, (byte) attributeId.length, attributeId);
        }

        private void RequestContinuingResponse(int pduId) {
            AvrcpControllerStateMachine.logD("processRequestContinuingResponse pduId=" + pduId);
            AvrcpControllerService.requestContinuingResponseNative(AvrcpControllerStateMachine.this.mDeviceAddress, (byte) pduId);
        }

        private void AbortContinuingResponse(int pduId) {
            AvrcpControllerStateMachine.logD("processAbortContinuingResponse pduId=" + pduId);
            AvrcpControllerService.abortContinuingResponseNative(AvrcpControllerStateMachine.this.mDeviceAddress, (byte) pduId);
        }

        private void processAvailablePlayerChanged() {
            AvrcpControllerStateMachine.logD("processAvailablePlayerChanged");
            AvrcpControllerStateMachine.this.mBrowseTree.mRootNode.setCached(false);
            AvrcpControllerStateMachine.this.mBrowseTree.mRootNode.setExpectedChildren(255);
            BluetoothMediaBrowserService.notifyChanged(AvrcpControllerStateMachine.this.mBrowseTree.mRootNode);
            AvrcpControllerStateMachine.this.removeUnusedArtworkFromBrowseTree();
        }

        private void processSearchReq(String query) {
            if (AvrcpControllerStateMachine.this.mSearch.isSearchingSupported()) {
                AvrcpControllerStateMachine.logD("processSearchReq search: " + query);
                AvrcpControllerService.searchNative(AvrcpControllerStateMachine.this.mDeviceAddress, AvrcpControllerStateMachine.AVRC_CHARSET_UTF8, query.length(), query);
                AvrcpControllerStateMachine avrcpControllerStateMachine = AvrcpControllerStateMachine.this;
                avrcpControllerStateMachine.transitionTo(avrcpControllerStateMachine.mSearch);
                return;
            }
            Log.w(AvrcpControllerStateMachine.TAG, "Search not supported");
        }
    }

    class GetFolderList extends State {
        private static final String STATE_TAG = "Avrcp.GetFolderList";
        boolean mAbort;
        BrowseTree.BrowseNode mBrowseNode;
        BrowseTree.BrowseNode mNextStep;
        byte mScope = 1;

        GetFolderList() {
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            AvrcpControllerStateMachine.logD("Avrcp.GetFolderList Entering GetFolderList");
            AvrcpControllerStateMachine.this.sendMessageDelayed(AvrcpControllerStateMachine.MESSAGE_INTERNAL_CMD_TIMEOUT, 10000L);
            super.enter();
            this.mAbort = false;
            Message msg = AvrcpControllerStateMachine.this.getCurrentMessage();
            if (msg.what == AvrcpControllerStateMachine.MESSAGE_GET_FOLDER_ITEMS) {
                AvrcpControllerStateMachine.logD("Avrcp.GetFolderList new Get Request");
                this.mBrowseNode = (BrowseTree.BrowseNode) msg.obj;
            } else if (msg.what == AvrcpControllerStateMachine.MSG_AVRCP_GET_FOLDER_ITEMS_PTS) {
                Bundle extras = (Bundle) msg.obj;
                int scope = extras.getInt(AvrcpControllerStateMachine.KEY_BROWSE_SCOPE, 0);
                if (scope == 2) {
                    this.mBrowseNode = AvrcpControllerStateMachine.this.mBrowseTree.mSearchNode;
                } else if (scope == 3) {
                    this.mBrowseNode = AvrcpControllerStateMachine.this.mBrowseTree.mNowPlayingNode;
                } else if (scope == 0) {
                    this.mBrowseNode = AvrcpControllerStateMachine.this.mBrowseTree.mRootNode;
                } else {
                    this.mBrowseNode = AvrcpControllerStateMachine.this.mBrowseTree.getCurrentBrowsedFolder();
                }
            }
            BrowseTree.BrowseNode browseNode = this.mBrowseNode;
            if (browseNode == null) {
                setScope((byte) 1);
                AvrcpControllerStateMachine avrcpControllerStateMachine = AvrcpControllerStateMachine.this;
                avrcpControllerStateMachine.transitionTo(avrcpControllerStateMachine.mConnected);
                return;
            }
            if (browseNode.equals(AvrcpControllerStateMachine.this.mBrowseTree.mSearchNode)) {
                setScope((byte) 2);
            } else if (this.mBrowseNode.equals(AvrcpControllerStateMachine.this.mBrowseTree.mNowPlayingNode)) {
                setScope((byte) 3);
            } else if (this.mBrowseNode.equals(AvrcpControllerStateMachine.this.mBrowseTree.mRootNode)) {
                setScope((byte) 0);
            } else {
                setScope((byte) 1);
            }
            if (msg.what != AvrcpControllerStateMachine.MSG_AVRCP_GET_FOLDER_ITEMS_PTS) {
                navigateToFolderOrRetrieve(this.mBrowseNode);
            }
        }

        public void setScope(byte scope) {
            this.mScope = scope;
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public boolean processMessage(Message msg) {
            AvrcpControllerStateMachine.logD("Avrcp.GetFolderList processMessage " + msg.what);
            int i = msg.what;
            if (i == AvrcpControllerStateMachine.MESSAGE_INTERNAL_CMD_TIMEOUT) {
                Log.w(AvrcpControllerStateMachine.TAG, "TIMEOUT");
                AvrcpControllerStateMachine avrcpControllerStateMachine = AvrcpControllerStateMachine.this;
                avrcpControllerStateMachine.transitionTo(avrcpControllerStateMachine.mConnected);
            } else if (i != AvrcpControllerStateMachine.MESSAGE_GET_FOLDER_ITEMS) {
                if (i != AvrcpControllerStateMachine.MSG_AVRCP_SEARCH) {
                    switch (i) {
                        case AvrcpControllerStateMachine.MESSAGE_PROCESS_GET_FOLDER_ITEMS /* 209 */:
                            ArrayList<AvrcpItem> folderList = (ArrayList) msg.obj;
                            int endIndicator = this.mBrowseNode.getExpectedChildren() - 1;
                            AvrcpControllerStateMachine.logD("GetFolderItems: End " + endIndicator + " received " + folderList.size());
                            for (AvrcpItem track : folderList) {
                                if (AvrcpControllerStateMachine.this.shouldDownloadBrowsedImages()) {
                                    AvrcpControllerStateMachine.this.downloadImageIfNeeded(track);
                                } else {
                                    track.setCoverArtUuid(null);
                                }
                            }
                            int newSize = this.mBrowseNode.addChildren(folderList, this.mScope);
                            AvrcpControllerStateMachine.logD("Added " + newSize + " items to the browse tree");
                            AvrcpControllerStateMachine.this.notifyChanged(this.mBrowseNode);
                            if (this.mBrowseNode.getChildrenCount() >= endIndicator || folderList.size() == 0 || this.mAbort) {
                                this.mBrowseNode.setCached(true);
                                sendFolderBroadcastAndUpdateNode();
                                AvrcpControllerStateMachine avrcpControllerStateMachine2 = AvrcpControllerStateMachine.this;
                                avrcpControllerStateMachine2.transitionTo(avrcpControllerStateMachine2.mConnected);
                            } else {
                                fetchContents(this.mBrowseNode);
                                AvrcpControllerStateMachine.this.removeMessages(AvrcpControllerStateMachine.MESSAGE_INTERNAL_CMD_TIMEOUT);
                                AvrcpControllerStateMachine.this.sendMessageDelayed(AvrcpControllerStateMachine.MESSAGE_INTERNAL_CMD_TIMEOUT, 10000L);
                            }
                            break;
                        case AvrcpControllerStateMachine.MESSAGE_PROCESS_GET_FOLDER_ITEMS_OUT_OF_RANGE /* 210 */:
                            this.mBrowseNode.setCached(true);
                            AvrcpControllerStateMachine avrcpControllerStateMachine3 = AvrcpControllerStateMachine.this;
                            avrcpControllerStateMachine3.transitionTo(avrcpControllerStateMachine3.mConnected);
                            break;
                        case AvrcpControllerStateMachine.MESSAGE_PROCESS_GET_PLAYER_ITEMS /* 211 */:
                            BrowseTree.BrowseNode rootNode = AvrcpControllerStateMachine.this.mBrowseTree.mRootNode;
                            if (!rootNode.isCached()) {
                                List<AvrcpPlayer> playerList = (List) msg.obj;
                                AvrcpControllerStateMachine.this.mAvailablePlayerList.clear();
                                for (AvrcpPlayer player : playerList) {
                                    AvrcpControllerStateMachine.this.mAvailablePlayerList.put(player.getId(), player);
                                }
                                rootNode.addChildren(playerList);
                                AvrcpControllerStateMachine.this.mBrowseTree.setCurrentBrowsedFolder(BrowseTree.ROOT);
                                rootNode.setExpectedChildren(playerList.size());
                                rootNode.setCached(true);
                                if (this.mBrowseNode == null) {
                                    this.mBrowseNode = rootNode;
                                }
                                sendFolderBroadcastAndUpdateNode();
                                AvrcpControllerStateMachine.this.notifyChanged(rootNode);
                            }
                            AvrcpControllerStateMachine avrcpControllerStateMachine4 = AvrcpControllerStateMachine.this;
                            avrcpControllerStateMachine4.transitionTo(avrcpControllerStateMachine4.mConnected);
                            break;
                        case AvrcpControllerStateMachine.MESSAGE_PROCESS_FOLDER_PATH /* 212 */:
                            AvrcpControllerStateMachine.this.mBrowseTree.setCurrentBrowsedFolder(this.mNextStep.getID());
                            AvrcpControllerStateMachine.this.mBrowseTree.getCurrentBrowsedFolder().setExpectedChildren(msg.arg1);
                            AvrcpControllerStateMachine.this.refreshCoverArt();
                            if (!this.mAbort) {
                                AvrcpControllerStateMachine.this.removeMessages(AvrcpControllerStateMachine.MESSAGE_INTERNAL_CMD_TIMEOUT);
                                AvrcpControllerStateMachine.this.sendMessageDelayed(AvrcpControllerStateMachine.MESSAGE_INTERNAL_CMD_TIMEOUT, 10000L);
                                navigateToFolderOrRetrieve(this.mBrowseNode);
                            } else {
                                AvrcpControllerStateMachine avrcpControllerStateMachine5 = AvrcpControllerStateMachine.this;
                                avrcpControllerStateMachine5.transitionTo(avrcpControllerStateMachine5.mConnected);
                            }
                            break;
                        case AvrcpControllerStateMachine.MESSAGE_PROCESS_SET_BROWSED_PLAYER /* 213 */:
                            BrowseTree.BrowseNode preBrPlayer = AvrcpControllerStateMachine.this.mBrowseTree.getCurrentBrowsedPlayer();
                            AvrcpControllerStateMachine.this.mBrowseTree.setCurrentBrowsedPlayer(this.mNextStep.getID(), msg.arg1, msg.arg2);
                            BrowseTree.BrowseNode currBrPlayer = AvrcpControllerStateMachine.this.mBrowseTree.getCurrentBrowsedPlayer();
                            if ((currBrPlayer == null || (currBrPlayer != null && !currBrPlayer.equals(preBrPlayer))) && AvrcpControllerStateMachine.this.isActive()) {
                                AvrcpControllerStateMachine.this.refreshSearchNode(false);
                            }
                            AvrcpControllerStateMachine.this.removeMessages(AvrcpControllerStateMachine.MESSAGE_INTERNAL_CMD_TIMEOUT);
                            AvrcpControllerStateMachine.this.sendMessageDelayed(AvrcpControllerStateMachine.MESSAGE_INTERNAL_CMD_TIMEOUT, 10000L);
                            navigateToFolderOrRetrieve(this.mBrowseNode);
                            break;
                        default:
                            return false;
                    }
                } else {
                    this.mAbort = true;
                    AvrcpControllerStateMachine.this.deferMessage(msg);
                }
            } else if (this.mBrowseNode.equals(msg.obj)) {
                AvrcpControllerStateMachine.logD("GetFolderItems: Get The Same Directory, ignore");
            } else {
                if (shouldAbort(this.mBrowseNode.getScope(), ((BrowseTree.BrowseNode) msg.obj).getScope())) {
                    this.mAbort = true;
                }
                AvrcpControllerStateMachine.this.deferMessage(msg);
                AvrcpControllerStateMachine.logD("GetFolderItems: Go Get Another Directory");
            }
            return true;
        }

        private boolean shouldAbort(int currentScope, int fetchScope) {
            if (currentScope == fetchScope || (currentScope == 1 && fetchScope == 0)) {
                return true;
            }
            return false;
        }

        private void fetchContents(BrowseTree.BrowseNode target) {
            int start = target.getChildrenCount();
            int end = Math.min(target.getExpectedChildren(), target.getChildrenCount() + 20) - 1;
            AvrcpControllerStateMachine.logD("fetchContents(title=" + target.getID() + ", scope=" + ((int) target.getScope()) + ", start=" + start + ", end=" + end + ", expected=" + target.getExpectedChildren() + ")");
            byte scope = target.getScope();
            if (scope == 0) {
                AvrcpControllerStateMachine.this.mService.getPlayerListNative(AvrcpControllerStateMachine.this.mDeviceAddress, start, end);
                return;
            }
            if (scope == 1) {
                AvrcpControllerStateMachine.this.mService.getFolderListNative(AvrcpControllerStateMachine.this.mDeviceAddress, start, end);
                return;
            }
            if (scope == 2) {
                AvrcpControllerService.getSearchListNative(AvrcpControllerStateMachine.this.mDeviceAddress, start, end);
                return;
            }
            if (scope == 3) {
                AvrcpControllerStateMachine.this.mService.getNowPlayingListNative(AvrcpControllerStateMachine.this.mDeviceAddress, start, end);
                return;
            }
            Log.e(AvrcpControllerStateMachine.TAG, "Avrcp.GetFolderList Scope " + ((int) target.getScope()) + " cannot be handled here.");
        }

        private void navigateToFolderOrRetrieve(BrowseTree.BrowseNode target) {
            this.mNextStep = AvrcpControllerStateMachine.this.mBrowseTree.getNextStepToFolder(target);
            AvrcpControllerStateMachine.logD("NAVIGATING From " + AvrcpControllerStateMachine.this.mBrowseTree.getCurrentBrowsedFolder().toString());
            AvrcpControllerStateMachine.logD("NAVIGATING Toward " + target.toString());
            if (this.mNextStep == null) {
                return;
            }
            if (target.equals(AvrcpControllerStateMachine.this.mBrowseTree.mNowPlayingNode) || target.equals(AvrcpControllerStateMachine.this.mBrowseTree.mRootNode) || this.mNextStep.equals(AvrcpControllerStateMachine.this.mBrowseTree.getCurrentBrowsedFolder()) || target.equals(AvrcpControllerStateMachine.this.mBrowseTree.mSearchNode)) {
                fetchContents(this.mNextStep);
                return;
            }
            if (this.mNextStep.isPlayer()) {
                AvrcpControllerStateMachine.logD("NAVIGATING Player " + this.mNextStep.toString());
                if (!this.mNextStep.isBrowsable()) {
                    AvrcpControllerStateMachine.logD("Player doesn't support browsing");
                    this.mNextStep.setCached(true);
                    AvrcpControllerStateMachine avrcpControllerStateMachine = AvrcpControllerStateMachine.this;
                    avrcpControllerStateMachine.transitionTo(avrcpControllerStateMachine.mConnected);
                    return;
                }
                AvrcpControllerStateMachine.this.mService.setBrowsedPlayerNative(AvrcpControllerStateMachine.this.mDeviceAddress, (int) this.mNextStep.getBluetoothID());
                return;
            }
            if (this.mNextStep.equals(AvrcpControllerStateMachine.this.mBrowseTree.mNavigateUpNode)) {
                AvrcpControllerStateMachine.logD("NAVIGATING UP " + this.mNextStep.toString());
                this.mNextStep = AvrcpControllerStateMachine.this.mBrowseTree.getCurrentBrowsedFolder().getParent();
                AvrcpControllerStateMachine.this.mBrowseTree.getCurrentBrowsedFolder().setCached(false);
                AvrcpControllerStateMachine.this.removeUnusedArtworkFromBrowseTree();
                AvrcpControllerStateMachine.this.mService.changeFolderPathNative(AvrcpControllerStateMachine.this.mDeviceAddress, AvrcpControllerStateMachine.this.mUidCounter, (byte) 0, 0L);
                return;
            }
            AvrcpControllerStateMachine.logD("NAVIGATING DOWN " + this.mNextStep.toString());
            AvrcpControllerStateMachine.this.mService.changeFolderPathNative(AvrcpControllerStateMachine.this.mDeviceAddress, AvrcpControllerStateMachine.this.mUidCounter, (byte) 1, this.mNextStep.getBluetoothID());
        }

        private void sendFolderBroadcastAndUpdateNode() {
            if (!Utils.isPtsTestMode()) {
                return;
            }
            String id = this.mBrowseNode.getID();
            AvrcpControllerStateMachine.logD("sendFolderBroadcastAndUpdateNode, folderID: " + id + ", size: " + this.mBrowseNode.getChildrenCount());
            List<MediaBrowserCompat.MediaItem> list = this.mBrowseNode.getContents();
            ArrayList<MediaBrowserCompat.MediaItem> folderList = new ArrayList<>(0);
            for (MediaBrowserCompat.MediaItem folder : list) {
                folderList.add(folder);
            }
            Intent intent = new Intent(AvrcpControllerService.ACTION_FOLDER_LIST);
            intent.putExtra(AvrcpControllerService.EXTRA_FOLDER_ID, id);
            intent.putParcelableArrayListExtra(AvrcpControllerService.EXTRA_FOLDER_LIST, folderList);
            AvrcpControllerStateMachine.this.mService.sendBroadcast(intent, ProfileService.BLUETOOTH_PERM);
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void exit() {
            AvrcpControllerStateMachine.this.removeMessages(AvrcpControllerStateMachine.MESSAGE_INTERNAL_CMD_TIMEOUT);
            this.mBrowseNode = null;
            super.exit();
        }
    }

    class Search extends State {
        private String STATE_TAG = "Avrcp.Search";

        Search() {
        }

        public boolean isSearchingSupported() {
            BrowseTree.BrowseNode currBrPlayer = AvrcpControllerStateMachine.this.mBrowseTree.getCurrentBrowsedPlayer();
            if (currBrPlayer == null || currBrPlayer.mItem == null) {
                return false;
            }
            long UID = currBrPlayer.mItem.getUid();
            AvrcpPlayer player = (AvrcpPlayer) AvrcpControllerStateMachine.this.mAvailablePlayerList.get((int) UID);
            boolean supported = player.isSearchingSupported();
            return supported;
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            super.enter();
            AvrcpControllerStateMachine.this.sendMessageDelayed(AvrcpControllerStateMachine.MESSAGE_INTERNAL_CMD_TIMEOUT, 10000L);
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public boolean processMessage(Message msg) {
            if (AvrcpControllerStateMachine.DBG) {
                Log.d(this.STATE_TAG, "processMessage " + msg);
            }
            int i = msg.what;
            if (i == AvrcpControllerStateMachine.MESSAGE_INTERNAL_CMD_TIMEOUT) {
                Log.e(this.STATE_TAG, "search timeout");
                AvrcpControllerStateMachine avrcpControllerStateMachine = AvrcpControllerStateMachine.this;
                avrcpControllerStateMachine.transitionTo(avrcpControllerStateMachine.mConnected);
            } else if (i == AvrcpControllerStateMachine.MESSAGE_PROCESS_SEARCH_RESP) {
                int status = msg.arg1;
                int items = msg.arg2;
                if (AvrcpControllerStateMachine.DBG) {
                    Log.d(this.STATE_TAG, "search response, status: " + status + ", items: " + items);
                }
                AvrcpControllerStateMachine.this.mBrowseTree.mSearchNode.setExpectedChildren(items);
                AvrcpControllerStateMachine.this.broadcastNumOfItems(AvrcpControllerStateMachine.CUSTOM_ACTION_SEARCH, status, items);
                if (AvrcpControllerStateMachine.this.isActive()) {
                    AvrcpControllerStateMachine.this.refreshSearchNode(true);
                }
                AvrcpControllerStateMachine avrcpControllerStateMachine2 = AvrcpControllerStateMachine.this;
                avrcpControllerStateMachine2.transitionTo(avrcpControllerStateMachine2.mConnected);
            } else {
                if (AvrcpControllerStateMachine.DBG) {
                    Log.d(this.STATE_TAG, "deferring message " + msg + " to connected!");
                }
                AvrcpControllerStateMachine.this.deferMessage(msg);
            }
            return true;
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void exit() {
            AvrcpControllerStateMachine.this.removeMessages(AvrcpControllerStateMachine.MESSAGE_INTERNAL_CMD_TIMEOUT);
            super.exit();
        }
    }

    class AddToNowPlaying extends State {
        private String STATE_TAG = "Avrcp.AddToNowPlaying";
        private String mMediaId = null;
        private int mScope = 1;

        AddToNowPlaying() {
        }

        private boolean isSupported() {
            BrowseTree.BrowseNode currBrPlayer = AvrcpControllerStateMachine.this.mBrowseTree.getCurrentBrowsedPlayer();
            if (currBrPlayer == null) {
                return false;
            }
            int playerId = (int) currBrPlayer.getBluetoothID();
            if (AvrcpControllerStateMachine.DBG) {
                Log.d(this.STATE_TAG, "current browsed playerId " + playerId);
            }
            for (int i = 0; i < AvrcpControllerStateMachine.this.mAvailablePlayerList.size(); i++) {
                AvrcpPlayer player = (AvrcpPlayer) AvrcpControllerStateMachine.this.mAvailablePlayerList.valueAt(i);
                if (player.getId() == playerId) {
                    boolean supported = player.supportsFeature(61);
                    return supported;
                }
            }
            return false;
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            Message msg = AvrcpControllerStateMachine.this.getCurrentMessage();
            if (msg.what == AvrcpControllerStateMachine.MSG_AVRCP_ADD_TO_NOW_PLAYING) {
                this.mMediaId = ((Bundle) msg.obj).getString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID);
                this.mScope = ((Bundle) msg.obj).getInt(AvrcpControllerStateMachine.KEY_BROWSE_SCOPE, 0);
            }
            BrowseTree.BrowseNode currItem = AvrcpControllerStateMachine.this.mBrowseTree.findBrowseNodeByID(this.mMediaId);
            Log.d(this.STATE_TAG, "processAddToNowPlayingReq mediaId=" + this.mMediaId + " node=" + currItem + " scope=" + this.mScope);
            if (currItem != null) {
                if (isSupported()) {
                    Log.d(this.STATE_TAG, "Add to now playing, scope: " + this.mScope);
                    if (this.mScope != 0) {
                        AvrcpControllerService.addToNowPlayingNative(AvrcpControllerStateMachine.this.mDeviceAddress, (byte) this.mScope, currItem.getBluetoothID(), AvrcpControllerStateMachine.this.mUidCounter);
                        AvrcpControllerStateMachine.this.sendMessageDelayed(AvrcpControllerStateMachine.MESSAGE_INTERNAL_CMD_TIMEOUT, 10000L);
                        return;
                    }
                    Log.w(this.STATE_TAG, "Add to now playing invalid scope: " + this.mScope);
                    AvrcpControllerStateMachine.this.broadcastAddToNowPlayingResult(10);
                    AvrcpControllerStateMachine avrcpControllerStateMachine = AvrcpControllerStateMachine.this;
                    avrcpControllerStateMachine.transitionTo(avrcpControllerStateMachine.mConnected);
                    return;
                }
                Log.w(this.STATE_TAG, "Add to now playing not supported");
                AvrcpControllerStateMachine.this.broadcastAddToNowPlayingResult(0);
                AvrcpControllerStateMachine avrcpControllerStateMachine2 = AvrcpControllerStateMachine.this;
                avrcpControllerStateMachine2.transitionTo(avrcpControllerStateMachine2.mConnected);
                return;
            }
            AvrcpControllerStateMachine avrcpControllerStateMachine3 = AvrcpControllerStateMachine.this;
            avrcpControllerStateMachine3.transitionTo(avrcpControllerStateMachine3.mConnected);
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public boolean processMessage(Message msg) {
            Log.d(this.STATE_TAG, "processMessage " + msg);
            int i = msg.what;
            if (i == AvrcpControllerStateMachine.MESSAGE_INTERNAL_CMD_TIMEOUT) {
                AvrcpControllerStateMachine avrcpControllerStateMachine = AvrcpControllerStateMachine.this;
                avrcpControllerStateMachine.transitionTo(avrcpControllerStateMachine.mConnected);
                return true;
            }
            if (i == AvrcpControllerStateMachine.MESSAGE_PROCESS_UIDS_CHANGED) {
                AvrcpControllerStateMachine.this.processUIDSChange(msg);
                return true;
            }
            if (i == AvrcpControllerStateMachine.MESSAGE_PROCESS_ADD_TO_NOW_PLAYING) {
                AvrcpControllerStateMachine.this.removeMessages(AvrcpControllerStateMachine.MESSAGE_INTERNAL_CMD_TIMEOUT);
                AvrcpControllerStateMachine.this.broadcastAddToNowPlayingResult(msg.arg1);
                AvrcpControllerStateMachine avrcpControllerStateMachine2 = AvrcpControllerStateMachine.this;
                avrcpControllerStateMachine2.transitionTo(avrcpControllerStateMachine2.mConnected);
                return true;
            }
            Log.d(this.STATE_TAG, "deferring message " + msg + " to connected!");
            AvrcpControllerStateMachine.this.deferMessage(msg);
            return true;
        }
    }

    protected class Disconnecting extends State {
        protected Disconnecting() {
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            AvrcpControllerStateMachine.this.disconnectCoverArt();
            AvrcpControllerStateMachine.this.onBrowsingDisconnected();
            AvrcpControllerStateMachine.this.setActive(false);
            AvrcpControllerStateMachine.this.broadcastConnectionStateChanged(3);
            AvrcpControllerStateMachine avrcpControllerStateMachine = AvrcpControllerStateMachine.this;
            avrcpControllerStateMachine.transitionTo(avrcpControllerStateMachine.mDisconnected);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleAbsVolumeRequest(int absVol, int label) {
        logD("handleAbsVolumeRequest: absVol = " + absVol + ", label = " + label);
        this.mVolumeChangedNotificationsToIgnore = this.mVolumeChangedNotificationsToIgnore + 1;
        removeMessages(MESSAGE_INTERNAL_ABS_VOL_TIMEOUT);
        sendMessageDelayed(MESSAGE_INTERNAL_ABS_VOL_TIMEOUT, 1000L);
        logD("handleAbsVolumeRequest: not to set absVol");
        this.mService.sendAbsVolRspNative(this.mDeviceAddress, absVol, label);
    }

    private void setAbsVolume(int absVol) {
        int currIndex = 0;
        try {
            currIndex = this.mCarAudioManager.getGroupVolume(mVolumeGroupId);
        } catch (CarNotConnectedException e) {
            Log.e(TAG, "Car is not connected", e);
        } catch (NullPointerException e2) {
            Log.e(TAG, "mCarAudioManager is NULL!", e2);
        }
        int newIndex = (mMaxVolume * absVol) / 127;
        logD(" setAbsVolume =" + absVol + " maxVol = " + mMaxVolume + " cur = " + currIndex + " new = " + newIndex);
        if (newIndex != currIndex) {
            try {
                this.mCarAudioManager.setGroupVolume(mVolumeGroupId, newIndex, 1);
            } catch (CarNotConnectedException e3) {
                Log.e(TAG, "Car is not connected", e3);
            } catch (NullPointerException e4) {
                Log.e(TAG, "mCarAudioManager is NULL!", e4);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getAbsVolume() {
        int currIndex = 0;
        try {
            currIndex = this.mCarAudioManager.getGroupVolume(mVolumeGroupId);
        } catch (CarNotConnectedException e) {
            Log.e(TAG, "Car is not connected", e);
        } catch (NullPointerException e2) {
            Log.e(TAG, "mCarAudioManager is NULL!", e2);
        }
        if (mMaxVolume != 0) {
            Log.w(TAG, "mMaxVolume is not updated!");
            int newIndex = (currIndex * 127) / mMaxVolume;
            return newIndex;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processUIDSChange(Message msg) {
        BluetoothDevice device = (BluetoothDevice) msg.obj;
        int uidCounter = msg.arg1;
        if (DBG) {
            Log.d(TAG, " processUIDSChange device: " + device.getAddressOnlyforLog() + ", uidCounter: " + uidCounter);
        }
        this.mUidCounter = uidCounter;
        if (Utils.isPtsTestMode()) {
            refreshCoverArt();
        }
        Intent intent_uids = new Intent("android.bluetooth.avrcp-controller.profile.action.UIDS_EVENT");
        intent_uids.putExtra("android.bluetooth.device.extra.DEVICE", device);
        this.mService.sendBroadcast(intent_uids, ProfileService.BLUETOOTH_PERM);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldDownloadBrowsedImages() {
        return this.mService.getResources().getBoolean(R.bool.avrcp_controller_cover_art_browsed_images);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadImageIfNeeded(AvrcpItem track) {
        downloadImageIfNeeded(track, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadImageIfNeeded(AvrcpItem track, boolean forced) {
        String uuid;
        if (this.mCoverArtManager != null && (uuid = track.getCoverArtUuid()) != null) {
            if (forced) {
                if (this.mCoverArtManager.getHandleForUuid(this.mDevice, uuid) != null) {
                    this.mCoverArtManager.downloadImage(this.mDevice, uuid, forced);
                    return;
                } else {
                    AvrcpControllerService.getElementAttributesNative(this.mDeviceAddress, (byte) 0, null);
                    return;
                }
            }
            Uri imageUri = this.mCoverArtManager.getImageUri(this.mDevice, uuid);
            if (imageUri != null) {
                track.setCoverArtLocation(imageUri);
            } else {
                this.mCoverArtManager.downloadImage(this.mDevice, uuid);
            }
        }
    }

    void requestAudioFocus() {
        A2dpSinkService a2dpSinkService = A2dpSinkService.getA2dpSinkService();
        if (a2dpSinkService != null) {
            a2dpSinkService.requestAudioFocus(this.mDevice, true);
        }
    }

    boolean hasAudioFocus() {
        int focusState = -1;
        A2dpSinkService a2dpSinkService = A2dpSinkService.getA2dpSinkService();
        if (a2dpSinkService != null) {
            focusState = a2dpSinkService.getFocusState();
        }
        logD("hasAudioFocus: " + focusState);
        return focusState == 1;
    }

    protected void broadcastConnectionStateChanged(int currentState) {
        if (this.mMostRecentState == currentState) {
            return;
        }
        if (currentState == 2) {
            MetricsLogger.logProfileConnectionEvent(BluetoothMetricsProto.ProfileId.AVRCP_CONTROLLER);
        }
        logD("Connection state " + this.mDevice.getAddressOnlyforLog() + ": " + this.mMostRecentState + "->" + currentState);
        Intent intent = new Intent("android.bluetooth.avrcp-controller.profile.action.CONNECTION_STATE_CHANGED");
        intent.putExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", this.mMostRecentState);
        intent.putExtra("android.bluetooth.profile.extra.STATE", currentState);
        intent.putExtra("android.bluetooth.device.extra.DEVICE", this.mDevice);
        intent.addFlags(VCardConfig.FLAG_APPEND_TYPE_PARAM);
        this.mMostRecentState = currentState;
        this.mService.sendBroadcast(intent, ProfileService.BLUETOOTH_PERM);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void broadcastAddToNowPlayingResult(int status) {
        logD("broadcastAddToNowPlayingResult status: " + status);
        broadcastResult(CUSTOM_ACTION_ADD_TO_NOW_PLAYING, status);
    }

    private void broadcastResult(String cmd, int status) {
        int result = getResult(status);
        logD("broadcastResult cmd: " + cmd + ", result: " + result + ", status: " + status);
        Intent intent = new Intent(ACTION_CUSTOM_ACTION_RESULT);
        intent.putExtra(EXTRA_CUSTOM_ACTION, cmd);
        intent.putExtra(EXTRA_CUSTOM_ACTION_RESULT, result);
        this.mService.sendBroadcast(intent, ProfileService.BLUETOOTH_PERM);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldRequestFocus() {
        return this.mService.getResources().getBoolean(R.bool.a2dp_sink_automatically_request_audio_focus);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCustomActionSearch(Bundle extras) {
        logD("handleCustomActionSearch extras: " + extras);
        if (extras == null) {
            return;
        }
        String searchQuery = extras.getString(KEY_SEARCH);
        sendMessage(MSG_AVRCP_SEARCH, searchQuery);
    }

    public void handleCustomActionSendPassThruCmd(Bundle extras) {
        logD("handleCustomActionSendPassThruCmd extras: " + extras);
        if (extras == null) {
            return;
        }
        int cmd = extras.getInt(KEY_CMD);
        int state = extras.getInt(KEY_STATE);
        sendMessage(MSG_AVRCP_PASSTHRU_EXT, cmd, state);
    }

    public void handleCustomActionGetItemAttributes(Bundle extras) {
        logD("handleCustomActionGetItemAttributes extras: " + extras);
        if (extras == null) {
            return;
        }
        sendMessage(MSG_AVRCP_GET_ITEM_ATTR, extras);
    }

    public void handleCustomActionGetElementAttributes(Bundle extras) {
        logD("handleCustomActionGetElementAttributes extras" + extras);
        if (extras == null) {
            return;
        }
        sendMessage(MSG_AVRCP_GET_ELEMENT_ATTR, extras);
    }

    public void handleCustomActionGetFolderItems(Bundle extras) {
        logD("handleCustomActionGetFolderItems extras: " + extras);
        if (extras == null) {
            return;
        }
        sendMessage(MSG_AVRCP_GET_FOLDER_ITEMS_PTS, extras);
    }

    public void handleCustomActionRequestContinuingResponse(Bundle extras) {
        logD("handleCustomActionRequestContinuingResponse extras: " + extras);
        if (extras == null) {
            return;
        }
        int pduId = extras.getInt(KEY_PDU_ID, 0);
        sendMessage(MSG_AVRCP_REQUEST_CONTINUING_RESPONSE, pduId, 0);
    }

    public void handleCustomActionAbortContinuingResponse(Bundle extras) {
        logD("handleCustomActionAbortContinuingResponse extras: " + extras);
        if (extras == null) {
            return;
        }
        int pduId = extras.getInt(KEY_PDU_ID, 0);
        sendMessage(MSG_AVRCP_ABORT_CONTINUING_RESPONSE, pduId, 0);
    }

    public void handleCustomActionAddToNowPlaying(Bundle extras) {
        logD("handleCustomActionAddToNowPlaying extras: " + extras);
        if (extras == null) {
            return;
        }
        sendMessage(MSG_AVRCP_ADD_TO_NOW_PLAYING, extras);
    }

    public void handleCustomActionSetAddressedPlayer(Bundle extras) {
        logD("handleCustomActionSetAddressedPlayer extras: " + extras);
        if (extras == null) {
            return;
        }
        sendMessage(MSG_AVRCP_SET_ADDRESSED_PLAYER_PTS, extras);
    }

    public void handleCustomActionPlayItem(Bundle extras) {
        logD("handleCustomActionPlayItem extras: " + extras);
        if (extras == null) {
            return;
        }
        sendMessage(MSG_AVRCP_PLAY_ITEM_PTS, extras);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void broadcastNumOfItems(String cmd, int status, int items) {
        logD("broadcastNumOfItems cmd: " + cmd + ", status: " + status + ", items: " + items);
        Intent intent = createIntent(cmd, status);
        intent.putExtra(EXTRA_NUM_OF_ITEMS, items);
        this.mService.sendBroadcast(intent, ProfileService.BLUETOOTH_PERM);
    }

    private Intent createIntent(String cmd, int status) {
        int result = getResult(status);
        Intent intent = new Intent(ACTION_CUSTOM_ACTION_RESULT);
        intent.putExtra(EXTRA_CUSTOM_ACTION, cmd);
        intent.putExtra(EXTRA_CUSTOM_ACTION_RESULT, result);
        return intent;
    }

    private int getResult(int status) {
        if (status == 0) {
            return 3;
        }
        if (status == 1) {
            return 2;
        }
        if (status != 4) {
            return (status == 10 || status == 11) ? 2 : 1;
        }
        return 0;
    }
}
