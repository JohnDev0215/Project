package cerence.ark.assistant.dm.data;

/* JADX INFO: loaded from: classes.dex */
public enum FatalErrorCode {
    FATAL_RECOGNITION_START_FAILED,
    FATAL_AUDIO_OUTPUT_TIMEOUT,
    FATAL_AUDIO_INPUT_TIMEOUT,
    FATAL_RECOGNITION_TIMEOUT,
    FATAL_UNKNOWN;

    public static FatalErrorCode valueOf(int i) {
        if (i < 0 || i > values().length - 1) {
            return FATAL_UNKNOWN;
        }
        return values()[i];
    }
}
