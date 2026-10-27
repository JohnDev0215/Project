package com.android.car.trust;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseData;
import android.bluetooth.le.AdvertiseSettings;
import android.content.Context;
import android.os.Handler;
import android.os.ParcelUuid;
import android.util.Log;
import androidx.collection.SimpleArrayMap;
import com.android.car.BLEStreamProtos.BLEOperationProto;
import com.android.car.BLEStreamProtos.VersionExchangeProto;
import com.android.car.R;
import com.android.car.Utils;
import com.android.car.protobuf.InvalidProtocolBufferException;
import com.android.car.trust.BlePeripheralManager;
import com.android.car.trust.CarTrustAgentBleManager;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes3.dex */
class CarTrustAgentBleManager implements BleMessageStreamCallback, BlePeripheralManager.Callback, BlePeripheralManager.OnCharacteristicWriteListener {
    private static final int ATT_PAYLOAD_RESERVED_BYTES = 3;
    private static final String TAG = "CarTrustBLEManager";
    private static final int TRUSTED_DEVICE_OPERATION_ENROLLMENT = 1;
    private static final int TRUSTED_DEVICE_OPERATION_NONE = 0;
    private static final int TRUSTED_DEVICE_OPERATION_UNLOCK = 2;
    private final BlePeripheralManager mBlePeripheralManager;
    private final Context mContext;
    private AdvertiseCallback mEnrollmentAdvertisingCallback;
    private UUID mEnrollmentClientWriteUuid;
    private BluetoothGattService mEnrollmentGattService;
    private UUID mEnrollmentServerWriteUuid;
    private UUID mEnrollmentServiceUuid;
    private BleMessageStream mMessageStream;
    private String mOriginalBluetoothName;
    private SendMessageCallback mSendMessageCallback;
    private byte[] mUniqueId;
    private UUID mUnlockClientWriteUuid;
    private BluetoothGattService mUnlockGattService;
    private UUID mUnlockServerWriteUuid;
    private UUID mUnlockServiceUuid;
    private static final UUID CLIENT_CHARACTERISTIC_CONFIG = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");
    static final long BLE_MESSAGE_RETRY_DELAY_MS = TimeUnit.SECONDS.toMillis(2);
    private int mCurrentTrustedDeviceOperation = 0;
    private int mMaxWriteSize = 20;
    int mBleMessageRetryLimit = 20;
    private final List<BleEventCallback> mBleEventCallbacks = new ArrayList();
    private final Handler mHandler = new Handler();
    private final SimpleArrayMap<UUID, DataReceivedListener> mDataReceivedListeners = new SimpleArrayMap<>();
    private final AdvertiseCallback mUnlockAdvertisingCallback = new AdvertiseCallback() { // from class: com.android.car.trust.CarTrustAgentBleManager.1
        @Override // android.bluetooth.le.AdvertiseCallback
        public void onStartSuccess(AdvertiseSettings settingsInEffect) {
            super.onStartSuccess(settingsInEffect);
            if (Log.isLoggable(CarTrustAgentBleManager.TAG, 3)) {
                Log.d(CarTrustAgentBleManager.TAG, "Unlock Advertising onStartSuccess");
            }
        }

        @Override // android.bluetooth.le.AdvertiseCallback
        public void onStartFailure(int errorCode) {
            Log.e(CarTrustAgentBleManager.TAG, "Failed to advertise, errorCode: " + errorCode);
            super.onStartFailure(errorCode);
            if (errorCode == 3) {
                return;
            }
            if (Log.isLoggable(CarTrustAgentBleManager.TAG, 3)) {
                Log.d(CarTrustAgentBleManager.TAG, "Start unlock advertising fail, retry to advertising..");
            }
            CarTrustAgentBleManager.this.setupUnlockBleServer();
            CarTrustAgentBleManager.this.startUnlockAdvertising();
        }
    };

    interface BleEventCallback {
        void onClientDeviceNameRetrieved(String str);

        void onRemoteDeviceConnected(BluetoothDevice bluetoothDevice);

        void onRemoteDeviceDisconnected(BluetoothDevice bluetoothDevice);
    }

    interface DataReceivedListener {
        void onDataReceived(byte[] bArr);
    }

    interface SendMessageCallback {
        void onSendMessageFailure();
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TrustedDeviceOperation {
    }

    CarTrustAgentBleManager(Context context, BlePeripheralManager blePeripheralManager) {
        this.mContext = context;
        this.mBlePeripheralManager = blePeripheralManager;
        blePeripheralManager.registerCallback(this);
    }

    void setUniqueId(UUID uniqueId) {
        this.mUniqueId = Utils.uuidToBytes(uniqueId);
    }

    void cleanup() {
        this.mBlePeripheralManager.cleanup();
    }

    void stopGattServer() {
        this.mBlePeripheralManager.stopGattServer();
    }

    @Override // com.android.car.trust.BlePeripheralManager.Callback
    public void onRemoteDeviceConnected(final BluetoothDevice device) {
        if (this.mBleEventCallbacks.isEmpty()) {
            Log.e(TAG, "No valid BleEventCallback for trust device.");
            return;
        }
        if (this.mCurrentTrustedDeviceOperation == 1 && device.getName() == null) {
            this.mBlePeripheralManager.retrieveDeviceName(device);
        }
        BleMessageStream bleMessageStream = this.mMessageStream;
        if (bleMessageStream != null) {
            bleMessageStream.unregisterCallback(this);
            this.mMessageStream = null;
        }
        this.mSendMessageCallback = null;
        this.mBlePeripheralManager.addOnCharacteristicWriteListener(this);
        this.mBleEventCallbacks.forEach(new Consumer() { // from class: com.android.car.trust.-$$Lambda$CarTrustAgentBleManager$khiWIzfAa4ovh1usO0JGf63UT5s
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((CarTrustAgentBleManager.BleEventCallback) obj).onRemoteDeviceConnected(device);
            }
        });
    }

    @Override // com.android.car.trust.BlePeripheralManager.Callback
    public void onRemoteDeviceDisconnected(final BluetoothDevice device) {
        this.mBlePeripheralManager.removeOnCharacteristicWriteListener(this);
        this.mBleEventCallbacks.forEach(new Consumer() { // from class: com.android.car.trust.-$$Lambda$CarTrustAgentBleManager$TE43H6w41yAIqDgL9NNV-75YHQk
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((CarTrustAgentBleManager.BleEventCallback) obj).onRemoteDeviceDisconnected(device);
            }
        });
        BleMessageStream bleMessageStream = this.mMessageStream;
        if (bleMessageStream != null) {
            bleMessageStream.unregisterCallback(this);
            this.mMessageStream = null;
        }
        this.mSendMessageCallback = null;
    }

    @Override // com.android.car.trust.BlePeripheralManager.Callback
    public void onDeviceNameRetrieved(final String deviceName) {
        this.mBleEventCallbacks.forEach(new Consumer() { // from class: com.android.car.trust.-$$Lambda$CarTrustAgentBleManager$5Q3GSgAl2OzD6fOf9g28U1yiP9I
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((CarTrustAgentBleManager.BleEventCallback) obj).onClientDeviceNameRetrieved(deviceName);
            }
        });
    }

    @Override // com.android.car.trust.BlePeripheralManager.Callback
    public void onMtuSizeChanged(int size) {
        int i = size - 3;
        this.mMaxWriteSize = i;
        BleMessageStream bleMessageStream = this.mMessageStream;
        if (bleMessageStream != null) {
            bleMessageStream.setMaxWriteSize(i);
        }
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "MTU size changed to: " + size + "; setting max payload size to: " + this.mMaxWriteSize);
        }
    }

    @Override // com.android.car.trust.BlePeripheralManager.OnCharacteristicWriteListener
    public void onCharacteristicWrite(BluetoothDevice device, BluetoothGattCharacteristic characteristic, byte[] value) {
        UUID uuid = characteristic.getUuid();
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "onCharacteristicWrite received uuid: " + uuid);
        }
        if (this.mMessageStream != null) {
            Log.e(TAG, "Received a message but message stream has already been created. Was this manager not unregistered as a listener for writes?");
        } else {
            resolveBLEVersion(device, value, uuid);
        }
    }

    void setBleMessageRetryLimit(int limit) {
        this.mBleMessageRetryLimit = limit;
    }

    private void resolveBLEVersion(BluetoothDevice device, byte[] value, UUID clientCharacteristicUUID) {
        BluetoothGattCharacteristic readCharacteristic;
        BluetoothGattCharacteristic writeCharacteristic = getCharacteristicForWrite(clientCharacteristicUUID);
        if (writeCharacteristic == null) {
            Log.e(TAG, "Invalid write UUID (" + clientCharacteristicUUID + ") during version exchange; disconnecting from remote device.");
            disconnectRemoteDevice();
            return;
        }
        if (clientCharacteristicUUID.equals(this.mEnrollmentClientWriteUuid)) {
            readCharacteristic = this.mEnrollmentGattService.getCharacteristic(clientCharacteristicUUID);
        } else {
            readCharacteristic = this.mUnlockGattService.getCharacteristic(clientCharacteristicUUID);
        }
        if (readCharacteristic == null) {
            Log.e(TAG, "No read characteristic corresponding to UUID (" + clientCharacteristicUUID + "). Cannot listen for messages. Disconnecting.");
            disconnectRemoteDevice();
            return;
        }
        try {
            VersionExchangeProto.BLEVersionExchange deviceVersion = VersionExchangeProto.BLEVersionExchange.parseFrom(value);
            BleMessageStream bleMessageStreamResolveToStream = BLEVersionExchangeResolver.resolveToStream(deviceVersion, device, this.mBlePeripheralManager, writeCharacteristic, readCharacteristic);
            this.mMessageStream = bleMessageStreamResolveToStream;
            bleMessageStreamResolveToStream.setMaxWriteSize(this.mMaxWriteSize);
            this.mMessageStream.registerCallback(this);
            if (this.mMessageStream == null) {
                Log.e(TAG, "No supported version found during version exchange. Could not create message stream.");
                disconnectRemoteDevice();
                return;
            }
            this.mBlePeripheralManager.removeOnCharacteristicWriteListener(this);
            VersionExchangeProto.BLEVersionExchange headunitVersion = BLEVersionExchangeResolver.makeVersionExchange();
            setValueOnCharacteristicAndNotify(device, headunitVersion.toByteArray(), writeCharacteristic);
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Sent supported version to the phone.");
            }
        } catch (InvalidProtocolBufferException e) {
            disconnectRemoteDevice();
            Log.e(TAG, "Could not parse version exchange message", e);
        }
    }

    void setupEnrollmentBleServer() {
        this.mEnrollmentServiceUuid = UUID.fromString(this.mContext.getString(R.string.enrollment_service_uuid));
        this.mEnrollmentClientWriteUuid = UUID.fromString(this.mContext.getString(R.string.enrollment_client_write_uuid));
        this.mEnrollmentServerWriteUuid = UUID.fromString(this.mContext.getString(R.string.enrollment_server_write_uuid));
        this.mEnrollmentGattService = new BluetoothGattService(this.mEnrollmentServiceUuid, 0);
        BluetoothGattCharacteristic clientCharacteristic = new BluetoothGattCharacteristic(this.mEnrollmentClientWriteUuid, 12, 16);
        BluetoothGattCharacteristic serverCharacteristic = new BluetoothGattCharacteristic(this.mEnrollmentServerWriteUuid, 16, 1);
        addDescriptorToCharacteristic(serverCharacteristic);
        this.mEnrollmentGattService.addCharacteristic(clientCharacteristic);
        this.mEnrollmentGattService.addCharacteristic(serverCharacteristic);
    }

    void setupUnlockBleServer() {
        this.mUnlockServiceUuid = UUID.fromString(this.mContext.getString(R.string.unlock_service_uuid));
        this.mUnlockClientWriteUuid = UUID.fromString(this.mContext.getString(R.string.unlock_client_write_uuid));
        this.mUnlockServerWriteUuid = UUID.fromString(this.mContext.getString(R.string.unlock_server_write_uuid));
        this.mUnlockGattService = new BluetoothGattService(this.mUnlockServiceUuid, 0);
        BluetoothGattCharacteristic clientCharacteristic = new BluetoothGattCharacteristic(this.mUnlockClientWriteUuid, 12, 16);
        BluetoothGattCharacteristic serverCharacteristic = new BluetoothGattCharacteristic(this.mUnlockServerWriteUuid, 16, 1);
        addDescriptorToCharacteristic(serverCharacteristic);
        this.mUnlockGattService.addCharacteristic(clientCharacteristic);
        this.mUnlockGattService.addCharacteristic(serverCharacteristic);
    }

    @Override // com.android.car.trust.BleMessageStreamCallback
    public void onMessageReceivedError(UUID uuid) {
        Log.e(TAG, "Error parsing the message from the client on UUID: " + uuid);
    }

    @Override // com.android.car.trust.BleMessageStreamCallback
    public void onMessageReceived(byte[] message, UUID uuid) {
        if (this.mDataReceivedListeners.containsKey(uuid)) {
            this.mDataReceivedListeners.get(uuid).onDataReceived(message);
        }
    }

    @Override // com.android.car.trust.BleMessageStreamCallback
    public void onWriteMessageError() {
        SendMessageCallback sendMessageCallback = this.mSendMessageCallback;
        if (sendMessageCallback != null) {
            sendMessageCallback.onSendMessageFailure();
        }
    }

    private void addDescriptorToCharacteristic(BluetoothGattCharacteristic characteristic) {
        BluetoothGattDescriptor descriptor = new BluetoothGattDescriptor(CLIENT_CHARACTERISTIC_CONFIG, 17);
        descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
        characteristic.addDescriptor(descriptor);
    }

    void startEnrollmentAdvertising(String deviceName, AdvertiseCallback enrollmentAdvertisingCallback) {
        if (enrollmentAdvertisingCallback == null) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Enrollment Advertising not started: enrollmentAdvertisingCallback is null");
                return;
            }
            return;
        }
        this.mCurrentTrustedDeviceOperation = 1;
        this.mEnrollmentAdvertisingCallback = enrollmentAdvertisingCallback;
        if (deviceName != null) {
            BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
            if (this.mOriginalBluetoothName == null) {
                this.mOriginalBluetoothName = adapter.getName();
            }
            adapter.setName(deviceName);
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Changing bluetooth adapter name from " + this.mOriginalBluetoothName + " to " + deviceName);
            }
        }
        attemptAdvertising();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void attemptAdvertising() {
        if (this.mOriginalBluetoothName != null && BluetoothAdapter.getDefaultAdapter().getName().equals(this.mOriginalBluetoothName)) {
            this.mHandler.postDelayed(new Runnable() { // from class: com.android.car.trust.-$$Lambda$CarTrustAgentBleManager$WiQB9zxBMg7JJsbK3pVgUj_6TTM
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.attemptAdvertising();
                }
            }, BLE_MESSAGE_RETRY_DELAY_MS);
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Adapter name change has not taken affect prior to advertising attempt. Trying again.");
                return;
            }
            return;
        }
        this.mBlePeripheralManager.startAdvertising(this.mEnrollmentGattService, new AdvertiseData.Builder().setIncludeDeviceName(true).addServiceUuid(new ParcelUuid(this.mEnrollmentServiceUuid)).build(), this.mEnrollmentAdvertisingCallback);
    }

    void stopEnrollmentAdvertising() {
        if (this.mOriginalBluetoothName != null) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Changing bluetooth adapter name back to " + this.mOriginalBluetoothName);
            }
            BluetoothAdapter.getDefaultAdapter().setName(this.mOriginalBluetoothName);
            this.mOriginalBluetoothName = null;
        }
        AdvertiseCallback advertiseCallback = this.mEnrollmentAdvertisingCallback;
        if (advertiseCallback != null) {
            this.mBlePeripheralManager.stopAdvertising(advertiseCallback);
        }
    }

    void startUnlockAdvertising() {
        if (this.mUniqueId == null) {
            Log.e(TAG, "unique id is null");
        } else {
            this.mCurrentTrustedDeviceOperation = 2;
            this.mBlePeripheralManager.startAdvertising(this.mUnlockGattService, new AdvertiseData.Builder().setIncludeDeviceName(false).addServiceData(new ParcelUuid(this.mUnlockServiceUuid), this.mUniqueId).addServiceUuid(new ParcelUuid(this.mUnlockServiceUuid)).build(), this.mUnlockAdvertisingCallback);
        }
    }

    void stopUnlockAdvertising() {
        this.mCurrentTrustedDeviceOperation = 0;
        this.mBlePeripheralManager.stopAdvertising(this.mUnlockAdvertisingCallback);
    }

    void disconnectRemoteDevice() {
        this.mBlePeripheralManager.stopGattServer();
    }

    void sendMessage(byte[] message, BLEOperationProto.OperationType operation, boolean isPayloadEncrypted, SendMessageCallback callback) {
        BleMessageStream bleMessageStream = this.mMessageStream;
        if (bleMessageStream == null) {
            Log.e(TAG, "Request to send message, but no valid message stream.");
        } else {
            this.mSendMessageCallback = callback;
            bleMessageStream.writeMessage(message, operation, isPayloadEncrypted);
        }
    }

    private void setValueOnCharacteristicAndNotify(BluetoothDevice device, byte[] message, BluetoothGattCharacteristic characteristic) {
        characteristic.setValue(message);
        this.mBlePeripheralManager.notifyCharacteristicChanged(device, characteristic, false);
    }

    private BluetoothGattCharacteristic getCharacteristicForWrite(UUID uuid) {
        if (uuid.equals(this.mEnrollmentClientWriteUuid)) {
            return this.mEnrollmentGattService.getCharacteristic(this.mEnrollmentServerWriteUuid);
        }
        if (uuid.equals(this.mUnlockClientWriteUuid)) {
            return this.mUnlockGattService.getCharacteristic(this.mUnlockServerWriteUuid);
        }
        return null;
    }

    void addDataReceivedListener(UUID uuid, DataReceivedListener listener) {
        this.mDataReceivedListeners.put(uuid, listener);
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "Register DataReceivedListener: " + listener + "for uuid " + uuid.toString());
        }
    }

    void removeDataReceivedListener(UUID uuid) {
        if (!this.mDataReceivedListeners.containsKey(uuid)) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "No DataReceivedListener for uuid " + uuid.toString() + " to unregister.");
                return;
            }
            return;
        }
        this.mDataReceivedListeners.remove(uuid);
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "Unregister DataReceivedListener for uuid " + uuid.toString());
        }
    }

    void addBleEventCallback(BleEventCallback callback) {
        this.mBleEventCallbacks.add(callback);
    }

    void removeBleEventCallback(BleEventCallback callback) {
        if (!this.mBleEventCallbacks.remove(callback) && Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "Remove BleEventCallback that does not exist.");
        }
    }
}
