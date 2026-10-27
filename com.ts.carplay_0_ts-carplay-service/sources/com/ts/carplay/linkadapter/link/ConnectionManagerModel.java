package com.ts.carplay.linkadapter.link;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.text.TextUtils;
import com.ts.carplay.common.data.CarPlayDevice;
import com.ts.carplay.common.util.LogEncrypt;
import com.ts.carplay.common.util.LogUtil;
import com.ts.carplay.linkadapter.device.data.LinkDevice;
import java.util.Map;
import ts.car.dcm.common.data.CmDevice;
import ts.car.service.dcm.data.ConnectionDevice;
import ts.car.service.dcm.data.Device;

/* JADX INFO: loaded from: classes.dex */
public class ConnectionManagerModel {
    private static final String TAG = "ConnectionManagerModel";
    private static boolean USB_HISTORY_CAP_INVALID = true;
    private static KnownWirelessAttributes sKnownWirelessAttributes = new KnownWirelessAttributes();

    public static class KnownWirelessAttributes {
        private String mBluetoothTransportIdentifier = null;
        private int mWirelessAvailableState = 0;

        public String getBluetoothTransportIdentifier() {
            return this.mBluetoothTransportIdentifier;
        }

        public void setBluetoothTransportIdentifier(String bluetoothTransportIdentifier) {
            this.mBluetoothTransportIdentifier = bluetoothTransportIdentifier;
        }

        public int getWirelessAvailableState() {
            return this.mWirelessAvailableState;
        }

        public void setWirelessAvailableState(int wirelessAvailableState) {
            this.mWirelessAvailableState = wirelessAvailableState;
        }

        public String toString() {
            return "KnownWirelessAttributes {mBluetoothTransportIdentifier:" + this.mBluetoothTransportIdentifier + ", mWirelessAvailableState:" + this.mWirelessAvailableState + "}";
        }
    }

    public static int getCmsCapability(LinkDevice device) {
        if (device == null) {
            return -1;
        }
        if (device.getLinkType() == 1) {
            return 64;
        }
        if (device.getLinkType() != 2) {
            return -1;
        }
        return 512;
    }

    public static int getCmsCapabilityState(int linkStatus) {
        if (linkStatus == 4 || linkStatus == 5) {
            return 1;
        }
        if (linkStatus == 3) {
            return 2;
        }
        if (linkStatus == 1) {
            return 3;
        }
        if (linkStatus != 2) {
            return 0;
        }
        return 4;
    }

    public static int convertLinkState(int linkStatus) {
        if (linkStatus == 1) {
            return 4;
        }
        if (linkStatus == 2) {
            return 3;
        }
        if (linkStatus == 3) {
            return 1;
        }
        if (linkStatus != 4) {
            return 4;
        }
        return 2;
    }

    public static LinkDevice toCarPlayDevice(Device dev) {
        if (dev == null) {
            return null;
        }
        LinkDevice device = new LinkDevice();
        device.setDeviceId(dev.getDeviceId());
        device.setStatus(4);
        device.setUsbSerialNumber(dev.getUsbSerialNumber());
        device.setBtAddr(dev.getBtAddr());
        device.setWifiAddr(dev.getWifiAddr());
        return device;
    }

    public static LinkDevice toCarPlayDevice(CarPlayDevice dev) {
        if (dev == null) {
            return null;
        }
        LinkDevice device = new LinkDevice();
        device.setDeviceId(dev.getDeviceId());
        device.setStatus(dev.getDeviceStatus());
        device.setUsbSerialNumber(dev.getUsbSerialNumber());
        device.setBtAddr(dev.getBtAddr());
        device.setWifiAddr(dev.getWifiAddr());
        return device;
    }

    public static LinkDevice toCarPlayDevice(CmDevice cmDevice, int capability) {
        LinkDevice device = null;
        if (cmDevice != null) {
            device = new LinkDevice();
            device.setDeviceId(cmDevice.getDeviceId());
            device.setStatus(4);
            device.setUsbSerialNumber(cmDevice.getUsbSerialNumber());
            device.setBtAddr(cmDevice.getBtAddr());
            device.setWifiAddr(cmDevice.getWifiAddr());
            if (capability == 64) {
                device.setLinkType(1);
            } else if (capability == 512) {
                device.setLinkType(2);
            }
        }
        return device;
    }

    public static CarPlayDevice toCarPlayAidlDevice(ConnectionDevice device) {
        KnownWirelessAttributes attributes;
        if (device == null) {
            return null;
        }
        CarPlayDevice dev = new CarPlayDevice();
        dev.setDeviceName(device.getFriendlyName());
        dev.setUsbSerialNumber(device.getUsbSerialNumber());
        dev.setBtAddr(device.getBtAddr());
        dev.setWifiAddr(device.getWifiAddr());
        dev.setProjectionConnectedTimeMillis(device.getUsbCarplayConnectedTime() > device.getWirelessCarplayConnectedTime() ? device.getUsbCarplayConnectedTime() : device.getWirelessCarplayConnectedTime());
        if (device.getConnectedTypeState(1) == 3 && device.getActiveCapability() == 64) {
            dev.setUsbDevice(true);
        }
        dev.setIsFirstConntect(device.isCarplayFirstConn());
        int usbProjectionState = device.getActiveCapabilityState(64);
        int wirelessProjectionState = device.getActiveCapabilityState(512);
        if (device.getDeviceConnectedState() == 2) {
            dev.setDeviceStatus(-1);
        } else if (usbProjectionState == 4) {
            dev.setDeviceStatus(4);
            dev.setProjectionConnectType(1);
        } else if (wirelessProjectionState == 4) {
            dev.setDeviceStatus(4);
            dev.setProjectionConnectType(2);
        } else if (usbProjectionState == 3) {
            dev.setDeviceStatus(3);
            dev.setProjectionConnectType(1);
        } else if (wirelessProjectionState == 3) {
            dev.setDeviceStatus(3);
            dev.setProjectionConnectType(2);
        } else if (usbProjectionState == 2) {
            dev.setDeviceStatus(2);
            dev.setProjectionConnectType(1);
        } else if (wirelessProjectionState == 2) {
            dev.setDeviceStatus(2);
            dev.setProjectionConnectType(2);
        } else if (device.isAvailableCapability(64) || device.isAvailableCapability(512) || (device.getCapability() & 512) > 0) {
            dev.setDeviceStatus(1);
        } else if ((device.getCapability() & 64) <= 0 || USB_HISTORY_CAP_INVALID) {
            dev.setDeviceStatus(-1);
        } else {
            dev.setDeviceStatus(0);
        }
        dev.setCurWirelessCap(device.isAvailableCapability(512));
        synchronized (sKnownWirelessAttributes) {
            attributes = sKnownWirelessAttributes;
        }
        dev.setWirelessAvailableState(0);
        if (attributes != null) {
            String btMac = device.getBtAddr();
            if (!TextUtils.isEmpty(btMac) && btMac.equals(attributes.getBluetoothTransportIdentifier())) {
                dev.setWirelessAvailableState(attributes.getWirelessAvailableState());
            }
        }
        return dev;
    }

    public static UsbDevice getUsbDevice(CmDevice cmDevice, Context context) {
        UsbManager usbManager;
        Map<String, UsbDevice> deviceList;
        UsbDevice device;
        if (cmDevice == null || context == null) {
            return null;
        }
        String serialNumber = cmDevice.getUsbSerialNumber();
        if (TextUtils.isEmpty(serialNumber) || (usbManager = (UsbManager) context.getSystemService("usb")) == null || (deviceList = usbManager.getDeviceList()) == null || deviceList.isEmpty()) {
            return null;
        }
        for (Map.Entry<String, UsbDevice> entry : deviceList.entrySet()) {
            if (entry != null && (device = entry.getValue()) != null) {
                String targetSerialNumber = device.getSerialNumber();
                LogUtil.verbose(TAG, "getUsbDevice serialNumber:" + serialNumber + " targetSerialNumber:" + targetSerialNumber);
                if (serialNumber.equals(targetSerialNumber)) {
                    return device;
                }
            }
        }
        return null;
    }

    public static void onKnownWirelessAttributesChanged(String mac, int state) {
        LogUtil.debug(TAG, "onKnownWirelessAttributesChanged mac:" + LogEncrypt.MacAddressEncrypt(mac) + " state:" + state);
        synchronized (sKnownWirelessAttributes) {
            sKnownWirelessAttributes.setBluetoothTransportIdentifier(mac);
            sKnownWirelessAttributes.setWirelessAvailableState(state);
        }
    }
}
