package com.ts.carplay.common;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayConstants {
    public static final String APPLICATION_CLASS_NAME = "com.ts.carplay.app.ui.display.view.CarPlayDisplayActivity";
    public static final String APPLICATION_PACKAGE_NAME = "com.ts.carplay.app";
    public static final String DEVICE_ASK_CARPLAYUI = "DeviceAsk";
    public static final String PLATFORM_CARPLAY_APP_PERMISSION = "car.permission.CARPLAY_APP";
    public static final String PLATFORM_CARPLAY_SERVICE_PERMISSION = "car.permission.CARPLAY";
    public static final String PLATFORM_DEVICE_LIST_PERMISSION = "car.permission.CARPLAY_DEVICE_LIST";
    public static final String REMOTE_SERVICE_NAME = "carplay_service";
    public static final String SERVICE_COMPONENT = "com.ts.carplay/.CarPlayService";

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

    public static final class AudioFocusReqResult {
        public static final int AUDIOFOCUS_REQUEST_DELAYED = 2;
        public static final int AUDIOFOCUS_REQUEST_FAILED = 0;
        public static final int AUDIOFOCUS_REQUEST_GRANTED = 1;
    }

    public static final class AuxInState {
        public static final int STREAM_STATE_BUFFERING = 1;
        public static final int STREAM_STATE_OFF = 0;
        public static final int STREAM_STATE_STREAMING = 2;
    }

    public static final class BluetoothProfile {
        public static final int PROFILE_A2DP = 2;
        public static final int PROFILE_AVRCP = 3;
        public static final int PROFILE_HFP = 0;
        public static final int PROFILE_PBAP = 1;
        public static final int PROFILE_UNSPECIFIED = -1;
    }

    public static final class BluetoothProfileState {
        public static final int STATE_CONNECTED = 2;
        public static final int STATE_CONNECTING = 1;
        public static final int STATE_DISCONNECTED = 0;
        public static final int STATE_DISCONNECTING = 3;
    }

    public static final class BluetoothState {
        public static final int STATE_OFF = 10;
        public static final int STATE_ON = 12;
        public static final int STATE_TURNING_OFF = 13;
        public static final int STATE_TURNING_ON = 11;
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

    public static final class CallbackListType {
        public static final int CALL_STATE_CALLBACK_LIST = 0;
        public static final int CONTACT_LIST_CALLBACK_LIST = 3;
        public static final int NOW_PLAYING_CALLBACK_LIST = 2;
        public static final int ROUTE_GUIDANCE_CALLBACK_LIST = 1;
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

    public static final class ConnectType {
        public static final int CONNECTION_TYPE_USB = 1;
        public static final int CONNECTION_TYPE_WIRELESS = 2;
    }

    public static final class ConnectedTypeState {
        public static final int DETECTING = 2;
        public static final int DISCONNECTED = 4;
        public static final int INVALID = 0;
        public static final int READY = 3;
        public static final int UNKNOWN = 1;
    }

    public static final class ConnectionType {
        public static final int BT = 2;
        public static final int ETHERNET = 4;
        public static final int NONE = 0;
        public static final int USB = 1;
        public static final int WIFI = 3;
    }

    public static final class ContactListType {
        public static final int FAVORITE_LIST = 1;
        public static final int RECENT_LIST = 0;
    }

    public static final class ControlEffect {
        public static final int CONTROL_EFFECT_SCREEN_OFF = 3;
        public static final int CONTROL_EFFECT_SCREEN_ON_FOREVER = 1;
        public static final int CONTROL_EFFECT_SCREEN_ON_TEMPORARILY = 2;
        public static final int CONTROL_EFFECT_UNKNOWN = 0;
    }

    public static final class ControllingResult {
        public static final int CONTROLLING_RESULT_NORMAL_EXECUTION = 0;
        public static final int CONTROLLING_RESULT_NO_EXECUTION_BECAUSE_HARD_KEY = 1;
    }

    public static final class DeviceMirrorType {
        public static final int DEVICE_MIRROR_TYPE_NULL = 0;
        public static final int DEVICE_MIRROR_TYPE_WIRE = 1;
        public static final int DEVICE_MIRROR_TYPE_WIRELESS = 2;
    }

    public static final class DeviceState {
        public static final int DISABLE = 3;
        public static final int OFFLINE = 0;
        public static final int ONLINE = 1;
        public static final int REMOVING = 2;
        public static final int VISIBLE = 4;
    }

    public static final class DeviceStatus {
        public static final int AVAILABLE = 1;
        public static final int CONNECTED = 4;
        public static final int CONNECTING = 3;
        public static final int DISCONNECTING = 2;
        public static final int INVALID = -1;
        public static final int UNAVAILABLE = 0;
    }

    public static final class DeviceType {
        public static final int ANDROID_PHONE = 1;
        public static final int IPHONE = 2;
        public static final int IPOD = 3;
        public static final int UNKNOWN = 0;
    }

    public static final class Entity {
        public static final int CARPLAY_ENTITY_ACCESSORY = 2;
        public static final int CARPLAY_ENTITY_CONTROLLER = 1;
        public static final int CARPLAY_ENTITY_NONE = 0;
    }

    public static final class Iap2NotifyReason {
        public static final int CANCEL_WIRELESS_CARPLAY = 3;
        public static final int CHECK_WIRELESS_CAPLAY_WHEN_BT_CONNECTED = 5;
        public static final int CHECK_WIRELESS_CAPLAY_WHEN_BT_PAIRED = 1;
        public static final int CONNECTING = 2;
        public static final int PRECHECK_WIRELESS_CARPLAY = 4;
    }

    public static final class IapSessionState {
        public static final int CONNECTED = 1;
        public static final int DISCONNECTED = 2;
    }

    public static final class IapSessionStateReason {
        public static final int CONNECTION_TIME_OUT = 3;
        public static final int DISCONNECTION_TIME_OUT = 4;
        public static final int LINK_ERROR = 2;
        public static final int LINK_LOST = 1;
        public static final int NO_ERROR = 0;
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

    public static final class ManagerName {
        public static final String CARPLAYMANAGER_NAME = "CarPlayManager";
    }

    public static final class MergeKeyType {
        public static final int BT_MACADDRESS = 2;
        public static final int DEVICE_IDENTIFICATION = 4;
        public static final int USB_SERIALNUMBER = 1;
        public static final int WIFI_MACADDRESS = 3;
    }

    public enum NativePhoneStatus {
        STATUS_IN_DEFAULT,
        STATUS_IN_CALL,
        STATUS_IN_RINGING
    }

    public static final class PriorityId {
        public static final int PRIORITY_LEVEL_CARPLAY_AUDIO = 2;
        public static final int PRIORITY_LEVEL_CARPLAY_AUXOUT = 8;
        public static final int PRIORITY_LEVEL_CARPLAY_NAVI = 5;
        public static final int PRIORITY_LEVEL_CARPLAY_PHONE = 4;
        public static final int PRIORITY_LEVEL_CARPLAY_RINGTONE = 9;
        public static final int PRIORITY_LEVEL_CARPLAY_SCREEN = 1;
        public static final int PRIORITY_LEVEL_CARPLAY_SIRI = 3;
        public static final int PRIORITY_LEVEL_HIGH = 6;
        public static final int PRIORITY_LEVEL_HIGHEST = 7;
        public static final int PRIORITY_LEVEL_NORMAL = 0;
    }

    public static final class ProjectionCapabilityUpdatedReason {
        public static final int IAP_AUTHENTICATION_FAILED = 2;
        public static final int NOERR = 0;
        public static final int RFCOMM_FAILD = 1;
    }

    public static final class ProjectionConnectType {
        public static final int PROJECTION_CONNECT_NONE = 0;
        public static final int PROJECTION_CONNECT_USB = 1;
        public static final int PROJECTION_CONNECT_WIRELESS = 2;
    }

    public static final class ProjectionType {
        public static final int PROJECTION_TYPE_OTHER = 2;
        public static final int PROJECTION_TYPE_WIRELESS_CARPLAY = 1;
        public static final int PROJECTION_TYPE_WIRE_CARPLAY = 0;
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

    public static final class RoleSwitchPurpose {
        public static final int CARLIFE = 2;
        public static final int CARPLAY = 1;
        public static final int IAP = 3;
        public static final int NO_PURPOSE = 0;
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

    public static final class VehicleGearMode {
        public static final int GEAR_DRIVE = 1;
        public static final int GEAR_NEUTRAL = 0;
        public static final int GEAR_PARK = 2;
        public static final int GEAR_REVERSE = 3;
    }

    public static final class VideoFocusReqResult {
        public static final int VIDEOFOCUS_REQUEST_FAILED = 0;
        public static final int VIDEOFOCUS_REQUEST_GRANTED = 1;
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

    public static final class WifiApState {
        public static final int WIFI_STATE_AP_DISABLED = 11;
        public static final int WIFI_STATE_AP_ENABLED = 13;
    }

    public static final class WifiSecurityType {
        public static final int WIFI_SECURITY_NONE = 0;
        public static final int WIFI_SECURITY_UNKNOWN = -1;
        public static final int WIFI_SECURITY_WEP = 1;
        public static final int WIFI_SECURITY_WPA = 2;
        public static final int WIFI_SECURITY_WPA_3_PERSONNAL_ONLY = 4;
        public static final int WIFI_SECURITY_WPA_3_PERSON_TRANSITION_MODE = 3;
    }

    public static final class WirelessAvailableState {
        public static final int WIRELESS_AVAILABLE_STATE_AVAILABLE = 1;
        public static final int WIRELESS_AVAILABLE_STATE_UNAVAILABLE = 2;
        public static final int WIRELESS_AVAILABLE_STATE_UNKNOWN = 0;
    }

    public static final class AudioFocus {
        public static final int AUDIO_FOCUS_CARPLAY_ALTERNATE_AUDIO_WITHOUT_DUCK = 1;
        public static final int AUDIO_FOCUS_CARPLAY_ALTERNATE_AUDIO_WITH_DUCK = 4;
        public static final int AUDIO_FOCUS_CARPLAY_AUXOUT = 6;
        public static final int AUDIO_FOCUS_CARPLAY_AUXOUT_DUCK = 8;
        public static final int AUDIO_FOCUS_CARPLAY_MEDIA = 0;
        public static final int AUDIO_FOCUS_CARPLAY_RINGTONE = 5;
        public static final int AUDIO_FOCUS_CARPLAY_TEL = 3;
        public static final int AUDIO_FOCUS_CARPLAY_TMP_MEDIA = 7;
        public static final int AUDIO_FOCUS_CARPLAY_VR = 2;

        public static String toString(int type) {
            if (type == 0) {
                return "AUDIO_FOCUS_CARPLAY_MEDIA";
            }
            if (type == 1) {
                return "AUDIO_FOCUS_CARPLAY_ALTERNATE_AUDIO_WITHOUT_DUCK";
            }
            if (type == 2) {
                return "AUDIO_FOCUS_CARPLAY_VR";
            }
            if (type == 3) {
                return "AUDIO_FOCUS_CARPLAY_TEL";
            }
            if (type == 4) {
                return "AUDIO_FOCUS_CARPLAY_ALTERNATE_AUDIO_WITH_DUCK";
            }
            if (type == 5) {
                return "AUDIO_FOCUS_CARPLAY_RINGTONE";
            }
            if (type == 8) {
                return "AUDIO_FOCUS_CARPLAY_AUXOUT_DUCK";
            }
            if (type == 7) {
                return "AUDIO_FOCUS_CARPLAY_TMP_MEDIA";
            }
            if (type == 6) {
                return "AUDIO_FOCUS_CARPLAY_AUXOUT";
            }
            return "AUDIO_FOCUS_CARPLAY_UNKNOWN";
        }
    }
}
