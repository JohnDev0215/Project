package android.security.identity;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes3.dex */
public class RequestNamespaceParcel implements Parcelable {
    public static final Parcelable.Creator<RequestNamespaceParcel> CREATOR = new Parcelable.Creator<RequestNamespaceParcel>() { // from class: android.security.identity.RequestNamespaceParcel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RequestNamespaceParcel createFromParcel(Parcel parcel) {
            RequestNamespaceParcel requestNamespaceParcel = new RequestNamespaceParcel();
            requestNamespaceParcel.readFromParcel(parcel);
            return requestNamespaceParcel;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RequestNamespaceParcel[] newArray(int i) {
            return new RequestNamespaceParcel[i];
        }
    };
    public RequestEntryParcel[] entries;
    public String namespaceName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeString(this.namespaceName);
        parcel.writeTypedArray(this.entries, 0);
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
            this.namespaceName = parcel.readString();
            if (parcel.dataPosition() - iDataPosition < i) {
                this.entries = (RequestEntryParcel[]) parcel.createTypedArray(RequestEntryParcel.CREATOR);
                if (parcel.dataPosition() - iDataPosition < i) {
                }
            }
        } finally {
            parcel.setDataPosition(iDataPosition + i);
        }
    }
}
