package com.android.bluetooth.map;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothUuid;
import android.bluetooth.IBluetoothMap;
import android.bluetooth.SdpMnsRecord;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.ParcelUuid;
import android.os.PowerManager;
import android.os.RemoteException;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import androidx.core.app.NotificationCompat;
import androidx.core.os.EnvironmentCompat;
import com.android.bluetooth.BluetoothMetricsProto;
import com.android.bluetooth.R;
import com.android.bluetooth.Utils;
import com.android.bluetooth.btservice.AdapterService;
import com.android.bluetooth.btservice.MetricsLogger;
import com.android.bluetooth.btservice.ProfileService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothMapService extends ProfileService {
    static final String ACTION_SHOW_MAPS_SETTINGS = "android.btmap.intent.action.SHOW_MAPS_SETTINGS";
    private static final String BLUETOOTH_ADMIN_PERM = "android.permission.BLUETOOTH_ADMIN";
    private static final String BLUETOOTH_PERM = "android.permission.BLUETOOTH";
    public static final boolean DEBUG = false;
    private static final int DISCONNECT_MAP = 3;
    private static final int MAS_ID_SMS_MMS = 0;
    static final int MSG_ACQUIRE_WAKE_LOCK = 5005;
    static final int MSG_MAS_CONNECT = 5003;
    static final int MSG_MAS_CONNECT_CANCEL = 5004;
    static final int MSG_MNS_SDP_SEARCH = 5007;
    static final int MSG_OBSERVER_REGISTRATION = 5008;
    static final int MSG_RELEASE_WAKE_LOCK = 5006;
    static final int MSG_SERVERSESSION_CLOSE = 5000;
    static final int MSG_SESSION_DISCONNECTED = 5002;
    static final int MSG_SESSION_ESTABLISHED = 5001;
    private static final int RELEASE_WAKE_LOCK_DELAY = 10000;
    private static final int SHUTDOWN = 4;
    private static final int START_LISTENER = 1;
    private static final String TAG = "BluetoothMapService";
    private static final int UPDATE_MAS_INSTANCES = 5;
    static final int UPDATE_MAS_INSTANCES_ACCOUNT_ADDED = 0;
    static final int UPDATE_MAS_INSTANCES_ACCOUNT_DISCONNECT = 3;
    static final int UPDATE_MAS_INSTANCES_ACCOUNT_REMOVED = 1;
    static final int UPDATE_MAS_INSTANCES_ACCOUNT_RENAMED = 2;
    public static final String USER_CONFIRM_TIMEOUT_ACTION = "com.android.bluetooth.map.USER_CONFIRM_TIMEOUT";
    private static final int USER_CONFIRM_TIMEOUT_VALUE = 25000;
    private static final int USER_TIMEOUT = 2;
    public static final boolean VERBOSE = false;
    private static BluetoothMapService sBluetoothMapService;
    private BluetoothAdapter mAdapter;
    private MapServiceMessageHandler mSessionStatusHandler;
    private static BluetoothDevice sRemoteDevice = null;
    private static String sRemoteDeviceName = null;
    private static final ParcelUuid[] MAP_UUIDS = {BluetoothUuid.MAP, BluetoothUuid.MNS};
    private PowerManager.WakeLock mWakeLock = null;
    private BluetoothMnsObexClient mBluetoothMnsObexClient = null;
    private SparseArray<BluetoothMapMasInstance> mMasInstances = new SparseArray<>(1);
    private HashMap<BluetoothMapAccountItem, BluetoothMapMasInstance> mMasInstanceMap = new HashMap<>(1);
    private ArrayList<BluetoothMapAccountItem> mEnabledAccounts = null;
    private BluetoothMapAppObserver mAppObserver = null;
    private AlarmManager mAlarmManager = null;
    private boolean mIsWaitingAuthorization = false;
    private boolean mRemoveTimeoutMsg = false;
    private boolean mRegisteredMapReceiver = false;
    private int mPermission = 0;
    private boolean mAccountChanged = false;
    private boolean mSdpSearchInitiated = false;
    private SdpMnsRecord mMnsRecord = null;
    private boolean mServiceStarted = false;
    private boolean mSmsCapable = true;
    private MapBroadcastReceiver mMapReceiver = new MapBroadcastReceiver();
    private int mState = 0;

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void closeService() {
        if (this.mBluetoothMnsObexClient != null) {
            this.mBluetoothMnsObexClient.shutdown();
            this.mBluetoothMnsObexClient = null;
        }
        int numMasInstances = this.mMasInstances.size();
        for (int i = 0; i < numMasInstances; i++) {
            this.mMasInstances.valueAt(i).shutdown();
        }
        this.mMasInstances.clear();
        this.mIsWaitingAuthorization = false;
        this.mPermission = 0;
        setState(0);
        if (this.mWakeLock != null) {
            this.mWakeLock.release();
            this.mWakeLock = null;
        }
        sRemoteDevice = null;
        if (this.mSessionStatusHandler == null) {
            return;
        }
        this.mSessionStatusHandler.removeCallbacksAndMessages(null);
        Looper looper = this.mSessionStatusHandler.getLooper();
        if (looper != null) {
            looper.quit();
        }
        this.mSessionStatusHandler = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startSocketListeners(int masId) {
        if (masId == -1) {
            int c = this.mMasInstances.size();
            for (int i = 0; i < c; i++) {
                this.mMasInstances.valueAt(i).startSocketListeners();
            }
            return;
        }
        BluetoothMapMasInstance masInst = this.mMasInstances.get(masId);
        if (masInst != null) {
            masInst.startSocketListeners();
            return;
        }
        Log.w(TAG, "startSocketListeners(): Invalid MasId: " + masId);
    }

    private void startObexServerSessions() {
        if (this.mWakeLock == null) {
            PowerManager pm = (PowerManager) getSystemService("power");
            PowerManager.WakeLock wakeLockNewWakeLock = pm.newWakeLock(1, "StartingObexMapTransaction");
            this.mWakeLock = wakeLockNewWakeLock;
            wakeLockNewWakeLock.setReferenceCounted(false);
            this.mWakeLock.acquire();
        }
        if (this.mBluetoothMnsObexClient == null) {
            this.mBluetoothMnsObexClient = new BluetoothMnsObexClient(sRemoteDevice, this.mMnsRecord, this.mSessionStatusHandler);
        }
        boolean connected = false;
        int c = this.mMasInstances.size();
        for (int i = 0; i < c; i++) {
            try {
                if (this.mMasInstances.valueAt(i).startObexServerSession(this.mBluetoothMnsObexClient)) {
                    connected = true;
                }
            } catch (RemoteException e) {
                Log.w(TAG, "RemoteException occured while starting an obexServerSession restarting the listener", e);
                this.mMasInstances.valueAt(i).restartObexServerSession();
            } catch (IOException e2) {
                Log.w(TAG, "IOException occured while starting an obexServerSession restarting the listener", e2);
                this.mMasInstances.valueAt(i).restartObexServerSession();
            }
        }
        if (connected) {
            setState(2);
        }
        this.mSessionStatusHandler.removeMessages(5006);
        MapServiceMessageHandler mapServiceMessageHandler = this.mSessionStatusHandler;
        mapServiceMessageHandler.sendMessageDelayed(mapServiceMessageHandler.obtainMessage(5006), 10000L);
    }

    public Handler getHandler() {
        return this.mSessionStatusHandler;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopObexServerSessions(int masId) {
        boolean lastMasInst = true;
        if (masId != -1) {
            int c = this.mMasInstances.size();
            for (int i = 0; i < c; i++) {
                BluetoothMapMasInstance masInst = this.mMasInstances.valueAt(i);
                if (masInst.getMasId() != masId && masInst.isStarted()) {
                    lastMasInst = false;
                }
            }
        }
        BluetoothMnsObexClient bluetoothMnsObexClient = this.mBluetoothMnsObexClient;
        if (bluetoothMnsObexClient != null && lastMasInst) {
            bluetoothMnsObexClient.shutdown();
            this.mBluetoothMnsObexClient = null;
        }
        BluetoothMapMasInstance masInst2 = this.mMasInstances.get(masId);
        if (masInst2 != null) {
            masInst2.restartObexServerSession();
        } else if (masId == -1) {
            int c2 = this.mMasInstances.size();
            for (int i2 = 0; i2 < c2; i2++) {
                this.mMasInstances.valueAt(i2).restartObexServerSession();
            }
        }
        if (lastMasInst) {
            setState(0);
            this.mPermission = 0;
            sRemoteDevice = null;
            if (this.mAccountChanged) {
                updateMasInstances(3);
            }
        }
        if (this.mWakeLock != null && lastMasInst) {
            this.mSessionStatusHandler.removeMessages(5005);
            this.mSessionStatusHandler.removeMessages(5006);
            this.mWakeLock.release();
        }
    }

    private final class MapServiceMessageHandler extends Handler {
        private MapServiceMessageHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            int i = msg.what;
            if (i == 1) {
                BluetoothMapService.this.startSocketListeners(msg.arg1);
                return;
            }
            if (i == 2) {
                if (BluetoothMapService.this.mIsWaitingAuthorization) {
                    Intent intent = new Intent("android.bluetooth.device.action.CONNECTION_ACCESS_CANCEL");
                    intent.setPackage(BluetoothMapService.this.getString(R.string.pairing_ui_package));
                    intent.putExtra("android.bluetooth.device.extra.DEVICE", BluetoothMapService.sRemoteDevice);
                    intent.putExtra("android.bluetooth.device.extra.ACCESS_REQUEST_TYPE", 3);
                    BluetoothMapService.this.sendBroadcast(intent);
                    BluetoothMapService.this.cancelUserTimeoutAlarm();
                    BluetoothMapService.this.mIsWaitingAuthorization = false;
                    BluetoothMapService.this.stopObexServerSessions(-1);
                    return;
                }
                return;
            }
            if (i == 3) {
                BluetoothMapService.this.disconnectMap((BluetoothDevice) msg.obj);
                return;
            }
            if (i == 4) {
                BluetoothMapService.this.closeService();
                return;
            }
            if (i == 5) {
                BluetoothMapService.this.updateMasInstancesHandler();
                return;
            }
            if (i != 5000) {
                switch (i) {
                    case BluetoothMapService.MSG_MAS_CONNECT /* 5003 */:
                        BluetoothMapService.this.onConnectHandler(msg.arg1);
                        break;
                    case BluetoothMapService.MSG_MAS_CONNECT_CANCEL /* 5004 */:
                        BluetoothMapService.this.stopObexServerSessions(-1);
                        break;
                    case 5005:
                        if (BluetoothMapService.this.mWakeLock == null) {
                            PowerManager pm = (PowerManager) BluetoothMapService.this.getSystemService("power");
                            BluetoothMapService.this.mWakeLock = pm.newWakeLock(1, "StartingObexMapTransaction");
                            BluetoothMapService.this.mWakeLock.setReferenceCounted(false);
                        }
                        if (!BluetoothMapService.this.mWakeLock.isHeld()) {
                            BluetoothMapService.this.mWakeLock.acquire();
                        }
                        BluetoothMapService.this.mSessionStatusHandler.removeMessages(5006);
                        BluetoothMapService.this.mSessionStatusHandler.sendMessageDelayed(BluetoothMapService.this.mSessionStatusHandler.obtainMessage(5006), 10000L);
                        break;
                    case 5006:
                        if (BluetoothMapService.this.mWakeLock != null) {
                            BluetoothMapService.this.mWakeLock.release();
                        }
                        break;
                    case 5007:
                        if (BluetoothMapService.sRemoteDevice != null) {
                            BluetoothMapService.sRemoteDevice.sdpSearch(BluetoothMnsObexClient.BLUETOOTH_UUID_OBEX_MNS);
                        } else {
                            Log.w(BluetoothMapService.TAG, "remoteDevice info not available");
                        }
                        break;
                    case BluetoothMapService.MSG_OBSERVER_REGISTRATION /* 5008 */:
                        BluetoothMapMasInstance masInst = (BluetoothMapMasInstance) BluetoothMapService.this.mMasInstances.get(msg.arg1);
                        if (masInst != null && masInst.mObserver != null) {
                            try {
                                if (msg.arg2 == 1) {
                                    masInst.mObserver.registerObserver();
                                } else {
                                    masInst.mObserver.unregisterObserver();
                                }
                            } catch (RemoteException e) {
                                Log.e(BluetoothMapService.TAG, "ContentObserverRegistarion Failed: " + e);
                                return;
                            }
                            break;
                        }
                        break;
                }
                return;
            }
            BluetoothMapService.this.stopObexServerSessions(msg.arg1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onConnectHandler(int masId) {
        if (this.mIsWaitingAuthorization || sRemoteDevice == null || this.mSdpSearchInitiated) {
            return;
        }
        BluetoothMapMasInstance masInst = this.mMasInstances.get(masId);
        if (this.mPermission == 1) {
            try {
                if (this.mBluetoothMnsObexClient != null && masInst != null) {
                    masInst.startObexServerSession(this.mBluetoothMnsObexClient);
                } else {
                    startObexServerSessions();
                }
            } catch (RemoteException ex) {
                Log.e(TAG, "catch RemoteException starting obex server session", ex);
            } catch (IOException ex2) {
                Log.e(TAG, "catch IOException starting obex server session", ex2);
            }
        }
    }

    public int getState() {
        return this.mState;
    }

    public static BluetoothDevice getRemoteDevice() {
        return sRemoteDevice;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setState(int state) {
        setState(state, 1);
    }

    private synchronized void setState(int state, int result) {
        if (state != this.mState) {
            int prevState = this.mState;
            this.mState = state;
            Intent intent = new Intent("android.bluetooth.map.profile.action.CONNECTION_STATE_CHANGED");
            intent.putExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", prevState);
            intent.putExtra("android.bluetooth.profile.extra.STATE", this.mState);
            intent.putExtra("android.bluetooth.device.extra.DEVICE", sRemoteDevice);
            sendBroadcast(intent, "android.permission.BLUETOOTH");
        }
    }

    public void disconnect(BluetoothDevice device) {
        MapServiceMessageHandler mapServiceMessageHandler = this.mSessionStatusHandler;
        mapServiceMessageHandler.sendMessage(mapServiceMessageHandler.obtainMessage(3, 0, 0, device));
    }

    void disconnectMap(BluetoothDevice device) {
        if (getRemoteDevice() != null && getRemoteDevice().equals(device) && this.mState == 2) {
            stopObexServerSessions(-1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<BluetoothDevice> getConnectedDevices() {
        List<BluetoothDevice> devices = new ArrayList<>();
        synchronized (this) {
            if (this.mState == 2 && sRemoteDevice != null) {
                devices.add(sRemoteDevice);
            }
        }
        return devices;
    }

    List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states) {
        List<BluetoothDevice> deviceList = new ArrayList<>();
        Set<BluetoothDevice> bondedDevices = this.mAdapter.getBondedDevices();
        if (bondedDevices == null) {
            return deviceList;
        }
        synchronized (this) {
            for (BluetoothDevice device : bondedDevices) {
                ParcelUuid[] featureUuids = device.getUuids();
                if (BluetoothUuid.containsAnyUuid(featureUuids, MAP_UUIDS)) {
                    int connectionState = getConnectionState(device);
                    for (int state : states) {
                        if (connectionState == state) {
                            deviceList.add(device);
                        }
                    }
                }
            }
        }
        return deviceList;
    }

    public int getConnectionState(BluetoothDevice device) {
        synchronized (this) {
            return (getState() == 2 && getRemoteDevice().equals(device)) ? 2 : 0;
        }
    }

    boolean setConnectionPolicy(BluetoothDevice device, int connectionPolicy) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_PRIVILEGED", "Need BLUETOOTH_PRIVILEGED permission");
        AdapterService.getAdapterService().getDatabase().setProfileConnectionPolicy(device, 9, connectionPolicy);
        return true;
    }

    int getConnectionPolicy(BluetoothDevice device) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_PRIVILEGED", "Need BLUETOOTH_PRIVILEGED permission");
        return AdapterService.getAdapterService().getDatabase().getProfileConnectionPolicy(device, 9);
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected ProfileService.IProfileServiceBinder initBinder() {
        return new BluetoothMapBinder(this);
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected boolean start() {
        HandlerThread thread = new HandlerThread("BluetoothMapHandler");
        thread.start();
        Looper looper = thread.getLooper();
        this.mSessionStatusHandler = new MapServiceMessageHandler(looper);
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.bluetooth.device.action.CONNECTION_ACCESS_REPLY");
        filter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
        filter.addAction("android.bluetooth.device.action.SDP_RECORD");
        filter.addAction(ACTION_SHOW_MAPS_SETTINGS);
        filter.addAction(USER_CONFIRM_TIMEOUT_ACTION);
        IntentFilter filterMessageSent = new IntentFilter();
        filterMessageSent.addAction("com.android.bluetooth.BluetoothMapContentObserver.action.MESSAGE_SENT");
        try {
            filterMessageSent.addDataType("message/*");
        } catch (IntentFilter.MalformedMimeTypeException e) {
            Log.e(TAG, "Wrong mime type!!!", e);
        }
        if (!this.mRegisteredMapReceiver) {
            registerReceiver(this.mMapReceiver, filter);
            registerReceiver(this.mMapReceiver, filterMessageSent);
            this.mRegisteredMapReceiver = true;
        }
        this.mAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mAppObserver = new BluetoothMapAppObserver(this, this);
        TelephonyManager tm = (TelephonyManager) getSystemService("phone");
        this.mSmsCapable = tm.isSmsCapable();
        this.mEnabledAccounts = this.mAppObserver.getEnabledAccountItems();
        createMasInstances();
        sendStartListenerMessage(-1);
        setBluetoothMapService(this);
        this.mServiceStarted = true;
        return true;
    }

    public static synchronized BluetoothMapService getBluetoothMapService() {
        if (sBluetoothMapService == null) {
            Log.w(TAG, "getBluetoothMapService(): service is null");
            return null;
        }
        if (!sBluetoothMapService.isAvailable()) {
            Log.w(TAG, "getBluetoothMapService(): service is not available");
            return null;
        }
        return sBluetoothMapService;
    }

    private static synchronized void setBluetoothMapService(BluetoothMapService instance) {
        sBluetoothMapService = instance;
    }

    void updateMasInstances(int action) {
        this.mSessionStatusHandler.obtainMessage(5, action, 0).sendToTarget();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMasInstancesHandler() {
        if (getState() != 0) {
            this.mAccountChanged = true;
            return;
        }
        ArrayList<BluetoothMapAccountItem> newAccountList = this.mAppObserver.getEnabledAccountItems();
        ArrayList<BluetoothMapAccountItem> newAccounts = new ArrayList<>();
        for (BluetoothMapAccountItem account : newAccountList) {
            if (!this.mEnabledAccounts.remove(account)) {
                newAccounts.add(account);
            }
        }
        if (this.mEnabledAccounts.size() > 0) {
            Iterator<BluetoothMapAccountItem> it = this.mEnabledAccounts.iterator();
            while (it.hasNext()) {
                BluetoothMapMasInstance masInst = this.mMasInstanceMap.remove(it.next());
                if (masInst != null) {
                    masInst.shutdown();
                    this.mMasInstances.remove(masInst.getMasId());
                }
            }
        }
        for (BluetoothMapAccountItem account2 : newAccounts) {
            int masId = getNextMasId();
            BluetoothMapMasInstance newInst = new BluetoothMapMasInstance(this, this, account2, masId, false);
            this.mMasInstances.append(masId, newInst);
            this.mMasInstanceMap.put(account2, newInst);
            if (this.mAdapter.isEnabled()) {
                newInst.startSocketListeners();
            }
        }
        this.mEnabledAccounts = newAccountList;
        this.mAccountChanged = false;
    }

    private int getNextMasId() {
        int largestMasId = 0;
        int c = this.mMasInstances.size();
        for (int i = 0; i < c; i++) {
            int masId = this.mMasInstances.keyAt(i);
            if (masId > largestMasId) {
                largestMasId = masId;
            }
        }
        if (largestMasId < 255) {
            return largestMasId + 1;
        }
        for (int i2 = 1; i2 <= 255; i2++) {
            if (this.mMasInstances.get(i2) == null) {
                return i2;
            }
        }
        return 255;
    }

    private void createMasInstances() {
        int masId = 0;
        if (this.mSmsCapable) {
            BluetoothMapMasInstance smsMmsInst = new BluetoothMapMasInstance(this, this, null, 0, true);
            this.mMasInstances.append(0, smsMmsInst);
            this.mMasInstanceMap.put(null, smsMmsInst);
            masId = 0 + 1;
        }
        for (BluetoothMapAccountItem account : this.mEnabledAccounts) {
            BluetoothMapMasInstance newInst = new BluetoothMapMasInstance(this, this, account, masId, false);
            this.mMasInstances.append(masId, newInst);
            this.mMasInstanceMap.put(account, newInst);
            masId++;
        }
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected boolean stop() {
        if (!this.mServiceStarted) {
            return true;
        }
        setBluetoothMapService(null);
        this.mServiceStarted = false;
        if (this.mRegisteredMapReceiver) {
            this.mRegisteredMapReceiver = false;
            unregisterReceiver(this.mMapReceiver);
            this.mAppObserver.shutdown();
        }
        sendShutdownMessage();
        return true;
    }

    public boolean onConnect(BluetoothDevice remoteDevice, BluetoothMapMasInstance masInst) {
        boolean sendIntent = false;
        boolean cancelConnection = false;
        synchronized (this) {
            if (sRemoteDevice == null) {
                sRemoteDevice = remoteDevice;
                String name = remoteDevice.getName();
                sRemoteDeviceName = name;
                if (TextUtils.isEmpty(name)) {
                    sRemoteDeviceName = getString(R.string.defaultname);
                }
                int messageAccessPermission = sRemoteDevice.getMessageAccessPermission();
                this.mPermission = messageAccessPermission;
                if (messageAccessPermission == 0) {
                    sendIntent = true;
                    this.mIsWaitingAuthorization = true;
                    setUserTimeoutAlarm();
                } else if (messageAccessPermission == 2) {
                    cancelConnection = true;
                } else if (messageAccessPermission == 1) {
                    sRemoteDevice.sdpSearch(BluetoothMnsObexClient.BLUETOOTH_UUID_OBEX_MNS);
                    this.mSdpSearchInitiated = true;
                }
            } else if (!sRemoteDevice.equals(remoteDevice)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Unexpected connection from a second Remote Device received. name: ");
                sb.append(remoteDevice == null ? EnvironmentCompat.MEDIA_UNKNOWN : remoteDevice.getName());
                Log.w(TAG, sb.toString());
                return false;
            }
            if (sendIntent) {
                Intent intent = new Intent("android.bluetooth.device.action.CONNECTION_ACCESS_REQUEST");
                intent.setPackage(getString(R.string.pairing_ui_package));
                intent.putExtra("android.bluetooth.device.extra.ACCESS_REQUEST_TYPE", 3);
                intent.putExtra("android.bluetooth.device.extra.DEVICE", sRemoteDevice);
                sendOrderedBroadcast(intent, "android.permission.BLUETOOTH_ADMIN");
            } else if (cancelConnection) {
                sendConnectCancelMessage();
            } else if (this.mPermission == 1) {
                sendConnectMessage(masInst.getMasId());
                MetricsLogger.logProfileConnectionEvent(BluetoothMetricsProto.ProfileId.MAP);
            }
            return true;
        }
    }

    private void setUserTimeoutAlarm() {
        if (this.mAlarmManager == null) {
            this.mAlarmManager = (AlarmManager) getSystemService(NotificationCompat.CATEGORY_ALARM);
        }
        this.mRemoveTimeoutMsg = true;
        Intent timeoutIntent = new Intent(USER_CONFIRM_TIMEOUT_ACTION);
        PendingIntent pIntent = PendingIntent.getBroadcast(this, 0, timeoutIntent, 0);
        this.mAlarmManager.set(0, System.currentTimeMillis() + 25000, pIntent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelUserTimeoutAlarm() {
        Intent timeoutIntent = new Intent(USER_CONFIRM_TIMEOUT_ACTION);
        PendingIntent pIntent = PendingIntent.getBroadcast(this, 0, timeoutIntent, 0);
        pIntent.cancel();
        AlarmManager alarmManager = (AlarmManager) getSystemService(NotificationCompat.CATEGORY_ALARM);
        alarmManager.cancel(pIntent);
        this.mRemoveTimeoutMsg = false;
    }

    void sendStartListenerMessage(int masId) {
        MapServiceMessageHandler mapServiceMessageHandler = this.mSessionStatusHandler;
        if (mapServiceMessageHandler != null && !mapServiceMessageHandler.hasMessages(1)) {
            Message msg = this.mSessionStatusHandler.obtainMessage(1, masId, 0);
            this.mSessionStatusHandler.sendMessageDelayed(msg, 20L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendConnectMessage(int masId) {
        MapServiceMessageHandler mapServiceMessageHandler = this.mSessionStatusHandler;
        if (mapServiceMessageHandler != null) {
            Message msg = mapServiceMessageHandler.obtainMessage(MSG_MAS_CONNECT, masId, 0);
            this.mSessionStatusHandler.sendMessageDelayed(msg, 20L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendConnectTimeoutMessage() {
        MapServiceMessageHandler mapServiceMessageHandler = this.mSessionStatusHandler;
        if (mapServiceMessageHandler != null) {
            Message msg = mapServiceMessageHandler.obtainMessage(2);
            msg.sendToTarget();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendConnectCancelMessage() {
        MapServiceMessageHandler mapServiceMessageHandler = this.mSessionStatusHandler;
        if (mapServiceMessageHandler != null) {
            Message msg = mapServiceMessageHandler.obtainMessage(MSG_MAS_CONNECT_CANCEL);
            msg.sendToTarget();
        }
    }

    private void sendShutdownMessage() {
        if (this.mRemoveTimeoutMsg) {
            Intent timeoutIntent = new Intent(USER_CONFIRM_TIMEOUT_ACTION);
            sendBroadcast(timeoutIntent, "android.permission.BLUETOOTH");
            this.mIsWaitingAuthorization = false;
            cancelUserTimeoutAlarm();
        }
        MapServiceMessageHandler mapServiceMessageHandler = this.mSessionStatusHandler;
        if (mapServiceMessageHandler == null) {
            Log.w(TAG, "mSessionStatusHandler is null");
            return;
        }
        if (mapServiceMessageHandler.hasMessages(4)) {
            return;
        }
        this.mSessionStatusHandler.removeCallbacksAndMessages(null);
        Message msg = this.mSessionStatusHandler.obtainMessage(4);
        if (!this.mSessionStatusHandler.sendMessage(msg)) {
            Log.w(TAG, "mSessionStatusHandler shutdown message could not be sent");
        }
    }

    private class MapBroadcastReceiver extends BroadcastReceiver {
        private MapBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            BluetoothMapMasInstance masInst;
            String action = intent.getAction();
            if (action.equals(BluetoothMapService.USER_CONFIRM_TIMEOUT_ACTION)) {
                BluetoothMapService.this.sendConnectTimeoutMessage();
                return;
            }
            if (action.equals("android.bluetooth.device.action.CONNECTION_ACCESS_REPLY")) {
                int requestType = intent.getIntExtra("android.bluetooth.device.extra.ACCESS_REQUEST_TYPE", 2);
                if (BluetoothMapService.this.mIsWaitingAuthorization && requestType == 3) {
                    BluetoothMapService.this.mIsWaitingAuthorization = false;
                    if (BluetoothMapService.this.mRemoveTimeoutMsg) {
                        BluetoothMapService.this.mSessionStatusHandler.removeMessages(2);
                        BluetoothMapService.this.cancelUserTimeoutAlarm();
                        BluetoothMapService.this.setState(0);
                    }
                    if (intent.getIntExtra("android.bluetooth.device.extra.CONNECTION_ACCESS_RESULT", 2) == 1) {
                        BluetoothMapService.this.mPermission = 1;
                        if (intent.getBooleanExtra("android.bluetooth.device.extra.ALWAYS_ALLOWED", false)) {
                            BluetoothMapService.sRemoteDevice.setMessageAccessPermission(1);
                        }
                        BluetoothMapService.sRemoteDevice.sdpSearch(BluetoothMnsObexClient.BLUETOOTH_UUID_OBEX_MNS);
                        BluetoothMapService.this.mSdpSearchInitiated = true;
                        return;
                    }
                    BluetoothMapService.this.mPermission = 2;
                    if (intent.getBooleanExtra("android.bluetooth.device.extra.ALWAYS_ALLOWED", false)) {
                        BluetoothMapService.sRemoteDevice.setMessageAccessPermission(2);
                    }
                    BluetoothMapService.this.sendConnectCancelMessage();
                    return;
                }
                return;
            }
            if (action.equals("android.bluetooth.device.action.SDP_RECORD")) {
                ParcelUuid uuid = (ParcelUuid) intent.getParcelableExtra("android.bluetooth.device.extra.UUID");
                if (uuid.equals(BluetoothMnsObexClient.BLUETOOTH_UUID_OBEX_MNS)) {
                    BluetoothMapService.this.mMnsRecord = intent.getParcelableExtra("android.bluetooth.device.extra.SDP_RECORD");
                    int status = intent.getIntExtra("android.bluetooth.device.extra.SDP_SEARCH_STATUS", -1);
                    if (BluetoothMapService.this.mBluetoothMnsObexClient != null && !BluetoothMapService.this.mSdpSearchInitiated) {
                        BluetoothMapService.this.mBluetoothMnsObexClient.setMnsRecord(BluetoothMapService.this.mMnsRecord);
                    }
                    if (status != -1 && BluetoothMapService.this.mMnsRecord != null) {
                        int c = BluetoothMapService.this.mMasInstances.size();
                        for (int i = 0; i < c; i++) {
                            ((BluetoothMapMasInstance) BluetoothMapService.this.mMasInstances.valueAt(i)).setRemoteFeatureMask(BluetoothMapService.this.mMnsRecord.getSupportedFeatures());
                        }
                    }
                    if (BluetoothMapService.this.mSdpSearchInitiated) {
                        BluetoothMapService.this.mSdpSearchInitiated = false;
                        BluetoothMapService.this.sendConnectMessage(-1);
                        return;
                    }
                    return;
                }
                return;
            }
            if (action.equals(BluetoothMapService.ACTION_SHOW_MAPS_SETTINGS)) {
                Intent in = new Intent(context, (Class<?>) BluetoothMapSettings.class);
                in.setFlags(335544320);
                context.startActivity(in);
                return;
            }
            if (action.equals("com.android.bluetooth.BluetoothMapContentObserver.action.MESSAGE_SENT")) {
                int result = getResultCode();
                boolean handled = false;
                if (BluetoothMapService.this.mSmsCapable && BluetoothMapService.this.mMasInstances != null && (masInst = (BluetoothMapMasInstance) BluetoothMapService.this.mMasInstances.get(0)) != null) {
                    intent.putExtra("result", result);
                    handled = masInst.handleSmsSendIntent(context, intent);
                }
                if (!handled) {
                    BluetoothMapContentObserver.actionMessageSentDisconnected(context, intent, result);
                    return;
                }
                return;
            }
            if (action.equals("android.bluetooth.device.action.ACL_DISCONNECTED") && BluetoothMapService.this.mIsWaitingAuthorization) {
                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                if (BluetoothMapService.sRemoteDevice != null && device != null) {
                    if (BluetoothMapService.sRemoteDevice.equals(device)) {
                        BluetoothMapService.this.mSessionStatusHandler.removeMessages(2);
                        BluetoothMapService.this.mSessionStatusHandler.obtainMessage(2).sendToTarget();
                        return;
                    }
                    return;
                }
                Log.e(BluetoothMapService.TAG, "Unexpected error!");
            }
        }
    }

    private static class BluetoothMapBinder extends IBluetoothMap.Stub implements ProfileService.IProfileServiceBinder {
        private BluetoothMapService mService;

        private BluetoothMapService getService() {
            if (!Utils.checkCaller()) {
                Log.w(BluetoothMapService.TAG, "MAP call not allowed for non-active user");
                return null;
            }
            BluetoothMapService bluetoothMapService = this.mService;
            if (bluetoothMapService == null || !bluetoothMapService.isAvailable()) {
                return null;
            }
            this.mService.enforceCallingOrSelfPermission("android.permission.BLUETOOTH", "Need BLUETOOTH permission");
            return this.mService;
        }

        BluetoothMapBinder(BluetoothMapService service) {
            this.mService = service;
        }

        @Override // com.android.bluetooth.btservice.ProfileService.IProfileServiceBinder
        public synchronized void cleanup() {
            this.mService = null;
        }

        public int getState() {
            BluetoothMapService service = getService();
            if (service == null) {
                return 0;
            }
            return getService().getState();
        }

        public BluetoothDevice getClient() {
            BluetoothMapService service = getService();
            if (service == null) {
                return null;
            }
            BluetoothDevice client = BluetoothMapService.getRemoteDevice();
            return client;
        }

        public boolean isConnected(BluetoothDevice device) {
            BluetoothMapService service = getService();
            return service != null && service.getState() == 2 && BluetoothMapService.getRemoteDevice().equals(device);
        }

        public boolean disconnect(BluetoothDevice device) {
            BluetoothMapService service = getService();
            if (service == null) {
                return false;
            }
            service.disconnect(device);
            return true;
        }

        public List<BluetoothDevice> getConnectedDevices() {
            BluetoothMapService service = getService();
            if (service == null) {
                return new ArrayList(0);
            }
            Utils.enforceBluetoothPrivilegedPermission(service);
            return service.getConnectedDevices();
        }

        public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states) {
            BluetoothMapService service = getService();
            if (service == null) {
                return new ArrayList(0);
            }
            return service.getDevicesMatchingConnectionStates(states);
        }

        public int getConnectionState(BluetoothDevice device) {
            BluetoothMapService service = getService();
            if (service == null) {
                return 0;
            }
            return service.getConnectionState(device);
        }

        public boolean setConnectionPolicy(BluetoothDevice device, int connectionPolicy) {
            BluetoothMapService service = getService();
            if (service == null) {
                return false;
            }
            return service.setConnectionPolicy(device, connectionPolicy);
        }

        public int getConnectionPolicy(BluetoothDevice device) {
            BluetoothMapService service = getService();
            if (service == null) {
                return -1;
            }
            return service.getConnectionPolicy(device);
        }
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    public void dump(StringBuilder sb) {
        super.dump(sb);
        println(sb, "mRemoteDevice: " + sRemoteDevice);
        println(sb, "sRemoteDeviceName: " + sRemoteDeviceName);
        println(sb, "mState: " + this.mState);
        println(sb, "mAppObserver: " + this.mAppObserver);
        println(sb, "mIsWaitingAuthorization: " + this.mIsWaitingAuthorization);
        println(sb, "mRemoveTimeoutMsg: " + this.mRemoveTimeoutMsg);
        println(sb, "mPermission: " + this.mPermission);
        println(sb, "mAccountChanged: " + this.mAccountChanged);
        println(sb, "mBluetoothMnsObexClient: " + this.mBluetoothMnsObexClient);
        println(sb, "mMasInstanceMap:");
        for (BluetoothMapAccountItem key : this.mMasInstanceMap.keySet()) {
            println(sb, "  " + key + " : " + this.mMasInstanceMap.get(key));
        }
        println(sb, "mEnabledAccounts:");
        for (BluetoothMapAccountItem account : this.mEnabledAccounts) {
            println(sb, "  " + account);
        }
    }
}
