package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothUuid;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.ParcelUuid;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.EventLog;
import android.util.Log;
import com.android.internal.util.ArrayUtils;
import com.android.settingslib.R;
import com.android.settingslib.Utils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class CachedBluetoothDevice implements Comparable<CachedBluetoothDevice> {
    private static final long MAX_HEARING_AIDS_DELAY_FOR_AUTO_CONNECT = 15000;
    private static final long MAX_HOGP_DELAY_FOR_AUTO_CONNECT = 30000;
    private static final long MAX_MEDIA_PROFILE_CONNECT_DELAY = 60000;
    private static final long MAX_UUID_DELAY_FOR_AUTO_CONNECT = 5000;
    private static final String TAG = "CachedBluetoothDevice";
    private long mConnectAttempted;
    private final Context mContext;
    BluetoothDevice mDevice;
    private long mHiSyncId;
    boolean mJustDiscovered;
    private boolean mLocalNapRoleConnected;
    private final LocalBluetoothProfileManager mProfileManager;
    short mRssi;
    private CachedBluetoothDevice mSubDevice;
    private final Object mProfileLock = new Object();
    private final Collection<LocalBluetoothProfile> mProfiles = new CopyOnWriteArrayList();
    private final Collection<LocalBluetoothProfile> mRemovedProfiles = new CopyOnWriteArrayList();
    private final Collection<Callback> mCallbacks = new CopyOnWriteArrayList();
    private boolean mIsActiveDeviceA2dp = false;
    private boolean mIsActiveDeviceHeadset = false;
    private boolean mIsActiveDeviceHearingAid = false;
    private boolean mIsA2dpProfileConnectedFail = false;
    private boolean mIsHeadsetProfileConnectedFail = false;
    private boolean mIsHearingAidProfileConnectedFail = false;
    private final Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.android.settingslib.bluetooth.CachedBluetoothDevice.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            int i = msg.what;
            if (i == 1) {
                CachedBluetoothDevice.this.mIsHeadsetProfileConnectedFail = true;
            } else if (i == 2) {
                CachedBluetoothDevice.this.mIsA2dpProfileConnectedFail = true;
            } else if (i == 21) {
                CachedBluetoothDevice.this.mIsHearingAidProfileConnectedFail = true;
            } else {
                Log.w(CachedBluetoothDevice.TAG, "handleMessage(): unknown message : " + msg.what);
            }
            Log.w(CachedBluetoothDevice.TAG, "Connect to profile : " + msg.what + " timeout, show error message !");
            CachedBluetoothDevice.this.refresh();
        }
    };
    private final BluetoothAdapter mLocalAdapter = BluetoothAdapter.getDefaultAdapter();

    public interface Callback {
        void onDeviceAttributesChanged();
    }

    CachedBluetoothDevice(Context context, LocalBluetoothProfileManager profileManager, BluetoothDevice device) {
        this.mContext = context;
        this.mProfileManager = profileManager;
        this.mDevice = device;
        fillData();
        this.mHiSyncId = 0L;
    }

    private String describe(LocalBluetoothProfile profile) {
        StringBuilder sb = new StringBuilder();
        sb.append("Address:");
        sb.append(this.mDevice);
        if (profile != null) {
            sb.append(" Profile:");
            sb.append(profile);
        }
        return sb.toString();
    }

    void onProfileStateChanged(LocalBluetoothProfile profile, int newProfileState) {
        Log.d(TAG, "onProfileStateChanged: profile " + profile + ", device=" + this.mDevice + ", newProfileState " + newProfileState);
        if (this.mLocalAdapter.getState() == 13) {
            Log.d(TAG, " BT Turninig Off...Profile conn state change ignored...");
            return;
        }
        synchronized (this.mProfileLock) {
            if ((profile instanceof A2dpProfile) || (profile instanceof HeadsetProfile) || (profile instanceof HearingAidProfile)) {
                setProfileConnectedStatus(profile.getProfileId(), false);
                if (newProfileState != 0) {
                    if (newProfileState == 1) {
                        this.mHandler.sendEmptyMessageDelayed(profile.getProfileId(), MAX_MEDIA_PROFILE_CONNECT_DELAY);
                    } else if (newProfileState == 2) {
                        this.mHandler.removeMessages(profile.getProfileId());
                    } else if (newProfileState == 3) {
                        if (this.mHandler.hasMessages(profile.getProfileId())) {
                            this.mHandler.removeMessages(profile.getProfileId());
                        }
                    } else {
                        Log.w(TAG, "onProfileStateChanged(): unknown profile state : " + newProfileState);
                    }
                } else if (this.mHandler.hasMessages(profile.getProfileId())) {
                    this.mHandler.removeMessages(profile.getProfileId());
                    setProfileConnectedStatus(profile.getProfileId(), true);
                }
            }
            if (newProfileState == 2) {
                if (profile instanceof MapProfile) {
                    profile.setEnabled(this.mDevice, true);
                }
                if (!this.mProfiles.contains(profile)) {
                    this.mRemovedProfiles.remove(profile);
                    this.mProfiles.add(profile);
                    if ((profile instanceof PanProfile) && ((PanProfile) profile).isLocalRoleNap(this.mDevice)) {
                        this.mLocalNapRoleConnected = true;
                    }
                }
            } else if ((profile instanceof MapProfile) && newProfileState == 0) {
                profile.setEnabled(this.mDevice, false);
            } else if (this.mLocalNapRoleConnected && (profile instanceof PanProfile) && ((PanProfile) profile).isLocalRoleNap(this.mDevice) && newProfileState == 0) {
                Log.d(TAG, "Removing PanProfile from device after NAP disconnect");
                this.mProfiles.remove(profile);
                this.mRemovedProfiles.add(profile);
                this.mLocalNapRoleConnected = false;
            }
        }
        fetchActiveDevices();
    }

    void setProfileConnectedStatus(int profileId, boolean isFailed) {
        if (profileId == 1) {
            this.mIsHeadsetProfileConnectedFail = isFailed;
            return;
        }
        if (profileId == 2) {
            this.mIsA2dpProfileConnectedFail = isFailed;
            return;
        }
        if (profileId == 21) {
            this.mIsHearingAidProfileConnectedFail = isFailed;
            return;
        }
        Log.w(TAG, "setProfileConnectedStatus(): unknown profile id : " + profileId);
    }

    public void disconnect() {
        synchronized (this.mProfileLock) {
            this.mLocalAdapter.disconnectAllEnabledProfiles(this.mDevice);
        }
        PbapServerProfile PbapProfile = this.mProfileManager.getPbapProfile();
        if (PbapProfile != null && isConnectedProfile(PbapProfile)) {
            PbapProfile.setEnabled(this.mDevice, false);
        }
    }

    public void disconnect(LocalBluetoothProfile profile) {
        if (profile.setEnabled(this.mDevice, false)) {
            Log.d(TAG, "Command sent successfully:DISCONNECT " + describe(profile));
        }
    }

    @Deprecated
    public void connect(boolean connectAllProfiles) {
        connect();
    }

    public void connect() {
        if (!ensurePaired()) {
            return;
        }
        this.mConnectAttempted = SystemClock.elapsedRealtime();
        connectAllEnabledProfiles();
    }

    public long getHiSyncId() {
        return this.mHiSyncId;
    }

    public void setHiSyncId(long id) {
        Log.d(TAG, "setHiSyncId: mDevice " + this.mDevice + ", id " + id);
        this.mHiSyncId = id;
    }

    public boolean isHearingAidDevice() {
        return this.mHiSyncId != 0;
    }

    void onBondingDockConnect() {
        connect();
    }

    private void connectAllEnabledProfiles() {
        synchronized (this.mProfileLock) {
            if (this.mProfiles.isEmpty()) {
                Log.d(TAG, "No profiles. Maybe we will connect later for device " + this.mDevice);
                return;
            }
            this.mLocalAdapter.connectAllEnabledProfiles(this.mDevice);
        }
    }

    public void connectProfile(LocalBluetoothProfile profile) {
        this.mConnectAttempted = SystemClock.elapsedRealtime();
        connectInt(profile);
        refresh();
    }

    synchronized void connectInt(LocalBluetoothProfile profile) {
        if (ensurePaired()) {
            if (profile.setEnabled(this.mDevice, true)) {
                Log.d(TAG, "Command sent successfully:CONNECT " + describe(profile));
                return;
            }
            Log.i(TAG, "Failed to connect " + profile.toString() + " to " + getName());
        }
    }

    private boolean ensurePaired() {
        if (getBondState() == 10) {
            startPairing();
            return false;
        }
        return true;
    }

    public boolean startPairing() {
        if (this.mLocalAdapter.isDiscovering()) {
            this.mLocalAdapter.cancelDiscovery();
        }
        if (!this.mDevice.createBond()) {
            return false;
        }
        return true;
    }

    public void unpair() {
        BluetoothDevice dev;
        int state = getBondState();
        if (state == 11) {
            this.mDevice.cancelBondProcess();
        }
        if (state != 10 && (dev = this.mDevice) != null) {
            boolean successful = dev.removeBond();
            if (successful) {
                Log.d(TAG, "Command sent successfully:REMOVE_BOND " + describe(null));
            }
        }
    }

    public int getProfileConnectionState(LocalBluetoothProfile profile) {
        if (profile != null) {
            return profile.getConnectionStatus(this.mDevice);
        }
        return 0;
    }

    private void fillData() {
        updateProfiles();
        fetchActiveDevices();
        migratePhonebookPermissionChoice();
        migrateMessagePermissionChoice();
        dispatchAttributesChanged();
    }

    public BluetoothDevice getDevice() {
        return this.mDevice;
    }

    public String getAddress() {
        return this.mDevice.getAddress();
    }

    public String getName() {
        String aliasName = this.mDevice.getAlias();
        return TextUtils.isEmpty(aliasName) ? getAddress() : aliasName;
    }

    public void setName(String name) {
        if (name != null && !TextUtils.equals(name, getName())) {
            this.mDevice.setAlias(name);
            dispatchAttributesChanged();
        }
    }

    public boolean setActive() {
        boolean result = false;
        A2dpProfile a2dpProfile = this.mProfileManager.getA2dpProfile();
        if (a2dpProfile != null && isConnectedProfile(a2dpProfile) && a2dpProfile.setActiveDevice(getDevice())) {
            Log.i(TAG, "OnPreferenceClickListener: A2DP active device=" + this);
            result = true;
        }
        HeadsetProfile headsetProfile = this.mProfileManager.getHeadsetProfile();
        if (headsetProfile != null && isConnectedProfile(headsetProfile) && headsetProfile.setActiveDevice(getDevice())) {
            Log.i(TAG, "OnPreferenceClickListener: Headset active device=" + this);
            result = true;
        }
        HearingAidProfile hearingAidProfile = this.mProfileManager.getHearingAidProfile();
        if (hearingAidProfile != null && isConnectedProfile(hearingAidProfile) && hearingAidProfile.setActiveDevice(getDevice())) {
            Log.i(TAG, "OnPreferenceClickListener: Hearing Aid active device=" + this);
            return true;
        }
        return result;
    }

    void refreshName() {
        Log.d(TAG, "Device name: " + getName());
        dispatchAttributesChanged();
    }

    public boolean hasHumanReadableName() {
        return !TextUtils.isEmpty(this.mDevice.getAlias());
    }

    public int getBatteryLevel() {
        return this.mDevice.getBatteryLevel();
    }

    void refresh() {
        dispatchAttributesChanged();
    }

    public void setJustDiscovered(boolean justDiscovered) {
        if (this.mJustDiscovered != justDiscovered) {
            this.mJustDiscovered = justDiscovered;
            dispatchAttributesChanged();
        }
    }

    public int getBondState() {
        return this.mDevice.getBondState();
    }

    public void onActiveDeviceChanged(boolean isActive, int bluetoothProfile) {
        boolean changed = false;
        if (bluetoothProfile == 1) {
            changed = this.mIsActiveDeviceHeadset != isActive;
            this.mIsActiveDeviceHeadset = isActive;
        } else if (bluetoothProfile == 2) {
            changed = this.mIsActiveDeviceA2dp != isActive;
            this.mIsActiveDeviceA2dp = isActive;
        } else if (bluetoothProfile == 21) {
            changed = this.mIsActiveDeviceHearingAid != isActive;
            this.mIsActiveDeviceHearingAid = isActive;
        } else {
            Log.w(TAG, "onActiveDeviceChanged: unknown profile " + bluetoothProfile + " isActive " + isActive);
        }
        if (changed) {
            dispatchAttributesChanged();
        }
    }

    void onAudioModeChanged() {
        dispatchAttributesChanged();
    }

    public boolean isActiveDevice(int bluetoothProfile) {
        if (bluetoothProfile == 1) {
            return this.mIsActiveDeviceHeadset;
        }
        if (bluetoothProfile == 2) {
            return this.mIsActiveDeviceA2dp;
        }
        if (bluetoothProfile == 21) {
            return this.mIsActiveDeviceHearingAid;
        }
        Log.w(TAG, "getActiveDevice: unknown profile " + bluetoothProfile);
        return false;
    }

    void setRssi(short rssi) {
        if (this.mRssi != rssi) {
            this.mRssi = rssi;
            dispatchAttributesChanged();
        }
    }

    public boolean isConnected() {
        synchronized (this.mProfileLock) {
            for (LocalBluetoothProfile profile : this.mProfiles) {
                int status = getProfileConnectionState(profile);
                if (status == 2) {
                    return true;
                }
            }
            return false;
        }
    }

    public boolean isConnectedProfile(LocalBluetoothProfile profile) {
        int status = getProfileConnectionState(profile);
        return status == 2;
    }

    public boolean isBusy() {
        int status;
        synchronized (this.mProfileLock) {
            Iterator<LocalBluetoothProfile> it = this.mProfiles.iterator();
            do {
                boolean z = true;
                if (it.hasNext()) {
                    LocalBluetoothProfile profile = it.next();
                    status = getProfileConnectionState(profile);
                    if (status == 1) {
                        break;
                    }
                } else {
                    if (getBondState() != 11) {
                        z = false;
                    }
                    return z;
                }
            } while (status != 3);
            return true;
        }
    }

    private boolean updateProfiles() {
        ParcelUuid[] localUuids;
        ParcelUuid[] uuids = this.mDevice.getUuids();
        if (uuids == null || (localUuids = this.mLocalAdapter.getUuids()) == null) {
            return false;
        }
        processPhonebookAccess();
        synchronized (this.mProfileLock) {
            this.mProfileManager.updateProfiles(uuids, localUuids, this.mProfiles, this.mRemovedProfiles, this.mLocalNapRoleConnected, this.mDevice);
        }
        Log.e(TAG, "updating profiles for " + this.mDevice.getAlias() + ", " + this.mDevice);
        BluetoothClass bluetoothClass = this.mDevice.getBluetoothClass();
        if (bluetoothClass != null) {
            Log.v(TAG, "Class: " + bluetoothClass.toString());
        }
        Log.v(TAG, "UUID:");
        for (ParcelUuid uuid : uuids) {
            Log.v(TAG, "  " + uuid);
        }
        return true;
    }

    private void fetchActiveDevices() {
        A2dpProfile a2dpProfile = this.mProfileManager.getA2dpProfile();
        if (a2dpProfile != null) {
            this.mIsActiveDeviceA2dp = this.mDevice.equals(a2dpProfile.getActiveDevice());
        }
        HeadsetProfile headsetProfile = this.mProfileManager.getHeadsetProfile();
        if (headsetProfile != null) {
            this.mIsActiveDeviceHeadset = this.mDevice.equals(headsetProfile.getActiveDevice());
        }
        HearingAidProfile hearingAidProfile = this.mProfileManager.getHearingAidProfile();
        if (hearingAidProfile != null) {
            this.mIsActiveDeviceHearingAid = hearingAidProfile.getActiveDevices().contains(this.mDevice);
        }
    }

    void onUuidChanged() {
        updateProfiles();
        ParcelUuid[] uuids = this.mDevice.getUuids();
        long timeout = MAX_UUID_DELAY_FOR_AUTO_CONNECT;
        if (ArrayUtils.contains(uuids, BluetoothUuid.HOGP)) {
            timeout = MAX_HOGP_DELAY_FOR_AUTO_CONNECT;
        } else if (ArrayUtils.contains(uuids, BluetoothUuid.HEARING_AID)) {
            timeout = MAX_HEARING_AIDS_DELAY_FOR_AUTO_CONNECT;
        }
        Log.d(TAG, "onUuidChanged: Time since last connect=" + (SystemClock.elapsedRealtime() - this.mConnectAttempted));
        if (!this.mProfiles.isEmpty() && this.mConnectAttempted + timeout > SystemClock.elapsedRealtime()) {
            connectAllEnabledProfiles();
        }
        dispatchAttributesChanged();
    }

    void onBondingStateChanged(int bondState) {
        if (bondState == 10) {
            synchronized (this.mProfileLock) {
                this.mProfiles.clear();
            }
            this.mDevice.setPhonebookAccessPermission(0);
            this.mDevice.setMessageAccessPermission(0);
            this.mDevice.setSimAccessPermission(0);
        }
        refresh();
        if (bondState == 12 && this.mDevice.isBondingInitiatedLocally()) {
            connect();
        }
    }

    public BluetoothClass getBtClass() {
        return this.mDevice.getBluetoothClass();
    }

    public List<LocalBluetoothProfile> getProfiles() {
        return new ArrayList(this.mProfiles);
    }

    public List<LocalBluetoothProfile> getConnectableProfiles() {
        List<LocalBluetoothProfile> connectableProfiles = new ArrayList<>();
        synchronized (this.mProfileLock) {
            for (LocalBluetoothProfile profile : this.mProfiles) {
                if (profile.accessProfileEnabled()) {
                    connectableProfiles.add(profile);
                }
            }
        }
        return connectableProfiles;
    }

    public List<LocalBluetoothProfile> getRemovedProfiles() {
        return new ArrayList(this.mRemovedProfiles);
    }

    public void registerCallback(Callback callback) {
        this.mCallbacks.add(callback);
    }

    public void unregisterCallback(Callback callback) {
        this.mCallbacks.remove(callback);
    }

    void dispatchAttributesChanged() {
        for (Callback callback : this.mCallbacks) {
            callback.onDeviceAttributesChanged();
        }
    }

    public String toString() {
        return this.mDevice.toString();
    }

    public boolean equals(Object o) {
        if (o == null || !(o instanceof CachedBluetoothDevice)) {
            return false;
        }
        return this.mDevice.equals(((CachedBluetoothDevice) o).mDevice);
    }

    public int hashCode() {
        return this.mDevice.getAddress().hashCode();
    }

    @Override // java.lang.Comparable
    public int compareTo(CachedBluetoothDevice cachedBluetoothDevice) {
        int i = (cachedBluetoothDevice.isConnected() ? 1 : 0) - (isConnected() ? 1 : 0);
        if (i != 0) {
            return i;
        }
        int i2 = (cachedBluetoothDevice.getBondState() == 12 ? 1 : 0) - (getBondState() != 12 ? 0 : 1);
        if (i2 != 0) {
            return i2;
        }
        int i3 = (cachedBluetoothDevice.mJustDiscovered ? 1 : 0) - (this.mJustDiscovered ? 1 : 0);
        if (i3 != 0) {
            return i3;
        }
        int i4 = cachedBluetoothDevice.mRssi - this.mRssi;
        return i4 != 0 ? i4 : getName().compareTo(cachedBluetoothDevice.getName());
    }

    private void migratePhonebookPermissionChoice() {
        SharedPreferences preferences = this.mContext.getSharedPreferences("bluetooth_phonebook_permission", 0);
        if (!preferences.contains(this.mDevice.getAddress())) {
            return;
        }
        if (this.mDevice.getPhonebookAccessPermission() == 0) {
            int oldPermission = preferences.getInt(this.mDevice.getAddress(), 0);
            if (oldPermission == 1) {
                this.mDevice.setPhonebookAccessPermission(1);
            } else if (oldPermission == 2) {
                this.mDevice.setPhonebookAccessPermission(2);
            }
        }
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(this.mDevice.getAddress());
        editor.commit();
    }

    private void migrateMessagePermissionChoice() {
        SharedPreferences preferences = this.mContext.getSharedPreferences("bluetooth_message_permission", 0);
        if (!preferences.contains(this.mDevice.getAddress())) {
            return;
        }
        if (this.mDevice.getMessageAccessPermission() == 0) {
            int oldPermission = preferences.getInt(this.mDevice.getAddress(), 0);
            if (oldPermission == 1) {
                this.mDevice.setMessageAccessPermission(1);
            } else if (oldPermission == 2) {
                this.mDevice.setMessageAccessPermission(2);
            }
        }
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(this.mDevice.getAddress());
        editor.commit();
    }

    private void processPhonebookAccess() {
        if (this.mDevice.getBondState() != 12) {
            return;
        }
        ParcelUuid[] uuids = this.mDevice.getUuids();
        if (BluetoothUuid.containsAnyUuid(uuids, PbapServerProfile.PBAB_CLIENT_UUIDS) && this.mDevice.getPhonebookAccessPermission() == 0) {
            if (this.mDevice.getBluetoothClass().getDeviceClass() == 1032 || this.mDevice.getBluetoothClass().getDeviceClass() == 1028) {
                EventLog.writeEvent(1397638484, "138529441", -1, "");
            }
            this.mDevice.setPhonebookAccessPermission(2);
        }
    }

    public int getMaxConnectionState() {
        int maxState = 0;
        synchronized (this.mProfileLock) {
            for (LocalBluetoothProfile profile : getProfiles()) {
                int connectionStatus = getProfileConnectionState(profile);
                if (connectionStatus > maxState) {
                    maxState = connectionStatus;
                }
            }
        }
        return maxState;
    }

    public String getConnectionSummary() {
        return getConnectionSummary(false);
    }

    public String getConnectionSummary(boolean shortSummary) {
        boolean profileConnected = false;
        boolean a2dpConnected = true;
        boolean hfpConnected = true;
        boolean hearingAidConnected = true;
        int leftBattery = -1;
        int rightBattery = -1;
        if (isProfileConnectedFail() && isConnected()) {
            return this.mContext.getString(R.string.profile_connect_timeout_subtext);
        }
        synchronized (this.mProfileLock) {
            for (LocalBluetoothProfile profile : getProfiles()) {
                int connectionStatus = getProfileConnectionState(profile);
                if (connectionStatus != 0) {
                    if (connectionStatus != 1) {
                        if (connectionStatus == 2) {
                            profileConnected = true;
                        } else if (connectionStatus != 3) {
                        }
                    }
                    return this.mContext.getString(BluetoothUtils.getConnectionStateSummary(connectionStatus));
                }
                if (profile.isProfileReady()) {
                    if ((profile instanceof A2dpProfile) || (profile instanceof A2dpSinkProfile)) {
                        a2dpConnected = false;
                    } else if ((profile instanceof HeadsetProfile) || (profile instanceof HfpClientProfile)) {
                        hfpConnected = false;
                    } else if (profile instanceof HearingAidProfile) {
                        hearingAidConnected = false;
                    }
                }
            }
            String batteryLevelPercentageString = null;
            int batteryLevel = getBatteryLevel();
            if (batteryLevel > -1) {
                batteryLevelPercentageString = Utils.formatPercentage(batteryLevel);
            }
            int stringRes = R.string.bluetooth_pairing;
            if (profileConnected) {
                if (BluetoothUtils.getBooleanMetaData(this.mDevice, 6)) {
                    leftBattery = BluetoothUtils.getIntMetaData(this.mDevice, 10);
                    rightBattery = BluetoothUtils.getIntMetaData(this.mDevice, 11);
                }
                if (isTwsBatteryAvailable(leftBattery, rightBattery)) {
                    stringRes = R.string.bluetooth_battery_level_untethered;
                } else if (batteryLevelPercentageString != null) {
                    stringRes = R.string.bluetooth_battery_level;
                }
                if (a2dpConnected || hfpConnected || hearingAidConnected) {
                    boolean isOnCall = Utils.isAudioModeOngoingCall(this.mContext);
                    if (this.mIsActiveDeviceHearingAid || ((this.mIsActiveDeviceHeadset && isOnCall) || (this.mIsActiveDeviceA2dp && !isOnCall))) {
                        if (isTwsBatteryAvailable(leftBattery, rightBattery) && !shortSummary) {
                            stringRes = R.string.bluetooth_active_battery_level_untethered;
                        } else if (batteryLevelPercentageString != null && !shortSummary) {
                            stringRes = R.string.bluetooth_active_battery_level;
                        } else {
                            stringRes = R.string.bluetooth_active_no_battery_level;
                        }
                    }
                }
            }
            if (stringRes != R.string.bluetooth_pairing || getBondState() == 11) {
                return isTwsBatteryAvailable(leftBattery, rightBattery) ? this.mContext.getString(stringRes, Utils.formatPercentage(leftBattery), Utils.formatPercentage(rightBattery)) : this.mContext.getString(stringRes, batteryLevelPercentageString);
            }
            return null;
        }
    }

    private boolean isTwsBatteryAvailable(int leftBattery, int rightBattery) {
        return leftBattery >= 0 && rightBattery >= 0;
    }

    private boolean isProfileConnectedFail() {
        return this.mIsA2dpProfileConnectedFail || this.mIsHearingAidProfileConnectedFail || this.mIsHeadsetProfileConnectedFail;
    }

    public String getCarConnectionSummary() {
        String activeDeviceString;
        boolean profileConnected = false;
        boolean a2dpNotConnected = false;
        boolean hfpNotConnected = false;
        boolean hearingAidNotConnected = false;
        synchronized (this.mProfileLock) {
            for (LocalBluetoothProfile profile : getProfiles()) {
                int connectionStatus = getProfileConnectionState(profile);
                if (connectionStatus != 0) {
                    if (connectionStatus != 1) {
                        if (connectionStatus == 2) {
                            profileConnected = true;
                        } else if (connectionStatus != 3) {
                        }
                    }
                    return this.mContext.getString(BluetoothUtils.getConnectionStateSummary(connectionStatus));
                }
                if (profile.isProfileReady()) {
                    if ((profile instanceof A2dpProfile) || (profile instanceof A2dpSinkProfile)) {
                        a2dpNotConnected = true;
                    } else if ((profile instanceof HeadsetProfile) || (profile instanceof HfpClientProfile)) {
                        hfpNotConnected = true;
                    } else if (profile instanceof HearingAidProfile) {
                        hearingAidNotConnected = true;
                    }
                }
            }
            String batteryLevelPercentageString = null;
            int batteryLevel = getBatteryLevel();
            if (batteryLevel > -1) {
                batteryLevelPercentageString = Utils.formatPercentage(batteryLevel);
            }
            String[] activeDeviceStringsArray = this.mContext.getResources().getStringArray(R.array.bluetooth_audio_active_device_summaries);
            String activeDeviceString2 = activeDeviceStringsArray[0];
            if (this.mIsActiveDeviceA2dp && this.mIsActiveDeviceHeadset) {
                activeDeviceString = activeDeviceStringsArray[1];
            } else {
                if (this.mIsActiveDeviceA2dp) {
                    activeDeviceString2 = activeDeviceStringsArray[2];
                }
                if (!this.mIsActiveDeviceHeadset) {
                    activeDeviceString = activeDeviceString2;
                } else {
                    activeDeviceString = activeDeviceStringsArray[3];
                }
            }
            if (!hearingAidNotConnected && this.mIsActiveDeviceHearingAid) {
                String activeDeviceString3 = activeDeviceStringsArray[1];
                return this.mContext.getString(R.string.bluetooth_connected, activeDeviceString3);
            }
            if (profileConnected) {
                return (a2dpNotConnected && hfpNotConnected) ? batteryLevelPercentageString != null ? this.mContext.getString(R.string.bluetooth_connected_no_headset_no_a2dp_battery_level, batteryLevelPercentageString, activeDeviceString) : this.mContext.getString(R.string.bluetooth_connected_no_headset_no_a2dp, activeDeviceString) : a2dpNotConnected ? batteryLevelPercentageString != null ? this.mContext.getString(R.string.bluetooth_connected_no_a2dp_battery_level, batteryLevelPercentageString, activeDeviceString) : this.mContext.getString(R.string.bluetooth_connected_no_a2dp, activeDeviceString) : hfpNotConnected ? batteryLevelPercentageString != null ? this.mContext.getString(R.string.bluetooth_connected_no_headset_battery_level, batteryLevelPercentageString, activeDeviceString) : this.mContext.getString(R.string.bluetooth_connected_no_headset, activeDeviceString) : batteryLevelPercentageString != null ? this.mContext.getString(R.string.bluetooth_connected_battery_level, batteryLevelPercentageString, activeDeviceString) : this.mContext.getString(R.string.bluetooth_connected, activeDeviceString);
            }
            if (getBondState() == 11) {
                return this.mContext.getString(R.string.bluetooth_pairing);
            }
            return null;
        }
    }

    public boolean isConnectedA2dpDevice() {
        A2dpProfile a2dpProfile = this.mProfileManager.getA2dpProfile();
        return a2dpProfile != null && a2dpProfile.getConnectionStatus(this.mDevice) == 2;
    }

    public boolean isConnectedHfpDevice() {
        HeadsetProfile headsetProfile = this.mProfileManager.getHeadsetProfile();
        return headsetProfile != null && headsetProfile.getConnectionStatus(this.mDevice) == 2;
    }

    public boolean isConnectedHearingAidDevice() {
        HearingAidProfile hearingAidProfile = this.mProfileManager.getHearingAidProfile();
        return hearingAidProfile != null && hearingAidProfile.getConnectionStatus(this.mDevice) == 2;
    }

    public CachedBluetoothDevice getSubDevice() {
        return this.mSubDevice;
    }

    public void setSubDevice(CachedBluetoothDevice subDevice) {
        this.mSubDevice = subDevice;
    }

    public void switchSubDeviceContent() {
        BluetoothDevice tmpDevice = this.mDevice;
        short tmpRssi = this.mRssi;
        boolean tmpJustDiscovered = this.mJustDiscovered;
        CachedBluetoothDevice cachedBluetoothDevice = this.mSubDevice;
        this.mDevice = cachedBluetoothDevice.mDevice;
        this.mRssi = cachedBluetoothDevice.mRssi;
        this.mJustDiscovered = cachedBluetoothDevice.mJustDiscovered;
        cachedBluetoothDevice.mDevice = tmpDevice;
        cachedBluetoothDevice.mRssi = tmpRssi;
        cachedBluetoothDevice.mJustDiscovered = tmpJustDiscovered;
        fetchActiveDevices();
    }
}
