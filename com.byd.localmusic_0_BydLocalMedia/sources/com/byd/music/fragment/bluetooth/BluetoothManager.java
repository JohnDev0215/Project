package com.byd.music.fragment.bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaMetadata;
import android.media.browse.MediaBrowser;
import android.media.session.MediaController;
import android.media.session.PlaybackState;
import com.android.internal.telephony.SmsApplication;
import com.blankj.utilcode.util.Utils;
import com.byd.music.utils.LogUtil;

/* JADX INFO: loaded from: classes5.dex */
public class BluetoothManager implements ReceiverListener {
    private static BluetoothA2dp bluetoothA2dp;
    public BlueToothReceiver blueToothReceiver;
    private BluetoothAdapter bluetoothAdapter;
    private IBluetoothListener iBluetoothListener;
    private final String TAG = BluetoothManager.class.getName();
    private MediaBrowser mMediaBrowser = null;
    private MediaController mMediaController = null;
    private int mTryConnectMediaSessionCnt = 0;
    private final BluetoothProfile.ServiceListener mListener = new BluetoothProfile.ServiceListener() { // from class: com.byd.music.fragment.bluetooth.BluetoothManager.1
        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int i) {
            LogUtil.d(BluetoothManager.this.TAG, "onServiceDisconnected!，profile：" + i);
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            LogUtil.d(BluetoothManager.this.TAG, "onServiceConnected! proxy：" + bluetoothProfile);
            if (i == 2) {
                BluetoothA2dp unused = BluetoothManager.bluetoothA2dp = (BluetoothA2dp) bluetoothProfile;
            }
        }
    };
    MediaController.Callback callback = new MediaController.Callback() { // from class: com.byd.music.fragment.bluetooth.BluetoothManager.2
        @Override // android.media.session.MediaController.Callback
        public void onMetadataChanged(MediaMetadata mediaMetadata) {
            super.onMetadataChanged(mediaMetadata);
            LogUtil.d(BluetoothManager.this.TAG, "metadata:" + mediaMetadata);
            BluetoothManager.this.iBluetoothListener.onMetadataChanged(mediaMetadata);
        }

        @Override // android.media.session.MediaController.Callback
        public void onPlaybackStateChanged(PlaybackState playbackState) {
            super.onPlaybackStateChanged(playbackState);
            BluetoothManager.this.iBluetoothListener.onPlaybackStateChanged(playbackState);
        }
    };
    private final MediaBrowser.ConnectionCallback mConnectionCallback = new MediaBrowser.ConnectionCallback() { // from class: com.byd.music.fragment.bluetooth.BluetoothManager.3
        @Override // android.media.browse.MediaBrowser.ConnectionCallback
        public void onConnected() {
            super.onConnected();
            if (BluetoothManager.this.mMediaBrowser.getSessionToken() == null) {
                LogUtil.d(BluetoothManager.this.TAG, "mMediaBrowser mMediaBrowser.getSessionToken() is null");
                throw new IllegalArgumentException("No Session token");
            }
            BluetoothManager.this.mMediaController = new MediaController(Utils.getApp(), BluetoothManager.this.mMediaBrowser.getSessionToken());
            BluetoothManager.this.mMediaController.registerCallback(BluetoothManager.this.callback);
            BluetoothManager.this.mMediaBrowser.getServiceComponent().getPackageName();
            BluetoothManager.this.iBluetoothListener.onMetadataChanged(BluetoothManager.this.mMediaController.getMetadata());
            BluetoothManager.this.mTryConnectMediaSessionCnt = 0;
        }

        @Override // android.media.browse.MediaBrowser.ConnectionCallback
        public void onConnectionFailed() {
            super.onConnectionFailed();
            if (BluetoothManager.this.mTryConnectMediaSessionCnt <= 3) {
                BluetoothManager.this.connectMediaBrowser();
            }
            LogUtil.d(BluetoothManager.this.TAG, "onConnectionFailed");
        }

        @Override // android.media.browse.MediaBrowser.ConnectionCallback
        public void onConnectionSuspended() {
            super.onConnectionSuspended();
            BluetoothManager.this.mTryConnectMediaSessionCnt = 0;
            LogUtil.d(BluetoothManager.this.TAG, "onConnectionSuspended");
        }
    };

    public void destroy() {
        disConnectMediaBrowser();
        Utils.getApp().unregisterReceiver(this.blueToothReceiver);
    }

    public void init(Activity activity, IBluetoothListener iBluetoothListener) {
        this.iBluetoothListener = iBluetoothListener;
        this.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
        intentFilter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        this.blueToothReceiver = new BlueToothReceiver(this);
        Utils.getApp().registerReceiver(this.blueToothReceiver, intentFilter);
        BluetoothAdapter bluetoothAdapter = this.bluetoothAdapter;
        if (bluetoothAdapter == null) {
            iBluetoothListener.onNotluetoothA2dp();
            LogUtil.d(this.TAG, "未找到蓝牙驱动");
            return;
        }
        if (!bluetoothAdapter.isEnabled()) {
            activity.startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), 1);
            LogUtil.d(this.TAG, "蓝牙未打开");
            iBluetoothListener.onNotluetoothA2dp();
        } else {
            connectMediaBrowser();
        }
        boolean profileProxy = this.bluetoothAdapter.getProfileProxy(Utils.getApp(), this.mListener, 2);
        LogUtil.d(this.TAG, "IS:" + profileProxy);
        BluetoothA2dp bluetoothA2dp2 = bluetoothA2dp;
        if (bluetoothA2dp2 != null) {
            BluetoothUtil.connectA2dp(bluetoothA2dp2, this.bluetoothAdapter);
        }
    }

    @Override // com.byd.music.fragment.bluetooth.ReceiverListener
    public void disConnectMediaBrowser() {
        MediaBrowser mediaBrowser = this.mMediaBrowser;
        if (mediaBrowser != null) {
            mediaBrowser.disconnect();
            this.mMediaBrowser = null;
        }
        this.iBluetoothListener.onDisConnected();
    }

    @Override // com.byd.music.fragment.bluetooth.ReceiverListener
    public void connectMediaBrowser() {
        MediaBrowser mediaBrowser = this.mMediaBrowser;
        if (mediaBrowser != null) {
            if (mediaBrowser.isConnected()) {
                return;
            }
            this.iBluetoothListener.onNotluetoothA2dp();
        } else {
            MediaBrowser mediaBrowser2 = new MediaBrowser(Utils.getApp(), new ComponentName(SmsApplication.BLUETOOTH_PACKAGE_NAME, "com.android.bluetooth.avrcpcontroller.BluetoothMediaBrowserService"), this.mConnectionCallback, null);
            this.mMediaBrowser = mediaBrowser2;
            mediaBrowser2.connect();
            this.mTryConnectMediaSessionCnt++;
        }
    }

    public boolean isBlueToothConnect() {
        return BluetoothUtil.isBlueToothConnect(this.mMediaBrowser);
    }

    public void btMusicPlayContorl(int i) {
        BluetoothUtil.btMusicPlayContorlCmd(i, this.mMediaController);
    }
}
