package com.android.bluetooth.avrcpcontroller;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothAvrcpPlayerSettings;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.IBluetoothAvrcpController;
import android.content.Intent;
import android.os.Message;
import android.os.SystemProperties;
import android.support.v4.media.MediaBrowserCompat;
import android.util.Log;
import com.android.bluetooth.R;
import com.android.bluetooth.Utils;
import com.android.bluetooth.avrcpcontroller.AvrcpCoverArtManager;
import com.android.bluetooth.avrcpcontroller.AvrcpItem;
import com.android.bluetooth.avrcpcontroller.BrowseTree;
import com.android.bluetooth.btservice.ProfileService;
import com.android.bluetooth.opp.BluetoothShare;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public class AvrcpControllerService extends ProfileService {
    public static final String ACTION_FOLDER_LIST = "android.bluetooth.avrcp-controller.profile.action.FOLDER_LIST";
    public static final String ACTION_TRACK_EVENT = "android.bluetooth.avrcp-controller.profile.action.TRACK_EVENT";
    public static final byte BROWSE_SCOPE_NOW_PLAYING = 3;
    public static final byte BROWSE_SCOPE_PLAYER_LIST = 0;
    public static final byte BROWSE_SCOPE_SEARCH = 2;
    public static final byte BROWSE_SCOPE_VFS = 1;
    public static final String EXTRA_FOLDER_ID = "android.bluetooth.avrcp-controller.profile.extra.EXTRA_FOLDER_ID";
    public static final String EXTRA_FOLDER_LIST = "android.bluetooth.avrcp-controller.profile.extra.FOLDER_LIST";
    public static final String EXTRA_METADATA = "android.bluetooth.avrcp-controller.profile.extra.METADATA";
    public static final byte FOLDER_NAVIGATION_DIRECTION_DOWN = 1;
    public static final byte FOLDER_NAVIGATION_DIRECTION_UP = 0;
    public static final int JNI_AVRC_INV_RANGE = 11;
    public static final int JNI_AVRC_STS_INVALID_CMD = 0;
    public static final int JNI_AVRC_STS_INVALID_PARAMETER = 1;
    public static final int JNI_AVRC_STS_INVALID_SCOPE = 10;
    public static final int JNI_AVRC_STS_NO_ERROR = 4;
    private static final byte JNI_PLAY_STATUS_ERROR = -1;
    private static final byte JNI_PLAY_STATUS_FWD_SEEK = 3;
    private static final byte JNI_PLAY_STATUS_PAUSED = 2;
    private static final byte JNI_PLAY_STATUS_PLAYING = 1;
    private static final byte JNI_PLAY_STATUS_REV_SEEK = 4;
    private static final byte JNI_PLAY_STATUS_STOPPED = 0;
    public static final int KEY_STATE_PRESSED = 0;
    public static final int KEY_STATE_RELEASED = 1;
    static final int MAXIMUM_CONNECTED_DEVICES = 5;
    public static final int PASS_THRU_CMD_ID_BACKWARD = 76;
    public static final int PASS_THRU_CMD_ID_FF = 73;
    public static final int PASS_THRU_CMD_ID_FORWARD = 75;
    public static final int PASS_THRU_CMD_ID_PAUSE = 70;
    public static final int PASS_THRU_CMD_ID_PLAY = 68;
    public static final int PASS_THRU_CMD_ID_REWIND = 72;
    public static final int PASS_THRU_CMD_ID_STOP = 69;
    public static final int PASS_THRU_CMD_ID_VOL_DOWN = 66;
    public static final int PASS_THRU_CMD_ID_VOL_UP = 65;
    static final boolean VDBG = false;
    static BrowseTree sBrowseTree;
    private static AvrcpControllerService sService;
    protected AvrcpCoverArtManager mCoverArtManager;
    static final String TAG = "AvrcpControllerService";
    static final boolean DBG = Log.isLoggable(TAG, 3);
    protected Map<BluetoothDevice, AvrcpControllerStateMachine> mDeviceStateMap = new ConcurrentHashMap(1);
    private boolean mCoverArtEnabled = false;
    private final BluetoothAdapter mAdapter = BluetoothAdapter.getDefaultAdapter();

    static native void abortContinuingResponseNative(byte[] bArr, byte b);

    static native void addToNowPlayingNative(byte[] bArr, byte b, long j, int i);

    private static native void classInitNative();

    private native void cleanupNative();

    static native void getElementAttributesNative(byte[] bArr, byte b, int[] iArr);

    static native void getFolderItemsNative(byte[] bArr, byte b, byte b2, byte b3, byte b4, int[] iArr);

    static native void getItemAttributesNative(byte[] bArr, byte b, long j, int i, byte b2, int[] iArr);

    public static native void getSearchListNative(byte[] bArr, int i, int i2);

    private native void initNative();

    static native void requestContinuingResponseNative(byte[] bArr, byte b);

    public static native void searchNative(byte[] bArr, int i, int i2, String str);

    public native void changeFolderPathNative(byte[] bArr, int i, byte b, long j);

    public native void getCurrentMetadataNative(byte[] bArr);

    public native void getFolderListNative(byte[] bArr, int i, int i2);

    public native void getNowPlayingListNative(byte[] bArr, int i, int i2);

    public native void getPlaybackStateNative(byte[] bArr);

    public native void getPlayerListNative(byte[] bArr, int i, int i2);

    public native void playItemNative(byte[] bArr, byte b, long j, int i);

    public native void sendAbsVolRspNative(byte[] bArr, int i, int i2);

    public native boolean sendGroupNavigationCommandNative(byte[] bArr, int i, int i2);

    public native boolean sendPassThroughCommandNative(byte[] bArr, int i, int i2);

    public native void sendRegisterAbsVolRspNative(byte[] bArr, byte b, int i, int i2);

    public native void setAddressedPlayerNative(byte[] bArr, int i);

    public native void setBrowsedPlayerNative(byte[] bArr, int i);

    public native void setPlayerApplicationSettingValuesNative(byte[] bArr, byte b, byte[] bArr2, byte[] bArr3);

    static {
        classInitNative();
    }

    private class ImageDownloadCallback implements AvrcpCoverArtManager.Callback {
        private ImageDownloadCallback() {
        }

        @Override // com.android.bluetooth.avrcpcontroller.AvrcpCoverArtManager.Callback
        public void onImageDownloadComplete(BluetoothDevice device, AvrcpCoverArtManager.DownloadEvent event) {
            if (AvrcpControllerService.DBG) {
                Log.d(AvrcpControllerService.TAG, "Image downloaded [device: " + device.getAddressOnlyforLog() + ", uuid: " + event.getUuid() + ", uri: " + event.getUri());
            }
            AvrcpControllerStateMachine stateMachine = AvrcpControllerService.this.getStateMachine(device);
            if (stateMachine == null) {
                Log.e(AvrcpControllerService.TAG, "No state machine found for device " + device.getAddressOnlyforLog());
                AvrcpControllerService.this.mCoverArtManager.removeImage(device, event.getUuid());
                return;
            }
            stateMachine.sendMessage(BluetoothShare.STATUS_BAD_REQUEST, event);
        }
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected synchronized boolean start() {
        initNative();
        boolean z = getResources().getBoolean(R.bool.avrcp_controller_enable_cover_art);
        this.mCoverArtEnabled = z;
        if (z) {
            this.mCoverArtManager = new AvrcpCoverArtManager(this, new ImageDownloadCallback());
        }
        sBrowseTree = new BrowseTree(null);
        sService = this;
        Intent startIntent = new Intent(this, (Class<?>) BluetoothMediaBrowserService.class);
        startService(startIntent);
        return true;
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected synchronized boolean stop() {
        Intent stopIntent = new Intent(this, (Class<?>) BluetoothMediaBrowserService.class);
        stopService(stopIntent);
        for (AvrcpControllerStateMachine stateMachine : this.mDeviceStateMap.values()) {
            stateMachine.doQuit();
        }
        sService = null;
        sBrowseTree = null;
        if (this.mCoverArtManager != null) {
            this.mCoverArtManager.cleanup();
            this.mCoverArtManager = null;
        }
        return true;
    }

    public static AvrcpControllerService getAvrcpControllerService() {
        return sService;
    }

    protected AvrcpControllerStateMachine newStateMachine(BluetoothDevice device) {
        return new AvrcpControllerStateMachine(device, this);
    }

    protected void getCurrentMetadataIfNoCoverArt(BluetoothDevice device) {
        AvrcpControllerStateMachine stateMachine;
        AvrcpItem track;
        if (device != null && (stateMachine = getStateMachine(device)) != null && (track = stateMachine.getCurrentTrack()) != null && track.getCoverArtLocation() == null) {
            getCurrentMetadataNative(Utils.getByteAddress(device));
        }
    }

    private void refreshContents(BrowseTree.BrowseNode node) {
        AvrcpControllerStateMachine stateMachine;
        BluetoothDevice device = node.getDevice();
        if (device != null && (stateMachine = getStateMachine(device)) != null) {
            stateMachine.requestContents(node);
        }
    }

    void playItem(String parentMediaId) {
        if (DBG) {
            Log.d(TAG, "playItem(" + parentMediaId + ")");
        }
        if (sBrowseTree.findBrowseNodeByID(parentMediaId) == null) {
            for (AvrcpControllerStateMachine stateMachine : this.mDeviceStateMap.values()) {
                BrowseTree.BrowseNode requestedNode = stateMachine.findNode(parentMediaId);
                if (requestedNode != null) {
                    if (DBG) {
                        Log.d(TAG, "Found a node");
                    }
                    stateMachine.playItem(requestedNode);
                    return;
                }
            }
        }
    }

    public synchronized List<MediaBrowserCompat.MediaItem> getContents(String parentMediaId) {
        if (DBG) {
            Log.d(TAG, "getContents(" + parentMediaId + ")");
        }
        BrowseTree.BrowseNode requestedNode = sBrowseTree.findBrowseNodeByID(parentMediaId);
        if (requestedNode == null) {
            Iterator<AvrcpControllerStateMachine> it = this.mDeviceStateMap.values().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                AvrcpControllerStateMachine stateMachine = it.next();
                requestedNode = stateMachine.findNode(parentMediaId);
                if (requestedNode != null) {
                    Log.d(TAG, "Found a node");
                    break;
                }
            }
        }
        if (requestedNode == null) {
            if (DBG) {
                Log.d(TAG, "Didn't find a node");
            }
            return new ArrayList(0);
        }
        if (!requestedNode.isCached()) {
            if (DBG) {
                Log.d(TAG, "node is not cached");
            }
            refreshContents(requestedNode);
        }
        if (DBG) {
            Log.d(TAG, "Returning contents");
        }
        return requestedNode.getContents();
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected ProfileService.IProfileServiceBinder initBinder() {
        return new AvrcpControllerServiceBinder(this);
    }

    private static class AvrcpControllerServiceBinder extends IBluetoothAvrcpController.Stub implements ProfileService.IProfileServiceBinder {
        private AvrcpControllerService mService;

        private AvrcpControllerService getService() {
            if (!Utils.checkCaller()) {
                Log.w(AvrcpControllerService.TAG, "AVRCP call not allowed for non-active user");
                return null;
            }
            AvrcpControllerService avrcpControllerService = this.mService;
            if (avrcpControllerService != null) {
                return avrcpControllerService;
            }
            return null;
        }

        AvrcpControllerServiceBinder(AvrcpControllerService service) {
            this.mService = service;
        }

        @Override // com.android.bluetooth.btservice.ProfileService.IProfileServiceBinder
        public void cleanup() {
            this.mService = null;
        }

        public List<BluetoothDevice> getConnectedDevices() {
            AvrcpControllerService service = getService();
            if (service == null) {
                return new ArrayList(0);
            }
            return service.getConnectedDevices();
        }

        public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states) {
            AvrcpControllerService service = getService();
            if (service == null) {
                return new ArrayList(0);
            }
            return service.getDevicesMatchingConnectionStates(states);
        }

        public int getConnectionState(BluetoothDevice device) {
            AvrcpControllerService service = getService();
            if (service == null) {
                return 0;
            }
            return service.getConnectionState(device);
        }

        public void sendGroupNavigationCmd(BluetoothDevice device, int keyCode, int keyState) {
            Log.w(AvrcpControllerService.TAG, "sendGroupNavigationCmd not implemented");
        }

        public boolean setPlayerApplicationSetting(BluetoothAvrcpPlayerSettings settings) {
            Log.w(AvrcpControllerService.TAG, "setPlayerApplicationSetting not implemented");
            return false;
        }

        public BluetoothAvrcpPlayerSettings getPlayerSettings(BluetoothDevice device) {
            Log.w(AvrcpControllerService.TAG, "getPlayerSettings not implemented");
            return null;
        }

        public int getSupportedFeatures(BluetoothDevice device) {
            Log.v(AvrcpControllerService.TAG, "Binder Call: getSupportedFeatures");
            AvrcpControllerService service = getService();
            if (service == null) {
                return 0;
            }
            return service.getSupportedFeatures(device);
        }

        public void startFetchingAlbumArt(BluetoothDevice device, String type, String scheme, String mimeType, int height, int width, int maxSize) {
            Log.v(AvrcpControllerService.TAG, "Binder Call: startFetchingAlbumArt");
            AvrcpControllerService service = getService();
            if (service == null) {
                return;
            }
            service.startFetchingAlbumArt(device, type, scheme, mimeType, height, width, maxSize);
        }
    }

    private void handlePassthroughRsp(int id, int keyState, byte[] address) {
        if (DBG) {
            Log.d(TAG, "passthrough response received as: key: " + id + " state: " + keyState + "address:" + address);
        }
    }

    private void handleGroupNavigationRsp(int id, int keyState) {
        if (DBG) {
            Log.d(TAG, "group navigation response received as: key: " + id + " state: " + keyState);
        }
    }

    private synchronized void onConnectionStateChanged(boolean remoteControlConnected, boolean browsingConnected, byte[] address) {
        BluetoothDevice device = this.mAdapter.getRemoteDevice(address);
        if (DBG) {
            Log.d(TAG, "onConnectionStateChanged " + remoteControlConnected + " " + browsingConnected + device.getAddressOnlyforLog());
        }
        if (device == null) {
            Log.e(TAG, "onConnectionStateChanged Device is null");
            return;
        }
        StackEvent event = StackEvent.connectionStateChanged(remoteControlConnected, browsingConnected);
        AvrcpControllerStateMachine stateMachine = getOrCreateStateMachine(device);
        if (remoteControlConnected || browsingConnected) {
            stateMachine.connect(event);
        } else {
            stateMachine.disconnect();
        }
    }

    private void getRcFeatures(byte[] address, int features) {
        BluetoothDevice device = this.mAdapter.getRemoteDevice(address);
        AvrcpControllerStateMachine stateMachine = getStateMachine(device);
        if (stateMachine != null) {
            Message msg = stateMachine.obtainMessage(223, features);
            stateMachine.sendMessage(msg);
        }
    }

    private void getRcPsm(byte[] address, int psm) {
        BluetoothDevice device = this.mAdapter.getRemoteDevice(address);
        if (DBG) {
            Log.d(TAG, "getRcPsm(device=" + device.getAddressOnlyforLog() + ", psm=" + psm + ")");
        }
        AvrcpControllerStateMachine stateMachine = getOrCreateStateMachine(device);
        if (stateMachine != null) {
            stateMachine.sendMessage(220, psm);
        }
    }

    private void setPlayerAppSettingRsp(byte[] address, byte accepted) {
    }

    private synchronized void handleRegisterNotificationAbsVol(byte[] address, byte label) {
        if (DBG) {
            Log.d(TAG, "handleRegisterNotificationAbsVol");
        }
        BluetoothDevice device = this.mAdapter.getRemoteDevice(address);
        AvrcpControllerStateMachine stateMachine = getStateMachine(device);
        if (stateMachine != null) {
            stateMachine.sendMessage(204);
        }
    }

    private synchronized void handleSetAbsVolume(byte[] address, byte absVol, byte label) {
        if (DBG) {
            Log.d(TAG, "handleSetAbsVolume ");
        }
        BluetoothDevice device = this.mAdapter.getRemoteDevice(address);
        AvrcpControllerStateMachine stateMachine = getStateMachine(device);
        if (stateMachine != null) {
            stateMachine.sendMessage(203, absVol);
        }
    }

    private synchronized void onTrackChanged(byte[] address, byte numAttributes, int[] attributes, String[] attribVals) {
        String handle;
        if (DBG) {
            Log.d(TAG, "onTrackChanged");
        }
        BluetoothDevice device = this.mAdapter.getRemoteDevice(address);
        AvrcpControllerStateMachine stateMachine = getStateMachine(device);
        if (stateMachine != null) {
            AvrcpItem.Builder aib = new AvrcpItem.Builder();
            aib.fromAvrcpAttributeArray(attributes, attribVals);
            aib.setDevice(device);
            aib.setItemType(3);
            aib.setUuid(UUID.randomUUID().toString());
            AvrcpItem item = aib.build();
            if (this.mCoverArtManager != null && (handle = item.getCoverArtHandle()) != null) {
                item.setCoverArtUuid(this.mCoverArtManager.getUuidForHandle(device, handle));
            }
            stateMachine.sendMessage(205, item);
        }
    }

    private synchronized void onPlayPositionChanged(byte[] address, int songLen, int currSongPosition) {
        if (DBG) {
            Log.d(TAG, "onPlayPositionChanged pos " + currSongPosition);
        }
        BluetoothDevice device = this.mAdapter.getRemoteDevice(address);
        AvrcpControllerStateMachine stateMachine = getStateMachine(device);
        if (stateMachine != null) {
            stateMachine.sendMessage(206, songLen, currSongPosition);
        }
    }

    private synchronized void onPlayStatusChanged(byte[] address, byte playStatus) {
        int playbackState;
        if (DBG) {
            Log.d(TAG, "onPlayStatusChanged " + ((int) playStatus));
        }
        if (playStatus == 0) {
            playbackState = 1;
        } else if (playStatus == 1) {
            playbackState = 3;
        } else if (playStatus == 2) {
            playbackState = 2;
        } else if (playStatus == 3) {
            playbackState = 4;
        } else if (playStatus == 4) {
            playbackState = 5;
        } else {
            playbackState = 0;
        }
        BluetoothDevice device = this.mAdapter.getRemoteDevice(address);
        AvrcpControllerStateMachine stateMachine = getStateMachine(device);
        if (stateMachine != null) {
            stateMachine.sendMessage(207, playbackState);
        }
    }

    private void onUidsChanged(byte[] address, int uidCounter) {
        if (DBG) {
            Log.d(TAG, "onUidsChanged uidCounter: " + uidCounter);
        }
        BluetoothDevice device = this.mAdapter.getRemoteDevice(address);
        AvrcpControllerStateMachine stateMachine = getStateMachine(device);
        if (stateMachine != null) {
            stateMachine.sendMessage(222, uidCounter, 0, device);
        }
    }

    private synchronized void handlePlayerAppSetting(byte[] address, byte[] playerAttribRsp, int rspLen) {
        if (DBG) {
            Log.d(TAG, "handlePlayerAppSetting rspLen = " + rspLen);
        }
        BluetoothDevice device = this.mAdapter.getRemoteDevice(address);
        AvrcpControllerStateMachine stateMachine = getStateMachine(device);
        if (stateMachine != null) {
            PlayerApplicationSettings supportedSettings = PlayerApplicationSettings.makeSupportedSettings(playerAttribRsp);
            stateMachine.sendMessage(217, supportedSettings);
        }
    }

    private synchronized void onPlayerAppSettingChanged(byte[] address, byte[] playerAttribRsp, int rspLen) {
        if (DBG) {
            Log.d(TAG, "onPlayerAppSettingChanged ");
        }
        BluetoothDevice device = this.mAdapter.getRemoteDevice(address);
        AvrcpControllerStateMachine stateMachine = getStateMachine(device);
        if (stateMachine != null) {
            PlayerApplicationSettings currentSettings = PlayerApplicationSettings.makeSettings(playerAttribRsp);
            stateMachine.sendMessage(218, currentSettings);
        }
    }

    private void onAvailablePlayerChanged(byte[] address) {
        if (DBG) {
            Log.d(TAG, " onAvailablePlayerChanged");
        }
        BluetoothDevice device = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(address);
        AvrcpControllerStateMachine stateMachine = getStateMachine(device);
        if (stateMachine != null) {
            stateMachine.sendMessage(219);
        }
    }

    void handleGetFolderItemsRsp(byte[] address, int status, AvrcpItem[] items) {
        String handle;
        if (DBG) {
            Log.d(TAG, "handleGetFolderItemsRsp called with status " + status + " items " + items.length + " items.");
        }
        BluetoothDevice device = this.mAdapter.getRemoteDevice(address);
        List<AvrcpItem> itemsList = new ArrayList<>();
        for (AvrcpItem item : items) {
            if (this.mCoverArtManager != null && (handle = item.getCoverArtHandle()) != null) {
                item.setCoverArtUuid(this.mCoverArtManager.getUuidForHandle(device, handle));
            }
            itemsList.add(item);
        }
        AvrcpControllerStateMachine stateMachine = getStateMachine(device);
        if (stateMachine != null) {
            stateMachine.sendMessage(209, itemsList);
        }
    }

    void handleGetPlayerItemsRsp(byte[] address, AvrcpPlayer[] items) {
        if (DBG) {
            Log.d(TAG, "handleGetFolderItemsRsp called with " + items.length + " items.");
        }
        List<AvrcpPlayer> itemsList = new ArrayList<>();
        for (AvrcpPlayer item : items) {
            itemsList.add(item);
        }
        BluetoothDevice device = this.mAdapter.getRemoteDevice(address);
        AvrcpControllerStateMachine stateMachine = getStateMachine(device);
        if (stateMachine != null) {
            stateMachine.sendMessage(211, itemsList);
        }
    }

    AvrcpItem createFromNativeMediaItem(byte[] address, long uid, int type, String name, int[] attrIds, String[] attrVals) {
        BluetoothDevice device = this.mAdapter.getRemoteDevice(address);
        AvrcpItem.Builder aib = new AvrcpItem.Builder().fromAvrcpAttributeArray(attrIds, attrVals);
        aib.setDevice(device);
        aib.setItemType(3);
        aib.setType(type);
        aib.setUid(uid);
        aib.setUuid(UUID.randomUUID().toString());
        aib.setPlayable(true);
        AvrcpItem item = aib.build();
        return item;
    }

    AvrcpItem createFromNativeFolderItem(byte[] address, long uid, int type, String name, int playable) {
        BluetoothDevice device = this.mAdapter.getRemoteDevice(address);
        AvrcpItem.Builder aib = new AvrcpItem.Builder();
        aib.setDevice(device);
        aib.setItemType(2);
        aib.setType(type);
        aib.setUid(uid);
        aib.setUuid(UUID.randomUUID().toString());
        aib.setDisplayableName(name);
        aib.setPlayable(playable == 1);
        aib.setBrowsable(true);
        return aib.build();
    }

    AvrcpPlayer createFromNativePlayerItem(byte[] address, int id, String name, byte[] transportFlags, int playStatus, int playerType) {
        int playbackState;
        if (playStatus != 0) {
            if (playStatus != 1) {
                if (playStatus != 2) {
                    if (playStatus == 3 || playStatus == 4) {
                        playbackState = 4;
                    } else {
                        playbackState = 0;
                    }
                } else {
                    playbackState = 2;
                }
            } else {
                playbackState = 3;
            }
        } else {
            playbackState = 1;
        }
        BluetoothDevice device = this.mAdapter.getRemoteDevice(address);
        AvrcpPlayer player = new AvrcpPlayer(device, id, name, transportFlags, playbackState, playerType);
        return player;
    }

    private void handleChangeFolderRsp(byte[] address, int count) {
        if (DBG) {
            Log.d(TAG, "handleChangeFolderRsp count: " + count);
        }
        BluetoothDevice device = this.mAdapter.getRemoteDevice(address);
        AvrcpControllerStateMachine stateMachine = getStateMachine(device);
        if (stateMachine != null) {
            stateMachine.sendMessage(212, count);
        }
    }

    private void handleSetBrowsedPlayerRsp(byte[] address, int items, int depth) {
        if (DBG) {
            Log.d(TAG, "handleSetBrowsedPlayerRsp depth: " + depth);
        }
        BluetoothDevice device = this.mAdapter.getRemoteDevice(address);
        AvrcpControllerStateMachine stateMachine = getStateMachine(device);
        if (stateMachine != null) {
            stateMachine.sendMessage(213, items, depth);
        }
    }

    private void handleSetAddressedPlayerRsp(byte[] address, int status) {
        if (DBG) {
            Log.d(TAG, "handleSetAddressedPlayerRsp status: " + status);
        }
        BluetoothDevice device = this.mAdapter.getRemoteDevice(address);
        AvrcpControllerStateMachine stateMachine = getStateMachine(device);
        if (stateMachine != null) {
            stateMachine.sendMessage(214);
        }
    }

    private void handleAddressedPlayerChanged(byte[] address, int id) {
        if (DBG) {
            Log.d(TAG, "handleAddressedPlayerChanged id: " + id);
        }
        BluetoothDevice device = this.mAdapter.getRemoteDevice(address);
        AvrcpControllerStateMachine stateMachine = getStateMachine(device);
        if (stateMachine != null) {
            stateMachine.sendMessage(215, id);
        }
    }

    private void handleNowPlayingContentChanged(byte[] address) {
        if (DBG) {
            Log.d(TAG, "handleNowPlayingContentChanged");
        }
        BluetoothDevice device = this.mAdapter.getRemoteDevice(address);
        AvrcpControllerStateMachine stateMachine = getStateMachine(device);
        if (stateMachine != null) {
            stateMachine.nowPlayingContentChanged();
        }
    }

    private void handleSearchRsp(byte[] address, int status, int uid, int items) {
        if (DBG) {
            Log.d(TAG, "handleSearchRsp status: " + status + ", uid: " + uid + ", items: " + items);
        }
        BluetoothDevice device = this.mAdapter.getRemoteDevice(address);
        AvrcpControllerStateMachine stateMachine = getStateMachine(device);
        if (stateMachine != null) {
            stateMachine.sendMessage(221, status, items);
        }
    }

    private void handleAddToNowPlayingRsp(byte[] address, int status) {
        if (DBG) {
            Log.d(TAG, "handleAddToNowPlayingRsp status" + status);
        }
        BluetoothDevice device = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(address);
        AvrcpControllerStateMachine stateMachine = getStateMachine(device);
        if (stateMachine != null) {
            stateMachine.sendMessage(224, status, 0);
        }
    }

    public synchronized boolean disconnect(BluetoothDevice device) {
        if (DBG) {
            StringBuilder sb = new StringBuilder();
            dump(sb);
            Log.d(TAG, "MAP disconnect device: " + device.getAddressOnlyforLog() + ", InstanceMap start state: " + sb.toString());
        }
        AvrcpControllerStateMachine stateMachine = this.mDeviceStateMap.get(device);
        if (stateMachine == null) {
            return false;
        }
        int connectionState = stateMachine.getState();
        if (connectionState != 2 && connectionState != 1) {
            return false;
        }
        stateMachine.disconnect();
        if (DBG) {
            StringBuilder sb2 = new StringBuilder();
            dump(sb2);
            Log.d(TAG, "MAP disconnect device: " + device.getAddressOnlyforLog() + ", InstanceMap start state: " + sb2.toString());
        }
        return true;
    }

    public void removeStateMachine(AvrcpControllerStateMachine stateMachine) {
        this.mDeviceStateMap.remove(stateMachine.getDevice());
    }

    public List<BluetoothDevice> getConnectedDevices() {
        return getDevicesMatchingConnectionStates(new int[]{2});
    }

    protected AvrcpControllerStateMachine getStateMachine(BluetoothDevice device) {
        return this.mDeviceStateMap.get(device);
    }

    protected AvrcpControllerStateMachine getOrCreateStateMachine(BluetoothDevice device) {
        AvrcpControllerStateMachine stateMachine = this.mDeviceStateMap.get(device);
        if (stateMachine == null) {
            AvrcpControllerStateMachine stateMachine2 = newStateMachine(device);
            this.mDeviceStateMap.put(device, stateMachine2);
            stateMachine2.start();
            return stateMachine2;
        }
        return stateMachine;
    }

    protected AvrcpCoverArtManager getCoverArtManager() {
        return this.mCoverArtManager;
    }

    List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states) {
        if (DBG) {
            Log.d(TAG, "getDevicesMatchingConnectionStates" + Arrays.toString(states));
        }
        List<BluetoothDevice> deviceList = new ArrayList<>();
        Set<BluetoothDevice> bondedDevices = this.mAdapter.getBondedDevices();
        for (BluetoothDevice device : bondedDevices) {
            int connectionState = getConnectionState(device);
            if (DBG) {
                Log.d(TAG, "Device: " + device.getAddressOnlyforLog() + "State: " + connectionState);
            }
            for (int i : states) {
                if (connectionState == i) {
                    deviceList.add(device);
                }
            }
        }
        if (DBG) {
            Log.d(TAG, deviceList.toString());
        }
        Log.d(TAG, "GetDevicesDone");
        return deviceList;
    }

    synchronized int getConnectionState(BluetoothDevice device) {
        AvrcpControllerStateMachine stateMachine;
        stateMachine = this.mDeviceStateMap.get(device);
        return stateMachine == null ? 0 : stateMachine.getState();
    }

    public synchronized int getSupportedFeatures(BluetoothDevice device) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        if (DBG) {
            Log.d(TAG, "getSupportedFeatures device " + device.getAddressOnlyforLog());
        }
        AvrcpControllerStateMachine stateMachine = getStateMachine(device);
        if (stateMachine != null) {
            return stateMachine.getRemoteFeatures();
        }
        return 0;
    }

    public synchronized void startFetchingAlbumArt(BluetoothDevice device, String type, String scheme, String mimeType, int height, int width, int maxSize) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        if (DBG) {
            Log.d(TAG, "startFetchingAlbumArt mimeType " + mimeType + " pixel " + height + " * " + width + " maxSize: " + maxSize);
        }
        SystemProperties.set(AvrcpCoverArtManager.AVRCP_CONTROLLER_COVER_ART_IMGTYPE, type);
        SystemProperties.set(AvrcpCoverArtManager.AVRCP_CONTROLLER_COVER_ART_SCHEME, scheme);
        SystemProperties.set(AvrcpCoverArtManager.AVRCP_CONTROLLER_COVER_ART_MIMETYPE, mimeType);
        SystemProperties.set(AvrcpCoverArtManager.AVRCP_CONTROLLER_COVER_ART_IMGHEIGHT, String.valueOf(height));
        SystemProperties.set(AvrcpCoverArtManager.AVRCP_CONTROLLER_COVER_ART_IMGWIDTH, String.valueOf(width));
        SystemProperties.set(AvrcpCoverArtManager.AVRCP_CONTROLLER_COVER_ART_IMGMAXSIZE, String.valueOf(maxSize));
        AvrcpControllerStateMachine stateMachine = getStateMachine(device);
        if (stateMachine != null) {
            stateMachine.sendMessage(450);
        }
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    public void dump(StringBuilder sb) {
        super.dump(sb);
        ProfileService.println(sb, "Devices Tracked = " + this.mDeviceStateMap.size());
        for (AvrcpControllerStateMachine stateMachine : this.mDeviceStateMap.values()) {
            ProfileService.println(sb, "==== StateMachine for " + stateMachine.getDevice() + " ====");
            stateMachine.dump(sb);
        }
        sb.append("\n  sBrowseTree: " + sBrowseTree.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append("\n  Cover Artwork Enabled: ");
        sb2.append(this.mCoverArtEnabled ? "True" : "False");
        sb.append(sb2.toString());
        if (this.mCoverArtManager != null) {
            sb.append("\n  " + this.mCoverArtManager.toString());
        }
    }
}
