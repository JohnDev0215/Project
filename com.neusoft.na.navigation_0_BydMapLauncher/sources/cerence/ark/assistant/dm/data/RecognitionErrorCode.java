package cerence.ark.assistant.dm.data;

/* JADX INFO: loaded from: classes2.dex */
public enum RecognitionErrorCode {
    RECOGNITION_TIMEOUT,
    RECOGNITION_NO_MATCH,
    RECOGNITION_FATAL_ERROR,
    RECOGNITION_UNKNOWN;

    public static RecognitionErrorCode valueOf(int i) {
        if (i < 0 || i > values().length - 1) {
            return RECOGNITION_UNKNOWN;
        }
        return values()[i];
    }
}
