package com.iflytek.speech;

import com.iflytek.speech.mvw.IMVWListener;

/* JADX INFO: loaded from: classes.dex */
public class libissmvw {
    public static final String ISS_MUL_IVW_INPUT_NUM = "mul_ivw_input_num";
    public static final int ISS_MVW_LANG_ARABIC = 6;
    public static final int ISS_MVW_LANG_BUTT = 20;
    public static final int ISS_MVW_LANG_CANTONESE = 2;
    public static final int ISS_MVW_LANG_CHN = 0;
    public static final int ISS_MVW_LANG_DONGBEI = 18;
    public static final int ISS_MVW_LANG_ENG = 1;
    public static final int ISS_MVW_LANG_ENGLISHSIRI = 100;
    public static final int ISS_MVW_LANG_FRENCH = 15;
    public static final int ISS_MVW_LANG_GERMAN = 12;
    public static final int ISS_MVW_LANG_INDONESIAN = 10;
    public static final int ISS_MVW_LANG_ITALIAN = 16;
    public static final int ISS_MVW_LANG_JAPANESE = 3;
    public static final int ISS_MVW_LANG_MALAY = 9;
    public static final int ISS_MVW_LANG_MANDARINCANTONESE = 13;
    public static final int ISS_MVW_LANG_MANDARINSICHUAN = 14;
    public static final int ISS_MVW_LANG_MINNAN = 19;
    public static final int ISS_MVW_LANG_PORTUGUESE = 17;
    public static final int ISS_MVW_LANG_RUSSIAN = 4;
    public static final int ISS_MVW_LANG_SHANGHAI = 11;
    public static final int ISS_MVW_LANG_SICHUAN = 7;
    public static final int ISS_MVW_LANG_SPANISH = 5;
    public static final int ISS_MVW_LANG_THAI = 8;
    public static final int ISS_MVW_MSG_Error = 40002;
    public static final int ISS_MVW_MSG_InitStatus = 40000;
    public static final int ISS_MVW_MSG_Result = 40003;
    public static final int ISS_MVW_MSG_Timeout = 40004;
    public static final int ISS_MVW_MSG_VolumeLevel = 40001;
    public static final String ISS_MVW_PARAM_AEC = "mvw_enable_aec";
    public static final String ISS_MVW_PARAM_AUDIO_LOCATE = "0";
    public static final String ISS_MVW_PARAM_AUDIO_MODE = "mvw_aduio_mode";
    public static final String ISS_MVW_PARAM_AUDIO_OMNI = "1";
    public static final int ISS_MVW_PARAM_LOG_FILE_NAME = 1288;
    public static final int ISS_MVW_PARAM_LOG_LEVEL = 1286;
    public static final int ISS_MVW_PARAM_LOG_OUTPUT = 1287;
    public static final String ISS_MVW_PARAM_LSA = "mvw_enable_lsa";
    public static final String ISS_MVW_PARAM_SAMPLEAVG = "mvw_enable_sampleavg";
    public static final String ISS_MVW_PARAM_SIRI_MODE = "mvw_siri_mode";
    public static final String ISS_MVW_PARAM_THRESHOLD_LEVEL = "mvw_threshold_level";
    public static final String ISS_MVW_PARAM_TMP_LOG_DIR = "TmpLogDir";
    public static final String ISS_MVW_PARAM_VALUE_OFF = "off";
    public static final String ISS_MVW_PARAM_VALUE_ON = "on";
    public static final int ISS_MVW_SCENE_ANSWER_CALL = 8;
    public static final int ISS_MVW_SCENE_BUILD_GRM = 16;
    public static final int ISS_MVW_SCENE_CONFIRM = 2;
    public static final int ISS_MVW_SCENE_GLOBAL = 1;
    public static final int ISS_MVW_SCENE_ONESHOT = 32;
    public static final int ISS_MVW_SCENE_SELECT = 4;
    public static final String ISS_MVW_VOLUME_LOG_LEVEL_ALL = "-1";
    public static final String ISS_MVW_VOLUME_LOG_LEVEL_CRIT = "1";
    public static final String ISS_MVW_VOLUME_LOG_LEVEL_ERROR = "2";
    public static final String ISS_MVW_VOLUME_LOG_LEVEL_NONE = "0";
    public static final String ISS_MVW_VOLUME_LOG_LEVEL_WARNING = "4";
    public static final String ISS_MVW_VOLUME_LOG_OUTPUT_CONSOLE = "2";
    public static final String ISS_MVW_VOLUME_LOG_OUTPUT_DEBUGGER = "4";
    public static final String ISS_MVW_VOLUME_LOG_OUTPUT_FILE = "1";
    public static final String ISS_MVW_VOLUME_LOG_OUTPUT_MSGBOX = "8";
    public static final String ISS_MVW_VOLUME_LOG_OUTPUT_NONE = "0";
    private static libissmvw instance = null;
    private static final String tag = "libissmvw";

    public static native synchronized int GetCacheAudioData(NativeHandle nativeHandle, int i, long j, int i2, byte[] bArr, int[] iArr);

    public static native synchronized int GetVprAudioData(NativeHandle nativeHandle, byte[] bArr, int[] iArr);

    public static native synchronized int ResRollBack(NativeHandle nativeHandle);

    public static native synchronized int ResUpdate(NativeHandle nativeHandle);

    public static native synchronized String ResVersionQuery(NativeHandle nativeHandle);

    public static native synchronized int addstartscene(NativeHandle nativeHandle, int i);

    public static native synchronized int appendAudioData(NativeHandle nativeHandle, byte[] bArr, int i);

    public static native synchronized int appendAudioDataOld(NativeHandle nativeHandle, byte[] bArr, int i);

    public static native synchronized int create(NativeHandle nativeHandle, String str, IMVWListener iMVWListener);

    public static native synchronized int createEx(NativeHandle nativeHandle, String str, IMVWListener iMVWListener);

    public static native synchronized int destroy(NativeHandle nativeHandle);

    public static native synchronized boolean isCouldAppendAudioData();

    public static native synchronized int setLogCfgParam(int i, String str);

    public static native synchronized int setMachineCode(String str);

    public static native synchronized int setMvwDefaultKeyWords(NativeHandle nativeHandle, int i);

    public static native synchronized int setMvwKeyWords(NativeHandle nativeHandle, int i, String str);

    public static native synchronized int setMvwLanguage(int i);

    public static native synchronized int setParam(NativeHandle nativeHandle, String str, String str2);

    public static native synchronized int setThreshold(NativeHandle nativeHandle, int i, int i2, int i3);

    public static native synchronized int start(NativeHandle nativeHandle, int i);

    public static native synchronized int stop(NativeHandle nativeHandle);

    public static native synchronized int stopscene(NativeHandle nativeHandle, int i);

    public static native synchronized int vprSetThreshold(NativeHandle nativeHandle, int i, double d);

    public static native synchronized int vprStart(NativeHandle nativeHandle, int i, int i2, int i3);

    public static native synchronized int vprStop(NativeHandle nativeHandle, int i, int i2, int i3);

    static {
        System.loadLibrary("websockets");
        System.loadLibrary("w_ivw");
        System.loadLibrary("issmvw");
        System.loadLibrary("issauth");
        System.loadLibrary("SpWord");
    }
}
