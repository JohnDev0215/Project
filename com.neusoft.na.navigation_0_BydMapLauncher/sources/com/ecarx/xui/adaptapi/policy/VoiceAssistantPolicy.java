package com.ecarx.xui.adaptapi.policy;

import android.content.Context;
import android.os.SystemProperties;
import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class VoiceAssistantPolicy implements IVoiceAssistantPolicy {
    public static final String CVHBHH = "CVHBHH";
    public static final String CVHBQJ = "CVHBQJ";
    public static final String CVHBSS = "CVHBSS";
    public static final String CVHH = "CVHH";
    public static final String CVQJ = "CVQJ";
    public static final String FE5HAHH = "FE5HAHH";
    public static final String FE5HAQJ = "FE5HAQJ";
    public static final String FE5HASS = "FE5HASS";
    public static final String FE6HH = "FE6HH";
    public static final String FE6QJ = "FE6QJ";
    public static final String FE6SS = "FE6SS";
    public static final int FUNC_MODE_NOISECLEAN = 1;
    public static final int FUNC_MODE_PASSBY = 3;
    public static final int FUNC_MODE_PHONE = 0;
    public static final int FUNC_MODE_WAKEUP = 4;
    public static final String KC2HBHH = "KC2HBHH";
    public static final String KC2HBQJ = "KC2HBQJ";
    public static final String KC2HBSS = "KC2HBSS";
    public static final String KC2HBZG = "KC2HBZG";
    public static final String KC2HH = "KC2HH";
    public static final String KC2QJ = "KC2QJ";
    public static final String KC2SS = "KC2SS";
    public static final String KC2ZG = "KC2ZG";
    public static final String NL3BHH = "NL3BHH";
    public static final String NL3BQJ = "NL3BQJ";
    public static final String NL5HH = "NL5HH";
    public static final String NL5QJ = "NL5QJ";
    public static final String SX11FLAG = "SX11FLAG";
    public static final String SX11QJ = "SX11QJ";
    private static final String TAG = "VoiceAssistantPolicy";
    private static final int TTS_VOLUME = 8;
    private Context mContext;

    public static final class WakeID {
        public static final int WAKE_ID_BIN_RUI = 9;
        public static final int WAKE_ID_BIN_YUE = 10;
        public static final int WAKE_ID_BO_RUI = 7;
        public static final int WAKE_ID_BO_YUE = 1;
        public static final int WAKE_ID_DI_HAO = 2;
        public static final int WAKE_ID_ELISE = 5;
        public static final int WAKE_ID_JIA_JI = 11;
        public static final int WAKE_ID_JI_LI = 12;
        public static final int WAKE_ID_LYNKCO = 4;
        public static final int WAKE_ID_SHUAI_GE = 6;
        public static final int WAKE_ID_XIAO_KA = 8;

        @Deprecated
        public static final int WAKE_ID_XING_YUE = 10;
        public static final int WAKE_ID_YUAN_JING = 3;
    }

    public static final class WorkMode {
        public static final int WORK_MODE_BYPASS = 0;
        public static final int WORK_MODE_DEFAULT = 3;
        public static final int WORK_MODE_ECHO_CANCEL = 2;
        public static final int WORK_MODE_NOISE_CLEAN = 1;
        public static final int WORK_MODE_VOICE_WAKEUP = 3;
    }

    @Override // com.ecarx.xui.adaptapi.policy.IVoiceAssistantPolicy
    public int getTTSPCMVolume() {
        return 8;
    }

    @Override // com.ecarx.xui.adaptapi.policy.IVoiceAssistantPolicy
    public int setWorkMode(int i) {
        return -2147483647;
    }

    public VoiceAssistantPolicy(Context context) {
        this.mContext = context;
    }

    @Override // com.ecarx.xui.adaptapi.policy.IVoiceAssistantPolicy
    public int getWakeID() {
        return getProject();
    }

    private int getProject() {
        String str = SystemProperties.get("ro.product.device");
        int i = str.contains("BX11") ? 4 : 12;
        Log.d(TAG, "getProject mProject = " + str + ", result = " + i);
        return i;
    }
}
