package com.android.car;

import android.bluetooth.BluetoothAdapter;
import android.car.encryptionrunner.DummyEncryptionRunner;
import android.car.hardware.power.CarPowerManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import java.io.PrintWriter;
import java.util.concurrent.CompletableFuture;

/* JADX INFO: loaded from: classes3.dex */
public class CarBluetoothPowerManager implements CarPowerManager.CarPowerStateListenerWithCompletion {
    private static final boolean DBG = true;
    private static final String TAG = "CAR.BT.POWER";
    private static CarBluetoothPowerManager sBluetoothPowerManager = null;
    private BluetoothAdapter mBluetoothAdapter;
    private Context mContext;
    private CompletableFuture<Void> mFuture;
    private IntentFilter mBluetoothIntentFilter = null;
    private CarPowerManager mCarPowerManager = null;
    private boolean mIsSuspend = false;
    private final Object mLock = new Object();
    private HandlerThread mThread = null;
    private BluetoothHandler mHandler = null;
    private int mState = 10;
    private BroadcastReceiver mBluetoothReceiver = new BroadcastReceiver() { // from class: com.android.car.CarBluetoothPowerManager.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(intent.getAction()) || "android.bluetooth.adapter.action.BLE_STATE_CHANGED".equals(intent.getAction())) {
                int newState = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
                int prevState = intent.getIntExtra("android.bluetooth.adapter.extra.PREVIOUS_STATE", Integer.MIN_VALUE);
                CarBluetoothPowerManager.this.logd("Bluetooth newState: " + newState + " (" + BluetoothAdapter.nameForState(newState) + "), prevState: " + prevState + " (" + BluetoothAdapter.nameForState(prevState) + ")");
                CarBluetoothPowerManager.this.mState = newState;
                if (CarBluetoothPowerManager.this.mHandler != null) {
                    CarBluetoothPowerManager.this.mHandler.notifyStateChanged(newState, prevState);
                }
            }
        }
    };

    public static CarBluetoothPowerManager createInstance(Context context) {
        if (sBluetoothPowerManager == null) {
            sBluetoothPowerManager = new CarBluetoothPowerManager(context);
        }
        return sBluetoothPowerManager;
    }

    public CarBluetoothPowerManager(Context context) {
        this.mContext = context;
        init();
    }

    private void init() {
        logd(DummyEncryptionRunner.INIT);
        this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.mThread = handlerThread;
        handlerThread.start();
        this.mHandler = new BluetoothHandler(this.mThread.getLooper());
        IntentFilter intentFilter = new IntentFilter();
        this.mBluetoothIntentFilter = intentFilter;
        intentFilter.addAction("android.bluetooth.adapter.action.BLE_STATE_CHANGED");
        this.mContext.registerReceiver(this.mBluetoothReceiver, this.mBluetoothIntentFilter);
        CarPowerManager carPowerManagerCreateCarPowerManager = CarLocalServices.createCarPowerManager(this.mContext);
        this.mCarPowerManager = carPowerManagerCreateCarPowerManager;
        if (carPowerManagerCreateCarPowerManager != null) {
            logd("listen car power state");
            this.mCarPowerManager.setListenerWithCompletion(this);
        } else {
            loge("can't get CarPowerManager");
        }
    }

    private synchronized void release() {
        logd("release ");
        if (this.mCarPowerManager != null) {
            this.mCarPowerManager.clearListener();
            this.mCarPowerManager = null;
        }
        this.mContext.unregisterReceiver(this.mBluetoothReceiver);
    }

    private int getBluetoothState() {
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        if (bluetoothAdapter != null) {
            return bluetoothAdapter.getState();
        }
        return Integer.MIN_VALUE;
    }

    private boolean isBluetoothPersistedOn() {
        return Settings.Global.getInt(this.mContext.getContentResolver(), "bluetooth_on", -1) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBluetoothPersistedOn() {
        Settings.Global.putInt(this.mContext.getContentResolver(), "bluetooth_on", 1);
    }

    private boolean enableBluetooth() {
        logd("enable Bluetooth");
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        if (bluetoothAdapter != null) {
            return bluetoothAdapter.enable();
        }
        loge("can't enable Bluetooth due to null BluetoothAdapter");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean disableBluetooth() {
        logd("disable Bluetooth");
        BluetoothAdapter bluetoothAdapter = this.mBluetoothAdapter;
        if (bluetoothAdapter != null) {
            return bluetoothAdapter.disable();
        }
        loge("can't disable Bluetooth due to null BluetoothAdapter");
        return false;
    }

    public void onStateChanged(int state, CompletableFuture<Void> future) {
        logd("onStateChanged: power state " + state + " (" + mapCarPowerState2String(state) + ")");
        if (state == 2) {
            handleSuspendEnter(future);
            return;
        }
        if (state != 3) {
            if (state == 7) {
                handleSuspendPrepare(future);
                return;
            } else if (state != 8) {
                completeFuture(future);
                return;
            }
        }
        handleSuspendExit(future);
    }

    private void handleSuspendPrepare(CompletableFuture<Void> future) {
        logd("handleSuspendPrepare");
        int state = getBluetoothState();
        logd("bluetooth state: " + state + " (" + BluetoothAdapter.nameForState(state) + ")");
        if (isBluetoothOff(state) && isBluetoothOff(this.mState)) {
            completeFuture(future);
            return;
        }
        if (this.mHandler != null) {
            synchronized (this.mLock) {
                this.mIsSuspend = true;
                this.mFuture = future;
            }
            logd("mFuture: " + this.mFuture);
            this.mHandler.notifyOffReq();
            return;
        }
        completeFuture(future);
    }

    private void handleSuspendEnter(CompletableFuture<Void> future) {
        logd("handleSuspendEnter");
        completeFuture(future);
    }

    private void handleSuspendExit(CompletableFuture<Void> future) {
        logd("handleSuspendExit");
        synchronized (this.mLock) {
            this.mIsSuspend = false;
        }
        if (isBluetoothPersistedOn()) {
            enableBluetooth();
        }
        completeFuture(future);
    }

    private void completeFuture(CompletableFuture<Void> future) {
        if (future != null) {
            future.complete(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void completeFuture() {
        synchronized (this.mLock) {
            if (this.mFuture != null) {
                logd("completeFuture: " + this.mFuture);
                this.mFuture.complete(null);
                this.mFuture = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isBluetoothOff(int state) {
        return state == 10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isBluetoothOn(int state) {
        return state == 12;
    }

    private String mapCarPowerState2String(int state) {
        switch (state) {
            case 0:
                return "INVALID";
            case 1:
                return "WAIT_FOR_VHAL";
            case 2:
                return "SUSPEND_ENTER";
            case 3:
                return "SUSPEND_EXIT";
            case 4:
            default:
                return "UNNKNOWN";
            case 5:
                return "SHUTDOWN_ENTER";
            case 6:
                return "ON";
            case 7:
                return "SHUTDOWN_PREPARE";
            case 8:
                return "SHUTDOWN_CANCELLED";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logd(String msg) {
        Log.d(TAG, msg);
    }

    private void logw(String msg) {
        Log.w(TAG, msg);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loge(String msg) {
        Log.e(TAG, msg);
    }

    private class BluetoothHandler extends Handler {
        private static final boolean DBG = true;
        private static final int MAX_OFF_TIMEOUT = 5000;
        private static final int MSG_OFF_REQ = 100;
        private static final int MSG_STATE_CHANGED = 200;
        private static final int MSG_TIMEOUT = 201;
        private static final String TAG = "BluetoothHandler";

        public BluetoothHandler(Looper looper) {
            super(looper);
        }

        public void notifyOffReq() {
            sendEmptyMessage(100);
        }

        public void notifyStateChanged(int newState, int prevState) {
            Message msg = obtainMessage(200, newState, prevState);
            sendMessage(msg);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            int i = msg.what;
            if (i == 100) {
                handleOffReq();
            } else if (i == 200) {
                handleStateChanged(msg.arg1, msg.arg2);
            } else if (i == MSG_TIMEOUT) {
                handleTimeout();
            }
        }

        private void handleOffReq() {
            CarBluetoothPowerManager.this.logd("handleOffReq");
            if (!CarBluetoothPowerManager.this.disableBluetooth()) {
                CarBluetoothPowerManager.this.completeFuture();
            } else {
                sendEmptyMessageDelayed(MSG_TIMEOUT, 5000L);
            }
        }

        private void handleStateChanged(int newState, int prevState) {
            if (CarBluetoothPowerManager.this.isBluetoothOff(newState)) {
                CarBluetoothPowerManager.this.logd("handleStateChanged: Bluetooth off");
                if (prevState == 16 && CarBluetoothPowerManager.this.mIsSuspend) {
                    removeMessages(MSG_TIMEOUT);
                    CarBluetoothPowerManager.this.setBluetoothPersistedOn();
                    CarBluetoothPowerManager.this.completeFuture();
                    return;
                }
                return;
            }
            if (CarBluetoothPowerManager.this.isBluetoothOn(newState)) {
                CarBluetoothPowerManager.this.logd("handleStateChanged: Bluetooth on");
            }
        }

        private void handleTimeout() {
            CarBluetoothPowerManager.this.loge("handleTimeout");
            CarBluetoothPowerManager.this.completeFuture();
        }

        public void dump(PrintWriter writer) {
            writer.println(TAG + toString());
        }
    }
}
