package android.net;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class InitialConfigurationParcelable implements Parcelable {
    public static final Parcelable.Creator<InitialConfigurationParcelable> CREATOR = new Parcelable.Creator<InitialConfigurationParcelable>() { // from class: android.net.InitialConfigurationParcelable.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InitialConfigurationParcelable createFromParcel(Parcel parcel) {
            InitialConfigurationParcelable initialConfigurationParcelable = new InitialConfigurationParcelable();
            initialConfigurationParcelable.readFromParcel(parcel);
            return initialConfigurationParcelable;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InitialConfigurationParcelable[] newArray(int i) {
            return new InitialConfigurationParcelable[i];
        }
    };
    public IpPrefix[] directlyConnectedRoutes;
    public String[] dnsServers;
    public String gateway;
    public LinkAddress[] ipAddresses;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeTypedArray(this.ipAddresses, 0);
        parcel.writeTypedArray(this.directlyConnectedRoutes, 0);
        parcel.writeStringArray(this.dnsServers);
        parcel.writeString(this.gateway);
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
            this.ipAddresses = (LinkAddress[]) parcel.createTypedArray(LinkAddress.CREATOR);
            if (parcel.dataPosition() - iDataPosition < i) {
                this.directlyConnectedRoutes = (IpPrefix[]) parcel.createTypedArray(IpPrefix.CREATOR);
                if (parcel.dataPosition() - iDataPosition < i) {
                    this.dnsServers = parcel.createStringArray();
                    if (parcel.dataPosition() - iDataPosition < i) {
                        this.gateway = parcel.readString();
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
