package cerence.ark.assistant.dm.data;

/* JADX INFO: loaded from: classes.dex */
public enum SpeechAudioType {
    WAKEUPWORD,
    COMMAND,
    GENERIC;

    public static SpeechAudioType valueOf(int i) {
        if (i < 0 || i > values().length - 1) {
            return GENERIC;
        }
        return values()[i];
    }
}
