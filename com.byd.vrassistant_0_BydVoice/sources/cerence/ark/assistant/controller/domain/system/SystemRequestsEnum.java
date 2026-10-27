package cerence.ark.assistant.controller.domain.system;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
@Retention(RetentionPolicy.SOURCE)
public @interface SystemRequestsEnum {
    public static final String CANCEL = "cancel";
    public static final String CHECK_SHORTCUT_AVAILABLE = "checkShortcutAvailable";
    public static final String GET_ENABLED_AUDIO_ZONES = "getEnabledAudioZones";
    public static final String GET_EXTERNAL_VOICE_ASSISTANT_STATUS = "getExternalVoiceAssistantStatus";
    public static final String GET_NETWORK_STATUS = "getNetworkStatus";
    public static final String HIDE_SCREEN = "hideScreen";
    public static final String QUERY_HUFORBID_STATUS = "queryHUForbidStatus";
    public static final String RELEASE_AUDIO_FOCUS = "releaseAudioFocus";
    public static final String REQUEST_AUDIO_FOCUS = "requestAudioFocus";
    public static final String SHOW_SCREEN = "showScreen";
}
