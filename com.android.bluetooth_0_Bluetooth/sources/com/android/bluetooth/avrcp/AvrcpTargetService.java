package com.android.bluetooth.avrcp;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.IBluetoothAvrcpTarget;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Looper;
import android.os.SystemProperties;
import android.os.UserManager;
import android.util.Log;
import com.android.bluetooth.BluetoothMetricsProto;
import com.android.bluetooth.Utils;
import com.android.bluetooth.a2dp.A2dpService;
import com.android.bluetooth.avrcp.MediaPlayerList;
import com.android.bluetooth.btservice.MetricsLogger;
import com.android.bluetooth.btservice.ProfileService;
import com.android.bluetooth.btservice.ServiceFactory;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class AvrcpTargetService extends ProfileService {
    private static final String AVRCP_ENABLE_PROPERTY = "persist.bluetooth.enablenewavrcp";
    private static final int AVRCP_MAX_VOL = 127;
    private static final boolean DEBUG = true;
    private static final int MEDIA_KEY_EVENT_LOGGER_SIZE = 20;
    private static final String MEDIA_KEY_EVENT_LOGGER_TITLE = "Media Key Events";
    private static final String TAG = "AvrcpTargetService";
    private static int sDeviceMaxVolume = 0;
    private static AvrcpTargetService sInstance = null;
    private AudioManager mAudioManager;
    private MediaData mCurrentData;
    private MediaPlayerList mMediaPlayerList;
    private AvrcpNativeInterface mNativeInterface;
    private AvrcpBroadcastReceiver mReceiver;
    private AvrcpVolumeManager mVolumeManager;
    private final AvrcpEventLogger mMediaKeyEventLogger = new AvrcpEventLogger(20, MEDIA_KEY_EVENT_LOGGER_TITLE);
    private ServiceFactory mFactory = new ServiceFactory();

    class ListCallback implements MediaPlayerList.MediaUpdateCallback, MediaPlayerList.FolderUpdateCallback {
        ListCallback() {
        }

        @Override // com.android.bluetooth.avrcp.MediaPlayerList.MediaUpdateCallback
        public void run(MediaData data) {
            if (AvrcpTargetService.this.mNativeInterface == null) {
                return;
            }
            boolean metadata = !Objects.equals(AvrcpTargetService.this.mCurrentData.metadata, data.metadata);
            boolean state = !MediaPlayerWrapper.playstateEquals(AvrcpTargetService.this.mCurrentData.state, data.state);
            boolean queue = !Objects.equals(AvrcpTargetService.this.mCurrentData.queue, data.queue);
            Log.d(AvrcpTargetService.TAG, "onMediaUpdated: track_changed=" + metadata + " state=" + state + " queue=" + queue);
            AvrcpTargetService.this.mCurrentData = data;
            AvrcpTargetService.this.mNativeInterface.sendMediaUpdate(metadata, state, queue);
        }

        @Override // com.android.bluetooth.avrcp.MediaPlayerList.FolderUpdateCallback
        public void run(boolean availablePlayers, boolean addressedPlayers, boolean uids) {
            if (AvrcpTargetService.this.mNativeInterface == null) {
                return;
            }
            AvrcpTargetService.this.mNativeInterface.sendFolderUpdate(availablePlayers, addressedPlayers, uids);
        }
    }

    private class AvrcpBroadcastReceiver extends BroadcastReceiver {
        private AvrcpBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            BluetoothDevice device;
            String action = intent.getAction();
            if (action.equals("android.bluetooth.a2dp.profile.action.ACTIVE_DEVICE_CHANGED")) {
                if (AvrcpTargetService.this.mNativeInterface == null) {
                    return;
                }
                AvrcpTargetService.this.mNativeInterface.sendMediaUpdate(false, true, false);
                return;
            }
            if (action.equals("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED")) {
                if (AvrcpTargetService.this.mNativeInterface == null || (device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE")) == null) {
                    return;
                }
                int state = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                if (state == 0 && AvrcpTargetService.this.mNativeInterface.disconnectDevice(device.getAddress())) {
                    Log.d(AvrcpTargetService.TAG, "request to disconnect device " + device.getAddressOnlyforLog());
                    return;
                }
                return;
            }
            if (action.equals("android.media.VOLUME_CHANGED_ACTION")) {
                int streamType = intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_TYPE", -1);
                if (streamType == 3) {
                    int volume = intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_VALUE", 0);
                    BluetoothDevice activeDevice = AvrcpTargetService.this.getA2dpActiveDevice();
                    if (activeDevice != null && !AvrcpTargetService.this.mVolumeManager.getAbsoluteVolumeSupported(activeDevice)) {
                        Log.d(AvrcpTargetService.TAG, "stream volume change to " + volume + " " + activeDevice);
                        AvrcpTargetService.this.mVolumeManager.storeVolumeForDevice(activeDevice, volume);
                    }
                }
            }
        }
    }

    public static AvrcpTargetService get() {
        return sInstance;
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    public String getName() {
        return TAG;
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected ProfileService.IProfileServiceBinder initBinder() {
        return new AvrcpTargetBinder(this);
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected void setUserUnlocked(int userId) {
        Log.i(TAG, "User unlocked, initializing the service");
        if (!SystemProperties.getBoolean(AVRCP_ENABLE_PROPERTY, true)) {
            Log.w(TAG, "Skipping initialization of the new AVRCP Target Player List");
            sInstance = null;
        } else {
            MediaPlayerList mediaPlayerList = this.mMediaPlayerList;
            if (mediaPlayerList != null) {
                mediaPlayerList.init(new ListCallback());
            }
        }
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected boolean start() {
        if (sInstance != null) {
            Log.wtf(TAG, "The service has already been initialized");
            return false;
        }
        Log.i(TAG, "Starting the AVRCP Target Service");
        this.mCurrentData = new MediaData(null, null, null);
        if (!SystemProperties.getBoolean(AVRCP_ENABLE_PROPERTY, true)) {
            Log.w(TAG, "Skipping initialization of the new AVRCP Target Service");
            sInstance = null;
            return true;
        }
        AudioManager audioManager = (AudioManager) getSystemService("audio");
        this.mAudioManager = audioManager;
        sDeviceMaxVolume = audioManager.getStreamMaxVolume(3);
        this.mMediaPlayerList = new MediaPlayerList(Looper.myLooper(), this);
        AvrcpNativeInterface avrcpNativeInterface = AvrcpNativeInterface.getInterface();
        this.mNativeInterface = avrcpNativeInterface;
        avrcpNativeInterface.init(this);
        this.mVolumeManager = new AvrcpVolumeManager(this, this.mAudioManager, this.mNativeInterface);
        UserManager userManager = UserManager.get(getApplicationContext());
        if (userManager.isUserUnlocked()) {
            this.mMediaPlayerList.init(new ListCallback());
        }
        this.mReceiver = new AvrcpBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.bluetooth.a2dp.profile.action.ACTIVE_DEVICE_CHANGED");
        filter.addAction("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
        filter.addAction("android.media.VOLUME_CHANGED_ACTION");
        registerReceiver(this.mReceiver, filter);
        sInstance = this;
        return true;
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected boolean stop() {
        Log.i(TAG, "Stopping the AVRCP Target Service");
        if (sInstance == null) {
            Log.w(TAG, "stop() called before start()");
            return true;
        }
        sInstance = null;
        unregisterReceiver(this.mReceiver);
        MediaPlayerList mediaPlayerList = this.mMediaPlayerList;
        if (mediaPlayerList != null) {
            mediaPlayerList.cleanup();
        }
        AvrcpNativeInterface avrcpNativeInterface = this.mNativeInterface;
        if (avrcpNativeInterface != null) {
            avrcpNativeInterface.cleanup();
        }
        this.mMediaPlayerList = null;
        this.mNativeInterface = null;
        this.mAudioManager = null;
        this.mReceiver = null;
        return true;
    }

    private void init() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BluetoothDevice getA2dpActiveDevice() {
        A2dpService service = this.mFactory.getA2dpService();
        if (service == null) {
            return null;
        }
        return service.getActiveDevice();
    }

    private void setA2dpActiveDevice(BluetoothDevice device) {
        A2dpService service = A2dpService.getA2dpService();
        if (service == null) {
            Log.d(TAG, "setA2dpActiveDevice: A2dp service not found");
        } else {
            service.setActiveDevice(device);
        }
    }

    void deviceConnected(BluetoothDevice device, boolean absoluteVolume) {
        Log.i(TAG, "deviceConnected: device=" + device.getAddressOnlyforLog() + " absoluteVolume=" + absoluteVolume);
        this.mVolumeManager.deviceConnected(device, absoluteVolume);
        MetricsLogger.logProfileConnectionEvent(BluetoothMetricsProto.ProfileId.AVRCP);
    }

    void deviceDisconnected(BluetoothDevice device) {
        Log.i(TAG, "deviceDisconnected: device=" + device.getAddressOnlyforLog());
        this.mVolumeManager.deviceDisconnected(device);
    }

    public void volumeDeviceSwitched(BluetoothDevice device) {
        Log.d(TAG, "volumeDeviceSwitched: device=" + device.getAddressOnlyforLog());
        this.mVolumeManager.volumeDeviceSwitched(device);
    }

    public void removeStoredVolumeForDevice(BluetoothDevice device) {
        if (device == null) {
            return;
        }
        this.mVolumeManager.removeStoredVolumeForDevice(device);
    }

    public int getRememberedVolumeForDevice(BluetoothDevice device) {
        if (device == null) {
            return -1;
        }
        AvrcpVolumeManager avrcpVolumeManager = this.mVolumeManager;
        return avrcpVolumeManager.getVolume(device, avrcpVolumeManager.getNewDeviceVolume());
    }

    void setVolume(int avrcpVolume) {
        BluetoothDevice activeDevice = getA2dpActiveDevice();
        if (activeDevice == null) {
            Log.d(TAG, "setVolume: no active device");
        } else {
            this.mVolumeManager.setVolume(activeDevice, avrcpVolume);
        }
    }

    public void sendVolumeChanged(int deviceVolume) {
        BluetoothDevice activeDevice = getA2dpActiveDevice();
        if (activeDevice == null) {
            Log.d(TAG, "sendVolumeChanged: no active device");
        } else {
            this.mVolumeManager.sendVolumeChanged(activeDevice, deviceVolume);
        }
    }

    Metadata getCurrentSongInfo() {
        return this.mMediaPlayerList.getCurrentSongInfo();
    }

    PlayStatus getPlayState() {
        return PlayStatus.fromPlaybackState(this.mMediaPlayerList.getCurrentPlayStatus(), Long.parseLong(getCurrentSongInfo().duration));
    }

    String getCurrentMediaId() {
        String id = this.mMediaPlayerList.getCurrentMediaId();
        if (id != null) {
            return id;
        }
        Metadata song = getCurrentSongInfo();
        return song != null ? song.mediaId : "error";
    }

    List<Metadata> getNowPlayingList() {
        return this.mMediaPlayerList.getNowPlayingList();
    }

    int getCurrentPlayerId() {
        return this.mMediaPlayerList.getCurrentPlayerId();
    }

    List<PlayerInfo> getMediaPlayerList() {
        return this.mMediaPlayerList.getMediaPlayerList();
    }

    void getPlayerRoot(int playerId, MediaPlayerList.GetPlayerRootCallback cb) {
        this.mMediaPlayerList.getPlayerRoot(playerId, cb);
    }

    void getFolderItems(int playerId, String mediaId, MediaPlayerList.GetFolderItemsCallback cb) {
        this.mMediaPlayerList.getFolderItems(playerId, mediaId, cb);
    }

    void playItem(int playerId, boolean nowPlaying, String mediaId) {
        this.mMediaPlayerList.playItem(playerId, nowPlaying, mediaId);
    }

    void sendMediaKeyEvent(int event, boolean pushed) {
        BluetoothDevice activeDevice = getA2dpActiveDevice();
        MediaPlayerWrapper player = this.mMediaPlayerList.getActivePlayer();
        AvrcpEventLogger avrcpEventLogger = this.mMediaKeyEventLogger;
        StringBuilder sb = new StringBuilder();
        sb.append("getMediaKeyEvent: device=");
        sb.append(activeDevice);
        sb.append(" event=");
        sb.append(event);
        sb.append(" pushed=");
        sb.append(pushed);
        sb.append(" to ");
        sb.append(player == null ? null : player.getPackageName());
        avrcpEventLogger.logd(true, TAG, sb.toString());
        this.mMediaPlayerList.sendMediaKeyEvent(event, pushed);
    }

    void setActiveDevice(BluetoothDevice device) {
        Log.i(TAG, "setActiveDevice: device=" + device);
        if (device == null) {
            Log.wtf(TAG, "setActiveDevice: could not find device " + device);
        }
        setA2dpActiveDevice(device);
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    public void dump(StringBuilder sb) {
        sb.append("\nProfile: AvrcpTargetService:\n");
        if (sInstance == null) {
            sb.append("AvrcpTargetService not running");
            return;
        }
        StringBuilder tempBuilder = new StringBuilder();
        MediaPlayerList mediaPlayerList = this.mMediaPlayerList;
        if (mediaPlayerList != null) {
            mediaPlayerList.dump(tempBuilder);
        } else {
            tempBuilder.append("\nMedia Player List is empty\n");
        }
        this.mMediaKeyEventLogger.dump(tempBuilder);
        tempBuilder.append("\n");
        this.mVolumeManager.dump(tempBuilder);
        sb.append(tempBuilder.toString().replaceAll("(?m)^", "  "));
    }

    private static class AvrcpTargetBinder extends IBluetoothAvrcpTarget.Stub implements ProfileService.IProfileServiceBinder {
        private AvrcpTargetService mService;

        AvrcpTargetBinder(AvrcpTargetService service) {
            this.mService = service;
        }

        @Override // com.android.bluetooth.btservice.ProfileService.IProfileServiceBinder
        public void cleanup() {
            this.mService = null;
        }

        public void sendVolumeChanged(int volume) {
            if (!Utils.checkCaller()) {
                Log.w(AvrcpTargetService.TAG, "sendVolumeChanged not allowed for non-active user");
                return;
            }
            AvrcpTargetService avrcpTargetService = this.mService;
            if (avrcpTargetService == null) {
                return;
            }
            avrcpTargetService.sendVolumeChanged(volume);
        }
    }
}
