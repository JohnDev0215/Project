package com.android.bluetooth.hid;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.IBluetoothHidHost;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.UserHandle;
import android.util.Log;
import com.android.bluetooth.BluetoothMetricsProto;
import com.android.bluetooth.Utils;
import com.android.bluetooth.btservice.AdapterService;
import com.android.bluetooth.btservice.MetricsLogger;
import com.android.bluetooth.btservice.ProfileService;
import com.android.vcard.VCardConfig;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class HidHostService extends ProfileService {
    private static final int CONN_STATE_CONNECTED = 0;
    private static final int CONN_STATE_CONNECTING = 1;
    private static final int CONN_STATE_DISCONNECTED = 2;
    private static final int CONN_STATE_DISCONNECTING = 3;
    private static final boolean DBG = false;
    private static final int MESSAGE_CONNECT = 1;
    private static final int MESSAGE_CONNECT_STATE_CHANGED = 3;
    private static final int MESSAGE_DISCONNECT = 2;
    private static final int MESSAGE_GET_IDLE_TIME = 14;
    private static final int MESSAGE_GET_PROTOCOL_MODE = 4;
    private static final int MESSAGE_GET_REPORT = 8;
    private static final int MESSAGE_ON_GET_IDLE_TIME = 15;
    private static final int MESSAGE_ON_GET_PROTOCOL_MODE = 6;
    private static final int MESSAGE_ON_GET_REPORT = 9;
    private static final int MESSAGE_ON_HANDSHAKE = 13;
    private static final int MESSAGE_ON_VIRTUAL_UNPLUG = 12;
    private static final int MESSAGE_SEND_DATA = 11;
    private static final int MESSAGE_SET_IDLE_TIME = 16;
    private static final int MESSAGE_SET_PROTOCOL_MODE = 7;
    private static final int MESSAGE_SET_REPORT = 10;
    private static final int MESSAGE_VIRTUAL_UNPLUG = 5;
    private static final String TAG = "BluetoothHidHostService";
    private static HidHostService sHidHostService;
    private Map<BluetoothDevice, Integer> mInputDevices;
    private boolean mNativeAvailable;
    private BluetoothDevice mTargetDevice = null;
    private final Handler mHandler = new Handler() { // from class: com.android.bluetooth.hid.HidHostService.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    BluetoothDevice device = (BluetoothDevice) msg.obj;
                    if (!HidHostService.this.connectHidNative(Utils.getByteAddress(device))) {
                        HidHostService.this.broadcastConnectionState(device, 3);
                        HidHostService.this.broadcastConnectionState(device, 0);
                    } else {
                        HidHostService.this.mTargetDevice = device;
                    }
                    break;
                case 2:
                    BluetoothDevice device2 = (BluetoothDevice) msg.obj;
                    if (!HidHostService.this.disconnectHidNative(Utils.getByteAddress(device2))) {
                        HidHostService.this.broadcastConnectionState(device2, 3);
                        HidHostService.this.broadcastConnectionState(device2, 0);
                    }
                    break;
                case 3:
                    BluetoothDevice device3 = HidHostService.this.getDevice((byte[]) msg.obj);
                    int halState = msg.arg1;
                    Integer prevStateInteger = (Integer) HidHostService.this.mInputDevices.get(device3);
                    int prevState = prevStateInteger == null ? 0 : prevStateInteger.intValue();
                    if (halState != 0 || prevState != 0 || HidHostService.this.okToConnect(device3)) {
                        HidHostService.this.broadcastConnectionState(device3, HidHostService.convertHalState(halState));
                    } else {
                        HidHostService.this.virtualUnPlugNative(Utils.getByteAddress(device3));
                    }
                    if (halState == 0 && HidHostService.this.mTargetDevice != null && HidHostService.this.mTargetDevice.equals(device3)) {
                        HidHostService.this.mTargetDevice = null;
                        AdapterService adapterService = AdapterService.getAdapterService();
                        adapterService.enable(false);
                        break;
                    }
                    break;
                case 4:
                    if (!HidHostService.this.getProtocolModeNative(Utils.getByteAddress((BluetoothDevice) msg.obj))) {
                        Log.e(HidHostService.TAG, "Error: get protocol mode native returns false");
                    }
                    break;
                case 5:
                    if (!HidHostService.this.virtualUnPlugNative(Utils.getByteAddress((BluetoothDevice) msg.obj))) {
                        Log.e(HidHostService.TAG, "Error: virtual unplug native returns false");
                    }
                    break;
                case 6:
                    HidHostService.this.broadcastProtocolMode(HidHostService.this.getDevice((byte[]) msg.obj), msg.arg1);
                    break;
                case 7:
                    BluetoothDevice device4 = (BluetoothDevice) msg.obj;
                    byte protocolMode = (byte) msg.arg1;
                    Log.d(HidHostService.TAG, "sending set protocol mode(" + ((int) protocolMode) + ")");
                    if (!HidHostService.this.setProtocolModeNative(Utils.getByteAddress(device4), protocolMode)) {
                        Log.e(HidHostService.TAG, "Error: set protocol mode native returns false");
                    }
                    break;
                case 8:
                    BluetoothDevice device5 = (BluetoothDevice) msg.obj;
                    Bundle data = msg.getData();
                    byte reportType = data.getByte("android.bluetooth.BluetoothHidHost.extra.REPORT_TYPE");
                    byte reportId = data.getByte("android.bluetooth.BluetoothHidHost.extra.REPORT_ID");
                    int bufferSize = data.getInt("android.bluetooth.BluetoothHidHost.extra.REPORT_BUFFER_SIZE");
                    if (!HidHostService.this.getReportNative(Utils.getByteAddress(device5), reportType, reportId, bufferSize)) {
                        Log.e(HidHostService.TAG, "Error: get report native returns false");
                    }
                    break;
                case 9:
                    BluetoothDevice device6 = HidHostService.this.getDevice((byte[]) msg.obj);
                    Bundle data2 = msg.getData();
                    byte[] report = data2.getByteArray("android.bluetooth.BluetoothHidHost.extra.REPORT");
                    int bufferSize2 = data2.getInt("android.bluetooth.BluetoothHidHost.extra.REPORT_BUFFER_SIZE");
                    HidHostService.this.broadcastReport(device6, report, bufferSize2);
                    break;
                case 10:
                    BluetoothDevice device7 = (BluetoothDevice) msg.obj;
                    Bundle data3 = msg.getData();
                    byte reportType2 = data3.getByte("android.bluetooth.BluetoothHidHost.extra.REPORT_TYPE");
                    String report2 = data3.getString("android.bluetooth.BluetoothHidHost.extra.REPORT");
                    if (!HidHostService.this.setReportNative(Utils.getByteAddress(device7), reportType2, report2)) {
                        Log.e(HidHostService.TAG, "Error: set report native returns false");
                    }
                    break;
                case 11:
                    BluetoothDevice device8 = (BluetoothDevice) msg.obj;
                    String report3 = msg.getData().getString("android.bluetooth.BluetoothHidHost.extra.REPORT");
                    if (!HidHostService.this.sendDataNative(Utils.getByteAddress(device8), report3)) {
                        Log.e(HidHostService.TAG, "Error: send data native returns false");
                    }
                    break;
                case 12:
                    BluetoothDevice device9 = HidHostService.this.getDevice((byte[]) msg.obj);
                    int status = msg.arg1;
                    HidHostService.this.broadcastVirtualUnplugStatus(device9, status);
                    break;
                case 13:
                    BluetoothDevice device10 = HidHostService.this.getDevice((byte[]) msg.obj);
                    int status2 = msg.arg1;
                    HidHostService.this.broadcastHandshake(device10, status2);
                    break;
                case 14:
                    if (!HidHostService.this.getIdleTimeNative(Utils.getByteAddress((BluetoothDevice) msg.obj))) {
                        Log.e(HidHostService.TAG, "Error: get idle time native returns false");
                    }
                    break;
                case 15:
                    BluetoothDevice device11 = HidHostService.this.getDevice((byte[]) msg.obj);
                    int idleTime = msg.arg1;
                    HidHostService.this.broadcastIdleTime(device11, idleTime);
                    break;
                case 16:
                    BluetoothDevice device12 = (BluetoothDevice) msg.obj;
                    byte idleTime2 = msg.getData().getByte("android.bluetooth.BluetoothHidHost.extra.IDLE_TIME");
                    if (!HidHostService.this.setIdleTimeNative(Utils.getByteAddress(device12), idleTime2)) {
                        Log.e(HidHostService.TAG, "Error: get idle time native returns false");
                    }
                    break;
            }
        }
    };

    private static native void classInitNative();

    private native void cleanupNative();

    /* JADX INFO: Access modifiers changed from: private */
    public native boolean connectHidNative(byte[] bArr);

    /* JADX INFO: Access modifiers changed from: private */
    public native boolean disconnectHidNative(byte[] bArr);

    /* JADX INFO: Access modifiers changed from: private */
    public native boolean getIdleTimeNative(byte[] bArr);

    /* JADX INFO: Access modifiers changed from: private */
    public native boolean getProtocolModeNative(byte[] bArr);

    /* JADX INFO: Access modifiers changed from: private */
    public native boolean getReportNative(byte[] bArr, byte b, byte b2, int i);

    private native void initializeNative();

    /* JADX INFO: Access modifiers changed from: private */
    public native boolean sendDataNative(byte[] bArr, String str);

    /* JADX INFO: Access modifiers changed from: private */
    public native boolean setIdleTimeNative(byte[] bArr, byte b);

    /* JADX INFO: Access modifiers changed from: private */
    public native boolean setProtocolModeNative(byte[] bArr, byte b);

    /* JADX INFO: Access modifiers changed from: private */
    public native boolean setReportNative(byte[] bArr, byte b, String str);

    /* JADX INFO: Access modifiers changed from: private */
    public native boolean virtualUnPlugNative(byte[] bArr);

    static {
        classInitNative();
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    public ProfileService.IProfileServiceBinder initBinder() {
        return new BluetoothHidHostBinder(this);
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected boolean start() {
        this.mInputDevices = Collections.synchronizedMap(new HashMap());
        initializeNative();
        this.mNativeAvailable = true;
        setHidHostService(this);
        return true;
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected boolean stop() {
        return true;
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    protected void cleanup() {
        if (this.mNativeAvailable) {
            cleanupNative();
            this.mNativeAvailable = false;
        }
        Map<BluetoothDevice, Integer> map = this.mInputDevices;
        if (map != null) {
            for (BluetoothDevice device : map.keySet()) {
                int inputDeviceState = getConnectionState(device);
                if (inputDeviceState != 0) {
                    broadcastConnectionState(device, 0);
                }
            }
            this.mInputDevices.clear();
        }
        setHidHostService(null);
    }

    public static synchronized HidHostService getHidHostService() {
        if (sHidHostService == null) {
            Log.w(TAG, "getHidHostService(): service is null");
            return null;
        }
        if (!sHidHostService.isAvailable()) {
            Log.w(TAG, "getHidHostService(): service is not available ");
            return null;
        }
        return sHidHostService;
    }

    private static synchronized void setHidHostService(HidHostService instance) {
        sHidHostService = instance;
    }

    private static class BluetoothHidHostBinder extends IBluetoothHidHost.Stub implements ProfileService.IProfileServiceBinder {
        private HidHostService mService;

        BluetoothHidHostBinder(HidHostService svc) {
            this.mService = svc;
        }

        @Override // com.android.bluetooth.btservice.ProfileService.IProfileServiceBinder
        public void cleanup() {
            this.mService = null;
        }

        private HidHostService getService() {
            if (!Utils.checkCaller()) {
                Log.w(HidHostService.TAG, "InputDevice call not allowed for non-active user");
                return null;
            }
            HidHostService hidHostService = this.mService;
            if (hidHostService != null && hidHostService.isAvailable()) {
                return this.mService;
            }
            Log.w(HidHostService.TAG, "Service is null");
            return null;
        }

        public boolean connect(BluetoothDevice device) {
            HidHostService service = getService();
            if (service == null) {
                return false;
            }
            Utils.enforceBluetoothPrivilegedPermission(service);
            return service.connect(device);
        }

        public boolean disconnect(BluetoothDevice device) {
            HidHostService service = getService();
            if (service == null) {
                return false;
            }
            Utils.enforceBluetoothPrivilegedPermission(service);
            return service.disconnect(device);
        }

        public int getConnectionState(BluetoothDevice device) {
            HidHostService service = getService();
            if (service == null) {
                return 0;
            }
            Utils.enforceBluetoothPermission(service);
            return service.getConnectionState(device);
        }

        public List<BluetoothDevice> getConnectedDevices() {
            HidHostService service = getService();
            if (service == null) {
                return new ArrayList();
            }
            Utils.enforceBluetoothPermission(service);
            return getDevicesMatchingConnectionStates(new int[]{2});
        }

        public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states) {
            HidHostService service = getService();
            if (service == null) {
                return new ArrayList(0);
            }
            Utils.enforceBluetoothPermission(service);
            return service.getDevicesMatchingConnectionStates(states);
        }

        public boolean setConnectionPolicy(BluetoothDevice device, int connectionPolicy) {
            HidHostService service = getService();
            if (service == null) {
                return false;
            }
            Utils.enforceBluetoothPrivilegedPermission(service);
            return service.setConnectionPolicy(device, connectionPolicy);
        }

        public int getConnectionPolicy(BluetoothDevice device) {
            HidHostService service = getService();
            if (service == null) {
                return -1;
            }
            Utils.enforceBluetoothPrivilegedPermission(service);
            return service.getConnectionPolicy(device);
        }

        public boolean getProtocolMode(BluetoothDevice device) {
            HidHostService service = getService();
            if (service == null) {
                return false;
            }
            Utils.enforceBluetoothAdminPermission(service);
            return service.getProtocolMode(device);
        }

        public boolean virtualUnplug(BluetoothDevice device) {
            HidHostService service = getService();
            if (service == null) {
                return false;
            }
            Utils.enforceBluetoothAdminPermission(service);
            return service.virtualUnplug(device);
        }

        public boolean setProtocolMode(BluetoothDevice device, int protocolMode) {
            HidHostService service = getService();
            if (service == null) {
                return false;
            }
            Utils.enforceBluetoothAdminPermission(service);
            return service.setProtocolMode(device, protocolMode);
        }

        public boolean getReport(BluetoothDevice device, byte reportType, byte reportId, int bufferSize) {
            HidHostService service = getService();
            if (service == null) {
                return false;
            }
            Utils.enforceBluetoothAdminPermission(service);
            return service.getReport(device, reportType, reportId, bufferSize);
        }

        public boolean setReport(BluetoothDevice device, byte reportType, String report) {
            HidHostService service = getService();
            if (service == null) {
                return false;
            }
            Utils.enforceBluetoothAdminPermission(service);
            return service.setReport(device, reportType, report);
        }

        public boolean sendData(BluetoothDevice device, String report) {
            HidHostService service = getService();
            if (service == null) {
                return false;
            }
            Utils.enforceBluetoothAdminPermission(service);
            return service.sendData(device, report);
        }

        public boolean setIdleTime(BluetoothDevice device, byte idleTime) {
            HidHostService service = getService();
            if (service == null) {
                return false;
            }
            Utils.enforceBluetoothAdminPermission(service);
            return service.setIdleTime(device, idleTime);
        }

        public boolean getIdleTime(BluetoothDevice device) {
            HidHostService service = getService();
            if (service == null) {
                return false;
            }
            Utils.enforceBluetoothAdminPermission(service);
            return service.getIdleTime(device);
        }
    }

    public boolean connect(BluetoothDevice device) {
        if (getConnectionState(device) != 0) {
            Log.e(TAG, "Hid Device not disconnected: " + device);
            return false;
        }
        if (getConnectionPolicy(device) == 0) {
            Log.e(TAG, "Hid Device CONNECTION_POLICY_FORBIDDEN: " + device);
            return false;
        }
        Message msg = this.mHandler.obtainMessage(1, device);
        this.mHandler.sendMessage(msg);
        return true;
    }

    public boolean disconnect(BluetoothDevice device) {
        Message msg = this.mHandler.obtainMessage(2, device);
        this.mHandler.sendMessage(msg);
        return true;
    }

    public int getConnectionState(BluetoothDevice device) {
        if (this.mInputDevices.get(device) == null) {
            return 0;
        }
        return this.mInputDevices.get(device).intValue();
    }

    List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states) {
        List<BluetoothDevice> inputDevices = new ArrayList<>();
        for (BluetoothDevice device : this.mInputDevices.keySet()) {
            int inputDeviceState = getConnectionState(device);
            int length = states.length;
            int i = 0;
            while (true) {
                if (i < length) {
                    int state = states[i];
                    if (state != inputDeviceState) {
                        i++;
                    } else {
                        inputDevices.add(device);
                        break;
                    }
                }
            }
        }
        return inputDevices;
    }

    public boolean setConnectionPolicy(BluetoothDevice device, int connectionPolicy) {
        AdapterService.getAdapterService().getDatabase().setProfileConnectionPolicy(device, 4, connectionPolicy);
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
        return AdapterService.getAdapterService().getDatabase().getProfileConnectionPolicy(device, 4);
    }

    boolean getProtocolMode(BluetoothDevice device) {
        int state = getConnectionState(device);
        if (state != 2) {
            return false;
        }
        Message msg = this.mHandler.obtainMessage(4, device);
        this.mHandler.sendMessage(msg);
        return true;
    }

    boolean virtualUnplug(BluetoothDevice device) {
        int state = getConnectionState(device);
        if (state != 2) {
            return false;
        }
        Message msg = this.mHandler.obtainMessage(5, device);
        this.mHandler.sendMessage(msg);
        return true;
    }

    boolean setProtocolMode(BluetoothDevice device, int protocolMode) {
        int state = getConnectionState(device);
        if (state != 2) {
            return false;
        }
        Message msg = this.mHandler.obtainMessage(7);
        msg.obj = device;
        msg.arg1 = protocolMode;
        this.mHandler.sendMessage(msg);
        return true;
    }

    boolean getReport(BluetoothDevice device, byte reportType, byte reportId, int bufferSize) {
        int state = getConnectionState(device);
        if (state != 2) {
            return false;
        }
        Message msg = this.mHandler.obtainMessage(8);
        msg.obj = device;
        Bundle data = new Bundle();
        data.putByte("android.bluetooth.BluetoothHidHost.extra.REPORT_TYPE", reportType);
        data.putByte("android.bluetooth.BluetoothHidHost.extra.REPORT_ID", reportId);
        data.putInt("android.bluetooth.BluetoothHidHost.extra.REPORT_BUFFER_SIZE", bufferSize);
        msg.setData(data);
        this.mHandler.sendMessage(msg);
        return true;
    }

    boolean setReport(BluetoothDevice device, byte reportType, String report) {
        int state = getConnectionState(device);
        if (state != 2) {
            return false;
        }
        Message msg = this.mHandler.obtainMessage(10);
        msg.obj = device;
        Bundle data = new Bundle();
        data.putByte("android.bluetooth.BluetoothHidHost.extra.REPORT_TYPE", reportType);
        data.putString("android.bluetooth.BluetoothHidHost.extra.REPORT", report);
        msg.setData(data);
        this.mHandler.sendMessage(msg);
        return true;
    }

    boolean sendData(BluetoothDevice device, String report) {
        int state = getConnectionState(device);
        if (state != 2) {
            return false;
        }
        return sendDataNative(Utils.getByteAddress(device), report);
    }

    boolean getIdleTime(BluetoothDevice device) {
        int state = getConnectionState(device);
        if (state != 2) {
            return false;
        }
        Message msg = this.mHandler.obtainMessage(14, device);
        this.mHandler.sendMessage(msg);
        return true;
    }

    boolean setIdleTime(BluetoothDevice device, byte idleTime) {
        int state = getConnectionState(device);
        if (state != 2) {
            return false;
        }
        Message msg = this.mHandler.obtainMessage(16);
        msg.obj = device;
        Bundle data = new Bundle();
        data.putByte("android.bluetooth.BluetoothHidHost.extra.IDLE_TIME", idleTime);
        msg.setData(data);
        this.mHandler.sendMessage(msg);
        return true;
    }

    private void onGetProtocolMode(byte[] address, int mode) {
        Message msg = this.mHandler.obtainMessage(6);
        msg.obj = address;
        msg.arg1 = mode;
        this.mHandler.sendMessage(msg);
    }

    private void onGetIdleTime(byte[] address, int idleTime) {
        Message msg = this.mHandler.obtainMessage(15);
        msg.obj = address;
        msg.arg1 = idleTime;
        this.mHandler.sendMessage(msg);
    }

    private void onGetReport(byte[] address, byte[] report, int rptSize) {
        Message msg = this.mHandler.obtainMessage(9);
        msg.obj = address;
        Bundle data = new Bundle();
        data.putByteArray("android.bluetooth.BluetoothHidHost.extra.REPORT", report);
        data.putInt("android.bluetooth.BluetoothHidHost.extra.REPORT_BUFFER_SIZE", rptSize);
        msg.setData(data);
        this.mHandler.sendMessage(msg);
    }

    private void onHandshake(byte[] address, int status) {
        Message msg = this.mHandler.obtainMessage(13);
        msg.obj = address;
        msg.arg1 = status;
        this.mHandler.sendMessage(msg);
    }

    private void onVirtualUnplug(byte[] address, int status) {
        Message msg = this.mHandler.obtainMessage(12);
        msg.obj = address;
        msg.arg1 = status;
        this.mHandler.sendMessage(msg);
    }

    private void onConnectStateChanged(byte[] address, int state) {
        Message msg = this.mHandler.obtainMessage(3);
        msg.obj = address;
        msg.arg1 = state;
        this.mHandler.sendMessage(msg);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void broadcastConnectionState(BluetoothDevice device, int newState) {
        Integer prevStateInteger = this.mInputDevices.get(device);
        int prevState = prevStateInteger == null ? 0 : prevStateInteger.intValue();
        if (prevState == newState) {
            Log.w(TAG, "no state change: " + newState);
            return;
        }
        if (newState == 2) {
            MetricsLogger.logProfileConnectionEvent(BluetoothMetricsProto.ProfileId.HID_HOST);
        }
        this.mInputDevices.put(device, Integer.valueOf(newState));
        Log.d(TAG, "Connection state " + device + ": " + prevState + "->" + newState);
        Intent intent = new Intent("android.bluetooth.input.profile.action.CONNECTION_STATE_CHANGED");
        intent.putExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", prevState);
        intent.putExtra("android.bluetooth.profile.extra.STATE", newState);
        intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
        intent.addFlags(VCardConfig.FLAG_APPEND_TYPE_PARAM);
        sendBroadcastAsUser(intent, UserHandle.ALL, ProfileService.BLUETOOTH_PERM);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void broadcastHandshake(BluetoothDevice device, int status) {
        Intent intent = new Intent("android.bluetooth.input.profile.action.HANDSHAKE");
        intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
        intent.putExtra("android.bluetooth.BluetoothHidHost.extra.STATUS", status);
        intent.addFlags(VCardConfig.FLAG_APPEND_TYPE_PARAM);
        sendBroadcast(intent, ProfileService.BLUETOOTH_PERM);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void broadcastProtocolMode(BluetoothDevice device, int protocolMode) {
        Intent intent = new Intent("android.bluetooth.input.profile.action.PROTOCOL_MODE_CHANGED");
        intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
        intent.putExtra("android.bluetooth.BluetoothHidHost.extra.PROTOCOL_MODE", protocolMode);
        intent.addFlags(VCardConfig.FLAG_APPEND_TYPE_PARAM);
        sendBroadcast(intent, ProfileService.BLUETOOTH_PERM);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void broadcastReport(BluetoothDevice device, byte[] report, int rptSize) {
        Intent intent = new Intent("android.bluetooth.input.profile.action.REPORT");
        intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
        intent.putExtra("android.bluetooth.BluetoothHidHost.extra.REPORT", report);
        intent.putExtra("android.bluetooth.BluetoothHidHost.extra.REPORT_BUFFER_SIZE", rptSize);
        intent.addFlags(VCardConfig.FLAG_APPEND_TYPE_PARAM);
        sendBroadcast(intent, ProfileService.BLUETOOTH_PERM);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void broadcastVirtualUnplugStatus(BluetoothDevice device, int status) {
        Intent intent = new Intent("android.bluetooth.input.profile.action.VIRTUAL_UNPLUG_STATUS");
        intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
        intent.putExtra("android.bluetooth.BluetoothHidHost.extra.VIRTUAL_UNPLUG_STATUS", status);
        intent.addFlags(VCardConfig.FLAG_APPEND_TYPE_PARAM);
        sendBroadcast(intent, ProfileService.BLUETOOTH_PERM);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void broadcastIdleTime(BluetoothDevice device, int idleTime) {
        Intent intent = new Intent("android.bluetooth.input.profile.action.IDLE_TIME_CHANGED");
        intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
        intent.putExtra("android.bluetooth.BluetoothHidHost.extra.IDLE_TIME", idleTime);
        intent.addFlags(VCardConfig.FLAG_APPEND_TYPE_PARAM);
        sendBroadcast(intent, ProfileService.BLUETOOTH_PERM);
    }

    public boolean okToConnect(BluetoothDevice device) {
        AdapterService adapterService = AdapterService.getAdapterService();
        if (adapterService == null) {
            Log.w(TAG, "okToConnect: adapter service is null");
            return false;
        }
        if (adapterService.isQuietModeEnabled() && this.mTargetDevice == null) {
            Log.w(TAG, "okToConnect: return false as quiet mode enabled");
            return false;
        }
        int connectionPolicy = getConnectionPolicy(device);
        int bondState = adapterService.getBondState(device);
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

    /* JADX INFO: Access modifiers changed from: private */
    public static int convertHalState(int halState) {
        if (halState == 0) {
            return 2;
        }
        if (halState == 1) {
            return 1;
        }
        if (halState == 2) {
            return 0;
        }
        if (halState == 3) {
            return 3;
        }
        Log.e(TAG, "bad hid connection state: " + halState);
        return 0;
    }

    @Override // com.android.bluetooth.btservice.ProfileService
    public void dump(StringBuilder sb) {
        super.dump(sb);
        println(sb, "mTargetDevice: " + this.mTargetDevice);
        println(sb, "mInputDevices:");
        for (BluetoothDevice device : this.mInputDevices.keySet()) {
            println(sb, "  " + device + " : " + this.mInputDevices.get(device));
        }
    }
}
