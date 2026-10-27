package com.ts.car.dcm.wirelesscontroller;

import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class WirelessMonitorConstants {
    public static final int BLUETOOTH_CALL_STATE_TERMINATED = 7;
    public static final String BUNDLE_ADDRESS = "Address";
    public static final String BUNDLE_CALLITEM = "CallItem";
    public static final String BUNDLE_CAPABILITY = "Capability";
    public static final String BUNDLE_DEVTYPE = "DevType";
    public static final String BUNDLE_ISSUPPORTCPBYUUID = "IsSupportCpByUuid";
    public static final String BUNDLE_RESULT = "Result";
    public static final String CARLIFE_BROADCAST_FLAG = "CarlifeHost";
    public static final int CARLIFE_BROADCAST_PORT = 7999;
    public static final int CHECK_IS_APPLE_DEVICE = 1;
    public static final int CHECK_NOT_APPLE_DEVICE = 0;
    public static final int CHECK_NO_SDP_UUIDS = -1;
    public static final int DATA_SIZE = 512;
    public static final String DEFAULT_5G_CHANNEL_LIST = "36,40,44,48,149,153,157,161,165";
    public static final long DISCOVERY_DEVICE_TIME = 15000;
    public static final int EVENT_BLUETOOTH_AUDIO_STATE_CHANGE = 505;
    public static final int EVENT_BLUETOOTH_BOND_STATE_CHANGE = 502;
    public static final int EVENT_BLUETOOTH_CALL_STATE_CHANGE = 504;
    public static final int EVENT_BLUETOOTH_DEVICE_DISCONNECTED = 503;
    public static final int EVENT_BLUETOOTH_GET_REMOTE_UUIDS = 508;
    public static final int EVENT_BLUETOOTH_OOB_PAIR_COMPLETE = 507;
    public static final int EVENT_BLUETOOTH_PROFILE_STATE_CHANGE = 501;
    public static final int EVENT_BLUETOOTH_STATE_CHANGE = 506;
    public static final int EVENT_BTCONN_PROFILE_STATE_CHANGE = 601;
    public static final int EVENT_CARLIFE_DEVICE_FOUND = 804;
    public static final int EVENT_CARLIFE_FOUND = 805;
    public static final int EVENT_DCM_ACCEPT_DEVICE = 201;
    public static final int EVENT_DCM_DISCONNECT_OTHER_BT_DEVICES = 203;
    public static final int EVENT_DCM_SET_DEVICE_CAPABILITY = 202;
    public static final int EVENT_DCM_SET_PLATFORM_CAPABILITY = 200;
    public static final int EVENT_DETECT_BT_DISCOVERY_TIMEOUT = 703;
    public static final int EVENT_DETECT_BT_FETCH_UUID_TIMEOUT = 702;
    public static final int EVENT_DETECT_WIRELESS_DEVICE = 701;
    public static final int EVENT_ENABLE_WIFI_AP = 204;
    public static final int EVENT_MONITOR_START = 101;
    public static final int EVENT_MONITOR_STOP = 102;
    public static final int EVENT_WIFI_AP_CONNECT_CHANGE = 301;
    public static final int EVENT_WIFI_AP_STATE_CHANGE = 300;
    public static final int EVENT_WIFI_AP_STOP_TIME_OUT = 302;
    public static final int EVENT_WIFI_STA_CONNECTE_CHANGE = 801;
    public static final int EVENT_WIFI_STA_DISENABLE = 803;
    public static final int EVENT_WIFI_STA_ENABLE = 802;
    public static final int EVENT_WIRELESS_DEVICE_ATTACH = 400;
    public static final int EVENT_WIRELESS_DEVICE_CHANGE = 401;
    public static final int EVENT_WIRELESS_DEVICE_DETACH = 402;
    public static final long FETCH_UUID_TIME = 1000;
    public static final String KEY_WIFI_AP_5G_CHANNEL_LIST = "WifiAp5GChannelList";
    public static final String KEY_WIFI_AP_SSID_RANDOM = "WifiApSsidRandom";
    public static final int MONITOR_TYPE_BT = 0;
    public static final int MONITOR_TYPE_WIFI = 1;
    public static final String SPLIT_COMMA = ",";
    public static final UUID UUID_IAP_APPLE = UUID.fromString("fecacade-afde-cade-defa-cade00000000");
    public static final long WIFI_AP_STOP_TIME_OUT = 5000;

    public static String getCheckIphoneResult(int value) {
        if (value == 1) {
            return "iphone";
        }
        if (value == 0) {
            return "not iphone";
        }
        return "unknown value";
    }

    public static String getSupportCarplayDescribe(int value) {
        if (value == 2) {
            return "support";
        }
        if (value == -1 || value == 1) {
            return "not support";
        }
        if (value == 0) {
            return "support unkown";
        }
        return "unknown value";
    }
}
