package com.ts.car.dcm.common.data;

/* JADX INFO: loaded from: classes.dex */
public class Constants {
    public static final int BOND_BONDED = 12;
    public static final int BOND_BONDING = 11;
    public static final int BOND_NONE = 10;
    public static final String FRIENDLY_NAME_REGEX = "([A-Z]|[a-z]|[0-9]|-|_|'){1,}";
    public static final int PCA_DISCONNECT_WAIT_TIME = 20000;
    public static final int PCA_WAIT_TIME = 10000;
    public static final int PROJECTION_PRCONNECT_WAITTIME = 20000;
    public static final int WIFI_2HGZ_BAND = 1;
    public static final int WIFI_5HGZ_BAND = 0;

    public static final class ArbitrationResult {
        public static final int NO_RESOURCE = 1;
        public static final int OTHER_PROJECTION_EXIST = 2;
        public static final int PARAMETER_ERROR = 3;
        public static final int PENDING_CONNECT = 5;
        public static final int SERVICE_ACTIVATED = 4;
        public static final int SUCCESS = 0;
    }

    public static final class ProjectionChangedSource {
        public static final int CONNCONTROLLER_SOURCE = 2;
        public static final int PROJECTION_SERVICE_SOURCE = 1;
    }

    public static final class SendCpwlIap2AuthResult {
        public static final int DEVICE_NOT_FOUND = 2;
        public static final int REQUEST_LIST_EMPTY = 3;
        public static final int SERVICE_NOT_REGISTER = 4;
        public static final int SUCCESS = 1;
    }

    private Constants() {
    }

    public static final class WifiApState {
        public static final int WIFI_AP_STATE_DISABLED = 11;
        public static final int WIFI_AP_STATE_DISABLING = 10;
        public static final int WIFI_AP_STATE_ENABLED = 13;
        public static final int WIFI_AP_STATE_ENABLING = 12;
        public static final int WIFI_AP_STATE_FAILED = 14;

        private WifiApState() {
        }
    }

    public static final class DeviceStateChangedReason {
        public static final int NOTIFY_CHANGED_CAPABILITY = 3;
        public static final int NOTIFY_CONNECT_COMPLETED = 5;
        public static final int NOTIFY_DEVICE_ATTACHED = 1;
        public static final int NOTIFY_DEVICE_DETACHED = 2;
        public static final int NOTIFY_DEVICE_INFO_CHANGE = 6;
        public static final int NOTIFY_DISCONNECT_COMPLETED = 4;

        private DeviceStateChangedReason() {
        }
    }

    public static final class DeviceTypeString {
        public static final String DEVICETYPE_ANDROID_PHONE = "DEVICETYPE_ANDROID_PHONE";
        public static final String DEVICETYPE_ANY_ACCESSPOINT = "DEVICETYPE_ANY_ACCESSPOINT";
        public static final String DEVICETYPE_ANY_ECU = "DEVICETYPE_ANY_ECU";
        public static final String DEVICETYPE_IPHONE = "DEVICETYPE_IPHONE";
        public static final String DEVICETYPE_IPOD = "DEVICETYPE_IPOD";
        public static final String DEVICETYPE_TCU = "DEVICETYPE_TCU";
        public static final String DEVICETYPE_UNKNOWN = "DEVICETYPE_UNKNOWN";
        public static final String DEVICETYPE_WIRELESSCAMERA = "DEVICETYPE_WIRELESSCAMERA";

        private DeviceTypeString() {
        }
    }

    public static final class ControllerType {
        public static final int ETHERNET = 2;
        public static final int USB = 1;
        public static final int WIRELESS = 0;

        public static boolean isAvailableType(int val) {
            if (val == 0 || val == 1 || val == 2) {
                return true;
            }
            return false;
        }
    }

    public static final class ResultCode {
        public static final int FAILURE = 1;
        public static final int INSERT_FAILED = 3;
        public static final int NO_DEVICE_MEMORY = 4;
        public static final int PARAMETER_ERR = 2;
        public static final int SUCCESS = 0;
        public static final int UPDATE_FAILED = 5;

        ResultCode() {
        }
    }
}
