package ts.car.service.voiceactuator;

/* JADX INFO: loaded from: classes.dex */
public class VoiceType {
    public static final int TYPE_APP = 3;
    public static final int TYPE_CALL = 2;
    public static final int TYPE_DEVICE = 5;
    public static final int TYPE_LIGHT = 4;
    public static final int TYPE_MUSIC = 8;
    public static final int TYPE_NAVI = 9;
    public static final int TYPE_SCENE = 6;
    public static final int TYPE_VEHICLE = 1;
    public static final int TYPE_VIEW_CONTROL = 10;
    public static final int TYPE_VOLUME = 7;

    public static final class AppType {
        public static final int START_DESIGNATED_APP = 1;
        public static final int START_FUZZY_APP = 2;
    }

    public static final class CallType {
        public static final int BT_CALL_ANSWER = 2;
        public static final int BT_CALL_CALL_NUMBER = 1;
        public static final int BT_CALL_HANDUP = 3;
        public static final int B_CALL_HANDUP = 7;
        public static final int B_CALL_START = 6;
        public static final int E_CALL_HANDUP = 5;
        public static final int E_CALL_START = 4;
    }

    public static final class DeviceType {
        public static final int BLUETOOTH_CLOSE = 8;
        public static final int BLUETOOTH_CONNECT = 9;
        public static final int BLUETOOTH_DISCONNECT = 10;
        public static final int BLUETOOTH_OPEN = 7;
        public static final int HOTSPOT_CLOSE = 6;
        public static final int HOTSPOT_OPEN = 5;
        public static final int WIFI_CLOSE = 2;
        public static final int WIFI_CONNECT_APP = 3;
        public static final int WIFI_DISCONNECT_APP = 4;
        public static final int WIFI_OPEN = 1;
    }

    public static final class ErrorCode {
        public static final int ERROR_CODE_ADJUST_SCREEN_BRIGHTNESS_FAILED = 19;
        public static final int ERROR_CODE_ANSWER_PHONE_FAILED = 10;
        public static final int ERROR_CODE_APP_START_FAILED = 7;
        public static final int ERROR_CODE_AUDIO_EXT_NOT_CONNECTED = 35;
        public static final int ERROR_CODE_AUDIO_MUTE_FAILED = 36;
        public static final int ERROR_CODE_AUDIO_NOT_MUTED_YET = 37;
        public static final int ERROR_CODE_AUDIO_SET_VOLUME_FAILED = 38;
        public static final int ERROR_CODE_BLUETOOTH_CLOSE_ERROR = 21;
        public static final int ERROR_CODE_BLUETOOTH_NOT_SUPPORTED = 20;
        public static final int ERROR_CODE_BT_CALL_CONTACT_NOT_FOUND = 14;
        public static final int ERROR_CODE_BT_CALL_FAILED = 12;
        public static final int ERROR_CODE_BT_CONTACT_ITEM_NULL = 13;
        public static final int ERROR_CODE_CAR_PROPERTY_CALLBACK_ERROR = 33;
        public static final int ERROR_CODE_CAR_PROPERTY_CALLBACK_TIMEOUT = 34;
        public static final int ERROR_CODE_CAR_SERVICE_CONNECT_FAILED = 32;
        public static final int ERROR_CODE_FUNCTION_NOT_IMPLEMENTED = 8;
        public static final int ERROR_CODE_HANGUP_PHONE_FAILED = 11;
        public static final int ERROR_CODE_HOTSPOT_CLOSE_ERROR = 17;
        public static final int ERROR_CODE_HOTSPOT_OPEN_ERROR = 16;
        public static final int ERROR_CODE_HOTSPOT_OPEN_TIMEOUT = 18;
        public static final int ERROR_CODE_NOT_INIT_SUCCESS = 5;
        public static final int ERROR_CODE_NO_APP_ERROR = 2;
        public static final int ERROR_CODE_NO_BT_NOT_CONNECTED = 1;
        public static final int ERROR_CODE_NO_ERROR = 0;
        public static final int ERROR_CODE_NO_PHONE_CALLS = 9;
        public static final int ERROR_CODE_NO_VEHICLE_ERROR = 3;
        public static final int ERROR_CODE_NO_VEHICLE_NO_PROPERTY = 4;
        public static final int ERROR_CODE_PARAM_ERROR = 6;
        public static final int ERROR_CODE_SYSTEM_SERVICE_NOT_FOUND = 15;
        public static final int ERROR_CODE_TBOX_DIAL_FAILED = 24;
        public static final int ERROR_CODE_TBOX_ENABLE_TETHERING_FAILED = 23;
        public static final int ERROR_CODE_TBOX_NOT_INITED = 22;
        public static final int ERROR_CODE_VIEW_CONTROL_MATCH_FAILED = 39;
        public static final int ERROR_CODE_WIFI_CONNECT_FAILED = 30;
        public static final int ERROR_CODE_WIFI_CONNECT_TIMEOUT = 31;
        public static final int ERROR_CODE_WIFI_DISCONNECT_FAILED = 28;
        public static final int ERROR_CODE_WIFI_ENABLE_FAILED = 29;
        public static final int ERROR_CODE_WIFI_NAME_NOT_SCANED = 26;
        public static final int ERROR_CODE_WIFI_NOT_CONFIGED = 27;
        public static final int ERROR_CODE_WIFI_NOT_ENABLED = 25;
    }

    public static final class LightType {
        public static final int SCREEN_LIGHT_DOWN = 4;
        public static final int SCREEN_LIGHT_TO_TARGET = 5;
        public static final int SCREEN_LIGHT_UP = 3;
        public static final int SCREEN_OFF = 1;
        public static final int SCREEN_ON = 2;
    }

    public static final class MusicType {
        public static final int MUSIC_PAUSE = 1;
        public static final int MUSIC_PLAY = 0;
        public static final int MUSIC_PLAY_ALBUM = 6;
        public static final int MUSIC_PLAY_NEXT = 2;
        public static final int MUSIC_PLAY_PREV = 3;
        public static final int MUSIC_PLAY_SINGER = 5;
        public static final int MUSIC_PLAY_STYLE = 7;
        public static final int MUSIC_STOP = 4;
    }

    public static final class NavigationType {
        public static final int NAVI_OPEN = 0;
    }

    public static final class VehicleType {
        public static final int VEHICLE_CARBIN = 2;
        public static final int VEHICLE_HAVC = 1;
        public static final int VEHICLE_OTHER = 5;
        public static final int VEHICLE_SETTINGS = 4;
        public static final int VEHICLE_VR = 3;
    }

    public static final class ViewControlType {
        public static final int VIEW_CONTROL_CLICK = 0;
        public static final int VIEW_CONTROL_GESTURE = 3;
        public static final int VIEW_CONTROL_GLOBAL = 2;
        public static final int VIEW_CONTROL_SLIDE = 1;
    }

    public static final class ViewControlTypeExt {
        public static final String CLICK_LONG = "CLICK_LONG";
        public static final String CLICK_NORMAL = "CLICK_NORMAL";
        public static final String GLOBAL_BACK = "GLOBAL_BACK";
        public static final String GLOBAL_HOME = "GLOBAL_HOME";
        public static final String GLOBAL_NOTIFICATIONS = "GLOBAL_NOTIFICATIONS";
        public static final String GLOBAL_RECENTS = "GLOBAL_RECENTS";
    }

    public static final class VoiceResult {
        public static final int VOICE_RESULT_FAILED = 2;
        public static final int VOICE_RESULT_NOT_PERFORMED = 0;
        public static final int VOICE_RESULT_SUCCESSED = 1;
    }

    public static final class VolumeType {
        public static final int VOLUME_DOWN = 2;
        public static final int VOLUME_TO_MAX = 3;
        public static final int VOLUME_TO_MIN = 4;
        public static final int VOLUME_TO_MUTE = 5;
        public static final int VOLUME_TO_UNMUTE = 6;
        public static final int VOLUME_UP = 1;
    }
}
