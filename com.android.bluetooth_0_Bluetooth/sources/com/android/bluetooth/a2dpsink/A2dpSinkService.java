package com.android.bluetooth.a2dpsink;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothAudioConfig;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.IBluetoothA2dpSink;
import android.util.Log;
import com.android.bluetooth.Utils;
import com.android.bluetooth.btservice.AdapterService;
import com.android.bluetooth.btservice.ProfileService;
import com.android.bluetooth.opp.BluetoothShare;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public class A2dpSinkService extends ProfileService {
    static final int MAXIMUM_CONNECTED_DEVICES = 1;
    private static A2dpSinkService sService;
    private A2dpSinkStreamHandler mA2dpSinkStreamHandler;
    private static final String TAG = "A2dpSinkService";
    private static final boolean DBG = Log.isLoggable(TAG, 3);
    protected Map<BluetoothDevice, A2dpSinkStateMachine> mDeviceStateMap = new ConcurrentHashMap(1);
    private final Object mStreamHandlerLock = new Object();
    private final BluetoothAdapter mAdapter = BluetoothAdapter.getDefaultAdapter();

    private static native void classInitNative();

    private native void cleanupNative();

    private native void initNative();

    native boolean connectA2dpNative(byte[] bArr);

    native boolean disconnectA2dpNative(byte[] bArr);

    public native void informAudioFocusStateNative(int i);

    public native void informAudioTrackGainNative(float f);

    public native boolean setActiveDeviceNative(byte[] bArr);

    static {
        classInitNative();
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected boolean start() {
        synchronized (this.mStreamHandlerLock) {
            this.mA2dpSinkStreamHandler = new A2dpSinkStreamHandler(this, this);
        }
        initNative();
        setA2dpSinkService(this);
        return true;
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected boolean stop() {
        setA2dpSinkService(null);
        cleanupNative();
        for (A2dpSinkStateMachine stateMachine : this.mDeviceStateMap.values()) {
            stateMachine.quitNow();
        }
        this.mDeviceStateMap.clear();
        synchronized (this.mStreamHandlerLock) {
            if (this.mA2dpSinkStreamHandler != null) {
                this.mA2dpSinkStreamHandler.cleanup();
                this.mA2dpSinkStreamHandler = null;
            }
        }
        return true;
    }

    public static synchronized A2dpSinkService getA2dpSinkService() {
        return sService;
    }

    public static synchronized void setA2dpSinkService(A2dpSinkService service) {
        sService = service;
    }

    public void requestAudioFocus(BluetoothDevice device, boolean request) {
        synchronized (this.mStreamHandlerLock) {
            if (this.mA2dpSinkStreamHandler == null) {
                return;
            }
            this.mA2dpSinkStreamHandler.requestAudioFocusIfNone();
        }
    }

    public int getFocusState() {
        synchronized (this.mStreamHandlerLock) {
            if (this.mA2dpSinkStreamHandler == null) {
                return -1;
            }
            return this.mA2dpSinkStreamHandler.getFocusState();
        }
    }

    boolean isA2dpPlaying(BluetoothDevice device) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_PRIVILEGED", "Need BLUETOOTH_PRIVILEGED permission");
        synchronized (this.mStreamHandlerLock) {
            if (this.mA2dpSinkStreamHandler == null) {
                return false;
            }
            return this.mA2dpSinkStreamHandler.isPlaying();
        }
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected ProfileService.IProfileServiceBinder initBinder() {
        return new A2dpSinkServiceBinder(this);
    }

    private static class A2dpSinkServiceBinder extends IBluetoothA2dpSink.Stub implements ProfileService.IProfileServiceBinder {
        private A2dpSinkService mService;

        private A2dpSinkService getService() {
            if (!Utils.checkCaller()) {
                Log.w(A2dpSinkService.TAG, "A2dp call not allowed for non-active user");
                return null;
            }
            A2dpSinkService a2dpSinkService = this.mService;
            if (a2dpSinkService != null) {
                return a2dpSinkService;
            }
            return null;
        }

        A2dpSinkServiceBinder(A2dpSinkService svc) {
            this.mService = svc;
        }

        @Override // com.android.bluetooth.btservice.ProfileService.IProfileServiceBinder
        public void cleanup() {
            this.mService = null;
        }

        public boolean connect(BluetoothDevice device) {
            A2dpSinkService service = getService();
            if (service == null) {
                return false;
            }
            return service.connect(device);
        }

        public boolean disconnect(BluetoothDevice device) {
            A2dpSinkService service = getService();
            if (service == null) {
                return false;
            }
            return service.disconnect(device);
        }

        public List<BluetoothDevice> getConnectedDevices() {
            A2dpSinkService service = getService();
            if (service == null) {
                return new ArrayList(0);
            }
            return service.getConnectedDevices();
        }

        public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states) {
            A2dpSinkService service = getService();
            if (service == null) {
                return new ArrayList(0);
            }
            return service.getDevicesMatchingConnectionStates(states);
        }

        public int getConnectionState(BluetoothDevice device) {
            A2dpSinkService service = getService();
            if (service == null) {
                return 0;
            }
            return service.getConnectionState(device);
        }

        public boolean setConnectionPolicy(BluetoothDevice device, int connectionPolicy) {
            A2dpSinkService service = getService();
            if (service == null) {
                return false;
            }
            return service.setConnectionPolicy(device, connectionPolicy);
        }

        public boolean setConnectionPolicy2(BluetoothDevice device, int connectionPolicy) {
            A2dpSinkService service = getService();
            if (service == null) {
                return false;
            }
            return service.setConnectionPolicy2(device, connectionPolicy);
        }

        public int getConnectionPolicy(BluetoothDevice device) {
            A2dpSinkService service = getService();
            if (service == null) {
                return -1;
            }
            return service.getConnectionPolicy(device);
        }

        public boolean isA2dpPlaying(BluetoothDevice device) {
            A2dpSinkService service = getService();
            if (service == null) {
                return false;
            }
            return service.isA2dpPlaying(device);
        }

        public BluetoothAudioConfig getAudioConfig(BluetoothDevice device) {
            A2dpSinkService service = getService();
            if (service == null) {
                return null;
            }
            return service.getAudioConfig(device);
        }
    }

    public boolean connect(BluetoothDevice device) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_PRIVILEGED", "Need BLUETOOTH_PRIVILEGED permission");
        if (device == null) {
            throw new IllegalArgumentException("Null device");
        }
        if (DBG) {
            StringBuilder sb = new StringBuilder();
            dump(sb);
            Log.d(TAG, " connect device: " + device.getAddressOnlyforLog() + ", InstanceMap start state: " + sb.toString());
        }
        if (getConnectionPolicy(device) == 0) {
            Log.w(TAG, "Connection not allowed: <" + device.getAddressOnlyforLog() + "> is CONNECTION_POLICY_FORBIDDEN");
            return false;
        }
        A2dpSinkStateMachine stateMachine = getOrCreateStateMachine(device);
        if (stateMachine != null) {
            stateMachine.connect();
            return true;
        }
        Log.e(TAG, "Maxed out on the number of allowed A2DP Sink connections. Connect request rejected on " + device.getAddressOnlyforLog());
        return false;
    }

    public boolean disconnect(BluetoothDevice device) {
        int connectionState;
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN", "Need BLUETOOTH ADMIN permission");
        if (DBG) {
            StringBuilder sb = new StringBuilder();
            dump(sb);
            Log.d(TAG, "A2DP disconnect device: " + device.getAddressOnlyforLog() + ", InstanceMap start state: " + sb.toString());
        }
        A2dpSinkStateMachine stateMachine = this.mDeviceStateMap.get(device);
        if (stateMachine == null || (connectionState = stateMachine.getState()) == 0 || connectionState == 3) {
            return false;
        }
        stateMachine.disconnect();
        return true;
    }

    void removeStateMachine(A2dpSinkStateMachine stateMachine) {
        this.mDeviceStateMap.remove(stateMachine.getDevice());
    }

    public List<BluetoothDevice> getConnectedDevices() {
        return getDevicesMatchingConnectionStates(new int[]{2});
    }

    protected A2dpSinkStateMachine getOrCreateStateMachine(BluetoothDevice device) {
        A2dpSinkStateMachine newStateMachine = new A2dpSinkStateMachine(device, this);
        A2dpSinkStateMachine existingStateMachine = this.mDeviceStateMap.putIfAbsent(device, newStateMachine);
        if (existingStateMachine == null) {
            newStateMachine.start();
            return newStateMachine;
        }
        return existingStateMachine;
    }

    List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states) {
        if (DBG) {
            Log.d(TAG, "getDevicesMatchingConnectionStates" + Arrays.toString(states));
        }
        List<BluetoothDevice> deviceList = new ArrayList<>();
        Set<BluetoothDevice> bondedDevices = this.mAdapter.getBondedDevices();
        for (BluetoothDevice device : bondedDevices) {
            int connectionState = getConnectionState(device);
            if (DBG) {
                Log.d(TAG, "Device: " + device.getAddressOnlyforLog() + "State: " + connectionState);
            }
            for (int i : states) {
                if (connectionState == i) {
                    deviceList.add(device);
                }
            }
        }
        if (DBG) {
            Log.d(TAG, deviceList.toString());
        }
        Log.d(TAG, "GetDevicesDone");
        return deviceList;
    }

    public int getConnectionState(BluetoothDevice device) {
        A2dpSinkStateMachine stateMachine = this.mDeviceStateMap.get(device);
        if (stateMachine == null) {
            return 0;
        }
        return stateMachine.getState();
    }

    public boolean setConnectionPolicy(BluetoothDevice device, int connectionPolicy) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_PRIVILEGED", "Need BLUETOOTH_PRIVILEGED permission");
        if (DBG) {
            Log.d(TAG, "Saved connectionPolicy " + device.getAddressOnlyforLog() + " = " + connectionPolicy);
        }
        AdapterService.getAdapterService().getDatabase().setProfileConnectionPolicy(device, 11, connectionPolicy);
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

    public boolean setConnectionPolicy2(BluetoothDevice device, int connectionPolicy) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_PRIVILEGED", "Need BLUETOOTH_PRIVILEGED permission");
        if (DBG) {
            Log.d(TAG, "Saved connectionPolicy2 " + device.getAddressOnlyforLog() + " = " + connectionPolicy);
        }
        AdapterService.getAdapterService().getDatabase().setProfileConnectionPolicy(device, 11, connectionPolicy);
        return true;
    }

    public int getConnectionPolicy(BluetoothDevice device) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_PRIVILEGED", "Need BLUETOOTH_PRIVILEGED permission");
        return AdapterService.getAdapterService().getDatabase().getProfileConnectionPolicy(device, 11);
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    public void dump(StringBuilder sb) {
        super.dump(sb);
        ProfileService.println(sb, "Devices Tracked = " + this.mDeviceStateMap.size());
        for (A2dpSinkStateMachine stateMachine : this.mDeviceStateMap.values()) {
            ProfileService.println(sb, "==== StateMachine for " + stateMachine.getDevice() + " ====");
            stateMachine.dump(sb);
        }
    }

    BluetoothAudioConfig getAudioConfig(BluetoothDevice device) {
        A2dpSinkStateMachine stateMachine = this.mDeviceStateMap.get(device);
        if (stateMachine == null) {
            return null;
        }
        return stateMachine.getAudioConfig();
    }

    private void onConnectionStateChanged(byte[] address, int state) {
        StackEvent event = StackEvent.connectionStateChanged(getDevice(address), state);
        A2dpSinkStateMachine stateMachine = getOrCreateStateMachine(event.mDevice);
        stateMachine.sendMessage(BluetoothShare.STATUS_SUCCESS, event);
    }

    private void onAudioStateChanged(byte[] address, int state) {
        synchronized (this.mStreamHandlerLock) {
            if (this.mA2dpSinkStreamHandler == null) {
                Log.e(TAG, "Received audio state change before we've been started");
                return;
            }
            if (state == 2) {
                this.mA2dpSinkStreamHandler.obtainMessage(0).sendToTarget();
            } else if (state == 1 || state == 0) {
                this.mA2dpSinkStreamHandler.obtainMessage(1).sendToTarget();
            }
        }
    }

    private void onAudioConfigChanged(byte[] address, int sampleRate, int channelCount, int codecIndex) {
        StackEvent event = StackEvent.audioConfigChanged(getDevice(address), sampleRate, channelCount, codecIndex);
        A2dpSinkStateMachine stateMachine = getOrCreateStateMachine(event.mDevice);
        stateMachine.sendMessage(BluetoothShare.STATUS_SUCCESS, event);
    }
}
