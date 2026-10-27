package com.android.car.trust;

import android.app.ActivityManager;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseSettings;
import android.car.encryptionrunner.EncryptionRunner;
import android.car.encryptionrunner.EncryptionRunnerFactory;
import android.car.encryptionrunner.HandshakeException;
import android.car.encryptionrunner.HandshakeMessage;
import android.car.encryptionrunner.Key;
import android.car.trust.ICarTrustAgentBleCallback;
import android.car.trust.ICarTrustAgentEnrollment;
import android.car.trust.ICarTrustAgentEnrollmentCallback;
import android.car.trust.TrustedDeviceInfo;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.RemoteException;
import android.sysprop.CarProperties;
import android.util.Log;
import com.android.car.BLEStreamProtos.BLEOperationProto;
import com.android.car.ICarImpl;
import com.android.car.R;
import com.android.car.Utils;
import com.android.car.trust.CarTrustAgentBleManager;
import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.UUID;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public class CarTrustAgentEnrollmentService extends ICarTrustAgentEnrollment.Stub implements CarTrustAgentBleManager.DataReceivedListener {
    static final byte[] CONFIRMATION_SIGNAL = "True".getBytes();
    private static final char DEVICE_INFO_DELIMITER = '#';
    private static final int DEVICE_NAME_LENGTH_LIMIT = 8;
    private static final int DEVICE_NAME_PREFIX_LIMIT = 4;
    static final int ENROLLMENT_STATE_ENCRYPTION_COMPLETED = 2;
    static final int ENROLLMENT_STATE_HANDLE = 3;
    static final int ENROLLMENT_STATE_NONE = 0;
    static final int ENROLLMENT_STATE_UNIQUE_ID = 1;
    private static final int MAX_LOG_SIZE = 20;
    private static final String TAG = "CarTrustAgentEnroll";
    private static final String TRUSTED_DEVICE_ENROLLMENT_ENABLED_KEY = "trusted_device_enrollment_enabled";
    private final CarCompanionDeviceStorage mCarCompanionDeviceStorage;
    private final CarTrustAgentBleManager mCarTrustAgentBleManager;
    private String mClientDeviceId;
    private String mClientDeviceName;
    private final Context mContext;
    private Key mEncryptionKey;
    private final UUID mEnrollmentClientWriteUuid;
    private CarTrustAgentEnrollmentRequestDelegate mEnrollmentDelegate;
    private String mEnrollmentDeviceName;
    int mEnrollmentState;
    private long mHandle;
    private HandshakeMessage mHandshakeMessage;
    private BluetoothDevice mRemoteEnrollmentDevice;
    private final CarTrustedDeviceService mTrustedDeviceService;
    private final List<EnrollmentStateClient> mEnrollmentStateClients = new ArrayList();
    private final List<BleStateChangeClient> mBleStateChangeClients = new ArrayList();
    private final Queue<String> mLogQueue = new LinkedList();
    private Object mRemoteDeviceLock = new Object();
    private final Map<Long, Boolean> mTokenActiveStateMap = new HashMap();
    private CarTrustAgentBleManager.SendMessageCallback mSendMessageCallback = new CarTrustAgentBleManager.SendMessageCallback() { // from class: com.android.car.trust.-$$Lambda$Z5HWTXwXoYZuJPM1dYFUEwq4_7g
        @Override // com.android.car.trust.CarTrustAgentBleManager.SendMessageCallback
        public final void onSendMessageFailure() {
            this.f$0.terminateEnrollmentHandshake();
        }
    };
    private EncryptionRunner mEncryptionRunner = EncryptionRunnerFactory.newRunner();
    int mEncryptionState = 0;

    interface CarTrustAgentEnrollmentRequestDelegate {
        void addEscrowToken(byte[] bArr, int i);

        void isEscrowTokenActive(long j, int i);

        void removeEscrowToken(long j, int i);
    }

    @Retention(RetentionPolicy.SOURCE)
    @interface EnrollmentState {
    }

    public CarTrustAgentEnrollmentService(Context context, CarTrustedDeviceService service, CarTrustAgentBleManager bleService) {
        this.mContext = context;
        this.mTrustedDeviceService = service;
        this.mCarTrustAgentBleManager = bleService;
        this.mEnrollmentClientWriteUuid = UUID.fromString(context.getString(R.string.enrollment_client_write_uuid));
        this.mCarCompanionDeviceStorage = new CarCompanionDeviceStorage(context);
    }

    public synchronized void init() {
        this.mCarTrustAgentBleManager.setupEnrollmentBleServer();
        this.mCarTrustAgentBleManager.addDataReceivedListener(this.mEnrollmentClientWriteUuid, this);
    }

    void setEncryptionRunner(EncryptionRunner dummyEncryptionRunner) {
        this.mEncryptionRunner = dummyEncryptionRunner;
    }

    public synchronized void release() {
        for (EnrollmentStateClient client : this.mEnrollmentStateClients) {
            client.mListenerBinder.unlinkToDeath(client, 0);
        }
        for (BleStateChangeClient client2 : this.mBleStateChangeClients) {
            client2.mListenerBinder.unlinkToDeath(client2, 0);
        }
        this.mEnrollmentStateClients.clear();
    }

    public void startEnrollmentAdvertising() {
        ICarImpl.assertTrustAgentEnrollmentPermission(this.mContext);
        if (!this.mCarCompanionDeviceStorage.getSharedPrefs().getBoolean(TRUSTED_DEVICE_ENROLLMENT_ENABLED_KEY, true)) {
            Log.e(TAG, "Trusted Device Enrollment disabled");
            dispatchEnrollmentFailure(2);
            return;
        }
        this.mTrustedDeviceService.getCarTrustAgentUnlockService().stopUnlockAdvertising();
        stopEnrollmentAdvertising();
        EventLog.logEnrollmentEvent("START_ENROLLMENT_ADVERTISING");
        addEnrollmentServiceLog("startEnrollmentAdvertising");
        this.mCarTrustAgentBleManager.startEnrollmentAdvertising(getEnrollmentDeviceName(), new AdvertiseCallback() { // from class: com.android.car.trust.CarTrustAgentEnrollmentService.1
            @Override // android.bluetooth.le.AdvertiseCallback
            public void onStartSuccess(AdvertiseSettings settingsInEffect) {
                super.onStartSuccess(settingsInEffect);
                CarTrustAgentEnrollmentService.this.onEnrollmentAdvertiseStartSuccess();
                if (Log.isLoggable(CarTrustAgentEnrollmentService.TAG, 3)) {
                    Log.d(CarTrustAgentEnrollmentService.TAG, "Successfully started advertising service");
                }
            }

            @Override // android.bluetooth.le.AdvertiseCallback
            public void onStartFailure(int errorCode) {
                super.onStartFailure(errorCode);
                Log.e(CarTrustAgentEnrollmentService.TAG, "Failed to advertise, errorCode: " + errorCode);
                CarTrustAgentEnrollmentService.this.onEnrollmentAdvertiseStartFailure();
            }
        });
        this.mEnrollmentState = 0;
    }

    public void stopEnrollmentAdvertising() {
        ICarImpl.assertTrustAgentEnrollmentPermission(this.mContext);
        EventLog.logEnrollmentEvent("STOP_ENROLLMENT_ADVERTISING");
        addEnrollmentServiceLog("stopEnrollmentAdvertising");
        this.mCarTrustAgentBleManager.stopEnrollmentAdvertising();
    }

    public void enrollmentHandshakeAccepted(BluetoothDevice device) {
        ICarImpl.assertTrustAgentEnrollmentPermission(this.mContext);
        EventLog.logEnrollmentEvent("ENROLLMENT_HANDSHAKE_ACCEPTED");
        addEnrollmentServiceLog("enrollmentHandshakeAccepted");
        if (device == null || !device.equals(this.mRemoteEnrollmentDevice)) {
            Log.wtf(TAG, "Enrollment Failure: device is different from cached remote bluetooth device, disconnect from the device. current device is:" + device);
            this.mCarTrustAgentBleManager.disconnectRemoteDevice();
            return;
        }
        this.mCarTrustAgentBleManager.sendMessage(CONFIRMATION_SIGNAL, BLEOperationProto.OperationType.ENCRYPTION_HANDSHAKE, false, this.mSendMessageCallback);
        setEnrollmentHandshakeAccepted();
    }

    public void terminateEnrollmentHandshake() {
        ICarImpl.assertTrustAgentEnrollmentPermission(this.mContext);
        addEnrollmentServiceLog("terminateEnrollmentHandshake");
        this.mCarTrustAgentBleManager.disconnectRemoteDevice();
        Iterator<Map.Entry<Long, Boolean>> it = this.mTokenActiveStateMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Long, Boolean> pair = it.next();
            boolean isHandleActive = pair.getValue().booleanValue();
            if (!isHandleActive) {
                long handle = pair.getKey().longValue();
                int uid = this.mCarCompanionDeviceStorage.getSharedPrefs().getInt(String.valueOf(handle), -1);
                removeEscrowToken(handle, uid);
                it.remove();
            }
        }
    }

    public boolean isEscrowTokenActive(long handle, int uid) {
        ICarImpl.assertTrustAgentEnrollmentPermission(this.mContext);
        if (this.mTokenActiveStateMap.get(Long.valueOf(handle)) != null) {
            return this.mTokenActiveStateMap.get(Long.valueOf(handle)).booleanValue();
        }
        return false;
    }

    public void removeEscrowToken(long handle, int uid) {
        ICarImpl.assertTrustAgentEnrollmentPermission(this.mContext);
        this.mEnrollmentDelegate.removeEscrowToken(handle, uid);
        addEnrollmentServiceLog("removeEscrowToken (handle:" + handle + " uid:" + uid + ")");
    }

    public void removeAllTrustedDevices(int uid) {
        ICarImpl.assertTrustAgentEnrollmentPermission(this.mContext);
        for (TrustedDeviceInfo device : getEnrolledDeviceInfosForUser(uid)) {
            removeEscrowToken(device.getHandle(), uid);
        }
    }

    public void setTrustedDeviceEnrollmentEnabled(boolean isEnabled) {
        ICarImpl.assertTrustAgentEnrollmentPermission(this.mContext);
        SharedPreferences.Editor editor = this.mCarCompanionDeviceStorage.getSharedPrefs().edit();
        editor.putBoolean(TRUSTED_DEVICE_ENROLLMENT_ENABLED_KEY, isEnabled);
        if (!editor.commit()) {
            Log.wtf(TAG, "Enrollment Failure: Commit to SharedPreferences failed. Enable? " + isEnabled);
        }
    }

    public void setTrustedDeviceUnlockEnabled(boolean isEnabled) {
        ICarImpl.assertTrustAgentEnrollmentPermission(this.mContext);
        this.mTrustedDeviceService.getCarTrustAgentUnlockService().setTrustedDeviceUnlockEnabled(isEnabled);
    }

    public List<TrustedDeviceInfo> getEnrolledDeviceInfosForUser(int uid) {
        ICarImpl.assertTrustAgentEnrollmentPermission(this.mContext);
        Set<String> enrolledDeviceInfos = this.mCarCompanionDeviceStorage.getSharedPrefs().getStringSet(String.valueOf(uid), new HashSet());
        List<TrustedDeviceInfo> trustedDeviceInfos = new ArrayList<>(enrolledDeviceInfos.size());
        for (String deviceInfoWithId : enrolledDeviceInfos) {
            TrustedDeviceInfo deviceInfo = extractDeviceInfo(deviceInfoWithId);
            if (deviceInfo != null) {
                trustedDeviceInfos.add(deviceInfo);
            }
        }
        return trustedDeviceInfos;
    }

    public synchronized void registerEnrollmentCallback(ICarTrustAgentEnrollmentCallback listener) {
        ICarImpl.assertTrustAgentEnrollmentPermission(this.mContext);
        if (listener == null) {
            throw new IllegalArgumentException("Listener is null");
        }
        if (findEnrollmentStateClientLocked(listener) == null) {
            EnrollmentStateClient client = new EnrollmentStateClient(listener);
            try {
                listener.asBinder().linkToDeath(client, 0);
                this.mEnrollmentStateClients.add(client);
            } catch (RemoteException e) {
                Log.e(TAG, "Cannot link death recipient to binder ", e);
            }
        }
    }

    void onEscrowTokenAdded(byte[] token, long handle, int uid) {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "onEscrowTokenAdded handle:" + handle + " uid:" + uid);
        }
        if (this.mRemoteEnrollmentDevice == null) {
            Log.e(TAG, "onEscrowTokenAdded() but no remote device connected!");
            removeEscrowToken(handle, uid);
            dispatchEnrollmentFailure(1);
            return;
        }
        this.mTokenActiveStateMap.put(Long.valueOf(handle), false);
        for (EnrollmentStateClient client : this.mEnrollmentStateClients) {
            try {
                client.mListener.onEscrowTokenAdded(handle);
            } catch (RemoteException e) {
                Log.e(TAG, "onEscrowTokenAdded dispatch failed", e);
            }
        }
    }

    void onEscrowTokenRemoved(long handle, int uid) {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "onEscrowTokenRemoved handle:" + handle + " uid:" + uid);
        }
        for (EnrollmentStateClient client : this.mEnrollmentStateClients) {
            try {
                client.mListener.onEscrowTokenRemoved(handle);
            } catch (RemoteException e) {
                Log.e(TAG, "onEscrowTokenRemoved dispatch failed", e);
            }
        }
        SharedPreferences sharedPrefs = this.mCarCompanionDeviceStorage.getSharedPrefs();
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.remove(String.valueOf(handle));
        Set<String> deviceInfos = sharedPrefs.getStringSet(String.valueOf(uid), new HashSet());
        Iterator<String> iterator = deviceInfos.iterator();
        while (true) {
            if (!iterator.hasNext()) {
                break;
            }
            String deviceIdAndInfo = iterator.next();
            TrustedDeviceInfo info = extractDeviceInfo(deviceIdAndInfo);
            if (info != null && info.getHandle() == handle) {
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "Removing trusted device: " + info);
                }
                String clientDeviceId = extractDeviceId(deviceIdAndInfo);
                if (clientDeviceId != null && sharedPrefs.getLong(clientDeviceId, -1L) == handle) {
                    editor.remove(clientDeviceId);
                }
                iterator.remove();
            }
        }
        editor.putStringSet(String.valueOf(uid), deviceInfos);
        if (!editor.commit()) {
            Log.e(TAG, "EscrowToken removed, but shared prefs update failed");
        }
    }

    void onEscrowTokenActiveStateChanged(long handle, boolean isTokenActive, int uid) {
        String clientDeviceName;
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "onEscrowTokenActiveStateChanged: " + Long.toHexString(handle));
        }
        if (this.mRemoteEnrollmentDevice == null || !isTokenActive) {
            if (this.mRemoteEnrollmentDevice == null) {
                Log.e(TAG, "Device disconnected before sending back handle.  Enrollment incomplete");
            }
            if (!isTokenActive) {
                Log.e(TAG, "Unexpected: Escrow Token activation failed");
            }
            removeEscrowToken(handle, uid);
            dispatchEnrollmentFailure(1);
            return;
        }
        SharedPreferences sharedPrefs = this.mCarCompanionDeviceStorage.getSharedPrefs();
        if (sharedPrefs.contains(this.mClientDeviceId)) {
            removeEscrowToken(sharedPrefs.getLong(this.mClientDeviceId, -1L), uid);
        }
        this.mTokenActiveStateMap.put(Long.valueOf(handle), Boolean.valueOf(isTokenActive));
        Set<String> deviceInfo = sharedPrefs.getStringSet(String.valueOf(uid), new HashSet());
        if (this.mRemoteEnrollmentDevice.getName() != null) {
            clientDeviceName = this.mRemoteEnrollmentDevice.getName();
        } else {
            String clientDeviceName2 = this.mClientDeviceName;
            if (clientDeviceName2 != null) {
                clientDeviceName = this.mClientDeviceName;
            } else {
                clientDeviceName = this.mContext.getString(R.string.trust_device_default_name);
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("trustedDeviceAdded (id:");
        stringBuffer.append(this.mClientDeviceId);
        stringBuffer.append(", handle:");
        stringBuffer.append(handle);
        stringBuffer.append(", uid:");
        stringBuffer.append(uid);
        stringBuffer.append(", addr:");
        stringBuffer.append(this.mRemoteEnrollmentDevice.getAddress());
        stringBuffer.append(", name:");
        stringBuffer.append(clientDeviceName);
        StringBuffer log = stringBuffer.append(")");
        addEnrollmentServiceLog(log.toString());
        deviceInfo.add(serializeDeviceInfoWithId(new TrustedDeviceInfo(handle, this.mRemoteEnrollmentDevice.getAddress(), clientDeviceName), this.mClientDeviceId));
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putStringSet(String.valueOf(uid), deviceInfo);
        if (!editor.commit()) {
            Log.e(TAG, "Writing DeviceInfo to shared prefs Failed");
            removeEscrowToken(handle, uid);
            dispatchEnrollmentFailure(1);
            return;
        }
        editor.putInt(String.valueOf(handle), uid);
        if (!editor.commit()) {
            Log.e(TAG, "Writing (handle, uid) to shared prefs Failed");
            removeEscrowToken(handle, uid);
            dispatchEnrollmentFailure(1);
            return;
        }
        editor.putLong(this.mClientDeviceId, handle);
        if (!editor.commit()) {
            Log.e(TAG, "Writing (identifier, handle) to shared prefs Failed");
            removeEscrowToken(handle, uid);
            dispatchEnrollmentFailure(1);
            return;
        }
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "Sending handle: " + handle);
        }
        this.mHandle = handle;
        this.mCarTrustAgentBleManager.sendMessage(this.mEncryptionKey.encryptData(Utils.longToBytes(handle)), BLEOperationProto.OperationType.CLIENT_MESSAGE, true, this.mSendMessageCallback);
    }

    void onEnrollmentAdvertiseStartSuccess() {
        for (BleStateChangeClient client : this.mBleStateChangeClients) {
            try {
                client.mListener.onEnrollmentAdvertisingStarted();
            } catch (RemoteException e) {
                Log.e(TAG, "onAdvertiseSuccess dispatch failed", e);
            }
        }
    }

    void onEnrollmentAdvertiseStartFailure() {
        for (BleStateChangeClient client : this.mBleStateChangeClients) {
            try {
                client.mListener.onEnrollmentAdvertisingFailed();
            } catch (RemoteException e) {
                Log.e(TAG, "onAdvertiseSuccess dispatch failed", e);
            }
        }
    }

    void onRemoteDeviceConnected(BluetoothDevice device) {
        EventLog.logEnrollmentEvent("REMOTE_DEVICE_CONNECTED");
        addEnrollmentServiceLog("onRemoteDeviceConnected (addr:" + device.getAddress() + ")");
        resetEncryptionState();
        this.mHandle = 0L;
        synchronized (this.mRemoteDeviceLock) {
            this.mRemoteEnrollmentDevice = device;
        }
        for (BleStateChangeClient client : this.mBleStateChangeClients) {
            try {
                client.mListener.onBleEnrollmentDeviceConnected(device);
            } catch (RemoteException e) {
                Log.e(TAG, "onRemoteDeviceConnected dispatch failed", e);
            }
        }
        this.mCarTrustAgentBleManager.stopEnrollmentAdvertising();
    }

    void onRemoteDeviceDisconnected(BluetoothDevice device) {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "Device Disconnected: " + device.getAddress() + " Enrollment State: " + this.mEnrollmentState + " Encryption State: " + this.mEncryptionState);
        }
        addEnrollmentServiceLog("onRemoteDeviceDisconnected (addr:" + device.getAddress() + ")");
        addEnrollmentServiceLog("Enrollment State: " + this.mEnrollmentState + " EncryptionState: " + this.mEncryptionState);
        resetEncryptionState();
        this.mHandle = 0L;
        synchronized (this.mRemoteDeviceLock) {
            this.mRemoteEnrollmentDevice = null;
        }
        for (BleStateChangeClient client : this.mBleStateChangeClients) {
            try {
                client.mListener.onBleEnrollmentDeviceDisconnected(device);
            } catch (RemoteException e) {
                Log.e(TAG, "onRemoteDeviceDisconnected dispatch failed", e);
            }
        }
    }

    @Override // com.android.car.trust.CarTrustAgentBleManager.DataReceivedListener
    public void onDataReceived(byte[] value) {
        if (this.mEnrollmentDelegate == null) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Enrollment Delegate not set");
                return;
            }
            return;
        }
        int i = this.mEnrollmentState;
        if (i == 0) {
            if (!CarTrustAgentValidator.isValidEnrollmentDeviceId(value)) {
                Log.e(TAG, "Device id rejected by validator.");
                return;
            } else {
                notifyDeviceIdReceived(value);
                EventLog.logEnrollmentEvent("RECEIVED_DEVICE_ID");
                return;
            }
        }
        if (i == 1) {
            try {
                processInitEncryptionMessage(value);
                return;
            } catch (HandshakeException e) {
                Log.e(TAG, "HandshakeException during set up of encryption: ", e);
                return;
            }
        }
        if (i == 2) {
            notifyEscrowTokenReceived(value);
        } else if (i == 3) {
            dispatchEscrowTokenActiveStateChanged(this.mHandle, true);
            this.mCarTrustAgentBleManager.disconnectRemoteDevice();
        }
    }

    void onClientDeviceNameRetrieved(String deviceName) {
        this.mClientDeviceName = deviceName;
    }

    private void notifyDeviceIdReceived(byte[] id) {
        UUID deviceId = Utils.bytesToUUID(id);
        if (deviceId == null) {
            Log.e(TAG, "Invalid device id sent");
            return;
        }
        this.mClientDeviceId = deviceId.toString();
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "Received device id: " + this.mClientDeviceId);
        }
        UUID uniqueId = this.mCarCompanionDeviceStorage.getUniqueId();
        if (uniqueId == null) {
            Log.e(TAG, "Cannot get Unique ID for the IHU");
            resetEnrollmentStateOnFailure();
            dispatchEnrollmentFailure(1);
            return;
        }
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "Sending device id: " + uniqueId.toString());
        }
        this.mCarTrustAgentBleManager.sendMessage(Utils.uuidToBytes(uniqueId), BLEOperationProto.OperationType.CLIENT_MESSAGE, false, this.mSendMessageCallback);
        this.mEnrollmentState++;
    }

    private void notifyEscrowTokenReceived(byte[] token) {
        try {
            this.mEnrollmentDelegate.addEscrowToken(this.mEncryptionKey.decryptData(token), ActivityManager.getCurrentUser());
            this.mEnrollmentState++;
            EventLog.logEnrollmentEvent("ESCROW_TOKEN_ADDED");
        } catch (SignatureException e) {
            Log.e(TAG, "Could not decrypt escrow token", e);
        }
    }

    private void processInitEncryptionMessage(byte[] message) throws HandshakeException {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "Processing init encryption message.");
        }
        int i = this.mEncryptionState;
        if (i == 0) {
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "Responding to handshake init request.");
            }
            HandshakeMessage handshakeMessageRespondToInitRequest = this.mEncryptionRunner.respondToInitRequest(message);
            this.mHandshakeMessage = handshakeMessageRespondToInitRequest;
            this.mEncryptionState = handshakeMessageRespondToInitRequest.getHandshakeState();
            this.mCarTrustAgentBleManager.sendMessage(this.mHandshakeMessage.getNextMessage(), BLEOperationProto.OperationType.ENCRYPTION_HANDSHAKE, false, this.mSendMessageCallback);
            EventLog.logEnrollmentEvent("ENROLLMENT_ENCRYPTION_STATE", this.mEncryptionState);
            return;
        }
        if (i != 1) {
            if (i == 2) {
                Log.w(TAG, "Encountered VERIFICATION_NEEDED state when it should have been transitioned to after IN_PROGRESS.");
                showVerificationCode();
                return;
            } else {
                if (i == 3) {
                    notifyEscrowTokenReceived(message);
                    return;
                }
                Log.w(TAG, "Encountered invalid handshake state: " + this.mEncryptionState);
                return;
            }
        }
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "Continuing handshake.");
        }
        HandshakeMessage handshakeMessageContinueHandshake = this.mEncryptionRunner.continueHandshake(message);
        this.mHandshakeMessage = handshakeMessageContinueHandshake;
        this.mEncryptionState = handshakeMessageContinueHandshake.getHandshakeState();
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "Updated encryption state: " + this.mEncryptionState);
        }
        if (this.mEncryptionState == 2) {
            showVerificationCode();
        } else {
            this.mCarTrustAgentBleManager.sendMessage(this.mHandshakeMessage.getNextMessage(), BLEOperationProto.OperationType.ENCRYPTION_HANDSHAKE, false, this.mSendMessageCallback);
        }
    }

    private void showVerificationCode() {
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "showVerificationCode(): " + this.mHandshakeMessage.getVerificationCode());
        }
        for (EnrollmentStateClient client : this.mEnrollmentStateClients) {
            try {
                client.mListener.onAuthStringAvailable(this.mRemoteEnrollmentDevice, this.mHandshakeMessage.getVerificationCode());
            } catch (RemoteException e) {
                Log.e(TAG, "Broadcast verification code failed", e);
            }
        }
        EventLog.logEnrollmentEvent("SHOW_VERIFICATION_CODE");
    }

    private void resetEnrollmentStateOnFailure() {
        terminateEnrollmentHandshake();
        resetEncryptionState();
    }

    private void resetEncryptionState() {
        this.mEncryptionRunner = EncryptionRunnerFactory.newRunner();
        this.mHandshakeMessage = null;
        this.mEncryptionKey = null;
        this.mEncryptionState = 0;
        this.mEnrollmentState = 0;
    }

    private synchronized void setEnrollmentHandshakeAccepted() {
        if (this.mEncryptionRunner == null) {
            Log.e(TAG, "Received notification that enrollment handshake was accepted, but encryption was never set up.");
            return;
        }
        try {
            HandshakeMessage message = this.mEncryptionRunner.verifyPin();
            if (message.getHandshakeState() != 3) {
                Log.e(TAG, "Handshake not finished after calling verify PIN. Instead got state: " + message.getHandshakeState());
                return;
            }
            this.mEncryptionState = 3;
            Key key = message.getKey();
            this.mEncryptionKey = key;
            if (!this.mCarCompanionDeviceStorage.saveEncryptionKey(this.mClientDeviceId, key.asBytes())) {
                resetEnrollmentStateOnFailure();
                dispatchEnrollmentFailure(1);
            } else {
                EventLog.logEnrollmentEvent("ENCRYPTION_KEY_SAVED");
                this.mEnrollmentState++;
            }
        } catch (HandshakeException e) {
            Log.e(TAG, "Error during PIN verification", e);
            resetEnrollmentStateOnFailure();
            dispatchEnrollmentFailure(1);
        }
    }

    private EnrollmentStateClient findEnrollmentStateClientLocked(ICarTrustAgentEnrollmentCallback listener) {
        IBinder binder = listener.asBinder();
        for (EnrollmentStateClient client : this.mEnrollmentStateClients) {
            if (client.isHoldingBinder(binder)) {
                return client;
            }
        }
        return null;
    }

    public synchronized void unregisterEnrollmentCallback(ICarTrustAgentEnrollmentCallback listener) {
        ICarImpl.assertTrustAgentEnrollmentPermission(this.mContext);
        if (listener == null) {
            throw new IllegalArgumentException("Listener is null");
        }
        EnrollmentStateClient client = findEnrollmentStateClientLocked(listener);
        if (client == null) {
            Log.e(TAG, "unregisterEnrollmentCallback(): listener was not previously registered");
        } else {
            listener.asBinder().unlinkToDeath(client, 0);
            this.mEnrollmentStateClients.remove(client);
        }
    }

    public synchronized void registerBleCallback(ICarTrustAgentBleCallback listener) {
        ICarImpl.assertTrustAgentEnrollmentPermission(this.mContext);
        if (listener == null) {
            throw new IllegalArgumentException("Listener is null");
        }
        if (findBleStateClientLocked(listener) == null) {
            BleStateChangeClient client = new BleStateChangeClient(listener);
            try {
                listener.asBinder().linkToDeath(client, 0);
                this.mBleStateChangeClients.add(client);
            } catch (RemoteException e) {
                Log.e(TAG, "Cannot link death recipient to binder " + e);
            }
        }
    }

    private BleStateChangeClient findBleStateClientLocked(ICarTrustAgentBleCallback listener) {
        IBinder binder = listener.asBinder();
        for (BleStateChangeClient client : this.mBleStateChangeClients) {
            if (client.isHoldingBinder(binder)) {
                return client;
            }
        }
        return null;
    }

    public synchronized void unregisterBleCallback(ICarTrustAgentBleCallback listener) {
        ICarImpl.assertTrustAgentEnrollmentPermission(this.mContext);
        if (listener == null) {
            throw new IllegalArgumentException("Listener is null");
        }
        BleStateChangeClient client = findBleStateClientLocked(listener);
        if (client == null) {
            Log.e(TAG, "unregisterBleCallback(): listener was not previously registered");
        } else {
            listener.asBinder().unlinkToDeath(client, 0);
            this.mBleStateChangeClients.remove(client);
        }
    }

    void setEnrollmentRequestDelegate(CarTrustAgentEnrollmentRequestDelegate delegate) {
        this.mEnrollmentDelegate = delegate;
    }

    void dump(PrintWriter writer) {
        writer.println("*CarTrustAgentEnrollmentService*");
        writer.println("Enrollment Service Logs:");
        for (String log : this.mLogQueue) {
            writer.println("\t" + log);
        }
    }

    private void addEnrollmentServiceLog(String message) {
        if (this.mLogQueue.size() >= 20) {
            this.mLogQueue.remove();
        }
        this.mLogQueue.add(System.currentTimeMillis() + " : " + message);
    }

    private void dispatchEscrowTokenActiveStateChanged(long handle, boolean active) {
        for (EnrollmentStateClient client : this.mEnrollmentStateClients) {
            try {
                client.mListener.onEscrowTokenActiveStateChanged(handle, active);
            } catch (RemoteException e) {
                Log.e(TAG, "Cannot notify client of a Token Activation change: " + active);
            }
        }
    }

    private void dispatchEnrollmentFailure(int error) {
        for (EnrollmentStateClient client : this.mEnrollmentStateClients) {
            try {
                client.mListener.onEnrollmentHandshakeFailure((BluetoothDevice) null, error);
            } catch (RemoteException e) {
                Log.e(TAG, "onEnrollmentHandshakeFailure dispatch failed", e);
            }
        }
    }

    private static TrustedDeviceInfo extractDeviceInfo(String deviceInfoWithId) {
        int delimiterIndex = deviceInfoWithId.indexOf(35);
        if (delimiterIndex < 0) {
            return null;
        }
        return TrustedDeviceInfo.deserialize(deviceInfoWithId.substring(delimiterIndex + 1));
    }

    private static String extractDeviceId(String deviceInfoWithId) {
        int delimiterIndex = deviceInfoWithId.indexOf(35);
        if (delimiterIndex < 0) {
            return null;
        }
        return deviceInfoWithId.substring(0, delimiterIndex);
    }

    private static String serializeDeviceInfoWithId(TrustedDeviceInfo info, String id) {
        return id + DEVICE_INFO_DELIMITER + info.serialize();
    }

    private class EnrollmentStateClient implements IBinder.DeathRecipient {
        private final ICarTrustAgentEnrollmentCallback mListener;
        private final IBinder mListenerBinder;

        EnrollmentStateClient(ICarTrustAgentEnrollmentCallback listener) {
            this.mListener = listener;
            this.mListenerBinder = listener.asBinder();
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            if (Log.isLoggable(CarTrustAgentEnrollmentService.TAG, 3)) {
                Log.d(CarTrustAgentEnrollmentService.TAG, "Binder died " + this.mListenerBinder);
            }
            this.mListenerBinder.unlinkToDeath(this, 0);
            synchronized (CarTrustAgentEnrollmentService.this) {
                CarTrustAgentEnrollmentService.this.mEnrollmentStateClients.remove(this);
            }
        }

        public boolean isHoldingBinder(IBinder binder) {
            return this.mListenerBinder == binder;
        }
    }

    private class BleStateChangeClient implements IBinder.DeathRecipient {
        private final ICarTrustAgentBleCallback mListener;
        private final IBinder mListenerBinder;

        BleStateChangeClient(ICarTrustAgentBleCallback listener) {
            this.mListener = listener;
            this.mListenerBinder = listener.asBinder();
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            if (Log.isLoggable(CarTrustAgentEnrollmentService.TAG, 3)) {
                Log.d(CarTrustAgentEnrollmentService.TAG, "Binder died " + this.mListenerBinder);
            }
            this.mListenerBinder.unlinkToDeath(this, 0);
            synchronized (CarTrustAgentEnrollmentService.this) {
                CarTrustAgentEnrollmentService.this.mBleStateChangeClients.remove(this);
            }
        }

        public boolean isHoldingBinder(IBinder binder) {
            return this.mListenerBinder == binder;
        }

        public void onEnrollmentAdvertisementStarted() {
            try {
                this.mListener.onEnrollmentAdvertisingStarted();
            } catch (RemoteException e) {
                Log.e(CarTrustAgentEnrollmentService.TAG, "onEnrollmentAdvertisementStarted() failed", e);
            }
        }
    }

    private String getEnrollmentDeviceName() {
        if (this.mEnrollmentDeviceName == null) {
            String deviceNamePrefix = (String) CarProperties.trusted_device_device_name_prefix().orElse("");
            String deviceNamePrefix2 = deviceNamePrefix.substring(0, Math.min(deviceNamePrefix.length(), 4));
            int randomNameLength = 8 - deviceNamePrefix2.length();
            String randomName = Utils.generateRandomNumberString(randomNameLength);
            this.mEnrollmentDeviceName = deviceNamePrefix2 + randomName;
        }
        return this.mEnrollmentDeviceName;
    }
}
