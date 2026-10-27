package com.ts.car.dcm.wirelesscontroller;

import android.content.Context;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.ts.car.dcm.common.data.LinkDevice;
import java.util.HashMap;
import ts.car.dcm.common.data.BtOobInfo;
import ts.car.dcm.common.data.WifiApConfiguration;

/* JADX INFO: loaded from: classes.dex */
public class WirelessDeviceManager {
    private static final String TAG = "WirelessDeviceManager";
    private HandlerThread mHandlerThread;
    private boolean mMonitorInitDone;
    private final HashMap<Integer, DeviceMonitor> mMonitorMap;
    private boolean mMonitorStarted;

    public WirelessDeviceManager(WirelessConnController controller) {
        if (this.mHandlerThread == null) {
            HandlerThread handlerThread = new HandlerThread(TAG);
            this.mHandlerThread = handlerThread;
            handlerThread.start();
        }
        Looper looper = this.mHandlerThread.getLooper();
        this.mMonitorMap = new HashMap<>();
        BluetoothDeviceMonitor btDeviceMonitor = new BluetoothDeviceMonitor(controller, looper);
        WifiDeviceMonitor wifiDeviceMonitor = new WifiDeviceMonitor(controller, looper);
        this.mMonitorMap.put(0, btDeviceMonitor);
        this.mMonitorMap.put(1, wifiDeviceMonitor);
    }

    public synchronized void init(Context context, int capability) {
        if (!this.mMonitorInitDone) {
            for (DeviceMonitor monitor : this.mMonitorMap.values()) {
                monitor.init(context, capability);
            }
            this.mMonitorInitDone = true;
        }
    }

    public synchronized void startController() {
        if (!this.mMonitorStarted) {
            for (DeviceMonitor monitor : this.mMonitorMap.values()) {
                Message msg = monitor.obtainMessage(101);
                msg.sendToTarget();
            }
            this.mMonitorStarted = true;
        }
    }

    public synchronized void stopController() {
        if (this.mMonitorStarted) {
            for (DeviceMonitor monitor : this.mMonitorMap.values()) {
                Message msg = monitor.obtainMessage(102);
                msg.sendToTarget();
            }
            this.mMonitorStarted = false;
        }
    }

    public synchronized void setCapability(int capability) {
        for (DeviceMonitor monitor : this.mMonitorMap.values()) {
            Message msg = monitor.obtainMessage(200);
            msg.arg1 = capability;
            msg.sendToTarget();
        }
    }

    public void acceptDevice(String address, boolean result) {
        for (DeviceMonitor monitor : this.mMonitorMap.values()) {
            Bundle data = new Bundle();
            data.putString(WirelessMonitorConstants.BUNDLE_ADDRESS, address);
            data.putBoolean(WirelessMonitorConstants.BUNDLE_RESULT, result);
            Message msg = monitor.obtainMessage(201);
            msg.setData(data);
            msg.sendToTarget();
        }
    }

    public void setDeviceCapability(String address, int capability) {
        for (DeviceMonitor monitor : this.mMonitorMap.values()) {
            Message msg = monitor.obtainMessage(202);
            msg.obj = address;
            msg.arg1 = capability;
            msg.sendToTarget();
        }
    }

    public boolean deleteBtDevice(String address) {
        BluetoothDeviceMonitor monitor = (BluetoothDeviceMonitor) this.mMonitorMap.get(0);
        if (!monitor.isMonitorReady()) {
            return false;
        }
        return monitor.deleteBtDevice(address);
    }

    public boolean deleteBtConnection(String address) {
        BluetoothDeviceMonitor monitor = (BluetoothDeviceMonitor) this.mMonitorMap.get(0);
        if (!monitor.isMonitorReady()) {
            return false;
        }
        return monitor.deleteBtConnection(address);
    }

    public boolean disconnectBtDevice(String address) {
        BluetoothDeviceMonitor monitor = (BluetoothDeviceMonitor) this.mMonitorMap.get(0);
        if (!monitor.isMonitorReady()) {
            return false;
        }
        return monitor.disconnectBtDevice(address);
    }

    public boolean connectBtDevices(String address) {
        BluetoothDeviceMonitor monitor = (BluetoothDeviceMonitor) this.mMonitorMap.get(0);
        if (!monitor.isMonitorReady()) {
            return false;
        }
        return monitor.connectBtDevices(address);
    }

    public boolean disconnectOtherBtDevices(String address) {
        DeviceMonitor monitor = this.mMonitorMap.get(0);
        if (!monitor.isMonitorReady()) {
            return false;
        }
        Message msg = monitor.obtainMessage(203);
        msg.obj = address;
        msg.sendToTarget();
        return true;
    }

    public boolean connectBtProfile(String mac, int profile) {
        BluetoothDeviceMonitor monitor = (BluetoothDeviceMonitor) this.mMonitorMap.get(0);
        if (!monitor.isMonitorReady()) {
            return false;
        }
        return monitor.connectBtProfile(mac, profile);
    }

    public boolean disconnectBtProfile(String mac, int profile) {
        BluetoothDeviceMonitor monitor = (BluetoothDeviceMonitor) this.mMonitorMap.get(0);
        if (!monitor.isMonitorReady()) {
            return false;
        }
        return monitor.disconnectBtProfile(mac, profile);
    }

    public boolean isBluetoothEnabled() {
        BluetoothDeviceMonitor monitor = (BluetoothDeviceMonitor) this.mMonitorMap.get(0);
        if (!monitor.isMonitorReady()) {
            return false;
        }
        return monitor.isBluetoothEnabled();
    }

    public boolean enableBluetooth() {
        BluetoothDeviceMonitor monitor = (BluetoothDeviceMonitor) this.mMonitorMap.get(0);
        if (!monitor.isMonitorReady()) {
            return false;
        }
        return monitor.enableBluetooth();
    }

    public boolean disableBluetooth() {
        BluetoothDeviceMonitor monitor = (BluetoothDeviceMonitor) this.mMonitorMap.get(0);
        if (!monitor.isMonitorReady()) {
            return false;
        }
        return monitor.disableBluetooth();
    }

    public BtOobInfo getBtOobInfo() {
        BluetoothDeviceMonitor monitor = (BluetoothDeviceMonitor) this.mMonitorMap.get(0);
        if (monitor.isMonitorReady()) {
            return monitor.getBtOobInfo();
        }
        return null;
    }

    public boolean setBtOobInfo(String name, String linkKey, String mac, int linkKeyType) {
        BluetoothDeviceMonitor monitor = (BluetoothDeviceMonitor) this.mMonitorMap.get(0);
        if (!monitor.isMonitorReady()) {
            return false;
        }
        return monitor.setBtOobInfo(name, linkKey, mac, linkKeyType);
    }

    public boolean enableWifiAp(boolean z, WifiApConfiguration wifiApConfiguration) {
        DeviceMonitor deviceMonitor = this.mMonitorMap.get(1);
        if (deviceMonitor.isMonitorReady()) {
            Message messageObtainMessage = deviceMonitor.obtainMessage(204);
            messageObtainMessage.arg1 = z ? 1 : 0;
            messageObtainMessage.obj = wifiApConfiguration;
            messageObtainMessage.sendToTarget();
            return true;
        }
        return false;
    }

    public LinkDevice getWifiLinkDeviceByIpaddr(String ipAddr) {
        WifiDeviceMonitor monitor = (WifiDeviceMonitor) this.mMonitorMap.get(1);
        if (monitor.isMonitorReady()) {
            return monitor.getWifiLinkDeviceByIpaddr(ipAddr);
        }
        return null;
    }

    public boolean deleteWifiDevice(String address) {
        WifiDeviceMonitor monitor = (WifiDeviceMonitor) this.mMonitorMap.get(1);
        if (monitor.isMonitorReady()) {
            Log.i(TAG, "deleteWifiDevice success, the WifiDeviceMonitor is ready");
            return monitor.deleteWifiDevice(address);
        }
        Log.w(TAG, "deleteWifiDevice failed, the WifiDeviceMonitor is not ready");
        return false;
    }

    public boolean reattachWifiDevice(String address) {
        WifiDeviceMonitor monitor = (WifiDeviceMonitor) this.mMonitorMap.get(1);
        if (monitor.isMonitorReady()) {
            return monitor.reattachWifiDevice(address);
        }
        return false;
    }

    public boolean requestBluetoothPairing(String mac) {
        BluetoothDeviceMonitor monitor = (BluetoothDeviceMonitor) this.mMonitorMap.get(0);
        if (!monitor.isMonitorReady()) {
            return false;
        }
        return monitor.requestBluetoothPairing(mac);
    }
}
