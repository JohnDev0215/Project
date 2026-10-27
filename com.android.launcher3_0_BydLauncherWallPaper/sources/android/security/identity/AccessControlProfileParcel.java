package android.security.identity;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class AccessControlProfileParcel implements Parcelable {
    public static final Parcelable.Creator<AccessControlProfileParcel> CREATOR = new Parcelable.Creator<AccessControlProfileParcel>() { // from class: android.security.identity.AccessControlProfileParcel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AccessControlProfileParcel createFromParcel(Parcel parcel) {
            AccessControlProfileParcel accessControlProfileParcel = new AccessControlProfileParcel();
            accessControlProfileParcel.readFromParcel(parcel);
            return accessControlProfileParcel;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AccessControlProfileParcel[] newArray(int i) {
            return new AccessControlProfileParcel[i];
        }
    };
    public int id;
    public byte[] readerCertificate;
    public boolean userAuthenticationRequired;
    public long userAuthenticationTimeoutMillis;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeInt(this.id);
        parcel.writeByteArray(this.readerCertificate);
        parcel.writeInt(this.userAuthenticationRequired ? 1 : 0);
        parcel.writeLong(this.userAuthenticationTimeoutMillis);
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
            this.id = parcel.readInt();
            if (parcel.dataPosition() - iDataPosition < i) {
                this.readerCertificate = parcel.createByteArray();
                if (parcel.dataPosition() - iDataPosition < i) {
                    this.userAuthenticationRequired = parcel.readInt() != 0;
                    if (parcel.dataPosition() - iDataPosition < i) {
                        this.userAuthenticationTimeoutMillis = parcel.readLong();
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
