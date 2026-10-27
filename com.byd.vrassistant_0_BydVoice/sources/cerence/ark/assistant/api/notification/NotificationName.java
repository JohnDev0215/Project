package cerence.ark.assistant.api.notification;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
@Retention(RetentionPolicy.SOURCE)
public @interface NotificationName {
    public static final String ASR_RESULT_UPDATED = "AsrResultUpdated";
    public static final String DIALOG_EVENT = "DialogEvent";
    public static final String DIALOG_STATE_CHANGED = "DialogStateChanged";
    public static final String DYNAMIC_CONTENT_UPDATED = "DynamicContentUpdated";
    public static final String ENHANCED_SIRI_EVENT = "EnhancedSiriEvent";
    public static final String SPEECH_INPUT_TIMEOUT = "SpeechInputTimeout";
    public static final String SPEECH_INPUT_TIMER_BEGIN = "SpeechInputTimerBegin";
    public static final String SPEECH_LEVEL_UPDATED = "SpeechLevelUpdated";
}
