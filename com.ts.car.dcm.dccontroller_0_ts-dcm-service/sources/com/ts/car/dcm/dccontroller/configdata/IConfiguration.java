package com.ts.car.dcm.dccontroller.configdata;

import com.ts.car.dcm.dccontroller.R;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public interface IConfiguration {
    public static final int CONFIG_NAME_ID = R.string.app_name;
    public static final String DEFAULT_CONTROLLERINFORMATION = "UsbConnController.jar,com.ts.car.dcm.usbcontroller.UsbConnController,true,v0;WirelessConnController.jar,com.ts.car.dcm.wirelesscontroller.WirelessConnController,true,v0";
    public static final String DEFAULT_JAR_VERSION = "0";
    public static final String DEFAULT_PROJECTION_PRIORITY = "CarLife,CarPlay,AndroidAuto";
    public static final int DEFAULT_WIFI_AP_CHANNEL_ITEM_LENGTH = 8;
    public static final String KEY_ALLOW_DEVICE_DETECTION = "AllowDeviceDetection";
    public static final String KEY_ANDROIDAUTO_ENABLE = "EnableAndroidauto";
    public static final String KEY_BLOCK_BUILTIN_USB = "BlockBuiltinUsb";
    public static final String KEY_BUILTIN_USB_PIDS = "BuiltinUsbPids";
    public static final String KEY_BUILTIN_USB_VIDS = "BuiltinUsbVids";
    public static final String KEY_CAPABILITY_FROM_PROJECTIONSERVICE = "EnableCapabilityUpdateByService";
    public static final String KEY_CARLIFE_ENABLE = "EnableCarlife";
    public static final String KEY_CARPLAY_ENABLE = "EnableCarplay";
    public static final String KEY_CONTROLLERINFORMATION = "ControllerInformation";
    public static final String KEY_DATABASE_SYSNC_TIME = "DatabaseSyncTime";
    public static final String KEY_DEFAULT_PROJECTION_ON_ANDROID = "DefaultProjectionOnAndroid";
    public static final String KEY_DEFAULT_PROJECTION_ON_IPHONE = "DefaultProjectionOnIphone";
    public static final String KEY_DEVICE_ACCESS_AUTHORIZATION = "DeviceAccessAuthorization";
    public static final String KEY_ENABLE_CWPL_DETECING_OVER_IAP = "EnableCpwlDetectingOverIap";
    public static final String KEY_ENABLE_DEVICE_DETECTION_CONTROL = "EnableDeviceDetectionControl";
    public static final String KEY_ENABLE_INDEX_OF_SAME_DEVICE_NAME = "EnableIndexOfSameDeviceName";
    public static final String KEY_ENABLE_MULTI_USB_PORT = "EnableMultiUsbPort";
    public static final String KEY_GET_ACCESSORY_MATCH_PATH = "AccessoryMatchPath";
    public static final String KEY_GET_EAP_PATH = "EapDevicePath";
    public static final String KEY_GET_HID_DEV_PATH_0 = "HidDevicePath0";
    public static final String KEY_GET_HID_DEV_PATH_1 = "HidDevicePath1";
    public static final String KEY_GET_IAP_DEV_PATH = "IapDevicePath";
    public static final String KEY_GET_IPODOUT_MATCH_PATH = "IpodOutMatchPath";
    public static final String KEY_GET_NCM_NAME = "NcmName";
    public static final String KEY_GET_PHONE_NAME_BY_IAP = "GetPhoneNameByIap";
    public static final String KEY_GET_ROLE_SWITCH_BUS_PATH = "RoleSwitchBusPath";
    public static final String KEY_GET_WIFI_AP_CHANNEL_INFO = "GetWifiApChannelInfo";
    public static final String KEY_HOST_UEVENT_MATCH_PATHS = "HostUeventMatchPaths";
    public static final String KEY_IPOD_ENABLE = "EnableIpod";
    public static final String KEY_MONITOR_NO_RESPONSE_DEVICE = "MonitorNoResponseDevice";
    public static final String KEY_MONITOR_POWER_STATUS = "MonitorPowerStatus";
    public static final String KEY_MONITOR_UNSUPPORT_DEVICE = "MonitorUnSupportDevice";
    public static final String KEY_MONITOR_USB_HUB_DEVICE = "MonitorUsbHubDevice";
    public static final String KEY_NOTIFY_BT_PROJECTION_STATE = "NotifyBtProjectionState";
    public static final String KEY_ONLY_OTG_PORT_SUPPORT_PROJECTION = "OnlyOtgPortSupportProjection";
    public static final String KEY_PROJECTION_AUTO_SHOW = "ProjectionAutoShow";
    public static final String KEY_PROJECTION_CONN_REQUEST_MODE = "ProjectionConnRequestMode";
    public static final String KEY_PROJECTION_HANDOVER = "ProjectionHandover";
    public static final String KEY_PROJECTION_PRIORITY = "ProjectionPriority";
    public static final String KEY_PROJECTION_READY_CASE = "ProjectionReadyCase";
    public static final String KEY_SUPPORT_ANDROIDAUTO = "SupportAndroidauto";
    public static final String KEY_SUPPORT_ANDROID_CARLIFE = "SupportAndroidCarlife";
    public static final String KEY_SUPPORT_CARLIFE = "SupportCarlife";
    public static final String KEY_SUPPORT_CARPLAY = "SupportCarplay";
    public static final String KEY_SUPPORT_IOS_CARLIFE = "SupportIosCarlife";
    public static final String KEY_SUPPORT_IPOD = "SupportIpod";
    public static final String KEY_UNSUPPORT_DEVICE_PIDS = "UnSupportDevicePids";
    public static final String KEY_UNSUPPORT_DEVICE_VIDS = "UnSupportDeviceVids";
    public static final String KEY_UN_PROJECTION_DEVICE_VID_PIDS = "UnProjectionDeviceVidPids";
    public static final String KEY_WIFI_AP_5G_CHANNEL_LIST = "WifiAp5GChannelList";
    public static final String KEY_WIFI_AP_BAND_FOR_CARPLAY = "WifiApBandForCarplay";
    public static final String KEY_WIFI_AP_SSID_RANDOM = "WifiApSsidRandom";
    public static final String SPLIT_COMMA = ",";
    public static final String SPLIT_SEMICOLON = ";";

    HashMap<String, String> getConfigInfo();

    boolean saveConfigInfo(String str, String str2);
}
