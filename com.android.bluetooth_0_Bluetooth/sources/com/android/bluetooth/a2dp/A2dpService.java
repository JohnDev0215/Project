package com.android.bluetooth.a2dp;

import android.bluetooth.BluetoothCodecConfig;
import android.bluetooth.BluetoothCodecStatus;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothUuid;
import android.bluetooth.IBluetoothA2dp;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.HandlerThread;
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
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: loaded from: classes.dex */
public class A2dpService extends ProfileService {
    private static final boolean DBG = true;
    private static final int MAX_A2DP_STATE_MACHINES = 50;
    private static final String TAG = "A2dpService";
    private static A2dpService sA2dpService;
    private A2dpCodecConfig mA2dpCodecConfig;
    A2dpNativeInterface mA2dpNativeInterface;
    private BluetoothDevice mActiveDevice;
    private AdapterService mAdapterService;
    private AudioManager mAudioManager;
    private BroadcastReceiver mBondStateChangedReceiver;
    private BroadcastReceiver mConnectionStateChangedReceiver;
    private HandlerThread mStateMachinesThread;
    ServiceFactory mFactory = new ServiceFactory();
    private final ConcurrentMap<BluetoothDevice, A2dpStateMachine> mStateMachines = new ConcurrentHashMap();
    private final Object mActiveSwitchingGuard = new Object();
    private int mMaxConnectedAudioDevices = 1;
    boolean mA2dpOffloadEnabled = false;

    @Override // com.android.bluetooth.btservice.ProfileService
    protected ProfileService.IProfileServiceBinder initBinder() {
        return new BluetoothA2dpBinder(this);
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected void create() {
        Log.i(TAG, "create()");
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected boolean start() {
        Log.i(TAG, "start()");
        if (sA2dpService != null) {
            throw new IllegalStateException("start() called twice");
        }
        AdapterService adapterService = AdapterService.getAdapterService();
        Objects.requireNonNull(adapterService, "AdapterService cannot be null when A2dpService starts");
        this.mAdapterService = adapterService;
        A2dpNativeInterface a2dpNativeInterface = A2dpNativeInterface.getInstance();
        Objects.requireNonNull(a2dpNativeInterface, "A2dpNativeInterface cannot be null when A2dpService starts");
        this.mA2dpNativeInterface = a2dpNativeInterface;
        AudioManager audioManager = (AudioManager) getSystemService("audio");
        this.mAudioManager = audioManager;
        Objects.requireNonNull(audioManager, "AudioManager cannot be null when A2dpService starts");
        this.mMaxConnectedAudioDevices = this.mAdapterService.getMaxConnectedAudioDevices();
        Log.i(TAG, "Max connected audio devices set to " + this.mMaxConnectedAudioDevices);
        this.mStateMachines.clear();
        HandlerThread handlerThread = new HandlerThread("A2dpService.StateMachines");
        this.mStateMachinesThread = handlerThread;
        handlerThread.start();
        A2dpCodecConfig a2dpCodecConfig = new A2dpCodecConfig(this, this.mA2dpNativeInterface);
        this.mA2dpCodecConfig = a2dpCodecConfig;
        this.mA2dpNativeInterface.init(this.mMaxConnectedAudioDevices, a2dpCodecConfig.codecConfigPriorities(), this.mA2dpCodecConfig.codecConfigOffloading());
        this.mA2dpOffloadEnabled = this.mAdapterService.isA2dpOffloadEnabled();
        Log.d(TAG, "A2DP offload flag set to " + this.mA2dpOffloadEnabled);
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
        BondStateChangedReceiver bondStateChangedReceiver = new BondStateChangedReceiver();
        this.mBondStateChangedReceiver = bondStateChangedReceiver;
        registerReceiver(bondStateChangedReceiver, filter);
        IntentFilter filter2 = new IntentFilter();
        filter2.addAction("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
        ConnectionStateChangedReceiver connectionStateChangedReceiver = new ConnectionStateChangedReceiver();
        this.mConnectionStateChangedReceiver = connectionStateChangedReceiver;
        registerReceiver(connectionStateChangedReceiver, filter2);
        setA2dpService(this);
        setActiveDevice(null);
        return true;
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected boolean stop() {
        Log.i(TAG, "stop()");
        if (sA2dpService == null) {
            Log.w(TAG, "stop() called before start()");
            return true;
        }
        removeActiveDevice(true);
        setA2dpService(null);
        unregisterReceiver(this.mConnectionStateChangedReceiver);
        this.mConnectionStateChangedReceiver = null;
        unregisterReceiver(this.mBondStateChangedReceiver);
        this.mBondStateChangedReceiver = null;
        this.mA2dpNativeInterface.cleanup();
        this.mA2dpNativeInterface = null;
        this.mA2dpCodecConfig = null;
        synchronized (this.mStateMachines) {
            for (A2dpStateMachine sm : this.mStateMachines.values()) {
                sm.doQuit();
                sm.cleanup();
            }
            this.mStateMachines.clear();
        }
        this.mStateMachinesThread.quitSafely();
        this.mStateMachinesThread = null;
        this.mMaxConnectedAudioDevices = 1;
        this.mAudioManager = null;
        this.mA2dpNativeInterface = null;
        this.mAdapterService = null;
        return true;
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected void cleanup() {
        Log.i(TAG, "cleanup()");
    }

    public static synchronized A2dpService getA2dpService() {
        if (sA2dpService == null) {
            Log.w(TAG, "getA2dpService(): service is null");
            return null;
        }
        if (!sA2dpService.isAvailable()) {
            Log.w(TAG, "getA2dpService(): service is not available");
            return null;
        }
        return sA2dpService;
    }

    private static synchronized void setA2dpService(A2dpService instance) {
        Log.d(TAG, "setA2dpService(): set to: " + instance);
        sA2dpService = instance;
    }

    public boolean connect(BluetoothDevice device) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN", "Need BLUETOOTH ADMIN permission");
        Log.d(TAG, "connect(): " + device);
        if (getConnectionPolicy(device) == 0) {
            Log.e(TAG, "Cannot connect to " + device + " : CONNECTION_POLICY_FORBIDDEN");
            return false;
        }
        if (!ArrayUtils.contains(this.mAdapterService.getRemoteUuids(device), BluetoothUuid.A2DP_SINK)) {
            Log.e(TAG, "Cannot connect to " + device + " : Remote does not have A2DP Sink UUID");
            return false;
        }
        synchronized (this.mStateMachines) {
            if (!connectionAllowedCheckMaxDevices(device)) {
                if (this.mMaxConnectedAudioDevices == 1) {
                    List<BluetoothDevice> sinks = getDevicesMatchingConnectionStates(new int[]{2, 1, 3});
                    for (BluetoothDevice sink : sinks) {
                        if (sink.equals(device)) {
                            Log.w(TAG, "Connecting to device " + device + " : disconnect skipped");
                        } else {
                            disconnect(sink);
                        }
                    }
                } else {
                    Log.e(TAG, "Cannot connect to " + device + " : too many connected devices");
                    return false;
                }
            }
            A2dpStateMachine smConnect = getOrCreateStateMachine(device);
            if (smConnect == null) {
                Log.e(TAG, "Cannot connect to " + device + " : no state machine");
                return false;
            }
            smConnect.sendMessage(1);
            return true;
        }
    }

    public boolean disconnect(BluetoothDevice device) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN", "Need BLUETOOTH ADMIN permission");
        Log.d(TAG, "disconnect(): " + device);
        synchronized (this.mStateMachines) {
            A2dpStateMachine sm = this.mStateMachines.get(device);
            if (sm == null) {
                Log.e(TAG, "Ignored disconnect request for " + device + " : no state machine");
                return false;
            }
            sm.sendMessage(2);
            return true;
        }
    }

    public List<BluetoothDevice> getConnectedDevices() {
        List<BluetoothDevice> devices;
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        synchronized (this.mStateMachines) {
            devices = new ArrayList<>();
            for (A2dpStateMachine sm : this.mStateMachines.values()) {
                if (sm.isConnected()) {
                    devices.add(sm.getDevice());
                }
            }
        }
        return devices;
    }

    private boolean connectionAllowedCheckMaxDevices(BluetoothDevice device) {
        int connected = 0;
        synchronized (this.mStateMachines) {
            for (A2dpStateMachine sm : this.mStateMachines.values()) {
                int connectionState = sm.getConnectionState();
                if (connectionState == 1 || connectionState == 2) {
                    if (Objects.equals(device, sm.getDevice())) {
                        return true;
                    }
                    connected++;
                }
            }
            return connected < this.mMaxConnectedAudioDevices;
        }
    }

    public boolean okToConnect(BluetoothDevice device, boolean isOutgoingRequest) {
        Log.i(TAG, "okToConnect: device " + device + " isOutgoingRequest: " + isOutgoingRequest);
        if (this.mAdapterService.isQuietModeEnabled() && !isOutgoingRequest) {
            Log.e(TAG, "okToConnect: cannot connect to " + device + " : quiet mode enabled");
            return false;
        }
        if (!connectionAllowedCheckMaxDevices(device)) {
            Log.e(TAG, "okToConnect: cannot connect to " + device + " : too many connected devices");
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
        List<BluetoothDevice> devices = new ArrayList<>();
        if (states == null || (bondedDevices = this.mAdapterService.getBondedDevices()) == null) {
            return devices;
        }
        synchronized (this.mStateMachines) {
            for (BluetoothDevice device : bondedDevices) {
                if (ArrayUtils.contains(this.mAdapterService.getRemoteUuids(device), BluetoothUuid.A2DP_SINK)) {
                    int connectionState = 0;
                    A2dpStateMachine sm = this.mStateMachines.get(device);
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
            for (A2dpStateMachine sm : this.mStateMachines.values()) {
                devices.add(sm.getDevice());
            }
        }
        return devices;
    }

    public int getConnectionState(BluetoothDevice device) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        synchronized (this.mStateMachines) {
            A2dpStateMachine sm = this.mStateMachines.get(device);
            if (sm == null) {
                return 0;
            }
            return sm.getConnectionState();
        }
    }

    private void removeActiveDevice(boolean forceStopPlayingAudio) {
        synchronized (this.mActiveSwitchingGuard) {
            synchronized (this.mStateMachines) {
                if (this.mActiveDevice == null) {
                    return;
                }
                BluetoothDevice previousActiveDevice = this.mActiveDevice;
                updateAndBroadcastActiveDevice(null);
                boolean suppressNoisyIntent = !forceStopPlayingAudio && getConnectionState(previousActiveDevice) == 2;
                Log.i(TAG, "removeActiveDevice: suppressNoisyIntent=" + suppressNoisyIntent);
                this.mAudioManager.setBluetoothA2dpDeviceConnectionStateSuppressNoisyIntent(previousActiveDevice, 0, 2, suppressNoisyIntent, -1);
                synchronized (this.mStateMachines) {
                    if (!this.mA2dpNativeInterface.setActiveDevice(null)) {
                        Log.w(TAG, "setActiveDevice(null): Cannot remove active device in native layer");
                    }
                }
            }
        }
    }

    public boolean setSilenceMode(BluetoothDevice device, boolean silence) {
        Log.d(TAG, "setSilenceMode(" + device + "): " + silence);
        if (silence && Objects.equals(this.mActiveDevice, device)) {
            removeActiveDevice(true);
        } else if (!silence && this.mActiveDevice == null) {
            setActiveDevice(device);
        }
        if (this.mA2dpNativeInterface.setSilenceDevice(device, silence)) {
            return true;
        }
        Log.e(TAG, "Cannot set " + device + " silence mode " + silence + " in native layer");
        return false;
    }

    public boolean setActiveDevice(BluetoothDevice device) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN", "Need BLUETOOTH ADMIN permission");
        synchronized (this.mActiveSwitchingGuard) {
            if (device == null) {
                removeActiveDevice(false);
                return true;
            }
            synchronized (this.mStateMachines) {
                if (Objects.equals(device, this.mActiveDevice)) {
                    Log.i(TAG, "setActiveDevice(" + device + "): current is " + this.mActiveDevice + " no changed");
                    return true;
                }
                Log.d(TAG, "setActiveDevice(" + device + "): current is " + this.mActiveDevice);
                A2dpStateMachine sm = this.mStateMachines.get(device);
                if (sm == null) {
                    Log.e(TAG, "setActiveDevice(" + device + "): Cannot set as active: no state machine");
                    return false;
                }
                if (sm.getConnectionState() != 2) {
                    Log.e(TAG, "setActiveDevice(" + device + "): Cannot set as active: device is not connected");
                    return false;
                }
                BluetoothDevice previousActiveDevice = this.mActiveDevice;
                Log.d(TAG, "Switch A2DP devices to " + device + " from " + previousActiveDevice);
                updateAndBroadcastActiveDevice(device);
                if (previousActiveDevice != null) {
                    this.mAudioManager.setBluetoothA2dpDeviceConnectionStateSuppressNoisyIntent(previousActiveDevice, 0, 2, true, -1);
                }
                synchronized (this.mStateMachines) {
                    if (!this.mA2dpNativeInterface.setActiveDevice(device)) {
                        Log.e(TAG, "setActiveDevice(" + device + "): Cannot set as active in native layer");
                        removeActiveDevice(true);
                        return false;
                    }
                    BluetoothCodecStatus codecStatus = sm.getCodecStatus();
                    if (codecStatus != null) {
                        broadcastCodecConfig(this.mActiveDevice, codecStatus);
                    }
                    BluetoothDevice newActiveDevice = this.mActiveDevice;
                    int rememberedVolume = -1;
                    if (this.mFactory.getAvrcpTargetService() != null) {
                        rememberedVolume = this.mFactory.getAvrcpTargetService().getRememberedVolumeForDevice(newActiveDevice);
                    }
                    this.mAudioManager.setBluetoothA2dpDeviceConnectionStateSuppressNoisyIntent(newActiveDevice, 2, 2, true, rememberedVolume);
                    this.mAudioManager.handleBluetoothA2dpDeviceConfigChange(newActiveDevice);
                    return true;
                }
            }
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

    /* JADX WARN: Removed duplicated region for block: B:10:0x0011  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean isActiveDevice(android.bluetooth.BluetoothDevice r3) {
        /*
            r2 = this;
            java.util.concurrent.ConcurrentMap<android.bluetooth.BluetoothDevice, com.android.bluetooth.a2dp.A2dpStateMachine> r0 = r2.mStateMachines
            monitor-enter(r0)
            if (r3 == 0) goto L11
            android.bluetooth.BluetoothDevice r1 = r2.mActiveDevice     // Catch: java.lang.Throwable -> Lf
            boolean r1 = java.util.Objects.equals(r3, r1)     // Catch: java.lang.Throwable -> Lf
            if (r1 == 0) goto L11
            r1 = 1
            goto L12
        Lf:
            r1 = move-exception
            goto L14
        L11:
            r1 = 0
        L12:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lf
            return r1
        L14:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lf
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.a2dp.A2dpService.isActiveDevice(android.bluetooth.BluetoothDevice):boolean");
    }

    public boolean setConnectionPolicy(BluetoothDevice device, int connectionPolicy) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_PRIVILEGED", "Need BLUETOOTH_PRIVILEGED permission");
        Log.d(TAG, "Saved connectionPolicy " + device + " = " + connectionPolicy);
        boolean setSuccessfully = this.mAdapterService.getDatabase().setProfileConnectionPolicy(device, 2, connectionPolicy);
        if (setSuccessfully && connectionPolicy == 100) {
            connect(device);
        } else if (setSuccessfully && connectionPolicy == 0) {
            disconnect(device);
        }
        return setSuccessfully;
    }

    public boolean setConnectionPolicy2(BluetoothDevice device, int connectionPolicy) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_PRIVILEGED", "Need BLUETOOTH_PRIVILEGED permission");
        Log.d(TAG, "Saved connectionPolicy2 " + device + " = " + connectionPolicy);
        this.mAdapterService.getDatabase().setProfileConnectionPolicy(device, 2, connectionPolicy);
        return true;
    }

    public int getConnectionPolicy(BluetoothDevice device) {
        return this.mAdapterService.getDatabase().getProfileConnectionPolicy(device, 2);
    }

    public boolean isAvrcpAbsoluteVolumeSupported() {
        return false;
    }

    public void setAvrcpAbsoluteVolume(int volume) {
        if (this.mFactory.getAvrcpTargetService() != null) {
            this.mFactory.getAvrcpTargetService().sendVolumeChanged(volume);
        }
    }

    boolean isA2dpPlaying(BluetoothDevice device) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        Log.d(TAG, "isA2dpPlaying(" + device + ")");
        synchronized (this.mStateMachines) {
            A2dpStateMachine sm = this.mStateMachines.get(device);
            if (sm == null) {
                return false;
            }
            return sm.isPlaying();
        }
    }

    public BluetoothCodecStatus getCodecStatus(BluetoothDevice device) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        Log.d(TAG, "getCodecStatus(" + device + ")");
        synchronized (this.mStateMachines) {
            if (device == null) {
                try {
                    device = this.mActiveDevice;
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (device == null) {
                return null;
            }
            A2dpStateMachine sm = this.mStateMachines.get(device);
            if (sm == null) {
                return null;
            }
            return sm.getCodecStatus();
        }
    }

    public void setCodecConfigPreference(BluetoothDevice device, BluetoothCodecConfig codecConfig) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        Log.d(TAG, "setCodecConfigPreference(" + device + "): " + Objects.toString(codecConfig));
        if (device == null) {
            device = this.mActiveDevice;
        }
        if (device == null) {
            Log.e(TAG, "setCodecConfigPreference: Invalid device");
            return;
        }
        if (codecConfig == null) {
            Log.e(TAG, "setCodecConfigPreference: Codec config can't be null");
            return;
        }
        BluetoothCodecStatus codecStatus = getCodecStatus(device);
        if (codecStatus == null) {
            Log.e(TAG, "setCodecConfigPreference: Codec status is null");
        } else {
            this.mA2dpCodecConfig.setCodecConfigPreference(device, codecStatus, codecConfig);
        }
    }

    public void enableOptionalCodecs(BluetoothDevice device) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        Log.d(TAG, "enableOptionalCodecs(" + device + ")");
        if (device == null) {
            device = this.mActiveDevice;
        }
        if (device == null) {
            Log.e(TAG, "enableOptionalCodecs: Invalid device");
            return;
        }
        if (getSupportsOptionalCodecs(device) != 1) {
            Log.e(TAG, "enableOptionalCodecs: No optional codecs");
            return;
        }
        BluetoothCodecStatus codecStatus = getCodecStatus(device);
        if (codecStatus == null) {
            Log.e(TAG, "enableOptionalCodecs: Codec status is null");
        } else {
            this.mA2dpCodecConfig.enableOptionalCodecs(device, codecStatus.getCodecConfig());
        }
    }

    public void disableOptionalCodecs(BluetoothDevice device) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        Log.d(TAG, "disableOptionalCodecs(" + device + ")");
        if (device == null) {
            device = this.mActiveDevice;
        }
        if (device == null) {
            Log.e(TAG, "disableOptionalCodecs: Invalid device");
            return;
        }
        if (getSupportsOptionalCodecs(device) != 1) {
            Log.e(TAG, "disableOptionalCodecs: No optional codecs");
            return;
        }
        BluetoothCodecStatus codecStatus = getCodecStatus(device);
        if (codecStatus == null) {
            Log.e(TAG, "disableOptionalCodecs: Codec status is null");
        } else {
            this.mA2dpCodecConfig.disableOptionalCodecs(device, codecStatus.getCodecConfig());
        }
    }

    public int getSupportsOptionalCodecs(BluetoothDevice device) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN", "Need BLUETOOTH ADMIN permission");
        return this.mAdapterService.getDatabase().getA2dpSupportsOptionalCodecs(device);
    }

    public void setSupportsOptionalCodecs(BluetoothDevice device, boolean doesSupport) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN", "Need BLUETOOTH ADMIN permission");
        int value = doesSupport ? 1 : 0;
        this.mAdapterService.getDatabase().setA2dpSupportsOptionalCodecs(device, value);
    }

    public int getOptionalCodecsEnabled(BluetoothDevice device) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN", "Need BLUETOOTH_ADMIN permission");
        return this.mAdapterService.getDatabase().getA2dpOptionalCodecsEnabled(device);
    }

    public void setOptionalCodecsEnabled(BluetoothDevice device, int value) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN", "Need BLUETOOTH_ADMIN permission");
        if (value != -1 && value != 0 && value != 1) {
            Log.w(TAG, "Unexpected value passed to setOptionalCodecsEnabled:" + value);
            return;
        }
        this.mAdapterService.getDatabase().setA2dpOptionalCodecsEnabled(device, value);
    }

    void messageFromNative(A2dpStackEvent stackEvent) {
        int i;
        Objects.requireNonNull(stackEvent.device, "Device should never be null, event: " + stackEvent);
        synchronized (this.mStateMachines) {
            BluetoothDevice device = stackEvent.device;
            A2dpStateMachine sm = this.mStateMachines.get(device);
            if (sm == null && stackEvent.type == 1 && ((i = stackEvent.valueInt) == 1 || i == 2)) {
                if (!connectionAllowedCheckMaxDevices(device)) {
                    Log.e(TAG, "Cannot connect to " + device + " : too many connected devices");
                    return;
                }
                sm = getOrCreateStateMachine(device);
            }
            if (sm == null) {
                Log.e(TAG, "Cannot process stack event: no state machine: " + stackEvent);
                return;
            }
            sm.sendMessage(101, stackEvent);
        }
    }

    public void codecConfigUpdated(BluetoothDevice device, BluetoothCodecStatus codecStatus, boolean sameAudioFeedingParameters) {
        BluetoothCodecConfig codecConfig = codecStatus.getCodecConfig();
        int metricId = this.mAdapterService.getMetricId(device);
        BluetoothStatsLog.write(BluetoothStatsLog.BLUETOOTH_A2DP_CODEC_CONFIG_CHANGED, this.mAdapterService.obfuscateAddress(device), codecConfig.getCodecType(), codecConfig.getCodecPriority(), codecConfig.getSampleRate(), codecConfig.getBitsPerSample(), codecConfig.getChannelMode(), codecConfig.getCodecSpecific1(), codecConfig.getCodecSpecific2(), codecConfig.getCodecSpecific3(), codecConfig.getCodecSpecific4(), metricId);
        BluetoothCodecConfig[] codecCapabilities = codecStatus.getCodecsSelectableCapabilities();
        int length = codecCapabilities.length;
        int i = 0;
        while (i < length) {
            BluetoothCodecConfig codecCapability = codecCapabilities[i];
            BluetoothStatsLog.write(BluetoothStatsLog.BLUETOOTH_A2DP_CODEC_CAPABILITY_CHANGED, this.mAdapterService.obfuscateAddress(device), codecCapability.getCodecType(), codecCapability.getCodecPriority(), codecCapability.getSampleRate(), codecCapability.getBitsPerSample(), codecCapability.getChannelMode(), codecConfig.getCodecSpecific1(), codecConfig.getCodecSpecific2(), codecConfig.getCodecSpecific3(), codecConfig.getCodecSpecific4(), metricId);
            i++;
            length = length;
            codecCapabilities = codecCapabilities;
        }
        broadcastCodecConfig(device, codecStatus);
        if (isActiveDevice(device) && !sameAudioFeedingParameters) {
            this.mAudioManager.handleBluetoothA2dpDeviceConfigChange(device);
        }
    }

    private A2dpStateMachine getOrCreateStateMachine(BluetoothDevice device) {
        if (device == null) {
            Log.e(TAG, "getOrCreateStateMachine failed: device cannot be null");
            return null;
        }
        synchronized (this.mStateMachines) {
            A2dpStateMachine sm = this.mStateMachines.get(device);
            if (sm != null) {
                return sm;
            }
            if (this.mStateMachines.size() >= 50) {
                Log.e(TAG, "Maximum number of A2DP state machines reached: 50");
                return null;
            }
            Log.d(TAG, "Creating a new state machine for " + device);
            A2dpStateMachine sm2 = A2dpStateMachine.make(device, this, this.mA2dpNativeInterface, this.mStateMachinesThread.getLooper());
            this.mStateMachines.put(device, sm2);
            return sm2;
        }
    }

    private void updateAndBroadcastActiveDevice(BluetoothDevice device) {
        Log.d(TAG, "updateAndBroadcastActiveDevice(" + device + ")");
        if (this.mFactory.getAvrcpTargetService() != null) {
            this.mFactory.getAvrcpTargetService().volumeDeviceSwitched(device);
        }
        synchronized (this.mStateMachines) {
            this.mActiveDevice = device;
        }
        BluetoothStatsLog.write(151, 2, this.mAdapterService.obfuscateAddress(device), this.mAdapterService.getMetricId(device));
        Intent intent = new Intent("android.bluetooth.a2dp.profile.action.ACTIVE_DEVICE_CHANGED");
        intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
        intent.addFlags(83886080);
        sendBroadcast(intent, ProfileService.BLUETOOTH_PERM);
    }

    private void broadcastCodecConfig(BluetoothDevice device, BluetoothCodecStatus codecStatus) {
        Log.d(TAG, "broadcastCodecConfig(" + device + "): " + codecStatus);
        Intent intent = new Intent("android.bluetooth.a2dp.profile.action.CODEC_CONFIG_CHANGED");
        intent.putExtra("android.bluetooth.extra.CODEC_STATUS", codecStatus);
        intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
        intent.addFlags(83886080);
        sendBroadcast(intent, ProfileService.BLUETOOTH_PERM);
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
            A2dpService.this.bondStateChanged(device, state);
        }
    }

    void bondStateChanged(BluetoothDevice device, int bondState) {
        Log.d(TAG, "Bond state changed for device: " + device + " state: " + bondState);
        if (bondState != 10) {
            return;
        }
        synchronized (this.mStateMachines) {
            A2dpStateMachine sm = this.mStateMachines.get(device);
            if (sm == null) {
                return;
            }
            if (sm.getConnectionState() != 0) {
                return;
            }
            if (this.mFactory.getAvrcpTargetService() != null) {
                this.mFactory.getAvrcpTargetService().removeStoredVolumeForDevice(device);
            }
            removeStateMachine(device);
        }
    }

    private void removeStateMachine(BluetoothDevice device) {
        synchronized (this.mStateMachines) {
            A2dpStateMachine sm = this.mStateMachines.get(device);
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

    /* JADX WARN: Removed duplicated region for block: B:26:0x0046  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void updateOptionalCodecsSupport(android.bluetooth.BluetoothDevice r13) {
        /*
            r12 = this;
            int r0 = r12.getSupportsOptionalCodecs(r13)
            r1 = 0
            r2 = 0
            java.util.concurrent.ConcurrentMap<android.bluetooth.BluetoothDevice, com.android.bluetooth.a2dp.A2dpStateMachine> r3 = r12.mStateMachines
            monitor-enter(r3)
            java.util.concurrent.ConcurrentMap<android.bluetooth.BluetoothDevice, com.android.bluetooth.a2dp.A2dpStateMachine> r4 = r12.mStateMachines     // Catch: java.lang.Throwable -> L61
            java.lang.Object r4 = r4.get(r13)     // Catch: java.lang.Throwable -> L61
            com.android.bluetooth.a2dp.A2dpStateMachine r4 = (com.android.bluetooth.a2dp.A2dpStateMachine) r4     // Catch: java.lang.Throwable -> L61
            if (r4 != 0) goto L15
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L61
            return
        L15:
            android.bluetooth.BluetoothCodecStatus r5 = r4.getCodecStatus()     // Catch: java.lang.Throwable -> L61
            r6 = 0
            if (r5 == 0) goto L32
            android.bluetooth.BluetoothCodecConfig[] r7 = r5.getCodecsSelectableCapabilities()     // Catch: java.lang.Throwable -> L61
            int r8 = r7.length     // Catch: java.lang.Throwable -> L61
            r9 = r6
        L22:
            if (r9 >= r8) goto L32
            r10 = r7[r9]     // Catch: java.lang.Throwable -> L61
            boolean r11 = r10.isMandatoryCodec()     // Catch: java.lang.Throwable -> L61
            if (r11 == 0) goto L2e
            r2 = 1
            goto L2f
        L2e:
            r1 = 1
        L2f:
            int r9 = r9 + 1
            goto L22
        L32:
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L61
            if (r2 != 0) goto L3d
            java.lang.String r3 = "A2dpService"
            java.lang.String r4 = "updateOptionalCodecsSupport: Mandatory codec is not selectable."
            android.util.Log.i(r3, r4)
            return
        L3d:
            r3 = -1
            r4 = 1
            if (r0 == r3) goto L46
            if (r0 != r4) goto L44
            r6 = r4
        L44:
            if (r1 == r6) goto L49
        L46:
            r12.setSupportsOptionalCodecs(r13, r1)
        L49:
            if (r1 == 0) goto L60
            int r5 = r12.getOptionalCodecsEnabled(r13)
            if (r5 == r3) goto L5a
            if (r5 == 0) goto L56
            if (r5 == r4) goto L5d
            goto L60
        L56:
            r12.disableOptionalCodecs(r13)
            goto L60
        L5a:
            r12.setOptionalCodecsEnabled(r13, r4)
        L5d:
            r12.enableOptionalCodecs(r13)
        L60:
            return
        L61:
            r4 = move-exception
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L61
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.a2dp.A2dpService.updateOptionalCodecsSupport(android.bluetooth.BluetoothDevice):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectionStateChanged(BluetoothDevice device, int fromState, int toState) {
        if (device == null || fromState == toState) {
            return;
        }
        if (toState == 2) {
            MetricsLogger.logProfileConnectionEvent(BluetoothMetricsProto.ProfileId.A2DP);
        }
        if (toState == 2 && this.mMaxConnectedAudioDevices == 1) {
            setActiveDevice(device);
        }
        if (isActiveDevice(device) && fromState == 2) {
            setActiveDevice(null);
        }
        if (toState == 0 && this.mAdapterService.getBondState(device) == 10) {
            if (this.mFactory.getAvrcpTargetService() != null) {
                this.mFactory.getAvrcpTargetService().removeStoredVolumeForDevice(device);
            }
            removeStateMachine(device);
        }
    }

    private class ConnectionStateChangedReceiver extends BroadcastReceiver {
        private ConnectionStateChangedReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (!"android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                return;
            }
            BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            int toState = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
            int fromState = intent.getIntExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", -1);
            A2dpService.this.connectionStateChanged(device, fromState, toState);
        }
    }

    static class BluetoothA2dpBinder extends IBluetoothA2dp.Stub implements ProfileService.IProfileServiceBinder {
        private A2dpService mService;

        private A2dpService getService() {
            if (!Utils.checkCaller()) {
                Log.w(A2dpService.TAG, "A2DP call not allowed for non-active user");
                return null;
            }
            A2dpService a2dpService = this.mService;
            if (a2dpService == null || !a2dpService.isAvailable()) {
                return null;
            }
            return this.mService;
        }

        BluetoothA2dpBinder(A2dpService svc) {
            this.mService = svc;
        }

        @Override // com.android.bluetooth.btservice.ProfileService.IProfileServiceBinder
        public void cleanup() {
            this.mService = null;
        }

        public boolean connect(BluetoothDevice device) {
            A2dpService service = getService();
            if (service == null) {
                return false;
            }
            return service.connect(device);
        }

        public boolean disconnect(BluetoothDevice device) {
            A2dpService service = getService();
            if (service == null) {
                return false;
            }
            return service.disconnect(device);
        }

        public List<BluetoothDevice> getConnectedDevices() {
            A2dpService service = getService();
            if (service == null) {
                return new ArrayList(0);
            }
            return service.getConnectedDevices();
        }

        public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states) {
            A2dpService service = getService();
            if (service == null) {
                return new ArrayList(0);
            }
            return service.getDevicesMatchingConnectionStates(states);
        }

        public int getConnectionState(BluetoothDevice device) {
            A2dpService service = getService();
            if (service == null) {
                return 0;
            }
            return service.getConnectionState(device);
        }

        public boolean setActiveDevice(BluetoothDevice device) {
            A2dpService service = getService();
            if (service == null) {
                return false;
            }
            return service.setActiveDevice(device);
        }

        public BluetoothDevice getActiveDevice() {
            A2dpService service = getService();
            if (service == null) {
                return null;
            }
            return service.getActiveDevice();
        }

        public boolean setConnectionPolicy(BluetoothDevice device, int connectionPolicy) {
            A2dpService service = getService();
            if (service == null) {
                return false;
            }
            return service.setConnectionPolicy(device, connectionPolicy);
        }

        public boolean setConnectionPolicy2(BluetoothDevice device, int connectionPolicy) {
            A2dpService service = getService();
            if (service == null) {
                return false;
            }
            return service.setConnectionPolicy2(device, connectionPolicy);
        }

        public int getPriority(BluetoothDevice device) {
            A2dpService service = getService();
            if (service == null) {
                return -1;
            }
            Utils.enforceBluetoothPermission(service);
            return service.getConnectionPolicy(device);
        }

        public int getConnectionPolicy(BluetoothDevice device) {
            A2dpService service = getService();
            if (service == null) {
                return -1;
            }
            Utils.enforceBluetoothPrivilegedPermission(service);
            return service.getConnectionPolicy(device);
        }

        public boolean isAvrcpAbsoluteVolumeSupported() {
            A2dpService service = getService();
            if (service == null) {
                return false;
            }
            return service.isAvrcpAbsoluteVolumeSupported();
        }

        public void setAvrcpAbsoluteVolume(int volume) {
            A2dpService service = getService();
            if (service == null) {
                return;
            }
            service.setAvrcpAbsoluteVolume(volume);
        }

        public boolean isA2dpPlaying(BluetoothDevice device) {
            A2dpService service = getService();
            if (service == null) {
                return false;
            }
            return service.isA2dpPlaying(device);
        }

        public BluetoothCodecStatus getCodecStatus(BluetoothDevice device) {
            A2dpService service = getService();
            if (service == null) {
                return null;
            }
            return service.getCodecStatus(device);
        }

        public void setCodecConfigPreference(BluetoothDevice device, BluetoothCodecConfig codecConfig) {
            A2dpService service = getService();
            if (service == null) {
                return;
            }
            service.setCodecConfigPreference(device, codecConfig);
        }

        public void enableOptionalCodecs(BluetoothDevice device) {
            A2dpService service = getService();
            if (service == null) {
                return;
            }
            service.enableOptionalCodecs(device);
        }

        public void disableOptionalCodecs(BluetoothDevice device) {
            A2dpService service = getService();
            if (service == null) {
                return;
            }
            service.disableOptionalCodecs(device);
        }

        public int supportsOptionalCodecs(BluetoothDevice device) {
            A2dpService service = getService();
            if (service == null) {
                return -1;
            }
            return service.getSupportsOptionalCodecs(device);
        }

        public int getOptionalCodecsEnabled(BluetoothDevice device) {
            A2dpService service = getService();
            if (service == null) {
                return -1;
            }
            return service.getOptionalCodecsEnabled(device);
        }

        public void setOptionalCodecsEnabled(BluetoothDevice device, int value) {
            A2dpService service = getService();
            if (service == null) {
                return;
            }
            service.setOptionalCodecsEnabled(device, value);
        }
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    public void dump(StringBuilder sb) {
        super.dump(sb);
        ProfileService.println(sb, "mActiveDevice: " + this.mActiveDevice);
        ProfileService.println(sb, "mMaxConnectedAudioDevices: " + this.mMaxConnectedAudioDevices);
        if (this.mA2dpCodecConfig != null) {
            ProfileService.println(sb, "codecConfigPriorities:");
            for (BluetoothCodecConfig codecConfig : this.mA2dpCodecConfig.codecConfigPriorities()) {
                ProfileService.println(sb, "  " + codecConfig.getCodecName() + ": " + codecConfig.getCodecPriority());
            }
            ProfileService.println(sb, "mA2dpOffloadEnabled: " + this.mA2dpOffloadEnabled);
            if (this.mA2dpOffloadEnabled) {
                ProfileService.println(sb, "codecConfigOffloading:");
                for (BluetoothCodecConfig bluetoothCodecConfig : this.mA2dpCodecConfig.codecConfigOffloading()) {
                    ProfileService.println(sb, "  " + bluetoothCodecConfig);
                }
            }
        } else {
            ProfileService.println(sb, "mA2dpCodecConfig: null");
        }
        for (A2dpStateMachine sm : this.mStateMachines.values()) {
            sm.dump(sb);
        }
    }
}
