package ts.car.service.androidauto.common;

/* JADX INFO: loaded from: classes.dex */
public class AndroidAutoConst {

    public static final class AppState {
        public static final int APP_STATE_TURN_BY_TURN = 0;
    }

    public static final class AppType {
        public static final int APP_TYPE_ROUTE_GUIDANCE = 0;
        public static final int APP_TYPE_VR = 1;
    }

    public static final class AvailableDeviceCapability {
        public static final int ANDROID_AUTO_WIRED = 1;
        public static final int ANDROID_AUTO_WIRELESS = 2;
    }

    public static final class BroadcastConst {
        public static final String ACTION_LINK_STATUS_CHANGED = "ts.androidauto.STATUS_CHANGED";
        public static final String EXTRA_ACTIVATED_STATUS = "isActivated";
    }

    public static final class ConnectionType {
        public static final int CONNECTION_TYPE_DEFAULT = 0;
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

    public static final class LaunchMode {
        public static final int LAUNCH_MODE_DEFAULT = 0;
        public static final int LAUNCH_MODE_HOME = 1;
        public static final int LAUNCH_MODE_MUSIC = 3;
        public static final int LAUNCH_MODE_NAVI = 4;

        @Deprecated
        public static final int LAUNCH_MODE_NORMAL = 0;
        public static final int LAUNCH_MODE_PHONE = 2;
    }

    public static final class LinkErrorCode {
        public static final int ERROR_AUTHENTICATION_FAILURE_CERT_NOT_YET_VALID = 4;
        public static final int ERROR_NOT_SUPPORTED = 10;
        public static final int ERROR_PING_TIMEOUT = 8;
        public static final int ERROR_REQUEST_AOA_FAILED = 13;
        public static final int ERROR_TCP_CONNECTION_FAILURE = 9;
        public static final int ERROR_UNRECOVERABLE_ERROR = 1;
        public static final int ERROR_USER_SELECT_DISCONNECT = 3;
        public static final int ERROR_VIDEO_DATA_TIMEOUT = 2;
        public static final int ERROR_WIFI_CONNECT_INSTRUCT_USER_TO_CHECK_THE_PHONE = 12;
        public static final int ERROR_WIFI_CONNECT_STATUS = 6;
        public static final int ERROR_WIFI_CONNECT_UNSOLICITED_MESSAGE = 11;
        public static final int ERROR_WIFI_DISCONNECT = 7;
        public static final int ERROR_WIFI_START_FAILED = 5;
        public static final int NO_ERROR_CODE = 0;
    }

    public static final class LinkStatus {
        public static final int LINK_STATUS_AAP_FRX = 6;

        @Deprecated
        public static final int LINK_STATUS_AAP_USERSWITCH = 8;
        public static final int LINK_STATUS_ACTIVATED = 4;
        public static final int LINK_STATUS_ACTIVATING = 3;

        @Deprecated
        public static final int LINK_STATUS_AVAILABLE = 7;

        @Deprecated
        public static final int LINK_STATUS_CONNECT_ERROR = 9;
        public static final int LINK_STATUS_CONNECT_FAILED = 5;
        public static final int LINK_STATUS_DEACTIVATED = 1;
        public static final int LINK_STATUS_DEACTIVATING = 2;

        @Deprecated
        public static final int LINK_STATUS_INIT = 0;
    }

    public static final class MediaPlaybackStatus {
        public static final int MEDIA_PLAYBACK_NOT_START = 0;
        public static final int MEDIA_PLAYBACK_PAUSED = 2;
        public static final int MEDIA_PLAYBACK_PLAYING = 1;
    }

    public static final class MediaRepeatMode {
        public static final int REPEAT_ALL = 1;
        public static final int REPEAT_OFF = 0;
        public static final int REPEAT_ONE = 2;
    }

    public static final class MediaShuffleMode {
        public static final int SHUFFLE_ALL = 1;
        public static final int SHUFFLE_OFF = 0;
    }

    @Deprecated
    public static final class MusicStatus {
        public static final int MUSIC_STATUS_NOT_START = 0;
        public static final int MUSIC_STATUS_PAUSED = 2;
        public static final int MUSIC_STATUS_PLAYING = 1;
    }

    public static final class NativePhoneCallStatus {
        public static final int NATIVE_PHONE_CALL_STATUS_ACTIVE = 0;
        public static final int NATIVE_PHONE_CALL_STATUS_IDLE = 1;
    }

    public static final class NavigationDistanceUnits {
        public static final int DISTANCE_UNIT_FEET = 6;
        public static final int DISTANCE_UNIT_KILOMETERS = 2;
        public static final int DISTANCE_UNIT_KILOMETERS_P1 = 3;
        public static final int DISTANCE_UNIT_METERS = 1;
        public static final int DISTANCE_UNIT_MILES = 4;
        public static final int DISTANCE_UNIT_MILES_P1 = 5;
        public static final int DISTANCE_UNIT_UNKNOWN = 0;
        public static final int DISTANCE_UNIT_YARDS = 7;
    }

    public static final class NavigationLaneShape {
        public static final int SHAPE_NORMAL_LEFT = 4;
        public static final int SHAPE_NORMAL_RIGHT = 5;
        public static final int SHAPE_SHARP_LEFT = 6;
        public static final int SHAPE_SHARP_RIGHT = 7;
        public static final int SHAPE_SLIGHT_LEFT = 2;
        public static final int SHAPE_SLIGHT_RIGHT = 3;
        public static final int SHAPE_STRAIGHT = 1;
        public static final int SHAPE_UNKNOWN = 0;
        public static final int SHAPE_U_TURN_LEFT = 8;
        public static final int SHAPE_U_TURN_RIGHT = 9;
    }

    public static final class NavigationManeuverType {
        public static final int MANEUVER_DEPART = 1;
        public static final int MANEUVER_DESTINATION = 39;
        public static final int MANEUVER_DESTINATION_LEFT = 41;
        public static final int MANEUVER_DESTINATION_RIGHT = 42;
        public static final int MANEUVER_DESTINATION_STRAIGHT = 40;
        public static final int MANEUVER_FERRY_BOAT = 37;
        public static final int MANEUVER_FERRY_BOAT_LEFT = 47;
        public static final int MANEUVER_FERRY_BOAT_RIGHT = 48;
        public static final int MANEUVER_FERRY_TRAIN = 38;
        public static final int MANEUVER_FERRY_TRAIN_LEFT = 49;
        public static final int MANEUVER_FERRY_TRAIN_RIGHT = 50;
        public static final int MANEUVER_FORK_LEFT = 25;
        public static final int MANEUVER_FORK_RIGHT = 26;
        public static final int MANEUVER_KEEP_LEFT = 3;
        public static final int MANEUVER_KEEP_RIGHT = 4;
        public static final int MANEUVER_MERGE_LEFT = 27;
        public static final int MANEUVER_MERGE_RIGHT = 28;
        public static final int MANEUVER_MERGE_SIDE_UNSPECIFIED = 29;
        public static final int MANEUVER_NAME_CHANGE = 2;
        public static final int MANEUVER_OFF_RAMP_NORMAL_LEFT = 23;
        public static final int MANEUVER_OFF_RAMP_NORMAL_RIGHT = 24;
        public static final int MANEUVER_OFF_RAMP_SLIGHT_LEFT = 21;
        public static final int MANEUVER_OFF_RAMP_SLIGHT_RIGHT = 22;
        public static final int MANEUVER_ON_RAMP_NORMAL_LEFT = 15;
        public static final int MANEUVER_ON_RAMP_NORMAL_RIGHT = 16;
        public static final int MANEUVER_ON_RAMP_SHARP_LEFT = 17;
        public static final int MANEUVER_ON_RAMP_SHARP_RIGHT = 18;
        public static final int MANEUVER_ON_RAMP_SLIGHT_LEFT = 13;
        public static final int MANEUVER_ON_RAMP_SLIGHT_RIGHT = 14;
        public static final int MANEUVER_ON_RAMP_U_TURN_LEFT = 19;
        public static final int MANEUVER_ON_RAMP_U_TURN_RIGHT = 20;
        public static final int MANEUVER_ROUNDABOUT_ENTER_AND_EXIT_CCW = 34;
        public static final int MANEUVER_ROUNDABOUT_ENTER_AND_EXIT_CCW_WITH_ANGLE = 35;
        public static final int MANEUVER_ROUNDABOUT_ENTER_AND_EXIT_CW = 32;
        public static final int MANEUVER_ROUNDABOUT_ENTER_AND_EXIT_CW_WITH_ANGLE = 33;
        public static final int MANEUVER_ROUNDABOUT_ENTER_CCW = 45;
        public static final int MANEUVER_ROUNDABOUT_ENTER_CW = 43;
        public static final int MANEUVER_ROUNDABOUT_EXIT_CCW = 46;
        public static final int MANEUVER_ROUNDABOUT_EXIT_CW = 44;
        public static final int MANEUVER_STRAIGHT = 36;
        public static final int MANEUVER_TURN_NORMAL_LEFT = 7;
        public static final int MANEUVER_TURN_NORMAL_RIGHT = 8;
        public static final int MANEUVER_TURN_SHARP_LEFT = 9;
        public static final int MANEUVER_TURN_SHARP_RIGHT = 10;
        public static final int MANEUVER_TURN_SLIGHT_LEFT = 5;
        public static final int MANEUVER_TURN_SLIGHT_RIGHT = 6;
        public static final int MANEUVER_UNKNOWN = 0;
        public static final int MANEUVER_U_TURN_LEFT = 11;
        public static final int MANEUVER_U_TURN_RIGHT = 12;
    }

    public static final class NavigationStatus {
        public static final int NAVI_ACTIVE = 1;
        public static final int NAVI_INACTIVE = 2;
        public static final int NAVI_REROUTING = 3;
        public static final int NAVI_UNAVAILABLE = 0;
    }

    public static final class ProjectionConnectType {
        public static final int PROJECTION_CONNECT_NONE = 0;
        public static final int PROJECTION_CONNECT_USB = 1;
        public static final int PROJECTION_CONNECT_WIRELESS = 2;
    }

    public static final class VideoFocusRequest {
        public static final int VIDEOFOCUS_REQUEST_AA_1080P = 3;
        public static final int VIDEOFOCUS_REQUEST_AA_480P = 1;
        public static final int VIDEOFOCUS_REQUEST_AA_720P = 2;
        public static final int VIDEOFOCUS_REQUEST_NATIVE = 0;

        @Deprecated
        public static final int VIDEOFOCUS_REQUEST_PROJECTED = 4;
    }

    public static final class VideoFocusStatus {
        public static final int VIDEO_FOCUS_NATIVE = 0;
        public static final int VIDEO_FOCUS_PROJECTION = 1;
    }
}
