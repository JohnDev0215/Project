package com.ts.androidauto.aap.common;

/* JADX INFO: loaded from: classes.dex */
public final class AapConfig {
    public static final int AOA_CHANAGE_FAILED = 2;
    public static final int AOA_CHANAGE_SUCESS = 1;
    public static final int AUDIO_BITS_MEDIA = 16;
    public static final int AUDIO_BITS_TTS = 16;
    public static final int AUDIO_CHANNELS_MEDIA = 2;
    public static final int AUDIO_CHANNELS_TTS = 1;
    public static final int AUDIO_CHANNEL_GUIDANCE = 101;
    public static final int AUDIO_CHANNEL_MEDIA = 100;
    public static final int AUDIO_CHANNEL_MONO = 4;
    public static final int AUDIO_CHANNEL_STEREO = 12;
    public static final int AUDIO_FORMAT_PCM_16BIT = 2;
    public static final int AUDIO_REQ_BEGIN = 1;
    public static final int AUDIO_REQ_FAILED = 3;
    public static final int AUDIO_REQ_INIT = 0;
    public static final int AUDIO_REQ_SUCCESS = 2;
    public static final int AUDIO_TYPE_ASR = 202;
    public static final int AUDIO_TYPE_GUIDANCE = 201;
    public static final int AUDIO_TYPE_MEDIA = 200;
    public static final int CAR_CALL_STATE_CALLING = 1;
    public static final int CAR_CALL_STATE_IDEL = 0;
    public static final int CHECK_COUNT = 30;
    public static final int CHECK_RFCOMM_COUNT = 30;
    public static final int DISPLAY_ID_CLUSTER = 1;
    public static final int DISPLAY_ID_MAIN = 0;
    public static final int FOCUS_RELEASE_DELAY_TIME = 600;
    public static final int MERGE_DEVICE_TYPE_BT = 2;
    public static final int MERGE_DEVICE_TYPE_USB = 1;
    public static final int MERGE_DEVICE_TYPE_WIFI = 3;
    public static final int MESSAGE_RELEASE_NOTIFY_TIMEOUT = 1;
    public static final int MUTE_STATE_OFF = 0;
    public static final int MUTE_STATE_ON = 1;
    public static final int NAVI_FOCUS_RELEASE_DELAY = 1;
    public static final int POWER_OFF_DELAY_TIME = 1000;
    public static final int POWER_OFF_RELEASE_DELAY = 2;
    public static final int SAMPLING_RATE_MEDIA = 48000;
    public static final int SAMPLING_RATE_TTS = 48000;
    public static final int SEARCHING_SWITCH_DEVICES_COUNT = 120;
    public static final int SEND_VEHICLE_COUNT = 10;
    public static final int SLEEP_10 = 10;
    public static final int SLEEP_100 = 100;
    public static final int SLEEP_1000 = 1000;
    public static final int SLEEP_50 = 50;
    public static final int SLEEP_500 = 500;
    public static final int STREAM_TYPE_MUSIC = 3;
    public static final int STREAM_TYPE_NOTIFICATION = 5;
    public static final int TIME_RELEASE_NOTIFY_TIMEOUT = 5000;
    public static final int UPDATE_SWITCH_DEVICE_COUNT = 30;
    public static final int VIDEO_SIZE_480P = 480;
    public static final int VIDEO_SIZE_720P = 720;
    public static final int VR_FOCUS_RELEASE_DELAY = 0;
    public static final int VR_PLAY_DEFAULT = 0;
    public static final int VR_PLAY_START = 1;
    public static final int VR_PLAY_STOP = 2;
    public static final int WAIT_TIME_BYE_RESPONSE_TIMEOUT = 200;
    public static final int WIFI_PORT_FIRST = 9998;
    public static final int WIFI_PORT_SECOND = 9999;
    public static final int WIFI_START_CHECK_COUNT = 500;

    public enum ValidAudioFocusType {
        VALID_AUDIO_FOCUS_MEDIA,
        VALID_AUDIO_FOCUS_NAVI,
        VALID_AUDIO_FOCUS_VR,
        VALID_AUDIO_FOCUS_INVALID
    }
}
