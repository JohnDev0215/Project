package com.android.bluetooth.btservice;

import android.R;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.INetd;
import android.os.ParcelUuid;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.util.Log;
import android.util.Pair;
import com.android.bluetooth.BluetoothStatsLog;
import com.android.bluetooth.Utils;
import com.android.bluetooth.btservice.RemoteDevices;
import com.android.bluetooth.util.AesUtil;
import com.android.vcard.VCardConfig;
import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
class AdapterProperties {
    private static final String A2DP_OFFLOAD_DISABLED_PROPERTY = "persist.bluetooth.a2dp_offload.disabled";
    private static final String A2DP_OFFLOAD_SUPPORTED_PROPERTY = "ro.bluetooth.a2dp_offload.supported";
    private static final int BD_ADDR_LEN = 6;
    private static final String CPU_KEY = "ro.serialno";
    private static final int CPU_LENGTH = 5;
    private static final boolean DBG = true;
    private static final String DEFAULT_BLUETOOTH_NAME = "persist.bluetooth.default.name";
    private static final long DEFAULT_DISCOVERY_TIMEOUT_MS = 12800;
    static final int MAX_CONNECTED_AUDIO_DEVICES_LOWER_BOND = 1;
    private static final String MAX_CONNECTED_AUDIO_DEVICES_PROPERTY = "persist.bluetooth.maxconnectedaudiodevices";
    private static final int MAX_CONNECTED_AUDIO_DEVICES_UPPER_BOUND = 5;
    private static final String TAG = "AdapterProperties";
    private static final boolean VDBG = false;
    private volatile byte[] mAddress;
    private volatile BluetoothClass mBluetoothClass;
    private String mCountryCodeJson;
    private volatile int mDiscoverableTimeout;
    private boolean mDiscovering;
    private long mDiscoveryEndMs;
    private boolean mIsActivityAndEnergyReporting;
    private boolean mIsDebugLogSupported;
    private boolean mIsExtendedScanSupported;
    private boolean mIsLe2MPhySupported;
    private boolean mIsLeCodedPhySupported;
    private boolean mIsLeExtendedAdvertisingSupported;
    private boolean mIsLePeriodicAdvertisingSupported;
    private int mLeMaximumAdvertisingDataLength;
    private volatile String mName;
    private int mNumOfAdvertisementInstancesSupported;
    private int mNumOfOffloadedIrkSupported;
    private int mNumOfOffloadedScanFilterSupported;
    private int mOffloadedScanResultStorageBytes;
    private int mProfilesConnected;
    private int mProfilesConnecting;
    private int mProfilesDisconnecting;
    private boolean mReceiverRegistered;
    private RemoteDevices mRemoteDevices;
    private boolean mRpaOffloadSupported;
    private volatile int mScanMode;
    private AdapterService mService;
    private int mTotNumOfTrackableAdv;
    private volatile ParcelUuid[] mUuids;
    private int mVersSupported;
    private volatile int mLocalIOCapability = 255;
    private volatile int mLocalIOCapabilityBLE = 255;
    private CopyOnWriteArrayList<BluetoothDevice> mBondedDevices = new CopyOnWriteArrayList<>();
    private final HashMap<Integer, Pair<Integer, Integer>> mProfileConnectionState = new HashMap<>();
    private volatile int mConnectionState = 0;
    private volatile int mState = 10;
    private int mMaxConnectedAudioDevices = 1;
    private boolean mA2dpOffloadEnabled = false;
    private BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.android.bluetooth.btservice.AdapterProperties.1
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == null) {
                Log.w(AdapterProperties.TAG, "Received intent with null action");
            }
            byte b = -1;
            switch (action.hashCode()) {
                case -1765782003:
                    if (action.equals("android.bluetooth.sap.profile.action.CONNECTION_STATE_CHANGED")) {
                        b = 11;
                    }
                    break;
                case -1676167190:
                    if (action.equals("android.bluetooth.pbap.profile.action.CONNECTION_STATE_CHANGED")) {
                        b = Ascii.CR;
                    }
                    break;
                case -1021360715:
                    if (action.equals("android.bluetooth.input.profile.action.CONNECTION_STATE_CHANGED")) {
                        b = 6;
                    }
                    break;
                case -843338808:
                    if (action.equals("android.bluetooth.pan.profile.action.CONNECTION_STATE_CHANGED")) {
                        b = 8;
                    }
                    break;
                case -612790895:
                    if (action.equals("android.bluetooth.hearingaid.profile.action.CONNECTION_STATE_CHANGED")) {
                        b = 3;
                    }
                    break;
                case -83749707:
                    if (action.equals("android.bluetooth.pbapclient.profile.action.CONNECTION_STATE_CHANGED")) {
                        b = 12;
                    }
                    break;
                case 40146574:
                    if (action.equals("android.bluetooth.a2dp-sink.profile.action.CONNECTION_STATE_CHANGED")) {
                        b = 4;
                    }
                    break;
                case 448318136:
                    if (action.equals("android.bluetooth.headsetclient.profile.action.CONNECTION_STATE_CHANGED")) {
                        b = 2;
                    }
                    break;
                case 545516589:
                    if (action.equals("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED")) {
                        b = 0;
                    }
                    break;
                case 655892284:
                    if (action.equals("android.bluetooth.mapmce.profile.action.CONNECTION_STATE_CHANGED")) {
                        b = 10;
                    }
                    break;
                case 921398788:
                    if (action.equals("android.bluetooth.hiddevice.profile.action.CONNECTION_STATE_CHANGED")) {
                        b = 5;
                    }
                    break;
                case 926397831:
                    if (action.equals("android.bluetooth.map.profile.action.CONNECTION_STATE_CHANGED")) {
                        b = 9;
                    }
                    break;
                case 1244161670:
                    if (action.equals("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED")) {
                        b = 1;
                    }
                    break;
                case 1347806984:
                    if (action.equals("android.bluetooth.avrcp-controller.profile.action.CONNECTION_STATE_CHANGED")) {
                        b = 7;
                    }
                    break;
            }
            switch (b) {
                case 0:
                    AdapterProperties.this.sendConnectionStateChange(1, intent);
                    break;
                case 1:
                    AdapterProperties.this.sendConnectionStateChange(2, intent);
                    break;
                case 2:
                    AdapterProperties.this.sendConnectionStateChange(16, intent);
                    break;
                case 3:
                    AdapterProperties.this.sendConnectionStateChange(21, intent);
                    break;
                case 4:
                    AdapterProperties.this.sendConnectionStateChange(11, intent);
                    break;
                case 5:
                    AdapterProperties.this.sendConnectionStateChange(19, intent);
                    break;
                case 6:
                    AdapterProperties.this.sendConnectionStateChange(4, intent);
                    break;
                case 7:
                    AdapterProperties.this.sendConnectionStateChange(12, intent);
                    break;
                case 8:
                    AdapterProperties.this.sendConnectionStateChange(5, intent);
                    break;
                case 9:
                    AdapterProperties.this.sendConnectionStateChange(9, intent);
                    break;
                case 10:
                    AdapterProperties.this.sendConnectionStateChange(18, intent);
                    break;
                case 11:
                    AdapterProperties.this.sendConnectionStateChange(10, intent);
                    break;
                case 12:
                    AdapterProperties.this.sendConnectionStateChange(17, intent);
                    break;
                case 13:
                    AdapterProperties.this.sendConnectionStateChange(6, intent);
                    break;
                default:
                    Log.w(AdapterProperties.TAG, "Received unknown intent " + intent);
                    break;
            }
        }
    };
    private final Object mObject = new Object();
    private BluetoothAdapter mAdapter = BluetoothAdapter.getDefaultAdapter();

    AdapterProperties(AdapterService service) {
        this.mService = service;
        invalidateBluetoothCaches();
    }

    public void init(RemoteDevices remoteDevices) {
        this.mProfileConnectionState.clear();
        this.mRemoteDevices = remoteDevices;
        int configDefaultMaxConnectedAudioDevices = this.mService.getResources().getInteger(R.integer.config_attentiveWarningDuration);
        int propertyOverlayedMaxConnectedAudioDevices = SystemProperties.getInt(MAX_CONNECTED_AUDIO_DEVICES_PROPERTY, configDefaultMaxConnectedAudioDevices);
        this.mMaxConnectedAudioDevices = Math.min(Math.max(propertyOverlayedMaxConnectedAudioDevices, 1), 5);
        Log.i(TAG, "init(), maxConnectedAudioDevices, default=" + configDefaultMaxConnectedAudioDevices + ", propertyOverlayed=" + propertyOverlayedMaxConnectedAudioDevices + ", finalValue=" + this.mMaxConnectedAudioDevices);
        boolean z = false;
        if (SystemProperties.getBoolean(A2DP_OFFLOAD_SUPPORTED_PROPERTY, false) && !SystemProperties.getBoolean(A2DP_OFFLOAD_DISABLED_PROPERTY, false)) {
            z = true;
        }
        this.mA2dpOffloadEnabled = z;
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
        filter.addAction("android.bluetooth.headsetclient.profile.action.CONNECTION_STATE_CHANGED");
        filter.addAction("android.bluetooth.hearingaid.profile.action.CONNECTION_STATE_CHANGED");
        filter.addAction("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
        filter.addAction("android.bluetooth.a2dp-sink.profile.action.CONNECTION_STATE_CHANGED");
        filter.addAction("android.bluetooth.hiddevice.profile.action.CONNECTION_STATE_CHANGED");
        filter.addAction("android.bluetooth.input.profile.action.CONNECTION_STATE_CHANGED");
        filter.addAction("android.bluetooth.avrcp-controller.profile.action.CONNECTION_STATE_CHANGED");
        filter.addAction("android.bluetooth.pan.profile.action.CONNECTION_STATE_CHANGED");
        filter.addAction("android.bluetooth.map.profile.action.CONNECTION_STATE_CHANGED");
        filter.addAction("android.bluetooth.mapmce.profile.action.CONNECTION_STATE_CHANGED");
        filter.addAction("android.bluetooth.sap.profile.action.CONNECTION_STATE_CHANGED");
        filter.addAction("android.bluetooth.pbapclient.profile.action.CONNECTION_STATE_CHANGED");
        this.mService.registerReceiver(this.mReceiver, filter);
        this.mReceiverRegistered = true;
        invalidateBluetoothCaches();
    }

    public void cleanup() {
        this.mRemoteDevices = null;
        this.mProfileConnectionState.clear();
        if (this.mReceiverRegistered) {
            this.mService.unregisterReceiver(this.mReceiver);
            this.mReceiverRegistered = false;
        }
        this.mService = null;
        this.mBondedDevices.clear();
        invalidateBluetoothCaches();
    }

    private static void invalidateGetProfileConnectionStateCache() {
        BluetoothAdapter.invalidateGetProfileConnectionStateCache();
    }

    private static void invalidateIsOffloadedFilteringSupportedCache() {
        BluetoothAdapter.invalidateIsOffloadedFilteringSupportedCache();
    }

    private static void invalidateGetBondStateCache() {
        BluetoothDevice.invalidateBluetoothGetBondStateCache();
    }

    private static void invalidateBluetoothCaches() {
        invalidateGetProfileConnectionStateCache();
        invalidateIsOffloadedFilteringSupportedCache();
        invalidateGetBondStateCache();
    }

    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    String getName() {
        return this.mName;
    }

    boolean setName(String name) {
        boolean adapterPropertyNative;
        synchronized (this.mObject) {
            adapterPropertyNative = this.mService.setAdapterPropertyNative(1, name.getBytes());
        }
        return adapterPropertyNative;
    }

    boolean setBluetoothClass(BluetoothClass bluetoothClass) {
        boolean result;
        synchronized (this.mObject) {
            result = this.mService.setAdapterPropertyNative(4, bluetoothClass.getClassOfDeviceBytes());
            if (result) {
                this.mBluetoothClass = bluetoothClass;
            }
        }
        return result;
    }

    BluetoothClass getBluetoothClass() {
        BluetoothClass bluetoothClass;
        synchronized (this.mObject) {
            bluetoothClass = this.mBluetoothClass;
        }
        return bluetoothClass;
    }

    boolean setIoCapability(int capability) {
        boolean result;
        synchronized (this.mObject) {
            result = this.mService.setAdapterPropertyNative(14, Utils.intToByteArray(capability));
            if (result) {
                this.mLocalIOCapability = capability;
            }
        }
        return result;
    }

    int getIoCapability() {
        int i;
        synchronized (this.mObject) {
            i = this.mLocalIOCapability;
        }
        return i;
    }

    boolean setLeIoCapability(int capability) {
        boolean result;
        synchronized (this.mObject) {
            result = this.mService.setAdapterPropertyNative(15, Utils.intToByteArray(capability));
            if (result) {
                this.mLocalIOCapabilityBLE = capability;
            }
        }
        return result;
    }

    int getLeIoCapability() {
        int i;
        synchronized (this.mObject) {
            i = this.mLocalIOCapabilityBLE;
        }
        return i;
    }

    int getScanMode() {
        return this.mScanMode;
    }

    boolean setScanMode(int scanMode) {
        boolean adapterPropertyNative;
        synchronized (this.mObject) {
            adapterPropertyNative = this.mService.setAdapterPropertyNative(7, Utils.intToByteArray(scanMode));
        }
        return adapterPropertyNative;
    }

    ParcelUuid[] getUuids() {
        return this.mUuids;
    }

    byte[] getAddress() {
        return this.mAddress;
    }

    void setConnectionState(int connectionState) {
        this.mConnectionState = connectionState;
    }

    int getConnectionState() {
        return this.mConnectionState;
    }

    void setState(int state) {
        debugLog("Setting state to " + BluetoothAdapter.nameForState(state));
        this.mState = state;
    }

    int getState() {
        return this.mState;
    }

    int getNumOfAdvertisementInstancesSupported() {
        return this.mNumOfAdvertisementInstancesSupported;
    }

    boolean isRpaOffloadSupported() {
        return this.mRpaOffloadSupported;
    }

    int getNumOfOffloadedIrkSupported() {
        return this.mNumOfOffloadedIrkSupported;
    }

    int getNumOfOffloadedScanFilterSupported() {
        return this.mNumOfOffloadedScanFilterSupported;
    }

    int getOffloadedScanResultStorage() {
        return this.mOffloadedScanResultStorageBytes;
    }

    boolean isActivityAndEnergyReportingSupported() {
        return this.mIsActivityAndEnergyReporting;
    }

    boolean isLe2MPhySupported() {
        return this.mIsLe2MPhySupported;
    }

    boolean isLeCodedPhySupported() {
        return this.mIsLeCodedPhySupported;
    }

    boolean isLeExtendedAdvertisingSupported() {
        return this.mIsLeExtendedAdvertisingSupported;
    }

    boolean isLePeriodicAdvertisingSupported() {
        return this.mIsLePeriodicAdvertisingSupported;
    }

    int getLeMaximumAdvertisingDataLength() {
        return this.mLeMaximumAdvertisingDataLength;
    }

    int getTotalNumOfTrackableAdvertisements() {
        return this.mTotNumOfTrackableAdv;
    }

    int getMaxConnectedAudioDevices() {
        return this.mMaxConnectedAudioDevices;
    }

    boolean isA2dpOffloadEnabled() {
        return this.mA2dpOffloadEnabled;
    }

    BluetoothDevice[] getBondedDevices() {
        BluetoothDevice[] bondedDeviceList = new BluetoothDevice[0];
        try {
            bondedDeviceList = (BluetoothDevice[]) this.mBondedDevices.toArray(bondedDeviceList);
        } catch (ArrayStoreException e) {
            errorLog("Error retrieving bonded device array");
        }
        infoLog("getBondedDevices: length=" + bondedDeviceList.length);
        return bondedDeviceList;
    }

    void onBondStateChanged(BluetoothDevice device, int state) {
        if (device == null) {
            Log.w(TAG, "onBondStateChanged, device is null");
            return;
        }
        try {
            byte[] addrByte = Utils.getByteAddress(device);
            RemoteDevices.DeviceProperties prop = this.mRemoteDevices.getDeviceProperties(device);
            if (prop == null) {
                prop = this.mRemoteDevices.addDeviceProperties(addrByte);
            }
            prop.setBondState(state);
            if (state == 12) {
                if (!this.mBondedDevices.contains(device)) {
                    debugLog("Adding bonded device:" + device.getAddressOnlyforLog());
                    this.mBondedDevices.add(device);
                }
            } else if (state == 10) {
                if (this.mBondedDevices.remove(device)) {
                    debugLog("Removing bonded device:" + device.getAddressOnlyforLog());
                } else {
                    debugLog("Failed to remove device: " + device.getAddressOnlyforLog());
                }
            }
            invalidateGetBondStateCache();
        } catch (Exception ee) {
            Log.w(TAG, "onBondStateChanged: Exception ", ee);
        }
    }

    int getDiscoverableTimeout() {
        return this.mDiscoverableTimeout;
    }

    boolean setDiscoverableTimeout(int timeout) {
        boolean adapterPropertyNative;
        synchronized (this.mObject) {
            adapterPropertyNative = this.mService.setAdapterPropertyNative(9, Utils.intToByteArray(timeout));
        }
        return adapterPropertyNative;
    }

    int getProfileConnectionState(int profile) {
        synchronized (this.mObject) {
            Pair<Integer, Integer> p = this.mProfileConnectionState.get(Integer.valueOf(profile));
            if (p != null) {
                return ((Integer) p.first).intValue();
            }
            return 0;
        }
    }

    long discoveryEndMillis() {
        return this.mDiscoveryEndMs;
    }

    boolean isDiscovering() {
        return this.mDiscovering;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendConnectionStateChange(int profile, Intent connIntent) {
        BluetoothDevice device = (BluetoothDevice) connIntent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        int prevState = connIntent.getIntExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", -1);
        int state = connIntent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
        Log.d(TAG, "PROFILE_CONNECTION_STATE_CHANGE: profile=" + profile + ", device=" + device.getAddressOnlyforLog() + ", " + prevState + " -> " + state);
        BluetoothStatsLog.write(68, state, 0, profile, this.mService.obfuscateAddress(device), this.mService.getMetricId(device));
        if (!isNormalStateTransition(prevState, state)) {
            Log.w(TAG, "PROFILE_CONNECTION_STATE_CHANGE: unexpected transition for profile=" + profile + ", device=" + device.getAddressOnlyforLog() + ", " + prevState + " -> " + state);
        }
        sendConnectionStateChange(device, profile, state, prevState);
    }

    void sendConnectionStateChange(BluetoothDevice device, int profile, int state, int prevState) {
        if (!validateProfileConnectionState(state) || !validateProfileConnectionState(prevState)) {
            errorLog("sendConnectionStateChange: invalid state transition " + prevState + " -> " + state);
            return;
        }
        synchronized (this.mObject) {
            updateProfileConnectionState(profile, state, prevState);
            if (updateCountersAndCheckForConnectionStateChange(state, prevState)) {
                int newAdapterState = convertToAdapterState(state);
                int prevAdapterState = convertToAdapterState(prevState);
                setConnectionState(newAdapterState);
                Intent intent = new Intent("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED");
                intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
                intent.putExtra("android.bluetooth.adapter.extra.CONNECTION_STATE", newAdapterState);
                intent.putExtra("android.bluetooth.adapter.extra.PREVIOUS_CONNECTION_STATE", prevAdapterState);
                intent.addFlags(VCardConfig.FLAG_APPEND_TYPE_PARAM);
                Log.d(TAG, "ADAPTER_CONNECTION_STATE_CHANGE: " + device.getAddressOnlyforLog() + ": " + prevAdapterState + " -> " + newAdapterState);
                if (!isNormalStateTransition(prevState, state)) {
                    Log.w(TAG, "ADAPTER_CONNECTION_STATE_CHANGE: unexpected transition for profile=" + profile + ", device=" + device.getAddressOnlyforLog() + ", " + prevState + " -> " + state);
                }
                this.mService.sendBroadcastAsUser(intent, UserHandle.ALL, ProfileService.BLUETOOTH_PERM);
            }
        }
    }

    private boolean validateProfileConnectionState(int state) {
        return state == 0 || state == 1 || state == 2 || state == 3;
    }

    private static int convertToAdapterState(int state) {
        if (state == 0) {
            return 0;
        }
        if (state == 1) {
            return 1;
        }
        if (state == 2) {
            return 2;
        }
        if (state == 3) {
            return 3;
        }
        Log.e(TAG, "convertToAdapterState, unknow state " + state);
        return -1;
    }

    private static boolean isNormalStateTransition(int prevState, int nextState) {
        if (prevState == 0) {
            return nextState == 1;
        }
        if (prevState != 1) {
            if (prevState == 2) {
                return nextState == 3;
            }
            if (prevState != 3) {
                return false;
            }
        }
        return nextState == 0 || nextState == 2;
    }

    private boolean updateCountersAndCheckForConnectionStateChange(int state, int prevState) {
        if (prevState == 1) {
            int i = this.mProfilesConnecting;
            if (i > 0) {
                this.mProfilesConnecting = i - 1;
            } else {
                Log.e(TAG, "mProfilesConnecting " + this.mProfilesConnecting);
                throw new IllegalStateException("Invalid state transition, " + prevState + " -> " + state);
            }
        } else if (prevState != 2) {
            if (prevState == 3) {
                int i2 = this.mProfilesDisconnecting;
                if (i2 > 0) {
                    this.mProfilesDisconnecting = i2 - 1;
                } else {
                    Log.e(TAG, "mProfilesDisconnecting " + this.mProfilesDisconnecting);
                    throw new IllegalStateException("Invalid state transition, " + prevState + " -> " + state);
                }
            }
        } else {
            int i3 = this.mProfilesConnected;
            if (i3 > 0) {
                this.mProfilesConnected = i3 - 1;
            } else {
                Log.e(TAG, "mProfilesConnected " + this.mProfilesConnected);
                throw new IllegalStateException("Invalid state transition, " + prevState + " -> " + state);
            }
        }
        if (state == 0) {
            return this.mProfilesConnected == 0 && this.mProfilesConnecting == 0;
        }
        if (state == 1) {
            int i4 = this.mProfilesConnecting + 1;
            this.mProfilesConnecting = i4;
            return this.mProfilesConnected == 0 && i4 == 1;
        }
        if (state != 2) {
            if (state != 3) {
                return true;
            }
            int i5 = this.mProfilesDisconnecting + 1;
            this.mProfilesDisconnecting = i5;
            return this.mProfilesConnected == 0 && i5 == 1;
        }
        int i6 = this.mProfilesConnected + 1;
        this.mProfilesConnected = i6;
        return i6 == 1;
    }

    private void updateProfileConnectionState(int profile, int newState, int oldState) {
        int numDev = 1;
        int newHashState = newState;
        boolean update = true;
        Pair<Integer, Integer> stateNumDev = this.mProfileConnectionState.get(Integer.valueOf(profile));
        if (stateNumDev != null) {
            int currHashState = ((Integer) stateNumDev.first).intValue();
            numDev = ((Integer) stateNumDev.second).intValue();
            if (newState == currHashState) {
                numDev++;
            } else if (newState == 2 || (newState == 1 && currHashState != 2)) {
                numDev = 1;
            } else if (numDev == 1 && oldState == currHashState) {
                update = true;
            } else if (numDev > 1 && oldState == currHashState) {
                numDev--;
                if (currHashState == 2 || currHashState == 1) {
                    newHashState = currHashState;
                }
            } else {
                update = false;
            }
        }
        if (update) {
            this.mProfileConnectionState.put(Integer.valueOf(profile), new Pair<>(Integer.valueOf(newHashState), Integer.valueOf(numDev)));
            invalidateGetProfileConnectionStateCache();
        }
    }

    void adapterPropertyChangedCallback(int[] types, byte[][] values) {
        for (int i = 0; i < types.length; i++) {
            byte[] val = values[i];
            int type = types[i];
            infoLog("adapterPropertyChangedCallback with type:" + type + " len:" + val.length);
            synchronized (this.mObject) {
                switch (type) {
                    case 1:
                        this.mName = new String(val);
                        Intent intent = new Intent("android.bluetooth.adapter.action.LOCAL_NAME_CHANGED");
                        intent.putExtra("android.bluetooth.adapter.extra.LOCAL_NAME", this.mName);
                        intent.addFlags(VCardConfig.FLAG_APPEND_TYPE_PARAM);
                        this.mService.sendBroadcastAsUser(intent, UserHandle.ALL, ProfileService.BLUETOOTH_PERM);
                        debugLog("Name is: " + this.mName);
                        break;
                    case 2:
                        this.mAddress = val;
                        String address = Utils.getAddressStringFromByte(this.mAddress);
                        debugLog("Address is:" + AesUtil.encrypt(address));
                        Intent intent2 = new Intent("android.bluetooth.adapter.action.BLUETOOTH_ADDRESS_CHANGED");
                        intent2.putExtra("android.bluetooth.adapter.extra.BLUETOOTH_ADDRESS", address);
                        intent2.addFlags(VCardConfig.FLAG_APPEND_TYPE_PARAM);
                        this.mService.sendBroadcastAsUser(intent2, UserHandle.ALL, ProfileService.BLUETOOTH_PERM);
                        break;
                    case 3:
                        this.mUuids = Utils.byteArrayToUuid(val);
                        break;
                    case 4:
                        if (val != null && val.length == 3) {
                            int bluetoothClass = (val[0] << Ascii.DLE) + (val[1] << 8) + val[2];
                            if (bluetoothClass != 0) {
                                this.mBluetoothClass = new BluetoothClass(bluetoothClass);
                            }
                            debugLog("BT Class:" + this.mBluetoothClass);
                            break;
                        }
                        debugLog("Invalid BT CoD value from stack.");
                        return;
                    case 5:
                    case 6:
                    case 10:
                    case 11:
                    case 12:
                    default:
                        try {
                            errorLog("Property change not handled in Java land:" + type);
                        } catch (Throwable th) {
                            throw th;
                        }
                        break;
                    case 7:
                        int mode = Utils.byteArrayToInt(val, 0);
                        this.mScanMode = AdapterService.convertScanModeFromHal(mode);
                        Intent intent3 = new Intent("android.bluetooth.adapter.action.SCAN_MODE_CHANGED");
                        intent3.putExtra("android.bluetooth.adapter.extra.SCAN_MODE", this.mScanMode);
                        intent3.addFlags(VCardConfig.FLAG_APPEND_TYPE_PARAM);
                        this.mService.sendBroadcast(intent3, ProfileService.BLUETOOTH_PERM);
                        debugLog("Scan Mode:" + this.mScanMode);
                        break;
                    case 8:
                        int number = val.length / 6;
                        byte[] addrByte = new byte[6];
                        for (int j = 0; j < number; j++) {
                            System.arraycopy(val, j * 6, addrByte, 0, 6);
                            onBondStateChanged(this.mAdapter.getRemoteDevice(Utils.getAddressStringFromByte(addrByte)), 12);
                        }
                        break;
                    case 9:
                        this.mDiscoverableTimeout = Utils.byteArrayToInt(val, 0);
                        debugLog("Discoverable Timeout:" + this.mDiscoverableTimeout);
                        break;
                    case 13:
                        updateFeatureSupport(val);
                        break;
                    case 14:
                        this.mLocalIOCapability = Utils.byteArrayToInt(val);
                        debugLog("mLocalIOCapability set to " + this.mLocalIOCapability);
                        break;
                    case 15:
                        this.mLocalIOCapabilityBLE = Utils.byteArrayToInt(val);
                        debugLog("mLocalIOCapabilityBLE set to " + this.mLocalIOCapabilityBLE);
                        break;
                }
            }
        }
    }

    private void updateFeatureSupport(byte[] val) {
        this.mVersSupported = ((val[1] & UnsignedBytes.MAX_VALUE) << 8) + (val[0] & UnsignedBytes.MAX_VALUE);
        this.mNumOfAdvertisementInstancesSupported = val[3] & UnsignedBytes.MAX_VALUE;
        this.mRpaOffloadSupported = (val[4] & UnsignedBytes.MAX_VALUE) != 0;
        this.mNumOfOffloadedIrkSupported = val[5] & UnsignedBytes.MAX_VALUE;
        this.mNumOfOffloadedScanFilterSupported = val[6] & UnsignedBytes.MAX_VALUE;
        this.mIsActivityAndEnergyReporting = (val[7] & UnsignedBytes.MAX_VALUE) != 0;
        this.mOffloadedScanResultStorageBytes = ((val[9] & UnsignedBytes.MAX_VALUE) << 8) + (val[8] & UnsignedBytes.MAX_VALUE);
        this.mTotNumOfTrackableAdv = ((val[11] & UnsignedBytes.MAX_VALUE) << 8) + (val[10] & UnsignedBytes.MAX_VALUE);
        this.mIsExtendedScanSupported = (val[12] & UnsignedBytes.MAX_VALUE) != 0;
        this.mIsDebugLogSupported = (val[13] & UnsignedBytes.MAX_VALUE) != 0;
        this.mIsLe2MPhySupported = (val[14] & UnsignedBytes.MAX_VALUE) != 0;
        this.mIsLeCodedPhySupported = (val[15] & UnsignedBytes.MAX_VALUE) != 0;
        this.mIsLeExtendedAdvertisingSupported = (val[16] & UnsignedBytes.MAX_VALUE) != 0;
        this.mIsLePeriodicAdvertisingSupported = (val[17] & UnsignedBytes.MAX_VALUE) != 0;
        this.mLeMaximumAdvertisingDataLength = (val[18] & UnsignedBytes.MAX_VALUE) + ((val[19] & UnsignedBytes.MAX_VALUE) << 8);
        Log.d(TAG, "BT_PROPERTY_LOCAL_LE_FEATURES: update from BT controller mNumOfAdvertisementInstancesSupported = " + this.mNumOfAdvertisementInstancesSupported + " mRpaOffloadSupported = " + this.mRpaOffloadSupported + " mNumOfOffloadedIrkSupported = " + this.mNumOfOffloadedIrkSupported + " mNumOfOffloadedScanFilterSupported = " + this.mNumOfOffloadedScanFilterSupported + " mOffloadedScanResultStorageBytes= " + this.mOffloadedScanResultStorageBytes + " mIsActivityAndEnergyReporting = " + this.mIsActivityAndEnergyReporting + " mVersSupported = " + this.mVersSupported + " mTotNumOfTrackableAdv = " + this.mTotNumOfTrackableAdv + " mIsExtendedScanSupported = " + this.mIsExtendedScanSupported + " mIsDebugLogSupported = " + this.mIsDebugLogSupported + " mIsLe2MPhySupported = " + this.mIsLe2MPhySupported + " mIsLeCodedPhySupported = " + this.mIsLeCodedPhySupported + " mIsLeExtendedAdvertisingSupported = " + this.mIsLeExtendedAdvertisingSupported + " mIsLePeriodicAdvertisingSupported = " + this.mIsLePeriodicAdvertisingSupported + " mLeMaximumAdvertisingDataLength = " + this.mLeMaximumAdvertisingDataLength);
        invalidateIsOffloadedFilteringSupportedCache();
    }

    void onBluetoothReady() {
        debugLog("onBluetoothReady, state=" + BluetoothAdapter.nameForState(getState()) + ", ScanMode=" + this.mScanMode);
        synchronized (this.mObject) {
            setConnectionState(0);
            this.mProfileConnectionState.clear();
            invalidateGetProfileConnectionStateCache();
            this.mProfilesConnected = 0;
            this.mProfilesConnecting = 0;
            this.mProfilesDisconnecting = 0;
            setScanMode(2);
            setDiscoverableTimeout(this.mDiscoverableTimeout);
            getDefaultBluetoothName();
        }
    }

    void getDefaultBluetoothName() {
        String cpu;
        String name = SystemProperties.get(AdapterService.BLUETOOTH_NAME_SET_KEY, INetd.NEXTHOP_NONE);
        if (!name.isEmpty()) {
            return;
        }
        String cpu2 = SystemProperties.get(CPU_KEY, "00000");
        if (cpu2.length() < 5) {
            cpu = "00000";
        } else {
            cpu = cpu2.substring(cpu2.length() - 5);
        }
        String bluetoothName = "BYD-" + cpu;
        debugLog("getDefaultBluetoothName defaultBluetoothName:" + bluetoothName);
        setName(bluetoothName);
        SystemProperties.set(DEFAULT_BLUETOOTH_NAME, bluetoothName);
    }

    void onBleDisable() {
        debugLog("onBleDisable");
        setScanMode(0);
    }

    void discoveryStateChangeCallback(int state) {
        infoLog("Callback:discoveryStateChangeCallback with state:" + state);
        synchronized (this.mObject) {
            try {
                if (state == 0) {
                    this.mDiscovering = false;
                    this.mService.clearDiscoveringPackages();
                    this.mDiscoveryEndMs = System.currentTimeMillis();
                    Intent intent = new Intent("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
                    this.mService.sendBroadcast(intent, ProfileService.BLUETOOTH_PERM);
                } else if (state == 1) {
                    this.mDiscovering = true;
                    this.mDiscoveryEndMs = System.currentTimeMillis() + DEFAULT_DISCOVERY_TIMEOUT_MS;
                    Intent intent2 = new Intent("android.bluetooth.adapter.action.DISCOVERY_STARTED");
                    this.mService.sendBroadcast(intent2, ProfileService.BLUETOOTH_PERM);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    protected void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
        writer.println(TAG);
        writer.println("  Name: " + getName());
        writer.println("  Address: " + Utils.getAddressStringFromByte(this.mAddress));
        writer.println("  BluetoothClass: " + getBluetoothClass());
        writer.println("  ScanMode: " + dumpScanMode(getScanMode()));
        writer.println("  ConnectionState: " + dumpConnectionState(getConnectionState()));
        writer.println("  State: " + BluetoothAdapter.nameForState(getState()));
        writer.println("  MaxConnectedAudioDevices: " + getMaxConnectedAudioDevices());
        writer.println("  A2dpOffloadEnabled: " + this.mA2dpOffloadEnabled);
        writer.println("  Discovering: " + this.mDiscovering);
        writer.println("  DiscoveryEndMs: " + this.mDiscoveryEndMs);
        writer.println("  Bonded devices:");
        for (BluetoothDevice device : this.mBondedDevices) {
            writer.println("    " + device.getAddressOnlyforLog() + " [" + dumpDeviceType(device.getType()) + "] " + device.getName());
        }
    }

    private String dumpDeviceType(int deviceType) {
        if (deviceType == 0) {
            return " ???? ";
        }
        if (deviceType == 1) {
            return "BR/EDR";
        }
        if (deviceType == 2) {
            return "  LE  ";
        }
        if (deviceType == 3) {
            return " DUAL ";
        }
        return "Invalid device type: " + deviceType;
    }

    private String dumpConnectionState(int state) {
        if (state == 0) {
            return "STATE_DISCONNECTED";
        }
        if (state == 1) {
            return "STATE_CONNECTING";
        }
        if (state == 2) {
            return "STATE_CONNECTED";
        }
        if (state == 3) {
            return "STATE_DISCONNECTING";
        }
        return "Unknown Connection State " + state;
    }

    private String dumpScanMode(int scanMode) {
        if (scanMode == 20) {
            return "SCAN_MODE_NONE";
        }
        if (scanMode == 21) {
            return "SCAN_MODE_CONNECTABLE";
        }
        if (scanMode == 23) {
            return "SCAN_MODE_CONNECTABLE_DISCOVERABLE";
        }
        return "Unknown Scan Mode " + scanMode;
    }

    private static void infoLog(String msg) {
    }

    private static void debugLog(String msg) {
        Log.d(TAG, msg);
    }

    private static void errorLog(String msg) {
        Log.e(TAG, msg);
    }
}
