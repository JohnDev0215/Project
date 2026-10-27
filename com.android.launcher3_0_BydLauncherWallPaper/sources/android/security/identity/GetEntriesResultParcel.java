package android.security.identity;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class GetEntriesResultParcel implements Parcelable {
    public static final Parcelable.Creator<GetEntriesResultParcel> CREATOR = new Parcelable.Creator<GetEntriesResultParcel>() { // from class: android.security.identity.GetEntriesResultParcel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GetEntriesResultParcel createFromParcel(Parcel parcel) {
            GetEntriesResultParcel getEntriesResultParcel = new GetEntriesResultParcel();
            getEntriesResultParcel.readFromParcel(parcel);
            return getEntriesResultParcel;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GetEntriesResultParcel[] newArray(int i) {
            return new GetEntriesResultParcel[i];
        }
    };
    public byte[] deviceNameSpaces;
    public byte[] mac;
    public ResultNamespaceParcel[] resultNamespaces;
    public byte[] staticAuthenticationData;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeTypedArray(this.resultNamespaces, 0);
        parcel.writeByteArray(this.deviceNameSpaces);
        parcel.writeByteArray(this.mac);
        parcel.writeByteArray(this.staticAuthenticationData);
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
            this.resultNamespaces = (ResultNamespaceParcel[]) parcel.createTypedArray(ResultNamespaceParcel.CREATOR);
            if (parcel.dataPosition() - iDataPosition < i) {
                this.deviceNameSpaces = parcel.createByteArray();
                if (parcel.dataPosition() - iDataPosition < i) {
                    this.mac = parcel.createByteArray();
                    if (parcel.dataPosition() - iDataPosition < i) {
                        this.staticAuthenticationData = parcel.createByteArray();
                        if (parcel.dataPosition() - iDataPosition < i) {
                        }
                    }
                }
            }
        } finally {
            parcel.setDataPosition(iDataPosition + i);
        }
    }
}
