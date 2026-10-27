package com.ts.car.dcm.wirelesscontroller;

import android.content.Context;
import android.net.NetworkInfo;
import android.net.wifi.SoftApConfiguration;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.ts.car.dcm.common.data.LinkDevice;
import com.ts.car.dcm.common.data.LogEncrypt;
import com.ts.car.dcm.wirelesscontroller.wifi.MyWifiManager;
import com.ts.car.dcm.wirelesscontroller.wifi.WifiCallback;
import com.ts.car.dcm.wirelesscontroller.wifi.WifiDeviceInfo;
import com.ts.car.dcm.wirelesscontroller.wifi.WifiStartTetheringCallback;
import java.util.HashMap;
import java.util.Map;
import ts.car.dcm.common.data.WifiApConfiguration;

/* JADX INFO: loaded from: classes.dex */
public class WifiDeviceMonitor extends DeviceMonitor implements WifiCallback {
    public static final int INDEX_0 = 0;
    public static final int INDEX_1 = 1;
    public static final int INDEX_2 = 2;
    public static final int INDEX_3 = 3;
    public static final int KEY_RANDOM_END_AFTER = 13;
    public static final int KEY_RANDOM_END_FRONT = 8;
    public static final int KEY_RANDOM_FIRST_AFTER = 9;
    public static final int KEY_RANDOM_FIRST_FRONT = 0;
    private static final String TAG = WifiDeviceMonitor.class.getSimpleName();
    private Context mContext;
    private boolean mMonitorReady;
    private int mPlatformCapability;
    private int mWifiApBand;
    public SoftApConfiguration mWifiApConfig;
    private int mWifiApLocalState;
    private Looper mWifiConnLooper;
    private HashMap<String, WifiConnection> mWifiConnections;
    private WifiDeviceDetection mWifiDeviceDetection;
    private MyWifiManager mWifiManager;
    private WirelessConnController mWirelessController;

    public WifiDeviceMonitor(WirelessConnController controller, Looper looper) {
        super(looper);
        this.mWifiConnections = new HashMap<>();
        this.mWifiApLocalState = 11;
        this.mWirelessController = controller;
        this.mWifiConnLooper = looper;
    }

    @Override // com.ts.car.dcm.wirelesscontroller.DeviceMonitor
    public void init(Context context, int platformCapality) {
        this.mContext = context;
        this.mPlatformCapability = platformCapality;
        MyWifiManager myWifiManager = new MyWifiManager(context);
        this.mWifiManager = myWifiManager;
        myWifiManager.setCallback(this);
        this.mWifiManager.init();
    }

    @Override // com.ts.car.dcm.wirelesscontroller.DeviceMonitor
    public boolean isMonitorReady() {
        Log.d(TAG, "isMonitorReady:" + this.mMonitorReady);
        return this.mMonitorReady;
    }

    @Override // com.ts.car.dcm.wirelesscontroller.DeviceMonitor
    public WirelessDeviceDetection getWirelessDeviceDetection() {
        return this.mWifiDeviceDetection;
    }

    @Override // com.ts.car.dcm.wirelesscontroller.wifi.WifiCallback
    public void onWifiApStateChanged(int status) {
        Log.d(TAG, "onWifiApStateChanged:" + status);
        Message msg = obtainMessage(300);
        msg.arg1 = status;
        msg.sendToTarget();
    }

    @Override // com.ts.car.dcm.wirelesscontroller.wifi.WifiCallback
    public void onNetworkStateChanged(NetworkInfo.State state, WifiDeviceInfo wifiDeviceInfo) {
        Message msg = obtainMessage(801);
        msg.obj = fromWifiDeviceInfo(wifiDeviceInfo);
        if (state == NetworkInfo.State.CONNECTED) {
            msg.arg1 = 802;
        } else if (state == NetworkInfo.State.DISCONNECTED) {
            msg.arg1 = 803;
        }
        msg.sendToTarget();
    }

    public WifiConnection getWifiConnectionByIpaddr(String ipAddress) {
        for (WifiConnection connection : this.mWifiConnections.values()) {
            String ipAddr = connection.getIpAddress();
            if (ipAddr != null && ipAddr.equals(ipAddress)) {
                return connection;
            }
        }
        return null;
    }

    @Override // com.ts.car.dcm.wirelesscontroller.DeviceMonitor, android.os.Handler
    public void handleMessage(Message msg) {
        Log.d(TAG, "handleMessage msg.what:" + msg.what);
        super.handleMessage(msg);
        int i = msg.what;
        if (i == 204) {
            boolean enable = msg.arg1 == 1;
            SoftApConfiguration config = null;
            if (msg.obj != null) {
                WifiApConfiguration apConfig = (WifiApConfiguration) msg.obj;
                config = convertWifiApConfiguration(apConfig);
            }
            processEnableWifiAp(enable, config);
            return;
        }
        if (i != 801) {
            if (i != 805) {
                switch (i) {
                    case 300:
                        int state = msg.arg1;
                        processWifiApStateChange(state);
                        break;
                    case WirelessMonitorConstants.EVENT_WIFI_AP_STOP_TIME_OUT /* 302 */:
                        Log.d(TAG, "handleMessage, Stop softap timed out.");
                        if (this.mWifiApLocalState != 14) {
                            processEnableWifiAp(true, this.mWifiApConfig);
                        } else {
                            Log.d(TAG, "handleMessage, local state faild!");
                        }
                        this.mWifiApConfig = null;
                        break;
                }
                return;
            }
            String ipAddress = (String) msg.obj;
            int capability = msg.arg1;
            processCarlifeFound(ipAddress, capability);
            return;
        }
        WifiDevice wifiDevice = (WifiDevice) msg.obj;
        int event = msg.arg1;
        processWifiConnectChange(wifiDevice, event);
    }

    private void processCarlifeFound(String ipAddress, int capability) {
        if (ipAddress != null) {
            WifiConnection wifiConnection = getWifiConnectionByIpaddr(ipAddress);
            if (wifiConnection != null && capability != 0 && !wifiConnection.isExistCapability(capability)) {
                Log.d(TAG, "processCarlifeFound, find the capability:" + capability + ", Ipaddress:" + LogEncrypt.IpAddressEncrypt(ipAddress));
                wifiConnection.sendMessage(12, capability);
                return;
            }
            return;
        }
        WifiDevice wifiDevice = new WifiDevice();
        wifiDevice.setMacAddress(ipAddress);
        wifiDevice.setIpAddress(ipAddress);
        wifiDevice.setCapability(capability);
        wifiDevice.setName(ipAddress);
        Message msg = obtainMessage(801);
        msg.obj = wifiDevice;
        msg.arg1 = 802;
        msg.sendToTarget();
    }

    @Override // com.ts.car.dcm.wirelesscontroller.DeviceMonitor
    public void processMonitorStart() {
        this.mMonitorReady = true;
        Log.d(TAG, "processMonitorStart, Start.");
        WifiDeviceDetection wifiDeviceDetection = WifiDeviceDetection.getInstance(this);
        this.mWifiDeviceDetection = wifiDeviceDetection;
        if (wifiDeviceDetection != null) {
            wifiDeviceDetection.startDetectDevice();
        }
        if (this.mWifiManager.isWifiApEnabled()) {
            this.mWifiApLocalState = 13;
        }
        WifiDevice wifiDevice = fromWifiDeviceInfo(this.mWifiManager.getWifiDevice());
        WifiConnection wifiConnection = WifiConnection.make(this.mWifiConnLooper, wifiDevice, this, this.mPlatformCapability);
        wifiConnection.sendMessage(20);
    }

    private WifiDevice fromWifiDeviceInfo(WifiDeviceInfo wd) {
        WifiDevice wifiDevice = new WifiDevice();
        wifiDevice.setMacAddress(wd.macAddress);
        wifiDevice.setIpAddress(wd.ipAddress);
        wifiDevice.setCapability(4096);
        wifiDevice.setName(wd.name);
        return wifiDevice;
    }

    @Override // com.ts.car.dcm.wirelesscontroller.DeviceMonitor
    public void processMonitorStop() {
        this.mMonitorReady = false;
        Log.d(TAG, "WifiDeviceMonitor Stop.");
        WifiDeviceDetection wifiDeviceDetection = this.mWifiDeviceDetection;
        if (wifiDeviceDetection != null) {
            wifiDeviceDetection.stopDetectDevice();
        }
        getLooper().quit();
    }

    @Override // com.ts.car.dcm.wirelesscontroller.DeviceMonitor
    public void processAcceptDevice(Bundle data) {
        if (data != null) {
            String devId = data.getString(WirelessMonitorConstants.BUNDLE_ADDRESS);
            boolean result = data.getBoolean(WirelessMonitorConstants.BUNDLE_RESULT);
            WifiConnection conn = this.mWifiConnections.get(devId);
            if (conn != null && result) {
                conn.sendMessage(11, Boolean.valueOf(result));
            }
        }
    }

    @Override // com.ts.car.dcm.wirelesscontroller.DeviceMonitor
    public void processSetCapability(int capability) {
        this.mPlatformCapability = capability;
        for (WifiConnection conn : this.mWifiConnections.values()) {
            conn.sendMessage(10, capability);
        }
        Log.d(TAG, "processSetCapability, mPlatformCapability:" + this.mPlatformCapability);
    }

    private boolean isValidChannel(int band, int channel) {
        Log.d(TAG, "isValidband:" + band + "chanal:" + channel);
        if (band == 2) {
            String channellist = this.mWirelessController.getWirelessConfig("WifiAp5GChannelList");
            if (channellist == null || channellist.isEmpty()) {
                channellist = WirelessMonitorConstants.DEFAULT_5G_CHANNEL_LIST;
            }
            String[] channels = channellist.split(",");
            for (String tmpChannel : channels) {
                if (Integer.parseInt(tmpChannel) == channel) {
                    return true;
                }
            }
        }
        return false;
    }

    private void processEnableWifiAp(boolean enable, SoftApConfiguration config) {
        if (!enable) {
            Log.d(TAG, "processEnableWifiAp, enable:" + enable + ", call ConnectivityManager.stopTethering().");
            this.mWifiManager.stopTethering(0);
            return;
        }
        boolean isWifiApEnabled = this.mWifiManager.isWifiApEnabled();
        if (!isWifiApEnabled) {
            if (config == null) {
                Log.d(TAG, "processEnableWifiAp, enable wifi ap with null configuration.");
                this.mWifiManager.startTethering(0, false, new WifiStartTetheringCallback() { // from class: com.ts.car.dcm.wirelesscontroller.WifiDeviceMonitor.1
                    @Override // com.ts.car.dcm.wirelesscontroller.wifi.WifiStartTetheringCallback
                    public void onTetheringStarted() {
                        Log.d(WifiDeviceMonitor.TAG, "onTetheringStarted");
                    }

                    @Override // com.ts.car.dcm.wirelesscontroller.wifi.WifiStartTetheringCallback
                    public void onTetheringFailed() {
                        Log.e(WifiDeviceMonitor.TAG, "onTetheringFailed");
                    }
                });
                return;
            } else {
                this.mWifiManager.handleStartWifiAp(config, isWifiApSsidRandom());
                return;
            }
        }
        if (config == null) {
            Log.d(TAG, "processEnableWifiAp, wifi ap was already enabled.");
            this.mWirelessController.notifyWifiApInfo(config, 13);
            return;
        }
        SoftApConfiguration currentConfig = this.mWifiManager.getSoftApConfiguration();
        int currentBand = getWifiApBandInternal(currentConfig);
        int currentChannel = getWifiApChannelInternal(currentConfig);
        int wantedBand = getWifiApBandInternal(config);
        if (currentBand != wantedBand || isWifiApSsidRandom() || !isValidChannel(currentBand, currentChannel)) {
            Log.d(TAG, "processEnableWifiAp, wifi ap running with un-expected band or channel, stop it.");
            this.mWifiManager.stopTethering(0);
            Message msg = obtainMessage(WirelessMonitorConstants.EVENT_WIFI_AP_STOP_TIME_OUT);
            this.mWifiApConfig = config;
            sendMessageDelayed(msg, WirelessMonitorConstants.WIFI_AP_STOP_TIME_OUT);
            return;
        }
        Log.d(TAG, "processEnableWifiAp, wifi ap already enabled with the specified band and channel.");
        this.mWirelessController.notifyWifiApInfo(config, 13);
    }

    private boolean isWifiApSsidRandom() {
        String keyWifiApSsid = this.mWirelessController.getWirelessConfig("WifiApSsidRandom");
        if (keyWifiApSsid != null) {
            return Boolean.parseBoolean(keyWifiApSsid);
        }
        return false;
    }

    private void processWifiApStateChange(int state) {
        if (this.mWirelessController != null) {
            if (state == 11) {
                HashMap<String, WifiConnection> map = this.mWifiConnections;
                if (map != null) {
                    for (Map.Entry<String, WifiConnection> entry : map.entrySet()) {
                        WifiConnection conn = entry.getValue();
                        if (conn != null) {
                            conn.sendMessage(21);
                        }
                    }
                }
                this.mWifiApLocalState = 11;
                if (hasMessages(WirelessMonitorConstants.EVENT_WIFI_AP_STOP_TIME_OUT) && this.mWifiApConfig != null) {
                    removeMessages(WirelessMonitorConstants.EVENT_WIFI_AP_STOP_TIME_OUT);
                    processEnableWifiAp(true, this.mWifiApConfig);
                    this.mWifiApConfig = null;
                }
            } else if (state == 10) {
                this.mWifiApLocalState = 10;
            } else if (state == 12) {
                this.mWifiApLocalState = 12;
            } else if (state == 13) {
                this.mWifiApLocalState = 13;
            } else if (state == 14) {
                this.mWifiApLocalState = 14;
            }
            if (this.mWifiApLocalState != 0) {
                SoftApConfiguration config = this.mWifiManager.getSoftApConfiguration();
                this.mWirelessController.notifyWifiApInfo(config, this.mWifiApLocalState);
            }
        }
    }

    private void processWifiConnectChange(WifiDevice wifiDevice, int event) {
        if (wifiDevice != null) {
            String macAddress = wifiDevice.getMacAddress();
            if (event == 802) {
                Log.d(TAG, "processWifiConnectChange, connected macAddress:" + LogEncrypt.MacAddressEncrypt(macAddress) + ", event:" + event);
                if (this.mWifiConnections.get(macAddress) == null) {
                    WifiConnection wifiConnection = WifiConnection.make(this.mWifiConnLooper, wifiDevice, this, this.mPlatformCapability);
                    this.mWifiConnections.put(macAddress, wifiConnection);
                    wifiConnection.sendMessage(20);
                    return;
                }
                Log.d(TAG, "processWifiConnectChange, wifi connection is exist.");
                return;
            }
            if (event == 803) {
                Log.d(TAG, "processWifiConnectChange, disconnected macAddress:" + LogEncrypt.MacAddressEncrypt(macAddress) + ", event:" + event);
                WifiConnection wifiConnection2 = this.mWifiConnections.get(macAddress);
                if (wifiConnection2 != null) {
                    wifiConnection2.sendMessage(21);
                }
            }
        }
    }

    @Override // com.ts.car.dcm.wirelesscontroller.DeviceMonitor
    public void processWirelessDeviceAttach(LinkDevice linkDevice) {
        if (this.mWirelessController != null && linkDevice != null) {
            Log.d(TAG, "processWirelessDeviceAttach, address:" + LogEncrypt.IpAddressEncrypt(linkDevice.getId()));
            this.mWirelessController.notifyWirelessDeviceAttach(linkDevice);
            this.mWirelessController.notifyWifiConnectStatus(linkDevice.getId(), 1);
        }
    }

    public LinkDevice getWifiLinkDeviceByIpaddr(String ipAddr) {
        LinkDevice device = null;
        for (WifiConnection connection : this.mWifiConnections.values()) {
            String ip = connection.getIpAddress();
            if (ipAddr != null && ipAddr.equals(ip)) {
                device = (LinkDevice) connection.getLinkDevice().clone();
            }
        }
        return device;
    }

    @Override // com.ts.car.dcm.wirelesscontroller.DeviceMonitor
    public void processWirelessDeviceChange(LinkDevice linkDevice, int reason) {
        if (this.mWirelessController != null && linkDevice != null) {
            Log.d(TAG, "processWirelessDeviceChange, address:" + LogEncrypt.IpAddressEncrypt(linkDevice.getId()) + ", capability:" + linkDevice.getCapability());
            this.mWirelessController.notifyWirelssDeviceChange(linkDevice, reason);
        }
    }

    @Override // com.ts.car.dcm.wirelesscontroller.DeviceMonitor
    public void processWirelessDeviceDetach(LinkDevice linkDevice) {
        WifiConnection conn;
        if (linkDevice != null && (conn = this.mWifiConnections.get(linkDevice.getId())) != null) {
            if (!conn.isRemovedState()) {
                conn.quit();
                this.mWifiConnections.remove(linkDevice.getId());
            }
            if (this.mWirelessController != null) {
                Log.d(TAG, "processWirelessDeviceDetach, address:" + LogEncrypt.IpAddressEncrypt(linkDevice.getId()));
                this.mWirelessController.notifyWirelessDeviceDetach(linkDevice.getId(), 3);
                this.mWirelessController.notifyWifiConnectStatus(linkDevice.getId(), 0);
            }
        }
    }

    public boolean deleteWifiDevice(String macAddress) {
        WifiConnection conn = this.mWifiConnections.get(macAddress);
        if (conn != null) {
            conn.sendMessage(22);
            return true;
        }
        return true;
    }

    public boolean reattachWifiDevice(String macAddress) {
        WifiConnection conn = this.mWifiConnections.get(macAddress);
        if (conn != null) {
            conn.sendMessage(24);
            return true;
        }
        return true;
    }

    public int getWifiApBand() {
        return this.mWifiApBand;
    }

    private class WifiApBandChannel {
        public int band;
        public int channel;

        public WifiApBandChannel(int band, int channel) {
            this.band = band;
            this.channel = channel;
        }
    }

    private int getWifiApBandInternal(SoftApConfiguration config) {
        try {
            int mBand = config.getBand();
            return mBand;
        } catch (Exception ex) {
            Log.e(TAG, "Exception in getWifiApBandInternal", ex);
            return 0;
        }
    }

    private int getWifiApChannelInternal(SoftApConfiguration config) {
        try {
            int mChannel = config.getChannel();
            return mChannel;
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    private SoftApConfiguration convertWifiApConfiguration(WifiApConfiguration config) {
        int wantedBand = config.getWifiApBand();
        int wantedChannel = config.getWifiApChannel();
        SoftApConfiguration currentConfig = this.mWifiManager.getSoftApConfiguration();
        Log.d(TAG, "convertWifiApConfiguration band:" + wantedBand + " channel:" + wantedChannel);
        SoftApConfiguration.Builder configBuilder = new SoftApConfiguration.Builder(currentConfig);
        try {
            configBuilder.setChannel(wantedChannel, wantedBand);
            return configBuilder.build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
