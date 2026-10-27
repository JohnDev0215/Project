package com.byd.dilink.media;

/* JADX INFO: loaded from: classes.dex */
public class DiLinkAudioConstants {
    public static String ACTION_AUDIO_BECOMING_NOISY = "android.media.AUDIO_BECOMING_NOISY";
    public static final int AUDIOFOCUS_FLAG_FSE = 32;
    public static final int AUDIOFOCUS_FLAG_IVI = 16;
    public static final int AUDIOFOCUS_GAIN = 1;
    public static final int AUDIOFOCUS_GAIN_TRANSIENT = 2;
    public static final int AUDIOFOCUS_LOSS = -1;
    public static final int AUDIOFOCUS_LOSS_TRANSIENT = -1;
    public static final int AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK = -1;
    public static final int AUDIOFOCUS_REQUEST_GRANTED = 1;
    public static final int CALL_CLIENT_BT = 1;
    public static final int CALL_CLIENT_XCALL = 2;
    public static final int DENOISE_REQUEST_DELAY = 2;
    public static final int DENOISE_REQUEST_FAILED = 0;
    public static final int DENOISE_REQUEST_SUCCESS = 1;
    public static String EXTRA_SYSTEM_MUTE_STATUS = "android.media.extra.SYSTEM_MUTE_STATUS";
    public static String EXTRA_SYSTEM_UNMUTE_TYPE = "android.media.extra.SYSTEM_UNMUTE_TYPE";
    public static final String EXTRA_VEHICLE_DEVICE_VOLUME_CHANGED = "android.media.EXTRA_VEHICLE_DEVICE_VOLUME_CHANGED";
    public static final int FLAG_SHOW_FSE_UI = 8192;
    public static final int FLAG_SHOW_IVI_UI = 4096;
    public static final int HU_DEVICE_LOCAL_SPEAKER = 1;
    public static final int HU_DEVICE_UNSPECIFIED = 2;
    public static final int HU_DEVICE_VEHICLE = 0;
    public static final int MODE_IN_COMMUNICATION = 3;
    public static final int MUTE_CLIENT_AUTO_VOICE = 3;
    public static final int MUTE_CLIENT_MAX = 5;
    public static final int MUTE_CLIENT_NONE = 4;
    public static final int MUTE_CLIENT_SYSTEM = 0;
    public static final int MUTE_CLIENT_TEMPORARY = 2;
    public static final int MUTE_CLIENT_VOICE_CALL = 1;
    public static final int STREAM_AUX = 13;
    public static final int STREAM_BTTS = 17;
    public static final int STREAM_BT_MUSIC = 14;
    public static final int STREAM_FM = 12;
    public static final int STREAM_MUSIC = 3;
    public static final int STREAM_MUTE = 16;
    public static final String STREAM_MUTE_CHANGED_ACTION = "android.media.STREAM_MUTE_CHANGED_ACTION";
    public static final int STREAM_NAVI = 15;
    public static String SYSTEM_MUTE_CHANGED_ACTION = "android.media.AudioManager.SYSTEM_MUTE_CHANGED_ACTION";
    public static final int SYSTEM_UNMUTE_TYPE_AUTO = 1;
    public static final int SYSTEM_UNMUTE_TYPE_MANUAL = 2;
    public static final int TYPE_FSE = 4;
    public static final int TYPE_IVI = 1;
    public static final int TYPE_LEFT = 2;
    public static final int TYPE_NONE = -1;
    public static final int TYPE_RIGHT = 3;
    public static final String VOLUME_CHANGED_ACTION = "android.media.VOLUME_CHANGED_ACTION";
    public static final int ZMQ_VOLUME_DIRECTION_DOWN = 0;
    public static final int ZMQ_VOLUME_DIRECTION_UP = 1;

    public static class AudioSlotStateOnAccOffStat {
        public static final int ERROR = -1;
        public static final int SUCCESS = 0;
    }

    public static class SlotType {
        public static final int SLOT_TYPE_MEDIA = 1;
        public static final int SLOT_TYPE_NAVI = 2;
    }
}
