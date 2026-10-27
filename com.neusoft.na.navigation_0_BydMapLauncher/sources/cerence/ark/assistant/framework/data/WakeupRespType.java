package cerence.ark.assistant.framework.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public enum WakeupRespType implements Parcelable {
    TEXT,
    EARCON;

    public static final Parcelable.Creator<WakeupRespType> CREATOR = new Parcelable.Creator<WakeupRespType>() { // from class: cerence.ark.assistant.framework.data.WakeupRespType.1
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
