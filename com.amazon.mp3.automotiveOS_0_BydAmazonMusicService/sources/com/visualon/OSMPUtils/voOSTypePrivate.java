package com.visualon.OSMPUtils;

/* JADX INFO: loaded from: classes7.dex */
public class voOSTypePrivate {
    private static final String TAG = "@@@voOSTypePrivate";
    public static final int VOOSMP_CB_ANALYTICS_LISTENER = 83886084;
    public static final int VOOSMP_CB_DRM_PRIVATE_START = 83886080;
    public static final int VOOSMP_CB_OUTPUT_CONTROL_DOWN_RESOLUTION = 83886082;
    public static final int VOOSMP_CB_VIDEO_DEST_BOX = 83886090;
    public static final int VOOSMP_PID_ADAPTIVESTREAMING_INTERNAL_SEEK_POS = 65550;
    public static final int VOOSMP_PID_ANTI_MIRROR = 65543;
    public static final int VOOSMP_PID_AUDIO_FOCUS_GAIN = 69651;
    public static final int VOOSMP_PID_AUDIO_FOCUS_LOSS = 69652;
    public static final int VOOSMP_PID_ENABLE_CARD_BOARD_VIDEO = 65574;
    public static final int VOOSMP_PID_ENABLE_MC = 69638;
    public static final int VOOSMP_PID_ENABLE_SPHERICAL_VIDEO = 65560;
    public static final int VOOSMP_PID_GET_ANALYTICS_EXPORT_TYPE = 65608;
    public static final int VOOSMP_PID_GET_ANALYTICS_URL_SESSIONID = 65539;
    public static final int VOOSMP_PID_GET_AUDIO_DATA = 69634;
    public static final int VOOSMP_PID_GET_DATASOURCE_INFO = 65546;
    public static final int VOOSMP_PID_GET_DECODER_TYPE = 65569;
    public static final int VOOSMP_PID_GET_ENGINE_INFO = 65541;
    public static final int VOOSMP_PID_GET_ENGINE_POSITION_NATIVE = 69641;
    public static final int VOOSMP_PID_GET_VIDEO_DATA = 69633;
    public static final int VOOSMP_PID_GLRENDER_WRAPPER = 69640;
    public static final int VOOSMP_PID_HDCP_POLICY = 65542;
    public static final int VOOSMP_PID_HWDECODER_MAX_RESOLUTION = 65552;
    public static final int VOOSMP_PID_I_FRAME_MODE = 65556;
    public static final int VOOSMP_PID_PLAYBACK_SPEED = 65555;
    public static final int VOOSMP_PID_PRIVATE_START = 65536;
    public static final int VOOSMP_PID_REFER_SYSTEM_CLOCK = 65538;
    public static final int VOOSMP_PID_REGISTER_VOISERVICE = 65568;
    public static final int VOOSMP_PID_SET_ANA_RENDER_OBJ = 69637;
    public static final int VOOSMP_PID_SET_JAVAVM = 69635;
    public static final int VOOSMP_PID_SET_SUBTITLE_RENDER_OBJ = 69636;
    public static final int VOOSMP_PID_SET_TIMELISTENER_OBJ = 69650;
    public static final int VOOSMP_PID_SET_VIEW_OBJ = 69649;
    public static final int VOOSMP_PID_SPHERICAL_VIDEO_VIEW = 65561;
    public static final int VOOSMP_PID_UPDATE_VIDEO_DEST_BOX_FINISH = 65571;
    public static final int VOOSMP_PID_VIDEO_VSYNC_HELPER = 69648;
    public static final int VOOSMP_RENDER_TYPE_BITMAP = 2;
    public static final int VOOSMP_RENDER_TYPE_HW_RENDER = 5;
    public static final int VOOSMP_RENDER_TYPE_JAVA = 0;
    public static final int VOOSMP_RENDER_TYPE_JMHW_RENDER = 6;
    public static final int VOOSMP_SRC_ADAPTIVE_STREAMING_INFO_EVENT_INTERNAL_SEEK_POS = 5001;
    public static final int VOOSMP_SRC_PID_ADD_CONFIGURATION = 50397208;
    public static final int VOOSMP_SRC_PID_ADSCTRL_CALLBACK = 50397189;
    public static final int VOOSMP_SRC_PID_ADSINFO_CALLBACK = 50397187;
    public static final int VOOSMP_SRC_PID_DOWNLOAD_EXTERNAL_SUBTITLE_URL = 50397195;
    public static final int VOOSMP_SRC_PID_DRMLICMGR_CALLBACK = 50397188;
    public static final int VOOSMP_SRC_PID_DRM_HASHMAP = 50397190;
    public static final int VOOSMP_SRC_PID_PRIVATE_OUTPUT_CONTROL_DOWN_RESOLUTION = 50397185;
    public static final int VOOSMP_SRC_PID_PRIVATE_START = 50397184;
    public static final int VOOSMP_SRC_PID_SET_DOWNLOAD_DIRECTORY = 50397193;
    public static final int VOOSMP_SRC_PID_SET_REPLACE_URL = 50397207;
    public static final int VOOSMP_SRC_PID_URL_REGEX_PATTERN = 50397191;
    public static final int VOOSMP_SRC_PID_URL_SUBS_PATTERN = 50397192;

    public static class VOMP_REFER_WALL_CLOCK {
        public boolean bEnable = false;
        public boolean bLowLatency = false;
        public int nVideoAdjustTime = 0;
    }

    public enum VOOSMP_I_FRAME_MODE {
        VOOSMP_I_FRAME_MODE_DISABLED(0),
        VOOSMP_I_FRAME_MODE_FORWARD(1),
        VOOSMP_I_FRAME_MODE_BACKWARD(2),
        VOOSMP_I_FRAME_MODE_MAX(Integer.MAX_VALUE);

        private int value;

        VOOSMP_I_FRAME_MODE(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        public static VOOSMP_I_FRAME_MODE valueOf(int i) {
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].getValue() == i) {
                    return values()[i2];
                }
            }
            if (voLog.enablePrintLog()) {
                voLog.e(voOSTypePrivate.TAG, "VOOSMP_I_FRAME_MODE does not match. id = " + Integer.toHexString(i), new Object[0]);
            }
            return VOOSMP_I_FRAME_MODE_MAX;
        }
    }

    public enum VOOSMP_HDCP_POLICY {
        VOOSMP_HDCP_NO_CHECK(1),
        VOOSMP_HDCP_NON_COMPLIANT_DOWNRES(2),
        VOOSMP_HDCP_NON_COMPLIANT_BLOCK(3),
        VOOSMP_HDCP_MAX(Integer.MAX_VALUE);

        private int value;

        VOOSMP_HDCP_POLICY(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        public static VOOSMP_HDCP_POLICY valueOf(int i) {
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].getValue() == i) {
                    return values()[i2];
                }
            }
            return VOOSMP_HDCP_MAX;
        }
    }
}
