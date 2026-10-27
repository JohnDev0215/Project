package android.hardware.bydauto.multimedia;

import android.content.Context;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class BYDAutoMultimediaDevice {
    public static final int ACTION_AUTO_SEARCH = 6;
    public static final int ACTION_CANCEL_RADIO_SEARCH = 9;
    public static final int ACTION_ENTER = 0;
    public static final int ACTION_NONE = -1;
    public static final int ACTION_PAUSE = 2;
    public static final int ACTION_PLAY = 1;
    public static final int ACTION_PLAY_NEXT = 4;
    public static final int ACTION_PLAY_NEXT_FREQ = 8;
    public static final int ACTION_PLAY_PRE = 3;
    public static final int ACTION_PLAY_PRE_FREQ = 7;
    public static final int ACTION_SET_PLAY_PATTERN = 5;
    public static final String ACTION_START_MEDIA = "byd.intent.action.START_MEDIA";
    public static final String MEDIA_ACTION = "MediaAction";
    public static final String MEDIA_MODE = "MediaMode";
    public static final String MEDIA_PARAM = "MediaParam";
    public static final int MODE_MUSIC = 1;
    public static final int MODE_NONE = -1;
    public static final int MODE_PHOTO = 3;
    public static final int MODE_RADIO = 0;
    public static final int MODE_VIDEO = 2;
    public static final int MULTIMEDIA_COMMAND_BUSY = -2147482647;
    public static final int MULTIMEDIA_COMMAND_FAILED = -2147482648;
    public static final int MULTIMEDIA_COMMAND_INVALID_VALUE = -2147482645;
    public static final int MULTIMEDIA_COMMAND_SUCCESS = 0;
    public static final int MULTIMEDIA_COMMAND_TIMEOUT = -2147482646;
    public static final int MULTIMEDIA_PLAY_MODE_ALL_REPEAT = 5;
    public static final int MULTIMEDIA_PLAY_MODE_INVAID = 6;
    public static final int MULTIMEDIA_PLAY_MODE_PREVIEW = 2;
    public static final int MULTIMEDIA_PLAY_MODE_RANDOM = 1;
    public static final int MULTIMEDIA_PLAY_MODE_SCAN = 3;
    public static final int MULTIMEDIA_PLAY_MODE_SINGLE_REPEAT = 0;
    public static final int MULTIMEDIA_PLAY_MODE_STEREO = 4;
    public static final int MULTIMEDIA_STATE_PAUSE = 1;
    public static final int MULTIMEDIA_STATE_PLAY = 0;
    public static final int MULTIMEDIA_STATE_STOP = 2;
    public static final int MULTIMEDIA_TYPE_AM = 0;
    public static final int MULTIMEDIA_TYPE_AUDIO_OFF = 6;
    public static final int MULTIMEDIA_TYPE_AUX = 7;
    public static final int MULTIMEDIA_TYPE_BT = 16;
    public static final int MULTIMEDIA_TYPE_CD = 2;
    public static final int MULTIMEDIA_TYPE_DVD = 4;
    public static final int MULTIMEDIA_TYPE_FM = 1;
    public static final int MULTIMEDIA_TYPE_HD_AUDIO = 14;
    public static final int MULTIMEDIA_TYPE_HD_VIDEO = 15;
    public static final int MULTIMEDIA_TYPE_INVAID = 18;
    public static final int MULTIMEDIA_TYPE_LOCAL_AUDIO = 8;
    public static final int MULTIMEDIA_TYPE_LOCAL_VIDEO = 9;
    public static final int MULTIMEDIA_TYPE_ROBOT = 17;
    public static final int MULTIMEDIA_TYPE_SD_AUDIO = 12;
    public static final int MULTIMEDIA_TYPE_SD_VIDEO = 13;
    public static final int MULTIMEDIA_TYPE_TV = 5;
    public static final int MULTIMEDIA_TYPE_USB_AUDIO = 10;
    public static final int MULTIMEDIA_TYPE_USB_VIDEO = 11;
    public static final int MULTIMEDIA_TYPE_VCD = 3;
    public static final String PARAM_ARTIST_NAME = "artistname";
    public static final String PARAM_FILE_NAME = "fliename";
    public static final String PARAM_PATTERN = "pattern";
    public static final String PARAM_RADIO_FREQ = "radiofreq";
    public static final String PARAM_RADIO_SEARCH = "searchtype";
    public static final String PARAM_SOURCE = "source";
    public static final String PARAM_WITH_UI = "with_ui";
    public static final int PATTERN_CYCLE = 0;
    public static final int PATTERN_FULL_SCREEN = 1;
    public static final int PATTERN_HALF_SCREEN = 0;
    public static final int PATTERN_RANDOM = 1;
    public static final int PATTERN_SINGLE = 2;
    public static final int RADIO_SEARCH_AUTO = 0;
    public static final int RADIO_SEARCH_NEXT = 2;
    public static final int RADIO_SEARCH_PRE = 1;
    public static final int SOURCE_BTMUSIC = 2;
    public static final int SOURCE_LOCAL = 0;
    public static final int SOURCE_NONE = -1;
    public static final int SOURCE_SD = 3;
    public static final int SOURCE_USB = 1;
    protected static final String TAG = "BYDAutoMultimediaDevice";

    public class MediaControlParam {
        public MediaControlParam() {
            throw new RuntimeException("Stub!");
        }

        public int getIntParam(String str) {
            throw new RuntimeException("Stub!");
        }

        public HashMap<String, String> getParam() {
            throw new RuntimeException("Stub!");
        }

        public String getStringParam(String str) {
            throw new RuntimeException("Stub!");
        }

        public void setParam(String str, int i7) {
            throw new RuntimeException("Stub!");
        }

        public void setParam(String str, String str2) {
            throw new RuntimeException("Stub!");
        }

        public MediaControlParam(HashMap<String, String> map) {
            throw new RuntimeException("Stub!");
        }
    }

    BYDAutoMultimediaDevice(Context context) {
        throw new RuntimeException("Stub!");
    }

    public static BYDAutoMultimediaDevice getInstance(Context context) {
        throw new RuntimeException("Stub!");
    }

    public int controlMedia(int i7, int i8, MediaControlParam mediaControlParam) {
        throw new RuntimeException("Stub!");
    }

    public int getMediaMode() {
        throw new RuntimeException("Stub!");
    }

    public int getMediaType() {
        throw new RuntimeException("Stub!");
    }

    public MediaInfo getPlayMediaInfo() {
        throw new RuntimeException("Stub!");
    }

    public int getPlayMode() {
        throw new RuntimeException("Stub!");
    }

    public int getPlayProgress() {
        throw new RuntimeException("Stub!");
    }

    public int getPlayState() {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoMultimediaListener absBYDAutoMultimediaListener) {
        throw new RuntimeException("Stub!");
    }

    public int setMediaType(int i7) {
        throw new RuntimeException("Stub!");
    }

    public int setPlayMediaInfo(int i7, MediaInfo mediaInfo) {
        throw new RuntimeException("Stub!");
    }

    public int setPlayMode(int i7, int i8) {
        throw new RuntimeException("Stub!");
    }

    public int setPlayProgress(int i7, int i8) {
        throw new RuntimeException("Stub!");
    }

    public int setPlayState(int i7, int i8) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(AbsBYDAutoMultimediaListener absBYDAutoMultimediaListener) {
        throw new RuntimeException("Stub!");
    }
}
