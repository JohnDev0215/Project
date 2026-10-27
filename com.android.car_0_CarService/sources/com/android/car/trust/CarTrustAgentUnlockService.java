package com.android.car.trust;

import android.bluetooth.BluetoothDevice;
import android.car.encryptionrunner.EncryptionRunner;
import android.car.encryptionrunner.EncryptionRunnerFactory;
import android.car.encryptionrunner.HandshakeException;
import android.car.encryptionrunner.HandshakeMessage;
import android.car.encryptionrunner.Key;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.android.car.BLEStreamProtos.BLEOperationProto;
import com.android.car.PhoneAuthProtos.PhoneAuthProto;
import com.android.car.R;
import com.android.car.Utils;
import com.android.car.protobuf.InvalidProtocolBufferException;
import com.android.car.trust.CarTrustAgentBleManager;
import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.security.SignatureException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.UUID;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public class CarTrustAgentUnlockService {
    private static final byte[] ACKNOWLEDGEMENT_MESSAGE = "ACK".getBytes();
    private static final int MAX_LOG_SIZE = 20;
    private static final String TAG = "CarTrustAgentUnlock";
    private static final String TRUSTED_DEVICE_UNLOCK_ENABLED_KEY = "trusted_device_unlock_enabled";
    private static final int UNLOCK_STATE_KEY_EXCHANGE_IN_PROGRESS = 1;
    private static final int UNLOCK_STATE_MUTUAL_AUTH_ESTABLISHED = 2;
    private static final int UNLOCK_STATE_PHONE_CREDENTIALS_RECEIVED = 3;
    private static final int UNLOCK_STATE_WAITING_FOR_UNIQUE_ID = 0;
    private final CarCompanionDeviceStorage mCarCompanionDeviceStorage;
    private final CarTrustAgentBleManager mCarTrustAgentBleManager;
    private String mClientDeviceId;
    private Key mEncryptionKey;
    private BluetoothDevice mRemoteUnlockDevice;
    private CarTrustAgentBleManager.SendMessageCallback mSendMessageCallback;
    private final CarTrustedDeviceService mTrustedDeviceService;
    private final UUID mUnlockClientWriteUuid;
    private CarTrustAgentUnlockDelegate mUnlockDelegate;
    private int mCurrentUnlockState = 0;
    private final Queue<String> mLogQueue = new LinkedList();
    private final Object mDeviceLock = new Object();
    private EncryptionRunner mEncryptionRunner = EncryptionRunnerFactory.newRunner();
    private int mEncryptionState = 0;

    interface CarTrustAgentUnlockDelegate {
        void onUnlockDataReceived(int i, byte[] bArr, long j);
    }

    @Retention(RetentionPolicy.SOURCE)
    @interface UnlockState {
    }

    CarTrustAgentUnlockService(Context context, CarTrustedDeviceService service, CarTrustAgentBleManager bleService) {
        this.mTrustedDeviceService = service;
        this.mCarTrustAgentBleManager = bleService;
        this.mUnlockClientWriteUuid = UUID.fromString(context.getString(R.string.unlock_client_write_uuid));
        this.mEncryptionRunner.setIsReconnect(true);
        this.mSendMessageCallback = new CarTrustAgentBleManager.SendMessageCallback() { // from class: com.android.car.trust.-$$Lambda$CarTrustAgentUnlockService$_cqFybxCBJoOjEViUV_TvoWGXf4
            @Override // com.android.car.trust.CarTrustAgentBleManager.SendMessageCallback
            public final void onSendMessageFailure() {
                this.f$0.lambda$new$0$CarTrustAgentUnlockService();
            }
        };
        this.mCarCompanionDeviceStorage = new CarCompanionDeviceStorage(context);
    }

    public /* synthetic */ void lambda$new$0$CarTrustAgentUnlockService() {
        this.mCarTrustAgentBleManager.disconnectRemoteDevice();
    }

    public void setTrustedDeviceUnlockEnabled(boolean isEnabled) {
        SharedPreferences.Editor editor = this.mCarCompanionDeviceStorage.getSharedPrefs().edit();
        editor.putBoolean(TRUSTED_DEVICE_UNLOCK_ENABLED_KEY, isEnabled);
        if (!editor.commit()) {
            Log.wtf(TAG, "Unlock Enable Failed. Enable? " + isEnabled);
        }
    }

    void setUnlockRequestDelegate(CarTrustAgentUnlockDelegate delegate) {
        this.mUnlockDelegate = delegate;
    }

    void startUnlockAdvertising() {
        if (!this.mCarCompanionDeviceStorage.getSharedPrefs().getBoolean(TRUSTED_DEVICE_UNLOCK_ENABLED_KEY, true)) {
            Log.e(TAG, "Trusted Device Unlock is disabled");
            return;
        }
        this.mTrustedDeviceService.getCarTrustAgentEnrollmentService().stopEnrollmentAdvertising();
        stopUnlockAdvertising();
        EventLog.logUnlockEvent("START_UNLOCK_ADVERTISING");
        queueMessageForLog("startUnlockAdvertising");
        this.mCarTrustAgentBleManager.setUniqueId(this.mCarCompanionDeviceStorage.getUniqueId());
        this.mCarTrustAgentBleManager.startUnlockAdvertising();
    }

    void stopUnlockAdvertising() {
        EventLog.logUnlockEvent("STOP_UNLOCK_ADVERTISING");
        queueMessageForLog("stopUnlockAdvertising");
        this.mCarTrustAgentBleManager.stopUnlockAdvertising();
        if (this.mRemoteUnlockDevice != null) {
            this.mCarTrustAgentBleManager.disconnectRemoteDevice();
            this.mRemoteUnlockDevice = null;
        }
    }

    void init() {
        EventLog.logUnlockEvent("UNLOCK_SERVICE_INIT");
        this.mCarTrustAgentBleManager.setupUnlockBleServer();
        this.mCarTrustAgentBleManager.addDataReceivedListener(this.mUnlockClientWriteUuid, new CarTrustAgentBleManager.DataReceivedListener() { // from class: com.android.car.trust.-$$Lambda$m05kpi3GeW0xkIrEKve8NooXFnM
            @Override // com.android.car.trust.CarTrustAgentBleManager.DataReceivedListener
            public final void onDataReceived(byte[] bArr) {
                this.f$0.onUnlockDataReceived(bArr);
            }
        });
    }

    void release() {
        synchronized (this.mDeviceLock) {
            this.mRemoteUnlockDevice = null;
        }
    }

    void onRemoteDeviceConnected(BluetoothDevice device) {
        synchronized (this.mDeviceLock) {
            if (this.mRemoteUnlockDevice != null) {
                Log.e(TAG, "Unexpected: Cannot connect to another device when already connected");
            }
            queueMessageForLog("onRemoteDeviceConnected (addr:" + device.getAddress() + ")");
            EventLog.logUnlockEvent("REMOTE_DEVICE_CONNECTED");
            this.mRemoteUnlockDevice = device;
        }
        resetEncryptionState();
        this.mCurrentUnlockState = 0;
    }

    void onRemoteDeviceDisconnected(BluetoothDevice device) {
        if (!device.equals(this.mRemoteUnlockDevice) && device.getAddress() != null) {
            Log.e(TAG, "Disconnected from an unknown device:" + device.getAddress());
        }
        queueMessageForLog("onRemoteDeviceDisconnected (addr:" + device.getAddress() + ")");
        synchronized (this.mDeviceLock) {
            this.mRemoteUnlockDevice = null;
        }
        resetEncryptionState();
        this.mCurrentUnlockState = 0;
    }

    void onUnlockDataReceived(byte[] value) {
        int i = this.mCurrentUnlockState;
        if (i == 0) {
            if (!CarTrustAgentValidator.isValidUnlockDeviceId(value)) {
                Log.e(TAG, "Device Id rejected by validator.");
                resetUnlockStateOnFailure();
                return;
            }
            String strConvertToDeviceId = convertToDeviceId(value);
            this.mClientDeviceId = strConvertToDeviceId;
            if (strConvertToDeviceId == null) {
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "Phone not enrolled as a trusted device");
                }
                resetUnlockStateOnFailure();
                return;
            } else {
                EventLog.logUnlockEvent("RECEIVED_DEVICE_ID");
                sendAckToClient(false);
                this.mCurrentUnlockState = 1;
                return;
            }
        }
        if (i == 1) {
            try {
                processKeyExchangeHandshakeMessage(value);
                return;
            } catch (HandshakeException e) {
                Log.e(TAG, "Handshake failure", e);
                resetUnlockStateOnFailure();
                return;
            }
        }
        if (i != 2) {
            if (i == 3) {
                Log.e(TAG, "Landed on unexpected state of credentials received.");
                return;
            }
            Log.e(TAG, "Encountered unexpected unlock state: " + this.mCurrentUnlockState);
            return;
        }
        Key key = this.mEncryptionKey;
        if (key == null) {
            Log.e(TAG, "Current session key null. Unexpected at this stage: " + this.mCurrentUnlockState);
            this.mCarCompanionDeviceStorage.clearEncryptionKey(this.mClientDeviceId);
            resetUnlockStateOnFailure();
            return;
        }
        this.mCarCompanionDeviceStorage.saveEncryptionKey(this.mClientDeviceId, key.asBytes());
        try {
            byte[] decryptedCredentials = this.mEncryptionKey.decryptData(value);
            processCredentials(decryptedCredentials);
            this.mCurrentUnlockState = 3;
            EventLog.logUnlockEvent("UNLOCK_CREDENTIALS_RECEIVED");
            sendAckToClient(true);
        } catch (SignatureException e2) {
            Log.e(TAG, "Could not decrypt phone credentials.", e2);
            resetUnlockStateOnFailure();
        }
    }

    private void sendAckToClient(boolean isEncrypted) {
        byte[] ack = isEncrypted ? this.mEncryptionKey.encryptData(ACKNOWLEDGEMENT_MESSAGE) : ACKNOWLEDGEMENT_MESSAGE;
        this.mCarTrustAgentBleManager.sendMessage(ack, BLEOperationProto.OperationType.CLIENT_MESSAGE, isEncrypted, this.mSendMessageCallback);
    }

    private String convertToDeviceId(byte[] id) {
        UUID deviceId = Utils.bytesToUUID(id);
        if (deviceId == null || this.mCarCompanionDeviceStorage.getEncryptionKey(deviceId.toString()) == null) {
            if (deviceId != null) {
                Log.e(TAG, "Unknown phone connected: " + deviceId.toString());
                return null;
            }
            return null;
        }
        return deviceId.toString();
    }

    private void processKeyExchangeHandshakeMessage(byte[] message) throws HandshakeException {
        int i = this.mEncryptionState;
        if (i == 0) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Responding to handshake init request.");
            }
            HandshakeMessage handshakeMessage = this.mEncryptionRunner.respondToInitRequest(message);
            this.mEncryptionState = handshakeMessage.getHandshakeState();
            this.mCarTrustAgentBleManager.sendMessage(handshakeMessage.getNextMessage(), BLEOperationProto.OperationType.ENCRYPTION_HANDSHAKE, false, this.mSendMessageCallback);
            EventLog.logUnlockEvent("UNLOCK_ENCRYPTION_STATE", this.mEncryptionState);
            return;
        }
        if (i == 1) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Continuing handshake.");
            }
            int handshakeState = this.mEncryptionRunner.continueHandshake(message).getHandshakeState();
            this.mEncryptionState = handshakeState;
            EventLog.logUnlockEvent("UNLOCK_ENCRYPTION_STATE", handshakeState);
            if (this.mEncryptionState != 5) {
                Log.e(TAG, "Handshake did not went to resume session after calling verify PIN. Instead got state: " + this.mEncryptionState);
                resetUnlockStateOnFailure();
                return;
            }
            EventLog.logUnlockEvent("WAITING_FOR_CLIENT_AUTH");
            return;
        }
        if (i == 5) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Start reconnection authentication.");
            }
            String str = this.mClientDeviceId;
            if (str == null) {
                resetUnlockStateOnFailure();
                return;
            }
            HandshakeMessage handshakeMessage2 = this.mEncryptionRunner.authenticateReconnection(message, this.mCarCompanionDeviceStorage.getEncryptionKey(str));
            this.mEncryptionKey = handshakeMessage2.getKey();
            int handshakeState2 = handshakeMessage2.getHandshakeState();
            this.mEncryptionState = handshakeState2;
            EventLog.logUnlockEvent("UNLOCK_ENCRYPTION_STATE", handshakeState2);
            if (this.mEncryptionState != 3) {
                resetUnlockStateOnFailure();
                return;
            }
            this.mCurrentUnlockState = 2;
            sendServerAuthToClient(handshakeMessage2.getNextMessage());
            EventLog.logUnlockEvent("CLIENT_AUTHENTICATED");
            return;
        }
        Log.w(TAG, "Encountered invalid handshake state: " + this.mEncryptionState);
    }

    private void sendServerAuthToClient(byte[] resumeBytes) {
        this.mCarTrustAgentBleManager.sendMessage(resumeBytes, BLEOperationProto.OperationType.CLIENT_MESSAGE, false, this.mSendMessageCallback);
    }

    void processCredentials(byte[] credentials) {
        if (this.mUnlockDelegate == null) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "No Unlock delegate to notify of unlock credentials.");
                return;
            }
            return;
        }
        queueMessageForLog("processCredentials");
        try {
            PhoneAuthProto.PhoneCredentials phoneCredentials = PhoneAuthProto.PhoneCredentials.parseFrom(credentials);
            byte[] handle = phoneCredentials.getHandle().toByteArray();
            this.mUnlockDelegate.onUnlockDataReceived(this.mCarCompanionDeviceStorage.getUserHandleByTokenHandle(Utils.bytesToLong(handle)), phoneCredentials.getEscrowToken().toByteArray(), Utils.bytesToLong(handle));
        } catch (InvalidProtocolBufferException e) {
            Log.e(TAG, "Error parsing credentials protobuf.", e);
        }
    }

    private void resetUnlockStateOnFailure() {
        this.mCarTrustAgentBleManager.disconnectRemoteDevice();
        resetEncryptionState();
    }

    private void resetEncryptionState() {
        EncryptionRunner encryptionRunnerNewRunner = EncryptionRunnerFactory.newRunner();
        this.mEncryptionRunner = encryptionRunnerNewRunner;
        encryptionRunnerNewRunner.setIsReconnect(true);
        this.mEncryptionKey = null;
        this.mEncryptionState = 0;
        this.mCurrentUnlockState = 0;
    }

    void dump(PrintWriter writer) {
        writer.println("*CarTrustAgentUnlockService*");
        writer.println("Unlock Service Logs:");
        for (String log : this.mLogQueue) {
            writer.println("\t" + log);
        }
    }

    private void queueMessageForLog(String message) {
        if (this.mLogQueue.size() >= 20) {
            this.mLogQueue.remove();
        }
        this.mLogQueue.add(System.currentTimeMillis() + " : " + message);
    }
}
