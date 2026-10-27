package com.android.bluetooth.hearingaid;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothUuid;
import android.bluetooth.IBluetoothHearingAid;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.HandlerThread;
import android.os.ParcelUuid;
import android.util.Log;
import com.android.bluetooth.BluetoothMetricsProto;
import com.android.bluetooth.BluetoothStatsLog;
import com.android.bluetooth.Utils;
import com.android.bluetooth.btservice.AdapterService;
import com.android.bluetooth.btservice.MetricsLogger;
import com.android.bluetooth.btservice.ProfileService;
import com.android.bluetooth.btservice.ServiceFactory;
import com.android.internal.util.ArrayUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public class HearingAidService extends ProfileService {
    private static final boolean DBG = true;
    private static final int MAX_HEARING_AID_STATE_MACHINES = 10;
    private static final String TAG = "HearingAidService";
    private static HearingAidService sHearingAidService;
    private AdapterService mAdapterService;
    AudioManager mAudioManager;
    private BroadcastReceiver mBondStateChangedReceiver;
    private BroadcastReceiver mConnectionStateChangedReceiver;
    HearingAidNativeInterface mHearingAidNativeInterface;
    private BluetoothDevice mPreviousAudioDevice;
    private HandlerThread mStateMachinesThread;
    private final Map<BluetoothDevice, HearingAidStateMachine> mStateMachines = new HashMap();
    private final Map<BluetoothDevice, Long> mDeviceHiSyncIdMap = new ConcurrentHashMap();
    private final Map<BluetoothDevice, Integer> mDeviceCapabilitiesMap = new HashMap();
    private final Map<Long, Boolean> mHiSyncIdConnectedMap = new HashMap();
    private long mActiveDeviceHiSyncId = 0;
    private final ServiceFactory mFactory = new ServiceFactory();

    @Override // com.android.bluetooth.btservice.ProfileService
    protected ProfileService.IProfileServiceBinder initBinder() {
        return new BluetoothHearingAidBinder(this);
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected void create() {
        Log.d(TAG, "create()");
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected boolean start() {
        Log.d(TAG, "start()");
        if (sHearingAidService != null) {
            throw new IllegalStateException("start() called twice");
        }
        AdapterService adapterService = AdapterService.getAdapterService();
        Objects.requireNonNull(adapterService, "AdapterService cannot be null when HearingAidService starts");
        this.mAdapterService = adapterService;
        HearingAidNativeInterface hearingAidNativeInterface = HearingAidNativeInterface.getInstance();
        Objects.requireNonNull(hearingAidNativeInterface, "HearingAidNativeInterface cannot be null when HearingAidService starts");
        this.mHearingAidNativeInterface = hearingAidNativeInterface;
        AudioManager audioManager = (AudioManager) getSystemService("audio");
        this.mAudioManager = audioManager;
        Objects.requireNonNull(audioManager, "AudioManager cannot be null when HearingAidService starts");
        this.mStateMachines.clear();
        HandlerThread handlerThread = new HandlerThread("HearingAidService.StateMachines");
        this.mStateMachinesThread = handlerThread;
        handlerThread.start();
        this.mDeviceHiSyncIdMap.clear();
        this.mDeviceCapabilitiesMap.clear();
        this.mHiSyncIdConnectedMap.clear();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
        BondStateChangedReceiver bondStateChangedReceiver = new BondStateChangedReceiver();
        this.mBondStateChangedReceiver = bondStateChangedReceiver;
        registerReceiver(bondStateChangedReceiver, filter);
        IntentFilter filter2 = new IntentFilter();
        filter2.addAction("android.bluetooth.hearingaid.profile.action.CONNECTION_STATE_CHANGED");
        ConnectionStateChangedReceiver connectionStateChangedReceiver = new ConnectionStateChangedReceiver();
        this.mConnectionStateChangedReceiver = connectionStateChangedReceiver;
        registerReceiver(connectionStateChangedReceiver, filter2);
        setHearingAidService(this);
        this.mHearingAidNativeInterface.init();
        return true;
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected boolean stop() {
        Log.d(TAG, "stop()");
        if (sHearingAidService == null) {
            Log.w(TAG, "stop() called before start()");
            return true;
        }
        this.mHearingAidNativeInterface.cleanup();
        this.mHearingAidNativeInterface = null;
        setHearingAidService(null);
        unregisterReceiver(this.mBondStateChangedReceiver);
        this.mBondStateChangedReceiver = null;
        unregisterReceiver(this.mConnectionStateChangedReceiver);
        this.mConnectionStateChangedReceiver = null;
        synchronized (this.mStateMachines) {
            for (HearingAidStateMachine sm : this.mStateMachines.values()) {
                sm.doQuit();
                sm.cleanup();
            }
            this.mStateMachines.clear();
        }
        this.mDeviceHiSyncIdMap.clear();
        this.mDeviceCapabilitiesMap.clear();
        this.mHiSyncIdConnectedMap.clear();
        HandlerThread handlerThread = this.mStateMachinesThread;
        if (handlerThread != null) {
            handlerThread.quitSafely();
            this.mStateMachinesThread = null;
        }
        this.mAudioManager = null;
        this.mHearingAidNativeInterface = null;
        this.mAdapterService = null;
        return true;
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected void cleanup() {
        Log.d(TAG, "cleanup()");
    }

    public static synchronized HearingAidService getHearingAidService() {
        if (sHearingAidService == null) {
            Log.w(TAG, "getHearingAidService(): service is NULL");
            return null;
        }
        if (!sHearingAidService.isAvailable()) {
            Log.w(TAG, "getHearingAidService(): service is not available");
            return null;
        }
        return sHearingAidService;
    }

    private static synchronized void setHearingAidService(HearingAidService instance) {
        Log.d(TAG, "setHearingAidService(): set to: " + instance);
        sHearingAidService = instance;
    }

    public boolean connect(BluetoothDevice device) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_PRIVILEGED", "Need BLUETOOTH_PRIVILEGED permission");
        Log.d(TAG, "connect(): " + device);
        if (device == null || getConnectionPolicy(device) == 0) {
            return false;
        }
        ParcelUuid[] featureUuids = this.mAdapterService.getRemoteUuids(device);
        if (!ArrayUtils.contains(featureUuids, BluetoothUuid.HEARING_AID)) {
            Log.e(TAG, "Cannot connect to " + device + " : Remote does not have Hearing Aid UUID");
            return false;
        }
        long hiSyncId = this.mDeviceHiSyncIdMap.getOrDefault(device, 0L).longValue();
        long j = this.mActiveDeviceHiSyncId;
        if (hiSyncId != j && hiSyncId != 0 && j != 0) {
            for (BluetoothDevice connectedDevice : getConnectedDevices()) {
                disconnect(connectedDevice);
            }
        }
        synchronized (this.mStateMachines) {
            HearingAidStateMachine smConnect = getOrCreateStateMachine(device);
            if (smConnect == null) {
                Log.e(TAG, "Cannot connect to " + device + " : no state machine");
            }
            smConnect.sendMessage(1);
        }
        for (BluetoothDevice storedDevice : this.mDeviceHiSyncIdMap.keySet()) {
            if (!device.equals(storedDevice) && this.mDeviceHiSyncIdMap.getOrDefault(storedDevice, 0L).longValue() == hiSyncId) {
                synchronized (this.mStateMachines) {
                    HearingAidStateMachine sm = getOrCreateStateMachine(storedDevice);
                    if (sm == null) {
                        Log.e(TAG, "Ignored connect request for " + device + " : no state machine");
                    } else {
                        sm.sendMessage(1);
                        if (hiSyncId == 0 && !device.equals(storedDevice)) {
                            break;
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean disconnect(BluetoothDevice device) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_PRIVILEGED", "Need BLUETOOTH_PRIVILEGED permission");
        Log.d(TAG, "disconnect(): " + device);
        if (device == null) {
            return false;
        }
        long hiSyncId = this.mDeviceHiSyncIdMap.getOrDefault(device, 0L).longValue();
        for (BluetoothDevice storedDevice : this.mDeviceHiSyncIdMap.keySet()) {
            if (this.mDeviceHiSyncIdMap.getOrDefault(storedDevice, 0L).longValue() == hiSyncId) {
                synchronized (this.mStateMachines) {
                    HearingAidStateMachine sm = this.mStateMachines.get(storedDevice);
                    if (sm == null) {
                        Log.e(TAG, "Ignored disconnect request for " + device + " : no state machine");
                    } else {
                        sm.sendMessage(2);
                        if (hiSyncId == 0 && !device.equals(storedDevice)) {
                            return true;
                        }
                    }
                }
            }
        }
        return true;
    }

    List<BluetoothDevice> getConnectedDevices() {
        List<BluetoothDevice> devices;
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        synchronized (this.mStateMachines) {
            devices = new ArrayList<>();
            for (HearingAidStateMachine sm : this.mStateMachines.values()) {
                if (sm.isConnected()) {
                    devices.add(sm.getDevice());
                }
            }
        }
        return devices;
    }

    public boolean isConnectedPeerDevices(BluetoothDevice device) {
        long hiSyncId = getHiSyncId(device);
        if (getConnectedPeerDevices(hiSyncId).isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean okToConnect(BluetoothDevice device) {
        if (this.mAdapterService.isQuietModeEnabled()) {
            Log.e(TAG, "okToConnect: cannot connect to " + device + " : quiet mode enabled");
            return false;
        }
        int connectionPolicy = getConnectionPolicy(device);
        int bondState = this.mAdapterService.getBondState(device);
        if (bondState != 12) {
            Log.w(TAG, "okToConnect: return false, bondState=" + bondState);
            return false;
        }
        if (connectionPolicy != -1 && connectionPolicy != 100) {
            Log.w(TAG, "okToConnect: return false, connectionPolicy=" + connectionPolicy);
            return false;
        }
        return true;
    }

    List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states) {
        BluetoothDevice[] bondedDevices;
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        ArrayList<BluetoothDevice> devices = new ArrayList<>();
        if (states == null || (bondedDevices = this.mAdapterService.getBondedDevices()) == null) {
            return devices;
        }
        synchronized (this.mStateMachines) {
            for (BluetoothDevice device : bondedDevices) {
                ParcelUuid[] featureUuids = device.getUuids();
                if (ArrayUtils.contains(featureUuids, BluetoothUuid.HEARING_AID)) {
                    int connectionState = 0;
                    HearingAidStateMachine sm = this.mStateMachines.get(device);
                    if (sm != null) {
                        connectionState = sm.getConnectionState();
                    }
                    int length = states.length;
                    int i = 0;
                    while (true) {
                        if (i < length) {
                            int state = states[i];
                            if (connectionState != state) {
                                i++;
                            } else {
                                devices.add(device);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return devices;
    }

    List<BluetoothDevice> getDevices() {
        List<BluetoothDevice> devices = new ArrayList<>();
        synchronized (this.mStateMachines) {
            for (HearingAidStateMachine sm : this.mStateMachines.values()) {
                devices.add(sm.getDevice());
            }
        }
        return devices;
    }

    Map<BluetoothDevice, Long> getHiSyncIdMap() {
        return this.mDeviceHiSyncIdMap;
    }

    public int getConnectionState(BluetoothDevice device) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        synchronized (this.mStateMachines) {
            HearingAidStateMachine sm = this.mStateMachines.get(device);
            if (sm == null) {
                return 0;
            }
            return sm.getConnectionState();
        }
    }

    public boolean setConnectionPolicy(BluetoothDevice device, int connectionPolicy) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_PRIVILEGED", "Need BLUETOOTH_PRIVILEGED permission");
        Log.d(TAG, "Saved connectionPolicy " + device + " = " + connectionPolicy);
        this.mAdapterService.getDatabase().setProfileConnectionPolicy(device, 21, connectionPolicy);
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
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_PRIVILEGED", "Need BLUETOOTH_PRIVILEGED permission");
        return this.mAdapterService.getDatabase().getProfileConnectionPolicy(device, 21);
    }

    void setVolume(int volume) {
        this.mHearingAidNativeInterface.setVolume(volume);
    }

    long getHiSyncId(BluetoothDevice device) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_PRIVILEGED", "Need BLUETOOTH_PRIVILEGED permission");
        if (device == null) {
            return 0L;
        }
        return this.mDeviceHiSyncIdMap.getOrDefault(device, 0L).longValue();
    }

    int getCapabilities(BluetoothDevice device) {
        return this.mDeviceCapabilitiesMap.getOrDefault(device, -1).intValue();
    }

    public boolean setActiveDevice(BluetoothDevice device) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN", "Need BLUETOOTH ADMIN permission");
        Log.d(TAG, "setActiveDevice:" + device);
        synchronized (this.mStateMachines) {
            if (device == null) {
                if (this.mActiveDeviceHiSyncId != 0) {
                    reportActiveDevice(null);
                    this.mActiveDeviceHiSyncId = 0L;
                }
                return true;
            }
            if (getConnectionState(device) != 2) {
                Log.e(TAG, "setActiveDevice(" + device + "): failed because device not connected");
                return false;
            }
            Long deviceHiSyncId = this.mDeviceHiSyncIdMap.getOrDefault(device, 0L);
            if (deviceHiSyncId.longValue() != this.mActiveDeviceHiSyncId) {
                this.mActiveDeviceHiSyncId = deviceHiSyncId.longValue();
                reportActiveDevice(device);
            }
            return true;
        }
    }

    public List<BluetoothDevice> getActiveDevices() {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        Log.d(TAG, "getActiveDevices");
        ArrayList<BluetoothDevice> activeDevices = new ArrayList<>();
        activeDevices.add(null);
        activeDevices.add(null);
        synchronized (this.mStateMachines) {
            if (this.mActiveDeviceHiSyncId == 0) {
                return activeDevices;
            }
            for (BluetoothDevice device : this.mDeviceHiSyncIdMap.keySet()) {
                if (getConnectionState(device) == 2) {
                    if (this.mDeviceHiSyncIdMap.get(device).longValue() == this.mActiveDeviceHiSyncId) {
                        int deviceSide = getCapabilities(device) & 1;
                        if (deviceSide == 1) {
                            activeDevices.set(1, device);
                        } else {
                            activeDevices.set(0, device);
                        }
                    }
                }
            }
            return activeDevices;
        }
    }

    void messageFromNative(HearingAidStackEvent stackEvent) {
        int i;
        Objects.requireNonNull(stackEvent.device, "Device should never be null, event: " + stackEvent);
        if (stackEvent.type == 2) {
            BluetoothDevice device = stackEvent.device;
            int capabilities = stackEvent.valueInt1;
            long hiSyncId = stackEvent.valueLong2;
            Log.d(TAG, "Device available: device=" + device + " capabilities=" + capabilities + " hiSyncId=" + hiSyncId);
            this.mDeviceCapabilitiesMap.put(device, Integer.valueOf(capabilities));
            this.mDeviceHiSyncIdMap.put(device, Long.valueOf(hiSyncId));
            return;
        }
        synchronized (this.mStateMachines) {
            BluetoothDevice device2 = stackEvent.device;
            HearingAidStateMachine sm = this.mStateMachines.get(device2);
            if (sm == null && stackEvent.type == 1 && ((i = stackEvent.valueInt1) == 1 || i == 2)) {
                sm = getOrCreateStateMachine(device2);
            }
            if (sm == null) {
                Log.e(TAG, "Cannot process stack event: no state machine: " + stackEvent);
                return;
            }
            sm.sendMessage(101, stackEvent);
        }
    }

    private HearingAidStateMachine getOrCreateStateMachine(BluetoothDevice device) {
        if (device == null) {
            Log.e(TAG, "getOrCreateStateMachine failed: device cannot be null");
            return null;
        }
        synchronized (this.mStateMachines) {
            HearingAidStateMachine sm = this.mStateMachines.get(device);
            if (sm != null) {
                return sm;
            }
            if (this.mStateMachines.size() >= 10) {
                Log.e(TAG, "Maximum number of HearingAid state machines reached: 10");
                return null;
            }
            Log.d(TAG, "Creating a new state machine for " + device);
            HearingAidStateMachine sm2 = HearingAidStateMachine.make(device, this, this.mHearingAidNativeInterface, this.mStateMachinesThread.getLooper());
            this.mStateMachines.put(device, sm2);
            return sm2;
        }
    }

    private void reportActiveDevice(BluetoothDevice device) {
        Log.d(TAG, "reportActiveDevice(" + device + ")");
        BluetoothStatsLog.write(151, 21, this.mAdapterService.obfuscateAddress(device), this.mAdapterService.getMetricId(device));
        Intent intent = new Intent("android.bluetooth.hearingaid.profile.action.ACTIVE_DEVICE_CHANGED");
        intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
        intent.addFlags(83886080);
        sendBroadcast(intent, ProfileService.BLUETOOTH_PERM);
        if (device == null) {
            Log.d(TAG, "Set Hearing Aid audio to disconnected");
            boolean suppressNoisyIntent = getConnectionState(this.mPreviousAudioDevice) == 2;
            this.mAudioManager.setBluetoothHearingAidDeviceConnectionState(this.mPreviousAudioDevice, 0, suppressNoisyIntent, 0);
            this.mPreviousAudioDevice = null;
            return;
        }
        Log.d(TAG, "Set Hearing Aid audio to connected");
        BluetoothDevice bluetoothDevice = this.mPreviousAudioDevice;
        if (bluetoothDevice != null) {
            this.mAudioManager.setBluetoothHearingAidDeviceConnectionState(bluetoothDevice, 0, true, 0);
        }
        this.mAudioManager.setBluetoothHearingAidDeviceConnectionState(device, 2, true, 0);
        this.mPreviousAudioDevice = device;
    }

    private class BondStateChangedReceiver extends BroadcastReceiver {
        private BondStateChangedReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (!"android.bluetooth.device.action.BOND_STATE_CHANGED".equals(intent.getAction())) {
                return;
            }
            int state = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", Integer.MIN_VALUE);
            BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            Objects.requireNonNull(device, "ACTION_BOND_STATE_CHANGED with no EXTRA_DEVICE");
            HearingAidService.this.bondStateChanged(device, state);
        }
    }

    void bondStateChanged(BluetoothDevice device, int bondState) {
        Log.d(TAG, "Bond state changed for device: " + device + " state: " + bondState);
        if (bondState != 10) {
            return;
        }
        this.mDeviceHiSyncIdMap.remove(device);
        synchronized (this.mStateMachines) {
            HearingAidStateMachine sm = this.mStateMachines.get(device);
            if (sm == null) {
                return;
            }
            if (sm.getConnectionState() != 0) {
                return;
            }
            removeStateMachine(device);
        }
    }

    private void removeStateMachine(BluetoothDevice device) {
        synchronized (this.mStateMachines) {
            HearingAidStateMachine sm = this.mStateMachines.get(device);
            if (sm == null) {
                Log.w(TAG, "removeStateMachine: device " + device + " does not have a state machine");
                return;
            }
            Log.i(TAG, "removeStateMachine: removing state machine for device: " + device);
            sm.doQuit();
            sm.cleanup();
            this.mStateMachines.remove(device);
        }
    }

    private List<BluetoothDevice> getConnectedPeerDevices(long hiSyncId) {
        List<BluetoothDevice> result = new ArrayList<>();
        for (BluetoothDevice peerDevice : getConnectedDevices()) {
            if (getHiSyncId(peerDevice) == hiSyncId) {
                result.add(peerDevice);
            }
        }
        return result;
    }

    synchronized void connectionStateChanged(BluetoothDevice device, int fromState, int toState) {
        if (device == null || fromState == toState) {
            Log.e(TAG, "connectionStateChanged: unexpected invocation. device=" + device + " fromState=" + fromState + " toState=" + toState);
            return;
        }
        if (toState == 2) {
            long myHiSyncId = getHiSyncId(device);
            if (myHiSyncId == 0 || getConnectedPeerDevices(myHiSyncId).size() == 1) {
                MetricsLogger.logProfileConnectionEvent(BluetoothMetricsProto.ProfileId.HEARING_AID);
            }
            if (!this.mHiSyncIdConnectedMap.getOrDefault(Long.valueOf(myHiSyncId), false).booleanValue()) {
                setActiveDevice(device);
                this.mHiSyncIdConnectedMap.put(Long.valueOf(myHiSyncId), true);
            }
        }
        if (fromState == 2 && getConnectedDevices().isEmpty()) {
            setActiveDevice(null);
            this.mHiSyncIdConnectedMap.put(Long.valueOf(getHiSyncId(device)), false);
        }
        if (toState == 0) {
            int bondState = this.mAdapterService.getBondState(device);
            if (bondState == 10) {
                Log.d(TAG, device + " is unbond. Remove state machine");
                removeStateMachine(device);
            }
        }
    }

    private class ConnectionStateChangedReceiver extends BroadcastReceiver {
        private ConnectionStateChangedReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (!"android.bluetooth.hearingaid.profile.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                return;
            }
            BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            int toState = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
            int fromState = intent.getIntExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", -1);
            HearingAidService.this.connectionStateChanged(device, fromState, toState);
        }
    }

    static class BluetoothHearingAidBinder extends IBluetoothHearingAid.Stub implements ProfileService.IProfileServiceBinder {
        private HearingAidService mService;

        private HearingAidService getService() {
            if (!Utils.checkCaller()) {
                Log.w(HearingAidService.TAG, "HearingAid call not allowed for non-active user");
                return null;
            }
            HearingAidService hearingAidService = this.mService;
            if (hearingAidService == null || !hearingAidService.isAvailable()) {
                return null;
            }
            return this.mService;
        }

        BluetoothHearingAidBinder(HearingAidService svc) {
            this.mService = svc;
        }

        @Override // com.android.bluetooth.btservice.ProfileService.IProfileServiceBinder
        public void cleanup() {
            this.mService = null;
        }

        public boolean connect(BluetoothDevice device) {
            HearingAidService service = getService();
            if (service == null) {
                return false;
            }
            return service.connect(device);
        }

        public boolean disconnect(BluetoothDevice device) {
            HearingAidService service = getService();
            if (service == null) {
                return false;
            }
            return service.disconnect(device);
        }

        public List<BluetoothDevice> getConnectedDevices() {
            HearingAidService service = getService();
            if (service == null) {
                return new ArrayList();
            }
            return service.getConnectedDevices();
        }

        public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states) {
            HearingAidService service = getService();
            if (service == null) {
                return new ArrayList();
            }
            return service.getDevicesMatchingConnectionStates(states);
        }

        public int getConnectionState(BluetoothDevice device) {
            HearingAidService service = getService();
            if (service == null) {
                return 0;
            }
            return service.getConnectionState(device);
        }

        public boolean setActiveDevice(BluetoothDevice device) {
            HearingAidService service = getService();
            if (service == null) {
                return false;
            }
            return service.setActiveDevice(device);
        }

        public List<BluetoothDevice> getActiveDevices() {
            HearingAidService service = getService();
            if (service == null) {
                return new ArrayList();
            }
            return service.getActiveDevices();
        }

        public boolean setConnectionPolicy(BluetoothDevice device, int connectionPolicy) {
            HearingAidService service = getService();
            if (service == null) {
                return false;
            }
            return service.setConnectionPolicy(device, connectionPolicy);
        }

        public int getConnectionPolicy(BluetoothDevice device) {
            HearingAidService service = getService();
            if (service == null) {
                return -1;
            }
            return service.getConnectionPolicy(device);
        }

        public void setVolume(int volume) {
            HearingAidService service = getService();
            if (service == null) {
                return;
            }
            service.setVolume(volume);
        }

        public long getHiSyncId(BluetoothDevice device) {
            HearingAidService service = getService();
            if (service == null) {
                return 0L;
            }
            return service.getHiSyncId(device);
        }

        public int getDeviceSide(BluetoothDevice device) {
            HearingAidService service = getService();
            if (service == null) {
                return 1;
            }
            return 1 & service.getCapabilities(device);
        }

        public int getDeviceMode(BluetoothDevice device) {
            HearingAidService service = getService();
            if (service == null) {
                return 1;
            }
            return 1 & (service.getCapabilities(device) >> 1);
        }
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    public void dump(StringBuilder sb) {
        super.dump(sb);
        for (HearingAidStateMachine sm : this.mStateMachines.values()) {
            sm.dump(sb);
        }
    }
}
