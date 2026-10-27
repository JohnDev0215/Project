package com.android.internal.net;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.DateFormat;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class VpnInfo implements Parcelable {
    public static final Parcelable.Creator<VpnInfo> CREATOR = new Parcelable.Creator<VpnInfo>() { // from class: com.android.internal.net.VpnInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VpnInfo createFromParcel(Parcel parcel) {
            VpnInfo vpnInfo = new VpnInfo();
            vpnInfo.ownerUid = parcel.readInt();
            vpnInfo.vpnIface = parcel.readString();
            vpnInfo.underlyingIfaces = parcel.readStringArray();
            return vpnInfo;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VpnInfo[] newArray(int i) {
            return new VpnInfo[i];
        }
    };
    public int ownerUid;
    public String[] underlyingIfaces;
    public String vpnIface;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "VpnInfo{ownerUid=" + this.ownerUid + ", vpnIface='" + this.vpnIface + DateFormat.QUOTE + ", underlyingIfaces='" + Arrays.toString(this.underlyingIfaces) + DateFormat.QUOTE + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.ownerUid);
        parcel.writeString(this.vpnIface);
        parcel.writeStringArray(this.underlyingIfaces);
    }
}
