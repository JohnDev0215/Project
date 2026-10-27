package com.android.car;

import android.bluetooth.BluetoothAdapter;
import android.car.hardware.power.CarPowerManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import java.io.PrintWriter;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class BluetoothDeviceConnectionPolicy {
    private static final String ACTION_BLUETOOTH_CONNECT = "com.android.car.bluetooth.action.BLUETOOTH_ISCONNECT";
    private static final String AUTO_CONNECT_PERSIST = "persist.bluetooth.autoconnect";
    private static final boolean DBG = true;
    private static final int MESSAGE_DELAY_TIME = 30000;
    private static final int MESSAGE_WHAT = 1000;
    private static final String TAG = "BluetoothDeviceConnectionPolicy";
    private final BluetoothAdapter mBluetoothAdapter;
    private final BluetoothBroadcastReceiver mBluetoothBroadcastReceiver;
    private final BluetoothConnectBroadcastReceiver mBluetoothConnectBroadcastReceiver;
    private final CarBluetoothService mCarBluetoothService;
    private final Context mContext;
    private final Handler mHandler = new Handler(CarServiceUtils.getHandlerThread("CarBluetoothService").getLooper()) { // from class: com.android.car.BluetoothDeviceConnectionPolicy.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            BluetoothDeviceConnectionPolicy.logd("don`t Connect devices,remove Message");
            removeMessages(1000);
        }
    };
    private final int mUserId;

    public CarPowerManager.CarPowerStateListenerWithCompletion getCarPowerStateListener() {
        return null;
    }

    private class BluetoothBroadcastReceiver extends BroadcastReceiver {
        private BluetoothBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(action)) {
                int state = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1);
                BluetoothDeviceConnectionPolicy.logd("Bluetooth Adapter state changed: " + Utils.getAdapterStateName(state));
                if (state == 12) {
                    BluetoothDeviceConnectionPolicy.this.connectDevices();
                }
            }
        }
    }

    private class BluetoothConnectBroadcastReceiver extends BroadcastReceiver {
        private BluetoothConnectBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDeviceConnectionPolicy.ACTION_BLUETOOTH_CONNECT.equals(action)) {
                int state = intent.getIntExtra(NotificationCompat.CATEGORY_STATUS, -1);
                int isCpConnectState = intent.getIntExtra("connect_status", -1);
                boolean hasMessage = BluetoothDeviceConnectionPolicy.this.mHandler.hasMessages(1000);
                BluetoothDeviceConnectionPolicy.logd("Carplay notify Bluetooth, state: " + state + " isCpConnectState: " + isCpConnectState + "  hasMessage: " + hasMessage);
                if (state == 1 && isCpConnectState == 0 && hasMessage) {
                    BluetoothDeviceConnectionPolicy.this.mHandler.removeMessages(1000);
                    BluetoothDeviceConnectionPolicy.this.connectDevices();
                }
            }
        }
    }

    public static BluetoothDeviceConnectionPolicy create(Context context, int userId, CarBluetoothService bluetoothService) {
        try {
            return new BluetoothDeviceConnectionPolicy(context, userId, bluetoothService);
        } catch (NullPointerException e) {
            return null;
        }
    }

    private BluetoothDeviceConnectionPolicy(Context context, int userId, CarBluetoothService bluetoothService) {
        this.mUserId = userId;
        Objects.requireNonNull(context);
        this.mContext = context;
        this.mCarBluetoothService = bluetoothService;
        this.mBluetoothBroadcastReceiver = new BluetoothBroadcastReceiver();
        this.mBluetoothConnectBroadcastReceiver = new BluetoothConnectBroadcastReceiver();
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        Objects.requireNonNull(defaultAdapter);
        this.mBluetoothAdapter = defaultAdapter;
        CarBluetoothPowerManager.createInstance(context);
    }

    public void init() {
        logd("init()");
        IntentFilter profileFilter = new IntentFilter();
        profileFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        this.mContext.registerReceiverAsUser(this.mBluetoothBroadcastReceiver, UserHandle.CURRENT, profileFilter, null, null);
        IntentFilter notifyConnectFilter = new IntentFilter();
        notifyConnectFilter.addAction(ACTION_BLUETOOTH_CONNECT);
        this.mContext.registerReceiver(this.mBluetoothConnectBroadcastReceiver, notifyConnectFilter);
        if (this.mBluetoothAdapter.getState() == 12) {
            connectDevices();
        }
    }

    public void release() {
        logd("release()");
        BluetoothBroadcastReceiver bluetoothBroadcastReceiver = this.mBluetoothBroadcastReceiver;
        if (bluetoothBroadcastReceiver != null) {
            this.mContext.unregisterReceiver(bluetoothBroadcastReceiver);
        }
        BluetoothConnectBroadcastReceiver bluetoothConnectBroadcastReceiver = this.mBluetoothConnectBroadcastReceiver;
        if (bluetoothConnectBroadcastReceiver != null) {
            this.mContext.unregisterReceiver(bluetoothConnectBroadcastReceiver);
        }
    }

    public void connectDevices() {
        String state = SystemProperties.get(AUTO_CONNECT_PERSIST, "1");
        logd("Connect devices for each profile from carplay state: " + state);
        if (state.equals("1")) {
            this.mCarBluetoothService.connectDevices();
        } else {
            logd("don`t Connect devices,sendEmptyMessageDelayed");
            this.mHandler.sendEmptyMessageDelayed(1000, 30000L);
        }
    }

    public void dump(PrintWriter writer, String indent) {
        writer.println(indent + TAG + ":");
        writer.println(indent + "\tUserId: " + this.mUserId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void logd(String msg) {
        Log.i(TAG, msg);
    }
}
