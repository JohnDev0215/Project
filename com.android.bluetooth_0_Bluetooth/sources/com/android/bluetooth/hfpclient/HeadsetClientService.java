package com.android.bluetooth.hfpclient;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadsetClientCall;
import android.bluetooth.IBluetoothHeadsetClient;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import com.android.bluetooth.Utils;
import com.android.bluetooth.btservice.AdapterService;
import com.android.bluetooth.btservice.ProfileService;
import com.android.bluetooth.hfpclient.HeadsetClientHandler;
import com.android.bluetooth.hfpclient.connserv.HfpClientConnectionService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class HeadsetClientService extends ProfileService {
    private static final boolean DBG = false;
    public static final String HFP_CLIENT_STOP_TAG = "hfp_client_stop_tag";
    private static final int MAX_STATE_MACHINES_POSSIBLE = 100;
    private static final String TAG = "HeadsetClientService";
    private static HeadsetClientService sHeadsetClientService;
    private final HashMap<BluetoothDevice, HeadsetClientStateMachine> mStateMachineMap = new HashMap<>();
    private NativeInterface mNativeInterface = null;
    private HandlerThread mSmThread = null;
    private HeadsetClientStateMachineFactory mSmFactory = null;
    private AudioManager mAudioManager = null;
    private final Object mStartStopLock = new Object();
    private HeadsetClientHandler mHandler = null;
    private final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() { // from class: com.android.bluetooth.hfpclient.HeadsetClientService.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("android.bluetooth.headsetclient.profile.action.VENDOR_SPECIFIC_EVENT")) {
                Bundle extras = (Bundle) intent.getExtra(HeadsetClientHandler.EXTRA_CUSTOM_ACTION);
                HeadsetClientService.this.mHandler.obtainMessage(1, extras).sendToTarget();
            }
        }
    };

    @Override // com.android.bluetooth.btservice.ProfileService
    public ProfileService.IProfileServiceBinder initBinder() {
        return new BluetoothHeadsetClientBinder(this);
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected boolean start() {
        synchronized (this.mStartStopLock) {
            if (getHeadsetClientService() != null) {
                Log.w(TAG, "start(): start called without stop");
                return false;
            }
            NativeInterface nativeInterface = NativeInterface.getInstance();
            this.mNativeInterface = nativeInterface;
            nativeInterface.initialize();
            AudioManager audioManager = (AudioManager) getSystemService("audio");
            this.mAudioManager = audioManager;
            if (audioManager == null) {
                Log.e(TAG, "AudioManager service doesn't exist?");
            } else {
                audioManager.setParameters("hfp_enable=false");
            }
            this.mSmFactory = new HeadsetClientStateMachineFactory();
            synchronized (this.mStateMachineMap) {
                this.mStateMachineMap.clear();
            }
            IntentFilter filter = new IntentFilter();
            filter.addAction("android.bluetooth.headsetclient.profile.action.VENDOR_SPECIFIC_EVENT");
            try {
                registerReceiver(this.mBroadcastReceiver, filter);
            } catch (Exception e) {
                Log.w(TAG, "Unable to register broadcat receiver", e);
            }
            Intent startIntent = new Intent(this, (Class<?>) HfpClientConnectionService.class);
            startService(startIntent);
            HandlerThread handlerThread = new HandlerThread("HeadsetClient.SM");
            this.mSmThread = handlerThread;
            handlerThread.start();
            setHeadsetClientService(this);
            this.mHandler = new HeadsetClientHandler.Builder().setContext(this).build();
            return true;
        }
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected boolean stop() {
        synchronized (this.mStartStopLock) {
            synchronized (HeadsetClientService.class) {
                if (sHeadsetClientService == null) {
                    Log.w(TAG, "stop() called without start()");
                    return false;
                }
                Intent stopIntent = new Intent(this, (Class<?>) HfpClientConnectionService.class);
                sHeadsetClientService.stopService(stopIntent);
                setHeadsetClientService(null);
                unregisterReceiver(this.mBroadcastReceiver);
                synchronized (this.mStateMachineMap) {
                    Iterator<Map.Entry<BluetoothDevice, HeadsetClientStateMachine>> it = this.mStateMachineMap.entrySet().iterator();
                    while (it.hasNext()) {
                        HeadsetClientStateMachine sm = this.mStateMachineMap.get(it.next().getKey());
                        sm.doQuit();
                        it.remove();
                    }
                }
                this.mSmThread.quit();
                this.mSmThread = null;
                this.mNativeInterface.cleanup();
                this.mNativeInterface = null;
                return true;
            }
        }
    }

    private static class BluetoothHeadsetClientBinder extends IBluetoothHeadsetClient.Stub implements ProfileService.IProfileServiceBinder {
        private HeadsetClientService mService;

        BluetoothHeadsetClientBinder(HeadsetClientService svc) {
            this.mService = svc;
        }

        @Override // com.android.bluetooth.btservice.ProfileService.IProfileServiceBinder
        public void cleanup() {
            this.mService = null;
        }

        private HeadsetClientService getService() {
            if (!Utils.checkCaller()) {
                Log.w(HeadsetClientService.TAG, "HeadsetClient call not allowed for non-active user");
                return null;
            }
            HeadsetClientService headsetClientService = this.mService;
            if (headsetClientService != null && headsetClientService.isAvailable()) {
                return this.mService;
            }
            Log.e(HeadsetClientService.TAG, "HeadsetClientService is not available.");
            return null;
        }

        public boolean connect(BluetoothDevice device) {
            HeadsetClientService service = getService();
            if (service == null) {
                return false;
            }
            return service.connect(device);
        }

        public boolean disconnect(BluetoothDevice device) {
            HeadsetClientService service = getService();
            if (service == null) {
                return false;
            }
            return service.disconnect(device);
        }

        public List<BluetoothDevice> getConnectedDevices() {
            HeadsetClientService service = getService();
            if (service == null) {
                return new ArrayList(0);
            }
            return service.getConnectedDevices();
        }

        public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states) {
            HeadsetClientService service = getService();
            if (service != null) {
                return service.getDevicesMatchingConnectionStates(states);
            }
            return new ArrayList(0);
        }

        public int getConnectionState(BluetoothDevice device) {
            HeadsetClientService service = getService();
            if (service == null) {
                return 0;
            }
            return service.getConnectionState(device);
        }

        public boolean setConnectionPolicy(BluetoothDevice device, int connectionPolicy) {
            HeadsetClientService service = getService();
            if (service == null) {
                return false;
            }
            return service.setConnectionPolicy(device, connectionPolicy);
        }

        public boolean setConnectionPolicy2(BluetoothDevice device, int connectionPolicy) {
            HeadsetClientService service = getService();
            if (service == null) {
                return false;
            }
            return service.setConnectionPolicy2(device, connectionPolicy);
        }

        public int getConnectionPolicy(BluetoothDevice device) {
            HeadsetClientService service = getService();
            if (service == null) {
                return -1;
            }
            return service.getConnectionPolicy(device);
        }

        public boolean startVoiceRecognition(BluetoothDevice device) {
            HeadsetClientService service = getService();
            if (service == null) {
                return false;
            }
            return service.startVoiceRecognition(device);
        }

        public boolean stopVoiceRecognition(BluetoothDevice device) {
            HeadsetClientService service = getService();
            if (service == null) {
                return false;
            }
            return service.stopVoiceRecognition(device);
        }

        public int getAudioState(BluetoothDevice device) {
            HeadsetClientService service = getService();
            Log.d(HeadsetClientService.TAG, "getAudioState   service: " + service);
            if (service == null) {
                return 0;
            }
            return service.getAudioState(device);
        }

        public void setAudioRouteAllowed(BluetoothDevice device, boolean allowed) {
            Log.e(HeadsetClientService.TAG, "setAudioRouteAllowed API not supported");
        }

        public boolean getAudioRouteAllowed(BluetoothDevice device) {
            Log.e(HeadsetClientService.TAG, "getAudioRouteAllowed API not supported");
            return false;
        }

        public boolean connectAudio(BluetoothDevice device) {
            HeadsetClientService service = getService();
            if (service == null) {
                return false;
            }
            return service.connectAudio(device);
        }

        public boolean disconnectAudio(BluetoothDevice device) {
            HeadsetClientService service = getService();
            if (service == null) {
                return false;
            }
            return service.disconnectAudio(device);
        }

        public boolean acceptCall(BluetoothDevice device, int flag) {
            HeadsetClientService service = getService();
            if (service == null) {
                return false;
            }
            return service.acceptCall(device, flag);
        }

        public boolean rejectCall(BluetoothDevice device) {
            HeadsetClientService service = getService();
            if (service == null) {
                return false;
            }
            return service.rejectCall(device);
        }

        public boolean holdCall(BluetoothDevice device) {
            HeadsetClientService service = getService();
            if (service == null) {
                return false;
            }
            return service.holdCall(device);
        }

        public boolean terminateCall(BluetoothDevice device, BluetoothHeadsetClientCall call) {
            HeadsetClientService service = getService();
            if (service != null) {
                return service.terminateCall(device, call != null ? call.getUUID() : null);
            }
            Log.w(HeadsetClientService.TAG, "service is null");
            return false;
        }

        public boolean explicitCallTransfer(BluetoothDevice device) {
            HeadsetClientService service = getService();
            if (service == null) {
                return false;
            }
            return service.explicitCallTransfer(device);
        }

        public boolean enterPrivateMode(BluetoothDevice device, int index) {
            HeadsetClientService service = getService();
            if (service == null) {
                return false;
            }
            return service.enterPrivateMode(device, index);
        }

        public BluetoothHeadsetClientCall dial(BluetoothDevice device, String number) {
            HeadsetClientService service = getService();
            if (service == null) {
                return null;
            }
            return service.dial(device, number);
        }

        public List<BluetoothHeadsetClientCall> getCurrentCalls(BluetoothDevice device) {
            HeadsetClientService service = getService();
            if (service == null) {
                return new ArrayList();
            }
            return service.getCurrentCalls(device);
        }

        public boolean sendDTMF(BluetoothDevice device, byte code) {
            HeadsetClientService service = getService();
            if (service == null) {
                return false;
            }
            return service.sendDTMF(device, code);
        }

        public boolean getLastVoiceTagNumber(BluetoothDevice device) {
            HeadsetClientService service = getService();
            if (service == null) {
                return false;
            }
            return service.getLastVoiceTagNumber(device);
        }

        public Bundle getCurrentAgEvents(BluetoothDevice device) {
            HeadsetClientService service = getService();
            if (service == null) {
                return null;
            }
            return service.getCurrentAgEvents(device);
        }

        public boolean sendVendorAtCommand(BluetoothDevice device, int vendorId, String atCommand) {
            HeadsetClientService service = getService();
            if (service == null) {
                return false;
            }
            return service.sendVendorAtCommand(device, vendorId, atCommand);
        }

        public Bundle getCurrentAgFeatures(BluetoothDevice device) {
            HeadsetClientService service = getService();
            if (service == null) {
                return null;
            }
            return service.getCurrentAgFeatures(device);
        }
    }

    public static synchronized HeadsetClientService getHeadsetClientService() {
        if (sHeadsetClientService == null) {
            Log.w(TAG, "getHeadsetClientService(): service is null");
            return null;
        }
        if (!sHeadsetClientService.isAvailable()) {
            Log.w(TAG, "getHeadsetClientService(): service is not available ");
            return null;
        }
        return sHeadsetClientService;
    }

    private static synchronized void setHeadsetClientService(HeadsetClientService instance) {
        sHeadsetClientService = instance;
    }

    public boolean connect(BluetoothDevice device) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN", "Need BLUETOOTH_ADMIN permission");
        if (getConnectionPolicy(device) == 0) {
            if (device == null) {
                Log.w(TAG, "Connection not allowed: < device is null> is CONNECTION_POLICY_FORBIDDEN");
            } else {
                Log.w(TAG, "Connection not allowed: <" + device.getAddressOnlyforLog() + "> is CONNECTION_POLICY_FORBIDDEN");
            }
            return false;
        }
        HeadsetClientStateMachine sm = getStateMachine(device, true);
        if (sm == null) {
            if (device == null) {
                Log.e(TAG, "Cannot allocate SM for device is null");
            } else {
                Log.e(TAG, "Cannot allocate SM for device " + device.getAddressOnlyforLog());
            }
            return false;
        }
        sm.sendMessage(1, device);
        return true;
    }

    public boolean disconnect(BluetoothDevice device) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN", "Need BLUETOOTH_ADMIN permission");
        HeadsetClientStateMachine sm = getStateMachine(device);
        if (sm == null) {
            if (device == null) {
                Log.e(TAG, "SM does not exist for device is null");
            } else {
                Log.e(TAG, "SM does not exist for device " + device.getAddressOnlyforLog());
            }
            return false;
        }
        int connectionState = sm.getConnectionState(device);
        if (connectionState != 2 && connectionState != 1) {
            return false;
        }
        sm.sendMessage(2, device);
        return true;
    }

    public List<BluetoothDevice> getConnectedDevices() {
        ArrayList<BluetoothDevice> connectedDevices = new ArrayList<>();
        synchronized (this.mStateMachineMap) {
            for (BluetoothDevice bd : this.mStateMachineMap.keySet()) {
                HeadsetClientStateMachine sm = this.mStateMachineMap.get(bd);
                if (sm != null && sm.getConnectionState(bd) == 2) {
                    connectedDevices.add(bd);
                }
            }
        }
        return connectedDevices;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states) {
        List<BluetoothDevice> devices = new ArrayList<>();
        synchronized (this.mStateMachineMap) {
            for (BluetoothDevice bd : this.mStateMachineMap.keySet()) {
                for (int state : states) {
                    HeadsetClientStateMachine sm = this.mStateMachineMap.get(bd);
                    if (sm != null && sm.getConnectionState(bd) == state) {
                        devices.add(bd);
                    }
                }
            }
        }
        return devices;
    }

    public int getConnectionState(BluetoothDevice device) {
        HeadsetClientStateMachine sm = this.mStateMachineMap.get(device);
        if (sm != null) {
            return sm.getConnectionState(device);
        }
        return 0;
    }

    public boolean setConnectionPolicy(BluetoothDevice device, int connectionPolicy) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN", "Need BLUETOOTH_ADMIN permission");
        AdapterService.getAdapterService().getDatabase().setProfileConnectionPolicy(device, 16, connectionPolicy);
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
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN", "Need BLUETOOTH_ADMIN permission");
        AdapterService.getAdapterService().getDatabase().setProfileConnectionPolicy(device, 16, connectionPolicy);
        return true;
    }

    public int getConnectionPolicy(BluetoothDevice device) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN", "Need BLUETOOTH_ADMIN permission");
        return AdapterService.getAdapterService().getDatabase().getProfileConnectionPolicy(device, 16);
    }

    boolean startVoiceRecognition(BluetoothDevice device) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        HeadsetClientStateMachine sm = getStateMachine(device);
        if (sm == null) {
            if (device == null) {
                Log.e(TAG, "SM does not exist for device is null");
            } else {
                Log.e(TAG, "SM does not exist for device " + device.getAddressOnlyforLog());
            }
            return false;
        }
        int connectionState = sm.getConnectionState(device);
        if (connectionState != 2) {
            return false;
        }
        sm.sendMessage(5);
        return true;
    }

    boolean stopVoiceRecognition(BluetoothDevice device) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        HeadsetClientStateMachine sm = getStateMachine(device);
        if (sm == null) {
            if (device == null) {
                Log.e(TAG, "SM does not exist for device is null");
            } else {
                Log.e(TAG, "SM does not exist for device " + device.getAddressOnlyforLog());
            }
            return false;
        }
        int connectionState = sm.getConnectionState(device);
        if (connectionState != 2) {
            return false;
        }
        sm.sendMessage(6);
        return true;
    }

    int getAudioState(BluetoothDevice device) {
        HeadsetClientStateMachine sm = this.mStateMachineMap.get(device);
        Log.d(TAG, "getAudioState\u3000sm: " + sm);
        if (sm == null) {
            if (device == null) {
                Log.e(TAG, "SM does not exist for device is null");
                return -1;
            }
            Log.e(TAG, "SM does not exist for device " + device.getAddressOnlyforLog());
            return -1;
        }
        return sm.getAudioState(device);
    }

    boolean connectAudio(BluetoothDevice device) {
        Log.w(TAG, "connectAudio pid: " + Binder.getCallingPid());
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN", "Need BLUETOOTH_ADMIN permission");
        HeadsetClientStateMachine sm = getStateMachine(device);
        if (sm == null) {
            if (device == null) {
                Log.e(TAG, "SM does not exist for device is null");
            } else {
                Log.e(TAG, "SM does not exist for device " + device.getAddressOnlyforLog());
            }
            return false;
        }
        if (!sm.isConnected()) {
            Log.w(TAG, "connectAudio is not Connected");
            return false;
        }
        if (sm.isAudioOn()) {
            Log.w(TAG, "connectAudio current isAudioOn");
            return false;
        }
        sm.sendMessage(3);
        return true;
    }

    boolean disconnectAudio(BluetoothDevice device) {
        Log.w(TAG, "disconnectAudio pid: " + Binder.getCallingPid());
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN", "Need BLUETOOTH_ADMIN permission");
        HeadsetClientStateMachine sm = getStateMachine(device);
        if (sm == null) {
            if (device == null) {
                Log.e(TAG, "SM does not exist for device is null");
            } else {
                Log.e(TAG, "SM does not exist for device " + device.getAddressOnlyforLog());
            }
            return false;
        }
        if (!sm.isAudioOn()) {
            Log.w(TAG, "disconnectAudio is not AudioOn");
            return false;
        }
        sm.sendMessage(4);
        return true;
    }

    boolean holdCall(BluetoothDevice device) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        HeadsetClientStateMachine sm = getStateMachine(device);
        if (sm == null) {
            if (device == null) {
                Log.e(TAG, "SM does not exist for device is null");
            } else {
                Log.e(TAG, "SM does not exist for device " + device.getAddressOnlyforLog());
            }
            return false;
        }
        int connectionState = sm.getConnectionState(device);
        if (connectionState != 2 && connectionState != 1) {
            return false;
        }
        Message msg = sm.obtainMessage(14);
        sm.sendMessage(msg);
        return true;
    }

    boolean acceptCall(BluetoothDevice device, int flag) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        synchronized (this.mStateMachineMap) {
            for (Map.Entry<BluetoothDevice, HeadsetClientStateMachine> entry : this.mStateMachineMap.entrySet()) {
                if (entry.getValue() != null && !entry.getKey().equals(device)) {
                    int connectionState = entry.getValue().getConnectionState(entry.getKey());
                    if (connectionState == 2) {
                        entry.getValue().obtainMessage(15).sendToTarget();
                    }
                }
            }
        }
        HeadsetClientStateMachine sm = getStateMachine(device);
        if (sm == null) {
            if (device == null) {
                Log.e(TAG, "SM does not exist for device is null");
            } else {
                Log.e(TAG, "SM does not exist for device " + device.getAddressOnlyforLog());
            }
            return false;
        }
        int connectionState2 = sm.getConnectionState(device);
        if (connectionState2 != 2) {
            return false;
        }
        Message msg = sm.obtainMessage(12);
        msg.arg1 = flag;
        sm.sendMessage(msg);
        return true;
    }

    boolean rejectCall(BluetoothDevice device) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        HeadsetClientStateMachine sm = getStateMachine(device);
        if (sm == null) {
            if (device == null) {
                Log.e(TAG, "SM does not exist for device is null");
            } else {
                Log.e(TAG, "SM does not exist for device " + device.getAddressOnlyforLog());
            }
            return false;
        }
        int connectionState = sm.getConnectionState(device);
        if (connectionState != 2 && connectionState != 1) {
            return false;
        }
        Message msg = sm.obtainMessage(13);
        sm.sendMessage(msg);
        return true;
    }

    boolean terminateCall(BluetoothDevice device, UUID uuid) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        HeadsetClientStateMachine sm = getStateMachine(device);
        if (sm == null) {
            if (device == null) {
                Log.e(TAG, "SM does not exist for device is null");
            } else {
                Log.e(TAG, "SM does not exist for device " + device.getAddressOnlyforLog());
            }
            return false;
        }
        int connectionState = sm.getConnectionState(device);
        if (connectionState != 2 && connectionState != 1) {
            return false;
        }
        Message msg = sm.obtainMessage(15);
        msg.obj = uuid;
        sm.sendMessage(msg);
        return true;
    }

    boolean enterPrivateMode(BluetoothDevice device, int index) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        HeadsetClientStateMachine sm = getStateMachine(device);
        if (sm == null) {
            if (device == null) {
                Log.e(TAG, "SM does not exist for device is null");
            } else {
                Log.e(TAG, "SM does not exist for device " + device.getAddressOnlyforLog());
            }
            return false;
        }
        int connectionState = sm.getConnectionState(device);
        if (connectionState != 2 && connectionState != 1) {
            return false;
        }
        Message msg = sm.obtainMessage(16);
        msg.arg1 = index;
        sm.sendMessage(msg);
        return true;
    }

    boolean releaseCall(BluetoothDevice device, int index) {
        Log.d(TAG, "Enter releaseCall");
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        HeadsetClientStateMachine sm = getStateMachine(device);
        if (sm == null) {
            if (device == null) {
                Log.e(TAG, "SM does not exist for device is null");
            } else {
                Log.e(TAG, "SM does not exist for device " + device.getAddressOnlyforLog());
            }
            return false;
        }
        int connectionState = sm.getConnectionState(device);
        if (connectionState != 2 && connectionState != 1) {
            return false;
        }
        Message msg = sm.obtainMessage(22);
        msg.arg1 = index;
        sm.sendMessage(msg);
        Log.d(TAG, "Exit releaseCall");
        return true;
    }

    BluetoothHeadsetClientCall dial(BluetoothDevice device, String number) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        HeadsetClientStateMachine sm = getStateMachine(device);
        if (sm == null) {
            if (device == null) {
                Log.e(TAG, "SM does not exist for device is null");
            } else {
                Log.e(TAG, "SM does not exist for device " + device.getAddressOnlyforLog());
            }
            return null;
        }
        int connectionState = sm.getConnectionState(device);
        if (connectionState != 2 && connectionState != 1) {
            return null;
        }
        BluetoothHeadsetClientCall call = new BluetoothHeadsetClientCall(device, -1, 2, number, false, true, sm.getInBandRing());
        Message msg = sm.obtainMessage(10);
        msg.obj = call;
        sm.sendMessage(msg);
        return call;
    }

    public boolean sendDTMF(BluetoothDevice device, byte code) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        HeadsetClientStateMachine sm = getStateMachine(device);
        if (sm == null) {
            if (device == null) {
                Log.e(TAG, "SM does not exist for device is null");
            } else {
                Log.e(TAG, "SM does not exist for device " + device.getAddressOnlyforLog());
            }
            return false;
        }
        int connectionState = sm.getConnectionState(device);
        if (connectionState != 2 && connectionState != 1) {
            return false;
        }
        Message msg = sm.obtainMessage(17);
        msg.arg1 = code;
        sm.sendMessage(msg);
        return true;
    }

    public boolean getLastVoiceTagNumber(BluetoothDevice device) {
        return false;
    }

    public List<BluetoothHeadsetClientCall> getCurrentCalls(BluetoothDevice device) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        HeadsetClientStateMachine sm = getStateMachine(device);
        if (sm == null) {
            if (device == null) {
                Log.e(TAG, "SM does not exist for device is null");
            } else {
                Log.e(TAG, "SM does not exist for device " + device.getAddressOnlyforLog());
            }
            return null;
        }
        int connectionState = sm.getConnectionState(device);
        if (connectionState != 2) {
            return null;
        }
        return sm.getCurrentCalls();
    }

    public boolean explicitCallTransfer(BluetoothDevice device) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        HeadsetClientStateMachine sm = getStateMachine(device);
        if (sm == null) {
            if (device == null) {
                Log.e(TAG, "SM does not exist for device is null");
            } else {
                Log.e(TAG, "SM does not exist for device " + device.getAddressOnlyforLog());
            }
            return false;
        }
        int connectionState = sm.getConnectionState(device);
        if (connectionState != 2 && connectionState != 1) {
            return false;
        }
        Message msg = sm.obtainMessage(18);
        sm.sendMessage(msg);
        return true;
    }

    public boolean sendVendorAtCommand(BluetoothDevice device, int vendorId, String atCommand) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        HeadsetClientStateMachine sm = getStateMachine(device);
        if (sm == null) {
            if (device == null) {
                Log.e(TAG, "SM does not exist for device is null");
            } else {
                Log.e(TAG, "SM does not exist for device " + device.getAddressOnlyforLog());
            }
            return false;
        }
        int connectionState = sm.getConnectionState(device);
        if (connectionState != 2) {
            return false;
        }
        Message msg = sm.obtainMessage(21, vendorId, 0, atCommand);
        sm.sendMessage(msg);
        return true;
    }

    public Bundle getCurrentAgEvents(BluetoothDevice device) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        HeadsetClientStateMachine sm = getStateMachine(device);
        if (sm == null) {
            if (device == null) {
                Log.e(TAG, "SM does not exist for device is null");
            } else {
                Log.e(TAG, "SM does not exist for device " + device.getAddressOnlyforLog());
            }
            return null;
        }
        int connectionState = sm.getConnectionState(device);
        if (connectionState != 2) {
            return null;
        }
        return sm.getCurrentAgEvents();
    }

    public Bundle getCurrentAgFeatures(BluetoothDevice device) {
        enforceCallingOrSelfPermission(ProfileService.BLUETOOTH_PERM, "Need BLUETOOTH permission");
        HeadsetClientStateMachine sm = getStateMachine(device);
        if (sm == null) {
            if (device == null) {
                Log.e(TAG, "SM does not exist for device is null");
            } else {
                Log.e(TAG, "SM does not exist for device " + device.getAddressOnlyforLog());
            }
            return null;
        }
        int connectionState = sm.getConnectionState(device);
        if (connectionState != 2) {
            return null;
        }
        return sm.getCurrentAgFeatures();
    }

    public void messageFromNative(StackEvent stackEvent) {
        Objects.requireNonNull(stackEvent.device, "Device should never be null, event: " + stackEvent);
        HeadsetClientStateMachine sm = getStateMachine(stackEvent.device, isConnectionEvent(stackEvent));
        if (sm == null) {
            throw new IllegalStateException("State machine not found for stack event: " + stackEvent);
        }
        sm.sendMessage(100, stackEvent);
    }

    private boolean isConnectionEvent(StackEvent stackEvent) {
        if (stackEvent.type == 1) {
            return stackEvent.valueInt == 1 || stackEvent.valueInt == 2;
        }
        return false;
    }

    private HeadsetClientStateMachine getStateMachine(BluetoothDevice device) {
        return getStateMachine(device, false);
    }

    private HeadsetClientStateMachine getStateMachine(BluetoothDevice device, boolean isConnectionEvent) {
        HeadsetClientStateMachine sm;
        if (device == null) {
            Log.e(TAG, "getStateMachine failed: Device cannot be null");
            return null;
        }
        synchronized (this.mStateMachineMap) {
            sm = this.mStateMachineMap.get(device);
        }
        if (sm == null && isConnectionEvent && (sm = allocateStateMachine(device)) == null) {
            Log.e(TAG, "SM could not be allocated for device " + device.getAddressOnlyforLog());
        }
        return sm;
    }

    private HeadsetClientStateMachine allocateStateMachine(BluetoothDevice device) {
        if (device == null) {
            Log.e(TAG, "allocateStateMachine failed: Device cannot be null");
            return null;
        }
        if (getHeadsetClientService() == null) {
            Log.e(TAG, "Cannot allocate SM if service has begun stopping or has not completed startup.");
            return null;
        }
        synchronized (this.mStateMachineMap) {
            HeadsetClientStateMachine sm = this.mStateMachineMap.get(device);
            if (sm != null) {
                return sm;
            }
            if (this.mStateMachineMap.keySet().size() > 100) {
                Log.e(TAG, "Max state machines reached, possible DOS attack 100");
                return null;
            }
            Log.d(TAG, "Creating a new state machine");
            HeadsetClientStateMachine sm2 = this.mSmFactory.make(this, this.mSmThread, this.mNativeInterface);
            this.mStateMachineMap.put(device, sm2);
            return sm2;
        }
    }

    boolean isScoRouted() {
        synchronized (this.mStateMachineMap) {
            for (Map.Entry<BluetoothDevice, HeadsetClientStateMachine> entry : this.mStateMachineMap.entrySet()) {
                if (entry.getValue() != null) {
                    int audioState = entry.getValue().getAudioState(entry.getKey());
                    if (audioState == 2) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    public void dump(StringBuilder sb) {
        super.dump(sb);
        synchronized (this.mStateMachineMap) {
            for (HeadsetClientStateMachine sm : this.mStateMachineMap.values()) {
                if (sm != null) {
                    sm.dump(sb);
                }
            }
        }
    }

    protected Map<BluetoothDevice, HeadsetClientStateMachine> getStateMachineMap() {
        HashMap<BluetoothDevice, HeadsetClientStateMachine> map;
        synchronized (this.mStateMachineMap) {
            map = this.mStateMachineMap;
        }
        return map;
    }

    protected void setSMFactory(HeadsetClientStateMachineFactory factory) {
        this.mSmFactory = factory;
    }

    protected AudioManager getAudioManager() {
        return this.mAudioManager;
    }
}
