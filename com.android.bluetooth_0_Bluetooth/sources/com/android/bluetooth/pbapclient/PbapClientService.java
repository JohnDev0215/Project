package com.android.bluetooth.pbapclient;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.bluetooth.BluetoothCardEntry;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.IBluetoothPbapCallBack;
import android.bluetooth.IBluetoothPbapClient;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.INetd;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.provider.CallLog;
import android.util.Log;
import android.util.Pair;
import androidx.core.os.EnvironmentCompat;
import com.android.bluetooth.R;
import com.android.bluetooth.btservice.AdapterService;
import com.android.bluetooth.btservice.ProfileService;
import com.android.bluetooth.hfpclient.connserv.HfpClientConnectionService;
import com.android.bluetooth.sdp.SdpManager;
import com.android.vcard.VCardEntry;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public class PbapClientService extends ProfileService {
    private static final boolean DBG = true;
    private static final int MAXIMUM_DEVICES = 10;
    private static final int MAX_NUMBER_CHART = 57;
    private static final int MIN_NUMBER_CHART = 48;
    private static final String SERVICE_NAME = "Phonebook Access PCE";
    private static final String TAG = "PbapClientService";
    private static final String TIMESTAMP_FORMAT = "yyyyMMdd'T'HHmmss";
    private static final String TIMESTAMP_PROPERTY = "X-IRMC-CALL-DATETIME";
    private static final boolean VDBG = false;
    private static PbapClientService sPbapClientService;
    private Map<BluetoothDevice, PbapClientStateMachine> mPbapClientStateMachineMap = new ConcurrentHashMap();
    private PbapBroadcastReceiver mPbapBroadcastReceiver = new PbapBroadcastReceiver();
    private int mSdpHandle = -1;
    private final RemoteCallbackList<IBluetoothPbapCallBack> mCallbacks = new RemoteCallbackList<>();

    @Override // com.android.bluetooth.btservice.ProfileService
    public ProfileService.IProfileServiceBinder initBinder() {
        return new BluetoothPbapClientBinder(this);
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected boolean start() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
        filter.addAction("android.intent.action.USER_UNLOCKED");
        filter.addAction("android.bluetooth.headsetclient.profile.action.CONNECTION_STATE_CHANGED");
        try {
            registerReceiver(this.mPbapBroadcastReceiver, filter);
        } catch (Exception e) {
            Log.w(TAG, "Unable to register pbapclient receiver", e);
        }
        removeUncleanAccounts();
        registerSdpRecord();
        setPbapClientService(this);
        return true;
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected boolean stop() {
        setPbapClientService(null);
        cleanUpSdpRecord();
        try {
            unregisterReceiver(this.mPbapBroadcastReceiver);
        } catch (Exception e) {
            Log.w(TAG, "Unable to unregister pbapclient receiver", e);
        }
        for (PbapClientStateMachine pbapClientStateMachine : this.mPbapClientStateMachineMap.values()) {
            pbapClientStateMachine.doQuit();
        }
        removeUncleanAccounts();
        return true;
    }

    void cleanupDevice(BluetoothDevice device) {
        Log.d(TAG, "Cleanup device: " + device.getAddressOnlyforLog());
        synchronized (this.mPbapClientStateMachineMap) {
            PbapClientStateMachine pbapClientStateMachine = this.mPbapClientStateMachineMap.get(device);
            if (pbapClientStateMachine != null) {
                this.mPbapClientStateMachineMap.remove(device);
            }
        }
    }

    private void removeUncleanAccounts() {
        AccountManager accountManager = AccountManager.get(this);
        Account[] accounts = accountManager.getAccountsByType(getString(R.string.pbap_account_type));
        for (Account acc : accounts) {
            Log.w(TAG, "Deleting " + acc);
            try {
                getContentResolver().delete(CallLog.Calls.CONTENT_URI, "subscription_id=?", new String[]{acc.name});
            } catch (IllegalArgumentException e) {
                Log.w(TAG, "Call Logs could not be deleted, they may not exist yet.");
            }
            accountManager.removeAccountExplicitly(acc);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeHfpCallLog(String accountName, Context context) {
        Log.d(TAG, "Removing call logs from " + accountName);
        ComponentName componentName = new ComponentName(context, (Class<?>) HfpClientConnectionService.class);
        String[] selectionArgs = {accountName, componentName.flattenToString()};
        try {
            getContentResolver().delete(CallLog.Calls.CONTENT_URI, "subscription_id=? AND subscription_component_name=?", selectionArgs);
        } catch (IllegalArgumentException e) {
            Log.w(TAG, "Call Logs could not be deleted, they may not exist yet.");
        }
    }

    private void registerSdpRecord() {
        SdpManager sdpManager = SdpManager.getDefaultManager();
        if (sdpManager == null) {
            Log.e(TAG, "SdpManager is null");
        } else {
            this.mSdpHandle = sdpManager.createPbapPceRecord(SERVICE_NAME, 258);
        }
    }

    private void cleanUpSdpRecord() {
        if (this.mSdpHandle < 0) {
            Log.e(TAG, "cleanUpSdpRecord, SDP record never created");
            return;
        }
        int sdpHandle = this.mSdpHandle;
        this.mSdpHandle = -1;
        SdpManager sdpManager = SdpManager.getDefaultManager();
        if (sdpManager == null) {
            Log.e(TAG, "cleanUpSdpRecord failed, sdpManager is null, sdpHandle=" + sdpHandle);
            return;
        }
        Log.i(TAG, "cleanUpSdpRecord, mSdpHandle=" + sdpHandle);
        if (!sdpManager.removeSdpRecord(sdpHandle)) {
            Log.e(TAG, "cleanUpSdpRecord, removeSdpRecord failed, sdpHandle=" + sdpHandle);
        }
    }

    private class PbapBroadcastReceiver extends BroadcastReceiver {
        private PbapBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.v(PbapClientService.TAG, "onReceive" + action);
            if (action.equals("android.bluetooth.device.action.ACL_DISCONNECTED")) {
                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                if (PbapClientService.this.getConnectionState(device) == 2) {
                    PbapClientService.this.disconnect(device);
                    return;
                }
                return;
            }
            if (action.equals("android.intent.action.USER_UNLOCKED")) {
                for (PbapClientStateMachine stateMachine : PbapClientService.this.mPbapClientStateMachineMap.values()) {
                    stateMachine.resumeDownload();
                }
                return;
            }
            if (action.equals("android.bluetooth.headsetclient.profile.action.CONNECTION_STATE_CHANGED")) {
                BluetoothDevice device2 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                int newState = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                if (newState == 0) {
                    Log.d(PbapClientService.TAG, "Received intent to disconnect HFP with " + device2.getAddressOnlyforLog());
                    PbapClientService.this.removeHfpCallLog(device2.getAddress(), context);
                }
            }
        }
    }

    private static class BluetoothPbapClientBinder extends IBluetoothPbapClient.Stub implements ProfileService.IProfileServiceBinder {
        private PbapClientService mService;

        BluetoothPbapClientBinder(PbapClientService svc) {
            this.mService = svc;
        }

        @Override // com.android.bluetooth.btservice.ProfileService.IProfileServiceBinder
        public void cleanup() {
            this.mService = null;
        }

        private PbapClientService getService() {
            if (!com.android.bluetooth.Utils.checkCaller()) {
                Log.w(PbapClientService.TAG, "PbapClient call not allowed for non-active user");
                return null;
            }
            PbapClientService pbapClientService = this.mService;
            if (pbapClientService == null || !pbapClientService.isAvailable()) {
                return null;
            }
            return this.mService;
        }

        public boolean connect(BluetoothDevice device) {
            PbapClientService service = getService();
            Log.d(PbapClientService.TAG, "PbapClient Binder connect ");
            if (service == null) {
                Log.e(PbapClientService.TAG, "PbapClient Binder connect no service");
                return false;
            }
            return service.connect(device);
        }

        public boolean connectFromScene(BluetoothDevice device, int scene) {
            PbapClientService service = getService();
            Log.d(PbapClientService.TAG, "PbapClient Binder connect ");
            if (service == null) {
                Log.e(PbapClientService.TAG, "PbapClient Binder connect no service");
                return false;
            }
            return service.connectFromScene(device, scene);
        }

        public boolean disconnect(BluetoothDevice device) {
            PbapClientService service = getService();
            if (service == null) {
                return false;
            }
            return service.disconnect(device);
        }

        public List<BluetoothDevice> getConnectedDevices() {
            PbapClientService service = getService();
            if (service == null) {
                return new ArrayList(0);
            }
            return service.getConnectedDevices();
        }

        public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states) {
            PbapClientService service = getService();
            if (service != null) {
                return service.getDevicesMatchingConnectionStates(states);
            }
            return new ArrayList(0);
        }

        public int getConnectionState(BluetoothDevice device) {
            PbapClientService service = getService();
            if (service == null) {
                return 0;
            }
            return service.getConnectionState(device);
        }

        public boolean setConnectionPolicy(BluetoothDevice device, int connectionPolicy) {
            PbapClientService service = getService();
            if (service == null) {
                return false;
            }
            return service.setConnectionPolicy(device, connectionPolicy);
        }

        public int getConnectionPolicy(BluetoothDevice device) {
            PbapClientService service = getService();
            if (service == null) {
                return -1;
            }
            return service.getConnectionPolicy(device);
        }

        public void registerCallback(IBluetoothPbapCallBack callBack) {
            PbapClientService service = getService();
            if (service != null) {
                service.mCallbacks.register(callBack);
            }
        }

        public void unregisterCallback(IBluetoothPbapCallBack callBack) {
            PbapClientService service = getService();
            if (service != null) {
                service.mCallbacks.unregister(callBack);
            }
        }
    }

    public static synchronized PbapClientService getPbapClientService() {
        if (sPbapClientService == null) {
            Log.w(TAG, "getPbapClientService(): service is null");
            return null;
        }
        if (!sPbapClientService.isAvailable()) {
            Log.w(TAG, "getPbapClientService(): service is not available");
            return null;
        }
        return sPbapClientService;
    }

    private static synchronized void setPbapClientService(PbapClientService instance) {
        sPbapClientService = instance;
    }

    public boolean connect(BluetoothDevice device) {
        if (device == null) {
            throw new IllegalArgumentException("Null device");
        }
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_PRIVILEGED", "Need BLUETOOTH_PRIVILEGED permission");
        Log.d(TAG, "Received request to ConnectPBAPPhonebook " + device.getAddressOnlyforLog() + "  policy: " + getConnectionPolicy(device));
        synchronized (this.mPbapClientStateMachineMap) {
            if (this.mPbapClientStateMachineMap.get(device) == null && this.mPbapClientStateMachineMap.size() < 10) {
                PbapClientStateMachine pbapClientStateMachine = new PbapClientStateMachine(this, device);
                pbapClientStateMachine.setScene(0);
                pbapClientStateMachine.start();
                this.mPbapClientStateMachineMap.put(device, pbapClientStateMachine);
                return true;
            }
            Log.w(TAG, "Received connect request while already connecting/connected.");
            return false;
        }
    }

    public boolean connectFromScene(BluetoothDevice device, int scene) {
        if (device == null) {
            throw new IllegalArgumentException("Null device");
        }
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_PRIVILEGED", "Need BLUETOOTH_PRIVILEGED permission");
        Log.w(TAG, "Received request to ConnectPBAPPhonebook " + device.getAddressOnlyforLog() + "  policy: " + getConnectionPolicy(device) + " scene: " + scene);
        synchronized (this.mPbapClientStateMachineMap) {
            if (this.mPbapClientStateMachineMap.get(device) == null && this.mPbapClientStateMachineMap.size() < 10) {
                PbapClientStateMachine pbapClientStateMachine = new PbapClientStateMachine(this, device);
                pbapClientStateMachine.setScene(scene);
                pbapClientStateMachine.start();
                this.mPbapClientStateMachineMap.put(device, pbapClientStateMachine);
                return true;
            }
            Log.w(TAG, "Received connect request while already connecting/connected.");
            return false;
        }
    }

    public boolean disconnect(BluetoothDevice device) {
        if (device == null) {
            throw new IllegalArgumentException("Null device");
        }
        Log.d(TAG, "disconnect: " + device.getAddressOnlyforLog());
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_PRIVILEGED", "Need BLUETOOTH_PRIVILEGED permission");
        PbapClientStateMachine pbapClientStateMachine = this.mPbapClientStateMachineMap.get(device);
        if (pbapClientStateMachine != null) {
            pbapClientStateMachine.disconnect(device);
            return true;
        }
        Log.w(TAG, "disconnect() called on unconnected device.");
        return false;
    }

    public List<BluetoothDevice> getConnectedDevices() {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        int[] desiredStates = {2};
        return getDevicesMatchingConnectionStates(desiredStates);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        List<BluetoothDevice> deviceList = new ArrayList<>(0);
        for (Map.Entry<BluetoothDevice, PbapClientStateMachine> stateMachineEntry : this.mPbapClientStateMachineMap.entrySet()) {
            int currentDeviceState = stateMachineEntry.getValue().getConnectionState();
            int length = states.length;
            int i = 0;
            while (true) {
                if (i < length) {
                    int state = states[i];
                    if (currentDeviceState != state) {
                        i++;
                    } else {
                        deviceList.add(stateMachineEntry.getKey());
                        break;
                    }
                }
            }
        }
        return deviceList;
    }

    public int getConnectionState(BluetoothDevice device) {
        if (device == null) {
            throw new IllegalArgumentException("Null device");
        }
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        PbapClientStateMachine pbapClientStateMachine = this.mPbapClientStateMachineMap.get(device);
        if (pbapClientStateMachine == null) {
            return 0;
        }
        return pbapClientStateMachine.getConnectionState(device);
    }

    public boolean setConnectionPolicy(BluetoothDevice device, int connectionPolicy) {
        if (device == null) {
            throw new IllegalArgumentException("Null device");
        }
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_PRIVILEGED", "Need BLUETOOTH_PRIVILEGED permission");
        Log.d(TAG, "Saved connectionPolicy " + device.getAddressOnlyforLog() + " = " + connectionPolicy);
        AdapterService.getAdapterService().getDatabase().setProfileConnectionPolicy(device, 17, connectionPolicy);
        if (connectionPolicy == 100) {
            connect(device);
            return true;
        }
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
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_PRIVILEGED", "Need BLUETOOTH_PRIVILEGED permission");
        return AdapterService.getAdapterService().getDatabase().getProfileConnectionPolicy(device, 17);
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    public void dump(StringBuilder sb) {
        super.dump(sb);
        for (PbapClientStateMachine stateMachine : this.mPbapClientStateMachineMap.values()) {
            stateMachine.dump(sb);
        }
    }

    public void notifyPhoneBookState(String path, int state, String address) {
        Log.d(TAG, "notifyPhoneBookState path: " + path + "  state: " + state);
        RemoteCallbackList<IBluetoothPbapCallBack> remoteCallbackList = this.mCallbacks;
        if (remoteCallbackList != null) {
            int length = remoteCallbackList.beginBroadcast();
            Log.d(TAG, "notifyPhoneBookState length: " + length);
            if (length < 1) {
                return;
            }
            try {
                this.mCallbacks.getBroadcastItem(0).onGetPhoneBookState(path, state, address);
            } catch (RemoteException exception) {
                Log.e(TAG, "notifyPhoneBookSize() - Callback # failed (" + exception + ")");
            }
            this.mCallbacks.finishBroadcast();
        }
    }

    public void notifyPhoneBookSize(String path, int size, String address) {
        Log.d(TAG, "notifyPhoneBookSize path: " + path + "  size: " + size);
        RemoteCallbackList<IBluetoothPbapCallBack> remoteCallbackList = this.mCallbacks;
        if (remoteCallbackList != null) {
            int length = remoteCallbackList.beginBroadcast();
            Log.d(TAG, "notifyPhoneBookSize length: " + length);
            if (length < 1) {
                return;
            }
            try {
                this.mCallbacks.getBroadcastItem(0).onGetPhoneBookSize(path, size, address);
            } catch (RemoteException exception) {
                Log.e(TAG, "notifyPhoneBookSize() - Callback # failed (" + exception + ")");
            }
            this.mCallbacks.finishBroadcast();
        }
    }

    public void notifyPhoneBookContent(String path, List<VCardEntry> cardEntryList, String address) {
        Log.d(TAG, "notifyPhoneBookContent cardEntryList: " + cardEntryList.size());
        List<BluetoothCardEntry> bluetoothCardEntryList = new ArrayList<>();
        for (VCardEntry cardEntry : cardEntryList) {
            BluetoothCardEntry bluetoothCardEntry = new BluetoothCardEntry();
            bluetoothCardEntry.setDisplayName(cardEntry.getDisplayName());
            bluetoothCardEntry.setKatakana(cardEntry.getPhoneticFamilyName() + " " + cardEntry.getPhoneticGivenName());
            String number = INetd.NEXTHOP_NONE;
            List<VCardEntry.PhoneData> phoneList = cardEntry.getPhoneList();
            if (phoneList != null && !phoneList.isEmpty()) {
                for (VCardEntry.PhoneData data : phoneList) {
                    String nu = data.getNumber().replace("-", INetd.NEXTHOP_NONE);
                    number = number.isEmpty() ? nu : number + "and" + nu;
                }
            } else {
                Log.e(TAG, "notifyPhoneBookContent phoneList is Null");
            }
            bluetoothCardEntry.setNumber(number);
            bluetoothCardEntryList.add(bluetoothCardEntry);
        }
        Log.d(TAG, "notifyPhoneBookContent cardEntryList: " + bluetoothCardEntryList.size());
        RemoteCallbackList<IBluetoothPbapCallBack> remoteCallbackList = this.mCallbacks;
        if (remoteCallbackList != null) {
            int length = remoteCallbackList.beginBroadcast();
            Log.d(TAG, "notifyPhoneBookContent length: " + length);
            if (length < 1) {
                return;
            }
            try {
                try {
                    this.mCallbacks.getBroadcastItem(0).onGetPhoneBookComplete(path, bluetoothCardEntryList, address);
                } catch (RemoteException e) {
                    exception = e;
                    Log.e(TAG, "notifyPhoneBookContent - Callback # failed (" + exception + ")");
                }
            } catch (RemoteException e2) {
                exception = e2;
            }
            this.mCallbacks.finishBroadcast();
        }
    }

    public void notifyCallLogState(String path, int state, String address) {
        Log.d(TAG, "notifyCallLogState path: " + path + "  state: " + state);
        RemoteCallbackList<IBluetoothPbapCallBack> remoteCallbackList = this.mCallbacks;
        if (remoteCallbackList != null) {
            int length = remoteCallbackList.beginBroadcast();
            Log.d(TAG, "notifyCallLogState length: " + length);
            if (length < 1) {
                return;
            }
            try {
                this.mCallbacks.getBroadcastItem(0).onGetCalllogState(path, state, address);
            } catch (RemoteException exception) {
                Log.e(TAG, "notifyCallLogState() - Callback # failed (" + exception + ")");
            }
            this.mCallbacks.finishBroadcast();
        }
    }

    public void notifyCallLogContent(String path, List<VCardEntry> cardEntryList, String address) {
        int type;
        String phoneNumber;
        Iterator<VCardEntry> it;
        long dateTime;
        Log.d(TAG, "notifyCallLogContent cardEntryList: " + cardEntryList.size());
        List<BluetoothCardEntry> bluetoothCardEntryList = new ArrayList<>();
        if (path.equals(PbapClientConnectionHandler.ICH_PATH)) {
            type = 1;
        } else if (path.equals(PbapClientConnectionHandler.OCH_PATH)) {
            type = 2;
        } else if (!path.equals(PbapClientConnectionHandler.MCH_PATH)) {
            type = 0;
        } else {
            type = 3;
        }
        Iterator<VCardEntry> it2 = cardEntryList.iterator();
        while (it2.hasNext()) {
            VCardEntry cardEntry = it2.next();
            BluetoothCardEntry bluetoothCardEntry = new BluetoothCardEntry();
            String cardName = cardEntry.getDisplayName();
            bluetoothCardEntry.setType(type);
            List<VCardEntry.PhoneData> phoneList = cardEntry.getPhoneList();
            if (phoneList != null && !phoneList.isEmpty()) {
                String phoneNumber2 = phoneList.get(0).getNumber();
                if (phoneNumber2.equalsIgnoreCase(EnvironmentCompat.MEDIA_UNKNOWN)) {
                    phoneNumber2 = INetd.NEXTHOP_NONE;
                    cardName = INetd.NEXTHOP_NONE;
                } else if (!isNumMethod(phoneNumber2) && cardName.isEmpty()) {
                    Log.d(TAG, "notifyCallLogContent phoneNumber\u3000isn`t no. and cardName is Empty");
                    phoneNumber2 = INetd.NEXTHOP_NONE;
                    cardName = INetd.NEXTHOP_NONE;
                }
                bluetoothCardEntry.setNumber(phoneNumber2);
                phoneNumber = cardName;
            } else {
                Log.e(TAG, "notifyCallLogContent callLogList is Null");
                phoneNumber = cardName;
            }
            bluetoothCardEntry.setDisplayName(phoneNumber);
            List<Pair<String, String>> unknownXDataList = cardEntry.getUnknownXData();
            SimpleDateFormat parser = new SimpleDateFormat(TIMESTAMP_FORMAT);
            if (unknownXDataList != null) {
                for (Pair<String, String> pair : unknownXDataList) {
                    int type2 = type;
                    if (!((String) pair.first).startsWith(TIMESTAMP_PROPERTY)) {
                        it = it2;
                    } else {
                        try {
                            dateTime = parser.parse((String) pair.second).getTime();
                            it = it2;
                        } catch (ParseException e) {
                            it = it2;
                        }
                        try {
                            bluetoothCardEntry.setDateTime(dateTime);
                        } catch (ParseException e2) {
                            Log.d(TAG, "Failed to parse date ");
                        }
                    }
                    type = type2;
                    it2 = it;
                }
            }
            bluetoothCardEntryList.add(bluetoothCardEntry);
            type = type;
            it2 = it2;
        }
        Log.d(TAG, "notifyCallLogContent cardEntryList: " + bluetoothCardEntryList.size() + " mCallbacks: " + this.mCallbacks);
        RemoteCallbackList<IBluetoothPbapCallBack> remoteCallbackList = this.mCallbacks;
        if (remoteCallbackList != null) {
            int length = remoteCallbackList.beginBroadcast();
            Log.d(TAG, "notifyCallLogContent length: " + length);
            if (length < 1) {
                return;
            }
            try {
                Log.d(TAG, "onGetCalllogComplete path: " + path);
                try {
                    this.mCallbacks.getBroadcastItem(0).onGetCalllogComplete(path, bluetoothCardEntryList, address);
                } catch (RemoteException e3) {
                    exception = e3;
                    Log.e(TAG, "notifyCallLogContent - Callback # failed (" + exception + ")");
                }
            } catch (RemoteException e4) {
                exception = e4;
            }
            this.mCallbacks.finishBroadcast();
        }
    }

    private boolean isNumMethod(String str) {
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch >= '0' && ch <= '9') {
                return true;
            }
        }
        return false;
    }
}
