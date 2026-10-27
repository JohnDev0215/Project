package cerence.ark.assistant.framework.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public enum RppLocationType implements Parcelable {
    LOCATION_CURRENT,
    LOCATION_DESTINATION,
    LOCATION_IN_PROGRESS;

    public static final Parcelable.Creator<RppLocationType> CREATOR = new Parcelable.Creator<RppLocationType>() { // from class: cerence.ark.assistant.framework.data.RppLocationType.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RppLocationType createFromParcel(Parcel parcel) {
            return RppLocationType.valueOf(parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RppLocationType[] newArray(int i) {
            return new RppLocationType[i];
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
