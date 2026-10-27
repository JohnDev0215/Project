package com.android.bluetooth.mapclient;

import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothUuid;
import android.bluetooth.IBluetoothMapClient;
import android.bluetooth.SdpMasRecord;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.ParcelUuid;
import android.util.Log;
import com.android.bluetooth.Utils;
import com.android.bluetooth.btservice.AdapterService;
import com.android.bluetooth.btservice.ProfileService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public class MapClientService extends ProfileService {
    private static final String BLUETOOTH_PERM = "android.permission.BLUETOOTH";
    static final boolean DBG = false;
    static final int MAXIMUM_CONNECTED_DEVICES = 4;
    private static final String TAG = "MapClientService";
    static final boolean VDBG = false;
    private static MapClientService sMapClientService;
    private BluetoothAdapter mAdapter;
    private Map<BluetoothDevice, MceStateMachine> mMapInstanceMap = new ConcurrentHashMap(1);
    private MapBroadcastReceiver mMapReceiver;
    private MnsService mMnsServer;

    public static synchronized MapClientService getMapClientService() {
        if (sMapClientService == null) {
            Log.w(TAG, "getMapClientService(): service is null");
            return null;
        }
        if (!sMapClientService.isAvailable()) {
            Log.w(TAG, "getMapClientService(): service is not available ");
            return null;
        }
        return sMapClientService;
    }

    private static synchronized void setMapClientService(MapClientService instance) {
        sMapClientService = instance;
    }

    Map<BluetoothDevice, MceStateMachine> getInstanceMap() {
        return this.mMapInstanceMap;
    }

    public synchronized boolean connect(BluetoothDevice device) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_PRIVILEGED", "Need BLUETOOTH_PRIVILEGED permission");
        if (device == null) {
            throw new IllegalArgumentException("Null device");
        }
        if (getConnectionPolicy(device) == 0) {
            Log.w(TAG, "Connection not allowed: <" + device.getAddress() + "> is CONNECTION_POLICY_FORBIDDEN");
            return false;
        }
        MceStateMachine mapStateMachine = this.mMapInstanceMap.get(device);
        if (mapStateMachine == null) {
            if (this.mMapInstanceMap.size() < 4) {
                addDeviceToMapAndConnect(device);
                return true;
            }
            removeUncleanAccounts();
            if (this.mMapInstanceMap.size() < 4) {
                addDeviceToMapAndConnect(device);
                return true;
            }
            Log.e(TAG, "Maxed out on the number of allowed MAP connections. Connect request rejected on " + device);
            return false;
        }
        int state = getConnectionState(device);
        if (state != 2 && state != 1) {
            this.mMapInstanceMap.remove(device);
            addDeviceToMapAndConnect(device);
            return true;
        }
        Log.w(TAG, "Received connect request while already connecting/connected.");
        return true;
    }

    private synchronized void addDeviceToMapAndConnect(BluetoothDevice device) {
        MceStateMachine mapStateMachine = new MceStateMachine(this, device);
        this.mMapInstanceMap.put(device, mapStateMachine);
    }

    public synchronized boolean disconnect(BluetoothDevice device) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_PRIVILEGED", "Need BLUETOOTH_PRIVILEGED permission");
        MceStateMachine mapStateMachine = this.mMapInstanceMap.get(device);
        if (mapStateMachine == null) {
            return false;
        }
        int connectionState = mapStateMachine.getState();
        if (connectionState != 2 && connectionState != 1) {
            return false;
        }
        mapStateMachine.disconnect();
        return true;
    }

    public List<BluetoothDevice> getConnectedDevices() {
        return getDevicesMatchingConnectionStates(new int[]{2});
    }

    MceStateMachine getMceStateMachineForDevice(BluetoothDevice device) {
        return this.mMapInstanceMap.get(device);
    }

    public synchronized List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states) {
        List<BluetoothDevice> deviceList;
        deviceList = new ArrayList<>();
        Set<BluetoothDevice> bondedDevices = this.mAdapter.getBondedDevices();
        for (BluetoothDevice device : bondedDevices) {
            int connectionState = getConnectionState(device);
            for (int i : states) {
                if (connectionState == i) {
                    deviceList.add(device);
                }
            }
        }
        return deviceList;
    }

    public synchronized int getConnectionState(BluetoothDevice device) {
        MceStateMachine mapStateMachine;
        mapStateMachine = this.mMapInstanceMap.get(device);
        return mapStateMachine == null ? 0 : mapStateMachine.getState();
    }

    public boolean setConnectionPolicy(BluetoothDevice device, int connectionPolicy) {
        enforceCallingOrSelfPermission("android.permission.BLUETOOTH_PRIVILEGED", "Need BLUETOOTH_PRIVILEGED permission");
        AdapterService.getAdapterService().getDatabase().setProfileConnectionPolicy(device, 18, connectionPolicy);
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
        return AdapterService.getAdapterService().getDatabase().getProfileConnectionPolicy(device, 18);
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized boolean sendMessage(android.bluetooth.BluetoothDevice r3, android.net.Uri[] r4, java.lang.String r5, android.app.PendingIntent r6, android.app.PendingIntent r7) {
        /*
            r2 = this;
            monitor-enter(r2)
            java.util.Map<android.bluetooth.BluetoothDevice, com.android.bluetooth.mapclient.MceStateMachine> r0 = r2.mMapInstanceMap     // Catch: java.lang.Throwable -> L16
            java.lang.Object r0 = r0.get(r3)     // Catch: java.lang.Throwable -> L16
            com.android.bluetooth.mapclient.MceStateMachine r0 = (com.android.bluetooth.mapclient.MceStateMachine) r0     // Catch: java.lang.Throwable -> L16
            if (r0 == 0) goto L13
            boolean r1 = r0.sendMapMessage(r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L16
            if (r1 == 0) goto L13
            r1 = 1
            goto L14
        L13:
            r1 = 0
        L14:
            monitor-exit(r2)
            return r1
        L16:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.mapclient.MapClientService.sendMessage(android.bluetooth.BluetoothDevice, android.net.Uri[], java.lang.String, android.app.PendingIntent, android.app.PendingIntent):boolean");
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected ProfileService.IProfileServiceBinder initBinder() {
        return new Binder(this);
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected synchronized boolean start() {
        Log.e(TAG, "start()");
        if (this.mMnsServer == null) {
            MnsService mnsServiceNewMnsServiceInstance = MapUtils.newMnsServiceInstance(this);
            this.mMnsServer = mnsServiceNewMnsServiceInstance;
            if (mnsServiceNewMnsServiceInstance == null) {
                Log.w(TAG, "MnsService is *not* created!");
                return false;
            }
        }
        this.mAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mMapReceiver = new MapBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.bluetooth.device.action.SDP_RECORD");
        filter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
        registerReceiver(this.mMapReceiver, filter);
        removeUncleanAccounts();
        setMapClientService(this);
        return true;
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected synchronized boolean stop() {
        if (this.mMapReceiver != null) {
            unregisterReceiver(this.mMapReceiver);
            this.mMapReceiver = null;
        }
        if (this.mMnsServer != null) {
            this.mMnsServer.stop();
        }
        for (MceStateMachine stateMachine : this.mMapInstanceMap.values()) {
            if (stateMachine.getState() == 2) {
                stateMachine.disconnect();
            }
            stateMachine.doQuit();
        }
        return true;
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected void cleanup() {
        removeUncleanAccounts();
        setMapClientService(null);
    }

    public void cleanupDevice(BluetoothDevice device) {
        synchronized (this.mMapInstanceMap) {
            MceStateMachine stateMachine = this.mMapInstanceMap.get(device);
            if (stateMachine != null) {
                this.mMapInstanceMap.remove(device);
            }
        }
    }

    void removeUncleanAccounts() {
        Iterator<Map.Entry<BluetoothDevice, MceStateMachine>> it = this.mMapInstanceMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<BluetoothDevice, MceStateMachine> profileConnection = it.next();
            if (profileConnection.getValue().getState() == 0) {
                it.remove();
            }
        }
    }

    public synchronized boolean getUnreadMessages(BluetoothDevice device) {
        MceStateMachine mapStateMachine = this.mMapInstanceMap.get(device);
        if (mapStateMachine == null) {
            return false;
        }
        return mapStateMachine.getUnreadMessages();
    }

    public synchronized int getSupportedFeatures(BluetoothDevice device) {
        MceStateMachine mapStateMachine = this.mMapInstanceMap.get(device);
        if (mapStateMachine == null) {
            return 0;
        }
        return mapStateMachine.getSupportedFeatures();
    }

    public synchronized boolean setMessageStatus(BluetoothDevice device, String handle, int status) {
        MceStateMachine mapStateMachine = this.mMapInstanceMap.get(device);
        if (mapStateMachine == null) {
            return false;
        }
        return mapStateMachine.setMessageStatus(handle, status);
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    public void dump(StringBuilder sb) {
        super.dump(sb);
        for (MceStateMachine stateMachine : this.mMapInstanceMap.values()) {
            stateMachine.dump(sb);
        }
    }

    private static class Binder extends IBluetoothMapClient.Stub implements ProfileService.IProfileServiceBinder {
        private MapClientService mService;

        Binder(MapClientService service) {
            this.mService = service;
        }

        private MapClientService getService() {
            if (!Utils.checkCaller()) {
                Log.w(MapClientService.TAG, "MAP call not allowed for non-active user");
                return null;
            }
            MapClientService mapClientService = this.mService;
            if (mapClientService == null || !mapClientService.isAvailable()) {
                return null;
            }
            this.mService.enforceCallingOrSelfPermission("android.permission.BLUETOOTH", "Need BLUETOOTH permission");
            return this.mService;
        }

        @Override // com.android.bluetooth.btservice.ProfileService.IProfileServiceBinder
        public void cleanup() {
            this.mService = null;
        }

        public boolean isConnected(BluetoothDevice device) {
            MapClientService service = getService();
            return service != null && service.getConnectionState(device) == 2;
        }

        public boolean connect(BluetoothDevice device) {
            MapClientService service = getService();
            if (service == null) {
                return false;
            }
            return service.connect(device);
        }

        public boolean disconnect(BluetoothDevice device) {
            MapClientService service = getService();
            if (service == null) {
                return false;
            }
            return service.disconnect(device);
        }

        public List<BluetoothDevice> getConnectedDevices() {
            MapClientService service = getService();
            if (service == null) {
                return new ArrayList(0);
            }
            return service.getConnectedDevices();
        }

        public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states) {
            MapClientService service = getService();
            if (service == null) {
                return new ArrayList(0);
            }
            return service.getDevicesMatchingConnectionStates(states);
        }

        public int getConnectionState(BluetoothDevice device) {
            MapClientService service = getService();
            if (service == null) {
                return 0;
            }
            return service.getConnectionState(device);
        }

        public boolean setConnectionPolicy(BluetoothDevice device, int connectionPolicy) {
            MapClientService service = getService();
            if (service == null) {
                return false;
            }
            return service.setConnectionPolicy(device, connectionPolicy);
        }

        public int getConnectionPolicy(BluetoothDevice device) {
            MapClientService service = getService();
            if (service == null) {
                return -1;
            }
            return service.getConnectionPolicy(device);
        }

        public boolean sendMessage(BluetoothDevice device, Uri[] contacts, String message, PendingIntent sentIntent, PendingIntent deliveredIntent) {
            MapClientService service = getService();
            if (service == null) {
                return false;
            }
            this.mService.enforceCallingOrSelfPermission("android.permission.SEND_SMS", "Need SEND_SMS permission");
            return service.sendMessage(device, contacts, message, sentIntent, deliveredIntent);
        }

        public boolean getUnreadMessages(BluetoothDevice device) {
            MapClientService service = getService();
            if (service == null) {
                return false;
            }
            this.mService.enforceCallingOrSelfPermission("android.permission.READ_SMS", "Need READ_SMS permission");
            return service.getUnreadMessages(device);
        }

        public int getSupportedFeatures(BluetoothDevice device) {
            MapClientService service = getService();
            if (service == null) {
                return 0;
            }
            this.mService.enforceCallingOrSelfPermission("android.permission.BLUETOOTH", "Need BLUETOOTH permission");
            return service.getSupportedFeatures(device);
        }

        public boolean setMessageStatus(BluetoothDevice device, String handle, int status) {
            MapClientService service = getService();
            if (service == null) {
                return false;
            }
            this.mService.enforceCallingOrSelfPermission("android.permission.READ_SMS", "Need READ_SMS permission");
            return service.setMessageStatus(device, handle, status);
        }
    }

    private class MapBroadcastReceiver extends BroadcastReceiver {
        private MapBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (!action.equals("android.bluetooth.device.action.ACL_DISCONNECTED") && !action.equals("android.bluetooth.device.action.SDP_RECORD")) {
                return;
            }
            BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            if (device != null) {
                MceStateMachine stateMachine = (MceStateMachine) MapClientService.this.mMapInstanceMap.get(device);
                if (stateMachine == null) {
                    Log.e(MapClientService.TAG, "No Statemachine found for the device from broadcast");
                    return;
                }
                if (action.equals("android.bluetooth.device.action.ACL_DISCONNECTED") && stateMachine.getState() == 2) {
                    stateMachine.disconnect();
                }
                if (action.equals("android.bluetooth.device.action.SDP_RECORD")) {
                    ParcelUuid uuid = (ParcelUuid) intent.getParcelableExtra("android.bluetooth.device.extra.UUID");
                    if (uuid.equals(BluetoothUuid.MAS)) {
                        SdpMasRecord masRecord = intent.getParcelableExtra("android.bluetooth.device.extra.SDP_RECORD");
                        int status = intent.getIntExtra("android.bluetooth.device.extra.SDP_SEARCH_STATUS", -1);
                        if (masRecord == null) {
                            Log.w(MapClientService.TAG, "SDP search ended with no MAS record. Status: " + status);
                            return;
                        }
                        stateMachine.obtainMessage(1005, masRecord).sendToTarget();
                        return;
                    }
                    return;
                }
                return;
            }
            Log.e(MapClientService.TAG, "broadcast has NO device param!");
        }
    }
}
