package com.android.car.trust;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Handler;
import android.util.Log;
import com.android.car.BLEStreamProtos.BLEMessageProto;
import com.android.car.BLEStreamProtos.BLEOperationProto;
import com.android.car.protobuf.InvalidProtocolBufferException;
import com.android.car.trust.BlePeripheralManager;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
class BleMessageStreamV1 implements BleMessageStream {
    private static final long BLE_MESSAGE_RETRY_DELAY_MS = TimeUnit.SECONDS.toMillis(2);
    static final int BLE_MESSAGE_RETRY_LIMIT = 5;
    private static final String TAG = "BleMessageStreamV1";
    private final BlePeripheralManager mBlePeripheralManager;
    private final BluetoothDevice mDevice;
    private final Handler mHandler;
    private final BluetoothGattCharacteristic mReadCharacteristic;
    private final BluetoothGattCharacteristic mWriteCharacteristic;
    private final Deque<BLEMessageProto.BLEMessage> mMessageQueue = new ArrayDeque();
    private final BLEMessagePayloadStream mPayloadStream = new BLEMessagePayloadStream();
    private int mRetryCount = 0;
    private int mMaxWriteSize = 20;
    private final List<BleMessageStreamCallback> mCallbacks = new ArrayList();
    private final Runnable mSendMessageWithTimeoutRunnable = new Runnable() { // from class: com.android.car.trust.BleMessageStreamV1.1
        @Override // java.lang.Runnable
        public void run() {
            if (Log.isLoggable(BleMessageStreamV1.TAG, 3)) {
                Log.d(BleMessageStreamV1.TAG, "Sending BLE message; retry count: " + BleMessageStreamV1.this.mRetryCount);
            }
            if (BleMessageStreamV1.this.mRetryCount >= 5) {
                BleMessageStreamV1.this.mHandler.removeCallbacks(this);
                BleMessageStreamV1.this.mRetryCount = 0;
                BleMessageStreamV1.this.mMessageQueue.clear();
                Log.e(BleMessageStreamV1.TAG, "Error during BLE message sending - exceeded retry limit.");
                for (BleMessageStreamCallback callback : BleMessageStreamV1.this.mCallbacks) {
                    callback.onWriteMessageError();
                }
                return;
            }
            BleMessageStreamV1 bleMessageStreamV1 = BleMessageStreamV1.this;
            bleMessageStreamV1.writeValueAndNotify(((BLEMessageProto.BLEMessage) bleMessageStreamV1.mMessageQueue.peek()).toByteArray());
            BleMessageStreamV1.access$008(BleMessageStreamV1.this);
            BleMessageStreamV1.this.mHandler.postDelayed(this, BleMessageStreamV1.BLE_MESSAGE_RETRY_DELAY_MS);
        }
    };

    static /* synthetic */ int access$008(BleMessageStreamV1 x0) {
        int i = x0.mRetryCount;
        x0.mRetryCount = i + 1;
        return i;
    }

    BleMessageStreamV1(Handler handler, BlePeripheralManager blePeripheralManager, BluetoothDevice device, BluetoothGattCharacteristic writeCharacteristic, BluetoothGattCharacteristic readCharacteristic) {
        this.mHandler = handler;
        this.mBlePeripheralManager = blePeripheralManager;
        this.mDevice = device;
        this.mWriteCharacteristic = writeCharacteristic;
        this.mReadCharacteristic = readCharacteristic;
        blePeripheralManager.addOnCharacteristicWriteListener(new BlePeripheralManager.OnCharacteristicWriteListener() { // from class: com.android.car.trust.-$$Lambda$XCO0SMj1J-fyjhW-q3iO_R1Oswo
            @Override // com.android.car.trust.BlePeripheralManager.OnCharacteristicWriteListener
            public final void onCharacteristicWrite(BluetoothDevice bluetoothDevice, BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
                this.f$0.onCharacteristicWrite(bluetoothDevice, bluetoothGattCharacteristic, bArr);
            }
        });
    }

    @Override // com.android.car.trust.BleMessageStream
    public void registerCallback(BleMessageStreamCallback callback) {
        this.mCallbacks.add(callback);
    }

    @Override // com.android.car.trust.BleMessageStream
    public void unregisterCallback(BleMessageStreamCallback callback) {
        this.mCallbacks.remove(callback);
    }

    @Override // com.android.car.trust.BleMessageStream
    public void setMaxWriteSize(int maxWriteSize) {
        this.mMaxWriteSize = maxWriteSize;
    }

    @Override // com.android.car.trust.BleMessageStream
    public int getMaxWriteSize() {
        return this.mMaxWriteSize;
    }

    @Override // com.android.car.trust.BleMessageStream
    public void writeMessage(byte[] message, BLEOperationProto.OperationType operationType, boolean isPayloadEncrypted) {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "Writing message to device with name: " + this.mDevice.getName());
        }
        List<BLEMessageProto.BLEMessage> bleMessages = BLEMessageV1Factory.makeBLEMessages(message, operationType, this.mMaxWriteSize, isPayloadEncrypted);
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "Number of messages to send to device: " + bleMessages.size());
        }
        if (!this.mMessageQueue.isEmpty()) {
            this.mMessageQueue.clear();
            Log.w(TAG, "Request to write a new message when there are still messages in the queue.");
        }
        this.mMessageQueue.addAll(bleMessages);
        writeNextMessageInQueue();
    }

    void onCharacteristicWrite(BluetoothDevice device, BluetoothGattCharacteristic characteristic, byte[] value) {
        if (!this.mDevice.equals(device)) {
            Log.w(TAG, "Received a message from a device (" + device.getAddress() + ") that is not the expected device (" + this.mDevice.getAddress() + ") registered to this stream. Ignoring.");
            return;
        }
        if (!characteristic.getUuid().equals(this.mReadCharacteristic.getUuid())) {
            Log.w(TAG, "Received a write to a characteristic (" + characteristic.getUuid() + ") that is not the expected UUID (" + this.mReadCharacteristic.getUuid() + "). Ignoring.");
            return;
        }
        try {
            BLEMessageProto.BLEMessage bleMessage = BLEMessageProto.BLEMessage.parseFrom(value);
            if (bleMessage.getOperation() == BLEOperationProto.OperationType.ACK) {
                handleClientAckMessage();
                return;
            }
            try {
                this.mPayloadStream.write(bleMessage);
                if (!this.mPayloadStream.isComplete()) {
                    sendAcknowledgmentMessage();
                    return;
                }
                for (BleMessageStreamCallback callback : this.mCallbacks) {
                    callback.onMessageReceived(this.mPayloadStream.toByteArray(), characteristic.getUuid());
                }
                this.mPayloadStream.reset();
            } catch (IOException e) {
                Log.e(TAG, "Unable to parse the BLE message's payload from client.", e);
                for (BleMessageStreamCallback callback2 : this.mCallbacks) {
                    callback2.onMessageReceivedError(characteristic.getUuid());
                }
            }
        } catch (InvalidProtocolBufferException e2) {
            Log.e(TAG, "Can not parse BLE message from client.", e2);
            for (BleMessageStreamCallback callback3 : this.mCallbacks) {
                callback3.onMessageReceivedError(characteristic.getUuid());
            }
        }
    }

    private void writeNextMessageInQueue() {
        if (this.mMessageQueue.isEmpty()) {
            Log.e(TAG, "Call to write next message in queue, but the message queue is empty.");
        } else if (this.mMessageQueue.size() == 1) {
            writeValueAndNotify(this.mMessageQueue.remove().toByteArray());
        } else {
            this.mHandler.post(this.mSendMessageWithTimeoutRunnable);
        }
    }

    private void handleClientAckMessage() {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "Received ACK from client. Attempting to write next message in queue.");
        }
        this.mHandler.removeCallbacks(this.mSendMessageWithTimeoutRunnable);
        this.mRetryCount = 0;
        if (this.mMessageQueue.isEmpty()) {
            Log.e(TAG, "Received ACK, but the message queue is empty. Ignoring.");
        } else {
            this.mMessageQueue.remove();
            writeNextMessageInQueue();
        }
    }

    private void sendAcknowledgmentMessage() {
        writeValueAndNotify(BLEMessageV1Factory.makeAcknowledgementMessage().toByteArray());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void writeValueAndNotify(byte[] message) {
        this.mWriteCharacteristic.setValue(message);
        this.mBlePeripheralManager.notifyCharacteristicChanged(this.mDevice, this.mWriteCharacteristic, false);
    }
}
