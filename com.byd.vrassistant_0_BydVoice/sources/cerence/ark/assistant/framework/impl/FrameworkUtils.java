package cerence.ark.assistant.framework.impl;

import android.text.TextUtils;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.dm.data.DmConfiguration;
import cerence.ark.assistant.dm.data.DmInitParams;
import cerence.ark.assistant.dm.data.FatalErrorCode;
import cerence.ark.assistant.dm.data.PrompterVoice;
import cerence.ark.assistant.framework.data.AudioPlaybackMode;
import cerence.ark.assistant.framework.data.AudioRecordingMode;
import cerence.ark.assistant.framework.data.ErrorInfo;
import cerence.ark.assistant.framework.data.InteractionMode;
import cerence.ark.assistant.framework.data.wuw.Wuw;
import cerence.ark.assistant.framework.data.wuw.WuwInfo;
import cerence.ark.assistant.library.JsonKey;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class FrameworkUtils {
    private static final String TAG = "FrameworkUtils";

    private FrameworkUtils() {
    }

    public static String getDefaultVoiceId(String str, PrompterVoice[] prompterVoiceArr) {
        if (prompterVoiceArr == null || prompterVoiceArr.length == 0) {
            Logger.error(TAG, "getDefaultVoidId: getAvailableTtsVoices is empty");
            return null;
        }
        if (TextUtils.isEmpty(str)) {
            Logger.error(TAG, "getDefaultVoidId: asrLanguage is null");
            return null;
        }
        for (PrompterVoice prompterVoice : prompterVoiceArr) {
            if (prompterVoice != null && str.equals(prompterVoice.getLanguage())) {
                return prompterVoice.getVoiceId();
            }
        }
        return null;
    }

    public static boolean checkTtsVoiceIdValid(String str, PrompterVoice[] prompterVoiceArr) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (prompterVoiceArr == null || prompterVoiceArr.length == 0) {
            Logger.error(TAG, "checkTtsVoiceIdValid: getAvailableTtsVoices is empty");
            return false;
        }
        for (PrompterVoice prompterVoice : prompterVoiceArr) {
            if (prompterVoice == null) {
                Logger.warn(TAG, "checkTtsVoiceIdValid: tts voice is empty");
            } else if (str.equals(prompterVoice.getVoiceId())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPlaybackModePal(AudioPlaybackMode audioPlaybackMode) {
        return audioPlaybackMode == AudioPlaybackMode.PAL;
    }

    public static DmInitParams.AudioRecordingMode getDmAudioRecordingMode(AudioRecordingMode audioRecordingMode) {
        if (audioRecordingMode == AudioRecordingMode.PAL) {
            return DmInitParams.AudioRecordingMode.SDK;
        }
        return DmInitParams.AudioRecordingMode.APP;
    }

    public static DmInitParams.AudioPlaybackMode getDmAudioPlaybackMode(AudioPlaybackMode audioPlaybackMode) {
        return audioPlaybackMode == AudioPlaybackMode.PAL ? DmInitParams.AudioPlaybackMode.SDK : DmInitParams.AudioPlaybackMode.APP;
    }

    public static DmConfiguration.InteractionMode getDmInteractionMode(InteractionMode interactionMode) {
        if (AnonymousClass1.$SwitchMap$cerence$ark$assistant$framework$data$InteractionMode[interactionMode.ordinal()] == 1) {
            return DmConfiguration.InteractionMode.WUW;
        }
        return DmConfiguration.InteractionMode.PTT;
    }

    public static String[] getCustomWakeupWords(List<String> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                String str = list.get(i);
                if (TextUtils.isEmpty(str)) {
                    Logger.warn(TAG, "the wake up word is empty");
                } else {
                    arrayList.add(String.format("%s#%s", Integer.valueOf(i + 1), str));
                }
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    /* JADX INFO: renamed from: cerence.ark.assistant.framework.impl.FrameworkUtils$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$cerence$ark$assistant$dm$data$FatalErrorCode;
        static final /* synthetic */ int[] $SwitchMap$cerence$ark$assistant$framework$data$InteractionMode;

        static {
            int[] iArr = new int[FatalErrorCode.values().length];
            $SwitchMap$cerence$ark$assistant$dm$data$FatalErrorCode = iArr;
            try {
                iArr[FatalErrorCode.FATAL_RECOGNITION_START_FAILED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$dm$data$FatalErrorCode[FatalErrorCode.FATAL_AUDIO_OUTPUT_TIMEOUT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$dm$data$FatalErrorCode[FatalErrorCode.FATAL_AUDIO_INPUT_TIMEOUT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$dm$data$FatalErrorCode[FatalErrorCode.FATAL_RECOGNITION_TIMEOUT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[InteractionMode.values().length];
            $SwitchMap$cerence$ark$assistant$framework$data$InteractionMode = iArr2;
            try {
                iArr2[InteractionMode.WUW.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public static ErrorInfo.ErrorCode getErrorCode(FatalErrorCode fatalErrorCode) {
        int i = AnonymousClass1.$SwitchMap$cerence$ark$assistant$dm$data$FatalErrorCode[fatalErrorCode.ordinal()];
        if (i == 1) {
            return ErrorInfo.ErrorCode.FATAL_RECOGNITION_START_FAILED;
        }
        if (i == 2) {
            return ErrorInfo.ErrorCode.FATAL_AUDIO_OUTPUT_TIMEOUT;
        }
        if (i == 3) {
            return ErrorInfo.ErrorCode.FATAL_AUDIO_INPUT_TIMEOUT;
        }
        if (i == 4) {
            return ErrorInfo.ErrorCode.FATAL_RECOGNITION_TIMEOUT;
        }
        return ErrorInfo.ErrorCode.FATAL_UNKNOWN;
    }

    public static WuwInfo getWuwInfo(String str) {
        try {
            WuwInfo.Builder builderNewBuilder = WuwInfo.newBuilder();
            JSONObject jSONObject = new JSONObject(str);
            builderNewBuilder.setPredefinedWuwList(getWuwInfoList(jSONObject.optJSONArray(JsonKey.WUW_TYPE_SDS)));
            builderNewBuilder.setCustomizedWuwList(getWuwInfoList(jSONObject.optJSONArray(JsonKey.WUW_TYP_CUSTOMIZED)));
            builderNewBuilder.setShortcutList(getWuwInfoList(jSONObject.optJSONArray(JsonKey.WUW_TYP_SHORTCUT)));
            return builderNewBuilder.build();
        } catch (Exception e) {
            Logger.error(TAG, "getWuwInfo: parse wuw json exception", e);
            return null;
        }
    }

    private static List<Wuw> getWuwInfoList(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
                arrayList.add(Wuw.newBuilder().setPhrase(jSONObjectOptJSONObject.optString(JsonKey.WUW_ORTHOGRAPHY)).setActivated(jSONObjectOptJSONObject.optBoolean("activated")).build());
            }
        }
        return arrayList;
    }
}
