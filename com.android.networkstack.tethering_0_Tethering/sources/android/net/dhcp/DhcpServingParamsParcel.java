package android.net.dhcp;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class DhcpServingParamsParcel implements Parcelable {
    public static final Parcelable.Creator<DhcpServingParamsParcel> CREATOR = new Parcelable.Creator<DhcpServingParamsParcel>() { // from class: android.net.dhcp.DhcpServingParamsParcel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DhcpServingParamsParcel createFromParcel(Parcel parcel) {
            DhcpServingParamsParcel dhcpServingParamsParcel = new DhcpServingParamsParcel();
            dhcpServingParamsParcel.readFromParcel(parcel);
            return dhcpServingParamsParcel;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DhcpServingParamsParcel[] newArray(int i) {
            return new DhcpServingParamsParcel[i];
        }
    };
    public int[] defaultRouters;
    public long dhcpLeaseTimeSecs;
    public int[] dnsServers;
    public int[] excludedAddrs;
    public int linkMtu;
    public boolean metered;
    public int serverAddr;
    public int serverAddrPrefixLength;
    public int singleClientAddr = 0;
    public boolean changePrefixOnDecline = false;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeInt(this.serverAddr);
        parcel.writeInt(this.serverAddrPrefixLength);
        parcel.writeIntArray(this.defaultRouters);
        parcel.writeIntArray(this.dnsServers);
        parcel.writeIntArray(this.excludedAddrs);
        parcel.writeLong(this.dhcpLeaseTimeSecs);
        parcel.writeInt(this.linkMtu);
        parcel.writeInt(this.metered ? 1 : 0);
        parcel.writeInt(this.singleClientAddr);
        parcel.writeInt(this.changePrefixOnDecline ? 1 : 0);
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
            this.serverAddr = parcel.readInt();
            if (parcel.dataPosition() - iDataPosition < i) {
                this.serverAddrPrefixLength = parcel.readInt();
                if (parcel.dataPosition() - iDataPosition < i) {
                    this.defaultRouters = parcel.createIntArray();
                    if (parcel.dataPosition() - iDataPosition < i) {
                        this.dnsServers = parcel.createIntArray();
                        if (parcel.dataPosition() - iDataPosition < i) {
                            this.excludedAddrs = parcel.createIntArray();
                            if (parcel.dataPosition() - iDataPosition < i) {
                                this.dhcpLeaseTimeSecs = parcel.readLong();
                                if (parcel.dataPosition() - iDataPosition < i) {
                                    this.linkMtu = parcel.readInt();
                                    if (parcel.dataPosition() - iDataPosition < i) {
                                        boolean z = true;
                                        this.metered = parcel.readInt() != 0;
                                        if (parcel.dataPosition() - iDataPosition < i) {
                                            this.singleClientAddr = parcel.readInt();
                                            if (parcel.dataPosition() - iDataPosition < i) {
                                                if (parcel.readInt() == 0) {
                                                    z = false;
                                                }
                                                this.changePrefixOnDecline = z;
                                                if (parcel.dataPosition() - iDataPosition < i) {
                                                }
                                            }
                                        }
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
