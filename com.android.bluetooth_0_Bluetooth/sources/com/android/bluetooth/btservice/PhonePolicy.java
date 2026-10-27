package com.android.bluetooth.btservice;

import android.R;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothUuid;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.ParcelUuid;
import android.os.Parcelable;
import android.util.Log;
import com.android.bluetooth.a2dp.A2dpService;
import com.android.bluetooth.hearingaid.HearingAidService;
import com.android.bluetooth.hfp.HeadsetService;
import com.android.bluetooth.hid.HidHostService;
import com.android.bluetooth.pan.PanService;
import com.android.internal.util.ArrayUtils;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class PhonePolicy {
    private static final boolean DBG = true;
    private static final int MESSAGE_ADAPTER_STATE_TURNED_ON = 4;
    private static final int MESSAGE_CONNECT_OTHER_PROFILES = 3;
    private static final int MESSAGE_DEVICE_CONNECTED = 6;
    private static final int MESSAGE_PROFILE_ACTIVE_DEVICE_CHANGED = 5;
    private static final int MESSAGE_PROFILE_CONNECTION_STATE_CHANGED = 1;
    private static final int MESSAGE_PROFILE_INIT_PRIORITIES = 2;
    private static final String TAG = "BluetoothPhonePolicy";
    static int sConnectOtherProfilesTimeoutMillis = 6000;
    private final AdapterService mAdapterService;
    private final ServiceFactory mFactory;
    private final Handler mHandler;
    private final HashSet<BluetoothDevice> mHeadsetRetrySet = new HashSet<>();
    private final HashSet<BluetoothDevice> mA2dpRetrySet = new HashSet<>();
    private final HashSet<BluetoothDevice> mConnectOtherProfilesDeviceSet = new HashSet<>();
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.android.bluetooth.btservice.PhonePolicy.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == null) {
                PhonePolicy.errorLog("Received intent with null action");
                return;
            }
            switch (action) {
                case "android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED":
                    PhonePolicy.this.mHandler.obtainMessage(1, 1, -1, intent).sendToTarget();
                    return;
                case "android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED":
                    PhonePolicy.this.mHandler.obtainMessage(1, 2, -1, intent).sendToTarget();
                    return;
                case "android.bluetooth.a2dp.profile.action.ACTIVE_DEVICE_CHANGED":
                    PhonePolicy.this.mHandler.obtainMessage(5, 2, -1, intent).sendToTarget();
                    return;
                case "android.bluetooth.headset.profile.action.ACTIVE_DEVICE_CHANGED":
                    PhonePolicy.this.mHandler.obtainMessage(5, 1, -1, intent).sendToTarget();
                    return;
                case "android.bluetooth.hearingaid.profile.action.ACTIVE_DEVICE_CHANGED":
                    PhonePolicy.this.mHandler.obtainMessage(5, 21, -1, intent).sendToTarget();
                    return;
                case "android.bluetooth.adapter.action.STATE_CHANGED":
                    int newState = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1);
                    if (newState == 12) {
                        PhonePolicy.this.mHandler.obtainMessage(4).sendToTarget();
                        return;
                    }
                    return;
                case "android.bluetooth.device.action.UUID":
                    PhonePolicy.this.mHandler.obtainMessage(2, intent).sendToTarget();
                    return;
                case "android.bluetooth.device.action.ACL_CONNECTED":
                    PhonePolicy.this.mHandler.obtainMessage(6, intent).sendToTarget();
                    break;
            }
            Log.e(PhonePolicy.TAG, "Received unexpected intent, action=" + action);
        }
    };

    BroadcastReceiver getBroadcastReceiver() {
        return this.mReceiver;
    }

    class PhonePolicyHandler extends Handler {
        PhonePolicyHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Intent intent = (Intent) msg.obj;
                    BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    int prevState = intent.getIntExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", -1);
                    int nextState = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
                    PhonePolicy.this.processProfileStateChanged(device, msg.arg1, nextState, prevState);
                    break;
                case 2:
                    Intent intent2 = (Intent) msg.obj;
                    BluetoothDevice device2 = (BluetoothDevice) intent2.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    Parcelable[] uuids = intent2.getParcelableArrayExtra("android.bluetooth.device.extra.UUID");
                    PhonePolicy.debugLog("Received ACTION_UUID for device " + device2.getAddressOnlyforLog());
                    if (uuids != null) {
                        ParcelUuid[] uuidsToSend = new ParcelUuid[uuids.length];
                        for (int i = 0; i < uuidsToSend.length; i++) {
                            uuidsToSend[i] = (ParcelUuid) uuids[i];
                            PhonePolicy.debugLog("index=" + i + "uuid=" + uuidsToSend[i]);
                        }
                        PhonePolicy.this.processInitProfilePriorities(device2, uuidsToSend);
                    }
                    break;
                case 3:
                    BluetoothDevice device3 = (BluetoothDevice) msg.obj;
                    PhonePolicy.this.processConnectOtherProfiles(device3);
                    PhonePolicy.this.mConnectOtherProfilesDeviceSet.remove(device3);
                    break;
                case 4:
                    PhonePolicy.this.resetStates();
                    PhonePolicy.this.autoConnect();
                    break;
                case 5:
                    BluetoothDevice activeDevice = (BluetoothDevice) ((Intent) msg.obj).getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    PhonePolicy.this.processActiveDeviceChanged(activeDevice, msg.arg1);
                    break;
                case 6:
                    BluetoothDevice device4 = (BluetoothDevice) ((Intent) msg.obj).getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    PhonePolicy.this.processDeviceConnected(device4);
                    break;
            }
        }
    }

    protected void start() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
        filter.addAction("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
        filter.addAction("android.bluetooth.device.action.ACL_CONNECTED");
        filter.addAction("android.bluetooth.device.action.UUID");
        filter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        filter.addAction("android.bluetooth.a2dp.profile.action.ACTIVE_DEVICE_CHANGED");
        filter.addAction("android.bluetooth.headset.profile.action.ACTIVE_DEVICE_CHANGED");
        filter.addAction("android.bluetooth.hearingaid.profile.action.ACTIVE_DEVICE_CHANGED");
        this.mAdapterService.registerReceiver(this.mReceiver, filter);
    }

    protected void cleanup() {
        this.mAdapterService.unregisterReceiver(this.mReceiver);
        resetStates();
    }

    PhonePolicy(AdapterService service, ServiceFactory factory) {
        this.mAdapterService = service;
        this.mFactory = factory;
        this.mHandler = new PhonePolicyHandler(service.getMainLooper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processInitProfilePriorities(BluetoothDevice device, ParcelUuid[] uuids) {
        debugLog("processInitProfilePriorities() - device " + device.getAddressOnlyforLog());
        HidHostService hidService = this.mFactory.getHidHostService();
        A2dpService a2dpService = this.mFactory.getA2dpService();
        HeadsetService headsetService = this.mFactory.getHeadsetService();
        PanService panService = this.mFactory.getPanService();
        HearingAidService hearingAidService = this.mFactory.getHearingAidService();
        if (hidService != null && ((ArrayUtils.contains(uuids, BluetoothUuid.HID) || ArrayUtils.contains(uuids, BluetoothUuid.HOGP)) && hidService.getConnectionPolicy(device) == -1)) {
            this.mAdapterService.getDatabase().setProfileConnectionPolicy(device, 4, 100);
        }
        if (headsetService != null && ((ArrayUtils.contains(uuids, BluetoothUuid.HSP) || ArrayUtils.contains(uuids, BluetoothUuid.HFP)) && headsetService.getConnectionPolicy(device) == -1)) {
            this.mAdapterService.getDatabase().setProfileConnectionPolicy(device, 1, 100);
        }
        if (a2dpService != null && ((ArrayUtils.contains(uuids, BluetoothUuid.A2DP_SINK) || ArrayUtils.contains(uuids, BluetoothUuid.ADV_AUDIO_DIST)) && a2dpService.getConnectionPolicy(device) == -1)) {
            this.mAdapterService.getDatabase().setProfileConnectionPolicy(device, 2, 100);
        }
        if (panService != null && ArrayUtils.contains(uuids, BluetoothUuid.PANU) && panService.getConnectionPolicy(device) == -1 && this.mAdapterService.getResources().getBoolean(R.bool.config_appCompatUserAppAspectRatioFullscreenIsEnabled)) {
            this.mAdapterService.getDatabase().setProfileConnectionPolicy(device, 5, 100);
        }
        if (hearingAidService != null && ArrayUtils.contains(uuids, BluetoothUuid.HEARING_AID) && hearingAidService.getConnectionPolicy(device) == -1) {
            debugLog("setting hearing aid profile priority for device " + device.getAddressOnlyforLog());
            this.mAdapterService.getDatabase().setProfileConnectionPolicy(device, 21, 100);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processProfileStateChanged(BluetoothDevice device, int profileId, int nextState, int prevState) {
        debugLog("processProfileStateChanged, device=" + device.getAddressOnlyforLog() + ", profile=" + profileId + ", " + prevState + " -> " + nextState);
        if (profileId == 2 || profileId == 1) {
            if (nextState == 2) {
                if (profileId == 1) {
                    this.mHeadsetRetrySet.remove(device);
                } else if (profileId == 2) {
                    this.mA2dpRetrySet.remove(device);
                }
                connectOtherProfile(device);
            }
            if (nextState == 0) {
                if (profileId == 2) {
                    this.mAdapterService.getDatabase().setDisconnection(device);
                }
                handleAllProfilesDisconnected(device);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processActiveDeviceChanged(BluetoothDevice device, int profileId) {
        debugLog("processActiveDeviceChanged, device=" + device.getAddressOnlyforLog() + ", profile=" + profileId);
        if (device != null) {
            this.mAdapterService.getDatabase().setConnection(device, profileId == 2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processDeviceConnected(BluetoothDevice device) {
        debugLog("processDeviceConnected, device=" + device.getAddressOnlyforLog());
        this.mAdapterService.getDatabase().setConnection(device, false);
    }

    private boolean handleAllProfilesDisconnected(BluetoothDevice device) {
        boolean atLeastOneProfileConnectedForDevice = false;
        boolean allProfilesEmpty = true;
        HeadsetService hsService = this.mFactory.getHeadsetService();
        A2dpService a2dpService = this.mFactory.getA2dpService();
        PanService panService = this.mFactory.getPanService();
        if (hsService != null) {
            List<BluetoothDevice> hsConnDevList = hsService.getConnectedDevices();
            allProfilesEmpty = true & hsConnDevList.isEmpty();
            atLeastOneProfileConnectedForDevice = false | hsConnDevList.contains(device);
        }
        if (a2dpService != null) {
            List<BluetoothDevice> a2dpConnDevList = a2dpService.getConnectedDevices();
            allProfilesEmpty &= a2dpConnDevList.isEmpty();
            atLeastOneProfileConnectedForDevice |= a2dpConnDevList.contains(device);
        }
        if (panService != null) {
            List<BluetoothDevice> panConnDevList = panService.getConnectedDevices();
            allProfilesEmpty &= panConnDevList.isEmpty();
            atLeastOneProfileConnectedForDevice |= panConnDevList.contains(device);
        }
        if (!atLeastOneProfileConnectedForDevice) {
            debugLog("handleAllProfilesDisconnected: all profiles disconnected for " + device.getAddressOnlyforLog());
            this.mHeadsetRetrySet.remove(device);
            this.mA2dpRetrySet.remove(device);
            if (allProfilesEmpty) {
                debugLog("handleAllProfilesDisconnected: all profiles disconnected for all devices");
                resetStates();
                return true;
            }
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetStates() {
        this.mHeadsetRetrySet.clear();
        this.mA2dpRetrySet.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void autoConnect() {
        if (this.mAdapterService.getState() != 12) {
            errorLog("autoConnect: BT is not ON. Exiting autoConnect");
            return;
        }
        if (!this.mAdapterService.isQuietModeEnabled()) {
            debugLog("autoConnect: Initiate auto connection on BT on...");
            BluetoothDevice mostRecentlyActiveA2dpDevice = this.mAdapterService.getDatabase().getMostRecentlyConnectedA2dpDevice();
            if (mostRecentlyActiveA2dpDevice == null) {
                errorLog("autoConnect: most recently active a2dp device is null");
                return;
            }
            debugLog("autoConnect: Device " + mostRecentlyActiveA2dpDevice + " attempting auto connection");
            autoConnectHeadset(mostRecentlyActiveA2dpDevice);
            autoConnectA2dp(mostRecentlyActiveA2dpDevice);
            return;
        }
        debugLog("autoConnect() - BT is in quiet mode. Not initiating auto connections");
    }

    private void autoConnectA2dp(BluetoothDevice device) {
        A2dpService a2dpService = this.mFactory.getA2dpService();
        if (a2dpService == null) {
            warnLog("autoConnectA2dp: service is null, failed to connect to " + device.getAddressOnlyforLog());
            return;
        }
        int a2dpConnectionPolicy = a2dpService.getConnectionPolicy(device);
        if (a2dpConnectionPolicy == 100) {
            debugLog("autoConnectA2dp: connecting A2DP with " + device.getAddressOnlyforLog());
            a2dpService.connect(device);
            return;
        }
        debugLog("autoConnectA2dp: skipped auto-connect A2DP with device " + device.getAddressOnlyforLog() + " connectionPolicy " + a2dpConnectionPolicy);
    }

    private void autoConnectHeadset(BluetoothDevice device) {
        HeadsetService hsService = this.mFactory.getHeadsetService();
        if (hsService == null) {
            warnLog("autoConnectHeadset: service is null, failed to connect to " + device.getAddressOnlyforLog());
            return;
        }
        int headsetConnectionPolicy = hsService.getConnectionPolicy(device);
        if (headsetConnectionPolicy == 100) {
            debugLog("autoConnectHeadset: Connecting HFP with " + device.getAddressOnlyforLog());
            hsService.connect(device);
            return;
        }
        debugLog("autoConnectHeadset: skipped auto-connect HFP with device " + device.getAddressOnlyforLog() + " connectionPolicy " + headsetConnectionPolicy);
    }

    private void connectOtherProfile(BluetoothDevice device) {
        if (this.mAdapterService.isQuietModeEnabled()) {
            debugLog("connectOtherProfile: in quiet mode, skip connect other profile " + device.getAddressOnlyforLog());
            return;
        }
        if (this.mConnectOtherProfilesDeviceSet.contains(device)) {
            debugLog("connectOtherProfile: already scheduled callback for " + device.getAddressOnlyforLog());
            return;
        }
        this.mConnectOtherProfilesDeviceSet.add(device);
        Message m = this.mHandler.obtainMessage(3);
        m.obj = device;
        this.mHandler.sendMessageDelayed(m, sConnectOtherProfilesTimeoutMillis);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processConnectOtherProfiles(BluetoothDevice device) {
        debugLog("processConnectOtherProfiles, device=" + device.getAddressOnlyforLog());
        if (this.mAdapterService.getState() != 12) {
            warnLog("processConnectOtherProfiles, adapter is not ON " + this.mAdapterService.getState());
            return;
        }
        if (handleAllProfilesDisconnected(device)) {
            debugLog("processConnectOtherProfiles: all profiles disconnected for " + device.getAddressOnlyforLog());
            return;
        }
        HeadsetService hsService = this.mFactory.getHeadsetService();
        A2dpService a2dpService = this.mFactory.getA2dpService();
        PanService panService = this.mFactory.getPanService();
        if (hsService != null && !this.mHeadsetRetrySet.contains(device) && hsService.getConnectionPolicy(device) == 100 && hsService.getConnectionState(device) == 0) {
            debugLog("Retrying connection to Headset with device " + device.getAddressOnlyforLog());
            this.mHeadsetRetrySet.add(device);
            hsService.connect(device);
        }
        if (a2dpService != null && !this.mA2dpRetrySet.contains(device) && a2dpService.getConnectionPolicy(device) == 100 && a2dpService.getConnectionState(device) == 0) {
            debugLog("Retrying connection to A2DP with device " + device.getAddressOnlyforLog());
            this.mA2dpRetrySet.add(device);
            a2dpService.connect(device);
        }
        if (panService != null) {
            List<BluetoothDevice> panConnDevList = panService.getConnectedDevices();
            if (panConnDevList.isEmpty() && panService.getConnectionPolicy(device) == 100 && panService.getConnectionState(device) == 0) {
                debugLog("Retrying connection to PAN with device " + device.getAddressOnlyforLog());
                panService.connect(device);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void debugLog(String msg) {
        Log.i(TAG, msg);
    }

    private static void warnLog(String msg) {
        Log.w(TAG, msg);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void errorLog(String msg) {
        Log.e(TAG, msg);
    }
}
