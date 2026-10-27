package android.net;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class TetherOffloadRuleParcel implements Parcelable {
    public static final Parcelable.Creator<TetherOffloadRuleParcel> CREATOR = new Parcelable.Creator<TetherOffloadRuleParcel>() { // from class: android.net.TetherOffloadRuleParcel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TetherOffloadRuleParcel createFromParcel(Parcel parcel) {
            TetherOffloadRuleParcel tetherOffloadRuleParcel = new TetherOffloadRuleParcel();
            tetherOffloadRuleParcel.readFromParcel(parcel);
            return tetherOffloadRuleParcel;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TetherOffloadRuleParcel[] newArray(int i) {
            return new TetherOffloadRuleParcel[i];
        }
    };
    public byte[] destination;
    public byte[] dstL2Address;
    public int inputInterfaceIndex;
    public int outputInterfaceIndex;
    public int pmtu = 1500;
    public int prefixLength;
    public byte[] srcL2Address;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeInt(this.inputInterfaceIndex);
        parcel.writeInt(this.outputInterfaceIndex);
        parcel.writeByteArray(this.destination);
        parcel.writeInt(this.prefixLength);
        parcel.writeByteArray(this.srcL2Address);
        parcel.writeByteArray(this.dstL2Address);
        parcel.writeInt(this.pmtu);
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
            this.inputInterfaceIndex = parcel.readInt();
            if (parcel.dataPosition() - iDataPosition < i) {
                this.outputInterfaceIndex = parcel.readInt();
                if (parcel.dataPosition() - iDataPosition < i) {
                    this.destination = parcel.createByteArray();
                    if (parcel.dataPosition() - iDataPosition < i) {
                        this.prefixLength = parcel.readInt();
                        if (parcel.dataPosition() - iDataPosition < i) {
                            this.srcL2Address = parcel.createByteArray();
                            if (parcel.dataPosition() - iDataPosition < i) {
                                this.dstL2Address = parcel.createByteArray();
                                if (parcel.dataPosition() - iDataPosition < i) {
                                    this.pmtu = parcel.readInt();
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
