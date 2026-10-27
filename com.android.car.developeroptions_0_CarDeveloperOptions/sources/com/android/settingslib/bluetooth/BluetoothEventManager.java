package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.UserHandle;
import android.util.Log;
import com.android.car.developeroptions.intelligence.LogProto$SettingsLog;
import com.android.settingslib.R$string;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothEventManager {
    private final BroadcastReceiver mBroadcastReceiver;
    private final Context mContext;
    private final CachedBluetoothDeviceManager mDeviceManager;
    private final LocalBluetoothAdapter mLocalAdapter;
    private final BroadcastReceiver mProfileBroadcastReceiver;
    private final android.os.Handler mReceiverHandler;
    private final UserHandle mUserHandle;
    private final Collection<BluetoothCallback> mCallbacks = new CopyOnWriteArrayList();
    private final IntentFilter mAdapterIntentFilter = new IntentFilter();
    private final IntentFilter mProfileIntentFilter = new IntentFilter();
    private final Map<String, Handler> mHandlerMap = new HashMap();

    interface Handler {
        void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice);
    }

    BluetoothEventManager(LocalBluetoothAdapter localBluetoothAdapter, CachedBluetoothDeviceManager cachedBluetoothDeviceManager, Context context, android.os.Handler handler, UserHandle userHandle) {
        this.mBroadcastReceiver = new BluetoothBroadcastReceiver();
        this.mProfileBroadcastReceiver = new BluetoothBroadcastReceiver();
        this.mLocalAdapter = localBluetoothAdapter;
        this.mDeviceManager = cachedBluetoothDeviceManager;
        this.mContext = context;
        this.mUserHandle = userHandle;
        this.mReceiverHandler = handler;
        addHandler("android.bluetooth.adapter.action.STATE_CHANGED", new AdapterStateChangedHandler());
        addHandler("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED", new ConnectionStateChangedHandler());
        addHandler("android.bluetooth.adapter.action.DISCOVERY_STARTED", new ScanningStateChangedHandler(true));
        addHandler("android.bluetooth.adapter.action.DISCOVERY_FINISHED", new ScanningStateChangedHandler(false));
        addHandler("android.bluetooth.device.action.FOUND", new DeviceFoundHandler());
        addHandler("android.bluetooth.device.action.NAME_CHANGED", new NameChangedHandler());
        addHandler("android.bluetooth.device.action.ALIAS_CHANGED", new NameChangedHandler());
        addHandler("android.bluetooth.device.action.BOND_STATE_CHANGED", new BondStateChangedHandler());
        addHandler("android.bluetooth.device.action.CLASS_CHANGED", new ClassChangedHandler());
        addHandler("android.bluetooth.device.action.UUID", new UuidChangedHandler());
        addHandler("android.bluetooth.device.action.BATTERY_LEVEL_CHANGED", new BatteryLevelChangedHandler());
        addHandler("android.bluetooth.a2dp.profile.action.ACTIVE_DEVICE_CHANGED", new ActiveDeviceChangedHandler());
        addHandler("android.bluetooth.headset.profile.action.ACTIVE_DEVICE_CHANGED", new ActiveDeviceChangedHandler());
        addHandler("android.bluetooth.hearingaid.profile.action.ACTIVE_DEVICE_CHANGED", new ActiveDeviceChangedHandler());
        addHandler("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED", new AudioModeChangedHandler());
        addHandler("android.intent.action.PHONE_STATE", new AudioModeChangedHandler());
        addHandler("android.bluetooth.device.action.ACL_CONNECTED", new AclStateChangedHandler());
        addHandler("android.bluetooth.device.action.ACL_DISCONNECTED", new AclStateChangedHandler());
        registerAdapterIntentReceiver();
    }

    public void registerCallback(BluetoothCallback bluetoothCallback) {
        this.mCallbacks.add(bluetoothCallback);
    }

    public void unregisterCallback(BluetoothCallback bluetoothCallback) {
        this.mCallbacks.remove(bluetoothCallback);
    }

    void registerProfileIntentReceiver() {
        registerIntentReceiver(this.mProfileBroadcastReceiver, this.mProfileIntentFilter);
    }

    void registerAdapterIntentReceiver() {
        registerIntentReceiver(this.mBroadcastReceiver, this.mAdapterIntentFilter);
    }

    private void registerIntentReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        UserHandle userHandle = this.mUserHandle;
        if (userHandle == null) {
            this.mContext.registerReceiver(broadcastReceiver, intentFilter, null, this.mReceiverHandler);
        } else {
            this.mContext.registerReceiverAsUser(broadcastReceiver, userHandle, intentFilter, null, this.mReceiverHandler);
        }
    }

    void addProfileHandler(String str, Handler handler) {
        this.mHandlerMap.put(str, handler);
        this.mProfileIntentFilter.addAction(str);
    }

    boolean readPairedDevices() {
        Set<BluetoothDevice> bondedDevices = this.mLocalAdapter.getBondedDevices();
        boolean z = false;
        if (bondedDevices == null) {
            return false;
        }
        for (BluetoothDevice bluetoothDevice : bondedDevices) {
            if (this.mDeviceManager.findDevice(bluetoothDevice) == null) {
                this.mDeviceManager.addDevice(bluetoothDevice);
                z = true;
            }
        }
        return z;
    }

    void dispatchDeviceAdded(CachedBluetoothDevice cachedBluetoothDevice) {
        Iterator<BluetoothCallback> it = this.mCallbacks.iterator();
        while (it.hasNext()) {
            it.next().onDeviceAdded(cachedBluetoothDevice);
        }
    }

    void dispatchDeviceRemoved(CachedBluetoothDevice cachedBluetoothDevice) {
        Iterator<BluetoothCallback> it = this.mCallbacks.iterator();
        while (it.hasNext()) {
            it.next().onDeviceDeleted(cachedBluetoothDevice);
        }
    }

    void dispatchProfileConnectionStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i, int i2) {
        Iterator<BluetoothCallback> it = this.mCallbacks.iterator();
        while (it.hasNext()) {
            it.next().onProfileConnectionStateChanged(cachedBluetoothDevice, i, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchConnectionStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i) {
        Iterator<BluetoothCallback> it = this.mCallbacks.iterator();
        while (it.hasNext()) {
            it.next().onConnectionStateChanged(cachedBluetoothDevice, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchAudioModeChanged() {
        Iterator<CachedBluetoothDevice> it = this.mDeviceManager.getCachedDevicesCopy().iterator();
        while (it.hasNext()) {
            it.next().onAudioModeChanged();
        }
        Iterator<BluetoothCallback> it2 = this.mCallbacks.iterator();
        while (it2.hasNext()) {
            it2.next().onAudioModeChanged();
        }
    }

    void dispatchActiveDeviceChanged(CachedBluetoothDevice cachedBluetoothDevice, int i) {
        for (CachedBluetoothDevice cachedBluetoothDevice2 : this.mDeviceManager.getCachedDevicesCopy()) {
            cachedBluetoothDevice2.onActiveDeviceChanged(Objects.equals(cachedBluetoothDevice2, cachedBluetoothDevice), i);
        }
        Iterator<BluetoothCallback> it = this.mCallbacks.iterator();
        while (it.hasNext()) {
            it.next().onActiveDeviceChanged(cachedBluetoothDevice, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchAclStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i) {
        Iterator<BluetoothCallback> it = this.mCallbacks.iterator();
        while (it.hasNext()) {
            it.next().onAclConnectionStateChanged(cachedBluetoothDevice, i);
        }
    }

    void addHandler(String str, Handler handler) {
        this.mHandlerMap.put(str, handler);
        this.mAdapterIntentFilter.addAction(str);
    }

    private class BluetoothBroadcastReceiver extends BroadcastReceiver {
        private BluetoothBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            Handler handler = (Handler) BluetoothEventManager.this.mHandlerMap.get(action);
            if (handler != null) {
                handler.onReceive(context, intent, bluetoothDevice);
            }
        }
    }

    private class AdapterStateChangedHandler implements Handler {
        private AdapterStateChangedHandler() {
        }

        @Override // com.android.settingslib.bluetooth.BluetoothEventManager.Handler
        public void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice) {
            int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
            BluetoothEventManager.this.mLocalAdapter.setBluetoothStateInt(intExtra);
            Iterator it = BluetoothEventManager.this.mCallbacks.iterator();
            while (it.hasNext()) {
                ((BluetoothCallback) it.next()).onBluetoothStateChanged(intExtra);
            }
            BluetoothEventManager.this.mDeviceManager.onBluetoothStateChanged(intExtra);
        }
    }

    private class ScanningStateChangedHandler implements Handler {
        private final boolean mStarted;

        ScanningStateChangedHandler(boolean z) {
            this.mStarted = z;
        }

        @Override // com.android.settingslib.bluetooth.BluetoothEventManager.Handler
        public void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice) {
            Iterator it = BluetoothEventManager.this.mCallbacks.iterator();
            while (it.hasNext()) {
                ((BluetoothCallback) it.next()).onScanningStateChanged(this.mStarted);
            }
            BluetoothEventManager.this.mDeviceManager.onScanningStateChanged(this.mStarted);
        }
    }

    private class DeviceFoundHandler implements Handler {
        private DeviceFoundHandler() {
        }

        @Override // com.android.settingslib.bluetooth.BluetoothEventManager.Handler
        public void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice) {
            short shortExtra = intent.getShortExtra("android.bluetooth.device.extra.RSSI", Short.MIN_VALUE);
            intent.getStringExtra("android.bluetooth.device.extra.NAME");
            CachedBluetoothDevice cachedBluetoothDeviceFindDevice = BluetoothEventManager.this.mDeviceManager.findDevice(bluetoothDevice);
            if (cachedBluetoothDeviceFindDevice == null) {
                cachedBluetoothDeviceFindDevice = BluetoothEventManager.this.mDeviceManager.addDevice(bluetoothDevice);
                Log.d("BluetoothEventManager", "DeviceFoundHandler created new CachedBluetoothDevice: " + cachedBluetoothDeviceFindDevice);
            } else if (cachedBluetoothDeviceFindDevice.getBondState() == 12 && !cachedBluetoothDeviceFindDevice.getDevice().isConnected()) {
                BluetoothEventManager.this.dispatchDeviceAdded(cachedBluetoothDeviceFindDevice);
                Log.d("BluetoothEventManager", "DeviceFoundHandler found bonded and not connected device:" + cachedBluetoothDeviceFindDevice);
            } else {
                Log.d("BluetoothEventManager", "DeviceFoundHandler found existing CachedBluetoothDevice:" + cachedBluetoothDeviceFindDevice);
            }
            cachedBluetoothDeviceFindDevice.setRssi(shortExtra);
            cachedBluetoothDeviceFindDevice.setJustDiscovered(true);
        }
    }

    private class ConnectionStateChangedHandler implements Handler {
        private ConnectionStateChangedHandler() {
        }

        @Override // com.android.settingslib.bluetooth.BluetoothEventManager.Handler
        public void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice) {
            BluetoothEventManager.this.dispatchConnectionStateChanged(BluetoothEventManager.this.mDeviceManager.findDevice(bluetoothDevice), intent.getIntExtra("android.bluetooth.adapter.extra.CONNECTION_STATE", Integer.MIN_VALUE));
        }
    }

    private class NameChangedHandler implements Handler {
        private NameChangedHandler() {
        }

        @Override // com.android.settingslib.bluetooth.BluetoothEventManager.Handler
        public void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice) {
            BluetoothEventManager.this.mDeviceManager.onDeviceNameUpdated(bluetoothDevice);
        }
    }

    private class BondStateChangedHandler implements Handler {
        private BondStateChangedHandler() {
        }

        @Override // com.android.settingslib.bluetooth.BluetoothEventManager.Handler
        public void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice) {
            if (bluetoothDevice == null) {
                Log.e("BluetoothEventManager", "ACTION_BOND_STATE_CHANGED with no EXTRA_DEVICE");
                return;
            }
            int intExtra = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", Integer.MIN_VALUE);
            CachedBluetoothDevice cachedBluetoothDeviceFindDevice = BluetoothEventManager.this.mDeviceManager.findDevice(bluetoothDevice);
            if (cachedBluetoothDeviceFindDevice == null) {
                Log.w("BluetoothEventManager", "Got bonding state changed for " + bluetoothDevice + ", but we have no record of that device.");
                cachedBluetoothDeviceFindDevice = BluetoothEventManager.this.mDeviceManager.addDevice(bluetoothDevice);
            }
            Iterator it = BluetoothEventManager.this.mCallbacks.iterator();
            while (it.hasNext()) {
                ((BluetoothCallback) it.next()).onDeviceBondStateChanged(cachedBluetoothDeviceFindDevice, intExtra);
            }
            cachedBluetoothDeviceFindDevice.onBondingStateChanged(intExtra);
            if (intExtra == 10) {
                if (cachedBluetoothDeviceFindDevice.getHiSyncId() != 0) {
                    BluetoothEventManager.this.mDeviceManager.onDeviceUnpaired(cachedBluetoothDeviceFindDevice);
                }
                showUnbondMessage(context, cachedBluetoothDeviceFindDevice.getName(), intent.getIntExtra("android.bluetooth.device.extra.REASON", Integer.MIN_VALUE));
            }
        }

        private void showUnbondMessage(Context context, String str, int i) {
            int i2;
            switch (i) {
                case 1:
                    i2 = R$string.bluetooth_pairing_pin_error_message;
                    break;
                case 2:
                    i2 = R$string.bluetooth_pairing_rejected_error_message;
                    break;
                case 3:
                default:
                    Log.w("BluetoothEventManager", "showUnbondMessage: Not displaying any message for reason: " + i);
                    return;
                case 4:
                    i2 = R$string.bluetooth_pairing_device_down_error_message;
                    break;
                case 5:
                case LogProto$SettingsLog.TIMESTAMP_FIELD_NUMBER /* 6 */:
                case 7:
                case 8:
                    i2 = R$string.bluetooth_pairing_error_message;
                    break;
            }
            BluetoothUtils.showError(context, str, i2);
        }
    }

    private class ClassChangedHandler implements Handler {
        private ClassChangedHandler() {
        }

        @Override // com.android.settingslib.bluetooth.BluetoothEventManager.Handler
        public void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice) {
            CachedBluetoothDevice cachedBluetoothDeviceFindDevice = BluetoothEventManager.this.mDeviceManager.findDevice(bluetoothDevice);
            if (cachedBluetoothDeviceFindDevice != null) {
                cachedBluetoothDeviceFindDevice.refresh();
            }
        }
    }

    private class UuidChangedHandler implements Handler {
        private UuidChangedHandler() {
        }

        @Override // com.android.settingslib.bluetooth.BluetoothEventManager.Handler
        public void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice) {
            CachedBluetoothDevice cachedBluetoothDeviceFindDevice = BluetoothEventManager.this.mDeviceManager.findDevice(bluetoothDevice);
            if (cachedBluetoothDeviceFindDevice != null) {
                cachedBluetoothDeviceFindDevice.onUuidChanged();
            }
        }
    }

    private class BatteryLevelChangedHandler implements Handler {
        private BatteryLevelChangedHandler() {
        }

        @Override // com.android.settingslib.bluetooth.BluetoothEventManager.Handler
        public void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice) {
            CachedBluetoothDevice cachedBluetoothDeviceFindDevice = BluetoothEventManager.this.mDeviceManager.findDevice(bluetoothDevice);
            if (cachedBluetoothDeviceFindDevice != null) {
                cachedBluetoothDeviceFindDevice.refresh();
            }
        }
    }

    private class ActiveDeviceChangedHandler implements Handler {
        private ActiveDeviceChangedHandler() {
        }

        @Override // com.android.settingslib.bluetooth.BluetoothEventManager.Handler
        public void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice) {
            int i;
            String action = intent.getAction();
            if (action != null) {
                CachedBluetoothDevice cachedBluetoothDeviceFindDevice = BluetoothEventManager.this.mDeviceManager.findDevice(bluetoothDevice);
                if (Objects.equals(action, "android.bluetooth.a2dp.profile.action.ACTIVE_DEVICE_CHANGED")) {
                    i = 2;
                } else if (Objects.equals(action, "android.bluetooth.headset.profile.action.ACTIVE_DEVICE_CHANGED")) {
                    i = 1;
                } else {
                    if (!Objects.equals(action, "android.bluetooth.hearingaid.profile.action.ACTIVE_DEVICE_CHANGED")) {
                        Log.w("BluetoothEventManager", "ActiveDeviceChangedHandler: unknown action " + action);
                        return;
                    }
                    i = 21;
                }
                BluetoothEventManager.this.dispatchActiveDeviceChanged(cachedBluetoothDeviceFindDevice, i);
                return;
            }
            Log.w("BluetoothEventManager", "ActiveDeviceChangedHandler: action is null");
        }
    }

    private class AclStateChangedHandler implements Handler {
        private AclStateChangedHandler() {
        }

        @Override // com.android.settingslib.bluetooth.BluetoothEventManager.Handler
        public void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice) {
            if (bluetoothDevice != null) {
                if (BluetoothEventManager.this.mDeviceManager.isSubDevice(bluetoothDevice)) {
                    return;
                }
                String action = intent.getAction();
                if (action != null) {
                    CachedBluetoothDevice cachedBluetoothDeviceFindDevice = BluetoothEventManager.this.mDeviceManager.findDevice(bluetoothDevice);
                    if (cachedBluetoothDeviceFindDevice == null) {
                        Log.w("BluetoothEventManager", "AclStateChangedHandler: activeDevice is null");
                        return;
                    }
                    byte b = -1;
                    int iHashCode = action.hashCode();
                    int i = 0;
                    if (iHashCode != -301431627) {
                        if (iHashCode == 1821585647 && action.equals("android.bluetooth.device.action.ACL_DISCONNECTED")) {
                            b = 1;
                        }
                    } else if (action.equals("android.bluetooth.device.action.ACL_CONNECTED")) {
                        b = 0;
                    }
                    if (b == 0) {
                        i = 2;
                    } else if (b != 1) {
                        Log.w("BluetoothEventManager", "ActiveDeviceChangedHandler: unknown action " + action);
                        return;
                    }
                    BluetoothEventManager.this.dispatchAclStateChanged(cachedBluetoothDeviceFindDevice, i);
                    return;
                }
                Log.w("BluetoothEventManager", "AclStateChangedHandler: action is null");
                return;
            }
            Log.w("BluetoothEventManager", "AclStateChangedHandler: device is null");
        }
    }

    private class AudioModeChangedHandler implements Handler {
        private AudioModeChangedHandler() {
        }

        @Override // com.android.settingslib.bluetooth.BluetoothEventManager.Handler
        public void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice) {
            if (intent.getAction() != null) {
                BluetoothEventManager.this.dispatchAudioModeChanged();
            } else {
                Log.w("BluetoothEventManager", "AudioModeChangedHandler() action is null");
            }
        }
    }
}
