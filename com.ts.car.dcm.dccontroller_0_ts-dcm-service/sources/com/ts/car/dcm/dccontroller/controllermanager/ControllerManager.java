package com.ts.car.dcm.dccontroller.controllermanager;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.net.wifi.SoftApConfiguration;
import android.util.Log;
import com.ts.car.dcm.common.controller.ConnectionController;
import com.ts.car.dcm.common.controller.IControllerCallback;
import com.ts.car.dcm.common.controller.IUsbControllerCallback;
import com.ts.car.dcm.common.controller.IWirelessControllerCallback;
import com.ts.car.dcm.common.controller.UsbConnectionController;
import com.ts.car.dcm.common.controller.WirelessConnectionController;
import com.ts.car.dcm.common.data.DcmConsumer;
import com.ts.car.dcm.common.data.DcmUtils;
import com.ts.car.dcm.common.data.LinkDevice;
import com.ts.car.dcm.common.data.LogEncrypt;
import com.ts.car.dcm.dccontroller.DcController;
import com.ts.car.dcm.dccontroller.configdata.ConfigManager;
import com.ts.car.dcm.dccontroller.storemanager.Device;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import ts.car.dcm.common.data.BtOobInfo;
import ts.car.dcm.common.data.WifiApConfiguration;

/* JADX INFO: loaded from: classes.dex */
public class ControllerManager {
    private static final String TAG = "ControllerManager";
    private ConfigManager mConfig;
    private Context mContext;
    private DcController mDcController;
    private int mCapabilitys = 0;
    private Map<Integer, ConnectionController> mControllerMap = new ConcurrentHashMap();
    private Map<Integer, IControllerCallback> mControllerCallbackMap = new ConcurrentHashMap();
    private IControllerCallback mUsbCtrlCallback = new IUsbControllerCallback() { // from class: com.ts.car.dcm.dccontroller.controllermanager.ControllerManager.1
        @Override // com.ts.car.dcm.common.controller.IControllerCallback
        public void onDeviceAttach(LinkDevice device) {
            Log.d(ControllerManager.TAG, "onDeviceAttach, mUsbCtrlCallback device:" + device);
            if (ControllerManager.this.mDcController != null) {
                ControllerManager.this.mDcController.sendMessage(100, 0, 0, device);
            }
        }

        @Override // com.ts.car.dcm.common.controller.IControllerCallback
        public void onDeviceDetach(String devId, int conntype) {
            ControllerManager.this.mDcController.sendMessage(101, conntype, 0, devId);
        }

        @Override // com.ts.car.dcm.common.controller.IControllerCallback
        public void onDeviceChanged(LinkDevice device, int reason) {
            ControllerManager.this.mDcController.sendMessage(102, reason, 0, device);
        }

        @Override // com.ts.car.dcm.common.controller.IControllerCallback
        public String onGetConfig(String key) {
            return ControllerManager.this.mConfig.getConfig(key);
        }

        @Override // com.ts.car.dcm.common.controller.IUsbControllerCallback
        public void onRoleSwitchStarting(String devId, int mode) {
            ControllerManager.this.mDcController.sendMessage(DcController.EVENT_USB_ROLESWITCH_STARTING, mode, 0, devId);
        }

        @Override // com.ts.car.dcm.common.controller.IUsbControllerCallback
        public void onRoleSwitchFinished(LinkDevice device, int mode, boolean result) {
            ControllerManager.this.mDcController.sendMessage(151, mode, DcmUtils.boolToInt(result), device);
        }

        @Override // com.ts.car.dcm.common.controller.IUsbControllerCallback
        public void onResetUsbBusStarting(String devId) {
            ControllerManager.this.mDcController.sendMessage(152, 0, 0, devId);
        }

        @Override // com.ts.car.dcm.common.controller.IUsbControllerCallback
        public void onResetUsbFinished(String devId, boolean result) {
            ControllerManager.this.mDcController.sendMessage(153, DcmUtils.boolToInt(result), 0, devId);
        }

        @Override // com.ts.car.dcm.common.controller.IUsbControllerCallback
        public void onUsbAoAStarting(String devId, int mode) {
            ControllerManager.this.mDcController.sendMessage(154, 0, mode, devId);
        }

        @Override // com.ts.car.dcm.common.controller.IUsbControllerCallback
        public void onUsbAoAFinished(LinkDevice device, int mode, boolean result) {
            ControllerManager.this.mDcController.sendMessage(155, DcmUtils.boolToInt(result), mode, device);
        }

        @Override // com.ts.car.dcm.common.controller.IControllerCallback
        public void onAbnormalDeviceAttach(String deviceId, int abnormalType, int connType) {
            ControllerManager.this.mDcController.sendMessage(300, abnormalType, connType, deviceId);
        }

        @Override // com.ts.car.dcm.common.controller.IControllerCallback
        public void onAbnormalDeviceDetach(String deviceId) {
            ControllerManager.this.mDcController.sendMessage(301, 0, 0, deviceId);
        }
    };
    private IControllerCallback mWirelessCtrlCallback = new IWirelessControllerCallback() { // from class: com.ts.car.dcm.dccontroller.controllermanager.ControllerManager.2
        @Override // com.ts.car.dcm.common.controller.IControllerCallback
        public void onDeviceAttach(LinkDevice device) {
            Log.d(ControllerManager.TAG, "onDeviceAttach, mWirelessCtrlCallback device:" + device);
            ControllerManager.this.mDcController.sendMessage(100, 0, 0, device);
        }

        @Override // com.ts.car.dcm.common.controller.IControllerCallback
        public void onDeviceDetach(String devId, int conntype) {
            ControllerManager.this.mDcController.sendMessage(101, conntype, 0, devId);
        }

        @Override // com.ts.car.dcm.common.controller.IControllerCallback
        public void onDeviceChanged(LinkDevice device, int reason) {
            ControllerManager.this.mDcController.sendMessage(102, reason, 0, device);
        }

        @Override // com.ts.car.dcm.common.controller.IControllerCallback
        public String onGetConfig(String key) {
            return ControllerManager.this.mConfig.getConfig(key);
        }

        @Override // com.ts.car.dcm.common.controller.IWirelessControllerCallback
        public void onBtDeleted(String devId, int status) {
            ControllerManager.this.mDcController.sendMessage(203, status, 0, devId);
        }

        @Override // com.ts.car.dcm.common.controller.IWirelessControllerCallback
        public void onBtDisconnected(String devId, int status) {
            ControllerManager.this.mDcController.sendMessage(202, status, 0, devId);
        }

        @Override // com.ts.car.dcm.common.controller.IWirelessControllerCallback
        public void onBtConnected(String devId, int status) {
            ControllerManager.this.mDcController.sendMessage(204, status, 0, devId);
        }

        @Override // com.ts.car.dcm.common.controller.IWirelessControllerCallback
        public void onDisconnectedOtherBtDevices(String devId, int status) {
            ControllerManager.this.mDcController.sendMessage(DcController.EVENT_BT_OTHER_DISCONNECTED, status, 0, devId);
        }

        @Override // com.ts.car.dcm.common.controller.IWirelessControllerCallback
        public void onBtProfileChanged(String devId, int profile, int connstate) {
            ControllerManager.this.mDcController.sendMessage(201, profile, connstate, devId);
        }

        @Override // com.ts.car.dcm.common.controller.IWirelessControllerCallback
        public void onBtCallStateChanged(String devId, boolean inCall) {
            ControllerManager.this.mDcController.sendMessage(DcController.EVENT_BT_CALL_STATE_CHANGED, DcmUtils.boolToInt(inCall), 0, devId);
        }

        @Override // com.ts.car.dcm.common.controller.IWirelessControllerCallback
        public void onBtAudioStateChanged(String devId, boolean inAudioPlay) {
            ControllerManager.this.mDcController.sendMessage(DcController.EVENT_BT_AUDIO_STATE_CHANGED, DcmUtils.boolToInt(inAudioPlay), 0, devId);
        }

        @Override // com.ts.car.dcm.common.controller.IWirelessControllerCallback
        public void onBtStateChanged(int status) {
            ControllerManager.this.mDcController.sendMessage(DcController.EVENT_BT_STATE_CHANGED, status, 0, null);
        }

        @Override // com.ts.car.dcm.common.controller.IWirelessControllerCallback
        public void notifyBtOobPairComplete(String devId, int status) {
            Log.e(ControllerManager.TAG, "notifyBtOobPairComplete, devId:" + LogEncrypt.IpAddressEncrypt(devId) + ", status:" + status);
            ControllerManager.this.mDcController.sendMessage(DcController.EVENT_BT_OOB_PAIR_COMPLETE, status, 0, devId);
        }

        @Override // com.ts.car.dcm.common.controller.IWirelessControllerCallback
        public void onBtPairStateChanged(String mac, int status) {
            ControllerManager.this.mDcController.sendMessage(DcController.EVENT_BT_PAIR_STATE_CHANGED, status, 0, mac);
        }

        @Override // com.ts.car.dcm.common.controller.IWirelessControllerCallback
        public void onWifiApInfo(SoftApConfiguration config, int status) {
            if (config != null) {
                ControllerManager.this.mDcController.sendMessage(DcController.EVENT_WIFI_AP_INFO, status, 0, config);
            }
        }

        @Override // com.ts.car.dcm.common.controller.IWirelessControllerCallback
        public void onWifiConnectStatus(String mac, int status) {
            if (mac != null) {
                ControllerManager.this.mDcController.sendMessage(DcController.EVENT_WIFI_CONNECT_STATE, status, 0, mac);
            }
        }

        @Override // com.ts.car.dcm.common.controller.IWirelessControllerCallback
        public void onWirelessProjectionFound(String mac) {
            Log.d(ControllerManager.TAG, "onWirelessProjectionFound, mac:" + LogEncrypt.MacAddressEncrypt(mac));
            ControllerManager.this.mDcController.sendMessage(DcController.EVENT_WIRELESS_PROJECTION_DEVICE_FOUND, 0, 0, mac);
        }

        @Override // com.ts.car.dcm.common.controller.IControllerCallback
        public void onAbnormalDeviceAttach(String deviceId, int abnormalType, int connType) {
            ControllerManager.this.mDcController.sendMessage(300, abnormalType, connType, deviceId);
        }

        @Override // com.ts.car.dcm.common.controller.IControllerCallback
        public void onAbnormalDeviceDetach(String deviceId) {
            ControllerManager.this.mDcController.sendMessage(301, 0, 0, deviceId);
        }

        @Override // com.ts.car.dcm.common.controller.IWirelessControllerCallback
        public void onCheckCpwlCapability(String mac) {
            ControllerManager.this.mDcController.sendMessage(DcController.EVENT_CHECK_WIRELESS_CARPLAY_CAPABILITY, 0, 0, mac);
        }
    };

    public ControllerManager(DcController dcc, Context ctx) {
        this.mDcController = dcc;
        this.mContext = ctx;
        this.mConfig = dcc.getConfigManager();
        init();
    }

    private void init() {
        ConnectionController controller;
        this.mCapabilitys = this.mConfig.getPlatformCapabilitys();
        for (ControllerInformation jarInfo : this.mConfig.getControllerInformations()) {
            if (jarInfo.isAvailable() && (controller = ConnControllerFactory.getConnController(this.mContext, jarInfo)) != null) {
                int controllerType = controller.getControllerType();
                if (controllerType == 0) {
                    this.mControllerCallbackMap.put(0, this.mWirelessCtrlCallback);
                } else if (controllerType == 1) {
                    this.mControllerCallbackMap.put(1, this.mUsbCtrlCallback);
                }
                controller.init(this.mContext, this.mCapabilitys);
                this.mControllerMap.put(Integer.valueOf(controller.getControllerType()), controller);
            }
        }
        setCapability(this.mCapabilitys);
    }

    public synchronized void startConnectionMonitor() {
        for (ConnectionController controller : this.mControllerMap.values()) {
            Log.e(TAG, "startConnectionMonitor, type:" + controller.getControllerType());
            controller.addControllerListener(this.mControllerCallbackMap.get(Integer.valueOf(controller.getControllerType())));
        }
    }

    public synchronized void stopConnectionMonitor() {
        for (ConnectionController controller : this.mControllerMap.values()) {
            Log.e(TAG, "stopConnectionMonitor, type:" + controller.getControllerType());
            controller.removeControllerListener(this.mControllerCallbackMap.get(Integer.valueOf(controller.getControllerType())));
        }
    }

    public void setCapability(int capability) {
        if (capability > 65535 || capability < 0) {
            Log.e(TAG, "setCapability, invalid capability:" + capability);
            return;
        }
        Log.e(TAG, "setCapability, capability:" + capability);
        for (ConnectionController controller : this.mControllerMap.values()) {
            controller.setCapability(capability);
        }
        this.mCapabilitys = this.mConfig.getPlatformCapabilitys();
    }

    public void updateCarlifeSupportStatus(int deviceType, boolean flag) {
        UsbConnectionController controller = (UsbConnectionController) this.mControllerMap.get(1);
        if (controller != null) {
            controller.updateCarlifeSupportStatus(deviceType, flag);
        }
    }

    public void acceptDeviceConnect(Device device, boolean result) {
        Set<Integer> connTypes = device.getDeviceConnTypeSet();
        Iterator<Integer> it = connTypes.iterator();
        while (it.hasNext()) {
            int type = it.next().intValue();
            ConnectionController controller = null;
            String devId = null;
            if (type == 1) {
                ConnectionController controller2 = this.mControllerMap.get(1);
                controller = controller2;
                devId = getDevUsbUniqueId(device);
            } else if (type == 2) {
                ConnectionController controller3 = this.mControllerMap.get(0);
                controller = controller3;
                devId = device.getBtAddr();
            } else if (type == 3) {
                ConnectionController controller4 = this.mControllerMap.get(0);
                controller = controller4;
                devId = device.getWifiAddr();
            } else {
                Log.e(TAG, "acceptDeviceConnect, illegal connection type:" + type);
            }
            if (controller != null) {
                controller.acceptDevice(devId, result);
            }
        }
    }

    public void setDeviceCapabilitys(Device device) {
        Set<Integer> connTypes = device.getDeviceConnTypeSet();
        Iterator<Integer> it = connTypes.iterator();
        while (it.hasNext()) {
            int type = it.next().intValue();
            if (type == 1) {
                ConnectionController controller = this.mControllerMap.get(1);
                if (controller != null) {
                    controller.setDeviceCapability(getDevUsbUniqueId(device), device.getCapability());
                }
            } else if (type == 2) {
                ConnectionController controller2 = this.mControllerMap.get(0);
                if (controller2 != null) {
                    controller2.setDeviceCapability(device.getBtAddr(), device.getCapability());
                }
            } else if (type == 3) {
                ConnectionController controller3 = this.mControllerMap.get(0);
                if (controller3 != null) {
                    controller3.setDeviceCapability(device.getWifiAddr(), device.getCapability());
                }
            } else {
                Log.e(TAG, "setDeviceCapabilitys, illegal connection type:" + type);
            }
        }
    }

    public UsbDevice getUsbConnectedDevices(Device device) {
        UsbConnectionController controller;
        if (!device.hasConnection(1) || (controller = (UsbConnectionController) this.mControllerMap.get(1)) == null) {
            return null;
        }
        UsbDevice usbDev = controller.getUsbConnectedDevices(getDevUsbUniqueId(device));
        return usbDev;
    }

    public boolean enableUsbDevice(final Device device) {
        return usbCtrlBroadcast(new DcmConsumer.FdConsumer() { // from class: com.ts.car.dcm.dccontroller.controllermanager.-$$Lambda$ControllerManager$iqNG-8lx3lciW4euPejwvcotDGw
            @Override // com.ts.car.dcm.common.data.DcmConsumer.FdConsumer
            public final boolean action(Object obj) {
                return this.f$0.lambda$enableUsbDevice$0$ControllerManager(device, (UsbConnectionController) obj);
            }
        }, device);
    }

    public /* synthetic */ boolean lambda$enableUsbDevice$0$ControllerManager(Device device, UsbConnectionController controller) {
        return controller.connectUsbDevice(getDevUsbUniqueId(device));
    }

    public boolean disableUsbDevice(final Device device) {
        return usbCtrlBroadcast(new DcmConsumer.FdConsumer() { // from class: com.ts.car.dcm.dccontroller.controllermanager.-$$Lambda$ControllerManager$XytF7bQWYBgBl8BiyHbn7LVcI7A
            @Override // com.ts.car.dcm.common.data.DcmConsumer.FdConsumer
            public final boolean action(Object obj) {
                return this.f$0.lambda$disableUsbDevice$1$ControllerManager(device, (UsbConnectionController) obj);
            }
        }, device);
    }

    public /* synthetic */ boolean lambda$disableUsbDevice$1$ControllerManager(Device device, UsbConnectionController controller) {
        return controller.disconnectUsbDevice(getDevUsbUniqueId(device));
    }

    public boolean doUsbRoleSwitch(final Device device, final int mode, final int service) {
        return usbCtrlBroadcast(new DcmConsumer.FdConsumer() { // from class: com.ts.car.dcm.dccontroller.controllermanager.-$$Lambda$ControllerManager$fSfDIefs3jgyAyNSzE8PqlMs4ms
            @Override // com.ts.car.dcm.common.data.DcmConsumer.FdConsumer
            public final boolean action(Object obj) {
                return this.f$0.lambda$doUsbRoleSwitch$2$ControllerManager(device, mode, service, (UsbConnectionController) obj);
            }
        }, device);
    }

    public /* synthetic */ boolean lambda$doUsbRoleSwitch$2$ControllerManager(Device device, int mode, int service, UsbConnectionController controller) {
        return controller.doUsbRoleSwitch(getDevUsbUniqueId(device), mode, service);
    }

    public boolean enableUsbAoA(final Device device, final int mode, final int flag) {
        if (mode != 0 && mode != 1) {
            return false;
        }
        return usbCtrlBroadcast(new DcmConsumer.FdConsumer() { // from class: com.ts.car.dcm.dccontroller.controllermanager.-$$Lambda$ControllerManager$-cs-rwmRNSJ7dxQzIghKyTRdkXw
            @Override // com.ts.car.dcm.common.data.DcmConsumer.FdConsumer
            public final boolean action(Object obj) {
                return this.f$0.lambda$enableUsbAoA$3$ControllerManager(device, mode, flag, (UsbConnectionController) obj);
            }
        }, device);
    }

    public /* synthetic */ boolean lambda$enableUsbAoA$3$ControllerManager(Device device, int mode, int flag, UsbConnectionController controller) {
        return controller.enableUsbAoA(getDevUsbUniqueId(device), mode, flag);
    }

    public boolean enableUsbAoA(final Device device, final String serialNumber, final int mode, final int flag) {
        if (mode != 0 && mode != 1) {
            return false;
        }
        return usbCtrlBroadcast(new DcmConsumer.FdConsumer() { // from class: com.ts.car.dcm.dccontroller.controllermanager.-$$Lambda$ControllerManager$B0R97SeY6MIZ0MaRyrpusr1JnK4
            @Override // com.ts.car.dcm.common.data.DcmConsumer.FdConsumer
            public final boolean action(Object obj) {
                return this.f$0.lambda$enableUsbAoA$4$ControllerManager(device, serialNumber, mode, flag, (UsbConnectionController) obj);
            }
        }, device);
    }

    public /* synthetic */ boolean lambda$enableUsbAoA$4$ControllerManager(Device device, String serialNumber, int mode, int flag, UsbConnectionController controller) {
        return controller.enableUsbAoA(getDevUsbUniqueId(device), serialNumber, mode, flag);
    }

    public /* synthetic */ boolean lambda$resetUsbBus$5$ControllerManager(Device device, UsbConnectionController controller) {
        return controller.resetUsbBus(getDevUsbUniqueId(device));
    }

    public boolean resetUsbBus(final Device device) {
        return usbCtrlBroadcast(new DcmConsumer.FdConsumer() { // from class: com.ts.car.dcm.dccontroller.controllermanager.-$$Lambda$ControllerManager$2zINxv-4ulqEr0aXiIGMw3V-UN4
            @Override // com.ts.car.dcm.common.data.DcmConsumer.FdConsumer
            public final boolean action(Object obj) {
                return this.f$0.lambda$resetUsbBus$5$ControllerManager(device, (UsbConnectionController) obj);
            }
        }, device);
    }

    public /* synthetic */ boolean lambda$removeUsbDevice$6$ControllerManager(Device device, UsbConnectionController controller) {
        return controller.removeUsbDevice(getDevUsbUniqueId(device));
    }

    public boolean removeUsbDevice(final Device device) {
        return usbCtrlBroadcast(new DcmConsumer.FdConsumer() { // from class: com.ts.car.dcm.dccontroller.controllermanager.-$$Lambda$ControllerManager$7vwdmCUvyzNi-zdV2y_DyHV-abg
            @Override // com.ts.car.dcm.common.data.DcmConsumer.FdConsumer
            public final boolean action(Object obj) {
                return this.f$0.lambda$removeUsbDevice$6$ControllerManager(device, (UsbConnectionController) obj);
            }
        }, device);
    }

    public boolean removeUsbConnection(final Device device) {
        return usbCtrlBroadcast(new DcmConsumer.FdConsumer() { // from class: com.ts.car.dcm.dccontroller.controllermanager.-$$Lambda$ControllerManager$Ucczh97M-OrKL9BvP6vZq5Khi6k
            @Override // com.ts.car.dcm.common.data.DcmConsumer.FdConsumer
            public final boolean action(Object obj) {
                return ((UsbConnectionController) obj).deleteUsbConnection(device.getUsbSerialNumber());
            }
        }, null);
    }

    private boolean usbCtrlBroadcast(DcmConsumer.FdConsumer<UsbConnectionController> c, Device device) {
        UsbConnectionController controller;
        if (device != null) {
            if (!device.hasConnection(1) || (controller = (UsbConnectionController) this.mControllerMap.get(1)) == null) {
                return false;
            }
            boolean blRet = c.action(controller);
            return blRet;
        }
        UsbConnectionController controller2 = (UsbConnectionController) this.mControllerMap.get(1);
        if (controller2 == null) {
            return false;
        }
        boolean blRet2 = c.action(controller2);
        return blRet2;
    }

    public boolean removeBtDevice(final Device device) {
        return wirelessCtrlBroadcast(new DcmConsumer.FdConsumer() { // from class: com.ts.car.dcm.dccontroller.controllermanager.-$$Lambda$ControllerManager$7CSla5J_zbxmobdg_aua3__EKw4
            @Override // com.ts.car.dcm.common.data.DcmConsumer.FdConsumer
            public final boolean action(Object obj) {
                return ((WirelessConnectionController) obj).deleteBtDevice(device.getBtAddr());
            }
        }, null);
    }

    public boolean removeBtConnection(final Device device) {
        return wirelessCtrlBroadcast(new DcmConsumer.FdConsumer() { // from class: com.ts.car.dcm.dccontroller.controllermanager.-$$Lambda$ControllerManager$XsJ_TNboTMJ2WrN2X3eYMK8AubU
            @Override // com.ts.car.dcm.common.data.DcmConsumer.FdConsumer
            public final boolean action(Object obj) {
                return ((WirelessConnectionController) obj).deleteBtConnection(device.getBtAddr());
            }
        }, null);
    }

    public boolean disconnectBtDevice(final String mac) {
        return wirelessCtrlBroadcast(new DcmConsumer.FdConsumer() { // from class: com.ts.car.dcm.dccontroller.controllermanager.-$$Lambda$ControllerManager$iw2JV-UszxyTsjofCkQQDidbK1g
            @Override // com.ts.car.dcm.common.data.DcmConsumer.FdConsumer
            public final boolean action(Object obj) {
                return ((WirelessConnectionController) obj).disconnectBtDevice(mac);
            }
        }, null);
    }

    public boolean connectBtDevice(final String mac) {
        return wirelessCtrlBroadcast(new DcmConsumer.FdConsumer() { // from class: com.ts.car.dcm.dccontroller.controllermanager.-$$Lambda$ControllerManager$Lw--JO-RyFTi-7DaIALme484gzs
            @Override // com.ts.car.dcm.common.data.DcmConsumer.FdConsumer
            public final boolean action(Object obj) {
                return ((WirelessConnectionController) obj).connectBtDevices(mac);
            }
        }, null);
    }

    public boolean disconnectOtherBtDevices(final Device device) {
        return wirelessCtrlBroadcast(new DcmConsumer.FdConsumer() { // from class: com.ts.car.dcm.dccontroller.controllermanager.-$$Lambda$ControllerManager$lLI6ig2sZxVjwqtOozWyukjYo8M
            @Override // com.ts.car.dcm.common.data.DcmConsumer.FdConsumer
            public final boolean action(Object obj) {
                return ((WirelessConnectionController) obj).disconnectOtherBtDevices(device.getBtAddr());
            }
        }, device);
    }

    public boolean connectBtProfile(final String mac, final int profile) {
        return wirelessCtrlBroadcast(new DcmConsumer.FdConsumer() { // from class: com.ts.car.dcm.dccontroller.controllermanager.-$$Lambda$ControllerManager$dDQAkrBKgwT-1AodxMRYXb0V3zQ
            @Override // com.ts.car.dcm.common.data.DcmConsumer.FdConsumer
            public final boolean action(Object obj) {
                return ((WirelessConnectionController) obj).connectBtProfile(mac, profile);
            }
        }, null);
    }

    public boolean disconnectBtProfile(final String mac, final int profile) {
        return wirelessCtrlBroadcast(new DcmConsumer.FdConsumer() { // from class: com.ts.car.dcm.dccontroller.controllermanager.-$$Lambda$ControllerManager$Rl5Pr2aaLwX_QYz70gpxoBvZ-os
            @Override // com.ts.car.dcm.common.data.DcmConsumer.FdConsumer
            public final boolean action(Object obj) {
                return ((WirelessConnectionController) obj).disconnectBtProfile(mac, profile);
            }
        }, null);
    }

    public boolean isBluetoothEnabled() {
        return wirelessCtrlBroadcast(new DcmConsumer.FdConsumer() { // from class: com.ts.car.dcm.dccontroller.controllermanager.-$$Lambda$ControllerManager$X2P-g3w2YwQPzlC2FaQRI-4wA0g
            @Override // com.ts.car.dcm.common.data.DcmConsumer.FdConsumer
            public final boolean action(Object obj) {
                return ((WirelessConnectionController) obj).isBluetoothEnabled();
            }
        }, null);
    }

    public boolean enableBluetooth(final boolean flag) {
        return wirelessCtrlBroadcast(new DcmConsumer.FdConsumer() { // from class: com.ts.car.dcm.dccontroller.controllermanager.-$$Lambda$ControllerManager$SjIBGyGrd4cUGvduo8yfpSq9ss8
            @Override // com.ts.car.dcm.common.data.DcmConsumer.FdConsumer
            public final boolean action(Object obj) {
                return ((WirelessConnectionController) obj).enableBluetooth(flag);
            }
        }, null);
    }

    public BtOobInfo getBtOobInfo() {
        WirelessConnectionController controller = (WirelessConnectionController) this.mControllerMap.get(0);
        if (controller == null) {
            return null;
        }
        BtOobInfo oobInfo = controller.getBtOobInfo();
        return oobInfo;
    }

    public boolean setBtOobInfo(final String name, final String linkKey, final String mac, final int linkKeyType) {
        return wirelessCtrlBroadcast(new DcmConsumer.FdConsumer() { // from class: com.ts.car.dcm.dccontroller.controllermanager.-$$Lambda$ControllerManager$I8uT62cJoTw1wTiMD7GEFRgaS5o
            @Override // com.ts.car.dcm.common.data.DcmConsumer.FdConsumer
            public final boolean action(Object obj) {
                return ((WirelessConnectionController) obj).setBtOobInfo(name, linkKey, mac, linkKeyType);
            }
        }, null);
    }

    public boolean requestBluetoothPairing(final String mac) {
        return wirelessCtrlBroadcast(new DcmConsumer.FdConsumer() { // from class: com.ts.car.dcm.dccontroller.controllermanager.-$$Lambda$ControllerManager$nYZGw1b2iNqOgVmnLV4SivdWykM
            @Override // com.ts.car.dcm.common.data.DcmConsumer.FdConsumer
            public final boolean action(Object obj) {
                return ((WirelessConnectionController) obj).requestBluetoothPairing(mac);
            }
        }, null);
    }

    public boolean enableWifiAp(final boolean enable, final WifiApConfiguration config) {
        return wirelessCtrlBroadcast(new DcmConsumer.FdConsumer() { // from class: com.ts.car.dcm.dccontroller.controllermanager.-$$Lambda$ControllerManager$RQyhmVYiLybBNsDXlAz1d5GttDE
            @Override // com.ts.car.dcm.common.data.DcmConsumer.FdConsumer
            public final boolean action(Object obj) {
                return ((WirelessConnectionController) obj).enableWifiAp(enable, config);
            }
        }, null);
    }

    public LinkDevice getWifiLinkDeviceByIpaddr(String ipAddr) {
        WirelessConnectionController controller = (WirelessConnectionController) this.mControllerMap.get(0);
        if (controller == null) {
            return null;
        }
        LinkDevice dev = controller.getWifiLinkDeviceByIpaddr(ipAddr);
        return dev;
    }

    public boolean removeWifiDevice(final String mac) {
        return wirelessCtrlBroadcast(new DcmConsumer.FdConsumer() { // from class: com.ts.car.dcm.dccontroller.controllermanager.-$$Lambda$ControllerManager$GCD_NoBlx46BdAZwBlllE4Tio3I
            @Override // com.ts.car.dcm.common.data.DcmConsumer.FdConsumer
            public final boolean action(Object obj) {
                return ((WirelessConnectionController) obj).removeWifiDevice(mac);
            }
        }, null);
    }

    public boolean reattachWifiDevice(final String mac) {
        return wirelessCtrlBroadcast(new DcmConsumer.FdConsumer() { // from class: com.ts.car.dcm.dccontroller.controllermanager.-$$Lambda$ControllerManager$mlK2ZgvvrB5MmKHXBG6JcMEf72k
            @Override // com.ts.car.dcm.common.data.DcmConsumer.FdConsumer
            public final boolean action(Object obj) {
                return ((WirelessConnectionController) obj).reattachWifiDevice(mac);
            }
        }, null);
    }

    private boolean wirelessCtrlBroadcast(DcmConsumer.FdConsumer<WirelessConnectionController> c, Device device) {
        if (device != null) {
            Log.d(TAG, "wirelessCtrlBroadcast1");
            if (!device.hasConnection(2)) {
                return false;
            }
            Log.d(TAG, "wirelessCtrlBroadcast1.1");
            WirelessConnectionController controller = (WirelessConnectionController) this.mControllerMap.get(0);
            if (controller == null) {
                return false;
            }
            Log.d(TAG, "wirelessCtrlBroadcast1.2");
            boolean blRet = c.action(controller);
            return blRet;
        }
        Log.d(TAG, "wirelessCtrlBroadcast2");
        WirelessConnectionController controller2 = (WirelessConnectionController) this.mControllerMap.get(0);
        if (controller2 == null) {
            return false;
        }
        Log.d(TAG, "wirelessCtrlBroadcast2.1");
        boolean blRet2 = c.action(controller2);
        return blRet2;
    }

    private String getDevUsbUniqueId(Device device) {
        return device.getUsbSerialNumber() != null ? device.getUsbSerialNumber() : device.getId();
    }
}
