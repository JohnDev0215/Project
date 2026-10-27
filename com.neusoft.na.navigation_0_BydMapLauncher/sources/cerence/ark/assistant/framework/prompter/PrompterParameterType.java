package cerence.ark.assistant.framework.prompter;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public enum PrompterParameterType implements Parcelable {
    VOLUME,
    SPEECH_RATE,
    PITCH,
    TIMBRE;

    public static final Parcelable.Creator<PrompterParameterType> CREATOR = new Parcelable.Creator<PrompterParameterType>() { // from class: cerence.ark.assistant.framework.prompter.PrompterParameterType.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PrompterParameterType createFromParcel(Parcel parcel) {
            return PrompterParameterType.valueOf(parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PrompterParameterType[] newArray(int i) {
            return new PrompterParameterType[i];
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
