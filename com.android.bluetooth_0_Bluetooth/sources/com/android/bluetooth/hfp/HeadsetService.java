package com.android.bluetooth.hfp;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothUuid;
import android.bluetooth.IBluetoothHeadset;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.INetd;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.ParcelUuid;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.util.Log;
import com.android.bluetooth.BluetoothMetricsProto;
import com.android.bluetooth.BluetoothStatsLog;
import com.android.bluetooth.Utils;
import com.android.bluetooth.btservice.AdapterService;
import com.android.bluetooth.btservice.MetricsLogger;
import com.android.bluetooth.btservice.ProfileService;
import com.android.bluetooth.sap.SapService;
import com.android.vcard.VCardConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.function.ToLongFunction;

/* JADX INFO: loaded from: classes.dex */
public class HeadsetService extends ProfileService {
    private static final boolean DBG = false;
    private static final int DIALING_OUT_TIMEOUT_MS = 10000;
    private static final String DISABLE_INBAND_RINGING_PROPERTY = "persist.bluetooth.disableinbandringing";
    private static final String TAG = "HeadsetService";
    private static HeadsetService sHeadsetService;
    private BluetoothDevice mActiveDevice;
    private AdapterService mAdapterService;
    private boolean mCreated;
    private DialingOutTimeoutEvent mDialingOutTimeoutEvent;
    private boolean mForceScoAudio;
    private boolean mInbandRingingRuntimeDisable;
    private HeadsetNativeInterface mNativeInterface;
    private boolean mStarted;
    private HandlerThread mStateMachinesThread;
    private Handler mStateMachinesThreadHandler;
    private HeadsetSystemInterface mSystemInterface;
    private boolean mVirtualCallStarted;
    private boolean mVoiceRecognitionStarted;
    private VoiceRecognitionTimeoutEvent mVoiceRecognitionTimeoutEvent;
    private static final ParcelUuid[] HEADSET_UUIDS = {BluetoothUuid.HSP, BluetoothUuid.HFP};
    private static final int[] CONNECTING_CONNECTED_STATES = {1, 2};
    static int sStartVrTimeoutMs = SapService.MSG_SERVERSESSION_CLOSE;
    private int mMaxHeadsetConnections = 1;
    private final HashMap<BluetoothDevice, HeadsetStateMachine> mStateMachines = new HashMap<>();
    private boolean mAudioRouteAllowed = true;
    private final BroadcastReceiver mHeadsetReceiver = new AnonymousClass1();

    interface StateMachineTask {
        void execute(HeadsetStateMachine headsetStateMachine);
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    public ProfileService.IProfileServiceBinder initBinder() {
        return new BluetoothHeadsetBinder(this);
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected void create() {
        Log.i(TAG, "create()");
        if (this.mCreated) {
            throw new IllegalStateException("create() called twice");
        }
        this.mCreated = true;
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected boolean start() {
        Log.i(TAG, "start()");
        if (this.mStarted) {
            throw new IllegalStateException("start() called twice");
        }
        AdapterService adapterService = AdapterService.getAdapterService();
        Objects.requireNonNull(adapterService, "AdapterService cannot be null when HeadsetService starts");
        this.mAdapterService = adapterService;
        HandlerThread handlerThread = new HandlerThread("HeadsetService.StateMachines");
        this.mStateMachinesThread = handlerThread;
        handlerThread.start();
        HeadsetSystemInterface headsetSystemInterfaceMakeSystemInterface = HeadsetObjectsFactory.getInstance().makeSystemInterface(this);
        this.mSystemInterface = headsetSystemInterfaceMakeSystemInterface;
        headsetSystemInterfaceMakeSystemInterface.init();
        this.mMaxHeadsetConnections = this.mAdapterService.getMaxConnectedAudioDevices();
        HeadsetNativeInterface nativeInterface = HeadsetObjectsFactory.getInstance().getNativeInterface();
        this.mNativeInterface = nativeInterface;
        nativeInterface.init(this.mMaxHeadsetConnections + 1, isInbandRingingEnabled());
        if (this.mStateMachines.size() > 0) {
            throw new IllegalStateException("start(): mStateMachines is not empty, " + this.mStateMachines.size() + " is already created. Was stop() called properly?");
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.BATTERY_CHANGED");
        filter.addAction("android.media.VOLUME_CHANGED_ACTION");
        filter.addAction("android.bluetooth.device.action.CONNECTION_ACCESS_REPLY");
        filter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
        registerReceiver(this.mHeadsetReceiver, filter);
        setHeadsetService(this);
        this.mStarted = true;
        return true;
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected boolean stop() {
        Log.i(TAG, "stop()");
        if (!this.mStarted) {
            Log.w(TAG, "stop() called before start()");
            return true;
        }
        this.mStarted = false;
        setHeadsetService(null);
        unregisterReceiver(this.mHeadsetReceiver);
        synchronized (this.mStateMachines) {
            this.mActiveDevice = null;
            this.mInbandRingingRuntimeDisable = false;
            this.mForceScoAudio = false;
            this.mAudioRouteAllowed = true;
            this.mMaxHeadsetConnections = 1;
            this.mVoiceRecognitionStarted = false;
            this.mVirtualCallStarted = false;
            if (this.mDialingOutTimeoutEvent != null) {
                getStateMachinesThreadHandler().removeCallbacks(this.mDialingOutTimeoutEvent);
                this.mDialingOutTimeoutEvent = null;
            }
            if (this.mVoiceRecognitionTimeoutEvent != null) {
                getStateMachinesThreadHandler().removeCallbacks(this.mVoiceRecognitionTimeoutEvent);
                this.mVoiceRecognitionTimeoutEvent = null;
                if (this.mSystemInterface.getVoiceRecognitionWakeLock().isHeld()) {
                    this.mSystemInterface.getVoiceRecognitionWakeLock().release();
                }
            }
            for (HeadsetStateMachine stateMachine : this.mStateMachines.values()) {
                HeadsetObjectsFactory.getInstance().destroyStateMachine(stateMachine);
            }
            this.mStateMachines.clear();
        }
        this.mNativeInterface.cleanup();
        this.mSystemInterface.stop();
        this.mStateMachinesThread.quitSafely();
        this.mStateMachinesThread = null;
        this.mStateMachinesThreadHandler = null;
        synchronized (this.mStateMachines) {
            this.mAdapterService = null;
        }
        return true;
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected void cleanup() {
        Log.i(TAG, "cleanup");
        if (!this.mCreated) {
            Log.w(TAG, "cleanup() called before create()");
        }
        this.mCreated = false;
    }

    public boolean isAlive() {
        return isAvailable() && this.mCreated && this.mStarted;
    }

    public Looper getStateMachinesThreadLooper() {
        return this.mStateMachinesThread.getLooper();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean doForStateMachine(BluetoothDevice device, StateMachineTask task) {
        synchronized (this.mStateMachines) {
            HeadsetStateMachine stateMachine = this.mStateMachines.get(device);
            if (stateMachine == null) {
                return false;
            }
            task.execute(stateMachine);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doForEachConnectedStateMachine(StateMachineTask task) {
        synchronized (this.mStateMachines) {
            for (BluetoothDevice device : getConnectedDevices()) {
                task.execute(this.mStateMachines.get(device));
            }
        }
    }

    void onDeviceStateChanged(final HeadsetDeviceState deviceState) {
        doForEachConnectedStateMachine(new StateMachineTask() { // from class: com.android.bluetooth.hfp.-$$Lambda$HeadsetService$aK7IyaKUC7uqefRgWRqWtTOWFfE
            @Override // com.android.bluetooth.hfp.HeadsetService.StateMachineTask
            public final void execute(HeadsetStateMachine headsetStateMachine) {
                headsetStateMachine.sendMessage(10, deviceState);
            }
        });
    }

    void messageFromNative(HeadsetStackEvent stackEvent) {
        int i;
        Objects.requireNonNull(stackEvent.device, "Device should never be null, event: " + stackEvent);
        synchronized (this.mStateMachines) {
            HeadsetStateMachine stateMachine = this.mStateMachines.get(stackEvent.device);
            if (stackEvent.type == 1 && (((i = stackEvent.valueInt) == 1 || i == 2) && stateMachine == null)) {
                stateMachine = HeadsetObjectsFactory.getInstance().makeStateMachine(stackEvent.device, this.mStateMachinesThread.getLooper(), this, this.mAdapterService, this.mNativeInterface, this.mSystemInterface);
                this.mStateMachines.put(stackEvent.device, stateMachine);
            }
            if (stateMachine == null) {
                throw new IllegalStateException("State machine not found for stack event: " + stackEvent);
            }
            stateMachine.sendMessage(101, stackEvent);
        }
    }

    /* JADX INFO: renamed from: com.android.bluetooth.hfp.HeadsetService$1, reason: invalid class name */
    class AnonymousClass1 extends BroadcastReceiver {
        AnonymousClass1() {
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, final Intent intent) {
            byte b;
            String action = intent.getAction();
            if (action == null) {
                Log.w(HeadsetService.TAG, "mHeadsetReceiver, action is null");
                return;
            }
            switch (action.hashCode()) {
                case -1940635523:
                    b = !action.equals("android.media.VOLUME_CHANGED_ACTION") ? (byte) -1 : (byte) 1;
                    break;
                case -1538406691:
                    b = !action.equals("android.intent.action.BATTERY_CHANGED") ? (byte) -1 : (byte) 0;
                    break;
                case -725473775:
                    b = !action.equals("android.bluetooth.device.action.CONNECTION_ACCESS_REPLY") ? (byte) -1 : (byte) 2;
                    break;
                case 2116862345:
                    b = !action.equals("android.bluetooth.device.action.BOND_STATE_CHANGED") ? (byte) -1 : (byte) 3;
                    break;
                default:
                    b = -1;
                    break;
            }
            if (b == 0) {
                int batteryLevel = intent.getIntExtra("level", -1);
                int scale = intent.getIntExtra("scale", -1);
                if (batteryLevel < 0 || scale <= 0) {
                    Log.e(HeadsetService.TAG, "Bad Battery Changed intent: batteryLevel=" + batteryLevel + ", scale=" + scale);
                    return;
                }
                int cindBatteryLevel = Math.round((batteryLevel * 5) / scale);
                HeadsetService.this.mSystemInterface.getHeadsetPhoneState().setCindBatteryCharge(cindBatteryLevel);
                return;
            }
            if (b == 1) {
                int streamType = intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_TYPE", -1);
                if (streamType == 6) {
                    HeadsetService.this.doForEachConnectedStateMachine(new StateMachineTask() { // from class: com.android.bluetooth.hfp.-$$Lambda$HeadsetService$1$eJt5KOpRmibPD3Tpb-ARPmHl8aw
                        @Override // com.android.bluetooth.hfp.HeadsetService.StateMachineTask
                        public final void execute(HeadsetStateMachine headsetStateMachine) {
                            headsetStateMachine.sendMessage(7, intent);
                        }
                    });
                    return;
                }
                return;
            }
            if (b == 2) {
                int requestType = intent.getIntExtra("android.bluetooth.device.extra.ACCESS_REQUEST_TYPE", 2);
                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                HeadsetService.logD("Received BluetoothDevice.ACTION_CONNECTION_ACCESS_REPLY, device=" + device + ", type=" + requestType);
                if (requestType == 2) {
                    synchronized (HeadsetService.this.mStateMachines) {
                        HeadsetStateMachine stateMachine = (HeadsetStateMachine) HeadsetService.this.mStateMachines.get(device);
                        if (stateMachine == null) {
                            Log.wtf(HeadsetService.TAG, "Cannot find state machine for " + device);
                            return;
                        }
                        stateMachine.sendMessage(8, intent);
                        return;
                    }
                }
                return;
            }
            if (b == 3) {
                int state = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", Integer.MIN_VALUE);
                BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                Objects.requireNonNull(bluetoothDevice, "ACTION_BOND_STATE_CHANGED with no EXTRA_DEVICE");
                BluetoothDevice device2 = bluetoothDevice;
                HeadsetService.logD("Bond state changed for device: " + device2 + " state: " + state);
                if (state == 10) {
                    synchronized (HeadsetService.this.mStateMachines) {
                        HeadsetStateMachine stateMachine2 = (HeadsetStateMachine) HeadsetService.this.mStateMachines.get(device2);
                        if (stateMachine2 != null) {
                            if (stateMachine2.getConnectionState() == 0) {
                                HeadsetService.this.removeStateMachine(device2);
                            }
                        }
                    }
                    return;
                }
                return;
            }
            Log.w(HeadsetService.TAG, "Unknown action " + action);
        }
    }

    private static class BluetoothHeadsetBinder extends IBluetoothHeadset.Stub implements ProfileService.IProfileServiceBinder {
        private volatile HeadsetService mService;

        BluetoothHeadsetBinder(HeadsetService svc) {
            this.mService = svc;
        }

        @Override // com.android.bluetooth.btservice.ProfileService.IProfileServiceBinder
        public void cleanup() {
            this.mService = null;
        }

        private HeadsetService getService() {
            HeadsetService service = this.mService;
            if (!Utils.checkCallerAllowManagedProfiles(service)) {
                Log.w(HeadsetService.TAG, "Headset call not allowed for non-active user");
                return null;
            }
            if (service == null) {
                Log.w(HeadsetService.TAG, "Service is null");
                return null;
            }
            if (!service.isAlive()) {
                Log.w(HeadsetService.TAG, "Service is not alive");
                return null;
            }
            return service;
        }

        public boolean connect(BluetoothDevice device) {
            HeadsetService service = getService();
            if (service == null) {
                return false;
            }
            return service.connect(device);
        }

        public boolean disconnect(BluetoothDevice device) {
            HeadsetService service = getService();
            if (service == null) {
                return false;
            }
            return service.disconnect(device);
        }

        public List<BluetoothDevice> getConnectedDevices() {
            HeadsetService service = getService();
            if (service == null) {
                return new ArrayList(0);
            }
            return service.getConnectedDevices();
        }

        public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states) {
            HeadsetService service = getService();
            if (service == null) {
                return new ArrayList(0);
            }
            return service.getDevicesMatchingConnectionStates(states);
        }

        public int getConnectionState(BluetoothDevice device) {
            HeadsetService service = getService();
            if (service == null) {
                return 0;
            }
            return service.getConnectionState(device);
        }

        public boolean setPriority(BluetoothDevice device, int connectionPolicy) {
            HeadsetService service = getService();
            if (service == null) {
                return false;
            }
            Utils.enforceBluetoothAdminPermission(service);
            return service.setConnectionPolicy(device, connectionPolicy);
        }

        public boolean setConnectionPolicy(BluetoothDevice device, int connectionPolicy) {
            HeadsetService service = getService();
            if (service == null) {
                return false;
            }
            Utils.enforceBluetoothPrivilegedPermission(service);
            return service.setConnectionPolicy(device, connectionPolicy);
        }

        public int getPriority(BluetoothDevice device) {
            HeadsetService service = getService();
            if (service == null) {
                return -1;
            }
            Utils.enforceBluetoothPermission(service);
            return service.getConnectionPolicy(device);
        }

        public int getConnectionPolicy(BluetoothDevice device) {
            HeadsetService service = getService();
            if (service == null) {
                return -1;
            }
            Utils.enforceBluetoothPrivilegedPermission(service);
            return service.getConnectionPolicy(device);
        }

        public boolean startVoiceRecognition(BluetoothDevice device) {
            HeadsetService service = getService();
            if (service == null) {
                return false;
            }
            return service.startVoiceRecognition(device);
        }

        public boolean stopVoiceRecognition(BluetoothDevice device) {
            HeadsetService service = getService();
            if (service == null) {
                return false;
            }
            return service.stopVoiceRecognition(device);
        }

        public boolean isAudioOn() {
            HeadsetService service = getService();
            if (service == null) {
                return false;
            }
            return service.isAudioOn();
        }

        public boolean isAudioConnected(BluetoothDevice device) {
            HeadsetService service = getService();
            if (service == null) {
                return false;
            }
            return service.isAudioConnected(device);
        }

        public int getAudioState(BluetoothDevice device) {
            HeadsetService service = getService();
            if (service == null) {
                return 10;
            }
            return service.getAudioState(device);
        }

        public boolean connectAudio() {
            HeadsetService service = getService();
            if (service == null) {
                return false;
            }
            return service.connectAudio();
        }

        public boolean disconnectAudio() {
            HeadsetService service = getService();
            if (service == null) {
                return false;
            }
            return service.disconnectAudio();
        }

        public void setAudioRouteAllowed(boolean allowed) {
            HeadsetService service = getService();
            if (service == null) {
                return;
            }
            service.setAudioRouteAllowed(allowed);
        }

        public boolean getAudioRouteAllowed() {
            HeadsetService service = getService();
            if (service != null) {
                return service.getAudioRouteAllowed();
            }
            return false;
        }

        public void setForceScoAudio(boolean forced) {
            HeadsetService service = getService();
            if (service == null) {
                return;
            }
            service.setForceScoAudio(forced);
        }

        public boolean startScoUsingVirtualVoiceCall() {
            HeadsetService service = getService();
            if (service != null) {
                return service.startScoUsingVirtualVoiceCall();
            }
            return false;
        }

        public boolean stopScoUsingVirtualVoiceCall() {
            HeadsetService service = getService();
            if (service == null) {
                return false;
            }
            return service.stopScoUsingVirtualVoiceCall();
        }

        public void phoneStateChanged(int numActive, int numHeld, int callState, String number, int type, String name) {
            HeadsetService service = getService();
            if (service != null) {
                service.phoneStateChanged(numActive, numHeld, callState, number, type, name, false);
            }
        }

        public void clccResponse(int index, int direction, int status, int mode, boolean mpty, String number, int type) {
            HeadsetService service = getService();
            if (service != null) {
                service.clccResponse(index, direction, status, mode, mpty, number, type);
            }
        }

        public boolean sendVendorSpecificResultCode(BluetoothDevice device, String command, String arg) {
            HeadsetService service = getService();
            if (service != null) {
                return service.sendVendorSpecificResultCode(device, command, arg);
            }
            return false;
        }

        public boolean setActiveDevice(BluetoothDevice device) {
            HeadsetService service = getService();
            if (service == null) {
                return false;
            }
            return service.setActiveDevice(device);
        }

        public BluetoothDevice getActiveDevice() {
            HeadsetService service = getService();
            if (service == null) {
                return null;
            }
            return service.getActiveDevice();
        }

        public boolean isInbandRingingEnabled() {
            HeadsetService service = getService();
            if (service == null) {
                return false;
            }
            return service.isInbandRingingEnabled();
        }
    }

    public static synchronized HeadsetService getHeadsetService() {
        if (sHeadsetService == null) {
            Log.w(TAG, "getHeadsetService(): service is NULL");
            return null;
        }
        if (!sHeadsetService.isAvailable()) {
            Log.w(TAG, "getHeadsetService(): service is not available");
            return null;
        }
        logD("getHeadsetService(): returning " + sHeadsetService);
        return sHeadsetService;
    }

    private static synchronized void setHeadsetService(HeadsetService instance) {
        logD("setHeadsetService(): set to: " + instance);
        sHeadsetService = instance;
    }

    public boolean connect(BluetoothDevice device) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN", "Need BLUETOOTH ADMIN permission");
        if (getConnectionPolicy(device) == 0) {
            Log.w(TAG, "connect: CONNECTION_POLICY_FORBIDDEN, device=" + device + ", " + Utils.getUidPidString());
            return false;
        }
        ParcelUuid[] featureUuids = this.mAdapterService.getRemoteUuids(device);
        if (!BluetoothUuid.containsAnyUuid(featureUuids, HEADSET_UUIDS)) {
            Log.e(TAG, "connect: Cannot connect to " + device + ": no headset UUID, " + Utils.getUidPidString());
            return false;
        }
        synchronized (this.mStateMachines) {
            Log.i(TAG, "connect: device=" + device + ", " + Utils.getUidPidString());
            HeadsetStateMachine stateMachine = this.mStateMachines.get(device);
            if (stateMachine == null) {
                stateMachine = HeadsetObjectsFactory.getInstance().makeStateMachine(device, this.mStateMachinesThread.getLooper(), this, this.mAdapterService, this.mNativeInterface, this.mSystemInterface);
                this.mStateMachines.put(device, stateMachine);
            }
            int connectionState = stateMachine.getConnectionState();
            if (connectionState != 2 && connectionState != 1) {
                List<BluetoothDevice> connectingConnectedDevices = getDevicesMatchingConnectionStates(CONNECTING_CONNECTED_STATES);
                boolean disconnectExisting = false;
                if (connectingConnectedDevices.size() >= this.mMaxHeadsetConnections) {
                    if (this.mMaxHeadsetConnections == 1) {
                        disconnectExisting = true;
                    } else {
                        Log.w(TAG, "Max connection has reached, rejecting connection to " + device);
                        return false;
                    }
                }
                if (disconnectExisting) {
                    for (BluetoothDevice connectingConnectedDevice : connectingConnectedDevices) {
                        disconnect(connectingConnectedDevice);
                    }
                    setActiveDevice(null);
                }
                stateMachine.sendMessage(1, device);
                return true;
            }
            Log.w(TAG, "connect: device " + device + " is already connected/connecting, connectionState=" + connectionState);
            return false;
        }
    }

    public boolean disconnect(BluetoothDevice device) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN", "Need BLUETOOTH_ADMIN permission");
        Log.i(TAG, "disconnect: device=" + device + ", " + Utils.getUidPidString());
        synchronized (this.mStateMachines) {
            HeadsetStateMachine stateMachine = this.mStateMachines.get(device);
            if (stateMachine == null) {
                Log.w(TAG, "disconnect: device " + device + " not ever connected/connecting");
                return false;
            }
            int connectionState = stateMachine.getConnectionState();
            if (connectionState != 2 && connectionState != 1) {
                Log.w(TAG, "disconnect: device " + device + " not connected/connecting, connectionState=" + connectionState);
                return false;
            }
            stateMachine.sendMessage(2, device);
            return true;
        }
    }

    public List<BluetoothDevice> getConnectedDevices() {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        ArrayList<BluetoothDevice> devices = new ArrayList<>();
        synchronized (this.mStateMachines) {
            for (HeadsetStateMachine stateMachine : this.mStateMachines.values()) {
                if (stateMachine.getConnectionState() == 2) {
                    devices.add(stateMachine.getDevice());
                }
            }
        }
        return devices;
    }

    public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        ArrayList<BluetoothDevice> devices = new ArrayList<>();
        synchronized (this.mStateMachines) {
            if (states != null) {
                if (this.mAdapterService != null) {
                    BluetoothDevice[] bondedDevices = this.mAdapterService.getBondedDevices();
                    if (bondedDevices == null) {
                        return devices;
                    }
                    for (BluetoothDevice device : bondedDevices) {
                        ParcelUuid[] featureUuids = this.mAdapterService.getRemoteUuids(device);
                        if (BluetoothUuid.containsAnyUuid(featureUuids, HEADSET_UUIDS)) {
                            int connectionState = getConnectionState(device);
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
                    return devices;
                }
            }
            return devices;
        }
    }

    public int getConnectionState(BluetoothDevice device) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        synchronized (this.mStateMachines) {
            HeadsetStateMachine stateMachine = this.mStateMachines.get(device);
            if (stateMachine == null) {
                return 0;
            }
            return stateMachine.getConnectionState();
        }
    }

    public boolean setConnectionPolicy(BluetoothDevice device, int connectionPolicy) {
        Log.i(TAG, "setConnectionPolicy: device=" + device + ", connectionPolicy=" + connectionPolicy + ", " + Utils.getUidPidString());
        this.mAdapterService.getDatabase().setProfileConnectionPolicy(device, 1, connectionPolicy);
        if (connectionPolicy == 100) {
            connect(device);
        } else if (connectionPolicy == 0) {
            disconnect(device);
        }
        return true;
    }

    public int getConnectionPolicy(BluetoothDevice device) {
        return this.mAdapterService.getDatabase().getProfileConnectionPolicy(device, 1);
    }

    boolean startVoiceRecognition(BluetoothDevice device) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        Log.i(TAG, "startVoiceRecognition: device=" + device + ", " + Utils.getUidPidString());
        synchronized (this.mStateMachines) {
            if (this.mVoiceRecognitionStarted) {
                boolean status = stopVoiceRecognition(this.mActiveDevice);
                Log.w(TAG, "startVoiceRecognition: voice recognition is still active, just called stopVoiceRecognition, returned " + status + " on " + this.mActiveDevice + ", please try again");
                this.mVoiceRecognitionStarted = false;
                return false;
            }
            boolean status2 = isAudioModeIdle();
            if (!status2) {
                Log.w(TAG, "startVoiceRecognition: audio mode not idle, active device is " + this.mActiveDevice);
                return false;
            }
            if (isAudioOn()) {
                boolean status3 = disconnectAudio();
                Log.w(TAG, "startVoiceRecognition: audio is still active, please wait for audio to be disconnected, disconnectAudio() returned " + status3 + ", active device is " + this.mActiveDevice);
                return false;
            }
            if (device == null) {
                Log.i(TAG, "device is null, use active device " + this.mActiveDevice + " instead");
                device = this.mActiveDevice;
            }
            boolean pendingRequestByHeadset = false;
            if (this.mVoiceRecognitionTimeoutEvent != null) {
                if (!this.mVoiceRecognitionTimeoutEvent.mVoiceRecognitionDevice.equals(device)) {
                    Log.w(TAG, "startVoiceRecognition: device " + device + " is not the same as requesting device " + this.mVoiceRecognitionTimeoutEvent.mVoiceRecognitionDevice + ", fall back to requesting device");
                    device = this.mVoiceRecognitionTimeoutEvent.mVoiceRecognitionDevice;
                }
                getStateMachinesThreadHandler().removeCallbacks(this.mVoiceRecognitionTimeoutEvent);
                this.mVoiceRecognitionTimeoutEvent = null;
                if (this.mSystemInterface.getVoiceRecognitionWakeLock().isHeld()) {
                    this.mSystemInterface.getVoiceRecognitionWakeLock().release();
                }
                pendingRequestByHeadset = true;
            }
            if (!Objects.equals(device, this.mActiveDevice) && !setActiveDevice(device)) {
                Log.w(TAG, "startVoiceRecognition: failed to set " + device + " as active");
                return false;
            }
            HeadsetStateMachine stateMachine = this.mStateMachines.get(device);
            if (stateMachine == null) {
                Log.w(TAG, "startVoiceRecognition: " + device + " is never connected");
                return false;
            }
            int connectionState = stateMachine.getConnectionState();
            if (connectionState != 2 && connectionState != 1) {
                Log.w(TAG, "startVoiceRecognition: " + device + " is not connected or connecting");
                return false;
            }
            this.mVoiceRecognitionStarted = true;
            if (pendingRequestByHeadset) {
                stateMachine.sendMessage(15, 1, 0, device);
            } else {
                stateMachine.sendMessage(5, device);
            }
            stateMachine.sendMessage(3, device);
            return true;
        }
    }

    boolean stopVoiceRecognition(BluetoothDevice device) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        Log.i(TAG, "stopVoiceRecognition: device=" + device + ", " + Utils.getUidPidString());
        synchronized (this.mStateMachines) {
            if (!Objects.equals(this.mActiveDevice, device)) {
                Log.w(TAG, "startVoiceRecognition: requested device " + device + " is not active, use active device " + this.mActiveDevice + " instead");
                device = this.mActiveDevice;
            }
            HeadsetStateMachine stateMachine = this.mStateMachines.get(device);
            if (stateMachine == null) {
                Log.w(TAG, "stopVoiceRecognition: " + device + " is never connected");
                return false;
            }
            int connectionState = stateMachine.getConnectionState();
            if (connectionState != 2 && connectionState != 1) {
                Log.w(TAG, "stopVoiceRecognition: " + device + " is not connected or connecting");
                return false;
            }
            if (!this.mVoiceRecognitionStarted) {
                Log.w(TAG, "stopVoiceRecognition: voice recognition was not started");
                return false;
            }
            this.mVoiceRecognitionStarted = false;
            stateMachine.sendMessage(6, device);
            stateMachine.sendMessage(4, device);
            return true;
        }
    }

    boolean isAudioOn() {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        return getNonIdleAudioDevices().size() > 0;
    }

    boolean isAudioConnected(BluetoothDevice device) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        synchronized (this.mStateMachines) {
            HeadsetStateMachine stateMachine = this.mStateMachines.get(device);
            if (stateMachine == null) {
                return false;
            }
            return stateMachine.getAudioState() == 12;
        }
    }

    int getAudioState(BluetoothDevice device) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        synchronized (this.mStateMachines) {
            HeadsetStateMachine stateMachine = this.mStateMachines.get(device);
            if (stateMachine == null) {
                return 10;
            }
            return stateMachine.getAudioState();
        }
    }

    public void setAudioRouteAllowed(boolean allowed) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN", "Need BLUETOOTH_ADMIN permission");
        Log.i(TAG, "setAudioRouteAllowed: allowed=" + allowed + ", " + Utils.getUidPidString());
        this.mAudioRouteAllowed = allowed;
        this.mNativeInterface.setScoAllowed(allowed);
    }

    public boolean getAudioRouteAllowed() {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        return this.mAudioRouteAllowed;
    }

    public void setForceScoAudio(boolean forced) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN", "Need BLUETOOTH_ADMIN permission");
        Log.i(TAG, "setForceScoAudio: forced=" + forced + ", " + Utils.getUidPidString());
        this.mForceScoAudio = forced;
    }

    public boolean getForceScoAudio() {
        return this.mForceScoAudio;
    }

    public BluetoothDevice getFirstConnectedAudioDevice() {
        ArrayList<HeadsetStateMachine> stateMachines = new ArrayList<>();
        synchronized (this.mStateMachines) {
            List<BluetoothDevice> availableDevices = getDevicesMatchingConnectionStates(CONNECTING_CONNECTED_STATES);
            for (BluetoothDevice device : availableDevices) {
                HeadsetStateMachine stateMachine = this.mStateMachines.get(device);
                if (stateMachine != null) {
                    stateMachines.add(stateMachine);
                }
            }
        }
        stateMachines.sort(Comparator.comparingLong(new ToLongFunction() { // from class: com.android.bluetooth.hfp.-$$Lambda$zZhcclBCEMTE1UUJjcoEJjXmX_8
            @Override // java.util.function.ToLongFunction
            public final long applyAsLong(Object obj) {
                return ((HeadsetStateMachine) obj).getConnectingTimestampMs();
            }
        }));
        if (stateMachines.size() > 0) {
            return stateMachines.get(0).getDevice();
        }
        return null;
    }

    public boolean setSilenceMode(BluetoothDevice device, boolean silence) {
        Log.d(TAG, "setSilenceMode(" + device + "): " + silence);
        if (silence && Objects.equals(this.mActiveDevice, device)) {
            setActiveDevice(null);
        } else if (!silence && this.mActiveDevice == null) {
            setActiveDevice(device);
        }
        synchronized (this.mStateMachines) {
            HeadsetStateMachine stateMachine = this.mStateMachines.get(device);
            if (stateMachine == null) {
                Log.w(TAG, "setSilenceMode: device " + device + " was never connected/connecting");
                return false;
            }
            stateMachine.setSilenceDevice(silence);
            return true;
        }
    }

    public boolean setActiveDevice(BluetoothDevice device) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN", "Need BLUETOOTH_ADMIN permission");
        Log.i(TAG, "setActiveDevice: device=" + device + ", " + Utils.getUidPidString());
        synchronized (this.mStateMachines) {
            if (device == null) {
                if (this.mVoiceRecognitionStarted && !stopVoiceRecognition(this.mActiveDevice)) {
                    Log.w(TAG, "setActiveDevice: fail to stopVoiceRecognition from " + this.mActiveDevice);
                }
                if (this.mVirtualCallStarted && !stopScoUsingVirtualVoiceCall()) {
                    Log.w(TAG, "setActiveDevice: fail to stopScoUsingVirtualVoiceCall from " + this.mActiveDevice);
                }
                if (getAudioState(this.mActiveDevice) != 10 && !disconnectAudio(this.mActiveDevice)) {
                    Log.w(TAG, "setActiveDevice: disconnectAudio failed on " + this.mActiveDevice);
                }
                this.mActiveDevice = null;
                broadcastActiveDevice(null);
                return true;
            }
            if (device.equals(this.mActiveDevice)) {
                Log.i(TAG, "setActiveDevice: device " + device + " is already active");
                return true;
            }
            if (getConnectionState(device) != 2) {
                Log.e(TAG, "setActiveDevice: Cannot set " + device + " as active, device is not connected");
                return false;
            }
            if (!this.mNativeInterface.setActiveDevice(device)) {
                Log.e(TAG, "setActiveDevice: Cannot set " + device + " as active in native layer");
                return false;
            }
            BluetoothDevice previousActiveDevice = this.mActiveDevice;
            this.mActiveDevice = device;
            if (getAudioState(previousActiveDevice) != 10) {
                if (!disconnectAudio(previousActiveDevice)) {
                    Log.e(TAG, "setActiveDevice: fail to disconnectAudio from " + previousActiveDevice);
                    this.mActiveDevice = previousActiveDevice;
                    this.mNativeInterface.setActiveDevice(previousActiveDevice);
                    return false;
                }
                broadcastActiveDevice(this.mActiveDevice);
            } else if (shouldPersistAudio()) {
                broadcastActiveDevice(this.mActiveDevice);
                if (!connectAudio(this.mActiveDevice)) {
                    Log.e(TAG, "setActiveDevice: fail to connectAudio to " + this.mActiveDevice);
                    this.mActiveDevice = previousActiveDevice;
                    this.mNativeInterface.setActiveDevice(previousActiveDevice);
                    return false;
                }
            } else {
                broadcastActiveDevice(this.mActiveDevice);
            }
            return true;
        }
    }

    public BluetoothDevice getActiveDevice() {
        BluetoothDevice bluetoothDevice;
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        synchronized (this.mStateMachines) {
            bluetoothDevice = this.mActiveDevice;
        }
        return bluetoothDevice;
    }

    boolean connectAudio() {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN", "Need BLUETOOTH_ADMIN permission");
        synchronized (this.mStateMachines) {
            BluetoothDevice device = this.mActiveDevice;
            if (device == null) {
                Log.w(TAG, "connectAudio: no active device, " + Utils.getUidPidString());
                return false;
            }
            return connectAudio(device);
        }
    }

    boolean connectAudio(BluetoothDevice device) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN", "Need BLUETOOTH_ADMIN permission");
        Log.i(TAG, "connectAudio: device=" + device + ", " + Utils.getUidPidString());
        synchronized (this.mStateMachines) {
            if (!isScoAcceptable(device)) {
                Log.w(TAG, "connectAudio, rejected SCO request to " + device);
                return false;
            }
            HeadsetStateMachine stateMachine = this.mStateMachines.get(device);
            if (stateMachine == null) {
                Log.w(TAG, "connectAudio: device " + device + " was never connected/connecting");
                return false;
            }
            if (stateMachine.getConnectionState() != 2) {
                Log.w(TAG, "connectAudio: profile not connected");
                return false;
            }
            if (stateMachine.getAudioState() != 10) {
                logD("connectAudio: audio is not idle for device " + device);
                return true;
            }
            if (isAudioOn()) {
                Log.w(TAG, "connectAudio: audio is not idle, current audio devices are " + Arrays.toString(getNonIdleAudioDevices().toArray()));
                return false;
            }
            stateMachine.sendMessage(3, device);
            return true;
        }
    }

    private List<BluetoothDevice> getNonIdleAudioDevices() {
        ArrayList<BluetoothDevice> devices = new ArrayList<>();
        synchronized (this.mStateMachines) {
            for (HeadsetStateMachine stateMachine : this.mStateMachines.values()) {
                if (stateMachine.getAudioState() != 10) {
                    devices.add(stateMachine.getDevice());
                }
            }
        }
        return devices;
    }

    boolean disconnectAudio() {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN", "Need BLUETOOTH_ADMIN permission");
        boolean result = false;
        synchronized (this.mStateMachines) {
            for (BluetoothDevice device : getNonIdleAudioDevices()) {
                if (disconnectAudio(device)) {
                    result = true;
                } else {
                    Log.e(TAG, "disconnectAudio() from " + device + " failed");
                }
            }
        }
        if (!result) {
            logD("disconnectAudio() no active audio connection");
        }
        return result;
    }

    boolean disconnectAudio(BluetoothDevice device) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN", "Need BLUETOOTH_ADMIN permission");
        synchronized (this.mStateMachines) {
            Log.i(TAG, "disconnectAudio: device=" + device + ", " + Utils.getUidPidString());
            HeadsetStateMachine stateMachine = this.mStateMachines.get(device);
            if (stateMachine == null) {
                Log.w(TAG, "disconnectAudio: device " + device + " was never connected/connecting");
                return false;
            }
            if (stateMachine.getAudioState() == 10) {
                Log.w(TAG, "disconnectAudio, audio is already disconnected for " + device);
                return false;
            }
            stateMachine.sendMessage(4, device);
            return true;
        }
    }

    boolean isVirtualCallStarted() {
        boolean z;
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        synchronized (this.mStateMachines) {
            z = this.mVirtualCallStarted;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean startScoUsingVirtualVoiceCall() {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN", "Need BLUETOOTH_ADMIN permission");
        Log.i(TAG, "startScoUsingVirtualVoiceCall: " + Utils.getUidPidString());
        synchronized (this.mStateMachines) {
            if (this.mVoiceRecognitionStarted) {
                boolean status = stopVoiceRecognition(this.mActiveDevice);
                Log.w(TAG, "startScoUsingVirtualVoiceCall: voice recognition is still active, just called stopVoiceRecognition, returned " + status + " on " + this.mActiveDevice + ", please try again");
                this.mVoiceRecognitionStarted = false;
                return false;
            }
            boolean status2 = isAudioModeIdle();
            if (!status2) {
                Log.w(TAG, "startScoUsingVirtualVoiceCall: audio mode not idle, active device is " + this.mActiveDevice);
                return false;
            }
            if (isAudioOn()) {
                boolean status3 = disconnectAudio();
                Log.w(TAG, "startScoUsingVirtualVoiceCall: audio is still active, please wait for audio to be disconnected, disconnectAudio() returned " + status3 + ", active device is " + this.mActiveDevice);
                return false;
            }
            if (this.mActiveDevice == null) {
                Log.w(TAG, "startScoUsingVirtualVoiceCall: no active device");
                return false;
            }
            this.mVirtualCallStarted = true;
            phoneStateChanged(0, 0, 2, INetd.NEXTHOP_NONE, 0, INetd.NEXTHOP_NONE, true);
            phoneStateChanged(0, 0, 3, INetd.NEXTHOP_NONE, 0, INetd.NEXTHOP_NONE, true);
            phoneStateChanged(1, 0, 6, INetd.NEXTHOP_NONE, 0, INetd.NEXTHOP_NONE, true);
            return true;
        }
    }

    boolean stopScoUsingVirtualVoiceCall() {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN", "Need BLUETOOTH_ADMIN permission");
        Log.i(TAG, "stopScoUsingVirtualVoiceCall: " + Utils.getUidPidString());
        synchronized (this.mStateMachines) {
            if (!this.mVirtualCallStarted) {
                Log.w(TAG, "stopScoUsingVirtualVoiceCall: virtual call not started");
                return false;
            }
            this.mVirtualCallStarted = false;
            phoneStateChanged(0, 0, 6, INetd.NEXTHOP_NONE, 0, INetd.NEXTHOP_NONE, true);
            return true;
        }
    }

    class DialingOutTimeoutEvent implements Runnable {
        BluetoothDevice mDialingOutDevice;

        DialingOutTimeoutEvent(BluetoothDevice fromDevice) {
            this.mDialingOutDevice = fromDevice;
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (HeadsetService.this.mStateMachines) {
                HeadsetService.this.mDialingOutTimeoutEvent = null;
                HeadsetService.this.doForStateMachine(this.mDialingOutDevice, new StateMachineTask() { // from class: com.android.bluetooth.hfp.-$$Lambda$HeadsetService$DialingOutTimeoutEvent$DfDBG7rWmVZbY_YyILx3boiOIbM
                    @Override // com.android.bluetooth.hfp.HeadsetService.StateMachineTask
                    public final void execute(HeadsetStateMachine headsetStateMachine) {
                        this.f$0.lambda$run$0$HeadsetService$DialingOutTimeoutEvent(headsetStateMachine);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$run$0$HeadsetService$DialingOutTimeoutEvent(HeadsetStateMachine stateMachine) {
            stateMachine.sendMessage(14, 0, 0, this.mDialingOutDevice);
        }

        public String toString() {
            return "DialingOutTimeoutEvent[" + this.mDialingOutDevice + "]";
        }
    }

    public boolean dialOutgoingCall(BluetoothDevice fromDevice, String dialNumber) {
        synchronized (this.mStateMachines) {
            Log.i(TAG, "dialOutgoingCall: from " + fromDevice);
            if (!isOnStateMachineThread()) {
                Log.e(TAG, "dialOutgoingCall must be called from state machine thread");
                return false;
            }
            if (this.mDialingOutTimeoutEvent != null) {
                Log.e(TAG, "dialOutgoingCall, already dialing by " + this.mDialingOutTimeoutEvent);
                return false;
            }
            if (isVirtualCallStarted() && !stopScoUsingVirtualVoiceCall()) {
                Log.e(TAG, "dialOutgoingCall failed to stop current virtual call");
                return false;
            }
            if (!setActiveDevice(fromDevice)) {
                Log.e(TAG, "dialOutgoingCall failed to set active device to " + fromDevice);
                return false;
            }
            Intent intent = new Intent("android.intent.action.CALL_PRIVILEGED", Uri.fromParts("tel", dialNumber, null));
            intent.setFlags(VCardConfig.FLAG_REFRAIN_QP_TO_NAME_PROPERTIES);
            startActivity(intent);
            this.mDialingOutTimeoutEvent = new DialingOutTimeoutEvent(fromDevice);
            getStateMachinesThreadHandler().postDelayed(this.mDialingOutTimeoutEvent, 10000L);
            return true;
        }
    }

    public boolean hasDeviceInitiatedDialingOut() {
        boolean z;
        synchronized (this.mStateMachines) {
            z = this.mDialingOutTimeoutEvent != null;
        }
        return z;
    }

    class VoiceRecognitionTimeoutEvent implements Runnable {
        BluetoothDevice mVoiceRecognitionDevice;

        VoiceRecognitionTimeoutEvent(BluetoothDevice device) {
            this.mVoiceRecognitionDevice = device;
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (HeadsetService.this.mStateMachines) {
                if (HeadsetService.this.mSystemInterface.getVoiceRecognitionWakeLock().isHeld()) {
                    HeadsetService.this.mSystemInterface.getVoiceRecognitionWakeLock().release();
                }
                HeadsetService.this.mVoiceRecognitionTimeoutEvent = null;
                HeadsetService.this.doForStateMachine(this.mVoiceRecognitionDevice, new StateMachineTask() { // from class: com.android.bluetooth.hfp.-$$Lambda$HeadsetService$VoiceRecognitionTimeoutEvent$EV67KkqTR78Q6tNuwx9Q2WKkskk
                    @Override // com.android.bluetooth.hfp.HeadsetService.StateMachineTask
                    public final void execute(HeadsetStateMachine headsetStateMachine) {
                        this.f$0.lambda$run$0$HeadsetService$VoiceRecognitionTimeoutEvent(headsetStateMachine);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$run$0$HeadsetService$VoiceRecognitionTimeoutEvent(HeadsetStateMachine stateMachine) {
            stateMachine.sendMessage(15, 0, 0, this.mVoiceRecognitionDevice);
        }

        public String toString() {
            return "VoiceRecognitionTimeoutEvent[" + this.mVoiceRecognitionDevice + "]";
        }
    }

    boolean startVoiceRecognitionByHeadset(BluetoothDevice fromDevice) {
        synchronized (this.mStateMachines) {
            Log.i(TAG, "startVoiceRecognitionByHeadset: from " + fromDevice);
            if (this.mVoiceRecognitionStarted) {
                boolean status = stopVoiceRecognition(this.mActiveDevice);
                Log.w(TAG, "startVoiceRecognitionByHeadset: voice recognition is still active, just called stopVoiceRecognition, returned " + status + " on " + this.mActiveDevice + ", please try again");
                this.mVoiceRecognitionStarted = false;
                return false;
            }
            if (fromDevice == null) {
                Log.e(TAG, "startVoiceRecognitionByHeadset: fromDevice is null");
                return false;
            }
            if (!isAudioModeIdle()) {
                Log.w(TAG, "startVoiceRecognitionByHeadset: audio mode not idle, active device is " + this.mActiveDevice);
                return false;
            }
            if (isAudioOn()) {
                boolean status2 = disconnectAudio();
                Log.w(TAG, "startVoiceRecognitionByHeadset: audio is still active, please wait for audio to be disconnected, disconnectAudio() returned " + status2 + ", active device is " + this.mActiveDevice);
                return false;
            }
            if (this.mVoiceRecognitionTimeoutEvent != null) {
                Log.w(TAG, "startVoiceRecognitionByHeadset: failed request from " + fromDevice + ", already pending by " + this.mVoiceRecognitionTimeoutEvent);
                return false;
            }
            if (!setActiveDevice(fromDevice)) {
                Log.w(TAG, "startVoiceRecognitionByHeadset: failed to set " + fromDevice + " as active");
                return false;
            }
            if (!this.mSystemInterface.activateVoiceRecognition()) {
                Log.w(TAG, "startVoiceRecognitionByHeadset: failed request from " + fromDevice);
                return false;
            }
            this.mVoiceRecognitionTimeoutEvent = new VoiceRecognitionTimeoutEvent(fromDevice);
            getStateMachinesThreadHandler().postDelayed(this.mVoiceRecognitionTimeoutEvent, sStartVrTimeoutMs);
            if (!this.mSystemInterface.getVoiceRecognitionWakeLock().isHeld()) {
                this.mSystemInterface.getVoiceRecognitionWakeLock().acquire(sStartVrTimeoutMs);
            }
            return true;
        }
    }

    boolean stopVoiceRecognitionByHeadset(BluetoothDevice fromDevice) {
        synchronized (this.mStateMachines) {
            Log.i(TAG, "stopVoiceRecognitionByHeadset: from " + fromDevice);
            if (!Objects.equals(fromDevice, this.mActiveDevice)) {
                Log.w(TAG, "stopVoiceRecognitionByHeadset: " + fromDevice + " is not active, active device is " + this.mActiveDevice);
                return false;
            }
            if (!this.mVoiceRecognitionStarted && this.mVoiceRecognitionTimeoutEvent == null) {
                Log.w(TAG, "stopVoiceRecognitionByHeadset: voice recognition not started, device=" + fromDevice);
                return false;
            }
            if (this.mVoiceRecognitionTimeoutEvent != null) {
                if (this.mSystemInterface.getVoiceRecognitionWakeLock().isHeld()) {
                    this.mSystemInterface.getVoiceRecognitionWakeLock().release();
                }
                getStateMachinesThreadHandler().removeCallbacks(this.mVoiceRecognitionTimeoutEvent);
                this.mVoiceRecognitionTimeoutEvent = null;
            }
            if (this.mVoiceRecognitionStarted) {
                if (!disconnectAudio()) {
                    Log.w(TAG, "stopVoiceRecognitionByHeadset: failed to disconnect audio from " + fromDevice);
                }
                this.mVoiceRecognitionStarted = false;
            }
            if (!this.mSystemInterface.deactivateVoiceRecognition()) {
                Log.w(TAG, "stopVoiceRecognitionByHeadset: failed request from " + fromDevice);
                return false;
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:16:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void phoneStateChanged(final int r13, final int r14, final int r15, final java.lang.String r16, final int r17, final java.lang.String r18, boolean r19) {
        /*
            r12 = this;
            r1 = r12
            r9 = r13
            r10 = r14
            r11 = r15
            java.lang.String r0 = "android.permission.MODIFY_PHONE_STATE"
            java.lang.String r2 = "Need MODIFY_PHONE_STATE permission"
            r12.enforceCallingOrSelfPermission(r0, r2)
            java.util.HashMap<android.bluetooth.BluetoothDevice, com.android.bluetooth.hfp.HeadsetStateMachine> r2 = r1.mStateMachines
            monitor-enter(r2)
            int r0 = r9 + r10
            r3 = 6
            if (r0 > 0) goto L15
            if (r11 == r3) goto L27
        L15:
            if (r19 != 0) goto L1e
            boolean r0 = r1.mVirtualCallStarted     // Catch: java.lang.Throwable -> L83
            if (r0 == 0) goto L1e
            r12.stopScoUsingVirtualVoiceCall()     // Catch: java.lang.Throwable -> L83
        L1e:
            boolean r0 = r1.mVoiceRecognitionStarted     // Catch: java.lang.Throwable -> L83
            if (r0 == 0) goto L27
            android.bluetooth.BluetoothDevice r0 = r1.mActiveDevice     // Catch: java.lang.Throwable -> L83
            r12.stopVoiceRecognition(r0)     // Catch: java.lang.Throwable -> L83
        L27:
            com.android.bluetooth.hfp.HeadsetService$DialingOutTimeoutEvent r0 = r1.mDialingOutTimeoutEvent     // Catch: java.lang.Throwable -> L83
            if (r0 == 0) goto L57
            r0 = 2
            if (r11 != r0) goto L44
            android.os.Handler r0 = r12.getStateMachinesThreadHandler()     // Catch: java.lang.Throwable -> L83
            com.android.bluetooth.hfp.HeadsetService$DialingOutTimeoutEvent r3 = r1.mDialingOutTimeoutEvent     // Catch: java.lang.Throwable -> L83
            r0.removeCallbacks(r3)     // Catch: java.lang.Throwable -> L83
            com.android.bluetooth.hfp.HeadsetService$DialingOutTimeoutEvent r0 = r1.mDialingOutTimeoutEvent     // Catch: java.lang.Throwable -> L83
            android.bluetooth.BluetoothDevice r0 = r0.mDialingOutDevice     // Catch: java.lang.Throwable -> L83
            com.android.bluetooth.hfp.-$$Lambda$HeadsetService$tvXsd6PW2g_Lq7-NAnok7BU3LUQ r3 = new com.android.bluetooth.hfp.-$$Lambda$HeadsetService$tvXsd6PW2g_Lq7-NAnok7BU3LUQ     // Catch: java.lang.Throwable -> L83
            r3.<init>()     // Catch: java.lang.Throwable -> L83
            r12.doForStateMachine(r0, r3)     // Catch: java.lang.Throwable -> L83
            goto L57
        L44:
            if (r11 == 0) goto L48
            if (r11 != r3) goto L57
        L48:
            android.os.Handler r0 = r12.getStateMachinesThreadHandler()     // Catch: java.lang.Throwable -> L83
            com.android.bluetooth.hfp.HeadsetService$DialingOutTimeoutEvent r3 = r1.mDialingOutTimeoutEvent     // Catch: java.lang.Throwable -> L83
            boolean r0 = r0.hasCallbacks(r3)     // Catch: java.lang.Throwable -> L83
            if (r0 != 0) goto L57
            r0 = 0
            r1.mDialingOutTimeoutEvent = r0     // Catch: java.lang.Throwable -> L83
        L57:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L83
            android.os.Handler r0 = r12.getStateMachinesThreadHandler()
            com.android.bluetooth.hfp.-$$Lambda$HeadsetService$X-6qVEhmJhTkG3uzSTJxyJanRg8 r2 = new com.android.bluetooth.hfp.-$$Lambda$HeadsetService$X-6qVEhmJhTkG3uzSTJxyJanRg8
            r2.<init>()
            r0.post(r2)
            com.android.bluetooth.hfp.-$$Lambda$HeadsetService$Ouf0btK-y7fAoPvJmtljb_xAIhw r0 = new com.android.bluetooth.hfp.-$$Lambda$HeadsetService$Ouf0btK-y7fAoPvJmtljb_xAIhw
            r2 = r0
            r3 = r13
            r4 = r14
            r5 = r15
            r6 = r16
            r7 = r17
            r8 = r18
            r2.<init>()
            r12.doForEachConnectedStateMachine(r0)
            android.os.Handler r0 = r12.getStateMachinesThreadHandler()
            com.android.bluetooth.hfp.-$$Lambda$HeadsetService$nMnRTe8Yibs-I9WG36QYpyp5k2A r2 = new com.android.bluetooth.hfp.-$$Lambda$HeadsetService$nMnRTe8Yibs-I9WG36QYpyp5k2A
            r2.<init>()
            r0.post(r2)
            return
        L83:
            r0 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L83
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.hfp.HeadsetService.phoneStateChanged(int, int, int, java.lang.String, int, java.lang.String, boolean):void");
    }

    public /* synthetic */ void lambda$phoneStateChanged$1$HeadsetService(HeadsetStateMachine stateMachine) {
        stateMachine.sendMessage(14, 1, 0, this.mDialingOutTimeoutEvent.mDialingOutDevice);
    }

    public /* synthetic */ void lambda$phoneStateChanged$2$HeadsetService(int numActive, int numHeld, int callState) {
        boolean isCallIdleBefore = this.mSystemInterface.isCallIdle();
        this.mSystemInterface.getHeadsetPhoneState().setNumActiveCall(numActive);
        this.mSystemInterface.getHeadsetPhoneState().setNumHeldCall(numHeld);
        this.mSystemInterface.getHeadsetPhoneState().setCallState(callState);
        if (callState != 7 && !this.mSystemInterface.isCallIdle() && isCallIdleBefore) {
            this.mSystemInterface.getAudioManager().setParameters("A2dpSuspended=true");
        }
    }

    public /* synthetic */ void lambda$phoneStateChanged$4$HeadsetService(int callState) {
        if (callState == 6 && this.mSystemInterface.isCallIdle() && !isAudioOn()) {
            this.mSystemInterface.getAudioManager().setParameters("A2dpSuspended=false");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clccResponse(final int index, final int direction, final int status, final int mode, final boolean mpty, final String number, final int type) {
        enforceCallingOrSelfPermission("android.permission.MODIFY_PHONE_STATE", "Need MODIFY_PHONE_STATE permission");
        doForEachConnectedStateMachine(new StateMachineTask() { // from class: com.android.bluetooth.hfp.-$$Lambda$HeadsetService$nkWyHwQ1Pld2lvc6gO-GGCls530
            @Override // com.android.bluetooth.hfp.HeadsetService.StateMachineTask
            public final void execute(HeadsetStateMachine headsetStateMachine) {
                headsetStateMachine.sendMessage(11, new HeadsetClccResponse(index, direction, status, mode, mpty, number, type));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean sendVendorSpecificResultCode(BluetoothDevice device, String command, String arg) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        synchronized (this.mStateMachines) {
            HeadsetStateMachine stateMachine = this.mStateMachines.get(device);
            if (stateMachine == null) {
                Log.w(TAG, "sendVendorSpecificResultCode: device " + device + " was never connected/connecting");
                return false;
            }
            int connectionState = stateMachine.getConnectionState();
            if (connectionState != 2) {
                return false;
            }
            if (!command.equals("+ANDROID")) {
                Log.w(TAG, "Disallowed unsolicited result code command: " + command);
                return false;
            }
            stateMachine.sendMessage(12, new HeadsetVendorSpecificResultCode(device, command, arg));
            return true;
        }
    }

    boolean isInbandRingingEnabled() {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        return (!BluetoothHeadset.isInbandRingingSupported(this) || SystemProperties.getBoolean(DISABLE_INBAND_RINGING_PROPERTY, false) || this.mInbandRingingRuntimeDisable) ? false : true;
    }

    public void onConnectionStateChangedFromStateMachine(BluetoothDevice device, int fromState, int toState) {
        synchronized (this.mStateMachines) {
            List<BluetoothDevice> audioConnectableDevices = getDevicesMatchingConnectionStates(CONNECTING_CONNECTED_STATES);
            if (fromState != 2 && toState == 2) {
                if (audioConnectableDevices.size() > 1) {
                    this.mInbandRingingRuntimeDisable = true;
                    doForEachConnectedStateMachine(new StateMachineTask() { // from class: com.android.bluetooth.hfp.-$$Lambda$HeadsetService$wWmTpua2-QzIxn0hEBAL3FrWr6c
                        @Override // com.android.bluetooth.hfp.HeadsetService.StateMachineTask
                        public final void execute(HeadsetStateMachine headsetStateMachine) {
                            headsetStateMachine.sendMessage(13, 0);
                        }
                    });
                }
                MetricsLogger.logProfileConnectionEvent(BluetoothMetricsProto.ProfileId.HEADSET);
            }
            if (fromState != 0 && toState == 0) {
                if (audioConnectableDevices.size() <= 1) {
                    this.mInbandRingingRuntimeDisable = false;
                    doForEachConnectedStateMachine(new StateMachineTask() { // from class: com.android.bluetooth.hfp.-$$Lambda$HeadsetService$wVTEkKLAeSgOMGSZraQxSzc7kaA
                        @Override // com.android.bluetooth.hfp.HeadsetService.StateMachineTask
                        public final void execute(HeadsetStateMachine headsetStateMachine) {
                            headsetStateMachine.sendMessage(13, 1);
                        }
                    });
                }
                if (device.equals(this.mActiveDevice)) {
                    setActiveDevice(null);
                }
            }
        }
    }

    private boolean isAudioModeIdle() {
        synchronized (this.mStateMachines) {
            if (!this.mVoiceRecognitionStarted && !this.mVirtualCallStarted && this.mSystemInterface.isCallIdle()) {
                return true;
            }
            Log.i(TAG, "isAudioModeIdle: not idle, mVoiceRecognitionStarted=" + this.mVoiceRecognitionStarted + ", mVirtualCallStarted=" + this.mVirtualCallStarted + ", isCallIdle=" + this.mSystemInterface.isCallIdle());
            return false;
        }
    }

    private boolean shouldCallAudioBeActive() {
        return this.mSystemInterface.isInCall() || (this.mSystemInterface.isRinging() && isInbandRingingEnabled());
    }

    private boolean shouldPersistAudio() {
        return !this.mVirtualCallStarted && shouldCallAudioBeActive();
    }

    public void onAudioStateChangedFromStateMachine(BluetoothDevice device, int fromState, int toState) {
        synchronized (this.mStateMachines) {
            if (toState == 10) {
                if (fromState != 10) {
                    if (this.mActiveDevice != null && !this.mActiveDevice.equals(device) && shouldPersistAudio() && !connectAudio(this.mActiveDevice)) {
                        Log.w(TAG, "onAudioStateChangedFromStateMachine, failed to connect audio to new active device " + this.mActiveDevice + ", after " + device + " is disconnected from SCO");
                    }
                }
                if (this.mVoiceRecognitionStarted && !stopVoiceRecognitionByHeadset(device)) {
                    Log.w(TAG, "onAudioStateChangedFromStateMachine: failed to stop voice recognition");
                }
                if (this.mVirtualCallStarted && !stopScoUsingVirtualVoiceCall()) {
                    Log.w(TAG, "onAudioStateChangedFromStateMachine: failed to stop virtual voice call");
                }
                if (this.mSystemInterface.isCallIdle()) {
                    this.mSystemInterface.getAudioManager().setParameters("A2dpSuspended=false");
                }
            }
        }
    }

    private void broadcastActiveDevice(BluetoothDevice device) {
        logD("broadcastActiveDevice: " + device);
        BluetoothStatsLog.write(151, 1, this.mAdapterService.obfuscateAddress(device), this.mAdapterService.getMetricId(device));
        Intent intent = new Intent("android.bluetooth.headset.profile.action.ACTIVE_DEVICE_CHANGED");
        intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
        intent.addFlags(83886080);
        sendBroadcastAsUser(intent, UserHandle.ALL, ProfileService.BLUETOOTH_PERM);
    }

    public boolean okToAcceptConnection(BluetoothDevice device) {
        if (this.mAdapterService.isQuietModeEnabled()) {
            Log.w(TAG, "okToAcceptConnection: return false as quiet mode enabled");
            return false;
        }
        int connectionPolicy = getConnectionPolicy(device);
        int bondState = this.mAdapterService.getBondState(device);
        if (bondState != 12) {
            Log.w(TAG, "okToAcceptConnection: return false, bondState=" + bondState);
            return false;
        }
        if (connectionPolicy != -1 && connectionPolicy != 100) {
            Log.w(TAG, "okToAcceptConnection: return false, connectionPolicy=" + connectionPolicy);
            return false;
        }
        List<BluetoothDevice> connectingConnectedDevices = getDevicesMatchingConnectionStates(CONNECTING_CONNECTED_STATES);
        if (connectingConnectedDevices.size() >= this.mMaxHeadsetConnections) {
            Log.w(TAG, "Maximum number of connections " + this.mMaxHeadsetConnections + " was reached, rejecting connection from " + device);
            return false;
        }
        return true;
    }

    public boolean isScoAcceptable(BluetoothDevice device) {
        synchronized (this.mStateMachines) {
            if (device != null) {
                if (device.equals(this.mActiveDevice)) {
                    if (this.mForceScoAudio) {
                        return true;
                    }
                    if (!this.mAudioRouteAllowed) {
                        Log.w(TAG, "isScoAcceptable: rejected SCO since audio route is not allowed");
                        return false;
                    }
                    if (!this.mVoiceRecognitionStarted && !this.mVirtualCallStarted) {
                        if (shouldCallAudioBeActive()) {
                            return true;
                        }
                        Log.w(TAG, "isScoAcceptable: rejected SCO, inCall=" + this.mSystemInterface.isInCall() + ", voiceRecognition=" + this.mVoiceRecognitionStarted + ", ringing=" + this.mSystemInterface.isRinging() + ", inbandRinging=" + isInbandRingingEnabled() + ", isVirtualCallStarted=" + this.mVirtualCallStarted);
                        return false;
                    }
                    return true;
                }
            }
            Log.w(TAG, "isScoAcceptable: rejected SCO since " + device + " is not the current active device " + this.mActiveDevice);
            return false;
        }
    }

    void removeStateMachine(BluetoothDevice device) {
        synchronized (this.mStateMachines) {
            HeadsetStateMachine stateMachine = this.mStateMachines.get(device);
            if (stateMachine == null) {
                Log.w(TAG, "removeStateMachine(), " + device + " does not have a state machine");
                return;
            }
            Log.i(TAG, "removeStateMachine(), removing state machine for device: " + device);
            HeadsetObjectsFactory.getInstance().destroyStateMachine(stateMachine);
            this.mStateMachines.remove(device);
        }
    }

    private boolean isOnStateMachineThread() {
        Looper myLooper = Looper.myLooper();
        return (myLooper == null || this.mStateMachinesThread == null || myLooper.getThread().getId() != this.mStateMachinesThread.getId()) ? false : true;
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    public void dump(StringBuilder sb) {
        synchronized (this.mStateMachines) {
            super.dump(sb);
            ProfileService.println(sb, "mMaxHeadsetConnections: " + this.mMaxHeadsetConnections);
            ProfileService.println(sb, "DefaultMaxHeadsetConnections: " + this.mAdapterService.getMaxConnectedAudioDevices());
            ProfileService.println(sb, "mActiveDevice: " + this.mActiveDevice);
            ProfileService.println(sb, "isInbandRingingEnabled: " + isInbandRingingEnabled());
            ProfileService.println(sb, "isInbandRingingSupported: " + BluetoothHeadset.isInbandRingingSupported(this));
            ProfileService.println(sb, "mInbandRingingRuntimeDisable: " + this.mInbandRingingRuntimeDisable);
            ProfileService.println(sb, "mAudioRouteAllowed: " + this.mAudioRouteAllowed);
            ProfileService.println(sb, "mVoiceRecognitionStarted: " + this.mVoiceRecognitionStarted);
            ProfileService.println(sb, "mVoiceRecognitionTimeoutEvent: " + this.mVoiceRecognitionTimeoutEvent);
            ProfileService.println(sb, "mVirtualCallStarted: " + this.mVirtualCallStarted);
            ProfileService.println(sb, "mDialingOutTimeoutEvent: " + this.mDialingOutTimeoutEvent);
            ProfileService.println(sb, "mForceScoAudio: " + this.mForceScoAudio);
            ProfileService.println(sb, "mCreated: " + this.mCreated);
            ProfileService.println(sb, "mStarted: " + this.mStarted);
            ProfileService.println(sb, "AudioManager.isBluetoothScoOn(): " + this.mSystemInterface.getAudioManager().isBluetoothScoOn());
            ProfileService.println(sb, "Telecom.isInCall(): " + this.mSystemInterface.isInCall());
            ProfileService.println(sb, "Telecom.isRinging(): " + this.mSystemInterface.isRinging());
            for (HeadsetStateMachine stateMachine : this.mStateMachines.values()) {
                ProfileService.println(sb, "==== StateMachine for " + stateMachine.getDevice() + " ====");
                stateMachine.dump(sb);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void logD(String message) {
    }

    private Handler getStateMachinesThreadHandler() {
        if (this.mStateMachinesThreadHandler == null) {
            this.mStateMachinesThreadHandler = new Handler(this.mStateMachinesThread.getLooper());
        }
        return this.mStateMachinesThreadHandler;
    }
}
