package cerence.ark.assistant.dm.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public final class DmConfiguration {
    public static final int DEFAULT_LISTENING_TIMEOUT = 5000;
    public static final int DEFAULT_SNOOZING_DURATION = 0;
    public static final int MAX_SNOOZING_DURATION = 10000;
    public static final int MIN_SNOOZING_DURATION = -1;
    private final String mDisabledHints;
    private final boolean mEnableBargeIn;
    private final boolean mEnableDataRetention;
    private final String mExtra;
    private final String mHybridTTSDomains;
    private final InteractionMode mInteractionMode;
    private final boolean mIsReceiveSpeechAudio;
    private final String mLanguage;
    private final int mListeningTimeout;
    private final int mSnoozingDuration;
    private final String mVoice;
    private final WakeupRespType mWakeupRespType;
    private final String mWuwJsonStr;

    private DmConfiguration(Builder builder) {
        this.mEnableBargeIn = builder.mEnableBargeIn;
        this.mSnoozingDuration = builder.mSnoozingDuration;
        this.mListeningTimeout = builder.mListeningTimeout;
        this.mInteractionMode = builder.mInteractionMode;
        this.mExtra = builder.mExtra;
        this.mDisabledHints = builder.mDisabledHints;
        this.mIsReceiveSpeechAudio = builder.mIsReceiveSpeechAudio;
        this.mWuwJsonStr = builder.mWuwJsonStr;
        this.mEnableDataRetention = builder.mEnableDataRetention;
        this.mVoice = builder.mVoice;
        this.mLanguage = builder.mLanguage;
        this.mHybridTTSDomains = builder.mHybridTTSDomains;
        this.mWakeupRespType = builder.mWakeupRespType;
    }

    public boolean isEnableBargeIn() {
        return this.mEnableBargeIn;
    }

    public boolean isEnableDataRetention() {
        return this.mEnableDataRetention;
    }

    public int getSnoozingDuration() {
        return this.mSnoozingDuration;
    }

    public int getListeningTimeout() {
        return this.mListeningTimeout;
    }

    public InteractionMode getInteractionMode() {
        return this.mInteractionMode;
    }

    public WakeupRespType getWakeupRespType() {
        return this.mWakeupRespType;
    }

    public String getExtra() {
        return this.mExtra;
    }

    public String getDisabledHints() {
        return this.mDisabledHints;
    }

    public boolean isReceiveSpeechAudio() {
        return this.mIsReceiveSpeechAudio;
    }

    public String getWuw() {
        return this.mWuwJsonStr;
    }

    public String getTtsVoice() {
        return this.mVoice;
    }

    public String getLanguage() {
        return this.mLanguage;
    }

    public String getHybridTTSDomains() {
        return this.mHybridTTSDomains;
    }

    public enum InteractionMode implements Parcelable {
        WUW,
        PTT,
        JT;

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

    public enum WakeupRespType implements Parcelable {
        TEXT,
        EARCON;

        public static final Parcelable.Creator<WakeupRespType> CREATOR = new Parcelable.Creator<WakeupRespType>() { // from class: cerence.ark.assistant.dm.data.DmConfiguration.WakeupRespType.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public WakeupRespType createFromParcel(Parcel parcel) {
                return WakeupRespType.valueOf(parcel.readString());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public WakeupRespType[] newArray(int i) {
                return new WakeupRespType[i];
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
        private String mDisabledHints;
        private boolean mEnableBargeIn;
        private boolean mEnableDataRetention;
        private String mExtra;
        private String mHybridTTSDomains;
        private boolean mIsReceiveSpeechAudio;
        private String mLanguage;
        private String mVoice;
        private String mWuwJsonStr;
        private int mSnoozingDuration = 0;
        private int mListeningTimeout = 5000;
        private InteractionMode mInteractionMode = InteractionMode.WUW;
        private WakeupRespType mWakeupRespType = WakeupRespType.EARCON;

        public Builder enableBargeIn(boolean z) {
            this.mEnableBargeIn = z;
            return this;
        }

        public Builder enableDataRetention(boolean z) {
            this.mEnableDataRetention = z;
            return this;
        }

        public Builder snoozingDuration(int i) {
            if (i < -1 || i > 10000) {
                throw new IllegalArgumentException(String.format("The time range of snoozing is [%s-%s]", -1, 10000));
            }
            this.mSnoozingDuration = i;
            return this;
        }

        public Builder listeningTimeout(int i) {
            if (i < 0) {
                throw new IllegalArgumentException("The listeningTimeout should be equal to or greater than 0.");
            }
            this.mListeningTimeout = i;
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

        public Builder disabledHints(String str) {
            this.mDisabledHints = str;
            return this;
        }

        public Builder isReceiveSpeechAudio(boolean z) {
            this.mIsReceiveSpeechAudio = z;
            return this;
        }

        public Builder setWuw(String str) {
            this.mWuwJsonStr = str;
            return this;
        }

        public Builder setTtsVoice(String str) {
            this.mVoice = str;
            return this;
        }

        public Builder setLanguage(String str) {
            this.mLanguage = str;
            return this;
        }

        public Builder setHybridTTSDomains(String str) {
            this.mHybridTTSDomains = str;
            return this;
        }

        public Builder setWakeupRespType(WakeupRespType wakeupRespType) {
            this.mWakeupRespType = wakeupRespType;
            return this;
        }

        public DmConfiguration build() {
            return new DmConfiguration(this);
        }
    }
}
