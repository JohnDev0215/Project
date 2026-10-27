package ts.car.service.carplay.data;

/* JADX INFO: loaded from: classes3.dex */
public class CarPlayConstants {
    public static final String DEVICE_ASK_CARPLAYUI = "DeviceAsk";

    public static final class AppState {
        public static final int APP_STATE_PHONE_CALL = 0;
        public static final int APP_STATE_SPEECH = 2;
        public static final int APP_STATE_TURN_BY_TURN = 1;
    }

    public static final class AppearanceMode {
        public static final int APPEARANCE_MODE_DARK = 1;
        public static final int APPEARANCE_MODE_LIGHT = 0;
    }

    public static final class AppearanceSetting {
        public static final int APPEARANCE_SETTING_ALWAYS = 2;
        public static final int APPEARANCE_SETTING_AUTOMATIC = 0;
        public static final int APPEARANCE_SETTING_USER_CHOISE = 1;
    }

    public static final class AuxInState {
        public static final int STREAM_STATE_BUFFERING = 1;
        public static final int STREAM_STATE_OFF = 0;
        public static final int STREAM_STATE_STREAMING = 2;
    }

    public static final class BorrowId {
        public static final String BORROW_ID_AUDIO_OFF = "audioOff";
        public static final String BORROW_ID_BACKUP_CAMERA = "backupCamera";
        public static final String BORROW_ID_DISPLAY_OFF = "displayOff";
        public static final String BORROW_ID_NATIVE_VR = "nativeVR";
        public static final String BORROW_ID_PHONE_CALL = "phoneCall";
        public static final String BORROW_ID_STANDBY = "standby";
        public static final String BORROW_ID_UI_NOTIFICATION = "uiNotification";
    }

    public static final class CallAcceptAction {
        public static final int ACTION_ACCEPT_HOLD_AND_ACCEPT = 0;
        public static final int ACTION_END_AND_ACCEPT = 1;
        public static final int ACTION_UNKNOWN = -1;
    }

    public static final class CallEndAction {
        public static final int ACTION_END_ALL = 1;
        public static final int ACTION_END_OR_DECLINE = 0;
        public static final int ACTION_UNKNOWN = -1;
    }

    public static final class CarPlayHidKeyCode {
        public static final int CARPLAY_KEY_BACK = 37;
        public static final int CARPLAY_KEY_HOME = 31;
        public static final int CARPLAY_KEY_NEXTTRACK = 35;
        public static final int CARPLAY_KEY_PAUSE = 33;
        public static final int CARPLAY_KEY_PLAY = 32;
        public static final int CARPLAY_KEY_PLAYPAUSE = 34;
        public static final int CARPLAY_KEY_PREVTRACK = 36;
        public static final int CARPLAY_KEY_VR = 21;
        public static final int CARPLAY_TELKEY_0 = 4;
        public static final int CARPLAY_TELKEY_1 = 5;
        public static final int CARPLAY_TELKEY_2 = 6;
        public static final int CARPLAY_TELKEY_3 = 7;
        public static final int CARPLAY_TELKEY_4 = 8;
        public static final int CARPLAY_TELKEY_5 = 9;
        public static final int CARPLAY_TELKEY_6 = 10;
        public static final int CARPLAY_TELKEY_7 = 11;
        public static final int CARPLAY_TELKEY_8 = 12;
        public static final int CARPLAY_TELKEY_9 = 13;
        public static final int CARPLAY_TELKEY_DELETE = 17;
        public static final int CARPLAY_TELKEY_DROP = 3;
        public static final int CARPLAY_TELKEY_FLASH = 2;
        public static final int CARPLAY_TELKEY_HOOKSWITCH = 1;
        public static final int CARPLAY_TELKEY_MUTE = 16;
        public static final int CARPLAY_TELKEY_POUND = 15;
        public static final int CARPLAY_TELKEY_STAR = 14;
    }

    public static final class ConnectionType {
        public static final int CONNECTION_TYPE_USB = 1;
        public static final int CONNECTION_TYPE_WIRELESS = 2;
    }

    public static final class DeviceStatus {
        public static final int AVAILABLE = 1;
        public static final int CONNECTED = 4;
        public static final int CONNECTING = 3;
        public static final int DISCONNECTING = 2;
        public static final int INVALID = -1;
        public static final int UNAVAILABLE = 0;
    }

    public static final class Entity {
        public static final int CARPLAY_ENTITY_ACCESSORY = 2;
        public static final int CARPLAY_ENTITY_CONTROLLER = 1;
        public static final int CARPLAY_ENTITY_NONE = 0;
    }

    public static final class InitiateCallService {
        public static final int CALL_SERVICE_FACE_TIME_AUDIO = 2;
        public static final int CALL_SERVICE_FACE_TIME_VIDEO = 3;
        public static final int CALL_SERVICE_TELEPHONY = 1;
        public static final int CALL_SERVICE_UNKNOWN = -1;
    }

    public static final class InitiateCallType {
        public static final int CALL_TYPE_DESTINATION = 0;
        public static final int CALL_TYPE_REDIAL = 2;
        public static final int CALL_TYPE_UNKNOWN = -1;
        public static final int CALL_TYPE_VOICEMAIL = 1;
    }

    public static final class KeyAction {
        public static final int ACTION_DOWN = 0;
        public static final int ACTION_UP = 1;
    }

    public static final class LaunchMode {
        public static final int LAUNCH_MODE_MAP = 1;
        public static final int LAUNCH_MODE_MUSIC = 2;
        public static final int LAUNCH_MODE_NORMAL = 0;
        public static final int LAUNCH_MODE_NOW_PLAYING = 4;
        public static final int LAUNCH_MODE_PHONE = 3;
    }

    public static final class PriorityId {
        public static final int PRIORITY_LEVEL_CARPLAY_AUDIO = 2;
        public static final int PRIORITY_LEVEL_CARPLAY_NAVI = 5;
        public static final int PRIORITY_LEVEL_CARPLAY_PHONE = 4;
        public static final int PRIORITY_LEVEL_CARPLAY_SCREEN = 1;
        public static final int PRIORITY_LEVEL_CARPLAY_SIRI = 3;
        public static final int PRIORITY_LEVEL_HIGH = 6;
        public static final int PRIORITY_LEVEL_HIGHEST = 7;
        public static final int PRIORITY_LEVEL_NORMAL = 0;
    }

    public static final class ProjectionConnectType {
        public static final int PROJECTION_CONNECT_NONE = 0;
        public static final int PROJECTION_CONNECT_USB = 1;
        public static final int PROJECTION_CONNECT_WIRELESS = 2;
    }

    public static final class RemoteMediaPlaybackKeyCode {
        public static final int REMOTE_MEDIA_PLAYBACK_NEXT = 4;
        public static final int REMOTE_MEDIA_PLAYBACK_PAUSE = 2;
        public static final int REMOTE_MEDIA_PLAYBACK_PLAY = 1;
        public static final int REMOTE_MEDIA_PLAYBACK_PLAY_PAUSE = 64;
        public static final int REMOTE_MEDIA_PLAYBACK_PREV = 8;
        public static final int REMOTE_MEDIA_PLAYBACK_REPEAT = 32;
        public static final int REMOTE_MEDIA_PLAYBACK_SHUFFLE = 16;
    }

    public static final class ResourceType {
        public static final int RS_CP_AUDIO = 0;
        public static final int RS_CP_SCREEN = 1;
    }

    public static final class ScreenAction {
        public static final int ENTER_ACTION = 1;
        public static final int EXIT_ACTION = 0;
    }

    public static final class ScreenType {
        public static final int CLUSTER = 1;
        public static final int MAIN = 0;
    }

    public static final class SessionStatus {
        public static final int SESSION_STATUS_ACTIVATED = 2;
        public static final int SESSION_STATUS_ACTIVATING = 1;
        public static final int SESSION_STATUS_CONNECT_FAILED = 5;
        public static final int SESSION_STATUS_DEACTIVATED = 4;
        public static final int SESSION_STATUS_DEACTIVATING = 3;
        public static final int SESSION_STATUS_INIT = 0;
    }

    public static final class SiriAction {
        public static final int BUTTON_DOWN = 2;
        public static final int BUTTON_UP = 3;
        public static final int PREWARM = 1;
        public static final int VOICE_ACTIVATION = 4;
    }

    public static final class SiriTriggerZones {
        public static final int DRIVER = 1;
    }

    public static final class SpeechMode {
        public static final int SPEECHMODE_INVALID = 0;
        public static final int SPEECHMODE_NONE = -1;
        public static final int SPEECHMODE_RECOGNIZING_SPEECH = 2;
        public static final int SPEECHMODE_SPEAKING = 1;
    }

    public static final class VideoFocusRequest {
        public static final int VIDEOFOCUS_REQUEST_NATIVE = 1;
        public static final int VIDEOFOCUS_REQUEST_NATIVE_HOME = 2;
        public static final int VIDEOFOCUS_REQUEST_PROJECTED = 0;
    }

    public static final class VoiceDetectionMode {
        public static final int DEACTIVATED = 2;
        public static final int KEYWORD_DETECTION_MODE = 0;
        public static final int VOICE_ACTIVITY_DETECTION_MODE = 1;
    }

    public static final class WirelessAvailableState {
        public static final int WIRELESS_AVAILABLE_STATE_AVAILABLE = 1;
        public static final int WIRELESS_AVAILABLE_STATE_UNAVAILABLE = 2;
        public static final int WIRELESS_AVAILABLE_STATE_UNKNOWN = 0;
    }
}
