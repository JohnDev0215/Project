package android.net;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class InterfaceConfigurationParcel implements Parcelable {
    public static final Parcelable.Creator<InterfaceConfigurationParcel> CREATOR = new Parcelable.Creator<InterfaceConfigurationParcel>() { // from class: android.net.InterfaceConfigurationParcel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InterfaceConfigurationParcel createFromParcel(Parcel parcel) {
            InterfaceConfigurationParcel interfaceConfigurationParcel = new InterfaceConfigurationParcel();
            interfaceConfigurationParcel.readFromParcel(parcel);
            return interfaceConfigurationParcel;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InterfaceConfigurationParcel[] newArray(int i) {
            return new InterfaceConfigurationParcel[i];
        }
    };
    public String[] flags;
    public String hwAddr;
    public String ifName;
    public String ipv4Addr;
    public int prefixLength;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeString(this.ifName);
        parcel.writeString(this.hwAddr);
        parcel.writeString(this.ipv4Addr);
        parcel.writeInt(this.prefixLength);
        parcel.writeStringArray(this.flags);
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
            this.ifName = parcel.readString();
            if (parcel.dataPosition() - iDataPosition < i) {
                this.hwAddr = parcel.readString();
                if (parcel.dataPosition() - iDataPosition < i) {
                    this.ipv4Addr = parcel.readString();
                    if (parcel.dataPosition() - iDataPosition < i) {
                        this.prefixLength = parcel.readInt();
                        if (parcel.dataPosition() - iDataPosition < i) {
                            this.flags = parcel.createStringArray();
                            if (parcel.dataPosition() - iDataPosition < i) {
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
