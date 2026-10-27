package com.ts.androidauto.fsdk.common;

/* JADX INFO: loaded from: classes2.dex */
public class AndroidAutoConst {
    public static final String ANDROIDAUTO_MODE = "AndroidAuto";
    public static final String APPLICATION_CLASS_NAME = "com.ts.androidauto.app.display.AapActivity";
    public static final String APPLICATION_PACKAGE_NAME = "com.ts.androidauto.app";
    public static final String DEBUG_CONNECT_TYPE = "debug.connect.type";
    public static final String DEFAULT_SCREEN_NAME = "";
    public static final String INVALID_MODE = "Invalid";
    public static final int REPEAT_MODE_ALL = 1;
    public static final int REPEAT_MODE_NONE = 0;
    public static final int REPEAT_MODE_ONE = 2;
    public static final int RESULT_OTHER_FAILED = -1;
    public static final int RESULT_PROJECTION_CONNECTED_REQUEST_FOCUS_FAILED = 1;
    public static final int RESULT_PROJECTION_CONNECTED_REQUEST_FOCUS_SUCCESS = 0;
    public static final int RESULT_PROJECTION_NOT_CONNECTED_REQUEST_FOCUS_FAILED = 3;
    public static final int RESULT_PROJECTION_NOT_CONNECTED_REQUEST_FOCUS_SUCCESS = 2;
    public static final String SERVICE_NAME = "SmartProjectionService";
    public static final int SHUFFLE_MODE_OFF = 0;
    public static final int SHUFFLE_MODE_ON = 1;
    public static final String SMART_PROJECTION_RESTART_ACTION = "com.ts.androidauto.SMART_PROJECTION_RESTART_ACTION";
    public static final String SMART_PROJECTION_SERVICE_NAME = "com.ts.androidauto.projectionservice.AndroidAutoService";
    public static final int STATUS_INIT = 256;
    public static final int STATUS_INSTRUCT_USER_TO_CHECK_THE_PHONE = 12;
    public static final int STATUS_INVALID_HOST = -7;
    public static final int STATUS_NO_COMPATIBLE_VERSION = -1;
    public static final int STATUS_NO_SUPPORTED_WIFI_CHANNELS = -8;
    public static final int STATUS_PHONE_WIFI_DISABLED = -10;
    public static final int STATUS_PROJECTION_ALREADY_STARTED = -4;
    public static final int STATUS_SUCCESS = 0;
    public static final int STATUS_UNSOLICITED_MESSAGE = 11;
    public static final int STATUS_WIFI_DISABLED = -5;
    public static final int STATUS_WIFI_INACCESSIBLE_CHANNEL = -2;
    public static final int STATUS_WIFI_INCORRECT_CREDENTIALS = -3;
    public static final int STATUS_WIFI_NETWORK_UNAVAILABLE = -11;
    public static final int STATUS_WIFI_NOT_YET_STARTED = -6;

    public enum AppType {
        APP_TYPE_ROUTE_GUIDANCE,
        APP_TYPE_VR
    }

    public enum BluetoothEnableState {
        BT_STATE_OFF,
        BT_STATE_TURNING_ON,
        BT_STATE_ON,
        BT_STATE_TURNING_OFF
    }

    public enum ConnMode {
        MODE_DEFAULT_CONN,
        MODE_AUTO_CONN,
        MODE_USER_REQUEST
    }

    public enum ConnectReason {
        CONNECT_REASON_DEFAULT_CONN,
        CONNECT_REASON_AUTO_CONN
    }

    public static final class ConnectType {
        public static final int CONNECTION_TYPE_USB = 1;
        public static final int CONNECTION_TYPE_WIRELESS = 2;
    }

    public enum ConnectionType {
        CONNECTION_TYPE_NULL,
        CONNECTION_TYPE_WIRE,
        CONNECTION_TYPE_WIRELESS
    }

    public static final class DeviceStatus {
        public static final int AVAILABLE = 1;
        public static final int CONNECTED = 4;
        public static final int CONNECTING = 3;
        public static final int DISCONNECTING = 2;
        public static final int INVALID = -1;
        public static final int UNAVAILABLE = 0;
    }

    public enum HmiLastMode {
        LAST_MODE_DEFAULT,
        LAST_MODE_HOME,
        LAST_MODE_PHONE,
        LAST_MODE_MEDIA,
        LAST_MODE_NAVI
    }

    public static final class LaunchMode {
        public static final int LAUNCH_MODE_HOME = 1;
        public static final int LAUNCH_MODE_MUSIC = 3;
        public static final int LAUNCH_MODE_NAVI = 4;
        public static final int LAUNCH_MODE_NORMAL = 0;
        public static final int LAUNCH_MODE_PHONE = 2;
    }

    public enum LinkDeviceEvent {
        PROJECTION_EVENT_DEVICE_AVAILABLE,
        PROJECTION_EVENT_DEVICE_LOST
    }

    public enum LinkErrorCode {
        NO_ERROR_CODE,
        ERROR_UNRECOVERABLE_ERROR,
        ERROR_VIDEO_DATA_TIMEOUT,
        ERROR_USER_SELECT_DISCONNECT,
        ERROR_AUTHENTICATION_FAILURE_CERT_NOT_YET_VALID,
        ERROR_WIFI_START_FAILED,
        ERROR_WIFI_CONNECT_STATUS,
        ERROR_WIFI_DISCONNECT,
        ERROR_PING_TIMEOUT,
        ERROR_TCP_CONNECTION_FAILURE,
        ERROR_NOT_SUPPORTED,
        ERROR_WIFI_CONNECT_UNSOLICITED_MESSAGE,
        ERROR_WIFI_CONNECT_INSTRUCT_USER_TO_CHECK_THE_PHONE,
        ERROR_REQUEST_AOA_FAILED
    }

    public enum LinkStatus {
        LINK_STATUS_INIT,
        LINK_STATUS_DEACTIVATED,
        LINK_STATUS_DEACTIVATING,
        LINK_STATUS_ACTIVATING,
        LINK_STATUS_ACTIVATED,
        LINK_STATUS_CONNECT_FAILED,
        LINK_STATUS_AAP_FRX,
        LINK_STATUS_AVAILABLE,
        LINK_STATUS_AAP_USERSWITCH,
        LINK_STATUS_CONNECT_ERROR
    }

    public enum MusicStatus {
        MUSIC_STATUS_NOT_START,
        MUSIC_STATUS_PLAYING,
        MUSIC_STATUS_PAUSED
    }

    public enum PhoneStatus {
        PHONE_DISCONNECTED,
        CALL_ACTIVED,
        CALL_HELD,
        INCOMING_CALL,
        PHONE_CONFERENCED,
        PHONE_MUTED
    }

    public enum ShowMode {
        SHOW_MODE_HOME,
        SHOW_MODE_MEDIA,
        SHOW_MODE_PHONE,
        SHOW_MODE_VR
    }

    public enum VoiceSessionStatus {
        VOICE_RECOGNITION_STATE_STARTED,
        VOICE_RECOGNITION_STATE_ENDED
    }

    public enum LinkMode {
        LINK_MODE_NULL(0),
        LINK_MODE_IPOD(1),
        LINK_MODE_CARPLAY(2),
        LINK_MODE_ANDROIDAUTO(4),
        LINK_MODE_CARLIFE(8),
        LINK_MODE_RFCOMM(16);

        private int mVal;

        LinkMode(int val) {
            this.mVal = -1;
            this.mVal = val;
        }

        public int toNumber() {
            return this.mVal;
        }
    }
}
