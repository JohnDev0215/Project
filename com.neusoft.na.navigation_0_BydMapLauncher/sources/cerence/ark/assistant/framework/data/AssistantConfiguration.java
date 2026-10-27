package cerence.ark.assistant.framework.data;

import android.media.AudioAttributes;
import android.os.Parcel;
import android.os.Parcelable;
import cerence.ark.assistant.api.IObserver;
import cerence.ark.assistant.framework.data.wuw.WuwConfig;
import cerence.ark.assistant.library.Utils;
import cerence.ark.assistant.library.exception.ArkAssistantException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
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
    private final String mAsrLanguage;
    private final AudioAttributes mAudioAttributes;
    private final List<String> mDisabledHints;
    private final boolean mEnableBargeIn;
    private final boolean mEnableUserDataRetention;
    private final String mExtra;
    private final List<String> mHybridTtsDomains;
    private final InteractionMode mInteractionMode;
    private final int mListeningTimeout;
    private List<IObserver> mObservers;
    private final int mSnoozingDuration;
    private final VoiceConfig mVoiceConfig;
    private final WakeupRespType mWakeupRespType;
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
        this.mWuwConfig = builder.mWuwConfig;
        this.mVoiceConfig = builder.mVoiceConfig;
        this.mEnableUserDataRetention = builder.mEnableUserDataRetention;
        this.mDisabledHints = builder.mDisabledHints;
        this.mAsrLanguage = builder.mAsrLanguage;
        this.mHybridTtsDomains = builder.mHybridTtsDomains;
        this.mWakeupRespType = builder.mWakeupRespType;
        this.mObservers = builder.mObservers;
    }

    private AssistantConfiguration(Parcel parcel) {
        this.mEnableBargeIn = parcel.readByte() != 0;
        this.mSnoozingDuration = parcel.readInt();
        this.mListeningTimeout = parcel.readInt();
        this.mAudioAttributes = (AudioAttributes) parcel.readParcelable(AudioAttributes.class.getClassLoader());
        this.mInteractionMode = (InteractionMode) parcel.readParcelable(InteractionMode.class.getClassLoader());
        this.mExtra = parcel.readString();
        this.mWuwConfig = (WuwConfig) parcel.readParcelable(WuwConfig.class.getClassLoader());
        this.mVoiceConfig = (VoiceConfig) parcel.readParcelable(VoiceConfig.class.getClassLoader());
        this.mEnableUserDataRetention = parcel.readByte() != 0;
        this.mDisabledHints = parcel.createStringArrayList();
        this.mAsrLanguage = parcel.readString();
        this.mHybridTtsDomains = parcel.createStringArrayList();
        this.mWakeupRespType = (WakeupRespType) parcel.readParcelable(WakeupRespType.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.mEnableBargeIn ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.mSnoozingDuration);
        parcel.writeInt(this.mListeningTimeout);
        parcel.writeParcelable(this.mAudioAttributes, i);
        parcel.writeParcelable(this.mInteractionMode, i);
        parcel.writeString(this.mExtra);
        parcel.writeParcelable(this.mWuwConfig, i);
        parcel.writeParcelable(this.mVoiceConfig, i);
        parcel.writeByte(this.mEnableUserDataRetention ? (byte) 1 : (byte) 0);
        parcel.writeStringList(this.mDisabledHints);
        parcel.writeString(this.mAsrLanguage);
        parcel.writeStringList(this.mHybridTtsDomains);
        parcel.writeParcelable(this.mWakeupRespType, i);
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

    public WuwConfig getWuwConfig() {
        return this.mWuwConfig;
    }

    public VoiceConfig getVoiceConfig() {
        return this.mVoiceConfig;
    }

    public boolean isUserDataRetentionEnabled() {
        return this.mEnableUserDataRetention;
    }

    public List<String> getDisabledHints() {
        return this.mDisabledHints;
    }

    public String getAsrLanguage() {
        return this.mAsrLanguage;
    }

    public List<String> getHybridTtsDomains() {
        return this.mHybridTtsDomains;
    }

    public List<IObserver> getObservers() {
        return this.mObservers;
    }

    public WakeupRespType getWakeupRespType() {
        return this.mWakeupRespType;
    }

    public String toString() {
        return "AssistantConfiguration: \nmEnableBargeIn             = " + this.mEnableBargeIn + "\nmSnoozingDuration          = " + this.mSnoozingDuration + "\nmListeningTimeout          = " + this.mListeningTimeout + "\nmAudioAttributes           = " + this.mAudioAttributes + "\nmInteractionMode           = " + this.mInteractionMode + "\nmExtra                     = " + this.mExtra + "\nmWuwConfig                 = " + this.mWuwConfig + "\nmVoiceConfig               = " + this.mVoiceConfig + "\nmEnableUserDataRetention   = " + this.mEnableUserDataRetention + "\nmDisabledHints             = " + this.mDisabledHints + "\nmAsrLanguage               = " + this.mAsrLanguage + "\nmHybridTtsDomains          = " + this.mHybridTtsDomains + "\nmWakeupRespType            = " + this.mWakeupRespType;
    }

    public static class Builder {
        private String mAsrLanguage;
        private AudioAttributes mAudioAttributes;
        private List<String> mDisabledHints;
        private boolean mEnableBargeIn;
        private String mExtra;
        private List<String> mHybridTtsDomains;
        private List<IObserver> mObservers;
        private VoiceConfig mVoiceConfig;
        private WuwConfig mWuwConfig;
        private int mSnoozingDuration = 0;
        private int mListeningTimeout = 5000;
        private InteractionMode mInteractionMode = InteractionMode.WUW;
        private boolean mEnableUserDataRetention = false;
        private WakeupRespType mWakeupRespType = WakeupRespType.EARCON;

        public Builder enableBargeIn(boolean z) {
            this.mEnableBargeIn = z;
            return this;
        }

        public Builder snoozingDuration(int i) {
            if (i < -1 || i > 10000) {
                throw new ArkAssistantException(Utils.format("The time range of snoozing is [%s-%s]", -1, 10000));
            }
            this.mSnoozingDuration = i;
            return this;
        }

        public Builder listeningTimeout(int i) {
            if (i < 0) {
                throw new ArkAssistantException("The listeningTimeout should be equal to or greater than 0.");
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

        public Builder setWuwConfig(WuwConfig wuwConfig) {
            this.mWuwConfig = wuwConfig;
            return this;
        }

        public Builder setVoiceConfig(VoiceConfig voiceConfig) {
            this.mVoiceConfig = voiceConfig;
            return this;
        }

        public Builder setUserDataRetentionEnabled(boolean z) {
            this.mEnableUserDataRetention = z;
            return this;
        }

        public Builder setDisabledHints(List<String> list) {
            this.mDisabledHints = list != null ? new ArrayList(list) : null;
            return this;
        }

        public Builder setAsrLanguage(String str) {
            this.mAsrLanguage = str;
            return this;
        }

        public Builder setHybridTtsDomains(List<String> list) {
            this.mHybridTtsDomains = list;
            return this;
        }

        public Builder setObservers(List<IObserver> list) {
            this.mObservers = list;
            return this;
        }

        public Builder setWakeupRespType(WakeupRespType wakeupRespType) {
            this.mWakeupRespType = wakeupRespType;
            return this;
        }

        public AssistantConfiguration build() {
            return new AssistantConfiguration(this);
        }
    }
}
