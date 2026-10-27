package android.net.dhcp;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class DhcpServingParamsParcel implements Parcelable {
    public static final Parcelable.Creator<DhcpServingParamsParcel> CREATOR = new Parcelable.Creator<DhcpServingParamsParcel>() { // from class: android.net.dhcp.DhcpServingParamsParcel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DhcpServingParamsParcel createFromParcel(Parcel _aidl_source) {
            DhcpServingParamsParcel _aidl_out = new DhcpServingParamsParcel();
            _aidl_out.readFromParcel(_aidl_source);
            return _aidl_out;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DhcpServingParamsParcel[] newArray(int _aidl_size) {
            return new DhcpServingParamsParcel[_aidl_size];
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

    public final void readFromParcel(Parcel _aidl_parcel) {
        int _aidl_start_pos = _aidl_parcel.dataPosition();
        int _aidl_parcelable_size = _aidl_parcel.readInt();
        if (_aidl_parcelable_size < 0) {
            return;
        }
        try {
            this.serverAddr = _aidl_parcel.readInt();
            if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) {
                return;
            }
            this.serverAddrPrefixLength = _aidl_parcel.readInt();
            if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) {
                return;
            }
            this.defaultRouters = _aidl_parcel.createIntArray();
            if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) {
                return;
            }
            this.dnsServers = _aidl_parcel.createIntArray();
            if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) {
                return;
            }
            this.excludedAddrs = _aidl_parcel.createIntArray();
            if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) {
                return;
            }
            this.dhcpLeaseTimeSecs = _aidl_parcel.readLong();
            if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) {
                return;
            }
            this.linkMtu = _aidl_parcel.readInt();
            if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) {
                return;
            }
            boolean z = true;
            this.metered = _aidl_parcel.readInt() != 0;
            if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) {
                return;
            }
            this.singleClientAddr = _aidl_parcel.readInt();
            if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) {
                return;
            }
            if (_aidl_parcel.readInt() == 0) {
                z = false;
            }
            this.changePrefixOnDecline = z;
            if (_aidl_parcel.dataPosition() - _aidl_start_pos >= _aidl_parcelable_size) {
            }
        } finally {
            _aidl_parcel.setDataPosition(_aidl_start_pos + _aidl_parcelable_size);
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}
