package android.net;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class DhcpResultsParcelable implements Parcelable {
    public static final Parcelable.Creator<DhcpResultsParcelable> CREATOR = new Parcelable.Creator<DhcpResultsParcelable>() { // from class: android.net.DhcpResultsParcelable.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DhcpResultsParcelable createFromParcel(Parcel parcel) {
            DhcpResultsParcelable dhcpResultsParcelable = new DhcpResultsParcelable();
            dhcpResultsParcelable.readFromParcel(parcel);
            return dhcpResultsParcelable;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DhcpResultsParcelable[] newArray(int i) {
            return new DhcpResultsParcelable[i];
        }
    };
    public StaticIpConfiguration baseConfiguration;
    public String captivePortalApiUrl;
    public int leaseDuration;
    public int mtu;
    public String serverAddress;
    public String serverHostName;
    public String vendorInfo;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        if (this.baseConfiguration != null) {
            parcel.writeInt(1);
            this.baseConfiguration.writeToParcel(parcel, 0);
        } else {
            parcel.writeInt(0);
        }
        parcel.writeInt(this.leaseDuration);
        parcel.writeInt(this.mtu);
        parcel.writeString(this.serverAddress);
        parcel.writeString(this.vendorInfo);
        parcel.writeString(this.serverHostName);
        parcel.writeString(this.captivePortalApiUrl);
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
            if (parcel.readInt() != 0) {
                this.baseConfiguration = (StaticIpConfiguration) StaticIpConfiguration.CREATOR.createFromParcel(parcel);
            } else {
                this.baseConfiguration = null;
            }
            if (parcel.dataPosition() - iDataPosition < i) {
                this.leaseDuration = parcel.readInt();
                if (parcel.dataPosition() - iDataPosition < i) {
                    this.mtu = parcel.readInt();
                    if (parcel.dataPosition() - iDataPosition < i) {
                        this.serverAddress = parcel.readString();
                        if (parcel.dataPosition() - iDataPosition < i) {
                            this.vendorInfo = parcel.readString();
                            if (parcel.dataPosition() - iDataPosition < i) {
                                this.serverHostName = parcel.readString();
                                if (parcel.dataPosition() - iDataPosition < i) {
                                    this.captivePortalApiUrl = parcel.readString();
                                    if (parcel.dataPosition() - iDataPosition < i) {
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } finally {
            parcel.setDataPosition(iDataPosition + i);
        }
    }
}
