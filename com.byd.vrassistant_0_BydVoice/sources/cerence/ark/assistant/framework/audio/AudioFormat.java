package cerence.ark.assistant.framework.audio;

/* JADX INFO: loaded from: classes.dex */
public enum AudioFormat {
    PCM_8BIT(8),
    PCM_16BIT(16);

    private final int mValue;

    AudioFormat(int i) {
        this.mValue = i;
    }

    public int getValue() {
        return this.mValue;
    }
}
