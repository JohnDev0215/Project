package com.android.bluetooth.pbap;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.bluetooth.IBluetoothPbap;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.database.sqlite.SQLiteException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.os.UserManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.android.bluetooth.IObexConnectionHandler;
import com.android.bluetooth.ObexServerSockets;
import com.android.bluetooth.R;
import com.android.bluetooth.Utils;
import com.android.bluetooth.btservice.AdapterService;
import com.android.bluetooth.btservice.ProfileService;
import com.android.bluetooth.sdp.SdpManager;
import com.android.bluetooth.util.DevicePolicyUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothPbapService extends ProfileService implements IObexConnectionHandler {
    private static final String ACCESS_AUTHORITY_CLASS = "com.android.settings.bluetooth.BluetoothPermissionRequest";
    private static final String ACCESS_AUTHORITY_PACKAGE = "com.android.settings";
    static final String AUTH_CANCELLED_ACTION = "com.android.bluetooth.pbap.authcancelled";
    static final String AUTH_CHALL_ACTION = "com.android.bluetooth.pbap.authchall";
    static final String AUTH_RESPONSE_ACTION = "com.android.bluetooth.pbap.authresponse";
    private static final String BLUETOOTH_ADMIN_PERM = "android.permission.BLUETOOTH_ADMIN";
    static final String BLUETOOTH_PERM = "android.permission.BLUETOOTH";
    static final int CHECK_SECONDARY_VERSION_COUNTER = 6;
    static final int CONTACTS_LOADED = 5;
    public static final boolean DEBUG = true;
    static final String EXTRA_DEVICE = "com.android.bluetooth.pbap.device";
    static final String EXTRA_SESSION_KEY = "com.android.bluetooth.pbap.sessionkey";
    static final int GET_LOCAL_TELEPHONY_DETAILS = 8;
    static final int LOAD_CONTACTS = 4;
    static final int MSG_ACQUIRE_WAKE_LOCK = 5004;
    static final int MSG_RELEASE_WAKE_LOCK = 5005;
    static final int MSG_STATE_MACHINE_DONE = 5006;
    private static final int PBAP_NOTIFICATION_ID_END = 2000000;
    private static final int PBAP_NOTIFICATION_ID_START = 1000000;
    static final int RELEASE_WAKE_LOCK_DELAY = 10000;
    static final int ROLLOVER_COUNTERS = 7;
    private static final int SDP_PBAP_SERVER_VERSION = 258;
    private static final int SDP_PBAP_SUPPORTED_FEATURES = 543;
    private static final int SDP_PBAP_SUPPORTED_REPOSITORIES = 9;
    static final int SHUTDOWN = 3;
    static final int START_LISTENER = 1;
    private static final String TAG = "BluetoothPbapService";
    static final String THIS_PACKAGE_NAME = "com.android.bluetooth";
    static final String USER_CONFIRM_TIMEOUT_ACTION = "com.android.bluetooth.pbap.userconfirmtimeout";
    static final int USER_CONFIRM_TIMEOUT_VALUE = 30000;
    static final int USER_TIMEOUT = 2;
    public static final boolean VERBOSE = false;
    private static BluetoothPbapService sBluetoothPbapService;
    private static String sLocalPhoneName;
    private static String sLocalPhoneNum;
    private BluetoothPbapContentObserver mContactChangeObserver;
    protected Context mContext;
    private HandlerThread mHandlerThread;
    private PbapHandler mSessionStatusHandler;
    private Thread mThreadLoadContacts;
    private Thread mThreadUpdateSecVersionCounter;
    private PowerManager.WakeLock mWakeLock;
    private ObexServerSockets mServerSockets = null;
    private int mSdpHandle = -1;
    private final HashMap<BluetoothDevice, PbapStateMachine> mPbapStateMachineMap = new HashMap<>();
    private volatile int mNextNotificationId = PBAP_NOTIFICATION_ID_START;
    private boolean mContactsLoaded = false;
    private BroadcastReceiver mPbapReceiver = new BroadcastReceiver() { // from class: com.android.bluetooth.pbap.BluetoothPbapService.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            BluetoothPbapService.this.parseIntent(intent);
        }
    };

    private class BluetoothPbapContentObserver extends ContentObserver {
        BluetoothPbapContentObserver() {
            super(new Handler());
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange) {
            Log.d(BluetoothPbapService.TAG, " onChange on contact uri ");
            BluetoothPbapService.this.sendUpdateRequest();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendUpdateRequest() {
        if (this.mContactsLoaded && !this.mSessionStatusHandler.hasMessages(6)) {
            PbapHandler pbapHandler = this.mSessionStatusHandler;
            pbapHandler.sendMessage(pbapHandler.obtainMessage(6));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseIntent(Intent intent) {
        String action = intent.getAction();
        Log.d(TAG, "action: " + action);
        if ("android.bluetooth.device.action.CONNECTION_ACCESS_REPLY".equals(action)) {
            int requestType = intent.getIntExtra("android.bluetooth.device.extra.ACCESS_REQUEST_TYPE", 2);
            if (requestType != 2) {
                return;
            }
            BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            synchronized (this.mPbapStateMachineMap) {
                PbapStateMachine sm = this.mPbapStateMachineMap.get(device);
                if (sm == null) {
                    Log.w(TAG, "device not connected! device=" + device.getAddressOnlyforLog());
                    return;
                }
                this.mSessionStatusHandler.removeMessages(2, sm);
                int access = intent.getIntExtra("android.bluetooth.device.extra.CONNECTION_ACCESS_RESULT", 2);
                boolean savePreference = intent.getBooleanExtra("android.bluetooth.device.extra.ALWAYS_ALLOWED", false);
                if (access == 1) {
                    if (savePreference) {
                        device.setPhonebookAccessPermission(1);
                    }
                    sm.sendMessage(1);
                } else {
                    if (savePreference) {
                        device.setPhonebookAccessPermission(2);
                    }
                    sm.sendMessage(2);
                }
                return;
            }
        }
        if (AUTH_RESPONSE_ACTION.equals(action)) {
            String sessionKey = intent.getStringExtra(EXTRA_SESSION_KEY);
            BluetoothDevice device2 = (BluetoothDevice) intent.getParcelableExtra(EXTRA_DEVICE);
            synchronized (this.mPbapStateMachineMap) {
                PbapStateMachine sm2 = this.mPbapStateMachineMap.get(device2);
                if (sm2 == null) {
                    return;
                }
                Message msg = sm2.obtainMessage(7, sessionKey);
                sm2.sendMessage(msg);
                return;
            }
        }
        if (AUTH_CANCELLED_ACTION.equals(action)) {
            BluetoothDevice device3 = (BluetoothDevice) intent.getParcelableExtra(EXTRA_DEVICE);
            synchronized (this.mPbapStateMachineMap) {
                PbapStateMachine sm3 = this.mPbapStateMachineMap.get(device3);
                if (sm3 == null) {
                    return;
                }
                sm3.sendMessage(8);
                return;
            }
        }
        Log.w(TAG, "Unhandled intent action: " + action);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeService() {
        BluetoothPbapUtils.savePbapParams(this);
        PowerManager.WakeLock wakeLock = this.mWakeLock;
        if (wakeLock != null) {
            wakeLock.release();
            this.mWakeLock = null;
        }
        cleanUpServerSocket();
        PbapHandler pbapHandler = this.mSessionStatusHandler;
        if (pbapHandler != null) {
            pbapHandler.removeCallbacksAndMessages(null);
        }
    }

    private void cleanUpServerSocket() {
        synchronized (this.mPbapStateMachineMap) {
            for (PbapStateMachine stateMachine : this.mPbapStateMachineMap.values()) {
                stateMachine.sendMessage(3);
            }
        }
        cleanUpSdpRecord();
        ObexServerSockets obexServerSockets = this.mServerSockets;
        if (obexServerSockets != null) {
            obexServerSockets.shutdown(false);
            this.mServerSockets = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createSdpRecord() {
        if (this.mSdpHandle > -1) {
            Log.w(TAG, "createSdpRecord, SDP record already created");
        }
        this.mSdpHandle = SdpManager.getDefaultManager().createPbapPseRecord("OBEX Phonebook Access Server", this.mServerSockets.getRfcommChannel(), this.mServerSockets.getL2capPsm(), 258, 9, SDP_PBAP_SUPPORTED_FEATURES);
        Log.d(TAG, "created Sdp record, mSdpHandle=" + this.mSdpHandle);
    }

    private void cleanUpSdpRecord() {
        if (this.mSdpHandle < 0) {
            Log.w(TAG, "cleanUpSdpRecord, SDP record never created");
            return;
        }
        int sdpHandle = this.mSdpHandle;
        this.mSdpHandle = -1;
        SdpManager sdpManager = SdpManager.getDefaultManager();
        Log.d(TAG, "cleanUpSdpRecord, mSdpHandle=" + sdpHandle);
        if (sdpManager == null) {
            Log.e(TAG, "sdpManager is null");
        } else if (!sdpManager.removeSdpRecord(sdpHandle)) {
            Log.w(TAG, "cleanUpSdpRecord, removeSdpRecord failed, sdpHandle=" + sdpHandle);
        }
    }

    private class PbapHandler extends Handler {
        private PbapHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            int i = msg.what;
            switch (i) {
                case 1:
                    BluetoothPbapService bluetoothPbapService = BluetoothPbapService.this;
                    bluetoothPbapService.mServerSockets = ObexServerSockets.create(bluetoothPbapService);
                    if (BluetoothPbapService.this.mServerSockets != null) {
                        BluetoothPbapService.this.createSdpRecord();
                        BluetoothPbapUtils.fetchPbapParams(BluetoothPbapService.this.mContext);
                        return;
                    } else {
                        Log.w(BluetoothPbapService.TAG, "ObexServerSockets.create() returned null");
                        return;
                    }
                case 2:
                    Intent intent = new Intent("android.bluetooth.device.action.CONNECTION_ACCESS_CANCEL");
                    intent.setPackage(BluetoothPbapService.this.getString(R.string.pairing_ui_package));
                    PbapStateMachine stateMachine = (PbapStateMachine) msg.obj;
                    intent.putExtra("android.bluetooth.device.extra.DEVICE", stateMachine.getRemoteDevice());
                    intent.putExtra("android.bluetooth.device.extra.ACCESS_REQUEST_TYPE", 2);
                    BluetoothPbapService.this.sendBroadcast(intent, "android.permission.BLUETOOTH_ADMIN");
                    stateMachine.sendMessage(2);
                    return;
                case 3:
                    BluetoothPbapService.this.closeService();
                    return;
                case 4:
                    BluetoothPbapService.this.loadAllContacts();
                    return;
                case 5:
                    BluetoothPbapService.this.mContactsLoaded = true;
                    return;
                case 6:
                    BluetoothPbapService.this.updateSecondaryVersion();
                    return;
                case 7:
                    BluetoothPbapUtils.rolloverCounters();
                    return;
                case 8:
                    BluetoothPbapService.this.getLocalTelephonyDetails();
                    return;
                default:
                    switch (i) {
                        case BluetoothPbapService.MSG_ACQUIRE_WAKE_LOCK /* 5004 */:
                            if (BluetoothPbapService.this.mWakeLock == null) {
                                PowerManager pm = (PowerManager) BluetoothPbapService.this.getSystemService("power");
                                BluetoothPbapService.this.mWakeLock = pm.newWakeLock(1, "StartingObexPbapTransaction");
                                BluetoothPbapService.this.mWakeLock.setReferenceCounted(false);
                                BluetoothPbapService.this.mWakeLock.acquire();
                                Log.w(BluetoothPbapService.TAG, "Acquire Wake Lock");
                            }
                            BluetoothPbapService.this.mSessionStatusHandler.removeMessages(5005);
                            BluetoothPbapService.this.mSessionStatusHandler.sendMessageDelayed(BluetoothPbapService.this.mSessionStatusHandler.obtainMessage(5005), 10000L);
                            return;
                        case 5005:
                            if (BluetoothPbapService.this.mWakeLock != null) {
                                BluetoothPbapService.this.mWakeLock.release();
                                BluetoothPbapService.this.mWakeLock = null;
                                return;
                            }
                            return;
                        case 5006:
                            PbapStateMachine sm = (PbapStateMachine) msg.obj;
                            BluetoothDevice remoteDevice = sm.getRemoteDevice();
                            sm.quitNow();
                            synchronized (BluetoothPbapService.this.mPbapStateMachineMap) {
                                BluetoothPbapService.this.mPbapStateMachineMap.remove(remoteDevice);
                                break;
                            }
                            return;
                        default:
                            return;
                    }
            }
        }
    }

    public int getConnectionState(BluetoothDevice device) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_PRIVILEGED", "Need BLUETOOTH_PRIVILEGED permission");
        HashMap<BluetoothDevice, PbapStateMachine> map = this.mPbapStateMachineMap;
        if (map == null) {
            return 0;
        }
        synchronized (map) {
            PbapStateMachine sm = this.mPbapStateMachineMap.get(device);
            if (sm == null) {
                return 0;
            }
            return sm.getConnectionState();
        }
    }

    List<BluetoothDevice> getConnectedDevices() {
        ArrayList arrayList;
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH", "Need BLUETOOTH permission");
        HashMap<BluetoothDevice, PbapStateMachine> map = this.mPbapStateMachineMap;
        if (map == null) {
            return new ArrayList();
        }
        synchronized (map) {
            arrayList = new ArrayList(this.mPbapStateMachineMap.keySet());
        }
        return arrayList;
    }

    List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH", "Need BLUETOOTH permission");
        List<BluetoothDevice> devices = new ArrayList<>();
        HashMap<BluetoothDevice, PbapStateMachine> map = this.mPbapStateMachineMap;
        if (map == null || states == null) {
            return devices;
        }
        synchronized (map) {
            for (int state : states) {
                for (BluetoothDevice device : this.mPbapStateMachineMap.keySet()) {
                    if (state == this.mPbapStateMachineMap.get(device).getConnectionState()) {
                        devices.add(device);
                    }
                }
            }
        }
        return devices;
    }

    public boolean setConnectionPolicy(BluetoothDevice device, int connectionPolicy) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_PRIVILEGED", "Need BLUETOOTH_PRIVILEGED permission");
        Log.d(TAG, "Saved connectionPolicy " + device.getAddressOnlyforLog() + " = " + connectionPolicy);
        AdapterService.getAdapterService().getDatabase().setProfileConnectionPolicy(device, 6, connectionPolicy);
        if (connectionPolicy == 0) {
            disconnect(device);
            return true;
        }
        return true;
    }

    public int getConnectionPolicy(BluetoothDevice device) {
        if (device == null) {
            throw new IllegalArgumentException("Null device");
        }
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN", "Need BLUETOOTH_ADMIN permission");
        return AdapterService.getAdapterService().getDatabase().getProfileConnectionPolicy(device, 6);
    }

    public void disconnect(BluetoothDevice device) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN", "Need BLUETOOTH_ADMIN permission");
        synchronized (this.mPbapStateMachineMap) {
            PbapStateMachine sm = this.mPbapStateMachineMap.get(device);
            if (sm != null) {
                sm.sendMessage(3);
            }
        }
    }

    static String getLocalPhoneNum() {
        return sLocalPhoneNum;
    }

    static String getLocalPhoneName() {
        return sLocalPhoneName;
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected ProfileService.IProfileServiceBinder initBinder() {
        return new PbapBinder(this);
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected boolean start() {
        this.mContext = this;
        this.mContactsLoaded = false;
        HandlerThread handlerThread = new HandlerThread("PbapHandlerThread");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mSessionStatusHandler = new PbapHandler(this.mHandlerThread.getLooper());
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.bluetooth.device.action.CONNECTION_ACCESS_REPLY");
        filter.addAction(AUTH_RESPONSE_ACTION);
        filter.addAction(AUTH_CANCELLED_ACTION);
        BluetoothPbapConfig.init(this);
        registerReceiver(this.mPbapReceiver, filter);
        try {
            this.mContactChangeObserver = new BluetoothPbapContentObserver();
            getContentResolver().registerContentObserver(DevicePolicyUtils.getEnterprisePhoneUri(this), false, this.mContactChangeObserver);
        } catch (SQLiteException e) {
            Log.e(TAG, "SQLite exception: " + e);
        } catch (IllegalStateException e2) {
            Log.e(TAG, "Illegal state exception, content observer is already registered");
        }
        setBluetoothPbapService(this);
        PbapHandler pbapHandler = this.mSessionStatusHandler;
        pbapHandler.sendMessage(pbapHandler.obtainMessage(8));
        PbapHandler pbapHandler2 = this.mSessionStatusHandler;
        pbapHandler2.sendMessage(pbapHandler2.obtainMessage(4));
        PbapHandler pbapHandler3 = this.mSessionStatusHandler;
        pbapHandler3.sendMessage(pbapHandler3.obtainMessage(1));
        return true;
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected boolean stop() {
        setBluetoothPbapService(null);
        PbapHandler pbapHandler = this.mSessionStatusHandler;
        if (pbapHandler != null) {
            pbapHandler.obtainMessage(3).sendToTarget();
        }
        HandlerThread handlerThread = this.mHandlerThread;
        if (handlerThread != null) {
            handlerThread.quitSafely();
        }
        this.mContactsLoaded = false;
        if (this.mContactChangeObserver == null) {
            Log.i(TAG, "Avoid unregister when receiver it is not registered");
            return true;
        }
        unregisterReceiver(this.mPbapReceiver);
        getContentResolver().unregisterContentObserver(this.mContactChangeObserver);
        this.mContactChangeObserver = null;
        return true;
    }

    public static synchronized BluetoothPbapService getBluetoothPbapService() {
        if (sBluetoothPbapService == null) {
            Log.w(TAG, "getBluetoothPbapService(): service is null");
            return null;
        }
        if (!sBluetoothPbapService.isAvailable()) {
            Log.w(TAG, "getBluetoothPbapService(): service is not available");
            return null;
        }
        return sBluetoothPbapService;
    }

    private static synchronized void setBluetoothPbapService(BluetoothPbapService instance) {
        Log.d(TAG, "setBluetoothPbapService(): set to: " + instance);
        sBluetoothPbapService = instance;
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected void setCurrentUser(int userId) {
        Log.i(TAG, "setCurrentUser(" + userId + ")");
        UserManager userManager = (UserManager) getSystemService("user");
        if (userManager.isUserUnlocked(userId)) {
            setUserUnlocked(userId);
        }
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected void setUserUnlocked(int userId) {
        Log.i(TAG, "setUserUnlocked(" + userId + ")");
        sendUpdateRequest();
    }

    private static class PbapBinder extends IBluetoothPbap.Stub implements ProfileService.IProfileServiceBinder {
        private BluetoothPbapService mService;

        private BluetoothPbapService getService() {
            if (!Utils.checkCaller()) {
                Log.w(BluetoothPbapService.TAG, "not allowed for non-active user");
                return null;
            }
            BluetoothPbapService bluetoothPbapService = this.mService;
            if (bluetoothPbapService == null || !bluetoothPbapService.isAvailable()) {
                return null;
            }
            return this.mService;
        }

        PbapBinder(BluetoothPbapService service) {
            this.mService = service;
        }

        @Override // com.android.bluetooth.btservice.ProfileService.IProfileServiceBinder
        public void cleanup() {
            this.mService = null;
        }

        public List<BluetoothDevice> getConnectedDevices() {
            Log.d(BluetoothPbapService.TAG, "getConnectedDevices");
            BluetoothPbapService service = getService();
            if (service == null) {
                return new ArrayList(0);
            }
            return service.getConnectedDevices();
        }

        public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states) {
            Log.d(BluetoothPbapService.TAG, "getDevicesMatchingConnectionStates");
            BluetoothPbapService service = getService();
            if (service == null) {
                return new ArrayList(0);
            }
            return service.getDevicesMatchingConnectionStates(states);
        }

        public int getConnectionState(BluetoothDevice device) {
            Log.d(BluetoothPbapService.TAG, "getConnectionState: " + device.getAddressOnlyforLog());
            BluetoothPbapService service = getService();
            if (service == null) {
                return 0;
            }
            return service.getConnectionState(device);
        }

        public boolean setConnectionPolicy(BluetoothDevice device, int connectionPolicy) {
            Log.d(BluetoothPbapService.TAG, "setConnectionPolicy for device: " + device.getAddressOnlyforLog() + ", policy:" + connectionPolicy);
            BluetoothPbapService service = getService();
            if (service == null) {
                return false;
            }
            return service.setConnectionPolicy(device, connectionPolicy);
        }

        public void disconnect(BluetoothDevice device) {
            Log.d(BluetoothPbapService.TAG, "disconnect");
            BluetoothPbapService service = getService();
            if (service == null) {
                return;
            }
            service.disconnect(device);
        }
    }

    @Override // com.android.bluetooth.IObexConnectionHandler
    public boolean onConnect(BluetoothDevice remoteDevice, BluetoothSocket socket) {
        if (remoteDevice == null || socket == null) {
            Log.e(TAG, "onConnect(): Unexpected null. remoteDevice=" + remoteDevice + " socket=" + socket);
            return false;
        }
        PbapStateMachine sm = PbapStateMachine.make(this, this.mHandlerThread.getLooper(), remoteDevice, socket, this, this.mSessionStatusHandler, this.mNextNotificationId);
        this.mNextNotificationId++;
        if (this.mNextNotificationId == PBAP_NOTIFICATION_ID_END) {
            this.mNextNotificationId = PBAP_NOTIFICATION_ID_START;
        }
        synchronized (this.mPbapStateMachineMap) {
            this.mPbapStateMachineMap.put(remoteDevice, sm);
        }
        sm.sendMessage(4);
        return true;
    }

    public void checkOrGetPhonebookPermission(PbapStateMachine stateMachine) {
        BluetoothDevice device = stateMachine.getRemoteDevice();
        int permission = device.getPhonebookAccessPermission();
        Log.d(TAG, "getPhonebookAccessPermission() = " + permission);
        if (permission == 1) {
            setConnectionPolicy(device, 100);
            stateMachine.sendMessage(1);
            return;
        }
        if (permission == 2) {
            stateMachine.sendMessage(2);
            return;
        }
        Intent intent = new Intent("android.bluetooth.device.action.CONNECTION_ACCESS_REQUEST");
        intent.setClassName(ACCESS_AUTHORITY_PACKAGE, ACCESS_AUTHORITY_CLASS);
        intent.putExtra("android.bluetooth.device.extra.ACCESS_REQUEST_TYPE", 2);
        intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
        intent.putExtra("android.bluetooth.device.extra.PACKAGE_NAME", getPackageName());
        sendOrderedBroadcast(intent, "android.permission.BLUETOOTH_ADMIN");
        Message msg = this.mSessionStatusHandler.obtainMessage(2, stateMachine);
        this.mSessionStatusHandler.sendMessageDelayed(msg, 30000L);
    }

    @Override // com.android.bluetooth.IObexConnectionHandler
    public synchronized void onAcceptFailed() {
        Log.w(TAG, "PBAP server socket accept thread failed. Restarting the server socket");
        if (this.mWakeLock != null) {
            this.mWakeLock.release();
            this.mWakeLock = null;
        }
        cleanUpServerSocket();
        if (this.mSessionStatusHandler != null) {
            this.mSessionStatusHandler.removeCallbacksAndMessages(null);
        }
        synchronized (this.mPbapStateMachineMap) {
            this.mPbapStateMachineMap.clear();
        }
        this.mSessionStatusHandler.sendMessage(this.mSessionStatusHandler.obtainMessage(1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadAllContacts() {
        if (this.mThreadLoadContacts == null) {
            Runnable r = new Runnable() { // from class: com.android.bluetooth.pbap.BluetoothPbapService.2
                @Override // java.lang.Runnable
                public void run() {
                    BluetoothPbapUtils.loadAllContacts(BluetoothPbapService.this.mContext, BluetoothPbapService.this.mSessionStatusHandler);
                    BluetoothPbapService.this.mThreadLoadContacts = null;
                }
            };
            Thread thread = new Thread(r);
            this.mThreadLoadContacts = thread;
            thread.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSecondaryVersion() {
        if (this.mThreadUpdateSecVersionCounter == null) {
            Runnable r = new Runnable() { // from class: com.android.bluetooth.pbap.BluetoothPbapService.3
                @Override // java.lang.Runnable
                public void run() {
                    BluetoothPbapUtils.updateSecondaryVersionCounter(BluetoothPbapService.this.mContext, BluetoothPbapService.this.mSessionStatusHandler);
                    BluetoothPbapService.this.mThreadUpdateSecVersionCounter = null;
                }
            };
            Thread thread = new Thread(r);
            this.mThreadUpdateSecVersionCounter = thread;
            thread.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getLocalTelephonyDetails() {
        TelephonyManager tm = (TelephonyManager) getSystemService("phone");
        if (tm != null) {
            sLocalPhoneNum = tm.getLine1Number();
            sLocalPhoneName = getString(R.string.localPhoneName);
        }
    }
}
