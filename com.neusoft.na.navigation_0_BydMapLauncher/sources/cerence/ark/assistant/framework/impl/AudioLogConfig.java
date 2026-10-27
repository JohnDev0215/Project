package cerence.ark.assistant.framework.impl;

/* JADX INFO: loaded from: classes2.dex */
public class AudioLogConfig {
    private final String mAudioDir;
    private final boolean mIsSaveInputAudio;
    private final boolean mIsSaveOutputAudio;
    private final long mMaximumSize;

    private AudioLogConfig(Builder builder) {
        this.mAudioDir = builder.mAudioDir;
        this.mIsSaveInputAudio = builder.mIsSaveInputAudio;
        this.mIsSaveOutputAudio = builder.mIsSaveOutputAudio;
        this.mMaximumSize = builder.mMaximumSize;
    }

    public static class Builder {
        private String mAudioDir;
        private boolean mIsSaveInputAudio;
        private boolean mIsSaveOutputAudio;
        private long mMaximumSize = -1;

        public Builder setAudioDir(String str) {
            this.mAudioDir = str;
            return this;
        }

        public Builder setMaximumSize(long j) {
            this.mMaximumSize = j;
            return this;
        }

        public Builder setSaveOutputAudio(boolean z) {
            this.mIsSaveOutputAudio = z;
            return this;
        }

        public Builder setSaveInputAudio(boolean z) {
            this.mIsSaveInputAudio = z;
            return this;
        }

        public AudioLogConfig build() {
            return new AudioLogConfig(this);
        }
    }

    public boolean isSaveOutputAudio() {
        return this.mIsSaveOutputAudio;
    }

    public boolean isSaveInputAudio() {
        return this.mIsSaveInputAudio;
    }

    public String getAudioDir() {
        return this.mAudioDir;
    }

    public long getMaximumSize() {
        return this.mMaximumSize;
    }
}
