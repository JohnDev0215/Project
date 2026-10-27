package android.net;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class TetherConfigParcel implements Parcelable {
    public static final Parcelable.Creator<TetherConfigParcel> CREATOR = new Parcelable.Creator<TetherConfigParcel>() { // from class: android.net.TetherConfigParcel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TetherConfigParcel createFromParcel(Parcel parcel) {
            TetherConfigParcel tetherConfigParcel = new TetherConfigParcel();
            tetherConfigParcel.readFromParcel(parcel);
            return tetherConfigParcel;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TetherConfigParcel[] newArray(int i) {
            return new TetherConfigParcel[i];
        }
    };
    public String[] dhcpRanges;
    public boolean usingLegacyDnsProxy;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeInt(this.usingLegacyDnsProxy ? 1 : 0);
        parcel.writeStringArray(this.dhcpRanges);
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
            this.usingLegacyDnsProxy = parcel.readInt() != 0;
            if (parcel.dataPosition() - iDataPosition < i) {
                this.dhcpRanges = parcel.createStringArray();
                if (parcel.dataPosition() - iDataPosition < i) {
                }
            }
        } finally {
            parcel.setDataPosition(iDataPosition + i);
        }
    }
}
