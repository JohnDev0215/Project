package cerence.ark.assistant.framework.impl;

import android.text.TextUtils;
import cerence.ark.assistant.api.prompter.PrompterInstance;
import cerence.ark.assistant.api.prompter.VoiceType;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.dm.data.CloudParamType;
import cerence.ark.assistant.dm.data.DmConfiguration;
import cerence.ark.assistant.dm.data.DmInitParams;
import cerence.ark.assistant.dm.data.DmPrompterInstance;
import cerence.ark.assistant.dm.data.DmResultCode;
import cerence.ark.assistant.dm.data.FatalErrorCode;
import cerence.ark.assistant.dm.data.LocationType;
import cerence.ark.assistant.dm.data.NativeUpdateResultCode;
import cerence.ark.assistant.dm.data.PrompterVoice;
import cerence.ark.assistant.framework.data.AssistantContextType;
import cerence.ark.assistant.framework.data.AudioPlaybackMode;
import cerence.ark.assistant.framework.data.AudioRecordingMode;
import cerence.ark.assistant.framework.data.ErrorCode;
import cerence.ark.assistant.framework.data.ErrorInfo;
import cerence.ark.assistant.framework.data.InteractionMode;
import cerence.ark.assistant.framework.data.Language;
import cerence.ark.assistant.framework.data.RppLocationType;
import cerence.ark.assistant.framework.data.VoiceConfig;
import cerence.ark.assistant.framework.data.WakeupRespType;
import cerence.ark.assistant.framework.updater.UpdateResultCode;
import cerence.ark.assistant.library.Constant;
import cerence.ark.assistant.library.JsonKey;
import cerence.ark.assistant.library.Utils;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class FrameworkUtils {
    private static final String TAG = "FrameworkUtils";

    private FrameworkUtils() {
    }

    public static VoiceConfig getDefaultVoiceConfig(String str, PrompterVoice[] prompterVoiceArr) {
        if (prompterVoiceArr == null || prompterVoiceArr.length == 0) {
            Logger.error(TAG, "getDefaultVoiceConfig: getAvailableTtsVoices is empty");
            return null;
        }
        if (TextUtils.isEmpty(str)) {
            Logger.error(TAG, "getDefaultVoiceConfig: asrLanguage is null");
            return null;
        }
        EnumMap enumMap = new EnumMap(VoiceType.class);
        for (PrompterVoice prompterVoice : prompterVoiceArr) {
            if (prompterVoice != null && str.equals(prompterVoice.getLanguage()) && prompterVoice.getSupportedBySds()) {
                enumMap.putIfAbsent(VoiceType.fromString(prompterVoice.getType()), prompterVoice);
            }
        }
        PrompterVoice prompterVoice2 = (PrompterVoice) enumMap.get(VoiceType.LOCAL);
        VoiceConfig.Builder localVoiceId = prompterVoice2 != null ? VoiceConfig.builder().setLocalVoiceId(prompterVoice2.getVoiceId()) : null;
        PrompterVoice prompterVoice3 = (PrompterVoice) enumMap.get(VoiceType.HYBRID);
        if (prompterVoice3 != null) {
            if (localVoiceId == null) {
                localVoiceId = VoiceConfig.builder().setHybridVoiceId(prompterVoice3.getVoiceId());
            } else {
                localVoiceId.setHybridVoiceId(prompterVoice3.getVoiceId());
            }
        }
        if (localVoiceId != null) {
            VoiceConfig voiceConfigBuild = localVoiceId.build();
            Logger.info(TAG, "getDefaultVoiceConfig: found voice config: " + voiceConfigBuild);
            return voiceConfigBuild;
        }
        Logger.error(TAG, "getDefaultVoiceConfig: did not find for default voice");
        return null;
    }

    public static boolean checkTtsVoiceConfigValid(VoiceConfig voiceConfig, PrompterVoice[] prompterVoiceArr) {
        if (voiceConfig == null) {
            Logger.error(TAG, "checkTtsVoiceConfigValid: voiceConfig is empty");
            return false;
        }
        if (TextUtils.isEmpty(voiceConfig.getLocalVoiceId()) && TextUtils.isEmpty(voiceConfig.getHybridVoiceId())) {
            Logger.error(TAG, "checkTtsVoiceConfigValid: Both local and hybrid voiceIds are empty");
            return false;
        }
        if (prompterVoiceArr == null || prompterVoiceArr.length == 0) {
            Logger.error(TAG, "checkTtsVoiceConfigValid: getAvailableTtsVoices is empty");
            return false;
        }
        boolean z = false;
        boolean z2 = false;
        for (PrompterVoice prompterVoice : prompterVoiceArr) {
            if (prompterVoice != null) {
                String voiceId = prompterVoice.getVoiceId();
                if (!TextUtils.isEmpty(voiceId) && prompterVoice.getSupportedBySds()) {
                    if (!z && voiceId.equals(voiceConfig.getLocalVoiceId()) && VoiceType.LOCAL.getValue().equals(prompterVoice.getType())) {
                        z = true;
                    } else if (!z2 && voiceId.equals(voiceConfig.getHybridVoiceId()) && VoiceType.HYBRID.getValue().equals(prompterVoice.getType())) {
                        z2 = true;
                    }
                }
            } else {
                Logger.warn(TAG, "checkTtsVoiceConfigValid: tts voice is empty");
            }
        }
        if (TextUtils.isEmpty(voiceConfig.getLocalVoiceId()) || z) {
            return TextUtils.isEmpty(voiceConfig.getHybridVoiceId()) || z2;
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
        if (interactionMode == InteractionMode.WUW) {
            return DmConfiguration.InteractionMode.WUW;
        }
        if (interactionMode == InteractionMode.JT) {
            return DmConfiguration.InteractionMode.JT;
        }
        return DmConfiguration.InteractionMode.PTT;
    }

    public static ErrorInfo.ErrorCode getErrorCode(FatalErrorCode fatalErrorCode) {
        ErrorInfo.ErrorCode errorCode = ErrorInfo.ErrorCode.FATAL_UNKNOWN;
        if (fatalErrorCode == null) {
            Logger.error(TAG, "fatalErrorCode is null");
            return errorCode;
        }
        int i = AnonymousClass1.$SwitchMap$cerence$ark$assistant$dm$data$FatalErrorCode[fatalErrorCode.ordinal()];
        if (i == 1) {
            return ErrorInfo.ErrorCode.FATAL_RECOGNITION_START_FAILED;
        }
        if (i == 2) {
            return ErrorInfo.ErrorCode.FATAL_AUDIO_OUTPUT_TIMEOUT;
        }
        if (i != 3) {
            return i != 4 ? errorCode : ErrorInfo.ErrorCode.FATAL_RECOGNITION_TIMEOUT;
        }
        return ErrorInfo.ErrorCode.FATAL_AUDIO_INPUT_TIMEOUT;
    }

    public static CloudParamType getCloudParamType(AssistantContextType assistantContextType) {
        if (assistantContextType == AssistantContextType.TEXT_CONTEXT) {
            return CloudParamType.CLOUD_PARAM_TYPE_TEXT_CONTEXT;
        }
        if (assistantContextType == AssistantContextType.ASR_CONTEXT) {
            return CloudParamType.CLOUD_PARAM_TYPE_ASR_CONTEXT;
        }
        return CloudParamType.CLOUD_PARAM_TYPE_NLU_CONTEXT;
    }

    public static PromptEvent parsePromptEvent(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        String strOptString = jSONObject.optString("type");
        String strOptString2 = jSONObject.optString("event");
        if (!JsonKey.PROMPT_EVENT_TYPE_VALUE.equals(strOptString)) {
            return null;
        }
        PromptEvent promptEventFromString = PromptEvent.fromString(strOptString2);
        if (promptEventFromString == null) {
            Logger.error(TAG, Utils.format("parseEvent: [%s] not mapping PromptEvent enum, jsonString = ", strOptString2, str));
        }
        return promptEventFromString;
    }

    public static ErrorCode getErrorCode(DmResultCode dmResultCode) {
        ErrorCode errorCode = ErrorCode.UNKNOWN;
        if (dmResultCode == null) {
            Logger.error(TAG, "dmResultCode is null");
            return errorCode;
        }
        switch (AnonymousClass1.$SwitchMap$cerence$ark$assistant$dm$data$DmResultCode[dmResultCode.ordinal()]) {
            case 1:
                return ErrorCode.WRITABLE_PATH_INVALID;
            case 2:
                return ErrorCode.DATAPACK_PATH_INVALID;
            case 3:
                return ErrorCode.DATAPACK_VERSION_INCOMPATIBLE;
            case 4:
                return ErrorCode.DATAPACK_INVALID;
            case 5:
                return ErrorCode.PARAMETER_INVALID;
            case 6:
                return ErrorCode.ASSISTANT_ALREADY_INITIALIZED;
            case 7:
                return ErrorCode.ASSISTANT_NOT_INITIALIZED;
            case 8:
                return ErrorCode.ASSISTANT_NOT_STARTED;
            case 9:
                return ErrorCode.OOM;
            case 10:
                return ErrorCode.UPDATER_ALREADY_INITIALIZED;
            case 11:
                return ErrorCode.UPDATER_NOT_INITIALIZED;
            case 12:
                return ErrorCode.RECOVERY_MODE_ALREADY_INITIALIZED;
            case 13:
                return ErrorCode.RECOVERY_MODE_NOT_INITIALIZED;
            case 14:
                return ErrorCode.ASSISTANT_ALREADY_STARTED;
            default:
                return errorCode;
        }
    }

    public static UpdateResultCode getUpdateResultCode(NativeUpdateResultCode nativeUpdateResultCode) {
        UpdateResultCode updateResultCode = UpdateResultCode.UPDATE_ERROR;
        if (nativeUpdateResultCode == null) {
            Logger.error(TAG, "dmUpdateResultCode is null");
            return updateResultCode;
        }
        switch (AnonymousClass1.$SwitchMap$cerence$ark$assistant$dm$data$NativeUpdateResultCode[nativeUpdateResultCode.ordinal()]) {
            case 1:
                return UpdateResultCode.UPDATE_OK;
            case 2:
                return UpdateResultCode.UPDATE_STOPPED;
            case 3:
                return UpdateResultCode.UPDATE_CONNECTION_ERROR;
            case 4:
                return UpdateResultCode.UPDATE_REMOTE_ERROR;
            case 5:
                return UpdateResultCode.UPDATE_CHECKSUM_ERROR;
            case 6:
                return UpdateResultCode.UPDATE_FILESYSTEM_ERROR;
            case 7:
                return UpdateResultCode.UPDATE_INTERNAL_ERROR;
            case 8:
                return UpdateResultCode.RECOVERY_NOT_FOUND_ERROR;
            case 9:
                return UpdateResultCode.UPDATE_FATAL_ERROR;
            default:
                return updateResultCode;
        }
    }

    public static boolean isSuccess(DmResultCode dmResultCode) {
        return dmResultCode == DmResultCode.ACE_OK;
    }

    public static String convertDisabledHints(List<String> list) {
        if (list == null) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next());
        }
        return jSONArray.toString();
    }

    public static DmConfiguration.WakeupRespType convertWakeupRespType(WakeupRespType wakeupRespType) {
        if (WakeupRespType.TEXT == wakeupRespType) {
            return DmConfiguration.WakeupRespType.TEXT;
        }
        return DmConfiguration.WakeupRespType.EARCON;
    }

    public static Map<String, String> getAsrLanguageMap() {
        HashMap map = new HashMap(16);
        map.put("zh-CN", Language.CMN_CHN);
        map.put("sz-CN", Language.QSI_CHN);
        map.put("yue-CN", Language.YUE_CHN);
        map.put("sh-CN", Language.WUU_CHN);
        map.put("en-US", Language.ENG_USA);
        map.put("en-GB", Language.ENG_GBR);
        map.put("en-AU", Language.ENG_AUS);
        map.put("en-IN", Language.ENG_IND);
        map.put("en-SG", Language.ENG_SGP);
        map.put("fr-CA", Language.FRA_CAN);
        map.put("fr-FR", Language.FRA_FRA);
        map.put("bn-IN", Language.BEN_IND);
        map.put("hi-IN", Language.HIN_IND);
        map.put("ta-IN", Language.TAM_IND);
        map.put("te-IN", Language.TEL_IND);
        map.put("pt-BR", Language.POR_BRA);
        map.put("pt-PT", Language.POR_PRT);
        map.put("es-ES", Language.SPA_ESP);
        map.put("es-MX", Language.SPA_XLA);
        map.put("bg-BG", Language.BUL_BGR);
        map.put("hr-HR", Language.HRV_HRV);
        map.put("cs-CZ", Language.CES_CZE);
        map.put("nl-NL", Language.NLD_NLD);
        map.put("da-DK", Language.DAN_DNK);
        map.put("pes-IR", Language.FAS_IRN);
        map.put("fi-FI", Language.FIN_FIN);
        map.put("de-DE", Language.DEU_DEU);
        map.put("el-GR", Language.ELL_GRC);
        map.put("he-IL", Language.HEB_ISR);
        map.put("hu-HU", Language.HUN_HUN);
        map.put("id-ID", Language.IND_IDN);
        map.put("it-IT", Language.ITA_ITA);
        map.put("ja-JP", Language.JPN_JPN);
        map.put("ko-KR", Language.KOR_KOR);
        map.put("ms-MY", Language.ZLM_MYS);
        map.put("no-NO", Language.NOR_NOR);
        map.put("pl-PL", Language.POL_POL);
        map.put("ro-RO", Language.RON_ROU);
        map.put("ru-RU", Language.RUS_RUS);
        map.put("sk-SK", Language.SLK_SVK);
        map.put("sl-SI", Language.SLV_SVN);
        map.put("sv-SE", Language.SWE_SWE);
        map.put("th-TH", Language.THA_THA);
        return map;
    }

    public static PrompterInstance getTtsPrompterInstance(List<PrompterInstance> list) {
        if (list == null || list.isEmpty()) {
            Logger.error(TAG, "getTtsPrompterInstance: prompterInstances is empty");
            return null;
        }
        EnumMap enumMap = new EnumMap(VoiceType.class);
        for (PrompterInstance prompterInstance : list) {
            if (prompterInstance.getName().startsWith(Constant.TTS_INSTANCE_NAME_PREFIX)) {
                Logger.info(TAG, "getTtsPrompterInstance: found tts instance: " + prompterInstance);
                return prompterInstance;
            }
            enumMap.putIfAbsent(prompterInstance.getType(), prompterInstance);
        }
        PrompterInstance prompterInstance2 = (PrompterInstance) enumMap.get(VoiceType.LOCAL);
        if (prompterInstance2 != null) {
            Logger.info(TAG, "getTtsPrompterInstance: found local instance: " + prompterInstance2);
            return prompterInstance2;
        }
        PrompterInstance prompterInstance3 = (PrompterInstance) enumMap.get(VoiceType.HYBRID);
        if (prompterInstance3 != null) {
            Logger.info(TAG, "getTtsPrompterInstance: found hybrid instance: " + prompterInstance3);
            return prompterInstance3;
        }
        PrompterInstance prompterInstance4 = (PrompterInstance) enumMap.get(VoiceType.CLOUD);
        if (prompterInstance4 != null) {
            Logger.info(TAG, "getTtsPrompterInstance: found cloud instance: " + prompterInstance4);
            return prompterInstance4;
        }
        Logger.error(TAG, "getTtsPrompterInstance: did not find for android tts instance");
        return null;
    }

    public static boolean isPrompterInstanceNameValid(String str, List<PrompterInstance> list) {
        if (TextUtils.isEmpty(str)) {
            Logger.error(TAG, "checkPrompterInstanceValid: prompterInstanceName is empty");
            return false;
        }
        if (list == null || list.isEmpty()) {
            Logger.error(TAG, "checkPrompterInstanceValid: prompterInstances is empty");
            return false;
        }
        for (PrompterInstance prompterInstance : list) {
            if (prompterInstance == null) {
                Logger.warn(TAG, "checkPrompterInstanceValid: prompterInstance is null");
            } else if (str.equals(prompterInstance.getName())) {
                return true;
            }
        }
        return false;
    }

    public static LocationType convertLocationType(RppLocationType rppLocationType) {
        if (rppLocationType == null) {
            Logger.error(TAG, "convertLocationType: rppLocationType is null");
            return null;
        }
        int i = AnonymousClass1.$SwitchMap$cerence$ark$assistant$framework$data$RppLocationType[rppLocationType.ordinal()];
        if (i == 1) {
            return LocationType.LOCATION_IN_PROGRESS;
        }
        if (i == 2) {
            return LocationType.LOCATION_DESTINATION;
        }
        if (i == 3) {
            return LocationType.LOCATION_CURRENT;
        }
        Logger.error(TAG, Utils.format("convertLocationType: %s does not match the corresponding value.", rppLocationType));
        return null;
    }

    /* JADX INFO: renamed from: cerence.ark.assistant.framework.impl.FrameworkUtils$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$cerence$ark$assistant$dm$data$DmResultCode;
        static final /* synthetic */ int[] $SwitchMap$cerence$ark$assistant$dm$data$FatalErrorCode;
        static final /* synthetic */ int[] $SwitchMap$cerence$ark$assistant$dm$data$NativeUpdateResultCode;
        static final /* synthetic */ int[] $SwitchMap$cerence$ark$assistant$framework$data$RppLocationType;

        static {
            int[] iArr = new int[RppLocationType.values().length];
            $SwitchMap$cerence$ark$assistant$framework$data$RppLocationType = iArr;
            try {
                iArr[RppLocationType.LOCATION_IN_PROGRESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$framework$data$RppLocationType[RppLocationType.LOCATION_DESTINATION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$framework$data$RppLocationType[RppLocationType.LOCATION_CURRENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[NativeUpdateResultCode.values().length];
            $SwitchMap$cerence$ark$assistant$dm$data$NativeUpdateResultCode = iArr2;
            try {
                iArr2[NativeUpdateResultCode.UPDATE_OK.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$dm$data$NativeUpdateResultCode[NativeUpdateResultCode.UPDATE_STOPPED.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$dm$data$NativeUpdateResultCode[NativeUpdateResultCode.UPDATE_CONNECTION_ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$dm$data$NativeUpdateResultCode[NativeUpdateResultCode.UPDATE_REMOTE_ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$dm$data$NativeUpdateResultCode[NativeUpdateResultCode.UPDATE_CHECKSUM_ERROR.ordinal()] = 5;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$dm$data$NativeUpdateResultCode[NativeUpdateResultCode.UPDATE_FILESYSTEM_ERROR.ordinal()] = 6;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$dm$data$NativeUpdateResultCode[NativeUpdateResultCode.UPDATE_INTERNAL_ERROR.ordinal()] = 7;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$dm$data$NativeUpdateResultCode[NativeUpdateResultCode.UPDATE_RECOVERY_NOT_FOUND_ERROR.ordinal()] = 8;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$dm$data$NativeUpdateResultCode[NativeUpdateResultCode.UPDATER_FATAL_ERROR.ordinal()] = 9;
            } catch (NoSuchFieldError unused12) {
            }
            int[] iArr3 = new int[DmResultCode.values().length];
            $SwitchMap$cerence$ark$assistant$dm$data$DmResultCode = iArr3;
            try {
                iArr3[DmResultCode.ACE_WRITABLE_PATH_INVALID.ordinal()] = 1;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$dm$data$DmResultCode[DmResultCode.ACE_DATAPACK_PATH_INVALID.ordinal()] = 2;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$dm$data$DmResultCode[DmResultCode.ACE_DATAPACK_VERSION_INCOMPATIBLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$dm$data$DmResultCode[DmResultCode.ACE_DATAPACK_INVALID.ordinal()] = 4;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$dm$data$DmResultCode[DmResultCode.ACE_PARAMETER_INVALID.ordinal()] = 5;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$dm$data$DmResultCode[DmResultCode.ACE_ALREADY_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$dm$data$DmResultCode[DmResultCode.ACE_NOT_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$dm$data$DmResultCode[DmResultCode.ACE_NOT_STARTED.ordinal()] = 8;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$dm$data$DmResultCode[DmResultCode.ACE_OOM.ordinal()] = 9;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$dm$data$DmResultCode[DmResultCode.ACE_UPDATER_ALREADY_INITIALIZED.ordinal()] = 10;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$dm$data$DmResultCode[DmResultCode.ACE_UPDATER_NOT_INITIALIZED.ordinal()] = 11;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$dm$data$DmResultCode[DmResultCode.ACE_RECOVERY_MODE_ALREADY_INITIALIZED.ordinal()] = 12;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$dm$data$DmResultCode[DmResultCode.ACE_RECOVERY_MODE_NOT_INITIALIZED.ordinal()] = 13;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$dm$data$DmResultCode[DmResultCode.ACE_ALREADY_STARTED.ordinal()] = 14;
            } catch (NoSuchFieldError unused26) {
            }
            int[] iArr4 = new int[FatalErrorCode.values().length];
            $SwitchMap$cerence$ark$assistant$dm$data$FatalErrorCode = iArr4;
            try {
                iArr4[FatalErrorCode.FATAL_RECOGNITION_START_FAILED.ordinal()] = 1;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$dm$data$FatalErrorCode[FatalErrorCode.FATAL_AUDIO_OUTPUT_TIMEOUT.ordinal()] = 2;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$dm$data$FatalErrorCode[FatalErrorCode.FATAL_AUDIO_INPUT_TIMEOUT.ordinal()] = 3;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                $SwitchMap$cerence$ark$assistant$dm$data$FatalErrorCode[FatalErrorCode.FATAL_RECOGNITION_TIMEOUT.ordinal()] = 4;
            } catch (NoSuchFieldError unused30) {
            }
        }
    }

    static List<PrompterInstance> getPrompterInstances(DmPrompterInstance[] dmPrompterInstanceArr) {
        ArrayList arrayList = new ArrayList();
        if (dmPrompterInstanceArr == null) {
            Logger.error(TAG, "getPrompterInstances: dmPrompterInstances is null");
            return arrayList;
        }
        for (DmPrompterInstance dmPrompterInstance : dmPrompterInstanceArr) {
            arrayList.add(PrompterInstance.builder().setName(dmPrompterInstance.getName()).setType(VoiceType.fromString(dmPrompterInstance.getType())).build());
        }
        return arrayList;
    }
}
