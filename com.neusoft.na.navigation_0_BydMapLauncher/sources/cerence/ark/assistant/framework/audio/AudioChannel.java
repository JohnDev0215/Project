package cerence.ark.assistant.framework.audio;

/* JADX INFO: loaded from: classes2.dex */
public enum AudioChannel {
    CHANNEL_MONO(1),
    CHANNEL_STEREO(2);

    private final int mValue;

    AudioChannel(int i) {
        this.mValue = i;
    }

    public int getValue() {
        return this.mValue;
    }
}
