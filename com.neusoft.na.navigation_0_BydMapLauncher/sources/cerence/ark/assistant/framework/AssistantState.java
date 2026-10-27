package cerence.ark.assistant.framework;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public enum AssistantState implements Parcelable {
    UNKNOWN,
    INITIALIZED,
    RUNNING,
    UNINITIALIZED;

    public static final Parcelable.Creator<AssistantState> CREATOR = new Parcelable.Creator<AssistantState>() { // from class: cerence.ark.assistant.framework.AssistantState.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AssistantState createFromParcel(Parcel parcel) {
            return AssistantState.valueOf(parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AssistantState[] newArray(int i) {
            return new AssistantState[i];
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
