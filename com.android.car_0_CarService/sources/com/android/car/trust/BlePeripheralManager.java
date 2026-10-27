package com.android.car.trust;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattServer;
import android.bluetooth.BluetoothGattServerCallback;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseData;
import android.bluetooth.le.AdvertiseSettings;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import com.android.car.Utils;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public class BlePeripheralManager {
    private static final int BLE_RETRY_INTERVAL_MS = 1000;
    private static final int BLE_RETRY_LIMIT = 5;
    private static final int GATT_SERVER_RETRY_DELAY_MS = 200;
    private static final int GATT_SERVER_RETRY_LIMIT = 20;
    private AdvertiseCallback mAdvertiseCallback;
    private AdvertiseData mAdvertiseData;
    private BluetoothLeAdvertiser mAdvertiser;
    private int mAdvertiserStartCount;
    private BluetoothGatt mBluetoothGatt;
    private BluetoothGattService mBluetoothGattService;
    private BluetoothManager mBluetoothManager;
    private final Context mContext;
    private BluetoothGattServer mGattServer;
    private int mGattServerRetryStartCount;
    private static final String TAG = BlePeripheralManager.class.getSimpleName();
    private static final UUID GENERIC_ACCESS_PROFILE_UUID = UUID.fromString("00001800-0000-1000-8000-00805f9b34fb");
    private static final UUID DEVICE_NAME_UUID = UUID.fromString("00002a00-0000-1000-8000-00805f9b34fb");
    private final Handler mHandler = new Handler();
    private final CopyOnWriteArrayList<Callback> mCallbacks = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<OnCharacteristicWriteListener> mWriteListeners = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<OnCharacteristicReadListener> mReadListeners = new CopyOnWriteArrayList<>();
    private int mMtuSize = 20;
    private final BluetoothGattServerCallback mGattServerCallback = new BluetoothGattServerCallback() { // from class: com.android.car.trust.BlePeripheralManager.1
        @Override // android.bluetooth.BluetoothGattServerCallback
        public void onConnectionStateChange(BluetoothDevice device, int status, int newState) {
            if (Log.isLoggable(BlePeripheralManager.TAG, 3)) {
                Log.d(BlePeripheralManager.TAG, "BLE Connection State Change: " + newState);
            }
            if (newState == 0) {
                for (Callback callback : BlePeripheralManager.this.mCallbacks) {
                    callback.onRemoteDeviceDisconnected(device);
                }
                return;
            }
            if (newState != 2) {
                Log.w(BlePeripheralManager.TAG, "Connection state not connecting or disconnecting; ignoring: " + newState);
                return;
            }
            for (Callback callback2 : BlePeripheralManager.this.mCallbacks) {
                callback2.onRemoteDeviceConnected(device);
            }
        }

        @Override // android.bluetooth.BluetoothGattServerCallback
        public void onServiceAdded(int status, BluetoothGattService service) {
            if (Log.isLoggable(BlePeripheralManager.TAG, 3)) {
                Log.d(BlePeripheralManager.TAG, "Service added status: " + status + " uuid: " + service.getUuid());
            }
        }

        @Override // android.bluetooth.BluetoothGattServerCallback
        public void onCharacteristicReadRequest(BluetoothDevice device, int requestId, int offset, BluetoothGattCharacteristic characteristic) {
            if (Log.isLoggable(BlePeripheralManager.TAG, 3)) {
                Log.d(BlePeripheralManager.TAG, "Read request for characteristic: " + characteristic.getUuid());
            }
            boolean isSuccessful = BlePeripheralManager.this.mGattServer.sendResponse(device, requestId, 0, offset, characteristic.getValue());
            if (isSuccessful) {
                for (OnCharacteristicReadListener listener : BlePeripheralManager.this.mReadListeners) {
                    listener.onCharacteristicRead(device, characteristic);
                }
                return;
            }
            BlePeripheralManager.this.stopGattServer();
            for (Callback callback : BlePeripheralManager.this.mCallbacks) {
                callback.onRemoteDeviceDisconnected(device);
            }
        }

        @Override // android.bluetooth.BluetoothGattServerCallback
        public void onCharacteristicWriteRequest(BluetoothDevice device, int requestId, BluetoothGattCharacteristic characteristic, boolean preparedWrite, boolean responseNeeded, int offset, byte[] value) {
            if (Log.isLoggable(BlePeripheralManager.TAG, 3)) {
                Log.d(BlePeripheralManager.TAG, "Write request for characteristic: " + characteristic.getUuid() + "value: " + Utils.byteArrayToHexString(value));
            }
            BlePeripheralManager.this.mGattServer.sendResponse(device, requestId, 0, offset, value);
            for (OnCharacteristicWriteListener listener : BlePeripheralManager.this.mWriteListeners) {
                listener.onCharacteristicWrite(device, characteristic, value);
            }
        }

        @Override // android.bluetooth.BluetoothGattServerCallback
        public void onDescriptorWriteRequest(BluetoothDevice device, int requestId, BluetoothGattDescriptor descriptor, boolean preparedWrite, boolean responseNeeded, int offset, byte[] value) {
            if (Log.isLoggable(BlePeripheralManager.TAG, 3)) {
                Log.d(BlePeripheralManager.TAG, "Write request for descriptor: " + descriptor.getUuid() + "; value: " + Utils.byteArrayToHexString(value));
            }
            BlePeripheralManager.this.mGattServer.sendResponse(device, requestId, 0, offset, value);
        }

        @Override // android.bluetooth.BluetoothGattServerCallback
        public void onMtuChanged(BluetoothDevice device, int mtu) {
            if (Log.isLoggable(BlePeripheralManager.TAG, 3)) {
                Log.d(BlePeripheralManager.TAG, "onMtuChanged: " + mtu + " for device " + device.getAddress());
            }
            BlePeripheralManager.this.mMtuSize = mtu;
            for (Callback callback : BlePeripheralManager.this.mCallbacks) {
                callback.onMtuSizeChanged(mtu);
            }
        }
    };
    private final BluetoothGattCallback mGattCallback = new BluetoothGattCallback() { // from class: com.android.car.trust.BlePeripheralManager.2
        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            if (Log.isLoggable(BlePeripheralManager.TAG, 3)) {
                Log.d(BlePeripheralManager.TAG, "Gatt Connection State Change: " + newState);
            }
            if (newState == 0) {
                if (Log.isLoggable(BlePeripheralManager.TAG, 3)) {
                    Log.d(BlePeripheralManager.TAG, "Gatt Disconnected");
                }
            } else {
                if (newState != 2) {
                    if (Log.isLoggable(BlePeripheralManager.TAG, 3)) {
                        Log.d(BlePeripheralManager.TAG, "Connection state not connecting or disconnecting; ignoring: " + newState);
                        return;
                    }
                    return;
                }
                if (Log.isLoggable(BlePeripheralManager.TAG, 3)) {
                    Log.d(BlePeripheralManager.TAG, "Gatt connected");
                }
                BlePeripheralManager.this.mBluetoothGatt.discoverServices();
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            if (Log.isLoggable(BlePeripheralManager.TAG, 3)) {
                Log.d(BlePeripheralManager.TAG, "Gatt Services Discovered");
            }
            BluetoothGattService gapService = BlePeripheralManager.this.mBluetoothGatt.getService(BlePeripheralManager.GENERIC_ACCESS_PROFILE_UUID);
            if (gapService == null) {
                Log.e(BlePeripheralManager.TAG, "Generic Access Service is Null");
                return;
            }
            BluetoothGattCharacteristic deviceNameCharacteristic = gapService.getCharacteristic(BlePeripheralManager.DEVICE_NAME_UUID);
            if (deviceNameCharacteristic == null) {
                Log.e(BlePeripheralManager.TAG, "Device Name Characteristic is Null");
            } else {
                BlePeripheralManager.this.mBluetoothGatt.readCharacteristic(deviceNameCharacteristic);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            if (status != 0) {
                Log.e(BlePeripheralManager.TAG, "Reading GAP Failed: " + status);
                return;
            }
            String deviceName = characteristic.getStringValue(0);
            if (Log.isLoggable(BlePeripheralManager.TAG, 3)) {
                Log.d(BlePeripheralManager.TAG, "BLE Device Name: " + deviceName);
            }
            for (Callback callback : BlePeripheralManager.this.mCallbacks) {
                callback.onDeviceNameRetrieved(deviceName);
            }
        }
    };

    interface Callback {
        void onDeviceNameRetrieved(String str);

        void onMtuSizeChanged(int i);

        void onRemoteDeviceConnected(BluetoothDevice bluetoothDevice);

        void onRemoteDeviceDisconnected(BluetoothDevice bluetoothDevice);
    }

    interface OnCharacteristicReadListener {
        void onCharacteristicRead(BluetoothDevice bluetoothDevice, BluetoothGattCharacteristic bluetoothGattCharacteristic);
    }

    interface OnCharacteristicWriteListener {
        void onCharacteristicWrite(BluetoothDevice bluetoothDevice, BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr);
    }

    BlePeripheralManager(Context context) {
        this.mContext = context;
    }

    void registerCallback(Callback callback) {
        this.mCallbacks.add(callback);
    }

    void unregisterCallback(Callback callback) {
        this.mCallbacks.remove(callback);
    }

    void addOnCharacteristicWriteListener(OnCharacteristicWriteListener listener) {
        this.mWriteListeners.add(listener);
    }

    void removeOnCharacteristicWriteListener(OnCharacteristicWriteListener listener) {
        this.mWriteListeners.remove(listener);
    }

    void addOnCharacteristicReadListener(OnCharacteristicReadListener listener) {
        this.mReadListeners.add(listener);
    }

    void removeOnCharacteristicReadistener(OnCharacteristicReadListener listener) {
        this.mReadListeners.remove(listener);
    }

    int getMtuSize() {
        return this.mMtuSize;
    }

    void startAdvertising(BluetoothGattService service, AdvertiseData data, AdvertiseCallback advertiseCallback) {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "startAdvertising: " + service.getUuid().toString());
        }
        if (!this.mContext.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le")) {
            Log.e(TAG, "Attempted start advertising, but system does not support BLE. Ignoring");
            return;
        }
        this.mBluetoothGattService = service;
        this.mAdvertiseCallback = advertiseCallback;
        this.mAdvertiseData = data;
        this.mGattServerRetryStartCount = 0;
        this.mBluetoothManager = (BluetoothManager) this.mContext.getSystemService("bluetooth");
        lambda$openGattServer$0$BlePeripheralManager();
    }

    void stopAdvertising(AdvertiseCallback advertiseCallback) {
        if (this.mAdvertiser != null) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "stopAdvertising: ");
            }
            this.mAdvertiser.stopAdvertising(advertiseCallback);
        }
    }

    void notifyCharacteristicChanged(BluetoothDevice device, BluetoothGattCharacteristic characteristic, boolean confirm) {
        BluetoothGattServer bluetoothGattServer = this.mGattServer;
        if (bluetoothGattServer == null) {
            return;
        }
        boolean result = bluetoothGattServer.notifyCharacteristicChanged(device, characteristic, confirm);
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "notifyCharacteristicChanged succeeded: " + result);
        }
    }

    final void retrieveDeviceName(BluetoothDevice device) {
        this.mBluetoothGatt = device.connectGatt(this.mContext, false, this.mGattCallback);
    }

    BluetoothGattServer getGattServer() {
        return this.mGattServer;
    }

    void cleanup() {
        BluetoothLeAdvertiser bluetoothLeAdvertiser = this.mAdvertiser;
        if (bluetoothLeAdvertiser != null) {
            bluetoothLeAdvertiser.cleanup();
        }
        BluetoothGattServer bluetoothGattServer = this.mGattServer;
        if (bluetoothGattServer != null) {
            bluetoothGattServer.clearServices();
            try {
                try {
                    for (BluetoothDevice d : this.mBluetoothManager.getConnectedDevices(8)) {
                        this.mGattServer.cancelConnection(d);
                    }
                } catch (UnsupportedOperationException e) {
                    Log.e(TAG, "Error getting connected devices", e);
                }
            } finally {
                stopGattServer();
            }
        }
    }

    void stopGattServer() {
        if (this.mGattServer == null) {
            return;
        }
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "stopGattServer");
        }
        BluetoothGatt bluetoothGatt = this.mBluetoothGatt;
        if (bluetoothGatt != null) {
            bluetoothGatt.disconnect();
        }
        this.mGattServer.close();
        this.mGattServer = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: openGattServer, reason: merged with bridge method [inline-methods] */
    public void lambda$openGattServer$0$BlePeripheralManager() {
        if (this.mGattServer == null) {
            if (this.mGattServerRetryStartCount < 20) {
                this.mGattServer = this.mBluetoothManager.openGattServer(this.mContext, this.mGattServerCallback);
                this.mGattServerRetryStartCount++;
                this.mHandler.postDelayed(new Runnable() { // from class: com.android.car.trust.-$$Lambda$BlePeripheralManager$QGZGtgTFhvItW72VciCliw9r8ZM
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$openGattServer$0$BlePeripheralManager();
                    }
                }, 200L);
                return;
            }
            Log.e(TAG, "Gatt server not created - exceeded retry limit.");
            return;
        }
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "Gatt Server created, retry count: " + this.mGattServerRetryStartCount);
        }
        this.mGattServer.clearServices();
        this.mGattServer.addService(this.mBluetoothGattService);
        AdvertiseSettings settings = new AdvertiseSettings.Builder().setAdvertiseMode(2).setTxPowerLevel(3).setConnectable(true).build();
        this.mAdvertiserStartCount = 0;
        lambda$startAdvertisingInternally$1$BlePeripheralManager(settings, this.mAdvertiseData, this.mAdvertiseCallback);
        this.mGattServerRetryStartCount = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: startAdvertisingInternally, reason: merged with bridge method [inline-methods] */
    public void lambda$startAdvertisingInternally$1$BlePeripheralManager(final AdvertiseSettings settings, final AdvertiseData data, final AdvertiseCallback advertiseCallback) {
        if (BluetoothAdapter.getDefaultAdapter() != null) {
            this.mAdvertiser = BluetoothAdapter.getDefaultAdapter().getBluetoothLeAdvertiser();
        }
        if (this.mAdvertiser != null) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Advertiser created, retry count: " + this.mAdvertiserStartCount);
            }
            this.mAdvertiser.startAdvertising(settings, data, advertiseCallback);
            this.mAdvertiserStartCount = 0;
            return;
        }
        if (this.mAdvertiserStartCount < 5) {
            this.mHandler.postDelayed(new Runnable() { // from class: com.android.car.trust.-$$Lambda$BlePeripheralManager$xXgDohZIuJ7oI6fGG-0cRF1ORE8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$startAdvertisingInternally$1$BlePeripheralManager(settings, data, advertiseCallback);
                }
            }, 1000L);
            this.mAdvertiserStartCount++;
            return;
        }
        Log.e(TAG, "Cannot start BLE Advertisement.  BT Adapter: " + BluetoothAdapter.getDefaultAdapter() + " Advertise Retry count: " + this.mAdvertiserStartCount);
    }
}
