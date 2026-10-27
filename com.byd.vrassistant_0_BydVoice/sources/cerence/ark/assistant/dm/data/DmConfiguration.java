package cerence.ark.assistant.dm.data;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class DmConfiguration {
    public static final int DEFAULT_LISTENING_TIMEOUT = 5000;
    public static final int DEFAULT_SNOOZING_DURATION = 5000;
    public static final int DEFAULT_TTS_STREAM_TYPE = 3;
    public static final int MAX_LISTENING_TIMEOUT = 10000;
    public static final int MAX_SNOOZING_DURATION = 10000;
    public static final int MIN_LISTENING_TIMEOUT = 5000;
    public static final int MIN_SNOOZING_DURATION = 0;
    private final boolean mEnableBargeIn;
    boolean mEnableDataRetention;
    private final boolean mEnableSwys;
    private final String mExtra;
    private final InteractionMode mInteractionMode;
    private final boolean mIsReceiveSpeechAudio;
    private final int mListeningTimeout;
    private final int mSnoozingDuration;
    private final int mTtsStreamType;
    private final String mVoiceId;
    private final String[] mWakeupWords;
    private final String mWuwJsonStr;

    private DmConfiguration(Builder builder) {
        this.mEnableBargeIn = builder.mEnableBargeIn;
        this.mSnoozingDuration = builder.mSnoozingDuration;
        this.mListeningTimeout = builder.mListeningTimeout;
        this.mTtsStreamType = builder.mTtsStreamType;
        this.mInteractionMode = builder.mInteractionMode;
        this.mExtra = builder.mExtra;
        this.mIsReceiveSpeechAudio = builder.mIsReceiveSpeechAudio;
        this.mWakeupWords = builder.mWakeupWords;
        this.mEnableSwys = builder.mEnableSwys;
        this.mVoiceId = builder.mVoiceId;
        this.mEnableDataRetention = builder.mEnableDataRetention;
        this.mWuwJsonStr = builder.mWuwJsonStr;
    }

    public boolean isEnableBargeIn() {
        return this.mEnableBargeIn;
    }

    public boolean isEnableSwys() {
        return this.mEnableSwys;
    }

    public boolean isDataRetentionEnabled() {
        return this.mEnableDataRetention;
    }

    public int getSnoozingDuration() {
        return this.mSnoozingDuration;
    }

    public int getListeningTimeout() {
        return this.mListeningTimeout;
    }

    public int getTtsStreamType() {
        return this.mTtsStreamType;
    }

    public InteractionMode getInteractionMode() {
        return this.mInteractionMode;
    }

    public String getExtra() {
        return this.mExtra;
    }

    public String getWuw() {
        return this.mWuwJsonStr;
    }

    public boolean isReceiveSpeechAudio() {
        return this.mIsReceiveSpeechAudio;
    }

    public String[] getWakeupWords() {
        return this.mWakeupWords;
    }

    public String getTtsVoiceId() {
        return this.mVoiceId;
    }

    public String toString() {
        return "DmConfiguration{mEnableBargeIn=" + this.mEnableBargeIn + ", mSnoozingDuration=" + this.mSnoozingDuration + ", mListeningTimeout=" + this.mListeningTimeout + ", mTtsStreamType=" + this.mTtsStreamType + ", mInteractionMode=" + this.mInteractionMode + ", mExtra='" + this.mExtra + "', mIsReceiveSpeechAudio=" + this.mIsReceiveSpeechAudio + ", mWakeUpWords=" + Arrays.toString(this.mWakeupWords) + '}';
    }

    public enum InteractionMode implements Parcelable {
        WUW,
        PTT;

        public static final Parcelable.Creator<InteractionMode> CREATOR = new Parcelable.Creator<InteractionMode>() { // from class: cerence.ark.assistant.dm.data.DmConfiguration.InteractionMode.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public InteractionMode createFromParcel(Parcel parcel) {
                return InteractionMode.valueOf(parcel.readString());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public InteractionMode[] newArray(int i) {
                return new InteractionMode[i];
            }
        };

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(name());
        }
    }

    public static class Builder {
        private boolean mEnableBargeIn;
        private boolean mEnableDataRetention;
        private boolean mEnableSwys;
        private String mExtra;
        private boolean mIsReceiveSpeechAudio;
        private String mVoiceId;
        private String[] mWakeupWords;
        private String mWuwJsonStr;
        private int mSnoozingDuration = 5000;
        private int mListeningTimeout = 5000;
        private int mTtsStreamType = 3;
        private InteractionMode mInteractionMode = InteractionMode.WUW;

        public Builder enableBargeIn(boolean z) {
            this.mEnableBargeIn = z;
            return this;
        }

        public Builder enableSwys(boolean z) {
            this.mEnableSwys = z;
            return this;
        }

        public Builder snoozingDuration(int i) {
            if (i < 0 || i > 10000) {
                throw new IllegalArgumentException(String.format("The time range of snoozing is [%s-%s]", 0, 10000));
            }
            this.mSnoozingDuration = i;
            return this;
        }

        public Builder listeningTimeout(int i) {
            if (i < 5000 || i > 10000) {
                throw new IllegalArgumentException(String.format("The time range of listening timeout is [%s-%s]", 5000, 10000));
            }
            this.mListeningTimeout = i;
            return this;
        }

        public Builder ttsStreamType(int i) {
            this.mTtsStreamType = i;
            return this;
        }

        public Builder interactionMode(InteractionMode interactionMode) {
            this.mInteractionMode = interactionMode;
            return this;
        }

        public Builder extra(String str) {
            this.mExtra = str;
            return this;
        }

        public Builder isReceiveSpeechAudio(boolean z) {
            this.mIsReceiveSpeechAudio = z;
            return this;
        }

        public Builder setWakeupWords(String[] strArr) {
            this.mWakeupWords = strArr;
            return this;
        }

        public Builder setTtsVoiceId(String str) {
            this.mVoiceId = str;
            return this;
        }

        public Builder enableDataRetention(boolean z) {
            this.mEnableDataRetention = z;
            return this;
        }

        public Builder setWuw(String str) {
            this.mWuwJsonStr = str;
            return this;
        }

        public DmConfiguration build() {
            return new DmConfiguration(this);
        }
    }
}
