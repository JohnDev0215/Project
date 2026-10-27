package android.security.identity;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes3.dex */
public class AuthKeyParcel implements Parcelable {
    public static final Parcelable.Creator<AuthKeyParcel> CREATOR = new Parcelable.Creator<AuthKeyParcel>() { // from class: android.security.identity.AuthKeyParcel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AuthKeyParcel createFromParcel(Parcel parcel) {
            AuthKeyParcel authKeyParcel = new AuthKeyParcel();
            authKeyParcel.readFromParcel(parcel);
            return authKeyParcel;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AuthKeyParcel[] newArray(int i) {
            return new AuthKeyParcel[i];
        }
    };
    public byte[] x509cert;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeByteArray(this.x509cert);
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
            this.x509cert = parcel.createByteArray();
            if (parcel.dataPosition() - iDataPosition >= i) {
            }
        } finally {
            parcel.setDataPosition(iDataPosition + i);
        }
    }
}
