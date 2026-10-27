package com.android.bluetooth.avrcp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.net.INetd;
import android.util.Log;
import com.android.bluetooth.avrcp.MediaPlayerList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AvrcpNativeInterface {
    private static final boolean DEBUG = true;
    private static final String TAG = "AvrcpNativeInterface";
    private static AvrcpNativeInterface sInstance;
    private AvrcpTargetService mAvrcpService;

    private static native void classInitNative();

    private native void cleanupNative();

    private native boolean connectDeviceNative(String str);

    private native boolean disconnectDeviceNative(String str);

    private native void getFolderItemsResponseNative(String str, List<ListItem> list);

    private native void initNative();

    private native void sendFolderUpdateNative(boolean z, boolean z2, boolean z3);

    private native void sendMediaUpdateNative(boolean z, boolean z2, boolean z3);

    private native void sendVolumeChangedNative(String str, int i);

    private native void setBrowsedPlayerResponseNative(int i, boolean z, String str, int i2);

    static {
        classInitNative();
    }

    static AvrcpNativeInterface getInterface() {
        if (sInstance == null) {
            sInstance = new AvrcpNativeInterface();
        }
        return sInstance;
    }

    void init(AvrcpTargetService service) {
        d("Init AvrcpNativeInterface");
        this.mAvrcpService = service;
        initNative();
    }

    void cleanup() {
        d("Cleanup AvrcpNativeInterface");
        this.mAvrcpService = null;
        cleanupNative();
    }

    Metadata getCurrentSongInfo() {
        d("getCurrentSongInfo");
        AvrcpTargetService avrcpTargetService = this.mAvrcpService;
        if (avrcpTargetService == null) {
            Log.w(TAG, "getCurrentSongInfo(): AvrcpTargetService is null");
            return null;
        }
        return avrcpTargetService.getCurrentSongInfo();
    }

    PlayStatus getPlayStatus() {
        d("getPlayStatus");
        AvrcpTargetService avrcpTargetService = this.mAvrcpService;
        if (avrcpTargetService == null) {
            Log.w(TAG, "getPlayStatus(): AvrcpTargetService is null");
            return null;
        }
        return avrcpTargetService.getPlayState();
    }

    void sendMediaKeyEvent(int keyEvent, boolean pushed) {
        d("sendMediaKeyEvent: keyEvent=" + keyEvent + " pushed=" + pushed);
        AvrcpTargetService avrcpTargetService = this.mAvrcpService;
        if (avrcpTargetService == null) {
            Log.w(TAG, "sendMediaKeyEvent(): AvrcpTargetService is null");
        } else {
            avrcpTargetService.sendMediaKeyEvent(keyEvent, pushed);
        }
    }

    String getCurrentMediaId() {
        d("getCurrentMediaId");
        AvrcpTargetService avrcpTargetService = this.mAvrcpService;
        if (avrcpTargetService == null) {
            Log.w(TAG, "getMediaPlayerList(): AvrcpTargetService is null");
            return INetd.NEXTHOP_NONE;
        }
        return avrcpTargetService.getCurrentMediaId();
    }

    List<Metadata> getNowPlayingList() {
        d("getNowPlayingList");
        AvrcpTargetService avrcpTargetService = this.mAvrcpService;
        if (avrcpTargetService == null) {
            Log.w(TAG, "getMediaPlayerList(): AvrcpTargetService is null");
            return null;
        }
        return avrcpTargetService.getNowPlayingList();
    }

    int getCurrentPlayerId() {
        d("getCurrentPlayerId");
        AvrcpTargetService avrcpTargetService = this.mAvrcpService;
        if (avrcpTargetService == null) {
            Log.w(TAG, "getMediaPlayerList(): AvrcpTargetService is null");
            return -1;
        }
        return avrcpTargetService.getCurrentPlayerId();
    }

    List<PlayerInfo> getMediaPlayerList() {
        d("getMediaPlayerList");
        AvrcpTargetService avrcpTargetService = this.mAvrcpService;
        if (avrcpTargetService == null) {
            Log.w(TAG, "getMediaPlayerList(): AvrcpTargetService is null");
            return null;
        }
        return avrcpTargetService.getMediaPlayerList();
    }

    void setBrowsedPlayer(int playerId) {
        d("setBrowsedPlayer: playerId=" + playerId);
        this.mAvrcpService.getPlayerRoot(playerId, new MediaPlayerList.GetPlayerRootCallback() { // from class: com.android.bluetooth.avrcp.-$$Lambda$AvrcpNativeInterface$vNJITuoaCzOnnbl8EHU94oBzRuA
            @Override // com.android.bluetooth.avrcp.MediaPlayerList.GetPlayerRootCallback
            public final void run(int i, boolean z, String str, int i2) {
                this.f$0.lambda$setBrowsedPlayer$0$AvrcpNativeInterface(i, z, str, i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: renamed from: setBrowsedPlayerResponse, reason: merged with bridge method [inline-methods] */
    public void lambda$setBrowsedPlayer$0$AvrcpNativeInterface(int playerId, boolean success, String rootId, int numItems) {
        d("setBrowsedPlayerResponse: playerId=" + playerId + " success=" + success + " rootId=" + rootId + " numItems=" + numItems);
        setBrowsedPlayerResponseNative(playerId, success, rootId, numItems);
    }

    void getFolderItemsRequest(int playerId, String mediaId) {
        d("getFolderItemsRequest: playerId=" + playerId + " mediaId=" + mediaId);
        this.mAvrcpService.getFolderItems(playerId, mediaId, new MediaPlayerList.GetFolderItemsCallback() { // from class: com.android.bluetooth.avrcp.-$$Lambda$AvrcpNativeInterface$yqsIIES5LBrM35GDVi_3FCOWPYA
            @Override // com.android.bluetooth.avrcp.MediaPlayerList.GetFolderItemsCallback
            public final void run(String str, List list) {
                this.f$0.lambda$getFolderItemsRequest$1$AvrcpNativeInterface(str, list);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: renamed from: getFolderItemsResponse, reason: merged with bridge method [inline-methods] */
    public void lambda$getFolderItemsRequest$1$AvrcpNativeInterface(String parentId, List<ListItem> items) {
        d("getFolderItemsResponse: parentId=" + parentId + " items.size=" + items.size());
        getFolderItemsResponseNative(parentId, items);
    }

    void sendMediaUpdate(boolean metadata, boolean playStatus, boolean queue) {
        d("sendMediaUpdate: metadata=" + metadata + " playStatus=" + playStatus + " queue=" + queue);
        sendMediaUpdateNative(metadata, playStatus, queue);
    }

    void sendFolderUpdate(boolean availablePlayers, boolean addressedPlayers, boolean uids) {
        d("sendFolderUpdate: availablePlayers=" + availablePlayers + " addressedPlayers=" + addressedPlayers + " uids=" + uids);
        sendFolderUpdateNative(availablePlayers, addressedPlayers, uids);
    }

    void playItem(int playerId, boolean nowPlaying, String mediaId) {
        d("playItem: playerId=" + playerId + " nowPlaying=" + nowPlaying + " mediaId" + mediaId);
        AvrcpTargetService avrcpTargetService = this.mAvrcpService;
        if (avrcpTargetService == null) {
            Log.d(TAG, "playItem: AvrcpTargetService is null");
        } else {
            avrcpTargetService.playItem(playerId, nowPlaying, mediaId);
        }
    }

    boolean connectDevice(String bdaddr) {
        d("connectDevice: bdaddr=" + bdaddr);
        return connectDeviceNative(bdaddr);
    }

    boolean disconnectDevice(String bdaddr) {
        d("disconnectDevice: bdaddr=" + bdaddr);
        return disconnectDeviceNative(bdaddr);
    }

    void setActiveDevice(String bdaddr) {
        BluetoothDevice device = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(bdaddr.toUpperCase());
        d("setActiveDevice: device=" + device.getAddressOnlyforLog());
        this.mAvrcpService.setActiveDevice(device);
    }

    void deviceConnected(String bdaddr, boolean absoluteVolume) {
        BluetoothDevice device = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(bdaddr.toUpperCase());
        d("deviceConnected: device=" + device.getAddressOnlyforLog() + " absoluteVolume=" + absoluteVolume);
        AvrcpTargetService avrcpTargetService = this.mAvrcpService;
        if (avrcpTargetService == null) {
            Log.w(TAG, "deviceConnected: AvrcpTargetService is null");
        } else {
            avrcpTargetService.deviceConnected(device, absoluteVolume);
        }
    }

    void deviceDisconnected(String bdaddr) {
        BluetoothDevice device = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(bdaddr.toUpperCase());
        d("deviceDisconnected: device=" + device.getAddressOnlyforLog());
        AvrcpTargetService avrcpTargetService = this.mAvrcpService;
        if (avrcpTargetService == null) {
            Log.w(TAG, "deviceDisconnected: AvrcpTargetService is null");
        } else {
            avrcpTargetService.deviceDisconnected(device);
        }
    }

    void sendVolumeChanged(String bdaddr, int volume) {
        d("sendVolumeChanged: volume=" + volume);
        sendVolumeChangedNative(bdaddr, volume);
    }

    void setVolume(int volume) {
        d("setVolume: volume=" + volume);
        AvrcpTargetService avrcpTargetService = this.mAvrcpService;
        if (avrcpTargetService == null) {
            Log.w(TAG, "setVolume: AvrcpTargetService is null");
        } else {
            avrcpTargetService.setVolume(volume);
        }
    }

    private static void d(String msg) {
        Log.d(TAG, msg);
    }
}
