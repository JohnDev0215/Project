package cerence.ark.assistant.api.prompter;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class PrompterInstance implements Parcelable {
    public static final Parcelable.Creator<PrompterInstance> CREATOR = new Parcelable.Creator<PrompterInstance>() { // from class: cerence.ark.assistant.api.prompter.PrompterInstance.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PrompterInstance createFromParcel(Parcel parcel) {
            return new PrompterInstance(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PrompterInstance[] newArray(int i) {
            return new PrompterInstance[i];
        }
    };
    private final String mName;
    private final VoiceType mType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected PrompterInstance(Parcel parcel) {
        this.mName = parcel.readString();
        this.mType = (VoiceType) parcel.readParcelable(VoiceType.class.getClassLoader());
    }

    private PrompterInstance(Builder builder) {
        this.mName = builder.mName;
        this.mType = builder.mType;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mName);
        parcel.writeParcelable(this.mType, i);
    }

    public String getName() {
        return this.mName;
    }

    public VoiceType getType() {
        return this.mType;
    }

    public String toString() {
        return "PrompterInstance{mName='" + this.mName + "', mType=" + this.mType + '}';
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PrompterInstance)) {
            return false;
        }
        PrompterInstance prompterInstance = (PrompterInstance) obj;
        return Objects.equals(this.mName, prompterInstance.mName) && this.mType == prompterInstance.mType;
    }

    public int hashCode() {
        return Objects.hash(this.mName, this.mType);
    }

    public static final class Builder {
        private String mName;
        private VoiceType mType;

        private Builder() {
        }

        public Builder setName(String str) {
            this.mName = str;
            return this;
        }

        public Builder setType(VoiceType voiceType) {
            this.mType = voiceType;
            return this;
        }

        public PrompterInstance build() {
            return new PrompterInstance(this);
        }
    }
}
