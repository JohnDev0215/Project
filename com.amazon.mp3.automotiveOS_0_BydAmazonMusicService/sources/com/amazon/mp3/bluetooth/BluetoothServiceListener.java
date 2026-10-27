package com.amazon.mp3.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import androidx.core.content.ContextCompat;
import com.amazon.mp3.util.Log;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class BluetoothServiceListener {
    private static final String TAG = "BluetoothServiceListener";
    private static Map<String, BluetoothDevice> sConnectedBluetoothDevices = new HashMap();
    private static final int[] sConnectionStates = {1, 2};
    private static final IntentFilter sReceiverFilter;
    private final BluetoothConnectionCallback mBluetoothConnectionCallback;
    private Context mContext;
    private boolean mListening;
    private final BroadcastReceiver mBluetoothBroadcastReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.bluetooth.BluetoothServiceListener.1
        @Override // android.content.BroadcastReceiver
        public synchronized void onReceive(final Context context, final Intent intent) {
            BluetoothServiceListener.this.onBroadcastReceived(intent);
        }
    };
    private final BluetoothProfile.ServiceListener mBluetoothServiceListener = new BluetoothProfile.ServiceListener() { // from class: com.amazon.mp3.bluetooth.BluetoothServiceListener.2
        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(final int profile, final BluetoothProfile bluetoothProfile) {
            Log.info(BluetoothServiceListener.TAG, "Connected Bluetooth profile: " + profile + " ThreadId: " + BluetoothServiceListener.this.getCurrentThreadId());
            if (ContextCompat.checkSelfPermission(BluetoothServiceListener.this.mContext, "android.permission.BLUETOOTH_CONNECT") == 0 && Build.VERSION.SDK_INT >= 31) {
                BluetoothServiceListener.this.addDevices(bluetoothProfile.getDevicesMatchingConnectionStates(BluetoothServiceListener.sConnectionStates));
            } else if (Build.VERSION.SDK_INT < 31) {
                BluetoothServiceListener.this.addDevices(bluetoothProfile.getDevicesMatchingConnectionStates(BluetoothServiceListener.sConnectionStates));
            }
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(final int profile) {
            Log.info(BluetoothServiceListener.TAG, "Disconnected Bluetooth profile: " + profile + " ThreadId: " + BluetoothServiceListener.this.getCurrentThreadId());
            BluetoothServiceListener.this.resetDevices();
        }
    };

    private String getProfileEventName(final int bluetoothProfile) {
        return bluetoothProfile == 2 ? "bluetoothA2dp" : bluetoothProfile == 1 ? "bluetoothHeadset" : "bluetoothUnknown";
    }

    static {
        IntentFilter intentFilter = new IntentFilter();
        sReceiverFilter = intentFilter;
        intentFilter.addAction("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.a2dp.profile.action.PLAYING_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED");
    }

    public BluetoothServiceListener(final BluetoothConnectionCallback bluetoothConnectionCallback) {
        this.mBluetoothConnectionCallback = bluetoothConnectionCallback;
    }

    public void init(final Context context) {
        this.mContext = context;
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null) {
            Log.error(TAG, "BluetoothAdapter is null. This device likely does not support bluetooth.");
            return;
        }
        if (!defaultAdapter.getProfileProxy(this.mContext, this.mBluetoothServiceListener, 2)) {
            Log.error(TAG, "Unable to getProfileProxy for A2DP Profile");
        }
        if (defaultAdapter.getProfileProxy(this.mContext, this.mBluetoothServiceListener, 1)) {
            return;
        }
        Log.error(TAG, "Unable to getProfileProxy for Headset Profile");
    }

    public void startListening() {
        if (this.mListening) {
            return;
        }
        Context context = this.mContext;
        if (context == null) {
            Log.error(TAG, "startListening called before init!");
        } else {
            this.mListening = true;
            context.registerReceiver(this.mBluetoothBroadcastReceiver, sReceiverFilter);
        }
    }

    public void stopListening() {
        if (this.mListening) {
            this.mListening = false;
            this.mContext.unregisterReceiver(this.mBluetoothBroadcastReceiver);
        }
    }

    public static List<BluetoothDevice> getConnectedDevices() {
        return new ArrayList(sConnectedBluetoothDevices.values());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onBroadcastReceived(final Intent intent) {
        String action;
        action = intent.getAction();
        action.hashCode();
        switch (action) {
            case "android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED":
            case "android.bluetooth.a2dp.profile.action.PLAYING_STATE_CHANGED":
                handlePlayStateChanged(intent);
                break;
            case "android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED":
                handleConnectionStateChanged(1, intent);
                break;
            case "android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED":
                handleConnectionStateChanged(2, intent);
                break;
            default:
                Log.warning(TAG, "Unknown action type received: " + action);
                break;
        }
    }

    private void handleConnectionStateChanged(final int profile, final Intent intent) {
        BluetoothConnectionCallback bluetoothConnectionCallback;
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        String deviceName = getDeviceName(bluetoothDevice);
        int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", 0);
        int intExtra2 = intent.getIntExtra("android.bluetooth.profile.extra.STATE", 0);
        if (intExtra2 == 0 && intExtra == 2) {
            Log.info(TAG, "Bluetooth device has disconnected: %s - %d", deviceName, Integer.valueOf(profile));
            if (profile == 2 && (bluetoothConnectionCallback = this.mBluetoothConnectionCallback) != null) {
                bluetoothConnectionCallback.onDisconnect();
            }
            handleDisconnect(profile, Collections.singletonList(bluetoothDevice));
            return;
        }
        if (intExtra2 == 2 && intExtra == 0) {
            Log.info(TAG, "Bluetooth device has connected: %s - %d", deviceName, Integer.valueOf(profile));
            BluetoothConnectionCallback bluetoothConnectionCallback2 = this.mBluetoothConnectionCallback;
            if (bluetoothConnectionCallback2 != null) {
                bluetoothConnectionCallback2.onConnect();
            }
            handleConnect(profile, Collections.singletonList(bluetoothDevice));
        }
    }

    private void handlePlayStateChanged(final Intent intent) {
        String deviceName = getDeviceName((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE"));
        int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", 11);
        int intExtra2 = intent.getIntExtra("android.bluetooth.profile.extra.STATE", 11);
        int intExtra3 = intent.getIntExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", 10);
        int intExtra4 = intent.getIntExtra("android.bluetooth.profile.extra.STATE", 10);
        if (intExtra2 == 11 && intExtra == 10) {
            Log.info(TAG, "Bluetooth A2DP device has stopped playing: " + deviceName);
        }
        if (intExtra2 == 10 && intExtra == 11) {
            Log.info(TAG, "Bluetooth A2DP device has started playing: " + deviceName);
        }
        if (intExtra4 == 10 && intExtra3 == 12) {
            Log.info(TAG, "Bluetooth Headset device audio disconnected: " + deviceName);
        }
        if (intExtra4 == 12 && intExtra3 == 10) {
            Log.info(TAG, "Bluetooth Headset device has audio connected: " + deviceName);
        }
    }

    private void handleConnect(final int bluetoothProfile, final List<BluetoothDevice> connectedDevices) {
        addDevices(connectedDevices);
        emitBluetoothMetrics(getProfileEventName(bluetoothProfile), "connected", getDeviceNameList(connectedDevices));
    }

    private void handleDisconnect(final int bluetoothProfile, final List<BluetoothDevice> deviceList) {
        removeDevices(deviceList);
        emitBluetoothMetrics(getProfileEventName(bluetoothProfile), "disconnected", "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addDevices(final List<BluetoothDevice> deviceList) {
        if (deviceList == null) {
            return;
        }
        for (BluetoothDevice bluetoothDevice : deviceList) {
            if (!sConnectedBluetoothDevices.containsKey(bluetoothDevice.getAddress())) {
                sConnectedBluetoothDevices.put(bluetoothDevice.getAddress(), bluetoothDevice);
            }
        }
    }

    private void removeDevices(final List<BluetoothDevice> deviceList) {
        if (deviceList == null) {
            return;
        }
        for (BluetoothDevice bluetoothDevice : deviceList) {
            if (sConnectedBluetoothDevices.containsKey(bluetoothDevice.getAddress())) {
                sConnectedBluetoothDevices.remove(bluetoothDevice.getAddress());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetDevices() {
        sConnectedBluetoothDevices.clear();
    }

    private void emitBluetoothMetrics(final String bluetoothProfile, final String connectionState, final String deviceNames) {
        MetricsHolder.getManager().handleEvent(FlexEvent.builder(bluetoothProfile).withFlexStr1(connectionState).withFlexStr2(deviceNames).build());
    }

    private String getDeviceName(final BluetoothDevice device) {
        return device != null ? device.getName() : "UnknownDevice";
    }

    public static String getDeviceNameList(final List<BluetoothDevice> connectedDevices) {
        Iterator<BluetoothDevice> it = connectedDevices.iterator();
        String str = "";
        while (it.hasNext()) {
            str = str + it.next().getName() + " - ";
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getCurrentThreadId() {
        if (Thread.currentThread() == null) {
            return 0L;
        }
        return Thread.currentThread().getId();
    }
}
