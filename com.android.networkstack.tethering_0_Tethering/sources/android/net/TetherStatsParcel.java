package android.net;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class TetherStatsParcel implements Parcelable {
    public static final Parcelable.Creator<TetherStatsParcel> CREATOR = new Parcelable.Creator<TetherStatsParcel>() { // from class: android.net.TetherStatsParcel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TetherStatsParcel createFromParcel(Parcel parcel) {
            TetherStatsParcel tetherStatsParcel = new TetherStatsParcel();
            tetherStatsParcel.readFromParcel(parcel);
            return tetherStatsParcel;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TetherStatsParcel[] newArray(int i) {
            return new TetherStatsParcel[i];
        }
    };
    public int ifIndex = 0;
    public String iface;
    public long rxBytes;
    public long rxPackets;
    public long txBytes;
    public long txPackets;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeString(this.iface);
        parcel.writeLong(this.rxBytes);
        parcel.writeLong(this.rxPackets);
        parcel.writeLong(this.txBytes);
        parcel.writeLong(this.txPackets);
        parcel.writeInt(this.ifIndex);
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
            this.iface = parcel.readString();
            if (parcel.dataPosition() - iDataPosition < i) {
                this.rxBytes = parcel.readLong();
                if (parcel.dataPosition() - iDataPosition < i) {
                    this.rxPackets = parcel.readLong();
                    if (parcel.dataPosition() - iDataPosition < i) {
                        this.txBytes = parcel.readLong();
                        if (parcel.dataPosition() - iDataPosition < i) {
                            this.txPackets = parcel.readLong();
                            if (parcel.dataPosition() - iDataPosition < i) {
                                this.ifIndex = parcel.readInt();
                                if (parcel.dataPosition() - iDataPosition < i) {
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
