package cerence.ark.assistant.framework.data;

import android.media.AudioAttributes;
import android.os.Parcel;
import android.os.Parcelable;
import cerence.ark.assistant.framework.data.wuw.WuwConfig;
import cerence.ark.assistant.library.exception.ArkAssistantException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class AssistantConfiguration implements Parcelable {
    public static final Parcelable.Creator<AssistantConfiguration> CREATOR = new Parcelable.Creator<AssistantConfiguration>() { // from class: cerence.ark.assistant.framework.data.AssistantConfiguration.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AssistantConfiguration createFromParcel(Parcel parcel) {
            return new AssistantConfiguration(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AssistantConfiguration[] newArray(int i) {
            return new AssistantConfiguration[i];
        }
    };
    private final AudioAttributes mAudioAttributes;
    private final boolean mEnableBargeIn;
    private final boolean mEnableSwys;
    private final boolean mEnableUserDataRetention;
    private final String mExtra;
    private final InteractionMode mInteractionMode;
    private final int mListeningTimeout;
    private final int mSnoozingDuration;
    private final String mTtsVoiceId;
    private final List<String> mWakeupWords;
    private final WuwConfig mWuwConfig;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private AssistantConfiguration(Builder builder) {
        this.mEnableBargeIn = builder.mEnableBargeIn;
        this.mSnoozingDuration = builder.mSnoozingDuration;
        this.mListeningTimeout = builder.mListeningTimeout;
        this.mAudioAttributes = builder.mAudioAttributes;
        this.mInteractionMode = builder.mInteractionMode;
        this.mExtra = builder.mExtra;
        this.mWakeupWords = builder.mWakeupWords;
        this.mWuwConfig = builder.mWuwConfig;
        this.mEnableSwys = builder.mEnableSwys;
        this.mTtsVoiceId = builder.mTtsVoiceId;
        this.mEnableUserDataRetention = builder.mEnableUserDataRetention;
    }

    protected AssistantConfiguration(Parcel parcel) {
        this.mEnableBargeIn = parcel.readByte() != 0;
        this.mSnoozingDuration = parcel.readInt();
        this.mListeningTimeout = parcel.readInt();
        this.mAudioAttributes = (AudioAttributes) parcel.readParcelable(AudioAttributes.class.getClassLoader());
        this.mInteractionMode = (InteractionMode) parcel.readParcelable(InteractionMode.class.getClassLoader());
        this.mExtra = parcel.readString();
        this.mWakeupWords = parcel.createStringArrayList();
        this.mWuwConfig = (WuwConfig) parcel.readParcelable(WuwConfig.class.getClassLoader());
        this.mEnableSwys = parcel.readByte() != 0;
        this.mTtsVoiceId = parcel.readString();
        this.mEnableUserDataRetention = parcel.readByte() != 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.mEnableBargeIn ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.mSnoozingDuration);
        parcel.writeInt(this.mListeningTimeout);
        parcel.writeParcelable(this.mAudioAttributes, i);
        parcel.writeParcelable(this.mInteractionMode, i);
        parcel.writeString(this.mExtra);
        parcel.writeStringList(this.mWakeupWords);
        parcel.writeParcelable(this.mWuwConfig, i);
        parcel.writeByte(this.mEnableSwys ? (byte) 1 : (byte) 0);
        parcel.writeString(this.mTtsVoiceId);
        parcel.writeByte(this.mEnableUserDataRetention ? (byte) 1 : (byte) 0);
    }

    public boolean isEnableBargeIn() {
        return this.mEnableBargeIn;
    }

    public int getSnoozingDuration() {
        return this.mSnoozingDuration;
    }

    public int getListeningTimeout() {
        return this.mListeningTimeout;
    }

    public AudioAttributes getAudioAttributes() {
        return this.mAudioAttributes;
    }

    public InteractionMode getInteractionMode() {
        return this.mInteractionMode;
    }

    public String getExtra() {
        return this.mExtra;
    }

    public List<String> getWakeupWords() {
        return this.mWakeupWords;
    }

    public WuwConfig getWuwConfig() {
        return this.mWuwConfig;
    }

    public boolean isEnableSwys() {
        return this.mEnableSwys;
    }

    public String getTtsVoiceId() {
        return this.mTtsVoiceId;
    }

    public boolean isUserDataRetentionEnabled() {
        return this.mEnableUserDataRetention;
    }

    public String toString() {
        return "AssistantConfiguration: \nmEnableBargeIn             = " + this.mEnableBargeIn + "\nmSnoozingDuration          = " + this.mSnoozingDuration + "\nmListeningTimeout          = " + this.mListeningTimeout + "\nmAudioAttributes           = " + this.mAudioAttributes + "\nmInteractionMode           = " + this.mInteractionMode + "\nmExtra                     = " + this.mExtra + "\nmWakeupWords               = " + this.mWakeupWords + "\nmWuwConfig                 = " + this.mWuwConfig + "\nmEnableSwys                = " + this.mEnableSwys + "\nmTtsVoiceId                = " + this.mTtsVoiceId + "\nmEnableUserDataRetention   = " + this.mEnableUserDataRetention;
    }

    public static class Builder {
        private AudioAttributes mAudioAttributes;
        private boolean mEnableBargeIn;
        private String mExtra;
        private String mTtsVoiceId;
        private WuwConfig mWuwConfig;
        private int mSnoozingDuration = 5000;
        private int mListeningTimeout = 5000;
        private InteractionMode mInteractionMode = InteractionMode.WUW;
        private final List<String> mWakeupWords = new ArrayList();
        private boolean mEnableSwys = false;
        private boolean mEnableUserDataRetention = false;

        public Builder enableBargeIn(boolean z) {
            this.mEnableBargeIn = z;
            return this;
        }

        public Builder snoozingDuration(int i) {
            if (i < 0 || i > 10000) {
                throw new ArkAssistantException(String.format("The time range of snoozing is [%s-%s]", 0, 10000));
            }
            this.mSnoozingDuration = i;
            return this;
        }

        public Builder listeningTimeout(int i) {
            if (i < 5000 || i > 10000) {
                throw new ArkAssistantException(String.format("The time range of listening timeout is [%s-%s]", 5000, 10000));
            }
            this.mListeningTimeout = i;
            return this;
        }

        public Builder setAudioAttributes(AudioAttributes audioAttributes) {
            this.mAudioAttributes = audioAttributes;
            return this;
        }

        public Builder interactionMode(InteractionMode interactionMode) {
            this.mInteractionMode = interactionMode;
            return this;
        }

        public Builder setExtra(String str) {
            this.mExtra = str;
            return this;
        }

        public Builder setWakeupWords(List<String> list) {
            if (list != null) {
                this.mWakeupWords.clear();
                this.mWakeupWords.addAll(list);
            }
            return this;
        }

        public Builder setWuwConfig(WuwConfig wuwConfig) {
            this.mWuwConfig = wuwConfig;
            return this;
        }

        public Builder setEnableSwys(boolean z) {
            this.mEnableSwys = z;
            return this;
        }

        public Builder setTtsVoiceId(String str) {
            this.mTtsVoiceId = str;
            return this;
        }

        public Builder setUserDataRetentionEnabled(boolean z) {
            this.mEnableUserDataRetention = z;
            return this;
        }

        public AssistantConfiguration build() {
            return new AssistantConfiguration(this);
        }
    }
}
