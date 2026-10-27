package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothUuid;
import android.content.Context;
import android.content.Intent;
import android.os.ParcelUuid;
import android.util.Log;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.CollectionUtils;
import com.android.settingslib.bluetooth.BluetoothEventManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class LocalBluetoothProfileManager {
    private static final boolean DEBUG = true;
    private static final String TAG = "LocalBluetoothProfileManager";
    private A2dpProfile mA2dpProfile;
    private A2dpSinkProfile mA2dpSinkProfile;
    private final Context mContext;
    private final CachedBluetoothDeviceManager mDeviceManager;
    private final BluetoothEventManager mEventManager;
    private HeadsetProfile mHeadsetProfile;
    private HearingAidProfile mHearingAidProfile;
    private HfpClientProfile mHfpClientProfile;
    private HidDeviceProfile mHidDeviceProfile;
    private HidProfile mHidProfile;
    private MapClientProfile mMapClientProfile;
    private MapProfile mMapProfile;
    private OppProfile mOppProfile;
    private PanProfile mPanProfile;
    private PbapClientProfile mPbapClientProfile;
    private PbapServerProfile mPbapProfile;
    private SapProfile mSapProfile;
    private final Map<String, LocalBluetoothProfile> mProfileNameMap = new HashMap();
    private final Collection<ServiceListener> mServiceListeners = new CopyOnWriteArrayList();

    public interface ServiceListener {
        void onServiceConnected();

        void onServiceDisconnected();
    }

    LocalBluetoothProfileManager(Context context, LocalBluetoothAdapter adapter, CachedBluetoothDeviceManager deviceManager, BluetoothEventManager eventManager) {
        this.mContext = context;
        this.mDeviceManager = deviceManager;
        this.mEventManager = eventManager;
        adapter.setProfileManager(this);
        Log.d(TAG, "LocalBluetoothProfileManager construction complete");
    }

    void updateLocalProfiles() {
        List<Integer> supportedList = BluetoothAdapter.getDefaultAdapter().getSupportedProfiles();
        if (CollectionUtils.isEmpty(supportedList)) {
            Log.d(TAG, "supportedList is null");
            return;
        }
        if (this.mA2dpProfile == null && supportedList.contains(2)) {
            Log.d(TAG, "Adding local A2DP profile");
            A2dpProfile a2dpProfile = new A2dpProfile(this.mContext, this.mDeviceManager, this);
            this.mA2dpProfile = a2dpProfile;
            addProfile(a2dpProfile, "A2DP", "android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
        }
        if (this.mA2dpSinkProfile == null && supportedList.contains(11)) {
            Log.d(TAG, "Adding local A2DP SINK profile");
            A2dpSinkProfile a2dpSinkProfile = new A2dpSinkProfile(this.mContext, this.mDeviceManager, this);
            this.mA2dpSinkProfile = a2dpSinkProfile;
            addProfile(a2dpSinkProfile, "A2DPSink", "android.bluetooth.a2dp-sink.profile.action.CONNECTION_STATE_CHANGED");
        }
        if (this.mHeadsetProfile == null && supportedList.contains(1)) {
            Log.d(TAG, "Adding local HEADSET profile");
            HeadsetProfile headsetProfile = new HeadsetProfile(this.mContext, this.mDeviceManager, this);
            this.mHeadsetProfile = headsetProfile;
            addHeadsetProfile(headsetProfile, "HEADSET", "android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED", "android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED", 10);
        }
        if (this.mHfpClientProfile == null && supportedList.contains(16)) {
            Log.d(TAG, "Adding local HfpClient profile");
            HfpClientProfile hfpClientProfile = new HfpClientProfile(this.mContext, this.mDeviceManager, this);
            this.mHfpClientProfile = hfpClientProfile;
            addHeadsetProfile(hfpClientProfile, "HEADSET_CLIENT", "android.bluetooth.headsetclient.profile.action.CONNECTION_STATE_CHANGED", "android.bluetooth.headsetclient.profile.action.AUDIO_STATE_CHANGED", 0);
        }
        if (this.mMapClientProfile == null && supportedList.contains(18)) {
            Log.d(TAG, "Adding local MAP CLIENT profile");
            MapClientProfile mapClientProfile = new MapClientProfile(this.mContext, this.mDeviceManager, this);
            this.mMapClientProfile = mapClientProfile;
            addProfile(mapClientProfile, "MAP Client", "android.bluetooth.mapmce.profile.action.CONNECTION_STATE_CHANGED");
        }
        if (this.mMapProfile == null && supportedList.contains(9)) {
            Log.d(TAG, "Adding local MAP profile");
            MapProfile mapProfile = new MapProfile(this.mContext, this.mDeviceManager, this);
            this.mMapProfile = mapProfile;
            addProfile(mapProfile, "MAP", "android.bluetooth.map.profile.action.CONNECTION_STATE_CHANGED");
        }
        if (this.mOppProfile == null && supportedList.contains(20)) {
            Log.d(TAG, "Adding local OPP profile");
            OppProfile oppProfile = new OppProfile();
            this.mOppProfile = oppProfile;
            this.mProfileNameMap.put("OPP", oppProfile);
        }
        if (this.mHearingAidProfile == null && supportedList.contains(21)) {
            Log.d(TAG, "Adding local Hearing Aid profile");
            HearingAidProfile hearingAidProfile = new HearingAidProfile(this.mContext, this.mDeviceManager, this);
            this.mHearingAidProfile = hearingAidProfile;
            addProfile(hearingAidProfile, "HearingAid", "android.bluetooth.hearingaid.profile.action.CONNECTION_STATE_CHANGED");
        }
        if (this.mHidProfile == null && supportedList.contains(4)) {
            Log.d(TAG, "Adding local HID_HOST profile");
            HidProfile hidProfile = new HidProfile(this.mContext, this.mDeviceManager, this);
            this.mHidProfile = hidProfile;
            addProfile(hidProfile, "HID", "android.bluetooth.input.profile.action.CONNECTION_STATE_CHANGED");
        }
        if (this.mHidDeviceProfile == null && supportedList.contains(19)) {
            Log.d(TAG, "Adding local HID_DEVICE profile");
            HidDeviceProfile hidDeviceProfile = new HidDeviceProfile(this.mContext, this.mDeviceManager, this);
            this.mHidDeviceProfile = hidDeviceProfile;
            addProfile(hidDeviceProfile, "HID DEVICE", "android.bluetooth.hiddevice.profile.action.CONNECTION_STATE_CHANGED");
        }
        if (this.mPanProfile == null && supportedList.contains(5)) {
            Log.d(TAG, "Adding local PAN profile");
            PanProfile panProfile = new PanProfile(this.mContext);
            this.mPanProfile = panProfile;
            addPanProfile(panProfile, "PAN", "android.bluetooth.pan.profile.action.CONNECTION_STATE_CHANGED");
        }
        if (this.mPbapProfile == null && supportedList.contains(6)) {
            Log.d(TAG, "Adding local PBAP profile");
            PbapServerProfile pbapServerProfile = new PbapServerProfile(this.mContext);
            this.mPbapProfile = pbapServerProfile;
            addProfile(pbapServerProfile, PbapServerProfile.NAME, "android.bluetooth.pbap.profile.action.CONNECTION_STATE_CHANGED");
        }
        if (this.mPbapClientProfile == null && supportedList.contains(17)) {
            Log.d(TAG, "Adding local PBAP Client profile");
            PbapClientProfile pbapClientProfile = new PbapClientProfile(this.mContext, this.mDeviceManager, this);
            this.mPbapClientProfile = pbapClientProfile;
            addProfile(pbapClientProfile, "PbapClient", "android.bluetooth.pbapclient.profile.action.CONNECTION_STATE_CHANGED");
        }
        if (this.mSapProfile == null && supportedList.contains(10)) {
            Log.d(TAG, "Adding local SAP profile");
            SapProfile sapProfile = new SapProfile(this.mContext, this.mDeviceManager, this);
            this.mSapProfile = sapProfile;
            addProfile(sapProfile, "SAP", "android.bluetooth.sap.profile.action.CONNECTION_STATE_CHANGED");
        }
        this.mEventManager.registerProfileIntentReceiver();
    }

    private void addHeadsetProfile(LocalBluetoothProfile profile, String profileName, String stateChangedAction, String audioStateChangedAction, int audioDisconnectedState) {
        BluetoothEventManager.Handler handler = new HeadsetStateChangeHandler(profile, audioStateChangedAction, audioDisconnectedState);
        this.mEventManager.addProfileHandler(stateChangedAction, handler);
        this.mEventManager.addProfileHandler(audioStateChangedAction, handler);
        this.mProfileNameMap.put(profileName, profile);
    }

    private void addProfile(LocalBluetoothProfile profile, String profileName, String stateChangedAction) {
        this.mEventManager.addProfileHandler(stateChangedAction, new StateChangedHandler(profile));
        this.mProfileNameMap.put(profileName, profile);
    }

    private void addPanProfile(LocalBluetoothProfile profile, String profileName, String stateChangedAction) {
        this.mEventManager.addProfileHandler(stateChangedAction, new PanStateChangedHandler(profile));
        this.mProfileNameMap.put(profileName, profile);
    }

    public LocalBluetoothProfile getProfileByName(String name) {
        return this.mProfileNameMap.get(name);
    }

    void setBluetoothStateOn() {
        updateLocalProfiles();
        this.mEventManager.readPairedDevices();
    }

    private class StateChangedHandler implements BluetoothEventManager.Handler {
        final LocalBluetoothProfile mProfile;

        StateChangedHandler(LocalBluetoothProfile profile) {
            this.mProfile = profile;
        }

        @Override // com.android.settingslib.bluetooth.BluetoothEventManager.Handler
        public void onReceive(Context context, Intent intent, BluetoothDevice device) {
            CachedBluetoothDevice cachedDevice = LocalBluetoothProfileManager.this.mDeviceManager.findDevice(device);
            if (cachedDevice == null) {
                Log.w(LocalBluetoothProfileManager.TAG, "StateChangedHandler found new device: " + device);
                cachedDevice = LocalBluetoothProfileManager.this.mDeviceManager.addDevice(device);
            }
            onReceiveInternal(intent, cachedDevice);
        }

        protected void onReceiveInternal(Intent intent, CachedBluetoothDevice cachedDevice) {
            int newState = intent.getIntExtra("android.bluetooth.profile.extra.STATE", 0);
            int oldState = intent.getIntExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", 0);
            if (newState == 0 && oldState == 1) {
                Log.i(LocalBluetoothProfileManager.TAG, "Failed to connect " + this.mProfile + " device");
            }
            if (LocalBluetoothProfileManager.this.getHearingAidProfile() != null && (this.mProfile instanceof HearingAidProfile) && newState == 2 && cachedDevice.getHiSyncId() == 0) {
                long newHiSyncId = LocalBluetoothProfileManager.this.getHearingAidProfile().getHiSyncId(cachedDevice.getDevice());
                if (newHiSyncId != 0) {
                    cachedDevice.setHiSyncId(newHiSyncId);
                }
            }
            cachedDevice.onProfileStateChanged(this.mProfile, newState);
            if (cachedDevice.getHiSyncId() == 0 || !LocalBluetoothProfileManager.this.mDeviceManager.onProfileConnectionStateChangedIfProcessed(cachedDevice, newState)) {
                cachedDevice.refresh();
                LocalBluetoothProfileManager.this.mEventManager.dispatchProfileConnectionStateChanged(cachedDevice, newState, this.mProfile.getProfileId());
            }
        }
    }

    private class HeadsetStateChangeHandler extends StateChangedHandler {
        private final String mAudioChangeAction;
        private final int mAudioDisconnectedState;

        HeadsetStateChangeHandler(LocalBluetoothProfile profile, String audioChangeAction, int audioDisconnectedState) {
            super(profile);
            this.mAudioChangeAction = audioChangeAction;
            this.mAudioDisconnectedState = audioDisconnectedState;
        }

        @Override // com.android.settingslib.bluetooth.LocalBluetoothProfileManager.StateChangedHandler
        public void onReceiveInternal(Intent intent, CachedBluetoothDevice cachedDevice) {
            if (this.mAudioChangeAction.equals(intent.getAction())) {
                int newState = intent.getIntExtra("android.bluetooth.profile.extra.STATE", 0);
                if (newState != this.mAudioDisconnectedState) {
                    cachedDevice.onProfileStateChanged(this.mProfile, 2);
                }
                cachedDevice.refresh();
                return;
            }
            super.onReceiveInternal(intent, cachedDevice);
        }
    }

    private class PanStateChangedHandler extends StateChangedHandler {
        PanStateChangedHandler(LocalBluetoothProfile profile) {
            super(profile);
        }

        @Override // com.android.settingslib.bluetooth.LocalBluetoothProfileManager.StateChangedHandler, com.android.settingslib.bluetooth.BluetoothEventManager.Handler
        public void onReceive(Context context, Intent intent, BluetoothDevice device) {
            PanProfile panProfile = (PanProfile) this.mProfile;
            int role = intent.getIntExtra("android.bluetooth.pan.extra.LOCAL_ROLE", 0);
            panProfile.setLocalRole(device, role);
            super.onReceive(context, intent, device);
        }
    }

    public void addServiceListener(ServiceListener l) {
        this.mServiceListeners.add(l);
    }

    public void removeServiceListener(ServiceListener l) {
        this.mServiceListeners.remove(l);
    }

    void callServiceConnectedListeners() {
        Collection<ServiceListener> listeners = new ArrayList<>(this.mServiceListeners);
        for (ServiceListener l : listeners) {
            l.onServiceConnected();
        }
    }

    void callServiceDisconnectedListeners() {
        Collection<ServiceListener> listeners = new ArrayList<>(this.mServiceListeners);
        for (ServiceListener listener : listeners) {
            listener.onServiceDisconnected();
        }
    }

    public synchronized boolean isManagerReady() {
        LocalBluetoothProfile profile = this.mHeadsetProfile;
        if (profile != null) {
            return profile.isProfileReady();
        }
        LocalBluetoothProfile profile2 = this.mA2dpProfile;
        if (profile2 != null) {
            return profile2.isProfileReady();
        }
        LocalBluetoothProfile profile3 = this.mA2dpSinkProfile;
        if (profile3 != null) {
            return profile3.isProfileReady();
        }
        return false;
    }

    public A2dpProfile getA2dpProfile() {
        return this.mA2dpProfile;
    }

    public A2dpSinkProfile getA2dpSinkProfile() {
        A2dpSinkProfile a2dpSinkProfile = this.mA2dpSinkProfile;
        if (a2dpSinkProfile != null && a2dpSinkProfile.isProfileReady()) {
            return this.mA2dpSinkProfile;
        }
        return null;
    }

    public HeadsetProfile getHeadsetProfile() {
        return this.mHeadsetProfile;
    }

    public HfpClientProfile getHfpClientProfile() {
        HfpClientProfile hfpClientProfile = this.mHfpClientProfile;
        if (hfpClientProfile != null && hfpClientProfile.isProfileReady()) {
            return this.mHfpClientProfile;
        }
        return null;
    }

    public PbapClientProfile getPbapClientProfile() {
        return this.mPbapClientProfile;
    }

    public PbapServerProfile getPbapProfile() {
        return this.mPbapProfile;
    }

    public MapProfile getMapProfile() {
        return this.mMapProfile;
    }

    public MapClientProfile getMapClientProfile() {
        return this.mMapClientProfile;
    }

    public HearingAidProfile getHearingAidProfile() {
        return this.mHearingAidProfile;
    }

    HidProfile getHidProfile() {
        return this.mHidProfile;
    }

    HidDeviceProfile getHidDeviceProfile() {
        return this.mHidDeviceProfile;
    }

    synchronized void updateProfiles(ParcelUuid[] uuids, ParcelUuid[] localUuids, Collection<LocalBluetoothProfile> profiles, Collection<LocalBluetoothProfile> removedProfiles, boolean isPanNapConnected, BluetoothDevice device) {
        removedProfiles.clear();
        removedProfiles.addAll(profiles);
        Log.d(TAG, "Current Profiles" + profiles.toString());
        profiles.clear();
        if (uuids == null) {
            return;
        }
        if (this.mHeadsetProfile != null && ((ArrayUtils.contains(localUuids, BluetoothUuid.HSP_AG) && ArrayUtils.contains(uuids, BluetoothUuid.HSP)) || (ArrayUtils.contains(localUuids, BluetoothUuid.HFP_AG) && ArrayUtils.contains(uuids, BluetoothUuid.HFP)))) {
            profiles.add(this.mHeadsetProfile);
            removedProfiles.remove(this.mHeadsetProfile);
        }
        if (this.mHfpClientProfile != null && ArrayUtils.contains(uuids, BluetoothUuid.HFP_AG) && ArrayUtils.contains(localUuids, BluetoothUuid.HFP)) {
            profiles.add(this.mHfpClientProfile);
            removedProfiles.remove(this.mHfpClientProfile);
        }
        if (BluetoothUuid.containsAnyUuid(uuids, A2dpProfile.SINK_UUIDS) && this.mA2dpProfile != null) {
            profiles.add(this.mA2dpProfile);
            removedProfiles.remove(this.mA2dpProfile);
        }
        if (BluetoothUuid.containsAnyUuid(uuids, A2dpSinkProfile.SRC_UUIDS) && this.mA2dpSinkProfile != null) {
            profiles.add(this.mA2dpSinkProfile);
            removedProfiles.remove(this.mA2dpSinkProfile);
        }
        if (ArrayUtils.contains(uuids, BluetoothUuid.OBEX_OBJECT_PUSH) && this.mOppProfile != null) {
            profiles.add(this.mOppProfile);
            removedProfiles.remove(this.mOppProfile);
        }
        if ((ArrayUtils.contains(uuids, BluetoothUuid.HID) || ArrayUtils.contains(uuids, BluetoothUuid.HOGP)) && this.mHidProfile != null) {
            profiles.add(this.mHidProfile);
            removedProfiles.remove(this.mHidProfile);
        }
        if (this.mHidDeviceProfile != null && this.mHidDeviceProfile.getConnectionStatus(device) != 0) {
            profiles.add(this.mHidDeviceProfile);
            removedProfiles.remove(this.mHidDeviceProfile);
        }
        if (isPanNapConnected) {
            Log.d(TAG, "Valid PAN-NAP connection exists.");
        }
        if ((ArrayUtils.contains(uuids, BluetoothUuid.NAP) && this.mPanProfile != null) || isPanNapConnected) {
            profiles.add(this.mPanProfile);
            removedProfiles.remove(this.mPanProfile);
        }
        if (this.mMapProfile != null && this.mMapProfile.getConnectionStatus(device) == 2) {
            profiles.add(this.mMapProfile);
            removedProfiles.remove(this.mMapProfile);
            this.mMapProfile.setEnabled(device, true);
        }
        if (this.mPbapProfile != null && this.mPbapProfile.getConnectionStatus(device) == 2) {
            profiles.add(this.mPbapProfile);
            removedProfiles.remove(this.mPbapProfile);
            this.mPbapProfile.setEnabled(device, true);
        }
        if (this.mMapClientProfile != null && BluetoothUuid.containsAnyUuid(uuids, MapClientProfile.UUIDS)) {
            profiles.add(this.mMapClientProfile);
            removedProfiles.remove(this.mMapClientProfile);
        }
        if (this.mPbapClientProfile != null && BluetoothUuid.containsAnyUuid(uuids, PbapClientProfile.SRC_UUIDS)) {
            profiles.add(this.mPbapClientProfile);
            removedProfiles.remove(this.mPbapClientProfile);
        }
        if (ArrayUtils.contains(uuids, BluetoothUuid.HEARING_AID) && this.mHearingAidProfile != null) {
            profiles.add(this.mHearingAidProfile);
            removedProfiles.remove(this.mHearingAidProfile);
        }
        if (this.mSapProfile != null && ArrayUtils.contains(uuids, BluetoothUuid.SAP)) {
            profiles.add(this.mSapProfile);
            removedProfiles.remove(this.mSapProfile);
        }
        Log.d(TAG, "New Profiles" + profiles.toString());
    }
}
