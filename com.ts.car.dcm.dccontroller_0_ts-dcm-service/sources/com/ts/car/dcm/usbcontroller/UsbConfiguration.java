package com.ts.car.dcm.usbcontroller;

import android.util.Log;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class UsbConfiguration {
    private static final String FILE_NAME = "projectionusbconfigs";
    private static final String KEY_GET_ACCESSORY_MATCH_PATH = "AccessoryMatchPath";
    private static final String KEY_GET_BUILTIN_USB_PID = "BuiltinUsbPids";
    private static final String KEY_GET_BUILTIN_USB_SETTING = "BlockBuiltinUsb";
    private static final String KEY_GET_BUILTIN_USB_VID = "BuiltinUsbVids";
    private static final String KEY_GET_EAP_PATH = "EapDevicePath";
    private static final String KEY_GET_HID_DEV_PATH_0 = "HidDevicePath0";
    private static final String KEY_GET_HID_DEV_PATH_1 = "HidDevicePath1";
    private static final String KEY_GET_IAP_DEV_PATH = "IapDevicePath";
    private static final String KEY_GET_IPODOUT_MATCH_PATH = "IpodOutMatchPath";
    private static final String KEY_GET_MULTI_USB_PORT_SETTING = "EnableMultiUsbPort";
    private static final String KEY_GET_NCM_NAME = "NcmName";
    private static final String KEY_GET_ROLE_SWITCH_BUS_PATH = "RoleSwitchBusPath";
    private static final String KEY_GET_SUPPORT_ANDROID_CARLIFE = "SupportAndroidCarlife";
    private static final String KEY_GET_SUPPORT_IOS_CARLIFE = "SupportIosCarlife";
    private static final String KEY_HOST_UEVENT_MATCH_PATHS = "HostUeventMatchPaths";
    private static final String KEY_MONITOR_NO_RESPONSE_DEVICE = "MonitorNoResponseDevice";
    private static final String KEY_MONITOR_UNSUPPORT_DEVICE = "MonitorUnSupportDevice";
    private static final String KEY_MONITOR_USB_HUB_DEVICE = "MonitorUsbHubDevice";
    private static final String KEY_ONLY_OTG_PORT_SUPPORT_PROJECTION = "OnlyOtgPortSupportProjection";
    public static final String KEY_PORTNAME_WITH_OTGPORTPATH = "PortNameWithOtgPortPath";
    private static final String KEY_UNSUPPORT_DEVICE_PIDS = "UnSupportDevicePids";
    private static final String KEY_UNSUPPORT_DEVICE_VIDS = "UnSupportDeviceVids";
    public static final String KEY_USB_UN_PROJECTION_DEVICE_VID_PIDS = "UnProjectionDeviceVidPids";
    private static final String SPLIT_COLON = ":";
    public static final String SPLIT_COMMA = ",";
    public static final String SPLIT_SEMICOLON = ";";
    private static final String TAG = "UsbConfiguration";
    private static final int USB_CONFIG_ACCESSORY_MATCH_PATH = 5;
    private static final int USB_CONFIG_BUILTIN_USB_PID = 12;
    private static final int USB_CONFIG_BUILTIN_USB_SETTING = 10;
    private static final int USB_CONFIG_BUILTIN_USB_VID = 11;
    private static final int USB_CONFIG_COUNT = 23;
    private static final int USB_CONFIG_EAP_PATH = 7;
    private static final int USB_CONFIG_HID_DEV_PATH_0 = 0;
    private static final int USB_CONFIG_HID_DEV_PATH_1 = 1;
    private static final int USB_CONFIG_HOST_USB_UEVENT_PATHS = 19;
    private static final int USB_CONFIG_IAP_DEV_PATH = 2;
    private static final int USB_CONFIG_IPODOUT_MATCH_PATH = 6;
    private static final int USB_CONFIG_MONITOR_NO_RESPONSE_DEVICE = 20;
    private static final int USB_CONFIG_MONITOR_UNSUPPORT_DEVICE = 15;
    private static final int USB_CONFIG_MONITOR_USB_HUB_DEVICE = 18;
    private static final int USB_CONFIG_MULTI_USB_PORT_SETTING = 9;
    private static final int USB_CONFIG_NCM_NAME = 3;
    private static final int USB_CONFIG_ONLY_OTG_PORT_SUPPORT_PROJECTION = 8;
    private static final int USB_CONFIG_PORTNAME_WITH_OTGPORTPATH = 22;
    private static final int USB_CONFIG_ROLE_SWITCH_BUS_PATH = 4;
    private static final int USB_CONFIG_SUPPORT_ANDROID_CARLIFE = 13;
    private static final int USB_CONFIG_SUPPORT_IOS_CARLIFE = 14;
    private static final int USB_CONFIG_UNSUPPORT_DEVICE_PIDS = 17;
    private static final int USB_CONFIG_UNSUPPORT_DEVICE_VIDS = 16;
    private static final String USB_DEFAULT_BLOCK_BUILTIN_USB_SETTING = "false";
    private static final String USB_DEFAULT_MONITOR_HUB_DEVICE = "false";
    private static final String USB_DEFAULT_MONITOR_NO_RESPONSE_DEVICE = "false";
    private static final String USB_DEFAULT_MONITOR_UNSUPPORT_DEVICE = "true";
    private static final String USB_DEFAULT_MULTI_USB_SETTING = "true";
    private static final String USB_DEFAULT_ONLY_OTG_PORT_SUPPORT_PROJECTION = "true";
    private static final String USB_DEFAULT_SUPPORT_ANDROID_CARLIFE = "true";
    private static final String USB_DEFAULT_SUPPORT_IOS_CARLIFE = "true";
    private static final int USB_UN_PROJECTION_DEVICE_VID_PIDS = 21;
    private HashMap<String, String> mPortNameWithOtgPortPath = new HashMap<>();
    HashMap<Integer, Integer> mUnProjectionDeviceVidPidsMap = new HashMap<>();
    private static UsbConfiguration sUsbConfiguration = null;
    private static final String USB_DEFAULT_IPOD_HID_DEV_PATH_0 = "/dev/hidraw0";
    private static final String USB_DEFAULT_IPOD_HID_DEV_PATH_1 = "/dev/hidraw1";
    private static final String USB_DEFAULT_CARPLAY_IAP_DEV_PATH = "/dev/ipodout0";
    private static final String USB_DEFAULT_NCM_NAME = "usb0";
    private static final String USB_DEFAULT_ROLESWITCH_DEV_BUS_PATH = "/dev/bus/usb/003";
    private static final String USB_DEFAULT_ACCESSORY_MATCH_PATH = "/devices/virtual/android_usb/android0";
    private static final String USB_DEFAULT_IPODOUT_MATCH_PATH = "/devices/virtual/ipodout/ipodout0";
    private static final String USB_DEFAULT_EAP_DEV_PATH = "/dev/ea_native_transport";
    private static final String USB_DEFAULT_BUILTIN_USB_VID = "6610,12378,1478";
    private static final String USB_DEFAULT_BUILTIN_USB_PID = "5238,5126,22,5122,36872";
    private static final String USB_DEFAULT_UNSUPPORT_VIDS = "6666,1133,1118,6121,18017";
    private static final String USB_DEFAULT_UNSUPPORT_PIDS = "512,514,2628,2066,49271,49237,2";
    private static final String USB_DEFAULT_HOST_UEVENT_MATCH_PATHS = "/devices/soc/7600000.hsusb/7600000.dwc3/xhci@hcd.0.auto/usb1/1@1;/devices/soc/6a00000.ssusb/6a00000.dwc3/xhci@hcd.1.auto/usb3";
    private static final String USB_DEFAULT_UN_PROJECTION_DEVICE_VID_PIDS = "2965,30507;3034,33107";
    private static final String USB_DEFAULT_PORTNAME_WITH_OTGPORTPATH = "usb3_0:/dev/bus/usb/003";
    private static String[] mDeviceConfigInfo = {USB_DEFAULT_IPOD_HID_DEV_PATH_0, USB_DEFAULT_IPOD_HID_DEV_PATH_1, USB_DEFAULT_CARPLAY_IAP_DEV_PATH, USB_DEFAULT_NCM_NAME, USB_DEFAULT_ROLESWITCH_DEV_BUS_PATH, USB_DEFAULT_ACCESSORY_MATCH_PATH, USB_DEFAULT_IPODOUT_MATCH_PATH, USB_DEFAULT_EAP_DEV_PATH, "true", "true", "false", USB_DEFAULT_BUILTIN_USB_VID, USB_DEFAULT_BUILTIN_USB_PID, "true", "true", "true", USB_DEFAULT_UNSUPPORT_VIDS, USB_DEFAULT_UNSUPPORT_PIDS, "false", USB_DEFAULT_HOST_UEVENT_MATCH_PATHS, "false", USB_DEFAULT_UN_PROJECTION_DEVICE_VID_PIDS, USB_DEFAULT_PORTNAME_WITH_OTGPORTPATH};

    public String getDeviceHidDevPath0() {
        return mDeviceConfigInfo[0];
    }

    public String getDeviceHidDevPath1() {
        return mDeviceConfigInfo[1];
    }

    public String getDeviceIapDevPath() {
        return mDeviceConfigInfo[2];
    }

    public String getDeviceNcmName() {
        return mDeviceConfigInfo[3];
    }

    public String[] getDeviceRoleSwitchDevBusPath() {
        return mDeviceConfigInfo[4].split(";");
    }

    public void setDeviceRoleSwitchDevBusPath(String[] roleSwitchPaths) {
        if (roleSwitchPaths.length == 0) {
            return;
        }
        mDeviceConfigInfo[4] = roleSwitchPaths[0];
        Log.e(TAG, "setDeviceRoleSwitchDevBusPath, path:" + roleSwitchPaths[0]);
        for (int i = 1; i < roleSwitchPaths.length; i++) {
            Log.e(TAG, "setDeviceRoleSwitchDevBusPath, path:" + roleSwitchPaths[i]);
            StringBuilder sb = new StringBuilder();
            String[] strArr = mDeviceConfigInfo;
            sb.append(strArr[4]);
            sb.append(";");
            strArr[4] = sb.toString();
            StringBuilder sb2 = new StringBuilder();
            String[] strArr2 = mDeviceConfigInfo;
            sb2.append(strArr2[4]);
            sb2.append(roleSwitchPaths[i]);
            strArr2[4] = sb2.toString();
        }
    }

    public String getDeviceAccessoryMatchPath() {
        return mDeviceConfigInfo[5];
    }

    public String getDeviceIpodOutMatchPath() {
        return mDeviceConfigInfo[6];
    }

    public String getDeviceEapPath() {
        return mDeviceConfigInfo[7];
    }

    public boolean isOnlyOtgPortSupportProjection() {
        return Boolean.parseBoolean(mDeviceConfigInfo[8]);
    }

    public boolean enableMultiUsbPort() {
        return Boolean.parseBoolean(mDeviceConfigInfo[9]);
    }

    public boolean blockBuiltInUsb() {
        return Boolean.parseBoolean(mDeviceConfigInfo[10]);
    }

    public String getBuiltInUsbVids() {
        return mDeviceConfigInfo[11];
    }

    public String getBuiltInUsbPids() {
        return mDeviceConfigInfo[12];
    }

    public boolean isSupportAndroidCarlife() {
        return Boolean.parseBoolean(mDeviceConfigInfo[13]);
    }

    public boolean isSupportIosCarlife() {
        return Boolean.parseBoolean(mDeviceConfigInfo[14]);
    }

    public boolean isMonitorUnSupportDevice() {
        return Boolean.parseBoolean(mDeviceConfigInfo[15]);
    }

    public String getUnSupportDeviceVids() {
        return mDeviceConfigInfo[16];
    }

    public String getUnSupportDevicePids() {
        return mDeviceConfigInfo[17];
    }

    public boolean isMonitorUsbHubDevice() {
        return Boolean.parseBoolean(mDeviceConfigInfo[18]);
    }

    public void setPortNameWithOtgPortPath(String portNameWithOtgPortPath) {
        String[] portNameArrays = portNameWithOtgPortPath.split(";");
        for (String portName : portNameArrays) {
            String[] otgArrays = portName.split(SPLIT_COLON);
            this.mPortNameWithOtgPortPath.put(otgArrays[0], otgArrays[1]);
        }
    }

    public String getHostUsbUeventMatchPaths() {
        return mDeviceConfigInfo[19];
    }

    public boolean isMonitorNoResponseDevice() {
        return Boolean.parseBoolean(mDeviceConfigInfo[20]);
    }

    private void setUnProjectionDeviceVidPids() {
        String vidpid = mDeviceConfigInfo[21];
        if (vidpid != null) {
            String[] vidPidArray = vidpid.split(";");
            if (vidPidArray.length > 0) {
                for (String vidVipString : vidPidArray) {
                    String[] keyValue = vidVipString.split(",");
                    if (keyValue.length > 0) {
                        this.mUnProjectionDeviceVidPidsMap.put(Integer.valueOf(Integer.parseInt(keyValue[0])), Integer.valueOf(Integer.parseInt(keyValue[1])));
                    }
                }
            }
        }
    }

    public HashMap<Integer, Integer> getUnProjectionDeviceVidPids() {
        return this.mUnProjectionDeviceVidPidsMap;
    }

    public String getOtgPortPathByPortName(String portName) {
        return this.mPortNameWithOtgPortPath.get(portName);
    }

    public String getPortNameByOtgPortPath(String path) {
        if (path == null) {
            Log.e(TAG, "getPortNameByOtgPortPath, path is null.");
            return null;
        }
        for (Map.Entry<String, String> entry : this.mPortNameWithOtgPortPath.entrySet()) {
            if (path.contains(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static synchronized UsbConfiguration getInstance() {
        if (sUsbConfiguration == null) {
            sUsbConfiguration = new UsbConfiguration();
        }
        return sUsbConfiguration;
    }

    private void getAbnormalDeviceConfigInfo(UsbConnController usbConnController) {
        String value = usbConnController.getUsbConfig("MonitorUnSupportDevice");
        if (value != null) {
            mDeviceConfigInfo[15] = value;
        }
        String value2 = usbConnController.getUsbConfig("UnSupportDeviceVids");
        if (value2 != null) {
            mDeviceConfigInfo[16] = value2;
        }
        String value3 = usbConnController.getUsbConfig("UnSupportDevicePids");
        if (value3 != null) {
            mDeviceConfigInfo[17] = value3;
        }
        String value4 = usbConnController.getUsbConfig("MonitorUsbHubDevice");
        if (value4 != null) {
            mDeviceConfigInfo[18] = value4;
        }
        String value5 = usbConnController.getUsbConfig("HostUeventMatchPaths");
        if (value5 != null) {
            mDeviceConfigInfo[19] = value5;
        }
        String value6 = usbConnController.getUsbConfig("MonitorNoResponseDevice");
        if (value6 != null) {
            mDeviceConfigInfo[20] = value6;
        }
    }

    public void getConfigInfo(UsbConnController usbConnController) {
        Log.d(TAG, "getConfigInfo, Start loading usb configure.");
        String value = usbConnController.getUsbConfig("HidDevicePath0");
        if (value != null) {
            mDeviceConfigInfo[0] = value;
        }
        String value2 = usbConnController.getUsbConfig("HidDevicePath1");
        if (value2 != null) {
            mDeviceConfigInfo[1] = value2;
        }
        String value3 = usbConnController.getUsbConfig("IapDevicePath");
        if (value3 != null) {
            mDeviceConfigInfo[2] = value3;
        }
        String value4 = usbConnController.getUsbConfig("NcmName");
        if (value4 != null) {
            mDeviceConfigInfo[3] = value4;
        }
        String value5 = usbConnController.getUsbConfig("RoleSwitchBusPath");
        if (value5 != null) {
            mDeviceConfigInfo[4] = value5;
        }
        String value6 = usbConnController.getUsbConfig("AccessoryMatchPath");
        if (value6 != null) {
            mDeviceConfigInfo[5] = value6;
        }
        String value7 = usbConnController.getUsbConfig("IpodOutMatchPath");
        if (value7 != null) {
            mDeviceConfigInfo[6] = value7;
        }
        String value8 = usbConnController.getUsbConfig("EapDevicePath");
        if (value8 != null) {
            mDeviceConfigInfo[7] = value8;
        }
        String value9 = usbConnController.getUsbConfig("OnlyOtgPortSupportProjection");
        if (value9 != null) {
            mDeviceConfigInfo[8] = value9;
        }
        String value10 = usbConnController.getUsbConfig("EnableMultiUsbPort");
        if (value10 != null) {
            mDeviceConfigInfo[9] = value10;
        }
        String value11 = usbConnController.getUsbConfig("BlockBuiltinUsb");
        if (value11 != null) {
            mDeviceConfigInfo[10] = value11;
        }
        String value12 = usbConnController.getUsbConfig("BuiltinUsbVids");
        if (value12 != null) {
            mDeviceConfigInfo[11] = value12;
        }
        String value13 = usbConnController.getUsbConfig("BuiltinUsbPids");
        if (value13 != null) {
            mDeviceConfigInfo[12] = value13;
        }
        String value14 = usbConnController.getUsbConfig("SupportAndroidCarlife");
        if (value14 != null) {
            mDeviceConfigInfo[13] = value14;
        }
        String value15 = usbConnController.getUsbConfig("SupportIosCarlife");
        if (value15 != null) {
            mDeviceConfigInfo[14] = value15;
        }
        String value16 = usbConnController.getUsbConfig("UnProjectionDeviceVidPids");
        if (value16 != null) {
            mDeviceConfigInfo[21] = value16;
        }
        setUnProjectionDeviceVidPids();
        getAbnormalDeviceConfigInfo(usbConnController);
        String value17 = usbConnController.getUsbConfig(KEY_PORTNAME_WITH_OTGPORTPATH);
        if (value17 != null) {
            mDeviceConfigInfo[22] = value17;
        }
        setPortNameWithOtgPortPath(mDeviceConfigInfo[22]);
        Log.d(TAG, "getConfigInfo, End loading usb configure.");
    }
}
