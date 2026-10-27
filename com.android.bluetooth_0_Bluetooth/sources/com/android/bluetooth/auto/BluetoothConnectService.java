package com.android.bluetooth.auto;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.android.bluetooth.a2dpsink.A2dpSinkService;
import com.android.bluetooth.hfpclient.HeadsetClientService;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothConnectService extends Service {
    private static final int FIRST_INTERVAL = 3000;
    private static final int INTERVAL = 15000;
    private static final String TAG = "BluetoothConnectService";
    private static final int TOTAL = 600000;
    private A2dpSinkService mA2dpSinkService;
    private BluetoothAdapter mAdapter;
    private HeadsetClientService mHeadsetClientService;
    private int mPassedTime;
    private BluetoothDevice mRecentDevice;
    private Timer timer;

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mA2dpSinkService = A2dpSinkService.getA2dpSinkService();
        this.mHeadsetClientService = HeadsetClientService.getHeadsetClientService();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        isOpenTimer(false);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) {
            Timer timer = this.timer;
            if (timer != null) {
                timer.cancel();
                this.timer = null;
            }
        } else {
            BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            this.mRecentDevice = bluetoothDevice;
            if (bluetoothDevice == null) {
                Timer timer2 = this.timer;
                if (timer2 != null) {
                    timer2.cancel();
                    this.timer = null;
                }
            } else {
                isOpenTimer(true);
            }
        }
        return 1;
    }

    private void isOpenTimer(boolean isOpenWindow) {
        if (isOpenWindow) {
            if (this.timer == null) {
                Timer timer = new Timer();
                this.timer = timer;
                timer.scheduleAtFixedRate(new RefreshTask(), 3000L, 15000L);
                return;
            }
            return;
        }
        Timer timer2 = this.timer;
        if (timer2 != null) {
            timer2.cancel();
            this.timer = null;
        }
    }

    private class RefreshTask extends TimerTask {
        private RefreshTask() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            BluetoothConnectService.this.autoConnectDevice();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void autoConnectDevice() {
        int i = this.mPassedTime;
        if (i >= TOTAL) {
            Log.d(TAG, "autoConnect timeout,cancel autoConnect");
            stopSelf();
            this.mPassedTime = 0;
            return;
        }
        this.mPassedTime = i + INTERVAL;
        if (this.mAdapter == null) {
            this.mAdapter = BluetoothAdapter.getDefaultAdapter();
        }
        if (this.mAdapter.getState() != 12) {
            Log.d(TAG, "Bluetooth is OFF,cancel autoConnect");
            stopSelf();
            return;
        }
        if (this.mRecentDevice == null) {
            Log.d(TAG, "mRecentDevice is null,cancel autoConnect");
            stopSelf();
            return;
        }
        Set<BluetoothDevice> bondedDevices = this.mAdapter.getBondedDevices();
        if (bondedDevices.isEmpty()) {
            Log.d(TAG, "bondedDevices is null,cancel autoConnect");
            stopSelf();
            return;
        }
        int hfpConnectState = this.mAdapter.getProfileConnectionState(16);
        int a2dpConnectState = this.mAdapter.getProfileConnectionState(11);
        Log.d(TAG, "hfpConnectState:" + hfpConnectState + " a2dpConnectState:" + a2dpConnectState);
        if (hfpConnectState == 2 && a2dpConnectState == 2) {
            Log.d(TAG, "hfp&a2dp has connected,cancel autoConnect");
            stopSelf();
            return;
        }
        if (hfpConnectState != 2) {
            if (this.mHeadsetClientService == null) {
                this.mHeadsetClientService = HeadsetClientService.getHeadsetClientService();
            }
            if (this.mHeadsetClientService != null) {
                Log.d(TAG, "start connect hfp");
                this.mHeadsetClientService.connect(this.mRecentDevice);
            }
        }
        if (a2dpConnectState != 2) {
            if (this.mA2dpSinkService == null) {
                this.mA2dpSinkService = A2dpSinkService.getA2dpSinkService();
            }
            if (this.mA2dpSinkService != null) {
                Log.d(TAG, "start connect a2dp");
                this.mA2dpSinkService.connect(this.mRecentDevice);
            }
        }
    }
}
