package android.security.identity;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class RequestEntryParcel implements Parcelable {
    public static final Parcelable.Creator<RequestEntryParcel> CREATOR = new Parcelable.Creator<RequestEntryParcel>() { // from class: android.security.identity.RequestEntryParcel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RequestEntryParcel createFromParcel(Parcel parcel) {
            RequestEntryParcel requestEntryParcel = new RequestEntryParcel();
            requestEntryParcel.readFromParcel(parcel);
            return requestEntryParcel;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RequestEntryParcel[] newArray(int i) {
            return new RequestEntryParcel[i];
        }
    };
    public String name;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeString(this.name);
        int iDataPosition2 = parcel.dataPosition();
        parcel.setDataPosition(iDataPosition);
        parcel.writeInt(iDataPosition2 - iDataPosition);
        parcel.setDataPosition(iDataPosition2);
    }

    public final void readFromParcel(Parcel parcel) {
        int iDataPosition = parcel.dataPosition();
        int i = parcel.readInt();
        if (i < 0) {
            return;
        }
        try {
            this.name = parcel.readString();
            if (parcel.dataPosition() - iDataPosition >= i) {
            }
        } finally {
            parcel.setDataPosition(iDataPosition + i);
        }
    }
}
