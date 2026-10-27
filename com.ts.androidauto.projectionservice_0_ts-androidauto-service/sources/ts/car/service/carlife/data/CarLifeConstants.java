package ts.car.service.carlife.data;

/* JADX INFO: loaded from: classes2.dex */
public class CarLifeConstants {

    public static final class LaunchMode {
        public static final int LAUNCH_MODE_INVALID = -1;
        public static final int LAUNCH_MODE_MAP = 1;
        public static final int LAUNCH_MODE_MUSIC = 2;
        public static final int LAUNCH_MODE_NORMAL = 0;
        public static final int LAUNCH_MODE_PHONE = 3;
    }

    public static final class ModuleId {
        public static final int MODULE_ID_INVALID = -1;
        public static final int MODULE_ID_NAVI = 1;
        public static final int MODULE_ID_VR = 0;
    }

    public static final class MusicStatus {
        public static final int MUSIC_STATUS_INVALID = -1;
        public static final int MUSIC_STATUS_PAUSED = 1;
        public static final int MUSIC_STATUS_PLAYING = 0;
    }

    public static final class SessionStatus {
        public static final int SESSION_STATUS_ACTIVATED = 2;
        public static final int SESSION_STATUS_ACTIVATING = 1;
        public static final int SESSION_STATUS_CONNECT_ERROR = 6;
        public static final int SESSION_STATUS_CONNECT_FAILED = 5;
        public static final int SESSION_STATUS_DEACTIVATED = 4;
        public static final int SESSION_STATUS_DEACTIVATING = 3;
        public static final int SESSION_STATUS_INIT = 0;
        public static final int SESSION_STATUS_INVALID = -1;
    }

    public static final class VideoFocusRequest {
        public static final int VIDEOFOCUS_REQUEST_INVALID = -1;
        public static final int VIDEOFOCUS_REQUEST_NATIVE = 1;
        public static final int VIDEOFOCUS_REQUEST_NATIVE_HOME = 2;
        public static final int VIDEOFOCUS_REQUEST_PROJECTED = 0;
        public static final int VIDEOFOCUS_REQUEST_VIDEO_DATA = 3;
        public static final int VIDEOFOCUS_REQUEST_VIDEO_DATA_REPORT = 4;
    }

    public class KeyCodeConst {
        public static final int KEYCODE_INVALID = -1;
        public static final int KEYCODE_PAUSE = 4;
        public static final int KEYCODE_PLAY = 3;
        public static final int KEYCODE_SEEK_DOWN = 1;
        public static final int KEYCODE_SEEK_UP = 2;
        public static final int KEYCODE_VR = 0;

        public KeyCodeConst() {
        }
    }
}
