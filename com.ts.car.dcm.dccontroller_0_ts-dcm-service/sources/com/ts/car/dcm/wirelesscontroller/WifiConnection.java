package com.ts.car.dcm.wirelesscontroller;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.ts.car.dcm.common.data.LinkDevice;
import com.ts.car.dcm.common.data.LogEncrypt;
import com.ts.car.dcm.utils.statemachine.State;

/* JADX INFO: loaded from: classes.dex */
public class WifiConnection extends WirelessConnection {
    public static final int REATTACH_WIFI_DEVICE = 24;
    private static final String TAG = WifiConnection.class.getSimpleName();
    public static final int WIFI_CONNECTED = 20;
    public static final int WIFI_DELETE_DEVICE = 22;
    public static final int WIFI_DISCONNECTED = 21;
    public static final int WIFI_INFO_UPDATED = 23;
    private Connected mConnected;
    private Detecting mDetecting;
    private WirelessDeviceDetection mDeviceDetection;
    private Disconnected mDisconnected;
    private Removed mRemoved;
    private WifiDeviceMonitor mWifiDeviceMonitor;

    private WifiConnection(Looper looper, WifiDevice wifiDevice, WifiDeviceMonitor monitor, int platformCapability) {
        super("WifiConnection", looper);
        this.mWifiDeviceMonitor = monitor;
        this.mPlatformCapability = platformCapability;
        this.mDeviceDetection = monitor.getWirelessDeviceDetection();
        this.mLinkDevice = new LinkDevice();
        this.mLinkDevice.setWifiIpAddrress(wifiDevice.getIpAddress());
        this.mLinkDevice.setWifiAddress(wifiDevice.getMacAddress());
        this.mLinkDevice.setFriendlyName(wifiDevice.getName());
        this.mDeviceRealCapability = wifiDevice.getCapability();
        this.mLinkDevice.setCapability(this.mDeviceRealCapability & platformCapability);
        this.mDisconnected = new Disconnected();
        this.mDetecting = new Detecting();
        this.mConnected = new Connected();
        this.mRemoved = new Removed();
        addState(this.mDisconnected);
        addState(this.mDetecting);
        addState(this.mConnected);
        addState(this.mRemoved);
        setInitialState(this.mDisconnected);
        Log.d(TAG, "platformCapability:" + platformCapability);
    }

    public static WifiConnection make(Looper looper, WifiDevice wifiDevice, WifiDeviceMonitor monitor, int platformCapability) {
        WifiConnection wifiConnection = new WifiConnection(looper, wifiDevice, monitor, platformCapability);
        wifiConnection.start();
        return wifiConnection;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean updateLinkDevice(WifiDevice wifiDevice) {
        boolean ret = false;
        String devIpAddress = wifiDevice.getIpAddress();
        String devFriendlyName = wifiDevice.getName();
        if (devIpAddress != null && !devIpAddress.equals(this.mLinkDevice.getWifiIpAddrress())) {
            this.mLinkDevice.setWifiIpAddrress(devIpAddress);
            ret = true;
            Log.d(TAG, "updateLinkDevice, Ip address: " + LogEncrypt.IpAddressEncrypt(this.mLinkDevice.getWifiIpAddrress()));
        }
        if (devFriendlyName != null && !devFriendlyName.equals(this.mLinkDevice.getFriendlyName())) {
            this.mLinkDevice.setFriendlyName(devFriendlyName);
            Log.e(TAG, "updateLinkDevice, Friendly name: " + this.mLinkDevice.getFriendlyName());
            return true;
        }
        return ret;
    }

    private class Disconnected extends State {
        private Disconnected() {
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public void enter() {
            Log.d(WifiConnection.TAG, "enter Disconnected");
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public boolean processMessage(Message msg) {
            Log.d(WifiConnection.TAG, "Disconnected processMessage msg.what:" + msg.what);
            if (msg.what == 20) {
                Log.d(WifiConnection.TAG, "Disconnected processMessage WIFI_CONNECTED");
                WifiConnection.this.notifyWirelessDeviceAttach();
                WifiConnection wifiConnection = WifiConnection.this;
                wifiConnection.transitionTo(wifiConnection.mDetecting);
            }
            return super.processMessage(msg);
        }
    }

    private class Detecting extends State {
        private Detecting() {
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public void enter() {
            Log.d(WifiConnection.TAG, "enter Detecting");
            if ((WifiConnection.this.mLinkDevice.getCapability() & 2048) == 0) {
                if (WifiConnection.this.mDeviceDetection != null) {
                    WifiConnection.this.mDeviceDetection.detectWirelessDevice(WifiConnection.this.mLinkDevice.getWifiAddress(), WifiConnection.this);
                    WifiConnection.this.sendMessageDelayed(13, 20000L);
                    return;
                }
                return;
            }
            WifiConnection wifiConnection = WifiConnection.this;
            wifiConnection.transitionTo(wifiConnection.mConnected);
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public boolean processMessage(Message msg) {
            Log.d(WifiConnection.TAG, "Detecting processMessage msg.what:" + msg.what);
            int i = msg.what;
            switch (i) {
                case 10:
                    Log.d(WifiConnection.TAG, "Detecting processMessage DCC_SET_PLATFORM_CAPABILITY");
                    WifiConnection.this.mPlatformCapability = msg.arg1;
                    WifiConnection.this.mLinkDevice.setCapability(WifiConnection.this.mPlatformCapability & WifiConnection.this.mDeviceRealCapability);
                    WifiConnection.this.notifyWirelessDeviceChanged(3);
                    break;
                case 11:
                    Log.d(WifiConnection.TAG, "Detecting processMessage DCC_ACCEPT_CONNECT");
                    WifiConnection.this.processApproveConnect();
                    break;
                case 12:
                    Log.d(WifiConnection.TAG, "Detecting processMessage MONITOR_DEVICE_DETECTED");
                    WifiConnection.this.removeMessages(13);
                    int detectedCapability = msg.arg1;
                    WifiConnection.this.updateLinkDeviceCapability(true, detectedCapability);
                    WifiConnection.this.notifyWirelessDeviceChanged(3);
                    WifiConnection.this.mDeviceDetection.removeDetectDevice(WifiConnection.this.mLinkDevice.getWifiAddress());
                    WifiConnection wifiConnection = WifiConnection.this;
                    wifiConnection.transitionTo(wifiConnection.mConnected);
                    break;
                case 13:
                    Log.d(WifiConnection.TAG, "Detecting processMessage MONITOR_DETECTION_TIMEOUT");
                    Log.d(WifiConnection.TAG, "Detecting failed. wifi:" + LogEncrypt.MacAddressEncrypt(WifiConnection.this.mLinkDevice.getWifiAddress()));
                    WifiConnection.this.mDeviceDetection.removeDetectDevice(WifiConnection.this.mLinkDevice.getWifiAddress());
                    WifiConnection wifiConnection2 = WifiConnection.this;
                    wifiConnection2.transitionTo(wifiConnection2.mConnected);
                    break;
                default:
                    switch (i) {
                        case 21:
                            Log.d(WifiConnection.TAG, "Detecting processMessage WIFI_DISCONNECTED");
                            WifiConnection.this.notifyWirelessDeviceDetach();
                            WifiConnection.this.mDeviceDetection.removeDetectDevice(WifiConnection.this.mLinkDevice.getWifiAddress());
                            WifiConnection wifiConnection3 = WifiConnection.this;
                            wifiConnection3.transitionTo(wifiConnection3.mDisconnected);
                            break;
                        case 22:
                            WifiConnection wifiConnection4 = WifiConnection.this;
                            wifiConnection4.transitionTo(wifiConnection4.mRemoved);
                            break;
                        case 23:
                            WifiDevice wifiDevice = (WifiDevice) msg.obj;
                            if (WifiConnection.this.updateLinkDevice(wifiDevice)) {
                                WifiConnection.this.notifyWirelessDeviceChanged(6);
                            }
                            break;
                    }
                    break;
            }
            return super.processMessage(msg);
        }
    }

    private class Connected extends State {
        private Connected() {
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public void enter() {
            Log.d(WifiConnection.TAG, "enter Connected");
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public boolean processMessage(Message msg) {
            Log.d(WifiConnection.TAG, "Connected processMessage msg.what:" + msg.what);
            int i = msg.what;
            switch (i) {
                case 10:
                    Log.d(WifiConnection.TAG, "Connected processMessage DCC_SET_PLATFORM_CAPABILITY");
                    WifiConnection.this.mPlatformCapability = msg.arg1;
                    WifiConnection.this.mLinkDevice.setCapability(WifiConnection.this.mPlatformCapability & WifiConnection.this.mDeviceRealCapability);
                    WifiConnection.this.notifyWirelessDeviceChanged(3);
                    break;
                case 11:
                    Log.d(WifiConnection.TAG, "Connected processMessage DCC_ACCEPT_CONNECT");
                    WifiConnection.this.processApproveConnect();
                    break;
                case 12:
                    Log.d(WifiConnection.TAG, "Connected processMessage MONITOR_DEVICE_DETECTED");
                    WifiConnection.this.removeMessages(13);
                    int detectedCapability = msg.arg1;
                    WifiConnection.this.updateLinkDeviceCapability(true, detectedCapability);
                    WifiConnection.this.notifyWirelessDeviceChanged(3);
                    WifiConnection.this.mDeviceDetection.removeDetectDevice(WifiConnection.this.mLinkDevice.getWifiAddress());
                    break;
                default:
                    switch (i) {
                        case 21:
                            Log.d(WifiConnection.TAG, "Connected processMessage WIFI_DISCONNECTED");
                            WifiConnection.this.notifyWirelessDeviceDetach();
                            WifiConnection.this.mDeviceDetection.removeDetectDevice(WifiConnection.this.mLinkDevice.getWifiAddress());
                            WifiConnection wifiConnection = WifiConnection.this;
                            wifiConnection.transitionTo(wifiConnection.mDisconnected);
                            break;
                        case 22:
                            Log.d(WifiConnection.TAG, "Connected processMessage WIFI_DELETE_DEVICE");
                            WifiConnection wifiConnection2 = WifiConnection.this;
                            wifiConnection2.transitionTo(wifiConnection2.mRemoved);
                            break;
                        case 23:
                            WifiDevice wifiDevice = (WifiDevice) msg.obj;
                            if (WifiConnection.this.updateLinkDevice(wifiDevice)) {
                                WifiConnection.this.notifyWirelessDeviceChanged(6);
                            }
                            break;
                    }
                    break;
            }
            return super.processMessage(msg);
        }
    }

    private class Removed extends State {
        private Removed() {
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public void enter() {
            Log.d(WifiConnection.TAG, "enter Removed");
            WifiConnection.this.notifyWirelessDeviceDetach();
            WifiConnection.this.mDeviceDetection.removeDetectDevice(WifiConnection.this.mLinkDevice.getWifiAddress());
        }

        @Override // com.ts.car.dcm.utils.statemachine.State, com.ts.car.dcm.utils.statemachine.IState
        public boolean processMessage(Message msg) {
            Log.d(WifiConnection.TAG, "Removed processMessage msg.what:" + msg.what);
            switch (msg.what) {
                case 21:
                    Log.d(WifiConnection.TAG, "Removed processMessage WIFI_DISCONNECTED");
                    WifiConnection.this.notifyWirelessDeviceDetach();
                    WifiConnection.this.mDeviceDetection.removeDetectDevice(WifiConnection.this.mLinkDevice.getWifiAddress());
                    WifiConnection wifiConnection = WifiConnection.this;
                    wifiConnection.transitionTo(wifiConnection.mDisconnected);
                    break;
                case 22:
                    Log.d(WifiConnection.TAG, "Removed processMessage WIFI_DELETE_DEVICE");
                    WifiConnection.this.notifyWirelessDeviceDetach();
                    break;
                case 23:
                    WifiDevice wifiDevice = (WifiDevice) msg.obj;
                    if (WifiConnection.this.updateLinkDevice(wifiDevice)) {
                        WifiConnection.this.notifyWirelessDeviceChanged(6);
                    }
                    break;
                case 24:
                    WifiConnection.this.notifyWirelessDeviceAttach();
                    WifiConnection wifiConnection2 = WifiConnection.this;
                    wifiConnection2.transitionTo(wifiConnection2.mDetecting);
                    break;
            }
            return super.processMessage(msg);
        }
    }

    @Override // com.ts.car.dcm.wirelesscontroller.WirelessConnection
    public void notifyWirelessDeviceAttach() {
        this.mLinkDevice.setId(this.mLinkDevice.getWifiAddress());
        this.mLinkDevice.setConnectionType(3);
        this.mLinkDevice.setDeviceState(3);
        Message msg = this.mWifiDeviceMonitor.obtainMessage(WirelessMonitorConstants.EVENT_WIRELESS_DEVICE_ATTACH);
        msg.obj = this.mLinkDevice.clone();
        msg.sendToTarget();
    }

    @Override // com.ts.car.dcm.wirelesscontroller.WirelessConnection
    public void notifyWirelessDeviceChanged(int reason) {
        if (this.mDeviceApproved) {
            int deviceCapability = this.mLinkDevice.getCapability();
            Log.d(TAG, "notifyWirelessDeviceChanged, mLastReportCapability:" + this.mLastReportCapability + ", capability:" + deviceCapability);
            if (this.mLastReportCapability != deviceCapability) {
                this.mLastReportCapability = deviceCapability;
                Message msg = this.mWifiDeviceMonitor.obtainMessage(WirelessMonitorConstants.EVENT_WIRELESS_DEVICE_CHANGE);
                msg.obj = this.mLinkDevice.clone();
                msg.arg1 = reason;
                msg.sendToTarget();
            }
        }
    }

    @Override // com.ts.car.dcm.wirelesscontroller.WirelessConnection
    public void notifyWirelessDeviceDetach() {
        this.mLinkDevice.setDeviceState(4);
        Message msg = this.mWifiDeviceMonitor.obtainMessage(WirelessMonitorConstants.EVENT_WIRELESS_DEVICE_DETACH);
        msg.obj = this.mLinkDevice.clone();
        msg.sendToTarget();
    }

    public LinkDevice getLinkDevice() {
        return this.mLinkDevice;
    }

    public String getIpAddress() {
        return this.mLinkDevice.getWifiIpAddrress();
    }

    public boolean isExistCapability(int capability) {
        return (this.mLinkDevice.getCapability() & capability) > 0;
    }

    public boolean isRemovedState() {
        return getCurrentState() == this.mRemoved;
    }
}
