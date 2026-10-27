package ts.car.service.ipod.data;

/* JADX INFO: loaded from: classes.dex */
public class IpodConst {

    public static final class DbCategotyType {
        public static final int DB_TYPE_ALBUM = 3;
        public static final int DB_TYPE_ARTIST = 2;
        public static final int DB_TYPE_AUDIOBOOK = 7;
        public static final int DB_TYPE_COMPOSER = 6;
        public static final int DB_TYPE_GENRE = 4;
        public static final int DB_TYPE_MUSIC_RADIO = 9;
        public static final int DB_TYPE_PLAYLIST = 1;
        public static final int DB_TYPE_PODCAST = 8;
        public static final int DB_TYPE_TOP_LEVEL = 0;
        public static final int DB_TYPE_TRACK = 5;
    }

    public static final class Hidkey {
        public static final int IAP_HID_PLAYBACK_NEXT = 4;
        public static final int IAP_HID_PLAYBACK_PAUSE = 2;
        public static final int IAP_HID_PLAYBACK_PLAY = 1;
        public static final int IAP_HID_PLAYBACK_PLAY_PAUSE = 64;
        public static final int IAP_HID_PLAYBACK_PREV = 8;
        public static final int IAP_HID_PLAYBACK_REPEAT = 32;
        public static final int IAP_HID_PLAYBACK_SHUFFLE = 16;
    }

    public static final class IpodConnectStatus {
        public static final int CONNECTED = 0;
        public static final int CONNECTED_DB_EMPTY = 2;
        public static final int CONNECT_FAIL = 3;
        public static final int DISCONNECT = 4;
        public static final int UNINITIALISED = 1;
    }

    public static final class PlayStatus {
        public static final int PB_ST_EX_FF = 3;
        public static final int PB_ST_EX_PAUSE = 2;
        public static final int PB_ST_EX_PLAY = 1;
        public static final int PB_ST_EX_REW = 4;
        public static final int PB_ST_EX_STOP = 0;
        public static final int PB_ST_EX_UNKNOWN = -1;
    }

    public static final class RepeatMode {
        public static final int REPEAT_ALLTRACKS = 2;
        public static final int REPEAT_OFF = 0;
        public static final int REPEAT_ONETRACK = 1;
        public static final int UNKNOW_REPEAT_MODE = -1;
    }

    public static final class ShuffleMode {
        public static final int SHUFFLE_ALBUM = 2;
        public static final int SHUFFLE_OFF = 0;
        public static final int SHUFFLE_TRACK = 1;
        public static final int UNKNOW_SHUFFLE_MODE = -1;
    }

    public enum IpodReturnTypeEnum {
        IPOD_RET_OK(0),
        IPOD_RET_FAILED(-1),
        IPOD_NO_DEVICE(-2),
        IPOD_INIT_ERROR(-3),
        IPOD_NO_DATA(-4),
        IPOD_ARGUMENT_INVALID(-5);

        private int mVal;

        IpodReturnTypeEnum(int val) {
            this.mVal = val;
        }

        public int getVal() {
            return this.mVal;
        }
    }
}
