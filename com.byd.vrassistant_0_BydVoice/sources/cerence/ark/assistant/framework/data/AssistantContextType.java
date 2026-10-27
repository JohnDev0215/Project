package cerence.ark.assistant.framework.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public enum AssistantContextType implements Parcelable {
    NLU_CONTEXT(0);

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
    private final int mValue;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    AssistantContextType(int i) {
        this.mValue = i;
    }

    public int getValue() {
        return this.mValue;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name());
    }
}
