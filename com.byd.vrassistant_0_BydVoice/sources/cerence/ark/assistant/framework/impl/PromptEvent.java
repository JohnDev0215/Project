package cerence.ark.assistant.framework.impl;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public enum PromptEvent {
    PROMPT_PLAY_STARTED,
    PROMPT_PLAY_ENDED,
    PROMPT_GENERATION_STARTED,
    PROMPT_GENERATION_ENDED,
    PROMPT_ABORTED,
    PROMPT_FINISHED;

    private static final Map<String, PromptEvent> EVENT_MAP = new HashMap(16);

    static {
        for (PromptEvent promptEvent : values()) {
            EVENT_MAP.put(promptEvent.toString(), promptEvent);
        }
    }

    public static PromptEvent fromString(String str) {
        return EVENT_MAP.get(str.toUpperCase(Locale.US));
    }
}
