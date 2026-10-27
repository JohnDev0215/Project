package android.net;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class PrivateDnsConfigParcel implements Parcelable {
    public static final Parcelable.Creator<PrivateDnsConfigParcel> CREATOR = new Parcelable.Creator<PrivateDnsConfigParcel>() { // from class: android.net.PrivateDnsConfigParcel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PrivateDnsConfigParcel createFromParcel(Parcel parcel) {
            PrivateDnsConfigParcel privateDnsConfigParcel = new PrivateDnsConfigParcel();
            privateDnsConfigParcel.readFromParcel(parcel);
            return privateDnsConfigParcel;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PrivateDnsConfigParcel[] newArray(int i) {
            return new PrivateDnsConfigParcel[i];
        }
    };
    public String hostname;
    public String[] ips;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeString(this.hostname);
        parcel.writeStringArray(this.ips);
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
            this.hostname = parcel.readString();
            if (parcel.dataPosition() - iDataPosition < i) {
                this.ips = parcel.createStringArray();
                if (parcel.dataPosition() - iDataPosition < i) {
                }
            }
        } finally {
            parcel.setDataPosition(iDataPosition + i);
        }
    }
}
