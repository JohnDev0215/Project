package cerence.ark.assistant.api.prompter;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class TtsVoice implements Parcelable {
    public static final Parcelable.Creator<TtsVoice> CREATOR = new Parcelable.Creator<TtsVoice>() { // from class: cerence.ark.assistant.api.prompter.TtsVoice.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TtsVoice createFromParcel(Parcel parcel) {
            return new TtsVoice(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TtsVoice[] newArray(int i) {
            return new TtsVoice[i];
        }
    };
    private final String[] mAvailableDomains;
    private final String mGender;
    private final String mLanguage;
    private final String mOperatingPoint;
    private final String mSpeakerName;
    private final boolean mSupportedBySds;
    private final VoiceType mType;
    private final String mVersion;
    private final String mVoiceId;
    private final String mVoiceName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private TtsVoice(Builder builder) {
        this.mVoiceId = builder.mVoiceId;
        this.mLanguage = builder.mLanguage;
        this.mVoiceName = builder.mVoiceName;
        this.mGender = builder.mGender;
        this.mOperatingPoint = builder.mOperatingPoint;
        this.mVersion = builder.mVersion;
        this.mAvailableDomains = builder.mAvailableDomains;
        this.mType = builder.mType;
        this.mSupportedBySds = builder.mSupportedBySds;
        this.mSpeakerName = builder.mSpeakerName;
    }

    protected TtsVoice(Parcel parcel) {
        this.mVoiceId = parcel.readString();
        this.mLanguage = parcel.readString();
        this.mVoiceName = parcel.readString();
        this.mGender = parcel.readString();
        this.mOperatingPoint = parcel.readString();
        this.mVersion = parcel.readString();
        this.mAvailableDomains = parcel.createStringArray();
        this.mType = (VoiceType) parcel.readParcelable(VoiceType.class.getClassLoader());
        this.mSupportedBySds = parcel.readByte() != 0;
        this.mSpeakerName = parcel.readString();
    }

    public String getVoiceId() {
        return this.mVoiceId;
    }

    public String getLanguage() {
        return this.mLanguage;
    }

    public String getVoiceName() {
        return this.mVoiceName;
    }

    public String getGender() {
        return this.mGender;
    }

    public String getOperatingPoint() {
        return this.mOperatingPoint;
    }

    public String getVersion() {
        return this.mVersion;
    }

    public String[] getAvailableDomains() {
        return this.mAvailableDomains;
    }

    public VoiceType getType() {
        return this.mType;
    }

    public boolean isSupportedBySds() {
        return this.mSupportedBySds;
    }

    public String getSpeakerName() {
        return this.mSpeakerName;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TtsVoice)) {
            return false;
        }
        String str = this.mVoiceId;
        String voiceId = ((TtsVoice) obj).getVoiceId();
        return str != null ? str.equals(voiceId) : voiceId == null;
    }

    public int hashCode() {
        String str = this.mVoiceId;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "TtsVoice{voidId: " + this.mVoiceId + ", language: " + this.mLanguage + ", speakerName: " + this.mSpeakerName + ", voiceName: " + this.mVoiceName + ", gender: " + this.mGender + ", operatingPoint: " + this.mOperatingPoint + ", version: " + this.mVersion + ", availableDomains: " + Arrays.toString(this.mAvailableDomains) + ", type: " + this.mType + ", supportedBySds: " + this.mSupportedBySds + "}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mVoiceId);
        parcel.writeString(this.mLanguage);
        parcel.writeString(this.mVoiceName);
        parcel.writeString(this.mGender);
        parcel.writeString(this.mOperatingPoint);
        parcel.writeString(this.mVersion);
        parcel.writeStringArray(this.mAvailableDomains);
        parcel.writeParcelable(this.mType, i);
        parcel.writeByte(this.mSupportedBySds ? (byte) 1 : (byte) 0);
        parcel.writeString(this.mSpeakerName);
    }

    public static class Builder {
        private String[] mAvailableDomains;
        private boolean mSupportedBySds;
        private VoiceType mType;
        private String mVoiceId = "";
        private String mLanguage = "";
        private String mVoiceName = "";
        private String mGender = "";
        private String mOperatingPoint = "";
        private String mVersion = "";
        private String mSpeakerName = "";

        public Builder setVoiceId(String str) {
            if (str == null) {
                str = "";
            }
            this.mVoiceId = str;
            return this;
        }

        public Builder setLanguage(String str) {
            if (str == null) {
                str = "";
            }
            this.mLanguage = str;
            return this;
        }

        public Builder setVoiceName(String str) {
            if (str == null) {
                str = "";
            }
            this.mVoiceName = str;
            return this;
        }

        public Builder setGender(String str) {
            if (str == null) {
                str = "";
            }
            this.mGender = str;
            return this;
        }

        public Builder setOperatingPoint(String str) {
            if (str == null) {
                str = "";
            }
            this.mOperatingPoint = str;
            return this;
        }

        public Builder setVersion(String str) {
            if (str == null) {
                str = "";
            }
            this.mVersion = str;
            return this;
        }

        public Builder setAvailableDomains(String[] strArr) {
            this.mAvailableDomains = strArr;
            return this;
        }

        public Builder setType(VoiceType voiceType) {
            this.mType = voiceType;
            return this;
        }

        public Builder setSupportedBySds(boolean z) {
            this.mSupportedBySds = z;
            return this;
        }

        public Builder setSpeakerName(String str) {
            if (str == null) {
                str = "";
            }
            this.mSpeakerName = str;
            return this;
        }

        public TtsVoice build() {
            return new TtsVoice(this);
        }
    }
}
