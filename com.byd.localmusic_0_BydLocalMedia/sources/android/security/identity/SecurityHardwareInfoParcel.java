package android.security.identity;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes3.dex */
public class SecurityHardwareInfoParcel implements Parcelable {
    public static final Parcelable.Creator<SecurityHardwareInfoParcel> CREATOR = new Parcelable.Creator<SecurityHardwareInfoParcel>() { // from class: android.security.identity.SecurityHardwareInfoParcel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SecurityHardwareInfoParcel createFromParcel(Parcel parcel) {
            SecurityHardwareInfoParcel securityHardwareInfoParcel = new SecurityHardwareInfoParcel();
            securityHardwareInfoParcel.readFromParcel(parcel);
            return securityHardwareInfoParcel;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SecurityHardwareInfoParcel[] newArray(int i) {
            return new SecurityHardwareInfoParcel[i];
        }
    };
    public boolean directAccess;
    public String[] supportedDocTypes;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeInt(this.directAccess ? 1 : 0);
        parcel.writeStringArray(this.supportedDocTypes);
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
            this.directAccess = parcel.readInt() != 0;
            if (parcel.dataPosition() - iDataPosition < i) {
                this.supportedDocTypes = parcel.createStringArray();
                if (parcel.dataPosition() - iDataPosition < i) {
                }
            }
        } finally {
            parcel.setDataPosition(iDataPosition + i);
        }
    }
}
