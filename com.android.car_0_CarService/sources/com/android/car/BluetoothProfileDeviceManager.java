package com.android.car;

import android.bluetooth.BluetoothA2dpSink;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadsetClient;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothUuid;
import android.car.ICarBluetoothUserService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.ParcelUuid;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.Log;
import android.util.SparseArray;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes3.dex */
public class BluetoothProfileDeviceManager {
    private static final int AUTO_CONNECT_TIMEOUT_MS = 8000;
    private static final Object AUTO_CONNECT_TOKEN = new Object();
    private static final boolean DBG = true;
    private static final long REMOVE_BONDED_DELAY = 6000;
    private static final String SETTINGS_DELIMITER = ",";
    private static final String TAG = "BluetoothProfileDeviceManager";
    private static final SparseArray<BluetoothProfileInfo> sProfileActions;
    private int mAutoConnectPriority;
    private ArrayList<BluetoothDevice> mAutoConnectingDevices;
    private BluetoothA2dpSink mBluetoothA2dpSink;
    private final BluetoothAdapter mBluetoothAdapter;
    private final BluetoothBroadcastReceiver mBluetoothBroadcastReceiver;
    private BluetoothHeadsetClient mBluetoothHeadsetClient;
    private final ICarBluetoothUserService mBluetoothUserProxies;
    private final Context mContext;
    private ArrayList<BluetoothDevice> mPrioritizedDevices;
    private final String mProfileConnectionAction;
    private final int mProfileId;
    private final int[] mProfileTriggers;
    private final ParcelUuid[] mProfileUuids;
    private final String mSettingsKey;
    private final int mUserId;
    private Set<String> mBondingDevices = new HashSet();
    private final ConcurrentHashMap<String, String> mBondedDeviceMap = new ConcurrentHashMap<>();
    private final Object mPrioritizedDevicesLock = new Object();
    private final Object mAutoConnectLock = new Object();
    private boolean mConnecting = false;
    private final Handler mHandler = new Handler(CarServiceUtils.getHandlerThread("CarBluetoothService").getLooper());
    private final BluetoothProfile.ServiceListener mHfpServiceListener = new BluetoothProfile.ServiceListener() { // from class: com.android.car.BluetoothProfileDeviceManager.1
        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int profile, BluetoothProfile proxy) {
            if (profile == 16) {
                BluetoothProfileDeviceManager.this.mBluetoothHeadsetClient = (BluetoothHeadsetClient) proxy;
            }
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int profile) {
            if (profile == 16) {
                BluetoothProfileDeviceManager.this.mBluetoothHeadsetClient = null;
            }
        }
    };
    private final BluetoothProfile.ServiceListener mA2dpServiceListener = new BluetoothProfile.ServiceListener() { // from class: com.android.car.BluetoothProfileDeviceManager.2
        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int profile, BluetoothProfile proxy) {
            if (profile == 11) {
                BluetoothProfileDeviceManager.this.mBluetoothA2dpSink = (BluetoothA2dpSink) proxy;
            }
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int profile) {
            if (profile == 11) {
                BluetoothProfileDeviceManager.this.mBluetoothA2dpSink = null;
            }
        }
    };

    static {
        SparseArray<BluetoothProfileInfo> sparseArray = new SparseArray<>();
        sProfileActions = sparseArray;
        sparseArray.put(11, new BluetoothProfileInfo("android.bluetooth.a2dp-sink.profile.action.CONNECTION_STATE_CHANGED", "android.car.KEY_BLUETOOTH_A2DP_SINK_DEVICES", new ParcelUuid[]{BluetoothUuid.A2DP_SOURCE}, new int[0]));
        sProfileActions.put(16, new BluetoothProfileInfo("android.bluetooth.headsetclient.profile.action.CONNECTION_STATE_CHANGED", "android.car.KEY_BLUETOOTH_HFP_CLIENT_DEVICES", new ParcelUuid[]{BluetoothUuid.HFP_AG, BluetoothUuid.HSP_AG}, new int[]{18, 17}));
        sProfileActions.put(18, new BluetoothProfileInfo("android.bluetooth.mapmce.profile.action.CONNECTION_STATE_CHANGED", "android.car.KEY_BLUETOOTH_MAP_CLIENT_DEVICES", new ParcelUuid[]{BluetoothUuid.MAS}, new int[0]));
        sProfileActions.put(5, new BluetoothProfileInfo("android.bluetooth.pan.profile.action.CONNECTION_STATE_CHANGED", "android.car.KEY_BLUETOOTH_PAN_DEVICES", new ParcelUuid[]{BluetoothUuid.PANU}, new int[0]));
        sProfileActions.put(17, new BluetoothProfileInfo("android.bluetooth.pbapclient.profile.action.CONNECTION_STATE_CHANGED", "android.car.KEY_BLUETOOTH_PBAP_CLIENT_DEVICES", new ParcelUuid[]{BluetoothUuid.PBAP_PSE}, new int[0]));
    }

    private static class BluetoothProfileInfo {
        final String mConnectionAction;
        final int[] mProfileTriggers;
        final String mSettingsKey;
        final ParcelUuid[] mUuids;

        private BluetoothProfileInfo(String action, String settingsKey, ParcelUuid[] uuids, int[] profileTriggers) {
            this.mSettingsKey = settingsKey;
            this.mConnectionAction = action;
            this.mUuids = uuids;
            this.mProfileTriggers = profileTriggers;
        }
    }

    private class BluetoothBroadcastReceiver extends BroadcastReceiver {
        private BluetoothBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothProfileDeviceManager.this.mProfileConnectionAction.equals(action)) {
                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                int state = intent.getIntExtra("android.bluetooth.profile.extra.STATE", 0);
                BluetoothProfileDeviceManager.this.handleDeviceConnectionStateChange(device, state);
            } else if ("android.bluetooth.device.action.BOND_STATE_CHANGED".equals(action)) {
                BluetoothDevice device2 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                int state2 = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", Integer.MIN_VALUE);
                BluetoothProfileDeviceManager.this.handleDeviceBondStateChange(device2, state2);
            } else if ("android.bluetooth.device.action.UUID".equals(action)) {
                BluetoothDevice device3 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                Parcelable[] uuids = intent.getParcelableArrayExtra("android.bluetooth.device.extra.UUID");
                BluetoothProfileDeviceManager.this.handleDeviceUuidEvent(device3, uuids);
            } else if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(action)) {
                int state3 = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1);
                BluetoothProfileDeviceManager.this.handleAdapterStateChange(state3);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDeviceConnectionStateChange(BluetoothDevice device, int state) {
        logd("Connection state changed [device: " + device.getAddressOnlyforLog() + ", state: " + Utils.getConnectionStateName(state) + "]");
        StringBuilder sb = new StringBuilder();
        sb.append("mProfileId: ");
        sb.append(this.mBluetoothAdapter.getProfileConnectionState(this.mProfileId));
        logd(sb.toString());
        if (state == 2) {
            if (this.mProfileId == 16 && getProfilePriority(device) >= 100) {
                addDevice(device);
            }
            if (isAutoConnecting() && isAutoConnectingDevice(device)) {
                logd("isAutoConnecting()");
                if (isContinue(device)) {
                    logd("continue Auto Connecting");
                    continueAutoConnecting();
                    return;
                } else {
                    logd("cancel Auto Connecting");
                    cancelAutoConnecting();
                    return;
                }
            }
            handleDeviceConnectionStateChangeWithThread(device);
        }
    }

    private void handleDeviceConnectionStateChangeWithThread(final BluetoothDevice device) {
        this.mHandler.post(new Runnable() { // from class: com.android.car.-$$Lambda$BluetoothProfileDeviceManager$ZKhvR_-redzVLyp2yXf8pDxotVY
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$handleDeviceConnectionStateChangeWithThread$0$BluetoothProfileDeviceManager(device);
            }
        });
    }

    public /* synthetic */ void lambda$handleDeviceConnectionStateChangeWithThread$0$BluetoothProfileDeviceManager(BluetoothDevice device) {
        logd("isContinue isComming  " + Thread.currentThread());
        if (isContinue(device)) {
            logd("isContinue == true");
            if (getProfilePriority(device) >= 100) {
                addDevice(device);
            }
            triggerConnections(device);
            return;
        }
        logd("isContinue == false");
    }

    private boolean isContinue(BluetoothDevice device) {
        BluetoothHeadsetClient bluetoothHeadsetClient = this.mBluetoothHeadsetClient;
        if (bluetoothHeadsetClient == null || this.mBluetoothA2dpSink == null) {
            logd("HFP or A2dp  is not connected");
            return true;
        }
        List<BluetoothDevice> hfpDevices = bluetoothHeadsetClient.getConnectedDevices();
        List<BluetoothDevice> a2dpDevices = this.mBluetoothA2dpSink.getConnectedDevices();
        logd("isContinue hfpDevices.size: " + hfpDevices.size() + "   a2dpDevices.size: " + a2dpDevices.size() + "   profile: " + Utils.getProfileName(this.mProfileId));
        if (this.mProfileId == 16) {
            if (!hfpDevices.isEmpty()) {
                if (hfpDevices.size() == 1) {
                    String hfpMac = device.getAddress();
                    for (BluetoothDevice a2dpDevice : a2dpDevices) {
                        String a2dpMac = a2dpDevice.getAddress();
                        if (!hfpMac.equals(a2dpMac)) {
                            disconnectAllProfile(a2dpDevice);
                        }
                    }
                    return false;
                }
                logd("hfp.size>1 disconnect newDevice: " + device.getAddressOnlyforLog());
                String result = this.mBondedDeviceMap.get(device.getAddress());
                if (result != null && !result.isEmpty()) {
                    logd("newDevice is connected from bonded: " + device.getAddressOnlyforLog());
                    return true;
                }
                disconnect(device);
                return false;
            }
        } else {
            if (hfpDevices.isEmpty()) {
                return true;
            }
            if (hfpDevices.size() > 1) {
                logd("error  incorrect!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            }
            BluetoothDevice oldHfpBluetoothDevice = hfpDevices.get(0);
            String newDeviceMac = device.getAddress();
            String oldDeviceMac = oldHfpBluetoothDevice.getAddress();
            if (!newDeviceMac.equals(oldDeviceMac)) {
                logd("other disconnect: " + device.getAddressOnlyforLog());
                disconnect(device);
                return false;
            }
        }
        return true;
    }

    private void disconnectAllProfile(BluetoothDevice device) {
        logd("disconnectAllProfile: " + device.getAddressOnlyforLog());
        try {
            this.mBluetoothUserProxies.bluetoothDisconnectFromProfile(16, device);
            this.mBluetoothUserProxies.bluetoothDisconnectFromProfile(11, device);
            this.mBluetoothUserProxies.bluetoothDisconnectFromProfile(17, device);
        } catch (RemoteException exception) {
            logw("Failed to disconnect " + device.getAddressOnlyforLog() + ", Reason: " + exception);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDeviceBondStateChange(final BluetoothDevice device, int state) {
        logd("Bond state has changed [device: " + device.getAddressOnlyforLog() + ", state: " + Utils.getBondStateName(state) + "]");
        if (state == 10) {
            this.mBondingDevices.remove(device.getAddress());
            removeDevice(device);
        } else if (state == 11) {
            this.mBondingDevices.add(device.getAddress());
        } else if (state == 12) {
            addBondedDeviceIfSupported(device);
            this.mBondingDevices.remove(device.getAddress());
            this.mBondedDeviceMap.put(device.getAddress(), device.getAddress());
            this.mHandler.postDelayed(new Runnable() { // from class: com.android.car.-$$Lambda$BluetoothProfileDeviceManager$V5hkwsJV4sNwh0d595qUzSCg2yI
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$handleDeviceBondStateChange$1$BluetoothProfileDeviceManager(device);
                }
            }, REMOVE_BONDED_DELAY);
        }
    }

    public /* synthetic */ void lambda$handleDeviceBondStateChange$1$BluetoothProfileDeviceManager(BluetoothDevice device) {
        logw("6s delete device: " + device.getAddressOnlyforLog());
        this.mBondedDeviceMap.remove(device.getAddress());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDeviceUuidEvent(final BluetoothDevice device, Parcelable[] uuids) {
        logd("UUIDs found, device: " + device.getAddressOnlyforLog());
        if (this.mBondingDevices.remove(device.getAddress()) && uuids != null) {
            final ParcelUuid[] uuidsToSend = new ParcelUuid[uuids.length];
            for (int i = 0; i < uuidsToSend.length; i++) {
                uuidsToSend[i] = (ParcelUuid) uuids[i];
            }
            this.mHandler.post(new Runnable() { // from class: com.android.car.-$$Lambda$BluetoothProfileDeviceManager$UzcouAhWDsvoqm3b3pk9TXboaAw
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$handleDeviceUuidEvent$2$BluetoothProfileDeviceManager(device, uuidsToSend);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleAdapterStateChange(int state) {
        logd("Bluetooth Adapter state changed: " + Utils.getAdapterStateName(state));
        if (state != 12) {
            cancelAutoConnecting();
        }
        if (state == 10) {
            commit();
        }
    }

    public static BluetoothProfileDeviceManager create(Context context, int userId, ICarBluetoothUserService bluetoothUserProxies, int profileId) {
        try {
            return new BluetoothProfileDeviceManager(context, userId, bluetoothUserProxies, profileId);
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }

    private BluetoothProfileDeviceManager(Context context, int userId, ICarBluetoothUserService bluetoothUserProxies, int profileId) {
        Objects.requireNonNull(context);
        this.mContext = context;
        this.mUserId = userId;
        this.mBluetoothUserProxies = bluetoothUserProxies;
        this.mPrioritizedDevices = new ArrayList<>();
        BluetoothProfileInfo bpi = sProfileActions.get(profileId);
        if (bpi == null) {
            throw new IllegalArgumentException("Provided profile " + Utils.getProfileName(profileId) + " is unrecognized");
        }
        this.mProfileId = profileId;
        this.mSettingsKey = bpi.mSettingsKey;
        this.mProfileConnectionAction = bpi.mConnectionAction;
        this.mProfileUuids = bpi.mUuids;
        this.mProfileTriggers = bpi.mProfileTriggers;
        this.mBluetoothBroadcastReceiver = new BluetoothBroadcastReceiver();
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        Objects.requireNonNull(defaultAdapter);
        this.mBluetoothAdapter = defaultAdapter;
        connectProfile(context);
    }

    private void connectProfile(Context context) {
        this.mBluetoothAdapter.getProfileProxy(context.getApplicationContext(), this.mHfpServiceListener, 16);
        this.mBluetoothAdapter.getProfileProxy(context.getApplicationContext(), this.mA2dpServiceListener, 11);
    }

    public void start() {
        logd("Starting device management");
        load();
        synchronized (this.mAutoConnectLock) {
            this.mConnecting = false;
            this.mAutoConnectPriority = -1;
            this.mAutoConnectingDevices = null;
        }
        IntentFilter profileFilter = new IntentFilter();
        profileFilter.addAction(this.mProfileConnectionAction);
        profileFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        profileFilter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
        profileFilter.addAction("android.bluetooth.device.action.UUID");
        this.mContext.registerReceiverAsUser(this.mBluetoothBroadcastReceiver, UserHandle.CURRENT, profileFilter, null, null);
    }

    public void stop() {
        Context context;
        logd("Stopping device management");
        BluetoothBroadcastReceiver bluetoothBroadcastReceiver = this.mBluetoothBroadcastReceiver;
        if (bluetoothBroadcastReceiver != null && (context = this.mContext) != null) {
            context.unregisterReceiver(bluetoothBroadcastReceiver);
        }
        cancelAutoConnecting();
        commit();
    }

    private boolean load() {
        List<String> deviceList;
        logd("Loading device priority list snapshot using key '" + this.mSettingsKey + "'");
        String devicesStr = Settings.Secure.getStringForUser(this.mContext.getContentResolver(), this.mSettingsKey, this.mUserId);
        if (devicesStr == null || "".equals(devicesStr) || (deviceList = Arrays.asList(devicesStr.split(SETTINGS_DELIMITER))) == null) {
            return false;
        }
        ArrayList<BluetoothDevice> devices = new ArrayList<>();
        for (String address : deviceList) {
            try {
                BluetoothDevice device = this.mBluetoothAdapter.getRemoteDevice(address);
                devices.add(device);
            } catch (IllegalArgumentException e) {
                logw("Unable to parse address '" + address + "' to a device");
            }
        }
        synchronized (this.mPrioritizedDevicesLock) {
            this.mPrioritizedDevices = devices;
        }
        logd("Loaded Priority list: " + devices);
        return true;
    }

    private boolean commit() {
        StringBuilder sb = new StringBuilder();
        String delimiter = "";
        synchronized (this.mPrioritizedDevicesLock) {
            for (BluetoothDevice device : this.mPrioritizedDevices) {
                sb.append(delimiter);
                sb.append(device.getAddress());
                delimiter = SETTINGS_DELIMITER;
            }
        }
        String devicesStr = sb.toString();
        Settings.Secure.putStringForUser(this.mContext.getContentResolver(), this.mSettingsKey, devicesStr, this.mUserId);
        return true;
    }

    private void sync() {
        logd("Syncing the priority list with the adapter's list of bonded devices");
        Set<BluetoothDevice> bondedDevices = this.mBluetoothAdapter.getBondedDevices();
        Iterator<BluetoothDevice> it = bondedDevices.iterator();
        while (it.hasNext()) {
            addDevice(it.next());
        }
        synchronized (this.mPrioritizedDevicesLock) {
            ArrayList<BluetoothDevice> devices = getDeviceListSnapshot();
            for (BluetoothDevice device : devices) {
                if (!bondedDevices.contains(device)) {
                    removeDevice(device);
                }
            }
        }
    }

    public ArrayList<BluetoothDevice> getDeviceListSnapshot() {
        ArrayList<BluetoothDevice> devices;
        new ArrayList();
        synchronized (this.mPrioritizedDevicesLock) {
            devices = (ArrayList) this.mPrioritizedDevices.clone();
        }
        return devices;
    }

    public void addDevice(BluetoothDevice device) {
        if (device == null) {
            return;
        }
        synchronized (this.mPrioritizedDevicesLock) {
            if (this.mPrioritizedDevices.contains(device)) {
                logd("Add device contains remove: " + device.getAddressOnlyforLog());
                this.mPrioritizedDevices.remove(device);
            }
            logd("Add device " + device.getAddressOnlyforLog());
            this.mPrioritizedDevices.add(0, device);
            commit();
        }
    }

    public void removeDevice(BluetoothDevice device) {
        if (device == null) {
            return;
        }
        synchronized (this.mPrioritizedDevicesLock) {
            if (this.mPrioritizedDevices.contains(device)) {
                logd("Remove device " + device.getAddressOnlyforLog());
                this.mPrioritizedDevices.remove(device);
                commit();
            }
        }
    }

    public int getDeviceConnectionPriority(BluetoothDevice device) {
        int iIndexOf;
        if (device == null) {
            return -1;
        }
        logd("Get connection priority of " + device.getAddressOnlyforLog());
        synchronized (this.mPrioritizedDevicesLock) {
            iIndexOf = this.mPrioritizedDevices.indexOf(device);
        }
        return iIndexOf;
    }

    public void setDeviceConnectionPriority(BluetoothDevice device, int priority) {
        synchronized (this.mPrioritizedDevicesLock) {
            if (device != null && priority >= 0) {
                if (priority <= this.mPrioritizedDevices.size() && getDeviceConnectionPriority(device) != priority) {
                    if (this.mPrioritizedDevices.contains(device)) {
                        this.mPrioritizedDevices.remove(device);
                        if (priority > this.mPrioritizedDevices.size()) {
                            priority = this.mPrioritizedDevices.size();
                        }
                    }
                    logd("Set connection priority " + device.getAddressOnlyforLog() + " to " + priority);
                    this.mPrioritizedDevices.add(priority, device);
                    commit();
                }
            }
        }
    }

    private boolean connect(BluetoothDevice device) {
        logd("Connecting " + device.getAddressOnlyforLog());
        try {
            return this.mBluetoothUserProxies.bluetoothConnectToProfile(this.mProfileId, device);
        } catch (RemoteException e) {
            logw("Failed to connect " + device.getAddressOnlyforLog() + ", Reason: " + e);
            return false;
        }
    }

    private boolean disconnect(BluetoothDevice device) {
        logd("Disconnecting " + device.getAddressOnlyforLog());
        try {
            return this.mBluetoothUserProxies.bluetoothDisconnectFromProfile(this.mProfileId, device);
        } catch (RemoteException e) {
            logw("Failed to disconnect " + device.getAddressOnlyforLog() + ", Reason: " + e);
            return false;
        }
    }

    private int getProfilePriority(BluetoothDevice device) {
        try {
            return this.mBluetoothUserProxies.getProfilePriority(this.mProfileId, device);
        } catch (RemoteException e) {
            logw("Failed to get bluetooth stack priority for " + device.getAddressOnlyforLog() + ", Reason: " + e);
            return -1;
        }
    }

    private boolean setProfilePriority(BluetoothDevice device, int priority) {
        logd("Set " + device.getAddressOnlyforLog() + " stack priority to " + Utils.getProfilePriorityName(priority));
        try {
            this.mBluetoothUserProxies.setProfilePriority2(this.mProfileId, device, priority);
            return true;
        } catch (RemoteException e) {
            logw("Failed to set bluetooth stack priority for " + device.getAddressOnlyforLog() + ", Reason: " + e);
            return false;
        }
    }

    public void beginAutoConnecting() {
        logd("Request to begin auto connection process");
        synchronized (this.mAutoConnectLock) {
            if (isAutoConnecting()) {
                logd("Auto connect requested while we are already auto connecting.");
                return;
            }
            if (this.mBluetoothAdapter.getState() != 12) {
                logd("Bluetooth Adapter is not on, cannot connect devices");
                return;
            }
            ArrayList<BluetoothDevice> deviceListSnapshot = getDeviceListSnapshot();
            this.mAutoConnectingDevices = deviceListSnapshot;
            if (deviceListSnapshot.size() == 0) {
                logd("No saved devices to auto-connect to.");
                cancelAutoConnecting();
            } else {
                this.mConnecting = true;
                this.mAutoConnectPriority = 0;
                autoConnectWithTimeout();
            }
        }
    }

    private void autoConnectWithTimeout() {
        synchronized (this.mAutoConnectLock) {
            if (!isAutoConnecting()) {
                logd("Autoconnect process was cancelled, skipping connecting next device.");
                return;
            }
            if (this.mAutoConnectPriority >= 0 && this.mAutoConnectPriority < this.mAutoConnectingDevices.size()) {
                final BluetoothDevice device = this.mAutoConnectingDevices.get(this.mAutoConnectPriority);
                logd("Auto connecting (" + this.mAutoConnectPriority + ") device: " + device.getAddressOnlyforLog());
                this.mHandler.post(new Runnable() { // from class: com.android.car.-$$Lambda$BluetoothProfileDeviceManager$JGtQHsM8E2lST9OvRDwuUuJid8k
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$autoConnectWithTimeout$3$BluetoothProfileDeviceManager(device);
                    }
                });
                this.mHandler.postDelayed(new Runnable() { // from class: com.android.car.-$$Lambda$BluetoothProfileDeviceManager$wxyuRbjm-1jar05kNk0ntI19zX0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$autoConnectWithTimeout$4$BluetoothProfileDeviceManager(device);
                    }
                }, AUTO_CONNECT_TOKEN, 8000L);
            }
        }
    }

    public /* synthetic */ void lambda$autoConnectWithTimeout$3$BluetoothProfileDeviceManager(BluetoothDevice device) {
        boolean connectStatus = connect(device);
        if (!connectStatus) {
            logw("Connection attempt immediately failed, moving to the next device");
            continueAutoConnecting();
        }
    }

    public /* synthetic */ void lambda$autoConnectWithTimeout$4$BluetoothProfileDeviceManager(BluetoothDevice device) {
        logw("Auto connect process has timed out connecting to " + device.getAddressOnlyforLog());
        continueAutoConnecting();
    }

    private void continueAutoConnecting() {
        logd("Continue auto-connect process on next device");
        synchronized (this.mAutoConnectLock) {
            if (!isAutoConnecting()) {
                logd("Autoconnect process was cancelled, no need to continue.");
                return;
            }
            this.mHandler.removeCallbacksAndMessages(AUTO_CONNECT_TOKEN);
            int i = this.mAutoConnectPriority + 1;
            this.mAutoConnectPriority = i;
            if (i >= this.mAutoConnectingDevices.size()) {
                logd("No more devices to connect to");
                cancelAutoConnecting();
            } else {
                autoConnectWithTimeout();
            }
        }
    }

    private void cancelAutoConnecting() {
        logd("Cleaning up any auto-connect process");
        synchronized (this.mAutoConnectLock) {
            if (isAutoConnecting()) {
                this.mHandler.removeCallbacksAndMessages(AUTO_CONNECT_TOKEN);
                this.mConnecting = false;
                this.mAutoConnectPriority = -1;
                this.mAutoConnectingDevices = null;
            }
        }
    }

    public boolean isAutoConnecting() {
        boolean z;
        synchronized (this.mAutoConnectLock) {
            z = this.mConnecting;
        }
        return z;
    }

    private boolean isAutoConnectingDevice(BluetoothDevice device) {
        synchronized (this.mAutoConnectLock) {
            if (this.mAutoConnectingDevices == null) {
                return false;
            }
            return this.mAutoConnectingDevices.get(this.mAutoConnectPriority).equals(device);
        }
    }

    private void addBondedDeviceIfSupported(BluetoothDevice device) {
        logd("Add device " + device.getAddressOnlyforLog() + " if it is supported");
        if (device.getBondState() == 12 && BluetoothUuid.containsAnyUuid(device.getUuids(), this.mProfileUuids) && getProfilePriority(device) >= 100) {
            addDevice(device);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: provisionDeviceIfSupported, reason: merged with bridge method [inline-methods] */
    public void lambda$handleDeviceUuidEvent$2$BluetoothProfileDeviceManager(BluetoothDevice device, ParcelUuid[] uuids) {
        logd("Checking UUIDs for device: " + device.getAddressOnlyforLog());
        if (BluetoothUuid.containsAnyUuid(uuids, this.mProfileUuids)) {
            int devicePriority = getProfilePriority(device);
            logd("Device " + device.getAddressOnlyforLog() + " supports this profile. Priority: " + Utils.getProfilePriorityName(devicePriority));
            if (devicePriority == -1) {
                setProfilePriority(device, 100);
                return;
            }
        }
        logd("Provisioning of " + device.getAddressOnlyforLog() + " has ended without priority being set");
    }

    private void triggerConnections(BluetoothDevice device) {
        for (int profile : this.mProfileTriggers) {
            logd("Trigger connection to " + Utils.getProfileName(profile) + "on " + device.getAddressOnlyforLog());
            try {
                this.mBluetoothUserProxies.bluetoothConnectToProfile(profile, device);
            } catch (RemoteException e) {
                logw("Failed to connect " + device.getAddressOnlyforLog() + ", Reason: " + e);
            }
        }
    }

    public void dump(PrintWriter writer, String indent) {
        writer.println(indent + "BluetoothProfileDeviceManager [" + Utils.getProfileName(this.mProfileId) + "]");
        StringBuilder sb = new StringBuilder();
        sb.append(indent);
        sb.append("\tUser: ");
        sb.append(this.mUserId);
        writer.println(sb.toString());
        writer.println(indent + "\tSettings Location: " + this.mSettingsKey);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(indent);
        sb2.append("\tUser Proxies Exist: ");
        sb2.append(this.mBluetoothUserProxies != null ? "Yes" : "No");
        writer.println(sb2.toString());
        StringBuilder sb3 = new StringBuilder();
        sb3.append(indent);
        sb3.append("\tAuto-Connecting: ");
        sb3.append(isAutoConnecting() ? "Yes" : "No");
        writer.println(sb3.toString());
        writer.println(indent + "\tPriority List:");
        ArrayList<BluetoothDevice> devices = getDeviceListSnapshot();
        for (BluetoothDevice device : devices) {
            writer.println(indent + "\t\t" + device.getAddressOnlyforLog() + " - " + device.getName());
        }
    }

    private void logd(String msg) {
        Log.i(TAG, "[" + Utils.getProfileName(this.mProfileId) + " - User: " + this.mUserId + "] " + msg);
    }

    private void logw(String msg) {
        Log.w(TAG, "[" + Utils.getProfileName(this.mProfileId) + " - User: " + this.mUserId + "] " + msg);
    }
}
