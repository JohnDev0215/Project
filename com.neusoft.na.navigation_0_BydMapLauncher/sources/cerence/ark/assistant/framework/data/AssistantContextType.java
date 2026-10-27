package cerence.ark.assistant.framework.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public enum AssistantContextType implements Parcelable {
    NLU_CONTEXT,
    ASR_CONTEXT,
    TEXT_CONTEXT;

    public static final Parcelable.Creator<AssistantContextType> CREATOR = new Parcelable.Creator<AssistantContextType>() { // from class: cerence.ark.assistant.framework.data.AssistantContextType.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AssistantContextType createFromParcel(Parcel parcel) {
            return AssistantContextType.valueOf(parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AssistantContextType[] newArray(int i) {
            return new AssistantContextType[i];
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
